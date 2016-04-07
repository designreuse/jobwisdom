<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<%@ include file="/base.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>业务员</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
    <style>
    	.input-wrap #salesmanPhone{
    		border:1px solid #ccc;
    	}
    	.input-wrap #salesmanName{
    		border:1px solid #ccc;
    	}
    	.input-wrap #salesmanPwd{
    		border:1px solid #ccc;
    	}
    	.input-wrap #salesmanAge{
    		border:1px solid #ccc;
    	}
    	.input-wrap #salesmanComment{
    		border:1px solid #ccc;
    	}
    	.input-wrap{
    		margin-right:0;
    	}
    </style>
</head>
<body>
<div class="new-yewu">
  <div class="content">
	<div class="register">
	
	    <div class="input-wrap mt20">
	    	<span class="left-label register-label">手机：</span>
	        <div class="name register-input">
            	<input type="tel" id="salesmanPhone" />
            </div>
	    </div>
	    
	    <div class="input-wrap mt20">
	    	<span class="left-label register-label">密码：</span>
	        <div  class="name register-input">
                <input type="password" id="salesmanPwd"/>
            </div>
	    </div>
	    
	    <c:if test="${agentId != null}">
	    	<div class="input-wrap mt20">
	    		<span class="left-label register-label">姓名：</span>
		        <div  class="name register-input">
                    <input type="text" id="salesmanName"/>
                </div>
		    </div>
		    
	    	<div class="input-wrap mt20">
	    		<span class="left-label register-label">年龄：</span>
		        <div class="name register-input">
                    <input type="tel" id="salesmanAge"/>
                </div>
		    </div>
		    
		    <div class="normal-li sex mt20">
               <span class="span-label left-label register-label">性别：</span>
               <div class="name register-input">
                   <div class="part1">
                       <div class="checker">
                           <div class="beau-radio">
                               <span class="iconfont icon-gou"></span>
                           </div>
                           <input type="radio" class="yellow-radio" name="sex" value="0" checked="checked"/>
                       </div>
                       &nbsp;&nbsp;
                       <span class="word">男</span>
                   </div>
                   <div class="part2">
                       <div class="checker">
                           <div class="beau-radio">
                               <span class="iconfont icon-gou"></span>
                           </div>
                           <input type="radio" class="yellow-radio" name="sex" value="1" />
                       </div>
                       &nbsp;&nbsp;
                       <span class="word">女</span>
                   </div>
               </div>
            </div>
            
		    <div class="input-wrap mt20">
                <span class="span-label left-label register-label">备注：</span>
                <div class="name register-input">
                    <input type="text" id="salesmanComment" />
                </div>
            </div>
	    </c:if>
	    
	    <div class="btn-group" onclick="salesmanRegister(${agentId})">
	    	<!-- 渠道注册业务员 -->
	        <c:if test="${agentId != null}">
	    		<div class="tj-btn w100p btn">添加</div>
	    	</c:if>
	    	<!-- 业务员第一次登录(绑定业务员openId) -->
	    	<c:if test="${agentId == null }">
	    		<div class="tj-btn w100p btn">登录</div>
	    	</c:if>
	    </div>
	    
	</div>
  </div>
</div>

</body>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script>
/*漂亮的单选和多选*/
$(".checker").on("click", function () {
    var radio = $(".yellow-radio");
    for(var i=0;i<radio.length;i++)
    {
        if(radio[i].checked)
        {
            $(radio[i]).siblings(".beau-radio").addClass("active");
        }else{
            $(radio[i]).siblings(".beau-radio").removeClass("active");
        }
    }
});
//渠道商注册业务员/业务员第一次登录
function salesmanRegister(agentId) {
	var phone = jQuery("#salesmanPhone").val();
    if(!phone || !$.trim(phone)) {
      dialog("手机号不能为空");
      return;
    }
    phone = $.trim(phone);
    if(!validate_phone(phone)) {
    	dialog("请输入正确的手机号");
        return;
    }
    var pwd = jQuery("#salesmanPwd").val();
    if(!pwd || !$.trim(pwd)) {
      dialog("请输入密码");
      return;
    }
    var param = "phone=" + phone + "&password=" + pwd + "&agentId=${agentId}";
    //如果是渠道商增加业务员就要填写姓名,业务员首次登录就不用
    if (agentId || $.trim(agentId)) {
    	var name = jQuery("#salesmanName").val();
        if(!name || !$.trim(name)) {
          dialog("姓名不能为空");
          return;
        }
        param += "&name=" + name + "&age=" + jQuery("#salesmanAge").val() + "&gender=" + $('input[name="sex"]:checked').val() + "&comment=" + jQuery("#salesmanComment").val();
    }
	jQuery.ajax({
		type : "post",
		url : baseUrl + "salesman/action/add",
		data : param,
		dataType : "json",
		success : function(e){
			if (e.code != 0) {
				alert(e.msg);
				return;
			}
			else if (agentId || $.trim(agentId)){
				alert("添加成功");
			}
			window.location.href = baseUrl + e.msg;
		}
	});
}
</script>
</html>