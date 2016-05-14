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
<title>在线商城</title>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobile/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobile/shop.css">
<script type="text/javascript" src="<%=basePath%>js/mobile/zepto.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/zepto.touchSlider.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery-1.7.1.min.js"></script>
<style type="text/css">
.hide{display:none}
</style>
</head>

<body>
	<div class="con clearfix" style="background: #e5e5e5">
		<header class="clearfix header_">
			<div class="down_content" style="background: none;" onclick="javascript:history.back(-1);">
				<img src="<%=basePath%>images/mobile/member/back_1.png" style="height: 2.5rem; width: auto">
			</div>
			<div class="search">
				<span class="search_img"><img src="<%=basePath%>images/mobile/member/search.png"></span> <input type="search" name="search" placeholder="搜索">
			</div>

			<div class="more_shop">
				<p>更多店铺</p>
				<ul>
					<li>广州分店<span>惠州旗舰店</span></li>
				</ul>
			</div>
		</header>

		<ul class="shop_list_ul">
			<c:forEach items="${goodsInfoCatagoryDtos }" var="goodsInfoCatagoryDto" varStatus="status">
				<li id="${goodsInfoCatagoryDto.categoryId }" onclick="exchange(this)">${goodsInfoCatagoryDto.categoryName }</li>
			</c:forEach>
		</ul>
		<script type="text/javascript">
			$(".shop_list_ul").children("li").eq(0).addClass("active");
		</script>
		<script type="text/javascript">
			function exchange(opt){
				var catagoryId = $(opt).attr("id");
				$(opt).addClass("active");
				$(opt).siblings().removeClass("active");
				$(".right_content").hide();
				$(".right_content[catagoryId='"+catagoryId+"']").show();
				
			}
		</script>
		<c:forEach items="${goodsInfoCatagoryDtos }" var="goodsInfoCatagoryDto" varStatus="status">
			<div class="right_content hide" catagoryId="${goodsInfoCatagoryDto.categoryId }">
			<c:forEach items="${goodsInfoCatagoryDto.goodsInfos }" var="goodsInfo">
			<a href="<%=basePath %>mobile/view/pay/goodsInfo?storeId=${goodsInfoCatagoryDto.storeId }&storeGoodsId=${goodsInfo.goodsId }">
				<div class="content_detial clearfix">
					<div class="detail_left">
						<img src="<%=picPath %>${goodsInfo.goodsImage }">
					</div>
					<div class="detail_text">
						<h2>${goodsInfo.goodsName }</h2>
						<p class="text_content">${goodsInfo.goodsDesc }</p>
						<div class="list_money">
							<span>¥${goodsInfo.goodsPrice }</span><em>已售122份</em>
						</div>
					</div>
				</div>
			</a>
			</c:forEach>
			</div>
		</c:forEach>
		<script type="text/javascript">
			$(".right_content").eq(0).removeClass("hide");
		</script>
	</div>
</body>
</html>