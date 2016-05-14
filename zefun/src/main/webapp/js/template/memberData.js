var memberDataId = "";

var memberDatePageNo = {"moneyFlowPageNo":1, "orderComboPageNo":1, "integralFlowPageNo":1, "orderProjectPageNo":1, "orderGoodsPageNo":1, "giftMoneyFlowPageNo":1, "debtFlowPageNo":1};

var memberDatePageSize = {"moneyFlowPageSize":5, "orderComboPageSize":5, "integralFlowPageSize":5, "orderProjectPageSize":5, "orderGoodsPageSize":5, "giftMoneyFlowPageSize":5, "debtFlowPageSize":5};

var memberDateTotalPage = {"moneyFlowTotalPage":0, "orderComboTotalPage":0, "integralFlowTotalPage":0, "orderProjectTotalPage":0, "orderGoodsTotalPage":0, "giftMoneyFlowTotalPage":0, "debtFlowTotalPage":0};

var businessTypeArray = new Array("","消费","充值","转账","开卡","升级", "卡金", "会员导入", "数据迁移");

var offTypeArray = new Array("", "套餐", "优惠券", "礼金");

var inOutTypeArray = new Array("", "支出", "收入");

var flowTypeArray = new Array("", "-", "+");

var debtTypeArray = new Array("","挂账","还款");

var memberList = "";

jQuery(document).ready(function(){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectStoreMemberInfo",
		dataType : "json",
		beforeSend : function(){
		},
		complete : function(){
		},
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			memberList = e.msg;
		}
	});
});

function dimSelectMember (obj, str) {
	var a = 1;
	var parentsObj = jQuery(obj).parents("div[name='memberTR']");
	parentsObj.find(".focus_input").empty();
	for (var i = 0; i < inputMemberList.length; i++) {
		var mMap = inputMemberList[i];
		var name = mMap.name.toString();
		var phone = mMap.phone.toString();
		if (name.indexOf(str.toString()) != -1){
			parentsObj.find(".focus_input").append("<li class='text-center' onclick = choosePhone(this,'"+phone+"')><sapn><span class='mr10'>"+name+":</span>"+phone+"</sapn></li>");
			a++;
			if (a == 10) {
				break;
			}
			continue;
		}
		if (phone.indexOf(str) != -1) {
			parentsObj.find(".focus_input").append("<li class='text-center' onclick = choosePhone(this,'"+phone+"')><sapn><span class='mr10'>"+name+":</span>"+phone+"</sapn></li>");
			a++;
			if (a == 10) {
				break;
			}
			continue;
		}
		inputMemberList.splice(i,1);//从下标为i的元素开始，连续删除1个元素
        i--;//因为删除下标为i的元素后，该位置又被新的元素所占据，所以要重新检测该位置
	}
	parentsObj.find(".focus_input").append("<em class='t-border'></em>"+
                                           "<span class='t-content'></span>")
}

function choosePhone (obj, phone) {
	jQuery(obj).parents("div[name='memberTR']").find("input[name='phoneNumber']").val(phone);
	submitPhone (obj);
}

jQuery("body").delegate("input[name='phoneNumber']","blur", function(event){
	setTimeout(function () {
		jQuery(".focus_input").css("display", "none");
	},500);
});

jQuery("body").delegate("input[name='phoneNumber']","focus", function(event){
	
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	
	if (isEmpty(jQuery(obj).val()) || obj != inputMemberObj) {
		jQuery(obj).parents("div[name='memberTR']").find(".focus_input").empty();
		inputMemberList = JSON.parse(JSON.stringify(memberList));
		inputPhoneNum = 0;
		inputMemberObj = obj;
		jQuery(obj).val("");
		return;
	}

	dimSelectMember(obj, jQuery(obj).val());
	
	jQuery(obj).parents("div[name='memberTR']").find(".focus_input").css("display", "block");
});

//当前
var inputMemberObj = "";
var inputPhoneNum = 0;
var inputMemberList = "";

jQuery(document).keyup(function(event){ 
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	if (jQuery(obj).attr("name") == "phoneNumber") {
		
		if (isEmpty(jQuery(obj).val())) {
			jQuery(obj).parents("div[name='memberTR']").find(".focus_input").empty();
			inputMemberList = JSON.parse(JSON.stringify(memberList));
			return;
		}
		
		jQuery(obj).parents("div[name='memberTR']").find(".focus_input").css("display", "block");
		
		if (inputPhoneNum < jQuery(obj).val().length) {
			dimSelectMember(obj, jQuery(obj).val());
		}
		else {
			inputMemberList = JSON.parse(JSON.stringify(memberList));
			dimSelectMember(obj, jQuery(obj).val());
		}
		inputPhoneNum = jQuery(obj).val().length;
		//输入11位自动提交
		/*if (jQuery(obj).val().length == 11) {
			submitPhone (obj);
		}*/
	}
}); 

jQuery("#orderComboTBODY").delegate(".project-toggle", "click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
    var thisElement = jQuery(obj);
    var thisElementParent = thisElement.parents("tr")
    var projectPart = thisElementParent.nextUntil(".single");
    if(!projectPart.is(":visible")){
        projectPart.show();
    }
    else{
        projectPart.hide();
    }
});

jQuery("div[name = 'memberTR']").delegate("span[name='breakName']","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	jQuery(obj).parents("div[name='memberTR']").find("input[name='memberId']").val("").change();
	jQuery(obj).parents("div[name='memberTR']").find("div[name='resultTD']").css("display","none");
    jQuery(obj).parents("div[name='memberTR']").find("div[name='seekTD']").css("display", "inline-block");
});

//通过手机号码查询会员
jQuery("div[name = 'memberTR']").delegate("span[name='seekName']","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	submitPhone (obj);
});

function submitPhone (obj) {
	var phoneNumber = jQuery(obj).parents("div[name='memberTR']").find("input[name='phoneNumber']").val();
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if (!reg.test(phoneNumber)) {
		dialog("号码格式有误~");
		return;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMemberByPhone",
		data : "phone="+phoneNumber,
		//async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var memberBaseDto = e.msg;
			if (jQuery(obj).parents("div[name='memberTR']").attr("selectType") == 1) {
				var parentsObj = jQuery(obj).parents(".card-main").next();
				parentsObj.find("span[name='memberNameSpan']").text(memberBaseDto.name);
				parentsObj.find("span[name='memberPhoneSpan']").text(memberBaseDto.phone);
				parentsObj.find("span[name='memberSexSpan']").text(memberBaseDto.sex);
				parentsObj.find("span[name='memberBalanceAmountSpan']").text(zeroValue(memberBaseDto.balanceAmount));
				parentsObj.find("input[name='memberId']").val(memberBaseDto.memberId).change();
				parentsObj.find("span[name='subAccountNum']").text(memberBaseDto.subAccountNum);
				parentsObj.find("span[name='needRefund']").text(zeroValue(memberBaseDto.debtAmount));
				var subAccountDtoList = memberBaseDto.subAccountDtoList;
				parentsObj.find("ul[name='subAccountUL']").empty();
				
				for (var i = 0; i < subAccountDtoList.length; i++) {
					var subAccountDto = subAccountDtoList[i];
					
					var str = "";
					if (i == 0) {
						str = "<li class='current' subAccountId = '"+subAccountDto.subAccountId+"'>";
					}
					else {
						str = "<li subAccountId = '"+subAccountDto.subAccountId+"'>";
					}
					var projectDiscount = subAccountDto.projectDiscount / 10;
					var goodsDiscount = subAccountDto.goodsDiscount / 10;
					str = str +  "<dl>"+
                                    "<dt>"+subAccountDto.levelName+"<span class='iconfont icon-xuanzhong'></span>"+
                                    "</dt>"+
                                    "<dd>余额：<span class='red' name = 'balance'>"+subAccountDto.balanceAmount+"</span></dd>"+
                                    "<dd>项目折扣：<span class='blue1'>"+projectDiscount+"折</span></dd>"+
                                    "<dd>商品折扣：<span class='blue1'>"+goodsDiscount+"折</span></dd>"+
                                "</dl>"+
	                           "</li>";
					parentsObj.find("ul[name='subAccountUL']").append(str);
				}
				
				jQuery(obj).parents(".card-main").addClass("hide");
				jQuery(obj).parents(".card-main").next().removeClass("hide");
				
			}
			else {
				jQuery(obj).parents("div[name='memberTR']").find(".can-click").text(memberBaseDto.name+"  "+memberBaseDto.sex+"  "+memberBaseDto.phone);
				jQuery(obj).parents("div[name='memberTR']").find("span[name='levelName']").text("会员等级："+memberBaseDto.levelName);
				jQuery(obj).parents("div[name='memberTR']").find("span[name='balance']").text("余额："+memberBaseDto.balanceAmount);
				jQuery(obj).parents("div[name='memberTR']").find("input[name='memberId']").val(memberBaseDto.memberId).change();
				jQuery(obj).parents("div[name='memberTR']").find("input[name='levelId']").val(memberBaseDto.levelId);
				
				jQuery(obj).parents("div[name='memberTR']").find("em[name='memberNameSpan']").text(memberBaseDto.name);
				jQuery(obj).parents("div[name='memberTR']").find("em[name='memberPhoneSpan']").text(memberBaseDto.phone);
				jQuery(obj).parents("div[name='memberTR']").find("em[name='memberSexSpan']").text(memberBaseDto.sex);
				jQuery(obj).parents("div[name='memberTR']").find("em[name='memberBalanceAmountSpan']").text(zeroValue(memberBaseDto.balanceAmount));
				jQuery(obj).parents("div[name='memberTR']").find("em[name='memberBalanceGiftmoneyAmountSpan']").text(zeroValue(memberBaseDto.giftmoneyAmount));
				jQuery(obj).parents("div[name='memberTR']").find("em[name='memberBalanceIntegralSpan']").text(zeroValue(memberBaseDto.balanceIntegral));
				
				jQuery("div[name='moreMemberInfoDIV']").find("span[name='totalConsumeAmountSpan']").text(zeroValue(memberBaseDto.totalConsumeAmount));
				jQuery("div[name='moreMemberInfoDIV']").find("span[name='lastDayNumberSpan']").text(memberBaseDto.lastDayNumber);
				jQuery("div[name='moreMemberInfoDIV']").find("span[name='projectNameSpan']").text(memberBaseDto.lastProjectName);
				
				var projectStepList = memberBaseDto.projectStepList;
				
				var str = "";
				
				for (var i = 0; i < projectStepList.length; i++) {
					var projectStep = projectStepList[i];
					if (i == 0) {
						str = "(" + projectStep.shiftMahjongName + " " + projectStep.employeeCode+ " " + projectStep.employeeName;
					}
					else {
						str = str + " " + projectStep.shiftMahjongName + " " + projectStep.employeeCode+ " " + projectStep.employeeName;
					}
					
					if (i + 1 == projectStepList.length) {
						str = str + ")";
					}
				}
				
				jQuery("div[name='moreMemberInfoDIV']").find("span[name='projectStepSpan']").text(str);
				
			    jQuery(obj).parents("div[name='memberTR']").find("div[name='resultTD']").css("display", "inline-block");
			    jQuery(obj).parents("div[name='memberTR']").find("div[name='seekTD']").css("display","none");
			    
			    jQuery(obj).parents(".tab-form1").find("span[name='needRefund']").text(zeroValue(memberBaseDto.debtAmount));
			}
			
		}
	});
}

//查询会员信息
jQuery("div[name = 'memberTR']").delegate(".can-click","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	showMemberModal(obj);
});

function showMemberModal (obj) {
    jQuery("#tabsLI-1").click();
	
	memberDataId = jQuery(obj).parents("div[name='memberTR']").find("input[name='memberId']").val();
	
	selectMemberInfo(memberDataId);
}

function selectMemberInfo(memberId) {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectByMemberDto",
		data : "memberId="+memberId,
		//async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var dto = e.msg;
			memberDataId = memberId;
			var memberDto = dto.memberDto;
			jQuery("span[name='memberBase']").text(memberDto.name +" "+memberDto.sex);
			//判断是否存在多个子账户
			var subAccountList = dto.subAccountList;
			if (subAccountList.length > 1) {
				var selectSub = document.createElement("select");
				selectSub.setAttribute("class", "chzn-select wp100");
				for (var i = 0; i < subAccountList.length; i++) {
					var subAccount = subAccountList[i];
					var option = document.createElement("option");
					option.innerHTML = subAccount.levelName + " (项目：" + subAccount.projectDiscount + "折, 商品：" + subAccount.goodsDiscount + "折, 余额：" + subAccount.balanceAmount + ")";
					selectSub.appendChild(option);
				}
				jQuery("#subAccountSpan").empty().append(selectSub);
				jQuery(selectSub).chosen({disable_search_threshold: 10});
			}
			
			jQuery("td[name='phone']").text(memberDto.phone);
			jQuery("td[name='totalConsumeAmount']").text(memberDto.memberAccount.totalConsumeAmount);
			jQuery("td[name='levelName']").text(memberDto.memberLevel.levelName);
			jQuery("td[name='totalAmount']").text(memberDto.memberAccount.totalAmount);
			jQuery("td[name='birthday']").text(memberDto.birthday);
			jQuery("td[name='totalIntegral']").text(memberDto.memberAccount.totalIntegral);
			jQuery("td[name='balanceAmount']").text(memberDto.memberAccount.balanceAmount);
			jQuery("td[name='avgConsumeAmount']").text(memberDto.memberAccount.avgConsumeAmount);
			jQuery("td[name='balanceIntegral']").text(memberDto.memberAccount.balanceIntegral);
			jQuery("td[name='avgConsumeDays']").text(memberDto.memberAccount.avgConsumeDays+"天/次");
			jQuery("td[name='storeName']").text(memberDto.storeInfo.storeName);
			jQuery("td[name='lastConsumeTime']").text(memberDto.memberAccount.lastConsumeTime);
			jQuery("td[name='balanceGiftmoneyAmount']").text(memberDto.memberAccount.balanceGiftmoneyAmount);
			jQuery("td[name='totalGiftmoneyAmount']").text(memberDto.memberAccount.totalGiftmoneyAmount);
			jQuery("td[name='debtAmount']").text(memberDto.memberAccount.debtAmount);
			jQuery("td[name='community']").text(memberDto.community);
			moneyFlow(dto.pageMoneyFlowDto);
			orderProject(dto.pageOrderProjectPageDto);
			orderGoods(dto.pageOrderGoodsPageDto);
			orderCombo(dto.pageOrderComboPageDto);
			integralFlow(dto.pageIntegralFlowDto);
			giftMoneyFlow(dto.pageGiftmoneyFlowDto);
			debtFlow(dto.pageDebtFlowDto);
			paginationDemo("pagination-demo1", dto.pageOrderProjectPageDto, 1);
			paginationDemo("pagination-demo2", dto.pageOrderComboPageDto, 2);
			paginationDemo("pagination-demo3", dto.pageOrderGoodsPageDto, 3);
			paginationDemo("pagination-demo4", dto.pageMoneyFlowDto, 4);
			paginationDemo("pagination-demo5", dto.pageIntegralFlowDto, 5);
			paginationDemo("pagination-demo6", dto.pageGiftmoneyFlowDto, 6);
			paginationDemo("pagination-demo7", dto.pageDebtFlowDto, 7);
		}
	});
}

function answerFunction(){
	jQuery("td[name='problem']").addClass("hide");
	jQuery("td[name='answer']").removeClass("hide");
}

function problemFunction(){
	jQuery("td[name='answer']").addClass("hide");
	jQuery("td[name='problem']").removeClass("hide");
}


function page(moneyFlowPageNo, moneyFlowPageSize, type){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMoneyFlow",
		data : "memberId="+memberDataId+"&pageNo="+moneyFlowPageNo+"&pageSize="+moneyFlowPageSize+"&type="+type,
		async:false,//使用同步的Ajax请求  
	    beforeSend : function(){
		    jQuery("#loadingWrap").css("display","block");
		  },
	    complete : function(){
		  setTimeout(function(){
			  jQuery("#loadingWrap").css("display","none");//请求完毕后将加载效果移除
		  },800);
	    },
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var page = e.msg;
			if (type == 4) {
				moneyFlow(page);
				initPageClickData("pagination-demo4",type,page.pageNo,page.pageSize,page.totalPage);
			}
			//套餐
	        else if (type == 2) {
	        	orderCombo(page);
	        	initPageClickData("pagination-demo2",type,page.pageNo,page.pageSize,page.totalPage);
	        }
	        //积分流水
	        else if (type == 5) {
	        	integralFlow(page);
	        	initPageClickData("pagination-demo5",type,page.pageNo,page.pageSize,page.totalPage);
	        }
			//礼金流水
	        else if (type == 6) {
	        	giftMoneyFlow(page);
	        	initPageClickData("pagination-demo6",type,page.pageNo,page.pageSize,page.totalPage);
	        }
			//欠款流水
	        else if (type == 7) {
	        	debtFlow(page);
	        	initPageClickData("pagination-demo7",type,page.pageNo,page.pageSize,page.totalPage);
	        }
	        //项目
	        else if (type == 1) {
	        	orderProject(page);
	        	initPageClickData("pagination-demo1",type,page.pageNo,page.pageSize,page.totalPage);
	        }
	        //商品
	        else if (type == 3) {
	        	orderGoods(page);
	        	initPageClickData("pagination-demo3",type,page.pageNo,page.pageSize,page.totalPage);
	        }
		}
	});
}

function moneyFlow(page) {
	memberDateTotalPage.moneyFlowTotalPage = page.totalPage;
	addDisable("previousMoneyFlow");
	if(memberDateTotalPage.moneyFlowTotalPage == 1){
		addDisable("nextMoneyFlow");
	}
	var results = page.results;
	jQuery("#moneyFlowTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var moneyFlowDto = results[i];
		var a = i + 1;
		jQuery("#moneyFlowTBODY").append("<tr class='single'>"+
				                            "<td>"+a+"</td>"+
			                                "<td>"+businessTypeArray[moneyFlowDto.businessType]+"</td>"+
			                               /* "<td class='can-click'>"+moneyFlowDto.orderName+"</td>"+*/
			                                /*"<td class='blue'>"+moneyFlowDto.balanceAmount+"</td>"+*/
			                                "<td class='blue'>"+flowTypeArray[moneyFlowDto.flowType] + moneyFlowDto.flowAmount+"</td>"+
			                                "<td>"+moneyFlowDto.flowTime+"</td>"+
			                                "<td>"+moneyFlowDto.lastOperatorName+"</td>"+
			                                "<td>"+moneyFlowDto.storeName+"</td>"+
			                            "</tr>");
	}
}

function orderProject(page) {
	memberDateTotalPage.orderProjectTotalPage = page.totalPage;
	addDisable("previousOrderProject");
	if(memberDateTotalPage.orderProjectTotalPage == 1){
		addDisable("nextOrderProject");
	}
	var results = page.results;
	jQuery("#orderProjectTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var orderProjectDto = results[i];
		var str = "<tr class='single'>"+
	        "<td>"+orderProjectDto.orderCode+"</td>"+
	        "<td>"+orderProjectDto.projectName+"</td>"+
	        "<td class='blue'>"+orderProjectDto.discountAmount+"</td><td>";
		if (orderProjectDto.privilegeMoney > 0) {
			str +=  offTypeArray[orderProjectDto.offType] + "<span class='red'> -" +  + orderProjectDto.privilegeMoney+"</span>";
		}
		str += "</td><td class='red'>"+orderProjectDto.realPrice+"</td>"+
	        "<td>"+orderProjectDto.createTime+"</td>"+
	        "<td>"+orderProjectDto.storeName+"</td>"+
	        "</tr>";
		jQuery("#orderProjectTBODY").append(str);
	}
}

function integralFlow(page) {
	memberDateTotalPage.integralFlowTotalPage = page.totalPage;
	addDisable("previousIntegralFlow");
	if(memberDateTotalPage.integralFlowTotalPage == 1){
		addDisable("nextIntegralFlow");
	}
	var results = page.results;
	jQuery("#integralFlowTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var integralFlow = results[i];
		jQuery("#integralFlowTBODY").append("<tr class='single'>"+
				                                  "<td>"+inOutTypeArray[integralFlow.flowType]+"</td>"+
				                                  "<td class='red'>"+flowTypeArray[integralFlow.flowType] + integralFlow.flowAmount +"</td>"+
				                                  "<td>"+integralFlow.businessDesc+"</td>"+
				                                  "<td>"+integralFlow.flowTime+"</td>"+
				                              "</tr>");
	}
}

function orderGoods(page) {
	memberDateTotalPage.orderGoodsTotalPage = page.totalPage;
	addDisable("previousOrderGoods");
	if(memberDateTotalPage.orderGoodsTotalPage == 1){
		addDisable("nextOrderGoods");
	}
	var results = page.results;
	jQuery("#orderGoodsTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var orderGoodsDto = results[i];
		
		var str = "<tr class='single'>"+
	        "<td>"+orderGoodsDto.orderCode+"</td>"+
	        "<td>"+orderGoodsDto.projectName+"</td>"+
	        "<td class='blue'>"+orderGoodsDto.discountAmount+"</td><td>";
		if (orderGoodsDto.privilegeMoney > 0) {
			str +=  offTypeArray[orderGoodsDto.offType] + "<span class='red'> -" +  + orderGoodsDto.privilegeMoney+"</span>";
		}
		str += "</td><td class='red'>"+orderGoodsDto.realPrice+"</td>"+
	        "<td>"+orderGoodsDto.createTime+"</td>"+
	        "<td>"+orderGoodsDto.lastOperatorName+"</td>"+
	        "<td>"+orderGoodsDto.storeName+"</td>"+
	        "</tr>";
		jQuery("#orderGoodsTBODY").append(str);
	}
}

function orderCombo(page) {
	memberDateTotalPage.orderComboTotalPage = page.totalPage;
	addDisable("previousOrderCombo");
	if(memberDateTotalPage.orderComboTotalPage == 1){
		addDisable("nextOrderCombo");
	}
	var results = page.results;
	jQuery("#orderComboTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var orderComboDto = results[i];
		jQuery("#orderComboTBODY").append("<tr class='single'>"+
				                              "<td class='cursor mr10 project-toggle'>"+orderComboDto.comboName+"<span class='iconfont icon-zhankai'></span></td>"+
//				                              "<td>"+orderComboDto.projectCount+"</td>"+
//				                              "<td class='red'>"+orderComboDto.remainingCount+"</td>"+
				                              "<td></td>"+
				                              "<td class='red'></td>"+
				                              "<td class='blue'>"+orderComboDto.projectAmount+"</td>"+
				                              "<td class='red'>"+orderComboDto.comboPrice+"</td>"+
				                              "<td>"+orderComboDto.lastOperatorName+"</td>"+
				                              "<td>"+orderComboDto.createTime+"</td>"+
				                              "<th>"+orderComboDto.overdueTime+"</th>"+
				                              "<td>"+orderComboDto.storeName+"</td>"+
				                          "</tr>");
		var projectList = orderComboDto.projectList;
		for (var j = 0; j < projectList.length; j++) {
			var memberComboProject = projectList[j];
			jQuery("#orderComboTBODY").append("<tr class='project-part hide'> style='display: table-row;'"+
				                                  "<td >"+memberComboProject.projectName+"</td>"+
				                                  "<td>项目次数："+memberComboProject.projectCount+"</td>"+
				                                  "<td>剩余次数：<span class='red'>"+memberComboProject.remainingCount+"</span></td>"+
				                                  "<td class='blue'>"+memberComboProject.projectPrice+"</td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                                  "<td></td>"+
				                              "</tr>");
		}
	}
}

function giftMoneyFlow(page) {
	memberDateTotalPage.giftMoneyFlowTotalPage = page.totalPage;
	addDisable("previousGiftMoneyFlow");
	if(memberDateTotalPage.giftMoneyFlowTotalPage <= 1){
		addDisable("nextGiftMoneyFlow");
	}
	var results = page.results;
	jQuery("#giftMoneyTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var giftMoneyFlow = results[i];
		jQuery("#giftMoneyTBODY").append("<tr class='single'>"+
				                                  "<td>"+inOutTypeArray[giftMoneyFlow.flowType]+"</td>"+
				                                  "<td class='red'>"+flowTypeArray[giftMoneyFlow.flowType] + giftMoneyFlow.flowAmount +"</td>"+
				                                  "<td>"+giftMoneyFlow.businessDesc+"</td>"+
				                                  "<td>"+giftMoneyFlow.flowTime+"</td>"+
				                              "</tr>");
	}
}

function debtFlow(page) {
	removeDisable("nextDebtFlow");
	memberDateTotalPage.debtFlowTotalPage = page.totalPage;
	addDisable("previousDebtFlow");
	if(memberDateTotalPage.debtFlowTotalPage <= 1){
		addDisable("nextDebtFlow");
	}
	var results = page.results;
	jQuery("#debtTBODY").empty();
	for (var i = 0; i < results.length; i++) {
		var debtFlow = results[i];
		jQuery("#debtTBODY").append("<tr class='single'>"+
				                                  "<td>"+debtTypeArray[debtFlow.flowType]+"</td>"+
				                                  "<td class='red'>"+flowTypeArray[debtFlow.flowType] + debtFlow.inAmount +"</td>"+
				                                  "<td>"+debtFlow.flowDesc+"</td>"+
				                                  "<td>"+debtFlow.flowDebtTime+"</td>"+
				                              "</tr>");
	}
}

//上一页
function previousPageButton(type){
	if (type == 4) {
		if(memberDatePageNo.moneyFlowPageNo == 1){
			return;
		}
		memberDatePageNo.moneyFlowPageNo = memberDatePageNo.moneyFlowPageNo - 1;
		page(memberDatePageNo.moneyFlowPageNo, memberDatePageSize.moneyFlowPageSize, 4);
		
		if(memberDatePageNo.moneyFlowPageNo == 1){
			addDisable("previousMoneyFlow");
		}
		else {
			removeDisable("nextMoneyFlow");
			removeDisable("previousMoneyFlow");
		}
	}
	//套餐
    else if (type == 2) {
    	if(memberDatePageNo.orderComboPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.orderComboPageNo = memberDatePageNo.orderComboPageNo - 1;
		page(memberDatePageNo.orderComboPageNo, memberDatePageSize.orderComboPageSize, 2);
		
		if(memberDatePageNo.orderComboPageNo == 1){
			addDisable("previousOrderCombo");
		}
		else {
			removeDisable("nextOrderCombo");
			removeDisable("previousOrderCombo");
		}
    }
    //积分流水
    else if (type == 5) {
    	if(memberDatePageNo.integralFlowPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.integralFlowPageNo = memberDatePageNo.integralFlowPageNo - 1;
		page(memberDatePageNo.integralFlowPageNo, memberDatePageSize.integralFlowPageSize, 5);
		
		if(memberDatePageNo.integralFlowPageNo == 1){
			addDisable("previousIntegralFlow");
		}
		else {
			removeDisable("nextIntegralFlow");
			removeDisable("previousIntegralFlow");
		}
    }
    //礼金流水
    else if (type == 6) {
    	if(memberDatePageNo.giftMoneyFlowPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.giftMoneyFlowPageNo = memberDatePageNo.giftMoneyFlowPageNo - 1;
		page(memberDatePageNo.giftMoneyFlowPageNo, memberDatePageSize.giftMoneyFlowPageSize, 6);
		
		if(memberDatePageNo.giftMoneyFlowPageNo == 1){
			addDisable("previousGiftMoneyFlow");
		}
		else {
			removeDisable("nextGiftMoneyFlow");
			removeDisable("previousGiftMoneyFlow");
		}
    }
	//欠款流水
    else if (type == 7) {
    	if(memberDatePageNo.debtFlowPageNo == 1){
			return;
		}
    	memberDatePageNo.debtFlowPageNo = memberDatePageNo.debtFlowPageNo - 1;
		page(memberDatePageNo.debtFlowPageNo, memberDatePageSize.debtFlowPageSize, 7);
		
		if(memberDatePageNo.debtFlowPageNo == 1){
			addDisable("previousDebtFlow");
		}
		else {
			removeDisable("nextDebtFlow");
			removeDisable("previousDebtFlow");
		}
    }
    //项目
    else if (type == 1) {
    	if(memberDatePageNo.orderProjectPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.orderProjectPageNo = memberDatePageNo.orderProjectPageNo - 1;
		page(memberDatePageNo.orderProjectPageNo, memberDatePageSize.orderProjectPageSize, 1);
		
		if(memberDatePageNo.orderProjectPageNo == 1){
			addDisable("previousOrderProject");
		}
		else {
			removeDisable("nextOrderProject");
			removeDisable("previousOrderProject");
		}
    }
    //商品
    else if (type == 3) {
    	if(memberDatePageNo.orderGoodsPageNo == 1){
			return;
		}
    	
    	memberDatePageNo.orderGoodsPageNo = memberDatePageNo.orderGoodsPageNo - 1;
		page(memberDatePageNo.orderGoodsPageNo, memberDatePageSize.orderGoodsPageSize, 3);
		
		if(memberDatePageNo.orderGoodsPageNo == 1){
			addDisable("previousOrderGoods");
		}
		else {
			removeDisable("nextOrderGoods");
			removeDisable("previousOrderGoods");
		}
    }
}
//下一页
function nextPageButton (type){
	if (type == 4) {
		if(memberDatePageNo.moneyFlowPageNo == memberDateTotalPage.moneyFlowTotalPage){
			return;
		}
		memberDatePageNo.moneyFlowPageNo = memberDatePageNo.moneyFlowPageNo + 1;
		page(memberDatePageNo.moneyFlowPageNo, memberDatePageSize.moneyFlowPageSize, 4);
		
		if(memberDatePageNo.moneyFlowPageNo == memberDateTotalPage.moneyFlowTotalPage){
			addDisable("nextMoneyFlow");
		}
		else {
			removeDisable("nextMoneyFlow");
		}
		removeDisable("previousMoneyFlow");
	}
	//套餐
    else if (type == 2) {
    	if(memberDatePageNo.orderComboPageNo == memberDateTotalPage.orderComboTotalPage){
			return;
		}
    	memberDatePageNo.orderComboPageNo = memberDatePageNo.orderComboPageNo + 1;
		page(memberDatePageNo.orderComboPageNo, memberDatePageSize.orderComboPageSize, 2);
		
		if(memberDatePageNo.orderComboPageNo == memberDateTotalPage.orderComboTotalPage){
			addDisable("nextOrderCombo");
		}
		else {
			removeDisable("nextOrderCombo");
		}
		removeDisable("previousOrderCombo");
    }
    //积分流水
    else if (type == 5) {
    	if(memberDatePageNo.integralFlowPageNo == memberDateTotalPage.integralFlowTotalPage){
			return;
		}
    	memberDatePageNo.integralFlowPageNo = memberDatePageNo.integralFlowPageNo + 1;
		page(memberDatePageNo.integralFlowPageNo, memberDatePageSize.integralFlowPageSize, 5);
		
		if(memberDatePageNo.integralFlowPageNo == memberDateTotalPage.integralFlowTotalPage){
			addDisable("nextIntegralFlow");
		}
		else {
			removeDisable("nextIntegralFlow");
		}
		removeDisable("previousIntegralFlow");
    }
	//礼金流水
    else if (type == 6) {
    	if(memberDatePageNo.giftMoneyFlowPageNo >= memberDateTotalPage.giftMoneyFlowTotalPage){
			return;
		}
    	memberDatePageNo.giftMoneyFlowPageNo = memberDatePageNo.giftMoneyFlowPageNo + 1;
		page(memberDatePageNo.giftMoneyFlowPageNo, memberDatePageSize.giftMoneyFlowPageSize, 6);
		
		if(memberDatePageNo.giftMoneyFlowPageNo == memberDateTotalPage.giftMoneyFlowTotalPage){
			addDisable("nextGiftMoneyFlow");
		}
		else {
			removeDisable("nextGiftMoneyFlow");
		}
		removeDisable("previousGiftMoneyFlow");
    }
	//挂账
    else if (type == 7) {
    	if(memberDatePageNo.debtFlowPageNo >= memberDateTotalPage.debtFlowTotalPage){
			return;
		}
    	memberDatePageNo.debtFlowPageNo = memberDatePageNo.debtFlowPageNo + 1;
		page(memberDatePageNo.debtFlowPageNo, memberDatePageSize.debtFlowPageSize, 7);
		
		if(memberDatePageNo.debtFlowPageNo == memberDateTotalPage.debtFlowTotalPage){
			addDisable("nextDebtFlow");
		}
		else {
			removeDisable("nextDebtFlow");
		}
		removeDisable("previousDebtFlow");
    }
    //项目
    else if (type == 1) {
    	if(memberDatePageNo.orderProjectPageNo == memberDateTotalPage.orderProjectTotalPage){
			return;
		}
    	memberDatePageNo.orderProjectPageNo = memberDatePageNo.orderProjectPageNo + 1;
		page(memberDatePageNo.orderProjectPageNo, memberDatePageSize.orderProjectPageSize, 1);
		
		if(memberDatePageNo.orderProjectPageNo == memberDateTotalPage.orderProjectTotalPage){
			addDisable("nextOrderProject");
		}
		else {
			removeDisable("nextOrderProject");
		}
		removeDisable("previousOrderProject");
    }
    //商品
    else if (type == 3) {
    	if(memberDatePageNo.orderGoodsPageNo == memberDateTotalPage.orderGoodsTotalPage){
			return;
		}
    	memberDatePageNo.orderGoodsPageNo = pageNo.orderGoodsPageNo + 1;
		page(memberDatePageNo.orderGoodsPageNo, memberDatePageSize.orderGoodsPageSize, 3);
		
		if(memberDatePageNo.orderGoodsPageNo == memberDateTotalPage.orderGoodsTotalPage){
			addDisable("nextOrderGoods");
		}
		else {
			removeDisable("nextOrderGoods");
		}
		removeDisable("previousOrderGoods");
    }
}

function addDisable(name) {
	jQuery("#" + name).addClass("page-disable");
}

function removeDisable(name) {
	jQuery("#" + name).removeClass("page-disable");
}
//实例化分页插件
function paginationDemo(pageId,page,type){
	
	jQuery('#'+pageId).empty();
	jQuery('#'+pageId).removeData("twbs-pagination");
	jQuery('#'+pageId).unbind("page");
	
	var pageNo = page.pageNo;
	var pageSize = page.pageSize;
	var totalPage = page.totalPage;
	if (totalPage == 0){
		jQuery('#'+pageId).prev().text('共找到了 0 条数据, 共 0  页');
		return;
	}
	jQuery('#'+pageId).twbsPagination({
		totalPages : totalPage,
		visiblePages : 5,
	});
	jQuery('#'+pageId).prev().text('共找到了 '+page.totalRecord+' 条数据, 共 '+totalPage+' 页');
	initPageClickData(pageId,type,pageNo,pageSize,totalPage);
}
//为分页按钮绑定事件,但是每次都要在page方法后重新调用,因为该插件会使得按钮事件消失
function initPageClickData(pageId,type,pageNo,pageSize,totalPage) {
	
	jQuery('#'+pageId).find(".first").on("click", function() {
		page(1, pageSize, type);
	});
	jQuery('#'+pageId).find(".last").on("click", function() {
		page(totalPage, pageSize, type);
	});
	jQuery('#'+pageId).find(".next").on("click", function() {
		if (pageNo >= totalPage) {
			return;
		}
		pageNo++;
		page(pageNo, pageSize, type);
	});
	jQuery('#'+pageId).find(".prev").on("click", function() {
		if (pageNo <= 1) {
			return;
		}
		pageNo--;
		page(pageNo, pageSize, type);
	});
	jQuery('#'+pageId).find(".page").on("click", function(){
		pageNum = jQuery(this).children("a").text();
		page(pageNum, pageSize, type);
	});
}

function zeroValue (value) {
	if (isEmpty(value)) {
		value = "0";
	}
	return value;
}