/**
 * 全局变量
 */
//上午(time1),下午(time2),晚上(time3),夜间(time4)之间的时间段切换标记
var globalPeriod = "time1";

//滚动日期中参数
var globalSelectYearAndMonthAndDay;
var globalSelectMonthAndDay;
var globalSelectWeekDay;
var globalTime;
//选中员工职位id(点击员工头像时)
var globalLevelId;
//新增预约所需参数
var globalEmployeeId;
var globalProjectId;
var globalProjectPrice;
//如果新增预约是会员以下两个是有值的
var globalCustomerName;
var globalCustomerPhone;
//可预约员工长度(点击部门查看可预约员工时)
var globalRollEmployeeLength;

jQuery(function() {
	//页面加载默认选中当前日期
	var now = new Date();
	var month = now.getMonth() + 1;
	var day = now.getDate();
	if (month < 10) 
		month = "0" + month;
	if (day < 10) 
		day = "0" + day;
	var nowString = month + "-" + day;
	//alert(jQuery("#rollDateUL li:eq(0) span").text());
	jQuery("#rollDateUL li").each(function() {
		//alert(jQuery(this).children("span").text());
		var spanDateValue = jQuery(this).children("span").text();
		var pWeekValue = jQuery(this).children("p").text();
		if (nowString == spanDateValue) {
			jQuery(this).addClass("current");
			//给全局变量赋值
			globalSelectYearAndMonthAndDay = new Date().getFullYear() + "-" + spanDateValue;
			globalSelectMonthAndDay = spanDateValue;
			globalSelectWeekDay = pWeekValue;
		}
	});
	//给滚动日期加载样式，全局日期变量赋值完调用次查询当日预约员工
	findMemberAppointByDate(null);
	
	/**
	 * 给预约加号绑定click事件
	 */
	jQuery(".yuyue").delegate(".add", "click", function() {
		/*jQuery("#yuyue").modal("show");*/
		jQuery(".zzc").show("800");
		var index = jQuery(this).parent("li").index();
		
		//根据globalPeriod,上午(time1),下午(time2),晚上(time3),夜间(time4)之间的时间段来判断是否取用哪个时间
		switch (globalPeriod) {
		case "time1":
			globalTime = jQuery("#forenoonPeriod").children("li:eq(" + index + ")").text();
			break;
		case "time2":
			globalTime = jQuery("#afternoonPeriod").children("li:eq(" + index + ")").text();
			break;
		case "time3":
			globalTime = jQuery("#eveningPeriod").children("li:eq(" + index + ")").text();
			break;
		case "time4":
			globalTime = jQuery("#nightPeriod").children("li:eq(" + index + ")").text();
			break;
		default:
			globalTime = jQuery("#forenoonPeriod").find("li:eq(" + index + ")").text();
			break;
		}
		
		//点击进去加载第一个选中部门下所有可预约员工(jQuery自动模拟一次点击事件,trigger)
		jQuery("#appointmentDeptUL li").first().trigger("click");
		
		jQuery("#customerName").val("");
		jQuery("#customerPhone").val("");
		cleanSelectEmployee();
		cleanSelectProject();
		cleanProjectListCategoryList();
		cleanProjectList();
		
	});
	
	//点击部门查询该部门下所有可预约员工
	/*jQuery("#appointmentDeptUL li").on("click",function(){
		jQuery(this).addClass("current").siblings().removeClass("current");
		globalSelectMonthAndDay = jQuery(this).children("span").text();
		globalSelectWeekDay = jQuery(this).children("p").text();
		jQuery.ajax({
			type : "post",
			data : {deptId : jQuery(this).val(), monthAndDay : globalSelectMonthAndDay, weekDay : globalSelectWeekDay, time : globalTime},
			url : baseUrl + "appoint/action/findEmployeeByDept",
			dataType : "json",
			success : function(returnData) {
				refreshEmployee(returnData.msg);
			}
		});
	});*/
	
	
	
});

//根据部门查询该部门下所有可预约员工
function findCanAppointEmployeeByDept(obj) {
	cleanProjectListCategoryList();
	//cleanProjectList();
	cleanSelectEmployee();
	//cleanSelectProject();
	//填充样式自己样式，清除其他兄弟节点样式
	jQuery(obj).addClass("active").siblings().removeClass("active");
	selectDeptInfoCategory(jQuery(obj).val());
	jQuery.ajax({
		type : "post",
		data : {deptId : jQuery(obj).val(), monthAndDay : globalSelectMonthAndDay, weekDay : globalSelectWeekDay, time : globalTime},
		url : baseUrl + "appoint/action/findEmployeeByDept",
		dataType : "json",
		success : function(returnData) {
			refreshEmployee(returnData.msg);
		}
	});
	
}

/**
 * 刷新员工列表
 * @param employeeList
 * <li>
 * 	   <img src="../../assets/images/yuyue.png" alt="" />
	   <div class="yuyue-name">1011 老魏</div>
   </li>
 */
function refreshEmployee(employeeList) {
	/*var ul = jQuery("<ul></ul>");
	for (var i=0; i<employeeList.length; i++) {
		var employee = employeeList[i];
		var li = jQuery("<li></li>");
		var img = jQuery("<img src='" + employee.headImage + "'/>");
		var nameDiv = jQuery("<div class='yuyue-name'>" + employee.name + "</div>");
		li.prepend(img.append(nameDiv));
		ul.prepend(li);
	}
	jQuery("#appointmentEmployeeDiv ul").remove();
	jQuery("#appointmentEmployeeDiv").prepend(ul);*/
	
	globalRollEmployeeLength = employeeList.length;
	if (employeeList.length == 0) {
		dialog("该部门下在" + globalSelectMonthAndDay + " " + globalTime + "时段暂无可预约员工");
	}
	jQuery("#selectTime").text(globalSelectMonthAndDay + " " + globalTime);
	
	var ul = document.createElement("ul");
	jQuery("#newAppoint").empty();
	for (var i=0; i<employeeList.length; i++) {
		var employee = employeeList[i];
		var html = '<li onclick="findProjectCategoryByEmployeeLevel(' + employee.employeeId + ',' + employee.levelId + ', this)"><img src="'+picUrl + employee.headImage+'"><div>'+employee.employeeCode + " " + employee.name+'</div></li>';
		jQuery("#newAppoint").append(jQuery(html));
		/*var employee = employeeList[i];
		var li = document.createElement("li");
		li.setAttribute("onclick", "findProjectCategoryByEmployeeLevel(" + employee.employeeId + "," + employee.levelId + ", this)");
		
		var img = document.createElement("img");
		img.setAttribute("src", picUrl + employee.headImage);
		li.appendChild(img);
		
		var nameDiv = document.createElement("div");
		nameDiv.setAttribute("class", "yuyue-name");
		nameDiv.innerHTML = employee.employeeCode + " " + employee.name;
		li.appendChild(nameDiv);
		
		ul.appendChild(li);*/
	}
	cleanScrollableListEmployee ();
	jQuery("#appointmentEmployeeDiv").append(ul);
}

//点击员工头像根据该员工职位查询他能做的项目系列
function findProjectCategoryByEmployeeLevel(employeeId, levelId, obj) {
	//现在不需要进行查询了, 针对人员进行预约
	//点击头像出现被选中员工样式
	jQuery("#employeeCode").text(jQuery(obj).find("div").text());
	/*jQuery(obj).addClass("current").append('<span class="iconfont icon-xuanzhong"></span>').siblings().removeClass("current");
    jQuery(obj).append('<span class="iconfont icon-xuanzhong"></span>').siblings().children(".iconfont").remove();*/
    globalEmployeeId = employeeId;
	return ;
	cleanProjectList();
	cleanSelectProject();
	
	globalEmployeeId = employeeId;
	globalLevelId = levelId;
	jQuery("#selectEmployee").text(jQuery(obj).children("div").text());
	
	//点击头像出现被选中员工样式
	jQuery(obj).addClass("current").append('<span class="iconfont icon-xuanzhong"></span>').siblings().removeClass("current");
    jQuery(obj).append('<span class="iconfont icon-xuanzhong"></span>').siblings().children(".iconfont").remove();
	
	jQuery.ajax({
		type : "post",
		dataType : "json",
		url : baseUrl + "appoint/action/findProjectCategoryByEmployeeLevel",
		data : {levelId : levelId},
		success : function(returnData) {
			refreshProjectCategory(returnData.msg);
		}
	});
	
}

//刷新项目系列
function refreshProjectCategory(projectCategoryList) {
	cleanProjectListCategoryList();
	if (projectCategoryList == null) {
		dialog("该员工没有可预约项目系列");
		return;
	}
	for (var i=0; i<projectCategoryList.length; i++) {
		var category = projectCategoryList[i];
		var li = document.createElement("li");
		li.setAttribute("class", "selected-item");
		li.setAttribute("onclick", "findProjectByCategory(" + category.categoryId + ", this)");
		li.setAttribute("style", "cursor:pointer");
		li.innerHTML = category.categoryName;
		jQuery("#projectCategoryUl").append(li);
	}
	
}

//根据项目系列查询项目
function findProjectByCategory(categoryId, obj) {
	cleanSelectProject();
	jQuery(obj).addClass("active").siblings().removeClass("active");
	jQuery.ajax({
		type : "post",
		dataType : "json",
		url : baseUrl + "appoint/action/findProjectByCategoryId",
		data : {categoryId : categoryId, levelId : globalLevelId},
		success : function(returnData) {
			refreshProject(returnData.msg);
		}
	});
}

//刷新项目
function refreshProject(projectList) {
	cleanProjectList();
	for (var i=0; i<projectList.length; i++) {
		var project = projectList[i];
		
		var li = document.createElement("li");
		li.setAttribute("class", "detail-item");
		li.setAttribute("style", "cursor:pointer");
		li.setAttribute("onclick", "clickProject(this, " + project.projectId + "," + project.projectPrice +")");
		
		var divOfName = document.createElement("div");
		var spanOfName = document.createElement("span");
		spanOfName.setAttribute("class", "name");
		/*spanOfName.innerHTML = project.projectName;*/
		divOfName.appendChild(spanOfName);
		li.appendChild(divOfName);
		
		var divOfPrice = document.createElement("div");
		divOfPrice.innerHTML = "会员价:";
		var spanOfPrice = document.createElement("span");
		spanOfPrice.setAttribute("class", "item-price");
		spanOfPrice.innerHTML = project.projectPrice;
		divOfPrice.appendChild(spanOfPrice);
		li.appendChild(divOfPrice);
		
		projectUl.appendChild(li);
	}
}

//点击项目
function clickProject(obj, projectId, projectPrice) {
	globalProjectId = projectId;
	globalProjectPrice = projectPrice;
	jQuery("#selectProject").text(jQuery(obj).children("div:eq(0)").children("span").text());
	
	//点击头像出现被选中员工样式
	jQuery(obj).addClass("active").siblings().removeClass("active");
	
}

//新增会员预约项目
function addAppointProject() {
	var memberId = jQuery("input[name='memberId']").val();
	var customerName = jQuery("#customerName").val();
	var customerPhone = jQuery("#customerPhone").val();
	//如果是会员
	if(memberId != null && memberId.length != 0) {
		customerName = globalCustomerName;
		customerPhone = globalCustomerPhone;
	} else { //否则就是散客
		if (customerName == null || customerName.length == 0) {
			dialog("请输入客户姓名");
			return;
		}
		if (customerPhone == null || customerPhone.length == 0) {
			dialog("请输入客户手机号码");
			return;
		}
		if (isNaN(customerPhone)){
	        dialog("手机号码只能为数字！");
	        return;
	     }
		if(customerPhone.length != 11){
			dialog("手机号码为11位数！");
			return;
		}
	}
	if (globalEmployeeId == null || globalEmployeeId.length == 0) {
		dialog("请选择被预约员工");
		return;
	}
	if (globalProjectId == null || globalProjectId.length == 0) {
		dialog("请选择被预约项目");
		return;
	}
	jQuery.ajax({
		type : "post",
		dataType : "json",
		url : baseUrl + "appoint/action/addAppointProject",
		data : {employeeId : globalEmployeeId, projectId : globalProjectId, 
			appointmentDate : globalSelectMonthAndDay, appointmentTime : globalTime, appointmentPrice : globalProjectPrice, 
			memberId : memberId},
		success : function(returnData) {
			dialog(returnData.msg);
			//新增成功刷新页面
			//findMemberAppointByDate(null);
			window.location.reload();
		}
	});
}

//------------------

//点击滚动日期(根据日期(yyyy-MM-dd)查询会员预约)
function findMemberAppointByDate(obj) {
	/*
	 * obj == this(注意这个this不是字符串),代表是事件源信息点击滚动日期过来;
	 * obj == null,代表页面刚刚加载时调用;
	 * obj == query,代表是点击页面上查询按钮出来的
	 */
	if (obj != null && obj != 'query') {  //代表点击滚动日期事件
		jQuery(obj).addClass("current").siblings().removeClass("current");
		globalSelectMonthAndDay = jQuery(obj).children("span").text();
		globalSelectWeekDay = jQuery(obj).children("p").text();
		//根据点击日期获取的月-日(MM-dd)和当前的月日比较得到年(yyyy)
		var year = getYearOfNowMonthDayThanParamMonthDay(jQuery(obj).children("span").text());
		globalSelectYearAndMonthAndDay = year + "-" + globalSelectMonthAndDay;
	}
	else if (obj != null && obj == 'query') {  //代表点击页面查询按钮
		var queryYearMonthDay = jQuery("#editOfSignOutTime").val();
		if (queryYearMonthDay == null || queryYearMonthDay == "" || queryYearMonthDay.length == 0) {
			dialog("请输入查询日期");
			return;
		}
		var queryYearMonthDayArray = queryYearMonthDay.split("-");
		globalSelectMonthAndDay = queryYearMonthDayArray[1] + "-" + queryYearMonthDayArray[2];
		globalSelectYearAndMonthAndDay = queryYearMonthDay;
	}
	cleanAppointEmployeeList();
	jQuery.ajax({
		async : false,
		cache : false,
		type : "post",
		dataType : "json",
		url : baseUrl + "appoint/action/findMemberAppointByDate",
		data : {date : globalSelectMonthAndDay},
		success : function(returnData) {
			refreshMemberAppointmentSum(returnData.msg.memberAppointmentSumDto);
			refreshAppointEmployee(returnData.msg.appointEmployeeList);
		}
	});
}

//刷新会员预约汇总信息
function refreshMemberAppointmentSum(memberAppointmentSumDto) {
	jQuery("#memberAppointmentSumDiv span:eq(1)").text(memberAppointmentSumDto.allAppointEmployeeNum);
	jQuery("#memberAppointmentSumDiv span:eq(3)").text(memberAppointmentSumDto.allAppointMemberNum);
	jQuery("#memberAppointmentSumDiv span:eq(5)").text(memberAppointmentSumDto.allAppointPriceNum);
	jQuery("#memberAppointmentSumDiv span:eq(7)").text(memberAppointmentSumDto.successServiceMemberNum);
	jQuery("#memberAppointmentSumDiv span:eq(9)").text(memberAppointmentSumDto.cancelNum);
	if (memberAppointmentSumDto.allAppointEmployeeNum == 0) {
		jQuery("#memberAppointmentSumDiv span:eq(11)").text("0%" );
	} else {
		jQuery("#memberAppointmentSumDiv span:eq(11)").text((memberAppointmentSumDto.cancelNum / memberAppointmentSumDto.allAppointEmployeeNum).toFixed(2) * 100 + "%" );
	}
	
}

//刷新被预约员工信息
/*
   //被预约员工
 * <div class="yuyue-p">
		<i class="yuan"> 
			<span class="iconfont icon-xuanzhong"></span>
		</i>
		<img src="../../assets/images/yuyue-pic.png" alt="" class="fl" /> 
		<span class="fl">
			<span>1010</span> 
			<br />
			<span class="yuyue-name">张进军</span>
		</span>
	</div>
	
	//被预约员工弹出框
	<div class="yuyue-yg-s hide">
        <div>
            <div class="modal-dialog" role="document">
                <div class="modal-content yuyue-yg-modal">
                    <div class="modal-header">
                        <img  class="yuyue-pic-m fl" src="../../assets/images/yuyue-pic.png" alt=""/>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title fl">1013 老魏</h4>
                    </div>
                    <div class="modal-body">
                        <ul class="yuyue-yg">
                            <li><span>客户姓名：</span><span>张三丰</span></li>
                            <li><span>张三丰：</span><span>15865695569</span></li>
                            <li><span>预约项目：</span><span>泰式经典美发</span></li>
                            <li><span>预约时间：</span><span>上午10：00</span></li>
                            <li><span>预约方式：</span><span>电话预约</span></li>
                        </ul>
                    </div>
                    <div class="modal-footer">
                        <div class="fr f-btn">
                            <button class="btn yy-btn">修改预约</button>
                            <button class="btn yy-btn1">确定预约</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	//灰色勾号(绿色和灰色的区别在于div样式多了个active)
	<div class="yuyue-p">
	<span class="iconfont icon-xuanzhong"></span>
	//灰色感叹号
	<div class="yuyue-p">
	<span class="iconfont icon-caution"></span>
	//绿色勾号
	<div class="yuyue-p active">
	<span class="iconfont icon-xuanzhong"></span>
	//绿色感叹号
	<div class="yuyue-p active">
	<span class="iconfont icon-caution"></span>
	//绿色等待符号
	<div class="yuyue-p active">
	<span class="iconfont icon-yuyue1"></span>
	
	//加号
	<div class="add">
		<span class="iconfont icon-jiahao"></span>
	</div>
 */
function refreshAppointEmployee(memberAppointmentList) {
	for (var j=0; j<memberAppointmentList.length; j++) {
		var memberAppointment = memberAppointmentList[j];
		
		var div = document.createElement("div");
		
		var i = document.createElement("i");
		i.setAttribute("class", "yuan");
		var divISpan = document.createElement("span");
		divISpan.setAttribute("class", "iconfont icon-xuanzhong");  //这个是预约状态图标
		i.appendChild(divISpan);
		div.appendChild(i);
		
		var img = document.createElement("img");
		img.setAttribute("class", "fl");
		img.setAttribute("src", picUrl + memberAppointment.employeeInfo.headImage);
		div.appendChild(img);
		
		var span = document.createElement("span");
		span.setAttribute("class", "fl");
		var spanSpanOfEmployeeCode = document.createElement("span");
		spanSpanOfEmployeeCode.innerHTML = memberAppointment.employeeInfo.employeeCode;
		span.appendChild(spanSpanOfEmployeeCode);
		var spanBr = document.createElement("br");
		span.appendChild(spanBr);
		var spanSpanOfEmployeeName = document.createElement("span");
		spanSpanOfEmployeeName.setAttribute("class", "yuyue-name");
		spanSpanOfEmployeeName.innerHTML = memberAppointment.employeeInfo.name;
		span.appendChild(spanBr);
		span.appendChild(spanSpanOfEmployeeName);
		div.appendChild(span);
		
		//被预约员工弹出框
		var div2 = document.createElement("div");
		div.appendChild(div2);
		div2.setAttribute("class", "yuyue-yg-s hide");
		
		var div3 = document.createElement("div");
		div2.appendChild(div3);
		var div4 = document.createElement("div");
		div3.appendChild(div4);
		div4.setAttribute("class", "modal-dialog");
		div4.setAttribute("role", "document");
		var div5 = document.createElement("div");
		div4.appendChild(div5);
		div5.setAttribute("class", "modal-content yuyue-yg-modal");
		
		var div6_1 = document.createElement("div");
		div5.appendChild(div6_1);
		div6_1.setAttribute("class", "modal-header");
		
		var div6_1_img = document.createElement("img");
		div6_1.appendChild(div6_1_img);
		div6_1_img.setAttribute("class", "yuyue-pic-m fl");
		div6_1_img.setAttribute("src", picUrl + memberAppointment.employeeInfo.headImage);
		
		var div6_1_h4 = document.createElement("h4");
		div6_1.appendChild(div6_1_h4);
		div6_1_h4.setAttribute("class", "modal-title fl");
		div6_1_h4.innerHTML = memberAppointment.employeeInfo.name;
		
		var div6_2 = document.createElement("div");
		div5.appendChild(div6_2);
		div6_2.setAttribute("class", "modal-body");
		
		var div6_2_ul = document.createElement("ul");
		div6_2.appendChild(div6_2_ul);
		div6_2_ul.setAttribute("class", "yuyue-yg");
		
		var div6_2_li1 = document.createElement("li");
		div6_2_ul.appendChild(div6_2_li1);
		var div6_2_li1_span1 = document.createElement("span");
		div6_2_li1_span1.innerHTML = "客户姓名：";
		div6_2_li1.appendChild(div6_2_li1_span1);
		var div6_2_li1_span2 = document.createElement("span");
		div6_2_li1_span2.innerHTML = memberAppointment.name;
		div6_2_li1.appendChild(div6_2_li1_span2);
		
		var div6_2_li2 = document.createElement("li");
		div6_2_ul.appendChild(div6_2_li2);
		var div6_2_li2_span1 = document.createElement("span");
		div6_2_li2_span1.innerHTML = "客户电话：";
		div6_2_li2.appendChild(div6_2_li2_span1);
		var div6_2_li2_span2 = document.createElement("span");
		div6_2_li2_span2.innerHTML = memberAppointment.phone;
		div6_2_li2.appendChild(div6_2_li2_span2);
		
		var div6_2_li3 = document.createElement("li");
		div6_2_ul.appendChild(div6_2_li3);
		var div6_2_li3_span1 = document.createElement("span");
		div6_2_li3_span1.innerHTML = "预约大项：";
		div6_2_li3.appendChild(div6_2_li3_span1);
		var div6_2_li3_span2 = document.createElement("span");
		console.log(memberAppointment);
		div6_2_li3_span2.innerHTML = memberAppointment.category.categoryName;;//memberAppointment.projectInfo.projectName;
		div6_2_li3.appendChild(div6_2_li3_span2);
		
		var div6_2_li4 = document.createElement("li");
		div6_2_ul.appendChild(div6_2_li4);
		var div6_2_li4_span1 = document.createElement("span");
		div6_2_li4_span1.innerHTML = "预约时间：";
		div6_2_li4.appendChild(div6_2_li4_span1);
		var div6_2_li4_span2 = document.createElement("span");
		div6_2_li4_span2.innerHTML = memberAppointment.appointmentDate + " " + memberAppointment.appointmentTime;
		div6_2_li4.appendChild(div6_2_li4_span2);
		
		var div6_2_li5 = document.createElement("li");
		div6_2_ul.appendChild(div6_2_li5);
		var div6_2_li5_span1 = document.createElement("span");
		div6_2_li5_span1.innerHTML = "预约方式：";
		div6_2_li5.appendChild(div6_2_li5_span1);
		var div6_2_li5_span2 = document.createElement("span");
		if (memberAppointment.appointmentWay == 1) {
			div6_2_li5_span2.innerHTML = "电话预约";
		} else {
			div6_2_li5_span2.innerHTML = "微信预约";
		}
		div6_2_li5.appendChild(div6_2_li5_span2);
		
		var div6_3 = document.createElement("div");
		div5.appendChild(div6_3);
		div6_3.setAttribute("class", "modal-footer");
		
		var div6_3_div = document.createElement("div");
		div6_3.appendChild(div6_3_div);
		div6_3_div.setAttribute("class", "fr f-btn");
		var div6_3_div_btn = document.createElement("button");
		div6_3_div.appendChild(div6_3_div_btn);
		div6_3_div_btn.setAttribute("class", "btn yy-btn");
		div6_3_div_btn.setAttribute("onclick", "cancelAppointment(" + memberAppointment.appointmentId + ")");
		div6_3_div_btn.innerHTML = "取消预约";
		
		
		//当前时间是否在预约时间后面(true:是)
		var isAfterNow = appointTimeIsAfterNowFunction(memberAppointment.appointmentTime);
		//添加绿色勾(appointmentStatus=3)，绿色钟(appointmentStatus=2&&时间还没过)，灰色钟(appointmentStatus=2&&时间已经过了)
		if(memberAppointment.appointmentStatus == 3) {  //绿色勾
			div.setAttribute("class", "yuyue-p active");
			divISpan.setAttribute("class", "iconfont icon-xuanzhong");
		} else if (memberAppointment.appointmentStatus == 2 && isAfterNow == false) {  //绿色钟
			div.setAttribute("class", "yuyue-p active");
			divISpan.setAttribute("class", "iconfont icon-yuyue1");
		} else if (memberAppointment.appointmentStatus == 2 && isAfterNow == true) { //灰色钟
			div.setAttribute("class", "yuyue-p");
			divISpan.setAttribute("class", "iconfont icon-yuyue1");
		} else {  //最后就显示灰色感叹号
			div.setAttribute("class", "yuyue-p");
			divISpan.setAttribute("class", "iconfont icon-caution");
		}
		
		//24小时，一共48个的id段，用memberAppointment.appointmentTime来确定id
		var appointmentTime = memberAppointment.appointmentTime;
		var liId = document.getElementById(memberAppointment.appointmentTime + "OfTheClock");
		if (liId != null) {
			liId.appendChild(div);
		}
	}
	//添加加号
	addId();
}

/**
 * 批量添加加号
 * 得到那个每个时间段的选择器:jQuery(".status-wrap-time li")
 * 遍历选择器得到时间，与当前时间毫秒值比较是否要加加号
 */
function addId() {
	var liArray = jQuery(".status-wrap-time li");
	for (var i = 0; i < liArray.length; i++) {
		var addDiv = document.createElement("div");
		addDiv.setAttribute("class", "add");
		var addSpan = document.createElement("span");
		addSpan.setAttribute("class", "iconfont icon-jiahao");
		addDiv.appendChild(addSpan);
		var date = new Date();
		
		var li = liArray[i];
		var d = new Date(globalSelectYearAndMonthAndDay + " " + li.innerHTML);
		if (d.getTime() > date.getTime()) {
			document.getElementById(li.innerHTML + "OfTheClock").appendChild(addDiv);
		}
	}
}

//取消预约
function cancelAppointment(appointmentId) {
	if (appointment_id = null) {
		return "预约id错误";
	}
	if (window.confirm("确认取消吗")) {
		jQuery.ajax({
			type : "post",
			dataType : "json",
			url : baseUrl + "appoint/action/cancelAppointment",
			data : {appointmentId : appointmentId},
			success : function(returnData) {
				dialog("取消成功");
				window.location.reload();
			}
		});
	}
	
}

/**
 * 注意，只适合该滚动日期场景，因为滚动日期是一个月内
 * 点击日期滚动条时根据参数月日(MM-dd)和当前时间比较得到参数月日对应的年(yyyy)
 * @param paramMonthDay  参数月日(MM-dd)
 */
function getYearOfNowMonthDayThanParamMonthDay(paramMonthDay) {
	var nowMonth = new Date().getMonth();
	var nowDay = new Date().getDate();
	var paramMonthDayArray = paramMonthDay.split("-");
	//if 参数月份 < 当前月份，说明跨年了
	if (paramMonthDayArray[0] < nowMonth) {
		return new Date().getFullYear() + 1;
	}
	//参数月份 = 当前月份
	else if (paramMonthDayArray[0] = nowMonth) {
		return new Date().getFullYear();
	}
	//参数月份 > 当前月份
	else if (paramMonthDayArray[0] > nowMonth) {
		return new Date().getFullYear();
	}
}
/**
 * 判断当前时间是否超过拉取预约时间(true: 超过)
 * @param appointTime  预约时间(HH:mm)
 */
function appointTimeIsAfterNowFunction(appointTime) {
	var now = new Date();
	var nowHours = now.getHours();
	var nowMinutes =  now.getMinutes();
	var appointTimeArray = appointTime.split(":");
	if (nowHours > appointTimeArray[0]) {
		return true;
	} else if (nowHours < appointTimeArray[0]) {
		return false;
	} else {
		if (nowMinutes <= appointTimeArray[1]) {
			return false;
		} else {
			return true;
		}
	}
}
/**
 * 遍历时间(MM-dd和HH:mm)是否可预约(判断在每个时间(HH:mm)是否要显示+号)
 * @param paramHour  小时参数，页面上的每个小时
 * @param paramMinute  分钟参数，只有00和30
 * return ture 该时间可预约，false 不可
 */
function isAfterNowFunction(paramHour, paramMinute) {
	var now = new Date();
	/*var nowYear = now.getFullYear();
	var nowMonth = now.getMonth();
	var nowDay = now.getDate();*/
	var nowHours = now.getHours();
	var nowMinutes = now.getMinutes();
	var globalSelectYearAndMonthAndDayArr = globalSelectYearAndMonthAndDay.split("-");
	var paramDate = new Date().setFullYear(globalSelectYearAndMonthAndDayArr[0], globalSelectYearAndMonthAndDayArr[1] - 1, globalSelectYearAndMonthAndDayArr[2]);
	//if 参数日期(yyyy-MM-dd) 在当前日期后面
	if (paramDate > now) {
		return true;
	} 
	//if 参数日期(yyyy-MM-dd) 在当前日期前面
	else if (paramDate < now) {
		return false;
	}
	//if 相等(说明在同一天)就需要判断小时
	else {
		if (paramHour > nowHours) {
			return true;
		}
		else if (paramHour < nowHours) {
			return false;
		}
		//if (小时相等)判断分钟
		else {
			if (paramMinute > nowMinutes) {
				return true;
			}
			else {
				return false;
			}
		}
	}
}

//--------------


//清空滚动员工列表
function cleanScrollableListEmployee () {
	jQuery("#appointmentEmployeeDiv ul").remove();
}
//清空项目系列列表
function cleanProjectListCategoryList() {
	//jQuery("#projectCategoryUl li").remove();
}
//清空项目列表
function cleanProjectList() {
	jQuery("#projectUl li").remove();
}
//清空被选中预约员工
function cleanSelectEmployee() {
	jQuery("#selectEmployee").text("");
	globalEmployeeId = "";
}
//清空被选中预约项目
function cleanSelectProject() {
	jQuery("#selectProject").text("");
	globalProjectId = "";
	globalProjectPrice = "";
}
/**
 * 清空一日48个半小时段预约详情列表
 * 可以根据48个id依次清空
 * 此处根据id规律遍历清空
 */
function cleanAppointEmployeeList() {
	for (var i=0; i<24; i++) {
		var hours = i;
		if (hours < 10) {
			hours = "0" + hours;
		}
		document.getElementById(hours + ":00OfTheClock").innerHTML = "";
		document.getElementById(hours + ":30OfTheClock").innerHTML = "";
//		jQuery("#" + hours + ":00OfTheClock").text("");  //不起效
//		document.getElementById(hours + ":30OfTheClock").removeChild("div");  //不起效
		
	}
}

//-----------------------begin老王memberData.js--------------------
var memberDataId = "";

var memberDatePageNo = {"moneyFlowPageNo":1, "orderComboPageNo":1, "integralFlowPageNo":1, "orderProjectPageNo":1, "orderGoodsPageNo":1, "giftMoneyFlowPageNo":1, "debtFlowPageNo":1};

var memberDatePageSize = {"moneyFlowPageSize":5, "orderComboPageSize":5, "integralFlowPageSize":5, "orderProjectPageSize":5, "orderGoodsPageSize":5, "giftMoneyFlowPageSize":5, "debtFlowPageSize":5};

var memberDateTotalPage = {"moneyFlowTotalPage":0, "orderComboTotalPage":0, "integralFlowTotalPage":0, "orderProjectTotalPage":0, "orderGoodsTotalPage":0, "giftMoneyFlowTotalPage":0, "debtFlowTotalPage":0};

var businessTypeArray = new Array("","消费","充值","转账","开卡","升级", "卡金", "会员导入", "数据迁移");

var flowTypeArray =new Array("","-","+");

var debtTypeArray =new Array("","挂账","还款");

//hover delegate绑定，鼠标移动到预约员工显示弹框，移走隐藏预约员工弹框
jQuery("body").delegate(".yuyue-p", "mouseenter", function (event) {  
	jQuery(this).children(".yuyue-yg-s").removeClass("hide");
}).delegate(".yuyue-p", "mouseleave", function (event) {  
	jQuery(this).children(".yuyue-yg-s").addClass("hide");
});


jQuery("#orderComboTBODY").delegate(".project-toggle", "click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
    var thisElement = jQuery(obj);
    var thisElementParent = thisElement.parents("tr")
    var projectPart = thisElementParent.nextUntil(".single");
    if(!projectPart.is(":visible")){
        projectPart.show();
    }
    else{
        projectPart.hide();
    }
});

function answerFunction(){
	jQuery("td[name='problem']").addClass("hide");
	jQuery("td[name='answer']").removeClass("hide");
}

function problemFunction(){
	jQuery("td[name='answer']").addClass("hide");
	jQuery("td[name='problem']").removeClass("hide");
}


function page(moneyFlowPageNo, moneyFlowPageSize, type){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMoneyFlow",
		data : "memberId="+memberDataId+"&pageNo="+moneyFlowPageNo+"&pageSize="+moneyFlowPageSize+"&type="+type,
		async:false,//使用同步的Ajax请求  
	    beforeSend : function(){
		    jQuery("#loadingWrap").css("display","block");
		  },
	    complete : function(){
		  setTimeout(function(){
			  jQuery("#loadingWrap").css("display","none");//请求完毕后将加载效果移除
		  },800);
	    },
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var page = e.msg;
			if (type == 4) {
				moneyFlow(page);
				initPageClickData("pagination-demo4",type,page.pageNo,page.pageSize,page.totalPage);
			}
			//疗程
	        else if (type == 2) {
	        	orderCombo(page);
	        	initPageClickData("pagination-demo2",type,page.pageNo,page.pageSize,page.totalPage);
	        }
	        //积分流水
	        else if (type == 5) {
	        	integralFlow(page);
	        	initPageClickData("pagination-demo5",type,page.pageNo,page.pageSize,page.totalPage);
	        }
			//礼金流水
	        else if (type == 6) {
	        	giftMoneyFlow(page);
	        	initPageClickData("pagination-demo6",type,page.pageNo,page.pageSize,page.totalPage);
	        }
			//欠款流水
	        else if (type == 7) {
	        	debtFlow(page);
	        	initPageClickData("pagination-demo7",type,page.pageNo,page.pageSize,page.totalPage);
	        }
	        //项目
	        else if (type == 1) {
	        	orderProject(page);
	        	initPageClickData("pagination-demo1",type,page.pageNo,page.pageSize,page.totalPage);
	        }
	        //商品
	        else if (type == 3) {
	        	orderGoods(page);
	        	initPageClickData("pagination-demo3",type,page.pageNo,page.pageSize,page.totalPage);
	        }
		}
	});
}

function moneyFlow(page) {
	memberDateTotalPage.moneyFlowTotalPage = page.totalPage;
	addDisable("previousMoneyFlow");
	if(memberDateTotalPage.moneyFlowTotalPage == 1){
		addDisable("nextMoneyFlow");
	}
	var results = page.results;
	jQuery("#moneyFlowTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var moneyFlowDto = results[i];
		var a = i + 1;
		jQuery("#moneyFlowTBODY").append("<tr class='single'>"+
				                            "<td>"+a+"</td>"+
			                                "<td>"+businessTypeArray[moneyFlowDto.businessType]+"</td>"+
			                               /* "<td class='can-click'>"+moneyFlowDto.orderName+"</td>"+*/
			                                /*"<td class='blue'>"+moneyFlowDto.balanceAmount+"</td>"+*/
			                                "<td class='blue'>"+flowTypeArray[moneyFlowDto.flowType] + moneyFlowDto.flowAmount+"</td>"+
			                                "<td>"+moneyFlowDto.flowTime+"</td>"+
			                                "<td>"+moneyFlowDto.lastOperatorName+"</td>"+
			                                "<td>"+moneyFlowDto.storeName+"</td>"+
			                            "</tr>");
	}
}

function orderProject(page) {
	memberDateTotalPage.orderProjectTotalPage = page.totalPage;
	addDisable("previousOrderProject");
	if(memberDateTotalPage.orderProjectTotalPage == 1){
		addDisable("nextOrderProject");
	}
	var results = page.results;
	jQuery("#orderProjectTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var orderProjectDto = results[i];
		jQuery("#orderProjectTBODY").append("<tr class='single'>"+
					                              "<td>"+orderProjectDto.orderCode+"</td>"+
					                              "<td></td>"+
					                              /*"<td>"+orderProjectDto.projectName+"</td>"+*/
					                              "<td class='blue'>"+orderProjectDto.discountAmount+"</td>"+
					                              "<td><span class='cursor'>"+orderProjectDto.privilegeInfo+" </span><span class='red'>-"+orderProjectDto.privilegeMoney+"</span></td>"+
					                              "<td class='red'>"+orderProjectDto.realPrice+"</td>"+
					                              "<td>"+orderProjectDto.createTime+"</td>"+
					                              "<td>"+orderProjectDto.storeName+"</td>"+
					                         "</tr>");
	}
}

function integralFlow(page) {
	memberDateTotalPage.integralFlowTotalPage = page.totalPage;
	addDisable("previousIntegralFlow");
	if(memberDateTotalPage.integralFlowTotalPage == 1){
		addDisable("nextIntegralFlow");
	}
	var results = page.results;
	jQuery("#integralFlowTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var integralFlow = results[i];
		jQuery("#integralFlowTBODY").append("<tr class='single'>"+
				                                  "<td>"+businessTypeArray[integralFlow.businessType]+"</td>"+
				                                  "<td class='red'>"+flowTypeArray[integralFlow.flowType] + integralFlow.flowAmount +"</td>"+
				                                  "<td>"+integralFlow.businessDesc+"</td>"+
				                                  "<td>"+integralFlow.flowTime+"</td>"+
				                              "</tr>");
	}
}

function orderGoods(page) {
	memberDateTotalPage.orderGoodsTotalPage = page.totalPage;
	addDisable("previousOrderGoods");
	if(memberDateTotalPage.orderGoodsTotalPage == 1){
		addDisable("nextOrderGoods");
	}
	var results = page.results;
	jQuery("#orderGoodsTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var orderGoodsDto = results[i];
		jQuery("#orderGoodsTBODY").append("<tr class='single'>"+
				                              "<td>"+orderGoodsDto.orderCode+"</td>"+
				                              "<td></td>"+
				                              /*"<td>"+orderGoodsDto.projectName+"</td>"+*/
				                              "<td>"+orderGoodsDto.lastOperatorName+"</td>"+
				                              "<td class='blue'>"+orderGoodsDto.discountAmount+"</td>"+
				                              "<td><span class='cursor'>"+orderGoodsDto.privilegeInfo+" </span><span class='red'>-"+orderGoodsDto.privilegeMoney+"</span></td>"+
				                              "<td class='red'>"+orderGoodsDto.realPrice+"</td>"+
				                              "<td>"+orderGoodsDto.createTime+"</td>"+
				                              "<td>"+orderGoodsDto.storeName+"</td>"+
				                             "</tr>");
				}
}

function orderCombo(page) {
	memberDateTotalPage.orderComboTotalPage = page.totalPage;
	addDisable("previousOrderCombo");
	if(memberDateTotalPage.orderComboTotalPage == 1){
		addDisable("nextOrderCombo");
	}
	var results = page.results;
	jQuery("#orderComboTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var orderComboDto = results[i];
		jQuery("#orderComboTBODY").append("<tr class='single'>"+
				                              "<td class='cursor mr10 project-toggle'>"+orderComboDto.comboName+"<span class='icon-chevron-down'></span></td>"+
				                              "<td>"+orderComboDto.projectCount+"</td>"+
				                              "<td class='red'>"+orderComboDto.remainingCount+"</td>"+
				                              "<td class='blue'>"+orderComboDto.projectAmount+"</td>"+
				                              "<td class='red'>"+orderComboDto.comboPrice+"</td>"+
				                              "<td>"+orderComboDto.lastOperatorName+"</td>"+
				                              "<td>"+orderComboDto.createTime+"</td>"+
				                              "<th>"+orderComboDto.overdueTime+"</th>"+
				                              "<td>"+orderComboDto.storeName+"</td>"+
				                          "</tr>");
		var projectList = orderComboDto.projectList;
		for (var j = 0; j < projectList.length; j++) {
			var memberComboProject = projectList[j];
			jQuery("#orderComboTBODY").append("<tr class='project-part hide'>"+
				                                  "<td></td>"+
				                                  /*"<td>"+memberComboProject.projectName+"</td>"+*/
				                                  "<td>"+memberComboProject.projectCount+"</td>"+
				                                  "<td class='red'>"+memberComboProject.remainingCount+"</td>"+
				                                  "<td class='blue'>"+memberComboProject.projectPrice+"</td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                              "</tr>");
		}
	}
}

function giftMoneyFlow(page) {
	memberDateTotalPage.giftMoneyFlowTotalPage = page.totalPage;
	addDisable("previousGiftMoneyFlow");
	if(memberDateTotalPage.giftMoneyFlowTotalPage <= 1){
		addDisable("nextGiftMoneyFlow");
	}
	var results = page.results;
	jQuery("#giftMoneyTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var giftMoneyFlow = results[i];
		jQuery("#giftMoneyTBODY").append("<tr class='single'>"+
				                                  "<td>"+businessTypeArray[giftMoneyFlow.businessType]+"</td>"+
				                                  "<td class='red'>"+flowTypeArray[giftMoneyFlow.flowType] + giftMoneyFlow.flowAmount +"</td>"+
				                                  "<td>"+giftMoneyFlow.businessDesc+"</td>"+
				                                  "<td>"+giftMoneyFlow.flowTime+"</td>"+
				                              "</tr>");
	}
}

function debtFlow(page) {
	removeDisable("nextDebtFlow");
	memberDateTotalPage.debtFlowTotalPage = page.totalPage;
	addDisable("previousDebtFlow");
	if(memberDateTotalPage.debtFlowTotalPage <= 1){
		addDisable("nextDebtFlow");
	}
	var results = page.results;
	jQuery("#debtTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var debtFlow = results[i];
		jQuery("#debtTBODY").append("<tr class='single'>"+
				                                  "<td>"+debtTypeArray[debtFlow.flowType]+"</td>"+
				                                  "<td class='red'>"+flowTypeArray[debtFlow.flowType] + debtFlow.inAmount +"</td>"+
				                                  "<td>"+debtFlow.flowDesc+"</td>"+
				                                  "<td>"+debtFlow.flowDebtTime+"</td>"+
				                              "</tr>");
	}
}

//上一页
function previousPageButton(type){
	if (type == 4) {
		if(memberDatePageNo.moneyFlowPageNo == 1){
			return;
		}
		memberDatePageNo.moneyFlowPageNo = memberDatePageNo.moneyFlowPageNo - 1;
		page(memberDatePageNo.moneyFlowPageNo, memberDatePageSize.moneyFlowPageSize, 4);
		
		if(memberDatePageNo.moneyFlowPageNo == 1){
			addDisable("previousMoneyFlow");
		}
		else {
			removeDisable("nextMoneyFlow");
			removeDisable("previousMoneyFlow");
		}
	}
	//疗程
    else if (type == 2) {
    	if(memberDatePageNo.orderComboPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.orderComboPageNo = memberDatePageNo.orderComboPageNo - 1;
		page(memberDatePageNo.orderComboPageNo, memberDatePageSize.orderComboPageSize, 2);
		
		if(memberDatePageNo.orderComboPageNo == 1){
			addDisable("previousOrderCombo");
		}
		else {
			removeDisable("nextOrderCombo");
			removeDisable("previousOrderCombo");
		}
    }
    //积分流水
    else if (type == 5) {
    	if(memberDatePageNo.integralFlowPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.integralFlowPageNo = memberDatePageNo.integralFlowPageNo - 1;
		page(memberDatePageNo.integralFlowPageNo, memberDatePageSize.integralFlowPageSize, 5);
		
		if(memberDatePageNo.integralFlowPageNo == 1){
			addDisable("previousIntegralFlow");
		}
		else {
			removeDisable("nextIntegralFlow");
			removeDisable("previousIntegralFlow");
		}
    }
    //礼金流水
    else if (type == 6) {
    	if(memberDatePageNo.giftMoneyFlowPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.giftMoneyFlowPageNo = memberDatePageNo.giftMoneyFlowPageNo - 1;
		page(memberDatePageNo.giftMoneyFlowPageNo, memberDatePageSize.giftMoneyFlowPageSize, 6);
		
		if(memberDatePageNo.giftMoneyFlowPageNo == 1){
			addDisable("previousGiftMoneyFlow");
		}
		else {
			removeDisable("nextGiftMoneyFlow");
			removeDisable("previousGiftMoneyFlow");
		}
    }
	//欠款流水
    else if (type == 7) {
    	if(memberDatePageNo.debtFlowPageNo == 1){
			return;
		}
    	memberDatePageNo.debtFlowPageNo = memberDatePageNo.debtFlowPageNo - 1;
		page(memberDatePageNo.debtFlowPageNo, memberDatePageSize.debtFlowPageSize, 7);
		
		if(memberDatePageNo.debtFlowPageNo == 1){
			addDisable("previousDebtFlow");
		}
		else {
			removeDisable("nextDebtFlow");
			removeDisable("previousDebtFlow");
		}
    }
    //项目
    else if (type == 1) {
    	if(memberDatePageNo.orderProjectPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.orderProjectPageNo = memberDatePageNo.orderProjectPageNo - 1;
		page(memberDatePageNo.orderProjectPageNo, memberDatePageSize.orderProjectPageSize, 1);
		
		if(memberDatePageNo.orderProjectPageNo == 1){
			addDisable("previousOrderProject");
		}
		else {
			removeDisable("nextOrderProject");
			removeDisable("previousOrderProject");
		}
    }
    //商品
    else if (type == 3) {
    	if(memberDatePageNo.orderGoodsPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.orderGoodsPageNo = memberDatePageNo.orderGoodsPageNo - 1;
		page(memberDatePageNo.orderGoodsPageNo, memberDatePageSize.orderGoodsPageSize, 3);
		
		if(memberDatePageNo.orderGoodsPageNo == 1){
			addDisable("previousOrderGoods");
		}
		else {
			removeDisable("nextOrderGoods");
			removeDisable("previousOrderGoods");
		}
    }
}
//下一页
function nextPageButton (type){
	if (type == 4) {
		if(memberDatePageNo.moneyFlowPageNo == memberDateTotalPage.moneyFlowTotalPage){
			return;
		}
		memberDatePageNo.moneyFlowPageNo = memberDatePageNo.moneyFlowPageNo + 1;
		page(memberDatePageNo.moneyFlowPageNo, memberDatePageSize.moneyFlowPageSize, 4);
		
		if(memberDatePageNo.moneyFlowPageNo == memberDateTotalPage.moneyFlowTotalPage){
			addDisable("nextMoneyFlow");
		}
		else {
			removeDisable("nextMoneyFlow");
		}
		removeDisable("previousMoneyFlow");
	}
	//疗程
    else if (type == 2) {
    	if(memberDatePageNo.orderComboPageNo == memberDateTotalPage.orderComboTotalPage){
			return;
		}
    	memberDatePageNo.orderComboPageNo = memberDatePageNo.orderComboPageNo + 1;
		page(memberDatePageNo.orderComboPageNo, memberDatePageSize.orderComboPageSize, 2);
		
		if(memberDatePageNo.orderComboPageNo == memberDateTotalPage.orderComboTotalPage){
			addDisable("nextOrderCombo");
		}
		else {
			removeDisable("nextOrderCombo");
		}
		removeDisable("previousOrderCombo");
    }
    //积分流水
    else if (type == 5) {
    	if(memberDatePageNo.integralFlowPageNo == memberDateTotalPage.integralFlowTotalPage){
			return;
		}
    	memberDatePageNo.integralFlowPageNo = memberDatePageNo.integralFlowPageNo + 1;
		page(memberDatePageNo.integralFlowPageNo, memberDatePageSize.integralFlowPageSize, 5);
		
		if(memberDatePageNo.integralFlowPageNo == memberDateTotalPage.integralFlowTotalPage){
			addDisable("nextIntegralFlow");
		}
		else {
			removeDisable("nextIntegralFlow");
		}
		removeDisable("previousIntegralFlow");
    }
	//礼金流水
    else if (type == 6) {
    	if(memberDatePageNo.giftMoneyFlowPageNo >= memberDateTotalPage.giftMoneyFlowTotalPage){
			return;
		}
    	memberDatePageNo.giftMoneyFlowPageNo = memberDatePageNo.giftMoneyFlowPageNo + 1;
		page(memberDatePageNo.giftMoneyFlowPageNo, memberDatePageSize.giftMoneyFlowPageSize, 6);
		
		if(memberDatePageNo.giftMoneyFlowPageNo == memberDateTotalPage.giftMoneyFlowTotalPage){
			addDisable("nextGiftMoneyFlow");
		}
		else {
			removeDisable("nextGiftMoneyFlow");
		}
		removeDisable("previousGiftMoneyFlow");
    }
	//挂账
    else if (type == 7) {
    	if(memberDatePageNo.debtFlowPageNo >= memberDateTotalPage.debtFlowTotalPage){
			return;
		}
    	memberDatePageNo.debtFlowPageNo = memberDatePageNo.debtFlowPageNo + 1;
		page(memberDatePageNo.debtFlowPageNo, memberDatePageSize.debtFlowPageSize, 7);
		
		if(memberDatePageNo.debtFlowPageNo == memberDateTotalPage.debtFlowTotalPage){
			addDisable("nextDebtFlow");
		}
		else {
			removeDisable("nextDebtFlow");
		}
		removeDisable("previousDebtFlow");
    }
    //项目
    else if (type == 1) {
    	if(memberDatePageNo.orderProjectPageNo == memberDateTotalPage.orderProjectTotalPage){
			return;
		}
    	memberDatePageNo.orderProjectPageNo = memberDatePageNo.orderProjectPageNo + 1;
		page(memberDatePageNo.orderProjectPageNo, memberDatePageSize.orderProjectPageSize, 1);
		
		if(memberDatePageNo.orderProjectPageNo == memberDateTotalPage.orderProjectTotalPage){
			addDisable("nextOrderProject");
		}
		else {
			removeDisable("nextOrderProject");
		}
		removeDisable("previousOrderProject");
    }
    //商品
    else if (type == 3) {
    	if(memberDatePageNo.orderGoodsPageNo == memberDateTotalPage.orderGoodsTotalPage){
			return;
		}
    	memberDatePageNo.orderGoodsPageNo = pageNo.orderGoodsPageNo + 1;
		page(memberDatePageNo.orderGoodsPageNo, memberDatePageSize.orderGoodsPageSize, 3);
		
		if(memberDatePageNo.orderGoodsPageNo == memberDateTotalPage.orderGoodsTotalPage){
			addDisable("nextOrderGoods");
		}
		else {
			removeDisable("nextOrderGoods");
		}
		removeDisable("previousOrderGoods");
    }
}

function addDisable(name) {
	jQuery("#" + name).addClass("page-disable");
}

function removeDisable(name) {
	jQuery("#" + name).removeClass("page-disable");
}
//实例化分页插件
function paginationDemo(pageId,page,type){
	
	jQuery('#'+pageId).empty();
	jQuery('#'+pageId).removeData("twbs-pagination");
	jQuery('#'+pageId).unbind("page");
	
	var pageNo = page.pageNo;
	var pageSize = page.pageSize;
	var totalPage = page.totalPage;
	if (totalPage == 0){
		jQuery('#'+pageId).prev().text('共找到了 0 条数据, 共 0  页');
		return;
	}
	jQuery('#'+pageId).twbsPagination({
		totalPages : totalPage,
		visiblePages : 5,
	});
	jQuery('#'+pageId).prev().text('共找到了 '+page.totalRecord+' 条数据, 共 '+totalPage+' 页');
	initPageClickData(pageId,type,pageNo,pageSize,totalPage);
}
//为分页按钮绑定事件,但是每次都要在page方法后重新调用,因为该插件会使得按钮事件消失
function initPageClickData(pageId,type,pageNo,pageSize,totalPage) {
	
	jQuery('#'+pageId).find(".first").on("click", function() {
		page(1, pageSize, type);
	});
	jQuery('#'+pageId).find(".last").on("click", function() {
		page(totalPage, pageSize, type);
	});
	jQuery('#'+pageId).find(".next").on("click", function() {
		if (pageNo >= totalPage) {
			return;
		}
		pageNo++;
		page(pageNo, pageSize, type);
	});
	jQuery('#'+pageId).find(".prev").on("click", function() {
		if (pageNo <= 1) {
			return;
		}
		pageNo--;
		page(pageNo, pageSize, type);
	});
	jQuery('#'+pageId).find(".page").on("click", function(){
		pageNum = jQuery(this).children("a").text();
		page(pageNum, pageSize, type);
	});
}

function zeroValue (value) {
	if (isEmpty(value)) {
		value = "0";
	}
	return value;
}

function againSearch(obj) {
	jQuery(obj).parents("[name='memberTR']").find("input[name='memberId']").val("");
	jQuery(obj).parents("[name='memberTR']").prev().removeClass("hide");
	jQuery(obj).parents("[name='memberTR']").addClass("hide");
}
//-----------------------end老王memberData.js--------------------

