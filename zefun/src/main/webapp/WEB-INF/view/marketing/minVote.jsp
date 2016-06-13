<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/vote.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right'>
     <div class="lantern_top clearfix">
       <div class="lantern_top_img">
	     <img src="<%=qiniuPath%>system/profile/vote_1.png">
	   </div>
	   <div class="lantern_right">
	     <p><em>微投票</em><span>活动</span></p>
		 <div class="lantern_content">
		 商家采用投票的活动来吸引用户，与用户之间产生互动，从而促进企业营销的一种手段。
		 </div>
		 <div class="note">
		   注：好友帮助点亮灯笼时，一定会点亮某一灯笼，灯笼点亮概率越高，该灯笼被点亮的可能性越大，所有灯笼的点亮概率之和必须为100%。
		 </div>
	   </div>

    </div>	

    <div class="lantern_content_pic">
         <ul class="clearfix">
            <li><img src="<%=qiniuPath%>system/profile/vote_2.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/vote_3.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/vote_4.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/vote_5.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/vote_6.png"></li>
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