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
    	data : "settingRuleId=" + settingRuleId + "&ruleType="+pageType+"&ruleInfo=" + ruleInfo,
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

var memberRuleArray = new Array();

function packgTable (rule, ruleType) {
	var settingRule = rule.settingRule;
	jQuery("table[name='boyTable'] tr").find("td:gt(0)").remove();
	jQuery("table[name='grilTable'] tr").find("td:gt(0)").remove();
	
	var storeIdOrAccount = settingRule.storeIdOrAccount;
	
	jQuery.ajax({
    	url : baseUrl + "programme/action/bigMemberData",
    	type : "POST",
    	data : "storeIdOrAccount="+storeIdOrAccount+"&ruleType="+ruleType+"&pageType="+pageType,
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		var dataMap = e.msg;
    		var girlObjList = dataMap.girlObjList;
    		var boyObjList = dataMap.boyObjList;
    		
    		var regionArray = new Array();
    		
    		var grilTotalVlaueArray = new Array();
    		
    		var boyTotalVlaueArray = new Array();
    		
    		var colors = Highcharts.getOptions().colors;
    		
    		for (var i = 0; i < girlObjList.length; i++) {
    			var girlObj = girlObjList[i];
    			regionArray.push(girlObj.rule);
    			
    			var obj = {y: girlObj.totalVlaue, color: colors[i]}
    			
    			grilTotalVlaueArray.push(obj);
    			
    			var memberRuleObj = {"ruleType" : "1_"+i, "memberIdList" : girlObj.memberIdList}
    			
    			memberRuleArray.push(memberRuleObj);
    			
    			jQuery("table[name='grilTable']").find("tr").eq(0).append('<td>'+girlObj.rule+'</td>')
    			jQuery("table[name='grilTable']").find("tr").eq(1).append('<td onclick = "selectMember(\'1_'+i+'\')">'+girlObj.memberNumber+'人</td>');
    			jQuery("table[name='grilTable']").find("tr").eq(2).append('<td>'+girlObj.numProportion+'%</td>');
    			jQuery("table[name='grilTable']").find("tr").eq(3).append('<td>'+girlObj.totalVlaue+'</td>');
    			jQuery("table[name='grilTable']").find("tr").eq(4).append('<td>'+girlObj.totalProportion+'%</td>');
    			jQuery("table[name='grilTable']").find("tr").eq(5).append('<td>'+girlObj.avgProportion+'</td>');
    		}
    		
    		for (var i =0; i < boyObjList.length; i++) {
    			var girlObj = boyObjList[i];
    			
                var obj = {y: girlObj.totalVlaue, color: colors[i]}
    			
                boyTotalVlaueArray.push(obj);
                
                var memberRuleObj = {"ruleType" : "2_"+i, "memberIdList" : girlObj.memberIdList}
    			
    			memberRuleArray.push(memberRuleObj);

    			jQuery("table[name='boyTable']").find("tr").eq(0).append('<td>'+girlObj.rule+'</td>')
    			jQuery("table[name='boyTable']").find("tr").eq(1).append('<td onclick = "selectMember(\'2_'+i+'\')">'+girlObj.memberNumber+'人</td>');
    			jQuery("table[name='boyTable']").find("tr").eq(2).append('<td>'+girlObj.numProportion+'%</td>');
    			jQuery("table[name='boyTable']").find("tr").eq(3).append('<td>'+girlObj.totalVlaue+'</td>');
    			jQuery("table[name='boyTable']").find("tr").eq(4).append('<td>'+girlObj.totalProportion+'%</td>');
    			jQuery("table[name='boyTable']").find("tr").eq(5).append('<td>'+girlObj.avgProportion+'</td>');
    		}
    		
    		var valueTitle = null;
    		if (pageType == 1) {
    			valueTitle = "大客户消费分析";
    		}
    		else if (pageType == 2) {
    			valueTitle = "忠诚度分析";
    		}
    		else {
    			valueTitle = "距今几日未到店";
    		}
    		
    		packgPage ("container1", "(女客)" + valueTitle, valueTitle, regionArray, grilTotalVlaueArray);
    		packgPage ("container2", "(男客)" + valueTitle, valueTitle, regionArray, boyTotalVlaueArray)
    	}
    });
}

var parentMemberIdList = null;

function selectMember (ruleType) {
	for (var i = 0; i < memberRuleArray.length; i++) {
		var memberRuleObj = memberRuleArray[i];
		if (ruleType == memberRuleObj.ruleType) {
			var memberIdList = memberRuleObj.memberIdList;
			if (memberIdList.length == 0) {
				dialog("暂无客户");
				return;
			}
			parentMemberIdList = memberIdList;
			pageNo = 1;	
			pageSize = 15;	
			changePage();
		}
	}
}

/*分页*/
//上一页
function previous(){
	if(pageNo <= 1){
		return;
	}
	pageNo--;
	changePage();
}
//下一页
function next(){
	if(pageNo >= totalPage){
		return;
	}
	pageNo++;
	changePage();
}
//更改每页显示数量
function changePageSize(size){
	pageNo = 1;
	pageSize = size;
	jQuery(".perpage").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePage();
}
//分页处理
function changePage(){

	  jQuery.ajax({
	    	url : baseUrl + "programme/action/ruleMemberIdList",
	    	type : "POST",
	    	data : "pageNo="+pageNo+"&pageSize="+pageSize+"&memberIdListStr=" + JSON.stringify(parentMemberIdList),
	    	success : function(e){
	    		if (e.code != 0) {
	                dialog(e.msg);
	                return;
	            }
	    		jQuery(".perpage").text(e.msg.pageSize);
				jQuery("#init_member").find("tr :gt(0)").remove();
				initTable(e);
				pageNo = e.msg.pageNo;
				pageSize = e.msg.pageSize;
				totalPage = e.msg.totalPage;
				totalRecord = e.msg.totalRecord;
				unbuildPagination();
				jQuery(".content_right ul").find("li").eq(1).click();
	    	}
	    });
}

function initTable (e) {
	var memberInfoDtos = e.msg.results;
	for (var i =0; i < memberInfoDtos.length; i++) {
		var dto = memberInfoDtos[i];
		jQuery("#init_member").append('<tr>'+
									     '<td><input type="checkbox"></td>'+
									     '<td>'+dto.name+'</td>'+
										 '<td>'+dto.sex+'</td>'+
										 '<td>'+dto.phone+'</td>'+
										 '<td>'+dto.totalAmount+'</td>'+
										 '<td>'+dto.consumeCount+'</td>'+
										 '<td>'+dto.lastConsumeTime+'</td>'+
										 '<td>'+dto.birthday+'</td>'+
										 '<td>'+dto.createTime+'</td>'+
										 '<td>'+dto.storeName+'</td>'+
									   '</tr>');
	}
}

function packgPage (id, nameTitle, endName, regionArray, totalVlaueArray) {
	
	var colors = Highcharts.getOptions().colors,
	    categories = regionArray,
	    name = endName,
	    data = totalVlaueArray;
	
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
	var chart = jQuery('#'+id).highcharts({
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: nameTitle
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
	
}

function chooseStoreSelect(obj) {
	var storeIdOrAccount = jQuery(obj).val();
	var ruleType = jQuery(obj).find('[value="'+storeIdOrAccount+'"]').attr("storeType");
	
	for (var i = 0; i < storeRuleList.length; i++) {
		var rule = storeRuleList[i];
		if (rule.storeIdOrAccount == storeIdOrAccount) {
			packgTable(rule, ruleType);
		}
	}
}

jQuery(function () {
    if (analysisType == 1) {
    	dialog("未创建门店, 请先创建门店");
    	return;
    }
	
    var rule = storeRuleList[1];
    packgTable(rule, 1);
    
    
    jQuery('.customer_analyse_content_:gt(0)').hide();
    jQuery('.content_right ul li').click(function(){
	  jQuery(this).addClass('active').siblings().removeClass('active');
	  jQuery('.customer_analyse_content_').eq(jQuery(this).index()).show().siblings().hide();
	})
});				
  