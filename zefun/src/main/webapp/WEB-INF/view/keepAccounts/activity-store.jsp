<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/create_store_activity.css" type="text/css" />
<script type="text/javascript"	src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
   <script>
jQuery(function(){
  jQuery('input[type="checkbox"]').click(function(){
   if(jQuery(this).is(':checked')){
           jQuery(this).parent().addClass('active')
   }
   else{
   jQuery(this).parent().removeClass('active')
    }
  })
})
</script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				
				<div class="content_right clearfix">
		 <p><button onclick="jQuery('.zzc').show()">新增计划</button></p>
		 <div class="new_plan_plan">
		    <table id="tables">
		       <tbody><tr>
			      <td>活动名称</td>
				  <td style="width:240px">有效期</td>
				  <td style="width:450px">简述</td>
				  <td>状态</td>
				  <td>操作</td>
				  
			   </tr>
			   <c:forEach items="${page.results}" var="ativityStore">
			    <tr id="${ativityStore.activityStoreId }" name="trs">
			      <td>${ativityStore.activityStoreName }</td>
				  <td>${ativityStore.statusTime }至${ativityStore.sotpTime }</td>
				  <td>${ativityStore.note }</td>
				  <c:if test="${ativityStore.status eq 1}"><td><em class="online">在线</em></td></c:if>
				  <c:if test="${ativityStore.status eq 2}"><td><em class="no_begin">未开始</em></td></c:if>
				  <c:if test="${ativityStore.status eq 3}"><td><em class="over">结束</em></td></c:if>
				  <td><img onclick="deleted(${ativityStore.activityStoreId })" src="<%=basePath%>images/architecture_delete.png"></td>
			   </tr>
			   </c:forEach>
		  
		   </tbody>
		 </table>
	
		</div>
			<%@ include file="/template/page.jsp"%>	
	  </div>
			
			</div>
		</div>
	</div>
	
	<div class="zzc" style="display: none;">
  	 <div class="zzc_create_store_activity">
      <p>创建门店活动</p>
      <div class="zzc_create_store_activity_content">
	      <p>
		    <span>活动名称<input type="text" name="activityStoreName" style="width:180px"></span>
		    <span style="position:relative;left:24px">有效期<input name="statusTime" type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" >至<input onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"  name="sotpTime" type="text"></span>
		  </p>
	      <div class="clearfix textarea_content">
		     <div class="textarea_saying">简述</div>
		     <div class="textarea">
			    <textarea name="note"></textarea>
			 </div>
		  </div>
		  
		  <div class="join_store">
		    <p>活动的门店</p>
		    <ul class="clearfix" id="clearfix">
		    	<c:forEach items="${storeInfos }" var="storeInfos">
		    		   <li><input onclick="listen(this)" name="${storeInfos.storeId }" type="checkbox">${storeInfos.storeName }</li>
		    	</c:forEach>
		
              	<li><button class="buc" onclick="checkboxs(this)" id="1">全选</button></li>					   
			</ul>
		  
		  </div>
	     <div class="clearfix textarea_content_">
		     <div class="textarea_saying_">描述</div>
		     <div class="textarea_">
			    <textarea name="notes"></textarea>
			 </div>
		  </div>
	  </div>
	  <div class="zzc_create_store_activity_button">
	    <button onclick="save()">确定</button>
		<button onclick="jQuery('.zzc').hide()">取消</button>
	  </div>
   </div>
	</div>
</body>
<script>

 
 
function  deleted(activityStoreId){
	 	jQuery.ajax({
			type : "post",
			url : baseUrl +"activity/view/showactivitydelete",
			data : 	"activityStoreId="+activityStoreId,
			dataType : "json",
			success : function(e){
				dialog(" 删除成功");
				jQuery("tr[id='"+activityStoreId+"']").attr("style","display: none;")
			}
		});
	
	}

function save (){
	var activityStoreName = jQuery("input[name='activityStoreName'").val();
	var statusTime = jQuery("input[name='statusTime']").val();
	var sotpTime = jQuery("input[name='sotpTime']").val();
	var note = jQuery("textarea[name='note']").val();
	var notes = jQuery("textarea[name='notes']").val();
	var storeId=[];
	var li = jQuery("#clearfix li");
	for (var i = 0; i < li.length; i++) {
		 if(jQuery(li).eq(i).find("input[type='checkbox']").is(':checked')){
			 storeId.push(jQuery(li).eq(i).find("input[type='checkbox']").attr("name"))
		 }
	}
	if (storeId.length == 0) {
		dialog("门店不能一个不选");
		return ;
	}
	if (isEmpty(activityStoreName)) {
		dialog("活动名称不能为空");
		return ;
	}
	if (isEmpty(sotpTime)) {
		dialog("结束时间不能为空");
		return ;
	}
	if (isEmpty(statusTime)) {
		dialog("开始时间不能为空");
		return ;
	}
	if (isEmpty(note)) {
		dialog("简述不能为空");
		return ;
	}
	
	if(daysBetween(sotpTime,statusTime)<=0){
		dialog("结束时间不能小于开始时间");
		return ;
	}
	var createdate = newdates();
	if(daysBetween(createdate,statusTime)>=0){
		dialog("开始日期不能是过去的日期");
		return ;
	}
	
	var data = {
		"activityStoreName" : activityStoreName, "statusTime" : statusTime, "sotpTime" : sotpTime, "note" : note, "notes" : notes,
		"storeId" : storeId.toString() 
	}
 	jQuery.ajax({
		type : "post",
		url : baseUrl +"activity/view/showactivitysave",
		data : JSON.stringify(data),
		contentType : "application/json",
		dataType : "json",
		success : function(e){
			dialog("保存成功");
			location.href = baseUrl+"activity/view/showactivitysign";
		}
	});
}

function checkboxs(type){
	 var button = jQuery(type).attr("id");
	 var li = jQuery("#clearfix li");
	 if(button==1){
		 for (var int = 0; int < li.length; int++) {
			 if(!jQuery(li).eq(int).find("input[type='checkbox']").is(':checked')){
				 jQuery(li).eq(int).find("input[type='checkbox']").click();
			 }
		}
		 jQuery(type).attr("id",2);
		 jQuery(type).text("全不选");
	 }
	 if(button==2){
		 for (var int = 0; int < li.length; int++) {
			 if(jQuery(li).eq(int).find("input[type='checkbox']").is(':checked')){
				 jQuery(li).eq(int).find("input[type='checkbox']").click();
			 }
		}
		 jQuery(type).attr("id",1);
		 jQuery(type).text("全选");
	 }
}
function listen(input){
	var inputs = jQuery(input).parent().siblings().find("input[type='checkbox']").length;
	var inputsChecks = jQuery(input).parent().siblings().find("input[type='checkbox']:checked").length;
	if (inputs == inputsChecks) {
		 jQuery(".buc").attr("id",2);
		 jQuery(".buc").text("全不选");
	}else{
		 jQuery(".buc").attr("id",1);
		 jQuery(".buc").text("全选");
	}
}
var changes = false;
function daysBetween(DateOne,DateTwo)  
{   
    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
  
    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
  
    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
    return cha;  
}  


//分页
function changePage() {
	var datas = "pageNo=" + pageNo;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "activity/view/showactivitysign",
		data : datas,
		dataType : "json",
		success : function(e) {
			pageNo = e.msg.pageNo;
			pageSize = e.msg.pageSize;
			totalPage = e.msg.totalPage;
			totalRecord = e.msg.totalRecord;
			if (changes) {
				changes = false;
				unbuildPagination();
			}
			jQuery("#tables [name='trs']").empty();
			jQuery.each(e.msg.results, function(n, value) {
				var html = "<tr id='"+value.activityStoreId +"' name='trs'>"+
				"<tr id='"+value.activityStoreId +"' name='trs'>"+
				"<td>"+value.activityStoreName+"</td> "+
				"<td>"+value.statusTime +"至"+value.sotpTime +"</td>"+
				"<td>"+value.note+"</td> ";
				if(value.status == 1){
					  html += "<td><em class='online'>在线</em></td>"
				}
				if(value.status == 2){
				  html += "<td><em class='no_begin'>未开始</em></td>"
				}
				if(value.status == 3){
				  html += "<td><em class='over'>结束</em></td></td>"
				}
				html += "<td><img onclick='deleted("+value.activityStoreId+")' src='"+baseUrl+"images/architecture_delete.png'></td></tr>";
		
				jQuery("#tables").append(jQuery(html));
			});
		}
	});
}
function newdates(){
	var myDate = new Date();
	myDate.getFullYear();    //获取完整的年份(4位,1970-????)
	myDate.getMonth();       //获取当前月份(0-11,0代表1月)
	myDate.getDate();        //获取当前日(1-31)
	return myDate.getFullYear()+"-"+(myDate.getMonth()+1)+"-"+myDate.getDate();  
}
//dialog('msg');
</script>
</html>