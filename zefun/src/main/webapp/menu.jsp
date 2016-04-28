<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.zefun.web.entity.MemberMenu"%>
<%
    String menuBasePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";
    String menuPicPath = "7xkv8r.com1.z0.glb.clouddn.com";
%>
<div class="leftmenu">

	<ul class="left_nav" style="height: 840px;">

		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_1.png">
			</div> <span>业务中心</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_2.png">
			</div> <span>业务中心</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_3.png">
			</div> <span>业务中心</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_4.png">
			</div> <span>业务中心</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_5.png">
			</div> <span>业务中心</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_6.png">
			</div> <span>业务中心</span>
		</li>
		<li>
			<div class="nav_img">
				<img src="<%=basePath%>images/left_7.png">
			</div> <span>业务中心</span>
		</li>
	</ul>

	<div class="left_nav_2" style="height: 840px;">
		<ul>
			<li class=""><span><img src="<%=basePath%>images/left0.png">项目设置</span></li>
			<li><span><img src="<%=basePath%>images/left1.png">项目设置</span></li>
			<li class=""><span><img src="<%=basePath%>images/left2.png">项目设置</span></li>
			<li class="active"><span><img src="<%=basePath%>images/left3.png">项目设置</span></li>
			<li><span><img src="<%=basePath%>images/left4.png">项目设置</span></li>
			<li><span><img src="<%=basePath%>images/left5.png">项目设置</span></li>

		</ul>

	</div>
	<!--营业报表-->

	<img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li class="active" name="yingyehuizong"><a href="report-yingyehuizong.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/kaika.png" alt=""/>-->
				<i class="iconfont icon-yingyehuizong1 left-icon"></i>
			</div> 营业汇总
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-xianjinshouru"><a href="report-xianjinshouru.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/kaika.png" alt=""/>-->
				<i class="iconfont icon-xianjin-SR left-icon"></i>
			</div> 现金收入
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-card-income"><a href="report-card-income.html">
			<div class="img-wrap">
				<i class="iconfont icon-kaxiangxiaoshou left-icon"></i>
			</div> 卡项销售
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-laodongyeji"><a href="report-laodongyeji.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/kaizhijizhang.png" alt=""/>-->
				<i class="iconfont icon-laodongyeji left-icon"></i>
			</div> 劳动业绩
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-huakaxiaofei"><a href="report-huakaxiaofei.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/shouyin.png" alt=""/>-->
				<i class="iconfont icon-kajin-XF left-icon"></i>
			</div> 划卡消费
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-taocan-sell"><a href="report-taocan-sell.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/kaizhijizhang.png" alt=""/>-->
				<i class="iconfont icon-taocanxiaoshou1 left-icon"></i>
			</div> 套餐销售
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-shops-sell"><a href="report-shops-sell.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/kaizhijizhang.png" alt=""/>-->
				<i class="iconfont icon-shangpinxiaoshou1 left-icon"></i>
			</div> 商品销售
	</a></li>

	<!-- 经营分析 -->

	<img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-client-analyze"><a href="report-client-analyze.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/lunzhipaiban.png" alt=""/>-->
				<i class="iconfont icon-keqingfenxi1 left-icon"></i>
			</div> 客情分析
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="appointment-analyze"><a href="appointment-analyze.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/lunzhipaiban.png" alt=""/>-->
				<i class="iconfont icon-yuyuefenxi1 left-icon"></i>
			</div> 预约分析
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-emploee-analyze"><a href="report-emploee-analyze.html">
			<div class="img-wrap">
				<i class="iconfont icon-yuangongfenxi left-icon"></i>
			</div> 员工分析
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-emploee-analyze"><a href="report-business-status.html">
			<div class="img-wrap">
				<i class="iconfont icon-yingyefenxi left-icon"></i>
			</div> 营业分析
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-yingxiao"><a href="report-yingxiao.html">
			<div class="img-wrap">
				<i class="iconfont icon-yingxiaofenxi1 left-icon"></i>
			</div> 营销分析
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li class="active" name="report-payroll"><a href="report-payroll.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/kaika.png" alt=""/>-->
				<i class="iconfont icon-gongzidan4 left-icon"></i>
			</div> 工资单
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="report-porfile"><a href="report-profile.html">
			<div class="img-wrap">
				<i class="iconfont icon-yuangong-FX left-icon"></i>
			</div> 财务分析
	</a></li>

	<!-- 服务设置模块 -->


	<!-- 员工设置模块 -->

	<img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li class="active" name="gangwei-setting"><a href="gangwei-setting.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/shouyin.png" alt=""/>-->
				<i class="iconfont icon-zuzhijiagou left-icon"></i>
			</div> 组织架构
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="post-data"><a href="post-data.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/lunzhipaiban.png" alt=""/>-->
				<i class="iconfont icon-yuangong-ZL left-icon"></i>
			</div> 员工资料
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="award-punishment"><a href="award-punishment.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/shouyin.png" alt=""/>-->
				<i class="iconfont icon-zhidu-KH left-icon"></i>
			</div> 管理制度
	</a></li>
	<!--<img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide"/>
            <li name="employee-leave">
                <a href="employee-leave.html">
                    <div class="img-wrap">
                        &lt;!&ndash;<img src="<%=basePath%>images/shouyin.png" alt=""/>&ndash;&gt;
                        <i class="iconfont icon-qingjia-SP left-icon"></i>
                    </div>
                    请假审批
                </a>
            </li>-->

	<img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="employee-shift"><a href="employee-shift.html">
			<div class="img-wrap">
				<!--<img src="<%=basePath%>images/shouyin.png" alt=""/>-->
				<i class="iconfont icon-yuangong-BC left-icon"></i>
			</div> 排班设置
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="set-target"><a href="set-target.html">
			<div class="img-wrap">
				<i class="iconfont icon-mdmubiao left-icon"></i>
			</div> 目标设置
	</a></li>

	<!--<img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide"/>
            <li name="jingsai">
                <a href="jingsai.html">
                    <div class="img-wrap">
                        &lt;!&ndash;<img src="<%=basePath%>images/kaizhijizhang.png" alt=""/>&ndash;&gt;
                        <i class="iconfont icon-wodebisai left-icon"></i>
                    </div>
                    组织比赛
                </a>
            </li>-->
	<img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="target-assessment"><a href="target-assessment.html">
			<div class="img-wrap">
				<i class="iconfont icon-kaohechengji left-icon"></i>
			</div> 目标考核
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li name="employee-attendance"><a href="employee-attendance.html">
			<div class="img-wrap">
				<i class="iconfont icon-kaiqin left-icon"></i>
			</div> 考勤记录
	</a></li>

	<!--系统设置-->

	<img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li class="active" name="jueseguanli"><a href="jueseguanli.html">
			<div class="img-wrap">
				<i class="iconfont icon-zuzhijiagou left-icon"></i>
			</div> 角色管理
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li class="active" name="os-usage"><a href="os-usage.html">
			<div class="img-wrap">
				<i class="iconfont icon-iconfontxianshi4 left-icon"></i>
			</div> 系统使用状况
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li class="active" name="zhanghuxinxi"><a href="zhanghuxinxi.html">
			<div class="img-wrap">
				<i class="iconfont icon-iconfontxianshi4 left-icon"></i>
			</div> 账户信息
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li class="active" name="wodefendian"><a href="member-xuzhi.html">
			<div class="img-wrap">
				<i class="iconfont icon-iconfontxianshi4 left-icon"></i>
			</div> 会员须知
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li class="active" name="wodefendian"><a href="wodefendian.html">
			<div class="img-wrap">
				<i class="iconfont icon-iconfontxianshi4 left-icon"></i>
			</div> 我的分店
	</a></li> <img src="<%=basePath%>images/left-active.png" alt="" class="left-active-img hide">
	<li class="active" name="share-shezhi"><a href="share-shezhi.html">
			<div class="img-wrap">
				<i class="iconfont icon-iconfontxianshi4 left-icon"></i>
			</div> 分享者设置
	</a></li>

</div>
<!--leftmenu-->
</div>