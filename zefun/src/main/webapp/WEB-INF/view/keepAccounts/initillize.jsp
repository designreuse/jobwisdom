<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/income.css"
	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<style>
.income_out_1 {
	width: 130px;
	height: 20px;
	margin-left: 8px;
	border: 1px solid black;
}
.zzc_add_income_content_left1 span{position:relative;}
.zzc_add_income_content_left1 .addcolor{position:absolute;}
.add_income_table{height:650px;border:1px solid #d5d5d5;    border-radius: 12px;
    overflow: hidden;}

</style>
<script src="http://open.web.meitu.com/sources/xiuxiu.js"
	type="text/javascript"></script>
<script type="text/javascript">
    
    function editPage (imgUrl) {
    	xiuxiu.setLaunchVars("cropPresets", "200*200");
    	xiuxiu.embedSWF("altContent2", 5, 700, 500);
    	xiuxiu.onInit = function (id)
    	{
            xiuxiu.setUploadType(3);
            if (imgUrl != null) {
            	xiuxiu.loadPhoto(qiniuUrl + imgUrl, false);
            }
    	}
    	xiuxiu.onSaveBase64Image = function (data, fileName, fileType, id)
    	{
            zccCallback(data);
            xiuxiu.onClose();
    	}
    	
    	xiuxiu.onDebug = function (data)
    	{
            dialog("网咯繁忙，请重试！");
    	}
    	xiuxiu.onClose = function (id)
    	{
            jQuery(".mask").hide();
    	}
    }
</script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper"
		style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<!--loading end-->
			<%@ include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px;">
				<%@ include file="/top.jsp"%>

				<div class="content_right clearfix">
					<div class="add_income">

						<p>
							<button onclick="jQuery('.zzc1').show()">新增收支</button>
							<button
								onclick="window.location.href='<%=basePath%>KeepAccounts/type'">新增收支类别</button>
						</p>
						<ul class="clearfix">
							<li>部门：<select id="daptid">
									<c:forEach items="${deptInfos }" var="deptInfo">
										<option value="${deptInfo.deptId }">${deptInfo.deptName }</option>
									</c:forEach>
							</select></li>
							<li>收支类型：<select id="income" onchange="changeType(this)">
									<option>收入</option>
									<option>支出</option>
							</select></li>
							<li ><i id="incometype">收入类别</i>：<select id="typename">
									<!-- 收入 -->
									<c:forEach items="${incometype }" var="incometype">
										<option value="${incometype.name }">${incometype.name }</option>
									</c:forEach>

							</select></li>
							<li>日期：<input type="text" id="date" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />至<input
								type="text" id="dates"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"</li>
							<li><button onclick="select()">确定</button></li>
						</ul>
					</div>
					<div class="add_income_table">

						<table>
							<tbody id="tables">
								<tr>
									<td>日期</td>
									<td>部门</td>
									<td>收支类型</td>
									<td>收支方式</td>
									<td>收支类别</td>
									<td>现金</td>
									<td>备注</td>
									<td>操作</td>
								</tr>

								<c:forEach var="initializ" items="${page.results}">
									<tr id="trs" name="${initializ.id}">
										<td>${initializ.date}</td>
										<td>${initializ.deptName}</td>
										<td>${initializ.type}</td>
										<td>${initializ.typeName}</td>
										<td>${initializ.priceName}</td>
										<td>${initializ.goodsPrice}</td>
										<td>${initializ.note}</td>
										<td onclick="update(${initializ.id},'${initializ.initializeImage}')" style="cursor: pointer">编辑</td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
						
					</div>
					<%@ include file="/template/page.jsp"%>
				</div>


			</div>
		</div>
	</div>
	<div class="zzc1" style="display: none">
		<div class="zzc_add_income">
			<p>新增收支</p>
			<div class="zzc_add_income_content">

				<div class="zzc_add_income_content_left1">
					<span><em>部门</em> <select id="bumen1">
							<c:forEach items="${deptInfos }" var="deptInfo">
								<option value="${deptInfo.deptId }">${deptInfo.deptName }</option>
							</c:forEach>
					</select></span> <span><em style="position: relative; left: 20px">金额</em> <input
						id="price1" value=" " type="Number"><i  class = "addcolor">*</i></span>
				</div>
				<p style="margin-top: 10px">
					<em style="margin-right: 10px">收支类型</em> <select id="shouzhi1"
						class="income_out" name="type">
						<option value="1">收入</option>
						<option value="2">支出</option>
					</select>
				</p>
				<div class="income_num">
					<div class="zzc_add_income_content_left2">
						<p>
							<em>收入日期</em><input id="date1" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</p>
						<p>
							<em>收入类别</em><select id="shouru1"><c:forEach
									items="${incometype }" var="incometype">
									<option value="${incometype.incometypeId}">${incometype.name }</option>
								</c:forEach></select>
						</p>
					</div>

					<div class="income_way" id="income_way">
						<p>收入方式</p>
						<span><input type="radio" value="现金" id="money1"  name="money" checked>现金</span> 
						<span><input type="radio" value="银联" id="money1" name="money">银联</span>
						<span><input type="radio" value="微信" id="money1" name="money">微信</span>
						<span><input type="radio" value="支付宝" id="money1" name="money">支付宝</span>
					</div>

					<div class="income_saying">
						<p>备注</p>
						<p>
							<textarea id="note1">	  
	 						 </textarea>
						</p>
					</div>
				</div>

				<div class="income_num_1" style="display: none; position: relative">
					<div class="zzc_add_income_content_left2">
						<p>
							<em>支出日期</em><input id="date2" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</p>
						<p>
							<em>支出类别</em><select id="shouru2"><c:forEach
									items="${incometypes }" var="incometypes">
									<option value="${incometypes.incometypeId}">${incometypes.name }</option>
								</c:forEach></select>
						</p>
					</div>
					<p style="position: absolute; right: 100px; top: -40px">
						<em style="position: relative; top: -90px">支出凭证</em>
						<img onclick="uploadImage(1)" name="img1" src="<%=basePath%>images/add_img.png"
							style="width: 108px; position: relative; left: 4px">
					</p>
					<div class="income_way" id="income_way2">
						<p>支出方式</p>
						<span><input type="radio" value="营收现金" id="money2" checked="" name="money_">营收现金</span>
						 <span><input type="radio" value="备用金" id="money2" name="money_">备用金</span>
					</div>

					<div class="income_saying">
						<p>备注</p>
						<p>
							<textarea id="note2"> </textarea>
						</p>
					</div>
				</div>
				<div class="income_button">
					<button onclick="Save()">保存</button>
					<button onclick="hideDiv(1)">取消</button>
				</div>
			</div>
		</div>
	</div>

	<div class="zzc2" style="display: none">
		<div class="zzc_add_income">
			<p>修改收支</p>
			<div class="zzc_add_income_content">

				<div class="zzc_add_income_content_left1">
					<span><em>部门</em> <select id="bumen3">
							<c:forEach items="${deptInfos }" var="deptInfo">
								<option value="${deptInfo.deptId }">${deptInfo.deptName }</option>
							</c:forEach>
					</select></span> <span><em style="position: relative; left: 20px">金额</em> <input
						id="price2" value=" " type="Number"> <i  class = "addcolor" >*</i></span>
				</div>
				<p style="margin-top: 10px">
					<em style="margin-right: 10px">收支类型</em> <select id="shouzhi2"
						class="income_out_1" name="type">
						<option value="1">收入</option>
						<option value="2">支出</option>
					</select>
				</p>
				<div class="income_num_2">
					<div class="zzc_add_income_content_left2">
						<p>
							<em>收入日期</em><input id="date3" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</p>
						<p>
							<em>收入类别</em><select id="shouru3"><c:forEach
									items="${incometype }" var="incometype">
									<option value="${incometype.incometypeId}">${incometype.name }</option>
								</c:forEach></select>
						</p>
					</div>

					<div class="income_way" id="income_way">
						<p>收入方式</p>
						<span><input type="radio" value="现金" id="money3" checked=""name="money3">现金</span> 
						<span><input type="radio" value="银联" id="money" name="money3">银联</span>
						<span><input type="radio" value="微信" id="money3" name="money3">微信</span>
						<span><input type="radio" value="支付宝" id="money3" name="money3">支付宝</span>
					</div>

					<div class="income_saying">
						<p>备注</p>
						<p>
							<textarea id="note3">	  
	 						 </textarea>
						</p>
					</div>
				</div>

				<div class="income_num_3" style="display: none; position: relative">
					<div class="zzc_add_income_content_left2">
						<p>
							<em>支出日期</em><input id="date4" type="text"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
						</p>
						<p>
							<em>支出类别</em><select id="shouru4"><c:forEach
									items="${incometypes }" var="incometypes">
									<option value="${incometypes.incometypeId}">${incometypes.name }</option>
								</c:forEach></select>
						</p>
					</div>
					<p style="position: absolute; right: 100px; top: -40px">
						<em style="position: relative; top: -90px">支出凭证</em>
						<img  onclick="uploadImage(2)" name="img2" src="<%=basePath%>images/add_img.png" style="width: 108px; position: relative; left: 4px">
					</p>
					<div class="income_way" id="income_way2">
						<p>支出方式</p>
						<span><input type="radio" value="营收现金" id="money4" checked="" name="money4">营收现金</span> 
						<span><input  type="radio" value="备用金" id="money4" name="money4">备用金</span>
					</div>
					<div class="income_saying">
						<p>备注</p>
						<p>
							<textarea id="note4">	  
	  </textarea>
						</p>
					</div>
				</div>
				<div class="income_button">
					<button onclick="updateSave()">保存</button>
					<button onclick="hideDiv(2)">取消</button>
				</div>
			</div>
		</div>
	</div>


		<div class="mask" style="display: none;">
			<div id="flashEditorOut" style="position: relative;">
				<span class="mask_close"
					style="position: absolute; right: -5px; top: -5px"><img
					onclick="xiuxiu.onClose();"
					src="<%=basePath%>images/seo_close.png"></span>
				<div id="altContent2">
					<h1>美图秀秀</h1>
				</div>
			</div>
		</div>


</body>
<script>
	function Trim(str){ 
	    return str.replace(/(^\s*)|(\s*$)/g, "");  //去除前后空格
	}
	var reg = /^\d+(\.\d+)?$/;  //数字正则
	var deptName = "";
	var type = "";
	var priceName = "";
	var date1 = "";
	var date2 = "";
	var changes = false;
	var incometype = eval('(' + '${incomenamez}' + ')');
	
	jQuery(function() {
		jQuery('.income_out').change(function() {
			var selected = jQuery(this).find('option:selected').text();
			if (selected == "收入") {
				jQuery('.income_num').show();
				jQuery('.income_num_1').hide()
			} else {
				jQuery('.income_num').hide();
				jQuery('.income_num_1').show()
			}

		})
		jQuery('.income_out_1').change(function() {
			var selected = jQuery(this).find('option:selected').text();
			if (selected == "收入") {
				jQuery('.income_num_2').show();
				jQuery('.income_num_3').hide()
			} else {
				jQuery('.income_num_2').hide();
				jQuery('.income_num_3').show()
			}

		})
		

	})
	function changeType(select) {
		var type = jQuery(select).val();
		jQuery('#typename').eq(0).empty();
		for (var i = 0; i < incometype.length; i++) {
			var names = incometype[i].name;
			var number = incometype[i].type;
			var html = '';
			if (type == '支出' && number == 2) {
				html = '<option value='+names+'>' + names + '</option>'
				jQuery("#incometype").text("支出类别");
			}
			if (type == '收入' && number == 1) {
				html = '<option value='+names+'>' + names + '</option>'
				jQuery("#incometype").text("收入类别");
			}
			jQuery("#typename").eq(0).append(jQuery(html));
			html = '';
		}
	}
	//分页
	function changePage() {
		var datas = "pageNo=" + pageNo + "&deptName=" + deptName + "&type=" + type + "&date1=" + date1 + "&date2=" + date2 + "&priceName="	+ priceName
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/initializeStoreFlowSelect",
			data : datas,
			dataType : "json",
			success : function(e) {
				pageNo = e.msg.pageNo;
				pageSize = e.msg.pageSize;
				totalPage = e.msg.totalPage;
				totalRecord = e.msg.totalRecord;
				if (changes) {
					changes = false;
					unbuildPagination();
				}
				jQuery("#tables [id='trs']").empty();
				jQuery.each(e.msg.results, function(n, value) {
					var html = '<tr id="trs" name="'+value.id+'">'
						+'		<td>'+value.date+'</td>'
						+'		<td>'+value.deptName+'</td>'
						+'		<td>'+value.type+'</td>'
						+'		<td>'+value.typeName+'</td>'
						+' 		<td>'+value.priceName+'</td>'
						+'		<td>'+value.goodsPrice+'</td>'
						+'		<td>'+value.note+'</td>'
						+'<td onclick="update('+value.id+','+value.initializeImage+')" style="cursor: pointer">编辑</td>'
						+'	</tr>'
					jQuery("#tables").append(jQuery(html));
				});
			}
		});
	}
	//新增保存
	function Save() {
		var type = jQuery("#shouzhi1 option:selected").val();
		var types = jQuery("#shouzhi1 option:selected").text();
		// 部门
		var dapt = jQuery("#bumen1  option:selected").val();
		// 收支名称
		var name = jQuery("#shouru" + type + "  option:selected").val();
		// 时间
		var date = jQuery("#date" + type).val();
		// 金额
		var price = Trim(jQuery("#price1").val());
		
		// 备注
		var note = Trim(jQuery("#note" + type).val());
		// 收支方式
		var typeName = jQuery("input[id=money" + type + "]:checked").val();
		var initializeImage = jQuery("img[name='img1']").attr("src");
		if(reg.test(price)==true){
			jQuery.ajax({
				type : "post",
				url : baseUrl + "KeepAccounts/initializeStoreFlow/add",
				data : JSON.stringify({
					"deptName" : dapt,
					"goodsPrice" : price,
					"typeName" : typeName,
					"date" : date,
					"incometypeType" : type,
					"isdeleted" : 0,
					"note" : note,
					"type" : types,
					"initializeImage" : initializeImage,
					"priceName" : name
				}),
				dataType : "json",
				contentType : "application/json",
				success : function(e) {
					jQuery('.zzc1').hide();
					dialog("新增成功");
					window.location.href = baseUrl +"KeepAccounts/initializeStoreFlow";
				}
			});
		}else{
			dialog("金钱必须是数字");
		}
	
	}
	//条件查询 
	function select() {
		changes=true;
		deptName = jQuery("#daptid  option:selected").val();
		type = jQuery("#income  option:selected").text();
		priceName = jQuery("#typename  option:selected").text();
		date1 = jQuery("#date").val();
		date2 = jQuery("#dates").val();
		changePage();
	}
	
  
   var number;
   var id ;
   function update(ids,initializeImage){
	   id =ids;
	   var date  =jQuery("tr[name="+id+"]").children('td').eq(0).text();
	   var dapt_name  =jQuery("tr[name="+id+"]").children('td').eq(1).text();
	   var type  =jQuery("tr[name="+id+"]").children('td').eq(2).text();
	   var price_name  =jQuery("tr[name="+id+"]").children('td').eq(3).text();
	   var type_name  =jQuery("tr[name="+id+"]").children('td').eq(4).text();
	   var price  =jQuery("tr[name="+id+"]").children('td').eq(5).text();
	   var note  =jQuery("tr[name="+id+"]").children('td').eq(6).text();
		if (type == "收入") {
			jQuery('.income_num_2').show();
			jQuery('.income_num_3').hide()
			jQuery("#shouzhi2").val(1);
			number=3;
			jQuery("input[name='money3'][value='"+price_name+"']").click();
		} else {
			jQuery('.income_num_2').hide();
			jQuery('.income_num_3').show()
			jQuery("#shouzhi2").val(2);
			number=4;
			jQuery("input[name='money4'][value='"+price_name+"']").click();
			var initializeImage = jQuery("img[name='img2']").attr("src",initializeImage);
		}
	  
	   jQuery("#date" + number).val(date);
	   var count1=jQuery("#bumen3").get(0).options.length;
	   for(var i=0;i<count1;i++){
		   if( jQuery("#bumen3").get(0).options[i].text == dapt_name)  {
			   jQuery("#bumen3").get(0).options[i].selected = true;          
		  		 break;  
		   }  
		   }
	   
	   var count=jQuery("#shouru" + number).get(0).options.length;
	   for(var i=0;i<count;i++){
	   if( jQuery("#shouru" + number).get(0).options[i].text == type_name)  {
		   jQuery("#shouru" + number).get(0).options[i].selected = true;          
	  		 break;  
	   }  
	   }
	 
	   jQuery("#price2").val(price);
	   jQuery("#note" + number).val(note);
	   jQuery('.zzc2').show();
   }
   
  function  updateSave(){
	  	var type =jQuery("#shouzhi2 option:selected").text();
	  	var incometypeType;
	  	if (type == "收入") {
			number=3;
			incometypeType=1;
		} else {
			number=4;
			incometypeType=2
		}
	    var note =jQuery("#note" + number).val();
	    var goods_price =jQuery("#price2").val();
		var price_name = jQuery("#shouru" + number + "  option:selected").val();
	    var type_name = jQuery("input[id=money" + number + "]:checked").val();
	    var dapt = jQuery("#bumen3  option:selected").val();
	    var data = jQuery("#date" + number).val();
	    var initializeImage =   jQuery("img[name='img2']").attr("src");
	  
	   jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/initializeStoreFlow/update",
			data : JSON.stringify({"initializeImage" : initializeImage ,"id" : id ,"deptName" : dapt ,"goodsPrice" : goods_price,"type" : type ,"typeName":type_name ,"note":note,"date":data,"priceName":price_name,"isdeleted":0,"incometypeType":incometypeType}),
			dataType : "json",
			contentType : "application/json",
			success : function(e) {
				dialog("修改成功");
				window.location.href = baseUrl +"KeepAccounts/initializeStoreFlow";
			}
		});
   }
  
  function zccCallback(dataBase64) {
		var key = "jobwisdom/project/" + new Date().getTime();
		var data = {
			"stringBase64" : dataBase64,
			"key" : key
		};
		jQuery('.cancelinput').click();
		jQuery.ajax({
				type : "POST",
				url : baseUrl + "qiniu/base64",
				data : JSON.stringify(data),
				contentType : "application/json",
				dataType : "json",
				async : true,
				beforeSend : function() {
					console.log("beforeSend upload image");
				},
				error : function() {
					console.log("upload image error");
				},
				complete : function() {
					console.log("complete upload image");
				},
				success : function(data) {
					var imageUrl = data.msg.imageUrl;
					var key = data.msg.key;
					jQuery("img[name='img"+imgName+"']").attr("src", qiniuUrl + key);
// 					console.log(qiniuUrl + key);

					
				}
			});
	}
  
  var imgName;
  function uploadImage(image){
	  jQuery(".mask").show();
	  editPage(null);
	  imgName = image;

	  
  }
  
  function hideDiv(type){
	  jQuery(".zzc"+type).hide();
	  jQuery("img[name='img"+type+"']").attr("src", "http://localhost:80/jobwisdom/images/add_img.png");
	  jQuery("#note" + type).val("");
	  jQuery("#price2").val("");
  }
 </script>

</html>