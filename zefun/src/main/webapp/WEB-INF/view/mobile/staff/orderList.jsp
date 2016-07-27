<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>服务订单</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>css/employee/shop.css">
       <style>
	       .order_style>p>button{position:relative;top:0;left:0;margin-left:2px}
		   .order_style>p>img{position:absolute;right:4px;width:22px;top:4px}
		   .order_ul li span{display:inline-block;width:50%;height:4rem;float:left}
		   .zzc{display:none;position: fixed;top: 0px;height: 100%;left: 0px;width: 100%;background: rgba(102, 108, 121, 0.8);z-index: 2;}  
		   .delete_order{margin:15% auto;width:22rem;height:13rem;background:white;border-radius:8px;overflow:hidden} 
		   .delete_order>p{height:3rem;line-height:3rem;color:white;text-align:center;background:#ed5f19;font-size:16px}
		   .delete_order_content em{color:#ed5f19;font-size:14px}
		   .delete_order_content>p{text-align:center;padding-top:2rem;font-size:13px}
		   .delete_order_content_button{text-align:center;margin-top:2rem}
		   .delete_order_content_button button{background:white;margin:0 1.5rem;border:none;border-radius:8px;font-size:14px;text-align:center;height:3rem;width:6rem}
		   .select_people{background:#fbcd8e!important}
		   .begin{background:#3ec4e6!important}
		   .select_people_over{background:#f69731!important}
	   </style>
  </head>
  <body>
     <div class="con"> 
		   <ul class="clearfix goods">
		      <c:choose>
		          <c:when test="${orderDto.memberId != null }">
		              <li>顾客:${orderDto.memberName }</li>
		          </c:when>
		          <c:otherwise>
		              <li>顾客:散客</li>
		          </c:otherwise>
		      </c:choose>
			  <li>手牌号：${orderDto.handOrderCode }</li>
			  <li>开单时间：${fn:substring(orderDto.createTime, 11, 16)}</li>
		   </ul>
		   <c:forEach items="${orderDto.orderDetails}" var="orderDetail" varStatus="status">
                <div class="order_style">
                   <c:choose>
			           <c:when test="${orderDetail.projectId == null}">
			               <p style="background:#fbcd8e;color:white;font-size:32px" onclick = "selectCategory(${orderDetail.detailId})">+<img src="<%=basePath%>images/mobile/newemployee/order_close.png" name = "deleteShow" detailId = "${orderDetail.detailId}" deleteType = "1"></p>
			           </c:when>
			           <c:otherwise>
			                <p>${orderDetail.projectName}<button onclick = "selectCategory(${orderDetail.detailId})">修改</button><img src="<%=basePath%>images/mobile/newemployee/order_close.png" name = "deleteShow" detailId = "${orderDetail.detailId}" deleteType = "1"></p>	
			           </c:otherwise>
			        </c:choose>
			       <table cellpadding="0" cellspacing="0">
				     <tr>
					   <td>岗位</td>
					   <td>开始时间</td>
					   <td>结束时间</td>
					   <td>服务者</td> 
					   <td>操作</td> 
					 </tr>
					 <c:forEach items="${orderDetail.stepList}" var="step" varStatus="status">
					      <tr>
						   <td>${fn:substring(step.positionName, 0, 3)}</td>
					       <td>${fn:substring(step.beginTime, 0, 5)}</td>
					       <td>${fn:substring(step.finishTime, 0, 5)}</td>
						   <td>
						      <c:choose>
						          <c:when test="${step.employeeInfo != null}">
						             <span <c:if test="${step.isOver == 2}">class="over"</c:if> <c:if test="${step.isOver == 1}">onclick="serverAssociate(${step.shiftMahjongStepId}, ${step.positionId}, 1)"</c:if>>${step.employeeInfo.name}</span>
						          </c:when>
						          <c:otherwise>
						             <span class="select_people" onclick="serverAssociate(${step.shiftMahjongStepId}, ${step.positionId}, 2)">+</span>
						          </c:otherwise>
						       </c:choose>
						   </td>
						   <td>
						      <c:if test="${step.isOver == 1}">
			                     <em class="select_people_over" onclick="overServerEmployee(${step.shiftMahjongStepId})">结束</em>
			                  </c:if>
			                  <c:if test="${step.isOver == 2}">
			                     <em>结束</em>
			                  </c:if>
						   </td>  
						 </tr>
					 </c:forEach>
				  </table> 
				 </div>
           </c:forEach>
	  </div>
	  <div style="height:4rem"></div>
	  <div class="order_ul">	
		<ul class=" clearfix">
		   <li onclick="addDetail(${orderDto.orderId})">添加服务项</li>
		   <li>
	      	   <span style="background:#949494" name = "deleteShow" detailId = "${orderDto.orderId}" deleteType = "2">删除</span>
	      	   <span style="background:#ea631a" onclick = "overServer(${orderDto.orderId})">结束</span>
		   </li>
		</ul>
	  </div>	
	  
	<div class="zzc">
	    <div class="delete_order">
	      <p name = "pName">删除订单</p>
	      <div class="delete_order_content">
		    <p>您确定要删除掉这个<em name = "emName">订单</em>么？</p>
		    <div class="delete_order_content_button">
			  <button style="border:1px solid #ed5f19" name = "buttonName">确定</button>
			  <button style="background:#ed5f19;color:white" onclick = "cancl()">取消</button>
			</div>
		  </div>
	    </div>
	 </div>
 </body>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script>
function selectCategory (detailId) {
	window.location.href = baseUrl+"staff/view/selectCategory?detailId="+detailId;
}

function overServer (orderId) {
	$.ajax({
        type : "post",
        url : baseUrl + "staff/action/updateIsOrderOption",
        data : "orderId="+orderId,
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            window.location.href = baseUrl+"staff/view/reception";
        }
    });
}

function cancl () {
	jQuery(".zzc").hide();
}

function addDetail(orderId) {
	$.ajax({
        type : "post",
        url : baseUrl + "staff/action/addDetailServer",
        data : "orderId="+orderId,
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            location.reload();
        }
    });
}

function serverAssociate(shiftMahjongStepId, positionId, type) {
	window.location.href = baseUrl+"staff/view/serverAssociate?positionId="+positionId+"&shiftMahjongStepId="+shiftMahjongStepId+"&type="+type;
}

function overServerEmployee(shiftMahjongStepId) {
	jQuery.ajax({
    	url : baseUrl + "staff/action/overServerEmployee",
    	type : "POST",
    	data : "shiftMahjongStepId="+shiftMahjongStepId,
    	success : function(e){
    		if (e.code != 0) {
                dialog(e.msg);
                return;
            }
    		var datas = e.msg;
    		dialog("操作成功！");
    		location.reload();
    	}
    });
}

jQuery("[name='deleteShow']").click(function(event){
	var oId = jQuery(this).attr("detailid");
	var type = jQuery(this).attr("deletetype");
	
	if (type == 1) {
		jQuery("p[name='pName']").text("删除服务");
		jQuery("em[name='emName']").text("服务");
		jQuery("button[name='buttonName']").attr("onclick", "deleteDetailId('"+oId+"')");
		event.stopPropagation() 
	}
	else {
		jQuery("p[name='pName']").text("删除整单");
		jQuery("em[name='emName']").text("订单");
		jQuery("button[name='buttonName']").attr("onclick", "deleteOrderInfo('"+oId+"')");
	}
	jQuery(".zzc").show();
	return false;
});

function deleteShow (oId, type) {
	
}

function deleteDetailId (detailId) {
	jQuery.ajax({
		type : "post",  
		url : baseUrl + "staff/action/deleteOrderDetail",
		data : "detailId="+detailId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("删除服务成功！");
			if (e.msg == 1) {
				location.reload();
			}
			else {
				window.location.href = baseUrl+"staff/view/reception";
			}
		}
	});
}

function deleteOrderInfo (orderId) {
	jQuery.ajax({
		type : "post",  
		url : baseUrl + "staff/view/deleteOrderInfo",
		data : "orderId="+orderId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("删除订单成功！");
			window.location.href = baseUrl+"staff/view/reception";
		}
	});
}
</script>
</html>