var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function(req, res){
    res.send('<h1>Welcome Realtime Server</h1>');
});

http.listen(3000, function(){
	logger.info("http listening is started");
});

var mq = require("./mqClient");

var co = require('co');
var wrapper = require('co-redis');
var redis = require("./redisClient");
var redisClient = wrapper(redis.chatClient);

var logger = require("./log").logger("server");

var STORE_USER_KEY = "store_to_chat_user_set_";
var USER_SOCKET_KEY = "chat_user_to_socket_hash";

//rabbitmq监听到通知的回调操作
var receiveNotify = function(msg) {
	logger.info("\r\n\r\n<-- receiveNotify begin -->");
	var obj = JSON.parse(msg);
	logger.info(obj);
	sendMsg(obj);
}

mq.createConnection(receiveNotify);

io.on('connection', function (socket) {

  io.sockets.emit('connect',{"success":'connection success'});
  socket.on('sendMsg', function (from,toUser,msg,msgType) {
	 co(function* () {
		 var userSID = yield redisClient.hget(USER_SOCKET_KEY, toUser);
		 io.sockets.connected[userSID].emit('getMessage',{msg:msg});
	 })(); 
  });

  socket.on('initUser',function(data){
	 var redis_key = STORE_USER_KEY + data.storeId;

	 //redis插入数据 
	 redisClient.sadd(redis_key,data.userId);
	 redisClient.hset(USER_SOCKET_KEY, data.userId, socket.id);

	 logger.info('initUser --->> 门店 :' + data.storeId + ', 用户 : ' + data.userId + ", socketId : " + socket.id);
  });

  socket.on('disconnect', function () {
	  
  });
});

var sendMsg = function(obj){
	co(function* () {
		var socketId = yield redisClient.hget(USER_SOCKET_KEY, obj.toUser);
		io.to(socketId).emit("message", obj);
	})(); 
}