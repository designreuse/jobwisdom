<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/seo-2.css" type="text/css" />
<style>
.right_head{height:80px;background: #f2f5f8}
.right_button .save{ float: left;
    width: 130px;
    height: 35px;
    border-radius: 20px;
    background: #2a3244;
    text-align: center;
    line-height: 35px;
    color: #dddddd;
    box-shadow: 0px 6px 10px #ccc;
    border: none;
	margin-right:10px
	}
</style>
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right">
					<div class="right_head clearfix">
						<div class="right_button">
							<button class="save" onclick="save()">保 存</button>
						</div>
					</div>	
					<div class="seo-2-content_">
						<div class="seo-2-list">
							<p>1.分享操作奖励，每笔服务订单仅可获得一次奖励</p>
							<div class="list_content">
							<c:choose>
								   <c:when test="${shareReward != null && shareReward.sharerRewardType == 1 }">  
										<span>
										<input type="radio" name="sharerRewardType" value="1" checked="true"/>积分
										<input style="width: 30px" type="number" value="${shareReward.sharerRewardContent }" name="share" id="sharerRewardContent1">
										</span> 
								   </c:when>
								   <c:otherwise> 
								   		<span>
										<input type="radio" name="sharerRewardType" value="1"/>积分
										<input style="width: 30px" type="number" name="share" id="sharerRewardContent1">
										</span> 
								   </c:otherwise>
							</c:choose>
							<c:choose>
								   <c:when test="${shareReward != null && shareReward.sharerRewardType == 2 }">  
										<span>
										<input type="radio" name="sharerRewardType" value="2" checked="true"/>礼金
										<input style="width: 30px" type="number" value="${shareReward.sharerRewardContent }" name="share" id="sharerRewardContent2">
										</span> 
								   </c:when>
								   <c:otherwise> 
								   		<span>
										<input type="radio" name="sharerRewardType" value="2"/>礼金
										<input style="width: 30px" type="number" name="share" id="sharerRewardContent2">
										</span> 
								   </c:otherwise>
							</c:choose>
							<c:choose>
								   <c:when test="${shareReward != null && shareReward.sharerRewardType == 3 }">  
										<span><input type="radio" name="sharerRewardType" value="3" checked="true"/>优惠劵
										<select name="sharerRewardContent" id="sharerRewardContent3" class="chzn-select w185" data-placeholder="选择一张优惠券">
											<c:forEach items="${couponInfoDtos }" var="coupon">
											<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
											</c:forEach>
										</select> 
										</span>
										<script type="text/javascript">
										var sharerRewardContent = '${shareReward.sharerRewardContent }';
										jQuery("#sharerRewardContent3").val(sharerRewardContent);
										</script>
								   </c:when>
								   <c:otherwise> 
								   	   <span><input type="radio" value="3" name="sharerRewardType">优惠劵
									   <select name="sharerRewardContent" id="sharerRewardContent3" class="chzn-select w185" data-placeholder="选择一张优惠券">
											<c:forEach items="${couponInfoDtos }" var="coupon">
											<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
											</c:forEach>
									   </select> 
									   </span>
								   </c:otherwise>
							</c:choose>
							</div>
						</div>
						<div class="seo-2-list">
							<p>2.通过分享页面注册领取的奖励</p>
							<div class="list_content">
							<c:choose>
								   <c:when test="${shareReward != null && shareReward.watcherRewardType == 1 }">  
										<span>
										<input type="radio" name="watcherRewardType" value="1" checked="true"/>积分
										<input style="width: 30px" type="number" value="${shareReward.watcherRewardContent }" name="share" id="watcherRewardContent1">
										</span> 
								   </c:when>
								   <c:otherwise> 
								   		<span>
										<input type="radio" name="watcherRewardType" value="1"/>积分
										<input style="width: 30px" type="number" name="share" id="watcherRewardContent1">
										</span> 
								   </c:otherwise>
							</c:choose>
							<c:choose>
								   <c:when test="${shareReward != null && shareReward.watcherRewardType == 2 }">  
										<span>
										<input type="radio" name="watcherRewardType" value="2" checked="true"/>礼金
										<input style="width: 30px" type="number" value="${shareReward.watcherRewardContent }" name="share" id="watcherRewardContent2">
										</span> 
								   </c:when>
								   <c:otherwise> 
								   		<span>
										<input type="radio" name="watcherRewardType" value="2"/>礼金
										<input style="width: 30px" type="number" name="share" id="sharerRewardContent2">
										</span> 
								   </c:otherwise>
							</c:choose>
							<c:choose>
								   <c:when test="${shareReward != null && shareReward.watcherRewardType == 3 }">  
										<span><input type="radio" name="watcherRewardType" value="3" checked="true"/>优惠劵
										<select name="sharerRewardContent" id="watcherRewardContent3" class="chzn-select w185" data-placeholder="选择一张优惠券">
											<c:forEach items="${couponInfoDtos }" var="coupon">
											<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
											</c:forEach>
										</select> 
										</span>
										<script type="text/javascript">
										var sharerRewardContent = '${shareReward.watcherRewardContent }';
										jQuery("#sharerRewardContent3").val(sharerRewardContent);
										jQuery("#sharerRewardContent3").trigger("liszt:updated");
										</script>
								   </c:when>
								   <c:otherwise> 
								   	   <span><input type="radio" value="3" name="watcherRewardType">优惠劵
									   <select name="" id="watcherRewardContent3" class="chzn-select w185" data-placeholder="选择一张优惠券">
											<c:forEach items="${couponInfoDtos }" var="coupon">
											<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
											</c:forEach>
									   </select> 
									   </span>
								   </c:otherwise>
							</c:choose>
							</div>
						</div>
						<div class="seo-2-list">
							<p>
								3.通过分享页面注册的新会员首次消费时(仅一次)，对于分享者的回馈奖励。每消费 <input class="input40" name="firstConsumeRewardUnit" type="number" value="${shareReward.firstConsumeRewardUnit }" name=""> 元,奖励:
							</p>
							<div class="list_content">
							<c:choose>
								   <c:when test="${shareReward != null && shareReward.firstConsumeRewardType == 1 }">  
										<span>
										<input type="radio" name="firstConsumeRewardType" value="1" checked="true"/>积分
										<input style="width: 30px" type="number" value="${shareReward.firstConsumeRewardContent }" name="share" id="firstConsumeRewardContent1">
										</span> 
								   </c:when>
								   <c:otherwise> 
								   		<span>
										<input type="radio" name="firstConsumeRewardType" value="1"/>积分
										<input style="width: 30px" type="number" name="share" id="firstConsumeRewardContent2">
										</span> 
								   </c:otherwise>
							</c:choose>
							<c:choose>
								   <c:when test="${shareReward != null && shareReward.firstConsumeRewardType == 2 }">  
										<span>
										<input type="radio" name="firstConsumeRewardType" value="2" checked="true"/>礼金
										<input style="width: 30px" type="number" value="${shareReward.firstConsumeRewardContent }" name="share" id="firstConsumeRewardContent2">
										</span> 
								   </c:when>
								   <c:otherwise> 
								   		<span>
										<input type="radio" name="firstConsumeRewardType" value="2"/>礼金
										<input style="width: 30px" type="number" name="share" id="firstConsumeRewardContent2">
										</span> 
								   </c:otherwise>
							</c:choose>
							<c:choose>
								   <c:when test="${shareReward != null && shareReward.firstConsumeRewardType == 3 }">  
										<span><input type="radio" name="firstConsumeRewardType" value="3" checked="true"/>优惠劵
										<select name="sharerRewardContent" id="firstConsumeRewardContent3" class="chzn-select w185" data-placeholder="选择一张优惠券">
											<c:forEach items="${couponInfoDtos }" var="coupon">
											<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
											</c:forEach>
										</select> 
										</span>
										<script type="text/javascript">
										var sharerRewardContent = '${shareReward.firstConsumeRewardContent }';
										jQuery("#firstConsumeRewardContent3").val(sharerRewardContent);
										</script>
								   </c:when>
								   <c:otherwise> 
								   	   <span><input type="radio" value="3" name="firstConsumeRewardType">优惠劵
									   <select name="" id="firstConsumeRewardContent3" class="chzn-select w185" data-placeholder="选择一张优惠券">
											<c:forEach items="${couponInfoDtos }" var="coupon">
											<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
											</c:forEach>
									   </select> 
									   </span>
								   </c:otherwise>
							</c:choose>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function save() {
			var sharerRewardType = jQuery(
					'input:radio[name="sharerRewardType"]:checked').val();
			var watcherRewardType = jQuery(
					'input:radio[name="watcherRewardType"]:checked').val();
			var firstConsumeRewardType = jQuery(
					'input:radio[name="firstConsumeRewardType"]:checked').val();
			var sharerRewardContent, watcherRewardContent, firstConsumeRewardContent, firstConsumeRewardUnit;
			if (typeof (sharerRewardType) == 'undefined'
					|| typeof (watcherRewardType) == 'undefined'
					|| typeof (firstConsumeRewardType) == 'undefined') {
				dialog('至少选择一种奖励方式');
				return;
			}

			switch (Number(sharerRewardType)) {
			case 1:
				sharerRewardContent = jQuery("#sharerRewardContent1").val();
				break;
			case 2:
				sharerRewardContent = jQuery("#sharerRewardContent2").val();
				break;
			case 3:
				sharerRewardContent = jQuery("#sharerRewardContent3").val();
				break;
			default:
				break;
			}
			switch (Number(watcherRewardType)) {
			case 1:
				watcherRewardContent = jQuery("#watcherRewardContent1").val();
				break;
			case 2:
				watcherRewardContent = jQuery("#watcherRewardContent2").val();
				break;
			case 3:
				watcherRewardContent = jQuery("#watcherRewardContent3").val();
				break;
			default:
				break;
			}
			switch (Number(firstConsumeRewardType)) {
			case 1:
				firstConsumeRewardContent = jQuery(
						"#firstConsumeRewardContent1").val();
				break;
			case 2:
				firstConsumeRewardContent = jQuery(
						"#firstConsumeRewardContent2").val();
				break;
			case 3:
				firstConsumeRewardContent = jQuery(
						"#firstConsumeRewardContent3").val();
				break;
			default:
				break;
			}
			var dataJson = {
				"sharerRewardType" : Number(sharerRewardType),
				"sharerRewardContent" : Number(sharerRewardContent),
				"watcherRewardType" : Number(watcherRewardType),
				"watcherRewardContent" : Number(watcherRewardContent),
				"firstConsumeRewardType" : Number(firstConsumeRewardType),
				"firstConsumeRewardUnit" : Number(jQuery(
						"input[name='firstConsumeRewardUnit']").val()),
				"firstConsumeRewardContent" : Number(firstConsumeRewardContent)
			}
			console.log(dataJson);
			jQuery.ajax({
				dataType : "json",
				contentType : "application/json",
				type : "POST",
				url : baseUrl + "action/save/share",
				data : JSON.stringify(dataJson),
				async : false,
				success : function(data) {
					if (data.code != 0)
						dialog("修改失败");
					dialog(data.msg);
				}
			});
		}
	</script>
</body>

</html>
