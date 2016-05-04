<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/water.css" type="text/css" />
<body>

<div class="zzc" style="display:none">
   <div class="zzc_content">
	  <div class="water_number">流水单号:<span id="orderCodeModel"></span><span class="close_zzc"><img src="assets/images/close.png"></span ></div>
	   <table class="zzc_1">
	     <tr>
	       <td>应收 </td>
		   <td>现钞 </td>
		   <td>银联 </td>
		   <td>微信 </td>
		   <td>支付宝</td>
		   <td>卡金</td>
		   <td>团购 </td>
		   <td>优惠抵扣 </td>
		   <td>挂账 </td>
		   <td>实收</td>
	      </tr>
		   <tr>
	       <td><span class="red" id = "discountAmountModel"></span></td>
		   <td><input type="text" name = "cashAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
		   <td><input type="text" name = "unionpayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
		   <td><input type="text" name = "wechatAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
           <td><input type="text" name = "alipayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
           <td><input type="text" name = "cardAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
           <td><input type="text" name = "groupAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
           <td><span class="red" id = "privilegeModel"></span></td>
           <td><input type="text" name = "debtAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
           <td><span class="red" id = "realAmountModel"></span></td>
	      </tr>
	   </table>
	   <div id = "detailExpense">
	      
	   </div>
	 <div class="water_button">
	   <button class="water_cancel">取消</button>
	   <button class="accounts">结账</button>
	 </div>
	</div>
</div>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
    <%@include file="/menu.jsp"%>

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
      	<%@ include file="/top.jsp" %>
        <div class='content_right' >
 	请输入单号：<input type="text" id="ipt-search-phone" class="simgle" placeholder="水单号/手机号/员工工号">
	<input type="datetime-local"  id = "startDate" value="2014-03-15T08:00" style="color:#d7d8da;border:1px solid #d7d8da;width:160px">

    <span style="display:inline-block;margin:0 10px;color:#ccc">-——</span>

	<input type="datetime-local" id="endDate" class="datetime_" style="color:#d7d8da;border:1px solid #d7d8da;width:160px">
 
    <button class="search_" onclick="btnSearchTime();">立刻查询</button>
	<div class="income_">营业收入：</div>
	<table class="income">
	  <tr>
	    <td>现钞</td>
	    <td>银联</td>
		<td>微信支付</td>
		<td>支付宝付</td>
		<td>团购支付</td>
		<td>疗程消费</td>
		<td>划卡消费</td>
	  </tr>
	   <tr>
	    <td id="cashAmount">${countBook.cashAmount}</td>
	    <td id="unionpayAmount">${countBook.unionpayAmount}</td>
		<td id="wechatAmount">${countBook.wechatAmount}</td>
		<td id="alipayAmount">${countBook.alipayAmount }</td>
	    <td id="groupAmount">${countBook.groupAmount }</td>
	    <td id="debtAmount">${countBook.debtAmount }</td>
		<td id="cardAmount">${countBook.cardAmount }</td>
	  </tr>
	</table>

	
	
	<div class="income_">收入来源：</div>
	<table class="income">
	  <tr>
	    <td>服务项目（<span id="projectSalesCount">${detailCount.projectSalesCount}</span>单）</td>
	    <td>商品销售（<span id="goodsSalesCount">${detailCount.goodsSalesCount}</span>单）</td>
		<td>疗程销售（<span id="comboSalesCount">${detailCount.comboSalesCount}</span>单）</td>
		<td>开卡充值（<span id="cardSalesCount">${detailCount.cardSalesCount}</span>单）</td>
		<td>卡金赠送（<span id="presentCount">${detailCount.presentCount}</span>单）</td>
		
	  </tr>
	   <tr>
	    <td id="projectSalesAmount">${detailCount.projectSalesAmount}</td>
	    <td id="goodsSalesAmount">${detailCount.goodsSalesAmount}</td>
		<td id="comboSalesAmount">${detailCount.comboSalesAmount}</td>
	    <td id="cardSalesAmount">${detailCount.cardSalesAmount}</td>
	    <td id="presentAmount">${detailCount.presentAmount}</td>
		
	  </tr>
	</table>
	<div class="total">
	  <span>总客数：<em id="totalPersonCount">${countBook.count }</em></span>
	  <span>应收款：<em id="receivableAmount">${countBook.receivableAmount}</em></span>
	  <span>礼金抵扣：<em id="giftAmount">${countBook.giftAmount }</em></span>
	  <span>优惠券抵扣：<em id="couponAmount">${countBook.couponAmount }</em></span>
	  <span>套餐抵扣：<em id="comboAmount">${countBook.comboAmount }</em></span>
	  <span>划卡：<em id="cardAmount">${countBook.cardAmount }</em></span>
	  <span>现金实收：<em id="realPrice">${countBook.realPrice }</em></span>
	</div>
	
	
		<table class="income_1">
		   <thead>
			  <tr>
			    <td class="water_" style="cursor:pointer">水单号</td>
			    <td>顾客</td>
				<td>消费时间</td>
				<td>服务内容</td>
				<td>应收</td>
				<td>消费金额</td>
				<td>抵扣</td>
				<td>挂账</td>
				<td>实收</td>
				<td>操作</td>
			  </tr>
	      </thead>
	      <tbody id="tbody-data">
	          <c:forEach var="daybook" items="${page.results}" varStatus="status">
		        <tr>
		            <td onclick="updateSelectOrder(${daybook.orderId})"><a class="can-click">${daybook.orderCode}</a></td>
		            <c:choose>
		                 <c:when test="${daybook.memberId == null}">
		                 <td>
		                 	散客（${daybook.sex}）
		                 </td>
		                 </c:when>
		                 <c:otherwise>
		                   <td class="can-click" data-toggle="modal" data-target="#member-data" onclick="selectMemberInfo(${daybook.memberId})">
		                      ${daybook.memberName}
		                   </td>
		                 </c:otherwise>
	                </c:choose>
	                <td>${daybook.createTime}</td>
	                <td>
	                   ${daybook.projectName}
	                </td>
	                <td>${daybook.realPrice}</td>
	                <td>
		               <c:if test="${daybook.cashAmount == 0 and daybook.unionpayAmount == 0 and daybook.wechatAmount == 0 and daybook.alipayAmount == 0 and daybook.cardAmount == 0}">
		                   0.00
		               </c:if>
		               <c:if test="${daybook.cashAmount != 0}">
		                   <span>${daybook.cashAmount}<em style="color:red">现金</em></span>
		               </c:if>
		               <c:if test="${daybook.unionpayAmount != 0}">
		                   <span>${daybook.unionpayAmount}<em style="color:blue">银联</em></span>
		               </c:if>
		               <c:if test="${daybook.wechatAmount != 0}">
		                   <span>${daybook.wechatAmount}<em style="color:#59688a">微信</em></span>
		               </c:if>
		               <c:if test="${daybook.alipayAmount != 0}">
		                   <span>${daybook.alipayAmount}<em style="color:green">支付宝</em></span>
		               </c:if>
		               <c:if test="${daybook.cardAmount != 0}">
		                   <span>${daybook.cardAmount}<em style="color:pink">卡金</em></span>
		               </c:if>
	                </td>
                  
	                <td>
	                  <c:if test="${daybook.comboAmount == 0 and daybook.giftAmount == 0 and daybook.couponAmount == 0 and daybook.groupAmount == 0}">
	                     0.00
	                  </c:if>
	                  <c:if test="${daybook.comboAmount != 0}">
		                   <span>${daybook.comboAmount}<em style="color:red;display:block">疗程</em></span>
		              </c:if>
		              <c:if test="${daybook.giftAmount != 0}">
		                   <span>${daybook.giftAmount}<em style="color:blue;display:block">礼金</em></span>
		              </c:if>
		              <c:if test="${daybook.couponAmount != 0}">
		                   <span>${daybook.couponAmount}<em style="color:#59688a;display:block">优惠券</em></span>
		              </c:if>
		              <c:if test="${daybook.groupAmount != 0}">
		                   <span>${daybook.groupAmount}<em style="color:green;display:block">团购</em></span>
		              </c:if>
	                </td>
	                <td>${daybook.debtAmount}</td>
	                <td>${daybook.realAmount}</td>
			        <td><span class="iconfa-trash project-icon" onclick="deleteOrder(${daybook.orderId}, this)"></span></td>
		        </tr> 
	  </c:forEach>
	      </tbody>
	</table>
	<%@ include file="/template/page.jsp" %>
  </div>
	    

  </div>
</div>
</div>
<!--mainwrapper-->

<%@ include file="/template/memberData.jsp" %>
<script>
	var pageNo = "${page.pageNo}";
	var pageSize = "${page.pageSize}";
	var totalPage = "${page.totalPage}";
	var totalRecord = '${page.totalRecord}';
	
	var queryParams = JSON.parse('${queryParamsStr}');
   
    //初始化时间控件
    var now = new Date() ;
        
    var nowYear = now.getFullYear() ; //年
    var nowMonth = now.getMonth()+1<10?"0"+(now.getMonth()+1):now.getMonth() ; //月
    var nowDay = now.getDate()<10?"0"+now.getDate():now.getDate() ; //日期
        
    var nowDate = nowYear+"-"+nowMonth+"-"+nowDay ;
        
    jQuery("#startDate").val(nowDate + "T00:00") ;
    jQuery("#endDate").val(nowDate + "T23:59") ;
</script>
<script type="text/javascript" src="<%=basePath %>js/daybook/daybook.js"></script>
</body>
</html>