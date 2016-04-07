<%@page import="net.sf.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%
String path2 = request.getContextPath();
String basePath2 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path2 + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
      <meta charset="UTF-8">
     
  </head>
  <body>
	<script src="<%=basePath2 %>assets/js/excanvas.min.js"></script>
	<script src="<%=basePath2 %>assets/app/js/highcharts.js"></script>
	<script src="<%=basePath2 %>assets/js/jquery.ui.custom.js"></script>
	<script src="<%=basePath2 %>assets/js/bootstrap.min.js"></script>
	<!--<script src="assets/js/jquery.flot.min.js"></script>
      <script src="assets/js/jquery.flot.resize.min.js"></script>-->
	<script src="<%=basePath2 %>assets/js/jquery.peity.min.js"></script>
	<script src="<%=basePath2 %>assets/js/fullcalendar.min.js"></script>
	<script src="<%=basePath2 %>assets/js/matrix.js"></script>
	<!--<script src="assets/js/matrix.dashboard.js"></script>-->
	<script src="<%=basePath2 %>assets/js/jquery.gritter.min.js"></script>
	<script src="<%=basePath2 %>assets/js/matrix.interface.js"></script>
	<script src="<%=basePath2 %>assets/js/jquery.validate.js"></script>
	<script src="<%=basePath2 %>assets/js/matrix.form_validation.js"></script>
	<script src="<%=basePath2 %>assets/js/jquery.wizard.js"></script>
	<script src="<%=basePath2 %>assets/js/jquery.uniform.js"></script>
	<!--<script src="assets/js/select2.min.js"></script>-->
	<script src="<%=basePath2 %>assets/js/matrix.popover.js"></script>
	<script src="<%=basePath2 %>assets/js/jquery.dataTables.min.js"></script>
	<script src="<%=basePath2 %>assets/js/matrix.tables.js"></script>
	<!--<script src="assets/js/matrix.chat.js"></script>-->
	<script src="<%=basePath2 %>assets/js/bootstrap-datepicker.js"></script>

	<!--Bootrap-table-->
	<script src="<%=basePath2 %>assets/bootstrap-table/bootstrap-table.js"
		type="text/javascript"></script>
	<!--支持中文bootstap-table-->
	<script
		src="<%=basePath2 %>assets/bootstrap-table/bootstrap-table-zh-CN.js"
		type="text/javascript"></script>
	<!--editor-->
	<!-- 公告编辑器 -->
	<script src="<%=basePath2 %>assets/editor/summernote.min.js"type="text/javascript"></script>
	
	<script src="<%=basePath2 %>assets/editor/summernote-zh-CN.js" type="text/javascript"></script>
		
	<script
		src="<%=basePath2 %>assets/datetimepicker/bootstrap-datetimepicker.min.js"
		type="text/javascript"></script>
<script  type='text/javascript' src='<%=basePath2 %>assets/app/js/PCASClass.js'></script>
<script type="text/javascript" src="<%=basePath2 %>assets/js/bootstrap-multiselect.js"></script>


<!-- <script  type='text/javascript' src='<%=basePath2 %>assets/app/js/PCASClass.js'></script> -->
<script type="text/javascript">
//new PCAS("province3","city3","area3");

var baseUrl = "<%=basePath2 %>";
</script>
  </body>
</html>