<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/base.jsp" %>

<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>H5微信支付测试</title>
  </head>
<body class="gray-bg">
<img src="<%=basePath %>qr_code.img?codeUrl= ${codeUrl}" style="width:300px;height:300px;"/>

<input type="text" id="amount">
<input type="button" onclick="pay()">
</body>
<script type="text/javascript" src="<%=jqueryJsPath %>"></script>
<script type="text/javascript">
function pay(){
	var amount = jQuery("#amount").val();
	jQuery.ajax({
		type : "POST",
		url : baseUrl + "app/pay/qr",
		data : "amount="+amount,
		dataType : "json",
		success : function(data) {
			jQuery("img").attr("src", baseUrl+"qr_code.img?codeUrl="+data.msg);
		}
	});
}
</script>
</html>