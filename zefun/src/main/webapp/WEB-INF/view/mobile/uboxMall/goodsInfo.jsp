<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>商品信息</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath %>css/mobile/share.min.css" />
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
    <style type="text/css">
    	.swiper-container img {
	        -webkit-box-shadow: 1px 2px 5px rgba(0,0,0,.4);
	    }
    </style>
  </head>
<body>

<div class="content wrap">
	<div class="project-detail-descript">
	    <div class="swiper-container">
	        <div class="swiper-wrapper">
	        	<div class="swiper-slide">
	                <img class="lazy" name="lazyImage" src="<%=picPath %>${goodsInfo.goodsImage}" data-original="${goodsInfo.goodsImage}"/>
	            </div>
	        	<c:forEach items="${fn:split(goodsInfo.affiliatedImage, ',') }" var="img">
	        		<div class="swiper-slide">
		                <img class="lazy" name="lazyImage" src="<%=picPath %>${img}" data-original="${img }"/>
		            </div>
	        	</c:forEach>
	        </div>
	        <div class="pagination"></div>
	    </div>
	</div>

    <section class="box pro-desc">
        <div class="ovh">
            <div class="fl">
                <h1>${goodsInfo.goodsName }</h1>
                <div class="pro-others">
                    <span class="item font-666">已售出：${goodsInfo.salesCount }</span>
                </div>
            </div>
            <div class="share1 fr">
                <span class="iconfont icon-iconfontfenxiang c999"></span>
                <p class="c666">分享</p>
            </div>
        </div>
        <div class="pro-money">
        	<span class="time">￥${goodsInfo.goodsPrice / 100}</span>
        	<%-- <c:if test="${storeGoods.storeGoodsIntegral > 0 }">
        		<span>或 </span><span class="time">￥${storeGoods.storeGoodsPrice / 100}+${storeGoods.storeGoodsIntegral }积分</span>
        	</c:if> --%>
        </div>
        <%-- <c:choose>
        	<c:when test="${!empty storeGoods.rewardsCoupon and storeGoods.rewardsGiftAmount > 0 }">
        		<p class="pro-yh"><span>送</span>${storeGoods.rewardsCoupon.couponPrice }元${storeGoods.rewardsCoupon.couponName }, 另送${storeGoods.rewardsGiftAmount }元礼金</p>
        	</c:when>
        	<c:when test="${!empty storeGoods.rewardsCoupon and storeGoods.rewardsGiftAmount <= 0 }">
        		<p class="pro-yh"><span>送</span>${storeGoods.rewardsCoupon.couponPrice }元${storeGoods.rewardsCoupon.couponName }</p>
        	</c:when>
        	<c:when test="${empty storeGoods.rewardsCoupon and storeGoods.rewardsGiftAmount > 0 }">
        		<p class="pro-yh"><span>送</span>${storeGoods.rewardsGiftAmount }元礼金</p>
        	</c:when>
        </c:choose> --%>
    </section>

    <h2 class="box-title" style="background-color: #F7F7F7;">商品详情</h2>

    <%-- <section class="box">

        <div class="article">
            <c:if test="${!empty storeGoods.goodsInfo.goodsContentList }">
                   <c:forEach var="content" items="${storeGoods.goodsInfo.goodsContentList }">
					<c:if test="${content.type == '1' }">
						<p>${content.text }</p>
					</c:if>
					<c:if test="${content.type == '2' }">
						<p><img style="max-width: 100%;max-height: 100%;" src="<%=picPath %>${content.text}"></p>
					</c:if>
				</c:forEach>
				<p></p>
			</c:if>
        </div>

    </section> --%>

    <h3 class="box-title" style="font-size: 1.4rem;font-weight: normal;background-color: #F7F7F7;">${goodsInfo.goodsDesc}</h3>
    <section class="box"></section>
    <!-- <h2 class="box-title">订单&取货</h2> -->

    <!-- <section class="box">

        <div class="maker ovh">
            <div class="pic fl">
                <img src="../../assets/images/code.jpg" alt=""/>
            </div>
            <div class="fr wz">
                <p>长按左侧图片</p>
                <p>选择“识别图中二维码”</p>
                <p>即可关注”智放“</p>
            </div>
        </div>

        <div class="article " style="padding-bottom: 50px;">
            <p class="c666"><span class="hot">取货号码</span><br />关注“智放”微信公众号，进入“友宝商城”，点击“订单列表”，即可看到订单的取货号码。
            </p>
            <p><span class="hot">取货地址</span><br /> <span class="iconfont c999">&#xe6e9</span>深圳市福田区家乐大厦508</p>
        </div>

    </section> -->
    
 	<div id="footer">
 		<div class="f-con c">
			<a class="btn btn-primary" onclick="initPay(${goodsInfo.storeId},${goodsInfo.goodsId})">立即购买</a>
 		</div>
 	</div>
</div>  

<div id="modalBuy-pop" class="modal fade">
    <div class="box">
        <div class="deal-card">
            <div class="deal-img s"><img style="width: 100%;height: 100%;" src="<%=picPath %>${storeGoods.goodsInfo.uboxPicture }" alt=""></div>
            <div class="deal-con">
                <h3 class="single-title">${storeGoods.goodsInfo.goodsName }</h3>
                <div class="pro-price font-666">已售出：${storeGoods.goodsInfo.goodsSales }</div>
            </div>
        </div>
        <p>选择购买方式</p>
        <div class="pro-price">
        	<c:choose>
        		<c:when test="${storeGoods.storeGoodsIntegral <= 0 }">
        			<span class="pro-yuanjia1 pro-current" data-type="2">${storeGoods.goodsInfo.uboxOriginalPrice / 100}元 <span class="iconfont"> </span></span>
        		</c:when>
        		<c:when test="${memberInfo.balanceIntegral > storeGoods.storeGoodsIntegral }">
        			<span class="pro-yuanjia1 pro-current" data-type="1">${storeGoods.storeGoodsPrice / 100}元 + ${storeGoods.storeGoodsIntegral }积分 <span class="iconfont icon-xuanzhong"> </span></span>
            		<span class="pro-yuanjia1" data-type="2">${storeGoods.goodsInfo.uboxOriginalPrice / 100}元 <span class="iconfont"> </span></span>
        		</c:when>
        		<c:otherwise>
        			<span class="pro-yuanjia" data-type="1">${storeGoods.storeGoodsPrice / 100}元 + ${storeGoods.storeGoodsIntegral }积分 <span class="iconfont"> </span></span>
            		<span class="pro-yuanjia1 pro-current" data-type="2">${storeGoods.goodsInfo.uboxOriginalPrice / 100}元 <span class="iconfont icon-xuanzhong"> </span></span>
        		</c:otherwise>
        	</c:choose>
        </div>
    </div>
	
	<c:if test="${memberInfo.balanceIntegral < storeGoods.storeGoodsIntegral }">
	    <p class="pro-p1">您的积分不足，可以选择现金支付。<!-- <a href="#" style="color: #4ccdc9">获得更多积分>></a> --></p>
	</c:if>
    <div class="footer-con c"><a class="btn btn-primary" href="javascript:checkPay();">确认支付</a></div>
</div>

<div class="s-modal hide s-modal-miss" id="confirmWindow">
    <div class="s-modal-wrap">
        <div class="d-member-info">
            <div class="n-modal-title text-center">
                <span>购买提示</span>
                <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
                <div class="clearfix"></div>
            </div>
            <div class="s-modal-body">
                <div class="word text-left tip" id="confirmTip">
                    	抱歉，您还不是本店会员。只有会员才能购买商品并获得奖励哦！
                </div>
            </div>
            <div class="s-modal-foot">
                <div class="modal-cancel">继续逛逛</div>
                <div class="modal-confirm" onclick="register();">立即注册</div>
            </div>
        </div>
    </div>
</div>

<%@include file="../memberBase.jsp" %>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.lazyload.min.js"></script>
<script>
	$(document).ready(function (){
		var width = $(window).width();
		$(".swiper-container").height(width);
		
       var mySwiper = new Swiper ('.swiper-container', {
           autoplay: 2000,
           direction : 'horizontal',
       });
       
       $("img[name='lazyImage']").each(function(){
    	   var dw = width * 2;
           $(this).attr("src", picUrl + $(this).attr("data-original") + "?imageView2/1/w/" + dw + "/h/" + dw)
       });

		$("img.lazy").lazyload({ 
		  threshold : 100,
		  skip_invisible : false,
		  effect: "fadeIn"
		}); 
    });
	
	$(function(){
        $(".pro-price .pro-yuanjia1").on("click",function(){
        	$(".pro-current").removeClass("pro-current").find('.icon-xuanzhong').removeClass("icon-xuanzhong");
            $(this).addClass("pro-current").find('.iconfont').addClass("icon-xuanzhong");
        })
        
        $("#modalBuy").on("click",function(){
            var $mask = $('<div class="mask"></div>');
            $("body").append($mask).css({"overflow":"hidden",height:"100%"});
            $("#modalBuy-pop").addClass("in");
            $mask.click(function(){
                $("#modalBuy-pop").removeClass("in");
                $(this).remove();
                $("body").removeAttr("style");
            })
        });
  	});
 
 function checkPay() {
	 if (isEmpty('${memberInfo.memberId}')) {
		 $("#confirmWindow").removeClass("hide");
		 return false;
     }
	 else {
		 return true;
	 }
	 /* else {
		 pay();
	 } */
 }
 
 function register() {
	 window.location.href = baseUrl + "memberCenter/view/register/${goodsInfo.storeId}";
 }

function initPay(storeId, goodsId){
	var isOk = checkPay();
	if(!isOk)return;
	var data = "storeId=" + storeId + "&goodsId=" + goodsId;
 	$.ajax({
 		  type : "POST",
 		  url : baseUrl + "app/goodsinfo/wechat/init/pay",
 		  data : data,
 		  dataType : "json",
 		  beforeSend : function(){
		  },
		  complete : function(){
		  },
 		  success : function(e){
 			  if (e.code != 0) {
 				  dialog(e.msg);
 				  return;
 			  }
 			  callWeixin(e.msg);
 		  }
 	  });
}
 
 function pay(){
	  var type = $(".pro-current").attr("data-type");
	  $.ajax({
		  type : "POST",
		  url : baseUrl + "uboxMall/action/goodsPay",
		  data : "storeId=${session_key_store_id}&storeGoodsId=${storeGoods.storeGoodsId}&payType=" + type,
		  dataType : "json",
		  success : function(e){
			  if (e.code != 0) {
				  dialog(e.msg);
				  return;
			  }
			  callWeixin(e.msg);
		  }
	  });
  }
  function callWeixin(rj) {
    WeixinJSBridge.invoke('getBrandWCPayRequest', {
        "appId" : rj.appId,
        "timeStamp" : rj.timeStamp,
        "nonceStr" : rj.nonceStr,
        "package" : rj.package,
        "signType" : rj.signType,
        "paySign" : rj.paySign
    }, function(res) {
        if (res.err_msg == "get_brand_wcpay_request:ok") {
        	window.location.href = baseUrl + 'memberCenter/view/orderList';
            //window.location.href = baseUrl + "uboxMall/view/goodsPayCallback/" + rj.transactionId;
        } /* else {
        	$.ajax({
      		  type : "POST",
      		  url : baseUrl + "uboxMall/action/goodsPayCancel",
      		  data : "transactionId=" + rj.transactionId,
      		  dataType : "json",
      		  success : function(e){
      			  if (e.code != 0) {
      				  dialog(e.msg);
      				  return;
      			  }
      			  dialog("取消支付");
      		  }
      	  });
        } */
    });
  }
</script>
</body>
</html>