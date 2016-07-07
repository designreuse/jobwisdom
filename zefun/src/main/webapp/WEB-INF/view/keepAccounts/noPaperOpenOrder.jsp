<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/open_card_alert.css" type="text/css" />

<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right clearfix'>
			    <p><button onclick = "alertZzc()">点击进入开单</button><input type="text" placeholder="输入手牌号"><button>查找</button></p>
			    <div class="open_card_content">
				   <ul class="clearfix">
				     <c:forEach items="${cashierDtoList}" var="cashierDto">
				        <li>
						   <img src="<%=basePath%>images/money_close.png">
						   <p>手牌号${cashierDto.handOrderCode }</p>
						   <div class="customer">
						     <span>顾客：散户</span>
							 <em>开单时间：${fn:substring(cashierDto.createTime, 11, 16)}</em>
						   </div>
						   <div class="customer_content">
						      <div class="customer_content_div">
						        <span class="left_click"><img src="<%=basePath%>images/left_click.png"></span>
				              <div class="table_content clearfix">			   
							     <c:forEach items="${cashierDto.orderDetails}" var="orderDetail">
							         <table>
									   <tr>
									    <td colspan="5" name = "projectId" projectId = "3173">
									        <c:choose>
									           <c:when test="${orderDetail.projectId == null}">
									               <em style="position:absolute;right:-4px;top:-10px">
											            <img src="<%=basePath%>images/hand_close.png">
											       </em>
											       <em class="empty" onclick = "settingProject(${orderDetail.detailId})" >+</em>
									           </c:when>
									           <c:otherwise>
									                ${orderDetail.projectName}
									                <span>
									                    <img src="<%=basePath%>images/architecture_edit.png">
									                    <em><img src="<%=basePath%>images/hand_close.png"></em>
									                </span>
									           </c:otherwise>
									        </c:choose>
									    </td>
									  </tr> 
									  <tr>
									    <td>岗位</td>
									    <td>开始</td>
									    <td>结束</td>
									    <td>服务者</td>
										<td>操作</td>
									  </tr>
									  <c:forEach items="${orderDetail.stepList}" var="step">
									      <tr name = "shiftMahjongStep" shiftMahjongStepId = "${step.shiftMahjongStepId}" shiftMahjongId = "${step.shiftMahjongId}" positionId = "${step.positionId}" employeeId = "${step.employeeInfo.employeeId }" isOver = "${step.isOver }">
										    <td>${fn:substring(step.positionName, 0, 1)}</td>
										    <td>${fn:substring(step.beginTime, 0, 5)}</td>
										    <td>${fn:substring(step.finishTime, 0, 5)}</td>
										    <td>
										       <c:choose>
										          <c:when test="${step.employeeInfo != null}">
										             ${step.employeeInfo.name}
										             <c:if test="${step.isOver == 2}">
										                  <a href="javascript:;" onclick="showServers(this, 1)">改</a>
										             </c:if>
										          </c:when>
										          <c:otherwise>
										             <em onclick="showServers(this, 2)">+</em>
										          </c:otherwise>
										       </c:choose>
										    </td>
											<td onclick="overServerEmployee(this)">
											<c:if test="${step.isOver == 1}">
							                  <img src="<%=basePath%>images/do_over.png" style="position:relative;right:0px;top:2px"></td>
							                </c:if>
										  </tr>
									  </c:forEach>
									</table>
							     </c:forEach>
							   </div>	
							    
								<span class="right_click"><img src="<%=basePath%>images/right_click.png"></span>
							 </div>
							 
							 <ul class="clearfix">
							   <li class="active2"></li>
							   <li></li>			   
							 </ul>
							 <div class="table_content_button">
							    <button onclick = "settlementOrder(this, ${cashierDto.orderId})">结算</button>
							 </div>
						   </div> 
						 </li>
				     </c:forEach>
				   </ul>
				 </div>
			  </div> 
        </div>
  </div>
</div>	

<div class="zzc" name = "openOrderZzc" style="display:none">
  <div class="zzc_open_card_alert">
     <p>开单</p>
     <div class="zzc_open_card_alert_content">
	    <p>
		  <span>手牌号<input type="text" name = "handOrderCode" style="width: 40px"></span>
		  <span>会员开单<input type="text" name = "memberId"></span>
		  <em>散客<i>男<input type="radio" name="sex" value = "男" checked></i><i>女<input type="radio" name="sex" value = "女"></i></em>
		  <em style="width:120px" class="order_top">预约开单<input type="checkbox"></em>
		</p>
	  <div class="open_card_alert_state_show">	
	    <div class="open_card_alert_state">
		  <ul class="clearfix" name = "positionUl">
		    
		  </ul>
		</div>
	    <div class="open_card_alert_state_button">
		  <button onclick="submits()">开单</button>
		  <button>取消</button>
		</div>
	 </div>
 
       </div> 
   
      <div class="card_number" style="display:none">
	    <img src="<%=basePath%>images/open_card_close.png">
	    <p>深灰色为不可选状态</p>
	    <ul class="clearfix" name = "handNumberUl">

		</ul>
	  </div>
   
      <div class="order_open_card" style="display:none">
	    <div class="order_open_card_close"><img src="<%=basePath%>images/open_card_close.png"></div>
	    <p>2016／06/23 16:57</p>
		 <div class="order_open_card_ clearfix">
			<div class="order_open_card_content">
			   <div class="order_open_card_content_left">
				  <div class="img">
					<img src="assets/images/seo_pic.png">
				  </div>
				  <div class="img_right">
					 <div>实打实的</div>
					 <p>1313131313113</p>
					 <span>收银预约</span>
				  </div>
			   </div>
				<div class="order_open_card_content_right">
				   <p>啊啊啊啊啊啊系列</p>
				   <span>预约时间16:30 已过期</span>
				   <div>110 名啊字</div>
				   <div>美发发型师</div>
			   </div>
			</div>
			
			<div class="order_open_card_content">
			   <div class="order_open_card_content_left">
				  <div class="img">
					<img src="assets/images/seo_pic.png">
				  </div>
				  <div class="img_right">
					 <div>实打实的</div>
					 <p>1313131313113</p>
					 <span>收银预约</span>
				  </div>
			   </div>
				<div class="order_open_card_content_right">
				   <p>啊啊啊啊啊啊系列</p>
				   <span>预约时间16:30 已过期</span>
				   <div>110 名啊字</div>
				   <div>美发发型师</div>
			   </div>
			</div>
			
			
				<div class="order_open_card_content">
			   <div class="order_open_card_content_left">
				  <div class="img">
					<img src="assets/images/seo_pic.png">
				  </div>
				  <div class="img_right">
					 <div>实打实的</div>
					 <p>1313131313113</p>
					 <span>收银预约</span>
				  </div>
			   </div>
				<div class="order_open_card_content_right">
				   <p>啊啊啊啊啊啊系列</p>
				   <span>预约时间16:30 已过期</span>
				   <div>110 名啊字</div>
				   <div>美发发型师</div>
			   </div>
			</div>
			
		 </div>
         
        		 
	  </div>
   </div>
</div>

<div class="zzc1" style="display:none">
  <div class="zzc1_open_card_alert">
  <p>修改服务人员</p>
    <div class="open_card_alert_state_content">
		     <p>
		        <span>选择轮牌名称<input type="text" name= "shiftMahjongName" shiftMahjongId = ""></span>
		        <span>选择轮牌人员<input type="text" name="shiftMahjongEmployeeName" shiftMahjongEmployeeId = ""></span>
		        <span>是否指定<input type="checkbox" name= "isAssign"></span>
		     </p>
			 <div class="open_state" name = "addOrUpdate">
			   <span><em style="background:#21d9db"></em>空闲</span>
			   <span><em style="background:#e11e23"></em>工作</span>
			   <span><em style="background:#e7a3ef"></em>点客</span>
			   <span><em style="background:#eede9f"></em>暂休</span>
			 </div>
			<div class="open_card_alert_state_button">
			  <button onclick="sureAddOrUpdate(this)">开单</button>
			  <button>取消</button>
			</div>
	</div>  
  </div>

</div>
<script type="text/javascript">
var startHandNumber = '${startHandNumber}';
/* var positionInfoShiftMahjongDtoListStr = '${positionInfoShiftMahjongDtoListStr}';
var positionInfoShiftMahjongDtoList = eval("(" + positionInfoShiftMahjongDtoListStr + ")"); */
</script>
<script type="text/javascript" src="<%=basePath %>js/keepAccounts/noPaperOpenOrder.js"></script>
</body>
</html>