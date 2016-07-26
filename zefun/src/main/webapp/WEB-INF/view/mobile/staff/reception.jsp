<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>接待大厅</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
     <style>
	   .order_content{padding:10%}
	   .order_content input{font-size:18px;background:#e9e9e9;border-radius:8px;width:100%;height:3rem;border:1px solid #434343}
	   .order_content_ul li{margin-top:1.5rem;width:33.3%;float:left}
	   .order_content_ul li span{text-align:center;display:inline-block;width:4rem;height:4rem;border-radius:2rem;background:#f49549;color:white;font-size:22px;line-height:4rem}
	   .order_content p button{width:100%;height:3rem;font-size:22px;color:white;border-radius:8px;text-align:ceter;line-height:3rem;background:#ea631a;border:none;font-weight:bold}
	   .order_button{margin-top:2rem} 
	   .order_content_ul li em{display:inline-block;color:#86562f;font-size:16px;position:relative;top:12px}   
	   .order_content_ul li em img{vertical-align:middle;width:25px;margin-right:5px;}
	  </style>
</head>
<body>
<div class="con"> 
      <div class="order_content">
		   <p><input type="text" name = "handOrderCode" dir="rtl" disabled></p>
		  <div class="order_content_ul"> 
		    <ul class="clearfix">
		      <li><span ontouchstart="setNumber(1)">1</span></li>
		      <li style="text-align:center" ><span ontouchstart="setNumber(2)">2</span></li>
			  <li style="text-align:right" ><span ontouchstart="setNumber(3)">3</span></li>
			  <li ><span ontouchstart="setNumber(4)">4</span></li>
		      <li style="text-align:center" ><span ontouchstart="setNumber(5)">5</span></li>
			  <li style="text-align:right" ><span ontouchstart="setNumber(6)">6</span></li>
			  <li ><span ontouchstart="setNumber(7)">7</span></li>
		      <li style="text-align:center" ><span ontouchstart="setNumber(8)">8</span></li>
			  <li style="text-align:right" ><span ontouchstart="setNumber(9)">9</span></li>
			  <li><em ontouchstart = "clean()"><img src="<%=basePath%>images/mobile/newemployee/clean.png">清除</em></li>
		      <li style="text-align:center"><span ontouchstart="setNumber(0)">0</span></li>
			  <li style="text-align:right"><em ontouchstart = "back()"><img src="<%=basePath%>images/mobile/newemployee/back_.png">回退</em></li>
		  </ul>
		</div>
		<p class="order_button"><button ontouchstart="goEasy()">走 起</button></p>
     </div>
	<ul class="bottom_fix clearfix">
      <li ontouchstart = "chooseTypePage(1, '${session_key_store_account}')">
          <img src="<%=basePath%>images/mobile/newemployee/bottomOne_.png">
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
		     <img src="<%=basePath%>images/mobile/newemployee/bottomFour.png">
		     <p>我的</p>
	  </li>
    </ul> 
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script type="text/javascript">
   function setNumber (num) {
	   var handOrderCode = jQuery("input[name='handOrderCode']").val();
	   if (isEmpty(handOrderCode)) {
		   jQuery("input[name='handOrderCode']").val(num);
	   }
	   else {
		   handOrderCode = handOrderCode + num;
		   jQuery("input[name='handOrderCode']").val(handOrderCode);
	   }
   }
   
   function clean () {
	   jQuery("input[name='handOrderCode']").val("");
   }
   
   function back () {
	   var handOrderCode = jQuery("input[name='handOrderCode']").val();
	   handOrderCode = handOrderCode.substring(0,handOrderCode.length - 1);
	   jQuery("input[name='handOrderCode']").val(handOrderCode);
   }
   
   function goEasy() {
	   var handOrderCode = jQuery("input[name='handOrderCode']").val();
	   
	   if (isEmpty(handOrderCode)) {
		   dialog("手牌号不能为空！");
		   return;
	   }
	   
	   $.ajax({
	        type : "post",
	        url : baseUrl + "staff/action/selectBaseInfo",
	        data : "handOrderCode="+handOrderCode,
	        dataType : "json",
	        success : function(e){
	            if(e.code != 0){
	                dialog(e.msg);
	                return;
	            }
	            var orderId = e.msg;
	            window.location.href = baseUrl+"staff/view/order/all?orderId="+orderId;
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