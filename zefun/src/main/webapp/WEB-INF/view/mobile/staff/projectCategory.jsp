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
    <style>
		.active{font-size:13px;color:#ea631a!important;border-bottom:1px solid #ea631a!important}
     .apartment{position:relative;height:3.4rem;background:#ea631a;color:white;font-size:16px;text-align:center;line-height:3.4rem}
	 .apartment img{position:absolute;left:1rem;top:0.8rem;width:23px;}
	 .wash_item_img img{;width:4rem}
	 .wash_item li{background:white;border-bottom:1px solid #e5e5e5;height:5rem;}
	 .img_right{float:left;max-width:50%}
	 .img_right>p{font-size:16px;color:black}
	 .img_right>span{color:#9b8b8b;display:inline-block;margin-top:0.5rem}
	 .wash_item_img{padding:0.5rem;margin:0 0 0 1rem;width:4rem;height:4rem;float:left}
	 .img_right{margin-top:0.6rem}
	 .wash_item_price{float:right;margin-top:1.3rem;margin-right:1rem;color:#e65420;font-size:16px}
	 .zzc{position: fixed;
		top: 0px;
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
	.apartment_ul li{color:black;border-bottom:1px solid white;width:120px;float:left;text-align:center;height:3rem;text-align:center;line-height:3rem}
	.apartment_ul{width:100%;overflow:overlay;background:white;border-bottom:1px solid #e5e5e5;box-shadow:0 2px 2px #e5e5e5 ;}
	</style>
</head>
<body>
<div class="zzc" style="display:none">
     <div class="zzc_conetent">
		<p>选择部门</p>
		<ul class="clearfix">
		  <c:forEach items="${deptList}" var="dept" varStatus="status">
             <li onclick="changeDept(${dept.deptId})"><span>${dept.deptName}</span></li>
          </c:forEach>
		</ul> 
	 </div>	
   </div>
   <c:forEach items="${dtoList}" var="dto" varStatus="status">
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
                          <li onclick="chooseProjectInfo(${projectInfo.projectId})">
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
   </c:forEach>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript">
var detailId = '${detailId}';
$(function(){
    $('.apartment img').click(function(){
	  $('.zzc').show();
	  $('body,html').attr('style','overflow:hidden;height:100%');
	  $('.zzc_conetent').animate({
         bottom:0
	  },500);
	})
	$('.zzc').click(function(e){
	   if($(e.target).is('.zzc')){
	     $('.zzc').hide();
		 $('.zzc_conetent').attr('style','bottom:-50%');
		 $('body,html').attr('style','overflow:visible;height:100%');
	   } 
	})
  })
 
 
 $(function(){
   var count=$('.apartment_ul li').size();
   $('.apartment_ul ul').css('width',count*120);
   $('.apartment_ul li').click(function(){
     $(this).addClass('active').siblings().removeClass('active')  
    
   })   
 })
  
  function changeDept(deptId) {
	    $(".con").hide();
    	$("div[deptId='"+deptId+"']").show();
    	
    	$(".zzc").hide();
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
</body>
</html>