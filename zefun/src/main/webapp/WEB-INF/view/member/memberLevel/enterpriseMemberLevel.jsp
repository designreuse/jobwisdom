<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>css/business_manage.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	<!-- 页面代码 -->
		<div class='content_right clearfix'>
    <button class="new_vip">新建会员卡</button>
	<table class="business_manage_table">
	   <tr>
	     <td>会员卡类别</td>
		 <td>会员卡名称</td>
		 <td>项目折扣</td>
		 <td>商品折扣</td>
	     <td>最低充值</td>
		 <td>开卡充值</td>
		 <td>现金支付打折</td>
		 <td>业绩折扣比例</td>
		 <td>积分计算方式</td>
		 <td>等级说明</td>
		 <td>操作</td>
	   </tr>
	   <tr>
	     <td>折扣卡</td>
		 <td>三折卡</td>
		 <td>10折</td>
		 <td>15折</td>
	     <td>5000元</td>
		 <td>2000元</td>
		 <td>不打折</td>
		 <td>100%</td>
		 <td>10元=1积分</td>
		 <td>啊啊啊</td>
		 <td> <span><img src="assets/images/vip_manage.png"> </span>
		  <div class="manage_hover">
		    <ul class="demo_fade"><li style="border-bottom:1px solid black"><img src="assets/images/handle_2.png"></li><li><img src="assets/images/handle_1.png"></li></ul>
		  </div>
		   
		 </td>
	   </tr>
	    <tr>
	     <td>折扣卡</td>
		 <td>三折卡</td>
		 <td>10折</td>
		 <td>15折</td>
	     <td>5000元</td>
		 <td>2000元</td>
		 <td>不打折</td>
		 <td>100%</td>
		 <td>10元=1积分</td>
		 <td>啊啊啊</td>
		 <td> <span><img src="assets/images/vip_manage.png"> </span>
		  <div class="manage_hover">
		    <ul class="demo_fade"><li style="border-bottom:1px solid black"><img src="assets/images/handle_2.png"></li><li><img src="assets/images/handle_1.png"></li></ul>
		  </div>
		   
		 </td>
	   </tr>
	
	
	
	</table>
   	 
  </div>
    </div>
</div>
</div><!--mainwrapper-->

<div class="zzc" style="display:none">
   <div class="business_level">
      <p>新建等级卡</p>     
	  <div class="business_level_content">
	   <div class="business_level_content_left">
	    <div class="business_level_input clearfix">
		  <div class="business_level_input_left">
		     <span>会员卡种类<select><option></option></select><em>?</em></span>
			 <span>会员卡名称<select><option></option></select></span>
		  </div> 
		  <div class="business_level_input_right">
		     <span><i>使用须知</i><textarea></textarea></span>
		  </div>
		</div>
		
		<div class="business_level_back">
		  <p>选择卡背</p>
		   <ul class="business_level_back_ul clearfix">
		     <li><img src="assets/images/vip_card.png"></li>
			 <li><img src="assets/images/vip_card.png"></li>
			 <li><img src="assets/images/vip_card.png"></li>
			 <li><img src="assets/images/vip_card.png"></li>
			 <li><img src="assets/images/vip_card.png"></li>
			 <li><img src="assets/images/vip_card.png"></li>
			 <li><img src="assets/images/vip_card.png"></li>
			 <li><img src="assets/images/vip_card.png"></li>
			 <li><img src="assets/images/vip_card.png"></li>
			 <li><img src="assets/images/vip_card.png"></li>
           </ul>
		   <div class="business_level_back_text">
		      <ul class="business_level_back_text_ul clearfix">
		         <li>商品折扣<input type="text"></li>
				 <li>现金支付打折<input type="text"></li>
				 <li>项目折扣<input type="text"></li>
				 <li>业绩折扣打折<input type="text"></li>
				 <li>开卡费用<input type="text"></li>
				 <li>积分计算方式<input type="text"></li>
			  </ul>
		   </div>
		</div>
		
		<div class="business_level_button">
		   <button>预览</button>
		   <button>确认</button>
		   <button>取消</button>
		</div>
		
		</div>
	
		<div class="business_level_content_right">
		  <p>效果图预览</p>
		  <div class="preview">
		     <div class="preview_1 clearfix">
			    <div class="preview_left">
				   <span>5折卡</span>
            	</div>
				<div class="preview_right">
                   <ul>
					  <li>项目折扣：5折</li>
					  <li>项目折扣：6折</li>
                   </ul>
				   <span>卡上余额：12354651元<em>充值</em></span>
	            </div>
         	 </div>  
		  </div>
		</div>
	  </div>
   </div>   
</div>

<script>
   
  	jQuery(document).ready(function() {    
         jQuery('.business_manage_table td span').click(function(){
	     find=jQuery(this).parent().find('.demo_fade');
	   if(find.css('display')=='none'){
	      find.stop(true,true).slideDown('normal');
	   }    
	   else if(find.css('display')=='block'){
          jQuery('.demo_fade').stop(true,true).slideUp('normal');
	    }	
	  
 	 })
  });
  
  </script>

</body>
</html>