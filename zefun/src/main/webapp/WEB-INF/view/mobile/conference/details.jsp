<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>会议收支明细</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
  </head>
<body class="gray-bg">
<div class="wrap">
<!--费用详情-->
<div class="huiyi-coat">
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
        <!--费用内容-->
        <ul class="cost">
            <li>
                <p><i class="huiyi-c1">${earning+0 }</i></p>
                <span>已收入场费</span>
            </li>
            <li>
                <p><i>${pays }</i></p>
                <span>需支出奖金</span>
            </li>
        </ul>
        <div class="hy-cost-table">
            <div class="tongji-item">
                <div class="tongji-content">
                    <table class="tongji-table table-hovered c-table">
                        <thead>
                        <tr>
                            <th >联系人</th>
                            <th >一级推荐<span class="person">(人)</span></th>
                            <th >二级推荐<span class="person">(人)</span></th>
                            <th >支付奖金</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${detailsDtos }" var="detailsDto">
	                    <tr>
	                    	<td>${detailsDto.name }</td>
	                    	<td>${detailsDto.mainCount }</td>
	                    	<td>${detailsDto.branchCount }</td>
	                    	<td>${detailsDto.mainAmount + branchAmount }</td>
	                    </tr>
	                    </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

</div>
</body>
</html>