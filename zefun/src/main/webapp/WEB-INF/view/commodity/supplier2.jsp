<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/supplier_manage.css" type="text/css" />
<script>
	  jQuery(document).ready(function() { 
		  jQuery(document).on('click','.add_supplier_content_right i',function(){
		   find=jQuery(this).parent().find('.demo_fade');
		   if(find.css('display')=='none'){
		      find.stop(true,true).slideDown('normal');
		   }    
		   else if(find.css('display')=='block'){
	          jQuery('.demo_fade').stop(true,true).slideUp('normal');
		    }	
	 	 })
	  });
	   
</script>
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<!-- 页面代码 -->
				<div class="content_right clearfix">
					<div class="add_supplier">
						<p>
							<button onclick="jQuery('.zzc').show('800');jQuery('.zzc').find('input[type=\'text\']').val('');">添加供应商</button>
							<input type="text" onkeyup="selectSupplier(this.value)" placeholder="供应商名称">
						</p>
						<script>
						/**搜索供应商*/
						function selectSupplier(name){
							if (name == ''){
								jQuery(".add_supplier_content").children("div").show();
								return;
							}
							jQuery(".add_supplier_content").children("div").each(function (){
								if (jQuery(this).attr("supplierName").indexOf(name)!=-1){
									jQuery(this).show();
								}else {
									jQuery(this).hide();
								}
							})
						}
						</script>
						<div class="add_supplier_content" style="overflow: overlay;">
							<c:forEach items="${supplierInfoDtos }" var="supplierInfo">
							<div supplierId="${supplierInfo.supplierId }" supplierName="${supplierInfo.supplierName }" linkName="${supplierInfo.linkName }" linkPhone="${supplierInfo.linkPhone }" class="add_supplier_content_padding clearfix">
								<div class="add_supplier_content_left ">
									<p class="add_supplier_adjust">供应商：${supplierInfo.supplierName }<span onclick="updateSupper(${supplierInfo.supplierId }, this)">修改</span>
									</p>
									<p style="margin: 10px 0">地址：${supplierInfo.linkName }</p>
									<p>联系方式：${supplierInfo.linkPhone }</p>
								</div>
								<div class="add_supplier_content_right">
									<c:forEach items="${supplierInfo.brands }" var="brand">
									<span brandId="${brand.brandId }" brandName="${brand.brandName }"> 
									<input type="text" placeholder="${brand.brandName }" style="height: 14px; width: 100px; margin-left: 5px; padding-left: 4px"><i><img src="<%=basePath%>images/demo2_down.png"></i>
										<ul class="demo_fade" style="display: none;">
											<li onclick="deleted(this, ${brand.brandId })"><img src="<%=basePath%>images/handle_2.png"></li>
											<li onclick="updateBrand(this, ${brand.brandId })"><img src="<%=basePath%>images/handle_1.png"></li>
										</ul>
									</span> 
									</c:forEach>
									<span class="add_slide" onclick="showAdd(${supplierInfo.supplierId })"> <img src="<%=basePath%>images/add_slide.png"> </span>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="zzc hide">
	  <div class="add_supplier_alert">
	     <p>添加供应商</p>
	     <div class="add_supplier_alert_content">
		   <p><em>供应商名称</em><input type="hidden" name="supplierId"><input type="text" name="supplierName"><i  class = "addcolor">*</i></p>
		   <p><em>地址</em><input type="text" style="width:200px" name="linkName"><i  class = "addcolor">*</i></p>
		   <p><em>联系方式</em><textarea style="border:1px solid black;width:212px;height:50px;border-radius:8px" name="linkPhone"></textarea><i  class = "addcolor">*</i></p>
		 
		  <div class="add_supplier_alert_sure">
		    <button onclick="saveSupper()">确认</button>  
			<button onclick='hideZZc(this)'>取消</button>  
		  </div>
		 </div>
	  </div>
	</div>
		
	<div class="zzc1 hide">
	  <div class="zzc1_div">
	    <p>商品品牌</p> 
	    <div><input type="hidden" name="brandId"><input type="text" name="brandName"></div>
	    <div class="zzc_div_button"> 
	     <button onclick="saveBrand()">确定</button> <button onclick="jQuery(this).parents('.zzc1').hide('800');supplierId = null;">取消</button>
		</div> 
	  </div>
	</div>
<script type="text/javascript">
var supplierId = null;
function hideZZc(opt){
	jQuery(opt).parents(".zzc").hide("800");
	jQuery("input[name='supplierId']").val("");
	jQuery("input[name='supplierName']").val("");
	jQuery("input[name='linkName']").val("");
	jQuery("textarea[name='linkPhone']").val("");
}
function updateSupper(id, dm){
	var supplierName = jQuery(dm).parents("div[supplierId]").attr("supplierName");
	var linkName = jQuery(dm).parents("div[supplierId]").attr("linkName");
	var linkPhone = jQuery(dm).parents("div[supplierId]").attr("linkPhone");
	jQuery("input[name='supplierId']").val(id);
	jQuery("input[name='supplierName']").val(supplierName);
	jQuery("input[name='linkName']").val(linkName);
	jQuery("textarea[name='linkPhone']").val(linkPhone);
	jQuery(".zzc").show('800');
}
function saveSupper(){
	var supplierIds = jQuery("input[name='supplierId']").val();
	var supplierName = jQuery("input[name='supplierName']").val();
	var linkName = jQuery("input[name='linkName']").val();
	var linkPhone = jQuery("textarea[name='linkPhone']").val();
	var data = "";
	if (supplierIds == ""){
		data = "supplierName=" +supplierName+ "&linkName=" +linkName+ "&linkPhone=" +linkPhone;
	}else {
		data = "supplierId=" +supplierIds+ "&supplierName=" +supplierName+ "&linkName=" +linkName+ "&linkPhone=" +linkPhone;
	}
	console.log(data);
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"supplierInfo/saveSupplierInfo",
        data: data,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function(data) {
        	var supplierInfo = data.msg;
        	console.log(supplierInfo);
        	if (jQuery("div[supplierId="+supplierInfo.supplierId+"]").length == 1){
        		jQuery("div[supplierId="+supplierInfo.supplierId+"]").attr("supplierName", supplierInfo.supplierName);
        		jQuery("div[supplierId="+supplierInfo.supplierId+"]").attr("linkName", supplierInfo.linkName);
        		jQuery("div[supplierId="+supplierInfo.supplierId+"]").attr("linkPhone", supplierInfo.linkPhone);
        		jQuery("div[supplierId="+supplierInfo.supplierId+"]").children(".add_supplier_content_left").empty();
        		var html = '<p class="add_supplier_adjust">供应商：'+supplierInfo.supplierName +'<span onclick="updateSupper('+supplierInfo.supplierId+', this)">修改</span>'+
							'</p>'+
							'<p style="margin: 10px 0">地址：'+supplierInfo.linkName +'</p>'+
							'<p>联系方式：'+supplierInfo.linkPhone +'</p>';
				jQuery("div[supplierId="+supplierInfo.supplierId+"]").children(".add_supplier_content_left").append(jQuery(html));
        	}
        	else {
        		var html = '<div supplierId="'+supplierInfo.supplierId +'" supplierName="'+supplierInfo.supplierName +'" linkName="'+supplierInfo.linkName +'" linkPhone="'+supplierInfo.linkPhone +'"  class="add_supplier_content_padding clearfix">'+
									'<div class="add_supplier_content_left ">'+
								'<p class="add_supplier_adjust">供应商：'+supplierInfo.supplierName +'<span onclick="updateSupper('+supplierInfo.supplierId+', this)">修改</span>'+
								'</p>'+
								'<p style="margin: 10px 0">地址：'+supplierInfo.linkName +'</p>'+
								'<p>联系方式：'+supplierInfo.linkPhone +'</p>'+
							'</div>'+
							'<div class="add_supplier_content_right">'+
								'<span class="add_slide" onclick="showAdd('+supplierInfo.supplierId+')"> <img src="'+baseUrl+'images/add_slide.png"> </span>'+
							'</div>'+
						'</div>';
				jQuery(".add_supplier_content").append(jQuery(html));
        	}
			jQuery(".zzc").hide('800');
        }
	});
}
function updateBrand(opt, id){
	jQuery(".zzc1").show('800');
	supplierId = jQuery(opt).parents("div[supplierId]").attr("supplierId");
	jQuery("input[name='brandId']").val(id);
	jQuery("input[name='brandName']").val(jQuery(opt).parents("span[brandId]").attr("brandName"));
}
function saveBrand(){
	var brandId = jQuery("input[name='brandId']").val();
	var brandName = jQuery("input[name='brandName']").val();
	if (brandId == ""){
		data = "brandName=" +brandName+ "&supplierId=" +supplierId;
	}else {
		data = "brandName=" +brandName+ "&supplierId=" +supplierId + "&brandId=" +brandId;
	}
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"goodsInfo/save/brand",
        data: data,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function(data) {
        	var brand = data.msg;
        	if (jQuery("span[brandId="+brand.brandId+"]").length == 1){
        		jQuery("span[brandId="+brand.brandId+"]").attr("brandName", brand.brandName);
        		jQuery("span[brandId="+brand.brandId+"]").children("input").attr("placeholder", brand.brandName);
        	}else{
        		var html = '<span brandId="'+brand.brandId +'" brandName="'+brand.brandName +'">'+
								'<input type="text" placeholder="'+brand.brandName +'" style="height: 14px; width: 100px; margin-left: 5px; padding-left: 4px"><i><img src="'+baseUrl+'images/demo2_down.png"></i>'+
								'<ul class="demo_fade" style="display: none;">'+
									'<li onclick="deleted(this, '+brand.brandId +')"><img src="'+baseUrl+'images/handle_2.png"></li>'+
									'<li onclick="updateBrand(this, '+brand.brandId +')"><img src="'+baseUrl+'images/handle_1.png"></li>'+
								'</ul>'+
							'</span>';
				jQuery("div[supplierId="+brand.supplierId+"]").children(".add_supplier_content_right").children(".add_slide").before(jQuery(html));
        	}
        	jQuery('.zzc1').hide('800');
        	supplierId = null;
        }
	});
}
function showAdd(id){
	jQuery('.zzc1').show('800');
	supplierId=id;
	jQuery("input[name='brandId']").val("");
	jQuery("input[name='brandName']").val("");
}
function deleted(opt, id){
	var data = "brandId=" + id;
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"goodsInfo/deleteGoodsBrand",
        data: data,
        dataType: "json",
        success: function(data) {
        	jQuery(opt).parents("span[brandId]").hide('800');
        }
	});
}
</script>
</body>
</html>