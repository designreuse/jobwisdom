<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.zefun.web.entity.MemberMenu"%>
<%
    String menuBasePath = request.getScheme() + "://"
            + request.getServerName() + request.getContextPath() + "/";
    String menuPicPath = "7xss26.com1.z0.glb.clouddn.com";
%>
<%@include file="/loading.jsp" %>
<%@include file="/abnormalLanding.jsp" %>
<%-- <%@include file="chat.jsp" %> --%>
<link rel="stylesheet" href="<%=basePath%>css/common.css" type="text/css" />
<div class="logo-wrap">
	<a href="index.html"> <img src="<%=basePath%>images/logo_by.png" alt=""></a>
</div>
<div class="leftmenu">
	<ul class="left_nav">
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_1.png">
			</div> <span>业务中心</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_2.png">
			</div> <span>会员管理</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_2.png">
			</div> <span>营销中心</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_3.png">
			</div> <span>统计分析</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_4.png">
			</div> <span>服务设置</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_4.png">
			</div> <span>商品管理</span>
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
			<a href="<%=menuBasePath%>selfcashier/view/list"><li><span><img src="<%=basePath%>images/left0.png">消费收银</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeManuallyOpenOrder"><li><span><img src="<%=basePath%>images/left1.png">无纸开单</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeStoreFlow"><li><span><img src="<%=basePath%>images/left4.png">收支记账</span></li></a>
			<a href="<%=menuBasePath%>daybook/view/index"><li><span><img src="<%=basePath%>images/left5.png">流水查询</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeShiftMahjong"><li><span><img src="<%=basePath%>images/left5.png">电子轮牌</span></li></a>
		</ul>
		<ul index="1">
		    <a href="<%=menuBasePath%>KeepAccounts/initializeOpenCard"><li><span><img src="<%=basePath%>images/left2.png">开卡充值</span></li></a>
			<a href="<%=menuBasePath%>appoint/view/list"><li><span><img src="<%=basePath%>images/left3.png">预约中心</span></li></a>
			<a href="<%=menuBasePath%>member/view/list"><li class=""><span><img src="<%=basePath%>images/left0.png">会员数据</span></li></a>
			<%-- <a href="<%=menuBasePath%>member/view/error/member/info"><li><span><img src="<%=basePath%>images/left1.png">异常会员数据</span></li></a> --%>
			<a href="<%=menuBasePath%>member/view/census/list"><li class=""><span><img src="<%=basePath%>images/left2.png">会员分组</span></li></a>
			<a href="<%=menuBasePath%>memberLevel/view/enterpriseMemberLevelList"><li class="active"><span><img src="<%=basePath%>images/left3.png">企业会员卡</span></li></a>
			<a href="<%=menuBasePath%>memberLevel/view/list"><li class="active"><span><img src="<%=basePath%>images/left3.png">门店会员卡</span></li></a>
		</ul>
		<ul index="2">
			<a href="<%=menuBasePath%>storeinfo/view/storeSetting"><li><span><img src="<%=basePath%>images/left4.png">微门店设置</span></li></a>
			<a href="<%=menuBasePath%>memberCenter/view/store/shop"><li><span><img src="<%=basePath%>images/left5.png">在线商城</span></li></a>
			<a href="<%=menuBasePath%>wechat/items/manage"><li><span><img src="<%=basePath%>images/left5.png">营销方案库</span></li></a>
			<a href="<%=menuBasePath%>view/coupons"><li><span><img src="<%=basePath%>images/left5.png">优惠券管理</span></li></a>
		</ul>
		<ul index="3">
			<a href="<%=menuBasePath%>summary/view/summary"><li class=""><span><img src="<%=basePath%>images/left0.png">营业汇总</span></li></a>
			<a href="<%=menuBasePath%>cashreceipts/view/cashreceipts"><li><span><img src="<%=basePath%>images/left1.png">现金收入</span></li></a>
			<a href="<%=menuBasePath%>cardsales/view/cardsales"><li class=""><span><img src="<%=basePath%>images/left2.png">卡项销售</span></li></a>
			<a href="<%=menuBasePath%>laborperformance/view/laborperformance"><li class="active"><span><img src="<%=basePath%>images/left3.png">劳动业绩</span></li></a>
			<a href="<%=menuBasePath%>packagesales/view/packagesales"><li><span><img src="<%=basePath%>images/left4.png">套餐销售</span></li></a>
			<a href="<%=menuBasePath%>commoditysales/view/commoditysales"><li><span><img src="<%=basePath%>images/left5.png">商品销售</span></li></a>
			<a href="<%=menuBasePath%>reconciliation/view/crossReconciliation"><li><span><img src="<%=basePath%>images/left5.png">跨店对账</span></li></a>
			<a href="<%=menuBasePath%>businessAnalysis/view/payroll"><li><span><img src="<%=basePath%>images/left5.png">工资单</span></li></a>
		</ul>
		<ul index="4">
			<a href="<%=menuBasePath%>project/view/categorys"><li class=""><span><img src="<%=basePath%>images/left0.png">新增系列</span></li></a>
			<a href="<%=menuBasePath%>project/view/projects"><li class=""><span><img src="<%=basePath%>images/left0.png">项目列表</span></li></a>
			<a href="<%=menuBasePath%>goodsInfo/view/goodsInfoList"><li class=""><span><img src="<%=basePath%>images/left2.png">商品列表</span></li></a>
			<a href="<%=menuBasePath%>goods/info/setting"><li class=""><span><img src="<%=basePath%>images/left2.png">商品上架</span></li></a>
			<a href="<%=menuBasePath%>comboInfo/view/comboInfoList"><li><span><img src="<%=basePath%>images/left1.png">套餐列表</span></li></a>
		</ul>
		<ul index="5">
			<a href="<%=menuBasePath%>goodsInfo/view/setting"><li class="active"><span><img src="<%=basePath%>images/left3.png">库存管理</span></li></a>
			<a href="<%=menuBasePath%>stock/view"><li class="active"><span><img src="<%=basePath%>images/left3.png">商品调遣</span></li></a>
			<a href="<%=menuBasePath%>view/storeAccount/suplier"><li><span><img src="<%=basePath%>images/left5.png">供应商管理</span></li></a>
		</ul>
		<ul index="6">
			<a href="<%=menuBasePath%>position/view/positioninfo"><li class=""><span><img src="<%=basePath%>images/left0.png">组织架构</span></li></a>
			<a href="<%=menuBasePath%>employee/view/employee"><li><span><img src="<%=basePath%>images/left1.png">员工资料</span></li></a>
			<a href="<%=menuBasePath%>storeManageRule/view/home"><li class=""><span><img src="<%=basePath%>images/left2.png">管理制度</span></li></a>
			<a href="<%=menuBasePath%>shift/view/shift"><li class="active"><span><img src="<%=basePath%>images/left3.png">排班设置</span></li></a>
			<a href="<%=menuBasePath%>objective/view/objective"><li><span><img src="<%=basePath%>images/left4.png">目标设置</span></li></a>
			<a href="<%=menuBasePath%>objectiverule/view/objectiverule"><li><span><img src="<%=basePath%>images/left5.png">目标考核</span></li></a>
			<a href="<%=menuBasePath%>attendance/view/attendance"><li><span><img src="<%=basePath%>images/left5.png">考勤记录</span></li></a>
		</ul>
		<ul index="7">
			<a href="<%=menuBasePath%>system/view/person"><li class=""><span><img src="<%=basePath%>images/left0.png">账户设置</span></li></a>
			<a href="<%=menuBasePath%>system/view/storeWechat"><li class="active"><span><img src="<%=basePath%>images/left3.png">微信设置</span></li></a>
			<a href="<%=menuBasePath%>storeinfo/view/showStoreList"><li class="active"><span><img src="<%=basePath%>images/left3.png">门店管理</span></li></a>
			<a href="<%=menuBasePath%>system/view/baseSetting"><li><span><img src="<%=basePath%>images/left1.png">基础设置</span></li></a>
			<a href="<%=menuBasePath%>enterprise/view/showEnterprise"><li><span><img src="<%=basePath%>images/left1.png">新增企业</span></li></a>
			<a href="<%=menuBasePath%>system/view/share"><li class=""><span><img src="<%=basePath%>images/left2.png">分享拓客</span></li></a>
			<a href="<%=menuBasePath%>system/view/storeUsage"><li class="active"><span><img src="<%=basePath%>images/left3.png">系统信息</span></li></a>
			<a href="<%=menuBasePath%>app/pay/qr"><li class="active"><span><img src="<%=basePath%>images/left3.png">门店充值</span></li></a>
		</ul>
	</div>
</div>
<!--leftmenu-->
</div>
<script>
var tempUrl = '<%=menuBasePath%>';
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
function choseMenu(url){
	jQuery(".left_nav_2").find("li").removeClass("active");
	jQuery(".left_nav_2").find("a[href='"+url+"']").parent("ul").show().siblings().hide();
	jQuery(".left_nav_2").find("a[href='"+url+"']").children("li").addClass("active");
	jQuery(".left_nav li").removeClass("active1");
	jQuery(".left_nav li").eq(jQuery(".left_nav_2").find("a[href='"+url+"']").parent("ul").attr("index")).addClass("active1");
	
}

jQuery(".leftmenu").mouseleave(function(){
	choseMenu(requestUrl);
});

choseMenu(requestUrl);

var tmp = "http://"+location.host+location.pathname;
if(tmp.indexOf("project")!=-1){
	tmp = "http://"+location.host+"/jobwisdom/project/view/projects";
	choseMenu(tmp);
}

function showLoginOut () {
	jQuery(".top_zzc").show();
}

function loginOut(isLoginAgain) {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "user/logout",
		data : null,
		success : function(data) {
			
		}
	});
	//window.location.href = "http://"+location.host+"/jobwisdom/user/logout";
	if (isLoginAgain!=null){
		alert("您的账号在其他终端登陆,若非本人操作,请及时修改密码!");
	}
}

function updatePassword () {
	var passUrl = "http://"+location.host+"/jobwisdom/system/view/person";
	window.location.href = passUrl;
}

function cantLoginOut () {
	jQuery(".top_zzc").hide();
}

jQuery(function(){
	   jQuery('.support_hover').hover(function(){
	      jQuery(this).find('.ask_responsive').show();
      },function(){
		    jQuery(this).find('.ask_responsive').hide()
		 })
  })
</script>