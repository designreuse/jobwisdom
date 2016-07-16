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

	   var str = '<tr><td onclick="updateSelectOrder('+ data.orderId +'}"><a class="can-click">'+ data.orderCode +'</a></td>';
			if(data.memberId == null || data.memberId == '') {
				str += '<td>散（' + data.sex + '）</td>';
				} else {
				str += '<td class="can-click" data-toggle="modal" data-target="#member-data" onclick="selectMemberInfo('+data.memberId+')">' + data.memberName + '</td>'
			}
			var projectName = data.projectName ;
			if (isEmpty(projectName) || projectName == 'null') {
				projectName = "充值开卡";
			}
			 
			str += '<td><i>' + data.createTime + '</i><img onclick="WdatePicker({el:\'d12\',dateFmt:\'yyyy-MM-dd HH:mm:ss\',onpicked:pickedFunc})" src="'+ baseUrl +'images/coupon_write.png"> <input type="text" class="hidden" style="width: 0px" id="d12" "></td>'
				+ '<td>合计 <em>' + new Big(data.realAmount).toFixed(2)+ '</em></td>'
				/*+ '<td>' + data.realPrice + '</td>'*/
				+ '<td>';
			if (data.cashAmount == 0 && data.unionpayAmount == 0 && data.wechatAmount == 0 && data.alipayAmount == 0 && data.cardAmount == 0) {
				str += new Big(0).toFixed(2);
			} 
			if (data.cashAmount != 0) {
				str += '<p>'+new Big(data.cashAmount).toFixed(2)+'<em style="color:red">现金</em></p>';
			} 
			if (data.unionpayAmount != 0){
				str += '<p>'+new Big(data.unionpayAmount).toFixed(2)+'<em style="color:blue">银联</em></p>';
			}
			if (data.wechatAmount != 0){
				str += '<p>'+new Big(data.wechatAmount).toFixed(2)+'<em style="color:#59688a">微信</em></p>';
			}
			if (data.alipayAmount != 0){
				str += '<p>'+new Big(data.alipayAmount).toFixed(2)+'<em style="color:green">支付宝</em></p>';
			}
			if (data.cardAmount != 0){
				str += '<p>'+new Big(data.cardAmount).toFixed(2)+'<em style="color:pink">卡金</em></p>';
			}
			str += '</td><td style="width:140px" colspan="2">';
			for(var i =0 ; i< data.orderDetailList.length ;i++){
					var orderDetailList = data.orderDetailList[i];
					str += '<table class="search_table_"  width="439"><tr><td rowspan="100" width="141"><p class="pay_item">'+orderDetailList.projectName+'</p><i class="pay_item" style="text-align: center;">';
				for(var s = 0 ; s< data.deptList.length; s++){
				    var deptList =  data.deptList[s];
					str += '('+deptList.deptName+')';
				}
				str += '</i></td></tr>';
				for(var g = 0; g<orderDetailList.commissionList.length ; g++ ){
				   var commissionList =  orderDetailList.commissionList[g];
					str += ' <tr><td><span>('+commissionList.employeeCode+')<em>'+commissionList.employeeName+'</em>';	
				   if(orderDetailList.isAssign == 1){
					str += '<i>(指定)</i>+';
				   }
				   if(orderDetailList.isAssign == 0){
					str += ' <i>(未指定)</i>+';
				   }
				   str += '</span><span>业绩:<em>'+new Big(commissionList.commissionCalculate).toFixed(2) +'</em></span> <span style="border-right:none">提成:<em>'+new Big(commissionList.commissionAmount).toFixed(2) +' </em></span> </td> </tr>';
				}
			str += '</table>';
			}
			str	+= '<td><button onclick="deleteOrder('+ data.orderId + ', this)">退单</button></td></tr>';
		return str;
}

var updateOrderId = "";

var commissionArray = new Array();
 
 jQuery('.close_zzc,.water_cancel').click(function(){
    jQuery('.zzc').hide();
 });

function updateSelectOrder(orderId) {
	updateOrderId = orderId;
	var type = [];
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
			
			jQuery("#orderCodeModel").text(obj.orderCode);
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
			jQuery("#divobj").empty();
			var orderDetailList = obj.orderDetailList;
			var numbertype = 0;
			for (var i = 0; i < orderDetailList.length; i++) {
				var orderDetailDto = orderDetailList[i];
				var lj = "礼金"
				if(orderDetailDto.offTypeint == 1){
					lj="套餐"
				}
				if(orderDetailDto.offTypeint == 2){
					lj="优惠券"
				}
				
				var HTML = ' <div class="order_style"> <div class="order_style_content clearfix" >  <div class="order_style_content_left" ><span>'+orderDetailDto.projectName+'</span>';
				if(orderDetailDto.giftAmount != 0){
					HTML += '<p>'+lj+'抵扣：<em>¥'+orderDetailDto.giftAmount+'</em></p>';
				}
				HTML +='</div>';
				if(orderDetailDto.orderType ==1){
					HTML += '<ul class="order_style_content_right">';
					for (var q = 0; q < orderDetailDto.stepList.length; q++) {
						numbertype++;
						var stepList =orderDetailDto.stepList[q];   //订单员工信息
						HTML += ' <li commissionId = '+stepList.commissionId+' > <span>'+stepList.positionName+'</span><span><select class = "chzn-select w100" name = "employeeSelect">';
						for (var j = 0; j < employeeInfoList.length; j++) {    //全部员工
							if(employeeInfoList[j].employeeCode == stepList.employeeInfo.employeeCode){
								HTML += '<option id='+employeeInfoList[j].employeeCode+' selected="selected">'+employeeInfoList[j].employeeCode+'   '+ employeeInfoList[j].name+'</option>';
							}
							else{
								HTML += '<option id='+employeeInfoList[j].employeeCode+'>'+employeeInfoList[j].employeeCode+'   '+ employeeInfoList[j].name+'</option>';
							}
						}
						HTML += '</select></span><span style="margin-left:20px"><select style="width:60px;padding-left:20px">';
						if(stepList.isAssign == 1){
						    HTML +=	'<option value="1" selected="selected" >指定</option><option value="0">非指定</option></select></span>'	;								
						}else{
							HTML +=	'<option value="1">指定</option><option value="0" selected="selected" >非指定</option></select></span>'	;
									
								
						}
						HTML += '<span style="margin-left:20px"><select style="width:60px;padding-left:20px">';
						if(stepList.isAppoint == 1){
							  HTML +=	'<option value="1" selected="selected" >预约</option><option value="0">非预约</option></select></span>'	;		
						}else{
							  HTML +=	'<option value="1">预约</option><option value="0" selected="selected" >非预约</option></select></span>'	;		
								
						}
						HTML += '</select> </span><span style="margin-left:60px">提成<input type="text" name="commissionAmount" value ='+stepList.commissionAmount+'></span><span>业绩<input type="text" name="commissionCalculate" value ='+stepList.commissionCalculate+'></span></li>';
						var commissionObj = {"commissionId": stepList.commissionId,"commissionAmount":stepList.commissionAmount,"commissionCalculate":stepList.commissionCalculate};
		                commissionArray.push(commissionObj);
					}

					HTML +='</ul> </div></div> ';
			
					
					jQuery("#divobj").append(HTML);
				}else{
					HTML += '<ul class="order_style_content_right">';
					for (var q = 0; q < orderDetailDto.commissionList.length; q++) {
						numbertype++;
						var commissionList =orderDetailDto.commissionList[q];   //订单员工信息
						var number = "一";
						if( q == 1){number = "二";}
						if(q == 2){number = "三";}
						
						HTML += ' <li commissionId = '+commissionList.commissionId+'> <span>销售人员</span><span>第'+number+'人</span><span><select class = "chzn-select w100" name = "employeeSelect">';
						for (var j = 0; j < employeeInfoList.length; j++) {    //全部员工
							if(commissionList.employeeCode == employeeInfoList[j].employeeCode){
								HTML += '<option id='+employeeInfoList[j].employeeCode+' selected="selected">'+employeeInfoList[j].employeeCode+'   '+ employeeInfoList[j].name+'</option>';
							}
							else{
								HTML += '<option id='+employeeInfoList[j].employeeCode+'>'+employeeInfoList[j].employeeCode+'   '+ employeeInfoList[j].name+'</option>';
							}
						}
						HTML += '</select> </span><span style="margin-left:100px">提成<input type="text"   name="commissionAmount" value ='+commissionList.commissionAmount+'></span><span>业绩<input type="text"   name="commissionCalculate" value ='+commissionList.commissionCalculate+'></span></li>';
						var commissionObj = {"commissionId": commissionList.commissionId,"commissionAmount":commissionList.commissionAmount,"commissionCalculate":commissionList.commissionCalculate};
		                commissionArray.push(commissionObj);
					}
					HTML +='</ul> </div></div> ';
					jQuery("#divobj").append(HTML);
				}
			
			}
			jQuery("select[name='employeeSelect']").chosen({disable_search_threshold: 3});
			jQuery("select[name='employeeSelect']").trigger("liszt:updated");
			if(numbertype>0){
				jQuery('.zzc').show();
			}
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
		var commissionId = jQuery(commissionAmountInput[i]).parents("li").attr("commissionId");
		
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
			jQuery(".zzc").hide();
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