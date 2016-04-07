<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/base.jsp"%>
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
       <!-- <a href="" class="tip-bottom" data-original-title="开户注册">开户注册</a>-->
        <a href="#" class="current">客户公海</a>
    </div>
</div>


    <div class="container-fluid">
    	<div class="search-top">
				<div class="head-top">
					<div class="width10 c-header c-dblue">跟进状态</div>
					<select class="width10 c-header" id="clueStatus">
						<option value="">全部</option>
						<option value="未接触">未接触</option>
						<option value="已初访">已初访</option>
						<option value="有意向">有意向</option>
						<option value="已确定">已确定</option>
						<option value="已拒绝">已拒绝</option>
					</select>
					<div class="width10 c-header c-yellow">录入时间</div>
					<input size="16" type="text" value="" id="startTime"
						class="width15 header fl form_datetime "style="height:30px;"><input size="16"
						type="text" value="" id="endTime"
						class="width15 header fl form_datetime " style="height:30px;">
					<div class="width10 c-header c-dblue">店铺名称</div>
					<input type="text" id="customerName" placeholder="店铺名称"
						class="width10 c-header" style="margin: 0 auto;height:30px;"
						data-provide="typeahead" data-items="4" id="start_time"
						data-source=" ">
				</div>
				<div class="head-bottom">
					<div class="width10 c-header c-lblue">店铺数量</div>
					<input type="number" id="minNumber" class="width10 c-header" style="height:30px;"/>
					<input type="number" id="maxNumber" class="width10 c-header" style="height:30px;"/>
					<div class="width10 c-header c-green">品牌定位</div>
					<select class="width10 c-header" id="brandLevel">
						<option value="">全部</option>
						<option value="高端">高端</option>
						<option value="中高端">中高端</option>
						<option value="中端">中端</option>
						<option value="中低端">中低端</option>
						<option value="低端">低端</option>
					</select>
				</div>
				<div class="sale-search">
					<i class="icon-search"></i> <span><a href="#"
						onclick="publicQuery()">搜索</a></span>
				</div>
			</div>
			<hr>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                        <h5>销售线索(客户公海)</h5>
                    </div>
                    <button type="button" class="btn btn-success" style="margin: 4px 4px;height: 30px;" data-toggle="modal" data-target="#addLeads" onclick="getSelections1()">
                        <i class="icon-share-alt">转到销售线索</i>
                    </button>
                    <button type="button" class="btn btn-success" style="margin: 4px 4px;height: 30px;" data-toggle="modal" data-target="#addLeads" onclick="goback()">
                        <i class="">进入销售线索页面</i>
                    </button>
                    <!--bootstrap table-->
                    <!--data-url="http://mikepenz.com/jsfiddle/" data-show-refresh="true"-->
                    <table data-toggle="table"
                           data-method="post"
                           data-url="<%=basePath%>custclue/queryPublicInfo"
                           data-pagination="true"
                           checkboxHeader="true"
                           data-side-pagination="server"
                           data-query-params="publicQueryParams"
                           data-page-list="[5, 10, 20, 50, 100, 200]"
                           data-height="490" id="modUserTable"
                           data-toolbar="#toolbar">
                        <thead>
                        <tr>
                            <th data-field="" data-checkbox="true" ></th>
									<th data-field="clueId" data-visible="false" ></th>
									<th data-field="createTime" data-align="right" >录入时间</th>
									<th data-field="customerName" data-align="center">店铺名称</th>
									<th data-field="customerType">店铺属性</th>
									<th data-field="storeNumber" data-sortable="true">间数</th>
									<th data-field="brandLevel">店铺定位</th>
									<th data-field="principalName">联系人</th>
									<th data-field="principalDuty">职位</th>
									<th data-field="phone">手机号</th>
									<th data-field="province">省份</th>
									<th data-field="city">城区</th>
									<th data-field="area">区/县</th>
									<th data-field="clueStatus">跟进状态</th>
                           <!-- <th data-field="operate" data-formatter="pencil" data-events="operateEvents">修改</th>--><!--operateFormatter-->
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
	
<script type="text/javascript">
var baseUrl = "<%=basePath %>";
function goback(){
	location.href=encodeURI(baseUrl +"custclue/view/query"); 
}
</script>
<script type='text/javascript' src='<%=basePath %>js/custclue.js'></script>
  </body>
</html>