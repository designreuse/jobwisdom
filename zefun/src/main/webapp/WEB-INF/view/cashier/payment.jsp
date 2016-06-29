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
			    <div style="font-size:12px;margin-left:24px"><img src="<%=basePath %>images/bulb.jpg" style="width:20px;position:relative;top:6px;">注：拖拽流水单到目标单可实现流水单合并</div>
			   <div class="clearfix seo_first">
			       <c:forEach var="selfCashier" items="${cashierDtoList}" varStatus="status">
			         <div class="money_content" orderId = "${selfCashier.orderId}">
					    <p class="money_title">${selfCashier.orderCode}<em class="money_close" onclick="deleteOrder(this, ${selfCashier.orderId})"><img src="<%=basePath %>images/money_close.png"></em></p>
						<c:choose>
							<c:when test="${selfCashier.memberInfo == null}">
								<div class="money_head_pic clearfix">
									<div class="head_pic_1eft">
						                 <c:choose>
											<c:when test="${selfCashier.sex == '男'}">
												<img src="<%=qiniuPath %>system/profile/common_img_man.png">
											</c:when>
											<c:when test="${selfCashier.sex == '女'}">
												<img src="<%=qiniuPath %>system/profile/common_img_gril.png">
											</c:when>
										</c:choose>
						            </div>	
						            <div class="head_pic_right">
						                <div class="please">拖拽至 目标单 合并</div>
						                <p>顾客：散客</p>
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
						                <div class="please">拖拽至 目标单 合并</div>
						                <p>顾客：${selfCashier.memberInfo.name}</p>
										 <p>性别：${selfCashier.sex }</p>
						            </div>	
					            	<div class="refrush"><img src="<%=basePath %>images/freshen.png"></div>			 
								</div>
							</c:otherwise>
						</c:choose>
						<div class="consume">
						  <span class="copy_text"> </span>
						  <p>消费项目：
						      <em class="overflow_" name = "projectTextEm">
							      <c:forEach items="${selfCashier.orderDetails }" var="detail">
						         	${detail.projectName }
	                              </c:forEach>
                              </em>
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
							    <a href="<%=basePath %>selfcashier/action/orderinfo?orderId=${selfCashier.orderId}">
							        <div class="confition active">
									   <img src="<%=basePath %>images/wait_money.png">待结账
									</div>
							    </a>
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
				<p class="money_head_p"><img src=""></p>
				<div class="boss_number_">
				  <span class="boss_sex"></span>
				  <span class="order_number"><em name="orderNumber"></em></span>
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
			
			<p class="money_get"><span>应收：<em name="totalReceivableMoney"></em></span><em>实收：<i name="totalRealMoney"></i></em></p>	
			<button class="money_next" onclick="nextCheckout()" >下一步</button>
		
        </div>
    </div>
</div>	

<div class="zzc1">
   <div class="sure_price">
      <p class="sure_price_"><em onclick = "lastStep()" style="position:relative;left:-280px"><img src="<%=basePath %>images/come_back.png" style="width:28px"></em><span class="money_close_1"><img src="<%=basePath %>images/close.png"></span></p>
      
	  <div class="money_head_content">
	        <div class="money_head clearfix">
				<p class="money_head_p"><img src="assets/images/money_head.png"></p>
				<div class="boss_number_">
				   <span class="boss_sex"></span>
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
			  <tr name = "nextLevelId" levelId = "">
                <td name = "nextLevelName"></td>
				<td name = "nextBalanceAmount"></td>
				<td name = "nextProjectDiscount"></td>
				<td name = "nextGoodsDiscount"></td>
			  </tr>
			</table>
	   
	       <table class="zzc_1_card1">

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
			<p class="money_get" style="margin-left:30px;margin-top:4px"><span >应收：<em name="totalReceivableMoney"></em></span><em>应收：<i name="totalRealMoney"></i></em> <span class="notice">发送通知：<input type="radio" name="isNotify" value="1" checked="checked"> 是<input type="radio" name="isNotify" value="0"> 否</span> </p>	
			
			<button class="money_over" onclick="submitOrderInfo()">结账</button>
		   <div class="change_price_content">
		      <div class="triangle"><img src="<%=basePath %>images/triangle.png"></div>
		      <div class="money_close_" onclick = "cancleSure()"><img src="<%=basePath %>images/pay_close.png"></div>
			  <table class="sure_table" id = "updatePricTable">
			     <tr>
				  <td>项目名称</td>
				  <td>项目金额</td>
				  <td>改价金额</td>
				  <td><select name = 'projectSelectUpdatePric'  onchange="onchangeUpdatePric(this)"></select></td>
			     </tr>
			  </table>
		   	  <p class="adjust_price_saying"><span>改价说明<input type="text" name = "upRemark"></span><button class="sure_change_price" onclick="saveUpdatePric()" >确认</button></p>
		   </div>
        </div>
   </div>
    
</div>	


<%@ include file="/template/memberData.jsp" %>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>
<%-- <script type="text/javascript" src="<%=basePath %>js/cashier/cashier.js?date=<%=new Date().getTime() %>"></script>
 --%><script type="text/javascript" src="<%=basePath %>js/cashier/cashier.js"></script>
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

   	   //点击改价页面的关闭
   	   
   	    jQuery('.money_close_1').click(function(){
   	     jQuery('.zzc1').hide();
          });
      });
    
    
    jQuery(function() {
        
        jQuery(".money_content").draggable({
		 
          start:function(){
              jQuery(this).find('.please').show();
			  jQuery(this).css('z-index','2');
          } ,
          drag:function(){
                 jQuery(".money_content" ).draggable({ revert: "valid",revert: true });
				   $this_ = jQuery(this);
				    console.log('b')
          },
            stop:function(){
			     
              jQuery('.please').hide();
			   console.log('e');
               
			}
       
	   });
	   
     jQuery( ".money_content" ).droppable({
          drop: function(event, ui) {
       	     event = event ? event : window.event; 
       		 var obj = event.srcElement ? event.srcElement : event.target;
       		 console.log("编号"+ jQuery(obj).find(".money_title").text());
	         var like=window.confirm('确定合并');
		   if(like==true){
			   var thistext = jQuery($this_).find("projectTextEm").text();
			   var objtext = jQuery(obj).find("projectTextEm").text();
			   objtext = objtext + " " + thistext;
			   jQuery(obj).find("projectTextEm").text(objtext);
			   var mainOrderId = jQuery(obj).attr("orderId");
			   
			   var removeOrderId = jQuery($this_).attr("orderId");
			   
			   jQuery.ajax({
					type : "post",
					url : baseUrl + "selfcashier/action/mergeOrder",
					data : "mainOrderId="+mainOrderId+"&removeOrderId="+removeOrderId,
					async:false,//使用同步的Ajax请求 
					dataType : "json",
					success : function(e){
						if(e.code != 0){
							dialog(e.msg);
							return;
						}
						dialog("合并成功成功！");
						$this_.remove();
					}
				});
	       }
	       else{
	　　　　    jQuery( ".money_content").draggable({ revert: "valid",revert: true });
	       }
         }
     });
	 
    
 
  });
</script>
</body>
</html>