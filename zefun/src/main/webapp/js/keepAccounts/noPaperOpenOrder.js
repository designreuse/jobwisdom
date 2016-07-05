jQuery(function(){
	  jQuery('.zzc_open_card_alert_content input[type="checkbox"]').click(function(){
	   if(jQuery(this).is(':checked')){
          jQuery(this).parent().addClass('active1')
	   }
	   else{
	       jQuery(this).parent().removeClass('active1')
	    }
	  })
	});
//点击预约开单,下拉框
 jQuery(function(){
	  jQuery('.order_top input[type="checkbox"]').click(function(){
	   if(jQuery(this).is(':checked')){
          jQuery('.order_open_card').show();
			
	   }
	   else{
	       jQuery('.order_open_card').hide();
		  
	    }
	  })
	  
	 jQuery('input[name="handOrderCode"]').click(function(){
	   	jQuery('.card_number').show();
	  
	  })
	});
//选择开单号 

  jQuery('.card_number li').click(function(){
    jQuery(this).addClass('active').siblings().removeClass('active'); 
    var handOrderCode = jQuery(this).text();
    jQuery('input[name="handOrderCode"]').val(handOrderCode);
    jQuery('.card_number').hide();
  })

function alertZzc() {
	jQuery("div[name='openOrderZzc']").show();
}

function choosePositionShiftMahjong (obj, positionId) {
	jQuery(obj).siblings().removeClass("active");
	jQuery(obj).addClass("active");
	jQuery(".open_card_alert_state_content").addClass("hide");
	jQuery("div[positionId='"+positionId+"']").removeClass("hide");
}

jQuery(".open_card_alert_state_content").delegate("li[name='activeLi']", "click", function () {
	if (!jQuery(this).find("a").hasClass("active3")) {
		jQuery(this).parents(".open_card_alert_state_content").find("li[name='activeLi'] a").removeClass("active3");
		jQuery(this).find("a").addClass("active3");
	}
	else {
		jQuery(this).parents(".open_card_alert_state_content").find("li[name='activeLi'] a").removeClass("active3");
	}
})

function submits(){
	var handOrderCode  = jQuery("input[name='handOrderCode']").val();
	var sex = jQuery("input[name='sex'] :checked").val();
	var memberId = jQuery("input[name='memberId']").val();
	if (isEmpty(memberId)) {
		memberId = "";
	}
    var arrayObj = new Array();
        
    var obj = $(".open_card_alert_state_content");
    for (var i =0; i < obj.length; i++) {
        var positionId = $(obj[i]).attr("positionId");
        var isAssign = 0;
        if ($("input[name='isAssign']").is(':checked')) {
        	isAssign = 1;
        }
        var shiftMahjongEmployeeId = $(obj[i]).find(".active3").parents("li[name='activeLi']").attr("shiftMahjongEmployeeId");
        var projectId = $(obj[i]).attr("projectId");
        var projectNum = $(obj[i]).attr("projectNum");
        var orderNum = $(obj[i]).attr("orderNum");
        var employeeId = $(obj[i]).attr("employeeId");
        var isType = $(obj[i]).attr("isType");
        var employeeStr = {"positionId":positionId, "isAssign":isAssign, "shiftMahjongEmployeeId":shiftMahjongEmployeeId, "shiftMahjongEmployeeId":shiftMahjongEmployeeId, "employeeId":employeeId, "isType" : isType}
        arrayObj.push(employeeStr);
    }
    var employeeObj = JSON.stringify(arrayObj);
        
        $.ajax({
        	url : baseUrl + "staff/action/addOrder",
        	type : "POST",
        	data : "sex="+sex+"&handOrderCode="+handOrderCode+"&employeeObj="+employeeObj+"&memberId="+memberId,
        	success : function(e){
        		if (e.code != 0) {
                    dialog(e.msg);
                    return;
                }
        		var datas = e.msg;
        		
        	}
        });
    }
