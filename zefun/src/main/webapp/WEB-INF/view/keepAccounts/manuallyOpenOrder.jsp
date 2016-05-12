<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/hand.css" type="text/css" />
<style>
i{font-style: normal;}
.hand_2 em {
    display: inline-block;
    margin-left: 40px;
}

em {
    font-style: normal;
}

.hand_2 select {
    border-radius: 5px!important;
    box-shadow: 0 0 3px #ccc;
}


</style>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
            <div class="content_right">
			    <div  class="hand_search" style="position:relative" name = "memberTR">
			         <div style="display: inline-block;" name= "seekTD">
			             <span>会员名称：</span>
					     <input type="text" class="focus_" name = "phoneNumber" placeholder="会员手机号">
						 <ul class="focus_input">
						     
						 </ul>
						 <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
						 <em class="content_right_sex">
						   <i>男 <input type="radio" name="sex" value="男" checked></i>
						   <i>女 <input type="radio" name="sex" value="女"></i>
					     </em>
			         </div>
				     <div style="position:relative;display:none" name="resultTD" class="hand-detail">
				          <span>手机号：<em name = "memberPhoneSpan">13414313443</em> &nbsp;
				                                           姓名：<em name = "memberNameSpan">lily</em> &nbsp; 
				                                           性别：<em name = "memberSexSpan">女 </em>&nbsp;
				                                           门店：<em name = "memberSexSpan">涛美华南店</em>&nbsp;余额：0&nbsp;礼金：0&nbsp;欠款：0&nbsp;卡号：</span>
				           <button class="rsearch">重新查询</button>
				           <input type="hidden" name = "memberId" onchange="changeMember(this)">
				     </div>	
				</div> 
			 
			   <div class="kd-select-content">
		                <div class="kai-bumen kd-bmlist">
		                    <ul class="flex-box trangle">
		                        <c:forEach items="${list}" var="dept" varStatus="status">
		                           <li <c:if test="${status.index == 0}">class="flex-item bm active"</c:if> <c:if test="${status.index != 0}">class="flex-item bm"</c:if> onclick="changeDept(${dept.deptId})">
			                            <span>${dept.deptName}</span>
			                        </li>
		                        </c:forEach>
		                    </ul>
		                </div>
		                
		                <c:forEach items="${list}" var="dept" varStatus="status">
		                    <div <c:if test="${status.index == 0}">class="select-target"</c:if> <c:if test="${status.index != 0}">class="select-target hide"</c:if> name= "${dept.deptId}">
		                        <div class="select-style-wrap">
		                            <!--选择类型-->
			                        <div class="select-list">
			                            <ul>
			                                <li class="select-item active" onclick="changeType(this, 'project')">项目</li>
			                                <li class="select-item" onclick="changeType(this, 'combo')">套餐</li>
			                                <li class="select-item" onclick="changeType(this, 'goods')">商品</li>
			                               
			                            </ul>
			                        </div>
			                        
			                        <!--选择类型的系列-->
			                        <div class="selected-child-select" name = "projectUL">
			                            <ul>
			                                <c:forEach items="${dept.projectCategoryList}" var="projectCategory" varStatus="projectStatus">
			                                    <li <c:if test="${projectStatus.index == 0}">class="selected-item active"</c:if> <c:if test="${projectStatus.index != 0}">class="selected-item"</c:if> style="cursor:pointer;" categoryid = "${projectCategory.categoryId}" onclick="changeCategory(this, ${projectCategory.categoryId}, 'project')">${projectCategory.categoryName}</li>
			                                </c:forEach>
			                            </ul>
			                        </div>
			                        
			                        <div class="selected-child-select hide" name = "comboUL">
			                            <ul>
			                                <li class="selected-item active" style="cursor:pointer;">疗程</li>
			                            </ul>
			                        </div>
			                        
			                        <div class="selected-child-select hide" name = "goodsUL">
			                            <ul>
			                                <c:forEach items="${dept.goodsCategoryList}" var="goodsCategory" varStatus="goodsStatus">
			                                    <li <c:if test="${goodsStatus.index == 0}">class="selected-item active"</c:if> <c:if test="${goodsStatus.index != 0}">class="selected-item"</c:if> style="cursor:pointer;" categoryid = "${goodsCategory.categoryId}" onclick="changeCategory(this, ${goodsCategory.categoryId}, 'goods')">${goodsCategory.categoryName}</li>
			                                </c:forEach>
			                            </ul>
			                        </div>
		                        </div>
		                        
		                        <c:forEach items="${dept.projectCategoryList}" var="projectCategory" varStatus="projectStatus">
			                        <div <c:if test="${projectStatus.index == 0}">class="all-kind-wrap"</c:if> <c:if test="${projectStatus.index != 0}">class="all-kind-wrap hide"</c:if> name= "project" categoryid = "${projectCategory.categoryId}">
				                        <ul>
				                            <c:forEach items="${projectCategory.projectList}" var="project">
				                                <li class="detail-item" style="cursor:pointer;" onclick="chooceProject(${project.projectId}, '${project.projectName}', ${project.projectPrice}, 1)">
					                                <div><span class="name">${project.projectName}</span></div>
					                                <div>项目价格:<span class="item-price">￥${project.projectPrice}</span></div>
					                            </li>
				                            </c:forEach>
				                        </ul>
				                    </div>
			                    </c:forEach>
			                    <!--选择具体的项目-->
			                    
			                    <div class="all-kind-wrap hide" name= "combo">
			                        <ul>
			                            <c:forEach items="${dept.comboInfoList}" var="comboInfo">
			                                <li class="detail-item" style="cursor:pointer;" onclick="chooceProject(${comboInfo.comboId}, '${comboInfo.comboName}', ${comboInfo.comboSalePrice}, 3)">
				                                <div><span class="name">${comboInfo.comboName}</span></div>
				                                <div>套餐价格:<span class="item-price">￥${comboInfo.comboSalePrice}</span></div>
				                            </li>
			                            </c:forEach>
			                        </ul>
			                    </div>
			                    
			                    <c:forEach items="${dept.goodsCategoryList}" var="goodsCategory" varStatus="goodsStatus">
			                        <div class="all-kind-wrap hide" name= "goods" categoryid = "${goodsCategory.categoryId}">
				                        <ul>
				                            <c:forEach items="${goodsCategory.goodsBaseDtos}" var="goods">
				                                <li class="detail-item" style="cursor:pointer;" onclick="chooceProject(${goods.goodsId}, '${goods.goodsName}', ${goods.goodsPrice}, 2)">
					                                <div><span class="name">${goods.goodsName}</span></div>
					                                <div>商品价格:<span class="item-price">￥${goods.goodsPrice}</span></div>
					                            </li>
				                            </c:forEach>
				                        </ul>
				                    </div>
			                    </c:forEach>
			                    
		                    </div>
		                </c:forEach>
		                
		
		            </div> 
		   
		           <div class="pay_detail">消费明细</div>
		           
				   <div class="pay_detail_content">
				       <ul class="clearfix pay_detail_ul">
					      <li class="active" onclick="changeDiv(1)" name = "projectDetail">项目销售</li>
						  <li onclick="changeDiv(2)" name = "goodsDetail">商品销售</li>
						  <li onclick="changeDiv(3)" name = "comboDetail">疗程销售</li>
					   </ul>
						<div class="pay_detail_content_" name = "projectPay">
			                  
			            </div>	
			            <div class="pay_detail_content_ hide" name = "goodsNameLI">
			                  
			            </div>	
			            <div class="pay_detail_content_ hide" name = "comboNameLI">
			                  
			            </div>				
					   
					   <div class="hand_time">
					     <span>开单时间<input type="datetime-local" name="openOrderDate"></span>
						 <span>手工单号<input type="text" name= "handOrderCode"></span>
						<span> <button onclick="save()">立即开单</button></span>
					   </div>
		
		           </div>
		                
		    </div>
	        <%@ include file="/template/memberData.jsp" %>
        </div>
  </div>
</div>

<script type="text/javascript">
   var employeeInfoListStr = '${employeeInfoList}';
   var employeeInfoList = eval("(" + employeeInfoListStr + ")");
   jQuery(function(){
	      jQuery('.focus_').focus(function(){
		     jQuery('.focus_input').show();
		  
		  });
	     
	    jQuery('.focus_').blur(function(){
		     jQuery('.focus_input').show();
		  
		  });
	     
	   
	   })
	  
	  jQuery(function(){
	     
	     jQuery('.focus_input p').click(function(){
		    jQuery('.hand_search').hide();
		    jQuery('.hand-detail').show();
		  });
    })
</script>	
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/keepAccounts/manuallyOpenOrder.js"></script>
</body>
</html>