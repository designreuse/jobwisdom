<%@page import="com.zefun.web.entity.MemberMenu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String topBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/";
String qiniu = "http://7xss26.com1.z0.glb.clouddn.com/";
%>
<div class="headerpanel">
	<div class="headerlist">
		<div class="header_">
			<div class="header_content">
				<input type="text" class="input_" value="搜索"> <img src="<%=basePath%>images/seach.png"> <span class="header_img"><img src="<%=basePath%>images/logo_.png"></span> <select>
					<option>111</option>
					<option>111</option>
				</select>
			</div>
		</div>
	</div>
</div>