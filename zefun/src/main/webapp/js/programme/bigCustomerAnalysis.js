function showZzc () {
	var selectStore = jQuery("select[name='selectStore']").val();
	jQuery("select[name='chooseStore']").val(selectStore);
	chooseActiveStore();
	jQuery(".zzc").show();
}

jQuery("input[name='end1'], input[name='end2'], input[name='end3'], input[name='end4']").keyup(function (){
	var name = jQuery(this).attr("name");
	var value = jQuery(this).val();
	var type = name.charAt(name.length - 1);
	type = parseInt(type) + 1;
	jQuery("input[name='start"+type+"']").val(value);
})

function chooseActiveStore () {
	var chooseStore = jQuery("select[name='chooseStore']").val();
	
	for (var i = 0; i < storeRuleList.length; i++) {
		var rule = storeRuleList[i];
		if (rule.storeIdOrAccount == chooseStore) {
			var settingRule = rule.settingRule;
			var ruleInfo = settingRule.ruleInfo;
			var ruleList = ruleInfo.split(":");
			jQuery("input[name='start1']").val(ruleList[0]);
			jQuery("input[name='end1']").val(ruleList[1]);
			jQuery("input[name='start2']").val(ruleList[1]);
			jQuery("input[name='end2']").val(ruleList[2]);
			jQuery("input[name='start3']").val(ruleList[2]);
			jQuery("input[name='end3']").val(ruleList[3]);
			jQuery("input[name='start4']").val(ruleList[3]);
			jQuery("input[name='end4']").val(ruleList[4]);
			jQuery("input[name='start5']").val(ruleList[4]);
			
			jQuery(".set_pay_content_button").attr("settingRuleId", settingRule.settingRuleId);
		}
	}
}

function cancal () {
	jQuery(".zzc").hide();
}

function saveRule () {
	var chooseStore = jQuery("select[name='chooseStore']").val();
	var settingRuleId = jQuery(".set_pay_content_button").attr("settingRuleId");
	var start1 = jQuery("[name='start1']").val();
	var end1 = jQuery("[name='end1']").val();
	var end2 = jQuery("[name='end2']").val();
	var end3 = jQuery("[name='end3']").val();
	var end4 = jQuery("[name='end4']").val();
		
	if (isEmpty(start1) || isEmpty(end1) || isEmpty(end2) || isEmpty(end3) || isEmpty(end4)) {
		dialog("规则消费金额不能存在空值");
        return;
	}
	
	if (parseInt(start1) >= parseInt(end1) || parseInt(end1) >= parseInt(end2) || parseInt(end2) >= parseInt(end3) || parseInt(end3) >= parseInt(end4)) {
		dialog("区域开始金额不能大于等于区域结束金额");
        return;
	}
	
	var ruleInfo = start1 + ":" + end1 + ":" + end2 + ":" + end3 + ":" + end4;
	
	jQuery.ajax({
    	url : baseUrl + "programme/action/initializationSettingRule",
    	type : "POST",
    	data : "settingRuleId=" + settingRuleId + "&ruleType=1&ruleInfo=" + ruleInfo,
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		dialog("规则消费金额成功");
    		
    		for (var i = 0; i < storeRuleList.length; i++) {
    			var rule = storeRuleList[i];
    			if (rule.storeIdOrAccount == chooseStore) {
    				var settingRule = rule.settingRule;
    				settingRule.ruleInfo = ruleInfo;
    			}
    		}
    		
    		jQuery(".zzc").hide();
    	}
    });
}

jQuery(function () {
    if (analysisType == 1) {
    	dialog("未创建门店, 请先创建门店");
    	return;
    }
	
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
    }).highcharts(); // return chart

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
    }).highcharts(); // return chart
    
    jQuery('.customer_analyse_content_:gt(0)').hide();
    jQuery('.content_right ul li').click(function(){
	  jQuery(this).addClass('active').siblings().removeClass('active');
	  jQuery('.customer_analyse_content_').eq(jQuery(this).index()).show().siblings().hide();
	})
});				
  