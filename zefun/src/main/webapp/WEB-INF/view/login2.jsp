<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>引客上门,留住顾客,促成交易</title>
<%-- <link rel="stylesheet" href="<%=basePath %>css/style.default.css" type="text/css" /> --%>
<link rel="stylesheet" href="<%=basePath %>css/iconfont.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>css/zhifang.css" type="text/css" />
<style>
    body{
        background-color:#2a3244;
        margin: 0;
        height: 100%;
        overflow: hidden;
    }
    /*LOGO标语*/
    .loginDL,.registerDL{
        height: 800px;
        width: 760px;
        margin: 100px auto;
    }
    .logo_by{
        margin:auto;
    }

    /*用户登录*/
    .denglu{
        margin-top: 50px;
    }
    .yonghuAnniu{
        position: relative;
        margin-left: 200px;
        width: 350px;
        height: 50px;

    }
    .yonghuAnniu span{
        color: #ffffff;
        position: absolute;
        left: 40px;
        top:8px;

    }
    .dengluAnniu img{
        position: absolute;
        top: 0px;
    }
    .DLclassA{
        margin-top: 20px;
        width: 705px;
        height: 220px;
        background-color: #ffffff;
        border-radius: 10px;
    }
    .DLclassA li{
        margin-bottom: 5px;
		line-height:50px;
    }
    .hei{
        width: 650px;
        height: 50px;
        margin: auto;
    }
    .poop input{
        width: 650px;
        height: 50px;
        background-color: #ffffff;
        border:none;
        border-bottom: 1px solid #dbdce2;
        line-height: 50px;
        font-size: 14px;
    }
    .popo input{
        width: 650px;
        height: 50px;
        background-color: #ffffff;
        border:none;
        line-height: 50px;
        font-size: 14px;
    }
    .shuaxin{
        margin-left: 620px;
        margin-top: -40px;
    }
    .yzm{
        margin-left: 530px;
        margin-top: -40px;
        font-family:myFont;
        font-size: 30px;
        letter-spacing:2px;
    }

    /*店面登录*/
    .mendianAnniu{
        position: relative;
        margin-top: -50px;
        margin-left: 400px;
        width: 350px;
        height: 50px;

    }
    .mendianAnniu span{
        color: #ffffff;
        position: absolute;
        left: 40px;
        top:8px;

    }
    .mendianAnniu img{
        position: absolute;
        top: 0px;
    }

    /*登录按钮*/
    .dengluAnniu{
        margin-top: 30px;

    }
    .dengluAnniu button{
        width:705px ;
        height:58px ;
        background-color: #e5e5e5;
        border-radius: 10px;
        border: none;
        cursor:pointer;
        font-size: 18px;
        color: #000;
    }

    /*忘记密码*/
    .wangjimima{
        margin-top:30px;
       
    }
    .wangjimima a{
        color: #cbcccf;
        font-size: 16px;
    }

    /*分割线*/
    .fengex{
        position:absolute;
        top:800px;
        left:5%;
    }

    /*客服咨询*/
    .kefuzixun{
        margin-top: 130px;
        width: 705px;
        height: 50px;
    }
    .kefuzixun span{
        float: left ;
        color: #cbcccf;
        font-size: 14px;
        margin-left: 250px;
        margin-top: 10px;
    }
    .zzA{float: left;margin-left: 25px;}
    .zzB{float: left;margin-left: 25px;}

    /*版权*/
    .banquan{
        margin-top: 10px;
        text-align: center;
    }
    .banquan span{
        color:#cbcccf ;
        font-size: 14px;
    }
    .list2{
        display:none;
    }

	.code{
	    float:left
		
		}
  
   .code input{
    width:200px;
	height:30px;
	border:1px solid white;
	font-size:14px
	
    }  
	.get_code{
	
	float:right;
	background:#f2f2f2;
	font-size:14px;
	cursor:pointer;
	width:110px;
	height:40px;
	text-align:center;
	line-height:40px;
	margin-top:8px;
	border:none;
	
	 }
	 
	input:-webkit-autofill {
		-webkit-box-shadow: 0 0 0px 1000px white inset;
		
	}
</style>

</head>

<body class="loginbody">

<%-- <div>
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
                <div class="wrong-msg">
                    <img src="<%=basePath %>img/wrong-msg.png" alt="" class="attention-img">
                    <div class="msg-desc">用户名或密码输入错误，请重新输入。
                        <br>
                        你也可以：<span class="reset-pass">重置登录密码</span>
                    </div>
                </div>
                <h1 class="logintitle">
                    登录智放系统
                </h1>
                <p class="animate4 bounceIn"><input type="text" id="username" name="username" placeholder="用户名" /></p>
                <p class="animate5 bounceIn"><input type="password" id="password" name="password" placeholder="密码" /></p>
                <p class="animate7 fadeIn"><a href="javascript:void(0);" class="fr" style="margin-top: -15px;margin-bottom: 10px;"><span class="iconfont icon-bangzhuzhongxin"></span> 忘记登录密码</a></p>
                <p class="animate6 bounceIn login-p">
                    <button class="btn btn-default btn-block login-btn loginButton">登&nbsp;&nbsp;录</button>
                </p>
                <p>
                    <button class="link-zhifang">还未加入智放，请立即了解 <img src="<%=basePath %>img/youjiantou.png" alt="" class="go-img"></button>
                </p>
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
</div> --%>

<!--分割线-->
<div class="fengex"><img src="<%=basePath %>images/fengex.png"> </div>

<div class="loginDL">
    <!--LOGO及标语-->
    <div class="logo_by">
        <img src="<%=basePath %>images/logo_by.png">
    </div>

    <!--登陆表单切换-->
    <div class="denglu">
        <div class="con">
            <div class="DLclassA listone list1">
                <ul>
                    <li class="hei">
                        <div class="zhanghuming poop"><input type="text"  id="loginStoreAccount" placeholder=" 企业代号" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color: #999"></div>
                    </li>
                    <li class="hei">
                        <div class="zhanghuming poop"><input type="text"  id="usernamepp" placeholder=" 工号" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color: #999"></div>
                    </li>
                    <li class="hei">
                        <div class="mima poop"><input type="password" id="passwordpp" placeholder="密码" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color: #999"></div>
                    </li>
                    <%-- <li class="hei">
                        <div class="yanzhengma popo"><input type="text" id ="verificationpp" placeholder=" 请输入验证码" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color: #999"></div>
                        <div class="shuaxin"><a href="#"><img src="<%=basePath %>images/shuaxin.png"></a></div>
                        <div name = "imgyzmpp" onclick="yzm(this)"  class="yzm" ><img src=""></div>
                    </li> --%>
                </ul>
            </div>
        </div>
    </div>

    <!--登陆按钮-->
    <div class="dengluAnniu">
        <button type="button" name = "loginButton">登&nbsp;录</button>
    </div>

    <!--忘记密码-->
    <div class="wangjimima">
        <a href="javascript:;" class="toregister">免费注册</a>
        <a href="#" style="position:relative;left:560px">忘记密码?</a>
    </div>

    <!--客服咨询-->
    <div class="kefuzixun">
        <span>使用客服咨询</span>
        <div class="zzA"><a href="#" title="QQ客服" ><img src="<%=basePath %>images/login_qq.png"></a></div>
        <div class="zzB"><a href="#" title="微信客服" ><img src="<%=basePath %>images/login_weixin.png"></a></div>
    </div>

    <!--版权-->
    <div class="banquan">
        <span>© 2016 jobwisdom.cn 版权所有 ICP证：粤ICP备15091901号&nbsp;&nbsp;&nbsp;&nbsp;</span>
    </div>

</div>

<!--注册-->
<div class="registerDL">
    <!--LOGO及标语-->
    <div class="logo_by">
        <img src="<%=basePath %>images/logo_by.png">
    </div>

    <!--登陆表单切换-->
    <div class="denglu">
            <!-- <div class="DLgeren  DLmendian titleone">
                <div class="yonghuAnniu"><a href="#"><img src="assets/images/login_geren.png" ><span>用户登录</span></a></div>
                <div class="mendianAnniu"><a href="#"><img src="assets/images/login_mendian.png" ><span>门店登录</span></a></div>
            </div> -->
        <div class="con">
            <div class="DLclassA listone list1">
                <ul>
	                <li class="hei">
                        <div class="zhanghuming poop"><input type="text"  name="storeName" placeholder=" 企业名称" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color: #999"></div>
                    </li>
                    <li class="hei">
                        <div class="zhanghuming poop"><input type="text"  name="storeAccount" placeholder=" 企业代号" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color: #999"></div>
                    </li>
                    <li class="hei">
                        <div class="mima poop"><input type="text" name="phoneNumber" placeholder=" 手机号" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color: #999"></div>
                    </li>
		            <li class="hei">
                        <div class="code"><input type="text" name="phoneYzm" placeholder=" 请输入6位验证码" onFocus="if(value==defaultValue){value='';this.style.color='#000'}" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" style="color: #999"></div>
                        <button class="get_code">获取验证码</button> 
		            </li>
                </ul>
            </div>
        </div>
    </div>

    <!--登陆按钮-->
    <div class="dengluAnniu">
        <button type="button" name = "registerButton" >注&nbsp;册</button>
    </div>

    <!--忘记密码-->
    <div class="wangjimima">
  <a href="javascript:;" class="tologin">返回登录</a>
        <a href="#" style="position:relative;left:560px">服务协议</a>
    </div>

    <!--客服咨询-->
    <div class="kefuzixun">
        <span>使用客服咨询</span>
        <div class="zzA"><a href="#" title="QQ客服" ><img src="<%=basePath %>images/login_qq.png"></a></div>
        <div class="zzB"><a href="#" title="微信客服" ><img src="<%=basePath %>images/login_weixin.png"></a></div>
    </div>

    <!--版权-->
    <div class="banquan">
        <span>© 2016 jobwisdom.cn 版权所有 ICP证：粤ICP备15091901号&nbsp;&nbsp;&nbsp;&nbsp;</span>
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
<%-- <script src="<%=basePath %>js/common/loginA.js"></script>
 --%><script src="<%=basePath %>js/common/loginB.js"></script>
<script type="text/javascript">

var pageUrlTop = "http://7xt6g0.com1.z0.glb.clouddn.com/";
var pageValue = "";
jQuery.noConflict();

jQuery(document).ready(function(){
  
	jQuery('.get_code').click(function(){
	       var s=120;
	       jQuery('.get_code').text('120')
	       
		    _res= setInterval(function(){
		    	jQuery(".get_code").attr("disabled", true);//设置disabled属性
			    s-=1;
			    jQuery('.get_code').text(s);
				
				if(s<=0){
					jQuery('.get_code').text('重新获取验证码');
				   jQuery(".get_code").removeAttr("disabled"); //移除disabled属性
				  clearInterval(_res);
				}
				
			   },1000)
	   
    });	
	
	jQuery('.toregister').click(function(){
		jQuery('.loginDL').hide();
		jQuery('.registerDL').show();
	});
	
	jQuery('.tologin').click(function(){
		jQuery('.loginDL').show();
		jQuery('.registerDL').hide();
	});
	
  jQuery.noConflict();
  
  yzm(jQuery("div[name='imgyzmpp']"));
  
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
            jQuery('button[name="loginButton"]').click();
          }
      });
      
      jQuery('button[name="loginButton"]').click(function(){
    	var loginStoreAccount = jQuery("#loginStoreAccount").val();
    	var username = jQuery("#usernamepp").val();
    	var usernameObj = jQuery("#usernamepp");
        var password = jQuery("#passwordpp").val();
        var passwordObj = jQuery("#passwordpp");
        var verification = jQuery("#verificationpp").val();
        var verificationObj = jQuery("#verificationpp");
        
        if(username == '' || password == '' || loginStoreAccount == '') {
	        	if(loginStoreAccount == '') {
	        		jQuery("#loginStoreAccount").focus();
	        		jQuery("#loginStoreAccount").addClass('error');
	                dialog("对不起，未找到您输入的账号");
	            } else {
	            	jQuery("#loginStoreAccount").removeClass('error');
	            }
                if(username == '') {
                	usernameObj.focus();
                	usernameObj.addClass('error');
                    dialog("对不起，未找到您输入的账号");
                } else {
                	usernameObj.removeClass('error');
                }
                if(password == '') {
                	passwordObj.focus();
                	passwordObj.addClass('error'); 
                    dialog("对不起，您输入的密码错误");
                }else {
                	passwordObj.removeClass('error');
                }
                
                /* if (pageValue != verification) {
                	verificationObj.focus();
                	verificationObj.addClass('error');
                	dialog("对不起，您输入的验证码有误");
                } */
                /* else {
                	verificationObj.removeClass('error');
                } */
                return;
        }
        
        password = CryptoJS.MD5(CryptoJS.MD5(password).toString().toUpperCase()).toString().toUpperCase();
        
        jQuery.ajax({
            type : "post",
            url : baseUrl + "user/login",
            data : "storeAccount=" + loginStoreAccount +"&username=" + username + "&password=" + password,
            dataType : "json",
            success : function(e){
                if (e.code != 0) {
                  if (e.code == 9001) {
                    jQuery('#loginStoreAccount').focus();
                    jQuery('#loginStoreAccount').addClass('error');
                    dialog(e.msg);
                  } else {
                    jQuery('#password').focus();
                    jQuery('#password').addClass('error');
                    dialog("对不起，您输入的密码错误");
                  }
                  return;
                }else{
                	window.location.href = baseUrl + e.msg;
                }
            }
        });
      });
  });
});

jQuery("button[name='registerButton']").click(function(){
	
	var storeName = jQuery("input[name='storeName']").val();
	var storeAccount = jQuery("input[name='storeAccount']").val();
	var phoneNumber = jQuery("input[name='phoneNumber']").val();
	var phoneYzm = jQuery("input[name='phoneYzm']").val();
	
	if (storeName == '' || storeAccount == '' || phoneNumber == '' || phoneYzm == '') {
		if(storeName == '') {
			jQuery("input[name='storeName']").focus();
			jQuery("input[name='storeName']").addClass('error');
            dialog("对不起，企业名称不能为空");
        } else {
        	jQuery("input[name='storeName']").removeClass('error');
        }
        if(storeAccount == '') {
        	jQuery("input[name='storeAccount']").focus();
        	jQuery("input[name='storeAccount']").addClass('error'); 
            dialog("对不起，企业代号不能为空");
        }else {
        	jQuery("input[name='storeAccount']").removeClass('error');
        }
        
        if (phoneNumber == '') {
        	jQuery("input[name='phoneNumber']").focus();
        	jQuery("input[name='phoneNumber']").addClass('error');
        	dialog("对不起，手机号码不能为空");
        }
        else {
        	jQuery("input[name='phoneNumber']").removeClass('error');
        }
        
        if (phoneYzm == '') {
        	jQuery("input[name='phoneYzm']").focus();
        	jQuery("input[name='phoneYzm']").addClass('error');
        	dialog("对不起，手机号码不能为空");
        }
        else {
        	jQuery("input[name='phoneYzm']").removeClass('error');
        }
        return;
	}
	
	jQuery.ajax({
        type : "post",
        url : baseUrl + "app/registerStoreFree",
        data : "storeName=" + storeName + "&storeAccount=" + storeAccount+ "&phoneNumber=" + phoneNumber  + "&phoneYzm=" + phoneYzm,
        dataType : "json",
        success : function(e){
            if (e.code != 0) {
              if (e.code == 9901) {
            	jQuery("input[name='storeAccount']").focus();
            	jQuery("input[name='storeAccount']").addClass('error');
                dialog("对不起，企业代号已存在");
              } 
              return;
            }else{
            	window.location.href = baseUrl + e.msg;
            }
        }
    });
});

function yzm(obj){
	jQuery.ajax({
        type : "post",
        url : baseUrl + "app/getYzmPage",
        dataType : "json",
        success : function(e){
            if (e.code != 0) {
                dialog("验证码刷新失败");
                return;
            }else{
            	var data = e.msg;
            	var pageUrl  = pageUrlTop + data.pageUrl;
            	pageValue = data.pageValue;
            	jQuery(obj).find("img").attr("src", pageUrl);
            }
        }
    });
}
</script>
</body>
</html>