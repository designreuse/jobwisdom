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
    <c:set var="pageTitle" value="系统续费" />
    <c:set var="chargeTitle" value="续费方式" />
    <c:if test="${businessType == 2 }">
    	<c:set var="pageTitle" value="短信购买" />
    	<c:set var="chargeTitle" value="短信套餐" />
    </c:if>
    <title>${pageTitle }</title>
</head>
<body class="gray-bg">
<div class="pay gray-bg">
    <ul class="list ">
        <li class="way way1">
            ${chargeTitle }
        </li>
        <c:forEach var="setting" items="${rechargeList }" varStatus="settingStatus">
	        <c:choose>
        		<c:when test="${settingStatus.first }">
        			<li class="way">
			            <div class="checker">
			                <div class="beau-radio active">
			                    <span class="iconfont icon-gou"></span>
			                </div>
			                <input type="radio" checked="checked" class="yellow-radio sys_type" name="productType" value="${setting.id }"/>
			            </div>
			            <div class="desc-wrap">
			                <span class="desc">${setting.description }</span>
			            </div>
			        </li>
        		</c:when>
        		<c:otherwise>
        			<li class="way">
			            <div class="checker">
			                <div class="beau-radio">
			                    <span class="iconfont icon-gou"></span>
			                </div>
			                <input type="radio" class="yellow-radio sys_type" name="productType" value="${setting.id }"/>
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
    $(".checker").on("click", function () {
        var radio = $(".yellow-radio");
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
    
    var type = 2;
    if ('${businessType}' == 2) {
    	type = 3;
    }

    function pay(){
        var productType = $('input[name="productType"]:checked').val();
        $.ajax({
            type : "POST",
            url : baseUrl + "storedetail/action/chargeSys",
            data : "businessType=${businessType}&productType=" + productType,
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
                  window.location.href = baseUrl + "storedetail/view/chargeInfo?storeId=${storeId}&type=" + type;
              }
          });
      }
</script>
</body>
</html>