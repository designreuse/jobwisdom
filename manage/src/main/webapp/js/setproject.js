/*已设置的项目列表*/
$('#projectListTable').bootstrapTable({
});
function projectListTable(value, row, index) {
  return [
    '<a class="like" style="color: blue;" href="#" data-toggle="modal" title="用户项目"><i class="icon-pencil"></i></a> ',
    '<a class="remove" style="color: blue; " href="javascript:todelete()"  title="删除"><i class="icon-remove"></i></a>',
  ].join('');
}


window.operateEvents = {
  'click .like': function (e, value, row, index) {
	  $('#projectMod').modal();

    //alert('You click like action, row: ' + JSON.stringify(row));
    $("#update_goodstype").val(row.goodsType);
    
    $("#update_goodsName").val(row.goodsNametype);
    $("#update_goodsDesc").val(row.goodsDesc);
    $("#update_goodsPrice").val(row.goodsPrice);
    $("#update_goodsId").val(row.goodsId);
    $("#update_goodsStock").val(row.goodsStock);
    
    
  },
  'click .remove': function (e, value, row, index) {
	    //alert('You click like action, row: ' + JSON.stringify(row));
	  $("#delete_goodsId").val(row.goodsId);
	  }
};


//删除
function todelete(){
	if(confirm("确认要删除该条客户信息吗？")){ 
		var goodsId =$("#delete_goodsId").val();
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        url: baseUrl+'project/deleteProject',
	        data:'goodsId='+goodsId,
	        success: function (result) {
	            alert(result.code);
	            
	        	$('#projectListTable').bootstrapTable('refresh', null);
	        }
	    });
	
	}
	
	
}
//修改
function update(){
	
	var goodsId =$("#update_goodsId").val();
	var goodsType =$("#update_goodstype").val();
	if(goodsType==null||goodsType==""){
		alert("项目类别不能为空！");
		return;
	}
	var goodsName =$("#update_goodsName").val();
	if(goodsName==null||goodsName==""){
		alert("项目名称不能为空！");
		return;
	}
	var goodsDesc =$("#update_goodsDesc").val();
	if(goodsType==1){
		if(goodsDesc==null||goodsDesc==""){
			alert("时间不能为空！");
			return;
		}
	}
	var goodsDesc1 =$("#update_goodsDesc").find("option:selected").text();
	var goodsPrice =$("#update_goodsPrice").val();
	if(goodsPrice==null||goodsPrice==""){
		alert("价格不能为空！");
		return;
	}
	var re = /^\d+(?=\.{0,1}\d+$|$)/ ;
	if(!re.test(goodsPrice)){
		alert("价格格式不正确");
		return;
	}	
	var goodsStock =$("#update_goodsStock").val();
	if(goodsStock==null||goodsStock==""){
		alert("库存量不能为空！");
		return;
	}
	if(isNaN(goodsStock)){
		alert("库存量只能输入数字！");
		return;
	}
	$.ajax({
      type: "POST",
      dataType: "json",
      url: baseUrl+'project/updateProject',
      data:'goodsType='+goodsType+'&goodsName='+goodsName+'&goodsDesc='+goodsDesc+'&goodsPrice='+goodsPrice+'&goodsId='+goodsId+'&goodsDesc1='+goodsDesc1+'&goodsStock='+goodsStock,
      success: function (result) {
          alert(result.code);
          $('#projectMod').modal('hide')
      	$('#projectListTable').bootstrapTable('refresh', null);
      }
  });
}

function addnew(){
	$('#addprojectMod').modal();
}
//新增项目功能
function add(){
	var goodsType =$("#add_goodstype").val();
	if(goodsType==null||goodsType==""){
		alert("项目类别不能为空！");
		return;
	}
	var goodsName =$("#add_goodsName").val();
	if(goodsName==null||goodsName==""){
		alert("项目名称不能为空！");
		return;
	}
	var goodsDesc =$("#add_goodsDesc").val();
	if(goodsType==1){
		if(goodsDesc==null||goodsDesc==""){
			alert("时间不能为空！");
			return;
		}
	}
	var goodsDesc1 =$("#add_goodsDesc").find("option:selected").text();
	
	var goodsPrice =$("#add_goodsPrice").val();
	if(goodsPrice==null||goodsPrice==""){
		alert("价格不能为空！");
		return;
	}
	var re = /^\d+(?=\.{0,1}\d+$|$)/ ;
	if(!re.test(goodsPrice)){
		alert("价格格式不正确");
		return;
	}
	var goodsStock =$("#add_goodsStock").val();
	if(goodsStock==null||goodsStock==""){
		alert("库存量不能为空！");
		return;
	}
	if(isNaN(goodsStock)){
		alert("库存量只能输入数字！");
		return;
	}

	$.ajax({
      type: "POST",
      dataType: "json",
      url: baseUrl+'project/addProject',
      data:'goodsType='+goodsType+'&goodsName='+goodsName+'&goodsDesc='+goodsDesc+'&goodsPrice='+goodsPrice+'&goodsDesc1='+goodsDesc1+'&goodsStock='+goodsStock,
      success: function (result) {
          alert(result.code);

      	$('#projectListTable').bootstrapTable('refresh', null);
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
	var goodstype =$("#goodstypename").val();
	if(goodstype!=null){
		searchData["goodsType"] = goodstype;
	}
	var goodsName =$("#goodsName").val();
	if(goodsName!=null){
		searchData["goodsName"] = goodsName;
	}
	$('#projectListTable').bootstrapTable('refresh', null);
}


