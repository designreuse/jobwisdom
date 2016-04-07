/**
 * 
 */
	$('#accountSearchTable').bootstrapTable({

	});

var searchData = {};

	//扩展bootstrap table的请求参数
	function queryParams(params){
		
		for(var k in searchData){
			params[k] = searchData[k];
		}
		//console.log("queryParams..." + JSON.stringify(params));
		return params;
	}

	//调用bootstrap table的函数
	function query(){
		
		var startTime =$("#startTime").val();
		if(startTime!=null){
			searchData["startTime"] = startTime;
		}
		var endTime =$("#endTime").val();
		if(endTime!=null){
			searchData["endTime"] = endTime;
		}
		
		var roleName =$("#roleName").val();
		if(roleName!=null){
			searchData["roleId"] = roleName;
		}
		var province =$("#province").val();
		if(province!=null){
			searchData["province"] = province;
		}
		var city =$("#city").val();
		if(city!=null){
			searchData["city"] = city;
		}
		var area =$("#area").val();
		if(area!=null){
			searchData["area"] = area;
		}
		$('#accountSearchTable').bootstrapTable('refresh', null);
	}
	
	/*账号查询的操作*/
	function accountSearchTable(value, row, index) {
	return [
	'<a class="like" href="#" data-toggle="modal" title="用户修改"><i class="icon-pencil"></i></a> ',
	'<a class="remove" href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
	'<a class="update" href="javascript:void(0)" title="密码初始化"><i class="icon-unlock" style="margin-left: 5px;"></i></a>'
	].join('');
	}
	window.operateEvents = {
			  'click .like': function (e, value, row, index) {
				$('#account-fixed').modal();
			    //alert('You click like action, row: ' + JSON.stringify(row));
			    //$('#update_roleName').val(row.roleId);
			    
			    $('#update_province').val(row.province);
			    $('#update_province').change();
			    
			    $('#update_city').val(row.city);
			    $('#update_city').change();
			    
			    $('#update_area').val(row.area);
			    
			    $('#update_companyName').val(row.companyName);
			    $('#update_addr').val(row.addr);
			    $('#update_realName').val(row.realName);
			    $('#update_fixedNum').val(row.fixedNum);
			    $('#update_phone').val(row.phone);
			    $('#update_userName').val(row.userName);
			    $('#update_userId').val(row.userId);
				  
			  },
			  'click .remove': function (e, value, row, index) {
				   // alert('You click like action, row: ' + JSON.stringify(row));
				 if(confirm("确认要删除该条帐号信息吗？")){ 
		    	 $.ajax({
		             type: "POST",
		             dataType: "json",
		             url: baseUrl+'user/deleteUser',
		             data:'userId='+row.userId,
		             success: function (result) {
		                 //加载最大可退金额
		                 if(result.code==0){
		                	 alert("删除成功！");
		                	 $('#accountSearchTable').bootstrapTable('refresh', null);
		                 }else{
		                	 alert("删除失败！");
		                 }
		                 
		             }
		         });
		    	return;
		    	}
				   
				  },
				  'click .update': function (e, value, row, index) {
					   // alert('You click like action, row: ' + JSON.stringify(row));
					 if(confirm("确认要初始化该账户的密码？")){ 
			    	 $.ajax({
			             type: "POST",
			             dataType: "json",
			             url: baseUrl+'user/initpassword',
			             data:'userId='+row.userId,
			             success: function (result) {
			                 //加载最大可退金额
			                 if(result.code==0){
			                	 alert("修改成功！");
			                	 $('#accountSearchTable').bootstrapTable('refresh', null);
			                 }else{
			                	 alert("修改失败！");
			                 }
			             }
			         });
			    	return;
			    	}
					   
					  }
			};
	