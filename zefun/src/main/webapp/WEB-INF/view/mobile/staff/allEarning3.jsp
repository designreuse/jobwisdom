<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>排行</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
</head>
<body>
<div class="con" style="background:white"> 
	    <div class="time_rank clearfix">
	      <ul class="clearfix"> 
		   <li class="active">日排行</li>
		   <li>周排行</li>
		   <li>月排行</li>
		  </ul> 
		</div>
		<ul class="clearfix achievement">
		  <li class="active">劳动业绩</li>
		  <li>指定业绩</li>
		  <li>指定比例</li>
		  <li>套餐业绩</li>
		  <li>商品销售</li>
		  <li>卡项销售</li>
		</ul> 
		<div class="rank_content">
		   <div class="rank_detail">
		      <span class="num">1</span>
		      <img src="images/rank_head.png">
		      <em>李狗蛋</em>
		      <em>1101</em>
		      <i>劳动业绩：<span>¥0.00</span><img src="<%=basePath%>images/mobile/newemployee/right.png"></i>
		      
		   </div>
		</div>
		<div class="rank_content">
		   <div class="rank_detail">
		      <span class="num">2</span><img src="images/rank_head.png"><em>李狗蛋</em><em>1101</em><i>劳动业绩：<span>¥0.00</span><img src="<%=basePath%>images/mobile/newemployee/right.png"></i>
		   </div>
		</div>
		<div class="rank_content">
		   <div class="rank_detail">
		      <span class="num">3</span><img src="images/rank_head.png"><em>李狗蛋</em><em>1101</em><i>劳动业绩：<span>¥0.00</span><img src="<%=basePath%>images/mobile/newemployee/right.png"></i>
		   </div>
		</div>
     </div>

  
    <ul class="bottom_fix clearfix">
      <li>
          <a href="<%=basePath%>staff/view/reception">
	          <img src="<%=basePath%>images/mobile/newemployee/bottomOne.png">
		      <p>开单</p>
	      </a>
	  </li>
	  <li>
	     <a href="<%=basePath%>staff/view/employeeOrderView/${session_key_store_account}/2">
	        <img src="<%=basePath%>images/mobile/newemployee/bottomTwo.png">
	        <p>订单</p>
	     </a>
	  </li>
	  <li>
	    <a href="<%=basePath%>staff/view/allEarning">
		    <img src="<%=basePath%>images/mobile/newemployee/bottomThree.png">
		    <p>排行</p>
	    </a>
	  </li>
	  <li>
	     <a href="<%=basePath%>staff/view/staffCenter/${session_key_store_account}/2">
		     <img src="<%=basePath%>images/mobile/newemployee/bottomFour_.png">
		     <p>我的</p>
	     </a>
	  </li>
    </ul>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script>
function changeShow(obj, type) {

    $(".score-shop-li").removeClass("active");
    $(".score-shop-li").find("img").addClass("hide");
    $(".border-group").removeClass("hide");
    $(obj).find("img").removeClass("hide");
    $(obj).find(".border-group").addClass("hide");
    $(obj).addClass("active");
    
    $("#dateDIV").empty();
    
    chooseDateType(type);
}

function chooseDateType(type) {
	$.ajax({
        type : "post",  
        url : baseUrl + "staff/action/selectEmployeeData",
        data : "chooseType="+type,
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            var data = e.msg;
            var dataList = data.dataList;
            for (var i = 0; i < dataList.length; i++) {
            	var dataObj = dataList[i];
            	var partDIV = "<div class='part'>";
            	var a = i + 1;
            	partDIV = partDIV + "<div class='toggle-ctl  ul-title'>"+
						                "<div class='fl'>"+
							                "<span class='font-333'>"+a+"</span>"+
							            "</div>"+
							            "<div class='desc fl'>"+
							                "<img src='"+picUrl + dataObj.headImage+"' alt='' class='person-img'/>"+
							                "<div><span>"+dataObj.employeeCode+"</span> <span> "+dataObj.name+"</span></div>"+
							            "</div>"+
							            "<span class='fr'><span class='blue-word'>"+dataObj.projectValue+"元 </span><i class='iconfont icon-zhankai' style='padding-left: 15px;'></i></span>"+
							            "<div class='clearfix'></div>"+
							        "</div>"+
							        "<div class='table-wrap'>"+
							        "<table class='table' style='display: none;'>"+
			                        "<tbody>"+
			                        "<tr class='h60 line-height60 font-gray font-999'>"+
			                            "<td>均值</td>"+
			                            "<td class='text-center'>关键指标</td>"+
			                            "<td class='text-right'>我的表现</td>"+
			                        "</tr>"+
			                        "<tr class='h90 line-height90'>"+
			                            "<td class='font-gray'>"+data.totalProjectScale+"元</td>"+
			                            "<td class='font-999 text-center'>劳动业绩</td>";
			    if (dataObj.projectValue < data.totalProjectScale) {
			    	partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.projectValue+"元</td>";
			    }
			    else {
			    	partDIV = partDIV + "<td class='text-right'>"+dataObj.projectValue+"元</td>";
			    }  
			    
			    partDIV = partDIV + "</tr>"+
			                        "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalAssignProjectScale+"元</td>"+
					                    "<td class='font-999 text-center'>指定业绩</td>";
	            
                if (dataObj.assignProjectValue < data.totalAssignProjectScale) {
                	partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.assignProjectValue+"元</td>";
                }
                else {
                	partDIV = partDIV + "<td class='text-right'>"+dataObj.assignProjectValue+"元</td>";
                }
                
                partDIV = partDIV + "</tr>"+
					                "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalScale+"%</td>"+
					                    "<td class='font-999 text-center'>指定比例</td>";
					                    
                if (dataObj.scale < data.totalScale) {
                	partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.scale+"%</td>";
                }
                else {
                	partDIV = partDIV + "<td class='text-right'>"+dataObj.scale+"%</td>";
                }
                
                partDIV = partDIV + "</tr>"+
					                "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalComboScale+"元</td>"+
					                    "<td class='font-999 text-center'>套餐业绩</td>";
                    
				if (dataObj.comboValue < data.totalComboScale) {
				    partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.comboValue+"元</td>";
				}
				else {
				    partDIV = partDIV + "<td class='text-right'>"+dataObj.comboValue+"元</td>";
				}
				
				partDIV = partDIV + "</tr>"+
					                "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalGoodsScale+"元</td>"+
					                    "<td class='font-999 text-center'>商品销售</td>";

				if (dataObj.goodsValue < data.totalGoodsScale) {
					partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.goodsValue+"元</td>";
				}
				else {
					partDIV = partDIV + "<td class='text-right'>"+dataObj.goodsValue+"元</td>";
				}
				
				partDIV = partDIV + "</tr>"+
					                "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalChargeScale+"元</td>"+
					                    "<td class='font-999 text-center'>卡项销售</td>";

				if (dataObj.chargeValue < data.totalChargeScale) {
					partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.chargeValue+"元</td>";
				}
				else {
					partDIV = partDIV + "<td class='text-right'>"+dataObj.chargeValue+"元</td>";
				}
				
				partDIV = partDIV + "</tr>"+
				                "</tbody>"+
				                "</table>"+
				                "</div>"+
				            "</div>";
				            
				$("#dateDIV").append(partDIV);
            }
        }
    });
}
</script>
</body>
</html>
