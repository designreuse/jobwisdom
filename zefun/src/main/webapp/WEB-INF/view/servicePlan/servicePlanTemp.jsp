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
						<button onclick="jQuery('.zzc').show('800')">新增模板</button>&nbsp;&nbsp;&nbsp;<button onclick="location.href='<%=basePath%>service/view/view'">新增计划</button>
					</p>
					<div class="new_templet">
						<table>
							<tbody>
								<tr>
									<td>模板名称</td>
									<td>主题内容</td>
									<td>提醒时间</td>
									<td>操作</td>
								</tr>
								<c:forEach items="${servicePlanTemps }" var="servicePlanTemp" >
									<tr tId="${servicePlanTemp.tId }" tempName="${servicePlanTemp.tempName }"  theme="${servicePlanTemp.theme }"  topicDay="${servicePlanTemp.topicDay }" 
										topicHoure="${servicePlanTemp.topicHoure }"  serviceProjectName="${servicePlanTemp.serviceProjectName }"  serviceDay="${servicePlanTemp.serviceDay }"  
										serviceHoure="${servicePlanTemp.serviceHoure }" isSms="${servicePlanTemp.isSms }" >
										
										<td>${servicePlanTemp.tempName }</td>
										<td>${servicePlanTemp.theme }</td>
										<td>计划启动后,${servicePlanTemp.topicDay }天${servicePlanTemp.topicHoure }小时后, 进行推送</td>
										<td><img onclick="showUpdate(this)" src="<%=basePath %>images/add_store_1.png"><img onclick="deleted(${servicePlanTemp.tId }, this)" src="<%=basePath %>images/add_store_2.png"></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="zzc" style="display: none;">
		<div class="zzc_new_templet">
			<p>新建模板</p>
			<div class="zzc_new_templet_content clearfix">
				<div class="zzc_new_templet_content_left">
					<div class="left_content">
						<p>
							<span>模板名称</span><input name="tempName" type="text">
						</p>
						<p>
							<span>主题</span><input name="theme" type="text">
						</p>
						<div class="plan_begin">
							<p>
								计划启动后<input type="number" name="topicDay">天<input type="number" name="topicHoure">后提醒
							</p>
							<p>
								做<select name="serviceProjectName"><c:forEach items="${projectInfos }" var="projectInfo"><option value="${projectInfo.projectName }">${projectInfo.projectName }</option></c:forEach></select> 服务
							</p>
						</div>
						<div class="plan_begin">
							<p>
								将在 <input type="number" name="serviceDay" >天 <input type="number" name="serviceHoure" >后服务
							</p>
						</div>
						<div class="message_content">
							<p>消息内容</p>
							<div class="message_content_">
								尊敬的｛会员名｝系统提示您；您需要在｛<a href="javascript:">服务时间</a>｝在｛<a href="javascript:">门店名称</a>｝进行｛<a href="javascript:">服务项目</a>｝服务，欢迎您的到来。
							</div>
						</div>

						<div class="phone_ask">
							是否短信通知<input type="checkbox" value="1" checked="checked" name="isSms" onclick="isSms(this)" >
							<button onclick="addHtml()">
								添加<img src="<%=basePath%>images/new_templet.png">
							</button>

						</div>
					</div>

				</div>
				<div class="zzc_new_templet_content_right">
					<div class="zzc_new_templet_content_right_table">
						<table>
							<tbody id="data">
								<tr>
									<td>主题</td>
									<td>服务时间</td>
									<td>服务项目</td>
									<td>提醒时间</td>
									<td>短信通知</td>
									<td>操作</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="zzc_new_templet_button">
				<button onclick="save()">保存</button>
				<button onclick="tId=null;jQuery('.zzc').hide('800');">取消</button>
			</div>
		</div>
	</div>
</body>
<script>
	var tId = null;
	function showUpdate(image){
		tId = jQuery(image).parents("tr").attr("tId");
		var tempName = jQuery(image).parents("tr").attr("tempName");
		var theme = jQuery(image).parents("tr").attr("theme");
		var topicDay = jQuery(image).parents("tr").attr("topicDay");
		var topicHoure = jQuery(image).parents("tr").attr("topicHoure");
		var serviceProjectName = jQuery(image).parents("tr").attr("serviceProjectName");
		var serviceDay = jQuery(image).parents("tr").attr("serviceDay");
		var serviceHoure = jQuery(image).parents("tr").attr("serviceHoure");
		var isSms = jQuery(image).parents("tr").attr("isSms");
		
		jQuery("input[name='tempName']").val(tempName);
		jQuery("input[name='theme']").val(theme);
		jQuery("input[name='topicDay']").val(topicDay);
		jQuery("input[name='topicHoure']").val(topicHoure);
		jQuery("select[name='serviceProjectName']").val(serviceProjectName);
		jQuery("input[name='serviceDay']").val(serviceDay);
		jQuery("input[name='serviceHoure']").val(serviceHoure);
		if (jQuery("input[name='isSms']").val() != isSms){
			jQuery("input[name='isSms']").click();
		}
		jQuery(".zzc").show('800');
	}
	
	
	function save() {
		var tr = jQuery("#data").children("tr");
		var data = [];
		for (var i = 1; i < tr.length; i++) {
			var td = jQuery("#data").children("tr").eq(i);
			var tIds = td.attr("tId");
			var tempName = td.attr("tempName");
			var theme = td.attr("theme");
			var topicDay = td.attr("topicDay");
			var topicHoure = td.attr("topicHoure");
			var serviceProjectName = td.attr("serviceProjectName");
			var serviceDay = td.attr("serviceDay");
			var serviceHoure = td.attr("serviceHoure");
			var isSms = td.attr("isSms");
			var da = {
					"tId" : tIds,
					"tempName" : tempName,
					"theme" : theme,
					"topicDay" : topicDay,
					"topicHoure" : topicHoure,
					"serviceProjectName" : serviceProjectName,
					"serviceDay" : serviceDay,
					"serviceHoure" : serviceHoure,
					"isSms" : isSms
				}
			data.push(da);
		}
		console.log(data);
		var strData = {"data":data};
		jQuery.ajax({
			type : "post",
			url : baseUrl + "service/save/temp",
			data : JSON.stringify(strData),
			dataType : "json",
			contentType : "application/json",
			async : false,
			beforeSend : function(){
				console.log("模板生效中...");
			},
			complete : function(){
				console.log("模板以生效...");
			},
			success : function(data) {
				jQuery(".zzc").hide('500');
				if (data.code!=-1){
					dialog("模板以生效, 请在计划中使用该模板进行推送");
				}else {
					dialog(data.msg);
				}
				window.location.href = baseUrl + "service/view/temp";
			}
		});
		tId = null;
	}
	
	function addHtml(){
		var tempName = jQuery("input[name='tempName']").val();
		var theme = jQuery("input[name='theme']").val();
		var topicDay = jQuery("input[name='topicDay']").val();
		var topicHoure = jQuery("input[name='topicHoure']").val();
		var serviceProjectName = jQuery("select[name='serviceProjectName']").val();
		var serviceDay = jQuery("input[name='serviceDay']").val();
		var serviceHoure = jQuery("input[name='serviceHoure']").val();
		var isSms = jQuery("input[name='isSms']").val();
		var html = '<tr  tId="'+tId+'" tempName="'+tempName+'"  theme="'+theme+'"  topicDay="'+topicDay+'" '+
						'topicHoure="'+topicHoure+'"  serviceProjectName="'+serviceProjectName+'"  serviceDay="'+serviceDay+'"  '+
						'serviceHoure="'+serviceHoure+'" isSms="'+isSms+'">'+
				'<td>'+tempName+'</td>'+
				'<td>计划启动后,'+topicDay+'天'+topicHoure+'小时后,提醒</td>'+
				'<td>'+serviceProjectName+'</td>'+
				'<td>计划启动后,'+serviceDay+'天'+serviceHoure+'小时后,服务</td>';
		if (isSms == 1){
			html += '<td>否</td>'+
						'<td><img onclick="tId='+tId+'\;showUpdate(this)\;jQuery(this).parents(\'tr\').remove()" src="<%=basePath%>images/add_store_1.png"><img onclick="jQuery(this).parents(\'tr\').remove()" src="<%=basePath%>images/add_store_2.png"></td>'+
					'</tr>';
		}else {
			html += '<td>否</td>'+
						'<td><img onclick="tId='+tId+'\;showUpdate(this)\;jQuery(this).parents(\'tr\').remove()" src="<%=basePath%>images/add_store_1.png"><img onclick="jQuery(this).parents(\'tr\').remove()" src="<%=basePath%>images/add_store_2.png"></td>'+
					'</tr>';
		}
		jQuery("tbody").eq(1).append(jQuery(html));
		clearInput();		
	}
	
	function deleted(tIds, img){
		if(confirm("确定要删除该服务模板 ？")){
			jQuery.ajax({
				type : "post",
				url : baseUrl + "service/view/temp/delete",
				data : "tId="+tIds,
				dataType : "json",
				async : false,
				success : function(data) {
					if(data.code!=-1){
						dialog("该服务模板已删除,将不会出现计划列表中");
						jQuery(img).parents("tr").remove();
					}
				}
			});
		}
	}
	
	function isSms(check){
		if (jQuery(check).val() == 1){
			jQuery(check).val('0');
		}
		else {
			jQuery(check).val('1');
		}
	}
	
	function clearInput(){
		tId = null;
		jQuery("input[name='tempName']").val('');
		jQuery("input[name='theme']").val('');
		jQuery("input[name='topicDay']").val('');
		jQuery("input[name='topicHoure']").val('');
		jQuery("input[name='serviceDay']").val('');
		jQuery("input[name='serviceHoure']").val('');
	}
	
</script>
</html>