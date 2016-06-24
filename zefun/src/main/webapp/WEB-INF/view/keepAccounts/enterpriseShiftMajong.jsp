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
				     <p><button onclick="addShiftMajong()">新增轮牌</button></p>
				     <div class="card_table">
					     <table>
						    <tr>
							   <td>名称</td>
							   <td style="width:220px">角色</td>
							   <td>选择门店</td>
							   <td>技师</td>
							   <td>设计师</td>
							   <td>助理</td>
							   <td>操作</td>
							</tr>
							 <tr>
							   <td>理发牌</td>
							   <td>美发（容/甲）/手艺师</td>
							   <td>小高分店</td>
							   <td>是</td>
							   <td>是</td>
							   <td>是</td>
							   <td><img src="assets/images/add_store_1.png"><img src="assets/images/add_store_2.png"></td>
							</tr>
							 <tr>
							   <td>理发牌</td>
							   <td>美发（容/甲）/手艺师</td>
							   <td>小高分店</td>
							   <td>是</td>
							   <td>是</td>
							   <td>是</td>
							   <td><img src="assets/images/add_store_1.png"><img src="assets/images/add_store_2.png"></td>
							</tr>
							 <tr>
							   <td>理发牌</td>
							   <td>美发（容/甲）/手艺师</td>
							   <td>小高分店</td>
							   <td>是</td>
							   <td>是</td>
							   <td>是</td>
							   <td><img src="assets/images/add_store_1.png"><img src="assets/images/add_store_2.png"></td>
							</tr>
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
		  <p><span>选择岗位:</span><em><input type="checkbox" name = "positionId">设计师</em><em><input type="checkbox" name = "positionId">技师</em><em><input type="checkbox" name = "positionId">助理</em></p>

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
   	for (var i = 0; i < positionIdList.length; i++) {
   		positionId += positionIdList[i];
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
   		data : "shiftMahjongId="+shiftMahjongId+"&shiftMahjongName="+shiftMahjongName+"&shiftMahjongUp="+shiftMahjongUp+"&shiftMahjongRule="+shiftMahjongRule+"&nature="+nature+"&positionId="+positionId,
   		async:false,//使用同步的Ajax请求  
   		dataType : "json",
   		success : function(e){
   			if(e.code != 0){
   				dialog(e.msg);
   				return;
   			}
   			location.reload();
   		}
   	});
   });
</script>
</html>