<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/out.css" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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

	//切换

	jQuery(function() {
		jQuery('.out_tab_content_:gt(0)').hide();
		jQuery('.out_tab li').click(
				function() {
					jQuery(this).addClass('active').siblings().removeClass(
							'active');
					jQuery('.out_tab_content_').eq(jQuery(this).index()).show()
							.siblings().hide();

				});
	})

	//提示气泡
	jQuery(function() {
		jQuery('.overflow_text').hover(function() {
			jQuery('.fly_').html('');
			var htm = jQuery(this).html();
			jQuery(this).parent().find('.fly_').append(htm);
		}, function() {
			jQuery('.fly_').html('')
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
					<div class="out_tab">
						<ul class="clearfix">
							<li style="width: 505px" class="active">入库管理</li>
							<li style="width: 505px" class="">出库管理</li>
						</ul>
						<div class="out_tab_content">
							<div class="out_tab_content_">
								<div class="out_tab_shop">
									<button class="select_button" onclick="jQuery('.zzc2').show('800')">选择商品入库</button>
									<i><span>开始日期<input type="date"></span><span>结束日期<input type="date"></span><select><option>入库方式</option></select>
										<button class="query">查询</button></i>
								</div>

								<table class="payroll_table">
									<tbody>
										<tr>
											<td>入库时间</td>
											<td>入库方式</td>
											<td>入库明细</td>
											<td>操作</td>
										</tr>
										<c:forEach items="${inFlows }" var="flow">
										<tr>
											<td>${flow.createTime }</td>
											<td>${flow.flowType }</td>
											<td>
												<div class="overflow_text">
													<c:forEach varStatus="index" items="${flow.accountGoods }" var="goods"><span>${goods.goodsCodeSuffix } ${goods.goodsName } : <i>${fn:split(flow.stockCount, ',')[index.count-1] }</i></span></c:forEach>
												</div>
												<div class="fly_"></div></td>
											<td><span><img src="<%=basePath%>images/handle_1.png"></span><span><img src="<%=basePath%>images/handle_2.png"></span></td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="out_tab_content_" style="display: none;">
								<div class="out_tab_2">
									<button onclick="jQuery('.zzc1').show('800')">选择商品入库</button>
									<input type="text" placeholder="名称/编码">
								</div>
								<table class="payroll_table">
									<tbody>
										<tr>
											<td>调拨时间</td>
											<td>出库门店</td>
											<td>入库门店</td>
											<td>调拨明细</td>
											<td>操作</td>
										</tr>
										<c:forEach items="${outFlows }" var="flow">
										<tr>
											<td>${flow.createTime }</td>
											<td>${flow.fromStoreName }</td>
											<td>${flow.toStoreName }</td>
											<td>
												<div class="overflow_text">
													<c:forEach varStatus="index" items="${flow.accountGoods }" var="goods"><span>${goods.goodsCodeSuffix } ${goods.goodsName } : <i>${fn:split(flow.stockCount, ',')[index.count-1] }</i></span></c:forEach>
												</div>
												<div class="fly_"></div></td>
											<td><span><img src="<%=basePath%>images/handle_1.png"></span><span><img src="<%=basePath%>images/handle_2.png"></span></td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>

				</div>
			</div>

			<div class="zzc2 hide">
				<div class="out_goods">
					<p>选择入库商品</p>
					<div class="out_goods_content">
						<span>入库方式 ：<select name="flowType"><option value="正常入库">正常入库</option>
								<option value="客户退货">客户退货</option></select></span>
						<div class="textarea_saying">
							备注：
							<textarea name="stockDesc"></textarea>
						</div>
						<div class="allocation">
							<div class="allocation_ clearfix">
								<div class="allocation_left">
									<p>
										<i style="color: black; font-size: 16px; font-style: normal">商品库</i> <select>
											<option>名称/编号</option>
										</select>
									</p>
									<div class="allocation_left_table">
										<table>
											<tbody>
												<c:forEach items="${goods }" var="goods">
												<tr goodsId="${goods.goodsId }" onclick="moveGoodsInfo('.zzc2','${goods.goodsId }','${goods.goodsName }','${goods.goodsCodeSuffix }', this)">
													<td>${goods.goodsName }</td>
													<td>${goods.goodsCodeSuffix }</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<div class="allocation_right">
									
								</div>
							</div>
						</div>
						<div class="out_button">
							<button onclick="saveFlow(1, '.zzc2')">确认</button>
							<button onclick="modalHide('.zzc2')">取消</button>
						</div>
					</div>
				</div>
			</div>

			<div class="zzc1" style="display: none">
				<div class="out_goods">
					<p>选择出库商品</p>

					<div class="out_goods_content">
						<div class="textarea_saying">
							<span>出库对象：<select name="libraryObject">
									<c:forEach items="${employeeInfos }" var="employeeInfo">
										<option value="${employeeInfo.employeeId }">${employeeInfo.name }</option>
									</c:forEach>
							</select>
							</span> <span>出库方式：<select name="flowType">
									<option value="正常出库">正常出库</option>
									<option value="供应商退货">供应商退货</option>
									<option value="损坏">损坏</option>
									<option value="赠送">赠送</option>
									<option value="领用">领用</option>
							</select></span>
							<div class="textarea_saying">
								备注：
								<textarea name="stockDesc"></textarea>
							</div>
						</div>

						<div class="allocation">
							<div class="allocation_ clearfix">
								<div class="allocation_left">
									<p>
										<i style="color: black; font-size: 16px; font-style: normal">商品库</i> <select>
											<option>名称/编号</option>
										</select>
									</p>
									<div class="allocation_left_table">
										<table>
											<tbody>
												<c:forEach items="${goods }" var="goods">
												<tr goodsId="${goods.goodsId }" onclick="moveGoodsInfo('.zzc1','${goods.goodsId }','${goods.goodsName }','${goods.goodsCodeSuffix }', this)">
													<td>${goods.goodsName }</td>
													<td>${goods.goodsCodeSuffix }</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								<div class="allocation_right">
								
								</div>
							</div>
						</div>
						<div class="out_button">
							<button onclick="saveFlow(2, '.zzc1')">确认</button>
							<button onclick="modalHide('.zzc1')">取消</button>
						</div>
					</div>

				</div>
			</div>
</body>

<script>
	var storeAccount = '${session_key_store_account}';
	function moveGoodsInfo(modal, goodsId, goodsName, code, tr){
		jQuery(tr).addClass("hide");
		var html = '<div goodsId="'+goodsId+'" class="out_number">'+
						'<i onclick="backMoveGoods(\''+modal+'\','+goodsId+', this)"><img src="'+baseUrl+'images/hand_close.png"></i>'+
						'<div class="out_content">'+
							'<span>'+goodsName+'</span><em>'+code+'</em>'+
						'</div>'+
						'<div class="out_number_1">'+
							'入库数量<input type="text">'+
						'</div>'+
					'</div>';
		jQuery(modal).find(".allocation_right").append(jQuery(html));
	}
	function backMoveGoods(modal, goodsId, i){
		jQuery(i).parent().remove();
		jQuery(modal).find("tr[goodsId='"+goodsId+"']").removeClass("hide");
	}
	/**保存库存设置*/
	function saveFlow(type, modal){
		// 入库
		if(type == 1){
			var stockType = type;
			var flowType = jQuery(modal).find("select[name='flowType']").val();
			var stockDesc = jQuery(modal).find("textarea[name='stockDesc']").val();
			var aIds = "";
			var stockCount = "";
			jQuery(modal).find(".allocation_right").children("div").each(function(){
				aIds = aIds + jQuery(this).attr("goodsId") + ",";
				stockCount = stockCount + jQuery(this).find("input").val() + ",";
			});
			aIds = aIds.substring(0,(aIds.length-1));
			stockCount = stockCount.substring(0,(stockCount.length-1));
			var toStore = '${session_key_store_id}';
			var data = {"stockType":stockType, "flowType":flowType, "stockDesc":stockDesc, "aIds":aIds, "stockCount":stockCount, "toStore":toStore};
			console.log(data);
			jQuery.ajax({
				type : "post",
				url : baseUrl + "stock/action",
				data : JSON.stringify(data),
				dataType : "json",
				contentType : "application/json",
				async : false,
				success : function(data) {
					dialog(data.msg);
					modalHide(modal);
				}
			});
		}
		//出库 
		if(type == 2){
			var stockType = type;
			var flowType = jQuery(modal).find("select[name='flowType']").val();
			var stockDesc = jQuery(modal).find("textarea[name='stockDesc']").val();
			var aIds = "";
			var stockCount = "";
			jQuery(modal).find(".allocation_right").children("div").each(function(){
				aIds = aIds + jQuery(this).attr("goodsId") + ",";
				stockCount = stockCount + jQuery(this).find("input").val() + ",";
			});
			aIds = aIds.substring(0,(aIds.length-1));
			stockCount = stockCount.substring(0,(stockCount.length-1));
			var fromStore = '${session_key_store_id}';
			var libraryObject = jQuery("select[name='libraryObject']").val();
			var data = {"stockType":stockType, "flowType":flowType, "stockDesc":stockDesc, "aIds":aIds, "stockCount":stockCount, "libraryObject":libraryObject, "fromStore":fromStore};
			console.log(data);
			jQuery.ajax({
				type : "post",
				url : baseUrl + "stock/action",
				data : JSON.stringify(data),
				dataType : "json",
				contentType : "application/json",
				async : false,
				success : function(data) {
					dialog(data.msg);
					modalHide(modal);
				}
			});
		}
		//商品调拨
		if(type == 3){
			var stockType = type;
			var stockDesc = jQuery(modal).find("textarea[name='stockDesc']").val();
			var aIds = "";
			var stockCount = "";
			jQuery(modal).find(".allocation_right").children("div").each(function(){
				aIds = aIds + jQuery(this).attr("goodsId") + ",";
				stockCount = stockCount + jQuery(this).find("input").val() + ",";
			});
			aIds = aIds.substring(0,(aIds.length-1));
			stockCount = stockCount.substring(0,(stockCount.length-1));
			var fromStore = jQuery(modal).find("select[name='fromStore']").val();
			var toStore = jQuery(modal).find("select[name='toStore']").val();
			if (fromStore == toStore){dialog('自己给自己出库啊?');return;}
			var data = {"stockType":stockType, "aIds":aIds, "stockDesc":stockDesc, "stockCount":stockCount, "toStore":toStore, "fromStore":fromStore};
			console.log(data);
			jQuery.ajax({
				type : "post",
				url : baseUrl + "stock/action",
				data : JSON.stringify(data),
				dataType : "json",
				contentType : "application/json",
				async : false,
				success : function(data) {
					dialog(data.msg);
					modalHide(modal);
				}
			});
		}
	}
	function modalHide(modal){
		jQuery(modal).hide('800');
		jQuery(modal).find("tr").removeClass("hide");
		jQuery(modal).find(".allocation_right").empty();
	}
	function choseStore(li){
		jQuery(li).siblings().removeClass("active");
		jQuery(li).addClass("active");
	}
</script>
</html>