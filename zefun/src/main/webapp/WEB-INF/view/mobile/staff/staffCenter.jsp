<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
</head>
<body>
<div class="con"> 
	   
    <div class="person_">
	  <div class="head_pic">
	     <img src="<%=qiniuPath%>${employeeInfo.headImage }">
	   </div>
	   <div class="person_name">
	       <H1>${employeeInfo.name }<img src="<%=basePath%>images/mobile/newemployee/sex.png"></H1>
	       <p>${employeeInfo.levelName}</p>
		   <span onclick="initMap()">签到</span>
	   </div>
	 </div>
	  
	 <ul class="person_ul">
	   <a href="<%=basePath%>staff/view/staffAppoint/${session_key_store_account}/2/1">
		   <li>
		      <img src="<%=basePath%>images/mobile/newemployee/personalOne.png">我的预约
		   </li>
	    </a>
	    <a href="<%=basePath%>staff/view/staffEarning">
		    <li>
		         <img src="<%=basePath%>images/mobile/newemployee/personalTwo.png">我的业绩
		    </li>
	    </a>
	    <a href="<%=basePath%>staff/action/selectCommissionInfo">
		    <li>
		          <img src="<%=basePath%>images/mobile/newemployee/personalThree.png">我的提成
		    </li>
	    </a>
	    <a href="<%=basePath%>staff/view/individualPerformance">
		    <li>
		          <img src="<%=basePath%>images/mobile/newemployee/personalFour.png">我的表现
		    </li>
	    </a>
	    <a href="<%=basePath%>staff/view/myShiftMahjong">
		    <li>
		           <img src="<%=basePath%>images/mobile/newemployee/personalFive.png">我的轮牌
		    </li>
	    </a>
	    <a href="<%=basePath%>staff/view/selectViewScheduling">
		    <li>
		           <img src="<%=basePath%>images/mobile/newemployee/personalSix.png">我的排班
		    </li>
 	    </a>
 	     
	    <a href="<%=basePath%>staff/view/staffInfo">
	       <li>
	          <img src="<%=basePath%>images/mobile/newemployee/personalSeven.png">个人设置
	       </li>
	   </a>
     </ul>
   </div>

  
   <ul class="bottom_fix clearfix">
      <li ontouchstart = "chooseTypePage(1, '${session_key_store_account}')">
          <img src="<%=basePath%>images/mobile/newemployee/bottomOne.png">
	      <p>接待</p>
	  </li>
	  <li ontouchstart = "chooseTypePage(2, '${session_key_store_account}')">
          <img src="<%=basePath%>images/mobile/newemployee/bottomTwo.png">
          <p>订单</p>
	  </li>
	  <li ontouchstart = "chooseTypePage(3, '${session_key_store_account}')">
		    <img src="<%=basePath%>images/mobile/newemployee/bottomThree.png">
		    <p>排行</p>
	  </li>
	  <li ontouchstart = "chooseTypePage(4, '${session_key_store_account}')">
		     <img src="<%=basePath%>images/mobile/newemployee/bottomFour_.png">
		     <p>我的</p>
	  </li>
    </ul> 
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zxYZYzCtCT1lhiVOuxQeieOf"></script>
<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script type="text/javascript">

function initMap(){
	var now = new Date().getTime();
	$.ajax({
		url : baseUrl + "staff/action/signOperate",
		type : "POST",
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				$(globalSignObj).addClass("hide").siblings(".qiandao").removeClass("hide");
			}
			dialog(e.msg);
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

function tip(){
	dialog("暂未开放，敬请期待！");
}
</script>
</body>
</html>