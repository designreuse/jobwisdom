<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>完善个人信息</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=muiCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
    <style>
	    .icon-position {
	        display: inline-block;
	        position: relative;
	    }
	    .edit-font-wrap {
	        position: absolute;
	        top: .5rem;
	        right: 0rem;
	        height: 2.1rem;
	        line-height: 2.1rem;
	        width: 2.1rem;
	        border-radius: 50%;
	        background-color: #ecb200;
	        color: #fff;
	        text-align: center;
	        font-size: 2rem;
	    }
	    .tip-title {
	        margin-top: 3rem;
	        font-size: 1.5rem;
	        color: #333;
	    }
	    .tip-content {
	        text-indent: 2em;
	        font-size: 1.5rem;
	        color: #666;
	    }
	    .tip-content {
	        margin: .5rem 0;
	    }
	    .icon-iconfontmodify2{
	        cursor: pointer;
	        vertical-align: super;
	        font-size: 1.3rem;
	    }
	    input {
	        font-size: 2rem;
	        color: #333;
	    }
	    .register-info {
	        border: 0;
	    }
	    .register-info {
	        background-color: transparent;
	    }
	    .perfect-ul {
	        background-color: transparent;
	    }
	    .perfect-ul li{
	        background-color: #fff;
	    }
	    .normal-li input {
	    	margin: 0;
	    	width: auto;
	    	height: auto;
	    	border: 0;
	    	padding: 0;
	    }
	
	</style>
  </head>
<body>

<div class="content wrap">
    <div class="perfect-per-info">
	    <div class="register-info">
	        <div class="registre-ul-wrap">
	            <ul class="perfect-ul">
	                <li class="person-img">
	                    <div class="img-wrap">
	                    	<div class="icon-position">
		                        <c:choose>
					               <c:when test="${empty headimgurl }">
					                   <img id="headImg" src="<%=picPath%>userhead/default/female.png?imageView2/1/w/200/h/200" alt="" class="per-img" onclick="chooseImgage()"/>
					               </c:when>
					               <c:otherwise>
					                   <img id="headImg" src="${headimgurl }" alt="" class="per-img" onclick="chooseImgage()"/>
					               </c:otherwise>
					           </c:choose>
					           <div class="edit-font-wrap"><span class="iconfont icon-iconfontmodify2"></span></div>
				           </div>
	                    </div>
	                </li>
	                <li class="normal-li ">
	                    <span class="span-label"><i class="iconfont icon-person"></i>姓名：</span>
	                    <input type="text" id="nickname" value="" placeholder="该怎么称呼您？"/>
	                </li>
	                <li class="normal-li ">
	                    <span class="span-label"><i class="iconfont icon-sex1 "></i>性别：</span>
	                    <c:choose>
	                       <c:when test="${sex == 2 }">
	                            <div class="part2">
		                            <span class="word">女</span>
		                            <div class="checker">
		                                <div class="beau-radio active">
		                                    <span class="iconfont icon-gou"></span>
		                                </div>
		                                <input type="radio" checked="checked" value="女" class="yellow-radio" name="sex"/>
		                            </div>
		                        </div>
		                        <div class="part1">
		                            <span class="word">男</span>
		                            <div class="checker">
		                                <div class="beau-radio">
		                                    <span class="iconfont icon-gou"></span>
		                                </div>
		                                <input type="radio" class="yellow-radio" value="男" name="sex"/>
		                            </div>
		                        </div>
	                       </c:when>
	                       <c:otherwise>
	                            <div class="part2">
                                    <span class="word">女</span>
                                    <div class="checker">
                                        <div class="beau-radio">
                                            <span class="iconfont icon-gou"></span>
                                        </div>
                                        <input type="radio" checked="checked" class="yellow-radio" value="女" name="sex"/>
                                    </div>
                                </div>
                                <div class="part1">
                                    <span class="word">男</span>
                                    <div class="checker">
                                        <div class="beau-radio active">
                                            <span class="iconfont icon-gou"></span>
                                        </div>
                                        <input type="radio" checked="checked" class="yellow-radio" value="男" name="sex"/>
                                    </div>
                                </div>
	                       </c:otherwise>
	                    </c:choose>
	                </li>
	                <li class="normal-li ">
	                    <span class="span-label"><i class="iconfont icon-person"></i>生日：</span>
	                    <input type="tel" id="birthday" readonly="readonly" required="required" value="" placeholder="生日送好礼，选填"/>
	                </li>
	                <li class="normal-li ">
	                    <span class="span-label">支付密码：</span>
	                    <input type="tel" id="password" maxlength="6" placeholder="6位数字，选填"/>
	                </li>
	            </ul>
	        </div>
	    </div>
	    
	    <a href="javascript:register();" class="confirm-btn mt2">进入主页</a>
	    
	    <div class="tip">
	        <div class="tip-title">温馨提示：</div>
	        <div class="tip-content">
	            <p>1. 为了更好的为您服务，请您务必填写真实姓名！<br></p>
	            <p>2. 为了您的账户信息安全，请您设置支付密码！</p>
	            <p>&nbsp;&nbsp;&nbsp;&nbsp;在忘记带手机消费时使用此密码确认付款。</p>
	        </div>
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

    $(function(){
        $(".hide-radio").on("click", function(){
            var radioElement =  $(".hide-radio");
            if(radioElement.attr("checked")){
                radioElement.parents(".radio-wrap").find(".radio-label").removeClass("hide");
            }else{
                radioElement.parents(".radio-wrap").find(".radio-label").addClass("hide");
            }
        });
    });
    
    var headUrl = "${headimgurl }";
    if(headUrl == null || headUrl == ''){
        headUrl = "userhead/default.png";
    }
    
    function register(){
        var nickName = $("#nickname").val();
        if(isEmpty(nickName)){
        	dialog("请填写您的真实姓名~");
            return;
        }
        if (getByteLen(nickName) > 12) {
            dialog("姓名最长为6个汉字或12个英文字符～");
            return;
        }
        var birthday = $("#birthday").val();
        var password = $("#password").val();
        if(!isEmpty(password) && (isNaN(password) || password.length != 6)){
        	dialog("支付密码只能为6为数字");
            return;
        }
        if (!isEmpty(password)) {
        	password = CryptoJS.MD5(CryptoJS.MD5(password).toString().toUpperCase()).toString().toUpperCase();
        }
        var sex = $("[name='sex']:checked").val();
        $.ajax({
            type : "post",
            url : baseUrl + "memberCenter/action/registerInfo",
            data : "nickname=" + nickName + "&paycode=" + password + "&headUrl=" + headUrl + "&sex=" + sex + "&birthday=" + birthday,
            dataType : "json",
            success : function(e){
                if (e.code != 0) {
                    dialog(e.msg);
                    return;
                }
                window.location.href = baseUrl + "memberCenter/view/home/${session_key_store_account}/1";
            }
        });
    }
    
    function getByteLen(val) {
        var len = 0;
        for (var i = 0; i < val.length; i++) {
           var length = val.charCodeAt(i);
           if(length>=0&&length<=128)
            {
                len += 1;
            }
            else
            {
                len += 2;
            }
        }
        return len;
    }
    
    var userId = "${session_key_user_id}";
    function chooseImgage(){
        wx.chooseImage({
            count: 1,
            sizeType: ['original', 'compressed'],
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
                                $("#headImg").attr("src", picUrl + key + "?imageView2/1/w/200/h/200");
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