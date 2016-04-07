<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/base.jsp" %>
<%@ page import="java.util.Date" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
    <title>业务员账户</title>
</head>
<body class="gray-bg">
<div class="wodezhanghu">
    <div class="ml-head">
        <div style="overflow: hidden">
            <div class="name">${salesman.name }</div>
        </div>
        <div class="address-wrap">
            <div class="address">
                <div>${salesman.agentInfo.city }</div>
            </div>
        </div>
        <div class="h-btn">
            <div class="md-btn s-modal-control" onclick="share()">
            	推荐新客户
            </div>
        </div>
    </div>

    <a href="<%=basePath%>agentdetail/view/income">
	    <ul class="tip">
	        <li class="shouyi-sum">
	            <div class="wrap">
	                <div class="wrap-content">
	                    <div class="name">账户收益</div>
	                    <div class="num-sum">
	                        <span class="num">0</span> <span class="font-size-24">元</span>
	                    </div>
	                </div>
	            </div>
	        </li>
	        <li class="shouyi-sum">
	            <div class="wrap">
	                <div class="wrap-content">
	                    <div class="name">获得账户/剩余</div>
	                    <div class="num-sum">
	                        <span class="num">0</span> <span class="font-size-48">/</span><span class="shegnyu">0</span><span>个</span>
	                    </div>
	                </div>
	            </div>
	        </li>
	    </ul>
    </a>

    <ul class="zh-about-list">
        <a href="<%=basePath%>salesman/view/seeSalesmanRecommendStoreNormal?salesmanId=${salesman.salesmanId}">
	        <li class="zh-item">
	          <span class="iconfont icon-iconfonticonfontwodehuiyi"></span> <span>我的客户</span> <span class="iconfont icon-youbianfangxiang fr"></span>
	        </li>
        </a>
    </ul>

</div>

<!--选择推荐新客户-->
<div class="s-modal no-padding hide s-modal-miss" id="shareTip">
    <div class="s-modal-wrap">
        <div class="jd-wrap">
            <img src="<%=picPath %>agent_share_tip.png" alt=""/>
            <div class="jd-main">
            	已为你生成 <span id="typeName">新渠道</span> 的推广链接, 请点击右上角分享吧！
                <div class="jd-btn">我知道了</div>
            </div>
        </div>
    </div>
</div>
<%@include file="../wechatBase.jsp" %>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script src="<%=mobileBaseJsPath%>"></script>
<script>
    var name = "${salesman.name }";
    
    var title = '';
    var desc = '2016美业的首选！智放为美业设计的门店智能营销管理系统颠覆现场作业流程！让系统帮你运转门店！无卡化经营！无纸化开单！直接降低成本！智能轮牌！系统自动识别员工牌位状态进行派单！只需一次点击，业绩，提成自动入帐！微信互动预约！顾客与员工通过系统中确认预约！会员自助买单！智能识别优惠！全面解放收银前台!顾客朋友圈分享！让顾客成为你的拓客好帮手！';
    var link = '';
    var imgUrl = picUrl + 'maywant_logo.jpg?imageView2/1/w/200/h/200';
    
    function share(){
   		title = name + '诚邀您体验智放智能营销管理系统，无卡化经营！无纸化开单！智能轮牌！';
        link = baseUrl + 'storeapply/view/storeApply?code=${code}';
    	$("#typeName").html("新门店");
    	$("#shareTip").show();
    	registerShare();
    }
    
    function registerShare(){
        wx.onMenuShareAppMessage({
          title: title,
          desc: desc,
          link: link,
          imgUrl: imgUrl
        });

        wx.onMenuShareTimeline({
          title: title,
          link: link,
          imgUrl: imgUrl
        });
    }
    
    $("#shareTip").click(function(){
    	$(this).hide();
    });
</script>
</body>
</html>
