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
		jQuery('.clearfix.b >li').click(
				function() {
					jQuery(this).addClass('active').siblings().removeClass(
							'active');
					jQuery('.out_tab_content_').eq(jQuery(this).index()).show()
							.siblings().hide();

				});
	})

	
	
	//提示气泡
	jQuery(function() {
		
		jQuery("body").delegate(".overflow_text", "mouseover", function (){
			jQuery('.fly_').html('');
			var htm = jQuery(this).html();
			jQuery(this).parent().find('.fly_').append(htm);
		});
		jQuery("body").delegate(".overflow_text", "mouseout", function (){
			jQuery('.fly_').html('')
		});
	})
	//提示气泡
// 	jQuery(function() {
// 		jQuery('.overflow_text').hover(function() {
// 			jQuery('.fly_').html('');
// 			var htm = jQuery(this).html();
// 			jQuery(this).parent().find('.fly_').append(htm);
// 		}, function() {
// 			jQuery('.fly_').html('')
// 		})
// 	})
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
								<ul class="clearfix out_roll_ul">
									<c:forEach items="${storeInfos }" var="store">
										<li onclick="choseStore(this)" storeId="${store.storeId }" >${store.storeName }</li>
									</c:forEach>
									<script>
										jQuery(".clearfix.out_roll_ul").children("li").eq(0).addClass("active");
									</script>
								</ul>
							</div>
							<span class="click_right"><img src="<%=basePath%>images/right_click.png"></span>
						</div>
					</div>

					<div class="out_tab">
						<ul class="clearfix b" id="type">
							<li class="active">入库管理</li>
							<li class="">出库管理</li>
							<li class="">商品调拨管理</li>
						</ul>
						<div class="out_tab_content">
							<div class="out_tab_content_">
								<div class="out_tab_shop">
									<button class="select_button" onclick="jQuery('.zzc2').show('800')">选择商品入库</button>
									<!-- <i><span>开始日期<input type="date"></span><span>结束日期<input type="date"></span><select><option>入库方式</option></select>
										<button class="query">查询</button></i> -->
								</div>

								<table class="payroll_table" id="intable1">
									<tbody>
										<tr>
											<td>入库时间</td>
											<td>入库方式</td>
											<td>入库明细</td>
										</tr>
	
									</tbody>
								</table>
								<div class="fenye" id="fenye1">
										<span>共找到了<i name="result1">0</i>条数据, 共 <i name="page1">0</i> 页</span>
										<ul id="pagination-demo" class="pagination pagination-sm">
										<li class="first disabled" onclick="upPage(1,-2,event)"><a   href="#">首页</a></li><li class="prev disabled" id="upSize1" onclick="upPage(1,-1,event)"><a>上一页</a></li><li class="next disabled"><a href="#" onclick="upPage(1,1,event)">下一页</a></li><li class="last disabled" onclick="upPage(1,2,event)"><a href="#" >尾页</a></li></ul>
								</div>
							</div>
								
							<div class="out_tab_content_" style="display: none;">
								<div class="out_tab_2">
									<button onclick="jQuery('.zzc1').show('800')">选择商品出库</button>
									<input type="text" placeholder="名称/编码">
								</div>
								<table class="payroll_table" id="intable2">
									<tbody>
										<tr>
											<td>出库时间</td>
											<td>出库对象</td>
											<td>出库方式</td>
											<td>调拨明细</td>
										</tr>

									</tbody>
								</table>
									<div class="fenye" id="fenye2">
										<span>共找到了<i name="result2">0</i>条数据, 共 <i name="page2">0</i> 页</span>
										<ul id="pagination-demo" class="pagination pagination-sm">
										<li class="first disabled" onclick="upPage(2,-2,event)"><a   href="#">首页</a></li><li class="prev disabled" id="upSize2" onclick="upPage(2,-1,event)"><a  >上一页</a></li><li class="next disabled" onclick="upPage(2,1,event)"><a href="#" >下一页</a></li><li class="last disabled" onclick="upPage(2,2,event)"><a href="#" >尾页</a></li></ul>
									</div>
							</div>
							<div class="out_tab_content_" style="display: none;">
								<div class="out_tab_2">
									<button onclick="jQuery('.zzc').show('800')">选择调拨商品</button>
									<input type="text" placeholder="名称/编码">
								</div>
								<table class="payroll_table" id="intable3">
									<tbody>
										<tr>
											<td>调拨时间</td>
											<td>出库门店</td>
											<td>入库门店</td>
											<td>调拨明细</td>

										</tr>

									</tbody>
								</table>
									<div class="fenye" id="fenye3">
										<span>共找到了<i name="result3">0</i>条数据, 共 <i name="page3">0</i> 页</span>
										<ul id="pagination-demo" class="pagination pagination-sm">
										<li class="first disabled" onclick="upPage(3,-2,event)"><a   href="#">首页</a></li><li class="prev disabled" id="upSize3" onclick="upPage(3,-1,event)"><a  >上一页</a></li><li class="next disabled" onclick="upPage(3,1,event)"><a href="#" >下一页</a></li><li class="last disabled" onclick="upPage(3,2,event)"><a href="#" >尾页</a></li></ul>
									</div>
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

			<div class="zzc" style="display: none">
				<div class="out_goods">
					<p>选择调拨商品</p>
					<div class="out_goods_content">
						<span>出库门店：
						<select name="fromStore">
							<c:forEach items="${storeInfos }" var="store">
								<option value="${store.storeId }">${store.storeName }</option>
							</c:forEach>
						</select> 
						<span>入库门店：
						<select name="toStore">
							<c:forEach items="${storeInfos }" var="store">
								<option value="${store.storeId }">${store.storeName }</option>
							</c:forEach>
						</select> 
						</span>
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
												<tr goodsId="${goods.goodsId }" onclick="moveGoodsInfo('.zzc','${goods.goodsId }','${goods.goodsName }','${goods.goodsCodeSuffix }', this)">
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
							<button onclick="saveFlow(3, '.zzc')">确认</button>
							<button onclick="modalHide('.zzc')">取消</button>
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
			var toStore = jQuery(".clearfix.out_roll_ul").children("li[class='active']").attr("storeId");
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
			var fromStore = jQuery(".clearfix.out_roll_ul").children("li[class='active']").attr("storeId");
			var libraryObject = jQuery("select[name='libraryObject']").val();
			if (libraryObject == undefined){dialog("请选择出库员工");return;}
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
		window.location.href=baseUrl+"stock/view";
	}
	function modalHide(modal){
		jQuery(modal).hide('800');
		jQuery(modal).find("tr").removeClass("hide");
		jQuery(modal).find(".allocation_right").empty();
	}
	function choseStore(li){
		jQuery(li).siblings().removeClass("active");
		jQuery(li).addClass("active");
		var storeId = jQuery(li).attr("storeId");
		var url = baseUrl + "stock/query/" + storeId;
		jQuery.ajax({
			type : "POST",
			url : url,
			dataType : "json",
			async : false,
			success : function(data) {
// 				var inFlows = data.msg.inFlows;
// 				showFlow(inFlows, 0);
// 				var outFlows = data.msg.outFlows;
// 				showFlow(outFlows, 1);
// 				var employeeInfos = data.msg.employeeInfos;
// 				queryEmployeeInfo(employeeInfos);
				outFlows  = data.msg.outFlows;
				moveFlows = data.msg.moveFlows;
				inflows   = data.msg.inFlows;
				jQuery("li[name = 'pageSize']").remove();
				pageNo=1;
				initPage();
			}
		});
	}
	function showFlow(inFlows, index){
		var html0 = "";
		if (index==0){
			html0 = '<tr><td>入库时间</td><td>入库方式</td><td>入库明细</td><td>操作</td></tr>';
		}else {
			html0 = '<tr><td>出库时间</td><td>出库对象</td><td>出库方式</td><td>调拨明细</td><td>操作</td></tr>';
		}
		
		jQuery(".payroll_table").eq(index).find("tbody").empty();
		jQuery(".payroll_table").eq(index).find("tbody").append(jQuery(html0));
		for (var i = 0; i < inFlows.length; i++) {
			var flow = inFlows[i];
			var html1 = '';
			if (index == 0){
				html1 = '<tr>'+
						'<td>'+flow.createTime +'</td>'+
						'<td>'+flow.flowType +'</td>'+
						'<td>'+
							'<div class="overflow_text">';
			}else{
				html1 = '<tr>'+
						'<td>'+flow.createTime +'</td>'+
						'<td>'+flow.fromStoreName +'</td>'+
						'<td>'+flow.flowType +'</td>'+
						'<td>'+
							'<div class="overflow_text">';
			} 
			var html2 = "";
			var count = inFlows[i].stockCount.split(",");
			for (var j = 0; j < inFlows[i].accountGoods.length; j++) {
				var goods = inFlows[i].accountGoods[j];
				html2 += '<span>'+goods.goodsCodeSuffix+'  '+goods.goodsName +' : <i>'+count[j]+'</i></span>';
			}
			var html3 = '</div>'+
							'<div class="fly_"></div></td>'+
							'<td><span><img src="'+baseUrl+'images/handle_1.png"></span><span><img src="'+baseUrl+'images/handle_2.png"></span></td>'+
						'</tr>';
			var html = html1 + html2 + html3;
			jQuery(".payroll_table").eq(index).find("tbody").append(jQuery(html));
		}
	}
	/**更新员工信息*/
	function queryEmployeeInfo(employeeInfos){
		jQuery("select[name='libraryObject']").empty();
		for (var i = 0; i < employeeInfos.length; i++) {
			var employeeId = employeeInfos[i].employeeId;
			var employeeName = employeeInfos[i].name;
			var html = '<option value='+employeeId+'>'+employeeName+'</option>';
			jQuery("select[name='libraryObject']").append(jQuery(html));
		}
	}

	var inFlow = '${inFlows}';
	var inflows = null;
	if(!isEmpty(inFlow)){
		inflows = eval("("+inFlow.replace(/[\r\n]/g,"\\n")+")");
	}
	
	var outFlow = '${outFlows}'; 
	var outFlows = null;
	if(!isEmpty(outFlow)){
		outFlows = eval("("+outFlow.replace(/[\r\n]/g,"\\n")+")");
	}
	var moveFlow = '${moveFlows}'; 
	var moveFlows = null;
	if(!isEmpty(moveFlow)){
		moveFlows = eval("("+moveFlow.replace(/[\r\n]/g,"\\n")+")");
	}
	
	
	
	//一页多少条
	var resultPage = 9;
	var pageNo = 1;
	function initPageClick(type,page){
		if(type ==1){
			flow1(page);
		}
		else if(type ==2){
			flow2(page);
		}
		else if(type ==3){
			flow3(page);
		}
		
	}
	
	//表格一分页
	function flow1(page){
		
		jQuery("#fenye1 li[id='"+pageNo+"']").attr("class","page");
		jQuery("#fenye1 li[id='"+page+"']").attr("class","page active");
		pageNo=page;
		
		jQuery("#intable1 tr[name='in']").empty();
		var inf = (inflows.length - resultPage * page + resultPage)>resultPage ? resultPage * page : inflows.length;
		for (var i = resultPage*page-resultPage;i < inf; i++) {
			var html = '<tr name="in"><td>'+inflows[i].createTime+'</td><td>'+inflows[i].flowType+'</td><td><div class="overflow_text">';
			for (var j = 0; j < inflows[i].accountGoods.length; j++) {
				var  goods = inflows[i].accountGoods[j];
				html +='<span>'+goods.goodsCodeSuffix +''+ goods.goodsName+ ':<i>'+inflows[i].stockCount.split(",")[j]+' </i></span>';
			}
			html +='</div><div class="fly_"></div></td></tr>';
			jQuery("#intable1").append(jQuery(html));
		}
		
	}
	//表格二分页
	function flow2(page){
		
		jQuery("#fenye2 li[id='"+pageNo+"']").attr("class","page");
		jQuery("#fenye2 li[id='"+page+"']").attr("class","page active");
		pageNo=page;
		
		jQuery("#intable2 tr[name='in']").empty();
		var inf = (outFlows.length - resultPage * page + resultPage)>resultPage ? resultPage * page: outFlows.length;
		for (var i = resultPage*page-resultPage;i < inf; i++) {
			var html = '<tr name="in"><td>'+outFlows[i].createTime+'</td><td>'+outFlows[i].employeeName+'</td><td>'+outFlows[i].flowType+'</td><td><div class="overflow_text">';
			for (var j = 0; j < outFlows[i].accountGoods.length; j++) {
				var  goods = outFlows[i].accountGoods[j];
				html +='<span>'+goods.goodsCodeSuffix +''+ goods.goodsName+ ':<i>'+outFlows[i].stockCount.split(",")[j]+' </i></span>';
			}
			html +='</div><div class="fly_"></div></td></tr>';
			jQuery("#intable2").append(jQuery(html));
		}
	}
	
	
	function flow3(page){
		
		jQuery("#fenye3 li[id='"+pageNo+"']").attr("class","page");
		jQuery("#fenye3 li[id='"+page+"']").attr("class","page active");
		pageNo=page;
		
		jQuery("#intable3 tr[name='in']").empty();
		var inf = (moveFlows.length - resultPage * page + resultPage)>resultPage ? resultPage * page: moveFlows.length;
		for (var i = resultPage*page-resultPage;i < inf; i++) {
			var html = '<tr name="in"><td>'+moveFlows[i].createTime+'</td><td>'+moveFlows[i].fromStoreName+'</td><td>'+moveFlows[i].toStoreName+'</td><td><div class="overflow_text">';
			for (var j = 0; j < moveFlows[i].accountGoods.length; j++) {
				var  goods = moveFlows[i].accountGoods[j];
				html +='<span>'+goods.goodsCodeSuffix +''+ goods.goodsName+ ':<i>'+moveFlows[i].stockCount.split(",")[j]+' </i></span>';
			}
			html +='</div><div class="fly_"></div></td></tr>';
			jQuery("#intable3").append(jQuery(html));
		}
		
		
	}
	//上一页下一页
	function  upPage(type,pages,event){
		event.stopPropagation();
		if(pages==1){
			if(pageNo==1 && pages==-1){
				return;
			}
			if(type ==1 &&pages==1 && pageNo==Math.ceil(inflows.length/resultPage) ||  type ==2 && pages==1 &&pageNo==Math.ceil(outFlows.length/resultPage) || type ==3 && pages==1 &&pageNo==Math.ceil(moveFlows.length/resultPage)){
				return;
			}
			initPageClick(type,pageNo+pages);
		}
		else if( pages==2){
			if(type==1){
				initPageClick(type,Math.ceil(inflows.length/resultPage));
			}
			if(type==2){
				initPageClick(type,Math.ceil(outFlows.length/resultPage));
			}
			if(type==3){
				initPageClick(type,Math.ceil(moveFlows.length/resultPage));
			}
		}
		else{
			initPageClick(type,1);
		}
	}
	
	
	function initPage(){
		var resultPage1=inflows.length;
		var resultPage2=outFlows.length;
		var resultPage3=moveFlows.length;
		jQuery("#fenye3 i[name='result3']").text(resultPage3);
		jQuery("#fenye2 i[name='result2']").text(resultPage2);
		jQuery("#fenye1 i[name='result1']").text(resultPage1);
		
		jQuery("#fenye3 i[name='page3']").text(Math.ceil(resultPage3/resultPage));
		jQuery("#fenye2 i[name='page2']").text(Math.ceil(resultPage2/resultPage));
		jQuery("#fenye1 i[name='page1']").text(Math.ceil(resultPage1/resultPage));

		fenye(resultPage3,3);
		fenye(resultPage2,2);
		fenye(resultPage1,1);
		flow3(pageNo);
		flow2(pageNo);
		flow1(pageNo);
	}
	//初始化
	jQuery(function(){
		initPage();

	})
	//页数赋值
	function fenye(resultPage1,type){
		
		for (var i = Math.ceil(resultPage1/resultPage); i >0 ; i--) {
			if(i==1){
				var html ='<li name = "pageSize" class="page active" id="'+i+'" onclick="initPageClick('+type+','+i+')"><a href="#">'+i+'</a></li>';
			}
			else{
				var html ='<li name = "pageSize" class="page" id="'+i+'"  onclick="initPageClick('+type+','+i+')"><a  href="#">'+i+'</a></li>';
			}
			
			jQuery("#upSize"+type).after(jQuery(html));
		}
	}
</script>
</html>