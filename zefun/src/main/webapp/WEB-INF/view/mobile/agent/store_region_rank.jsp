<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="/base.jsp"%>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="viewport"
  content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<meta name="msapplication-tap-highlight" content="no">
<link rel="stylesheet" href="<%=iconfontCssPath%>"/>
<link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css"/>
<title>渠道排行</title>
</head>
<body class="gray-bg">
  <div class="wrap">

    <div class="agent-paixing gray-bg">
<%--       <div class="px-search">
        <form action="<%=basePath%>agentdetail/view/stat" method="post" id="sea_rank_frm">
        <input type="text" placeholder="搜索城市" value="${cityName }">
        <span class="iconfont icon-sousuo"></span>
        <input type="hidden" name="orderType" value="${orderType }" id="orderType">
        </form>
      </div> --%>
      <div class="quanguo">
        <div class="tongji-item">
          <div class="tongji-content">
            <table class="tongji-table table-hovered">
              <thead>
                <tr>
                  <th>排行</th>
                  <th>城市</th>
                  <th onclick="search(2);">当月装店</th>
                  <th onclick="search(1);">累计装店</th>
                  <th onclick="search(3);">微信顾客</th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${rankDtos }" var="rank"
                  varStatus="rankStatus">
                  <tr>
                    <td>${rankStatus.index }</td>
                    <td>${rank.city }</td>
                    <td>${rank.currMonth }</td>
                    <td>${rank.total }</td>
                    <td>${rank.wechat }</td>
                  </tr>
                </c:forEach>
              </tbody>
            </table>
          </div>
        </div>

      </div>

    </div>

    <div class="footer">
      <ul>
        <li><a href="<%=basePath%>agentdetail/view/newstore"> <span
            class="iconfont icon-huizhenshenqing"></span> <span>新申请</span>
        </a></li>
        <li><a href="<%=basePath%>agentdetail/view/store"> <span
            class="iconfont icon-xingzhuang22"></span> <span>我的门店</span>
        </a></li>
        <li><a href="<%=basePath%>agentdetail/view/index"> <span
            class="iconfont icon-wode"></span> <span>我的账户</span>
        </a></li>
        <li class="active">
          	<a href="<%=basePath%>agentdetail/view/stat">
                <span class="iconfont icon-yejipaihang02"></span>
                <span>渠道排行</span>
            </a>
        </li>
      </ul>
    </div>

  </div>
</body>
<script src="<%=jqueryJsPath%>"></script>
<script type="text/javascript">
var search = function(ot) {
  if(!ot) {
    ot = 1;
  }
  if(ot == ${orderType}) {
    return;
  }
  $('#orderType').val(ot);
  $('#sea_rank_frm').submit();
}

</script>
</html>

