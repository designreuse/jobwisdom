<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/stock.css" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
	jQuery(function() {
		jQuery('.wages_content:gt(0)').hide();
		jQuery('.wages_content:gt(0)').hide();
		jQuery('.content_right>ul>li').click(
				function() {
					jQuery(this).addClass('active').siblings().removeClass(
							'active');
					jQuery('.wages_content').eq(jQuery(this).index()).show()
							.siblings('.wages_content').hide();
				})
	})

	jQuery(function() {
		jQuery('.wages_content_datail_top>span').click(
				function() {
					jQuery(this).addClass('active').siblings('span')
							.removeClass('active');

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
					<ul class="clearfix">
						<li class="active">库存统计</li>
						<li class="">库存查询</li>
					</ul>
					<div class="wages_content" style="display: block;">
						<div class="wages_content_datail">
							<div class="wages_content_datail_top">
								时间查询
								<input type="text" name="flowStartDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px; margin: 0 10px">
								至
								<input type="text" name="flowStopDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px; margin: 0 10px">
								<select name="flowStore">
									<c:forEach items="${storeInfos }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
								</select>
								<button onclick="serchGoodFlow()">查询</button>
							</div>
						</div>
						<ul class="stock_content clearfix">
							<li>
								<div class="number">入库数量：${inquiryCount }</div>
								<div class="money">入库金额：${inquiryAmount }</div>
								<img src="<%=basePath%>images/decorate.png">
							</li>
							<li>
								<div class="number">出库数量：${deliveringCount }</div>
								<div class="money">出库金额：${deliveringCountAmount }</div>
								<img src="<%=basePath%>images/decorate.png">
							</li>
							<li>
								<div class="number">库存总量：${amountAndCount.amcount }</div>
								<div class="money">库存总金额：${amountAndCount.amount }</div>
								<img src="<%=basePath%>images/decorate.png">
							</li>
						</ul>

						<div class="stock_content_table">
							<table style="border-right: 1px solid #bbcae0">
								<tbody id="inquiry">
									<tr>
										<td>入库项目</td>
										<td>数量</td>
										<td>金额</td>
									</tr>
									<tr>
										<td>正常入库</td>
										<td>${normalStorageCount }</td>
										<td>
											${normalStorageAmount }<img src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>调拨入库</td>
										<td>${merchandiseCount }</td>
										<td>
											${merchandiseAmount }<img src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>顾客退货</td>
										<td>${customerReturnsCount }</td>
										<td>
											${customerReturnsAmount }<img src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</tbody>
							</table>
							<table>
								<tbody id="delivering">
									<tr>
										<td>出库项目</td>
										<td>数量</td>
										<td>金额</td>
									</tr>
									<tr>
										<td>销售出库</td>
										<td>${normalDeliveryCount }</td>
										<td>
											${normalDeliveryAmount }<img src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>供应商退货</td>
										<td>${supplierReturnsCount }</td>
										<td>
											${supplierReturnsAmount }<img src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>损坏</td>
										<td>${damageCount }</td>
										<td>
											${damageAmount }<img src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>赠送</td>
										<td>${sendCount }</td>
										<td>
											${sendAmount }<img src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>领用</td>
										<td>${getCount }</td>
										<td>
											${getAmount }<img src="<%=basePath%>images/stock.png">
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>

					<div class="wages_content third" style="display: none;">
						<div class="wages_content_datail" style="padding: 0">
							<div class="wages_content_datail">
								<div class="wages_content_datail_top">
									商品大项
									<select name="category">
										<option value="0">选择一个商品大项</option>
										<c:forEach items="${goodsCategories }" var="goodsCategorie"><option value="${goodsCategorie.categoryId }">${goodsCategorie.categoryName }</option></c:forEach>
									</select>
									<select name="stockStore">
										<c:forEach items="${storeInfos }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
									</select>
									<script type="text/javascript">jQuery("select[name='stockStore']").val(storeId);</script>
									<button onclick="serchGoodStock()">查询</button>
								</div>
							</div>

						</div>
						<div class="wages_content_datail_table">
							<ul class="clearfix total_top">
								<li style="width: 146px">商品</li>
								<li style="width: 147px">编号</li>
								<li style="width: 147px">商品分类</li>
								<li style="width: 147px">单位</li>
								<li style="width: 147px">库存总数</li>
								<li style="width: 148px">成本价格</li>
								<li style="width: 147px">库存总额</li>
							</ul>
							<div class="wages_content_datail_table_">
								<table class="second">
									<tbody id="stockGoods">
										<c:forEach items="${page.results }" var="goods">
											<tr>
												<td>${goods.goodsName }</td>
												<td>${goods.goodsCodeSuffix }</td>
												<td>${goods.categoryName }</td>
												<td>${goods.unit }</td>
												<td>${goods.goodsStock }</td>
												<td>${goods.costPrice }</td>
												<td>${goods.goodsStock*goods.costPrice }</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<%@ include file="/template/page.jsp" %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
var isStock = false;
function serchGoodStock(){
	isStock = true;
	pageNo = 1;
	changePage();
	jQuery("#stockGoods").empty();
}
function changePage(){
	var storeId = jQuery("select[name='stockStore']").val();
	var data = {"storeId":storeId, "serchType":1, "pageNo":pageNo};
	var categoryId = jQuery("select[name='category']").val();
	if (categoryId != "0"){data['categoryId'] = categoryId;}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "goodsInfo/stock/view/sercher",
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
		success : function(e){
			if (isStock){
				isStock = false;
				pageNo = e.msg.pageNo;
				pageSize = e.msg.pageSize;
				totalPage = e.msg.totalPage;
				totalRecord = e.msg.totalRecord;
				unbuildPagination();
			}
			var goodsInfos = e.msg.results;
			for (var i = 0; i < goodsInfos.length; i++) {
				var goods = goodsInfos[i];
				if (goods.goodsStock == null){goods.goodsStock = 0;}
				var html = '<tr>'+
							'<td>'+goods.goodsName +'</td>'+
							'<td>'+goods.goodsCodeSuffix +'</td>'+
							'<td>'+goods.categoryName +'</td>'+
							'<td>'+goods.unit +'</td>'+
							'<td>'+goods.goodsStock +'</td>'+
							'<td>'+(goods.goodsStock*goods.costPrice) +'</td>'+
						'</tr>';
				jQuery("#stockGoods").append(jQuery(html));
			}
		}
	});
}

function serchGoodFlow(){
	var flowStartDate = jQuery("input[name='flowStartDate']").val();
	var flowStopDate = jQuery("input[name='flowStopDate']").val();
	var storeId = jQuery("select[name='flowStore']").val();
	var data = {"storeId":storeId, "serchType":2, "flowStartDate":flowStartDate, "flowStopDate":flowStopDate};
	jQuery.ajax({
		type : "post",
		url : baseUrl + "goodsInfo/stock/view/sercher",
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
		success : function(e){
			jQuery(".number").eq(0).text('入库数量：' + e.msg.inquiryCount);
			jQuery(".money").eq(0).text('入库金额：' + e.msg.inquiryAmount);
			jQuery(".number").eq(1).text('出库数量：' + e.msg.deliveringCount);
			jQuery(".money").eq(1).text('出库金额：' + e.msg.deliveringCountAmount);
			jQuery(".number").eq(2).text('库存总数量：' + e.msg.amountAndCount.amcount);
			jQuery(".money").eq(2).text('库存总金额：' + e.msg.amountAndCount.amount);
			jQuery("#inquiry").children("tr").eq(1).children("td").eq(1).text(e.msg.normalStorageCount);
			jQuery("#inquiry").children("tr").eq(1).children("td").eq(2).text(e.msg.normalStorageAmount);
			jQuery("#inquiry").children("tr").eq(2).children("td").eq(1).text(e.msg.merchandiseCount);
			jQuery("#inquiry").children("tr").eq(2).children("td").eq(2).text(e.msg.merchandiseAmount);
			jQuery("#inquiry").children("tr").eq(3).children("td").eq(1).text(e.msg.customerReturnsCount);
			jQuery("#inquiry").children("tr").eq(3).children("td").eq(2).text(e.msg.customerReturnsAmount);
			

			jQuery("#delivering").children("tr").eq(1).children("td").eq(1).text(e.msg.normalDeliveryCount);
			jQuery("#delivering").children("tr").eq(1).children("td").eq(2).text(e.msg.normalDeliveryAmount);
			jQuery("#delivering").children("tr").eq(2).children("td").eq(1).text(e.msg.supplierReturnsCount);
			jQuery("#delivering").children("tr").eq(2).children("td").eq(2).text(e.msg.supplierReturnsAmount);
			jQuery("#delivering").children("tr").eq(3).children("td").eq(1).text(e.msg.damageCount);
			jQuery("#delivering").children("tr").eq(3).children("td").eq(2).text(e.msg.damageAmount);
			jQuery("#delivering").children("tr").eq(4).children("td").eq(1).text(e.msg.sendCount);
			jQuery("#delivering").children("tr").eq(4).children("td").eq(2).text(e.msg.sendAmount);
			jQuery("#delivering").children("tr").eq(5).children("td").eq(1).text(e.msg.getCount);
			jQuery("#delivering").children("tr").eq(5).children("td").eq(2).text(e.msg.getAmount);	
		}
	});
}
</script>
</html>