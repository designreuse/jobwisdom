<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/customer_analyse.css" type="text/css" />
<style>
.grilTable td {height: 45px;}
</style>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			 <div class='content_right clearfix'>
			    <ul class="clearfix">
				  <li class="active">
				     <c:choose>
				        <c:when test="${pageType == 1}">大客户分析</c:when>
				        <c:when test="${pageType == 2}">忠诚客户分析</c:when>
				        <c:otherwise>活跃客户分析</c:otherwise>
				     </c:choose>
				  </li>
				  <li>解决方案</li>
				  <li>图文介绍</li>
				</ul>
				<div class="customer_analyse_content">
				 <div class="customer_analyse_content_">
				  <div class="customer_analyse_content_datail">
				     <div class="customer_analyse_search">
				       <c:if test="${roleId == 1}">
				           <select name = "selectStore" onchange="chooseStoreSelect(this)">
						     <option value="${storeAccount}" storeType = "2">全企业</option>
						     <c:forEach items="${storeInfoList}" var="storeInfo" varStatus="status">
						         <option value="${storeInfo.storeId}" storeType = "1"
						            <c:if test = "${status.index == 0 }">selected = "selected"</c:if>
						         >${storeInfo.storeName}</option>
						     </c:forEach>
						   </select>
				       </c:if>
					   <span><button onclick = "showZzc()">设置会员指标</button></span>
					 </div>
				      
				  </div>
				    <div id="container1" style="width:508px;height:400px;"></div>
				    <div id="container2" style="width:508px;height:400px"></div>
					
					<div class="customer_analyse_table clearfix">
					  <div class="customer_analyse_table_left">
					    <table name = "grilTable" class = "grilTable">
						   <tr>
						      <c:choose>
						        <c:when test="${pageType == 1}"><td>平均年消费</td></c:when>
						        <c:when test="${pageType == 2}"><td>年到店次数</td></c:when>
						        <c:otherwise><td>几天未到店</td></c:otherwise>
						      </c:choose>
						   </tr>
						   <tr>
						      <td>消费人数</td>
						   </tr>
						   <tr>
						      <td>人数占比</td>
						   </tr>
						   <tr>
						      <td>消费总额</td>
						   </tr>
						   <tr>
						      <td>消费占比</td>
						   </tr>
						    <tr>
						      <td>平均消费单价</td>
						    </tr>
						</table>
					  </div>		
					  <div class="customer_analyse_table_right">
					    <table name = "boyTable" class = "grilTable">
						   <tr>
						      <c:choose>
						        <c:when test="${pageType == 1}"><td>平均年消费</td></c:when>
						        <c:when test="${pageType == 2}"><td>年到店次数</td></c:when>
						        <c:otherwise><td>几天未到店</td></c:otherwise>
						      </c:choose>
						   </tr>
						   <tr>
						      <td>消费人数</td>
						   </tr>
						   <tr>
						      <td>人数占比</td>
						   </tr>
						   <tr>
						      <td>消费总额</td>
						   </tr>
						   <tr>
						      <td>消费占比</td>
						   </tr>
						    <tr>
						      <td>平均消费单价</td>
						    </tr>
						</table>
						
					  </div>	
					</div>
				  </div>
				  <div class="customer_analyse_content_">
				    <div class="customer_analyse_content_datail">
				      <div class="customer_analyse_search">
					    <button>发送优惠券</button>
						<button>赠送礼金</button>
						<button>群发消息</button>
					  </div>
				    </div>
					
					<div class="solve_way">
					  <div class="solve_way_table">
					    <table id = "init_member">
					       <tr>
						     <td><input type="checkbox"><span>全选</span></td>
						     <td>姓名</td>
							 <td>性别</td>
							 <td>电话号码</td>
							 <td>总金额消费</td>
							 <td>总到店次数</td>
							 <td>距今今日未到店</td>
							 <td>生日</td>
							 <td>首次到店时间</td>
							 <td>登记门店</td>
						   </tr>
					    </table>
					  </div>
					</div>	
					<%@ include file="/template/page.jsp" %>  
				  </div>
				  <div class="customer_analyse_content_">
				     <div style="height: 760px; overflow-x: hidden;overflow-y: scroll;">
				        <c:choose>
					        <c:when test="${pageType == 1}"><img src="<%=qiniuPath%>system/profile/bigCustomer.jpg"></c:when>
					        <c:when test="${pageType == 2}"><img src="<%=qiniuPath%>system/profile/customerLoyal.jpg"></c:when>
					        <c:otherwise><img src="<%=qiniuPath%>system/profile/customerActive.jpg"></c:otherwise>
					    </c:choose>
				     </div>
				  </div>	  
				</div>
			  </div> 
		</div>
    </div>
  </div>


  <div class="zzc" style="display: none;">
	  <div class="set_pay">
	    <p>设置消费金额</p>
	    <div class="set_pay_content">
	      <p>企业或门店<span></span>
	                  <select name="chooseStore" onchange="chooseActiveStore()">
					     <option value="${storeAccount}">全企业</option>
					     <c:forEach items="${storeInfoList}" var="storeInfo" varStatus="status">
					         <option value="${storeInfo.storeId}">${storeInfo.storeName}</option>
					     </c:forEach>
					   </select>
		  </p>
	      <p><input type="number" name = "start1"><span>至</span><input type="number" name = "end1"></p>
		  <p><input type="number" name = "start2" class="active1" disabled><span>至</span><input type="number" name = "end2"></p>
		  <p><input type="number" name = "start3" class="active1" disabled><span>至</span><input type="number" name = "end3"></p>
		  <p><input type="number" name = "start4" class="active1" disabled><span>至</span><input type="number" name = "end4"></p>
		  <p><input type="number" name = "start5" class="active1" disabled><span>以上</span></p>
		  <div class="set_pay_content_button" settingRuleId = "">
		    <button onclick = "saveRule()">确认</button>
		    <button onclick = "cancal()">取消</button>
		  </div>
		</div>
	  </div> 
  </div>
</body>
<script type="text/javascript">

   var analysisType = '${analysisType}';
   
   var ruleListStr = '${ruleListStr}';
   var storeRuleList = eval("(" + ruleListStr + ")");
   
   var pageNo = 1;	
   var pageSize = 15;	
   
   var pageType = '${pageType}';
</script>
<script type="text/javascript" src="<%=basePath %>js/programme/bigCustomerAnalysis.js"></script>
</html>