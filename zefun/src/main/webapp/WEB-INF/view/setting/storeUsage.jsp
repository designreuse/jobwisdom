<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
<body>
<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp"%>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp"%>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
        <%@ include file="/top.jsp"%>
        <div class="maincontent">
		  <div class="contentinner">
		
		    <div class="widgetcontent">
		      <!--使用期限-->
		      <div class="qixian">
		        <div class="border-head">
		          <span>使用期限</span>
		        </div>
		        <div class="border-content">
		          <div class="show"></div>
		          <div class="desc">
		              <div class="font-size-14">累计充值: ${storeAccount.totalDays }天</div>
		              <div class="font-size-14">当前剩余: ${storeAccount.balanceDays }天</div>
		              <div class="btn w80" data-toggle="modal" data-target="#chongzhi">充值</div>
		          </div>
		          <div class="clearfix"></div>
		        </div>
		      </div>
		
		      <!--短信剩余-->
		      <div class="shengyu">
		          <div class="border-head">
		              <span>短信服务</span>
		          </div>
		          <div class="border-content">
		              <div class="show"></div>
		              <div class="desc">
		                  <div>累计充值: ${storeAccount.totalSms }条</div>
		                  <div>当前剩余: ${storeAccount.balanceSms }条</div>
		                  <div class="btn w80" data-toggle="modal" data-target="#chongzhi">充值</div>
		              </div>
		              <div class="clearfix"></div>
		          </div>
		      </div>
		
		      <div class="clearfix"></div>
		    </div>
		
		    <div class="widetcontent">
		      <!--店铺状态-->
		      <div class="shop-state">
		        <div class="border-head">店铺状态</div>
		        <div class="content">
		          <table class="table nobordered-table lr-bordered-table">
		              <tbody>
		              <!-- <tr>
		                  <td class="text-right fb w100">店铺状态：</td>
		                  <td>
		                  </td>
		              </tr> -->
		              <tr>
		                  <td class="text-right fb w100">使用状态：</td>
		                  <td>
		                      <div class="state-control">
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
		                      </div>
		                  </td>
		              </tr>
		              </tbody>
		          </table>
		        </div>
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
		
        <!--RIGHT PANEL结束 -->
        <div class="clearfix"></div>
        <div id="star"></div>
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