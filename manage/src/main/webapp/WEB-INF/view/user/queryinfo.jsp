<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

 <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="author" content="lives"/>
      <meta name="description" content="admin"/>
      <meta name="keywords" content="admin lives 店务管理 美容美发"/>

      <title>后台管理</title>

     

  </head>
  <body>
<!--头部页面引用-->
	<%@include file="/WEB-INF/view/topheader.jsp"%>
	<!--左侧菜单页面引用-->
	<%@include file="/WEB-INF/view/sliderbar.jsp"%>


<div id="content">
  <div id="content-header">
    <div id="breadcrumb">
        <a href="<%=basePath %>homepage/view/query" class="tip-bottom" data-original-title="主页">
            <i class="icon-home"></i>首页看板
        </a>
       <!-- <a href="" class="tip-bottom" data-original-title="客户管理">客户管理</a>-->
        <a href="#" class="current">账户查询</a>
    </div>
</div>

    <div class="container-fluid">
        <div class="search-top">
            <div class="head-top">
                   <div class="width8 c-header c-lblue">注册日期</div>
                    <input id="startTime" type="text" class="width10 c-header form_datetime" style="height: 30px;"/>
                    <span class="fl mgr10">---</span>
                    <input id="endTime" type="text" class="width10 c-header form_datetime" style="height: 30px;"/>
                <div class="width10 c-header c-dblue" id="roleNamename">选择角色</div>
                <select class="width10 c-header " id="roleName">
                    <option value="">全部角色</option>
                    <c:forEach items="${role}" var="role">
		                 <option value="${role.roleId}">${role.roleName} </option> 
		               </c:forEach>
                </select>
                <div class="width10 c-header c-green">选择区域</div>
                <select class="width10 c-header" name="province3" id="province">
                </select>
                <select class="width10 c-header" name="city3" id="city">
                </select>
                <select class="width10 c-header" name="area3" id="area">
                </select>
            </div>

            <div class="sale-search">
                <i class="icon-search"></i>
                <span><a href="#"
						onclick="query()">搜索</a></span>
            </div>
        </div>

        <!--<div class="head-bottom"></div>-->

        <hr>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                        <h5>账号列表</h5>
                    </div>

                        <!--bootstrap table-->
                        <!--data-url="http://mikepenz.com/jsfiddle/" data-show-refresh="true"-->
                        <table data-toggle="table"
                               data-method="post"
                               data-url="<%=basePath %>user/queryUser"
                               data-pagination="true"
                               data-side-pagination="server"
                               data-query-params="queryParams"
                               data-page-list="[5, 10, 20, 50, 100, 200]"
                               data-height="490" id="accountSearchTable"
                               data-toolbar="#toolbar">
                            <thead>
                            <tr>
                                <!--<th data-field="state" data-checkbox="true"></th>-->
                                <th data-field="userId" data-visible="false"></th>
                                <th data-field="createTime" data-sortable="true">注册时间</th>
                                <th data-field="roleName" >账号角色</th>
                                <th data-field="province">省份</th>
                                <th data-field="city" >城市</th>
                                <th data-field="area" >区/县</th>
                                <th data-field="userName" >账号名</th>
                                <th data-field="realName" >姓名</th>
                                <th data-field="phone" >手机号</th>
                                <th data-field="money" >可增客户数</th>
                                <th data-field="operate" data-sortable="true" data-formatter="accountSearchTable"
                                    data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>

<!-- 新增线索Modal -->
<div id="account-fixed" class="modal hide">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h5> 修改用户</h5>
    </div>
    <div class="modal-body nopadding">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box" style="margin: 0px;">
                    <div class="widget-content nopadding">
                        <form action="#" class="form-horizontal">

                            <div class="control-group">
                                <div class="span11" >
                                    <label for="normal" class="control-label">选择账号角色: </label>
                                    <div class="controls">
                                        <select  id="update_roleName" class="span12 client-attribute" >
                                            <c:forEach items="${role}" var="role">
							                 <option value="${role.roleId}">${role.roleName} </option> 
							               </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span12">
                                    <label for="normal" class="control-label">选择管理区域: </label>
                                    <div class="controls">
                                        <div class="controls-x1">
                                            <select class="span4"  name="province1" id="update_province">
                                            </select>
                                        </div>
                                        <div class="controls-x3">
                                            <select class="span4" name="city1" id="update_city">
                                            </select>
                                        </div>
                                        <div class="controls-x3" >
                                            <select class="span3" name="area1" id="update_area">
                                            </select>
                                        </div>
                                        <!-- <textarea name="" id="" cols="30" rows="10" class="mgt10 span11"></textarea> -->
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span11">
                                    <label for="normal" class="control-label">公司名称: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" id="update_companyName"/>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span11">
                                    <label for="normal" class="control-label">公司地址: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" id="update_addr"/>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span11">
                                    <label for="normal" class="control-label">真实姓名: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" id="update_realName"/>
                                    </div>
                                </div>
                            </div>


                            <div class="control-group">
                                <div class="control-group">
                                    <div class="span6 fl">
                                        <label for="normal" class="control-label">联系电话: </label>
                                        <div class="controls">
                                            <input type="text" class="span12" id="update_fixedNum">
                                        </div>
                                    </div>
                                    <div class="span5 fl" style="margin-left: 2.927659574468085%;">
                                        <label for="normal" class="control-label">手机号: </label>
                                        <div class="controls">
                                            <input type="text" class="span12" style="" id="update_phone">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="control-group">
                                    <div class="span6 fl">
                                        <label for="normal" class="control-label">后台账号: </label>
                                        <div class="controls">
                                            <input type="text" class="span12" id="update_userName">
                                            <input type="hidden" class="span12" id="update_userId">
                                        </div>
                                    </div>
                                    <div class="span5 fl" style="margin-left: 2.927659574468085%;">
                                        <label for="normal" class="control-label">可增客户数: </label>
                                        <div class="controls">
                                            <input type="text" class="span12" style="" id="update_noney">
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="modal-footer"> <a class="btn btn-primary btn-success" href="#" onclick="updateUser()">保存</a> <a data-dismiss="modal" class="btn" href="#">取消</a> </div>
</div>


      




   
         <!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%> 
	<script type='text/javascript' src='<%=basePath %>js/user.js'></script>
	
	<script type="text/javascript">
 	var baseUrl = "<%=basePath %>";
 	new PCAS("province3","city3","area3");
 	new PCAS("province1","city1","area1");

	</script>
  </body>
</html>