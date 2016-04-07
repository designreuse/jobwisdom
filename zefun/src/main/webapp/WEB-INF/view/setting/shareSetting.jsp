<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
					<div class="border-head">
						<span>分享拓客奖励设置</span>
					</div>
					<form id="baseSettingForm">
						<div class="border-content">
							<table class="table nobordered-table share-table">
								<tbody>
									<tr>
										<td class="fb">1.分享操作奖励，每笔服务订单仅可获得一次奖励</td>
									</tr>
									<tr>
										<td>
											<div>
											<c:choose>
											   <c:when test="${shareReward != null && shareReward.sharerRewardType == 1 }">  
											   		<label for="" class="radio">
													<input type="radio" name="sharerRewardType" value="1" checked="true"/>积分</label>
													<input type="number" id="sharerRewardContent1" class="name  w60" value="${shareReward.sharerRewardContent }"> 
													<span class="percent-symbol ">分</span>
											   </c:when>
											   <c:otherwise> 
											   		<label for="" class="radio">
													<input type="radio" name="sharerRewardType" value="1" />积分</label>
													<input type="number" id="sharerRewardContent1" class="name  w60" value="0"> 
													<span class="percent-symbol ">分</span>
											   </c:otherwise>
											</c:choose>
											</div>
											<div>
											<c:choose>
											   <c:when test="${shareReward != null && shareReward.sharerRewardType == 2 }">  
											   		<label for="" class="radio">
													<input type="radio" name="sharerRewardType" value="2" checked="true"/>礼金</label>
													<input type="number" id="sharerRewardContent2" class="name  w60" value="${shareReward.sharerRewardContent }"> 
													<span class="percent-symbol ">元</span>
											   </c:when>
											   <c:otherwise> 
											   		<label for="" class="radio">
													<input type="radio" name="sharerRewardType" value="2" />礼金</label>
													<input type="number" id="sharerRewardContent2" class="name  w60" value="0"> 
													<span class="percent-symbol ">元</span>
											   </c:otherwise>
											</c:choose>
											</div>
											<div class="fenxiang">
											<c:choose>
											   <c:when test="${shareReward != null && shareReward.sharerRewardType == 3 }">  
											   		<label for="" class="radio">
													<input type="radio" name="sharerRewardType" value="3" checked="true"/>优惠劵</label> 
													<select name="sharerRewardContent" id="sharerRewardContent3" class="chzn-select w185" data-placeholder="选择一张优惠券">
														<c:forEach items="${couponInfoDtos }" var="coupon">
														<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
														</c:forEach>
													</select>
													<script type="text/javascript">
													var sharerRewardContent = '${shareReward.sharerRewardContent }';
													jQuery("#sharerRewardContent3").val(sharerRewardContent);
													</script>
											   </c:when>
											   <c:otherwise> 
											   		<label for="" class="radio">
													<input type="radio" name="sharerRewardType" value="3"/>优惠劵</label> 
													<select name="sharerRewardContent" id="sharerRewardContent3" class="chzn-select w185" data-placeholder="选择一张优惠券">
														<c:forEach items="${couponInfoDtos }" var="coupon">
														<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
														</c:forEach>
													</select>
											   </c:otherwise>
											</c:choose>
											</div>
										</td>
									</tr>
									<tr>
										<td class="fb">2.通过分享页面注册领取的奖励</td>
									</tr>
									<tr>
										<td>
											<div>
											<c:choose>
											   <c:when test="${shareReward != null && shareReward.watcherRewardType == 1 }">  
											   		<label for="" class="radio">
													<input type="radio" name="watcherRewardType" value="1" checked="true"/>积分</label>
													<input type="number" id="watcherRewardContent1" class="name  w60" value="${shareReward.watcherRewardContent }"> 
													<span class="percent-symbol ">分</span>
											   </c:when>
											   <c:otherwise> 
											   		<label for="" class="radio">
													<input type="radio" name="watcherRewardType" value="1" />积分</label>
													<input type="number" id="watcherRewardContent1" class="name  w60" value="0"> 
													<span class="percent-symbol ">分</span>
											   </c:otherwise>
											</c:choose>
											</div>
											<div>
											<c:choose>
											   <c:when test="${shareReward != null && shareReward.watcherRewardType == 2 }">  
											   		<label for="" class="radio">
													<input type="radio" name="watcherRewardType" value="2" checked="true"/>礼金</label>
													<input type="number" id="watcherRewardContent2" class="name  w60" value="${shareReward.watcherRewardContent }"> 
													<span class="percent-symbol ">元</span>
											   </c:when>
											   <c:otherwise> 
											   		<label for="" class="radio">
													<input type="radio" name="watcherRewardType" value="2" />礼金</label>
													<input type="number" id="watcherRewardContent2" class="name  w60" value="0"> 
													<span class="percent-symbol ">元</span>
											   </c:otherwise>
											</c:choose>
											</div>
											<div class="fenxiang">
											<c:choose>
											   <c:when test="${shareReward != null && shareReward.watcherRewardType == 3 }">  
											   		<label for="" class="radio">
													<input type="radio" name="watcherRewardType" value="3" checked="true"/>优惠劵</label> 
													<select value="" name="" id="watcherRewardContent3" class="chzn-select w185">
														<c:forEach items="${couponInfoDtos }" var="coupon">
														<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
														</c:forEach>
													</select>
													<script type="text/javascript">
													var watcherRewardContent = '${shareReward.watcherRewardContent }';
													jQuery("#watcherRewardContent3").val(watcherRewardContent);
													</script>
											   </c:when>
											   <c:otherwise> 
												    <label for="" class="radio">
													<input type="radio" name="watcherRewardType" value="3"/>优惠劵</label> 
													<select value="" name="" id="watcherRewardContent3" class="chzn-select w185">
														<c:forEach items="${couponInfoDtos }" var="coupon">
														<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
														</c:forEach>
													</select>
											   </c:otherwise>
											</c:choose>
											</div>
										</td>
									</tr>
									<tr>
										<td class="fb">3.通过分享页面注册的新会员首次消费时(仅一次)，对于分享者的回馈奖励。</td>
									</tr>
									<tr>
										<td>
											<span class="fb" style="margin-bottom: 10px">每消费<input class="input40" name="firstConsumeRewardUnit" type="number" value="${shareReward.firstConsumeRewardUnit }" name="">元,奖励</span>
											<div>
											<c:choose>
											   <c:when test="${shareReward != null && shareReward.firstConsumeRewardType == 1 }">  
											   		<label for="" class="radio">
													<input type="radio" name="firstConsumeRewardType" value="1" checked="true"/>积分</label>
													<input type="number" id="firstConsumeRewardContent1" class="name  w60" value="${shareReward.firstConsumeRewardContent }"> 
													<span class="percent-symbol ">分</span>
											   </c:when>
											   <c:otherwise> 
											   		<label for="" class="radio">
													<input type="radio" name="firstConsumeRewardType" value="1" />积分</label>
													<input type="number" id="firstConsumeRewardContent1" class="name  w60" value="0"> 
													<span class="percent-symbol ">分</span>
											   </c:otherwise>
											</c:choose>
											</div>
											<div>
											<c:choose>
											   <c:when test="${shareReward != null && shareReward.firstConsumeRewardType == 2 }">  
											   		<label for="" class="radio">
													<input type="radio" name="firstConsumeRewardType" value="2" checked="true"/>礼金</label>
													<input type="number" id="firstConsumeRewardContent2" class="name  w60" value="${shareReward.firstConsumeRewardContent }"> 
													<span class="percent-symbol ">元</span>
											   </c:when>
											   <c:otherwise> 
											   		<label for="" class="radio">
													<input type="radio" name="firstConsumeRewardType" value="2" />礼金</label>
													<input type="number" id="firstConsumeRewardContent2" class="name  w60" value="0"> 
													<span class="percent-symbol ">元</span>
											   </c:otherwise>
											</c:choose>
											</div>
											<span class="fb">或者奖励下面一张优惠券</span>
											<div class="fenxiang">
											<c:choose>
											   <c:when test="${shareReward != null && shareReward.firstConsumeRewardType == 3 }">  
											   		<label for="" class="radio">
													<input type="radio" name="firstConsumeRewardType" value="3" checked="true"/>优惠劵</label> 
													<select name="" id="firstConsumeRewardContent3" class="chzn-select w185" >
														<c:forEach items="${couponInfoDtos }" var="coupon">
														<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
														</c:forEach>
													</select>
													<script type="text/javascript">
													var firstConsumeRewardContent = '${shareReward.firstConsumeRewardContent }';
													jQuery("#firstConsumeRewardContent3").val(firstConsumeRewardContent);
													</script>
											   </c:when>
											   <c:otherwise> 
											   		<label for="" class="radio">
													<input type="radio" name="firstConsumeRewardType" value="3"/>优惠劵</label> 
													<select name="" id="firstConsumeRewardContent3" class="chzn-select w185" >
														<c:forEach items="${couponInfoDtos }" var="coupon">
														<option value="${coupon.couponId }">${coupon.couponPrice}元 ${coupon.couponName }</option>
														</c:forEach>
													</select>
											   </c:otherwise>
											</c:choose>
											</div>
										</td>
									</tr>
									<tr>
										<td class="fb"><a class="btn btn-primary"
											href="javascript:save();">保存</a></td>
										<td></td>
									</tr>
								</tbody>
							</table>
						</div>
					</form>
				</div>
			</div>
			<!--RIGHT PANEL结束 -->
			<div class="clearfix"></div>
			<div id="star"></div>
		</div>
	</div>
<script type="text/javascript">

function save(){
	var sharerRewardType = jQuery('input:radio[name="sharerRewardType"]:checked').val();
	var watcherRewardType = jQuery('input:radio[name="watcherRewardType"]:checked').val();
	var firstConsumeRewardType = jQuery('input:radio[name="firstConsumeRewardType"]:checked').val();
	var sharerRewardContent, watcherRewardContent, firstConsumeRewardContent, firstConsumeRewardUnit;
	if(typeof(sharerRewardType)=='undefined'||typeof(watcherRewardType)=='undefined'||typeof(firstConsumeRewardType)=='undefined') {dialog('至少选择一种奖励方式');return;}
	
	switch(Number(sharerRewardType))
	{
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
	switch(Number(watcherRewardType))
	{
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
	switch(Number(firstConsumeRewardType))
	{
	case 1:
		firstConsumeRewardContent = jQuery("#firstConsumeRewardContent1").val();
	  break;
	case 2:
		firstConsumeRewardContent = jQuery("#firstConsumeRewardContent2").val();
	  break;
	case 3:
		firstConsumeRewardContent = jQuery("#firstConsumeRewardContent3").val();
	  break;
	default:
	  break;
	}
	var dataJson = {
		    "sharerRewardType": Number(sharerRewardType),
		    "sharerRewardContent": Number(sharerRewardContent),
		    "watcherRewardType": Number(watcherRewardType),
		    "watcherRewardContent": Number(watcherRewardContent),
		    "firstConsumeRewardType": Number(firstConsumeRewardType),
		    "firstConsumeRewardUnit": Number(jQuery("input[name='firstConsumeRewardUnit']").val()),
		    "firstConsumeRewardContent": Number(firstConsumeRewardContent)
	}
	console.log(dataJson);
	jQuery.ajax({
        dataType: "json",
        contentType: "application/json",
        type: "POST",
        url: baseUrl+"action/save/share",
        data: JSON.stringify(dataJson),
        async: false,
        success: function(data) {
        	if(data.code!=0)dialog("修改失败");
        	dialog(data.msg);
        }
    });
}
</script>
</body>

</html>
