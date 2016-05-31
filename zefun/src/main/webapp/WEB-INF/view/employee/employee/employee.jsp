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

  
   
    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
    <%@ include file="/top.jsp" %>
    
<div class='content_right clearfix'>
    <div class="payroll_2_head">
	   <button id="downLondimport">导出模板下载</button><button id="downLond">导出</button><button data-toggle="modal" data-target="#toLeadModal">导入</button><button onclick="openadd()">添加员工</button>
	   <span class="payroll_1_search">
	     <input type="search" id="search" placeholder="员工姓名/工号/手机号"/>
         <input type="hidden" id="querygangwei">
         <!-- <button class="button-search btn width100 ml-10" onclick="changePage()">查询</button> -->
         <button class="button-search btn width100 ml-10" onclick="findAllByNameOrCodeOrPhone()">查询</button>
	   </span>
	</div>
	
	<div class="payroll_2_content clearfix">
	   <div class="payroll_2_left">
	     <div class="content_wrap">
	         <div class="zTreeDemoBackground left">
		       <ul id="treeDemo" class="ztree"></ul>
         	</div>
         </div>
	 
	   </div>
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
				  <td><span onclick="openedit(${employee.employeeId})"><img src="<%=basePath %>images/handle_1.png" ></span><span onclick="deleteinfo(${employee.employeeId})"><img src="<%=basePath %>images/handle_2.png"></span></td>
		        </tr>
	       </c:forEach>
	    </table>
	   </div>
	
	</div>
  
 </div>
<!--添加员工模态框-->
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
</div><!--modal-->

<!--修改员工资料模态框-->
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
</div><!--modal-->
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


<div class="modal hide in" id="add-content-image1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage1" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<div class="modal hide" id="jietu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content jietu ">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">选择头像</h4>
            </div>
            <div class="modal-body nopadding">
              <div class="crop-container">
                <img src="<%=basePath %>images/pic_none.gif" id="cropbox" />
              </div>

              <div class="jietu-control">
                <input type="file" class="inputfile" accept="image/*" />
                <div class="btn dblock">选择文件</div>
                <div class="btn dblock mt5 save">保存</div>
                <div class="btn dblock mt5 zoomin">放大</div>
                <div class="btn dblock mt5 zoomout">缩小</div>
              </div>

            </div>
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
										    				  "<td><span onclick='openedit("+employeeDto.employeeId+")'><img src='"+baseUrl+"images/handle_1.png' ></span><span onclick='deleteinfo("+employeeDto.employeeId+")'><img src='"+baseUrl+"images/handle_2.png'></span></td>"+
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
</script>
<script type="text/javascript" src="<%=basePath %>js/employee/employee.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/jquery.ztree.core-3.5.js"></script>
</div>
<!--RIGHT PANEL结束 -->
  </div>
</div>
</body>
</html>