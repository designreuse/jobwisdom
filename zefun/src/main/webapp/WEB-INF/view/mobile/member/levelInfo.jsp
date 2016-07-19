<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<link rel="stylesheet" href="<%=basePath%>css/mobile/shop.css" />
<title>会员卡信息</title>
<style>
body {
	background: #85888d;
	color: white
}

.vip_manage_content {
	width: 25rem;
	font-size: 15px;
	height: 15.3rem;
	border-radius: 1rem;
	box-shadow: 0 2px 2px #5d5f62
}

.vip_manage_content>p {
	margin: 0 auto;
	padding: 1.4rem 0 0.6rem 0;
	text-align: center
}

.vip_manage_content>p span {
	font-size: 3.6rem;
	margin-right: 0.5rem
}

.vip_manage_content ul li {
	text-align: center;
	line-height: 3.2rem
}

.vip_manage_content .rest_money {
	float: right;
	margin-right: 1rem;
	margin-top: 0.8rem
}

.vip_manage_content>p>em {
	font-size: 20px
}

.vip_manage {
	width: 25rem;
	margin: 0 auto
}

.vip_manage>p {
	margin-top: 1rem
}

.vip_manage_content {
	margin-top: 1rem
}

.vip_color1 {
	background: #8552a1
}

.vip_color2 {
	background: #f8b65b
}
</style>
</head>
<body style="font: 0.85em/1.4 微软雅黑,Microsoft Yahei, Arial, san-serif;">
	<div class="con">
		<div class="vip_manage">
			<p>我的会员卡</p>
			<c:forEach items="${subAccountList }" var="subAccount">
				<div class="vip_manage_content vip_color1" style="background:${subAccount.levelLogo}">
					<p>
						<span>VIP</span><em>${subAccount.levelName}</em>
					</p>
					<ul>
						<li>项目折扣：${subAccount.projectDiscount/100 }折</li>
						<li>商品折扣：${subAccount.goodsDiscount/100 }折</li>
					</ul>
					<div class="rest_money">卡上余额：${subAccount.balanceAmount }元</div>
				</div>
			</c:forEach>
		</div>
	</div>
</body>

</html>