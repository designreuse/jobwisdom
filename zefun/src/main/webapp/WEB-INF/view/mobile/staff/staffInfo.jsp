<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>个人信息</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
</head>
<body>
<div class="wrap">
	<div class="content mb5">
		<div class="person-info-show">
		    <ul class="person-info-show-ul border-radius-ul">
		        <li class="avatar normal-li">
		            <span class="list-name li-name dep-blue-font-color">头像</span>
		            <span class="fr iconfont icon-right"></span>
		            <img id="headImg" src="<%=picPath %>${employeeInfo.headImage}?imageView2/1/w/230/h/230" onclick="chooseImgage()" alt="" class="fr mr2"/>
		        </li>
		        <li class="name normal-li">
		            <span class="li-name dep-blue-font-color">姓名</span>
		            <span class="fr iconfont icon-right"></span>
		            <span class="fr font-999">${employeeInfo.name }</span>
		        </li>
		        <li class="sex normal-li">
		            <span class="li-name dep-blue-font-color">工号</span>
		            <span class="fr iconfont icon-right"></span>
		            <span class="fr font-999">${employeeInfo.employeeCode }</span>
		        </li>
		        <li class="dengji normal-li">
		            <span class="li-name dep-blue-font-color">职位</span>
		            <span class="fr iconfont icon-right"></span>
		            <span class="fr font-999">${employeeInfo.levelName }</span>
		        </li>
		    </ul>
		    <div class="zilian font-size-24">员工信息由店长/老板管理</div>
		    <ul class="person-info-show-ul border-radius-ul">
		        <li class="name normal-li ">
		            <a href="<%=basePath %>staff/view/updatePwd">
		                <span class="li-name dep-blue-font-color">登录密码</span>
		                <span class="fr iconfont icon-right"></span>
		                <span class="fr font-999" style="margin-top: 0.4rem;">******</span>
		            </a>
		        </li>
		        <li class="name normal-li ">
                    <a href="javascript:logoutTip();">
                        <span class="li-name dep-blue-font-color">解除绑定</span>
                        <span class="fr iconfont icon-right"></span>
                    </a>
                </li>
		    </ul>
		</div>
		<!-- 退出确认提示 -->
	    <div class="s-modal hide s-modal-miss" id="logoutTip">
	        <div class="s-modal-wrap">
	            <div class="d-member-info">
	                <div class="n-modal-title text-center">
	                    <span>解除绑定</span>
	                    <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
	                    <div class="clearfix"></div>
	                </div>
	                <div class="s-modal-body">
	                    <div class="word text-left">
	                        解除绑定后此微信将不再收到任何与当前员工相关的消息通知
	                    </div>
	                </div>
	                <div class="s-modal-foot">
	                    <div class="modal-cancel">我点错了</div>
	                    <div class="modal-confirm" onclick="logout();">确认解除</div>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
</div>
<%@include file="../wechatBase.jsp" %>
<script src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script type="text/javascript">

var userId = "${session_key_user_id}";

function logoutTip(){
    $("#logoutTip").removeClass("hide");
}

function logout(){
	$.ajax({
        url : baseUrl + "staff/action/logout",
        type : "POST",
        success : function(e){
            if (e.code != 0) {
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
                            updateEmployeeInfo(key);
                            updateInfo(null);
                        }
                    });
                }
            });
        }
    });
}

function updateEmployeeInfo(headImage){
	$.ajax({
        url : baseUrl + "staff/action/updateMsg",
        type : "POST",
        dataType : "json",
        data : "headImage=" + headImage,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
        }
    });
}
</script>
</body>
</html>