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
       <!-- <a href="" class="tip-bottom" data-original-title=""></a>-->
        <a href="#" class="current">项目设置</a>
    </div>
</div>


    <div class="container-fluid">
        <!-- <div class="row-fluid">
            <div class="search">
                <input  id="querydata" type="text" placeholder="项目类别/项目名称/价格">
                <button type="submit" class="tip-bottom" data-original-title="Search" onclick="query()"><i class="icon-search icon-white"></i></button>
            </div>
        </div> -->
<div class="search-top">
            <div class="head-top">
                <div class="division4">
                    <div class="width8 c-header c-lblue">系统版本</div>
                    <select class="width8 c-header" id="goodstypename">
                        <option value="">选择类型</option>
                         <option value="1">系统类</option>
                         <option value="2">短信套餐</option>
                         <option value="3">外置设备</option>
                    </select>
                </div>
                <div class="division4 mgl30">
                    <div class="width8 c-header c-lblue" style="margin-left: 10px;">项目名称</div>
                    <input id="goodsName" type="text" placeholder="" class="width8 c-header" style="height: 30px;" data-provide="typeahead" data-items="4" data-source="[&quot;Alabama&quot;,&quot;Alaska&quot;,&quot;Arizona&quot;,&quot;Arkansas&quot;,&quot;California&quot;,&quot;Colorado&quot;,&quot;Ahmedabad&quot;,&quot;India&quot;,&quot;Florida&quot;,&quot;Georgia&quot;,&quot;Hawaii&quot;,&quot;Idaho&quot;,&quot;Illinois&quot;,&quot;Indiana&quot;,&quot;Iowa&quot;,&quot;Kansas&quot;,&quot;Kentucky&quot;,&quot;Louisiana&quot;,&quot;Maine&quot;,&quot;Maryland&quot;,&quot;Massachusetts&quot;,&quot;Michigan&quot;,&quot;Minnesota&quot;,&quot;Mississippi&quot;,&quot;Missouri&quot;,&quot;Montana&quot;,&quot;Nebraska&quot;,&quot;Nevada&quot;,&quot;New Hampshire&quot;,&quot;New Jersey&quot;,&quot;New Mexico&quot;,&quot;New York&quot;,&quot;North Dakota&quot;,&quot;North Carolina&quot;,&quot;Ohio&quot;,&quot;Oklahoma&quot;,&quot;Oregon&quot;,&quot;Pennsylvania&quot;,&quot;Rhode Island&quot;,&quot;South Carolina&quot;,&quot;South Dakota&quot;,&quot;Tennessee&quot;,&quot;Texas&quot;,&quot;Utah&quot;,&quot;Vermont&quot;,&quot;Virginia&quot;,&quot;Washington&quot;,&quot;West Virginia&quot;,&quot;Wisconsin&quot;,&quot;Wyoming&quot;]">
                </div>

            </div>
            <div class="sale-search" style="font-size: 25px;" onclick="query()">
                    <a href="#"><i class="icon-search" style=" margin-top: 23px;"></i></a>
            </div>
        </div>
       <!--  <div class="row-fluid">
            <div class="span12">
                <table class="span12">
                    <thead>
                        <tr>
                        <td class="span3" align="center">项目类别</td>
                        <td class="span3" align="center">项目名称</td>
                        <td class="span3" align="center">时间</td>
                        <td class="span3" align="center">销售价格</td>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <select name="" id="add_goodstype" class="span10" onchange="changetype()">
                                    <option value="1">系统类</option>
                                    <option value="2">短信套餐</option>
                                    <option value="3">外置设备</option>
                                </select>
                            </td>
                            <td class="goodsname">
                                <input type="text" id="add_goodsName" class="span10"/> 
                            </td>
                            <td>
                                <select name="" id="add_goodsDesc" class="span10">
                                    <option value=""></option>
                                	<option value="1">30天试用</option>
                                    <option value="2">1年</option>
                                    <option value="3">2年</option>
                                    <option value="4">100年</option>
                                </select>
                            </td>
                            <td>
                                <input type="text" id="add_goodsPrice" class="span10"/>
                            </td>
                    </tr>
                    </tbody>
                </table>
                <input type="button" class="btn btn-info pull-right mg-right30" value="保存" onclick="add()"/>
            </div>
        </div> -->

        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-list"></i> </span>
                        <h5>已设置项目列表</h5>
                    </div>
                    <div id="toolbar" class="btn-group">
                            <button type="button" class="btn btn-default" style="height: 30px; margin-bottom:6px; margin-left:6px;"onclick="addnew()">
                                <i class=" icon-plus">新增</i>
                            </button>
                        </div>
                    <div class="widget-content nopadding">
                        <table data-toggle="table"
                               data-method="post"
                               data-url="<%=basePath%>project/queryProject"
                               data-pagination="true"
                               data-side-pagination="server"
                               data-query-params="queryParams"
                               data-page-list="[10, 20, 50, 100, 200]"
                               data-height="490" id="projectListTable">
                            <thead>
                            <tr>
                                <th data-field="typeName"  class="span3">类型</th>
                                <th data-field="goodsName" >项目名称</th>
                                <th data-field="descName">时间</th>
                                <th data-field="goodsPrice" >销售价格</th>
                                <th data-field="goodsStock" >库存量</th>
                                <th data-field="operate"  data-formatter="projectListTable"
                                    data-events="operateEvents">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>

        </div>

    </div>
    
</div>
<!--新增Modal -->
<div id="addprojectMod" class="modal hide" style="width: 800">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h5> 新增项目</h5>
    </div>
    <div class="modal-body nopadding">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box" style="margin: 0px;">
                    <div class="widget-content nopadding">
                        <form action="#" class="form-horizontal">
                            <div class="control-group">
                                <div class="span18">
                                    <table class="span18">
                                        <thead>
                                        <tr>
                                            <td  align="center" style="width: 150;margin-right: 10px;">项目类别</td>
                                            <td  align="center"style="width: 150;margin-right: 10px;">项目名称</td>
                                            <td  align="center"style="width: 150;margin-right: 10px;">时间</td>
                                            <td  align="center"style="width: 150;margin-right: 10px;">销售价格</td>
                                            <td  align="center"style="width: 150;margin-right: 10px;">库存量</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>
                                                <select name="" id="add_goodstype" class="span10">
                                                    <option value="1">系统类</option>
                                                    <option value="2">短信套餐</option>
                                                    <option value="3">外置设备</option>
                                                </select>
                                            </td>
                                            <td>
                                                 <input type="text" id="add_goodsName" class="span10"/> 
                                            </td>
                                            <td>
				                                <select name="" id="add_goodsDesc" class="span10">
				                                    <option value=""></option>
				                                	<option value="1">30天试用</option>
				                                    <option value="2">1年</option>
				                                    <option value="3">2年</option>
				                                    <option value="4">100年</option>
				                                </select>
				                            </td>
				                            <td>
				                                <input name="add_goodsPrice" type="text" id="add_goodsPrice" class="span10"/>
				                            </td>
				                            <td>
				                                <input name="add_goodsStock" type="text" id="add_goodsStock" class="span10"/>
				                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer"> <a ondata-dismiss="modal" class="btn btn-primary btn-success" href="#" onclick="add()">保存</a> <a data-dismiss="modal" class="btn" href="#">取消</a> </div>
</div>



<!-- 已设置项目类别修改Modal -->
<div id="projectMod" class="modal hide" style="width: 800">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h5> 修改项目</h5>
    </div>
    <div class="modal-body nopadding">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box" style="margin: 0px;">
                    <div class="widget-content nopadding">
                        <form action="#" class="form-horizontal">
                            <div class="control-group">
                                <div class="span18">
                                    <table class="span18">
                                        <thead>
                                        <tr>
                                            <td style="width: 150;margin-right: 10px;" align="center">项目类别</td>
                                            <td style="width: 150;margin-right: 10px;" align="center">项目名称</td>
                                            <td style="width: 150;margin-right: 10px;" align="center">时间</td>
                                            <td style="width: 150;margin-right: 10px;" align="center">销售价格</td>
                                            <td  align="center"style="width: 150;margin-right: 10px;">库存量</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>
                                            <input type="hidden" id="update_goodsId">
                                                <select name="" id="update_goodstype" class="span10">
                                                    <option value="1">系统类</option>
                                                    <option value="2">短信套餐</option>
                                                    <option value="3">外置设备</option>
                                                </select>
                                            </td>
                                            <td>
                                                 <input type="text" id="update_goodsName" class="span10"/> 
                                            </td>
                                            <td>
				                                <select name="" id="update_goodsDesc" class="span10">
				                                    <option value=""></option>
				                                	<option value="1">30天试用</option>
				                                    <option value="2">1年</option>
				                                    <option value="3">2年</option>
				                                    <option value="4">100年</option>
				                                </select>
				                            </td>
				                            <td>
				                                <input name="update_goodsPrice" type="text" id="update_goodsPrice" class="span10"/>
				                            </td>
				                            <td>
				                                <input name="update_goodsStock" type="text" id="update_goodsStock" class="span10"/>
				                            </td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
<input type="hidden" id="delete_goodsId">
    </div>
    <div class="modal-footer"> <a ondata-dismiss="modal" class="btn btn-primary btn-success" href="#" onclick="update()">保存修改</a> <a data-dismiss="modal" class="btn" href="#">取消</a> </div>
</div>


<script type="text/javascript">


</script>
    <!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
	<script type="text/javascript">
 	var baseUrl = "<%=basePath %>";
   </script>
      <script type='text/javascript' src='<%=basePath %>js/setproject.js'></script>
  </body>
</html>