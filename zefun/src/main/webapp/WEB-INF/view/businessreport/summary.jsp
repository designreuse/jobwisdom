<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<link rel="stylesheet" href="<%=basePath %>css/business_collect.css" type="text/css" />
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
 <div class='content_right clearfix'>
    <ul class="clearfix">
	  <li class="active">走势图</li>
	  <li>营业汇总</li> 
	</ul>
	
	<div class="wages_content">
	  <div class="wages_content_datail">
		   <div class="wages_content_datail_top" name = "trendDIV">
			 <span class="active" dateType = "2" onclick="selectFlowDateType(2, this)">月</span>
			 <span dateType = "1" onclick="selectFlowDateType(1, this)">年</span>
			 <input type="text" name="flowTime" style="display: none;" onfocus="WdatePicker({dateFmt:'yyyy'})">
			 <input type="text" name="flowTime" onfocus="WdatePicker({dateFmt:'yyyy-MM'})">
			 <c:if test="${role == 1 }">
			      	门店<select name = "trendStoreId"> 
			      	       <c:forEach items="${storeInfoList}" var="storeInfo">
			      	           <option value="${storeInfo.storeId}">${storeInfo.storeName}</option>
			      	       </c:forEach>
			      	   </select>
			 </c:if>
			 <button onclick="trendPage()">查询</button>
			</div>
	  </div>
	  <div id="container1"  style="min-width:1000px;height:600px;"></div>
	</div>
	<div class="wages_content">
	  <div class="wages_content_datail">
		   <div class="wages_content_datail_top">
			  <span class="active" onclick = "chooseDay(1)">今日</span>
			  <span onclick = "chooseDay(2)">本月</span>
			  <span onclick = "chooseDay(3)">本年</span>
			  <input type="text" id="startDate" onfocus="WdatePicker({dateFmt:'yyyy-mm-dd'})">--
			  <input type="text" id="endDate" onfocus="WdatePicker({dateFmt:'yyyy-mm-dd'})">
			  <c:if test="${role == 1 }">
			      	门店
                    <select name = "businessStoreId"> 
		      	       <c:forEach items="${storeInfoList}" var="storeInfo">
		      	           <option value="${storeInfo.storeId}">${storeInfo.storeName}</option>
		      	       </c:forEach>
		      	    </select>
			  </c:if>
			 <button onclick="totailSelect()">查询</button>
			</div>
	  </div>	
	  <div class="business_canvas clearfix">
	     <div class="business_canvas_left">
		   <div id="container2" style="min-width:450px;height:350px"></div>
		 
		 </div>
	     <div class="business_canvas_right">
		  <table>
		     <tr>
			   <td>实收项目</td>
			   <td>实收金额</td>
			   <td>实收占比</td>
			 </tr>
		  </table>
		  <div class="business_canvas_right_content">
		    <table>
		      
			</table>
		  </div>
		 </div>
	  
	  </div>
	  
	  
	  <div class="total_score">
	     <p>总业绩</p>
	      <div class="total_score_table1">
		    <table>
			  <tr>
			    <td colspan="8">项目业绩</td>
			  </tr>
			  <tr>
			    <td>总业绩</td>
				<td>总个数</td>
				<td>指定业绩</td>
				<td>非指定业绩</td>
				<td>指定个数</td>
				<td>非指定个数</td>
				<td>指定率</td>
			  </tr>
			   <tr name = "projectTR">
			    
			  </tr>
			</table>
		  </div>
		  
		  <div class="total_score_content clearfix">
		    <div class="total_score_left">
			  <div class="total_score_table2">
				<table>
				  <tr>
					<td colspan="7">外卖</td>
				  </tr>
				  <tr>
					<td>总业绩</td>
					<td>总个数</td>
					<td>商城购买业绩</td>
					<td>门店购买业绩</td>
					<td>商城购买个数</td>
					<td>门店购买个数</td>
					<td>商城购买率</td>
				  </tr>
				   <tr name = "goodsTR">
					
				  </tr>
				</table>
			  </div>

	    	  <div class="total_score_table3">
				<table>
				  <tr>
					<td colspan="5">卡项</td>
				  </tr>
				  <tr>
					<td>卡项业绩</td>
					<td>新增卡金</td>
					<td>新增卡金赠送</td>
					<td>卡金消耗</td>
					<td>总变动</td>
				  </tr>
				   <tr name = "cardTR">
					
				  </tr>
				</table>
			  </div>
		  
		       <div class="total_score_table4">
				<table>
				  <tr>
					<td colspan="4">疗程</td>
				  </tr>
				  <tr>
					<td>销售业绩</td>
					<!-- <td>新增疗程金</td>
					<td>消耗疗程卡金</td>
					<td>总变动</td> -->
				  </tr>
				   <tr name = "comboTR">
					<!-- <td>111</td>
					<td>111</td>
					<td>111</td> -->
				  </tr>
				</table>
			  </div>
		  </div>
		  
		  <div class="total_score_right clearfix">
		     <div class="total_score_right_1">
			   <p>卡项</p>
			   <span name = "cardTotailCalculate"></span>
			 </div>
		    <div class="total_score_right_2" name = "totailCalculate">
			   5000.00
			</div>
		    <div class="total_score_right_3">
			   <p>项目业绩</p>
			   <span name = "projectTotailCalculate"></span>
			 </div> 
			 <div class="total_score_right_4">
			   <p>外卖</p>
			   <span name = "goodsTotailCalculate"></span>
			 </div>
			  <div class="total_score_right_5">
			   <p name="comboTotailCalculate"></p>
			   <span>疗程</span>
			 </div>
		  </div>
	  </div>
  </div>
  
  <div class="business_collect_table">
    <table> 
	  <tr>
	     <td colspan="8">客情</td>
	  </tr>
	  <tr>
	     <td>总客次</td>
		 <td>客单价</td>
		 <td>指定客次</td>
		 <td>非指定客次</td>
		 <td>会员客次</td>
		 <td>散客客次</td>
		 <td>男客客次</td>
		 <td>女客客次</td>
	  </tr>
	</table>
    <div class="business_collect_table_1">
	   <table>
	     <tr name = "customerTR">
		   <td></td>
		   <td></td>
		   <td></td>
		   <td></td>
		   <td></td>
		   <td></td>
		   <td></td>
		   <td></td>
		 </tr>
	   </table>
	</div>
   </div>


    <!--<button class="btn" data-target="#jietu" data-toggle="modal">截图</button>-->
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
   <!-- <div class="left-show-btn">
        <span class="iconfont icon-quanbu110"></span>
    </div>-->
    <!-- <a href="" class="showmenu"></a>

    <i class="iconfont icon-fuzhi"></i> -->


    <!--返回顶部-->
<!-- <div id="return-top" class="return-top">
  <span class="iconfont icon-huidaodingbu"></span>
</div> -->

<!--轮播-->


</div><!--mainwrapper-->
<script type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/businessreport/summary.js"></script>
<script type="text/javascript">

var dataMapStr = '${dataMapStr}';
var dataMap = eval("(" + dataMapStr + ")");

var storeId = '${storeId}';
</script>
</body>
</html>
