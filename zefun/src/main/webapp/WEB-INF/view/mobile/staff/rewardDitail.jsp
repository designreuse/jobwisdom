<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/base.jsp" %>
<%@ page import="java.util.Date" %>
<html lang="zh-CN">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta name="x5-orientation" content="portrait">
    <meta content="telephone=no" name="format-detection" />
    <meta name="msapplication-tap-highlight" content="no">
    <title>员工奖惩明细</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
	<link rel="stylesheet" href="<%=starCssPath%>"/>
	<link rel="stylesheet" href="<%=muiCssPath%>"/>
	<link rel="stylesheet" href="<%=employeeCssPath%>"/>
   	<link rel="stylesheet" href="<%=basePath%>css/mobile/boss-newer.css"/>
   	<link rel="stylesheet" href="<%=basePath%>css/mobile/chart-component.css">
	<link rel="stylesheet" href="<%=basePath%>css/mobile/employee-detail.css">
</head>
<body>
	<div class="wrap">
		<div class="content">
			<div class="employee-detail chart-bg">
				<div id="employee-detail" class="employee-detail-wrap">
					<table id="customerEvaluationTable">
						<thead>
							<tr>
								<th>时间</th>
								<th>扣款</th>
								<th>原因</th>
							</tr>
						</thead>
						<tbody>

						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
</html>