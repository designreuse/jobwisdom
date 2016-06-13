<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/dial.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right'>
     <div class="lantern_top clearfix">
       <div class="lantern_top_img">
	     <img src="<%=qiniuPath%>system/profile/turntable_1.png">
	   </div>
	   <div class="lantern_right">
	     <p><em>大转盘</em><span>活动</span></p>
		 <div class="lantern_content">
		大转盘为商家提供转盘抽奖服务，商家只需设置活动时间、活动规则、活动奖品等信息就能发起活动，粉丝进入活动界面，点击开始抽奖，即有几率
得不同类型的奖品
		 </div>
		
	   </div>

    </div>	

    <div class="lantern_content_pic">
         <ul class="clearfix">
            <li><img src="<%=qiniuPath%>system/profile/turntable_2.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/turntable_3.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/turntable_4.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/turntable_5.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/turntable_6.png"></li>
        </ul>		 
    </div>   
   
     
  </div>	
        </div>
  </div>
</div>

<div class="zzc">
  <div class="lantern_alert"><img src="<%=qiniuPath%>system/profile/lantern_alert.png"></div>
</div>

<script type="text/javascript">
jQuery(".content_right").click(function(){
    jQuery('.zzc').show();
})	
jQuery(".zzc").click(function(){
    jQuery('.zzc').hide();
})	
</script>	
</body>
</html>