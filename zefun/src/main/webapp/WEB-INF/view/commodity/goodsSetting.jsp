<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xss26.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/roll.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/demo2.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/lang/zh-cn/zh-cn.js"></script>
<style>
input[type=radio] {
    margin-left: 0px!important;
}
.add_pic_ ul li{
	width: 220px;
	margin-top: 45px;
}
.add_pic_ ul li em img{
	width: 220px;
}
.addImage {
	position: relative;
	left: -604px;
	top: 54px;
	z-index: 1000;
	width: 20px;
	height: 20px;
	text-align: center;
	line-height: 20px;
	display: inline-block;
	border: 1px solid #fafafa;
}
</style>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript">
    
    function editPage (imgUrl) {
    	xiuxiu.setLaunchVars("cropPresets", "375*200");
    	xiuxiu.embedSWF("altContent2", 5, 700, 500);
    	xiuxiu.onInit = function (id)
    	{
            xiuxiu.setUploadType(3);
            if (imgUrl != null) {
            	xiuxiu.loadPhoto(qiniuUrl + imgUrl, false);
            }
    	}
    	xiuxiu.onSaveBase64Image = function (data, fileName, fileType, id)
    	{
            zccCallback(data);
            xiuxiu.onClose();
    	}
    	
    	xiuxiu.onDebug = function (data)
    	{
            dialog("网咯繁忙，请重试！");
    	}
    	xiuxiu.onClose = function (id)
    	{
            jQuery(".mask").hide();
    	}
    }
</script>  
<script>
     //切换
      jQuery(function(){
	     jQuery('.add_store_include .add_store_content:gt(0)').hide();
	     jQuery('.add_store_back li ').click(function(){
		   jQuery(this).addClass('active').siblings().removeClass('active');
		   jQuery('.add_store_include .add_store_content').eq(jQuery(this).index()).show().siblings().hide()
        });
	  });	
	   //轮播
	   var now_=0;
 	jQuery(function(){
	     var count=jQuery('.roll_ul li').size();
		 
	  //向右走
      jQuery(document).on("click",'.right_click',function(){
    	 count=jQuery('.roll_ul li').size();
         if(now_<=count-5){
		    now_+=1;
	        jQuery(this).parent('').find('.roll_ul ul').stop(true,true).animate({
		       left:-187*now_
		       }) 
			  }
		  });
	  //向左走
	  
	  	//向左走
	 jQuery('.left_click').click(function(){
		 count=jQuery('.roll_ul li').size();
         if(now_>=1){
		    now_-=1;
	         jQuery(this).parent('').find('.roll_ul ul').stop(true,true).animate({
		     left:-187*now_
		   
		     }) 
		  }	
        		
	  });
 });
 	
 	
 	jQuery(function(){
 		  jQuery('.add_store_back li').each(function(i){
 			jQuery(this).attr("class",'num'+i); 
 	       })
 		  })
 		function list(num){
 	       var num_=jQuery('.add_store_back li');
 		    num_.eq(num).attr({'id':'active'+num}).siblings().attr({'id':''});
 		    jQuery('.add_store_include .add_store_content').eq(num).show().siblings().hide(); 	
 	 }
 		
</script>
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right clearfix">
					<div class="add_store_div clearfix">
						<ul class="clearfix add_store_back">
							<li id="active0" onclick="list(0)" style="position:relative;z-index:2"><span>上架商品</span></li>
							<li class="" onclick="list(1)" style="position:relative;top:-55px"><span style="position: relative; top: 28px;">价格<i style="transform: rotate(-45deg);">\</i>提成<i style="transform: rotate(-45deg);">\</i>会员价格
							</span></li>
						</ul>

						<div class="add_store_include">
							<div class="add_store_content clearfix" style="display: ;">
								<div class="add_store_sale">
									<p>
										<span><em>选择卖品商品</em><select name="aId" onchange="queryGoodsInfo(this.value)"><option value="0">名称/编号</option>
																	<c:forEach items="${goodsInfos }" var="goodsInfo">
																	<option value="${goodsInfo.goodsId }">${goodsInfo.goodsName }</option>
																	</c:forEach>
																  </select><i  class = "addcolor">*</i></span><span>商品名称：<i name="goodsName"></i></span> <span>商品编号：<i name="goodsCodeSuffix"></i></span>
									</p>
									<p>
										<span><em>所属部门</em>
										<select name="deptId" onchange="choseCategory(this.value)">
										<c:forEach var="deptGoods" items="${deptGoodsBaseDto }">
											<option value="${deptGoods.deptId }">${deptGoods.deptName }</option>
										</c:forEach>
										</select></span><span>
										商品系列<select name="categoryId" style="margin-left: 20px">
										<c:forEach var="goodsInfoCategoryList" items="${deptGoodsBaseDto[0].goodsCategoryBaseDto }">
											<option value="${goodsInfoCategoryList.categoryId }">${goodsInfoCategoryList.categoryName }</option>
										</c:forEach>
										</select></span>
									</p>
								</div>

								<div class="add_pic_">
									<p>添加图片</p>
									<ul class="clearfix">
										<li><em><img  goodsImage="system/profile/add_img2.png" name="goodsImage" src="<%=qiniu%>system/profile/add_img2.png"></em></li>
										<li><em><img  affiliatedImage="system/profile/add_img2.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img2.png"></em></li>
										<li><em><img  affiliatedImage="system/profile/add_img2.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img2.png"></em></li>
										<li><em><img  affiliatedImage="system/profile/add_img2.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img2.png"></em></li>
										<li><em><img  affiliatedImage="system/profile/add_img2.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img2.png"></em></li>
										<li><em><img  affiliatedImage="system/profile/add_img2.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img2.png"></em></li>
									</ul>
								</div>

								<div class="item_saying">
									<p>项目描述</p>
									<div class="textarea1">
										<!-- <div>
											<button id="editImage" style="width: 130px; height: 26px; line-height: 26px; text-align: center; border: none; background: #617195; color: white; border-radius: 10px; margin-top: 10px; margin-left: 10px">插入图片</button>
										</div> -->
										<P></P>
										<div class="clearfix">
											<span id="editImage" class="addImage" title="插入图片"> <img
												src="<%=basePath%>images/insert_img.png"
												style="position: relative; left: 1px; top: 1px">
											</span>
											<script id="editor1" type="text/plain" style="width:550px;height:322px;float: left"></script>
											<div style="float: left; width: 320px; height: 420px; margin-top: 25px" class="textarea_text">
												<p>在此编辑的内容，将会在移动端－在线商城－商品详情中展示。</p>
												<p></p>
												<p>插入图片后，请保持图片的原样。切勿拖拽图片大小。自动生成的图片可自动适配所有手机显示。</p>
												<p>如若无法预览或全屏编辑或出现其他编辑问题。请更换谷歌浏览器，体验更佳。</p>
											</div>
										</div>
									</div>
								</div>
								<div class="item_button">
									<button onclick="saveImage()">保存</button>
									<button onclick="window.location.href='<%=basePath%>goodsInfo/view/goodsInfoList'">取消</button>
								</div>
							</div>
							<div class="add_store_content clearfix" style="display: block;">
								<div class="shop_price">
									<p class="shop_price_1">
										门店价格<span><input name="goodsPrice" type="text"><i  class = "addcolor">*</i><em>元</em></span>
									</p>
									<p class="shop_price_2">
										是否接受礼金<i><input onclick="jQuery(this).parent().next().next().show();jQuery(this).parent().next().next().next().show();" type="radio"  name="isCashDeduction" value="1">是</i><i><input onclick="jQuery('input[name=\'highestDiscount\']').val('0');jQuery(this).parent().next().hide();jQuery(this).parent().next().next().hide();" type="radio" name="isCashDeduction" value="0">否</i><i>最大抵扣礼金</i><span><input name="highestDiscount" type="text"><em>元</em></span>
									</p>
									
								</div>

								<div class="percentage">
									<p>业绩</p>
									<div class="percentage_content" style="height: 40px; padding-top: 10px">
										员工销售业绩方式<span><input onclick="jQuery(this).parents('.percentage_content').find('em').text('元')" type="radio" name="calculationType" value="2">固定</span><span style="margin-left: 67px"><input onclick="jQuery(this).parents('.percentage_content').find('em').text('%')" type="radio" name="calculationType" value="1">比例</span><span
											style="margin-left: 64px; position: relative">员工销售业绩值<input name="onlineShoppingPrice" type="text"><em>元</em></span>
									</div>
								</div>
								
								<div class="percentage">
									<p>提成</p>
									<div class="percentage_content" style="height: 40px; padding-top: 10px">
										提成方式<span style="margin-left: 80px"><input onclick="jQuery(this).parents('.percentage_content').find('em').text('元')" type="radio" name="commissionType" value="2">固定</span><span style="margin-left: 67px"><input onclick="jQuery(this).parents('.percentage_content').find('em').text('%')" type="radio" name="commissionType" value="1">比例</span><span
											style="margin-left: 64px; position: relative">现金提成<input name="commissionAmount" type="text"><em>元</em></span><span style="margin-left: 11px; position: relative">卡金提成<input name="cardAmount" type="text"><em>元</em></span>
									</div>
								</div>


								<div class="vip_price">
									<p>会员价格</p>
									<div class="vip_price_content">
										<p>
											为不同会员设置折扣
											<button onclick="addLevel()" style="width: 126px; height: 26px; text-align: center; line-height: 26px; border-radius: 10px; color: white; border: none; background: #657392; position: relative; left: 330px">新建</button>
										</p>
										<div class="vip_roll">
											<span class="left_click"><img src="<%=basePath%>images/left_click.png"></span>
											<div class="roll_ul">
												<ul id="memberLevel" class="clearfix">
												
												</ul>
											</div>
											<span class="right_click"><img src="<%=basePath%>images/right_click.png"></span>
										</div>

									</div>
								</div>

								<div class="demo2_button">
									<button onclick="savePrice()">保存</button>
									<button onclick="window.location.href='<%=basePath%>goodsInfo/view/goodsInfoList'">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>

			</div>
		</div>

		<div class="mask" style="display: none;">
		   <div id="flashEditorOut" style="position: relative;">
		   <span class="mask_close" style="position:absolute;right:-5px;top:-5px"><img onclick="xiuxiu.onClose();" src="<%=basePath %>images/seo_close.png"></span>
			        <div id="altContent2">
			            <h1>美图秀秀</h1>
			        </div>
				</div>
		</div>

		<div class="zzc one">
			<div class="photo_cut">
				<div id="clipArea"></div>
				<input type="file" id="file" style="position: absolute; width: 100px; height: 40px; left: 212px; bottom: 8px; opacity: 0; cursor: pointer; filter: alpha(opacity = 0);">
				<button id="clipBtn" style="position: absolute; width: 100px; height: 40px; left: 346px; bottom: 8px; opacity: 0; cursor: pointer; filter: alpha(opacity = 0);">截取</button>
				<div class="button_panel">
					<button class="selectpic">选择图片</button>
					<button class="sureinput">确定上传</button>
					<button class="cancelinput">取消</button>
				</div>
			</div>
		</div>
</body>
<script id="memberLevelSelect" type="text/html">
													<li>
														<p>
															会员等级<select name="levelId">
															<c:forEach items="${memberLevels }" var="memberLevel"><option value="${memberLevel.levelId }">${memberLevel.levelName }</option></c:forEach>
															</select>
														</p>
														<p>
															会员价格<input type="text" name="discountAmount">
														</p>
														<div class="li_button">
															<button>编辑</button>
															<button onclick="jQuery(this).parents('li').remove()">删除</button>
														</div>
													</li>
</script>
<script>
	var cssWidth = 375;
	var cssHeight = 200;
	var qiniu = '<%=qiniu%>';
	var imgObject;
	var type = 1;
	jQuery(function() {
		jQuery('.add_pic_ img').click(function() {
			jQuery(".mask").show();
			editPage(jQuery(this).attr("goodsimage"));
			imgObject = jQuery(this);
			type=1;
		})
		jQuery('.cancelinput').click(function() {
			jQuery('.zzc.one').hide();
			jQuery('.photo-clip-rotateLayer').html('');
		})
		jQuery('#editImage').click(function() {
			type=2;
			jQuery(".mask").show();
			editPage(null);
		})
	})
	function zccCallback(dataBase64) {
		if(type==1)imgObject.attr("src", dataBase64);
		var key = "jobwisdom/goods/" + new Date().getTime();
		var data = {
			"stringBase64" : dataBase64,
			"key" : key
		};
		jQuery('.cancelinput').click();
		jQuery.ajax({
			type : "POST",
			url : baseUrl + "qiniu/base64",
			data : JSON.stringify(data),
			contentType : "application/json",
			dataType : "json",
			async : true,
			beforeSend : function() {
				console.log("beforeSend upload image");
			},
			error : function() {
				console.log("upload image error");
			},
			complete : function() {
				console.log("complete upload image");
			},
			success : function(data) {
				var imageUrl = data.msg.imageUrl;
				var key = data.msg.key;
				if(type==1){
					if ((typeof (imgObject.attr("goodsImage"))) != "undefined") {
						imgObject.attr("goodsImage", key);
						imgObject.attr("src", qiniu+key);
					} else {
						imgObject.attr("affiliatedImage", key);
						imgObject.attr("src", qiniu+key);
					}
				}else{
					u1.execCommand('insertHtml', '<img style="margin-top: 0px; width: 100%; padding: 0px; border-color: rgb(30, 155, 232); color: inherit; height: 100%;" data-width="100%" border="0" vspace="0" src="'+qiniu+key+'">');
				}
				console.log(imageUrl);
			}
		});
	}
	var toolbars = {
			toolbars: [
			   		['fullscreen', 'source', '|', 'undo', 'redo', '|',
			           'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript','|', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
			           'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
			           'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
			           'directionalityltr', 'directionalityrtl', 'indent', '|',
			           'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase','preview']
			   	],maximumWords:3000,elementPathEnabled:false,imageScaleEnabled:false,wordCount:false,autoHeightEnabled:false};
	var u1 = UE.getEditor('editor1', toolbars);
	

</script>
<script type="text/javascript" src="<%=basePath%>js/base/zcc.js"></script>
<script>
	var deptGoodsBaseDtoList = ${js_deptGoodsBaseDto};
	var memberLevelList = ${memberLevelList};

	var storeId = '${session_key_store_id}';
	var deptId = deptGoodsBaseDtoList[0].deptId;

	var goodsId = null;
	var aId = null;
	var goodsInfo = null;
	var goodsDiscountList = null;
	
	var queryAID = '${aId}';

	/**
	 * 更换部门切换类别
	 */
	function choseCategory(deptIds) {
		deptId = deptIds;
		for (var int = 0; int < deptGoodsBaseDtoList.length; int++) {
			if (deptGoodsBaseDtoList[int].deptId == deptId) {
				jQuery("select[name='categoryId']").empty();
				for (var j = 0; j < deptGoodsBaseDtoList[int].goodsCategoryBaseDto.length; j++) {
					var categoryId = deptGoodsBaseDtoList[int].goodsCategoryBaseDto[j].categoryId
					var categoryName = deptGoodsBaseDtoList[int].goodsCategoryBaseDto[j].categoryName
					var html = '<option value="'+categoryId+'">' + categoryName
							+ '</option>';
					jQuery("select[name='categoryId']").append(jQuery(html));
				}
			}
		}
	}
	
	
	/**查找项目*/
	function queryGoodsInfo(goodsIds){
		if(typeof(goodsIds) == "undefined"){
			dialog("暂无可上架商品");
			return;
		}
		if(goodsIds == "0")return;
		aId = goodsIds;
		jQuery.ajax({
	        cache: true,
	        type: "GET",
	        async: false,
	        url: baseUrl+"goodsInfo/queryGoodsInfoById?goodsId="+goodsIds,
	        success: function(data) {
	        	if (data.code == 3){
	        		var goodsInfo = data.msg;
	        		jQuery("i[name='goodsName']").text(goodsInfo.goodsName);
		        	jQuery("i[name='goodsCodeSuffix']").text(goodsInfo.goodsCodeSuffix);
		        	clearInput();
	        		return;
	        	}
	        	var goodsInfo = data.msg.goodsInfo;
	        	goodsId = goodsInfo.goodsId;
	        	var goodsDiscountList = data.msg.goodsDiscountList;
	        	console.log(goodsInfo);
	        	jQuery("select[name='deptId']").val(goodsInfo.deptId);
	        	choseCategory(goodsInfo.deptId);
	        	jQuery("select[name='categoryId']").val(goodsInfo.categoryId);
	        	jQuery("i[name='goodsName']").text(goodsInfo.goodsName);
	        	jQuery("i[name='goodsCodeSuffix']").text(goodsInfo.goodsCodeSuffix);
	        	if (goodsInfo.goodsImage!=null){jQuery("img[name='goodsImage']").attr("goodsImage", goodsInfo.goodsImage);jQuery("img[name='goodsImage']").attr("src", qiniu+goodsInfo.goodsImage);}
	        	
	        	var affiliatedImage = goodsInfo.affiliatedImage;
	        	if (affiliatedImage!=null){
	        		for (var i = 0; i < affiliatedImage.split(",").length; i++) {
		    			jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage", affiliatedImage.split(",")[i]);
		    			jQuery("img[name='affiliatedImage']").eq(i).attr("src", qiniu + affiliatedImage.split(",")[i]);
		    		}
	        	}
	    		jQuery("input[name='goodsPrice']").val(goodsInfo.goodsPrice);
	    		jQuery("input[name='isCashDeduction'][value='"+ goodsInfo.isCashDeduction + "']").click();
	    		jQuery("input[name='highestDiscount']").val(goodsInfo.highestDiscount);
	    		jQuery("input[name='calculationType'][value='"+ goodsInfo.calculationType + "']").click();
	    		jQuery("input[name='onlineShoppingPrice']").val(goodsInfo.onlineShoppingPrice);
	    		jQuery("input[name='commissionType'][value='"+ goodsInfo.commissionType + "']").click();
	    		jQuery("input[name='commissionAmount']").val(goodsInfo.commissionAmount);
	    		jQuery("input[name='cardAmount']").val(goodsInfo.cardAmount);
	    		if(goodsInfo.goodsDesc!=null){u1.setContent(goodsInfo.goodsDesc);}
	    		
	    		for (var i = 0; i < goodsDiscountList.length; i++) {
	    			addLevel();
	    			jQuery("select[name='levelId']").eq(i).val(goodsDiscountList[i].levelId);
	    			jQuery("input[name='discountAmount']").eq(i).val(goodsDiscountList[i].discountAmount);
	    		}
	    		
	        }
        });
	}
	
	function saveImage(){
		if (goodsId==null){savePrice()}
		if (aId==null){dialog("请先选择一个商品");return;}
	
		var data = null;
		var deptId = jQuery("select[name='deptId']").val();
		var categoryId = jQuery("select[name='categoryId']").val();
		var goodsDesc = u1.getContent();
		var goodsImage = jQuery("img[name='goodsImage']").attr("goodsImage");
		var affiliatedImage = "";
		for (var i = 0; i < jQuery("img[name='affiliatedImage']").length; i++) {
			if (i == (jQuery("img[name='affiliatedImage']").length - 1)) {
				affiliatedImage = affiliatedImage + jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage");
			} else {
				affiliatedImage = affiliatedImage + jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage") + ",";
			}
		}
		data = {
			"storeId" : storeId,
			"aId" : aId,
			"goodsId" : goodsId,
			"deptId" : deptId,
			"categoryId" : categoryId,
			"goodsDesc" : goodsDesc,
			"goodsImage" : goodsImage,
			"affiliatedImage" : affiliatedImage
		};
		data = {"goodsInfo":data,"levelId":[],"discountAmount":[]};
		jQuery.ajax({
			type : "post",
			data : JSON.stringify(data),
			url : baseUrl + "goodsInfo/saveGoodsInfo",
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(data) {
				goodsId = data.msg;
				dialog("该商品更新");
			}
		});
		jQuery("select[name='aId']").attr("disabled",true);
	}
	
	function savePrice(){
		if (aId==null){saveImage()};
		if (goodsId==null){dialog("请先选择一个商品");return;}
		var deptId = jQuery("select[name='deptId']").val();
		var goodsPrice = jQuery("input[name='goodsPrice']").val();
		if (goodsPrice == ""){goodsPrice = 0;}
		var isCashDeduction = jQuery('input:radio[name="isCashDeduction"]:checked').val();
		var highestDiscount = jQuery("input[name='highestDiscount']").val();
		if (highestDiscount == ""){highestDiscount = 0;}
		var calculationType = jQuery('input:radio[name="calculationType"]:checked').val();
		var onlineShoppingPrice = jQuery("input[name='onlineShoppingPrice']").val();
		if (onlineShoppingPrice == ""){onlineShoppingPrice = 0;}
		var commissionType = jQuery('input:radio[name="commissionType"]:checked').val();
		var commissionAmount = jQuery("input[name='commissionAmount']").val();
		if (commissionAmount == ""){commissionAmount = 0;}
		var cardAmount = jQuery("input[name='cardAmount']").val();
		if (cardAmount == ""){cardAmount = 0;}
		
		data = {
			"storeId" : storeId,
			"goodsId" : goodsId,
			"aId" : aId,
			"deptId" : deptId,
			"goodsPrice" : goodsPrice,
			"isCashDeduction" : isCashDeduction,
			"highestDiscount" : highestDiscount,
			"calculationType" : calculationType,
			"onlineShoppingPrice" : onlineShoppingPrice,
			"commissionType" : commissionType,
			"commissionAmount" : commissionAmount,
			"cardAmount" : cardAmount
		};
		var levelIds = [];
		var discountAmounts = [];
		for (var i = 0; i < jQuery("select[name='levelId']").length; i++) {
			var levelId = jQuery("select[name='levelId']").eq(i).val();
			var discountAmount = jQuery("input[name='discountAmount']").eq(i).val();
			levelIds.push(levelId);
			discountAmounts.push(discountAmount);
		}
		data = {"goodsInfo":data,"levelId":levelIds,"discountAmount":discountAmounts};
		console.log(data)
		jQuery.ajax({
			type : "post",
			data : JSON.stringify(data),
			url : baseUrl + "goodsInfo/saveGoodsInfo",
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(data) {
				goodsId = data.msg;
				dialog("该商品更新");
			}
		});
		jQuery("select[name='aId']").attr("disabled",true);
	}
	
	function addLevel(){jQuery("ul[class='clearfix']").eq(1).append(jQuery('#memberLevelSelect').html());}


	/** 非空校验 */
	function isNotNull(str) {
		if (str != null && str != '' && typeof (str) != "undefined")
			return true;
		return false;
	}
	/** 重新获取焦点的时候,去掉校验的红色框 */
	jQuery(function() {
		jQuery("input").focus(function() {
			jQuery(this).removeClass("border")
		});
		jQuery("input[name='isCashDeduction'][value='1']").click();
		jQuery("input[name='calculationType'][value='2']").click()
		jQuery("input[name='commissionType'][value='2']").click()
	})

	function trim(t) {
		if(t!=null)return (t || "").replace(/^\s+|\s+$/g, "");
	}
	
	function clearInput(){
		jQuery("img[name='goodsImage']").attr("goodsImage", "system/profile/add_img.png");
    	jQuery("img[name='goodsImage']").attr("src", qiniu+"system/profile/add_img.png");
		for (var i = 0; i < 6; i++) {
			jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage", "system/profile/add_img.png");
			jQuery("img[name='affiliatedImage']").eq(i).attr("src", qiniu + "system/profile/add_img.png");
		}
		jQuery("input[name='goodsPrice']").val("");
		jQuery("input[name='highestDiscount']").val("");
		jQuery("input[name='onlineShoppingPrice']").val("");
		jQuery("input[name='commissionAmount']").val("");
		jQuery("input[name='cardAmount']").val("");
		u1.setContent("");
		jQuery("#memberLevel").empty();
	}
</script>
</html>
<script type="text/javascript">
u1.ready(function() {
	if (queryAID!=''){
		jQuery("select[name='aId']").val(queryAID);
		queryGoodsInfo(queryAID);
		jQuery("select[name='aId']").attr("disabled",true);
	}
});

</script>