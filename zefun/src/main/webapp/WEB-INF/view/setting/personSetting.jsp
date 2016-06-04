<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>css/account_set.css" />
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<head>
    <script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
    <script type="text/javascript">
    
    function editPage (imgUrl) {
    	xiuxiu.embedSWF("altContent2", 5, 700, 500);
    	
    	xiuxiu.onInit = function (id)
    	{
            xiuxiu.setUploadType(3);
            if (imgUrl != null) {
            	xiuxiu.loadPhoto(qiniuUrl + imgUrl, false);
            }
    	}
    	xiuxiu.onSaveBase64Image = function (data, fileName, fileType, id)
    	{
    		data = "data:image/"+fileType+";base64," + data;
    		zccCallback(data);
    	}
    	
    	xiuxiu.onDebug = function (data)
    	{
            dialog("网咯繁忙，请重试！");
    	}
    	
    	xiuxiu.onClose = function (id)
    	{
            jQuery(".mask").hide();
    	}
    	
    }
	
    </script>
</head>
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				
		        <div class='content_right clearfix'>
				     <div class="accont_detail clearfix">
					    <div class="accont_detail_left">
						   <p class="guest_detail">客户信息</p>
						   <div class="account_set_head">
						      <%-- <span class="account_set_head_"><img src="<%=qiniuPath %>${session_key_user_info.headImage }"></span> --%>
						      <c:choose>
							      <c:when test="${session_key_user_info.headImage == null}">
							         <span class="account_set_head_"><img border=0 affiliatedImage="" name="affiliatedImage" src="<%=qiniuPath%>system/profile/set_img.png" width="180" height="180" /></span>
							      </c:when>
							      <c:otherwise>
							         <span class="account_set_head_"><img border=0 affiliatedImage="${session_key_user_info.headImage }" name="affiliatedImage" src="<%=qiniuPath%>${session_key_user_info.headImage }" width="180" height="180" /></span>
							      </c:otherwise>
						      </c:choose>
						      <span>
							     <button class="alter_head"><img src="<%=basePath %>images/webchat_add.png">修改头像</button>
							  </span>
							 <p class="account_name">姓名：<span><input type="text" name="name" value="${session_key_user_info.name }"></span></p>
						    <p class="account_sex">性别：<span><input type="radio" name="sex" value="男">男  </span> <span><input type="radio" name="sex" value="女">女</span></p>
						     <div class="set_save">
							 
							   <button class="first_button" onclick ="save();">保存</button>
							 </div>
						   </div>
						
						</div>
						
					 <div class="accont_detail_right">
						   <p class="guest_detail">修改密码</p>
						   <div class="account_set_head_1">
						     <p class="account_name_">原密码：<span><input type="text" id="oldPwd"></span></p>
							 <p class="account_name_">新密码：<span><input type="text" id="newPwd"></span></p>
							<p class="account_name_" style="position:relative;left:-12px"/>确认密码：<span><input type="text" id="repeatPwd"></span></p>
						      
							  <button class="account_sure" onclick = "updatePassword();">确认</button>
						   </div>
						
						</div>
						
						
					 
					 
					 
					 </div>
					
				  </div>
    </div>
</div>
</div>
<!-- 美图秀秀 -->
<div class="mask" style="display: none;">
   <div id="flashEditorOut" >
        <div id="altContent2">
            <h1>美图秀秀2</h1>
        </div>
	</div>
</div>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<script type="text/javascript">

function zccCallback(dataBase64){
	jQuery(".account_set_head_").children("img").attr("src", dataBase64);
	var key = "jobwisdom/project/" + new Date().getTime();

	jQuery(".account_set_head_").children("img").attr("affiliatedImage", key);
    var data = {"stringBase64":dataBase64,"key":key};
    jQuery('.cancelinput').click();
    jQuery.ajax({
		type: "POST",
		url: baseUrl+"qiniu/base64",
	       data: JSON.stringify(data),
	       contentType: "application/json",
	       dataType: "json",
	       async:true,  
	       beforeSend:function(){
	       	console.log("beforeSend upload image");
	       },
	       error:function (){
	       	console.log("upload image error");
	       },
	       complete :function (){
	       	console.log("complete upload image");
	       },
	       success: function(data) {
		       	var imageUrl = data.msg.imageUrl;
		       	var key = data.msg.key;
		       	jQuery(".mask").hide();
	       }
 	});
}

jQuery(function(){
	jQuery("[name='sex'][value='${session_key_user_info.sex}']").attr('checked',true);
});

jQuery('.alter_head').click(function(){
	editPage();
	jQuery(".mask").show();
 })
 jQuery('.cancelinput').click(function(){
	 jQuery(".mask").hide();
 })

var audio = null;
function testVoice(per){
	jQuery.ajax({
	    url : baseUrl + "qiniu/textToVoice",
	    type : "POST",
	    data : "text=感谢您选择我道系统&per=" + per,
	    beforeSend : function(){
	    },
	    complete : function(){
	    },
	    success : function(e){
	    	if(e.code != 0){
	    		dialog("出了点小问题，要不重试一次?");
	    		return;
	    	}
	    	if(audio != null) audio.pause();
	    	
            audio = new Audio();
            audio.src = picUrl + e.msg;
            audio.play();
	    }
	});
}

function showUpdatePassword(){
	jQuery("#oldPwd").val('');
    jQuery("#newPwd").val('');
    jQuery("#repeatPwd").val('');
}

function updatePassword(){
	var oldPwd = jQuery("#oldPwd").val();
	var newPwd = jQuery("#newPwd").val();
	var repeatPwd = jQuery("#repeatPwd").val();
	
	if (isEmpty(oldPwd)) {
		dialog("请输入您的原密码");
		return;
	}
	
	if (isEmpty(newPwd)) {
        dialog("请输入您的新密码");
        return;
    }
	
	if (newPwd != repeatPwd) {
        dialog("两次密码不一致");
        return;
    }
	
	oldPwd = CryptoJS.MD5(CryptoJS.MD5(oldPwd).toString().toUpperCase()).toString().toUpperCase();
	newPwd = CryptoJS.MD5(CryptoJS.MD5(newPwd).toString().toUpperCase()).toString().toUpperCase();
	jQuery.ajax({
        url : baseUrl + "system/action/updatePwd",
        type : "POST",
        data : "oldPwd=" + oldPwd + "&newPwd=" + newPwd,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("密码修改成功");
            console.log("密码修改成功");
            showUpdatePassword();
        }
    });
}

function save(){
    var headImage = jQuery(".account_set_head_").find("img").attr("affiliatedImage");
    var name = jQuery("input[name='name']").val();
    var sex = jQuery("input[name='sex']:checked").val();
	var data = {"headImage" : headImage, "name" : name, "sex" : sex};
	jQuery.ajax({
		url : baseUrl + "system/action/person",
		type : "POST",
		data : data,
		success : function(e){
			if (e.code != 0) {
                dialog(e.msg);
                return;
            }
			dialog("更新成功");
		}
	});
}

</script>
</body>
</html>