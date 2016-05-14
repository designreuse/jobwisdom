<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>预约服务</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=memberCssPath%>"/>
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <style type="text/css">
    .bottom_fix{width:100%;background:white;position:fixed;bottom:0;height:6rem;z-index:2}
	.bottom_fix li{float:left;width:25%;text-align:center;line-height:3rem;margin-top:0.5rem}
	.bottom_fix img{width:3rem}
    </style>
  </head>
  
<body>

<div class="content wrap">
    <c:if test="${storeSize > 0 }">
	    <div class="head">
		    <div class="tmain">
		        <i>
		            <img src="<%=picPath %>${selectStore.storeLogo}?imageView2/1/w/63/h/63"/>
		        </i>
		        <div class="dianmin">
		            <span class="h-title">${selectStore.storeName }</span>
		        </div>
		        <div class="liansuo fr s-modal-control" data-target="#liansuo-modal">
		            <span>查看${storeSize }家分店</span>
		        </div>
		    </div>
		</div>
	</c:if>
    <div class="bd-white bodyoh">
		<c:if test="${typeNumber >= 2 }">
	       <div class="swiper-container-daohang tab">
	            <div class="swiper-wrapper">
	                <c:forEach items="${serviceList }" var="service" varStatus="status">
	                    <div class="swiper-slide score-shop-li" onclick="chooseDept('${service.deptId }')">
	                        <img src="<%=basePath%>/images/mobile/member/active-new.png" class="hide"/>
	                        <div class="tab-word">
	                            <div class="medium-font line-height80">${service.deptName }</div>
	                        </div>
	                    </div>
	                </c:forEach>
	            </div>
	        </div>
	    </c:if>
		<c:forEach items="${serviceList }" var="service" varStatus="status">
            <div class="d-select-project hide" deptService="${service.deptId }">
	            <div class="d-content">
	                <div class="project-type">
	                    <ul class="project-type-ul">
	                       <c:forEach items="${service.projectCategoryList }" var="projectCategory">
	                            <li class="type-item j-tab" categoryTitle="${projectCategory.categoryId }" >
	                                <span>${projectCategory.categoryName }</span>
	                            </li>
	                       </c:forEach>
	                       <li class="type-item j-tab" >
                               <span></span>
                           </li>
	                    </ul>
	                </div>
	                
	                <div class="project-list j-tab">
	                   <c:forEach items="${service.projectCategoryList }" var="projectCategory">
	                       <div class="project-list-part" categoryContent="${projectCategory.categoryId }">
	                            <div class="list-part-title">
	                                <span>${projectCategory.categoryName }</span>
	                            </div>
	                            <div class="list-item">
	                                <ul>
	                                   <c:forEach items="${projectCategory.projectList }" var="project">
	                                       <c:if test="${project.isAppointment == 1 }">
		                                       <a href="<%=basePath%>memberCenter/view/projectDetail?storeId=${session_key_store_id}&projectId=${project.projectId}">
			                                       <li>
			                                            <img src="<%=picPath%>${project.projectImage}?imageView2/1/w/220/h/220" width="109" height="109" alt=""/>
			                                            <div class="item-desc">
			                                                <div class="name">${project.projectName}</div>
			                                                <div class="d-price font-666">已服务：${project.salesCount}</div>
			                                                <div class="origin-price"><span class="text-through">价格: ￥${project.projectPrice}</span></div>
			                                            </div>
			            
			                                            <div class="shop-num-control">
			                                                <span class="num-emphasie">￥${project.projectPrice - project.appointmentPrice}</span>
			                                            </div>
			                                        </li>
		                                        </a>
	                                        </c:if>
	                                   </c:forEach>
	                                </ul>
	                            </div>
	                        </div>
	                   </c:forEach>
	                   <div class="h15r"></div>
	                </div>
	            </div>
	        </div>               
        </c:forEach>
	</div>
	
	<ul class="bottom_fix clearfix">
 		<a href="<%=basePath %>memberCenter/view/home/${session_key_store_id}/1">
	      <li><img src="<%=basePath %>images/mobile/member/botton_1_1.png">
		      <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">我的</p>
		  </li>
	    </a>
	    <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_id}/1">
	    	<li><img src="<%=basePath %>images/mobile/member/botton_2_2.png">
		       <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">预约</p>
		    </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_id}/1">
		  <li><img src="<%=basePath %>images/mobile/member/botton_3.png">
		    <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">商城</p>
		  </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_id}/1">
	     <li><img src="<%=basePath %>images/mobile/member/botton_4.png">
		     <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">门店</p>
		  </li>
	    </a>
 	</ul>
 
	<%-- <div>
      <ul class="footer">
        <li>
          <a href="<%=basePath %>memberCenter/view/home/${session_key_store_id}/1">
            <span class="iconfont icon-wode"></span>
            <span class="word">我的</span>
          </a>
        </li>
        <li class="active">
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
        <li >
          <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_id}/1">
            <span class="iconfont icon-dianpu2"></span>
            <span>门店</span>
          </a>
        </li>
      </ul>
    </div> --%>
    
    <c:if test="${!empty storeList }">
        <div class="s-modal share-modal s-modal-miss hide" id="liansuo-modal">
	        <div class="s-modal-wrap  liansuo-modal-content">
	            <div class="liansuo-drowndown">
	                <div class="ls-logo">
	                       <img src="<%=picPath %>${mainStore.storeLogo}?imageView2/1/w/40/h/45"/>
	                       <span>${mainStore.storeName }</span>
	                </div>
	                <ul>
	                   <c:forEach items="${storeList }" var="store">
	                       <a href="<%=basePath%>memberCenter/view/orderAppointment/${mainStore.storeId }/1?selectStoreId=${store.storeId}">
		                       <li>
		                            <img src="<%=picPath %>${store.storeLogo}?imageView2/1/w/81/h/81"/>
		                            <div class="dm dianmin fl">
		                                <span class="ls-dianmin">${store.storeName }</span><br/>
		                                <span>${store.storeAddress }</span><br/>
		                                <span>电话：${store.storeTel }</span>
		                            </div>
		                        </li>
	                        </a>
	                   </c:forEach>
	                </ul>
	            </div>
	        </div>
	    </div>
    </c:if>
</div>
<%@include file="../memberBase.jsp" %>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript">
    $(function(){
    	$(".swiper-slide:first").addClass("active").find("img").removeClass("hide");
    	$(".d-select-project:first").removeClass("hide");
    	$(".project-type-ul").find("li:first").addClass("active");
    	
        var swiper = new Swiper('.swiper-container-daohang', {
            pagination: '.swiper-pagination',
            slidesPerView: '${typeNumber}',
            paginationClickable: true,
            spaceBetween: 0,
            freeMode: true
        });

        $('.swiper-slide').click(function(){
            $(".swiper-slide").removeClass("active");
            $(".swiper-slide img").addClass("hide");
            $(this).addClass("active");
            $(this).find("img").removeClass("hide");
        });

        var contentHeight = function(){
            var height = document.documentElement.clientHeight;
            var projectUlHeight = height;
            $(".project-list").css("height", projectUlHeight);
            $(".project-type-ul").css("height", projectUlHeight);
        }

        contentHeight();
        
        var offset = $(".d-content").offset().top;
        var top = offset;
        $(".type-item").click(function(){
            $(this).addClass("active").siblings().removeClass("active");
            var sub = $("[categoryContent='" + $(this).attr("categorytitle") + "']");
            var container = sub.parent();
            offset = sub.offset().top - container.offset().top + container.scrollTop();
            container.scrollTop(offset);
        });
    });
    
    
    function chooseDept(deptId){
        $(".d-select-project").addClass("hide");
        $("[deptService='" + deptId + "']").removeClass("hide");
    }
</script>
</body>
</html>