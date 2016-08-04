<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel ="stylesheet" href="<%=basePath %>css/wages.css" type ="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<script>
  jQuery(function(){
    jQuery('.wages_content:gt(0)').hide();
    jQuery('.content_right ul li').click(function(){
      jQuery(this).addClass('active').siblings().removeClass('active');
	  jQuery('.wages_content').eq(jQuery(this).index()).show().siblings('.wages_content').hide();

	})
  })
  
                                                                                  	
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
			  <input name="time1" type="text"  placeholder="2016-02" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
			  <select name="store1">
			   <option value= "0">全部</option>
			   <c:forEach items="${selectByStoreAccount }" var="store">
			 <option value= "${store.storeId }">${store.storeName }</option>
			   </c:forEach></select>
			  <button onclick="checkWages(1)">查询</button>
			</div>
		 </div>
	   <div id="container" style="min-width:1000px;height:700px;overflow:ovelay" data-highcharts-chart="0"><div class="highcharts-container" id="highcharts-0" style="position: relative; overflow: hidden; width: 1000px; height: 700px; text-align: left; line-height: normal; z-index: 0; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);"><svg version="1.1" style="font-family:&quot;Lucida Grande&quot;, &quot;Lucida Sans Unicode&quot;, Arial, Helvetica, sans-serif;font-size:12px;" xmlns="http://www.w3.org/2000/svg" width="1000" height="700"><desc>Created with Highcharts 4.1.5</desc><defs><clipPath id="highcharts-1"><rect x="0" y="0" width="583" height="941"></rect></clipPath></defs><rect x="0" y="0" width="1000" height="700" strokeWidth="0" fill="#FFFFFF" class=" highcharts-background"></rect><g class="highcharts-button" style="cursor:default;" stroke-linecap="round" transform="translate(966,10)"><title>Chart context menu</title><rect x="0.5" y="0.5" width="24" height="22" strokeWidth="1" fill="white" stroke="none" stroke-width="1" rx="2" ry="2"></rect><path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1"></path><text x="0" zIndex="1" style="color:black;fill:black;" transform="translate(0,12)"></text></g><g class="highcharts-button" style="cursor:default;" stroke-linecap="round" transform="translate(966,10)"><title>Chart context menu</title><rect x="0.5" y="0.5" width="24" height="22" strokeWidth="1" fill="white" stroke="none" stroke-width="1" rx="2" ry="2"></rect><path fill="#E0E0E0" d="M 6 6.5 L 20 6.5 M 6 11.5 L 20 11.5 M 6 16.5 L 20 16.5" stroke="#666" stroke-width="3" zIndex="1"></path><text x="0" zIndex="1" style="color:black;fill:black;" transform="translate(0,12)"></text></g><g class="highcharts-grid" zIndex="1"></g><g class="highcharts-grid" zIndex="1"><path fill="none" d="M 48.5 53 L 48.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 120.5 53 L 120.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 193.5 53 L 193.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 265.5 53 L 265.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 338.5 53 L 338.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 410.5 53 L 410.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 482.5 53 L 482.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 555.5 53 L 555.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 627.5 53 L 627.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 699.5 53 L 699.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 772.5 53 L 772.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 844.5 53 L 844.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 917.5 53 L 917.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path><path fill="none" d="M 990.5 53 L 990.5 636" stroke="#D8D8D8" stroke-width="1" zIndex="1" opacity="1"></path></g><g class="highcharts-axis" zIndex="2"><path fill="none" d="M 49 89.5 L 39 89.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 126.5 L 39 126.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 162.5 L 39 162.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 199.5 L 39 199.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 235.5 L 39 235.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 272.5 L 39 272.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 308.5 L 39 308.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 345.5 L 39 345.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 381.5 L 39 381.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 417.5 L 39 417.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 454.5 L 39 454.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 490.5 L 39 490.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 527.5 L 39 527.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 563.5 L 39 563.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 600.5 L 39 600.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 636.5 L 39 636.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 49 52.5 L 39 52.5" stroke="#C0D0E0" stroke-width="1" opacity="1"></path><path fill="none" d="M 48.5 53 L 48.5 636" stroke="#C0D0E0" stroke-width="1" zIndex="7" visibility="visible"></path></g><g class="highcharts-axis" zIndex="2"><text x="519.5" zIndex="7" text-anchor="middle" transform="translate(0,0)" class=" highcharts-yaxis-title" style="color:#707070;fill:#707070;" visibility="visible" y="679">Values</text></g><g class="highcharts-series-group" zIndex="3"><g class="highcharts-series highcharts-tracker" visibility="visible" zIndex="0.1" transform="translate(990,636) rotate(90) scale(-1,1) scale(1 1)" style="" width="941" height="583" clip-path="url(#highcharts-1)"><rect x="555.5" y="166.5" width="18" height="775" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="519.5" y="717.5" width="18" height="224" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="482.5" y="481.5" width="18" height="460" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="446.5" y="794.5" width="18" height="147" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="409.5" y="796.5" width="18" height="145" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="373.5" y="579.5" width="18" height="362" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="336.5" y="72.5" width="18" height="869" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="300.5" y="794.5" width="18" height="147" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="264.5" y="796.5" width="18" height="145" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="227.5" y="579.5" width="18" height="362" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="191.5" y="72.5" width="18" height="869" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="154.5" y="72.5" width="18" height="869" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="118.5" y="794.5" width="18" height="147" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="81.5" y="796.5" width="18" height="145" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="45.5" y="579.5" width="18" height="362" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect><rect x="8.5" y="72.5" width="18" height="869" stroke="#FFFFFF" stroke-width="1" fill="#7cb5ec" rx="0" ry="0"></rect></g><g class="highcharts-markers" visibility="visible" zIndex="0.1" transform="translate(990,636) rotate(90) scale(-1,1) scale(1 1)" width="941" height="583" clip-path="none"></g></g><text x="500" text-anchor="middle" class="highcharts-title" zIndex="4" style="color:#333333;font-size:18px;fill:#333333;width:936px;" y="24">工资图表（元）</text><g class="highcharts-data-labels highcharts-tracker" visibility="visible" zIndex="6" transform="translate(49,53) scale(1 1)" opacity="1" style=""><g zIndex="1" style="" transform="translate(775,5)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>10 700</tspan></text></g><g zIndex="1" style="" transform="translate(224,41)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>3 100</tspan></text></g><g zIndex="1" style="" transform="translate(460,78)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>6 350</tspan></text></g><g zIndex="1" style="" transform="translate(147,114)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 030</tspan></text></g><g zIndex="1" style="" transform="translate(145,151)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 000</tspan></text></g><g zIndex="1" style="" transform="translate(362,187)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>5 000</tspan></text></g><g zIndex="1" style="" transform="translate(869,224)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>12 000</tspan></text></g><g zIndex="1" style="" transform="translate(147,260)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 030</tspan></text></g><g zIndex="1" style="" transform="translate(145,296)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 000</tspan></text></g><g zIndex="1" style="" transform="translate(362,333)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>5 000</tspan></text></g><g zIndex="1" style="" transform="translate(869,369)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>12 000</tspan></text></g><g zIndex="1" style="" transform="translate(869,406)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>12 000</tspan></text></g><g zIndex="1" style="" transform="translate(147,442)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 030</tspan></text></g><g zIndex="1" style="" transform="translate(145,479)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>2 000</tspan></text></g><g zIndex="1" style="" transform="translate(362,515)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>5 000</tspan></text></g><g zIndex="1" style="" transform="translate(869,552)"><text x="5" zIndex="1" style="font-size: 11px; font-weight: bold; color: rgb(0, 0, 0); fill: rgb(0, 0, 0); text-shadow: rgb(255, 255, 255) 0px 0px 6px, rgb(255, 255, 255) 0px 0px 3px;" transform="translate(0,17)"><tspan>12 000</tspan></text></g></g><g class="highcharts-legend" zIndex="7" transform="translate(889,110)"><rect x="0.5" y="0.5" width="60" height="28" strokeWidth="1" stroke="black" stroke-width="5" fill="none" isShadow="true" stroke-opacity="0.049999999999999996" transform="translate(1, 1)" visibility="visible"></rect><rect x="0.5" y="0.5" width="60" height="28" strokeWidth="1" stroke="black" stroke-width="3" fill="none" isShadow="true" stroke-opacity="0.09999999999999999" transform="translate(1, 1)" visibility="visible"></rect><rect x="0.5" y="0.5" width="60" height="28" strokeWidth="1" stroke="black" stroke-width="1" fill="none" isShadow="true" stroke-opacity="0.15" transform="translate(1, 1)" visibility="visible"></rect><rect x="0.5" y="0.5" width="60" height="28" strokeWidth="1" stroke="#909090" stroke-width="1" fill="#FFFFFF" visibility="visible"></rect><g zIndex="1"><g><g class="highcharts-legend-item" zIndex="1" transform="translate(8,3)"><text x="21" style="color:#333333;font-size:12px;font-weight:bold;cursor:pointer;fill:#333333;" text-anchor="start" zIndex="2" y="15">工资</text><rect x="0" y="4" width="16" height="12" zIndex="3" fill="#7cb5ec"></rect></g></g></g></g><g class="highcharts-axis-labels highcharts-xaxis-labels" zIndex="7"><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="74" opacity="1">小斌</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="111" opacity="1">小明</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="147" opacity="1">小红</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="184" opacity="1">小高</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="220" opacity="1">小张</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="256" opacity="1">小贱</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="293" opacity="1">小芬</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="329" opacity="1">小高</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="366" opacity="1">小张</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="402" opacity="1">小贱</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="439" opacity="1">小芬</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="475" opacity="1">小芬</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="511" opacity="1">小高</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="548" opacity="1">小张</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="584" opacity="1">小贱</text><text x="34" style="color:#606060;cursor:default;font-size:11px;fill:#606060;width:320px;text-overflow:clip;" text-anchor="end" transform="translate(0,0)" y="621" opacity="1">小芬</text></g><g class="highcharts-axis-labels highcharts-yaxis-labels" zIndex="7"><text x="49" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">0k</text><text x="121.38461538461539" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">1k</text><text x="193.76923076923077" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">2k</text><text x="266.15384615384613" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">3k</text><text x="338.53846153846155" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">4k</text><text x="410.9230769230769" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">5k</text><text x="483.30769230769226" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">6k</text><text x="555.6923076923076" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">7k</text><text x="628.0769230769231" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">8k</text><text x="700.4615384615385" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">9k</text><text x="772.8461538461538" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">10k</text><text x="845.2307692307692" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">11k</text><text x="917.6153846153845" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="middle" transform="translate(0,0)" y="656" opacity="1">12k</text><text x="990" style="color:#606060;cursor:default;font-size:11px;fill:#606060;" text-anchor="end" transform="translate(0,0)" y="656" opacity="1">13k</text></g><g class="highcharts-tooltip" zIndex="8" style="cursor:default;padding:0;white-space:nowrap;" transform="translate(0,-9999)"><path fill="none" d="M 3 0 L 13 0 C 16 0 16 0 16 3 L 16 13 C 16 16 16 16 13 16 L 3 16 C 0 16 0 16 0 13 L 0 3 C 0 0 0 0 3 0" isShadow="true" stroke="black" stroke-opacity="0.049999999999999996" stroke-width="5" transform="translate(1, 1)"></path><path fill="none" d="M 3 0 L 13 0 C 16 0 16 0 16 3 L 16 13 C 16 16 16 16 13 16 L 3 16 C 0 16 0 16 0 13 L 0 3 C 0 0 0 0 3 0" isShadow="true" stroke="black" stroke-opacity="0.09999999999999999" stroke-width="3" transform="translate(1, 1)"></path><path fill="none" d="M 3 0 L 13 0 C 16 0 16 0 16 3 L 16 13 C 16 16 16 16 13 16 L 3 16 C 0 16 0 16 0 13 L 0 3 C 0 0 0 0 3 0" isShadow="true" stroke="black" stroke-opacity="0.15" stroke-width="1" transform="translate(1, 1)"></path><path fill="rgba(249, 249, 249, .85)" d="M 3 0 L 13 0 C 16 0 16 0 16 3 L 16 13 C 16 16 16 16 13 16 L 3 16 C 0 16 0 16 0 13 L 0 3 C 0 0 0 0 3 0"></path><text x="8" zIndex="1" style="font-size:12px;color:#333333;fill:#333333;" transform="translate(0,20)"></text></g></svg></div></div>  
	</div>
	
	<div class="wages_content" style="display: none;">
	  <div class="wages_content_datail">
			<div class="wages_content_datail_top">
			<input name="time2" type="text"  placeholder="2016-02" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" />
			  <input type="text" name="code" placeholder="员工姓名/工号" style="width:116px">
			  <select name="store2">
			  	 <option value= "0">全部</option>
			   	<c:forEach items="${selectByStoreAccount }" var="store">
			 		<option value= "${store.storeId }">${store.storeName }</option>
			    </c:forEach></select>
			  <button onclick="checkWages(2)">搜索</button>
			  <button style="width:130px">新增考勤</button>
			</div>
	  </div>
	 <div class="wages_content_datail_table">
	  <div class="wages_content_datail_table_">
	   <table>
	     <tbody name ="tab">
	     <tr name = "tr">
		   <td>工号</td>
		   <td >姓名</td>
		   <td >基本工资</td>
		   <td  >劳动业绩提成</td>
		   <td >商品销售提成</td>
		   <td >疗程销售提成</td>
		   <td >开充卡提成</td>
		   <td >提成汇总</td>
		   <td >考勤奖惩</td>
		   <td >实际发放</td>
		 </tr>
	   </tbody></table>
	  </div>
	 </div>
	</div>
  </div>

			</div>
		</div>
	</div>
	
	<div class="zzc hide">
  <div class="zzc_wages">
    <p>新增考勤</p>
    <div class="zzc_wages_content">
	  <p>
	   <span><i>员工姓名</i><select><option>黄渤</option></select></span>
	   <span style="margin-left:78px"><i>考勤</i><input type="text"><em>元</em></span>
	  </p>
	  
	  <div class="remarks clearfix">
	    <div class="remarks_left">
		  备注	
		</div>
	    <div class="remarks_right">
		  <textarea></textarea>		  
		</div>
	  </div>
	  
	  <div class="remarks_button">
	    <button>确定</button>
	    <button>取消</button>
	  </div>
	</div>
  </div>
</div>
</body>
<script>
var jsonos = ${jsonos };
var views = ${type };
 jQuery(function(){
	 var json = jsonos.jsona;
	 showTable(json);
	 showViwe(jsonos.jsonn,jsonos.jsont);
	 var myDate = new Date();
	 var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
	 var month = myDate.getMonth();       //获取当前月份(0-11,0代表1月)
	 var year_month = year + "-"+month;
	 jQuery("input[name='time1']").val(year_month);
	 jQuery("input[name='time2']").val(year_month);
	
	 if(views == 1){
		 jQuery("select[name='store1']").attr("class","hide");
		 jQuery("select[name='store2']").attr("class","hide");
	 }
	 jQuery("select option:nth-child(2)").attr("selected" , "selected");  
// 	 jQuery("select[name='store1']").options[1].selected = true;
// 	 jQuery("select[name='store2']").options[1].selected = true;
 });
 function showTable(s){
	 jQuery("tbody[name='tab']").empty();
	 var html  ='<tr ><td>工号</td><td >姓名</td><td >基本工资</td><td>劳动业绩提成</td> <td >商品销售提成</td> <td >疗程销售提成</td> <td >开充卡提成</td>';
	 html += '<td >提成汇总</td>	 <td >考勤奖惩</td>	 <td >实际发放</td>	</tr>';
	   jQuery(s).each(function(t,value){
		   html += '<tr> <td>'+value.code+'</td> <td>'+value.name+'</td><td>'+value.baseSalaries+'</td><td>'+value.ld+'</td><td>'+value.sp+'</td><td>'+value.lc+'</td>';
		   html += '<td>'+value.kk+'</td><td>'+value.tc+'</td><td>考勤奖惩</td><td>'+value.tc+'</td>';
	   })
	
	   jQuery("tbody[name='tab']").append(html);
	 
 }
 
function  checkWages(g){
	var code = jQuery("input[name='code']").val();
	var t = "time2"
	var n = "store2"
	if(g==1){
		t = "time1"
		n = "store1"
		code ="";
	}
   var time =  jQuery("input[name='"+t+"']").val();
   var store =  jQuery("select[name='"+n+"']").val();

   var data = "code="+code+"&time="+time+"&store="+store;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/account/store/selectwages",
		data : data,
		dataType : "json",
		success : function(e){
				 dialog('查询成功');
				 jsonos = e.msg;
				 var json = jsonos.jsona;
				 showTable(json);
				 showViwe(jsonos.jsonn,jsonos.jsont);
			     jQuery("select[name='store1']").val(store);
			     jQuery("select[name='store2']").val(store);
			     jQuery("input[name='time2']").val(time);
			     jQuery("input[name='time1']").val(time);
		}
	});
	
}
 
 function showViwe(name,data){
	 //图表  
			jQuery('#container').highcharts({                                           
				chart: {                                                           
					type: 'bar'                                                    
				},                                                                 
				title: {                                                           
					text: '工资图表（元）'                    
				},                                                                 
				                                                              
				xAxis: {                                                           
					categories: name,
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
					data: data                                   
				}]                                                                 
			});                                                                    
 }

	//dialog('msg');
</script>
</html>