<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>积分商城</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath %>css/mobile/share.min.css" />
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
  </head>
<body>


<div class="content wrap">
	<c:if test="${hasUbox == 1 }">
		<div class="project-detail-descript">
            <div class="swiper-container">
                <div class="swiper-wrapper">
                    <div class="swiper-slide">
		            	<img src="<%=picPath %>zefun/shop/banner/1_雀巢咖啡.jpg" />
		            </div>
		            <div class="swiper-slide">
		                <img src="<%=picPath %>zefun/shop/banner/2_迪奥化妆品.jpg" alt=""/>
		            </div>
		            <div class="swiper-slide">
		                <img src="<%=picPath %>zefun/shop/banner/3_歌帝梵巧克力.jpg" alt=""/>
		            </div>
		            <div class="swiper-slide">
		                <img src="<%=picPath %>zefun/shop/banner/4_飞利浦蓝牙音响.jpg" alt=""/>
		            </div>
                </div>
            </div>
        </div>
		<div class="">
		    <ul class="store-tab">
		        <li class="current" onclick="showConten(1, this)">购物商城</li>
		        <li onclick="showConten(2, this)">积分商城</li>
		    </ul>
		</div>
	</c:if>
	
	<!-- 友宝商城 -->
    <div id="ubox" class="waterfall pb7 hide">
    	<c:forEach items="${goodsList}" var="storeGoods">
	      	<div class="grid">
	      		<c:choose>
	      			<c:when test="${storeGoods.goodsInfo.uboxStock <= 0 }">
	      				<a href="javascript:void(0);">
		      				<div class="shop-status">
				                <img src="<%=basePath %>images/mobile/member/shop-status.png" alt="">
				            </div>
				            <div class="imgholder">
				                <img class="lazy" src="<%=picPath %>img_lazy_loding.png" data-original="${storeGoods.goodsInfo.uboxPicture}" />
				            </div>
				            <div class="meta">
				                <h3>${storeGoods.goodsInfo.goodsName }</h3>
				                <p class="pro-p"><span class="pro-price"><i>￥</i>${storeGoods.storeGoodsPrice / 100 }</span></p>
				            </div>
			            </a>
	      			</c:when>
	      			<c:otherwise>
	      				<a href="<%=basePath %>mobile/view/pay/goodsInfo?storeId=${session_key_store_id}&storeGoodsId=${storeGoods.storeGoodsId}">
				            <div class="imgholder">
				                <img class="lazy" src="<%=picPath %>img_lazy_loding.png" data-original="${storeGoods.goodsInfo.uboxPicture}" />
				            </div>
				            <div class="meta">
				                <h3>${storeGoods.goodsInfo.goodsName }</h3>
				                <p class="pro-p"><span class="pro-price"><i>￥</i>${storeGoods.storeGoodsPrice / 100 }</span></p>
				            </div>
				        </a>
	      			</c:otherwise>
	      		</c:choose>
		    </div>
    	</c:forEach>
	</div>
	
	<!-- 积分商城 -->
	<div id="coupon" class="waterfall pb7 hide">
	    <div class="jifenduihuan-wrap">
	        <ul class="designer-list">
	           <c:forEach items="${page.results }" var="couponInfo">
	               <c:set var="couponType" value="店内所有项目与商品" />
	               <c:set var="couponImage" value="shop_coupon_project.png" />
	               <c:choose>
	                   <c:when test="${couponInfo.couponType == 2 }">
	                       <c:set var="couponType" value="商品" />
	                       <c:set var="couponImage" value="${couponInfo.projectImage }" />
	                   </c:when>
	                   <c:when test="${couponInfo.couponType == 1 }">
                           <c:set var="couponType" value="项目" />
                           <c:set var="couponImage" value="${couponInfo.projectImage }" />
                       </c:when>
                       <c:when test="${couponInfo.couponType == 4 }">
                           <c:set var="couponType" value="项目系列" />
                           <c:set var="couponImage" value="shop_coupon_project.png" />
                       </c:when>
                       <c:when test="${couponInfo.couponType == 5 }">
                           <c:set var="couponType" value="商品系列" />
                           <c:set var="couponImage" value="shop_coupon_goods.png" />
                       </c:when>
	               </c:choose>
	               
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
	
	<div class="footer">
      <ul>
        <li>
          <a href="<%=basePath %>memberCenter/view/home/${session_key_store_id}/1">
            <span class="iconfont icon-wode"></span>
            <span class="word">我的</span>
          </a>
        </li>
        <li>
          <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_id}/1">
            <span class="iconfont icon-yuyue5"></span>
            <span>预约</span>
          </a>
        </li>
        <li  class="active">
          <a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_id}/1">
            <span class="iconfont icon-jifen"></span>
            <span>商城</span>
          </a>
        </li>
        <li >
          <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_id}/1">
            <span class="iconfont icon-dianpu2"></span>
            <span>门店</span>
          </a>
        </li>
      </ul>
    </div>
	
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
<script src="<%=basePath %>js/mobile/jquery.lazyload.min.js"></script>
<script src="<%=basePath %>js/mobile/blocksit.min.js"></script>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript">
	var mySwiper = new Swiper ('.swiper-container', {
	    autoplay: 2000,
	    direction : 'horizontal'
	});
	if ('${hasUbox}' == 1) {
		var waterfallCol; //列数
		var winWidth = $(window).width();
		var winHeight = $(window).height();
		var conWidth; //列宽
		var waterfall = $("#waterfall");
		
		$(document).ready(function() { 
			setWidth();
			setBlocksIt();
			
			$("img.lazy").lazyload({ 
			  threshold : 150, 
			  load:function(){
				console.log("lazyload...");
				setBlocksIt()
			  }
			}); 
		  
			//window resize
			var currentWidth = $(window).width();
			$(window).resize(function() {  
			    winHeight = $(window).height();
			    setWidth();
			    
			    if(conWidth != currentWidth) {
			      currentWidth = conWidth;
			      $(waterfall).width(conWidth);
			      setBlocksIt();
			    }
			});
		});
		
		function setBlocksIt(){
			$(waterfall).BlocksIt({
		      numOfCol: waterfallCol,
		      offsetX: 8,
		      offsetY: 8,
		      blockElement: '.grid'
		    });
		}
		//设置列数
		function setWidth(){
		  	winWidth = $(window).width();
		
		    if(winWidth < 500) {
		      conWidth = winWidth;
		      waterfallCol = 2;
		    } else if(winWidth < 750) {
		      conWidth = winWidth;
		      waterfallCol = 3;
		    } else if(winWidth < 1100) {
		      conWidth = 880;
		      waterfallCol = 4;
		    } else {
		      conWidth = 1100;
		      waterfallCol = 5;
		    }
		}
		
		$("#ubox").removeClass("hide");
	}
	else {
		$("#coupon").removeClass("hide");
	}
	
	function showConten(type, obj){
		if (type == 1) {
			$("#ubox").removeClass("hide");
			$("#coupon").addClass("hide");
		}
		else {
			$("#coupon").removeClass("hide");
			$("#ubox").addClass("hide");
		}
		$(obj).addClass("current");
		$(obj).siblings().removeClass("current");
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
    
    $(function () {
       var width = document.body.scrollWidth;
        imgWidth = width - 168;
       $(".price-img").css("width", imgWidth);
    })
</script>
</body>
</html>