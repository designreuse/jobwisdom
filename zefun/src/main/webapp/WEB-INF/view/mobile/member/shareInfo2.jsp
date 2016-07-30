<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>好友推荐</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <style>
      	html {
	      font-size: 62.5%;
	      height: 100%;
	      font-family: "微软雅黑";
	    }
	
	    @media screen and (max-width: 320px) {
	      html {
	        font-size: 10px;
	      }
	    }
	
	    @media (min-width: 321px) and (max-width: 413px) {
	      html {
	        font-size: 12px;
	      }
	    }
	
	    @media (min-width: 414px) and (max-width: 600px) {
	      html {
	        font-size: 14px;
	      }
	    }
	
	    @media (min-width: 600px) and (max-width: 720px) {
	      html {
	        font-size: 16px;
	      }
	    }
	
	    @media screen and (min-width: 768px) {
	      html {
	        font-size: 19px;
	      }
	    }
	
	    a:link, a:visited, a:hover, a:active {
	      color: #1e807a;
	    }
	
	    .hide {
	      display: none;
	    }
	
	    body {
	      height: 100%;
	      margin: 0;
	      position: absolute;
	      width: 100%;
	      background: url("<%=basePath %>images/mobile/member/fenxiang-bg.png") no-repeat #2fc3bf;
	      text-align: center;
	      background-size: 100% 100%;
	    }
	
	    .fl {
	      float: left;
	    }
	
	    .fr {
	      float: right;
	    }
	
	    .qianbao-wrap {
	      width: 100%;
	      max-width: 768px;
	      margin: auto;
	      height: 28rem;
	      position: relative;
	    }
	
	    .qianbao-img {
	      width: 100%;
	      height: 28rem;
	
	    }
	
	    .logo-img {
	      height: 4rem;
	    }
	
	    .money-num, .qianbao-name, .logo {
	      width: 100%;
	      text-align: center;
	    }
	
	    .logo {
	      position: absolute;
	      top: 5rem;
	    }
	
	    .money-num {
	      position: absolute;
	      top: 10rem;
	      font-size: 4rem;
	    }
	
	    .qianbao-name {
	      position: absolute;
	      top: 14.5rem;
	      font-size: 1.6rem;
	    }
	
	    .share-word {
	      padding: 0 8% 0 7%;
	      position: absolute;
	      top: 17.2rem;
	    }
	
	    .person-img {
	      float: left;
	      height: 3rem;
	      width: 3rem;
	      border-radius: 50%;
	      margin-top: 1rem;
	    }
	
	    .word {
	      margin-left: 3.5rem;
	      display: inline-block;
	      margin-top: -2.9rem;
	      float: left;
	      height: 3rem;
	      overflow: hidden;
	
	      text-align: left;
	      font-size: 1.1rem;
	    }
	
	    .link-hair {
	      position: absolute;
	      bottom: 3rem;
	      right: 12%;
	      font-size: 1rem;
	      height: 2.5rem;
	      line-height: 2.5rem;
	      width: 10rem;
	      text-align: center;
	      background: url("<%=basePath %>images/mobile/member/new-hair-a.png") no-repeat;
	      background-size: 100% 100%;
	    }
	
	    .link-hair a {
	      color: #333;
	    }
	
	    a {
	      text-decoration: none;
	    }
	
	    .tel {
	      height: 4.5rem;
	      width: 100%;
	      margin: auto;
	      line-height: 4.5rem;
	      background-color: #D2F6F5;
	      border: 1px solid #3AAEAA;
	      border-radius: 5px;
	      color: #49A4A1;
	      padding: 0 1rem;
	      box-sizing: border-box;
	      position: relative;
	      font-size: 1.4rem;
	      font-family: "微软雅黑";
	    }
	
	    .btn {
	      height: 4.5rem;
	      line-height: 4.5rem;
	      width: 85%;
	      margin: auto;
	      margin-top: 1rem;
	      background: -webkit-linear-gradient(#fcd048 40%, #edb91b);
	      color: #3a2c07;
	      text-align: center;
	      border-radius: 0.5rem;
	      font-weight: 700;
	      position: relative;
	      font-size: 1.8rem;
	      font-family: "微软雅黑";
	    }
	
	    .shop-detail {
	      bottom: .5rem;
	      width: 100%;
	      margin: auto;
	      color: #fff;
	      text-align: center;
	      margin-top: 3rem;
	    }
	
	    .detail-wrap {
	      text-align: left;
	      display: inline-block;
	    }
	
	    .name {
	      font-size: 2rem;
	      line-height: 3rem;
	    }
	
	    .shop-tel {
	      font-size: 1.2rem;
	      line-height: 2rem;
	    }
	
	    .address {
	      font-size: 1.2rem;
	      line-height: 2rem;
	    }
	
	    .shop-tel img {
	      width: 1.25rem;
	      height: 1.3rem;
	    }
	
	    .address img {
	      width: 1.2rem;
	      height: 1.4rem;
	    }
	
	    .detail-wrap img {
	      vertical-align: text-bottom;
	      margin-right: 0.8rem;
	    }
	
	    .fx-btn {
	      height: 2.8rem;
	      line-height: 2.8rem;
	      border-radius: 0.5rem;
	      padding: 0 0.5rem;
	      float: right;
	      position: absolute;
	      top: 50%;
	      margin-top: -1.4rem;
	      right: 1rem;
	      background: -webkit-linear-gradient(#fcd048 40%, #edb91b);
	    }
	
	    .use-youhui {
	      border: 1px solid #123134;
	      border-radius: 0.3rem;
	    }
	
	    .use-title {
	      height: 3rem;
	      text-align: left;
	      line-height: 3rem;
	      background-color: #123134;
	      color: #20d0cb;
	      padding-left: 0.5rem;
	    }
	
	    .fl {
	      float: left;
	    }
	
	    .fx-main {
	      overflow: hidden;
	      margin: 0 auto;
	      display: inline-block;
	      margin-bottom: 1rem;
	    }
	
	    .fx-main img {
	      width: 2.5rem;
	      margin-right: 1rem;
	    }
	
	    .fx-youhui {
	      display: none;
	    }
	
	    .fx-title {
	      margin-bottom: 1rem;
	    }
	
	    .fx-title p {
	      color: #d0211c;
	      text-align: center;
	      font-size: 1.8rem;
	      font-weight: bold;
	      margin: 0;
	    }
	
	    .use-weixin {
	      padding-left: 0.5rem;
	    }
	
	    .use-weixin p {
	      margin: 0;
	      font-size: 1rem;
	      text-align: left;
	    }
	
	    .zimu {
	      font-size: 1.2rem !important;
	      font-family: Palatino Linotype;
	    }
	
	    .input-wrap {
	      width: 85%;
	      margin: 0 auto;
	      margin-top: 1rem;
	    }
	
	    .yazhengma .tel {
	      width: 64%;
	      margin-top: 0;
	      margin-left: -2%;
	    }
	
	    .yazhengma .yzm {
	      width: 34%;
	      background: -webkit-linear-gradient(#fcd048 40%, #edb91b);
	      height: 4.5rem;
	      float: right;
	      font-size: 1.4rem;
	      font-weight: 600;
	      line-height: 4.5rem;
	      color: #3a2c07;
	      text-align: center;
	      border-radius: 0 0.5rem 0.5rem 0;
	    }
	
	    .fx-index input {
	      font-size: 1.75rem;
	    }
	
	    .fx-index input::-webkit-input-placeholder {
	      font-size: 1.4rem;
	    }
	
	    .yazhengma .disable {
	      width: 34%;
	      height: 4.5rem;
	      float: right;
	      font-weight: 600;
	      background: #e0e0e0;
	      font-size: 1.4rem;
	      line-height: 4.5rem;
	      background-color: #e0e0e0;
	      border-radius: 0 0.5rem 0.5rem 0;
	      color: #3a2c07;
	      text-align: center;
	    }
	
	    .yazhengma .disable .time {
	      font-weight: 600;
	      color: #ec5c13;
	    }
	
	    .fx-wrap {
	      width: 90%;
	      position: relative;
	      margin: 0 auto;
	      margin-top: 2rem;
	      /*display: none;*/
	      box-sizing: border-box;
	    }
	
	    .fx-wrap-in {
	      width: 100%;
	      box-sizing: border-box;
	      background-color: #60d8d5;
	      border: 1px solid #097572;
	      border-radius: 1rem;
	      padding: 1rem 2.5rem 3rem 1rem;
	      position: relative;
	
	    }
	
	    .fx-wrap-in p {
	      font-size: 1.4rem;
	      text-align: left;
	      color: #032120;
	    }
	
	    .fx-wrap-in .person img {
	      width: 5rem;
	      position: absolute;
	      right: 0rem;
	      top: -3rem;
	    }
	
	    @media screen and (max-width: 320px) {
	      .fx-wrap .fx-info {
	        width: 90%;
	      }
	    }
	
	    @media screen and (min-width: 768px) {
	      .fx-wrap .fx-info {
	        width: 68%;
	      }
	    }
	
	    .arr1, .arr2 {
	      width: 0rem;
	      height: 0rem;
	      border-width: 0.8rem;
	      margin-left: -0.4rem;
	      border-color: transparent transparent #ffa271;
	      border-style: solid;
	      position: absolute;
	      left: 50%;
	      top: -1.5rem;
	      z-index: 2;
	    }
	
	    .arr2 {
	      top: -1.6rem;
	      z-index: 1;
	      border-color: transparent transparent rgba(255, 255, 255, .5);
	    }
	
	    .jiangli {
	      border: 1px solid #189794;
	      width: 100%;
	      box-sizing: border-box;
	      border-radius: 1rem;
	      color: #123134;
	      text-align: left;
	      padding: 0rem 2rem 1rem;
	      position: relative;
	      margin-top: 3rem;
	      margin-bottom: 1rem;
	    }
	
	    .fx-wrap-btn {
	      width: 11rem;
	      border-radius: 4rem;
	      height: 3rem;
	      font-size: 1.4rem;
	      bottom: -1rem;
	      line-height: 3rem;
	      padding: 0 1rem;
	      position: absolute;
	      background-color: #f1b617;
	      left: 50%;
	      margin-left: -6.5rem;
	      box-shadow: 0 2px 0px #d29d0e;
	      color: #fff;
	      text-shadow: 0 2px 1px rgba(0, 0, 0, .3);
	    }
	
	    .jiangli .title {
	      font-size: 1.4rem;
	
	    }
	
	    .jiangli p {
	     /* margin-top: .5rem;*/
	      width: 95%;
	    }
	    .jiangli1{
	      overflow: hidden;
	      margin-top: .5rem;
	    }
	    .jiangli1 span{
	      margin-top: 0.1rem;
	    }
	    .bold {
	      font-weight: 700;
	    }
	
	    p {
	      padding: 0;
	      margin: 0;
	    }
	
	    .code {
	      margin: 0 auto;
	    }
	
	    .code img {
	      width: 14rem;
	      margin-left: 3rem;
	      margin-top: 2rem;
	    }
	    .word a{
	      color: #fffdcd;
	    }
	
	    /*模态框*/
	    /*分享提示*/
	    .s-modal-wrap {
	      max-width: 768px;
	      margin: 0 auto;
	    }
	
	    .s-modal-wrap img {
	      width: 100%;
	    }
	
	    .s-modal {
	      background-color: rgba(0, 0, 0, 0.5);
	      width: 100%;
	      z-index: 1000;
	      position: fixed;
	      top: 0;
	      overflow: hidden;
	      height: 100%;
	    }
	
	    .no-padding {
	      padding: 0 !important;
	    }
	
	    .red {
	      color: #ff1f19;
	    }
	
	    .pb2 {
	      padding-bottom: 2rem;
	    }
	    
      	.alertWrap {
		  position: fixed;
		  top: 0;
		  left: 0;
		  height: 100%;
		  width: 100%;
		  z-index: 200;
		  text-align: center;
		  box-sizing: border-box;
		  -webkit-box-sizing: border-box;
		  display: none;
		  font-size: 1.4rem;
		}
		.alert {
		  display: inline-block;
		  background-color: rgba(0, 0, 0, 0.6);
		  color: #fff;
		  text-align: center;
		  padding: .625rem;
		  margin: 0 auto;
		  max-width: 38.75rem;
		  border-radius: .3125rem;
		  -webkit-border-radius: .3125rem;
		  box-sizing: border-box;
		  -webkit-box-sizing: border-box;
		}
		.loadWrap {
		  position: fixed;
		  top: 0;
		  left: 0;
		  height: 100%;
		  width: 100%;
		  z-index: 9999;
		  text-align: center;
		  box-sizing: border-box;
		  -webkit-box-sizing: border-box;
		  background-color: transparent;
		}
		.loadWrap .loadingImg {
		  font-size: 18px;
		  width: 100px;
		  margin: auto;
		  height: 100px;
		  line-height: 100px;
		  border-radius: 10px;
		  background-color: rgba(0, 0, 0, 0.85);
		  text-align: center;
		  color: #e3e3e3;
		  position: absolute;
		  top: 0px;
		  bottom: 0px;
		  left: 0px;
		  right: 0px;
		}
		.loadWrap .loadingImg img {
		  vertical-align: middle;
		}
    </style>
  </head>
<body>
<div class="fenxiang">
  <div class="qianbao-wrap">
    <img class="qianbao-img" src="<%=basePath%>images/mobile/member/qianbao.png" alt="">
    <div class="logo">
      <img class="logo-img" src="<%=picPath%>${projectShare.imgUrl}" alt="">
    </div>
    <div class="money-num">${rewardAmount }<span style="font-size: 1.6rem">${rewardUnit }</span></div>
    <div class="qianbao-name">
      <span>${rewardName }</span>
    </div>
    <div class="share-word">
      <img class="person-img" src="<%=picPath%>${ownerInfo.headUrl}" alt="">
      <div class="word" id="shareText">好样的</div>
    </div>
    <div class="link-hair">
      <a href="javascript:showHairstyle();">
        <span>看看她的新发型  >></span>
      </a>
    </div>
  </div>
</div>

<div class="fx-wrap">
  <!--领取奖励-->
  <div class="fx-index hide" id="newMember">
    <div class="input-wrap">
      <input id="phone" type="tel" class="tel" placeholder="输入手机号领券吧"/>
    </div>
    <div class="input-wrap mt yazhengma">
      <input type="tel" id="verifyCode" class="tel" placeholder="验证码">
      <span class="yzm" id="getDiv" onclick="getVerifyCode()">获取验证码</span>
      <span class="disable hide" id="timeDiv">剩余 <span id="time" class="time font-size-28 ">120"</span></span>
    </div>
    <div class="btn" onclick="getReward()">点击领取</div>
  </div>
  
  <!--老会员-->
  <div class="fx-wrap-in hide" id="oldMember">
    <div class="person"><img src="<%=basePath%>images/mobile/member/share-pic.png" alt=""/></div>
    <p>搜噶，您已经悄悄滴成了本店会员, 快给您的闺蜜们分享下做过的好项目吧，重重有赏哦！</p>
    <div class="fx-wrap-btn" onclick="window.location.href='<%=basePath%>memberCenter/view/orderList'">立即去分享</div>
  </div>
  
  <!--分享者-->
  <div class="fx-wrap-in hide" id="ownerMember">
    <div class="person">
    <c:choose>
   		<c:when test="${recommendCount > 0 }">
   			<img src="<%=basePath%>images/mobile/member/share-pic1.png" alt=""/>
   		</c:when>
   		<c:otherwise>
   			<img src="<%=basePath%>images/mobile/member/share-pic2.png" alt=""/>
   		</c:otherwise>
   	</c:choose>
   	</div>
    <p id="recommendText"></p>
    <div class="fx-wrap-btn s-modal-control">继续分享</div>
  </div>
  
  <!--新会员领取奖励-->
  <div class="fx-wrap-in pb2 hide" id="subscribe">
    <div class="person"><img src="<%=basePath%>images/mobile/member/share-pic1.png" alt=""/></div>
    <p><span class="red">恭喜您! </span>奖励已放置您的账户，长按二维码关注查看～～</p>

    <div class="code">
      <img src="<%=picPath %>qrcode_for_${qrcode }_258.jpg" alt=""/>
    </div>
  </div>
  
  <!--奖励说明-->
  <div id="shareDesc" style="padding-bottom: 2rem">
  	<c:choose>
  		<c:when test="${empty rewardName }">
  			<fieldset class="jiangli">
  			<legend align="center"><span class="title">奖励说明</span></legend>
  				店家暂未设置任何奖励，感谢您的支持。
  			</fieldset>
  		</c:when>
  		<c:otherwise>
  			<fieldset class="jiangli">
		      <legend align="center"><span class="title">奖励说明</span></legend>
		        <div class="jiangli1">
		            <span class="bold fl">1.</span>
		            <p class="fl">
		              &nbsp;您的好友通过您分享的页面成功注册可获得<br/><span class="bold">${rewardAmount }${rewardUnit }${rewardName }！</span>
		            </p>
		        </div>
		        <div class="jiangli1">
		          <span class="bold fl">2.</span>
		          <p class="fl">
		            &nbsp;您分享任一笔项目消费订单可获得<span class="bold">${shareDesc }</span>.<br/>
		           （注：每笔订单仅可获得一次奖励)
		          </p>
		       </div>
		        <div class="jiangli1">
		          <span class="bold fl">3. </span>
		          <p class="fl">
		            &nbsp;${firstConsumeDesc }。
		          </p>
		        </div>
		    </fieldset>
  		</c:otherwise>
  	</c:choose>
  </div>
</div>

<!--点击分享模态框-->
<div class="s-modal s-modal-miss no-padding hide" id="shareTip">
  <div class="s-modal-wrap">
    <img src="<%=basePath%>images/mobile/member/share-tip.png" alt=""/>
  </div>
</div>

<%@include file="../wechatBase.jsp" %>
<%@include file="../memberBase.jsp" %>
<script type="text/javascript">
//分享者
if ("${isOwner}"){
	$("#ownerMember").removeClass("hide");
	$("#recommendText").html(getRecommendText('${recommendCount}'))
}
//新会员
else if (!'${memberInfo}') {
	$("#newMember").removeClass("hide");
	$("#shareDesc").addClass("hide");
}
//老会员
else {
	$("#oldMember").removeClass("hide");
}

$(".s-modal-control").click(function(){
	$("#shareTip").removeClass("hide");
});
$(".s-modal-miss").click(function(){
	$("#shareTip").addClass("hide");
});

var storeName = "${storeInfo.storeName }";
var projectName = "${projectInfo.projectName}";
var contentArray = ['我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">' + storeName + '</a> 做了个 <b>' + projectName + '</b>，提前预约不等待，特别推荐给惜时如金的你！',
                    '我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">' + storeName + '</a> 做了个 <b>' + projectName + '</b>，老板剪起头发来，舒服得我不要不要的！',
                    '我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">' + storeName + '</a> 做了个 <b>' + projectName + '</b>，理发帅哥随便挑，温柔细心活还好！',
                    '我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">' + storeName + '</a> 做了个 <b>' + projectName + '</b>，洗头小妹个个靓，聊天按摩不在话下！',
                    '我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">' + storeName + '</a> 做了个 <b>' + projectName + '</b>，半价优惠还送券，老板简直不赚钱！']
                    
$("#shareText").html(contentArray[Math.floor((Math.random()*contentArray.length))]);

var imgUrl = picUrl + "${projectShare.imgUrl}";
var titleArray = ['之前只能算是剃头，在这家美发店，我终于做了一回“造型”！',
                  '理了二十年的发，在这里终于体会到什么叫做“造型”！',
                  '推荐一家省时间的理发店，提前预约不排队，分享发型还送券！'];
                  
var title = titleArray[Math.floor((Math.random()*titleArray.length))];
var desc = '';
var code = "${code}";
var link = baseUrl + 'memberCenter/view/shareInfo?code=' + code + '&orderId=${orderId}&mainStoreId=${session_key_store_account}';

wx.ready(function () {
	wx.onMenuShareAppMessage({
	  title: title,
	  desc: desc,
	  link: link,
	  imgUrl: imgUrl
	});

	wx.onMenuShareTimeline({
	  title: title,
	  link: link,
	  imgUrl: imgUrl
	});
});

function showHairstyle(){
	wx.previewImage({
	    current: imgUrl, // 当前显示图片的http链接
	    urls: [imgUrl] // 需要预览的图片http链接列表
	});
}

var storeId = "${storeInfo.storeId}";
var number = 120;
var timer = null;
var time = $("#time");
function clock(){
  number--;
  if(number <= 0){
	  $("#timeDiv").addClass("hide");
      $("#getDiv").removeClass("hide");
	  number = 120;
	  window.clearInterval(timer);
  }else{
	  time.html(number + '"');
  }
}

function getVerifyCode(){
  var phone = $("#phone").val();
  var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
   if (!reg.test(phone)) {
     dialog("请输入正确的手机号码!");
     return;
   }
  
  $("#getDiv").addClass("hide");
  $("#timeDiv").removeClass("hide");
  timer = setInterval("clock()",1000);
  $.ajax({
       type : "POST",
       url : baseUrl + "memberCenter/action/getVerifyCode",
       data : "storeId=" + storeId + "&phone=" + $("#phone").val(),
       dataType : "json",
       success : function(e){
           if (e.code != 0) {
               dialog(e.msg);
               return;
           }
       }
   });
}
function getReward(){
  var phone = $("#phone").val();
  var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
     if (!reg.test(phone)) {
     	dialog("请输入正确的手机号码!");
     	return;
     }
  var verifyCode = $("#verifyCode").val();
  $.ajax({
	  type : "POST",
	  url : baseUrl + "memberCenter/action/getRewardAction",
	  data : "code=" + code + "&storeId=" + storeId + "&phone=" + phone + "&verifyCode=" + verifyCode,
	  dataType : "json",
	  success : function(e){
		  if (e.code != 0) {
			  dialog(e.msg);
			  return;
		  }
		  $("#newMember").addClass('hide');
		  $("#subscribe").removeClass('hide');
	  }
  });
}

function getRecommendText(recommendCount){
	var text = "恭喜您，已成功邀请了 <span class='red'>" + recommendCount + "</span> 位好闺蜜。";
	if (recommendCount == 0) {
		text = "您目前还未成功邀请好友注册，别气馁。继续分享给您的朋友们！";
	}
	else if (recommendCount > 0 && recommendCount < 6) {
		text += "看来人气还不够旺哦，继续分享吧！";
	}
	else if (recommendCount > 5 && recommendCount < 16) {
		text += "您的新发型已经得到不少好友的肯定！继续分享吧！";
	}
	else if (recommendCount > 15 && recommendCount < 31) {
		text += "您在好闺蜜当中已经树立起一定的威信，继续分享不要停！";
	}
	else if (recommendCount > 30 && recommendCount < 51) {
		text += "原来您就是深藏不露的人气王！请收下我的膝盖！";
	}
	else {
		text += "您在朋友圈已经达到呼风唤雨的地步，在下跪服！";
	}
	return text;
}
</script>
</body>
</html>