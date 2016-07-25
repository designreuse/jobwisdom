<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>确认付款</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=muiCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
  </head>
<body>

<div class="content wrap">
    <div class="my-order-doing mb-footer">
    	<ul class="member-card">
	        <li>
	            <span class="font-666" style="font-size: 1.75rem">选择会员卡</span>
	        </li>
	        <c:forEach items="${orderDto.subAccountList }" var="subAccount" varStatus="status">
	        	<li class="subAccountLi <c:if test="${status.index == 0 }">current</c:if>" data-subAccountId="${subAccount.subAccountId }">
		            <span class="iconfont icon-huiyuanqia fl"></span>
		            <div class="fl">
		                <span class="font-333">${subAccount.levelName }<span class="font-size-24 font-666">(项目${subAccount.projectDiscount }折，商品${subAccount.goodsDiscount }折）</span></span>
		                <div class="font-999 font-size-18">余额：<span class="font-size-24">${subAccount.balanceAmount }</span></div>
		            </div>
		            <span class="iconfont icon-xuanzhong fr"></span>
		        </li>
	        </c:forEach>
	    </ul>
	    
	    <c:if test="${appointOff > 0 }">
		    <ul class="border-radius-ul mt2">
	          <li class="two-line-li" style="height: 4.5rem;">
	              <span class="fl font-size-30 font-333" style="line-height: 4.5rem;">预约优惠金额</span>
	              <div class="fr text-right" style="line-height: 4.5rem;">
	                  <div class="blue-price font-size-44" style="line-height: 4.5rem;">-${appointOff }</div>
	              </div>
	          </li>
	        </ul>
        </c:if>
	    
	    <ul class="order-ul mt2">
	        <li class="dingdan-danhao">
	            <span class="danhao fl">
	                单号: ${orderDto.orderCode}
	            </span>
	            <span class="fr fs24">${orderDto.createTime}</span>
	        </li>
	        <c:if test="${not empty orderDto.orderDetails and fn:length(orderDto.orderDetails) > 0}">
                <c:forEach var="detail" items="${orderDto.orderDetails}">
			        <li class="dingdan-list" data-type="${detail.orderType}">
			            <img src="<%=picPath%>${detail.projectImage}?imageView2/1/w/220/h/220">
			            <div class="list-desc">
			                <div class="list-name">
                                 ${detail.projectName}
                            </div>
			                <div class="origin-price"><span class="text-through font-666 font-size-18">门店价格: ￥${detail.projectPrice}</span></div>
			                <div class="origin-price">
			                    <span class="font-666 font-size-18">会员价格: </span>
			                    <span class="red-price font-size-30" name="${detail.detailId }_discountAmount">￥${detail.discountAmount}</span>
			                </div>
			            </div>
			            <c:set var="projectPrice" value="${detail.projectPrice }" />
			            <c:if test="${not empty detail.freeAmount && detail.freeAmount != '0' && detail.freeAmount != '0.00' }">
			            	<c:set var="projectPrice" value="${detail.discountAmount }" />
			            </c:if>
			            <div class="order-edit fr" name="payOffContent" highestDiscount="${detail.highestDiscount }" realMoney="${detail.discountAmount }" discountAmount="${detail.discountAmount }" projectPrice="${projectPrice}" detailId="${detail.detailId }" uid="0" offType="0" offId="0" offAmount="0">
			                <div class="list-price blue-price" name="offAmount"></div>
			                <div class="name" name="offName"></div>
			            </div>
			        </li>
	           </c:forEach>
           </c:if>
	    </ul>
	    <div class="pay">
            <span id="totalMoney" class="word">
            	共计<span class="num" id="cardMoney"></span>
                <span id="chargeTip" class="fr">
                     余额不足，请充值
                </span> 
                <div id="payBtn" onclick="orderpay('${orderDto.orderId}');" class="normal-btn fr mt2">确定付款</div>
            </span>
        </div>
	</div>
</div>
<%@include file="../memberBase.jsp" %>
<script type="text/javascript" src="<%=muiJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/base/big.js"></script>
<script type="text/javascript">
var detailList = eval('${detailStr}');
var allOffMap = JSON.parse('${allOffStr}');
var orderInfo = JSON.parse('${orderDtoStr}');
var discountMap = orderInfo.discountMap;
var subAccountList = orderInfo.subAccountList;
var subAccountMap = {};
for (var i = 0; i < subAccountList.length; i++) {
	var subAccount = subAccountList[i];
	subAccountMap[subAccount.subAccountId] = new Big(subAccount.balanceAmount);
}

var memberBalanceAmount = '${orderDto.memberInfo.balanceAmount}';
var appointOff = new Big('${appointOff}');
var orderId = '${orderDto.orderId}';
var storeId = "${session_key_store_id}";
var isEvaluate = "${isEvaluate}";
var balanceAmount = new Big(memberBalanceAmount);
var totalRealMoney = new Big(0);
var offPickerMap = new Object();
var detailOffMap = new Object();
//初始化优惠项
(function($, jq) {
    $.init();
    $.ready(function() {
    	for (var i = 0; i < detailList.length; i++) {
    		var detail = detailList[i];
    		totalRealMoney = totalRealMoney.plus(detail.discountAmount);
    		
    		var payOffContent = jq("[name='payOffContent'][detailId='" + detail.detailId + "']");
    		
    		var detailOffList = new Array();
    		//疗程/礼金/优惠券抵扣，组装数据
    		var pickerData = new Array();
    		var d = {};
            d["uid"] = 0;
            d["detailId"] = detail.detailId;
            d["offType"] = 0;
            d["offId"] = 0;
            d["count"] = 0;
            d["offAmount"] = 0;
            d["name"] = '不使用优惠';
            d["text"] = '不使用优惠';
            pickerData.push(d);
        	
    		//检查是否存在优惠项
            var paymentOffList = detail.paymentOffList;
            if (paymentOffList == null || paymentOffList.length == 0) {
            	payOffContent.find("[name='offName']:first").html("无可用优惠");
            } else {
            	payOffContent.find("[name='offName']:first").html("有可用优惠<span class='iconfont icon-zhankai'></span>");
                
                for (var j = 0; j < paymentOffList.length; j++) {
                    var offObj = paymentOffList[j];
                    
                    var uid = offObj.type + "_" + offObj.id;
                    detailOffList.push(uid);
                    
                    var data = {};
                    data["uid"] = uid;
                    data["detailId"] = detail.detailId;
                    data["offType"] = offObj.type;
                    data["offId"] = offObj.id;
                    data["count"] = offObj.count;
                    data["offAmount"] = offObj.amount;
                    data["name"] = offObj.name;
                    data["text"] = offObj.name + ' -' + offObj.amount + '元 ';
                    
                    pickerData.push(data);
                }
                detailOffMap[detail.detailId] = detailOffList;
            }
            //初始化多选项
            var userPicker = new $.PopPicker();
            userPicker.setData(pickerData);
            jq(payOffContent).on("click", {picker : userPicker, detailId : detail.detailId}, showPicker);
            offPickerMap[detail.detailId] = userPicker;
		}
    	syncRealMoney();
    });
})(mui, $);

var subAccountId = $(".subAccountLi.current").attr("data-subAccountId");
$(function(){ 
	//处理改变支付会员卡的逻辑
	$("body").delegate(".subAccountLi", "click", function(){
		$(this).addClass("current").siblings().removeClass("current");
		var tempSubAccountId = $(this).attr("data-subAccountId");
		if (subAccountId != tempSubAccountId) {
			subAccountId = tempSubAccountId;
			//更新所有会员折扣价格
			jQuery(".order-edit").each(function(){
				var $this = jQuery(this);
				var detailId = $this.attr("detailId");
				var detailType = $this.attr("detailType");
				//购买疗程直接跳过
				if (detailType != 3) {
					//更新折扣价格
					var discountAmount = discountMap[detailId + "_" + subAccountId];
					$("[name='" + detailId + "_discountAmount']").html(discountAmount.toFixed(2));
					$this.attr("discountAmount", discountAmount.toFixed(2));
					
					//更新实收价格
					var offType = $this.attr("offtype");//1:项目疗程，2:商品疗程，3:优惠券，4:礼金
					if (offType == 3 || offType == 0) {
						var offAmount = $this.attr("offAmount");
						var realAmount = new Big(discountAmount - offAmount);
						if (realAmount.lt(0)) {
							realAmount = 0;
						}
						totalRealMoney = totalRealMoney.minus($this.attr("realMoney")).plus(realAmount);
						$this.attr("realMoney", realAmount.toFixed(2));
					}
				}
			});
			
			balanceAmount = subAccountMap[subAccountId];
			syncRealMoney();
		}
	});
}); 

function syncRealMoney(){
	var tmpTotalRealMoney = totalRealMoney.minus(appointOff);
	if (tmpTotalRealMoney.lt(0)) {
		tmpTotalRealMoney = new Big(0);
	}
	$("#cardMoney").html(" ¥" + tmpTotalRealMoney.toFixed(2));
	
	if (balanceAmount.lt(tmpTotalRealMoney)) {
		$("#payBtn").addClass("hide");
		$("#chargeTip").removeClass("hide");
	} 
	else {
		$("#chargeTip").addClass("hide");
		$("#payBtn").removeClass("hide");
	}
}

function showPicker(data){
	$(".mui-poppicker").removeClass("mui-active");
	data.data.picker.show(function(items) {
		var selectOff = items[0];
		var detailId = selectOff.detailId;
		var payOffContent = $("[name='payOffContent'][detailId='" + detailId + "']");
		var projectPrice = payOffContent.attr("projectPrice");
		//检查是否有改变选项
		var sid = payOffContent.attr("uid");
		if (selectOff.uid != sid) {
			var obj = findElementByProperty(this.pickers[0].items, "uid", sid);
			if (obj != null) {
				syncOff(obj, detailId, projectPrice, 2);
			}
			
			//如果选择为不使用优惠
			if (selectOff.uid != 0) {
				syncOff(selectOff, detailId, projectPrice, 1);
			}
			
			//计算优惠之后的实收
			var discountAmount = new Big(payOffContent.attr("discountAmount"));
            if (selectOff.offType == 3 && discountAmount.lt(selectOff.offAmount)) {
            	payOffContent.find("[name='offAmount']:first").html("-" + projectPrice);
            } else {
            	payOffContent.find("[name='offAmount']:first").html("-" + selectOff.offAmount);
            }
            
            //同步实收金额
			var realMoney = new Big(payOffContent.attr("realMoney"));
			totalRealMoney = totalRealMoney.minus(realMoney);
            if (selectOff.offType == 4) {
            	realMoney = new Big(projectPrice - selectOff.offAmount);
            } 
            else {
            	realMoney = discountAmount.minus(selectOff.offAmount);
            }
            if (realMoney.lt(0)) {
            	realMoney = 0;
            }
            totalRealMoney = totalRealMoney.plus(realMoney);
			
            //更新项目详情的当前所有优惠项
            payOffContent.attr("uid", selectOff.uid);
            payOffContent.attr("realMoney", realMoney);
			payOffContent.attr("offType", selectOff.offType);
        	payOffContent.attr("offId", selectOff.offId);
        	payOffContent.attr("offAmount", selectOff.offAmount);
            payOffContent.find("[name='offName']:first").html(selectOff.name + '<span class="iconfont icon-zhankai"></span>');
            syncRealMoney();
		}
	});
}

//type,1:选择，2:取消选择
function syncOff(selectOff, detailId, projectPrice, type){
	var id = selectOff.uid;
	var offAmount = new Big(selectOff.offAmount);
	
	var balance = new Big(allOffMap[id]);
	//检查优惠类型,如果为礼金，需要检查礼金预约，其他检查数量
	var deduction = 1;
	if (selectOff.offType == 4) {
		//维护当前所选部门的礼金余额
		deduction = offAmount;
	}
	if (type == 1) {
		balance = balance.minus(deduction);
	} else {
		balance = balance.plus(deduction);
		
		//如果为礼金，先查看是否有其他项目使用礼金优惠，检查抵扣金额是否需要变更
		if (selectOff.offType == 4) {
			for ( var k in offPickerMap) {
				if (k == detailId) {
					continue;
				}
				var picker = offPickerMap[k].pickers[0];
				var obj = picker.getSelectedItem();
				var payOffContent = $("[name='payOffContent'][detailId='" + obj.detailId + "']");
				var pp = new Big(payOffContent.attr("projectPrice"));
				var pd = new Big(payOffContent.attr("highestDiscount"));
				if(obj.uid == id && obj.offAmount < projectPrice){
					var cof = obj.offAmount;
					obj.offAmount = balance.plus(cof);
					if (obj.offAmount.gt(pd)) {
						obj.offAmount = pd;
					}
					//更新项目详情的当前所有优惠项
					payOffContent.find("[name='offAmount']:first").html("-" + obj.offAmount);
					var r = pp.minus(obj.offAmount);
					
					totalRealMoney = totalRealMoney.plus(r.minus(payOffContent.attr("realMoney")));
					syncRealMoney();
		            payOffContent.attr("realMoney", r);
		        	payOffContent.attr("offAmount", obj.offAmount);
		        	
		        	obj.text = obj.name + ' -' + obj.offAmount + '元 ';
					offPickerMap[k].setData(picker.items);
					balance = balance.plus(cof);
					allOffMap[id] = balance;
					var to =  jQuery.extend(true,{}, obj);
					syncOff(to, to.detailId, projectPrice, 1);
					return;
				}
			}
		}
	}
	allOffMap[id] = balance;
	
	//遍历其他优惠项
	for ( var k in offPickerMap) {
		if (k == detailId) {
			continue;
		}
		//获取此次遍历的项目详情可使用的优惠标识列表
		var detailOffList = detailOffMap[k];
		//如果不能使用该优惠，直接跳过
		if ($.inArray(id, detailOffList) < 0) {
			continue;
		}
		
		var picker = offPickerMap[k].pickers[0];
		//获取此次遍历的实时优惠选项
		var dataItems = picker.items;
		//检查该优惠是否存在其他优惠列表中
		var obj = findElementByProperty(dataItems, "uid", id);
		
		//如果不存在，检查是否有余额，有余额则可加入其内
		if(obj == null && balance.gt(0)) {
			var payOffContent = $("[name='payOffContent'][detailId='" + k + "']");
			var pd = new Big(payOffContent.attr("highestDiscount"));
			
			var to =  jQuery.extend(true,{}, selectOff);
			to.detailId = k;
			if (to.offType == 4) {
				to.offAmount = balance;
				if(to.offAmount.gt(pd)){
					to.offAmount = pd;
				}
			}
			
			to.text = to.name + ' -' + to.offAmount + '元 ';
            dataItems.push(to);
		} 
		//如果存在，且余额不足，且不是当前遍历选项选择的优惠时从中移除
		else if(obj != null && balance.lt(1) && picker.getSelectedItem().uid != id) {
			removeElementByProperty(dataItems, "uid", id);
		}
		//如果存在，且为礼金，需重新更新礼金抵扣的优惠项
		else if (obj != null && picker.getSelectedItem().uid != id && selectOff.offType == 4) {
			var payOffContent = $("[name='payOffContent'][detailId='" + k + "']");
			var pd = new Big(payOffContent.attr("highestDiscount"));
			obj.offAmount = balance;
			if(obj.offAmount.gt(pd)){
				obj.offAmount = pd;
			}
			obj.text = obj.name + ' -' + obj.offAmount + '元 ';
		}
		
		offPickerMap[k].setData(dataItems);
	}
}

//提交订单
function orderpay(orderId) {
	var tmpTotalRealMoney = totalRealMoney.minus(appointOff);
	if (tmpTotalRealMoney.lt(0)) {
		tmpTotalRealMoney = new Big(0);
	}
	if (balanceAmount.lt(tmpTotalRealMoney)) {
		$("#chargeTip").addClass("hide");
		$("#payBtn").removeClass("hide");
		return;
	}
	
    var details = new Array();
    var detailId = null;
    jQuery("[name='payOffContent']").each(function(){
        var $this = jQuery(this);
        details.push({"detailId" : $this.attr("detailId"), "offType" : $this.attr("offType"), "offId" : $this.attr("offId"), "offAmount" : $this.attr("offAmount")});
    });

    var cardAmount = tmpTotalRealMoney;
    
    var data = {'orderId':orderId,'cardAmount':cardAmount,'cashAmount':0,
            'unionpayAmount':0,'wechatAmount':0,'alipayAmount':0,
            'groupAmount':0,'debtAmount':0,'detailList':details,'isNotify':0,"subAccountId":subAccountId};
    jQuery.ajax({
        type: "POST",
        url: baseUrl + "memberCenter/action/orderPay",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(data) {
            if(data.code != 0) {
                dialog(data.msg);
                return;
            }
            dialog('结账成功');
            setTimeout(function(){
            	if (isEmpty(isEvaluate)) {
            		window.location.href = baseUrl + "memberCenter/view/paymentDetail/" + storeId + "/1?orderId=" + orderId;
            	}
            	else {
            		window.location.href = baseUrl + "memberCenter/view/orderEvaluate?orderId=" + orderId ;
            	}
            }, 500)
        }
    });
}
</script>
</body>
</html>