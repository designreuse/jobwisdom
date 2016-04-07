<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/base.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
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
        <a href="<%=basePath %>cust/view/query" class="current">客户查询</a>
    </div>
</div>

   <div class="container-fluid">
         <div class="search-top">
            <div class="head-top">
                <div class="division2">
                    <div class="width8 c-header c-lblue">注册日期</div>
                    <input id="startTime" type="text" class="width10 c-header form_datetime" style="height: 30px;"/>
                    <span class="fl mgr10">---</span>
                    <input id="endTime" type="text" class="width10 c-header form_datetime" style="height: 30px;"/>
                </div>
                <div class="division2 mgl30">
                    <div class="width8 c-header c-lblue" style="margin-left: 17px;">区域查询</div>
                    <select class="width10 c-header" name="province3" id="province"></select>
                    <select class="width10 c-header" name="city3" id="city"></select>
                    <select class="width10 c-header" name="area3" id="area"></select>
                </div>
            </div>

            <div class="head-bottom">
                <div class="division4">
                    <div class="width8 c-header c-lblue">系统版本</div>
                    <select class="width8 c-header" id="sysName">
                         <option value="">选择版本</option>
                         <c:forEach items="${listmap}" var="listmap">
							<option value="${listmap.goodsId}">${listmap.goodsName} </option> 
						 </c:forEach>
                    </select>
                </div>
                <div class="division4" id="customerTypeid">
                    <div class="width8 c-header c-lblue">客户属性</div>
                    <select class="width8 c-header" id="customerType">
                            <option value="">选择版本</option>
							<option value="1">单店</option>
							<option value="2">连锁店</option> 
							<option value="3">分店</option>  
                    </select>
                </div>
                <div class="division4 mgl30">
                    <div class="width8 c-header c-lblue" style="margin-left: 10px;">姓名查询</div>
                    <input id="principalName" type="text" placeholder="" class="width8 c-header" style="height: 30px;" data-provide="typeahead" data-items="4" data-source="[&quot;Alabama&quot;,&quot;Alaska&quot;,&quot;Arizona&quot;,&quot;Arkansas&quot;,&quot;California&quot;,&quot;Colorado&quot;,&quot;Ahmedabad&quot;,&quot;India&quot;,&quot;Florida&quot;,&quot;Georgia&quot;,&quot;Hawaii&quot;,&quot;Idaho&quot;,&quot;Illinois&quot;,&quot;Indiana&quot;,&quot;Iowa&quot;,&quot;Kansas&quot;,&quot;Kentucky&quot;,&quot;Louisiana&quot;,&quot;Maine&quot;,&quot;Maryland&quot;,&quot;Massachusetts&quot;,&quot;Michigan&quot;,&quot;Minnesota&quot;,&quot;Mississippi&quot;,&quot;Missouri&quot;,&quot;Montana&quot;,&quot;Nebraska&quot;,&quot;Nevada&quot;,&quot;New Hampshire&quot;,&quot;New Jersey&quot;,&quot;New Mexico&quot;,&quot;New York&quot;,&quot;North Dakota&quot;,&quot;North Carolina&quot;,&quot;Ohio&quot;,&quot;Oklahoma&quot;,&quot;Oregon&quot;,&quot;Pennsylvania&quot;,&quot;Rhode Island&quot;,&quot;South Carolina&quot;,&quot;South Dakota&quot;,&quot;Tennessee&quot;,&quot;Texas&quot;,&quot;Utah&quot;,&quot;Vermont&quot;,&quot;Virginia&quot;,&quot;Washington&quot;,&quot;West Virginia&quot;,&quot;Wisconsin&quot;,&quot;Wyoming&quot;]">

                </div>

            </div>
            <div class="sale-search" style="font-size: 25px;" onclick="query()">
                    <a href="#"><i class="icon-search" style=" margin-top: 23px;"></i></a>
            </div>
        </div>

        <!--<div class="head-bottom"></div>-->

        <hr>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                        <h5>用户列表</h5>

                    </div>
                    <div class="widget-content nopadding">
                        <div id="toolbar" class="btn-group">

                        </div>
                        <!--bootstrap table-->
                        <!--data-url="http://mikepenz.com/jsfiddle/" data-show-refresh="true"-->
                        <table data-toggle="table"
                               data-method="post"
                               data-url="<%=basePath%>cust/queryCust"
                               data-pagination="true"
                               data-side-pagination="server"
                               data-query-params="queryParams"
                               data-page-list="[5, 10, 20, 50, 100, 200]"
                               data-height="490" id="utable"
                               data-toolbar="#toolbar">
                            <thead>
                                <tr>
                                    <th data-field="sysName1" data-sortable="true">系统版本</th>
                                    <th data-field="customerType1" data-sortable="true">客户属性</th>
                                    <th data-field="companyName" data-align="center" data-sortable="true">公司名称</th>
                                    <th data-field="customerName" data-align="center" data-sortable="true">客户名称</th>
                                    <th data-field="createTime" data-sortable="true">注册时间</th>
                                    <th data-field="expireTime" data-sortable="true">到期时间</th>
                                    <th data-field="principalName" data-sortable="true">联系人</th>
                                    <th data-field="phone" data-sortable="true">联系电话</th>
                                    <th data-field="province" data-sortable="true">所属省</th>
                                    <th data-field="city" data-sortable="true">所属市</th>
                                    <th data-field="area" data-sortable="true">所属区</th>
                                    <th data-field="operate" data-sortable="true" data-formatter="operateFormatter"
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


<%@include file="/WEB-INF/view/footer.jsp"%>
<%@include file="/WEB-INF/js.jsp"%>
<script type="text/javascript">
/**元素控制,注意将特殊的符号去掉*/
var classInit = ${ys};
</script>
<script type='text/javascript' src='<%=basePath %>js/cust.js'></script>
<script type="text/javascript">
new PCAS("province3","city3","area3");
$(document).ready(function(){
 	var type='${type}';
	if(type!=null&&type!=""){
		$('#customerTypeid').hide();
	}
	//-------------------------
	  $.ajax({
	     type: "POST",
	     dataType: "json",
	     url: '<%=basePath%>cust/getCompanyName',
	     data:'',
	     success: function (result) {
	       //查询列表的客户查询条件
	       var last=JSON.stringify(result.msg); 
	       $('#update_companyName').typeahead({
	     	  source : result.msg,
	    		  menu : "<ul class='typeahead dropdown-menu cust' style='z-index:99999;'></ul>"
	    	   });
	     }
	  });
	});  
</script>
<!-- 下面是权限控制 -->
<%-- <script  type='text/javascript'>
=======
<%-- <!-- 下面是权限控制 -->
<script  type='text/javascript'>
var classname = '';
</script>
<%List<String> ls =(List<String>)request.getAttribute("ys"); %>
<%for (int i = 0; i < ls.size(); i++) { %>
<script>
if (classname != '') classname += '!';
classname += '<%=ls.get(i)%>';
</script>
<%} %>
<script>
$(document).ready(function(){ 
	$("body").click(function(event){
		window.setTimeout("secondShow()",100);
		});
start();}); 
var iid ;
function showClass(){
	$(".like").hide();
	$(".remove").hide();
	$(".add").hide();
	if(window.document.readyState == "complete"){
		//只要页面上有需要的尽管在这里面写就行了,还有就是权限配置好久行了,引入即可实现
		$(".like").hide();
		$(".remove").hide();
		$(".add").hide();
		window.clearInterval(iid);  
		var pidArr = classname.split("!");
		for (var i = 0; i < pidArr.length; i++) {
			$("."+pidArr[i]).show();
		}
	}
}
function start(){
	iid = window.setInterval("showClass()",10); 
}
function secondShow(){
	$(".like").hide();
	$(".remove").hide();
	$(".add").hide();
	var pidArr = classname.split("!");
	for (var i = 0; i < pidArr.length; i++) {
		$("."+pidArr[i]).show();
	}
} 
</script>
--%>

  </body>
</html>