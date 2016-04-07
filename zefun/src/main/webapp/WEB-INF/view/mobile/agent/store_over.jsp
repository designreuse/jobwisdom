<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css"/>
    <title>正常使用</title>
</head>
<body class="gray-bg">
<div class="my-shop">

    <div class="tab t0">
        <ul >
            <li class="tab-more  " style="width: 33.333%">
                <a href="<%=basePath%>agentdetail/view/store">
                    <img src="<%=basePath%>/images/mobile/agent/active-new.png" alt="" class="hide"/>
                    <div class="tab-word">
                        <span>正常使用</span>
                    </div>
                </a>
            </li>
            <li class="tab-more" style="width: 33.333%">
                <a href="<%=basePath%>agentdetail/view/store/renew">
                    <img src="<%=basePath%>/images/mobile/agent/active-new.png" alt="" class="hide"/>
                    <div class="tab-word">
                        <span>续费提醒</span>
                    </div>
                </a>
            </li>
            <li class="tab-more active" style="width: 33.333%">
                <a href="<%=basePath%>agentdetail/view/store/over">
                    <img src="<%=basePath%>/images/mobile/agent/active-new.png" alt="" />
                    <div class="tab-word">
                        <span>未续费</span>
                    </div>
                </a>
            </li>
        </ul>
    </div>
    <div class="clearfix"></div>

    <!--未续费-->
    <div class="tab-target" id="tab3">
        <div class="new-shop-list">
            <c:forEach var="storeInfo" items="${storeInfos }"   varStatus="storeInfoStatus">
            <ul class="new-shop-item" onclick="show(this);">
                <li class="name"  >
                    <span>${storeInfo.storeName }</span> <span class="iconfont icon-xiala fr"></span>
                </li>
                <li class="shop-detail <c:if test="${!storeInfoStatus.first }">hide</c:if>">
                    <ul class="shop-detail-ul">
                        <li class="normal-li">
                            <span class="key">开通日期</span>
                            <span class="value fr">${storeInfo.createTime }</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">使用期限</span>
                            <c:set var="storeAccount" value="${storeAccounts[storeInfo.storeId] }" />
                            <span class="value fr">剩余${storeAccount.balanceDays }天</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">短信剩余量</span>
                            <span class="value fr">剩余${storeAccount.balanceSms }条</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">门店类型</span>
                          <c:set var="type" value="单店" />
                          <c:if test="${storeInfo.storeType == 2 }">
                          <c:set var="type" value="连锁总店" />
                          </c:if>
                          <c:if test="${storeInfo.storeType == 3 }">
                          <c:set var="type" value="连锁分店" />
                          </c:if>
                          <span class="value fr">${type }</span>
                        </li>
                        <li class="normal-li">
                            <span class="key">地址</span>
                            <c:set var="address" value="--" />
                              <c:if test="${not empty storeInfo.storeAddress }">
                              <c:set var="address" value="${storeInfo.storeAddress }" />
                              </c:if>
                              <span class="value fr">${address }</span>
                        </li>
                        <li class="recommend-people normal-li">
                            <span class="key">推荐人</span>
                            <c:set value="无" var="recommend" />
                          <c:if test="${not empty recommendAgents[storeInfo.storeId] }">
                          <c:set value="${recommendAgents[storeInfo.storeId].province}${recommendAgents[storeInfo.storeId].city }" var="recommend" />
                          </c:if>
                          <span class="value fr">${recommend }</span>
                        </li>
                        <!-- 渠道跟踪记录 -->
                        <li class="rec-pp-info">
                            <ul>
                                <li class="info-name">
                                    <span>服务记录</span>
                                </li>
                                <c:forEach items="${agentFollowList }" var="agentFollow">
                                  <li class="time-reason">
                                      <div> <span class="fr time">${agentFollow.createTime }</span></div>
                                      <div> <span class="reason">${agentFollow.content }</span></div>
                                  </li>
                                </c:forEach>
                                <li class="input-reason">
                                    <div class="input-wrap">
                                        <input id="agentFollowContentOfAdd" type="text" placeholder="请输入跟踪服务记录"/>
                                    </div>
                                    <div class="queren" onclick="addAgentFollow(${storeInfo.storeId})">确认</div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="person-info">
                    <span class="iconfont icon-dianhua4 bule"></span>
                    <span>${storeInfo.storeLinkphone }</span>
                    <span class="fr">联系人：${storeInfo.storeLinkname }</span>
                </li>
            </ul>
            </c:forEach>
        </div>
    </div>
</div>


<div class="footer">
    <ul>
        <li >
            <a href="<%=basePath%>agentdetail/view/newstore">
                <span class="iconfont icon-huizhenshenqing"></span>
                <span>新申请</span>
            </a>
        </li>
        <li class="active">
            <a href="<%=basePath%>agentdetail/view/store">
                <span class="iconfont icon-xingzhuang22"></span>
                <span>我的门店</span>
            </a>
        </li>
        <li >
            <a href="<%=basePath%>agentdetail/view/index">
                <span class="iconfont icon-wode"></span>
                <span>我的账户</span>
            </a>
        </li>
        <li>
            <a href="<%=basePath%>agentdetail/view/stat">
                <span class="iconfont icon-yejipaihang02"></span>
                <span>渠道排行</span>
            </a>
        </li>
    </ul>
</div>

<script src="<%=jqueryJsPath%>"></script>
<script src="<%=mobileBaseJsPath%>"></script>
<script>
var clientHeight =  $(window).height();
$(".gray-bg").css("min-height", clientHeight);
function show(obj){
  var op = $(obj);
  $(op).find('.shop-detail').removeClass('hide');
  $(op).siblings().find('.shop-detail').addClass('hide');
}
//新增渠道跟踪记录
function addAgentFollow(storeId) {
  var content = jQuery("#agentFollowContentOfAdd").val();
  if (content == null || content == undefined || content == "" || content.trim().length == 0) {
    dialog("请输入跟踪服务记录");
    return;
  }
  jQuery.ajax({
    type : "post",
    dataType : "json",
    data : {
      storeId : storeId,
      content : jQuery("#agentFollowContentOfAdd").val()
    },
    url : baseUrl + "agentFollow/action/add",
    success : function (resultData) {
      dialog(resultData.msg);
      if (resultData.code = 0) {
        return;
      }
      window.location.reload();
    }
  });
}
//删除渠道跟踪记录
function deleteAgentFollow(agentFollowId) {
  if (window.confirm("确认删除吗")) {
    jQuery.ajax({
      type : "post",
      dataType : "json",
      data : {
        agentFollowId : agentFollowId
      },
      url : baseUrl + "agentFollow/action/delete",
      success : function (resultData) {
        dialog(resultData.msg);
        if (resultData.code = 0) {
          return;
        }
        window.location.reload();
      }
    });
  }
}
</script>
</body>
</html>
