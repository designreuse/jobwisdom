<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位设置</title>
<%@include file="/css.jsp" %>
</head>
<body>
  <%@include file="/head.jsp" %> 
  <div class="container">
    <%@include file="/sidebar.jsp" %>
    <div id="content" class="fl">
 		<div class="content-submenu">
		    <ul class="submunu clearfix">
		        <li >
		            <a href="index.html">
		                <span>散客收银</span>
		            </a>
		        </li>
		        <li>
		            <a href="vip.html">
		                <span>会员消费</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>新增会员</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>自助账单</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>账单查询</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>顾客预约</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>动态轮牌</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>收支记账</span>
		            </a>
		
		        </li>
		        <li class="active">
		            <a href="project_setting.html">
		                <span>项目设置</span>
		            </a>
		        </li>
		    </ul>
		</div>
	    <div class="box live-tab setting-content">
		    <ul class="tab_menu">
		        <li id="staffPage">岗位设置</li>
		        <li class="current" >员工设置</li>
		    </ul>
		    <div class="tab_box">
               <!--增加员工-->
               <div class="add-staff">
		            <form class="" action="<%=cssBasePath %>StaffEmployeeSetting/action/saveEmployee" method="post" id="saveForm">
		              <div class="title">员工详细信息</div>
		              <!--头像-->
		              <div class="avatar">
		                  <input type="hidden" name="employeeId" value="${employeeId}">
		                  <input type="text" placeholder="员工编号" class="code mgb10" name="employeeNo" value="${employeeNo}"/>
		                  <c:choose>
		                  	<c:when test="${headImg == null || headImg == ''}">
		                  		<img alt="" src="<%=cssBasePath %>app/img/employee.jpg" name="headImg" class="mgb10" id="pickHeadImg">
		                  	</c:when>
		                  	<c:otherwise>
		                  		<img alt="" src="${headImg}" name="headImg" class="mgb10" id="pickHeadImg">
		                  	</c:otherwise>
		                  </c:choose>
		                  <input type="hidden" name="headImg" value="${headImg}">
		                  <input type="button" value="身份证" class="btn btn-blue added mgb10" data-toggle="modal" data-target="#model-identity" id="cardModal"/>
		                  <input type="hidden" name="cardImg" value="${cardImg}">
		                  <input type="button" value="健康证" class="to-add mgb10" data-toggle="modal" data-target="#model-identity" id="healthModal"/>
		                  <input type="hidden" name="healthProveImg" value="${healthProveImg}">
		                  <input type="button" value="其它证件" class="to-add" data-toggle="modal" data-target="#model-identity" id="otherImgModal"/>
		                  <input type="hidden" name="otherImg" value="${otherImg}">
		              </div>
		              <!--表单-->
		              <div class="form-staff">
		                      <!--左边表单-->
		                      <div class="form-staff-left">
		                          <!--所属店铺-->
		                          <div class="f-group">
		                              <div class="f-whole">
		                                  <label class="f-label" for="">所属店铺</label>
		                                  <div class="btn-group dropdown" supdiv = "supdiv">
				                                   <input type="hidden" name= "storesId" value="${storesId}">
												   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "storesName" supname = "supname" style="width: 280px;height: 30px;">${storesCode}_${storesName}</button>
												   <ul class="dropdown-menu dropdownUL" style="width: 280px;">
		                                                 <c:forEach var="stores" items="${stores}" varStatus="status">
		                                                   <c:if test="${status.index != 0}">
		                                                     <li class="divider"></li>
		                                                   </c:if>
		                                                   <li><a href="javascript://" supa = "supa" heddenValue = "${stores.storesId}">${stores.storesCode}_${stores.storesName}</a></li>
		                                                 </c:forEach>											   
												   </ul>
											</div>
		                              </div>
		                          </div>
		                          <!--岗位职位-->
		                          <div class="f-group">
		                              <div class="f-whole" id="postMessageDIV">
		                                  <label class="f-label" for="">岗位</label>
		                                  <div class="btn-group dropdown" supdiv = "supdiv">
				                                   <input type="hidden" name= "postMessageId" value="${postMessageId}">
												   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "postMessageName" supname = "supname" style="width: 280px;height: 30px;">${postMessageName}</button>
												   <ul class="dropdown-menu dropdownUL" style="width: 280px;">
		                                                 <c:forEach var="postMessage" items="${postMessage}" varStatus="status">
		                                                   <c:if test="${status.index != 0}">
		                                                     <li class="divider"></li>
		                                                   </c:if>
		                                                   <li><a href="javascript://" supa = "supa" heddenValue = "${postMessage.postMessageId}">${postMessage.postMessageName}</a></li>
		                                                 </c:forEach>											   
												   </ul>
										 </div>
		                              </div>
		                           </div>
		                           <div class="f-group">
		                              <div class="f-whole">
		                                  <label class="f-label" for="">职位</label>
		                                  <div class="btn-group dropdown" supdiv = "supdiv">
				                                   <input type="hidden" name= "positionId" value="${positionId}">
												   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "positionName" supname = "supname" style="width: 280px;height: 30px;">${positionName}</button>
												   <ul class="dropdown-menu dropdownUL" style="width: 280px;">
		                                                 <c:forEach var="position" items="${position}" varStatus="status">
		                                                   <c:if test="${status.index != 0}">
		                                                     <li class="divider"></li>
		                                                   </c:if>
		                                                   <li><a href="javascript://" supa = "supa" heddenValue = "${position.positionId}">${position.positionName}</a></li>
		                                                 </c:forEach>											   
												   </ul>
										 </div>
		                              </div>
		                          </div>
		                          <!--姓名性别-->
		                          <div class="f-group two-part">
		                              <div class="f-left">
		                                  <label class="f-label" for="">姓名</label>
		                                  <input type="text" class="f-part-input" name="employeeName" value="${employeeName}"/>
		                              </div>
		                              <div class="f-right">
		                                  <label class="f-label" for="">性别</label>
		                                  <div class="btn-group dropdown" supdiv = "supdiv">
												   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "sex" supname = "supname" style="width: 109px;height: 28px;margin-left: 1px;">${sex}</button>
												   <input type="hidden" name="sex" value="${sex}">
												   <ul class="dropdown-menu dropdownUL" style="width: 109px;">
		                                              <li><a href="javascript://" supa = "supa" heddenValue="男">男</a></li>	
		                                              <li class="divider"></li>			
		                                              <li><a href="javascript://" supa = "supa" heddenValue="女">女</a></li>							   
												   </ul>
										 </div>
		                              </div>
		                          </div>
		                          <!--生日学历-->
		                          <div class="f-group two-part">
		                              <div class="f-left">
		                                  <label class="f-label control-label" for="birthday" style="padding-top: 6px;padding-left: 0px;">生日</label>
							                <div class="input-group date" id="birthday">
							                    <input class="f-whole-input"  name="birthday" type="text" value="${birthday}" style="width: 109px;height: 28px;background-color: white;margin-left: 4px;" readonly>
							                    <span class="input-group-addon" style="display:none;"><span class="glyphicon glyphicon-th"></span></span>
							                </div>
								      </div>
								      <div class="f-right">
								        <label class="f-label" for="">学历</label>
		                                  <div class="btn-group dropdown button-dropdown" supdiv = "supdiv">
		                                           <input type="hidden" name="degree" value="${degreeName}">
												   <button type="button" class="dropDownIcon" data-toggle="dropdown" supname = "supname" style="width: 109px;height: 28px;margin-left: 1px;">${degreeName}</button>
												   <ul class="dropdown-menu dropdownUL" style="width: 109px;">
		                                                 <c:forEach var="degreeList" items="${degreeList}" varStatus="status">
		                                                   <c:if test="${status.index != 0}">
		                                                     <li class="divider"></li>
		                                                   </c:if>
		                                                   <li><a href="javascript://" supa = "supa" heddenValue="${degreeList.codeName}">${degreeList.codeName}</a></li>
		                                                 </c:forEach>											   
												   </ul>
										  </div>
									 </div>
		                          </div>
		                          <!--手机号码-->
		                          <div class="f-group">
		                              <div class="f-whole">
		                                  <label class="f-label" for="">手机号码</label>
		                                  <input type="text" class="f-whole-input" name="mobile" value="${mobile}"/>
		                              </div>
		                          </div>
		                          <!--身份证-->
		                          <div class="f-group">
		                              <div class="f-whole">
		                                  <label class="f-label" for="">身份证</label>
		                                  <input type="text" class="f-whole-input" name="card" value="${card}"/>
		                              </div>
		                          </div>
		                          <!--身高体重-->
		                          <div class="f-group two-part">
		                              <div class="f-left ">
		                                  <label class="f-label" for="">身高</label>
		                                  <input type="text" class="f-part-input" name="height" value="${height}"/>
		                              </div>
		                              <div class="f-right">
		                                  <label class="f-label" for="">体重</label>
		                                  <input type="text" class="f-part-input" name="weight" value="${weight}"/>
		                              </div>
		                          </div>
		                          <!--健康证-->
		                          <div class="f-group">
		                              <div class="f-whole">
		                                  <label class="f-label" for="">健康证</label>
		                                  <input type="text" class="f-whole-input" name="healthProve" value="${healthProve}"/>
		                              </div>
		                          </div>
		                          <!--健康状况-->
		                          <div class="f-group">
		                              <div class="f-whole">
		                                  <label class="f-label" for="">健康状况</label>
		                                  <div class="btn-group dropup" supdiv = "supdiv">
		                                           <input type="hidden" name="healthState" value="${healthName}">
												   <button type="button" class="dropDownIcon" data-toggle="dropdown" supname = "supname" style="width: 280px;height: 28px;">${healthName}</button>
												   <ul class="dropdown-menu dropdownUL" style="width: 280px;">
		                                                 <c:forEach var="healthList" items="${healthList}" varStatus="status">
		                                                   <c:if test="${status.index != 0}">
		                                                     <li class="divider"></li>
		                                                   </c:if>
		                                                   <li><a href="javascript://" supa = "supa" heddenValue="${healthList.codeName}">${healthList.codeName}</a></li>
		                                                 </c:forEach>											   
												   </ul>
										 </div>
		                              </div>
		                          </div>
		                      </div>
		
		                      <!--右边菜单-->
		                      <div class="form-staff-left" style="margin-left: 58px;">
		                          <!--银行名称-->
		                          <div class="f-group">
		                              <div class="f-whole">
		                                   <label class="f-label" for="">银行名称</label>
		                                   <div class="btn-group dropdown" supdiv = "supdiv">
		                                       <input type="hidden" name="bankname" value="${bankName}">
			                                   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "bankname" supname = "supname" style="width: 280px;height: 30px;">${bankName}</button>
											   <ul class="dropdown-menu dropdownUL" style="width: 280px;">
			                                               <c:forEach var="bankList" items="${bankList}" varStatus="status">
			                                                 <c:if test="${status.index != 0}">
			                                                   <li class="divider"></li>
			                                                 </c:if>
			                                                 <li><a href="javascript://" supa = "supa" heddenValue="${bankList.codeName}">${bankList.codeName}</a></li>
			                                               </c:forEach>											   
											   </ul>
		                                  </div>
		                          </div>
		                       </div>
		                          <!--银行卡号-->
		                          <div class="f-group">
		                              <div class="f-whole">
		                                  <label class="f-label" for="">银行卡号</label>
		                                  <input type="text" class="f-whole-input" name="bankCard" value="${bankCard}"/>
		                              </div>
		                          </div>
		                          <!--联系人电话-->
		                          <div class="f-group two-part">
		                              <div class="f-left">
		                                  <label class="f-label" for="">联系人</label>
		                                  <input type="text" class="f-part-input" name="contacts" value="${contacts}"/>
		                              </div>
		                              <div class="f-right">
		                                  <label class="f-label" for="">电话</label>
		                                  <input type="text" class="f-part-input" name="contactsMobile" value="${contactsMobile}"/>
		                              </div>
		                          </div>
		                          <!--主推介人推介人-->
		                          <div class="f-group two-part ">
		                              <div class="f-left ">
		                                  <label class="f-label" for="">主推介人</label>
		                                  <div class="dropdown" supdiv = "supdiv">
				                                        <a data-toggle="dropdown" class="dropdown-toggle" id="bossSponsorDropdown" supname = "supname">
				                                           <input type="text" name="bossSponsorName"  dropdowninput = "dropdowninput" class="border-left-none" style="width: 109px;color: black;" value="${bossSponsorName}">
				                                           <input type="hidden" name="bossSponsor" value="${bossSponsor}">
				                                        </a>
				                                        <ul class='dropdown-menu dropdownUL' style="width: 120px;"></ul>
								          </div>
		                              </div>
		                              <div class="f-right">
		                                  <label class="f-label" for="">推介人</label>
		                                  <div class="dropdown" supdiv = "supdiv">
				                                        <a data-toggle="dropdown" class="dropdown-toggle" id="sponsorDropdown" supname = "supname">
				                                           <input type="text" name="sponsorName"  dropdowninput = "dropdowninput" class="border-left-none" style="width: 109px;color: black;" value="${sponsorName}">
				                                           <input type="hidden" name="sponsor" style="display: none;" value="${sponsor}">
				                                        </a>
				                                        <ul class='dropdown-menu dropdownUL' style="width: 120px;"></ul>
								          </div>
		                              </div>
		                          </div>
		                          <!--入职状态时间-->
		                          <div class="f-group two-part">
		                              <div class="f-left" id="workStateDIV">
		                                  <label class="f-label" for="">入职状态</label>
		                                  <div class="btn-group dropdown" supdiv = "supdiv">
											   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "workState" supname = "supname" style="width: 109px;height: 28px;margin-left: 4px;">${workState}</button>
											   <input type="hidden" name="workState" value="${workState}">
											   <ul class="dropdown-menu dropdownUL" style="width: 109px;">
	                                              <li><a href="javascript://" supa = "supa" heddenValue="未入职">未入职</a></li>	
	                                              <li class="divider"></li>			
	                                              <li><a href="javascript://" supa = "supa" heddenValue="已入职">已入职</a></li>							   
											   </ul>
										 </div>
		                              </div>
									  <div class="f-right">
		                                  <label class="f-label control-label" for="workTime" style="padding-top: 6px;">时间</label>
							                <div class="input-group date" id="workTime">
							                    <input class="f-whole-input" name="workTime" value="${workTime}" size="16" type="text" style="width:109px;background-color: white;margin-left: 4px;" disabled="disabled" readonly>
							                    <span class="input-group-addon" style="display:none;"><span class="glyphicon glyphicon-th"></span></span>
							                </div>
		                              </div>
		                          </div>
		                          <!--账号密码-->
		                          <div class="f-group two-part " id="userDIV">
		                              <div class="f-left">
		                                  <label class="f-label" for="">账号</label>
		                                  <input type="text" class="f-part-input" name="userInfoName" />
		                              </div>
		                              <div class="f-right">
		                                  <label class="f-label" for="">密码</label>
		                                  <input type="text" class="f-part-input" name="userPassword" />
		                              </div>
		                          </div>
		                          <!--权限设置-->
		                          <div class="f-group" id="roleDIV">
		                              <div class="f-whole">
		                                  <label class="f-label" for="">权限设置</label>
		                                  <div class="btn-group dropdown" supdiv = "supdiv">
		                                       <input type="hidden" name="roleId" value="${roleId}">
			                                   <button type="button" class="dropDownIcon" data-toggle="dropdown" supname = "supname" style="width: 280px;height: 30px;">${roleName}</button>
											   <ul class="dropdown-menu dropdownUL" style="width: 280px;">
			                                               <c:forEach var="roleList" items="${roleList}" varStatus="status">
			                                                 <c:if test="${status.index != 0}">
			                                                   <li class="divider"></li>
			                                                 </c:if>
			                                                 <li><a href="javascript://" supa = "supa" heddenValue="${roleList.roleName}">${roleList.roleName}</a></li>
			                                               </c:forEach>											   
											   </ul>
		                                  </div>
		                              </div>
		                          </div>
		                          <input type="button" class="form-button return" value="返回" id="cancelEmployeeId"/>
		                          <input type="button" class="form-button confirm" value="保存" id="saveEmployeeId"/>  
		                          
		                   </div>
		                   </form>
		              </div>
		       
            </div>
	     </div>
       </div>
	</div>
  </div>
	  <!--
	身份证模态框-->
	<!-- Modal -->
	<div class="modal fade" id="model-identity" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content identity">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h3 class="modal-title" id="modal-title" typeName=""></h3>
	            </div>
	            <div class="modal-body">
	                <div class="display-frame" id="container">
	                </div>
	                <div class="selected-show" style="">
	                    <ul id="showMode">
	                    </ul>
	                </div>
	
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="select-file" id="pickfiles" style="text-align: center;">选择文件</button>
	                <button type="button" class="btn btn-default" data-dismiss="modal" id="cancelImg">取消</button>
	                <button type="button" class="btn btn-primary" data-dismiss="modal" id ="sureImg">确定</button>
	            </div>
	        </div>
	    </div>
	</div>

  <%@ include file="/js.jsp"%>
  <script type="text/javascript">
	var baseUrl = "<%=cssBasePath%>";
  	var employeeListStr = '${employeeList}';
  	var employeeList = eval("(" + employeeListStr + ")");
  </script>
  <script type="text/javascript" src="<%=cssBasePath %>js/staff_employee_setting/staff_employee_setting.js"></script>
</body>
</html>
