<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>智慧美业，绽放未来</title>
<link rel="stylesheet" href="<%=basePath %>css/style.default.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>css/zhifang.css" type="text/css" />
<style>
	#particles.particles-box {
        position: absolute;
        width: 100%;
        top: 0;
        bottom: 0;
        background: url(<%=basePath%>img/home/login-bg.png) no-repeat center center;
        background-size: 100% 100%;
        min-height: 100%;
    }
    body{
        margin: 0;
        height: 100%;
        overflow: hidden;
    }
    .loginwrapper{
        position: absolute;
        top:80px;
        right: 200px;
    }
    .logintitle {
        text-align: center;
        font-size: 36px;
        color: #18beb6;
        background-image: none;
        border: 0;
        box-shadow: none;
        margin: 10px;
    }
    .loginwrapperinner{
        position: relative;
        backgrond-color: #0e2e2e;
    }
    .loginwrapperinner {
        border: 1px solid rgb(29, 159, 152);
        border-radius: 3px;
        background-color: rgb(14, 56, 55);
        box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.2);
    }
    .loginform input {
        color: #2f7e7c;
        border: 1px solid #41c8c1;
        border-radius: 3px;

    }
    #loginform {
         margin-top: 30px;
    }
    .loginwrapper input#username {
        background: #cfd7d7 url(<%=basePath%>img/username.png) no-repeat 8px 12px;
        border: 1px solid #41c8c1;
        border-radius: 3px;
    }
    .loginwrapper input#password {
        background: #cfd7d7 url(<%=basePath%>img/password.png) no-repeat 8px 12px;
        border: 1px solid #41c8c1;
        border-radius: 3px;
    }
    .loginwrapper button.btn {
        background-image: -moz-linear-gradient( 90deg, rgb(26,186,182) 0%, rgb(32,217,211) 100%);
        background-image: -webkit-linear-gradient( 90deg, rgb(26,186,182) 0%, rgb(32,217,211) 100%);
        background-image: -ms-linear-gradient( 90deg, rgb(26,186,182) 0%, rgb(32,217,211) 100%);
        box-shadow: 0px 1px 3px 0px #666;
        color: #333;
        font-size: 18px;
    }
    .wrong-msg {
        width: 358px;
        position: absolute;
        border: 1px solid #e6a7a7;
        padding: 15px 12px;
        background-color: #e3d7d7;
        color: #333;
        box-sizing: border-box;
    }

    .attention-img {
        width: 35px;
        height: 35px;
        vertical-align: middle;
        float: left;
    }
    .reset-pass{
        font-size: 14px;
        color: #2f7e7c;
    }
    .msg-desc {
        margin-left: 40px;
    }
    .go-img {
        margin-left: 30px;
        verticle-align: middle;
    }
    .link-zhifang {
        width: 100%;
        padding: 12px 0;
        border: 1px solid #41c8c1;
        text-align: center;
        font-size: 13px;
        color: #b9dfde;
        background-color: transparent;
        border-radius: 3px;
    }
    .banquan {
        position: absolute;
        width: 100%;
        font-size: 12px;
        color: #fff;
        bottom: 40px;
        text-align: center;
    }
    .logo{
        position: absolute;
        width: 207px;
        height: 58px;
        top: 40px;
        left: 80px;
        z-index: 99;
    }
    .slogon {
        position: absolute;
        width: 380px;
        height: 40px;
        top: 55px;
        left: 350px;
        z-index: 100;
    }
    #staticParticle {
        position: absolute;
        width: 100%;
        top: 0;
        bottom: 0;
        background: url('<%=basePath%>img/home/login-bg.png') no-repeat center center; /*static-particle.png*/
        background-size: 100% 100%;
        min-height: 100%;
    }
    .staticParticle {
        margin-left: 10%;
        height: 80%;
        margin-top: 5%;
    }
    @media (max-width: 1024px) {
        #staticParticle .staticParticle {
            display: none;
        }
    }
</style>
<script src="<%=basePath %>js/common/login.js"></script>
</head>

<body class="loginbody">

<div>
    <div class="logo">
        <img src="<%=basePath %>img/login-logo.png" alt="">
    </div>
    <div class="slogon">
        <img src="<%=basePath %>img/biaoyu.png" alt="">
    </div>

    <div id="particles" class="particles-box hidden-sm hidden-xs"></div>
    <div id="staticParticle" style="display: none">
        <img class="staticParticle" src="<%=basePath%>img/home/static-particle.png" alt="">
    </div>
    
    <div class="loginwrapper">

        <div class="loginwrap zindex100 animate2 bounceInDown">

            <div class="loginwrapperinner">
                <%-- <div class="wrong-msg">
                    <img src="<%=basePath %>img/wrong-msg.png" alt="" class="attention-img">
                    <div class="msg-desc">用户名或密码输入错误，请重新输入。
                        <br>
                        你也可以：<span class="reset-pass">重置登录密码</span>
                    </div>
                </div> --%>
                <h1 class="logintitle">
                    登录智放系统
                </h1>
                <p class="animate4 bounceIn"><input type="text" id="username" name="username" placeholder="用户名" /></p>
                <p class="animate5 bounceIn"><input type="password" id="password" name="password" placeholder="密码" /></p>
                <p class="animate7 fadeIn"><a href="javascript:void(0);" class="fr" style="margin-top: -15px;margin-bottom: 10px;"><span class="iconfont icon-bangzhuzhongxin"></span> 忘记登录密码</a></p>
                <p class="animate6 bounceIn login-p">
                    <button class="btn btn-default btn-block login-btn loginButton">登&nbsp;&nbsp;录</button>
                </p>
                <%-- <p>
                    <button class="link-zhifang">还未加入智放，请立即了解 <img src="<%=basePath %>img/youjiantou.png" alt="" class="go-img"></button>
                </p> --%>
            </div><!--loginwrapperinner-->
        </div>

    <script type="x-shader/x-vertex" id="vertexshader">
        attribute float size;
        attribute vec3 customColor;
        varying vec3 vColor;

        void main() {
            vColor = customColor;
            vec4 mvPosition = modelViewMatrix * vec4( position, 1.0 );
            gl_PointSize = size * ( 300.0 / length( mvPosition.xyz ) );
            gl_Position = projectionMatrix * mvPosition;
        }
    </script>
        <script type="x-shader/x-fragment" id="fragmentshader">
            uniform vec3 color;
            uniform sampler2D texture;
            varying vec3 vColor;
            void main() {
                gl_FragColor = vec4( color * vColor, 1.0 );
                gl_FragColor = gl_FragColor * texture2D( texture, gl_PointCoord );
                if ( gl_FragColor.a < ALPHATEST ) discard;
            }
        </script>
    </div><!--loginwrapper-->
   
    <div class="banquan">
        <span>© 2016 waywant.com 版权所有 ICP证：粤ICP备15091901号</span>
    </div>
</div>

<div id="loadingWrap" class="alertWrap" style="display: none;">
    <div class="loadingImg">
        <img src="http://7xkv8r.com1.z0.glb.clouddn.com/pc/loadData.gif"/>
    </div>
</div>
<div id="alertWrap" class="alertWrap" style="display: none;">
    <div id="alertToast" class="toast">
    </div>
</div>
<script type="text/javascript" src="<%=basePath %>js/common/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/jquery-migrate-1.1.1.min.js"></script>
<script type="text/javascript" src="<%=basePath %>js/base/pc.js"></script>
<script type="text/javascript">
jQuery.noConflict();

jQuery(document).ready(function(){
  
  jQuery.noConflict();

  jQuery(document).ready(function(){
      jQuery("#username").focus();
      
      var anievent = (jQuery.browser.webkit)? 'webkitAnimationEnd' : 'animationend';
      jQuery('.loginwrap').bind(anievent,function(){
          jQuery(this).removeClass('animate2 bounceInDown');
      });
      
      jQuery('#username,#password').focus(function(){
          if(jQuery(this).hasClass('error')) jQuery(this).removeClass('error');
      });
      
      jQuery(document).keyup(function(e){
          if(e.keyCode == 13){
            jQuery('.loginButton').click();
          }
      });
      
      jQuery('.loginButton').click(function(){
        if(jQuery('#username').val() == '' || jQuery('#password').val() == '') {
                if(jQuery('#username').val() == '') {
                    jQuery('#username').focus();
                    jQuery('#username').addClass('error');
                    dialog("对不起，未找到您输入的账号");
                } else {
                    jQuery('#username').removeClass('error');
                }
                if(jQuery('#password').val() == '') {
                    jQuery('#password').focus();
                    jQuery('#password').addClass('error'); 
                    dialog("对不起，您输入的密码错误");
                }else {
                    jQuery('#password').removeClass('error');
                }
                jQuery('.loginwrap').addClass('animate0 wobble').bind(anievent,function(){
                    jQuery(this).removeClass('animate0 wobble');
                });
            }
        
        var username = jQuery("#username").val();
            var password = jQuery("#password").val();
            password = CryptoJS.MD5(CryptoJS.MD5(password).toString().toUpperCase()).toString().toUpperCase();
            jQuery.ajax({
                type : "post",
                url : baseUrl + "user/login",
                data : "username=" + username + "&password=" + password,
                dataType : "json",
                success : function(e){
                    if (e.code != 0) {
                      if (e.code == 9001) {
                        jQuery('#username').focus();
                        jQuery('#username').addClass('error');
                        dialog("对不起，未找到您输入的账号");
                      } else {
                        jQuery('#password').focus();
                        jQuery('#password').addClass('error');
                        dialog("对不起，您输入的密码错误");
                      }
                        jQuery('.loginwrap').addClass('animate0 wobble').bind(anievent,function(){
                            jQuery(this).removeClass('animate0 wobble');
                        });
                        return;
                    }else{
                    	window.location.href = baseUrl + e.msg;
                    }
                }
            });
      });
  });
});
</script>
</body>
</html>