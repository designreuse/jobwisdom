<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Date" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath %>css/money.css" type="text/css" />
<body>

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
										  <li onclick="deleteOrder(this, ${selfCashier.orderId})">取消 </li>
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

<!--删除提示-->
<div class="modal hide" id="deleteOrderModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content confirm">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="deleteOrderModalCancel()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>

            <div class="modal-body confirm-body">
                取消该笔订单将直接删除，确认进行此操作吗？
            </div><!--modal-body-->

            <div class="modal-footer">
                <a class="btn cancel-btn modal-cancel" data-dismiss="modal" onclick="deleteOrderModalCancel()" href="#">我点错了</a>
                <a onclick="rechargeDeleteOrder()" class="btn btn-primary save-btn modal-confirm" data-dismiss="modal" href="#">取消订单</a>
            </div>
        </div>
    </div>
</div>

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