<!DOCTYPE html>
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
    <title>客情分析</title>
    <link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/boss-newer.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/chart-component.css">
    <link rel="stylesheet" href="<%=basePath%>css/mobile/client-analyze.css">
    <style>
    .chart-wrap {
    	position: relative;
    }
    .part-tag {
    	letter-spacing: 1px;
	}
    </style>	
</head>
<body>

<div class="loading">
    <img src="<%=basePath%>images/mobile/boss-newer/tb-loading.gif" alt=""/>
</div>

<div class="content hide">
<div class="client-analyze chart-bg">

    <!--劳动业绩-->
    <div id="client-analyze">
        <div class="part-chart">
            <div class="year-month-day">
                <ul>
                    <li onclick="changeSerchType('year', this);">年</li>
                    <li onclick="changeSerchType('month', this);">月</li>
                    <li onclick="changeSerchType('day', this);" class="active">日</li>
                </ul>
            </div>
            <!--year-month-day-->

            <div class="chart-wrap part-chart">
            	
	            	<img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
		            <div class="part-tag jrllfx">
		                	今日客量分析
		            </div>
	                <div id="client-chart" style="width:100%;height:400px;"></div>
	                <!-- <div class="pie-legent">
	                        <div class="legent-cell">
	                            <div class="w50p l huiyuan">
	                                <div class="legent-tag lengent-one"></div>会员  <span class="num f30 ml2">259</span>
	                            </div>
	                            <div class="w50p l tl">
	                                <span class="nan ml5">男 <span>250</span></span> <span class="nv">女 <span>9</span></span>
	                            </div>
	                        <div class="legent-cell">
	                            <div class="w50p l sanke">
	                                <div class="legent-tag lengent-two"></div>散客  <span class="num f30 ml2">259</span>
	                            </div>
	                            <div class="w50p l tl">
	                                <span class="nan ml5">男 <span>250</span></span> <span class="nv">女 <span>9</span></span>
	                            </div>
	                        </div>
	                </div> -->
            </div>
            <!--chart-wrap-->
        </div>
        <!--part-chart-->

        <!--今日流量分析-->
        <div class="part-chart pt5">
            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag jrllfx">
                	今日时段流量分析
            </div>
            <div class="chart-wrap c1">
                <div id="client-chart1" style="width:100%;height:320px;"></div>
            </div>
        </div>
        <!--part-chart-->
        
        <!--七天流量趋势图-->
        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag qtllfx">
                	七天流量趋势图
            </div>
            <div class="chart-wrap c4">
                <div id="client-chart4" style="width:100%;height:400px;"></div>
            </div>
        </div>
        <!--part-chart-->

        <!--项目消费分析-->
        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
                	顾客消费力分析
            </div>
            <div class="left-chart" id="client-chart2-left" style="width: 100%;height:300px;"></div>
           
            
            <!-- <div class="chart-wrap">
                <div class="left-chart w60p l" id="client-chart2-left" style="width:50%;height:250px;"></div>
                <div class="right-chart w40p l" id="client-chart2-right" style="width:50%;height:250px;"></div>
            </div> -->
        </div>
        
        <!--part-chart-->
        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
                	顾客贡献业绩分析
            </div>
           
            <div class="right-chart" id="client-chart2-right" style="width: 100%;height:300px;"></div>
            
            <!-- <div class="chart-wrap">
                <div class="left-chart w60p l" id="client-chart2-left" style="width:50%;height:250px;"></div>
                <div class="right-chart w40p l" id="client-chart2-right" style="width:50%;height:250px;"></div>
            </div> -->
        </div>
        <!--part-chart-->

		<!--七天流量趋势图-->
        <%-- <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag">
                	项目消费趋势图
            </div>
            <div class="chart-wrap c5">
                <div id="client-chart5" style="width:100%;height:400px;"></div>
            </div>
            <!--chart-wrap-->
        </div> --%>
    </div>
    </div>
    <!--tab end-->

</div>
<!--business-report-->

<div class="footer">
    <ul>
        <li>
            <a href="<%=basePath %>boss/view/bossObjective/${session_key_store_id}/2">
                <span class="iconfont icon-yewubiaobiao"></span>
                <span>业绩报表</span>
            </a>
        </li>
        <li>
            <a href="<%=basePath %>boss/view/business/${session_key_store_id}/2">
                <span class="iconfont icon-yingyefenxi"></span>
                <span>营业分析</span>
            </a>
        </li>
        <li class="active">
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

<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/base.js"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<!--导航滑动所需的js-->
<script type="text/javascript" src="<%=basePath%>js/mobile/swiper.jquery.min.js"></script>
<!--boss js-->
<script type="text/javascript" src="<%=basePath%>js/mobile/highcharts.src.js"> </script>

<!--客情分析-->
<script>
	var storeId = '${storeId}';
	var customerAnalysisFlow = eval("(" + '<%=request.getAttribute("customerAnalysisFlow")%>' + ")");
	var customerAnalysisFlux = eval("(" + '<%=request.getAttribute("customerAnalysisFlux")%>' + ")");
	var customerAnalysisFlol = eval("(" + '<%=request.getAttribute("customerAnalysisFlol")%>' + ")");
	
	var customerAnalysisKdj = eval("(" + '<%=request.getAttribute("customerAnalysisKdj")%>' + ")");
	var customerAnalysisKbt = eval("(" + '<%=request.getAttribute("customerAnalysisKbt")%>' + ")");
	var levels = eval("(" + '<%=request.getAttribute("levels")%>' + ")");
	var levelName = eval("(" + '<%=request.getAttribute("levelName")%>' + ")");
	var kbk = eval("(" + '<%=request.getAttribute("kbk")%>' + ")");
	var customerAnalysisProject = eval("(" + '<%=request.getAttribute("customerAnalysisProject")%>' + ")");
	var amount = '${amount}';
	
	/**初始化文字内容*/
	function initData(){
		jQuery(".legent-tag").eq(0).next().text(customerAnalysisFlow.memberManCount+customerAnalysisFlow.memberWomenCount);
		jQuery(".legent-tag").eq(1).next().text(customerAnalysisFlow.sankManCount+customerAnalysisFlow.sankWomenCount);
		jQuery(".nan").eq(0).html('男 <span>'+customerAnalysisFlow.memberManCount+'</span>');
		jQuery(".nan").eq(1).html('男 <span>'+customerAnalysisFlow.sankManCount+'</span>');
		jQuery(".nv").eq(0).html('女 <span>'+customerAnalysisFlow.memberWomenCount+'</span>');
		jQuery(".nv").eq(1).html('女 <span>'+customerAnalysisFlow.sankWomenCount+'</span>');
	}
	
	/**客情分析*/
	function initCustomerFlow(){
		initData();
        var colors = ['#ffc450', '#37d38b', '#ffda6f', '#fff7d8', '#5be4a5', '#9ae9c5',];
        var sum = customerAnalysisFlow.sankManCount + customerAnalysisFlow.sankWomenCount + customerAnalysisFlow.memberManCount + customerAnalysisFlow.memberWomenCount;
        var sanSum = customerAnalysisFlow.sankManCount + customerAnalysisFlow.sankWomenCount;
        var memberSum = customerAnalysisFlow.memberManCount + customerAnalysisFlow.memberWomenCount;
        var sanke = changeTwoDecimal(customerAnalysisFlow.sankManCount + customerAnalysisFlow.sankWomenCount, sum); //(customerAnalysisFlow.sankManCount + customerAnalysisFlow.sankWomenCount)/sum * 100;
        var member = changeTwoDecimal(customerAnalysisFlow.memberManCount + customerAnalysisFlow.memberWomenCount, sum); //(customerAnalysisFlow.memberManCount + customerAnalysisFlow.memberWomenCount)/sum * 100;
        var mnv = [changeTwoDecimal(customerAnalysisFlow.memberManCount, memberSum), changeTwoDecimal(customerAnalysisFlow.memberWomenCount, memberSum),
                   changeTwoDecimal(customerAnalysisFlow.sankManCount, sanSum), changeTwoDecimal(customerAnalysisFlow.sankWomenCount, sanSum)];
        var snv = [changeTwoDecimal(customerAnalysisFlow.sankManCount, sanSum), changeTwoDecimal(customerAnalysisFlow.sankWomenCount, sanSum)];
       /*  categories = [member + "%",sanke + '%'], */
        name = '客量分析',
        //性别数据
        data = [{
        	y: (customerAnalysisFlow.memberManCount+customerAnalysisFlow.memberWomenCount),
            color: colors[0],
            drilldown: {
                categories: ["会员男", "会员女"],
                series: [{
                	data: [customerAnalysisFlow.memberManCount,customerAnalysisFlow.memberWomenCount],
                }],
            }
        }, {
        	y: (customerAnalysisFlow.sankManCount+customerAnalysisFlow.sankWomenCount),
            color: colors[1],
            drilldown: {
                categories: ["散客男",  "散客女"],
                series: [{
                	data: [customerAnalysisFlow.sankManCount,customerAnalysisFlow.sankWomenCount],
                }],
            }
        }];

        // Build the data arrays
        var browserData = [];
        var versionsData = [], x=0;
        var sex = ["男","女"];
        var mscategories = ["会员","散客"];
        // 会员/散客
        for (var i = 0; i < data.length; i++) {
        	var num = 0;
            browserData.push({
                name: mscategories[i],
                categories: mscategories[i],
                
                y: data[i].y,
                color: data[i].color,
                id: mscategories[i],
                dataLabels: {
                    enabled: true,
                    /* format: '{point.name}', */
                    formatter: function() {
                    	return  (this.percentage).toFixed(2)+ "%";
                    }
                }
            });
	        // 男女
	        for (var j = 0; j < data[i].drilldown.series[0].data.length; j++) {
	            var brightness = 0.2 - (j / data[i].drilldown.series[0].data.length) / 5 ;
	            versionsData.push({
	                name: data[i].drilldown.categories[j],
	                y: data[i].drilldown.series[0].data[j],
	                color: colors[x+2],
	            	id:  mscategories[i] + sex[j],
		           	dataLabels: {
		                    enabled: true,
		                    formatter: function() {
		                    	num ++;
		                    	return mnv[num-1] + "%";
		                    }
		                }
		            });
	            x++;
	        }
    }

        $('#client-chart').highcharts({
            chart: {
                type: 'pie',
                backgroundColor: '#0a1718'
            },
            credits: {
                enabled: false,
            },
            legend: {
                backgroundColor: 'rgba(0,0,0,0)',
                itemDistance: 40,
                itemMarginBottom: 10,
                itemWidth: 115,
                itemStyle: {
                    color: '#a2abb5',
                },
                labelFormatter: function () {
                    return this.id ;
                }
                
            },
            title: {
                text: ''
            },
            yAxis: {
                title: {
                    text: ''
                },
                labels: {
                    style: {
                        color: '#a2abb5'
                    }
                }

            },
            plotOptions: {
                pie: {
                    shadow: false,
                    center: ['50%', '50%'],
                    showInLegend: true,
                    tooltip: {
                    	useHTML: true,
                    	headerFormat: '<span style="font-size: 10px">{point.key}:{point.y}</span><br/>',
                    	 /* pointFormat: '占比: <b>{point.name}</b>' */
                    	 pointFormatter: function(){
                    		return "占比:"+(this.percentage).toFixed(2) + "%";
                    	}  
                    },
                }
            },
            
            series: [{
                name: ["会员nn", "散客nn"],
                data: browserData,
                categories: ["会员ss"],
                size: '40%',
                dataLabels: {
                    distance: -30,
                    color: 'white',
                    style: {
                        textShadow: "none",
                        fontSize: 16
                    }
                }
            }, {
                name: '男女',
                data: versionsData,
                categories: ["散客ss"],
                size: '80%',
                innerSize: '70%',
                dataLabels: {
                    distance: 0,
                    color: 'white',
                    style: {
                        textShadow: "none",
                        fontSize: 16
                    }
                }
            }]
        });
	}
	/**今日流量统计*/
	function initCustomerFlux(){
		$('#client-chart1').highcharts({
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
                }
            },
            xAxis: {
                categories: ['11点前', '12点', '13点', '14点', '15点', '16点', '17点', '18点', '19点', '20点', '21点', '22点后'],
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
                    text: '(项目)',
                    align: 'high',
                    rotation: 0,
                    x:40,
                    style: {
                        color: '#a2abb5',
                        fontSize: 9,
                    }
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
                    return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'人';
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
                    enableMouseTracking: true
                }
            },
            series: [{
                name: '总客量',
                datalabel: {
                    shadow: false,
                },
                data: [customerAnalysisFlux.melevenBefore+customerAnalysisFlux.selevenBefore, customerAnalysisFlux.mtwelve+customerAnalysisFlux.stwelve, customerAnalysisFlux.mone+customerAnalysisFlux.sone, customerAnalysisFlux.mtwo+customerAnalysisFlux.stwo, customerAnalysisFlux.mthree+customerAnalysisFlux.sthree, customerAnalysisFlux.mfore+customerAnalysisFlux.sfore, 
                       customerAnalysisFlux.mfive+customerAnalysisFlux.sfive, customerAnalysisFlux.msix+customerAnalysisFlux.ssix, customerAnalysisFlux.mseven+customerAnalysisFlux.sseven, customerAnalysisFlux.meight+customerAnalysisFlux.seight, customerAnalysisFlux.mnine+customerAnalysisFlux.snine, customerAnalysisFlux.mten+customerAnalysisFlux.sten]
            }, {
                name: '会员',
                datalabel: {
                    shadow: false,
                },
                data: [customerAnalysisFlux.melevenBefore, customerAnalysisFlux.mtwelve, customerAnalysisFlux.mone, customerAnalysisFlux.mtwo, customerAnalysisFlux.mthree, customerAnalysisFlux.mfore, customerAnalysisFlux.mfive, customerAnalysisFlux.msix, customerAnalysisFlux.mseven, customerAnalysisFlux.meight, customerAnalysisFlux.mnine, customerAnalysisFlux.mten]
            }, {
                name: '散客',
                datalabel: {
                    shadow: false,
                },
                data: [customerAnalysisFlux.selevenBefore, customerAnalysisFlux.stwelve, customerAnalysisFlux.sone, customerAnalysisFlux.stwo, customerAnalysisFlux.sthree, customerAnalysisFlux.sfore, customerAnalysisFlux.sfive, customerAnalysisFlux.ssix, customerAnalysisFlux.sseven, customerAnalysisFlux.seight, customerAnalysisFlux.snine, customerAnalysisFlux.sten]
            }]
        });
	}
	var curMonthDays = 7; //new Date(new Date().getFullYear(), (new Date().getMonth()+1), 0).getDate();
	var flat = "日";
	var catagory = new Array();
	for(var i = 0; i < 7; i++){
		var value =   7 - i - 1;
		if(value == 0) {
			catagory[i] = "今日";
		}
		else {
			catagory[i] = "前" + value +"天";
		}
		
	};
	//客流量分析(七天流量趋势图)
	function initCustomerFlol(){
		$('#client-chart4').highcharts({
	        chart: {
	            backgroundColor: '#0a1718',
	            type: 'areaspline',
	            inverted: false
	        },
	        credits: {
	            enabled: false,
	        },
	        title: {
	            text: ''
	        },
	        subtitle: {
	            enable: false
	        },
	        xAxis: {
	            reversed: false,
	            title: {
	                enabled: false,
	                text: 's'
	            },
	            categories: catagory,
	            maxPadding: 0.05,
	            showLastLabel: true,
	            tickColor: '#1d2727',
	            tickWidth: 10,
	            tickPosition: 'inside',
	            lineColor: '#383943',
	            allowDecimals: false,
	            style: {
	                color: '#a2abb5',
	            }
	        },
	        yAxis: {
	            title: {
	                text: ''
	            },
	            gridLineColor: '#383943',
	            style: {
	                color: '#a2abb5',
	            }
	        },
	        legend: {
	            enabled: false
	        },
	        tooltip: {
	            headerFormat: '<b>{point.x}客量</b><br/>',
	            pointFormat: '{point.y} (人)'
	        },
	        plotOptions: {
	            series: {
	                color: 'rgba(17,205,200,.5)',
	                fillColor: {
	                    linearGradient: [0, 0, 0, 300],
	                    stops: [
	                        [0, Highcharts.Color(Highcharts.getOptions().colors[9]).setOpacity(0.4).get('rgba')],
	                        [1, Highcharts.Color(Highcharts.getOptions().colors[9]).setOpacity(0).get('rgba')]
	                    ]
	                },
	                marker: {
	                    fillColor: 'rgba(17,205,200,.1)',
	                    radius: 0.1
	                }
	            }
	        },
	        series: [{
	            name: '',
	            data: customerAnalysisFlol
	        }]
	    }, function(chart) {
	        var group = document.querySelectorAll("#container2 .highcharts-series path");
	        for(var i = 0; i< group.length; i++ ) {
	            group[i].setAttribute('width', 1);
	        };
	    });
	}
	//项目消费趋势图
	function initCustomerFlolProject(){
		$('#client-chart5').highcharts({
	        chart: {
	            backgroundColor: '#0a1718',
	            type: 'areaspline',
	            inverted: false
	        },
	        credits: {
	            enabled: false,
	        },
	        title: {
	            text: ''
	        },
	        subtitle: {
	            enable: false
	        },
	        xAxis: {
	            reversed: false,
	            title: {
	                enabled: false,
	                text: ''
	            },
	            labels: {
	                formatter: function() {
	                	if(this.value == 0) return '今'+flat;
	                    return '前'+(this.value+1) + flat;
	                }
	            },
	            maxPadding: 0.05,
	            showLastLabel: true,
	            tickColor: '#1d2727',
	            tickWidth: 10,
	            tickPosition: 'inside',
	            lineColor: '#383943',
	            allowDecimals: false,
	            style: {
	                color: '#a2abb5',
	            }
	        },
	        yAxis: {
	            title: {
	                text: ''
	            },
	            gridLineColor: '#383943',
	            style: {
	                color: '#a2abb5',
	            }
	        },
	        legend: {
	            enabled: false
	        },
	        tooltip: {
	            headerFormat: '<b>项目消费总金额</b><br/>',
	            //pointFormat: '{point.y} (¥)'
	        },
	        plotOptions: {
	            series: {
	                color: 'rgba(17,205,200,.5)',
	                fillColor: {
	                    linearGradient: [0, 0, 0, 300],
	                    stops: [
	                        [0, Highcharts.Color(Highcharts.getOptions().colors[9]).setOpacity(0.4).get('rgba')],
	                        [1, Highcharts.Color(Highcharts.getOptions().colors[9]).setOpacity(0).get('rgba')]
	                    ]
	                },
	                marker: {
	                    fillColor: 'rgba(17,205,200,.1)',
	                    radius: 0.1
	                }
	            }
	        },
	        series: [{
	            name: '',
	            data: customerAnalysisProject
	        }]
	    });
	}
	
	function projectKdj(){
		//项目消费分析
        $('#client-chart2-left').highcharts({
            chart: {
                backgroundColor: '#0a1718',
                type: 'bar'
            },
            colors: ["#5d31dc", '#4fabe6', '#ff7e7d', '#ffc07b', '#b5e986'],
            legend: {
            	enabled:false,
                backgroundColor: '#0a1718',
                itemDistance: 60,
                itemStyle: {
                    color: '#a2abb5',
                    fontWeight: 'bold'
                }
            },
            credits: {
                enabled: false,
            },
            title: {
                text: ''
            },
            tooltip: {
                headerFormat: '<span style="font-size: 14px;color:#000; font-weight:700;"><b>{point.key}</span></b><br/>',
                pointFormat: '{series.name}:{point.y}'
            },
            xAxis: {
                categories: levelName,//['洗剪吹', '单剪', '路新染发', '经典梨子'],
                tickColor: '#1d2727',
                tickWidth: 2,
                lineColor: '#383943',
                distance: -5,
                tickPixelInterval: 30,
                labels: {
                    x: -2,
                    y: 2,
                    style: {
                        color: '#a2abb5',
                        fontSize: 11,

                    }
                },
            },
            yAxis: {
                min: 0,
                tickPixelInterval: 5,
                title: {
                    text: ''
                },
                minPadding: 0.5,
                gridLineColor: '#383943',
                labels: {
                    style: {
                        color: '#a2abb5',
                        fontSize: 11,
                    }
                }
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                },
                series: {
                    pointWidth: 21,
                    stickyTracking: false,
                    borderWidth: 0,
                    colorByPoint: true,
                }
            },
            series: [{
                name: '客单价',
                pointWidth: 10,
                data: customerAnalysisKdj/* //[6352, 3244, 5244, 500, 1000] */
            }]
        });
	}
	
	function projectKbt(){
		$('#client-chart2-right').highcharts({
            chart: {
                backgroundColor: '#0a1718',
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false,
            },
            credits: {
                enabled: false,
            },
            legend: {
            	enabled: true,
                backgroundColor: 'rgba(0,0,0,0)',
                itemDistance: 40,
                itemMarginBottom: 10,
                itemWidth: 115,
                itemStyle: {
                    color: '#a2abb5',
                }
            },
            colors: ['#ff7e7d', '#4fabe6', '#5d31dc', '#e5be2e', '#b5e986', '#ffc07b'],
            title: {
                align: 'center',
                text:  '<div style="text-align: center"><span style="font-size: 20px;color: #b8c2cc">'+ amount + '</span><br><span style="font-size: 14px;color: #7f868d">业绩总值</span></div>',
                verticalAlign: 'middle',
                y: -50,
                x: -3,
                style: {
                    fontWeight: 'bold',
                    color: '#ffffff'
                }
            },
            tooltip: {
                useHTML: true,
                headerFormat: '<div style="font-size: 14px;color:#000;font-weight:700;">{point.key}</div>',
                pointFormat: '贡献业绩:{point.y}<br/>{series.name}:{point.percentage:.1f}%'
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
                    center: ['50%', '50%'],
                    borderWidth: 0,
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: '贡献占比',
                size: '120%',
                innerSize: '65%',
                data: kbk
            }]
        });
	}
    $(function () {
//      客情分析
//      客情分析初始化数据
		initCustomerFlow();
//      今日流量分析
        initCustomerFlux();
//      客流量分析
        initCustomerFlol();
//      项目分析左侧       
        projectKdj();
//      项目分析右侧        
        projectKbt();
//      项目消费趋势        
        initCustomerFlolProject();
    });
</script>
</div>
</body>
<script>
    $(document).ready(function() {
        $(".loading").hide();
        $(".content").fadeIn();
    });
    
    function changeSerchType(type, obj){
    	jQuery(obj).parent().find("li").removeClass("active");
    	jQuery(obj).addClass("active");
    	if(type=='year'){
    		flat = "月";
    		catagory = new Array();
    		for(var i = 0; i < 12; i++){
    			var value =   12 - i - 1;
    			if(value == 0) {
    				catagory[i] = "本月";
    			}
    			else {
    				catagory[i] = "前" + value +"月";
    			}
    			
    		};
    		jQuery(".qtllfx").text("一年客量趋势图");
 			jQuery(".jrllfx").text("今年时段客量分析");
   		}else if(type=='month'){
 			flat = "日";
 			catagory = new Array();
    		for(var i = 0; i < 30; i++){
    			var value =   30 - i - 1;
    			if(value == 0) {
    				catagory[i] = "今日";
    			}
    			else {
    				catagory[i] = "前" + value +"天";
    			}
    			
    		};
 			jQuery(".qtllfx").text("一个月客量趋势图");
 			jQuery(".jrllfx").text("本月时段客量分析");
		}else if(type=='day'){
 			flat = "日";
 			catagory = new Array();
    		for(var i = 0; i < 7; i++){
    			var value =   7 - i - 1;
    			if(value == 0) {
    				catagory[i] = "今日";
    			}
    			else {
    				catagory[i] = "前" + value +"天";
    			}
    			
    		};
 			jQuery(".qtllfx").text("七天客量趋势图");
 			jQuery(".jrllfx").text("今日时段客量分析");
		}
    	jQuery.ajax({
    		type: "POST",
    		url: baseUrl+"boss/view/serch/coutomer",
            data: "storeId="+storeId+"&serchType="+type,
            dataType: "json",
            success: function(data) {
            	customerAnalysisFlow = data.msg.customerAnalysisFlow;
            	customerAnalysisFlux = data.msg.customerAnalysisFlux;
            	customerAnalysisFlol = data.msg.customerAnalysisFlol;
            	customerAnalysisKdj = data.msg.customerAnalysisKdj;
            	customerAnalysisKbt = data.msg.customerAnalysisKbt;
            	customerAnalysisProject = data.msg.customerAnalysisProject;
            	
            	$(".c1").empty();
            	$(".c4").empty();
            	$(".c5").empty();
            	$(".c4").append($('<div id="client-chart4" style="width:100%;height:400px;"></div>'));
            	$(".c5").append($('<div id="client-chart5" style="width:100%;height:400px;"></div>'));
            	$(".c1").append($('<div id="client-chart1" style="width:100%;height:320px;"></div>'));
            	
            	levels = data.msg.levels;
            	levelName = data.msg.levelName;
            	kbk = data.msg.kbk;
            	amount = data.msg.amount;
            	initCustomerFlow();
            	initCustomerFlol();
                initCustomerFlux();
                projectKdj();
                projectKbt();
                initCustomerFlolProject();
            }
    	});
    }
    /**js除法,保留两位*/
    function changeTwoDecimal(x, y){
    	if(y==0)return 0;
    	if(x==0)return 0;
    	return (x*100/y).toFixed(2);
    }
</script>
</html>