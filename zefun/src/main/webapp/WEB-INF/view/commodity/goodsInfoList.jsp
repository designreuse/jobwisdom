<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>

<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<script>
	//轮播
	jQuery(function() {
		var count = jQuery('.rollBox_content ul').size(), len = count * 900, now = 0;

		jQuery('.rollBox_content_ul').css('width', len);
		jQuery('.LeftButton').click(function() {
			if (now >= 1) {
				now -= 1;
				jQuery('.rollBox_content_ul').stop(true, true).animate({
					left : -900 * now
				})
			}
		})

		jQuery('.RightButton').click(function() {
			if (now <= count - 2) {
				now += 1;
				jQuery('.rollBox_content_ul').stop(true, true).animate({
					left : -900 * now
				})
			}
		})
	})

	//选择全部
	jQuery(function() {
		jQuery('.setting_first em').hover(function() {
			jQuery(this).parent().find('.setting_all').show();
			jQuery(this).find('i').addClass('active')

		}, function() {
			jQuery(this).parent().find('.setting_all').hide();
			jQuery(this).find('i').removeClass('active')

		})
	})
	// 遮罩层
	jQuery(function() {
		jQuery('.rollBox_content_ul li').hover(function() {
			jQuery(this).find('.rollBox_top_mask').stop(true, true).fadeIn();

		}, function() {
			jQuery(this).find('.rollBox_top_mask').stop(true, true).fadeOut();
		})
	})
</script>

<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right">
					<div class="setting_first">
						<a href="<%=basePath %>goods/info/setting"><button>上架</button></a>
						<span>部门 <em><i class="" id="dept">全部<img src="<%=basePath%>images/setting_down.png" style="position: relative; left: 10px; top: 1px; width: 15px"></i>
								<ul class="setting_all clearfix" style="display: none;">
									<li onclick="changeDept(0, this)">全部</li>
									<c:forEach items="${deptGoodsBaseDto }" var="deptGoodsBaseDto"><li onclick="changeDept(${deptGoodsBaseDto.deptId }, this)" deptId="${deptGoodsBaseDto.deptId }">${deptGoodsBaseDto.deptName }</li></c:forEach>
								</ul> </em></span> <img src="<%=basePath%>images/settiong_right.png" style="position: relative; left: 20px"><span>系列<em> <i class="" id="category">全部<img src="<%=basePath%>images/setting_down.png"
									style="position: relative; left: 10px; top: 1px; width: 15px"></i>
								<ul class="setting_all clearfix" style="display: none;">
									<li onclick="changeCategory(0, this)">全部</li>
									<c:forEach items="${deptGoodsBaseDto[0].goodsCategoryBaseDto }" var="goodsCategory"><li onclick="changeCategory(${goodsCategory.categoryId }, this)" categoreId="${goodsCategory.categoryId }">${goodsCategory.categoryName }</li></c:forEach>
								</ul>
						</em></span> <span class="total_text">共<i>${fn:length(goodsInfos)}个</i>项目，已完成创建<i>${hasFinish }个</i>，未完成创建<i>${fn:length(goodsInfos)-hasFinish}个</i></span>
					</div>
					<div class="rollBox">
						<div class="LeftButton">
							<img src="<%=basePath%>images/left_click.png">
						</div>
						<div class="rollBox_content">
							<div class="rollBox_content_ul" >
							<c:forEach items="${goodsInfos }" var="goodsInfo" step="12" varStatus="index">
								<c:set var="count" value="${index.count*12 }"></c:set>
								<ul>
									<c:forEach var="i" begin="0" end="11">
									<c:if test="${fn:length(goodsInfos) >= (count-i)}">
									<li>
										<div class="setting_write">
											<a href="<%=basePath %>goods/info/setting?goodsId=${goodsInfos[count-i-1].goodsId }"><img src="<%=basePath%>images/coupon_write.png"></a>
										</div>
										<div class="rollBox_top">
											<img src="<%=qiniuPath%>${goodsInfos[count-i-1].goodsImage }">
										</div>
										<div class="rollBox_center">
											<p>名称：${goodsInfos[count-i-1].goodsName }</p>
											<p>部门：${goodsInfos[count-i-1].deptName }</p>
											<p>系列：${goodsInfos[count-i-1].categoryName }</p>
										</div>
										<div class="rollBox_bottom clearfix">
											<div class="rollBox_bottom_num">
												<p>编号</p>
												<span>${goodsInfos[count-i-1].goodsCodeSuffix}</span>
											</div>
											<div class="rollBox_bottom_price">
												<p>门店价格</p>
												<span>${goodsInfos[count-i-1].goodsPrice}</span>
											</div>
										</div>
										<c:if test="${goodsInfos[count-i-1].goodsPrice == null}"><div class="setting_mask">未完成</div></c:if>
									</li>
									</c:if>
									</c:forEach>
								</ul>
							</c:forEach>
							</div>
						</div>
						<div class="RightButton">
							<img src="<%=basePath%>images/right_click.png">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>js/base/roll.js"></script>
<script>

	var deptGoodsBaseDtoJs = ${deptGoodsBaseDtoJs};
	var deptId = null;
	function selectDept(deptId){
		jQuery("#dept").text(jQuery("li[deptId='"+deptId+"']").text());
		jQuery(".setting_all").eq(1).empty();
		var html = '<li onclick="changeCategory(0, this)" categoreid="0">全部</li>';
		jQuery(".setting_all").eq(1).append(jQuery(html));
		for (var i = 0; i < deptGoodsBaseDtoJs.length; i++) {
			if (deptGoodsBaseDtoJs[i].deptId == deptId){
				for (var j = 0; j < deptGoodsBaseDtoJs[i].goodsCategoryBaseDto.length; j++) {
					var categoryId = deptGoodsBaseDtoJs[i].goodsCategoryBaseDto[j].categoryId;
					var categoryName = deptGoodsBaseDtoJs[i].goodsCategoryBaseDto[j].categoryName;
					var html = '<li onclick="changeCategory('+categoryId+', this)" categoreid="'+categoryId+'">'+categoryName+'</li>';
					jQuery(".setting_all").eq(1).append(jQuery(html));
				}
			}
		}
	}
	function selectCategory(categoryId){
		console.log(categoryId);
		var categoryName = jQuery("li[categoreId='"+categoryId+"']").text();
		console.log(categoryName);
		jQuery("#category").text(categoryName);
	}
	if ('${deptId}' != ''){
		deptId = '${deptId}';
		selectDept('${deptId}')
	}
	if ('${categoryId}' != ''){
		selectCategory('${categoryId}');
	}
	function changeDept(deptId, li){
		window.location.href = baseUrl + "goodsInfo/view/goodsInfoList?deptId=" + deptId;
	}
	function changeCategory(categoryId, li){
		jQuery("#category").text(jQuery(li).text());
		var path = baseUrl + "goodsInfo/view/goodsInfoList" + "?categoryId=" + categoryId;
		if (deptId!=null){
			path = path + "&deptId=" + deptId;
		}
		window.location.href = path;
	}
</script>
</html>