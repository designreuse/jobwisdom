<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
<link rel="stylesheet" href="<%=basePath %>css/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="<%=basePath %>css/payroll_2.css" type="text/css" />
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
<div class="leftpanel" style="height: 840px; margin-left: 0px;">
<%@ include file="/menu.jsp" %>
<%@ include file="/template/employeedetail.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.all.min.js"> </script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/wenben.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<head>
    <script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
    <script type="text/javascript">
    
    function editPage (imgUrl) {
    	xiuxiu.embedSWF("altContent2", 5, 700, 500);
    	
    	xiuxiu.onInit = function (id)
    	{
            xiuxiu.setUploadType(3);
            if (imgUrl != null) {
            	xiuxiu.loadPhoto(qiniuUrl + imgUrl, false);
            }
    	}
    	xiuxiu.onSaveBase64Image = function (data, fileName, fileType, id)
    	{
    		data = "data:image/"+fileType+";base64," + data;
            zccCallback(data);
    	}
    	
    	xiuxiu.onDebug = function (data)
    	{
            dialog("网咯繁忙，请重试！");
    	}
    	
    	xiuxiu.onClose = function (id)
    	{
            jQuery(".mask").hide();
    	}
    	
    }
	
    </script>
</head>
  
   
    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
    <%@ include file="/top.jsp" %>
    
<div class='content_right clearfix'>
    <div class="payroll_2_head">
	   <button id="downLondimport">导出模板下载</button><button id="downLond">导出</button><button data-toggle="modal" data-target="#toLeadModal">导入</button><button onclick="jQuery('.zzc').show();employeeId=null;">添加员工</button>
	   <span class="payroll_1_search">
	     <input type="search" id="search" placeholder="员工姓名/工号/手机号"/>
         <input type="hidden" id="querygangwei">
         <!-- <button class="button-search btn width100 ml-10" onclick="changePage()">查询</button> -->
         <button class="button-search btn width100 ml-10" onclick="findAllByNameOrCodeOrPhone()">查询</button>
	   </span>
	</div>
	
	<div class="payroll_2_content clearfix">
	   <!-- <div class="payroll_2_left">
	     <div class="content_wrap">
	         <div class="zTreeDemoBackground left">
		       <ul id="treeDemo" class="ztree"></ul>
         	</div>
         </div>
	 
	   </div> -->
	   <div class="payroll_2_right">
	     <table class="payroll_table">
		    <tr>
			  <td>工号</td>
			  <td>姓名</td>
			  <td>性别</td>
			  <td>部门</td>
			  <td>岗位</td>
			  <td>职位</td>
			  <td>员工账号</td>
			  <td>手机号</td>
			  <td>操作</td>
			 
	       </tr>
	       <c:forEach items="${employeeDtoList }" var="employee" varStatus="index">
	            <tr id="${employee.employeeId}">
				  <td>${employee.employeeCode }</td>
				  <td>${employee.name }</td>
				  <td>${employee.sex }</td>
				  <td>${employee.deptName }</td>
				  <td>${employee.positionName }</td>
				  <td>${employee.levelName }</td>
				  <td>${employee.userName }</td>
				  <td>${employee.phone }</td>
				  <td><span onclick="selectEmp(${employee.employeeId})"><img src="<%=basePath %>images/handle_1.png" ></span><span onclick="deleteinfo(${employee.employeeId})"><img src="<%=basePath %>images/handle_2.png"></span></td>
		        </tr>
	       </c:forEach>
	    </table>
	   </div>
	</div>
 </div>
 
<div class="zzc" style="display: none">
   <div class="emploee_information">
      <p>员工资料</p>
      <div class="emploee_content clearfix">  
        <div class="imformation_left"> 
		 <div class="clearfix">
		    <div class="head_img">
			  <img onclick="jQuery('.mask').show();editPage(null);" src="<%=basePath%>images/input_head.png">
			  <input type="hidden" name="headImage" value="">
			</div>
		    <div class="information">
			   <p> <span><em>姓名</em><input type="text" name="name"></span>
			 <span><em>工号</em><input type="text" value="" name="employeeCode"></span></p> 
			   <p><span><em>性别</em><select name="sex"><option value="男">男</option><option value="女">女</option></select></span> 
             <span><em>出生日期</em><input type="text" name="birthday" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span></p> 
               <p><span><em>介绍人</em><select name="recommendId"><c:forEach items="${recommendList }" var="recommend"><option value="${recommend.employeeId }">${recommend.name }</option></c:forEach></select></span>
             <span><em>手机号</em><input type="text" name="phone" value=""></span></p> 
               <p><span><em>身份证</em><input type="text" name="identityCard" value=""></span><span><em>角色</em><select name="roleId" >
                                          	<c:forEach items="${rolelist }" var="rolelist">
                                            <option value="${rolelist.roleId }">${rolelist.roleName }</option>
                                            </c:forEach>
                                     </select></span></p>
			</div>  
		 </div>
		  <div class="imformation_bottom">
		     <p>
			    <span><em>选择岗位</em><select onchange="changeEmployeeLevel(this.value)" name="positionId"><c:forEach items="${positionlist }" var="position"><option value="${position.positionId }">${position.positionName }</option></c:forEach></select></span>
			    <span><em>部门</em><select name="deptId"><c:forEach items="${deptlist }" var="dept"><option value="${dept.deptId }">${dept.deptName }</option></c:forEach></select></span>
				<span><em>职位</em><select name="levelId"></select></span>
			 </p>
		     <p>
			    <span><em>当前状态</em><select name="employeeStatus" >
                                            <option value="1">在职</option>
                                            <option value="2">离职</option>
                                        </select></span>
			    <span><em>到职日期</em><input type="text" value="" name="entryDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
				<span><em>离职日期</em><input type="text" value="" name="leaveDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
			 </p>
		  </div>	 
		 </div>
		 
	 
	  <div class="textarea clearfix">
	    <div class="introduction">
		  <h5>员工简介</h5>
		  <p>显示在会员端个人简介中</p>
		</div>
		<div class="textarea">
	      <textarea name="employeeDesc" value=""> </textarea>
	    </div>
	  </div>
	  <div class="imformation_button">
	     <button onclick="saveEmployee()">保存</button>
		 <button onclick="jQuery('.zzc').hide()">取消</button>
	  </div>
	 </div>
   </div>
</div>
<%-- <!--添加员工模态框-->
<div class="modal hide" id="employee-add-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content employee-data-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">员工资料</h5>
            </div>
            <div class="modal-body">
                <div id="tabs">
                    <ul>
                        <li><a href="#tabs-1">基本资料</a></li>
                    </ul>
                    <!-- 人员新增后返回这个人员的id -->
                    <input type="hidden" id="addemployeeid">
                    <div id="tabs-1">
                        <div class="base-info-img" id="container">
                            <img name='headImg' src="" alt="" id="headImage"/> 
                            <input type="hidden" name="hiddenheadImg"/>
                            <button class="btn select-btn" id="pickHeadImg" onclick="cavsenSave(this)">上传图片</button>
                            <p style="margin-top:20px;text-align:left;">个人简介</p>
                            <textarea id="employeeDescOfAdd" style="width:100px;height:158px;" placeholder="用于向顾客展示"></textarea>	
                        </div>
                        <table class="table table-bordered base-info-table">
                            <tbody>
                                <tr>
                                   <td class="width15">部门</td>
                                    <td class="input-td">
                                    <select name="deptId"  class="chzn-select-search input-xlarge" id="deptId" onchange="changedept()">
                                        <option value="">-选择部门-</option>
                                    </select>
                                    </td>
                                    <td class="width15">岗位</td>
                                    <td class="input-td">
                                    <select name="positionId" id="positionId" class="chzn-select-search input-xlarge" onchange="changeposition()">
                                            <option value="">-请先选择岗位-</option>
                                            
                                        </select></td>
                                </tr>
                                <tr>
                                	<td class="width15">职位</td>
                                    <td class="input-td">
                                    	<select name="levelId"  class="chzn-select-search input-xlarge" id="levelId">
                                            <option value="">-请先选择岗位-</option>
                                        </select>
                                    </td>
                                    <td class="">选择角色</td>
                                    <td class="input-td word-parent">
                                    <select  class="chzn-select-search input-xlarge" id="roleId" >
                                        	<option value="">选择角色</option>
                                          <c:forEach items="${rolelist }" var="rolelist">
                                            <option value="${rolelist.roleId }">${rolelist.roleName }</option>
                                            </c:forEach>
                                     </select>
									</td>
                                </tr>
                                <tr>
                                	<td class="">工号</td>
                                    <td class="input-td">
                                    <input type="text" class="input-xlarge" id="employeeCode"/>
                                    <input type="hidden" class="input-xlarge" id="employeeCode1"/>
                                    </td>
                                    <td class="">姓名</td>
                                    <td class="input-td">
                                        <input type="text" class="input-xlarge" id="name"/>
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <td class="">身份证</td>
                                    <td class="input-td"><input type="text" class="input-xlarge " id="identityCard"/></td>
                                    <td class="">出生日期</td>
                                    <td class="input-td"><input type="text" class="input-xlarge timePickerClean datetimepicker"  id="birthday"/></td>
                                </tr>
                                <tr>
                                    <td class="">性别</td>
                                    <td class="input-td">
                                    <select name="sex" id="sex" class="chzn-select input-xlarge">
                                            <option value="">-选择性别-</option>
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </td>
                                    <td class="">手机号</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="phone"  onkeyup="setuser()"/></td>
                                </tr>
                                <tr>
                                    <td class="">到职日期</td>
                                    <td class="input-td">
                                        <input type="text" class="input-xlarge timePickerClean datetimepicker" daysoffset="0" id="entryDate"/>
                                    </td>
                                    <td class="">住址</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="address"/></td>
                                </tr>
                                <tr>
                                    <td class="">离职日期</td>
                                    <td class="input-td">
                                        <input type="text" class="width97 timePickerClean datetimepicker"  id="leaveDate"/>
                                    </td>
                                    <td class="">健康证</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="healthCard"/></td>
                                </tr>
                                <tr>
                                    <td class="">介绍人</td>
                                    <td class="input-td">
                                       
                                        <!-- <input type="text" class="input-xlarge" id="recommendId"/> -->
                                        <select  class="chzn-select-search input-xlarge" id="recommendId" >
                                        	<option value="">选择介绍人</option>
                                          <c:forEach items="${recommendList }" var="List">
                                            <option value="${List.employeeId }">${List.name }</option>
                                            </c:forEach>
                                        </select>
                                        
                                    </td>
                                    <td class="">紧急联系人电话</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="emergencyPhone"/></td>
                                </tr>
                                <tr>
                                    <td class="">基本工资</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="baseSalaries"/><span class="word">元/月</span></td>
                                    <td class="">岗位津贴</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="positionSalaries"/><span class="word-right">元/月</span></td>
                                </tr>
                                <tr>
                                    <td class="">账号</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="userName" disabled="true"/></td>
                                    <td class="">当前状态</td>
                                    <td class="input-td">
                                        <select data-placeholder="在职"  class="chzn-select-search input-xlarge" id="employeeStatus" >
                                            <option value="1">在职</option>
                                            <option value="2">离职</option>
                                        </select>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                        <div class="clearfix"></div>
                        <div class="self-btn">
                            <div class="btn btn-primary ml10 fr" onclick="addsave()">确定保存</div>
                            <button class="btn fr" onclick="addcanse()">取消</button>
                        </div>
                    </div><!--tabs-1-->
                </div>
            </div><!--modal-body-->
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal--> --%>

<%-- <!--修改员工资料模态框-->
<div class="modal hide" id="employee-update-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content employee-data-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">员工资料</h5>
            </div>
            <div class="modal-body">
                <div id="tabs">
                    <!-- <ul>
                        <li><a href="#tabs-11">基本资料</a></li>
                        <li><a href="#tabs-22" onclick="querypx()">个人简介</a></li>
                        <li><a href="#tabs-33" onclick="querygz()">工作经历</a></li>
                        <li><a href="#tabs-44" onclick="querysc()">擅长项目</a></li>
                        <li><a href="#tabs-55" onclick="queryjcjl()">奖惩记录</a></li>
                        <li><a href="#tabs-66" onclick="querytjgx()">推介关系图</a></li>
                        <li><a href="#tabs-77">派遣调动</a></li>
                    </ul> -->
                    <!-- 人员的id -->
                    <input type="hidden" id="updateemployeeId">
                    <div id="tabs-11">
                        <div class="base-info-img" id="container">
                            <img name='updateheadImg' src="" alt="" id="updateheadImage"/> 
                            <input type="hidden" name="hiddenupdateheadImage"/>
                            <button class="btn select-btn" id="updatepickHeadImg" onclick="cavsenEdit(this)">上传图片</button>
                            <p style="margin-top:20px;text-align:left;">个人简介</p>
                            <textarea id="employeeDescOfEdit" style="width:100px;height:158px;" placeholder="用于向顾客展示"></textarea>	
                        </div>
                        <table class="table table-bordered base-info-table" >
                            <tbody>
                                <tr>
                                <td class="width15">部门</td>
                                    <td class="input-td">
                                    <select name="updatedeptId"  class="chzn-select-search input-xlarge" id="updatedeptId" onchange="changedept1()">
                                    		<c:forEach items="${deptlist}" var="deptlist">
                                            <option value="${deptlist.deptId}">${deptlist.deptName}</option>
                                            </c:forEach>
                                     </select>
                                    </td>
                                    <td class="width15">岗位</td>
                                    <td class="input-td">
                                    <select name="updatepositionId" id="updatepositionId" class="chzn-select-search input-xlarge" onchange="changeposition1()">
                                            <option value="">-选择岗位-</option>
                                            <c:forEach items="${positionlist }" var="position">
                                            <option value="${position.positionId }">${position.positionName }</option>
                                            </c:forEach>
                                        </select></td>
                                </tr>
                                <tr>
                                <td class="width15">职位</td>
                                    <td class="input-td">
                                    <select name="updatelevelId"  class="chzn-select-search input-xlarge" id="updatelevelId">
                                            <option value="">-请先选择岗位-</option>
                                        </select>
                                    </td>
                                    <td class="">工号</td>
                                    <td class="input-td">
                                    <input type="text" class="input-xlarge" id="updateemployeeCode"/>
                                    <input type="hidden" class="input-xlarge" id="updateemployeeCode1"/>
                                    </td>
                                    
                                </tr>
                                <tr>
                                    <td class="">姓名</td>
                                    <td class="input-td">
                                        <input type="text" class="input-xlarge" id="updatename"/>
                                    </td>
                                    <td class="">性别</td>
                                    <td class="input-td">
                                    <select name="updatesex" id="updatesex" class="chzn-select input-xlarge">
                                            <option value="">-选择性别-</option>
                                            <option value="男">男</option>
                                            <option value="女">女</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="">身份证</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="updateidentityCard"/></td>
                                    <td class="">出生日期</td>
                                    <td class="input-td"><input type="text" class="input-xlarge timePickerClean datetimepicker"  id="updatebirthday"/></td>
                                </tr>
                                <tr>
                                    <td class="">当前状态</td>
                                    <td class="input-td">
                                        <select data-placeholder="在职"  class="chzn-select-search input-xlarge" id="updateemployeeStatus" >
                                            <option value="1">在职</option>
                                            <option value="2">离职</option>
                                        </select>
                                    </td>
                                    <td class="">手机号</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="updatephone"/></td>
                                </tr>
                                <tr>
                                    <td class="">到职日期</td>
                                    <td class="input-td">
                                        <input type="text" class="input-xlarge timePickerClean datetimepicker"  id="updateentryDate"/>
                                    </td>
                                    <td class="">住址</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="updateaddress"/></td>
                                </tr>
                                <tr>
                                    <td class="">离职日期</td>
                                    <td class="input-td">
                                        <input type="text" class="width97 timePickerClean datetimepicker"  id="updateleaveDate"/>
                                    </td>
                                    <td class="">选择角色</td>
                                    <td class="input-td word-parent">
                                    <select  class="chzn-select-search input-xlarge" id="updateroleId" >
                                        	<option value="">选择角色</option>
                                          <c:forEach items="${rolelist }" var="rolelist">
                                            <option value="${rolelist.roleId }">${rolelist.roleName }</option>
                                            </c:forEach>
                                     </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="">介绍人</td>
                                    <td class="input-td">
                                       
                                        <!-- <input type="text" class="input-xlarge" id="recommendId"/> -->
                                        <select  class="chzn-select-search input-xlarge" id="updaterecommendId" >
                                        	<option value="">选择介绍人</option>
                                          <c:forEach items="${recommendList }" var="List1">
                                            <option value="${List1.employeeId }">${List1.name }</option>
                                            </c:forEach>
                                        </select>
                                        
                                    </td>
                                    <td class="">紧急联系人电话</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="updateemergencyPhone"/></td>
                                </tr>
                                <tr>
                                    <td class="">基本工资</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="updatebaseSalaries"/><span class="word">元/月</span></td>
                                    <td class="">岗位津贴</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="updatepositionSalaries"/><span class="word-right">元/月</span></td>
                                </tr>
                                <tr>
                                    <td class="">健康证</td>
                                    <td class="input-td"><input type="text" class="input-xlarge" id="updatehealthCard"/></td>
                                    <!-- <td class=""></td>
                                    <td class="input-td word-parent">
                                    </td> -->
                                    <td class="">账号</td>
                                    <td class="input-td word-parent"><input type="text" class="input-xlarge" id="updateUserName" disabled="true"/></td>
                                </tr>

                            </tbody>
                        </table>
                        <div class="clearfix"></div>
                        <div class="self-btn">
                            <div class="btn btn-primary ml10 fr" onclick="updatesave()">确定保存</div>
                            <button class="btn fr" onclick="updatecanse()">取消</button>
                        </div>
                    </div><!--tabs-1-->
                   
                </div>
            </div><!--modal-body-->
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal--> --%>
<div class="modal hide" id="toLeadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account" style="width: 450px;height: 180px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">上传文件</h5>
            </div>
            <div class="modal-body">
                <form action="<%=basePath %>objective/action/importExcle" class="editprofileform" method="post" enctype="multipart/form-data" id="excleForm">
                    <p>
                        <label>文件位置选择:</label>
                        <input type="file" name="filename" id="file" class="input-large" value="" readonly="readonly">
                   </p>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn btn-primary modal-confirm" href="#" id="confirm" data-dismiss="modal" onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>


<!-- 美图秀秀 -->
<div class="mask" style="display: none;">
   <div id="flashEditorOut" style="position: relative;">
   <span class="mask_close" style="position:absolute;right:-5px;top:-5px"><img onclick="xiuxiu.onClose();" src="<%=basePath %>images/seo_close.png"></span>
        <div id="altContent2">
            <h1>美图秀秀2</h1>
        </div>
	</div>
</div>


<script type="text/javascript">
//获取加载页面时的页码信息
var pageNo = "${page.pageNo}";
var pageSize = "${page.pageSize}";
var totalPage = "${page.totalPage}"

var storeDtoListStr = '${storeDtoListStr}';
var storeDtoList= eval("("+storeDtoListStr+")");
	var setting = {
	    view: {
	        dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
	        showLine: true,//是否显示节点之间的连线
	        selectedMulti: false //设置是否允许同时选中多个节点
	    },
	    data: {
	        simpleData: {//简单数据模式
	            enable:true,
	            deptIdKey: "dateId",
	            dateKey: "dateType"
	        }
	    },
	    callback: {
	        beforeClick: function(treeId, treeNode) {
	            var dateId = treeNode.dateId;
	            var dateType = treeNode.dateType;
	            jQuery.ajax({
	        		type : "post",
	        		url : baseUrl + "employee/action/selectEmployeeBydateType",
	        		data : "dateId=" +dateId + "&dateType=" + dateType,
	        		dataType : "json",
	        		success : function(e){
	        			if(e.code != 0){
	        				dialog("系统繁忙！");
	        			}
	        			var list=e.msg;
	        			jQuery(".payroll_table").find("tr:gt(0)").remove();
	        			for (var i = 0; i < list.length; i++) {
	        				var employeeDto = list[i];
	        				jQuery(".payroll_table").append("<tr id='"+employeeDto.employeeId+"'>"+
										      				  "<td>"+employeeDto.employeeCode+"</td>"+
										    				  "<td>"+employeeDto.name+"</td>"+
										    				  "<td>"+employeeDto.sex+"</td>"+
										    				  "<td>"+employeeDto.deptName+"</td>"+
										    				  "<td>"+employeeDto.positionName+"</td>"+
										    				  "<td>"+employeeDto.levelName+"</td>"+
										    				  "<td>"+employeeDto.userName+"</td>"+
										    				  "<td>"+employeeDto.phone+"</td>"+
										    				  "<td><span onclick='selectEmp("+employeeDto.employeeId+")'><img src='"+baseUrl+"images/handle_1.png' ></span><span onclick='deleteinfo("+employeeDto.employeeId+")'><img src='"+baseUrl+"images/handle_2.png'></span></td>"+
										    		        "</tr>");
	        			}
	        		}
	        		
	        	});
	        }
	    }
	};

	var zNodes = storeDtoList;

	jQuery(document).ready(function(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting, zNodes);
	});
	


	//模拟用户点击
	jQuery(function(){
		for(i=0;i<=100;i++){
			
		jQuery('#treeDemo_1_span,#treeDemo_'+i+'_switch').click(function(){
			
				
		})
	 
		jQuery('#treeDemo_1_span,#treeDemo_'+i+'_switch').click()
		}
	})
	
	jQuery(function(){
        jQuery('.ztree> li>ul>li>ul>li>a>span').attr('style','background-position:-110px -16px');	  
	});
	
</script>
<script type="text/javascript" src="<%=basePath %>js/employee/employee.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/jquery.ztree.core-3.5.js"></script>
</div>
<!--RIGHT PANEL结束 -->
  </div>
</div>
</body>
<script>
var employeeLevels = eval('('+'${employeeLevels}'+')');
jQuery(function (){
	changeEmployeeLevel(jQuery("select[name='positionId']").val());
})
// 改变岗位等级
function changeEmployeeLevel(positionId){
	jQuery("select[name='levelId']").empty();
	for(var i=0;i<employeeLevels.length;i++){
		if (employeeLevels[i].positionId == positionId){
			var html = '<option value='+employeeLevels[i].levelId+'>'+employeeLevels[i].levelName+'</option>';
			jQuery("select[name='levelId']").append(jQuery(html));
		}
	}
}
var employeeId = null;
function saveEmployee(){
	var url = "employee/action/add";
	if (employeeId != null){
		var url = "employee/action/update";
	}
	var name = jQuery("input[name='name']").val();
	var employeeCode = jQuery("input[name='employeeCode']").val();
	var sex = jQuery("select[name='sex']").val();
	var birthday = jQuery("input[name='birthday']").val();
	var recommendId = jQuery("select[name='recommendId']").val();
	var phone = jQuery("input[name='phone']").val();
	var identityCard = jQuery("input[name='identityCard']").val();
	var roleId = jQuery("select[name='roleId']").val();
	var positionId = jQuery("select[name='positionId']").val();
	var deptId = jQuery("select[name='deptId']").val();
	var levelId = jQuery("select[name='levelId']").val();
	var employeeStatus = jQuery("select[name='employeeStatus']").val();
	var entryDate = jQuery("input[name='entryDate']").val();
	var leaveDate = jQuery("input[name='leaveDate']").val();
	var employeeDesc = jQuery("textarea[name='employeeDesc']").val();
	var data = {"employeeId":employeeId, "employeeCode":employeeCode, "sex":sex, "birthday":birthday, "recommendId":recommendId, "phone":phone, "identityCard":identityCard, 
			    "roleId":roleId, "positionId":positionId, "deptId":deptId, "levelId":levelId, "employeeStatus":employeeStatus, "entryDate":entryDate, "leaveDate":leaveDate, 
			    "employeeDesc":employeeDesc, "name":name};
	console.log(data);
	jQuery.ajax({
		type : "post",
		url : baseUrl + url,
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
		async : false,
		success : function(data) {
			dialog("该人员配置成功");
			jQuery(".zzc").hide();
		}
	});
}

function selectEmp(id){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/getdetail",
		data : "employeeId="+id,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			else {
				employeeId = id;
				jQuery("input[name='name']").val(e.msg.name);
				jQuery("input[name='employeeCode']").val(e.msg.employeeCode);
				jQuery("select[name='sex']").val(e.msg.sex);
				jQuery("input[name='birthday']").val(e.msg.birthday);
				jQuery("select[name='recommendId']").val(e.msg.recommendId);
				jQuery("input[name='phone']").val(e.msg.phone);
				jQuery("input[name='identityCard']").val(e.msg.identityCard);
				jQuery("select[name='roleId']").val(e.msg.roleId);
				jQuery("select[name='positionId']").val(e.msg.positionId);
				changeEmployeeLevel(e.msg.positionId);
				jQuery("select[name='deptId']").val(e.msg.deptId);
				jQuery("select[name='levelId']").val(e.msg.levelId);
				jQuery("select[name='employeeStatus']").val(e.msg.employeeStatus);
				jQuery("input[name='entryDate']").val(e.msg.entryDate);
				jQuery("input[name='leaveDate']").val(e.msg.leaveDate);
				jQuery("textarea[name='employeeDesc']").val(e.msg.employeeDesc);
				jQuery(".zzc").show();
			}
       }
	});
}
</script>
</html>