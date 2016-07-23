<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>我的订单</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
  </head>
<body>

<div class="content wrap">
    
    <c:choose>
    	<c:when test="${empty orderList }"><div></c:when>
    	<c:otherwise><div class="my-order-doing"></c:otherwise>
    </c:choose>
        <c:forEach items="${orderList }" var="order">
            <ul class="order-ul bsw mb2">
	            <li class="dingdan-danhao">
	                <span class="danhao fl">
	                    	单号: ${order.orderCode }
	                </span>
	                <c:choose>
	                   <c:when test="${order.orderStatus == 1 }">
	                       <span class="fr yellow-word fs24">进行中</span>
	                   </c:when>
	                   <c:when test="${order.orderStatus == 2 || order.orderStatus == 5 }">
                           <span class="fr yellow-word fs24">待结账</span>
                       </c:when>
                       <c:when test="${order.orderStatus == 3 }">
                           <span class="fr font-blue fs24">待评价</span>
                       </c:when>
                       <c:otherwise>
                           <span class="fr font-999 fs24">已完成</span> 
                       </c:otherwise>
	                </c:choose>
	            </li>
	            <c:forEach items="${order.detailList }" var="detail">
	               <li class="dingdan-list">
	                    <c:choose>
	                       <c:when test="${detail.orderType > 3 }">
	                           <img src="<%=picPath%>zefun/mobile/charge.png?imageView2/1/w/220/h/220" alt="">
	                       </c:when>
	                       <c:otherwise>
	                           <img src="<%=picPath%>${detail.projectImage}?imageView2/1/w/220/h/220" alt="">
	                       </c:otherwise>
	                    </c:choose>
	                    <div class="list-desc">
	                        <div class="list-name">
	                        	${detail.projectName }
		                        <c:if test="${detail.projectCount > 1}">
		                        	&nbsp;&nbsp;X${detail.projectCount}
		                        </c:if>
	                        </div>
	                        <div class="faxingshi origin-price"><span class="text-through">价格: ￥${detail.projectPrice }</span></div>
	                        <div class="dingdan">
	                           <c:choose>
	                               <c:when test="${detail.orderType == 2 }">
	                                   <span class= "state combo">商品</span>
	                               </c:when>
	                               <c:when test="${detail.orderType == 3 }">
                                       <span class= "state combo">疗程</span>
                                   </c:when>
                                   <c:when test="${detail.orderType == 4 }">
                                        <span class= "state doing">开卡</span>
                                   </c:when>
                                   <c:when test="${detail.orderType == 5 }">
                                        <span class= "state doing">充值</span>
                                   </c:when>
                                   <c:when test="${detail.orderType == 6 }">
                                        <span class= "state doing">升级</span>
                                   </c:when>
                                   <c:otherwise>
                                        <c:choose>
		                                   <c:when test="${detail.orderStatus == 1 }">
		                                       <span class="fs22 font-999">等待中...</span>
		                                   </c:when>
		                                   <c:when test="${detail.orderStatus == 2 }">
		                                       <span class="fs22 font-999">服务中...</span>
		                                   </c:when>
		                                   <c:otherwise>
		                                       <span class="fs22 font-999">服务用时：</span><span class="font-666">${detail.serviceLength }分钟</span>
		                                   </c:otherwise>
		                               </c:choose>
                                   </c:otherwise>
	                           </c:choose>
	                        </div>
	                    </div>
	                    <div class="dingdan-edit fr">
	                        <span class="list-price">
	                        ￥${detail.discountAmount}
	                        </span>
	                    </div>
	                </li>
	            </c:forEach>
	            <li class="dingdan-heji">
	                <span class="heji">合计: ￥<span class="num">${order.discountAmount }</span></span>
	                <c:choose>
                       <c:when test="${order.orderStatus == 2 || order.orderStatus == 5 }">
                           <div onclick="pay('${order.orderId}')" class="normal-btn fr mt2">结&nbsp;&nbsp;账</div>
                       </c:when>
                       <c:when test="${order.orderStatus == 100 }">
                           <div onclick="goEvaluate(${order.orderId})" class="normal-btn fr ml2 mt2">评价并分享</div>
                       </c:when>
                       <c:when test="${order.orderStatus == 101 }">
                       	   <div onclick="goShare(${order.orderId})" class="normal-btn ml2 fr">分&nbsp;&nbsp;享</div>
                       </c:when>
                    </c:choose>
                    <c:if test="${order.orderStatus == 3 || order.orderStatus == 4 || order.orderStatus == 100 || order.orderStatus == 101}">
                         <div onclick="goDetail(${order.orderId})" class="normal-btn fr">小&nbsp;&nbsp;票</div>
                    </c:if>
	            </li>
	        </ul>
        </c:forEach>
	</div>
	<c:if test="${empty orderList }">
	   <a href="<%=basePath%>memberCenter/view/orderAppointment/${session_key_store_id}/1">
		   <div class="kongjilv">
		        <div class="center">
		            <div class="iconfont icon-xingzhuang14"></div>
		            <div>您暂无任何订单，在线预约可享受更多优惠</div>
		        </div>
		    </div>
	    </a>
	</c:if>
</div>
<%@include file="../memberBase.jsp" %>
<script type="text/javascript">
   function goEvaluate(orderId){
	   window.location.href = baseUrl + "memberCenter/view/orderEvaluate?orderId=" + orderId ;
   }
   function goShare(orderId){
       window.location.href = baseUrl + "memberCenter/view/share?orderId=" + orderId ;
   }
   function goDetail(orderId){
       window.location.href = baseUrl + "memberCenter/view/paymentDetail/${session_key_store_account}/1?orderId=" + orderId ;
   }
   
   function pay(orderId){
	   window.location.href = baseUrl + "memberCenter/view/orderPay/${session_key_store_account}/1?orderId=" + orderId ;
   }
</script>
</body>
</html>