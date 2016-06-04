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
<link rel="stylesheet" href="<%=basePath %>css/mobile/level.css"/>
<title>会员卡信息</title>
</head>
<body>
	<div class="vip">
		<c:forEach items="${subAccountList }" var="subAccount">
			<div class="vip${subAccount.levelTemplate }">
				<ul>
					<li class="flip-container">
						<div class="flipper">
							<div class="front" style="background: url('<%=qiniuPath %>${fn:split(subAccount.levelLogo,',')[1]}') no-repeat;"></div>
							<div class="back" style="background: url('<%=qiniuPath %>${fn:split(subAccount.levelLogo,',')[0]}') no-repeat;">
								<div class="number">${subAccount.levelName }</div>
								<div class="item">
									<span>项目折扣：${subAccount.projectDiscount/100 }折</span> <span>商品折扣：${subAccount.goodsDiscount/100 }折</span>
								</div>
								<div class="rest">卡上余额:${subAccount.balanceAmount }元</div>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</c:forEach>
		<div class="fix">
			<button>充值</button>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"></script>
</body>
<script>
  $(function(){ 
	  $('.flip-container').click(function(){   
	   $(this).find('.front').toggleClass('active');
	   $(this).find('.back').toggleClass('active1');
	})
  })
</script>
</html>