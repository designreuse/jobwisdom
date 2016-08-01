<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel ="stylesheet" href="<%=bastPath %>css/wages.css" type ="text/css" />
 <script>
  jQuery(function(){
    jQuery('.wages_content:gt(0)').hide();jQuery('.wages_content:gt(0)').hide();
    jQuery('.content_right ul li').click(function(){
      jQuery(this).addClass('active').siblings().removeClass('active');
	  jQuery('.wages_content').eq(jQuery(this).index()).show().siblings('.wages_content').hide();

	})
  })
  
  //图表  
	jQuery(function () {                                                                
		jQuery('#container').highcharts({                                           
			chart: {                                                           
				type: 'bar'                                                    
			},                                                                 
			title: {                                                           
				text: '工资图表（元）'                    
			},                                                                 
			                                                              
			xAxis: {                                                           
				categories: ['小斌', '小明', '小红', '小高', '小张','小贱','小芬', '小高', '小张','小贱','小芬','小芬', '小高', '小张','小贱','小芬'],
				title: {                                                       
					text: null                                                 
				}                                                              
			},                                                                 
			yAxis: {                                                           
				min: 0,                                                        
				                                                            
				labels: {                                                      
					overflow: 'justify'                                        
				}                                                              
			},                                                                 
			tooltip: {                                                         
				valueSuffix: ' 元'                                       
			},                                                                 
			plotOptions: {                                                     
				bar: {                                                         
					dataLabels: {                                              
						enabled: true                                          
					}                                                          
				}                                                              
			},                                                                 
			legend: {                                                          
				layout: 'vertical',                                            
				align: 'right',                                                
				verticalAlign: 'top',                                          
				x: -40,                                                        
				y: 100,                                                        
				floating: true,                                                
				borderWidth: 1,                                                
				backgroundColor: '#FFFFFF',                                    
				shadow: true                                                   
			},                                                                 
			credits: {                                                         
				enabled: false                                                 
			},                                                                 
			series: [{                                                         
				name: '工资',                                             
				data: [10700, 3100, 6350, 2030, 2000, 5000, 12000, 2030, 2000, 5000, 12000, 12000, 2030, 2000, 5000, 12000]                                   
			}]                                                                 
		});                                                                    
	});                                                                                      	
  </script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper"
		style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel"
				style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right clearfix">
				    <ul class="clearfix">
					  <li class="active">工资图表</li>
					  <li>当月工资表</li>
					</ul>
					<div class="wages_content">
				         <div class="wages_content_datail">
							<div class="wages_content_datail_top">
							  <input type="text">
							  <select><option>搜索门店</option></select>
							  <button>查询</button>
							</div>
						 </div>
					   <div id="container" style="min-width:1000px;height:700px;overflow:ovelay" data-highcharts-chart="0"><div class="highcharts-container" id="highcharts-0" style="position: relative; overflow: hidden; width: 1040px; height: 700px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="1040" height="700"><desc>Created with Highcharts 4.1.5</desc><defs><clipPath id="highcharts-1"><rect x="0" y="0" width="583" height="981"></rect></clipPath><linearGradient x1="0" y1="0" x2="0" y2="1" id="highcharts-3"><stop offset="0" stop-color="#FFF" stop-opacity="1"></stop><stop offset="1" stop-color="#ACF" stop-opacity="1"></stop></linearGradient><linearGradient x1="0" y1="0" x2="0" y2="1" id="highcharts-4"><stop offset="0" stop-color="#9BD" stop-opacity="1"></stop><stop offset="1" stop-color="#CDF" stop-opacity="1"></stop></linearGradient></defs><rect x="0" y="0" width="1040" height="700" strokeWidth="0" fill="#FFFFFF" class=" highcharts-background"></rect><g class="highcharts-button" style="cursor:default;" stroke-linecap="round" transform="translate(1006,10)"><title>Chart context menu</title><rect x="0.5" y="0.5" width="24" height="22" strokeWidth="1" fill="white" stroke="none" stroke-width="1" rx="2" ry="2"></rect><path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1"></path><text x="0" zIndex="1" style="color:black;fill:black;" transform="translate(0,12)"></text></g><g class="highcharts-button" style="cursor:default;" stroke-linecap="round" transform="translate(1006,10)"><title>Chart context menu</title><rect x="0.5" y="0.5" width="24" height="22" strokeWidth="1" fill="white" stroke="none" stroke-width="1" rx="2" ry="2"></rect><path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1"></path><text x="0" zIndex="1" style="color:black;fill:black;" transform="translate(0,12)"></text></g><g class="highcharts-grid" zIndex="1"></g><g class="highcharts-grid" zIndex="1"><path fill="none" d="M 48.5 53 L 48.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 123.5 53 L 123.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 199.5 53 L 199.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 274.5 53 L 274.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 350.5 53 L 350.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 425.5 53 L 425.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 501.5 53 L 501.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 576.5 53 L 576.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 652.5 53 L 652.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 727.5 53 L 727.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 803.5 53 L 803.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 878.5 53 L 878.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 954.5 53 L 954.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 1030.5 53 L 1030.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path></g><g class="highcharts-axis" zIndex="2"><path fill="none" d="M 49 89.5 L 39 89.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 126.5 L 39 126.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 162.5 L 39 162.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 199.5 L 39 199.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 235.5 L 39 235.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 272.5 L 39 272.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 308.5 L 39 308.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 345.5 L 39 345.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 381.5 L 39 381.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 417.5 L 39 417.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 454.5 L 39 454.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 490.5 L 39 490.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 527.5 L 39 527.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 563.5 L 39 563.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 600.5 L 39 600.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 636.5 L 39 636.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 52.5 L 39 52.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 48.5 53 L 48.5 636" stroke="#C0D0E0" stroke-width="1" zIndex="7" visibility="visible"></path></g><g class="highcharts-axis" zIndex="2"><text x="539.5" zIndex="7" text-anchor="middle" transform="translate(0,0)" class=" highcharts-yaxis-title" style="color:#707070;fill:#707070;" visibility="visible" y="679">Values</text></g><g class="highcharts-series-group" zIndex="3"><g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(1030,636) rotate(90) scale(-1,1) scale(1 1)" style="" width="981" height="583" clip-path="url(#highcharts-1)"><rect x="555.5" y="174.5" width="18" height="807" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="519.5" y="747.5" width="18" height="234" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="482.5" y="502.5" width="18" height="479" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="446.5" y="828.5" width="18" height="153" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="409.5" y="830.5" width="18" height="151" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="373.5" y="604.5" width="18" height="377" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="336.5" y="75.5" width="18" height="906" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="300.5" y="828.5" width="18" height="153" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="264.5" y="830.5" width="18" height="151" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="227.5" y="604.5" width="18" height="377" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="191.5" y="75.5" width="18" height="906" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="154.5" y="75.5" width="18" height="906" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="118.5" y="828.5" width="18" height="153" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="81.5" y="830.5" width="18" height="151" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="45.5" y="604.5" width="18" height="377" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="8.5" y="75.5" width="18" height="906" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect></g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(1030,636) rotate(90) scale(-1,1) scale(1 1)" width="981" height="583" clip-path="none"></g></g><text x="520" text-anchor="middle" class="highcharts-title" zIndex="4" style="color:#333333;font-size:18px;fill:#333333;width:976px;" y="24">工资图表（元）</text><g class="highcharts-data-labels highcharts-tracker" visibility="visible" zIndex="6" transform="translate(49,53) scale(1 1)" opacity="1" style=""><g zIndex="1" style="" transform="translate(807,5)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>10 700</tspan></text></g><g zIndex="1" style="" transform="translate(234,41)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>3 100</tspan></text></g><g zIndex="1" style="" transform="translate(479,78)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>6 350</tspan></text></g><g zIndex="1" style="" transform="translate(153,114)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 030</tspan></text></g><g zIndex="1" style="" transform="translate(151,151)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 000</tspan></text></g><g zIndex="1" style="" transform="translate(377,187)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>5 000</tspan></text></g><g zIndex="1" style="" transform="translate(906,224)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>12 000</tspan></text></g><g zIndex="1" style="" transform="translate(153,260)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 030</tspan></text></g><g zIndex="1" style="" transform="translate(151,296)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 000</tspan></text></g><g zIndex="1" style="" transform="translate(377,333)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>5 000</tspan></text></g><g zIndex="1" style="" transform="translate(906,369)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>12 000</tspan></text></g><g zIndex="1" style="" transform="translate(906,406)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>12 000</tspan></text></g><g zIndex="1" style="" transform="translate(153,442)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 030</tspan></text></g><g zIndex="1" style="" transform="translate(151,479)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 000</tspan></text></g><g zIndex="1" style="" transform="translate(377,515)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>5 000</tspan></text></g><g zIndex="1" style="" transform="translate(906,552)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>12 000</tspan></text></g></g><g class="highcharts-legend" zIndex="7" transform="translate(929,110)"><rect x="0.5" y="0.5" width="60" height="28" strokeWidth="1" stroke="black" stroke-width="5" fill="none" isShadow="true" stroke-opacity="0.049999999999999996" transform="translate(1, 1)" visibility="visible"></rect><rect x="0.5" y="0.5" width="60" height="28" strokeWidth="1" stroke="black" stroke-width="3" fill="none" isShadow="true" stroke-opacity="0.09999999999999999" transform="translate(1, 1)" visibility="visible"></rect><rect x="0.5" y="0.5" width="60" height="28" strokeWidth="1" stroke="black" stroke-width="1" fill="none" isShadow="true" stroke-opacity="0.15" transform="translate(1, 1)" visibility="visible"></rect><rect x="0.5" y="0.5" width="60" height="28" strokeWidth="1" stroke="#909090" stroke-width="1" fill="#FFFFFF" visibility="visible"></rect><g zIndex="1"><g><g class="highcharts-legend-item" zIndex="1" transform="translate(8,3)"><text x="21" style="color:#333333;font-size:12px;font-weight:bold;cursor:pointer;fill:#333333;" text-anchor="start" zIndex="2" y="15">工资</text><rect x="0" y="4" width="16" height="12" zIndex="3" fill="#7cb5ec"></rect></g></g></g></g><g class="highcharts-axis-labels highcharts-xaxis-labels" zIndex="7"><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="74" opacity="1">小斌</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="111" opacity="1">小明</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="147" opacity="1">小红</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="184" opacity="1">小高</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="220" opacity="1">小张</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="256" opacity="1">小贱</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="293" opacity="1">小芬</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="329" opacity="1">小高</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="366" opacity="1">小张</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="402" opacity="1">小贱</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="439" opacity="1">小芬</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="475" opacity="1">小芬</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="511" opacity="1">小高</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="548" opacity="1">小张</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="584" opacity="1">小贱</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:333px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="621" opacity="1">小芬</text></g><g class="highcharts-axis-labels highcharts-yaxis-labels" zIndex="7"><text x="49" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">0k</text><text x="124.46153846153845" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">1k</text><text x="199.9230769230769" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">2k</text><text x="275.38461538461536" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">3k</text><text x="350.8461538461538" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">4k</text><text x="426.30769230769226" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">5k</text><text x="501.7692307692307" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">6k</text><text x="577.2307692307692" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">7k</text><text x="652.6923076923076" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">8k</text><text x="728.1538461538461" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">9k</text><text x="803.6153846153845" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">10k</text><text x="879.076923076923" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">11k</text><text x="954.5384615384614" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">12k</text><text x="1030" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="end" transform="translate(0,0)" y="656" opacity="1">13k</text></g><g class="highcharts-tooltip" zIndex="8" style="cursor:default;padding:0;white-space:nowrap;" transform="translate(862,-9999)" opacity="0" visibility="visible"><path fill="none" d="M 3.5 0.5 L 117.5 0.5 C 120.5 0.5 120.5 0.5 120.5 3.5 L 120.5 46.5 C 120.5 49.5 120.5 49.5 117.5 49.5 L 3.5 49.5 C 0.5 49.5 0.5 49.5 0.5 46.5 L 0.5 30 -5.5 24 0.5 18 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" isShadow="true" stroke="black" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)" width="120" height="49"></path><path fill="none" d="M 3.5 0.5 L 117.5 0.5 C 120.5 0.5 120.5 0.5 120.5 3.5 L 120.5 46.5 C 120.5 49.5 120.5 49.5 117.5 49.5 L 3.5 49.5 C 0.5 49.5 0.5 49.5 0.5 46.5 L 0.5 30 -5.5 24 0.5 18 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" isShadow="true" stroke="black" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)" width="120" height="49"></path><path fill="none" d="M 3.5 0.5 L 117.5 0.5 C 120.5 0.5 120.5 0.5 120.5 3.5 L 120.5 46.5 C 120.5 49.5 120.5 49.5 117.5 49.5 L 3.5 49.5 C 0.5 49.5 0.5 49.5 0.5 46.5 L 0.5 30 -5.5 24 0.5 18 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" isShadow="true" stroke="black" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)" width="120" height="49"></path><path fill="rgba(249, 249, 249, .85)" d="M 3.5 0.5 L 117.5 0.5 C 120.5 0.5 120.5 0.5 120.5 3.5 L 120.5 46.5 C 120.5 49.5 120.5 49.5 117.5 49.5 L 3.5 49.5 C 0.5 49.5 0.5 49.5 0.5 46.5 L 0.5 30 -5.5 24 0.5 18 0.5 3.5 C 0.5 0.5 0.5 0.5 3.5 0.5" stroke="#7cb5ec" stroke-width="1"></path><text x="8" zIndex="1" style="font-size:12px;color:#333333;fill:#333333;" transform="translate(0,20)"><tspan style="font-size: 10px">小斌</tspan><tspan style="fill:#7cb5ec" x="8" dy="15">●</tspan><tspan dx="0"> 工资: </tspan><tspan style="font-weight:bold" dx="0">10 700 元</tspan></text></g></svg><div class="highcharts-contextmenu" style="position: absolute; z-index: 1000; padding: 24px; display: none; right: -14px; top: 8px;"><div style="box-shadow: rgb(136, 136, 136) 3px 3px 10px; border: 1px solid rgb(160, 160, 160); padding: 5px 0px; background: rgb(255, 255, 255);"><div style="cursor: pointer; padding: 0px 10px; color: rgb(48, 48, 48); font-size: 11px; background: none;">打印图表</div><hr><div style="cursor: pointer; padding: 0px 10px; color: rgb(48, 48, 48); font-size: 11px; background: none;">下载 PNG 图片</div><div style="cursor: pointer; padding: 0px 10px; color: rgb(48, 48, 48); font-size: 11px; background: none;">下载 JPEG 图片</div><div style="cursor: pointer; padding: 0px 10px; color: rgb(48, 48, 48); font-size: 11px; background: none;">下载 PDF 文档</div><div style="cursor: pointer; padding: 0px 10px; color: rgb(48, 48, 48); font-size: 11px; background: none;">下载 SVG 矢量图</div></div></div></div></div>  
					</div>
					
					<div class="wages_content" style="display: none;">
					  <div class="wages_content_datail">
							<div class="wages_content_datail_top">
							  <input type="text" placeholder="2016-02">
							  <input type="text" placeholder="员工姓名/工号" style="width:116px">
							  <select>
							     <option>搜索门店</option>
							  </select>
							  <button>搜索</button>
							  <button style="width:130px">新增考勤</button>
							</div>
					  </div>
					 <div class="wages_content_datail_table">
					  <div class="wages_content_datail_table_">
					   <table>
					     <tbody><tr>
						   <td colspan="2">员工信息</td>
						   <td rowspan="2">基本工资</td>
						   <td colspan="2">劳动业绩提成</td>
						   <td colspan="2">商品销售提成</td>
						   <td colspan="2">疗程销售提成</td>
						   <td colspan="2">开充卡提成</td>
						   <td rowspan="2">考勤奖惩</td>
						   <td rowspan="2">实际发放</td>
						 </tr>
					     <tr>
						   <td>工号</td>
						   <td>姓名</td>
						   <td>提成</td>
						   <td>业绩</td>
						   <td>提成</td>
						   <td>业绩</td>
						   <td>提成</td>
						   <td>业绩</td>
						   <td>提成</td>
						   <td>业绩</td>
						 </tr>
						 <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						  <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						   <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						   <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						   <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						   <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						   <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
						   <tr>
						   <td>1201</td>
						   <td>尼泊尔男爵</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						   <td>1000</td>
						 </tr>
					   </tbody></table>
					  </div>
					 </div>
					</div>
				  </div>
			</div>
		</div>
	</div>
</body>
<script>
	//dialog('msg');
</script>
</html>