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
    <title>会议入场情况</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/weui.min.css?date=<%=new Date().getTime() %>"/>
  </head>
<body class="gray-bg">
<div class="wrap">
<!--会议入场情况-->
<div class="huiyi-rc">
    <div class="huiyi-rc-head">
        <ul class="huiyi-tab">
            <li class="current" onclick="show(this,'one')">未入场 <span class="fs wrc">(${noAdmissionLength })</span></li>
            <li onclick="show(this,'two')">已入场 <span class="fs yrc">(${hasAdmissionLength })</span></li>
        </ul>
    </div>
    <div class="huiyi-rc-body">
        <!--未入场-->
        <ul class="huiyi-rc-main" id="one">
        <c:forEach items="${noAdmission }" var="noAdmission">
            <li>
                <div class="fl">
                   <p class="mendian">
                   <c:choose>
                   <c:when test="${noAdmission.storeType == 1 }"><span class="hy-dian hy-dian-c2">单</span></c:when>
                   <c:when test="${noAdmission.storeType == 2 }"><span class="hy-dian hy-dian-c1">总</span></c:when>
                   <c:when test="${noAdmission.storeType == 3 }"><span class="hy-dian hy-dian-c1">分</span></c:when>
                   </c:choose>
                   	${noAdmission.storeName }</p>
                   <p class="gray-999 hy-pl"><span>${noAdmission.name }</span> <span class="showActionSheet">${noAdmission.phone }</span></p>
                </div>
                <div class="fr hy-qiandao showToast" onclick="signIn(${noAdmission.personnelId }, this)">
                    <span class="iconfont icon-qiandao"></span>
                </div>
            </li>
        </c:forEach>
        </ul>
        <!--已入场-->
        <ul class="huiyi-rc-main hide" id="two">
        <c:forEach items="${hasAdmission }" var="hasAdmission">
            <li>
                <div class="fl">
                   <p class="mendian">
                   <c:choose>
                   <c:when test="${hasAdmission.storeType == 1 }"><span class="hy-dian hy-dian-c2">单</span></c:when>
                   <c:when test="${hasAdmission.storeType == 2 }"><span class="hy-dian hy-dian-c1">总</span></c:when>
                   <c:when test="${hasAdmission.storeType == 3 }"><span class="hy-dian hy-dian-c1">分</span></c:when>
                   </c:choose>
                   	${hasAdmission.storeName }</p>
                   <p class="gray-999 hy-pl"><span>${hasAdmission.name }</span> <span class="showActionSheet">${hasAdmission.phone }</span></p>
                </div>
                <div class="fr hy-qiandao qiandao-co1" onclick="signOut(${hasAdmission.personnelId }, this)">
                    <span class="iconfont icon-qiandao"></span>
                </div>
            </li>
        </c:forEach>
        </ul>
    </div>
</div>
<!--签到成功-->
<div id="toast" class="hide s-modal-miss" >
    <div class="weui_mask_transition weui_fade_toggle" id="mask" style="display: block;"></div>
    <div class="weui_toast">
        <i class="iconfont icon-qiandao"></i>
        <p class="weui_toast_content">签到成功</p>
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
    	$('body').delegate(".showActionSheet", "click", function(){
    		var phone = $(this).text();
        	$("[name='linkphone']").eq(0).attr("href", "tel:" + phone).html("点击拨号：" + phone);
            $("#actionSheet_wrap").removeClass("hide");
    	})

        $(".s-modal-miss").on("click", function(){
            $("body").css("overflow-y","auto");
            $("#toast").addClass("hide");
        })
        $(".s-modal-miss").on("click", function(){
            $("body").css("overflow-y","auto");
            $("#actionSheet_wrap").addClass("hide");
        })
    })
    
    //签到入场
    function signIn(personnelId, obj){
		$.ajax({
			type: "POST",
			url: baseUrl+"conference/action/admission",
	        data: "personnelId="+personnelId+"&isTurn=1",
	        dataType: "json",
	        beforeSend : function(){
	        },
	        complete : function(){
	        },
	        success: function(e) {
	        	if(e.code==0){
	        		$(obj).removeClass("showToast").addClass("qiandao-co1");
	            	var $li = $(obj).closest("li")
	            	$("#two").append($li.clone());
	            	$li.hide("normal");
	            	$(".weui_toast_content").text("签到成功");
	                $("#toast").removeClass("hide");
	                $(".yrc").text((Number($(".yrc").text().replace("(","").replace(")",""))+1));
	                $(".wrc").text((Number($(".wrc").text().replace("(","").replace(")",""))-1));
	        	}
	        }
		});
    }
    
    /**看错了,取消入场*/
   	function signOut(personnelId, obj){
   		$.ajax({
   			type: "POST",
   			url: baseUrl+"conference/action/admission",
   	        data: "personnelId="+personnelId+"&isTurn=0",
   	        dataType: "json",
   	        beforeSend : function(){
   	        },
   	        complete : function(){
   	        },
   	        success: function(e) {
   	        	if(e.code==0){
   	        		$(obj).removeClass("qiandao-co1").addClass("showToast");
   	        		var $li = $(obj).closest("li");
   	            	$("#one").append($li.clone());
   	            	$li.hide("normal");
   	            	$(".weui_toast_content").text("取消签到");
   	                $("#toast").removeClass("hide");
   	                $(".yrc").text((Number($(".yrc").text().replace("(","").replace(")",""))-1));
   	                $(".wrc").text((Number($(".wrc").text().replace("(","").replace(")",""))+1));
   	        	}
   	        }
   		});
   	}
</script>

</div>
</body>
<script type="text/javascript">
function show(obj, tabId){
	$(".huiyi-tab").children("li").removeClass("current");
	$(obj).addClass("current");
	$("#one").addClass("hide");
	$("#two").addClass("hide");
    $("#"+tabId).removeClass("hide");
}
</script>
</html>