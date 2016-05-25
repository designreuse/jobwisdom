<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>选择门店</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
  </head>
<body>

<div class="content wrap">
    <div class="shop-select">
        <div class="shop-liansuo bsw" style="margin-bottom: .625rem">
		   <%-- <span class="shop-name">${mainStoreInfo.storeName }</span> --%>
		</div>
	    <c:forEach items="${storeList }" var="store">
	       <a href="<%=basePath %>${fn:replace(url, '_storeId_', store.storeId)}">
	         <div class="select-shop-item bsw mb10p">
			    <img src="<%=picPath %>${store.storeLogo}" class="fl"/>
			    <div class="desc fl" >
			      <div> <span class="shop-name">${store.storeName }</span></div>
			      <div> <span class="shop-addr">${store.storeAddress }</span> </div>
			      <div> <span class="shop-desc">电话：${store.storeTel }</span> </div>
			    </div>
			  </div>
		    </a>
	    </c:forEach>
    </div>
</div>  
<%@include file="../memberBase.jsp" %>
</body>
</html>