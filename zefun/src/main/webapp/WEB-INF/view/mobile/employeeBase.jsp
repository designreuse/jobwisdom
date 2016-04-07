<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String memberBasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<script type="text/javascript" src="<%=memberBasePath%>js/mobile/jquery.min.js"></script>
<script type="text/javascript" src="<%=memberBasePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=memberBasePath%>js/mobile/base.js"></script>