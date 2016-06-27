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
    <title>在线商城</title>
    
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath %>css/mobile/share.min.css" />
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
	<script type="text/javascript" src="<%=swiperJsPath%>"></script>
    
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/mobile/style.css">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>css/mobile/shop.css">
  </head>
    
<body>
<div class="con">
       <header  class="clearfix header_">
       	  <div class="down_content" onclick="$('.more_shop').show()">
       	     <img src="<%=basePath%>images/mobile/member/down_content.png">
       	  </div>
       	  <div class="search">
             <span class="search_img"><img src="<%=basePath%>images/mobile/member/search.png"></span> <input type="search" name="search" placeholder="搜索">
       	  </div>

       	  <div class="more_shop">
             <p>更多店铺</p>
            <ul>
            	<c:forEach items="${storeList }" var="store"><a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_account }/1?selectedStoreId=${store.storeId }"><li>${store.storeCity }<span>${store.storeName }</span></li></a></c:forEach>
            </ul>
       	  </div>
       </header>
       
       <div class="project-detail-descript" style="height:230px;">
          <div class="swiper-container">
              <div class="swiper-wrapper">
              		<c:forEach items="${fn:split(storeShop.adsense, ',') }" var="pic">
	                    <div class="swiper-slide">
		            	    <img src="${pic}" />
		                </div>
              		</c:forEach>
              </div>
          </div>
       </div>

      <div class="img">
        <div><img src="<%=basePath%>images/mobile/member/app_10.png" onclick="showConten('aestSellers')"><p>新品上市</p></div>
        <div><img src="<%=basePath%>images/mobile/member/app_11.png" onclick="showConten('bestSellers')"><p>热销产品</p></div>
        <div><img src="<%=basePath%>images/mobile/member/app_12.png" onclick="showConten('coupon')"><p>积分兑换</p></div>
       </div>
       <div id="aestSellers" style="margin-bottom: 7rem;">
	       <div class="shop_content">
	            <p><span class="recommend_">新品上市</span><a href="<%=basePath %>memberCenter/view/shopCenter/list/${session_key_store_account}?selectStoreId=${storeId}">更多>></a></p>
	        </div>
          <div class="shop_content_  clearfix">
          	<c:forEach items="${aestSellers }" var="goodsList">
          	<a href="<%=basePath %>mobile/view/pay/goodsInfo?storeId=${goodsList.storeId }&storeGoodsId=${goodsList.goodsId }">
                  <div class="recommend_shop">
                  	 <div><img src="<%=picPath %>${goodsList.goodsImage}"></div>
                  	 <span>${goodsList.goodsName }</span>
                     <div class="al_sell">
                         <span class="number_">已售0件</span>
                         <span class="price_">¥ ${goodsList.goodsPrice }</span>
                     </div>
                  </div>
             </a>
             </c:forEach>
           </div> 
       </div>
       
       <div id="bestSellers" class="hide" style="margin-bottom: 7rem;">
	       <div class="shop_content">
	            <p><span class="recommend_">热销产品</span><a href="<%=basePath %>memberCenter/view/shopCenter/list/${session_key_store_account}">更多>></a></p>
	        </div>
          <div class="shop_content_  clearfix">
          	<c:forEach items="${bestSellers }" var="goodsList">
          	<a href="<%=basePath %>mobile/view/pay/goodsInfo?storeId=${goodsList.storeId }&storeGoodsId=${goodsList.goodsId }">
                  <div class="recommend_shop">
                  	 <div><img src="<%=picPath %>${goodsList.goodsImage}"></div>
                  	 <span>${goodsList.goodsName }</span>
                     <div class="al_sell">
                         <span class="number_">已售0件</span>
                         <span class="price_">¥ ${goodsList.goodsPrice }</span>
                     </div>
                  </div>
             </a>
             </c:forEach>
           </div> 
       </div>
     </div>
	
	<!-- 积分兑换 -->
	<div id="coupon" class="waterfall pb7 hide">
	    <div class="jifenduihuan-wrap">
	        <ul class="designer-list">
	           <c:forEach items="${page.results }" var="couponInfo">
	               <li class="designer-item-content">
		                <img src="<%=picPath %>${couponImage}">
		                <div class="info">
		                    <div class="info-wrap">
		                        <div class="fs30 font-333">${couponInfo.couponPrice }元${couponInfo.couponName}</div>
		                        <div class="fs22 font-666">需要：${couponInfo.couponVantages }积分</div>
		                        <div class="shop-li ">
		                           <span></span>适用于${couponInfo.projectName}${couponType }
		                        </div>
		                    </div>
		                </div>
		                <a href="javascript:exchange(${couponInfo.couponId});" class="shop-btn fr">兑 换</a>
		                <span id="${couponInfo.couponId}_tip" class="hide">
				                           您确定使用${couponInfo.couponVantages }积分，兑换${couponInfo.couponPrice }元${couponInfo.projectName}${couponType }现金抵用券吗？
				                           该券将在<span style="color: red;">${couponInfo.overdueTime }</span>过期，兑换后尽快到店使用哦。
                       	</span>
		            </li>
	           </c:forEach>
	        </ul>
	        <p></p>
	    </div>
    </div>
 <ul class="bottom_fix clearfix">
 		<a href="<%=basePath %>memberCenter/view/home/${session_key_store_account}/1">
	      <li><img src="<%=basePath %>images/mobile/member/botton_1_1.png">
		      <p>我的</p>
		  </li>
	    </a>
	    <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_account}/1">
	    	<li><img src="<%=basePath %>images/mobile/member/botton_2.png">
		       <p>预约</p>
		    </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_account}/1">
		  <li><img src="<%=basePath %>images/mobile/member/botton_3_3.png">
		    <p>商城</p>
		  </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_account}/1">
	     <li><img src="<%=basePath %>images/mobile/member/botton_4.png">
		     <p>门店</p>
		  </li>
	    </a>
 </ul>
	<!--确认兑换-->
	<div class="s-modal hide s-modal-miss" id="confirmWindow">
	    <div class="s-modal-wrap">
	        <div class="d-member-info">
	            <div class="n-modal-title text-center">
	                <span>确认兑换</span>
	                <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
	                <div class="clearfix"></div>
	            </div>
	            <div class="s-modal-body">
	                <div class="word text-left tip" id="confirmTip">
	                    
	                </div>
	            </div>
	            <div class="s-modal-foot">
	                <div class="modal-cancel">取消</div>
	                <div class="modal-confirm" onclick="exchangeConfirm();">确认</div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!--积分不足-->
	<div class="s-modal hide s-modal-miss" id="duihuan-buzu">
	    <div class="s-modal-wrap">
	        <div class="d-member-info">
	            <div class="n-modal-title text-center">
	                <span>抱歉！积分余额不足</span>
	                <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
	                <div class="clearfix"></div>
	            </div>
	            <div class="s-modal-body">
	                <div class="body-title">您可以通过以下方式兑换积分:</div>
	                <div class="word text-left">
	                    <p>1.您在对会员卡进行充值或消费时均可获得积分奖励。</p>
	                    <p>2.您可以再积分商城兑换优惠券，在消费项目或购买商品时均可抵现。</p>
	                </div>
	            </div>
	            <div class="s-modal-foot">
	                <div class="modal-confirm" style="width: 480px;">确认</div>
	            </div>
	        </div>
	    </div>
	</div>
</div>  
<%@include file="../memberBase.jsp" %>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.lazyload.min.js"></script>
<script type="text/javascript">
    /**轮播*/
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

	function showConten(type){
		$("#coupon").addClass("hide");
		$("#aestSellers").addClass("hide");
		$("#bestSellers").addClass("hide");
		$("#"+type).removeClass("hide");
	}

    var couponId = '';
    function exchange(c){
    	$("#confirmTip").html($("#" + c + "_tip").html());
    	$("#confirmWindow").removeClass("hide");
    	couponId = c;
    } 
    
    function exchangeConfirm(){
    	$.ajax({
    		url : baseUrl + "memberCenter/action/exchangeCoupon",
    		type : "POST",
    		data : "couponId=" + couponId,
    		success : function(e){
    			if (e.code != 0) {
    				dialog(e.msg);
    			} else {
	    			dialog("兑换成功，已放入您的优惠券列表！");
    			}
    		}
    	});
    }
</script>
</body>
</html>