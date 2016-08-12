function changeCategory (obj) {
	var categoryId = jQuery(obj).val();
	for (var i = 0; i < projectCategoryDtoList.length; i++) {
		var projectCategoryDto = projectCategoryDtoList[i];
		if (categoryId = projectCategoryDto.projectCategoryDto) {
			var projectInfos = projectCategoryDto.projectInfo;
			for (var j = 0; j < projectInfos.length; j++) {
				var projectInfo = projectInfos[j];
				jQuery("div[name='batchOne']").append('<div class="batch_set_1_content_datail">'+
														   '<div class="item_name">'+
														     '<p>'+projectInfo.projectName+'</p>'+
															'<span>¥'+projectInfo.projectPrice+'</span><em>成本¥'+projectInfo.costPrice+'</em>'+
														   '</div>'+
													   '</div>');
			}
		}
	}
}