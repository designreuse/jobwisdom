<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/round_of_cards.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			 <div class='content_right clearfix'>
			      <div class="card_content">
			         <div class="out_roll_content">
					    <div class="out_roll">
						  <span class="click_left"><img src="<%=basePath%>images/left_click.png"></span>
					     <div class="out_roll_div">	  
						  <ul class="clearfix out_roll_ul">
						     <c:forEach items="${storeInfoList}" var="storeInfo" varStatus="status">
						         <li <c:if test="${status.index == 0}">class="active"</c:if> storeId = "${storeInfo.storeId}" onclick = "chooseStore(this)">${storeInfo.storeName }</li>
						     </c:forEach>
						   </ul>
						  </div> 
						   <span class="click_right"><img src="<%=basePath%>images/right_click.png"></span>
					   </div>
				    </div>
				     <p><button onclick="addShiftMajong()">新增轮牌</button></p>
				     <div class="card_table">
					     <table name = "shiftTable">
						    <tr>
							   <td>名称</td>
							   <td style="width:220px">角色</td>
							   <td>轮牌规则</td>
							   <td>离开规则</td>
							   <td>上牌规则</td>
							   <td>创建时间</td>
							   <td>操作</td>
							</tr>
							<c:forEach items="${shiftMahjongList}" var="shiftMahjong" varStatus="status">
							    <tr>
								   <td>${shiftMahjong.shiftMahjongName }</td>
								   <td>${shiftMahjong.position }</td>
								   <td><c:if test="${shiftMahjong.shiftMahjongRule == 1 }">指定不轮牌</c:if><c:if test="${shiftMahjong.shiftMahjongRule == 2 }">指定轮牌</c:if></td>
								   <td><c:if test="${shiftMahjong.shiftMahjongUp == 1 }">考勤轮牌</c:if><c:if test="${shiftMahjong.shiftMahjongRule == 2 }">持续轮牌</c:if></td>
								   <td><c:if test="${shiftMahjong.nature == 1 }">离开不轮牌</c:if><c:if test="${shiftMahjong.nature == 2 }">离开轮牌</c:if></td>
								   <td>${shiftMahjong.createTime }</td>
								   <td>
								      <em onclick="setShiftMahjong(this, ${shiftMahjong.shiftMahjongId })"><img src="<%=basePath%>images/architecture_edit.png"></em>
								      <em onclick="deleteShiftMahjong(this, ${shiftMahjong.shiftMahjongId })"><img src="<%=basePath%>images/architecture_delete.png"></em>
								   </td>
								</tr>
							</c:forEach>
						 </table>		 
					 </div>
				  </div> 
			  </div> 
		</div>
    </div>
  </div>


  <div class="zzc" style="display:none">
   <div class="new_round_of_cards">
      <p>新建轮牌</p>
      <div class="new_round_of_cards_content">
	    <p>轮牌名牌<input type="text" name="shiftMahjongName" placeholder="最多5个字"></p>
	    <ul class="new_rules clearfix">
		   <li>轮牌规则:<span> <input type="radio"  name="shiftMahjongRule" value="1">指定不动牌</span><span> <input type="radio" name="shiftMahjongRule" value="2">指定动牌</span></li>
		   <li style="position:relative;left:70px">离开规则:<span> <input type="radio" name="nature" value="1">离开不轮牌</span><span> <input type="radio"  name="nature" value="2">离开轮牌</span></li>
		   <li>上牌规则:<span style="margin-right:14px"> <input type="radio"  name="shiftMahjongUp" value="1">考勤上牌</span><span> <input type="radio" name="shiftMahjongUp" value="2">持续上牌</span></li>
		</ul>
	    <div class="select_job">
		  <p>
		     <span>选择岗位:</span>
		     <c:forEach items="${positionInfoList}" var="positionInfo" varStatus="status">
		         <em><input type="checkbox" name = "positionId" value = "${positionInfo.positionId }">${positionInfo.positionName }</em>
		     </c:forEach>
		  </p>
		</div>
		<div class="new_round_of_cards_button">
		  <button id= "confirm">确定</button>
		  <button onclick="canclezzc()">取消</button>
		</div>
	  </div>
   </div>
</div>
</body>
<script>
   var  shiftMahjongId = "";
   
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
	
   
   function addShiftMajong() {
	   
	   shiftMahjongId = "";
		
		valuation("shiftMahjongRule", 1);
		valuation("shiftMahjongUp", 1);
		valuation("nature", 1);
		jQuery("input[name='shiftMahjongName']").val("");
		
		jQuery("input[name='positionId']").prop("checked", false);
		
		jQuery(".zzc").show();
   }
   
   function canclezzc () {
		jQuery(".zzc").hide();
   }
   
   //赋值model中redio的值
   function valuation(names, values) {
   	var nameObj = jQuery("input[name='"+names+"']");
   	for (var i = 0; i < nameObj.length; i++) {
   		var obj = nameObj[i];
   		jQuery(obj).parent().find(".beau-checker").removeClass("active");
   		var value = (jQuery(obj).val()).replace(/[^0-9]/ig,""); 
   		if (value == values) {
   			jQuery(obj).parent().find(".beau-checker").addClass("active");
   			jQuery(obj).prop("checked", true);
   		}
   	}
   }
   
 //新增或设置轮牌model
  jQuery("#confirm").click(function(){
   	
   	var shiftMahjongUp = radioValue("shiftMahjongUp");
   	var shiftMahjongRule = radioValue("shiftMahjongRule");
   	var nature = radioValue("nature");
   	var positionIdList = jQuery("input[name='positionId']:checked");
   	var positionId = "";
   	
   	var storeId = jQuery(".out_roll_ul .active").attr("storeId");
   	
   	for (var i = 0; i < positionIdList.length; i++) {
   		if (i == 0) {
   			positionId = jQuery(positionIdList[i]).val();
   		}
   		else {
   			positionId = positionId + "," + jQuery(positionIdList[i]).val();
   		}
   	}
   	
   	if (isEmpty(positionId)) {
   		dialog("选择岗位！");
   		return;
   	}
   	
   	var shiftMahjongName = jQuery("input[name='shiftMahjongName']").val();
   	if (shiftMahjongName == "") {
   		dialog("轮牌名称不能为空！");
   		return;
   	}
   	if (shiftMahjongName.length > 5) {
   		dialog("轮牌名称不能超出5个字符！");
   		return;
   	}
   	jQuery.ajax({
   		type : "post",
   		url : baseUrl + "KeepAccounts/addUpdateShiftMahjong",
   		data : "shiftMahjongId="+shiftMahjongId+"&shiftMahjongName="+shiftMahjongName+"&shiftMahjongUp="+shiftMahjongUp+"&shiftMahjongRule="+shiftMahjongRule+"&nature="+nature+"&positionId="+positionId+"&storeId="+storeId,
   		async:false,//使用同步的Ajax请求  
   		dataType : "json",
   		success : function(e){
   			if(e.code != 0){
   				dialog(e.msg);
   				return;
   			}
   			var shiftMahjong = e.msg;
   			if (isEmpty(shiftMahjongId)) {
	   			var str = '<tr>'+
	 			   '<td>'+shiftMahjong.shiftMahjongName+'</td>'+
	 			   '<td>'+shiftMahjong.position+'</td>';
	 			if (shiftMahjong.shiftMahjongRule == 1) {
	 				str += '<td>指定不轮牌</td>';
	 			}
	 			else {
	 				str += '<td>指定轮牌</td>';
	 			}
	 			
	 			if (shiftMahjong.shiftMahjongUp == 1) {
	 				str += '<td>考勤轮牌</td>';
	 			}
	 			else {
	 				str += '<td>持续轮牌</td>';
	 			}
	 			
	 			if (shiftMahjong.nature == 1) {
	 				str += '<td>离开不轮牌</td>';
	 			}
	 			else {
	 				str += '<td>离开轮牌</td>';
	 			}
	 			
	 			str += '<td>'+shiftMahjong.createTime+'</td>'+
	 				   '<td>'+
	 				      '<em onclick="setShiftMahjong(this, '+shiftMahjong.shiftMahjongId+')"><img src="'+baseUrl+'images/architecture_edit.png"></em>'+
	 				      '<em onclick="deleteShiftMahjong(this,'+shiftMahjong.shiftMahjongId+')"><img src="'+baseUrl+'images/architecture_delete.png"></em>'+
	 				   '</td>'+
	 				'</tr>';
	 			jQuery("table[name='shiftTable']").append(str);
	 			dialog("新增轮牌成功！");
   			}
   			else {
   				chooseShiftObj.empty();
   				var str = '<td>'+shiftMahjong.shiftMahjongName+'</td>'+
	 			          '<td>'+shiftMahjong.position+'</td>';
	 			if (shiftMahjong.shiftMahjongRule == 1) {
	 				str += '<td>指定不轮牌</td>';
	 			}
	 			else {
	 				str += '<td>指定轮牌</td>';
	 			}
	 			
	 			if (shiftMahjong.shiftMahjongUp == 1) {
	 				str += '<td>考勤轮牌</td>';
	 			}
	 			else {
	 				str += '<td>持续轮牌</td>';
	 			}
	 			
	 			if (shiftMahjong.nature == 1) {
	 				str += '<td>离开不轮牌</td>';
	 			}
	 			else {
	 				str += '<td>离开轮牌</td>';
	 			}
	 			
	 			str += '<td>'+shiftMahjong.createTime+'</td>'+
	 				   '<td>'+
	 				      '<em onclick="setShiftMahjong(this, '+shiftMahjong.shiftMahjongId+')"><img src="'+baseUrl+'images/architecture_edit.png"></em>'+
	 				      '<em onclick="deleteShiftMahjong(this,'+shiftMahjong.shiftMahjongId+')"><img src="'+baseUrl+'images/architecture_delete.png"></em>'+
	 				   '</td>';
	 			jQuery(chooseShiftObj).append(str);
	 			dialog("修改轮牌成功！");
   			}
   			canclezzc();
   		}
   	});
   });
 
  //取model中redio的值
  function radioValue(names) {
  	var nameObj = jQuery("input[name='"+names+"']:checked").val();
  	return nameObj;
  }
  
  var chooseShiftObj = "";
  function setShiftMahjong(obj, shiftMahjongIdS){
	    chooseShiftObj = jQuery(obj).parents("tr");
		shiftMahjongId = shiftMahjongIdS;
		
		jQuery(".zzc").show();
		
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/initializeModel",
			data : "shiftMahjongId="+shiftMahjongId,
			async:false,//使用同步的Ajax请求  
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				var shiftMahjong = e.msg;
				
				valuation("shiftMahjongRule", shiftMahjong.shiftMahjongRule);
				valuation("shiftMahjongUp", shiftMahjong.shiftMahjongUp);
				valuation("nature", shiftMahjong.nature);
			    
				jQuery("input[name='shiftMahjongName']").val(shiftMahjong.shiftMahjongName);
				
				var positionIds = shiftMahjong.positionId.split(",");
				jQuery("input[name='positionId']").prop("checked", false);
				var obj = jQuery("input[name='positionId']");
				for (var i = 0; i < obj.length; i++) {
					var positionId = jQuery(obj[i]).val();
					for (var j = 0; j < positionIds.length; j++) {
						if (positionIds[j] == positionId) {
							jQuery(obj[i]).prop("checked",true);
						}
					}
				}
			}
		});
	}
  
  function deleteShiftMahjong(obj, shiftMahjongId){
	  if(confirm("确认要删除该轮牌吗？")){ 
		  jQuery.ajax({
				type : "post",
				url : baseUrl + "KeepAccounts/deleteShiftMahjong",
				data : "shiftMahjongId="+shiftMahjongId,
				async:false,//使用同步的Ajax请求 
				dataType : "json",
				success : function(e){
					if(e.code != 0){
						dialog(e.msg);
						return;
					}
					dialog("删除轮牌成功！");
					jQuery(obj).parents("tr").remove();
				}
			});
	  }
	}
  
  function chooseStore (obj) {
	    jQuery(obj).siblings().removeClass("active");
	    jQuery(obj).addClass("active");
	    var storeId = jQuery(obj).attr("storeId");
	  
		jQuery.ajax({
			type : "post",
			url : baseUrl + "KeepAccounts/showStoreShiftMahjong",
			data : "storeId="+storeId,
			async:false,//使用同步的Ajax请求 
			dataType : "json",
			success : function(e){
				if(e.code != 0){
					dialog(e.msg);
					return;
				}
				var shiftMahjongList = e.msg.shiftMahjongList;
				var positionInfoList = e.msg.positionInfoList;
				jQuery("table[name='shiftTable'] tr:gt(0)").remove();
				for (var i = 0; i < shiftMahjongList.length; i++) {
					var shiftMahjong = shiftMahjongList[i];
					var str = '<tr>'+
					   '<td>'+shiftMahjong.shiftMahjongName+'</td>'+
					   '<td>'+shiftMahjong.position+'</td>';
					if (shiftMahjong.shiftMahjongRule == 1) {
						str += '<td>指定不轮牌</td>';
					}
					else {
						str += '<td>指定轮牌</td>';
					}
					
					if (shiftMahjong.shiftMahjongUp == 1) {
						str += '<td>考勤轮牌</td>';
					}
					else {
						str += '<td>持续轮牌</td>';
					}
					
					if (shiftMahjong.nature == 1) {
						str += '<td>离开不轮牌</td>';
					}
					else {
						str += '<td>离开轮牌</td>';
					}
					
					str += '<td>'+shiftMahjong.createTime+'</td>'+
						   '<td>'+
						      '<em onclick="setShiftMahjong(this, '+shiftMahjong.shiftMahjongId+')"><img src="'+baseUrl+'images/architecture_edit.png"></em>'+
						      '<em onclick="deleteShiftMahjong(this,'+shiftMahjong.shiftMahjongId+')"><img src="'+baseUrl+'images/architecture_delete.png"></em>'+
						   '</td>'+
						'</tr>';
					jQuery("table[name='shiftTable']").append(str);
				}
				
				jQuery(".select_job").find("em").remove();
				
				for (var i = 0; i < positionInfoList.length; i++) {
					var positionInfo = positionInfoList[i];
					jQuery(".select_job").find("p").append('<em><input type="checkbox" name = "positionId" value = "'+positionInfo.positionId+'">'+positionInfo.positionName+'</em>');
				}
				
				
			}
		});
  }
</script>
</html>