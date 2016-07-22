<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>我的预约</title>
<link rel="stylesheet" href="<%=basePath%>css/mobile/shop.css" />
</head>
<style>
.my_order_content {
	margin-bottom: 1rem;
	height: 8.8rem;
	background: white;
	box-shadow: 0 2px 2px #ececec
}

.my_order_content>p {
	border-bottom: 1px solid #d2d2d2;
	padding-left: 1rem;
	font-size: 14px;
	height: 2.7rem;
	line-height: 2.7rem;
}

.my_order_content>p>span {
	float: right;
	margin-right: 1rem
}

.my_order_content_detail_text i {
	font-size: 12px;
	font-style: normal
}

.my_order_content>p>.sure {
	color: #1d65d2
}

.my_order_content_detail_img {
	margin-left: 0.8rem;
	float: left;
	width: 4.5rem;
	height: 4.5rem;
	background: #a0a0a0;
	border-radius: 4px
}

.my_order_content_detail_img img {
	width: 4.5rem;
	height: 4.5rem;
	border-radius: 4px
}

.my_order_content_detail_text {
	float: left;
	color: #777777;
	margin-left: 0.5rem
}

.my_order_content_detail_text>p {
	font-size: 14px;
	color: black
}

.my_order_content_detail_text>p>em {
	text-align: center;
	color: #d9531e;
	font-size: 15px
}

.my_order_content_detail_text div {
	margin-top: 5px
}

.my_order_content_detail {
	margin-top: 0.7rem;
}

.button {
	float: right;
	margin-right: 0.3rem
}

.button button {
	margin-top: 1rem;
	width: 6rem;
	height: 2.8rem;
	border: none;
	border-radius: 8px;
	text-align: center;
	line-height: 2.8rem;
	color: white;
	font-size: 14px;
}

.button .cancle {
	background: #a0a0a0
}

.button .again {
	background: #45254a
}

.my_order_content>p .al_cancle {
	color: #d95325
}

.zzc {
	display: none;
	position: fixed;
	top: 0px;
	height: 100%;
	left: 0px;
	width: 100%;
	background: rgba(23, 23, 23, 0.7);
	z-index: 1000;
}

.zzc_slide_content {
	position: absolute;
	bottom: -30%;
	left: 0;
	width: 100%
}

.zzc_slide_content ul {
	height: 10rem;
	line-height: 10rem;
	width: 100%;
	background: white
}

.zzc_slide_content ul li {
	width: 33.3%;
	float: left;
	text-align: center;
	height: 4.5rem;
}

.zzc_slide_content>p {
	color: white;
	font-size: 16px;
	height: 3.6rem;
	width: 100%;
	background: #45254a;
	text-align: center;
	line-height: 3.6rem
}

.zzc_slide_content ul li a {
	display: inline-block;
	width: 60%;
	height: 2.5rem;
	border-radius: 8px;
	line-height: 2.5rem;
	color: white;
}
</style>
<body>
	<div class="zzc">
		<div class="zzc_slide_content">
			<p>取消类型</p>
			<ul class="clearfix">
				<li>
					<a href="javascript:can('重新预约');" style="background: #e27474">重新预约</a>
				</li>
				<li>
					<a href="javascript:can('临时有事');" style="background: #2cba83">临时有事</a>
				</li>
				<li>
					<a href="javascript:can('其他考虑');" style="background: #59b5d9">其他考虑</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="con">
		<div class="my_order">
		<c:forEach items="${appointmentList }" var="appointment">
			<div class="my_order_content">
				<p>
					下单时间: ${appointment.createTime }
					<c:choose>
                           <c:when test="${appointment.appointmentStatus == 1 }">
                               <span class="sure">等待确认</span>
                           </c:when>
                           <c:when test="${appointment.appointmentStatus == 2 }">
                               <span class="sure">预约成功</span>
                           </c:when>
                           <c:when test="${appointment.appointmentStatus == 3 }">
                               <span class="sure">已服务</span>
                           </c:when>
                           <c:otherwise>
                               <span class="al_cancle">已取消</span>
                           </c:otherwise>
                    </c:choose>
				</p>
				<div class="my_order_content_detail clearfix">
					<div class="my_order_content_detail_img"><img src="<%=picPath%>${appointment.employeeInfo.headImage}"></div>
					<div class="my_order_content_detail_text">
						<p>
							<span>${appointment.employeeInfo.name}</span><em>(${appointment.employeeInfo.employeeCode })</em><i>${appointment.employeeInfo.levelName }</i>
						</p>
						<div>${appointment.category.categoryName}</div>
						<div>预约时间:${appointment.appointmentDate } ${appointment.appointmentTime }</div>
					</div>
					<div class="button">
					<c:choose>
                           <c:when test="${appointment.appointmentStatus == 1 }">
                              <button class="cancle" appointmentId='${appointment.appointmentId}' employeeId='${appointment.employeeInfo.employeeId }' appointmentTime='${appointment.appointmentDate } ${appointment.appointmentTime }'>取消预约</button>
                           </c:when>
                           <c:when test="${appointment.appointmentStatus == 2 }">
                               <button class="again" onclick="window.location.href='<%=basePath%>memberCenter/view/dateAppointment?employeeId=${appointment.employeeId }'">再次预约</button>
                           </c:when>
                           <c:when test="${appointment.appointmentStatus == 3 }">
                               <button class="again" onclick="window.location.href='<%=basePath%>memberCenter/view/dateAppointment?employeeId=${appointment.employeeId }'">再次预约</button>
                           </c:when>
                           <c:otherwise>
                               <button class="again" onclick="window.location.href='<%=basePath%>memberCenter/view/dateAppointment?employeeId=${appointment.employeeId }'">再次预约</button>
                           </c:otherwise>
                    </c:choose>
					</div>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>
	<%@include file="../memberBase.jsp"%>
</body>
<script>

	var appointmentId = null;
	var employeeId = null;
	var appointmentTime = null;
	
	//弹出
	$(function() {
		$('.button .cancle').on('touchstart', function() {
			appointmentId = $(this).attr("appointmentId");
			employeeId = $(this).attr("employeeId");
			appointmentTime = $(this).attr("appointmentTime");
			$('.zzc').fadeIn();
			$('body,html').attr('style', 'overflow:hidden');
			$('.zzc_slide_content').animate({
				bottom : 0
			}, 600)
		});
		//隐藏
		$('.zzc').on('touchstart', function(e) {
			if ($(tar).is('.zzc')) {
				$(this).fadeOut();
				$('body,html').attr('style', 'height:100%');
				$('.zzc_slide_content').attr('style', 'bottom:-30%');
			}
		});
	})
	
	function can(reason){
		var data = "appointmentId=" + appointmentId + "&employeeId=" + employeeId + "&appointmentTime=" + appointmentTime + "&reason=" + reason;
		console.log(data);
    	$.ajax({
    		url : baseUrl + "memberCenter/view/cancelAppointment",
    		type : "POST",
    		data : data,
    		success : function(e){
    			if (e.code != 0) {
                    dialog(e.msg);
                    return;
                }else {
                	alert("您已取消预约");
                	setTimeout('go()', 2000);
                }
    		}
    	});
	}
	function go(){
		window.location.href = baseUrl + "memberCenter/view/appointmentList/${session_key_store_account }/1";
	}
</script>
</html>