var isAssignArray =new Array("未指定", "指定", "");
var isAppointArray =new Array("未预约","预约", "");

var curDate = getCurDate();

/*function btnSearchPhone() {
	var queryCode = jQuery("#ipt-search-phone").val();
	if (isEmpty(queryCode)) {
		dialog("请填写完整的订单号或者手机号码");
		return;
	}
	
	pageNo = 1;
	changePage(queryCode);
}*/

function btnSearchTime () {
	pageNo = 1;
	changePage();
}

jQuery("#orderType, #moneyWay, #orderState, #deptId").delegate("span", "click", function () {
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	jQuery(obj).siblings().removeClass("active");
	jQuery(obj).addClass("active");
	
	changePage();
})

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
	/*if (isEmpty(queryCode)) {*/
		var beginTimes = jQuery("#startDate").val();
		var endTimes = jQuery("#endDate").val();
		var d1 = new Date(beginTimes);
		var d2 = new Date(endTimes);
		if (d1 > d2) {
			dialog("开始时间不能大于结束时间");
			return;
		}
		var queryCode = jQuery("#ipt-search-phone").val();
		var orderType = jQuery("#orderType").find(".active").attr("value");
		var moneyWay = jQuery("#moneyWay").find(".active").attr("value");
		var orderState = jQuery("#orderState").find(".active").attr("value");
		var deptId = jQuery("#deptId").find(".active").attr("value");
		/*var isDeleted = 0;
		var isDeletedValue = jQuery("input[name='isDeletedValue']:checked");
		
		if (isDeletedValue.length == 0) {
			jQuery(".ls-detai-table").removeClass("hide");
			jQuery(".more-toolbar").removeClass("hide");
		}
		else {
			isDeleted = 1;
			jQuery(".ls-detai-table").addClass("hide");
			jQuery(".more-toolbar").addClass("hide");
		}*/
		
		beginTime = beginTimes.replace(/\//g,"-");
		endTime = endTimes.replace(/\//g,"-");
		queryParams["beginTime"] = beginTime;
		queryParams["endTime"] = endTime;
		queryParams["pageNo"] = pageNo;
		/*queryParams["isDeleted"] = isDeleted;*/
		queryParams["queryCode"] = queryCode;
		queryParams["orderType"] = orderType;
		queryParams["moneyWay"] = moneyWay;
		queryParams["orderState"] = orderState;
		queryParams["deptId"] = deptId;
/*	}
	else {
		data = {"queryCode":queryCode, "pageNo":pageNo, "isDeleted":0};
	}*/
	
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
			refreshStatData(obj.page.totalRecord, obj);
			/*if (isDeleted == 0) {
				refreshStatData(obj.page.totalRecord, obj);
			}*/
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
	
	var $tbody = jQuery(".search_table");
	$tbody.find("tr:gt(0)").remove();
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
	
	/*var detailCount = obj.detailCount;
	jQuery("#projectSalesAmount").text(new Big(detailCount.projectSalesAmount).toFixed(2));
	jQuery("#goodsSalesAmount").text(new Big(detailCount.goodsSalesAmount).toFixed(2));
	jQuery("#comboSalesAmount").text(new Big(detailCount.comboSalesAmount).toFixed(2));
	jQuery("#cardSalesAmount").text(new Big(detailCount.cardSalesAmount).toFixed(2));
	jQuery("#presentAmount").text(new Big(detailCount.presentAmount).toFixed(2));
	
	jQuery("#projectSalesCount").text(detailCount.projectSalesCount);
	jQuery("#goodsSalesCount").text(detailCount.goodsSalesCount);
	jQuery("#comboSalesCount").text(detailCount.comboSalesCount);
	jQuery("#cardSalesCount").text(detailCount.cardSalesCount);
	jQuery("#presentCount").text(detailCount.presentCount);*/
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
		+ '<td>'
	 	+ projectName + '</td>'
	 	+ '<td>' + data.realPrice + '</td>'
	    + '<td>';
    
	if (data.cashAmount == 0 && data.unionpayAmount == 0 && data.wechatAmount == 0 && data.alipayAmount == 0 && data.cardAmount == 0) {
		str += 0.00;
	} 
	if (data.cashAmount != 0) {
		str += '<p>'+data.cashAmount+'<em style="color:red">现金</em></p>';
	} 
	if (data.unionpayAmount != 0){
		str += '<p>'+data.unionpayAmount+'<em style="color:blue">银联</em></p>';
	}
	if (data.wechatAmount != 0){
		str += '<p>'+data.wechatAmount+'<em style="color:#59688a">微信</em></p>';
	}
	if (data.alipayAmount != 0){
		str += '<p>'+data.alipayAmount+'<em style="color:green">支付宝</em></p>';
	}
	if (data.cardAmount != 0){
		str += '<p>'+data.cardAmount+'<em style="color:pink">卡金</em></p>';
	}
	str += '</td><td>';
	
	if (data.comboAmount == 0 && data.giftAmount == 0 && data.couponAmount == 0 && data.groupAmount == 0) {
		str += 0.00;
	}
	if (data.comboAmount != 0) {
		str += '<span>'+data.comboAmount+'<em style="color:red;display:block">疗程</em></span>';
	}
	if (data.giftAmount != 0) {
		str += '<span>'+data.giftAmount+'<em style="color:blue;display:block">礼金</em></span>';
	}
	if (data.couponAmount != 0) {
		str += '<span>'+data.couponAmount+'<em style="color:#59688a;display:block">优惠券</em></span>';
	}
	if (data.groupAmount != 0) {
		str += '<span>'+data.groupAmount+'<em style="color:green;display:block">团购</em></span>';
	}
	str += '</td>'
		+ '<td>' + data.debtAmount + '</td>'
		+ '<td>' + data.realAmount + '</td>'
		+ '<td>正常</td>'
		+ '<td><button onclick="deleteOrder('+ data.orderId + ', this)">退单</button></td>'
		+ '</tr>';
	return str;
}

var updateOrderId = "";

var commissionArray = new Array();
 
 jQuery('.close_zzc,.water_cancel').click(function(){
    jQuery('.zzc').hide();
 });

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
			var data = e.msg;
			var obj = data.orderInfoBaseDto;
			var employeeInfoList = data.employeeInfoList;
			
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
			var orderDetailList = obj.orderDetailList;
			for (var i = 0; i < orderDetailList.length; i++) {
				var orderDetailDto = orderDetailList[i];
				
				var divObj = jQuery("<div class='boss_cut'></div>");
				
				var proStr = "<div class='boss_'>"+orderDetailDto.projectName+"<span><em>应收：¥"+orderDetailDto.discountAmount+"</em>";
				if (orderDetailDto.privilegeMoney <= 0) {
					proStr += "";
				} else {
					proStr += "<em>"+orderDetailDto.privilegeInfo+" -"+orderDetailDto.privilegeMoney+"</em>"
				}
				if (orderDetailDto.appointOff <= 0) {
					proStr += "";
				}
				else {
					proStr += "<em>预约：-"+orderDetailDto.appointOff+"</em>";
				}
				if (orderDetailDto.freeAmount > 0) {
					proStr += "<em>改单金额: +"+orderDetailDto.freeAmount+" </em>"+
				    "<em>改单备注: "+orderDetailDto.orderRemark+"</em>";
				} 
				else if (orderDetailDto.freeAmount < 0){
					proStr += "<em>改单金额: "+orderDetailDto.freeAmount+" </em>"+
				    "<em>改单备注: "+orderDetailDto.orderRemark+"</em>";
				}
				else {
					proStr += "";
				}
				proStr += "<em>实收: "+orderDetailDto.realPrice+" </em></span></div>";
				
				divObj.append(proStr);
				
				var stepList = orderDetailDto.stepList;
				var tbodys = jQuery("<table></table>");
				for (var j = 0; j < stepList.length; j++) {
					var orderDetailStepDto = stepList[j];
					var tbodysStr = "<tr commissionId = '"+orderDetailStepDto.commissionId+"'>"+
						                "<td>"+orderDetailStepDto.projectStepName+"</td>"+
						                "<td>"+orderDetailStepDto.shiftMahjongName+"</td>";
										
					tbodysStr += "<td><select name='employeeId' class='chzn-select mr5 input-medium'>";

					tbodysStr += "<option value=''>未设置服务人员</option>";
					for (var k = 0; k < employeeInfoList.length; k++) {
						if (isEmpty(orderDetailStepDto.employeeInfo) || orderDetailStepDto.employeeInfo == "null") {
							tbodysStr += "<option value='"+employeeInfoList[k].employeeId+"'><span class='gp'>"+employeeInfoList[k].employeeCode+"</span> <span class='name'>"+employeeInfoList[k].name+"</span></option>";
						}
						else {
							if (orderDetailStepDto.employeeInfo.employeeId == employeeInfoList[k].employeeId) {
								tbodysStr += "<option selected='selected' value='"+employeeInfoList[k].employeeId+"'><span class='gp'>"+employeeInfoList[k].employeeCode+"</span> <span class='name'>"+employeeInfoList[k].name+"</span></option>";
							}
							else {
								tbodysStr += "<option value='"+employeeInfoList[k].employeeId+"'><span class='gp'>"+employeeInfoList[k].employeeCode+"</span> <span class='name'>"+employeeInfoList[k].name+"</span></option>";
							}
						}
					}
					if (orderDetailDto.orderType == 1) {
						tbodysStr += "</select></td><td><select name='isAssign' value = "+orderDetailStepDto.isAssign+">";
						tbodysStr += "<option value='0'>非指定</option>"+
				                     "<option value='1'>指定</option></select>";
						
						tbodysStr += "<td><select name='isAppoint' value = "+orderDetailStepDto.isAppoint+">";
						tbodysStr += "<option value='0'>非预约</option>"+
				                     "<option value='1'>预约</option></select></td>";
					}
					
					tbodysStr = tbodysStr + "<td>提成： <input class='w85' type='text' name = 'commissionAmount' value = '"+orderDetailStepDto.commissionAmount+"' placeholder='0'/></td>"+
							                "<td>业绩： <input class='w85' type='text' name = 'commissionCalculate' value = '"+orderDetailStepDto.commissionCalculate+"' placeholder='0'/></td>"+
							            "</tr>";
					tbodys.append(tbodysStr);
					var commissionObj = {"commissionId": orderDetailStepDto.commissionId,"commissionAmount":orderDetailStepDto.commissionAmount,"commissionCalculate":orderDetailStepDto.commissionCalculate};
	                commissionArray.push(commissionObj);
				}
				divObj.append(tbodys);
				jQuery("#detailExpense").append(divObj);
			}
			jQuery("select[name='employeeId']").chosen({disable_search_threshold: 3});
			jQuery("select[name='employeeId']").trigger("liszt:updated");
			jQuery('.zzc').show();
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