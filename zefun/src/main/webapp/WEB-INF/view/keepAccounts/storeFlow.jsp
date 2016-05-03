<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/payment.css" type="text/css" />
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right">
					<div class="add_menu_content">
						<div class="add_menu">新增账单</div>
						<table class="add_menu_table">
							<tbody>
								<tr>
									<td>记账金额</td>
									<td>流水类型</td>
									<td>记账部门</td>
									<td>记账类别</td>
									<td>记账账目</td>
									<td>收支来源去向</td>
									<td>经手人</td>
									<td>分摊月数</td>
								</tr>
								<tr>
									<td><input type="number" name="flowAmount" placeholder="请输入金额"></td>
									<td><select data-placeholder="流水类型" name="flowType">
											<option value="">选择流水类型</option>
			                                <option value="1">支出</option>
			                                <option value="2">收入</option>
									</select></td>
									<td><select data-placeholder="记账部门" name="deptId" onchange="employeeValue(this)">
											<option value="">选择部门</option>
				                            <c:forEach items="${deptInfoDtoList}" var="deptInfoDto" varStatus="status">
				                               <option value="${deptInfoDto.deptId}">${deptInfoDto.deptName}</option>
				                            </c:forEach>
									</select></td>
									<td><select data-placeholder="记账类别" name="businessType">
											<option value="">选择记账类别</option>
				                            <c:forEach items="${businessTypeList}" var="businessType">
				                               <option value="${businessType.codeName}">${businessType.codeName}</option>
				                            </c:forEach>
									</select></td>
									<td><select data-placeholder="记账项目" name="businessProject">
											<option value="">选择记账项目</option>
					                        <c:forEach items="${businessProjectList}" var="businessProject">
				                                <option value="${businessProject.codeName}">${businessProject.codeName}</option>
				                            </c:forEach>
									</select></td>
									<td><select data-placeholder="收支来源及去向" name="flowSource">
											 <option value="">选择收支来源及去向</option>
				                             <c:forEach items="${flowSourceList}" var="flowSource">
			                                    <option value="${flowSource.codeName}">${flowSource.codeName}</option>
			                                 </c:forEach>
									</select></td>
									<td><select data-placeholder="选择经手人" name="principalId">
											<option value="">选择经手人</option>
											<c:forEach items="${employeeDtos}" var="employee">
			                                    <option value="${employee.employeeId}">${employee.employeeCode} ${employee.name}</option>
			                                </c:forEach>
									</select></td>
									<td><input type="number" name= "flowNum" placeholder="分摊月数"></td>
								</tr>

								<tr>
									<td colspan="8"><input type="text" name="businessDesc" placeholder="备注说明" style="width: 1000px; height: 40px; text-align: left; padding-left: 30px"></td>
								</tr>

							</tbody>
						</table>
						<button id="save" class="add_save">保存</button>
					</div>

					<div class="balance_content">
						<div class="balance">收支明细：</div>
						<input type="date" id="begin_time" style="color: #d7d8da; border: 1px solid #d7d8da"> <span style="display: inline-block; margin: 0 10px; color: #ccc">-——</span> 
						<input type="date" id="end_time" class="datetime_" style="color: #d7d8da; border: 1px solid #d7d8da">

						<button class="im_search">立刻查询</button>
						<table class="add_menu_table_" style="margin-top: 20px">
							<thead>
								<tr>
									<td>收支类别</td>
									<td>收支项目</td>
									<td>收支来源/去向</td>
									<td>收入金额</td>
									<td>支出金额</td>
									<td>经手人</td>
									<td>记账人</td>
									<td>记账时间</td>
									<td>分期月数</td>
									<td>备注说明</td>
									<td>删除该项</td>
								</tr>
							</<thead>
							<tbody id="storeflowTbody">
							<c:forEach items="${page.results }" var="storeflow" varStatus="index">
		                  	<c:if test="${index.index == 0 }"><tr id="${storeflow.flowId}" class="t-table"></c:if>
		                  	<c:if test="${index.index != 0 }"><tr id="${storeflow.flowId}"></c:if>
			                        <td>${storeflow.businessType}</td>
			                        <td>${storeflow.businessProject}</td>
			                        <td>${storeflow.flowSource}</td>
			                        <td><c:if test="${storeflow.flowType == 2}">${storeflow.flowAmount}</c:if></td>
			                        <td><c:if test="${storeflow.flowType == 1}">${storeflow.flowAmount}</c:if></td>
			                        <td>${storeflow.principal.name}</td>
			                        <td>${storeflow.operator.name}</td>
			                        <td>${storeflow.flowTime}</td>
			                        <td>${storeflow.flowNum}</td>
			                        <td>${storeflow.businessDesc}</td>
			                        <td>
			                            <i class="iconfa-trash project-icon" onclick="deleteStoreflow(${storeflow.flowId})"></i>
			                        </td>
			                    </tr>
		                  </c:forEach>
							</tbody>
						</table>
						<%@ include file="/template/page.jsp" %>
						<!-- <div class="num">
							<a href="" class="index">首页</a> 
							<a href="" class="prev">上一页</a> 
							<a href="" class="number active">1</a> 
							<a href="" class="number">2</a> 
							<a href="" class="number">3</a>
							<a href="" class="number">4</a>
							<span style="position: relative; top: -4px; left: 5px"> ....</span> <a href="" class="number">7</a> <a href="" class="next">下一页</a> <a href="" class="last">尾页</a>
						</div> -->
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
/**部门与员工级联*/
function employeeValue(obj){
	var values = jQuery(obj).val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/getDeptEmployee",
		data : "deptId=" + values,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
			}else{
				jQuery("select[name='principalId']").empty();
				jQuery("select[name='principalId']").append("<option value=''>选择经手人</option>");
				jQuery.each(e.msg,function(i,employees){
					jQuery("select[name='principalId']").append("<option value='"+employees.employeeId+"'>"+employees.employeeCode+" "+employees.name+"</option>");
				});
			}
		}
	});
}
/**保存开支记账信息*/
jQuery("#save").click(function(){
	var flowAmount = jQuery("input[name='flowAmount']").val();
	var deptId = jQuery("select[name='deptId']").val();
	var businessType = jQuery("select[name='businessType']").val();
	var businessProject = jQuery("select[name='businessProject']").val();
	var flowSource = jQuery("select[name='flowSource']").val();
	var principalId = jQuery("select[name='principalId']").val();
	var flowType = jQuery("select[name='flowType']").val();
	var flowNum = jQuery("input[name='flowNum']").val();
	var businessDesc = jQuery("input[name='businessDesc']").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/addStoreFlow",
		data : "flowAmount="+flowAmount+"&deptId="+deptId+"&businessType="+businessType+"&businessProject="+businessProject+"&flowSource="+flowSource+"&principalId="+principalId+"&flowType="+flowType+"&flowNum="+flowNum+"&businessDesc="+businessDesc,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				return;
			}
			dialog("提交成功,即将刷新页面...");
			window.location.href = baseUrl + "KeepAccounts/initializeStoreFlow";
		}
	})
})

/**分页处理*/
function changePage(){
	var beginTimes = jQuery("#begin_time").val();
	var endTimes = jQuery("#end_time").val();
	var beginTime = parseInt(beginTimes.replace("/","").replace("/",""));
	var endTime = parseInt(endTimes.replace("/","").replace("/",""));
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/storeFlowList",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize + "&beginTime=" + beginTime + "&endTime=" + endTime,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var obj = e.msg;
			refreshTableData(obj.page);
			updateCome(obj.inComeAll,obj.outComeAll);
			checkPageButton();
			totalRecord = obj.page.totalRecord;
			if(isFindDate){
				unbuildPagination();
				isFindDate = false;
			}
		}
	});
}
</script>
</html>