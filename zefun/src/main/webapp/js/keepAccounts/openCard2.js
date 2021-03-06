
jQuery(document).ready(function(){
    jQuery('#tabs').tabs();

    jQuery("#search-member").on("click", function(){
        jQuery("#search-td").hide();
        jQuery("#member-info").show();
    });

    jQuery("#icon-search").on("click", function(){
        jQuery("#member-info").hide();
        jQuery("#search-td").show();
    });

    jQuery("#search-member-chongzhi").on("click", function(){
        jQuery("#search-td-chongzhi").hide();
        jQuery("#member-info-chongzhi").show();
    });

    jQuery("#icon-search-chongzhi").on("click", function(){
        jQuery("#member-info-chongzhi").hide();
        jQuery("#search-td-chongzhi").show();
    });


    jQuery("#search-member-chongzhi2").on("click", function(){
        jQuery("#search-td-chongzhi2").hide();
        jQuery("#member-info-chongzhi2").show();
    });

    jQuery("#icon-search-chongzhi2").on("click", function(){
        jQuery("#member-info-chongzhi2").hide();
        jQuery("#search-td-chongzhi2").show();
    });
    
    if (clickType == 1) {
    	subTabFun(jQuery("li[name='rechargeName']"));
    	jQuery("#tab2").find("input[name='phoneNumber']").val(phoneNum);
    	jQuery("#tab2").find("span[name='seekName']").click();
    }
    else if (clickType == 2) {
    	subTabFun(jQuery("li[name='upgradeName']"));
    	jQuery("#tab4").find("input[name='phoneNumber']").val(phoneNum);
    	jQuery("#tab4").find("span[name='seekName']").click();
    }
});

jQuery(document).delegate(".member-card li", "click", function() {
    jQuery(this).addClass("current").siblings().removeClass("current")
})

function subTabFun (obj) {
	jQuery(obj).siblings().removeClass("active");
    jQuery(obj).addClass("active");
    var targetTab = jQuery(obj).data("target");
    
      jQuery(".tab-word").css("border","");
    jQuery(".target-tab").addClass("hide");
    jQuery(targetTab).removeClass("hide");
}

function changeOpenCard(obj){
    /*var valMoney = jQuery(obj).val();
    if(valMoney == 1){
    	jQuery("span[name='1']").removeClass("hide");
        jQuery("span[name='2']").addClass("hide");
    }else{
    	jQuery("span[name='1']").addClass("hide");
        jQuery("span[name='2']").removeClass("hide");
    }*/
}

//汇总
function totaiUpdateEmployeeInfo(obj){
	//找到父级
	var parentObj = jQuery(obj).parents(".target-tab");
	
	var employeeId = jQuery(obj).val();
	var name = jQuery(obj).find("option[value='"+employeeId+"']").text();
	var vl = "";
	var commissionType = parentObj.find("select[name='commissionType']").val();
	if (commissionType == 1) {
		vl = "元";
	}
	else {
		vl = "%";
	}
	/*parentObj.find("div[name='employeeChoose']").append("<div class='p-part'>"+
					                        "<div class='select-result' style='padding: 0px 6px;'>"+
					                            "<span name='employeeId' value='"+employeeId+"' class='chooseEmployeeStyle'>"+name+"：</span>"+
					                            "提成&nbsp;&nbsp;<input type='text' name = 'commission' onkeyup='checkNum(this)'/><span class='percent-symbol mr10'>"+vl+"</span>"+
					                            "&nbsp;&nbsp;业绩&nbsp;&nbsp;<input type='text' name = 'calculate' onkeyup='checkNum(this)'/><span class='percent-symbol mr10'>"+vl+"</span>"+
					                            "<span onclick = 'totaiDeleteEmployee(this)' class='mr10 cursor'>&nbsp;&nbsp;X&nbsp;&nbsp;</span>"+
					                        "</div>"+
					                    "</div>");*/
	parentObj.find("div[name='employeeChoose']").append("<ul class='select-result1'>"+
														    "<li name='employeeId' value='"+employeeId+"'>"+name+"</li>"+
														    "<li>提成 <input type='text' class='w60' name = 'commission' onkeyup='checkNum(this)'><span class='percent-symbol'>"+vl+"</span></li>"+
														    "<li>业绩 <input type='text' class='w60' name = 'calculate' onkeyup='checkNum(this)'><span class='percent-symbol'>"+vl+"</span></li>"+
														    "<span class='iconfont icon-shanchujilu' onclick = 'totaiDeleteEmployee(this)'></span>"+
														"</ul>");
	
	jQuery(obj).find("option[value='"+employeeId+"']").remove();
	jQuery(obj).trigger("liszt:updated");
}

function chooseDept(obj) {
	//找到父级
	var parentObj = jQuery(obj).parents(".target-tab");
	
	var deptChooseType = jQuery(obj).val();
	var vl = "";
	if (deptChooseType == 1) {
		vl = "元";
	}
	else {
		vl = "%";
	}
	
	var deptObjList = parentObj.find("div[name = 'deptIsChoose']");
	
	for (var i =  0; i < deptObjList.length; i++) {
		jQuery(deptObjList[i]).find(".percent-symbol").text(vl);
	}
}

function deleteDeptInfo(obj) {
	//找到父级
	var parentObj = jQuery(obj).parents(".target-tab");
	
	var deptId = jQuery(obj).parents(".select-result").find("span[name='deptId']").attr("value");
	var name = jQuery.trim((jQuery(obj).parents(".select-result").find("span[name='deptId']").text()).replace("：",""));
	
	jQuery(obj).parents(".select-result").remove();
	parentObj.find("select[name='deptSelectValue']").append("<option value='"+deptId+"'>"+name+"</option>");
	parentObj.find("select[name='deptSelectValue']").trigger("liszt:updated");
} 

function chooseDeptInfo(obj) {
	//找到父级
	var parentObj = jQuery(obj).parents(".target-tab");
	
	var deptId = jQuery(obj).val();
	
	var name = jQuery(obj).find("option[value='"+deptId+"']").text();
	var vl = "";
	var deptChooseType = parentObj.find("select[name='deptChooseType']").val();
	if (deptChooseType == 1) {
		vl = "元";
	}
	else {
		vl = "%";
	}
	
	parentObj.find("div[name='deptIdChoose']").append("<div class='select-result'  name = 'deptIsChoose' >"+
											            "<span name = 'deptId' value = '"+deptId+"'>"+name+"：</span>"+
											              	"<input class='w60' type='text' name='deptCalculate'>"+
											              	"<span class='percent-symbol'>"+vl+"</span></li>"+
											            "<span class='iconfont icon-shanchujilu' onclick = 'deleteDeptInfo(this)'></span>"+
											          "</div>");
	
	jQuery(obj).find("option[value='"+deptId+"']").remove();
	jQuery(obj).trigger("liszt:updated");
} 

function totaiUpdateVL(obj) {
	//找到父级
	var parentObj = jQuery(obj).parents(".target-tab");
	
	var employeeIds = parentObj.find("li[name='employeeId']");
	
	var commissionType = parentObj.find("select[name='commissionType']").val();
	if (commissionType == 1) {
		vl = "元";
	}
	else {
		vl = "%";
	}
	
	for (var i = 0; i < employeeIds.length; i++) {
		
		jQuery(employeeIds[i]).parent().find(".percent-symbol").text(vl);
	}
}

function totaiDeleteEmployee(obj){
	//找到父级
	var parentObj = jQuery(obj).parents(".target-tab");

	var employeeId = jQuery(obj).parents(".select-result1").find("li[name='employeeId']").attr("value");
	var name = jQuery(obj).parents(".select-result1").find("li[name='employeeId']").text();
	
	jQuery(obj).parents(".select-result1").remove();
	parentObj.find("select[name='recommendId']").append("<option value='"+employeeId+"'>"+name+"</option>");
	parentObj.find("select[name='recommendId']").trigger("liszt:updated");
}

function checkPhone(phone) {
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	 if (!reg.test(phone)) {
		dialog("号码格式有误~");
		return;
	 }
	 jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/isCheckAccount",
			data : "phone="+phone,
			async:false,//使用同步的Ajax请求 
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return 0;
				}
				return 1;
			}
		});
} 

function changeMemberLevel(obj){
	var levelId = jQuery(obj).val();
	var parentsTab = jQuery(obj).parents(".clearfix");
	for(var i = 0; i < memberLevelList.length; i++){
		var a = 0;
		if (memberLevelList[i].levelId == levelId) {
			
			parentsTab.find("[name='openType']").html(memberLevelList[i].sellAmount);
		}
	}
}

function againSearch(obj) {
	jQuery(obj).parents(".card-main1").find("input[name='memberId']").val("");
	jQuery(obj).parents(".card-main1").prev().removeClass("hide");
	jQuery(obj).parents(".card-main1").addClass("hide");
}

jQuery('body').delegate('.lcs_check_assignType', 'lcs-statuschange', function() {
    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
    if(status == 'checked'){
    	jQuery(this).val(1);
    	jQuery(this).parent().parent().siblings().find("input").next().text("%");
    }
    else{
    	jQuery(this).val(2);
    	jQuery(this).parent().parent().siblings().find("input").next().text("元");
    }
});

var modelType = -1;
var dataAjax = {};
jQuery("#confirm").click(function(){
	var memberId = jQuery("input[name='memberId']").val();
	
	var levelId = "";
	
	var phone = "";
	
	var name = "";
	
	var payPassword = "";
	
	var amountvalue = "";
	
	var sex = "";
	
	var messageType = "";
	
	var amountvalue = "";
	
	var openRecommendId = "";
	if (memberId == "") {
		levelId = jQuery("select[name='kkLevelId']").val();
		
		phone = jQuery("input[name = 'phoneNumber']").val();
		
		if (phone == "") {
			dialog("手机号不能为空！");
			return;
		}
		
		if (checkPhone(phone) == 0) {
			dialog("号码有误请修改");
			return;
		}
		
		name = jQuery("input[name = 'name']").val();
		if (name == "") {
			dialog("名称不能为空！");
			return;
		}
		
		payPassword = jQuery("input[name = 'payPassword']").val();
		var password = jQuery("input[name = 'password']").val();
		if (payPassword == "") {
			dialog("密码为空！");
			return;
		}
		if (payPassword != password) {
			dialog("密码不一致，请重新输入！");
			return;
		}
		
		openRecommendId = jQuery("select[name = 'openRecommendId']").val();
		
		payPassword = CryptoJS.MD5(CryptoJS.MD5(payPassword).toString().toUpperCase()).toString().toUpperCase();
		
		sex = jQuery("input:radio[name='sex']:checked").val();
		
		//是否接收短信
		messageType = jQuery("input:radio[name='messageType']:checked").val();
		
		amountvalue = jQuery("#tab1").find("div[name='fillMemberInfo']").find("[name='openType']").text();
	}
	else {
		amountvalue = jQuery("#tab1").find("div[name='memberTR']").find("[name='openType']").text();
		levelId = jQuery("select[name='kkLevelIdToo']").val();
	}
	
	var balanceAmount = 0;

	var cashAmount = jQuery("input[name = 'kkCashAmount']").val();
	var unionpayAmount = jQuery("input[name = 'kkUnionpayAmount']").val();
	var wechatAmount = jQuery("input[name = 'kkWechatAmount']").val();
	var alipayAmount = jQuery("input[name = 'kkAlipayAmount']").val();
	var debtAmount = jQuery("input[name = 'kkDebtAmount']").val();

	cashAmount = valueZero(cashAmount);
	unionpayAmount = valueZero(unionpayAmount);
	wechatAmount = valueZero(wechatAmount);
	alipayAmount = valueZero(alipayAmount);
	debtAmount = valueZero(debtAmount);
		
	var realPrice = parseInt(cashAmount) + parseInt(unionpayAmount) + parseInt(debtAmount) + parseInt(wechatAmount) + parseInt(alipayAmount);
	
	balanceAmount = realPrice;
	
    var deptStr = deptArrayObj("tab1", realPrice);
	
	if (deptStr == -1) {
		return;
	}
	
	var recommend = arrayObj("tab1", realPrice);
	if (recommend == -1) {
		return;
	}
	
	//礼金
	var giftmoneyAmount = jQuery("input[name='giftmoneyAmount']").val();

	giftmoneyAmount = valueZero(giftmoneyAmount);
	var partType = jQuery("select[name='partType']").val();
	var pastDate = 0;
	
	if (parseInt(giftmoneyAmount) != 0) {
		pastDate = jQuery("select[name='pastDate']").val();
		pastDate = valueZero(pastDate);
	}
	
	//卡金
	var rewardAmount = jQuery("input[name='rewardAmount']").val();

	rewardAmount = valueZero(rewardAmount);
	
	dataAjax = {"memberId":memberId,"phone":phone, "name":name, "sex": sex, "levelId": levelId, "amountvalue" : amountvalue, 
			"recommend" : recommend, "giftmoneyAmount" : giftmoneyAmount, "pastDate" : pastDate,
	        "partType" : partType, "rewardAmount" : rewardAmount,"messageType" : messageType, "balanceAmount" : balanceAmount, "cashAmount" : cashAmount, 
	        "unionpayAmount" : unionpayAmount, "wechatAmount" : wechatAmount, "alipayAmount" : alipayAmount, "debtAmount" : debtAmount, "payPassword" : payPassword, "deptStr" : deptStr, "openRecommendId": openRecommendId};
	
	openCard();
	
})

function confirmModel() {
	if (modelType == 1) {
		openCard();
	}
	else if (modelType == 2) {
		rechargeCard(1);
	}
	else {
		queren();
	}
}

function openCard() {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/addMemberInfo",
		data : dataAjax,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("保存成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}

function deptArrayObj(tabType, realPrice) {
	var tab = jQuery("#" + tabType);
	
	var deptObjs = jQuery(tab).find("div[name='deptIsChoose']");
	
	var deptChooseType = tab.find("select[name='deptChooseType']").val(); 
	
	var deptStr = "";
	
	if (deptObjs.length > 0) {
		for (var i = 0; i < deptObjs.length; i++) {
			var deptCalculate = jQuery(deptObjs[i]).find("input[name='deptCalculate']").val();
			var deptId = jQuery(deptObjs[i]).find("span[name='deptId']").attr("value");
			
			if (deptCalculate == "") {
				var name = jQuery.trim((jQuery(deptObjs[i]).find("span[name='deptId']").text()).replace("：",""));
				dialog(name+"的提成,业绩不能为同时空！");
				return -1;
			}
			
			deptCalculate = valueZero(deptCalculate);
			
			if (deptChooseType != 1) {
				deptCalculate = deptCalculate * realPrice/100;
			}
			
			if (deptStr == "") {
				deptStr = deptId + ":" + deptCalculate;
			}
			else {
				deptStr = deptStr + "," + deptId + ":" + deptCalculate;
			}
		}
	}
	else {
		dialog("业绩归属不能为空！");
		return -1;
	}
	
	return deptStr; 
}

//tabType 那个tab ID， type 1：提成 2： 业绩, realPrice 支付金额
function arrayObj (tabType, realPrice) {
	var tab = jQuery("#" + tabType);
	
	var	commissionParentObj = jQuery(tab).find("div[name='employeeChoose']");
	
	//选中员工标识集合
	var commissionEmployeeIds = commissionParentObj.find("li[name='employeeId']");
	
	var recommend = "";
	//类型
	var commissionType = jQuery(tab).find("select[name='commissionType']").val();
	
	for (var i = 0; i < commissionEmployeeIds.length; i++) {
		var commissionNum = jQuery(commissionEmployeeIds[i]).parent().find("input[name='commission']").val();
		
		var calculateNum = jQuery(commissionEmployeeIds[i]).parent().find("input[name='calculate']").val();
		
		var employeeId = jQuery(commissionEmployeeIds[i]).attr("value");
		
		if (commissionNum == "" && calculateNum == "") {
			var name = jQuery(commissionEmployeeIds[i]).text();
			dialog(name+"的提成,业绩不能为同时空！");
			return -1;
		}
		
		commissionNum = valueZero(commissionNum);
		
		calculateNum = valueZero(calculateNum);
		
		if (commissionType != 1) {
			commissionNum = commissionNum * realPrice/100;
			calculateNum = calculateNum * realPrice/100;
		}
		
		if (recommend == "") {
			recommend = employeeId + ":" + commissionNum + ":" + calculateNum;
		}
		else {
			recommend = recommend + "," + employeeId + ":" + commissionNum + ":" + calculateNum;
		}
	}
	
	return recommend;
}

function czConfirm(type){
	
	jQuery("#czModel").modal("hide");
	var subAccountId = jQuery("#tab2").find("ul[name = 'subAccountUL']").find(".current").attr("subAccountId");
	if (subAccountId == "") {
		dialog("未正确查找出充值会员！");
		return;
	}

	
	var cashAmount = jQuery("input[name = 'czCashAmount']").val();
	var unionpayAmount = jQuery("input[name = 'czUnionpayAmount']").val();
	var wechatAmount = jQuery("input[name = 'czWechatAmount']").val();
	var alipayAmount = jQuery("input[name = 'czAlipayAmount']").val();
	var debtAmount = jQuery("input[name = 'czDebtAmount']").val();

	cashAmount = valueZero(cashAmount);
	unionpayAmount = valueZero(unionpayAmount);
	wechatAmount = valueZero(wechatAmount);
	alipayAmount = valueZero(alipayAmount);
	debtAmount = valueZero(debtAmount);
	
	var realPrice = parseInt(cashAmount) + parseInt(unionpayAmount) + parseInt(debtAmount) + parseInt(wechatAmount) + parseInt(alipayAmount);

	var chargeAmount = realPrice;
	
    var deptStr = deptArrayObj("tab2", realPrice);
	
	if (deptStr == -1) {
		return;
	}
	
	var recommend = arrayObj("tab2", realPrice);
	if (recommend == -1) {
		return;
	}

	var partType = jQuery("select[name='czPartType']").val();
	
	//礼金
	var giftmoneyAmount = jQuery("input[name='czGiftmoneyAmount']").val();
	giftmoneyAmount = valueZero(giftmoneyAmount);
    var pastDate = 0;
	
	if (parseInt(giftmoneyAmount) != 0) {
		pastDate = jQuery("select[name='czPastDate']").val();
		pastDate = valueZero(pastDate);
	}
	
	//卡金
	var rewardAmount = jQuery("input[name='czRewardAmount']").val();
	rewardAmount = valueZero(rewardAmount);
	
	dataAjax = {"subAccountId" : subAccountId, "chargeAmount" : chargeAmount, "cashAmount" : cashAmount, "unionpayAmount" : unionpayAmount, "wechatAmount" : wechatAmount, "alipayAmount" : alipayAmount, "debtAmount" : debtAmount, "recommend" : recommend, "giftmoneyAmount" : giftmoneyAmount, 
			"pastDate": pastDate, "partType" : partType, "rewardAmount" : rewardAmount, "type" : type, "deptStr" : deptStr}
	
	rechargeCard(type);
}


//优惠赠送
function presentGift(){
	var memberId = jQuery("#tab5").find("input[name = 'memberId']").val();
	if (memberId == "") {
		dialog("未正确查找出赠送会员！");
		return;
	}
	
	//礼金
	var giftmoneyAmount = jQuery("input[name='zsGiftmoneyAmount']").val();
	if(!isEmpty(giftmoneyAmount) && isNaN(giftmoneyAmount)){
		dialog("积分只能赠送整数！");
		return;
	} else if(isEmpty(giftmoneyAmount)) {
		giftmoneyAmount = 0;
	}
	
	//积分
	var integralAmount = jQuery("input[name='zsIntegralAmount']").val();
	if(!isEmpty(integralAmount) && isNaN(integralAmount)){
		dialog("积分只能赠送整数！");
		return;
	} else if(isEmpty(integralAmount)) {
		integralAmount = 0;
	}
	
	//优惠券
	var coupon = jQuery("#couponSelect").val();
	
	if(giftmoneyAmount == 0 && integralAmount == 0 && isEmpty(coupon)) {
		dialog("暂无选择任何优惠项！");
		return;
	}
	
	if (coupon == null) {
		coupon = '';
	}
	
	var comment = jQuery("input[name='zsReason']").val();
	if (isEmpty(comment)) {
		dialog("请填写赠送原因，以备核查！");
		return;
	}
	
	var part = jQuery("[name='zsPartType']").val();
	var overdueMonth = jQuery("[name='zsPastDate']").val();
	
	var data = "memberId=" + memberId + "&giftmoneyAmount=" + giftmoneyAmount + "&part=" + part 
		+ "&overdueMonth=" + overdueMonth + "&integralAmount=" + integralAmount + "&coupon=" + coupon + "&comment=" + comment;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/presentGift",
		data : data,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("赠送成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}

function rechargeCard(type) {
	dataAjax.type = type;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/rechargeMemberInfo",
		data : dataAjax,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				if (e.code == 41007) {
					jQuery("#czModel").modal("show");
				}
				else {
					dialog(e.msg);
				}
				return;
			}
			dialog("保存成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}

function zzConfirm(){
	var outSubAccountId = jQuery("#outDIV").find(".current").attr("subAccountId");
	if (outSubAccountId == "") {
		dialog("未正确查找出转出会员！");
		return;
	}
	var inSubAccountId = jQuery("#inDIV").find(".current").attr("subAccountId");
	if (inSubAccountId == "") {
		dialog("未正确查找出转入会员！");
		return;
	}
	
	if (outSubAccountId == inSubAccountId) {
		dialog("转入会员账户不能相同！");
		return;
	} 
	
	var chargeAmount = jQuery("input[name = 'zzChargeAmount']").val();
	if (chargeAmount == "") {
		dialog("转入金额不能为空！");
		return;
	}
	var password = jQuery("input[name = 'zzPassword']").val();
	if (password == "") {
		dialog("转出会员密码不能为空！");
		return;
	}
	password = CryptoJS.MD5(CryptoJS.MD5(password).toString().toUpperCase()).toString().toUpperCase();
    var num = jQuery("#outDIV").find(".current").find("span[name='balance']").text();
	
	if (parseInt(num) < parseInt(chargeAmount)) {
		dialog("转账金额大于储值余额");
		return;
	}
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/checkoutAccount",
		data : "outSubAccountId="+outSubAccountId+"&inSubAccountId="+inSubAccountId+"&chargeAmount="+chargeAmount+"&password="+password,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("保存成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}

function hkConfirm(){
	var memberId = jQuery("#tab6").find("input[name = 'memberId']").val();
	if (memberId == "") {
		dialog("未正确查找出转出会员！");
		return;
	}

	var needRefund = jQuery("span[name = 'needRefund']").text();
	
	var cashAmount = jQuery("input[name = 'hkCashAmount']").val();
	
	var unionpayAmount = jQuery("input[name = 'hkUnionpayAmount']").val();
	
	var wechatAmount = jQuery("input[name = 'hkWechatAmount']").val();
	
	var alipayAmount = jQuery("input[name = 'hkAlipayAmount']").val();

	cashAmount = valueZero(cashAmount);
	unionpayAmount = valueZero(unionpayAmount);
	wechatAmount = valueZero(wechatAmount);
	alipayAmount = valueZero(alipayAmount);
	
	var realPrice = parseInt(cashAmount) + parseInt(unionpayAmount) + parseInt(wechatAmount) + parseInt(alipayAmount);
	
	if (realPrice > parseInt(needRefund)) {
		dialog("还款金额大于欠款金额！");
		return;
	}
	
	if (realPrice <= 0) {
		dialog("还款金额必须大于0！");
		return;
	}
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/refundMemberinfo",
		data : "memberId="+memberId+"&realPrice="+realPrice+"&cashAmount="+cashAmount+"&unionpayAmount="+unionpayAmount+"&wechatAmount="+wechatAmount+"&alipayAmount="+alipayAmount,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("保存成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}

function czCancel() {
	jQuery('#czModel').addClass('hide');
}

var amountIsNull = 1;

function sjConfirm() {
	if (amountIsNull == 1) {
		var cashAmount = jQuery("input[name = 'sjCashAmount']").val();
		var unionpayAmount = jQuery("input[name = 'sjUnionpayAmount']").val();
		if (cashAmount == "" && unionpayAmount == "") {
			jQuery('#sjModel').modal('show');
			return;
		}
	}
	pastDateConfirm(1);
}

function pastDateConfirm(type) {
	amountIsNull = type;
	var giftmoneyAmount = jQuery("input[name='sjGiftmoneyAmount']").val();
	giftmoneyAmount = valueZero(giftmoneyAmount);
	
    var pastDate = 0;
	
	if (parseInt(giftmoneyAmount) != 0) {
		pastDate = jQuery("select[name='sjPastDate']").val();
		pastDate = valueZero(pastDate);
	}
	queren();
}

function queren(){
	var memberId = jQuery("#tab4").find("input[name = 'memberId']").val();
	if (memberId == "") {
		dialog("请输入正确的升级会员！");
		return;
	}
	var levelId = jQuery("select[name='sjLevelId']").val();
	var oldLevelId = jQuery("#tab4").find("input[name = 'levelId']").val();
	if (levelId == oldLevelId) {
		dialog("升级会员等级相同，不能执行该操作！");
		return;
	}
	
    var deptId = jQuery("select[name= 'sjDeptId']").val();
	
	if (deptId == "") {
		dialog("请选择本次开卡业绩归属部门！");
		return;
	}
		
	var amountvalue = jQuery("#tab4").find("[name='openType']").html();

	var cashAmount = jQuery("input[name = 'sjCashAmount']").val();
	var unionpayAmount = jQuery("input[name = 'sjUnionpayAmount']").val();
    var wechatAmount = jQuery("input[name = 'sjWechatAmount']").val();
	var alipayAmount = jQuery("input[name = 'sjAlipayAmount']").val();
	var debtAmount = jQuery("input[name = 'sjDebtAmount']").val();
	
	cashAmount = valueZero(cashAmount);
	
	unionpayAmount = valueZero(unionpayAmount);
	wechatAmount = valueZero(wechatAmount);
	alipayAmount = valueZero(alipayAmount);
	
	debtAmount = valueZero(debtAmount);
	
	var realPrice = parseInt(cashAmount) + parseInt(unionpayAmount) + parseInt(debtAmount) + parseInt(wechatAmount) + parseInt(alipayAmount);
	
	var recommend = arrayObj("tab4", realPrice);
	if (recommend == -1) {
		return;
	}
	
	var partType = jQuery("select[name='sjPartType']").val();
	
	//礼金
	var giftmoneyAmount = jQuery("input[name='sjGiftmoneyAmount']").val();
	giftmoneyAmount = valueZero(giftmoneyAmount);
	
	var pastDate = jQuery("select[name='sjPastDate']").val();
	
	//卡金
	var rewardAmount = jQuery("input[name='sjRewardAmount']").val();
	rewardAmount = valueZero(rewardAmount);
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/upgradeMemberInfo",
		data : "memberId="+memberId+"&levelId="+levelId+"&amountvalue="+amountvalue+"&recommend="+recommend+"&giftmoneyAmount="+giftmoneyAmount+"&pastDate="+pastDate+"&partType="+partType+"&rewardAmount="+rewardAmount+"&cashAmount="+cashAmount+"&unionpayAmount="+unionpayAmount+"&wechatAmount="+wechatAmount+"&alipayAmount="+alipayAmount+"&debtAmount="+debtAmount+"&deptId="+deptId,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				return;
			}
			dialog("保存成功！");
			window.location.href = baseUrl + "KeepAccounts/initializeOpenCard";
		}
	});
}


jQuery("#outDIV").delegate("input[name='memberId']","change",function(){
	var memberId = jQuery("#outDIV").find("input[name='memberId']").val();
	if (memberId == "") {
		jQuery("#zzTbody").empty();
	}
	else{
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/selectZcDetail",
			data : "memberId="+memberId,
			async:false,//使用同步的Ajax请求  
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					return;
				}
				var data = e.msg;
				for (var i = 0 ; i < data.length; i++) {
					jQuery("#zzTbody").append("<tr>"+
					                            "<td>"+data[i].flowTime+"</td>"+
					                            "<td>"+data[i].name+"</td>"+
					                            "<td>"+data[i].orderName+"</td>"+
					                            "<td>"+data[i].flowAmount+"</td>"+
					                           "</tr>");
				}
			}
		});
	}
});

jQuery("#partDIV").delegate("input[name='memberId']","change",function(){
	var memberId = jQuery("#partDIV").find("input[name='memberId']").val();
	if (memberId == "") {
		jQuery("#czTbody").empty();
	}
	else{
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/selectCzDetail",
			data : "memberId="+memberId,
			async:false,//使用同步的Ajax请求  
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					return;
				}
				var data = e.msg;
				for (var i = 0 ; i < data.length; i++) {
					jQuery("#czTbody").append("<tr>"+
					                            "<td>"+data[i].flowTime+"</td>"+
					                            "<td>"+data[i].name+"</td>"+
					                            "<td>"+data[i].storeName+"</td>"+
					                            "<td>"+data[i].flowAmount+"</td>"+
					                           "</tr>");
				}
			}
		});
	}
});

function valueZero(value) {
	if (isEmpty(value)) {
		value = 0;
	}
	return value;
}

function checkNum(obj) {  
    //检查是否是非数字值  
    if (isNaN(obj.value)) {  
        obj.value = "";  
    }   
} 
