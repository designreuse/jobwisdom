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
    <title>我的预约</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
    <style type="text/css">
        .emptyImg {display:inline-block;width:80%;}
    	.empty{padding-top:30%;text-align: center;}
    </style>
</head>
<body>
  <div class="con"> 
    <div class="myForward">
	    <ul>
	     <li class="active">新预约</li>
	   	 <li>已接受</li>
		 <li>已拒绝</li> 
	   </ul>
    </div>
    <div class="myForward_content_tab">
    
	   <div class="myForward_content_tab_">  
	      <c:choose>
            <c:when test="${fn:length(appointmentList1) > 0 }">
               <c:forEach items="${appointmentList1 }" var="appointment">
		         <div class="myForward_content" id="${appointment.appointmentId}">
			        <p>订单时间：${appointment.createTime }</p>
			        <div class="myForward_content_detail clearfix">
					  <div class="myForward_content_left">
					    <img src="<%=picPath%>${appointment.memberInfo.headUrl }"> 
					  </div>
					  <div class="myForward_content_center">
					     <p>${appointment.memberInfo.name}</p>
						 <span>${appointment.appointmentDate } ${appointment.appointmentTime }</span>
					  </div>
					  <div class="myForward_content_right">
			             ${appointment.category.categoryName}
			          </div>		  
					</div>
				    <div class="forward_ clearfix">	
						<span class="refuse" ontouchstart = "cancel(2, '${appointment.appointmentId}', '${appointment.memberId}')">拒绝预约</span>
						<span class="accept" ontouchstart = "agreeTip(1, '${appointment.appointmentId}', '${appointment.memberId}')">接受预约</span>  
				    </div>	
				 </div> 
		      </c:forEach>
            </c:when>
            <c:otherwise>
               <div class="empty">
			     <img class = "emptyImg" src="<%=qiniuPath %>system/profile/order_empty_1.png">
			   </div> 
            </c:otherwise>
         </c:choose>
	   </div>

	   <div class="myForward_content_tab_">  
	       <c:choose>
            <c:when test="${fn:length(appointmentList2) > 0 }">
               <c:forEach items="${appointmentList2 }" var="appointment">
		           <div class="myForward_content" id="${appointment.appointmentId}">
				        <p>订单时间：${appointment.createTime }</p>
				        <div class="myForward_content_detail clearfix">
						  <div class="myForward_content_left">
						    <img src="<%=picPath%>${appointment.memberInfo.headUrl }"> 
						  </div>
						  <div class="myForward_content_center">
						     <p>${appointment.memberInfo.name}</p>
							 <span>${appointment.appointmentDate } ${appointment.appointmentTime }</span>
						  </div>
						  <div class="myForward_content_right">${appointment.category.categoryName}</div>		  
						</div>	
				  </div>
		       </c:forEach>
            </c:when>
            <c:otherwise>
               <div class="empty">
			     <img class = "emptyImg" src="<%=qiniuPath %>system/profile/order_empty_1.png">
			   </div> 
            </c:otherwise>
           </c:choose>
		 </div> 

	     <div class="myForward_content_tab_">  
		     <c:choose>
	            <c:when test="${fn:length(appointmentList3) > 0 }">
	               <c:forEach items="${appointmentList3 }" var="appointment">
			          <div class="myForward_content" id="${appointment.appointmentId}">
				       <p>订单时间：${appointment.createTime }</p>
				        <div class="myForward_content_detail clearfix">
						  <div class="myForward_content_left">
						    <img src="<%=picPath%>${appointment.memberInfo.headUrl }"> 
						  </div>
						  <div class="myForward_content_center">
						     <p>${appointment.memberInfo.name}</p>
							 <span>${appointment.appointmentDate } ${appointment.appointmentTime }</span>
						  </div>
						  <div class="myForward_content_right">${appointment.category.categoryName}</div>		  
						</div>	
					   <div class="forward_ clearfix">	
				         <div class="cancle"> 
				             <c:choose>
							    <c:when test="${appointment.appointmentStatus == 4 }">
					        		顾客取消：${appointment.cancelReason }
							    </c:when>
							    <c:otherwise>
							                     拒绝预约：${appointment.cancelReason }
							    </c:otherwise>
							 </c:choose>		 
						 </div> 
					   </div>	
					  </div>
			     </c:forEach>
	            </c:when>
	            <c:otherwise>
	               <div class="empty">
				     <img class = "emptyImg" src="<%=qiniuPath %>system/profile/order_empty_1.png">
				   </div> 
	            </c:otherwise>
	           </c:choose>
		 </div> 	 
	</div> 
   </div> 
    
    
    <div class="mask">
	  <div class="accept_alert">
	    <p>接受预约</p>
		<div class="accept_alert_content">
		  <p>如果您接受了此次预约，请调安排好个人时间提供服务</p>
		  <div class="accept_if">
		     <button class="no">暂不接受</button>
		     <button style="background:#ea631a" ontouchstart = "operate()">接受预约</button>
		  </div>
		</div>
	  </div>
	</div>
	
	<div class="mask_1">
	  <div class="accept_alert">
	    <p>拒绝预约</p>
		<div class="accept_alert_content">
		  <p><input type="text" name="reason" placeholder="请输入取消原因"/></p>
		  <div class="accept_if">
		     <button class="sure" ontouchstart = "operate()">确认拒绝</button>
		     <button style="background:#ea631a">暂不拒绝</button>
		  </div>
		</div>
	  </div>
	</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script type="text/javascript" >
var type = parseInt("${type}");

jQuery(function(){
	 jQuery('.myForward_content_tab_:gt(0)').hide();
	 jQuery('.myForward li').on('touchstart',function(){
	      jQuery(this).addClass('active').siblings().removeClass('active');
		  jQuery('.myForward_content_tab .myForward_content_tab_').eq($(this).index()).show().siblings().hide()
	   
	 });
	   jQuery('.no').on('touchstart',function(){
	      jQuery('.mask').fadeOut() 
	   })
	   jQuery('.sure').on('touchstart',function(){
	      jQuery('.mask_1').fadeOut() 
	   })
})

var type, appointmentId, memberId, projectId;

function agreeTip(t, a, m){
    type = t;
    appointmentId = a;
    memberId = m;
    jQuery('.mask').fadeIn()
}

function cancel(t, a, m){
    type = t;
    appointmentId = a;
    memberId = m;
    jQuery('.mask_1').fadeIn()
}

function operate(){
	var at = $("#" + appointmentId);
    var reason = jQuery("input[name='reason']").val();
    if (type == 2 && isEmpty(reason)) {
        dialog("请填写取消原因");
        return;
    }
    var data = "type=" + type + "&appointmentId=" + appointmentId + "&memberId=" + memberId + "&reason=" + reason;
    $.ajax({
        url : baseUrl + "staff/action/appointOperate",
        type : "POST",
        data : data,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            if (type == 1) {
            	at.find(".forward_").remove();
            	$(".myForward_content_tab_").eq(1).find(".empty").remove();
                $(".myForward_content_tab_").eq(1).prepend(at.prop("outerHTML"));
                jQuery(".myForward").find("li").eq(1).addClass('active').siblings().removeClass('active');
        		jQuery('.myForward_content_tab .myForward_content_tab_').eq(1).show().siblings().hide();
                jQuery('.mask').fadeOut() 
            }
            else {
            	at.find(".forward_").empty();
            	at.find(".forward_").append('<div class="cancle">拒绝预约：'+reason+'</div> ');
            	$(".myForward_content_tab_").eq(2).find(".empty").remove();
            	$(".myForward_content_tab_").eq(2).prepend(at.prop("outerHTML"));
                jQuery(".myForward").find("li").eq(2).addClass('active').siblings().removeClass('active');
        		jQuery('.myForward_content_tab .myForward_content_tab_').eq(2).show().siblings().hide();
                jQuery('.mask_1').fadeOut()
            }
            at.remove();
        }
    }); 
}
</script>
</body>
</html>