<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@include file="/base.jsp" %>
<html lang="zh-CN">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>会议申请</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=muiCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
    <style>
    .page-btn-wrap{
    
    padding-bottom:3rem;
    }
    
    .mb20 {
    padding-bottom:1.25rem;
    margin-bottom:0rem;
    }
    </style>
  </head>
<body class="gray-bg">
<div class="wrap">
	<div class="sqhuiyi">
	    <div class="form-group mt35">
	    
		    <form id="saveConference">
		        <ul class="register-ul">
		            <li class="mb20">
		                <label for="" class="left-label register-label">会议名称</label>
		                <div class="name register-input">
		                    <input type="text" name="conferenceName" class="normal-input"/>
		                </div>
		            </li>
		            <li class="mb20">
		                <label for="" class="left-label register-label">报名截止</label>
		                <div class="name register-input">
		                    <!-- <input type="text" readonly="readonly" id="registrationStartTime" value="" onclick="selectDateTime('registrationStartTime');" class="half-input fl" placeholder="报名开始时间" />- -->
		                    <input type="text" readonly="readonly" id="registrationEndTime" value="" onclick="selectDateTime('registrationEndTime');" class="normal-input" placeholder="报名结束时间"/>
		                </div>
		            </li>
		            <li class="mb20">
		                <label for="" class="left-label register-label">召开时间</label>
		                <div class="name register-input">
		                    <input type="text" readonly="readonly" id="holdTime" value="" onclick="selectDateTime('holdTime');" class="half-input fl" placeholder="会议开始时间" />-
		                    <input type="text" readonly="readonly" id="endTime" value="" onclick="selectDateTime('endTime');" class="half-input fr" placeholder="会议结束时间"/>
		                </div>
		            </li>
		            <li class="mb20">
		                <label for="" class="left-label register-label">地点</label>
		                <div class="name register-input">
		                    <input type="text" name="address" class="normal-input"/>
		                </div>
		            </li>
		            <li class="mb20">
		                <label for="" class="left-label register-label">人员设定</label>
		                <div class="name register-input">
		                    <input type="tel" name="peopleCount" class="normal-input"/>
		                </div>
		            </li>
		            <li class="mb20">
		                <label for="" class="left-label register-label">参会费用</label>
		                <div class="name register-input">
		                    <input type="tel" name="registrationAmount" class="normal-input"/>
		                </div>
		            </li>
		            <li class="mb20">
		                <label for="" class="left-label register-label">一级奖励</label>
		                <div class="name register-input">
		                    <input type="tel" name="mainAward" class="normal-input"/>
		                </div>
		            </li>
		            <li class="mb20">
		                <label for="" class="left-label register-label">二级奖励</label>
		                <div class="name register-input">
		                    <input type="tel" name="branchAward" class="normal-input"/>
		                </div>
		            </li>
		            <li class="mb20">
		                <label for="" class="left-label register-label">联系人</label>
		                <div class="name register-input">
		                    <input type="text" name="linkName" class="normal-input"/>
		                </div>
		            </li>
		            <li class="mb20">
		                <label for="" class="left-label register-label">电话</label>
		                <div class="name register-input">
		                    <input type="tel" name="linkPhone" class="normal-input"/>
		                </div>
		            </li>
		            <li>
		                <label for="" class="left-label register-label">会议说明</label>
		                <div class="wenben register-input">
		                    <textarea name="conferenceDesc" cols="30" rows="10"></textarea>
		                </div>
		            </li>
		        </ul>
		        <input type="hidden" name="registrationStartTime" />
		        <input type="hidden" name="registrationEndTime" />
		        <input type="hidden" name="holdTime" />
		        <input type="hidden" name="endTime" />
			</form>
	        <div class="page-btn-wrap">
	            <div class="page-btn"  onclick="saveConfenerce();">提交申请</div>
	        </div>
	    </div>
	</div>
</div>

<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=muiJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/validate.js"></script>
<script type="text/javascript">
function saveConfenerce(){
	var data = $("form").serialize();
	if(validate() == false){
		return;
	}
	$.ajax({
		type: "POST",
		url: baseUrl+"conference/save/conference",
        data: data,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function(e) {
        	if(e.code!=0) dialog(e.msg);
        	window.location.href = baseUrl+"conference/view/list";
        }
	});
}

function selectDateTime(property){
	datePicker.show(function(date) {
		timePicker.show(function(time) {
			var viewTime = date[1].value + "-" + date[2].value + " " + time[0].value + ":" + time[1].value;
			var saveTime = date[0].value + "-" + date[1].value + "-" + date[2].value + " " + time[0].value + ":" + time[1].value;
	        $("#" + property).val(viewTime);
	        $("[name='" + property + "']").val(saveTime);
	    });
    });
}

var datePicker, timePicker;
(function($, doc) {
    $.init();
    $.ready(function() {
    	var curDate = new Date();
    	var curYear = curDate.getFullYear();
    	var curMonth = curDate.getMonth();
    	var curDay = curDate.getDate();
		//年月日
        var dateArray = new Array();
        for (var i = 0; i < 10; i ++) {
            var monthArray = new Array();
            for (var j = 0; j < 12; j ++) {
            	//检查是否为过去的月份
            	if (i == 0 && j < curMonth) {
            		continue;
            	}
            	
                //月的最后一天
                var year = i + curYear;
                var month = j;
                var lastDay = new Date(year, month + 1, 0).getDate();//month 要加1,原本month是从0开始的,但是因为0是这个月的上个月,所以到了上个月.
                if(year == curYear && month == 1) {
                    console.log("lastday" + lastDay);
                }
                var dayArray = new Array();
                for(var k = 0; k < lastDay; k ++) {
                	//检查是否为过去的日子
                	if (curMonth == month && k < curDay) {
                		continue;
                	}
                	
                    var day = new Object();
                    var dayNumber = k + 1;
                    if (dayNumber < 10) {
                    	dayNumber = "0" + dayNumber;
                    }
                    day.value = dayNumber;
                    day.text = dayNumber + "日";
                    dayArray.push(day);
                }
                var month = new Object();
                var monthNumber = j + 1;
                if (monthNumber < 10) {
                	monthNumber = "0" + monthNumber;
                }
                month.value = monthNumber;
                month.text = monthNumber + "月";
                month.children = dayArray;
                monthArray.push(month);
            }
            var year = new Object();
            year.value = i + curYear;
            year.text = i + curYear + "年";
            year.children = monthArray;
            dateArray.push(year);
        };

		//小时分钟
        var timeArray = new Array();
        for (var i = 0; i < 24; i ++) {
            var hsChildrenArray = new Array();
            for (var j = 0; j < 60; j=j+5) {

                var childrenObject = new Object();
                var secTrue = j ;
                if(j < 10) {
                    var secTrue = j ;
                    childrenObject.value = "0" + secTrue;
                    childrenObject.text = "0" + secTrue + "分";
                }else {
                    childrenObject.value = secTrue;
                    childrenObject.text = secTrue + "分";
                }
                hsChildrenArray.push(childrenObject);
                
            }

            var object = new Object();
            if(i < 10) {
                object.value = "0" + i;
                object.text = "0" + i + "点";
            }else {
                object.value = i;
                object.text = i + "点";
            }
            object.children = hsChildrenArray;
            timeArray.push(object);
        };

        datePicker = new $.PopPicker({layer: 3});
        datePicker.setData(dateArray);

        timePicker = new $.PopPicker({layer: 2});
        timePicker.setData(timeArray);
    });
})(mui, document);
</script>
</body>
</html>