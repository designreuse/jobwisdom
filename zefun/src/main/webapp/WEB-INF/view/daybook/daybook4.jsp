<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/water_search.css" type="text/css" />
<<style>
.water_search_top_content ul li .active{
	color:#e41d1d
}
</style>
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
        <div class='content_right'>
	     <div class="water_search_top">
		    <div class="water_search_top_input">
			   <input type="text" id="ipt-search-phone" placeholder="水单号/手机号/员工工号">
			   <span><input type="text" id = "startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">至<input type="text" id="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></span>
			   <button onclick="btnSearchTime();">查询</button>
			  <!-- <em> <button >今天</button> <button>昨天</button></em> -->
			</div>
			<div class="clearfix water_search_top_content">
			  <ul>
			   <li id = "orderType">
			    <em>类型：<em>
				<span class = "active" value = "0">全部</span>
				<span value = "1">项目</span>
				<span value = "2">商品</span>
				<span value = "3">疗程</span>
				<span value = "4">开卡</span>
				<span value = "5">充值</span>
				<span value = "6">升级</span>
				<span value = "7">赠送</span>
				<span value = "8">还款</span>
			  </li>
			  <li id = "moneyWay">
			    <em>方式：<em>
				<span class = "active" value = "0">全部</span>
				<span value = "1">现金</span>
				<span value = "2">银联</span>
				<span value = "3">团购</span>
				<span value = "4">微信</span>
				<span value = "5">支付宝</span>
				<span value = "6">卡金</span>
				<span value = "7">礼金</span>
				<span value = "8">疗程</span>
				<span value = "9">优惠券</span>
				<span value = "10">挂账</span>
			 </li>
			  <li id = "orderState">
			    <em>状态：<em>
				<span class = "active" value = "0">全部</span>
				<span value = "1">正常</span>
				<span value = "2">退单</span>
			  </li>
			  <li id = "deptId">
			    <em>部门：<em>
				<span class = "active">全部</span>
				<c:forEach var="deptInfo" items="${deptInfoList}" varStatus="status">
				     <span value = "${deptInfo.deptId }" style="width: 100px">${deptInfo.deptName }</span>
				</c:forEach>
			  </li>
			 </ul>
	          <div class="water_search_top_total">
	             <div class="water_search_top_total_top">
				    <p><span>总客数：<em id="totalPersonCount">${countBook.count }</em></span><span>应收款：<em id="receivableAmount">${countBook.receivableAmount }</em></span></p>
					<p><span>礼金折扣：<em id="giftAmount">${countBook.giftAmount }</em></span><span>划卡：<em id="cardAmount">${countBook.cardAmount }</em></span></p>
				    <p><span>疗程折扣：<em id="comboAmount">${countBook.comboAmount }</em></span><span>优惠券抵扣：<em id="couponAmount">${countBook.couponAmount }</em></span></p>
				 </div>
				 <div class="water_search_top_total_price">
				    <span>现金实收：</span>
				    <em>${countBook.realPrice }</em>
				 </div>
	
	         </div>		
			</div>
		 </div>
		<div class="search_table_content"> 
		 <table class="search_table">
		   <tr>
		     <td>水单号</td>
			 <td>顾客</td>
			 <td>消费时间</td>
			 <td>服务内容</td>
			 <td>应收</td>
			 <td>消费金额</td>
			 <td>抵扣</td>
			 <td>挂账</td>
			 <td>实收</td>
			 <td>状态</td>
			 <td>操作</td>
		   </tr>
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
		               <c:if test="${daybook.cashAmount == '0.00' and daybook.unionpayAmount == '0.00' and daybook.wechatAmount == '0.00' and daybook.alipayAmount == '0.00' and daybook.cardAmount == '0.00'}">
		                   0.00
		               </c:if>
		               <c:if test="${daybook.cashAmount != 0}">
		                   <p>${daybook.cashAmount}<em style="color:red">现金</em></p>
		               </c:if>
		               <c:if test="${daybook.unionpayAmount != '0.00'}">
		                   <p>${daybook.unionpayAmount}<em style="color:blue">银联</em></p>
		               </c:if>
		               <c:if test="${daybook.wechatAmount != '0.00'}">
		                   <p>${daybook.wechatAmount}<em style="color:#59688a">微信</em></p>
		               </c:if>
		               <c:if test="${daybook.alipayAmount != '0.00'}">
		                   <p>${daybook.alipayAmount}<em style="color:green">支付宝</em></p>
		               </c:if>
		               <c:if test="${daybook.cardAmount != '0.00'}">
		                   <p>${daybook.cardAmount}<em style="color:pink">卡金</em></p>
		               </c:if>
	                </td>
                  
	                <td>
	                  <c:if test="${daybook.comboAmount == '0.00' and daybook.giftAmount == '0.00' and daybook.couponAmount == '0.00' and daybook.groupAmount == '0.00'}">
	                     0.00
	                  </c:if>
	                  <c:if test="${daybook.comboAmount != '0.00'}">
		                   <span>${daybook.comboAmount}<em style="color:red;display:block">疗程</em></span>
		              </c:if>
		              <c:if test="${daybook.giftAmount != '0.00'}">
		                   <span>${daybook.giftAmount}<em style="color:blue;display:block">礼金</em></span>
		              </c:if>
		              <c:if test="${daybook.couponAmount != '0.00'}">
		                   <span>${daybook.couponAmount}<em style="color:#59688a;display:block">优惠券</em></span>
		              </c:if>
		              <c:if test="${daybook.groupAmount != '0.00'}">
		                   <span>${daybook.groupAmount}<em style="color:green;display:block">团购</em></span>
		              </c:if>
	                </td>
	                <td>${daybook.debtAmount}</td>
	                <td>${daybook.realAmount}</td>
	                <td>正常</td>
			        <td><button onclick="deleteOrder(${daybook.orderId}, this)">退单</button></td>
		        </tr> 
		   </c:forEach>
		 </table>
	  </div>
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
        
    jQuery("#startDate").val(nowDate + " 00:00:00") ;
    jQuery("#endDate").val(nowDate + " 23:59:59") ;
</script>
<script type="text/javascript" src="<%=basePath %>js/daybook/daybook.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
</body>
</html>