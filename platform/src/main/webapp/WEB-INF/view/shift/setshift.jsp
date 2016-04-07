<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=basePath %>css/style.css" />
<link rel="stylesheet" href="<%=basePath %>app/css/bootstrap.css"/>
<link rel="stylesheet" href="<%=basePath %>app/css/bootstrap-datetimepicker.css"/>
<title>轮班设置</title>
<style type="text/css">
table{ border-collapse:collapse;text-align: center; }
td{ border:1px solid #000;}
</style>
</head>

<body onload="selectPost()">
	<table id = "postID">
        <tr>
            <td>轮牌岗位</td>
            <td><div class="addID"><input type="button" onclick="copyPost();" value="添加"></div></td>
            <td>
                <div class="f-group">
                        <div class="f-whole form-group  col-md-6">
                            <label class="f-label control-label col-md-4" for="birthday" style="padding-top: 6px;padding-left: 0px;">生日</label>
			             <div class="input-group date form_datetime col-md-5" id="birthday" data-date="1979-09-16" data-date-format="yyyy-MM-dd" data-link-field="birthday">
			                  <input class="f-whole-input"  size="16" type="text" value="2105-07-14" style="width: 200px;background-color: white;margin-left: 4px;" readonly>
						      <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
			             </div>
                     </div>
               </div>
            </td>
        </tr>
	</table>
	<br>
	<br>
	<br>
	<br>
	<br>
	<table id = "registerID" style="display:none">
	   <tr>
            <td>动态轮牌设置</td>
            <td><input type="button" onclick="copyRegister();" value="添加"><input type="hidden" name="registerPostId"></td>
        </tr>
	</table>
	
</body>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.11.3.min.js"></script>
<script src="<%=basePath %>app/js/bootstrap.js"></script>
<script src="<%=basePath %>app/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
<script src="<%=basePath %>app/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
	<script type="text/javascript">
	      var datas ='';
	      function selectPost(){
	    	  removePost();
	    	  $.ajax({
        			url : "selectAllPost",
        			type: "post", 
        			dataType : "json",
        			success : function(data){
        				if(data != ''){
        					$.each(data,function(i,item){
        						  if(datas == ''){
        							  datas = selectAllPostMessage();
        						  }
        						  var tr = $("<tr name='idTR'></tr>");
        			        	  tr.append("<td>岗位  :</td>"+
        			        			    "<td name='idTD'><input type='hidden' name='postId' value='"+item.postId+"'></td>");
        			        	  var td =$("<td name='nameTD'></td>");
        						  var select = $("<select name='postMessageId'></select>");
        						  select.append("<option value=''>请选择</option>");
        						  if(datas != ''){
        							  $.each(datas ,function(i,obj){
        									var postMessageId = obj.postMessageId;
        									var postMessageName = obj.postMessageName;
        									if(item.postMessageId == postMessageId){
        										select.append("<option value='"+postMessageId+"' selected = 'selected'>"+postMessageName+"</option>");
        									}else{
        										select.append("<option value='"+postMessageId+"'>"+postMessageName+"</option>");
        									}
        							  });
        						  }
        						  td.append(select);
        						  tr.append(td);
        								    "<td name='nameTD'><input type='text' name='postName' value='"+item.postName+"'></td>"+
        						  tr.append("<td><input type='button' onclick='setPost(this)' value='设置轮牌'>"+
        								    "<input type='button' onclick='savePost(this)'name='saveBtn' value='保存'>"+
        								    "<input type='button' onclick='deletePost(this)' value='删除'>");
        			        	  if(item.valid == 1){
        			        		  tr.append("<div class='switch demo3'>"+
          								        "<input type='checkbox' onclick='validPost(this)' checked>"+
  								                "<label><i></i></label></div></td>");
        			        	  }else{
        			        		  tr.append("<div class='switch demo3'>"+
        								        "<input type='checkbox' onclick='validPost(this)'>"+
								                "<label><i></i></label></div></td>");
        			        	  }
        			        	  $("#postID").append(tr);
        					});
        				}
        			},
        		  });
	      }
	      
          function copyPost(){
        	  if(datas == ''){
				  datas = selectAllPostMessage();
			  }
        	  var tr = $("<tr name='idTR'></tr>");
        	  tr.append("<td>岗位  :</td>"+
        			    "<td name='idTD'><input type='hidden' name='postId'></td>");
			  var td =$("<td name='nameTD'></td>");
			  var select = $("<select name='postMessageId'></select>");
			  select.append("<option value=''>请选择</option>");
			  if(datas != ''){
				  $.each(datas ,function(i,item){
						var postMessageId = item.postMessageId;
						var postMessageName = item.postMessageName;
						select.append("<option value='"+postMessageId+"'>"+postMessageName+"</option>");
				  });
			  }
			  td.append(select);
			  tr.append(td);
			  tr.append("<td>"+
					    "<input type='button' onclick='savePost(this)'name='saveBtn' value='保存'>"+
					    "<input type='button' onclick='deletePost(this)' value='删除'>"+
					    "<div class='switch demo3'>"+
					    "<input type='checkbox' onclick='validPost(this)' checked>"+
					    "<label><i></i></label></div></td>");
        	  $("#postID").append(tr);
        	  i++;
          }			
          
          function setPost(obj){
        	  var postId = $(obj).parent().parent().find("td[name='idTD']").find("input[name='postId']").val();
        	  $("input[name='registerPostId']").val(postId);
        	  $("#registerID").attr("style","display:''");
          }
          
          function savePost(obj){
        	  var postId = $(obj).parent().parent().find("td[name='idTD']").find("input[name='postId']").val();
        	  var postMessageId =$(obj).parent().parent().find("td[name='nameTD']").find("select[name='postMessageId']").val();
        	  if(postMessageId != ''){
        		  var valid = 0;
            	  if($(obj).parent().parent().find("input[type='checkbox']").is(':checked')) {
            		  valid = 1;
            	  }
                  if(postId == ''){
                	  $.ajax({
              			url : "savePost",
              			data : "postMessageId="+postMessageId+"&valid="+valid,
              			type: "post", 
              			dataType : "json",
              			success : function(data){
              				if(parseInt(data) != 0){
              					alert("保存成功！");
              					selectPost();
              				}else{
              					alert("保存失败！");
              					selectPost();
              				}
              			},
              		  });
                  }else{
                	  $.ajax({
                			url : "updatePost",
                			data : "postId="+postId+"&postMessageId="+postMessageId+"&valid="+valid,
                			type: "post",
                			dataType : "json",
                			success : function(data){
                				if(parseInt(data) != 0){
                					alert("修改成功！");
                					selectPost();
                				}else{
                					alert("修改失败！");
                					selectPost();
                				}
                			},
                		  });
                  } 
        	  }else{
        		  alert("请填写岗位！");
        	  }
          }
          
          function deletePost(obj){
        	  var postId = $(obj).parent().parent().find("td[name='idTD']").find("input[name='postId']").val();
        	  $.ajax({
      			url : "deletePost",
      			data : "postId="+postId,
      			type: "post",
      			dataType : "json",
      			success : function(data){
      				if(parseInt(data) != 0){
      					alert("删除成功！");
      					selectPost();
      				}else{
      					alert("删除失败！");
      					selectPost();
      				}
      			},
      		  });
          }
          
          function validPost(obj){
        	  var postId = $(obj).parent().parent().find("td[name='idTD']").find("input[name='postId']").val();
        	  var postMessageId = $(obj).parent().parent().find("td[name='nameTD']").find("select[name='postMessageId']").val();
        	  var valid = 0;
        	  if($(obj).parent().parent().find("input[type='checkbox']").is(':checked')) {
        		  valid = 1;
        	  }
        	  $.ajax({
      			url : "updatePost",
      			data : "postId="+postId+"&postMessageId="+postMessageId+"&valid="+valid,
      			type: "post",
      			dataType : "json",
      			success : function(data){
      				if(parseInt(data) != 0){
      					alert("修改成功！");
      					selectPost();
      				}else{
      					alert("修改失败！");
      					selectPost();
      				}
      			},
      		  });
          }
          
          function removePost(){
        	  $("tr[name='idTR']").remove();
          }
          
          function selectAllPostMessage(){
        	  $.ajax({
      			url : "selectAllPostMessage",
      			type: "post",
      			async : false,
      			dataType : "json",
      			success : function(data1){
      				if(data1 != ''){
      					datas = data1;
      				}
      			},
      	      });
        	  return datas;
          }
          
          function copyRegister(){
        	  
          }
</script>
</html>