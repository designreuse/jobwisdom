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

	//轮播
	jQuery(function() {
		var now_ = 0, count = jQuery('.roll li').size();
		//向右走
		jQuery('.hand_right').click(function() {
			if (now_ <= count - 6) {
				now_ += 1;
				jQuery(this).parent().find('ul').stop(true, true).animate({
					left : -73 * now_
				})
			}
		});

		//向左走
		jQuery('.hand_left').click(function() {
			if (now_ >= 1) {
				now_ -= 1;
				jQuery(this).parent().find('ul').stop(true, true).animate({
					left : -73 * now_
				})
			}
		});
	});

	//选中
	jQuery(function() {
		jQuery('.roll li').click(function() {
			jQuery(this).addClass('active').siblings().removeClass('active')
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
					<ul class="clearfix" id="ul">
						<li class="active">库存统计</li>
						<li>库存查询</li>
						<li>出入库单</li>
						<li>出入库单据</li>
						<li>出入库明细</li>
					</ul>
					<div class="wages_content" style="display: block;">
						<div class="wages_content_datail">
							<div class="wages_content_datail_top">
								时间查询
								<input type="text" name="flowStartDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px; margin: 0 10px">
								至
								<input type="text" name="flowStopDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px; margin: 0 10px">
								<select name="flowStore">
									<c:forEach items="${storeInfos }" var="storeInfo">
										<option value="${storeInfo.storeId }">${storeInfo.storeName }</option>
									</c:forEach>
								</select>
								<button onclick="serchGoodFlow()">查询</button>
							</div>
						</div>

						<div class="wages_content_top clearfix">
							<div class="wages_content_top_left">
								<span>总量</span><em>库存金额：${amountAndCount.amount }</em>
							</div>
							<div class="wages_content_top_right">
								<span>库存数量:${amountAndCount.amcount }</span>
							</div>
						</div>

						<ul class="stock_content clearfix">
							<li>
								<div class="number">入库数量：${result.inquiryCount }</div>
								<div class="money">入库金额：${result.inquiryAmount }</div>
								<img src="<%=basePath%>images/decorate.png">
							</li>
							<li>
								<div class="number">出库数量：${result.deliveringCount }</div>
								<div class="money">出库金额：${result.deliveringCountAmount }</div>
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
										<td>${result.normalStorageCount }</td>
										<td>
											${result.normalStorageAmount }<img onclick="clickPostFlow(1, '正常入库')" src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>顾客退货</td>
										<td>${result.customerReturnsCount }</td>
										<td>
											${result.customerReturnsAmount }<img onclick="clickPostFlow(1, '客户退货')" src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>调拨入库</td>
										<td>${result.merchandiseCount }</td>
										<td>
											${result.merchandiseAmount }<img onclick="clickPostFlow(3, '商品调拨')" src="<%=basePath%>images/stock.png">
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
									<tr>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</tbody>
							</table>
							<script>
								function clickPostFlow(stockType, flowType){
									jQuery("select[name='serchStockType']").val(stockType);
									serchStockType(stockType);
									jQuery("select[name='serchStockFlowType']").val(flowType);
									var flowStartDate = jQuery("input[name='flowStartDate']").val();
									var flowStopDate = jQuery("input[name='flowStopDate']").val();
									if (flowStartDate == ""){flowStartDate = "1970-01-01";}
									if (flowStopDate == ""){flowStopDate = "2050-01-01";}
									jQuery("input[name='postageSerchStartDate']").val(flowStartDate);
									jQuery("input[name='postageSerchStopDate']").val(flowStopDate);
									jQuery("select[name='postageStore']").val(jQuery("select[name='flowStore']").val());
									jQuery("#ul").children("li").eq(3).click();
									serchPostage();
								}
							</script>
							<table>
								<tbody id="delivering">
									<tr>
										<td>出库项目</td>
										<td>数量</td>
										<td>金额</td>
									</tr>
									<tr>
										<td>销售出库</td>
										<td>${result.normalDeliveryCount }</td>
										<td>
											${result.normalDeliveryAmount }<img onclick="clickPostFlow(2, '销售出库')" src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>供应商退货</td>
										<td>${result.supplierReturnsCount }</td>
										<td>
											${result.supplierReturnsAmount }<img onclick="clickPostFlow(2, '供应商退货')" src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>损坏</td>
										<td>${result.damageCount }</td>
										<td>
											${result.damageAmount }<img onclick="clickPostFlow(2, '损坏')" src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>赠送</td>
										<td>${result.sendCount }</td>
										<td>
											${result.sendAmount }<img onclick="clickPostFlow(2, '赠送')" src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>领用</td>
										<td>${result.getCount }</td>
										<td>
											${result.getAmount }<img onclick="clickPostFlow(2, '领用')" src="<%=basePath%>images/stock.png">
										</td>
									</tr>
									<tr>
										<td>商品调拨</td>
										<td>${result.deliveringDbCount }</td>
										<td>
											${result.deliveringDbAmount }<img onclick="clickPostFlow(3, '商品调拨')" src="<%=basePath%>images/stock.png">
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
										<c:forEach items="${goodsCategories }" var="goodsCategorie">
											<option value="${goodsCategorie.categoryId }">${goodsCategorie.categoryName }</option>
										</c:forEach>
									</select>
									<select name="stockStore">
										<c:forEach items="${storeInfos }" var="storeInfo">
											<option value="${storeInfo.storeId }">${storeInfo.storeName }</option>
										</c:forEach>
									</select>
									<script type="text/javascript">
										jQuery("select[name='stockStore']").val(storeId);
									</script>
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
								<%@ include file="/template/page.jsp"%>
							</div>
						</div>
					</div>

					<div class="wages_content">
						<div class="wages_content_datail clearfix">
							<div class="wages_content_datail_left">
								<span>出/入库类型：
									<select name="stockType" onchange="selectShowEmSpan(this.value)">
										<option value="1">入库</option>
										<option value="2">出库</option>
										<c:if test="${storeId != '' }"><option value="3">调拨</option></c:if>
									</select>
								</span>
								<em id="em"> 
									<span><em>入库名称:</em>
										<select name="flowType">
											<option value="正常入库">正常入库</option>
											<option value="客户退货">客户退货</option>
										</select>
									</span>
									<span><em>入库门店:</em>
										<select name="toStore">
											<c:forEach items="${storeInfos }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
										</select>
									</span>
									<span style="display: none;">出库门店：
										<select name="fromStore" onchange="changeFromStore(this.value)">
											<c:forEach items="${storeInfos }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
										</select>
									</span>
								</em>
							</div>
							<div class="wages_content_datail_right">
								<span>负责人：
									<select name="libraryObject">
										<c:forEach items="${storeInfoDtos[0].employeeInfos }" var="employeeInfo">
											<option value="${employeeInfo.employeeId }">${employeeInfo.name }</option>
										</c:forEach>
									</select>
								</span>
							</div>
						</div>
						<div class="wages_content_apartment clearfix">
							<div class="wages_content_apartment_left">
								<p>
									<select name="supplier" onchange="changeSuppiler(this.value)"></select>
								</p>
								<div class="wages_content_apartment_left_content">
									<div class="roll_bottom" name="accountGoods">
									<!-- 商品信息 -->
									</div>
								</div>
							</div>
							<div class="wages_content_apartment_right">
								<p>
									<button>导入模板下载</button>
									<button style="width: 100px">导入</button>
								</p>
								<div class="wages_content_apartment_right_content clearfix" id="left">
									<!-- 进出的商品信息 -->
								</div>
							</div>
						</div>

						<div class="remarks">
							备注
							<input type="text" name="stockDesc">
						</div>

						<div class="stock_button">
							<button onclick="saveFlow()">确定</button>
							<button>刷新</button>
						</div>
					</div>

					<div class="wages_content">
						<div class="wages_content_datail" style="padding: 0">
							<div class="wages_content_datail">
								<div class="wages_content_datail_top four">
									<input type="text" name="postageSerchStartDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px; margin: 0 10px">
									至
									<input type="text" name="postageSerchStopDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px; margin: 0 10px">
									单号：
									<input type="text" style="width: 100px; margin: 0 10px">
									出入库类型
									<select name="serchStockType" onchange="serchStockType(this.value)">
										<option value="0">选择出入库</option>
										<option value="1">入库</option>
										<option value="2">出库</option>
										<option value="3">调拨</option>
									</select>
									出库名称：
									<select name="serchStockFlowType">
										<option value="0">全部</option>
									</select>
									选择门店：
									<select name="postageStore">
										<option value="0">全部门店</option>
										<c:forEach items="${storeInfos }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
									</select>
									<button onclick="serchPostage()">搜索</button>
								</div>
							</div>

						</div>
						<div class="wages_content_datail_table four_">
							<ul class="clearfix total_top">
								<li>单号</li>
								<li>出入库类型</li>
								<li style="width: 103px">出库门店</li>
								<li style="width: 103px">入库门店</li>
								<li style="width: 103px">数量</li>
								<li style="width: 103px">总额</li>
								<li style="width: 103px">负责人</li>
								<li style="width: 103px">发生时间</li>
								<li style="width: 206px">备注</li>
							</ul>
							<div class="wages_content_datail_table_">
								<table class="second" id="serchStockFlows">
									
								</table>
							</div>
							<script>
								function selectFlowNumber(flowNumber){
									jQuery("input[name='detailFlowNumber']").val(flowNumber);
									jQuery("select[name='detailFlowType']").val(jQuery("select[name='postageStore']").val());
									jQuery("#ul").children("li").eq(4).click();
									serchDetailStore();
								}
							</script>
						</div>
					</div>
					<div class="wages_content">
						<div class="wages_content_datail" style="padding: 0">
							<div class="wages_content_datail">
								<div class="wages_content_datail_top four">
									<input type="text" name="detailStartDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px; ">
									至
									<input type="text" name="detailStopDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 100px; ">
									单号
									<input type="text" name="detailFlowNumber" style="width: 100px;">
									商品
									<select name="aId">
										<option value="0">全部</option>
										<c:forEach items="${accountGoods }" var="accountGood"><option value="${accountGood.goodsId }">${accountGood.goodsName }</option></c:forEach>
									</select>
									出/入库类型
									<select name="detailStockType" onchange="serchDetailFlowType(this.value)">
										<option value="0">全部</option>
										<option value="1">入库</option>
										<option value="2">出库</option>
										<option value="3">商品调拨</option>
									</select>
									出/入库名称
									<select name="detailFlowType">
										<option value="0">全部类型</option><option value="正常入库">正常入库</option><option value="客户退货">客户退货</option>
									</select>
									所属门店
									<select name="detailStore">
										<option value="0">全部门店</option>
										<c:forEach items="${storeInfos }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
									</select>
									<button onclick="serchDetailStore()">搜索</button>
								</div>
							</div>

						</div>
						<div class="wages_content_datail_table">
							<ul class="clearfix total_top five">
								<li style="width: 102px">单号</li>
								<li style="width: 102px">商品编号/名称</li>
								<li style="width: 102px">规格</li>
								<li style="width: 103px">出/入库类型</li>
								<li style="width: 103px">出/入库名称</li>
								<li style="width: 103px">数量</li>
								<li style="width: 103px">成本</li>
								<li style="width: 103px">合计</li>
								<li style="width: 103px">负责人</li>
								<li style="width: 103px">发生时间</li>
							</ul>
							<div class="wages_content_datail_table_ five_">
								<table class="second" id="detailFlowTable">
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script>
	var storeId = '${storeId}';
	if (storeId != '') {
		jQuery("select[name='flowStore']").val(storeId);
		jQuery("select[name='flowStore']").hide();
		jQuery("select[name='stockStore']").val(storeId);
		jQuery("select[name='stockStore']").hide();
		jQuery("select[name='postageStore']").val(storeId);
		jQuery("select[name='postageStore']").hide();
		jQuery("select[name='detailStore']").val(storeId);
		jQuery("select[name='detailStore']").hide();
	}
	var isStock = false;
	function serchGoodStock() {
		isStock = true;
		pageNo = 1;
		changePage();
		jQuery("#stockGoods").empty();
	}
	function changePage() {
		var storeId = jQuery("select[name='stockStore']").val();
		var data = {
			"storeId" : storeId,
			"serchType" : 1,
			"pageNo" : pageNo
		};
		var categoryId = jQuery("select[name='category']").val();
		if (categoryId != "0") {
			data['categoryId'] = categoryId;
		}
		jQuery.ajax({
			type : "post",
			url : baseUrl + "goodsInfo/stock/view/sercher",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			success : function(e) {
				if (isStock) {
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
					if (goods.goodsStock == null) {
						goods.goodsStock = 0;
					}
					var html = '<tr>' + '<td>' + goods.goodsName + '</td>'
							+ '<td>' + goods.goodsCodeSuffix + '</td>' + '<td>'
							+ goods.categoryName + '</td>' + '<td>'
							+ goods.unit + '</td>' + '<td>' + goods.goodsStock
							+ '</td>' + '<td>' + goods.costPrice + '</td>'
							+ '<td>' + (goods.goodsStock * goods.costPrice)
							+ '</td>' + '</tr>';
					jQuery("#stockGoods").append(jQuery(html));
				}
			}
		});
	}

	function serchGoodFlow() {
		var flowStartDate = jQuery("input[name='flowStartDate']").val();
		var flowStopDate = jQuery("input[name='flowStopDate']").val();
		if (flowStartDate == ""){flowStartDate = "1970-01-01";}
		if (flowStopDate == ""){flowStopDate = "2050-01-01";}
		var storeId = jQuery("select[name='flowStore']").val();
		var data = {
			"storeId" : storeId,
			"serchType" : 2,
			"flowStartDate" : flowStartDate,
			"flowStopDate" : flowStopDate
		};
		jQuery.ajax({
			type : "post",
			url : baseUrl + "goodsInfo/stock/view/sercher",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			success : function(e) {
				jQuery(".number").eq(0).text('入库数量：' + e.msg.result.inquiryCount);
				jQuery(".money").eq(0).text('入库金额：' + e.msg.result.inquiryAmount);
				jQuery(".number").eq(1).text('出库数量：' + e.msg.result.deliveringCount);
				jQuery(".money").eq(1).text('出库金额：' + e.msg.result.deliveringCountAmount);

				jQuery(".wages_content_top_left").find("em").text("库存金额：" + e.msg.amountAndCount.amount);
				jQuery(".wages_content_top_right").find("span").text("库存数量: " + e.msg.amountAndCount.amcount);
				
				jQuery("#inquiry").children("tr").eq(1).children("td").eq(1).text(e.msg.result.normalStorageCount);
				jQuery("#inquiry").children("tr").eq(1).children("td").eq(2).text(e.msg.result.normalStorageAmount);
				
				jQuery("#inquiry").children("tr").eq(1).children("td").eq(2).append(jQuery('<img onclick="clickPostFlow(1, \'正常入库\')" src="'+baseUrl+'images/stock.png">'));
				jQuery("#inquiry").children("tr").eq(2).children("td").eq(1).text(e.msg.result.customerReturnsCount);
				jQuery("#inquiry").children("tr").eq(2).children("td").eq(2).text(e.msg.result.customerReturnsAmount);
				jQuery("#inquiry").children("tr").eq(2).children("td").eq(2).append(jQuery('<img onclick="clickPostFlow(1, \'客户退货\')" src="'+baseUrl+'images/stock.png">'));
				jQuery("#inquiry").children("tr").eq(3).children("td").eq(1).text(e.msg.result.merchandiseCount);
				jQuery("#inquiry").children("tr").eq(3).children("td").eq(2).text(e.msg.result.merchandiseAmount);
				jQuery("#inquiry").children("tr").eq(3).children("td").eq(2).append(jQuery('<img onclick="clickPostFlow(3, \'商品调拨\')" src="'+baseUrl+'images/stock.png">'));

				jQuery("#delivering").children("tr").eq(1).children("td").eq(1).text(e.msg.result.normalDeliveryCount);
				jQuery("#delivering").children("tr").eq(1).children("td").eq(2).text(e.msg.result.normalDeliveryAmount);
				jQuery("#delivering").children("tr").eq(1).children("td").eq(2).append(jQuery('<img onclick="clickPostFlow(3, \'销售出库\')" src="'+baseUrl+'images/stock.png">'));
				jQuery("#delivering").children("tr").eq(2).children("td").eq(1).text(e.msg.result.supplierReturnsCount);
				jQuery("#delivering").children("tr").eq(2).children("td").eq(2).text(e.msg.result.supplierReturnsAmount);
				jQuery("#delivering").children("tr").eq(2).children("td").eq(2).append(jQuery('<img onclick="clickPostFlow(3, \'供应商退货\')" src="'+baseUrl+'images/stock.png">'));
				jQuery("#delivering").children("tr").eq(3).children("td").eq(1).text(e.msg.result.damageCount);
				jQuery("#delivering").children("tr").eq(3).children("td").eq(2).text(e.msg.result.damageAmount);
				jQuery("#delivering").children("tr").eq(3).children("td").eq(2).append(jQuery('<img onclick="clickPostFlow(3, \'损坏\')" src="'+baseUrl+'images/stock.png">'));
				jQuery("#delivering").children("tr").eq(4).children("td").eq(1).text(e.msg.result.sendCount);
				jQuery("#delivering").children("tr").eq(4).children("td").eq(2).text(e.msg.result.sendAmount);
				jQuery("#delivering").children("tr").eq(4).children("td").eq(2).append(jQuery('<img onclick="clickPostFlow(3, \'赠送\')" src="'+baseUrl+'images/stock.png">'));
				jQuery("#delivering").children("tr").eq(5).children("td").eq(1).text(e.msg.result.getCount);
				jQuery("#delivering").children("tr").eq(5).children("td").eq(2).text(e.msg.result.getAmount);
				jQuery("#delivering").children("tr").eq(5).children("td").eq(2).append(jQuery('<img onclick="clickPostFlow(3, \'领用\')" src="'+baseUrl+'images/stock.png">'));
				jQuery("#delivering").children("tr").eq(6).children("td").eq(1).text(e.msg.result.deliveringDbCount);
				jQuery("#delivering").children("tr").eq(6).children("td").eq(2).text(e.msg.result.deliveringDbAmount);
				jQuery("#delivering").children("tr").eq(6).children("td").eq(2).append(jQuery('<img onclick="clickPostFlow(3, \'商品调拨\')" src="'+baseUrl+'images/stock.png">'));
			}
		});
	}
	
	var storeInfoDtosJs = eval('('+'${storeInfoDtosJs}'+')');
	/** 改变出库对象*/
	function changeFromStore(storeIds){
		jQuery("select[name='libraryObject']").empty();
		for (var i = 0; i < storeInfoDtosJs.length; i++) {
			if (storeInfoDtosJs[i].storeId == storeIds){
				for (var j = 0; j < storeInfoDtosJs[i].employeeInfos.length; j++) {
					var emp = storeInfoDtosJs[i].employeeInfos[j];
					var employeeId = emp.employeeId;
					var employeeName = emp.name;
					var html = '<option value="'+employeeId+'">'+employeeName+'</option>';
					jQuery("select[name='libraryObject']").append(jQuery(html));
				}
			}
		}
	}
	/** 切换出入库对象选项卡*/
	function selectShowEmSpan(stockType){
		if (stockType == 1){
			//入库
			jQuery("select[name='flowType']").empty();
			var html = '<option value="正常入库">正常入库</option><option value="客户退货">客户退货</option>';
			jQuery("select[name='flowType']").append(jQuery(html));
			
			jQuery("#em").children("span").eq(0).show();
			jQuery("#em").children("span").eq(0).children("em").text("入库名称:");
			jQuery("#em").children("span").eq(1).show();
			jQuery("#em").children("span").eq(2).hide();
		}else if (stockType == 2){
			//出库
			jQuery("select[name='flowType']").empty();
			var html = '<option value="正常出库">正常出库</option><option value="供应商退货">供应商退货</option><option value="损坏">损坏</option><option value="赠送">赠送</option><option value="领用">领用</option>';
			jQuery("select[name='flowType']").append(jQuery(html));
			
			jQuery("#em").children("span").eq(0).show();
			jQuery("#em").children("span").eq(0).children("em").text("出库名称:");
			jQuery("#em").children("span").eq(2).show();
			jQuery("#em").children("span").eq(1).hide();
		}else {
			//商品调拨
			jQuery("#em").children("span").eq(0).hide();
			jQuery("#em").children("span").eq(2).show();
			jQuery("#em").children("span").eq(1).show();
		}
	}
	// 商品信息
	var supplierInfoDtos = eval('('+'${supplierInfoDtos}'+')');
	for (var i = 0; i < supplierInfoDtos.length; i++) {
		var html = '<option value="'+supplierInfoDtos[i].supplierId+'">'+supplierInfoDtos[i].supplierName+'</option>';
		jQuery("select[name='supplier']").append(jQuery(html));
	}
	
	function changeSuppiler(supplierIds){
		jQuery("div[name='accountGoods']").empty();
		for (var i = 0; i < supplierInfoDtos.length; i++) {
			if (supplierInfoDtos[i].supplierId == supplierIds){
				for (var j = 0; j < supplierInfoDtos[i].accountGoods.length; j++) {
					var accountGoods = supplierInfoDtos[i].accountGoods[j];
					var html = '<div goodsId="'+accountGoods.goodsId+'" code="'+accountGoods.goodsCodeSuffix+'" onclick="toLeft(this)" class="roll_bottom_content">'+
									'<p>'+accountGoods.goodsName+'</p>'+
									'<span>计量单位:<em>'+accountGoods.unit+'</em></span>'+
								'</div>';
					jQuery("div[name='accountGoods']").append(jQuery(html));
					
				}
			}
		}
	}
	changeSuppiler(supplierInfoDtos[0].supplierId);
	
	function toLeft(div){
		var goodsId = jQuery(div).attr("goodsId");
		var goodsName = jQuery(div).children("p").text();
		var code = jQuery(div).attr("code");
		var unit = jQuery(div).find("em").text();
		var html = '<div class="wages_content_apartment_right_content_detail">'+
							'<img onclick="toRight(this)" src="'+baseUrl+'images/close1.png">'+
						'<div class="detail_left">'+
							'<p>'+goodsName+'</p>'+
							'<span>'+code+'</span>'+
						'</div>'+
						'<div class="detail_right">'+
							'<input type="hidden" name="goodsId" value="'+goodsId+'">'+
							'<input type="text" name="stockGoodsNum">'+unit+
						'</div>'+
					'</div>';
		jQuery("#left").append(jQuery(html));
		jQuery(div).hide('500');
	}
	function toRight(img){
		var goodsId = jQuery(img).parent().find("input[name='goodsId']").val();
		jQuery("div[goodsId='"+goodsId+"']").show('500');
		jQuery(img).parent().remove();
	}
	function saveFlow(){
		var stockType = jQuery("select[name='stockType']").val();
		var stockDesc = jQuery("input[name='stockDesc']").val();
		var toStore = jQuery("select[name='toStore']").val();
		var fromStore = jQuery("select[name='fromStore']").val();
		var flowType = jQuery("select[name='flowType']").val();
		var libraryObject = jQuery("select[name='libraryObject']").val();
		var aIds = '';
		var stockCount = '';
		jQuery("input[name='stockGoodsNum']").each(function(){
			aIds += "," + jQuery(this).prev().val();
			stockCount += "," + jQuery(this).val();
		});
		var data = null;
		if (stockType == 1){
			data = {"stockType":stockType, "flowType":flowType, "stockDesc":stockDesc, 
					"aIds":aIds.substring(1, aIds.length), "stockCount":stockCount.substring(1, stockCount.length), 
					"toStore":toStore};
		}
		if (stockType == 2){
			data = {"stockType":stockType, "flowType":flowType, "stockDesc":stockDesc, 
					"aIds":aIds.substring(1, aIds.length), "stockCount":stockCount.substring(1, stockCount.length), 
					"libraryObject":libraryObject, "fromStore":fromStore};
		}
		if (stockType == 3){
			data = {"stockType":stockType, flowType:"商品调拨", "aIds":aIds.substring(1, aIds.length), 
					"stockDesc":stockDesc, "stockCount":stockCount.substring(1, stockCount.length), 
					"toStore":toStore, "fromStore":fromStore};
		}
		jQuery.ajax({
			type : "post",
			url : baseUrl + "stock/action",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(data) {
				if (data.code != 0){
					dialog(data.msg);
				}else {
					dialog(data.msg);
					/* window.location.href = baseUrl + "goodsInfo/stock/view/sercher"; */
				}
			}
		});
	}
	/** 单据搜索库存信息*/
	function serchStockType(stockTypes){
		jQuery("select[name='serchStockFlowType']").empty();
		var html = '';
		if (stockTypes == 0){
			return;
		}
		if (stockTypes == 1){
			html = '<option value="0">全部类型</option><option value="正常入库">正常入库</option><option value="客户退货">客户退货</option>';
		}
		if (stockTypes == 2){
			html = '<option value="0">全部类型</option><option value="正常出库">正常出库</option><option value="供应商退货">供应商退货</option><option value="损坏">损坏</option><option value="赠送">赠送</option><option value="领用">领用</option>';
		}
		if (stockTypes == 3){
			html = '<option value="0">全部类型</option><option value="商品调拨">商品调拨</option>';
		}
		jQuery("select[name='serchStockFlowType']").append(jQuery(html));
	}
	/** 查询单据的库存信息*/
	function serchPostage(){
		var toStore = jQuery("select[name='postageStore']").val();
		var flowStartDate = jQuery("input[name='postageSerchStartDate']").val();
		var flowStopDate = jQuery("input[name='postageSerchStopDate']").val();
		var stockType = jQuery("select[name='serchStockType']").val();
		var flowType = jQuery("select[name='serchStockFlowType']").val();
		var data = {};
		if (toStore!="0"){data['toStore'] = toStore;}
		if (flowStartDate==""){flowStartDate = "1970-01-01"}
		if (flowStopDate==""){flowStopDate = "2050-01-01"}
		if (stockType!="0"){data['stockType'] = stockType;}
		if (flowType!="0"){data['flowType'] = flowType;}
		data['flowStartDate'] = flowStartDate;
		data['flowStopDate'] = flowStopDate;
		data['serchType'] = 3;
		jQuery.ajax({
			type : "post",
			url : baseUrl + "goodsInfo/stock/view/sercher",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			success : function(e) {
				var stockFlows = e.msg;
				jQuery("#serchStockFlows").empty();
				for (var i = 0; i < stockFlows.length; i++) {
					var html = '<tr>'+
							'<td onclick="selectFlowNumber(\''+stockFlows[i].flowNumber+'\')">'+stockFlows[i].flowNumber+'</td>'+
							'<td>'+stockFlows[i].flowType+'</td>'+
							'<td>'+stockFlows[i].fromStoreName+'</td>'+
							'<td>'+stockFlows[i].toStoreName+'</td>'+
							'<td>'+stockFlows[i].count+'</td>'+
							'<td>'+stockFlows[i].goodsAmount+'</td>'+
							'<td>'+stockFlows[i].employeeName+'</td>'+
							'<td>'+stockFlows[i].createTime+'</td>'+
							'<td style="width: 200px">'+stockFlows[i].stockDesc+'</td>'+
						'</tr>';
					jQuery("#serchStockFlows").append(jQuery(html));
				}
			}
		});
	}

	/** 单据明细库存信息*/
	function serchDetailFlowType(stockTypes){
		jQuery("select[name='detailFlowType']").empty();
		var html = '';
		if (stockTypes == 0){
			return;
		}
		if (stockTypes == 1){
			html = '<option value="0">全部类型</option><option value="正常入库">正常入库</option><option value="客户退货">客户退货</option>';
		}
		if (stockTypes == 2){
			html = '<option value="0">全部类型</option><option value="正常出库">正常出库</option><option value="供应商退货">供应商退货</option><option value="损坏">损坏</option><option value="赠送">赠送</option><option value="领用">领用</option>';
		}
		if (stockTypes == 3){
			html = '<option value="0">全部类型</option><option value="商品调拨">商品调拨</option>';
		}
		jQuery("select[name='detailFlowType']").append(jQuery(html));
	}
	function serchDetailStore(){
		var data = {};
		var detailStartDate = jQuery("input[name='detailStartDate']").val();
		var detailStopDate = jQuery("input[name='detailStopDate']").val();
		var detailFlowNumber = jQuery("input[name='detailFlowNumber']").val();
		var aId = jQuery("select[name='aId']").val();
		var stockType = jQuery("select[name='detailStockType']").val();
		var flowType = jQuery("select[name='detailFlowType']").val();
		var toStore = jQuery("select[name='detailStore']").val();
		if (detailStartDate == "") detailStartDate = "1970-01-01";
		if (detailStopDate == "") detailStopDate = "2050-01-01";
		data['detailStartDate'] = detailStartDate;
		data['detailStopDate'] = detailStopDate;
		if (detailFlowNumber != ""){data['flowNumber'] = detailFlowNumber;}
		if (aId != "0"){data['aId'] = aId;}
		if (stockType != "0"){data['stockType'] = stockType;}
		if (flowType != "0"){data['flowType'] = flowType;}
		if (toStore != "0"){data['toStore'] = toStore;}
		data['serchType'] = 4;
		jQuery.ajax({
			type : "post",
			url : baseUrl + "goodsInfo/stock/view/sercher",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			success : function(e) {
				jQuery("#detailFlowTable").empty();
				var stockFlowDetails = e.msg;
				for (var i = 0; i < stockFlowDetails.length; i++) {
					var stockFlowDetail = stockFlowDetails[i];
					if (stockFlowDetail.employeeName == null) stockFlowDetail.employeeName = '';
					if (stockFlowDetail.stockType == 1) stockFlowDetail.stockType = "入库";
					if (stockFlowDetail.stockType == 2) stockFlowDetail.stockType = "出库";
					if (stockFlowDetail.stockType == 3) stockFlowDetail.stockType = "调拨";
					var html = '<tr>'+
									'<td>'+stockFlowDetail.flowNumber+'</td>'+
									'<td>'+stockFlowDetail.accountGoods.goodsName+'</td>'+
									'<td>'+stockFlowDetail.accountGoods.unit+'</td>'+
									'<td>'+stockFlowDetail.stockType+'</td>'+
									'<td>'+stockFlowDetail.flowType+'</td>'+
									'<td>'+stockFlowDetail.flowCount+'</td>'+
									'<td>'+stockFlowDetail.flowAmount+'</td>'+
									'<td>'+stockFlowDetail.accountGoods.costPrice+'</td>'+
									'<td>'+stockFlowDetail.employeeName+'</td>'+
									'<td>'+stockFlowDetail.createTime+'</td>'+
								'</tr>';
					jQuery("#detailFlowTable").append(jQuery(html));
				}
			}
		});
	}
</script>
</html>