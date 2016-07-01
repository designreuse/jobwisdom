<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>我的优惠券</title>
<link rel="stylesheet" href="<%=basePath%>css/mobile/shop.css?date=<%=new Date() %>" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobile/style.css">
</head>
<body>
	<div class="con">
		<ul class="clearfix coupon">
			<a href="<%=basePath%>memberCenter/view/storeCoupon/${session_key_store_account}/1"><li style="border-right: 1px solid #ccc"><span style="background: #ea631a;">兑换优惠券</span></li></a>
			<li><span style="background: #f69731">推荐有奖</span></li>
		</ul>
		<div class="noCoupon">
			<img src="<%=basePath%>images/mobile/member/myCoupon.png">
			<div class="noCoupon_text">
				<p>抱歉，您还没有优惠卷</p>
				<p>赶紧去兑换吧</p>
				<a href="">立即兑换&gt;</a>
			</div>
		</div>
		<div style="overflow: overlay;height: 500px">
		<c:forEach items="${couponList }" var="coupon">
			<div class="coupon_content">
				<div class="coupon_content_detail clearfix" style="background: #${coupon.couponColour }">
					<div class="coupon_content_detail_left">
						<p>券</p>
						<div class="coupon_price">
							<span>¥<i>${coupon.couponPrice }</i><em>${coupon.couponName }</em></span>
						</div>
						<div class="time_out">到期时间${coupon.stopTime }</div>
					</div>
					<div class="coupon_content_detail_right">限制条件</div>
				</div>
			</div>
		</c:forEach>
		<p class="past">去积分商城吧</p>
		</div>
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