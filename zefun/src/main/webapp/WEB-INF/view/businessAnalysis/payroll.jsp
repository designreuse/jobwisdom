<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>

	<div class="maincontent">
	    <div class="contentinner">
	        <div class="widgetcontent">
	            <div class="more-toolbar" >
	                <div class="table-toolbar">
	                    <span class="font-size-16 btn-color mr10">员工工资单</span>
	                    <div class="btn-group">
	                        <button data-toggle="dropdown" class="btn dropdown-toggle">${month }<span class="caret"></span></button>
	                        <ul class="dropdown-menu">
	                            <c:forEach items="${monthList }" var="month">
	                            	<li><a href="<%=basePath %>businessAnalysis/view/payroll?month=${month }">${month }</a></li>
	                            </c:forEach>
	                        </ul>
	                    </div>
	                </div>
	                <div class="clearfix"></div>
	            </div><!--more-toolbar-->
	            <table id="export" class="table table-bordered table-striped">
	                <thead>
		                <tr>
		                    <th colspan="2">员工信息</th>
		                    <th colspan="2" class="text-center">基本工资</th>
		                    <th colspan="2" class="text-center">劳动业绩提成</th>
		                    <th colspan="2" class="text-center">商品销售提成</th>
		                    <th colspan="2" class="text-center">疗程销售提成</th>
		                    <th colspan="2" class="text-center">开充卡提成</th>
		                    <th rowspan="2" class="text-center">考勤奖惩</th>
		                    <th rowspan="2" class="text-center">行为奖惩</th>
		                    <th rowspan="2" class="text-center">服务奖惩</th>
		                    <th rowspan="2" class="text-center">应发合计</th>
		                    <th rowspan="2" class="text-center">实际发放</th>
		                </tr>
		                <tr>
		                    <th>工号</th>
		                    <th>姓名</th>
		                    <th>工资</th>
		                    <th>津贴</th>
		                    <th>提成</th>
		                    <th>业绩</th>
		                    <th>提成</th>
		                    <th>业绩</th>
		                    <th>提成</th>
		                    <th>业绩</th>
		                    <th>提成</th>
		                    <th>业绩</th>
		                </tr>
	                </thead>
	                <tbody>
	                	<c:set var="baseSalariesTotal" value="0" />
	                	<c:set var="positionSalariesTotal" value="0" />
	                	<c:set var="projectCommissionTotal" value="0" />
	                	<c:set var="projectPerformanceTotal" value="0" />
	                	<c:set var="goodsCommissionTotal" value="0" />
	                	<c:set var="goodsPerformanceTotal" value="0" />
	                	<c:set var="comboCommissionTotal" value="0" />
	                	<c:set var="comboPerformanceTotal" value="0" />
	                	<c:set var="cardCommissionTotal" value="0" />
	                	<c:set var="cardPerformanceTotal" value="0" />
	                	<c:set var="attendanceRewardTotal" value="0" />
	                	<c:set var="behaviourRewardTotal" value="0" />
	                	<c:set var="serviceRewardTotal" value="0" />
	                	<c:set var="receivableAmountTotal" value="0" />
	                	<c:set var="realAmountTotal" value="0" />
	                	<c:forEach items="${employeePayrollList }" var="employeePayroll">
	                		<tr>
			                    <td>${employeePayroll.employeeCode }</td>
			                    <td>${employeePayroll.name }</td>
			                    <td>${employeePayroll.baseSalaries }</td>
			                    <td>${employeePayroll.positionSalaries }</td>
			                    <td>${employeePayroll.payrollCommission.projectCommission }</td>
			                    <td>${employeePayroll.payrollCommission.projectPerformance }</td>
			                    <td>${employeePayroll.payrollCommission.goodsCommission }</td>
			                    <td>${employeePayroll.payrollCommission.goodsPerformance }</td>
			                    <td>${employeePayroll.payrollCommission.comboCommission }</td>
			                    <td>${employeePayroll.payrollCommission.comboPerformance }</td>
			                    <td>${employeePayroll.payrollCommission.cardCommission }</td>
			                    <td>${employeePayroll.payrollCommission.cardPerformance }</td>
			                    <td>${employeePayroll.payrollReward.attendanceReward }</td>
			                    <td>${employeePayroll.payrollReward.behaviourReward }</td>
			                    <td>${employeePayroll.payrollReward.serviceReward }</td>
			                    <td>${employeePayroll.receivableAmount }</td>
			                    <td>${employeePayroll.realAmount }</td>
			                </tr>
			                <c:set var="baseSalariesTotal" value="${baseSalariesTotal + employeePayroll.baseSalaries }" />
		                	<c:set var="positionSalariesTotal" value="${positionSalariesTotal + employeePayroll.positionSalaries }" />
		                	<c:set var="projectCommissionTotal" value="${projectCommissionTotal + employeePayroll.payrollCommission.projectCommission }" />
		                	<c:set var="projectPerformanceTotal" value="${projectPerformanceTotal + employeePayroll.payrollCommission.projectPerformance }" />
		                	<c:set var="goodsCommissionTotal" value="${goodsCommissionTotal + employeePayroll.payrollCommission.goodsCommission }" />
		                	<c:set var="goodsPerformanceTotal" value="${goodsPerformanceTotal + employeePayroll.payrollCommission.goodsPerformance }" />
		                	<c:set var="comboCommissionTotal" value="${comboCommissionTotal + employeePayroll.payrollCommission.comboCommission }" />
		                	<c:set var="comboPerformanceTotal" value="${comboPerformanceTotal + employeePayroll.payrollCommission.comboPerformance }" />
		                	<c:set var="cardCommissionTotal" value="${cardCommissionTotal + employeePayroll.payrollCommission.cardCommission }" />
		                	<c:set var="cardPerformanceTotal" value="${cardPerformanceTotal + employeePayroll.payrollCommission.cardPerformance }" />
		                	<c:set var="attendanceRewardTotal" value="${attendanceRewardTotal + employeePayroll.payrollReward.attendanceReward }" />
		                	<c:set var="behaviourRewardTotal" value="${behaviourRewardTotal + employeePayroll.payrollReward.behaviourReward }" />
		                	<c:set var="serviceRewardTotal" value="${serviceRewardTotal + employeePayroll.payrollReward.serviceReward }" />
		                	<c:set var="receivableAmountTotal" value="${receivableAmountTotal + employeePayroll.receivableAmount }" />
		                	<c:set var="realAmountTotal" value="${realAmountTotal + employeePayroll.realAmount }" />
	                	</c:forEach>
	                </tbody>
	                <tfoot>
		                <tr class="huizong-tr">
		                    <td colspan="2" class="text-center">汇总</td>
		                    <td>${baseSalariesTotal }</td>
		                    <td>${positionSalariesTotal }</td>
		                    <td>${projectCommissionTotal }</td>
		                    <td>${projectPerformanceTotal }</td>
		                    <td>${goodsCommissionTotal }</td>
		                    <td>${goodsPerformanceTotal }</td>
		                    <td>${comboCommissionTotal }</td>
		                    <td>${comboPerformanceTotal }</td>
		                    <td>${cardCommissionTotal }</td>
		                    <td>${cardPerformanceTotal }</td>
		                    <td>${attendanceRewardTotal }</td>
		                    <td>${behaviourRewardTotal }</td>
		                    <td>${serviceRewardTotal }</td>
		                    <td>${receivableAmountTotal }</td>
		                    <td>${realAmountTotal }</td>
		                </tr>
		                <!-- <tr>
		                    <td colspan="18">
		                        <div class="s-btn-group fr">
		                            <button class="btn ml10">
		                                <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
		                                <span >导出</span>
		                            </button>
		                            <button class="btn ml10">
		                                <span>打印</span>
		                            </button>
		                        </div>
		                    </td>
		                </tr> -->
	                </tfoot>
	            </table>
	            <div class="more-toolbar">
             <div class="table-toolbar">
                 <div class="s-btn-group fr">
                     <button class="btn ml10" onclick ="exportTable('export')">
                         <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp">
                         <span>导出</span>
                     </button>
                     <button class="btn ml10" onclick ="printTable('export')">
                         <span>打印</span>
                     </button>
                 </div>
             </div>
             <div class="clearfix"></div>
           </div>
	        </div>
	    </div>
	</div>
    </div>
    <!--RIGHT PANEL结束 -->
    </div>
    <a href="" class="showmenu"></a>
</div><!--mainwrapper-->
</body>
</html>