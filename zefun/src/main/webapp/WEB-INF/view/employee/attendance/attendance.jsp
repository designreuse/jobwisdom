<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
     <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
    	<%@ include file="/top.jsp" %>
<div class="maincontent">
    <div class="contentinner">

        <div class="more-toolbar" >
            <div class="table-toolbar">
                <label for="" style="display: inline-block;padding-right: 5px;">打卡列表</label>
                  <input type="search" id="employeeName" placeholder="姓名"/>
                  <input type="text" id="attendanceDate" class="input100 timePickerClean datetimepicker" format="Y-m-d"  placeholder="日期" />
                  <button id="searchEmployeeAttendanceButton" class="button-search btn width100 ml-10" onclick="searchEmployeeAttendance()">查询</button>
                <!-- <div class="table-pagination fr">
                    <button data-toggle="dropdown" class="btn dropdown-toggle perpage">
                        10 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="javascript:changePageSize(5)">5</a></li>
		                <li><a href="javascript:changePageSize(10)">10</a></li>
		                <li><a href="javascript:changePageSize(20)">20</a></li>
		                <li><a href="javascript:changePageSize(50)">50</a></li>
		                <li><a href="javascript:changePageSize(100)">100</a></li>
                    </ul>
                    <div class="left-page" id="previousPageButton" onclick="previous()"><span class="FontAwesome iconfa-caret-left afont"></span></div>
		            <div class="right-page" id="nextPageButton" onclick="next()"><span class="FontAwesome iconfa-caret-right afont"></span></div>
                </div> --><!--table-pagination-->
            </div><!--table-toolbar-->
            <div class="clearfix"></div>
        </div><!--more-toolbar-->

        <table class="table table-bordered table-striped member-list-table">
            <thead class="t-fix">
            <tr>
                <th>工号</th>
                <th>姓名</th>
                <th>签到时间</th>
                <th>迟到时间</th>
                <th>签退时间</th>
                <th>早退时间</th>
                <th>日期</th>
                <th style="text-align: center">操作</th>
            </tr>
            </thead>
            <tbody>
           <c:forEach items="${page.results }" var="employee" varStatus="index">
           	<c:if test="${index.index == 0 }">
         		<tr class="t-table">
         	</c:if>
         	<c:if test="${index.index != 0 }">
         		<tr>
         	</c:if>
                <td>${employee.employeeInfo.employeeCode }</td>
                <td>${employee.employeeInfo.name }</td>
                <td>${employee.signInTime }</td>
                <td>${employee.signInOffsetToTimeDetail }</td>
                <td>${employee.signOutTime }</td>
                <td>${employee.signOutOffsetTimeDetail }</td>
                <td>${employee.attendanceDate }</td>
                <td>
                    <span class="iconfa-pencil project-icon" onclick="toEdit(${employee.recordId})" ></span>
				    <span class="iconfa-trash project-icon" onclick="employeeAttendacneDelete(${employee.recordId})"></span>
                </td>
            </tr>
            </c:forEach>
            </tbody>
        </table>
        <%@ include file="/template/page.jsp" %>
    </div>
</div>

<!--修改考勤记录模态框-->
<div class="modal hide" id="edit-employeeAttendance-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-sub-project-modal" style="width:400px !important">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">修改考勤记录</h4>
                <span class="return hide cursor">
                    <span class="icon-chevron-left fl"></span>
                    <span class="fl ml10">返回</span>
                </span>
            </div>
            <div class="modal-body">
                <ul class="xiugai-kaoqing">
                    <!-- <li><span>工号</span>     <input id="editOfEmployeeCode" type="text" class="input-small" readonly /></li> -->
                    <!-- <li><span>姓名</span>     <input id="editOfEmployeeName" type="text" class="input-small" readonly /></li> -->
                    <li><span>员工：</span>
                    	<span id="editOfEmployeeName"></span>
                    	<span id="editOfEmployeeCode"></span>
                    </li>
                    <li><span>考勤时间：</span> <!-- <input id="editOfAttendanceDate" type="text" class="input-small" readonly /> -->
                    	<span id="editOfAttendanceDate"></span>
                    </li>
                    <!-- <li><span>迟到时间</span> <input id="editOfSignInOffset" type="text" class="input-small" readonly /></li>
                    <li><span>早到时间</span> <input id="editOfSignOutOffset" type="text" class="input-small" readonly /></li> -->
                    <li><span>签到时间：</span> 
                    	<!-- <input id="editOfSignInTime" type="text" class="input-xlarge timePickerClean datetimepicker" data-date-format="yyyy-mm-dd hh:ii" /> -->
                    	<input id="editOfSignInTime" type="text" class="w150" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                    </li>
                    <li><span>签退时间：</span> 
                    	<!-- <input id="editOfSignOutTime" type="text" class="input-xlarge timePickerClean datetimepicker" data-date-format="yyyy-mm-dd hh:ii" /> -->
                    	<input id="editOfSignOutTime" type="text" class="w150" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
                    </li>
                    <!-- 隐藏id -->
                    <input id="editOfRecordId" type="hidden" class="input-small"/>
                </ul>
            </div><!--modal-body-->
            <div class="modal-footer">
                <a class="btn modal-cancel" href="javascript:cancelEdit()">取消</a>
                <a class="btn btn-primary modal-confirm" href="javascript:edit()">保存</a>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript" src="<%=basePath %>js/daybook/daybook.js"></script>
<script type="text/javascript" src="<%=basePath %>js/employee/attendance.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<script>
//id为editOfSignInTime的输入框绑定日期组件(datetimepicker)
//jQuery('#editOfSignInTime').datetimepicker();
//jQuery('#editOfSignOutTime').datetimepicker();
//获取加载页面时的页码信息
var pageNo = "${page.pageNo}";
var pageSize = "${page.pageSize}";
var totalPage = "${page.totalPage}";
var totalRecord = "${page.totalRecord}";
</script>
<script type="text/javascript">
	checkPageButton();
</script>
    </div>
    <!--RIGHT PANEL结束 -->
  </div>
</div>

</body>

</html>