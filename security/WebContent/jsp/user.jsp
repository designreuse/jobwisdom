<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>Insert title here</title>
</head>
<body>
	<h5><a href="../j_spring_security_logout">logout</a></h5>
	<!-- 拥有ROLE_ADMIN权限的才看的到 -->
	<sec:authorize access="hasRole('ROLE_ADMIN')">
	显示拥有ROLE_ADMIN权限的页面<br/>
	<form action="#">
		账号：<input type="text" /><br/>
		密码：<input type="password"/><br/>
		<input type="submit" value="submit"/>
	</form>
	</sec:authorize>
	
	<p/>
	<sec:authorize access="hasRole('ROLE_USER')">
	显示拥有ROLE_USER权限的页面<br/>
	<form action="#">
		账号：<input type="text" /><br/>
		密码：<input type="password"/><br/>
		<input type="submit" value="submit"/>
	</form>
	
	</sec:authorize>
	<p/>
	<h5>测试方法控制访问权限</h5>
	<a href="addreport_admin.do">添加报表管理员</a><br/>
	<a href="deletereport_admin.do">删除报表管理员</a>
</body>
</html>