<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/recharge.css"
	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/keepAccounts/openCard.js?date=<%=new Date().getTime()%>"></script>
<script>
	jQuery(
			function() {
				jQuery('.recharge_ul_right_content:gt(0)').hide();
				jQuery('.recharge_ul li')
						.click(
								function() {
									jQuery(this).addClass('active').siblings()
											.removeClass('active');
									jQuery(
											'.recharge_ul_right .recharge_ul_right_content')
											.eq(jQuery(this).index()).show()
											.siblings().hide()
								})
			})
</script>
<style>
.common_table table {
	width: 870px !important
}

.send_manage_content select {
	border-radius: 12px;
	border: 1px solid black;
	width: 100px;
	margin-left: 10px
}
.select-result1 input{background:white}
.achievement_recharge_content li input{height:26px;width:66px}
.achievement_recharge_content_input span em{top:2px}
</style>

<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper"
		style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel"
				style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right">
					<ul class="clearfix recharge_ul">
						<li class="active"><span>充值</span></li>
						<li class=""><span>开卡</span></li>
						<li class=""><span>升级</span></li>
						<li class=""><span>还款</span></li>
						<li class=""><span>赠送</span></li>
						<li class=""><span>转账</span></li>
						<li class=""><span>退卡</span></li>
					</ul>
					<div class="recharge_ul_right">
						<div id="tab1" class="recharge_ul_right_content"
							style="display: block;">
							<div class="recharge_user">
								<p>充值账户</p>
								<div class="seach_user">
									<div class="card-main clearfix" name="fillMemberInfo">
										<div class="p-part-first ml10" name="memberTR" selectType="1">
											<label class="w60" for="">搜索会员:</label> <input type="text"
												class="w185" name="phoneNumber" placeholder="会员手机号">
											<span class="iconfont icon-sousuo ml-30 mt5" name="seekName"></span>
											<div class="show_search" name="memberListDIV"
												style="display: none;">
												<p>
													以<i name="conditionValue">12</i>为条件显示到<i name="showList">20</i>位顾客
													<em><input type="checkbox" name="enterpriseCheck"
														onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
													</em>
												<div class="common_close" onclick="cancleMemberSelect(this)">
													<img src="<%=basePath%>images/emploee_3.png">
												</div>
												</p>
												<div style="height: 400px; overflow: overlay;"
													name="memberoverDIV"></div>
											</div>
										</div>
									</div>
									<div class="card-main1 clearfix hide" name="memberTR"
										style="overflow: hidden">
										<div class="common_table">
											<table>
												<tr>
													<td rowspan="2"><img src="" name="memberImg"></td>
													<td>手机号</td>
													<td>姓名</td>
													<td>性别</td>
													<td>开卡门店</td>
													<td>余额</td>
													<td>礼金</td>
													<td>欠款</td>
													<td>会员卡</td>
													<td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
												</tr>
												<tr>
													<td name="memberPhoneSpan" data-toggle="modal"
														data-target="#member-data" onclick="showMemberModal(this)"></td>
													<td name="memberNameSpan" data-toggle="modal"
														data-target="#member-data" onclick="showMemberModal(this)"></td>
													<td name="memberSexSpan">男</td>
													<td name="memberStoreName">华南美联店</td>
													<td style="color: #eb4749"
														name="memberBalanceGiftmoneyAmountSpan"></td>
													<td style="color: #eb4749" name="memberBalanceIntegralSpan"></td>
													<td style="color: #eb4749" name="needRefund"></td>
													<td><span name="subAccountNum"></span>张</td>
												</tr>
												<input type="hidden" name="memberId">
											</table>
										</div>
										<ul class="member-card" name="subAccountUL">
										</ul>
									</div>
								</div>
							</div>

							<div class="recharge_way">
								<p>充值金额及支付方式</p>
								<div class="recharge_way_content">
									<span> 现金 <input type="text" name="czCashAmount"
										onkeyup="checkNum(this)">
									</span> <span> 银联<input type="text" name="czUnionpayAmount"
										onkeyup="checkNum(this)">
									</span> <span> 微信<input type="text" name="czWechatAmount"
										onkeyup="checkNum(this)">
									</span> <span> 支付宝<input type="text" name="czAlipayAmount"
										onkeyup="checkNum(this)">
									</span> <span> 挂账 <input type="text" name="czDebtAmount"
										onkeyup="checkNum(this)">
									</span>
								</div>
							</div>

							<div class="achievement">
								<p>业绩提成</p>
								<div class="achievement_content">
									<div class="achievement_detail">
										<p>
											部门业绩 <select name="deptChooseType"  onchange="chooseDept(this)">
												<option value="1">固定</option>
												<option value="2">比例</option>
											</select> <span> <select name="deptSelectValue" id=""
												onchange="chooseDeptInfo(this)">
													<option value="">选择部门</option>
													<c:forEach items="${deptInfoList}" var="deptInfo"
														varStatus="status">
														<option value="${deptInfo.deptId}">${deptInfo.deptName}</option>
													</c:forEach>
											</select>
											</span>

										</p>
										<ul   class="achievement_detail_content clearfix">

										</ul>
									</div>

									<div class="achievement_recharge">
										<p>
											充卡业绩 <select name="commissionType" class="chzn-select w70"
												onchange="totaiUpdateVL(this)">
												<option value="1">固定</option>
												<option value="2">比例</option>
											</select> <select name="recommendId" class="chzn-select w100 "
												onchange="totaiUpdateEmployeeInfo(this)">
												<option value="">选择员工</option>
												<c:forEach items="${employeeInfoList}" var="employeeInfo"
													varStatus="status">
													<option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode}
														${employeeInfo.name}</option>
												</c:forEach>
											</select>
										</p>
										<ul class="achievement_recharge_content">

										</ul>
									</div>
								</div>

							</div>

							<div class="send_manage">
								<p>赠送设置</p>
								<div class="send_manage_content">
									<span>礼金赠送<input type="text" name = "czGiftmoneyAmount" ></span>
									<span>分期<select name="czPartType">
											<option value="1">不分期</option>
											<option value="3">3个月</option>
											<option value="6">6个月</option>
											<option value="9">9个月</option>
											<option value="12">12个月</option>
											<option value="24">24个月</option>
									</select></span> <span>过期 <select name="czPastDate">
											<option value="0">不过期</option>
											<option value="3">3个月</option>
											<option value="6">6个月</option>
											<option value="9">9个月</option>
											<option value="12">12个月</option>
											<option value="24">24个月</option>
									</select></span> <span>卡金赠送<input type="text" name="czRewardAmount"></span>


								</div>
							</div>
							<div class="recharge_time">
								<span>开单时间<input name ="createTime1" type="text" id="date"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></span>
									<span>手工单号<input name="orderCodetab1" type="text"></span>
								<button id="confirm" onclick="czConfirm(1)">充值并结账</button>
							</div>

						</div>


						<div id="tab2" class="recharge_ul_right_content"
							style="display: none;">
							<div class="recharge_user">
								<p>填写会员资料</p>
								<div class="seach_user">
									<div class="card-main clearfix" name="fillMemberInfo">
										<div class="p-part-first ml10" name="memberTR" selectType="1">
											<label class="w60" for="">搜索会员:</label> <input type="text"
												class="w185" name="phoneNumber" placeholder="会员手机号">
											<span class="iconfont icon-sousuo ml-30 mt5" name="seekName"></span>
											<div class="show_search" name="memberListDIV"
												style="display: none;">
												<p>
													以<i name="conditionValue">12</i>为条件显示到<i name="showList">20</i>位顾客
													<em><input type="checkbox" name="enterpriseCheck"
														onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
													</em>
												<div class="common_close" onclick="cancleMemberSelect(this)">
													<img src="<%=basePath%>images/emploee_3.png">
												</div>
												</p>
												<div style="height: 400px; overflow: overlay;"
													name="memberoverDIV"></div>
											</div>
										</div>
									</div>
									<div class="card-main1 clearfix hide" name="memberTR"
										style="overflow: hidden">
										<div class="common_table">
											<table>
												<tr>
													<td rowspan="2"><img src="" name="memberImg"></td>
													<td>手机号</td>
													<td>姓名</td>
													<td>性别</td>
													<td>开卡门店</td>
													<td>余额</td>
													<td>礼金</td>
													<td>欠款</td>
													<td>会员卡</td>
													<td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
												</tr>
												<tr>
													<td name="memberPhoneSpan" data-toggle="modal"
														data-target="#member-data" onclick="showMemberModal(this)"></td>
													<td name="memberNameSpan" data-toggle="modal"
														data-target="#member-data" onclick="showMemberModal(this)"></td>
													<td name="memberSexSpan">男</td>
													<td name="memberStoreName">华南美联店</td>
													<td style="color: #eb4749"
														name="memberBalanceGiftmoneyAmountSpan"></td>
													<td style="color: #eb4749" name="memberBalanceIntegralSpan"></td>
													<td style="color: #eb4749" name="needRefund"></td>
													<td><span name="subAccountNum"></span>张</td>
												</tr>
												<input type="hidden" name="memberId">
											</table>
										</div>
										<ul class="member-card" name="subAccountUL">
										</ul>
									</div>


									<div class="write_imformation">
										<p>
											<span><em>姓名</em><input type="text" name="name"
												placeholder="*" /></span> <span><em
												style="display: inline-block; margin-right: 28px">性别</em>
												<select name="sex"><option value="男">男</option><option value="女">女</option></select></span> <span
												style="margin-left: 80px"><em>开卡类型</em>
												<select style="width: 100px; margin-left: 8px" name="kkLevelId"
												onchange="changeMemberLevel(this)">
													<c:forEach items="${memberLevelList}" var="memberLevel"
														varStatus="status">
														<option value="${memberLevel.levelId}">${memberLevel.levelName}</option>
													</c:forEach>
											</select></span> <span style="margin-left: 20px">开卡充值：<i>0元</i><a
												href="javascript:;">（不计入卡金金额）</a></span>
										</p>
										<p>
											<span><em>卡号</em><input type="text"></span> <span><em>支付密码</em>
												<input type="password" name="payPassword" placeholder="*" /></span>
											<span><em>确认密码</em><input type="password"
												name="password" placeholder="*" /></span> <span>短信通知：
												<select name="messageType"><option value="1">是</option><option value="0">否</option></select></span>
										</p>
									</div>
								</div>





							</div>

							<div class="recharge_way">
								<p>充值金额及支付方式</p>
								<div class="recharge_way_content">
									<span> 现金 <input type="text" name="kkCashAmount"
										onkeyup="checkNum(this)">
									</span> <span> 银联<input type="text" name="kkUnionpayAmount"
										onkeyup="checkNum(this)">
									</span> <span> 微信<input type="text" name="kkWechatAmount"
										onkeyup="checkNum(this)">
									</span> <span> 支付宝<input type="text" name="kkAlipayAmount"
										onkeyup="checkNum(this)">
									</span> <span> 挂账 <input type="text" name="kkDebtAmount"
										onkeyup="checkNum(this)">
									</span>
								</div>


							</div>

							<div class="achievement">
								<p>业绩提成</p>
								<div class="achievement_content">
									<div class="achievement_detail">
										<p>
											部门业绩 <select name="deptChooseType"  onchange="chooseDept(this)">
												<option value="1">固定</option>
												<option value="2">比例</option>
											</select> <span> <select name="deptSelectValue" id=""
												onchange="chooseDeptInfo(this)">
													<option value="">选择部门</option>
													<c:forEach items="${deptInfoList}" var="deptInfo"
														varStatus="status">
														<option value="${deptInfo.deptId}">${deptInfo.deptName}</option>
													</c:forEach>
											</select>
											</span>
										</p>

										<ul class="achievement_detail_content clearfix">
										</ul>
									</div>

									<div class="achievement_recharge">
										<p>
											充卡业绩<input type="text"> <select name="recommendId"
												class="chzn-select w100 "
												onchange="totaiUpdateEmployeeInfo(this)">
												<option value="">选择员工</option>
												<c:forEach items="${employeeInfoList}" var="employeeInfo"
													varStatus="status">
													<option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode}
														${employeeInfo.name}</option>
												</c:forEach>
											</select>
										</p>
										<ul class="achievement_recharge_content">
										</ul>
									</div>
								</div>

							</div>

							<div class="send_manage">
								<p>赠送设置</p>
								<div class="send_manage_content">
									<span>礼金赠送 <input type="text" name="giftmoneyAmount" /></span>
									<span>分期 <select name="partType">
											<option value="1">不分期</option>
											<option value="3">3个月</option>
											<option value="6">6个月</option>
											<option value="9">9个月</option>
											<option value="12">12个月</option>
											<option value="24">24个月</option>
									</select></span> <span>过期 <select name="pastDate">
											<option value="0">不过期</option>
											<option value="3">3个月</option>
											<option value="6">6个月</option>
											<option value="9">9个月</option>
											<option value="12">12个月</option>
											<option value="24">24个月</option>
									</select></span> <span>卡金赠送 <input type="text" name="rewardAmount" /></span>
								</div>
							</div>
							<div class="recharge_time">
								<span>开单时间<input type="text" id="date" name="createTime2" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></span>
								<span>手工单号<input name="orderCodetab2" type="text"></span>
								<button id="confirm" onclick="save(this)">充值并结账</button>
								
							</div>
                       </div>

						

						<div id="tab3" class="recharge_ul_right_content"


							style="display: none;">
							<div class="recharge_user">
								<p>升级账户</p>
								<div class="seach_user">
									<div class="seach_user">
										<div class="card-main clearfix" name="fillMemberInfo">
											<div class="p-part-first ml10" name="memberTR" selectType="1">
												<label class="w60" for="">搜索会员:</label> <input type="text"
													class="w185" name="phoneNumber" placeholder="会员手机号">
												<span class="iconfont icon-sousuo ml-30 mt5" name="seekName"></span>
												<div class="show_search" name="memberListDIV"
													style="display: none;">
													<p>
														以<i name="conditionValue">12</i>为条件显示到<i name="showList">20</i>位顾客
														<em><input type="checkbox" name="enterpriseCheck"
															onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
														</em>
													<div class="common_close"
														onclick="cancleMemberSelect(this)">
														<img src="<%=basePath%>images/emploee_3.png">
													</div>
													</p>
													<div style="height: 400px; overflow: overlay;"
														name="memberoverDIV"></div>
												</div>
											</div>
										</div>
										<div class="card-main1 clearfix hide" name="memberTR"
											style="overflow: hidden">
											<div class="common_table">
												<table>
													<tr>
														<td rowspan="2"><img src="" name="memberImg"></td>
														<td>手机号</td>
														<td>姓名</td>
														<td>性别</td>
														<td>开卡门店</td>
														<td>余额</td>
														<td>礼金</td>
														<td>欠款</td>
														<td>会员卡</td>
														<td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
													</tr>
													<tr>
														<td name="memberPhoneSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberNameSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberSexSpan">男</td>
														<td name="memberStoreName">华南美联店</td>
														<td style="color: #eb4749"
															name="memberBalanceGiftmoneyAmountSpan"></td>
														<td style="color: #eb4749"
															name="memberBalanceIntegralSpan"></td>
														<td style="color: #eb4749" name="needRefund"></td>
														<td><span name="subAccountNum"></span>张</td>
													</tr>
													<input type="hidden" name="memberId">
												</table>
											</div>
											<ul class="member-card" name="subAccountUL">
											</ul>
										</div>
									</div>
								</div>
							</div>

							<div class="upgrade_vip">
								<p>升级会员</p>
								<div class="upgrade_vip_content">
									<select><option></option></select>升级费用：<em>0元</em><i>(不计入卡金金额）</i>
								</div>
							</div>

							<div class="recharge_way">
								<p>充值金额及支付方式</p>
								<div class="recharge_way_content">
									<span> 现金 <input type="text" name="kkCashAmount"
										onkeyup="checkNum(this)">
									</span> <span> 银联<input type="text" name="kkUnionpayAmount"
										onkeyup="checkNum(this)">
									</span> <span> 微信<input type="text" name="kkWechatAmount"
										onkeyup="checkNum(this)">
									</span> <span> 支付宝<input type="text" name="kkAlipayAmount"
										onkeyup="checkNum(this)">
									</span> <span> 挂账 <input type="text" name="kkDebtAmount"
										onkeyup="checkNum(this)">
									</span>
								</div>
							</div>

							<div class="achievement">
								<p>业绩提成</p>
								<div class="achievement_content">
									<div class="achievement_detail">
										<p>
											部门业绩 <select name="deptChooseType"  onchange="chooseDept(this)">
												<option value="1">固定</option>
												<option value="2">比例</option>
											</select> <span> <select name="deptSelectValue" id=""
												onchange="chooseDeptInfo(this)">
													<option value="">选择部门</option>
													<c:forEach items="${deptInfoList}" var="deptInfo"
														varStatus="status">
														<option value="${deptInfo.deptId}">${deptInfo.deptName}</option>
													</c:forEach>
											</select>
											</span>

										</p>
										<ul class="achievement_detail_content clearfix">

										</ul>
									</div>

									<div class="achievement_recharge">
										<p>
											充卡业绩<input type="text"><select><option>选择员工</option></select>
										</p>
										<ul class="achievement_recharge_content">
										</ul>
									</div>
								</div>

							</div>

							<div class="send_manage">
								<p>赠送设置</p>
								<div class="send_manage_content">
									<span>礼金赠送<input type="text"></span> <span>分期<input
										type="text"></span> <span>过期<input type="text"></span>
									<span>卡金赠送<input type="text"></span>
								</div>
							</div>
							<div class="recharge_time">
								<span>开单时间<input type="text" id="date"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></span><span>手工单号<input
									type="text"></span>
								<button onclick="save(this)">充值并结账</button>
							</div>

						</div>

					
						<div id="tab4" class="recharge_ul_right_content"
							style="display: none;">
							<div class="recharge_user">
								<p>还款会员</p>
								<div class="seach_user">
									<div class="seach_user">
										<div class="card-main clearfix" name="fillMemberInfo">
											<div class="p-part-first ml10" name="memberTR" selectType="1">
												<label class="w60" for="">搜索会员:</label> <input type="text"
													class="w185" name="phoneNumber" placeholder="会员手机号">
												<span class="iconfont icon-sousuo ml-30 mt5" name="seekName"></span>
												<div class="show_search" name="memberListDIV"
													style="display: none;">
													<p>
														以<i name="conditionValue">12</i>为条件显示到<i name="showList">20</i>位顾客
														<em><input type="checkbox" name="enterpriseCheck"
															onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
														</em>
													<div class="common_close"
														onclick="cancleMemberSelect(this)">
														<img src="<%=basePath%>images/emploee_3.png">
													</div>
													</p>
													<div style="height: 400px; overflow: overlay;"
														name="memberoverDIV"></div>
												</div>
											</div>
										</div>
										<div class="card-main1 clearfix hide" name="memberTR"
											style="overflow: hidden">
											<div class="common_table">
												<table>
													<tr>
														<td rowspan="2"><img src="" name="memberImg"></td>
														<td>手机号</td>
														<td>姓名</td>
														<td>性别</td>
														<td>开卡门店</td>
														<td>余额</td>
														<td>礼金</td>
														<td>欠款</td>
														<td>会员卡</td>
														<td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
													</tr>
													<tr>
														<td name="memberPhoneSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberNameSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberSexSpan">男</td>
														<td name="memberStoreName">华南美联店</td>
														<td style="color: #eb4749"
															name="memberBalanceGiftmoneyAmountSpan"></td>
														<td style="color: #eb4749"
															name="memberBalanceIntegralSpan"></td>
														<td style="color: #eb4749" name="needRefund"></td>
														<td><span name="subAccountNum"></span>张</td>
													</tr>
													<input type="hidden" name="memberId">
												</table>
											</div>
											<ul class="member-card" name="subAccountUL">
											</ul>
										</div>
									</div>
								</div>
							</div>

							<div class="recharge_way">
								<p>充值金额及支付方式</p>
								<div class="recharge_way_content">
									<span> 现金 <input type="text" name="hkCashAmount"
										onkeyup="checkNum(this)">
									</span> 
									<span> 银联<input type="text" name="hkUnionpayAmount"
										onkeyup="checkNum(this)">
									</span> 
									<span> 微信<input type="text" name="hkWechatAmount"
										onkeyup="checkNum(this)">
									</span> 
									<span> 支付宝<input type="text" name="hkAlipayAmount"
										onkeyup="checkNum(this)">
									</span> 
								</div>
							</div>

							<div class="recharge_time">
								<span>开单时间<input type="text" id="date" name="createTime4" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></span>
								<span>手工单号<input name="orderCodetab4" type="text"></span>
								<button onclick="hkConfirm()">充值并结账</button>
							</div>

						</div>

						<div id="tab5" class="recharge_ul_right_content"
							style="display: none;">
							<div class="recharge_user">
								<p>赠送会员</p>
								<div class="seach_user">
									<div class="seach_user">
										<div class="card-main clearfix" name="fillMemberInfo">
											<div class="p-part-first ml10" name="memberTR" selectType="1">
												<label class="w60" for="">搜索会员:</label> <input type="text"
													class="w185" name="phoneNumber" placeholder="会员手机号">
												<span class="iconfont icon-sousuo ml-30 mt5" name="seekName"></span>
												<div class="show_search" name="memberListDIV"
													style="display: none;">
													<p>
														以<i name="conditionValue">12</i>为条件显示到<i name="showList">20</i>位顾客
														<em><input type="checkbox" name="enterpriseCheck"
															onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
														</em>
													<div class="common_close"
														onclick="cancleMemberSelect(this)">
														<img src="<%=basePath%>images/emploee_3.png">
													</div>
													</p>
													<div style="height: 400px; overflow: overlay;"
														name="memberoverDIV"></div>
												</div>
											</div>
										</div>
										<div class="card-main1 clearfix hide" name="memberTR"
											style="overflow: hidden">
											<div class="common_table">
												<table>
													<tr>
														<td rowspan="2"><img src="" name="memberImg"></td>
														<td>手机号</td>
														<td>姓名</td>
														<td>性别</td>
														<td>开卡门店</td>
														<td>余额</td>
														<td>礼金</td>
														<td>欠款</td>
														<td>会员卡</td>
														<td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
													</tr>
													<tr>
														<td name="memberPhoneSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberNameSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberSexSpan">男</td>
														<td name="memberStoreName">华南美联店</td>
														<td style="color: #eb4749"
															name="memberBalanceGiftmoneyAmountSpan"></td>
														<td style="color: #eb4749"
															name="memberBalanceIntegralSpan"></td>
														<td style="color: #eb4749" name="needRefund"></td>
														<td><span name="subAccountNum"></span>张</td>
													</tr>
													<input type="hidden" name="memberId">
												</table>
											</div>
											<ul class="member-card" name="subAccountUL">
											</ul>
										</div>
									</div>
								</div>
							</div>

							<div class="send_manage_">
								<p>赠送设置</p>
								<div class="send_manage_content_">
									<p>
										积分赠送<input type="text" name="zsIntegralAmount"><em>分</em>
									</p>
									<p>
										<span>礼金赠送 <input type="text" name="zsGiftmoneyAmount" /><em>元</em></span>
										<span> <select name="zsPartType">
												<option value="1">不分期</option>
												<option value="3">3个月</option>
												<option value="6">6个月</option>
												<option value="9">9个月</option>
												<option value="12">12个月</option>
												<option value="24">24个月</option>
										</select></span> <span>过期 <select name="zsPastDate">
												<option value="0">不过期</option>
												<option value="3">3个月</option>
												<option value="6">6个月</option>
												<option value="9">9个月</option>
												<option value="12">12个月</option>
												<option value="24">24个月</option>
										</select></span>
									</p>
									<p>
										<span>赠优惠券 <select style="width: 280px"
											id="couponSelect">
												<c:forEach items="${couponList }" var="coupon">
													<option value="${coupon.couponId }">${coupon.couponName }
														-${coupon.couponPrice }元</option>
												</c:forEach>
										</select> <input type="text" style="width: 60px" name="numberCoupon"><em>张</em>
											<button onclick="addCoupon(this)">新增</button>
										</span>
									</p>

									<ul  name ="coupon" class="add_recharge clearfix">
									</ul>

									<div class="send_reason">
										赠送原因<input type="text" name="zsReason">
									</div>
								</div>
							</div>
							<div class="send_button">
								<button onclick="presentGift()">立刻赠送</button>
							</div>

						</div>

						<div id="tab6" class="recharge_ul_right_content"
							style="display: none;">
							<div class="recharge_user">
								<p>转出会员</p>
								<div class="seach_user">
									<div class="seach_user">
										<div class="card-main clearfix" name="fillMemberInfo">
											<div class="p-part-first ml10" name="memberTR" selectType="1">
												<label class="w60" for="">搜索会员:</label> <input type="text"
													class="w185" name="phoneNumber" placeholder="会员手机号">
												<span class="iconfont icon-sousuo ml-30 mt5" name="seekName"></span>
												<div class="show_search" name="memberListDIV"
													style="display: none;">
													<p>
														以<i name="conditionValue">12</i>为条件显示到<i name="showList">20</i>位顾客
														<em><input type="checkbox" name="enterpriseCheck"
															onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
														</em>
													<div class="common_close"
														onclick="cancleMemberSelect(this)">
														<img src="<%=basePath%>images/emploee_3.png">
													</div>
													</p>
													<div style="height: 400px; overflow: overlay;"
														name="memberoverDIV"></div>
												</div>
											</div>
										</div>
										<div class="card-main1 clearfix hide" id = "outDIV" name="memberTR"
											style="overflow: hidden">
											<div class="common_table">
												<table>
													<tr>
														<td rowspan="2"><img src="" name="memberImg"></td>
														<td>手机号</td>
														<td>姓名</td>
														<td>性别</td>
														<td>开卡门店</td>
														<td>余额</td>
														<td>礼金</td>
														<td>欠款</td>
														<td>会员卡</td>
														<td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
													</tr>
													<tr>
														<td name="memberPhoneSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberNameSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberSexSpan">男</td>
														<td name="memberStoreName">华南美联店</td>
														<td style="color: #eb4749"
															name="memberBalanceGiftmoneyAmountSpan"></td>
														<td style="color: #eb4749"
															name="memberBalanceIntegralSpan"></td>
														<td style="color: #eb4749" name="needRefund"></td>
														<td><span name="subAccountNum"></span>张</td>
													</tr>
													<input type="hidden" name="memberId">
												</table>
											</div>
											<ul class="member-card" name="subAccountUL">
											</ul>
										</div>
									</div>
								</div>
							</div>

							<div class="recharge_user" style="margin: 20px 0">
								<p>转入会员</p>
								<div class="seach_user">
									<div class="seach_user">
										<div class="card-main clearfix" name="fillMemberInfo">
											<div class="p-part-first ml10" name="memberTR" selectType="1">
												<label class="w60" for="">搜索会员:</label> <input type="text"
													class="w185" name="phoneNumber" placeholder="会员手机号">
												<span class="iconfont icon-sousuo ml-30 mt5" name="seekName"></span>
												<div class="show_search" name="memberListDIV"
													style="display: none;">
													<p>
														以<i name="conditionValue">12</i>为条件显示到<i name="showList">20</i>位顾客
														<em><input type="checkbox" name="enterpriseCheck"
															onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
														</em>
													<div class="common_close"
														onclick="cancleMemberSelect(this)">
														<img src="<%=basePath%>images/emploee_3.png">
													</div>
													</p>
													<div style="height: 400px; overflow: overlay;"
														name="memberoverDIV"></div>
												</div>
											</div>
										</div>
										<div class="card-main1 clearfix hide" id = "inDIV" name="memberTR"
											style="overflow: hidden">
											<div class="common_table">
												<table>
													<tr>
														<td rowspan="2"><img src="" name="memberImg"></td>
														<td>手机号</td>
														<td>姓名</td>
														<td>性别</td>
														<td>开卡门店</td>
														<td>余额</td>
														<td>礼金</td>
														<td>欠款</td>
														<td>会员卡</td>
														<td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
													</tr>
													<tr>
														<td name="memberPhoneSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberNameSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberSexSpan">男</td>
														<td name="memberStoreName">华南美联店</td>
														<td style="color: #eb4749"
															name="memberBalanceGiftmoneyAmountSpan"></td>
														<td style="color: #eb4749"
															name="memberBalanceIntegralSpan"></td>
														<td style="color: #eb4749" name="needRefund"></td>
														<td><span name="subAccountNum"></span>张</td>
													</tr>
													<input type="hidden" name="memberId">
												</table>
											</div>
											<ul class="member-card" name="subAccountUL">
											</ul>
										</div>
									</div>
								</div>
							</div>

							<div class="recharge_user">
								<p>转账金额</p>
								<div class="seach_user_">
									<input name = "zzChargeAmount" type="text"><em>元</em>
									<button onclick="zzConfirm()">确认金额</button>
								</div>

							</div>

						</div>

						<div id="tab7" class="recharge_ul_right_content"
							style="display: none;">
							<div class="recharge_user">
								<p>转出会员</p>
								<div class="seach_user">
									<div class="seach_user">
										<div class="card-main clearfix" name="fillMemberInfo">
											<div class="p-part-first ml10" name="memberTR" selectType="1">
												<label class="w60" for="">搜索会员:</label> <input type="text"
													class="w185" name="phoneNumber" placeholder="会员手机号">
												<span class="iconfont icon-sousuo ml-30 mt5" name="seekName"></span>
												<div class="show_search" name="memberListDIV"
													style="display: none;">
													<p>
														以<i name="conditionValue">12</i>为条件显示到<i name="showList">20</i>位顾客
														<em><input type="checkbox" name="enterpriseCheck"
															onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
														</em>
													<div class="common_close"
														onclick="cancleMemberSelect(this)">
														<img src="<%=basePath%>images/emploee_3.png">
													</div>
													</p>
													<div style="height: 400px; overflow: overlay;"
														name="memberoverDIV"></div>
												</div>
											</div>
										</div>
										<div class="card-main1 clearfix hide" name="memberTR"
											style="overflow: hidden">
											<div class="common_table">
												<table>
													<tr>
														<td rowspan="2"><img src="" name="memberImg"></td>
														<td>手机号</td>
														<td>姓名</td>
														<td>性别</td>
														<td>开卡门店</td>
														<td>余额</td>
														<td>礼金</td>
														<td>欠款</td>
														<td>会员卡</td>
														<td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
													</tr>
													<tr>
														<td name="memberPhoneSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberNameSpan" data-toggle="modal"
															data-target="#member-data"
															onclick="showMemberModal(this)"></td>
														<td name="memberSexSpan">男</td>
														<td name="memberStoreName">华南美联店</td>
														<td style="color: #eb4749"
															name="memberBalanceGiftmoneyAmountSpan"></td>
														<td style="color: #eb4749"
															name="memberBalanceIntegralSpan"></td>
														<td style="color: #eb4749" name="needRefund"></td>
														<td><span name="subAccountNum"></span>张</td>
													</tr>
													<input type="hidden" name="memberId">
												</table>
											</div>
											<ul class="member-card" name="subAccountUL">
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="quit_vip">
								<table>
									<tbody>
										<tr>
											<td rowspan="2"><img
												src="<%=basePath%>images/common_img.png"></td>
											<td>姓名</td>
											<td>性别</td>
											<td>手机号</td>
											<td>开卡门店</td>
											<td>余额</td>
											<td>礼金</td>
											<td>欠款</td>
											<td>会员卡</td>
											<td rowspan="2"><button>重新搜索</button></td>
										</tr>
										<tr>
											<td>哎呦喂啊</td>
											<td>男</td>
											<td>1323123123</td>
											<td>华南美联店</td>
											<td style="color: #eb4749">12345.0</td>
											<td style="color: #eb4749">21351</td>
											<td style="color: #eb4749">21021</td>
											<td>5张</td>
										</tr>
									</tbody>
								</table>
								<ul class="recharge_card clearfix">
									<li>
										<p>
											<em>VIP</em>啊啊啊啊啊啊啊啊
										</p>
										<div class="item_card">
											<p>项目折扣：10折</p>
											<p>商品折扣：10折</p>
										</div>
										<div class="rest_money">卡上余额：30300元</div>
									</li>

									<li>
										<p>
											<em>VIP</em>啊啊啊啊啊啊啊啊
										</p>
										<div class="item_card">
											<p>项目折扣：10折</p>
											<p>商品折扣：10折</p>
										</div>
										<div class="rest_money">卡上余额：30300元</div>
									</li>

									<li>
										<p>
											<em>VIP</em>啊啊啊啊啊啊啊啊
										</p>
										<div class="item_card">
											<p>项目折扣：10折</p>
											<p>商品折扣：10折</p>
										</div>
										<div class="rest_money">卡上余额：30300元</div>
									</li>

									<li>
										<p>
											<em>VIP</em>啊啊啊啊啊啊啊啊
										</p>
										<div class="item_card">
											<p>项目折扣：10折</p>
											<p>商品折扣：10折</p>
										</div>
										<div class="rest_money">卡上余额：30300元</div>
									</li>

									<li>
										<p>
											<em>VIP</em>啊啊啊啊啊啊啊啊
										</p>
										<div class="item_card">
											<p>项目折扣：10折</p>
											<p>商品折扣：10折</p>
										</div>
										<div class="rest_money">卡上余额：30300元</div>
									</li>
								</ul>

								<div class="quit_money">
									<span>支出类型<select><option></option></select></span> <span>退出金额<input
										type="text"><em>元</em></span>
									<button onclick="save(this)">退卡</button>
								</div>
							</div>
						</div>


					</div>
				</div>

			</div>
		</div>
	</div>
	
		<!--充值提示-->
	<div class="modal hide" id="czModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content confirm">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" onclick="czCancel()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">提示</h4>
	            </div>
	
	            <div class="modal-body confirm-body">
	                                            会员充值没有达到最低充值金额呦！是否确定本次操作？
	            </div><!--modal-body-->
	
	            <div class="modal-footer">
	                <a class="btn cancel-btn modal-cancel" data-dismiss="modal" onclick="czCancel()" href="#">取消</a>
	                <a onclick="rechargeCard(2)" class="btn btn-primary save-btn modal-confirm" data-dismiss="modal" href="#">保存</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!--提示-->
	<div class="modal hide" id="sjModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content confirm">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">提示</h4>
	            </div>
	
	            <div class="modal-body confirm-body">
	                                            会员升级没有输入金额呦！是否确定本次操作？
	            </div><!--modal-body-->
	
	            <div class="modal-footer">
	                <a class="btn cancel-btn modal-cancel" data-dismiss="modal"  href="#">取消</a>
	                <a onclick="pastDateConfirm(2)" class="btn btn-primary save-btn modal-confirm" data-dismiss="modal" href="#">保存</a>
	            </div>
	        </div>
	    </div>
	</div>
	<%@ include file="/template/memberData.jsp"%>
</body>


<script>
	//dialog('msg');
</script>
</html>