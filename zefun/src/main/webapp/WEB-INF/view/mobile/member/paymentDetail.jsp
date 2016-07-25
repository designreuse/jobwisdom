<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>支付信息</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
  </head>
<body>

<div class="content wrap">
    <div class="xiaofeimingxi">
	  <div class="xiaofei-item">
	    <div class="dingdan-title">
	      <span class="fl font-666 fs24">订单号：${orderPayment.orderCode }</span>
	      <span class="fr fs24">${orderPayment.createTime }</span>
	    </div>
	
	    <ul class="order-ul mb2 pb2">
	       <c:forEach items="${orderPayment.detailList }" var="detail">
	           <li class="xiaofei-list">
	            <div class="xiaofei-col one">
	              <span class="fl">
	               <c:choose>
	                   <c:when test="${detail.orderType == 4 }">
	                       开卡
	                   </c:when>
	                   <c:when test="${detail.orderType == 5 }">
                           充值
                       </c:when>
                       <c:when test="${detail.orderType == 6 }">
                           升级
                       </c:when>
                       <c:otherwise>
                            ${detail.projectName }
                       </c:otherwise>
	               </c:choose>
	              </span>
	              <span class="fr">
	               ￥${detail.projectPrice }
	              </span>
	            </div>
	            <c:choose>
	            	<c:when test="${empty detail.offType || edetail.offType == '' }">
	            		<c:if test="${detail.projectPrice > detail.discountAmount }">
			               <div class="clearfix"></div>
			                <div class="xiaofei-col two">
			                  <span class="fl">会员折扣优惠</span>
			                  <span class="fr">
			                     -￥${detail.projectPrice - detail.discountAmount }
			                  </span>
			                </div>
			            </c:if>
			            <c:if test="${detail.appointOff > 0 }">
			               <div class="clearfix"></div>
			                <div class="xiaofei-col three">
			                  <span class="fl">在线预约优惠</span>
			                  <span class="fr">
			                    -￥${detail.appointOff}
			                  </span>
			                </div>
			            </c:if>
	            	</c:when>
	            	<c:when test="${detail.offType == '疗程抵扣' }">
	            		<c:if test="${detail.offAmount > 0 }">
			               <div class="clearfix"></div>
			                <div class="xiaofei-col two">
			                  <span class="fl">${detail.offType }</span>
			                  <span class="fr">
			                     -￥${detail.offAmount }
			                  </span>
			                </div>
			            </c:if>
	            	</c:when>
	            	<c:otherwise>
	            		<c:if test="${detail.offAmount > 0 }">
			               <div class="clearfix"></div>
			                <div class="xiaofei-col two">
			                  <span class="fl">${detail.offType }</span>
			                  <span class="fr">
			                     -￥${detail.offAmount }
			                  </span>
			                </div>
			            </c:if>
			            <c:if test="${detail.appointOff > 0 }">
			               <div class="clearfix"></div>
			                <div class="xiaofei-col three">
			                  <span class="fl">在线预约优惠</span>
			                  <span class="fr">
			                    -￥${detail.appointOff}
			                  </span>
			                </div>
			            </c:if>
	            	</c:otherwise>
	            </c:choose>
	          </li>
	       </c:forEach>
	    </ul>
	    <ul class="xiaofei-zongjie bsw ">
	      <li>
	        <c:if test="${orderPayment.cashAmount > 0 }">
	           <div class="xiaofei-col two">
	              <span class="fl">现金支付</span>
	              <span class="fr">-￥${orderPayment.cashAmount }</span>
	            </div>
	           <div class="clearfix"></div>
	        </c:if>
	        <c:if test="${orderPayment.unionpayAmount > 0 }">
               <div class="xiaofei-col two">
                  <span class="fl">银联支付</span>
                  <span class="fr">-￥${orderPayment.unionpayAmount }</span>
                </div>
               <div class="clearfix"></div>
            </c:if>
            <c:if test="${orderPayment.cardAmount > 0 }">
               <div class="xiaofei-col two">
                  <span class="fl">卡金支付</span>
                  <span class="fr">-￥${orderPayment.cardAmount }</span>
                </div>
               <div class="clearfix"></div>
            </c:if>
            <c:if test="${orderPayment.wechatAmount > 0 }">
               <div class="xiaofei-col two">
                  <span class="fl">微信支付</span>
                  <span class="fr">-￥${orderPayment.wechatAmount }</span>
                </div>
               <div class="clearfix"></div>
            </c:if>
            <c:if test="${orderPayment.alipayAmount > 0 }">
               <div class="xiaofei-col two">
                  <span class="fl">支付宝支付</span>
                  <span class="fr">-￥${orderPayment.alipayAmount }</span>
                </div>
               <div class="clearfix"></div>
            </c:if>
            <c:if test="${orderPayment.realAmount <= 0 }">
               <div class="xiaofei-col two">
                  <span class="fl">实际支付</span>
                  <span class="fr">¥ 0.00</span>
                </div>
               <div class="clearfix"></div>
            </c:if>
	        <div class="xiaofei-name xiaofei-col">
	          <span class="fl">实际消费金额</span>
	          <span class="red-price fr s-price">￥${orderPayment.receivableAmount }</span>
	        </div>
	        <div class="clearfix"></div>
	      </li>
	      <li class="share">
	      	  <c:if test="${integralAmount > 0 }">
		      	<div class="tip-word">本次消费，您获得了 <span class="num">${integralAmount }</span> 积分。</div>
		      </c:if>
		      <c:if test="${isProject == '1' }">
		      	<div>赶快分享你的新造型，领取额外奖励！</div>
		      	<c:choose>
		            <c:when test="${order.orderStatus == 3 }">
		                <div onclick="goEvaluate()" class="normal-btn fr">评价并分享</div>
		            </c:when>
		            <c:otherwise>
		                <div onclick="goShare()" class="normal-btn fr">分享给好友</div>
		            </c:otherwise>
		        </c:choose>
		      </c:if>
	      </li>
	    </ul>
	  </div>
	
	  <div class="good-shop bsw">
	    <img src="<%=picPath %>${storeInfo.storeLogo }?imageView2/1/w/125/h/125" class="fl"/>
	    <div class="desc fl" >
	      <div> <span class="shop-name">${storeInfo.storeName }</span></div>
	      <div> <span class="shop-addr">${storeInfo.storeAddress }</span> </div>
	      <div> <span class="shop-desc">电话：${storeInfo.storeTel }</span> </div>
	    </div>
	  </div>
	</div>
</div>
<%@include file="../memberBase.jsp" %>
<script type="text/javascript">
function goEvaluate(){
	window.location.href = "<%=basePath %>memberCenter/view/orderEvaluate?orderId=${orderPayment.orderId}";
}
function goShare(){
    window.location.href = baseUrl + "memberCenter/view/share?orderId=${orderPayment.orderId}";
}
</script>
</body>
</html>