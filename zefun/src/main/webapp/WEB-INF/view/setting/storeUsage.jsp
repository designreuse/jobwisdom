<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>css/system.css" />
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
        <div class='content_right clearfix'>
		      <div class="system_content clearfix">
			     <div class="system_content_detail">
				    <p class="system_time">使用期限</p>
				     <div class="system_content_time">
					   <span>累计充值：${storeAccount.totalDays }天</span>
					   <span>当前剩余：${storeAccount.balanceDays }天</span>
		            </div>
					<button class="recharge">充值</button>
				 </div>
				 
				 
			  
			     <div class="system_content_detail">
				    <p class="system_time">短信服务</p>
				     <div class="system_content_time">
					   <span>累计充值：${storeAccount.totalSms }条</span>
					   <span>当前剩余：${storeAccount.balanceSms }条</span>
		            </div>
					<button class="recharge">充值</button>
				 </div>
				 
				    <div class="system_content_detail">
				    <p class="system_time">店铺状态</p>
				     <div class="system_content_time">
					   <span>使用状态：
					   <c:choose>
                      		<c:when test="${storeAccount.storeStatus == 2 }">
                      			<div class="state-toggle state-active cursor">试运营</div>
                        		<div class="state-toggle state-neg cursor" data-toggle="modal" data-target="#cleanDataTip">正式运营</div>
                        		<span class="ml30 font-999">*目前是试运营状态，点击可切换正式运营状态。</span>
                      		</c:when>
                      		<c:when test="${storeAccount.storeStatus == 3 }">
                      			正式运营
                      		</c:when>
                      	</c:choose>
					   </span>
					  
		            </div>
					
				 </div>
				 
			  </div>
			
			
		  </div>
		  
		  
		<div class="modal hide" id="cleanDataTip" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content confirm">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		                <h4 class="modal-title" id="myModalLabel">温馨提示</h4>
		            </div>
		            <div class="modal-body confirm-body">
		            	此次操作将会清除所有会员卡、会员数据、订单数据，您确定要切换到正式营业状态吗？
		            </div><!--modal-body-->
		            <div class="modal-footer">
		                <a class="btn cancel-btn modal-cancel" data-dismiss="modal"  href="javascrip:void(0);">取消</a>
		                <a onclick="cleanData()" class="btn btn-primary save-btn modal-confirm" href="javascrip:void(0);">保存</a>
		            </div>
		        </div>
		    </div>
		</div>
		</div>
    </div>
</div>

<script type="text/javascript">
function cleanData(){
	jQuery("#cleanDataTip").modal('hide');
	jQuery.ajax({
        url : baseUrl + "system/action/cleanData",
        type : "POST",
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("已为您切换到正式营业状态");
            jQuery(".state-control").html("正式运营");
        }
    });
}
</script>
</body>
</html>