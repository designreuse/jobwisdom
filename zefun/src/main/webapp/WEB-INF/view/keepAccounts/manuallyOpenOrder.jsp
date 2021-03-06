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
.show_search table td:nth-child(1) img {
    width: 47px;
    border-radius: 25px;
    height: 47px;
}

.absolute td{width:130px!important}
.single_account{box-shadow:0 0 0 white;border:1px solid #7d7575 }
.active1{color:#d11e1e;border:1px solid #d11e1e}
.content_right{color:black;}
input{border:1px solid black}
select{border:1px solid black}
input[type="checkbox"] {
    -webkit-appearance: none;
    appearance: none;
    width: 12px;
    height: 12px;
    border: 1px solid black;
    position: relative;
    top: -3px;
    margin-right: 8px;
	}

input[type="checkbox"]:checked {
    background: url('<%=basePath%>images/checked.png') no-repeat;
    background-position: center center;
    border: 1px solid #d93717;
background-size: cover;}

.content_right input[type='checkbox']{position:relative;top:-1px}

.absolute{box-shadow:0 0 10px #908f8f;position:absolute;;background:white;z-index:10;box-shadow:0 0 10px #ccc;top:130px}
.absolute em{color:#d92020;display:inline-block;margin-right:2px}
.content_right input[type='text']{border:1px solid #7d7575}
.hand_table{border: 1px solid #7d7575;box-shadow:0 0 0 white}
.hand_table tr td{border: 1px solid #7d7575;}
.hand_detail_left{    border: 1px solid #7d7575;
    box-shadow: 0 0 0px white;}
.hair_series_{
	  border: 1px solid #7d7575;
	}
.hand_detail_right{
   border: 1px solid #7d7575;
}	
.hand_project li{font-weight:bold;font-size:14px}
.hand_project .active{border: 1px solid #7d7575;}
.nav li{font-weight:bold}
.select_part_{font-weight:bold}
.absolute{width:440px!important;margin-left:40px!important}
.nav_content td select{width:52px;height:14px}
.nav_content td{width:110px}
.nav_content_div{height:auto;padding-bottom:5px}

.nav_content_div li{float:left;padding:5px 42px 5px 10px}
.nav_content_div ul{border-bottom:1px solid #e0e2ea;}
.select_people{margin-left:0!important}
.select_people td{text-align:left!important;padding-left:10px}

.nav_content_div input[type='text']{width:65px;border:1px solid black;border-radius:12px;height:15px}
.absolute li{ float: left;width: 100px; padding: 2px 0 2px 10px;cursor:pointer} 

.nav_right_content input[type='text']{width:88px}
.absolute ul{border-bottom:1px dashed #e1dfdf;padding:5px 0}
.common_table table{width:998px!important;margin-left:30px}
.show_search{left:60px!important}

 .nav_content_div{border:1px solid #ccc}
 .select_order{padding:1px 5px;background:#5b6080;border-radius:12px;margin-left:20px;color:white;cursor:pointer}
 .project_hand{width:900px;height:400px;background:white;border-radius:12px;margin:50px auto;overflow:hidden}
 .project_hand>p{height:40px;line-height:40px;text-align:center;color:white;font-size:14px;background:#43495d}
 .project_hand_content li{padding:10px;width:330px;height:60px;border:1px solid #909090;margin:10px 30px;border-radius:12px;float:left}
 .project_hand_content .img{float:left;width:60px;height:60px;background:#ccc}
 .project_hand_content .text{float:left;color:black;margin-left:8px}
 .project_hand_content .text span{font-size:14px}
 .project_hand_content .text p{color:#909090}
 .project_hand_right{float:right;line-height:16px;color:black}
 .project_hand_right div{font-size:14px;}
 .project_hand_right span{color:#d92010}
 .project_hand_button{text-align:center}
 .project_hand_button button{margin:0 30px;width:130px;height:24px;line-height:24px;color:white;font-size:14px;text-align:center;border:none;border-radius:12px;background:#5c6f86}
 .project_hand_button button:hover{background:#54677d;}
 .zzc {
    position: fixed;
    top: 0px;
    height: 1090px;
    left: 0px;
    width: 100%;
    z-index: 10000;
    background: rgba(102, 108, 121, 0.8);
    display: none;
}
.appiontActive {
    background: #d8e0f2;
}
</style>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative;background:white;">
			<%@include file="/top.jsp"%>
			<div class="more-toolbar" >
	                <div class="table-toolbar" style="font-size: 14px;width:91%;padding:15px;border:1px solid #ccc;border-radius:12px;margin:10px 30px" name = "memberTR" selectType = "2">
	                    <div class="p-part-first">
	                        <div style="display: inline-block;" name= "seekTD">
	                           <span class="ml10">会员:</span>
	                           <input type="text" class="w185 searchinpput" name = "phoneNumber" placeholder="会员手机号/名称"/>
	                           <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
	                           
	                           <div class="show_search" name = "memberListDIV" style="display: none;">
								    <p>以<i name = "conditionValue">12</i>为条件显示到<i name ="showList">20</i>位顾客 <em><input type="checkbox" onchange="changeAllEnterprise(this)">全店搜索<span>?</span></em><div class="common_close"><img src="<%=basePath %>images/emploee_3.png"></div></p>
								    <div style="height: 400px; overflow: overlay;" name = "memberoverDIV">
								    
								    </div>  
							   </div>
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
	                
	                <div class="card-main1 clearfix hide" name = "memberTR" style="height: 70px;">
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
								   <td>积分</td>
								   <td>欠款</td>
								   <td>会员卡</td>
								   <td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
								 </tr>
								 <tr>
								   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
								   <td name = "memberSexSpan"></td>
								   <td name = "memberStoreName"></td>
								   <td style="color:#eb4749" name = "memberBalanceAmountSpan"></td>
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
				                                <li onclick="chooceProject(null,${project.projectId}, '${project.projectName}', ${project.projectPrice}, 1)">
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
			                                <li onclick="chooceProject(null,${comboInfo.comboId}, '${comboInfo.comboName}', ${comboInfo.comboSalePrice}, 3)">
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
				                                <li onclick="chooceProject(${goods.aId},${goods.goodsId}, '${goods.goodsName}', ${goods.goodsPrice}, 2)">
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
		           <div class="absolute">	 
		             <c:forEach items="${employeeInfoList}" var="employeeInfo">
		                 <ul class="clearfix" positionId = "${employeeInfo.positionId }">
		                    <li style="font-weight:bold">${employeeInfo.positionName }</li>
		                    <c:forEach items="${employeeInfo.employeeDtoList}" var="employeeDto" >
		                        <li employeeId = "${employeeDto.employeeId }" employeeCode = "${employeeDto.employeeCode }" employeeName = "${employeeDto.name }" name = "employeeId"><em>${employeeDto.employeeCode }</em>${employeeDto.name }</li>
		                    </c:forEach>
		                 </ul>
		             </c:forEach>				 
				   </div>
				     
				<div class="nav_right_content" orderId = "${selfCashierOrderDto.orderId}"> 
				  
					  <div class="nav_content" name = "projectPay">
				           <c:forEach items="${selfCashierOrderDto.orderDetails}" var="orderDetail">
				                <div class="nav_content_div" name= 'projectNameLI' projectId = "${orderDetail.projectId }" detailId = "${orderDetail.detailId }">
								   <span class="hand_close" onclick = 'deleteProject(this)'><img src='<%=basePath%>images/hand_close.png'></span>
								      <p>
								         <em>${orderDetail.projectName}</em>
								         <i>项目价格：${orderDetail.projectPrice}</i>
								         <c:choose>
								           <c:when test="${orderDetail.isAppoint == null}">
								               <span class='select_order' onclick = 'chooseAppoint(this, 1)' appointmentId = ''>选择预约人员+</span>
								           </c:when>
								           <c:otherwise>
								               <span class='select_order' onclick = 'chooseAppoint(this, 2)' appointmentId = '${orderDetail.isAppoint}'>预约人员:${appointEmployeeName }  时间:${appointTime }</span>
								           </c:otherwise>
								         </c:choose>
								      </p>
								      <table>
								         <c:forEach items="${orderDetail.stepList}" var="step" varStatus="status">
								            <tr positionId = '${step.positionId}' shiftMahjongStepId = "${step.shiftMahjongStepId}">
											   <td style="width:210px">${step.positionName }</td>
												 <td style="width:360px">选择员工
												      <c:if test="${status.index == 0 }">
												          <input type="text" name = 'employeeId' employeeId = '${step.employeeInfo.employeeId }' value="${step.employeeInfo.name }" chooseType = "1">
												      </c:if>
												      <c:if test="${status.index != 0 }">
												          <input type="text" name = 'employeeId' employeeId = '${step.employeeInfo.employeeId }' value="${step.employeeInfo.name }" chooseType = "2">
												      </c:if>
												 </td>
												 <td>指定<input type="checkbox" name = 'isAssign' <c:if test="${step.isAssign == 1}">checked</c:if>></td>
												 <c:if test="${status.index == 0 }">
												     <td>预约<input type="checkbox" name = 'isAppoint' <c:if test="${step.isAppoint == 1}">checked</c:if>></td>
												 </c:if>
											 </tr>
								         </c:forEach>
									  </table>
								 </div>
				           </c:forEach>
			          </div>
					  
					   <div class="nav_content hide" name = "goodsNameLI">
						     
			           </div>
					  
					   <div class="nav_content hide" name = "comboNameLI">

			           </div>
					  
			      </div>		  
				</div> 
				<div style="clear:both"></div>
				<div class="open_time">
			      <span>开单时间<input type="text" name="openOrderDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"></span>
			      <span>手工单号<input type="text" name= "handOrderCode"></span>
				  <button onclick="save(1)">结账</button>
				  <button onclick="save(2)">挂单</button>
			    </div>
			</div>
	        <%@ include file="/template/memberData.jsp" %>
        </div>
  </div>
</div>	
<div class="zzc">
  <div class="project_hand">
     <p>选择预约人员</p>
     <ul class="project_hand_content clearfix">
  
	 </ul>
	 
	 <div class="project_hand_button">
       <button onclick = "confirmAppion()">确定</button> 
	   <button onclick = "cancl()">取消</button> 
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
   var positionInfosStr = '${positionInfosStr}';
   var positionInfos = eval("(" + positionInfosStr + ")");
   var memberBaseDtoStr = '${memberBaseDto}';
   var chooseObj = "";
   
    jQuery('.absolute').hide();
    jQuery('.nav_content').delegate("input[type='text']", "focus",function(){
	   event = event ? event : window.event; 
	   var obj = event.srcElement ? event.srcElement : event.target;
       jQuery('.absolute').show();
       jQuery('.absolute').find("ul").show();
       if (jQuery(obj).attr("chooseType") == 1) {
    	   var positionid = jQuery(obj).parents("tr").attr("positionid");
    	   jQuery('.absolute').find("ul").hide();
    	   jQuery('.absolute').find("ul[positionid='"+positionid+"']").show();
       }
       chooseObj = obj;
       var top=jQuery(this).offset().top+30;
	    jQuery('.absolute').attr('style','position:absolute;top:'+top+'px')
	     fun();
    })
    function fun(){ 
	   jQuery(document).click(function(e){
	    var tar=e.target;
		if(!jQuery(tar).is('.absolute ul li,input[type="text"], .absolute ul')){
       jQuery('.absolute').hide();
		}
	  })
   }
   
   jQuery('.hand_detail_right').delegate("li[name='employeeId']", "click",function(){
	   event = event ? event : window.event; 
	   var obj = event.srcElement ? event.srcElement : event.target;
	   var employeeId = jQuery(obj).attr("employeeId");
	   if (isEmpty(employeeId)) {
		   obj = jQuery(obj).parents("li[name='employeeId']");
	   }
	   employeeId = jQuery(obj).attr("employeeId");
	   var employeeId = jQuery(obj).attr("employeeId");
	   var employeeCode = jQuery(obj).attr("employeeCode");
	   var name = jQuery(obj).attr("employeeName");
	   jQuery(chooseObj).attr("employeeId", employeeId);
	   jQuery(chooseObj).val(employeeCode + " " +name);
	   jQuery('.absolute').hide();
   })
</script>	
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/keepAccounts/manuallyOpenOrder.js"></script>
</body>
</html>