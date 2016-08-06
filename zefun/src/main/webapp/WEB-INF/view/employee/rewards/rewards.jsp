<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath %>css/reward.css" type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>
<script>
  jQuery(function(){
    jQuery('.wages_content:gt(0)').hide();
    jQuery('.content_right ul li').click(function(){
      jQuery(this).addClass('active').siblings().removeClass('active');
	  jQuery('.wages_content').eq(jQuery(this).index()).show().siblings('.wages_content').hide();

	})
  })
  </script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper"
		style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel"
				style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
<div class="content_right clearfix">
    <ul class="clearfix">
	  <li class="active">奖惩管理</li>
	  <li class="">奖惩明细</li> 
	</ul>
	<div class="wages_content" >
	  <div class="wages_content_datail">
			<div class="wages_content_datail_top">
			 时间查询
			  <input type="text" style="width:100px;margin:0 10px" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})">
			  <select id="storeId1">
			     <option storeId="0">全部门店</option>
			  <c:forEach items="${selectByStoreAccount }" var="store">
			     <option storeId="${store.storeId }">${store.storeName }</option>
			  </c:forEach>
			  </select>
			</div>
	  </div>
	 <div class="wages_content_datail_table clearfix">
	  <div class="table_left">
	   <table id="teb1">
	      <tbody>
	      <tr>
		    <td style="height:85px">工号</td>
		    <td style="height:85px">姓名</td>
		  </tr>
	
		
	   </tbody></table>
	  </div>
	  <div class="table_right">
	   <div class="table_right_head">
		   <table id="teb2">  
			 <tbody>
			 <tr>
			   <td rowspan="2">合计</td>
			   <td class = "rule1" colspan="3">奖励类别</td>
				<td rowspan="2">奖励合计</td>
				<td class = "rule2" colspan="3">奖惩类别</td>
				<td rowspan="2">惩罚合计</td>	
			  </tr>
			  <tr>
				 <c:forEach items="${ruleList1 }" var="ruleList">
					<td>${ruleList.ruleName }</td>
			    </c:forEach>
			     <c:forEach items="${ruleList2 }" var="ruleList">
					<td>${ruleList.ruleName }</td>
			    </c:forEach>
			  </tr>
		   </tbody></table>
		 </div>
     	 	
	  </div>    
	 </div>
		
	</div>
	
	<div class="wages_content third" style="display: block;">
	  <div class="wages_content_datail" style="padding:0">
		<table class="second_table">
		  <tbody><tr>
		    <td rowspan="2"><button onclick="showView()">新增</button></td>
			<td>开始时间</td>
			<td>结束时间</td>
			<td>所属门店</td>
			<td>奖惩名称</td>
			<td>奖惩类别</td>
			<td>选择员工</td>
			<td rowspan="2"><button>搜索</button></td>
		  </tr>
		   <tr>
			<td><input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
			<td><input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
			<td>	
			  <select id="storeId2">
			     <option storeId=" ">全部门店</option>
			  <c:forEach items="${selectByStoreAccount }" var="store">
			     <option storeId="${store.storeId }">${store.storeName }</option>
			  </c:forEach>
			  </select>
			 </td>
			<td>  <select name="storeManageRule">
         	    <option ruleId ="0">全部</option>
			  <c:forEach items="${storeManageRule }" var="storeRule">
			     <option ruleId="${storeRule.ruleId }">${storeRule.ruleName }</option>
			  </c:forEach>
         </select></td>
			<td><select name="type"><option value="0">全部</option><option value="1">奖励</option><option value="0">惩罚</option></select></td>
			<td><input type="text" placeholder="名称/工号"></td>
		  </tr>
		
		</tbody></table>
	  </div>
	 <div class="wages_content_datail_table" style="margin-top:43px">
	    <ul class="clearfix total_top">
	     <li>工号</li>
	     <li>姓名</li>
		 <li>所属门店</li>
		 <li>奖惩类别</li>
		 <li>奖惩名称</li>
		 <li style="width:229px">备注</li>
		 <li style="width:115px">创建时间</li>
		 <li>操作</li>
		
	   </ul>
	  <div class="wages_content_datail_table_">
	
	   <table class="second">  
	     <tbody>
		<c:forEach var="list" items="${page.results}">
		 <tr>
		   <td>${list.employeeCode }</td>
		   <td>${list.employeeName }</td>
		   <td>${list.employeeName }</td>
		   <td>${list.type }</td>
		   <c:if test="${list.isReward eq 0}"> <td>惩罚</td></c:if>
		   <c:if test="${list.isReward eq 1}"> <td>奖励</td></c:if>
		   <td style="width:200px">${list.reasons }</td>
		   <td>${list.modifytime }</td>
		   <td><img src="<%=basePath %>images/add_store_1.png"><img src="<%=basePath %>images/add_store_2.png"></td>
    	 </tr>
		</c:forEach>
	   	</tbody>
	   </table>
	
	  </div>
 	
	 </div>
	</div>
   <%@ include file="/template/page.jsp"%>
  </div>
			</div>
		</div>
	</div>
	<div class="zzc">
  <div class="zzc_new_style">
     <p>新增类型</p>
     <div class="zzc_new_style_content">
	   <ul class="zzc_new_style_content_ul clearfix">
	     <li><span>所属门店</span>
	     	  <select id="storeIdAll" onchange="employee(this)">
			  <c:forEach items="${selectByStoreAccount }" var="store">
			     <option storeId="${store.storeId }">${store.storeName }</option>
			  </c:forEach>
			  </select>
	     </li>
	     <li>
	     <span style="position:relative;left:-10px">员工</span>
	     <select id="employee">
	     </select></li>
	      
	      <li><span>奖惩类别</span>
	         <select id="type1" onchange="storeRule(this)">
	         <option value="1">奖励</option><option value="2">惩罚</option></select></li>
         <li><span>奖惩名称</span>
         <select name="storeManageRule1" >
         </select></li>
         		     
	   </ul>
	   <div class="alert_saying clearfix">
	     <div class="alert_saying_left">
		  备注
		 </div>
	     <div class="alert_saying_right">
		  <textarea>		  
		  </textarea>
		 </div>
	   </div>
	   
	   <div class="new_style_button">
	     <button>确定</button>
		 <button>取消</button>
	   </div>
	 </div>
  </div>
</div>
</body>
<script>

function employee(){
	
	var storeId = jQuery("#storeIdAll option:selected").attr("storeId");
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewards/view/employee",
		data : "storeId=" +storeId,
		dataType : "json",
		success : function(e) {
			var emp = e.msg;
			var html ='';
			for (var i = 0; i < emp.length; i++) {
				html += '<option employeeId="'+emp[i].employeeId+'">'+emp[i].name+'</option>';
			}
			jQuery("#employee").empty();
			jQuery("#employee").append(html);
		}
	})
}

function storeRule(s){
	var type = jQuery(s).val();
}

function showView(){
	jQuery(".zzc").show();
}



var storeManageRule =  '${storeManageRule}';
var ruleList1Str =  '${ruleList1Str}';
var ruleList2Str = '${ruleList2Str}';

var ruleList1;
if (isEmpty(ruleList1Str)) {
	ruleList1 = new Array();
}
else {
	ruleList1 = eval("(" + ruleList1Str + ")");
}

var ruleList2;
if (isEmpty(ruleList2Str)) {
	ruleList2 = new Array();
}
else {
	ruleList2 = eval("(" + ruleList2Str + ")");
}
var jsonarray = ${jsonarray};
var ruleL1 = ruleList1.length;
var ruleL2 = ruleList2.length;

jQuery(function(){
	jQuery(".rule1").attr("colspan",ruleL1);
	jQuery(".rule2").attr("colspan",ruleL2);
	showHtml(jsonarray);
	wid= (ruleL2+ruleL1+2)*100;
	jQuery('.table_right_head').css('width',wid+'px');
	employee();
});

function showHtml(jsonarray){
	var html1 ='';
	var html2 ='';
    jQuery.each(jsonarray,function(index,value){
    	html1 += ' <tr><td>'+value.code+'</td><td>'+value.name+'</td></tr>';
    	html2 += '<tr><td><span>'+value.total+'</span></td>';
    	for (var i = 0; i < value.jsonjl.length; i++) {
    		html2 += '<td><span class="award">'+value.jsonjl[i]+'</span></td>';
		}
    	html2 += '<td><span class="award">'+value.sum1+'</span></td>';
    	for (var i = 0; i < value.jsoncl.length; i++) {
    		html2 += '<td><span class="punish">'+value.jsoncl[i]+'</span></td>';
		}
    	html2 += '<td><span class="punish">'+value.sum2+'</span></td></tr>';
    })
    jQuery("#teb1 tr:gt(0)").empty();
    jQuery("#teb1").append(html1);
    jQuery("#teb2 tr:gt(1)").empty();
    jQuery("#teb2").append(html2);
    
}

function changePage() {
	var datas = "pageNo=" + pageNo
	jQuery.ajax({
		type : "post",
		url : baseUrl + "view/coupons/by/page",
		data : datas,
		dataType : "json",
		success : function(e) {
			pageNo = e.msg.pageNo;
			pageSize = e.msg.pageSize;
			totalPage = e.msg.totalPage;
			totalRecord = e.msg.totalRecord;
			jQuery("#tables [name='trs']").empty();
			jQuery.each(e.msg.results, function(n, value) {
					jQuery("#tables").append(jQuery(html));
			});
		}
	});
}

	//dialog('msg');
</script>
</html>