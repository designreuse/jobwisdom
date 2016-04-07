var selectStoresId = "";//选择的分店id
var isFlag = true;//标记，一个类别保存后，才可以新增另一个类别
var tempStoresList = new Array();

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
})

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

function createStroeLi(store){
	var str = "<li value="+store.storesId+" name=" + store.storesName + ">";
	str += ' <input type="checkbox" class="check-input"/>';
	str += " <span></span>";
	str += " <span class='company-name'>"+store.storesName+"</span>";
	str += " </li>";
    return str;
}


/*【返回】指定会员  美发设置页面*/
function returnProject(){
	 window.location.href = basePath+"project/toProjectPage";
}

/*新增设置*/
function addSetClass(obj){
	if(isFlag){
		var len = $("form[name='projectForm']").length;
		var objHtml = $("#menbersHtml").html();
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

/*会员等级 新增 一行*/
function addMembersLevel(obj){
	var trEl = $("<tr></tr>");
	trEl.append("<td scope='row'></td>" +
				"<td class='name'>会员等级</td>");
	
	var tdPosition = $("<td class='item-content'></td>");
	var selectEl = $("<select name='vipLevelId'></select>");
	
	$.ajax({
		url : basePath + "project/vipLevelList",
		dataType : "json",
		async: false,
		success : function(data){
			for(v in data){
				var cardNameId = data[v].cardNameId;
				var cardName = data[v].cardName;
//				console.log("vipLevelId:"+vipLevelId+",levelName:"+levelName);
				selectEl.append("<option value='"+cardNameId+"'>"+cardName+"</option>");
			}
		}
	});
	
	tdPosition.append(selectEl);
	tdPosition.append("<input class='discount assign' type='text' placeholder='提成' name='discount'/>");
	tdPosition.append("<input class='money assign' type='text' placeholder='金额' name='amount'/>");
	tdPosition.append("<div class='plus'><img src='"+basePath+"app/img/head/plus.png' alt='' style='background-color:#bbb;' onclick='addMembersLevel(this);' /></div");
	trEl.append(tdPosition);
	trEl.append("<td class='delete-operation'><img src='"+basePath+"app/img/delete.png' alt='' onclick='deleteMembersLevel(this);' /></td>");
	
	$(obj).parent().parent().parent(":last").after(trEl);
}

/*会员等级 删除 tr*/
function deleteMembersLevel(obj){
	$(obj).parent().parent().remove();
}

/*【确认】保存会员等级类别*/
function saveMembers(obj){
	var formData = $(obj).parent().parent().parent().parent().parent().serialize();
	$.ajax({
        cache: true,
        type: "POST",
        url: basePath+"project/saveMembers",
        data: formData,// 你的formid
        async: false,
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
            if(data.code == 0){
            	alert("分店设置会员保存成功");
            	
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
		$(thisObj).find(".plus img").attr("onclick","addMembersLevel(this);");
		$(thisObj).find(".delete-operation img").attr("onclick","deleteMembersLevel(this);");
		
		$(obj).parent().replaceWith("<td colspan='4' class='button-group'>" +
				"<button class='cancel btn' type='button' onclick='canncelClass(this,false);'>取消</button>" +
				"<button class='affirm btn' type='button' onclick='saveMembers(this);'>保存</button></td>");
		
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
    	$(thisObj).find("img").removeAttr("onclick");
    	
    	$(obj).parent().replaceWith("<td colspan='4' class='button-group'>" +
    			"<button class='cancel btn' type='button' onclick='canncelClass(this,true);'>删除</button>" +
    			"<button class='affirm btn' type='button' onclick='updateClasses(this);'>修改</button></td>");
	}
	
	isFlag = true;
}

/*【删除】删除类别*/
function deleteClass(obj){
	var vipLevelIdSet = $(obj).parent().parent().parent().parent().parent().find("input[name='vipLevelIdSet']").val();
	
//	var formData = $(obj).parent().parent().parent().parent().parent().serialize();
	var selectStoresId = $(obj).parent().parent().parent().parent().parent().find("input[name='selectStoresId']").val();
	var vipLevelIds = $(obj).parent().parent().parent().find("select[name='vipLevelId'] option:selected");
	var projectInfoIds = $(obj).parent().parent().parent().parent().parent().find("input[name='projectInfoIdCheck']");
	var vipLevelIdStr = "";
	var projectInfoIdStr = "";
	for(var i=0;i<vipLevelIds.length;i++){
		vipLevelIdStr += $(vipLevelIds[i]).val() + ",";
	}
	for(var i=0;i<projectInfoIds.length;i++){
		projectInfoIdStr += $(projectInfoIds[i]).val() + ",";
	}
	console.log("selectStoresId="+selectStoresId+",vipLevelIdStr:"+vipLevelIdStr+",projectInfoIdStr="+projectInfoIdStr);
	if(vipLevelIdStr != "" && projectInfoIdStr != ""){
		$.ajax({
			url : basePath + "project/deleteMembersLevel",
			dataType : "json",
			data: "selectStoresId="+selectStoresId+"&vipLevelIds="+vipLevelIdStr+"&projectInfoIds="+projectInfoIdStr,
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
		//$(obj).parent().parent().parent().parent().parent().remove();
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
$("#divid").delegate("input[name='discount']","blur",function(){
	console.log("val:"+$(this).val());
	if($(this).val() != ""){
		$(this).parent().find("input[name='amount']").val("/");
	}
});
/*指定 - 金额*/
$("#divid").delegate("input[name='amount']","blur",function(){
	console.log("val:"+$(this).val());
	if($(this).val() != ""){
		$(this).parent().find("input[name='discount']").val("/");
	}
});
