var isAssignArray =new Array("未指定", "指定", "");
var isAppointArray =new Array("未预约","预约", "");

var curDate = getCurDate();
jQuery('#startDate').datetimepicker({value : curDate + ' 00:00'});
jQuery('#endDate').datetimepicker({value : curDate + ' 23:59'});

function btnSearchPhone() {
	var queryCode = jQuery("#ipt-search-phone").val();
	if (isEmpty(queryCode)) {
		dialog("请填写完整的订单号或者手机号码");
		return;
	}
	
	pageNo = 1;
	changePage(queryCode);
}

function btnSearchTime () {
	pageNo = 1;
	changePage();
}

//根据不同类型进行排序
function changeOrderByType(typeName, obj){
	//先清除其他排序
	jQuery(".iconfa-caret-up").removeClass('hide');
	jQuery(".iconfa-caret-down").removeClass('hide');
	
	var order = queryParams[typeName];
	var type = 0;
	if (isEmpty(order) || order == 0 || order == 2) {
		type = 1;
		
		jQuery(obj).find(".iconfa-caret-up").removeClass('hide');
		jQuery(obj).find(".iconfa-caret-down").addClass('hide');
	}
	else {
		type = 2;
		
		jQuery(obj).find(".iconfa-caret-down").removeClass('hide');
		jQuery(obj).find(".iconfa-caret-up").addClass('hide');
	}
	
	queryParams["timeOrder"] = 0;
	queryParams["receivableOrder"] = 0;
	queryParams["cashOrder"] = 0;
	queryParams["unionpayOrder"] = 0;
	queryParams["netpayOrder"] = 0;
	queryParams["cardOrder"] = 0;
	queryParams["comboOrder"] = 0;
	queryParams["giftOrder"] = 0;
	queryParams["couponOrder"] = 0;
	queryParams["grouppayOrder"] = 0;
	queryParams["debtOrder"] = 0;
	queryParams["realOrder"] = 0;
	queryParams[typeName] = type;
	
	changePage();
}

//分页处理
function changePage(queryCode){
	var data = queryParams;
	//如果查询内容不为空，为精准查询
	if (isEmpty(queryCode)) {
		var beginTimes = jQuery("#startDate").val();
		var endTimes = jQuery("#endDate").val();
		var d1 = new Date(beginTimes);
		var d2 = new Date(endTimes);
		if (d1 > d2) {
			dialog("开始时间不能大于结束时间");
			return;
		}
		
		var isDeleted = 0;
		var isDeletedValue = jQuery("input[name='isDeletedValue']:checked");
		
		if (isDeletedValue.length == 0) {
			jQuery(".ls-detai-table").removeClass("hide");
			jQuery(".more-toolbar").removeClass("hide");
		}
		else {
			isDeleted = 1;
			jQuery(".ls-detai-table").addClass("hide");
			jQuery(".more-toolbar").addClass("hide");
		}
		
		beginTime = beginTimes.replace(/\//g,"-");
		endTime = endTimes.replace(/\//g,"-");
		queryParams["beginTime"] = beginTime;
		queryParams["endTime"] = endTime;
		queryParams["pageNo"] = pageNo;
		queryParams["isDeleted"] = isDeleted;
		queryParams["queryCode"] = null;
	}
	else {
		data = {"queryCode":queryCode, "pageNo":pageNo, "isDeleted":0};
	}
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/action/list",
		data : data,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var obj = e.msg;
			queryParams = obj.queryParams;
			
			refreshTableData(obj.page);
			if (isDeleted == 0) {
				refreshStatData(obj.page.totalRecord, obj);
			}
		}
	});
}

function refreshTableData(page){
	totalRecord = page.totalRecord;
	totalPage = page.totalPage;
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	if (pageNo == 1) {
		unbuildPagination();
	}
	
	var $tbody = jQuery("#tbody-data");
	$tbody.empty();
	var daybookDatas = page.results;
	for (var i = 0; i < daybookDatas.length; i++) {
		$tbody.append(spellTableData(daybookDatas[i]));
	}
}

function refreshStatData(totalCount, obj) {
	var statInfo = obj.countBook;
	jQuery("#span-totalcount").text(totalCount);
	jQuery("#searchDate").text(obj.searchDate);
	
	jQuery("#totalPersonCount").text(statInfo.count);
	jQuery("#receivableAmount").text(statInfo.receivableAmount);
	jQuery("#giftAmount").text(statInfo.giftAmount);
	jQuery("#couponAmount").text(statInfo.couponAmount);
	jQuery("#comboAmount").text(statInfo.comboAmount);
	jQuery("#realPrice").text(statInfo.realPrice);
	
	jQuery("#cashAmount").text(new Big(statInfo.cashAmount).toFixed(2));
	jQuery("#unionpayAmount").text(new Big(statInfo.unionpayAmount).toFixed(2));
	jQuery("#wechatAmount").text(new Big(statInfo.wechatAmount).toFixed(2));
	jQuery("#alipayAmount").text(new Big(statInfo.alipayAmount).toFixed(2));
	jQuery("#groupAmount").text(new Big(statInfo.groupAmount).toFixed(2));
	jQuery("#debtAmount").text(new Big(statInfo.debtAmount).toFixed(2));
	jQuery("#cardAmount").text(new Big(statInfo.cardAmount).toFixed(2));
	
	var detailCount = obj.detailCount;
	jQuery("#projectSalesAmount").text(new Big(detailCount.projectSalesAmount).toFixed(2));
	jQuery("#goodsSalesAmount").text(new Big(detailCount.goodsSalesAmount).toFixed(2));
	jQuery("#comboSalesAmount").text(new Big(detailCount.comboSalesAmount).toFixed(2));
	jQuery("#cardSalesAmount").text(new Big(detailCount.cardSalesAmount).toFixed(2));
	jQuery("#presentAmount").text(new Big(detailCount.presentAmount).toFixed(2));
	
	jQuery("#projectSalesCount").text(detailCount.projectSalesCount);
	jQuery("#goodsSalesCount").text(detailCount.goodsSalesCount);
	jQuery("#comboSalesCount").text(detailCount.comboSalesCount);
	jQuery("#cardSalesCount").text(detailCount.cardSalesCount);
	jQuery("#presentCount").text(detailCount.presentCount);
}

function spellTableData(data) {
	var str = '"<tr><td data-toggle="modal" onclick="updateSelectOrder('+ data.orderId +')"><a class="can-click">'+ data.orderCode +'</a></td>';
	if(data.memberId == null || data.memberId == '') {
		str += '<td>散（' + data.sex + '）</td>';
	} else {
		str += '<td class="can-click" data-toggle="modal" data-target="#member-data" onclick="selectMemberInfo('+data.memberId+')">' + data.memberName + '</td>'
	}
	var projectName = data.projectName ;
	if (isEmpty(projectName) || projectName == 'null') {
		projectName = "充值开卡";
	}
	str += '<td>' + data.createTime + '</td>'
		+ '<td class="wthn100 ellipsis-text" data-toggle="tooltip" data-placement="right" title="' + projectName + '">'
	 	+ projectName + '</td>'
	 	+ '<td>' + data.realPrice + '</td>'
	 	+ '<td>' + data.cashAmount + '</td>'
	    + '<td>' + data.unionpayAmount + '</td>'
	    + '<td>';
	if(data.wechatAmount == 0 && data.alipayAmount == 0) {
		str += 0;
	} else if (data.wechatAmount > data.alipayAmount) {
		var waAmount = data.wechatAmount + data.alipayAmount;
		str += '<i class="iconfont icon-weixin fl"></i><span class="fl ml10">' + waAmount + '</span>';
	} else {
		var waAmount = data.wechatAmount + data.alipayAmount;
		str += '<i class="iconfont icon-zhifubao fl"></i><span class="fl ml10">' + waAmount + '</span>';
	}
	str += '</td>'
		+ '<td>' + data.cardAmount + '</td>'
		+ '<td>' + data.comboAmount + '</td>'
		+ '<td>' + data.giftAmount + '</td>'
		+ '<td>' + data.couponAmount + '</td>'
		+ '<td>' + data.groupAmount + '</td>'
		+ '<td>' + data.debtAmount + '</td>'
		+ '<td>' + data.realAmount + '</td>'
		+ '<td><span class="iconfa-trash project-icon" onclick="deleteOrder('+ data.orderId + ', this)"></span></td>'
		+ '</tr>';
	return str;
}

var updateOrderId = "";

var commissionArray = new Array();

function updateSelectOrder(orderId) {
	updateOrderId = orderId;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/selectOrderByUpdate",
		data : "orderId="+orderId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var obj = e.msg;
			jQuery("#orderCodeModel").text("流水单号：" + obj.orderCode);
			jQuery("#discountAmountModel").text(obj.discountAmount);
			jQuery("input[name='cashAmountModel']").val(obj.cashAmount);
			jQuery("input[name='unionpayAmountModel']").val(obj.unionpayAmount);
			jQuery("input[name='wechatAmountModel']").val(obj.wechatAmount);
			jQuery("input[name='alipayAmountModel']").val(obj.alipayAmount);
			jQuery("input[name='cardAmountModel']").val(obj.cardAmount);
			jQuery("input[name='groupAmountModel']").val(obj.groupAmount);
			jQuery("input[name='debtAmountModel']").val(obj.debtAmount);
			jQuery("#realAmountModel").text(obj.realAmount);
			var privilege = parseFloat(obj.appointOff) + parseFloat(obj.comboAmount) + parseFloat(obj.couponAmount) + parseFloat(obj.giftAmount);
			jQuery("#privilegeModel").text(privilege);
			
			jQuery("#detailExpense").empty();
			jQuery("#detailExpense").append("<p class='mt20'></p>");
			var orderDetailList = obj.orderDetailList;
			for (var i = 0; i < orderDetailList.length; i++) {
				var orderDetailDto = orderDetailList[i];
				var tables = jQuery("<table class='table xfdetail' orderDetail = '"+orderDetailDto.orderDetail+"'></table>");
				
				var proStr = "<thead>" + "<tr>" +
	                "<th colspan='3'>" + orderDetailDto.projectName+"</thd>" +
	                "<th class='text-right' colspan='5' name = ''>	<span class='mr10'>应收：¥" + orderDetailDto.discountAmount + '</span>';
				if (orderDetailDto.privilegeMoney <= 0) {
					proStr += "";
				} else {
					proStr += "<span class='mr10'>"+orderDetailDto.privilegeInfo+" -"+orderDetailDto.privilegeMoney+"</span>"
				}
				if (orderDetailDto.appointOff <= 0) {
					proStr += "";
				}
				else {
					proStr += "<span class='mr10'>预约：-"+orderDetailDto.appointOff+"</span>";
				}
				if (orderDetailDto.freeAmount > 0) {
					proStr += "<span class='mr10'>改单金额: +"+orderDetailDto.freeAmount+" </span>"+
				    "<span class='mr10'>改单备注: "+orderDetailDto.orderRemark+"</span>";
				} 
				else if (orderDetailDto.freeAmount < 0){
					proStr += "<span class='mr10'>改单金额: "+orderDetailDto.freeAmount+" </span>"+
				    "<span class='mr10'>改单备注: "+orderDetailDto.orderRemark+"</span>";
				}
				else {
					proStr += "";
				}
				proStr += "<span>实收: "+orderDetailDto.realPrice+" </span></th></tr>"+
					          "</thead>";
				
				tables.append(proStr);
				var stepList = orderDetailDto.stepList;
				var tbodys = jQuery("<tbody></tbody>");
				for (var j = 0; j < stepList.length; j++) {
					var orderDetailStepDto = stepList[j];
					var tbodysStr = "<tr  commissionId = '"+orderDetailStepDto.commissionId+"'>"+
						                "<td class='w100'>"+orderDetailStepDto.projectStepName+"</td>"+
						                "<td class='w100'>"+orderDetailStepDto.shiftMahjongName+"</td>";
					if (isEmpty(orderDetailStepDto.employeeInfo) || orderDetailStepDto.employeeInfo == "null") {
						tbodysStr = tbodysStr + "<td class='wthn150'>未设置人员</td>";
					}
					else {
						tbodysStr = tbodysStr + "<td class='wthn150'>"+orderDetailStepDto.employeeInfo.employeeCode+" "+orderDetailStepDto.employeeInfo.name+"</td>";
					}
					tbodysStr = tbodysStr + "<td class='w100'>"+isAssignArray[orderDetailStepDto.isAssign]+"</td>"+
							                "<td class='w100'>"+isAppointArray[orderDetailStepDto.isAppoint]+"</td>"+
							                "<td>提成： <input class='w85' type='text' name = 'commissionAmount' value = '"+orderDetailStepDto.commissionAmount+"' placeholder='0'/></td>"+
							                "<td>业绩： <input class='w85' type='text' name = 'commissionCalculate' value = '"+orderDetailStepDto.commissionCalculate+"' placeholder='0'/></td>"+
							                "<td></td>"
							            "</tr>";
					tbodys.append(tbodysStr);
					var commissionObj = {"commissionId": orderDetailStepDto.commissionId,"commissionAmount":orderDetailStepDto.commissionAmount,"commissionCalculate":orderDetailStepDto.commissionCalculate};
	                commissionArray.push(commissionObj);
				}
				tables.append(tbodys);
				jQuery("#detailExpense").append(tables);
			}
			jQuery("#liushui-search").modal("show");
		}
	});
}

function checkNum(obj) {  
    //检查是否是非数字值  
    if (isNaN(obj.value)) {  
        obj.value = "";  
    }   
}

//取消model
function cancelModel() {
	jQuery("#liushui-search").addClass("hide");
}

//确定model
function confirmModel() {
	var cashAmount = jQuery("input[name='cashAmountModel']").val();
	var unionpayAmount = jQuery("input[name='unionpayAmountModel']").val();
	var wechatAmount = jQuery("input[name='wechatAmountModel']").val();
	var alipayAmount = jQuery("input[name='alipayAmountModel']").val();
	var cardAmount = jQuery("input[name='cardAmountModel']").val();
	var groupAmount = jQuery("input[name='groupAmountModel']").val();
	var debtAmount = jQuery("input[name='debtAmountModel']").val();
	if (isEmpty(cashAmount)) {
		cashAmount = 0;
	}
	if (isEmpty(unionpayAmount)) {
		unionpayAmount = 0;
	}
	if (isEmpty(alipayAmount)) {
		alipayAmount = 0;
	}
	if (isEmpty(wechatAmount)) {
		wechatAmount = 0;
	}
	if (isEmpty(cardAmount)) {
		cardAmount = 0;
	}
	if (isEmpty(groupAmount)) {
		groupAmount = 0;
	}
	if (isEmpty(debtAmount)) {
		debtAmount = 0;
	}
	var realAmount = jQuery("#realAmountModel").text();
	var totailMoney = parseFloat(cashAmount) + parseFloat(unionpayAmount) + parseFloat(wechatAmount) 
	+ parseFloat(alipayAmount) + parseFloat(cardAmount) + parseFloat(debtAmount) + parseFloat(groupAmount);
	
	if (parseFloat(realAmount) != totailMoney) {
		dialog("支付金额与实收不相等，请重新填写！");
		return;
	}
	
	var commissionAmountInput = jQuery("input[name='commissionAmount']");
	var commissionCalculateInput = jQuery("input[name='commissionCalculate']");
	
	var newCommissionArray = new Array();
	
	for (var i = 0; i < commissionAmountInput.length; i++) {
		var commissionAmount = jQuery(commissionAmountInput[i]).val();
		var commissionCalculate = jQuery(commissionCalculateInput[i]).val();
		var commissionId = jQuery(commissionAmountInput[i]).parent().parent().attr("commissionId");
		
		for (var j = 0; j < commissionArray.length; j++) {
			if (commissionArray[j].commissionId == commissionId) {
				if (commissionArray[j].commissionAmount != commissionAmount || commissionArray[j].commissionCalculate != commissionCalculate) {
					var newObj = {"commissionId": commissionId, "commissionAmount":commissionAmount, "commissionCalculate":commissionCalculate};
					newCommissionArray.push(newObj);
				}
			}
		}
	}
	
	var commissionArrayStr = JSON.stringify(newCommissionArray);
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/orderByUpdate",
		data : {"cashAmount":cashAmount, "unionpayAmount":unionpayAmount, "wechatAmount":wechatAmount, "alipayAmount":alipayAmount,
			"cardAmount":cardAmount, "groupAmount":groupAmount, "debtAmount":debtAmount, "realAmount":realAmount, "orderId":updateOrderId, "commissionArrayStr":commissionArrayStr},
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("保存成功！");
			jQuery("#cancelModel").click();
		}
	});
}

var memberId = null;

var deleteOrderId;
var deleteOrderObj;
function deleteOrder(orderId, obj) {
	deleteOrderId = orderId;
	deleteOrderObj = obj;
	jQuery("#deleteOrderTip").modal('show');
}

function deleteOrderConfirm(){
	jQuery("#deleteOrderTip").modal('hide');
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/elementDeleteOrderId",
		data : "orderId="+deleteOrderId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				if (e.code == 888) {
					var date = e.msg;
					var memberLevelList = date.memberLevelList;
					
					memberId = date.memberId;
					
					var levelId = date.levelId;
					
					jQuery("#topTR").siblings().remove();
					
					var trVar = null;
					for (var i = 0; i < memberLevelList.length; i++) {
						if (i % 2 == 0) {
							trVar = jQuery("<tr></tr>");
							var element = packageElement(memberLevelList[i].levelId, memberLevelList[i].levelName, levelId);
							trVar.append(element);
							if (i + 1 == memberLevelList.length) {
								trVar.append("<td></td>")
								jQuery("#modelTbody").append(trVar);
							}
						}
						else {
							var element = packageElement(memberLevelList[i].levelId, memberLevelList[i].levelName, levelId);
							trVar.append(element);
							jQuery("#modelTbody").append(trVar);
						}
					}
					dialog("删除成功！");
					jQuery("#member-level-modal").modal("show");
					jQuery(deleteOrderObj).parents("tr").remove();
					return;
				}
				dialog(e.msg);
				return;
			}
			dialog("删除成功！");
			jQuery(deleteOrderObj).parents("tr").remove();
		}
	});
}

function packageElement(levelId, levelName, oldLevelId) {
	var str =  "<td >"+
				    "<div class='ch-checker fl'>";
	if (oldLevelId == levelId) {
		str = str + "<div class='beau-checker active'>"+
			           "<span class='iconfont icon-gou'></span>"+
			        "</div>" +
		            "<input type='radio' class='yellow-checker' name='levelId' checked='checked' value='"+ levelId +"'>";
	}
	else {
		str = str + "<div class='beau-checker'>"+
			           "<span class='iconfont icon-gou'></span>"+
			        "</div>" +
			        "<input type='radio' class='yellow-checker' name='levelId' value='"+ levelId +"'>";
	}    
	str = str + "</div>"+
				"<span class='ml30'>"+ levelName +"</span>"+
		     "</td>";
	return str;
}

function updateMemberLevel() {
	var levelId = jQuery("input[name='levelId']:checked").val();
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "daybook/changeLevelId",
		data : "memberId=" + memberId + "&levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("修改会员等级成功！");
			jQuery("#member-level-modal").modal("hide");
		}
	});
}