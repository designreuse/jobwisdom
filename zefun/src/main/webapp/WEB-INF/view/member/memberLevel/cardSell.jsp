<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<link rel="stylesheet" href="<%=basePath%>css/card_sell.css" type="text/css" />
<script type="text/javascript" src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<body>
	<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://112.74.210.155:3306/zb" user="root"  password="123456"/>
	
	<script>
	var sql = "<sql:query dataSource='${snapshot}' sql='select * from member_level' var='result' />";
	console.log(sql.rows);
	 jQuery(function(){
		    jQuery('.wages_content:gt(0)').hide();jQuery('.wages_content:gt(0)').hide();
		    jQuery('.content_right>ul>li').click(function(){
		      jQuery(this).addClass('active').siblings().removeClass('active');
			  jQuery('.wages_content').eq(jQuery(this).index()).show().siblings('.wages_content').hide();
			})
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
								<input name="startDate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								至
								<input name="stopDate" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								门店
								<select name="levelStore" style="width: 90px">
									<c:forEach items="${storeInfos }" var="storeInfo"><option value="${storeInfo.storeId }">${storeInfo.storeName }</option></c:forEach>
								</select>
								<button onclick="serchTotalLevel()">查询</button>
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
								</tr>
								<tr>
									<td>人次/卡费</td>
									<td>实冲</td>
									<td>赠送</td>
									<td>人次</td>
									<td>实冲</td>
									<td>赠送</td>
									<td>人次</td>
									<td>实冲</td>
									<td>赠送</td>
									<td>实充汇总</td>
									<td>赠送汇总</td>
									<td>汇总金额</td>
								</tr>
							</table>
							<div class="store_sell_table_content">
								<table  id="table">
									<c:set value="0" var="activePersionCount"/>
									<c:set value="0" var="activeFaceAmount"/>
									<c:set value="0" var="activePersentAmount"/>
									<c:set value="0" var="rechargePersionCount"/>
									<c:set value="0" var="rechargeFaceAmount"/>
									<c:set value="0" var="rechargePersentAmount"/>
									<c:set value="0" var="upgradePersionCount"/>
									<c:set value="0" var="upgradeFaceAmount"/>
									<c:set value="0" var="upgradePersentAmount"/>
									<c:set value="0" var="faceAmounts"/>
									<c:set value="0" var="persentAmounts"/>
									<c:set value="0" var="total"/>
									<c:set value="0" var="pushLevel"/>
									<c:set value="0" var="earyPushLevel"/>
									
									<c:forEach items="${levelTotalRemittanceDtosLows }" var="levelTotalRemittanceDtosLow">
									<c:set value="${activePersionCount + levelTotalRemittanceDtosLow.activePersionCount }" var="activePersionCount"/>
									<c:set value="${activeFaceAmount + levelTotalRemittanceDtosLow.activeFaceAmount }" var="activeFaceAmount"/>
									<c:set value="${activePersentAmount + levelTotalRemittanceDtosLow.activePersentAmount }" var="activePersentAmount"/>
									<c:set value="${rechargePersionCount + levelTotalRemittanceDtosLow.rechargePersionCount }" var="rechargePersionCount"/>
									<c:set value="${rechargeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount }" var="rechargeFaceAmount"/>
									<c:set value="${rechargePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount }" var="rechargePersentAmount"/>
									<c:set value="${upgradePersionCount + levelTotalRemittanceDtosLow.upgradePersionCount }" var="upgradePersionCount"/>
									<c:set value="${upgradeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount }" var="upgradeFaceAmount"/>
									<c:set value="${upgradePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount }" var="upgradePersentAmount"/>
									<c:set value="${faceAmounts + levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount }" var="faceAmounts"/>
									<c:set value="${persentAmounts + levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount }" var="persentAmounts"/>
									<c:set value="${total + levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount + levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount }" var="total"/>
									<c:set value="${pushLevel + levelTotalRemittanceDtosLow.pushLevel }" var="pushLevel"/>
									<c:set value="${earyPushLevel + levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount + levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount - levelTotalRemittanceDtosLow.pushLevel}" var="earyPushLevel"/>
									
										<tr>
											<td>${levelTotalRemittanceDtosLow.levelName }</td>
											<td>${levelTotalRemittanceDtosLow.activePersionCount }/${levelTotalRemittanceDtosLow.sellAmount }</td>
											<td>${levelTotalRemittanceDtosLow.activeFaceAmount }</td>
											<td>${levelTotalRemittanceDtosLow.activePersentAmount }</td>
											<td>${levelTotalRemittanceDtosLow.rechargePersionCount }</td>
											<td>${levelTotalRemittanceDtosLow.rechargeFaceAmount }</td>
											<td>${levelTotalRemittanceDtosLow.rechargePersentAmount }</td>
											<td>${levelTotalRemittanceDtosLow.upgradePersionCount }</td>
											<td>${levelTotalRemittanceDtosLow.upgradeFaceAmount }</td>
											<td>${levelTotalRemittanceDtosLow.upgradePersentAmount }</td>
											<td>${levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount }</td>
											<td>${levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount }</td>
											<td>${levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount + levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount}</td>
											<td>${levelTotalRemittanceDtosLow.pushLevel }</td>
											<td>${levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount + levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount - levelTotalRemittanceDtosLow.pushLevel }</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<table id="totalTable" class="total_count" style="background: #b8c7ea !important">
								<tr>
									<td>合计</td>
									<td>${activePersionCount }</td>
									<td>${activeFaceAmount }</td>
									<td>${activePersentAmount }</td>
									<td>${rechargePersionCount }</td>
									<td>${rechargeFaceAmount }</td>
									<td>${rechargePersentAmount }</td>
									<td>${upgradePersionCount }</td>
									<td>${upgradeFaceAmount }</td>
									<td>${upgradePersentAmount }</td>
									<td>${faceAmounts }</td>
									<td>${persentAmounts }</td>
									<td>${total }</td>
									<td>${pushLevel }</td>
									<td>${earyPushLevel }</td>
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
									<td>总续卡人次</td>
									<td>总储值余额</td>
									<td>总储值占比</td>
									<td>总实冲</td>
									<td>总赠送</td>
								</tr>
							</table>
							<div class="store_sell_table_content">
								<table>
									<c:set var="balanceAmounts" value="0"/>
									<c:set var="activateLevelCounts" value="0"/>
									<c:set var="activateLevelGoOnCounts" value="0"/>
									<c:set var="totalAmounts" value="0"/>
									<c:set var="totalRechargeAmounts" value="0"/>
									<c:set var="totalPresentAmounts" value="0"/>
									
									<c:forEach items="${levelTotalRemittanceDtos }" var="levelTotalRemittanceDto">
									
									<c:set var="balanceAmounts" value="${balanceAmounts + levelTotalRemittanceDto.balanceAmount }" />
									<c:set var="activateLevelCounts" value="${activateLevelCounts + levelTotalRemittanceDto.activateLevelCount }" />
									<c:set var="activateLevelGoOnCounts" value="${activateLevelGoOnCounts + levelTotalRemittanceDto.activateLevelGoOnCount }" />
									<c:set var="totalAmounts" value="${totalAmounts + levelTotalRemittanceDto.totalAmount }" />
									<c:set var="totalRechargeAmounts" value="${totalRechargeAmounts + levelTotalRemittanceDto.totalRechargeAmount }" />
									<c:set var="totalPresentAmounts" value="${totalPresentAmounts + levelTotalRemittanceDto.totalPresentAmount }" />
									
										<tr>
											<td>${levelTotalRemittanceDto.levelName }</td>
											<td>${levelTotalRemittanceDto.balanceAmount }</td>
											<td>${levelTotalRemittanceDto.activateLevelCount }</td>
											<td>${levelTotalRemittanceDto.activateLevelGoOnCount }</td>
											<td>${levelTotalRemittanceDto.totalAmount }</td>
											<td>${levelTotalRemittanceDto.totalAmount/levelTotalRemittanceDto.levelTotalAmount*100 }%</td>
											<td>${levelTotalRemittanceDto.totalRechargeAmount }</td>
											<td>${levelTotalRemittanceDto.totalPresentAmount }</td>
										</tr>
									</c:forEach>
								</table>
							</div>
							<table class="total_count" style="background: #b8c7ea !important">
								<tr>
									<td>合计</td>
									<td><c:out value="${balanceAmounts }"/></td>
									<td><c:out value="${activateLevelCounts }"/></td>
									<td><c:out value="${activateLevelGoOnCounts }"/></td>
									<td><c:out value="${totalAmounts }"/></td>
									<td>100%</td>
									<td><c:out value="${totalRechargeAmounts }"/></td>
									<td><c:out value="${totalPresentAmounts }"/></td>
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
	
	function serchTotalLevel(){
		var startDate = jQuery("input[name='startDate']").val();
		var stopDate = jQuery("input[name='stopDate']").val();
		var levelStore = jQuery("select[name='levelStore']").val();
		if (startDate == ""){startDate = "1970-01-01";}
		if (stopDate == ""){stopDate = "2050-01-01";}
		var data = {"startDate":startDate, "stopDate":stopDate, "storeId":levelStore, "queryNum":2}
		jQuery.ajax({
			type : "post",
			url : baseUrl + "memberLevel/view/cardSellCount",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(e) {
				var levelTotalRemittanceDtos = e.msg;
				jQuery("#table").empty();
				var activePersionCount = 0;
				var activeFaceAmount = 0;
				var activePersentAmount = 0;
				var rechargePersionCount = 0;
				var rechargeFaceAmount = 0;
				var rechargePersentAmount = 0;
				var upgradePersionCount = 0;
				var upgradeFaceAmount = 0;
				var upgradePersentAmount = 0;
				var faceAmounts = 0;
				var persentAmounts = 0;
				var total = 0;
				var pushLevel = 0;
				var erayPushLevel = 0;
				for (var i = 0; i < levelTotalRemittanceDtos.length; i++) {
					
					var levelTotalRemittanceDtosLow = levelTotalRemittanceDtos[i];
					
					activePersionCount += levelTotalRemittanceDtosLow.activePersionCount;
					activeFaceAmount += levelTotalRemittanceDtosLow.activeFaceAmount;
					activePersentAmount += levelTotalRemittanceDtosLow.activePersentAmount;
					rechargePersionCount += levelTotalRemittanceDtosLow.rechargePersionCount;
					rechargeFaceAmount += levelTotalRemittanceDtosLow.rechargeFaceAmount;
					rechargePersentAmount += levelTotalRemittanceDtosLow.rechargePersentAmount;
					upgradePersionCount += levelTotalRemittanceDtosLow.upgradePersionCount;
					upgradeFaceAmount += levelTotalRemittanceDtosLow.upgradeFaceAmount;
					upgradePersentAmount += levelTotalRemittanceDtosLow.upgradePersentAmount;
					faceAmounts += levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount;
					persentAmounts += levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount;
					total += levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount + levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount;
					pushLevel += levelTotalRemittanceDtosLow.pushLevel;
					erayPushLevel += levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount + levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount - levelTotalRemittanceDtosLow.pushLevel;
					var html = '<tr>'+
									'<td>'+levelTotalRemittanceDtosLow.levelName+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.activePersionCount+'/'+levelTotalRemittanceDtosLow.sellAmount+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.activeFaceAmount+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.activePersentAmount+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.rechargePersionCount+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.rechargeFaceAmount+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.rechargePersentAmount+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.upgradePersionCount+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.upgradeFaceAmount+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.upgradePersentAmount+'</td>'+
									'<td>'+(levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount)+'</td>'+
									'<td>'+(levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount)+'</td>'+
									'<td>'+(levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount + levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount)+'</td>'+
									'<td>'+levelTotalRemittanceDtosLow.pushLevel+'</td>'+
									'<td>'+(levelTotalRemittanceDtosLow.activeFaceAmount + levelTotalRemittanceDtosLow.rechargeFaceAmount + levelTotalRemittanceDtosLow.upgradeFaceAmount + levelTotalRemittanceDtosLow.activePersentAmount + levelTotalRemittanceDtosLow.rechargePersentAmount + levelTotalRemittanceDtosLow.upgradePersentAmount - levelTotalRemittanceDtosLow.pushLevel)+'</td>'+
								'</tr>';
					jQuery("#table").append(jQuery(html));
				}
				jQuery("#totalTable").empty();
				var html = '<tr>'+
								'<td>合计</td>'+
								'<td>'+activePersionCount+'</td>'+
								'<td>'+activeFaceAmount+'</td>'+
								'<td>'+activePersentAmount+'</td>'+
								'<td>'+rechargePersionCount+'</td>'+
								'<td>'+rechargeFaceAmount+'</td>'+
								'<td>'+rechargePersentAmount+'</td>'+
								'<td>'+upgradePersionCount+'</td>'+
								'<td>'+upgradeFaceAmount+'</td>'+
								'<td>'+upgradePersentAmount+'</td>'+
								'<td>'+faceAmounts+'</td>'+
								'<td>'+persentAmounts+'</td>'+
								'<td>'+total+'</td>'+
								'<td>'+pushLevel+'</td>'+
								'<td>'+erayPushLevel+'</td>'+
							'</tr>';
				jQuery("#totalTable").append(jQuery(html));
			}
		});
	}
</script>
</html>