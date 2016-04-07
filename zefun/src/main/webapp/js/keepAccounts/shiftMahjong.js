var  shiftMahjongId = "";

var stateList = new Array();
var a1 = {"state":"0","name":"工作中","styles":"gongzuozhong","imgs":baseUrl+"img/lunpai/cut.png"};
var a2 = {"state":"1","name":"空闲中","styles":"kongxianzhong","imgs":baseUrl+"img/lunpai/coffee.png"};
var a3 = {"state":"2","name":"暂时离开","styles":"zanshilikai","imgs":baseUrl+"img/lunpai/alarm.png"};
var a4 = {"state":"3","name":"离开","styles":"likai","imgs":baseUrl+"img/lunpai/clock.png"};
var a5 = {"state":"4","name":"指定服务","styles":"zhidingfuwu","imgs":baseUrl+"img/lunpai/heart.png"};

stateList[0] = a1;
stateList[1] = a2;
stateList[2] = a3;
stateList[3] = a4;
stateList[4] = a5;

//全局修改状态点击对象
var overallObj = "";

//全局修改状态轮牌员工标识
var shiftMahjongEmployeeId = "";

jQuery(document).ready(function(){
	updatePage();
	var obj = jQuery(".s-slider-wrap");
	for (var i = 0; i < obj.length; i++) {
		var num = jQuery(obj[i]).attr("num");
		renewalShift(num, 0);
	}
});

function renewalShift(num, locationNum) {
	var tmpSlide = jQuery(".s-slider-wrap[num = '"+num+"'] .slider4").bxSlider({
      slideWidth: 108,
      minSlides: 1,
      maxSlides: 15,
      moveSlides: 1,
      async:false,
      slideMargin: 0,
      pager: false,
      infiniteLoop: false
    });
	tmpSlide.goToSlide(locationNum);
}

//初始化轮牌新增model
jQuery("#shiftModel").click(function(){
	shiftMahjongId = "";
	
	valuation("shiftMahjongRule", 1);
	valuation("shiftMahjongUp", 1);
	valuation("nature", 1);
	jQuery("input[name='shiftMahjongName']").val("");
	
	updatePosition();
	
	jQuery("#rotating-setting-modal").modal("show");
});

function setShiftMahjong(shiftMahjongIdS,shiftMahjongName,nature,shiftMahjongUp,shiftMahjongRule){
	shiftMahjongId = shiftMahjongIdS;
	
	jQuery("#rotating-setting-modal").modal("show");
	
	valuation("shiftMahjongRule", shiftMahjongRule);
	valuation("shiftMahjongUp", shiftMahjongUp);
	valuation("nature", nature);
    
	jQuery("input[name='shiftMahjongName']").val(shiftMahjongName);
	
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/initializeModel",
		data : "shiftMahjongId="+shiftMahjongId,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var data = e.msg;
			updatePosition();
			if(data.length > 0){
				var obj = jQuery("input[name='positionId']");
				for (var j = 0; j < obj.length; j++) {
					for(var i = 0; i < data.length;i++){
						if (jQuery(obj[j]).val() == data[i].positionId) {
							jQuery(obj[j]).parent().find(".beau-checker").addClass("active");
							jQuery(obj[j]).prop("checked", true);
							jQuery(obj[j]).parent().parent().next().find("select[name='upShiftType']").val(data[i].isPunchCard);
							jQuery(obj[j]).parent().parent().next().find("select[name='upShiftType']").trigger("liszt:updated");
						}
					}
				}
			}
		}
	});
}

//切换部门时更新model中岗位下拉
function updatePosition(){
	jQuery("tr[name = 'positionMessage']").remove();
	
	for (var i = 0; i < deptDtoList.length; i++) {
		if (deptId == deptDtoList[i].deptId) {
			var positionList = deptDtoList[i].positionInfo;
			var positionMessage = null;
			for (var j = 0; j < positionList.length; j++) {
				if (j % 2 == 0) {
					positionMessage = jQuery("<tr name = 'positionMessage'></tr>");
					positionMessage.append("<td>"+
					                            "<div class='ch-checker fl'>"+
					                              "<div class='beau-checker'>"+
					                                "<span class='iconfont icon-gou'></span>"+
					                              "</div>"+
					                              "<input type='checkbox' class='yellow-checker' name='positionId' value='"+positionList[j].positionId+"'/>"+
					                            "</div>"+
					                            "<span class='ml30'>"+positionList[j].positionName+"</span>"+
					                        "</td>"+
					                        "<td>"+
					                            "<span class='ml8 mr5'>上牌方式:</span>"+
					                            "<select name='upShiftType' class='chzn-select input-small'>"+
					                              "<option value='1'>打卡上牌</option>"+
					                              "<option value='0'>手动上牌</option>"+
					                            "</select>"+
					                         "</td>");
					if (j + 1 == positionList.length) {
						positionMessage.append("<td></td><td></td>")
						jQuery("#modelTbody").append(positionMessage);
					}
				}
				else {
					positionMessage.append("<td>"+
					                            "<div class='ch-checker fl'>"+
					                              "<div class='beau-checker'>"+
					                                "<span class='iconfont icon-gou'></span>"+
					                              "</div>"+
					                              "<input type='checkbox' class='yellow-checker' name='positionId' value='"+positionList[j].positionId+"'/>"+
					                            "</div>"+
					                            "<span class='ml30'>"+positionList[j].positionName+"</span>"+
				                           "</td>"+
					                       "<td>"+
					                            "<span class='ml8 mr5'>上牌方式:</span>"+
					                            "<select name='upShiftType' class='chzn-select input-small'>"+
					                              "<option value='1'>打卡上牌</option>"+
					                              "<option value='0'>手动上牌</option>"+
					                            "</select>"+
					                         "</td>");
					jQuery("#modelTbody").append(positionMessage);
				}
			}
			jQuery(".chzn-select").chosen({disable_search_threshold: 10, no_results_text: ""});
			return;
		}
	}
}

function updateDept(obj, deptIds){
	var tabObj = jQuery(obj).parent().find(".tab");
	for (var i = 0; i < tabObj.length; i++) {
		jQuery(tabObj[i]).removeClass("active");
	}
	jQuery(obj).addClass("active");
	deptId = deptIds;
	updatePage();
	
	var obj = jQuery(".s-slider-wrap");
	for (var i = 0; i < obj.length; i++) {
		var num = jQuery(obj[i]).attr("num");
		renewalShift(num, 0);
	}
}

//新增或设置轮牌model
jQuery("#confirm").click(function(){
	
	var shiftMahjongUp = radioValue("shiftMahjongUp");
	var shiftMahjongRule = radioValue("shiftMahjongRule");
	var nature = radioValue("nature");
	var positionIdList = jQuery("input[name='positionId']:checked");
	var positionIdListStr = "";
	for (var i = 0; i < positionIdList.length; i++) {
		if (positionIdListStr == "") {
			positionIdListStr = jQuery(positionIdList[i]).val() + ":" + jQuery(positionIdList[i]).parent().parent().next().find("select[name='upShiftType']").val();
		}
		else {
			positionIdListStr = positionIdListStr + "," + jQuery(positionIdList[i]).val() + ":" + jQuery(positionIdList[i]).parent().parent().next().find("select[name='upShiftType']").val();
		}
	}
	
	var shiftMahjongName = jQuery("input[name='shiftMahjongName']").val();
	if (shiftMahjongName == "") {
		dialog("轮牌名称不能为空！");
		return;
	}
	if (shiftMahjongName.length > 5) {
		dialog("轮牌名称不能超出5个字符！");
		return;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/addUpdateShiftMahjong",
		data : "shiftMahjongId="+shiftMahjongId+"&shiftMahjongName="+shiftMahjongName+"&shiftMahjongUp="+shiftMahjongUp+"&shiftMahjongRule="+shiftMahjongRule+"&deptId="+deptId+"&nature="+nature+"&positionIdListStr="+positionIdListStr,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			updatePage();
			var obj = jQuery(".s-slider-wrap");
			for (var i = 0; i < obj.length; i++) {
				var num = jQuery(obj[i]).attr("num");
				renewalShift(num, 0);
			}
			jQuery('#rotating-setting-modal').modal("hide");
		}
	});
});

//赋值model中redio的值
function valuation(names, values) {
	var nameObj = jQuery("input[name='"+names+"']");
	for (var i = 0; i < nameObj.length; i++) {
		var obj = nameObj[i];
		jQuery(obj).parent().find(".beau-checker").removeClass("active");
		var value = (jQuery(obj).val()).replace(/[^0-9]/ig,""); 
		if (value == values) {
			jQuery(obj).parent().find(".beau-checker").addClass("active");
			jQuery(obj).prop("checked", true);
		}
	}
}

//取model中redio的值
function radioValue(names) {
	var nameObj = jQuery("input[name='"+names+"']:checked").val();
	return nameObj;
}

//更新轮牌页面（按部门）
function updatePage(){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "staff/action/selectshiftMahjong",
		data : "deptId="+deptId,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var shiftMahjongDtoList = e.msg;
			jQuery("#lunpaiDIV").empty();
			for (var i = 0; i < shiftMahjongDtoList.length; i++) {
				var shiftInfo = shiftMahjongDtoList[i];
                var widgetcontent = jQuery("<div class='widgetcontent'></div>")
				var zhiwei = jQuery("<div class='zhiwei-lunpai'></div>");
				zhiwei.append("<div class='zhiwei-name'>"+
						                        "<span class='ml10 name'>"+shiftInfo.shiftMahjongName+"</span>"+
						                        "<div class='lunpai-shezhi fr'>"+
						                            "<i class='iconfont icon-shezhi' onclick=\"setShiftMahjong("+shiftInfo.shiftMahjongId+",'"+shiftInfo.shiftMahjongName+"',"+shiftInfo.nature+","+shiftInfo.shiftMahjongUp+","+shiftInfo.shiftMahjongRule+")\"></i>"+
						                            "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<i class='iconfont iconfa-trash' onclick=\"deleteShiftMahjong(this, "+shiftInfo.shiftMahjongId+")\"></i>"+
						                        "</div>"+
						                    "</div>");
				var shiftObj = jQuery("<div class='s-whole-wrap'></div>");
				shiftObj.append("<div class='s-left-control'>"+
	                             "<span class='iconfont icon-zuobianfangxiang'></span>"+
	                          "</div>");
				var wrapObj = jQuery("<div class='s-slider-wrap' nature = '"+shiftInfo.nature+"' num = '"+i+"'></div>");
				
				var shiftMahjongEmployeeList = shiftMahjongDtoList[i].shiftMahjongEmployeeList;
				
				var employeeObj = infoDIV(shiftInfo.shiftMahjongId,shiftMahjongEmployeeList, i);
				
				wrapObj.append(employeeObj);
				shiftObj.append(wrapObj);
				shiftObj.append("<div class='s-right-control'>"+
	                             "<span class='iconfont icon-youbianfangxiang'></span>"+
	                          "</div>");
				zhiwei.append(shiftObj);
				widgetcontent.append(zhiwei);
				jQuery("#lunpaiDIV").append(widgetcontent);
			}
		}
	});
}

var totailNum = 0;

//员工排位
function infoDIV(shiftMahjongIdDiv,shiftMahjongEmployeeList){
	
	var employeeObj = jQuery("<div class='slider4'></div>");
	totailNum = shiftMahjongEmployeeList.length - 1;
	for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
		var shiftMahjongEmployee = shiftMahjongEmployeeList[j];
		var slideObj = jQuery("<div class='slide slider-part' name='"+j+"'></div>");
		var imgs = picUrl+shiftMahjongEmployee.headImage;
		var sliderTitle = jQuery("<div class='slider-title' shiftMahjongEmployeeId = '"+shiftMahjongEmployee.shiftMahjongEmployeeId+"' shiftMahjongId = '"+shiftMahjongIdDiv+"' shiftMahjongOrder = '"+shiftMahjongEmployee.shiftMahjongOrder+"'></div>");
		if (shiftMahjongEmployee.shiftMahjongOrder == 999) {
			sliderTitle.append("<span class=''>"+shiftMahjongEmployee.shiftMahjongOrder+"</span>");
		}
		else {
			sliderTitle.append("<img src='"+baseUrl+"img/common/left-arraw.png' alt='' onclick= 'upwardIMG(this)'/>"+
			                   "<span class=''>"+shiftMahjongEmployee.shiftMahjongOrder+"</span>"+
			                   "<img src='"+baseUrl+"img/common/right-arraw.png' alt='' onclick= 'nextIMG(this)'/>");
		}
		slideObj.append(sliderTitle);
		slideObj.append("<div class='slider-content'>"+
		                    "<div class='center'>"+
		                        "<img src='"+imgs+"' alt=''/>"+
		                        "<div class='name'>"+shiftMahjongEmployee.employeeCode+" "+ shiftMahjongEmployee.name+"</div>"+
		                    "</div>"+
		                "</div>");
		for (var k = 0; k < stateList.length; k++) {
			if(stateList[k].state == shiftMahjongEmployee.state){
				slideObj.append("<div class='slider-foot' shiftMahjongEmployeeId = '"+shiftMahjongEmployee.shiftMahjongEmployeeId+"' shiftMahjongId = '"+shiftMahjongIdDiv+"'>"+
					               "<div class='center "+stateList[k].styles+"'>"+
				                        "<div class='zhuangtai fl'>"+
				                            "<img src='"+stateList[k].imgs+"' alt='"+stateList[k].name+"'/>"+
				                        "</div>"+
				                        "<div class='zhuangtai-word fl' names = '"+stateList[k].state+"'>"+
				                            ""+stateList[k].name+""+
				                        "</div>"+
				                    "</div>"+
				                "</div>");
			}
		}
		employeeObj.append(slideObj);
	}
	return employeeObj;
}

var moveShiftMahjongEmployeeId = "";

var moveShiftMahjongId = "";

var moveShiftMahjongOrder = "";

var moveObj = "";


function refreshShift(shiftMahjongIds) {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "staff/action/refreshShiftMahjongEmployee",
		data : "shiftMahjongId="+shiftMahjongIds,
		async: false,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
            var date = e.msg;
            
            jQuery(moveObj).empty();
            
            var employeeObj = infoDIV(shiftMahjongIds, date);
			moveObj.empty();
			moveObj.append(employeeObj);
		}
	});
}

//上移
function upwardIMG(obj){
	moveShiftMahjongEmployeeId = jQuery(obj).parents(".slider-title").attr("shiftMahjongEmployeeId");

	moveShiftMahjongId = jQuery(obj).parents(".slider-title").attr("shiftMahjongId");

	moveObj = jQuery(obj).parents(".s-slider-wrap");
	
	refreshShift(moveShiftMahjongId);
	
	moveShiftMahjongOrder = jQuery(moveObj).find(".slider-title[shiftMahjongEmployeeId='"+moveShiftMahjongEmployeeId+"']").attr("shiftMahjongOrder");
	
	if (moveShiftMahjongOrder == "999" || isEmpty(moveShiftMahjongOrder)) {
		dialog("页面信息已失效，请刷新页面！");
		return;
	}
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/upwardIMG",
		data : "shiftMahjongEmployeeId="+moveShiftMahjongEmployeeId+"&shiftMahjongId="+moveShiftMahjongId+"&shiftMahjongOrder="+moveShiftMahjongOrder,
		async:false,//使用同步的Ajax请求 
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var employeeObj = infoDIV(moveShiftMahjongId, e.msg);
			moveObj.empty();
			moveObj.append(employeeObj);
			
			var showNum = jQuery(moveObj).find(".slider-title[shiftMahjongEmployeeId='"+moveShiftMahjongEmployeeId+"']").parents(".slider-part").attr("name");
			showShift(showNum);
		}
	});
}

function showShift(showNum) {
	var num = jQuery(moveObj).attr("num");
	var showObj = jQuery(moveObj).find(".slider4 .slider-part");
	
	var locationNum = 0;
	
	if (showNum == totailNum) {
		locationNum = totailNum - 8;
	}
	else {
		locationNum = showNum - 8;
	}
	renewalShift(num, locationNum);
}

//下移
function nextIMG(obj){
	moveShiftMahjongEmployeeId = jQuery(obj).parents(".slider-title").attr("shiftMahjongEmployeeId");

	moveShiftMahjongId = jQuery(obj).parents(".slider-title").attr("shiftMahjongId");

	moveObj = jQuery(obj).parents(".s-slider-wrap");
	
    refreshShift(moveShiftMahjongId);
	
    moveShiftMahjongOrder = jQuery(moveObj).find(".slider-title[shiftMahjongEmployeeId='"+moveShiftMahjongEmployeeId+"']").attr("shiftMahjongOrder");
	
	if (moveShiftMahjongOrder == "999" || isEmpty(moveShiftMahjongOrder)) {
		dialog("页面信息已失效，请重试！");
		return;
	}
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/nextIMG",
		data : "shiftMahjongEmployeeId="+moveShiftMahjongEmployeeId+"&shiftMahjongId="+moveShiftMahjongId+"&shiftMahjongOrder="+moveShiftMahjongOrder,
		async:false,//使用同步的Ajax请求 
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var employeeObj = infoDIV(moveShiftMahjongId, e.msg);
			moveObj.empty();
			moveObj.append(employeeObj);

			var showNum = jQuery(moveObj).find(".slider-title[shiftMahjongEmployeeId='"+moveShiftMahjongEmployeeId+"']").parents(".slider-part").attr("name");
			showShift(showNum);
		}
	});
}

jQuery("body").delegate(".slider-foot", "click", function () {
    	moveObj = jQuery(this).parents(".s-slider-wrap");
    	
    	moveShiftMahjongEmployeeId = jQuery(this).attr("shiftMahjongEmployeeId");
    	
    	moveShiftMahjongId = jQuery(this).attr("shiftMahjongId");
    	
    	refreshShift(moveShiftMahjongId);
    	
    	var showNum = jQuery(moveObj).find(".slider-title[shiftMahjongEmployeeId='"+moveShiftMahjongEmployeeId+"']").parents(".slider-part").attr("name");
		
    	showShift(showNum);
    	
    	var showType = 0;
    	
    	var parentObj = jQuery(moveObj).find(".slider-foot[shiftMahjongEmployeeId='"+moveShiftMahjongEmployeeId+"']");
    	
    	var state = jQuery(parentObj).find(".zhuangtai-word").text();
		
		if (state == "工作中" || state == "指定服务") {
			return;
		}
    	
    	jQuery(moveObj).find(".slider4").on(transitionEnd, function() {
        	
        	var left = jQuery(parentObj).offset().left + 10;
            var top = jQuery(parentObj).offset().top + 20;
            
            jQuery(".select-zhuangtai").removeClass("hide").css({"left":left, "top":top});
            
            showType = 1;
    	});
    	
    	if (showType == 0) {
        	var left = jQuery(parentObj).offset().left + 10;
            var top = jQuery(parentObj).offset().top + 20;
            
            jQuery(".select-zhuangtai").removeClass("hide").css({"left":left, "top":top});
    	}
});


function transitionEndEventName () {
    var i,
            undefined,
            el = document.createElement('div'),
            transitions = {
                'transition':'transitionend',
                'OTransition':'otransitionend',  // oTransitionEnd in very old Opera
                'MozTransition':'transitionend',
                'WebkitTransition':'webkitTransitionEnd'
            };

    for (i in transitions) {
        if (transitions.hasOwnProperty(i) && el.style[i] !== undefined) {
            return transitions[i];
        }
    }
}

var transitionEnd = transitionEndEventName();

jQuery("html").delegate("body:not('.slider-foot')", "click", function(){
	jQuery(".select-zhuangtai").addClass("hide");
});

jQuery(".select-zhuangtai").click(function(){
	event.stopPropagation();
});





//jQuery("body:not('.select-zhuangtai')").click(function () {
//	jQuery(".select-zhuangtai").addClass("hide");
//});

function updateState(num){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/updateState",
		data : "shiftMahjongEmployeeId="+moveShiftMahjongEmployeeId+"&state="+num,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
            var date = e.msg;
			var employeeObj = infoDIV(date.shiftMahjongId,date.shiftMahjongEmployeeList);

			moveObj.empty();
			moveObj.append(employeeObj);
			
			var showNum = jQuery(moveObj).find(".slider-title[shiftMahjongEmployeeId='"+moveShiftMahjongEmployeeId+"']").parents(".slider-part").attr("name");
			showShift(showNum);
			
			jQuery(".select-zhuangtai").addClass("hide");
		}
	});
}

function deleteShiftMahjong(obj, shiftMahjongId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/deleteShiftMahjong",
		data : "shiftMahjongId="+shiftMahjongId,
		async:false,//使用同步的Ajax请求 
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			jQuery(obj).parents(".widgetcontent").remove();
		}
	});
}

