<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <meta http-equiv="pragma" content="no-cache">
    <title>预约时间</title>
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
    <style type="text/css">
    	.content{
    		height: auto;
    	}
    	.touch_{display:inline-block;width:78%;overflow:overlay;position:relative;top:14px;left:10px}
    	.normal-li{font-size:14px}
    	.touch{display:inline-block;}
    	.touch em{display:inline-block;width:140px;text-align:center;height:34px;border:1px solid #cfcfcf;border-radius:8px;text-align:center;line-height:34px;margin-left:8px}
    	.green{color:white;background:#26cbc5}
    </style>
  
  </head>
<body>

<div class="content wrap">
	<div class="coupon-future">
	    <div class="swiper-container-daohang tab">
	        <div class="swiper-wrapper">
	           <c:forEach items="${dateList }" var="d">
	                <div date="${d.date }" class="swiper-slide score-shop-li">
	                    <img src="<%=basePath%>images/mobile/member/active-new.png" class="hide"/>
	                    <div class="tab-word">
	                        <div class="zhouyi ">${d.date }</div>
	                        <div class="medium-font">周${d.weekName }</div>
	                    </div>
	                </div>
	           </c:forEach>
	        </div>
	    </div>
	
	    <div class="list-warp-score" style="padding-top: 9rem">
	        <ul>
	            <li class="normal-li first-li" >
	                <span class="normoal-word">服务项目</span>
	                <span class="fr">${projectName }</span>
	                <i class="touch_">
		                <span class="touch">
			                <c:forEach items="${projectCategories }" var="projectCategorie">
			                	<em onclick="projectId=${projectCategorie.categoryId };" value="${projectCategorie.categoryId }">${projectCategorie.categoryName }</em>
		                	</c:forEach>
	               		</span>
               		</i>
	            </li>
	            <li class="normal-li">
	                <span class="normoal-word">${levelName }</span>
	                <span class="fr">${employeeName }</span>
	            </li>
	        </ul>
	    </div>
	    <div>
	       <c:forEach items="${dateList }" var="d">
	           <div date="${ d.date}" class="appointDate hide pb7">
	           	   <ul class="hint-ul">
		             <li class="hint-li">
		                <span class="dep-word medium-font">*半小时内恕不接受预约，请您直接到店，尽情谅解</span>
		             </li>
		           </ul>
		           <div class="appointment-item">
		               <c:forEach items="${d.timeList }" var="t" varStatus="s">
		                   <c:if test="${(s.index + 1) % 3 == 1 }">
		                       <div class="time-line">
		                   </c:if>
		                   <div class="item-time-wrap">
		                       <c:set var="time" value="" />
		                       <c:set var="status" value="" />
		                       <c:forEach items="${t }" var="tt">
		                           <c:set var="time" value="${tt.key }" />
		                           <c:set var="status" value="${tt.value }" />
		                       </c:forEach>
		                       <c:choose>
		                           <c:when test="${status == 1 }">
		                               <div class="item-time appoint-time bg-white">${time }</div>
		                           </c:when>
		                           <c:otherwise>
		                               <div class="item-time bg-gray">${time }</div>
		                           </c:otherwise>
		                       </c:choose>
		                     </div>
		                   <c:if test="${(s.index + 1) % 3 == 0 || s.last }">
			                       <div class="clearfix"></div>
			                   </div>
		                   </c:if>
		               </c:forEach>
		          </div>
		       </div>   
	       </c:forEach>
	  </div>
	</div>
	
	<div class="foot-confirm">
	    <div class="info">
	        <div class="medium-font">
	             	预约时间
	        </div>
	        <div class="yellow-word bold-font"><span id="selectTime">请选择</span></div>
	    </div>
	    <a href="javascript:appoint();" class="foot-confirm-btn">确定预约</a>
	</div>
</div>   
<%@include file="../memberBase.jsp" %>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript">
    var date = $(".appointDate:first").attr("date");
    var projectId = null;
    var time = "";
    $(function(){
        var swiper = new Swiper('.swiper-container-daohang', {
            pagination: '.swiper-pagination',
            slidesPerView: 3,
            paginationClickable: true,
            spaceBetween: 0,
            freeMode: true
        });
    	
    	//默认显示第一天的时间段
        $(".appointDate:first").removeClass("hide");
        $(".swiper-slide:first").addClass("active").find("img").removeClass("hide");

        /*导航滑动选中状态*/
        $('.swiper-slide').click(function(){
            $(".swiper-slide").removeClass("active");
            $(".swiper-slide img").addClass("hide");
            $(this).addClass("active");
            $(this).find("img").removeClass("hide");
            date = $(this).attr("date")
            $(".appointDate[date='"+date+"']").removeClass("hide").siblings().addClass("hide");
        });
        
        $('.appoint-time').click(function(){
            time = $(this).html();
            $("#selectTime").html(date + " " + time);
            
            $(".bg-blue").removeClass("bg-blue").addClass("bg-white");
            $(this).removeClass("bg-white").addClass("bg-blue");
        });
    });
    
    var data = "employeeId=${employeeId}&mainStoreId=${mainStoreId}&projectId="+projectId;
    var year = new Date().getFullYear();
    function appoint(){
    	if (isEmpty(time)) {
    		dialog("请选择预约时间");
    		return;
    	}
    	if (isEmpty(projectId)) {
    		dialog("请选择预约大项");
    		return;
    	}
    	data = "employeeId=${employeeId}&mainStoreId=${mainStoreId}&projectId="+projectId;
    	$.ajax({
    		url : baseUrl + "memberCenter/action/orderAppointment",
    		type : "POST",
    		data : data + "&appointDate=" + year + "-" + date.replace("/", "-") + "&appointTime=" + time,
    		success : function(e){
    			if (e.code != 0) {
    				dialog(e.msg);
    			}
    			window.location.href = baseUrl + "memberCenter/view/appointmentList/${session_key_store_account}/1";
    		}
    	});
    }
</script>
  <script>
      $(function(){
    	var wid= $('.touch em').width()+16,count=$('.touch em').size();
    	 $('.touch').css('width',wid*count) 
    	  
    	  
      })
    
     $(function(){
    	$('.touch em').click(function(){
    		$(this).addClass('green').siblings().removeClass('green');	
    		
    	})
 	  
      })
      //刷新缓存
      window.onload = function(){  
        var url = window.location.href;  
        var ps = url.split("#");  
        try{  
            if(ps[1] != 1){  
                url += "#1";  
            }else{  
                window.location = ps[0];  
            }  
        }catch(ex){  
            url += "#1";  
        }  
        window.location.replace(url);  
    };  
    </script> 
</body>
</html>