var Chat = function(socket, uid) {
  this.socket = socket;
  this.uid = uid;
  this.useable = false;
};

Chat.prototype.login = function() {
  var message = {
    fId: 0,
    uId: this.uid
  };
  //console.log("login message", message);
  this.socket.emit('login', message);
};

Chat.prototype.sendMessage = function(to, text, type) {
  if(!this.useable) {
    return false;
  }
  var message = {
    fId: 1,
    tId: this.tId,
    data : {
      createTime: new Date().getTime(),
      msg: text,
      msgType: type,
      toUser: to,
      receiveTime: 0
    }
  };
  //console.log("send message", message);
  this.socket.emit('sendMsg', message);
  return true;
};

var parse_notice_msg = function(chat, data, cb){
  if(cb) {
    cb(data);
    return;
  }
};

var exec_funcs = {'2' : parse_notice_msg};

var do_chat = function(uid, cb){
  var socket = io.connect("ws://120.25.254.164:9000");
  var c = new Chat(socket, uid);
  c.login();
  socket.on('message', function(data) {
    if(data.fId != 0 && data.fId != 1) { // 不是需要处理的消息.
      return;
    }
    var func = exec_funcs[data.fId + ''];
    if(func) {
      func(c, data, cb);
    }
  });
  return c;
};
