<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/customer_analyse.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			 <div class='content_right clearfix'>
			    <ul class="clearfix">
				  <li class="active">大客户分析</li>
				  <li>解决方案</li>
				</ul>
				<div class="customer_analyse_content">
				 <div class="customer_analyse_content_">
				  <div class="customer_analyse_content_datail">
				     <div class="customer_analyse_search">
					   <select name = "selectStore">
					     <option value="${storeAccount}">全企业</option>
					     <c:forEach items="${storeInfoList}" var="storeInfo" varStatus="status">
					         <option value="${storeInfo.storeId}" 
					            <c:if test = "${status.index == 0 }">selected = "selected"</c:if>
					         >${storeInfo.storeName}</option>
					     </c:forEach>
					   </select>
					   <button>查询</button>
					   <span><button onclick = "showZzc()">设置会员指标</button></span>
					 </div>
				      
				  </div>
				    <div id="container1" style="width:508px;height:400px;"></div>
				    <div id="container2" style="width:508px;height:400px"></div>
					
					<div class="customer_analyse_table clearfix">
					  <div class="customer_analyse_table_left">
					    <table>
						   <tr>
						      <td>平均年消费</td>
							  <td>0-300元</td>
							  <td>300-500元</td>
							  <td>500-1000元</td>
							  <td>1000-3000元</td>
							  <td>3000元以上</td>
						   </tr>
						   <tr>
						      <td>消费人数</td>
							  <td>3</td>
							  <td>3</td>
							  <td>3</td>
							  <td>3</td>
							  <td>3</td>
						   </tr>
						   <tr>
						      <td>人数占比</td>
							  <td>37%</td>
							  <td>37%</td>
							  <td>37%</td>
							  <td>37%</td>
							  <td>37%</td>
						   </tr>
						   <tr>
						      <td>消费总额</td>
							  <td>340.00</td>
							  <td>340.00</td>
							  <td>340.00</td>
							  <td>340.00</td>
							  <td>340.00</td>
						   </tr>
						   <tr>
						      <td>消费占比</td>
							  <td>3.1%</td>
							  <td>3.1%</td>
							  <td>3.1%</td>
							  <td>3.1%</td>
							  <td>3.1%</td>
						   </tr>
						    <tr>
						      <td>平均消费单价</td>
							  <td>133.33</td>
							  <td>133.33</td>
							  <td>133.33</td>
							  <td>133.33</td>
							  <td>133.33</td>
						    </tr>
						   <tr>
						     <td>合计</td>
						     <td>3840.00</td>
							 <td>3840.00</td>
							 <td>3840.00</td>
							 <td>3840.00</td>
						     <td>3840.00</td>
						   </tr>
						</table>
					  </div>		
					  <div class="customer_analyse_table_right">
					    <table>
						   <tr>
						      <td>平均年消费</td>
							  <td>0-300元</td>
							  <td>300-500元</td>
							  <td>500-1000元</td>
							  <td>1000-3000元</td>
							  <td>3000元以上</td>
						   </tr>
						   <tr>
						      <td>消费人数</td>
							  <td>3</td>
							  <td>3</td>
							  <td>3</td>
							  <td>3</td>
							  <td>3</td>
						   </tr>
						   <tr>
						      <td>人数占比</td>
							  <td>37%</td>
							  <td>37%</td>
							  <td>37%</td>
							  <td>37%</td>
							  <td>37%</td>
						   </tr>
						   <tr>
						      <td>消费总额</td>
							  <td>340.00</td>
							  <td>340.00</td>
							  <td>340.00</td>
							  <td>340.00</td>
							  <td>340.00</td>
						   </tr>
						   <tr>
						      <td>消费占比</td>
							  <td>3.1%</td>
							  <td>3.1%</td>
							  <td>3.1%</td>
							  <td>3.1%</td>
							  <td>3.1%</td>
						   </tr>
						    <tr>
						      <td>平均消费单价</td>
							  <td>133.33</td>
							  <td>133.33</td>
							  <td>133.33</td>
							  <td>133.33</td>
							  <td>133.33</td>
						    </tr>
						   <tr>
						     <td>合计</td>
						     <td>3840.00</td>
							 <td>3840.00</td>
							 <td>3840.00</td>
							 <td>3840.00</td>
						     <td>3840.00</td>
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
					    <table>
					       <tr>
						     <td><input type="checkbox"><span>全选</span></td>
						     <td>姓名</td>
							 <td>性别</td>
							 <td>电话号码</td>
							 <td>卡号</td>
							 <td>总金额消费</td>
							 <td>总到点次数</td>
							 <td>距今今日未到店</td>
							 <td>年龄</td>
							 <td>首次到店时间</td>
							 <td>登记门店</td>
						   </tr>
					       <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						    <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						    <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						    <tr>
						     <td><input type="checkbox"><span>1</span></td>
						     <td>打死</td>
							 <td>女客</td>
							 <td>13020205050</td>
							 <td>13020205050</td>
							 <td>10000</td>
							 <td>34</td>
							 <td>2</td>
							 <td>29</td>
							 <td>2016-16-12</td>
							 <td>中邦我道一号店</td>
						   </tr>
						   
					    </table>
					  </div>
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
	      <p><input type="text" name = "start1"><span>至</span><input type="text" name = "end1"></p>
		  <p><input type="text" name = "start2" class="active1" disabled><span>至</span><input type="text" name = "end2"></p>
		  <p><input type="text" name = "start3" class="active1" disabled><span>至</span><input type="text" name = "end3"></p>
		  <p><input type="text" name = "start4" class="active1" disabled><span>至</span><input type="text" name = "end4"></p>
		  <p><input type="text" name = "start5" class="active1" disabled><span>以上</span></p>
		  <div class="set_pay_content_button" setting_rule_id>
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
</script>
<script type="text/javascript" src="<%=basePath %>js/programme/bigCustomerAnalysis.js"></script>
</html>