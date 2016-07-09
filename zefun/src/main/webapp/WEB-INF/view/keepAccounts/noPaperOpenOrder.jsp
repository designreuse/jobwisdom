<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/open_card_alert.css" type="text/css" />
<style>
	.open_card_alert_state_content>p{padding:8px 20px;height:26px;line-height:26px;background:#d8deed}
	.open_card_alert_state_content>p input[type="text"]{width:100px!important}
	.open_card_alert_state_content .active1{border:none}
	.zzc1, .zzc2{display:none}
	.zzc_open_card_alert_content>p>span em{margin-left:8px;vertical-align:middle;display:inline-block;width:70px;height:26px;border-radius:12px;border:1px solid #838383;position:relative}
	.zzc_open_card_alert_content>p>span em img{position:absolute;right:5px;top:10px}
	.zzc_open_card_alert_content>p>i{display:inline-block;padding:2px 10px;border:1px solid #717171;border-radius:12px;margin-right:15px;cursor:pointer}
	.zzc_open_card_alert_content i a{color:black}
	.zzc_open_card_alert_content>p>em{display:inline-block;width:86px;height:20px;text-align:center;cursor:pointer;}
	.zzc_open_card_alert_content>p>em img{width:18px}
	.active_border{background:url('<%=basePath%>images/checked_.png') no-repeat!important;background-size:18px!important}
	.active_border_{border:1px solid #e4671b!important;color:#e4671b}
	.active_color{border:1px solid #e4671b!important;}
	.active_color_{color:#e4671b!important}
	.zzc_open_card_alert_content>p>em>i{display:inline-block;width:18px;height:18px;background:url('assets/images/check.png') no-repeat;vertical-align:middle;margin-left:2px;background-size:18px}
	.zzc_open_card_alert_content>p input{width:100px!important;position:relative;left:4px;border:none!important;display:none;height:14px!important}
	.zzc_open_card_alert_content>p>i>em{display:inline-block;width:21px;height:19px;background:url(assets/images/seach.png) no-repeat;vertical-align:middle;margin-left:5px}
	.zzc_open_card_alert_content>p>i .action{background:url('assets/images/seach_.png') no-repeat}
</style>
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
						   <img src="<%=basePath%>images/money_close.png" onclick="deleteOrderInfo(${cashierDto.orderId })">
						   <p>手牌号${cashierDto.handOrderCode}</p>
						   <div class="customer">
						     <c:choose>
					           <c:when test="${cashierDto.memberId != null}">
					               <span>顾客：${cashierDto.memberInfo.name}</span>
					           </c:when>
					           <c:otherwise>
					                <span>顾客：散客</span>
					           </c:otherwise>
					         </c:choose>
							 <em>开单时间：${fn:substring(cashierDto.createTime, 11, 16)}</em>
						   </div>
						   <div class="customer_content">
						      <div class="customer_content_div">
						        <span class="left_click"><img src="<%=basePath%>images/left_click.png"></span>
				              <div class="table_content clearfix">			   
							     <c:forEach items="${cashierDto.orderDetails}" var="orderDetail">
							         <table>
									   <tr>
									        <c:choose>
									           <c:when test="${orderDetail.projectId == null}">
									             <td colspan="5" name = "projectId" projectId = "">
									               <em style="position:absolute;right:-4px;top:-10px" onclick="deleteDetailId(${orderDetail.detailId})">
											            <img src="<%=basePath%>images/hand_close.png">
											       </em>
											       <em class="empty" onclick = "showSettingProject(${orderDetail.detailId})" >+</em>
											     </td>
									           </c:when>
									           <c:otherwise>
									              <td colspan="5" name = "projectId" projectId = "${orderDetail.projectId}">
									                ${orderDetail.projectName}
									                <span>
									                    <img src="<%=basePath%>images/architecture_edit.png">
									                    <em><img onclick="deleteDetailId(${orderDetail.detailId})" src="<%=basePath%>images/hand_close.png"></em>
									                </span>
									              </td>
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
										             <c:if test="${step.isOver == 1}">
										                  <a href="javascript:;" onclick="showServers(this, 1)">改</a>
										             </c:if>
										          </c:when>
										          <c:otherwise>
										             <em onclick="showServers(this, 2)">+</em>
										          </c:otherwise>
										       </c:choose>
										    </td>
											<td>
											<c:if test="${step.isOver == 1}">
							                  <img onclick="overServerEmployee(this)" src="<%=basePath%>images/do_over.png" style="position:relative;right:0px;top:2px"></td>
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
     <div class="zzc_open_card_alert_content" name="memberTR" selectType="3">
		<p>
		  <span>手牌号<em name = "handOrderCode"><img src="<%=basePath%>images/open_card_img.png"></em></span>
		  <i><a href="javascript:;">会员开单
		       <input type="text"name="phoneNumber" placeholder="会员手机号">
		       <span class="iconfont icon-sousuo ml-30 mt5" name="seekName"></span>
			   <div class="show_search" name="memberListDIV"
					style="display: none;">
					<p>
						以<i name="conditionValue">12</i>为条件显示到<i name="showList">20</i>位顾客
						<em><input type="checkbox" name="enterpriseCheck"
							onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
						</em>
					</p>
					<div class="common_close" onclick="cancleMemberSelect(this)">
						<img src="<%=basePath%>images/emploee_3.png">
					</div>
					<div style="height: 400px; overflow: overlay;"
						name="memberoverDIV"></div>
				</div>
		     </a>
		     <em></em>
		  </i>
		  <em>散客开单<i></i></em>
		  <em>预约开单<i></i></em>
		</p>
		<div class="open_card_table" name = "memberNoPage">
		   <table>
		      <tr>
			    <td>名称</td>
				<td>手机号码</td>
				<td>性别</td>
			  </tr>
			  <tr>
			    <td name = "memberName" memberId = "" appointmentId = ""></td>
				<td name = 'memberPhone'></td>
				<td name = "memberSex" memberSex = ""><!-- <span><input type="radio" name="sex" value = "男" checked>男</span><span><input type="radio" name="sex" value = "女">女</span> --></td>
			  </tr>
		   </table>
		</div>
		<div class="open_card_alert_state_show">	
		    <div class="open_card_alert_state">
			  <ul class="clearfix" name = "positionUl">
			    
			  </ul>
			</div>
		    <div class="open_card_alert_state_button">
			  <button onclick="submits()">开单</button>
			  <button onclick = "hideModal()">取消</button>
			</div>
		</div>
 
       </div> 
   
      <div class="card_number" style="display:none">
	    <img src="<%=basePath%>images/open_card_close.png" onclick = "closeCardNumber()">
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
			  <button onclick = "hideModal()">取消</button>
			</div>
	</div>  
  </div>
</div>

<div class="zzc2" style="display:none">
   <div class="zzc2_select_item">
      <p>选择项目</p>
	  <div class="zzc2_select_item_content">
	      <p>
	         <select name = "deptName">
	            <c:forEach items="${deptList}" var="dept" varStatus="status">
	                <option value="${dept.deptId}">${dept.deptName}</option>
	            </c:forEach>
	         </select>
	      </p>
	      <c:forEach items="${dtoList}" var="dto" varStatus="status">
	         <div class="zzc2_select_item_content_ clearfix" deptId = "${dto.deptId}" <c:if test="${status.index != 0 }">style="display: none;"</c:if>>
			  <div class="zzc2_select_item_content_left">
				 <ul>
				   <c:forEach items="${dto.project}" var="projectCategoryDto" varStatus="categoryStatus">
		                <li categoryId="${projectCategoryDto.categoryId }" <c:if test="${status.index == 0 }">class="active"</c:if>>${projectCategoryDto.categoryName}</li>
		           </c:forEach>
				 </ul>			 
			  </div> 
			  <div class="zzc2_select_item_content_right">
			    <c:forEach items="${dto.project}" var="projectCategoryDto" varStatus="categoryStatus">
			        <div class="zzc2_select_item_content_right_content <c:if test="${categoryStatus.index != 0 }">hide</c:if>" categoryId="${projectCategoryDto.categoryId }">
	                   <ul class="clearfix">
	                     <c:forEach items="${projectCategoryDto.projectList}" var="projectInfo" varStatus="projectStatus">
	                        <li <c:if test="${projectStatus.index == 0 }">class="active4"</c:if> projectId = "${projectInfo.projectId}">
							   <div>${projectInfo.projectName}</div>
							   <span>价格:<em>${projectInfo.projectPrice}</em></span>
							</li>
	                     </c:forEach>
					   </ul>
	                </div>	
			    </c:forEach>
			  </div> 
		  </div>
	      </c:forEach>
		  
	  </div>
     <div class="select_item_button">
        <button>确认</button>
		<button>取消</button>
     </div>
    	 
   </div>
   <%@ include file="/template/memberData.jsp"%>
</div>
<script type="text/javascript">
var startHandNumber = '${startHandNumber}';

jQuery('.zzc_open_card_alert_content>p>em').click(function(){
    jQuery(this).find('i').stop(true,true).toggleClass('active_border');
	  jQuery(this).stop(true,true).toggleClass('active_border_');
	  
 }) 
 jQuery('.zzc_open_card_alert_content>p>i').click(function(e){
   jQuery(this).stop(true,true).toggleClass('active_color');
	 jQuery(this).find('a').stop(true,true).toggleClass('active_color_');
	 jQuery(this).find('em').stop(true,true).toggleClass('action');
  if(!jQuery(e.target).is('input')) {
	   jQuery(this).find('input').stop(true,true).toggle('normal')
   }
 });


</script>
<script type="text/javascript" src="<%=basePath %>js/keepAccounts/noPaperOpenOrder.js"></script>
</body>
</html>