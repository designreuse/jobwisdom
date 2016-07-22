<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>我的提成</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=muiCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
    <style>
    .current-select-date{font-size:10px}
    .content{background:white}
    .mt2{margin-top:0!important}
    .current-select-date{width:7.5rem;position:relative;left:1.4rem}
    .tc-table-wrap{padding-top:0!important}
       .tc-table{
	        width: 100%;
	        margin: 2rem auto;
	        border: 1px solid #ccc;
	    }
	    .chart-desc{
	        background-color: #38e7e2;
	        height: 4rem;
	        line-height: 4rem;
	        padding: 0 1rem;
	    }
	    .tc-table th{
	        height: 5rem;
	        background-color: #38e7e2;
	    }
	    .tc-table td{
	        color: #666;
	        height: 5.625rem;
	        text-align: center;
	        border: 0;
	    }
	
	    .tc-table-wrap {
	        padding-top:7rem;
	    }
	    .tc-table tr:nth-child(odd) {
	        background-color: #f5f5f5;
	    }
	    .tc-table tr:nth-child(even) {
	        background-color: #fff;
	    }
	    .yg-data{
	        background-color: #fff;
	        position: relative;
	        height: 5rem;
	        line-height: 5rem;
	        overflow: hidden;
	        padding: 0 2rem;
	    }
	
	    .date {
	        position: absolute;
	        top:50%;
	        -webkit-transform: translatey(-50%);
	        -moz-transform: translatey(-50%);
	        -ms-transform: translatey(-50%);
	        -o-transform: translatey(-50%);
	        transform: translatey(-50%);
	        right: 2rem;
	
	    }
	    .select-day .swiper-slide {
	        height: 5.625rem;
	        background-image: -moz-linear-gradient(90deg, #e1e0e5 0%, #ffffff 100%);
	        background-image: -webkit-linear-gradient(90deg, #e1e0e5 0%, #ffffff 100%);
	        background-image: -ms-linear-gradient(90deg, #e1e0e5 0%, #ffffff 100%);
	    }
	    .select-day .swiper-slide .tab-word{
	        line-height: 5rem;
	    }
	    .return-btn{
	        background-color: #ffc309;
	        width: 100%;
	        box-shadow: none;
	        position: fixed;
	        top: 5.625rem;
	        max-width: 768px;
	    }
	    .chart-wrap {
	        overflow: hidden;
	        width: 100%;
	        margin-bottom: 1rem;
	    }
	
	    .tab .score-shop-li {
	        width: 50% !important;
	    }
	
	    .icon-zhankai {
	        vertical-align: middle;
	    }
	
	    .chart-wrap {
	        background-color: #fff;
	    }
    
    
	    .time_rank ul{height:3rem;width:48%}
		 .time_rank li{height:3rem;line-height:3rem;width:48%}
		 .time_rank{height:5rem;line-height:5rem;position:relative}
		 .time_rank .input_{position:absolute;bottom:1rem;right:0.5rem;width:22%;height:2rem}
		 .percentage_content{padding:1rem;font-size:14px}
		 .percentage_content_ul li{float:left;width:25%;text-align:center;height:3rem;line-height:3rem}
		 body{background:white}
		 .percentage_content_ul li span{color:white;display:inline-block;width:90%;text-align:center;height:3rem;line-height:3rem}
		 .percentage_content_data table{margin-bottom:2rem;width:100%;border:1px solid #c8c8c8;border-radius:8px}
		 .percentage_content_data table td{font-size:12px;text-align:center;height:3rem;vertical-align:middle;border-bottom:1px solid #c8c8c8}
		  .percentage_content_data table tr:nth-child(1){color:#e24420;}
		  .percentage_content_data table tr:last-child td{border-bottom:none}
		  .percentage_content_data td .cover{color:#61bf40}
		  .percentage_content_data td .shop{color:#63a9e4}
		  .percentage_content_data td .card{color:#eaa050}
		  .percentage_content_data td .work{color:#e95c40}
		  .percentage_content_data td span img{width:18px;vertical-align:middle;margin-right:5px}
		  .percentage_content_data td span{display:inline-block;margin-right:1rem}
	</style>
</head>
<body>
<div class="wrap">
<div class="content">
    <div class="yg-wodeticheng">
	    <%-- <div class="select-day tab">
	        <div class="swiper-wrapper">
	            <div class="swiper-slide active score-shop-li" onclick="changeTab(this, 1)">
	                <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class=""/>
	                <div class="tab-word">
	                    <div class="zhouyi ">按日查询</div>
	                </div>
	            </div>
	            <div class="swiper-slide score-shop-li" onclick="changeTab(this, 2)">
	                <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
	                <div class="tab-word">
	                    <div class="zhouyi ">按月查询</div>
	                </div>
	            </div>
	        </div>
	    </div> --%>
	    <div class="time_rank clearfix">
	      <ul class="clearfix"> 
			   <li class="active" ontouchstart="changeTab(this, 1)">按日查询</li>
			   <li ontouchstart="changeTab(this, 2)">按月查询</li>
          </ul>
          <div class="date fr" id="selectTime">
              <div class="current-select-date">
                  ${nowDay}
              </div>
          </div>
          
          <div class="date fr hide" id="selectMonth">
              <div class="current-select-date">
                  ${nowMonth}
              </div>
          </div>
          
    	</div>
	    
	     <div class="day tab-control">
		      <div class="tc-table-wrap">
		          <div class="chart-wrap mt2">
		             <div>
		                 <ul class="yg-tab">
		                     <li class="current" data-target="one-day">业绩</li>
		                     <li data-target="two-day">提成</li>
		                 </ul>
		             </div>
		              <div id="one-day" style="width: 50%;height: 300px; margin: 0 auto"></div>
		              <div id="two-day" class="hide" style="width: 50%;height: 300px; margin: 0 auto"></div>
		          </div>

			    <div class="percentage_content_data" id = "dayTbody">
				   
				</div>
		  </div>
        </div>
	    
	    <div class="month hide tab-control">
	        <div class="tc-table-wrap">
		        <div class="chart-wrap mt2">
		             <div>
		                 <ul class="yg-tab">
		                     <li class="current" data-target="one-month">业绩</li>
		                     <li data-target="two-month">提成</li>
		                 </ul>
		             </div>
		              <div id="one-month" style="width: 50%;height: 300px; margin: 0 auto"></div>
		              <div id="two-month" class="hide" style="width: 50%;height: 300px; margin: 0 auto"></div>
		          </div>
	            <!-- <table class="tc-table" cellspacing=0>
	                <thead >
	                <tr>
	                    <th>日期</th>
	                    <th>当日业绩</th>
	                    <th>当日提成</th>
	                </tr>
	                </thead>
	                <tbody id = "monthTbody">
		                
	                </tbody>
	            </table> -->
	            <div class="percentage_content_data" id = "monthTbody">
				    
				 </div>
	        </div>
	    </div>
	    
	    <div class="year hide tab-control">
	        <div class="tc-table-wrap">
	            <!-- <div class="chart">
	                <div class="chart-desc">年提成趋势图</div>
	                <div id="chartContainer"></div>
	            </div> -->
	            <div class="chart-wrap">
	                <div id="year-chart" style="width: 100%;height: 300px;"></div>
	            </div>
	            <table class="tc-table" cellspacing=0>
	                <thead >
	                <tr>
	                    <th>月份</th>
	                    <th>提成</th>
	                </tr>
	                </thead>
	                <tbody id = "yearTbody">
	                    <c:forEach items="${dateList}" var="dates">
	                        <tr onclick="triggerMonth('${dates.monthDate}')">
			                    <td>${dates.monthDate}</td>
			                    <td>${dates.sumCommissionMonth}</td>
			                </tr>
	                    </c:forEach>
	                </tbody>
	            </table>
	        </div>
	    </div>
	</div>
    
</div>    
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/fusioncharts.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/highcharts.src.js"> </script>
<script type="text/javascript" src="<%=muiJsPath%>"> </script>
<script type="text/javascript" src="<%=basePath%>js/base/big.js"> </script>
<script>

var dayMapStr = '${dayMapStr}';
var dayMap = eval("(" + dayMapStr + ")");

var monthMapStr = '${monthMapStr}';
var monthMap = eval("(" + monthMapStr + ")");

var nowMonth = '${nowMonth}';

var nowDay = '${nowDay}';

var orderTypeArray = new Array("", "劳动" , "商品", "套餐", "开卡", "充值", "升级");

var nowCommissionList = "";

var dateType = 1;

$(function () {
	$(".yg-tab li").on("touchstart",function() {
        $(this).addClass("current").siblings().removeClass("current");
        if ($(this).attr("data-target") == "two-day" || $(this).attr("data-target") == "one-day") {
        	$("#one-day,#two-day").addClass("hide");
        }
        else {
        	$("#one-month,#two-month").addClass("hide");
        }
        $("#"+$(this).attr("data-target")).removeClass("hide");

    });
	loadMonthTable(monthMap);
	loadDayTable(dayMap);
    
});

/*手机端导航滑动*/
var swiper = new Swiper('.select-day', {
    pagination: '.swiper-pagination',
    slidesPerView: 3,
    paginationClickable: true,
    spaceBetween: 0,
    freeMode: true
});

/*导航滑动选中状态*/
function changeTab(obj, type){
    $(obj).siblings().removeClass("active");
    $(obj).addClass("active");
    dateType = type;
    $(".tab-control").addClass("hide");
    $(".date").addClass("hide");
    if (type == 1) {
    	$(".day").removeClass("hide");
    	$("#selectTime").removeClass("hide");
    }
    else if (type == 2){
    	$(".month").removeClass("hide");
    	$("#selectMonth").removeClass("hide");
    }
} 

function selectCommission(dateTime) {
	$.ajax({
        url : baseUrl + "staff/action/selectCommissionDateType",
        type : "POST",
        data : "dateType=" + dateType + "&dateTime=" + dateTime,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            var data =  e.msg;
            if (dateType == 1) {
            	loadDayTable(data);
            }
            else {
            	loadMonthTable(data);
            }
        }
    });
}

function loadDayTable (dayMapLoad) {
	$("#dayTbody").empty();
	
	var orderList = dayMapLoad.orderList;
	
	for (var j = 0; j < orderList.length; j++) {
		var orderMap = orderList[j];
		var str = '<table>'+
					  '<tr>'+
				        '<td colspan="3">'+orderMap.orderCode+'</td>'+
					  '</tr>';
		var detailList = orderMap.detailList;			  
		for (var i = 0; i < detailList.length; i++) {
			var detailObj = detailList[i];
			str += '<tr>'+
				     '<td style="border-right:1px solid #c8c8c8;width:147px">'+detailObj.projectName;
		    if (detailObj.orderType == 1) {
		    	str += '<em class="work">（劳）</em></td>';
		    }
		    else if (detailObj.orderType == 2) {
		    	str += '<em class="shop">（商）</em></td>';
		    }
		    else if (detailObj.orderType == 3) {
		    	str += '<em class="shop">（套）</em></td>';
		    }
		    else {
		    	str += '<em class="card">（卡）</em></td>';
		    }
		    str +=	 '<td><span><img src="'+baseUrl+'images/mobile/newemployee/score.png">业绩: '+detailObj.commissionCalculate+'</span><span><img src="'+baseUrl+'images/mobile/newemployee/money.png">提成: '+detailObj.commissionAmount+'</span></td>'+
				   '</tr>';
		}
		str += '</table>';
		
		$("#dayTbody").append(str);
	}
	
	packageData(1, dayMapLoad);

}

function loadMonthTable (obj) {
	$("#monthTbody").empty();
	
	var commissionList = obj.commissionList;
	
	nowCommissionList = commissionList;
	
	for (var j = 0; j < commissionList.length; j++) {
		var commission = commissionList[j];
		$("#monthTbody").append('<table cellpadding="0" cellspacing="0">'+
								   '<tr>'+
						             '<td colspan="3">'+commission.chargeTime+'</td>'+
								   '</tr>'+
								   '<tr>'+
								     '<td style="border-right:1px solid #c8c8c8;"><span><img src="'+baseUrl+'images/mobile/newemployee/score.png">业绩: '+commission.sumCommissionCalculate+'</span></td>'+
								   	 '<td><span><img src="'+baseUrl+'images/mobile/newemployee/money.png">提成: '+commission.sumCommissionAmount+'</span></td>'+
								   '</tr>'+
								'</table>');
	}
	var dayMap = {"projectCalculate" : obj.monthProjectCalculate, "projectAmount" : obj.monthProjectAmount, "goodsCalculate" : obj.monthGoodsCalculate, "goodsAmount" : obj.monthGoodsAmount,
			      "comboCalculate" : obj.monthComboCalculate, "comboAmount" : obj.monthComboAmount, "chargeCalculate" : obj.monthChargeCalculate, "chargeAmount" : obj.monthChargeAmount};
	packageData(2, dayMap);
}

function packageData(type, dayMap) {
	var tatailCalculate = 0;
	var tatailAmount = 0;
	var calculateArray = new Array();
	var obj1 = {"name" : "劳动业绩", "y" : dayMap.projectCalculate};
	tatailCalculate = tatailCalculate + dayMap.projectCalculate;
	calculateArray.push(obj1);
	var obj2 = {"name" : "商品业绩", "y" : dayMap.goodsCalculate};
	tatailCalculate = tatailCalculate + dayMap.goodsCalculate;
	calculateArray.push(obj2);
	var obj3 = {"name" : "套餐业绩", "y" :dayMap.comboCalculate};
	tatailCalculate = tatailCalculate + dayMap.comboCalculate;
	calculateArray.push(obj3);
	var obj4 = {"name" : "卡项业绩", "y" : dayMap.chargeCalculate};
	tatailCalculate = tatailCalculate + dayMap.chargeCalculate;
	calculateArray.push(obj4);
	tatailCalculate= new Big(parseFloat(tatailCalculate));
	
	var amountArray = new Array();
	var obj5 = {"name" : "劳动提成", "y" : dayMap.projectAmount};
	tatailAmount = tatailAmount + dayMap.projectAmount;
	amountArray.push(obj5);
	var obj6 = {"name" : "商品提成", "y" : dayMap.goodsAmount};
	tatailAmount = tatailAmount + dayMap.goodsAmount;
	amountArray.push(obj6);
	var obj7 = {"name" : "套餐提成", "y" : dayMap.comboAmount};
	tatailAmount = tatailAmount + dayMap.comboAmount;
	amountArray.push(obj7);
	var obj8 = {"name" : "卡项提成", "y" : dayMap.chargeAmount};
	tatailAmount = tatailAmount + dayMap.chargeAmount;
	amountArray.push(obj8);
	var valueName = "";
	if (type == 1) {
		valueName = "day";
	}
	else {
		valueName = "month";
	}
	tatailAmount= new Big(parseFloat(tatailAmount));
	
	var calculateObj = {"objId" : "one-"+valueName, "name" : "业绩", "tatail" : tatailCalculate.toFixed(2), "arrayObj" : calculateArray};
	var amountObj = {"objId" : "two-"+valueName, "name" : "提成", "tatail" : tatailAmount.toFixed(2), "arrayObj" : amountArray};
	pieMap(calculateObj);
	pieMap(amountObj);
}

function loadYearTable () {
	
	var valueArray = new Array();
	var labelArray = new Array();
	var num = dateList.length - 1;
	for (var i = num; i < dateList.length; i--) {
		var obj = dateList[i];
		valueArray.push(obj.sumCommissionMonth);
		labelArray.push(obj.monthDate);
		if (i == 0) {
			break;
		}
	}
	
	$('#year-chart').highcharts({
        chart: {
            type: 'line',
            backgroundColor: 'rgba(0,0,0,0)'
        },
        colors: ['#8d60cb', '#20d0a8', '#dbb324'],
        credits: {
            enabled: false,
        },
        title: {
            text: ''
        },
        subtitle: {
            text: ''
        },
        legend: {
            backgroundColor: 'rgba(0,0,0,0)',
            itemStyle: {
                color: '#a2abb5',
            }
        },
        xAxis: {
            categories: labelArray,
            tickColor: '#1d2727',
            tickWidth: 2,
            lineColor: '#383943',
            labels: {
                style: {
                    color: '#a2abb5',
                    fontSize: 9,
                }
            },
            title: {
                text: '',
            }
        },
        yAxis: {
            title: {
                text: ''
            },
            tickPixelInterval: 30,
            gridLineColor: '#383943',
            labels: {
                style: {
                    color: '#a2abb5',

                }
            }
        },
        tooltip: {
            enabled: true,
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'元';
            }
        },
        plotOptions: {
            spline: {
                dataLabels: {
                    enabled: false,
                    shadow: false,
                    style: {
                        color: '#fff',
                    }
                },
                enableMouseTracking: false
            }
        },
        series: [{
            name: '月收益',
            data: valueArray,
            datalabel: {
                shadow: false,
            }
        }]
    });
}


function pieMap(dataObj) {
	var textNmae = '<div style="text-align: center; margin-top: -20px;"><span style="font-size: 12px;color: #b8c2cc;">'+dataObj.tatail+'</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">'+dataObj.name+'</span></div>';
	var dataArrar = dataObj.arrayObj;
	$('#' + dataObj.objId).highcharts({
        chart: {
            backgroundColor: 'transparent',
            plotBackgroundColor: null,
            plotBorderWidth: 0,
            plotShadow: false,
        },
        legend: {
            itemStyle: {
                color: '#a2abb5',
            }
        },
        credits: {
            enabled: false,
        },
        colors: ['#ff7e7d', '#7cb5ec', '#97ec76', '#f4a255'],
        title: {
            align: 'center',
            useHTML: true,
            text: textNmae,
            verticalAlign: 'middle',
            y: -10,
            style: {
                fontWeight: 'bold',
                color: '#ffffff'
            }
        },
        tooltip: {
            pointFormat: '{point.percentage:.1f}元</b>'
        },
        plotOptions: {
            pie: {
                dataLabels: {
                    enabled: false,
                    distance: 0,
                    style: {
                        fontWeight: 'bold',
                        color: '#b8c2cc',
                        textShadow: '0px 1px 2px black'
                    }
                },
                startAngle: 0,
                endAngle: 360,
                size: '100%',
                center: ['50%', '50%'],
                borderWidth: 0,
            }
        },
        series: [{
            type: 'pie',
            name: '营业收入',
            innerSize: '65%',
            showInLegend: true,
            data: dataArrar
        }]
    });
}

function changeTwoDecimal (floatvar){  
    var f_x = floatvar;  
    if (isNaN(f_x)){  
        return '0.00';  
    }
    var f_x = Math.round(f_x*100)/100;  
    var s_x = f_x.toString();  
    var pos_decimal = s_x.indexOf('.');  
    if (pos_decimal < 0){  
        pos_decimal = s_x.length;  
        s_x += '.';  
    }  
    while (s_x.length <= pos_decimal + 2){  
        s_x += '0';  
    }  
    return s_x;  
}


(function($, doc) {
    $.init();
    $.ready(function() {
        //普通示例

        //        年月
        var yearMonthArray = new Array();
        for (var i = 0; i < 10; i ++) {
            var monthArray = new Array();
            for (var j = 0; j < 12; j ++) {
            	var a = j + 1;
                var month = new Object();
                if (a < 10) {
                	a = "0" + a;
                }
                month.value = a;
                month.text = a + "月";
                monthArray.push(month);
            }
            var year = new Object();
            year.value = i + 2016;
            year.text = i + 2016 + "年";
            year.children = monthArray;
            yearMonthArray.push(year);
        };

        //年月日
        var yearArray = new Array();
        for (var i = 0; i < 10; i ++) {
            var monthArray = new Array();
            for (var j = 0; j < 12; j ++) {
                //月的最后一天
                var year = i + 2016;
                var month = j;
                var lastDay = new Date(year, month + 1, 0).getDate();//month 要加1,原本month是从0开始的,但是因为0是这个月的上个月,所以到了上个月.
                
                var dayArray = new Array();
                for(var k = 0; k < lastDay; k ++) {
                	var a = k + 1;
                    var day = new Object();
                    if (a < 10) {
                    	a = "0" + a;
                    }
                    day.value = a;
                    day.text = a + "日";
                    dayArray.push(day);
                }
                var b = j + 1
                if (b < 10) {
                	b = "0" + b;
                }
                var month = new Object();
                month.children = dayArray;
                month.value = b;
                month.text = b + "月";
                monthArray.push(month);
            }
            var year = new Object();
            year.value = i + 2016;
            year.text = i + 2016 + "年";
            year.children = monthArray;
            yearArray.push(year);
        };
        console.log(yearArray);

        //        小时分钟
        var hoursec = new Array();
        for (var i = 0; i < 24; i ++) {
            var hsChildrenArray = new Array();
            for (var j = 0; j < 60; j ++) {

                var childrenObject = new Object();
                var secTrue = j ;
                if(j < 10) {
                    var secTrue = j ;
                    childrenObject.value = "0" + secTrue;
                    childrenObject.text = "0" + secTrue;
                }else {
                    childrenObject.value = secTrue ;
                    childrenObject.text = secTrue;
                }

                hsChildrenArray.push(childrenObject);
            }

            var object = new Object();
            if(i < 10) {
                object.value = "0" + i + ":" ;
                object.text = "0" + i + ":";
            }else {
                object.value = i +":" ;
                object.text = i + ":";
            }
            object.children = hsChildrenArray;
            hoursec.push(object);
        };

        var yearMonthDayPicker = new $.PopPicker({layer: 3});
        yearMonthDayPicker.setData(yearArray);
        var selectDatePickerButton = doc.getElementById('selectTime');
        
        var yearMonthPicker = new $.PopPicker({layer: 3});
        yearMonthPicker.setData(yearMonthArray);
        var selectMonthPickerButton = doc.getElementById('selectMonth');
        
        var currentSelectDate = doc.getElementsByClassName('current-select-date')[0];
        var currentSelectDate1 = doc.getElementsByClassName('current-select-date')[1];
        
        selectDatePickerButton.addEventListener('tap', function(event) {
            yearMonthDayPicker.show(function(items) {
            	var dateValue = items[0].value + "-" + items[1].value + "-" + items[2].value;
            	currentSelectDate.innerText = dateValue;
            	selectCommission(dateValue);
            });
        }, false);
        
        selectMonthPickerButton.addEventListener('tap', function(event) {
        	yearMonthPicker.show(function(items) {
            	var dateValue = items[0].value + "-" + items[1].value;
            	currentSelectDate1.innerText = dateValue;
            	selectCommission(dateValue);
            });
        }, false);


    });
})(mui, document);
</script>
</body>
</html>