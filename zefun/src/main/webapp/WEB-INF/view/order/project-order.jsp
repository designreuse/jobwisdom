<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/work_achievement.css"	type="text/css" />
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
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
	 jQuery('.date').eq(jQuery(this).index()).show().siblings('.date').hide();
	 jQuery('.container_').eq(jQuery(this).index()).show().siblings('.container_').hide()
   });
   
 })
 
  jQuery(function(){
   jQuery('.wages_content_datail_top>em>span').click(function(){
     jQuery(this).addClass('active').siblings().removeClass('active');
	 jQuery(this).find('.date').eq(jQuery(this).index()).show().siblings('.date').hide();
	 
   });
   
 })
 

  </script>
  
  <script>

   //点击展开收缩
   jQuery(function(){
     jQuery('.store_sell_table_content table').find('tr:gt(0)').css('background','#eeeeee');

     jQuery(document).on('click','.store_sell_table_content img',function(){
       jQuery(this).parents('table').find('tr:gt(0)').stop(true,true).toggle('normal')

     })
   })

   
  function hideHtml(){
	     jQuery('.store_sell_table_content table').find('tr:gt(0)').hide();  
	   
   }
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
	  <li class="active">劳动业绩</li>
	  <li onclick="checkGood()">项目PK</li> 
	  <li onclick="jsonarrjoin()">销量汇总</li> 
	</ul>
	
	<div class="wages_content">
	  <div class="wages_content_datail">
		   <div class="wages_content_datail_top">
			 <span class="active" id="typeYear">年</span><span>月</span>
			  <input  class="date" type="text" name="birthday" value="" onfocus="WdatePicker({dateFmt:'yyyy'})"> 
			  <input class="date" type="text" name="birthdays" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" style="display: none;"> 
			
			 <i class="md">门店</i><select  id="stores" name="md" >
					  <c:forEach items="${selectByStoreAccount }" var="store">
					     <option storeId="${store.storeId }">${store.storeName }</option>
					  </c:forEach>
				 </select>
			  走势类型<select id="yj" onchange="changeType(this)"> <option value="1">业绩</option> <option value="0">数量</option></select>
			 <button onclick="check()">查询</button>
			</div>
	  </div>
	  <div id="container1" class="container_" style="min-width:1000px;height:600px" ></div>
	 <div id="container2" class="container_" style="min-width:1000px;height:600px;display:none"></div>
	</div>
	<div class="wages_content">
	  <div class="wages_content_datail">
		   <div class="wages_content_datail_top">
			 <span class="active" onclick="showProject()">大项PK</span><span onclick="showGoods()">项目PK</span>
			  <select style="margin:0;display:none;" id="projectInfo1" >
			     <c:forEach items="${projectInfo }" var="projectInfo">
					     <option projectId="${projectInfo.projectId }">${projectInfo.projectName }</option>
					  </c:forEach>
			  </select>
			  
			   <select style="margin:0" id="category1" >
			    <c:forEach items="${category }" var="category">
					     <option categoryId="${category.categoryId }">${category.categoryName }</option>
					  </c:forEach>
			  </select>
			  PK
			     <select style="margin:0;display:none;"id="projectInfo2" >
			       <c:forEach items="${projectInfo }" var="projectInfo">
					     <option projectId="${projectInfo.projectId }">${projectInfo.projectName }</option>
					  </c:forEach>
			  </select>
			  
			     <select style="margin:0" id="category2" >
			       <c:forEach items="${category }" var="category">
					     <option categoryId="${category.categoryId }">${category.categoryName }</option>
					  </c:forEach>
			  </select>
			  PK类型
			<select style="margin:0" id="sl" onchange="showData()"> <option value="0">业绩</option> <option value="1">数量</option></select>
			  <em>
			    <span class="active" style="width:50px" onclick="showYear()">年</span>
			    <span style="width:50px" onclick="showYearMonth()">月</span>
			  <input  type="text" onchange="checkGood()" id="yearMonth1" value="" onfocus="WdatePicker({dateFmt:'yyyy'})"> 
			  <input  type="text" onchange="checkGood()" id="yearMonth2" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM'})" > 
			  </em>
			<i class="md">门店</i><select name="md" id="storeTwo" onchange="checkGood()">
					  <c:forEach items="${selectByStoreAccount }" var="store">
					     <option storeId="${store.storeId }">${store.storeName }</option>
					  </c:forEach>
				 </select>
			 <button onclick="checkGood()">查询</button>
			</div>
	  </div>
	  <div id="container3"style="min-width:1000px;height:600px" ></div>
	  
	  
	  
	  </div>
	<div class="wages_content" style="overflow: visible;">
	
	
	  <div class="wages_content_datail">
		   <div class="wages_content_datail_top">
			 <input  type="text" id="date1" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"> 至
							      <input  type="text"  id="date2" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"> 
							 <i class="md">门店</i><select style="width:90px" id="hz" name="md">
									  <c:forEach items="${selectByStoreAccount }" var="store">
									     <option storeId="${store.storeId }">${store.storeName }</option>
									  </c:forEach>
								 </select>
							 <button onclick="checkGoods()">查询</button>
			</div>
	  </div>
	  
	  
	  <div class="store_sell">
	     <div class="work_achievement_content clearfix">
			<ul class="work_achievement_ul">
			<li>
			  <p >现金销售业绩：<i id="priceDc">0<i/></p>
			  <p >现金销售数量：<i id="pricePc">0<i/></p>
			</li>
			<li>
			  <p >卡金销售业绩：<i id="caPriceDc">0<i/></p>
			  <p >卡金销售数量：<i id="caPricePc">0<i/></p>
			</li>
			<li>
			  <p>疗程销售业绩：<i id="lcDc">0<i/></p>
			  <p>疗程销售数量：<i id="lcPc">0<i/></p>
			</li>
			</ul>
			
			<div class="work_achievement_center">
			  <p>总额</p>
			  <ul class="clearfix">
			    <li>总销售数量：<i id="totalDc">0<i/></li>
				 <li>总销售数量金额：<i id="totalPc">0<i/></li>
			</div>
			
			<ul class="work_achievement_ul_right">
			<li>
			  <p>指定客业绩：<i id="zdDc">0<i/></p>
			  <p>指定客数量：<i id="zdPc">0<i/></p>
			</li>
			<li>
			  <p>非指定客业绩：<i id="fzdDc">0<i/></p>
			  <p>非指定客数量：<i id="fzdPc">0<i/></p>
			</li>
			</ul>
         </div>		  
 
 	
	  </div>
		
		 </div>
			</div>
		</div>
	</div>
</body>
<script>
	//dialog('msg');
var joinData = ${joinData};
var project= 1;
var year = ${year} ;
var month = ${month} ;
var number = 1;
var yearMonth = ${yearMonth} ;
var storeIds = ${storeId} ;
var view = ${view} ;
var json ="";
var jsonarr = "";
if(month<10){
	month = "0"+month.toString()
}else{
	month = month.toString()
}
jQuery(function(){
	jQuery("input[name='birthdays']").hide();
	jQuery("input[name='birthdays']").val(year.toString() +"-"+month);
	jQuery("input[name='birthday']").val(year);
	jQuery("#yearMonth1").val(year);
	jQuery("#yearMonth2").val(year.toString() +"-"+month);
	var name = jQuery("#hz option[storeId='"+storeIds+"']").text();
	jQuery("select[name='md']").val(name);
	if(view == 1){
		jQuery("select[name='md']").hide();
		jQuery(".md").hide();
	}
	secends();
	showYear();
	
})


function check(){
	var times = jQuery("input[name='birthday']").val();
	var timeType = jQuery("input[name='birthdays']").val();
	var storeId = jQuery("#stores option:selected").attr("storeId");
	
	if(times=="" || times ==null ){
		dialog('查询的日期年不能为空');
		return ;
	}
	if(timeType=="" || timeType ==null ){
		dialog('查询的日期年月不能为空');
		return ;
	}
	if(!jQuery("#typeYear").hasClass("active")){
		times = timeType.substring(0,4);
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "order/project/select",
		data : "time=" + times + "&storeId=" + storeId + "&timeType=" + timeType,
		dataType : "json",
		success : function(e){
			joinData = e.msg;
			jQuery("input[name='birthday']").val(times);
			changeType();
		}
	});
}


function firest(){
	secendData(joinData.day,joinData.jsonaMonth);
	firstData(joinData.month,joinData.jsonaYear);
}

function secends(){
	secendData(joinData.day,joinData.jsonoMonth);
	firstData(joinData.month,joinData.jsonoYear);
}

function changeType(){
	var type = jQuery("#yj option:selected").val();
	
	if(type == 1){
		secends()
	}
	else{
		firest()
	}
}

function showYear(){
	jQuery("#yearMonth1").show();
	jQuery("#yearMonth2").hide();
	number = 1;
	showData();
}
function showYearMonth(){
	jQuery("#yearMonth1").hide();
	jQuery("#yearMonth2").show();
	number = 2;
	showData();
}


function showProject(){
	jQuery("#projectInfo1").hide();
	jQuery("#projectInfo2").hide();
	jQuery("#category2").show();
	jQuery("#category1").show();
	project= 1;
	showData();
}
function showGoods(){
	jQuery("#projectInfo1").show();
	jQuery("#projectInfo2").show();
	jQuery("#category1").hide();
	jQuery("#category2").hide();
	project= 2;
	showData();
}
//PK查询
function checkGood(){
	var projectId1 =jQuery("#projectInfo1 option:selected").attr("projectId");	
	var projectId2 =jQuery("#projectInfo2 option:selected").attr("projectId");
	
	var categoryId1 =jQuery("#category1 option:selected").attr("categoryId");	
	var categoryId2 =jQuery("#category2 option:selected").attr("categoryId");	
	
	var storeId = jQuery("#storeTwo option:selected").attr("storeId");
	var time = jQuery("#yearMonth1").val();
	var timeType = jQuery("#yearMonth2").val();
	
	if(projectId1=="" || projectId1 ==null ){
		dialog('没有项目');
		return ;
	}
	if(categoryId1=="" || categoryId1 ==null ){
		dialog('没有项目大项');
		return ;
	}
	if(number==2){
		time = timeType.substring(0,4);
	}
	if(time=="" || time ==null ){
		dialog('查询的日期年不能为空');
		return ;
	}
	if(timeType=="" || timeType ==null ){
		dialog('查询的日期年月不能为空');
		return ;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "order/category/select",
		data : "projectId1=" + projectId1 + "&projectId2=" + projectId2  + "&storeId=" + storeId + "&categoryId1=" + categoryId1+ 
		"&categoryId2=" + categoryId2 + "&time=" + time+ "&timeType=" + timeType,
		dataType : "json",
		success : function(e){
			jQuery("#yearMonth1").val(time);
			json  = e.msg;
			showData();
		}
	});
}

function jsonarrjoin(){
	if(jsonarr == ''){
		checkGoods();
	}
}

//项目汇总
function checkGoods(){
	var storeId = jQuery("#hz option:selected").attr("storeId");
	var time1 = jQuery("#date1").val();
	var time2 = jQuery("#date2").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "order/project/category/check",
		data : "time1=" + time1 + "&time2=" + time2  + "&storeId=" + storeId,
		dataType : "json",
		success : function(e){
			jsonarr  = e.msg;
			showJsonArr();
			console.log(jsonarr);
		}
	});
}

function showJsonArr(){
	jQuery("#zdDc").text(jsonarr.zdDc);
	jQuery("#zdPc").text(jsonarr.zdPc);
	jQuery("#fzdDc").text(jsonarr.fzdDc);
	jQuery("#fzdPc").text(jsonarr.fzdPc);
	jQuery("#priceDc").text(jsonarr.priceDc);
	jQuery("#pricePc").text(jsonarr.pricePc);
	jQuery("#caPriceDc").text(jsonarr.caPriceDc);
	jQuery("#caPricePc").text(jsonarr.caPricePc);
	jQuery("#lcDc").text(jsonarr.lcDc);
	jQuery("#lcPc").text(jsonarr.lcPc);
	jQuery("#totalDc").text(jsonarr.totalDc);
	jQuery("#totalPc").text(jsonarr.totalPc);
	showTable();
	hideHtml();
}



function showTable(){
	
	var html = '<div class="store_sell_table"> ';
	for (var g = 0; g < jsonarr.projectJoin.length; g++) {
		var jsonaj = jsonarr.projectJoin[g];
		html += '<p><span>'+jsonaj.name+'</span>业绩汇总:'+jsonaj.deptTotal+'</p>';
		html += '<table> <tbody><tr> <td rowspan="20">大项</td> <td>项目</td>  <td>现金销售数量/业绩</td> <td>卡金销售数量/业绩</td> <td>疗程销售数量/业绩</td><td>指定数</td> <td>非指定</td> <td>指定率</td> <td>总销售数量</td> <td>总业绩</td> 　</tr>　</tbody></table>';
		for (var i = 0; i < jsonaj.table.length; i++) {
			var jsona = jsonaj.table[i];
			
			html += '<div class="store_sell_table_content"><table><tbody><tr><td rowspan="40">'+jsona.name+'</td><td>项目总汇<img src="'+baseUrl+'images/triangle1.png"></td>';
			html += '<td>'+jsona.pricePc+'/<em>'+jsona.priceDc+'</em></td><td>'+jsona.caPricePc+'/<em>'+jsona.caPriceDc+'</em></td><td>'+jsona.lcPc+'/<em>'+jsona.lcDc+'</em></td><td>'+jsona.zdPc+'</td>';
			html += '<td>'+jsona.fzdPc+'</td><td>'+jsona.zd+'</td><td>'+jsona.totalPc+'</td><td>'+jsona.totalDc+'</td></tr>' ;   
		
			for (var j = 0; j < jsona.jsonatable.length; j++) {
				var table = jsona.jsonatable[j];
				html += ' <tr style="background: rgb(238, 238, 238);"> <td>'+table.name+'</td>'
				html += '<td>'+table.pricePcl+'/<em>'+table.priceDcl+'</em></td><td>'+table.caPricePcl+'/<em>'+table.caPriceDcl+'</em></td><td>'+table.lcPcl+'/<em>'+table.lcDcl+'</em></td><td>'+table.zdPcl+'</td>';
				html += '<td>'+table.fzdPcl+'</td><td>'+table.zdl+'</td><td>'+table.totalPcl+'</td><td>'+table.totalDcl+'</td></tr>' ;   
			}
			html += '</tbody></table>	</div>'
		}
	}
	html += '</div>'
	jQuery(".store_sell_table").remove();
	jQuery(".store_sell").append(html);
}


function secendData(day,data){
	jQuery('#container2').highcharts({
	    chart: {
	        type: 'line'
	    },
	    title: {
	        text: '销售走势图'
	    },
	 
	    xAxis: {
	        categories: day
	    },
	    yAxis: {
	        allowDecimals: 'false',        //控制数轴是否显示小数。
	        min: 0                            //控制数轴的最小                   
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
	        name: '日期/天',
	        data: data
	    }]
	});

}

function firstData(month,jsonaYear){
	jQuery('#container1').highcharts({
        chart: {
            type: 'line'
        },
        title: {
            text: '销售走势图'
        },
     
        xAxis: {
            categories: month
        },   
        yAxis: {
	        allowDecimals: 'false',        //控制数轴是否显示小数。
	        min: 0                            //控制数轴的最小                   
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
            name: '日期/月',
            data: jsonaYear
        }]
    });
}

function showData(){
	if(json == ""){
		return;
	}
	var name1= "";
	var name2= "";
	var data1="";
	var data2="";
	var dayMonth ;
	var type = jQuery("#sl option:selected").val();
	// 大项PK or 商品
    if(project == 1){
    	name1= jQuery("#category1 option:selected").val();
    	name2= jQuery("#category2 option:selected").val();
    	data1= json.joinCategory;
    	data2= json.joinOrderCategory;
    }
    else {
    	name1= jQuery("#projectInfo1 option:selected").val();
    	name2= jQuery("#projectInfo2 option:selected").val();
    	data1= json.joinDetail;
    	data2= json.joinOrderDetail;
    }
    
	//按年  or 月 PK
    if(number == 1){
    	dayMonth =data1.month;
    	// 按数量Or 业绩
    	if(type ==1){
   	    	data1 = data1.jsonaYear;
   	    	data2 = data2.jsonaYear;
   	    }
   	    else{
   	    	data1 = data1.jsonoYear;
   	    	data2 = data2.jsonoYear;
   	    }
    }
    else {
    	dayMonth =data1.day;
    	if(type ==1){
   	    	data1 = data1.jsonaMonth;
   	    	data2 = data2.jsonaMonth;
   	    }
   	    else{
   	    	data1 = data1.jsonoMonth;
   	    	data2 = data2.jsonoMonth;
   	    }
    }
    showPK(data1,data2,name1,name2,dayMonth);
	
}

function showPK(data1,data2,name1,name2,dayMonth){
	jQuery('#container3').highcharts({
	      chart: {
	          type: 'line'
	      },
	      title: {
	          text: '销售走势图'
	      },
	   
	      xAxis: {
	          categories: dayMonth
	      },
	      yAxis: {
		        allowDecimals: 'false',        //控制数轴是否显示小数。
		        min: 0                            //控制数轴的最小                   
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
	          name: name1,
	          data: data1
	         }, {
	          name: name2,
	          data: data2
	      }]
	  });
}

</script>
</html>