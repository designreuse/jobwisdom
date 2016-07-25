//全局时间(yyyy-MM-dd)
var globalDate;

//存储各图表对象
//业绩分布图表
var globalPerformanceChart;
//技能分析之大小项图表
var globalSkillsOfBigAndSmallProjectChart;
//技能分析之预约/非预约图表
var globalSkillsOfIsAndNoAppointChart;
//技能分析之指定/非指定图表
var globalSkillsOfIsAndNoAssignChart;
//提成来源图表
var globalCommissionChart;

jQuery(function() {
	var date = new Date();
	jQuery(".current-select-date").text(date.getFullYear() + "-" + Number(date.getMonth() + 1) + "-" + date.getDate());
});

//查询本日/本月员工业绩详情(点击本月本日重画图表)
function findemployeeCommissionDetailByNowDayOrMonth(obj) {
	//jQuery(this).addClass("current").siblings().removeClass("current");
	//添加本日/本月样式
	jQuery(obj).addClass("active");
	jQuery(obj).find("img").removeClass("hide");
	//移除本月/本日样式
	jQuery(obj).siblings().removeClass("active");
	jQuery(obj).siblings().find("img").addClass("hide");
	var now = jQuery(obj).children("a").children("div").attr("id");
	var date = new Date();
	if (now == "selectTime") {
		jQuery(".current-select-date").text(date.getFullYear() + "-" + Number(date.getMonth() + 1) + "-" + date.getDate());
		now = "day";
		invokeAjax(now, "day");
	} else if (now == "selectDate") {
		jQuery(".current-select-date").text(date.getFullYear() + "-" + Number(date.getMonth() + 1));
		now = "month";
		invokeAjax(now, "month");
	}
}

//根据具体时间(yyyy-MM-dd)查询员工业绩详情(点击具体时间)
function findemployeeCommissionDetailByTime(time) {
	jQuery(".current-select-date").text(time);
	invokeAjax(time, "day");
}

/**
 * 调用ajax
 * @param time  时间 yyyy-MM-dd
 * @param dayOrMonth 有"day"和"month"  用于替换文字 "当日提成"/"当月提成"
 */
function invokeAjax(time, dayOrMonth) {
	//本日提成 本月提成文字切换
	if (dayOrMonth == "day") {
		jQuery(".profit span").text("当日提成");
	} else if (dayOrMonth == "month") {
		jQuery(".profit span").text("当月提成");
	}
	jQuery.ajax({
		type : "post",
		data : {employeeId : globalEmployeeId, time : time},
		url : baseUrl + "staff/action/findIndividualPerformanceByTime",
		dataType : "json",
		success : function(returnData) {
			fillMisc(returnData.msg);
			redrawPerformanceChart(returnData.msg.employeeCommissionDetailOfBossDto);
			redrawSkillsOfBigAndSmallProjectChart(returnData.msg.employeeCommissionDetailOfBossDto);
			redrawSkillsOfIsAndNoAppointChart(returnData.msg.employeeCommissionDetailOfBossDto);
			redrawSkillsOfIsAndNoAssignChart(returnData.msg.employeeCommissionDetailOfBossDto);
			redrawCommissionChart(returnData.msg.employeeCommissionDetailOfBossDto);
		}
	});
}

//填充顾客评价，工作态度等一系列杂项
function fillMisc(data) {
	//本月提成
	jQuery(".profit-num").text(data.employeeCommissionSum);
	var employeeCommissionDetailOfBossDto = data.employeeCommissionDetailOfBossDto;
	//总服务人次
	jQuery(".chart-sum").children(".num").text(Number(employeeCommissionDetailOfBossDto.customerCountOfIsAppoint + employeeCommissionDetailOfBossDto.customerCountOfNotAppoint));
	/*
	 * 顾客评价
	 */
	//该员工总共服务顾客人数(包含未评价顾客)
	var employeeEvaluateTotalCount = employeeCommissionDetailOfBossDto.employeeEvaluateFive + employeeCommissionDetailOfBossDto.employeeEvaluateFour + employeeCommissionDetailOfBossDto.employeeEvaluateThree + employeeCommissionDetailOfBossDto.employeeEvaluateTwo + employeeCommissionDetailOfBossDto.employeeEvaluateOne + employeeCommissionDetailOfBossDto.employeeEvaluateNull;
	if (employeeEvaluateTotalCount != 0) {
		//综合得分
		var avgScore = ((employeeCommissionDetailOfBossDto.employeeEvaluateFive + employeeCommissionDetailOfBossDto.employeeEvaluateNull)*5 + employeeCommissionDetailOfBossDto.employeeEvaluateFour*4 + employeeCommissionDetailOfBossDto.employeeEvaluateThree*3 + employeeCommissionDetailOfBossDto.employeeEvaluateTwo*2 + employeeCommissionDetailOfBossDto.employeeEvaluateOne*1)/employeeEvaluateTotalCount;
		jQuery("#customerEvaluationTable thead tr th span").text(avgScore.toFixed(2));
		//5分占比
		var fivePercentage = employeeCommissionDetailOfBossDto.employeeEvaluateFive/employeeEvaluateTotalCount * 100;
		jQuery("#customerEvaluationTable tbody tr:eq(0) td:eq(2)").text(fivePercentage.toFixed(2) + "%");
		//4分占比
		var fourPercentage = employeeCommissionDetailOfBossDto.employeeEvaluateFour/employeeEvaluateTotalCount * 100;
		jQuery("#customerEvaluationTable tbody tr:eq(1) td:eq(2)").text(fourPercentage.toFixed(2) + "%");
		//3分占比
		var threePercentage = employeeCommissionDetailOfBossDto.employeeEvaluateThree/employeeEvaluateTotalCount * 100;
		jQuery("#customerEvaluationTable tbody tr:eq(2) td:eq(2)").text(threePercentage.toFixed(2) + "%");
		//2分占比
		var twoPercentage = employeeCommissionDetailOfBossDto.employeeEvaluateTwo/employeeEvaluateTotalCount * 100;
		jQuery("#customerEvaluationTable tbody tr:eq(3) td:eq(2)").text(twoPercentage.toFixed(2) + "%");
		//1分占比
		var onePercentage = employeeCommissionDetailOfBossDto.employeeEvaluateOne/employeeEvaluateTotalCount * 100;
		jQuery("#customerEvaluationTable tbody tr:eq(4) td:eq(2)").text(onePercentage.toFixed(2) + "%");
		//未评分占比
		var nullPercentage = employeeCommissionDetailOfBossDto.employeeEvaluateNull/employeeEvaluateTotalCount * 100;
		jQuery("#customerEvaluationTable tbody tr:eq(5) td:eq(2)").text(nullPercentage.toFixed(2) + "%");
	} else {
		jQuery("#customerEvaluationTable thead tr th span").text(0);
	}
	//5分人数
	jQuery("#customerEvaluationTable tbody tr:eq(0) td:eq(1)").text(employeeCommissionDetailOfBossDto.employeeEvaluateFive + "人");
	//4分人数
	jQuery("#customerEvaluationTable tbody tr:eq(1) td:eq(1)").text(employeeCommissionDetailOfBossDto.employeeEvaluateFour + "人");
	//3分人数
	jQuery("#customerEvaluationTable tbody tr:eq(2) td:eq(1)").text(employeeCommissionDetailOfBossDto.employeeEvaluateThree + "人");
	//2分人数
	jQuery("#customerEvaluationTable tbody tr:eq(3) td:eq(1)").text(employeeCommissionDetailOfBossDto.employeeEvaluateTwo + "人");
	//1分人数
	jQuery("#customerEvaluationTable tbody tr:eq(4) td:eq(1)").text(employeeCommissionDetailOfBossDto.employeeEvaluateOne + "人");
	//未评分人数
	jQuery("#customerEvaluationTable tbody tr:eq(5) td:eq(1)").text(employeeCommissionDetailOfBossDto.employeeEvaluateNull + "人");
	/*
	 * 工作态度
	 */
	//迟到
	jQuery("#workAttitudeTable tbody tr:eq(0) td:eq(1)").text(employeeCommissionDetailOfBossDto.rewardCountOfLate);
	//请假
	jQuery("#workAttitudeTable tbody tr:eq(1) td:eq(1)").text(employeeCommissionDetailOfBossDto.rewardCountOfHoliday);
	//旷工
	jQuery("#workAttitudeTable tbody tr:eq(2) td:eq(1)").text(employeeCommissionDetailOfBossDto.rewardCountOfAbsenteeism);
	//大过
	jQuery("#workAttitudeTable tbody tr:eq(3) td:eq(1)").text(employeeCommissionDetailOfBossDto.rewardCountOfSeriousMistake);
	//小过
	jQuery("#workAttitudeTable tbody tr:eq(4) td:eq(1)").text(employeeCommissionDetailOfBossDto.rewardCountOfSmallMistake);
	//投诉
	jQuery("#workAttitudeTable tbody tr:eq(5) td:eq(1)").text(employeeCommissionDetailOfBossDto.rewardCountOfComplaint);
	
}

//重画业绩分布图表
function redrawPerformanceChart(employeeCommissionDetailOfBossDto) {
	//先清空业绩分布的series属性内容
	cleanSeries(globalPerformanceChart);
	//再填充series
	addPerformanceChartSeries(globalPerformanceChart, "劳动业绩", 
			employeeCommissionDetailOfBossDto.cashCommissionOfProject, employeeCommissionDetailOfBossDto.cardCommissionOfProject);
	addPerformanceChartSeries(globalPerformanceChart, "商品销售", 
			employeeCommissionDetailOfBossDto.cashCommissionOfGoods, employeeCommissionDetailOfBossDto.cardCommissionOfGoods);
	addPerformanceChartSeries(globalPerformanceChart, "疗程销售", 
			employeeCommissionDetailOfBossDto.cashCommissionOfPackage, employeeCommissionDetailOfBossDto.cardCommissionOfPackage);
	addPerformanceChartSeries(globalPerformanceChart, "卡项销售", 
			employeeCommissionDetailOfBossDto.cashCommissionOfCard, employeeCommissionDetailOfBossDto.cardCommissionOfCard);
	//填充xAxis(不需要先清空)
	globalPerformanceChart.xAxis[0].setCategories(['现金业绩￥' + Number(employeeCommissionDetailOfBossDto.cashCommissionSum),
	                         		               '卡金业绩￥' + Number(employeeCommissionDetailOfBossDto.cardCommissionSum)]);
	globalPerformanceChart.redraw();
}

//重画技能分析之大小项图表 
function redrawSkillsOfBigAndSmallProjectChart(employeeCommissionDetailOfBossDto) {
	cleanSeries(globalSkillsOfBigAndSmallProjectChart);
	addSkillsChartSeries(globalSkillsOfBigAndSmallProjectChart, "大项", "小项", 
			employeeCommissionDetailOfBossDto.customerCountOfBigProject, employeeCommissionDetailOfBossDto.customerCountOfSmallProject);
	//change title text
	globalSkillsOfBigAndSmallProjectChart.setTitle({text: '<div style="text-align: center; margin-top: -20px;"><span style="font-size: 12px;color: #b8c2cc;">'
    	+ Number(employeeCommissionDetailOfBossDto.customerCountOfBigProject + employeeCommissionDetailOfBossDto.customerCountOfSmallProject) 
    	+ '</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">人</span></div>'});
	globalSkillsOfBigAndSmallProjectChart.redraw();
}

//重画技能分析之预约/非预约图表
function redrawSkillsOfIsAndNoAppointChart(employeeCommissionDetailOfBossDto) {
	cleanSeries(globalSkillsOfIsAndNoAppointChart);
	addSkillsChartSeries(globalSkillsOfIsAndNoAppointChart, "预约", "非预约", 
			employeeCommissionDetailOfBossDto.customerCountOfIsAppoint, employeeCommissionDetailOfBossDto.customerCountOfNotAppoint);
	globalSkillsOfIsAndNoAppointChart.setTitle({text: '<div style="text-align: center; margin-top: -20px;"><span style="font-size: 12px;color: #b8c2cc;">'
    	+ Number(employeeCommissionDetailOfBossDto.customerCountOfIsAppoint + employeeCommissionDetailOfBossDto.customerCountOfNotAppoint) 
    	+ '</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">人</span></div>'});
	globalSkillsOfIsAndNoAppointChart.redraw();
}

//重画技能分析之指定/非指定图表
function redrawSkillsOfIsAndNoAssignChart(employeeCommissionDetailOfBossDto) {
	cleanSeries(globalSkillsOfIsAndNoAssignChart);
	addSkillsChartSeries(globalSkillsOfIsAndNoAssignChart, "指定", "非指定", 
			employeeCommissionDetailOfBossDto.customerCountOfIsAssign, employeeCommissionDetailOfBossDto.customerCountOfNotAssign);
	globalSkillsOfIsAndNoAssignChart.setTitle({text: '<div style="text-align: center; margin-top: -20px;"><span style="font-size: 12px;color: #b8c2cc;">'
    	+ Number(employeeCommissionDetailOfBossDto.customerCountOfIsAssign + employeeCommissionDetailOfBossDto.customerCountOfNotAssign) 
    	+ '</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">人</span></div>'});
	globalSkillsOfIsAndNoAssignChart.redraw();
}

//重画提成来源图表
function redrawCommissionChart(employeeCommissionDetailOfBossDto) {
	cleanSeries(globalCommissionChart);
	globalCommissionChart.addSeries({
		type: 'pie',
        name: '占比',
        size: '120%',
        innerSize: '75%',
        data: [
               ['卡项提成', Number(employeeCommissionDetailOfBossDto.employeeCommissionOfCard)],
               ['项目提成', Number(employeeCommissionDetailOfBossDto.employeeCommissionOfProject)],
               ['商品提成', Number(employeeCommissionDetailOfBossDto.employeeCommissionOfGoods)],
               ['疗程提成', Number(employeeCommissionDetailOfBossDto.employeeCommissionOfPackage)]
        ]
	}, false);
	globalCommissionChart.setTitle({text: '<div style="text-align: center"><span style="font-size: 20px;color: #b8c2cc">'
    	+ Number(employeeCommissionDetailOfBossDto.employeeCommissionOfCard + employeeCommissionDetailOfBossDto.employeeCommissionOfProject + employeeCommissionDetailOfBossDto.employeeCommissionOfGoods + employeeCommissionDetailOfBossDto.employeeCommissionOfPackage).toFixed(2)
    	+ '</span><br><span style="font-size: 14px;color: #7f868d">总提成</span></div>'});
	globalCommissionChart.redraw();
}


//添加业绩分布图表的series
function addPerformanceChartSeries(chartObj, name, dataValueOne, dataValueTwo) {
	dataValueOne = dataValueOne.toFixed(2);
	dataValueTwo = dataValueTwo.toFixed(2);
	chartObj.addSeries({
        name: name,
        data: [Number(dataValueOne),Number(dataValueTwo)]
    }, false);
}

//添加技能分析图表series
function addSkillsChartSeries(chartObj, dataNameOne, dataNameTwo, dataValueOne, dataValueTwo) {
	chartObj.addSeries({
		type: 'pie',
        name: '占比',
        innerSize: '65%',
        showInLegend: true,
        data: [
            [dataNameOne, Number(dataValueOne)],
            [dataNameTwo, Number(dataValueTwo)]
        ]
	}, false);
	
}

//清空图表中的series
function cleanSeries(obj) {
    var series = obj.series;
    while(series.length > 0) {
    	series[0].remove(false);
    }
    obj.redraw();
}

