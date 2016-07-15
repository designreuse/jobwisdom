<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>排行</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/app.css" />
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/idangerous.swiper.css">
    <style>
		 .time_rank ul{height:3rem}
		 .time_rank li{height:3rem;line-height:3rem}
		 .time_rank{height:5rem;line-height:5rem}
		
		header{background:white}
		.swiper-slide .title{color:red}
		.rank_content{background:white}
		.swiper-container1{border-bottom:1px solid #f2f2f2}
		.rank_detail img{width:48px}
		.rank_detail i img{width:1rem}
		.point_data{width:100%}
		.point_data li{float:left;width:33.3%;height:5rem;text-align:center;padding:0.5rem 0;border-bottom:1px solid #e0dfdf}
		.point_data li p{color:#52a7e8;margin:0.3rem 0}
		.point_data li span{color:#e25c1a}
		.swiper-slide,.swiper-wrapper,.swiper-slide{height:auto!important}
		.bar{top:42px}
		.swiper-container1{height:42px}
		.swiper-slide .title{line-height:42px}
	</style>
</head>
<body>
<div class="con"> 
	    <div class="time_rank clearfix">
	      <ul class="clearfix"> 
		   <li <c:if test="${chooseType == 1 }">class="active"</c:if> onclick="changeShow(this, 1)">日排行</li>
		   <li <c:if test="${chooseType == 2 }">class="active"</c:if> onclick="changeShow(this, 2)">周排行</li>
		   <li <c:if test="${chooseType == 3 }">class="active"</c:if> onclick="changeShow(this, 3)">月排行</li>
		  </ul> 
		</div>
		<header>
			<div class="swiper-container1">
				<div class="swiper-wrapper">
					<div class="swiper-slide mycolor-slide" id="slide0">
						<div class="title cuurent" name="title">
							劳动业绩
						</div>
					</div>
					<div class="swiper-slide mycolor-slide" id="slide1">
						<div class="title" name="title">
							指定业绩
						</div>
					</div>
					<div class="swiper-slide mycolor-slide" id="slide2">
						<div class="title" name="title">
							指定比例
						</div>
					</div>
					<div class="swiper-slide mycolor-slide" id="slide3">
						<div class="title cuurent" name="title">
							套餐业绩
						</div>
					</div>
					<div class="swiper-slide mycolor-slide" id="slide4">
						<div class="title" name="title">
							商品业绩
						</div>
					</div>
					<div class="swiper-slide mycolor-slide" id="slide5">
						<div class="title" name="title">
							卡项业绩
						</div>
					</div>

					<span class="bar" style="left: 2px; width: 64px;background-color: red;"></span>
				</div>
				<div class="pagination" style="display: none;"></div>
			</div>
		</header>
		
		<div class="swiper-container">
			<div class="swiper-wrapper">
				<div class="swiper-slide">
					<div id="wapper0">
						<div id="scroll0">
						    <c:forEach items="${hashMap.dataList}" var="toDay" varStatus="toDayStatus">
						         <div class="uc-t ubb ub b-gra t-bla ub-ac lis clearfix">
									<div class="rank_content">
									   <div class="rank_detail">
										  <span class="num">${toDayStatus.index + 1}</span>
										  <img src="<%=picPath%>${toDay.headImage}">
										  <em>${toDay.name}</em>
										  <em>${toDay.employeeCode}</em>
										  <i>劳动业绩：<span>¥${toDay.projectValue}</span>
										  <img src="<%=basePath%>images/mobile/newemployee/right.png"></i>
									   </div>
									</div>
									<ul class="point_data clearfix">
									  <li>
									            劳动业绩
										<p>平均:¥${hashMap.totalProjectScale}</p>
										<span>实际：¥${toDay.projectValue}</span>
									  </li>
									  <li>
									            指定业绩
										<p>平均:¥${hashMap.totalAssignProjectScale}</p>
										<span>实际：¥${toDay.assignProjectValue}</span>
									  </li>
									  <li>
									            指定比例
										<p>平均:${hashMap.totalScale}%</p>
										<span>实际：${toDay.scale}%</span>
									  </li>
									  <li>
									            套餐业绩
										<p>平均:¥${hashMap.totalComboScale}</p>
										<span>实际：¥${toDay.comboValue}</span>
									  </li>
									  <li>
									            商品业绩
										<p>平均:¥${hashMap.totalGoodsScale}</p>
										<span>实际：¥${toDay.goodsValue}</span>
									  </li>
									  <li>
									            卡项业绩
										<p>平均:¥${hashMap.totalChargeScale}</p>
										<span>实际：¥${toDay.chargeValue}</span>
									  </li>
									</ul>
								</div>
						    </c:forEach>
						</div>
					</div>
				</div>	

               	<div class="swiper-slide">
					<div id="wapper1">
						<div id="scroll1">
							<c:forEach items="${hashMap.assignProjectList}" var="toDay" varStatus="toDayStatus">
						         <div class="uc-t ubb ub b-gra t-bla ub-ac lis clearfix">
									<div class="rank_content">
									   <div class="rank_detail">
										  <span class="num">${toDayStatus.index + 1}</span>
										  <img src="<%=picPath%>${toDay.headImage}">
										  <em>${toDay.name}</em>
										  <em>${toDay.employeeCode}</em>
										  <i>指定业绩：<span>¥${toDay.assignProjectValue}</span>
										  <img src="<%=basePath%>images/mobile/newemployee/right.png"></i>
									   </div>
									</div>
									<ul class="point_data clearfix">
									  <li>
									            劳动业绩
										<p>平均:¥${hashMap.totalProjectScale}</p>
										<span>实际：¥${toDay.projectValue}</span>
									  </li>
									  <li>
									            指定业绩
										<p>平均:¥${hashMap.totalAssignProjectScale}</p>
										<span>实际：¥${toDay.assignProjectValue}</span>
									  </li>
									  <li>
									            指定比例
										<p>平均:${hashMap.totalScale}%</p>
										<span>实际：${toDay.scale}%</span>
									  </li>
									  <li>
									            套餐业绩
										<p>平均:¥${hashMap.totalComboScale}</p>
										<span>实际：¥${toDay.comboValue}</span>
									  </li>
									  <li>
									            商品业绩
										<p>平均:¥${hashMap.totalGoodsScale}</p>
										<span>实际：¥${toDay.goodsValue}</span>
									  </li>
									  <li>
									            卡项业绩
										<p>平均:¥${hashMap.totalChargeScale}</p>
										<span>实际：¥${toDay.chargeValue}</span>
									  </li>
									</ul>
								</div>
						    </c:forEach>
						</div>
					</div>
				</div>	

                <div class="swiper-slide">
					<div id="wapper2">
						<div id="scroll2">
						  <c:forEach items="${hashMap.scaleList}" var="toDay" varStatus="toDayStatus">
						         <div class="uc-t ubb ub b-gra t-bla ub-ac lis clearfix">
									<div class="rank_content">
									   <div class="rank_detail">
										  <span class="num">${toDayStatus.index + 1}</span>
										  <img src="<%=picPath%>${toDay.headImage}">
										  <em>${toDay.name}</em>
										  <em>${toDay.employeeCode}</em>
										  <i>指定比例：<span>${toDay.scale}%</span>
										  <img src="<%=basePath%>images/mobile/newemployee/right.png"></i>
									   </div>
									</div>
									<ul class="point_data clearfix">
									  <li>
									            劳动业绩
										<p>平均:¥${hashMap.totalProjectScale}</p>
										<span>实际：¥${toDay.projectValue}</span>
									  </li>
									  <li>
									            指定业绩
										<p>平均:¥${hashMap.totalAssignProjectScale}</p>
										<span>实际：¥${toDay.assignProjectValue}</span>
									  </li>
									  <li>
									            指定比例
										<p>平均:${hashMap.totalScale}%</p>
										<span>实际：${toDay.scale}%</span>
									  </li>
									  <li>
									            套餐业绩
										<p>平均:¥${hashMap.totalComboScale}</p>
										<span>实际：¥${toDay.comboValue}</span>
									  </li>
									  <li>
									            商品业绩
										<p>平均:¥${hashMap.totalGoodsScale}</p>
										<span>实际：¥${toDay.goodsValue}</span>
									  </li>
									  <li>
									            卡项业绩
										<p>平均:¥${hashMap.totalChargeScale}</p>
										<span>实际：¥${toDay.chargeValue}</span>
									  </li>
									</ul>
								</div>
						    </c:forEach>
						</div>
					</div>
				</div>  
				
				<div class="swiper-slide">
					<div id="wapper3">
						<div id="scroll3">
						  <c:forEach items="${hashMap.comboList}" var="toDay" varStatus="toDayStatus">
						         <div class="uc-t ubb ub b-gra t-bla ub-ac lis clearfix">
									<div class="rank_content">
									   <div class="rank_detail">
										  <span class="num">${toDayStatus.index + 1}</span>
										  <img src="<%=picPath%>${toDay.headImage}">
										  <em>${toDay.name}</em>
										  <em>${toDay.employeeCode}</em>
										  <i>套餐业绩：<span>¥${toDay.comboValue}</span>
										  <img src="<%=basePath%>images/mobile/newemployee/right.png"></i>
									   </div>
									</div>
									<ul class="point_data clearfix">
									  <li>
									            劳动业绩
										<p>平均:¥${hashMap.totalProjectScale}</p>
										<span>实际：¥${toDay.projectValue}</span>
									  </li>
									  <li>
									            指定业绩
										<p>平均:¥${hashMap.totalAssignProjectScale}</p>
										<span>实际：¥${toDay.assignProjectValue}</span>
									  </li>
									  <li>
									            指定比例
										<p>平均:${hashMap.totalScale}%</p>
										<span>实际：${toDay.scale}%</span>
									  </li>
									  <li>
									            套餐业绩
										<p>平均:¥${hashMap.totalComboScale}</p>
										<span>实际：¥${toDay.comboValue}</span>
									  </li>
									  <li>
									            商品业绩
										<p>平均:¥${hashMap.totalGoodsScale}</p>
										<span>实际：¥${toDay.goodsValue}</span>
									  </li>
									  <li>
									            卡项业绩
										<p>平均:¥${hashMap.totalChargeScale}</p>
										<span>实际：¥${toDay.chargeValue}</span>
									  </li>
									</ul>
								</div>
						    </c:forEach>
						</div>
					</div>
				</div>  
				
				<div class="swiper-slide">
					<div id="wapper4">
						<div id="scroll4">
						  <c:forEach items="${hashMap.goodsList}" var="toDay" varStatus="toDayStatus">
						         <div class="uc-t ubb ub b-gra t-bla ub-ac lis clearfix">
									<div class="rank_content">
									   <div class="rank_detail">
										  <span class="num">${toDayStatus.index + 1}</span>
										  <img src="<%=picPath%>${toDay.headImage}">
										  <em>${toDay.name}</em>
										  <em>${toDay.employeeCode}</em>
										  <i>商品业绩：<span>¥${toDay.goodsValue}</span>
										  <img src="<%=basePath%>images/mobile/newemployee/right.png"></i>
									   </div>
									</div>
									<ul class="point_data clearfix">
									  <li>
									            劳动业绩
										<p>平均:¥${hashMap.totalProjectScale}</p>
										<span>实际：¥${toDay.projectValue}</span>
									  </li>
									  <li>
									            指定业绩
										<p>平均:¥${hashMap.totalAssignProjectScale}</p>
										<span>实际：¥${toDay.assignProjectValue}</span>
									  </li>
									  <li>
									            指定比例
										<p>平均:${hashMap.totalScale}%</p>
										<span>实际：${toDay.scale}%</span>
									  </li>
									  <li>
									            套餐业绩
										<p>平均:¥${hashMap.totalComboScale}</p>
										<span>实际：¥${toDay.comboValue}</span>
									  </li>
									  <li>
									            商品业绩
										<p>平均:¥${hashMap.totalGoodsScale}</p>
										<span>实际：¥${toDay.goodsValue}</span>
									  </li>
									  <li>
									            卡项业绩
										<p>平均:¥${hashMap.totalChargeScale}</p>
										<span>实际：¥${toDay.chargeValue}</span>
									  </li>
									</ul>
								</div>
						    </c:forEach>
						</div>
					</div>
				</div>  
				
				<div class="swiper-slide">
					<div id="wapper5">
						<div id="scroll5">
						  <c:forEach items="${hashMap.chargeList}" var="toDay" varStatus="toDayStatus">
						         <div class="uc-t ubb ub b-gra t-bla ub-ac lis clearfix">
									<div class="rank_content">
									   <div class="rank_detail">
										  <span class="num">${toDayStatus.index + 1}</span>
										  <img src="<%=picPath%>${toDay.headImage}">
										  <em>${toDay.name}</em>
										  <em>${toDay.employeeCode}</em>
										  <i>卡项业绩：<span>¥${toDay.chargeValue}</span>
										  <img src="<%=basePath%>images/mobile/newemployee/right.png"></i>
									   </div>
									</div>
									<ul class="point_data clearfix">
									  <li>
									            劳动业绩
										<p>平均:¥${hashMap.totalProjectScale}</p>
										<span>实际：¥${toDay.projectValue}</span>
									  </li>
									  <li>
									            指定业绩
										<p>平均:¥${hashMap.totalAssignProjectScale}</p>
										<span>实际：¥${toDay.assignProjectValue}</span>
									  </li>
									  <li>
									            指定比例
										<p>平均:${hashMap.totalScale}%</p>
										<span>实际：${toDay.scale}%</span>
									  </li>
									  <li>
									            套餐业绩
										<p>平均:¥${hashMap.totalComboScale}</p>
										<span>实际：¥${toDay.comboValue}</span>
									  </li>
									  <li>
									            商品业绩
										<p>平均:¥${hashMap.totalGoodsScale}</p>
										<span>实际：¥${toDay.goodsValue}</span>
									  </li>
									  <li>
									            卡项业绩
										<p>平均:¥${hashMap.totalChargeScale}</p>
										<span>实际：¥${toDay.chargeValue}</span>
									  </li>
									</ul>
								</div>
						    </c:forEach>
						</div>
					</div>
				</div>  
				
		   </div> 
	   </div>
    </div>
   <div style="height: 6rem;"></div>
   <ul class="bottom_fix clearfix">
      <li>
          <a href="<%=basePath%>staff/view/reception">
	          <img src="<%=basePath%>images/mobile/newemployee/bottomOne.png">
		      <p>接待</p>
	      </a>
	  </li>
	  <li>
	     <a href="<%=basePath%>staff/view/employeeOrderView/${session_key_store_account}/2">
	        <img src="<%=basePath%>images/mobile/newemployee/bottomTwo.png">
	        <p>订单</p>
	     </a>
	  </li>
	  <li>
	    <a href="<%=basePath%>staff/view/allEarning">
		    <img src="<%=basePath%>images/mobile/newemployee/bottomThree_.png">
		    <p>排行</p>
	    </a>
	  </li>
	  <li>
	     <a href="<%=basePath%>staff/view/staffCenter/${session_key_store_account}/2">
		     <img src="<%=basePath%>images/mobile/newemployee/bottomFour.png">
		     <p>我的</p>
	     </a>
	  </li>
    </ul> 
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/idangerous.swiper.min-2.4.1.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/app.js"></script>
<script>


$(function(){
	 $('.point_data').hide()
	 $('.rank_content').click(function(){
	   $(this).parent().find('.point_data').stop(true, true).slideToggle();
	 })
})
	
function changeShow(obj, type) {
    chooseDateType(type);
}

function chooseDateType(type) {
	window.location.href = baseUrl+"staff/view/allEarning?chooseType="+type;
}
</script>
</body>
</html>
