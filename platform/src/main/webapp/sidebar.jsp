<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String sidebarPath = request.getContextPath();
String sidebarBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+sidebarPath + "/";
%>
<div id="sidebar">
    <ul>
        <li class="board" class="waves-effect" >
            <a href="index.html" >
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
            </a>
        </li>
        <li  class="active waves-effect">
            <a href="index.html" >
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
                <span class="mgt8">智能收银</span>
            </a>
        </li>
        <li class="waves-effect">
            <a href="" >
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
                <span class="mgt8">智能客管</span>
            </a>
        </li>
        <li class="waves-effect">
            <a href="" >
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
                <span class="mgt8">智能店务</span>
            </a>
        </li>
        <li class="waves-effect">
            <a href="" >
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
                <span class="mgt8">智能营销</span>
            </a>
        </li>
        <li class="waves-effect">
            <a href="staff_setting.html">
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
                <span class="mgt8">员工管理</span>
            </a>
        </li>
        <li class="waves-effect">
            <a href="">
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
                <span class="mgt8">商品管理</span>
            </a>
        </li>
        <li class="waves-effect">
            <a href="">
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
                <span class="mgt8">财务管理</span>
            </a>
        </li>
        <li class="waves-effect">
            <a href="">
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
                <span class="mgt8">智能分析</span>
            </a>
        </li>
        <li class="waves-effect">
            <a href="" >
                <img src="<%=sidebarBasePath %>app/img/sidebar/siderbar1.png" alt=""/>
                <span class="mgt8">系统设置</span>
            </a>
        </li>
    </ul>
</div>