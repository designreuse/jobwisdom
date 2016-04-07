jQuery(function () {
    jQuery(".bm").on("click", function () {
        var _self = jQuery(this);
        _self.parent().find(".bm").removeClass("active");
        _self.addClass("active");
    })
    jQuery('.lcs_check').lc_switch('是', '否');
})

function changeDept(deptId) {
	jQuery(".select-target").addClass("hide");
	jQuery("div[name='"+deptId+"']").removeClass("hide");
}


function changeType(obj, name) {
	var parent = jQuery(obj).parents(".select-target");
	parent.find(".select-item").removeClass("active");
	jQuery(obj).addClass("active");
	
	parent.find(".selected-child-select").addClass("hide");
	parent.find("div[name= '"+name+"UL']").removeClass("hide");
	
	parent.find(".all-kind-wrap").addClass("hide");
	
	if (name == 'combo') {
		parent.find("div[name= '"+name+"']").removeClass("hide");
	}
	else {
		var categoryid = parent.find("div[name= '"+name+"UL']").find(".active").attr("categoryid");
		
		parent.find("div[name='"+name+"'][categoryid='"+categoryid+"']").removeClass("hide");
	}
}

function changeCategory(obj, categoryid, name) {
	jQuery(obj).parent().find(".selected-item").removeClass("active");
	jQuery(obj).addClass("active");
	var parent = jQuery(obj).parents(".select-target");
	parent.find(".all-kind-wrap").addClass("hide");
	parent.find("div[name='"+name+"'][categoryid='"+categoryid+"']").removeClass("hide");
}

jQuery('body').delegate('.lcs_check', 'lcs-statuschange', function() {
    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
    if(status == 'checked'){
    	jQuery(this).val(1);
    }else{
    	jQuery(this).val(0);
    }
});

function chooceProject(projectId, projectName, projectPrice, type) {
	if (type == 1) {
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/selectProjectShiftStep",
			data : "projectId="+projectId,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				var date = e.msg;
				var xiaofei = jQuery("<li class='xiaofei-item' name= 'projectNameLI' projectId = '"+date.projectId+"'></li>");
				xiaofei.append("<div class='xiaofei-name'>"+
				                    "<span class='name mr20' style='display: inline-block; width:160px;'>"+date.projectName+"</span>"+
				                    "<span class='origin-price'>项目价格:</span>"+
				                    "<span class='item-price'>￥"+date.projectPrice+"</span>"+
				                    "<span class='fr iconfa-trash project-icon' onclick = 'deleteProject(this)'></span>"+
				                "</div>");
				
				var shiftStepDtoList = date.shiftStepDtoList;
				for (var i = 0; i < shiftStepDtoList.length; i++) {
					var buzhou = jQuery("<div class='buzhou' projectStepId = '"+shiftStepDtoList[i].projectStepId+"'></div>");
					buzhou.append("<span class='ml50' style='display: inline-block; width:140px;'>"+shiftStepDtoList[i].projectStepName+"</span>"+
		                          "<span style='display: inline-block; width:200px;'>"+shiftStepDtoList[i].shiftMahjongName+"</span>"+
		                          "<span >选择员工:</span>");
					var select = jQuery("<select name='employeeId' id='' class='chzn-select mr5 input-medium' ></select>")
					select.append("<option value=''>请选择</option>");
					var shiftMahjongEmployeeList = shiftStepDtoList[i].shiftMahjongEmployeeList;
					for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
						select.append("<option value='"+shiftMahjongEmployeeList[j].employeesId+"'><span class='gp'>"+shiftMahjongEmployeeList[j].employeeCode+"</span> <span class='name'>"+shiftMahjongEmployeeList[j].name+"</span></option>");
					}
					buzhou.append(select);
					buzhou.append("<span class='ml50'>是否指定:</span>"+
	                              "<input type='checkbox' class='mr5 lcs_check' name = 'isAssign' value='0'/>"+
	                              "<span class='ml50'>是否预约:</span>"+
	                              "<input type='checkbox' class='mr5 lcs_check isDisableApp' name = 'isAppoint' value='0' onchange = 'changeIsAppoint(this)'/>");
					xiaofei.append(buzhou);
				}
				jQuery("li[name='goodsNameLI']").before(xiaofei);
				jQuery("#showUL").find("select").chosen();
				jQuery('.lcs_check').lc_switch('是', '否');
			}
		});
	}
	else if (type == 2) {
		var str = "<div class='buzhou' goodsId = '"+projectId+"'>"+
                     "<span class='ml50' style='display: inline-block; width:200px;'>"+projectName+"</span>"+
                     "<span style='display: inline-block; width:90px;'>￥"+projectPrice+"</span>"+
                     "<span class='ml50'>提成员工:</span>"+
                     "<select name='employeeId' id='' class='chzn-select mr5 input-medium'>"+
                        "<option value=''>请选择人员</option>";
		for (var i = 0; i < employeeInfoList.length; i++) {
			str += "<option value='"+employeeInfoList[i].employeeId+"'><span class='gp'>"+employeeInfoList[i].employeeCode+"</span> <span class='name'>"+employeeInfoList[i].name+"</span></option>";
		}
		str += "</select>"+
	           "<span class='fr iconfa-trash project-icon' style='margin-right:20px;' onclick = 'deleteComboGoods(this)'></span>"+
	           "</div>";
		
		jQuery("li[name='goodsNameLI']").append(str);
		jQuery("#showUL").find("select").chosen();
	}
	else {
		var str = "<div class='buzhou' comboId = '"+projectId+"'>"+
        "<span class='ml50' style='display: inline-block; width:200px;'>"+projectName+"</span>"+
        "<span style='display: inline-block; width:90px;'>￥"+projectPrice+"</span>"+
        "<span class='ml50'>提成员工:</span>"+
        "<select name='employeeId' id='' class='chzn-select mr5 input-medium'>"+
           "<option value=''>请选择人员</option>";
		for (var i = 0; i < employeeInfoList.length; i++) {
		str += "<option value='"+employeeInfoList[i].employeeId+"'><span class='gp'>"+employeeInfoList[i].employeeCode+"</span> <span class='name'>"+employeeInfoList[i].name+"</span></option>";
		}
		str += "</select>"+
		  "<span class='fr iconfa-trash project-icon' style='margin-right:20px;' onclick = 'deleteComboGoods(this)'></span>"+
		  "</div>";
		
		jQuery("li[name='comboNameLI']").append(str);
		jQuery("#showUL").find("select").chosen();
	}
}

function deleteProject(obj) {
	jQuery(obj).parents(".xiaofei-item").remove();
}

function deleteComboGoods(obj) {
	jQuery(obj).parents(".buzhou").remove();
}

function changeIsAppoint(obj) {
	jQuery(obj).parents(".xiaofei-item").find("input[name='isAppoint']").attr("checked",false);
	jQuery(obj).attr("checked",true);
}

var openType = 1;

function save(type) {
	var memberId = jQuery("div[name='memberTR']").find("input[name = 'memberId']").val();
	if (type == 2) {
		jQuery("#saveModel").modal("show");
	}
	else {
		queren();
	}
	openType = type;
}

function queren() {
	var openOrderDate = jQuery("input[name='openOrderDate']").val();
	
	if (openType == 1) {
		openOrderDate = "";
	}
	else {
		if (openOrderDate.length == 0) {
			dialog("补单时间不能为空！");
			return;
		}
		
		if (isStartEndDate(openOrderDate)) {
			dialog("补单时间不能大于等于当前时间！");
			return;
		}
	}
	
	jQuery("#saveModel").addClass("hide");
	var memberId = jQuery("div[name='memberTR']").find("input[name = 'memberId']").val();
	var sex = jQuery("input:radio[name='sex']:checked").val();
	
	var arrayObj = new Array();
	//项目
	var projectObj = jQuery("li[name='projectNameLI']");
	for (var i = 0; i < projectObj.length; i++) {
		var projectId = jQuery(projectObj[i]).attr("projectId");
		var projectStepArrayObj = new Array();
		var projectStepObj = jQuery(projectObj[i]).find(".buzhou");
		
		var appoint = 0;
		
		for (var j = 0; j < projectStepObj.length; j++) {
			var projectStepId = jQuery(projectStepObj[j]).attr("projectStepId");
			var employeeId = jQuery(projectStepObj[j]).find("select[name='employeeId']").val();
			var isAssign = jQuery(projectStepObj[j]).find("input[name='isAssign']").val();

			var isAppoint = jQuery(projectStepObj[j]).find("input[name='isAppoint']").val();
            if (isAppoint == 1) {
            	appoint = isAppoint;
			}
			var StepStr = {"projectStepId":projectStepId, "employeeId":employeeId, "isAssign":isAssign, "isAppoint":isAppoint};
			projectStepArrayObj.push(StepStr);
		}
		var projectStepArrayObjStr = JSON.stringify(projectStepArrayObj);
		var projectObjStr = {"type":1, "projectId":projectId, "appoint" : appoint, "projectStepArrayObjStr":projectStepArrayObjStr};
		arrayObj.push(projectObjStr);
	}
	//套餐
	var comboObj = jQuery("li[name='comboNameLI']").find(".buzhou");
	for (var i = 0; i < comboObj.length; i++) {
		var comboId = jQuery(comboObj[i]).attr("comboId");
        var employeeId = jQuery(comboObj[i]).find("select[name='employeeId']").val();
		var projectObjStr = {"type":3, "projectId":comboId, "projectStepArrayObjStr":employeeId};
		arrayObj.push(projectObjStr);
	}
	//商品
	var goodsObj = jQuery("li[name='goodsNameLI']").find(".buzhou");
	for (var i = 0; i < goodsObj.length; i++) {
		var goodsId = jQuery(goodsObj[i]).attr("goodsId");
        var employeeId = jQuery(goodsObj[i]).find("select[name='employeeId']").val();
		var projectObjStr = {"type":2, "projectId":goodsId, "projectStepArrayObjStr":employeeId};
		arrayObj.push(projectObjStr);
	}
	if (arrayObj.length == 0) {
		dialog("请添加项目、商品或套餐！");
		return;
	}
	var arrayObjStr = JSON.stringify(arrayObj);
	jQuery.ajax({
    	url : baseUrl + "KeepAccounts/manuallyOpenOrderSave",
    	type : "POST",
    	data : "memberId=" + memberId + "&sex=" + sex + "&arrayObjStr=" + arrayObjStr + "&openOrderDate="+ openOrderDate,
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		jQuery("a[name='memberModelChoose']").removeClass("hide");
    		if (isEmpty(memberId)) {
    			jQuery("a[name='memberModelChoose']").addClass("hide");
    		}
    		jQuery("#memberSkipModal").modal("show");
    	}
    });
}

function chooseSkipType(skipType) {
	if (skipType == 1) {
		jQuery("#showUL").empty();
		jQuery("#showUL").append("<li class='xiaofei-item' name= 'goodsNameLI'>"+
			                        "<div class='xiaofei-name'>"+
			                            "<span class='name mr20'>商品销售</span>"+
			                        "</div>"+
			                     "</li>"+
			                     "<li class='xiaofei-item' name= 'comboNameLI'>"+
			                        "<div class='xiaofei-name'>"+
			                            "<span class='name mr20'>套餐销售</span>"+
			                        "</div>"+
			                     "</li>");
		
	}
	else if (skipType == 2) {
		var phoneNum = jQuery("input[name='phoneNumber']").val();
		window.location.href = baseUrl + "KeepAccounts/initializeOpenCard?phoneNum=" + phoneNum + "&clickType=1";
	}
	else if (skipType == 3) {
		var phoneNum = jQuery("input[name='phoneNumber']").val();
		window.location.href = baseUrl + "KeepAccounts/initializeOpenCard?phoneNum=" + phoneNum + "&clickType=2";
	}
	else {
		window.location.href = baseUrl + "selfcashier/view/list";
	}
	jQuery("#memberSkipModal").modal("hide");
}

//切换会员
function changeMember(obj){
	if (jQuery(obj).val() == "") {
		jQuery("div[name='sexDIV']").css("display", "inline-block");
		jQuery("div[name='sexDIV']").parent().find("div[name='memberTR']").addClass("p-part-first");
		jQuery(".fanhui").addClass("hide");
		jQuery("div[name='moreMemberInfoDIV']").addClass("hide");
	}
	else {
		jQuery("div[name='sexDIV']").css("display", "none");
		jQuery("div[name='sexDIV']").parent().find("div[name='memberTR']").removeClass("p-part-first");
		jQuery(".fanhui").removeClass("hide");
		jQuery("div[name='moreMemberInfoDIV']").removeClass("hide");
	}
}

jQuery(".more-toolbar").delegate(".fanhui","click", function(event){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	jQuery(obj).parent().find("div[name='memberTR'] input[name='memberId']").val("").change();
	jQuery(obj).parent().find("div[name='memberTR'] div[name='resultTD']").css("display","none");
    jQuery(obj).parent().find("div[name='memberTR'] div[name='seekTD']").css("display", "inline-block");
});

/**
 * 预约步骤,控制
 */
jQuery('#showUL').delegate('.isDisableApp', 'lcs-on', function() {
	jQuery(this).parents(".xiaofei-item").find("input[name='isAppoint'].isDisableApp[value='1']").lcs_off();
});

function cancelModel() {
	jQuery("#saveModel").modal("hide");
} 

function isStartEndDate(startDate){   
	var myDate = new Date();
	function appendZero(s){return ("00"+ s).substr((s+"").length);}  //补0函数
	
    if(startDate.length > 0){   
     var startDateTemp = startDate.split(" ");   
     var arrStartDate = startDateTemp[0].split("-");   

     var allStartDate = new Date(arrStartDate[0],arrStartDate[1],arrStartDate[2]);   
     var allEndDate = new Date(myDate.getFullYear(), appendZero(myDate.getMonth() + 1), appendZero(myDate.getDate()));   
     if(allStartDate.getTime() >= allEndDate.getTime()){   
        return true;   
     }   
    }
    
    return false;   
   }   