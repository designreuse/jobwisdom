<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.Date" %>
<link rel="stylesheet" href="<%=basePath%>css/commission.css" type="text/css" />
<style>
.commision_content_top li.active{background:#ebeff8}
.commision_content_top li{cursor:pointer}

</style>
<script>
     //切换
      jQuery(function(){
	    jQuery('.money_commission_content:gt(0)').hide();
	    jQuery('.commision_content_top .another').click(function(){
	    	jQuery(this).addClass('active').siblings('.another').removeClass('active');	  
	    	jQuery('.money_commission_content').eq(jQuery(this).index()-1).show().siblings('.money_commission_content').hide();
	    	jQuery("button[name='save']").attr("onclick","save("+jQuery(this).index()+")");
	    })
	    
	  });	
</script>
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>  
				 <div class='content_right clearfix'>
			    <div class="commision_content">
				   <ul class="commision_content_top clearfix">
				      <li style="width:136px;background:#ebeff8;cursor:text">分配对象</li>
				      <li class="active another">商品</li>
					  <li class="another">疗程</li>
				   </ul>
				 <div class="money_commission_content">
				   <div class="money_commission clearfix" style="border-bottom:1px solid #898989">
				      <div class="money_commission_left">
				        现金分配方式
					  </div>
					   <div class="money_commission_right">
				          <div class="money_commission_right_detail">
						    <p>二人分配</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input  id="lyjfp1_1" type="text"><em>%</em></span>员工提成<span><input id="lygfp1_1" type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input  id="lyjfp1_2" type="text"><em>%</em></span>员工提成<span><input id="lygfp1_2" type="text"><em>%</em></span></p>
							
							</div>
						  </div>
						 
						  <div class="money_commission_right_detail">
						    <p>三人分配</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input  id="syjfp1_1" type="text"><em>%</em></span>员工提成<span><input id="sygfp1_1" type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input  id="syjfp1_2" type="text"><em>%</em></span>员工提成<span><input id="sygfp1_2" type="text"><em>%</em></span></p>
							   <p>第3人：业绩分配<span><input  id="syjfp1_3" type="text"><em>%</em></span>员工提成<span><input id="sygfp1_3" type="text"><em>%</em></span></p>
							</div>
						  
						  </div>
						 
					  </div>
			
				   </div>
				   <div class="money_commission clearfix">
				      <div class="money_commission_left">
				        卡金提成方式
					  </div>
					   <div class="money_commission_right">
				          <div class="money_commission_right_detail">
						    <p>二人提成</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input   id="lyjtc1_1" type="text"><em>%</em></span>员工提成<span><input id="lygtc1_1" type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input   id="lyjtc1_2" type="text"><em>%</em></span>员工提成<span><input id="lygtc1_2" type="text"><em>%</em></span></p>
							
							</div>
						  
						  </div>
						 
						  <div class="money_commission_right_detail">
						    <p>三人提成</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input id="syjtc1_1" type="text"><em>%</em></span>员工提成<span><input id="sygtc1_1"  type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input id="syjtc1_2" type="text"><em>%</em></span>员工提成<span><input id="sygtc1_2"  type="text"><em>%</em></span></p>
							   <p>第3人：业绩分配<span><input id="syjtc1_3" type="text"><em>%</em></span>员工提成<span><input id="sygtc1_3"  type="text"><em>%</em></span></p>
							</div>
						  
						  </div>
						 
					  </div>
			
				   </div>
				</div>
				
				 <div class="money_commission_content">
				   <div class="money_commission clearfix" style="border-bottom:1px solid #898989">
				      <div class="money_commission_left">
				        现金分配方式
					  </div>
					   <div class="money_commission_right">
				          <div class="money_commission_right_detail">
						    <p>二人分配</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input id="lyjfp2_1" type="text"><em>%</em></span>员工提成<span><input id="lygfp2_1" type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input id="lyjfp2_2" type="text"><em>%</em></span>员工提成<span><input id="lygfp2_2" type="text"><em>%</em></span></p>
							
							</div>
						  
						  </div>
						 
						  <div class="money_commission_right_detail">
						    <p>三人分配</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input id="syjfp2_1" type="text"><em>%</em></span>员工提成<span><input id="sygfp2_1" type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input id="syjfp2_2" type="text"><em>%</em></span>员工提成<span><input id="sygfp2_2" type="text"><em>%</em></span></p>
							   <p>第3人：业绩分配<span><input id="syjfp2_3" type="text"><em>%</em></span>员工提成<span><input id="sygfp2_3" type="text"><em>%</em></span></p>
							</div>
						  
						  </div>
						 
					  </div>
			
				   </div>
				   <div class="money_commission clearfix">
				      <div class="money_commission_left">
				        卡金提成方式
					  </div>
					   <div class="money_commission_right">
				          <div class="money_commission_right_detail">
						    <p>二人提成</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input id="lyjtc2_1"  type="text"><em>%</em></span>员工提成<span><input id="lygtc2_1"  type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input id="lyjtc2_2"  type="text"><em>%</em></span>员工提成<span><input id="lygtc2_2"  type="text"><em>%</em></span></p>
							</div>
						  </div>
						 
		
						  <div class="money_commission_right_detail">
						    <p>三人提成</p>
						    <div class="money_commission_right_detail_content">
							   <p>第1人：业绩分配<span><input id="syjtc2_1" type="text"><em>%</em></span>员工提成<span><input id="sygtc2_1" type="text"><em>%</em></span></p>
							   <p>第2人：业绩分配<span><input id="syjtc2_2" type="text"><em>%</em></span>员工提成<span><input id="sygtc2_2" type="text"><em>%</em></span></p>
							   <p>第3人：业绩分配<span><input id="syjtc2_3" type="text"><em>%</em></span>员工提成<span><input id="sygtc2_3" type="text"><em>%</em></span></p>
							</div>
						  
						  </div>
						 
					  </div>
			
				   </div>
				</div>
				
				</div>
				<div class="commision_button">
				   <button name="save" onclick="save(1)">确认</button>
				</div>
		  </div>
		</div>
    </div>
</div>

<script type="text/javascript">
var cssWidth = 200;
var cssHeight = 200;
var qiniuUrl = '<%=qiniuPath%>';
var imgObject;

var commissionSchemeListStr =  '${commissionSchemeList }';

var commissionSchemeList;
if (!isEmpty(commissionSchemeListStr)) {
	commissionSchemeList = eval("("+commissionSchemeListStr+")");
	jQuery.each(commissionSchemeList,function(name, value){
		
		var type =value.commissionType;
		
		var cash_two_commission = value.cashTwoCommission;
		var cash_three_commission = value.cashThreeCommission;
		var card_two_commission = value.cardTwoCommission;
		var card_three_commission = value.cardThreeCommission;
		 //两人现金分配
		var cashTwoCommission = cash_two_commission.split(",");
		var cash0 = cashTwoCommission[0].split(":");
		var cash1 = cashTwoCommission[1].split(":");
		
		 //三人现金分配
		var cashThreeCommission = cash_three_commission.split(",");
		var cashT0 = cashThreeCommission[0].split(":");
		var cashT1 = cashThreeCommission[1].split(":");
		var cashT2 = cashThreeCommission[2].split(":");
		
		
		 //两人卡金分配
		var cardTwoCommission = card_two_commission.split(",");
		var card0 = cardTwoCommission[0].split(":");
		var card1 = cardTwoCommission[1].split(":");
		
		 //三人卡金分配
		var cardThreeCommission = card_three_commission.split(",");
		var cardT0 = cardThreeCommission[0].split(":");
		var cardT1 = cardThreeCommission[1].split(":");
		var cardT2 = cardThreeCommission[2].split(":");
		
		jQuery("#lyjfp"+type+"_1").val(cash0[0]);
		jQuery("#lygfp"+type+"_1").val(cash0[1]);
		jQuery("#lyjfp"+type+"_2").val(cash1[0]);
		jQuery("#lygfp"+type+"_2").val(cash1[1]);
		
		jQuery("#syjfp"+type+"_1").val(cashT0[0]);
		jQuery("#sygfp"+type+"_1").val(cashT0[1]);
		jQuery("#syjfp"+type+"_2").val(cashT1[0]);
		jQuery("#sygfp"+type+"_2").val(cashT1[1]);
		jQuery("#syjfp"+type+"_3").val(cashT2[0]);
		jQuery("#sygfp"+type+"_3").val(cashT2[1]);
		
		jQuery("#lyjtc"+type+"_1").val(card0[0]);
		jQuery("#lygtc"+type+"_1").val(card0[1]);
		jQuery("#lyjtc"+type+"_2").val(card1[0]);
		jQuery("#lygtc"+type+"_2").val(card1[1]);
		
		jQuery("#syjtc"+type+"_1").val(cardT0[0]);
		jQuery("#sygtc"+type+"_1").val(cardT0[1]);
		jQuery("#syjtc"+type+"_2").val(cardT1[0]);
		jQuery("#sygtc"+type+"_2").val(cardT1[1]);
		jQuery("#syjtc"+type+"_3").val(cardT2[0]);
		jQuery("#sygtc"+type+"_3").val(cardT2[1]);
	})
}

var storeEmployeeListStr =  '${storeEmployeeListStr}';
var storeEmployeeList;
if (!isEmpty(storeEmployeeListStr)) {
	storeEmployeeList = eval("(" + storeEmployeeListStr + ")");

}

var enterpriseEdition = '${enterpriseAccount.enterpriseEdition }';
var alreadyStoreNum = '${enterpriseAccount.alreadyStoreNum}';
var priceMoneyOrTimeStr = '${priceMoneyOrTimeStr}';
var priceMoneyOrTime;
if (!isEmpty(priceMoneyOrTimeStr)) {
	priceMoneyOrTime = eval("(" + priceMoneyOrTimeStr + ")");
}



function save(type){

	var lyjfp1 = jQuery("#lyjfp"+type+"_1").val();
	var lygfp1 =jQuery("#lygfp"+type+"_1").val();
	
	var lyjfp2 = jQuery("#lyjfp"+type+"_2").val();
	var lygfp2 =jQuery("#lygfp"+type+"_2").val();
	var number1 = lyjfp1*1+lyjfp2*1;
	var number2 = lygfp1*1+lygfp2*1;
	if(number1>100){
		dialog("现金二人分配业绩不能大于100%");
		return;
	}
	if(number2>100){
		dialog("现金二人分配提成不能大于100%");
		return;
	}
	
	
	var cash_two_commission =lyjfp1.toString()+":"+lygfp1.toString()+","+lyjfp2.toString()+":"+lygfp2.toString();
	
	var syjfp1 =jQuery("#syjfp"+type+"_1").val();
	var sygfp1 =jQuery("#sygfp"+type+"_1").val();
	
	var syjfp2 =jQuery("#syjfp"+type+"_2").val();
	var sygfp2 =jQuery("#sygfp"+type+"_2").val();
	
	var syjfp3 =jQuery("#syjfp"+type+"_3").val();
	var sygfp3 =jQuery("#sygfp"+type+"_3").val();
	number1 = syjfp1*1 + syjfp2*1 + syjfp3*1;
	number2 = sygfp1*1 + sygfp2*1 + sygfp3*1;
	if(number1>100){
		dialog("现金三人人分配业绩不能大于100%");
		return;
	}
	if(number2>100){
		dialog("现金三人人分配提成不能大于100%");
		return;
	}
	
	var cash_three_commission = syjfp1.toString()+":"+sygfp1.toString()+","+syjfp2.toString()+":"+syjfp2.toString()+","+syjfp3.toString()+":"+syjfp3.toString();
	
	
	var lyjtc1 = jQuery("#lyjtc"+type+"_1").val();
	var lygtc1 =jQuery("#lygtc"+type+"_1").val();
	
	var lyjtc2 = jQuery("#lyjtc"+type+"_2").val();
	var lygtc2 =jQuery("#lygtc"+type+"_2").val();
	var card_two_commission =lyjtc1+":"+lygtc1+","+lyjtc2+":"+lygtc2;
	number1 = lyjtc1*1 + lyjtc2*1;
	number2 = lygtc1*1 + lygtc2*1;
	if(number1>100){
		dialog("卡金二人分配业绩不能大于100%");
		return;
	}
	if(number2>100){
		dialog("现金二人分配提成不能大于100%");
		return;
	}
	
	var syjtc1 =jQuery("#syjtc"+type+"_1").val();
	var sygtc1 =jQuery("#sygtc"+type+"_1").val();
	
	var syjtc2 =jQuery("#syjtc"+type+"_2").val();
	var sygtc2 =jQuery("#sygtc"+type+"_2").val();
	
	var syjtc3 =jQuery("#syjtc"+type+"_3").val();
	var sygtc3 =jQuery("#sygtc"+type+"_3").val();
	number1 = syjtc1*1 + syjtc2*1 + syjtc3*1;
	number2 = sygtc1*1 + sygtc2*1 + sygtc3*1;
	if(number1>100){
		dialog("卡金三人人分配业绩不能大于100%");
		return;
	}
	if(number2>100){
		dialog("现金三人人分配提成不能大于100%");
		return;
	}
	var card_three_commission = syjtc1+":"+sygtc1+","+syjtc2+":"+syjtc2+","+syjtc3+":"+syjtc3;

   jQuery.ajax({
		type : "post",
		url : baseUrl + "commissionScheme/view/view_save_commission_scheme",
		data : JSON.stringify({"commissionType" : type ,"cashTwoCommission" : cash_two_commission ,"cashThreeCommission" : cash_three_commission,
			"cardTwoCommission" : card_two_commission ,"cardThreeCommission":card_three_commission }),
		dataType : "json",
		contentType : "application/json",
		success : function(e) {
			dialog("保存成功");
		}
	});
}


</script>
<script type="text/javascript" src="<%=basePath %>/js/setting/storeList.js"></script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>
<script src="<%=basePath%>js/common/city-picker.data.js"></script>
<script src="<%=basePath%>js/common/city-picker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>

</body>
</html>