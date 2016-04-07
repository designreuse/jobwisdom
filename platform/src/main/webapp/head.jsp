<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String headPath = request.getContextPath();
String headBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+headPath + "/";
%>
<div id="head">
    <div class="logo">
        <a href="">
            <img src="<%=headBasePath %>app/img/head/logo.png" alt="lives"/>
        </a>
    </div>

    <div class="avatar">
        <div class="cashier-name">
            <span class="name">张建军</span>
            <span>收银员</span>
        </div>
        <img src="" alt="" class="cashier-img"/>
    </div>

    <div class="head-list">
        <ul>
            <li>
                <img src="<%=headBasePath %>app/img/head/message.png" alt=""/>
                <span class="sup">2</span>
                <div class="head-word">
                <span class="mgl10">系统消息</span>
                </div>
            </li>
            <li>
                <img src="<%=headBasePath %>app/img/head/help.png" alt=""/>
                <div class="head-word">
                <span class="mgl10">帮助中心</span>
                </div>
            </li>
        </ul>
    </div>

    <div class="content-top">
        <div class="weather">
            <img src="<%=headBasePath %>app/img/head/weather.png" alt="天气"/>
        </div>
        <div class="theme">
            <img src="<%=headBasePath %>app/img/head/theme.png" alt="主题"/>
            <span>主题</span>
        </div>
        <div class="common">
            <img src="<%=headBasePath %>app/img/head/lock.png" alt="锁" class="mgr10"/>
            <img src="<%=headBasePath %>app/img/head/setting.png" alt="设置" class="mgr10"/>
            <img src="<%=headBasePath %>app/img/head/flash.png" alt="刷新" class="mgr10"/>
        </div>
    </div>
</div>