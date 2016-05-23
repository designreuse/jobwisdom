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
					<div class="set_name">
					 <div class="shop_name">
					      <p>企业名称</p>
					      <input type="text" id="enterpriseName" >
				     </div>
					 <div class="shop_name">
					      <p>企业负责人姓名</p>
					      <input type="text" id="enterpriseLinkname">
				     </div>
					 <div class="shop_name">
					      <p>企业负责人电话</p>
					      <input type="text" id="enterpriseLinkphone">
				     </div>
				     <div class="shop_name">
					      <p>企业代号</p>
					      <input type="text" id="storeAccount">
				     </div>
				    </div>
				  </div>
			  
			  <div class="content_right_">
			       
			  </div>
			  <button  onclick="saveStoreInfo();">提交</button>
			 </div>

              
            </div>
		</div>
    </div>
</div>

<script src="<%=basePath%>js/common/city-picker.data.js"></script>
<script src="<%=basePath%>js/common/city-picker.js"></script>
<script src="<%=basePath%>js/common/main.js"></script>
<script type="text/javascript">
	
	
	
	
	//提交企业信息
	function saveStoreInfo(){
		var enterpriseName = jQuery("#enterpriseName").val();
		var enterpriseLinkphone = jQuery("#enterpriseLinkphone").val();
		var enterpriseLinkname = jQuery("#enterpriseLinkname").val();
		var storeAccount = jQuery("#storeAccount").val();
		/* var addressList = jQuery("#city-picker3").val().split('/');
		var storeProvince = addressList[0];
		var storeCity = addressList[1]; */
		/* var street = jQuery("#searchtext").val(); */
		/* if (isEmpty(storeProvince)) {
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
		var storeLinkphone = jQuery("#storeLinkphone").val(); */
/* 		var latitude = jQuery("#lat").text();
		var longitude = jQuery("#lng").text(); */
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

		/* if (isEmpty(storeTel)) {
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
	    } */
		var data = "enterpriseName=" + enterpriseName + "&enterpriseLinkphone=" + enterpriseLinkphone + "&enterpriseLinkname=" + enterpriseLinkname + "&storeAccount=" + storeAccount;
		submit(data, "保存成功，已更新您的店铺信息");
	}
	
	//数据提交
	function submit(data, msg){
		jQuery.ajax({
	        cache: true,
	        type: "POST",
	        url: baseUrl + "enterprise/action/addEnterprise",
	        data: data,
	        async: false,
	        success: function(data) {
	        	if (data.code != 0) {
	                dialog(e.msg);
	                return;
	            }
	        	dialog(msg);
	        	window.location.href = baseUrl + "enterprise/view/showEnterprise";
	        }
	    });
	}
</script>

</body>
</html>