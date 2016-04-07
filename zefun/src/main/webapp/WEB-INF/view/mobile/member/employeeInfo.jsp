<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>名师介绍</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=starCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
  </head>
<body>

<div class="content wrap">
     <div class="famous-info">
	    <ul class="designer-list famous-list">
	        <li class="designer-item-content">
	            <img src="<%=picPath %>${employeeInfo.headImage }"/>
	            <div class="info">
	                <div class="info-wrap">
	                    <div class="fs30 font-666">
		                    ${employeeInfo.employeeCode } ${employeeInfo.name } 
		                    <span class="ml normoal-word font-size-28 font-666">(${employeeInfo.levelName })</span>
	                    </div>
	                    <input type="number" class="rating input-id " data-size="xs" disabled value="${employeeInfo.serviceScore }" style="display: inline-block">
	                    <div class="font-666 font-size-24">已服务 <span class="blue-word">${employeeInfo.serviceCount}</span> 人</div>
	                </div>
	            </div>
	        </li>
	      </ul>
	    <div class="famous-detail">
	        <div class="pingjia-title">
	            个人介绍
	        </div>
	        <div class="famous-name">
	            <p>
	            	<c:choose>
	            		<c:when test="${empty employeeInfo.employeeDesc }">
	            			这个家伙很懒，啥都没留下...
	            		</c:when>
	            		<c:otherwise>
	            			${employeeInfo.employeeDesc }
	            		</c:otherwise>
	            	</c:choose>
	            </p>
	        </div>
	    </div>
	    <div class="famous-works">
	        <div class="pingjia-title">
	            TA的服务
	        </div>
	        <ul class="famous-js">
	        	<c:forEach items="${projectList }" var="project">
	        		<li>
		                <a href="<%=basePath %>memberCenter/view/employeeProject?storeId=${session_key_store_id}&employeeId=${employeeInfo.employeeId}&projectId=${project.projectId}">
		                    <img class="lazy" name="lazyImage" src="<%=picPath %>img_lazy_loding.png" data-original="<%=picPath %>${project.projectImage}"/>
		                    <div class="famous-wz">
		                        <p>${project.projectName }</p>
		                        <div class="works-price">
		                        	<span class="fl font-size-44 red-price">￥${project.projectPrice }</span>
		                        	<%-- <span class="fr">已服务<span class="color-b">${project.salesCount }</span>人</span> --%>
		                        </div>
		                    </div>
		                </a>
		            </li>
	        	</c:forEach>
	        </ul>
	    </div>
	</div>
</div>
<%@include file="../memberBase.jsp" %>
<script type="text/javascript" src="<%=starJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.lazyload.min.js"></script>
<script type="text/javascript">
$(".input-id").rating({showCaption : false, step : 0.5});

var width = $(window).width();
$("img[name='lazyImage']").each(function(){
	var dw = width * 2;
    $(this).attr("src", $(this).attr("data-original") + "?imageView2/1/w/" + dw + "/h/" + dw)
});

$("img.lazy").lazyload({ 
  threshold : 100,
  skip_invisible : false,
  effect: "fadeIn"
}); 
</script>
</body>
</html>