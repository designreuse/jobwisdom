<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>交易信息</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath %>css/mobile/share.min.css" />
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
    <style type="text/css">
    	.content {
	        height: 100%;
	    }
    </style>
  </head>
<body>
<div class="content pay-wrap">
	<div class="pay-wrap">
	    <div class="pay-title">
	        <span></span>
	        交易成功
	    </div>
	    <div class="pay-main">
	        <span class="star"></span>
	        <c:choose>
           		<c:when test="${transaction.boxStatus == 1 }">
               		<p class="pay-ma"><span>取货码</span></p>
			        <p class="number">${transaction.pickupCode }</p>
			        <p class="shengyu">截至取货时间：<span class="time">${fn:substring(transaction.pickupExpireTime, 5, 16)}</span></p>
           		</c:when>
           		<c:otherwise>
               		<p class="pay-ma"><span>已取货</span></p>
			        <p class="shengyu">谢谢，期待您的下次购物</p>
           		</c:otherwise>
           	</c:choose>
	        <ul class="pay-icon-wrap">
	            <li data-target="pay1" >
	                <div class="pay-icon">
	                    <img src="<%=basePath %>images/mobile/member/machine_icon.png" alt=""/>
	                </div>
	                <p>取货柜</p>
	            </li>
	            <li data-target="pay2">
	                <div class="pay-icon pay-icon1">
	                    <img src="${transaction.goodsInfo.goodsInfo.uboxPicture}" alt=""/>
	                </div>
	                <p>商品信息</p>
	            </li>
	            <li data-target="pay3">
	                <div class="pay-icon pay-icon2">
	                	<c:choose>
	                		<c:when test="${empty employeeInfo }">
	                			<img class="pay-select" src="<%=basePath %>images/mobile/member/server_default.png" alt=""/>
	                    		<span>未选择</span>
	                		</c:when>
	                		<c:otherwise>
	                			<img class="pay-select" src="<%=picPath %>${employeeInfo.headImage}" alt=""/>
	                			<span>${employeeInfo.employeeCode }</span>
	                		</c:otherwise>
	                	</c:choose>
	                </div>
	                <p>售后服务</p>
	            </li>
	        </ul>
	        <div class="pay-xinxi" id="pay1">
	            <span class="pay-arr1"></span>
	            <span class="pay-arr1-1"></span>
	            <div class="pay-address">
	                <div class="fl">
	                    <span><span>NO.${machineInfo.id }</span></span>
	                    <p>${machineInfo.address }<span class="iconfont">&#xe6e9</span></p>
	                </div>
	            </div>
	        </div>
	        <div class="pay-xinxi hide" id="pay2">
	            <span class="pay-arr2"></span>
	            <span class="pay-arr2-2"></span>
	            <div class="deal-card">
	                <div class="pay-xinxi-img"><img src="${transaction.goodsInfo.goodsInfo.uboxPicture}" alt=""></div>
	                <div class="deal-con">
	                    <h3 class="single-title">${transaction.goodsInfo.goodsInfo.uboxName }</h3>
	                    <div class="pro-price font-666">
	                    ${transaction.transactionAmount }元
	                    <c:if test="${transaction.transactionIntegral > 0 }">
	                    	＋${transaction.transactionIntegral }积分
	                    </c:if>
	                    </div>
	                    <c:if test="${!empty couponInfo or transaction.rewardsGiftAmount > 0 }">
	                    	<div class="pay-give">
		                    	<span class="iconfont icon-xuanzhong"></span>
		                    	已赠送
		                    	<c:if test="${!empty couponInfo }">
	                    			${couponInfo.couponPrice }元${couponInfo.couponName }，
	                    		</c:if>
	                    		<c:if test="${transaction.rewardsGiftAmount > 0 }">
	                    			${transaction.rewardsGiftAmount }元礼金
	                    		</c:if>
		                    </div>
	                    </c:if>
	                </div>
	            </div>
	        </div>
	        <div class="pay-xinxi hide" id="pay3">
	            <span class="pay-arr3"></span>
	            <span class="pay-arr3-3"></span>
	            <div class="deal-card">
	            	<c:choose>
                		<c:when test="${empty employeeInfo }">
                    		<div class="pay-xinxi-img pay-icon1"><img src="<%=basePath %>images/mobile/member/server_default.png" alt=""></div>
			                <div class="deal-con">
			                    <div class="pay-price font-666">未选择</div>
			                    <div class="pay-price font-666">点击右侧进行选择</div>
			                </div>
                		</c:when>
                		<c:otherwise>
                			<div class="pay-xinxi-img pay-icon1"><img src="<%=picPath %>${employeeInfo.headImage}" alt=""></div>
			                <div class="deal-con">
			                    <h3 class="single-title">${employeeInfo.employeeCode } ${employeeInfo.name }</h3>
			                    <div class="pay-price font-666">联系电话：${employeeInfo.phone }</div>
			                </div>
                		</c:otherwise>
                	</c:choose>
	            </div>
	            <c:if test="${empty transaction.serviceEmployeeId}">
		            <span id="modalBuy" class="iconfont icon-xiugai"></span>
	            </c:if>
	        </div>
	        <div class="pay-lj">
	            <div class="pay-tip">
	            <div class="fl tishi">温馨提示:</div>
	            <div class="fl">
	                <p>1.请在规定时间内取货，超过取货时间订单自动取消</p>
	                <p>2.在对应的友宝取货机输入取货码即可取货</p>
	            </div>
	          </div>
	        </div>
	        <div id="modalBuy-pop" class="modal fade">
	            <div class="box pay-box">
	                <p>请选择售后服务人员</p>
	                <div class="pay-menber">
	                    <ul>
	                    	<c:forEach items="${employeeList }" var="employee">
	                    		<li data-employee="${employee.employeeId }">
		                            <img src="<%=picPath %>${employee.headImage}" height="140" width="140" alt="" />
		                            <p><span class="fl">${employee.employeeCode }</span><span class="fr">${employee.name }</span></p>
		                        </li>
	                    	</c:forEach>
	                    </ul>
	                </div>
	            </div>
	            <div class="pat-btn">
	                <!-- <span><a class="btn col" href="javascript:void(0);">再挑挑～</a></span> -->
	                <span><a class="btn co2" href="javascript:setServerEmployee();">确认选择</a></span>
	            </div>
	        </div>
	    </div>
	</div>
</div>	  
<%@include file="../memberBase.jsp" %>
<script type="text/javascript">
var $mask = $('<div class="mask"></div>');
function removeWindow() {
	$("#modalBuy-pop").removeClass("in").addClass("hide");
    $($mask).remove();
    $("body").removeAttr("style");
}
$(function(){
    //弹出规格
    $("#modalBuy").on("click",function(){
        $("body").append($mask).css({"overflow":"hidden",height:"100%"});
        $("#modalBuy-pop").addClass("in").removeClass("hide");
        $mask.click(function(){
        	removeWindow();
        })
    });

    /*选择人员*/
    $(".pay-menber li").on("click",function(){
        $(this).addClass('current').siblings().removeClass('current')
    });

    $(".pay-icon-wrap li").click(function(){
        $(".pay-xinxi").addClass("hide");
        $("#" + $(this).attr("data-target")).removeClass("hide");
    })
    
    var payHeight= $(".pay-menber li").height();
	var payWidth= $(".pay-menber li").width();
	var pay1= parseInt($(".pay-menber li").css('margin-bottom'));
	var payz=payWidth*3+pay1*2;
	$(".pay-menber ul").css('max-height',payz);
	$(".pay-menber li").css('height',payWidth);
})

function setServerEmployee() {
	removeWindow();
	var curLi = $(".pay-menber .current");
	if (curLi == null || curLi.length == 0) {
		dialog("请选择为您售后服务的员工");
		return;
	}
	var employeeId = curLi.attr("data-employee");
	$.ajax({
		type : "POST",
		url : baseUrl + "uboxMall/view/setServer",
		data : "transactionId=${transaction.transactionId}&employeeId=" + employeeId,
		success : function(e){
			if (e.code != 0) {
				dialog(e.msg);
				return;
			}
			dialog("选择成功，有事记得找ta哦～");
			setTimeout(function(){
				window.location.reload();
			}, 5000);
		}
	});
}
</script>
</body>
</html>