
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
	 
	 if (!isEmpty(memberBaseDtoStr)) {
		 var memberBaseDto = eval("(" + memberBaseDtoStr + ")");
		 var obj = jQuery("input[name='phoneNumber']");
		 var parentsObj = jQuery(obj).parents("div[name='memberTR']").next();
		 parentsObj.find("[name='memberImg']").attr("src", qiniuUrl + memberBaseDto.headUrl)
		 parentsObj.find("[name='memberNameSpan']").text(memberBaseDto.name);
		 parentsObj.find("[name='memberPhoneSpan']").text(memberBaseDto.phone);
		 parentsObj.find("[name='memberSexSpan']").text(memberBaseDto.sex);
		 parentsObj.find("[name='memberBalanceAmountSpan']").text(zeroValue(memberBaseDto.balanceAmount));
		 parentsObj.find("[name='memberBalanceGiftmoneyAmountSpan']").text(zeroValue(memberBaseDto.giftmoneyAmount));
		 parentsObj.find("[name='memberBalanceIntegralSpan']").text(zeroValue(memberBaseDto.balanceIntegral));
		 parentsObj.find("[name='memberStoreName']").text(memberBaseDto.storeName);
		 parentsObj.find("[name='memberId']").val(memberBaseDto.memberId).change();
		 parentsObj.find("[name='subAccountNum']").text(memberBaseDto.subAccountNum);
		 parentsObj.find("[name='needRefund']").text(zeroValue(memberBaseDto.debtAmount));
		
		 parentsObj.removeClass("hide");
		 jQuery(obj).parents("div[name='memberTR']").addClass("hide");
	 }
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
		var projectDiv = jQuery("<div class='nav_content_div' name= 'projectNameLI' projectId = '"+projectId+"'></div>");
		projectDiv.append("<span class='hand_close' onclick = 'deleteProject(this)'><img src='"+baseUrl+"images/hand_close.png'></span>"+
				     	  "<p><em>"+projectName+"</em><i>项目价格："+projectPrice+"</i><span class='select_order' onclick = 'chooseAppoint(this, 1)' appointmentId = ''>选择预约人员+</span></p>");
		
		var  stepTable = jQuery("<table></table>");
		
		for (var i = 0; i < positionInfos.length; i++) {
			var positionInfo = positionInfos[i];
			var buzhou = jQuery("<tr positionId = '"+positionInfo.positionId+"'></tr>");
			buzhou.append("<td  style='width:210px'>"+positionInfo.positionName+"</td>");
			
			if (i == 0) {
				buzhou.append("<td style='width:360px'><input type='text' name = 'employeeId' employeeId = '' chooseType = '1'></td>");
			}
			else {
				buzhou.append("<td style='width:360px'><input type='text' name = 'employeeId' employeeId = '' chooseType = '2'></td>");
			}
			buzhou.append("<td>指定:<input type='checkbox' name = 'isAssign' chooseType = '0'></td>");
			
			if (i == 0) {
				buzhou.append("<td>预约:<input type='checkbox' name = 'isAppoint' chooseType = '0'></td>");
			}
			stepTable.append(buzhou);
		}
		projectDiv.append(stepTable);
		jQuery("div[name='projectPay']").append(projectDiv);
		changeDiv(1);
	}
	else {
		var str = "<p><span class='hand_close' onclick = 'deleteComboGoods(this)'><img src='"+baseUrl+"images/hand_close.png'></span>"+
                  "<em>"+projectName+"</em>"+
                  "<i>价格：￥"+projectPrice+"</i></p>"+
                  "<table class='select_people'>"+
				      "<tr>"+
						 "<td style='width:360px'>销售第一人"+
						    "<input type='text' name = 'employeeId1' employeeId = '' chooseType = '2'></td>"+
						 "</td>"+
						 "<td style='width:360px'>销售第二人"+
						     "<input type='text' name = 'employeeId2' employeeId = '' chooseType = '2'></td>"+
						 "</td>"+
						 "<td style='width:360px'>销售第三人"+
						     "<input type='text' name = 'employeeId3' employeeId = '' chooseType = '2'></td>"+
					 	 "</td>"+
					  "</tr>"+
			      "</table>";
		
		
		if (type == 2) {
			var div = jQuery("<div class='nav_content_div' goodsId = '"+projectId+"'></div>");
			div.append(str);
			jQuery("div[name='goodsNameLI']").append(div);
			changeDiv(2);
		}
		else {
			var div = jQuery("<div class='nav_content_div' comboId = '"+projectId+"'></div>");
			div.append(str);
			jQuery("div[name='comboNameLI']").append(div);
			changeDiv(3);
		}
	}
}

jQuery(".project_hand_content").delegate("li", "click", function () {
	jQuery(this).siblings().removeClass("appiontActive");
	if (jQuery(this).hasClass("appiontActive")){
		jQuery(this).removeClass("appiontActive");
	}
	else {
		jQuery(this).addClass("appiontActive");
	}
});

function confirmAppion () {
	var appiont = jQuery(".zzc").find(".appiontActive");
	jQuery(appointObj).empty();
	if (isEmpty(jQuery(appiont).attr("employeename"))) {
		jQuery(appointObj).append("选择预约人员+");
		jQuery(appointObj).attr("appointmentId", "");
	}
	else {
		var employeename = jQuery(appiont).attr("employeename");
		var appointmentdate = jQuery(appiont).attr("appointmentdate");
		var appointmentId = jQuery(appiont).attr("appointmentId");
		jQuery(appointObj).append("预约人员:"+employeename+"  时间:"+appointmentdate);
		jQuery(appointObj).attr("appointmentId", appointmentId);
	}
	cancl();
}

var appointObj = "";

function chooseAppoint (obj, orderType) {
	if (orderType == 2) {
		dialog("无纸单无法修改预约人员！");
		return;
	}
	appointObj = obj;
	var appointmentId = jQuery(obj).attr("appointmentId");
	jQuery.ajax({
    	url : baseUrl + "KeepAccounts/manuallyOpenOrderAppoint",
    	type : "POST",
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		var appointObjList = e.msg;
    		
    		jQuery(".project_hand_content").empty();
    		
    		for (var i = 0; i < appointObjList.length; i++) {
    			var appointObj = appointObjList[i];
    			var str = "";
    			if (appointmentId == appointObj.appointmentId) {
    				str += '<li class="clearfix appiontActive" employeeName = "'+appointObj.employeeName+'" appointmentDate = "'+appointObj.appointmentDate+'" appointmentId = "'+appointObj.appointmentId+'">';
    			}
    			else {
    				str += '<li class="clearfix" employeeName = "'+appointObj.employeeName+'" appointmentDate = "'+appointObj.appointmentDate+'" appointmentId = "'+appointObj.appointmentId+'">';
    			}
    			str += '<div class="img">'+
					     	'<img src="'+qiniuUrl+appointObj.headUrl+'">'+
					   '</div>'+
					   '<div class="text">'+
						   '<span>'+appointObj.memberName+'</span>'+
						   '<div>'+appointObj.phone+'</div>';
    			if (appointObj.appointmentWay == 1) {
    				str += '<p>电话预约</p>';
    			}
    			else {
    				str += '<p>微信预约</p>';
    			}
		        str +='</div>'+
					 '<div class="project_hand_right">'+
					   '<div>'+appointObj.categoryName+'</div>'+
					   '<span>预约时间'+appointObj.appointmentDate+'</span>'+
					   '<p>'+appointObj.employeeName+'</p>'+
					   '<p>'+appointObj.levelName+'</p>'+
					 '</div>'+
				   '</li>';

    			jQuery(".project_hand_content").append(str);
    		}
    		jQuery(".zzc").show();
    	}
    });
}

function cancl () {
	jQuery(".zzc").hide();
}

jQuery("div[name='projectPay']").delegate("div[name='projectNameLI'] table tr:eq(0) input[type='checkbox']", "click", function () {
	jQuery(this).parents("div[name='projectNameLI']").find("table tr:eq(0) input[type='checkbox']").attr("checked",false);
    if (jQuery(this).attr("name") == "isAssign") {
    	jQuery(this).parents("div[name='projectNameLI']").find("table tr:eq(0) input[name='isAppoint']").attr("chooseType", 0);
    }
    else {
    	jQuery(this).parents("div[name='projectNameLI']").find("table tr:eq(0) input[name='isAssign']").attr("chooseType", 0);
    }
	if (jQuery(this).attr("chooseType") != 1) {
		jQuery(this).prop("checked",true);
		jQuery(this).attr("chooseType", 1);
	}
	else {
		jQuery(this).attr("chooseType", 0);
	}
});

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
	jQuery(obj).parents(".nav_content_div").remove();
}

function changeIsAppoint(obj) {
	jQuery(obj).parents(".xiaofei-item").find("input[name='isAppoint']").attr("checked",false);
	jQuery(obj).attr("checked",true);
}

function save(type) {
	var openOrderDate = jQuery("input[name='openOrderDate']").val();
	var handOrderCode = jQuery("input[name='handOrderCode']").val();
	var memberId = jQuery("div[name='memberTR']").find("input[name = 'memberId']").val();
	var sex = jQuery("input:radio[name='sex']:checked").val();
	var orderId = changeEmpty(jQuery(".nav_right_content").attr("orderId"));
	
	var arrayObj = new Array();
	//项目
	var projectObj = jQuery("div[name='projectNameLI']");
	for (var i = 0; i < projectObj.length; i++) {
		var projectId = jQuery(projectObj[i]).attr("projectId");
		var appointmentId = jQuery(projectObj[i]).find(".select_order").attr("appointmentid");
		var detailId = changeEmpty(jQuery(projectObj[i]).attr("detailId"));
		var projectStepArrayObj = new Array();
		var projectStepObj = jQuery(projectObj[i]).find("tr");
				
		for (var j = 0; j < projectStepObj.length; j++) {
			var positionId = jQuery(projectStepObj[j]).attr("positionId");
			var shiftMahjongStepId = changeEmpty(jQuery(projectStepObj[j]).attr("shiftMahjongStepId"));
			var employeeId = jQuery(projectStepObj[j]).find("input[name='employeeId']").attr("employeeId");
			var isAssign = 0;
			if (jQuery(projectStepObj[j]).find("input[name='isAssign']").prop('checked')) {
				isAssign = 1;
			}
			var isAppoint = 0;
			if (jQuery(projectStepObj[j]).find("input[name='isAppoint']").prop('checked')) {
				isAppoint = 1;
				isAssign = 1;
			}

			var StepStr = {"shiftMahjongStepId" : shiftMahjongStepId, "positionId":positionId, "employeeId":employeeId, "isAssign":isAssign, "isAppoint":isAppoint};
			projectStepArrayObj.push(StepStr);
		}
		
		var projectStepArrayObjStr = JSON.stringify(projectStepArrayObj);
		var projectObjStr = {"type":1, "projectId":projectId, "detailId" : detailId, "appoint" : appointmentId, "projectStepArrayObjStr":projectStepArrayObjStr};
		arrayObj.push(projectObjStr);
	}
	//疗程
	var comboObj = jQuery("div[name='comboNameLI']").find(".nav_content_div");
	for (var i = 0; i < comboObj.length; i++) {
		var comboId = jQuery(comboObj[i]).attr("comboId");
        var employeeId1 = jQuery(comboObj[i]).find("input[name='employeeId1']").attr("employeeId");
        var employeeId2 = jQuery(comboObj[i]).find("input[name='employeeId2']").attr("employeeId");
        var employeeId3 = jQuery(comboObj[i]).find("input[name='employeeId3']").attr("employeeId");
        if (isEmpty(employeeId1)) {
        	employeeId1 = 0;
        }
        if (isEmpty(employeeId2)) {
        	employeeId2 = 0;
        }
        if (isEmpty(employeeId3)) {
        	employeeId3 = 0;
        }
        var employeeIdObj = {"employeeId1" : employeeId1, "employeeId2" : employeeId2, "employeeId3" : employeeId3};
        
        var projectStepArrayObjStr = JSON.stringify(employeeIdObj);
		var projectObjStr = {"type":3, "projectId":comboId, "projectStepArrayObjStr":projectStepArrayObjStr};
		arrayObj.push(projectObjStr);
	}
	//商品
	var goodsObj = jQuery("div[name='goodsNameLI']").find(".nav_content_div");
	for (var i = 0; i < goodsObj.length; i++) {
		var goodsId = jQuery(goodsObj[i]).attr("goodsId");
        var employeeId1 = jQuery(goodsObj[i]).find("input[name='employeeId1']").attr("employeeId");
        var employeeId2 = jQuery(goodsObj[i]).find("input[name='employeeId2']").attr("employeeId");
        var employeeId3 = jQuery(goodsObj[i]).find("input[name='employeeId3']").attr("employeeId");
        if (isEmpty(employeeId1)) {
        	employeeId1 = 0;
        }
        if (isEmpty(employeeId2)) {
        	employeeId2 = 0;
        }
        if (isEmpty(employeeId3)) {
        	employeeId3 = 0;
        }
        var employeeIdObj = {"employeeId1" : employeeId1, "employeeId2" : employeeId2, "employeeId3" : employeeId3};
        
        var projectStepArrayObjStr = JSON.stringify(employeeIdObj);
		var projectObjStr = {"type":2, "projectId":goodsId, "projectStepArrayObjStr":projectStepArrayObjStr};
		arrayObj.push(projectObjStr);
	}
	if (arrayObj.length == 0) {
		dialog("请添加项目、商品或疗程！");
		return;
	}
	var arrayObjStr = JSON.stringify(arrayObj);
	jQuery.ajax({
    	url : baseUrl + "KeepAccounts/manuallyOpenOrderSave",
    	type : "POST",
    	data : "memberId=" + memberId + "&sex=" + sex + "&arrayObjStr=" + arrayObjStr + "&openOrderDate="+ openOrderDate +"&handOrderCode=" + handOrderCode +"&orderId="+orderId,
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		var orderId = e.msg;
    		if (type == 1) {
    			window.location.href = baseUrl + "selfcashier/action/orderinfo?orderId="+ orderId;
    		}
    		else {
    			dialog("挂单成功");
    			location.reload();
    		}
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

function changeEmpty (val) {
	if (isEmpty(val)) {
		val = "";
	}
	return val;
}