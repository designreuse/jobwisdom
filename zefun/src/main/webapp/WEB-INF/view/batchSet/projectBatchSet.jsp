<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Date" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath %>css/batch_set.css" type="text/css" />

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
				                    <div class="batch_set_1_content_datail">
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
				      <div class="batch_set_1_content">
					    <div class="batch_set_1_content_datail">
						   <div class="item_name">
						     <img src="assets/images/setting_close.png">
						     <p>项目名称项目名称</p>
							<span>¥300</span><em>成本¥500</em>
						   </div>
						</div>
					  </div>
				   </div>
				   <div class="batch_set_3">
				      <p>业绩设置</p>
				      <div class="batch_set_3_content">
					     <ul class="job">
						    <li>设计师</li>
						    <li>技师</li>
							<li>助理</li>
						 </ul>
						 
						 <div class="selected_job">
						   <p>已选择职位</p>
						   <div class="selected_job_content">
						      <div class="selected_job_content_detail">
							     <img src="<%=basePath %>images/setting_close.png">
							     <p>设计师</p>
							     <div class="selected_way">
								   <p><span>业绩方式</span><select><option>固定</option></select></p>
								   <p><span>业绩值</span><input type="text"><em>%</em></p>
								 </div>
							  </div>
						      <div class="selected_job_content_detail">
							     <img src="assets/images/setting_close.png">
							     <p>设计师</p>
							     <div class="selected_way">
								   <p><span>业绩方式</span><select><option>固定</option></select></p>
								   <p><span>业绩值</span><input type="text"><em>%</em></p>
								 </div>
							  </div>
						   </div>
			              <div class="batch_set_button">
						    <button>确定</button>
						  </div>   
						 </div>		  
					  </div>
				   </div>
				   
				   <div class="batch_set_4">
				     <p>提成设置</p>
				     <p style="background:#f4f7fc">提成方式<select><option>固定</option></select></p>
				      <div class="batch_set_4_">   
						<div class="batch_set_4_content">
						   <p><input type="checkbox"><span>设计师</span><img src="assets/images/open_card_img.png"></p>
						   <ul>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
						   </ul>
						   
						 </div>
						 <div class="batch_set_4_content">
						   <p><input type="checkbox"><span>技师</span><img src="assets/images/open_card_img.png"></p>
						   <ul>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 
						   </ul> 
						 </div>
						 <div class="batch_set_4_content">
						   <p><input type="checkbox"><span>助理</span><img src="assets/images/open_card_img.png"></p>
						   <ul>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
						   </ul> 
						 </div>
						 <div class="batch_set_4_content">
						   <p><input type="checkbox"><span>助理</span><img src="assets/images/open_card_img.png"></p>
						   <ul>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
						   </ul> 
						 </div>
						 <div class="batch_set_4_content">
						   <p><input type="checkbox"><span>助理</span><img src="assets/images/open_card_img.png"></p>
						   <ul>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
							 <li><input type="checkbox">烫染师</li>
						   </ul> 
						 </div>
					 </div>
					  <div class="batch_set_button">
						    <button>确认选择</button>
					  </div>
				   </div>
				   <div class="batch_set_5">
				     <p>批量设置</p>
				     <div class="batch_set_5_content">
					   <div class="batch_set_5_job">
					      <img src="assets/images/setting_close.png">
					      <ul class="clearfix">
						    <li>职位</li>
							<li><span class="active1">烫发师</span></li>
							<li><span>烫发师</span></li>
							<li><span>烫发师</span></li>
							<li><span>烫发师</span></li>
							<li><span>烫发师</span></li>	
						  </ul>  
					      <div class="batch_set_5_job_ul">
					        <ul class="clearfix">
							  <li>
							    <p>现金</p>
							    <div class="batch_set_5_job_ul_li">
								  <p><input type="text"><em>%</em><span>指定</span></p>
								  <p><input type="text"><em>%</em><span>非指定</span></p>
								</div>
							  </li>
							   <li>
							    <p>现金</p>
							    <div class="batch_set_5_job_ul_li">
								  <p><input type="text"><em>%</em><span>指定</span></p>
								  <p><input type="text"><em>%</em><span>非指定</span></p>
								</div>
							  </li>
							    <li>
							    <p>现金</p>
							    <div class="batch_set_5_job_ul_li">
								  <p><input type="text"><em>%</em><span>指定</span></p>
								  <p><input type="text"><em>%</em><span>非指定</span></p>
								</div>
							  </li>
							
							</ul>
					      </div>
					   </div>	 
					 </div>
				    <div class="batch_set_button">
					   <button style="width:126px">确认批量设置</button>
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