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
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
    <link rel="stylesheet" type="text/css" href="style/shop.css">
</head>
<body>
<div class="wrap">
<div class="content mb5">
	<div class="yg-person">
	    <div class="person-info-login yg-member-info">
	        <a href="<%=basePath%>staff/view/staffInfo">
		        <img src="<%=picPath%>${employeeInfo.headImage}?imageView2/1/w/230/h/230" alt="" class="person-img"/>
		        <div class="info">
		            <div class="name">${employeeInfo.name }</div>
		            <div class="font-white">${employeeInfo.levelName } </div>
		        </div>
	        </a>
	        <c:choose>
	           <c:when test="${empty signStatus }">
	               <img src="<%=basePath%>images/mobile/employee/qiandao.png" onclick="sign(this);" class="qiandao"/>
	               <img src="<%=basePath%>images/mobile/employee/signout.png" onclick="sign(this);" class="qiandao hide"/>
	           </c:when>
	           <c:when test="${signStatus == '1' }">
                   <img src="<%=basePath%>images/mobile/employee/signout.png" onclick="sign(this);" class="qiandao"/>
                   <img src="<%=basePath%>images/mobile/employee/yiqiantu.png" onclick="dialog('您今日已完成签到、签退操作！')" class="qiandao hide"/>
	           </c:when>
	           <c:otherwise>
	               <img src="<%=basePath%>images/mobile/employee/yiqiantu.png" onclick="dialog('您今日已完成签到、签退操作！')" class="qiandao"/>
	           </c:otherwise>
	        </c:choose>
	    </div>
	
	    <div class="function-list-wrap mt2">
	        <ul class="function-list">
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/staffAppoint/${session_key_store_account}/2/1">
	                    <span><i class="iconfont icon-iconfontreneps"></i> <span class="ml">我的预约</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/staffEarning">
	                    <span><i class="iconfont icon-yejitongji"></i> <span class="ml">我的业绩</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li ">
	                <a href="<%=basePath%>staff/action/selectCommissionInfo">
	                    <span><i class="iconfont icon-yejitongji2"></i> <span class="ml">我的提成</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/individualPerformance">
	                    <span><i class="iconfont icon-iconfontwodebiaoxian"></i> <span class="ml">我的表现</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/myShiftMahjong">
	                    <span><i class="iconfont icon-quanbulunpai"></i> <span class="ml">我的轮牌</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	        </ul>
	        
	        <ul class="function-list">
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/staffInfo">
	                    <span><i class="iconfont icon-ordinaryset"></i> <span class="ml">个人设置</span></span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	        </ul>
	
	    </div>
	</div>
	
	<div class="footer">
        <ul>
            <li >
                <a href="<%=basePath%>staff/view/reception">
                    <span class="iconfont icon-jiedai"></span>
                    <span>接待</span>
                </a>
            </li>
            <li>
                <a href="<%=basePath%>staff/view/employeeOrderView/${session_key_store_account}/2">
                    <span class="iconfont icon-dingdan"></span>
                    <span>订单</span>
                </a>
            </li>
            <li >
                <a href="<%=basePath%>staff/view/allEarning">
                    <span class="iconfont icon-yejipaihang02"></span>
                    <span>排行</span>
                </a>
            </li>
            <li class="active">
                <a href="<%=basePath%>staff/view/staffCenter/${session_key_store_account}/2">
                    <span class="iconfont icon-wode"></span>
                    <span>我的</span>
                </a>
            </li>
        </ul>
    </div>
</div>
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zxYZYzCtCT1lhiVOuxQeieOf"></script>
<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script type="text/javascript">
var globalGPSLongitude;
var globalGPSLatitude;
var globalBaiDuLongitude;
var globalBaiDuLatitude;
var globalSignObj;

function sign(obj){
	if (!navigator.geolocation) {
		dialog("设备不支持定位!");
    	return;
	}
	globalSignObj = obj;
	
	navigator.geolocation.getCurrentPosition(function(position){
		
		globalGPSLongitude = position.coords.longitude;
		globalGPSLatitude  = position.coords.latitude;
		
		if (globalGPSLongitude == null || globalGPSLatitude == null) {
			dialog("GPS定位失败，请重试");
			return;
		}
		
	    BMap.Convertor.translate(new BMap.Point(globalGPSLongitude, globalGPSLatitude), 0, initMap); //转换坐标
	});
}

function initMap(point){
	globalBaiDuLatitude = point.lat;
    globalBaiDuLongitude = point.lng;
    console.log("BaiDu X : " + globalBaiDuLatitude);
    console.log("BaiDu Y : " + globalBaiDuLongitude);
	var now = new Date().getTime();
	$.ajax({
		url : baseUrl + "staff/action/signOperate",
		type : "POST",
		data : {longitude : globalBaiDuLongitude, latitude : globalBaiDuLatitude, date : now},
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