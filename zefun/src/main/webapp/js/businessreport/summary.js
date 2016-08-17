//初始化时间控件
var now = new Date() ;
    
jQuery("#startDate").val(format(now)) ;
jQuery("#endDate").val(format(now)) ;

jQuery("input[name='flowTime']").eq(1).val(now.getFullYear()+ "-" + (now.getMonth()+1<10?"0"+(now.getMonth()+1):now.getMonth()));
jQuery("input[name='flowTime']").eq(0).val(now.getFullYear());
var queryType = 1;
function selectFlowDateType(type, span){
	queryType = type;
	if (type == 1){
		jQuery(span).addClass("active");
		jQuery(span).next().removeClass("active");
		jQuery("input[name='flowTime']").eq(0).show();
		jQuery("input[name='flowTime']").eq(1).hide();
	}
	if (type == 2){
		jQuery(span).addClass("active");
		jQuery(span).prev().removeClass("active");
		jQuery("input[name='flowTime']").eq(1).show();
		jQuery("input[name='flowTime']").eq(0).hide();
	}
}

function chooseDay (type) {
	if (type == 1) {
		jQuery("#startDate").val(format(now)) ;
		jQuery("#endDate").val(format(now)) ;
	}
	else if (type == 2){
		jQuery("#startDate").val(format(getCurrentMonthFirst())) ;
		jQuery("#endDate").val(format(getCurrentMonthLast())) ;
	}
	else {
		jQuery("#startDate").val(now.getFullYear() + "-01-01") ;
		jQuery("#endDate").val(now.getFullYear() + "-12-31") ;
	}
}

function format(time) {
	var nowYear = time.getFullYear() ; //年
	var nowMonth = time.getMonth()+1<10?"0"+(time.getMonth()+1):time.getMonth() ; //月
	var nowDay = time.getDate()<10?"0"+time.getDate():time.getDate() ; //日期
	    
	var nowDate = nowYear+"-"+nowMonth+"-"+nowDay ;
	return nowDate;
}

function getCurrentMonthFirst(){
	 var date=new Date();
	 date.setDate(1);
	 return date;
	}
/**
 * 获取当前月的最后一天
 */
function getCurrentMonthLast(){
 var date=new Date();
 var currentMonth=date.getMonth();
 var nextMonth=++currentMonth;
 var nextMonthFirstDay=new Date(date.getFullYear(),nextMonth,1);
 var oneDay=1000*60*60*24;
 return new Date(nextMonthFirstDay-oneDay);
}

jQuery(function(){
    jQuery('.wages_content:gt(0)').hide();jQuery('.wages_content:gt(0)').hide();
    jQuery('.content_right>ul>li').click(function(){
      jQuery(this).addClass('active').siblings().removeClass('active');
	  jQuery('.wages_content').eq(jQuery(this).index()).show().siblings('.wages_content').hide();

	})
})
  
//选中
 jQuery(function(){
   jQuery('.wages_content_datail_top>span').click(function(){
     jQuery(this).addClass('active').siblings().removeClass('active');
	 jQuery(this).siblings('.date').eq(jQuery(this).index()).show().siblings('.date').hide();
   });
   
 })
  //走势图表 
  jQuery(function () {
	  var countList = dataMap.countList;
	  var priceList = dataMap.priceList;
	  var timeList = dataMap.timeList;
	  linear(timeList, countList, priceList);
	  
	  totailBusiness(storeId, format(now), format(now));
  });		

  function linear(timeList, countList, priceList) {
	  jQuery('#container1').highcharts({
	        chart: {
			    
	            type: 'line'
	        },
	        title: {
	            text: '营业走势图'
	        },
	     
	        xAxis: {
	            categories: timeList
	        },
	        tooltip: {
	            enabled: false,
	            formatter: function() {
	                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C';
	            }
	        },
	        plotOptions: {
	            line: {
	                dataLabels: {
	                    enabled: true
	                },
	                enableMouseTracking: true
	            }
	        },
	        series: [{
	            name: '现金流',
	            data: countList
	        },{
	            name: '劳动业绩',
	            data: priceList
	        }]
	    });
  }
  
  
  function piePage(pieData) {
	  jQuery('#container2').highcharts({
	        chart: {
			    backgroundColor:'#ebeff8',
	            type: 'pie',
	            options3d: {
	                enabled: true,
	                alpha: 45,
	                beta: 0
	            }
	        },
			 title: {
	            text: ''
	        },
	       exporting: { enabled:false },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                depth: 35,
	                dataLabels: {
	                    enabled: true,
	                    format: '{point.name}'
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: '支付占比',
	            data: pieData
	        }]
	    });
  }
  
  function trendPage () {
	  var dateType = jQuery("div[name='trendDIV']").find(".active").attr("dateType");
	  var time = "";
	  if (dateType == 1) {
		  time = jQuery("input[name='flowTime']").eq(0).val();
	  }
	  else {
		  time = jQuery("input[name='flowTime']").eq(1).val();
	  }
	  
	  var trendStoreId = jQuery("select[name='trendStoreId']").val();
	  
	  if (isEmpty(trendStoreId)) {
		  trendStoreId = storeId;
	  }
	  
	  jQuery.ajax({
			type : "post",
			url : baseUrl + "summary/action/trendBusinessSummar",
			data : "dateType=" + dateType + "&time="+ time + "&storeId=" + trendStoreId,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				var obj = e.msg;
				var countList = obj.countList;
				var priceList = obj.priceList;
				var timeList = obj.timeList;
				linear(timeList, countList, priceList);
			}
		});
  }
  
  function totailBusiness(totailStoreId, startDate, endDate) {
	  jQuery.ajax({
			type : "post",
			url : baseUrl + "summary/action/totailBusinessSummary",
			data : "storeId=" + totailStoreId + "&startDate="+ startDate + "&endDate=" + endDate,
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				var cashMap = e.msg.cashMap;
				var totalAmount = new Big(cashMap.totalAmount).toFixed(2);
				var cashAmount = new Big(cashMap.cashAmount).toFixed(2);
				var unionpayAmount = new Big(cashMap.unionpayAmount).toFixed(2);
				var wechatAmount = new Big(cashMap.wechatAmount).toFixed(2);
				var alipayAmount = new Big(cashMap.alipayAmount).toFixed(2);
				var groupAmount = new Big(cashMap.groupAmount).toFixed(2);
				var pieData = [
	                ['微信:'+wechatAmount+'元', Number(wechatAmount)],
	                ['团购:'+groupAmount+'元', Number(groupAmount)],
	                ['银联:'+unionpayAmount+'元', Number(unionpayAmount)],
	                ['支付宝:'+alipayAmount+'元', Number(alipayAmount)],
	                ['现金:'+cashAmount+'元', Number(cashAmount)]
	            ]
				piePage(pieData);
				
				var serverMoneyMap = e.msg.serverMoneyMap;
				
				var cardMoney = new Big(serverMoneyMap.cardMoney).toFixed(2);
				var debtMoney = new Big(serverMoneyMap.debtMoney).toFixed(2);
				var projectMoney = new Big(serverMoneyMap.projectMoney).toFixed(2);
				var goodsMoney = new Big(serverMoneyMap.goodsMoney).toFixed(2);
				var comboMoney = new Big(serverMoneyMap.comboMoney).toFixed(2);
		    	
				var cardProportion = new Big(serverMoneyMap.cardProportion).toFixed(2);
				var debtProportion = new Big(serverMoneyMap.debtProportion).toFixed(2);
				var projectProportion = new Big(serverMoneyMap.projectProportion).toFixed(2);
				var goodsProportion = new Big(serverMoneyMap.goodsProportion).toFixed(2);
				var comboProportion = new Big(serverMoneyMap.comboProportion).toFixed(2);
                
				jQuery(".business_canvas_right_content").find("table").empty();
				jQuery(".business_canvas_right_content").find("table").append('<tr>'+
																			    '<td>卡项销售</td>'+
																				'<td>'+new Big(cardMoney).toFixed(2)+'</td>'+
																				'<td>'+new Big(cardProportion).toFixed(2)+'%</td>'+
																			  '</tr>'+
																			  '<tr>'+
																			    '<td>挂账还款</td>'+
																				'<td>'+new Big(debtMoney).toFixed(2)+'</td>'+
																				'<td>'+new Big(debtProportion).toFixed(2)+'%</td>'+
																			  '</tr>'+
																			  '<tr>'+
																			    '<td>服务项目</td>'+
																				'<td>'+new Big(projectMoney).toFixed(2)+'</td>'+
																				'<td>'+new Big(projectProportion).toFixed(2)+'%</td>'+
																			  '</tr>'+
																			  '<tr>'+
																			    '<td>商品销售</td>'+
																				'<td>'+new Big(goodsMoney).toFixed(2)+'</td>'+
																				'<td>'+new Big(goodsProportion).toFixed(2)+'%</td>'+
																			  '</tr>'+
																			  '<tr>'+
																			    '<td>疗程销售</td>'+
																				'<td>'+new Big(comboMoney).toFixed(2)+'</td>'+
																				'<td>'+new Big(comboProportion).toFixed(2)+'%</td>'+
																			  '</tr>');
				var goodsMap = e.msg.goodsMap;
				jQuery("tr[name='goodsTR']").empty();
				jQuery("tr[name='goodsTR']").append('<td>'+new Big(goodsMap.goodsTotailCalculate).toFixed(2)+'</td>'+
													  '<td>'+goodsMap.goodsTotailSize+'</td>'+
													  '<td>'+new Big(goodsMap.mallListCalculate).toFixed(2)+'</td>'+
													  '<td>'+new Big(goodsMap.storeListCalculate).toFixed(2)+'</td>'+
													  '<td>'+goodsMap.mallSize+'</td>'+
													  '<td>'+goodsMap.storeSize+'</td>'+
													  '<td>'+new Big(goodsMap.mallProportion).toFixed(2)+'%</td>');
				
				var projectMap = e.msg.projectMap;
				jQuery("tr[name='projectTR']").empty();
				jQuery("tr[name='projectTR']").append('<td>'+new Big(projectMap.projectTotailCalculate).toFixed(2)+'</td>'+
													  '<td>'+projectMap.projectTotailSize+'</td>'+
													  '<td>'+new Big(projectMap.isAssignListCalculate).toFixed(2)+'</td>'+
													  '<td>'+new Big(projectMap.isNoAssignListCalculate).toFixed(2)+'</td>'+
													  '<td>'+projectMap.isAssignSize+'</td>'+
													  '<td>'+projectMap.isNoAssignSize+'</td>'+
													  '<td>'+new Big(projectMap.assignProportion).toFixed(2)+'%</td>');
				
				var cardMap = e.msg.cardMap;
				jQuery("tr[name='cardTR']").empty();
				jQuery("tr[name='cardTR']").append('<td>'+new Big(cardMap.cardTotailCalculate).toFixed(2)+'</td>'+
													'<td>'+new Big(cardMap.addMoney).toFixed(2)+'</td>'+
													'<td>'+new Big(cardMap.giveMoney).toFixed(2)+'</td>'+
													'<td>'+new Big(cardMap.payMoney).toFixed(2)+'</td>'+
													'<td>'+new Big(cardMap.changeMoney).toFixed(2)+'</td>');
				
				var comboTotailCalculate = e.msg.comboTotailCalculate;
				jQuery("tr[name='comboTR']").empty();
				jQuery("tr[name='comboTR']").append('<td>'+new Big(comboTotailCalculate).toFixed(2)+'</td>');
				
				jQuery("[name='cardTotailCalculate']").text(new Big(cardMap.cardTotailCalculate).toFixed(2));
				jQuery("[name='totailCalculate']").text(cardMap.cardTotailCalculate + projectMap.projectTotailCalculate + goodsMap.goodsTotailCalculate + comboTotailCalculate);
				jQuery("[name='projectTotailCalculate']").text(new Big(projectMap.projectTotailCalculate).toFixed(2));
				jQuery("[name='goodsTotailCalculate']").text(new Big(goodsMap.goodsTotailCalculate).toFixed(2));
				jQuery("[name='comboTotailCalculate']").text(new Big(comboTotailCalculate).toFixed(2));
				
				var customerMap = e.msg.customerMap;
				jQuery("tr[name='customerTR']").empty();
				jQuery("tr[name='customerTR']").append('<td>'+customerMap.customerTotailTime+'</td>'+
													  '<td>'+new Big(customerMap.customerAvgPrice).toFixed(2)+'</td>'+
													  '<td>'+customerMap.assignCustomerNum+'</td>'+
													  '<td>'+customerMap.noAssignCustomerNum+'</td>'+
													  '<td>'+customerMap.memberNum+'</td>'+
													  '<td>'+customerMap.noMemberNum+'</td>'+
													  '<td>'+customerMap.manNum+'</td>'+
													  '<td>'+customerMap.girlNum+'</td>');
			}
		});
  }
  
  function totailSelect() {
	  var businessStoreId = jQuery("select[name='businessStoreId']").val();
	  if (isEmpty(businessStoreId)) {
		  businessStoreId = storeId;
	  }
	  var startDate = jQuery("#startDate").val();
	  var endDate = jQuery("#endDate").val();
	  totailBusiness(businessStoreId, startDate, endDate);
  }