<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.lang.Exception" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path + "/";
%>
<!-- <script type="text/javascript">
	var status = ${status};
	alert(status);
	switch (status) {
	case 500:
		alert("服务器发生错误");
		window.history.go(-1);
		break;
	case 404:
		alert("请求地址没找到");
		window.history.go(-1);
		break;
	}
</script> -->
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/main.css">
<body>
<%Exception ex = (Exception)request.getAttribute("ex");%>

<div id="wrapper"><a class="logo" href="/"></a>
  <div id="main">
    <header id="header">
      <h1><span class="icon">!</span>警告<span class="sub"><%=ex.getMessage()%></span></h1>
    </header>
    <div id="content">
      <h2>请注意您的操作</h2>
      <p>当您看到这个页面,表示您的访问出错,这个错误是您打开的页面不存在,请确认您输入的地址是正确的,如果是在本站点击后出现这个页面,请联系站长进行处理,或者请通过下边的搜索重新查找资源,JS代码站站长感谢您的支持!</p>
      <div class="utilities">
        <form  name="formsearch" action="/plus/search.php" id="formkeyword">
          <div class="input-container">
            <input type="text" class="left" name="q" size="24"  value="在这里搜索..." onfocus="if(this.value=='在这里搜索...'){this.value='';}"  onblur="if(this.value==''){this.value='在这里搜索...';}" id="inputString" onkeyup="lookup(this.value);" onblur="fill();" placeholder="搜索..." />
            <button id="search"></button>
          </div>
        </form>
        <a class="button right" href="#" onClick="history.go(-1);return true;">返回...</a><a class="button right" href="/platform/login.html">返回首页</a>
        <div class="clear"></div>
      </div>
    </div>
    <div id="footer">
    </div>
  </div>
</div>
<div></div>
</body>