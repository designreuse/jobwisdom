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
    <link rel="stylesheet" href="<%=basePath%>css/mobile/weui.min.css"/>  
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
		  background: url(../../<%=basePath%>images/mobile/member/mobile/agent/btn-bg.png) no-repeat;
		  border-radius: 10px;
		  text-align: center;
		  line-height:5.1875rem;
		  margin-top: 1.875rem;
		  margin: 0 auto;
		}
		.pay_way_ input{position:relative;top:-5px}
    </style>
    <style>
    .pay_way_ i{position: relative; top: -1.4rem;left: 0.4rem;}
	.zzc{position:fixed;top:0;left:0;width:100%;height:100%;background:rgba(71,67,55,0.8);z-index:1000;display:none}
    .zzc_div{width:82%;height:230px;background:white;margin:40% auto;padding:0 1rem;position:relative;border-radius:8px}
	.zzc_div .name{font-size:16px;text-align:center;padding:2rem 0 1rem 0}
	.zzc_div .price_{color:#e61717;font-size:20px;font-weight:bold;text-align:center}
	.zzc .way{margin-top:0.5rem;font-size:14px}
	.zzc .wx_pay{padding:0.5rem 0;border-top:1px solid #d9dce0;border-bottom:1px solid #d9dce0 ;}
	.zzc .wx_pay>img{width:60px;margin-right:1rem}
	.zzc_div>img{position:absolute;right:4px;top:4px;width:20px}
	.zzc .wx_pay span img{width:20px;margin-top:4px;width:40px;margin-right:1rem}
	.zzc .pay_button{text-align:center;margin-top:1rem}
	.zzc .pay_button button{width:26%;height:2rem;text-align:center;line-height:2rem;border-radius:4px;color:white;background:#ed4f1e;border:none;margin:0 1rem;font-size:13px}
	.zzc .way .p{margin-bottom:0.5rem}
	.zzc_pay_way{width:82%;height:350px;background:white;margin:40% auto;padding:0 1rem;position:relative;border-radius:8px;position:relative;left:-300px;opacity:0;top:-375px}
	.zzc_pay_way>p{color:#e26533;border-bottom:1px solid #d9dce0;padding: 0.5rem 0;font-size:16px}
    .zzc .pay_way_>div{padding:1rem 0;border-bottom:1px solid #d9dce0;font-size:14px}
    .zzc  .pay_way_>div input{position:relative;top:-1rem;left:1rem}
    .pay_way_ .img{display:inline-block;width:5rem;padding-right:1rem}
    .pay_way_ .img img{width:60px}
    .zzc .wx_img img{width:60px}
    .zzc .pay_way_{height:270px;overflow:overlay}
	.zzc .pay_way_button{text-align:center;margin-top:1rem}
	.zzc .pay_way_button button{width:26%;height:2rem;text-align:center;line-height:2rem;border-radius:4px;color:white;background:#ed4f1e;border:none;margin:0 1rem;font-size:14px}
	.wx_pay  input{display:none}
	.way i{position:relative;top:-5px}
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
            <div onclick="_system._guide(true)" class="share1 fr">
                <span class="iconfont icon-iconfontfenxiang c999"></span>
                <p class="c666">分享</p>
            </div>
        </div>
        <div class="pro-money" style="position:relative;top:-2.5rem;width: 70%;">
        	<span class="time">￥${goodsInfo.goodsPrice / 100}</span>
        </div>
        <span style="position:absolute;top:0px; position: absolute; top: 23rem;left: 65%;font-size: 1.2rem;color:#847F7F">已售: ${goodsInfo.salesCount }</span>
    </section>
    
   <div  style="position:relative;top:-3rem"> ${goodsInfo.goodsDesc}</div>

 	<div id="footer">
	 		<div class="f-con c">
	 			<c:if test="${isBuy == true }"><a class="btn btn-primary" onclick="initPay(${goodsInfo.storeId},${goodsInfo.goodsId})">立即购买</a></c:if>
				<c:if test="${isBuy == false }"><a class="btn btn-primary" onclick="dialog('库存不足')">库存不足</a></c:if>
	 		</div>
	 	</div>
	</div>  

	<div class="zzc">
	     <div class="zzc_div">
	     <div class="name">高档国外金扣蛇皮鳄鱼皮膏</div>
	     <div class="price_">¥80.00</div>
		 <div class="way">
		   <div class="p">选择支付方式</div>
		   <div class="wx_pay">
		      <div><em><span class="img"><img src="<%=basePath%>images/mobile/member/wx.png"> </span><i style="position:relative;top:-8px">微信支付</i></em><img src="<%=basePath%>images/mobile/member/right.png" style="position:absolute;right:10px;width:18px;margin-top:5px"></div>
		   </div>
		 </div>
		 <div class="pay_button">
		    <button>确认付款</button> <button class="cancle">取消</button>
		 </div>
  </div>
      
   
  <div class="zzc_pay_way" >
         <p>支付方式</p>
         <div class="pay_way_">
	     <div><span class="img wx_img"><img src="<%=basePath%>images/mobile/member/wx.png"> </span><i>可使用微信中零钱进行支付</i><input type="radio" name="simple"></div>
	     <div><span class="img"><img src="<%=basePath%>images/mobile/member/card.png"> </span><i>可使用微信中零钱进行支付</i><input type="radio" name="simple" ></div>
	     <div><span class="img"><img src="<%=basePath%>images/mobile/member/card.png"> </span><i>可使用微信中零钱进行支付</i><input type="radio" name="simple"></div>
		 <div><span class="img"><img src="<%=basePath%>images/mobile/member/card.png"> </span><i>可使用微信中零钱进行支付</i><input type="radio" name="simple"></div>
		 <div><span class="img"><img src="<%=basePath%>images/mobile/member/card.png"> </span><i>可使用微信中零钱进行支付</i><input type="radio" name="simple"></div>
	  </div>
	  <div class="pay_way_button">
	     <button class="sure">确认</button>
		 <button class="cancle">取消</button>
	  </div>
     </div> 	  
  </div>


<!-- 会员注册提示 -->
<div class="weui_dialog_confirm hide" id="confirmWindow">
   <div class="weui_mask"></div>
   <div class="weui_dialog">
       <div class="weui_dialog_hd"><strong class="weui_dialog_title">商城公告</strong></div>
       <div class="weui_dialog_bd">您还不是会员,暂时无法购买,请先注册</div>
       <div class="weui_dialog_ft">
           <a href="#" class="weui_btn_dialog default" onclick="$('#confirmWindow').addClass('hide')">取消</a>
           <a href="#" class="weui_btn_dialog primary" onclick="register();">去注册</a>
       </div>
   </div>
</div>

<!-- 操作提示 -->
<div id="toast" style="display: none;">
   <div class="weui_mask_transparent"></div>
   <div class="weui_toast">
       <i class="weui_icon_toast"></i>
       <p class="weui_toast_content">支付成功</p>
   </div>
</div>

<div class="s-modal hide s-modal-miss" >
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

<%@include file="../share.html" %>
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
 
 /**检查是否购买者为会员身份*/
 function checkPay() {
	 if (isEmpty('${memberInfo.memberId}')) {
		 $("#confirmWindow").removeClass("hide");
		 return false;
     }
	 else {
		 return true;
	 }
 }
 /**跳转注册页面*/
 function register() {
	 window.location.href = baseUrl + "memberCenter/view/register/${goodsInfo.storeId}";
 }
 /**发起微信支付*/
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
        	$('#toast').show();
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
  
  $(function(){
	     $('.wx_pay').click(function(){
		    $('.zzc_div').animate({
			  left:-300,
			  opacity:0
			},500,function(){
			  $('.zzc_pay_way').animate({
			  left:0,
			  opacity:1
			  })
			
			})
	      })
   })
	  
	$(function(){
	     $('.cancle').click(function(){
		    $(this).parents('.zzc').fadeOut(1000);
			$('.zzc').fadeOut(1000);
     })
   })
  //点击确认
  $(function(){
    $('.pay_way_button .sure').click(function(){
        var check=$('.pay_way_ input[name="simple"]:checked').parents('div').html();
		if(check==null) return;
		$('.wx_pay').html(check);
	    $(this).parents('.zzc_pay_way').animate({
		   left:-300,
		   opacity:0
		},function(){
		   $('.zzc_div').animate({
		    left:0,
			 opacity:1
		   })
		})
	});
  })
  jQuery(function(){
	    jQuery('.btn.btn-primary').click(function(){
		  jQuery('.zzc').fadeIn('slow') ;       
	  })
  }) 
</script>
</body>
</html>