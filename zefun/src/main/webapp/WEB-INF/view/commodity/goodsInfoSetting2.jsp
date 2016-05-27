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
						<li step="2">
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
						</li>
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
							<%-- <div>
								项目所属部门： 
								<select name="deptId" style="box-shadow: 0 0 3px #ccc;" onchange="choseCategory(this.value)">
									<c:forEach var="deptGoods" items="${deptGoodsBaseDto }">
										<option value="${deptGoods.deptId }">${deptGoods.deptName }</option>
									</c:forEach>
								</select>
							</div>
							<div>
								设置商品类别：
								 <select name="categoryId" style="box-shadow: 0 0 3px #ccc;">
									<c:forEach var="goodsInfoCategoryList" items="${deptGoodsBaseDto[0].goodsCategoryBaseDto }">
										<option value="${goodsInfoCategoryList.categoryId }">${goodsInfoCategoryList.categoryName }</option>
									</c:forEach>
								</select>
							</div> --%>
							<div>
								商品品牌： <input name="brandId" type="text" style="box-shadow: 0 0 3px #ccc;left: 53px">
							</div>
							<div>
								商品名称：<input name="goodsName" type="text" style="box-shadow: 0 0 3px #ccc;">

							</div>
							<div>
								商品编号：<input placeholder="五位编码,例如 : 11223" name="goodsCodeSuffix" type="text" maxlength="5" style="box-shadow: 0 0 3px #ccc;">

							</div>
							<!-- <div style="position: relative; top: -30px">
								商品描述：<textarea name="goodsDesc" type="textarea" class="textarea" style="position: relative; top: 40px !important; left: 60px !important; box-shadow: 0 0 3px #ccc;border-radius:8px!important;width:206px!important"></textarea>
							</div> -->
						</div>

						<%-- <div class="tab_content_div_right" style="margin-left:160px">
								添加图片：
				              <ul class="add_pic clearfix">
							    <li><img goodsImage="system/profile/set_img.png" name="goodsImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							  </ul>
						</div> --%>

					</div>

					<!--价格-->
					<div class="tab_content_div clearfix">

						<div class="tab_content_div_left">
							<!-- <div>
								<span class="shopp"> 门店价格：</span><input name="goodsPrice" type="number" style="box-shadow: 0 0 3px #ccc;">

							</div> -->
							<div>
								<span class="shopp"> 成本金额：</span><input name="costPrice" type="number" style="box-shadow: 0 0 3px #ccc;">

							</div>
							<!-- <div>
								<span class="shopp"> 员工销售业绩：</span><input name="onlineShoppingPrice" type="number" style="position: relative; left: 34px; box-shadow: 0 0 3px #ccc;">

							</div>
							<div>
								员工业绩方式:
								<select name="calculationType" style="box-shadow: 0 0 3px #ccc;position: relative; left: 44px!important;">
									<option value="1">比例</option>
									<option value="2">固定</option>
								</select>
							</div>
							<div>
								<span class="shopp"> 最大抵扣礼金：</span><input name="highestDiscount" type="number" style="position: relative; left: 34px; box-shadow: 0 0 3px #ccc;">
							</div> -->
						</div>


						<div class="tab_content_div_right">
							<div class="choose">
								<span class="width_"> 是否非卖品：</span> <input type="radio" name="isSellProduct" class="yes" value="1" style="margin-left: 20px">是 <input type="radio" value="0" name="isSellProduct" class="yes"
									style="margin-left: 20px">否
							</div>
							<!-- <div class="choose">
								<span class="width_">是否接受礼金：</span> <input type="radio" name="isCashDeduction" class="yes" value="1" style="margin-left: 20px">是 <input type="radio" name="isCashDeduction" value="0"
									class="yes" style="margin-left: 20px">否
							</div> -->
						</div>
					</div>


					<!--提成设置-->
					<div class="tab_content_div clearfix">
						<div class="tab_content_div_left">
							<!-- <div>
								<span class="shopp">提成方式:</span>
								<select name="commissionType" style="box-shadow: 0 0 3px #ccc;">
									<option value="1">比例</option>
									<option value="2">固定</option>
								</select>
							</div>
							<div>
								<span class="shopp"> 现金提成：</span><input name="commissionAmount" type="number" style="box-shadow: 0 0 3px #ccc;">
							</div>
							<div>
								<span class="shopp"> 卡金提成：</span><input name="cardAmount" type="number" style="box-shadow: 0 0 3px #ccc;">
							</div>
							<div>
								<span class="shopp"> 当前库存：</span><input name="goodsStock" type="number" style="box-shadow: 0 0 3px #ccc;">
							</div> -->
							<div>
								<span class="shopp"> 警告库存：</span><input name="warnStock" type="number" style="box-shadow: 0 0 3px #ccc;">
							</div>
						</div>
					</div>

					<!--会员种类-->
					<div class="tab_content_div clearfix">
						<table class="table_" style="position: relative; left: 160px">
							<tr>
								<td>会员卡种类</td>
								<td>会员门店价</td>
								<td>门店价格</td>
								<td>会员成本金额</td>
							<tr>
						</table>
						<div class="tab_content_div_left cash">
							<div>
								<span class="price_">会员种类：</span> 
								<select name="discountLevel" class="select_" style="box-shadow: 0 0 2px #ccc; left: 26px">
								<c:forEach items="${memberLevels }" var="memberLevel">
									<option value="${memberLevel.levelId }">${memberLevel.levelName }</option>
								</c:forEach>
								</select>
							</div>
							<div>
								<span class="price_"> 会员门店价格：</span><input name="discountAmount" type="number" class="vip" style="box-shadow: 0 0 2px #ccc; left: 28px">
							</div>
							<div>
								<span class="price_">会员成本金额：</span><input name="memberCostPrice" type="number" class="vipcoin" style="box-shadow: 0 0 2px #ccc; left: 28px">

							</div>


							<button class="save_">添加</button>
						</div>
						<div class="write_3" style="position: relative; left: 60px;width: 120px;">
							<img src="<%=basePath %>images/webchat_add.png"><em style="position:relative;left:4px">编辑</em>
						</div>
						<div class="tab_content_div_right"></div>

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
	
	var deptGoodsBaseDtoList = ${js_deptGoodsBaseDto};
	var memberLevelList = ${memberLevelList};
	
	var status = 0;
	var stepNum = 1;

	var storeId = '${session_key_store_id}';
	var goodsId = '${goodsId}';
	var deptId = deptGoodsBaseDtoList[0].deptId;
	
	var goodsInfo = null;
	var goodsDiscountList = null;
	
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
					var html = '<option value="'+categoryId+'">' + categoryName + '</option>';
					jQuery("select[name='categoryId']").append(jQuery(html));
				}
			}
		}
	}
	
	var projectStep = 0;
	if (goodsId != '') {
		console.log('${goodsInfo}');
		goodsInfo = eval('('+trim('${goodsInfo}')+')');
		goodsDiscountList = eval('('+'${goodsDiscountList}'+')');
		projectStep = goodsInfo.projectStep;
		deptId = goodsInfo.deptId;
		status = 1;
		
		var deptName = jQuery("select[name='deptId']").find("option[value='"+deptId+"']").text();
		jQuery("select[name='deptId']").empty();
		jQuery("select[name='deptId']").append(jQuery('<option value="'+deptId+'">'+deptName+'</option>'));
		jQuery("select[name='deptId']").val(deptId);
		
		var categoryId = goodsInfo.categoryId;
		choseCategory(deptId);
		jQuery("select[name='categoryId']").val(categoryId);
		
		var goodsImage = goodsInfo.goodsImage;
		jQuery("img[name='goodsImage']").attr("goodsImage", goodsImage);
		jQuery("img[name='goodsImage']").attr("src",  qiniu+goodsImage);
		var affiliatedImage = goodsInfo.affiliatedImage;
		for (var i = 0; i < affiliatedImage.split(",").length; i++) {
			jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage", affiliatedImage.split(",")[i]);
			jQuery("img[name='affiliatedImage']").eq(i).attr("src", qiniu+affiliatedImage.split(",")[i]);
		}
		console.log(jQuery.trim("${goodsDesc}"));
		var Desc = "${goodsDesc}";
		
		jQuery("input[name='brandId']").val(goodsInfo.brandId);
		jQuery("input[name='goodsName']").val(goodsInfo.goodsName);
		jQuery("textarea[name='goodsDesc']").val(Desc);
		jQuery("input[name='goodsCodeSuffix']").val(goodsInfo.goodsCodeSuffix);
		
		if(projectStep>=2){
			
			jQuery("input[name='goodsPrice']").val(goodsInfo.goodsPrice);
			jQuery("input[name='costPrice']").val(goodsInfo.costPrice);
			jQuery("input[name='onlineShoppingPrice']").val(goodsInfo.onlineShoppingPrice);
			jQuery("select[name='calculationType']").val(goodsInfo.calculationType);
			jQuery("input[name='highestDiscount']").val(goodsInfo.highestDiscount);
			
			jQuery("input[name='isSellProduct'][value='"+goodsInfo.isSellProduct+"']").click();
			jQuery("input[name='isCashDeduction'][value='"+goodsInfo.isCashDeduction+"']").click();
		}
		
		if(projectStep>=3){
			jQuery("select[name='commissionType']").val(goodsInfo.commissionType);
			jQuery("input[name='commissionAmount']").val(goodsInfo.commissionAmount);
			jQuery("input[name='cardAmount']").val(goodsInfo.cardAmount);
			jQuery("input[name='goodsStock']").val(goodsInfo.goodsStock);
			jQuery("input[name='warnStock']").val(goodsInfo.warnStock);
		}
		if(projectStep>=4){
			for (var i = 0; i < goodsDiscountList.length; i++) {
				var levelId = goodsDiscountList[i].levelId;
				var goodsPrice = goodsInfo.goodsPrice;
				var discountAmount = goodsDiscountList[i].discountAmount;
				var memberCostPrice = goodsDiscountList[i].memberCostPrice;
				var levelName = null;
				for (var j = 0; j < memberLevelList.length; j++) {
					if (levelId == memberLevelList[j].levelId){
						levelName = memberLevelList[j].levelName;
					}
				}
				var html = '<tr>'+
							'<td levelid="'+levelId+'">'+levelName+
							'</td><td>'+goodsPrice+
							'</td><td discountamount="'+discountAmount+'">'+discountAmount+
							'</td><td membercostprice="'+memberCostPrice+'">'+memberCostPrice+'</td></tr>';
				jQuery(".table_").children("tbody").append(jQuery(html));
			}
		}
	}
	else {
		goodsId = null;
	} 

	/**
	 * 保存数据,根据步骤去保存数据
	 */
	function save(){
		var data = coverDate(stepNum);
		console.log(data);
		var isOk = true;
		if (stepNum <= 2){
			jQuery.each(data,function(name,value) {
				if(!isNotNull(value) && name!="goodsId"){
					if(name=="goodsDesc"){
						jQuery("textarea[name='"+name+"']").addClass("border");
					}else{
						jQuery("input[name='"+name+"']").addClass("border");
					}
					isOk = false;
					return false;
				}
			});
		}
		if(!isOk){
			return ;
		}
		jQuery.ajax({
			type : "post",
			data : JSON.stringify(data),
			url : baseUrl + "goods/save/by/step/" + stepNum + "/" + status,
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(data) {
				goodsId = data.msg.goodsId;
				stepNum = data.msg.projectStep;
				projectStep = data.msg.projectStep;
				if (projectStep == 4){
					jQuery("li[step="+(projectStep)+"]").click();
				}
				else {
					jQuery("li[step="+(projectStep+1)+"]").click();
				}
			}
		});
	}
	//jQuery(".save").on("click", function() {})
	/**
	 * 拼装后台数据
	 */
	function coverDate(step) {
		if (step == 1) {
			var data = null;
			var goodsName = jQuery("input[name='goodsName']").val();
			var deptId = jQuery("select[name='deptId']").val();
			var categoryId = jQuery("select[name='categoryId']").val();
			var goodsDesc = jQuery("textarea[name='goodsDesc']").val();
			var goodsCodeSuffix = jQuery("input[name='goodsCodeSuffix']").val();
			var goodsImage = jQuery("img[name='goodsImage']").attr("goodsImage");
			var affiliatedImage = "";
			for (var i = 0; i < jQuery("img[name='affiliatedImage']").length; i++) {
				if (i == (jQuery("img[name='affiliatedImage']").length-1)){
					affiliatedImage = affiliatedImage + jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage");
				}else {
					affiliatedImage = affiliatedImage + jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage")+ ",";
				}
			}
			data = {
				"storeId" : storeId,
				"goodsId" : goodsId,
				"deptId" : deptId,
				"categoryId" : categoryId,
				"goodsName" : goodsName,
				"goodsDesc" : goodsDesc,
				"goodsCodeSuffix" : goodsCodeSuffix,
				"goodsImage" : goodsImage,
				"affiliatedImage" : affiliatedImage
			};
			deptId = deptId;
			return data;
		} else if (step == 2) {
			var goodsPrice = jQuery("input[name='goodsPrice']").val();
			var costPrice = jQuery("input[name='costPrice']").val();
			var onlineShoppingPrice = jQuery("input[name='onlineShoppingPrice']").val();
			var calculationType = jQuery("select[name='calculationType']").val();
			var highestDiscount = jQuery("input[name='highestDiscount']").val();
			var isSellProduct = jQuery('input:radio[name="isSellProduct"]:checked').val();
			var isCashDeduction = jQuery('input:radio[name="isCashDeduction"]:checked').val();
			data = {
				"storeId" : storeId,
				"goodsId" : goodsId,
				"goodsPrice" : goodsPrice,
				"costPrice" : costPrice,
				"onlineShoppingPrice" : onlineShoppingPrice,
				"calculationType" : calculationType,
				"highestDiscount" : highestDiscount,
				"isSellProduct" : isSellProduct,
				"isCashDeduction" : isCashDeduction
			};
			return data;
		} else if (step == 3) {
			var commissionType = jQuery("input[name='commissionType']").val();
			var commissionAmount = jQuery("input[name='commissionAmount']").val();
			var cardAmount = jQuery("input[name='cardAmount']").val();
			var goodsStock = jQuery("input[name='goodsStock']").val();
			var warnStock = jQuery("input[name='warnStock']").val();
			data = {
					"storeId" : storeId,
					"goodsId" : goodsId,
					"commissionType" : commissionType,
					"commissionAmount" : commissionAmount,
					"cardAmount" : cardAmount,
					"goodsStock" : goodsStock,
					"warnStock" : warnStock
				};
			return data;

		} else if (step == 4) {
				var data1 = [];
				var table = jQuery(".table_");
				for (var i = 2; i < table.find("tr").length; i++) {
					var levelId = table.find("tr").eq(i).children("td[levelid]").attr("levelid");
					var discountAmount = table.find("tr").eq(i).children("td[discountamount]").attr("discountamount");
					var memberCostPrice = table.find("tr").eq(i).children("td[membercostprice]").attr("membercostprice");
					var data = {"goodsId":goodsId,"levelId":levelId,"discountAmount":discountAmount,"memberCostPrice":memberCostPrice,"isDeleted":0};
					data1.push(data);
				}
				var data = {"data":data1,"goodsId":goodsId};
				return data;
		}
	}
	

	/**
	*　为项目table添加步骤和职位信息
	*/
	jQuery('.button_1').click(function(){
		var shiftMahjongId = jQuery("select[name='shiftMahjongId']").val();
		var shiftMahjongName = jQuery("select[name='shiftMahjongId']").find("option:selected").text();
		
	})
	
	/**
	*　table中添加新建的轮拍和职位信息
	*/
	function uploadMessageLevel(){
		var shiftMahjongId = jQuery("select[name='shiftMahjongId']").val();
		var shiftMahjongName = jQuery("select[name='shiftMahjongId']").find("option:selected").text();
		var projectStepName = jQuery("input[name='projectStepName']").val();
		var stepPerformanceType = jQuery("select[name='stepPerformanceType']").val();
		var stepPerformanceTypeName = jQuery("select[name='stepPerformanceType']").find("option:selected").text();
		var stepPerformance = jQuery("input[name='stepPerformance']").val();
		var isDisable = jQuery("input[name='isDisable']").val(); 
		//校验数据
		var checkJson = {"projectStepName":projectStepName,"stepPerformance":stepPerformance};
		var isOk = true;
		jQuery.each(checkJson,function(name,value) {
			if(!isNotNull(value)){
				jQuery("input[name='"+name+"']").addClass("border");
				isOk = false;
				return false;
			}
		});
		if(!isOk)return;
		
		var hasStep = jQuery(".table_s").find("td[rowspan]");
		var disableList = ['比例','固定'];
		var assignTypeList = ['','比例','固定'];
		
		var isHide = true;
		
		for (var i = 0; i < jQuery(".empLevel").length; i++) {
			var levelId = jQuery("select[name='levelId']").eq(i).val();
			var levelName = jQuery("select[name='levelId']").eq(i).find("option:selected").text();
			var assignType = jQuery("select[name='assignType']").eq(i).val();
			var assignTypeName = jQuery("select[name='assignType']").eq(i).find("option:selected").text();
			var assignCash = jQuery("input[name='assignCash']").eq(i).val();
			var assignCard = jQuery("input[name='assignCard']").eq(i).val();
			var appointmentReward = jQuery("input[name='appointmentReward']").eq(i).val();
			
			var checkJson = {"assignCash":assignCash,"assignCard":assignCard,"appointmentReward":appointmentReward};
			var isOk = true;
			jQuery.each(checkJson,function(name,value) {
				if(!isNotNull(value)){
					jQuery("input[name='"+name+"']").addClass("border");
					isOk = false;
					isHide = false;
					return false;
				}
			});
			if(!isOk)return;
			
			if(i==0){
				var tr = jQuery('<tr shiftMahjongId="'+shiftMahjongId+'" step='+(hasStep.length+1)+'>'+
						'<td rowspan="'+jQuery(".empLevel").length+'" style="border-right: 1px solid #dbdce2!important;box-shadow: 0 0 10px #ccc;">'+
						'<span style="position:relative;top:50%">'+(hasStep.length+1)+'</span>'+
						'</td><td>'+shiftMahjongName+'</td>'+
						'<td projectStepName="'+projectStepName+'">'+projectStepName+'</td>'+
						'<td stepPerformanceType="'+stepPerformanceType+'">'+stepPerformanceTypeName+'</td>'+
						'<td stepPerformance="'+stepPerformance+'">'+stepPerformance+'</td>'+
						'<td isDisable="'+isDisable+'">'+disableList[isDisable]+'</td>'+
						'<td levelId="'+levelId+'">'+levelName+'</td>'+
						'<td assignType="'+assignType+'">'+assignTypeList[assignType]+'</td>'+
						'<td assignCash="'+assignCash+'">'+assignCash+'</td>'+
						'<td assignCard="'+assignCard+'">'+assignCard+'</td>'+
						'<td appointmentReward="'+appointmentReward+'">'+appointmentReward+'</td>'+
						'</tr>');
				jQuery('.table_s').append(tr);
			}else {
				var tr = jQuery('<tr shiftMahjongId="'+shiftMahjongId+'" step='+(hasStep.length+1)+'>'+
						'<td>'+shiftMahjongName+'</td>'+
						'<td projectStepName="'+projectStepName+'">'+projectStepName+'</td>'+
						'<td stepPerformanceType="'+stepPerformanceType+'">'+stepPerformanceTypeName+'</td>'+
						'<td stepPerformance="'+stepPerformance+'">'+stepPerformance+'</td>'+
						'<td isDisable="'+isDisable+'">'+disableList[isDisable]+'</td>'+
						'<td levelId="'+levelId+'">'+levelName+'</td>'+
						'<td assignType="'+assignType+'">'+assignTypeList[assignType]+'</td>'+
						'<td assignCash="'+assignCash+'">'+assignCash+'</td>'+
						'<td assignCard="'+assignCard+'">'+assignCard+'</td>'+
						'<td appointmentReward="'+appointmentReward+'">'+appointmentReward+'</td>'+
						'</tr>');
				jQuery('.table_s').append(tr);
			}
		}
		if(isHide){
			jQuery('.step_1').hide();
			jQuery('.write_4').show();
		}
	}
	
	/**
	*  保存会员卡折扣
	*/
	jQuery('.save_').click(function(){
		var levelId = jQuery("select[name='discountLevel']").val();
		var levelName = jQuery("select[name='discountLevel']").find("option:selected").text();
		var discountAmount = jQuery("input[name='discountAmount']").val();
		var memberCostPrice = jQuery("input[name='memberCostPrice']").val();
		
		var checkJson = {"discountAmount":discountAmount,"memberCostPrice":memberCostPrice};
		var isOk = true;
		jQuery.each(checkJson,function(name,value) {
			if(!isNotNull(value)){
				jQuery("input[name='"+name+"']").addClass("border");
				isOk = false;
				return false;
			}
		});
		if(!isOk)return;
		
	    jQuery('.cash').hide();
	    jQuery('.write_3').show();
		var table=jQuery('.tab_content_div').find('.table_');
		var projectPrice = jQuery("input[name='projectPrice']").val();
		var html = '<tr>'+
					'<td levelId="'+levelId+'">'+levelName+'</td>'+
					'<td>'+projectPrice+'</td>'+
					'<td discountAmount="'+discountAmount+'">'+discountAmount+'</td>'+
					'<td memberCostPrice="'+memberCostPrice+'">'+memberCostPrice+'</td>'+
					'</tr>';
		table.append(jQuery(html));
	});

	jQuery(function(){
		jQuery('.tab_content_div:gt(0)').hide();
		jQuery('.right_head li').click(function(){
			var step = jQuery(this).attr("step");
			if (step > (projectStep+1)){
				dialog("请先完成前面的步骤吧");
				//return;
			}
			if (step <= (projectStep)){
				status = 1;
			}else{
				status = 0;
			}
			stepNum = step;
			jQuery(this).addClass('active').siblings().removeClass('active');
			jQuery('.tab_content_div').eq(jQuery(this).index()).show().siblings().hide();
		});
	})


	//会员种类
	jQuery(function(){
		jQuery('.write_3').click(function(){
			jQuery('.cash').show();
			jQuery(this).hide();
		});	
	})

	//职位
	 jQuery(function(){
		 jQuery('.write_4').click(function(){
		     jQuery('.step_1').fadeIn('1000');
			 jQuery(this).hide();
		});
	}) 

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