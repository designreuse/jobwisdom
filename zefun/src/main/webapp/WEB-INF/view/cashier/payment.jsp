<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Date" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath %>css/money.css" type="text/css" />
<body>

<div class="zzc" name = "changeMoneyDIV">
   <div class="change_price">
       <p class="change_price_">更改价格<span class="money_close_"><img src="assets/images/money_close.png"></span></p>
	     <div class="money_head_content">
	        <div class="money_head clearfix">
				<p class="money_head_p"><img src="assets/images/money_head.png"></p>
				<div class="boss_number_">
				  <p>欧老板<p>
				  <p>订单号：201604270027</p>
				
				</div>
			   
			    
			</div>
			
			<table class="money_change">
				   <tr>
				     <td>项目名称</td>
					 <td>项目价格</td>
					 <td>会员价格</td>
					 <td>改价金额</td>
				   </tr>
				     <tr>
				     <td>施华蔻烫</td>
					 <td>588</td>
					 <td>588</td>
					 <td></td>
				   </tr>
				</table>
				
			<div class="remarks_money"> <span>备注</span><input type="text"></div>
			
			
			<div class="money_button">
			   <button class="money_cancle">取消</button>
			   <button>结账</button>
			
			</div>
        </div>
    </div>
</div>	

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	
			<div class='content_right clearfix'>
			     <p class="money_">
				    <c:choose>
		            	<c:when test="${statDto == null}">
			                <span>已结账单数合计：0单 </span>
						    <span>已结账金额合计：¥ 0.00</span>
							<span>未结账单数合计：0单</span>
							<span>未结账金额合计：¥ 0.00</span>
		            	</c:when>
		            	<c:otherwise>
			                <span>已结账单数合计：${statDto.payCount}单 </span>
						    <span>已结账金额合计：¥ ${statDto.payAmount}</span>
							<span>未结账单数合计：${statDto.unpayCount}单</span>
							<span>未结账金额合计：¥ ${statDto.unpayAmount}</span>
		            	</c:otherwise>
	            	</c:choose>
				 </p>
			    
			   <div class="clearfix seo_first">
			       <c:forEach var="selfCashier" items="${cashierDtoList}" varStatus="status">
			          <div class="column_small_first " action-type="showdesc">
									<div class="column_img_container">
									    <c:choose>
											<c:when test="${selfCashier.memberName == null}">
												<div class="head_pic">
												   <img src="assets/images/head_pic.png">
												</div>
												<div class="long_hair">
													<span>${selfCashier.orderCode}</span>
													<span>顾客：散客(${selfCashier.sex })</span>
												</div>
											</c:when>
											<c:otherwise>
												<div class="head_pic" onclick="selectMemberInfo(${selfCashier.memberId})">
												   <img src="assets/images/head_pic.png">
												</div>
												<div class="long_hair">
													<span>${selfCashier.orderCode}</span>
													<span>顾客：散客(${selfCashier.memberName})</span>
												</div>
											</c:otherwise>
										</c:choose>
										<p class="seo_time">开单时间：${selfCashier.createTime}</p>
									    <p class="seo_time">消费项目：
									        <c:forEach items="${selfCashier.orderDetails }" var="detail">
										         ${detail.projectName }
	                                        </c:forEach>
									    </p>
										<p class="seo_time">开单专员：${selfCashier.operateEmployee.employeeCode } ${selfCashier.operateEmployee.name }</p>
										<ul class="clearfix shop_number">
										  <li>取消 </li>
										  <li class="change_price_money" onclick="changeMoney(${selfCashier.orderId});">更改价格</li>
										  <li>待结账</li>
										</ul>
									</div>
									
									
			         </div>
			       </c:forEach>
				</div>
				
				
			</div>
		</div></div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<%@ include file="/template/memberData.jsp" %>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<script type="text/javascript" src="<%=basePath %>js/cashier/cashier.js?date=<%=new Date().getTime() %>"></script>
<script>
   	var loginType = "${loginType}";
   	
   	/* jQuery('.change_price_money').click(function(){
		   jQuery('.zzc').show();
		   
		});

    jQuery('.money_close_,.money_cancle').click(function(){
	      
		   jQuery('.zzc').hide();
	   }); */
</script>
</body>
</html>