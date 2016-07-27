function showZzc () {
	jQuery(".zzc").show();
}

function cancal () {
	jQuery(".zzc").hide();
}

function saveRule () {
	var chooseStore = jQuery("[name='chooseStore']").val();
	var start1 = jQuery("[name='start1']").val();
	var end1 = jQuery("[name='end1']").val();
	var end2 = jQuery("[name='end2']").val();
	var end3 = jQuery("[name='end3']").val();
	var end4 = jQuery("[name='end4']").val();
	
	var start5 = jQuery("[name='start5']").val();
	
	if (isEmpty(start1) || isEmpty(end1) || isEmpty(end2) || isEmpty(end3) || isEmpty(end4) || isEmpty(start5)) {
		dialog("规则消费金额不能存在空值");
        return;
	}
	
	
}

jQuery(function () {
    
    var colors = Highcharts.getOptions().colors,
        categories = ['0-300元', '300-500元', '500-1000元', '1000-3000元', '3000元以上'],
        name = '消费分析',
        data = [{
                y: 6000,
                color: colors[0]
            }, {
                y: 5000,
                color: colors[1]
            }, {
                y:300,
                color: colors[2]
            }, {
                y: 5000,
                color: colors[3]
            }, {
                y: 500,
                color: colors[4],
             
            }];

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
//第一个图表
    var chart = jQuery('#container1').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: '(女客)大客户消费分析'
        },
      
        xAxis: {
            categories: categories
        },
      
        plotOptions: {
            column: {
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: colors[0],
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y +'';
                    }
                }
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

    .highcharts(); // return chart
});				
  
//第二个图表  
  jQuery(function () {
    var colors = Highcharts.getOptions().colors,
        categories = ['0-300元', '300-500元', '500-1000元', '1000-3000元', '3000元以上'],
        name = '消费分析',
        data = [{
                y: 5000,
                color: colors[0]
            }, {
                y: 6000,
                color: colors[1]
            }, {
                y: 2000,
                color: colors[2]
            }, {
                y: 1000,
                color: colors[3]
            }, {
                y: 500,
                color: colors[4],
             
            }];

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
//第一个图表
    var chart = jQuery('#container2').highcharts({
        chart: {
            type: 'column',
			
        },
        title: {
            text: '(男客)大客户消费分析'
        },
      
        xAxis: {
            categories: categories
        },
      
        plotOptions: {
            column: {
                cursor: 'pointer',
                dataLabels: {
                    enabled: true,
                    color: colors[0],
                    style: {
                        fontWeight: 'bold'
                    },
                    formatter: function() {
                        return this.y +'';
                    }
                }
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

    .highcharts(); // return chart
});				
  
  jQuery(function(){
    jQuery('.customer_analyse_content_:gt(0)').hide();
    jQuery('.content_right ul li').click(function(){
	  jQuery(this).addClass('active').siblings().removeClass('active');
	  jQuery('.customer_analyse_content_').eq(jQuery(this).index()).show().siblings().hide();
	})
  })