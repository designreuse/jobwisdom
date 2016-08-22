<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/base_manage.css" type="text/css" />

<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right clearfix">
					<div class="base_manage">
						<table>
							<tbody>
							    <!-- <tr>
									<td>提成计算方式</td>
									<td>
										<span><input type="radio" name="costCommissionType" value="1">业绩</span> <span><input type="radio" name="costCommissionType" value="0">毛利</span>
									</td>
									<td>提成为比例计算的时候,根据毛利（实收-成本=毛利）还是业绩来计算</td>
								</tr> -->
								<tr>
									<td>业绩是否扣除成本</td>
									<td>
										<span><input type="radio" name="costCommissionType" value="1">是</span> <span><input type="radio" name="costCommissionType" value="0">否</span>
									</td>
									<td>计算员工业绩是否扣除项目／商品设置的成本价格</td>
								</tr>
								<tr>
									<td>业绩是否减扣其他步骤的固定业绩值</td>
									<td>
										<span><input type="radio" name="commissionFixedType" value="1">是</span> <span><input type="radio" name="commissionFixedType" value="0">否</span>
									</td>
									<td style="width: 493px;">项目存在多个步骤时，如果此值为是，那么比例业绩计算规则为:项目实收-步骤中固定业绩值*步骤中业绩比例；固定业绩不考虑实收值，业绩不改变</td>
								</tr>
								<tr>
									<td>价格/业绩折后小数处理</td>
									<td>
										<span><input type="radio" name="isDecimalPoint" value="1">四舍五入取整数</span> <span><input type="radio" name="isDecimalPoint" value="0">保留两位小数</span>
									</td>
									<td style="width: 493px;">订单结算完成后，支付金额及员工业绩值四舍五入取整或保留两位小数</td>
								</tr>
								<tr>
									<td>允许库存负数</td>
									<td>
										<span><input type="radio" name="isGoodsMinus" value="1">是</span> <span><input type="radio" name="isGoodsMinus" value="0">否</span>
									</td>
									<td style="width: 493px;">开单、商城中商品库存不足时，是否允许继续开单购买</td>
								</tr>
								<!-- <tr>
									<td>提成是否减扣其他步骤的固定提成值</td>
									<td>
										<span><input type="radio" name="yes2">是</span> <span><input type="radio" name="yes2">否</span>
									</td>
									<td>提成是否减扣其他步骤的固定提成值</td>
								</tr>
								<tr>
									<td>是否按照实收／业绩计算提成</td>
									<td>
										<span><input type="radio" name="yes3">实收</span> <span><input type="radio" name="yes3">业绩</span>
									</td>
									<td>是否按照实收／业绩计算提成</td>
								</tr> -->
								<tr>
									<td>业绩折扣-优惠卷</td>
									<td>
										<span><input type="text" name="couponCommissionRate" value="${storeSetting.couponCommissionRate }">%</span>
									</td>
									<td>客户使用优惠卷抵扣的金额乘以此比例为优惠卷的业绩值，0-100</td>
								</tr>
								<tr>
									<td>业绩折扣-礼金</td>
									<td>
										<span><input type="text" name="giftCommissionRate" value="${storeSetting.giftCommissionRate }">%</span>
									</td>
									<td>客户使用礼金抵扣的金额乘以此比例为礼金的业绩值，0-100</td>
								</tr>
								<tr>
									<td>开始手牌号</td>
									<td>
										<span><input type="text" name="startHandNumber" value="${storeSetting.startHandNumber }">号</span>
									</td>
									<td>从100开始</td>
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
									<td>改价授权金额</td>
									<td>
										<span><input type="text" name="updateMoneyAuthorize" value="${storeSetting.updateMoneyAuthorize }">元</span>
									</td>
									<td>结账时，该订单改价金额超出该值，则需要授权</td>
								</tr>
								<!-- <tr>
									<td>优惠卷过期提醒</td>
									<td>

										<span><input type="text">天</span>
									</td>
									<td>优惠卷过期提醒</td>
								</tr> -->
							</tbody>
						</table>
					</div>

					<div class="base_manage_content">
						<p>
							首次关注公众号的会员自动发送此奖励(多选择)<em>＊选择后</em>
						</p>
						<div class="base_manage_content_detail">
							<div class="base_manage_content_detail_left">优惠卷赠送：</div>
							<ul>
								<c:forEach items="${couponList }" var="coupon">
									<li>
										<input type="checkbox" name="firstFollowCoupon" value="${coupon.couponId }">
										${coupon.couponName }
									</li>
								</c:forEach>
							</ul>
						</div>
						<div class="send_money">
							礼金赠送：
							<input type="text" name="firstFollowGift" value="${storeSetting.firstFollowGift }">
							<em>元</em>
						</div>
					</div>

					<div class="base_manage_manage">
						<button onclick="save()">保存</button>
					</div>

				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		jQuery(function() {
			jQuery('input[type="checkbox"]').click(function() {
				if (jQuery(this).is(':checked')) {
					jQuery(this).parent().addClass('active');
				} else {
					jQuery(this).parent().removeClass('active');
				}
			})
		})
		var isCost = "${storeSetting.costCommissionType}";
		var isFixed = "${storeSetting.commissionFixedType}";
		var couponStr = "${storeSetting.firstFollowCoupon}";
		var isDecimalPoint = "${storeSetting.isDecimalPoint}";
		var isGoodsMinus = "${storeSetting.isGoodsMinus}";
		jQuery(function() {
			jQuery("[name='costCommissionType'][value='" + isCost + "']").click();
			jQuery("[name='commissionFixedType'][value='" + isFixed + "']").click();
			jQuery("[name='isDecimalPoint'][value='" + isDecimalPoint + "']").click();
			jQuery("[name='isGoodsMinus'][value='" + isGoodsMinus + "']").click();
			if (!isEmpty(couponStr)) {
				var couponArr = couponStr.split(",");
				for (var i = 0; i < couponArr.length; i++) {
					jQuery("input[name='firstFollowCoupon'][value='"+couponArr[i]+"']").click();
				}
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
			var updateMoneyAuthorize = jQuery("input[name='updateMoneyAuthorize']").val();
			var isDecimalPoint = jQuery("input[name='isDecimalPoint']:checked").val();
			var isGoodsMinus = jQuery("input[name='isGoodsMinus']:checked").val();
			var data ={"costCommissionType" : costCommissionType, "commissionFixedType" : commissionFixedType, "couponCommissionRate" : couponCommissionRate,
		    		"giftCommissionRate" : giftCommissionRate, "smsFee" : smsFee, "appointRemindHour" : appointRemindHour, "firstFollowGift" : firstFollowGift,
		    		"updateMoneyAuthorize" : updateMoneyAuthorize, "isDecimalPoint" : isDecimalPoint, "isGoodsMinus" : isGoodsMinus};
		    var firstFollowCoupon = "";
		    if (jQuery("input[name='firstFollowCoupon']:checked").length > 0) {
		    	for (var i = 0; i < jQuery("input[name='firstFollowCoupon']:checked").length; i++) {
		    		firstFollowCoupon += jQuery("input[name='firstFollowCoupon']:checked").eq(i).val();
		    		firstFollowCoupon += ",";
				}
		    	firstFollowCoupon = firstFollowCoupon.substring(0, firstFollowCoupon.length-1);
		    }
		    data["firstFollowCoupon"] = firstFollowCoupon;
		    console.log(firstFollowCoupon);
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
	</script>
</body>
</html>