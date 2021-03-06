<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>我的服务</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
    <style type="text/css">
        .server{width:90%;background:white;margin:1rem auto;height:5.6rem;padding-top:1rem;border-radius:6px;box-shadow:0 0 8px #d5d4d4}
		.server_img{float:left;width:58px;height:58px;border-radius:29px;overflow:hidden;margin:0 0.4rem 0 1rem}
		.server_img img{width:58px;height:58px;border-radius:29px}
		.server_text{float:left;line-height:1.6rem}
		.server_text>p{font-size:14px;color:black}
		.server_text>p img{width:26px;vertical-align:middle;margin-left:3px}
		.server_button{float:right;margin:1.5rem 1rem 0 0 }
		.server_button button{border:none;background:#ed5f19;color:white;font-size:14px;text-align:center;width:5rem;height:2rem;line-height:2rem;border-radius:4px;}
        body{background:white;text-align: center}
    	.emptyImg {display:inline-block;width:80%;}
    	.empty{padding-top:30%}
    </style>
  </head>
  
    <body>
        <c:choose>
            <c:when test="${fn:length(mapList) > 0 }">
               <div class="con"> 
			         <c:forEach items="${mapList}" var="map">
			              <div class="server clearfix">
							 <div class="server_img">
							   <img src="<%=qiniuPath %>${map.memberBaseDto.headUrl }">
							 </div>
							 <div class="server_text">
							  <p>顾客：${map.memberBaseDto.name }<c:if test="${map.memberBaseDto.sex == '男'}"><img src="<%=basePath%>images/mobile/newemployee/sex.png"></c:if> <c:if test="${map.memberBaseDto.sex == '女'}"><img src="<%=basePath%>images/mobile/newemployee/sex_woman.png"></c:if></p>
							  <div>${map.shiftMahjongName }</div>
							  <div>开始时间:${fn:substring(map.beginTime, 11, 16)}</div>
							 </div>
							 <div class="server_button">
							   <button onclick = "overServerEmployee(${map.shiftMahjongStepId})">结束</button>
							 </div>
						  </div>
			         </c:forEach>
				 </div>
            </c:when>
            <c:otherwise>
               <div class="empty">
			     <img class = "emptyImg" src="<%=qiniuPath %>system/profile/order_empty_1.png">
			   </div> 
            </c:otherwise>
        </c:choose>
	    <div style="height:6rem"></div>
	    <ul class="bottom_fix clearfix">
	      <li ontouchstart = "chooseTypePage(1, '${session_key_store_account}')">
	          <img src="<%=basePath%>images/mobile/newemployee/bottomOne.png">
		      <p>接待</p>
		  </li>
		  <li ontouchstart = "chooseTypePage(2, '${session_key_store_account}')">
	          <img src="<%=basePath%>images/mobile/newemployee/bottomTwo_.png">
	          <p>服务</p>
		  </li>
		  <li ontouchstart = "chooseTypePage(3, '${session_key_store_account}')">
			    <img src="<%=basePath%>images/mobile/newemployee/bottomThree.png">
			    <p>排行</p>
		  </li>
		  <li ontouchstart = "chooseTypePage(4, '${session_key_store_account}')">
			     <img src="<%=basePath%>images/mobile/newemployee/bottomFour.png">
			     <p>我的</p>
		  </li>
	    </ul> 
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script>
function overServerEmployee(shiftMahjongStepId) {
	jQuery.ajax({
    	url : baseUrl + "staff/action/overServerEmployee",
    	type : "POST",
    	data : "shiftMahjongStepId="+shiftMahjongStepId,
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		var datas = e.msg;
    		dialog("操作成功！");
    		location.reload();
    	}
    });
}

function chooseTypePage (type, storeId) {
	if (type == 1) {
		window.location.href = baseUrl+"staff/view/reception";
	}
	else if (type == 2) {
		window.location.href = baseUrl+"staff/view/employeeOrderView/"+storeId+"/2";
	}
	else if (type == 3) {
		window.location.href = baseUrl+"staff/view/allEarning";
	}
	else {
		window.location.href = baseUrl+"staff/view/staffCenter/"+storeId+"/2";
	}
}
</script>
</body>
</html>