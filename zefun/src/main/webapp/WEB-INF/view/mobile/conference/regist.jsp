<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>会议报名情况</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/weui.min.css?date=<%=new Date().getTime() %>"/>
  </head>
<body class="gray-bg">
<div class="wrap">
<!--会议缴费情况-->
<div class="huiyi-rc">
    <div class="huiyi-rc-head">
        <ul class="huiyi-tab">
            <li class="current" data-target="hy1">已缴费 <span class="fs">(${fn:length(hasPay) })</span></li>
            <li data-target="hy2">未缴费<span class="fs">(${fn:length(noPay) })</span></li>
        </ul>
    </div>
    <div class="huiyi-rc-body">
        <!--已缴费-->
        <ul class="huiyi-rc-main" id="hy1">
        <c:forEach items="${hasPay }" var="hasPay">
        	<li>
                <div class="fl">
                    <p class="mendian">
                    <c:choose>
                    <c:when test="${empty hasPay.storeType}"><span class="hy-dian hy-dian-c2">单</span></c:when>
                    <c:when test="${hasPay.storeType == 1 }"><span class="hy-dian hy-dian-c2">单</span></c:when>
                    <c:when test="${hasPay.storeType == 2 }"><span class="hy-dian hy-dian-c1">总</span></c:when>
                    <c:when test="${hasPay.storeType == 3 }"><span class="hy-dian hy-dian-c1">分</span></c:when>
                    </c:choose>
					   ${hasPay.storeName }</p>
                    <p class="gray-999 hy-pl"><span>${hasPay.name }</span> <span class="showActionSheet" phone="${hasPay.phone }">${hasPay.phone }</span></p>
                </div>
                <div class="fr hy-qiandao showActionSheet" phone="${hasPay.phone }">
                    <span class="iconfont icon-iconfontdizhi-copy"></span>
                </div>
            </li>
        </c:forEach>
        </ul >
        <!--未缴费-->
        <ul class="huiyi-rc-main hide" id="hy2">
        <c:forEach items="${noPay }" var="noPay">
        	<li>
                <div class="fl">
                    <p class="mendian">
                    <c:choose>
                    <c:when test="${empty noPay.storeType}"><span class="hy-dian hy-dian-c2">单</span></c:when>
                    <c:when test="${noPay.storeType == 1 }"><span class="hy-dian hy-dian-c2">单</span></c:when>
                    <c:when test="${noPay.storeType == 2 }"><span class="hy-dian hy-dian-c1">总</span></c:when>
                    <c:when test="${noPay.storeType == 3 }"><span class="hy-dian hy-dian-c1">分</span></c:when>
                    </c:choose>
					   ${noPay.storeName }</p>
                    <p class="gray-999 hy-pl"><span>${noPay.name }</span> <span class="showActionSheet" phone="${noPay.phone }">${noPay.phone }</span></p>
                </div>
                <div class="fr hy-qiandao qiandao-co1" phone="${noPay.phone }">
                    <span class="iconfont icon-iconfontdizhi-copy"></span>
                </div>
            </li>
        </c:forEach>
        </ul>
    </div>
</div>
<!--电话弹出-->
<div id="actionSheet_wrap" class="hide s-modal-miss" >
    <div class="weui_mask_transition weui_fade_toggle" id="mask" style="display: block;"></div>
    <div class="weui_actionsheet weui_actionsheet_toggle" id="weui_actionsheet">
        <div class="weui_actionsheet_menu">
            <div class="weui_actionsheet_cell gray-999"><a name="linkphone" href="tel:13764567708"></a></div>
        </div>
        <div class="weui_actionsheet_action">
            <div class="weui_actionsheet_cell gray-999" id="actionsheet_cancel">取消</div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script src="<%=mobileBaseJsPath%>"></script>
<script>
    $(function(){

        $(".showActionSheet").on("click",function(){
        	var phone = $(this).attr("phone");
        	$("[name='linkphone']").eq(0).attr("href", "tel:" + phone).html("点击拨号：" + phone);
            $("#actionSheet_wrap").removeClass("hide")
        })
        $(".qiandao-co1").on("click",function(){
        	var phone = $(this).attr("phone");
        	$("[name='linkphone']").eq(0).attr("href", "tel:" + phone).html("点击拨号：" + phone);
            $("#actionSheet_wrap").removeClass("hide")
        })
        $(".s-modal-miss").on("click", function(){
            $("body").css("overflow-y","auto");
            $("#actionSheet_wrap").addClass("hide");
        })
        /*切换tab*/
        $(".huiyi-tab li").on("click",function(){
            $(this).addClass("current").siblings().removeClass("current")
            $(".huiyi-rc-main").addClass("hide")
            $("#" + $(this).attr("data-target")).removeClass("hide");
        })
    })
</script>

</div>
</body>

</html>