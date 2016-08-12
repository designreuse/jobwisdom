<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>预约服务</title>
<link rel="stylesheet" href="<%=basePath%>css/mobile/shop.css" />
<link rel="stylesheet" href="<%=basePath%>css/mobile/style.css" />
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/zepto.min.js"></script>

</head>
<style>
  em,i{font-style:normal}
  .order_top{background:#584489}
  .order_shop_name{border-radius:8px;color:black;width:15rem;height:2.8rem;background:white;margin:0 auto;font-size:16px;position:relative;top:0.7rem;text-align:center;line-height:2.8rem}
  .order_shop_name em{font-size:10px;display:inline-block;margin-left:4px}
  .order_content_top img{width:50px;}
  .order_content ul li {box-shadow:0 0 2px #dfdfdf;overflow:hidden;width:90%;height:6rem;background:white;border-radius:8px;padding:0!important;margin:1.5rem auto;}
  .order_content_top{height:50%;background:#bcb2d3;margin-bottom:1.4rem}
  .order_content_top .order_img{position:relative;top:0.8rem;display:inline-block;margin:0 0.8rem;float:left}
  
  .order_right_{float:right;width:2.8rem;height:1.7rem;background:#98da2f;border-radius:6px;color:white;font-size:12px;text-align:center;line-height:1.7rem;position:relative;left:-0.5rem;top:-3rem}
  .order_content_bottom span{display:inline-block;width:48%;text-align:center}
  .order_content_bottom span img{width:16px}
  .order_content_bottom{margin-top:2rem}
 
  .left_title{color:white;font-size:16px;padding-left:2rem}
  .left_title img{width:22px;margin-right:5px}
  .address_pic img{width:44px;margin-right:5px}
  .address_pic{float:left}
  .position{float:left}
  .name_shop ul{height:50px;margin-top:1.2rem}
  .name_shop ul li img{position:relative;top:0;right:0}
  .name_shop ul li{line-height:1.7rem;color:#b0a7c5;margin-bottom:0.5rem;padding-top:8px;}
  .position p{font-size:14px}
  
  
  
  .order_name{float:left;color:white;position:relative;}
  .order_name p{font-size:14px;}
  .order_shape{display:inline-block;background:url('<%=basePath%>images/mobile/member/order.png') no-repeat;height:4rem;width:4rem;float:right;background-size:4rem;color:white;font-size:14px;text-align:center;line-height:3rem;margin:1.5rem 1.4rem 0 0 }
  .assess{width:65%;margin:0 auto}
  .assess img{width:20px}
  .assess span{display:inline-block;}
  .assess_num{float:left;color:#eb6227}
  .works{float:right;color:#908d8b}
  .works img{position:relative;top:-1px}
  .branch_store{width:96%;margin:0 auto;height:100%;border-bottom: 1px solid #ded9e9;}
  .branch_store .position{color:#e5e1ee}
  </style>
<script>
	jQuery(function(){
		jQuery(".order_content ul").css("height", jQuery(document).height()-72);
	})
	jQuery(function() {
		var count = jQuery('nav li').size();
		jQuery('nav ul').css('width', count * 72);
	})

	jQuery(function() {
		jQuery('.swiper').click(function() {
			jQuery('body').animate({
				left : '80%'
			})
		});
	})

	//第一个展开
	jQuery(function() {
		jQuery('.name_shop').eq(0).find('ul').show();
	})
	//向左滑。关闭
	zepto(function() {
		zepto('html').swipeLeft(function() {
			zepto('body').animate({
				left : '0%'
			})
		});
	})
	//向右滑。
	zepto(function() {
		zepto('html').swipeRight(function() {
			zepto('body').animate({
				left : '80%'
			})
		});
	})
</script>
<body style="">

	<%-- <c:forEach items="${employeeInfos }" var="employeeInfo">
		<a href="<%=basePath%>memberCenter/view/dateAppointment?employeeId=${employeeInfo.employeeId }"><h3>${employeeInfo.name }${employeeInfo.employeeId }</h3></a>
	</c:forEach> --%>
	<div class="order_left">
      <div class="left_title"><img src="<%=basePath%>images/mobile/member/order_business.png">${enterpriseInfoDto.enterpriseName }</div>
      <div class="name_shop">
	   <ul>
	   	<c:forEach items="${storeList }" var="store">
	   	 <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_account }/1?selectStoreId=${store.storeId}">
	     <li class="clearfix active1">
     	   <div class="address_pic"><img src="<%=qiniuPath%>${store.storeLogo}"></div>
	       <div class="position">
               <p>${store.storeName }</p>
               <span><img src="<%=basePath%>images/mobile/member/position.png">${store.storeAddress }</span>
           </div>		   	   
		 </li> 
		 </a>
		</c:forEach>
	   </ul>
	  </div>
   </div>
   
   <div class="order_right" style="position:relative">
   	<div class="con">
	 <header class="order_top">
	    <div class="swiper">
		  <img src="<%=basePath%>images/mobile/member/down_content_.png">
		</div>
	    <div class="order_shop_name">
		   ${storeInfo.storeName }
		</div>
	 </header> 
      <div class="order_content">
        <ul class="clearfix">
	      <c:forEach items="${employeeInfos }" var="employeeInfo">
			  <li>
			    <div class="order_content_top clearfix">
	                <span class="order_img"><img src="<%=qiniuPath%>${employeeInfo.headImage }"></span>
					<div class="order_name">
					  <p>${employeeInfo.name }</p>
					  <i>${employeeInfo.levelName }</i> 
					</div>
					<a href="<%=basePath%>memberCenter/view/dateAppointment?employeeId=${employeeInfo.employeeId }">
					<span class="order_shape">
					   预约
					</span>
					</a>
	            </div>
	            <div class="assess clearfix">
				   <span class="assess_num"><img src="<%=basePath%>images/mobile/member/love.png"><em>好评量${employeeInfo.serviceScore }</em></span>
				   <span class="works"><img src="<%=basePath%>images/mobile/member/sample.png"><em>作品集1111</em></span>
				</div>
			  </li>
		  </c:forEach>
        </ul>
      </div>	  
             
   </div>
   
<div class="bottom_fix_content">
	<ul class="bottom_fix clearfix">
		<a href="<%=basePath %>memberCenter/view/home/${session_key_store_account}/1">
			<li><img src="<%=basePath%>images/mobile/member/botton_1.png">
				<p style="top: -2rem; font-size: 0.65em; font-family: '微软雅黑'; color: #555">我的</p></li>
		</a>
		<a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_account}/1">
			<li><img src="<%=basePath%>images/mobile/member/botton_2_2.png">
				<p style="top: -2rem; font-size: 0.65em; font-family: '微软雅黑'; color: #555">预约</p></li>
		</a>
		<a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_account}/1">
			<li><img src="<%=basePath%>images/mobile/member/botton_3.png">
				<p style="top: -2rem; font-size: 0.65em; font-family: '微软雅黑'; color: #555">商城</p></li>
		</a>
		<a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_account}/1">
			<li><img src="<%=basePath%>images/mobile/member/botton_4.png">
				<p style="top: -2rem; font-size: 0.65em; font-family: '微软雅黑'; color: #555">门店</p></li>
		</a>
	</ul>
</div>
</div>


</body>
<script type="text/javascript">
	var memberId = '${memberId }';
</script>
</html>