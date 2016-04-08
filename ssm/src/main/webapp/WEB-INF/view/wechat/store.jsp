<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript" src="<%=basePath %>js/ajax.js"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.9.1.min.js"></script>
<body>
<div>
<input type="text" value="${storeInfo.id }">
<input type="text" value="${storeInfo.date }">
</div>
<div>
<input type="text" name="date">
<input type="text" name="id">
<input type="button" value="initBuild数据绑定" onclick="initBuild()">
</div>

<div>
<input type="text" name="birth">
<input type="button" value="initConverter数据绑定" onclick="initConverter()">
</div>
<button onclick="getJson()">请求json数据</button>
<script type="text/javascript">
<!-- 接受view中数据-->
var jsonObject = '${userName}';
console.log(jsonObject);

var baseUrl = '<%=basePath%>';
function getJson(){
	jQuery.ajax({
		url: baseUrl+"wechat/upload",
        data: "menuName=Mybatis",
        type:"POST",
        success: function(data) {
           console.log(data.menuName);
        }
	});
}
function initBuild(){
	var date = jQuery("input[name='date']").val();
	var id = jQuery("input[name='id']").val();
	jQuery.ajax({
		url: baseUrl+"store/action/save",
        data: "date="+date+"&id="+id,
        type:"POST",
        success: function(data) {
           console.log(data.msg);
        }
	});
}

function initConverter(){
	var birth = jQuery("input[name='birth']").val();
	jQuery.ajax({
		url: baseUrl+"employee/action/save",
        data: "birth="+birth,
        type:"POST",
        success: function(data) {
           console.log(data.msg);
        }
	});
}
</script>
</body>
</html>