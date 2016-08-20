<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/course_score.css"	type="text/css" />
<script>
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
 

 

  </script>
  
  <script>
  //走势图表 
 

  </script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper"
		style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel"
				style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>


<div class='content_right clearfix'>
    <ul class="clearfix">
	  <li class="active">走势图</li>
	  <li>营业汇总</li> 
	</ul>
	
	<div class="wages_content">
	  <div class="wages_content_datail">
		   <div class="wages_content_datail_top">
			 <span class="active" onclick="showYear()" >年</span><span onclick="showDataMoth()">月</span>
			  <input  class="date" type="text" name="birthday" value="" onfocus="WdatePicker({dateFmt:'yyyy'})"> 
			  <input class="date" type="text" name="birthdays" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" style="display: none;"> 
			  门店<select> <option></option></select>
			 <button>查询</button>
			</div>
	  </div>
	
	 <div id="container1"  style="min-width:1000px;height:600px;"></div>
	</div>
	<div class="wages_content">
	  <div class="wages_content_datail">
		   <div class="wages_content_datail_top">
			  <input type="text">至<input type="text">
			  门店<select> <option></option></select>
			 <button>查询</button>
			</div>
	  </div>	
	  <div class="course_score">
	     <div class="course_score_image clearfix">
	        <div class="course_score_image_left">
			   <div class="number_now">
			     <p>当前现金疗程数量:0</p>
				 <p>当前现金疗程业绩:0.00</p>
			   </div>
			   <div class="number_now">
			     <p>当前现金疗程数量:0</p>
				 <p>当前现金疗程业绩:0.00</p>
			   </div>
			</div>
			<div class="course_score_image_center">
			    <p>合计</p>
			    <div class="sell clearfix">
				  <div class="sell_left">
				     <p>销售数量</p>
				     <span>0</span>
				  </div>
				  <div class="sell_right">
				    <p>销售金额</p>
				    <span>0.00</span>
				  </div>
				</div>
			</div>
			<div class="course_score_image_right">
			  <div class="number_now">
			     <p>指定客数量:0</p>
				 <p>指定客金额:0.00</p>
			   </div>
			   <div class="number_now">
			     <p>非指定客数量:0</p>
				 <p>非指定客金额:0.00</p>
			   </div>
			
			
			</div>
	     </div>
	  </div>

	
	<div class="card_content">
	   <div class="card_content_detail">
	     <table>
		   <tr>
		     <td colspan="10">次数限制卡分析</td>
		   </tr>
		   <tr>
		     <td>部门</td>
			 <td>疗程名称</td>
			 <td>现金业绩</td>
			 <td>卡金业绩</td>
			 <td>累计售出课次</td>
			 <td>服务总次数</td>
			 <td>已服务次数</td>
			 <td>剩余次数</td>
			 <td>剩余服务次数</td>
			 <td>消耗率</td>
		   </tr>
		 </table>
		 
		 <div class="card_content_detail_table">
		   <table>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>			
			  </tr>
		     <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>			
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>			
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>			
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>			
			  </tr>
		     <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>			
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>			
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>			
			  </tr>
		   </table>
		 </div>
	   </div>
	  
	  
	   <div class="card_content_detail">
	     <table>
		   <tr>
		     <td colspan="13">时间限制/次数限制/疗程分析</td>
		   </tr>
		   <tr>
		     <td>部门</td>
			 <td>疗程名称</td>
			 <td>现金业绩</td>
			 <td>卡金业绩</td>
			 <td>累计销售业绩</td>
			 <td>累计课次</td>
			 <td>有效期内个数</td>
			 <td>剩余有效服务业绩</td>
			 <td>剩余有效服务次数</td>
			 <td>历史已服务次数</td>
			 <td>历史总服务次数</td>
			 <td>历史实际使用率</td>
			 <td>预期服务次数</td>
		   </tr>
		 </table>
		 
		 <div class="card_content_detail_table">
		   <table>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
		     <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
			  <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
                <td>0</td>				
			  </tr>
		   </table>
		 </div>
	   </div>
	   
	    <div class="card_content_detail">
	     <table>
		   <tr>
		     <td colspan="13">时间限制/疗程分析</td>
		   </tr>
		   <tr>
		     <td>部门</td>
			 <td>疗程名称</td>
			 <td>现金业绩</td>
			 <td>卡金业绩</td>
			 <td>累计销售业绩</td>
			 <td>累计购买客次</td>
			 <td>当前有效人数</td>
			 <td>已服务人数</td>
			 <td>历史人数</td>
			 <td>历史服务次数</td>
			 <td>人均使用次数</td>
			 <td>预期服务次数</td>
		   </tr>
		 </table>
		 
		 <div class="card_content_detail_table">
		   <table>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
				<td>0</td> 
			  </tr>
		       <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
				<td>0</td> 
			  </tr>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
				<td>0</td> 
			  </tr>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
				<td>0</td> 
			  </tr>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
				<td>0</td> 
			  </tr>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
				<td>0</td> 
			  </tr>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
				<td>0</td> 
			  </tr>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
				<td>0</td> 
			  </tr>
		      <tr>
			    <td>不闷不闷</td>
				<td>不闷不闷</td>
				<td>0.00</td>
				<td>0.00</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0</td>
				<td>0%</td>	
				<td>0</td> 
			  </tr>
		     
		   </table>
		 </div>
	   </div>
	
	
	</div>
	
			</div>
		</div>
	</div>
</body>
<script>
var view = ${view};
var storeId = ${storeId};
var year = ${year};
var month = ${month};
if(month<10){
	month = "0"+month.toString()
}else{
	month = month.toString()
}


var comboInfoData = ${comboInfoData};
jQuery(function () {
// 	
	jQuery("input[name='birthday']").val(year);
	jQuery("input[name='birthdays']").val(year.toString() +"-"+month);
	showYear();
});		

function showYear(){
	jQuery("input[name='birthday']").show();
	jQuery("input[name='birthdays']").hide();
	showData(comboInfoData.jsonoYear, comboInfoData.month);
}
function showDataMoth(){
	jQuery("input[name='birthday']").hide();
	jQuery("input[name='birthdays']").show();
	showData(comboInfoData.jsonoMonth, comboInfoData.day);
}

function showData(data,time){

jQuery('#container1').highcharts({
    chart: {
	    
        type: 'line'
    },
    title: {
        text: ''
    },
    yAxis: {
            title: {
                text: '金额（元）'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
	  
    xAxis: {
        categories: time
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
        name: '疗程业绩走势图',
        data: data
    }]
});
}
	//dialog('msg');
</script>
</html>