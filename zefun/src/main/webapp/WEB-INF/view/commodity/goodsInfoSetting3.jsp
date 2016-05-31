<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xss26.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/roll.css" type="text/css" />
<style type="text/css">
	.border {border:1px solid red!important}
</style>
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right'>
				<div class="right_head clearfix">
					<ul class="right_ul">
						<li step="1" class="active">
							<div>
								<img src="<%=basePath%>/images/tab_1.png">
							</div> <span>新增商品</span>
						</li>
						<%-- <li step="2">
							<div>
								<img src="<%=basePath%>/images/tab_2.png">
							</div> <span>设置价格</span>
						</li>
						<li step="3">
							<div>
								<img src="<%=basePath%>/images/tab_4.png">
							</div> <span>设置提成</span>
						</li>
						<li step="4">
							<div>
								<img src="<%=basePath%>/images/tab_3.png">
							</div> <span>会员折扣</span>
						</li> --%>
					</ul>
					<div class="right_button">
						<button class="save" onclick="save()">保 存</button>
						<button class="cancle" onclick='window.open("<%=basePath %>goodsInfo/view/setting","_self")'>取 消</button>
					</div>
				</div>
				<div class="tab_content">
					<!--新增项目-->
					<div class="tab_content_div clearfix">
						<div class="tab_content_div_left">
							<div>
								商品品牌：<input name="brandName" onclick="jQuery('#select-brand').modal()" type="text" style="box-shadow: 0 0 3px #ccc;left: 53px"> 
								<input name="brandId" type="hidden">
							</div>
							<div>
								商品名称：<input name="goodsName" type="text" style="box-shadow: 0 0 3px #ccc;">

							</div>
							<div>
								商品编号：<input placeholder="五位编码,例如 : 11223" name="goodsCodeSuffix" type="text" maxlength="5" style="box-shadow: 0 0 3px #ccc;">
							</div>
							<div>
								<span class="shopp"> 成本金额：</span><input name="costPrice" type="number" style="box-shadow: 0 0 3px #ccc;">
							</div>
							<div>
								<span class="shopp"> 警告库存：</span><input name="warnStock" type="number" style="box-shadow: 0 0 3px #ccc;">
							</div>
							<div class="tab_content_div_right">
								<div class="choose">
									<span class="width_"> 是否非卖品：</span> <input type="radio" name="isSellProduct" class="yes" value="1" style="margin-left: 20px">是 <input type="radio" value="0" name="isSellProduct" class="yes"
										style="margin-left: 20px">否
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
			
		</div>
	</div>
	
	<div class="modal hide" id="select-brand" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content select-wordimg-modal" style="width: 580px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">×</span>
	                </button>
	                <h4 class="modal-title" id="myModalLabel">
	                    	选择品牌
	                </h4>
	            </div>
	            <div class="modal-body">
	                <div class="border-head">
	                    <input type="text" class="search-input">
	                    <button type="button" class="btn search-button" id="search-brands" onclick="serchBrandByName(this)">搜索</button>
	                </div>
	                <div class="border-content">
	                    <div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
	                        <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" role="tablist">
	                            <li class="ui-state-default ui-corner-top" role="tab" tabindex="-1" aria-controls="tabs-1" aria-labelledby="ui-id-1" aria-selected="false"><a href="#tabs-1" class="ui-tabs-anchor" role="presentation" tabindex="-1" id="ui-id-1">本店品牌</a></li>
	                        </ul>
	                        <!-- 自己的品牌 -->
	                        <div id="tabs-1" aria-labelledby="ui-id-1" class="ui-tabs-panel ui-widget-content ui-corner-bottom" role="tabpanel" aria-expanded="false" aria-hidden="true" style="display: none;">
	                            <c:forEach items="${brands }" var="brand">
	                            <div class="brand" onclick="saveBrand(this)" brandId="${brand.brandId }" style="cursor: pointer;">
	                                <span>${brand.brandName }</span>
	                            </div>
	                            </c:forEach>
	                            <div class="clearfix"></div>
	                        </div>
	                        <!-- 自己的品牌 -->
	                    </div>
	                </div>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn" data-dismiss="modal">取消</button>
	                <button type="button" class="btn " id="confirm-menu-url" data-dismiss="modal">确认</button>
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

</body>

<script>
	var cssWidth = 200;
	var cssHeight = 200;
	var qiniu = '<%=qiniu %>';
	var imgObject;
	jQuery(function(){
	     jQuery('.add_pic li').click(function(){
	    	imgObject = jQuery(this);
		    jQuery('.zzc').show()
		 })
		 jQuery('.cancelinput').click(function(){
		    jQuery('.zzc').hide();
		    jQuery('.photo-clip-rotateLayer').html('');
		 })
	})
	function zccCallback(dataBase64){
		imgObject.children("img").attr("src", dataBase64);
		var key = "jobwisdom/goods/" + new Date().getTime();
		if ((typeof(imgObject.children("img").attr("goodsImage")))!="undefined"){
			imgObject.children("img").attr("goodsImage", key);
		}else {
			imgObject.children("img").attr("affiliatedImage", key);
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
</script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>
<script>
	

	var storeId = '${session_key_store_id}';
	var goodsId = '${goodsId}';
	
	var goodsInfo = null;
	
	if (goodsId != '') {
		console.log('${goodsInfo}');
		goodsInfo = eval('('+trim('${goodsInfo}')+')');
		var goodsName = jQuery("input[name='goodsName']").val(goodsInfo.goodsName);
		var goodsCodeSuffix = jQuery("input[name='goodsCodeSuffix']").val(goodsInfo.goodsCodeSuffix);
		var brandId = jQuery("input[name='brandId']").val(goodsInfo.brandId);
		var costPrice = jQuery("input[name='costPrice']").val(goodsInfo.costPrice);
		var warnStock = jQuery("input[name='warnStock']").val(goodsInfo.warnStock);
		jQuery("input[name='isSellProduct'][value='"+goodsInfo.isSellProduct+"']").click();
	}
	else {
		goodsId = null;
	} 

	/**
	 * 保存数据,根据步骤去保存数据
	 */
	function save(){
		var data = coverDate();
		console.log(data);
		var isOk = true;
		jQuery.each(data,function(name,value) {
			if(!isNotNull(value) && name!="goodsId"){
				isOk = false;
				return false;
			}
		});
		if(!isOk){
			return ;
		}
		jQuery.ajax({
			type : "post",
			data : JSON.stringify(data),
			url : baseUrl + "goods/save/by/base",
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(data) {
				goodsId = data.msg.goodsId;
			}
		});
	}
	/**
	 * 拼装后台数据
	 */
	function coverDate() {
		var data = null;
		var goodsName = jQuery("input[name='goodsName']").val();
		var goodsCodeSuffix = jQuery("input[name='goodsCodeSuffix']").val();
		var brandId = jQuery("input[name='brandId']").val();
		var costPrice = jQuery("input[name='costPrice']").val();
		var warnStock = jQuery("input[name='warnStock']").val();
		var isSellProduct = jQuery('input:radio[name="isSellProduct"]:checked').val();
		data = {
			"storeId" : storeId,
			"goodsId" : goodsId,
			"goodsName" : goodsName,
			"brandId" : brandId,
			"costPrice" : costPrice,
			"warnStock" : warnStock,
			"goodsCodeSuffix" : goodsCodeSuffix,
			"isSellProduct": isSellProduct
		};
		return data;
	}
	
	/**
	 * 改变radio的样式
	 * @param id
	 */
	function chkRadio(opt) {
		if (jQuery(opt).val() == "1"){
			opt.checked = false;
			jQuery(opt).val("0");
		}
		else {
			opt.checked = true;
			jQuery(opt).val("1");
		}
	}

	/** 非空校验 */
	function isNotNull(str){
		if(str!=null&&str!=''&&typeof(str)!="undefined")return true;
		return false;
	}
	/** 重新获取焦点的时候,去掉校验的红色框 */
	jQuery(function(){
		jQuery("input").focus(function(){jQuery(this).removeClass("border")});
	})
	
	function trim(t){
		return (t||"").replace(/^\s+|\s+$/g, "");
	}
	
	
	function selectBrand(){
		jQuery("#select-brand").modal();
	}
	function saveBrand(obj){
		var brandName = jQuery(obj).find("span").text();
		var brandId = jQuery(obj).attr("brandId");
		jQuery("input[name='brandId']").val(brandId);
		jQuery("input[name='brandName']").val(brandName);
		jQuery("#select-brand").modal('hide');
	}
	function serchBrandByName(obj){
		var brandName = jQuery(obj).prev().val();
		var brand1 = jQuery("#tabs-1").children("div[class='brand']");
		var brand2 = jQuery("#tabs-2").children("div[class='brand']");
		for (var i = 0; i < brand1.length; i++) {
			brand1.eq(i).hide();
			var name = brand1.eq(i).find("span").text();
			if(name.indexOf(brandName)!=-1){
				brand1.eq(i).show();
			}
		}
		for (var i = 0; i < brand2.length; i++) {
			brand2.eq(i).hide();
			var name = brand2.eq(i).find("span").text();
			if(name.indexOf(brandName)!=-1){
				brand2.eq(i).show();
			}
		}
	}
</script>
</html>