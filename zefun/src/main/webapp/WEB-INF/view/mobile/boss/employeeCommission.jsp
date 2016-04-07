<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ include file="/base.jsp" %>
<%@ page import="java.util.Date" %>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
<meta name="x5-orientation" content="portrait">
<meta content="telephone=no" name="format-detection" />
<meta name="msapplication-tap-highlight" content="no">
<title>员工业绩</title>
<link rel="stylesheet" href="<%=swiperCssPath%>"/>
<link rel="stylesheet" href="<%=starCssPath%>"/>
<link rel="stylesheet" href="<%=basePath%>css/iconfont.css"/>
<link rel="stylesheet" href="<%=basePath%>css/mobile/boss-newer.css"/>
<link rel="stylesheet" href="<%=basePath%>css/mobile/chart-component.css">
<link rel="stylesheet" href="<%=basePath%>css/mobile/employee-rank.css">
</head>

<body>

	<div class="loading">
		<img src="<%=basePath%>/images/mobile/boss-newer/tb-loading.gif" alt="" />
	</div>

	<div class="content hide">
		

		<div class="employee-rank chart-bg">

			<!--劳动业绩-->
			<div id="employee-rank">
				<div class="part-table pt4">
					<div class="btn-bumen s-modal-control" data-target="#actionsheet">
						<span id="positionName">全岗位</span> <span class="ml1 iconfont icon-zhankai"></span>
					</div>
					<!--year-month-day-->
					<div class="year-month-day">
						<ul>
							<li onclick="findEmployeeCommissionByDateType('year')">年</li>
							<li onclick="findEmployeeCommissionByDateType('month')">月</li>
							<li onclick="findEmployeeCommissionByDateType('day')" class="active">日</li>
						</ul>
					</div>

					<table id="tableOfEmployeeCommission">
						<thead>
							<tr>
								<th>排序</th>
								<!-- <th onclick="findEmployeeCommissionBySortType('employee_name')">员工</th> -->
								<th>员工</th>
								<th class="active" onclick="findEmployeeCommissionBySortType('cash')">现金业绩</th>
								<th onclick="findEmployeeCommissionBySortType('card')">卡金业绩</th>
								<th onclick="findEmployeeCommissionBySortType('employee_commission')">提成</th>
								<th onclick="findEmployeeCommissionBySortType('order_num')">客量</th>
							</tr>
							<tr>
								<th class="search-desc" colspan="6">已按现金业绩从高到低排序</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${employeeCommissionOfBossList }" var="employeeCommissionOfBossDto" varStatus="index">
								<tr onclick="toEmployeeCommissionDetail(${employeeCommissionOfBossDto.employeeId})">
									<td>
										<c:choose>
											<c:when test="${index.index ==0 }">
												<img src="<%=basePath%>images/mobile/boss-newer/first.png" alt="">
											</c:when>
											<c:when test="${index.index ==1 }">
												<img src="<%=basePath%>images/mobile/boss-newer/second.png" alt="">
											</c:when>
											<c:when test="${index.index ==2 }">
												<img src="<%=basePath%>images/mobile/boss-newer/three.png" alt="">
											</c:when>
											<c:otherwise>
												<fmt:formatNumber type="number" value="${index.index + 1 }" maxFractionDigits="0"/>
											</c:otherwise>
										</c:choose>
									</td>
									<td>${employeeCommissionOfBossDto.employeeName }</td>
									<td>${employeeCommissionOfBossDto.cash }</td>
									<td>${employeeCommissionOfBossDto.card }</td>
									<td>${employeeCommissionOfBossDto.employeeCommission }</td>
									<td>${employeeCommissionOfBossDto.orderNum }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

				</div>
				<!--part-chart-->

			</div>
		</div>
		
		<!--business-report(部门列表)-->
		<div class="actionsheet hide s-modal s-modal-miss" id="actionsheet">
			<div class="m-actionsheet-toggle">
				<div class="m-actionsheet-menu">
					<ul>
						<li class="actionsheet-cell" onclick="findEmployeeCommissionByPosition(-1, '全岗位')">全岗位</li>
						<c:forEach items="${positionInfoList }" var="positionInfo">
							<li class="actionsheet-cell" onclick="findEmployeeCommissionByPosition(${positionInfo.positionId}, '${positionInfo.positionName}')">
								${positionInfo.positionName}
							</li>
						</c:forEach>
					</ul>
				</div>
				<div class="m-actionsheet-action">
					<ul>
						<li class="s-modal-miss actionsheet-cell">取消</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="footer">
	    <ul>
	        <li>
	            <a href="<%=basePath %>boss/view/bossObjective/${session_key_store_id}/2">
	                <span class="iconfont icon-yewubiaobiao"></span>
	                <span>业绩报表</span>
	            </a>
	        </li>
	        <li>
	            <a href="<%=basePath %>boss/view/business/${session_key_store_id}/2">
	                <span class="iconfont icon-yingyefenxi"></span>
	                <span>营业分析</span>
	            </a>
	        </li>
	        <li>
	            <a href="<%=basePath %>boss/view/coutomer/${session_key_store_id}/2">
	                <span class="iconfont">&#xe845;</span>
	                <span class="word">客情分析</span>
	            </a>
	        </li>
	        <li class="active">
	            <a href="<%=basePath %>boss/view/employeeCommissionHome/${session_key_store_id}/2">
	                <span class="iconfont icon-yuangong"></span>
	                <span>员工表现</span>
	            </a>
	        </li>
	    </ul>
	</div>
</body>
</html>

<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<!--导航滑动所需的js-->
<script type="text/javascript" src="<%=basePath%>/js/mobile/swipe-plugin.min.js"></script>
<!--导航滑动js-->
<script type="text/javascript" src="<%=basePath%>/js/common/star-rating.min.js"></script>
<!--common js-->
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/highcharts.src.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employeeCommission.js"> </script>
<script>
	$(document).ready(function() {
		$(".loading").hide();
		$(".content").fadeIn();
	});
</script>
