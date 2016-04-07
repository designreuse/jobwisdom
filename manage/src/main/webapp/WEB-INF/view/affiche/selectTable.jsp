<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>后台管理</title>
</head>
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
				</a>
				<!-- <a href="" class="tip-bottom" data-original-title="销售管理">销售管理</a>-->
				<a href="#" class="current">公告列表</a>
			</div>
		</div>


		<div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon"><i class="icon-th"></i></span>
						<h5>用户列表</h5>
					</div>
                    <div id="toolbar" class="btn-group">
                            <button type="button" class="btn btn-default" style="height: 30px; margin-bottom:6px; margin-left:6px;"onclick="gotoadd()">
                                <i class=" icon-plus">新增</i>
                            </button>
                        </div>
					<!-- data-url 指定调用的接口方法  data-formatter    data-events-->
					<table data-toggle="table" data-method="post"
						data-url="<%=basePath%>SystemAffiche/selectTable"		
						data-pagination="true" data-side-pagination="server"
						data-query-params="queryParams"
						data-page-list="[5, 10, 20, 50, 100, 200]" data-height="490"
						id="leadsMod1" data-toolbar="#toolbar">
						<thead>
							<tr>
								<th data-field="afficheTime">发布时间</th>
								<th data-field="afficheClasses">公告类别</th>
								<th data-field="afficheTitle" >公告主题</th>
								
								<th data-field="creatorId">发布人</th>
								<th data-field="stateName">发布状态</th>
								<th data-field="operate" data-sortable="true"
									data-formatter="leadsMod1" data-events="operateEvents">操作</th>
							</tr>
						</thead>
					</table>
					</a>
					<!--</div>-->
				</div>
			</div>
		</div>
		<input type="hidden" id="afficheId">
	</div>
	<!-- 已设置项目类别修改Modal -->
	<div id="projectMod" class="modal hide">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h5>修改项目</h5>
		</div>
		<div class="modal-body nopadding">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box" style="margin: 0px;">
						<div class="widget-content nopadding">
							<form action="#" class="form-horizontal">
								<div class="control-group">
									<div class="span12">
										<table class="span12">
											<div class="span4">
												<label class="gg-label">公告标题 :</label> <input type="text"
														id="afficheTitle" class="span8" />
												</div>
														<div class="span4">
															<label class="gg-label">公告类别 :</label> <select name=""
																id="afficheClasses" class="span8">
																<option value="1">类别一</option>
																<option value="2">类别二</option>
																<option value="3">类别三</option>
															</select>
														</div>
		
													<div class="search-list">
														<div class="span12">
															<div class="span4">
																<label for="" class="span4">公告部门</label> <select name=""
																	id="afficheRole" class="span8">
																	<option value="1">部门一</option>
																	<option value="2">部门二</option>
																	<option value="3">部门三</option>
																</select>
															</div>
														</div>
													</div>
											
													<div class="widget-box">
														<div class="widget-title">
															<span class="icon"> <i class="icon-align-justify"></i>
															</span>
															<h5>公告内容</h5>
														</div>
														<div class="widget-content">
															<div class="control-group">
			
																	<div class="controls">
																		<div class="summernote"></div>
																		<textarea class="textarea_editor span12" rows="6"
																			id="afficheContent"></textarea>
																	</div>
															</div>
														</div>
													</div>
												</div>
										</table>
									</div>
								</div>

							</form>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer"> <a ondata-dismiss="modal" class="btn btn-primary btn-success" href="#" onclick="updateAffiche()">保存修改</a> <a data-dismiss="modal" class="btn" href="#">取消</a> </div>
			<!--尾部代码页面引用-->
			<%@include file="/WEB-INF/view/footer.jsp"%>
			<%@include file="/WEB-INF/js.jsp"%>
			<!--引入定义的绝对路径  -->
			<script type="text/javascript">
				var baseUrl = "<%=basePath%>"; 
				
				function gotoadd(){
					location.href='<%=basePath %>SystemAffiche/view/affiche/addAffiche';
				}
			</script>
			<script type='text/javascript' src='<%=basePath%>js/systemaffiche.js'></script>
			
			