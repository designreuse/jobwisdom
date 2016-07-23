<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>分享发型</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
  </head>
<body>

<div class="content wrap">
	<div class="click-share bg-white">
	 <%--  <img id="headImg" src="<%=picPath %>mobile/share/hairstyle_show.png?imageView2/1/w/640/h/640" class="click-share-img"/> --%>
	  <div class="share-btn-group">
	    <div class="btn-content">
	      <div class="paizhao" onclick="chooseImgage()">
	        <div class="paizhao-wrap" >
	          <span class="iconfont icon-xiangji4"></span>
	        </div>
	        <div class="word">拍照</div>
	      </div>
	      <div class="fenxiang s-modal-control" data-target="#share-tip">
	        <div class="fenxiang-wrap">
	          <span class="iconfont icon-caidan"></span>
	        </div>
	        <div class="word" onclick="share();">分享给朋友</div>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- 分享提示 -->
	<div class="s-modal hide s-modal-miss no-padding" id="share-tip">
	  <div class="s-modal-wrap">
	    <div class="share-tip">
	      <img src="<%=basePath %>images/mobile/member/share-tip.png" alt=""/>
	      <div class="know">我知道了</div>
	    </div>
	  </div>
	</div>
	
	<!-- 分享成功 -->
	<div class="s-modal hide s-modal-miss" id="shareSuccessTip">
        <div class="s-modal-wrap">
            <div class="d-member-info">
                <div class="n-modal-title text-center">
                    <span>分享成功</span>
                    <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
                    <div class="clearfix"></div>
                </div>
                <div class="s-modal-body">
                    <div class="word text-left">
                        <span id="integralNum"></span>
                    </div>
                </div>
                <div class="s-modal-foot">
                    <div class="modal-confirm" onclick="backHome();">返回主页</div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../wechatBase.jsp" %>
<script src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
<script src="<%=basePath%>js/qiniu/hmac-sha1.js"></script>
<%@include file="../memberBase.jsp" %>
<script>
function share(){
	$("#share-tip").removeClass("hide");
}

function backHome(){
	window.location.href = baseUrl + "memberCenter/view/home/${session_key_store_account}/1";
}

var titleArray = ['之前只能算是剃头，在这家美发店，我终于做了一回“造型”！',
                  '理了二十年的发，在这里终于体会到什么叫做“造型”！',
                  '推荐一家省时间的理发店，提前预约不排队，分享发型还送券！'];
                  
var title = titleArray[Math.floor((Math.random()*titleArray.length))];
var content = "无内容";
var desc = '';
var link = baseUrl + 'memberCenter/view/shareInfo?code=${code}&orderId=${orderId}&mainStoreId=${session_key_store_account}';
var imgUrl = 'http://7xkv8r.com1.z0.glb.clouddn.com/faxing.jpg?imageView2/1/w/200/h/200';

var hairImg = "";
var orderId = "${orderId}";

wx.ready(function () {
	registerShare();
});

//注册分享事件
function registerShare(){
	wx.onMenuShareAppMessage({
	  title: title,
	  desc: desc,
	  link: link,
	  imgUrl: imgUrl,
	  success: shareCallback,
	  cancel: shareCancel
	});

	wx.onMenuShareTimeline({
	  title: title,
	  link: link,
	  imgUrl: imgUrl,
	  success: shareCallback,
      cancel: shareCancel
	});
}

function shareCallback(res){
	if (isEmpty(hairImg)) {
		dialog("您还没有拍发型呢");
		return;
	}
	$.ajax({
        url : baseUrl + "memberCenter/action/share",
        type : "post",
        data : "orderId=" + orderId + "&imgUrl=" + hairImg + "&content=" + content,
        dataType : "json",
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            
            var reward = e.msg.rewardDesc;
            if (isEmpty(reward)) {
            	$("#integralNum").html("感谢您的分享，期待您的再次光临！");
            } else {
            	$("#integralNum").html("感谢您的分享，" + reward + "已放至您的账户。期待您的再次光临！");
            }
            $("#shareSuccessTip").removeClass("hide");
        },
        beforeSend : function(){
        }
    });
}

function shareCancel(res){
    dialog("您取消了分享");
}

function chooseImgage(){
    wx.chooseImage({
        count: 1,
        sizeType: ['original', 'compressed'],
        sourceType: ['album', 'camera'], 
        success: function (res) {
            var localIds = res.localIds; 
            wx.uploadImage({
                localId: localIds[0], 
                isShowProgressTips: 1,
                success: function (res) {
                    var serverId = res.serverId;
                    var key = "userhead/share/" + new Date().getTime();
                    $.ajax({
                        type : "post",
                        url : baseUrl + "wechat/fetch/media",
                        data : "mediaid=" + serverId + "&key=" + key,
                        dataType : "json",
                        success : function(e){
                            if (e.code != 0) {
                                dialog(e.msg);
                                return;
                            }
                            imgUrl = picUrl + key + "?imageView2/1/w/200/h/200";
                            hairImg = key;
                            registerShare();
                            
                            var imgSrc = picUrl + key + "?imageView2/1/w/640/h/640";
                            $("#headImg").attr("src", imgSrc);
                        }
                    });
                }
            });
        }
    });
}
</script>
</body>
</html>