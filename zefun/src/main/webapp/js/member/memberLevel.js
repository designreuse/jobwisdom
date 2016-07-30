
function chooseStore(obj, storeId) {
	jQuery(obj).addClass("active");
	jQuery(obj).siblings().removeClass("active");
	changes = true;
	chooseStoreId = storeId;
	pageNo = 1;
	changePage();
}

function changeType () {
	pageNo = 1;
	changes = true;
	changePage();
}

//分页处理
var changes = false;
function changePage(){
	var type = jQuery("select[name='levelType']").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/selectStoreMemberLevel",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize + "&storeId=" +chooseStoreId+ "&type="+type,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			pageNo = e.msg.pageNo;
			pageSize = e.msg.pageSize;
			totalPage = e.msg.totalPage;
			totalRecord = e.msg.totalRecord;
			if(changes){
				unbuildPagination();
				changes = false;
			}
			refreshTableData(e.msg);
			totalRecord = e.msg.totalRecord;
		}
	});
}

//刷新表格数据
function refreshTableData(page){
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	totalPage = page.totalPage;
	jQuery(".vip_card_table tr:gt(0)").remove();
	var memberLevelDtoList = page.results;
	for (var i = 0; i < memberLevelDtoList.length; i++) {
		var memberLevelDto = memberLevelDtoList[i];
		
		var str = "<tr id='"+memberLevelDto.discountId+"'>"+
			             "<td>"+memberLevelDto.levelName;
	    if (memberLevelDto.isDefault == 1) {
	    	str += "<span class='font-999'>默认等级</span>";
	    }
	    str +=   "</td>"+
	             "<td>"+memberLevelDto.levelType+"</td>"+
	             "<td>"+memberLevelDto.projectDiscount+"%</td>"+
	             "<td>"+memberLevelDto.goodsDiscount+"%</td>"+
	             "<td>"+memberLevelDto.chargeMinMoney+"元</td>"+
	             "<td>"+memberLevelDto.sellAmount+"元</td>";
	    
	    if (memberLevelDto.cashDiscountType == 0) {
	    	str += "<td>不打折</td>";
	    }
	    else {
	    	str += "<td>打折</td>";
	    }
	    str += "<td>"+memberLevelDto.performanceDiscountPercent+"%</td>"+
	             "<td>"+memberLevelDto.integralUnit+"元 = "+memberLevelDto.integralNumber+"积分</td>"+
	             "<td class='input80'>"+memberLevelDto.levelNotice+"</td>"+
	             "<td>";
	    			if(memberLevelDto.levelType=='折扣卡'){
	    				
	    				 str += "<em onclick='editMemberLevel("+memberLevelDto.discountId+")'><img src='"+baseUrl+"images/handle_1.png'></em>";
	    			}
	    			 str +=  "<em onclick='showMemberLevel("+memberLevelDto.discountId+")'><img src='"+baseUrl+"images/shop_vip.png'></em></td>"+
	           "</tr>";
		jQuery(".vip_card_table table").append(str);
	}
}
function editMemberLevel (discountId) {
	jQuery(".zzc1").show();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/info",
		data : "discountId=" + discountId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dataToFormByName(e.msg);
			jQuery("#levelNameP").append(e.msg.levelName);
		}
	});
}


function saveEditMemberLevel () {
	var projectDiscountValue = jQuery("input[name='projectDiscount']").val();
	var goodsDiscountValue = jQuery("input[name='goodsDiscount']").val();
	var performanceDiscountPercentValue = jQuery("input[name='performanceDiscountPercent']").val();
	var sellAmountValue = jQuery("input[name='sellAmount']").val();
	var chargeMinMoneyValue = jQuery("input[name='chargeMinMoney']").val();
	var cashDiscountType = jQuery("[name='cashDiscountType']").val();
	var integralUnit = jQuery("[name='integralUnit']").val();
	var integralNumber = jQuery("[name='integralNumber']").val();
	var discountId = jQuery("[name='discountId']").val();
	if (isEmpty(integralUnit)) {
		integralUnit = 0;
	}
	if (isEmpty(integralNumber)) {
		integralNumber = 0;
	}
	//判断项目折扣
	if(projectDiscountValue.length == 0) {
		dialog("请填写项目折扣");
		return;
	} else if(projectDiscountValue == 0) {
		dialog("折扣不能为0,不打折请设置为100");
		return;
	} else if(parseInt(projectDiscountValue) != projectDiscountValue) {
		dialog("项目折扣为1-100内的整数");
		return;
	} else if(projectDiscountValue < 0 || projectDiscountValue > 100) {
		dialog("项目折扣为1-100内的整数");
		return;
	}
	//判断商品折扣
	if(goodsDiscountValue.length == 0) {
		dialog("请填写商品折扣");
		return;
	} else if(goodsDiscountValue == 0) {
		dialog("折扣不能为0,不打折请设置为100");
		return;
	} else if(parseInt(goodsDiscountValue) != goodsDiscountValue) {
		dialog("商品折扣为1-100内的整数");
		return;
	} else if(goodsDiscountValue < 0 || goodsDiscountValue > 100) {
		dialog("商品折扣为1-100内的整数");
		return;
	}
	//判断业绩折扣比例
	if(performanceDiscountPercentValue.length == 0) {
		dialog("业绩折扣比例为0-100内整数");
		return;
	} else if(parseInt(performanceDiscountPercentValue) != performanceDiscountPercentValue) {
		dialog("业绩折扣比例请输入整数");
		return;
	} else if(performanceDiscountPercentValue < 0 || performanceDiscountPercentValue > 100) {
		dialog("业绩折扣比例为0-100内整数");
		return;
	}
	//现金售卡
	if (sellAmountValue.length == 0) {
		dialog("请填写开卡费用");
		return;
	} else if(sellAmountValue < 0) {
		dialog("开卡费用只能为整数");
		return;
	}
	//最低充值
	if (chargeMinMoneyValue.length == 0) {
		dialog("请填写最低充值");
		return;
	} else if(chargeMinMoneyValue < 0) {
		dialog("'最低充值'填写错误");
		return;
	}
			
	var data = {};
	data["projectDiscount"] = projectDiscountValue;
	data["goodsDiscount"] = goodsDiscountValue;
	data["performanceDiscountPercent"] = performanceDiscountPercentValue;
	data["sellAmount"] = sellAmountValue;
	data["chargeMinMoney"] = chargeMinMoneyValue;
	data["cashDiscountType"] = cashDiscountType;
	data["integralUnit"] = integralUnit;
	data["integralNumber"] = integralNumber;
	data["discountId"] = discountId;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/saveEditMemberLevel",
		data : data,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("提交成功,即将刷新页面...");
			location.reload();
		}
	});
}

function showMemberLevel (discountId) {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/info",
		data : "discountId=" + discountId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var data = e.msg;
			var arrStr = data.levelLogo;
			jQuery("p[name='levelName']").find("i").text(data.levelName);
			jQuery("p[name='projectDiscount']").find("i").text(data.projectDiscount/10);
			jQuery("p[name='goodsDiscount']").find("i").text(data.goodsDiscount/10);
			jQuery('.preview_vip_card').css('background','#'+data.levelLogo);
			jQuery(".zzc").show();
		}
	});
}

function chooseMemberPage (type, positivePageUrl, oppositePageUrl) {
	valueChooseMemberPage(positivePageUrl, oppositePageUrl);
	
	jQuery("div[name='pagePreview']").attr("class", "");
	jQuery("div[name='pagePreviewLeft']").attr("class", "");
	jQuery("div[name='pagePreviewRight']").attr("class", "");
	if (type == 1) {
		jQuery("div[name='pagePreview']").addClass("preview_1");
		jQuery("div[name='pagePreviewLeft']").addClass("preview_left");
		jQuery("div[name='pagePreviewRight']").addClass("preview_right");
	}
	else if (type == 2) {
		jQuery("div[name='pagePreview']").addClass("vip_card_1");
		jQuery("div[name='pagePreviewLeft']").addClass("vip_card_1_left");
		jQuery("div[name='pagePreviewRight']").addClass("vip_card_1_content");
	}
	else if (type == 3) {
		jQuery("div[name='pagePreview']").addClass("vip_card_2");
		jQuery("div[name='pagePreviewLeft']").addClass("vip_card_2_left");
		jQuery("div[name='pagePreviewRight']").addClass("vip_card_2_content");
	}
	else if (type == 4) {
		jQuery("div[name='pagePreview']").addClass("vip_card_3");
		jQuery("div[name='pagePreviewLeft']").addClass("vip_card_3_left");
		jQuery("div[name='pagePreviewRight']").addClass("vip_card_3_content");
	}
	else if (type == 5) {
		jQuery("div[name='pagePreview']").addClass("vip_card_4");
		jQuery("div[name='pagePreviewLeft']").addClass("vip_card_4_left");
		jQuery("div[name='pagePreviewRight']").addClass("vip_card_4_content");
	}
}

function valueChooseMemberPage (positivePageUrl, oppositePageUrl) {
	jQuery("div[name='pagePreview']").css("background", "url('"+qiniuUrl + positivePageUrl+"') no-repeat");
	jQuery(".preview_2").css("background", "url('"+qiniuUrl + oppositePageUrl+"') no-repeat");
}

function cancelModal () {
	jQuery(".zzc1").hide();
	jQuery(".zzc").hide();
	jQuery(".zzc1").find("[type='text']").val('');
	jQuery("#levelNameP").text("");
}

jQuery(function(){
	jQuery('.zzc').click(function(e){
		var tar = e.target;
		if (jQuery(tar).is('.zzc')) {
			jQuery('.zzc').hide()
		}
	})
	
	jQuery('.preview').click(function(e){
	var tar = e.target;
	if (jQuery(tar).is('.preview')) {
		jQuery('.zzc').hide()
	}
	})
	
	

})