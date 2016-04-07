<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.Date" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>我的会议</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
    <style type="text/css">
   	  .head-desc {
        background-color: #fff;
        height: 6.25rem;
        line-height: 6.25rem;
        width: 100%;
        padding: 0 0rem 0 1rem;
        font-size: 1.8rem;
        box-sizing: border-box;
    }
    .right-add{
        width: 8rem;
        height: 5rem;
        line-height: 5rem;
        text-align: center;
        border-left: 1px solid #ccc;
        margin-top: 0.725rem;
    }
    .icon-tianjia1 {
        display: inline-block;
        font-size: 3rem;
        color: #1de1db;
        margin-top: -0.5rem;
        /*margin-left: -0.8rem;*/
    }
    .num {
        color: #1de1db;
        margin: 0 .5rem;
    }
    .huiyi-item {
        padding: 1rem;
        border-bottom: 1px solid #ccc;
        overflow: hidden;
    }
    .huiyi-item:last-child {
        border-bottom: 0;
    }
    .item-line {
        clear: both;
    }
    .huiyi-list {
        background-color: #fff;
    }
    .huiyi-state {
        padding: 0.2rem 1.5rem;
        border-radius: 1rem;
        color: #fff;
    }
    .going {
        background-color: #4ccdc9;
    }
    .complete {
        background-color: #ccc;
    }
    .huiyi-item-wrap {
        padding: 0 1rem;
    }
    </style>
  </head>
  
<body class="gray-bg">
<div class="wrap">

<div class="huiyi-list-page">
    <div class="head-desc">
        <span>共发起了<span class="num">${fn:length(conferenceInfoDtos)}</span>场会议</span>
        <div class="right-add fr">
        <a href="<%=basePath%>conference/view/conference">
            <span class="iconfont icon-tianjia1"></span>
        </a>    
        </div>
    </div>

    <div class="huiyi-list mt1">
        <ul class="huiyi-item-wrap">
            <c:forEach items="${conferenceInfoDtos }" var="conferenceInfo">
        	<li class="huiyi-item">
                <div class="item-line">
                    <div class="fl font-size-30">${conferenceInfo.conferenceName }</div>
                    <c:choose>
                    <c:when test="${conferenceInfo.status == 1}"><div class="fr hy-jinxing huiyi-state">进行中</div></c:when>
                    <c:when test="${conferenceInfo.status == 2}"><div class="fr hy-baoming huiyi-state">报名中</div></c:when>
                    <c:when test="${conferenceInfo.status == 3}"><div class="fr hy-finish huiyi-state">已完成</div></c:when>
                    <c:when test="${conferenceInfo.status == 4}"><div class="fr hy-uninitiated huiyi-state">未开始</div></c:when>
                    </c:choose>
                </div>
                <div class="item-line">
                    <div class="fl font-size-20 c-666">${conferenceInfo.address }</div>
                </div>
                <a href="<%=basePath %>conference/view/info?conferenceId=${conferenceInfo.conferenceId }&status=${conferenceInfo.status}">
                <div class="item-line c-999">
                    <div class="fl font-size-24 c-999">召开时间  ${conferenceInfo.holdTime }</div>
                    <div class="fr font-size-20">查看详情>></div>
                </div>
                </a>
            </li>
        </c:forEach>
        </ul>
    </div>
</div>
</div>
</body>
</html>