var changePricList = new Array();
var changePricObj = null;
var tatailChangePric = new Big(0);

//原价卡上的图片的出现与消失
jQuery(function(){
  jQuery('.money_card_content li').click(function(){
     jQuery(this).find('.circle_pic').show();
	 jQuery(this).addClass('active')
  
  });
  
  initialization();
})

//支付会员卡变更时的处理
jQuery(".money_card_content").delegate("li", "click", function(event){
	jQuery(this).find('.circle_pic').show();
	jQuery(this).siblings().find('.circle_pic').hide();
	jQuery(this).addClass('active');
	jQuery(this).siblings().removeClass('active');
	initialization();
});

//初始化优惠项
function initialization () {
	
    var subAccountId = jQuery(".money_card_content").find(".active").attr("levelId");;
	
	//更新所有会员折扣价格
	jQuery(".change_price__spread").each(function(){
		var $this = jQuery(this);
		var detailId = $this.attr("detailId");
		if (!isEmpty(detailId)) {
			var detailType = $this.attr("detailType");
            var discountAmount = null;
            if (memberType == 1) {
            	discountAmount = discountMap[detailId + "_" + subAccountId];
            }
            else {
            	discountAmount = new Big($this.find("span[name='discountAmount']").attr("discountAmount"));
            }
			
			$this.find("span[name='discountAmount']").attr("discountAmount", new Big(discountAmount.toFixed(toFixedNum)).toFixed(2));
			$this.find("span[name='discountAmount']").text(new Big(discountAmount.toFixed(toFixedNum)).toFixed(2));
			
			$this.find("em[name='mustAmount']").attr("mustAmount", new Big(discountAmount.toFixed(toFixedNum)).toFixed(2));
			$this.find("em[name='mustAmount']").text(new Big(discountAmount.toFixed(toFixedNum)).toFixed(2));
			
			$this.find("span[name='actualAmount']").attr("actualAmount", new Big(discountAmount.toFixed(toFixedNum)).toFixed(2));
			$this.find("span[name='actualAmount']").text(new Big(discountAmount.toFixed(toFixedNum)).toFixed(2));
		}
		
	});
	
	if (memberType == 1) {
		var offObjList = jQuery("select[name='selectOff']");
		
		for (var i = 0; i < offObjList.length; i++) {
			  var offObj =  offObjList[i];
			  var discountAmount = new Big(jQuery(offObj).parents(".change_price__spread").find("span[name='discountAmount']").attr("discountAmount"));
			  
			  var optionValue = jQuery(offObj).html();
			  jQuery(offObj).empty();
			  jQuery(offObj).append(optionValue);
			  
			  var optionobj = jQuery(offObj).find("option");
			  for (var j = 1; j < optionobj.length; j++) {
				  var option = jQuery(optionobj[j]);
				  var offtype = option.attr("offtype");
				  var uid = option.attr("uid");
				  var tatailGiftmoney = new Big(allOffMap[uid]);
				  var maxOffAmount = new Big(option.attr("maxOffAmount"));
	              if (offtype == 4) {
	            	  if (tatailGiftmoney == 0) {
	            		  option.addClass("hide");
	      			  }
	            	  else {
	            		  if (tatailGiftmoney.lt(discountAmount)) {
	            			  if (maxOffAmount.lt(tatailGiftmoney)) {
	            				  option.attr("offAmount", maxOffAmount);
	            				  option.text(option.attr("offName")+ " -" +maxOffAmount+ "元");
	            				  option.removeClass("hide");
	            			  }
	            			  else {
	            				  option.attr("offAmount", tatailGiftmoney);
	            				  option.text(option.attr("offName")+ " -" +tatailGiftmoney+ "元");
	            				  option.removeClass("hide");
	            			  }
	            		  }
	            		  else {
	            			  if (maxOffAmount.lt(discountAmount)) {
	            				  option.attr("offAmount", maxOffAmount);
	            				  option.text(option.attr("offName")+ " -" +maxOffAmount+ "元");
	            				  option.removeClass("hide");
	            			  }
	            			  else {
	            				  option.attr("offAmount", discountAmount);
	            				  option.text(option.attr("offName")+ " -" +discountAmount+ "元");
	            				  option.removeClass("hide");
	            			  }
	            		  }
	            	  }
				  }
	              else if (offtype == 1) {
	            	  if (tatailGiftmoney == 0) {
	            		  option.addClass("hide");
	      			  }
	            	  else {
	            		  option.attr("offAmount", discountAmount);
	    				  option.text(option.attr("offName")+ " -" +discountAmount+ "元");
	    				  option.removeClass("hide");
	            	  }
	              }
				  
				  optionobj.removeClass("hide")
			  }
		 }
	}
	
    changePricList = new Array();
	
	jQuery("span[name='offAmount']").attr("offAmount", 0);
	jQuery("span[name='offAmount']").text(0);
	
	jQuery("em[name='detailFree']").removeClass("adjust_price positive");
    
	jQuery("em[name='detailFree']").attr("detailFree", 0);
	jQuery("em[name='detailFree']").text(0);
	tatailProc();
	
}

//弹出改价框
function showChangePric (obj) {
	jQuery(".zzc").show();
	var mustAmount  = jQuery(obj).parents(".change_price__spread").find("em[name='mustAmount']").attr("mustAmount");
	var detailId  = jQuery(obj).parents(".change_price__spread").attr("detailId");
	jQuery("em[name='freeMustAmount']").text(mustAmount);
	
	var  type = 0;
	for (var i = 0; i < changePricList.length; i++) {
		var changePric = changePricList[i];
		if (changePric.detailId == detailId) {
			jQuery("input[name='freeAmount']").val(changePric.freeAmount);
			jQuery("[name='orderRemark']").val(changePric.orderRemark);
			type = 1;
		}
	}
	
	if (type == 0) {
		jQuery("input[name='freeAmount']").val();
		jQuery("[name='orderRemark']").val();
	}
	changePricObj = obj;
}

//minus是减法
function confirm () {
	var detailId = jQuery(changePricObj).parents(".change_price__spread").attr("detailId");
	var mustAmount  = jQuery(changePricObj).parents(".change_price__spread").find("em[name='mustAmount']").attr("mustAmount");
	var freeAmount = jQuery("input[name='freeAmount']").val();
	var orderRemark = jQuery("[name='orderRemark']").val();
	
	if (isEmpty(freeAmount)) {
		dialog("改价后金额不能为空");
		return;
	}
	
	if (isEmpty(orderRemark)) {
		dialog("改价原因不能为空");
		return;
	}
	
	//修改实收金额
	jQuery(changePricObj).parents(".change_price__spread").find("span[name='actualAmount']").text(new Big(freeAmount).toFixed(toFixedNum));
	jQuery(changePricObj).parents(".change_price__spread").find("span[name='actualAmount']").attr("actualAmount", new Big(freeAmount).toFixed(toFixedNum));
	freeAmount = new Big(freeAmount - mustAmount).toFixed(toFixedNum);
	
	//是否存在标识
	var type = 0;
	for (var i = 0; i < changePricList.length; i++) {
		var changePric = changePricList[i];
		if (changePric.detailId == detailId) {
			tatailChangePric = tatailChangePric.plus(new Big(freeAmount)).minus(new Big(changePric.freeAmount));
			changePric.freeAmount = freeAmount;
			changePric.orderRemark = orderRemark;
			type = 1;
			break;
		}
	}
	if (type == 0) {
		tatailChangePric = tatailChangePric.plus(new Big(freeAmount))
		var pricObj = {"detailId" : detailId, "freeAmount" : freeAmount, "orderRemark" : orderRemark}
		changePricList.push(pricObj);
	}
	if (freeAmount > 0) {
		jQuery(changePricObj).parents(".change_price__spread").find("em[name='detailFree']").addClass("adjust_price");
	}
	else {
		jQuery(changePricObj).parents(".change_price__spread").find("em[name='detailFree']").addClass("adjust_price positive");
	}
	jQuery(changePricObj).parents(".change_price__spread").find("em[name='detailFree']").text(freeAmount);
	
	jQuery(changePricObj).parents(".change_price__spread").find("em[name='detailFree']").parent().find("i").remove();
	jQuery(changePricObj).parents(".change_price__spread").find("em[name='detailFree']").parent().append("<i>!<a href='javascript:;' class='bubble_'>"+orderRemark+"<em></em></a></i>");
	jQuery(changePricObj).parents(".change_price__spread").find("em[name='detailFree']").parent().append("<em style = 'font-size: 12px;text-decoration: underline;color: #459ae9;' onclick = 'revokeUpdayeMoney(this)'>撤销</em>")
	
	tatailProc();
	
	canle();
}

function revokeUpdayeMoney (obj) {
	var detailId = jQuery(obj).parents(".change_price__spread").attr("detailId");
	for (var i = 0; i < changePricList.length; i++) {
		var changePric = changePricList[i];
		if (changePric.detailId == detailId) {
			changePricList.remove(i);
			
			
		}
	}
}

function canle () {
	jQuery(".zzc").hide();
	jQuery(".zzc3").hide();
}

//查询当前礼金余量，修改其他option中礼金金额
function changeGiftmoney (uid) {
	var tatailGiftmoney = new Big(allOffMap[uid]);
	//查询所有已选中的礼金总额
	var selectedList = jQuery("select[name='selectOff'] option:selected");
	
	for (var i = 0; i < selectedList.length; i++) {
		var selectedObj =  selectedList[i];
		if (jQuery(selectedObj).attr("offtype") == 4) {
			var offAmount = jQuery(selectedObj).attr("offAmount");
			tatailGiftmoney = tatailGiftmoney.minus(new Big(offAmount));
		}
	}
	//未选中的礼金抵扣修改
	for (var i = 0; i < selectedList.length; i++) {
		var selectedObj =  selectedList[i];
		var discountAmount = new Big(jQuery(selectedObj).parents(".change_price__spread").find("span[name='discountAmount']").attr("discountAmount"));
		if (jQuery(selectedObj).attr("offtype") != 4) {
			var option  =jQuery(selectedObj).parents("select").find("option[uid='"+uid+"']");
			var maxOffAmount = new  Big(option.attr("maxOffAmount"));
			if (tatailGiftmoney == 0) {
      		  option.addClass("hide");
			  }
      	  else {
      		  if (tatailGiftmoney.lt(discountAmount)) {
      			  if (maxOffAmount.lt(tatailGiftmoney)) {
      				  option.attr("offAmount", maxOffAmount);
      				  option.text(option.attr("offName")+ " -" +maxOffAmount+ "元");
      				  option.removeClass("hide");
      			  }
      			  else {
      				  option.attr("offAmount", tatailGiftmoney);
      				  option.text(option.attr("offName")+ " -" +tatailGiftmoney+ "元");
      				  option.removeClass("hide");
      			  }
      		  }
      		  else {
      			  if (maxOffAmount.lt(discountAmount)) {
      				  option.attr("offAmount", maxOffAmount);
      				  option.text(option.attr("offName")+ " -" +maxOffAmount+ "元");
      				  option.removeClass("hide");
      			  }
      			  else {
      				  option.attr("offAmount", discountAmount);
      				  option.text(option.attr("offName")+ " -" +discountAmount+ "元");
      				  option.removeClass("hide");
      			  }
      		  }
      	   }
		}
	}
}

//查询当前疗程抵扣，修改其他option中疗程
function changeCombo (uid) {
	var tatailCombo = new Big(allOffMap[uid]);
	//查询所有已选中的疗程
	var selectedList = jQuery("select[name='selectOff'] option:selected");
	
	for (var i = 0; i < selectedList.length; i++) {
		var selectedObj =  selectedList[i];
		if (jQuery(selectedObj).attr("offtype") == 1) {
			tatailCombo = tatailCombo.minus(new Big(1));
		}
	}
	//未选中的礼金抵扣修改
	for (var i = 0; i < selectedList.length; i++) {
		var selectedObj =  selectedList[i];
		var discountAmount = new Big(jQuery(selectedObj).parents(".change_price__spread").find("span[name='discountAmount']").attr("discountAmount"));
		if (jQuery(selectedObj).attr("offtype") != 1) {
			var option  =jQuery(selectedObj).parents("select").find("option[uid='"+uid+"']");
			/*var maxOffAmount = new  Big(option.attr("maxOffAmount"));*/
			if (tatailCombo == 0) {
      		  option.addClass("hide");
			}
			else {
			  option.removeClass("hide");
			}
		}
	}
}

function updateDetailPric () {
	var selectedList = jQuery("select[name='selectOff'] option:selected");
	for (var i = 0; i < selectedList.length; i++) {
		var selected = selectedList[i];
		var offAmount = jQuery(selected).attr("offAmount");
	   jQuery(selected).parents(".change_price__spread").find("span[name='offAmount']").text(offAmount);
	   jQuery(selected).parents(".change_price__spread").find("span[name='offAmount']").attr("offAmount", offAmount);
	   var discountAmount = jQuery(selected).parents(".change_price__spread").find("span[name='discountAmount']").attr("discountAmount");
	   var mustAmount = new Big(discountAmount - offAmount);
	   
	   jQuery(selected).parents(".change_price__spread").find("em[name='mustAmount']").text(new Big(mustAmount.toFixed(toFixedNum)).toFixed(2));
	   jQuery(selected).parents(".change_price__spread").find("em[name='mustAmount']").attr("mustAmount", new Big(mustAmount.toFixed(toFixedNum)).toFixed(2));
	   jQuery(selected).parents(".change_price__spread").find("span[name='actualAmount']").text(new Big(mustAmount.toFixed(toFixedNum)).toFixed(2));
	   jQuery(selected).parents(".change_price__spread").find("span[name='actualAmount']").attr("actualAmount", new Big(mustAmount.toFixed(toFixedNum)).toFixed(2));
	}
}

var totalRealMoney = new Big(0);

function tatailProc () {
	var totailDiscountAmount = new Big(0);
	var totailOffAmount = new Big(0);
	var totailMustAmount = new Big(0);
	var totailActualAmount = new Big(0);
	var tatailChangePric = new Big(0);
	var spreadList  = jQuery(".change_price__spread");
	for (var i = 0; i < spreadList.length; i++) {
		var spread = spreadList[i];
		var offamount = jQuery(spread).find("select[name='selectOff'] option:selected").attr("offamount");
		totailOffAmount = totailOffAmount.plus(new Big(offamount));
		var mustAmount = jQuery(spread).find("em[name='mustAmount']").attr("mustAmount");
		totailMustAmount = totailMustAmount.plus(new Big(mustAmount));
		var actualAmount = jQuery(spread).find("span[name='actualAmount']").attr("actualAmount");
		totailActualAmount = totailActualAmount.plus(new Big(actualAmount));
		var changePric = jQuery(spread).find("em[name='detailFree']").attr("detailFree");
		tatailChangePric = tatailChangePric.plus(new Big(changePric));
		var discountAmount = jQuery(spread).find("span[name='discountAmount']").attr("discountAmount");
		totailDiscountAmount = totailDiscountAmount.plus(new Big(discountAmount));
	}
	
	jQuery("span[name='tatailChangePric']").text(new Big(tatailChangePric.toFixed(toFixedNum)).toFixed(2));
	if (tatailChangePric > 0) {
		jQuery("span[name='tatailChangePric']").addClass("adjust_price");
	}
	else if (tatailChangePric < 0){
		jQuery("span[name='tatailChangePric']").addClass("adjust_price positive");
	}
	else {
		jQuery("span[name='tatailChangePric']").removeClass("adjust_price positive");
	}
	
	jQuery("span[name='totailOffAmount']").text(new Big(totailOffAmount.toFixed(toFixedNum)).toFixed(2));
	jQuery("span[name='totailMustAmount']").text(new Big(totailMustAmount.toFixed(toFixedNum)).toFixed(2));
	jQuery("span[name='totailActualAmount']").text(new Big(totailActualAmount.toFixed(toFixedNum)).toFixed(2));
	jQuery("span[name='totailDiscountAmount']").text(new Big(totailDiscountAmount.toFixed(toFixedNum)).toFixed(2));
	totalRealMoney = totailActualAmount;
}

function changeOff (obj) {
	var offType = jQuery(obj).find("option:selected").attr("offType")
	var uid = jQuery(obj).find("option:selected").attr("uid");
	if (offType == 4) {
		changeGiftmoney(uid);
	}
	else if (offType == 1){
		changeCombo(uid);
	}
	else if (offType == 0) {
		var giftmoneyUid = jQuery("select[name='selectOff']").find("option[offType='4']").attr("uid");
		var comboUid = jQuery("select[name='selectOff']").find("option[offType='1']").attr("uid");
		if (!isEmpty(giftmoneyUid)) {
			changeGiftmoney(giftmoneyUid);
		}
		if (!isEmpty(comboUid)) {
			changeCombo(comboUid);
		}
	}
	
	updateDetailPric();
	tatailProc();
}

function checkIsChangePric () {
	if (changePricList.length > 0 && isEmpty(authorityEmployeeId)) {
		var totail = new Big(0);
		for (var i = 0; i < changePricList.length; i++) {
			var changePric = changePricList[i];
			totail = totail.plus(new Big(changePric.freeAmount));
		}
		
		if  (totail.abs().gte(new Big(updateMoneyAuthorize))) {
			jQuery("[name='totailChangeUpdateMoney']").text(new Big(totail.toFixed(toFixedNum)).toFixed(2));
			jQuery("[name='authorityValue']").val("");
			jQuery(".zzc3").show();
		}
		else {
			submitOrderInfo();
		}
	}
	else {
		submitOrderInfo();
	}
}
var authorityEmployeeId = "";
function subminChangePric () {
	var authorityValue = jQuery("input[name='authorityValue']").val();
	if (isEmpty(authorityValue)) {
		dialog("授权码不能为空");
		return;
	}
	jQuery.ajax({
        type: "POST",
        url: baseUrl + "storeinfo/action/selectAuthorityByAuthorityValue",
        data: "authorityValue="+authorityValue,
        success: function(data) {
			if(data.code != 0) {
				dialog(data.msg);
				jQuery("input[name='authorityValue']").val("");
				jQuery("input[name='authorityValue']").focus();
				return;
			}
			authorityEmployeeId = data.msg;
			submitOrderInfo();
			jQuery(".zzc3").hide();
        }
	});
}

/**
 * 提交订单
 */
function submitOrderInfo() {
	var details = new Array();
	var detailId = null;
	var couponValue = null;
	var isErr = false;
	jQuery(".change_price__spread").each(function(){
		var $this = jQuery(this);
		detailId = $this.attr("detailId");
		if (!isEmpty(detailId)) {
			var detailType = $this.attr("detailType");
			//检查该笔订单是否包含疗程，散客不能购买疗程
			if (memberType == 0 && detailType == 3) {
				isErr = true;
			}
			var off = jQuery($this.find("[name='selectOff'] option:selected"));
			if(isEmpty(off) || isEmpty(jQuery(off).attr("offType"))) {
				details.push({"detailId" : detailId, "offType" : 0, "offId" : 0, "offAmount" : 0});
			} else {
				details.push({"detailId" : detailId, "offType" : jQuery(off).attr("offType"), "offId" : jQuery(off).attr("offId"), "offAmount" : jQuery(off).attr("offAmount")});
			}
		}
	});
	if (isErr) {
		dialog("只有会员才能购买疗程，请先开卡");
		return;
	}

	var cardAmount = getBigObj("#cardpayAmount");
	var cashAmount = getBigObj("#cashAmount");
	var unionpayAmount = getBigObj("#unionpayAmount");
	var wechatAmount = getBigObj("#wechatAmount");
	var alipayAmount = getBigObj("#alipayAmount");
	var groupAmount = getBigObj("#groupAmount");
	var debtAmount = getBigObj("#debtAmount");
	
	var subAccountId = null;
	var balanceAmount = new Big(0);
	
	if (memberType == 1) {
		subAccountId = jQuery(".money_card_content").find(".active").attr("levelId");
		balanceAmount = jQuery(".money_card_content").find(".active").attr("balanceAmount");
	}
	if(cardAmount.gt(balanceAmount)){
		dialog("余额不足，请充值");
		return;
	}

	var tempAmount = cashAmount.plus(unionpayAmount).plus(wechatAmount).plus(alipayAmount).plus(groupAmount).plus(debtAmount);
	if (cardAmount.gt(0) && tempAmount.gt(0)) {
		dialog("卡金不能与其他方式组合支付");
		return;
	}
	tempAmount = tempAmount.plus(cardAmount);
	
	if (tempAmount.lt(totalRealMoney)) {
		dialog("还差" + new Big(totalRealMoney.minus(tempAmount).toFixed(toFixedNum)).toFixed(2) + "元才能买单哦");
		return;
	} 
	else if (tempAmount.gt(totalRealMoney)) {
		dialog("您多付了" + new Big(tempAmount.minus(totalRealMoney).toFixed(toFixedNum)).toFixed(2) + "元，请更正");
		return;
	}
	
	var subAccountId = jQuery(".money_card_content").find(".active").attr("levelId");
	
	var isNotify = jQuery("input:radio[name='isNotify']:checked").val();
	var data = {'orderId':orderId,'cardAmount':cardAmount,'cashAmount':cashAmount,
        	'unionpayAmount':unionpayAmount,'wechatAmount':wechatAmount,'alipayAmount':alipayAmount,
        	'groupAmount':groupAmount,'debtAmount':debtAmount,'detailList':details,'isNotify':isNotify,
        	'subAccountId':subAccountId, 'updatePricArray' : JSON.stringify(changePricList), 'authorityEmployeeId': authorityEmployeeId};
	
	jQuery.ajax({
        type: "POST",
        url: baseUrl + "selfcashier/action/submitorder",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(data) {
			if(data.code != 0) {
				dialog(data.msg);
				return;
			}
			dialog('结账成功');
			var orderMap = data.msg;
			jQuery("em[name='orderCode']").text(orderMap.orderCode);
			var stepCommissionList = orderMap.stepCommissionList;
			for (var i = 0; i < stepCommissionList.length; i++) {
				var stepCommission = stepCommissionList[i];
				var stepList = stepCommission.stepList;
				var table = jQuery("<table></table>");
				for (var j = 0; j < stepList.length; j++) {
					var step = stepList[j];
					var str = '<tr name = "commission" commissionId = "'+step.commissionId+'">';
					if (j == 0) {
						str += '<td rowspan="3">'+stepCommission.projectName+'</td>';
					}
					if (isEmpty(step.positionName)) {
						str += '<td ></td>';
					}
					else {
						str += '<td >'+step.positionName+'</td>';
					}
					str += '<td >'+step.employeeCodeName+'</td>'+
						   '<td ><input type="text" name = "commissionCalculate" value = "'+new Big(step.commissionCalculate).toFixed(2)+'"><em>元</em></td>'+
							'<td ><input type="text" name = "commissionAmount" value = "'+new Big(step.commissionAmount).toFixed(2)+'"><em>元</em></td>'+
						 '</tr>';
					table.append(str);

				}
				jQuery(".wash_way_content").append(table);
			}
			var payTypeList = orderMap.payTypeList;
			for (var i = 0; i < payTypeList.length; i++) {
				var payType = payTypeList[i];
				jQuery("li[name='payTypeLi']").append("<span>"+ payType.payName + " ￥"+payType.payAmount+"</span>")
			}
			jQuery("em[name='realAmount']").text(orderMap.realAmount);
			jQuery("div[name='payModal']").show();
			/*window.location.href = baseUrl + 'selfcashier/view/list';*/
		}
	});
}

function determine () {
	window.location.href = baseUrl + 'selfcashier/view/list';
}

function determineUpdate () {
	var commissionList = jQuery("tr[name='commission']");
	var commissionSaveList = new Array();
	for (var i = 0; i < commissionList.length; i++) {
		var commission = commissionList[i];
		var commissionId = jQuery(commission).attr("commissionId");
		var commissionCalculate = jQuery(commission).find("input[name='commissionCalculate']").val();
		var commissionAmount = jQuery(commission).find("input[name='commissionAmount']").val();
		var commissionSave = {"commissionId" : commissionId, "commissionCalculate" : commissionCalculate, "commissionAmount" : commissionAmount}
		commissionSaveList.push(commissionSave);
	}
	
	var commissionSaveListStr = JSON.stringify(commissionSaveList);
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "selfcashier/action/saveUpdateCommission",
		data : "commissionSaveListStr="+commissionSaveListStr,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("修改成功！");
			window.location.href = baseUrl + 'selfcashier/view/list';
		}
	});
}

function getBigObj(selector){
	var val = jQuery(selector).val();
	if (isEmpty(val)) {
		return new Big(0);
	}
	else {
		return new Big(val);
	}
}