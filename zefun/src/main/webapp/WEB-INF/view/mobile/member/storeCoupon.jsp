<!DOCTYPE html>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>门店优惠券</title>
<link rel="stylesheet" href="<%=basePath%>css/mobile/shop.css?date=<%=new Date() %>" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobile/style.css">
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/mobile/weui.min.css">
</head>
<body>

	<div id="toast" style="display: none;">
	    <div class="weui_mask_transparent"></div>
	    <div class="weui_toast" style="height: 7.6rem;">
	        <i class="weui_icon_toast" style="margin: 5px 0 0;"></i>
	        <p class="weui_toast_content">兑换成功</p>
	    </div>
	</div>

	<div class="mask">
      <div class="coupon_exchange">
	     <p>使用积分兑换优惠券<img src="<%=basePath%>images/mobile/member/coupon_close.png" onclick="$('.mask').hide()" ></p>
		 <div class="coupon_exchange_content">
		   <p>兑现<input type="number" name="num" >张=<em>0</em>积分</p>
		   <p><button onclick="convert()">确认</button></p>
		   <div class="saying">温馨提示：本优惠劵于2016/12/12日过期</div>
		 </div>
	  </div>
   </div>
   
   <div class="con"> 
	   <ul class="clearfix coupon">
         <a href="<%=basePath %>memberCenter/view/memberCoupon/${session_key_store_account}/1"><li style="border-right:1px solid #ccc"><span style="background:#ea631a;">我的优惠券</span></li></a>
		 <li><span style="background:#f69731">推荐有奖</span></li>
       </ul>
        <p class="coupon_count">已经拥有积分：<em>${memberBaseInfo.balanceIntegral }</em><a href="">获取积分方式</a></p>
        <c:forEach items="${couponInfos }" var="couponInfo">
	        <div class="coupon_content" couponMan="${couponInfo.couponMan }" stopTime="${couponInfo.couponStopTime }" storeNames='${couponInfo.storeNames }' onclick="visCoupon(this, ${couponInfo.couponId}, ${couponInfo.couponVantages})">
	          <div class="coupon_content_detail clearfix" style="background:#${couponInfo.couponColour}">
	            <div class="coupon_content_detail_left">
				 <p style="color:#16b2e5">券</p>
				 <div class="coupon_price">
				     <span>¥<i>${couponInfo.couponPrice}</i><em>${couponInfo.couponName}</em></span>
				 </div>
				 <div class="time_out">到期时间 ${couponInfo.couponStopTime}</div>
				</div>
				<div class="coupon_content_detail_right" style="background:#168bb1">
				 	点击兑换
				</div>       		  
	          </div>
	       </div>
       </c:forEach>
        <p class="past">优惠券兑换</p><p>	
     </p></div>
	
	 <ul class="bottom_fix clearfix">
 		<a href="<%=basePath %>memberCenter/view/home/${session_key_store_account}/1">
	      <li><img src="<%=basePath %>images/mobile/member/botton_1.png">
		      <p>我的</p>
		  </li>
	    </a>
	    <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_account}/1">
	    	<li><img src="<%=basePath %>images/mobile/member/botton_2.png">
		       <p>预约</p>
		    </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_account}/1">
		  <li><img src="<%=basePath %>images/mobile/member/botton_3.png">
		    <p>商城</p>
		  </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_account}/1">
	     <li><img src="<%=basePath %>images/mobile/member/botton_4.png">
		     <p>门店</p>
		  </li>
	    </a>
 	</ul>
 	<%@include file="../memberBase.jsp" %>
</body>
<script>
var hasVag = '${memberBaseInfo.balanceIntegral }';
var couponId = null;
var vag = null;
var num = 0;
var couponMan = 0;
function visCoupon(div, id, vags){
	var stop  = jQuery(div).attr("stopTime");
	var storeNames  = jQuery(div).attr("storeNames");
	couponId = id;
	vag = vags;
	couponMan = jQuery(div).attr("couponMan");
	$(".mask").show();
	jQuery('.saying').text("温馨提示：本优惠劵于" + stop + "日下架" + ", 并且该优惠券只可在 " + storeNames +" 门店中使用");
}

function convert(){
	num = jQuery("input[name='num']").val();
	//校验是否超出了优惠券的最大领取额度
	if (num>couponMan){alert("该劵最大领取为"+couponMan);return;}
	//校验积分是否足够支付
	if (Number(hasVag)<Number(vag)*num){alert("您的积分不足");return;}
	var data = "num="+num+"&couponId="+couponId;
	 $.ajax({
		  type : "POST",
		  url : baseUrl + "memberCenter/action/exchangeCoupon",
		  data : data,
		  dataType : "json",
		  success : function(e){
			  if (e.code!=0){
				  alert("您的兑换次数已上限");return;
			  }
			  else {
				  hasVag = Number(hasVag) - Number(vag)*num;
				  $(".mask").hide();
				  $('#toast').show();
	              setTimeout(function () {
	                  $('#toast').hide();
	              }, 2000);
				  $(".coupon_count").children("em").text(hasVag);
			  }
		  }
	  });
}
$(function(){  
    $('input').bind('input  propertychange', function() {
    	$(this).next("em").text($(this).val()*vag)
   });  
})  
</script>
</html>