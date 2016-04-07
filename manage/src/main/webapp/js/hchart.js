
$('#salestable').bootstrapTable({
});


	
    //初始化统计图
      window.onload=function(){
    	  platSaleChat();
    	  platAnalysisChat();
    	  salesVolumeChar()
    	}
      
    /*月度平台销售走势图*/
      function platSaleChat(){
    	  var years =$("#ptxszs_years").val();
    	     var A =new Array();
    		 var B =new Array();
    		 var C =new Array();
    		 var D =new Array();
    		 var E =new Array();
    		 $.ajax({
    	            type: "POST",
    	            url: baseUrl+'hchart/eachMonthSales',
    	            dataType: "json",
    	            data:'years='+years,
    	            success: function (result) {
    	            	var last=JSON.stringify(result.msg); 
    		               
    	  $('#platSaleChat').highcharts({
    		    title: {
    		      text: '月度平台销售走势图',
    		      x: -20 //center
    		    },
    		    xAxis: {
    		      categories: ['一月份', '二月份', '三月份', '四月份', '五月份M', '六月分', '七月份', '八月份', '九月份', '十月份', '十一月份', '十二月分']
    		    },
    		    yAxis: {
    		      title: {
    		        text: '用户数'
    		      },
    		      plotLines: [{
    		        value: 1,
    		        width: 1,
    		        color: '#808080'
    		      }]
    		    },
    		    tooltip: {
    		      valueSuffix: '户'
    		    },
    		    legend: {
    		      layout: 'vertical',
    		      align: 'right',
    		      verticalAlign: 'middle',
    		      borderWidth: 0
    		    },
    		    series: result.msg
    		  });
      }
    		 });
    		 }
      /*6月平台数量分析图*/
    function platAnalysisChat(){

    	var years = document.getElementById('ptyxs_years').value;
    	var monthTime = document.getElementById('ptyxs_month').value;
    	 $.ajax({
    		 type: "POST",
             dataType: "json",
             url: baseUrl+'hchart/monthSales',
             data:'years='+years+'+&monthTime='+monthTime,
             success: function (result) {
            	 var last=JSON.stringify(result.msg); 
                
                // console.log("queryParams..." +  Highcharts.getOptions().colors);
                 var colors = Highcharts.getOptions().colors,
                 categories = result.msg.result,
                 name = ' ',
                 data = result.msg.maplist;

               function setChart(name, categories, data, color) {
                 chart.xAxis[0].setCategories(categories, false);
                 chart.series[0].remove(false);
                 chart.addSeries({
                   name: name,
                   data: data,
                   color: color || 'white'
                 }, false);
                 chart.redraw();
               }

               var chart = $('#platAnalysisChat').highcharts({
                 chart: {
                   type: 'column'
                 },
                 title: {
                   text: '月平台数量分析图'
                 },
                 subtitle: {
                   text: ''
                 },
                 xAxis: {
                   categories: categories
                 },
                 yAxis: {
                   title: {
                     text: '月平台数量分析图'
                   }
                 },
                 plotOptions: {
                   column: {
                     cursor: 'pointer',
                     point: {
                       events: {
                         click: function() {
                           var drilldown = this.drilldown;
                           if (drilldown) { // drill down
                             setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                           } else { // restore
                             setChart(name, categories, data);
                           }
                         }
                       }
                     },
                     dataLabels: {
                       enabled: true,
                       color: colors[0],
                       style: {
                         fontWeight: 'bold'
                       },
                       formatter: function() {
                         return this.y +'户';
                       }
                     }
                   }
                 },
                 tooltip: {
                	 formatter: function() {
                         var point = this.point,
                           s = '';
                           s += years+'年'+monthTime+'月份销售 '+':'+point.category+'<b>'+ this.y +'</b>' +' 户';
                         return s;
                       }
                 },
                 series: [{
                   name: name,
                   data: data,
                   color: 'white'
                 }],
                 exporting: {
                   enabled: false
                 }
               })
             }
         });
       
    }
         /* .highcharts();*/ // return chart
      /*  销量图*/
    function salesVolumeChar(){
    	var a=0;
    	var b=0;
    	var c=0;
    	var d=0;
    	var e=0;
    	var years = document.getElementById('xsje_years').value;
    	var monthTime = document.getElementById('xsje_month').value;
    	$.ajax({
    		 type: "POST",
             dataType: "json",
             url: baseUrl+'hchart/monthSalesMoney',
             data:'years='+years+'+&monthTime='+monthTime,
            success: function (result) {
                
                var last=JSON.stringify(result.msg); 
                
                $('#salesVolumeChar').highcharts({
                    chart: {
                      plotBackgroundColor: null,
                      plotBorderWidth: null,
                      plotShadow: false
                    },
                    title: {
                      text: '平台月销售金额'
                    },
                   
                    plotOptions: {
                      pie: {
                        allowPointSelect: true,
                        cursor: 'pointer',
                        dataLabels: {
                          enabled: true,
                          color: '#000000',
                          connectorColor: '#000000',
                          format: '<b>{point.name}</b>销售金额占: {point.percentage:.0f} %'
                        }
                      }
                    },
                    series: [{
                      type: 'pie',
                      name: years+'年'+monthTime+'月分销售金额',
                      data: result.msg
                    }]
                  });
            }
        });
    	
    }
