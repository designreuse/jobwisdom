
$('#leadsMod1').bootstrapTable({

});

$('#modUserTable').bootstrapTable({

});
//公海转入自己的接口
function getSelections1(){
	var selectDataArray = JSON.stringify($("#modUserTable").bootstrapTable('getSelections'));
	selectDataArray = eval("(" + selectDataArray + ")");
	var idArray = new Array();
	
	if(selectDataArray.length==0){
		alert("请先选择要转入的信息");
		return;
	}
	for (var i = 0; i < selectDataArray.length; i++) {
		idArray[i] = selectDataArray[i].clueId;
	}
	//console.log("idArray : " + idArray);
	 $.ajax({
         type: "POST",
         dataType: "json",
         url: baseUrl+'custclue/updateClueSs',
         data:'idArray='+idArray+'&isPublic=0',
         success: function (result) {
             //加载最大可退金额
             alert(result.msg);
             $('#modUserTable').bootstrapTable('refresh', null);
             
         }
     });
}

function stateFormatter(value, row, index) {
	
//	alert(row.customerName);
	
}
//打开新增窗口
function openmodal(){
	$('#addLeads').modal();
	$("#add_customerName").val("");
	$("#add_province").val("");
	$("#add_city").val("");
	$("#add_area").val("");
	$("#add_principalName").val("");
	$("#add_phone").val("");
	$("#add_storeNumber").val("");
}
//新增功能
function addsave(){
	var customerName =$("#add_customerName").val();
	if(customerName==null||customerName==""){
		alert("店铺名称不能为空");
		return;
	}
	var province =$("#add_province").val();
	if(province==null||province==""){
		alert("省份不能为空");
		return;
	}
	var city =$("#add_city").val();
	var area =$("#add_area").val();
	
	var principalName =$("#add_principalName").val();
	if(principalName==null||principalName==""){
		alert("联系人不能为空");
		return;
	}
	var principalDuty =$("#add_principalDuty").val();
	if(principalDuty==null||principalDuty==""){
		alert("联系人职位不能为空");
		return;
	}
	var phone =$("#add_phone").val();
	if(phone==null||phone==""){
		alert("手机号不能为空");
		return;
	}
	if(!phone.replace(/[^\d]/g,'')){
		alert("手机号码只能为数字");
		return;
	}
	if(phone.length!=11){
		alert("手机号码为11位数字");
		return;
	}
	var customerType =$("#add_customerType").val();
	if(customerType==null||customerType==""){
		alert("店铺属性不能为空");
		return;
	}
	var storeNumber =$("#add_storeNumber").val();
	if(storeNumber==null||storeNumber==""){
		alert("不能为空");
		return;
	}
	if(!storeNumber.replace(/[^\d]/g,'')){
		alert("店铺数量只能为数字");
		return;
	}

	var brandLevel =$("#add_brandLevel").val();
	
	var clueStatus =$("#add_clueStatus").val();
	
	var clueSource =$("#add_clueSource").val();
	
	$.ajax({
        type: "POST",
        dataType: "json",
        url: baseUrl+'custclue/addCustClue',
        data:'customerName='+customerName+'&province='+province+'&city='+city+'&area='+area+'&phone='+phone+'&principalName='+principalName+
        '&principalDuty='+principalDuty+'&customerType='+customerType+'&clueStatus='+clueStatus
        +'&storeNumber='+storeNumber+'&brandLevel='+brandLevel+'&clueSource='+clueSource,
        success: function (result) {
        	if(result.code==0){
        		alert(result.msg);
        		$('#addLeads').modal('hide');
        		$('#leadsMod1').bootstrapTable('refresh', null);
        	}else{
        		alert("新增失败！");
        	}
        }
    });
	
}

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
	//跟进状态
	var clueStatus =$("#clueStatus").val();
	if(clueStatus!=null){
		searchData["clueStatus"] = clueStatus;
	}
	//开始时间
	var startTime =$("#startTime").val();
	if(clueStatus!=null){
		searchData["startTime"] = startTime;
	}
	//结束时间
	var endTime =$("#endTime").val();
	if(endTime!=null){
		searchData["endTime"] = endTime;
	}
	//店铺名称
	var customerName =$("#customerName").val();
	if(clueStatus!=null){
		searchData["customerName"] = customerName;
	}
	//最小店铺数量
	var minNumber =$("#minNumber").val();
	if(clueStatus!=null){
		searchData["minNumber"] = minNumber;
	}
	var maxNumber =$("#maxNumber").val();
	if(clueStatus!=null){
		searchData["maxNumber"] = maxNumber;
	}
	var brandLevel =$("#brandLevel").val();
	if(brandLevel!=null){
		searchData["brandLevel"] = brandLevel;
	}
	$('#leadsMod1').bootstrapTable('refresh', null);
}

//扩展bootstrap table的请求参数
function publicQueryParams(params){
	
	for(var k in searchData){
		params[k] = searchData[k];
	}
	//console.log("queryParams..." + JSON.stringify(params));
	return params;
}
function publicQuery(){
	//跟进状态
	var clueStatus =$("#clueStatus").val();
	if(clueStatus!=null){
		searchData["clueStatus"] = clueStatus;
	}
	//开始时间
	var startTime =$("#startTime").val();
	if(clueStatus!=null){
		searchData["startTime"] = startTime;
	}
	//结束时间
	var endTime =$("#endTime").val();
	if(endTime!=null){
		searchData["endTime"] = endTime;
	}
	//店铺名称
	var customerName =$("#customerName").val();
	if(clueStatus!=null){
		searchData["customerName"] = customerName;
	}
	//最小店铺数量
	var minNumber =$("#minNumber").val();
	if(clueStatus!=null){
		searchData["minNumber"] = minNumber;
	}
	var maxNumber =$("#maxNumber").val();
	if(clueStatus!=null){
		searchData["maxNumber"] = maxNumber;
	}
	var brandLevel =$("#brandLevel").val();
	if(brandLevel!=null){
		searchData["brandLevel"] = brandLevel;
	}
	$('#modUserTable').bootstrapTable('refresh', null);
}

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

//转入公海
function getSelections(){
	var selectDataArray = JSON.stringify($("#leadsMod1").bootstrapTable('getSelections'));
	selectDataArray = eval("(" + selectDataArray + ")");
	var idArray = new Array();
	if(selectDataArray.length==0){
		alert("请先选择要转入公海的信息");
		return;
	}
	for (var i = 0; i < selectDataArray.length; i++) {
		idArray[i] = selectDataArray[i].clueId;
	}
	//console.log("idArray : " + idArray);
	 $.ajax({
         type: "POST",
         dataType: "json",
         url: baseUrl+'custclue/updateClueSs',
         data:'idArray='+idArray+'&isPublic=1',
         success: function (result) {
             //加载最大可退金额
             alert(result.msg);
             $('#leadsMod1').bootstrapTable('refresh', null);
         }
     });
}



//修改功能
function updatesave(){
	var clueId =$("#update_clueId").val();
	var customerName =$("#update_customerName").val();
	if(customerName==null||customerName==""){
		alert("店铺名称不能为空");
		return;
	}
	
	var province =$("#update_province").val();
	if(province==null||province==""){
		alert("省份不能为空");
		return;
	}
	var city =$("#update_city").val();
	var area =$("#update_area").val();
	
	var principalName =$("#update_principalName").val();
	if(principalName==null||principalName==""){
		alert("联系人不能为空");
		return;
	}
	var principalDuty =$("#update_principalDuty").val();
	if(principalDuty==null||principalDuty==""){
		alert("联系人职位不能为空");
		return;
	}
	var phone =$("#update_phone").val();
	if(phone==null||phone==""){
		alert("手机号不能为空");
		return;
	}
	if(!phone.replace(/[^\d]/g,'')){
		alert("手机号码只能为数字");
		return;
	}
	if(phone.length!=11){
		alert("手机号码为11位数字");
		return;
	}
	var customerType =$("#update_customerType").val();
	if(customerType==null||customerType==""){
		alert("店铺属性不能为空");
		return;
	}
	var storeNumber =$("#update_storeNumber").val();
	if(storeNumber==null||storeNumber==""){
		alert("不能为空");
		return;
	}
	if(!storeNumber.replace(/[^\d]/g,'')){
		alert("店铺数量只能为数字");
		return;
	}

	var brandLevel =$("#update_brandLevel").val();
	
	var clueStatus =$("#update_clueStatus").val();
	
	var clueSource =$("#update_clueSource").val();
	$.ajax({
        type: "POST",
        dataType: "json",
        url: baseUrl+'custclue/updateCustClue',
        data:'clueId='+clueId+'&customerName='+customerName+'&province='+province+'&city='+city+'&area='+area+'&phone='+phone+'&principalName='+principalName+
        '&principalDuty='+principalDuty+'&customerType='+customerType+'&clueStatus='+clueStatus
        +'&storeNumber='+storeNumber+'&brandLevel='+brandLevel+'&clueSource='+clueSource,
        success: function (result) {
        	if(result.code==0){
        		alert(result.msg);
        		$('#updateLeads').modal('hide');
        		$('#leadsMod1').bootstrapTable('refresh', null);
        	}else{
        		alert("修改失败！");
        	}
        }
    });
}
