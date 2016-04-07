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
        <div class="new-yewu-title">
            <span class="fl">&nbsp;<span style="color: #999999; font-size:1.6rem;">&nbsp;共${salesmanCount }位</span></span>
            <span class="fr yw-btn" onclick="toAddSalesman()">新增</span>
        </div>
        <c:forEach items="${salesmanInfoList }" var="salesman">
	        <ul class="new-shop-item">
	            <li class="name">
	                <span class="fl" style="text-decoration: underline" onclick="getSalesmanInfo(${salesman.salesmanId})">${salesman.name }</span> 
	                <span class="fr" style="text-decoration: underline" onclick="seeSalesmanStoreList(${salesman.salesmanId})">查看客户列表</span>
	            </li>
	            <li class="shop-detail ">
	                <ul class="shop-detail-ul">
	                    <li class="normal-li">
	                        <span class="key">电话</span>
	                        <span class="value fr">${salesman.phone }</span>
	                    </li>
	                    <li class="normal-li">
	                        <span class="key">已完成客户</span>
	                        <span class="value fr">${salesman.countStoreCompleted }</span>
	                    </li>
	                    <li class="normal-li">
	                        <span class="key">正在洽谈中客户</span>
	                        <span class="value fr">${salesman.countStoreProcessing }</span>
	                    </li>
	                    <li class="normal-li">
	                        <span class="key">状态</span>
	                        <span class="value fr">
	                        	<c:if test="${salesman.status == 0 }">正常</c:if>
	                        	<c:if test="${salesman.status == 1 }">停用</c:if>
	                        </span>
	                    </li>
	                </ul>
	            </li>
	            <li class="person-info">
	                <div class="fr">
	                    <c:if test="${salesman.status == 0 }">
	                    	<span class="yw-btn" onclick="deactivateSalesman(${salesman.salesmanId }, '${salesman.name }')">停用</span>
	                    </c:if>
	                    <c:if test="${salesman.status == 1 }">
	                    	<span class="yw-btn" onclick="reEnableSalesman(${salesman.salesmanId }, '${salesman.name }')">重新启用</span>
	                    </c:if>
	                    <span class="yw-btn" onclick="deleteSalesman(${salesman.salesmanId }, '${salesman.name }')">删除</span>
	                </div>
	            </li>
	        </ul>
        </c:forEach>
</div>

</div>
</div>
</body>
<script src="<%=jqueryJsPath%>"></script>
<script src="<%=mobileBaseJsPath%>"></script>
<script>
	//去往业务员添加页面(渠道商添加业务员)
	function toAddSalesman() {
		window.location.href = baseUrl +  "salesman/view/toAdd?agentId=" + ${agentInfo.agentId};
	}
	//停用业务员
	function deactivateSalesman(salesmanId, salesmanName) {
		if (window.confirm("确认停用" + salesmanName + "业务员吗？")) {
			jQuery.ajax({
				type : "post",
				url : baseUrl + "salesman/action/deactivate?salesmanId=" + salesmanId,
				dataType : "json",
				success : function(d) {
					if (d.code != 0) {
						dialog(d.msg);
						return ;
					}
					dialog(d.msg);
					window.location.reload();
				}
			});
		}
	}
	//重新启用业务员
	function reEnableSalesman(salesmanId, salesmanName) {
		if (window.confirm("确认重新启用" + salesmanName + "业务员吗？")) {
			jQuery.ajax({
				type : "post",
				url : baseUrl + "salesman/action/reEnable?salesmanId=" + salesmanId,
				dataType : "json",
				success : function(d) {
					if (d.code != 0) {
						dialog(d.msg);
						return ;
					}
					dialog(d.msg);
					window.location.reload();
				}
			});
		}
	}
	
	//删除业务员
	function deleteSalesman(salesmanId, salesmanName) {
		if (window.confirm("确认删除" + salesmanName + "业务员吗？")) {
			jQuery.ajax({
				type : "post",
				url : baseUrl + "salesman/action/delete?salesmanId=" + salesmanId,
				dataType : "json",
				success : function(d) {
					if(d.code != 0) {
						dialog(d.msg);
						return ;
					}
					dialog(d.msg);
					window.location.reload();
				}
			});
		}
	}
	
	//查看业务员详情
	function getSalesmanInfo(salesmanId) {
		window.location.href = baseUrl + "salesman/view/salesmanInfo?salesmanId=" + salesmanId;
	}
	//渠道商查看门下某业务员客户
	function seeSalesmanStoreList(salesmanId) {
		window.location.href = baseUrl + "salesman/view/seeSalesmanRecommendStoreNormal?salesmanId=" + salesmanId;
	}
	
</script>
</html>