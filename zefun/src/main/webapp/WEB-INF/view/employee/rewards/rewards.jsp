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
	  <li class="active" onclick="jQuery('.fenye').hide();">奖惩管理</li>
	  <li class=""  id="li" onclick="jQuery('.fenye').show();">奖惩明细</li> 
	</ul>
	<div class="wages_content" >
	  <div class="wages_content_datail">
			<div class="wages_content_datail_top">
			 时间查询
			  <input type="text" id="time" onchange="changeRule()" style="width:100px;margin:0 10px" onfocus="WdatePicker({dateFmt:'yyyy-MM'})">
			  <select id="storeId1"  onchange="changeRule()">
			  <c:forEach items="${selectByStoreAccount }" var="store">
			     <option storeId="${store.storeId }">${store.storeName }</option>
			  </c:forEach>
			  </select>
			</div>
	  </div>
	 <div class="wages_content_datail_table clearfix">
	  <div class="table_left">
	   <table id="teb1">
	      
	      <tr>
		    <td style="height:85px">工号</td>
		    <td style="height:85px">姓名</td>
		  </tr>
	
		
	   </table>
	  </div>
	  <div class="table_right">
	   <div class="table_right_head">
		   <table id="teb2">  
			 <tbody>
			 <tr id="tr1">
			   <td rowspan="2">合计</td>
			   <td class = "rule1" colspan="3">奖励类别</td>
				<td rowspan="2">奖励合计</td>
				<td class = "rule2" colspan="3">奖惩类别</td>
				<td rowspan="2">惩罚合计</td>	
			  </tr>
			  <tr id="tr2">
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
			<td id="storeTd">所属门店</td>
			<td>奖惩名称</td>
			<td>奖惩类别</td>
			<td>选择员工</td>
			<td rowspan="2"><button onclick="selectd()">搜索</button></td>
		  </tr>
		   <tr>
			<td><input type="text" onchange="changeIsFind()" name="statime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
			<td><input type="text"   onchange="changeIsFind()" name="endtime" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></td>
			<td id="storeTd2">	
			  <select id="storeId2" onchange="selectd()">
			  <c:forEach items="${selectByStoreAccount }" var="store">
			     <option storeId="${store.storeId }">${store.storeName }</option>
			  </c:forEach>
			  </select>
			 </td>
			<td>  
			<select name="storeManageRule" onchange="changeIsFind()">
         	   <option ruleId="0">全部</option>
			  <c:forEach items="${storeManageRule }" var="storeRule">
			     <option ruleId="${storeRule.ruleName }">${storeRule.ruleName }</option>
			  </c:forEach>
         	</select></td>
			<td><select onchange="changeIsFind()"  name="type"><option value="0">全部</option><option value="1">奖励</option><option value="0">惩罚</option></select></td>
			<td><input onchange="changeIsFind()" type="text" name ="employeeCode" placeholder="名称/工号"></td>
		  </tr>
		
		</tbody></table>
	  </div>
	 <div class="wages_content_datail_table" style="margin-top:43px">
	    <ul class="clearfix total_top">
	     <li>工号</li>
	     <li>姓名</li>
		 <li>所属门店</li>
		 <li>奖惩名称</li>
		 <li>奖惩类别</li>
		 <li style="width:229px">备注</li>
		 <li style="width:115px">创建时间</li>
		 <li>操作</li>
		
	   </ul>
	  <div class="wages_content_datail_table_">
	
	   <table class="second">  
	     <tbody>
		<c:forEach var="list" items="${page.results}">
		 <tr id="${list.rewardId }">
		   <td>${list.employeeCode }</td>
		   <td>${list.employeeName }</td>
		   <td>${list.storeName }</td>
		   <td>${list.type }</td>
		   <c:if test="${list.isReward eq 2}"> <td>惩罚</td></c:if>
		   <c:if test="${list.isReward eq 1}"> <td>奖励</td></c:if>
		   <td style="width:200px">${list.reasons }</td>
		   <td>${list.modifytime }</td>
		   <td><img onclick="updated(${list.rewardId },${list.storeId })" src="<%=basePath %>images/add_store_1.png"><img onclick="deleted(${list.rewardId })" src="<%=basePath %>images/add_store_2.png"></td>
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
	     <li id="storeHide"><span >所属门店</span>
	     	  <select id="storeIdAll" onchange="employee()">
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
	       
	   
	         <select id="type1" onchange="employee()">
	         <option value="1">奖励</option><option value="2">惩罚</option></select>
	     </li>
	     
        <li><span>奖惩名称</span>
         <select id="storeManageRule1" >
         </select>
         </li>
         		     
	   </ul>
	   <div class="alert_saying clearfix">
	     <div class="alert_saying_left">
		  备注
		 </div>
	     <div class="alert_saying_right">
		  <textarea id="reasons">		  
		  </textarea>
		 </div>
	   </div>
	   
	   <div class="new_style_button">
	     <button onclick = "save()">确定</button>
		 <button onclick="hide()">取消</button>
	   </div>
	 </div>
  </div>
</div>
</body>
<script>
var rewardId =null ;
var storeId = ${storeId} ;

jQuery(function(){
	jQuery(document).on('click','#teb1 tr:gt(0) td',function(){	
		 jQuery("input[name='employeeCode']").val(jQuery(this).text());
		 jQuery("#storeId2").val(jQuery("#storeId1").val());
		 jQuery("#li").click();
		 selectd();
	})
  })

function storeSelect(){
	if(storeId != 0 ){
		var storeName = jQuery("#storeIdAll option[storeId='"+storeId+"'] ").val();
		jQuery("#storeIdAll").val(storeName);
		jQuery("#storeHide").hide();
		jQuery("#storeTd").remove();
		jQuery("#storeTd2").remove();
		
		jQuery("#storeId1").val(storeName);
		jQuery("#storeId1").hide();
		
		jQuery("#storeId2").val(storeName);
		jQuery("#storeId2").hide();
		
	}
}


function updated(rewardIds,storeId){
	rewardId =rewardIds;
	showView();
	var name = jQuery("#storeIdAll option[storeid='"+storeId+"'").val();
	jQuery("#storeIdAll").val(name);
	
	var type = jQuery("#"+rewardIds).find("td").eq(4).text();
	if(type == "奖励"){
		type = "1";
	}else{
		type="2";
	}
	jQuery("#type1").val(type);
	employee();
	var typename = jQuery("#"+rewardIds).find("td").eq(3).text();
	jQuery("#storeManageRule1").val(typename);
	var reasons = jQuery("#"+rewardIds).find("td").eq(5).text();
	jQuery("#reasons").val(reasons);
}



function deleted(rewardId){
	if(confirm("你确定删除吗？")){
		 jQuery.ajax({
				type : "post",
				url : baseUrl + "rewards/action/delete",
				data : "rewardId="+rewardId,
				dataType : "json",
				success : function(e){
					if(e.code == 0){
						jQuery("#"+rewardId).empty();
					}
					dialog(e.msg);
				}
			})
	}

}
function hide(){
	jQuery(".zzc").hide();
	jQuery("#reasons").val("");
	rewardId =null ;

}
//弹出框保存
function save(){
	 var storeId = jQuery("#storeIdAll option:selected").attr("storeId");
	 var employeeId = jQuery("#employee option:selected").attr("employeeid");
	 var type = jQuery("#storeManageRule1 option:selected").attr("ruleid");
	 var isReward = jQuery("#type1").val();
	 var reasons = jQuery("#reasons").val();
	 var url ="";
	 var dates ="";
	 
	 if(employeeId == null || employeeId == ''){
		 dialog('没有员工不能保存');
		 return ;
	 }
	 if(type == null || type == ''){
		 dialog('奖罚名称不能为空');
		 return ;
	 }
	 if(rewardId == null){
		 url = baseUrl + "rewards/action/add";
		 dates = "type="+type+"&storeId="+storeId+"&employeeId="+employeeId+"&isReward="+isReward+"&reasons="+reasons;
	 }else{
		 url = baseUrl + "rewards/action/update";
		 dates = "type="+type+"&storeId="+storeId+"&employeeId="+employeeId+"&isReward="+isReward+"&reasons="+reasons+"&rewardId="+rewardId;
	 }
	 jQuery.ajax({
			type : "post",
			url : url,
			data : dates,
			dataType : "json",
			success : function(e){
		
				if(rewardId == null){
					dialog('新增成功');
				}else{
					dialog('修改成功');
				}
				 showOnehtml(e.msg);
					hide();
			}
		})
	
}
function showOnehtml(value){
	if(rewardId != null){
		jQuery("#"+value.rewardId).remove();
	}
	
	var html = '';
	html +=' <tr id="'+value.rewardId+'"><td>'+value.employeeCode+'</td>	   <td>'+value.employeeName +'</td> <td>'+value.storeName+'</td><td>'+value.type+'</td>';
	   if(value.isReward == 1){
			html += '<td>奖励</td>';
	   }
	   if(value.isReward == 2){
			html += '<td>惩罚</td>';
	   }
	   html += ' <td style="width:200px">'+value.reasons +'</td><td>'+value.modifytime+'</td> <td><img onclick="updated('+value.rewardId+','+value.storeId+')" src="'+baseUrl+'images/add_store_1.png"><img onclick="deleted('+value.rewardId +')" src="'+baseUrl+'images/add_store_2.png"></td>	 </tr>';
	   jQuery(".second").append(html);
}

//奖罚管理查询页面
function changeRule(){
	  var time = jQuery("#time").val();
	  var storeId = jQuery("#storeId1 option:selected").attr("storeId");
	  jQuery.ajax({
			type : "post",
			url : baseUrl + "rewards/view/home/rule",
			data : "time="+time+"&storeId="+storeId,
			dataType : "json",
			success : function(e){
				selectChange(e.msg.ruleList1, e.msg.ruleList2);
				showHtml(e.msg.jsonarray);
			}
		})
}

function selectChange(ruleList1,ruleList2){
	   jQuery("#tr2").empty();
	   var html ='<tr id="tr2"> ';
	   for (var i = 0; i < ruleList1.length; i++) {
			var ruleList = ruleList1[i];
			html += '<td>'+ruleList.ruleName +'</td>';
	   }
	   for (var j = 0; j < ruleList2.length; j++) {
			var ruleList = ruleList2[j];
			html += '<td>'+ruleList.ruleName +'</td>';
	   }
	   html +='</tr>';
	   jQuery("#tr1").after(html);
}
//搜索
var isFindDate = false;
function selectd(){
	isFindDate = true;
	changePage() ;
}


//条件改变时
function changeIsFind(){
	isFindDate = true;
}


function showRule(par){
	var storeManageRule = par.storeManageRule;
	jQuery("select[name='storeManageRule']").empty();
	var html = '<option ruleId="0">全部</option>';
	jQuery.each(storeManageRule, function(n, value){
		html += '<option ruleId="'+value.ruleId+'">'+value.ruleName+'</option>';
	});
	jQuery("select[name='storeManageRule']").append(html);
}

//分页
function changePage() {
	var staTime  = jQuery("input[name='statime']").val();
	var endTime  = jQuery("input[name='endtime']").val();
	var storeid  = jQuery("#storeId2 option:selected").attr("storeId");
	var ruleName = jQuery("select[name='storeManageRule'] option:selected").attr("ruleId");
	var ruleType = jQuery("select[name='type']").val();
	var employee = jQuery("input[name='employeeCode']").val();
	
	if(ruleName == '0'){
		ruleName= "";
	}
	var datas = "pageNo=" + pageNo + "&staTime=" +staTime + "&endTime=" +endTime + "&storeId=" +storeid + "&ruleName=" +ruleName
	+ "&ruleType=" +ruleType + "&employee=" +employee + "&pageSize=" +pageSize;
	if(storeId != 0 ){
		datas = "pageNo=" + pageNo + "&staTime=" +staTime + "&endTime=" +endTime +  "&ruleName=" +ruleName
		+ "&ruleType=" +ruleType + "&employee=" +employee + "&pageSize=" +pageSize;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewards/view/home/page",
		data : datas,
		dataType : "json",
		success : function(e) {
			pageNo = e.msg.pageNo;
			pageSize = e.msg.pageSize;
			totalPage = e.msg.totalPage;
			totalRecord = e.msg.totalRecord;
			if(isFindDate){
				unbuildPagination();
				isFindDate = false;
			}
			var par = e.msg.params
			showRule(par);
			var html='';
			jQuery(".second").empty();
			jQuery.each(e.msg.results, function(n, value) {
				html +=' <tr id="'+value.rewardId+'"><td>'+value.employeeCode+'</td>	   <td>'+value.employeeName +'</td> <td>'+value.employeeName+'</td><td>'+value.type+'</td>';
				
				   if(value.isReward == 1){
						html += '<td>奖励</td>';
				   }
				   if(value.isReward == 2){
						html += '<td>惩罚</td>';
				   }
				   html += ' <td style="width:200px">'+value.reasons +'</td><td>'+value.modifytime+'</td> <td><img onclick="updated('+value.rewardId+','+value.storeId+')" src="'+baseUrl+'images/add_store_1.png"><img onclick="deleted('+value.rewardId +')" src="'+baseUrl+'images/add_store_2.png"></td>	 </tr>';
			});
			jQuery(".second").append(html);
		}
	});
}


//弹出框改变下拉框
function employee(){
	var type = jQuery("#type1 option:selected").val();
	var storeId = jQuery("#storeIdAll option:selected").attr("storeId");
	jQuery.ajax({
		type : "post",
		url : baseUrl + "rewards/view/employee",
		data : "storeId=" +storeId +"&type="+type,
		dataType : "json",
		success : function(e) {
			var emp = e.msg.emp;
			var rule = e.msg.rule;
			var html ='';
			var htmlrule ='';
			
			for (var i = 0; i < emp.length; i++) {
				html += '<option employeeId="'+emp[i].employeeId+'">'+emp[i].name+'</option>';
			}
			for (var i = 0; i < rule.length; i++) {
				htmlrule += '<option ruleId="'+rule[i].ruleId+'" processType="'+rule[i].processType+'">'+rule[i].ruleName+'</option>';
			}
			
			jQuery("#storeManageRule1").empty();
			jQuery("#storeManageRule1").append(htmlrule);
			jQuery("#employee").empty();
			jQuery("#employee").append(html);
		}
	})
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
	jQuery(".fenye").hide();
	storeSelect();
	employee();
});

//展示奖罚管理
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





	//dialog('msg');
</script>
</html>