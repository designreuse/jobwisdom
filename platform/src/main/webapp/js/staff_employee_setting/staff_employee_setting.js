$('#birthday').datetimepicker({
	  	language: 'zh-CN',
	  	format:'yyyy-MM-dd',  
	  	autoclose: 1,
	      todayBtn: 1,
	      startDate: "1900-00-00",
	      minuteStep: 10,
	      pickerPosition: "bottom-left",
	      format: 'yyyy-mm-dd',
	      minView: 'month',
	      todayBtn:false,
	      todayHighlight: true,
	      initialDate: new Date()
	  });
	  $('#workTime').datetimepicker({
	  	language: 'zh-CN',
	  	  format:'yyyy-MM-dd', 
	      autoclose: 1,
	      todayBtn: 1,
	      startDate: "1900-00-00",
	      minuteStep: 10,
	      pickerPosition: "bottom-left",
	      format: 'yyyy-mm-dd',
	      minView: 'month',
	      todayBtn:false,
	      todayHighlight: true,
	      initialDate: new Date()
	  });
 var postMessageNum = new Array("1","2","3","4","5","6","7","8","9");
 var postMessageIdAll = "";
 var postMessageObj = new Array();
 var cardSrc = "";
 var healthSrc= "";
 var otherImgSrc= "";
 var qiniu = new QiniuJsSDK();
 qiniu.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickfiles',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: 'http://7xk8dt.com1.z0.glb.clouddn.com/',   //bucket 域名，下载资源时用到，**必需**
	    container: 'container',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
		chunk_size : '2mb', //分块上传时，每片的体积
		auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
		init : {
			'FilesAdded' : function(up, files) {
				var err = false;
				plupload.each(files, function(file) {
					if(!qiniu.isImage(file.name)){
						err = true;
						return false;
					}
				});
				if(err) {
					dialog("只能上传图片文件");
					up.removeFile(files[0]);
					up.stop();
					$('#mask').remove();
				}
			},
			'BeforeUpload' : function(up, file) {
				$(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
			},
			'UploadProgress' : function(up, file) {
			},
			'FileUploaded' : function(up, file, info) {
				var domain = up.getOption('domain');
				var res = eval('(' + info + ')');
				var sourceLink = domain + res.key;
				$('#mask').remove();
				if($("#modal-title").attr("typeName") != ""){
					if($("#modal-title").attr("typeName") == "card"){
						if(cardSrc == ""){
							cardSrc = sourceLink;
						}else{
							cardSrc = cardSrc + "," + sourceLink;
						}
					}
	                if($("#modal-title").attr("typeName") == "health"){
	                	if(healthSrc == ""){
	                		healthSrc = sourceLink;
						}else{
							healthSrc = healthSrc + "," + sourceLink;
						}
					}
					if($("#modal-title").attr("typeName") == "otherImg"){
						if(otherImgSrc == ""){
							otherImgSrc = sourceLink;
						}else{
							otherImgSrc = otherImgSrc + "," + sourceLink;
						}
					}
					$("#showImg").attr("src", sourceLink);
					if($("img[imgName='showImgCurrent']").attr("src") == ""){
						$("img[imgName='showImgCurrent']").attr("src",sourceLink);
					}else{
						if($("img[imgName='showImgOther']").attr("src") == ""){
							$("img[imgName='showImgOther']").attr("src",$("img[imgName='showImgCurrent']").attr("src"));
							$("img[imgName='showImgCurrent']").attr("src",sourceLink);
						}else{
							$("#showMode").prepend($("<li><img src='"+sourceLink+"' imgName='showImgCurrent' alt=''/><img src='"+baseUrl+"app/img/delete.png' alt='' class='delete'/></li>"));
						}
					}
				}else{
					$("img[name='headImg']").attr("src",sourceLink);
					$("input[name='headImg']").val(sourceLink);
				}
				
			},
			'Error' : function(up, err, errTip) {
				dialog(errTip);
				$('#mask').remove();
			},
			'UploadComplete' : function() {
				dialog("UploadComplete");
			},
			'Key' : function(up, file) {
				var key = "lives/idCard/" + new Date().getTime();
				return key;
			}
		}
	});
 
 qiniu.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: 'pickHeadImg',       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: 'http://7xk8dt.com1.z0.glb.clouddn.com/',   //bucket 域名，下载资源时用到，**必需**
	    container: 'container',           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
		chunk_size : '2mb', //分块上传时，每片的体积
		auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
		init : {
			'FilesAdded' : function(up, files) {
				var err = false;
				plupload.each(files, function(file) {
					if(!qiniu.isImage(file.name)){
						err = true;
						return false;
					}
				});
				if(err) {
					dialog("只能上传图片文件");
					up.removeFile(files[0]);
					up.stop();
					$('#mask').remove();
				}
			},
			'BeforeUpload' : function(up, file) {
				$(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
			},
			'UploadProgress' : function(up, file) {
			},
			'FileUploaded' : function(up, file, info) {
				var domain = up.getOption('domain');
				var res = eval('(' + info + ')');
				var sourceLink = domain + res.key;
				$("img[name='headImg']").attr("src",sourceLink);
				$("input[name='headImg']").val(sourceLink);
				$('#mask').remove();
			},
			'Error' : function(up, err, errTip) {
				dialog(errTip);
				$('#mask').remove();
			},
			'UploadComplete' : function() {
				dialog("UploadComplete");
				$('#mask').remove();
			},
			'Key' : function(up, file) {
				var key = "lives/idCard/" + new Date().getTime();
				return key;
			}
		}
	});
 
 $(function(){
	var file = $(".file").text();
	if(file.length > 0){
		for(var i = 0 ;i < file.length; i++){
			for(var j = 0;j < postMessageNum.length;j++){
				if( file[i] == postMessageNum[j]){
					postMessageNum.splice(j,1);
				}
			}
		}
	}
	
	//如果是修改的情况，隐藏用户信息
	if($("input[name='employeeId']").val() != ""){
		$("#roleDIV").hide();
		$("#userDIV").hide();
	}
	
	var dropInp = new Array();
	dropInp.push($("input[name='bossSponsorName']"));
	dropInp.push($("input[name='sponsorName']"));
	if(employeeList != ""){
		assembleData(employeeList,"employeeNo,employeeName","employeeNo,employeeId",dropInp);
	}
	document.onkeydown = function(event) {
		var e = event || window.event || arguments.callee.caller.arguments[0];
		//按下键盘后焦点位置
		var focusElement = document.activeElement;
		
		supkeydown(e,focusElement);
	}
	
 });
 
 //动态添加岗位下拉信息
 $("#addPost").click(function(event){
	 event = event ? event : window.event; 
	 var obj = event.srcElement ? event.srcElement : event.target;
	 $("#postMessageNo").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").empty();
	 for(var j = 0;j < postMessageNum.length;j++){
		 if(j == 0){
			 $("#postMessageNo").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").append("<li><a href='javascript://' supa = 'supa' style='text-align: center;'>"+postMessageNum[j]+"</a></li>");
		 }else{
			 $("#postMessageNo").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").append("<li class='divider'></li>");
			 $("#postMessageNo").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").append("<li><a href='javascript://' supa = 'supa' style='text-align: center;'>"+postMessageNum[j]+"</a></li>");
			 
		 }
	 }
 });

 //绑定岗位修改事件
 $("#postMessageTbody").delegate("#postMessageAlterId","click",function(event){
	 event = event ? event : window.event; 
	 var obj = event.srcElement ? event.srcElement : event.target;
	 var postMessageName = $(obj).parents("#postMessageTR").find(".name").text();
	 var postMessageId = $(obj).parents("#postMessageTR").find(".file").attr("postMessageId");
	 var postMessageNo = $(obj).parents("#postMessageTR").find(".file").text();
	 postMessageNum.push(postMessageNo);
	 postMessageNum.sort(function(a,b){return a>b?1:-1});
     $("#postMessageNo").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").empty();
	 for(var j = 0;j < postMessageNum.length;j++){
		 if(j == 0){
			 $("#postMessageNo").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").append("<li><a href='javascript://' supa = 'supa' style='text-align: center;'>"+postMessageNum[j]+"</a></li>");
		 }else{
			 $("#postMessageNo").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").append("<li class='divider'></li>");
			 $("#postMessageNo").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").append("<li><a href='javascript://' supa = 'supa' style='text-align: center;'>"+postMessageNum[j]+"</a></li>");
			 
		 }
	 }
	 $("#postMessageNo").text(postMessageNo);
	 $("input[name='postMessageName']").val(postMessageName);
	 $("input[name='postMessageId']").val(postMessageId);
 });
 
 //跳转到员工设置界面
 $("#employeePage").click(function(){
	 window.location.href= baseUrl +  "/StaffEmployeeSetting/action/employeeSelect";
 });
 
//跳转到岗位设置界面
 $("#staffPage").click(function(){
	 window.location.href= baseUrl +  "/StaffEmployeeSetting/action/staffSetting";
 });
 
 //跳转到员工列表界面
 $("#addEmployee").click(function(){
	 window.location.href= baseUrl +  "/StaffEmployeeSetting/action/employeeSetting";
 });
 //跳转到员工列表
 $("#cancelEmployeeId").click(function(){
	 window.location.href= baseUrl +  "/StaffEmployeeSetting/action/employeeSelect";
 });
 //岗位信息模态框确定事件
 $("#postSave").click(function(event){
	 event = event ? event : window.event; 
	 var obj = event.srcElement ? event.srcElement : event.target;
	 var postMessageId = $("input[name='postMessageId']").val();
	 var postMessageNo = $("#postMessageNo").text();
	 var postMessageName = $("input[name='postMessageName']").val();
	 $.ajax({
			url : baseUrl +  "/StaffEmployeeSetting/action/operationPostMessage",
			data : "postMessageId="+postMessageId+"&postMessageNo="+postMessageNo+"&postMessageName="+postMessageName,
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){
				if(data.code == 0){
					$("input[name='postMessageId']").val("");
					$("#postMessageNo").text("");
					$("input[name='postMessageName']").val("");
					$('#create-new-staff').modal('hide');
					for(var j = 0;j < postMessageNum.length;j++){
        				if( postMessageNo == postMessageNum[j]){
        					postMessageNum.splice(j,1);
        				}
        			}
					if(postMessageId != ""){
						$("td[postMessageId='"+postMessageId+"']").text(postMessageNo);
						$("td[postMessageId='"+postMessageId+"']").next().text(postMessageName);
					}else{
						$("#postMessageTbody").append("<tr id='postMessageTR'>"+
	                                "<td class='file' postMessageId = '"+data.msg.postMessageId+"'>"+postMessageNo+"</td>"+
	                                "<td class='name'>"+postMessageName+"</td>"+
	                                "<td class='alter' id='postMessageAlterId' data-toggle='modal' data-target='#create-new-staff'><img src='"+baseUrl+"app/img/alter.png' alt=''/></td>"+
	                                "<td class='delete' id='postMessageDeleteId'><img src='"+baseUrl+"app/img/delete.png' alt=''/></td>"+
	                              "</tr>");
					}
				}
			},
	   });
 });

 //岗位信息模态框取消事件
 $("#postCancel").click(function(event){
	 event = event ? event : window.event; 
	 var obj = event.srcElement ? event.srcElement : event.target;
	 if($(obj).parents("#postMessageBody").find("input[name='postMessageId']").val() != ""){
		 var postMessage = $("#postMessageTableId").find(".file");
		 for(var i = 0;i < postMessage.length;i++){
			 if($(postMessage[i]).attr("postMessageId") == $(obj).parents("#postMessageBody").find("input[name='postMessageId']").val()){
				 for(var j = 0;j < postMessageNum.length;j++){
					if( $(postMessage[i]).text() == postMessageNum[j]){
						postMessageNum.splice(j,1);
					}
				 }
			 }
		 }
	 }
     $("input[name='postMessageId']").val("");
	 $("#postMessageNo").text("");
	 $("input[name='postMessageName']").val("");
 });
 
 
 //绑定岗位的删除事件
 $("#postMessageTbody").delegate("#postMessageDeleteId","click",function(event){
	 event = event ? event : window.event; 
	 var obj = event.srcElement ? event.srcElement : event.target;
	 var postMessageNo = $(obj).parents("#postMessageTR").find(".file").text();
	 $.ajax({
		url : baseUrl + "/StaffEmployeeSetting/action/deletePostMessage",
		data : "postMessageId="+$(obj).parents("#postMessageTR").find(".file").attr("postMessageId"),
		type: "post",
		async : false,
		dataType : "json",
		success : function(data){
			if(data.code == 0){
				$(obj).parents("#postMessageTR").remove();
				postMessageNum.push(postMessageNo);
				postMessageNum.sort(function(a,b){return a>b?1:-1});
			}
		},
	   });
 });
 
 //向岗位信息绑定click事件
  $("#postMessageTbody").delegate("#postMessageTR","click",function(event){
	  event = event ? event : window.event; 
	  var obj = event.srcElement ? event.srcElement : event.target;
	  $(".active").removeAttr("class");
	  $(obj).parents("#postMessageTR").attr("class","active");
	  postMessageIdAll = $(obj).parents("#postMessageTR").find(".file").attr("postMessageId");
	  if(postMessageIdAll != ""){
		  $.ajax({
				url : baseUrl +"StaffEmployeeSetting/action/selectByPostMessageId",
				data : "postMessageId="+postMessageIdAll,
				type: "post",
				async : false,
				dataType : "json",
				success : function(data){
					if(data.code == 0){
						$("#positionTbody").empty();
						$.each(data.msg,function(i,position){
							   if(position != null){
								   $("#positionTbody").append("<tr id='positionTR'>"+
										    		            "<td id='positionNameTD' positionId = '"+position.positionId+"'>"+position.positionName+"</td>"+
										                        "<td id='appointScaleTD'>"+position.appointScale+"</td>"+
										                        "<td id='appointMoneyTD'>"+position.appointMoney+"</td>"+
										                        "<td id='nonAppointScaleTD'>"+position.nonAppointScale+"</td>"+
										                        "<td id='nonAppointMoneyTD'>"+position.nonAppointMoney+"</td>"+
										                        "<td style='text-align: right;padding-right: 10px;' id='srcTD'><img id='positionAlter' src='"+baseUrl+"app/img/alter.png' alt='' class='img-left' data-toggle='modal' data-target='#create-new-post'/><img id='positionDelete' src='"+baseUrl+"app/img/delete.png' alt=''/></td>"+
										    	              "</tr>");
							   }
						});
					}
				},
		   });
	  }
  });
 
 //给保存职位信息绑定click事件
 $("#positionSave").click(function(event){
	 event = event ? event : window.event; 
	 var obj = event.srcElement ? event.srcElement : event.target;
	 var positionId = $("input[name='positionId']").val();
	 var positionName = $("input[name='positionName']").val();
	 var appointScale = $("input[name='appointScale']").val();
	 var appointMoney = $("input[name='appointMoney']").val();
	 var nonAppointScale = $("input[name='nonAppointScale']").val();
	 var nonAppointMoney = $("input[name='nonAppointMoney']").val();
	 
	 if(postMessageIdAll == ""){
		 postMessageIdAll = $(".active").find(".file").attr("postMessageId");	 
	 }
	 $.ajax({
			url : baseUrl + "StaffEmployeeSetting/action/operationPosition",
			data : "positionName="+positionName+"&positionId="+positionId+"&postMessageId="+postMessageIdAll+"&appointScale="+appointScale+"&appointMoney="+appointMoney+"&nonAppointScale="+nonAppointScale+"&nonAppointMoney="+nonAppointMoney,
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){
				if(data.code == 0){
					$("input[name='positionId']").val("");
					$("input[name='positionName']").val("");
					$("input[name='appointScale']").val("");
					$("input[name='appointMoney']").val("");
					$("input[name='nonAppointScale']").val("");
					$("input[name='nonAppointMoney']").val("");
					$('#create-new-post').modal('hide');
					if(positionId == ""){
						$("#positionTbody").append("<tr id='positionTR'>"+
		    		            "<td id='positionNameTD' positionId = '"+data.msg+"'>"+positionName+"</td>"+
		                        "<td id='appointScaleTD'>"+appointScale+"</td>"+
		                        "<td id='appointMoneyTD'>"+appointMoney+"</td>"+
		                        "<td id='nonAppointScaleTD'>"+nonAppointScale+"</td>"+
		                        "<td id='nonAppointMoneyTD'>"+nonAppointMoney+"</td>"+
		                        "<td style='text-align: right;padding-right: 10px;' id='srcTD'><img id='positionAlter' src='"+baseUrl+"app/img/alter.png' alt='' class='img-left' data-toggle='modal' data-target='#create-new-post'/><img id='positionDelete' src='"+baseUrl+"app/img/delete.png' alt=''/></td>"+
		    	              "</tr>");
					}else{
                        if(positionName != ""){
                        	$("td[positionId='"+positionId+"']").parents("#positionTR").find("#positionNameTD").text(positionName);
						}
						if(appointScale != ""){
							$("td[positionId='"+positionId+"']").parents("#positionTR").find("#appointScaleTD").text(appointScale);
						}
						if(appointMoney != ""){
							$("td[positionId='"+positionId+"']").parents("#positionTR").find("#appointMoneyTD").text(appointMoney);
						}
						if(nonAppointScale != ""){
							$("td[positionId='"+positionId+"']").parents("#positionTR").find("#nonAppointScaleTD").text(nonAppointScale);
						}
						if(nonAppointMoney != ""){
							$("td[positionId='"+positionId+"']").parents("#positionTR").find("#nonAppointMoneyTD").text(nonAppointMoney);
						}
						
					}
				}
			},
	   });
  });
 
 //给修改职位信息取消click事件
 $("#positionCancel").click(function(event){
	$("input[name='positionId']").val("");
	$("input[name='positionName']").val("");
	$("input[name='appointScale']").val("");
	$("input[name='appointMoney']").val("");
	$("input[name='nonAppointScale']").val("");
	$("input[name='nonAppointMoney']").val("");
 });
 
 //给删除职位信息绑定click事件
 $("#positionTbody").delegate("#positionDelete","click",function(event){
	 event = event ? event : window.event; 
	 var obj = event.srcElement ? event.srcElement : event.target;
	 
	 var positionId = $(obj).parents("#positionTR").find("#positionNameTD").attr("positionId");
	 $.ajax({
			url : baseUrl + "StaffEmployeeSetting/action/deletePosition",
			data : "positionId="+positionId,
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){
				if(data.code == 0){
					$(obj).parents("#positionTR").remove();
				}
			},
	   });
 });
 
 //职位信息绑定修改事件
 $("#positionTbody").delegate("#positionAlter","click",function(event){
	 event = event ? event : window.event; 
	 var obj = event.srcElement ? event.srcElement : event.target;
	 
	 var positionId = $(obj).parents("#positionTR").find("#positionNameTD").attr("positionId");
	 var positionName = $(obj).parents("#positionTR").find("#positionNameTD").text();
	 var appointScale = $(obj).parents("#positionTR").find("#appointScaleTD").text();
	 var appointMoney = $(obj).parents("#positionTR").find("#appointMoneyTD").text();
	 var nonAppointScale = $(obj).parents("#positionTR").find("#nonAppointScaleTD").text();
	 var nonAppointMoney = $(obj).parents("#positionTR").find("#nonAppointMoneyTD").text();
	 $("input[name='positionId']").val(positionId);
	 $("input[name='positionName']").val(positionName);
	 $("input[name='appointScale']").val(appointScale);
	 $("input[name='appointMoney']").val(appointMoney);
	 $("input[name='nonAppointScale']").val(nonAppointScale);
	 $("input[name='nonAppointMoney']").val(nonAppointMoney);
 });
 
 $("#cardModal").click(function(){
	 $("#modal-title").text("身份证");
	 $("#modal-title").attr("typeName","card");
	 var objImg = $("input[name='cardImg']").val();
	 if(objImg != ""){
		 var cardArray = objImg.split(",");
		 cardSrc = objImg;
		 imgAssignment(cardArray);
	 }else{
		 $(".selected-show").attr("style","");
		 imgInitialize();
	 }
 });
 
 $("#healthModal").click(function(){
	 $("#modal-title").text("健康证");
	 $("#modal-title").attr("typeName","health");
	 var objImg = $("input[name='healthProveImg']").val();
	 if(objImg != ""){
		 var healthArray = objImg.split(",");
		 healthSrc = objImg;
		 imgAssignment(healthArray);
	 }else{
		 $(".selected-show").attr("style","");
		 imgInitialize();
	 }
 });
 
 $("#otherImgModal").click(function(){
	 $("#modal-title").text("其它证件");
	 $("#modal-title").attr("typeName","otherImg");
	 $(".selected-show").attr("style","width:188px;height:200px; overflow-y:scroll;");
	 var objImg = $("input[name='otherImg']").val();
	 if(objImg != ""){
		 var otherImgArray = objImg.split(",");
		 otherImgSrc = objImg;
		 imgAssignment(otherImgArray);
	 }else{
		 imgInitialize();
	 }
 });
 
 function imgAssignment(obj){
	 var imgOther = $("img[imgName='showImgOther']");
	 for(var i=0;i<imgOther.length;i++){
		 $(imgOther[i]).parent().remove();
	 }
	 var imgCurrent = $("img[imgName='showImgCurrent']");
	 for(var i=0;i<imgCurrent.length;i++){
		 $(imgCurrent[i]).parent().remove();
	 }
	 $("#showImg").remove();
	 for(var i =0;i < obj.length;i++){
		 if(i == (obj.length - 1)){
			 $("#container").append("<img id='showImg' src='"+obj[i]+"' style='width: 100%; height: 100%' />");
			 $("#showMode").prepend("<li><img src='"+obj[i]+"' imgName='showImgCurrent' alt=''/><img src='"+baseUrl+"app/img/delete.png' alt='' class='delete'/></li>");
		 }else{
			 $("#showMode").append("<li><img src='"+obj[i]+"' imgName='showImgCurrent' alt=''/><img src='"+baseUrl+"app/img/delete.png' alt='' class='delete'/></li>");
		 }
	 }
 }
 
 function imgInitialize(){
	 var imgOther = $("img[imgName='showImgOther']");
	 for(var i=0;i<imgOther.length;i++){
		 $(imgOther[i]).parent().remove();
	 }
	 var imgCurrent = $("img[imgName='showImgCurrent']");
	 for(var i=0;i<imgCurrent.length;i++){
		 $(imgCurrent[i]).parent().remove();
	 }
	 $("#showImg").remove();
	 $("#container").append("<img id='showImg' src='' style='width: 100%; height: 100%' />");
	 $("#showMode").append("<li><img src='' imgName='showImgCurrent' alt=''/><img src='"+baseUrl+"app/img/delete.png' alt='' class='delete'/></li>"+
                           "<li><img src='' imgName='showImgOther' alt=''/><img src='"+baseUrl+"app/img/delete.png' alt='' class='delete'/></li>");
 }
 
 $("#sureImg").click(function(){
	 if($("#modal-title").attr("typeName") == "card"){
		 $("input[name='cardImg']").val(cardSrc);
	 }else if($("#modal-title").attr("typeName") == "health"){
		 $("input[name='healthProveImg']").val(healthSrc);
	 }else if($("#modal-title").attr("typeName") == "otherImg"){
		 $("input[name='otherImg']").val(otherImgSrc);
	 }
 });
 
 $("#saveEmployeeId").click(function(){
	 $("#saveForm").submit(); 
 });

 $("input[name='birthday']").click(function(){
	 $("#birthday").datetimepicker('show');
 });
 
 $("input[name='workTime']").click(function(){
	 $("#workTime").datetimepicker('show');
 });
 
 $("#prevEmployeePage").click(function(){
		 var searchEmployee = $("input[name='searchEmployee']").val();
		 var pageSize = $("#pageSize").text();
		 var pageNo = $("input[name='pageNo']").val();
		 var totalPage = $("input[name='totalPage']").val();
		 pageNo = Number(pageNo)-1;
		 window.location.href= baseUrl +  "/StaffEmployeeSetting/action/employeeSelect?searchEmployee="+searchEmployee+"&pageNo="+pageNo+"&totalPage="+totalPage+"&pageSize="+pageSize;
 });
 
$("#nextEmployeePage").click(function(){
		 var searchEmployee = $("input[name='searchEmployee']").val();
		 var pageSize = $("#pageSize").text();
		 var pageNo = $("input[name='pageNo']").val();
		 var totalPage = $("input[name='totalPage']").val();
		 pageNo = Number(pageNo)+1;
		 window.location.href= baseUrl +  "/StaffEmployeeSetting/action/employeeSelect?searchEmployee="+searchEmployee+"&pageNo="+pageNo+"&totalPage="+totalPage+"&pageSize="+pageSize;
 });

 $(document).delegate("#employeeAlter","click",function(e){
	 e = e ? e : window.e; 
	 var obj = e.srcElement ? e.srcElement : e.target;
	 var employeeId = $(obj).parents("#employeeTR").find("input[name='employeeId']").val();
	 window.location.href= baseUrl +  "/StaffEmployeeSetting/action/employeeUpdate?employeeId="+employeeId;
 });
 
 $(document).delegate("#employeeDelete","click",function(e){
	 e = e ? e : window.e; 
	 var obj = e.srcElement ? e.srcElement : e.target;
	 var employeeId = $(obj).parents("#employeeTR").find("input[name='employeeId']").val();
	 $.ajax({
			url : baseUrl + "StaffEmployeeSetting/action/deleteEmployeeId",
			data : "employeeId="+employeeId,
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){
				if(data.code == 0){
					 var searchEmployee = $("input[name='searchEmployee']").val();
					 var pageSize = $("#pageSize").text();
					 var pageNo = $("input[name='pageNo']").val();
					 var totalPage = $("input[name='totalPage']").val();
					 window.location.href= baseUrl +  "/StaffEmployeeSetting/action/employeeSelect?searchEmployee="+searchEmployee+"&pageNo="+pageNo+"&totalPage="+totalPage+"&pageSize="+pageSize;
				}
			},
	  });
 });
 
 $("#postMessageDIV").delegate("a[supa = 'supa']","click",function(e){
	 e = e ? e : window.e; 
	 var obj = e.srcElement ? e.srcElement : e.target;
	 var postMessageId = $(obj).attr("heddenvalue");
	 $.ajax({
			url : baseUrl + "StaffEmployeeSetting/action/selectByPostMessageId",
			data : "postMessageId="+postMessageId,
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){
				if(data.code == 0){
					$("#positionName").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").empty();
					$.each(data.msg,function(i,position){
						if(i == 0){
							$("#positionName").text(position.positionName);
							$("#positionName").parents("div[supdiv = 'supdiv']").find("input[name='postMessageId']").val(position.positionId);
							$("#positionName").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").append("<li><a href='javascript://' heddenValue = '"+position.positionId+"' supa = 'supa'>"+position.positionName+"</a></li>");
						}else{
							$("#positionName").parents("div[supdiv = 'supdiv']").find(".dropdown-menu").append("<li class='divider'></li><li><a href='javascript://' heddenValue = '"+position.positionId+"' supa = 'supa'>"+position.positionName+"</a></li>");
						}
					});
				}
			},
	  });
 });
 
 $("#workStateDIV").delegate("a[supa = 'supa']","click",function(e){
	 e = e ? e : window.e; 
	 var obj = e.srcElement ? e.srcElement : e.target;
	 if($(obj).attr("heddenvalue") == "已入职"){
		 $("input[name='workTime']").removeAttr("disabled");
	 }else{
		 $("#workTime").attr("disabled","disabled");
	 }
 });
 
