<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/water.css" type="text/css" />
<body>

<div class="zzc" style="display:none">
   <div class="zzc_content">
	  <div class="water_number">流水单号:11111<span class="close_zzc"><img src="assets/images/close.png"></span ></div>
	   <table class="zzc_1">
	     <tr>
	       <td>应收 </td>
		   <td>现钞 </td>
		   <td>银联 </td>
		   <td>微信 </td>
		   <td>支付宝</td>
		   <td>卡金</td>
		   <td>团购 </td>
		   <td>优惠抵扣 </td>
		   <td>挂账 </td>
		   <td>实收</td>
	      </tr>
		   <tr>
	       <td><input type="text"> </td>
		   <td><input type="text"> </td>
		   <td><input type="text"> </td>
		   <td><input type="text"> </td>
		   <td><input type="text"></td>
		   <td><input type="text"></td>
		   <td><input type="text"> </td>
		   <td><input type="text"> </td>
		   <td><input type="text"> </td>
		   <td><input type="text"></td>
	      </tr>
	   </table>
	  <div class="boss_cut"> 
	   <div class="boss_">店长洗剪吹<span><em>应收：¥128</em><em>优惠券：-10</em><em>实收: 118</em></span></div>
	   
	   <table>
	     <tr>
		   <td>洗发</td>
		  <td>施华蔻烫</td>
		   <td>
		     <select>
			    <option>张大胖</option>
			    <option>张大胖</option>
			 </select>
		   </td>
		   <td>
		    <select>
			    <option>非指定</option>
			    <option>非指定</option>
			 </select>
		  </td>	
	        <td>	  
			 <select>
			    <option>非预约</option>
			    <option>非预约</option>
			 </select>
			 </td>	
		   <td>提成</td>
		   <td>业绩</td>
		 </tr>
	   
	   </table>
	 </div>
	 
	 
	 
	     <div class="Korea"> 
	   <div class="Korea_">韩国发膜<span><em>应收：¥150</em><em>实收：150</em></span></div>
	   
	   <table>
	     <tr>
		   <td>销售人员：
		   <select>
		     <option></option>
		   </select>	 
			</td>
		   <td style="position:relative;left:80px">业绩：
		   <select>
		     <option></option>
		   </select>
		   </td>
		 </tr>
		  <tr>
		   <td>提成：
		    <select style="position:relative;left:24px">
		     <option></option>
		   </select>
		   
		   </td>
		 
		 </tr>
	   
	   </table>
	 </div>
	 
	 <div class="water_button">
	   <button class="water_cancel">取消</button>
	   <button class="accounts">结账</button>
	 </div>
	</div>
</div>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
    <%@include file="/menu.jsp"%>

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
      	<%@ include file="/top.jsp" %>
        <div class='content_right' >
 	请输入单号：<input type="text" id="ipt-search-phone" class="simgle" placeholder="水单号/手机号/员工工号">
	<input type="datetime-local" id="startDate" style="color:#d7d8da;border:1px solid #d7d8da;width:160px">

    <span style="display:inline-block;margin:0 10px;color:#ccc">-——</span>

	<input type="datetime-local" id="endDate" class="datetime_" style="color:#d7d8da;border:1px solid #d7d8da;width:160px">
 
    <button class="search_">立刻查询</button>
	<div class="income_">营业收入：</div>
	<table class="income">
	  <tr>
	    <td>现钞</td>
	    <td>银联</td>
		<td>网络支付</td>
		<td>团购支付</td>
		<td>疗程消费</td>
		<td>划卡消费</td>
	  </tr>
	   <tr>
	    <td>11</td>
	    <td>11</td>
		<td>11</td>
	    <td>11</td>
	    <td>11</td>
		<td>11</td>
	  </tr>
	</table>

	
	
	<div class="income_">收入来源：</div>
	<table class="income">
	  <tr>
	    <td>服务单号</td>
	    <td>商品销售（29单）</td>
		<td>疗程销售（32单）</td>
		<td>开卡充值（80单）</td>
		<td>卡金赠送（5单）</td>
		
	  </tr>
	   <tr>
	    <td>11</td>
	    <td>11</td>
		<td>11</td>
	    <td>11</td>
	    <td>11</td>
		
	  </tr>
	</table>
	<div class="total">
	  <span>总客数：214</span>
	  <span>总现金收入：179339.90</span>
	  <span>总现金收入：179339.90</span>
	  <span>总现金收入：179339.90</span>
	  <span>挂账金额：179339.90</span>
	</div>
	
	
		<table class="income">
	  <tr>
	    <td class="water_" style="cursor:pointer">水单号</td>
	    <td>顾客</td>
		<td>浪费时间</td>
		<td>服务内容</td>
		<td>应收</td>
		<td>现钞</td>
		<td>银联</td>
		<td>网络</td>
		<td>卡金</td>
		<td>疗程</td>
		<td>礼金</td>
		<td>优惠券</td>
		<td>团购</td>
		<td>挂账</td>
		<td>实收</td>
		<td>操作</td>
		
	  </tr>
	   <tr>
	    <td>11111</td>
	    <td>1111</td>
		<td>111</td>
	    <td>11</td>
	    <td>11</td>
		<td>11111</td>
	    <td>1111</td>
		<td>111</td>
	    <td>11</td>
	    <td>11</td>
		<td>11111</td>
	    <td>1111</td>
		<td>111</td>
	    <td>11</td>
	    <td>11</td>
		<td></td>
	  </tr>
	</table>
	<div class="num">
	        <a href="" class="index">首页</a> 
            <a href="" class="prev">上一页</a>
            <a href="" class="number active">1</a>
            <a href="" class="number">2</a>
            <a href="" class="number">3</a>
            <a href="" class="number">4</a>
            <span style="position:relative;top:-4px;left:5px"> ....</span>
            <a href="" class="number">7</a>
            <a href="" class="next">下一页</a>
			<a href="" class="last">尾页</a>
        </div>
  </div>
	    

  </div>
</div>
</div>
<!--mainwrapper-->

<%@ include file="/template/memberData.jsp" %>
<script>
	  jQuery(function(){
	      jQuery('.water_').click(function(){
		    
			 jQuery('.zzc').show();
          });
	      
		  jQuery('.close_zzc,.water_cancel').click(function(){
		     jQuery('.zzc').hide();
          });
		  
   })
</script>
<script type="text/javascript" src="<%=basePath %>js/daybook/daybook.js"></script>
</body>
</html>