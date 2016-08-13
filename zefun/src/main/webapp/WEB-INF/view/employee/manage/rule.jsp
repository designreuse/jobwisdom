<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath %>css/money_system.css" type="text/css" />
<style>
  #stores{margin-left: 10px;
    width: 80px;
    padding: 0 0 0 30px;
    height: 24px;
    border-radius: 12px;
    border: 1px solid #b8b8b8;}


</style>

<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->
    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
    <%@ include file="/top.jsp" %>
	<div class="content_right clearfix">
     <div class="add_money">
	   <button onclick = "add()">新增</button>
	    <select  id="stores" onchange="changeStore()">
			  <c:forEach items="${selectByStoreAccount }" var="store">
			     <option storeId="${store.storeId }">${store.storeName }</option>
			  </c:forEach>
		 </select>
	 </div>
	 
	 <div class="add_money_table">
	   <table>
	     <tbody id="tab">
	     <tr>
	      <td>门店</td>
	      <td>制度名称</td>
	      <td>奖惩类型</td>
	      <td>金额</td>
		  <td>备注</td>
		  <td id="remove">操作</td>
		 </tr>

<%-- 		<c:forEach items="${ruleListByStoreId }" var="ruleListByStoreId"> --%>
<%-- 		  <tr id="${ruleListByStoreId.ruleId }"> --%>
<%-- 		  <td>${ruleListByStoreId.storeName }</td> --%>
<%-- 		  <td>${ruleListByStoreId.ruleName }</td> --%>
<%-- 		  <c:choose> --%>
<%-- 		   <c:when test="${ruleListByStoreId.processType =='1' }">   --%>
<!-- 		   <td>奖励</td>      -->
<%-- 		   </c:when> --%>
<%-- 		   <c:otherwise>  --%>
<!-- 		    <td>惩罚</td> -->
<%-- 		   </c:otherwise> --%>
<%-- 		</c:choose> --%>
<%-- 	      <td>${ruleListByStoreId.processMoney }</td> --%>
<%-- 		  <td>${ruleListByStoreId.ruleDesc }</td> --%>
<%-- 		  <td><img onclick = "updated(${ruleListByStoreId.storeId },${ruleListByStoreId.ruleId },${ruleListByStoreId.processType },'${ruleListByStoreId.ruleName }',${ruleListByStoreId.processMoney },'${ruleListByStoreId.ruleDesc }')" src="<%=basePath %>images/add_store_1.png"><img onclick="deleted(${ruleListByStoreId.ruleId })" src="<%=basePath %>images/add_store_2.png"></td> --%>
<!-- 		 </tr>  -->
<%-- 		</c:forEach> --%>
       
	   </tbody></table>
	 </div> 
	 
  </div>
    </div>
   </div>
   
   

   <div class="zzc hide">
   <div class="add_system">
    <p>新增制度</p>
	<div class="add_system_content">
	
	   <p>
	   	 <span id="store">所属门店 <select id="storeIdAll" >
			  <c:forEach items="${selectByStoreAccount }" var="store">
			     <option storeId="${store.storeId }">${store.storeName }</option>
			  </c:forEach>
		 </select>
		 </span> 
	   <span>制度名称<input name ="ruleName" type="text"></span> 
	
	 
	   <span>奖惩类型<select name = "processType"><option value = "1">奖励</option><option value = "2">惩罚</option></select>

	   </span><span style="margin-top:12px">金额<input  name = "processMoney"type="Number" style="margin-left:38px"></span></p>
	   <div class="system_saying clearfix">
	     <div class="saying">
		  备注
		 </div>
	     <div class="textarea1">
		  <textarea id= "ruleDesc"></textarea>
		 </div>
	   
	   </div>
	   <div class="money_system_button">
	     <button onclick= "save()">确定</button>
	     <button onclick= "hide()">取消</button>
	   </div>
	</div>
   
  </div>
</div>
</div>
</body>
<script>
var storeId = ${StoreId};
var ruleId= null;
var name = "";
var ruleLiss =  ${ruleListByStoreIds};



//门店登录 隐藏
jQuery(function(){ 
	if(storeId != 0){
		jQuery(".add_money").hide();
		jQuery("#remove").remove();
	}
	viewStore(ruleLiss);
})

//门店查询
function changeStore(){
	var storeid =jQuery("#stores option:selected").attr("storeId");
	jQuery.ajax({
		type : "post",
		url : baseUrl + "storeManageRule/view/store",
		data : "storeId="+storeid,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			
			jQuery("#tab tr:gt(0)").empty();
			viewStore(e.msg);
		}
	})
}


function add(){
	jQuery(".zzc").show();
}

function hide(){
	jQuery(".zzc").hide();
	jQuery("input[name='ruleName']").val("");
	jQuery("input[name='processMoney']").val("");
	jQuery("#ruleDesc").val("");
	jQuery("select[name='processType']").val(1);
	ruleId= null;
}

function updated(storeId,id,type,ruleName,processMoney,ruleDesc){
	ruleId= id;
	jQuery("input[name='ruleName']").val(ruleName);
	jQuery("input[name='processMoney']").val(processMoney);
	jQuery("#ruleDesc").val(ruleDesc);
	jQuery("select[name='processType']").val(type);
	
	var name =jQuery("#storeIdAll option[storeId='"+storeId+"']").val();
	jQuery("#storeIdAll").val(name);
	add();
}

function deleted(id){
 if(confirm("确定删除吗？")){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "storeManageRule/delete/home",
		data : "ruleId="+id,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			jQuery("#"+id).empty();
			dialog("删除成功");
		}
	})
	}
}


function save(){
	var storeId = jQuery("#storeIdAll option:selected").attr("storeId");
	var ruleName = jQuery("input[name='ruleName']").val();
	var processMoney = jQuery("input[name='processMoney']").val();
	var ruleDesc = jQuery("#ruleDesc").val();
	var processType = jQuery("select[name='processType']").val();
	
	var jsonData = "ruleName="+ruleName +"&processMoney=" + processMoney+"&ruleDesc=" + ruleDesc +"&processType=" + processType +"&storeId=" + storeId;
	if(ruleId != null){
		jsonData = "ruleName="+ruleName +"&processMoney=" + processMoney+"&ruleDesc=" + ruleDesc +"&processType=" + processType+"&ruleId=" + ruleId+"&storeId=" + storeId;
	}
    jQuery.ajax({
    	type : "post",
		url : baseUrl + "storeManageRule/save/home",
		data : jsonData ,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
			}
			
			var ruleListByStoreId = e.msg;
			name = jQuery("#storeIdAll").val();
			if(ruleId != null){
				
				jQuery("#"+ruleId).empty();
				ruleId = null;
			}
			viewHtml(ruleListByStoreId);
			hide();
		}
    })
}

function viewHtml(ruleListByStoreId){
		html =' <tr id="'+ruleListByStoreId.ruleId+'"" ><td>'+name+'</td><td>'+ruleListByStoreId.ruleName+'</td>';
		
		if(ruleListByStoreId.processType =='1'){
			html +='<td>奖励</td>';
		}
		else{
			html +=' <td>惩罚</td>';
		}
		html += ' <td>'+ruleListByStoreId.processMoney+'</td><td>'+ruleListByStoreId.ruleDesc+'</td>';
		html += '<td><img onclick = "updated('+ruleListByStoreId.storeId +','+ruleListByStoreId.ruleId +','+ruleListByStoreId.processType +',\''+ruleListByStoreId.ruleName +'\','+ruleListByStoreId.processMoney +',\''+ruleListByStoreId.ruleDesc +'\')" src="'+baseUrl+'images/add_store_1.png"><img onclick="deleted('+ruleListByStoreId.ruleId +')" src="'+baseUrl+'images/add_store_2.png"></td></tr>' 
		
	jQuery("#tab").append(html);
}


function viewStore(storeIds){
	var html ='';
	for (var i = 0; i < storeIds.length; i++) {
		var ruleListByStoreId = storeIds[i];
		html =' <tr id="'+ruleListByStoreId.ruleId+'"" ><td>'+ruleListByStoreId.storeName+'</td><td>'+ruleListByStoreId.ruleName+'</td>';
		
		if(ruleListByStoreId.processType =='1'){
			html +='<td>奖励</td>';
		}
		else{
			html +=' <td>惩罚</td>';
		}
		html += ' <td>'+ruleListByStoreId.processMoney+'</td><td>'+ruleListByStoreId.ruleDesc+'</td>';
		if(storeId == 0){
			html += '<td><img onclick = "updated('+ruleListByStoreId.storeId +','+ruleListByStoreId.ruleId +','+ruleListByStoreId.processType +',\''+ruleListByStoreId.ruleName +'\','+ruleListByStoreId.processMoney +',\''+ruleListByStoreId.ruleDesc +'\')" src="'+baseUrl+'images/add_store_1.png"><img onclick="deleted('+ruleListByStoreId.ruleId +')" src="'+baseUrl+'images/add_store_2.png"></td></tr>';
		}
		 
		jQuery("#tab").append(html);
	}
}

</script>
</html>


