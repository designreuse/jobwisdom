<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
.fenye{margin-top:10px}
</style>
 <script>
	 //轮播
 	jQuery(function(){
	     var now_=0, count=jQuery('.out_roll_ul li').size();
	  //向右走
      jQuery('.click_right').click(function(){
         if(now_<=count-6){
		    now_+=1;
	        jQuery(this).parent('').find('.out_roll_ul').stop(true,true).animate({
		       left:-181*now_
		   
		       }) 
			  }
		  });
	  //向左走
	  
	 jQuery('.click_left').click(function(){
         if(now_>=1){
		    now_-=1;
	         jQuery(this).parent('').find('.out_roll_ul').stop(true,true).animate({
		     left:-181*now_
		   
		     }) 
		  }	
        		
	  });
 });
	 
	 
	 jQuery(function(){
		 jQuery('.new_activity_spread >p').click(function(){
			 jQuery(this).parents('.new_activity_spread').find('.new_activity_spread_content').stop(true,true).slideToggle();
			 jQuery(this).stop(true,true).toggleClass('blue_')
			 
		 })
	 })
	</script>
	<style>
	.blue_{color:blue}
	.new_activity_spread_content{display:none}
	
	</style>

<link rel="stylesheet" href="<%=basePath%>css/activity.css" type="text/css" />
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				
				<div class="content_right clearfix">
  
				   <div class="card_content">
				     <div class="out_roll_content">
				     <div class="out_roll">
					  <span class="click_left"><img src="<%=basePath%>images/left_click.png"></span>
				     <div class="out_roll_div">	  
					  <ul class="clearfix out_roll_ul">
						<c:forEach items="${storeInfos }" var="storeInfo">
						<li onclick="selectStore(this, ${storeInfo.storeId })" id="${storeInfo.storeId }">${storeInfo.storeName }</li>
						</c:forEach>
					   </ul>
					   <script>jQuery(".out_roll_ul").children("li").eq(0).addClass("active");</script>
					  </div> 
					   <span class="click_right"><img src="<%=basePath%>images/right_click.png"></span>
					 </div>
					</div>
				   </div> 
					
				   <div class="new_activity">
				      <p>
				      <button onclick="jQuery('.zzc').show()">新建活动类别</button>
				      <button onclick="winHref()">返回</button>
				      </p> 
					  <div class="new_activity_content">
					    <div class="new_activity_content_datail">
						  <table id="tables">
						    <tbody>
						    <tr class="one">
							   <td>活动名称</td>
							   <td>活动类型</td>
							   <td>活动策略</td>
							   <td style="width:335px">员工提成</td>
							   <td style="width:180px">是否同时允许会员卡打折</td>
							   <td>操作</td>
							</tr>
							
							<c:forEach  items="${page.results}" var="activityInfo"> 
						
						     <tr class="tr" id="${activityInfo.activityId}">
							   <td>${activityInfo.activityName }</td>
							   <c:if test="${activityInfo.activityType eq 1 }"><td>折扣</td></c:if><c:if test="${activityInfo.activityType eq 2 }"><td>现金</td></c:if><c:if test="${activityInfo.activityType eq 3 }"><td>体验</td></c:if>
							   <td>&nbsp;${activityInfo.activityCl}</td>
							   <td>
							      <p><span><i id="one">${activityInfo.activityPositionOne }</i>
							      <c:if test="${activityInfo.positionOneType eq 1 }"><em>固定</em></span>	<span><i>提成</i><em>${activityInfo.positionOnePush }元</em></span><span><i>业绩</i><em>${activityInfo.positionOneResult }元</em></span></c:if>
							      <c:if test="${activityInfo.positionOneType eq 2 }"><em>比例</em></span>	<span><i>提成</i><em>${activityInfo.positionOnePush }%</em></span><span><i>业绩</i><em>${activityInfo.positionOneResult }%</em></span></c:if>
								  </p>
							      <p><span><i id="two">${activityInfo.activityPositionTwo }</i>
							      <c:if test="${activityInfo.positionTwoType eq 1 }"><em>固定</em></span>	<span><i>提成</i><em>${activityInfo.positionTwoPush }元</em></span><span><i>业绩</i><em>${activityInfo.positionTwoResult }元</em></span></c:if>
							      <c:if test="${activityInfo.positionTwoType eq 2 }"><em>比例</em></span>	<span><i>提成</i><em>${activityInfo.positionTwoPush }%</em></span><span><i>业绩</i><em>${activityInfo.positionTwoResult }%</em></span></c:if>
							
								 </p>
								  <p><span><i id="three">${activityInfo.activityPositionThree }</i>
								   <c:if test="${activityInfo.positionThreeType eq 1 }"><em>固定</em></span>	<span><i>提成</i><em>${activityInfo.positionThreePush }元</em></span><span><i>业绩</i><em>${activityInfo.positionThreeResult }元</em></span></c:if>
							      <c:if test="${activityInfo.positionThreeType eq 2 }"><em>比例</em></span>	<span><i>提成</i><em>${activityInfo.positionThreePush }%</em></span><span><i>业绩</i><em>${activityInfo.positionThreeResult }%</em></span></c:if>
								 </p>
							   </td>
							   <c:if test="${activityInfo.activityDiscount eq 1 }"><td>是</td></c:if>
							   <c:if test="${activityInfo.activityDiscount eq 2 }"><td>否</td></c:if>
							   <td><img onclick="updated(this,${activityInfo.activityId})" src="<%=basePath%>images/add_store_1.png"><img onclick="deleted(${activityInfo.activityId})" src="<%=basePath%>images/add_store_2.png"></td>
							 </tr>
						  </c:forEach>
						  </tbody></table>
					
						</div>
					 
					  </div>
					   	  <%@ include file="/template/page.jsp"%>
				   </div>   
				  </div>
				
			</div>
		</div>
	</div>
	
	<div class="zzc" style="display: none;">
		   <div class="zzc_new_activity">
		       <p>新建活动类别</p>           
		       <div class="zzc_new_activity_content">
			      <div class="zzc_new_activity_content_datail">
		            <p>活动</p>
		            <div class="activity_name">
					   <p>活动名称<input type="text" name="activityName" style="width:190px"></p>
					   <p  style="margin-right:-40px">活动类型<span><em><input type="radio" name="style" value="1" checked="">折扣</em><em><input  value="2" type="radio" name="style">现金</em><em><input type="radio"  value="3" name="style">体验</em></span>
					   <span class="activity_type" >活动策略<input type="text" style="padding-right:20px" name ="activityCl" ><i>元</i></span></p>
					   <p class="allow_activity"><input name ="activityDiscount" type="checkbox">同时允许会员卡打折</p>
					</div>
		          </div>		  

 
			      <div class="emploee_achievement">
			        <p>员工业绩提成方案</p>
			        <div class="emploee_achievement_">
					   <table id="position_emploee">
					     <tbody><tr> 
					       <td>职位</td>
						   <td>提成方式</td>
						   <td>提成</td>
						   <td>业绩</td>
					     </tr >
					       <tr name="price"> 
					       <td id="${positionInfos[0].positionId }">${positionInfos[0].positionName }</td>
						   <td><select  name ="positionOneType"  onchange="changeprice(this,2)" ><option value="1">固定</option><option value="2">比例</option></select></td>
						   <td><input type="text" name="positionOnePush"><i name="priceone">元</i></td>
						   <td><input type="text" name="positionOneResult"><i name="priceone">元</i></td>
					     </tr>
					 	 <tr name="price"> 
					       <td id="${positionInfos[1].positionId }">${positionInfos[1].positionName }</td>
						   <td><select name ="positionTwoType"  onchange="changeprice(this,2)" ><option value="1">固定</option><option value="2">比例</option></select></td>
						   <td><input type="text" name="positionTwoPush"><i name="pricetwo">元</i></td>
						   <td ><input type="text" name="positionTwoResult"><i name="pricetwo">元</i></td>
					     </tr>
						 <tr name="price"> 
					       <td id="${positionInfos[2].positionId }">${positionInfos[2].positionName }</td>
						   <td><select name ="positionThreeType"  onchange="changeprice(this,2)"  ><option value="1">固定</option><option value="2">比例</option></select></td>
						   <td><input type="text" name="positionThreePush"><i name="pricethree">元</i></td>
						   <td><input type="text" name="positionThreeResult"><i name="pricethree">元</i></td>
					     </tr>
						
					   </tbody></table>
			        </div>
		         </div>
				 
				<div class="zzc_achievement_button">
				   <button onclick="save()">确认</button>
				   <button onclick="hiddenz()">取消</button>
				</div>
		      </div>
		   </div>
		</div>
</body>
<script>
var type =1;
var storeId= '${storeId}';
var activityId = null;
var position = '${positionInfo}';
var positionInfo ;
if (!isEmpty(position)) {
	positionInfo = eval('('+position+')'); 
}
var positionInfo ;
function changeprice(position,type){
	var element;
	var tag;
	if(type == 1){
		element="div";
		tag="em";
	}else{
		element="tr";
		tag="i";
	}
	
	var positiontype = jQuery(position).val();
	if(positiontype == 1){
		jQuery(position).parents(element+"[name='price']").find(tag).text("元");
	}else{
		jQuery(position).parents(element+"[name='price']").find(tag).text("%");
	}
}

jQuery("input:radio[name='style']").change(function (){ 
	 switch(jQuery("input[name='style']:checked").attr("value")){
	  case "1":
			jQuery(".activity_type").show();
			jQuery(".allow_activity").show();
			jQuery(".zzc").find("input[name='activityCl']").val("");
	   break;
	  case "2":
			jQuery(".allow_activity").hide();
			jQuery(".activity_type").show();
			jQuery(".zzc").find("input[name='activityCl']").val("");
			if(jQuery(".zzc").find("input[type='checkbox']").is(':checked')){
				jQuery(".zzc").find("input[type='checkbox']").click();
			}
		   break;
	  case "3":
			jQuery(".activity_type").hide();
			jQuery(".allow_activity").hide();
			jQuery(".zzc").find("input[name='activityCl']").val("");
			if(jQuery(".zzc").find("input[type='checkbox']").is(':checked')){
				jQuery(".zzc").find("input[type='checkbox']").click();
			}
	   break;
	  default:
	   break;
	 }
});


function deleted (id){
	var data = "activityId="+id;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "activity/view/toactivitydelete",
		data : data,
		dataType : "json",
		success : function(e){
		
			jQuery("#"+id).attr("style","display: none");
			dialog("删除成功");
		}
	
	});	
	
}


function updated(activity,id){
	var data = "activityId="+id;
	var activityPositionOne = jQuery(activity).parents(".tr").find("#one").text();
	var activityPositionTwo = jQuery(activity).parents(".tr").find("#two").text();
	var activityPositionThree = jQuery(activity).parents(".tr").find("#three").text();
	jQuery.ajax({
		type : "post",
		url : baseUrl + "activity/view/toactivityselect",
		data : data,
		dataType : "json",
		success : function(e){
			var activity = e.msg;
			
			jQuery(".zzc").find("input[name='activityName']").val(activity.activityName);
			jQuery(".zzc").find("input[name='style'][value='"+activity.activityType+"']").click();
			jQuery(".zzc").find("input[name='activityCl']").val(activity.activityCl);
			var checkbox= jQuery(".zzc").find("input[type='checkbox']").is(':checked');
			if((checkbox && activity.activityDiscount ==2) || (!checkbox && activity.activityDiscount ==1)){
				jQuery(".zzc").find("input[type='checkbox']").click();
			}
			
			jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(0).attr("id",activity.activityPositionOne);
// 			jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(0).text(activityPositionOne);
			
			jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(0).attr("id",activity.activityPositionTwo);
// 			jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(0).text(activityPositionTwo);
			
			jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(0).attr("id",activity.activityPositionThree);
// 			jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(0).text(activityPositionThree);
			
			
			jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(1).find("select").val(activity.positionOneType);
			changeprice(jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(1).find("select"),2);
		 	jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(2).find("input").val(activity.positionOnePush);
		 	jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(3).find("input").val(activity.positionOneResult);
		 	
		 	jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(1).find("select").val(activity.positionTwoType); 
		 	changeprice(jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(1).find("select"),2);
		 	jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(2).find("input").val(activity.positionTwoPush);
		 	jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(3).find("input").val(activity.positionTwoResult);
		 	
		 	jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(1).find("select").val(activity.positionThreeType); 
		 	changeprice(jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(1).find("select"),2);
		 	jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(2).find("input").val(activity.positionThreePush);
		 	jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(3).find("input").val(activity.positionThreeResult);
		 	
		 	jQuery(".zzc").show();
		 	type=2;
		 	activityId=id;
		}
	
	});	
	
}
var changes = false;
function selectStore(li,id){
	changes = true;
	storeId=id;
	jQuery(li).siblings().removeClass("active");
	jQuery(li).addClass("active");
	jQuery(".new_activity_spread").attr("style","display: none");
	pageNo=1;
	changePage(); 

}





function save(){
	var activityName = jQuery(".zzc").find("input[name='activityName']").val();
	var activityType = jQuery(".zzc").find("input[name='style']:checked").attr("value");
	var activityCl   = jQuery(".zzc").find("input[name='activityCl']").val();
	
	var activityDiscount      = jQuery(".zzc").find("input[type='checkbox']").is(':checked')==true?1:2;
	var activityPositionOne   = jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(0).attr("id");
	var activityPositionTwo   = jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(0).attr("id");
	var activityPositionThree = jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(0).attr("id");
	
	var positionOneType   = jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(1).find("select").val(); 
 	var positionOnePush   = jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(2).find("input").val();
 	var positionOneResult = jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(3).find("input").val();
 	
 	var positionTwoType   = jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(1).find("select").val(); 
 	var positionTwoPush   = jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(2).find("input").val();
 	var positionTwoResult = jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(3).find("input").val();
 	
 	var positionThreeType   = jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(1).find("select").val(); 
 	var positionThreePush   = jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(2).find("input").val();
 	var positionThreeResult = jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(3).find("input").val();
 	var url =baseUrl + "activity/view/toactivitysave";
	if(type ==2){
		url=baseUrl + "activity/view/toactivityupdate";
	}
 	var data = {
		"activityName":activityName, "activityType" :activityType, "activityCl"	: activityCl, "activityDiscount" : activityDiscount , "activityPositionOne" : activityPositionOne ,
		"activityPositionTwo" : activityPositionTwo, "activityPositionTwo" : activityPositionTwo , "activityPositionThree" : activityPositionThree ,"positionOneType" : positionOneType,
		"positionOnePush" : positionOnePush, "positionOneResult" : positionOneResult , "positionTwoType" : positionTwoType, "positionTwoPush" : positionTwoPush, "positionTwoResult" : positionTwoResult, 
		"positionThreeType" : positionThreeType, "positionThreePush" : positionThreePush, "positionThreeResult":positionThreeResult, "storeId" : storeId, "activityId": activityId
	}
	
	if (isEmpty(activityName)) {
		dialog("活动名字不能为空");
		return ;
	}
	if (isEmpty(activityCl)) {
		activityCl =0;
	}
	
 	jQuery.ajax({
		type : "post",
		url : url,
		data : JSON.stringify(data),
		contentType : "application/json",
		dataType : "json",
		success : function(e){
			jQuery("li[id='"+storeId+"']").click();
// 			location.href="http://localhost/jobwisdom/activity/view/toactivitysign?storeId="+storeId;
		}
	});
}
function hiddenz(){
	type=1;
	activityId=null;
	jQuery(".zzc").find("input[name='activityName']").val("");
	jQuery(".zzc").find("input[name='style'][value='1']").click();
	jQuery(".zzc").find("input[name='activityCl']").val("");
	
	if(jQuery(".zzc").find("input[type='checkbox']").is(':checked')){
		jQuery(".zzc").find("input[type='checkbox']").click();
	}
	jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(0).attr("id",positionInfo[0].positionId);
	jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(0).text(positionInfo[0].positionName);
	jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(0).attr("id",positionInfo[1].positionId);
	jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(0).text(positionInfo[1].positionName);
	jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(0).attr("id",positionInfo[2].positionId);
	jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(0).text(positionInfo[2].positionName);
	
	jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(1).find("select").val(); 
 	jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(2).find("input").val("");
 	jQuery(".zzc").find("#position_emploee tr").eq(1).find("td").eq(3).find("input").val("");
 	
 	jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(1).find("select").val(); 
 	jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(2).find("input").val("");
 	jQuery(".zzc").find("#position_emploee tr").eq(2).find("td").eq(3).find("input").val("");
 	
 	jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(1).find("select").val(); 
 	jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(2).find("input").val("");
 	jQuery(".zzc").find("#position_emploee tr").eq(3).find("td").eq(3).find("input").val("");
	jQuery('.zzc').hide();
}


//分页
function changePage() { 
	var datas = "pageNo=" + pageNo +"&storeId="+storeId;;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "activity/view/toactivitysign",
		data : datas,
		dataType : "json",
		success : function(e) {
			pageNo = e.msg.page[0].pageNo;
			pageSize = e.msg.page[0].pageSize;
			totalPage = e.msg.page[0].totalPage;
			totalRecord = e.msg.page[0].totalRecord;
			if (changes) {
				changes = false;
				unbuildPagination();
			}
			var activity = e.msg.page[0].results;
// 			var activity = e.msg.activityInfo;
			positionInfo = e.msg.positionInfos;
			hiddenz();
			jQuery("#tables tbody").html("<tr class='one'><td>活动名称</td><td>活动类型</td><td>活动策略</td><td style='width:335px'>员工提成</td><td style='width:180px'>是否同时允许会员卡打折</td><td>操作</td></tr>");
			  jQuery.each(activity,function(name,value){
					var html = "<tr class='tr' id='"+value.activityId+"'><td>"+value.activityName+"</td>";
					if(value.activityType == 1 ){
						html += "<td>折扣</td>";
					}
					if(value.activityType == 2 ){
						html += "<td>现金</td>";
					}
					if(value.activityType == 3 ){
						html += "<td>体验</td>";
					}
					html += "<td>"+value.activityCl+"</td><td><p><span><i id='one'>"+value.activityPositionOne+"</i>";
					if(value.positionOneType == 1 ){
						html += "<em>固定</em>";
						html +="</span><span><i>提成</i><em>"+value.positionOnePush+"元</em></span><span><i>业绩</i><em>"+value.positionOneResult+"元</em></span></p><p><span><i id='two'>"+value.activityPositionTwo+"</i>";
					}
					if(value.positionOneType == 2 ){
						html += "<em>比例</em>";
						html +="</span><span><i>提成</i><em>"+value.positionOnePush+"%</em></span><span><i>业绩</i><em>"+value.positionOneResult+"%</em></span></p><p><span><i id='two'>"+value.activityPositionTwo+"</i>";
					}
					
					if(value.positionTwoType == 1 ){
						html += "<em>固定</em>";
						html +="</span><span><i>提成</i><em>"+value.positionTwoPush+"元</em></span><span><i>业绩</i><em>"+value.positionTwoResult+"元</em></span></p><p><span><i id='two'>"+value.activityPositionThree+"</i>";
					}
					if(value.positionTwoType == 2 ){
						html += "<em>比例</em>";
						html +="</span><span><i>提成</i><em>"+value.positionTwoPush+"%</em></span><span><i>业绩</i><em>"+value.positionTwoResult+"%</em></span></p><p><span><i id='two'>"+value.activityPositionThree+"</i>";
					}
					
					if(value.positionThreeType == 1 ){
						html += "<em>固定</em>";
						html +="</span><span><i>提成</i><em>"+value.positionThreePush+"元</em></span><span><i>业绩</i><em>"+value.positionThreeResult+"元</em></span></p>";
						
					}
					if(value.positionThreeType == 2 ){
						html += "<em>比例</em>";
						html +="</span><span><i>提成</i><em>"+value.positionThreePush+"%</em></span><span><i>业绩</i><em>"+value.positionThreeResult+"%</em></span></p>";
					}
					
					if(value.activityDiscount == 1 ){
						html += "<td>是</td>";
					}
					if(value.activityDiscount == 2 ){
						html += "<td>否</td>";
					}
					html += "<td><img onclick='updated(this,"+value.activityId+")' src='"+baseUrl+"images/add_store_1.png'><img onclick='deleted("+value.activityId+")' src='"+baseUrl+"images/add_store_2.png'></td></tr>";
					jQuery("#tables tbody").append(jQuery(html));
			  });
		}
	});
}

function winHref(){
	window.location.href= baseUrl +"activity/view/showactivitysign";
}
</script>
</html>