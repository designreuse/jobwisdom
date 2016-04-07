
/*已设置的项目列表*/
$('#leadsMod1').bootstrapTable({
	
});

/*
 * 
 */
function leadsMod1(value, row, index) {
  return [
	'<a class="like" style="color: blue;" href="#" mce_href="#" title="修改" ><i class="icon-pencil" style="margin-left: 10px;"></i></a> ',
	'<a class="remove" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove" style="margin-left: 10px;"></i></a>',
	'<a class="check" style="color: blue; " href="javascript:void(0)" title="审核"><i class="icon-file" style="margin-left: 10px;"></i></a>',
	'<a class="detail" style="color: blue;" href="#" mce_href="#" title="详情"><i class="icon-book" style="margin-left: 10px;"></i></a> ',
  ].join('');
}


/*
 * 
 */
window.operateEvents = {
  'click .like': function (e, value, row, index) {
	//  console.log("click .like ....." + JSON.stringify(row)); 
   //alert('You click like action, row: ' + JSON.stringify(row))
	  location.href=encodeURI(baseUrl + "SystemAffiche/view/affiche/updateAffiche?afficheId="+row.afficheId); 
	  
  },
  'click .remove': function (e, value, row, index) {
	    //alert('You click like action, row: ' + JSON.stringify(row));
	  	//console.log("click .remove ..." + row.afficheId);	//输出语句
	  	$("#afficheId").val(row.afficheId);
	  	deleteAffiche();
  },
  
  'click .check': function (e, value, row, index) {
	    //alert('You click like action, row: ' + JSON.stringify(row));
	  var afficheStatus;
	  	if(row.afficheStatus==1){
	  		afficheStatus=2;//审核
	  	}else{
	  		afficheStatus=1;//没审核
	  	}
	  	$.ajax({
	        type: "POST",
	        dataType: "json",
	        url: baseUrl + 'SystemAffiche/updateState',
	        data:'afficheId='+row.afficheId+'&afficheStatus='+afficheStatus,
	        success: function (result) {
	           
	        	$('#leadsMod1').bootstrapTable('refresh', null);
	        }
	    });
  },
  'click .detail': function (e, value, row, index) {
	  	location.href=encodeURI(baseUrl + "SystemAffiche/view/affiche/selectDetails?afficheId="+row.afficheId); 
}
};


var searchData = {};

//扩展bootstrap table的请求参数
function queryParams(params){
	
	for(var k in searchData){
		params[k] = searchData[k];
	}
	//console.log("queryParams..." + JSON.stringify(params));
	return params;j
}

 /**
  * 删除公告
  */
function deleteAffiche(){
	var afficheId =$("#afficheId").val();
	console.log("deleteAffiche ..." + afficheId);	//输出语句
	$.ajax({
        type: "POST",
        dataType: "json",
        url: baseUrl + 'SystemAffiche/deleteaffiche',
        data:'afficheId='+afficheId,
        success: function (result) {
            alert(result);
        	$('#leadsMod1').bootstrapTable('refresh', null);
        }
    });
}

/**
 * 修改公告
 */
function updateAffiche(){
	
	var afficheId =$("#afficheId").val();
	var afficheTitle=$("#afficheTitle").val();
	if(afficheTitle==null||afficheTitle==""){
		alert("标题不能为空！");
		return ;  
	}
	
	var afficheClasses=$("#afficheClasses").val();
	if(afficheClasses==null||afficheClasses==""){
		alert("类别不能为空！");
		return ;  
	}
	
	var afficheContent=$("#afficheContent").val();
	if(afficheContent==null||afficheContent==""){
		alert("内容不能为空！");
		return ;  
	}

	var afficheRole=$("#afficheRole").val();
	if(afficheRole==null||afficheRole==""){
		alert("部门不能为空！");
		return ;  
	}
	//console.log("updateAffiche ..." + afficheId);
	$.ajax({
        type: "POST",
        dataType: "json",
        url: baseUrl + 'SystemAffiche/updateaffiche',
        data:'&afficheId='+afficheId+'&afficheTitle='+afficheTitle+'&afficheClasses='+afficheClasses+'&afficheContent='+afficheContent+'&afficheRole='+afficheRole,
        success: function (result) {
            alert("修改成功");
            $('#projectMod').modal('hide')
        	$('#projectListTable').bootstrapTable('refresh', null);
        }
    });
}

/**
 * 查询公告详情
 */
function selectDetails(){
	var afficheId =$("#afficheId").val(); 
	//console.log("deleteAffiche ..." + afficheId);	//输出语句
	$.ajax({
        type: "POST",
        dataType: "json",
        url: baseUrl + 'SystemAffiche/deleteaffiche',
        data:'afficheId='+afficheId,
        success: function (result) {
            alert(result);
        	$('#leadsMod1').bootstrapTable('refresh', null);
        }
    });
	
}