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
		    	<div class="goods-wrap hide">
		            <div id="machineTip" class="goods-alert">
		                <img src="<%=basePath %>images/goods-pic.png" class="fl">
		                <p class="fl">
		                    未检测到您的友宝机器,请输入机器编号...
		                </p>
		            </div>
		            <div>
		                <span style="margin-right: 10px;">请输入机器编号：</span>
		                <input type="search" placeholder="机器编码" id="machineId"/>
		                <button class="button-search btn" style="margin-left: -10px;">立即绑定</button>
		            </div>
		        </div>
		        <div id="machineContent" class="table-toolbar hide" style="margin-bottom: 15px">
		            <div style="padding-left: 38px;">
		                <p><span style="margin-right: 10px;">机器编码：</span> <span style="font-weight: bold; font-size: 14px;">NO.${machineInfo.id }<span id="machineNo"></span></span></p>
		                <p><span style="margin-right: 10px;">安装地址：</span> <span id="machineAddress" style="font-size: 14px;">${machineInfo.address }</span></p>
		            </div>
		        </div>
		        <div id="goodsContent" class="goods-shezhi hide">
		            <ul>
		            	<c:choose>
		            		<c:when test="${isZefun == 1 }">
		            			<c:forEach items="${goodsList }" var="goodsInfo">
				            		<li style="height: 250px;">
					                   <div class="goods-pic">
					                       <img src="${goodsInfo.uboxPicture}"/>
					                   </div>
					                    <p>${goodsInfo.goodsName }</p>
					                    <div class="shezhi" onclick="editView(${goodsInfo.uboxId})">
					                        <span class="iconfont icon-shezhi"></span> <span class="cus">编辑</span>
					                    </div>
					                </li>
				            	</c:forEach>
		            		</c:when>
		            		<c:otherwise>
		            			<c:forEach items="${goodsList }" var="storeGoods">
				            		<li>
					                   <div class="goods-pic">
					                       <img src="${storeGoods.goodsInfo.uboxPicture}"/>
					                   </div>
					                    <p>${storeGoods.goodsInfo.goodsName }</p>
					                    <p>
					                    	售价：
					                    	<c:choose>
					                    		<c:when test="${storeGoods.storeGoodsIntegral > 0 }">
					                    			<span class="goods-co1">${storeGoods.storeGoodsPrice / 100}元 +${storeGoods.storeGoodsIntegral }积分</span>
					                    		</c:when>
					                    		<c:otherwise>
					                    			<span class="goods-co1">${storeGoods.storeGoodsPrice / 100}元</span>
					                    		</c:otherwise>
					                    	</c:choose>
					                    </p>
					                    <c:choose>
					                    	<c:when test="${!empty storeGoods.rewardsCoupon and storeGoods.rewardsGiftAmount > 0 }">
					                    		<p>奖励：<span class="goods-co1">${storeGoods.rewardsCoupon.couponPrice }元 ${storeGoods.rewardsCoupon.couponName }</span></p>
					                    		<p><span class="goods-co1 good-pl">${storeGoods.rewardsGiftAmount }元礼金金额</span></p>
					                    	</c:when>
					                    	<c:when test="${!empty storeGoods.rewardsCoupon and storeGoods.rewardsGiftAmount <= 0 }">
					                    		<p>奖励：<span class="goods-co1">${storeGoods.rewardsCoupon.couponPrice }元 ${storeGoods.rewardsCoupon.couponName }</span></p>
					                    	</c:when>
					                    	<c:when test="${empty storeGoods.rewardsCoupon and storeGoods.rewardsGiftAmount > 0 }">
					                    		<p>奖励：<span class="goods-co1">${storeGoods.rewardsGiftAmount }元礼金金额</span></p>
					                    	</c:when>
					                    	<c:otherwise>
					                    		<p>奖励：<span style="color: #999">未设置</span></p>
					                    	</c:otherwise>
					                    </c:choose>
					                    <div class="shezhi">
					                        <span class="iconfont icon-shezhi"></span>
					                        <span onclick="showRewardsSetting(${storeGoods.storeGoodsId}, '${storeGoods.goodsInfo.goodsName }', '${storeGoods.goodsInfo.uboxPicture}', ${storeGoods.goodsInfo.uboxOriginalPrice }, ${storeGoods.goodsInfo.uboxSalePrice }, ${storeGoods.storeGoodsPrice }, ${storeGoods.storeGoodsIntegral }, ${storeGoods.rewardsCouponId }, ${storeGoods.rewardsGiftAmount })" class="cus">设置</span>
					                    </div>
					                </li>
				            	</c:forEach>
		            		</c:otherwise>
			            </c:choose>
		            </ul>
		        </div>
		    </div>
		</div>
		
        <!--RIGHT PANEL结束 -->
        <div class="clearfix"></div>

        <div id="star"></div>
    </div>
    <div class="modal goods-modal hide" id="goodsModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
   		<div class="modal-dialog" role="document">
	        <div class="modal-content goods-shezhi-modal">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">商品价格、奖励设置</h4>
	            </div>
	            <div class="modal-body">
	                <div class="goods-top">
	                    <img id="uboxPicture" src="" alt="" class="fl"/>
	                    <div class="fl">
	                        <p id="goodsName"></p>
	                        <p><span class="font-size-14">最高销售价：</span><span id="uboxSalePrice" class="goods-co1 g-price"></span></p>
	                    </div>
	                </div>
	                <div>
	                    <span class="mr10 ">售价：</span>
	                    <span><input type="text" class="w80" id="storeGoodsPrice" name="storeGoodsPrice" onkeyup="calculate()" /><span class="percent-symbol">元</span></span>
	                    &nbsp;&nbsp;+&nbsp;
	                    <span><input type="text" class="w80" id="storeGoodsIntegral" name="storeGoodsIntegral"/><span class="percent-symbol">分</span></span>
	                </div>
	                <p class="row-sales">最低价<span class="goods-co1" id="uboxOriginalPrice"></span>，当前盈利<span id="profit" class="goods-co1"></span>。</p>
	                <p class="goods-p"><span>请设置奖励方式</span></p>
	                <p class="goods1">
	                    <span>优惠券：</span>
	                    <select id="rewardsCouponId" class="chzn-select-search mr5 w256" data-placeholder="请选择奖励的优惠券" >
	                    	<option value="0">请选择奖励的优惠券</option>
	                        <c:forEach items="${couponList }" var="coupon">
                                <option value="${coupon.couponId }">${coupon.couponName } -${coupon.couponPrice }元</option>
                            </c:forEach>
	                    </select>
	                </p>
	                <p class="goods1">
	                    <span>礼金：</span>
	                    <input id="rewardsGiftAmount" type="text" value="">
	                    <span class="percent-symbol">元</span>
	                </p>
		            <input type="hidden" id="storeGoodsId" name="storeGoodsId" />
	            </div><!--modal body-->
	            <div class="modal-footer">
	                <a class="btn modal-cancel" data-dismiss="modal" href="javascript:void(0);">取消</a>
	                <a class="btn btn-primary modal-confirm" href="javascript:rewardsAction();">保存</a>
	            </div>
	        </div>
	    </div>
	</div>
</div>

<script type="text/javascript">
function editView(goodsId){
	window.location.href = baseUrl + "ubox/goods/view/edit?goodsId=" + goodsId;
}

if ('${isZefun}' == 1) {
	jQuery("#goodsContent").removeClass("hide");
}
else if ('${hasGoods}' == 1) {
	jQuery("#machineContent").removeClass("hide");
	jQuery("#goodsContent").removeClass("hide");
}
else {
	jQuery("#machineTip").removeClass("hide");
}


var profitObj = jQuery("#profit");
var storeGoodsPriceObj = jQuery("#storeGoodsPrice");
var profitAmount = 0;
var storeGoodsAmount = 0;
var originalAmount = 0;
var uboxSaleAmount = 0;
var profit = 0;
function calculate(){
	storeGoodsAmount = storeGoodsPriceObj.val() * 100;
	profit = (storeGoodsAmount - originalAmount) / 100;
	profitObj.html(profit.toFixed(2) + "元");
}

function showRewardsSetting(storeGoodsId, goodsName, uboxPicture, uboxOriginalPrice, uboxSalePrice, storeGoodsPrice, storeGoodsIntegral, rewardsCouponId, rewardsGiftAmount){
	storeGoodsAmount = storeGoodsPrice;
	originalAmount = uboxOriginalPrice;
	uboxSaleAmount = uboxSalePrice;
	
	if (isEmpty(rewardsCouponId)) {
		jQuery("#rewardsCouponId option[value='0']").attr("selected",true);
	}
	else {
		jQuery("#rewardsCouponId option[value='" + rewardsCouponId + "']").attr("selected",true);
	}
	jQuery("#rewardsCouponId").trigger("liszt:updated");
	
	jQuery("#storeGoodsId").val(storeGoodsId);
	jQuery("#uboxPicture").attr("src", uboxPicture);
	jQuery("#goodsName").html(goodsName);
	jQuery("#uboxOriginalPrice").html(uboxOriginalPrice / 100 + "元");
	storeGoodsPriceObj.val(storeGoodsPrice / 100);
	jQuery("#storeGoodsIntegral").val(storeGoodsIntegral);
	jQuery("#uboxSalePrice").html(uboxSalePrice / 100 + "元");
	jQuery("#rewardsGiftAmount").val(rewardsGiftAmount);
	
	jQuery("#goodsModal").modal({show:true, backdrop:"static"});
}

function rewardsAction(){
	if (storeGoodsAmount < originalAmount) {
		dialog("售卖价格不能低于成本价格");
		return;
	}
	else if (storeGoodsAmount > uboxSaleAmount) {
		dialog("售卖价格不能大于最高销售价格");
		return;
	}
	
	var storeGoodsId = jQuery("#storeGoodsId").val();
	var storeGoodsPrice = jQuery("#storeGoodsPrice").val() * 100;
	var storeGoodsPrice = storeGoodsPrice.toFixed(0);
	var storeGoodsIntegral = jQuery("#storeGoodsIntegral").val();
	var rewardsCouponId = jQuery("#rewardsCouponId").val();
	
	if (storeGoodsIntegral <= 0 && storeGoodsAmount < uboxSaleAmount) {
		dialog("售卖价格低于标准售价时，需要增加使用积分");
		return;
	}
	
	var data = "storeGoodsId=" + storeGoodsId + "&storeGoodsPrice=" + storeGoodsPrice + "&storeGoodsIntegral=" + storeGoodsIntegral;
	if (!isEmpty(rewardsCouponId) && rewardsCouponId != 0) {
		data = data + "&rewardsCouponId=" + rewardsCouponId;
	}
	var rewardsGiftAmount = jQuery("#rewardsGiftAmount").val();
	if (!isEmpty(rewardsGiftAmount) && rewardsGiftAmount != 0) {
		data = data + "&rewardsGiftAmount=" + rewardsGiftAmount;
	}
	
	jQuery.ajax({
		type : "POST",
		url : baseUrl + "ubox/goods/action/rewards",
		data : data,
		success : function(e){
			if (e.code != 0) {
				dialog(e.msg);
				return;
			}
			dialog("提交成功，商品信息已更新");
			//jQuery("#goodsModal").modal("hide");
			setTimeout(function(){
    		   window.location.href = window.location.href;
    	    }, 1000);
		}
	})
	
}
</script>
</body>
</html>