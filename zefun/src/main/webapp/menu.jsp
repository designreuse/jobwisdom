<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.zefun.web.entity.MemberMenu"%>
<%
    String menuBasePath = request.getScheme() + "://"
            + request.getServerName() + request.getContextPath() + "/";
    String menuPicPath = "7xkv8r.com1.z0.glb.clouddn.com";
%>
<%@include file="/loading.jsp" %>
<div class="logo-wrap">
	<a href="index.html"> <img src="<%=basePath%>images/logo_by.png" alt=""></a>
</div>
<div class="leftmenu">
	<ul class="left_nav">
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_1.png">
			</div> <span>智能前台</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_2.png">
			</div> <span>会员营销</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_3.png">
			</div> <span>营业报表</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_4.png">
			</div> <span>服务设置</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_5.png">
			</div> <span>员工设置</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_6.png">
			</div> <span>系统设置</span>
		</li>
	</ul>

	<div class="left_nav_2" style="height: 840px;">
		<ul index="0">
			<a href="<%=menuBasePath%>selfcashier/view/list"><li><span><img src="<%=basePath%>images/left0.png">自助收银</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeManuallyOpenOrder"><li><span><img src="<%=basePath%>images/left1.png">手工开单</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeOpenCard"><li><span><img src="<%=basePath%>images/left2.png">开卡充值</span></li></a>
			<a href="<%=menuBasePath%>appoint/view/list"><li><span><img src="<%=basePath%>images/left3.png">预约中心</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeStoreFlow"><li><span><img src="<%=basePath%>images/left4.png">开支记账</span></li></a>
			<a href="<%=menuBasePath%>daybook/view/index"><li><span><img src="<%=basePath%>images/left5.png">流水查询</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeShiftMahjong"><li><span><img src="<%=basePath%>images/left5.png">员工轮牌</span></li></a>
		</ul>
		<ul index="1">
			<a href="<%=menuBasePath%>member/view/list"><li class=""><span><img src="<%=basePath%>images/left0.png">会员数据</span></li></a>
			<a href="<%=menuBasePath%>member/view/error/member/info"><li><span><img src="<%=basePath%>images/left1.png">异常会员数据</span></li></a>
			<a href="<%=menuBasePath%>member/view/census/list"><li class=""><span><img src="<%=basePath%>images/left2.png">会员分组</span></li></a>
			<a href="<%=menuBasePath%>memberLevel/view/list"><li class="active"><span><img src="<%=basePath%>images/left3.png">会员卡查询</span></li></a>
			<a href="<%=menuBasePath%>storeinfo/view/storeSetting"><li><span><img src="<%=basePath%>images/left4.png">微门店设置</span></li></a>
			<a href="<%=menuBasePath%>wechat/items/manage"><li><span><img src="<%=basePath%>images/left5.png">营销方案库</span></li></a>
			<a href="<%=menuBasePath%>view/coupons"><li><span><img src="<%=basePath%>images/left5.png">优惠券管理</span></li></a>
		</ul>
	</div>
</div>
<!--leftmenu-->
</div>
<script>
var requestUrl = document.URL;
jQuery(function(){
    jQuery('.left_nav li').click(function(){
		jQuery(this).addClass('active1').siblings().removeClass('active1');
		jQuery('.left_nav_2 ul').eq(jQuery(this).index()).show().siblings().hide();
	});
})
jQuery(function(){
	jQuery('.left_nav_2 li').hover(
	function(){
		jQuery(".left_nav_2").find("li").removeClass('active');
		jQuery(this).addClass('active');
	},
	function (){
		jQuery(".left_nav_2").find("li").removeClass("active");
		jQuery(".left_nav_2").find("a[href='"+requestUrl+"']").children("li").addClass("active");

	})
})
jQuery(".left_nav_2").find("li").removeClass("active");
jQuery(".left_nav_2").find("a[href='"+requestUrl+"']").parent("ul").show().siblings().hide();
jQuery(".left_nav_2").find("a[href='"+requestUrl+"']").children("li").addClass("active");
jQuery(".left_nav li").removeClass("active1");
jQuery(".left_nav li").eq(jQuery(".left_nav_2").find("a[href='"+requestUrl+"']").parent("ul").attr("index")).addClass("active1");

</script>