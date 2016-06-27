
//轮播
jQuery(function(){
	
	jQuery("div[name='memberTR']").find(".hand_table").hide();
	jQuery("div[name='memberTR']").find(".hand_table_").show();
	
	     var now_=0, count=jQuery('.hair_series li').size();
		 
	  //向右走
      jQuery('.hand_right').click(function(){
         if(now_<=count-4){
		    now_+=1;
	        jQuery(this).parent().find('.hair_series ul').stop(true,true).animate({
		       left:-95*now_
		   
		       }) 
			  }
		  });
	  //向左走
	  
	  	//向左走
	 jQuery('.hand_left').click(function(){
         if(now_>=1){
		    now_-=1;
	         jQuery(this).parent().find('.hair_series ul').stop(true,true).animate({
		     left:-95*now_
		   
		     }) 
		  }	
        		
	  });
 });

//初始化时间控件
var now = new Date() ;
     
var nowYear = now.getFullYear() ; //年
var nowMonth = now.getMonth()+1<10?"0"+(now.getMonth()+1):now.getMonth() ; //月
var nowDay = now.getDate()<10?"0"+now.getDate():now.getDate() ; //日期
var nowHour = now.getHours()<10?"0"+now.getHours():now.getHours() ; //时
var nowMinute = now.getMinutes()<10?"0"+now.getMinutes():now.getMinutes() ; //分
var nowSeconds = now.getSeconds()<10?"0"+now.getSeconds():now.getSeconds() ; //秒

var nowDate = nowYear+"-"+nowMonth+"-"+nowDay+" "+nowHour+":"+nowMinute+":"+nowSeconds;
jQuery("input[name='openOrderDate']").val(nowDate) ;

function changeDept(obj) {
	var deptId = jQuery(obj).val();
	jQuery("div[chooseDept='chooseDept']").addClass("hide");
	jQuery("div[name='"+deptId+"']").removeClass("hide");
}


function changeType(obj, name) {
	var parent = jQuery(obj).parents("div[chooseDept='chooseDept']");
	
	jQuery(obj).siblings().removeClass("active");
	jQuery(obj).addClass("active");
	
	parent.find(".hair_series").addClass("hide");
	parent.find("div[name= '"+name+"UL']").removeClass("hide");
	
	parent.find(".hair_content").addClass("hide");
	
	if (name == 'combo') {
		parent.find("div[name= '"+name+"']").removeClass("hide");
	}
	else {
		var categoryid = parent.find("div[name= '"+name+"UL']").find(".active").attr("categoryid");
		
		parent.find("div[name='"+name+"'][categoryid='"+categoryid+"']").removeClass("hide");
	}
}

function againSearch(obj) {
	jQuery(obj).parents("[name='memberTR']").find("input[name='memberId']").val("");
	jQuery(obj).parents("[name='memberTR']").prev().removeClass("hide");
	jQuery(obj).parents("[name='memberTR']").addClass("hide");
}

function changeCategory(obj, categoryid, name) {
	jQuery(obj).siblings().removeClass("active");
	jQuery(obj).addClass("active");
	var parent = jQuery(obj).parents("div[chooseDept='chooseDept']");
	parent.find(".hair_content").addClass("hide");
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
				var projectDiv = jQuery("<div class='nav_content_div' name= 'projectNameLI' projectId = '"+date.projectId+"'></div>");
				projectDiv.append("<span class='hand_close' onclick = 'deleteProject(this)'><img src='"+baseUrl+"images/hand_close.png'></span>"+
						     	  "<p><em>"+date.projectName+"</em><i>项目价格："+date.projectPrice+"</i></p>");
				
				var  stepTable = jQuery("<table></table>");
				
				var shiftStepDtoList = date.shiftStepDtoList;
				for (var i = 0; i < shiftStepDtoList.length; i++) {
					
					var buzhou = jQuery("<tr projectStepId = '"+shiftStepDtoList[i].projectStepId+"'></tr>");
					buzhou.append("<td>"+shiftStepDtoList[i].projectStepName+"</td>"+
		                          "<td>"+shiftStepDtoList[i].shiftMahjongName+"</td>");
					
					var buzhouTD = jQuery("<td style='width:140px'>选择员工:</td>");
					
					var select = jQuery("<select name='employeeId' class='chzn-select mr5 input-medium'></select>")
					select.append("<option value=''>请选择</option>");
					var shiftMahjongEmployeeList = shiftStepDtoList[i].shiftMahjongEmployeeList;
					for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
						select.append("<option value='"+shiftMahjongEmployeeList[j].employeesId+"'><span class='gp'>"+shiftMahjongEmployeeList[j].employeeCode+"</span> <span class='name'>"+shiftMahjongEmployeeList[j].name+"</span></option>");
					}
					
					buzhouTD.append(select);
					buzhou.append(buzhouTD);
					
					buzhou.append("<td>指定:<input type='checkbox' name = 'isAssign'></td>"+
	                              "<td>预约:<input type='checkbox' name='isAppoint'></td>");
					stepTable.append(buzhou);
				}
				projectDiv.append(stepTable);
				jQuery("div[name='projectPay']").append(projectDiv);
				jQuery("select[name='employeeId']").chosen({disable_search_threshold: 3});
				
				changeDiv(1);
				/*jQuery('.lcs_check').lc_switch('是', '否');*/
			}
		});
	}
	else {
		var str = "<div class='nav_content_div_1' goodsId = '"+projectId+"'>"+
		             "<span class='hand_close' onclick = 'deleteComboGoods(this)'><img src='"+baseUrl+"images/hand_close.png'></span>"+
                     "<em>"+projectName+"</em>"+
                     "<i>价格：￥"+projectPrice+"</i>"+
                     "<span>提成员工:"+
                     "<select name='employeeId' id='' class='chzn-select mr5 input-medium'>"+
                        "<option value=''>请选择人员</option>";
		for (var i = 0; i < employeeInfoList.length; i++) {
			str += "<option value='"+employeeInfoList[i].employeeId+"'><span class='gp'>"+employeeInfoList[i].employeeCode+"</span> <span class='name'>"+employeeInfoList[i].name+"</span></option>";
		}
		str += "</select></span>"+
	           "</div>";
		
		
		if (type == 2) {
			jQuery("div[name='goodsNameLI']").append(str);
			jQuery("div[name='goodsNameLI']").find("select").chosen();
			changeDiv(2);
		}
		else {
			jQuery("div[name='comboNameLI']").append(str);
			jQuery("div[name='comboNameLI']").find("select").chosen();
			changeDiv(3);
		}
	}
}

function changeDiv(type) {
	if (type == 1) {
		jQuery("div[name='goodsNameLI']").addClass("hide");
		jQuery("div[name='comboNameLI']").addClass("hide");
		jQuery("div[name='projectPay']").removeClass("hide");
		
		jQuery("li[name='projectDetail']").siblings().removeClass("active");
		jQuery("li[name='projectDetail']").addClass("active");
	}
	else if (type == 2) {
		jQuery("div[name='projectPay']").addClass("hide");
		jQuery("div[name='comboNameLI']").addClass("hide");
		jQuery("div[name='goodsNameLI']").removeClass("hide");
		
		jQuery("li[name='goodsDetail']").siblings().removeClass("active");
		jQuery("li[name='goodsDetail']").addClass("active");
	}
	else {
		jQuery("div[name='projectPay']").addClass("hide");
		jQuery("div[name='goodsNameLI']").addClass("hide");
		jQuery("div[name='comboNameLI']").removeClass("hide");
		
		jQuery("li[name='comboDetail']").siblings().removeClass("active");
		jQuery("li[name='comboDetail']").addClass("active");
	}
}

function deleteProject(obj) {
	jQuery(obj).parents(".nav_content_div").remove();
}

function deleteComboGoods(obj) {
	jQuery(obj).parents(".nav_content_div_1").remove();
}

function changeIsAppoint(obj) {
	jQuery(obj).parents(".xiaofei-item").find("input[name='isAppoint']").attr("checked",false);
	jQuery(obj).attr("checked",true);
}

function save() {
	var openOrderDate = jQuery("input[name='openOrderDate']").val();
	var handOrderCode = jQuery("input[name='handOrderCode']").val();
	var memberId = jQuery("div[name='memberTR']").find("input[name = 'memberId']").val();
	var sex = jQuery("input:radio[name='sex']:checked").val();
	
	var arrayObj = new Array();
	//项目
	var projectObj = jQuery("div[name='projectNameLI']");
	for (var i = 0; i < projectObj.length; i++) {
		var projectId = jQuery(projectObj[i]).attr("projectId");
		var projectStepArrayObj = new Array();
		var projectStepObj = jQuery(projectObj[i]).find("tr");
		
		var appoint = 0;
		
		for (var j = 0; j < projectStepObj.length; j++) {
			var projectStepId = jQuery(projectStepObj[j]).attr("projectStepId");
			var employeeId = jQuery(projectStepObj[j]).find("select[name='employeeId']").val();
			var isAssign = 0;
			if (jQuery(projectStepObj[j]).find("input[name='isAssign']").prop('checked')) {
				isAssign = 1;
			}
			var isAppoint = 0;
			if (jQuery(projectStepObj[j]).find("input[name='isAppoint']").prop('checked')) {
				isAppoint = 1;
			}

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
	var comboObj = jQuery("div[name='comboNameLI']").find(".nav_content_div_1");
	for (var i = 0; i < comboObj.length; i++) {
		var comboId = jQuery(comboObj[i]).attr("comboId");
        var employeeId = jQuery(comboObj[i]).find("select[name='employeeId']").val();
		var projectObjStr = {"type":3, "projectId":comboId, "projectStepArrayObjStr":employeeId};
		arrayObj.push(projectObjStr);
	}
	//商品
	var goodsObj = jQuery("div[name='goodsNameLI']").find(".nav_content_div_1");
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
    	data : "memberId=" + memberId + "&sex=" + sex + "&arrayObjStr=" + arrayObjStr + "&openOrderDate="+ openOrderDate +"&handOrderCode=" + handOrderCode,
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
		jQuery("div[name='projectPay']").empty();
		jQuery("div[name='goodsNameLI']").empty();
		jQuery("div[name='comboNameLI']").empty();
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
		jQuery("div[name='seekTD']").css("display", "inline-block");
		jQuery("div[name='resultTD']").css("display", "none");
		jQuery("div[name='sexDIV']").css("display", "inline-block");
		jQuery(".fanhui").addClass("hide");
	}
	else {
		jQuery("div[name='seekTD']").css("display", "none");
		jQuery("div[name='sexDIV']").css("display", "none");
		jQuery("div[name='resultTD']").css("display", "inline-block");
		jQuery(".fanhui").removeClass("hide");
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