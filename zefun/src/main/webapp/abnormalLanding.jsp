<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
var userId = '${session_key_user_id}';
var wsUrl = "ws://192.168.3.27/jobwisdom/websocket?userId=";
var webSocket;
if ('WebSocket' in window) {
	webSocket = new WebSocket(wsUrl + userId);
} else if ('MozWebSocket' in window) {
     websocket = new MozWebSocket(wsUrl + userId);
} else {
	webSocket = new SockJS(wsUrl + userId);
}
webSocket.onerror = function(event) {
	onError(event);
};
webSocket.onopen = function(event) {
	onOpen(event);
};
webSocket.onmessage = function(event) {
	onMessage(event);
};

//处理从服务端发送过来的数据
function onMessage(event) {
	loginOut(true);
}
function onOpen(event) {
	console.log("ws onOpen");
}
function onError(event) {
	console.log("ws onError");
}
</script>
