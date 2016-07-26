<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/base.jsp" %>
<%@ page import="java.util.Date" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta name="x5-orientation" content="portrait">
    <meta content="telephone=no" name="format-detection" />
    <meta name="msapplication-tap-highlight" content="no">
    <title>员工个人表现</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
	<link rel="stylesheet" href="<%=starCssPath%>"/>
	<link rel="stylesheet" href="<%=muiCssPath%>"/>
	<link rel="stylesheet" href="<%=employeeCssPath%>"/>
   	<link rel="stylesheet" href="<%=basePath%>css/mobile/boss-newer.css"/>
   	<link rel="stylesheet" href="<%=basePath%>css/mobile/chart-component.css">
	<link rel="stylesheet" href="<%=basePath%>css/mobile/employee-detail.css">
<style type="text/css">
	.chart-bg{
        background-color: #eee;
    }
    .employee-detail-wrap {
         background-color: #eee;
    }
    .top-fix {
        position: fixed;
        padding: 0 .625rem;
        top: 0;
        width: 100%;
        height: 5rem;
        line-height: 5rem;
        background-color: #fff;
        z-index: 10;
    }

    .date-btn {
        float: left;
        width: 3.25rem;
        height: 3rem;
        line-height: 3rem;
        background-color: #12b5b1;
        color: #fff;
        text-align: center;
    }

    .current-select-date {
        float: left;
        width: 12.75rem;
        height: 3rem;
        line-height: 3rem;
        background-color: #141e22;
        color: #fff;
        text-align: center;
        font-size: 12px;
    }
    .profit {
        float: right;
        color: #a2abb5;
        margin-right: 1.25rem;
    }

    .profit-num {
        font-size: 15px;
        color: #35cb66;
    }
    .part-chart {
        position: relative;
        border-top: 0;
        clear: both;
        padding: 6.875rem 0 5rem;
        background: #fff;
    }
    .yg-chart-title {
        height: 3rem;
        line-height: 3rem;
        background-color: #eee;
        color: #999;
        padding-left: 1.25rem;
    }
    .top-fix {
        position: static;
        top: 7rem;
        padding: 1rem;
    }
    .employee-detail {
        padding-top: 0rem;
    }
    thead th {
        background-color: #fff;
        color: #666;
    }
    tbody td {
        color: #666;
    }
    tbody tr:nth-child(odd) td {
        background-color: #f5f5f5;
    }
    tbody tr:nth-child(even) td {
        background-color: #fff;
    }
    .current-select-date {
        background-color: #eee;
        color: #999;
    }
    .profit {
        line-height: 2.5rem;
    }
    .profit-num {
        color: #21262b;
        font-size: 15px;
        font-weight: 700;
    }
    .chart-sum .num {
        color: #ff7e7d;
    }
    .score {
        border-bottom: 0;
    }
    .rating-sm {
        font-size: 2.1em;
    }
    .rating-container {
        color: #dad9d7;
        letter-spacing: 4px;
    }
    .part-chart {
        box-shadow: 0px 2px 5px 0px rgba(0, 0, 0, 0.2);
    }
    
    .time_rank ul{
    width: 20rem;
    height: 3.5rem;
    margin: 0 auto;
    line-height: 3,5rem;
    padding-top: 1.1rem;}
		 .time_rank li{height:3rem;line-height:3rem;float:left;width:50%;text-align:center;border: 1px solid white;color: white;
    font-size: 1.6rem;}
		 .time_rank{height:5rem;line-height:5rem;background: #ea631a;}
		 
	.time_rank li.active {
    background: white;
    color: #ea631a;}
</style>
</head>
<body>
<div class="wrap">
	<div class="content">
		<div class="time_rank clearfix">
		    <ul >
		        <%-- <li class="score-shop-li active w50p" data-target="yeji-d" onclick="findemployeeCommissionDetailByNowDayOrMonth(this)">
		            <a href="javascript:void(0);">
		                <img src="<%=basePath %>images/mobile/boss-newer/active-new.png" alt=""/>
		                <div class="tab-word"  id="selectTime">
		                    <span>今日</span>
		                </div>
		            </a>
		        </li>
		        <li class="score-shop-li w50p" data-target="yeji-m" onclick="findemployeeCommissionDetailByNowDayOrMonth(this)">
		            <a href="javascript:void(0);">
		                <img src="<%=basePath %>images/mobile/boss-newer/active-new.png" alt="" class="hide"/>
		                <div class="tab-word" id="selectDate">
		                    <span>本月</span>
		                </div>
		            </a>
		        </li> --%>
		        <li class="active" onclick="findemployeeCommissionDetailByNowDayOrMonth(this)" id="selectTime">今日</li>
		    	<li onclick="findemployeeCommissionDetailByNowDayOrMonth(this)" id="selectDate">本月</li>
		    </ul>
		</div>
		<div class="clearfix"></div>
	
		<div class="employee-detail chart-bg">
		
		    <div id="employee-detail" class="employee-detail-wrap">
		
		        <div class="top-fix">
		            <div class="date" id="selectDate">
		                <!-- <div class="date-btn">
		                    <span class="iconfont icon-zhankai"></span>
		                </div> -->
		                <div class="current-select-date" onclick="selectDate()">
		                    2016-03-07
		                </div>
		            </div>
		            <div class="profit"><span>当日提成</span> <span class="profit-num">${employeeCommissionSum }</span></div>
		        </div>
		        <!--业绩分布-->
		        <div class="yg-chart-title">业绩分布</div>
		        <div class="part-chart pt5">
		            <div class="chart-wrap">
		                <div id="employee-yeji" class="employee-yeji" style="width:100%;height:320px;"></div>
		            </div>
		            <!--chart-wrap-->
		        </div>
		        <!--part-chart-->
		
		
		        <!--技能分析-->
		        <div class="yg-chart-title">技能分析</div>
		        <div class="part-chart">
		            <div class="sum-position">
		                <span class="chart-sum">总服务人次:
		                    <span class="num">${employeeCommissionDetailOfBossDto.customerCountOfIsAppoint + employeeCommissionDetailOfBossDto.customerCountOfNotAppoint}</span>
		                </span>
		            </div>
		
		            <div class="chart-wrap">
		                <div class="l w33p">
		                    <div id="jineng1" class="jineng1"  style="width:100%;height:200px;"></div>
		                </div>
		                <div class="l w33p">
		                    <div id="jineng2" class="jineng2"  style="width:100%;height:200px;"></div>
		                </div>
		                <div class="l w33p">
		                    <div id="jineng3" class="jineng3"  style="width:100%;height:200px;"></div>
		                </div>
		            </div>
		            <!--chart-wrap-->
		        </div>
		        <!--part-chart-->
		
		        <!--提成来源-->
		        <div class="yg-chart-title">提成来源</div>
		        <div class="part-chart">
		            <div class="chart-wrap">
		                <div id="employ-ticheng" class="employ-ticheng" id="employ-ticheng" style="width:100%;height:250px;"></div>
		            </div>
		
		            <!-- <div class="pie-legent pl3 pr3">
		                <div>
		                    <div class="legent-cell w50p"><div class="legent-tag lengent-one"></div>卡项提成</div>
		                    <div class="legent-cell w50p"><div class="legent-tag lengent-two"></div>项目提成</div>
		                </div>
		                <div>
		                    <div class="legent-cell w50p"><div class="legent-tag lengent-three"></div>商品提成</div>
		                    <div class="legent-cell w50p"><div class="legent-tag lengent-four"></div>疗程提成</div>
		                </div>
		            </div> -->
		            <!--chart-wrap-->
		        </div>
		        <!--part-chart-->
		
		        <!--顾客评价-->
		        <div class="yg-chart-title">顾客评价</div>
		        <table id="customerEvaluationTable">
		        	<!-- 该员工总共服务顾客人数(包含未评价顾客) -->
		        	<c:set var="employeeEvaluateTotalCount" value="${employeeCommissionDetailOfBossDto.employeeEvaluateFive + employeeCommissionDetailOfBossDto.employeeEvaluateFour + employeeCommissionDetailOfBossDto.employeeEvaluateThree + employeeCommissionDetailOfBossDto.employeeEvaluateTwo + employeeCommissionDetailOfBossDto.employeeEvaluateOne + employeeCommissionDetailOfBossDto.employeeEvaluateNull }"></c:set>
		            <thead>
			            <tr>
			                <th>综合得分 
			                	<span class="score ml">
									<c:if test="${employeeEvaluateTotalCount == 0 }">0</c:if>
				                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
				                  		<fmt:formatNumber type="number" value="${((employeeCommissionDetailOfBossDto.employeeEvaluateFive + employeeCommissionDetailOfBossDto.employeeEvaluateNull)*5 + employeeCommissionDetailOfBossDto.employeeEvaluateFour*4 + employeeCommissionDetailOfBossDto.employeeEvaluateThree*3 + employeeCommissionDetailOfBossDto.employeeEvaluateTwo*2 + employeeCommissionDetailOfBossDto.employeeEvaluateOne*1)/employeeEvaluateTotalCount }" maxFractionDigits="2" />
				                  	</c:if>
								</span>分
							</th>
			                <th>人数</th>
			                <th>占比</th>
			            </tr>
		            </thead>
		            <tbody>
			            <tr>
			                <td>
			                	<input class="star rating" type="number" min=0 max=5 step=5 data-size="sm"  showClear="false" showCaption="false" value="5" readonly>
			                </td>
		                  	<td>${employeeCommissionDetailOfBossDto.employeeEvaluateFive }人</td>
		                  	<td>
			                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
			                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
			                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateFive/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
			                  	</c:if>
			                </td>
			            </tr>
			            <tr>
			                <td><input class="star rating" type="number" min=0 max=5 step=5 data-size="sm"  showClear="false" showCaption="false" value="4" readonly></td>
			                  <td>${employeeCommissionDetailOfBossDto.employeeEvaluateFour }人</td>
			                  <td>
			                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
			                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
			                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateFour/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
			                  	</c:if>
			                  </td>
			            </tr>
			            <tr>
			                <td><input class="star rating" type="number" min=0 max=5 step=5 data-size="sm"  showClear="false" showCaption="false" value="3" readonly></td>
			                  <td>${employeeCommissionDetailOfBossDto.employeeEvaluateThree }人</td>
			                  <td>
			                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
			                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
			                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateThree/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
			                  	</c:if>
			                  </td>
			            </tr>
			            <tr>
			                <td><input class="star rating" type="number" min=0 max=5 step=5 data-size="sm"  showClear="false" showCaption="false" value="2" readonly></td>
			                  <td>${employeeCommissionDetailOfBossDto.employeeEvaluateTwo }人</td>
			                  <td>
			                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
			                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
			                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateTwo/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
			                  	</c:if>
			                  </td>
			            </tr>
			            <tr>
			            	<td><input class="star rating" type="number" min=0 max=5 step=5 data-size="sm"  showClear="false" showCaption="false" value="1" readonly></td>
			                  <td>${employeeCommissionDetailOfBossDto.employeeEvaluateOne }人</td>
			                  <td>
			                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
			                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
			                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateOne/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
			                  	</c:if>
			                  </td>
			            </tr>
			            <tr>
			                <td>未评分</td>
			                  <td>${employeeCommissionDetailOfBossDto.employeeEvaluateNull }人</td>
			                  <td>
			                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
			                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
			                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateNull/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
			                  	</c:if>
			                  </td>
			            </tr>
		            </tbody>
		        </table>
		        <!--<div class="part-chart">-->
		            <!---->
		        <!--</div>-->
		        <!--part-chart-->
		
		        <!--工作态度-->
		        <div class="yg-chart-title">工作态度</div>
		        <table id="workAttitudeTable">
		            <thead>
			            <tr>
			                <th>考评项目</th>
			                <th>次数/时间</th>
			            </tr>
		            </thead>
		            <tbody>
		            	<tr>
		                    <td>迟到</td>
		                    <td>${employeeCommissionDetailOfBossDto.rewardCountOfLate }</td>
		                </tr>
		                <tr>
		                    <td>请假</td>
		                    <td>${employeeCommissionDetailOfBossDto.rewardCountOfHoliday }</td>
		                </tr>
		                <tr>
		                    <td>旷工</td>
		                    <td>${employeeCommissionDetailOfBossDto.rewardCountOfAbsenteeism }</td>
		                </tr>
		                <tr>
		                    <td>大过</td>
		                    <td>${employeeCommissionDetailOfBossDto.rewardCountOfSeriousMistake }</td>
		                </tr>
		                <tr>
		                    <td>小过</td>
		                    <td>${employeeCommissionDetailOfBossDto.rewardCountOfSmallMistake }</td>
		                </tr>
		                <tr>
		                    <td>投诉</td>
		                    <td>${employeeCommissionDetailOfBossDto.rewardCountOfComplaint }</td>
		                </tr>
		            </tbody>
		        </table>
		        <!--part-chart-->
		    </div>
		</div>
		<!--tab end-->
	</div>
	<!--business-report-->

	<div class="actionsheet hide s-modal s-modal-miss" id="actionsheet">
	
	    <div class="m-actionsheet-toggle">
	        <div class="m-actionsheet-menu">
	            <ul>
	                <li class="actionsheet-cell">美容部</li>
	                <li class="actionsheet-cell">足浴部</li>
	                <li class="actionsheet-cell">美甲部</li>
	                <li class="actionsheet-cell">皮肤管理部</li>
	                <li class="actionsheet-cell">美发部</li>
	            </ul>
	        </div>
	        <div class="m-actionsheet-action">
	            <ul>
	                <li class="s-modal-miss actionsheet-cell">取消</li>
	            </ul>
	        </div>
	    </div>
	</div>
</div>

<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=starJsPath%>"></script>
<script type="text/javascript" src="<%=muiJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/highcharts.src.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/individualPerformance.js"></script>
<script type="text/javascript">
	var globalEmployeeId = ${employeeInfo.employeeId};
	//业绩分布各项参数保留两位小数
	var cashCommissionOfProject = ${employeeCommissionDetailOfBossDto.cashCommissionOfProject}.toFixed(2);
	var cardCommissionOfProject = ${employeeCommissionDetailOfBossDto.cardCommissionOfProject}.toFixed(2);
	var cashCommissionOfGoods = ${employeeCommissionDetailOfBossDto.cashCommissionOfGoods}.toFixed(2);
	var cardCommissionOfGoods = ${employeeCommissionDetailOfBossDto.cardCommissionOfGoods}.toFixed(2);
	var cashCommissionOfPackage = ${employeeCommissionDetailOfBossDto.cashCommissionOfPackage}.toFixed(2);
	var cardCommissionOfPackage = ${employeeCommissionDetailOfBossDto.cardCommissionOfPackage}.toFixed(2);
	var cashCommissionOfCard = ${employeeCommissionDetailOfBossDto.cashCommissionOfCard}.toFixed(2);
	var cardCommissionOfCard = ${employeeCommissionDetailOfBossDto.cardCommissionOfCard}.toFixed(2);

    $(".star").rating();
    //业绩分布
    $(function () {
    	globalPerformanceChart = new Highcharts.Chart({
            chart: {
            	renderTo: 'employee-yeji',  
                backgroundColor: 'rgba(0,0,0,0)',
                type: 'column'
            },
            /* legend: {
                itemStyle: {
                    color: '#a2abb5',
                }
            }, */
            legend: {
                backgroundColor: 'rgba(0,0,0,0)',
                itemDistance: 40,
                itemMarginBottom: 10,
                itemWidth: 115,
                itemStyle: {
                    color: '#a2abb5',
                }
            },
            colors: ["#3982ce", "#ff7e7d", '#b5e986', '#ffc07b'],
            credits: {
                enabled: false,
            },
            title: {
                text: ''
            },
            xAxis: {
                categories: [
					'现金业绩￥${employeeCommissionDetailOfBossDto.cashCommissionSum}',
					'卡金业绩￥${employeeCommissionDetailOfBossDto.cardCommissionSum}'
                ],
                tickWidth: 0,
                lineColor: '#383943',
                labels: {
                    align: 'center',
                    x: -5,
                    style: {
                        color: '#a2abb5'
                    }
                },
            },
            yAxis: {
                title: {
                    text: ''
                },
                tickPixelInterval: 30,
                gridLineColor: '#383943',
                labels: {
                    style: {
                        color: '#a2abb5'
                    }
                }
            },
            tooltip: {
            	enable : false
                /* headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:5px">{series.name}: </td>' +
                '<td style="padding:5px"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true,
                backgroundColor:'#FFF', */
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                },
                series: {
                    pointWidth: 17,
                    stickyTracking: false,
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
                        format: '{y}',
                        style: {
                        	"textShadow": "none" ,
                        	"color": "#000000"
                        }
                      
                    }
                }
            },
            series: [{
                name: '劳动业绩',
                data: [Number(cashCommissionOfProject), Number(cardCommissionOfProject)],
            }, {
                name: '商品销售',
                data: [Number(cashCommissionOfGoods), Number(cardCommissionOfGoods)],
            }, {
                name: '疗程销售',
                data: [Number(cashCommissionOfPackage), Number(cardCommissionOfPackage)],
            }, {
                name: '卡项销售',
                data: [Number(cashCommissionOfCard), Number(cardCommissionOfCard)],
            }]
        });
        
      	//技能分析(大项/小项)
        globalSkillsOfBigAndSmallProjectChart = new Highcharts.Chart({
            chart: {
            	renderTo: 'jineng1',
                backgroundColor: 'rgba(0,0,0,0)',
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
            colors: ['#5b50cd', '#e99645'],
            title: {
                align: 'center',
                useHTML: true,
                text: '<div style="text-align: center; margin-top: -20px;"><span style="font-size: 12px;color: #b8c2cc;">'
            	+ '${employeeCommissionDetailOfBossDto.customerCountOfBigProject + employeeCommissionDetailOfBossDto.customerCountOfSmallProject}'
            	+ '</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">人</span></div>',
                verticalAlign: 'middle',
                y: -10,
                style: {
                    fontWeight: 'bold',
                    color: '#ffffff'
                }
            },
            tooltip: {
            	useHTML: true,
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b><br/>人数:<b>{point.y}</b>'
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
                name: '占比',
                innerSize: '65%',
                showInLegend: true,
                data: [
                    ['大项',  Number('${employeeCommissionDetailOfBossDto.customerCountOfBigProject}')],
                    ['小项',  Number('${employeeCommissionDetailOfBossDto.customerCountOfSmallProject}')]
                ]
            }]
        });

      	//技能分析(预约/非预约)
        globalSkillsOfIsAndNoAppointChart = new Highcharts.Chart({
            chart: {
            	renderTo: 'jineng2',
                backgroundColor: 'rgba(0,0,0,0)',
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
            colors: ['#3982ce', '#e079e1'],
            title: {
                align: 'center',
                useHTML: true,
                text: '<div style="text-align: center; margin-top: -20px;"><span style="font-size: 12px;color: #b8c2cc;">'
                	+ '${employeeCommissionDetailOfBossDto.customerCountOfIsAppoint + employeeCommissionDetailOfBossDto.customerCountOfNotAppoint}'
                	+ '</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">人</span></div>',
                verticalAlign: 'middle',
                y: -10,
                style: {
                    fontWeight: 'bold',
                    color: '#ffffff'
                }
            },
            tooltip: {
            	useHTML: true,
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b><br/>人数:<b>{point.y}</b>'
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
                name: '占比',
                innerSize: '65%',
                showInLegend: true,
                data: [
					['预约', Number('${employeeCommissionDetailOfBossDto.customerCountOfIsAppoint}')],
					['非预约', Number('${employeeCommissionDetailOfBossDto.customerCountOfNotAppoint}')]
                ]
            }]
        });

      	//技能分析(指定/非指定)
        globalSkillsOfIsAndNoAssignChart = new Highcharts.Chart({
            chart: {
            	renderTo: 'jineng3',
                backgroundColor: 'rgba(0,0,0,0)',
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
            colors: ['#39cea2', '#dfe14c'],
            title: {
                align: 'center',
                useHTML: true,
                text: '<div style="text-align: center; margin-top: -20px;"><span style="font-size: 12px;color: #b8c2cc;">'
                	+ '${employeeCommissionDetailOfBossDto.customerCountOfIsAssign + employeeCommissionDetailOfBossDto.customerCountOfNotAssign}'
                	+ '</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">人</span></div>',
                verticalAlign: 'middle',
                y: -10,
                style: {
                    fontWeight: 'bold',
                    color: '#ffffff'
                }
            },
            tooltip: {
            	useHTML: true,
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b><br/>人数:<b>{point.y}</b>'
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
                name: '占比',
                innerSize: '65%',
                showInLegend: true,
                data: [
                    ['指定', Number('${employeeCommissionDetailOfBossDto.customerCountOfIsAssign}')],
                    ['非指定', Number('${employeeCommissionDetailOfBossDto.customerCountOfNotAssign}')]
                ]
            }]
        });


        Highcharts.setOptions({
            colors: ['#ffc07b', '#3982ce', '#ff7e7d', '#b5e986']
        });
        //提成来源
        globalCommissionChart = new Highcharts.Chart({
            chart: {
            	renderTo: 'employ-ticheng',
                backgroundColor: 'rgba(0,0,0,0)',
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false,
            },
            legend: {
                backgroundColor: 'rgba(0,0,0,0)',
                itemDistance: 40,
                itemMarginBottom: 10,
                itemWidth: 115,
                itemStyle: {
                    color: '#a2abb5',
                }
            },
            credits: {
                enabled: false,
            },
            title: {
                align: 'center',
                useHTML: true,
                text: '<div style="text-align: center"><span style="font-size: 20px;color: #b8c2cc">'
                	+ '${employeeCommissionDetailOfBossDto.employeeCommissionOfCard + employeeCommissionDetailOfBossDto.employeeCommissionOfProject + employeeCommissionDetailOfBossDto.employeeCommissionOfGoods + employeeCommissionDetailOfBossDto.employeeCommissionOfPackage}'
                	+ '</span><br><span style="font-size: 14px;color: #7f868d">总提成</span></div>',
                verticalAlign: 'middle',
                y:-45,
                style: {
                    fontWeight: 'bold',
                    color: '#7f868d'
                }
            },
            tooltip: {
            	useHTML: true,
            	pointFormat: '{series.name}: <b>{point.percentage:.2f}%</b><br/>金额:<b>￥{point.y:.2f}</b>'
            },
            plotOptions: {
                pie: {
                    dataLabels: {
                        enabled: true,
                        distance: 0,
                        style: {
                            fontWeight: 'bold',
                            color: '#b8c2cc',
                            textShadow: '0px 1px 2px black'
                        },
                        formatter: function() {
                        	return (this.percentage).toFixed(2) + "%" ;
                        }
                    },
                    showInLegend: true,
                    borderWidth: 0,
                    startAngle: 0,
                    endAngle: 360,
                    center: ['50%', '50%']
                }
            },
            series: [{
                type: 'pie',
                name: '占比',
                size: '120%',
                innerSize: '75%',
                data: [
                    ['卡项提成', Number('${employeeCommissionDetailOfBossDto.employeeCommissionOfCard}')],
                    ['项目提成', Number('${employeeCommissionDetailOfBossDto.employeeCommissionOfProject}')],
                    ['商品提成', Number('${employeeCommissionDetailOfBossDto.employeeCommissionOfGoods}')],
                    ['疗程提成', Number('${employeeCommissionDetailOfBossDto.employeeCommissionOfPackage}')]
                ]
            }]
        });

    });
    
    var dpi = window.devicePixelRatio || 1;
    /*模态框出来*/
    $(".s-modal-control").on("click", function(){
        $('body').css({"overflow":"hidden"});
        var targetModal = $(this).data("target");
        /*console.log("targetModal" + targetModal);*/
        $(targetModal).removeClass("hide");
    });

    /*模态框消失*/
    $(".s-modal-miss").on("click", function(){
        $('body').css("overflow-y","auto");
        $(".s-modal").addClass("hide");
    });

    $(function() {
       $(".loading").hide();
        $(".wrap").removeClass("hide");
    });
    
    
   	//触发日期选择器(记得引入样式<link rel="stylesheet" href="<%=muiCssPath%>"/>)
    function selectDate(){
   		//选择触发事件
    	datePicker.show(function(date) {
    		globalDate = date[0].value + "-" + date[1].value + "-" + date[2].value;
    		console.log("globalDate : " + globalDate);
    		findemployeeCommissionDetailByTime(globalDate);
        });
    }
   	
    var datePicker, monthPicker;
    (function($, doc) {
        $.init();
        $.ready(function() {
        	var curDate = new Date();
        	var curYear = curDate.getFullYear();
        	var curMonth = curDate.getMonth();
        	var curDay = curDate.getDate();
    		//年月日
            var dateArray = new Array();
            for (var i = -4; i < 1; i ++) {
                var monthArray = new Array();
                for (var j = 0; j < 12; j ++) {
                    //月的最后一天
                    var year = curYear - i;
                    var month = j;
                    var lastDay = new Date(year, month + 1, 0).getDate();//month 要加1,原本month是从0开始的,但是因为0是这个月的上个月,所以到了上个月.
                    if(year == curYear && month == 1) {
                        console.log("lastday" + lastDay);
                    }
                    var dayArray = new Array();
                    for(var k = 0; k < lastDay; k ++) {
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

            datePicker = new $.PopPicker({layer: 3});
            datePicker.setData(dateArray);
        });
    })(mui, document);
    
</script>
</body>
</html>