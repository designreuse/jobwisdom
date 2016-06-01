function showAddMemberLevel () {
	jQuery("#memberLevelModal").show();
	jQuery("#memberLevelModal").find("[type='hidden']").val('');
	jQuery("#memberLevelModal").find("[type='text']").val('');
	jQuery("#memberLevelModal").find("[type='checkbox']").removeAttr("checked");
	jQuery(jQuery(".business_level_back_ul li")[0]).click();
}

function cancelModal () {
	jQuery("#memberLevelModal").hide();
}

var updatePositivePageUrl = 'system/profile/vip_card.png';
var updateOppositePageUrl = 'system/profile/vip_card1.png';
var chooseType = 1;

var levelTemplate = 1;

function chooseMemberPage (type, positivePageUrl, oppositePageUrl) {
	valueChooseMemberPage(positivePageUrl, oppositePageUrl);
	levelTemplate = type;
}

function valueChooseMemberPage (positivePageUrl, oppositePageUrl) {
	updatePositivePageUrl = positivePageUrl;
	updateOppositePageUrl = oppositePageUrl;
	jQuery(".preview_1").css("background", "url('"+qiniuUrl + positivePageUrl+"') no-repeat");
	jQuery(".preview_2").css("background", "url('"+qiniuUrl + oppositePageUrl+"') no-repeat");
}

function showMask (type) {
	chooseType = type;
	if (type == 1) {
		editPage(updatePositivePageUrl);
	}
	else {
		editPage(updateOppositePageUrl);
	}
	jQuery(".mask").show();
}

//提交会员等级信息，存在等级标识则修改，不存在则新增
function addOrEditMemberLevel(){
	var levelType = jQuery("select[name='levelType']").val();
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
	var discountId = jQuery("[name='discountId']").val();
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
		
	var levelNotice = jQuery('textarea[name = levelNotice]').val().replace(/\n/g, '</br>');
    jQuery(".preview_2_content_right").append(levelNotice);
	
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
	data["levelType"] = levelType;
	data["levelLogo"] = updatePositivePageUrl +","+updateOppositePageUrl;
	data["levelTemplate"] = levelTemplate;
	data["discountId"] = discountId;
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/view/saveEnterpriseMemberLevel",
		dataType : "json",
        data: data,
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

//修改会员等级信息
function editMemberLevel(levelId){
	jQuery("#memberLevelModal").show();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/view/selectEnterpriseMember",
		data : "levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var data = e.msg;
			dataToFormByName(data);
			var a = data.levelTemplate - 1;
			jQuery(".business_level_back_ul li").find('span').removeClass("active");
			jQuery(jQuery(".business_level_back_ul li")[a]).find("span").addClass("active");
			var arr = data.levelLogo.split(",");
			
			chooseMemberPage(data.levelTemplate, arr[0], arr[0]);
			
			
			
			/*var list = e.msg.levelNoticeList;
			var content = jQuery("#editor_id");
			if (list != null) {
				for (var i = 0; i < list.length; i++) {
					content.append("<div>" + list[i].text + "</div>");
				}
			}*/
		}
	});
}