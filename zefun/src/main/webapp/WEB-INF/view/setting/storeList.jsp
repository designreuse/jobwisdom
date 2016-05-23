<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/add_store.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link href="<%=basePath%>css/city-picker.css" rel="stylesheet" type="text/css" />
<style type="text/css">

	.rollBox{width:704px;padding:12px 0 5px 6px;}
.rollBox .LeftBotton{height:70px;width:50px;background:url('./assets/images/left_click.png') no-repeat 11px 0;overflow:hidden;float:left;display:inline;margin:25px 0 0 0;cursor:pointer;background-size:40px;position:relative;top:290px;left:-90px}
.rollBox .RightBotton{height:70px;width:50px;background:url('./assets/images/right_click.png') no-repeat -8px 0;overflow:hidden;float:right;display:inline;margin:25px 0 0 0;cursor:pointer;background-size:40px;position:relative;    top: -340px;
    left: 350px;}
.rollBox .Cont{width:910px;overflow:hidden;float:left;margin-left:70px}
.rollBox .ScrCont{width:10000000px;}
.rollBox .Cont .pic{float:left;text-align:center;}
.rollBox .Cont .pic img{padding:4px;background:#fff;border:1px solid #ccc;display:block;margin:0 auto;}
.rollBox .Cont .pic p{line-height:26px;color:#505050;}
.rollBox .Cont a:link,.rollBox .Cont a:visited{color:#626466;text-decoration:none;}
.rollBox .Cont a:hover{color:#f00;text-decoration:underline;}
.rollBox #List1,.rollBox #List2{float:left;}
#preview{width:100px;border:none;overflow:hidden;}
#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);width:80px;height:80px}

</style>

<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<!-- 页面元素开始 -->
			<div class='content_right'>
		         <div class="add_store_content">
				   <div class="add_store_content_ clearfix">
				     <div class="add_store_button"> <button onclick="addStore()">新增</button></div>
					  <div class="add_store_span"><span>已创建门店数：<em>3</em></span><span>剩余可创建门店数：<em>100</em></span></div>
				   
				   </div>
				   <div class="dd_store_content_2">
				     <ul class="clearfix">
				       <c:forEach items="${storeInfoList}" var="storeInfo" varStatus="status">
				           <li>
						    <span class="business">营业</span>
						      <p class="shop_name">${storeInfo.storeName}</p>
							  <span class="shop_pic"><img src="<%=qiniuPath%>${storeInfo.storeLogo}"></span>
							  <div class="store_right">
							     <span class="connect_people">联系人</span>
								 <p>${storeInfo.storeLinkname}</p>
							      <span class="phone_number">电话号码</span>
							      <p style="position:relative;right:16px">${storeInfo.storeLinkphone}</p>
							  </div>
							  <div class="clearfix add_store_content_bottom">
							     <div class="add_1" style="border-right:1px solid #ccc">
								        编辑<span><img src="<%=basePath%>images/add_store_1.png"></span>
								 </div>
								 <div class="add_1" style="position:relative;left:100px">
								        删除<span><img src="<%=basePath%>images/add_store_2.png"></span>
								 </div>
							  </div>
						   </li>
				       </c:forEach>
			       		 
					 </ul>
				   
				   </div>
				 
				 </div>     
		    </div>
		</div>
    </div>
</div>

<div class="zzc" style="display:none" id = "addOrUpdateStore">
 
	<div class="set_content clearfix" >
	   <p class="new_shop">新建店铺</p>
	  <div class="clearfix"> 
	   <div class="content_left" > 
	     <p style="font-size:14px">设置店铺LOGO:</p>

		   <div id="preview"><img border=0 affiliatedImage="" name="affiliatedImage" src="<%=qiniuPath%>system/profile/set_img.png" width="180" height="180" /></div>

		  <P>*此logo用于移动端店铺介绍页面</P>
	      <input type="text" class = "hide" name = "affiliated" style="width: 80px; height: 80px;top: -124px;opacity:0;cursor:pointer"/>
	     
		<div class="set_name">
		 <div class="shop_name_">
		      <p>设置门店名称</p>
		      <input type="text" id="storeName">
	     </div>
	      <div class="shop_name_">
		      <p>设置店铺电话(多个以,号分割)</p>
		      <input type="text" id="storeTel">
	     </div>
		  <div class="shop_name_">
		      <p>门店负责人姓名</p>
		      <input type="text" id="storeLinkname">
	     </div>
		  <div class="shop_name_">
		      <p>门店负责人电话</p>
		      <input type="text" id="storeLinkphone">
	     </div>
	   
	    </div>
	  </div>
	  
	  <div class="content_right_">
	     <P class="shop_address_">店铺地址</P>   
		 <div class="select_city">
		     <input type="text" placeholder="选择省/市/区" id="city-picker3">
		     <div class="detail_address">
			   <div>详细地址</div>
			   <textarea style="height:86px;margin-top:10px;border-radius:8px;width:220px"></textarea>
			 </div>
			 <div class="detail_address">
			   <div>操作员工号</div>
			   <input type="text" id = "userName">         		   
			 </div>
			 <div class="detail_address">
			   <div>操作员密码</div>
			   <input type="text" id = "userPwd">         		   
			 </div>
		 
		 </div>
	 </div>
	</div> 
		 <div class="add_button">
		  <button class="submit_">确认</button>
		  <button class="cancel_">取消</button>
		 </div> 
   </div>

</div>


<div class="zzc" id = "pageUp" style="display:none">
 <div class="photo_cut">
  <div id="clipArea"></div>
        <input type="file" id="file" style="position:absolute;width: 100px;height: 40px;left: 212px;bottom:8px;opacity:0;cursor:pointer;filter:alpha(opacity=0);">
    <button id="clipBtn" style="position:absolute;width: 100px;height: 40px;left: 346px;bottom:8px;opacity:0;cursor:pointer;filter:alpha(opacity=0);">截取</button>
  	<div class="button_panel"> 
	   <button class="selectpic">选择图片</button>
	   <button class="sureinput">确定上传</button>
		<button class="cancelinput">取消</button>
	 </div>
   </div>
</div>

<script type="text/javascript">
var cssWidth = 200;
var cssHeight = 200;
var qiniuUrl = '<%=qiniuPath%>';
var imgObject;
</script>
<script type="text/javascript" src="<%=basePath %>/js/setting/storeList.js"></script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>
<script src="<%=basePath%>js/common/city-picker.data.js"></script>
<script src="<%=basePath%>js/common/city-picker.js"></script>

</body>
</html>