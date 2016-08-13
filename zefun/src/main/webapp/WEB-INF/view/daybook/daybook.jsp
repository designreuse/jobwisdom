<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/water_search.css" type="text/css" />

 <style>
     .search_table_ tr:last-child td{border-bottom:none}
     .water_search_top_content ul li .active{color:#e41d1d}
	 .zzc {
		font-size: 12px;
		color: black;
		position: fixed;
		top: 0px;
		height: 1090px;
		left: 0px;
		width: 100%;
		z-index: 10000;
		background: rgba(102, 108, 121, 0.8);
       }
	   .order_see{width:1100px;height:485px;box-shadow:0 2px 4px #8299bc;background:white;border-radius:8px;overflow:hidden;margin:10% auto}
	   .order_see>p{height:58px;background:#383849;text-align:center;line-height:58px;color:white;font-size:15px}
	  .order_see_content{padding:15px 20px}
	  .order_see_content>.order_see_content_num p{height:30px;line-height:30px;font-size:14px;border-bottom:1px solid #dce3fa}
	  .order_see_content>.order_see_content_num p>span{color:#ff0f0f}
	  .order_see_content_num em{float:right;display:inline-block;width:100px;text-align:right}
	  .order_see_content_num em i{color:#d86451}
	  .order_see_content_num .cash_1 li{float:left;width:153px}
	  .order_see_content_num input{border-radius:12px;width:95px;height:14px;position:relative;left:4px;border:1px solid #c6d0e0}
	  .cash_content{margin-right:-80px;margin-top:10px}
	  .order_see_content_num{overflow:hidden}
	  .order_style_content{border-radius:8px;border:1px solid #dce3fa;width:1060px;margin:0px auto;margin-bottom:20px;font-size:14px}
	  .order_style_content_left{float:left;text-align:center;height:116px;width:145px;background:#d8e0f2}
	  .order_style_content_left>p{font-size:12px;margin-top:4px}
	  .order_style_content_left>p>em{color:#cb2626}
	  .order_style_content_left>span{display:inline-block;margin-top:34px}
	  .order_style_content_right{float:left}
	  .order_style_content_right>li>span{display:inline-block;text-align:center;margin-left:15px}
	  .order_style_content_right select{width:116px;height:16px;border-radius:12px;border:1px solid #b1c0d6}
	  .order_style_content_right input{margin-left:8px;width:110px;height:16px;border-radius:12px;border:1px solid #b1c0d6}
	  .order_style_content_right>li{height:38px;line-height:38px;width:915px;background:#f7f9fc;border-bottom:1px solid #dce3fa}
	  .order_style_content_right li:last-child{border-bottom:none}
	  .order_style_content_{height:260px;overflow-x:hidden;margin-top:20px;overflow-y:overlay}
	  .order_see_button{text-align:center;margin-top:20px}
	  .order_see_button button{width:130px;height:26px;text-align:center;line-height:26px;border:none;background:#61678b;border-radius:12px;margin:0 50px;color:white;font-size:14px}
	  .order_see_button button:hover{background:#515676}
	</style>
<body>

<!-- <div class="zzc2" style="display:none"> -->
<!--    <div class="zzc_content"> -->
<%-- 	  <div class="water_number">流水单号:<span id="orderCodeModel1"></span><span class="close_zzc"><img src="<%=basePath%>images/close.png"></span ></div> --%>
<!-- 	   <table class="zzc_1"> -->
<!-- 	     <tr> -->
<!-- 	       <td>应收 </td> -->
<!-- 		   <td>现钞 </td> -->
<!-- 		   <td>银联 </td> -->
<!-- 		   <td>微信 </td> -->
<!-- 		   <td>支付宝</td> -->
<!-- 		   <td>卡金</td> -->
<!-- 		   <td>团购 </td> -->
<!-- 		   <td>优惠抵扣 </td> -->
<!-- 		   <td>挂账 </td> -->
<!-- 		   <td>实收</td> -->
<!-- 	      </tr> -->
<!-- 		   <tr> -->
<!-- 	       <td><span class="red" id = "discountAmountModel"></span></td> -->
<!-- 		   <td><input type="text" name = "cashAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td> -->
<!-- 		   <td><input type="text" name = "unionpayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td> -->
<!-- 		   <td><input type="text" name = "wechatAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td> -->
<!--            <td><input type="text" name = "alipayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td> -->
<!--            <td><input type="text" name = "cardAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td> -->
<!--            <td><input type="text" name = "groupAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td> -->
<!--            <td><span class="red" id = "privilegeModel1"></span></td> -->
<!--            <td><input type="text" name = "debtAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td> -->
<!--            <td><span class="red" id = "realAmountModel"></span></td> -->
<!-- 	      </tr> -->
<!-- 	   </table> -->
<!-- 	   <div id = "detailExpense"> -->
	      
<!-- 	   </div> -->
<!-- 	 <div class="water_button"> -->
<!-- 	   <button class="water_cancel">取消</button> -->
<!-- 	   <button class="accounts">结账</button> -->
<!-- 	 </div> -->
<!-- 	</div> -->
<!-- </div> -->

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
	     <td width="100">水单号</td>
		 <td width='100'>顾客</td>
		 <td>消费时间</td>
		 <td colspan="2">总消费金额</td>
		 <td width="141">消费项目</td>
		 <td style="width:298px">服务者业绩（员工类型指定）</td>
		 <td style="width:80px">操作</td>
	   </tr>
	<c:forEach var="daybook" items="${page.results}" varStatus="status">
		<tr>
	    <td onclick="updateSelectOrder(${daybook.orderId})"><a class="can-click">${daybook.orderCode}</a></td>
<%-- 	    <td onclick="jQuery('.zzc').show()"><a class="can-click">${daybook.orderCode}</a></td> --%>
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
		 <td><i>${daybook.createTime}</i><img onclick="WdatePicker({el:'d12',dateFmt:'yyyy-MM-dd HH:mm:ss',onpicked:pickedFunc})" src="<%=basePath%>images/coupon_write.png"> <input type="text" class="hidden" style="width: 0px" id="d12" ></td>
		 
		 <td>合计 <em>${daybook.realAmount}</em></td>
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
		 <td style="width:140px" colspan="2">
			 <c:forEach items="${daybook.orderDetailList}" var="orderDetailList"> 
				   <table class="search_table_" width='439'>
					 <tr>
					  <td rowspan="100" width="141">
						<p class="pay_item">${orderDetailList.projectName}</p><p>${orderDetailList.detailCalculate}</p><i class="pay_item" style="text-align: center;"><c:forEach items="${daybook.deptList}" var="deptList">(${deptList.deptName})</c:forEach></i>
					  </td>
					  </tr>
					  <c:forEach items="${orderDetailList.commissionList}" var="commissionList">
						 <tr>
						   <td> 
								<span>(${commissionList.employeeCode })<em>${commissionList.employeeName }</em>
								  <c:if test="${orderDetailList.isAssign eq 1 }">
									<i>(指定)</i>
								  </c:if>
									<c:if test="${orderDetailList.isAssign eq 0 }">
									<i>(未指定)</i>
								  </c:if>
								</span>
								<span>业绩:<em>${commissionList.commissionCalculate }</em></span>
							  <span style="border-right:none">提成:<em>${commissionList.commissionAmount } </em></span>
						  </td>
						 </tr>
					 </c:forEach>
			   </table>
		   </c:forEach>
		 </td>
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
<div class="zzc">
   <div class="order_see">
     <p>订单查看</p>
     <div class="order_see_content">
       <div class="order_see_content_num">
	    <p>流水单号：<span id="orderCodeModel"></span><em>优惠折扣<i class="red" id = "privilegeModel"></i></em><em>应收<i  id = "discountAmountModel"></i></em>  <em>实收<i  id = "realAmountModel"></i></em>  </p>
		 <div class="cash_content">
			<ul class="cash_1 clearfix">
			   <li>现钞<input type="text" name = "cashAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></li>
			   <li>银联<input type="text" name = "unionpayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></li>
			   <li>微信<input type="text" name = "wechatAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></li>
			   <li>支付宝<input type="text" name = "alipayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></li>
			   <li>卡金<input type="text" name = "cardAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></li>
			   <li>团购<input type="text" name = "groupAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></li>
			   <li>挂账<input type="text" name = "debtAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></li>
			</ul>
		 </div>	
	   </div>
	  <div class="order_style_content_" id="divobj"> 
	
	   </div> 
	  </div>
	  <div class="order_see_button">
	    <button onclick="confirmModel()">确认</button>
	    <button class="accounts" onclick="jQuery('.zzc').hide()">取消</button>
	  </div>
	 </div>
   </div>
</div>
 <!--删除提示-->
	    <div class="modal hide" id="deleteOrderTip" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	        <div class="modal-dialog" role="document">
	            <div class="modal-content confirm">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" onclick="czCancel()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                    <h4 class="modal-title" id="myModalLabel">删除订单</h4>
	                </div>
	    
	                <div class="modal-body confirm-body">
	                                                订单删除后不可恢复，是否确定本次操作？
	                </div><!--modal-body-->
	    
	                <div class="modal-footer">
	                    <a class="btn cancel-btn modal-cancel" data-dismiss="modal" href="javascript:void(0);">取消</a>
	                    <a class="btn btn-primary save-btn modal-confirm" href="javascript:deleteOrderConfirm();">确定</a>
	                </div>
	            </div>
	        </div>
	    </div> 
<%@ include file="/template/memberData.jsp" %>
<script>
	var pageNo = "${page.pageNo}";
	var pageSize = "${page.pageSize}";
	var totalPage = "${page.totalPage}";
	var totalRecord = '${page.totalRecord}';
	
	var queryParams = JSON.parse('${queryParamsStr}');
	  
	   
   function pickedFunc() {
	  jQuery(this).parent("td").find("i").text(jQuery("#d12").val());
	}
   
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