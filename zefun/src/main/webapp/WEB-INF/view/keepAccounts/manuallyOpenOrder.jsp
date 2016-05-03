<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>

<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      <div class="maincontent">
	    <div class="contentinner">
	
	        <div class="widgetcontent">
	            <div class="more-toolbar" style="background-color: #d9e9e9;position: relative;">
	                <div class="table-toolbar" style="font-size: 14px">
	                    <div class="p-part-first" name = "memberTR">
	                        <div style="display: inline-block;" name= "seekTD">
	                           <span class="ml10">会员:</span>
	                           <input type="text" class="w185 searchinpput" name = "phoneNumber" placeholder="会员手机号"/>
	                           <ul class="fuzzysearch" >

		                       </ul>
	                           <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
	                        </div>
	                        <div  name="resultTD" style="display: none;">
                                <!-- <span data-toggle="modal"  data-target="#member-data" class="can-click"></span>
                                <input type="hidden" name = "memberId" onchange="changeMember(this)">
                                <span class="ml10" name = "balance"></span>
                                <span class="iconsweets-magnifying ml10 mt-5" name="breakName"></span> -->
                                <span class="mr30">会员名：<span class="color-g cursor" name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></span></span>
			                    <span class="mr30">手机号：<span class="color-g cursor" name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></span></span>
			                    <span class="mr30">性别：<span class="color-g" name = "memberSexSpan"></span></span>
			                    <span class="mr30">账户余额：<span class="cred" name = "memberBalanceAmountSpan"></span></span>
			                    <span class="mr30">可用礼金：<span  class="cred" name = "memberBalanceGiftmoneyAmountSpan"></span></span>
			                    <span class="mr30">剩余积分：<span  class="cred" name = "memberBalanceIntegralSpan"></span></span>
			                    <input type="hidden" name = "memberId" onchange="changeMember(this)">
                            </div>
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
	                
	                <span class="btn fanhui hide">返回</span>
	                
	                <div class="clearfix"></div>
	            </div><!--more-toolbar-->
	            
	            <div class="more-toolbar hide" name = "moreMemberInfoDIV" style="border-top: 0;font-size: 14px;margin-bottom: 10px;" >
	                <div class="table-toolbar">
	                    <span class="mr30">消费总额：<span name = "totalConsumeAmountSpan"></span></span>
	                    <span class="mr30">上次来店：<span><span class="color-g" name = "lastDayNumberSpan"></span> 天前</span></span>
	                    <span>上次消费：<span name = "projectNameSpan"></span><span style="font-size: 12px" name = "projectStepSpan"></span></span>
	                </div>
	                <div class="clearfix"></div>
	            </div>
	            
	            <!--kd-select-content start-->
	            <div class="kd-select-content">
	
	                <div class="kai-bumen kd-bmlist">
	                    <ul class="flex-box trangle">
	                        <c:forEach items="${list}" var="dept" varStatus="status">
	                           <li <c:if test="${status.index == 0}">class="flex-item bm active"</c:if> <c:if test="${status.index != 0}">class="flex-item bm"</c:if> style="cursor:pointer;" onclick="changeDept(${dept.deptId})">
		                            <span>${dept.deptName}</span>
		                            <em></em>
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
		                                <li class="select-item active" style="cursor:pointer;" onclick="changeType(this, 'project')">项目</li>
		                                <li class="select-item" style="cursor:pointer;" onclick="changeType(this, 'combo')">套餐</li>
		                                <li class="select-item" style="cursor:pointer;" onclick="changeType(this, 'goods')">商品</li>
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
		                                <li class="selected-item active" style="cursor:pointer;">套餐</li>
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
		                    
		                    
		                    <!--选择具体的项目-->
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
	            <!--kd-select-content end-->
	        </div>
	        <!--widgetcontent end-->
	
	        <div class="widgetcontent">
	            <div class="more-toolbar" >
	                <div class="table-toolbar">
	                    <span class="font-size-16 btn-color">消费明细</span>
	
	                    <!-- <div class="fr">
	                        <span class="mr10">手工单号:</span>
	                        <input class="input-medium" type="text"/>
	                    </div> -->
	                </div>
	                <div class="clearfix"></div>
	            </div>
	
	            <div class="show-content">
	                <ul id = "showUL">
	                    <li class="xiaofei-item" name= "goodsNameLI">
	                        <div class="xiaofei-name">
	                            <span class="name mr20">商品销售</span>
	                        </div>
	
	                    </li>
	                    <li class="xiaofei-item" name= "comboNameLI">
	                        <div class="xiaofei-name">
	                            <span class="name mr20">套餐销售</span>
	                        </div>
	
	                    </li>
	                </ul>
	            </div>
	            <div class="btn fr w80 mr20 mt20" onclick="save(1)">开单</div>
	            <div class="btn fr w80 mr20 mt20" onclick="save(2)">补单</div>
	        </div>
	        <!--widgetcontent-->
	
	    </div>
	</div>
	
	<!--提示-->
	<div class="modal hide" id="saveModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content confirm">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="cancelModel();"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">填写补单时间</h4>
	            </div>
	
	            <div class="modal-body confirm-body">
	                 补单时间 ：<input name="openOrderDate" type="text" class="w150" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
	            </div>
	
	            <div class="modal-footer">
	                <a class="btn cancel-btn modal-cancel" onclick="cancelModel();">取消</a>
	                <a onclick="queren()" class="btn btn-primary save-btn modal-confirm" data-dismiss="modal" href="#">确定</a>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!--开单完成后跳转（会员）-->
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
	
	<%@ include file="/template/memberData.jsp" %>
	<script type="text/javascript">
	   var employeeInfoListStr = '${employeeInfoList}';
	   var employeeInfoList = eval("(" + employeeInfoListStr + ")");
	</script>	
	<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/keepAccounts/manuallyOpenOrder.js"></script>
   </div>
  </div>
</div>
</body>
</html>