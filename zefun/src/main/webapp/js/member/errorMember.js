//上一页
function previous() {
	if (pageNo <= 1) {
		return;
	}
	pageNo--;
	changePage();
}
// 下一页
function next() {
	if (pageNo >= totalPage) {
		return;
	}
	pageNo++;
	changePage();
}
var isPhone = false;
// 更改每页显示数量
function changePageSize(size) {
	pageNo = 1;
	pageSize = size;
	jQuery(".perpage").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePage();
}
// 分页处理
function changePage() {
	var content = jQuery("#serchMemberByNameOrPhone").val();
	var data;
	if (content!=null&&content!=""){
		var data = "pageNo=" + pageNo + "&pageSize=" + pageSize + "&content="+content;
	}else {
		var data = "pageNo=" + pageNo + "&pageSize=" + pageSize;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/view/error/member/info",
		data : data,
		dataType : "json",
		success : function(e) {
			if (e.code == 0) {
				jQuery(".perpage").text(e.msg.pageSize);
				totalPage = e.msg.page.totalPage;
				jQuery("#init_member").empty();
				totalRecord = e.msg.page.totalRecord;
				initTable(e.msg.page.results, lastFacilitator);
			}
		}
	});
}

//根据姓名或者电话进行查询
function serchMemberByNameOrPhoneDoc(){
	isPhone = true;
	var content = jQuery("#serchMemberByNameOrPhone").val();
	pageNo = 1;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/view/error/member/info",
		data : "pageNo=" +pageNo+ "&pageSize=" + pageSize+"&content="+content,
		dataType : "json",
		success : function(e) {
			if (e.code == 0) {
				jQuery(".perpage").text(e.msg.pageSize);
				totalPage = e.msg.page.totalPage;
				jQuery("#init_member").empty();
				initTable(e.msg.page.results, lastFacilitator);
				totalRecord = e.msg.page.totalRecord;
				if(isPhone){
					unbuildPagination();
					isPhone = false;
				}
			}
		}
	});
}

//删除会员数据
var errMemberType = null;
var errMemberId = null;
function deleteErrMemberTip(type, id){
	errMemberType = type;
	errMemberId = id;
	jQuery("#deleteErrorMemberModel").modal('show');
}

//删除错误会员数据
function deleteErrMember(){
	jQuery("#deleteErrorMemberModel").modal('hide');
	if(isEmpty(errMemberType) || isEmpty(errMemberId)){
		dialog("请刷新重试！");
		return;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/view/error/member/delete",
		data : "type=" + errMemberType + "&id=" +errMemberId,
		dataType : "json",
		success : function(e) {
			if (e.code != 0) {
				dialog(e.msg);
				return;
			}
			else {
				jQuery("#err_" + errMemberId).fadeOut(500).remove();
//				if(errMemberType == 1){
//					jQuery("#err_"+errMemberId).children("td").last().prev().prev().remove();
//					jQuery("#err_"+errMemberId).children("td").last().prev().remove();
//					jQuery("#err_"+errMemberId).children("td").last().remove();
//					jQuery("#err_"+errMemberId).append('<td>已处理</td>');
//					jQuery("#err_"+errMemberId).append('<td>已处理</td>');
//					/*jQuery("#err_"+errMemberId).append('<td>已处理</td>');*/
//				}else{
//					jQuery("#err_"+errMemberId).children("td").last().prev().remove();
//					jQuery("#err_"+errMemberId).children("td").last().remove();
//					jQuery("#err_"+errMemberId).append('<td>已处理</td>');
//					jQuery("#err_"+errMemberId).append('<td>已处理</td>');
//				}
			}
			dialog("删除成功！");
//			jQuery("#err_" + errMemberId).fadeOut(500).remove();
		}
	});
}

//拼装table
function initTable(members, lastFacilitator) {
	if (lastFacilitator == "盛传"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			if(member.isDeleted == 0){
				var str = '<tr id="err_'+member.id+'">'+
							'<td>'+member.phone +'</td>'+
			                '<td>'+member.name +'</td>'+
			                '<td>'+member.sex +'</td>'+
			                '<td>'+member.levelNum +'</td>'+
			                '<td>'+member.levelName +'</td>'+
			                '<td>'+member.balanceAmount +'</td>'+
			                '<td>'+member.levelType +'</td>'+
			                '<td>'+member.discount +'</td>'+
			                '<td>'+member.totalAmount +'</td>'+
			                '<td>'+member.totalConsumeAmount +'</td>'+
			                '<td>'+member.sendAmount +'</td>'+
			                '<td>'+member.aeadTime +'</td>'+
			                '<td>'+member.consumeAmount +'</td>'+
			                '<td>'+member.balanceIntegral +'</td>'+
			                '<td>'+member.debtAmount +'</td>'+
			                '<td>'+member.lastConsumeTime +'</td>'+
			                '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(1, '+member.id+' ,this);"></i></td>'+
			                '<td class="can-click m-btn" onclick="balanceAmountMove(1, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
			                /*'<td class="can-click m-btn" onclick="comboMove(1, '+member.id+', this, 1)" style="text-decoration: none; text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+*/
			            '</tr>';
				jQuery("#init_member").append(str);
			}else {
				var str = '<tr id="err_'+member.id+'">'+
							'<td>'+member.phone +'</td>'+
			                '<td>'+member.name +'</td>'+
			                '<td>'+member.sex +'</td>'+
			                '<td>'+member.levelNum +'</td>'+
			                '<td>'+member.levelName +'</td>'+
			                '<td>'+member.balanceAmount +'</td>'+
			                '<td>'+member.levelType +'</td>'+
			                '<td>'+member.discount +'</td>'+
			                '<td>'+member.totalAmount +'</td>'+
			                '<td>'+member.totalConsumeAmount +'</td>'+
			                '<td>'+member.sendAmount +'</td>'+
			                '<td>'+member.aeadTime +'</td>'+
			                '<td>'+member.consumeAmount +'</td>'+
			                '<td>'+member.balanceIntegral +'</td>'+
			                '<td>'+member.debtAmount +'</td>'+
			                '<td>'+member.lastConsumeTime +'</td>'+
			                '<td>已处理</td>'+
			                '<td>已处理</td>'+
			                /*'<td>已处理</td>'+*/
			            '</tr>';
				jQuery("#init_member").append(str);
			}
			
		}
	}
	else if (lastFacilitator == "左右"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			if(member.isDeleted == 0){
				var str = '<tr id="err_'+member.id+'">'+
		             '<td>'+member.phone+'</td>'+
		             '<td>'+member.name+' </td>'+
		             '<td>'+member.sex+' </td>'+
		             '<td>'+member.levelNum+' </td>'+
		             '<td>'+member.levelName+' </td>'+
		             '<td>'+member.balanceAmount+' </td>'+
		             '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(2, '+member.id+' ,this);"></i></td>'+
		             '<td class="can-click m-btn" onclick="balanceAmountMove(2, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
		             '</tr>';
					jQuery("#init_member").append(str);
			}else {
				var str = '<tr id="err_'+member.id+'">'+
		             '<td>'+member.phone+'</td>'+
		             '<td>'+member.name+' </td>'+
		             '<td>'+member.sex+' </td>'+
		             '<td>'+member.levelNum+' </td>'+
		             '<td>'+member.levelName+' </td>'+
		             '<td>'+member.balanceAmount+' </td>'+
		             '<td>已处理</td>'+
		             '<td>已处理</td>'+
		             '</tr>';
					jQuery("#init_member").append(str);
			}
		}
	}
	else if (lastFacilitator == "云浩企汇通"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			if(member.isDeleted == 0){
				var str = '<tr id="err_'+member.id+'">'+
	             '<td>'+member.phone+'</td>'+
	             '<td>'+member.name+' </td>'+
	             '<td>'+member.sex+' </td>'+
	             '<td>'+member.levelNum+' </td>'+
	             '<td>'+member.levelName+' </td>'+
	             '<td>'+member.balanceAmount+' </td>'+
	             '<td>'+member.balanceGiftmoneyAmount+' </td>'+
	             '<td>'+member.consumeCount+' </td>'+
	             '<td>'+member.balanceIntegral+' </td>'+
	             '<td>'+member.lastConsumeTime+' </td>'+
	             '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(3, '+member.id+' ,this);"></i></td>'+
	             '<td class="can-click m-btn" onclick="balanceAmountMove(3, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
	             '</tr>';
				jQuery("#init_member").append(str);
			}else{
				var str = '<tr id="err_'+member.id+'">'+
	             '<td>'+member.phone+'</td>'+
	             '<td>'+member.name+' </td>'+
	             '<td>'+member.sex+' </td>'+
	             '<td>'+member.levelNum+' </td>'+
	             '<td>'+member.levelName+' </td>'+
	             '<td>'+member.balanceAmount+' </td>'+
	             '<td>'+member.balanceGiftmoneyAmount+' </td>'+
	             '<td>'+member.consumeCount+' </td>'+
	             '<td>'+member.balanceIntegral+' </td>'+
	             '<td>'+member.lastConsumeTime+' </td>'+
	             '<td>已处理</td>'+
	             '<td>已处理</td>'+
	             '</tr>';
				jQuery("#init_member").append(str);
			}
		}
	}
	else if (lastFacilitator == "博卡"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			if(member.isDeleted == 0){
				var str = '<tr id="err_'+member.id+'">'+
	             '<td>'+member.phone+'</td>'+
	             '<td>'+member.name+' </td>'+
	             '<td>'+member.sex+' </td>'+
	             '<td>'+member.levelNum+' </td>'+
	             '<td>'+member.totalAmount+' </td>'+
	             '<td>'+member.balanceAmount+' </td>'+
	             '<td>'+member.totalConsumeAmount+' </td>'+
	             '<td>'+member.consumeCount+' </td>'+
	             '<td>'+member.avgConsumeAmount+' </td>'+
	             '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(4, '+member.id+' ,this);"></i></td>'+
	             '<td class="can-click m-btn" onclick="balanceAmountMove(4, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
	             '</tr>';
				jQuery("#init_member").append(str);
			}else{
				var str = '<tr id="err_'+member.id+'">'+
	             '<td>'+member.phone+'</td>'+
	             '<td>'+member.name+' </td>'+
	             '<td>'+member.sex+' </td>'+
	             '<td>'+member.levelNum+' </td>'+
	             '<td>'+member.totalAmount+' </td>'+
	             '<td>'+member.balanceAmount+' </td>'+
	             '<td>'+member.totalConsumeAmount+' </td>'+
	             '<td>'+member.consumeCount+' </td>'+
	             '<td>'+member.avgConsumeAmount+' </td>'+
	             '<td>已处理</td>'+
	             '<td>已处理</td>'+
	             '</tr>';
				jQuery("#init_member").append(str);
			}
		}
	}
	else if (lastFacilitator == "耕宇"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			if(member.isDeleted == 0){
				var str = '<tr id="err_'+member.id+'">'+
	             '<td>'+member.phone+'</td>'+
	             '<td>'+member.name+' </td>'+
	             '<td>'+member.sex+' </td>'+
	             '<td>'+member.levelNum+' </td>'+
	             '<td>'+member.totalAmount+' </td>'+
	             '<td>'+member.balanceAmount+' </td>'+
	             '<td>'+member.totalConsumeAmount+' </td>'+
	             '<td>'+member.consumeCount+' </td>'+
	             '<td>'+member.avgConsumeAmount+' </td>'+
	             '<td>'+member.debtAmount+' </td>'+
	             '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(5, '+member.id+' ,this);"></i></td>'+
	             '<td class="can-click m-btn" onclick="balanceAmountMove(5, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
	             '</tr>';
				jQuery("#init_member").append(str);
			}else{
				var str = '<tr id="err_'+member.id+'">'+
	             '<td>'+member.phone+'</td>'+
	             '<td>'+member.name+' </td>'+
	             '<td>'+member.sex+' </td>'+
	             '<td>'+member.levelNum+' </td>'+
	             '<td>'+member.totalAmount+' </td>'+
	             '<td>'+member.balanceAmount+' </td>'+
	             '<td>'+member.totalConsumeAmount+' </td>'+
	             '<td>'+member.consumeCount+' </td>'+
	             '<td>'+member.avgConsumeAmount+' </td>'+
	             '<td>'+member.debtAmount+' </td>'+
	             '<td>已处理</td>'+
	             '<td>已处理</td>'+
	             '</tr>';
				jQuery("#init_member").append(str);
			}
		}
	}
	else if (lastFacilitator == "蓝蝶"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			if(member.isDeleted == 0){
				var str = '<tr id="err_'+member.id+'">'+
		             '<td>'+member.phone+'</td>'+
		             '<td>'+member.name+' </td>'+
		             '<td>'+member.sex+' </td>'+
		             '<td>'+member.levelNum+' </td>'+
		             '<td>'+member.balanceAmount+' </td>'+
		             '<td>'+member.balanceIntegral+' </td>'+
		             '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(6, '+member.id+' ,this);"></i></td>'+
		             '<td class="can-click m-btn" onclick="balanceAmountMove(6, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
		             '</tr>';
					jQuery("#init_member").append(str);
			}else {
				var str = '<tr id="err_'+member.id+'">'+
		             '<td>'+member.phone+'</td>'+
		             '<td>'+member.name+' </td>'+
		             '<td>'+member.sex+' </td>'+
		             '<td>'+member.levelNum+' </td>'+
		             '<td>'+member.levelName+' </td>'+
		             '<td>'+member.balanceAmount+' </td>'+
		             '<td>'+member.balanceIntegral+' </td>'+
		             '<td>已处理</td>'+
		             '<td>已处理</td>'+
		             '</tr>';
					jQuery("#init_member").append(str);
			}
		}
	}
	
	
	else if (lastFacilitator == "西沙龙"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			if(member.isDeleted == 0){
				var str = '<tr id="err_'+member.id+'">'+
		             '<td>'+member.phone+'</td>'+
		             '<td>'+member.name+' </td>'+
		             '<td>'+member.sex+' </td>'+
		             '<td>'+member.levelName+' </td>'+
		             '<td>'+member.levelNum+' </td>'+
		             '<td>'+member.balanceAmount+' </td>'+
		             '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(9, '+member.id+' ,this);"></i></td>'+
		             '<td class="can-click m-btn" onclick="balanceAmountMove(9, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
		             '</tr>';
					jQuery("#init_member").append(str);
			}else {
				var str = '<tr id="err_'+member.id+'">'+
		             '<td>'+member.phone+'</td>'+
		             '<td>'+member.name+' </td>'+
		             '<td>'+member.sex+' </td>'+
		             '<td>'+member.levelName+' </td>'+
		             '<td>'+member.levelNum+' </td>'+
		             '<td>'+member.balanceAmount+' </td>'+
		             '<td>已处理</td>'+
		             '<td>已处理</td>'+
		             '</tr>';
					jQuery("#init_member").append(str);
			}
		}
	}
	
	else if (lastFacilitator == "华彩"){
		for (var i = 0; i < members.length; i++) {
			var member = members[i];
			if(member.isDeleted == 0){
				var str = '<tr id="err_'+member.id+'">'+
		             '<td>'+member.phone+'</td>'+
		             '<td>'+member.name+' </td>'+
		             '<td>'+member.sex+' </td>'+
		             '<td>'+member.levelNum+' </td>'+
		             '<td>'+member.levelName+' </td>'+
		             '<td>'+member.balanceAmount+' </td>'+
		             '<td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(11, '+member.id+' ,this);"></i></td>'+
		             '<td class="can-click m-btn" onclick="balanceAmountMove(11, '+member.id+', this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
		             '</tr>';
					jQuery("#init_member").append(str);
			}else {
				var str = '<tr id="err_'+member.id+'">'+
		             '<td>'+member.phone+'</td>'+
		             '<td>'+member.name+' </td>'+
		             '<td>'+member.sex+' </td>'+
		             '<td>'+member.levelNum+' </td>'+
		             '<td>'+member.levelName+' </td>'+
		             '<td>'+member.balanceAmount+' </td>'+
		             '<td>已处理</td>'+
		             '<td>已处理</td>'+
		             '</tr>';
					jQuery("#init_member").append(str);
			}
		}
	}
	
	else {
		
	}
}

//上一页
function previousRumors() {
	if (pageNoR <= 1) {
		return;
	}
	pageNoR--;
	changeRumorsPage();
}
// 下一页
function nextRumors() {
	if (pageNoR >= totalPageR) {
		return;
	}
	pageNoR++;
	changeRumorsPage();
}
// 更改每页显示数量
function changeRumorsPageSize(size) {
	pageNoR = 1;
	pageSizeR = size;
	jQuery(".perpage").eq(1).html(size + " <span class='iconfa-caret-down afont'></span>");
	changeRumorsPage();
}
//分页处理
function changeRumorsPage() {
	var data = "pageNo=" + pageNoR + "&pageSize=" + pageSizeR;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/view/rumors",
		data : data,
		dataType : "json",
		success : function(e) {
			if (e.code == 0) {
				jQuery(".perpage").eq(10).text(e.msg.pageSize);
				totalPageR = e.msg.totalPage;
				jQuery("#init_member_rumors").empty();
				initRumorsTable(e.msg.results);
				totalRecordR = e.msg.totalRecord;
			}
		}
	});
}
function initRumorsTable(results){
	for (var i = 0; i < results.length; i++) {
		rumorsCoruse = results[i];
		var str = '<tr id="course_'+rumorsCoruse.id+'">'+
		        '<td>'+rumorsCoruse.phone +'</td>'+
		        '<td style="width: 80px">'+rumorsCoruse.name +'</td>'+
		        '<td>'+rumorsCoruse.levelNum +'</td>'+
		        '<td style="width: 80px">'+rumorsCoruse.levelName +'</td>'+
		        '<td style="width: 140px">'+rumorsCoruse.levelType +'</td>'+
		        '<td>'+rumorsCoruse.courseDesc +'</td>'+
		        '<td style="width: 80px">'+rumorsCoruse.residueDegree +'</td>'+
		        '<td style="width: 80px">'+rumorsCoruse.residueAmount +'</td>'+
		        '<td style="width: 40px" class="can-click m-btn" onclick="comboMove(1, '+rumorsCoruse.id+', this, 2)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>'+
		   '</tr>';
		jQuery("#init_member_rumors").append(str);	
	}
}