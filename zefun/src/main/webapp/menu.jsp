<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.zefun.web.entity.MemberMenu"%>
<%
    String menuBasePath = request.getScheme() + "://"
            + request.getServerName() + request.getContextPath() + "/";
    String menuPicPath = "7xss26.com1.z0.glb.clouddn.com";
%>
<%@include file="/loading.jsp" %>
<%@include file="/abnormalLanding.jsp" %>
<%@include file="chat.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/common.css" type="text/css" />
<div class="logo-wrap">
	<a href="index.html"> <img src="<%=basePath%>images/logo_by.png" alt=""></a>
</div>
<div class="leftmenu">
	<ul class="left_nav">
		<li class="active_1_2 border" index="1" url="<%=menuBasePath%>selfcashier/view/list">
			<div class="nav_img">
				
			</div>
			
		</li>
		<li class="active_2_1" index="2" url="<%=menuBasePath%>KeepAccounts/initializeOpenCard">
			<div class="nav_img">
				
			</div> 
			
		</li>
		<li class="active_3_1" index="3" url="<%=menuBasePath%>view/coupons">
			<div class="nav_img">
				
			</div> 
			 
		</li>
		<li class="active_4_1" index="4" url="<%=menuBasePath%>summary/view/summary">
			<div class="nav_img">
				
			</div>
			
		</li>
		<li class="active_5_1" index="5" url="<%=menuBasePath%>project/view/categorys">
			<div class="nav_img">
				
			</div>
			
		</li>
		<li class="active_6_1" index="6" url="<%=menuBasePath%>goodsInfo/view/goodsInfoList">
			<div class="nav_img">
				
			</div> 
			
		</li>
		<li class="active_7_1" index="7" url="<%=menuBasePath%>position/view/positioninfo">
			<div class="nav_img">
				
			</div>
			
		</li>
		<li class="active_8_1" index="8" url="<%=menuBasePath%>system/view/storeWechat">
			<div class="nav_img">
				
			</div> 
		</li>
		<li class="active_9_1" index="9" url="<%=menuBasePath%>system/view/person">
			<div class="nav_img">
				
			</div> 
		</li>
		<li class="active_10_1" index="10" url="<%=menuBasePath%>storeinfo/view/storeSetting">
			<div class="nav_img">
				
			</div> 
		</li>
	</ul>
	
	<ul class="left_title">
	   <li style="top:20px"><span></span>业务中心</li>
	   <li style="top:90px"><span></span>会员管理</li>
	   <li style="top:160px"><span></span>营销中心</li>
	   <li style="top:230px"><span></span>统计分析</li>
	   <li style="top:300px"><span></span>服务设置</li>
	   <li style="top:370px"><span></span>商品管理</li>
	   <li style="top:440px"><span></span>员工管理</li>
       <li style="top:510px"><span></span>企业管理</li>
       <li style="top:580px"><span></span>系统设置</li>
       <li style="top:580px"><span></span>微信设置</li>
	</ul>

	<div class="left_nav_2" style="height: 840px;">
		<ul index="0">
			<a href="<%=menuBasePath%>selfcashier/view/list"><li><span>消费收银</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeManuallyOpenOrder"><li><span>无纸开单</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeStoreFlow"><li><span>收支记账</span></li></a>
			<a href="<%=menuBasePath%>daybook/view/index"><li><span>流水查询</span></li></a>
			<a href="<%=menuBasePath%>KeepAccounts/initializeShiftMahjong"><li><span>电子轮牌</span></li></a>
		</ul>
		<ul index="1">
		    <a href="<%=menuBasePath%>KeepAccounts/initializeOpenCard"><li><span>开卡充值</span></li></a>
			<a href="<%=menuBasePath%>appoint/view/list"><li><span>预约中心</span></li></a>
			<a href="<%=menuBasePath%>member/view/list"><li class=""><span>会员数据</span></li></a>
			<%-- <a href="<%=menuBasePath%>member/view/error/member/info"><li><span>异常会员数据</span></li></a> --%>
			<a href="<%=menuBasePath%>member/view/census/list"><li class=""><span>会员分组</span></li></a>
			<a href="<%=menuBasePath%>memberLevel/view/enterpriseMemberLevelList"><li class="active"><span>企业会员卡</span></li></a>
			<a href="<%=menuBasePath%>memberLevel/view/list"><li class="active"><span>门店会员卡</span></li></a>
		</ul>
		<ul index="2">
			<a href="<%=menuBasePath%>view/coupons"><li><span>优惠券</span></li></a>
			<a href="<%=menuBasePath%>marketing/view/showMinBargain"><li><span>微砍价</span></li></a>
			<a href="<%=menuBasePath%>marketing/view/showBigTurntable"><li><span>大转盘</span></li></a>
			<a href="<%=menuBasePath%>marketing/view/showLantern"><li><span>点灯笼</span></li></a>
			<a href="<%=menuBasePath%>marketing/view/showMinVote"><li><span>微投票</span></li></a>
		</ul>
		<ul index="3">
			<a href="<%=menuBasePath%>summary/view/summary"><li class=""><span>营业汇总</span></li></a>
			<a href="<%=menuBasePath%>cashreceipts/view/cashreceipts"><li><span>现金收入</span></li></a>
			<a href="<%=menuBasePath%>cardsales/view/cardsales"><li class=""><span>卡项销售</span></li></a>
			<a href="<%=menuBasePath%>laborperformance/view/laborperformance"><li class="active"><span>劳动业绩</span></li></a>
			<a href="<%=menuBasePath%>packagesales/view/packagesales"><li><span>套餐销售</span></li></a>
			<a href="<%=menuBasePath%>commoditysales/view/commoditysales"><li><span>商品销售</span></li></a>
			<a href="<%=menuBasePath%>reconciliation/view/crossReconciliation"><li><span>跨店对账</span></li></a>
			<a href="<%=menuBasePath%>businessAnalysis/view/payroll"><li><span>工资单</span></li></a>
		</ul>
		<ul index="4">
			<a href="<%=menuBasePath%>project/view/categorys"><li class=""><span>新增系列</span></li></a>
			<a href="<%=menuBasePath%>project/view/projects"><li class=""><span>项目列表</span></li></a>
			<a href="<%=menuBasePath%>comboInfo/view/comboInfoList"><li><span>套餐列表</span></li></a>
		</ul>
		<ul index="5">
			<a href="<%=menuBasePath%>goodsInfo/view/goodsInfoList"><li class=""><span>商品列表</span></li></a>
			<a href="<%=menuBasePath%>goodsInfo/view/setting"><li><span>商品库管理</span></li></a>
			<a href="<%=menuBasePath%>stock/view"><li><span>商品调遣单</span></li></a>
			<a href="<%=menuBasePath%>view/storeAccount/suplier"><li><span>供应商管理</span></li></a>
		</ul>
		<ul index="6">
			<a href="<%=menuBasePath%>position/view/positioninfo"><li class=""><span>组织架构</span></li></a>
			<a href="<%=menuBasePath%>employee/view/employee"><li><span>员工资料</span></li></a>
			<a href="<%=menuBasePath%>storeManageRule/view/home"><li class=""><span>管理制度</span></li></a>
			<a href="<%=menuBasePath%>shift/view/shift"><li class="active"><span>排班设置</span></li></a>
			<a href="<%=menuBasePath%>objective/view/objective"><li><span>目标设置</span></li></a>
			<a href="<%=menuBasePath%>objectiverule/view/objectiverule"><li><span>目标考核</span></li></a>
			<a href="<%=menuBasePath%>attendance/view/attendance"><li><span>考勤记录</span></li></a>
		</ul>
		<ul index="7">
			<a href="<%=menuBasePath%>system/view/storeWechat"><li class="active"><span>微信设置</span></li></a>
			<a href="<%=menuBasePath%>storeinfo/view/showStoreList"><li class="active"><span>门店管理</span></li></a>
			<a href="<%=menuBasePath%>enterprise/view/showEnterprise"><li><span>新增企业</span></li></a>
			<a href="<%=menuBasePath%>system/view/share"><li class=""><span>分享拓客</span></li></a>
			<a href="<%=menuBasePath%>system/view/storeUsage"><li class="active"><span>系统信息</span></li></a>
			<a href="<%=menuBasePath%>app/pay/qr"><li class="active"><span>企业充值</span></li></a>
		</ul>
		<ul index="8">
			<a href="<%=menuBasePath%>system/view/person"><li class=""><span>账户设置</span></li></a>
			<a href="<%=menuBasePath%>system/view/baseSetting"><li><span>基础设置</span></li></a>
		</ul>
		<ul index="9">
			<a href="<%=menuBasePath%>storeinfo/view/storeSetting"><li><span>微门店设置</span></li></a>
			<a href="<%=menuBasePath%>memberCenter/view/store/shop"><li><span>在线商城</span></li></a>
			<a href="<%=menuBasePath%>wechat/items/manage"><li><span>营销方案库</span></li></a>
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
		jQuery(this).addClass('border').siblings('li').removeClass('border');
		jQuery('.left_nav_2 ul').eq(jQuery(this).index()).show().siblings().hide();
	});
})

//点击换图
jQuery(function(){
	jQuery('.left_nav li').on("click", function(){
		var index = jQuery(this).attr("index");
		jQuery(this).addClass('active_'+index+'_2 border').siblings().removeClass('border active_1_2 active_2_2 active_3_3 active_4_4 active_5_5 active_6_6 active_7_7 active_8_8 active_9_9 active_10_10');
		window.location.href = jQuery(this).attr("url");
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
	//二级菜单
	jQuery(".left_nav_2").find("li").removeClass("active");
	jQuery(".left_nav_2").find("a[href='"+url+"']").parent("ul").show().siblings().hide();
	jQuery(".left_nav_2").find("a[href='"+url+"']").children("li").addClass("active");
	//1级菜单
	jQuery('.left_nav li').eq(0).addClass('active_1_1');
	var index = jQuery(".left_nav_2").find("a[href='"+url+"']").parent("ul").attr("index");
	var index_ = Number(index)+1;
	jQuery(".left_nav li").eq(index).addClass("active_"+(index_)+"_2  border").siblings().removeClass('border active_1_2 active_2_2 active_3_3 active_4_4 active_5_5 active_6_6 active_7_7 active_8_8 active_9_9 active_10_10');
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
  
  
  
 jQuery(function(){
	 jQuery('.left_nav li').hover(function(){
		 
		 jQuery('.left_title li').eq(jQuery(this).index()).stop(true,true).fadeIn('slow');	 
		 
	 },function(){
		 
		 jQuery('.left_title li').eq(jQuery(this).index()).stop(true,true).fadeOut('slow');
		 		 
	 })
 
 })
</script>