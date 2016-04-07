<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>会员卡信息</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
  </head>
<body>
<div class="content wrap">
	<div class="mb-footer">
	    <ul class="designer-list famous-list">
	        <li class="designer-item-content">
	            <img src="<%=picPath%>${memberInfo.headUrl }" alt="">
	            <div class="info">
	                <div class="info-wrap">
	                    <div class="fs30 font-333 mb1">${memberInfo.name }</div>
	                    <div class="font-666 font-size-24">手机号码 <span class="blue-word">${memberInfo.phone }</span></div>
	                </div>
	            </div>
	        </li>
	    </ul>
	    <c:forEach var="subAccount" items="${subAccountList }">
	    	<div class="famous-detail mt2">
		        <div class="pingjia-title font-666">
		            <span>${subAccount.levelName }</span> <span class="fr">余额：<span class="pro-price">${subAccount.balanceAmount }</span></span>
		        </div>
		        <div class="famous-name">
		            <p class="font-333">
		                <c:choose>
		            		<c:when test="${subAccount.goodsDiscount >= 100 }">
		            			购买门店内商品需支付原价金额，
		            		</c:when>
		            		<c:otherwise>
		            			购买门店内商品享受${subAccount.goodsDiscount }折优惠，
		            		</c:otherwise>
		            	</c:choose>
		            	<c:choose>
		            		<c:when test="${subAccount.projectDiscount >= 100 }">
		            			消费门店内所有项目需支付原价金额。
		            		</c:when>
		            		<c:otherwise>
		            			消费门店内所有项目享受${subAccount.projectDiscount}折优惠。
		            		</c:otherwise>
		            	</c:choose>
		                更低折扣请到店咨询，在线预约还有折上折哦！
		            </p>
		            <div class="ka-title">
		                <span>会员须知</span>
		            </div>
		            <c:choose>
	        		<c:when test="${not empty memberLevel.levelNoticeList }">
	        			<c:forEach var="content" items="${memberLevel.levelNoticeList }">
	           				<p>${content.text }</p>
	           			</c:forEach>
	        		</c:when>
	        		<c:otherwise>
	        			<p>暂无描述</p>
	        		</c:otherwise>
	        	</c:choose>
		        </div>
		    </div>
	    </c:forEach>
	</div>
</div>  
<%@include file="../memberBase.jsp" %>
</body>
</html>