//新增保持
function saveType(type) {
	var types = '.zzc' + type; // 获取class属性
	if (type == 2) {
		types = '.zzc';
	}
	var name = jQuery(types + ' [name =name]').val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/type/add",
		data : JSON.stringify({"type" : type,"isDeleted" : 0,"name" : name,"types" : 1}),
		dataType : "json",
		contentType : "application/json",
		success : function(e) {
			jQuery(types).hide();
			var incometypeId = e.msg.incometypeId;
			var name = e.msg.name;
			var type = e.msg.type;
			var html = '<div class="write_delete" id = ' + incometypeId + '>'
					+ '<div class="write_delete_mask" style="display: none;">'
					+ '<span onclick="showincome( ' + "'" + name + "'" + ' ,1,'
					+ incometypeId + ' )" value="' + incometypeId+ '">编辑</span>' 
					+ '<span class="delete1" onclick="deleted('
					+ incometypeId + ', this)" value="' + incometypeId
					+ '">删除</span>' + '</div>' + '<em>' + name + '</em>'
					+ '</div>';
			jQuery("#" + type).append(jQuery(html));
			dialog("新增成功");
			jQuery('.write_delete').hover(function() {
				jQuery(this).find('.write_delete_mask').show()

			}, function() {
				jQuery(this).find('.write_delete_mask').hide()

			})
		}
	});
}

// 点击编辑按钮
function showincome(name, type, id) {

	if (type == 1) { // 收入修改
		jQuery("input[name='id']").val(id);
		jQuery('.zzc3').show();
		jQuery('#13').val(name);
	} else { // 支出修改
		jQuery("input[name='ids']").val(id);
		jQuery('.zzc2').show();
		jQuery('#12').val(name);
	}
}
// 删除
function deleted(id, span) {
	if (confirm("您确定要删除吗？")) {
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/type/add",
			data : JSON.stringify({"incometypeId" : id,"isDeleted" : 1,"types" : 2}),
			dataType : "json",
			contentType : "application/json",
			success : function(e) {
				jQuery(span).parents(".write_delete").hide();
				dialog("删除成功");
			}
		});
	}
}
// 修改保存
function updated(type, obj) {
	var id;
	var types;
	if (type == 2) {
		id = jQuery("input[name='ids']").val();
		types = ".zzc2";
	} else {
		id = jQuery("input[name='id']").val();
		types = ".zzc3";
		type = 1;// 收入方式
	}
	var name = jQuery(types + ' [name =name]').val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "KeepAccounts/type/add",
		data : JSON.stringify({"type" : type,"name" : name,"incometypeId" : id,"isDeleted" : 0,"types" : 2}),
		dataType : "json",
		contentType : "application/json",
		success : function(e) {
			jQuery(types).hide();
			var names = e.msg.name;
			jQuery('div [id=' + id + '] em').text(names);
			dialog("修改成功");
		}
	});
}
