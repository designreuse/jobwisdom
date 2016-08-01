<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta content="telephone=no" name="format-detection" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobile/shop.css">
<title>好友推荐</title>
</head>
<style>
html{font-family: 微软雅黑;}	 
.con{width:100%;overflow-x:hidden;background:url('<%=basePath %>images/mobile/member/integration.png') no-repeat;background-size:100% 100%}
.img_border_content{position:relative;height:18rem;transform:rotate(-5deg);-webkit-transform:rotate(-5deg);margin:1rem auto;border:3px solid white;width:20rem} 
.img_border{z-index:3;position:absolute;left:0;height:18rem;overflow:hidden;width: 20rem;}
.img_border img{width:100%;}
.img_border_{z-index:10;position:absolute;top:0;transform:rotate(3deg);-webkit-transform:rotate(3deg);height:18rem;border:3px solid white;width:20rem}
.ten{padding-top:5rem;color:#08a0e3;font-size:20px}
.shine{position:absolute;right:0;top:0}
.shine img{width:12em}
.ten_left{float:left;margin:1.5rem 0.5rem}
.ten_left img{width:4.5rem}
.ten_right{text-align:center;width: 20rem;margin:0 auto;font-weight:bold}
.ten_right p{text-align:center;transform:rotate(-5deg);-webkit-transform:rotate(-5deg)}
.ten_right img{width:16rem}
.span1{font-size:36px}
.span2{font-size:30px}
.span3{font-size:28px}
.span4{font-size:26px}
.span5{font-size:24px}
.span6{font-size:22px}
.span7{font-size:20px}
.span8{font-size:18px}
.span9{font-size:16px}
.span10{font-size:14px}
.span11{font-size:12px}
.span12{font-size:10px}
.swin{position:absolute;left:-5rem;bottom:-5rem;z-index:2}
.swin img{width:10rem;}
.iphone{position:absolute;right:-6rem;top:2rem;z-index:2}
.iphone img{width:10rem;}
.integration_header{z-index:30;transform:rotate(25deg);-webkit-transform:rotate(25deg);right:-4rem;top:1rem;position:absolute;background:rgba(201,216,218,0.8);height:4.8rem}
.integration_header_left{padding:0.5em 0.2rem 0.5rem 0.5rem;float:left;width:3.6rem;height:3.6rem;border-radius:2rem;-webkit-border-radius:2rem;overflow:hidden;}
.integration_header_left img{height:3.6rem;width:3.6rem;border-radius:2rem;-webkit-border-radius:2rem;}
.integration_header_right{float:left;margin-left:6px}
.integration_header_right p{color:black;margin-top:0.4rem;}
.integration_header_right p span{color:#e1471e;font-size:14px}
.integration_header em{float:left;display:inline-block;width: 0;height: 0; border-left: 0px solid transparent;border-right: 13px solid transparent;border-top: 25px solid rgba(201,216,218,0.8);position:relative; top: 4.5rem;right: 46%;}
.fly_left{float:left;position:absolute}
.fly_left img{width:7rem}
.ewm{margin-top:4rem;padding-top:1rem;float:left;width:100%;background:rgba(146,197,232,0.3);}
.ewm img{width:7rem}
.fly_right{float:left}
  
.img_border_ p{position:relative;left:40%;top:2px;width:20px;height:20px;border-radius:12px;background:#ffffff;text-align:center;}
.img_border_ p span{display:inline-block;width:60%;height:60%;background:#f5f5f1;border-radius:12px;position:relative;top:3px;box-shadow:0 2px 2px #ccc inset;-webkit-box-shadow:0 2px 2px #ccc inset}
.integration_text{padding-bottom:0.5rem;width:84%;background:rgba(202,223,231,0.6);margin:0 auto;position:relative;top:35%}
.integration_text .title p{float:left;margin:0 1rem;width:25px;height:25px;border-radius:12px;-webkit-border-radius:12px;background:#ffffff;text-align:center;}
.integration_text .title p span{display:inline-block;width:80%;height:80%;background:#fbcd8e;border-radius:12px;position:relative;top:2px;box-shadow:0 2px 2px #ccc inset}
.title{padding-top:1rem;line-height:2rem}
.integration_text>p{line-height:18px;padding-left:2rem;margin-bottom:0.5rem}
.saying{margin:1rem 0 0.2rem 0;padding-left:1rem}
.share{margin-top:1rem;margin-bottom:3rem;text-align:center}
.share button{-webkit-border-radius:5px;border-radius:5px;width:14rem;height:4rem;text-align:center;line-height:4rem;color:white;border-radius:5px;background:#e2721f;font-size:14px;border:none;box-shadow:0 2px 2px #9f9e93}
input{-webkit-appearance:none;appearance:none;}
.integration_text_input{height:15rem;overflow:hidden;width:80%;margin:7rem auto}
.integration_text_input input{font-size:14px;box-shadow:0px 0px 2px white;padding-left:6px;color:white;margin-bottom:1rem;width:97%;height:3.6rem;line-height:3.6rem;border-radius:4px;background:rgba(206,226,231,0.7)}
.integration_text_content{margin-top:2rem}

.zzc{position: fixed;width: 100%;height: 100%; background: rgba(71,67,55,0.8);z-index: 1000;}
.zzc img{width:85%;position:absolute;right:10%;top:18%}
.ewm_content{position:relative;text-align:center;margin:6rem auto;color:black;width:84%}
.ewm p{padding:1rem 0 2rem 0}
input[type=tel]{
	border: 1px solid #e3e3e3;
}
.hide {display: none;}
</style>
<body>
	<div class="zzc hide">
		<img src="<%=basePath %>images/mobile/member/zzc_img.png">
	</div>
	<div class="con">
		<div class="shine">
			<img src="<%=basePath %>images/mobile/member/shine.png">
		</div>
		
		<div class="ten clearfix">
			<div class="ten_left">
				<img src="<%=basePath %>images/mobile/member/hat.png">
			</div>
			<div class="ten_right">
				<p>
					<span class="span1">${rewardAmount }</span><span class="span2">${rewardUnit }</span>
					<c:forEach items="${rewardName }" var="rewardNames" varStatus="index">
						<span class="span${index.count+2 }">${rewardNames }</span>
					</c:forEach>
				</p>
				<img src="<%=basePath %>images/mobile/member/ten.png">
			</div>
		</div>
		
		<div class="img_border_content">
			<div class="integration_header clearfix">
				<div class="integration_header_left">
					<img src="<%=picPath%>${ownerInfo.headUrl}">
				</div>

				<div class="integration_header_right" id="shareText">
					<p>
						我在<span>${storeInfo.storeName }</span>
					</p>
					<p>
						做了<span>${projectInfo.projectName}<span>
					</p>
				</div>
				<em></em>
			</div>
			<div class="iphone">
				<img src="<%=basePath %>images/mobile/member/iphone.png">
			</div>
			<div class="img_border" >
				<img src="<%=picPath%>${projectShare.imgUrl}">
			</div>
			<div class="img_border_"></div>
			<div class="img_border_" style="transform: rotate(8deg); -webkit-transform: rotate(8deg);" onclick="showHairstyle();">
				<p>
					<span></span>
				</p>
			</div>
			<div class="swin">
				<img src="<%=basePath %>images/mobile/member/swin.png">
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
				<div class="saying">奖励说明</div>
				<p>1. 您的好友通过您分享的页面成功注册科获得10分商城积分</p>
				<p>2. 您分享任一笔项目消费订单可获得0积分商城积分</p>
				<p>3. 您推荐的好友首次消费时，每消费0元，您可以获得10元账户礼金</p>
			</div>

			<div class="share" onclick="window.location.href='<%=basePath%>memberCenter/view/orderList'">
				<button>立即去分享</button>
			</div>
		</div>
		
		<!--分享者-->
		<div class="integration_text_content hide" id="ownerMember">
			<div class="integration_text">
				<div class="title clearfix">
					<p>
						<span></span>
					</p>
					恭喜您，您通过分享，拉取到了${recommendCount}个粉丝了，再接再厉哦
				</div>
				<div class="saying">奖励说明</div>
				<p>1. 您的好友通过您分享的页面成功注册科获得10分商城积分</p>
				<p>2. 您分享任一笔项目消费订单可获得0积分商城积分</p>
				<p>3. 您推荐的好友首次消费时，每消费0元，您可以获得10元账户礼金</p>
			</div>

			<div class="share" onclick="$('.zzc').fadeIn();">
				<button >继续分享</button>
			</div>
		</div>
		
		<!--新会员领取奖励-->
		<div class="integration_text_input hide" id="newMember">
			<input type="tel" id="phone" value="输入手机号" class="phone">
			<input type="tel" id="verifyCode" style="width: 60.8%" value="输入验证码" class="code">
			<input type="button" value="获取验证码" style="background: #359ded; width: 34%; border: none;">
			<div class="share" style="margin: 0;">
				<button style="width: 100%" onclick="getReward()">点击领取</button>
			</div>
		</div>
		<!--新会员领取奖励-->
		<div class="ewm_content clearfix hide" id="subscribe">
			<div class="fly_left" style="left: -2rem">
				<img src="<%=basePath %>images/mobile/member/fly_left.png">
			</div>
			<div class="ewm">
				<img src="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=${ticket }">
				<p>奖励已放置您的账户，长按二维码关注查看～～</p>
			</div>
			<div class="fly_left" style="right: -2rem">
				<img src="<%=basePath %>images/mobile/member/fly_right.png">
			</div>
		</div>
		
	</div>
	
	<%@include file="../wechatBase.jsp"%>
	<%@include file="../memberBase.jsp"%>
	<script type="text/javascript">
	//分享者
	if ('${isOwner}'){
		$("#ownerMember").removeClass("hide");
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
	
	var storeName = "${storeInfo.storeName }";
	var projectName = "${projectInfo.projectName}";

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

	var isCode = false;
	function getVerifyCode() {
		var phone = $("#phone").val();
		var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
		if (!reg.test(phone)) {
			alert("请输入正确的手机号码!");
			isCode = false;
			return;
		}else {
			isCode = true;
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
				$("#newMember").fadeOut();
				$("#subscribe").fadeIn();
			}
		});
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
			if (!isCode){
				return;
			}
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

	//点击遮罩层
	$(function() {
		$('.zzc').on('touchstart', function() {
			$(this).fadeOut();
		})
	})
</script>
<script>
	//处理软键盘挡住输入框
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