<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/new_role.css" type="text/css" />
  <script>

  //选项卡
  jQuery(function(){
   jQuery('.first .appoint_ul_index ul:gt(0)').hide();
   jQuery(document).on('click','.first .appoint_ul li',function(){
     jQuery(this).addClass('active').siblings().removeClass('active');
	 jQuery('.first .appoint_ul_index ul').eq(jQuery(this).index()).show().siblings().hide();
	 jQuery('.second .appoint_ul li').eq(jQuery(this).index()).addClass('active').siblings().removeClass('active');
	 jQuery('.second .appoint_ul_index ul').eq(jQuery(this).index()).show().siblings().hide();
   
   });
   jQuery(document).on('click','.first .appoint_ul_index li',function(){
     var index=jQuery(this).parent().index();
	 var ind=jQuery(this).index();
     var img='<img src="<%=basePath%>images/setting_close.png">';
	 jQuery(this).attr({'name':'parent'+index+'child'+ind,'number':ind});
	 if(jQuery(this).css('background-color')=='rgb(255, 255, 255)'){
	    var background=jQuery(this).css('background-color');
	    html=jQuery(this).clone(); 
	   if(background=='rgb(184, 199, 234)')
	     return;
	   else{
		 jQuery('.second .appoint_ul_index ul').eq(index).append(html);
		 jQuery('.second .appoint_ul_index ul').eq(index).find('li img').remove();
		 jQuery('.second .appoint_ul_index ul').eq(index).find('li').append(img);
     	  }
		jQuery(this).addClass('active');	 
		jQuery('.second .appoint_ul li').eq(index).addClass('active').siblings().removeClass('active');
		jQuery('.second .appoint_ul_index ul').eq(index).show().siblings().hide();
	   }	
    else{
       jQuery('.second .appoint_ul_index').find("li[name=parent"+index+"child"+ind+"]").remove(); 
       jQuery(this).removeClass('active');	   
     }
   })
 
  })
 
 //second 
 jQuery(function(){
   jQuery('.second .appoint_ul_index ul:gt(0)').hide();
   jQuery(document).on('click','.second .appoint_ul li',function(){
     jQuery(this).addClass('active').siblings().removeClass('active');
	 jQuery('.second .appoint_ul_index ul').eq(jQuery(this).index()).show().siblings().hide();
	 jQuery('.first .appoint_ul li').eq(jQuery(this).index()).addClass('active').siblings().removeClass('active');
     jQuery('.first .appoint_ul_index ul').eq(jQuery(this).index()).show().siblings().hide();
   });
  })
 //点击删除
jQuery(function(){ 
 jQuery(document).on('click','.second .appoint_ul_index li img',function(){
    var name=jQuery(this).parent('li').attr('name');
    jQuery('.first .appoint_ul_index').find("li[name="+name+"]").removeClass('active');
	jQuery(this).parent().remove();
		
 })
}) 
  </script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				
<div class="content_right clearfix">
    <button onclick="roleChange(1,0)">新建</button>
	<div class="new_role_content">
	   <ul class="clearfix">
	       <c:forEach items="${selectRoles }" var="role">
			 <li id="${role.accountRoleId }">
			    <div class="circle">
				 <span onclick="deleteRole(${role.accountRoleId })"><img src="<%=basePath%>images/setting_close.png"></span>
				 <p>${role.accountRoleName }</p>			  
				</div>
				<div class="adjust">
			  	<span onclick="roleChange(${role.roleId },${role.accountRoleId })">修改</span>
				</div>
			 </li>
		</c:forEach>
	   </ul>
	</div>
  </div>
			</div>
		</div>
	</div>
	
<div class="zzc hide" >
  <div class="zzc_new_role">
     <p>新建角色</p>
     <div class="zzc_new_role_content">
	   <p>
	     <span>角色名称<em>*</em><input type="text" name="accountRoleName" accountRoleId="0" changeRoleId="1"></span>
	     <span>菜单<em>*</em><select onchange="systemRoleChange(this)" name="roleId">
	     <c:forEach items="${selectSystemRoles }" var="selectSystemRoles">
	     <option val="${selectSystemRoles.roleId }" id="${selectSystemRoles.roleId }">${selectSystemRoles.roleName }</option>
	     </c:forEach>
	     </select></span>
	   </p>
	   <div class="appoint"  name="systemMemu">
	     <p>*选择菜单</p>
	     <div class="appoint_content first">
		    <ul class="appoint_ul clearfix"  name="firestMemu">
			</ul>
		    <div class="appoint_ul_index" name="secendMemu">
			</div>
		 </div>
	   </div>
	   
	     <div class="appoint">
	     <p>*已选择菜单</p>
	     <div class="appoint_content second">
		    <ul class="appoint_ul clearfix" name="roleMemu">
			</ul>
		    <div class="appoint_ul_index">
			</div>
		 </div>
	   </div>
	   <div class="new_role_button">
	     <button  onclick="saveRole()">确定</button>
		 <button  onclick="hideRole()">取消</button>
	   </div>
    </div>
  </div>
</div>
</body>
<script>


jQuery(function(){
	var htmlLi = '';
	for (var z = 20; z > jQuery(".content_right .clearfix li").length; z--) {
		htmlLi +='<li></li>';
	}
	jQuery(".content_right ul").append(htmlLi)
})

function roleChange(roleId, accountRoleId) {
	jQuery(".zzc .second .appoint_ul_index").empty();
	jQuery(".zzc").find("div[name='secendMemu']").empty();
	jQuery(".zzc").find("ul[name='firestMemu']").empty();
	jQuery(".zzc").find("ul[name='roleMemu']").empty();
	jQuery(".zzc").find("input[name=' ']").val("");
	jQuery("select[name='roleId']").val(roleId);
	
// 	jQuery("select[name='roleId']").empty();
	var datas = "roleId=" + roleId + "&accountRoleId=" + accountRoleId ;
	jQuery.ajax({
		type : "POST",
		url : baseUrl + "system/view/selectRole",
		data : datas,
		dataType : "json",
		success : function(data) {
			if(accountRoleId != 0){
				jQuery(".zzc").find("input").attr("changeRoleId",0);
				jQuery("select").val(jQuery("option[id='"+roleId+"']").text());
				jQuery(".zzc").find("input").attr("changeRoleId",1);
			}
			var jsonarray = data.msg.jsonarr;
			var htmlUl  = '';
			var html ='';
			var htmlTwo = '';
			for (var i = 0; i < jsonarray.length; i++) {
				var jsono = jsonarray[i];
				
				if( i==0 ){
					html += ' <li class="active" id ="'+jsono.memuId+'">'+jsono.fristMemuName+'</li> '
					htmlTwo +=' <ul class="clearfix">';
					htmlUl += '<ul class="clearfix"> </ul>'; 
				}
				else{
					html += ' <li  id ="'+jsono.memuId+'">'+jsono.fristMemuName+'</li> '
					htmlTwo +=' <ul class="clearfix" style="display: none;">';
					htmlUl += '<ul class="clearfix" style="display: none;"> </ul>'; 
				}
				//二级菜单
				for (var j = 0; j < jsono.secendMemu.length; j++) {
					 var secendMemu =jsono.secendMemu[j];
					 htmlTwo +=' <li id="'+secendMemu.memuId+'">'+secendMemu.secendMemuName+'</li>'
				}
			
				htmlTwo += '</ul>';
				
			}
			jQuery(".zzc .second .appoint_ul_index").append(htmlUl);
			jQuery(".zzc").find("div[name='secendMemu']").append(htmlTwo);
			jQuery(".zzc").find("ul[name='firestMemu']").append(html);
			jQuery(".zzc").find("ul[name='roleMemu']").append(html);
			
			if(accountRoleId != 0 ){
				//修改的时候
				
				var accountRoleInfo = data.msg.accountRoleInfo;
				var secondMenu = accountRoleInfo.secondMenu.split(",");
				for (var g = 0; g < secondMenu.length; g++) {
					jQuery(".zzc").find("div[name='secendMemu']").find("li[id="+secondMenu[g]+"]").click();
				} 
				jQuery(".zzc").find("input[name='accountRoleName']").val(accountRoleInfo.accountRoleName);
				jQuery(".zzc").find("input[name='accountRoleName']").attr("accountRoleId",accountRoleId);
				jQuery("select[name='roleId']").attr("disabled","disabled");
			}
			jQuery(".zzc").removeClass("hide");
		}
	});
}

//更改引用
function systemRoleChange(s){
	if(jQuery(".zzc").find("input").attr("changeRoleId") !=0){
		roleChange(jQuery(s).find("option:checked").attr("id"),0);
	}
}
//保存
function saveRole (){
	var fristMemu = [];
	var secendMemu = [];
	var accountRoleName = jQuery(".zzc").find("input[name='accountRoleName']").val();
	var roleId = jQuery("select[name='roleId']").find("option:checked").attr("id")
	var accountRoleId = jQuery(".zzc").find("input[name='accountRoleName']").attr("accountRoleId");
	for (var i = 0; i < jQuery(".zzc").find(".second>ul li").length; i++) {
		if(jQuery(".zzc").find(".second .appoint_ul_index ul").eq(i).find("li").length != 0 ){  //如果二级菜单没有权限 ，一级菜单也不给权限
			fristMemu.push(jQuery(".zzc").find(".second>ul li").eq(i).attr("id"));
		}
	}
	for (var j = 0; j < jQuery(".zzc").find(".second .appoint_ul_index ul li").length; j++) {
		secendMemu.push(jQuery(".zzc").find(".second .appoint_ul_index ul li").eq(j).attr("id"));
	}
	//修改的参数
		datas = "fristMemu=" + fristMemu + "&secendMemu=" + secendMemu + "&roleId=" + roleId + "&accountRoleName=" + accountRoleName + "&accountRoleId=" + accountRoleId;
	if(accountRoleName == null || accountRoleName == ""){
		return dialog("角色名称不能为空");
	}
	if(secendMemu.length == 0){
		dialog('不能一个菜单都不选');
	}
	else{
		jQuery.ajax({
			type : "POST",
			url : baseUrl + "system/view/saveRole",
			data : datas,
			dataType : "json",
			success : function(data) {
				if (data.code != 0) {
	                dialog(data.msg);
	                return;
	            }
				var accountRoleInfo = data.msg;
				jQuery("select[name='roleId']").removeAttr("disabled");
				dialog('保存成功');
//	 			jQuery(".zzc").find("input[name='accountRoleName']").attr("accountRoleId",0);
//	 			jQuery(".zzc").addClass("hide");
				window.location.href = baseUrl +"system/view/showRole";
			}
		});
	}
	
}

function deleteRole(accountRoleId){
	var datas = "accountRoleId="+accountRoleId;
	if(confirm("确认删除吗")){
		jQuery.ajax({
			type : "POST",
			url : baseUrl + "system/view/deleteRole",
			data : datas,
			dataType : "json",
			success : function(data) {
				if (data.code != 0) {
	                dialog(data.msg);
	                return;
	            }
				dialog('删除成功');
				jQuery(".content_right").find("li[id='"+accountRoleId+"']").attr("style"," display: none;")
			}
		});
	}
}
function hideRole(){
	jQuery('.zzc').addClass('hide'); 
	jQuery('.zzc').find('input').attr('accountRoleId',0);
	jQuery("select[name='roleId']").removeAttr("disabled");
}
//dialog('msg');
</script>
</html>