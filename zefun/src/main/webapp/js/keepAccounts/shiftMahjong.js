var  shiftMahjongId = "";

var stateList = new Array();
var a1 = {"state":"0","name":"工作","imgs":baseUrl+"images/emploee_5.png", "brick" : "brick1", "state": "state1"};
var a2 = {"state":"1","name":"空闲","imgs":baseUrl+"images/emploee_6.png", "brick" : "brick2", "state": "state2"};
var a3 = {"state":"2","name":"暂休","imgs":baseUrl+"images/emploee_9.png", "brick" : "brick4", "state": "state4"};
var a4 = {"state":"3","name":"下牌","imgs":baseUrl+"images/emploee_8.png", "brick" : "brick", "state": "state"};
var a5 = {"state":"4","name":"点客","imgs":baseUrl+"images/emploee_7.png", "brick" : "brick3", "state": "state3"};

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

jQuery(document).ready(function(){
	for (var i = 0; i < shiftMahjongDtoList.length; i++) {
		var shiftInfo = shiftMahjongDtoList[i];
		if (i == 0) {
			jQuery(".emploee_right_ul").append("<li class='active' onclick = \"refreshShift("+shiftInfo.shiftMahjongId+", 1)\" shiftMahjongId = '"+shiftInfo.shiftMahjongId+"'>"+shiftInfo.shiftMahjongName+"</li>");
			var shiftMahjongEmployeeList = shiftMahjongDtoList[i].shiftMahjongEmployeeList;
			infoDIV(shiftMahjongEmployeeList);
		}
		else {
			jQuery(".emploee_right_ul").append("<li onclick = \"refreshShift("+shiftInfo.shiftMahjongId+", 1)\" shiftMahjongId = '"+shiftInfo.shiftMahjongId+"'>"+shiftInfo.shiftMahjongName+"</li>");
		}
	}
	
	if (isEmpty(shiftMahjongDtoList)) {
		jQuery(".gridly").empty();
	}
	count=jQuery('.emploee_right_ul li').size();
	jQuery('.emploee_right_ul').css('width',count*200);
});


function showDoewEmployee() {
	var shiftMahjongIdDown = jQuery(".emploee_right_ul").find(".active").attr("shiftMahjongId");
	refreshShift(shiftMahjongIdDown, 2);
}

function canclezzc () {
	jQuery("div[name='zzcDIV']").hide();
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
	jQuery( ".gridly" ).sortable( 'destroy');
	jQuery(".adjust_button").hide();
}

//员工排位
function infoDIV(shiftMahjongEmployeeList){
	
	jQuery(".gridly").empty();
	for (var j = 0; j < shiftMahjongEmployeeList.length; j++) {
		var shiftMahjongEmployee = shiftMahjongEmployeeList[j];
		var imgs = picUrl+shiftMahjongEmployee.headImage;
		jQuery(".gridly").append("<li class='brick small "+stateList[shiftMahjongEmployee.state].brick+"'  shiftMahjongEmployeeId = '"+shiftMahjongEmployee.shiftMahjongEmployeeId+"'>"+
								    "<div class='roll_pic'>"+
									   "<img src='"+imgs+"'>"+
									"</div>"+
									"<p>"+shiftMahjongEmployee.employeeCode+"<em>"+shiftMahjongEmployee.name+"</em></p>"+
									"<div class='state "+stateList[shiftMahjongEmployee.state].state+"'>"+
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
		      "<img src='"+baseUrl+"images/emploee_10.png' style='width:130px;height:152px;position:relative;top:-16px'>"+
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
        	jQuery( ".gridly" ).sortable( 'destroy');
        	dialog("调整顺序成功！");
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

