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
	  
	  if (isEmpty(storeIdVal)) {
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
				var totalAmount = cashMap.totalAmount;
				var cashAmount = cashMap.cashAmount;
				var unionpayAmount = cashMap.unionpayAmount;
				var wechatAmount = cashMap.wechatAmount;
				var alipayAmount = cashMap.alipayAmount;
				var groupAmount = cashMap.groupAmount;
				var pieData = [
	                ['微信:'+wechatAmount+'元', wechatAmount],
	                ['团购:'+groupAmount+'元', groupAmount],
	                ['银联:'+unionpayAmount+'元', unionpayAmount],
	                ['支付宝:'+alipayAmount+'元', alipayAmount],
	                ['现金:'+cashAmount+'元', cashAmount]
	            ]
				piePage(pieData);
				
				var serverMoneyMap = e.msg.serverMoneyMap;
				
				var cardMoney = serverMoneyMap.cardMoney;
				var debtMoney = serverMoneyMap.debtMoney;
				var projectMoney = serverMoneyMap.projectMoney;
				var goodsMoney = serverMoneyMap.goodsMoney;
				var comboMoney = serverMoneyMap.comboMoney;
		    	
				var cardProportion = serverMoneyMap.cardProportion;
				var debtProportion = serverMoneyMap.debtProportion;
				var projectProportion = serverMoneyMap.projectProportion;
				var goodsProportion = serverMoneyMap.goodsProportion;
				var comboProportion = serverMoneyMap.comboProportion;
                
				jQuery(".business_canvas_right_content").find("table").empty();
				jQuery(".business_canvas_right_content").find("table").append('<tr>'+
																			    '<td>卡项销售</td>'+
																				'<td>'+cardMoney+'</td>'+
																				'<td>'+cardProportion+'%</td>'+
																			  '</tr>'+
																			  '<tr>'+
																			    '<td>挂账还款</td>'+
																				'<td>'+debtMoney+'</td>'+
																				'<td>'+debtProportion+'%</td>'+
																			  '</tr>'+
																			  '<tr>'+
																			    '<td>服务项目</td>'+
																				'<td>'+projectMoney+'</td>'+
																				'<td>'+projectProportion+'%</td>'+
																			  '</tr>'+
																			  '<tr>'+
																			    '<td>商品销售</td>'+
																				'<td>'+goodsMoney+'</td>'+
																				'<td>'+goodsProportion+'%</td>'+
																			  '</tr>'+
																			  '<tr>'+
																			    '<td>疗程销售</td>'+
																				'<td>'+comboMoney+'</td>'+
																				'<td>'+comboProportion+'%</td>'+
																			  '</tr>');
				var goodsMap = e.msg.goodsMap;
				jQuery("tr[name='goodsTR']").empty();
				jQuery("tr[name='goodsTR']").append('<td>'+goodsMap.goodsTotailCalculate+'</td>'+
													  '<td>'+goodsMap.goodsTotailSize+'</td>'+
													  '<td>'+goodsMap.mallListCalculate+'</td>'+
													  '<td>'+goodsMap.storeListCalculate+'</td>'+
													  '<td>'+goodsMap.mallSize+'</td>'+
													  '<td>'+goodsMap.storeSize+'</td>'+
													  '<td>'+goodsMap.mallProportion+'%</td>');
				
				var projectMap = e.msg.projectMap;
				jQuery("tr[name='projectTR']").empty();
				jQuery("tr[name='projectTR']").append('<td>'+projectMap.projectTotailCalculate+'</td>'+
													  '<td>'+projectMap.projectTotailSize+'</td>'+
													  '<td>'+projectMap.isAssignListCalculate+'</td>'+
													  '<td>'+projectMap.isNoAssignListCalculate+'</td>'+
													  '<td>'+projectMap.isAssignSize+'</td>'+
													  '<td>'+projectMap.isNoAssignSize+'</td>'+
													  '<td>'+projectMap.assignProportion+'%</td>');
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