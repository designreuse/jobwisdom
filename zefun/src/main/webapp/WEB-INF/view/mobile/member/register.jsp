<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<html lang="en">
<link rel="stylesheet" href="<%=basePath%>css/mobile/style.css" />
<link rel="stylesheet" href="<%=basePath%>css/mobile/shop.css?date=<%=new Date()%>" />
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>会员注册</title>
<style>
.business_no input {
	width: 87%
}

.login_content>p img {
	left: 0
}

.login_imformation a {
	color: #52a4f1
}

.business_no input {
	height: 4rem;
	color: white;
	-webkit-border-radius: 5px;
	-webkit-appearance: none;
	appearance: none;
}

.business_no span img {
	top: 8px
}

.business_no span img {
	vertical-align: middle;
	width: 20px
}

.login_imformation button {
	height: 4rem
}

.login_imformation a {
	font-size: 14px
}

.business_no input {
	box-shadow: 0 0 0 white inset;
}

.con {
	height: 100%;
	overflow: hidden;
	width: 100%;
	background: url('<%=basePath%>images/mobile/member/login_back.jpg')
		no-repeat;
	background-size: 100% 100%;
	height: 100%
}
</style>
</head>
<body>
	<div class="con">
		<div class="login">
			<div class="login_content ">
				<p>
					<img src="<%=picPath %>${storeInfo.storeLogo }">
				</p>
				<div class="login_shop_name">${storeInfo.storeName }</div>
				<div class="login_imformation second" style="position: relative; top: 1rem;">
					<div class="business_no">
						<span><img src="<%=basePath%>images/mobile/member/phone.png">
						<input type="tel" id="phone" placeholder="输入手机号"></span>
					</div>
					<div class="business_no" style="margin: 1rem 0">
						<span><input id="verifyCode" type="tel" placeholder="输入六位数验证码" style="width: 60.5%; padding-left: 0.5rem">
							<input id="getDiv" type="button" value="领取验证码" style="width: 30%; margin-left: 5%; background: #4b596a; text-align: center; padding: 0; color: white"></span>
					</div>
					<button onclick="register();">绑定</button>
				</div>
			</div>
		</div>
	</div>
	<%@include file="../memberBase.jsp"%>
	<script type="text/javascript">
		$(function() {
			var height = $(document).height();
			$('body').css('height', height)

		})
		var windowHeight = $(window).height()
		$(".login-wrap").css({
			"height" : windowHeight
		})

		var storeId = "${storeId}";
		var number = 120;
		var timer = null;
		var time = $("#time");
		var isCode = false;
		function getVerifyCode() {
			var phone = $("#phone").val();
			var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
			if (!reg.test(phone)) {
				alert("请输入正确的手机号码!");
				isCode = true;
				return;
			}
			$.ajax({
				type : "POST",
				url : baseUrl + "memberCenter/action/getVerifyCode",
				data : "storeId=" + storeId + "&phone=" + $("#phone").val(),
				dataType : "json",
				success : function(e) {
					if (e.code != 0) {
						dialog(e.msg);
						return;
					}
				}
			});
		}
		function register() {
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
				data : "storeId=" + storeId + "&phone=" + phone
						+ "&verifyCode=" + verifyCode,
				dataType : "json",
				success : function(e) {
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

	<script>
		//动态设定高度
		$(function() {
			var height = $(document).height() + 30;
			$('.con').css('height', height)
		})

		jQuery(function() {
			jQuery('input[type="button"]').on('click', function() {
				getVerifyCode();
				if (isCode){
					isCode = false;
					return;
				}
				var time = 60;
				jQuery('input[type="button"]').val('60');
				var a = setInterval(function() {
					if (time > 1) {
						jQuery('input[type="button"]').val(time - 1);
						time--;
						jQuery('input[type="button"]').attr('disabled', 'true')

					} else {
						jQuery('input[type="button"]').val('重新发送');
						jQuery('input[type="button"]').attr('disabled', false);
						clearInterval(a)
					}
				}, 1000)
			})
		})
	</script>
</body>
</html>