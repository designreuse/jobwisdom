$(function(){
	
    var num = MathRand();
    var systemCardNo = $("#systemCardNo").val();
    if(systemCardNo == ""){
    	$("#systemCardNo").val(num);
    }
	
    //会员列表
    var dropInp = new Array();
	dropInp.push($("input[name='directReferrerName']"));
	dropInp.push($("input[name='indirectReferrerName']"));
	if(membersList != ""){
		assembleData(membersList,"membersName,membersMobile","membersName,membersId",dropInp);
	}
    
	//员工列表
    var dropInp = new Array();
	dropInp.push($("input[name='employeeNo']"));
	dropInp.push($("input[name='employeeNo1']"));
	dropInp.push($("input[name='employeeNo2']"));
	dropInp.push($("input[name='employeeNo3']"));
	if(employeeList != ""){
		assembleData(employeeList,"employeeNo,employeeName","employeeNo,employeeId",dropInp);
	}
	
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		//按下键盘后焦点位置
		var focusElement = document.activeElement;
		
		supkeydown(e,focusElement);
	}
});

//验证手机号码
function validMobile(obj){
	$.ajax({
		type: "POST",
		url : basePath + "members/validMobile",
		dataType : "json",
		data: "type="+type,
		success : function(data){
			if(data.code == 0){
				if(data.msg == "error"){
					alert("同一个会员不可以添加2张以上的储值卡");
					$(".confirm").attr("disabled","disabled");
				}else{
					$(".confirm").removeAttr("disabled");
				}
			}
		}
	});
}

//比较二次密码是否一致
function confirmPwd(obj){
	var payPwd = $("#payPwd").val();
	var payPwdConfirm = $(obj).val();
	console.log("payPwd:"+payPwd+",payPwdConfirm:"+payPwdConfirm);
	if(payPwd != payPwdConfirm){
		alert("2次的密码不一致");
		$(".confirm").attr("disabled","disabled");
	}else{
		$(".confirm").removeAttr("disabled");
	}
}

//会员卡类型
function membersTypeSelect(obj){
	$("#cardNameId").empty();
	var type = $(obj).val();
	$.ajax({
		type: "POST",
		url : basePath + "members/membersTypeSel",
		dataType : "json",
		data: "type="+type,
		success : function(data){
			if(data.code == 0){
				var memberCardInfoList = data.msg;
				for(var i = 0; i < memberCardInfoList.length; i++){
					$("#cardNameId").append("<option value='"+memberCardInfoList[i].cardId+"'>"+memberCardInfoList[i].cardName+"</option>");
				}
			}
		}
	});
}

//类型选择
function typeSelect(obj){
	console.log("val:"+$(obj).val());
	$.ajax({
		type: "POST",
		url : basePath + "members/typeSelect",
		dataType : "json",
		data: "cardId="+$(obj).val(),
		success : function(data){
			if(data.code == 0){
				var memberCardInfo = data.msg;
				var cardMoneyRecharge = memberCardInfo.cardMoneyRecharge;//充值开卡金额
				var cardMoneyCash = memberCardInfo.cardMoneyCash;//现金售卡金额
				
				if(cardMoneyRecharge == 0){
					$("#topupCardDiv").attr("style","display:none");
				}
				if(cardMoneyCash == 0){
					$("#cashCardDiv").attr("style","display:none");
				}
				$("#topupCard").val(cardMoneyRecharge);
				$("#cashCard").val(cardMoneyCash);
			}
		}
	});
}


//推荐人信息查询 --暂无使用
function indirectReferrerQuery(obj,referrer){
	$.ajax({
		type: "POST",
		url : basePath + "members/getRecommendInfo",
		dataType : "json",
		data: "nameOrMobile="+$(obj).val(),
		success : function(data){
			if(data.code == 0){
				var membersList = data.msg;
				for(var i = 0; i < membersList.length; i++){
					var membersId = membersList[i].membersId;
					console.log("membersId:"+membersId);
					$("#"+referrer).val(membersId);
				}
			}
		}
	});
}

//表单保存
function saveMembers(){
	$.ajax({
        type: "POST",
        url: basePath + "members/saveMembres",
        data:$(".saveMembresForm").serialize(),// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	if(data.code == 0){
        		window.location.href = basePath+"members/toMembersList";
        	}else{
        		alert("信息填写错误");
        	}
        }
    });
}

//单人模式、多人模式选择事件
$("#commissionAllocation").delegate(".openCardSendIntegral","click",function(){
	console.log("val:"+$(this).val());
	if($(this).val() == 1){
		$("#manyPeopleDiv").hide();
	}else{
		$("#manyPeopleDiv").show();
	}
});

//返回会员列表界面
function returnMembersList(){
	window.location.href = basePath+"members/toMembersList";
}


//生成6位随机数
function MathRand(){
	var num=""; 
	for(var i=0;i<6;i++){ 
		num += Math.floor(Math.random()*10); 
	}
	return num;
}