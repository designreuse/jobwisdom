function showAddMemberLevel () {
	jQuery("#memberLevelModal").show();
}

function changeType (obj) {
	var type = jQuery(obj).val();
	if (type == "折扣卡") {
		jQuery(".business_level_back_text").removeClass("hide");
	}
	else {
		jQuery(".business_level_back_text").addClass("hide");
	}
	
}

function cancelModal () {
	jQuery("#memberLevelModal").hide();
}

//提交会员等级信息，存在等级标识则修改，不存在则新增
function addOrEditMemberLevel(){
	var levleType = jQuery("input[name='levleType']").val();
	var projectDiscountValue = jQuery("input[name='projectDiscount']").val();
	var goodsDiscountValue = jQuery("input[name='goodsDiscount']").val();
	var performanceDiscountPercentValue = jQuery("input[name='performanceDiscountPercent']").val();
	var sellAmountValue = jQuery("input[name='sellAmount']").val();
	var chargeMinMoneyValue = jQuery("input[name='chargeMinMoney']").val();
	var levelName = jQuery("input[name='levelName']").val();
	var cashDiscountType = jQuery("[name='cashDiscountType']").val();
	var levelId = jQuery("[name='levelId']").val();
	var integralUnit = jQuery("[name='integralUnit']").val();
	var integralNumber = jQuery("[name='integralNumber']").val();
	
	if (isEmpty(integralUnit)) {
		integralUnit = 0;
	}
	if (isEmpty(integralNumber)) {
		integralNumber = 0;
	}
	
	if(levelName.length == 0) {
		dialog("请填写会员卡名称");
		return;
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
	
	var levelNotice = jQuery('#levelNotice').val();
	
	var data = {};
	data["levelId"] = levelId;
	data["levelName"] = levelName;
	data["projectDiscount"] = projectDiscountValue;
	data["goodsDiscount"] = goodsDiscountValue;
	data["performanceDiscountPercent"] = performanceDiscountPercentValue;
	data["sellAmount"] = sellAmountValue;
	data["chargeMinMoney"] = chargeMinMoneyValue;
	data["cashDiscountType"] = cashDiscountType;
	data["integralUnit"] = integralUnit;
	data["integralNumber"] = integralNumber;
	data["levelNotice"] = levelNotice;

	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/add",
		dataType : "json",
        data: data,
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("提交成功,即将刷新页面...");
			resetForm(".editMemberLevelForm");
			jQuery("#editor_id").html("");
			pageNo = 1;
			changePage();
			unbuildPagination();
		}
	});
}