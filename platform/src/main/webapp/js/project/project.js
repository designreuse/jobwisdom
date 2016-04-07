$(function(){
	
    $('.demo2').Tabs({
        event:'click'
    });
    
});

/*添加项目类别*/
function addClasses(obj){
	
	var trEl = $('<tr class="modification"></tr>');
	var tdEl = $('<td><input type="text" style="width: 150px" name="classesName"/></td>'+
				'<td class="alter"><input type="button" class="btn btn-empty" value="取消" onclick="deleteClasses(-1,this);"/></td>'+
				'<td class="delete"><input type="button" class="btn btn-blue" value="确定" onclick="saveClasses(this);" style="margin-left: 3px;margin-right: 9px;border: 1px solid #3997f3;"/></td>');
	
//	var tdEl = $('<td class="file"><img src="'+basePath +'app/img/file.png" alt=""/></td>'+
//			'<td class="name"><input type="text" name="classesName" onblur="saveClasses(this);"/></td>'+
//			'<td class="alter"><img src="'+basePath +'app/img/alter.png" alt=""/></td>'+
//			'<td class="delete"><img src="'+basePath +'app/img/delete.png" alt="" onclick="deleteClasses(-1,this);"/></td>');
	trEl.append(tdEl);
	$(obj).parent().parent().parent().find("tbody").append(trEl);
}

/*保存项目类别*/
function saveClasses(obj){
	var plateId = $('#plateId').find(".current").val();
	var classesName = $(obj).parent().parent().find("input[name='classesName']").val();
	if(classesName != ""){
		$.ajax({
			type: "POST",
			contentType: "application/x-www-form-urlencoded; charset=utf-8", 
			url : basePath + "project/addProjectClasses",
			dataType : "json",
			data: "plateId="+plateId+"&classesName="+classesName,
			success : function(data){
				if(data.code == 0){
					window.location.href = basePath+"project/toProjectPage";
				}
			}
		});
	}else{
		alert("类别名称不能为空");
	}
}

/*点击编辑图标 把文字替换成输入框*/
function editInput(classesId,classesName,obj){
	var trEl = $('<tr class="modification"></tr>');
	var tdEl = $('<td><input type="text" style="width: 150px" name="classesName"/ value="'+classesName+'"></td>'+
				'<td class="alter"><input type="button" class="btn btn-empty" value="取消" onclick="cancelClasses();"/></td>'+
				'<td class="delete"><input type="button" class="btn btn-blue" value="确定" onclick="editClasses('+classesId+',this);" style="margin-left: 3px;margin-right: 9px;border: 1px solid #3997f3;"/></td>');
	trEl.append(tdEl);
	$(obj).parent().parent().replaceWith(trEl);//'<input type="text" name="classesName" value="'+classesName+'" onblur="editClasses('+classesId+',this);"/>'
}

function cancelClasses(){
	window.location.href = basePath+"project/toProjectPage";
}

/*项目类别 编辑 保存*/
function editClasses(classesId,obj){
//	var classesName = $(obj).val();
	var classesName = $(obj).parent().parent().find("input[name='classesName']").val();
	console.log(classesName);
	if(classesName != ""){
		$.ajax({
			type: "POST",
			url : basePath + "project/addProjectClasses",
			dataType : "json",
			data: "classesId="+classesId+"&classesName="+classesName,
			success : function(data){
				if(data.code == 0){
					window.location.href = basePath+"project/toProjectPage";
				}
			}
		});
	}else{
		alert("请输入类别名称");
	}
}

/*删除项目类别*/
function deleteClasses(classesId,obj){
	if(classesId != "-1"){
		$.ajax({
			url : basePath + "project/deleteProjectClasses?classesId="+classesId,
		 	dataType : "json",
		 	success : function(data){
		 		if(data.code == 0){
		 			window.location.href = basePath + "project/toProjectPage";
		 		}
		 	}
		});
	}else{
		$(obj).parent().parent().remove();
	}
}

/*添加项目*/
function addProject(){
	var trEl = $("<tr></tr>");
	trEl.append("<td><input type='text' name='projectNo' /></td>"+
				"<td><input type='text' name='projectName' /></td>"+
				"<td><input type='text' name='projectPrice'/></td>"+ //onblur='saveProject(this)'
				"<td><img src='"+basePath+"app/img/true.png' alt='' class='img-left modification' onclick='saveProject(this)'/></td>"+
				"<td><img onclick='deleteProject(-1,this)' src='"+basePath+"app/img/delete.png' alt=''/></td>");
	
	var addBtn = $(".add-project").find("tbody tr:last-child");
	console.log(addBtn.html() == undefined);
	if(addBtn.html() == undefined){
		$(".add-project").find("thead tr:last-child").after(trEl);
	}else{
		$(".add-project").find("tbody tr:last-child").after(trEl);
	}
}


/* 点击编辑图标 把文字替换成输入框*/
function editProject(obj){
	var projectInfoId =  $(obj).parent().parent().find(".projectInfoId").val();
	var projectNo = $(obj).parent().parent().find(".projectNo").html();
	var projectName = $(obj).parent().parent().find(".projectName").html();
	var projectPrice = $(obj).parent().parent().find(".projectPrice").html();
	/*
	var trEl = $("<tr></tr>");
	trEl.append("<td><input type='text' name='projectNo' value='"+projectNo+"'/><input type='hidden' name='projectInfoId' value='"+projectInfoId+"'/></td>");
	trEl.append("<td><input type='text' name='projectName' value='"+projectName+"'/></td>");
	trEl.append("<td><input type='text' name='projectPrice' value='"+projectPrice+"'/></td>");
	trEl.append("<td><img src='"+basePath+"app/img/true.png' alt='' class='img-left modification' " +
				"onclick='editProjectSave(this)'/>");
	$(obj).parent().parent().replaceWith(trEl);
	*/
	$("#create-update-project").find("input[name='projectInfoId']").val(projectInfoId);
	$("#create-update-project").find("input[name='projectNo']").val(projectNo);
	$("#create-update-project").find("input[name='projectName']").val(projectName);
	$("#create-update-project").find("input[name='projectPrice']").val(projectPrice);
	
	$("#create-update-project").modal();
	
}

/*编辑项目 提交*/
function editProjectSave(obj){
	/*
	var projectInfoId = $(obj).parent().parent().find("input[name='projectInfoId']").val();
	var projectNo = $(obj).parent().parent().find("input[name='projectNo']").val();
	var projectName = $(obj).parent().parent().find("input[name='projectName']").val();
	var projectPrice = $(obj).parent().parent().find("input[name='projectPrice']").val();
	*/
	$.ajax({
        cache: true,
        type: "POST",
        url: basePath+"project/editProject",
        data: $("form[name='projectUpdateForm']").serialize(),// 你的formid
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
           if(data.code == 0){
        	   window.location.href = basePath + "project/toProjectPage";
           }else{
        	   alert("error");
           }
        }
    });
	/*
	$.ajax({
		url : basePath + "project/editProject",
		type: "POST",
		dataType : "json",
		data: "projectInfoId="+projectInfoId+"&projectNo="+projectNo+"&projectName="+projectName+"&projectPrice="+projectPrice,
		success : function(data){
			if(data.code == 0){
				window.location.href = basePath + "project/toProjectPage";
			}else{
				alert("error");
			}
		}
	});*/
}

/*删除项目*/
function deleteProject(projectInfoId,obj){
	$.ajax({
		url : basePath + "project/queryProjectIdIsSet?projectInfoId="+projectInfoId,
		dataType : "json",
		success : function(data){
			if(data.code == 0){
				var msg = data.msg;
				if(msg == "true"){
					 if(confirm("该项目有设置分店数据，确定要删除数据吗")){
						 $.ajax({
								url : basePath + "project/deleteProject?projectInfoId="+projectInfoId,
								dataType : "json",
								success : function(data){
									$(obj).parent().parent().remove();
								}
							});
					 }
				}else{
					$.ajax({
						url : basePath + "project/deleteProject?projectInfoId="+projectInfoId,
						dataType : "json",
						success : function(data){
							$(obj).parent().parent().remove();
						}
					});
				}
				//window.location.href = basePath + "project/toProjectPage";
			}else{
				alert("error");
			}
		}
	});
}


/* 批量保存项目信息 */
function saveProjectBatch(obj){
	//project/validProjectNo
	var projectNo = $(obj).parent().parent().find("input[name='projectNo']").val();
	console.log("projectNo:"+projectNo);
	$.ajax({
        cache: true,
        url: basePath+"project/validProjectNo?projectNo="+projectNo,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
           if(data.code == 0){
        	   if(data.msg == "error"){
        		   alert("项目编号已经存在，请更改");
        	   }else{
        		   $.ajax({
        		        cache: true,
        		        type: "POST",
        		        url: basePath+"project/addProjectBatch",
        		        data: $("form[name='projectAddForm']").serialize(),// 你的formid
        		        error: function(request) {
        		            alert("Connection error");
        		        },
        		        success: function(data) {
        		           if(data.code == 0){
        		        	   window.location.href = basePath + "project/toProjectPage";
        		           }else{
        		        	   alert("error");
        		           }
        		        }
        		    });
        	   }
           }else{
        	   alert("error");
           }
        }
    });
	
	
}

/*查询项目*/
function queryProject(classesId,obj){
	window.location.href = basePath + "project/toProjectPage?classesId="+classesId;
}

/*指定员工页面*/
function specifiedEmp(projectInfoId){
	window.location.href = basePath+"project/toProjectRelation?projectInfoId="+projectInfoId;
}

/*指定会员页面*/
function specifiedMembers(projectInfoId){
	window.location.href = basePath+"project/toProjectMembers?projectInfoId="+projectInfoId;
}

/*批量设置员工提成*/
function employeeBatchSet(){
	var r = document.getElementsByName("projectInfoId-Check");  
	var projectInfoIds = "";
	for(var i=0;i<r.length;i++){
	   if(r[i].checked){
	    	 projectInfoIds += r[i].value + ",";
	   }
	}
	window.location.href = basePath+"project/toProjectRelation?projectInfoId="+projectInfoIds;
}

/*批量设置会员折扣*/
function membersBatchSet(){
	var r = document.getElementsByName("projectInfoId-Check");  
	var projectInfoIds = "";
	for(var i=0;i<r.length;i++){
	   if(r[i].checked){
	    	 projectInfoIds += r[i].value + ",";
	   }
	}
	window.location.href = basePath+"project/toProjectMembers?projectInfoId="+projectInfoIds;
}


function selectAll(form) {
	 var obj = document.getElementsByName('projectInfoId-Check');
	 var cks = document.getElementsByTagName("input");
	 var ckslen = cks.length;
	 for(var i=0;i<ckslen-1;i++) {
	  if(cks[i].type == 'checkbox') {
	   cks[i].checked = obj[0].checked;
	  }
	 }
}

