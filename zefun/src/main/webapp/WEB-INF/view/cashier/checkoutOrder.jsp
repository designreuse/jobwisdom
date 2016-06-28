<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Date" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath %>css/change_price.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	
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
						          <li <c:if test="${listSize.index == 0 }">class="active"</c:if>>
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
				         <div class="change_price__spread">  
							  <p>
							     <span>${orderDetail.projectName }</span>
							     <span>${orderDetail.projectPrice }</span>
							     <span><input type="text" placeholder="50" value="${orderDetail.discountAmount }"></span>
							     <span style="width:180px">
							         <select  name = "selectOff" detailid = "${orderDetail.detailid }" >
							            <c:forEach items="${orderDetail.paymentOffList}" var = "paymentOff" varStatus="listSize">
							                <option uid= "${paymentOff.type}_${paymentOff.id}" detailId = "${orderDetail.orderDetail}" offType = "${paymentOff.type }" offType = "${paymentOff.type }" offId = "${paymentOff.id }" offAmount = "${paymentOff.amount }" offAmount = "${paymentOff.amount }" offName = "${paymentOff.name }">${paymentOff.name}_${paymentOff.amount}元 </option>
							            </c:forEach>
							         </select>
							     </span>
							     <span>128</span>
							     <span>123<a href="javascript:;" class="adjust">改</a></span>
							     <span class="adjust_price"><!-- -50<i>!</i> --></span>
							     <span>5000</span>
							  </p>
							  <div class="spread_content"> 
								  <div class="spread">
								     计师<span><select style="width:130px;padding-left:50px"><option>1100 马冬梅</option></select></span><span><select><option>指定</option></select></span><span><select><option>预约</option></select></span><span>业绩：<select><option>1121</option></select></span><span>提成：<input type="text"></span>
								  </div>
								   <div class="spread">
								     计师<span><select style="width:130px;padding-left:50px"><option>1100 马冬梅</option></select></span><span><select><option>指定</option></select></span><span><select><option>预约</option></select></span><span>业绩：<select><option>1121</option></select></span><span>提成：<input type="text"></span>
								  </div>
								   <div class="spread">
								     计师<span><select style="width:130px;padding-left:50px"><option>1100 马冬梅</option></select></span><span><select><option>指定</option></select></span><span><select><option>预约</option></select></span><span>业绩：<select><option>1121</option></select></span><span>提成：<input type="text"></span>
								  </div>
							  </div>  
						 </div>
				      </c:forEach>
				   
				  </div> 
				     <div class="change_price_">  
					    <p><span>汇总</span><span>1111</span><span>1112221</span><span style="width:180px">121212</span><span>121212</span><span>111000</span><span class="adjust_price">-50</span><span>2500000</span></p>
				   </div>
				  <div class="change_price_way">
				    <span>卡金支付<input type="text"></span>
				    <span>挂账<input type="text"></span>
				    <span>现金支付<input type="text"></span>
					<span>团购支付<input type="text"></span>
					<span>银联支付<input type="text"></span>
					<span>微信支付<input type="text"></span>
					<span>支付宝支付<input type="text"></span>
				  </div>
				  <div class="send_massage">
				   发送通知：<span><input type="radio" name="yes">是</span><span><input type="radio" name="yes">否</span><button>结账</button>
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
	    <p>改价金额<input type="text"><em>*扣款填写负数</em></p>
	    <div class="change_reason">
	      <span>改价原因</span><textarea></textarea>  
		</div>
		<div class="zzc_change_price_button">
		  <button>确定</button>
		  <button>取消</button>
		</div>
	 </div>
  </div>
</div>


<%@ include file="/template/memberData.jsp" %>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<%-- <script type="text/javascript" src="<%=basePath %>js/cashier/cashier.js?date=<%=new Date().getTime() %>"></script>
 --%><script type="text/javascript" src="<%=basePath %>js/cashier/cashier.js"></script>
<script>
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
 jQuery('.adjust').click(function(){
     jQuery(this).parents('.change_price__spread').find('.spread_content').stop(true,true).slideToggle();
  });
})


//原价卡上的图片的出现与消失
jQuery(function(){
  jQuery('.money_card_content li').click(function(){
     jQuery(this).find('.circle_pic').show();
	 jQuery(this).addClass('active')
  
  });

})
</script>
</body>
</html>