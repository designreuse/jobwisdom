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
	  .scheduling{width:94%;margin:2rem auto;background:white;border-radius:8px;overflow:hidden}
	  .scheduling table{width:100%}
	  .scheduling table tr td{color:white;font-size:16px;width:33.3%;height:6rem;text-align:center;vertical-align:middle;border-right:1px solid #ebeff8;border-bottom:1px solid #ebeff8}
	  .scheduling table tr td:last-child{border-right:none}
	  .scheduling table tr:last-child td{border-bottom:none;}
	  .scheduling table tr:nth-child(1){background:#ed5f19}
	  .scheduling table tr:nth-child(2) td{color:#f1381a}
	  .scheduling table tr:nth-child(3) td{color:#ff8e26}
	  .scheduling table tr:nth-child(4) td{color:#e1cd28}
	  .scheduling table tr:nth-child(5) td{color:#28c27b}
	  .scheduling table tr:nth-child(6) td{color:#40cfd1}
	  .scheduling table tr:nth-child(7) td{color:#450096}
	  .scheduling table tr:nth-child(8) td{color:#920088}
	</style>
  </head>
  <body>
      <div class="con"> 
        <div class="scheduling">
	      <table cellpadding="0" cellspacing="0">
	        <tr>
			 <td>日期</td>
			 <td>班次</td>
			 <td>时间</td>
			</tr>
			<c:forEach items="${maps}" var="map" varStatus="status">
			   <tr>
				 <td>
				       ${map.cname }
				   <p>${map.ename }</p>
				 </td>
				 <td>${map.shifName }</td>
				 <td>${map.startTime }~${map.endTime }</td>
			   </tr>
			</c:forEach>
		  </table>
	    </div>	
 </body>
</html>