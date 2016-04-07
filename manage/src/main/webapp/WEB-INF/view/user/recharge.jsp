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
        <a href="#" class="current">充值列表</a>
    </div>
</div>

    <div class="container-fluid">
        <div class="search-top">
            <div class="head-top">
                   <div class="width8 c-header c-lblue">用户姓名</div>
                    <input id="realName" type="text" class="width10 c-header " style="height: 30px;"/>
                <div class="width10 c-header c-dblue" id="roleNamename">选择角色</div>
                <select class="width10 c-header " id="roleName">
                    <option value="${roleId}">渠道商</option> 
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
                               data-url="<%=basePath %>user/queryUser?type='1'"
                               data-pagination="true"
                               data-side-pagination="server"
                               data-query-params="queryParams"
                               data-page-list="[5, 10, 20, 50, 100, 200]"
                               data-height="490" id="accountSearchTable"
                               data-toolbar="#toolbar">
                            <thead>
                            <tr>
                                
                                <th data-field="userId" data-visible="false"></th>
                                <th data-field="roleName" >账号角色</th>
                                <th data-field="province">省份</th>
                                <th data-field="city" >城市</th>
                                <th data-field="area" >区/县</th>
                                <th data-field="userName" >账号名</th>
                                <th data-field="realName" >姓名</th>
                                <th data-field="phone" >手机号</th>
                                <th data-field="money" >金额</th>
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

<!--新增Modal -->
<div id="rechargeMod" class="modal hide" style="width: 800">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h5> 充值</h5>
    </div>
    <div class="modal-body nopadding">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box" style="margin: 0px;">
                    <div class="widget-content nopadding">
                        <form action="#" class="form-horizontal">
                            <div class="control-group">
                                <div class="span12">
                                    <table class="span12">
                                        <thead>
                                        <tr>
                                            <td  align="center" style="width: 150;margin-right: 10px;">充值对象</td>
                                            <td  align="center"style="width: 150;margin-right: 10px;">充值金额</td>
                                            <!-- <td  align="center"style="width: 450;margin-right: 10px;" colspan="3">充值原因</td> -->
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td>
                                                <input type="hidden" id="add_userId" class="span10"  style="margin-left: 20px; text-align:center;" />
                                                <input type="text" id="add_realName" class="span10"  style="margin-left: 20px; text-align:center;" disabled/> 
                                            </td>
                                            <td>
                                                 <input type="text" id="add_money" class="span10" style="margin-left: 20px;text-align:center;"/>元
                                            </td>
                                            <!-- <td>
				                                <input type="text" id="add_goodsName" class="span10" colspan="3" style="margin-left: 20px;"/>
				                            </td> -->
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
    <div class="modal-footer"> <a class="btn btn-primary btn-success" href="#" onclick="add()">保存</a> <a data-dismiss="modal" class="btn" href="#">取消</a> </div>
</div>
   
         <!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%> 
	
	<script type="text/javascript">
 	var baseUrl = "<%=basePath %>";
 	new PCAS("province3","city3","area3");
 	
	var searchData = {};
	//扩展bootstrap table的请求参数
	function queryParams(params){
		
		for(var k in searchData){
			params[k] = searchData[k];
		}
		//console.log("queryParams..." + JSON.stringify(params));
		return params;
	}

	//调用bootstrap table的函数
	function query(){
		
		var realName =$("#realName").val();
		if(realName!=null){
			searchData["realName"] = realName;
		}
		
		var roleName =$("#roleName").val();
		if(roleName!=null){
			searchData["roleId"] = roleName;
		}
		var province =$("#province").val();
		if(province!=null){
			searchData["province"] = province;
		}
		var city =$("#city").val();
		if(city!=null){
			searchData["city"] = city;
		}
		var area =$("#area").val();
		if(area!=null){
			searchData["area"] = area;
		}
		$('#accountSearchTable').bootstrapTable('refresh', null);
	}
	
	function accountSearchTable(value, row, index) {
		return [
		'<a class="like" href="#" data-toggle="modal" title="充值"><i class="icon-pencil"></i></a> '
		
		].join('');
		}
		window.operateEvents = {
				  'click .like': function (e, value, row, index) {
					  $('#rechargeMod').modal();
					  $("#add_userId").val(row.userId);
					  $("#add_realName").val(row.realName);
				  }
				};
		
		
	function add(){
		var userId=$("#add_userId").val();
		var money=$("#add_money").val();
		if(money==""||money==null){
			alert("金额不能为空");
			return;
		}
		var re = /^\d+(?=\.{0,1}\d+$|$)/ ;
		if(!re.test(money)){
			alert("金额格式不正确");
			return;
		}
		$.ajax({
	        type: "POST",
	        dataType: "json",
	        url: baseUrl+'user/recharge',
	        data:'userId='+userId+'&money='+money,
	        success: function (result) {
	            alert(result.code);
	            $('#rechargeMod').modal('hide');
	        	$('#accountSearchTable').bootstrapTable('refresh', null);
	        }
	    });
	}
	</script>
  </body>
</html>