<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/base.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>引客上门,留住顾客,促成交易</title>
<style>
.login_content{width:1000px;margin:0 auto}
.login_mark{color:#808080;font-size:13px;text-align:center}
em,i{font-style:normal}
html {
  height: 100%;
  background-image: -webkit-radial-gradient(ellipse farthest-corner at center top, #000d4d 0%, #000105 100%);
  background-image: radial-gradient(ellipse farthest-corner at center top, #000d4d 0%, #000105 100%);
  cursor: move;
}

body {
  width: 100%;
  margin: 0;
  overflow: hidden;
}

/* Demo Buttons Style */
.codrops-demos {
	font-size: 0.8em;
	text-align:center;
	position:absolute;
	z-index:99;
	width:96%;
}

.codrops-demos a {
	display: inline-block;
	margin: 0.35em 0.1em;
	padding: 0.5em 1.2em;
	outline: none;
	text-decoration: none;
	text-transform: uppercase;
	letter-spacing: 1px;
	font-weight: 700;
	border-radius: 2px;
	font-size: 110%;
	border: 2px solid transparent;
	color:#fff;
}

.codrops-demos a:hover,
.codrops-demos a.current-demo {
	border-color: #383a3c;
}
.login,.register {height:550px;width:330px;background-color:rgba(99,93,111,0.4);position:absolute;top:8%;left:40%;}
.login_header,.register_header{position:relative;top:15px}
.login_header .login_{display:inline-block;margin-left:20px;color:white;cursor:pointer}
.login_header .register_{display:inline-block;margin-left:222px;color:#808080;cursor:pointer}

.login_header .register_ em{display:inline-block;margin-left:5px}
.login_logo{text-align:center;margin-top:40px}
.login_imformation{width:240px;margin:0 auto;}
.login_imformation p{position:relative;width:240px;height:34px}
.login_imformation input[type=text]{background-color:rgba(161,123,151,0.1);border:none;height:34px;width:195px;border-radius:4px;position:absolute;padding-left:4px;line-height:34px；color:#eeeff1;font-size:14px;padding-left:40px;color:#cacaca}
.login_imformation i{display:inline-block;position:absolute;left:15px;top:10px}
.login_imformation .code_pic,.login_imformation .get_code{width:72px;background-color:rgba(161,123,151,0.1);display:inline-block;height:36px;margin-left:162px;border-radius:4px;overflow:hidden}
.login_imformation .code_pic img{background-color:rgba(161,123,151,0.1);width:72px;height:36px;overflow:hidden;border-radius:4px;}
.login_imformation input[type=checkbox]{height:15px;width:15px;}
.remember_password{    width: 240px;
    height: 34px;font-size:14px;}
.remember_password input{margin-right:10px;vertical-align:middle}
.remember_password_{color:#5258d8}
.register_1,.login_1{width:240px;height:35px;line-height:35px;text-align:center;background-color:rgba(181,87,55,0.8);;border:none;border-radius:4px;color:#edd5cd;font-weight:bold;font-size:14px;cursor:pointer}
.login_bottom{width:170px;margin-top:25px;margin-left:52px}
.login_bottom span{display:inline-block;margin-right:20px;}

.login_imformation .get_code{border:none;color:#cacaca;cursor:pointer}
.register_header .login_{display:inline-block;margin-left:20px;color:#808080;cursor:pointer}
.register_header .register_ em{display:inline-block;margin-left:5px}
.register_header .register_{display:inline-block;margin-left:222px;color:white;cursor:pointer}

.register form span{color:#808080;font-size:10px;display:inline-block;height:22px;position:relative;top:6px}
.register p{height:12px!important}
.register .login_logo{margin-top:0!important}

/* input:-webkit-autofill {
		-webkit-box-shadow: 0 0 0px 1000px white inset;
		
	} */
	
	.login_imformation input[type=password] {
    background-color: rgba(161,123,151,0.1);
    border: none;
    height: 34px;
    width: 195px;
    border-radius: 4px;
    position: absolute;
    padding-left: 4px;
    line-height: 34px；color:#eeeff1;
    font-size: 14px;
    padding-left: 40px;
    color: #cacaca;
}

</style>

</head>

<body class="loginbody">

 <div class="login_content">
    <!--登录-->
    <div class="login" >
	   <div class="login_header">
	      <span class="login_">登陆</span>
	      <span class="register_">注册</span>
	   </div>
	   <p class="login_logo"><img src="<%=basePath %>images/login_logo.png"></p>
	   
	   <form class="login_imformation">
	       <p><input type="text" id="loginStoreAccount" placeholder="企业代号"><i><img src="<%=basePath %>images/business_number.png"></i></p>
	   
	      <p><input type="text" id="usernamepp" placeholder="工号"><i><img src="<%=basePath %>images/emploee_number.png"></i></p>
		  
		   <p><input type="password" id="passwordpp" placeholder="密码"><i><img src="<%=basePath %>images/password.png"></i></p>
		   
		    <p><input type="text" id ="verificationpp" placeholder="请输入6位数字" style="padding-left:10px;width:136px"><span class="code_pic"><img alt="" src=""></span></p>
		   
		   
		   <div class="remember_password"><input type="checkbox" id = "rmbUser"><span class="remember_password_">记住密码</span><a href="javascript:;" style="color:#5258d8;display:inline-block;margin-left:95px;text-decoration:underline">忘记密码</a></div>
		   <input type="button" name = "loginButton" value="登陆" class="login_1"/>
		   <div class="login_bottom">
		     <span><img src="<%=basePath%>images/login_bottom1.png"></span>
		     <span><img src="<%=basePath%>images/login_bottom2.png"></span>
			  <span><img src="<%=basePath%>images/login_bottom3.png"></span>
		   </div>
	   </form>
	    <p class="login_mark">© 2016 版权所有 ICP证：粤ICP备16038559号-1    </p>
	</div>
	
       <!--注册-->
    <div class="register" style="opacity:0;z-index:-1;margin-left:80px">
	   <div class="register_header">
	      <span class="login_">登陆</span>
	      <span class="register_">注册</span>
	   </div>
	   <p class="login_logo"></p>
	   
	   <form class="login_imformation" style="margin-top:60px">
	       <p><input type="text"  name="storeName" placeholder="企业名称"><i><img src="<%=basePath %>images/register1.png"></i>
		   
		   </p>
	        <span></span>
	      <p><input type="text" name="storeAccount" placeholder="企业代号"><i><img src="<%=basePath %>images/business_number.png"></i></p>
		  <span></span>
		   <p><input type="text" name="phoneNumber" placeholder="手机号"><i><img src="<%=basePath %>images/phone.png"></i></p>
		   <span></span>
		    <p><input type="text" placeholder="请输入6位数字" style="padding-left:10px;width:136px"><button class="get_code">获取验证码</button></p>
		   <span></span>
		   
		   <div class="remember_password"></div>
		   <input type="button" name = "registerButton" value="确认注册" class="register_1">
		   <div class="login_bottom">
		     <span><img src="<%=basePath %>images/login_bottom1.png"></span>
		     <span><img src="<%=basePath %>images/login_bottom2.png"></span>
			  <span><img src="<%=basePath %>images/login_bottom3.png"></span>
		   </div>
	   </form>
	       <p class="login_mark">© 2016 版权所有 ICP证：粤ICP备16038559号-1    </p>
	</div>

   
   </div>
  
	<canvas id="canv"></canvas>	

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
<script type="text/javascript" src="<%=basePath %>js/common/jquery.cookie.js"></script>
<script type="text/javascript" src="<%=basePath %>js/base/pc.js"></script>
<script type="text/javascript">
//注册登陆切换

jQuery(function(){
//点击注册
   jQuery('.login .register_').click(function(){
      jQuery('.login').animate({
	     marginLeft:'80px',
		opacity:0
     },1000,function(){
	    jQuery('.register').animate({
		 marginLeft:'0px',
		opacity:1
		},1000);
		jQuery('.register').attr('style','z-index:10')
     });
   });
	  //点击登陆
	  jQuery('.register .login_').click(function(){
	      jQuery('.register').animate({
	       marginLeft:'80px',
		   opacity:0
          },1000,function(){
		   jQuery('.login').animate({
		   marginLeft:'0px',
		   opacity:1
		},1000);
		jQuery('.register').attr('style','z-index:-1;opacity:0')
		  });
	  });	
 });
 
var pageUrlTop = "http://7xt6g0.com1.z0.glb.clouddn.com/";
var pageValue = "";

jQuery(document).ready(function(){
	  
  yzm();
  
  jQuery("#username").focus();
  
});

jQuery('input[name="loginButton"]').click(function(){
	
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

jQuery("input[name='registerButton']").click(function(){
	
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
            	jQuery("div[name='imgyzmpp']").find("img").attr("src", pageUrl);
            }
        }
    });
}

$(document).keydown(function (event) {  
	var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==13){ // enter 键
    	jQuery('input[name="loginButton"]').click();
   }
});

/* $(document).ready(function() {
    if ($.cookie("rmbUser") == "true") {
        $("#rmbUser").attr("checked", true);
        $("#loginStoreAccount").val($.cookie("loginStoreAccount"));
        $("#usernamepp").val($.cookie("usernamepp"));
        $("#passwordpp").val($.cookie("passwordpp"));
    }
});

function saveUserInfo() {
    if ($("#rmbUser").attr("checked") == "checked") {
        var loginStoreAccount = $("#loginStoreAccount").val();
        var usernamepp = $("#usernamepp").val();
        var passwordpp = $("#passwordpp").val();
        $.cookie("rmbUser", "true", { expires: 7 }); // 存储一个带7天期限的 cookie
        $.cookie("loginStoreAccount", loginStoreAccount, { expires: 7 }); // 存储一个带7天期限的 cookie
        $.cookie("usernamepp", usernamepp, { expires: 7 }); // 存储一个带7天期限的 cookie
        $.cookie("passwordpp", passwordpp, { expires: 7 }); // 存储一个带7天期限的 cookie
    }
    else {
        $.cookie("rmbUser", "false", { expires: -1 });        // 删除 cookie
        $.cookie("loginStoreAccount", '', { expires: -1 });
        $.cookie("usernamepp", '', { expires: -1 });
        $.cookie("passwordpp", '', { expires: -1 });
    }
} */

</script>
<script type="text/javascript">
var num = 200;
var w = window.innerWidth;
var h = window.innerHeight;
var max = 100;
var _x = 0;
var _y = 0;
var _z = 150;
var dtr = function(d) {
  return d * Math.PI / 180;
};

var rnd = function() {
  return Math.sin(Math.floor(Math.random() * 360) * Math.PI / 180);
};
var dist = function(p1, p2, p3) {
  return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2) + Math.pow(p2.z - p1.z, 2));
};

var cam = {
  obj: {
    x: _x,
    y: _y,
    z: _z
  },
  dest: {
    x: 0,
    y: 0,
    z: 1
  },
  dist: {
    x: 0,
    y: 0,
    z: 200
  },
  ang: {
    cplane: 0,
    splane: 0,
    ctheta: 0,
    stheta: 0
  },
  zoom: 1,
  disp: {
    x: w / 2,
    y: h / 2,
    z: 0
  },
  upd: function() {
    cam.dist.x = cam.dest.x - cam.obj.x;
    cam.dist.y = cam.dest.y - cam.obj.y;
    cam.dist.z = cam.dest.z - cam.obj.z;
    cam.ang.cplane = -cam.dist.z / Math.sqrt(cam.dist.x * cam.dist.x + cam.dist.z * cam.dist.z);
    cam.ang.splane = cam.dist.x / Math.sqrt(cam.dist.x * cam.dist.x + cam.dist.z * cam.dist.z);
    cam.ang.ctheta = Math.sqrt(cam.dist.x * cam.dist.x + cam.dist.z * cam.dist.z) / Math.sqrt(cam.dist.x * cam.dist.x + cam.dist.y * cam.dist.y + cam.dist.z * cam.dist.z);
    cam.ang.stheta = -cam.dist.y / Math.sqrt(cam.dist.x * cam.dist.x + cam.dist.y * cam.dist.y + cam.dist.z * cam.dist.z);
  }
};

var trans = {
  parts: {
    sz: function(p, sz) {
      return {
        x: p.x * sz.x,
        y: p.y * sz.y,
        z: p.z * sz.z
      };
    },
    rot: {
      x: function(p, rot) {
        return {
          x: p.x,
          y: p.y * Math.cos(dtr(rot.x)) - p.z * Math.sin(dtr(rot.x)),
          z: p.y * Math.sin(dtr(rot.x)) + p.z * Math.cos(dtr(rot.x))
        };
      },
      y: function(p, rot) {
        return {
          x: p.x * Math.cos(dtr(rot.y)) + p.z * Math.sin(dtr(rot.y)),
          y: p.y,
          z: -p.x * Math.sin(dtr(rot.y)) + p.z * Math.cos(dtr(rot.y))
        };
      },
      z: function(p, rot) {
        return {
          x: p.x * Math.cos(dtr(rot.z)) - p.y * Math.sin(dtr(rot.z)),
          y: p.x * Math.sin(dtr(rot.z)) + p.y * Math.cos(dtr(rot.z)),
          z: p.z
        };
      }
    },
    pos: function(p, pos) {
      return {
        x: p.x + pos.x,
        y: p.y + pos.y,
        z: p.z + pos.z
      };
    }
  },
  pov: {
    plane: function(p) {
      return {
        x: p.x * cam.ang.cplane + p.z * cam.ang.splane,
        y: p.y,
        z: p.x * -cam.ang.splane + p.z * cam.ang.cplane
      };
    },
    theta: function(p) {
      return {
        x: p.x,
        y: p.y * cam.ang.ctheta - p.z * cam.ang.stheta,
        z: p.y * cam.ang.stheta + p.z * cam.ang.ctheta
      };
    },
    set: function(p) {
      return {
        x: p.x - cam.obj.x,
        y: p.y - cam.obj.y,
        z: p.z - cam.obj.z
      };
    }
  },
  persp: function(p) {
    return {
      x: p.x * cam.dist.z / p.z * cam.zoom,
      y: p.y * cam.dist.z / p.z * cam.zoom,
      z: p.z * cam.zoom,
      p: cam.dist.z / p.z
    };
  },
  disp: function(p, disp) {
    return {
      x: p.x + disp.x,
      y: -p.y + disp.y,
      z: p.z + disp.z,
      p: p.p
    };
  },
  steps: function(_obj_, sz, rot, pos, disp) {
    var _args = trans.parts.sz(_obj_, sz);
    _args = trans.parts.rot.x(_args, rot);
    _args = trans.parts.rot.y(_args, rot);
    _args = trans.parts.rot.z(_args, rot);
    _args = trans.parts.pos(_args, pos);
    _args = trans.pov.plane(_args);
    _args = trans.pov.theta(_args);
    _args = trans.pov.set(_args);
    _args = trans.persp(_args);
    _args = trans.disp(_args, disp);
    return _args;
  }
};

(function() {
  "use strict";
  var threeD = function(param) {
    this.transIn = {};
    this.transOut = {};
    this.transIn.vtx = (param.vtx);
    this.transIn.sz = (param.sz);
    this.transIn.rot = (param.rot);
    this.transIn.pos = (param.pos);
  };

  threeD.prototype.vupd = function() {
    this.transOut = trans.steps(

      this.transIn.vtx,
      this.transIn.sz,
      this.transIn.rot,
      this.transIn.pos,
      cam.disp
    );
  };

  var Build = function() {
    this.vel = 0.04;
    this.lim = 360;
    this.diff = 200;
    this.initPos = 100;
    this.toX = _x;
    this.toY = _y;
    this.go();
  };

  Build.prototype.go = function() {
    this.canvas = document.getElementById("canv");
    this.canvas.width = window.innerWidth;
    this.canvas.height = window.innerHeight;
    this.$ = canv.getContext("2d");
    this.$.globalCompositeOperation = 'source-over';
    this.varr = [];
    this.dist = [];
    this.calc = [];

    for (var i = 0, len = num; i < len; i++) {
      this.add();
    }

    this.rotObj = {
      x: 0,
      y: 0,
      z: 0
    };
    this.objSz = {
      x: w / 5,
      y: h / 5,
      z: w / 5
    };
  };

  Build.prototype.add = function() {
    this.varr.push(new threeD({
      vtx: {
        x: rnd(),
        y: rnd(),
        z: rnd()
      },
      sz: {
        x: 0,
        y: 0,
        z: 0
      },
      rot: {
        x: 20,
        y: -20,
        z: 0
      },
      pos: {
        x: this.diff * Math.sin(360 * Math.random() * Math.PI / 180),
        y: this.diff * Math.sin(360 * Math.random() * Math.PI / 180),
        z: this.diff * Math.sin(360 * Math.random() * Math.PI / 180)
      }
    }));
    this.calc.push({
      x: 360 * Math.random(),
      y: 360 * Math.random(),
      z: 360 * Math.random()
    });
  };

  Build.prototype.upd = function() {
    cam.obj.x += (this.toX - cam.obj.x) * 0.05;
    cam.obj.y += (this.toY - cam.obj.y) * 0.05;
  };

  Build.prototype.draw = function() {
    this.$.clearRect(0, 0, this.canvas.width, this.canvas.height);
    cam.upd();
    this.rotObj.x += 0.1;
    this.rotObj.y += 0.1;
    this.rotObj.z += 0.1;

    for (var i = 0; i < this.varr.length; i++) {
      for (var val in this.calc[i]) {
        if (this.calc[i].hasOwnProperty(val)) {
          this.calc[i][val] += this.vel;
          if (this.calc[i][val] > this.lim) this.calc[i][val] = 0;
        }
      }

      this.varr[i].transIn.pos = {
        x: this.diff * Math.cos(this.calc[i].x * Math.PI / 180),
        y: this.diff * Math.sin(this.calc[i].y * Math.PI / 180),
        z: this.diff * Math.sin(this.calc[i].z * Math.PI / 180)
      };
      this.varr[i].transIn.rot = this.rotObj;
      this.varr[i].transIn.sz = this.objSz;
      this.varr[i].vupd();
      if (this.varr[i].transOut.p < 0) continue;
      var g = this.$.createRadialGradient(this.varr[i].transOut.x, this.varr[i].transOut.y, this.varr[i].transOut.p, this.varr[i].transOut.x, this.varr[i].transOut.y, this.varr[i].transOut.p * 2);
      this.$.globalCompositeOperation = 'lighter';
      g.addColorStop(0, 'hsla(255, 255%, 255%, 1)');
      g.addColorStop(.5, 'hsla(' + (i + 2) + ',85%, 40%,1)');
      g.addColorStop(1, 'hsla(' + (i) + ',85%, 40%,.5)');
      this.$.fillStyle = g;
      this.$.beginPath();
      this.$.arc(this.varr[i].transOut.x, this.varr[i].transOut.y, this.varr[i].transOut.p * 2, 0, Math.PI * 2, false);
      this.$.fill();
      this.$.closePath();
    }
  };
  Build.prototype.anim = function() {
    window.requestAnimationFrame = (function() {
      return window.requestAnimationFrame ||
        function(callback, element) {
          window.setTimeout(callback, 1000 / 60);
        };
    })();
    var anim = function() {
      this.upd();
      this.draw();
      window.requestAnimationFrame(anim);
    }.bind(this);
    window.requestAnimationFrame(anim);
  };

  Build.prototype.run = function() {
    this.anim();

    window.addEventListener('mousemove', function(e) {
      this.toX = (e.clientX - this.canvas.width / 2) * -0.8;
      this.toY = (e.clientY - this.canvas.height / 2) * 0.8;
    }.bind(this));
    window.addEventListener('touchmove', function(e) {
      e.preventDefault();
      this.toX = (e.touches[0].clientX - this.canvas.width / 2) * -0.8;
      this.toY = (e.touches[0].clientY - this.canvas.height / 2) * 0.8;
    }.bind(this));
    window.addEventListener('mousedown', function(e) {
      for (var i = 0; i < 100; i++) {
        this.add();
      }
    }.bind(this));
    window.addEventListener('touchstart', function(e) {
      e.preventDefault();
      for (var i = 0; i < 100; i++) {
        this.add();
      }
    }.bind(this));
  };
  var app = new Build();
  app.run();
})();
window.addEventListener('resize', function() {
  canvas.width = w = window.innerWidth;
  canvas.height = h = window.innerHeight;
}, false);

</script>
</body>
</html>