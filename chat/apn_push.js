var amqp = require("amqp");
var apn = require('apn');

var exchName = "directExchange";
var connOptions = {
      host: '112.74.210.155',
      port: 5672,
      login: 'admin',
      password: 'admin'
    };
var exchOption = {
      type: 'direct',
      durable: true,
      autoDelete: false,
      confirm: false
};

var conn;
var receiveLogout = null;
var receiveNotify  = null;
var logger = require('./apn_log').logger("apns_push");

conn = amqp.createConnection(connOptions);
//生产地址：gateway.push.apple.com
//UATgateway.sandbox.push.apple.com
var service = new apn.Connection({address:'gateway.sandbox.push.apple.com',passphrase:"123456"});

        service.on('connected', function() {
                logger.info("Connected");
        });

        service.on('transmitted', function(notification, device) {
                logger.info("Notification transmitted to:" + device.token.toString('hex'));
        });

        service.on('transmissionError', function(errCode, notification, device) {
                logger.info("Notification caused error: " + errCode + " for device ", device, notification);
        });

        service.on('timeout', function () {
                logger.info("Connection Timeout");
        });

        service.on('disconnected', function() {
                logger.info("Disconnected from APNS");
        });

        service.on('socketError', function(){
                        logger.info('socketError');
        });


//普通消息聚合后发送给iOS客户端
var _pushToAPN=function(payload){
	var data    = JSON.parse(payload);
	var device  = data.deviceToken;
	var type    = data.fId;
	var counter = 1;

        // Put your device token here (without spaces):
        var deviceToken = device || "3cbc01db3d3f03f00d42f3fde4e22b66434ef17c244628c418f4d07e162e587e";

        var myDevice = new apn.Device(deviceToken);

        var note = new apn.Notification();
                note.expiry = Math.floor(Date.now() / 1000) + 3600; // Expires 1 hour from now.
                note.badge = counter;
                note.sound = "ping.aiff";
		note.payload = {fId : type};
	if(type===1){	
                note.alert = "您有新的聊天消息";
	}else if(type===3){
		note.alert=  "您已经处于离线状态";
	}else{
		note.alert = "您有新的通知";
	}
	logger.info("note : " + JSON.stringify(note));
        service.pushNotification(note, myDevice);
}

//READY
conn.on('ready',function() {
  logger.info("rabbitmq is ready ... ");

  // Use the default 'amq.topic' exchange
  conn.queue('queue_ios_notify', { autoDelete: false, durable: true }, function (q) {

      // Receive messages
      q.subscribe(function (message) {
      		logger.info("subscribe queue_ios_notifyqueue_ios_notify : " + message.data);
		_pushToAPN(message.data);
      });
  });
});//END OF ready


conn.on('close', function(){
  logger.error("rabbitmq is close");
});

conn.on('error', function (error) {
  logger.error('Connection error : ' + error);
});


//===================================================================================

var feedback_options = {
    "batchFeedback": true,
    "interval": 300
};

var feedback = new apn.Feedback(feedback_options);
feedback.on("feedback", function(devices) {
    devices.forEach(function(item) {
        // Do something with item.device and item.time;
	logger.info(item);
    });
});
