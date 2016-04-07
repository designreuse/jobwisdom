var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
    res.send('<h1>Welcome Realtime Server</h1>');
});

http.listen(3000, function(){
    console.log('listening on *:3000');
});

var mq = require("./mqClient");

var co = require('co');
var wrapper = require('co-redis');
var redis = require("./redisClient");
var amqp = require("./amqp");
var redisClient = wrapper(redis.chatClient);

var logger = require("./log").logger("server");

var STORE_USER_KEY = "store_to_chat_user_set_";
var EMP_USER_SOCKET_KEY = "chat_user_to_socket_hash_emp";
var KF_USER_SOCKET_KEY = "chat_user_to_socket_hash_kf";

var KF_NOT_ONLINE_USER_ID = "kf_chat_not_online_user_id";
var EMP_NOT_ONLINE_USER_ID = "emp_chat_not_online_user_id";
var KF_NOT_ONLINE_MSG = "kf_chat_not_online_msg_";
var EMP_NOT_ONLINE_MSG = "kf_chat_not_online_msg_";

//rabbitmq监听到通知的回调操作
var receiveNotify = function(msg) {
	logger.info("\r\n\r\n<-- receiveNotify begin -->" + msg);
	var obj = JSON.parse(msg);
	sendMsg(obj);
}

mq.createConnection(receiveNotify);

io.on('connection', function (socket) {

  // 用户登陆
  socket.on('initUser',function(data){
	 var redis_key = STORE_USER_KEY + data.storeId;
	 //redis插入数据 
	 redisClient.sadd(redis_key,data.userId);
	 if (data.userType == 'emp'){
		redisClient.hset(EMP_USER_SOCKET_KEY, data.userId, socket.id);
	 	redisClient.hset(EMP_USER_SOCKET_KEY, socket.id, data.userId);
	 }
	 else if (data.userType == 'kf'){
		redisClient.hset(KF_USER_SOCKET_KEY, data.userId, socket.id);
	 	redisClient.hset(KF_USER_SOCKET_KEY, socket.id, data.userId);
	 }
	 //logger.info('initUser --->> 门店 :' + data.storeId + ', 用户 : ' + data.userId + ", socketId : " + socket.id);
  });

  // 客服聊天
  socket.on('chatMsg', function (fromUser,toUser,msg,userType) {
	 logger.info(fromUser +","+toUser+","+ msg);
	 co(function* () {
	 	 if (userType == 'emp'){
	 	 	var socketId = yield redisClient.hget(KF_USER_SOCKET_KEY, toUser);
	 	 	if (socketId == null){
			 	redisClient.zadd(KF_NOT_ONLINE_USER_ID, toUser, fromUser);
			 	var msgKey = KF_NOT_ONLINE_MSG + fromUser + "_" + toUser;
			 	redisClient.rpush(msgKey, msg);
			 	logger.info("客服不在线,转为离线消息.");
		 	}
		 	else {
			 	var data = {"fromUser":fromUser, "toUser":toUser, "msg":msg};
			 	amqp.persistence(fromUser, toUser, msg);
			 	io.to(socketId).emit("getMsg", JSON.stringify(data));
		 	}	
	 	 }
	 	 else if (userType == 'kf'){
	 	 	var socketId = yield redisClient.hget(EMP_USER_SOCKET_KEY, toUser);
	 	 	if (socketId == null){
			 	redisClient.zadd(EMP_NOT_ONLINE_USER_ID, toUser, fromUser);
			 	var msgKey = EMP_NOT_ONLINE_MSG + fromUser + "_" + toUser;
			 	redisClient.rpush(msgKey, msg);
			 	logger.info("用户不在线,转为离线消息.");
		 	}
		 	else {
			 	var data = {"fromUser":fromUser, "toUser":toUser, "msg":msg};
			 	amqp.persistence(fromUser, toUser, msg);
			 	io.to(socketId).emit("getMsg", JSON.stringify(data));
		 	}	 	 	
	 	 }
	  })(); 
  });

  //获取离线消息
  socket.on('getNotOnlineMsg', function (userId,userType) {
	 co(function* () {
	 	 if (userType == 'emp'){
			 var set = yield redisClient.zrangebyscore(EMP_NOT_ONLINE_USER_ID, '-inf', userId);
			 var socketId = yield redisClient.hget(EMP_USER_SOCKET_KEY, userId);
			 for ( var i in set) {
				while(true){
					var msgKey = KF_NOT_ONLINE_MSG + set[i] + "_" + userId;
					var data = new Object();
					var message = yield redisClient.lpop(msgKey);
					if (message == null) break;
					data.fromUser = set[i];
					data.toUser = userId;
					data.msg = message;
					//将消息发送至页面展现
					io.to(socketId).emit("getMsg", JSON.stringify(data));
					amqp.persistence(set[i], userId, data.msg);
				}
				redisClient.zrem(EMP_NOT_ONLINE_USER_ID, set[i]);
			 }	 	 	
	 	 }
	 	 else if (userType == 'kf'){
			 var set = yield redisClient.zrangebyscore(KF_NOT_ONLINE_USER_ID, '-inf', userId);
			 var socketId = yield redisClient.hget(KF_USER_SOCKET_KEY, userId);
			 for ( var i in set) {
				while(true){
					var msgKey = EMP_NOT_ONLINE_MSG + set[i] + "_" + userId;
					var data = new Object();
					var message = yield redisClient.lpop(msgKey);
					if (message == null) break;
					data.fromUser = set[i];
					data.toUser = userId;
					data.msg = message;
					//将消息发送至页面展现
					io.to(socketId).emit("getMsg", JSON.stringify(data));
					amqp.persistence(set[i], userId, data.msg);
				}
				redisClient.zrem(KF_NOT_ONLINE_USER_ID, set[i]);
			 }	
	 	 }
	  })(); 
  });

  socket.on('disconnect', function () {
  	  co(function* () {
		 var userId = yield redisClient.hget(EMP_USER_SOCKET_KEY, socket.id);
		 if (userId!=null){
		 	redisClient.hdel(EMP_USER_SOCKET_KEY, userId);
		 }
	  	 else {
	  	 	userId = yield redisClient.hget(KF_USER_SOCKET_KEY, socket.id);
	  	 	redisClient.hdel(KF_USER_SOCKET_KEY, userId);
	  	 }
	  })(); 
  });
});

var sendMsg = function(obj){
	co(function* () {
		var socketId = yield redisClient.hget(EMP_USER_SOCKET_KEY, obj.toUser);
		io.to(socketId).emit("message", obj);
	})(); 
}
