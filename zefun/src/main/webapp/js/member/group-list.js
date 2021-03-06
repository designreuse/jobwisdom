var groupId = null;
//查询满足该分组条件的会员信息 
jQuery(".vip_group_left_content_detail").on("click",function(){
	if (jQuery(this).children("p").children("em").text()=="0人"){
		dialog("该分组下没有会员数据");
		groupId = jQuery(this).attr("id");
		jQuery(this).addClass('active').siblings().removeClass('active');
		jQuery("#serch_member_list").empty();
		jQuery(".pagination-demo").prev().text("");
		return;
	}
	groupId = jQuery(this).attr("id");
	jQuery(this).addClass('active').siblings().removeClass('active');
	var groupName = jQuery(this).parent().parent().children("td").eq(0).text();
	jQuery("#group_name_show").text("当前分组:"+groupName);
	jQuery("#renyuan").show();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/view/census/member/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize + "&groupId="+groupId,
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				jQuery("#serch_member_list").empty();
				pageNo = e.msg.pageNo;
				pageSize = e.msg.pageSize;
				totalRecord = e.msg.totalRecord;
				totalPage = e.msg.totalPage;
				for (var i = 0; i < e.msg.results.length; i++) {
					var tr = jQuery("<tr></tr>");
					var td1 = jQuery("<td>"+e.msg.results[i].phone+"</td>");
					var td2 = jQuery('<td class="can-click" data-target="#member-data" data-toggle="modal" onclick="selectMemberInfo('+e.msg.results[i].memberId+')">'+e.msg.results[i].name+'</td>');
					var td3 = jQuery("<td>"+e.msg.results[i].sex+"</td>");
					var td4 = jQuery("<td>"+e.msg.results[i].birthday+"</td>");
					var td6 = jQuery("<td>"+e.msg.results[i].balanceAmount+"</td>");
					var td7 = jQuery("<td>"+e.msg.results[i].balanceIntegral+"</td>");
					var td8 = jQuery("<td>"+e.msg.results[i].totalConsumeAmount+"</td>");
					var td9 = jQuery("<td>"+e.msg.results[i].avgConsumeAmount+"</td>");
					var td10 = jQuery("<td>"+e.msg.results[i].createTime+"</td>");
					if (type == "2"){
						var td0 = jQuery("<td>"+e.msg.results[i].storeName+"</td>");
						tr.append(td0);
					}
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td6);
					tr.append(td7);
					tr.append(td8);
					tr.append(td9);
					tr.append(td10);
					jQuery("#serch_member_list").append(tr);
				}
				unbuildPagination();
			}else{
				jQuery("#serch_member_list").empty();
			}
		}
	});
});
//删除分组
jQuery(".delete_group").on("click",function(){
	var i = jQuery(this);
	if (groupId == null){dialog("暂无选择的分组");return;}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/delete/census",
		data : "groupId="+groupId,
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				jQuery(".vip_group_left_content_detail.active").remove();
			}else{
				dialog(e.msg);
			}
		}
	});
});
/*分页*/
//上一页
function previous(){
	if(pageNo <= 1){
		return;
	}
	pageNo--;
	changePage();
}
//下一页
function next(){
	if(pageNo >= totalPage){
		return;
	}
	pageNo++;
	changePage();
}
//更改每页显示数量
function changePageSize(size){
	pageNo = 1;
	pageSize = size;
	jQuery(".perpage").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePage();
}
//分页处理
function changePage(){
  //查询会员数据,刷新html元素
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/view/census/member/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize + "&groupId="+groupId,
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				jQuery("#serch_member_list").empty();
				totalPage = e.msg.totalPage;
				for (var i = 0; i < e.msg.results.length; i++) {
					var tr = jQuery("<tr></tr>");
					var td1 = jQuery("<td>"+e.msg.results[i].phone+"</td>");
					var td2 = jQuery("<td class='can-click' onclick='selectMemberInfo("+e.msg.results[i].memberId+")' data-target='#member-data' data-toggle='modal'>"+e.msg.results[i].name+"</td>");
					var td3 = jQuery("<td>"+e.msg.results[i].sex+"</td>");
					var td4 = jQuery("<td>"+e.msg.results[i].birthday+"</td>");
					var td6 = jQuery("<td>"+e.msg.results[i].balanceAmount+"</td>");
					var td7 = jQuery("<td>"+e.msg.results[i].balanceIntegral+"</td>");
					var td8 = jQuery("<td>"+e.msg.results[i].totalConsumeAmount+"</td>");
					var td9 = jQuery("<td>"+e.msg.results[i].avgConsumeAmount+"</td>");
					var td10 = jQuery("<td>"+e.msg.results[i].createTime+"</td>");
					if (type == "2"){
						var td0 = jQuery("<td>"+e.msg.results[i].storeName+"</td>");
						tr.append(td0);
					}
					tr.append(td1);
					tr.append(td2);
					tr.append(td3);
					tr.append(td4);
					tr.append(td6);
					tr.append(td7);
					tr.append(td8);
					tr.append(td9);
					tr.append(td10);
					jQuery("#serch_member_list").append(tr);
				}
			}else{
				jQuery("#serch_member_list").empty();
			}
		}
	});
}

function initPageClick() {
	
	jQuery('#pagination-demo').twbsPagination({
		totalPages : totalPage,
		visiblePages : 5,
		onPageClick: function (event, page) {
			pageNo = page;
			changePage();
        } 
	});
	
	jQuery('#pagination-demo').prev().text('共找到了 '+totalRecord+' 条数据, 共 '+totalPage+' 页');
}

//重新解绑,绑定,当数据重新书写,也就是进行了查询想干的操作,就要重新绑定分页插件
function unbuildPagination(){
	jQuery('.fenye').removeClass("hide");
	jQuery('#pagination-demo').empty();
	jQuery('#pagination-demo').removeData("twbs-pagination");
	jQuery('#pagination-demo').unbind("page");
	initPageClick();
}
