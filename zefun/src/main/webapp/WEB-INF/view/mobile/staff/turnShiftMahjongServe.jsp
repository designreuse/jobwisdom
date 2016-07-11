<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>选择服务人员</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
    <style>
		.select_server_content{padding:0.7rem 0.5rem;background:white;border-bottom:1px solid #eaeaea}
		.select_server{margin-top:1rem;}
		.ui-navigator .ui-navigator-list li a, .ui-navigator .ui-navigator-fix{font-size:14px}
	
		.selected li{padding-left:2px;width:14%;float:left;}
		.selected li em{display:inline-block;width:1rem;height:1rem;margin-right:2px;position:relative;top:1px}
		
		.select_detail{width:100%;overflow:overlay;border-radius:8px;margin-top:1rem}
		.select_detail li{float:left;width:50%;margin-top:1rem;height:4rem;text-align:center}
		.select_detail_div{width:130px;height:3rem;box-shadow:0 2px 2px #787878;background:#21d9db;border-radius:8px;overflow:hidden;position:relative;margin:0 auto}
		.select_detail_div_num{margin:0.5rem;float:left;width:2rem;height:2rem;border-radius:1rem;background:white;color:#e44e20;text-align:center;line-height:2rem;font-size:14px;float:left}
		.select_detail_div_text{float:left;color:white;font-size:14px;line-height:3rem}
		.selected_{position:absolute;right:-2px;top:0px;width:36px;height:24px;background-size:36px 24px;color:white}
		.selected_ span{position:relative;left:7px;top:-1px}
		.zzc_conetent{position:absolute;bottom:-50%;left:0;width:100%}
		.apartment_ul li{color:black;border-bottom:1px solid white;width:120px;float:left;text-align:center;height:3rem;text-align:center;line-height:3rem}
		.apartment_ul{width:100%;overflow:overlay;background:white;border-bottom:1px solid #e5e5e5;box-shadow:0 2px 2px #e5e5e5 ;}
		.active { font-size: 13px;color: #ea631a!important; border-bottom: 1px solid #ea631a!important;}
	
	    .zzc{ position: fixed;top: 0px;height: 100%;left: 0px;width: 100%;background: rgba(102, 108, 121, 0.8);z-index: 2;display:none}
	    .zzc_server_people{width:22rem;height:13rem;background:white;margin:20% auto}
		 .zzc_server_people>p{height:3rem;background:#ea631a;color:white;line-height:3rem;text-align:center;position:relative;font-size:14px}
		 .zzc_server_people>p img{position:absolute;right:8px;width:14px;top:4px}
		 
		 .zzc_server_people_content>p{margin-bottom:0.5rem;text-align:center;height:2rem;line-height:2rem;color:black}
		 .zzc_server_people_content ul li{float:left;width:50%;text-align:center}
		 .zzc_server_people_content ul li span{display:inline-block;width:6rem;height:6rem;background:#f49549;line-height:6rem;color:white;font-size:14px;}
		 .zzc_server_people_content ul li span img{vertical-align:middle;width:28px;margin:0 3px}
		 body{background:white}
	</style>
</head>
<body>
<div class="con"> 
		<div class="apartment_ul clearfix">
		   <ul>
		    <c:forEach items="${shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="status">
		        <li <c:if test="${status.index == 0 }">class="active"</c:if>>${shiftMahjongDto.shiftMahjongName }</li>
		    </c:forEach>
		  </ul>
		</div>
	<div class="select_server">
	   <div class="select_server_content">
	     <ul class="selected clearfix">
		   <li style="color:#f49549"><em style="background:#f49549"></em>选定</li>
		   <li style="width:25%;color:#2bbde7"><em style="background:#2bbde7"></em>指定</li> 
		   <li style="color:#21d9db"><em style="background:#21d9db"></em>空闲</li> 
		   <li style="color:#e11e23"><em style="background:#e11e23"></em>工作</li> 
		   <li style="color:#e7a3ef"><em style="background:#e7a3ef"></em>点客</li> 
		   <li style="color:#eede9f"><em style="background:#eede9f"></em>暂休</li> 
		 </ul>
	   </div>
	   <c:forEach items="${shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="status">
	       <ul class="select_detail clearfix <c:if test="${status.index == 0 }">hide</c:if>" shiftMahjongId = "${shiftMahjongDto.shiftMahjongId}">
	          <c:forEach items="${shiftMahjongDto.shiftMahjongEmployeeList}" var="shiftMahjongEmployee" varStatus="employeeStatus">
	               <li>
		     		 <div class="clearfix select_detail_div"
		     		    <c:choose>
		     		       <c:when test="${shiftMahjongEmployee.state == 0}">style="background:#e11e23"</c:when>
		     		       <c:when test="${shiftMahjongEmployee.state == 1}">style="background:#21d9db"</c:when>
		     		       <c:when test="${shiftMahjongEmployee.state == 2}">style="background:#eede9f"</c:when>
		     		       <c:when test="${shiftMahjongEmployee.state == 4}">style="background:#e7a3ef"</c:when>
		     		    </c:choose>
		     		    >
					    <!-- <div cllass="selected_" style="background: url('images/selected.png') no-repeat;  background-size: 36px 24px;"><span>选</span></div> -->
					    <div class="select_detail_div_num">${shiftMahjongEmployee.shiftMahjongOrder }</div>
						<div class="select_detail_div_text">
						   ${shiftMahjongEmployee.employeeCode } ${shiftMahjongEmployee.name }
						</div>
					 </div>
				   </li>
	          </c:forEach>
		      <li>
	     		 <div class="clearfix select_detail_div"> 
				    <div class="selected_" style="background:url('images/appoint.png') no-repeat;  background-size: 36px 24px;"><span>选</span></div>
				    <div class="select_detail_div_num">1</div>
					<div class="select_detail_div_text">
					   1100 龙傲天
					</div>
				 </div>
			  </li>
			   <li>
	     		 <div class="clearfix select_detail_div"> 
				    <div class="selected_" style="background:url('images/appoint.png') no-repeat;  background-size: 36px 24px;"><span>选</span></div>
				    <div class="select_detail_div_num">1</div>
					<div class="select_detail_div_text">
					   1100 龙傲天
					</div>
				 </div>
			   </li>
		   </ul>
	   </c:forEach>
	</div>
</div>
<div class="zzc">
    <div class="zzc_server_people">
   <p>选择服务人员<img src="images/coupon_close.png"></p>
   <div class="zzc_server_people_content">
     <p>1100 龙傲天</p>
      <ul class="clearfix">
        <li><span>选定<img src="images/choose.png"></span></li>
	     <li><span style="background:#2bbde7">选定<img src="images/choose.png"></span></li>
	  </ul>
      </div>
    </div>
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script type="text/javascript">
     $(function(){
	   var count=$('.apartment_ul li').size();
	   $('.apartment_ul ul').css('width',count*120);
	   $('.apartment_ul li').click(function(){
	     $(this).addClass('active').siblings().removeClass('active');
	   })   
	 })
	 
	 
</script>

<script language="JavaScript">
     javascript:window.history.forward(1);
</script>
</body>
</html>
