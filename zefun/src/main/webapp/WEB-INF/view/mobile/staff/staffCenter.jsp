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
	   <li>
	      <a href="<%=basePath%>staff/view/staffAppoint/${session_key_store_account}/2/1">
	         <img src="<%=basePath%>images/mobile/newemployee/personalOne.png">我的预约
	      </a>
	   </li>
	   <li>
	      <a href="<%=basePath%>staff/view/staffEarning">
	         <img src="<%=basePath%>images/mobile/newemployee/personalTwo.png">我的业绩
	      </a>
	   </li>
	   <li>
	       <a href="<%=basePath%>staff/action/selectCommissionInfo">
	          <img src="<%=basePath%>images/mobile/newemployee/personalThree.png">我的提成
	       </a>
	   </li>
	   <li>
	       <a href="<%=basePath%>staff/view/individualPerformance">
	          <img src="<%=basePath%>images/mobile/newemployee/personalFour.png">我的表现
	       </a>
	   </li>
	   <li>
	       <a href="<%=basePath%>staff/view/myShiftMahjong">
	           <img src="<%=basePath%>images/mobile/newemployee/personalFive.png">我的轮牌
	       </a>
	   </li>
	   <li>
	       <a href="<%=basePath%>staff/view/selectViewScheduling">
	           <img src="<%=basePath%>images/mobile/newemployee/personalSix.png">我的排班</li>
	       </a>
	   <li>
	       <a href="<%=basePath%>staff/view/staffInfo">
	          <img src="<%=basePath%>images/mobile/newemployee/personalSeven.png">个人设置
	       </a>
	   </li>
     </ul>
   </div>

  
   <ul class="bottom_fix clearfix">
      <li>
          <a href="<%=basePath%>staff/view/reception">
	          <img src="<%=basePath%>images/mobile/newemployee/bottomOne.png">
		      <p>接待</p>
	      </a>
	  </li>
	  <li>
	     <a href="<%=basePath%>staff/view/employeeOrderView/${session_key_store_account}/2">
	        <img src="<%=basePath%>images/mobile/newemployee/bottomTwo.png">
	        <p>订单</p>
	     </a>
	  </li>
	  <li>
	    <a href="<%=basePath%>staff/view/allEarning">
		    <img src="<%=basePath%>images/mobile/newemployee/bottomThree.png">
		    <p>排行</p>
	    </a>
	  </li>
	  <li>
	     <a href="<%=basePath%>staff/view/staffCenter/${session_key_store_account}/2">
		     <img src="<%=basePath%>images/mobile/newemployee/bottomFour_.png">
		     <p>我的</p>
	     </a>
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

function tip(){
	dialog("暂未开放，敬请期待！");
}
</script>
</body>
</html>