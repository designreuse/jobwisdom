<%@page import="com.zefun.web.entity.MemberMenu"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String topBasePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/";
%>
<div class="headerpanel">
    <div class="headerlist">
        <div class="header_">
		  <div class="header_1">
		     <em >你好，${session_key_user_info.name }</em>
			 <em class="support_hover"><span class="support" name = "onlineSupport"><img src="<%=basePath%>images/top1.png">在线支持</span>
			 
			  <div class="ask_responsive">
			      <div class="tool">
				     <p><img src="<%=basePath%>images/top4.png"></p>
					 <em class="ask">咨询</em>
				  </div>
				 
				  <div class="tool">
				     <p><img src="<%=basePath%>images/top5.png"></p>
					 <em class="ask">咨询</em>
				  </div>
		     </div>
			 
			 </em>
			 <span style="border-right:1px solid #999da7" onclick = "updatePassword()"><img src="<%=basePath%>/images/top2.png">修改密码</span>
			 <span class="out" onclick = "showLoginOut();"><img src="<%=basePath%>images/top3.png">退出登录</span>
           </div>
		</div>
    </div>
  </div>
  
  
  <div class="top_zzc">
   <div class="leave">
       <p class="make_title">操作提示</p>
	   <div class="leave_content clearfix">
	      <div class="leave_left">
		     <img src="<%=basePath%>images/top6.png">
		  </div>
		  <div class="leave_right">
		    <div class="h2">小主</div>
			<p class="ask_leave">确定要暂时离开了么</p>
			<div class="zzc_button">
			  <button onclick = "cantLoginOut()">留下</button>
			  <button onclick="loginOut()" >再见</button>
			
			</div>
		  </div>
	   
	   </div>
   </div>

</div>