<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.Date" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>会议信息</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
    <style type="text/css">
    	.huiyiliebiao{
    		padding-bottom: 4rem;
    	}
    </style>
  </head>
<body class="gray-bg">
<div class="wrap">
<div class="huiyi-detail">
  <!--会议头部-->
    <div class="huiyi-content">
        <div class="content-desc">
            <div class="han-title">
                	${conferenceInfo.conferenceName }
            </div>
            <div class="title-fenxian">
                <img src="<%=basePath %>images/mobile/agent/han-title-fenxain.png" alt=""/>
            </div>
            <div class="jb-time">
                会议开始时间：<fmt:formatDate pattern="MM月dd日 HH点mm分"  value="${date }" />
            </div>
            <div class="hy-person">
                <span class="iconfont mr phone">&#xe841;</span><span>${conferenceInfo.linkPhone }</span>  <span>${conferenceInfo.linkName }</span>
            </div>
            <div class="jb-address">
                <span class="iconfont icon-dizhi1 mr"></span><span>${conferenceInfo.address }</span>
            </div>
        </div>
    </div>
    <!--会议状态-->
    <div class="huiyi-status">
    <c:choose>
	    <c:when test="${status == 1 }">
		    <div class="hy-btn hy-jinxing">
		            进行中
	        </div>
	    </c:when>
	    <c:when test="${status == 2 }">
		    <div class="hy-btn hy-baoming">
	                      报名中
	        </div>
	    </c:when>
	    <c:when test="${status == 3 }">
		    <div class="hy-btn hy-finish">
	                      已完成
	        </div>
	    </c:when>
	    <c:otherwise>
		    <div class="hy-btn hy-uninitiated">
	                      未开始
	        </div>
	    </c:otherwise>
    </c:choose>
        <!--会议按钮-->
      <!--进行中-->
        
        <span class="hy-yuan hy-dw1"></span>
        <span class="hy-yuan hy-dw2"></span>
        <span class="hy-yuan hy-dw3"></span>
        <span class="hy-yuan hy-dw4"></span>
        <ul>
            <li class="hy-bdr hy-bdb">
                    <c:choose>
				    <c:when test="${status == 1 }">
				    <a href="<%=basePath%>conference/view/admission?conferenceId=${conferenceInfo.conferenceId }">
				    	<p class="huiyi-c1">${hasAdmission }</p>
					    <span class="huiyi-c2">入场人数</span>
				    </c:when>
				    <c:when test="${status == 2 }">
				    <a href="<%=basePath%>conference/view/regist?conferenceId=${conferenceInfo.conferenceId }">
				    	<p class="huiyi-c1">${haPays }</p>
					    <span class="huiyi-c2">报名人数</span>
				    </c:when>
				    <c:when test="${status == 3 }">
				    <a href="<%=basePath%>conference/view/admission?conferenceId=${conferenceInfo.conferenceId }">
				    	<p class="huiyi-c1">${hasAdmission }</p>
					    <span class="huiyi-c2">入场人数</span>
				    </c:when>
				    <c:otherwise>
				    <a href="<%=basePath%>conference/view/regist?conferenceId=${conferenceInfo.conferenceId }">
				    	<p class="huiyi-c1">${haPays }</p>
	                    <span class="huiyi-c2">报名人数</span>
				    </c:otherwise>
			    	</c:choose>
                    <span class="huiyi-c3">(${conferenceInfo.peopleCount })</span>
                </a>
            </li>
            <li class="hy-bdb">
                <a href="<%=basePath%>conference/view/details?conferenceId=${conferenceInfo.conferenceId }">
                	<c:if test="${earning == null}">
                		<p class="huiyi-c1">0</p>
                	</c:if>
                	<c:if test="${earning != null}">
                		<p class="huiyi-c1">${earning }</p>
                	</c:if>
                    <span class="huiyi-c2">参会费用</span>
                    <span class="huiyi-c3">(${conferenceInfo.registrationAmount })</span>
                </a>
            </li>
            <li class="hy-bdr">
                <a href="<%=basePath%>conference/view/details?conferenceId=${conferenceInfo.conferenceId }">
                    <p class="huiyi-c1">${mainPays }</p>
                    <span class="huiyi-c2">一级奖励</span>
                    <span class="huiyi-c3">(${conferenceInfo.mainAward })</span>
                </a>
            </li>
            <li>
                <a href="<%=basePath%>conference/view/details?conferenceId=${conferenceInfo.conferenceId }">
                    <p class="huiyi-c1">${branchPays }</p>
                    <span class="huiyi-c2">二级奖励</span>
                    <span class="huiyi-c3">(${conferenceInfo.branchAward })</span>
                </a>
            </li>
        </ul>

    </div>
    <!--hy-bottom-->
    <div class="huiyi-bottom">
        <div class="hy-share" onclick="share()">
            <span class="iconfont icon-iconfontfenxiang" style="margin-top: 1rem;margin-bottom: 0.5rem; display: block;"></span>
            <span>分享</span>
        </div>
        <ul>
            <li><a href="<%=basePath%>conference/view/update?conferenceId=${conferenceInfo.conferenceId }">修改</a></li>
            <li><a href="<%=basePath%>conference/view/details?conferenceId=${conferenceInfo.conferenceId }">统计</a></li>
        </ul>
    </div>
</div>
</div>

<div class="s-modal no-padding hide s-modal-miss" id="shareTip">
    <div class="s-modal-wrap">
        <div class="jd-wrap">
            <img src="<%=picPath %>agent_share_tip.png" alt=""/>
            <div class="jd-main">
                	已为你生成 <span id="typeName">会议</span> 的推广链接, 请点击右上角分享吧！
                <div class="jd-btn">我知道了</div>
            </div>
        </div>
    </div>
</div>
<%@include file="../wechatBase.jsp" %>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript">
var fromUser = '${conferenceInfo.agentId}';
var conferenceId = '${conferenceInfo.conferenceId }';
var imgUrl = "http://7xkv8r.com1.z0.glb.clouddn.com/zefun/project/1452323943233";
var desc = '${conferenceInfo.conferenceDesc}';
var link = baseUrl+'mobile/view/pay/conference?fromUser=0&conferenceId='+conferenceId;
var title = '${conferenceInfo.conferenceName}';

$("#shareTip").click(function(){
	$(this).hide();
});
wx.ready(function(){
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
});

function share(){
	$("#shareTip").show();
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

function updateConference(id){
	window.location.href = baseUrl + "conference/view/update?conferenceId=" + id;
}
</script>
</body>
</html>