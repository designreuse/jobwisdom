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
					
						<a href="<%=basePath %>KeepAccounts/initializeManuallyOpenOrder?orderId=${selfCashier.orderId}">
							<div class="confition" onclick="showCashierDetail(${selfCashier.orderId})">
							   <img src="<%=basePath %>images/condition.png">返单
							</div>
					    </a>

					    <a href="<%=basePath %>selfcashier/action/orderinfo?orderId=${selfCashier.orderId}">
					        <div class="confition active">
							   <img src="<%=basePath %>images/wait_money.png">结账
							</div>
					    </a>
	
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