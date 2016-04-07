/**
 * init
 */
//时间类型有 ：year(年), month(月), day(日)
var globalDateType = "day";
//部门id：-1代表全部部门
var globalPositionId = -1;
//排序类型有：employee_name(员工姓名), cash(现金业绩), card(卡金业绩), employee_commission(员工提成), order_num(客单数(客量)) 
var globalSortType = "cash";

/**
 * 根据日期查询员工业绩
 * dateType 有 ：year(年), month(月), day(日)
 * @param dateType
 */
function findEmployeeCommissionByDateType(dateType) {
	//遍历移除日期样式
	for(var i=0; i<3; i++) {
		//一系列选择后，看是否存在样式"active"
		if(jQuery(".year-month-day ul li:eq(" + i + ")").hasClass("active")) {
			jQuery(".year-month-day ul li:eq(" + i + ")").removeClass("active");
		}
	}
	//添加样式
	switch (dateType) {
		case "year":
			jQuery(".year-month-day ul li:eq(0)").addClass("active");
			break;
		case "month":
			jQuery(".year-month-day ul li:eq(1)").addClass("active");
			break;
		case "day":
			jQuery(".year-month-day ul li:eq(2)").addClass("active");
			break;
		default:
			break;
	}
	globalDateType = dateType;
	jQuery.ajax({
		type : "post", 
		data : {dateType :  globalDateType, positionId : globalPositionId, sortType : globalSortType},
		url : baseUrl + "boss/action/findEmployeeCommissionByCondition",
		dataType : "json",
		success : function (returnData) {
			refreshTableData(returnData);
		}
	});
}

/**
 * 根据岗位查询员工业绩
 * @param positionId  岗位id
 * @param positionName  岗位名
 */
function findEmployeeCommissionByPosition(positionId, positionName) {
//	jQuery(".btn-bumen s-modal-control").text(deptName);
//	jQuery(".ml1 iconfont icon-zhankai").prev().text(deptName);
	jQuery("#positionName").text(positionName);
	globalPositionId = positionId;
	jQuery.ajax({
		type : "post", 
		data : {dateType :  globalDateType, positionId : globalPositionId, sortType : globalSortType},
		url : baseUrl + "boss/action/findEmployeeCommissionByCondition",
		dataType : "json",
		success : function (returnData) {
			refreshTableData(returnData);
		}
	});
}

/**
 * 根据排序查询员工业绩
 * @param sortType
 */
function findEmployeeCommissionBySortType(sortType) {
	//移除样式
	for (var i=1; i<6; i++) {
		if(jQuery("#tableOfEmployeeCommission thead tr th:eq(" + i + ")").hasClass("active")) {
			jQuery("#tableOfEmployeeCommission thead tr th:eq(" + i + ")").removeClass("active");
		}
	}
	//添加样式
	switch(sortType) {
		case "employee_name":
			jQuery("#tableOfEmployeeCommission thead tr th:eq(1)").addClass("active");
			jQuery("#tableOfEmployeeCommission thead tr:eq(1) th:first").text("已按员工从高到低排序");
			break;
		case "cash":
			jQuery("#tableOfEmployeeCommission thead tr th:eq(2)").addClass("active");
			jQuery("#tableOfEmployeeCommission thead tr:eq(1) th:first").text("已按现金业绩从高到低排序");
			break;
		case "card":
			jQuery("#tableOfEmployeeCommission thead tr th:eq(3)").addClass("active");
			jQuery("#tableOfEmployeeCommission thead tr:eq(1) th:first").text("已按卡金业绩从高到低排序");
			break;
		case "employee_commission":
			jQuery("#tableOfEmployeeCommission thead tr th:eq(4)").addClass("active");
			jQuery("#tableOfEmployeeCommission thead tr:eq(1) th:first").text("已按提成从高到低排序");
			break;
		case "order_num":
			jQuery("#tableOfEmployeeCommission thead tr th:eq(5)").addClass("active");
			jQuery("#tableOfEmployeeCommission thead tr:eq(1) th:first").text("已按客量从高到低排序");
			break;
		default:
			break;
	}
	globalSortType = sortType;
	jQuery.ajax({
		type : "post", 
		data : {dateType :  globalDateType, positionId : globalPositionId, sortType : globalSortType},
		url : baseUrl + "boss/action/findEmployeeCommissionByCondition",
		dataType : "json",
		success : function (returnData) {
			refreshTableData(returnData);
		}
	});
}

/**
 * ajax刷新表格
 * @param employeeCommissionOfBossDtoList
 */
function refreshTableData(employeeCommissionOfBossDtoList) {
	var list = employeeCommissionOfBossDtoList;
	var tbody = document.createElement("tbody");
	for (var i = 0; i < list.length; i++) {
		var employeeCommission = list[i];
		var tr = document.createElement("tr");
		//为tr添加onclick事件
		tr.setAttribute("onclick", "toEmployeeCommissionDetail(" + employeeCommission.employeeId + ")");
		
		var sortTd = document.createElement("td");
		switch (i) {
			case 0:
				sortTd.innerHTML = '<img src="' + baseUrl + 'images/mobile/boss-newer/first.png" alt="">';
				break;
			case 1:
				sortTd.innerHTML = '<img src="' + baseUrl + 'images/mobile/boss-newer/second.png" alt="">';
				break;
			case 2:
				sortTd.innerHTML = '<img src="' + baseUrl + 'images/mobile/boss-newer/three.png" alt="">';
				break;
			default:
				sortTd.innerHTML = Number(i + 1);
				break;
		}
		tr.appendChild(sortTd);
		
		var employeeNameTd = document.createElement("td");
		employeeNameTd.innerHTML = employeeCommission.employeeName;
		tr.appendChild(employeeNameTd);
		
		var cashTd = document.createElement("td");
		cashTd.innerHTML = employeeCommission.cash.toFixed(2);
		tr.appendChild(cashTd);
		
		var cardTd = document.createElement("td");
		cardTd.innerHTML = employeeCommission.card.toFixed(2);
		tr.appendChild(cardTd);
		
		var employeeCommissionTd = document.createElement("td");
		employeeCommissionTd.innerHTML = employeeCommission.employeeCommission.toFixed(2);
		tr.appendChild(employeeCommissionTd);
		
		var orderNumTd = document.createElement("td");
		orderNumTd.innerHTML = employeeCommission.orderNum;
		tr.appendChild(orderNumTd);
		
		tbody.appendChild(tr);
	}
	jQuery("#tableOfEmployeeCommission tbody").remove();
	jQuery("#tableOfEmployeeCommission").append(tbody);
}

/**
 * 去往员工业绩详情页面
 * @param employeeId  员工id
 */
function toEmployeeCommissionDetail(employeeId) {
	window.location.href = baseUrl + "boss/view/employeeCommissionDetailHome?employeeId=" + employeeId + "&time=" + globalDateType;
}