function showAddMemberLevel () {
	jQuery("#memberLevelModal").show();
	jQuery("#memberLevelModal").find("[type='hidden']").val('');
	jQuery("#memberLevelModal").find("[type='text']").val('');
	jQuery("#memberLevelModal").find("[type='checkbox']").removeAttr("checked");
	jQuery("span[value='65c294']").click();
	
}

function cancelModal () {
	jQuery("#memberLevelModal").hide();
	jQuery("#textarea").val("");
	
	levelType2 =jQuery("select[name='levelType']").val();
	jQuery("select[name='levelType']").attr("disabled","disabled");
//	jQuery("input[name='goodsDiscount']").removeAttr("disabled");
//	jQuery("input[name='projectDiscount']").removeAttr("disabled");
//	jQuery("input[name='sellAmount']").removeAttr("disabled");
//	jQuery("input[name='performanceDiscountPercent']").removeAttr("disabled");
//	jQuery("input[name='chargeMinMoney']").removeAttr("disabled");
//	jQuery("input[name='integralUnit']").removeAttr("disabled");
//	jQuery("input[name='integralNumber']").removeAttr("disabled");
	jQuery("select[name='cashDiscountType']").removeAttr("disabled");
	jQuery("input[name='goodsDiscount']").attr({"disabled":false,"style":"background:#fff"});
	jQuery("input[name='projectDiscount']").attr({"disabled":false,"style":"background:#fff"});
	jQuery("input[name='sellAmount']").attr({"disabled":false,"style":"background:#fff"});
	jQuery("input[name='performanceDiscountPercent']").attr({"disabled":false,"style":"background:#fff"});
	jQuery("input[name='chargeMinMoney']").attr({"disabled":false,"style":"background:#fff;position:relative;left:22px"});
	jQuery("input[name='integralUnit']").attr({"disabled":false,"style":"background:#fff"});
	jQuery("input[name='integralNumber']").attr({"disabled":false,"style":"background:#fff"});
	jQuery("select[name='cashDiscountType']").attr({"disabled":false,"style":"background:#fff"});
}

var updatePositivePageUrl = 'system/profile/vip_card.png';
var updateOppositePageUrl = 'system/profile/vip_card1.png';
var chooseType = 1;

var levelTemplate = 1;

function chooseMemberPage (type, positivePageUrl, oppositePageUrl) {
	valueChooseMemberPage(positivePageUrl, oppositePageUrl);
	levelTemplate = type;
	
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
	updatePositivePageUrl = positivePageUrl;
	updateOppositePageUrl = oppositePageUrl;
	jQuery("div[name='pagePreview']").css("background", "url('"+qiniuUrl + positivePageUrl+"') no-repeat");
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
	data["levelLogo"] = colour;
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


function test_this(s)
{
	var reg = /^\d{1,3}$/;
	var re = s.value.match( reg );
	if(re){
	  if(s.value<100)
	  return true;
	}
	    return false;
}


var colour = "d93717";
function changetype(s){
	colour = jQuery(s).attr("value");
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
//			var arr = data.levelLogo.split(",");
			
//			chooseMemberPage(data.levelTemplate, arr[0], arr[0]);
		
			jQuery("p[name='levelName']").find("i").text(data.levelName);
	  
			jQuery("p[name='projectDiscount']").find("i").text(data.projectDiscount);
			jQuery("p[name='goodsDiscount']").find("i").text(data.goodsDiscount);
		  
			
			jQuery("span[value='"+data.levelLogo+"']").attr("value",data.levelLogo).click();
			colour = data.levelLogo;	
			/*var list = e.msg.levelNoticeList;
			var content = jQuery("#editor_id");
			if (list != null) {
				for (var i = 0; i < list.length; i++) {
					content.append("<div>" + list[i].text + "</div>");
				}
			}*/
			changeType();
			jQuery("select[name='levelType']").attr("disabled","disabled");
			levelType2 =jQuery("select[name='levelType']").val();
			if(levelType2=="等级卡"){
				jQuery("input[name='goodsDiscount']").attr({"disabled":"disabled","style":"background:#eee"});
				jQuery("input[name='projectDiscount']").attr({"disabled":"disabled","style":"background:#eee"});
				jQuery("input[name='sellAmount']").attr({"disabled":"disabled","style":"background:#eee"});
				jQuery("input[name='performanceDiscountPercent']").attr({"disabled":"disabled","style":"background:#eee"});
				jQuery("input[name='chargeMinMoney']").attr({"disabled":"disabled","style":"background:#eee;position:relative;left:22px"});
				jQuery("input[name='integralUnit']").attr({"disabled":"disabled","style":"background:#eee"});
				jQuery("input[name='integralNumber']").attr({"disabled":"disabled","style":"background:#eee"});
				jQuery("select[name='cashDiscountType']").attr({"disabled":"disabled","style":"background:#eee"});
			}
			
		}
	});
	
	
}