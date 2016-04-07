
/*账号查询表*/
	$('#accountSearchTable').bootstrapTable({

	});
function adduser(){
 		var roleName =$("#roleName").val();
 		if(roleName==null||roleName==""){
 			alert("角色不能为空");
 			return;
 		}

 		var realName =$("#realName").val();
 		var userName =$("#userName").val();
 		if(userName==null||userName==""){
 			alert("用户名不能为空");
 			return;
 		}
 		var province =$("#province").val();
 		var city =$("#city").val();
 		var area =$("#area").val();
 		
 		var phone =$("#phone").val();
 		if(phone==null||phone==""){
 			alert("手机不能为空");
 			return;
 		}
 		if(phone.length!=11){
 			alert("请正确输入11位手机号码！");
 			return;
 		}
 		var isNum = /^[0-9]*$/;
		 if (!isNum.test(phone))
	    {
	        alert("手机号码只能输入数字！");
	        return;
	     }
 		var fixedNum =$("#fixedNum").val();
 		
 		var companyName =$("#companyName").val();
 		$.ajax({
            type: "POST",
            dataType: "json",
            url: baseUrl+'user/addUser',
            data:'roleId='+roleName+'&province='+province+'&city='+city+'&area='+area+'&userName='+userName+'&realName='+realName+'&phone='+phone
            +'&fixedNum='+fixedNum+'&companyName='+companyName,
            success: function (result) {
                alert(result.msg);
                return;
            }
        });
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
			    $('#update_roleName').val(row.roleId);
			    
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
			                	 $('#account-fixed').modal("hide");
			                 }else{
			                	 alert("修改失败！");
			                 }
			             }
			         });
			    	return;
			    	}
					   
					  }
			};
	
	
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
	function updateUser(){
		 
	    var roleName = $('#update_roleName').val();
	    if(roleName==null||roleName==""){
 			alert("角色不能为空！");
 			return;
 		}
		var province = $('#update_province').val();
		var city = $('#update_city').val();
		var area = $('#update_area').val();
		var companyName = $('#update_companyName').val();
		var addr = $('#update_addr').val();
		var realName = $('#update_realName').val();
		var fixedNum = $('#update_fixedNum').val();
		var phone = $('#update_phone').val();
		var isNum = /^[0-9]*$/;
 		 if (!isNum.test(phone))
 	    {
 	        alert("手机号码只能输入数字！");
 	        return;
 	     }
 		if(phone.length!=11){
 			alert("请正确输入11位数手机号码！");
 			return;
 		}
 		if(phone==null||phone==""){
 			alert("手机不能为空");
 			return;
 		}
 		var money = $('#update_noney').val();
 		if (!isNum.test(money))
 	    {
 	        alert("可增客户数只能输入数字！");
 	        return;
 	     }
		var userName = $('#update_userName').val();
		var userId = $('#update_userId').val();
		 if(userName==null||userName==""){
	 			alert("帐号不能为空！");
	 			return;
	 		}
		var userId = $('#update_userId').val();
		$.ajax({
			type : "POST",
			dataType : "json",
			url : baseUrl+'user/updateUser',
			data : 'roleId=' + roleName + '&province=' + province+ '&city=' + city + '&area=' + area + '&userName='
					+ userName + '&realName=' + realName + '&phone='+ phone + '&fixedNum=' + fixedNum + '&companyName='
					+ companyName+ '&userId=' + userId+ '&money=' + money,
			success : function(result) {
				if(result.code==0){
					alert("修改成功！");
					$('#myModal').modal('hide');
					$('#accountSearchTable').bootstrapTable('refresh', null);
					return;
				}else{
					alert(result.msg);
					return;
				}
			}
		});
	}
	
	/*时间选择器中文包*/
	$.fn.datetimepicker.dates['zh-CN'] = {
	  days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
	  daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
	  daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
	  months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	  monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	  today: "今天",
	  suffix: [],
	  meridiem: ["上午", "下午"]
	};

	/*出发时间选择器*/
	$(".form_datetime").datetimepicker({
		  format: 'yyyy-mm-dd',//显示的格式
		  language: 'zh-CN',//中文
		  autoclose: 'true',//选择后自动关闭
		  minView: '2',//到日，不到时间
		  todayHighlight: true//今天时间高亮
		  //initialDate: new Date()//初始化时间为当前时间
		});
