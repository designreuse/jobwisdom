var  shiftMahjongId = "";

var stateList = new Array();
var a1 = {"state":"0","name":"工作","imgs":baseUrl+"images/emploee_5.png"};
var a2 = {"state":"1","name":"空闲","imgs":baseUrl+"images/emploee_6.png"};
var a3 = {"state":"2","name":"暂休","imgs":baseUrl+"images/emploee_9.png"};
var a4 = {"state":"3","name":"下牌","imgs":baseUrl+"images/emploee_8.png"};
var a5 = {"state":"4","name":"点客","imgs":baseUrl+"images/emploee_7.png"};

stateList[0] = a1;
stateList[1] = a2;
stateList[2] = a3;
stateList[3] = a4;
stateList[4] = a5;

//全局修改状态点击对象
var overallObj = "";

//全局修改状态轮牌员工标识
var shiftMahjongEmployeeId = "";

var now_=0;
var count= 0;

jQuery(document).ready(function(){
	updatePage();
	var obj = jQuery(".s-slider-wrap");
	for (var i = 0; i < obj.length; i++) {
		var num = jQuery(obj[i]).attr("num");
		renewalShift(num, 0);
	}
});

//向右走
jQuery('.right_click').click(function(){
   if(now_<=count-5){
	    now_+=1;
      jQuery(this).parent('').find('.emploee_right_ul').stop(true,true).animate({
	       left:-200*now_
	   
	       }) 
		  }
	  });
//向左走

	//向左走
jQuery('.left_click').click(function(){
   if(now_>=1){
	    now_-=1;
       jQuery(this).parent('').find('.emploee_right_ul').stop(true,true).animate({
	     left:-200*now_
	   
	     }) 
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
jQuery(".emploee_right_ul").delegate("#shiftModel", "click",function(){
	shiftMahjongId = "";
	
	valuation("shiftMahjongRule", 1);
	valuation("shiftMahjongUp", 1);
	valuation("nature", 1);
	jQuery("input[name='shiftMahjongName']").val("");
	
	updatePosition();
	
	jQuery(".zzc").show();
});

function setShiftMahjong(shiftMahjongIdS){
	shiftMahjongId = shiftMahjongIdS;
	
	jQuery(".zzc").show();
	
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
			var obj = e.msg;
			var data = obj.mapList;
			var shiftMahjong = obj.shiftMahjong;
			
			valuation("shiftMahjongRule", shiftMahjong.shiftMahjongRule);
			valuation("shiftMahjongUp", shiftMahjong.shiftMahjongUp);
			valuation("nature", shiftMahjong.nature);
		    
			jQuery("input[name='shiftMahjongName']").val(shiftMahjong.shiftMahjongName);
			updatePosition();
			if(data.length > 0){
				var obj = jQuery("input[name='positionId']");
				for (var j = 0; j < obj.length; j++) {
					for(var i = 0; i < data.length;i++){
						if (jQuery(obj[j]).val() == data[i].positionId) {
							jQuery(obj[j]).prop("checked", true);
							jQuery(obj[j]).parents(".design_").find("select[name='upShiftType']").val(data[i].isPunchCard);
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
	jQuery(".emplee_job_content_").empty();
	
	for (var i = 0; i < deptDtoList.length; i++) {
		if (deptId == deptDtoList[i].deptId) {
			var positionList = deptDtoList[i].positionInfo;
			for (var j = 0; j < positionList.length; j++) {
				var positionMessage = "<span class='design_'>"+
									      "<em>"+
										    "<input type='checkbox' name='positionId' value='"+positionList[j].positionId+"'><span class='design'>"+positionList[j].positionName+" </span> "+
										  "</em>"+
											"<i>上牌方式</i>"+
											 "<select name='upShiftType'>"+
											   "<option value='1'>打卡上牌</option>"+
						                       "<option value='0'>手动上牌</option>"+
											 "</select>"+
										"</span>";
										
				jQuery(".emplee_job_content_").append(positionMessage);
			}
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
			positionIdListStr = jQuery(positionIdList[i]).val() + ":" + jQuery(positionIdList[i]).parents(".design_").find("select[name='upShiftType']").val();
		}
		else {
			positionIdListStr = positionIdListStr + "," + jQuery(positionIdList[i]).val() + ":" + jQuery(positionIdList[i]).parents(".design_").find("select[name='upShiftType']").val();
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
			
			if (isEmpty(shiftMahjongId)) {
				var shiftMahjongIdReturn = e.msg;
				jQuery(".emploee_right_ul").find("li").removeClass("active");
				jQuery(".emploee_right_ul").find("#shiftModel").before("<li class='active' onclick = \"refreshShift("+shiftMahjongIdReturn+", 1)\" shiftMahjongId = '"+shiftMahjongIdReturn+"'>"+
						   "<div class='emploee_right_li'>"+shiftMahjongName+"</div>"+
						   "<span style='border-right:1px solid #ccc' onclick=\"setShiftMahjong("+shiftMahjongIdReturn+")\"><img src='"+ baseUrl +"images/emploee_2.png'></span>"+
						  "<span onclick=\"deleteShiftMahjong(this, "+shiftMahjongIdReturn+")\"><img src='"+baseUrl+"images/emploee_3.png'></span>"+
						"</li>");
				refreshShift(shiftMahjongIdReturn, 1);
			}
			else {
				jQuery(".emploee_right_ul").find(".active").find(".emploee_right_li").text(shiftMahjongName);
				refreshShift(shiftMahjongId, 1);
			}
            
			jQuery('.zzc').hide();
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
			jQuery(".emploee_right_ul").empty();
			
			for (var i = 0; i < shiftMahjongDtoList.length; i++) {
				var shiftInfo = shiftMahjongDtoList[i];
				if (i == 0) {
					jQuery(".emploee_right_ul").append("<li class='active' onclick = \"refreshShift("+shiftInfo.shiftMahjongId+", 1)\" shiftMahjongId = '"+shiftInfo.shiftMahjongId+"'>"+
														   "<div class='emploee_right_li'>"+shiftInfo.shiftMahjongName+"</div>"+
														   "<span style='border-right:1px solid #ccc' onclick=\"setShiftMahjong("+shiftInfo.shiftMahjongId+")\"><img src='"+ baseUrl +"images/emploee_2.png'></span>"+
														  "<span onclick=\"deleteShiftMahjong(this, "+shiftInfo.shiftMahjongId+")\"><img src='"+baseUrl+"images/emploee_3.png'></span>"+
														"</li>");
					var shiftMahjongEmployeeList = shiftMahjongDtoList[i].shiftMahjongEmployeeList;
					
					infoDIV(shiftMahjongEmployeeList);
				}
				else {
					jQuery(".emploee_right_ul").append("<li onclick = \"refreshShift("+shiftInfo.shiftMahjongId+", 1)\" shiftMahjongId = '"+shiftInfo.shiftMahjongId+"'>"+
							   "<div class='emploee_right_li'>"+shiftInfo.shiftMahjongName+"</div>"+
							   "<span style='border-right:1px solid #ccc' onclick=\"setShiftMahjong("+shiftInfo.shiftMahjongId+")\"><img src='"+ baseUrl+"images/emploee_2.png'></span>"+
							  "<span onclick=\"deleteShiftMahjong(this, "+shiftInfo.shiftMahjongId+")\"><img src='"+baseUrl+"images/emploee_3.png'></span>"+
							"</li>");
				}
			}
			jQuery(".emploee_right_ul").append("<li id = 'shiftModel' name ='addemployee' >"+
			        "<img src='"+baseUrl+"images/emploee_1.png'>"+
			   "</li>")
		}
	});
	
	count = jQuery('.emploee_right_ul li').size()
}

function showDoewEmployee() {
	var shiftMahjongIdDown = jQuery(".emploee_right_ul").find(".active").attr("shiftMahjongId");
	refreshShift(shiftMahjongIdDown, 2);
}

jQuery('.adjust_').click(function(){
	var shiftMahjongIdDown = jQuery(".emploee_right_ul").find(".active").attr("shiftMahjongId");
	refreshShift(shiftMahjongIdDown, 1);
    jQuery('.gridly li').addClass('active');
	  jQuery('.adjust_button').show();
	  jQuery(this).hide();
	  jQuery("li[name='addEmployeeState']").remove();
	  jQuery(".free").remove();
	  drag();
	  
  });

function caltUpdate() {
	var shiftMahjongIdDown = jQuery(".emploee_right_ul").find(".active").attr("shiftMahjongId");
	refreshShift(shiftMahjongIdDown, 1);
	jQuery(".adjust_").show();
	jQuery(".adjust_button").hide();
}

//员工排位
function infoDIV(shiftMahjongEmployeeList){
	
	jQuery(".gridly").empty();
	for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
		var shiftMahjongEmployee = shiftMahjongEmployeeList[j];
		var imgs = picUrl+shiftMahjongEmployee.headImage;
		jQuery(".gridly").append("<li class='brick small' shiftMahjongEmployeeId = '"+shiftMahjongEmployee.shiftMahjongEmployeeId+"'>"+
								    "<div class='roll_pic'>"+
									   "<img src='"+imgs+"'>"+
									"</div>"+
									"<p>"+shiftMahjongEmployee.employeeCode+"<em>"+shiftMahjongEmployee.name+"</em></p>"+
									"<div class='state'>"+
									 "<i>"+shiftMahjongEmployee.shiftMahjongOrder+"</i><span class='line_'></span> "+
									 "<em style='position:relative' name = 'chooseStateEM' onclick ='showUpdateState(this, "+shiftMahjongEmployee.state+")'>" +
									      "<span class='select_'> " +
									         "<em><img src='"+stateList[shiftMahjongEmployee.state].imgs+"'></em> " +
									         "<span style='position:relative;left:-5px'>"+stateList[shiftMahjongEmployee.state].name+"</span>" +
									      "</span>" +
									      "<em class='down'>" +
									         "<img src='"+baseUrl+"images/down_.png'>" +
									      "</em>" +
									 "</em>"+
									  "<ul class='free'>"+
									        "<li onclick='updateState("+shiftMahjongEmployee.shiftMahjongEmployeeId+", 1)'>"+
											  "<em><img src='"+baseUrl+"images/emploee_6.png'></em> 空闲"+
											"</li>"+
											 "<li onclick='updateState("+shiftMahjongEmployee.shiftMahjongEmployeeId+", 2)'>"+
											    "<em><img src='"+baseUrl+"images/emploee_9.png'></em> 暂休"+
						                    "</li>"+
											 "<li onclick='updateState("+shiftMahjongEmployee.shiftMahjongEmployeeId+", 3)'>"+
											    "<em><img src='"+baseUrl+"images/emploee_8.png'></em> 下牌"+
						                    "</li>"+
									   "</ul>"+	
									"</div>"+
						          "</li>");
	}
	jQuery(".gridly").append("<li class='brick small' onclick = 'showDoewEmployee()' name = 'addEmployeeState'>"+
		      "<img src='"+baseUrl+"images/emploee_10.png'>"+
          "</li>")
}

function saveUpdateOrder() {
	var objList = jQuery(".gridly").find(".brick");
	var shiftMahjongEmployeeIdList = new Array();
	for (var i = 0; i < objList.length; i++) {
		var shiftMahjongEmployeeId = jQuery(objList[i]).attr("shiftMahjongEmployeeId");
		shiftMahjongEmployeeIdList.push(shiftMahjongEmployeeId);
	}
	var shiftMahjongEmployeeIdListStr = JSON.stringify(shiftMahjongEmployeeIdList);
	var shiftMahjongId = jQuery(".emploee_right_ul").find(".active").attr("shiftMahjongId");
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/updateEmployeeOrder",
		data : "shiftMahjongId="+shiftMahjongId+"&shiftMahjongEmployeeIdListStr="+shiftMahjongEmployeeIdListStr,
		async: false,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
            var date = e.msg;
            infoDIV(date.shiftMahjongEmployeeList);
            jQuery(".adjust_").show();
        	jQuery(".adjust_button").hide();
		}
	});
}

function infoDIVDome(shiftMahjongEmployeeList) {
	jQuery(".emplee_content_2").empty();
	for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
		var shiftMahjongEmployee = shiftMahjongEmployeeList[j];
		jQuery(".emplee_content_2").append("<li shiftMahjongEmployeeId = '"+shiftMahjongEmployee.shiftMahjongEmployeeId+"'>"+shiftMahjongEmployee.employeeCode+" "+shiftMahjongEmployee.name+"</li>");

	}
	jQuery(".zzc1").show();
}

jQuery(".emplee_content_2").delegate("li", "click", function(){
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	if (jQuery(obj).hasClass("active")) {
		jQuery(obj).removeClass("active");
	}
	else {
		jQuery(obj).addClass("active");
	}
})

function showUpdateState(obj, state){
	if (state == 0 || state == 4) {
		dialog("工作状态，无法修改状态！");
		return;
	}
	jQuery(obj).parents('.state').find('.free').show();
}


var moveShiftMahjongEmployeeId = "";

var moveShiftMahjongId = "";

var moveShiftMahjongOrder = "";

var moveObj = "";


function refreshShift(shiftMahjongIds, stateType) {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "staff/action/refreshShiftMahjongEmployee",
		data : "shiftMahjongId="+shiftMahjongIds+"&stateType="+stateType,
		async: false,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
            var date = e.msg;
            if(stateType == 1) {
            	infoDIV(date);
            }            
            else {
            	infoDIVDome(date);
            }
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

/*jQuery("body").delegate(".slider-foot", "click", function () {
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
});*/


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

jQuery("html").delegate("*", "click", function(event){
	if (jQuery(this).parents("em[name='chooseStateEM']").attr("name") != "chooseStateEM") {
		jQuery(".free").hide();
	}
	event.stopPropagation();
});



function upStateEmployee() {
	var chooseObjList = jQuery(".emplee_content_2").find(".active");
	var shiftMahjongEmployeeIdList = new Array();
	for (var i = 0; i < chooseObjList.length; i++) {
		shiftMahjongEmployeeIdList.push(jQuery(chooseObjList[i]).attr("shiftmahjongemployeeid"));
	}
	var shiftMahjongEmployeeIdListStr = JSON.stringify(shiftMahjongEmployeeIdList);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/updateStateUp",
		data : "shiftMahjongEmployeeIdListStr="+shiftMahjongEmployeeIdListStr,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
            var date = e.msg;
			infoDIV(date.shiftMahjongEmployeeList);
			jQuery(".zzc1").hide();
		}
	});
}




function updateState(shiftMahjongEmployeeId, num){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/updateState",
		data : "shiftMahjongEmployeeId="+shiftMahjongEmployeeId+"&state="+num,
		async:false,//使用同步的Ajax请求  
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
            var date = e.msg;
			infoDIV(date.shiftMahjongEmployeeList);
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
			jQuery(".emploee_right_ul").find(".active").remove();
			var objList = jQuery(".emploee_right_ul").find("li");
			if (jQuery(objList[0]).attr("name") != "addemployee") {
				jQuery(objList[0]).addClass("active");
				refreshShift(jQuery(objList[0]).attr("shiftmahjongid"), 1)
			}
			
		}
	});
	event.stopPropagation();
}

