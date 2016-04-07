<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/base.jsp"%>
<%--      <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=basePath %>assets/css/bootstrap.css" rel="stylesheet">
<link href="<%=basePath %>assets/css/bootstrap-treeview.css" rel="stylesheet"> 
<script src="<%=basePath %>js/bootstrap-treeview.js"></script>
<%-- <script src="<%=basePath %>js/jquery-1.7.1.min.js"></script> --%>
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
				</a> <a href="#" class="current">权限信息</a>
			</div>
		</div>
		
		<div class="container-fluid" style="height: 800px">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>人员授权</h5>
						</div>
				    </div>
			    </div>
	        </div>
	        
				<div>     
						<!-- 角色信息 -->
						<div style="display: inline-block; float:left;">
						<div class="search permission-add">
						<h5>人员信息</h5>
						<!-- 人员信息 -->
        				<select id="s1" onchange="findUserRolePrivilege()"> </select>
					    </div>	
						
						</div>
						
				        <div class="col-sm-4" tyle="display: inline-block; float:right;">
				          <h2>人员授权页面</h2>
          					<div id="treeview-checkable" class=""></div>
				        </div>
			        	<div class="">
			        	111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
							<a data-dismiss="modal" class="btn btn-primary btn-success" href="#"
								id="a" onclick="dataTue()">保存</a>
						</div>
				</div>
				<!-- 角色信息 -->
		 </div>
		</div>
			<!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
</body>
<script type="text/javascript">
var baseUrl = '<%=basePath%>';
var defaultData = '<%=request.getAttribute("tree")%>';
var userList = '<%=request.getAttribute("u")%>';
</script>
 <script src="<%=basePath %>js/bootstraprole.js"></script>
</html>