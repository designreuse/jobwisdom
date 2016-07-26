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
    <style>
	.person_center{color:white;font-size:18px;text-align:center;line-height:3rem;height:3rem;position:absolute;top:0;left:0;width:100%;background:rgba(67,42,20,0.5)}  
	.person_name{position:relative}
    .person_name em{color:white;display:inline-block;margin-right:5px}
	.person_{background-size:100% 100%}
	.person_ul li span{float:right;}
	.person_ul li span img{width:12px}
	
	.zzc{position: fixed;top: 0px;height:100%;left: 0px;width: 100%;background: rgba(102, 108, 121, 0.8);z-index:2000;}
	.zzc .select_alert{width:80%;height:13rem;background:white;margin:40% auto;border-radius:8px;overflow:hidden}
	.zzc .select_alert>p{font-size:15px;height:2.6rem;line-height:2.6rem;color:white;text-align:center;color:white;background:#eb6100}
	.zzc ul li{line-height:3rem;width:33.3%;float:left;height:3rem;text-align:center}
	.zzc ul li span{width:90%;border-radius:4px;;border:1px solid #f19149;display:inline-block;height:2.2rem;line-height:2.2rem;}
	.zzc ul{margin-top:0.8rem;border-bottom:1px solid #fbdfcc;padding-bottom:5px}
	.select_alert_button{text-align:center;margin-top:0.5rem}
	.select_alert_button button{font-size:14px;;margin:0 1rem;width:5rem;height:2.2rem;line-height:2.2rem;text-align:center;border-radius:4px;border:none}
	.active{background:#eb6100;color:white}
	</style>
</head>
<body>
<div class="con"> 
	   
    <div class="person_">
	  <div class="head_pic">
	     <img src="<%=qiniuPath%>${employeeInfo.headImage }">
	   </div>
	   <div class="person_name">
	       <%-- <H1>${employeeInfo.name }<img src="<%=basePath%>images/mobile/newemployee/sex.png"></H1>
	       <p>${employeeInfo.levelName}</p>
		   <span onclick="initMap()">上牌</span> --%>
		   
		   <H1>${employeeInfo.name }<img src="<%=basePath%>images/mobile/newemployee/sex.png"></H1>
	       <p><em>${employeeInfo.storeName}</em>${employeeInfo.levelName}</p>
	   </div>
	   <!-- <span>上牌</span> -->
	 </div>
	  
	 <ul class="person_ul">
	   <a href="<%=basePath%>staff/view/staffAppoint/${session_key_store_account}/2/1">
		   <li>
		      <img src="<%=basePath%>images/mobile/newemployee/personalOne.png">我的预约<span><img src="<%=basePath%>images/mobile/newemployee/right.png"></span>
		   </li>
	    </a>
	    <a href="<%=basePath%>staff/view/staffEarning">
		    <li>
		         <img src="<%=basePath%>images/mobile/newemployee/personalTwo.png">我的业绩<span><img src="<%=basePath%>images/mobile/newemployee/right.png"></span>
		    </li>
	    </a>
	    <a href="<%=basePath%>staff/action/selectCommissionInfo">
		    <li>
		          <img src="<%=basePath%>images/mobile/newemployee/personalThree.png">我的提成<span><img src="<%=basePath%>images/mobile/newemployee/right.png"></span>
		    </li>
	    </a>
	    <a href="<%=basePath%>staff/view/individualPerformance">
		    <li>
		          <img src="<%=basePath%>images/mobile/newemployee/personalFour.png">我的表现<span><img src="<%=basePath%>images/mobile/newemployee/right.png"></span>
		    </li>
	    </a>
	    <a href="<%=basePath%>staff/view/myShiftMahjong">
		    <li>
		           <img src="<%=basePath%>images/mobile/newemployee/personalFive.png">我的轮牌<span><img src="<%=basePath%>images/mobile/newemployee/right.png"></span>
		    </li>
	    </a>
	    <a href="<%=basePath%>staff/view/selectViewScheduling">
		    <li>
		           <img src="<%=basePath%>images/mobile/newemployee/personalSix.png">我的排班<span><img src="<%=basePath%>images/mobile/newemployee/right.png"></span>
		    </li>
 	    </a>
 	     
	    <a href="<%=basePath%>staff/view/staffInfo">
	       <li>
	          <img src="<%=basePath%>images/mobile/newemployee/personalSeven.png">个人设置<span><img src="<%=basePath%>images/mobile/newemployee/right.png"></span>
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
          <p>服务</p>
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