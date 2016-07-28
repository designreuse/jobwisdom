var enterpriseList = new Array();
enterpriseList[0] = "单店版";
enterpriseList[1] = "5店版";
enterpriseList[2] = "10版";
enterpriseList[3] = "无限版";


jQuery(function(){
    jQuery('.business').click(function(){
	    text=jQuery(this).text();
		if(text=='停业'){
		   jQuery(this).text('营业');
		   jQuery(this).removeClass('active');
       }
	    else{
		    jQuery(this).text('停业');
			jQuery(this).addClass('active');
		 }
    });
 });

//切换
jQuery(function(){
   jQuery('.add_store_include .add_store_content:gt(0)').hide();
   jQuery('.add_store_back li ').click(function(){
	   jQuery(this).addClass('active').siblings().removeClass('active');
	   jQuery('.add_store_include .add_store_content').eq(jQuery(this).index()).show().siblings().hide()
	 
  });
});	

//点击分配，弹出浮窗
jQuery('.distribution').delegate(".distribution_list>p span", "click", function(){
	jQuery(this).parents('.distribution_list').find('.distribution_alert').fadeIn();
})

//提示气泡
jQuery(function(){
	jQuery('.left_detail span a').hover(function(){
	jQuery(this).parent().find('em').stop(true,true).fadeIn('normal');
	},function(){
	  jQuery(this).parent().find('em').stop(true,true).fadeOut('normal');
	});
}) 

function addStore() {
	jQuery("#addOrUpdateStore").find("[type='hidden']").val('');
	jQuery("#addOrUpdateStore").find("[type='text']").val('');
	jQuery("#addOrUpdateStore").find("[type='checkbox']").removeAttr("checked");
	jQuery("img[name='affiliatedImage']").attr("src", qiniuUrl + "system/profile/set_img.png");
	jQuery("#searchtext").val("");
	jQuery("#addOrUpdateStore").show();
//	jQuery("#city-picker3").citypicker('destroy');
}

 jQuery('#preview').click(function(){
	imgObject = jQuery(this);
	editPage();
	jQuery(".mask").show();
 })
 
 jQuery('.cancelinput').click(function(){
	 jQuery(".mask").hide();
 })

var imgKey = "";
function zccCallback(dataBase64){
	imgObject.children("img").attr("src", dataBase64);
	var key = "jobwisdom/project/" + new Date().getTime();
    var data = {"stringBase64":dataBase64,"key":key};
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
		       	imgKey = key;
		       	jQuery(".mask").hide();
	       }
 	});
}

function upgradeRenew () {
	jQuery("#upgradeRenew").show();
	
	jQuery("select[name='upgradeValue'] option:gt(0)").remove();
	var type = 0;
	for (var i = 0; i < enterpriseList.length; i++) {
		var enterprise =  enterpriseList[i];
		if (type == 1) {
			var a = i + 1;
			jQuery("select[name='upgradeValue']").append("<option value='"+a+"' >"+enterprise+"</option>")
		}
		if (enterprise == enterpriseEdition) {
			type = 1;
		}
	}
}
 
function updateUpgradeValue () {
	var upgradeValue = jQuery("select[name='upgradeValue']").val();
	var renewDate =  jQuery("select[name='renewDate']").val();
	var payableEM = new Big(0);
	
	var priceMoney = priceMoneyOrTime.priceMoney;
	var useTiem = priceMoneyOrTime.useTiem;
	if (upgradeValue == 2) {
		var tatailMoney = new Big(3800 *useTiem  - priceMoney);
		payableEM = tatailMoney.plus(new Big(3800*renewDate));
	}
	else if (upgradeValue == 3){
		var tatailMoney = new Big(5800*useTiem - priceMoney);
		payableEM = tatailMoney.plus(new Big(5800*renewDate));
	}
    else if (upgradeValue == 4){
    	var tatailMoney = new Big(8800*useTiem - priceMoney);
		payableEM = tatailMoney.plus(new Big(8800*renewDate));
	}
    else {
    	if (enterpriseEdition == "单店版") {
    		payableEM = new Big(2400*renewDate);
    	}
    	else if (enterpriseEdition == "5店版") {
    		payableEM = new Big(3800*renewDate);
    	}
    	else if (enterpriseEdition == "10店版") {
    		payableEM = new Big(5800*renewDate);
    	}
    	else if (enterpriseEdition == "无限版") {
    		payableEM = new Big(8800*renewDate); 
    	}
    }
	jQuery("em[name='payableEM']").text(payableEM.toFixed(2));
}



function confirmUpgradeRenew () {
	var upgradeValue = jQuery("select[name='upgradeValue']").val();
	var renewDate =  jQuery("select[name='renewDate']").val();
	
	if (upgradeValue == 0 && renewDate == 0) {
		dialog("操作失败，必须选择升级版本或续费");
		return;
	}
	
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/confirmUpgradeRenew",
        data: "upgradeValue=" +upgradeValue+ "&renewDate="+renewDate,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	dialog("升级续费成功");
        	window.location.href = baseUrl + "storeinfo/view/showStoreList";
        }
    });
}

function changeStore (obj) {
	var storeId = jQuery(obj).val();
	jQuery("select[name='employeeSelect']").empty();
	jQuery("select[name='employeeSelect']").append("<option name = 'employee'>选择员工 </option>")
	for (var i = 0; i < storeEmployeeList.length; i++) {
		if (storeId == storeEmployeeList[i].storeId) {
			var employeeInfos = storeEmployeeList[i].employeeInfos;
			for (var j = 0; j < employeeInfos.length; j++) {
				var employeeInfo = employeeInfos[j];
				jQuery("select[name='employeeSelect']").append("<option value='"+employeeInfo.employeeId+"'><span class='gp'>"+employeeInfo.employeeCode+"</span> <span class='name'>"+employeeInfo.name+"</span></option>");
			}
		}
	}
	jQuery("select[name='employeeSelect']").trigger("liszt:updated");
}

var authorityObj = "";
function updateAuthority (obj, storeAuthorityId, storeId, employeeId, authorityValue) {
	if (!isEmpty(jQuery("input[name='storeAuthorityId']").val())) {
		if(confirm("正在编辑授权码，需要取消编辑吗？")){
			clinAuthority();
		}
	}
	
	authorityObj = obj;
	jQuery("input[name='storeAuthorityId']").val(storeAuthorityId);
	jQuery("select[name='storeSelect']").val(storeId);
	jQuery("select[name='storeSelect']").attr("disabled", "disabled");
	
	jQuery("select[name='employeeSelect']").attr("disabled", "disabled");
	jQuery("select[name='employeeSelect']").empty();
	jQuery("select[name='employeeSelect']").append("<option name = 'employee'>选择员工 </option>")
	for (var i = 0; i < storeEmployeeList.length; i++) {
		if (storeId == storeEmployeeList[i].storeId) {
			var employeeInfos = storeEmployeeList[i].employeeInfos;
			for (var j = 0; j < employeeInfos.length; j++) {
				var employeeInfo = employeeInfos[j];
				if (employeeInfo.employeeId == employeeId) {
					jQuery("select[name='employeeSelect']").append("<option value='"+employeeInfo.employeeId+"' selected = 'selected'><span class='gp'>"+employeeInfo.employeeCode+"</span> <span class='name'>"+employeeInfo.name+"</span></option>");
				}
				else {
					jQuery("select[name='employeeSelect']").append("<option value='"+employeeInfo.employeeId+"'><span class='gp'>"+employeeInfo.employeeCode+"</span> <span class='name'>"+employeeInfo.name+"</span></option>");
				}
			}
		}
	}
	
	jQuery("select[name='storeSelect']").trigger("liszt:updated");
	
	jQuery("input[name='authorityValue']").val(authorityValue);
	jQuery("select[name='employeeSelect']").trigger("liszt:updated");
}

function editStore (storeId) {
	jQuery("#addOrUpdateStore").show();
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/selectStoreInfo",
        data: "storeId=" + storeId,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	var obj = data.msg;
        	var storeInfo = obj.storeInfo;
      
        	imgKey = storeInfo.storeLogo;
        	jQuery("img[name='affiliatedImage']").attr("src", qiniuUrl + storeInfo.storeLogo);
        	jQuery("img[name='affiliatedImage']").attr("affiliatedImage", storeInfo.storeLogo);
        	jQuery("#storeName").val(storeInfo.storeName);
        	jQuery("#storeTel").val(storeInfo.storeTel);
        	jQuery("#storeLinkname").val(storeInfo.storeLinkname);
        	jQuery("#storeLinkphone").val(storeInfo.storeLinkphone);
        	jQuery("#city-picker3").citypicker('destroy');
        	jQuery("#city-picker3").citypicker({province: storeInfo.storeProvince, city: storeInfo.storeCity});
        	jQuery("#searchtext").val(storeInfo.storeAddress);
        	jQuery("input[name='storeId']").val(storeInfo.storeId);
        	
        }
    });
	/*jQuery("#city-picker3").citypicker('destroy');*/
}

function addAuthority () {
	
	var storeAuthorityId = jQuery("input[name='storeAuthorityId']").val();
	var storeId = jQuery("select[name='storeSelect']").val();
	var employeeId = jQuery("select[name='employeeSelect']").val();
	var authorityValue = jQuery("input[name='authorityValue']").val();
	
	if (isEmpty(storeId) || storeId == "选择门店") {
		dialog("门店不能为空");
		return;
	}
	
	if (isEmpty(employeeId) || employeeId == "选择员工") {
		dialog("员工不能为空");
		return;
	}
	var reg = /^\w+$/;
	if (!reg.test(authorityValue)) {
		// 如果验证失败给出警告
		dialog("授权码限定为字母、数字或下划线的组合");
		return;
	}
	if (authorityValue.length != 6) {
		// 如果验证失败给出警告
		dialog("请输入6位授权码");
		return;
	}
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/addOrUpdateAuthority",
        data: "storeAuthorityId=" + storeAuthorityId + "&storeId="+ storeId + "&employeeId=" + employeeId + "&authorityValue=" +authorityValue,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	var objData = data.msg;
        	if (!isEmpty(storeAuthorityId)) {
        		jQuery(authorityObj).parent().parent().remove();
        	}
        	jQuery("#authorityTable").append('<tr>'+
											   '<td>'+objData.storeName+'</td>'+
											   '<td>'+objData.authorityValue+'</td>'+
											   '<td>'+objData.employeeCode+' '+objData.name+'</td>'+
											   '<td>'+objData.createTime+'</td>'+
											   '<td>' +
											      '<em onclick="updateAuthority(this, '+objData.storeAuthorityId+', '+objData.storeId+', '+objData.employeeId+', \''+objData.authorityValue+'\')"><img src="'+baseUrl+'images/architecture_edit.png"></em>' +
											      '<em onclick="deleteAuthority(this, '+objData.storeAuthorityId+')"><img src="'+baseUrl+'images/architecture_delete.png"></em>' +
											   '</td>'+
											'</tr>');
        	clinAuthority();
        	dialog("授权码操作成功");
        }
    });
}

function clinAuthority () {
	jQuery("input[name='storeAuthorityId']").val("");
	jQuery("input[name='authorityValue']").val("");
	jQuery("select[name='storeSelect']").find("[name='store']").attr("selected", "selected");
	jQuery("select[name='employeeSelect']").find("[name='employee']").attr("selected", "selected");
	jQuery("select[name='storeSelect']").removeAttr("disabled");
	jQuery("select[name='employeeSelect']").removeAttr("disabled");
	jQuery("select[name='storeSelect']").trigger("liszt:updated");
	jQuery("select[name='employeeSelect']").trigger("liszt:updated");
}

function deleteAuthority (obj, storeAuthorityId) {
	if (!isEmpty(jQuery("input[name='storeAuthorityId']").val())) {
		if(confirm("正在编辑授权码，需要取消编辑吗？")){
			clinAuthority();
		}
		else {
			return;
		}
	}
	
	if(confirm("确认要删除该条授权码吗？")){
		jQuery.ajax({
	        cache: true,
	        type: "POST",
	        url: baseUrl + "storeinfo/action/deleteAuthority",
	        data: "storeAuthorityId=" + storeAuthorityId,
	        async: false,
	        success: function(data) {
	        	if (data.code != 0) {
	                dialog(data.msg);
	                return;
	            }
	        	jQuery(obj).parent().parent().remove();
	        	dialog("授权码删除成功");
	        }
	    });
	}
}

function msnRecharge () {
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/selectEnterpriseAccount",
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	var objData = data.msg;
        	jQuery("em[name = 'balanceAmountEM']").text(objData.balanceAmount);
        	jQuery("#balanceMsnNumEM").text(objData.balanceMsnNum);
        }
    });
	jQuery("#msnRechargeDIV").show();
}

function saveMsnRecharge () {
	var msnRechargeType = jQuery("input[name='msnRechargeType']:checked").val();
	var msnNumber = jQuery("input[name='msnNumber']").val();
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/saveMsnRecharge",
        data:"msnRechargeType="+msnRechargeType+"&msnNumber="+msnNumber,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	dialog("充值成功！");
        	/*jQuery("#msnRechargeDIV").hide();*/
        	location.reload();
        }
    });
}

function msnInputFocus (obj) {
	jQuery(obj).parents(".clearfix").find("input[name='msnRechargeType']").each(function () {
		if (jQuery(this).val() == 6) {
			jQuery(this).prop("checked",true);
		}
	});
}

function cancel() {
	jQuery("div[name='modelDiv']").hide();
	jQuery('#city-picker3').citypicker({
		  province: "",
		  city: "",
		});
	jQuery('#city-picker3').citypicker("reset");
}

function cancelAlert (obj) {
	jQuery(obj).parents(".distribution_alert").hide();
}

function rechargeFlow() {
	jQuery("#rechargeFlow").show();
	
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/rechargeFlow",
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	var objList = data.msg;
        	
        	jQuery("#rechargeFlowTable tr:gt(1)").remove();
        	for (var i = 0; i < objList.length; i++) {
        		var storeObj = objList[i];
        		jQuery("#rechargeFlowTable").append("<tr>"+
										               "<td>"+storeObj.storeName+"</td>"+
													   "<td>"+storeObj.createTime+"</td>"+
													   "<td>"+storeObj.balanceAmount+"</td>"+
													   "<td>"+storeObj.flowAmount+"</td>"+
													 "</tr>")
        	}
        }
    });
}

function distributionMsn (obj, storeId) {
	var distributionNum = jQuery(obj).parents(".distribution_alert").find("input[name='distributionNum']").val();
	
	jQuery.ajax({
        type: "POST",
        url: baseUrl + "storeinfo/action/distributionMsn",
        data: "storeId=" + storeId + "&distributionNum=" +distributionNum,
        dataType: "json",
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	dialog("分配成功！");
        	var obj = data.msg;
        	var objList = obj.storeInfoList;
        	var msnNum = obj.msnNum;
        	jQuery(".distribution").empty();
        	for (var i = 0; i < objList.length; i++) {
        		var storeObj = objList[i];
        		jQuery(".distribution").append("<div class='distribution_list'>"+
								   "<p>"+storeObj.storeName+"<span>分配</span></p>"+
								   "<div class='distribution_list_text'>"+
								     "<p>累计短信数量："+storeObj.totalSms+"条</p>"+
									  "<p>剩余短信数量："+storeObj.balanceSms+"条</p>"+
								   "</div>"+
								   "<div class='distribution_alert'>"+
								     "<p>拥有"+msnNum+"条</p>"+
								     "<p>增加<input type='text' name = 'distributionNum'><span style='position:relative;right:20px;color:#9f9d9d'>条</span></p>"+
									 "<div class='distribution_alert_button'>"+
									   "<button onclick='distributionMsn(this, "+storeObj.storeId+")'>确认</button>"+
									   "<button onclick = 'cancelAlert(this)'>取消</button>"+
									 "</div>"+
								   "</div>"+
								"</div>")
        	}
        	jQuery("span[name='msnNumSpan']").text(msnNum);
        }
    });
	
}

function consumptionRecord () {
	jQuery("#consumptionRecordTBODY tr:gt(0)").remove();
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/selectConsumptionRecord",
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	var objData = data.msg;
        	for (var i = 0; i < objData.length; i++) {
        		var obj = objData[i];
        		var str = "<tr>";
        		if (obj.flowType == 2) {
        			str += "<td style='color:green'>"+obj.flowAmount+"</td>";
        		}
        		else {
        			str += "<td>"+obj.flowAmount+"</td>";
        		}
        		str += "<td>"+obj.balanceAmount+"</td>"+
					   "<td>"+obj.businessType+"</td>"+
					   "<td>"+obj.createTime+"</td>"+
					 "</tr>";
															 
															 
	            jQuery("#consumptionRecordTBODY").append(str);
        	}
        }
    });
	jQuery("#consumptionRecordDiv").show();
}

//提交店铺信息
function saveStoreInfo(){
	var storeLogo = imgKey;
	var storeName = jQuery("#storeName").val();
	var storeTel = jQuery("#storeTel").val();
	var addressList = jQuery("#city-picker3").val().split('/');
	var storeProvince = addressList[0];
	var storeCity = addressList[1];
	var street = jQuery("#searchtext").val();
	
	
	var storeId = jQuery("input[name='storeId']").val();
		
	if (isEmpty(storeProvince)) {
		dialog("请选择省");
		return;
	}
	if (isEmpty(storeCity)) {
		dialog("请选择市");
		return;
	}
	if (isEmpty(street)) {
		dialog("请选择街道");
		return;
	}
	var storeAddress = street;
	if (typeof storeAddress === 'undefined') {
		dialog("请选择地址");
		return;
	}		
	var storeLinkname = jQuery("#storeLinkname").val();
	var storeLinkphone = jQuery("#storeLinkphone").val();
/* 		var latitude = jQuery("#lat").text();
	var longitude = jQuery("#lng").text(); */
	if (isEmpty(storeLogo)) {
        dialog("请上传您的店铺Logo");
        return;
    }
	if (isEmpty(storeName)) {
		dialog("请填写您的店铺名称");
		return;
	}
	/*if (isEmpty(storeAddress)) {
		dialog("请填写您的店铺地址");
		return;
	} else {
		var city = jQuery("#city").text();
		//alert(city);
		if (storeAddress.indexOf(city) == -1) {
			dialog("修改地址只能在您店铺所在城市进行修改");
			return;
		}
	}*/

	if (isEmpty(storeTel)) {
        dialog("请填写您的店铺电话");
        return;
    }
	if (isEmpty(storeLinkname)) {
        dialog("请填写您的店铺负责人姓名");
        return;
    }
	if (isEmpty(storeLinkphone)) {
        dialog("请填写您的店铺负责人电话");
        return;
    }
/* 		if (isEmpty(latitude) || isEmpty(longitude)) {
		dialog("店铺坐标获取失败，请刷新页面重新选取位置");
		return;
	} */
	var data = "storeId="+storeId+"&storeLogo=" + storeLogo + "&storeName=" + storeName + "&storeTel=" + storeTel + "&storeAddress=" + storeAddress + "&storeProvince=" + storeProvince + "&storeCity=" + storeCity
		+ "&storeLinkname=" + storeLinkname + "&storeLinkphone=" + storeLinkphone;
	submit(data, "保存成功，已更新您的店铺信息");
}

//数据提交
function submit(data, msg){
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/saveUpdateStore",
        data: data,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(data.msg);
                return;
            }
        	dialog(msg);
        	window.location.href = baseUrl + "storeinfo/view/showStoreList";
        }
    });
}