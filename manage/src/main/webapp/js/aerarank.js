/**
 * 关于省  区域 排名与统计的js
 */
 /*本月区域开户汇总*/
$('#monthRegNumberTable').bootstrapTable({
});
 window.onload=function(){
	 citySaleRank();
	 provinceSaleRank();
    	}
 
 
 function citySaleRank(){
	 
	 $.ajax({
		 type:"POST",
		 dataType:"json",
		 url:baseUrl+'homepage/citySaleRank',
		 data:'',
		 success: function (result) {
            //var last=JSON.stringify(result.msg); 
            var cityList= result.msg.cityList;
            var countList=result.msg.countList;
            
            $('#cityTTChart').highcharts({
    	        chart: {
    	            type: 'column',
    	            margin: [ 50, 50, 100, 80]
    	        },
    	        title: {
    	            text: '城市前10名销售排行榜'
    	        },
    	        xAxis: {
    	            categories:cityList ,
    	            labels: {
    	                rotation: -45,
    	                align: 'right',
    	                style: {
    	                    fontSize: '13px',
    	                    fontFamily: 'Verdana, sans-serif'
    	                }
    	            }
    	        },
    	        yAxis: {
    	            min: 0,
    	            title: {
    	                text: ''
    	            }
    	        },
    	        legend: {
    	            enabled: false
    	        },
    	        tooltip: {
    	            pointFormat: '此城市累计销售量: <b>{point.y} 户</b>',
    	        },
    	        series: [{
    	            name: 'Population',
    	            data: countList,
    	            dataLabels: {
    	                enabled: true,
    	                rotation: -90,
    	                color: '#FFFFFF',
    	                align: 'right',
    	                x: 4,
    	                y: 10,
    	                style: {
    	                    fontSize: '13px',
    	                    fontFamily: 'Verdana, sans-serif',
    	                    textShadow: '0 0 3px black'
    	                }
    	            }
    	        }]
    	    });
    	
		 }
	 });
 }

 
function provinceSaleRank(){
	 
	 $.ajax({
		 type:"POST",
		 dataType:"json",
		 url:baseUrl+'homepage/provinceSaleRank',
		 data:'',
		 success: function (result) {
            //var last=JSON.stringify(result.msg); 
            var provinceList= result.msg.provinceList;
            var countList=result.msg.countList;
            
            $('#proviceTTChart').highcharts({
    	        chart: {
    	            type: 'column',
    	            margin: [ 50, 50, 100, 80]
    	        },
    	        title: {
    	            text: '省份前10名销售排行榜'
    	        },
    	        xAxis: {
    	            categories:provinceList ,
    	            labels: {
    	                rotation: -45,
    	                align: 'right',
    	                style: {
    	                    fontSize: '13px',
    	                    fontFamily: 'Verdana, sans-serif'
    	                }
    	            }
    	        },
    	        yAxis: {
    	            min: 0,
    	            title: {
    	                text: ''
    	            }
    	        },
    	        legend: {
    	            enabled: false
    	        },
    	        tooltip: {
    	            pointFormat: '此省份累计销售量: <b>{point.y} 户</b>',
    	        },
    	        series: [{
    	            name: 'Population',
    	            data: countList,
    	            dataLabels: {
    	                enabled: true,
    	                rotation: -90,
    	                color: '#FFFFFF',
    	                align: 'right',
    	                x: 4,
    	                y: 10,
    	                style: {
    	                    fontSize: '13px',
    	                    fontFamily: 'Verdana, sans-serif',
    	                    textShadow: '0 0 3px black'
    	                }
    	            }
    	        }]
    	    });
    	
		 }
	 });
 }

var searchData = {};

//扩展bootstrap table的请求参数
function queryParams(params){
	
	for(var k in searchData){
		params[k] = searchData[k];
	}
	//console.log("queryParams..." + JSON.stringify(params));
	return params;
}
//调用bootstrap table的函数
function query(){
	
	$('#monthRegNumberTable').bootstrapTable('refresh', null);
}
	