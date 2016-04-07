<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.Date" %>
<%@ include file="/base.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
    <title>业务员列表</title>
</head>
<body class="gray-bg">
	<div class="wrap">
		<div class="new-yewu">
			<div class="new-yewu-list">
				<ul class="new-shop-item">
					<li class="name">
		                <span class="fl">${salesman.name }</span> 
		            </li>
					<li class="shop-detail ">
						<ul class="shop-detail-ul">
							<li class="normal-li"><span class="key">电话</span> <span class="value fr">${salesman.phone }</span></li>
							<li class="normal-li"><span class="key">姓名</span> <span class="value fr">${salesman.name }</span></li>
							<li class="normal-li"><span class="key">年龄</span> <span class="value fr">${salesman.age }</span></li>
							<li class="normal-li"><span class="key">性别</span> 
								<span class="value fr">
									<c:if test="${salesman.gender==0 }">男</c:if>
									<c:if test="${salesman.gender==1 }">女</c:if>
								</span>
							</li>
							<li class="normal-li"><span class="key">创建时间</span> <span class="value fr">${salesman.createTime }</span></li>
							<li class="normal-li"><span class="key">备注</span> <span class="value fr">${salesman.comment }</span></li>
							<li class="normal-li"><span class="key">已完成客户</span> <span class="value fr">${salesman.countStoreCompleted }</span></li>
							<li class="normal-li"><span class="key">正在洽谈中客户</span> <span class="value fr">${salesman.countStoreProcessing }</span></li>
							<li class="normal-li">
								<span class="key">状态</span> 
									<span class="value fr"> 
										<c:if test="${salesman.status == 0 }">正常</c:if>
										<c:if test="${salesman.status == 1 }">停用</c:if>
								</span>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>