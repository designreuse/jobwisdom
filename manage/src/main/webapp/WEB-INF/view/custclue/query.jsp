<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/base.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
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
				<!-- <a href="" class="tip-bottom" data-original-title="销售管理">销售管理</a>-->
				<a href="#" class="current">销售线索</a>
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
						onclick="query()">搜索</a></span>
				</div>
			</div>
			<hr>

			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box">
						<div class="widget-title">
							<span class="icon"><i class="icon-th"></i></span>
							<h5>用户列表</h5>
						</div>
						<!-- <div &lt;!&ndash;id="toolbar"&ndash;&gt; class="pull-left">
                        &lt;!&ndash;<button type="button" class="btn btn-default">
                            <i class="icon-refresh"></i>
                        </button>
                        <button type="button" class="btn btn-default">
                            <i class=" icon-plus"></i>
                        </button>
                        <button type="button" class="btn btn-default">
                            <i class=" icon-trash"></i>
                        </button>&ndash;&gt;
                    </div>-->
                    <button type="button" class="btn btn-success" style="margin: 4px 4px;height: 30px;" data-toggle="modal"  onclick="getSelections()">
                        <i class="icon-share-alt">转入客户公海</i>
                    </button>
                    <button type="button" class="btn btn-success" style="margin: 4px 4px;height: 30px;" data-toggle="modal"  onclick="openmodal()">
                        <i class="icon-share-alt">新增线索</i>
                    </button>
                    <button type="button" class="btn btn-success" style="margin: 4px 4px;height: 30px;" data-toggle="modal"  onclick="goback()">
                        <i class="icon-share-alt">进入公海页面</i>
                    </button>
						<table data-toggle="table" data-method="post"
							data-url="<%=basePath%>custclue/queryCustClue"
							data-pagination="true" data-side-pagination="server"
							data-query-params="queryParams"
							data-page-list="[5, 10, 20, 50, 100, 200]" data-height="490"
							id="leadsMod1" data-toolbar="#toolbar">
							<thead>
								<tr>
									<th data-field="" data-checkbox="true"></th>
									<th data-field="clueId" data-visible="false"></th>
									<th data-field="createTime" data-align="right">录入时间</th>
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
									<th data-field="operate" data-sortable="true" data-formatter="operateFormatter"
                                        data-events="operateEvents">操作</th>
								</tr>
							</thead>
						</table>
						<!--</div>-->
					</div>
				</div>
			</div>

		</div>
	</div>

	<!-- 新增线索Modal -->
	<div id="addLeads" class="modal hide">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h5>新增线索</h5>
		</div>
		<div class="modal-body nopadding">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box" style="margin: 0px;">
						<div class="widget-content nopadding">
							<form action="#" class="form-horizontal">
								<div class="control-group">
									<label for="normal" class="control-label">店铺名称: </label>
									<div class="controls">
										<input type="text" id="add_customerName" class="span11"  />
									</div>
								</div>

								<div class="control-group">
									<label for="normal" class="control-label">选择区域</label>
									<div class="controls">
										<div class="controls-x1">
											<select name="province3" id="add_province" class="width20">
											</select>
										</div>
										<div class="controls-x3">
											<select name="city3" id="add_city" class="width20"></select>
										</div>
										<div class="controls-x3">
											<select name="area3" id="add_area" class="width20"></select>
										</div>
									</div>
								</div>
								<div class="control-group">
									<label for="normal" class="control-label">联系人姓名</label>
									<div class="controls">
										<div class="controls-x1">
											<input type="text" class="width20" id="add_principalName" />
										</div>
										职位
										<div class="controls-x3">
											<select class="width20" id="add_principalDuty">
												<option value="老板">老板</option>
												<option value="股东">股东</option>
												<option value="高管">高管</option>
												<option value="店长">店长</option>
											</select>
										</div>
										手机号
										<div class="controls-x3">
											<input type="text" id="add_phone" class="width20" />
										</div>
									</div>
								</div>
								<div class="control-group">
									<label for="normal" class="control-label">店铺属性</label>
									<div class="controls">
										<div class="controls-x1">
											<select class="width20" id="add_customerType">
												<option value="单店">单店</option>
												<option value="连锁店">连锁店</option>
											</select>
										</div>
										间数
										<div class="controls-x3">
											<input type="text" id="add_storeNumber" class="width20" />
										</div>
										品牌定位
										<div class="controls-x3">
											<select class="width20" id="add_brandLevel">
												<option value="高端">高端</option>
												<option value="中高端">中高端</option>
												<option value="中端">中端</option>
												<option value="中低端">中低端</option>
												<option value="低端">低端</option>
											</select>
										</div>
									</div>
								</div>

								<div class="control-group">
									<div class="span6">
										<label for="normal" class="control-label">跟进状态: </label>
										<div class="controls">
											<select name="" id="add_clueStatus" class="span11">
												<option value="未接触">未接触</option>
												<option value="已初访">已初访</option>
												<option value="有意向">有意向</option>
												<option value="已确定">已确定</option>
												<option value="已拒绝">已拒绝</option>
											</select>
										</div>
									</div>
									<div class="span6">
										<label for="normal" class="control-label">线索来源: </label>
										<div class="controls">
											<select name="" id="add_clueSource" class="span11">
												<option value="广告影响">广告影响</option>
												<option value="网络推广">网络推广</option>
												<option value="会议营销">会议营销</option>
												<option value="客户介绍">客户介绍</option>
												<option value="产品商介绍">产品商介绍</option>
												<option value="陌生拜访">陌生拜访</option>
											</select>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<a class="btn btn-primary btn-success" href="#"
				onclick="addsave()">保存</a> <a data-dismiss="modal" class="btn"
				href="#">取消</a>
		</div>
	</div>
	<!-- 修改线索Modal -->
	<div id="updateLeads" class="modal hide">
		<div class="modal-header">
			<button data-dismiss="modal" class="close" type="button">×</button>
			<h5>修改线索</h5>
		</div>
		<div class="modal-body nopadding">
			<div class="row-fluid">
				<div class="span12">
					<div class="widget-box" style="margin: 0px;">
						<div class="widget-content nopadding">
							<form action="#" class="form-horizontal">
								<div class="control-group">
									<label for="normal" class="control-label">店铺名称: </label>
									<div class="controls">
									<input type="hidden" id="update_clueId" class="span11"  />
										<input type="text" id="update_customerName" class="span11"  />
									</div>
								</div>

								<div class="control-group">
									<label for="normal" class="control-label">选择区域</label>
									<div class="controls">
										<div class="controls-x1">
											<select name="province2" id="update_province" class="width20">
											</select>
										</div>
										<div class="controls-x3">
											<select name="city2" id="update_city" class="width20"></select>
										</div>
										<div class="controls-x3">
											<select name="area2" id="update_area" class="width20"></select>
										</div>
									</div>
								</div>
								<div class="control-group">
									<label for="normal" class="control-label">联系人姓名</label>
									<div class="controls">
										<div class="controls-x1">
											<input type="text" class="width20" id="update_principalName" />
										</div>
										职位
										<div class="controls-x3">
											<select class="width20" id="update_principalDuty">
												<option value="老板">老板</option>
												<option value="股东">股东</option>
												<option value="高管">高管</option>
												<option value="店长">店长</option>
											</select>
										</div>
										手机号
										<div class="controls-x3">
											<input type="text" id="update_phone" class="width20" />
										</div>
									</div>
								</div>
								<div class="control-group">
									<label for="normal" class="control-label">店铺属性</label>
									<div class="controls">
										<div class="controls-x1">
											<select class="width20" id="update_customerType">
												<option value="单店">单店</option>
												<option value="连锁店">连锁店</option>
											</select>
										</div>
										间数
										<div class="controls-x3">
											<input type="text" id="update_storeNumber" class="width20" />
										</div>
										品牌定位
										<div class="controls-x3">
											<select class="width20" id="update_brandLevel">
												<option value="高端">高端</option>
												<option value="中高端">中高端</option>
												<option value="中端">中端</option>
												<option value="中低端">中低端</option>
												<option value="低端">低端</option>
											</select>
										</div>
									</div>
								</div>

								<div class="control-group">
									<div class="span6">
										<label for="normal" class="control-label">跟进状态: </label>
										<div class="controls">
											<select name="" id="update_clueStatus" class="span11">
												<option value="未接触">未接触</option>
												<option value="已初访">已初访</option>
												<option value="有意向">有意向</option>
												<option value="已确定">已确定</option>
												<option value="已拒绝">已拒绝</option>
											</select>
										</div>
									</div>
									<div class="span6">
										<label for="normal" class="control-label">线索来源: </label>
										<div class="controls">
											<select name="" id="update_clueSource" class="span11">
												<option value="广告影响">广告影响</option>
												<option value="网络推广">网络推广</option>
												<option value="会议营销">会议营销</option>
												<option value="客户介绍">客户介绍</option>
												<option value="产品商介绍">产品商介绍</option>
												<option value="陌生拜访">陌生拜访</option>
											</select>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<a class="btn btn-primary btn-success" href="#"
				onclick="updatesave()">保存</a> <a data-dismiss="modal" class="btn"href="#">取消</a>
		</div>
	</div>


<!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
	<script type="text/javascript">
	new PCAS("province3","city3","area3");
	new PCAS("province2","city2","area2");
	var baseUrl = "<%=basePath %>";
	function goback(){
		location.href=encodeURI(baseUrl +"custclue/view/publicquery"); 
	}
	function operateFormatter(value, row, index) {
		  return [
		    '<a class="like" style="color: blue;" href="#projectMod" data-toggle="modal" title="修改"><i class="icon-pencil"></i></a> ',
		    '<a class="remove" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
		  ].join('');
		}
		window.operateEvents = {
				  'click .like': function (e, value, row, index) {
					  $('#updateLeads').modal();
					  $('#update_clueId').val(row.clueId);
					  $('#update_customerName').val(row.customerName);
					  $('#update_province').val(row.province);
					  $('#update_province').change();
					  $('#update_city').val(row.city);
					  $('#update_city').change();
					  $('#update_area').val(row.area);
					  $('#update_principalName').val(row.principalName);
					  $('#update_principalDuty').val(row.principalDuty);
					  $('#update_phone').val(row.phone);
					  $('#update_customerType').val(row.customerType);
					  $('#update_storeNumber').val(row.storeNumber);
					  $('#update_brandLevel').val(row.brandLevel);
					  $('#update_clueStatus').val(row.clueStatus);
					  $('#update_clueSource').val(row.clueSource);
				  },
				  'click .remove': function (e, value, row, index) {
					    //alert('You click like action, row: ' + JSON.stringify(row));
					    if(confirm("确认要删除该条客户线索信息吗？")){ 
					    	 $.ajax({
					             type: "POST",
					             dataType: "json",
					             url: baseUrl+'custclue/deleteClue',
					             data:'clueId='+row.clueId,
					             success: function (result) {
					                 //加载最大可退金额
					                 if(result.code==0){
					                	 alert("删除成功！");
					                	 $('#leadsMod1').bootstrapTable('refresh', null);
					                 }else{
					                	 alert("删除失败！");
					                 }
					             }
					         });
					    	return;
					    	}
					  }
				};

</script>
<script type='text/javascript' src='<%=basePath %>js/custclue.js'></script>

</body>
</html>