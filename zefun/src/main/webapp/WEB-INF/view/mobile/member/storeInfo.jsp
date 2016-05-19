<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>店铺介绍</title>
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
    <style>
	    .swiper-slide{
	        width: 100% !important;
	    }
	    .swiper-slide img{
	        width: 100% !important;
	        max-width: none !important;
	    }
	    .swiper-container{
	        width: 100% !important;
	    }
	    .project-detail-descript{
	        width: 100% !important;
	    }
	    .mb-footer {
	        margin-bottom: 6.125rem;
	    }
	    
	    .recommend_shop {
		    width: 46%;
		    margin-left: 3%;
		    float: left;
		    border-radius: 0.5rem;
		    border: 1px solid #e1e1e1;
		    background: white;
		    margin-bottom: 1rem;
		    margin: 0;
		    padding: 0;
		    list-style: none;
		}
		.al_sell {
		    padding-left: 0.2rem;
		    margin-bottom: 0.5rem;
		}
		.al_sell .number_ {
		    color: #9f9f9f;
		    font-size: 0.6rem;
		}
		.al_sell .price_ {
		    color: #e0371b;
		    position: relative;
		    left: 30%;
		}
		.content_detial{float:left;width:50%}
		.detail_text h2{    display: block;
    margin: 0.5rem 0rem 1rem 0.2rem;
    font-weight: bold;
    color: black;
    font-size: 1.3rem;}
    .detail_text{font-size:10px}
    .text_content,.list_money{    color: #9f9f9f;
    font-size: 0.6rem;margin-top:0!important;    padding-top: 0rem!important;}
    .detail_text span{display:inline-block;margin:0 1rem}
	</style>
    <style type="text/css">
    .bottom_fix{width:100%;background:white;position:fixed;bottom:0;height:6rem;z-index:2}
	.bottom_fix li{float:left;width:25%;text-align:center;line-height:3rem;margin-top:0.5rem}
	.bottom_fix img{width:3rem}
    </style>
  </head>
<body>

<div class="content wrap">
     <div class="shop-intro pb7">
	    <div class="shop-info">
	        <div class="project-detail-descript">
	            <div class="swiper-container">
	                <div class="swiper-wrapper">
	                    <c:forEach items="${storeInfo.pictureArray }" var="img">
                            <div class="swiper-slide">
                                <img class="lazy" name="lazyImage" src="<%=picPath %>img_lazy_loding.png" data-original="<%=picPath %>${img}"/>
                            </div>
                       </c:forEach>
	                </div>
	            </div>
	        </div>
	        <div class="store-md">
	           <span class="fl">${storeInfo.storeName }</span>
	           <c:if test="${storeSize > 1 }">
	           		<a style="color: #fff" href="<%=basePath %>memberCenter/view/storeList?url=/memberCenter/view/storeInfo/${session_key_store_id}/1?selectStoreId=_storeId_">
			           <span class="fr">查看 ${storeSize } 家分店>></span>
	           		</a>
	           </c:if> 
	        </div>
	    </div>
	
	    <div class="address-tel">
	        <ul>
	            <li class="icon-address" style="padding-left: 0px;">
	                <i class="iconfont icon-icon"></i>
	            </li>
	            <li class="address">
	                <div id="storeAddress" class="address-specific" onclick="toNavigation()">${storeInfo.storeAddress }</div>
	                <input id="storeCity" class="hide" value="${storeInfo.storeCity }" />
	                <input id="storeLat" class="hide" value="${storeInfo.latitude }" />
	                <input id="storeLng" class="hide" value="${storeInfo.longitude }" />
	            </li>
	
	            <li class="tel s-modal-control" data-target="#shop-tel">
	                <i class="iconfont icon-dianhua9"></i>
	            </li>
	        </ul>
	    </div>
	
	    <div class="store-list-wrap mt2">
	        <ul class="store-tab">
	            <li class="current" data-target="s-tab1">门店介绍</li>
	            <li data-target="s-tab2">特色服务</li>
	            <li data-target="s-tab3">名师介绍</li>
	        </ul>
	        
	        <div class="store-wrap srore-bgc clearfix" id="s-tab1">
	        ${storeInfo.storeDesc }
	        </div>
	        
	        <div class="store-wrap srore-bgc clearfix  hide" id="s-tab2">
		        <c:forEach items="${specialServices }" var="specialService">
					<a href="<%=basePath %>memberCenter/view/store/special?sId=${specialService.sId }">
						<div class="content_detial clearfix">
							<div class="detail_left">
								<img style="width: 175.5px;height: 175.5px;" src="<%=picPath %>${specialService.sImage }">
							</div>
							<div class="detail_text">
								<span>服务名称</span><em style="color:black">${specialService.sName }</em></br>
								<span>适用项目</span><em style="color:black">${specialService.projectName }</em></br>
								<span>贡献员工</span><em style="color:black">${specialService.employeeCode }号 ,  ${specialService.employeeName }</em>
							</div>
						</div>
					</a>
				</c:forEach>
	        	<%-- ${storeInfo.characteristic } --%>
	        </div>
	        
	        <div class="store-wrap hide" id="s-tab3">
	            <ul class="famous-js">
	            	<c:forEach items="${employeeList }" var="employee">
                        <li>
                           <a href="<%=basePath %>memberCenter/view/employeeInfo?employeeId=${employee.employeeId}">
		                        <img class="lazy" name="employeeLazyImage" src="<%=picPath %>img_lazy_loding.png" data-original="<%=picPath%>${employee.headImage}"/>
		                        <div class="famous-wz">
		                            <p>${employee.name } (${employee.levelName })</p>
		                            <span>已服务<span class="color-b">${employee.serviceCount }</span>人</span>
		                        </div>
		                   </a>
                       </li>
                    </c:forEach>
	            </ul>
	        </div>
	    </div>
	
	</div>
	<ul class="bottom_fix clearfix">
 		<a href="<%=basePath %>memberCenter/view/home/${session_key_store_id}/1">
	      <li><img src="<%=basePath %>images/mobile/member/botton_1_1.png">
		      <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">我的</p>
		  </li>
	    </a>
	    <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_id}/1">
	    	<li><img src="<%=basePath %>images/mobile/member/botton_2.png">
		       <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">预约</p>
		    </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_id}/1">
		  <li><img src="<%=basePath %>images/mobile/member/botton_3.png">
		    <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">商城</p>
		  </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_id}/1">
	     <li><img src="<%=basePath %>images/mobile/member/botton_4_4.png">
		     <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">门店</p>
		  </li>
	    </a>
 	</ul>
 	
	<%-- <div class="footer">
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
	    <li >
	      <a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_id}/1">
	        <span class="iconfont icon-jifen"></span>
	        <span>商城</span>
	      </a>
	    </li>
	    <li  class="active">
	      <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_id}/1">
	        <span class="iconfont icon-dianpu2"></span>
	        <span>门店</span>
	      </a>
	    </li>
	  </ul>
	</div> --%>
</div>	

<!--店铺电话-->
<div class="s-modal hide s-modal-miss" id="shop-tel">
    <div class="s-modal-wrap">
        <div class="shop-tel">
        	<c:forEach items="${telArray }" var="tel">
        		<a href="tel:${tel }" class="modal-btn shop-modal-tel blue-word">${tel }</a>
        	</c:forEach>
            <a href="javascript:void(0);" class="modal-btn shop-modal-cancel">取消</a>
        </div>
    </div>
</div>

<!--会员点击商户地址选取交通方式模态框-->
<div class="modal hide" id="address-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content address-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                	请选择你需要的交通方式
                </h4>
            </div>
            <div class="modal-body">
                <ul class="address-tip clearfix">
                    <li>步行</li>
                    <li>公交</li>
                    <li>驾车</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%@include file="../memberBase.jsp" %>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/jquery.lazyload.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zxYZYzCtCT1lhiVOuxQeieOf"></script>
<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>
<script>
	$(document).ready(function (){
		//事先定位
		function showPositionBefore(position) {
			globalGPSLongitude = position.coords.longitude;
			globalGPSLatitude  = position.coords.latitude;
		}
		if (navigator.geolocation) {
	    	navigator.geolocation.getCurrentPosition(showPositionBefore);
	    }
		
		var width = $(window).width();
		$(".swiper-container").height(width);
		
		var mySwiper = new Swiper ('.swiper-container', {
	        autoplay: 2000,
	        direction : 'horizontal',
	        pagination : '.photo-num',
	    });
       
       $("img[name='lazyImage']").each(function(){
    	   var dw = width * 2;
           $(this).attr("src", $(this).attr("data-original") + "?imageView2/1/w/" + dw + "/h/" + dw)
       });
       
       var employeeImgW = $(".famous-js li").width();
       $("img[name='employeeLazyImage']").each(function(){
    	   var src = $(this).attr("data-original") + "?imageView2/1/w/" + employeeImgW + "/h/" + employeeImgW;
    	   console.log("src : " + src);
           $(this).attr("src", src)
       });

		$("img.lazy").lazyload({ 
		  threshold : 100,
		  skip_invisible : false,
		  effect: "fadeIn"
		}); 
		
		$(".store-tab li").on("click",function(){
            $(this).addClass("current").siblings().removeClass("current");
            $(".store-wrap").addClass("hide");
            $("#"+$(this).attr("data-target")).removeClass("hide")
        })
    });
	
	var globalGPSLongitude;
	var globalGPSLatitude;
	var globalBaiDuLongitude;
	var globalBaiDuLatitude;
	var storeCity = jQuery("#storeCity").val();
	var storeAddress = jQuery("#storeAddress").text();
	var storeLat = jQuery("#storeLat").val();
	var storeLng = jQuery("#storeLng").val();
	//会员查看商户导航选取交通方式
	function toNavigation() {
		if (storeLat == null || storeLng == null) {
			dialog("该商户暂未设置坐标参数");
			return;
		}
		if (navigator.geolocation) {
	    	navigator.geolocation.getCurrentPosition(showPosition);
	    } else{
	    	dialog("设备不支持定位!");
	    	return;
	    }
		if (globalGPSLongitude == null || globalGPSLatitude == null) {
			dialog("GPS定位失败，请重试");
			return;
		}
		console.log("GPS X : " + globalGPSLatitude);
		console.log("GPS Y : " + globalGPSLongitude);
	    BMap.Convertor.translate(new BMap.Point(globalGPSLongitude, globalGPSLatitude), 0, initMap); //转换坐标 */
	}
	
	//获得html5获取的GPS坐标
	function showPosition(position) {
		globalGPSLongitude = position.coords.longitude;
		globalGPSLatitude  = position.coords.latitude;
	}
	
	function initMap(point){
		globalBaiDuLatitude = point.lat;
	    globalBaiDuLongitude = point.lng;
	    console.log("BaiDu X : " + globalBaiDuLatitude);
	    console.log("BaiDu Y : " + globalBaiDuLongitude);
		var now = new Date().getTime();
		/* console.log("http://api.map.baidu.com/direction?origin=latlng:" + globalBaiDuLatitude + "," + globalBaiDuLongitude + "|name:我的位置"
				+ "&destination=latlng:" + storeLat + "," + storeLng + "|name:" + storeAddress + "&mode=driving&region=" + storeCity + "&output=html&src=maywant"); */
		window.location.href = "http://api.map.baidu.com/direction?origin=latlng:" + globalBaiDuLatitude + "," + globalBaiDuLongitude + "|name:我的位置"
			+ "&destination=latlng:" + storeLat + "," + storeLng + "|name:" + storeAddress + "&mode=driving&region=" + storeCity + "&output=html&src=maywant";
	}
	
</script>
</body>
</html>