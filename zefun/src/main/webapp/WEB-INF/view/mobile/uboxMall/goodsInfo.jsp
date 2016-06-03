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
	    .s-modal-wrap{
		  position: absolute;
		  width: 100%;
		  top:15%;
		  left:50%;
		  margin-left: -15rem;
		}
		.jd-wrap{
		  /*width:36.6875rem;*/
		  width: 30rem;
		  position: fixed;
		  margin: 0 auto;
		}
		.jd-wrap img{
		  width: 26rem;
		  /* height: 25.8125rem;*/
		}
		.jd-main{
		  position: absolute;
		  top: 19.5rem;
		  width: 100%;
		  background-color: #ffd83b;
		  border-radius: 1.25rem;
		  padding:1.75rem 1.25rem 1rem;
		  box-sizing: border-box;
		  font-size:2.5rem;
		  text-align: left;
		
		}
		.jd-main span{
		  color: #f82727;
		  font-size: 2.8rem;
		
		}
		.jd-btn{
		  width: 27.5625rem;
		  height:5.1875rem;
		  background: url(../../images/mobile/agent/btn-bg.png) no-repeat;
		  border-radius: 10px;
		  text-align: center;
		  line-height:5.1875rem;
		  margin-top: 1.875rem;
		  margin: 0 auto;
		}
    </style>
  </head>
<body>

<div class="">
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

    <section class="box pro-desc" style="position:relatve">
        <div class="ovh">
            <div class="fl">
                <h1></h1>
                <div class="pro-others" style="font-size:1.6rem;font-weight:bold">
                    <span class="item font-666">${goodsInfo.goodsName }</span>
                </div>
            </div>
            <div onclick="share()" class="share1 fr">
                <span class="iconfont icon-iconfontfenxiang c999"></span>
                <p class="c666">分享</p>
            </div>
        </div>
        <div class="pro-money" style="position:relative;top:-2.5rem">
        	<span class="time">￥${goodsInfo.goodsPrice / 100}</span>
        </div>
        <span style="position:absolute;top:0px; position: absolute; top: 23rem;left: 65%;font-size: 1.2rem;color:#847F7F">已售: ${goodsInfo.salesCount }</span>
    </section>
    
   <div style="position:relative;top:-3rem"> ${goodsInfo.goodsDesc}</div>

 	<div id="footer">
 		<div class="f-con c">
 			<c:if test="${isBuy == true }"><a class="btn btn-primary" onclick="initPay(${goodsInfo.storeId},${goodsInfo.goodsId})">立即购买</a></c:if>
			<c:if test="${isBuy == false }"><a class="btn btn-primary" onclick="dialog('库存不足')">库存不足</a></c:if>
 		</div>
 	</div>
</div>  

<div id="modalBuy-pop" class="modal fade">
    <div class="box" >
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
<div class="s-modal no-padding hide s-modal-miss" id="shareTip">
    <div class="s-modal-wrap">
        <div class="jd-wrap">
            <img src="<%=picPath %>agent_share_tip.png" alt=""/>
            <div class="jd-main">
                	已为你生成该商品 的推广链接, 请点击右上角分享吧！
                <div class="jd-btn">我知道了</div>
            </div>
        </div>
    </div>
</div>
<%@include file="../wechatBase.jsp" %>
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
		 //$("#modalBuy-pop").addClass("in");
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
  var title = '${goodsInfo.goodsName }';
  var desc = '围观围观,新品上架,速速抢购';
  var link = window.location.href;
  var imgUrl = picUrl + '${goodsInfo.goodsImage }';
  function share(){
		$("#shareTip").removeClass("hide");
		wx.onMenuShareAppMessage({
			  title: title,
			  desc: desc,
			  link: link,
			  imgUrl: imgUrl
			});
		wx.onMenuShareTimeline({
		  title: title,
		  link: link,
		  imgUrl: imgUrl
		});
	}
</script>
</body>
</html>