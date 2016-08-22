function changeCategory (obj) {
	var categoryId = jQuery(obj).val();
	jQuery("div[name='batchOne']").empty();
	
	for (var i = 0; i < projectCategoryDtoList.length; i++) {
		var projectCategoryDto = projectCategoryDtoList[i];
		if (categoryId == projectCategoryDto.categoryId) {
			var projectInfos = projectCategoryDto.projectInfo;
			for (var j = 0; j < projectInfos.length; j++) {
				var projectInfo = projectInfos[j];
				var chooseObj = jQuery("div[name='batchTwo']").find("div[projectid='"+projectInfo.projectId+"']");
				if (isEmpty(jQuery(chooseObj).attr("class"))) {
					jQuery("div[name='batchOne']").append('<div class="batch_set_1_content_datail" onclick = "chooseProject(this, '+projectInfo.projectId+', \''+projectInfo.projectName+'\','+projectInfo.projectPrice+', '+projectInfo.costPrice+')">'+
							   '<div class="item_name">'+
							     '<p>'+projectInfo.projectName+'</p>'+
								'<span>¥'+projectInfo.projectPrice+'</span><em>成本¥'+projectInfo.costPrice+'</em>'+
							   '</div>'+
						   '</div>');
				}
			}
		}
	}
}

function chooseProject (obj, projectId, projectName, projectPrice, costPrice) {
	jQuery(obj).remove();
	jQuery("div[name='batchTwo']").append('<div class="batch_set_1_content_datail" projectId = "'+projectId+'">'+
											   '<div class="item_name">'+
											     '<img src="'+baseUrl+'images/setting_close.png" onclick = "deleteProject(this, '+projectId+', \''+projectName+'\','+projectPrice+', '+costPrice+')">'+
											     '<p>'+projectName+'</p>'+
												'<span>¥'+projectPrice+'</span><em>成本¥'+costPrice+'</em>'+
											   '</div>'+
										  '</div>');
}

function deleteProject (obj, projectId, projectName, projectPrice, costPrice) {
	jQuery(obj).parents(".batch_set_1_content_datail").remove();
	jQuery("div[name='batchOne']").append('<div class="batch_set_1_content_datail" onclick = "chooseProject(this, '+projectId+', \''+projectName+'\','+projectPrice+', '+costPrice+')">'+
											   '<div class="item_name">'+
											     '<p>'+projectName+'</p>'+
												'<span>¥'+projectPrice+'</span><em>成本¥'+costPrice+'</em>'+
											   '</div>'+
										   '</div>');
}

function choosePosition (obj, positionId, positionName) {
	jQuery(obj).remove();
	jQuery(".selected_job_content").append('<div class="selected_job_content_detail" positionId = "'+positionId+'">'+
										     '<img src="'+baseUrl+'images/setting_close.png" onclick = "deletePosition(this, '+positionId+', \''+positionName+'\')">'+
										     '<p>'+positionName+'</p>'+
										    '<div class="selected_way">'+
											   '<p><span>业绩方式</span><select name="calculateSelect"><option value="2" checked="checked">固定</option><option value="1">比例</option></select></p>'+
											   '<p><span>业绩值</span><input type="text"  onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" name="calculateInput" placeholder="0"><em>元</em></p>'+
											 '</div>'+
										  '</div>')
}

function deletePosition (obj, positionId, positionName) {
	jQuery(obj).parents(".selected_job_content_detail").remove();
	jQuery(".job").append('<li onclick = "choosePosition(this, '+positionId+', \''+positionName+'\')">'+positionName+'</li>');
}

jQuery("input[name='positionCheck']").click(function () {
	if (jQuery(this).attr("checkType") == 1) {
		jQuery(this).parents(".batch_set_4_content").find("input[type='checkbox']").prop("checked",false);
		jQuery(this).attr("checkType", "0");
	}
	else {
		jQuery(this).parents(".batch_set_4_content").find("input[type='checkbox']").prop("checked",true);
		jQuery(this).attr("checkType", "1");
	}
})

function confirmLevelChoose () {
	/*var commissionWay = jQuery("select[name='commissionWay']").val();*/
	var type = "元 ";
	/*if (commissionWay == 1) {
		type = "%";
	}*/
	var hand = '<img src="'+baseUrl+'images/setting_close.png" onclick = "deleteLevel(this)">'+
			   '<ul class="clearfix">';
	
	var end = '</ul>'+  
			    '<div class="batch_set_5_job_ul">'+ 
			      '<ul class="clearfix">'+ 
					  '<li>'+ 
					    '<p>现金</p>'+ 
					    '<div class="batch_set_5_job_ul_li">'+ 
						  '<p><input type="text" name = "commissionCash" onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" placeholder="0"><em>'+type+'</em><span>指定</span></p>'+ 
						  '<p><input type="text" name = "commissionNoCash" onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" placeholder="0"><em>'+type+'</em><span>非指定</span></p>'+ 
						'</div>'+ 
					  '</li>'+ 
					   '<li>'+ 
					    '<p>卡金</p>'+ 
					    '<div class="batch_set_5_job_ul_li">'+ 
						  '<p><input type="text" name = "commissionCourse" onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" placeholder="0"><em>'+type+'</em><span>指定</span></p>'+ 
						  '<p><input type="text" name = "commissionNoCourse" onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" placeholder="0"><em>'+type+'</em><span>非指定</span></p>'+ 
						'</div>'+ 
					  '</li>'+ 
					    '<li>'+ 
					    '<p>疗程</p>'+ 
					    '<div class="batch_set_5_job_ul_li">'+ 
						  '<p><input type="text" name = "commissionGold" onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" placeholder="0"><em>'+type+'</em><span>指定</span></p>'+ 
						  '<p><input type="text" name = "commissionNoGold" onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" placeholder="0"><em>'+type+'</em><span>非指定</span></p>'+ 
						'</div>'+ 
					  '</li>'+ 
					'</ul>'+ 
			    '</div>';
	var positionIds = "";
	var levelCheckList = jQuery("input[name='levelCheck']:checked");
	var str = "";
	for (var i = 0; i < levelCheckList.length; i++) {
		var obj = levelCheckList[i];
		var levelId = jQuery(obj).attr("levelId");
		var levelName = jQuery(obj).attr("levelName");
		var positionId = jQuery(obj).parents(".batch_set_4_content").find("input[name='positionCheck']").attr("positionId");
		if (positionIds != positionId) {
			if (i != 0) {
				var page = "";
				if (jQuery("input[name='positionCheck']").eq(0).attr("positionId") == positionIds){
					page = hand + str + end + '<p class="get_way">提成方式：<select name="commissionWay"><option value="2" checked="checked">固定</option><option value="1">比例</option></select>预约奖励：<input type="text" name = "commissionCard" onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" placeholder="0"><em>元</em></p>';
				}
				else {
					page = hand + str + end + '<p class="get_way">提成方式：<select name="commissionWay"><option value="2" checked="checked">固定</option><option value="1">比例</option></select></p>';
				}
				var batchSetJob = jQuery('<div class="batch_set_5_job"></div>');
				batchSetJob.append(page);
				jQuery(".batch_set_5_content").append(batchSetJob);
			}
			positionIds  = positionId;
			var positionName = jQuery(obj).parents(".batch_set_4_content").find("input[name='positionCheck']").parent().find("span").text();
			str = '<li>'+positionName+'</li><li><span name = "levelId" levelId = "'+levelId+'">'+levelName+'</span></li>';
			if (i + 1 == levelCheckList.length) {
				var page = "";
				if (jQuery("input[name='positionCheck']").eq(0).attr("positionId") == positionIds){
					page = hand + str + end + '<p class="get_way">提成方式：<select name="commissionWay"><option value="2" checked="checked">固定</option><option value="1">比例</option></select>预约奖励：<input type="text" name = "commissionCard" onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" placeholder="0"><em>元</em></p>';
				}
				else {
					page = hand + str + end + '<p class="get_way">提成方式：<select name="commissionWay"><option value="2" checked="checked">固定</option><option value="1">比例</option></select></p>';
				}
				var batchSetJob = jQuery('<div class="batch_set_5_job"></div>');
				batchSetJob.append(page);
				jQuery(".batch_set_5_content").append(batchSetJob);
			}
		}
		else {
			str += '<li><span name = "levelId" levelId = "'+levelId+'">'+levelName+'</span></li>';
			if (i + 1 == levelCheckList.length) {
				var page = "";
				if (jQuery("input[name='positionCheck']").eq(0).attr("positionId") == positionIds){
					page = hand + str + end + '<p class="get_way">提成方式：<select name="commissionWay"><option value="2" checked="checked">固定</option><option value="1">比例</option></select>预约奖励：<input type="text" name = "commissionCard" onkeyup="this.value=this.value.replace(/[^0-9]/g,\'\')" onafterpaste="this.value=this.value.replace(/[^0-9]/g,\'\')" placeholder="0"><em>元</em></p>';
				}
				else {
					page = hand + str + end + '<p class="get_way">提成方式：<select name="commissionWay"><option value="2" checked="checked">固定</option><option value="1">比例</option></select></p>';
				}
				var batchSetJob = jQuery('<div class="batch_set_5_job"></div>');
				batchSetJob.append(page);
				jQuery(".batch_set_5_content").append(batchSetJob);
			}
		}
		jQuery(obj).parent().hide();
		jQuery(obj).prop("checked",false);
	}
	
	jQuery("input[name='positionCheck']").prop("checked",false);
}

function deleteLevel (obj) {
	jQuery(obj).parents(".batch_set_5_job").find("span[name='levelId']").each(function(){ 
		var levelId = jQuery(this).attr("levelId");
		jQuery("input[levelId='"+levelId+"']").parent().show();
	})
	jQuery(obj).parents(".batch_set_5_job").remove();
}

jQuery("body").delegate("select[name='commissionWay']", "click", function () {
	var type = jQuery(this).val();
	var val = "元";
	if (type == 1) {
		val = "%";
	}
	jQuery(this).parents(".batch_set_5_job").find("em").text(val);
})

jQuery("body").delegate("select[name='calculateSelect']", "click", function () {
	var type = jQuery(this).val();
	var val = "元";
	if (type == 1) {
		val = "%";
	}
	jQuery(this).parents(".selected_way").find("em").text(val);
})

/**
 * 业绩设置
 */
function batchSetCalculate () {
	var projectIdList = new Array();
	jQuery("div[name='batchTwo']").find(".batch_set_1_content_datail").each(function(){ 
		var projectId = jQuery(this).attr("projectId");
		projectIdList.push(projectId);
	})
	
	if (projectIdList.length == 0) {
		dialog("请选择项目");
		return;
	}
	
	var calculateList = new Array();
	jQuery(".selected_job_content_detail").each(function(){
		var positionId = jQuery(this).attr("positionid");
		var stepPerformanceType = jQuery(this).find("select[name='calculateSelect']").val();
		var stepPerformance = jQuery(this).find("input[name='calculateInput']").val();
		
		if (isEmpty(stepPerformance)) {
			stepPerformance = 0;
		}
		
		calculateList.push({"positionId" : positionId, "stepPerformanceType" : stepPerformanceType, "stepPerformance" : stepPerformance});
	})
	
	if (calculateList.length == 0) {
		dialog("请选择需设置业绩的岗位");
		return;
	}
	
	jQuery.ajax({
    	url : baseUrl + "batchSet/action/batchSetCalculate",
    	type : "POST",
    	data : "projectIdListStr=" + JSON.stringify(projectIdList) + "&calculateListStr=" + JSON.stringify(calculateList),
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		dialog("批量设置岗位业绩成功！");
    		jQuery(".selected_job_content_detail").find("img").click();
    	}
    });
}

/**
 * 提成设置
 */
function batchSetCommission () {
	var projectIdList = new Array();
	jQuery("div[name='batchTwo']").find(".batch_set_1_content_datail").each(function(){ 
		var projectId = jQuery(this).attr("projectId");
		projectIdList.push(projectId);
	})
	
	if (projectIdList.length == 0) {
		dialog("请选择项目");
		return;
	}
	
	var commissionList = new Array();
	jQuery(".batch_set_5_job").each(function(){
		var assignCashType = jQuery(this).find("select[name='commissionWay']").val();
		
		var commissionCash = setZero(jQuery(this).find("input[name='commissionCash']").val());
		var commissionNoCash = setZero(jQuery(this).find("input[name='commissionNoCash']").val());
		var commissionCourse = setZero(jQuery(this).find("input[name='commissionCourse']").val());
		var commissionNoCourse = setZero(jQuery(this).find("input[name='commissionNoCourse']").val());
		var commissionGold = setZero(jQuery(this).find("input[name='commissionGold']").val());
		var commissionNoGold = setZero(jQuery(this).find("input[name='commissionNoGold']").val());
		var commissionCard = setZero(jQuery(this).find("input[name='commissionCard']").val());
		
		
		var levelIdList = new Array();
		jQuery(this).find("span[name='levelId']").each(function(){
			var levelId = jQuery(this).attr("levelId");
			levelIdList.push(levelId);
		})
		
		commissionList.push({"assignCashType" : assignCashType, "commissionCash" : commissionCash, "commissionNoCash" : commissionNoCash, 
			"commissionCourse" : commissionCourse, "commissionNoCourse" : commissionNoCourse, 
			"commissionGold" : commissionGold, "commissionNoGold" : commissionNoGold, "commissionCard" : commissionCard, "levelIdList" : levelIdList});
	})
	
	if (commissionList.length == 0) {
		dialog("请选择需设置提成的职位");
		return;
	}
	
	jQuery.ajax({
    	url : baseUrl + "batchSet/action/batchSetCommission",
    	type : "POST",
    	data : "projectIdListStr=" + JSON.stringify(projectIdList) + "&commissionListStr=" + JSON.stringify(commissionList),
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		dialog("批量设置职位提成成功！");
    		jQuery(".batch_set_5_job").find("img").click();
    	}
    });
}

function setZero (val) {
	if (isEmpty(val)) {
		val = 0;
	}
	return val;
}