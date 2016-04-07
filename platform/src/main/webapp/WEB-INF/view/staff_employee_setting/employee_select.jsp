<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位设置</title>

<%@include file="/css.jsp" %>
</head>
<body>
  <%@include file="/head.jsp" %> 
  <div class="container">
    <%@include file="/sidebar.jsp" %>
    <div id="content" class="fl">
 		<div class="content-submenu">
		    <ul class="submunu clearfix">
		        <li >
		            <a href="index.html">
		                <span>散客收银</span>
		            </a>
		        </li>
		        <li>
		            <a href="vip.html">
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
	    <div class="box live-tab setting-content">
		    <ul class="tab_menu">
		        <li id="staffPage">岗位设置</li>
		        <li class="current" >员工设置</li>
		    </ul>
		    <div class="tab_box">
		        <div class="add-project staff-setting">
	              <table class="table big-table">
	                 <caption>
	                      <ol class="breadcrumb">
	                          <li><a href="#">员工设置</a></li>
	                          <li class="active">员工列表</li>
	                      </ol>
	                  </caption>
	                  <thead>
	                      <tr class="table-title">
	                          <td class="add-staff-btn" colspan="7">
	                              <button class="add add-people" data-toggle="modal" data-target="#create-member-card" style="float: left;margin-top: 5px;" id="addEmployee">添加新员工</button>
	                              <button type="button" class="more width100 fl mgt5 mgl10" style="text-align: center;">更多</button>
	                              <div class="search mgl10 mgt5">
	                                  <input type="search" class="serch-input" name="searchEmployee"/>
	                                  <span class="glyphicon glyphicon-search"></span>
	                              </div>
	                              <div class="fr">
	                                  <div class="per-page fl">
	                                                                                                           每页显示 
	                                    <div class="btn-group dropdown" supdiv = "supdiv">
	                                    <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "pageSize" supname = "supname" style="width: 60px;height: 30px;">5</button>
										    <ul class="dropdown-menu dropdownUL" style="width: 60px;">
		                                           <li><a href="javascript://" supa = "supa">5</a></li>
		                                           <li class="divider"></li>
		                                           <li><a href="javascript://" supa = "supa">10</a></li>
		                                           <li class="divider"></li>
		                                           <li><a href="javascript://" supa = "supa">15</a></li>
		                                           <li class="divider"></li>
		                                           <li><a href="javascript://" supa = "supa">20</a></li>										   
										    </ul>
									    </div>
	                                  </div>
	                                  <div class="page-num fl">
	                                                                                                            页数 ${pageNo}/${totalPage}
	                                    <input type="hidden" name ="pageNo" value="${pageNo}">
	                                    <input type="hidden" name ="totalPage" value="${totalPage}">
	                                  </div>
	                                  <div class="page-control fl">
	                                      <button class="prev-page mgr10" id="prevEmployeePage" disabled="disabled"><span class="glyphicon glyphicon-chevron-left"></span></button>
	                                      <button class="next-page" id="nextEmployeePage"><span class="glyphicon glyphicon-chevron-right"></span></button>
	                                  </div>
	                              </div>
	                          </td>
	                      </tr>
	                      <tr class="big-title">
	                          <td class="img"></td>
	                          <td class="name">姓名</td>
	                          <td class="sex">性别</td>
	                          <td class="station">岗位</td>
	                          <td class="post">职位</td>
	                          <td class="own-shop">所属店铺</td>
	                          <td class="add-people"></td>
	                      </tr>
	                  </thead>
	                  <tbody>
	                    <c:forEach var="employeeDto" items="${listEmployeeDto}" varStatus="status">
	                       <tr id="employeeTR">
	                          <td class="img"><img src="${employeeDto.headImg}" alt=""/><input type="hidden" name="employeeId" value="${employeeDto.employeeId}"></td>
	                          <td>${employeeDto.employeeName}</td>
	                          <td>${employeeDto.sex}</td>
	                          <td>${employeeDto.postMessage.postMessageName}</td>
	                          <td>${employeeDto.position.positionName}</td>
	                          <td>${employeeDto.stores.storesName}</td>
	                          <td class="tar"><img src="<%=cssBasePath %>app/img/alter.png" alt="" class="img-left" id="employeeAlter"/><img src="<%=cssBasePath %>app/img/delete.png" alt="" id="employeeDelete"/></td>
	                      </tr>
	                    </c:forEach>
	                  </tbody>
	              </table>
	          </div>
		    </div>
		</div>

	</div>
  </div>
  <%@ include file="/js.jsp"%>
  <script type="text/javascript">
  var baseUrl = "<%=cssBasePath%>";
  var employeeList ="";
  $(function(){
	  var pageNo = $("input[name='pageNo']").val();
      var totalPage = $("input[name='totalPage']").val(); 
      if(pageNo == totalPage){
    	  $("#nextEmployeePage").attr("disabled","disabled");
      }else{
    	  $("#nextEmployeePage").removeAttr("disabled");
      }
      if(pageNo == 1){
    	  $("#prevEmployeePage").attr("disabled","disabled");
      }else{
    	  $("#prevEmployeePage").removeAttr("disabled");
      }
      
  });
  </script>
  <script type="text/javascript" src="<%=cssBasePath %>js/staff_employee_setting/staff_employee_setting.js"></script>
</body>
</html>
