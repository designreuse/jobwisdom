<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员新增</title>
<%@include file="/css.jsp" %>
<body>
	<%@include file="/head.jsp" %>
	<div class="container">
        <%@include file="/sidebar.jsp" %>
        <div id="content" class="fl">
        	<!--二级菜单-->
		    <div class="content-submenu">
		        <ul class="submunu clearfix">
		            <li>
		                <a href="">
		                    <span>散客收银</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>会员消费</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>新增会员</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>自助账单</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>账单查询</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>顾客预约</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>动态轮牌</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>收支记账</span>
		                </a>
		
		            </li>
		            <li class="active">
		                <a href="project_setting.html">
		                    <span>项目设置</span>
		                </a>
		            </li>
		        </ul>
		    </div>
        	
        	<!--内容区-->
        	
        	<div class="huiyuanxinxi">
	            <div class="title">会员详细信息</div>
	            <div class="form-staff">
	                <form class="saveMembresForm">
	                    <!--左边表单-->
	                    <div class="form-staff-left">
	                        <!--系统卡号-->
	                        <div class="f-group">
	                            <div class="f-whole">
	                                <label class="f-label" for="">系统卡号</label>
	                                <input type="text" class="f-whole-input" readonly="readonly" name="systemCardNo" id="systemCardNo" value="${members.systemCardNo }"/>
	                            </div>
	                        </div>
	                        <!--实体卡号-->
	                        <div class="f-group">
	                            <div class="f-whole">
	                                <label class="f-label" for="">实体卡号</label>
	                                <input type="text" class="f-whole-input" name="entityCardNo" value="${members.entityCardNo }"/>
	                            </div>
	                        </div>
	                        <!--手机号码-->
	                        <div class="f-group">
	                            <div class="f-whole">
	                                <label class="f-label" for="">手机号码</label>
	                                <input type="text" class="f-whole-input" name="membersMobile" value="${members.membersMobile }" onblur="validMobile(this);"/>
	                            </div>
	                        </div>
	                        <!--卡类型-->
	                        <div class="f-group two-part">
	                            <div class="f-left ">
	                                <label class="f-label" for="">会员卡类型</label>
	                                <select name="membersType" class="f-part-input" onchange="membersTypeSelect(this);">
	                                	<option></option>
	                                	<c:if test="${not empty members.membersType }">
	                                		<option <c:if test="${members.membersType == 1}">selected="selected"</c:if> value="1">储值卡</option>
	                                    	<option <c:if test="${members.membersType == 2}">selected="selected"</c:if> value="2">疗程卡</option>
	                                	</c:if>
	                                	<c:if test="${empty members.membersType }">
	                                		<option value="1">储值卡</option>
	                                    	<option value="2">疗程卡</option>
	                                	</c:if>
	                                </select>
	                            </div>
	                            <div class="f-right">
	                                <label class="f-label" for="">类型</label>
	                                <select name="membersTypeName" class="f-part-input" id="cardNameId" onchange="typeSelect(this);">
<%-- 	                                	<c:forEach var="membersCardName" items="${membersCardNameList}" varStatus="status"> --%>
<%--                                				<option value="${membersCardName.cardNameId }">${membersCardName.cardName }</option> --%>
<%--                                			</c:forEach> --%>
	                                </select>
	                            </div>
	                        </div>
	                        <!--支付密码-->
	                        <div class="f-group">
	                            <div class="f-whole">
	                                <label class="f-label" for="">支付密码</label>
	                                <input type="password" class="f-whole-input" placeholder="支付密码" name="payPwd" id="payPwd" value="${members.payPwd }"/>
	                            </div>
	                        </div>
	                        <!--确认密码-->
	                        <div class="f-group">
	                            <div class="f-whole">
	                                <label class="f-label" for="">确认密码</label>
	                                <input type="password" class="f-whole-input" placeholder="确认密码" name="payPwdConfirm" id="payPwdConfirm" value="${members.payPwd }" onblur="confirmPwd(this);"/>
	                            </div>
	                        </div>
	                        <!--姓名-->
	                        <div class="f-group">
	                            <div class="f-whole">
	                                <label class="f-label" for="">姓名</label>
	                                <input type="text" class="f-whole-input" name="membersName" value="${members.membersName }"/>
	                            </div>
	                        </div>
	                        <!--性别生日-->
	                        <div class="f-group two-part">
	                            <div class="f-left">
	                                <label class="f-label" for="">性别</label>
	                                <select name="sex" class="f-part-input">
	                                	<c:if test="${not empty members.sex }">
	                                		<option <c:if test="${members.sex == 1}">selected="selected"</c:if> value="1">男</option>
	                                    	<option <c:if test="${members.sex == 0}">selected="selected"</c:if> value="0">女</option>
	                                	</c:if>
	                                    <c:if test="${empty members.sex }">
	                                		<option value="1">男</option>
	                                    	<option value="0">女</option>
	                                	</c:if>
	                                </select>
	                            </div>
	                            <div class="f-right">
	                                <label class="f-label" for="">生日</label>
	                                <input type="text" class="f-part-input" name="birthday" value="${members.birthday }"/>
	                            </div>
	                        </div>
	                        <!--开卡方式-->
	                        <div class="f-group checkbox-select" >
	                            <div class="f-whole select-tag" id="topupCardDiv">
	                                <label class="f-label" for="">开卡方式</label>
	                                <input type="checkbox" class="check-all" name="openCardWay" <c:if test="${members.openCardWay == 1}">checked="checked"</c:if> value="1"/>
	                                <span></span>
	                               		 充值开卡
	                               	<input type="text" class="show-input" id="topupCard"/>
	                            </div>
	                        </div>
	                        <div class="f-group checkbox-select" id="cashCardDiv">
	                            <div class="f-whole select-tag">
	                                <label class="f-label" for=""></label>
	                                <input type="checkbox" class="check-all" name="openCardWay" <c:if test="${members.openCardWay == 2}">checked="checked"</c:if> value="2"/>
	                                <span></span>
	                                	现金购卡
	                                <input type="text" class="show-input" id="cashCard"/>
	                            </div>
	                        </div>
	
	                    </div>
	
	                    <!--右边菜单-->
	                    <div class="form-staff-left" style="margin-left: 58px;">
	                        <!--直接推荐人-->
	                        <div class="f-group">
	                            <div class="f-whole">
	                                <label class="f-label" for="">直接推荐人</label>
	                                <div class="dropdown" supdiv = "supdiv" style="display: inline;">
                                        <a data-toggle="dropdown" class="dropdown-toggle" id="sponsorDropdown" supname = "supname">
                                           <input type="text" name="directReferrerName" dropdowninput = "dropdowninput" class="border-left-none" style="width: 280px;color: black;">
                                           <input type="hidden" name="directReferrer" style="display: none;" value="${members.directReferrer }">
                                        </a>
                                        <ul class='dropdown-menu dropdownUL' style="width: 150px;"></ul>
							        </div>
<!-- 	                                <input type="text" class="f-whole-input" placeholder="推荐人姓名" name="directReferrerName" onblur="indirectReferrerQuery(this,'directReferrer');"/> -->
<!-- 	                                <input type="hidden" name="directReferrer" id="directReferrer"/> -->
	                            </div>
	                        </div>
	
	                        <!--开卡/充值送积分-->
	                        <div class="f-group two-part">
	                            <div class="f-left">
	                                <label class="f-label" for="">开卡送积分</label>
	                                <input type="text" class="f-part-input" name="card_integral_direct" value="${directReferrer.openCardIntegral }"/>
	                                <span class="input-span">分</span>
	                            </div>
	                            <div class="f-right way">
	                                <label class="f-label" for="">充值送</label>
	                                <input type="text" class="f-part-input" name="topup_integral_direct" value="${directReferrer.topupIntegral }"/>
	                                <span class="way-span">按比例</span>
	                            </div>
	                        </div>
	
	                        <!--消费送积分-->
	                        <div class="f-group two-part way">
	                            <div class="f-left">
	                                <label class="f-label" for="">消费送积分</label>
	                                <span class="way-span">按比例</span>
	                                <input type="text" class="f-part-input" name="consume_integral_direct" value="${directReferrer.consumptionIntegral }"/>
	                            </div>
	                        </div>
	
	                        <!--间接推荐人-->
	                        <div class="f-group">
	                            <div class="f-whole">
	                                <label class="f-label" for="">间接推荐人</label>
<!-- 	                                <input type="text" class="f-whole-input" placeholder="推荐人姓名" name="indirectReferrerName" onblur="indirectReferrerQuery(this,'indirectReferrer');"/> -->
<!-- 	                                <input type="hidden" name="indirectReferrer" id="indirectReferrer"/> -->
	                                <div class="dropdown" supdiv = "supdiv" style="display: inline;">
                                        <a data-toggle="dropdown" class="dropdown-toggle" id="sponsorDropdown" supname = "supname">
                                           <input type="text" name="indirectReferrerName"  dropdowninput = "dropdowninput" class="border-left-none" style="width: 280px;color: black;">
                                           <input type="hidden" name="indirectReferrer" style="display: none;" value="${members.indirectReferrer }">
                                        </a>
                                        <ul class='dropdown-menu dropdownUL' style="width: 150px;"></ul>
							        </div>
	                            </div>
	                        </div>
	
	                        <!--开卡/充值送积分-->
	                        <div class="f-group two-part">
	                            <div class="f-left">
	                                <label class="f-label" for="">开卡送积分</label>
	                                <input type="text" class="f-part-input" name="card_integral_indirect" value="${indirectReferrer.openCardIntegral }"/>
	                                <span class="input-span">分</span>
	                            </div>
	                            <div class="f-right way">
	                                <label class="f-label" for="">充值送</label>
	                                <input type="text" class="f-part-input" name="topup_integral_indirect" value="${indirectReferrer.topupIntegral }"/>
	                                <span class="way-span">按比例</span>
	                            </div>
	                        </div>
	
	                        <!--消费送积分-->
	                        <div class="f-group two-part way">
	                            <div class="f-left">
	                                <label class="f-label" for="">消费送积分</label>
	                                <span class="way-span">按比例</span>
	                                <input type="text" class="f-part-input" name="consume_integral_indirect" value="${indirectReferrer.consumptionIntegral }"/>
	                            </div>
	                        </div>
	
	                        <!--提成分配-->
	                        <div id="commissionAllocation" class="f-group two-part select-tag " style="box-sizing: border-box;padding-top: 10px;">
	                            <div class="f-left" style="margin-left: 5px;">
	                                <label class="f-label" for="">开卡送积分</label>
	                                <input type="radio" class="check-all openCardSendIntegral" checked="checked" name="openCardSendIntegral" value="1"/><!-- name="jifen-qingling" -->
	                                <span></span>
	                                	单人模式
	                            </div>
	                            <div class="f-right" style="padding-left: 50px;">
	                                <input type="radio" class="check-all openCardSendIntegral" name="openCardSendIntegral" value="2"/>
	                                <span></span>
	                                	多人模式
	                            </div>
	                        </div>
	
	                        <div class="f-group two-part">
	                            <div class="f-left">
	                                <label class="f-label" for=""></label>
									<!-- <input type="text" class="f-part-input" name="employeeId"/>  -->
	                            	<div class="dropdown" supdiv = "supdiv">
                                        <a data-toggle="dropdown" class="dropdown-toggle" id="sponsorDropdown" supname = "supname">
                                           <input type="text" name="employeeNo"  dropdowninput = "dropdowninput" class="border-left-none" style="width: 109px;color: black;">
                                           <input type="hidden" name="employeeId" style="display: none;">
                                        </a>
                                        <ul class='dropdown-menu dropdownUL' style="width: 120px;"></ul>
							       </div>
	                            </div>
	                            <div class="f-right">
	                                <label class="f-label" for=""></label>
	                                <input type="text" class="f-part-input" style="  margin-left: -50px;width: 160px;"/>
	                            </div>
	                        </div>
	                        <div id="manyPeopleDiv" style="display: none;">
		                        <div class="f-group two-part">
		                            <div class="f-left">
		                                <label class="f-label" for=""></label>
		                                <div class="dropdown" supdiv = "supdiv">
	                                        <a data-toggle="dropdown" class="dropdown-toggle" id="sponsorDropdown" supname = "supname">
	                                           <input type="text" name="employeeNo1"  dropdowninput = "dropdowninput" class="border-left-none" style="width: 109px;color: black;">
	                                           <input type="hidden" name="employeeId" style="display: none;">
	                                        </a>
	                                        <ul class='dropdown-menu dropdownUL' style="width: 120px;"></ul>
								       </div>
		                            </div>
		                            <div class="f-right">
		                                <label class="f-label" for=""></label>
		                                <input type="text" class="f-part-input" style="  margin-left: -50px;width: 160px;"/>
		                            </div>
		                        </div>
		                        <div class="f-group two-part">
		                            <div class="f-left">
		                                <label class="f-label" for=""></label>
		                                <div class="dropdown" supdiv = "supdiv">
	                                        <a data-toggle="dropdown" class="dropdown-toggle" id="sponsorDropdown" supname = "supname">
	                                           <input type="text" name="employeeNo2"  dropdowninput = "dropdowninput" class="border-left-none" style="width: 109px;color: black;">
	                                           <input type="hidden" name="employeeId" style="display: none;">
	                                        </a>
	                                        <ul class='dropdown-menu dropdownUL' style="width: 120px;"></ul>
								       </div>
		                            </div>
		                            <div class="f-right">
		                                <label class="f-label" for=""></label>
		                                <input type="text" class="f-part-input" style="  margin-left: -50px;width: 160px;"/>
		                            </div>
		                        </div>
		                        <div class="f-group two-part">
		                            <div class="f-left">
		                                <label class="f-label" for=""></label>
		                                <div class="dropdown" supdiv = "supdiv">
	                                        <a data-toggle="dropdown" class="dropdown-toggle" id="sponsorDropdown" supname = "supname">
	                                           <input type="text" name="employeeNo3"  dropdowninput = "dropdowninput" class="border-left-none" style="width: 109px;color: black;">
	                                           <input type="hidden" name="employeeId" style="display: none;">
	                                        </a>
	                                        <ul class='dropdown-menu dropdownUL' style="width: 120px;"></ul>
								       </div>
		                            </div>
		                            <div class="f-right">
		                                <label class="f-label" for=""></label>
		                                <input type="text" class="f-part-input" style="  margin-left: -50px;width: 160px;"/>
		                            </div>
		                        </div>
							</div>
	                        <input type="button" class="return" value="返回" onclick="returnMembersList();"/>
	                        <input type="button"  class="confirm" value="保存" onclick="saveMembers();"/>
	                    </div>
	                </form>
	            </div>
	        </div>
        	
        	
		</div>
	</div>
	
	<%@ include file="/js.jsp"%>
   	<script>
   		var basePath = '<%=cssBasePath%>';
   		
	    $(".dropdown-condition").on("click",function(){
	        var target = $(".condition-content");
	        if(target.hasClass("hide")){
	            target.removeClass("hide");
	            $(this).parent().addClass("open");
	        }else{
	            target.addClass("hide");
	            $(this).parent().removeClass("open");
	        }
	    });
	    
	    var membersListStr = '${membersList}';
	  	var membersList = eval("(" + membersListStr + ")");
	    
	    var employeeListStr = '${employeeList}';
	  	var employeeList = eval("(" + employeeListStr + ")");
	</script>
	<script src="<%=cssBasePath%>js/members/membersAdd.js"></script>
</body>
</html>
