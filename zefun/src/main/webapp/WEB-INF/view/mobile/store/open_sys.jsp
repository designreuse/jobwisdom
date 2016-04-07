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
    <title>系统购买</title>
</head>
<body class="gray-bg">

<div class="pay gray-bg">
    <ul class="list">
        <li class="pay-title bsw">
            开通智放系统
        </li>
    </ul>
    <div class="charge-list">
        首次开通系统，我们赠送您两个月系统使用期限。为了微信公众号的会员注册，您需要先购买一定的短信数量。
    </div>
    <ul class="list">
        <li class="way way1">系统收费</li>
        <c:forEach var="setting" items="${sysList }" varStatus="settingStatus">
        	<c:choose>
        		<c:when test="${settingStatus.first }">
        			<li class="way">
			            <div class="checker sys_checker">
			                <div class="beau-radio active">
			                    <span class="iconfont icon-gou"></span>
			                </div>
			                <input type="radio" checked="checked" class="yellow-radio sys_type" name="sysType" value="${setting.id }"/>
			            </div>
			            <div class="desc-wrap">
			                <span class="desc">${setting.description }</span>
			            </div>
			        </li>
        		</c:when>
        		<c:otherwise>
        			<li class="way">
			            <div class="checker sys_checker">
			                <div class="beau-radio">
			                    <span class="iconfont icon-gou"></span>
			                </div>
			                <input type="radio" class="yellow-radio sys_type" name="sysType" value="${setting.id }"/>
			            </div>
			            <div class="desc-wrap">
			                <span class="desc">${setting.description }</span>
			            </div>
			        </li>
        		</c:otherwise>
        	</c:choose>
        </c:forEach>
        <li class="way way1">短信收费</li>
        <c:forEach var="setting" items="${smsList }" varStatus="settingStatus">
	        <c:choose>
        		<c:when test="${settingStatus.first }">
        			<li class="way">
			            <div class="checker sms_checker">
			                <div class="beau-radio active">
			                    <span class="iconfont icon-gou"></span>
			                </div>
			                <input type="radio" checked="checked" class="yellow-radio sms_type" name="smsType" value="${setting.id }"/>
			            </div>
			            <div class="desc-wrap">
			                <span class="desc">${setting.description }</span>
			            </div>
			        </li>
        		</c:when>
        		<c:otherwise>
        			<li class="way">
			            <div class="checker sms_checker">
			                <div class="beau-radio">
			                    <span class="iconfont icon-gou"></span>
			                </div>
			                <input type="radio" class="yellow-radio sms_type" name="smsType" value="${setting.id }"/>
			            </div>
			            <div class="desc-wrap">
			                <span class="desc">${setting.description }</span>
			            </div>
			        </li>
        		</c:otherwise>
        	</c:choose>
        </c:forEach>
        <li class="way bsw btn-way">
            <a href="javascript:pay();">
                <div class="pay-btn">
                    立即支付
                </div>
            </a>
        </li>
    </ul>
</div>
<script src="<%=jqueryJsPath%>"></script>
<script src="<%=mobileBaseJsPath%>"></script>
<script>
    /*漂亮的单选和多选*/
    $(".sys_checker").on("click", function () {
        var radio = $(".sys_type");
        for(var i=0;i<radio.length;i++)
        {
            if(radio[i].checked)
            {
                $(radio[i]).siblings(".beau-radio").addClass("active");
            }else{
                $(radio[i]).siblings(".beau-radio").removeClass("active");
            }
        }
    });
    
    $(".sms_checker").on("click", function () {
        var radio = $(".sms_type");
        for(var i=0;i<radio.length;i++)
        {
            if(radio[i].checked)
            {
                $(radio[i]).siblings(".beau-radio").addClass("active");
            }else{
                $(radio[i]).siblings(".beau-radio").removeClass("active");
            }
        }
    });

    function pay(){
        var sysType = $('input[name="sysType"]:checked').val();
        var smsType = $('input[name="smsType"]:checked').val();
        $.ajax({
            type : "POST",
            url : baseUrl + "storedetail/action/openSys",
            data : "sysType=" + sysType + "&smsType=" + smsType,
            dataType : "json",
            success : function(e){
                if (e.code != 0) {
                    dialog(e.msg);
                    return;
                }
                callWeixin(e.msg);
            }
        });
    }
    function callWeixin(rj) {
          WeixinJSBridge.invoke('getBrandWCPayRequest', {
              "appId" : rj.appId,
              "timeStamp" : rj.timeStamp,
              "nonceStr" : rj.nonceStr,
              "package" : rj.package,
              "signType" : rj.signType,
              "paySign" : rj.paySign
          }, function(res) {
              // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
              // 因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面
              if (res.err_msg == "get_brand_wcpay_request:ok") {
//              WeixinJSBridge.invoke('closeWindow', {}, function(e) {});
                  window.location.href = baseUrl + "storedetail/view/chargeInfo?storeId=${storeId}&type=1";
              }
          });
      }
</script>
</body>
</html>