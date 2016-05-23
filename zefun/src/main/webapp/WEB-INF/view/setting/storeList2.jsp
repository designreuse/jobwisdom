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
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<!-- 页面元素开始 -->
			<div class='content_right'>
		         <div class="write_input">
				    <div class="write_6" style="background:white;float:left;top:0px!important;left:120px!important;width:100px!important" onclick="addStorePage();"><span class="add_step" style="position:relative;left:-10px">+</span>新增</div>
				 </div>
				  <div class="rollBox">
				     <div class="LeftBotton" onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()"></div>
				     <div class="Cont" id="ISL_Cont">
				      <div class="ScrCont">
				       <div id="List1">
				        <!-- 图片列表 begin -->
				         <c:forEach items="${storeInfoList}" var="storeInfo" step="2" varStatus="status">
				             <div class="pic">
					           <div class="column_small_first" action-type="showdesc"  onclick='window.open("<%=basePath %>storeinfo/view/addStore?storeId=${storeInfoList[status.count*2-2].storeId }","_self")'>
									<div class="column_img_container">
										 <em class="boss_cut" style="font-size:14px;font-weight:bold">${storeInfoList[status.count*2-2].storeName}</em>
				                            <div class="head_pic">
											   <img src="<%=qiniuPath%>${storeInfoList[status.count*2-2].storeLogo}">
											</div>
											<ul class="clearfix shop_number" >
											  <li>
											      <em class="number_">联系人</em>
												   <p class="num_">${storeInfoList[status.count*2-2].storeLinkname}<p>
											  </li>
											  
											  <li>
											      <em class="shop_price">电话号码</em>
												   <p>${storeInfoList[status.count*2-2].storeLinkphone}<p>
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
								<c:if test="${(status.count*2-1)!=fn:length(storeInfoList) }">
								   <div class="column_small_first " action-type="showdesc" onclick='window.open("<%=basePath %>storeinfo/view/addStore?storeId=${storeInfoList[status.count*2-1].storeId }","_self")'>
										<div class="column_img_container">
											 <em class="boss_cut" style="font-size:14px;font-weight:bold">${storeInfoList[status.count*2-1].storeName}</em>
					                            <div class="head_pic">
												   <img src="<%=qiniuPath%>${storeInfoList[status.count*2-1].storeLogo}">
												</div>
												<ul class="clearfix shop_number" >
												  <li>
												      <em class="number_">联系人</em>
													  <p class="num_">${storeInfoList[status.count*2-1].storeLinkname}<p>
												  </li>
												  <li>
												     <em class="shop_price">电话号码</em>
													 <p>${storeInfoList[status.count*2-1].storeLinkphone}<p>
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
								</c:if>
					         </div>
				         </c:forEach>
				       
				        <!-- 图片列表 end -->
				       </div>
				       <div id="List2"></div>
				      </div>
				     </div>
				     <div class="RightBotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()"></div>
				    </div>
		              
		 </div>
		</div>
    </div>
</div>

<script language="javascript" type="text/javascript">
   function addStorePage(storeId) {
		   window.location.href = baseUrl + "storeinfo/view/addStore";
   }
   
   jQuery(function(){
	     var count=jQuery('.pic').size();
	     var now=0;
		 //向右走
	      jQuery('.RightBotton').click(function(){
	         if(now<=count-5){
			    now+=4;
		        jQuery('.pic').stop(true,true).animate({
			     left:-227*now
			   
			       }) 
			  }	
	        		
		    });
			//向左走
		 jQuery('.LeftBotton').click(function(){
	         if(now>=1){
			    now-=4;
		        jQuery('.pic').stop(true,true).animate({
			     left:-227*now
			   
			       }) 
			  }	
	        		
		    });
	  })
</script>
</body>
</html>