<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Date" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath %>css/batch_set.css" type="text/css" />
<style>
.batch_set_5_job_ul{height:auto!important}
.get_way{text-align:center;padding-bottom:5px;padding-top:5px;border-top:1px solid #d0d2d9}
.get_way select{padding: 0 0 0 20px;width: 60px;height: 18px;border: 1px solid #939393;border-radius: 12px;margin:0 20px 0 5px!important}
.get_way input{padding-right: 20px;width: 54px;height: 10px;border-radius: 12px;border: 1px solid #939393;margin:0 10px 0 5px}
.get_way em{position:relative;left:-30px;color:#d8d3d3}
</style>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right clearfix'>
			    <div class="batch_set_content clearfix">
				   <div class="batch_set_1">
				      <p><select onchange="changeCategory(this)">
				            <c:forEach items="${projectCategoryDtoList}" var="projectCategoryDto">
				                <option value="${projectCategoryDto.categoryId }">${projectCategoryDto.categoryName }</option>
				            </c:forEach>
				         </select>
				      </p>
				      <div class="batch_set_1_content" name = "batchOne">
				        <c:forEach items="${projectCategoryDtoList}" var="projectCategoryDto" varStatus="size">
				            <c:if test="${size.index == 0}">
				                <c:forEach items="${projectCategoryDto.projectInfo}" var="projectInfo">
				                    <div class="batch_set_1_content_datail" onclick = "chooseProject(this, ${projectInfo.projectId }, '${projectInfo.projectName }',${projectInfo.projectPrice }, ${projectInfo.costPrice })">
									   <div class="item_name">
									     <p>${projectInfo.projectName }</p>
										<span>¥${projectInfo.projectPrice }</span><em>成本¥${projectInfo.costPrice }</em>
									   </div>
									</div>
					            </c:forEach>
				            </c:if>
			            </c:forEach>
					  </div>
				   </div>
				    <div class="batch_set_2">
				      <p>已选择项目</p>
				      <div class="batch_set_1_content" name = "batchTwo">

					  </div>
				   </div>
				   <div class="batch_set_3">
				      <p>业绩设置</p>
				      <div class="batch_set_3_content">
					     <ul class="job" style="height: 115px;">
					        <c:forEach items="${positionInfos}" var="positionInfo">
					            <c:if test="${positionInfo.isShow == 0}">
					                <li onclick = "choosePosition(this, ${positionInfo.positionId}, '${positionInfo.positionName }')">${positionInfo.positionName }</li>
					            </c:if>
					        </c:forEach>
						 </ul>
						 
						 <div class="selected_job">
						   <p>已选择职位</p>
						   <div class="selected_job_content" >

						   </div>
			              <div class="batch_set_button">
						    <button onclick = "batchSetCalculate()">批量设置业绩</button>
						  </div>   
						 </div>		  
					  </div>
				   </div>
				   
				   <div>
				       <p style="    height: 40px;
    line-height: 40px;
    text-align: center;
    border-bottom: 1px solid #909090;
    font-size: 14px;
    background: #ebeff8;">提成设置</p>
				       <div class="batch_set_4">
					     <!-- <p style="background:#f4f7fc">提成方式
					         <select name = "commissionWay">
					         	<option value="2">固定</option>
					         	<option value="1">比例</option>
					         </select>
					      </p> -->
					      <div class="batch_set_4_">
					        <c:forEach items="${positionInfos}" var="positionInfo">
					            <c:if test="${positionInfo.isShow == 0}">
					               <div class="batch_set_4_content">
									   <p><input type="checkbox" name = "positionCheck" positionId = "${positionInfo.positionId }" checkType = "0"><span>${positionInfo.positionName }</span><img src="<%=basePath %>images/open_card_img.png"></p>
									   <ul>
									     <c:forEach items="${positionInfo.employeeLevel}" var="employeeLevel">
									        <li><input type="checkbox" name = "levelCheck" levelId = "${employeeLevel.levelId }" levelName = "${employeeLevel.levelName }">${employeeLevel.levelName }</li>
									     </c:forEach>
									   </ul>
								   </div>
					            </c:if>
					        </c:forEach>   
						 </div>
						  <div class="batch_set_button">
							    <button onclick = "confirmLevelChoose()">确认职位选择</button>
						  </div>
					   </div>
					   
					   <div class="batch_set_5">
					     <div class="batch_set_5_content">
						   	 
						 </div>
					    <div class="batch_set_button">
						   <button style="width:126px" onclick="batchSetCommission()">批量设置提成</button>
						</div>
					   
					   </div>
				   </div>
			  </div>
			  </div> 
		</div>
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<script type="text/javascript" src="<%=basePath %>js/batchSet/projectBatchSet.js"></script>
<script>
var projectCategoryDtoListStr = '${projectCategoryDtoListStr}';
var projectCategoryDtoList = eval("(" + projectCategoryDtoListStr + ")");

var positionInfosStr = '${projectCategoryDtoListStr}';
var positionInfos = eval("(" + positionInfosStr + ")");
jQuery(function(){ 
    jQuery('.batch_set_4_content>p').click(function(e){
	  var target=e.target;
	  if(jQuery(target).is('.batch_set_4_content>p,.batch_set_4_content span,.batch_set_4_content img')){
	   var ul=jQuery(this).parent().find('ul');
	    if(ul.css('display')=='none'){
	     ul.stop(true,true).fadeIn();
		 jQuery(this).find('img').addClass('active')
	    }
	   else{
	     ul.stop(true,true).fadeOut();
		 jQuery(this).find('img').removeClass('active')
	   }
	  };
	})
 })
</script>
</body>
</html>