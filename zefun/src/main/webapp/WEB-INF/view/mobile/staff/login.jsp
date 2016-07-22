<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>内部登录</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
</head>
<body style="background:none;height:100%;overflow:hidden;background:url('<%=basePath%>images/mobile/newemployee/login_back.png') no-repeat;background-size:100% 100%;height:100%">
	<div class="con" style="">
       <div class="login">
	     <div class="login_content " >
		    <p><img src="<%=basePath%>images/login_logo.png"></p>
			<div class="login_shop_name">${enterpriseName}</div>
			<div class="login_imformation first">
			   <div class="business_no"> 
			     <span><img src="<%=basePath%>images/mobile/newemployee/employee_no.png"><input type="tel" id="phone" placeholder="手机号"></span>
			   </div>
			    <div class="business_no"> 
			     <span><img src="<%=basePath%>images/mobile/newemployee/password.png"><input type="password" id="password" placeholder="密码"></span>
			   </div>
			   
			   <button onclick="javascript:login();">登录</button>
			</div>
		 </div>	
		 
		   
	   </div>
     </div>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script src="<%=mobileBaseJsPath%>"></script>
<script src="<%=basePath%>js/mobile/md5.js"></script>
<script>

    $(function(){
	   var height=$(document).height();
	   $('body').css('height',height)
	})

	function login(){
		var phone = $("#phone").val();
		var password = $("#password").val();
		password = CryptoJS.MD5(CryptoJS.MD5(password).toString().toUpperCase()).toString().toUpperCase();
		$.ajax({
            type : "post",
            url : baseUrl + "staff/action/login",
            data : "phone=" + phone + "&password=" + password,
            dataType : "json",
            success : function(e){
                if (e.code != 0) {
                	dialog(e.msg);
                    return;
                }
                window.location.href = baseUrl + "staff/view/home/${session_key_store_account}/2";
            }
        });
	}
</script>
</body>
</html>