//停用业务员
function deactivateSalesman(salesmanId) {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "/salesman/action/deactivate",
		data : "salesmanId=" + salesmanId,
		dataType : "json",
		success : function(e){
			dialog(e.msg);
			if(e.code != 0){
				return;
			}
			window.location.reload();
		}
	});
}

//删除业务员
function deleteSalesman(salesmanId) {
	jQuery.ajax({
		type : "post",
		url : baseUrl + "/salesman/action/delete",
		data : "salesmanId=" + salesmanId,
		dataType : "json",
		success : function(e){
			dialog(e.msg);
			if(e.code != 0){
				return;
			}
			window.location.reload();
		}
	});
}

