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
			<div class="more-toolbar" >
	                <div class="table-toolbar" style="font-size: 14px" name = "memberTR" selectType = "1">
	                    <div class="p-part-first">
	                        <div style="display: inline-block;" name= "seekTD">
	                           <span class="ml10">会员:</span>
	                           <input type="text" class="w185 searchinpput" name = "phoneNumber" placeholder="会员手机号"/>
	                           <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
	                           
	                           <div class="show_search" name = "memberListDIV" style="display: none;">
								    <p>以<i name = "conditionValue">12</i>为条件显示到<i name ="showList">20</i>位顾客 <em><input type="checkbox" onchange="changeAllEnterprise(this)">全店搜索<span>?</span></em><div class="common_close"><img src="<%=basePath %>images/emploee_3.png"></div></p>
								    <div style="height: 400px; overflow: overlay;" name = "memberoverDIV">
								    
								    </div>  
							   </div>
	                        </div>
	                        <!-- <div  name="resultTD" style="display: none;">
                                <span class="mr30">会员名：<span class="color-g cursor" name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></span></span>
			                    <span class="mr30">手机号：<span class="color-g cursor" name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></span></span>
			                    <span class="mr30">性别：<span class="color-g" name = "memberSexSpan"></span></span>
			                    <span class="mr30">账户余额：<span class="cred" name = "memberBalanceAmountSpan"></span></span>
			                    <span class="mr30">可用礼金：<span  class="cred" name = "memberBalanceGiftmoneyAmountSpan"></span></span>
			                    <span class="mr30">剩余积分：<span  class="cred" name = "memberBalanceIntegralSpan"></span></span>
			                    <input type="hidden" name = "memberId" onchange="changeMember(this)">
                            </div> -->
	                    </div>
	                    <div class="sex-select ml30" name='sexDIV'>
	                        <span class="ml10">散客:</span>
	                        <label class="radio ml30"  for="">
	                            <input type="radio" name = "sex" value="男" checked/> <span class="ml5">男</span>
	                        </label>
	                        <label class="radio ml30" for="">
	                            <input type="radio" name = "sex" value="女"/> <span class="ml5">女</span>
	                        </label>
	                    </div>
	                </div>
	                
	                <div class="card-main1 clearfix hide" name = "memberTR">
	                        <div class="common_table">   
						  	   <table>
							     <tr>
								   <td rowspan="2"><img src="" name = "memberImg"></td>
								   <td>手机号</td>
								   <td>姓名</td>
								   <td>性别</td>
								   <td>开卡门店</td>
								   <td>余额</td>
								   <td>礼金</td>
								   <td>欠款</td>
								   <td>会员卡</td>
								   <td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
								 </tr>
								 <tr>
								   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberSexSpan">男</td>
								   <td name = "memberStoreName">华南美联店</td>
								   <td style="color:#eb4749" name = "memberBalanceGiftmoneyAmountSpan"></td>
								   <td style="color:#eb4749" name = "memberBalanceIntegralSpan"></td>
								   <td style="color:#eb4749" name = "needRefund"></td>
								   <td ><span name = "subAccountNum"></span>张</td>
								 </tr>
								 <input type="hidden" name = "memberId">
							   </table>
						    </div>
	                    </div>
	            </div><!--more-toolbar-->
	            
	            <!-- <div class="more-toolbar hide" name = "moreMemberInfoDIV" style="border-top: 0;font-size: 14px;margin-bottom: 10px;" >
	                <div class="table-toolbar">
	                    <span class="mr30">消费总额：<span name = "totalConsumeAmountSpan"></span></span>
	                    <span class="mr30">上次来店：<span><span class="color-g" name = "lastDayNumberSpan"></span> 天前</span></span>
	                    <span>上次消费：<span name = "projectNameSpan"></span><span style="font-size: 12px" name = "projectStepSpan"></span></span>
	                </div>
	                <div class="clearfix"></div>
	            </div> -->
				<div class="hand_detail_content clearfix">
				   <div class="hand_detail_left">
				      <div class="select_part">
					     <select class="select_part_" onchange="changeDept(this)">
						   <c:forEach items="${list}" var="dept" varStatus="status">
		                        <option value = "${dept.deptId}">${dept.deptName}</option>
	                       </c:forEach>
			             <select>
			          </div>
			          <c:forEach items="${list}" var="dept" varStatus="status">
			              <div <c:if test="${status.index != 0}">class="hide"</c:if> chooseDept = "chooseDept" name= "${dept.deptId}">
			          		  <ul class="hand_project clearfix">
							    <li class="active" onclick="changeType(this, 'project')">项目</li>
								<li onclick="changeType(this, 'combo')">疗程</li>
								<li onclick="changeType(this, 'goods')">商品</li>
							  </ul>
							 <div class="hair_series_"> 
								  <span class="hand_left"><img src="<%=basePath%>images/hand_left.png"></span>
								  
								  <div class="hair_series" name = "projectUL">
								     <ul class="clearfix" >
										 <c:forEach items="${dept.projectCategoryList}" var="projectCategory" varStatus="projectStatus">
		                                    <li <c:if test="${projectStatus.index == 0}">class="active"</c:if> categoryid = "${projectCategory.categoryId}" onclick="changeCategory(this, ${projectCategory.categoryId}, 'project')">${projectCategory.categoryName}</li>
		                                 </c:forEach>
									 </ul>	
								 </div>
								 
								 <div class="hair_series hide" name = "comboUL">
								     <ul class="clearfix" >
										 <li class="active" style="cursor:pointer;">疗程</li>
									 </ul>	
								 </div>
								 
								 <div class="hair_series hide" name = "goodsUL">
								     <ul class="clearfix" >
										 <c:forEach items="${dept.goodsCategoryList}" var="goodsCategory" varStatus="goodsStatus">
		                                    <li <c:if test="${goodsStatus.index == 0}">class="active"</c:if> categoryid = "${goodsCategory.categoryId}" onclick="changeCategory(this, ${goodsCategory.categoryId}, 'goods')">${goodsCategory.categoryName}</li>
		                                </c:forEach>
									 </ul>	
								 </div>
								 
								 <span class="hand_right"><img src="<%=basePath%>images/hand_right.png"></span>
								 
								 <c:forEach items="${dept.projectCategoryList}" var="projectCategory" varStatus="projectStatus">
								     <div <c:if test="${projectStatus.index == 0}">class="hair_content"</c:if> <c:if test="${projectStatus.index != 0}">class="hair_content hide"</c:if> name= "project" categoryid = "${projectCategory.categoryId}">
				                        <ul class="clearfix">
				                            <c:forEach items="${projectCategory.projectList}" var="project">
				                                <li onclick="chooceProject(${project.projectId}, '${project.projectName}', ${project.projectPrice}, 1)">
					                                <p>${project.projectName}</p>
									                <span>价格：${project.projectPrice}</span>
					                            </li>
				                            </c:forEach>
				                        </ul>
				                    </div>
								 </c:forEach>
								 
								 <div class="hair_content hide" name= "combo">
			                        <ul class="clearfix">
			                            <c:forEach items="${dept.comboInfoList}" var="comboInfo">
			                                <li onclick="chooceProject(${comboInfo.comboId}, '${comboInfo.comboName}', ${comboInfo.comboSalePrice}, 3)">
				                                <p>${comboInfo.comboName}</p>
									            <span>价格：${comboInfo.comboSalePrice}</span>
				                            </li>
			                            </c:forEach>
			                        </ul>
			                    </div>
			                    
			                    <c:forEach items="${dept.goodsCategoryList}" var="goodsCategory" varStatus="goodsStatus">
			                        <div class="hair_content hide" name= "goods" categoryid = "${goodsCategory.categoryId}">
				                        <ul class="clearfix">
				                            <c:forEach items="${goodsCategory.goodsBaseDtos}" var="goods">
				                                <li onclick="chooceProject(${goods.goodsId}, '${goods.goodsName}', ${goods.goodsPrice}, 2)">
					                                <p>${goods.goodsName}</p>
									                <span>价格：${goods.goodsPrice}</span>
					                            </li>
				                            </c:forEach>
				                        </ul>
				                    </div>
			                    </c:forEach>
							</div> 
			             </div>
			          </c:forEach>
				 </div>
					  
				<div class="hand_detail_right">
				           <ul class="nav clearfix">
				             <li class="active" onclick="changeDiv(1)" name = "projectDetail">项目</li>
							 <li onclick="changeDiv(3)" name = "comboDetail">疗程</li>
							 <li onclick="changeDiv(2)" name = "goodsDetail">商品</li>
				           </ul>
				     
				<div class="nav_right_content"> 
				  
					  <div class="nav_content" name = "projectPay">
				
			          </div>
					  
					  
					  
					   <div class="nav_content hide" name = "goodsNameLI">
						     
			           </div>
					  
					   <div class="nav_content hide" name = "comboNameLI">

			           </div>
					  
			      </div>		  
					  <div class="open_time">
					     <span>开单时间<input type="text" name="openOrderDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></span>
					    <span>手工单号<input type="text" name= "handOrderCode"></span>
						<button onclick="save()">立即开单</button>
					  </div>
				</div> 
			</div>
	        <%@ include file="/template/memberData.jsp" %>
        </div>
  </div>
</div>

<div class="modal hide" tabindex="-1" role="dialog" id="memberSkipModal">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content kaidan-tip">
	            <div class="modal-header">
	                <h4 class="modal-title">温馨提示</h4>
	            </div>
	            <div class="modal-body">
	                <p class="extendpd"><span class="red font-size-14">已成功开单</span> 请问还需要进行其它操作吗?</p>
	            </div><!--modal body-->
	            <div class="modal-footer">
	                <a class="btn btn-primary modal-confirm" href="#" onclick ="chooseSkipType(1)">继续开单</a>
	                <a class="btn btn-primary modal-confirm" href="#" name = "memberModelChoose" onclick="chooseSkipType(2)">会员充值</a>
	                <a class="btn btn-primary modal-confirm" href="#" name = "memberModelChoose" onclick="chooseSkipType(3)">会员升级</a>
	                <a class="btn btn-primary modal-confirm" href="#" onclick ="chooseSkipType(4)">立即买单</a>
	            </div>
	        </div>
	    </div>
	</div>

<script type="text/javascript">
   var employeeInfoListStr = '${employeeInfoList}';
   var employeeInfoList = eval("(" + employeeInfoListStr + ")");
</script>	
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/keepAccounts/manuallyOpenOrder.js"></script>
</body>
</html>