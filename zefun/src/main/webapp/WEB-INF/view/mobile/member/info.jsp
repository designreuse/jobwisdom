<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>个人资料</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=muiCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
    <style type="text/css">
    	.fix-birthday {
		  width: 100%;
		  margin: 2rem 0rem;
		  padding: 0rem .75rem;
		  .icon-guanbi{
		    font-size: 3rem;
		    color: #ccc;
		  }
		  .name{
		    &:first-child{
		      input{
		        border-top-left-radius: @br10;
		        border-top-right-radius: @br10;
		      }
		    }
		    &:last-child{
		      input{
		        border-bottom-left-radius: @br10;
		        border-bottom-right-radius: @br10;
		        border-bottom: 1px solid #ccc;
		      }
		    }
		
		  }
		  input{
		    width: 100%;
		    height: 5.625rem;
		    background-color: #fff;
		    border: 1px solid #ccc;
		    padding: 0rem 1rem;
		    border: 1px solid #ccc;
		  }
		}
    </style>
  </head>
<body>

<div class="content wrap">
    <div class="person-info-show">
	    <ul class="person-info-show-ul border-radius-ul">
	        <li class="avatar normal-li">
	            <span class="list-name">头像</span>
	            <span class="fr normoal-word"> <i class="iconfont icon-right fr"></i></span>
	            <img id="headImg" src="<%=picPath%>${memberBaseInfo.headUrl }?imageView2/1/w/220/h/220" onclick="chooseImgage();" class="fr "/>
	        </li>
	        <li class="name normal-li" onclick="showFixName();" >
	            <span class="li-name">昵称</span>
	            <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	            <span class="fr font-999" id="labelName">${memberBaseInfo.name }</span>
	        </li>
	        <li class="sex normal-li" onclick="showFixSex();">
	            <span class="li-name">性别</span>
	            <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	            <span class="fr font-999" id="labelSex">${memberBaseInfo.sex }</span>
	        </li>
	        <li class="name normal-li" onclick="showFixBirthday();" >
	            <span class="li-name">生日</span>
	            <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	            <span class="fr font-999" id="labelBirthday">
	            	<c:choose>
	            		<c:when test="${empty memberBaseInfo.birthday }">
	            			未设置
	            		</c:when>
	            		<c:otherwise>
	            			${memberBaseInfo.birthday }
	            		</c:otherwise>
	            	</c:choose>
	            </span>
	        </li>
	    </ul>
	
	    <ul class="person-info-show-ul border-radius-ul">
	        <li class="name normal-li">
                <span class="li-name">手机号</span>
                <span class="fr font-999">${memberBaseInfo.phone }</span>
            </li>
            <c:choose>
                <c:when test="${isPayCode == 0 }">
                    <li class="name normal-li" onclick="setPwd();">
		                <a href="javescript:void(0);">
		                    <span class="li-name">支付密码</span>
		                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
		                    <span class="fr font-999">设置支付密码</span>
		                </a>
		            </li>
                </c:when>
                <c:otherwise>
                    <li class="name normal-li" onclick="showFixPwd();">
		                <a href="javescript:void(0);">
		                    <span class="li-name">支付密码</span>
		                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
		                    <span class="fr font-999">******</span>
		                </a>
		            </li>
                </c:otherwise>
            </c:choose>
	    </ul>
	    
	    <ul class="person-info-show-ul border-radius-ul">
            <li class="name normal-li">
                <a href="javascript:logoutTip();">
	                <span class="li-name">解除关联</span>
	                <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
                </a>
            </li>
        </ul>
	</div>
	
	<!--解除关联-->
    <div class="s-modal hide s-modal-miss" id="logoutTip">
        <div class="s-modal-wrap">
            <div class="d-member-info">
                <div class="n-modal-title text-center">
                    <span>解除关联</span>
                    <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
                    <div class="clearfix"></div>
                </div>
                <div class="s-modal-body">
                    <div class="word text-left tip">
                        解除绑定后此微信将不再收到任何与当前会员相关的消息通知
                    </div>
                </div>
                <div class="s-modal-foot">
                    <div class="modal-cancel">取消</div>
                    <div class="modal-confirm" onclick="logout();">确认</div>
                </div>
            </div>
        </div>
    </div>
	
	<div class="fix-name hide">
	    <div class="fix-desc">昵称：</div>
	    <div class="name">
	        <input type="text" id="name" value="${memberBaseInfo.name }" /><span class="iconfont icon-guanbi ml-4"></span>
	    </div>
	    <div class="btn-group mt2">
	        <div class="normal-btn btn fr" onclick="fixName();">确定</div>
	        <div class="btn-cancel btn mr2 fr" onclick="hideOperate('.fix-name');">取消</div>
	    </div>
	</div>
	
	<div class="fix-birthday hide">
	    <div class="fix-desc">生日：</div>
	    <div class="name">
	        <input type="text" id="birthday" readonly="readonly" value="${memberBaseInfo.birthday }" />
	    </div>
	    <div class="btn-group mt2">
	        <div class="normal-btn btn fr" onclick="fixBirthday();">确定</div>
	        <div class="btn-cancel btn mr2 fr" onclick="hideOperate('.fix-birthday');">取消</div>
	    </div>
	</div>
	
	<div class="fix-sex hide">
	    <div class="fix-desc">性别：</div>
	    <div class="sex male">
	        <span>男</span>
	        <div class="checker fr">
	            <div class="beau-radio">
	                <span class="iconfont icon-gou"></span>
	            </div>
	            <input type="radio" class="yellow-radio" value="男" name="sex"/>
	        </div>
	    </div>
	    <div class="sex female">
	        <span>女</span>
	        <div class="checker fr">
	            <div class="beau-radio">
	                <span class="iconfont icon-gou"></span>
	            </div>
	            <input type="radio" class="yellow-radio" value="女" name="sex"/>
	        </div>
	    </div>
	    <div class="btn-group mt2">
            <div class="normal-btn btn fr" onclick="fixSex();">确定</div>
            <div class="btn-cancel btn mr2 fr" onclick="hideOperate('.fix-sex');">取消</div>
        </div>
	</div>
	
	<div class="fix-password hide">
	    <div class="fix-desc">修改密码：</div>
	    <div class="input-wrap">
	        <input class="clearInput" type="tel" maxlength="6" id="oldPwd" placeholder="当前密码"/><span class="iconfont icon-guanbi ml-4 hide"></span>
	    </div>
	    <div class="input-wrap">
	        <input class="clearInput" type="password" maxlength="6" id="newPwd" placeholder="新密码"/><span class="iconfont icon-guanbi ml-4 hide"></span>
	    </div>
	    <div class="input-wrap">
	        <input class="clearInput" type="tel" maxlength="6" id="repeatPwd" placeholder="重复密码"/><span class="iconfont icon-guanbi ml-4 hide"></span>
	    </div>
	
	    <div class="btn-group mt2">
	        <div class="normal-btn btn fr" onclick="updatePassword();">确定</div>
	        <div class="btn-cancel btn mr2 fr" onclick="hideOperate('.fix-password');">取消</div>
	    </div>
	</div>
</div>  

<%@include file="../wechatBase.jsp" %>
<script type="text/javascript" src="<%=basePath%>js/mobile/monthData.js"></script>
<script type="text/javascript" src="<%=muiJsPath%>"></script>
<script src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
<script src="<%=basePath%>js/mobile/md5.js"></script>
<%@include file="../memberBase.jsp" %>
<script>
//初始化月－日选项
(function($, jq) {
    $.init();
    $.ready(function() {
    	//初始化多选项
        var userPicker = new $.PopPicker({layer: 2});
        userPicker.setData(monthData);
        var birthday = document.getElementById('birthday');
        birthday.addEventListener('tap', function(event) {
        	document.activeElement.blur();
			userPicker.show(function(items) {
				birthday.value = (items[0] || {}).value + "-" + (items[1] || {}).value;
				return true;
			});
		}, false);
    });
})(mui, $);

    var headUrl = "${memberBaseInfo.headUrl }";
    var name = "${memberBaseInfo.name }";
    var birthday = "${memberBaseInfo.birthday }";
    var sex = "${memberBaseInfo.sex }";
    var userId = "${session_key_user_id}";
    var mainWindow = $(".person-info-show");
    
    //前往设置支付密码页面
    function setPwd(){
    	console.log("set password ... ");
    	window.location.href = baseUrl + "memberCenter/view/setPwd";
    }
    
    $(function(){
    	$(".yellow-radio").on("click", function () {
            var radio = $(".yellow-radio");
            for(var i=0;i<radio.length;i++) {
                if(radio[i].checked) {
                    $(radio[i]).siblings(".beau-radio").addClass("active");
                }else{
                    $(radio[i]).siblings(".beau-radio").removeClass("active");
                }
            }
        });
    	
    	$(".icon-guanbi").click(function(){
            $(this).prev().val('');
        });
        
        $(".clearInput").keyup(function(){
            if($(this).val() != ''){
                $(this).next().removeClass("hide");
            } else {
                $(this).next().addClass("hide");
            }
        });
        
        $(".clearInput").blur(function(){
            $(this).next().addClass("hide");
        });
       
        $(".clearInput").focus(function(){
            if($(this).val() != ''){
               $(this).next().removeClass("hide");
            }
        });
    });
    
    function checkSex(s){
    	$(".beau-radio").removeClass("active");
    	if("男" == s){
            $(".male").find(".beau-radio").addClass("active");
        } else {
            $(".female").find(".beau-radio").addClass("active");
        }
    }
    
    function showFixName(){
    	mainWindow.hide();
    	$("#name").val($("#labelName").html());
        $(".fix-name").removeClass("hide"); 
    }
    
    function showFixBirthday(){
    	mainWindow.hide();
    	$("#birthday").val($("#labelBirthday").html());
        $(".fix-birthday").removeClass("hide"); 
    }
    
    function showFixSex(){
        mainWindow.hide();
        checkSex($("#labelSex").html());
        $(".fix-sex").removeClass("hide");
    }
    
    function showFixPwd(){
    	$("#oldPwd").val("");
        $("#newPwd").val("");
        $("#repeatPwd").val("");
        showOperate(".fix-password");
    }
    
    function fixName(){
    	name = $("#name").val();
    	if(isEmpty(name)){
        	dialog("请填写您的真实姓名~");
            return;
        }
        if (getByteLen(name) > 12) {
            dialog("姓名最长为6个汉字或12个英文字符～");
            return;
        }
    	updateInfo(function(e){
    		if(e.code != 0){
                dialog(e.msg);
                return;
            }
    		$("#labelName").html(name);
    		hideOperate(".fix-name");
    	})
    }
    
    function fixBirthday(){
    	birthday = $("#birthday").val();
    	if(isEmpty(birthday)){
        	dialog("请填写您的生日~");
            return;
        }
    	updateInfo(function(e){
    		if(e.code != 0){
                dialog(e.msg);
                return;
            }
    		$("#labelBirthday").html(birthday);
    		hideOperate(".fix-birthday");
    	})
    }
    
    function fixSex(){
        sex = $(".beau-radio.active").siblings("[name='sex']").val();
        updateInfo(function(e){
        	if(e.code != 0){
                dialog(e.msg);
                return;
            }
            $("#labelSex").html(sex);
            hideOperate(".fix-sex");
        })
    }
    
    function hideOperate(obj){
    	mainWindow.show();
        $(obj).addClass("hide");
    }
    
    function showOperate(obj){
    	mainWindow.hide();
        $(obj).removeClass("hide");
    }
    
    //修改个人信息
    function updateInfo(callback){
    	$.ajax({
    		url : baseUrl + "memberCenter/action/updateInfo",
    		type : "POST",
    		data : "name=" + name + "&headUrl=" + headUrl + "&sex=" + sex + "&birthday=" + birthday,
    		success : callback
    	});
    }
    
    //修改支付密码
    function updatePassword(){
        var oldPwd = $("#oldPwd").val();
        var newPwd = $("#newPwd").val();
        var repeatPwd = $("#repeatPwd").val();
        if(isNaN(newPwd) || newPwd.length != 6){
        	dialog("支付密码只能为6为数字");
            return;
        }
        if(newPwd != repeatPwd){
            dialog("两次密码输入不一致");
            return;
        }
        oldPwd = CryptoJS.MD5(CryptoJS.MD5(oldPwd).toString().toUpperCase()).toString().toUpperCase();
        newPwd = CryptoJS.MD5(CryptoJS.MD5(newPwd).toString().toUpperCase()).toString().toUpperCase();
        $.ajax({
            url : baseUrl + "memberCenter/action/updatePaycode",
            type : "POST",
            data : "oldPwd=" + oldPwd + "&newPwd=" + newPwd,
            success : function(e){
                if(e.code != 0){
                    dialog(e.msg);
                    return;
                }
                dialog("修改成功");
                hideOperate(".fix-password");
            }
        });
    }
    
    function logoutTip(){
    	$("#logoutTip").removeClass("hide");
    }
    
    //会员注销
    function logout(){
        $.ajax({
            url : baseUrl + "memberCenter/action/logout",
            type : "POST",
            success : function(e){
                if(e.code != 0){
                    dialog(e.msg);
                    return;
                }
                dialog("已成功解除关联");
                WeixinJSBridge.call('closeWindow');
            }
        });
    }
    
    function chooseImgage(){
        wx.chooseImage({
            count: 1,
            sizeType: ['original'],
            sourceType: ['album', 'camera'], 
            success: function (res) {
                var localIds = res.localIds; 
                wx.uploadImage({
                    localId: localIds[0], 
                    isShowProgressTips: 1,
                    success: function (res) {
                        var serverId = res.serverId;
                        var key = "userhead/" + userId + "/" + new Date().getTime();
                        $.ajax({
                            type : "post",
                            url : baseUrl + "wechat/fetch/media",
                            data : "mediaid=" + serverId + "&key=" + key,
                            dataType : "json",
                            success : function(e){
                                if (e.code != 0) {
                                    dialog(e.msg);
                                    return;
                                }
                                headUrl = key;
                                $("#headImg").attr("src", picUrl + key + "?imageView2/1/w/220/h/220");
                                updateInfo(null);
                            }
                        });
                    }
                });
            }
        });
    }
</script>
</body>
</html>