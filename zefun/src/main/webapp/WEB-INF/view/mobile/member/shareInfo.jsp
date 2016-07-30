<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>好友推荐</title>
</head>
<style>
html {
	font-family: 微软雅黑;
}

.con {
	background: url('<%=basePath%>images/mobile/member/integration.jpg')
		no-repeat;
	background-size: 100% 100%
}

.img_border_content {
	position: relative;
	height: 13rem;
	transform: rotate(-5deg);
	position: relative;
	top: 24%;
	left: 22%;
	border: 3px solid white;
	width: 15rem
}

.img_border {
	position: absolute;
	left: 0;
	height: 13rem;
	overflow: hidden
}

.img_border img {
	width: 100%
}

.img_border_ {
	position: absolute;
	top: 0;
	transform: rotate(3deg);
	height: 13rem;
	border: 3px solid white;
	width: 15rem
}

@media screen and (max-width: 320px) {
	.ten {
		font-size: 46px;
		position: absolute;
		left: 22%;
		top: 8%;
		color: #1ca7e4;
		font-weight: bold
	}
}

@media screen and (max-height:480px) {
	.ten {
		font-size: 46px;
		position: absolute;
		left: 22%;
		top: 6%;
		color: #1ca7e4;
		font-weight: bold
	}
}

@media screen and (min-width:321px) and (max-width: 375px) {
	.ten {
		font-size: 48px;
		position: absolute;
		left: 23%;
		top: 8.5%;
		color: #1ca7e4;
		font-weight: bold
	}
}

@media screen and (min-width:376px) {
	.ten {
		font-size: 52px;
		position: absolute;
		left: 25%;
		top: 9.5%;
		color: #1ca7e4;
		font-weight: bold
	}
}

.img_border_ p {
	position: relative;
	left: 40%;
	top: 2px;
	width: 20px;
	height: 20px;
	border-radius: 12px;
	background: #ffffff;
	text-align: center;
}

.img_border_ p span {
	display: inline-block;
	width: 65%;
	height: 65%;
	background: #f5f5f1;
	border-radius: 12px;
	position: relative;
	top: 3px;
	box-shadow: 0 2px 2px #ccc inset
}

.integration_text {
	width: 84%;
	height: 12rem;
	background: rgba(202, 223, 231, 0.6);
	margin: 0 auto;
	position: relative;
	top: 35%
}

.integration_text .title p {
	float: left;
	margin: 0 1rem;
	width: 25px;
	height: 25px;
	border-radius: 12px;
	background: #ffffff;
	text-align: center;
}

.integration_text .title p span {
	display: inline-block;
	width: 80%;
	height: 80%;
	background: #fbcd8e;
	border-radius: 12px;
	position: relative;
	top: 2px;
	box-shadow: 0 2px 2px #ccc inset
}

.title {
	padding-top: 1rem;
	line-height: 2rem
}

.integration_text>p {
	line-height: 2rem;
	padding-left: 2rem
}

.saying {
	margin: 1rem 0 0.2rem 0;
	padding-left: 1rem
}

.share {
	margin-top: 1rem;
	margin-bottom: 3rem;
	text-align: center
}

.share button {
	width: 14rem;
	height: 3rem;
	text-align: center;
	line-height: 3rem;
	color: white;
	border-radius: 5px;
	background: #e2721f;
	font-size: 14px;
	border: none;
	box-shadow: 0 2px 2px #9f9e93
}

.integration_text_input {
	height: 12rem;
	overflow-y: hidden;
	position: relative;
	top: 35%;
	width: 80%;
	margin-left: 10%
}

.integration_text_input input {
	font-size: 14px;
	box-shadow: 0px 0px 2px white;
	padding-left: 6px;
	color: white;
	margin-bottom: 1rem;
	width: 97%;
	height: 2.8rem;
	line-height: 2.8rem;
	border-radius: 4px;
	background: rgba(206, 226, 231, 0.7)
}

.integration_text_content {
	margin-top: 54%
}

.zzc {
	position: fixed;
	width: 100%;
	height: 100%;
	background: rgba(71, 67, 55, 0.8);
	z-index: 1000;
}

.zzc img {
	width: 85%;
	position: absolute;
	right: 10%;
	top: 18%
}

.ewm_content {
	text-align: center;
	position: relative;
	top: 30%
}

.ewm_content img {
	width: 50%;
	margin-bottom: 0.5rem
}

.ewm_content {
	font-size: 16px
}
.hide {
	display: none
}
</style>
<body>

	<div class="zzc hide">
		<img src="<%=basePath %>images/mobile/member/zzc_img.png">
	</div>
	<div class="con">
		<div class="ten">${rewardAmount }</div>
		<div class="img_border_content">
			<div class="img_border">
				<img src="<%=picPath%>${projectShare.imgUrl}" >
			</div>
			<div class="img_border_"></div>
			<div class="img_border_" style="transform: rotate(8deg);" onclick="showHairstyle();">
				<p>
					<span></span>
				</p>
			</div>
		</div>
		<!--老会员-->
		<div class="integration_text_content hide" id="oldMember">
			<div class="integration_text">
				<div class="title clearfix">
					<p>
						<span></span>
					</p>
					没想到您已经是会员了。
				</div>
				<c:choose>
			  		<c:when test="${empty rewardName }">
			  			<div class="saying">奖励说明</div>
			  			<p>店家暂未设置任何奖励，感谢您的支持。</p>
			  		</c:when>
			  		<c:otherwise>
			  			<div class="saying">奖励说明</div>
			  			<p>1.您的好友通过您分享的页面成功注册可获得${rewardAmount }${rewardUnit }${rewardName }</p>
			  			<p>2.您分享任一笔项目消费订单可获得${shareDesc }</p>
			  			<p>3.${firstConsumeDesc }</p>
			  		</c:otherwise>
			  	</c:choose>
			</div>
			<div class="share">
				<button onclick="window.location.href='<%=basePath%>memberCenter/view/orderList'">马上去分享</button>
			</div>
		</div>
		
		<!--分享者-->
		<div class="integration_text_content hide" id="ownerMember">
			<div class="integration_text">
				<div class="title clearfix">
					<p>
						<span></span>
					</p>
				</div>
				<div class="saying" id="recommendText"></div>
			</div>

			<div class="share">
				<button class="s-modal-control">分享</button>
			</div>
		</div>
		
		<!-- 领取奖励 -->
		<div class="integration_text_input hide" id="newMember">
			<input type="text" id="phone" value="输入手机号" class="phone">
			<input type="text" id="verifyCode" style="width: 60.8%" value="输入验证码" class="code">
			<input type="button" value="获取验证码" style="background: #359ded; width: 34%; border: none; margin-left: 2%">
			<div class="share" style="margin: 0;">
				<button style="width: 100%" onclick="getReward()">点击领取</button>
			</div>
		</div>
		
		<!--新会员领取奖励-->
		<div class="ewm_content hide" id="subscribe">
			<img src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${ticket }">
			<p>奖励已放置您的账户，长按二维码关注查看～～</p>
		</div>
		
	</div>
	<%@include file="../wechatBase.jsp"%>
	<%@include file="../memberBase.jsp"%>
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
	$(".zzc").removeClass("hide");
});
$(".s-modal-miss").click(function(){
	$(".zzc").addClass("hide");
});

var storeName = "${storeInfo.storeName }";
var projectName = "${projectInfo.projectName}";
var contentArray = ['我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">' + storeName + '</a> 做了个 <b>' + projectName + '</b>，提前预约不等待，特别推荐给惜时如金的你！',
                    '我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">' + storeName + '</a> 做了个 <b>' + projectName + '</b>，老板剪起头发来，舒服得我不要不要的！',
                    '我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">' + storeName + '</a> 做了个 <b>' + projectName + '</b>，理发帅哥随便挑，温柔细心活还好！',
                    '我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">' + storeName + '</a> 做了个 <b>' + projectName + '</b>，洗头小妹个个靓，聊天按摩不在话下！',
                    '我在 <a href="<%=basePath%>memberCenter/view/storeInfo/${mainStoreId}/1">'
						+ storeName + '</a> 做了个 <b>' + projectName
						+ '</b>，半价优惠还送券，老板简直不赚钱！']

		$("#shareText").html(contentArray[Math.floor((Math.random() * contentArray.length))]);

		var imgUrl = picUrl + "${projectShare.imgUrl}";
		var titleArray = ['之前只能算是剃头，在这家美发店，我终于做了一回“造型”！',
				'理了二十年的发，在这里终于体会到什么叫做“造型”！', '推荐一家省时间的理发店，提前预约不排队，分享发型还送券！'];

		var title = titleArray[Math.floor((Math.random() * titleArray.length))];
		var desc = '';
		var code = "${code}";
		var link = baseUrl
				+ 'memberCenter/view/shareInfo?code='
				+ code
				+ '&orderId=${orderId}&mainStoreId=${session_key_store_account}';

		wx.ready(function() {
			wx.onMenuShareAppMessage({
				title : title,
				desc : desc,
				link : link,
				imgUrl : imgUrl
			});

			wx.onMenuShareTimeline({
				title : title,
				link : link,
				imgUrl : imgUrl
			});
		});

		function showHairstyle() {
			wx.previewImage({
				current : imgUrl, // 当前显示图片的http链接
				urls : [imgUrl]
			    // 需要预览的图片http链接列表
			});
		}

		var storeId = "${storeInfo.storeId}";
		var number = 120;
		var timer = null;
		var time = $("#time");
		function clock() {
			number--;
			if (number <= 0) {
				$("#timeDiv").addClass("hide");
				$("#getDiv").removeClass("hide");
				number = 120;
				window.clearInterval(timer);
			} else {
				time.html(number + '"');
			}
		}

		function getVerifyCode() {
			var phone = $("#phone").val();
			var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
			if (!reg.test(phone)) {
				dialog("请输入正确的手机号码!");
				return;
			}

			$("#getDiv").addClass("hide");
			$("#timeDiv").removeClass("hide");
			timer = setInterval("clock()", 1000);
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
		function getReward() {
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
				data : "code=" + code + "&storeId=" + storeId + "&phone="
						+ phone + "&verifyCode=" + verifyCode,
				dataType : "json",
				success : function(e) {
					if (e.code != 0) {
						dialog(e.msg);
						return;
					}
					$("#newMember").addClass('hide');
					$("#subscribe").removeClass('hide');
				}
			});
		}

		function getRecommendText(recommendCount) {
			var text = "恭喜您，已成功邀请了 <span class='red'>" + recommendCount
					+ "</span> 位好闺蜜。";
			if (recommendCount == 0) {
				text = "您目前还未成功邀请好友注册，别气馁。继续分享给您的朋友们！";
			} else if (recommendCount > 0 && recommendCount < 6) {
				text += "看来人气还不够旺哦，继续分享吧！";
			} else if (recommendCount > 5 && recommendCount < 16) {
				text += "您的新发型已经得到不少好友的肯定！继续分享吧！";
			} else if (recommendCount > 15 && recommendCount < 31) {
				text += "您在好闺蜜当中已经树立起一定的威信，继续分享不要停！";
			} else if (recommendCount > 30 && recommendCount < 51) {
				text += "原来您就是深藏不露的人气王！请收下我的膝盖！";
			} else {
				text += "您在朋友圈已经达到呼风唤雨的地步，在下跪服！";
			}
			return text;
		}

		//动态设定高度
		$(function() {
			var height = $(document).height();
			$('.con').css('height', height)

		})

		//获取焦点失去焦点	
		$.fn.placeholder = function() {
			var $obj = this;
			var v = $(this).val();
			$obj.focus(function(event) {
				if ($obj.val() == v) {
					$obj.val("");
				}
			});
			$obj.blur(function(event) {
				if ($obj.val() == "") {
					$obj.val(v);
				}
			});
		}

		$(function() {
			$('.phone').placeholder();
			$('.code').placeholder();
		})

		//倒计时
		$(function() {
			$('input[type="button"]').on('click', function(e) {
				getVerifyCode();
				var time = 60;
				$('input[type="button"]').val('60');
				var a = setInterval(function() {
					if (time > 1) {
						$('input[type="button"]').attr('disabled', 'disabled')
						$('input[type="button"]').val(time - 1);
						time--;
					} else {
						$('input[type="button"]').val('重新发送');
						$('input[type="button"]').attr('disabled', false);
						clearInterval(a)
					}
				}, 1000)
			})
		})
		if (/Android/gi.test(navigator.userAgent)) {
			window.addEventListener('resize', function() {
				if (document.activeElement.tagName == 'INPUT'
						|| document.activeElement.tagName == 'TEXTAREA') {
					window.setTimeout(function() {
						document.activeElement.scrollIntoViewIfNeeded();
					}, 0);
				}
			})
		}
	</script>
</body>
</html>