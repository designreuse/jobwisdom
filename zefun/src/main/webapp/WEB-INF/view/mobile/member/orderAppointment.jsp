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
.order_top {
	background: #009ee6
}

.order_shop_name {
	width: 15rem;
	height: 2.8rem;
	background: white;
	margin: 0 auto;
	font-size: 16px;
	position: relative;
	top: 0.7rem;
	text-align: center;
	line-height: 2.8rem
}

.order_shop_name em {
	font-size: 10px;
	display: inline-block;
	margin-left: 4px
}

.order_content_top img {
	width: 50px;
	float: left;
	position: relative;
	top: 1.4rem;
	margin: 0 0.5rem;
	border-radius: 25px;
}

.order_content ul li {
	float: left;
	width: 45%;
	border: 1px solid #dfdfdf;
	padding: 0 !important;
	margin: 2%
}

.order_content_top {
	height: 25%;
	background: #3ec4e6;
	margin-bottom: 1.4rem
}

.order_content_top_name p {
	height: 2rem;
	line-height: 2rem
}

.order_right_ {
	float: right;
	width: 2.8rem;
	height: 1.7rem;
	background: #98da2f;
	border-radius: 6px;
	color: white;
	font-size: 12px;
	text-align: center;
	line-height: 1.7rem;
	position: relative;
	left: -0.5rem;
	top: -3rem
}

.order_content_bottom span {
	display: inline-block;
	width: 48%;
	text-align: center;

}
.order_content_bottom p{
padding: 0;
}

.order_content_bottom span img {
	width: 16px
}

.order_content_bottom {
	margin-top: 2rem
}

.bottom_fix .active {
	color: #3ec4e6
}

.order_left {
	background: linear-gradient(to top, #1782d3, #57c8ed) !important;
}

.left_title {
	color: white;
	font-size: 14px
}

.left_title img {
	width: 20px;
	margin-right: 2px
}

.address_pic img {
	width: 44px;
	margin-right: 5px;
	border-radius:22px 
}

.address_pic {
	float: left
}

.position {
	float: left
}

.name_shop ul {
	height: 50px;
	margin-top: 1.2rem
}

.name_shop ul li img {
	position: relative;
	top: 0;
	right: 0
}

.name_shop ul li {
	line-height: 1.7rem;
	color: #caebf8;
	border-bottom: 1px solid #caebf8;
	margin-bottom: 0.5rem
}

.position p {
	font-size: 14px
}

.name_shop ul .active1 {
	background: #13abdc
}
.tab .active, .tab ul .active{box-shadow:0 0 0rem white inset;border-radius:.5rem}
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
<body style="overflow: hidden">

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
		   ${storeInfo.storeName }<em>${storeInfo.storeProvince }</em>
		</div>
	 </header> 
      <div class="order_content">
        <ul class="clearfix">
        	<c:forEach items="${employeeInfos }" var="employeeInfo">
			  <li>
			    <div class="order_content_top">
			       <img src="<%=qiniuPath%>${employeeInfo.headImage }">
	               <div class="order_content_top_name">
	                 <p style="color:white;font-size:14px;position:relative;top:2rem">${employeeInfo.name }</p>
	                 <p style="position:relative;top:2rem">${employeeInfo.levelName }</p>
	              </div>
	              <a href="<%=basePath%>memberCenter/view/dateAppointment?employeeId=${employeeInfo.employeeId }">
	              <div class="order_right_">
	                   		 预约
	              </div>
	              </a>		  
				</div>
				 <div class="order_content_bottom">
			        <span><p><img src="<%=basePath%>images/mobile/member/love.png">好评量</p>${employeeInfo.serviceScore }</span>	
	    			<span><p><img src="<%=basePath%>images/mobile/member/sample.png">作品级</p>1111</span>
				</div>	
			  </li>
		    </c:forEach>
        </ul>
      </div>	  
             
   </div>

	<ul class="bottom_fix clearfix">
		<a href="<%=basePath %>memberCenter/view/home/${session_key_store_account}/1">
			<li><img src="<%=basePath%>images/mobile/member/botton_1_1.png">
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
</body>
<script type="text/javascript">
	var memberId = '${memberId }';
	/* var serviceList = ${js_serviceList};
	 var selectDeptId = '${selectDeptId}';
	 function changeDept(deptId){
	 jQuery(".clearfix.order_ul").hide();
	 jQuery(".clearfix.order_ul[value='"+deptId+"']").show();
	 for (var i = 0; i < serviceList.length; i++) {
	 if (serviceList[i].deptId == deptId){
	 changeCategory(serviceList[i].projectCategoryList[0].categoryId, null);
	 }
	 }
	 }
	 function changeCategory(categoryId, li){
	 if (li!=null){
	 jQuery(li).siblings().removeClass("active");
	 jQuery(li).addClass("active");
	 }
	 jQuery(".order_content").hide();
	 jQuery(".order_content[value='"+categoryId+"']").show();
	 }
	 jQuery(function(){
	 jQuery("select[name='dept']").val(selectDeptId);
	 changeDept(selectDeptId);
	 }) */
</script>
</html>