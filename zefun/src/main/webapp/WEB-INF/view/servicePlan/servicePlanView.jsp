<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/new_plan.css" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
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
						<button onclick="add()">新增计划</button>
					</p>
					<div class="new_plan_plan">
						<table>
							<tbody>
								<tr>
									<td>会员/会员分组</td>
									<td>跟踪人</td>
									<td>主题</td>
									<td>服务项目</td>
									<td>服务时间</td>
									<td>提醒时间</td>
									<td>状态</td>
									<td>操作</td>
								</tr>
								<c:forEach items="${servicePlanInfos }" var="servicePlanInfo">
									<tr sendMemberType="${servicePlanInfo.sendMemberType }" memberId="${servicePlanInfo.memberId }" 
										employeeId="${servicePlanInfo.employeeId }" theme="${servicePlanInfo.theme }" sId="${servicePlanInfo.sId }"
										serviceTime="${servicePlanInfo.serviceTime }"  serviceProjectName="${servicePlanInfo.serviceProjectName }" 
										topicTime="${servicePlanInfo.topicTime }" isSms="${servicePlanInfo.isSms }">
										
										<td>${servicePlanInfo.memberName }</td>
										<td>${servicePlanInfo.name }</td>
										<td>${servicePlanInfo.theme }</td>
										<td>${servicePlanInfo.serviceProjectName }</td>
										<td>${servicePlanInfo.serviceTime }</td>
										<td>${servicePlanInfo.topicTime }</td>
										<c:if test="${servicePlanInfo.isDeleted == 0 }"><td><span class="progressing">进行中</span></td></c:if>
										<c:if test="${servicePlanInfo.isDeleted == 1 }"><td><span class="lose">已失效</span></td></c:if>
										<td><img src="<%=basePath%>images/add_store_1.png" onclick="showUpdate(this)"><img src="<%=basePath%>images/add_store_2.png" onclick="deleted(${servicePlanInfo.sId })"></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div> 
	</div>

	<div class="zzc" style="display: none">
		<div class="zzc_new_plan">
			<p>新增计划</p>
			<div class="zzc_new_plan_content">
				<div class="new_plan_content_select">
					<span>
						  <select name="sendMemberType" onchange="initMemberIds(this.value)"><option value="1">会员等级</option><option value="2">会员分组</option></select></span> 
						  <select name="memberId"></select>
						  <span>跟踪人<select name="employeeId" style="margin-left: 15px"><c:forEach items="${employeeInfos }" var="employeeInfo"><option value="${employeeInfo.employeeId }">${employeeInfo.name }</option></c:forEach></select></span>
				</div>
				<div class="select_model">
					<p>
						是否从模版选择<input type="checkbox">
					</p>
					<ul class="clearfix">
						<li class="active">会员模板</li>
						<li>会员模板</li>
						<li>会员模板</li>
						<li>会员模板</li>
						<li>会员模板</li>
						<li>会员模板</li>
						<li>会员模板</li>
					</ul>
				</div>

				<div class="plan_theme">
					<p>
						<span><em>主题</em><input name="theme" maxlength="8" type="text" style="width: 562px"></span>
					</p>
					<p>
						<span><em>服务时间</em><input name="serviceTime" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
						<span><em>服务项目</em><select name="serviceProjectName"><c:forEach items="${projectInfos }" var="projectInfo"><option value="${projectInfo.projectName }">${projectInfo.projectName }</option></c:forEach></select></span>
						<span><em>提醒时间</em><input name="topicTime" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH'})"></span>
					</p>
					<div class="message_content clearfix">
						<p>消息内容:</p>
						<div>
							尊敬的<a href="javascript:;">｛会员名｝</a>系统提示您；您需要在<a href="javascript:;">｛服务时间｝</a>在<a href="javascript:;">｛门店名称｝</a>进行<a href="javascript:;">｛服务项目｝</a>服务，欢迎您的到来。
						</div>
					</div>
				</div>
				<div class="message_ask">
					短信通知<span><input name="isSms" type="radio" value="1" checked="checked">是</span><span><input name="isSms" type="radio" value="0">否</span>
				</div>

				<div class="new_plan_button">
					<button onclick="save()">确认</button>
					<button onclick="jQuery('.zzc').hide('800');sId=null;">取消</button>
				</div>
			</div>
		</div>
	</div>

</body>
<script>
var level = eval('('+'${level}'+')');
var screen = eval('('+'${screen}'+')');
var sId = null;
jQuery(function (){
	initMemberIds(1);
})
function initMemberIds(type){
	jQuery("select[name='memberId']").empty();
	if (type == 1){
		for (var i = 0; i < level.length; i++) {
			var html = '<option value='+level[i].levelId+'>'+level[i].levelName+'</option>';
			jQuery("select[name='memberId']").append(jQuery(html));
		}
	}
	if (type == 2){
		for (var i = 0; i < screen.length; i++) {
			var html = '<option value='+screen[i].screeningId+'>'+screen[i].screeningName+'</option>';
			jQuery("select[name='memberId']").append(jQuery(html));
		}
	}
}
function add(){
	jQuery(".zzc").show('800');
	sId = null;
}

function save(){
	
	var sendMemberType = jQuery("select[name='sendMemberType']").val();
	var memberId = jQuery("select[name='memberId']").val();
	var employeeId = jQuery("select[name='employeeId']").val();
	var theme = jQuery("input[name='theme']").val();
	var serviceTime = jQuery("input[name='serviceTime']").val();
	var serviceProjectName = jQuery("select[name='serviceProjectName']").val();
	var topicTime = jQuery("input[name='topicTime']").val();
	var isSms = jQuery("input[name='isSms']:checked").val();
	var data = {"sId":sId, "sendMemberType":sendMemberType, "memberId":memberId, "employeeId":employeeId, 
		"theme":theme, "serviceTime":serviceTime, "serviceProjectName":serviceProjectName, "topicTime":topicTime,
		"isSms":isSms}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "service/view/save",
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
		async : false,
		success : function(data) {
			if (data.code!=0){
				dialog(data.msg);
			}
			else {
				if (sId!=null){
					jQuery("tr[sid="+sId+"]").remove();
				}
				dialog("服务计划已生成");
				initHtml(data.msg);
				jQuery(".zzc").hide('800');
			}
		}
	});
}
function initHtml(service){
	var sendMemberType = service.sendMemberType;
	var memberId = service.memberId;
	var employeeId = service.employeeId;
	var theme = service.theme;
	var serviceTime = service.serviceTime;
	var serviceProjectName = service.serviceProjectName;
	var topicTime = service.topicTime;
	var isSms = service.isSms;
	var memberName = service.memberName;
	var employeeName = service.name;
	var html = '<tr sendmembertype="'+sendMemberType+'" memberid="'+memberId+'" employeeid="'+employeeId+'" theme="'+theme+'" '+ 
					'sid="'+service.sId+'" servicetime="'+serviceTime+'" serviceprojectname="'+serviceProjectName+'" topictime="'+topicTime+'" issms="'+isSms+'">'+
					'<td>'+memberName+'</td>'+
					'<td>'+employeeName+'</td>'+
					'<td>'+theme+'</td>'+
					'<td>'+serviceProjectName+'</td>'+
					'<td>'+serviceTime+'</td>'+
					'<td>'+topicTime+'</td>'+
					'<td><span class="progressing">运行中</span></td>'+
					'<td><img src="'+baseUrl+'images/add_store_1.png" onclick="showUpdate(this)"><img src="'+baseUrl+'images/add_store_2.png" onclick="deleted('+service.sId+')"></td>'+
				'</tr>';
	jQuery("tbody").append(jQuery(html));
}

function showUpdate(li){
	sId = jQuery(li).parents("tr").attr("sId");
	var sendMemberType = jQuery(li).parents("tr").attr("sendMemberType");
	var memberId = jQuery(li).parents("tr").attr("memberId");
	var employeeId = jQuery(li).parents("tr").attr("employeeId");
	var theme = jQuery(li).parents("tr").attr("theme");
	var serviceTime = jQuery(li).parents("tr").attr("serviceTime");
	var serviceProjectName = jQuery(li).parents("tr").attr("serviceProjectName");
	var topicTime = jQuery(li).parents("tr").attr("topicTime");
	var isSms = jQuery(li).parents("tr").attr("isSms");
	
	jQuery("select[name='sendMemberType']").val(sendMemberType);
	initMemberIds(sendMemberType);
	jQuery("select[name='memberId']").val(memberId);
	jQuery("select[name='employeeId']").val(employeeId);
	jQuery("input[name='theme']").val(theme);
	jQuery("input[name='serviceTime']").val(serviceTime);
	jQuery("select[name='serviceProjectName']").val(serviceProjectName);
	jQuery("input[name='topicTime']").val(topicTime);
	jQuery("input[name='isSms'][value="+isSms+"]").click();
	
	jQuery(".zzc").show('800');
}

function deleted(sIds){
	if(confirm("确定要清空该服务计划 ？")){
		jQuery.ajax({
			type : "post",
			url : baseUrl + "service/view/delete",
			data : "sId="+sIds,
			dataType : "json",
			async : false,
			success : function(data) {
				if(data.code!=-1){
					dialog("该服务计划已删除,将不会出现计划列表中");
					jQuery("tr[sid="+sIds+"]").remove();
				}
			}
		});
	}
}
</script>
</html>