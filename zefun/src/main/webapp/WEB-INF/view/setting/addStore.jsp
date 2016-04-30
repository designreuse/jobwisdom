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
				     
				      <div id="preview"><img id="imghead" border=0 src="<%=basePath%>images/set_img.png" width="180" height="180" /></div>
					  <P>*此logo用于移动端店铺介绍页面</P>
				      <input type="file" onchange="previewImage(this)"  style="position:relative;width: 80px; height: 80px;top: -124px;opacity:0;cursor:pointer"/>
				     
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
                      <input type="text" style="border-radius: 6px!important; width: 276px;position: relative;left: 50px;height: 26px;top: 6px; " placeholder="请搜索">
                      <%-- <input id="searchtext" type="text" class="wp60"  value="${street }" placeholder=""/> --%>
                      <div class="container">

	
	
	<div class="docs-methods">
		<form class="form-inline">
			<div id="distpicker">
				<div class="form-group">
					<div style="position: relative;">
						<input id="city-picker3" class="form-control" readonly type="text" value="江苏省/常州市/溧阳市" data-toggle="city-picker">
					</div>
				</div>
			</div>
		</form>
	</div>
	
</div>


                      
                      <span id="searchbt" class="addrres-search" onclick="serachlocal()" style="cursor:pointer">搜索</span>
					  
				        <div id="lat" class="hide">${storeInfo.latitude }</div>
	                    <div id="lng" class="hide">${storeInfo.longitude }</div>
	                    <div id="results"></div>  
				        <div id="mapx"></div>  
				        <div id="mapy"></div>  
				        <div id="level"></div> 
	                    <!-- 地图填充 -->
	                    <div style="width:490px;height:380px;border:#ccc solid 1px;font-size:12px;position:relative;top:-30px;left:70px" id="map"></div>
			     </div>
			  
			  
			  </div>
			  <button class="submit_">提交</button>
			  <button class="cancel_">取消</button>
			 </div>

              
            </div>
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
</body>
</html>