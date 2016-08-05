jQuery(document).ready(function () {
    var _now=0;
    var oul = jQuery(".table_content");
    var numl = jQuery(".customer_content ul li");
    var wid = jQuery(".table_content table").eq(0).width()+45;
    //数字图标实现
    numl.click(function () {
        var index = jQuery(this).index();
        jQuery(this).addClass("active2").siblings().removeClass('active2');
        jQuery(this).parents('.customer_content').find('.table_content').stop(true,true).animate({'left': -wid * index}, 500);
    })
    //左右箭头轮播
    jQuery(".left_click").click(function () {
        if (_now>=1) {_now--}
        else  _now=jQuery(this).parents('.customer_content').find('ul li').size()-1;
		     jQuery(this).parents('.customer_content').find('.table_content').stop(true,true).animate({'left': -wid * _now}, 500);
      jQuery(this).parents('.customer_content').find('li').eq(_now).addClass("active2").siblings().removeClass('active2');
        
    });
    jQuery(".right_click").click(function () {
        if (_now == jQuery(this).parents('.customer_content').find('ul li').size()-1) {
            _now = 0;
        }
        else  _now++;
	        jQuery(this).parents('.customer_content').find('.table_content').stop(true,true).animate({'left': -wid * _now}, 500);
      jQuery(this).parents('.customer_content').find('li').eq(_now).addClass("active2").siblings().removeClass('active2');
	   
    });
});

jQuery(function(){
	  jQuery('.zzc_open_card_alert_content input[type="checkbox"]').click(function(){
	   if(jQuery(this).is(':checked')){
          jQuery(this).parent().addClass('active1')
	   }
	   else{
	       jQuery(this).parent().removeClass('active1')
	    }
	  })
	  
	  jQuery('.order_top input[type="checkbox"]').click(function(){
	   if(jQuery(this).is(':checked')){
          jQuery('.order_open_card').show();
			
	   }
	   else{
	       jQuery('.order_open_card').hide();
		  
	    }
	  })
	  
	 jQuery('em[name="handOrderCode"]').click(function(){
	   	jQuery('.card_number').show();
	  
	  })
	  
	  jQuery("ul[name='handNumberUl']").empty();
	  var code = new Big(startHandNumber)
	  for (var i = 0; i < 50; i++) {
		  code = code.plus(new Big(1));
		  jQuery("ul[name='handNumberUl']").append("<li name = '"+code.toFixed(0)+"'>"+code.toFixed(0)+"</li>");
	  }
	  
});

function closeCardNumber () {
	jQuery(".card_number").hide();
}

//选择开单号 
jQuery('.card_number').delegate("li", "click", function(){
    
    var handOrderCode = jQuery(this).text();
    if (jQuery(this).hasClass("active")) {
    	return;
    }
    jQuery('em[name="handOrderCode"]').text(handOrderCode);
    jQuery('em[name="handOrderCode"]').append("<img src='"+baseUrl+"images/open_card_img.png'>");
    jQuery('em[name="handOrderCode"]').attr("handOrderCode", handOrderCode);
    jQuery('.card_number').hide();
  })

function alertZzc() {
	  jQuery.ajax({
	    	url : baseUrl + "KeepAccounts/initializeNoPaperModel",
	    	type : "POST",
	    	success : function(e){
	    		if (e.code != 0) {
	                dialog(e.msg);
	                return;
	            }
	    		var datas = e.msg;
	    		
	    		jQuery("[name='openOrderType'][typevalue = '2']").click();
	    		jQuery("span[name='appointEmployee']").parent().hide();
	    		var positionInfoShiftMahjongDtoList = datas.positionInfoShiftMahjongDtoList;
	    		var handOrderCodeList = datas.handOrderCodeList;
	    		var appointObjList = datas.appointObjList;
	    		
	    		for (var i = 0; i < handOrderCodeList.length; i++) {
	    			var handOrderCode = handOrderCodeList[i];
	    			jQuery("ul[name='handNumberUl']").find("[name='"+handOrderCode+"']").addClass("active");
	    		}
	    		
	    		jQuery("div[name='appointDIV']").empty();
	    		
	    		for (var i = 0; i < appointObjList.length; i++) {
	    			var appointObj = appointObjList[i];
	    			var str = '<div class="order_open_card_content" onclick = "chooseAppoint(\''+appointObj.memberName+'\', \''+appointObj.sex+'\', \''+appointObj.phone+'\', '+appointObj.appointmentId+', '+appointObj.memberId+', \''+appointObj.employeeCode+'\',\''+appointObj.employeeName+'\',\''+appointObj.categoryName+'\')">'+
							   '<div class="order_open_card_content_left">'+
								  '<div class="img">'+
									'<img src="'+qiniuUrl+appointObj.headUrl+'">'+
								  '</div>'+
								  '<div class="img_right">'+
									 '<div>'+appointObj.memberName+'</div>'+
									 '<p>'+appointObj.phone+'</p>';
	    			if (appointObj.appointmentWay == 1) {
	    				str += '<span>电话预约</span>';
	    			}
	    			else {
	    				str += '<span>微信预约</span>';
	    			}
	    			str += 	'</div>'+
					    '</div>'+
						'<div class="order_open_card_content_right">'+
						   '<p>'+appointObj.categoryName+'</p>'+
						   '<span>预约时间'+appointObj.appointmentDate+'</span>'+
						   '<div>'+appointObj.employeeName+'</div>'+
						   '<div>'+appointObj.levelName+'</div>'+
					   '</div>'+
					'</div>';
	    			jQuery("div[name='appointDIV']").append(str);
	    		}
	    		
	    		jQuery("ul[name='positionUl']").empty();
	    		jQuery(".zzc").find(".open_card_alert_state_content").remove();
	    		jQuery(".zzc").find("div[name='shiftMajone']").remove();
	    		for (var i = 0; i < positionInfoShiftMahjongDtoList.length; i++) {
	    			var positionInfoShiftMahjongDto = positionInfoShiftMahjongDtoList[i];
	    			var str = "";
	    			if (i == 0) {
	    				jQuery("ul[name='positionUl']").append('<li class="active" positionId = "'+positionInfoShiftMahjongDto.positionId+'" onclick = "choosePositionShiftMahjong(this, '+positionInfoShiftMahjongDto.positionId+')">'+positionInfoShiftMahjongDto.positionName+'</li>');
	    				
	    				str = '<div name = "shiftMajone" positionId = "'+positionInfoShiftMahjongDto.positionId+'"><p class = "shift_majone_name">'+
							        '<span>选择轮牌名称<input type="text" name= "shiftMahjongName" shiftMahjongId = "" disabled="disabled"></span>'+
							        '<span>选择轮牌人员<input type="text" name="shiftMahjongEmployeeName" shiftMahjongEmployeeId = "" disabled="disabled"></span>'+
							        '<span>是否指定<input type="checkbox" name= "isAssign"></span>'+
							   '</p>'+
	    				       '<div class="open_card_alert_state_content">';
	    			}
	    			else {
	    				jQuery("ul[name='positionUl']").append('<li onclick = "choosePositionShiftMahjong(this, '+positionInfoShiftMahjongDto.positionId+')">'+positionInfoShiftMahjongDto.positionName+'</li>');
	    				
	    				str = '<div class = "hide" name = "shiftMajone" positionId = "'+positionInfoShiftMahjongDto.positionId+'"><p class = "shift_majone_name">'+
							        '<span>选择轮牌名称<input type="text" name= "shiftMahjongName" shiftMahjongId = "" disabled="disabled"></span>'+
							        '<span>选择轮牌人员<input type="text" name="shiftMahjongEmployeeName" shiftMahjongEmployeeId = "" disabled="disabled"></span>'+
							        '<span>是否指定<input type="checkbox" name= "isAssign"></span>'+
							   '</p>'+
							   '<div class="open_card_alert_state_content">';
	    			}
	    			
	    			var shiftMahjongDtoList = positionInfoShiftMahjongDto.shiftMahjongDtoList;
	    			for (var k = 0; k < shiftMahjongDtoList.length; k++) {
	    				var shiftMahjongDto = shiftMahjongDtoList[k];
	    				str += '<div class="open_card_alert_state_content_style" shiftMahjongId = "'+shiftMahjongDto.shiftMahjongId+'" shiftMahjongName = "'+shiftMahjongDto.shiftMahjongName+'">'+
	    					        '<p>'+shiftMahjongDto.shiftMahjongName+'</p>'+
	    							'<div class="open_card_alert_state_number">'+
	    					           '<ul class="clearfix">';
	    				var shiftMahjongEmployeeList = shiftMahjongDto.shiftMahjongEmployeeList;
	    				for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
	    					var shiftMahjongEmployee = shiftMahjongEmployeeList[j];
	    					str += '<li ';
	    				    if (shiftMahjongEmployee.state == 0) {
	    				    	str += 'class="working"';
	    				    }
	    				    else if (shiftMahjongEmployee.state == 1) {
	    				    	str += 'class="free"';
	    				    }
	    				    else if (shiftMahjongEmployee.state == 2) {
	    				    	str += 'class="rest"';
	    				    }
	    				    else {
	    				    	str += 'class="off_point"';
	    				    }
	    				    str += 'name = "activeLi" shiftMahjongEmployeeId = "'+shiftMahjongEmployee.employeesId+'" shiftMahjongEmployeeName = "'+shiftMahjongEmployee.employeeCode+' '+shiftMahjongEmployee.name+'">'+
	    				                    '<i>'+shiftMahjongEmployee.shiftMahjongOrder+'</i>'+
	    				                    '<em>'+shiftMahjongEmployee.employeeCode+' '+shiftMahjongEmployee.name+'</em>'+
	    				                    '<a href="javascript:;"></a>'+
	    		                        '</li>';
	    				}
	    				str +=	'</div></div>';
	    			}
	    			str += '</div></div>';
	    			jQuery(".open_card_alert_state").append(str);
	    		}
	    	}
	    });
	  jQuery("div[name='openOrderZzc']").show();
}

function selectNoPageOrder () {
	var selectHandCode = jQuery("input[name='selectHandCode']").val();
	
	if (isEmpty(selectHandCode)) {
		jQuery("p[name='handOrderCodeP']").parent().show();
	}
	else {
		jQuery("p[name='handOrderCodeP']").parent().hide();
		if (!isEmpty(jQuery("p[handOrderCodeP='"+selectHandCode+"']").attr("name"))) {
			jQuery("p[handOrderCodeP='"+selectHandCode+"']").parent().show();
		}
	}

}

function chooseAppoint (memberName, sex, phone, appointmentId, memberId, employeeCode, employeeName, categoryName) {
	 jQuery("div[name='memberNoPage']").find("td[name='memberName']").attr("memberId", memberId);
	 jQuery("div[name='memberNoPage']").find("td[name='memberName']").attr("appointmentId", appointmentId);
	 jQuery("div[name='memberNoPage']").find("td[name='memberName']").text(memberName);
	 jQuery("div[name='memberNoPage']").find("td[name='memberPhone']").text(phone);
	 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").empty();
	 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").attr("memberSex", sex);
	 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").text(sex);
	 
	 /*var activeLi = jQuery(jQuery("li[shiftMahjongEmployeeId='"+employeeId+"']")[0]).attr("name");
	 if (!isEmpty(activeLi)) {
		 if (!jQuery(jQuery("li[shiftMahjongEmployeeId='"+employeeId+"']")[0]).find("a").hasClass("active3")) {
			 jQuery(jQuery("li[shiftMahjongEmployeeId='"+employeeId+"']")[0]).click();
		 }
		 var positionid = jQuery(jQuery("li[shiftMahjongEmployeeId='"+employeeId+"']")[0]).parents("div[name='shiftMajone']").attr("positionid");
		 jQuery("ul[name='positionUl']").find("li[positionid='"+positionid+"']").click();
		 jQuery(jQuery("li[shiftMahjongEmployeeId='"+employeeId+"']")[0]).parents("div[name='shiftMajone']").find("input[name='isAssign']").attr("checked",'true');
	 }*/
	 
	 jQuery("span[name='appointEmployee']").text(employeeCode + " " + employeeName);
	 jQuery("span[name='appointProject']").text(categoryName);
	 jQuery("span[name='appointEmployee']").parent().show();
	 orderOpenCardClose();
}

function choosePositionShiftMahjong (obj, positionId) {
	jQuery(obj).siblings().removeClass("active");
	jQuery(obj).addClass("active");
	jQuery("div[name = 'shiftMajone']").addClass("hide");
	jQuery("div[positionId='"+positionId+"']").removeClass("hide");
}

jQuery(".open_card_alert_state").delegate("li[name='activeLi']", "click", function () {
	if (!jQuery(this).find("a").hasClass("active3")) {
		jQuery(this).parents(".open_card_alert_state_content").find("li[name='activeLi'] a").removeClass("active3");
		jQuery(this).find("a").addClass("active3");
		
		var shiftMahjongEmployeeId = jQuery(this).attr("shiftMahjongEmployeeId");
		var shiftMahjongEmployeeName = jQuery(this).attr("shiftMahjongEmployeeName");
		var shiftmahjongid = jQuery(this).parents(".open_card_alert_state_content_style").attr("shiftmahjongid");
		var shiftmahjongname = jQuery(this).parents(".open_card_alert_state_content_style").attr("shiftmahjongname");
		
		jQuery(this).parents("div[name = 'shiftMajone']").find("input[name='shiftMahjongName']").val(shiftmahjongname);
		jQuery(this).parents("div[name = 'shiftMajone']").find("input[name='shiftMahjongName']").attr("shiftmahjongid", shiftmahjongid);
		jQuery(this).parents("div[name = 'shiftMajone']").find("input[name='shiftMahjongEmployeeName']").val(shiftMahjongEmployeeName);
		jQuery(this).parents("div[name = 'shiftMajone']").find("input[name='shiftMahjongEmployeeName']").attr("shiftMahjongEmployeeId", shiftMahjongEmployeeId);
	}
	else {
		jQuery(this).parents("div[name = 'shiftMajone']").find("li[name='activeLi'] a").removeClass("active3");
		
		jQuery(this).parents("div[name = 'shiftMajone']").find("input[name='shiftMahjongName']").val("");
		jQuery(this).parents("div[name = 'shiftMajone']").find("input[name='shiftMahjongName']").attr("shiftmahjongid", "");
		jQuery(this).parents("div[name = 'shiftMajone']").find("input[name='shiftMahjongEmployeeName']").val("");
		jQuery(this).parents("div[name = 'shiftMajone']").find("input[name='shiftMahjongEmployeeName']").attr("shiftMahjongEmployeeId", "");

	}
})

jQuery(".open_card_alert_state_content").delegate("li[name='activeLi']", "click", function () {
	if (!jQuery(this).find("a").hasClass("active3")) {
		jQuery(this).parents(".open_card_alert_state_content").find("li[name='activeLi'] a").removeClass("active3");
		jQuery(this).find("a").addClass("active3");
		
		var shiftMahjongEmployeeId = jQuery(this).attr("shiftMahjongEmployeeId");
		var shiftMahjongEmployeeName = jQuery(this).attr("shiftMahjongEmployeeName");
		var shiftmahjongid = jQuery(this).parents(".open_card_alert_state_content_style").attr("shiftmahjongid");
		var shiftmahjongname = jQuery(this).parents(".open_card_alert_state_content_style").attr("shiftmahjongname");
		
		jQuery(this).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongName']").val(shiftmahjongname);
		jQuery(this).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongName']").attr("shiftmahjongid", shiftmahjongid);
		jQuery(this).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongEmployeeName']").val(shiftMahjongEmployeeName);
		jQuery(this).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongEmployeeName']").attr("shiftMahjongEmployeeId", shiftMahjongEmployeeId);
	}
	else {
		jQuery(this).parents(".zzc1_open_card_alert").find("li[name='activeLi'] a").removeClass("active3");
		
		jQuery(this).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongName']").val("");
		jQuery(this).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongName']").attr("shiftmahjongid", "");
		jQuery(this).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongEmployeeName']").val("");
		jQuery(this).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongEmployeeName']").attr("shiftMahjongEmployeeId", "");

	}
})

function submits(){
	var handOrderCode = jQuery('em[name="handOrderCode"]').attr("handOrderCode");
	
	if (isEmpty(handOrderCode)) {
		dialog("手牌号不能为空！");
		jQuery("input[name='handOrderCode']").focus();
		return;
	}
	
	var typeValue = jQuery(".active_color").attr('typeValue');
	
	if (isEmpty(typeValue)) {
		typeValue = jQuery(".active_border_").attr('typeValue');
	}
	
	var sex = jQuery("td[name='memberSex']").attr("memberSex");
	var memberId = jQuery("td[name='memberName']").attr("memberId");
	var appointmentId = jQuery("td[name='memberName']").attr("appointmentId");
	
	if (isEmpty(memberId) && typeValue == 1) {
		dialog("当前选中会员，请选择一个会员！");
		return;
	}
	
	if (isEmpty(memberId) && typeValue == 3) {
		dialog("当前选中预约，请选择一个预约单！");
		return;
	}
	
	if (isEmpty(memberId)) {
		memberId = "";
	}
	if (isEmpty(appointmentId)) {
		appointmentId = "";
	}
    var arrayObj = new Array();
        
    var obj = jQuery(".zzc").find("div[name = 'shiftMajone']");
    for (var i =0; i < obj.length; i++) {
        var positionId = jQuery(obj[i]).attr("positionId");
        var isAssign = 0;
        if (jQuery("input[name='isAssign']").is(':checked')) {
        	isAssign = 1;
        }
        
        var shiftMahjongId = jQuery(obj[i]).find("input[name='shiftMahjongName']").attr("shiftmahjongid");
    	var employeeId = jQuery(obj[i]).find("input[name='shiftMahjongEmployeeName']").attr("shiftMahjongEmployeeId");
    	
        if (isEmpty(shiftMahjongId)) {
        	shiftMahjongId = "";
        }
        
        if (isEmpty(employeeId)) {
        	employeeId = "";
        }
    	
        var employeeStr = {"positionId":positionId, "isAssign":isAssign, "shiftMahjongId":shiftMahjongId, "employeeId" : employeeId}
        arrayObj.push(employeeStr);
    }
    var employeeObj = JSON.stringify(arrayObj);
        
    jQuery.ajax({
    	url : baseUrl + "KeepAccounts/addOrder",
    	type : "POST",
    	data : "sex="+sex+"&handOrderCode="+handOrderCode+"&employeeObj="+employeeObj+"&memberId="+memberId+"&appointmentId="+appointmentId,
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		dialog("开单成功！");
    		location.reload();
    	}
    });
}

var addOrUpdateShiftMahjongStepId = "";
var addOrUpdateType = null;

function showServers (obj, type) {
	var shiftMahjongStepId = jQuery(obj).parents("tr").attr("shiftMahjongStepId");
	addOrUpdateShiftMahjongStepId = shiftMahjongStepId;
	var positionId = jQuery(obj).parents("tr").attr("positionId");
	addOrUpdateType = type;
	var employeeId =  null;
	var shiftMahjongId = null;
	if (type == 1) {
		employeeId = jQuery(obj).parents("tr").attr("employeeId");
		shiftMahjongId = jQuery(obj).parents("tr").attr("shiftMahjongId");
	}
	
	jQuery(".zzc1").find(".open_card_alert_state_content_style").remove();
	jQuery.ajax({
    	url : baseUrl + "KeepAccounts/initializeNoPaperModel",
    	type : "POST",
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		var datas = e.msg;
    		var positionInfoShiftMahjongDtoList = datas.positionInfoShiftMahjongDtoList;
    		
    		for (var i = 0; i < positionInfoShiftMahjongDtoList.length; i++) {
    			var positionInfoShiftMahjongDto = positionInfoShiftMahjongDtoList[i];
    			if (positionInfoShiftMahjongDto.positionId == positionId) {
    				var shiftMahjongDtoList = positionInfoShiftMahjongDto.shiftMahjongDtoList;
    				for (var k = 0; k < shiftMahjongDtoList.length; k++) {
    					var shiftMahjongDto = shiftMahjongDtoList[k];
    					var str = '<div class="open_card_alert_state_content_style" shiftMahjongId = "'+shiftMahjongDto.shiftMahjongId+'" shiftMahjongName = "'+shiftMahjongDto.shiftMahjongName+'">'+
    						        '<p>'+shiftMahjongDto.shiftMahjongName+'</p>'+
    								'<div class="open_card_alert_state_number">'+
    						           '<ul class="clearfix">';
    					var shiftMahjongEmployeeList = shiftMahjongDto.shiftMahjongEmployeeList;
    					for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
    						var shiftMahjongEmployee = shiftMahjongEmployeeList[j];
    						str += '<li ';
    					    if (shiftMahjongEmployee.state == 0) {
    					    	str += 'class="working"';
    					    }
    					    else if (shiftMahjongEmployee.state == 1) {
    					    	str += 'class="free"';
    					    }
    					    else if (shiftMahjongEmployee.state == 2) {
    					    	str += 'class="rest"';
    					    }
    					    else {
    					    	str += 'class="off_point"';
    					    }
    					    str += 'name = "activeLi" shiftMahjongEmployeeId = "'+shiftMahjongEmployee.employeesId+'" shiftMahjongEmployeeName = "'+shiftMahjongEmployee.employeeCode+' '+shiftMahjongEmployee.name+'">'+
    					                    '<i>'+shiftMahjongEmployee.shiftMahjongOrder+'</i>'+
    					                    '<em>'+shiftMahjongEmployee.employeeCode+' '+shiftMahjongEmployee.name+'</em>';
    					    if (type == 1 && shiftMahjongEmployee.shiftMahjongId == shiftMahjongId && shiftMahjongEmployee.employeesId == employeeId) {
    					    	str += '<a href="javascript:;" class = "active3"></a>'+
    			                '</li>';
    					    }
    					    else {
    					    	str += '<a href="javascript:;"></a>'+
    			                '</li>';
    					    }
    					}
    					str +=	'</div></div>';
    					
    					jQuery(".zzc1").find("div[name='addOrUpdate']").before(str);
    				}
    			}
    		}
    	}
    });
	jQuery(".zzc1").show();
}

function sureAddOrUpdate (obj) {
	var shiftMahjongId = jQuery(obj).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongName']").attr("shiftmahjongid");
	var employeeId = jQuery(obj).parents(".zzc1_open_card_alert").find("input[name='shiftMahjongEmployeeName']").attr("shiftMahjongEmployeeId");
	
	var isAssign = 0;
    if (jQuery("input[name='isAssign']").is(':checked')) {
    	isAssign = 1;
    }
	
	jQuery.ajax({
    	url : baseUrl + "KeepAccounts/action/addOrUpdateServerEmployee",
    	type : "POST",
    	data : "shiftMahjongStepId="+addOrUpdateShiftMahjongStepId+"&type="+addOrUpdateType+"&shiftMahjongId="+shiftMahjongId+"&employeeId="+employeeId+"&isAssign="+isAssign,
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		var datas = e.msg;
    		dialog("操作成功！");
    		location.reload();
    	}
    });
}

function overServerEmployee(obj) {
	var shiftMahjongStepId = jQuery(obj).parents("tr").attr("shiftMahjongStepId");
	jQuery.ajax({
    	url : baseUrl + "KeepAccounts/action/overServerEmployee",
    	type : "POST",
    	data : "shiftMahjongStepId="+shiftMahjongStepId,
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		var datas = e.msg;
    		dialog("操作成功！");
    		location.reload();
    	}
    });
}
var setDetailId = "";
function showSettingProject (detailId) {
	setDetailId = detailId;
	jQuery(".zzc2").show();
}

function settlementOrder (obj, orderId) {
	var projectList = jQuery(obj).parents(".customer_content").find("td[name='projectId']");
	for (var i = 0; i < projectList.length; i++) {
		var projectId = jQuery(projectList[i]).attr("projectId");
		if (isEmpty(projectId)) {
			dialog("存在未设置项目的服务，请设置！");
			return;
		}
	}
	
	var shiftMahjongStepList = jQuery(obj).parents(".customer_content").find("td[name='shiftMahjongStep']");
	for (var i = 0; i < shiftMahjongStepList.length; i++) {
		var isOver = jQuery(shiftMahjongStepList[i]).attr("isOver");
		if (isOver == 1) {
			dialog("存在服务中的服务，无法结算！");
			return;
		}
	}
	
	window.location.href = baseUrl + "KeepAccounts/initializeManuallyOpenOrder?orderId="+ orderId;
}

function deleteDetailId (detailId) {
	if(confirm("确认要删除该服务吗？")){ 
		jQuery.ajax({
			type : "post",  
			url : baseUrl + "KeepAccounts/action/deleteOrderDetail",
			data : "detailId="+detailId,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				dialog("删除服务成功！");
	    		location.reload();
			}
		});
	}
}

function deleteOrderInfo (orderId) {
	if(confirm("确认要删除该订单吗？")){ 
		jQuery.ajax({
			type : "post",  
			url : baseUrl + "KeepAccounts/action/deleteOrderInfo",
			data : "orderId="+orderId,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				dialog("删除订单成功！");
	    		location.reload();
			}
		});
	}
}

function openOrderType (obj) {
	 jQuery("[name='openOrderType']").removeClass("active_border_");
	 jQuery("[name='openOrderType']").removeClass("active_color");
	 jQuery("[name='openOrderType']").find("[name='vip_em']").removeClass("active_border");
	 jQuery(".input_content").hide();
	 jQuery("[name='seekName']").hide();
	 jQuery(".order_open_card").hide();
	 if (jQuery(obj).hasClass("vip_search_content")) {
		 jQuery(obj).addClass("active_color");
		 jQuery(obj).find("[name='vip_em']").addClass("active_border");
		 jQuery(".input_content").show();
		 jQuery("[name='seekName']").show();
		 
		 jQuery("div[name='memberNoPage']").find("td[name='memberName']").attr("memberId", "");
		 jQuery("div[name='memberNoPage']").find("td[name='memberName']").attr("appointmentId", "");
		 jQuery("div[name='memberNoPage']").find("td[name='memberName']").text("");
		 jQuery("div[name='memberNoPage']").find("td[name='memberPhone']").text("");
		 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").empty();
		 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").attr("memberSex", "");
	 }
	 else {
		 jQuery(obj).addClass("active_border_");
		 jQuery(obj).find("i").addClass("active_border");
		 if (jQuery(obj).attr("typeValue") == 2) {
			 jQuery("div[name='memberNoPage']").find("td[name='memberName']").attr("memberId", "");
			 jQuery("div[name='memberNoPage']").find("td[name='memberName']").attr("appointmentId", "");
			 jQuery("div[name='memberNoPage']").find("td[name='memberName']").text("散客");
			 jQuery("div[name='memberNoPage']").find("td[name='memberPhone']").text("---");
			 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").empty();
			 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").append('<span><input type="radio" name="sex" value = "男" checked>男</span><span><input type="radio" name="sex" value = "女">女</span>');
			 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").attr("memberSex", "男");
		 }
		 else {
			 jQuery("div[name='memberNoPage']").find("td[name='memberName']").attr("memberId", "");
			 jQuery("div[name='memberNoPage']").find("td[name='memberName']").attr("appointmentId", "");
			 jQuery("div[name='memberNoPage']").find("td[name='memberName']").text("");
			 jQuery("div[name='memberNoPage']").find("td[name='memberPhone']").text("");
			 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").empty();
			 jQuery("div[name='memberNoPage']").find("td[name='memberSex']").attr("memberSex", "");
			 jQuery(".order_open_card").show();
		 }
	 }
}

function orderOpenCardClose () {
	 jQuery(".order_open_card").hide();
}

function hideModal () {
	jQuery(".zzc").hide();
	jQuery(".zzc1").hide();
	jQuery(".zzc2").hide();
}

function chooseDept (obj) {
	var deptId = jQuery(obj).val();
	jQuery(".zzc2_select_item_content_").hide();
	jQuery("div[deptId='"+deptId+"']").show();
}

function chooseCategory (categoryId, obj) {
	jQuery(obj).siblings().removeClass("active");
	jQuery(obj).addClass("active");
	jQuery(obj).parents(".zzc2_select_item_content_").find(".zzc2_select_item_content_right_content").hide();
	jQuery("div[categoryId='"+categoryId+"']").show();
}

jQuery(".zzc2_select_item_content_right_content").delegate("[name='projectId']", "click", function (){
	jQuery(this).siblings().removeClass("active4");
	jQuery(this).addClass("active4");
})

function saveProject () {
	var parentList = jQuery(".zzc2_select_item_content_");
	var projectId  = null;
	for (var j = 0; j < parentList.length; j++) {
		var parent = jQuery(parentList[j]);
		if (jQuery(parent).css("display")  != "none") {
			var contentList = jQuery(parent).find(".zzc2_select_item_content_right_content");
			
			for (var i = 0; i < contentList.length; i++) {
				var content = contentList[i];
				if (jQuery(content).css("display")  != "none") {
					projectId  = jQuery(content).find(".active4").attr("projectId");
				}
			}
		}
	}
	
	if (projectId == null) {
		dialog("请选择项目！");
		return;
	}
	jQuery.ajax({
        type : "post",
        url : baseUrl + "KeepAccounts/action/settingProject",
        data : "projectId="+projectId+"&detailId="+setDetailId,
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            location.reload();
        }
    });
}

function addServer (obj, orderId) {
	jQuery.ajax({
        type : "post",
        url : baseUrl + "KeepAccounts/action/addDetailServer",
        data : "orderId="+orderId,
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            location.reload();
        }
    });
}