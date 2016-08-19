<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>我的订单</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobile/shop.css">
</head>

<style>
i {
	font-style: normal
}

.order_search_header {
	height: 3.5rem;
	background: #7b3187;
	line-height: 3.5rem;
}

.order_search_header .pay_style {
	float: left;
	text-align: center;
	width: 40%;
}

.order_search_header .pay_style .select {
	width: 8rem;
	height: 2.4rem;
	background: white;
	border-radius: 8px;
	color: #833e8e;
	margin: 0.6rem auto;
	line-height: 2.6rem;
	font-size: 14px
}

.select img {
	width: 16px;
	position: relative;
	top: 0.9rem;
	right: 0.5rem;
	float: right
}

.order_search_header .time {
	text-align: center;
	float: left;
	width: 60%
}

.time input {
	width: 6.5rem;
	height: 2.1rem;
	border-radius: 8px
}

.time span {
	color: white;
	display: inline-block;
	margin: 0 4px;
	font-weight: bold
}

.order_search_detail {
	box-shadow: 0 2px 2px #e5e5e5;
	background: white;
	margin-top: 1rem
}

.order_search_detail>p {
	font-size: 12px;
	height: 2.4rem;
	line-height: 2.4rem;
	border-bottom: 1px solid #efefef;
	color: #b5b5b5;
	padding-left: 1rem
}

.order_search_detail em {
	float: right;
	margin-right: 1rem;
	font-size: 14px
}

.complete {
	color: #82d14d;
}

.wait {
	color: #a818d1
}

table {
	width: 100%
}

table td {
	text-align: center
}

table img {
	width: 42px;
	height: 42px
}

.order_search_img {
	width: 42px;
	height: 42px;
	border-radius: 6px;
	background: #dcdcdc;
	margin-left: 0.5rem
}

table .main {
	font-size: 14px;
	color: black
}

table tr {
	height: 4.5rem
}

table td>p>span {
	color: #e75a22
}

table td:last-child {
	width: 20rem;
	text-align: right;
	padding-right: 0.6rem
}

table td>p {
	margin-bottom: 4px;
	width: 4rem;
	height: 1.6rem;
	border-radius: 6px;
	text-align: center;
	line-height: 1.6rem;
}

.upgrade {
	background: #43d6d5;
	color: white
}

.repayment {
	background: #dd5c1c;
	color: white
}

.open_card {
	background: #f56b62;
	color: white
}

.send {
	background: #e59d11;
	color: white
}

.recharge {
	background: #e94a7a;
	color: white
}

.shop {
	background: #009fe5;
	color: white
}

.set_meal {
	background: #62c183;
	color: white
}

.item {
	background: #ffa329;
	color: white
}

.original_cost {
	background: #dcdcdc;
	color: white;
	text-decoration: line-through
}

table td button {
	width: 5rem;
	height: 2.2rem;
	color: white;
	font-size: 14px;
	text-align: center;
	border: none;
	background: #7b3187;
	border-radius: 6px
}

.total {
	color: #ed5f19;
	margin-bottom: 0.4rem
}

.order_search_detail>p>i {
	display: inline-block;
	margin-left: 1rem
}

.evaluate {
	background: #1982ed
}

.ing {
	color: #ed5f19
}

.zzc {
	display: none;
	position: fixed;
	top: 0px;
	height: 100%;
	left: 0px;
	width: 100%;
	background: rgba(23, 23, 23, 0.7);
	z-index: 1000;
}

.zzc_slide_content {
	position: absolute;
	bottom: -50%;
	left: 0;
	width: 100%
}

.zzc_slide_content ul {
	width: 100%;
	background: white
}

.zzc_slide_content ul li {
	width: 33.3%;
	float: left;
	text-align: center;
	height: 4.5rem;
	line-height: 4.5rem
}

.zzc_slide_content>p {
	color: white;
	font-size: 16px;
	height: 3.6rem;
	width: 100%;
	background: #52006a;
	text-align: center;
	line-height: 3.6rem
}

.zzc_slide_content ul li a {
	display: inline-block;
	width: 60%;
	height: 2.5rem;
	border-radius: 8px;
	line-height: 2.5rem;
	color: white;
}
</style>
<body>
	<div class="zzc">
		<div class="zzc_slide_content">
			<p>消费类型</p>
			<ul class="clearfix">
				<li>
					<a href="<%=basePath %>memberCenter/view/orderList?orderType=1" style="background: #e94a7a">项目</a>
				</li>
				<li>
					<a href="<%=basePath %>memberCenter/view/orderList?orderType=2" style="background: #f56b62">商品</a>
				</li>
				<li>
					<a href="<%=basePath %>memberCenter/view/orderList?orderType=3" style="background: #ffa329">疗程</a>
				</li>
				<li>
					<a href="<%=basePath %>memberCenter/view/orderList?orderType=4" style="background: #e59d11">开卡</a>
				</li>
				<li>
					<a href="<%=basePath %>memberCenter/view/orderList?orderType=5" style="background: #dd5c1c">充值</a>
				</li>
				<li>
					<a href="<%=basePath %>memberCenter/view/orderList?orderType=6" style="background: #43d6d5">升级</a>
				</li>
				<li>
					<a href="<%=basePath %>memberCenter/view/orderList?orderType=7" style="background: #62c183">赠送</a>
				</li>
				<li>
					<a href="<%=basePath %>memberCenter/view/orderList?orderType=8" style="background: #009fe5">还款</a>
				</li>
				<li>
					<a href="<%=basePath %>memberCenter/view/orderList" style="background: #4e1d56">全部</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="con">
		<div class="order_search">
			<div class="order_search_header clearfix">
				<div class="pay_style">
					<div class="select">
						消费类型<img src="<%=basePath %>images/mobile/member/pay_style.png">
					</div>
				</div>
				<div class="time">
					<input type="text">
					<span>--</span>
					<input type="text">
				</div>
			</div>

			<div class="order_search_">

				<c:forEach items="${orderList }" var="order">
					<div class="order_search_detail">
						<p>
							<span>单号：${order.orderCode }</span><i>${order.updateTime }</i>
							<c:choose>
								<c:when test="${order.orderStatus == 1 }">
									<em class="ing">进行中</em>
								</c:when>
								<c:when test="${order.orderStatus == 2 || order.orderStatus == 5 }">
									<em class="ing">进行中</em>
								</c:when>
								<c:when test="${order.orderStatus == 3 }">
									<em class="wait">待评价</em>
								</c:when>
								<c:otherwise>
									<em class="complete">已完成</em>
								</c:otherwise>
							</c:choose>
						</p>
						<table>
							<c:forEach items="${order.detailList }" var="detail" varStatus="index">
								<tr>
									<c:choose>
										<c:when test="${detail.orderType == 1 }">
											<td>
												<div class="order_search_img">
													<img src="<%=picPath%>${detail.projectImage}">
												</div>
											</td>
											<td>
												<p class="main">项目
												<p>
													<span>¥${detail.discountAmount}</span>
											</td>
											<td>
												<p class="item">项目</p>
												<p class="original_cost">¥${detail.projectPrice }</p>
											</td>
										</c:when>
										<c:when test="${detail.orderType == 2 }">
											<td>
												<div class="order_search_img">
													<img src="<%=picPath%>${detail.projectImage}">
												</div>
											</td>
											<td>
												<p class="main">商品
												<p>
													<span>¥${detail.discountAmount}</span>
											</td>
											<td>
												<p class="shop">商品</p>
												<p class="original_cost">¥${detail.projectPrice }</p>
											</td>
										</c:when>
										<c:when test="${detail.orderType == 3 }">
											<td>
												<div class="order_search_img">
													<img src="<%=picPath%>${detail.projectImage}">
												</div>
											</td>
											<td>
												<p class="main">疗程
												<p>
													<span>¥${detail.discountAmount}</span>
											</td>
											<td>
												<p class="set_meal">疗程</p>
												<p class="original_cost">¥${detail.projectPrice }</p>
											</td>
										</c:when>
										<c:when test="${detail.orderType == 4 }">
											<td>
												<div class="order_search_img">
													<img src="<%=basePath%>images/mobile/member/order_search3.png">
												</div>
											</td>
											<td>
												<p class="main">开卡
												<p>
													<span>¥${detail.discountAmount}</span>
											</td>
											<td>
												<p class="open_card">开卡</p>
											</td>
										</c:when>
										<c:when test="${detail.orderType == 5 }">
											<td>
												<div class="order_search_img">
													<img src="<%=basePath%>images/mobile/member/order_search5.png">
												</div>
											</td>
											<td>
												<p class="main">充值
												<p>
													<span>¥${detail.discountAmount}</span>
											</td>
											<td>
												<p class="recharge">充值</p>
											</td>
										</c:when>
										<c:when test="${detail.orderType == 6 }">
											<td>
												<div class="order_search_img">
													<img src="<%=basePath%>images/mobile/member/order_search1.png">
												</div>
											</td>
											<td>
												<p class="main">升级
												<p>
													<span>¥${detail.discountAmount}</span>
											</td>
											<td>
												<p class="upgrade">升级</p>
											</td>
										</c:when>
										<c:when test="${detail.orderType == 7 }">
											<td>
												<div class="order_search_img">
													<img src="<%=basePath%>images/mobile/member/order_search4.png">
												</div>
											</td>
											<td>
												<p class="main">赠送
												<p>
													<span>¥${detail.discountAmount}</span>
											</td>
											<td>
												<p class="send">赠送</p>
											</td>
										</c:when>
										<c:when test="${detail.orderType == 8 }">
											<td>
												<div class="order_search_img">
													<img src="<%=basePath%>images/mobile/member/order_search2.png">
												</div>
											</td>
											<td>
												<p class="main">还款
												<p>
													<span>¥${detail.discountAmount}</span>
											</td>
											<td>
												<p class="repayment">还款</p>
											</td>
										</c:when>
									</c:choose>
									<c:if test="${index.count == 1 }">
										<td rowspan="100">
											<div class="total">合计:¥${order.realAmount }</div>
											<c:choose>
											<%-- <c:when test="${order.orderStatus == 2 || order.orderStatus == 5 }">
												<button onclick="pay(${order.orderId})" class="evaluate">结账</button>
											</c:when>
					                        <c:when test="${order.orderStatus == 101 }">
					                           <button onclick="goShare(${order.orderId})" class="evaluate">分享</button>
					                        </c:when> --%>
											<c:when test="${order.orderStatus == 100 }">
					                           <button onclick="goEvaluate(${order.orderId})" class="evaluate">评价</button>
					                        </c:when>
											</c:choose>
											<c:if test="${order.orderStatus == 3 || order.orderStatus == 4 || order.orderStatus == 100 || order.orderStatus == 101}">
						                         <button onclick="goDetail(${order.orderId})" class="">小票</button>
						                    </c:if>
										</td>
									</c:if>
								</tr>
							</c:forEach>
						</table>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>
	<%@include file="../memberBase.jsp"%>
	<script>
		//弹出
		$(function() {
			$('.select').on('touchstart', function() {
				$('.zzc').fadeIn();
				$('body,html').attr('style', 'overflow:hidden');
				$('.zzc_slide_content').animate({
					bottom : 0
				}, 600)
			});
			//隐藏
			$('.zzc').on('touchstart', function(e) {
				var tar = e.target;
				if ($(tar).is('.zzc')) {
					$(this).fadeOut();
					$('body,html').attr('style', 'height:100%');
					$('.zzc_slide_content').attr('style', 'bottom:-50%');
				}
			});
		})
	</script>
	<script type="text/javascript">
		function goEvaluate(orderId) {
			window.location.href = baseUrl
					+ "memberCenter/view/orderEvaluate?orderId=" + orderId;
		}
		function goShare(orderId) {
			window.location.href = baseUrl + "memberCenter/view/share?orderId="
					+ orderId;
		}
		function goDetail(orderId) {
			window.location.href = baseUrl
					+ "memberCenter/view/paymentDetail/${session_key_store_account}/1?orderId="
					+ orderId;
		}

		function pay(orderId) {
			window.location.href = baseUrl
					+ "memberCenter/view/orderPay/${session_key_store_account}/1?orderId="
					+ orderId;
		}
	</script>

</body>
</html>