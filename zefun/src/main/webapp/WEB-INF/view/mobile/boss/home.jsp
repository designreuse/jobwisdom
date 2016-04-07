<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta name="x5-orientation" content="portrait">
    <meta content="telephone=no" name="format-detection" />
    <meta name="msapplication-tap-highlight" content="no">
    <title>我的门店</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/boss-newer.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/chart-component.css">
    <link rel="stylesheet" href="<%=basePath%>css/mobile/business-report.css">
</head>
<body>
<div class="content">
	<div class="business-report chart-bg">
	    <div class="shop-top">
	        <div class="shop-login fl">
	            <img src="<%=picPath%>${storeInfo.storeLogo }" alt=""/>
	        </div>
	        <div class="shop-name fl">
	            <p class="shop-median">${storeInfo.storeName }</p>
	            <p class="shop-dizhi"><span class="iconfont icon-dizhi"></span>${storeInfo.storeAddress }</p>
	        </div>
	    </div>
	
	    <div class="shop-status">
	        <span class="shop-col">Hi, ${employeeInfo.name }</span>
	        <span class="shop-co2">${employeeInfo.levelName }</span>
	         <span >
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
	         </span>
	    </div>
	
	    <div class="shop-block">
	        <ul>
	            <li>
                    <div class="shop-a">
                        <a href="<%=basePath%>staff/view/staffAppoint/${session_key_store_id}/2/1">
                            <div class="block1">
                                <img src="<%=basePath%>images/mobile/boss-newer/block1.png" alt=""/>
                            </div>
                            <p >我的预约</p>
                        </a>
                    </div>
	            </li>
	
	            <li>
	                <div class="shop-a">
	                    <a href="<%=basePath%>staff/view/reception">
	                        <div class="block2">
	                            <img src="<%=basePath%>images/mobile/boss-newer/block2.png" alt=""/>
	                        </div>
	                        <p >手机开单</p>
	                    </a>
	                </div>
	            </li>
	
	            <li>
	                <div class="shop-a">
	                    <a href="<%=basePath%>staff/view/myShiftMahjong">
	                        <div class="block3">
	                            <img src="<%=basePath%>images/mobile/boss-newer/block3.png" alt=""/>
	                        </div>
	                        <p >我的轮牌</p>
	                    </a>
	                </div>
	            </li>
	
	            <li>
	                <div class="shop-a">
	                    <a href="<%=basePath%>staff/view/staffEarning">
	                        <div class="block4">
	                            <img src="<%=basePath%>images/mobile/boss-newer/block4.png" alt=""/>
	                        </div>
	                        <p >我的业绩</p>
	                    </a>
	                </div>
	            </li>
	
	            <li>
	                <div class="shop-a">
	                    <a href="<%=basePath%>staff/view/allEarning">
	                        <div class="block5">
	                            <img src="<%=basePath%>images/mobile/boss-newer/block5.png" alt=""/>
	                        </div>
	                        <p >业绩排行</p>
	                    </a>
	                </div>
	            </li>
	
	            <li>
	                <div class="shop-a">
	                    <a href="<%=basePath%>staff/action/selectCommissionInfo">
	                        <div class="block6">
	                            <img src="<%=basePath%>images/mobile/boss-newer/block6.png" alt=""/>
	                        </div>
	                        <p >提成明细</p>
	                    </a>
	                </div>
	            </li>
	
	
	        </ul>
	    </div>
	
	    <div class="shop-block pb1">
	    <ul>
	        <li>
	            <div class="shop-a">
	                <a href="<%=basePath%>staff/view/employeeOrderView/${session_key_store_id}/2">
	                    <div class="block5">
	                        <img src="<%=basePath%>images/mobile/boss-newer/block7.png" alt=""/>
	                    </div>
	                    <p>我的订单</p>
	                </a>
	            </div>
	        </li>
	
	        <li>
	            <div class="shop-a">
	                <a href="<%=basePath%>boss/view/coutomer/${session_key_store_id}/2">
	                    <div class="block3">
	                        <img src="<%=basePath%>images/mobile/boss-newer/block8.png" alt=""/>
	                    </div>
	                    <p >客情分析</p>
	                </a>
	            </div>
	        </li>
	
	        <li>
	            <div class="shop-a">
	                <a href="<%=basePath%>boss/view/business/${session_key_store_id}/2">
	                    <div class="block2">
	                        <img src="<%=basePath%>images/mobile/boss-newer/block9.png" alt=""/>
	                    </div>
	                    <p >营业分析</p>
	                </a>
	            </div>
	        </li>
	
	        <li>
	            <div class="shop-a">
	                <a href="<%=basePath%>boss/view/bossObjective/${session_key_store_id}/2">
	                    <div class="block4">
	                        <img src="<%=basePath%>images/mobile/boss-newer/block10.png" alt=""/>
	                    </div>
	                    <p >业务报表</p>
	                </a>
	            </div>
	        </li>
	
	        <li>
	            <div class="shop-a">
	                <a href="<%=basePath%>boss/view/employeeCommissionHome/${session_key_store_id}/2">
	                    <div class="block1">
	                        <img src="<%=basePath%>images/mobile/boss-newer/block11.png" alt=""/>
	                    </div>
	                    <p >员工表现</p>
	                </a>
	            </div>
	        </li>
	
	        <!-- <li>
	            <div class="shop-a">
	                <a href="#">
	                    <div class="block6">
	                        <img src="<%=basePath%>images/mobile/boss-newer/block12.png" alt=""/>
	                    </div>
	                    <p >友宝销量</p>
	                </a>
	            </div>
	        </li> -->
	    </ul>
	</div>
	
	</div>
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript">
//加载时弹出框效果
function sign(obj){
	$.ajax({
		url : baseUrl + "staff/action/signOperate",
		type : "POST",
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				$(obj).addClass("hide").siblings(".qiandao").removeClass("hide");
			}
			dialog(e.msg);
		}
	});
}
</script>
</body>
</html>