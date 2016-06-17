jQuery(function() {
   if (loginType == 2)  {
	   dialog("你好，第一次登录的初哥");
   }
   
});

//字数超出，以气泡的形式
jQuery(function(){
    jQuery('.overflow_').hover(function(){
      var text=jQuery(this).text();
	   jQuery(this).parents('.consume').find('.copy_text').text(text);
 },function(){
   jQuery(this).parents('.consume').find('.copy_text').text('');
})
});

var isMember = false;
var orderId = null;
var useOffObject = new Object();
//实际支付金额
var totalRealMoney = new Big(0);
//订单原价
var totalReceivableMoney = new Big(0);
//订单折扣价
var totalDiscountAmount = new Big(0);
var allOffMap = null;
var discountMap = null;
var subAccountMap = new Object();

var detailOffMap = new Object();

var selectOffList = new Array();
var balanceAmount = 0;
var appointOff = 0;
var appointOffList = new Object();
//订单明细
var orderDetails = new Object();

function changeMoney(orderId) {
	
	jQuery("div[name='changeMoneyDIV']").show();
}

/**
 * 弹框搜索用户
 * @param orderId 订单标识
 * @param $obj 选中的行
 */
function openMemberInfoDialog(orderId, $obj) {
	jQuery("#btn-search-member").bind("click", function(){
		searchMemberInfo(orderId, $obj);
	});
	cleanSearchMember();
	jQuery("#find-member").modal({show:true, backdrop:"static"});
}
/**
 * 搜索会员
 * @param orderId 订单标识
 * @param $obj 选中的行
 */
function searchMemberInfo(orderId, $obj) {
	var memberId = null;
	var memberName = jQuery("#ipt-search-mamber").val();
	if(memberName == '') {
		dialog("请输入用户信息");
		return ;
	}
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"selfcashier/action/memberinfo",
        data: {'orderId' : orderId, 'phone' : memberName},
        async: false,
		success: function(data) {
			if(data.code == 0) {
				var memberinfo = data.msg;
				memberId = memberinfo.memberId;
				jQuery("#search-membername").text(memberinfo.name);
				jQuery("#search-memberlevel").text(memberinfo.levelName);
				jQuery("#search-memberamount").text(memberinfo.balanceAmount);
				jQuery("#search-memberstore").text(memberinfo.storeName);
				jQuery("#btn-order-member-submit").bind("click", function(){
					changeMemberInfo(orderId, memberId, memberinfo.name, $obj);
				});
			} else {
            	dialog("搜索不到会员，请重试");
            }
		}
	});
}
/**
 * 修改订单，绑定会员信息
 * @param orderId 订单标识
 * @param memberId 会员标识
 * @param memberName 会员名称
 * @param $obj 选中的行
 */
function changeMemberInfo(orderId, memberId, memberName, $obj) {
	var passwd = jQuery.trim(jQuery("#ipt-member-passwd").val());
	if(passwd == '') {
		dialog("请输入会员的支付密码！");
		return;
	}
	passwd = CryptoJS.MD5(CryptoJS.MD5(passwd).toString().toUpperCase()).toString().toUpperCase();
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"selfcashier/action/changemember",
        data: {'orderId' : orderId, 'memberId' : memberId, 'passwd' : passwd},
        async: false,
		success: function(data) {
			if(data.code == 0) {
				var memberTd = jQuery($obj).parent();
				var memberinfo = data.msg;
				memberTd.text(memberName);
				memberTd.addClass("can-click");
				jQuery("#btn-order-member-submit").unbind("click");
				jQuery('#cashier').modal('hide');
			} else if(data.msg != null){
            	dialog(data.msg);
            } else {
            	dialog("指定的会员不存在！");
            }
			jQuery('#find-member').modal('hide');
		}
	});
}
/**
 * 清除搜索会员的信息
 */
function cleanSearchMember() {
	jQuery("#ipt-search-mamber").val('');
	jQuery("#ipt-member-passwd").val('');
	jQuery("#search-membername").text('');
	jQuery("#search-memberlevel").text('');
	jQuery("#search-memberamount").text('');
	jQuery("#search-memberstore").text('');
}

function showCashierDetail(orderId) {
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "selfcashier/action/orderinfo",
        data: {"orderId" : orderId},
		success: function(data) {
			if(data.code != 0) {
				dialog(data.msg);
				return;
			}
			clearPayment();
			addCashierDetail(data.msg);
			if (data.msg.orderStatus == 2 || data.msg.orderStatus == 5) {
				jQuery(".money_next").css("visibility", "visible");
			}
			else {
				jQuery(".money_next").css("visibility", "hidden");
			}
			jQuery('.zzc').show();
		}
	});
}

function clearPayment(){
	jQuery("#cardpayAmount").val("0");
	jQuery("#cashAmount").val("0");
	jQuery("#unionpayAmount").val("0");
	jQuery("#wechatAmount").val("0");
	jQuery("#alipayAmount").val("0");
	jQuery("#groupAmount").val("0");
	jQuery("#debtAmount").val("0");
}

/**
 * 提交订单
 */
function submitOrderInfo() {
	var details = new Array();
	var detailId = null;
	var couponValue = null;
	var isErr = false;
	jQuery("#projectTbody tr").each(function(){
		var $this = jQuery(this);
		detailId = $this.attr("detailId");
		if (!isEmpty(detailId)) {
			var detailType = $this.attr("detailType");
			//检查该笔订单是否包含套餐，散客不能购买套餐
			if (!isMember && detailType == 3) {
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
		dialog("只有会员才能购买套餐，请先开卡");
		return;
	}

	var cardAmount = getBigObj("#cardpayAmount");
	var cashAmount = getBigObj("#cashAmount");
	var unionpayAmount = getBigObj("#unionpayAmount");
	var wechatAmount = getBigObj("#wechatAmount");
	var alipayAmount = getBigObj("#alipayAmount");
	var groupAmount = getBigObj("#groupAmount");
	var debtAmount = getBigObj("#debtAmount");
	
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
		dialog("还差" + totalRealMoney.minus(tempAmount).toFixed(2) + "元才能买单哦");
		return;
	} 
	else if (tempAmount.gt(totalRealMoney)) {
		dialog("您多付了" + tempAmount.minus(totalRealMoney).toFixed(2) + "元，请更正");
		return;
	}
	
	var isNotify = jQuery("input:radio[name='isNotify']:checked").val();
	var data = {'orderId':orderId,'cardAmount':cardAmount,'cashAmount':cashAmount,
        	'unionpayAmount':unionpayAmount,'wechatAmount':wechatAmount,'alipayAmount':alipayAmount,
        	'groupAmount':groupAmount,'debtAmount':debtAmount,'detailList':details,'isNotify':isNotify,
        	'subAccountId':jQuery("[name='nextLevelId']").attr("levelId")};
	
	jQuery.ajax({
        type: "POST",
        url: baseUrl + "selfcashier/action/submitorder",
        contentType: "application/json",
        data: JSON.stringify(data),
		success: function(data) {
			if(data.code != 0) {
				dialog(data.msg);
			} else {
				dialog('结账成功');
            }
			jQuery('#cashier').modal('hide');
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

function addCashierDetail(orderInfo){
	totalRealMoney = new Big(0);
	totalReceivableMoney = new Big(0);
	appointOff = 0;
	
	initHead(orderInfo);
	
	orderId = orderInfo.orderId;
	
	orderDetails = orderInfo.orderDetails;
	allOffMap = orderInfo.allOffMap;
	discountMap = orderInfo.discountMap;
	
	var tbody = document.getElementById("projectTbody");
	tbody.innerHTML = "";
	if (isMember) {
		jQuery("#projectTbody").append("<tr>"+
			     "<td>项目名称</td>"+
				 "<td>项目价格</td>"+
				 "<td>会员价格</td>"+
				 "<td style = 'width: 280px;'>疗程/礼金/优惠券抵扣</td>"+
				 "<td>实收金额</td>"+
			  "</tr>");
	}
	else {
		jQuery("#projectTbody").append("<tr>"+
			     "<td>项目名称</td>"+
				 "<td>项目价格</td>"+
				 "<td>实收金额</td>"+
			  "</tr>");
	}
	for (var i = 0; i < orderDetails.length; i++){
		var detail = orderDetails[i];
		var subAccountId = jQuery("#memberListUL").find(".active").attr("levelid");
		var realMoney = detail.discountAmount;
		
		var tr = document.createElement("tr");
		tr.setAttribute("detailId", detail.detailId)
		tr.setAttribute("detailType", detail.orderType);
		
		appointOffList[detail.detailId] = detail.appointOff;
		
		tdName = document.createElement("td");
		tdName.innerHTML = detail.projectName
		tr.appendChild(tdName);
		
		if (detail.appointOff > 0) {
			appointOff += detail.appointOff;
		}
		
		tdPrice = document.createElement("td");
		tdPrice.innerHTML = detail.projectPrice;
		tr.appendChild(tdPrice);
		
		if (!isEmpty(detail.freeAmount) && detail.freeAmount != '0' && detail.freeAmount != '0.00') {
			detail.projectPrice = tmpDiscountAmount;
			detail.discountAmount = tmpDiscountAmount;
		}
		
		totalReceivableMoney = totalReceivableMoney.plus(detail.projectPrice);
		if (isMember) {
			
			tr.setAttribute("uid", 0);
			tr.setAttribute("offId", 0);
			tr.setAttribute("offName", "");
			tr.setAttribute("offType", 0);
			tr.setAttribute("offAmount", 0);
			tr.setAttribute("projectPrice", detail.projectPrice - detail.appointOff);
			tr.setAttribute("discountAmount", detail.discountAmount);
			tr.setAttribute("highestDiscount", detail.highestDiscount);
			
			var tdDiscount = document.createElement("td");
			tdDiscount.innerHTML = detail.discountAmount;
			totalDiscountAmount = totalDiscountAmount.plus(new Big(detail.discountAmount));
			if (detail.appointOff > 0) {
				tdDiscount.innerHTML = detail.discountAmount + " (预约-" + detail.appointOff + ")";
			}
			tr.appendChild(tdDiscount);
			
			realMoney = new Big(detail.discountAmount - detail.appointOff);
			
			//套餐/礼金/优惠券抵扣，组装数据
			var selectOff = document.createElement("select");
			selectOff.setAttribute("detailid", detail.detailId);
			selectOff.setAttribute("class", "chzn-select");
			selectOff.setAttribute("name", "selectOff");
			selectOff.setAttribute("data-placeholder", "暂无优惠选项");
			
			var optionOff = document.createElement("option");
            optionOff.setAttribute("uid", 0);
            optionOff.setAttribute("detailId", detail.detailId);
            optionOff.setAttribute("offType", 0);
            optionOff.setAttribute("offId", 0);
            optionOff.setAttribute("offAmount", 0);
            optionOff.setAttribute("offName", '不使用优惠');
            optionOff.value = 0;
            optionOff.innerHTML = '不使用优惠';
            selectOff.appendChild(optionOff);
			
			//检查是否存在优惠项
	        var paymentOffList = detail.paymentOffList;
	        if (paymentOffList != null && paymentOffList.length > 0) {
	            var detailOffList = new Array();
	            //标记是否选择了最佳优惠项
	            var isSelected = false;
	            for (var j = 0; j < paymentOffList.length; j++) {
	                var offObj = paymentOffList[j];
	                
	                var uid = offObj.type + "_" + offObj.id;
	                detailOffList.push(uid);
	                //检查该优惠的可用次数/余额
	                var balance = new Big(allOffMap[uid]);
	                if (balance.lt(1)) {
	                	continue;
	                }
	                
	                var optionOff = document.createElement("option");
	                optionOff.setAttribute("uid", uid);
	                optionOff.setAttribute("detailId", detail.detailId);
	                optionOff.setAttribute("offType", offObj.type);
	                optionOff.setAttribute("offId", offObj.id);
	                optionOff.setAttribute("offAmount", offObj.amount);
	                optionOff.setAttribute("offName", offObj.name);
	                optionOff.value = uid;
	                optionOff.innerHTML = offObj.name + ' -' + offObj.amount + '元 ';
	                
	                //检查当前优惠是否被其他消费项目使用，如未使用，直接用为最佳优惠项，否则继续检查下一个
	                if (isSelected == false) {
	                    //如果为礼金，此次使用数量为项目价格
	                    if (offObj.type == 4) {
	                		offObj.amount = getMaxGiftOff(balance, new Big(detail.projectPrice - detail.appointOff), new Big(detail.highestDiscount));
                    		optionOff.setAttribute("offAmount", offObj.amount);
                    		optionOff.innerHTML = offObj.name + ' -' + offObj.amount + '元 ';
	                    	
	                    	realMoney = new Big(detail.projectPrice - detail.appointOff - offObj.amount);
	                    	balance = balance.minus(offObj.amount);
	                    } 
	                    else {
	                    	balance = balance.minus(1);
	                    	realMoney = realMoney.minus(offObj.amount);
	                    }
	                    isSelected = true;
	                    
	                    allOffMap[uid] = balance;
	                    tr.setAttribute("uid", uid);
	                    tr.setAttribute("offId", offObj.id);
	        			tr.setAttribute("offName",offObj.name);
	                    tr.setAttribute("offType", offObj.type);
	                    tr.setAttribute("offAmount", offObj.amount);
	                	optionOff.setAttribute("selected", "selected");
	                	if (balance.lt(1)) {
	                		removeOtherOff(detail.detailId, uid);
	                	}
	                }
	                selectOff.appendChild(optionOff);
	            }
	            detailOffMap[detail.detailId] = detailOffList;
	        }
	        
	        var tdOff = document.createElement("td");
			tdOff.appendChild(selectOff);
			tr.appendChild(tdOff);
			
			selectOffList.push(jQuery(selectOff));
            
            if (realMoney.lt(0)) {
            	realMoney = 0;
            }
		}
		
		//实收金额
		var tdReal = document.createElement("td");
		tdReal.innerHTML = realMoney;
		tr.appendChild(tdReal);
		
		totalRealMoney = totalRealMoney.plus(realMoney);
		syncRealMoney();
		tbody.appendChild(tr);
				
		jQuery("[name='selectOff']").chosen({disable_search_threshold: 10, width:"95%"});
	} 
	
	if (isMember) {
		allOffMap[0] = 9999;
		balanceAmount = new Big(orderInfo.subAccountList[0].balanceAmount);
		syncCardAmount();
	}
	else {
		jQuery("#cashAmount").val(totalRealMoney.toFixed(2));
	}
	
	jQuery("em[name='totalReceivableMoney']").html(totalReceivableMoney.toFixed(2));
	jQuery("#cashier").modal({show:true, backdrop:"static"});
}

function lastStep(){
	jQuery(".zzc1").hide();
	jQuery(".zzc").show();
}

function getMaxGiftOff(balance, projectPrice, highestDiscount){
	var off = balance;
	//如果余额大于抵扣金额，优惠金额＝余额
	if (off.gte(highestDiscount)) {
		off = highestDiscount;
	}
	//如果余额大于项目价格且小于最大抵扣金额
	if (off.gte(projectPrice) && projectPrice.lte(highestDiscount)) {
		off = projectPrice;
	}
	return off;
}

function syncRealMoney(){
	jQuery("[name='totalRealMoney']").text(totalRealMoney.toFixed(2));
}

function syncCardAmount(){
	if(balanceAmount.lt(totalRealMoney)){
		jQuery("#cardpayAmount").val(balanceAmount.toFixed(2));
	} else {
		jQuery("#cardpayAmount").val(totalRealMoney.toFixed(2));
	}
	jQuery("#memberCardBalance").html(balanceAmount.toFixed(2));
}

//优惠选项改变时的处理
jQuery("#projectTbody").delegate("[name='selectOff']", "change", function(event){
	var selectOff = jQuery(this).children('option:selected');
	var id = selectOff.val();
	
	var detailId = selectOff.attr("detailId") ;
	var tr = jQuery("tr[detailId='" + detailId + "']");
	var oldUid = tr.attr("uid");
	var oldType = tr.attr("offType");
	var oldId = tr.attr("offId");
	var oldName = tr.attr("offName");
	var oldAmount = tr.attr("offAmount");
	
	get(selectOff, id);
	
	put(detailId, oldUid, oldType, oldId, oldName, oldAmount);
});

//支付会员卡变更时的处理
jQuery(".money_card_content").delegate("li", "click", function(event){
	totalDiscountAmount = new Big(0);
	jQuery(this).find('.circle_pic').show();
	jQuery(this).siblings().find('.circle_pic').hide();
	jQuery(this).addClass('active');
	jQuery(this).siblings().removeClass('active');
	var subAccountId = jQuery(this).attr("levelid");
	//第二页显示选择的会员信息
	var levelName = jQuery(this).attr("levelName");
	var nextBalanceAmount = jQuery(this).attr("balanceAmount");
	var projectDiscount = jQuery(this).attr("projectDiscount");
	var goodsDiscount = jQuery(this).attr("goodsDiscount");
    
	jQuery("tr[name = 'nextLevelId']").attr("levelId", subAccountId);
	jQuery("td[name = 'nextLevelName']").text(levelName);
	jQuery("td[name = 'nextBalanceAmount']").text(nextBalanceAmount);
	jQuery("td[name = 'nextProjectDiscount']").text(projectDiscount);
	jQuery("td[name = 'nextGoodsDiscount']").text(goodsDiscount);
	
	//更新所有会员折扣价格
	jQuery("#projectTbody tr").each(function(){
		var $this = jQuery(this);
		var detailId = $this.attr("detailId");
		if (!isEmpty(detailId)) {
			var detailType = $this.attr("detailType");
			//购买套餐直接跳过
			if (detailType != 3) {
				//更新折扣价格
				var discountAmount = discountMap[detailId + "_" + subAccountId];
				totalDiscountAmount = totalDiscountAmount.plus(new Big(discountAmount));
				$this.attr("discountAmount", discountAmount);
				var appointOffStr = appointOffList[detailId];
				if (appointOffStr > 0) {
					$this.find("td:eq(2)").html(discountAmount.toFixed(2) + " (预约-" + appointOffStr + ")");
					discountAmount = new Big(discountAmount - appointOffStr);
				}
				else {
					$this.find("td:eq(2)").html(discountAmount.toFixed(2));
				}
				
				//更新实收价格
				var offType = $this.attr("offtype");//1:项目套餐，2:商品套餐，3:优惠券，4:礼金
				if (offType == 3 || offType == 0) {
					var offAmount = $this.attr("offAmount");
					var realAmount = new Big(discountAmount - offAmount);
					/*realAmount = realAmount.plus(new Big());*/
					if (realAmount.lt(0)) {
						realAmount = 0;
					}
					var realObj = $this.find("td:last");
					totalRealMoney = totalRealMoney.minus(realObj.html()).plus(realAmount);
					syncRealMoney();
					realObj.html(realAmount.toFixed(2));
				}
			}
		}
	});
	
	balanceAmount = subAccountMap[subAccountId];
	syncCardAmount();
	
});

function nextCheckout() {
	jQuery(".zzc_1_card1").empty();
	if (isMember) {
		jQuery(".zzc_1_card1").append('<tr>'+
						                '<td>订单原价</td>'+
						                '<td>会员价格</td>'+
										'<td>抵扣金额</td>'+
										'<td>实收金额</td>'+
										'<td>操作</td>'+
									  '</tr>'+
									  '<tr>'+
									    '<td>'+totalReceivableMoney+'</td>'+
						                '<td>'+totalDiscountAmount+'</td>'+
						                '<td >'+appointOff+'</td>'+
										'<td >'+totalRealMoney+'</td>'+
										'<td><span class="change_price_red" onclick="updatePric()">改价</span></td>'+
									  '</tr>');
	}
	else {
		jQuery(".zzc_1_card").addClass("hide");
		jQuery(".zzc_1_card1").append('<tr>'+
						                '<td>订单原价</td>'+
										'<td>抵扣金额</td>'+
										'<td>实收金额</td>'+
										'<td>操作</td>'+
									  '</tr>'+
									  '<tr>'+
						                '<td >'+totalReceivableMoney+'</td>'+
										'<td >'+appointOff+'</td>'+
										'<td >'+totalRealMoney+'</td>'+
										'<td><span class="change_price_red" onclick="updatePric()">改价</span></td>'+
									  '</tr>');
	}
}

//选择新的优惠项
function get(selectOff, id){
	var detailId = selectOff.attr("detailId") ;
	var tr = jQuery("tr[detailId='" + detailId + "']");
	var uid = selectOff.attr("uid");
	var offType = selectOff.attr("offType");
	var offId = selectOff.attr("offId");
	var offName = selectOff.attr("offName");
	var offAmount = selectOff.attr("offAmount");
	
	var projectPrice = new Big(tr.attr("projectPrice"));
	var discountAmount = new Big(tr.attr("discountAmount"));
	
	//计算使用余额
	var useAmount = 0;
	var tealMoneyTd = tr.find("td:last");
	var curRealMoney = new Big(tealMoneyTd.html());
	var realMoney = new Big(0);
	
	var banlance = new Big(allOffMap[uid]);
	if (offType == 4) {
		useAmount = getMaxGiftOff(banlance, new Big(projectPrice), new Big(tr.attr("highestDiscount")));
		
		realMoney = projectPrice.minus(useAmount);
		banlance = banlance.minus(useAmount);
		allOffMap[uid] = banlance;
		
		//礼金还需要检查其他的待选项，同步其可用金额
		for (var j = 0; j < selectOffList.length; j++) {
			var tempSelect = selectOffList[j];
			var tempDetailId = tempSelect.attr("detailId");
			//如果为当前选项，直接跳过
			if (tempDetailId == detailId) {
				continue;
			}
			
			//检查遍历项是否可以使用该项优惠，如不可以直接跳过
			var useOffList = detailOffMap[tempDetailId];
			if(jQuery.inArray(uid, useOffList) < 0){
				continue;
			}
			
			var option = tempSelect.children("[uid='" + uid + "']");
			var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']");
			var option = tempSelect.children("[uid='" + uid + "']");
			if (option != null && tempDetail.attr("uid") != option.attr("uid")) {
				var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']")
				
				var tempOffAmount = getMaxGiftOff(banlance, new Big(tempDetail.attr("projectPrice")), new Big(tempDetail.attr("highestDiscount")));
				
				var tempOffset = tempOffAmount.minus(option.attr("offAmount"));
				option.attr("offAmount", tempOffAmount);
				option.html(offName + ' -' + tempOffAmount + "元");
				tempSelect.trigger("liszt:updated");
			}
		}
	} else {
		useAmount = offAmount;
		realMoney = discountAmount.minus(useAmount);
		banlance = banlance.minus(1);
		allOffMap[uid] = banlance;
	}
	if (realMoney.lt(0)) {
		realMoney = 0;
	}
	//重新计算实收
	tealMoneyTd.html(realMoney.toFixed(2));
	totalRealMoney = totalRealMoney.minus(curRealMoney).plus(realMoney);
	syncRealMoney();
	syncCardAmount();
	
	tr.attr("uid", id);
	tr.attr("offId", offId);
	tr.attr("offName", offName);
	tr.attr("offType", offType);
	tr.attr("offAmount", useAmount);
	
	//如果余额为0，需要检查其他选项表，将其删除
	if(banlance.lte(0)){
		removeOtherOff(detailId, uid);
	}
}

function removeOtherOff(detailId, uid){
	for (var j = 0; j < selectOffList.length; j++) {
		var tempSelect = selectOffList[j];
		var tempDetailId = tempSelect.attr("detailId");
		//如果为当前选项，直接跳过
		if (tempDetailId == detailId) {
			continue;
		}
		
		//检查遍历项是否可以使用该项优惠，如不可以直接跳过
		var useOffList = detailOffMap[tempDetailId];
		if(jQuery.inArray(uid, useOffList) < 0){
			continue;
		}

		var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']");
		var option = tempSelect.children("[uid='" + uid + "']");
		if (option != null && tempDetail.attr("uid") != option.attr("uid")) {
			option.remove();
			tempSelect.trigger("liszt:updated");
		}
	}
}

function put(detailId, oldUid, oldType, oldId, oldName, oldAmount){
	var oldBanlance = new Big(allOffMap[oldUid]);
	//1.归还使用金额/数量
	if (oldType == 4) {
		oldBanlance = oldBanlance.plus(oldAmount);
	} else {
		oldBanlance = oldBanlance.plus(1);
	}
	allOffMap[oldUid] = oldBanlance;
	
	//2.检查是否有需要该资源的消费项
	for (var j = 0; j < selectOffList.length; j++) {
		var tempSelect = selectOffList[j];
		var tempDetailId = tempSelect.attr("detailId");
		//如果为当前选项，直接跳过
		
		//检查遍历项是否可以使用该项优惠，如不可以直接跳过
		var useOffList = detailOffMap[tempDetailId];
		if(jQuery.inArray(oldUid, useOffList) < 0){
			continue;
		}
		
		var option = tempSelect.children("[uid='" + oldUid + "']");
		var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']")
		var tempProjectPrice = new Big(tempDetail.attr("projectPrice"));
		var tempDiscountAmount = new Big(tempDetail.attr("discountAmount"));
		var highestDiscount = new Big(tempDetail.attr("highestDiscount"));
		//如果未使用，加入其中
		if (option == null || option.length == 0) {
			var optionOff = document.createElement("option");
            optionOff.setAttribute("uid", oldUid);
            optionOff.setAttribute("detailId", tempDetailId);
            optionOff.setAttribute("offType", oldType);
            optionOff.setAttribute("offId", oldId);
            optionOff.setAttribute("offName", oldName);
            optionOff.value = oldUid;
            //如果是礼金，需要检查抵扣金额
            if (oldType == 4) {
            	oldAmount = oldBanlance;
            	if (highestDiscount.lt(oldAmount)) {
            		oldAmount = highestDiscount;
            	}
            	if (tempProjectPrice.lt(oldAmount)) {
            		oldAmount = tempProjectPrice;
            	}
            }
            optionOff.setAttribute("offAmount", oldAmount);
            optionOff.innerHTML = oldName + ' -' + oldAmount + '元 ';
            tempSelect.append(optionOff);
            
            tempSelect.trigger("liszt:updated");
		} 
	}
	
	//如果是礼金
	if (oldType == 4) {
		//1.先检查所有在使用礼金的优惠列表
		var giftOptionList = jQuery("option:selected[uid='" + oldUid + "']");
		for (var i = 0; i < giftOptionList.length; i++) {
			var giftOption = jQuery(giftOptionList[i]);
			
			var tempDetailId = giftOption.attr("detailId");
			var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']");
			var tempProjectPrice = new Big(tempDetail.attr("projectPrice"));
			var highestDiscount = new Big(tempDetail.attr("highestDiscount"));
			
			var tempOffAmount = new Big(giftOption.attr("offAmount"));
			oldBanlance = oldBanlance.plus(tempOffAmount);
			tempOffAmount = oldBanlance;
			if (tempOffAmount.gt(highestDiscount)) {
				tempOffAmount = highestDiscount;
			}
			if (tempOffAmount.gt(tempProjectPrice)) {
				tempOffAmount = tempProjectPrice;
			}
			giftOption.attr("offAmount", tempOffAmount);
			giftOption.html(oldName + ' -' + tempOffAmount + "元");
			oldBanlance = oldBanlance.minus(tempOffAmount);
			allOffMap[oldUid] = oldBanlance;
			
			//重新计算实收金额
			var curRealMoney = new Big(tempDetail.find("td:last").html());
			var tempRealMoney = tempProjectPrice.minus(tempOffAmount).toFixed(2);
			totalRealMoney = totalRealMoney.minus(curRealMoney).plus(tempRealMoney);
			tempDetail.find("td:last").html(tempRealMoney);
			tempDetail.attr("offAmount", tempOffAmount);
			
			syncRealMoney();
			syncCardAmount();
			jQuery("select[detailId='" + tempDetailId +"'").trigger("liszt:updated");
		}
		
		//2.再检查所有存在礼金优惠项的优惠列表，同步其余额
		giftOptionList = jQuery("option[uid='" + oldUid + "']");
		for (var j = 0; j < giftOptionList.length; j++) {
			var giftOption = jQuery(giftOptionList[j]);
			var tempDetailId = giftOption.attr("detailId");
			var tempDetail = jQuery("tr[detailId='" + tempDetailId + "']")
			if(giftOption.attr("uid") == tempDetail.attr("uid")){
				continue;
			}
			if(oldBanlance.lte(0)){
				giftOption.remove();
			} 
			else {
				var highestDiscount = new Big(tempDetail.attr("highestDiscount"));
				var projectPrice = new Big(tempDetail.attr("projectPrice"));
				
				var tempOffAmount = new Big(giftOption.attr("offAmount"));
				tempOffAmount = oldBanlance;
				if (tempOffAmount.gt(highestDiscount)) {
					tempOffAmount = highestDiscount;
				}
				if (tempOffAmount.gt(projectPrice)) {
					tempOffAmount = projectPrice;
				}
				var tempOffset = tempOffAmount.minus(giftOption.attr("offAmount"));
				giftOption.attr("offAmount", tempOffAmount);
				giftOption.html(oldName + ' -' + tempOffAmount + "元");
			}
			
			jQuery("select[detailId='" + tempDetailId +"'").trigger("liszt:updated");
		}
	}
	
}

//初始化订单支付头部信息
function initHead(orderInfo){
	var orderCode = orderInfo.orderCode;
	var memberId = orderInfo.memberId;
	var memberName = "";
	var memberLevel = "";
	var memberImg = "";
	if (!isEmpty(memberId)) {
		memberName = orderInfo.memberInfo.name;
		memberLevel = orderInfo.memberInfo.levelName;
		memberImg = orderInfo.memberInfo.headUrl;
		isMember = true;
	} else {
		memberName = "散客";
		memberLevel = orderInfo.sex;
		if (memberLevel == "男") {
			memberImg = "system/profile/common_img_man.png";
		} else {
			memberImg = "system/profile/common_img_gril.png";
		}
		isMember = false;
	}
	memberImg = picUrl + memberImg;
	
	jQuery(".money_head_p").find("img").attr("src", memberImg);
	jQuery(".boss_sex").html(memberName);
	jQuery("em[name='orderNumber']").html(orderCode);

	if(!isEmpty(memberId)) {
		jQuery(".roll_money").removeClass("hide");
		var subAccountList = orderInfo.subAccountList;
		jQuery("#memberListUL").empty();
		for (var i = 0; i < subAccountList.length; i++) {
			var subAccount = subAccountList[i];
			subAccountMap[subAccount.subAccountId] = new Big(subAccount.balanceAmount);
			jQuery("#memberListUL").append("<li levelId = '" + subAccount.subAccountId + "' balanceAmount = '"+subAccount.balanceAmount+"' levelName = '"+subAccount.levelName+"' projectDiscount = '"+subAccount.projectDiscount+"' goodsDiscount = '"+subAccount.goodsDiscount+"'>"+
					    "<div class='original_card'>"+
						  "<div class='card_first'>"+subAccount.levelName +"<span class='circle_pic'><img src='"+baseUrl+"images/circle.png'></span></div>"+
						  "<p>余额：<em class='rest'>"+subAccount.balanceAmount+"</em></p>"+
						   "<p>项目折扣：<em class='ten_money'>" + subAccount.projectDiscount + "折</em></p>"+
						    "<p>商品折扣：<em class='ten_money'>"+ subAccount.goodsDiscount + "折</em></p>"+
						"</div>"+
					  "</li>");
			if (i == 0) {
				jQuery("#memberListUL").find("li").find('.circle_pic').show();
				jQuery("#memberListUL").find("li").addClass('active');
				jQuery("tr[name = 'nextLevelId']").attr("levelId", subAccount.subAccountId);
				jQuery("td[name = 'nextLevelName']").text(subAccount.levelName);
				jQuery("td[name = 'nextBalanceAmount']").text(subAccount.balanceAmount);
				jQuery("td[name = 'nextProjectDiscount']").text(subAccount.projectDiscount);
				jQuery("td[name = 'nextGoodsDiscount']").text(subAccount.goodsDiscount);
			}
		}
		jQuery(".roll_money").removeClass("hide");
	} else {
		jQuery(".roll_money").addClass("hide");
	}
}

var deleteOrderId = "";
var deleteOrderObj = "";
function deleteOrder (obj, deleteOrderIds) {
	jQuery("#deleteOrderModal").modal("show");
	deleteOrderId = deleteOrderIds;
	deleteOrderObj = jQuery(obj).parents(".money_content");
}

function deleteOrderModalCancel () {
	jQuery("#deleteOrderModal").modal("hide");
}

function rechargeDeleteOrder () {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "selfcashier/action/deleteOrderInfo",
		data : "orderId="+deleteOrderId,
		async:false,//使用同步的Ajax请求 
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("取消成功！");
			jQuery(deleteOrderObj).remove();
			deleteOrderModalCancel ();
		}
	});
}

function updatePric () {
	jQuery(".change_price_content").show();
	jQuery("select[name='projectSelect']").empty();
	for (var i = 0; i < orderDetails.length; i++) {
		var detail = orderDetails[i];
		jQuery("select[name='projectSelect']").append("<option value = '"+detail.detailId+"'>"+detail.projectName+"<option>");
		if (i == 0) {
			if (isMember) {
				
			}
			else {
				
			}
		}
	}
}

function cancleSure() {
	jQuery(".change_price_content").hide();
}