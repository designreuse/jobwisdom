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
  <script>
    jQuery(function(){
	    var count=jQuery('nav li').size();
		jQuery('nav ul').css('width',count*72);
	 })

  jQuery(function(){    
	jQuery('.swiper').click(function(){
	    jQuery('body').animate({left:'80%'})
	   });
    })
	
  jQuery(function(){ 
    jQuery('.name_shop:eq(0)').find('p img').css('transform','rotate(90deg)')  
	jQuery('.name_shop p').click(function(){
	     if(jQuery(this).parent().find('ul').css('display')=='none'){
		    jQuery(this).parent().find('ul').slideDown();
			jQuery(this).find('img').css('transform','rotate(90deg)')
		 }
		 else{
		    jQuery('.name_shop').find('ul').slideUp();
			jQuery('.name_shop p').find('img').css('transform','rotate(0deg)')
		 }
	   }); 
	   
	  jQuery('.name_shop ul li').click(function(){
	     jQuery(this).addClass('active').siblings('li').removeClass('active');
	   }); 
    })
	
	//第一个展开
	jQuery(function(){    
	   jQuery('.name_shop').eq(0).find('ul').show();
	   
    })
	//向左滑。关闭
   zepto(function(){    
	 zepto('html').swipeLeft(function(){
	     zepto('body').animate({left:'0%'})
	   }); 
    })
	
   </script>
<body style="overflow:hidden">
	<div class="order_left">
		<c:forEach items="${storeList }" var="store" varStatus="index">
			<div class="name_shop">
				<p>
					${store.storeName }<span><img src="<%=basePath%>images/mobile/member/right.png"></span>
				</p>
				<ul>
					<c:forEach items="${storeDepts[index.count-1] }" var="dept">
						<a href="<%=basePath%>memberCenter/view/orderAppointment/${session_key_store_account }/1?selectStoreId=${store.storeId }&selectDeptId=${dept.deptId }"><li>${dept.deptName }<span><img src="<%=basePath%>images/mobile/member/right.png"></span></li></a>
					</c:forEach>
				</ul>
			</div>
		</c:forEach>
	</div>
	<div class="order_right" style="position: relative">
		<div class="con">
			<header class="order_top">
				<div class="swiper">
					<img src="<%=basePath%>images/mobile/member/down_content.png">
				</div>
				<div class="header_select">
					<span>${storeInfo.storeName }</span><select onchange="changeDept(this.value)" name="dept"><c:forEach items="${deptInfos }" var="deptInfo"><option value="${deptInfo.deptId }">${deptInfo.deptName }</option></c:forEach></select>
				</div>
			</header>
			<nav>
				<c:forEach items="${serviceList }" var="servie" varStatus="index">
					<ul value="${servie.deptId }" class="clearfix order_ul" style="display: none">
						<c:forEach items="${serviceList[index.count-1].projectCategoryList }" var="projectCategory">
							<li onclick="changeCategory(this.value)" value="${projectCategory.categoryId }">${projectCategory.categoryName }</li>
						</c:forEach>
						<script>
							jQuery(".clearfix.order_ul").children("li").eq(0).addClass("active");
						</script>
					</ul>
				</c:forEach>
				<script>
					jQuery(".clearfix.order_ul").eq(0).show();
				</script>
			</nav>
			<c:forEach items="${serviceList }" var="servie" varStatus="index">
				<c:forEach items="${serviceList[index.count-1].projectCategoryList }" var="projectCategory">
					<div class="order_content" value="${projectCategory.categoryId }" style="display: none">
						<ul>
							<c:forEach items="${projectCategory.projectList }" var="project">
								<li class="clearfix">
									<div class="mall_good">
										<img src="<%=basePath%>images/mobile/member/mall_good.png">
									</div>
									<div class="mall_item">
										<p>${project.projectName }</p>
										<span>已服务:${project.salesCount }</span>
										<div class="level">
											<em><img src="<%=basePath%>images/mobile/member/level.png"></em><em><img src="<%=basePath%>images/mobile/member/level.png"></em><em><img src="<%=basePath%>images/mobile/member/level.png"></em><em><img src="<%=basePath%>images/mobile/member/level_.png"></em>
										</div>
									</div>
									<div class="order_price">
										<h2>¥ ${project.projectPrice }</h2>
										<span>预约立减${project.appointmentPrice }元</span>
									</div>
									<div class="special">预约</div>
								</li>
							</c:forEach>
						</ul>
					</div>
				</c:forEach>
			</c:forEach>
			<script>jQuery(".order_content").eq(0).show();</script>
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
var serviceList = ${js_serviceList};
var selectDeptId = '${selectDeptId}';
function changeDept(deptId){
	jQuery(".clearfix.order_ul").hide();
	jQuery(".clearfix.order_ul[value='"+deptId+"']").show();
	for (var i = 0; i < serviceList.length; i++) {
		if (serviceList[i].deptId == deptId){
			changeCategory(serviceList[i].projectCategoryList[0].categoryId);
		}
	}
}
function changeCategory(categoryId){
	jQuery(".order_content").hide();
	jQuery(".order_content[value='"+categoryId+"']").show();
}
jQuery(function(){
	jQuery("select[name='dept']").val(selectDeptId);
	changeDept(selectDeptId);
})
</script>
</html>