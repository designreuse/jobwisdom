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
			               <p style="background:#ededed;color:white;font-size:32px" onclick = "selectCategory(${orderDetail.detailId})">+</p>
			           </c:when>
			           <c:otherwise>
			                <p>${orderDetail.projectName}<button onclick = "selectCategory(${orderDetail.detailId})">修改</button></p>	
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
			                     <em style="background: #3ec4e6;">结束</em>
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
		   <li style="background:#ea631a" onclick = "overServer()">结束</li>
		</ul>
	  </div>	
 </body>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script>
function selectCategory (detailId) {
	window.location.href = baseUrl+"staff/view/selectCategory?detailId="+detailId;
}

function overServer () {
	window.location.href = baseUrl+"staff/view/reception";
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
</script>
</html>