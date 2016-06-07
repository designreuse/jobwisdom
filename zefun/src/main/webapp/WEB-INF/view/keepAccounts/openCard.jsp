<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ include file="/head.jsp" %>
<style>
	 .new-contentinner {
        background-color: #fff;

    }

    .tab-form1 {
        padding:0 0;
        padding-top:20px;
    }
    .n-tab{
        border-right:1px solid #bbbbbb;
    }

    .n-tab li.active {
        border-radius: 0;
        border-bottom: 1px solid #fff;
        background-color: #fff;
    }
    .p-part-first {
        margin-left: 0px;
        float: none;
    }
    .p-part{
        float: none;
        margin-bottom: 10px;
    }
    .kaidan-label {
        width: 70px;
        margin-right: 10px;
        text-align: right;
    }

    .n-tab li {
        height: 45px;
        line-height: 45px;
        border: 1px solid #bbbbbb;
        background-color: #f1f1f1;
        border-right: 0;
    }

    .n-tab-content {
        -webkit-box-shadow:none;
        border: 1px solid #bbbbbb;
        border-top:0;
    }

    .card-main {
        padding: 20px;
    }
    .card-main  .p-part-first {
        float: left;
        width: 33.3%;
    }

    .card-main .p-part {
        display: inline-block;
    }

    .card-main .p-part1 {
        display: inline-block;
        margin-bottom:14px;
    }

    .card-main .p-part:nth-child(1) {
        margin-right:165px;
    }

    .p-part {
        margin-left:0;
    }

    .select-result {
        margin-right:15px;
        margin-bottom: 15px;
    }

    .select-result input {
        background-color: #f1f1f1;
        margin: 0 10px 0 5px;
    }

    .select-result .iconfont {
        margin-top: 2px;

    }


    .n-tab-content .icon-shanchujilu {
        color: #cccccc !important;
    }

    .n-tab-content .icon-shanchujilu:hover {
        color: #86cdcd !important;
        cursor: pointer;
    }

     .w600 {
        width: 650px;
         min-height: 32px;
    }

    .card-main .p-part-first1 {
        float: left;
        width: 20%;
    }

    .n-tab-content {
        min-height:auto;
    }
    .mr55 {
        margin-right: 38px;
        margin-top: 10px;
    }
    .n-tab .active {
        box-shadow: none;
    }
    
    .fl {
        float: left !important;
    }
    
    span.btn.fanhui1 {
	    position:absolute;
	    right:0;
	    margin-right:0;
    }
    
    .member-zilian {
    	position:relative;
    }

</style>
<body>

  <div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
    <!--loading end-->
   <%@ include file="/menu.jsp" %>
   <div class="rightpanel" style="margin-left: 200px;">
      <%@ include file="/top.jsp" %>
      <div class="maincontent">
	    <div class="contentinner kaizhijizhang new-contentinner">
	          <div class="n-tab">
	            <ul>
	                <li onclick="subTabFun(this)" class="active" name = "rechargeName" data-target="#tab2">
	                    <span>充值</span>
	                </li>
	                <li onclick="subTabFun(this)" data-target="#tab1">
	                    <span>开卡</span>
	                </li>
	                <li onclick="subTabFun(this)" data-target="#tab6">
	                        <span>还款</span>
	                </li>
	                <li onclick="subTabFun(this)" data-target="#tab5">
                            <span>赠送</span>
                    </li>
	                <li onclick="subTabFun(this)" data-target="#tab3">
	                        <span>转账</span>
	                </li>
	            </ul>
	            <div class="clearfix"></div>
	        </div>
			
	        <div class="n-tab-content">
	        	<!-- 开卡 -->
	            <div id="tab1" class="target-tab hide">
                  <div class="tab-form1">
	                    <div class="card-title">填写会员资料</div>
	                    <div class="card-main clearfix" name="fillMemberInfo">
	                        <div class="p-part-first" name = "memberTR" selectType = "1">
	                            <label class="kaidan-label" for="">手机号:</label>
	                            <input type="text"  class="w185 jiaodian" name = "phoneNumber" placeholder="*"/>
		                        <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        
		                        <div class="show_search" name = "memberListDIV" style="display: none;">
								    <p>以<i name = "conditionValue">12</i>为条件显示到<i name ="showList">20</i>位顾客 <em><input type="checkbox" onchange="changeAllEnterprise(this)">全店搜索<span>?</span></em><div class="common_close"><img src="<%=basePath %>images/emploee_3.png"></div></p>
								    <div style="height: 400px; overflow: overlay;" name = "memberoverDIV">
								    
								    </div>  
								</div>
	                        </div>
	                        <div class="p-part-first">
	                            <label class="kaidan-label" for="">开卡类型:</label>
	                            <select name="kkLevelId" id="" class="chzn-select w185" onchange="changeMemberLevel(this)">
	                                <c:forEach items="${memberLevelList}" var="memberLevel" varStatus="status">
		                                <option value="${memberLevel.levelId}">${memberLevel.levelName}</option>
		                            </c:forEach>
	                            </select>
	                        </div>
	                        <div class="p-part-first">
	                        	<label class="kaidan-label" for="">推荐会员:</label>
	                        	<select name="openRecommendId" class="chzn-select w185">
	                        		<option value="">暂未上线，后期推出</option>
	                        	</select>
	                            <%-- <label class="kaidan-label" for="">推荐人:</label>
	                            <select name="openRecommendId" class="chzn-select w185">
	                                <option value="">点击选择推荐人</option>
		                            <c:forEach items="${employeeInfoList}" var="employeeInfo" varStatus="status">
		                               <option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode} ${employeeInfo.name}</option>
		                            </c:forEach>
	                            </select> --%>
	                        </div>
	                        <div class="p-part-first">
	                            <label class="kaidan-label" for="">姓名:</label>
	                            <input type="text" name = "name" class="w185 jiaodian" placeholder="*"/>
	                        </div>
	                        <div class="p-part-first">
	                            <label class="kaidan-label" for="">支付密码:</label>
	                            <input type="password" name = "payPassword" class="w185 jiaodian" placeholder="*"/>
	                        </div>
	                        <div class="p-part-first">
	                            <label class="kaidan-label" for="">确认密码:</label>
	                            <input type="password" name = "password" class="w185 jiaodian" placeholder="*"/>
	                        </div>
	                        <div class="p-part-first">
	                            <label class="kaidan-label" for="">性别:</label>
	                            <label class="radio"><input type="radio" class="ml10" name="sex" value="男" checked="checked"/> <span class="ml5">男</span></label>
	                            <label class="radio"><input type="radio" class="m110" name="sex" value="女"/> <span class="ml5">女</span></label>
	                        </div>
	                        <div class="p-part-first">
	                            <label class="ml10" for="">是否接收短信:</label>
	                            <label class="radio"><input type="radio" name="messageType" class="ml10" value="1"><span class="ml5">是</span></label>&nbsp;&nbsp;
	                            <label class="radio"><input type="radio" name="messageType" class="ml10" value="0" checked="checked"><span class="ml5">否</span></label>
	                        </div>
	                        <div class="p-part-first">
	                            <label class="ml10" for="">开卡费用:</label>
		                        <span name= "openType" class="red font-size-18">${memberLevels.sellAmount}</span> 元（不计入卡金余额）
	                        </div>
	                    </div>
	                    <div class="card-main1 clearfix hide" name = "memberTR">
	                        <div class="common_table">   
						  	   <table>
							     <tr>
								   <td rowspan="2"><img src="" name = "memberImg"></td>
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
								   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberSexSpan">男</td>
								   <td name = "memberStoreName">华南美联店</td>
								   <td style="color:#eb4749" name = "memberBalanceGiftmoneyAmountSpan"></td>
								   <td style="color:#eb4749" name = "memberBalanceIntegralSpan"></td>
								   <td style="color:#eb4749" name = "needRefund"></td>
								   <td ><span name = "subAccountNum"></span>张</td>
								 </tr>
								 <input type="hidden" name = "memberId">
							   </table>
						    </div>
	                        <ul class="member-card" name = "subAccountUL">
	                        </ul>
	                        <div class="p-part-first fl">
	                            <label class="kaidan-label" for="">开卡类型:</label>
	                            <select name="kkLevelIdToo" class="chzn-select w185" onchange="changeMemberLevel(this)">
	                                <c:forEach items="${memberLevelList}" var="memberLevel" varStatus="status">
		                                <option value="${memberLevel.levelId}">${memberLevel.levelName}</option>
		                            </c:forEach>
	                            </select>
	                        </div>
	                        <div class="p-part-first fl">
	                            <label class="ml10" for="">开卡费用:</label>
		                        <span name= "openType" class="red font-size-18">${memberLevels.sellAmount}</span> 元（不计入卡金余额）
	                        </div>
	                    </div>
	                    
	                    <div class="card-title">充值金额及支付方式</div>
	                    <div class="card-main clearfix bdb">
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">现金:</label>
	                            <input type="text" name="kkCashAmount" onkeyup="checkNum(this)" class="w85">
	                        </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">银联:</label>
	                            <input type="text" name= "kkUnionpayAmount" class="w85" onkeyup="checkNum(this)">
	                        </div>
	                        <div class="p-part-first1">
	                        <label class="kaidan-label" for="">微信:</label>
	                        <input type="text" name="kkWechatAmount" class="w85" onkeyup="checkNum(this)">
	                    </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">支付宝:</label>
	                            <input type="text" name="kkAlipayAmount" class="w85" onkeyup="checkNum(this)">
	                        </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">挂账:</label>
	                            <input type="text" name="kkDebtAmount" class="w85" onkeyup="checkNum(this)">
	                        </div>
	                    </div>
	                    
	                    <div class="card-title">业绩提成</div>
	                    <div class="card-main clearfix">
	                        <div class="p-part1 ml10 fl">
	                            <label>业绩归属:</label>
	                            <select name="deptChooseType" id="" class="chzn-select w70" onchange="chooseDept(this)">
	                                <option value="1">固定</option>
	                                <option value="2">比例</option>
	                            </select>
	                            <select name="deptSelectValue" id="" class="chzn-select w100" onchange="chooseDeptInfo(this)">
	                                <option value="">选择部门</option>
	                                <c:forEach items="${deptInfoList}" var="deptInfo" varStatus="status">
		                               <option value="${deptInfo.deptId}">${deptInfo.deptName}</option>
		                            </c:forEach>
	                            </select>
	                        </div>
	                        <div class="p-part1 fl w600" name = "deptIdChoose">
	                            
	                        </div>
	                        <div class="p-part1 ml10 fl">
	                            <label>开卡提成:</label>
	                            <select name="commissionType" class="chzn-select w70" onchange="totaiUpdateVL(this)">
	                                <option value="1">固定</option>
	                                <option value="2">比例</option>
	                            </select>
	                            <select name="recommendId" class="chzn-select w100 " onchange="totaiUpdateEmployeeInfo(this)">
	                                <option value="">选择员工</option>
	                                <c:forEach items="${employeeInfoList}" var="employeeInfo" varStatus="status">
		                               <option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode} ${employeeInfo.name}</option>
		                            </c:forEach>
	                            </select>
	                        </div>
	                        <div class="p-part1 fl w600" name = "employeeChoose">
	                        </div>
	                    </div>
	                    
	                    <div class="card-title">赠送设置</div>
	                    <div class="card-main clearfix">
	                        <div class="p-part">
	                            <label class=" kaidan-label" >礼金赠送:</label>
	                            <input type="text" name = "giftmoneyAmount" class="w85"/><span class="percent-symbol">元</span>
	                            <label class=" kaidan-label">分期:</label>
	                            <select name="partType" class="chzn-select w100">
	                                <option value="1">不分期</option>
		                            <option value="3">3个月</option>
		                            <option value="6">6个月</option>
		                            <option value="9">9个月</option>
		                            <option value="12">12个月</option>
		                            <option value="24">24个月</option>
	                            </select>
	                            <label class=" kaidan-label" >过期:</label>
	                            <select name="pastDate" class="chzn-select w100">
	                                <option value="0">不过期</option>
	                                <option value="3">3个月</option>
	                                <option value="6">6个月</option>
	                                <option value="9">9个月</option>
	                                <option value="12">12个月</option>
	                                <option value="24">24个月</option>
	                            </select>
	                        </div>
	                        <div class="p-part">
	                            <label class="kaidan-label" >卡金赠送:</label>
	                            <input type="text" name = "rewardAmount" class="w85"/><span class="percent-symbol">元</span>
	                        </div>
	                    </div>
	
						<div class="clearfix"></div>
	                    <div class="confirm-part ml80">
	                        <button class="btn w100 fr mr55" id = "confirm">保存并收银</button>
	                    </div>
	
	                    </form>
	                </div>
	            </div><!--tab1-->
	            
	            <!-- 充值 -->
	            <div id="tab2" class="target-tab">
	                <div class="tab-form1">
		                <div class="card-title">
	                        	充值账户
	                    </div>
	                  <!--搜索-->
	                    <div class="card-main clearfix" name="fillMemberInfo">
	                        <div class="p-part-first ml10" name = "memberTR" selectType = "1">
	                            <label class="w60" for="">搜索会员:</label>
	                            <input type="text" class="w185" name = "phoneNumber" placeholder="会员手机号">
		                        <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        <div class="show_search" name = "memberListDIV" style="display: none;">
								    <p>以<i name = "conditionValue">12</i>为条件显示到<i name ="showList">20</i>位顾客 <em><input type="checkbox" onchange="changeAllEnterprise(this)">全店搜索<span>?</span></em><div class="common_close"><img src="<%=basePath %>images/emploee_3.png"></div></p>
								    <div style="height: 400px; overflow: overlay;" name = "memberoverDIV">
								    
								    </div>  
								</div>
	                        </div>
	                    </div>
	                    <div class="card-main1 clearfix hide" name = "memberTR">
	                        <div class="common_table">   
						  	   <table>
							     <tr>
								   <td rowspan="2"><img src="" name = "memberImg"></td>
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
								   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberSexSpan">男</td>
								   <td name = "memberStoreName">华南美联店</td>
								   <td style="color:#eb4749" name = "memberBalanceGiftmoneyAmountSpan"></td>
								   <td style="color:#eb4749" name = "memberBalanceIntegralSpan"></td>
								   <td style="color:#eb4749" name = "needRefund"></td>
								   <td ><span name = "subAccountNum"></span>张</td>
								 </tr>
								 <input type="hidden" name = "memberId">
							   </table>
						    </div>
	                        <ul class="member-card" name = "subAccountUL">
	                        </ul>
	                    </div>
	                    
	                    <div class="card-title">充值金额及支付方式</div>
	                    <div class="card-main clearfix bdb">
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" style="margin-left: -10px;" for="">现金:</label>
	                            <input type="text" name="czCashAmount" class="w85">
	                        </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">银联:</label>
	                            <input type="text" name= "czUnionpayAmount" class="w85">
	                        </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">微信:</label>
	                            <input type="text" name="czWechatAmount" class="w85">
	                        </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">支付宝:</label>
	                            <input type="text" name="czAlipayAmount" class="w85">
	                        </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">挂账:</label>
	                            <input type="text" name="czDebtAmount" class="w85">
	                        </div>
	                    </div>
	                    
	                    <div class="card-title">业绩提成
	                    </div>
	                    <div class="card-main clearfix">
	                        <div class="p-part1 fl">
	                            <label >业绩归属:</label>
	                            <select name="deptChooseType" id="" class="chzn-select w70" onchange="chooseDept(this)">
	                                <option value="1">固定</option>
	                                <option value="2">比例</option>
	                            </select>
	                            <select name="deptSelectValue" id="" class="chzn-select w100" onchange="chooseDeptInfo(this)">
	                                <option value="">选择部门</option>
	                                <c:forEach items="${deptInfoList}" var="deptInfo" varStatus="status">
		                               <option value="${deptInfo.deptId}">${deptInfo.deptName}</option>
		                            </c:forEach>
	                            </select>
	                        </div>
	                        <div class="p-part1 fl w600" name = "deptIdChoose">
	                        </div>
	                        <div class="p-part1 fl">
	                            <label >开卡提成:</label>
	                            <select name="commissionType" id="" class="chzn-select w70" onchange="totaiUpdateVL(this)">
	                                <option value="1">固定</option>
	                                <option value="2">比例</option>
	                            </select>
	                            <select name="recommendId" class="chzn-select w100 " onchange="totaiUpdateEmployeeInfo(this)">
	                                <option value="">选择员工</option>
	                                <c:forEach items="${employeeInfoList}" var="employeeInfo" varStatus="status">
		                               <option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode} ${employeeInfo.name}</option>
		                            </c:forEach>
	                            </select>
	                        </div>
	                        <div class="p-part1 fl w600" name = "employeeChoose">
	                        </div>
	                    </div>
	
	                    <div class="card-title">赠送设置</div>
	                    <div class="card-main clearfix">
	                        <div class="p-part">
	                            <label class="kaidan-label" style="margin-left: -10px" >礼金赠送:</label>
	                            <input type="text" name = "czGiftmoneyAmount" class="w85"/><span class="percent-symbol">元</span>
	                            <label class=" kaidan-label">分期:</label>
	                            <select name="czPartType" id="" class="chzn-select w100">
	                                <option value="1">不分期</option>
		                            <option value="3">3个月</option>
		                            <option value="6">6个月</option>
		                            <option value="9">9个月</option>
		                            <option value="12">12个月</option>
		                            <option value="24">24个月</option>
	                            </select>
	                            <label class=" kaidan-label" >过期:</label>
	                            <select name="czPastDate" id="" class="chzn-select w100">
	                                <option value="0">不过期</option>
	                                <option value="3">3个月</option>
	                                <option value="6">6个月</option>
	                                <option value="9">9个月</option>
	                                <option value="12">12个月</option>
	                                <option value="24">24个月</option>
	                            </select>
	                        </div>
	                        <div class="p-part">
	                            <label class=" kaidan-label" >卡金赠送:</label>
	                            <input type="text" name = "czRewardAmount" class="w85"/><span class="percent-symbol">元</span>
	                        </div>
	                    </div>
	                    
	                    <div class="clearfix"></div>
	                    <div class="confirm-part ml80">
	                        <button class="btn w100 fr mr55" onclick="czConfirm(1)">充值并收银</button>
	                    </div>
	
	                    </form>
	            </div>
	
	            <!-- <div class="more-detail-table">
	                <div class="tab-table">
	
	                <table class="table">
	                    <thead>
	                    <tr>
	                        <th>充值时间</th>
	                        <th>会员账号</th>
	                        <th>充值门店</th>
	                        <th>充值金额</th>
	                    </tr>
	                    </thead>
	                    <tbody id = "czTbody">
	                    </tbody>
	                </table>
	                </div>
	            </div> -->
	            
	           </div><!--tab2-->
	
	            <div id="tab3" class="target-tab hide">
	                <div class="tab-form1">
	                    <div class="card-title">
	                        	转出会员
	                    </div>
	                    <!--搜索-->
	                    <div class="card-main clearfix" name="fillMemberInfo">
	                        <div class="p-part-first" name = "memberTR" selectType = "1">
	                            <label class="w60:" for="">转出会员:</label>
	                            <input type="text" name = "phoneNumber" class="w185" placeholder="会员手机号">
		                        <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        
		                        <div class="show_search" name = "memberListDIV" style="display: none;">
								    <p>以<i name = "conditionValue">12</i>为条件显示到<i name ="showList">20</i>位顾客 <em><input type="checkbox" onchange="changeAllEnterprise(this)">全店搜索<span>?</span></em><div class="common_close"><img src="<%=basePath %>images/emploee_3.png"></div></p>
								    <div style="height: 400px; overflow: overlay;" name = "memberoverDIV">
								    
								    </div>  
								</div>
	                        </div>
	                    </div>
	                    <div class="card-main1 clearfix hide" id = "outDIV"  name = "memberTR">
	                        <div class="common_table">   
						  	   <table>
							     <tr>
								   <td rowspan="2"><img src="" name = "memberImg"></td>
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
								   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberSexSpan">男</td>
								   <td name = "memberStoreName">华南美联店</td>
								   <td style="color:#eb4749" name = "memberBalanceGiftmoneyAmountSpan"></td>
								   <td style="color:#eb4749" name = "memberBalanceIntegralSpan"></td>
								   <td style="color:#eb4749" name = "needRefund"></td>
								   <td ><span name = "subAccountNum"></span>张</td>
								 </tr>
								 <input type="hidden" name = "memberId">
							   </table>
						    </div>
	                        <ul class="member-card" name = "subAccountUL">
	
	                        </ul>
	                    </div>
	                    <div class="card-title">
	                       	 转入会员
	                    </div>
	                    
	                    <div class="card-main clearfix bdb ptb" name="fillMemberInfo">
                            <div class="p-part-first" name = "memberTR" selectType = "1">
	                            <label class="w60:" for="">转入会员:</label>
	                            <input type="text" name = "phoneNumber" class="w185" placeholder="会员手机号">
		                        <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        
		                        <div class="show_search" name = "memberListDIV" style="display: none;">
								    <p>以<i name = "conditionValue">12</i>为条件显示到<i name ="showList">20</i>位顾客 <em><input type="checkbox" onchange="changeAllEnterprise(this)">全店搜索<span>?</span></em><div class="common_close"><img src="<%=basePath %>images/emploee_3.png"></div></p>
								    <div style="height: 400px; overflow: overlay;" name = "memberoverDIV">
								    
								    </div>  
								</div>
	                        </div>
	                    </div>
	                    
	                    <div class="card-main1 clearfix bdb ptb hide" id = "inDIV" name = "memberTR">
	                        <div class="common_table">   
						  	   <table>
							     <tr>
								   <td rowspan="2"><img src="" name = "memberImg"></td>
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
								   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberSexSpan">男</td>
								   <td name = "memberStoreName">华南美联店</td>
								   <td style="color:#eb4749" name = "memberBalanceGiftmoneyAmountSpan"></td>
								   <td style="color:#eb4749" name = "memberBalanceIntegralSpan"></td>
								   <td style="color:#eb4749" name = "needRefund"></td>
								   <td ><span name = "subAccountNum"></span>张</td>
								 </tr>
								 <input type="hidden" name = "memberId">
							   </table>
						    </div>
	                        <ul class="member-card" name = "subAccountUL">
	
	                        </ul>
	                    </div>
	
		                  <div class="card-main clearfix bdb ptb">
		                      <div class="p-part-first">
		                        <label class="w60" for="">转入金额:</label>
		                        <input type="text" name = "zzChargeAmount" class="w120 jiaodian">
		                      </div>
		                  </div>
	                  
	                  	<div class="card-main clearfix bdb ptb">
	                        <div class="p-part-first">
	                            <label class="w60" for="">支付密码:</label>
	                            <input type="password" name="zzPassword" class="w120"></span>
	                        </div>
	                    </div>
	                    
	                    <div class="clearfix"></div>
	                    <div class="confirm-part ml80">
	                        <button class="btn w100 fr mr55" onclick="zzConfirm()">保存并收银</button>
	                    </div>
	
	                    </form>
	                </div>
	            </div><!--tab3-->
	            
	            <div id="tab4" class="target-tab hide">
                    <div class="tab-form">
                    	<div class="p-part-first" style="width: 800px" name = "memberTR">
                            <label class="w60:" for="">升级会员:</label>
                            <div style="display: inline-block;" name= "seekTD">
                               <input type="text" class="w185 searchinpput" name = "phoneNumber" placeholder="会员手机号"/>
                               <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        <div class="show_search" name = "memberListDIV" style="display: none;">
								    <p>以<i name = "conditionValue">12</i>为条件显示到<i name ="showList">20</i>位顾客 <em><input type="checkbox" onchange="changeAllEnterprise(this)">全店搜索<span>?</span></em><div class="common_close"><img src="<%=basePath %>images/emploee_3.png"></div></p>
								    <div style="height: 400px; overflow: overlay;" name = "memberoverDIV">
								    
								    </div>  
								</div>
                            </div>
                        </div>
                        <div class="card-main1 clearfix bdb ptb hide" id = "inDIV" name = "memberTR">
	                        <div class="common_table">   
						  	   <table>
							     <tr>
								   <td rowspan="2"><img src="" name = "memberImg"></td>
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
								   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberSexSpan">男</td>
								   <td name = "memberStoreName">华南美联店</td>
								   <td style="color:#eb4749" name = "memberBalanceGiftmoneyAmountSpan"></td>
								   <td style="color:#eb4749" name = "memberBalanceIntegralSpan"></td>
								   <td style="color:#eb4749" name = "needRefund"></td>
								   <td ><span name = "subAccountNum"></span>张</td>
								 </tr>
								 <input type="hidden" name = "memberId">
							   </table>
						    </div>
	                        <ul class="member-card" name = "subAccountUL">
	
	                        </ul>
	                    </div>
                        <div class = "fl">
	                        <div class="p-part-first">
		                        <label class="w60:" for="">升级级别:</label>
	                            <select name = "sjLevelId" class="chzn-select w185" onchange="changeMemberLevel(this)">
	                                <c:forEach items="${memberLevelList}" var="memberLevel" varStatus="status">
	                                    <option value="${memberLevel.levelId}">${memberLevel.levelName}</option>
	                                </c:forEach>
	                            </select>
		                    </div>
		                    
		                    <div class="p-part-first">
		                        <label class="w60:" for="">开卡费用:</label>
		                        <span name= "openType" class="red font-size-18">${memberLevels.sellAmount}</span> 元（不计入卡金余额）
                                <%-- <select name= "openType" id="" class="chzn-select w185">
		                              <option value="2" amountvalue = "${memberLevels.sellAmount}">现金<span class="red">${memberLevels.sellAmount}元</span>购卡</option>
		                        </select> --%>
		                    </div>
		                    
		                    <div class="p-part-first">
		                        <label class="w60:" for="">卡金赠送:</label>
		                        <input type="text" name = "sjRewardAmount" class="w185"/><span class='percent-symbol'>元</span>
		                    </div>
		                    
		                    <div class="p-part-first">
		                      <label class="w60:" for="">现金支付:</label>
		                      <input type="text" name="sjCashAmount" class="w185" onkeyup="checkNum(this)"/>
		                    </div>
		                    
		                    <div class="p-part-first">
		                      <label class="w60:" for="">银联支付:</label>
		                      <input type="text" name= "sjUnionpayAmount" class="w185" onkeyup="checkNum(this)"/>
		                    </div>
		                    
	                        <div class="p-part-first">
		                      <label class="w60:" for="">微信支付:</label>
		                      <input type="text" name="sjWechatAmount" class="w185" onkeyup="checkNum(this)"/>
		                    </div>
	                    
	                        <div class="p-part-first">
		                      <label class="w60:" for="">支付宝付:</label>
		                      <input type="text" name="sjAlipayAmount" class="w185" onkeyup="checkNum(this)"/>
		                    </div>
		                    
		                    <div class="p-part-first">
		                        <label class="w60:" for="">挂账金额:</label>
		                        <input type="text" name="sjDebtAmount" class="w185" onkeyup="checkNum(this)"/>
		                    </div>
		                    
                        </div>
	                    
	                    <div class = "fl">
	                       <div class="p-part" id = "sjDeptInfoDIV">
	                            <label for="">礼金赠送:</label>
	                            <input type="text" name = "sjGiftmoneyAmount" class="w85"/><span class='percent-symbol'>元</span>
	                            <label class="ml10">分期:</label>
                                <select name="sjPartType" class="chzn-select" style="width: 125px">
                                    <option value="1">不分期</option>
                                    <option value="3">3个月</option>
                                    <option value="6">6个月</option>
                                    <option value="9">9个月</option>
                                    <option value="12">12个月</option>
                                    <option value="24">24个月</option>
                                </select>
                                <label class="ml5">过期:</label>
		                        <select name="sjPastDate" class="chzn-select" style="width: 125px">
                                    <option value="0">不过期</option>
                                    <option value="3">3个月</option>
                                    <option value="6">6个月</option>
                                    <option value="9">9个月</option>
                                    <option value="12">12个月</option>
                                    <option value="24">24个月</option>
                                </select>
		                    </div>
		                    
		                    <div class="p-part">
		                        <label class="kaidan-label">业绩归属:</label>
		                        <div style="display:inline-block">
			                        <select name="sjDeptId" class="chzn-select" style="width: 282px;">
			                            <option value="">点击选择部门</option>
			                            <c:forEach items="${deptInfoList}" var="deptInfo" varStatus="status">
			                               <option value="${deptInfo.deptId}">${deptInfo.deptName}</option>
			                            </c:forEach>
			                        </select>
			                    </div>
		                    </div>
		                    
		                    <div class="fl p-part-yg" name = "commissionDIV">
		                        <div class="p-part">
			                        <label class="w60" for="">提成业绩:</label>
			                        <select name="totaiCommissionType" id="" class="chzn-select w85" onchange="totaiUpdateVL(this)">
			                            <option value="1">固定</option>
			                            <option value="2">比例</option>
			                        </select>
			                        
			                        <div class="ml10" style="display:inline-block">
			                            <select name="totaiRecommendId" id="" class="chzn-select ml5" style="width: 170px;" onchange="totaiUpdateEmployeeInfo(this)">
				                            <option value="">选择员工</option>
				                            <c:forEach items="${employeeInfoList}" var="employeeInfo" varStatus="status">
				                               <option value="${employeeInfo.employeeId}">${employeeInfo.employeeCode} ${employeeInfo.name}</option>
				                            </c:forEach>
				                        </select>
			                        </div>
			                    </div>
			                    <div class="yugong-tc">
			                    
			                    </div>
			                </div>
	                    </div>

		                <div class="clearfix"></div>

	                    <div class="confirm-part mt50 ml80">
		                    <button class="btn w100" onclick="sjConfirm()">确认升级</button>
		                </div>
                    </div>
 
                </div><!--tab4-->
                
                <div id="tab5" class="target-tab hide">
	                <div class="tab-form1">
		                <div class="card-title">
	                       	 还款会员
	                    </div>
	                    <div class="card-main clearfix" name="fillMemberInfo">
	                        <div class="p-part-first" name = "memberTR" selectType = "1">
	                            <label class="w60:" for="">搜索会员:</label>
	                            <input type="text" class="w185" name = "phoneNumber" placeholder="会员手机号">
		                        <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        
		                        <div class="show_search" name = "memberListDIV" style="display: none;">
								    <p>以<i name = "conditionValue">12</i>为条件显示到<i name ="showList">20</i>位顾客 <em><input type="checkbox" onchange="changeAllEnterprise(this)">全店搜索<span>?</span></em><div class="common_close"><img src="<%=basePath %>images/emploee_3.png"></div></p>
								    <div style="height: 400px; overflow: overlay;" name = "memberoverDIV">
								    
								    </div>  
								</div>
	                        </div>
	                    </div>
	                    <div class="card-main1 clearfix hide" name = "memberTR">
	                        <div class="common_table">   
						  	   <table>
							     <tr>
								   <td rowspan="2"><img src="" name = "memberImg"></td>
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
								   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberSexSpan">男</td>
								   <td name = "memberStoreName">华南美联店</td>
								   <td style="color:#eb4749" name = "memberBalanceGiftmoneyAmountSpan"></td>
								   <td style="color:#eb4749" name = "memberBalanceIntegralSpan"></td>
								   <td style="color:#eb4749" name = "needRefund"></td>
								   <td ><span name = "subAccountNum"></span>张</td>
								 </tr>
								 <input type="hidden" name = "memberId">
							   </table>
						    </div>
	                        <ul class="member-card" name = "subAccountUL">
	
	                        </ul>
	
	                    </div>
	                    <div class="card-title">
	                        	赠送设置
	                    </div>
	                    <div class="card-main clearfix bdb">
	                        <div class="p-part-first2">
	                            <label class="w60" for="">积分赠送:</label>
	                            <input type="text" name = "zsIntegralAmount" class="w85"><span class="percent-symbol">分</span>
	                        </div>
	                        <div class="p-part-first2">
	                            <label class="w60" >礼金赠送:</label>
	                            <input type="text" name="zsGiftmoneyAmount"  class="w85"/><span class="percent-symbol">元</span>
	                            <label class=" kaidan-label">分期:</label>
	                            <select name="zsPartType" class="chzn-select w100">
	                                <option value="1">不分期</option>
		                            <option value="3">3个月</option>
		                            <option value="6">6个月</option>
		                            <option value="9">9个月</option>
		                            <option value="12">12个月</option>
		                            <option value="24">24个月</option>
	                            </select>
	                            <label class=" kaidan-label" >过期:</label>
	                            <select name="zsPastDate" class="chzn-select w100">
	                                <option value="0">不过期</option>
                                    <option value="3">3个月</option>
                                    <option value="6">6个月</option>
                                    <option value="9">9个月</option>
                                    <option value="12">12个月</option>
                                    <option value="24">24个月</option>
	                            </select>
	                        </div>
	                        <div class="p-part-first2">
	                            <label class="w60" for="">赠优惠劵:</label>
	                            <select class="chzn-select w300" id="couponSelect" multiple="true" data-placeholder="请选择要赠送的优惠券，可多选">
	                                <c:forEach items="${couponList }" var="coupon">
                                       <option value="${coupon.couponId }">${coupon.couponName } -${coupon.couponPrice }元</option>
                                    </c:forEach>
	                            </select>
	                        </div>
	                        <div class="p-part-first2">
	                            <label class="w60" for="">赠送原因:</label>
	                            <input type="text" name="zsReason" class="w300">
	                        </div>
	                    </div>
	                    <div class="clearfix"></div>
	
	                    <div class="confirm-part ml80">
	                        <button class="btn w100 fr mr55" onclick="presentGift()">立即赠送</button>
	                    </div>
	
	                    </form>
	                </div>
	            </div><!--tab5-->
	            
	            <div id="tab6" class="target-tab hide">
	                <div class="tab-form1">
	                    <div class="card-title">
	                          	还款会员
	                    </div>
	                    <!--搜索-->
	                    <div class="card-main clearfix" name="fillMemberInfo">
	                        <div class="p-part-first" name = "memberTR" selectType = "1">
	                            <label class="w60:" for="">搜索会员:</label>
	                            <input type="text" class="w185" name = "phoneNumber" placeholder="会员手机号">
		                        <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
		                        
		                        <div class="show_search" name = "memberListDIV" style="display: none;">
								    <p>以<i name = "conditionValue">12</i>为条件显示到<i name ="showList">20</i>位顾客 <em><input type="checkbox" onchange="changeAllEnterprise(this)">全店搜索<span>?</span></em><div class="common_close"><img src="<%=basePath %>images/emploee_3.png"></div></p>
								    <div style="height: 400px; overflow: overlay;" name = "memberoverDIV">
								    
								    </div>  
								</div>
	                        </div>
	                    </div>
	                    <div class="card-main clearfix hide" name = "memberTR">
	                        <div class="common_table">   
						  	   <table>
							     <tr>
								   <td rowspan="2"><img src="" name = "memberImg"></td>
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
								   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberSexSpan"></td>
								   <td name = "memberStoreName"></td>
								   <td style="color:#eb4749" name = "memberBalanceGiftmoneyAmountSpan"></td>
								   <td style="color:#eb4749" name = "memberBalanceIntegralSpan"></td>
								   <td style="color:#eb4749" name = "needRefund"></td>
								   <td ><span name = "subAccountNum"></span>张</td>
								 </tr>
								 <input type="hidden" name = "memberId">
							   </table>
						    </div>
	                        <div style="height: 40px;line-height: 40px;padding-left: 20px; font-size: 14px">需还款:<span class="red" name="needRefund"></span></div>
	                    </div>
	
	                    <div class="card-title">
	                        	支付方式
	                    </div>
	                    <div class="card-main clearfix bdb">
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" style="margin-left: -10px;" for="">现金:</label>
	                            <input type="text" name="hkCashAmount" class="w85">
	                        </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">银联:</label>
	                            <input type="text" name="hkUnionpayAmount" class="w85">
	                        </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">微信:</label>
	                            <input type="text" name="hkWechatAmount" class="w85">
	                        </div>
	                        <div class="p-part-first1">
	                            <label class="kaidan-label" for="">支付宝:</label>
	                            <input type="text" name="hkAlipayAmount" class="w85">
	                        </div>
	                    </div>
	
	                    <div class="clearfix"></div>
	
	                    <div class="confirm-part ml80">
	                        <button class="btn w100 fr mr55" onclick="hkConfirm()">保存并收银</button>
	                    </div>
	
	                    </form>
	                </div>
	
	            </div><!-- tab6 -->
                
	        </div>
	
	    </div><!--contentinner-->
	</div><!--maincontent-->
	
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
	
	<%@ include file="/template/memberData.jsp" %>
	<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
	<script type="text/javascript">
	   var memberLevelListStr = '${memberLevelListStr}';
	   var memberLevelList = eval("(" + memberLevelListStr + ")");
	   var phoneNum = '${phoneNum}';
	   var clickType = '${clickType}';
	   
	   jQuery(function(){
		    jQuery('.show_search table').click(function(){
			  jQuery(this).addClass('active').siblings('table').removeClass('active')
	
			});
	   })
	</script>	
	<script type="text/javascript" src="<%=basePath %>js/keepAccounts/openCard.js?date=<%=new Date().getTime() %>"></script>
   </div>
     <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
    </div>
  </div>

</body>
</html>