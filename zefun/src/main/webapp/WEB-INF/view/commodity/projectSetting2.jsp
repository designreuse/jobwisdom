<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
<body>

	<div class="mainwrapper">
		<!--loading start-->
		<%@ include file="/loading.jsp"%>
		<!--loading end-->

		<!--left-panel start-->
		<%@ include file="/menu.jsp"%>
		<!--left-panel end-->

		<!--RIGHT PANEL开始 -->
		<div class="rightpanel" style="margin-left: 200px;">
			<%@ include file="/top.jsp"%>
			<!-- 页面代码 -->

			<div class="maincontent">
				<div class="contentinner"></div>
				<!--RIGHT PANEL结束 -->

				<div class="clearfix"></div>

				<div id="star"></div>

			</div>
		</div>
	</div>
</body>

</html>