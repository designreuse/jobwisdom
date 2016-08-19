<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String chatPath = request.getContextPath();
			String chatPasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ chatPath + "/";
%>
<!-- <div class="new-news-wrap">
    <div class="alert alert-info">
        <button data-dismiss="alert" class="close" type="button">×</button>
        <span name="newMessagesTip"></span>
    </div>
</div> -->
<script src="<%=chatPath%>/js/common/socket.io.js"></script>
<script type="text/javascript">
var userId = '${session_key_user_id}';
var storeId = '${session_key_store_id}';
var storeName = '${session_key_store_name}';
var userType = 'emp';
var user = {"userId" : userId, "storeId" : storeId, "storeName" : storeName, "userType" : userType};
var socket = null;
if(!isEmpty(userId)){
	socket = io.connect('ws://120.76.223.240:443');
    	//登录聊天室
    socket.emit('initUser', user);
	//接收消息
    socket.on('message', function(data) {
    	console.log("message : " + JSON.stringify(data));
    	var fid = data.fid;
    	//PC通知类处理
    	if (fid == 2) {
    		var type = data.data.type;
    		//新预约或者服务交接
    		if (type == 2) {
    			//播放语音
    			textToVoice(1, data.data.msg);
    		} else if (type == 4) {
    			//播放语音
    			textToVoice(1, data.data.msg);
    		}
    	}
    });
	
	// 聊天
    socket.on('getMsg', function(data) {
    	var fromUser = data.fromUser;
    	var toUser = data.toUser;
    	data = eval('('+data+')');
    	var msg = data['msg'];
    	console.log(data);
    	console.log(msg);
    	var html = '<li class="media clearfix">'+
				    	'<div class="media-left">'+
				    	'<a href="javascript:void(0);" class="media-object">'+
				    		'<img src="'+baseUrl+'images/dd-img.png" alt="">'+
				        '</a>'+
					    '</div>'+
					    '<div class="media-body">'+
					        '<p class="media-text media-arr">'+msg+'</p>'+
					    '</div>'+
					'</li>';
    	jQuery("ul[ul_id='"+kfId+"']").append(jQuery(html));
    	jQuery("ul[ul_id='"+kfId+"']").animate({scrollTop:jQuery("ul[ul_id='"+kfId+"']").height()}, 800);
    	
   	});
    // 取出离线消息
    socket.emit('getNotOnlineMsg', userId, 'emp');
}
</script>
