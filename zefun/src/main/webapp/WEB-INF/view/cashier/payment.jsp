<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Date" %>
<%@ include file="/head.jsp" %>
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	
			<div class="maincontent">
				<div class="contentinner">
				<div class="report-title">
			            <ul>
			            	<c:choose>
				            	<c:when test="${statDto == null}">
				            		<li>
					                    <h1>0单</h1>
					                    <p>已结账</p>
					                </li>
					                <li>
					                    <h1>0.00元</h1>
					                    <p>已结账金额</p>
					                </li>
					                <li>
					                    <h1>0单</h1>
					                    <p>未结账</p>
					                </li>
					                <li>
					                    <h1>0.00元</h1>
					                    <p>未结账金额</p>
					                </li>
				            	</c:when>
				            	<c:otherwise>
					            	<li>
					                    <h1>${statDto.payCount}单</h1>
					                    <p>已结账</p>
					                </li>
					                <li>
					                    <h1>${statDto.payAmount}元</h1>
					                    <p>已结账金额</p>
					                </li>
					                <li>
					                    <h1>${statDto.unpayCount}单</h1>
					                    <p>未结账</p>
					                </li>
					                <li>
					                    <h1>${statDto.unpayAmount}元</h1>
					                    <p>未结账金额</p>
					                </li>
				            	</c:otherwise>
			            	</c:choose>
			            </ul>
			        </div>

					<div class="widgetcontent">
						<table class="table table-bordered table-striped">
							<thead class="t-fix">
								<tr>
									<th>单号</th>
									<th>顾客</th>
									<th>消费项目</th>
									<th>当前服务者</th>
									<th>开单时间</th>
									<th>开单人员</th>
									<th>订单金额</th>
									<th></th>
								</tr>
							</thead>

							<tbody id="thead-body">
								<c:forEach var="selfCashier" items="${cashierDtoList}" varStatus="status">
									<c:if test="${status.index == 0 }">
										<tr class="t-table">
									</c:if>
									<c:if test="${status.index != 0 }">
										<tr>
									</c:if>
										<td>
											${selfCashier.orderCode}
										</td>
										<c:choose>
											<c:when test="${selfCashier.memberName == null}">
												<td>
													散客(${selfCashier.sex })&nbsp;
													<span class="iconfa-pencil project-icon" onclick="openMemberInfoDialog(${selfCashier.orderId}, this)"></span>
												</td>
											</c:when>
											<c:otherwise>
												<td class="can-click" data-toggle="modal" data-target="#member-data" onclick="selectMemberInfo(${selfCashier.memberId})">
													${selfCashier.memberName}
												</td>
											</c:otherwise>
										</c:choose>
									    <c:set var="projectName" value="" />
									    <c:forEach items="${selfCashier.orderDetails }" var="detail">
									         <c:set var="projectName" value="${projectName } ${detail.projectName }  "/>
                                        </c:forEach>
								        <td class="wthn150 ellipsis-text" data-toggle="tooltip" data-placement="right" title="${projectName}">
							                ${projectName }
                                        </td>
                                        <td>${selfCashier.serverEmployee.employeeCode } ${selfCashier.serverEmployee.name }</td>    
										<td>${selfCashier.createTime}</td>
										<td>${selfCashier.operateEmployee.employeeCode } ${selfCashier.operateEmployee.name }</td>  
										<td class="red">
											${selfCashier.discountAmount}
										</td>
										<td>
											<div class="jinxingzhong gray-bg mr10" onclick="deleteOrder(this, ${selfCashier.orderId})">
												取消
											</div>
											<c:choose>
												<c:when test="${selfCashier.orderStatus == 1}">
													<div class="jinxingzhong gray-bg" onclick="showCashierDetail(${selfCashier.orderId}, ${selfCashier.orderStatus})">
														进行中
													</div>
												</c:when>
												<c:when test="${selfCashier.orderStatus == 2 || selfCashier.orderStatus == 5}">
													<div class="dengdaiqueren yellow-bg" onclick="showCashierDetail(${selfCashier.orderId}, ${selfCashier.orderStatus})" >
														待结账
													</div>
												</c:when>
											</c:choose>
										</td>
									</tr>
								</c:forEach>
							</tbody>

						</table>

						<br />

						<!--modal-->
					</div>
				</div>
			</div>
		</div></div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<!-- 修改会员信息模态框 -->
<div class="modal hide" id="find-member" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content find-member">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">修改会员</h4>
            </div>
            <div class="modal-body">
                <table class="table">
                    <tbody>
                    <tr>
                        <td colspan="2">
                            <div class="project-list-head">
                                <input id="ipt-search-mamber" type="search" placeholder="会员手机号" class="search-input">
                                <button id="btn-search-member" type="button" class="btn search-button">搜索</button>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>会员名</td>
                        <td id="search-membername"></td>
                    </tr>
                    <tr>
                        <td>等级</td>
                        <td id="search-memberlevel"></td>
                    </tr>
                    <tr>
                        <td>余额</td>
                        <td id="search-memberamount"></td>
                    </tr>
                    <tr>
                        <td>开卡门店</td>
                        <td id="search-memberstore"></td>
                    </tr>
                    <tr>
                        <td>密码确认</td>
                        <td><input id="ipt-member-passwd" type="password" value=""/></td>
                    </tr>
                    </tbody>
                </table>
            </div><!--modal-body-->

            <div class="modal-footer">
                <a class="btn cancel-btn modal-cancel" data-dismiss="modal">取消</a>
                <a id="btn-order-member-submit" class="btn btn-primary save-btn modal-confirm" href="javascript:void(0);">保存</a>
            </div>
        </div>
    </div>
</div>
</div>
<!-- 订单详情模态框 -->
<div class="modal hide" id="cashier" style="width:610px;" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content cashier">
            <div class="modal-header">
            	<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">X</span></button>
                <h5 class="modal-title">确认付款</h5>
            </div>
            <div class="modal-body">
                <div class="widgetcontent">
                	<p class="pay-info">
	                    <span class="fl">
	                        <img src="" class="pay-img" name="payImg">
	                        <span class="desc">
	                            <span class="font-size-16" name="payMemberName"></span>
	                            <span id="selectSubAccount">
	                            </span>
	                        </span>
	                    </span>
	                    <span class="fr mt5">
	                        <span class="font-size-12">订单号：</span>
	                        <span class="font-size-12" name="payOrderCode"></span>
	                    </span>
	                    <span class="clearfix"></span>
	                </p>
	                <table class="table table-bordered">
	                    <thead id="detail-thead-body">
	                    </thead>
	                    <tbody id="tbody-datainfo">
	                    </tbody>
	                </table>
                </div>
                <div><span id="appointOff"></span></div>
                <div class="widgetcontent">
                 <table class="table table-bordered" id="orderAccount" data-discount="0">
                      <tr id="memberAccount" class="hide">
                          <td class="w100">
                              <div class="font-333">卡金支付</div>
                              <div class="font-999">余额：<span id="memberCardBalance">0.00</span></div>
                          </td>
                          <td>
                              <input type="text" id="cardpayAmount" class="input-small" value="0.00">
                          </td>
                          <td class="w100">
                              <div class="font-333">挂帐</div>
                          </td>
                          <td>
                              <input  type="text" id="debtAmount" name="debtAmount" class="input-small" value="0.00">
                          </td>
                      </tr>
                      <tr class="default-member-account">
                          <td class="w100">
                              <div class="font-333">现金支付</div>
                          </td>
                          <td>
                              <input  type="text" id="cashAmount" name="cashpayAmount" class="input-small" value="0.00">
                          </td>
                          <td class="w100">
                              <div class="font-333">银联支付</div>
                          </td>
                          <td>
                              <input  type="text" id="unionpayAmount" name="unionpayAmount" class="input-small" value="0.00">
                          </td>
                      </tr>
                      <tr class="default-member-account">
                          <td class="w100">
                              <div class="font-333">微信支付</div>
                          </td>
                          <td >
                              <input  type="text" id="wechatAmount" name="wechatAmount" class="input-small" value="0.00">
                          </td>
                          <td class="w100">
                              <div class="font-333">支付宝支付</div>
                          </td>
                          <td>
                              <input  type="text" id="alipayAmount" name="alipayAmount" class="input-small" value="0.00">
                          </td>
                      </tr>
                      <tr class="default-member-account">
                          <td class="w100">
                              <div class="font-333">团购支付</div>
                          </td>
                          <td>
                              <input  type="text" id="groupAmount" name="groupAmount" class="input-small" value="0.00">
                          </td>
                          <td colspan="2" style="border-left: 0"></td>
                      </tr>
                  </table>
				</div>
			</div>
			<!--modal body-->
			<div id="div-cashier-footer" class="modal-footer">
				<div class="fl">
				    <span>应收：<span id="totalReceivableMoney" class="font-size-14">0.00</span></span>&nbsp;&nbsp;
				    <!-- <span class="ml30">优惠：<span id="totalOffOmoney">0.00</span></span>&nbsp;&nbsp; -->
				    <span class="ml30">实收：<span id="totalRealMoney" class="shishou red font-size-14">0.00</span></span>
				    <span id="notifyContent">
					    <label class="ml10" for="">发送通知:</label>
	                    <label class="radio"><input type="radio" name="isNotify" class="ml10" value="1" checked="checked" /><span class="ml5">是</span></label>
	                    <label class="radio ml5"><input type="radio" name="isNotify" class="ml10" value="0" /><span class="ml5">否</span></label>
                    </span>
			   	</div>
			    <a class="btn modal-cancel" href="javascript:void(0);" data-dismiss="modal">取消</a>
			   	<a class="btn btn-primary submitButton modal-confirm w80 hide" href="javascript:submitOrderInfo();" >结账</a>
			</div>
        </div>
    </div>
</div>

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
</script>
</body>
</html>