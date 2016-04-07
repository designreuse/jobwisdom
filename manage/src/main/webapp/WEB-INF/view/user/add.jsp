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
        <div id="breadcrumb"> <a href="<%=basePath %>homepage/view/query" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 主页看板</a> 
        <a href="#" class="current">新增用户</a> </div>
        <!--<h1>新增用户</h1>-->
    </div>
    <div class="container-fluid">
        <hr>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5>账号注册</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <div class="form-horizontal" >

                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">选择账号角色: </label>
                                    <div class="controls">
                                        <select name="" id="roleName" class="span12 client-attribute">
                                            <c:forEach items="${role}" var="role">
							                 <option value="${role.roleId}">${role.roleName} </option> 
							               </c:forEach>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">选择管理区域: </label>
                                    <div class="controls">
                                        <div class="controls-x1">
                                            <select class="span4" name="province3" id="province">
                                            </select>
                                        </div>
                                        <div class="controls-x3">
                                            <select class="span4" name="city3" id="city">
                                            </select>
                                        </div>
                                        <div class="controls-x3">
                                            <select class="span4" name="area3" id="area">
                                            </select>
                                        </div>
                                        <!-- <textarea name="" id="" cols="30" rows="10" class="mgt10 span12"></textarea> -->
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">公司名称: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" id="companyName"/>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">公司地址: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" id="addr"/>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">真实姓名: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" id="realName"/>
                                    </div>
                                </div>
                            </div>


                            <div class="control-group">
                                <div class="control-group">
                                    <div class="span4 fl">
                                        <label for="normal" class="control-label">联系电话: </label>
                                        <div class="controls">
                                            <input type="text" class="span12" id="fixedNum">
                                        </div>
                                    </div>
                                    <div class="span4 fl" style="margin-left: 2.927659574468085%;">
                                    <label for="normal" class="control-label">手机号: </label>
                                    <div class="controls">
                                        <input type="text" class="span11" style="" id="phone">
                                    </div>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="control-group">
                                    <div class="span4 fl">
                                        <label for="normal" class="control-label">后台账号: </label>
                                        <div class="controls">
                                            <input type="text" class="span11" id="userName">
                                        </div>
                                    </div>
                                    <div class="span4 fl" style="margin-left: 2.927659574468085%;">
                                    <label for="normal" class="control-label">可增客户次数: </label>
                                    <div class="controls">
                                        <input type="text" id="money" class="span12"  >
                                    </div>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <button class="btn btn-success client-add-save" style="margin-right: 25%" onclick="adduser()">保存</button>
                                 <button class="btn btn-success client-add-save" onclick="goback()"><a href="#"  style="text-decoration:none;color: #fff;">返回查询页面</a></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div></div>

      




	
   <script type='text/javascript' src='<%=basePath %>js/user.js'></script>
         <!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%> 
	<script type="text/javascript">
 	var baseUrl = "<%=basePath %>";
 	new PCAS("province3","city3","area3");
 	function goback(){
 		location.href=encodeURI(baseUrl +"user/view/query"); 
 	}
   </script>
  </body>
</html>