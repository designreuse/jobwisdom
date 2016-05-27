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
								商品品牌： <input name="brandId" type="text" style="box-shadow: 0 0 3px #ccc;left: 53px">
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
</script>
</html>