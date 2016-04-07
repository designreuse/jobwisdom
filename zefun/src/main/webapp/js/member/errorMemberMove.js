/** 余额迁移 */
function balanceAmountMove(type, id, obj) {
	jQuery("input[name='memberId']").eq(0).val("0");
	/** 展示数据-原系统 */
	var phone = jQuery(obj).parents("tr").children("td").eq(0).text();
	var name = jQuery(obj).parents("tr").children("td").eq(1).text();
	var sex = jQuery(obj).parents("tr").children("td").eq(2).text();
	var levelNum = jQuery(obj).parents("tr").children("td").eq(3).text();
	var balanceAmount = jQuery(obj).parents("tr").children("td").eq(5).text();
	if(type==6){balanceAmount = jQuery(obj).parents("tr").children("td").eq(4).text();}
	jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(0).html('<span>会员姓名：</span>'+name);
	jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(1).html('<span>电话号码：</span>'+phone);
	jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(2).html('<span>会员性别：</span>'+sex);
	jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(3).html('<span>会员卡号：</span>'+levelNum);
	jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(4).html('<span>储值余额：</span>'+balanceAmount);
	if(type == 1){
		var balanceIntegral = jQuery(obj).parents("tr").children("td").eq(13).text();
		jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(5).html('<span>积分余额：</span>'+balanceIntegral).show();
	}
	if(type == 3){
		var balanceIntegral = jQuery(obj).parents("tr").children("td").eq(8).text();
		var balanceGiftmoneyAmount = jQuery(obj).parents("tr").children("td").eq(6).text();
		jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(5).html('<span>积分余额：</span>'+balanceIntegral).show();
		jQuery("#yueqianyi").find(".xitong-main").eq(0).find("li").eq(6).html('<span>礼金余额：</span>'+balanceGiftmoneyAmount).show();
	}
	jQuery("#yueqianyi").modal();
	var data = "id="+id+"&type="+type;
	jQuery("#balandMove").on("click", function(){
		var memberId = jQuery("input[name='memberId']").eq(0).val();
		if (memberId == 0){
			dialog("请查询一个会员进行迁移");
			return;
		}
		data = data+"&memberId="+memberId;
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/move/baland/info",
			data : data,
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					dialog(e.msg);
					if(type == 1){
//						jQuery("#err_"+id).children("td").last().prev().prev().remove();
						jQuery("#err_"+id).children("td").last().prev().remove();
						jQuery("#err_"+id).children("td").last().remove();
//						jQuery("#err_"+id).append('<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(1, '+memberId+',this);"></i></td>');
						jQuery("#err_"+id).append('<td>已处理</td>');
						jQuery("#err_"+id).append('<td>已处理</td>');
					}else{
//						jQuery("#err_"+id).children("td").last().prev().remove();
						jQuery("#err_"+id).children("td").last().remove();
//						jQuery("#err_"+id).append('<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(1, '+memberId+',this);"></i></td>');
						jQuery("#err_"+id).append('<td>已处理</td>');
					}
//					jQuery("#err_"+id).hide();
				}else{
					
				}
				jQuery("#yueqianyi").modal('hide');
			}
		});
	});
}

/** 取消绑定事件 */
jQuery('#yueqianyi').on('hidden.bs.modal', function (){
	jQuery("#yueqianyi").find(".xitong-main").eq(1).find("li").eq(0).html('<span>会员姓名：</span> 暂无');
	jQuery("#yueqianyi").find(".xitong-main").eq(1).find("li").eq(1).html('<span>电话号码：</span> 暂无');
	jQuery("#yueqianyi").find(".xitong-main").eq(1).find("li").eq(2).html('<span>会员性别：</span> 暂无');
	jQuery("#yueqianyi").find(".xitong-main").eq(1).find("li").eq(3).html('<span>会员卡号：</span> 暂无');
	jQuery("#balandMove").unbind("click");
});
/** 取消绑定事件 */
jQuery('#taocanqianyi').on('hidden.bs.modal', function (){
	jQuery("#taocanqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(0).html('<span>会员姓名：</span> 暂无');
	jQuery("#comboMove").unbind("click");
});

function changeToMemberInfo(obj){
	/** 展示数据-智放系统 */
	var phone = jQuery(obj).prev().val();
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if (!reg.test(phone)) {
		dialog("号码格式有误~");
		return;
	}
	var data = "phone="+phone;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMemberByPhone",
		data : data,
		dataType : "json",
		success : function(e) {
			if (e.code != 0) {
				dialog(e.msg);
				return;
			}else{
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(0).html('<span>会员姓名：</span>'+e.msg.name);
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(1).html('<span>电话号码：</span>'+e.msg.phone);
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(2).html('<span>电话性别：</span>'+e.msg.sex);
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(3).html('<span>会员等级：</span>'+e.msg.levelName);
				jQuery("#yueqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(4).html('<span>当前余额：</span>'+e.msg.balanceAmount);
				jQuery("input[name='memberId']").eq(0).val(e.msg.memberId);
			}
		}
	});
}


/** 套餐迁移
 * type 1:盛传数据,2:待定
 * moveType 1:异常会员迁移,2:疗程卡迁移
 *  */
function comboMove(type, id, obj, moveType){
	jQuery("input[name='memberId']").eq(1).val("0");
	/** 展示数据-原系统 */
	var phone;
	var name;
	var sex;
	var levelNum;
	var desc;
	if (moveType == 1){
		phone = jQuery(obj).parents("tr").children("td").eq(0).text();
		name = jQuery(obj).parents("tr").children("td").eq(1).text();
		sex = jQuery(obj).parents("tr").children("td").eq(2).text();
		levelNum = jQuery(obj).parents("tr").children("td").eq(3).text();
	}else if (moveType == 2){
		phone = jQuery(obj).parents("tr").children("td").eq(0).text();
		name = jQuery(obj).parents("tr").children("td").eq(1).text();
		sex = "暂无";
		levelNum = jQuery(obj).parents("tr").children("td").eq(2).text();
		desc = jQuery(obj).parents("tr").children("td").eq(5).text();
	}
	jQuery("#taocanqianyi").find(".xitong-main").eq(0).find("li").eq(0).html('<span>会员姓名:</span>'+name);
	jQuery("#taocanqianyi").find(".xitong-main").eq(0).find("li").eq(1).html('<span>电话号码:</span>'+phone);
	jQuery("#taocanqianyi").find(".xitong-main").eq(0).find("li").eq(2).html('<span>会员性别:</span>'+sex);
	jQuery("#taocanqianyi").find(".xitong-main").eq(0).find("li").eq(3).html('<span>会员卡号:</span>'+levelNum);
	jQuery("#taocanqianyi").find(".xitong-main").eq(0).find("li").eq(4).html('<span>项目描述:</span>'+desc);
	jQuery("#taocanqianyi").modal();
	var data = "";
	jQuery("#comboMove").on("click", function(){
		data = "id="+id+"&type="+type+"&moveType="+moveType;
		var memberId = jQuery("input[name='memberId']").eq(1).val();
		if (memberId == 0){
			dialog("请查询一个会员进行迁移");
			data = "";
			return;
		}
		data = data+"&memberId="+memberId;
		var overdueTime = jQuery("input[name='overdueTime']").val();
		data = data +"&overdueTime="+overdueTime;
		for (var i = 0; i < jQuery("input[name='projectCount']").length; i++) {
			if(jQuery("input[name='projectCount']").eq(i).next("input[name='projectCounts']").val()<jQuery("input[name='projectCount']").eq(i).val()){
				dialog("设置的次数不可大于套餐中次数");
				data = "";
				return;
			}
			data = data + "&projectCount="+jQuery("input[name='projectCount']").eq(i).val();
		}
		var comboId = jQuery("select[name='comboId']").val();
		if(comboId == "0"){
			dialog("请选择一个套餐进行迁移");
			data = "";
			return;
		}
		data = data + "&comboId="+comboId;
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/move/combo/info",
			data : data,
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					dialog(e.msg);
					if(type == 1&&moveType==1){
						jQuery("#err_"+id).children("td").last().prev().prev().remove();
						jQuery("#err_"+id).children("td").last().prev().remove();
						jQuery("#err_"+id).children("td").last().remove();
						jQuery("#err_"+id).append('<td>已处理</td>');
						jQuery("#err_"+id).append('<td>已处理</td>');
						/*jQuery("#err_"+id).append('<td>已处理</td>');*/
					}else if(type == 1&&moveType==2){
						jQuery("#course_"+id).remove();
						/*jQuery("#course_"+id).children("td").last().prev().prev().remove();
						jQuery("#course_"+id).children("td").last().prev().remove();
						jQuery("#course_"+id).children("td").last().remove();
						jQuery("#course_"+id).append('<td>已处理</td>');
						jQuery("#course_"+id).append('<td>已处理</td>');
						jQuery("#course_"+id).append('<td>已处理</td>');*/
					}
					else{
						jQuery("#err_"+id).children("td").last().prev().remove();
						jQuery("#err_"+id).children("td").last().remove();
						jQuery("#err_"+id).append('<td>已处理</td>');
						jQuery("#err_"+id).append('<td>已处理</td>');
					}
				}else{
					dialog(e.msg);
					return;
				}
				jQuery("#taocanqianyi").modal('hide');
			}
		});
	});
}

function comboPhone(obj){
	/** 展示数据-智放系统 */
	var phone = jQuery(obj).prev().val();
	var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
	if (!reg.test(phone)) {
		dialog("号码格式有误~");
		return;
	}
	var data = "phone="+phone;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/action/selectMemberByPhone",
		data : data,
		dataType : "json",
		success : function(e) {
			if (e.code != 0) {
				dialog(e.msg);
				return;
			}else{
				jQuery("#taocanqianyi").find(".xitong-main").eq(1).children("ul").find("li").eq(0).html('<span>会员姓名:</span>'+e.msg.name);
				jQuery("input[name='memberId']").eq(1).val(e.msg.memberId);
			}
		}
	});
}

/**查询套餐信息*/
function changeCombo(obj){
	jQuery("input[name='projectCount']").parent().remove();
	var comboId = jQuery(obj).val();
	if(comboId == "0"){
		jQuery("input[name='projectCount']").parent("li").remove();
		return;
	}
	jQuery.ajax({
        cache: true,
        type: "GET",
        async: false,
        url: baseUrl+"comboInfo/queryComboInfoById?comboId="+comboId,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
        	var comboProjectList = data.msg.comboProjectList;
        	for (var i = 0; i < comboProjectList.length; i++) {
        		var projectName = comboProjectList[i].projectName;
        		var str = '<li><span>项目名称: </span>&nbsp;'+projectName.substring(0, 5)+'...<span>剩余次数 : </span>&nbsp;<input type="number" value="'+comboProjectList[i].projectCount+'" name="projectCount" class="input40" ><input type="hidden" name="projectCounts" value="'+comboProjectList[i].projectCount+'" class="input40" ></li>';
        		jQuery("input[name='overdueTime']").parent().before(str);
			}
    	}
    });
}

jQuery('.timePickerClean').datetimepicker({
	lang:'ch',
	timepicker:false,
	format:'Y-m-d'
});
