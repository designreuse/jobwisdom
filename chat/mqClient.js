var amqp = require("amqp");

var exchName = "directExchange";

var connOptions = {

      host: '120.24.165.15',

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

var receiveNotify  = null;

var logger = require('./log').logger("rabbitmq");

conn = amqp.createConnection(connOptions);

conn.on('ready',function() {

  logger.info("rabbitmq is ready ... ");

  conn.queue('queue_chat_notify', { autoDelete: false, durable: true }, function(queue) {

    queue.subscribe(function (msg) {

      receiveNotify(msg.data);

    });

  });

});


conn.on('close', function(){

  logger.error("rabbitmq is close");

});

conn.on('error', function (error) {

  logger.error('Connection error : ' + error);

});

exports.createConnection = function(r1) {
  
  receiveNotify = r1;

}

exports.publish = function(routeKey, message) {

  conn.publish(routeKey, message);

};