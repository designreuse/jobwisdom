<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/achievement_total.css" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<script>
	var cname = new Array();
	var cvalue = new Array();
	var earingCommissionDtos = eval('('+'${earingCommissionDtos}'+')');
	for (var i = 0; i < earingCommissionDtos.length; i++) {
		cname[i] = earingCommissionDtos[i].employeeName;
		cvalue[i] = earingCommissionDtos[i].commissionCalculate;
	}
	jQuery(function() {
		
		jQuery('.wages_content:gt(0)').hide();
		jQuery('.wages_content:gt(0)').hide();
		jQuery('.content_right ul li').click(
				function() {
					jQuery(this).addClass('active').siblings().removeClass(
							'active');
					jQuery('.wages_content').eq(jQuery(this).index()).show()
							.siblings('.wages_content').hide();

				})
	})
	//图表  
	jQuery(function() {
		earingHicharges();
	});
	function earingHicharges(){
		jQuery('#container').highcharts(
				{
					chart : {
						type : 'bar'
					},
					title : {
						text : '工资图表（元）'
					},

					xAxis : {
						categories : cname,
						title : {
							text : null
						}
					},
					yAxis : {
						min : 0,

						labels : {
							overflow : 'justify'
						}
					},
					tooltip : {
						valueSuffix : ' 元'
					},
					plotOptions : {
						bar : {
							dataLabels : {
								enabled : true
							}
						}
					},
					legend : {
						layout : 'vertical',
						align : 'right',
						verticalAlign : 'top',
						x : -40,
						y : 100,
						floating : true,
						borderWidth : 1,
						backgroundColor : '#FFFFFF',
						shadow : true
					},
					credits : {
						enabled : false
					},
					series : [{
						name : '业绩',
						data : cvalue
					}]
				});
	}
	var storeId = '${storeId }';
</script>
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class='content_right clearfix'>
					<ul class="clearfix">
						<li class="active">业绩图表</li>
						<li>业绩汇总</li>
						<li>业绩明细</li>
					</ul>
					<div class="wages_content">
						<div class="wages_content_datail">
							<div class="wages_content_datail_top">
								<input type="text" name="chargeTime" onfocus="WdatePicker({dateFmt:'MM'})">
								<select name="earingStore">
									<option value="0">搜索门店</option>
									<c:forEach items="${storeInfoDto }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
								</select>
								<button onclick="serchEaring()">查询</button>
							</div>
						</div>
						<div id="container" style="min-width: 1000px; height: 700px; overflow: ovelay"></div>
					</div>

					<div class="wages_content">
						<div class="wages_content_datail">
							<div class="wages_content_datail_top">
								搜索日期
								<input name="gatherStart" type="text" style="width: 116px; margin: 0 10px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								至
								<input name="gatherStop" type="text" style="width: 116px; margin-left: 5px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								<select name="gatherStore">
									<option value="0">搜索门店</option>
									<c:forEach items="${storeInfoDto }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
								</select>
								<button onclick="serchGather()">查询</button>
							</div>
						</div>
						<div class="wages_content_datail_table">
							<ul class="clearfix total_top">
								<li style="width: 171px">员工姓名</li>
								<li>服务业绩</li>
								<li>外卖业绩</li>
								<li>疗程业绩</li>
								<li>卡项业绩</li>
								<li>合计</li>
							</ul>
							<div class="wages_content_datail_table_">
								<table id="gather">
									<c:forEach items="${gatherCommissionDtos }" var="gatherCommissionDto" >
									<tr>
										<td>${gatherCommissionDto.employeeName }</td>
										<td>${gatherCommissionDto.projectCommissionCalculate }</td>
										<td>${gatherCommissionDto.goodsCommissionCalculate }</td>
										<td>${gatherCommissionDto.comboCommissionCalculate }</td>
										<td>${gatherCommissionDto.cardCommissionCalculate }</td>
										<td>${gatherCommissionDto.cardCommissionCalculate + gatherCommissionDto.projectCommissionCalculate + gatherCommissionDto.goodsCommissionCalculate + gatherCommissionDto.comboCommissionCalculate}</td>
									</tr>
									</c:forEach>
								</table>
							</div>
							<ul class="clearfix total_bottom">
								<li style="color: black; width: 171px">合计</li>
								<li>${projectCommissionCalculates }</li>
								<li>${goodsCommissionCalculates }</li>
								<li>${comboCommissionCalculates }</li>
								<li>${cardCommissionCalculates }</li>
								<li style="border-radius: 0 0 0 12px">${projectCommissionCalculates + goodsCommissionCalculates + comboCommissionCalculates + cardCommissionCalculates}</li>
							</ul>
						</div>
					</div>

					<div class="wages_content third">
						<div class="wages_content_datail">
							<div class="wages_content_datail_top">
								搜索日期
								<input type="text" name="detailStart" style="width: 80px; margin: 0 6px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								至
								<input type="text" name="detailStop" style="width: 80px; margin-left: 5px; margin-right: 10px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								业绩类型
								<select name="orderType">
									<option value="0">选择业绩类型</option>
									<option value="1">项目</option>
									<option value="2">商品</option>
									<option value="3">疗程</option>
									<option value="4">其他</option>
								</select>
								<select name="employeeId">
									<option value="0">选择员工</option>
									<c:forEach items="${employeeInfos }" var="employeeInfo"><option value="${employeeInfo.employeeId }">${employeeInfo.name }</option></c:forEach>
								</select>
								<select name="isAssign">
									<option value="3">选择指定方式</option>
									<option value="1">指定</option>
									<option value="0">非指定</option>
								</select>
								<select name="detailStore" style="width: 120px" onchange="selectEmployee(this.value)">
									<c:forEach items="${storeInfoDto }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
								</select>
								<button onclick="serchDetail()">查询</button>
							</div>
						</div>
						<div class="wages_content_datail_table">
							<ul class="clearfix total_top">
								<li>提成人员</li>
								<li>单号</li>
								<li>项目</li>
								<li>业绩类型</li>
								<li>是否指定</li>
								<li>客户类型</li>
								<li>业绩</li>
								<li>时间</li>
							</ul>
							<div class="wages_content_datail_table_">
								<table id="detail">
									<c:forEach items="${page.results }" var="detailCommissionDto">
										<tr>
											<td>${detailCommissionDto.employeeName }</td>
											<td>${detailCommissionDto.orderCode }</td>
											<c:choose>
												<c:when test="${detailCommissionDto.orderType == 1}"><td>项目</td></c:when>
												<c:when test="${detailCommissionDto.orderType == 2}"><td>商品</td></c:when>
												<c:when test="${detailCommissionDto.orderType == 3}"><td>疗程</td></c:when>
												<c:otherwise><td>其他</td></c:otherwise>
											</c:choose>
											<td>${detailCommissionDto.serverName }</td>
											<c:choose>
												<c:when test="${detailCommissionDto.isAssign == 1}"><td>指定</td></c:when>
												<c:when test="${detailCommissionDto.isAssign == 0}"><td>非指定</td></c:when>
											</c:choose>
											<td>${detailCommissionDto.levelName }</td>
											<td>${detailCommissionDto.commissionCalculate }</td>
											<td>${detailCommissionDto.chargeTime }</td>
										</tr>
									</c:forEach>
								</table>
								<%@ include file="/template/page.jsp" %>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	if (storeId != ''){
		jQuery("select[name='earingStore']").val(storeId);
		jQuery("select[name='earingStore']").hide();
		jQuery("select[name='gatherStore']").val(storeId);
		jQuery("select[name='gatherStore']").hide();
		jQuery("select[name='detailStore']").val(storeId);
		jQuery("select[name='detailStore']").hide();
	}
</script>
<script>

var storeInfoDtos = eval('('+'${storeInfoDtos}'+')');
console.log(storeInfoDtos);
function selectEmployee(storeId){
	jQuery("select[name='employeeInfo']").empty();
	jQuery("select[name='employeeInfo']").append(jQuery('<option value="0">请选择员工</option>'));
	for (var i = 0; i < storeInfoDtos.length; i++) {
		if (storeId == storeInfoDtos[i].storeId){
			for (var j = 0; j < storeInfoDtos[i].employeeInfos.length; j++) {
				var name = storeInfoDtos[i].employeeInfos[j].name;
				var id = storeInfoDtos[i].employeeInfos[j].employeeId;
				var html = '<option value='+id+'>'+name+'</option>';
				jQuery("select[name='employeeInfo']").append(jQuery(html));
			}
		}
	}
}

function serchEaring(){
	var storeId = jQuery("select[name='earingStore']").val();
	if (storeId==0){return dialog("请选择一个门店");}
	var data = {"storeId":storeId, "documentNum":1};
	if (jQuery("input[name='chargeTime']").val() != ""){
		data['chargeTime'] = jQuery("input[name='chargeTime']").val();
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/account/store/earning",
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
		async : false,
		success : function(data) {
			cname = new Array();
			cvalue = new Array();
			earingCommissionDtos = data.msg;
			for (var i = 0; i < earingCommissionDtos.length; i++) {
				cname[i] = earingCommissionDtos[i].employeeName;
				cvalue[i] = earingCommissionDtos[i].commissionCalculate;
			}
			console.log(cname);
			console.log(cvalue);
			earingHicharges();
		}
	});
}

function serchGather(){
	var storeId = jQuery("select[name='gatherStore']").val();
	var gatherStart = jQuery("input[name='gatherStart']").val();
	var gatherStop = jQuery("input[name='gatherStop']").val();
	if (storeId == 0){return dialog("请选择一个门店");}
	if (gatherStart == ""){gatherStart = "1970-01-01";}
	if (gatherStop == ""){gatherStop = "2050-01-01";}
	var data = {"storeId":storeId, "gatherStart":gatherStart, "gatherStop":gatherStop, "documentNum":2 };
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/account/store/earning",
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
		async : false,
		success : function(data) {
			jQuery("#gather").empty();
			for (var i = 0; i < data.msg.gatherCommissionDtos.length; i++) {
				var gatherCommissionDto = data.msg.gatherCommissionDtos[i];
				var projectCommissionCalculate = gatherCommissionDto.projectCommissionCalculate;
				var goodsCommissionCalculate = gatherCommissionDto.goodsCommissionCalculate;
				var comboCommissionCalculate = gatherCommissionDto.comboCommissionCalculate;
				var cardCommissionCalculate = gatherCommissionDto.cardCommissionCalculate;
				if (projectCommissionCalculate == null) projectCommissionCalculate = 0;
				if (goodsCommissionCalculate == null) goodsCommissionCalculate = 0;
				if (comboCommissionCalculate == null) comboCommissionCalculate = 0;
				if (cardCommissionCalculate == null) cardCommissionCalculate = 0;
				var html = '<tr>'+
								'<td>'+gatherCommissionDto.employeeName +'</td>'+
								'<td>'+ projectCommissionCalculate +'</td>'+
								'<td>'+ goodsCommissionCalculate +'</td>'+
								'<td>'+ comboCommissionCalculate +'</td>'+
								'<td>'+ cardCommissionCalculate +'</td>'+
								'<td>'+ (cardCommissionCalculate + projectCommissionCalculate + goodsCommissionCalculate + comboCommissionCalculate).toFixed(2) +'</td>'+
							'</tr>';
				jQuery("#gather").append(jQuery(html));
			}
			var projectCommissionCalculates = data.msg.projectCommissionCalculates;
			var goodsCommissionCalculates = data.msg.goodsCommissionCalculates;
			var comboCommissionCalculates = data.msg.comboCommissionCalculates;
			var cardCommissionCalculates = data.msg.cardCommissionCalculates;
			jQuery("#gather").parent("div").next().find("li").eq(1).text(projectCommissionCalculates.toFixed(2));
			jQuery("#gather").parent("div").next().find("li").eq(2).text(goodsCommissionCalculates.toFixed(2));
			jQuery("#gather").parent("div").next().find("li").eq(3).text(comboCommissionCalculates.toFixed(2));
			jQuery("#gather").parent("div").next().find("li").eq(4).text(cardCommissionCalculates.toFixed(2));
			jQuery("#gather").parent("div").next().find("li").eq(5).text((projectCommissionCalculates+goodsCommissionCalculates+comboCommissionCalculates+cardCommissionCalculates).toFixed(2));
		}
	});
}
var isUnbuildPagination = false;
function serchDetail(){
	isUnbuildPagination = true;
	changePage();
}
function changePage(){
	var storeId = jQuery("select[name='detailStore']").val();
	var detailStart = jQuery("input[name='detailStart']").val();
	var detailStop = jQuery("input[name='detailStop']").val();
	var employeeId = jQuery("select[name='employeeId']").val();
	var isAssign = jQuery("select[name='isAssign']").val();
	var orderType = jQuery("select[name='orderType']").val();
	if (detailStart == "") detailStart = "1970-1-01";
	if (detailStop == "") detailStop = "2050-1-01";
	if (employeeId == 0) employeeId = "";
	if (isAssign == 3) isAssign = "";
	if (orderType == 0) orderType = "";
	if (isUnbuildPagination == true) pageNo = 1;
	var data = {"storeId":storeId, "detailStart":detailStart, "detailStop":detailStop, "pageNo": pageNo, 
			"employeeId":employeeId, "isAssign":isAssign, "orderType":orderType, "documentNum":3};
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/account/store/earning/1",
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
		async : false,
		success : function(data) {
			if (isUnbuildPagination == true) {
				pageNo = data.msg.page.pageNo;
				pageSize = data.msg.page.pageSize;
				totalPage = data.msg.page.totalPage;
				totalRecord = data.msg.page.totalRecord;
				unbuildPagination();
				isUnbuildPagination=false;
			} 
			var detailCommissionDtos = data.msg.page.results;
			jQuery("#detail").empty();
			for (var i = 0; i < detailCommissionDtos.length; i++) {
				var detailCommissionDto = detailCommissionDtos[i];
				var html = '<tr>'+
								'<td>'+detailCommissionDto.employeeName +'</td>'+
								'<td>'+detailCommissionDto.orderCode +'</td>'+
								'<td>'+detailCommissionDto.orderType +'</td>'+
								'<td>'+detailCommissionDto.serverName +'</td>'+
								'<td>'+detailCommissionDto.isAssign +'</td>'+
								'<td>'+detailCommissionDto.levelName +'</td>'+
								'<td>'+detailCommissionDto.commissionCalculate +'</td>'+
								'<td>'+detailCommissionDto.chargeTime +'</td>'+
							'</tr>';
				jQuery("#detail").append(jQuery(html));
			}
		}
	});
}
</script>
</html>