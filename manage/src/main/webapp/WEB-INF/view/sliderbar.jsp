<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
  <%
    String slider = request.getContextPath();
    String sliderbasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+slider + "/";
  %>

<div id="sidebar">
    <ul class="sidebar-ul">
    
    <!-- 权限控制菜单开始  -- 高国藩-->
        <c:forEach items="${ session_menu_list_key}" var="menu">
        	<c:choose>
        		<c:when test="${menu.subMenus != null && fn:length(menu.subMenus) > 0 }">
        			<li class="submenu">
        				<a href="<%=sliderbasePath%>${menu.url}"><i class="${menu.classname}"></i> <span>${menu.name }</span><span class="icon-sort-down icon-right"></span></a>
	        			<ul >
			        		<c:forEach items="${menu.subMenus }" var="m">
			        			<li class="${m.classname}"><a href="<%=sliderbasePath%>${m.url}">${m.name }</a></li>
			        		</c:forEach>
	        			</ul>
        			</li>
        		</c:when>
        		<c:otherwise>
        			<li><a href="<%=sliderbasePath%>${menu.url}"><i class="${menu.classname}"></i> <span>${menu.name }</span></a></li>
        		</c:otherwise>
        	</c:choose>
        </c:forEach>
    <!-- 权限控制菜单结束  高国藩-->
        
        <!-- 下面的将全部注释掉,换成又权限控制的页面展示   注释开始  斌哥-->
        
        <%-- <li ><a href="<%=sliderbasePath%>homepage/view/query"><i class="icon icon-home"></i> <span> 首页看板</span></a> </li>
        <li ><a href="<%=sliderbasePath%>cust/view/addcust"><i class="iconfont icon-xinkaihu"></i> <span> 开户注册</span></a> </li>

       
        <li class="submenu"> <a href="#"><i class="iconfont icon-bianmin"></i> <span>客户管理</span> <span class="icon-sort-down icon-right"></span></a>
            <ul>
                <li><a href="<%=sliderbasePath%>cust/view/query">客户查询</a></li>
                <li><a href="#">客户互动</a></li>
            </ul>
        </li>
        <li class="submenu"> <a href="#"><i class="iconfont icon-weifenxiaopcjiemianzhuanhuan"></i> <span>销售管理</span> <span class="icon-sort-down icon-right"></span></a>
            <ul>
                <li><a href="<%=sliderbasePath%>custclue/view/query">销售线索</a></li>
                <li><a href="<%=sliderbasePath%>project/view/salesproject">项目销售</a></li>
                <li><a href="<%=sliderbasePath%>hchart/view/hchart">销售统计</a></li>
                <li><a href="<%=sliderbasePath%>user/view/recharge">充值设置</a></li>
                <li><a href="<%=sliderbasePath%>salesman/view/salesman">业务员管理</a></li>
            </ul>
        </li>
        <li class="submenu"> <a href="#"><i class="iconfont icon-jiaoliupingtai"></i> <span>内部交流</span><span class="icon-sort-down icon-right"></span></a>
            <ul>
                <li><a href="#" >在线交流</a></li>
                <li><a href="#">资料下载</a></li>
                <li><a href="<%=sliderbasePath%>SystemAffiche/view/affiche/addAffiche">发布公告</a></li>
            </ul>
        </li>
        <li class="submenu"><a href="#"><i class="iconfont icon-xitongshezhi"></i> <span>系统设置</span><span class="icon-sort-down icon-right"></span></a>
            <ul>
            	<li><a href="<%=sliderbasePath%>project/view/query">项目设置</a></li>
                <li><a href="<%=sliderbasePath%>user/view/queryinfo">用户管理</a></li>
                <li><a href="<%=sliderbasePath%>user/view/add">新增用户</a></li>
                <li><a href="<%=sliderbasePath%>privilege/bootstraprolebuildprivilege">角色权限</a></li>
                <li><a href="<%=sliderbasePath%>privilege/sendBprivilege">人员授权</a></li>
                <li><a href="<%=sliderbasePath%>privilege/bootstrapSendToPrivilege">权限列表</a></li>
            </ul>
        </li>   --%>
        
        <!-- 上面的将全部注释掉,换成又权限控制的页面展示   注释结束   斌哥-->
         
         
        <!--<li><a href="interface.html"><i class="icon icon-pencil"></i> <span>Eelements</span></a></li>
        <li class="submenu active"> <a href="#"><i class="icon icon-file"></i> <span>Addons</span> <span class="label label-important">5</span></a>
            <ul>
                <li><a href="index2.html">Dashboard2</a></li>
                <li><a href="gallery.html">Gallery</a></li>
                <li><a href="calendar.html">Calendar</a></li>
                <li><a href="invoice.html">Invoice</a></li>
                <li><a href="chat.html">Chat option</a></li>
            </ul>
        </li>
        <li class="submenu"> <a href="#"><i class="icon icon-info-sign"></i> <span>Error</span> <span class="label label-important">4</span></a>
            <ul>
                <li><a href="error403.html">Error 403</a></li>
                <li><a href="error404.html">Error 404</a></li>
                <li><a href="error405.html">Error 405</a></li>
                <li><a href="error500.html">Error 500</a></li>
            </ul>
        </li>-->
      <!--   <li class="content"> <span>全国平台总用户数</span>
            <div class="progress progress-mini progress-danger active progress-striped">
                <div style="width: 10%;" class="bar"></div>
            </div>
            <span class="percent">10%</span>
            <div class="stat">总用户16000个，广东省1600个</div>
        </li>
        <li class="content"> <span>当月新增用户数</span>
            <div class="progress progress-mini active progress-striped">
                <div style="width: 20%;" class="bar"></div>
            </div>
            <span class="percent">20%</span>
            <div class="stat">当月新增用户160个，广东省32个</div>
        </li> -->
    </ul>
</div>

<script type="text/javascript">
var baseUrl = "<%=sliderbasePath%>";
$(document).ready(function(){
	$(".sidebar-ul").find("a").removeAttr("style");
	$(".sidebar-ul").find("a[href='" + window.location+ "']").css("color", "#fff").css("background-color", "#28b779").parents("ul").show();
});  
</script>