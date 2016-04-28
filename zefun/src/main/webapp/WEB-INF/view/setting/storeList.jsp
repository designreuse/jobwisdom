<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<style type="text/css">

	.rollBox{width:704px;padding:12px 0 5px 6px;}
	.rollBox .LeftBotton{height:70px;width:50px;background:url('<%=basePath%>images/left_click.png') no-repeat 11px 0;overflow:hidden;float:left;display:inline;margin:25px 0 0 0;cursor:pointer;background-size:40px;position:relative;top:290px;left:-90px}
	.rollBox .RightBotton{height:70px;width:50px;background:url('<%=basePath%>images/right_click.png') no-repeat -8px 0;overflow:hidden;float:right;display:inline;margin:25px 0 0 0;cursor:pointer;background-size:40px;position:relative;    top: -340px;
	    left: 350px;}
	.rollBox .Cont{width:910px;overflow:hidden;float:left;margin-left:70px}
	.rollBox .ScrCont{width:10000000px;}
	.rollBox .Cont .pic{float:left;text-align:center;}
	.rollBox .Cont .pic img{padding:4px;background:#fff;border:1px solid #ccc;display:block;margin:0 auto;}
	.rollBox .Cont .pic p{line-height:26px;color:#505050;}
	.rollBox .Cont a:link,.rollBox .Cont a:visited{color:#626466;text-decoration:none;}
	.rollBox .Cont a:hover{color:#f00;text-decoration:underline;}
	.rollBox #List1,.rollBox #List2{float:left;}

</style>

<body>
<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp"%>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp"%>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
        <%@ include file="/top.jsp"%>
        <div class='content_right'>
		   <!--项目设置 -->
		
		  <div class="rollBox">
		     <div class="LeftBotton" onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()"></div>
		     <div class="Cont" id="ISL_Cont">
		      <div class="ScrCont">
		       <div id="List1">
		        <!-- 图片列表 begin -->
		         <div class="pic">
		           <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									      <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
							 <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									     <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
						
		         </div>
				   <div class="pic">
		           <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									      <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
							 <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									     <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
						
		         </div>
				 
				   <div class="pic">
		           <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									      <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
							 <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									     <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
						
		         </div>
				 
				   <div class="pic">
		           <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									      <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
							 <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									     <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
						
		         </div>
				   <div class="pic">
		           <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									      <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
							 <div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
								 <em class="boss_cut" style="font-size:14px;font-weight:bold">华南店</em>
		                            <div class="head_pic">
									   <img src="assets/images/head_pic.png">
									   
									</div>
									
								
									<ul class="clearfix shop_number" >
									  <li>
									      <em class="number_">联系人</em>
										   <p class="num_">大王<p>
										   
									  </li>
									  
									  <li>
									     <em class="shop_price">电话号码</em>
										   <p>1283232323<p>
									  </li>
									
									</ul>
										<div class="shop_bottom clearfix">
									  <div class="writting">
									  
									    编辑
									  </div>
									 <div class="delete">
									  删除
									  
									  </div>
									
									
									</div>
								</div>
								
							</div>
							
						
		         </div>
		       
		       
		        <!-- 图片列表 end -->
		       </div>
		       <div id="List2"></div>
		      </div>
		     </div>
		     <div class="RightBotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()"></div>
		    </div>
		              
		 </div>
        <!--RIGHT PANEL结束 -->
        <div class="clearfix"></div>

        <div id="star"></div>
    </div>
</div>
<script language="javascript" type="text/javascript">

//图片滚动列表 mengjia 070816
var Speed = 1; //速度(毫秒)
var Space = 6; //每次移动(px)
var PageWidth = 910; //翻页宽度
var fill = 0; //整体移位
var MoveLock = false;
var MoveTimeObj;
var Comp = 0;
var AutoPlayObj = null;

GetObj('ISL_Cont').scrollLeft = fill;
GetObj("ISL_Cont").onmouseover = function(){clearInterval(AutoPlayObj);}
GetObj("ISL_Cont").onmouseout = function(){AutoPlay();}
AutoPlay();
function GetObj(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}}

function ISL_GoUp(){ //上翻开始
 if(MoveLock) return;
 clearInterval(AutoPlayObj);
 MoveLock = true;
 MoveTimeObj = setInterval('ISL_ScrUp();',Speed);
}
function ISL_StopUp(){ //上翻停止
 clearInterval(MoveTimeObj);
 if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0){
  Comp = fill - (GetObj('ISL_Cont').scrollLeft % PageWidth);
  CompScr();
 }else{
  MoveLock = false;
 }
 AutoPlay();
}
function ISL_ScrUp(){ //上翻动作
 if(GetObj('ISL_Cont').scrollLeft <= 0){GetObj('ISL_Cont').scrollLeft = GetObj('ISL_Cont').scrollLeft + GetObj('List1').offsetWidth}
 GetObj('ISL_Cont').scrollLeft -= Space ;
}
function ISL_GoDown(){ //下翻
 clearInterval(MoveTimeObj);
 if(MoveLock) return;
 clearInterval(AutoPlayObj);
 MoveLock = true;
 ISL_ScrDown();
 MoveTimeObj = setInterval('ISL_ScrDown()',Speed);
}
function ISL_StopDown(){ //下翻停止
 clearInterval(MoveTimeObj);
 if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0 ){
  Comp = PageWidth - GetObj('ISL_Cont').scrollLeft % PageWidth + fill;
  CompScr();
 }else{
  MoveLock = false;
 }
 AutoPlay();
}
function ISL_ScrDown(){ //下翻动作
 if(GetObj('ISL_Cont').scrollLeft >= GetObj('List1').scrollWidth){GetObj('ISL_Cont').scrollLeft = GetObj('ISL_Cont').scrollLeft - GetObj('List1').scrollWidth;}
 GetObj('ISL_Cont').scrollLeft += Space ;
}
function CompScr(){
 var num;
 if(Comp == 0){MoveLock = false;return;}
 if(Comp < 0){ //上翻
  if(Comp < -Space){
   Comp += Space;
   num = Space;
  }else{
   num = -Comp;
   Comp = 0;
  }
  GetObj('ISL_Cont').scrollLeft -= num;
  setTimeout('CompScr()',Speed);
 }else{ //下翻
  if(Comp > Space){
   Comp -= Space;
   num = Space;
  }else{
   num = Comp;
   Comp = 0;
  }
  GetObj('ISL_Cont').scrollLeft += num;
  setTimeout('CompScr()',Speed);
 }
}

</script>
<script type="text/javascript" src="<%=basePath%>js/baiduMap/baiduMapOfEditStorePosition.js"></script>
</body>
</html>