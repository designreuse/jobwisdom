<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Date" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath %>css/change_price.css" type="text/css" />
<style>
	.zzc{font-size:14px;color:black;position: fixed;top: 0px;height: 1090px;left: 0px;width: 100%;z-index: 10000; background: rgba(102, 108, 121, 0.8);}    
	.zzc_sure{width:600px;height:430px;margin:50px auto;background:white}
	.zzc_sure>p{height:66px;text-align:center;font-size:18px;color:white;line-height:66px;color:white;background:#454560}
	.zzc_sure_content{padding:12px 19px}
	.zzc_sure_content>p{height:36px;line-height:36px;border-bottom:1px solid #dbdbdb}
	.zzc_sure_content>p>span{position:relative;left:300px}
	.zzc_sure_content>p>span em{color:#d01515}
	.sure_item_name{overflow:hidden;height:238px;border:1px solid #898989;border-radius:8px;margin-top:10px;position:relative}
    .sure_item_name li{float:left;width:111px;height:40px;text-align:center;line-height:40px;border:1px solid #cdd0d6;border-left:none}
    .wash_way{height:164px;overflow:overlay;margin-top:40px}
 	.wash_way_content{border-bottom:1px solid #898989;}
	.wash_way_content table{width:560px}
	.sure_item_name ul{position:absolute;background:#ebeff8}
	.zzc_sure_button{text-align:center;margin-top:25px}
	.zzc_sure_button button{width:130px;height:26px;border:none;text-align:center;line-height:26px;color:white;font-size:16px;background:#6c6c8b;margin:0 41px;border-radius:12px}
	.zzc_sure_button button:hover{background:#52526b}
	.wash_way_content input{width:64px;height:18px;border-radius:12px;padding-right:20px;border:1px solid #898989}
	 table tr:nth-child(1){font-weight:normal}
	.wash_way_content td{position:relative;height:40px;width:111px;border:1px solid #cdd0d6;border-left:none;vertical-align:middle;text-align:center;border-bottom:none}
	.wash_way_content em{position:absolute;right:18px;top:10px;color:#bcb5b5}

    .change_price_name i{cursor:pointer}
	.bubble_{z-index:2;position:absolute;background:#ebeff8;padding:4px;border-radius:4px;top:26px;left:-80px;font-size:12px;width:160px;overflow-wrap:break-word;display:none}
	.bubble_ em{
	    width: 0;
	    height: 0;
	    border-left: 8px solid transparent;
	    border-right:8px solid transparent;
	    border-bottom: 12px solid #ebeff8;
		position:absolute;
		left:80px;
		top:-10px
	}
	.bubble_{color:#3a3a3a}
</style>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	    <div style="font-size:12px;margin-left:24px"><img src="http://localhost:80/jobwisdom/images/bulb.jpg" style="width:20px;position:relative;top:6px;">注：拖拽流水单到目标单可实现流水单合并</div>
			<div class='content_right clearfix'>
			    <div class="change_price_content">
				   <div class="common_table">   
			  	   <table name = "memberInfoTable">
			  	     <c:choose>
							<c:when test="${selfCashierOrderDto.memberId == null}">
							    <tr>
							       <c:choose>
							          <c:when test="${selfCashierOrderDto.sex == '男'}">
							              <td rowspan="2"><img src="<%=qiniuPath %>system/profile/common_img_man.png"></td>
							          </c:when>
							          <c:otherwise>
							              <td rowspan="2"><img src="<%=qiniuPath %>system/profile/common_img_gril.png"></td>
							          </c:otherwise>
							       </c:choose>
								   <td>姓名</td>
								   <td>性别</td>
								   <!-- <td>手机号</td>
								   <td>开卡门店</td>
								   <td>余额</td>
								   <td>礼金</td>
								   <td>欠款</td>
								   <td>会员卡</td>
								   <td rowspan="2"><button>重新搜索</button></td> -->
								 </tr>
								 <tr>
								   <td>散客</td>
								   <td>${selfCashierOrderDto.sex }</td>
								   <!-- <td>132312312312</td>
								   <td>华南美联店</td>
								   <td style="color:#eb4749">12345.0</td>
								   <td style="color:#eb4749">21351</td>
								   <td style="color:#eb4749">21021</td>
								   <td >5张</td> -->
								 </tr>
							</c:when>
							<c:otherwise>
							    <tr>
								   <td rowspan="2"><img src="<%=qiniuPath %>${selfCashierOrderDto.memberInfo.headUrl }"></td>
								   <td>姓名</td>
								   <td>性别</td>
								   <td>手机号</td>
								   <td>开卡门店</td>
								   <td>余额</td>
								   <td>礼金</td>
								   <td>欠款</td>
								 </tr>
								 <tr>
								   <td>${selfCashierOrderDto.memberInfo.name }</td>
								   <td>${selfCashierOrderDto.memberInfo.sex }</td>
								   <td>${selfCashierOrderDto.memberInfo.phone }</td>
								   <td>${selfCashierOrderDto.memberInfo.storeName }</td>
								   <td style="color:#eb4749">${selfCashierOrderDto.memberInfo.balanceAmount }</td>
								   <td style="color:#eb4749">${selfCashierOrderDto.memberInfo.giftmoneyAmount }</td>
								   <td style="color:#eb4749">${selfCashierOrderDto.memberInfo.debtAmount }</td>
								 </tr>
							</c:otherwise>
				     </c:choose>
				     
				   </table>
			     </div>
				<c:if test="${selfCashierOrderDto.memberId != null}">
				    <div class="roll_money">
			            <span class="LeftBotton"><img src="<%=basePath %>images/left_click.png"></span>		
						<div class="money_card">
						 
						    <ul class="clearfix money_card_content"> 
						      <c:forEach items="${selfCashierOrderDto.subAccountList }" var="subAccount" varStatus="listSize">
						          <li <c:if test="${listSize.index == 0 }">class="active"</c:if> levelId = "${subAccount.subAccountId}" balanceAmount = "${subAccount.balanceAmount}" levelName = "${subAccount.levelName}" projectDiscount = "${subAccount.projectDiscount}" goodsDiscount = "${subAccount.goodsDiscount}">
								    <div class="original_card">
									  <div class="card_first"> ${subAccount.levelName } <span class="circle_pic" <c:if test="${listSize.index == 0 }">style="display: inline;"</c:if>><img src="<%=basePath %>images/circle.png"></span></div>
									  <p>项目折扣：<em class="rest">${subAccount.projectDiscount }</em></p>
									   <p>商品折扣：<em c>${subAccount.projectDiscount }</em></p>
									    <p>卡上余额：<em class="ten_money">${subAccount.balanceAmount}元</em></p>
									</div>
								  </li>
						      </c:forEach>
							</ul>
				      </div>
					     <span class="RightBotton"><img src="<%=basePath %>images/right_click.png"></span>
				   </div>
				</c:if>
				   <div class="change_price_name">
				      <p>
				         <span>项目名称</span>
				         <span>项目原价</span>
				         <span>折扣</span>
				         <span style="width:180px">疗程/礼金</span>
				         <span >抵扣金额</span>
				         <span>应收金额</span>
				         <span>改价金额</span>
				         <span>实收金额</span>
				      </p>
				      
				      <c:forEach items="${selfCashierOrderDto.orderDetails }" var="orderDetail" varStatus="listSize">
				         <div class="change_price__spread" detailId = "${orderDetail.detailId }" detailType = "${orderDetail.orderType }">  
							  <p>
							     <span><a name = "showStep">${orderDetail.projectName }</a></span>
							     <span>${orderDetail.projectPrice }</span>
							     <span name = "discountAmount" discountAmount = "${orderDetail.discountAmount }">${orderDetail.discountAmount }</span>
							     <span style="width:180px">
							         <select  name = "selectOff" detailid = "${orderDetail.detailId }" onchange="changeOff(this)">
							            <option uid= "" detailId = "${orderDetail.detailId}" offType = "0" offId = "${paymentOff.id }" offAmount = "0"  offName = "未使用优惠">未使用优惠</option>
							            <c:forEach items="${orderDetail.paymentOffList}" var = "paymentOff" varStatus="listSize">
							                <option uid= "${paymentOff.type}_${paymentOff.id}" detailId = "${orderDetail.detailId}" offType = "${paymentOff.type }" offId = "${paymentOff.id }" offAmount = "${paymentOff.amount }" maxOffAmount = "${paymentOff.amount }" offName = "${paymentOff.name }">${paymentOff.name} - ${paymentOff.amount}元 </option>
							            </c:forEach>
							         </select>
							     </span>
							     <span name = "offAmount" offAmount = "0">0</span>
							     <span><em name = "mustAmount" mustAmount = "0"></em><a href="javascript:;" class="adjust" onclick="showChangePric(this)">改</a></span>
							     <span><em name = "detailFree" detailFree = "0">0</em></span>
							     <span name = "actualAmount" actualAmount = "0"></span>
							  </p>
							  <div class="spread_content"> 
							      <c:forEach items="${orderDetail.stepList}" var = "step" varStatus="stepSize">
							         <div class="spread">
									    ${step.positionName }<span>
									           <input type="text" value="${step.employeeInfo.employeeCode } ${step.employeeInfo.name }">
									        </span>
									        <span>
									                             指定 <input type="checkbox" name ="isAssign" <c:if test="${step.isAssign == 1 }">checked="checked"</c:if>>
									         </span>
									         <c:if test="${stepSize.index == 0 }">
									            <span>
										                             预约 <input type="checkbox" name ="isAppoint" <c:if test="${step.isAppoint == 1 }">checked="checked"</c:if>>
										        </span>
									         </c:if>
									  </div>
							      </c:forEach>
							  </div>  
						 </div>
				      </c:forEach>
				   
				  </div> 
				     <div class="change_price_">  
					    <p>
					       <span>汇总</span>
					       <span>${selfCashierOrderDto.receivableAmount }</span>
					       <span>${selfCashierOrderDto.discountAmount }</span>
					       <span style="width:180px;height:26px"></span>
					       <span name = "totailOffAmount"></span>
					       <span name = "totailMustAmount"></span>
					       <span name = "tatailChangePric">0</span>
					       <span name = "totailActualAmount"></span>
					    </p>
				   </div>
				  <div class="change_price_way">
				    <c:if test="${memberType == 1 }">
				        <span>卡金支付<input type="text" id="cardpayAmount"></span>
				    </c:if>
				    <span>现金支付<input type="text" id="cashAmount" name="cashpayAmount"></span>
				    <span>银联支付<input type="text" id="unionpayAmount" name="unionpayAmount"></span>
					<span>微信支付<input type="text" id="wechatAmount" name="wechatAmount"></span>
					<span>支付宝支付<input type="text" id="alipayAmount" name="alipayAmount"></span>
					<span>团购支付<input type="text" id="groupAmount" name="groupAmount"></span>
					<c:if test="${memberType == 1 }">
					    <span>挂账<input type="text" id="debtAmount"></span>
					</c:if>
				  </div>
				  <div class="send_massage">
				    <c:if test="${memberType == 1 }">
				                  发送通知：<span><input type="radio" name="isNotify" value="1" checked="checked">是</span><span><input type="radio" name="isNotify" value="0">否</span>
				    </c:if>
				   <button onclick="submitOrderInfo()">结账</button>
				  </div>
				</div>
			  </div> 
		</div></div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<div class="zzc" style="display:none">
  <div class="zzc_change_price">
     <p>改价</p>
     <div class="zzc_change_price_content">
        <p>应收金额：<em name= "freeMustAmount"></em></p>
	    <p>改价后金额<input type="text" name = "freeAmount"></p>
	    <div class="change_reason">
	      <span>改价原因</span><textarea name = "orderRemark"></textarea>  
		</div>
		<div class="zzc_change_price_button">
		  <button onclick="confirm()">确定</button>
		  <button onclick = "canle()">取消</button>
		</div>
	 </div>
  </div>
</div>

<div class="hide" name = "payModal" style="font-size:14px;color:black;position: fixed;top: 0px;height: 1090px;left: 0px;width: 100%;z-index: 10000; background: rgba(102, 108, 121, 0.8);">
   <div class="zzc_sure">
     <p>结账成功,业绩提成确认</p>
     <div class="zzc_sure_content">
	     <p>订单编号:<em name = "orderCode"></em></p>
	     <div class="sure_item_name">
		    <ul class="clearfix" style="top:0;left:0">
			  <li>项目名称</li>
			  <li>岗位</li>
			  <li>服务人员</li>
			  <li>业绩</li>
			  <li>提成</li>
			</ul>
		   
	     <div class="wash_way">
		    <div class="wash_way_content clearfix">      

		   </div>
		 </div>  
		 
		    <ul class="clearfix" style="bottom:0;left:0;background:#f7f9fd">
			  <li>结算方式</li>
			  <li style="width:335px" name = "payTypeLi"></li>
			  <li>合集  ¥<em name="realAmount"></em></li>
			  
			</ul>
		 
		 </div>
		 
		 <div class="zzc_sure_button">
		   <button onclick="determineUpdate()">保存修改</button>
		   <button onclick="determine()">确认无误</button>
		 
		 </div>
	 </div>
   </div>
</div>


<%@ include file="/template/memberData.jsp" %>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<%-- <script type="text/javascript" src="<%=basePath %>js/cashier/cashier.js?date=<%=new Date().getTime() %>"></script>
 --%>
<script type="text/javascript" src="<%=basePath %>js/cashier/checkoutOrder.js"></script>
<script>
var orderId =  '${selfCashierOrderDto.orderId}';
var memberType = '${memberType}';
var discountMap = null;
var allOffMap = null;
if (memberType == 1) {
	var allOffMapStr = '${allOffMapStr}';
	allOffMap = eval("(" + allOffMapStr + ")");
	var discountMapStr = '${discountMapStr}';
	discountMap = eval("(" + discountMapStr + ")");
}

count=jQuery('.money_card_content li').size();
jQuery(function(){
 var now=0;
 //向右走
  jQuery(document).on('click','.RightBotton',function(){
     if(now<=count-5){
	    now+=1;
        jQuery('.money_card_content li').stop(true,true).animate({
	     left:-210*now
	   
	       }) 
	  }	
    		
    });
	//向左走
 jQuery(document).on('click','.LeftBotton',function(){
     if(now>=1){
	    now-=1;
        jQuery('.money_card_content li').stop(true,true).animate({
	     left:-210*now
	   
	       }) 
	  }	
    		
    });
 jQuery("a[name='showStep']").click(function(){
     jQuery(this).parents('.change_price__spread').find('.spread_content').stop(true,true).slideToggle();
  });
 
 jQuery(document).on('mouseover','.change_price_name i',function(){
	 jQuery(this).find('.bubble_').show();
   })
  jQuery(document).on('mouseout','.change_price_name i',function(){
	 jQuery(this).find('.bubble_').hide();
   })
 
})
</script>
</body>
</html>