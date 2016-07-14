<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>选择服务</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/app.css" />
     <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/idangerous.swiper.css">
    <style>
		 header{background:white}
	
      .mycolor-slide{color:red!important; }
	.ub-f1{background:white;height:60px;border-bottom:1px solid #e5e5e5}
	.swiper-container1{position:relative}
	.bar{top:36px}
	.swiper-slide .title{line-height:40px}
	.swiper-slide,.swiper-wrapper,.swiper-slide{height:auto!important}
	.active{font-size:13px;color:#ea631a!important;border-bottom:1px solid #ea631a!important}
     .apartment{position:relative;height:3.4rem;background:#ea631a;color:white;font-size:16px;text-align:center;line-height:3.4rem}
	 .apartment img{position:absolute;left:1rem;top:0.8rem;width:23px;}
	 .wash_item_img img{;width:4rem}
	 .wash_item li{background:white;border-bottom:1px solid #e5e5e5;height:5rem;}
	 .img_right{float:left;max-width:50%}
	 .img_right>p{font-size:14px;color:#381717}
	 .img_right>span{color:#9b8b8b;display:inline-block;margin-top:0.5rem;font-size:12px}
	 .wash_item_img{padding:0.5rem;margin:0 0 0 1rem;width:4rem;height:4rem;float:left}
	 .img_right{margin-top:0.6rem}
	 .wash_item_price{float:right;margin-top:1.3rem;margin-right:1rem;color:#e65420;font-size:14px}
	 .zzc{position: fixed;top: 0px;
		height:100%;
		left: 0px;
		width: 100%;
		z-index: 10000;
		background: rgba(0, 0, 0, 0.5);
		font-size: 12px;
		color: black;}
	.zzc p{font-size:16px;color:white;height:3.6rem;text-align:center;line-height:3.6rem;background:#ea631a}
     .zzc ul li{margin-bottom:1rem;float:left;width:33.3%;text-align:center;height:3rem;line-height:3rem;}	
	.zzc ul li span{display:inline-block;width:80%;height:2.6rem;border:1px solid #201712;line-height:2.6rem}
	.zzc ul {background:white;padding-top:1rem}
	.zzc_conetent{position:absolute;bottom:-50%;left:0;width:100%}
	</style>
</head>
<body>
<div class="zzc" style="display:none">
     <div class="zzc_conetent">
		<p>选择部门</p>
		<ul class="clearfix">
		  <c:forEach items="${deptList}" var="dept" varStatus="status">
             <li ontouchstart="changeDept(${dept.deptId})"><span>${dept.deptName}</span></li>
          </c:forEach>
		</ul> 
	 </div>	
</div>
   <%-- <c:forEach items="${dtoList}" var="dto" varStatus="status">
       <div class="con" deptId = "${dto.deptId}" <c:if test="${status.index != 0 }">style="display: none;"</c:if>> 
		  <p class="apartment"><img src="<%=basePath%>images/mobile/newemployee/down_content_.png">${dto.deptName}</p>
	      <div class="apartment_ul clearfix">
		       <ul>
		        <c:forEach items="${dto.project}" var="projectCategoryDto" varStatus="status">
	                <li categoryId="${projectCategoryDto.categoryId }">${projectCategoryDto.categoryName}</li>
	            </c:forEach>
		      </ul>
	      </div>
		  <c:forEach items="${dto.project}" var="projectCategoryDto" varStatus="categoryStatus">
                  <ul class="wash_item <c:if test="${categoryStatus.index != 0 }">hide</c:if>" categoryId="${projectCategoryDto.categoryId }">
                      <c:forEach items="${projectCategoryDto.projectList}" var="projectInfo" varStatus="status">
                          <li ontouchstart="chooseProjectInfo(${projectInfo.projectId})">
						     <div class="wash_item_img"><img src="<%=picPath%>${projectInfo.projectImage}"></div>
							 <div class="img_right">
							   <p>${projectInfo.projectName}</p>
							   <span>已服务：${projectInfo.salesCount}人</span>
							 </div>
							 <div class="wash_item_price">¥${projectInfo.projectPrice}</div>
						  </li>
                      </c:forEach>
                   </ul>
            </c:forEach>
	   </div>
   </c:forEach> --%>
   <div class="con"> 
	   <p class="apartment"><img src="<%=basePath%>images/mobile/newemployee/down_content_.png">${deptName}</p>
		<header>
			<div class="swiper-container1">
				<div class="swiper-wrapper">
				    <c:forEach items="${projectCategoryList}" var="projectCategoryDto" varStatus="status">
				        <div class="swiper-slide mycolor-slide" id="slide${status.index}">
							<div class="title cuurent" name="title">
								${projectCategoryDto.categoryName}
							</div>
						</div>
				    </c:forEach>
					<span class="bar" style="left: 2px; width: 64px;background-color: red;"></span>
				</div>
				<div class="pagination" style="display: none;"></div>
			</div>
		</header>
		
		<div class="swiper-container">
			<div class="swiper-wrapper">
			    <c:forEach items="${projectCategoryList}" var="projectCategoryDto" varStatus="status">
				      <div class="swiper-slide">
							<div id="wapper${status.index}">
								<div id="scroll${status.index}">
								    <c:forEach items="${projectCategoryDto.projectList}" var="projectInfo">
								        <div class="uc-t ubb ub b-gra t-bla ub-ac lis">
											<div class="ub-f1 ut-s clearfix" onclick="chooseProjectInfo(${projectInfo.projectId})">
												 <div class="wash_item_img"><img src="<%=picPath%>${projectInfo.projectImage}"></div>
											      <div class="img_right">
													   <p>${projectInfo.projectName }</p>
													   <span>已服务：${projectInfo.salesCount }人</span>
												  </div>
												 <div class="wash_item_price">¥${projectInfo.projectPrice }</div>	
											</div>	
										</div>
								    </c:forEach>
								</div>
							</div>
						</div>	  
				</c:forEach>
		   </div> 
	   </div>
    </div>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/idangerous.swiper.min-2.4.1.js"></script>
<script type="text/javascript">
var detailId = '${detailId}';

 zepto(".zzc").on("touchend", function(e){
  if($(e.target).is('.zzc')){
	     $('.zzc').hide();
		  $('.zzc_conetent').attr('style','bottom:-50%');
		  $('body,html').attr('style','overflow:visible;height:100%');
	   } 
 })

 zepto("body").delegate(".apartment", "touchend", function(){
  $('.zzc').show();
   $('body,html').attr('style','overflow:hidden;height:100%');
   $('.zzc_conetent').animate({
       bottom:0
  },500);
 })
 
  function changeDept(deptId) {
	 window.location.href = baseUrl+"staff/view/selectCategory?detailId="+detailId+"&deptId="+deptId;
  }
  
  function chooseProjectInfo(projectId) {
	  $.ajax({
	        type : "post",
	        url : baseUrl + "staff/action/settingProject",
	        data : "projectId="+projectId+"&detailId="+detailId,
	        dataType : "json",
	        success : function(e){
	            if(e.code != 0){
	                dialog(e.msg);
	                return;
	            }
	            var orderId = e.msg;
	            window.location.href = baseUrl+"staff/view/order/all?orderId="+orderId;
	        }
	    });
  }
</script>
<script type="text/javascript" src="<%=basePath%>js/mobile/app.js"></script>
</body>
</html>