<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/bargain.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right'>
     <div class="lantern_top clearfix">
       <div class="lantern_top_img">
	     <img src="<%=qiniuPath%>system/profile/minBargain_1.png">
	   </div>
	   <div class="lantern_right">
	     <p><em>微砍价</em><span>活动</span></p>
		 <div class="lantern_content">
		 用户通过微信端，关注商家微信公众号，即可进入微砍价平台。通过分享给好友或朋友圈的方式，让朋友帮忙“砍一刀”，“每人可以帮忙砍一次，帮忙
的人数不限”。
		 </div>
		
	   </div>

    </div>	

    <div class="lantern_content_pic">
         <ul class="clearfix">
            <li><img src="<%=qiniuPath%>system/profile/minBargain_2.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/minBargain_3.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/minBargain_4.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/minBargain_5.png"></li>
			<li><img src="<%=qiniuPath%>system/profile/minBargain_6.png"></li>
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