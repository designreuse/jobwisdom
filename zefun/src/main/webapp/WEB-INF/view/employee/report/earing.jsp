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
								<input type="text" style="width: 80px; margin: 0 6px">
								至
								<input type="text" style="width: 80px; margin-left: 5px; margin-right: 10px">
								业绩类型
								<select name="orderType">
									<option value="0">选择业绩类型</option>
									<option value="1">项目</option>
									<option value="2">商品</option>
									<option value="3">疗程</option>
									<option value="4">其他</option>
								</select>
								<select name="employeeInfo">
									<option value="0">选择员工</option>
									<c:forEach items="${employeeInfos }" var="employeeInfo"><option value="${employeeInfo.employeeId }">${employeeInfo.name }</option></c:forEach>
								</select>
								<select>
									<option value="0">选择指定方式</option>
									<option value="1">指定</option>
									<option value="2">非指定</option>
								</select>
								<select style="width: 120px" onchange="selectEmployee(this.value)">
									<c:forEach items="${storeInfoDto }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
								</select>
								<button>查询</button>
							</div>
						</div>
						<div class="wages_content_datail_table">
							<ul class="clearfix total_top">
								<li>提成人员</li>
								<li>单号</li>
								<li>项目</li>
								<li>业绩类型</li>
								<li>数量</li>
								<li>是否指定</li>
								<li style="width: 115px">客户类型</li>
								<li style="width: 115px">业绩</li>
								<li style="width: 115px">时间</li>
							</ul>
							<div class="wages_content_datail_table_">

								<table id="">
									<tr>
										<td>夜景神</td>
										<td>201608021222</td>
										<td>项目1号</td>
										<td>服务项目</td>
										<td>12</td>
										<td>是</td>
										<td>尊贵会员</td>
										<td>98.00</td>
										<td>2016-12-9 12:12:12</td>
									</tr>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
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
				var html = '<tr>'+
								'<td>'+gatherCommissionDto.employeeName +'</td>'+
								'<td>'+gatherCommissionDto.projectCommissionCalculate +'</td>'+
								'<td>'+gatherCommissionDto.goodsCommissionCalculate +'</td>'+
								'<td>'+gatherCommissionDto.comboCommissionCalculate +'</td>'+
								'<td>'+gatherCommissionDto.cardCommissionCalculate +'</td>'+
								'<td>'+ (gatherCommissionDto.cardCommissionCalculate + gatherCommissionDto.projectCommissionCalculate + gatherCommissionDto.goodsCommissionCalculate + gatherCommissionDto.comboCommissionCalculate) +'</td>'+
							'</tr>';
				jQuery("#gather").append(jQuery(html));
			}
		}
	});
}

</script>
</html>