<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/new_templet.css" type="text/css" />
<head>
</head>
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right clearfix">
					<p>
						<button>新增计划</button>
					</p>
					<div class="new_templet">
						<table>
							<tbody>
								<tr>
									<td>模板名称</td>
									<td>主题内容</td>
									<td>创建时间</td>
									<td>操作</td>

								</tr>
								<tr>
									<td>模板名称1好</td>
									<td style="width: 600px">主题内容主题内容主题内容主题内容主题内容主题内容主题内容主题内容</td>
									<td>2016-12-12</td>
									<td><img src="<%=basePath %>images/add_store_1.png"><img src="<%=basePath %>images/add_store_2.png"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="zzc">
		<div class="zzc_new_templet">
			<p>新建模板</p>
			<div class="zzc_new_templet_content clearfix">
				<div class="zzc_new_templet_content_left">
					<div class="left_content">
						<p>
							<span>模板名称</span><input type="text">
						</p>
						<p>
							<span>主题</span><input type="text">
						</p>
						<div class="plan_begin">
							<p>
								计划启动后<input type="text">天<input type="text">时 提醒
							</p>
							<p>
								做 <select><option></option></select> 服务
							</p>
						</div>
						<div class="plan_begin">
							<p>
								提前 <input type="text">天 <input type="text">时 服务
							</p>
						</div>
						<div class="message_content">
							<p>消息内容</p>
							<div class="message_content_">
								尊敬的｛会员名｝系统提示您；您需要在｛<a href="javascript:">服务时间</a>｝在｛<a href="javascript:">门店名称</a>｝进行｛<a href="javascript:">服务项目</a>｝服务，欢迎您的到来。
							</div>
						</div>

						<div class="phone_ask">
							是否短信通知<input type="checkbox">
							<button>
								添加<img src="<%=basePath%>images/new_templet.png">
							</button>

						</div>
					</div>

				</div>
				<div class="zzc_new_templet_content_right">
					<div class="zzc_new_templet_content_right_table">
						<table>
							<tbody>
								<tr>
									<td>主题</td>
									<td>服务时间</td>
									<td>服务项目</td>
									<td>提醒时间</td>
									<td>短信通知</td>
									<td>操作</td>
								</tr>
								<tr>
									<td>翻金花大赛</td>
									<td>2016.1.1</td>
									<td>洗剪吹</td>
									<td>10:00am</td>
									<td>是</td>
									<td><img src="<%=basePath%>images/add_store_1.png"><img src="<%=basePath%>images/add_store_2.png"></td>
								</tr>
								<tr>
									<td>翻金花大赛</td>
									<td>2016.1.1</td>
									<td>洗剪吹</td>
									<td>10:00am</td>
									<td>是</td>
									<td><img src="<%=basePath%>images/add_store_1.png"><img src="<%=basePath%>images/add_store_2.png"></td>
								</tr>
								<tr>
									<td>翻金花大赛</td>
									<td>2016.1.1</td>
									<td>洗剪吹</td>
									<td>10:00am</td>
									<td>是</td>
									<td><img src="<%=basePath%>images/add_store_1.png"><img src="<%=basePath%>images/add_store_2.png"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="zzc_new_templet_button">
				<button>保存</button>
				<button>取消</button>
			</div>
		</div>
	</div>
</body>
<script>
	function save() {

		var sendMemberType = jQuery("select[name='sendMemberType']").val();
		var memberId = jQuery("select[name='memberId']").val();
		var employeeId = jQuery("select[name='employeeId']").val();
		var theme = jQuery("input[name='theme']").val();
		var serviceTime = jQuery("input[name='serviceTime']").val();
		var serviceProjectName = jQuery("select[name='serviceProjectName']")
				.val();
		var topicTime = jQuery("input[name='topicTime']").val();
		var isSms = jQuery("input[name='isSms']:checked").val();
		var data = {
			"sId" : sId,
			"sendMemberType" : sendMemberType,
			"memberId" : memberId,
			"employeeId" : employeeId,
			"theme" : theme,
			"serviceTime" : serviceTime,
			"serviceProjectName" : serviceProjectName,
			"topicTime" : topicTime,
			"isSms" : isSms
		}
		jQuery.ajax({
			type : "post",
			url : baseUrl + "service/view/save",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(data) {
				if (data.code != 0) {
					dialog(data.msg);
				} else {
					if (sId != null) {
						jQuery("tr[sid=" + sId + "]").remove();
					}
					dialog("服务计划已生成");
					initHtml(data.msg);
					jQuery(".zzc").hide('800');
				}
			}
		});
	}
</script>
</html>