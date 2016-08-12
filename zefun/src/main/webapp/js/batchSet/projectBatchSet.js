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
											   '<p><span>业绩方式</span><select name="calculateSelect"><option value="2">固定</option><option value="1">比例</option></select></p>'+
											   '<p><span>业绩值</span><input type="text" name="calculateInput"><em>元</em></p>'+
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
	var commissionWay = jQuery("select[name='commissionWay']").val();
	var type = "元 ";
	if (commissionWay == 1) {
		type = "%";
	}
	var str = '<div class="batch_set_5_job">'+
					'<img src="'+baseUrl+'images/setting_close.png" onclick = "deleteLevel(this)">'+
				    '<ul class="clearfix">'+
					    '<li>职位</li>';
	jQuery("input[name='levelCheck']").each(function(){ 
		if (jQuery(this).prop("checked")) {
			var levelId = jQuery(this).attr("levelId");
			var levelName = jQuery(this).attr("levelName");
			str += '<li><span name = "levelId" levelId = "'+levelId+'">'+levelName+'</span></li>';
			
			jQuery(this).parent().hide();
			jQuery(this).prop("checked",false);
		}
	})
	
	str += '</ul>'+  
			    '<div class="batch_set_5_job_ul">'+ 
			      '<ul class="clearfix">'+ 
					  '<li>'+ 
					    '<p>现金</p>'+ 
					    '<div class="batch_set_5_job_ul_li">'+ 
						  '<p><input type="text"><em>'+type+'</em><span>指定</span></p>'+ 
						  '<p><input type="text"><em>'+type+'</em><span>非指定</span></p>'+ 
						'</div>'+ 
					  '</li>'+ 
					   '<li>'+ 
					    '<p>现金</p>'+ 
					    '<div class="batch_set_5_job_ul_li">'+ 
						  '<p><input type="text"><em>'+type+'</em><span>指定</span></p>'+ 
						  '<p><input type="text"><em>'+type+'</em><span>非指定</span></p>'+ 
						'</div>'+ 
					  '</li>'+ 
					    '<li>'+ 
					    '<p>现金</p>'+ 
					    '<div class="batch_set_5_job_ul_li">'+ 
						  '<p><input type="text"><em>'+type+'</em><span>指定</span></p>'+ 
						  '<p><input type="text"><em>'+type+'</em><span>非指定</span></p>'+ 
						'</div>'+ 
					  '</li>'+ 
					'</ul>'+ 
			    '</div>'+ 
			 '</div>';
	
	jQuery(".batch_set_5_content").append(str);
	
	jQuery("input[name='positionCheck']").prop("checked",false);
}

function deleteLevel (obj) {
	jQuery("span[name='levelId']").each(function(){ 
		var levelId = jQuery(this).attr("levelId");
		jQuery("input[levelId='"+levelId+"']").parent().show();
	})
	jQuery(obj).parents(".batch_set_5_job").remove();
}

jQuery("body").delegate("select[name='calculateSelect']", "click", function () {
	var type = jQuery(this).val();
	var val = "元";
	if (type == 1) {
		val = "%";
	}
	jQuery(this).parents(".selected_way").find("em").text(val);
})