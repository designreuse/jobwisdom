window.onload=function(){
	   $('#clientAttr').change(function(){
		     var p1=$(this).children('option:selected').val();//这就是selected的值
		     if (p1 == '1'){
		       $(".company-name").css("display","none");
		       $(".customerName-name").css("display","block");
		     }else if(p1 == '2'){
		       $(".customerName-name").css("display","none");
		       $(".company-name").css("display","block");
		       $(".company-input").attr("placeholder","");
		     }else if(p1 == '3'){
		       $(".customerName-name").css("display","block");
		       $(".company-name").css("display","block");
		       $(".company-input").attr("placeholder","输入关键字");
		     }
		   });
}
function changemoney(){
	   var sysName=$('#sysName').val();
		 $.ajax({
	         type: "POST",
	         dataType: "json",
	         url: baseUrl+'cust/getGoodsPrice',
	         data:'sysName='+sysName,
	         success: function (result) {
	             $('#money').val(result.msg);
	         }
	     });
	}
function addcust(){
	//客户属性
	var customerType =$("#clientAttr").val();
	if(customerType==null||customerType==""){
		alert("客户属性不能为空");
		return;
	}
	if(customerType==1){
		$("#companyName").val("");
	}
	if(customerType==2){
		$("#customerName").val("");
	}
	
	//系统版本
	var sysName =$("#sysName").val();
	if(sysName==null||sysName==""){
		alert("系统版本不能为空");
		return;
	}
	//品牌名称
	var brandName =$("#brandName").val();

	//公司名称
	if(customerType!=1){
	var companyName =$("#companyName").val();
		if(companyName==null||companyName==""){
			alert("公司名称不能为空");
			return;
		}
	}
	

	//店铺名称
	var customerName =$("#customerName").val();
	//当客户属性不属于连锁店时候
	if(customerType!=2){
		if(customerName==null||customerName==""){
			alert("店铺名称不能为空");
			return;
		}
	}
	
	//选择区域
	var province =$("#province").val();
	var city =$("#city").val();
	var area =$("#area").val();
	
	//地址
	var addr =$("#addr").val();

	//身份证号码
	var customerCode =$("#customerCode").val();

	//负责人姓名
	var principalName =$("#principalName").val();
	if(principalName==null||principalName==""){
		alert("负责人姓名不能为空");
		return;
	}
	
	
	//负责人职位
	var principalDuty =$("#principalDuty").val();
	if(principalDuty==null||principalDuty==""){
		alert("负责人职位不能为空");
		return;
	}
	//负责人电话
	var phone =$("#phone").val();
	if(phone==null||phone==""){
		alert("电话不能为空");
		return;
	}
	if (isNaN(phone)){
        alert("客户帐号只能为数字！");
        return;
     }
	if(phone.length!=11){
		alert("手机号码为11位号码！");
		return;
	}
	
	var userName =$("#userName").val();
	if(userName==null||userName==""){
		alert("客户帐号不能为空");
		return;
	}
	if (isNaN(userName)){
        alert("客户帐号只能为数字！");
        return;
     }
	if(userName.length!=11){
		alert("账号请输入11位的手机号码！");
		return;
	}
	
	 $.ajax({
         type: "POST",
         dataType: "json",
         url: baseUrl+'cust/addCust',
         data:'customerType='+customerType+'&sysName='+sysName+'&customerName='+customerName+'&province='+province+'&city='+city
             +'&area='+area+'&companyName='+companyName+'&addr='+addr+'&cardNo='+customerCode+'&principalName='+principalName+'&principalDuty='+principalDuty
             +'&phone='+phone+'&userName='+userName,
         success: function (result) {
        	 if(result.code==-1){
        		 alert(result.msg);
        		 return;
        	 }else if(result.code==-2){
        		 alert(result.msg);
        		 return;
        	 }else{
        		 alert(result.msg);
        	 }
         }
     });
}

/*已设置的项目列表*/
$('#utable').bootstrapTable({
});
function operateFormatter(value, row, index) {
  return [
    '<a class="like" style="color: blue;" href="#projectMod" data-toggle="modal" title="客户修改"><i class="'+classInit.iconpencil+'"></i></a> ',
    '<a class="remove" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
  ].join('');
}
window.operateEvents = {
		  'click .like': function (e, value, row, index) {
			  location.href=encodeURI(baseUrl + "cust/view/updatecust?customerId="+row.customerId); 
		
		  },
		  'click .remove': function (e, value, row, index) {
			    //alert('You click like action, row: ' + JSON.stringify(row));
			    if(confirm("确认要删除该条客户信息吗？")){ 
			    	 $.ajax({
			             type: "POST",
			             dataType: "json",
			             url: baseUrl+'cust/deleteCust',
			             data:'customerId='+row.customerId,
			             success: function (result) {
			                 //加载最大可退金额
			                 if(result.code==0){
			                	 alert("删除成功！");
			                	 $('#utable').bootstrapTable('refresh', null);
			                 }else{
			                	 alert("删除失败！");
			                 }
			                 
			             }
			         });
			    	return;
			    	}
			  }
		};

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

	//开始时间
	var startTime =$("#startTime").val();
	if(startTime!=null){
		searchData["startTime"] = startTime;
	}
	//结束时间
	var endTime =$("#endTime").val();
	if(endTime!=null){
		searchData["endTime"] = endTime;
	}
	//系统名称
	var sysName =$("#sysName").val();
	if(sysName!=null){
		searchData["sysName"] = sysName;
	}
	var customerType=$('#customerType').val();
	if(customerType!=null){
		searchData["customerType"] = customerType;
	}
	//省
	var province =$("#province").val();
	if(province!=null){
		searchData["province"] = province;
	}
	//市
	var city =$("#city").val();
	if(city!=null){
		searchData["city"] = city;
	}
	//区域
	var area =$("#area").val();
	if(area!=null){
		searchData["area"] = area;
	}
	//品牌
	var brandName =$("#brandName").val();
	if(brandName!=null){
		searchData["brandName"] = brandName;
	}
	//负责人
	var principalName =$("#principalName").val();
	if(principalName!=null){
		searchData["principalName"] = principalName;
	}
	$('#utable').bootstrapTable('refresh', null);
}

/*
function updateCust(){
	
	 //客户属性
	  var customerId=$("#update_customerId").val();
	  var customerType=$("#clientAttr").val();
	  //客户版本
	  var sysName=$("#update_sysName").val();
	  //品牌名称
	  var brandName=$("#update_brandName").val();
	  
	  var companyName=$("#update_companyName").val();
	  if(customerType!=1){
			if(companyName==null||companyName==""){
				alert("公司名称不能为空");
				return;
			}
		}
	  //客户名称
	  var customerName=$("#update_customerName").val();
	  if(customerType!=2){
			if(customerName==null||customerName==""){
				alert("店铺名称不能为空");
				return;
			}
		}
	  //省
	  var province=$("#update_province").val();
	  //市
	  var city=$("#update_city").val();
	  //区
	  var area=$("#update_area").val();
	  //地址
	  var addr=$("#update_addr").val();
	  //身份证
	  var cardNo=$("#update_cardNo").val();
	  //负责人姓名
	  var principalName=$("#update_principalName").val();
	  if(principalName==null||principalName==""){
			alert("负责人姓名不能为空");
			return;
		}
	  //负责人职位
	  var principalDuty=$("#update_principalDuty").val();
	  if(principalDuty==null||principalDuty==""){
			alert("负责人职位不能为空");
			return;
		}
	  //负责人电话
	  var phone=$("#update_phone").val();
	  if(phone==null||phone==""){
			alert("负责人电话不能为空");
			return;
		}
	  //主帐号
	  var userName=$("#update_userName").val();
	  if(userName==null||userName==""){
			alert("主帐号不能为空");
			return;
		}
	  //帐号期限
	  var expireTime=$("#update_expireTime").val();
	  if(expireTime==null||expireTime==""){
			alert("帐号期限不能为空");
			return;
		}
	  //付款
	  var isPay=$("#update_isPay").val();
	  
	  $.ajax({
	         type: "POST",
	         dataType: "json",
	         url: baseUrl+'cust/updateCust',
	         data:'customerId='+customerId+'&customerType='+customerType+'&sysName='+sysName+'&brandName='+brandName+'&customerName='+customerName+'&province='+province+'&city='+city
	             +'&area='+area+'&addr='+addr+'&cardNo='+cardNo+'&principalName='+principalName+'&principalDuty='+principalDuty
	             +'&phone='+phone+'&expireTime='+expireTime+'&isPay='+isPay+'&companyName='+companyName+'&userName='+userName,
	         success: function (result) {
	             alert(result.msg);
	             $('#utable').bootstrapTable('refresh', null);
	         }
	     });
	
}

*/
