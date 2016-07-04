<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.Date" %>
<link rel="stylesheet" href="<%=basePath%>css/commission.css" type="text/css" />
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>  
				 <div class='content_right clearfix'>
			    <div class="commision_content">
				   <ul class="commision_content_top clearfix">
				      <li style="width:136px">分配对象</li>
				      <li>商品</li>
					  <li>疗程</li>
				   </ul>
				   <div class="money_commission clearfix" style="border-bottom:1px solid #898989">
				      <div class="money_commission_left">
				        现金分配方式
					  </div>
					   <div class="money_commission_right">
				          <div class="money_commission_right_detail">
						    <p>二人分配</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							
							</div>
						  
						  </div>
						 
						  <div class="money_commission_right_detail">
						    <p>三人分配</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							   <p>第3人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							</div>
						  
						  </div>
						 
					  </div>
			
				   </div>
				   <div class="money_commission clearfix">
				      <div class="money_commission_left">
				        卡金提成方式
					  </div>
					   <div class="money_commission_right">
				          <div class="money_commission_right_detail">
						    <p>二人提成</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							
							</div>
						  
						  </div>
						 
						  <div class="money_commission_right_detail">
						    <p>三人提成</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							   <p>第3人：业绩分配<span><input type="text"><em>%</em></span>员工提成<span><input type="text"><em>%</em></span></p>
							</div>
						  
						  </div>
						 
					  </div>
			
				   </div>
				
				
				
				</div>
				<div class="commision_button">
				   <button>确认</button>
				</div>
		  </div>
		</div>
    </div>
</div>

<script type="text/javascript">
var cssWidth = 200;
var cssHeight = 200;
var qiniuUrl = '<%=qiniuPath%>';
var imgObject;

var storeEmployeeListStr =  '${storeEmployeeListStr}';
var storeEmployeeList;
if (!isEmpty(storeEmployeeListStr)) {
	storeEmployeeList = eval("(" + storeEmployeeListStr + ")");
}

var enterpriseEdition = '${enterpriseAccount.enterpriseEdition }';
var alreadyStoreNum = '${enterpriseAccount.alreadyStoreNum}';
var priceMoneyOrTimeStr = '${priceMoneyOrTimeStr}';
var priceMoneyOrTime;
if (!isEmpty(priceMoneyOrTimeStr)) {
	priceMoneyOrTime = eval("(" + priceMoneyOrTimeStr + ")");
}
</script>
<script type="text/javascript" src="<%=basePath %>/js/setting/storeList.js"></script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>
<script src="<%=basePath%>js/common/city-picker.data.js"></script>
<script src="<%=basePath%>js/common/city-picker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>

</body>
</html>