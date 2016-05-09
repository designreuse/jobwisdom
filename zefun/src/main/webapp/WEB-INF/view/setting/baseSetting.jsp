<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/base_.css" type="text/css" />

<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
        <div class='content_right clearfix'>
		  <div class="base_button">
		   <button onclick="save();">保存</button>
		  
		  </div>
		     <table class="base_table">
			    <tr>
				  <td style="width:200px">提成是否扣除成本</td>
				  <td style="width:170px">
				     <span><input type="radio" name="costCommissionType" value="1" >是</span>
					 <span> <input type="radio" name="costCommissionType" value="0">否</span>
		          </td>
				  <td>计算员工业绩是否扣除项目/商品设置的成本价格</td>
				</tr>
			 
			    <tr>
				  <td>业绩是否减扣其他步骤的固定业绩值</td>
				  <td>
				     <span><input type="radio" name="commissionFixedType" value="1">是</span>
					 <span> <input type="radio" name="commissionFixedType" value="0">否</span>
		         </td>
				  <td>项目存在多个步骤时，如果此值为是，那么比例业绩计算规则为：项目实收－步骤中固定业绩值 * 步骤中业绩比例；固定业绩不考虑实收值，业绩不改变。</td>
				</tr>
			    
				  <tr>
				  <td>提成是否减扣其他步骤的固定提成值</td>
				  <td>
				     <span><input type="radio" name="yes">是</span>
					 <span> <input type="radio" name="yes">否</span>
		         </td>
				  <td>提成是否减扣其他步骤的固定提成值</td>
				</tr>
				
				 <tr>
				  <td>是否按照实收/业绩计算提成</td>
				  <td>
				     <span style="position:relative;left:12px">
				        <input type="radio" name="yes" >时收
				     </span>
					 <span> 
					    <input type="radio" name="yes">业绩
					 </span>
		         </td>
				  <td>是否按照实收/业绩计算提成</td>
				</tr>
				
				
				 <tr>
				  <td>业绩折扣-优惠券</td>
				  <td>
				     <span><input type="text" name="couponCommissionRate" value="${storeSetting.couponCommissionRate }">%</span>
					
		         </td>
				  <td>客户使用优惠券抵扣的金额乘以此比例为优惠券的业绩值，0-100</td>
				</tr>
				 <tr>
				  <td>业绩折扣-礼金</td>
				  <td>
				     <span><input type="text" name="giftCommissionRate" value="${storeSetting.giftCommissionRate }">%</span>
					
		         </td>
				  <td>客户使用礼金抵扣的金额乘以此比例为礼金的业绩值，0-100</td>
				</tr>
				
				 <tr>
				  <td>会员短信服务费</td>
				  <td>
				     <span><input type="text" name="smsFee" value="${storeSetting.smsFee }">元</span>
					
		         </td>
				  <td>会员短信服务费</td>
				</tr>
				
				 <tr>
				  <td>预约到时提醒</td>
				  <td>
				     <span><input type="text" name="appointRemindHour" value="${storeSetting.appointRemindHour }">H</span>
					
		         </td>
				  <td>快到预约时间时发出预约提醒通知(单位:小时)</td>
				</tr>
				
				 <tr>
				  <td>优惠券过期提醒</td>
				  <td>
				     <span><input type="text" value="3">天</span>
					
		         </td>
				  <td>优惠券过期提醒</td>
				</tr>
			 </table>
			 <div class="base_bottom">
			   <p>首次关注公众号的会员自动发送此奖励(多选择)</p>
			   <p>优惠券赠送：</p>
			   <div class="register">
			           新员工奖励：<i class="input_content">
			                <select class="chzn-select" id="couponSelect" multiple="true" data-placeholder="请选择要赠送的优惠券，可多选">
                               <c:forEach items="${couponList }" var="coupon">
                                   <option value="${coupon.couponId }">${coupon.couponName }</option>
                               </c:forEach>
                            </select>
			            </i>
			   </div>
			    <div class="register_">
			                礼金赠送：<input type="text" name="firstFollowGift" value="${storeSetting.firstFollowGift }">元
			   </div>
			 </div>
		  </div>
    </div></div>
</div>
<!-- 选择门店进行复制 -->
<div class="modal hide" id="select-store" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 580px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="javascript:jQuery('#select-store').addClass('hide')">
                    <span aria-hidden="true">×</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择门店复制
                </h4>
            </div>
            <div class="modal-body">
                <div class="border-head">
                    <input type="text" class="search-input" onkeyup="serchBrandByName(this)">
                    <button type="button" class="btn search-button" id="search-brands" onclick="serchBrandByName(this)">搜索</button>
                </div>
                <div class="border-content">
                    <div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
                        <ul class="ui-tabs-nav ui-helper-reset ui-helper-clearfix ui-widget-header ui-corner-all" role="tablist">
                            <li class="ui-state-default ui-corner-top" role="tab" tabindex="-1" aria-controls="tabs-1" aria-labelledby="ui-id-1" aria-selected="false"><a href="#tabs-1" class="ui-tabs-anchor" role="presentation" tabindex="-1" id="ui-id-1">门店信息大全</a></li>
                        </ul>
                        <!-- 自己的品牌 -->
                        <div id="tabs-1" aria-labelledby="ui-id-1" class="ui-tabs-panel ui-widget-content ui-corner-bottom" role="tabpanel" aria-expanded="false" aria-hidden="true" style="display: none;">
                            <c:forEach items="${storeInfos }" var="storeInfo">
                            <div class="brand" storeId="${storeInfo.storeId }" onclick="saveBrand(this)" style="cursor: pointer;">
                                <span>${storeInfo.storeName }</span>
                            </div>
                            </c:forEach>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn" onclick="javascript:jQuery('#select-store').addClass('hide')" data-dismiss="modal">取消</button>
                <button type="button" class="btn " onclick="realyCopyStore()" id="confirm-menu-url" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

var isCost = "${storeSetting.costCommissionType}";
var isFixed = "${storeSetting.commissionFixedType}";
var couponStr = "${storeSetting.firstFollowCoupon}";
jQuery(function(){
	jQuery("[name='costCommissionType'][value='" + isCost + "']").attr('checked',true);
	jQuery("[name='commissionFixedType'][value='" + isFixed + "']").attr('checked',true);
	
	if (!isEmpty(couponStr)) {
		var couponArr = couponStr.split(",");
		for (var i = 0; i < couponArr.length; i++) {
			jQuery("#couponSelect option[value='" + couponArr[i] + "']").attr("selected", "selected");
		}
		jQuery("#couponSelect").trigger("liszt:updated");
	}
	
});

function save(){
	var costCommissionType = jQuery("input[name='costCommissionType']:checked").val();
	var commissionFixedType = jQuery("input[name='commissionFixedType']:checked").val();
	var couponCommissionRate = jQuery("input[name='couponCommissionRate']").val();
	var giftCommissionRate = jQuery("input[name='giftCommissionRate']").val();
	var smsFee = jQuery("input[name='smsFee']").val();
	var appointRemindHour = jQuery("input[name='appointRemindHour']").val();
	var firstFollowGift = jQuery("input[name='firstFollowGift']").val();
	var data ={"costCommissionType" : costCommissionType, "commissionFixedType" : commissionFixedType, "couponCommissionRate" : couponCommissionRate,
    		"giftCommissionRate" : giftCommissionRate, "smsFee" : smsFee, "appointRemindHour" : appointRemindHour, "firstFollowGift" : firstFollowGift,
    		};
    var coupon = jQuery("#couponSelect").val();
    if (!isEmpty(coupon) && coupon.length > 0) {
    	data["firstFollowCoupon"] = coupon.toString();
    }
    jQuery.ajax({
        url : baseUrl + "system/action/baseSetting",
        type : "POST",
        data : data,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("更新成功");
        }
    });
}

//初始化门店数据
/* function initStore(){
	jQuery.ajax({
        url : baseUrl + "storeinfo/action/initialize",
        type : "POST",
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("初始化门店成功");
        }
    });
} */
var storeId = 0;
function realyCopyStore(){
	if (storeId == 0){
		dialog("请选择一个门店进行复制");
		return;
	}
	jQuery.ajax({
        url : baseUrl + "storeinfo/action/copy?copyStoreId="+storeId,
        type : "GET",
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            else {
            	dialog("初始化门店成功");
            	window.location = baseUrl + "project/view/projectList";
            }
        }
    });
}
function serchBrandByName(obj){
	var brandName = jQuery(obj).val();
	var brand1 = jQuery("#tabs-1").children("div[class='brand']");
	for (var i = 0; i < brand1.length; i++) {
		brand1.eq(i).hide();
		var name = brand1.eq(i).find("span").text();
		if(name.indexOf(brandName)!=-1){
			brand1.eq(i).show();
		}
	}
}

function saveBrand(obj){
	jQuery(obj).parent().children(".brand").each(function (){
		jQuery(this).css({ "border": "1px solid #fbd04d"});
	})
	storeId = jQuery(obj).attr("storeId");
	jQuery(obj).css({ "border": "1px solid #272624"});
}
</script>
</body>
</html>