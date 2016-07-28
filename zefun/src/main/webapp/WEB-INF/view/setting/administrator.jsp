<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/manage.css" type="text/css" />
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
			 <%@include file="/top.jsp"%>
				
			<div class="content_right clearfix">
			    <div class="manage_top">
				   <input type="text" name="codeName" placeholder="姓名/工号">
				   <select name="roleInfo">
				   	<c:forEach items="${accountRoleInfo }" var="accountRoleInfo">
					<option roleId="${accountRoleInfo.accountRoleId }">${accountRoleInfo.accountRoleName }</option>
					</c:forEach>
				   </select>
				   <button onclick="selectUser()">搜索</button>
				   <button style="width:130px" onclick="showViwe()">新建</button>
				</div>
			
				<div class="manage_content">
				  <table>
				     <tbody class="trs">
				     <tr class="tr">
					  <td>工号</td>
					  <td>姓名</td>
					  <td>系统角色</td>
					  <td>所属门店</td>
					  <td>操作</td>
					 </tr>
					 <c:forEach items="${userAccount }" var="userAccount">
						 <tr id="${userAccount.userId }">
						  <td>${userAccount.userName }</td>
						  <td>${userAccount.employeeName }</td>
						  <td>${userAccount.roleName }</td>
						  <td>${userAccount.storeName }</td>
						<c:choose>
						   <c:when test="${userAccount.userName eq '10000'}">  
						   <td></td>    
						   </c:when>
						   <c:otherwise> 
							<td><img onclick="updateUser(this)" src="<%=basePath%>images/add_store_1.png"><img onclick="deleteUser(this)" src="<%=basePath%>images/add_store_2.png"></td>
						   </c:otherwise>
						</c:choose>
						 </tr>
					 </c:forEach>
				  </tbody></table>
				</div>
			 </div>
			</div>
		</div>
	</div>
	<div class="zzc" style="display: none;">
   <div class="zzc_manage">
      <p>新建/修改管理员</p>
      <div class="zzc_manage_content">
	    <p><span>工号：</span><input type="Number" name = "userName"><i>*</i></p>
	    <p><span>姓名：</span><input type="text" name = "employee"><i>*</i></p>
<!-- 		<p><span>密码：</span><input type="text" ><i>*</i></p> -->
		<p><span>系统角色：</span>
			<select name = "role"  onchange="tyep(this)"  >
				<c:forEach items="${accountRoleInfo }" var="accountRoleInfo">
				<option roleId="${accountRoleInfo.accountRoleId }"  systemRole="${accountRoleInfo.roleId }">${accountRoleInfo.accountRoleName }</option>
				</c:forEach>
			</select>
			<i>*</i>
		</p>
		<p><span>所属门店：</span>
			<select name = "store">
				
				<c:forEach items="${storeInfo }" var="storeInfo">
					<option storeId="${storeInfo.storeId }">${storeInfo.storeName }</option>
				</c:forEach>	
				<option  storeId = "0" >企业</option>
			</select>
		</p>
		<div class="zzc_manage_content_button">
		  <button onclick="saveAdmin()">确认</button>
		  <button onclick="clearZcc()">取消</button>
		</div>
	  </div>
   </div>
</div>
</body>
<script>

function tyep(s){
	var systemRole =jQuery(s).find("option:selected").attr("systemRole");
	if(systemRole == 1){
		jQuery("select[name='store']").val("企业");
		jQuery("select[name='store']").attr('disabled','disabled');
// 		jQuery("select[name='store']").val("")
	}
	
	else{
		if(jQuery("select[name='store'] option").length ==1){
			  dialog("没有分店");
			  jQuery("select[name='store']").val("企业");
			  return;
		}
		jQuery("select[name='store']").attr('disabled',false);
		jQuery('select option[storeid="0"]').attr('disabled','disabled');
		jQuery("select[name='store']").find("option").eq(0).attr("selected",true);
	}
}

function showViwe(){
	jQuery(".zzc").show();
	}
	
function clearZcc(){
	jQuery("input[name='userName']").val("");
	jQuery("input[name='employee']").val("");
	updateUserId = null
	jQuery(".zzc").hide();
}	
function saveAdmin(){
	
	var userPwd  = "123456";
	userPwd = CryptoJS.MD5(CryptoJS.MD5(userPwd).toString().toUpperCase()).toString().toUpperCase();
	var userName = jQuery("input[name='userName']").val();
	var employeeName = jQuery("input[name='employee']").val();
	var storeId = jQuery("select[name='store']").find("option:selected").attr("storeId");
	var roleId =  jQuery("select[name='role']").find("option:selected").attr("roleId");
	var roleName = jQuery("select[name='role']").find("option:selected").text();
	var storeName = jQuery("select[name='store']").find("option:selected").text();
	var data = "userPwd="+userPwd+"&userName="+userName+"&employeeName="+employeeName+"&roleId="+roleId+"&userId="+updateUserId+"&storeId="+storeId;
	if(updateUserId==null){
		data = "userPwd="+userPwd+"&userName="+userName+"&employeeName="+employeeName+"&roleId="+roleId+"&storeId="+storeId;
	}
	jQuery.ajax({
        type: "POST",
        url: baseUrl + "administrator/action/saveUpdate",
        data: data,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	var userAccount = data.msg;
        	var html = '<tr id="'+userAccount.userId+'"><td>'+userAccount.userName+'</td> <td>'+employeeName+'</td><td>'+roleName+'</td><td>'+storeName+'</td>';
        	html +='<td><img onclick="updateUser(this)" src="'+baseUrl+'images/add_store_1.png"><img   onclick="deleteUser(this)" src="'+baseUrl+'images/add_store_2.png"></td></tr>'
        	if(updateUserId != null){
        		jQuery("#"+updateUserId).remove();
        	}
        	jQuery(".tr").after(html);
        	clearZcc();
        	dialog("新增成功");
        }
    });
}
function selectUser (){
	var codeName = jQuery("input[name='codeName']").val();
	var role = jQuery("select[name='roleInfo']").find("option:selected").attr("roleId");
	var data = "codeName="+codeName+"&role="+role;
	jQuery.ajax({
        type: "POST",
        url: baseUrl + "administrator/action/selectUser",
        data: data,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                jQuery(".trs tr:gt(0)").empty();
                return;
            }
        	var userAccounts = data.msg;
        
        	var html = '';
        	for (var i = 0; i < userAccounts.length; i++) {
        		 var  userAccount = userAccounts[i];
        	     html += '<tr id="'+userAccount.userId+'"> <td>'+userAccount.userName +'</td><td>'+userAccount.employeeName+'</td>';
        	     html += '<td>'+userAccount.roleName+'</td><td>'+userAccount.storeName+'</td><td><img onclick="updateUser(this)" src="'+baseUrl+'images/add_store_1.png"><img onclick="deleteUser(this)" src="'+baseUrl+'images/add_store_2.png"></td></tr>'
			}
        	jQuery(".trs tr:gt(0)").empty();
        	jQuery(".trs").append(html);
        	dialog("查询完成");
        }
    });
}

function deleteUser(d){
	var userId = jQuery(d).parents("tr").attr("id");
	var data = "userId="+userId;
	 if (confirm("你确定提交吗？")) {  
		 jQuery.ajax({
		        type: "POST",
		        url: baseUrl + "administrator/action/deleteUser",
		        data: data,
		        async: false,
		        success: function(data) {
		        	if (data.code != 0) {
		                dialog(data.msg);
		                return;
		            }
		        	jQuery("#"+userId).remove();
		        	dialog("删除成功");
		        }
		    });
	 }
}
var updateUserId = null ;
function updateUser(u){
	updateUserId = jQuery(u).parents("tr").attr("id");
	var userName = jQuery(u).parents("tr").find("td").eq(0).text();
	var name = jQuery(u).parents("tr").find("td").eq(1).text();
	var role = jQuery(u).parents("tr").find("td").eq(2).text();
	var store = jQuery(u).parents("tr").find("td").eq(3).text();
	if(store == "") {
		store ="企业";
	}
	jQuery("input[name='userName']").val(userName);
	jQuery("input[name='employee']").val(name);
	jQuery("select[name='store']").val(store);
	jQuery("select[name='role']").val(role);
	showViwe();
}


//dialog('msg');
</script>
</html>