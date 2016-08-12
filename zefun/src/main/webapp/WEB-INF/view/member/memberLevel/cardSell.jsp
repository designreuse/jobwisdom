<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/card_sell.css" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<body>
	<script>
		jQuery(function() {
			jQuery('.wages_content:gt(0)').hide();
			jQuery('.wages_content:gt(0)').hide();
		})

		//选中
		jQuery(function() {
			jQuery('.wages_content_datail_top>span').click(
					function() {
						jQuery(this).addClass('active').siblings().removeClass(
								'active');
						jQuery('.date').eq(jQuery(this).index()).show()
								.siblings('.date').hide();
					});
		})

		jQuery(function() {
			jQuery('.wages_content_datail_top>em>span').click(
					function() {
						jQuery(this).addClass('active').siblings().removeClass(
								'active');
						jQuery(this).find('.date').eq(jQuery(this).index())
								.show().siblings('.date').hide();
					});

		})
	</script>

	<script>
		//储值走势图
		var balanceDays = eval('('+'${balanceAmount.blanckDates}'+')');
		var balanceValues = eval('('+'${balanceAmount.blanckValues}'+')');
		//消耗走势图
		var cardOutMoneyDays = eval('('+'${cardOutMoney.blanckDates}'+')');
		var cardOutMoneyValues = eval('('+'${cardOutMoney.blanckValues}'+')');
		//充值走势图
		var cardMoneyDays = eval('('+'${cardMoney.blanckDates}'+')');
		var cardMoneyValues = eval('('+'${cardMoney.blanckValues}'+')');
		
		
		jQuery(function() {
			initPictuer();
			initPictureTwo();
		});
		function initPictuer(){
			jQuery('#container1').highcharts(
					{
						chart : {
							type : 'line'
						},
						title : {
							text : '储值走势图'
						},

						xAxis : {
							categories : balanceDays
						},
						tooltip : {
							enabled : false,
							formatter : function() {
								return '<b>' + this.series.name + '</b><br/>'
										+ this.x + ': ' + this.y + '°C';
							}
						},
						plotOptions : {
							line : {
								dataLabels : {
									enabled : true
								},
								enableMouseTracking : true
							}
						},
						series : [{
							name : '日期/月',
							data : balanceValues
						}]
					});
		}
		
		function initPictureTwo(){
			jQuery('#container2').highcharts(
					{
						chart : {
							type : 'line'
						},
						title : {
							text : '新增/消耗储值图'
						},

						xAxis : {
							categories : cardOutMoneyDays
						},
						tooltip : {
							enabled : false,
							formatter : function() {
								return '<b>' + this.series.name + '</b><br/>'
										+ this.x + ': ' + this.y + '°C';
							}
						},
						plotOptions : {
							line : {
								dataLabels : {
									enabled : true
								},
								enableMouseTracking : true
							}
						},
						series : [
								{
									name : '消耗',
									data : cardOutMoneyValues
								},
								{
									name : '新增',
									data : cardMoneyValues
								}]
					});
		}
	</script>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class='content_right clearfix'>
					<ul class="clearfix">
						<li class="active">走势图</li>
						<li>卡项汇总</li>
					</ul>

					<div class="wages_content">
						<div class="wages_content_datail">
							<div class="wages_content_datail_top">
								<span class="active" onclick="selectFlowDateType(1, this)">年</span>
								<span onclick="selectFlowDateType(2, this)">月</span>
								<input type="hidden" name="flowTime">
								<input type="text" name="flowTime" onfocus="WdatePicker({dateFmt:'yyyy'})">
								<input type="text" style="display: none;" name="flowTime" onfocus="WdatePicker({dateFmt:'yyyy-MM'})">
								<script>
									var queryType = 1;
									function selectFlowDateType(type, span){
										queryType = type;
										if (type == 1){
											jQuery(span).addClass("active");
											jQuery(span).next().removeClass("active");
											jQuery("input[name='flowTime']").eq(1).show();
											jQuery("input[name='flowTime']").eq(2).hide();
										}
										if (type == 2){
											jQuery(span).addClass("active");
											jQuery(span).prev().removeClass("active");
											jQuery("input[name='flowTime']").eq(2).show();
											jQuery("input[name='flowTime']").eq(1).hide();
										}
									}
								</script>
								门店
								<select name="flowMoneyStore">
									<c:forEach items="${storeInfos }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
								</select>
								<button onclick="serchFlowMoney()">查询</button>
							</div>
						</div>
						<div id="container1" class="container_" style="min-width: 1000px; height: 600px"></div>
						<div style="height: 18px; background: #b8c7ea"></div>
						<div id="container2" class="container_" style="min-width: 1000px; height: 600px;"></div>
					</div>
					<div class="wages_content">
						<div class="wages_content_datail">
							<div class="wages_content_datail_top">
								<input type="text">
								至
								<input type="text">
								门店
								<select style="width: 90px">
									<option></option>
								</select>
								<button>查询</button>
							</div>
						</div>

						<div class="store_sell_table">
							<p>卡项销售</p>
							<table>
								<tr>
									<td rowspan="2">会员等级</td>
									<td colspan="3">开卡</td>
									<td colspan="3">充值</td>
									<td colspan="3">升级</td>
									<td colspan="3">新增卡金</td>
									<td rowspan="2">划卡</td>
									<td rowspan="2">卡金净增</td>
									<td rowspan="2">净增占比</td>
								</tr>
								<tr>
									<td>人数/卡费</td>
									<td>实冲</td>
									<td>赠送</td>
									<td>人数/卡费</td>
									<td>实冲</td>
									<td>赠送</td>
									<td>人数/卡费</td>
									<td>实冲</td>
									<td>赠送</td>
									<td>人数/卡费</td>
									<td>实冲</td>
									<td>赠送</td>
								</tr>
							</table>
							<div class="store_sell_table_content">
								<table>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</div>
							<table class="total_count" style="background: #b8c7ea !important">
								<tr>
									<td>合计</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</table>
						</div>


						<div class="store_sell_table">
							<p>当前汇款总计</p>
							<table>
								<tr>
									<td>会员等级</td>
									<td>当前卡内总余额</td>
									<td>总开卡数</td>
									<td>总续卡人数</td>
									<td>总储值余额</td>
									<td>总储值占比</td>
									<td>总实冲</td>
									<td>总赠送</td>
								</tr>
							</table>
							<div class="store_sell_table_content">
								<table>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
									<tr>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
										<td></td>
									</tr>
								</table>
							</div>
							<table class="total_count" style="background: #b8c7ea !important">
								<tr>
									<td>合计</td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</table>
						</div>
					</div>


				</div>
			</div>
		</div>
</body>
<script>
	function serchFlowMoney(){
		var flowTime = jQuery("input[name='flowTime']").eq(queryType).val();
		if (flowTime == ""){return dialog("选择一个时间");}
		var data = {"queryNum":1, "storeId":jQuery("select[name='flowMoneyStore']").val(), "queryType":queryType, "time":flowTime};
		jQuery.ajax({
			type : "post",
			url : baseUrl + "memberLevel/view/cardSellCount",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(e) {
				jQuery("#container1").empty();
				balanceDays = e.msg.balanceAmount.blanckDates;
				balanceValues = e.msg.balanceAmount.blanckValues;
				cardOutMoneyDays = e.msg.cardOutMoney.blanckDates;
				cardOutMoneyValues = e.msg.cardOutMoney.blanckValues;
				cardMoneyDays = e.msg.cardMoney.blanckDates;
				cardMoneyValues = e.msg.cardMoney.blanckValues;
				initPictuer();
				initPictureTwo();
			}
		});
	}
</script>
</html>