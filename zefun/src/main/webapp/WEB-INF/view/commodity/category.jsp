<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath %>css/goods_series.css" type="text/css" />
<style>
.active {
	background: #ecf7fe;
}
</style>
<script>
	//轮播

	jQuery(function() {
		var now_ = 0, count = jQuery('.out_roll_ul li').size();

		//向右走
		jQuery('.click_right').click(
				function() {
					if (now_ <= count - 6) {
						now_ += 1;
						jQuery(this).parent('').find('.out_roll_ul').stop(true,
								true).animate({
							left : -181 * now_

						})
					}
				});
		//向左走

		jQuery('.click_left').click(
				function() {
					if (now_ >= 1) {
						now_ -= 1;
						jQuery(this).parent('').find('.out_roll_ul').stop(true,
								true).animate({
							left : -181 * now_

						})
					}

				});
	});

	jQuery(function() {
		jQuery(document).on('mouseover', '.goods_series_detail li', function() {
			jQuery(this).find('p').show();
		});
		jQuery(document).on('mouseout', '.goods_series_detail li', function() {
			jQuery(this).find('p').hide();
		})
	})
	
</script>

<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right clearfix">
					<div class="out_roll_content">
						<div class="out_roll">
							<span class="click_left"><img src="<%=basePath%>images/left_click.png"></span>
							<div class="out_roll_div">
								<ul class="clearfix out_roll_ul" style="left: 0px;">
									<c:forEach items="${storeInfos }" var="storeInfo">
										<li onclick="window.location.href='<%=basePath %>project/view/categorys?storeId=${storeInfo.storeId }'" storeId="${storeInfo.storeId }">${storeInfo.storeName }</li>
									</c:forEach>
								</ul>
								<script>
								var storeId = '${storeId}';
								jQuery(".out_roll_ul").children("li[storeId="+storeId+"]").addClass("active");
								</script>
							</div>
							<span class="click_right"><img src="<%=basePath%>images/right_click.png"></span>
						</div>
					</div>

					<div class="goods_series_item">
						<div class="goods_series_item_content">
							<p>项目(大项)</p>
							<div class="goods_series_item_content_">
								<p>
									<button onclick="showSave(1);">添加</button>
								</p>
								<ul id="project" class="goods_series_detail">
									<c:forEach items="${projectCategories }" var="projectCategorie">
									<li categoryId="${projectCategorie.categoryId }" >${projectCategorie.categoryName }
										<p>
											<span onclick="showUpdate(1, ${projectCategorie.categoryId }, '${projectCategorie.categoryName }', this)">修改</span><span onclick="deleted(1, ${projectCategorie.categoryId })">删除</span>
										</p>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>

						<div class="goods_series_item_content">
							<p>商品系列</p>
							<div class="goods_series_item_content_">
								<p>
									<button onclick="showSave(2);">添加</button>
								</p>
								<ul id="goods" class="goods_series_detail">
									<c:forEach items="${goodsCategories }" var="goodsCategorie">
									<li categoryId="${goodsCategorie.categoryId }">${goodsCategorie.categoryName }
										<p style="display: none;">
											<span onclick="showUpdate(2, ${goodsCategorie.categoryId }, '${goodsCategorie.categoryName }', this)">修改</span><span onclick="deleted(2, ${goodsCategorie.categoryId })">删除</span>
										</p>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="zzc">
		<div class="zzc_goods_series">
			<p>修改商品</p>
			<div class="zzc_goods_series_content">
				<div class="zzc_goods_series_">
					<p>
						<input name="categoryName" type="text" maxlength="8">
					</p>
					<span>*不得超过8个字</span>
				</div>
				<div class="zzc_goods_series_button">
					<button onclick="save()">确定</button>
					<button onclick="hideSave()">取消</button>
				</div>
			</div>
		</div>
	</div>

</body>
<script>
var type = null;
var categoryId = null;
var categoryName = null;
function hideSave(){
	jQuery('.zzc').hide('800');
	type=null;
	categoryId=null;
}
function showSave(types){
	type = types;
	categoryId = null;
	jQuery("input[name='categoryName']").val('');
	jQuery(".zzc").show('800');
}
function save(){
	
	categoryName = jQuery("input[name='categoryName']").val();
	var data = {"type":type, "categoryId":categoryId, "categoryName":categoryName, "storeId":storeId };
	jQuery.ajax({
		type : "post",
		url : baseUrl + "project/save/update/category",
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
		async : false,
		success : function(data) {
			var html =  '<li categoryId="'+data.msg.categoryId+'">'+data.msg.categoryName+
									'<p style="display: none;">'+
								'<span onclick="showUpdate('+type+', '+data.msg.categoryId+', \''+data.msg.categoryName+'\', this)">修改</span><span>删除</span>'+
							'</p>'+
						'</li>';
			if (type == 1){
				jQuery("#project").find("li[categoryId='"+data.msg.categoryId+"']").remove();
				jQuery("#project").append(jQuery(html));
			}else {
				jQuery("#goods").find("li[categoryId='"+data.msg.categoryId+"']").remove();
				jQuery("#goods").append(jQuery(html));
			}
			jQuery(".zzc").hide('800');
		}
	});
}
function showUpdate(types, categoryIds, categoryNames, li){
	type = types;
	categoryId = categoryIds;
	jQuery("input[name='categoryName']").val(categoryNames);
	categoryName = categoryNames;
	jQuery(".zzc").show('800');
}
function deleted(types, id){
	if (confirm("确定要删除该数据么?")){
		jQuery.ajax({
			type : "post",
			url : baseUrl + "project/delete/category",
			data : "type="+types+"&categoryId="+id,
			dataType : "json",
			async : false,
			success : function(data) {
				dialog(data.msg);
				if (types == 1){
					jQuery("#project").find("li[categoryId='"+id+"']").remove();
				}else {
					jQuery("#goods").find("li[categoryId='"+id+"']").remove();
				}
			}
		});
	}
}
</script>
</html>