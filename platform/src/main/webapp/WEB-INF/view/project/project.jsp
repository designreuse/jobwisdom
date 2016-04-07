<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目</title>
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
		    <div class="box live-tab  setting-content">
		        <ul class="tab_menu" id="plateId">
		        	<c:forEach var="plate" items="${plateList }" varStatus="status">
			    		<li <c:if test="${status.index == 0 }">class="current"</c:if> value="${plate.plateId}">${plate.projectTypeName }</li>
			    	</c:forEach>
		        </ul>
		        <div class="huishou"><img src="<%=cssBasePath %>app/img/trash.png" alt=""/><span class="word">回收箱</span></div>
		        <div class="tab_box">
		            <!--美发设置-->
		            <div>
		                <!--增加美发设置-->
		                <div class="hair-setting">
		                  <div class="add-classify">
		                      <table class="table">
		                          <caption> 
		                          	<span style="display: inline-block;">大类设置</span>
		                          	<div class="plus" style="float: right;margin-right: 10px;" ><img src="<%=cssBasePath %>app/img/head/plus-white.png" alt="" onclick="addClasses(this);"/></div>
		                          </caption>
		                          <tbody>
		                          	<c:forEach var="classes" items="${classList}" varStatus="status">
			                    		<tr <c:if test="${classesId == classes.classesId }">class="active"</c:if> >
			                    			<td class="name" class="pl10" onclick="queryProject(${classes.classesId},this);">
			                    				<input type="hidden" value="${classes.classesId }" name="classesId"/>
			                    				${classes.classesName}
			                    			</td>
				                            <td class="alter"><img src="<%=cssBasePath %>app/img/alter.png" alt="" onclick="editInput(${classes.classesId},'${classes.classesName}',this);" class="imgClass"/></td>
				                            <td class="delete"><img src="<%=cssBasePath %>app/img/delete.png" alt="" onclick="deleteClasses(${classes.classesId},this);"/></td>
			                    		</tr>
		                    		</c:forEach>
		                          </tbody>
		                      </table>
		                  </div>
		                  <div class="add-project">
		                        <table class="table">
		                            <thead>
		                            <tr class="table-title">
		                                <td class="name" colspan="4">子项设置</td>
		                                <td class="add-staff-btn" colspan="3">
		                                    <button class="add " data-toggle="modal" data-target="#create-new-project">添加项目</button>
		                                    <div class="dropdown batch" supdiv = "supdiv">
		                                        <button type="button" class="appoints mgr10" data-toggle="dropdown" id = "mappoint" supname = "supname">批量设置</button>
		                                        <ul class="dropdown-menu" style="width:100px;">
		                                            <li><a href="javascript:void(0);" onclick="employeeBatchSet();">员工提成</a></li>
		                                            <li role="presentation" class="divider"></li>
		                                            <li><a href="javascript:void(0);" supa = "supa" onclick="membersBatchSet();">会员折扣</a></li>
		                                        </ul>
		                                    </div>
		                                </td>
		                            </tr>
		                            <tr class="p-column-name" style="height: 40px;">
		                                <td class="select-all" ><input type="checkbox" name="projectInfoId-Check" onclick="selectAll()" value="-1"/></td>
		                                <td class="code">编号</td>
		                                <td class="name">项目名称</td>
		                                <td class="money">项目价格</td>
		                                <td class="push-money">员工提成</td>
		                                <td class="discount" >会员折扣</td>
		                                <td class="f-operation" ></td>
		                            </tr>
		                            </thead>
		                            <tbody>
			                            <c:forEach var="project" items="${projectList }">
					                    	<tr>
					                    		<td style="padding-left: 0px"><input type="checkbox" name="projectInfoId-Check" value="${project.projectInfoId }"/></td>
					                    		<td><span class="projectNo">${project.projectNo }</span><input type="hidden" class="projectInfoId" value="${project.projectInfoId }"/></td>
												<td><span class="projectName">${project.projectName }</span></td>
												<td><span class="projectPrice">${project.projectPrice }</span></td>
												<c:if test="${project.statusEmp == 1}">
													<td class="yuangong-jump"><img src="<%=cssBasePath %>app/img/gray-people.png" alt="" onclick="specifiedEmp(${project.projectInfoId});"/></td>
												</c:if>
												<c:if test="${project.statusEmp == 2}">
													<td class="yuangong-jump"><img src="<%=cssBasePath %>app/img/people.png" alt="" onclick="specifiedEmp(${project.projectInfoId});"/></td>
												</c:if>
												<c:if test="${project.statusMember == 1}">
													<td class="huiyuan-jump"><img src="<%=cssBasePath %>app/img/gray-crown.png" alt="" onclick="specifiedMembers(${project.projectInfoId});"/></td>
												</c:if>
												<c:if test="${project.statusMember == 2}">
													<td class="huiyuan-jump"><img src="<%=cssBasePath %>app/img/crown.png" alt="" onclick="specifiedMembers(${project.projectInfoId});"/></td>
												</c:if>
												<td><img src="<%=cssBasePath %>app/img/alter.png" alt="" class="img-left" onclick="editProject(this);"/>
													<img src="<%=cssBasePath %>app/img/delete.png" onclick="deleteProject(${project.projectInfoId},this);" alt=""/>
												</td>
											</tr>
					                    </c:forEach>
		                            </tbody>
		                        </table>
		                    </div>
		                </div>
		            </div>
		        </div>
		    </div>
		</div>

		<!--新建项目的模态框-->
		<div class="modal fade" id="create-new-project" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content create-new-project">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		                <h3 class="modal-title">创建新项目</h3>
		            </div>
		            <div class="modal-body">
		                <form name="projectAddForm">
		                    <div class="f-group">
		                        <label for="">编号</label>
		                        <input type="text" class="whole-line" name="projectNo"/>
		                    </div>
		                    <div class="f-group">
		                        <label for="">项目名称</label>
		                        <input type="text" class="whole-line" name="projectName"/>
		                    </div>
		                    <div class="f-group">
		                        <label for="">项目价格</label>
		                        <input type="text" class="whole-line" name="projectPrice"/>
		                        <input type="hidden" value="${classesId }" name="classesId"/>
		                    </div>
		                    <div class="f-button">
		                        <button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
		                        <button type="button" class="btn btn-primary confirm" onclick="saveProjectBatch(this);">确定</button>
		                    </div>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>
		
		<div class="modal fade" id="create-update-project" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content create-new-project">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		                <h3 class="modal-title">创建新项目</h3>
		            </div>
		            <div class="modal-body">
		                <form name="projectUpdateForm">
		                    <div class="f-group">
		                        <label for="">编号</label>
		                        <input type="text" class="whole-line" name="projectNo"/>
		                        <input type="hidden" name="projectInfoId"/>
		                    </div>
		                    <div class="f-group">
		                        <label for="">项目名称</label>
		                        <input type="text" class="whole-line" name="projectName"/>
		                    </div>
		                    <div class="f-group">
		                        <label for="">项目价格</label>
		                        <input type="text" class="whole-line" name="projectPrice"/>
		                        <input type="hidden" value="${classesId }" name="classesId"/>
		                    </div>
		                    <div class="f-button">
		                        <button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
		                        <button type="button" class="btn btn-primary confirm" onclick="editProjectSave(this);">确定</button>
		                    </div>
		                </form>
		            </div>
		        </div>
		    </div>
		</div>

	</div>
	<%@ include file="/js.jsp"%>
	<script>
		var basePath = '<%=cssBasePath%>';
	</script>
	<script src="<%=cssBasePath%>js/project/project.js"></script>
</body>
</html>