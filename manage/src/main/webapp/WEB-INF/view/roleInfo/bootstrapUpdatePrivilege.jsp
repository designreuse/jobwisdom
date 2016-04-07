<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>后台管理</title>
<body>

	<!--头部页面引用-->
	<%@include file="/WEB-INF/view/topheader.jsp"%>
	<!--左侧菜单页面引用-->
	<%@include file="/WEB-INF/view/sliderbar.jsp"%>


	<div id="content">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="<%=basePath %>homepage/view/query" class="tip-bottom" data-original-title="主页">
					<i class="icon-home"></i>首页看板
				</a> <a href="#" class="current">权限信息</a>
			</div>
		</div>

           
			
		<div class="container-fluid" style="height: 800px">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>权限列表</h5>
						</div>
						
						 <!-- tabs -->
						<ul id="myTab" class="nav nav-tabs">
						   <li class="active"><a href="#home" data-toggle="tab">菜单权限信息 </a> </li>
						   <li><a href="#ios" data-toggle="tab">功能权限信息</a></li>
						   <li><a href="#jmeter" data-toggle="tab">元素权限信息</a></li>
						</ul>
						
						<div id="myTabContent" class="tab-content">
						   <div class="tab-pane fade in active" id="home">
						   <button type="button" class="btn btn-default">
								<i class="icon-refresh" onclick="refreshTable()">刷新</i>
						   </button>
						   <button type="button" class="btn btn-default">
								<i class=" icon-plus" onclick="addTableMenu()">新增</i>
						   </button>
						   <!-- 菜单权限信息 tabs -->
						   <table data-toggle="table"
                               data-method="post"
                               data-url="<%=basePath%>privilege/showPrivilegeInfo"
                               data-side-pagination="server" 
                               data-pagination="true" 
                               data-page-list="[5, 10, 20, 50, 100, 200]"
                               data-query-params="queryParams"
                               data-height="800" id="utable"
                               data-toolbar="#toolbar"
                               >
                            <thead>
                                <tr>
                                    <th data-field="id" data-sortable="true">主键ID</th>
                                    <th data-field="text" data-align="center" >权限名称</th>
                                    <th data-field="url" >链接地址</th>
                                    <th data-field="classname" >css样式表</th>
                                    <th data-field="fatherName" >所属菜单</th>
                                    <th data-field="operate"  data-formatter="operateFormatter"
                                        data-events="operateEvents">操作</th>
                                </tr>
                            </thead>
                        </table>
						   </div>
						   
						   <div class="tab-pane fade" id="ios">
						   <button type="button" class="btn btn-default">
								<i class="icon-refresh" onclick="refreshTable1()">刷新</i>
						   </button>
						   <button type="button" class="btn btn-default">
								<i class=" icon-plus" onclick="addTableGn()">新增</i>
						   </button>
						   <!-- 功能权限信息 tabs -->
						    <table data-toggle="table"
	                               data-method="post"
	                               data-url="<%=basePath%>privilege/showPrivilegeGnInfo"
	                               data-side-pagination="server" 
	                               data-pagination="true" 
	                               data-page-list="[5, 10, 20]"
	                               data-query-params="queryParamsForGn"
	                               data-height="490" id="utable1"
	                               data-toolbar="#toolbar">
	                            <thead>
	                                <tr>
	                                    <th data-field="id" data-sortable="true">主键ID</th>
	                                    <th data-field="text" data-align="center" data-sortable="true">权限名称</th>
	                                    <th data-field="url" data-sortable="true">链接地址</th>
	                                    <th data-field="operate" data-sortable="true" data-formatter="operateFormatterGn"
	                                        data-events="operateEventsGn">操作</th>
	                                </tr>
	                            </thead>
                       	 </table>
						   </div>
						   
						   <div class="tab-pane fade" id="jmeter">
						   <button type="button" class="btn btn-default">
								<i class="icon-refresh" onclick="refreshTable2()">刷新</i>
						   </button>
						   <button type="button" class="btn btn-default">
								<i class=" icon-plus" onclick="addTableYs()">新增</i>
						   </button>
						   <!-- 元素权限信息 tabs -->
						    <table data-toggle="table"
	                               data-method="post"
	                               data-url="<%=basePath%>privilege/showPrivilegeYsInfo"
	                               data-side-pagination="server" 
	                               data-pagination="true" 
	                               data-page-list="[5, 10, 20, 50, 100, 200]"
	                               data-query-params="queryParamsForYs"
	                               data-height="800" id="utable2"
	                               data-toolbar="#toolbar">
	                            <thead>
	                                <tr>
	                                    <th data-field="id" data-sortable="true">主键ID</th>
	                                    <th data-field="text" data-align="center" data-sortable="true">权限名称</th>
	                                    <th data-field="url" data-sortable="true">链接地址</th>
	                                    <th data-field="classname" data-sortable="true">控制按钮样式</th>
	                                    <th data-field="operate" data-sortable="true" data-formatter="operateFormatterYs"
	                                        data-events="operateEventsYs">操作</th>
	                                </tr>
	                            </thead>
                       		 </table>
						   </div>
						   
						</div>
						
						 
					</div>
				</div>
			</div>
		</div>
		
	</div>

<!--新增菜单权限 弹出层  -->
<div id="addMenu" class="modal hide" style="width: 360px">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h5>权限信息录入</h5>
		</div>
		<div class="modal-body nopadding" style="text-align: center">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box" style="margin: 0px;">
						<div class="widget-content nopadding">
								<div class="control-group">
									<label for="normal" class="p_name">权限名称: </label>
									<div class="controls">
										<input type="text" id="p_name" class="span11" />
									</div>
									<label for="normal" class="p_url">链接地址: </label>
									<div class="controls">
										<input type="text" id="p_url" class="span11" />
									</div>
									<label for="normal" class="p_css">css样式: </label>
									<div class="controls">
										<input type="text" id="p_css" class="span11" />
									</div>
										<label for="normal" class="p_father">父级菜单: </label>
									<select class="width20" id="s1">
									</select>
								</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="modal-footer">
			<a data-dismiss="modal" class="btn btn-primary btn-success" href="#"
				onclick="addsaveMenu()">保存</a> <a data-dismiss="modal" class="btn"
				href="#">取消</a>
		</div>
	</div>
	
<!--修改菜单权限 弹出层  -->
<div id="updateMenu" class="modal hide">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h5>权限信息录入</h5>
		</div>
		<div class="modal-body nopadding">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box" style="margin: 0px;">
						<div class="widget-content nopadding">
								<div class="control-group">
								    <input type="text" id="p_id_update" style="display: none;"/>
									<label for="normal" class="p_name">权限名称: </label>
									<div class="controls">
										<input type="text" id="p_name_update" class="span11" />
									</div>
									<label for="normal" class="p_url">链接地址: </label>
									<div class="controls">
										<input type="text" id="p_url_update" class="span11" />
									</div>
									<label for="normal" class="p_css">css样式: </label>
									<div class="controls">
										<input type="text" id="p_css_update" class="span11" />
									</div>
										<label for="normal" class="p_father_update">父级菜单: </label>
									<select class="width20" id="s2">
									</select>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<a data-dismiss="modal" class="btn btn-primary btn-success" href="#"
				onclick="realupdateMenu()">保存</a> <a data-dismiss="modal" class="btn"
				href="#">取消</a>
		</div>
	</div>
	
<!--新增功能权限 弹出层  -->
<div id="addGn" class="modal hide">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h5>权限信息录入</h5>
		</div>
		<div class="modal-body nopadding">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box" style="margin: 0px;">
						<div class="widget-content nopadding">
								<div class="control-group">
									<label for="normal" class="p_name">权限名称: </label>
									<div class="controls">
										<input type="text" id="p_Gn_name" class="span11" />
									</div>
									<label for="normal" class="p_url">链接地址: </label>
									<div class="controls">
										<input type="text" id="p_Gn_url" class="span11" />
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="modal-footer">
			<a data-dismiss="modal" class="btn btn-primary btn-success" href="#"
				onclick="addsaveGn()">保存</a> <a data-dismiss="modal" class="btn"
				href="#">取消</a>
		</div>
	</div>
	
<!--修改功能权限 弹出层  -->
<div id="updateGn" class="modal hide">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h5>权限信息录入</h5>
		</div>
		<div class="modal-body nopadding">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box" style="margin: 0px;">
						<div class="widget-content nopadding">
								<div class="control-group">
								    <input type="text" id="p_Gn_id_update" style="display: none;"/>
									<label for="normal" class="p_name">权限名称: </label>
									<div class="controls">
										<input type="text" id="p_Gn_name_update" class="span11" />
									</div>
									<label for="normal" class="p_url">链接地址: </label>
									<div class="controls">
										<input type="text" id="p_Gn_url_update" class="span11" />
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<a data-dismiss="modal" class="btn btn-primary btn-success" href="#"
				onclick="realupdateGn()">保存</a> <a data-dismiss="modal" class="btn"
				href="#">取消</a>
		</div>
	</div>

<!--新增元素权限 弹出层  -->
<div id="addYs" class="modal hide">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h5>权限信息录入</h5>
		</div>
		<div class="modal-body nopadding">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box" style="margin: 0px;">
						<div class="widget-content nopadding">
								<div class="control-group">
									<label for="normal" class="p_name">权限名称: </label>
									<div class="controls">
										<input type="text" id="p_Ys_name" class="span11" />
									</div>
									<label for="normal" class="p_url">链接地址: </label>
									<div class="controls">
										<input type="text" id="p_Ys_url" class="span11" />
									</div>
									<label for="normal" class="p_css">css样式: </label>
									<div class="controls">
										<input type="text" id="p_Ys_css" class="span11" />
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="modal-footer">
			<a data-dismiss="modal" class="btn btn-primary btn-success" href="#"
				onclick="addsaveYs()">保存</a> <a data-dismiss="modal" class="btn"
				href="#">取消</a>
		</div>
	</div>
	
<!--修改元素权限 弹出层  -->
<div id="updateYs" class="modal hide" >
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h5>权限信息录入</h5>
		</div>
		<div class="modal-body nopadding">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box" style="margin: 0px;">
						<div class="widget-content nopadding">
								<div class="control-group">
								    <input type="text" id="p_Ys_id_update" style="display: none;"/>
									<label for="normal" class="p_name">权限名称: </label>
									<div class="controls">
										<input type="text" id="p_Ys_name_update" class="span11" />
									</div>
									<label for="normal" class="p_url">链接地址: </label>
									<div class="controls">
										<input type="text" id="p_Ys_url_update" class="span11" />
									</div>
									<label for="normal" class="p_css">按钮样式: </label>
									<div class="controls">
										<input type="text" id="p_Ys_css_update" class="span11" />
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<a data-dismiss="modal" class="btn btn-primary btn-success" href="#"
				onclick="realupdateYs()">保存</a> <a data-dismiss="modal" class="btn"
				href="#">取消</a>
		</div>
	</div>
		
	<!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
	<script type="text/javascript">
	var baseUrl = '<%=basePath%>';
	</script>
    <script src="<%=basePath%>js/bootstrapUpdatePrivilege.js"></script>
</body>
</html>