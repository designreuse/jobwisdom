var selectStoresId = "";//选择的分店id
var isFlag = true;//标记，一个类别保存后，才可以新增另一个类别
var tempStoresList = new Array();
var tempPositoinList = new Array();
$(function(){
	if(2 == $("#isValue").val()){
		isFlag = false;
	}
	if(null != storesList){
		loadTagInput();
		//下拉事件触发，添加选择内容
		$(".select-tag").delegate("li","click",function(){
			$(this).parent().parent().find('[name="selectStoresId"]').tagsinput('add',  { id: $(this).attr("value"), text: $(this).attr("name") });
			selectStoresId = $(this).parent().parent().find('[name="selectStoresId"]').val();
			
			$(this).find("input").attr("checked","true");
		});
	}
	
	loadPositionSelect();
});

function loadTagInput(value){
	$(".select-tag").empty();
	for (var i = 0; i < storesList.length; i++) {
		var str = createStroeLi(storesList[i]);
		$(".select-tag").append(str);
	}
	//初始化taginput
	$('[name="selectStoresId"]').tagsinput({
		trimValue: true,
	    itemValue: 'id',
	    itemText: 'text'
//	    typeaheadjs: {
//    	    source: substringMatcher("1,2")
//    	},
//    	freeInput: true
	});
	
	$('[name="selectStoresId"]').on('itemRemoved', function(event) {
		var id = event.item.id;
		$(".select-tag").find("li[value="+id+"]").find("input").removeAttr("checked");
	});
}

function removeSelectStores(ids){
	var idArray = ids.split(",");
	for (var i = 0; i < idArray.length; i++) {
		removeStoresListById(idArray[i]);
	}
}

function removeStoresListById(id){
	for (var i = 0; i < storesList.length; i++) {
		var stores = storesList[i];
		if(stores.storesId == id){
			tempStoresList.push(stores);
			storesList.removeByIndex(i);
			$(".select-tag").find("li[value="+id+"]").remove();
			break;
		}
	}
}

/*创建门店列表*/
function createStroeLi(store){
	var str = "<li value="+store.storesId+" name=" + store.storesName + ">";
	str += ' <input type="checkbox" class="check-input"/>';
	str += " <span></span>";
	str += " <span class='company-name'>"+store.storesName+"</span>";
	str += " </li>";
    return str;
}

/*创建职位列表*/
function createPositonSelect(position){
//	console.log("positionId:"+position.positionId+",positionName:"+position.positionName);
	var str =  "<option value='"+position.positionId+"'	>"+position.positionName+"</option>";
	return str;
}
/*加载职位列表数据*/
function loadPositionSelect(){
	//获取到未添加元素的select对象，对其进行初始化
	var selectList = $(".positionId");
	var initSelctObj = null;
	for (var i = 0; i < selectList.length; i++) {
		if(selectList[i].options.length == 0){
			initSelctObj = selectList[i];
		}
	}
	
	if(initSelctObj == null) return;
	
	//获取当前下拉框所有选中的值
	var selectArray = $("option:selected");
	var selectMap = {};//存储所有下拉框选择的值，key为option的value，vaiue为option的text
	for (var i = 0; i < selectArray.length; i++) {
		var selectObj = selectArray[i];
		selectMap[selectObj.value] = selectObj.text;
	}
	//对未添加元素的select进行初始化
	for (var j = 0; j < positionList.length; j++) {
		//如果数据已经被其他下拉框选择，直接跳过不添加
		if(selectMap[positionList[j].positionId] != null){
			continue;
		}
		var str = createPositonSelect(positionList[j]);
		$(initSelctObj).append(str);
	}
}

function selectObjByPosition(id){
	for (var i = 0; i < positionList.length; i++) {
		var position = positionList[i];
		if(position.positionId == id){
			tempPositoinList.push(position);
			positionList.removeByIndex(i);
			$(".positionId").find("option[value="+id+"]").remove();
			break;
		}
	}
}

function unSelectObjByPosition(id){
	for (var i = 0; i < tempPositoinList.length; i++) {
		var position = tempPositoinList[i];
		if(position.positionId == id){
			positionList.push(position);
			tempPositoinList.removeByIndex(i);
			var str = createStroeLi(position);
			$(".select-tag").append(str);
			break;
		}
	}
}

/*
function unSelectObjByStores(id){
	for (var i = 0; i < tempStoresList.length; i++) {
		var stores = tempStoresList[i];
		if(stores.storesId == id){
			storesList.push(stores);
			tempStoresList.removeByIndex(i);
			var str = createStroeLi(stores);
			$(".select-tag").append(str);
			break;
		}
	}
}*/


/*指定员工 返回 美发设置页面*/
function returnProject(){
	 window.location.href = basePath+"project/toProjectPage";
}

/*新增设置*/
function addSetClass(obj){
	if(isFlag){
		var len = $("form[name='projectForm']").length;
		var objHtml = $("#storesHtml").html();
		if(len > 0){
			var lastCategoryId = $($("form[name='projectForm']")[len-1]).find("input[name='categoryName']").val();
			var code = lastCategoryId.charCodeAt();
			var next = String.fromCharCode(code + 1);
			objHtml = objHtml.replaceAll("{typeName}",next);
			$(".content-center").append(objHtml);
		}else{
			objHtml = objHtml.replaceAll("{typeName}","A");
			$(".content-center").append(objHtml);
		}
		loadTagInput();
		isFlag = false;
	}else{
		alert("先保存当前类别，再新增");
	}
}

/*职位名称 新增 tr*/
function addPosition(obj){
	var trEl = $("<tr></tr>");
	trEl.append("<td scope='row'></td>" +
				"<td class='name'>职位名称</td>");
	
	var tdPosition = $("<td class='item-content'></td>");
	var selectEl = $("<select class='positionId' onchange='positionChange(this)' name='positionId'></select>");
//	$.ajax({
//		url : basePath + "getPositionList",
//		dataType : "json",
//		async: false,
//		success : function(data){
//			for(pos in data){
//				var pId = data[pos].positionId;
//				var positionName = data[pos].positionName;
////				console.log("pId:"+pId+",positionName:"+positionName);
//				selectEl.append("<option selected='selected' value='"+pId+"'>"+positionName+"</option>");
//			}
//		}
//	});
	
	tdPosition.append(selectEl);
	tdPosition.append("<span>指定</span><input class='discount assign' type='text' placeholder='提成' name='discountAssign'/>");
	tdPosition.append("<input class='money assign' type='text' placeholder='金额' name='amountAssign'/>");
	tdPosition.append("<span>非指定</span><input class='discount assign' type='text' placeholder='提成' name='discount'/>");
	tdPosition.append("<input class='money assign' type='text' placeholder='金额' name='amount'/>");
	tdPosition.append("<div class='plus'><img src='"+basePath+"app/img/head/plus.png' alt='' style='background-color:#bbb;' onclick='addPosition(this);' /></div>");
	trEl.append(tdPosition);
	trEl.append("<td class='delete-operation'><img src='"+basePath+"app/img/delete.png' alt='' onclick='deletePosition(this);' /></td>");
	$(obj).parent().parent().parent(":last").after(trEl);
	
	loadPositionSelect();
}

/*职位名称 删除 tr*/
function deletePosition(obj){
	$(obj).parent().parent().remove();
}

/*【确认】 保存类别*/
function saveProject(obj){
	var formData = $(obj).parent().parent().parent().parent().parent().serialize();
	$.ajax({
        cache: true,
        type: "POST",
        url: basePath+"project/addProjectCommission",
        data: formData,// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	alert("分店员工设置提成保存成功");
            	
            	var thisObj = $(obj).parent().parent().parent().parent().parent();
            	$(thisObj).find("input").attr("disabled","disabled");
            	$(thisObj).find("select").attr("disabled","disabled");
            	$(thisObj).find("img").removeAttr("onclick");
            	$(obj).parent().replaceWith("<td colspan='4' class='button-group'>" +
            			"<button class='cancel btn' type='button' onclick='deleteClass(this);'>删除</button>" +
            			"<button class='affirm btn' type='button' onclick='updateClasses(this);'>修改</button></td>");
            	
            	removeSelectStores(selectStoresId);
            	isFlag = true;
            	
            }else{
            	alert("error");
            }
        }
    });
}

function updateClasses(obj){
	if(isFlag){
		var thisObj = $(obj).parent().parent().parent().parent().parent();
		$(thisObj).find("input").removeAttr("disabled");
		$(thisObj).find("select").removeAttr("disabled");
		$(thisObj).find(".plus img").attr("onclick","addPosition(this);");
		$(thisObj).find(".delete-operation img").attr("onclick","deletePosition(this);");
		
		$(obj).parent().replaceWith("<td colspan='4' class='button-group'>" +
				"<button class='cancel btn' type='button' onclick='canncelClass(this,false);'>取消</button>" +
				"<button class='affirm btn' type='button' onclick='saveProject(this);'>保存</button></td>");
		
		isFlag = false;
	}
}


/*【取消】 取消编辑*/
function canncelClass(obj,isCanncel){
	if(isCanncel){
		$(obj).parent().parent().parent().parent().parent().remove();
	}else{
		var thisObj = $(obj).parent().parent().parent().parent().parent();
    	$(thisObj).find("input").attr("disabled","disabled");
    	$(thisObj).find("select").attr("disabled","disabled");
    	
    	$(obj).parent().replaceWith("<td colspan='4' class='button-group'>" +
    			"<button class='cancel btn' type='button' onclick='canncelClass(this,true);'>删除</button>" +
    			"<button class='affirm btn' type='button' onclick='updateClasses(this);'>修改</button></td>");
	}
	
	isFlag = true;
}

/*【删除】 删除类别*/
function deleteClass(obj){
//	var formData = $(obj).parent().parent().parent().parent().parent().serialize();
	
	var positionIds = $(obj).parent().parent().parent().find("select[name='positionId'] option:selected");
	var projectInfoIds = $(obj).parent().parent().parent().parent().parent().find("input[name='projectInfoIdCheck']");
	var projectNos = $(obj).parent().parent().parent().parent().parent().find("input[name='projectNo']");
	var positionIdStr = "";
	var projectInfoIdStr = "";
	var projectNoStr = "";
	for(var i=0;i<positionIds.length;i++){
		positionIdStr += $(positionIds[i]).val() + ",";
	}
	for(var i=0;i<projectInfoIds.length;i++){
		projectInfoIdStr += $(projectInfoIds[i]).val() + ",";
		projectNoStr += $(projectNos[i]).val() + ",";
	}
	var selectStoresId = $(obj).parent().parent().parent().parent().parent().find("input[name='selectStoresId']").val();
	
//	console.log("positionIdStr:"+positionIdStr+",selectStoresId:"+selectStoresId+",projectInfoIdStr:"+projectInfoIdStr+",projectNoStr:"+projectNoStr);
	
	if(positionIdStr != "" && selectStoresId != "" && projectInfoIdStr != ""){
		$.ajax({
			url : basePath + "project/deleteEmpCommission",
			dataType : "json",
			data: "positionId="+positionIdStr+"&selectStoresId="+selectStoresId+"&projectInfoIdCheck="+projectInfoIdStr+"&projectNo="+projectNoStr,
			success : function(data){
				if(data.code == 0){
					$(obj).parent().parent().parent().parent().parent().remove();
				}else{
					alert("error");
				}
			}
		});
	}else{
		alert("数据不能为空");
	}
}

/*显示或隐藏类别*/
$("#divid").delegate(".down-operation","click",function(){
    var parent = $(this).parent().parent();
    if(parent.hasClass("open")){
        parent.removeClass("open");
    }else{
        parent.addClass("open");
    }
});

/*显示门店下拉列表*/
$("#divid").delegate(".item-input","click",function(){
	var th = $(this);
    if(!th.hasClass("open")){
        th.addClass("open");
    }else{
        th.removeClass("open");
    }
});

/*指定 - 提成*/
$("#divid").delegate("input[name='discountAssign']","blur",function(){
	console.log("val:"+$(this).val());
	if($(this).val() != ""){
		$(this).parent().find("input[name='amountAssign']").val("/");
	}
});
/*指定 - 金额*/
$("#divid").delegate("input[name='amountAssign']","blur",function(){
	console.log("val:"+$(this).val());
	if($(this).val() != ""){
		$(this).parent().find("input[name='discountAssign']").val("/");
	}
});
/*非指定 - 提成*/
$("#divid").delegate("input[name='discount']","blur",function(){
	console.log("val:"+$(this).val());
	if($(this).val() != ""){
		$(this).parent().find("input[name='amount']").val("/");
	}
});
/*非指定 - 金额*/
$("#divid").delegate("input[name='amount']","blur",function(){
	console.log("val:"+$(this).val());
	if($(this).val() != ""){
		$(this).parent().find("input[name='discount']").val("/");
	}
});


function textTrim(str){   
	return str.replace(/^(\s|\u00A0)+/,'').replace(/(\s|\u00A0)+$/,'');   
}

