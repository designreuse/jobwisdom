<!DOCTYPE html>
<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta name="x5-orientation" content="portrait">
    <meta content="telephone=no" name="format-detection" />
    <meta name="msapplication-tap-highlight" content="no">
    <title>营业分析</title>
    <link rel="stylesheet" href="<%=muiCssPath%>"/>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=bossNewer%>"/>
    <link rel="stylesheet" href="<%=chartComponent%>">
    <link rel="stylesheet" href="<%=businessAnalysis%>">
    <link rel="stylesheet" href="<%=chartComponent%>">
    <style type="text/css">
    .expenditure-item {
        height: 7rem;
    }
    </style>
</head>
<body>
<div class="loading">
<img src="<%=basePath%>images/mobile/boss-newer/tb-loading.gif" alt=""/>
</div>
<div class="content hide">
	<div class="top-fix">
	    <div class="date" id="selectDate">
	        <div class="date-btn">
	            <span class="iconfont icon-zhankai"></span>
	        </div>
	        <div class="current-select-date" id="current-select-date">
	            2016-02-01
	        </div>
	    </div>
	    <c:set value="${expenditure.fixedCost+expenditure.writerCost+expenditure.briefOperation+expenditure.staffCosts+expenditure.administration+expenditure.directCost }" var="expenditureAmount"/>
	    <div class="profit "><span>月利润</span> <span class="profit-num fred">894,646</span></div>
	</div>

<div class="business-analysis chart-bg">

    <div id="business-analysis">
        <!--营业分析-->
        <div class="part-chart mt-3">
            <div class="business-analysis-chart">
                <div class="left-chart l" style="width: 50%; height: 300px;"></div>
                <div class="right-chart l" style="width: 50%; height: 300px;"></div>
            </div>
        </div>

        <!--业绩分布-->
        <div class="part-chart pt5">
            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
                	支出分析
            </div>
            <div class="sum-position">
                <span class="chart-sum">总支出:
                    <span class="num">${expenditureAmount }</span>
                </span>
            </div>
			<c:choose>
			<c:when test="${expenditureStatus == 0 }">
			<div class="expenditure-list mt2">
                <div class="expenditure-item active" onclick="serchProjectAmount(this,'固定支出',${expenditure.fixedCost })">
                    <div class="name" id="fixedCost">固定支出(0%)</div>
                    <div class="num">${expenditure.fixedCost }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'水电燃料费',${expenditure.writerCost })">
                    <div class="name" id="writerCost">水电燃费(0%)</div>
                    <div class="num">${expenditure.writerCost }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'间接运营成本',${expenditure.briefOperation })">
                    <div class="name" id="briefOperation">间接运营(0%)</div>
                    <div class="num">${expenditure.briefOperation }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'人员费用',${expenditure.staffCosts })">
                    <div class="name" id="staffCosts">人员费用(0%)</div>
                    <div class="num">${expenditure.staffCosts }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'行政管理费用',${expenditure.administration })">
                    <div class="name" id="administration">行政管理(0%)</div>
                    <div class="num">${expenditure.administration }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'直接运营支出',${expenditure.directCost })">
                    <div class="name" id="directCost">直接运营(0%)</div>
                    <div class="num">${expenditure.directCost }</div>
                </div>
            </div>
			</c:when>
			<c:when test="${expenditureStatus != 0 }">
			<div class="expenditure-list mt2">
                <div class="expenditure-item active" onclick="serchProjectAmount(this,'固定支出',${expenditure.fixedCost })">
                    <div class="name" id="fixedCost">固定支出(${expenditure.fixedCost/expenditureAmount*100 }%)</div>
                    <div class="num">${expenditure.fixedCost }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'水电燃料费',${expenditure.writerCost })">
                    <div class="name" id="writerCost">水电燃费(${expenditure.writerCost/expenditureAmount*100 }%)</div>
                    <div class="num">${expenditure.writerCost }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'间接运营成本',${expenditure.briefOperation })">
                    <div class="name" id="briefOperation">间接运营(${expenditure.briefOperation/expenditureAmount*100 }%)</div>
                    <div class="num">${expenditure.briefOperation }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'人员费用',${expenditure.staffCosts })">
                    <div class="name" id="staffCosts">人员费用(${expenditure.staffCosts/expenditureAmount*100 }%)</div>
                    <div class="num">${expenditure.staffCosts }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'行政管理费用',${expenditure.administration })">
                    <div class="name" id="administration">行政管理(${expenditure.administration/expenditureAmount*100 }%)</div>
                    <div class="num">${expenditure.administration }</div>
                </div>
                <div class="expenditure-item" onclick="serchProjectAmount(this,'直接运营支出',${expenditure.directCost })">
                    <div class="name" id="directCost">直接运营(${expenditure.directCost/expenditureAmount*100 }%)</div>
                    <div class="num">${expenditure.directCost }</div>
                </div>
            </div>
			</c:when>
			</c:choose>
            

            <div class="table-wrap mt2" style="text-align: center;font-size: 1rem;font-weight: 700;font-style: normal;">
	            <span class="chart-sum">
            		<span>固定支出明细</span>
	            </span>
	            <br/>
                <table>
                    <thead>
                    <tr>
                        <th>项目</th>
                        <th>占比</th>
                        <th>金额</th>
                        <th>上月金额</th>
                    </tr>
                    </thead>
                    <tbody id="table">
                    <c:forEach items="${nowPays }" varStatus="status">
	                    <tr>
	                        <td>${nowPays[status.index].businessProject }</td>
	                        <c:choose>
							<c:when test="${nowPaysAmount == 0 }"><td>0%</td></c:when>
							<c:when test="${nowPaysAmount != 0 }"><td>${nowPays[status.index].flowAmount/nowPaysAmount*100 }%</td></c:when>
							</c:choose>
	                        <td>${nowPays[status.index].flowAmount }</td>
	                        <td>${lastPays[status.index].flowAmount }</td>
	                    </tr>
                    </c:forEach>
                    <tr class="huizong-tr">
                        <td>汇总</td>
                        <c:choose>
						<c:when test="${expenditureStatus == 0 }"><td>0%</td></c:when>
						<c:when test="${expenditureStatus != 0 }"><td>${expenditure.fixedCost/expenditureAmount*100 }%</td></c:when>
						</c:choose>
                        <td>${expenditure.fixedCost }</td>
                        <td>${lastPaysAmount }</td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <!--chart-wrap-->
        </div>
        <!--part-chart-->

        <!--技能分析-->
        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
               	 月盈利趋势图
            </div>
            <%-- <table class="mt-2">
                <thead>
                <tr>
                    <th>指定比</th>
                    <th>客单价</th>
                    <th>客成本</th>
                    <th>客盈利</th>
                </tr>
                </thead>
                <tbody>
                <tr class="trend-num">
                    <td>${assignRatio }%</td>
                    <td>${guestUnitPrice }</td>
                    <td>${customerCost }</td>
                    <td>${customerProfitability }</td>
                </tr>
                </tbody>
            </table> --%>
            
            <div class="chart-wrap mt2 c1">
                <div class="trend-chart2"  style="width:100%;height:320px;"></div>
            </div>
            
            <!-- <div class="chart-wrap mt2 c2">
                <div class="trend-chart"  style="width:100%;height:320px;"></div>
            </div> -->
            <!--chart-wrap-->
        </div>
        <!--part-chart-->
    </div>
</div>
<!--tab end-->
</div>
</div>

<div class="footer">
    <ul>
        <li>
            <a href="<%=basePath %>boss/view/bossObjective/${session_key_store_id}/2">
                <span class="iconfont icon-yewubiaobiao"></span>
                <span>业绩报表</span>
            </a>
        </li>
        <li class="active">
            <a href="<%=basePath %>boss/view/business/${session_key_store_id}/2">
                <span class="iconfont icon-yingyefenxi"></span>
                <span>营业分析</span>
            </a>
        </li>
        <li>
            <a href="<%=basePath %>boss/view/coutomer/${session_key_store_id}/2">
                <span class="iconfont">&#xe845;</span>
                <span class="word">客情分析</span>
            </a>
        </li>
        <li>
            <a href="<%=basePath %>boss/view/employeeCommissionHome/${session_key_store_id}/2">
                <span class="iconfont icon-yuangong"></span>
                <span>员工表现</span>
            </a>
        </li>
    </ul>
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=muiJsPath%>"></script>
<script type="text/javascript" src="<%=employee%>"></script>
<script type="text/javascript" src="<%=highcharts%>"> </script>
<script type="text/javascript">
	var storeId = '${storeId }';
	var include = eval("(" + '<%=request.getAttribute("include")%>' + ")");
	var consumption = eval("(" + '<%=request.getAttribute("consumption")%>' + ")");
	var date = '${date}';
	var kdjs = eval("(" + '<%=request.getAttribute("kdjs")%>' + ")");
	var kcbs = eval("(" + '<%=request.getAttribute("kcbs")%>' + ")");
	var kyls = eval("(" + '<%=request.getAttribute("kyls")%>' + ")");

	var yyls = eval("(" + '<%=request.getAttribute("yyls")%>' + ")");
	var ycbs = eval("(" + '<%=request.getAttribute("ycbs")%>' + ")");
	var ylrs = eval("(" + '<%=request.getAttribute("ylrs")%>' + ")");
	
	var includeAmount = (parseInt(include[0][1]+include[1][1]+include[2][1]));
	var expenditureAmount = '${expenditure.fixedCost+expenditure.writerCost+expenditure.briefOperation+expenditure.staffCosts+expenditure.administration+expenditure.directCost }';
	//统计到底是盈利还是亏了
	function initStatus(){
		if ((includeAmount-expenditureAmount)>0){
			$(".profit-num").addClass("fgreen");
			$(".profit-num").removeClass("fred");
			$(".profit-num").text("+"+Math.round(includeAmount-expenditureAmount));
		}
		else {
			$(".profit-num").addClass("fred");
			$(".profit-num").removeClass("fgreen");
			$(".profit-num").text("-"+Math.round(expenditureAmount-includeAmount));
		}
	}
	if ((includeAmount-expenditureAmount)>0){
		$(".profit-num").addClass("fgreen");
		$(".profit-num").removeClass("fred");
		$(".profit-num").text("+"+(includeAmount-expenditureAmount));
	}
	else {
		$(".profit-num").addClass("fred");
		$(".profit-num").removeClass("fgreen");
		$(".profit-num").text("-"+(expenditureAmount-includeAmount));
	}
	function initLeftChart(){
		$('.left-chart').highcharts({
            chart: {
                backgroundColor: '#0a1718',
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false,
            },
            legend: {
                itemStyle: {
                    color: '#a2abb5',
                },
                itemMarginBottom:5,
	            labelFormatter: function () {
	            	return this.name.substring(0, 4);
	            }
            },
            credits: {
                enabled: false,
            },
            colors: ['#f7cc61', '#20affc', '#8bf761'],
            title: {
                align: 'center',
                useHTML: true,
                text: '<div style="text-align: center;margin-top: -30px;"><span style="font-size: 20px;color: #b8c2cc">'+ (parseInt(include[0][1]+include[1][1]+include[2][1])) + '</span><br><span style="font-size: 14px;color: #7f868d">营业收入</span></div>',
                verticalAlign: 'middle',
                y: -10,
                style: {
                    fontWeight: 'bold',
                    color: '#ffffff'
                }
            },
            tooltip: {
            	useHTML: true,
                pointFormat: '{series.name}: <span style="font-weight:400">{point.percentage:.1f}%</span>'
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
                    size: '120%',
                    center: ['50%', '50%'],
                    borderWidth: 0,
                }
            },
            series: [{
                type: 'pie',
                name: '收入占比',
                innerSize: '65%',
                showInLegend: true,
                data: include/* [
                    ['疗程抵扣  1000',  1000],
                    ['现金消费  1000',  1000],
                    ['划卡消费  1000',  100],
                ] */
            }]
        });
	}
	
	function initRightChart(){
		$('.right-chart').highcharts({
            chart: {
                backgroundColor: '#0a1718',
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false,
            },
            legend: {
                itemStyle: {
                    color: '#a2abb5',
                },
                itemMarginBottom:5,
                labelFormatter: function () {
	            	if(this.name.indexOf("卡项/疗程销售")!=-1){
	            		return this.name.substring(0, 7);
	            	}
	            	else {
	            		return this.name.substring(0, 4);
	            	}
	            }
            },
            credits: {
                enabled: false,
            },
            colors: ['#2fc8bd', '#e8781e', '#e89e55'],
            title: {
                align: 'center',
                useHTML: true,
                text: '<div style="text-align: center;margin-top: -30px;"><span style="font-size: 20px;color: #b8c2cc">'+ (parseInt(consumption[0][1]+consumption[1][1]+consumption[2][1])) + '</span><br><span style="font-size: 14px;color: #7f868d">现金营收</span></div>',
                verticalAlign: 'middle',
                y: -10,
                style: {
                    fontWeight: 'bold',
                    color: '#ffffff'
                }
            },
            tooltip: {
            	useHTML: true,
                pointFormat: '{series.name}: <span style="font-weight:400">{point.percentage:.1f}%</span>'
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
                    size: '120%',
                    center: ['50%', '50%'],
                    borderWidth: 0,
                }
            },
            series: [{
                type: 'pie',
                name: '营收占比',
                innerSize: '65%',
                showInLegend: true,
                data: consumption/* [
                    ['项目消费 1000',    1000],
                    ['卡项(疗程)  1000',   1000],
                    ['商品销售  1000',   1000]
                ] */
            }]
        });
	}
	
	function initTrend(){
		$('.trend-chart').highcharts({
            chart: {
                type: 'spline',
                backgroundColor: '#0a1718'
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
                },
                itemMarginBottom:5,
            },
            xAxis: {
                categories: ['前1个月', '前2个月', '前3个月', '前4个月', '前5个月', '前6个月', '前7个月', '前8个月', '前9个月', '前10个月', '前11个月', '前12个月'],
                tickColor: '#1d2727',
                tickWidth: 2,
                lineColor: '#383943',
                allowDecimals: false,
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
                enabled: false,
                formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
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
                name: '客单价',
                data: kdjs,
                datalabel: {
                    shadow: false,
                }
            }, {
                name: '客成本',
                data: kcbs
            }, {
                name: '客盈利',
                data: kyls
            }]
        });
	}
	
	function initTrend2(){
		$('.trend-chart2').highcharts({
            chart: {
                type: 'spline',
                backgroundColor: '#0a1718'
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
                },
                itemMarginBottom:5,
            },
            xAxis: {
                categories: ['前1个月', '前2个月', '前3个月', '前4个月', '前5个月', '前6个月', '前7个月', '前8个月', '前9个月', '前10个月', '前11个月', '前12个月'],
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
                enabled: false,
                formatter: function() {
                    return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
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
                name: '月营利',
                data: yyls,
                datalabel: {
                    shadow: false,
                }
            }, {
                name: '月成本',
                data: ycbs
            }, {
                name: '月利润',
                data: ylrs
            }]
        });
	}
	
    $(function () {
    	$(".current-select-date").text(date.substring(0,7).replace("-","年")+"月");
    	initLeftChart();
    	initRightChart();
    	initTrend();
    	initTrend2();
    	initStatus();
    });
    $(document).ready(function() {
        $(".loading").hide();
        $(".content").fadeIn();
    });

    (function($, doc) {
        $.init();
        $.ready(function() {
            //普通示例
            var yearandmonth = new Array();
            for (var i = 0; i < 10; i ++) {
                var ymChildrenArray = new Array();
                for (var j = 0; j < 12; j ++) {
                    var childrenObject = new Object();
                    childrenObject.value = j + 1;
                    childrenObject.text = j + 1 + "月";
                    ymChildrenArray.push(childrenObject);

                }
                var object = new Object();
                object.value =  i + 2016 ;
                object.text = i + 2016 + "年";
                object.children = ymChildrenArray;
                yearandmonth.push(object);
            };

            var yearMonthPicker = new $.PopPicker({layer: 2});
            yearMonthPicker.setData(yearandmonth);
            var selectDatePickerButton = doc.getElementById('selectDate');
            selectDatePickerButton.addEventListener('tap', function(event) {
                yearMonthPicker.show(function(items) {
                	var dateText = (items[0] || {}).value + "年" + (items[1] || {}).value + "月";
                	var date = (items[0] || {}).value + "-" + (items[1] || {}).value;
                	document.getElementById("current-select-date").innerHTML = dateText;
                	var serchDate = (items[0] || {}).value + "-" + (items[1] || {}).value + "-1";
                	date = serchDate;
                	jQuery.ajax({
                		type: "POST",
                		timeout : 1000000,
                		url: baseUrl+"boss/view/serch/business",
                        data: "storeId="+storeId+"&serchDate="+serchDate,
                        dataType: "json",
                        success: function(data) {
                        	if (data.code != 0){
                        		dialog(data.msg);
                        		return;
                        	}
                        	include = data.msg.include;
                        	consumption = data.msg.consumption;
                        	expenditure = data.msg.expenditure;
                        	
                        	kdjs = data.msg.kdjs;
                        	kcbs = data.msg.kcbs;
                        	kyls = data.msg.kyls;
                        	yyls = data.msg.yyls;
                            ycbs = data.msg.ycbs;
                        	ylrs = data.msg.ylrs;
                        	
                        	initLeftChart();
                        	initRightChart();
                        	reflushTable(data);
                        	initTrend();
                        	includeAmount = (parseInt(include[0][1]+include[1][1]+include[2][1]));
                        	expenditureAmount = expenditure.fixedCost+expenditure.writerCost+expenditure.briefOperation+expenditure.staffCosts+expenditure.administration+expenditure.directCost;
                        	initStatus();
                        }
                	});
                	return true;
                });
            }, false);
        });
    })(mui, document);

    /** 动态生成表格 */
    function reflushTable(data){
    	var nowPays = data.msg.nowPays;
    	var lastPays = data.msg.lastPays;
    	var nowPaysAmount = data.msg.nowPaysAmount;
    	var lastPaysAmount = data.msg.lastPaysAmount;
    	var expenditure = data.msg.expenditure;
    	var expenditureAmount = expenditure.fixedCost+expenditure.writerCost+expenditure.briefOperation+expenditure.staffCosts+expenditure.administration+expenditure.directCost;
    	$("#table").empty();
    	for (var i = 0; i < nowPays.length; i++) {
			var str = '<tr>'+
		            '<td>'+nowPays[i].businessProject +'</td>'+
		            '<td>'+changeTwoDecimal(nowPays[i].flowAmount,nowPaysAmount)+' %</td>'+
		            '<td>'+nowPays[i].flowAmount +'</td>'+
		            '<td>'+lastPays[i].flowAmount +'</td>'+
		          '</tr>';
		    $("#table").append($(str));      
		}
    	var str =  '<tr class="huizong-tr">'+
			            '<td>汇总</td>'+
			            '<td>'+changeTwoDecimal(expenditure.fixedCost,expenditureAmount)+'%'+'</td>'+
			            '<td>'+expenditure.fixedCost +'</td>'+
			            '<td>'+lastPaysAmount +'</td>'+
			        '</tr>';
		$("#table").append($(str));	  
		
    	$(".chart-sum").children("span").text(expenditureAmount);
    	$(".table-wrap.mt2").children(".chart-sum").html($('<span>固定支出明细</span>'));
    	$("#fixedCost").text("固定支出("+changeTwoDecimal(expenditure.fixedCost,expenditureAmount)+"%)");
    	$("#fixedCost").next().text(expenditure.fixedCost);
    	$("#writerCost").text("水电燃费("+changeTwoDecimal(expenditure.writerCost,expenditureAmount)+"%)");
    	$("#writerCost").next().text(expenditure.writerCost);
    	$("#briefOperation").text("间接运营("+changeTwoDecimal(expenditure.briefOperation,expenditureAmount)+"%)");
    	$("#briefOperation").next().text(expenditure.briefOperation);
    	$("#staffCosts").text("人员费用("+changeTwoDecimal(expenditure.staffCosts,expenditureAmount)+"%)");
    	$("#staffCosts").next().text(expenditure.staffCosts);
    	$("#administration").text("行政管理("+changeTwoDecimal(expenditure.administration,expenditureAmount)+"%)");
    	$("#administration").next().text(expenditure.administration);
    	$("#directCost").text("直接运营("+changeTwoDecimal(expenditure.directCost,expenditureAmount)+"%)");
    	$("#directCost").next().text(expenditure.directCost);
    	
    	$(".trend-num").children().eq(0).text(data.msg.assignRatio);
    	$(".trend-num").children().eq(0).text(data.msg.guestUnitPrice);
    	$(".trend-num").children().eq(0).text(data.msg.customerCost);
    	$(".trend-num").children().eq(0).text(data.msg.customerProfitability);
    }
    
    function serchProjectAmount(obj,codeName,amount){
    	$(".table-wrap.mt2").children(".chart-sum").html($('<span>'+codeName+'明细</span>'));
    	$(".expenditure-list").children().removeClass("active");
    	$(obj).addClass("active");
    	jQuery.ajax({
    		type: "POST",
    		url: baseUrl+"boss/view/serch/project",
            data: "storeId="+storeId+"&date="+date+"&codeName="+codeName,
            dataType: "json",
            success: function(data) {
            	if (data.code != 0){
            		dialog(data.msg);
            		return;
            	}
            	var nowPays = data.msg.nowPays;
            	var lastPays = data.msg.lastPays;
            	var nowPaysAmount = data.msg.nowPaysAmount;
            	var lastPaysAmount = data.msg.lastPaysAmount;
            	var expenditure = data.msg.expenditure;
            	var expenditureAmount = expenditure.fixedCost+expenditure.writerCost+expenditure.briefOperation+expenditure.staffCosts+expenditure.administration+expenditure.directCost;
            	
            	$("#table").empty();
            	for (var i = 0; i < nowPays.length; i++) {
        			var str = '<tr>'+
	        		            '<td>'+nowPays[i].businessProject +'</td>'+
	        		            '<td>'+changeTwoDecimal(nowPays[i].flowAmount,nowPaysAmount)+' %</td>'+
	        		            '<td>'+nowPays[i].flowAmount +'</td>'+
	        		            '<td>'+lastPays[i].flowAmount +'</td>'+
	        		          '</tr>';
        		    $("#table").append($(str));      
        		}
            	var str =  '<tr class="huizong-tr">'+
        			            '<td>汇总</td>'+
        			            '<td>'+changeTwoDecimal(amount,expenditureAmount)+'%'+'</td>'+
        			            '<td>'+amount +'</td>'+
        			            '<td>'+lastPaysAmount +'</td>'+
        			        '</tr>';
        		$("#table").append($(str));
            }
    	});
    }
    
    /**js除法,保留两位*/
    function changeTwoDecimal(x, y){
    	if(y==0)return 0;
    	return (x*100/y).toFixed(2);
    }
</script>
</body>
</html>