<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    
    <%
    
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>
	<form action="">
		菜单名称:<input id="menuName" type="text" value="" />
		排序:<input id="ordreNo" type="text" value="" />
                        菜单父节点<input id="parentId" type="text" value="" />
                      菜单地址<input id="linkUrl" type="text" value="" />
		
	<input type="button" onclick="add()" value="新增提交">
	<input type="button" onclick="update()" value="修改提交">
	
	<input type="button" onclick="query()" value="查询">
	</form>
	<script type="text/javascript">
	
	function add(){

	
		var menuName=$("#menuName").val();
		if(menuName==null||menuName==""){
			alert("角色不能为空！");
			return ;  
		}
		var parentId=$("#parentId").val();
		if(parentId==null||parentId==""){
			alert("父节点不能为空！");
			return ;  
		}
		var linkUrl=$("#linkUrl").val();
		if(linkUrl==null||linkUrl==""){
			alert("地址不能为空！");
			return ;  
		}
		var ordreNo=$("#ordreNo").val();
		if(ordreNo==null||ordreNo==""){
			alert("排序不能为空！");
			return ;  
		}
	     
	}
	
	
	function query(){
		
		 $.ajax({
	            type: "POST",
	            dataType: "json",
	            url: '<%=basePath%>menu/queryMenu',
	            data:'',
	            success: function (result) {
	                //加载最大可退金额
	                alert(result.code);
	                alert(result.msg);
	                var last=JSON.stringify(result.msg); 
	                alert(last);
	                
	            }
	        });
		
	}
	
	function update(){
		var menuName=$("#menuName").val();
		if(menuName==null||menuName==""){
			alert("角色不能为空！");
			return ;  
		}
		var parentId=$("#parentId").val();
		if(parentId==null||parentId==""){
			alert("父节点不能为空！");
			return ;  
		}
		var linkUrl=$("#linkUrl").val();
		if(linkUrl==null||linkUrl==""){
			alert("地址不能为空！");
			return ;  
		}
		var ordreNo=$("#ordreNo").val();
		if(ordreNo==null||ordreNo==""){
			alert("排序不能为空！");
			return ;  
		}
	    $.ajax({
            type: "POST",
            dataType: "json",
            url: '<%=basePath%>menu/updateMenu',
            data:'menuId=1&menuName='+menuName+'&parentId='+parentId+'&linkUrl='+linkUrl+'&ordreNo='+ordreNo,
            success: function (result) {
                //加载最大可退金额
                alert(111111111);
                alert(result.code);
                alert(result.msg);
            }
        });
	}
	</script>
</body>
</html>