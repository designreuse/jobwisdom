<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.zefun.web.entity.MemberMenu"%>
<%
String menuBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/";
String menuPicPath = "7xkv8r.com1.z0.glb.clouddn.com";
%>
<!-- 如果小于等于 IE9 提示升级 -->
<!-- Browser update -->
<!--[if lte IE 9]>
    <div id=browser-update class=browser-update role=alert>
      <img class=browser-update-img src="assets/img/update-browser/update-browser.jpg" alt="update-browser">
      <a href=http://browsehappy.com target=_blank>
        <img id=browser-update-button class=browser-update-button src="assets/img/update-browser/browser-update-button.png" alt="browser-update-button.png">
      </a>
    </div>
    <script>
      document.getElementById("browser-update").onclick=function(){
        document.getElementById("browser-update").style.display="none"
      };
      document.getElementById("browser-update-button").onclick=function(a){
        if(a.stopPropagation){
          a.stopPropagation()
        }else{if(window.event){
          window.event.cancelBubble=false
        }}};
    </script>
<![endif]-->
<!-- LEFT PANEL 开始-->
<div class="leftpanel">
    <div class="logo-wrap">
        <img src="<%=menuBasePath %>images/logo.png" alt=""/>
    </div>
    <div class="leftmenu">
    ${session_key_system_left_submenu }
    </div><!--leftmenu-->
</div><!--mainleft-->
<!--LEFT PANEL结束 -->