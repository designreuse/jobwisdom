<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/store_sell.css"	type="text/css" />
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<script>
  jQuery(function(){
    jQuery('.wages_content:gt(0)').hide();
    jQuery('.content_right>ul>li').click(function(){
      jQuery(this).addClass('active').siblings().removeClass('active');
	  jQuery('.wages_content').eq(jQuery(this).index()).show().siblings('.wages_content').hide();

	})
  })
  
//选中
 jQuery(function(){
   jQuery('.first .wages_content_datail_top>span').click(function(){
     jQuery(this).addClass('active').siblings().removeClass('active');
	 jQuery('.date').eq(jQuery(this).index()).show().siblings('.date').hide();
	 jQuery('.first .container_').eq(jQuery(this).index()).show().siblings('.container_').hide()
   });
   
 })
 
  
 jQuery(function(){
	   jQuery('.second .wages_content_datail_top>span').click(function(){
	     jQuery(this).addClass('active').siblings().removeClass('active');
		 
		 
	   });
	   
	 })
	 
	 
  jQuery(function(){
   jQuery('.wages_content_datail_top>em>span').click(function(){
     jQuery(this).addClass('active').siblings().removeClass('active');
	 jQuery(this).find('.date').eq(jQuery(this).index()).show().siblings('.date').hide();
	 
   });
   
 })
 
 
 //点击展开收缩
 jQuery(function(){
   jQuery('.store_sell_table_content table').find('tr:gt(0)').css('background','#eeeeee');
   jQuery('.store_sell_table_content table').find('tr:gt(0)').hide();
   jQuery('.store_sell_table_content img').click(function(){
     jQuery(this).parents('table').find('tr:gt(0)').stop(true,true).toggle('normal')

   })
 })
  </script>

<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper"
		style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel"
				style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right clearfix">
				    <ul class="clearfix">
					  <li class="active">商品出售</li>
					  <li onclick="checkGood()">销售PK</li> 
					  <li class="" onclick="checkGoods()">销量汇总</li> 
					</ul>
					
					<div class="wages_content first" >
					  <div class="wages_content_datail">
						   <div class="wages_content_datail_top">
							 <span class="active" id="typeYear">年</span><span>月</span>
							  
							  <input  class="date" type="text" name="birthday" value="" onfocus="WdatePicker({dateFmt:'yyyy'})"> 
							  <input class="date" type="text" name="birthdays" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM'})"> 
							
							  门店<select  id="stores" >
									  <c:forEach items="${selectByStoreAccount }" var="store">
									     <option storeId="${store.storeId }">${store.storeName }</option>
									  </c:forEach>
								 </select>
							  走势类型<select id="yj" onchange="changeType(this)"> <option value="1">业绩</option> <option value="0">数量</option></select>
							 <button onclick="check()">查询</button>
							</div>
					  </div>
					  <div id="container1" class="container_" style="min-width:1000px;height:600px"></div>
	                 <div id="container2" class="container_" style="min-width:1000px;height:600px;display:none"></div>
					</div>
				
					<div class="wages_content second" >
					  <div class="wages_content_datail">
						   <div class="wages_content_datail_top">
							 <span class="active" onclick="showProject()">大项PK</span><span onclick="showGoods()">商品PK</span>
							  <select style="margin:0;display:none;" id="goodsInfo1" >
							     <c:forEach items="${goodsInfoDto }" var="goodsInfo">
									     <option goodsId="${goodsInfo.goodsId }">${goodsInfo.goodsName }</option>
									  </c:forEach>
							  </select>
							  
							   <select style="margin:0" id="goodsCategory1" >
							    <c:forEach items="${goodsCategory }" var="goodsCategory">
									     <option categoryId="${goodsCategory.categoryId }">${goodsCategory.categoryName }</option>
									  </c:forEach>
							  </select>
							  PK
							   <select style="margin:0;display:none;" id="goodsInfo2"  >
							     <c:forEach items="${goodsInfoDto }" var="goodsInfo">
									     <option goodsId="${goodsInfo.goodsId }">${goodsInfo.goodsName }</option>
									  </c:forEach>
							  </select>
							  
							     <select style="margin:0" id="goodsCategory2" >
							       <c:forEach items="${goodsCategory }" var="goodsCategory">
									     <option categoryId="${goodsCategory.categoryId }">${goodsCategory.categoryName }</option>
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
							  门店<select  id="storeTwo" onchange="checkGood()">
									  <c:forEach items="${selectByStoreAccount }" var="store">
									     <option storeId="${store.storeId }">${store.storeName }</option>
									  </c:forEach>
								 </select>
							 <button onclick="checkGood()">查询</button>
							</div>
					  </div>
					  <div id="container3" style="min-width:1000px;height:600px"></div>
					  
					  
				</div>
					<div class="wages_content" >
					  <div class="wages_content_datail">
						   <div class="wages_content_datail_top">
							    <input  type="text" id="date1" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"> 至
							      <input  type="text"  id="date2" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"> 
							  门店<select style="width:90px" id="hz">
									  <c:forEach items="${selectByStoreAccount }" var="store">
									     <option storeId="${store.storeId }">${store.storeName }</option>
									  </c:forEach>
								 </select>
							 <button>查询</button>
							</div>
					  </div>
					  
					  <div class="store_sell">
					     <ul class="clearfix">
						   <li>
						     <p >现金销售数量：<span id="priceDc">0</span></p>
						     <p>现金销售业绩：<span id="pricePc">0</span></p>
						   </li>
						   <li>
						     <p>卡金销售数量：<span id="caPriceDc">0</span></p>
						     <p>卡金销售业绩：<span id="caPricePc">0</span></p>
						   </li>
						   <li>
						     <p>套餐销售数量：<span id="tcDc">0</span></p>
						     <p>套餐销售业绩：<span id="tcPc">0</span></p>
						   </li>
						   <li>
						     <p>销售总数量：<span id="totalDc">0</span></p>
						     <p>销售总业绩：<span id="totalPc">0</span></p>
						   </li>
						 </ul>
					  </div>
					
					  <div class="store_sell_table">
				  </div>
			</div>
			</div>
		</div>
	</div>
</body>
<script>
var project= 1;
var yearMonth = 1;
var json ="";
var joinData = ${joinData};
var year = ${year} ;
var month = ${month} ;
if(month<10){
	month = "0"+month.toString()
}else{
	month = month.toString()
}

var jsonarr = "";
function checkGoods(){
	var storeId = jQuery("#hz option:selected").attr("storeId");
	var time1 = jQuery("#date1").val();
	var time2 = jQuery("#date2").val();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "order/goods/check",
		data : "time1=" + time1 + "&time2=" + time2  + "&storeId=" + storeId,
		dataType : "json",
		success : function(e){
// 			jQuery("#yearMonth1").val(time);
			jsonarr  = e.msg;
			console.log(jsonarr.goodsJoin);
			showJsonArr();
// 			showData();
		}
	});
}


function showJsonArr(){
	jQuery("#caPriceDc").text(jsonarr.caPriceDc);
	jQuery("#caPricePc").text(jsonarr.caPricePc);
	jQuery("#priceDc").text(jsonarr.priceDc);
	jQuery("#pricePc").text(jsonarr.pricePc);
	jQuery("#tcDc").text(jsonarr.tcDc);
	jQuery("#tcPc").text(jsonarr.tcPc);
	jQuery("#totalDc").text(jsonarr.totalDc);
	jQuery("#totalPc").text(jsonarr.totalPc);
}

function showTable(){
	
	var html = '';
	
    
		  
	
	
	caPriceDc	:	3261.4	caPricePc	:	31	jsona	:	Array[12]	name	:	"美容部"	priceDc	:	2366	pricePc	:	20	shopDc	:	0	shopPc	:	0	
	storeDc	:	5627.4	storePc	:	51	tcDc	:	0	tcPc	:	0	totalDc	:	11254.8	totalPc	:	102
	for (var i = 0; i < jsonarr.goodsJoin.length; i++) {
		var jsona = jsonarr.goodsJoin[i];
		html += '<p><span>'+jsona.name+'</span>业绩汇总:'+jsona.totalDc+'</p> <table> <tbody><tr> <td rowspan="20">大项</td> <td>商品名称</td> <td>商城销售数量/业绩</td>　<td>门店销售数量/业绩</td>　<td>现金销售数量/业绩</td>　<td>卡金销售数量/业绩</td><td>套餐销售数量/业绩</td>　<td>总数量</td>　<td>总销售额</td>　</tr>　</tbody></table>';
		html += '<div class="store_sell_table_content"><table><tbody><tr>';
		 
		    
			 
			 
		      <td rowspan="40">大项大项</td>
			  <td>项目总汇<img src="<%=basePath%>images/triangle1.png">	
			  </td>
			  <td>500/<em>500.00</em></td>
			  <td>500/<em>500.00</em></td>
			  <td>500/<em>500.00</em></td>
			  <td>500/<em>500.00</em></td>
			  <td>500/<em>500.00</em></td>
			  <td>500</td>
			  <td><em>500.00</em></td> 
			 </tr>
          <tr style="display: none; background: rgb(238, 238, 238);">
			  <td>啊啊啊啊啊</td>
			  <td>500/<em>500.00</em></td>
			  <td>500/<em>500.00</em></td>
			  <td>500/<em>500.00</em></td>
			  <td>500/<em>500.00</em></td>
			  <td>500/<em>500.00</em></td>
			  <td>500</td>   
			  <td>500/<em>500.00</em></td>
			</tr>
				
			</tbody></table>

	</div>
	}
	
	
	
}

function showYear(){
	jQuery("#yearMonth1").show();
	jQuery("#yearMonth2").hide();
	yearMonth = 1;
	showData();
}
function showYearMonth(){
	jQuery("#yearMonth1").hide();
	jQuery("#yearMonth2").show();
	yearMonth = 2;
	showData();
}

function showProject(){
	jQuery("#goodsInfo1").hide();
	jQuery("#goodsInfo2").hide();
	jQuery("#goodsCategory2").show();
	jQuery("#goodsCategory1").show();
	project= 1;
	showData();
}
function showGoods(){
	jQuery("#goodsInfo1").show();
	jQuery("#goodsInfo2").show();
	jQuery("#goodsCategory2").hide();
	jQuery("#goodsCategory1").hide();
	project= 2;
	showData();
}

function checkGood(){
	var goodsId1 =jQuery("#goodsInfo1 option:selected").attr("goodsId");	
	var goodsId2 =jQuery("#goodsInfo2 option:selected").attr("goodsId");	
	var categoryId1 =jQuery("#goodsCategory1 option:selected").attr("categoryId");	
	var categoryId2 =jQuery("#goodsCategory2 option:selected").attr("categoryId");	
	var storeId = jQuery("#storeTwo option:selected").attr("storeId");
	var time = jQuery("#yearMonth1").val();
	var timeType = jQuery("#yearMonth2").val();
	if(yearMonth==2){
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
		url : baseUrl + "order/category/check",
		data : "goodsId1=" + goodsId1 + "&goodsId2=" + goodsId2  + "&storeId=" + storeId + "&categoryId1=" + categoryId1+ 
		"&categoryId2=" + categoryId2 + "&time=" + time+ "&timeType=" + timeType,
		dataType : "json",
		success : function(e){
			jQuery("#yearMonth1").val(time);
			json  = e.msg;
			showData();
		}
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
    	name1= jQuery("#goodsCategory1 option:selected").val();
    	name2= jQuery("#goodsCategory2 option:selected").val();
    	data1= json.joinCategory;
    	data2= json.joinOrderCategory;
    }
    else {
    	name1= jQuery("#goodsInfo1 option:selected").val();
    	name2= jQuery("#goodsInfo2 option:selected").val();
    	data1= json.joinDetail;
    	data2= json.joinOrderDetail;
    }
    
	//按年  or 月 PK
    if(yearMonth == 1){
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
		url : baseUrl + "order/store/check",
		data : "time=" + times + "&storeId=" + storeId + "&timeType=" + timeType,
		dataType : "json",
		success : function(e){
			joinData = e.msg;
			jQuery("input[name='birthday']").val(times);
			changeType();
		}
	});
}

jQuery(function(){
	jQuery("input[name='birthdays']").hide();
	jQuery("input[name='birthdays']").val(year.toString() +"-"+month);
	jQuery("input[name='birthday']").val(year);
	jQuery("#yearMonth1").val(year);
	jQuery("#yearMonth2").val(year.toString() +"-"+month);
	secends();
	showYear()
})


function changeType(){
	var type = jQuery("#yj option:selected").val();
	
	if(type == 1){
		secends()
	}
	else{
		firest()
	}
}
function firest(){
	secendData(joinData.day,joinData.jsonaMonth);
	firstData(joinData.month,joinData.jsonaYear);
}

function secends(){
	secendData(joinData.day,joinData.jsonoMonth);
	firstData(joinData.month,joinData.jsonoYear);
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
</script>
</html>