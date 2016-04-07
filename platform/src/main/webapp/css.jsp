<%
String cssPath = request.getContextPath();
String cssBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+cssPath + "/";
%>
<link rel="stylesheet" href="<%=cssBasePath %>app/css/bootstrap.css"/>
<link rel="stylesheet" href="<%=cssBasePath %>app/css/bootstrap-tagsinput.css"/>
<link rel="stylesheet" href="<%=cssBasePath %>app/css/iconfont.css"/>
<link rel="stylesheet" href="<%=cssBasePath %>app/css/SimpleTree.css"/>
<link rel="stylesheet" href="<%=cssBasePath %>app/css/waves.min.css"/>
<link rel="stylesheet" href="<%=cssBasePath %>app/css/lives.css"/>
<link rel="stylesheet" href="<%=cssBasePath %>app/css/bootstrap-datetimepicker.css"/>
<link rel="stylesheet" href="<%=cssBasePath %>app/css/bootstrap-table.css"/>
<link rel="stylesheet" href="<%=cssBasePath %>css/superdropdown.css"/>
<script type="text/javascript" src="<%=cssBasePath %>app/js/jquery.min.js"></script>
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
  <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->