<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>会员中心</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobile/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobile/shop.css">
</head>
<body>

	<div class="con">

		<div class="person_">
			<%-- <a href="<%=basePath %>memberCenter/view/levelInfo"><span class="vip">${memberBaseInfo.levelName }</span></a> --%>
			<a href="<%=basePath %>memberCenter/view/levelInfo"><span class="vip">卡</span></a>
			<div class="head_pic">
			<a href="<%=basePath%>memberCenter/view/info">
				<img src="<%=picPath%>${memberBaseInfo.headUrl }?imageView2/1/w/220/h/220">
			</a>
			</div>
			<ul class="clearfix person_coin">
				<a href="<%=basePath%>memberCenter/view/integralFlow">
				<li>剩余积分
					<p>${memberBaseInfo.balanceIntegral }</p>
				</li>
				</a>
				<a href="<%=basePath%>memberCenter/view/giftmoneyFlow">
				<li>礼金金额
					<p>${memberBaseInfo.giftmoneyAmount }</p>
				</li>
				</a>
				<a href="<%=basePath%>memberCenter/view/memberCoupon">
				<li>优惠卷数
					<p>${memberBaseInfo.couponCount }</p>
				</li>
				</a>
				<a href="<%=basePath%>memberCenter/view/cardmoneyFlow">
				<li>储值余额
					<p>${memberBaseInfo.balanceAmount }</p>
				</li>
				</a>
			</ul>

		</div>

		<ul class="person_ul">
			<a href="<%=basePath%>memberCenter/view/orderList"><li><img src="<%=basePath%>images/mobile/member/person1.png">订单</li></a>
			<a href="<%=basePath%>memberCenter/view/appointmentList/${session_key_store_account}/1"><li><img src="<%=basePath%>images/mobile/member/person2.png">预约</li></a>
			<a href="<%=basePath%>memberCenter/view/comboList"><li><img src="<%=basePath%>images/mobile/member/person3.png">疗程</li></a>
			<a href="<%=basePath%>memberCenter/view/integralFlow"><li><img src="<%=basePath%>images/mobile/member/person4.png">积分</li></a>
			<a href="<%=basePath%>memberCenter/view/memberCoupon"><li><img src="<%=basePath%>images/mobile/member/person5.png">优惠券</li></a>
			<%-- a href="<%=basePath%>uboxMall/view/orderList/${session_key_store_id}"><li><img src="<%=basePath%>images/mobile/member/person6.png">购物清单</li></a> --%>
			<li><img src="<%=basePath%>images/mobile/member/person7.png">储值卡</li>
			<a href="<%=basePath%>memberCenter/view/info"><li><img src="<%=basePath%>images/mobile/member/person8.png">个人设置</li></a>

		</ul>
	</div>

 <ul class="bottom_fix clearfix">
 		<a href="<%=basePath %>memberCenter/view/home/${session_key_store_account}/1">
	      <li><img src="<%=basePath %>images/mobile/member/botton_1.png">
		      <p>我的</p>
		  </li>
	    </a>
	    <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_account}/1">
	    	<li><img src="<%=basePath %>images/mobile/member/botton_2.png">
		       <p>预约</p>
		    </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_account}/1">
		  <li><img src="<%=basePath %>images/mobile/member/botton_3.png">
		    <p>商城</p>
		  </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_account}/1">
	     <li><img src="<%=basePath %>images/mobile/member/botton_4.png">
		     <p>门店</p>
		  </li>
	    </a>
 </ul>
	
</body>
</html>