<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css"/>
    <c:set var="infoTip" value="开通成功"/>
    <c:choose>
    	<c:when test="${type == 2 }">
    		<c:set var="infoTip" value="续费成功"/>
    	</c:when>
    	<c:when test="${type == 3 }">
    		<c:set var="infoTip" value="购买成功"/>
    	</c:when>
    </c:choose>
    <title>${infoTip }</title>
</head>
<body class="gray-bg">

<div class="pay gray-bg">
	<ul class="list ">
        <li class="pay-title bsw tac">
            ${infoTip }！
        </li>
    </ul>
    <ul class="xinxi-detail-ul mt2">
        <li class="normal-li">
            <span >店铺名称</span>
            <span class="value fr">${storeInfo.storeName }</span>
        </li>
        <c:choose>
    	<c:when test="${type == 1 || type == 2 }">
	        <li class="normal-li">
	            <span >店铺账号</span>
	            <span class="value fr">${userName }</span>
	        </li>
	        <li class="normal-li">
	            <span >初始密码</span>
	            <span class="value fr">123456</span>
	        </li>
	        <li class=" normal-li">
	            <span >当前状态</span>
	            <span class="value fr">正常使用</span>
	        </li>
	        <li class=" normal-li">
	            <span >到期时间</span>
	            <span class="value fr">${overdueDate } 23:59:59</span>
	        </li>
	        <li class=" normal-li">登陆网址：www.maywant.com</li>
    	</c:when>
    	<c:otherwise>
	        <li class="normal-li">
	            <span >累计购买短信</span>
	            <span class="value fr">${storeAccount.totalSms }</span>
	        </li>
	        <li class=" normal-li">
	            <span >当前剩余短信</span>
	            <span class="value fr">${storeAccount.balanceSms }</span>
	        </li>
	        <a href="<%=basePath%>mobile/view/pay/chargeSys?businessType=1">
	        	<li class=" normal-li">系统续费</li>
	        </a>
    	</c:otherwise>
    </c:choose>
    </ul>
</div>
</body>
</html>