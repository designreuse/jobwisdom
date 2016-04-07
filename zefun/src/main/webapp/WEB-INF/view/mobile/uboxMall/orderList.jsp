<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>购物列表</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath %>css/mobile/share.min.css" />
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
    <style type="text/css">
    	.content {
	        height: 100%;
	    }
    </style>
  </head>
<body>
<div class="content wrap">
	<section class="order-list">
		<c:forEach items="${transactionList }" var="transaction">
			<ul class="list-normal space-t10">
		        <li class="item">
		            <div class="pr">
		            	<c:choose>
		            		<c:when test="${transaction.boxStatus == 1 }">
		            			<div class="single-from-s o">取货码：${transaction.pickupCode }</div>
		                		<span class="ar col2">待取货</span>
		            		</c:when>
		            		<c:otherwise>
		                		<span class="ar">已取货</span>
		            		</c:otherwise>
		            	</c:choose>
		            </div>
		        </li>
		        <li class="item">
		            <a class="deal-card" href="<%=basePath %>uboxMall/view/paymentInfo?transactionId=${transaction.transactionId }">
		                <div class="deal-img m" style="background-image:url(${transaction.goodsInfo.goodsInfo.uboxPicture});"></div>
		                <div class="deal-con pr">
		                    <h3 class="single-title s r">${transaction.goodsInfo.goodsInfo.uboxName }</h3>
		                    <div class="col1">
			                    ${transaction.transactionAmount/100 }元
			                    <c:if test="${transaction.transactionIntegral > 0 }">
			                    ＋${transaction.transactionIntegral }积分
			                    </c:if>
		                    </div>
		                    <div>订单号：${transaction.transactionId }<br /><span class="order-time">下单时间：${transaction.createTime }</span></div>
		                </div>
		            </a>
		        </li>
		    </ul>
		</c:forEach>
	</section>
</div>	  
<%@include file="../memberBase.jsp" %>
</body>
</html>