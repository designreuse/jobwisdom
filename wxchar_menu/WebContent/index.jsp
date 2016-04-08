<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%String echostr = request.getParameter("echostr"); 
if(echostr==null||echostr.equals("")){
	echostr = "wxchar";
}
response.getWriter().write(echostr);
%><br>
 <form action="/wxchar_menu/upload" method="post"
		enctype="multipart/form-data">
		<label> <input type="text" name="name" value="name" />
		</label> <label> <input type="file" name="userfile" />
		</label> <label> <input type="submit" value="up" />
		</label>
	</form>
</body>
</html>