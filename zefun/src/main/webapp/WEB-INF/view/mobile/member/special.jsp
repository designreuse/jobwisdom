<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>特殊服务</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath %>css/mobile/share.min.css" />
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
  </head>
<body>
${specialService.content }
 	<div id="footer">
 		<div class="f-con c">
			<a class="btn btn-primary" href="<%=basePath %>memberCenter/view/dateAppointment?employeeId=${employeeId }">立即预约</a>
 		</div>
 	</div>
</body>
</html>