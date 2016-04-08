
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<form action="hello-world-default/HelloWordAction.action" method="post">
		productName:<input type="text" name="productName"><br>
		productDesc:<input type="text" name="productDesc"><br>
		productPrice:<input type="text" name="productPrice"><br>
		<input type="submit" value="提交">
	</form>

	<br>
	<form action="hello-world-default/HelloWordAction_savePs.action"
		method="post">
		person.id:<input type="text" name="persons[0].id"><br>
		person.name:<input type="text" name="persons[0].name"><br>
		<br> person.id:<input type="text" name="persons[1].id"><br>
		person.name:<input type="text" name="persons[1].name"><br>
		<input type="submit" value="提交">
	</form>

	<form action="hello-world-default/HelloWordAction_saveUs.action"
		method="post">
		person.id:<input type="text" name="persons[0].id"><br>
		person.name:<input type="text" name="persons[0].name"><br>
		<br> person.id:<input type="text" name="persons[1].id"><br>
		person.name:<input type="text" name="persons[1].name"><br>
		<input type="submit" value="提交">
	</form>
</body>
</html>