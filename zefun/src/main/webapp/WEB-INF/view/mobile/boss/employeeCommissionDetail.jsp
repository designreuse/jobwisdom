<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/base.jsp" %>
<%@ page import="java.util.Date" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta name="x5-orientation" content="portrait">
    <meta content="telephone=no" name="format-detection" />
    <meta name="msapplication-tap-highlight" content="no">
    <title>员工业绩详情</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
	<link rel="stylesheet" href="<%=swiperCssPath%>"/>
	<link rel="stylesheet" href="<%=starCssPath%>"/>
   	<link rel="stylesheet" href="<%=basePath%>css/mobile/boss-newer.css"/>
   	<link rel="stylesheet" href="<%=basePath%>css/mobile/chart-component.css">
	<link rel="stylesheet" href="<%=basePath%>css/mobile/employee-detail.css">
	<style type="text/css">
		.employee-info {
		    padding: 2rem 0rem 1rem 1rem;
		}
	</style>	
</head>
<body>
<div class="loading">
    <img src="<%=basePath%>/images/mobile/boss-newer/tb-loading.gif" alt=""/>
</div>

        <div class="content hide">
          

<div class="employee-detail chart-bg">

    <div id="employee-detail" class="employee-detail-wrap">
        <!--员工详情-->
        <div class="employee-info">
            <div class="img-wrap l">
                <img class="employee-img" src="<%=picPath%>/${employeeInfo.headImage}" alt="">
                <div class="gonghao">工号：${employeeInfo.employeeCode }</div>
            </div>
            <div class="info-desc">
                <div class="info-item"><span>姓名: </span><span class="value">${employeeInfo.name }</span><span class="ml3">工龄: </span><span class="value">${employeeInfo.seniority }</span></div>
                <div class="info-item"><span>部门: </span><span class="value">${employeeInfo.deptName }</span><span class="ml3">岗位: </span><span class="value">${employeeInfo.positionName }</span></div>
                <div class="info-item"><span>职位: </span><span class="value">${employeeInfo.levelName }</span></div>
                <div class="info-item"><span>电话: </span><span class="value">${employeeInfo.phone }</span></div>
            </div>
        </div>

        <!--业绩分布-->
        <div class="part-chart pt5">
            <img class="left-tag-line" src="<%=basePath%>/images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
                业绩分布
            </div>
            <div class="chart-wrap">
                <div class="employee-yeji" style="width:100%;height:320px;"></div>
            </div>
            <!--chart-wrap-->
        </div>
        <!--part-chart-->

        <!--技能分析-->
        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>/images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
                技能分析
            </div>

            <div class="sum-position">
                <span class="chart-sum">总服务人次:
                    <span class="num">${employeeCommissionDetailOfBossDto.customerCountOfIsAppoint + employeeCommissionDetailOfBossDto.customerCountOfNotAppoint}</span>
                </span>
            </div>

            <div class="chart-wrap">
                <div class="l w33p">
                    <div class="jineng1"  style="width:33.3%;height:200px;"></div>
                </div>
                <div class="l w33p">
                    <div class="jineng2"  style="width:33.3%;height:200px;"></div>
                </div>
                <div class="l w33p">
                    <div class="jineng3"  style="width:33.3%;height:200px;"></div>
                </div>
            </div>
            <!--chart-wrap-->
        </div>
        <!--part-chart-->

        <!--提成来源-->
        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>/images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
                提成来源
            </div>

            <div class="chart-wrap">
                <div class="employ-ticheng" id="employ-ticheng" style="width:100%;height:250px;"></div>
            </div>

            <!-- <div class="pie-legent pl3 pr3">
                <div>
                    <div class="legent-cell w50p"><div class="legent-tag lengent-one"></div>劳动提成</div>
                    <div class="legent-cell w50p"><div class="legent-tag lengent-two"></div>商品提成</div>
                </div>
                <div>
                    <div class="legent-cell w50p"><div class="legent-tag lengent-four"></div>卡项提成</div>
                    <div class="legent-cell w50p"><div class="legent-tag lengent-three"></div>套餐销售</div>
                </div>
            </div> -->
            <!--chart-wrap-->
        </div>
        <!--part-chart-->

        <!--顾客评价-->
        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>/images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
                顾客评价
            </div>
          <table>
          	  <!-- 该员工总共服务顾客人数(包含未评价顾客) -->
          	  <c:set var="employeeEvaluateTotalCount" value="${employeeCommissionDetailOfBossDto.employeeEvaluateFive + employeeCommissionDetailOfBossDto.employeeEvaluateFour + employeeCommissionDetailOfBossDto.employeeEvaluateThree + employeeCommissionDetailOfBossDto.employeeEvaluateTwo + employeeCommissionDetailOfBossDto.employeeEvaluateOne + employeeCommissionDetailOfBossDto.employeeEvaluateNull }"></c:set>
              <%-- <c:out value="employeeEvaluateTotalCount"></c:out> --%>
              <thead>
              <tr>
                  <th>综合得分 <span class="score ml">
                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0</c:if>
                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
                  		<fmt:formatNumber type="number" value="${((employeeCommissionDetailOfBossDto.employeeEvaluateFive + employeeCommissionDetailOfBossDto.employeeEvaluateNull)*5 + employeeCommissionDetailOfBossDto.employeeEvaluateFour*4 + employeeCommissionDetailOfBossDto.employeeEvaluateThree*3 + employeeCommissionDetailOfBossDto.employeeEvaluateTwo*2 + employeeCommissionDetailOfBossDto.employeeEvaluateOne*1)/employeeEvaluateTotalCount }" maxFractionDigits="2" />
                  	</c:if>
                  	</span>分</th>
                  <th>人数</th>
                  <th>占比</th>
              </tr>
              </thead>
              <tbody>
              <tr>
                  <td><input class="star rating" type="number" min=0 max=5 step=5 data-size="xs"  showClear="false" showCaption="false" value="5" readonly></td>
                  <td>${employeeCommissionDetailOfBossDto.employeeEvaluateFive }人</td>
                  <td>
                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateFive/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
                  	</c:if>
                  </td>
              </tr>
              <tr>
                  <td><input class="star rating" type="number" min=0 max=5 step=5 data-size="xs"  showClear="false" showCaption="false" value="4" readonly></td>
                  <td>${employeeCommissionDetailOfBossDto.employeeEvaluateFour }人</td>
                  <td>
                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateFour/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
                  	</c:if>
                  </td>
              </tr>
              <tr>
                  <td><input class="star rating" type="number" min=0 max=5 step=5 data-size="xs"  showClear="false" showCaption="false" value="3" readonly></td>
                  <td>${employeeCommissionDetailOfBossDto.employeeEvaluateThree }人</td>
                  <td>
                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateThree/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
                  	</c:if>
                  </td>
              </tr>
              <tr>
                  <td><input class="star rating" type="number" min=0 max=5 step=5 data-size="xs"  showClear="false" showCaption="false" value="2" readonly></td>
                  <td>${employeeCommissionDetailOfBossDto.employeeEvaluateTwo }人</td>
                  <td>
                  	<c:if test="${employeeEvaluateTotalCount == 0 }">0%</c:if>
                  	<c:if test="${employeeEvaluateTotalCount != 0 }">
                  		<fmt:formatNumber type="number" value="${employeeCommissionDetailOfBossDto.employeeEvaluateTwo/employeeEvaluateTotalCount * 100 }" maxFractionDigits="2"/>％
                  	</c:if>
                  </td>
              </tr>
              <tr>
                  <td><input class="star rating" type="number" min=0 max=5 step=5 data-size="xs"  showClear="false" showCaption="false" value="1" readonly></td>
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
        </div>
        <!--part-chart-->

        <!--工作态度-->
        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>/images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
                工作态度
            </div>

            <table>
                <thead>
                <tr>
                    <th>考评项目</th>
                    <th>次数</th>
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
            <!--chart-wrap-->
        </div>
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

<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=starJsPath%>"></script>
<script type="text/javascript" src="<%=muiJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/highcharts.src.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employeeCommission.js"> </script>

<script>
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
        $('.employee-yeji').highcharts({
            chart: {
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
            	enabled: false
                /* headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.2f} ￥</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true */
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
                        	"color": "#fff"
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
                name: '套餐销售',
                data: [Number(cashCommissionOfPackage), Number(cardCommissionOfPackage)],
            }, {
                name: '卡项销售',
                data: [Number(cashCommissionOfCard), Number(cardCommissionOfCard)],
            }]
        });

        //技能分析(大项/小项)
        $('.jineng1').highcharts({
            chart: {
                backgroundColor: '#0a1718',
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
                text: '<div style="text-align: center; margin-top: -20px;"><span style="font-size: 12px;color: #b8c2cc;">'+
                	'${employeeCommissionDetailOfBossDto.customerCountOfBigProject + employeeCommissionDetailOfBossDto.customerCountOfSmallProject}'
                	+'</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">人</span></div>',
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
        $('.jineng2').highcharts({
            chart: {
                backgroundColor: '#0a1718',
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
                	+'${employeeCommissionDetailOfBossDto.customerCountOfIsAppoint + employeeCommissionDetailOfBossDto.customerCountOfNotAppoint}'
                	+'</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">人</span></div>',
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
        $('.jineng3').highcharts({
            chart: {
                backgroundColor: '#0a1718',
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
                	+'${employeeCommissionDetailOfBossDto.customerCountOfIsAssign + employeeCommissionDetailOfBossDto.customerCountOfNotAssign}'
                	+'</span><br><span style="font-size: 9px;color: #7f868d;display: inline-block;margin-top: -3px;">人</span></div>',
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
        $('.employ-ticheng').highcharts({
            chart: {
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
                	+'${employeeCommissionDetailOfBossDto.employeeCommissionOfCard + employeeCommissionDetailOfBossDto.employeeCommissionOfProject + employeeCommissionDetailOfBossDto.employeeCommissionOfGoods + employeeCommissionDetailOfBossDto.employeeCommissionOfPackage}'
                	+'</span><br><span style="font-size: 14px;color: #7f868d">总提成</span></div>',
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
                    ['套餐提成', Number('${employeeCommissionDetailOfBossDto.employeeCommissionOfPackage}')]
                ]
            }]
        });

    });
    
    $(document).ready(function() {
        $(".loading").hide();
        $(".content").fadeIn();
    });
</script>
</body>
</html>
