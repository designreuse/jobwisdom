/**
 * 
 */

 window.onload=function(){
	 eachDaySales();
	 Statistics();
    	}
 
 
 function Statistics(){
	 $.ajax({
		 type: "POST",
	     dataType: "json",
	     url: baseUrl+'homepage/statistics',
	     data:'',
	     success: function (result) {
	    	 
             var zong_count=result.msg[0].zong_count; 
             var dan_count=result.msg[0].dan_count; 
             var lian_count=result.msg[0].lian_count; 
             var fen_count=result.msg[0].fen_count; 
             var sumpt=result.msg[0].sumpt;
             if(sumpt==""||sumpt==null){
            	 sumpt=0;
             }
             var sumwp=result.msg[0].sumwp;
             if(sumwp==""||sumwp==null){
            	 sumwp=0;
             }
             $(".a").text(zong_count);
             $(".b").text(lian_count);
             $(".c").text(fen_count);
             $(".d").text(dan_count);
             $(".e").text(sumpt);
             $(".f").text(sumwp);
             
	     }
	 });
 }
 

function eachDaySales(){
	var province =$("#province").val();
	var city =$("#city").val();
	var area =$("#area").val();
	 $.ajax({
		 type: "POST",
	     dataType: "json",
	     url: baseUrl+'homepage/eachDaySales',
	     data:'province='+province+'&city='+city+'&area='+area,
	     success: function (result) {
	    	 var dan=null;
	    	 var lian=null;
	    	 var fen=null;
	    	 
	    	 for (var i = 0; i < result.msg.length; i++) {
	    		 //判断当为单店时候
	    		 if(result.msg[0].customerType==1){
	    			 dan=result.msg[0].list;
	    		 }
	    		//判断当为单店时候
	    		 if(result.msg[1].customerType==2){
	    			 lian=result.msg[1].list;
	    		 }
	    		//判断当为单店时候
	    		 if(result.msg[2].customerType==3){
	    			 fen=result.msg[2].list;
	    		 }
	    		
			}
	    	
	    	 //var last=JSON.stringify(result.msg); 
             //alert(last); 
             
	    	 /*本月新增客户数*/
	    	  $('#addUserChat').highcharts({
	    	    chart: {
	    	      height: 300,
	    	      type: 'line'
	    	    },
	    	    title: {
	    	      text: '本月新增客户数',
	    	      x: -20 //center
	    	    },
	    	    xAxis: {
	    	      categories: ['1号', '2号', '3号', '4号', '5号', '6号','7号','8号','9号','10号','11号','12号','13号','14号','15号','16号','17号','18号','19号','20号','21号','22号','23号','24号','25号','26号','27号','28号','29号','30号','31号'],
	    	     /* title: {
	    	        text: "日期",
	    	        margin: 3
	    	      }*/
	    	    },
	    	    yAxis: {
	    	      lineWidth: 1,
	    	      tickWidth: 1,
	    	      title: {
	    	        align: 'high',
	    	        offset: 0,
	    	        text: '数量',
	    	        rotation: 0,
	    	        y: -10
	    	      }
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
	    	    series: [{
	    	      name: '单店',
	    	      data: dan
	    	    }, {
	    	      name: '连锁店',
	    	      data: lian
	    	    }, {
		    	      name: '分店',
		    	      data: fen
		    	    }]
	    	  });
	     }
	});
}




