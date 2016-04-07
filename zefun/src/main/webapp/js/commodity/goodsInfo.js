jQuery(function(){
	jQuery('.lcs_check').lc_switch('是', '否');
	jQuery('#isSellProduct').lcs_off();
	jQuery('#isCashDeduction').lcs_off();
    jQuery('.commtype').lc_switch('比例','固定');
    jQuery(".calculationType").lc_switch('比例','固定');
    jQuery('body').delegate('.calculationType', 'lcs-off', function() {
        jQuery(this).siblings().css("background","#fbd04d").children().css("color","#123134");
    });
    jQuery('#calculationType').lcs_off();
    jQuery('body').delegate('.commtype', 'lcs-off', function() {
        jQuery(this).siblings().css("background","#fbd04d").children().css("color","#123134");
    });
    
    /*商品的展示与隐藏*/
    jQuery('.project-name').on("click", function(){
        var th = jQuery(this)
        var target = th.siblings();
        target.toggle();
        for (var i = 0; i < jQuery(this).children("i").length; i++) {
        	 if(jQuery(this).children("i").eq(i).attr("class") == "afont iconfont icon-iconfontxialaeps"){
             	jQuery(this).children("i").eq(i).attr("class","afont iconfont icon-iconfontxialaeps active");
             	return;
             } 
             if(jQuery(this).children("i").eq(i).attr("class") == "afont iconfont icon-iconfontxialaeps active"){
             	jQuery(this).children("i").eq(i).attr("class","afont iconfont icon-iconfontxialaeps");
             	return;
             }
		}
    });
    jQuery('body').delegate('.calculationType', 'lcs-statuschange', function() {
	    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
	    if(status == 'checked'){
	    	jQuery(this).val(1);
	    	jQuery(this).parent().parent().siblings().find("input[name='onlineShoppingPrice']").next().text("%");
	    }else{
	    	jQuery(this).val(2);
	    	jQuery(this).parent().parent().siblings().find("input[name='onlineShoppingPrice']").next().text("元");
	    }
	});
    jQuery('body').delegate('.lcs_check', 'lcs-statuschange', function() {
	    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
	    if(status == 'checked'){
	    	jQuery(this).val(1);
	    }else{
	    	jQuery(this).val(0);
	    }
	});
    
    jQuery('body').delegate('.commtype', 'lcs-statuschange', function() {
	    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
	    if(status == 'checked'){
	    	jQuery(this).val(1);
	    	jQuery(this).parent().parent().siblings().find("input[name='commissionAmount']").next().text("%");
	    	jQuery(this).parent().parent().siblings().find("input[name='cardAmount']").next().text("%");
	    }else{
	    	jQuery(this).val(2);
	    	jQuery(this).parent().parent().siblings().find("input[name='commissionAmount']").next().text("元");
	    	jQuery(this).parent().parent().siblings().find("input[name='cardAmount']").next().text("元");
	    }
	});
    
    //表单校验
    jQuery("#goodsInfoForm").Validform({
		tiptype: 1,
		btnSubmit: "#submitBtn",//指定表单提交按钮
		btnReset: ".btn_reset",//指定表单重置按钮
		ignoreHidden: true,//当为true时对:hidden的表单元素将不做验证
		postonce: true,//开启二次提交防御,为true时，在数据成功提交后，表单将不能再继续提交
		beforeSubmit: function(curform){
			saveGoodsInfo();
			return false;
		}
	});
    
});



/**搜索商品*/
function searchGodsInfo(obj){
	jQuery(".fuzzysearch").empty();
	var inputVal = jQuery(obj).val().trim();
	var str = "";
	var goodsNameObj = jQuery(".project-sublist-content");
	for(var i=0; i<goodsNameObj.length; i++){
		var goodsName = jQuery(goodsNameObj[i]).text();
		if(inputVal != ""){
			if(goodsName.indexOf(inputVal) == -1 ){
				jQuery(".fuzzysearch").hide();
			}else{
				var id = jQuery(goodsNameObj[i]).attr("id");
				str = str + '<li class="text-center" goodsId="'+id+'"><sapn>'+goodsName+'</sapn></li>';
			}
		}else{
			jQuery(".fuzzysearch").hide();
		}
	}
	str = str + '<em class="t-border"></em><span class="t-content"></span>';
	jQuery(".fuzzysearch").append(jQuery(str));
	if (jQuery(".fuzzysearch").children("li").length>0){
		jQuery(".fuzzysearch").show();
	}
}

jQuery(".fuzzysearch").delegate("li","click",function(){
	jQuery(".search-input").val(jQuery(this).children("sapn").text());
	jQuery("#"+jQuery(this).attr("goodsId")).triggerHandler("click");
	jQuery("#"+jQuery(this).attr("goodsId")).addClass("active");
});
jQuery("body").delegate(".search-input","blur", function(event){
	setTimeout(function () {
		jQuery(".fuzzysearch").css("display", "none");
	},500);
});
/**添加商品类别*/
function addBrand(deptId,obj){
	jQuery("#add-category-modal .iconfa-minus").closest("div").remove();
	jQuery("[name='categoryListName']").val('');
	
	jQuery("#bumenxinzeng").text(jQuery(obj).parent().text());
	jQuery("input[name='saveCategoryListDeptId']").val(deptId);
	jQuery("#add-category-modal").modal();
	stopBubble(obj);
	return;
	jQuery("input[name='categoryName']").val('');
	jQuery(obj).parent().parent().find("ul").find("#addBrandLi").show();
}

/**添加多类别modal*/
function addModalInputCategory(obj){
	var str = '<div><br><input type="hidden"><span>系列名称：</span> <input type="text" placeholder="系列名称" name="categoryListName" class="mr10 ml10 input-medium"><span class="fr font-span minus"><i class="iconfa-minus" onclick="deleteModalInputCategory(this)"></i></span></div>';
	jQuery(obj).parents(".modal-body").children(".modal-btn-group").before(str);
}
/**删除多类别添加中的一列*/
function deleteModalInputCategory(obj){
	jQuery(obj).parent().parent().remove();
}
/**保存类别集合*/
jQuery("#saveCategoryList").on("click",function(){
	var data = "data = data";
	for (var i = 0; i < jQuery("input[name='categoryListName']").length; i++) {
			data = data + "&categoryName=" + jQuery("input[name='categoryListName']").eq(i).val();
	}
	var deptId = jQuery("input[name='saveCategoryListDeptId']").val();
	data = data + "&deptId=" + deptId;
	console.log(data);
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"goodsInfo/saveGoodsCategorys",
        data: data,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function(data) {
            if(data.code == 0){
            	dialog("保存成功");
            	var categorys = data.msg;
            	for (var i = 0; i < categorys.length; i++) {
                	var str = '<ul class="project-sublist  category" style="display: block;">'+
				                	'<li class="project-sublist-title" id="categoryId'+categorys[i].categoryId+'">'+
				                	'<div style="display: block;">'+
				                	'<i class="afont iconfont icon-iconfontxialaeps"></i>'+
				                	'<span class="categoryNameSpan">&nbsp;'+categorys[i].categoryName+'</span>'+
				                	'<span class="fr">'+
				                	'<i class="iconfa-plus project-icon" onclick="addGoodsInfosList('+deptId+','+categorys[i].categoryId+',this)"></i>&nbsp;&nbsp;'+
				                	'<i class="iconfa-pencil project-icon" onclick='+'showBrand(this,"'+categorys[i].categoryName+'",'+categorys[i].categoryId+');'+'></i>'+
				                	'<i class="iconfa-trash project-icon" onclick="deleteBrandById('+categorys[i].categoryId+','+deptId+',this);"></i>'+
				                	'</span>'+
				                	'</div>'+
				                	'<div style="display: none;">'+
				                	'<input type="text" id="inputCategoryName" value="'+categorys[i].categoryName+'" style="width: 70%;">'+
				                	'<i class="iconfa-plus-sign project-icon" onclick="editBrand('+categorys[i].categoryId+','+deptId+',this);"></i>'+
				                	'<i class="iconfa-trash project-icon" onclick="cancelEditBrand(this);"></i>'+
				                	'</div>'+
				                	'</li>'+
				                	'</ul>';
            		jQuery("#deptId"+deptId).next().append(str);
            		//维护js中的属性值
                	maintenance(deptId,categorys[i].categoryName,categorys[i].categoryId,1);
				}
            	jQuery("#add-category-modal").modal('hide');
            }else{
            	dialog("error");
            }
        }
	});
});


/**批量新增项目信息*/
function addGoodsInfosList(deptId,categoryId,obj){
	stopBubble(obj);
	emptyInput();
	dialog("清空了,在右侧开始新增吧");
	/*jQuery("#add-goodsinfo-modal .iconfa-minus").closest("div").remove();
	jQuery("[name='goodsInfoListName']").val('');
	
	jQuery("input[name='saveGoodsInfoListDeptId']").val(deptId);
	jQuery("input[name='saveGoodsInfoListCategoryId']").val(categoryId);
	var categoryName = jQuery(obj).parent().prev().text();
	jQuery("#crutentCategoryName").text(categoryName);
	jQuery("#add-goodsinfo-modal").modal();*/
	
}
function addModalInputGoodsInfo(obj){
	var str = '<div><br><input type="hidden"><span>商品名称：</span> <input type="text" placeholder="商品名称" name="goodsInfoListName" class="mr10 ml10 input-medium"><span class="fr font-span minus"><i class="iconfa-minus" onclick="deleteModalInputGoodsInfo(this)"></i></span></div>';
	jQuery(obj).parents(".modal-body").children(".modal-btn-group").before(str);
}
function deleteModalInputGoodsInfo(obj){
	jQuery(obj).parent().parent().remove();
}
function saveGoodsInfoLists(obj){
	var data = "data=data";
	for (var i = 0; i < jQuery("input[name='goodsInfoListName']").length; i++) {
		if(jQuery("input[name='goodsInfoListName']").eq(i).val().length>8){
			dialog("名称不可超过8个字");
			return;
		}
	}
	for (var i = 0; i < jQuery("input[name='goodsInfoListName']").length; i++) {
			data = data + "&goodsInfoName=" + jQuery("input[name='goodsInfoListName']").eq(i).val();
	}
	var deptId = jQuery("input[name='saveGoodsInfoListDeptId']").val();
	var categoryId = jQuery("input[name='saveGoodsInfoListCategoryId']").val();
	data = data + "&deptId="+deptId + "&categoryId="+categoryId;
	console.log(data);
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"goodsInfo/saveGoodsInfos",
        data: data,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function(data) {
            if(data.code == 0){
            	dialog("保存成功");
            	var goodsInfos = data.msg;
            	for (var i = 0; i < goodsInfos.length; i++) {
            		var str = '<li class="project-sublist-content" id="goodsId'+goodsInfos[i].goodsId+'" onclick="queryGoodsInfoById('+goodsInfos[i].goodsId+',this);">'+goodsInfos[i].goodsName+
		            				'<span class="fr">'+
		            				    '<i class="icon-fuzhi iconfont" onclick="iconCopyGoodsInfo('+goodsInfos[i].goodsId+','+deptId+',this); title="复制"></i>&nbsp;'+
					                    '<i class="iconfa-trash project-icon" onclick="deleteGoodsInfo('+goodsInfos[i].goodsId+','+deptId+',this);" title="删除"></i>'+
					                '</span>'+
					           '</li>';
            		jQuery("#deptId"+deptId).nextAll().find("#categoryId"+categoryId).after(str);
				}
            	jQuery("#add-goodsinfo-modal").modal('hide');
            }else{
            	dialog("error");
            }
        }
	});
}


/**改变部门刷新商品类别*/
function selectCategory(select){
	var deptId = jQuery(select).val();
	reloadSelectCategory(deptId);
}

/**静态删除商品类别*/
function deleteBrand(obj){
	jQuery(obj).parent().hide();
}

/**显示编辑类别*/
function showBrand(obj,categoryName,categoryId){
	stopBubble(obj);
	jQuery(obj).parent().parent().hide();
	jQuery(obj).parent().parent().siblings().show();
}

/**取消编辑*/
function cancelEditBrand(obj){
	stopBubble(obj);
	jQuery(obj).parent().hide();
	jQuery(obj).parent().prev().show();
}

/**保存商品类别*/
function saveBrand(deptId,obj){
	var categoryName = jQuery(obj).parent().children("input[name='categoryName']").val(); 
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"goodsInfo/saveGoodsCategory",
        data: "categoryName="+categoryName+"&deptId="+deptId,
        success: function(data) {
            if(data.code == 0){
            	var categoryId = data.msg;
            	var str = '<ul class="project-sublist  category" style="display: block;">'+
            	'<li class="project-sublist-title" id="categoryId'+categoryId+'">'+
            	'<div style="display: block;">'+
            	'<i class="afont iconfont icon-iconfontxialaeps"></i>'+
            	'<span class="categoryNameSpan">'+categoryName+'</span>'+
            	'<span class="fr">'+
            	'<i class="iconfa-pencil project-icon" onclick='+'showBrand(this,"'+categoryName+'",'+categoryId+');'+'></i>'+
            	'<i class="iconfa-trash project-icon" onclick="deleteBrandById('+categoryId+','+deptId+',this);"></i>'+
            	'</span>'+
            	'</div>'+
            	'<div style="display: none;">'+
            	'<input type="text" id="inputCategoryName" value="'+categoryName+'" style="width: 70%;">'+
            	'<i class="iconfa-plus-sign project-icon" onclick="editBrand('+categoryId+','+deptId+',this);"></i>'+
            	'<i class="iconfa-trash project-icon" onclick="cancelEditBrand(this);"></i>'+
            	'</div>'+
            	'</li>'+
            	'</ul>';
            	jQuery(obj).parent().parent().append(str);
            	jQuery(obj).parent().hide();
            	jQuery("select[name='categoryId']").empty();
            	jQuery("select[name='categoryId']").append("<option value="+categoryId+">"+categoryName+"</option>");
            	//维护js中的属性值
            	maintenance(deptId,categoryName,categoryId,1);
            	dialog("保存成功");
            }else{
            	dialog("请稍后重试");
            }
        }
	});
}

/**编辑商品类别*/
function editBrand(categoryId,deptId,obj){
	stopBubble(obj);
	//var deptId = jQuery(obj).parent().parent().parent().find("input[name='categoryId']").eq(0).val();
	var categoryName = jQuery(obj).prev().val(); 
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"goodsInfo/editGoodsCategory",
        data: "categoryId="+ categoryId +"&categoryName="+categoryName+"&deptId="+deptId,
        success: function(data) {
            if(data.code == 0){
            	dialog("修改 成功");
            	//window.location.href = baseUrl+"goodsInfo/view/goodsInfoList";
            	jQuery(obj).parent().prev().find(".categoryNameSpan").text(categoryName);
            	jQuery(obj).parent().prev().show();
            	jQuery(obj).parent().hide();
            	//维护js中的属性值
            	maintenance(null,categoryName,categoryId,3);
            }else{
            	dialog("请稍后重试");
            }
        }
	});
}

/**删除商品类别*/
function deleteBrandById(categoryId,deptId,obj){
	var data = "categoryId="+categoryId+"&deptId="+deptId;
	for (var i = 0; i < deptGoodsList.length; i++) {
		if(deptGoodsList[i].deptId == deptId){
			for (var j = 0; j < deptGoodsList[i].goodsCategoryBaseDto.length; j++) {
				if(deptGoodsList[i].goodsCategoryBaseDto[j].categoryId == categoryId){
					for (var s = 0; s < deptGoodsList[i].goodsCategoryBaseDto[j].goodsBaseDtos.length; s++) {
						data = data + "&goodsId=" + deptGoodsList[i].goodsCategoryBaseDto[j].goodsBaseDtos[s].goodsId;
					}
				}
			}
		}
	}
	stopBubble(obj); 
	//var deptId = jQuery(obj).parent().parent().parent().parent().find("input[name='categoryId']").eq(0).val();
	if(confirm("删除该类别,将会删除类别下的所有商品,确定么?")){
		jQuery.ajax({
			type: "POST",
			url: baseUrl+"goodsInfo/deleteGoodsCategory",
	        data: data,
	        success: function(data) {
	            if(data.code == 0){
	            	dialog("删除成功");
	            	//window.location.href = baseUrl+"goodsInfo/view/goodsInfoList";
	            	jQuery(obj).parent().parent().parent().parent().hide();
	            	//维护js中的属性值
	            	maintenance(null,null,categoryId,2);
	            }else{
	            	dialog(data.msg);
	            }
	        }
		});
	}else{
		return;
	}

}

/**根据id查询商品信息*/
function queryGoodsInfoById(goodsId,obj){
	console.log(obj);
	emptyInput();
	console.log(window.event);
	jQuery(".project-sublist-content").removeClass("active");
	if(obj!=null&&typeof(window.event)!="undefined"){
		stopBubble(obj);
		jQuery(obj).addClass("active");
	}
	jQuery.ajax({
        cache: true,
        type: "GET",
        async: false,
        url: baseUrl+"goodsInfo/queryGoodsInfoById?goodsId="+goodsId,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	//锁定滚动条
            	jQuery('html,body').animate({scrollTop:0}, 800);
            	jQuery("#goodsInfoForm")[0].reset;
            	jQuery("#isUpdate").val(1);
            	
            	var map = data.msg;
            	var goodsInfo = map["goodsInfo"];
            	
            	jQuery("#deptIdSel").val(goodsInfo.deptId);
            	jQuery("#deptIdSel").trigger("liszt:updated");
            	
            	jQuery("#categoryNameSel").val(goodsInfo.categoryId);
            	jQuery("#categoryNameSel").trigger("liszt:updated");
            	
            	jQuery("input[name='brandId']").val(goodsInfo.brandId);
            	/*jQuery("#brandIdSel").val(goodsInfo.brandId);
            	jQuery("#brandIdSel").find("option[value='"+goodsInfo.brandId+"']").attr("selected","selected");
            	jQuery("#brandIdSel").trigger("liszt:updated");*/
            	
            	if(goodsInfo.goodsImage != null && goodsInfo.goodsImage != ""){
            		jQuery("img[name='headImg']").attr("src", picUrl + goodsInfo.goodsImage);
                	jQuery("input[name='goodsImage']").val(goodsInfo.goodsImage);
            	}else{
            		jQuery("img[name='headImg']").attr("src", picUrl + "zefun/images/pic_none.gif");
                	jQuery("input[name='goodsImage']").val("zefun/images/pic_none.gif");
            	}
            	
            	var affiliatedImage = goodsInfo.affiliatedImage;
            	var headImg = jQuery("img[name='affiliatedHeadImg']");
            	var affiliatedImageInput = jQuery("input[name='affiliatedImage']");
            	if(affiliatedImage != null && affiliatedImage != ""){
            		var img = affiliatedImage.split(",");
            		for(var i=0;i<affiliatedImageInput.length;i++){
            			jQuery(headImg[i]).attr("src",picUrl + img[i]);
            			jQuery(affiliatedImageInput[i]).val(img[i]);
            		}
            	}else{
            		for(var i=0;i<affiliatedImageInput.length;i++){
            			jQuery(headImg[i]).attr("src", picUrl + "zefun/images/pic_none.gif");
                    	jQuery(affiliatedImageInput[i]).val("zefun/images/pic_none.gif");
            		}
            	}
            	if(obj!=null){
            		jQuery(".dingdanzhuantai").text("正在修改商品 : "+goodsInfo.goodsName);
            	}else{
            		jQuery(".dingdanzhuantai").text("正在复制商品 : "+goodsInfo.goodsName);
            	}
            	
            	jQuery("#goodsName").val(goodsInfo.goodsName);
            	jQuery("#highestDiscount").val(goodsInfo.highestDiscount);
            	jQuery("#goodsId").val(goodsInfo.goodsId);
            	jQuery("#goodsDesc").val(goodsInfo.goodsDesc);
            	jQuery("#costPrice").val(goodsInfo.costPrice);
            	jQuery("#goodsPrice").val(goodsInfo.goodsPrice);
            	jQuery("#onlineShoppingPrice").val(goodsInfo.onlineShoppingPrice);
            	jQuery("#goodsStock").val(goodsInfo.goodsStock);
            	jQuery("#warnStock").val(goodsInfo.warnStock);
            	jQuery("#integralExchange").val(goodsInfo.integralExchange);
            	jQuery("#commissionAmount").val(goodsInfo.commissionAmount);
            	jQuery("#cardAmount").val(goodsInfo.cardAmount);
            	
            	/*锁定部门*/
            	jQuery("select[name='deptId']").empty();
            	jQuery("select[name='deptId']").append("<option value="+goodsInfo.deptId+">"+goodsInfo.deptName+"</option>");
            	jQuery("select[name='deptId']").trigger("liszt:updated");
            	/*锁定类别*/
            	for (var j = 0; j < deptGoodsList.length; j++) {
					if(deptGoodsList[j].deptId == jQuery("select[name='deptId']").val()){
						jQuery("select[name='categoryId']").empty();
						for (var m = 0; m < deptGoodsList[j].goodsCategoryBaseDto.length; m++) {
							if(deptGoodsList[j].goodsCategoryBaseDto[m].categoryId == goodsInfo.categoryId){
								jQuery("select[name='categoryId']").append("<option value='"+deptGoodsList[j].goodsCategoryBaseDto[m].categoryId+"' selected='selected' >"+deptGoodsList[j].goodsCategoryBaseDto[m].categoryName+"</option>");
							}else{
								jQuery("select[name='categoryId']").append("<option value='"+deptGoodsList[j].goodsCategoryBaseDto[m].categoryId+"' >"+deptGoodsList[j].goodsCategoryBaseDto[m].categoryName+"</option>");
							}
						}
						jQuery("select[name='categoryId']").trigger("liszt:updated");
					}
				}
            	/*如果是点击了查询的话,就把下面的类别ID锁定,修改商品的时候用于比较*/
            	jQuery("#defaultCategoryId").val(goodsInfo.categoryId);
            	//reloadSelectCategory(goodsInfo.deptId);
            	
            	var isSellProduct = goodsInfo.isSellProduct;
            	if(isSellProduct == 1){
            		jQuery("#isSellProduct").lcs_on();
            	}else{
            		jQuery('#isSellProduct').lcs_off();//关闭
            	}
            	
            	/*是否礼金抵扣*/
            	var isCashDeduction = goodsInfo.isCashDeduction;
            	if(isCashDeduction == 1){
            		jQuery("#isCashDeduction").lcs_on();
            	}else{
            		jQuery('#isCashDeduction').lcs_off();//关闭
            	}
            	
            	/*业绩计算方式*/
            	var calculationType = goodsInfo.calculationType;
            	if(calculationType == 1){
            		jQuery("#calculationType").lcs_on();
            	}else{
            		jQuery('#calculationType').lcs_off();//关闭
            	}
            	
            	var isIncludeCost = goodsInfo.isIncludeCost;
            	if(isIncludeCost == 1){
            		jQuery('#isIncludeCost').lcs_on();//选中
            	}else{
            		jQuery('#isIncludeCost').lcs_off();//关闭
            	}
            	
            	var isWechatSell = goodsInfo.isWechatSell;
            	if(isWechatSell == 1){
            		jQuery('#isWechatSell').lcs_on();//选中
            	}else{
            		jQuery('#isWechatSell').lcs_off();//关闭
            	}
            	
            	var isDisable = goodsInfo.isDisable;
            	if(isDisable == 1){
            		jQuery('#isDisable').lcs_on();//选中
            	}else{
            		jQuery('#isDisable').lcs_off();//关闭
            	}
            	
            	var commissionType = goodsInfo.commissionType;
            	if(commissionType == 1){
            		jQuery('#commissionType').lcs_on();//选中
            	}else{
            		jQuery('#commissionType').lcs_off();//关闭
            	}
            	
            	jQuery("#projectSetTbody").empty();
            	jQuery("#discountTbody").empty();
            	//会员等级折扣
            	var goodsDiscountList = map["goodsDiscountList"];
            	for(var i=0; i<goodsDiscountList.length; i++){
            		var discount = goodsDiscountList[i];
            		var levelId = discount.levelId;
            		var discountProportion = discount.discountProportion;
            		var discountAmount = discount.discountAmount;
            		var onlineAppointmentPrice = discount.onlineAppointmentPrice;
            		var trEl = jQuery("<tr></tr>");
            		var selectEl = addMemberLevelSelect(levelId);
            		var tdSelEl = jQuery("<td></td>");
            		tdSelEl.append(selectEl);
            		trEl.append(tdSelEl);
            		trEl.append("<td><span id='mendianjiage' class='mendianjiage'>"+goodsInfo.goodsPrice+"  元</span></td>");
            		trEl.append("<td><input type='text' class='input100' name='discountAmount' datatype='n' errormsg='会员门店价：请输入整数！' nullmsg='请填写会员门店价！' value='"+discountAmount+"'><span class='percent-symbol'>元</span></td>");
            		/*trEl.append("<td><input type='text' class='input100' name='discountProportion' value='"+ discountProportion +"'/><span class='percent-symbol'>%</span></td>");
            		trEl.append("<td><input type='text' class='input100' name='discountAmount' value='"+ discountAmount +"'/><span class='percent-symbol'>元</span></td>");
            		trEl.append("<td><input type='text' class='input100' name='onlineAppointmentPrice' value='"+ onlineAppointmentPrice +"'/><span class='percent-symbol'>元</span></td>");*/
            		trEl.append("<td><i class='cursor iconfa-minus' onclick='deleteDiscount(this);'></i></td>");
            		jQuery("#discountTbody").append(trEl);
            		jQuery(selectEl).chosen();
            	}
            }else{
            	dialog("error");
            }
        }
    });
}

/**会员等级下拉框*/
function addMemberLevelSelect(levelId){
	var selectEl = jQuery('<select name="levelId" data-placeholder="Choose a Country..." class="input-medium" ></select>');
	for (var i = 0; i < memberLevelList.length; i++) {
		var memberLevel = memberLevelList[i];
		if(levelId == memberLevel.levelId){
			selectEl.append("<option value='"+memberLevel.levelId+"' selected='selected' >"+memberLevel.levelName+"</option>");
		}else{
			selectEl.append("<option value='"+memberLevel.levelId+"' >"+memberLevel.levelName+"</option>");
		}
	}
	return selectEl;
}

//类别下拉框更换时,改变复制框中的值
function changeCopyGoods(obj){
	var categoryId = jQuery(obj).val();
	if(categoryId == 0){
		jQuery("select[name='copyGoodsInfo']").empty();
		jQuery("select[name='copyGoodsInfo']").trigger("liszt:updated");
	}else{
		var deptId = jQuery("select[name='deptId']").val();
		for (var i = 0; i < deptGoodsList.length; i++) {
			if(deptGoodsList[i].deptId == deptId){
				for (var j = 0; j < deptGoodsList[i].goodsCategoryBaseDto.length; j++) {
					if(deptGoodsList[i].goodsCategoryBaseDto[j].categoryId == categoryId){
						jQuery("select[name='copyGoodsInfo']").empty();
						jQuery("select[name='copyGoodsInfo']").append("<option value='0' >选择一个商品</option>");
						for (var s = 0; s < deptGoodsList[i].goodsCategoryBaseDto[j].goodsBaseDtos.length; s++) {
							jQuery("select[name='copyGoodsInfo']").append("<option value='"+deptGoodsList[i].goodsCategoryBaseDto[j].goodsBaseDtos[s].goodsId+"'>"+deptGoodsList[i].goodsCategoryBaseDto[j].goodsBaseDtos[s].goodsName+"</option>");
						}
						jQuery("select[name='copyGoodsInfo']").trigger("liszt:updated");
					}
				}
			}
			
		}
	}
}
/**复制变了*/
function iconCopyGoodsInfo(goodsId,deptId,obj){
	stopBubble(obj);
	jQuery(".dingdanzhuantai").text("正在复制商品");
	queryGoodsInfoById(goodsId,null);
	jQuery("#isUpdate").val(0);
	jQuery("#goodsId").val('');
	jQuery("#goodsName").val(jQuery("#goodsName").val()+"复制");
}
//进行选择一个商品进行复制
function copyGoodsInfos(obj){
	var goodsId = jQuery(obj).val();
	if(goodsId == 0){
		emptyInput();
		return ;
	}
	queryGoodsInfoById(goodsId,null);
	jQuery("#isUpdate").val(0);
	jQuery("#goodsId").val('');
	jQuery("#goodsName").val(jQuery("#goodsName").val()+"复制");
	
}

/**保存商品*/
function saveGoodsInfo(){
	var data = "data=data";
	var categoryId = jQuery("select[name='categoryId']").val();
	if(categoryId == 0){
		dialog("请选择一个商品类别");
		return;
	}
	//对会员折扣进行判断
	var levelIdArray = new Array();
	for (var i = 0; i < jQuery("select[name='levelId']").length; i++) {
		if(levelIdArray.contains(jQuery("select[name='levelId']").eq(i).val())){
			dialog("对会员的折扣设置不可重复");
			return;
		}else{
			levelIdArray.push(jQuery("select[name='levelId']").eq(i).val());
		}
		
	}
	data = jQuery("#goodsInfoForm").serialize();
	
	if(data.indexOf("commissionType")==-1){
		data = data + "&commissionType="+jQuery("#commissionType").val();
	}
	if(data.indexOf("calculationType")==-1){
		data = data + "&calculationType="+jQuery("#calculationType").val();
	}
	if(data.indexOf("isSellProduct")==-1){
		var isSellProduct = jQuery("#isSellProduct").val();
		data = data + "&isSellProduct=" + isSellProduct;
	}
	if(data.indexOf("isCashDeduction")==-1){
		var isCashDeduction = jQuery("#isCashDeduction").val();
		data = data + "&isCashDeduction=" + isCashDeduction;
	}
	console.log(data);
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"goodsInfo/saveGoodsInfo",
        data: data,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	var deptId = jQuery("#deptIdSel").val();
            	var categoryId = jQuery("#categoryNameSel").val();
            	var goodsName = jQuery("#goodsName").val();
            	if(jQuery("#isUpdate").val() == 1){//修改
            		var goodsId = jQuery("#goodsId").val();
            		jQuery("#goodsId"+goodsId).remove();
            		var str = '<li class="project-sublist-content active" id="goodsId'+goodsId+'" onclick="queryGoodsInfoById('+goodsId+',this);">'+goodsName+
		            				'<span class="fr">'+
		                    			'<i class="icon-fuzhi iconfont" onclick="iconCopyGoodsInfo('+goodsId+','+deptId+',this);" title="复制" ></i>&nbsp;'+
					                    '<i class="iconfa-trash project-icon" onclick="deleteGoodsInfo('+goodsId+','+deptId+',this);" title="删除"></i>'+
					                '</span>'+
					           '</li>';
            		jQuery("#deptId"+deptId).nextAll().find("#categoryId"+categoryId).after(str);
            	}
            	//新增操作
            	else{
            		var deptId = jQuery("#deptIdSel").val();
            		var goodsId = data.msg;
            		jQuery("#isUpdate").val(1);
            		var str = '<li class="project-sublist-content active" id="goodsId'+goodsId+'" onclick="queryGoodsInfoById('+goodsId+',this);">'+goodsName+
			                    '<span class="fr">'+
			                    '<i class="icon-fuzhi iconfont" onclick="iconCopyGoodsInfo('+goodsId+','+deptId+',this);" title="复制" ></i>&nbsp;'+
			                    '<i class="iconfa-trash project-icon" onclick="deleteGoodsInfo('+goodsId+','+deptId+',this);"></i>'+
			                    '</span>'+
			                  '</li>';
            		//加入下面一句,新增完成默认将此选中,在该更表单即为修改操作
            		jQuery("#goodsId").val(goodsId);
            		jQuery("#deptId"+deptId).nextAll().find("#categoryId"+categoryId).after(str);
            	}
            	dialog("保存成功");
            	emptyInput();
            }else{
            	dialog("error");
            }
        }
    });
}

/**添加指定折扣*/
function addDiscount(){
	var objHtml = jQuery("#memberLevelSelect").html();
	jQuery("#discountTbody").append(objHtml);
	jQuery("#discountTbody").find("select").chosen();
	//jQuery(".mendianjiage").text((jQuery("#goodsPrice").val()+0)+"  元");
	if (jQuery("#goodsPrice").val() == ""){
		jQuery(".mendianjiage").text(0+"  元");
	}else{
		jQuery(".mendianjiage").text(jQuery("#goodsPrice").val()+"  元");
	}
}
//修改门店价格的时候,将会员门店价格设置
function changeYuYuejiage(obj){
	console.log(jQuery(obj).val());
	jQuery(".mendianjiage").text(jQuery(obj).val()+"  元");
}

/**删除指定会员折扣*/
function deleteDiscount(obj){
	jQuery(obj).parent().parent().remove();
}

//对会员卡最低项目折扣的设置
function setMemberLastAccount(obj){
	var discount = jQuery(obj).val();
	if(discount == ""){
		return;
	}else{
		jQuery("#discountTbody").empty();
		for (var i = 0; i < memberLevelList.length; i++) {
			if(memberLevelList[i].projectDiscount<parseInt(discount)){
				addDiscount();
				jQuery("select[name='levelId']").eq((jQuery("select[name='levelId']").length-1)).val(memberLevelList[i].levelId);
				jQuery("select[name='levelId']").eq((jQuery("select[name='levelId']").length-1)).find("option[value='"+memberLevelList[i].levelId+"']").attr("selected","selected");
				jQuery("select[name='levelId']").eq((jQuery("select[name='levelId']").length-1)).trigger("liszt:updated");
			}
		}
	}
}

/**删除商品*/
function deleteGoodsInfo(goodsId,deptId,obj){
	stopBubble(obj);
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"goodsInfo/deleteGoodsInfo",
        data: "goodsId="+ goodsId+"&deptId="+deptId,
        async: false,
        error: function(request) {
        	dialog("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	jQuery("#goodsId"+goodsId).remove();
//            	jQuery(obj).parent().parent().parent().hide();
            	emptyInput();
            }else{
            	dialog(data.msg);
            }
        }
    });
}


//计算会员门店价
function calculateMenberPrice(obj){
	var discountProportion = jQuery(obj).val();
	var goodsPrice = jQuery("#goodsPrice").val();
	if(goodsPrice == ""){
		dialog("售价不能为空!");
	}else{
		jQuery(obj).parent().siblings().find("input[name='discountAmount']").val(Number(Number(discountProportion)*Number(goodsPrice)/100).toFixed(2));
	}
}

/**
 * 扩展Array方法,满足删除
 */
Array.prototype.remove=function(dx) 
{ 
    if(isNaN(dx)||dx>this.length){return false;} 
    for(var i=0,n=0;i<this.length;i++) 
    { 
        if(this[i]!=this[dx]) 
        { 
            this[n++]=this[i];
        } 
    } 
    this.length-=1;
} 
/**
 * 维护js中商品类别对象
 * type:1.新增维护,2.删除维护,3.修改维护
 */
function maintenance(deptId,categoryName,categoryId,type){
	if(type == 1){
		for (var i = 0; i < deptGoodsList.length; i++) {
			if(deptGoodsList[i].deptId == deptId){
				var category = new Object();
				category["categoryName"] = categoryName;
				category["categoryId"] = categoryId;
				deptGoodsList[i].goodsCategoryBaseDto.push(category);
				for (var j = 0; j < deptGoodsList[i].goodsCategoryBaseDto.length; j++) {
					if(deptGoodsList[i].goodsCategoryBaseDto[j].categoryId == categoryId){
						var goodsBaseDtos = new Array();
						deptGoodsList[i].goodsCategoryBaseDto[j]["goodsBaseDtos"] = goodsBaseDtos;
					}
				}
			}
		}
	}
	if(type == 2){
		for (var i = 0; i < deptGoodsList.length; i++) {
			for (var j = 0; j < deptGoodsList[i].goodsCategoryBaseDto.length; j++) {
				if(categoryId == deptGoodsList[i].goodsCategoryBaseDto[j].categoryId){
					deptGoodsList[i].goodsCategoryBaseDto.remove(j);
				}
			}
		}
	}
	if(type == 3){
		for (var i = 0; i < deptGoodsList.length; i++) {
			for (var j = 0; j < deptGoodsList[i].goodsCategoryBaseDto.length; j++) {
				if(categoryId == deptGoodsList[i].goodsCategoryBaseDto[j].categoryId){
					deptGoodsList[i].goodsCategoryBaseDto[j]["categoryName"] = categoryName;
				}
			}
		}
	}
	/**
	 * 刷新右侧类别下拉框
	 * 如果当前右侧选中的部门deptId和参数相等,那么久更新下列的项目类别下拉框
	 */
	var curtDeptId = jQuery("select[name='deptId']").val();
	reloadSelectCategory(curtDeptId);
}


/**
 * 刷新项目类别中的下拉框数据
*/
function reloadSelectCategory(deptId){
	for (var i = 0; i < deptGoodsList.length; i++) {
		if(deptGoodsList[i].deptId == deptId){
			jQuery("select[name='categoryId']").empty();
			jQuery("select[name='categoryId']").append("<option value='0'>请选择一个类别</option>");
			for (var j = 0; j < deptGoodsList[i].goodsCategoryBaseDto.length; j++) {
				jQuery("select[name='categoryId']").append("<option value="+deptGoodsList[i].goodsCategoryBaseDto[j].categoryId+">"+deptGoodsList[i].goodsCategoryBaseDto[j].categoryName+"</option>");
			}
    	    jQuery("select[name='categoryId']").trigger("liszt:updated");
		}
	}
}

/**手动清空表单数据*/
function emptyInput(){
     jQuery(':input','#goodsInfoForm') 
	.not(':button, :submit, :reset, :hidden') 
	.val('') 
	.removeAttr('checked') 
	.removeAttr('selected');
     jQuery("#discountTbody").empty();
     jQuery("input[name='goodsImage']").val("zefun/idCard/1445060767155");
     jQuery("img[name='headImg']").attr("src",baseUrl+"images/pic_none.gif");
     jQuery("input[name='affiliatedImage']").val("zefun/idCard/1445060767155");
     jQuery("img[name='affiliatedHeadImg']").attr("src",baseUrl+"images/pic_none.gif");
     jQuery("#isUpdate").val(0);
     jQuery("#goodsId").val("");
     jQuery(".project-sublist-content.nopadding.project-selected").attr("class","project-sublist-content nopadding");
     
     /*解锁部门*/
 	jQuery("select[name='deptId']").empty();
 	for (var i = 0; i < deptGoodsList.length; i++) {
 		jQuery("select[name='deptId']").append("<option value="+deptGoodsList[i].deptId+">"+deptGoodsList[i].deptName+"</option>");
	}
 	jQuery("select[name='deptId']").trigger("liszt:updated");
 	/*锁定类别*/
 	jQuery("select[name='categoryId']").empty();
 	for (var i = 0; i < deptGoodsList[0].goodsCategoryBaseDto.length; i++) {
 		jQuery("select[name='categoryId']").append("<option value="+deptGoodsList[0].goodsCategoryBaseDto[i].categoryId+">"+deptGoodsList[0].goodsCategoryBaseDto[i].categoryName+"</option>");
	}
 	jQuery("select[name='categoryId']").trigger("liszt:updated");
 	jQuery(".project-sublist-content").removeClass("active");
 	jQuery(".dingdanzhuantai").text("正在新增商品");
}

Array.prototype.contains = function(obj) {
    var i = this.length;
    while (i--) {
        if (this[i] === obj) {
            return true;
        }
    }
    return false;
}

function sortGoodsByDept(deptId,dateType,deptName){
	jQuery.ajax({
		type:"post",
		data:{"deptId":deptId,"dateType":dateType,"deptName":deptName},
		url:baseUrl+"commoditysales/view/commoditysales/searchGoodsByDept",
		dataType:"json",
		success:function(returnData){
			parseData(returnData);
		}
	});
}

function parseData(goodsRankList){
	var goodsList=goodsRankList;
	var tbody=document.createElement("tbody");
	if(goodsRankList.length==0){
		dialog("本部门当前没有数据，要加油哦!");
		return ;
	}
	for(var i=0; i< goodsList.length; i++){
		
		var goods=goodsList[i];
		var tr=document.createElement("tr");
		
		var nameTd=document.createElement("td");
		nameTd.innerHTML=goods.goodName;
		tr.appendChild(nameTd);
		
		var deptTd=document.createElement("td");
		deptTd.innerHTML=goods.deptName;
		tr.appendChild(deptTd);
		
		var saleNumTd=document.createElement("td");
		saleNumTd.innerHTML=goods.goodCnt;
		tr.appendChild(saleNumTd);
		
		var saleAmtTd=document.createElement("td");		
		saleAmtTd.innerHTML=goods.goodAmount.toFixed(2);
		tr.appendChild(saleAmtTd);	
		
		var currGoodRank=document.createElement("td");
		currGoodRank.innerHTML=goods.goodRank;
		tr.appendChild(currGoodRank);
		
		var lastGoodRankTd=document.createElement("td");
		lastGoodRankTd.innerHTML=goods.lastRank;
		tr.appendChild(lastGoodRankTd);
		
		tbody.appendChild(tr);		
		jQuery("#goodsRank tbody").remove();
		jQuery("#goodsRank").append(tbody);
	}
}

function sortGoodsByNum(storeId,begin,end,dateType){
	jQuery.ajax({
		type:"post",
		data:{"storeId":storeId,"begin":begin,"end":end,"dateType":dateType},
		url:baseUrl+"commoditysales/view/commoditysales/searchGoodsByCntOrAmt",
		dataType:"json",
		success:function(returnData){
			parseData(returnData);
		}
	});
}

