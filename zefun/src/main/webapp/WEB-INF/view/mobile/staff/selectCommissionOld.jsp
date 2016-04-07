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
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
    <style>
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
	        font-weight: 700;
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
	    .tc-table tr:nth-child(odd) {
	        background-color: #f5f5f5;
	    }
	    .tc-table tr:nth-child(even) {
	        background-color: #fff;
	    }
	    .tc-table-wrap{
	        padding-top: 7rem;
			padding-bottom: 1.5rem;
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
	        bottom: 0;
			max-width: 768px;
	    }
	    .chart-wrap {
	        overflow: hidden;
	        width: 100%;
	        margin-bottom: 1rem;
	    }
	</style>
</head>
<body>
<div class="wrap">
<div class="content">
    <div class="yg-wodeticheng">
	    <div class="select-day tab">
	        <div class="swiper-wrapper">
	            <div class="swiper-slide active score-shop-li" onclick="changeTab(this, 1)">
	                <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class=""/>
	                <div class="tab-word">
	                    <div class="zhouyi" name = "dayTop">${nowDay}</div>
	                </div>
	            </div>
	            <div class="swiper-slide score-shop-li" onclick="changeTab(this, 2)">
	                <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
	                <div class="tab-word">
	                    <div class="zhouyi" name = "monthTop">${nowMonth}</div>
	                </div>
	            </div>
	            <div class="swiper-slide score-shop-li" onclick="changeTab(this, 3)">
	                <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
	                <div class="tab-word">
	                    <div class="zhouyi">12个月记录</div>
	                </div>
	            </div>
	        </div>
	    </div>
	
	    <div class="day tab-control">
	      <div class="tc-table-wrap">
		      <div class="btn return-btn" onclick="triggerDay('${nowDay}')">返回当前日</div>
		      <div class="chart-wrap">
		              <div id="one-day" style="width: 50%;height: 300px;float: left;"></div>
		              <div id="two-day" style="width: 50%;height: 300px;float: left;"></div>
		      </div>
		      <table class="tc-table" cellspacing=0>
		        <thead >
			        <tr>
			            <th>单号</th>
			            <th>类别</th>
			            <th>业绩</th>
			            <th>提成</th>
			        </tr>
		        </thead>
		        <tbody id = "dayTbody">
			        
		        </tbody>
		      </table>
		
		      <!-- <table class="tc-table" cellspacing=0>
		          <thead >
		          <tr>
		              <th></th>
		              <th>业绩</th>
		              <th>提成</th>
		          </tr>
		          </thead>
		          <tbody id = "daySumTbody">

		          </tbody>
		      </table> -->
	      </div>
	    </div>
	    
	    <div class="month hide tab-control">
	        <div class="tc-table-wrap">
	            <div class="btn return-btn" onclick="triggerMonth('${nowMonth}')">返回当前月</div>
	            <div class="chart-wrap">
		              <div id="one-month" style="width: 50%;height: 300px;float: left;"></div>
		              <div id="two-month" style="width: 50%;height: 300px;float: left;"></div>
		        </div>
	            <table class="tc-table" cellspacing=0>
	                <thead >
	                <tr>
	                    <th>日期</th>
	                    <th>当日业绩</th>
	                    <th>当日提成</th>
	                </tr>
	                </thead>
	                <tbody id = "monthTbody">
		                
	                </tbody>
	            </table>
	
	            <!-- <table class="tc-table" cellspacing=0>
	                <thead >
	                <tr>
	                    <th>业绩分类</th>
	                    <th>业绩</th>
	                    <th>提成</th>
	                </tr>
	                </thead>
	                <tbody id = "monthSumTbody">

	                </tbody>
	            </table> -->
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
<script>

var dateListStr = '${dateListStr}';
var dateList = eval("(" + dateListStr + ")");

var nowMonth = '${nowMonth}';

var nowDay = '${nowDay}';

var orderTypeArray = new Array("", "劳动" , "商品", "套餐", "开卡", "充值", "升级");

var nowCommissionList = "";

$(function () {
	loadMonthTable(nowMonth);
	loadDayTable(nowDay);
    
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
    $(".swiper-slide").removeClass("active");
    $(".swiper-slide img").addClass("hide");
    $(obj).addClass("active");
    $(obj).find("img").removeClass("hide");
    
    $(".tab-control").addClass("hide");
    if (type == 1) {
    	$(".day").removeClass("hide");
    }
    else if (type == 2){
    	$(".month").removeClass("hide");
    }
    else {
    	$(".year").removeClass("hide");
    	loadYearTable();
    }
}

function triggerMonth (monthTime) {
	$(".swiper-slide").removeClass("active");
    $(".swiper-slide img").addClass("hide");
    
    $("div[name='monthTop']").parents(".swiper-slide").addClass("active");
    $("div[name='monthTop']").parents(".swiper-slide").find("img").removeClass("hide");
    
    $("div[name='monthTop']").text(monthTime);
    
    $(".tab-control").addClass("hide");
    
    $(".month").removeClass("hide");
    
    loadMonthTable(monthTime);
}

function triggerDay (dayTime) {
	$(".swiper-slide").removeClass("active");
    $(".swiper-slide img").addClass("hide");
    
    $("div[name='dayTop']").parents(".swiper-slide").addClass("active");
    $("div[name='dayTop']").parents(".swiper-slide").find("img").removeClass("hide");
    
    $("div[name='dayTop']").text(dayTime);
    
    $(".tab-control").addClass("hide");
    
    $(".day").removeClass("hide");
    
    loadDayTable(dayTime);
}

function loadDayTable (dayTime) {
	$("#daySumTbody").empty();
	$("#dayTbody").empty();
	
	for (var i = 0; i < nowCommissionList.length; i++) {
		var commission = nowCommissionList[i];
		if (dayTime == commission.chargeTime) {
			var dayMap = commission.dayMap;
			
			var dtoList = dayMap.dtoList;
			
			for (var j = 0; j < dtoList.length; j++) {
				var employeeCommissionDto = dtoList[j];
				$("#dayTbody").append("<tr>"+
							            "<td>"+employeeCommissionDto.orderCode+"</td>"+
							            "<td>"+orderTypeArray[employeeCommissionDto.orderType]+"</td>"+
							            "<td>"+employeeCommissionDto.commissionCalculate+"</td>"+
							            "<td>"+employeeCommissionDto.commissionAmount+"</td>"+
							          "</tr>");
			}
			
			
			packageData(1, dayMap);
			return;
			/* $("#daySumTbody").append("<tr>"+
					                    "<td>劳动汇总</td>"+
						                   "<td>"+dayMap.projectCalculate+"</td>"+
						                   "<td>"+dayMap.projectAmount+"</td>"+
						               "</tr>"+
						               "<tr>"+
					                    "<td>商品汇总</td>"+
						                   "<td>"+dayMap.goodsCalculate+"</td>"+
						                   "<td>"+dayMap.goodsAmount+"</td>"+
						               "</tr>"+
						               "<tr>"+
						                    "<td>套餐汇总</td>"+
							                   "<td>"+dayMap.comboCalculate+"</td>"+
							                   "<td>"+dayMap.comboAmount+"</td>"+
							               "</tr>"+
						               "<tr>"+
						               "<td>开卡充值汇总</td>"+
							                   "<td>"+dayMap.chargeCalculate+"</td>"+
							                   "<td>"+dayMap.chargeAmount+"</td>"+
							           "</tr>"); */
		}
	}
	var dayMap = {"projectCalculate" : 0, "projectAmount" : 0, "goodsCalculate" : 0, "goodsAmount" : 0,
		      "comboCalculate" : 0, "comboAmount" : 0, "chargeCalculate" : 0, "chargeAmount" : 0};
	packageData(1, dayMap);
}

function loadMonthTable (monthTime) {
	$("#monthTbody").empty();
	$("#monthSumTbody").empty();
	for (var i = 0; i < dateList.length; i++) {
		var obj = dateList[i];
		if (obj.monthDate == monthTime) {
			var commissionList = obj.commissionList;
			
			nowCommissionList = commissionList;
			
			for (var j = 0; j < commissionList.length; j++) {
				var commission = commissionList[j];
				$("#monthTbody").append("<tr onclick = \"triggerDay('"+commission.chargeTime+"')\">"+
						                    "<td>"+commission.chargeTime+"</td>"+
						                    "<td>"+commission.sumCommissionCalculate+"</td>"+
						                    "<td>"+commission.sumCommissionAmount+"</td>"+
						                "</tr>");
			}
			var dayMap = {"projectCalculate" : obj.monthProjectCalculate, "projectAmount" : obj.monthProjectAmount, "goodsCalculate" : obj.monthGoodsCalculate, "goodsAmount" : obj.monthGoodsAmount,
					      "comboCalculate" : obj.monthComboCalculate, "comboAmount" : obj.monthComboAmount, "chargeCalculate" : obj.monthChargeCalculate, "chargeAmount" : obj.monthChargeAmount};
			packageData(2, dayMap);
			/* $("#monthSumTbody").append("<tr>"+
					                       "<td>劳动汇总</td>"+
						                   "<td>"+obj.monthProjectCalculate+"</td>"+
						                   "<td>"+obj.monthProjectAmount+"</td>"+
						               "</tr>"+
						               "<tr>"+
					                       "<td>商品汇总</td>"+
						                   "<td>"+obj.monthGoodsCalculate+"</td>"+
						                   "<td>"+obj.monthGoodsAmount+"</td>"+
						               "</tr>"+
					                   "<tr>"+
					                       "<td>套餐汇总</td>"+
						                   "<td>"+obj.monthComboCalculate+"</td>"+
						                   "<td>"+obj.monthComboAmount+"</td>"+
						               "</tr>"+
					                   "<tr>"+
					                       "<td>开卡充值汇总</td>"+
						                   "<td>"+obj.monthChargeCalculate+"</td>"+
						                   "<td>"+obj.monthChargeAmount+"</td>"+
						               "</tr>"); */
			
			return;
		}
	}
	var dayMap = {"projectCalculate" : 0, "projectAmount" : 0, "goodsCalculate" : 0, "goodsAmount" : 0,
		      "comboCalculate" : 0, "comboAmount" : 0, "chargeCalculate" : 0, "chargeAmount" : 0};
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
	var calculateObj = {"objId" : "one-"+valueName, "name" : "业绩", "tatail" : tatailCalculate, "arrayObj" : calculateArray};
	var amountObj = {"objId" : "two-"+valueName, "name" : "提成", "tatail" : tatailAmount, "arrayObj" : amountArray};
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
	
	/* FusionCharts.ready(function () {
		
	    var visitChart = new FusionCharts({
	        type: 'msline',
	        renderAt: 'chartContainer',
	        width: '100%',
	        height: '350',
	        dataFormat: 'json',
	        dataSource: {
	            "chart": {
	                /*"caption": "Number of visitors last week",
	                "subCaption": "Bakersfield Central vs Los Angeles Topanga",*/
	                /* "captionFontSize": "14",
	                "subcaptionFontSize": "14",
	                "subcaptionFontBold": "0",
	                "paletteColors": "#fabc53",
	                "bgcolor": "#ffffff",
	                "showBorder": "0",
	                "showShadow": "0",
	                "showCanvasBorder": "0",
	                "usePlotGradientColor": "0",
	                "legendBorderAlpha": "0",
	                "legendShadow": "0",
	                "showAxisLines": "1",
	                "showAlternateHGridColor": "0",
	                "divlineThickness": "1",
	                "divLineIsDashed": "1",
	                "divLineDashLen": "1",
	                "divLineGapLen": "1",
	                "xAxisName": "(月)",
	                "yAxisName": "(千元)",
	                "rotateYAxisName": "90",
	                "showValues": "1"
	            }, 
	            "categories": [
	                {
	                    "category":labelArray 
	                }
	            ],
	            "dataset": [
	                {
	                    "seriesname": "月收益",
	                    "data": valueArray
	                }
	            ]
	        }
	    }).render();
	}); */
	
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
</script>
</body>
</html>