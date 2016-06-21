<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/income.css"
	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>

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
							<button onclick="jQuery('.zzc1').show('800')">新增收支</button>
							<button onclick="window.location.href='<%=basePath %>KeepAccounts/type'">新增收支类别</button>
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
							<li>收支方式：<select id="typename">
									<!-- 收入 -->
									<c:forEach items="${incometype }" var="incometype">
										<option value="${incometype.name }">${incometype.name }</option>
									</c:forEach>

							</select></li>
							<li>日期：<input type="text" id="date"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />至<input
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
									<td>收支名称</td>
									<td>现金</td>
									<td>备注</td>
								</tr>

								<c:forEach var="initializ" items="${page.results}">
									<tr id="trs">
										<td>${initializ.date}</td>
										<td>${initializ.deptName}</td>
										<td>${initializ.type}</td>
										<td>${initializ.typeName}</td>
										<td>${initializ.priceName}</td>
										<td>${initializ.goodsPrice}</td>
										<td>${initializ.note}</td>
									</tr>
								</c:forEach>


							</tbody>
						</table>
						<%@ include file="/template/page.jsp"%>
					</div>
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
						id="price1" value=" " type="text"></span>
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
							<em>收入类型</em><select id="shouru1"><c:forEach
									items="${incometype }" var="incometype">
									<option value="${incometype.name }">${incometype.name }</option>
								</c:forEach></select>
						</p>
					</div>

					<div class="income_way" id="income_way">
						<p>收入方式</p>
						<span><input type="radio" value="现金" id="money1" checked=""
							name="money">现金</span> <span><input type="radio"
							value="银联" id="money1" name="money">银联</span> <span><input
							type="radio" value="微信" id="money1" name="money">微信</span> <span><input
							type="radio" value="支付宝" id="money1" name="money">支付宝</span>
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
							<em>支出类型</em><select id="shouru2"><c:forEach
									items="${incometypes }" var="incometypes">
									<option value="${incometypes.name }">${incometypes.name }</option>
								</c:forEach></select>
						</p>
					</div>
					<p style="position: absolute; right: 100px; top: -40px">
						<em style="position: relative; top: -90px">支出凭证</em><img
							src="<%=basePath%>images/add_img.png"
							style="width: 108px; position: relative; left: 4px">
					</p>
					<div class="income_way" id="income_way2">
						<p>支出方式</p>
						<span><input type="radio" value="营收现金" id="money2"
							checked="" name="money_">营收现金</span> <span><input
							type="radio" value="备用金" id="money2" name="money_">备用金</span>
					</div>

					<div class="income_saying">
						<p>备注</p>
						<p>
							<textarea id="note2">	  
	  </textarea>
						</p>
					</div>
				</div>
				<div class="income_button">
					<button onclick="Save()">保存</button>
					<button onclick="jQuery('.zzc1').hide()">取消</button>
				</div>
			</div>
		</div>
	</div>


</body>
<script>
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
			}
			if (type == '收入' && number == 1) {
				html = '<option value='+names+'>' + names + '</option>'
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
					var html = '<tr id="trs">'
						+'		<td>'+value.date+'</td>'
						+'		<td>'+value.deptName+'</td>'
						+'		<td>'+value.type+'</td>'
						+'		<td>'+value.typeName+'</td>'
						+' 		<td>'+value.priceName+'</td>'
						+'		<td>'+value.goodsPrice+'</td>'
						+'		<td>'+value.note+'</td>'
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
		var name = jQuery("#shouru" + type + "  option:selected").text();
		// 时间
		var date = jQuery("#date" + type).val();
		// 金额
		var price = Trim(jQuery("#price1").val());
		var reg = /^\d+(\.\d+)?$/; 
		
		// 备注
		var note = Trim(jQuery("#note" + type).val());
		// 收支方式
		var typeName = jQuery("input[id=money" + type + "]:checked").val();
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
	
   function Trim(str){ 
        return str.replace(/(^\s*)|(\s*$)/g, ""); 
   }
</script>

</html>