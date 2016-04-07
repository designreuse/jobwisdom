<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path + "/";
String name = request.getParameter("param");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>liweishi</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=basePath %>assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=basePath %>assets/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="<%=basePath %>assets/css/matrix-login.css" />
        <link href="<%=basePath %>assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link rel="stylesheet" href="<%=basePath %>assets/app/css/index.css"/>
		<link href='http://fonts.useso.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

</head>
<body>
 <div id="loginbox">
            <form action="<%=basePath %>login" method="POST" id="loginfrom">
				 <div class="control-group normal_text"> <img src="assets/app/img/t4.png" alt="商标" class="login-img"/><h1 class="title">智能电务系统管理后台</h1></div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" name = "userName" id="userName" placeholder="请输入账号" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" name = "passWord" id="passWord"  placeholder="请输入密码" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <!-- <span class="pull-left"><a href="#" class="btn btn-info" id="to-recover">忘记密码?</a></span> -->
                    <span class="pull-right"><input type="button" value="登录" class="btn btn-success" onclick="tijiao()"></span>
                </div>
            </form>
            <!-- <form id="recoverform" action="#" class="form-vertical">
				<p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a password.</p>

                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                        </div>
                    </div>

                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; Back to login</a></span>
                    <span class="pull-right"><a class="btn btn-info"/>Reecover</a></span>
                </div>
            </form> -->
        </div>

        <script src="<%=basePath %>assets/js/jquery.min.js"></script>
        <script src="<%=basePath %>assets/js/matrix.login.js"></script>
        <script type="text/javascript">
        var name=<%=name%>
        if(name==1){
        	alert("您输入的帐号或者密码错误！");
        }
        function tijiao(){
        	var name=$("#userName").val();
        	var password=$("#passWord").val();
        	if(name==""||name==null){
        		alert("用户名不能为空！");
        		return;
        	}
        	if(password==""||password==null){
        		alert("密码不能为空！");
        		return;
        	}
        	document.getElementById("loginfrom").submit();
        }
        </script>
<%-- <form action="<%=basePath %>login" method="POST" >
用户名：<input name = "userName" type="text"><br>
密码：<input name = "passWord" type="text"><br>
<input type="submit" value="登陆">
</form> --%>
</body>
</html>