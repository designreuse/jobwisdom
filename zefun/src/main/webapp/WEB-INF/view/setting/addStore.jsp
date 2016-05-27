<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link href="<%=basePath%>css/city-picker.css" rel="stylesheet" type="text/css" />

<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<!-- 页面元素开始 -->
			<div class='content_right' style="background:white">
			   <div class="set_content clearfix">
			   
				  <div class="content_left"> 
				     <p style="font-size:14px">设置店铺LOGO:</p>
				     <c:choose>
				      <c:when test="${storeInfo.storeLogo == null}">
				         <div id="preview"><img border=0 affiliatedImage="" name="affiliatedImage" src="<%=qiniuPath%>system/profile/set_img.png" width="180" height="180" /></div>
				      </c:when>
				      <c:otherwise>
				         <div id="preview"><img border=0 affiliatedImage="<%=qiniuPath%>${storeInfo.storeLogo }" name="affiliatedImage" src="<%=qiniuPath%>${storeInfo.storeLogo }" width="180" height="180" /></div>
				      </c:otherwise>
				     </c:choose>
					  <P>*此logo用于移动端店铺介绍页面</P>
				      <!-- <input type="file"   style="position:relative;width: 80px; height: 80px;top: -124px;opacity:0;cursor:pointer"/> -->
				     
					<div class="set_name">
					 <div class="shop_name">
					      <p>设置门店名称</p>
					      <input type="text" id="storeName" value="${storeInfo.storeName }">
				     </div>
				      <div class="shop_name">
					      <p>设置店铺电话(多个以,号分割)</p>
					      <input type="text" id="storeTel" value="${storeInfo.storeTel }">
				     </div>
					  <div class="shop_name">
					      <p>门店负责人姓名</p>
					      <input type="text" id="storeLinkname" value="${storeInfo.storeLinkname }">
				     </div>
					  <div class="shop_name">
					      <p>门店负责人电话</p>
					      <input type="text" id="storeLinkphone" value="${storeInfo.storeLinkphone }">
				     </div>
				   
				    </div>
				  </div>
			  
			  <div class="content_right_">
			       
			  <div class="shop_addrss">
				      <h5>店铺地址：</h5>
				      <div id="city" class="choosecity">选择省市: </div>
				      <div class="detailaddress">详细地址： </div>
                      <%-- <span  class="addrres-difang">${storeInfo.storeProvince}${storeInfo.storeCity}</span> --%>
                      <input type="text" id="searchtext" style="border-radius: 6px!important; value = "${storeInfo.storeAddress}" width: 276px;position: relative;left: 50px;height: 26px;top: 6px; " placeholder="请搜索">
                      <%-- <input id="searchtext" type="text" class="wp60"  value="${street }" placeholder=""/> --%>
                      <div class="container">
						<div class="docs-methods">
							<form class="form-inline">
								<div id="distpicker">
									<div class="form-group">
										<div style="position: relative;">
											<input id="city-picker3" class="form-control" readonly type="text" value="${storeInfo.storeProvince}/${storeInfo.storeCity}" data-toggle="city-picker">
										</div>
									</div>
								</div>
							</form>
						</div>
						
					</div>
                    <span class="addrres-search" onclick="serachlocal()" style="cursor:pointer">搜索</span>
					  
<%-- 				        <div id="lat" class="hide">${storeInfo.latitude }</div>
	                    <div id="lng" class="hide">${storeInfo.longitude }</div>
	                    <div id="results"></div>  
				        <div id="mapx"></div>  
				        <div id="mapy"></div>  
				        <div id="level"></div> 
	                    <!-- 地图填充 -->
	                    <div style="width:490px;height:380px;border:#ccc solid 1px;font-size:12px;position:relative;top:-30px;left:70px" id="map"></div> --%>
			     </div>
			  
			  
			  </div>
			  <button class="submit_" onclick="saveStoreInfo();">提交</button>
			  <button class="cancel_" onclick= "cancel()">取消</button>
			 </div>

              
            </div>
		</div>
    </div>
</div>
<div class="zzc">
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
	var globalLat = '${storeInfo.latitude }';
	var globalLng = '${storeInfo.longitude }';
</script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zxYZYzCtCT1lhiVOuxQeieOf"></script>
<script type="text/javascript" src="<%=basePath%>js/baiduMap/baiduMapOfEditStorePosition.js"></script>
<script src="<%=basePath%>js/common/city-picker.data.js"></script>
<script src="<%=basePath%>js/common/city-picker.js"></script>
<script src="<%=basePath%>js/common/main.js"></script>
<script type="text/javascript">
	
	var cssWidth = 200;
	var cssHeight = 200;
	var qiniuUrl = '<%=qiniuPath%>';
	var imgObject;
	
	jQuery(function(){
	     jQuery('#preview').click(function(){
	    	imgObject = jQuery(this);
		    jQuery('.zzc').show()
		 })
		 jQuery('.cancelinput').click(function(){
		    jQuery('.zzc').hide();
		    jQuery('.photo-clip-rotateLayer').html('');
		 })
	});
	var imgKey = "";
	function zccCallback(dataBase64){
		imgObject.children("img").attr("src", dataBase64);
		var key = "jobwisdom/project/" + new Date().getTime();
		if ((typeof(imgObject.children("img").attr("projectImage")))!="undefined"){
			var url = qiniuUrl+key;
			imgKey = key;
			imgObject.children("img").attr("projectImage", url);
		}else {
			var url = qiniuUrl+key;
			imgKey = key;
			imgObject.children("img").attr("affiliatedImage", url);
		}
	    var data = {"stringBase64":dataBase64,"key":key};
	    jQuery('.cancelinput').click();
	    jQuery.ajax({
			type: "POST",
			url: baseUrl+"qiniu/base64",
		       data: JSON.stringify(data),
		       contentType: "application/json",
		       dataType: "json",
		       async:true,  
		       beforeSend:function(){
		       	console.log("beforeSend upload image");
		       },
		       error:function (){
		       	console.log("upload image error");
		       },
		       complete :function (){
		       	console.log("complete upload image");
		       },
		       success: function(data) {
			       	var imageUrl = data.msg.imageUrl;
			       	var key = data.msg.key;
			       	console.log(imageUrl);
		       }
     	});
	}

	function cancel() {
		window.location.href = baseUrl + "storeinfo/view/showStoreList";
	}
	
	//提交店铺信息
	function saveStoreInfo(){
		var storeLogo = imgKey;
		var storeName = jQuery("#storeName").val();
		var storeTel = jQuery("#storeTel").val();
		var addressList = jQuery("#city-picker3").val().split('/');
		var storeProvince = addressList[0];
		var storeCity = addressList[1];
		var street = jQuery("#searchtext").val();
		if (isEmpty(storeProvince)) {
			dialog("请选择省");
			return;
		}
		if (isEmpty(storeCity)) {
			dialog("请选择市");
			return;
		}
		if (isEmpty(street)) {
			dialog("请选择街道");
			return;
		}
		var storeAddress = jQuery("#city-picker3").val() + street;
		if (typeof storeAddress === 'undefined') {
			dialog("请选择地址");
			return;
		}		
		var storeLinkname = jQuery("#storeLinkname").val();
		var storeLinkphone = jQuery("#storeLinkphone").val();
/* 		var latitude = jQuery("#lat").text();
		var longitude = jQuery("#lng").text(); */
		if (isEmpty(storeLogo)) {
	        dialog("请上传您的店铺Logo");
	        return;
	    }
		if (isEmpty(storeName)) {
			dialog("请填写您的店铺名称");
			return;
		}
		/*if (isEmpty(storeAddress)) {
			dialog("请填写您的店铺地址");
			return;
		} else {
			var city = jQuery("#city").text();
			//alert(city);
			if (storeAddress.indexOf(city) == -1) {
				dialog("修改地址只能在您店铺所在城市进行修改");
				return;
			}
		}*/

		if (isEmpty(storeTel)) {
	        dialog("请填写您的店铺电话");
	        return;
	    }
		if (isEmpty(storeLinkname)) {
	        dialog("请填写您的店铺负责人姓名");
	        return;
	    }
		if (isEmpty(storeLinkphone)) {
	        dialog("请填写您的店铺负责人电话");
	        return;
	    }
/* 		if (isEmpty(latitude) || isEmpty(longitude)) {
			dialog("店铺坐标获取失败，请刷新页面重新选取位置");
			return;
		} */
		var data = "storeLogo=" + storeLogo + "&storeName=" + storeName + "&storeTel=" + storeTel + "&storeAddress=" + storeAddress + "&storeProvince=" + storeProvince + "&storeCity=" + storeCity
			+ "&storeLinkname=" + storeLinkname + "&storeLinkphone=" + storeLinkphone ;/* + "&latitude=" + latitude + "&longitude=" + longitude */
		submit(data, "保存成功，已更新您的店铺信息");
	}
	
	//数据提交
	function submit(data, msg){
		jQuery.ajax({
	        cache: true,
	        type: "POST",
	        url: baseUrl + "storeinfo/action/saveUpdateStore",
	        data: data,
	        async: false,
	        success: function(data) {
	        	if (data.code != 0) {
	                dialog(e.msg);
	                return;
	            }
	        	dialog(msg);
	        	window.location.href = baseUrl + "storeinfo/view/showStoreList";
	        }
	    });
	}
</script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>

</body>
</html>