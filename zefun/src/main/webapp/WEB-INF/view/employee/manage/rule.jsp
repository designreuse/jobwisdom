<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>

<style>
.xiangqing-chakan-modal .table thead th {
  text-align: center;
}
.xiangqing-chakan-modal .table tbody td {
  text-align: center;
}

.xiangqing-chakan-modal .modal-body {
	max-height:inherit;
}
</style>

</head>

<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->
    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
    <%@ include file="/top.jsp" %>
		<div class="maincontent">
		    <div class="contentinner new-contentinner">
		        <div class="n-tab">
		            <ul>
		                <li class="active n-sub-tab" onclick="changeRule(0)">
		                    <span>考勤规则</span>
		                    <div class="border-bottom"></div>
		                </li>
		                <li class="n-sub-tab" onclick="changeRule(1)">
		                    <div class="tab-word">
		                        <span>行为规范</span>
		                    </div>
		                    <div class="border-bottom"></div>
		                </li>
		                <li class="n-sub-tab" onclick="changeRule(2)">
		                    <div class="tab-word">
		                        <span>服务表现</span>
		                    </div>
		                    <div class="border-bottom"></div>
		                </li>
		            </ul>
		            <div class="clearfix"></div>
		        </div>
			    <div class="n-tab-content ">
			        <div class="tab-padding">
			            <div class="widgetcontent">
			                <table class="table table-bordered ruleinfo" style="display: none">
			                    <thead>
			                        <tr>
			                            <th class="name bold border_underline">考核项</th>
			                            <th class="name bold border_underline">考核标准</th>
			                            <th class="name bold border_underline">考核措施</th>
			                            <th class="name bold border_underline">奖罚金额</th>
			                            <th class="name bold border_underline">操作</th>
			                        </tr>
			                    </thead>
			                    <tbody>
			                        <c:forEach items="${ruleList }" var="rule">
			                             <c:if test="${rule.ruleType == 1 }">
			                                 <tr id="${rule.ruleId}">
				                                <td><span name="ruleName">${rule.ruleName }</span></td>
				                                <td>
				                                    <span name="ruleDesc">${rule.ruleDesc }</span>
				                                    <c:choose>
				                                        <c:when test="${rule.targetType == 1 }">
				                                            ${rule.targetValue }分钟
				                                        </c:when>
				                                        <c:when test="${rule.targetType == 2 }">
				                                          <c:choose>
				                                             <c:when test="${rule.ruleName == '请假' }">
				                                                 ,按每${rule.targetValue }小时扣除指定金额
				                                             </c:when>
				                                             <c:otherwise>
				                                                 ${rule.targetValue }小时,按小时扣除指定金额
				                                             </c:otherwise>
				                                          </c:choose>
	                                                    </c:when>
	                                                    <c:when test="${rule.targetType == 3 }">
	                                                        ${rule.targetValue }分
	                                                    </c:when>
				                                    </c:choose>
				                                    <input name="targetValue" type="hidden" value="${rule.targetValue }">
				                                    <input name="targetType" type="hidden" value="${rule.targetType }">
				                                    <input name="processType" type="hidden" value="${rule.processType }">
				                                </td>
				                                <td>
				                                    <c:choose>
				                                        <c:when test="${rule.processType == 1 }">
				                                            奖励
				                                        </c:when>
				                                        <c:otherwise>
				                                            惩罚
				                                        </c:otherwise>
				                                    </c:choose>
				                                </td>
				                                <td><span name="processMoney">${rule.processMoney }</span></td>
				                                <td>
				                                    <i class="iconfa-pencil project-icon" onclick="editTargetValue(${rule.ruleId})"></i>
				                                </td>
				                            </tr>
			                             </c:if>
			                        </c:forEach>
			                    </tbody>
			                </table>
			                <table class="table table-bordered ruleinfo" style="display: none">
			                    <thead>
			                        <tr>
			                            <th class="name bold border_underline">考核项</th>
			                            <th class="name bold border_underline">考核标准</th>
			                            <th class="name bold border_underline">考核措施</th>
			                            <th class="name bold border_underline">奖罚金额</th>
			                            <th class="name bold border_underline">操作</th>
			                        </tr>
			                    </thead>
			                    <tbody>
                                   <c:forEach items="${ruleList }" var="rule">
                                        <c:if test="${rule.ruleType == 2 }">
                                             <tr id="${rule.ruleId}">
                                                <td><span name="ruleName">${rule.ruleName }</span></td>
                                                <td>
                                                    <span name="ruleDesc">${rule.ruleDesc }</span>
                                                    <input name="targetValue" type="hidden" value="${rule.targetValue }">
                                                    <input name="targetType" type="hidden" value="${rule.targetType }">
                                                    <input name="processType" type="hidden" value="${rule.processType }">
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${rule.processType == 1 }">
                                                            奖励
                                                        </c:when>
                                                        <c:otherwise>
                                                            惩罚
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td><span name="processMoney">${rule.processMoney }</span></td>
                                                <td>
                                                    <i class="iconfa-pencil project-icon" onclick="editTargetValue(${rule.ruleId})"></i>
                                                </td>
                                            </tr>
                                         </c:if>
                                   </c:forEach>
			                    </tbody>
			                </table>
			                <table class="table table-bordered ruleinfo" style="display: none">
			                    <thead>
			                        <tr>
			                            <th class="name bold border_underline">考核项</th>
			                            <th class="name bold border_underline">考核标准</th>
			                            <th class="name bold border_underline">考核措施</th>
			                            <th class="name bold border_underline">奖罚金额</th>
			                            <th class="name bold border_underline">操作</th>
			                        </tr>
			                    </thead>
			                    <tbody>
                                   <c:forEach items="${ruleList }" var="rule">
                                        <c:if test="${rule.ruleType == 3 }">
                                             <tr id="${rule.ruleId}">
                                                <td><span name="ruleName">${rule.ruleName }</span></td>
                                                <td>
                                                    <span name="ruleDesc">${rule.ruleDesc }</span>
                                                    <c:choose>
                                                        <c:when test="${rule.targetType == 1 }">
                                                            ${rule.targetValue }分钟
                                                        </c:when>
                                                        <c:when test="${rule.targetType == 2 }">
                                                          <c:choose>
                                                             <c:when test="${rule.ruleName == '请假' }">
                                                                 ,按小时扣除指定金额
                                                             </c:when>
                                                             <c:otherwise>
                                                                 ${rule.targetValue }小时,按小时扣除指定金额
                                                             </c:otherwise>
                                                          </c:choose>
                                                        </c:when>
                                                        <c:when test="${rule.targetType == 3 }">
                                                            ${rule.targetValue }分
                                                        </c:when>
                                                    </c:choose>
                                                    <input name="targetValue" type="hidden" value="${rule.targetValue }">
                                                    <input name="targetType" type="hidden" value="${rule.targetType }">
                                                    <input name="processType" type="hidden" value="${rule.processType }">
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${rule.processType == 1 }">
                                                            奖励
                                                        </c:when>
                                                        <c:otherwise>
                                                            惩罚
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td><span name="processMoney">${rule.processMoney }</span></td>
                                                <td>
                                                    <i class="iconfa-pencil project-icon" onclick="editTargetValue(${rule.ruleId})"></i>
                                                </td>
                                            </tr>
                                         </c:if>
                                   </c:forEach>
			                    </tbody>
			                </table>
			            </div>
			           
						<!-- 关于考勤 -->
			            <div class="widgetcontent record_tab" style="display: block">
			                <div class="more-toolbar" >
			                    <div class="table-toolbar">本月员工缺勤记录
			                        <select id="queryYearAndMonth" class="chzn-select-search input120">
			                        	<c:forEach items="${monthList }" var="month">
			                        		<option value="${month }">${month }</option>
			                        	</c:forEach>
			                        </select>
			                    	<!-- <select name="" id="queryyear" class="chzn-select-search input120">
			                            <option value="2015">2015年</option>
			                            <option value="2016">2016年</option>
			                            <option value="2017">2017年</option>
			                        </select>
			                        <select name="" id="querymonth" class="chzn-select-search input120">
			                            <option value="1">1月</option>
			                            <option value="2">2月</option>
			                            <option value="3">3月</option>
			                            <option value="4">4月</option>
			                            <option value="5">5月</option>
			                            <option value="6">6月</option>
			                            <option value="7">7月</option>
			                            <option value="8">8月</option>
			                            <option value="9">9月</option>
			                            <option value="10">10月</option>
			                            <option value="11">11月</option>
			                            <option value="12">12月</option>
			                        </select> -->
			                        <input type="search" id="search" placeholder="姓名/工号" class="input120"/>
			                        <button id="employeeAttendanceRewardBtn" class="button-search btn width100 ml-10" onclick="changePage('ATTENDANCE')">查询</button>
			                        <button class="button-search btn width100" onclick="toAddEmployeeReward()">新增记录</button>
			                        <!-- <div class="table-pagination fr">
				                        <button data-toggle="dropdown" class="btn dropdown-toggle perpage perpage0">
				                            10 <span class="iconfa-caret-down afont"></span>
				                        </button>
				                        <ul class="dropdown-menu">
				                        <li><a href="javascript:changePageSize(5,'ATTENDANCE')">5</a></li>
						                <li><a href="javascript:changePageSize(10,'ATTENDANCE')">10</a></li>
						                <li><a href="javascript:changePageSize(20,'ATTENDANCE')">20</a></li>
						                <li><a href="javascript:changePageSize(50,'ATTENDANCE')">50</a></li>
						                <li><a href="javascript:changePageSize(100,'ATTENDANCE')">100</a></li>
				                        </ul>
							            <div class="left-page" id="previousPageButton" onclick="previous('ATTENDANCE')"><i class="FontAwesome iconfa-caret-left afont" ></i></div>
					                    <div class="right-page" id="nextPageButton" onclick="next('ATTENDANCE')"><i class="FontAwesome iconfa-caret-right afont" ></i></div>
				                    </div> -->
			                    </div>
			                    <!--table-toolbar-->
			                    <div class="clearfix"></div>
			                </div><!--more-toolbar-->
			
			                <table class="table table-bordered member-list-table">
			                    <thead class="t-fix">
			                    <tr>
			                        <th class="nav_td right_border">工号</th>
			                        <th class="nav_td right_border">姓名</th>
			                        <th class="nav_td right_border">迟到次数</th>
			                        <th class="nav_td right_border">早退次数</th>
			                        <th class="nav_td right_border">请假次数</th>
			                        <th class="nav_td right_border">旷工次数</th>
			                        <th class="nav_td right_border">奖励金额</th>
			                        <th class="nav_td right_border">扣款金额</th>
			                    </tr>
			                    </thead>
			                    <tbody id="employeeAttendanceRewardTbody">
			                    	<c:forEach items="${allOfAttendanceList }" var="employeeReward" varStatus="index">
			                    		<c:if test="${index.index == 0 }">
			                    			<tr class="t-table">
			                    		</c:if>
			                    		<c:if test="${index.index != 0 }">
			                    			<tr>
			                    		</c:if>
			                    			<td>${employeeReward.employeeCode }</td>
			                    			<td>${employeeReward.employeeName }</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(1,${employeeReward.employeeId})">
			                    					${employeeReward.countOfLate }
			                    				</a>
			                    			</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(2,${employeeReward.employeeId})">
			                    					${employeeReward.countOfEarlyLeave }
			                    				</a>
			                    			</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(3,${employeeReward.employeeId})">
			                    					${employeeReward.countOfHoliday }
			                    				</a>
			                    			</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(4,${employeeReward.employeeId})">
			                    					${employeeReward.countOfAbsenteeism }
			                    				</a>
			                    			</td>
			                    			<td>${employeeReward.moneyOfReward }</td>
			                    			<td>${employeeReward.moneyOfPunishment }</td>
			                    		</tr>
			                    	</c:forEach>
			                    </tbody>
			                </table>
			            </div>
			            <!-- 关于行为 -->
			            <div class="widgetcontent record_tab" style="display: none">
			                <div class="more-toolbar" >
			                    <div class="table-toolbar">本月员工行为记录
			                    	<select id="queryYearAndMonth1" class="chzn-select-search input120">
			                        	<c:forEach items="${monthList }" var="month">
			                        		<option value="${month }">${month }</option>
			                        	</c:forEach>
			                        </select>
			                    	<!-- <select name="" id="queryyear1" class="chzn-select-search input120" >
			                            <option value="2015">2015年</option>
			                            <option value="2016">2016年</option>
			                            <option value="2016">2017年</option>
			                        </select>
			                        <select name="" id="querymonth1" class="chzn-select-search input120">
			                            <option value="1">1月</option>
			                            <option value="2">2月</option>
			                            <option value="3">3月</option>
			                            <option value="4">4月</option>
			                            <option value="5">5月</option>
			                            <option value="6">6月</option>
			                            <option value="7">7月</option>
			                            <option value="8">8月</option>
			                            <option value="9">9月</option>
			                            <option value="10">10月</option>
			                            <option value="11">11月</option>
			                            <option value="12">12月</option>
			                        </select> -->
			                        <input type="search" id="search1" placeholder="姓名/工号" class="input120"/>
			                        <button class="button-search btn width100 ml-10" onclick="changePage('BEHAVIOUR')">查询</button>
			                        <button class="button-search btn width100" onclick="toAddEmployeeReward()">新增记录</button>
			                        <!-- <div class="table-pagination fr">
				                        <button data-toggle="dropdown" class="btn dropdown-toggle perpage perpage1"> 
				                            10 <span class="iconfa-caret-down afont"></span>
				                        </button>
				                        <ul class="dropdown-menu">
				                        <li><a href="javascript:changePageSize1(5,'BEHAVIOUR')">5</a></li>
						                <li><a href="javascript:changePageSize1(10,'BEHAVIOUR')">10</a></li>
						                <li><a href="javascript:changePageSize1(20,'BEHAVIOUR')">20</a></li>
						                <li><a href="javascript:changePageSize1(50,'BEHAVIOUR')">50</a></li>
						                <li><a href="javascript:changePageSize1(100,'BEHAVIOUR')">100</a></li>
				                        </ul>
							            <div class="left-page" id="previousPageButton1" onclick="previous1('BEHAVIOUR')"><i class="FontAwesome iconfa-caret-left afont" ></i></div>
					                    <div class="right-page" id="nextPageButton1" onclick="next1('BEHAVIOUR')"><i class="FontAwesome iconfa-caret-right afont" ></i></div>
				                    </div> -->
			                    </div>
			                    <!--table-toolbar-->
			                    <div class="clearfix"></div>
			                </div><!--more-toolbar-->
			
			                <table class="table table-bordered member-list-table1">
			                    <thead class="t-fix1">
			                    <tr>
			                        <th class="nav_td right_border">工号</th>
			                        <th class="nav_td right_border">姓名</th>
			                        <th class="nav_td right_border">小过次数</th>
			                        <th class="nav_td right_border">大过次数</th>
			                        <th class="nav_td right_border">警告次数</th>
			                        <th class="nav_td right_border">奖励金额</th>
			                        <th class="nav_td right_border">扣款金额</th>
			                    </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach items="${allOfBehaviourList }" var="employeeReward" varStatus="index">
			                    		<c:if test="${index.index == 0 }">
			                    			<tr class="t-table1">
			                    		</c:if>
			                    		<c:if test="${index.index != 0 }">
			                    			<tr>
			                    		</c:if>
			                    			<td>${employeeReward.employeeCode }</td>
			                    			<td>${employeeReward.employeeName }</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(6,${employeeReward.employeeId})">
			                    					${employeeReward.countOfSmallMistake }
			                    				</a>
			                    			</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(7,${employeeReward.employeeId})">
			                    					${employeeReward.countOfSeriousMistake }
			                    				</a>
			                    			</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(8,${employeeReward.employeeId})">
			                    					${employeeReward.countOfWarning }
			                    				</a>
			                    			</td>
			                    			<td>${employeeReward.moneyOfReward }</td>
			                    			<td>${employeeReward.moneyOfPunishment }</td>
			                    		</tr>
			                    	</c:forEach>
			                    </tbody>
			                </table>
			            </div>
			            <!-- 关于服务 -->
			            <div class="widgetcontent record_tab" style="display: none">
			                <div class="more-toolbar" >
			                    <div class="table-toolbar">本月员工服务记录
			                    	<select id="queryYearAndMonth2" class="chzn-select-search input120">
			                        	<c:forEach items="${monthList }" var="month">
			                        		<option value="${month }">${month }</option>
			                        	</c:forEach>
			                        </select>
			                    	<!-- <select name="" id="queryyear2" class="chzn-select-search input120">
			                            <option value="2015">2015年</option>
			                            <option value="2016">2016年</option>
			                            <option value="2016">2017年</option>
			                        </select>
			                        <select name="" id="querymonth2" class="chzn-select-search input120">
			                            <option value="1">1月</option>
			                            <option value="2">2月</option>
			                            <option value="3">3月</option>
			                            <option value="4">4月</option>
			                            <option value="5">5月</option>
			                            <option value="6">6月</option>
			                            <option value="7">7月</option>
			                            <option value="8">8月</option>
			                            <option value="9">9月</option>
			                            <option value="10">10月</option>
			                            <option value="11">11月</option>
			                            <option value="12">12月</option>
			                        </select> -->
			                        <input type="search" id="search2" placeholder="姓名/工号" class="input120"/>
			                        <button class="button-search btn width100 ml-10"onclick="changePage('SERVICE')">查询</button>
			                        <button class="button-search btn width100" onclick="toAddEmployeeReward()">新增记录</button>
			                        <!-- <div class="table-pagination fr">
				                        <button data-toggle="dropdown" class="btn dropdown-toggle perpage perpage2">
				                            10 <span class="iconfa-caret-down afont"></span>
				                        </button>
				                        <ul class="dropdown-menu">
				                        <li><a href="javascript:changePageSize2(5,'SERVICE')">5</a></li>
						                <li><a href="javascript:changePageSize2(10,'SERVICE')">10</a></li>
						                <li><a href="javascript:changePageSize2(20,'SERVICE')">20</a></li>
						                <li><a href="javascript:changePageSize2(50,'SERVICE')">50</a></li>
						                <li><a href="javascript:changePageSize2(100,'SERVICE')">100</a></li>
				                        </ul>
							            <div class="left-page" id="previousPageButton2" onclick="previous2('SERVICE')"><i class="FontAwesome iconfa-caret-left afont" ></i></div>
					                    <div class="right-page" id="nextPageButton2" onclick="next2('SERVICE')"><i class="FontAwesome iconfa-caret-right afont" ></i></div>
				                    </div> -->
			                    </div>
			                    <!--table-toolbar-->
			                    <div class="clearfix"></div>
			                </div><!--more-toolbar-->
			                
			                <table class="table table-bordered member-list-table2">
			                    <thead class="t-fix2">
			                    <tr>
			                        <th class="nav_td right_border">工号</th>
			                        <th class="nav_td right_border">姓名</th>
			                        <th class="nav_td right_border">好评次数</th>
			                        <th class="nav_td right_border">差评次数</th>
			                        <th class="nav_td right_border">投诉次数</th>
			                        <th class="nav_td right_border">奖励金额</th>
			                        <th class="nav_td right_border">扣款金额</th>
			                    </tr>
			                    </thead>
			                    <tbody>
			                    	<c:forEach items="${allOfServiceList }" var="employeeReward" varStatus="index">
			                    		<c:if test="${index.index == 0 }">
			                    			<tr class="t-table2">
			                    		</c:if>
			                    		<c:if test="${index.index != 0 }">
			                    			<tr>
			                    		</c:if>
			                    			<td>${employeeReward.employeeCode }</td>
			                    			<td>${employeeReward.employeeName }</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(9,${employeeReward.employeeId})">
			                    					${employeeReward.countOfFavourable }
			                    				</a>
			                    			</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(10,${employeeReward.employeeId})">
			                    					${employeeReward.countOfBadpost }
			                    				</a>
			                    			</td>
			                    			<td>
			                    				<a href="javascript:void(0)" onclick="opendetail(11,${employeeReward.employeeId})">
			                    					${employeeReward.countOfComplaint }
			                    				</a>
			                    			</td>
			                    			<td>${employeeReward.moneyOfReward }</td>
			                    			<td>${employeeReward.moneyOfPunishment }</td>
			                    		</tr>
			                    	</c:forEach>
			                    </tbody>
			                </table>
			            </div>
			             </div>
		            </div>
		</div>
		
		<!-- 查看奖惩的详细信息 -->
		<div class="modal hide" id="xiangqing-chakan-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  data-backdrop="static">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content xiangqing-chakan-modal" style="width: 800px;">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		                	<span aria-hidden="true" onclick="initPageInfoOfRewardsDetail()">&times;</span>
		                </button>
		                <h5 class="modal-title" id="myModalLabel">
		                	<span id="echoEmployeeName"></span>
		                	<span id="echoRewardType"></span>
		                </h5>
		            </div>
		            <div class="modal-body">
		                <!-- <div class="more-toolbar" >
		                    <div class="table-toolbar"></div>
		                    table-toolbar
		                    <div class="clearfix"></div>
		                </div> --><!--more-toolbar-->
		                <input id="type" type="hidden" >
		                <input id="employeeId" type="hidden" >
		                <input type="hidden" id="typeOfDetail"/>
		                <table class="table table-bordered derail">
		                    <thead>
		                    <tr>
			                    <!-- <th>名称</th> -->
			                    <th style="width: 150px">时间</th>
			                    <th>扣款</th>
			                    <th>原因</th>
			                    <th>操作</th>
		                    </tr>
		                    </thead>
		                    <tbody>
		                    </tbody>
		                </table>
		                <div class="table-pagination fr">
                            <!-- <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                                10 <span class="iconfa-caret-down afont"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li><a href="javascript:changePageSizeOfDetail(5)">5</a></li>
				                <li><a href="javascript:changePageSizeOfDetail(10)">10</a></li>
				                <li><a href="javascript:changePageSizeOfDetail(20)">20</a></li>
				                <li><a href="javascript:changePageSizeOfDetail(50)">50</a></li>
				                <li><a href="javascript:changePageSizeOfDetail(100)">100</a></li>
                            </ul> -->
                            <%@ include file="pageOfRewardsDetail.jsp" %>
                            <!-- <div class="left-page" id="previousPageButtonOfDetail" onclick="previousOfDetail()"><i class="FontAwesome iconfa-caret-left afont" ></i></div>
                    		<div class="right-page" id="nextPageButtonOfDetail" onclick="nextOfDetail()"><i class="FontAwesome iconfa-caret-right afont" ></i></div> -->
                        </div>
		            </div>
		        </div>
		    </div>
		</div>
  
<!-- 添加奖惩模态框 -->
<div class="modal hide" id="add-employee-reward" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-employee-reward">
            <div class="modal-header ">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h5 class="modal-title" id="myModalLabel">员工奖惩添加</h5>
            </div>
            <div class="modal-body">
                <div class="editprofileform" method="post">     
                	<p>
                		<span>姓名：</span>
                		<select id="employeeIdOfAddReward" class="chzn-select-search input120">
		            	<!-- 此处填充该店铺下的所有员工 -->
		            	</select>
                	</p>           	
                	<p>
                		<span>类别：</span>
	                	<select id="rewardTypeOfAddReward" onchange="isHoliday(this)" class="chzn-select-search input120">
		                    <option value="1">迟到</option>
		                    <option value="2">早退</option>
		                    <option value="3">请假</option>
		                    <option value="4">旷工</option>
		                    <option value="5">全勤</option>
		                    <option value="6">小过</option>
		                    <option value="7">大过</option>
		                    <option value="8">警告</option>
		                    <option value="9">好评</option>
		                    <option value="10">差评</option>
		                    <option value="11">投诉</option>
	                	</select>
                	</p>
                	<p id="startTimeP" class="hide">
                		<span>开始时间：</span>
                		<input id="startTimeOfAddReward" type="text" class="w150" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
                	</p>
                	<p id="endTimeP" class="hide">
                		<span>结束时间：</span>
                		<input id="endTimeOfAddReward" type="text" class="w150" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" />
                	</p>
                	<p>
                		<span>原因：</span>
                		<input id="rewardReasonsOfAddReward" type="text" placeholder=""/>
                	</p>
                </div>
            </div>
            <div class="modal-footer">
                <a class="btn modal-cancel" href="javascript:cancelAddEmployeeReward()">取消</a>
                <a class="btn btn-primary modal-confirm" href="javascript:addEmployeeReward()">确认</a>
            </div>
        </div>
    </div>
</div>

		<!--修改制度规则模态框-->
        <div class="modal hide" id="editRuleModal" tabindex="-1" role="dialog" aria-labelledby="editRuleModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content add-zhiwei-modal">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="editRuleModal">考核奖惩设置</h4>
                    </div>
                    <form id="editRuleForm">
	                    <div class="modal-body">
	                        <div class="editprofileform" >
                                <input type="hidden" name="ruleId">
	                            <p>
	                                <label>考核名称:</label>
	                                <input name="ruleName" type="text" disabled="disabled">
                                </p>
                                <p>
	                                <label>考核标准:</label>
	                                <span name="ruleDesc">
		                                
	                                </span>
                                </p>
                                <p>
                                    <label>奖惩方式:</label>
                                    <span name="processType"></span>
                                    <input type="number" class="input60" name="processMoney">元
                                </p>
	                        </div>
	                    </div>
                    </form>
                    <div class="modal-footer">
                        <a class="btn cancel-btn modal-cancel" data-dismiss="modal" href="javascript:void();">取消</a>
                        <a class="btn btn-primary save-btn modal-confirm" href="javascript:submitRule();">保存</a>
                    </div>
                </div>
            </div>
        </div>  
		</div>
    <!--RIGHT PANEL结束 -->
    </div>
   </div>
</div>
</body>

<script src="<%=basePath %>js/employee/rewards.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
//获取加载页面时的页码信息
/* var pageNoOfAttendance = ${pageOfAttendance.pageNo};
var pageSizeOfAttendance = ${pageOfAttendance.pageSize};
var totalPageOfAttendance = ${pageOfAttendance.totalPage};

var pageNoOfBehaviour = ${pageOfBehaviour.pageNo};
var pageSizeOfBehaviour = ${pageOfBehaviour.pageSize};
var totalPageOfBehaviour = ${pageOfBehaviour.totalPage};

var pageNoOfService = ${pageOfService.pageNo};
var pageSizeOfService = ${pageOfService.pageSize};
var totalPageOfService = ${pageOfService.totalPage}; */

var detailPageNoOfAttendance;
var detailPageSizeOfAttendance = 10;
var detailTotalPageOfAttendance;

var detailPageNoOfBehaviour;
var detailPageSizeOfBehaviour;
var detailTotalPageOfBehaviour;

var detailPageNoOfService;
var detailPageSizeOfService;
var detailTotalPageOfService;

//1.弹出迟到窗口页码信息  LATE
var pageNoOfLate = 1;
var pageSizeOfLate = 10;
var totalPageOfLate;
//2.弹出早退窗口页码信息  EARLY_LEAVE
var pageNoOfEarlyLeave = 1;
var pageSizeOfEarlyLeave = 10;
var totalPageOfEarlyLeave;
//3.弹出请假窗口页码信息  HOLIDAY Holiday
var pageNoOfHoliday = 1;
var pageSizeOfHoliday = 10;
var totalPageOfHoliday;
//4.弹出旷工窗口页码信息  ABSENTEEISM Absenteeism
var pageNoOfAbsenteeism = 1;
var pageSizeOfAbsenteeism = 10;
var totalPageOfAbsenteeism;
//5.弹出全勤窗口页码信息  ATTENDANCE  Attendance
var pageNoOfAttendance = 1;
var pageSizeOfAttendance = 10;
var totalPageOfAttendance;
//6.弹出小过窗口页码信息  SMALL_MISTAKE  SmallMistake
var pageNoOfSmallMistake = 1;
var pageSizeOfSmallMistake = 10;
var totalPageOfSmallMistake;
//7.弹出大过窗口页码信息  SERIOUS_MISTAKE  SeriousMistake
var pageNoOfSeriousMistake = 1;
var pageSizeOfSeriousMistake = 10;
var totalPageOfSeriousMistake;
//8.弹出警告窗口页码信息  WARNING  Warning
var pageNoOfWarning = 1;
var pageSizeOfWarning = 10;
var totalPageOfWarning;
//9.弹出好评窗口页码信息  FAVOURABLE  Favourable
var pageNoOfFavourable = 1;
var pageSizeOfFavourable = 10;
var totalPageOfFavourable;
//10.弹出差评窗口页码信息  BADPOST  Badpost
var pageNoOfBadpost = 1;
var pageSizeOfBadpost = 10;
var totalPageOfBadpost;
//11.弹出投诉窗口页码信息  COMPLAINT  Complaint
var pageNoOfComplaint = 1;
var pageSizeOfComplaint = 10;
var totalPageOfComplaint;

//var pageNo = 1;	
//var pageSize = 20;	
//var totalPage = '${page2.totalPage}';

jQuery(".ruleinfo:first").show();
function changeRule(index){
	jQuery(".n-sub-tab").removeClass("active").eq(index).addClass("active");
	jQuery(".ruleinfo").hide().eq(index).show();
	jQuery(".record_tab").hide().eq(index).show();
}

var editRuleForm = jQuery("#editRuleForm");
function editTargetValue(ruleId){
	var tr = jQuery("#" + ruleId);
	var ruleName = tr.find("[name='ruleName']").html();
	var ruleDesc = tr.find("[name='ruleDesc']").html();
	var targetType = tr.find("[name='targetType']").val();
	var targetValue = tr.find("[name='targetValue']").val();
	var processType = tr.find("[name='processType']").val();
	var processMoney = tr.find("[name='processMoney']").html();
	
	if (processType == '1') {
		processType = '奖励';
	} else {
		processType = '惩罚';
	}
	
	editRuleForm.find("[name='ruleId']").val(ruleId);
	editRuleForm.find("[name='ruleName']").val(ruleName);
	editRuleForm.find("[name='processType']").html(processType);
	editRuleForm.find("[name='processMoney']").val(processMoney);
    
	var targetTypeStr = "";
    if (targetType == 1) {
        targetTypeStr = "分钟";
    } else if (targetType == 2) {
        targetTypeStr = "小时";
    } else if (targetType == 3) {
        targetTypeStr = "分";
    }
    if (ruleName == '迟到' || ruleName == '早退' || ruleName == '旷工' || ruleName == '好评' || ruleName == '差评') {
    	editRuleForm.find("span[name='ruleDesc']").html(ruleDesc + '<input type="text" name="targetValue" value="' + targetValue + '" class="input40">' + targetTypeStr);
    } else if (ruleName == '请假') {
    	editRuleForm.find("span[name='ruleDesc']").html(ruleDesc + ',以每' + '<input type="text" name="targetValue" value="' + targetValue + '" class="input40">' + targetTypeStr + '为基数');
    } else if (ruleName == '全勤' || ruleName == '投诉') {
    	editRuleForm.find("span[name='ruleDesc']").html(ruleDesc);
    } else {
    	editRuleForm.find("span[name='ruleDesc']").html('<input type="text" name="ruleDesc" value="' + ruleDesc + '">');
    }
	
	jQuery("#editRuleModal").modal({show:true});
}

function submitRule(){
	var data = editRuleForm.serialize()
	jQuery.ajax({
        type: "POST",
        url: baseUrl + "storeManageRule/action/update",
        data: data,
        success: function(e) {
        	if (e.code == 0) {
        		dialog("修改成功，马上刷新页面...");
        	}
        	window.location.href = window.location.href;
        }
	});
}
</script>
<script>
    


</script>
</html>


