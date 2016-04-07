 jQuery(function(){
        var a=[];
        jQuery(".mainwrapper").show()
        var tlength=jQuery(".t-fix th").length;
        for(i=0;i<tlength;i++)  {
            a[i]=jQuery(".t-fix th").eq(i).width();
        }

        function table() {
            jQuery(".mainwrapper").show()
            var fix=jQuery(".t-fix").offset()
            var tableT=fix.top
            jQuery(window).scroll(function(event){
                var scH=jQuery(window).scrollTop()
                if(scH>tableT){
                    jQuery(".t-fix").addClass("add-fix")
                    for(i=0;i<jQuery(".t-fix th").length;i++){
                        /*  var thwidth=jQuery(".t-fix th").eq(i).width();
                          var tbwidth=jQuery(".t-table td").eq(i).width();*/
                        var tdwidth=a[i];
                        var tbwidth=a[i];
                        jQuery(".t-fix th").eq(i).css("width",tdwidth)
                        jQuery(".t-table td").eq(i).css("width",tbwidth)

                    }
                }
                else{
                    jQuery(".t-fix").removeClass("add-fix")
                }
            })
        }

        table();
    })



//上一页
function previous(){
	if(pageNo <= 1){
		return;
	}
	pageNo--;
	changePage();
}

//下一页
function next(){
	if(pageNo >= totalPage){
		return;
	}
	pageNo++;
	changePage();
}

//更改每页显示数量
function changePageSize(size){
	pageNo = 1;
	pageSize = size;
	jQuery(".perpage").html(size + "<span class='caret'></span>");
	changePage();
}

//分页处理
function changePage(){
	var employeeName = jQuery("#employeeName").val();
	var attendanceDate = jQuery("#attendanceDate").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "/employeeAttendance/action/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize+ "&employeeName=" + employeeName + "&attendanceDate=" + attendanceDate,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			refreshTableData(e.msg);
			checkPageButton();
			//点击查询按键才刷新页码上的数据(点击上一页下一页不用刷新)
			if (isClickSearchButton) {
				isClickSearchButton = false;
				unbuildPagination();
			}
		}
	});
}

//刷新表格数据
function refreshTableData(page){
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	totalPage = page.totalPage;
	totalRecord = page.totalRecord;
	
	var List = page.results;
	var tbody = document.createElement("tbody");
	
	for (var i = 0; i < List.length; i++) {
		var employee = List[i];
		var tr = document.createElement("tr");
		//tr.setAttribute("id", employee.employeeId);
		//工号
		var employeeCode = document.createElement("td");
		employeeCode.innerHTML = employee.employeeInfo.employeeCode;
		tr.appendChild(employeeCode);
		//姓名
		var name = document.createElement("td");
		name.innerHTML = employee.employeeInfo.name;
		tr.appendChild(name);
		//签到时间
		var signInTime = document.createElement("td");
		signInTime.innerHTML = employee.signInTime;
		tr.appendChild(signInTime);
		//迟到时间
		var lateTime = document.createElement("td");
		lateTime.innerHTML = employee.signInOffsetToTimeDetail;
		tr.appendChild(lateTime);
		//签退时间
		var signOutTime = document.createElement("td");
		signOutTime.innerHTML = employee.signOutTime;
		tr.appendChild(signOutTime);
		//早退时间
		var earlyLeaveTime = document.createElement("td");
		earlyLeaveTime.innerHTML = employee.signOutOffsetTimeDetail;
		tr.appendChild(earlyLeaveTime);
		//日期
		var date = document.createElement("td");
		date.innerHTML = employee.attendanceDate;
		tr.appendChild(date);
		//操作(修改删除按钮)
		var operation = document.createElement("td");
		operation.innerHTML = "<span class='iconfa-pencil project-icon' onclick='toEdit(" + employee.recordId + ")' ></span>" +
				"<span class='iconfa-trash project-icon' onclick='employeeAttendacneDelete(" + employee.recordId + ")' ></span>";
		tr.appendChild(operation);
		
//		var operateTd = document.createElement("td");
/*		var editSpan = document.createElement("span");
		editSpan.setAttribute("class", "icon-edit");
		editSpan.setAttribute("onclick", "openedit(" + employee.employeeId + ")");
		
		var removeSpan = document.createElement("span");
		removeSpan.setAttribute("class", "icon-remove-sign ml30");
		removeSpan.setAttribute("onclick", "deleteinfo(" + employee.employeeId + ")");
		operateTd.appendChild(editSpan);
		operateTd.appendChild(removeSpan);*/
//		tr.appendChild(operateTd);
		tbody.appendChild(tr);
	}
	jQuery(".member-list-table tbody").remove();
	jQuery(".member-list-table").append(tbody);
}

//检查当前页码是否可以进行上一页或者下一页，并对其翻页按钮进行对应的处理
function checkPageButton(){
	//处理上一页
	if(pageNo <= 1){
		jQuery("#previousPageButton").attr("disabled",true);  
	} else {
		jQuery("#previousPageButton").attr("disabled",false);  
	}
	
	//处理下一页
	if(pageNo >= totalPage){
		jQuery("#nextPageButton").attr("disabled",true);  
	} else {
		jQuery("#nextPageButton").attr("disabled",false);
	}
}

//隐藏模态框
function cancelEdit(){
	jQuery('#edit-employeeAttendance-modal').modal('hide');
}

//去往修改员工考勤记录模态框(参数主键)
function toEdit(recordId) {
	//显示模态框
	jQuery("#edit-employeeAttendance-modal").modal();
	jQuery.ajax({
		type : "post",
		dataType : "json",
		url : baseUrl + "employeeAttendance/action/findEmployeeAttendanceByPrimary?recordId=" + recordId,
		success : function(returnData) {
			jQuery("#editOfEmployeeCode").text(returnData.employeeInfo.employeeCode);
			jQuery("#editOfEmployeeName").text(returnData.employeeInfo.name);
			jQuery("#editOfAttendanceDate").text(returnData.attendanceDate);
			jQuery("#editOfSignInTime").val(returnData.signInTime)
			jQuery("#editOfSignOutTime").val(returnData.signOutTime);
			jQuery("#editOfSignInOffset").val(returnData.signInOffset);
			jQuery("#editOfSignOutOffset").val(returnData.signOutOffset);
			jQuery("#editOfRecordId").val(returnData.recordId);
		}
	});
}

//修改员工考勤记录
function edit() {
	var editOfSignInTime = jQuery("#editOfSignInTime").val();
	var editOfSignOutTime = jQuery("#editOfSignOutTime").val();
	/*if (! editOfSignInTime || editOfSignInTime.length == 0) {
		dialog("请输入签到时间");
		return;
	}
	if (! editOfSignOutTime || editOfSignOutTime.length == 0) {
		dialog("请输入签退时间");
		return;
	}*/
	jQuery.ajax({
		type : "post",
		dataType : "json",
		/*url : baseUrl + "employeeAttendance/action/edit?recordId=" + jQuery("#editOfRecordId").val() + 
			"&signInTime=" + jQuery("#editOfSignInTime").val() + "&signOutTime=" + jQuery("#editOfSignOutTime").val(),*/
		data : {recordId : jQuery("#editOfRecordId").val(), signInTime : jQuery("#editOfSignInTime").val(), signOutTime : jQuery("#editOfSignOutTime").val()},
		url : baseUrl + "employeeAttendance/action/edit",
		success : function(returnData) {
			if (returnData.code != 0) {
				dialog(returnData.msg);
				return;
			}
			window.location.href = baseUrl + "attendance/view/attendance";
		}
	});
}

//删除
function employeeAttendacneDelete(recordId) {
	if (window.confirm("确认删除该记录吗")) {
		jQuery.ajax({
			type : "post",
			dataType : "json",
			url : baseUrl + "employeeAttendance/action/delete?recordId=" + recordId,
			success : function(returnData) {
				dialog(returnData.msg);
				if(returnData.code == 0) {
					window.location.href = baseUrl + "attendance/view/attendance";
				}
			}
			
		});
	}
}

//isClickSearchButton检查是否是点击查询按钮(如果是就要刷新分页插件,changePage()回调哪刷新)
var isClickSearchButton = false;
function searchEmployeeAttendance() {
	isClickSearchButton = true;
	pageNo = 1;
	changePage();
}


