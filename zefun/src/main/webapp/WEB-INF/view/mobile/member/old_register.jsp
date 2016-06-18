<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>注册</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
    <style>
	    .login-img {
	        text-align: center;
	        padding-top: 4rem;
	    }
	    .login-img img {
	        width: 12rem;
	        height: 12rem;
	        margin-top: .5rem;
	    }
	    .login-pic {
	        width: 13rem;
	        height: 13rem;
	        margin: 0 auto;
	        position: relative;
	        z-index: 9999;
	    }
	    .login-wrap {
	        max-width: 100%;
	    }
	    .login-wrap .content {
	        background: url(<%=basePath %>images/mobile/member/login-bg.jpg) repeat;
	        max-width: auto;
	    }
	    .login-wrap .content .login-name {
	        color: #333333;
	        text-shadow: 1px 1px 0px rgba(255, 255, 255, 0.5);
	        display: block;
	        margin-top: 1rem;
	        font-size: 3rem;
	    }
	    .yanzheng {
	        height: 3rem;
	        line-height: 3rem;
	        margin-top: 4rem;
	        text-align: center;
	        position: relative;
	    }
	    .yanzheng span {
	        position: absolute;
	        z-index: 2;
	        left: 50%;
	        -webkit-transform: translatex(-50%);
	        -moz-transform: translatex(-50%);
	        -ms-transform: translatex(-50%);
	        -o-transform: translatex(-50%);
	        transform: translatex(-50%);
	        font-size: 1.6rem;
	        color: #fff;
	        width: 56%;
	    }
	    .yanzheng:after {
	        content: "";
	        display: inline-block;
	        border-top: 1px solid #fff;
	        width: 22%;
	        position: absolute;
	        left: 0;
	        top: 50%;
	        z-index: 1;
	    }
	    .yanzheng:before {
	        content: "";
	        display: inline-block;
	        border-top: 1px solid #fff;
	        width: 22%;
	        position: absolute;
	        right: 0;
	        top: 50%;
	        z-index: 1;
	    }
	
	    .s-login-wrap {
	        position: relative;
	    }
	    .yazhengma .yzm {
	        width: 34%;
	        background: -webkit-linear-gradient(#fcd048 40%, #edb91b);
	        height: 5.625rem;
	        float: right;
	        font-weight: 600;
	        line-height: 5.625rem;
	        color: #3a2c07;
	        text-align: center;
	        border-radius: 0 0.5rem 0.5rem 0;
	    }
	    .yazhengma .disable {
	        width: 34%;
	        background-image: none;
	        background-color: #e0e0e0;
	        height: 5.625rem;
	        float: right;
	        font-weight: 400;
	        line-height: 5.625rem;
	        text-align: center;
	        border-radius: 0 0.5rem 0.5rem 0;
	        color: #3a2c07;
	    }
	
	    .login-arr {
	        position: absolute;
	        top: -10rem;
	        left: 50%;
	        margin-left: -16rem;
	    }
	    .login-arr img {
	        width: 8.125rem;
	    }
	    .login-wz {
	        margin-top: 22rem;
	    }
	    .login-wz img {
	        width: 28rem;
	    }
	
	    .register input {
	        width: 100%;
	        height: 5.625rem;
	        background-color: #bfe7e5;
	        padding: 0rem 1rem;
	        border: 1px solid #06524e;
	        /* border-bottom: 0rem; */
	        box-shadow: none;
	        border-radius: 0.5rem;
	    }
	    input {
	        font-size: 2.2rem;
	        color: #333;
	    }
	    .yazhengma input {
	        width: 64%;
	        border-radius: 0.5rem 0 0 0.5rem;
	    }
	    .register .normal-btn {
	        height: 5.625rem;
	        line-height: 5.625rem;
	        font-size: 2.625rem;
	        color: #3a2c07;
	        margin-top: 2.75rem;
	        background: -webkit-linear-gradient(#fcd048 40%, #edb91b);
	        border-radius: 0.5rem;
	    }
	    .yazhengma .disable .time{
	        font-weight: 600;
	        color: #ec5c13;
	        font-size: 2.5rem;
	    }
	
	</style>
  </head>
  <body>
  <div class="loading">
    <img src="http://7xkv8r.com1.z0.glb.clouddn.com/pc/loding.gif" alt="">
  </div>
	<div class="content wrap">
	   <div class="wrap login-wrap">
	    <div class="content">
	        <div class="login-img">
	            <div class="login-pic">
	                <img src="<%=picPath %>${storeInfo.storeLogo }" alt=""/>
	            </div>
	            <span class="login-name">${storeInfo.storeName }</span>
	        </div>
	        <div class="yanzheng">
	            <span>验证手机号，轻松管理会员卡</span>
	        </div>
	        <div class="register">
	            <div class="input-wrap mt2">
	                <input type="tel" id="phone" placeholder="手机号码"/>
	            </div>
	            <div class="input-wrap mt yazhengma">
	                <input type="tel" id="verifyCode" placeholder="验证码"/>
	                <span class="yzm" id="getDiv" onclick="getVerifyCode()">获取验证码</span>
	                <span class="disable hide" id="timeDiv">剩余&nbsp;&nbsp;<span id="time" class="time font-size-28 ">120"</span></span>
	            </div>
	            <div class="btn-group">
	                <div class="normal-btn btn w100p" onclick="register();">注册</div>
	            </div>
	        </div>
	    </div>
	</div>
	</div>
<%@include file="../memberBase.jsp" %>
<script type="text/javascript">
  var windowHeight=$(window).height()
  $(".login-wrap").css({"height":windowHeight})

  var storeId = "${storeId}";
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
  function register(){
	  var phone = $("#phone").val();
	  var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
      if (!reg.test(phone)) {
        dialog("请输入正确的手机号码!");
        return;
      }
	  var verifyCode = $("#verifyCode").val();
	  $.ajax({
		  type : "POST",
		  url : baseUrl + "memberCenter/action/register",
		  data : "storeId=" + storeId + "&phone=" + phone + "&verifyCode=" + verifyCode,
		  dataType : "json",
		  success : function(e){
			  console.log(e.msg);
			  if (e.code != 0) {
				  dialog(e.msg);
				  return;
			  }
			  window.location.href = e.msg;
		  }
	  });
  }
</script>    
</body>
</html>