<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/base.jsp" %>

<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>H5微信支付测试</title>
  </head>
<link rel="stylesheet" href="<%=basePath%>css/mobile/weui.min.css?date=<%=new Date() %>" type="text/css" />
<body class="gray-bg">
<script type="text/javascript" src="<%=jqueryJsPath %>"></script>
<%@include file="/wechatBase.jsp" %>

<div class="weui_msg">
   <div class="weui_icon_area"><i class="weui_icon_success weui_icon_msg"></i></div>
   <div class="weui_text_area">
       <h2 class="weui_msg_title">操作成功</h2>
       <p class="weui_msg_desc">内容详情，可根据实际需要安排</p>
   </div>
   <div class="weui_opr_area">
       <p class="weui_btn_area">
           <a href="#" class="weui_btn weui_btn_primary" onclick="uploadImage()">头像</a>
           <a href="#" class="weui_btn weui_btn_default" onclick="initWechat(1,1)">支付</a>
       </p>
   </div>
   <div class="weui_extra_area">
       <a href="">查看详情</a>
   </div>
</div>

<div class="weui_dialog_alert" style="display: none">
   <div class="weui_mask"></div>
   <div class="weui_dialog">
       <div class="weui_dialog_hd"><strong class="weui_dialog_title">弹窗标题</strong></div>
       <div class="weui_dialog_bd">弹窗内容，告知当前页面信息等</div>
       <div class="weui_dialog_ft">
           <a href="#" class="weui_btn_dialog primary" onclick="initWechat(1,1)">支付</a>
       </div>
   </div>
</div>

<script type="text/javascript">
var localIds;
function uploadImage(){
	wx.chooseImage({
	    count: 1, // 默认9
	    sizeType: ['original', 'compressed'], // 可以指定是原图还是压缩图，默认二者都有
	    sourceType: ['album', 'camera'], // 可以指定来源是相册还是相机，默认二者都有
	    success: function (res) {
	        localIds = res.localIds; // 返回选定照片的本地ID列表，localId可以作为img标签的src属性显示图片
	        jQuery(".weui_dialog_bd").text(localIds);
	        jQuery(".weui_dialog_alert").show();
	    }
	});
}
function choseImage(){
	wx.uploadImage({
	    localId: localIds, // 需要上传的图片的本地ID，由chooseImage接口获得
	    isShowProgressTips: 1, // 默认为1，显示进度提示
	    success: function (res) {
	        var serverId = res.serverId; // 返回图片的服务器端ID
	        alert(serverId);
	    }
	});
}

function initWechat(personnelId, conferenceId){
	  $.ajax({
		  type : "POST",
		  url : baseUrl + "conference/init/pay",
		  data : "goodsName=渠道会议报名&personnelId="+personnelId+"&conferenceId="+conferenceId,
		  dataType : "json",
		  success : function(e){
			  if (e.code != 0) {
				  dialog(e.msg);
				  return;
			  }
			  callWeixin(e.msg, personnelId, conferenceId);
		  }
	  });
}

function callWeixin(rj, personnelId, conferenceId) {
  WeixinJSBridge.invoke('getBrandWCPayRequest', {
      "appId" : rj.appId,
      "timeStamp" : rj.timeStamp,
      "nonceStr" : rj.nonceStr,
      "package" : rj.package,
      "signType" : rj.signType,
      "paySign" : rj.paySign
  }, function(res) {
      if (res.err_msg == "get_brand_wcpay_request:ok") {
    	  
      } else {
    	  
      }
  });
}

</script>
</body>
</html>