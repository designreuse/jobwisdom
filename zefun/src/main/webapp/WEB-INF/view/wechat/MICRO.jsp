<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/pay.css" type="text/css" />
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right clearfix">
					<input type="text" name="authCode"> 扫码<br>
					<input type="text" name="fel"> 金额
					<button onclick="save()">模拟SOMA</button>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	function save(){
		jQuery.ajax({
			type : "POST",
			url : baseUrl + "app/pay/micro",
			data : "authCode=" + jQuery("input[name='authCode']").val() + "&fel=" + jQuery("input[name='fel']").val(),
			dataType : "json",
			success : function(data) {
				dialog(data.msg);
			}
		});
	}
</script>
</html>