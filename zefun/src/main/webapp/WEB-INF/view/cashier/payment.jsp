<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.Date" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath %>css/money.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	
			<div class='content_right clearfix'>
			     <p class="money_">
				    <c:choose>
		            	<c:when test="${statDto == null}">
							<span>已结账单数合计：<em>0单</em> </span>
						    <span>已结账金额合计：<em>¥ 0.00</em></span>
							<span>未结账单数合计：<em style="font-weight:bold;color:red">0单</em></span>
							<span>未结账金额合计：<em style="font-weight:bold;color:red">¥ 0.00</em></span>
		            	</c:when>
		            	<c:otherwise>
							<span>已结账单数合计：<em>${statDto.payCount}单</em> </span>
						    <span>已结账金额合计：<em>¥ ${statDto.payAmount}</em></span>
							<span>未结账单数合计：<em style="font-weight:bold;color:red">${statDto.unpayCount}单</em></span>
							<span>未结账金额合计：<em style="font-weight:bold;color:red">¥ ${statDto.unpayAmount}</em></span>
		            	</c:otherwise>
	            	</c:choose>
				 </p>
			    
			   <div class="clearfix seo_first">
			       <c:forEach var="selfCashier" items="${cashierDtoList}" varStatus="status">
			         <div class="money_content">
					    <p class="money_title">${selfCashier.orderCode}<em class="money_close"><img src="<%=basePath %>images/money_close.png"></em></p>
						<c:choose>
							<c:when test="${selfCashier.memberName == null}">
								<div class="money_head_pic clearfix">
									<div class="head_pic_1eft">
						              
						            </div>	
						            <div class="head_pic_right">
						                <p>顾客：散户</p>
										 <p>性别：${selfCashier.sex }</p>
						            </div>	
					            	<div class="refrush"><img src="<%=basePath %>images/freshen.png"></div>			 
								</div>
							</c:when>
							<c:otherwise>
								<div class="money_head_pic clearfix">
									<div class="head_pic_1eft" onclick="selectMemberInfo(${selfCashier.memberId})">
						                 <img src="<%=qiniuPath %>${selfCashier.memberInfo.headUrl }">
						            </div>	
						            <div class="head_pic_right">
						                <p>顾客：${selfCashier.memberName}</p>
										 <p>性别：${selfCashier.sex }</p>
						            </div>	
					            	<div class="refrush"><img src="<%=basePath %>images/freshen.png"></div>			 
								</div>
							</c:otherwise>
						</c:choose>
						<div class="consume">
						  <p>消费项目：
						      <c:forEach items="${selfCashier.orderDetails }" var="detail">
					         	${detail.projectName }
                              </c:forEach>
						  </p>
						  <p>开单人员：${selfCashier.operateEmployee.employeeCode} ${selfCashier.operateEmployee.name }</p>
						  <p>开单时间： ${selfCashier.createTime}</p>
						</div>
						<c:choose>
							<c:when test="${selfCashier.orderStatus == 1}">
								<div class="confition" onclick="showCashierDetail(${selfCashier.orderId})">
								   <img src="<%=basePath %>images/condition.png">进行中
								</div>
							</c:when>
							<c:when test="${selfCashier.orderStatus == 2 || selfCashier.orderStatus == 5}">
								<div class="confition active" onclick="showCashierDetail(${selfCashier.orderId})">
								   <img src="<%=basePath %>images/wait_money.png">待结账
								</div>
							</c:when>
						</c:choose>
					 </div>
			       </c:forEach>
				</div>
				
				
			</div>
		</div></div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<!--删除提示-->
<div class="modal hide" id="deleteOrderModal" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content confirm">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" onclick="deleteOrderModalCancel()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>

            <div class="modal-body confirm-body">
                取消该笔订单将直接删除，确认进行此操作吗？
            </div><!--modal-body-->

            <div class="modal-footer">
                <a class="btn cancel-btn modal-cancel" data-dismiss="modal" onclick="deleteOrderModalCancel()" href="#">我点错了</a>
                <a onclick="rechargeDeleteOrder()" class="btn btn-primary save-btn modal-confirm" data-dismiss="modal" href="#">取消订单</a>
            </div>
        </div>
    </div>
</div>

<div class="zzc">
   <div class="change_price">
       <p class="change_price_">确定价格<span class="money_close_"><img src="<%=basePath %>images/close.png"></span></p>
       
	     <div class="money_head_content">
	        <div class="money_head clearfix">
				<p class="money_head_p"><img src="assets/images/money_head.png"></p>
				<div class="boss_number_">
				  <span class="boss_sex">欧老板（男）</span>
				  <span class="order_number">订单号：<em name="orderNumber"></em></span>
				</div>
			</div>
			<div class="roll_money hide">
	            <span class="LeftBotton"><img src="<%=basePath %>images/left_click.png"></span>		
				<div class="money_card" >
				 
				    <ul class="clearfix money_card_content" id= "memberListUL"> 
					
					</ul>
		      </div>
			  <span class="RightBotton"><img src="<%=basePath %>images/right_click.png"></span>
		 </div>
			<table class="money_change" id = "projectTbody">
				       
			</table>
			
			<p class="money_get"><span>应收：<em name="totalReceivableMoney">156414</em></span><em>应收：<i id="totalRealMoney">156414</i></em></p>	
			<button class="money_next" nclick="nextCheckout()" >下一步</button>
		
        </div>
    </div>
</div>	

<div class="zzc1">
   <div class="sure_price">
      <p class="sure_price_"><span class="money_close_1"><img src="<%=basePath %>images/close.png"></span></p>
      
	  <div class="money_head_content">
	        <div class="money_head clearfix">
				<p class="money_head_p"><img src="assets/images/money_head.png"></p>
				<div class="boss_number_">
				   <span class="boss_sex">欧老板（男）</span>
				   <span class="order_number">订单号：<em name="orderNumber"></em></span>
				</div>
			   
			    
			</div>
			<table class="zzc_1_card">
			  <tr>
                <td>支付卡</td>
				<td>金额</td>
				<td>项目折扣</td>
				<td>商品折扣</td>
			  </tr>
			    <tr>
                <td>原价卡</td>
				<td>1231</td>
				<td>10</td>
				<td>10</td>
			  </tr>
			</table>
	   
	       <table class="zzc_1_card1">
			  <tr>
                <td>订单原价</td>
				<td>抵扣金额</td>
				<td>实收金额</td>
				<td>操作</td>
			  </tr>
			  <tr>
                <td >1231</td>
				<td>0</td>
				<td>1231</td>
				<td><span class="change_price_red">改价</span></td>
			  </tr>
			</table>
		    <ul class="how_pay clearfix">
			  <li> 卡金支付<input type="text" id="cardpayAmount"></li>
			  <li style="margin-left:24px"> 挂账<input type="text" id="debtAmount"></li>
			  <li style="margin-left:12px"> 现金支付<input type="text" id="cashAmount" name="cashpayAmount" ></li>
			  <li> 银联支付<input type="text" id="unionpayAmount" name="unionpayAmount"></li>
			  <li> 微信支付<input type="text" id="wechatAmount" name="wechatAmount"></li>
			  <li> 支护宝支付<input type="text" id="alipayAmount" name="alipayAmount"></li>
			  <li> 团购支付<input type="text" id="groupAmount" name="groupAmount"></li>
			</ul>
			<p class="money_get" style="margin-left:30px;margin-top:4px"><span >应收：<em name="totalReceivableMoney">156414</em></span><em>应收：<i id="totalRealMoney">156414</i></em> <span class="notice">发送通知：<input type="radio" name="yes"> 是<input type="radio" name="yes"> 否</span> </p>	
			<button class="money_over">结账</button>
		   <div class="change_price_content">
		   <span class="money_add"><img src="assets/images/money_add.png"></span>
		   <div class="triangle"><img src="assets/images/triangle.png"></div>
		      <button class="sure_change_price">确认</button>
			  <table class="sure_table">
			     <tr>
				  <td>项目名称</td>
				  <td>项目金额</td>
				  <td>改价金额</td>
			     </tr>
				 <tr>
				   <td>
				      <select>
					     <option>店长洗剪吹<option>
					  </select>	 
				   </td>
				   <td>12214</td>
				   <td><input type="text"></td>
			     </tr>
			  </table>
		   
		   </div>
        </div>
   </div>
    
</div>	


<%@ include file="/template/memberData.jsp" %>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<script type="text/javascript" src="<%=basePath %>js/cashier/cashier.js?date=<%=new Date().getTime() %>"></script>
<script>
   	var loginType = "${loginType}";
   	
    jQuery(function(){
        var count=jQuery('.money_card_content li').size();
        var now=0;
   	 //向右走
         jQuery('.RightBotton').click(function(){
            if(now<=count-4){
   		    now+=1;
   	        jQuery('.money_card_content li').stop(true,true).animate({
   		     left:-174*now
   		   
   		       }) 
   		  }	
           		
   	    });
   		//向左走
   	 jQuery('.LeftBotton').click(function(){
            if(now>=1){
   		    now-=1;
   	        jQuery('.money_card_content li').stop(true,true).animate({
   		     left:-174*now
   		   
   		       }) 
   		  }	
           		
   	    });
     })
      //原价卡上的图片的出现与消失
      jQuery(function(){
         jQuery('.money_card_content li').click(function(){
   	     jQuery(this).find('.circle_pic').show();
   		 jQuery(this).addClass('active')
   	  
   	  });
      
      })
      
      //点击下一步,关闭图标
      
      jQuery(function(){
        jQuery('.money_close_').click(function(){
   	    jQuery('.zzc').hide();
         });
        
        jQuery('.money_next').click(function(){
   	    jQuery('.zzc').hide();
   		jQuery('.zzc1').show();
   	    
        });
   	 
   	
        
      })
      
      //点击改价
      jQuery(function(){
         jQuery('.change_price_red').click(function(){
   	     jQuery('.change_price_content').show();
          });
   	   //点击改价页面的关闭
   	   
   	    jQuery('.money_close_1').click(function(){
   	     jQuery('.zzc1').hide();
          });
      });
   	
</script>
</body>
</html>