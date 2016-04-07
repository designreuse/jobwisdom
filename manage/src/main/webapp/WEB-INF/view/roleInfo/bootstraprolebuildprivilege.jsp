<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=basePath %>assets/css/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>assets/css/bootstrap-treeview.css" rel="stylesheet"> 
</head>
<script src="<%=basePath %>js/jquery-1.7.1.min.js"></script>
<script src="<%=basePath %>js/bootstrap-treeview.js"></script>
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
							<h5>角色权限绑定表</h5>
						</div>
				    </div>
			    </div>
	        </div>
	        
				<div>     
						<!-- 角色信息 -->
						<div style="display: inline-block; float:left;">
						<div class="search permission-add">
						<label for="">新增角色</label> <input type="text" id="roleName"
							placeholder="输入角色名" class="permission-input"> <input
							type="button" onclick="addRole()" class="btn btn-success"
							value="保存" />
					    </div>	
						<h5>已新增角色</h5>
								<ul id="rls">
									<li id="roleInfos" class="clearfix">
										 <div class="txt">平台管理员
										 <span class="date badge badge-important mgl10">
										 <a id="1" href="#" onclick = "roleHasBuildPrivilege(this)">查看权限</a>
										 </span> 
										 </div>
										 
							  </li>
								</ul>
						</div>
						
				        <div class="col-sm-4" style="display: inline-block;">
				          <!-- <div id="peopel"></div> -->
				          <div  style="width:480px; height:700px; overflow:auto;">
				          <div id="treeview-checkable" class=""></div>
				          </div>
				        </div>
							<a class="btn btn-primary btn-success" href="#" onclick="dataTue()" style="position: absolute;right: 200px;">绑定</a>
				</div>
				<!-- 角色信息 -->
		 </div>
		</div>

<!--修改元素权限 弹出层  -->
<div id="updateRole" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
								    <input type="text" id="role_id" style="display: none;"/>
									<label for="normal" class="role_name">权限名称: </label>
									<div class="controls">
										<input type="text" id="role_name" class="span11" />
									</div>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
		
			<a data-dismiss="modal" class="btn btn-primary btn-success" href="#"
				onclick="realupdateRole()">保存</a> <a data-dismiss="modal" class="btn"
				href="#">取消</a>
		</div>
	</div>		

	<!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
		
</body>
<script type="text/javascript">
var baseUrl = '<%=basePath%>';
var defaultData = '<%=request.getAttribute("tree")%>';
var roleInfo = '<%=request.getAttribute("r")%>';
</script>
 <script src="<%=basePath %>js/bootstraprolebuildprivilege.js"></script>
</html>