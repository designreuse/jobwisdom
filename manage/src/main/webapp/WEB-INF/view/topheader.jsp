<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path1 = request.getContextPath();
String basePath1 = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1 + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
      <link rel="image_src" type="image/jpeg" href="<%=basePath1 %>assets/app/img/t4.png">
      <link rel="stylesheet" href="<%=basePath1 %>assets/css/bootstrap-multiselect.css" type="text/css"/>
      <link rel="stylesheet" href="<%=basePath1 %>assets/css/non-responsive.css" />
      <link rel="stylesheet" href="<%=basePath1 %>assets/css/bootstrap.min.css" />
      <!--<link rel="stylesheet" href="<%=basePath1 %>assets/css/bootstrap-responsive.min.css" />-->
      <link rel="stylesheet" href="<%=basePath1 %>assets/css/fullcalendar.css" />
      <link href="<%=basePath1 %>assets/font-awesome/css/font-awesome.css" rel="stylesheet" />

      <link rel="stylesheet" href="<%=basePath1 %>assets/font-awesome/css/iconfont.css"/>
      <link rel="stylesheet" href="<%=basePath1 %>assets/font-awesome/css/iconfont-index.css"/>
      <!-- Bootstrap-table-->
      <link href="<%=basePath1 %>assets/bootstrap-table/bootstrap-table.css" rel="stylesheet" type="text/css" />
      <link href="<%=basePath1 %>assets/editor/summernote.css" rel="stylesheet" type="text/css" />
      <link href="<%=basePath1 %>assets/editor/font-awesome.min.css" rel="stylesheet" type="text/css" />

      <link href='http://fonts.useso.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>
      <link rel="stylesheet" href="<%=basePath1 %>assets/css/matrix-style.css" />
      <link rel="stylesheet" href="<%=basePath1 %>assets/css/matrix-media.css" />
      <link rel="stylesheet" href="<%=basePath1 %>assets/css/uniform.css" />
      <link rel="shortcut icon" href="<%=basePath1 %>assets/app/img/favicon.ico" />

      <link rel="stylesheet" href="<%=basePath1 %>assets/datetimepicker/bootstrap-datetimepicker.min.css" />

      <link rel="stylesheet" href="<%=basePath1 %>assets/app/css/index.css" />
<div id="header">
    <a href="index.html">
        <img src="<%=basePath1 %>assets/app/img/t4.png" class="header-img"/>
        <!--<h6 class="header-h1">智能电务系统管理后台</h6>-->
    </a>
</div>
<!--close-Header-part-->

<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse">
    <ul class="nav">
        <li  class="dropdown" id="profile-messages" ><a title="" href="#" data-toggle="dropdown" data-target="#profile-messages" class="dropdown-toggle"><i class="icon icon-user"></i>  <span class="text">账号管理</span><b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="<%=basePath1 %>user/view/userdetail"><i class="icon-user"></i> 帐号资料</a></li>
                <%-- <li class="divider"></li>
                <li><a href="<%=basePath1 %>user/view/add"><i class="icon-check"></i> 新增帐号</a></li>
                <li class="divider"></li>
                <li><a href="<%=basePath1 %>user/view/queryinfo"><i class="icon-check"></i> 账号查询</a></li>
                <li class="divider"></li> --%>
                <!-- <li><a href="login.html"><i class="icon-key"></i> 退出</a></li> -->
            </ul>
        </li>
        <li class="dropdown" id="menu-messages"><a href="#" data-toggle="dropdown" data-target="#menu-messages" class="dropdown-toggle"><i class="iconfont icon-gonggao"></i> <span class="text">系统公告</span> <!-- <span class="label label-important">5</span> --> <b class="caret"></b></a>
            <%-- <ul class="dropdown-menu">
                <li><a class="sInbox" title="" href="<%=basePath1 %>SystemAffiche/view/selectTable"><i class="icon-envelope"></i> 公告列表</a></li>
            </ul> --%>
        </li>
        <li class=""><a title="" href="<%=basePath1 %>loginOut"><i class="iconfont icon-tuichuxitong"></i> <span class="text">退出系统</span></a></li>
    </ul>
</div>
