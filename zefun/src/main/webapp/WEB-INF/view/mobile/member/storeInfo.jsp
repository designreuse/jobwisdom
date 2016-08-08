<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>门店介绍</title>
<link rel="stylesheet" href="<%=basePath%>css/mobile/style.css" />
<link rel="stylesheet" href="<%=basePath%>css/mobile/shop.css" />
<%-- <link rel="stylesheet" href="<%=basePath%>css/mobile/member-0-0-1.min.css" /> --%>
<script type="text/javascript" src="<%=basePath%>js/mobile/TouchSlide.1.1.js"></script>
<style>
.con {
	font-size: 14px
}

.slideBox {
	position: relative;
	overflow: hidden;
	margin: 0px auto;
	max-width: 560px; /* 设置焦点图最大宽度 */
}

.slideBox .hd {
	position: absolute;
	height: 28px;
	line-height: 28px;
	bottom: 0;
	right: 0;
	z-index: 1;
}

.slideBox .hd li {
	display: inline-block;
	width: 5px;
	height: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	background: #333;
	text-indent: -9999px;
	overflow: hidden;
	margin: 0 6px;
}

.slideBox .hd li.on {
	background: #fff;
}

.slideBox .bd {
	position: relative;
	z-index: 0;
}

.slideBox .bd li {
	position: relative;
	text-align: center;
}

.slideBox .bd li img {
	background: url('<%=basePath%>images/mobile/member/loading.gif') center center no-repeat;
	vertical-align: top;
	width: 100%; /* 图片宽度100%，达到自适应效果 */
}

.slideBox .bd li a {
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
} /* 去掉链接触摸高亮 */
.slideBox .bd li .tit {
	display: block;
	width: 100%;
	position: absolute;
	bottom: 0;
	text-indent: 10px;
	height: 28px;
	line-height: 28px;
	background: url('<%=basePath%>images/mobile/member/focusBg.png') repeat-x;
	color: #fff;
	text-align: left;
}

.slideBox_address {
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 5rem;
	background: rgba(34, 53, 105, 0.8);
	line-height: 5rem;
	color: white
}

.slideBox_address img {
	width: 28px
}

.slideBox_address span {
	margin-left: 0.5rem;
	display: inline-block;
	padding-right: 1rem;
	border-right: 1px solid white;
	height: 2rem;
	line-height: 2rem
}

.slideBox_address em {
	display: inline-block;
	float: right;
	margin-right: 0.5rem
}

.more_store {
	position: absolute;
	display: inline-block;
	top: 3rem;
	color: white !important;
	height: 2.8rem;
	width: 7.6rem;
	border-radius: 0 12px 12px 0;
	text-align: center;
	background: rgba(34, 53, 105, 0.8);
	line-height: 2.8rem
}

.tab li {
	position: relative;
	float: left;
	width: 33.3%;
	line-height: 5rem;
	line-height: 5rem;
	text-align: center;
	color: white;
}

.tab li span {
	display: inline-block;
	width: 0;
	height: 0;
	border-left: 15px solid transparent;
	border-right: 15px solid transparent;
	border-bottom: 10px solid white;
	position: absolute;
	bottom: 0;
	left: 38%
}

.tab_content_div li {
	margin-bottom: 1rem;
	border: 1px solid #ccc;
	padding-bottom: 0.7rem;
	width: 48.2%;
	float: left;
	margin-left: 0.5%;
	margin-right: 0.5%
}

.tab_content {
	margin-top: 2px;
	width: 100%;
	overflow: hidden
}

.tab_content_div .tab_content_div_ul img {
	width: 100%
}

.tab_content_div_ul .tab_content_div_text {
	font-size: 12px
}

.tab_content_div_ul .tab_content_div_text>p {
	padding-left: 1rem;
	margin-top: 2px
}

.tab_content_div_ul .tab_content_div_text>p>span {
	display: inline-block;
	margin-left: 1.5rem;
	color: black
}

.tab_content_div_last li {
	border: 1px solid #ccc
}

.star_name {
	font-size: 12px;
	padding-left: 0.5rem;
	color: black
}

.star_name p {
	padding-top: 4px
}

.star_name p span {
	color: #b2b2b2
}

.star_name p span em {
	color: #ff8e08
}

.tab_content_div_last img {
	width: 100%
}
.hide{
	display: none;
}
</style>
</head>

<body>
	<div class="con">
		<div id="slideBox" class="slideBox">
			<div class="bd">
				<ul>
					 <c:forEach items="${storeInfo.pictureArray }" var="img">
					 	<li>
							<a class="pic" href="#">
								<img src="<%=picPath %>${img}" />
							</a>
	
						</li>
                       </c:forEach>
				</ul>
			</div>
			<div class="hd" style="display: none">
				<ul></ul>
			</div>
			<div class="slideBox_address">
				<img src="<%=basePath%>images/mobile/member/localtion.png"><span>${storeInfo.storeAddress }</span>
				<em onclick="$('#shop-tel').removeClass('hide');"><img src="<%=basePath%>images/mobile/member/telphone.png"></em>
			</div>
			<c:if test="${storeSize > 1 }">
				<a class="more_store" href="<%=basePath %>memberCenter/view/storeList?url=/memberCenter/view/storeInfo/${session_key_store_account}/1?selectStoreId=_storeId_">更多门店》</a>
			</c:if>
		</div>

		<ul class="tab clearfix">
			<li style="background: #0771e8">
				门店介绍<span></span>
			</li>
			<li style="background: #50be0d">
				作品展示<span style="display: none"></span>
			</li>
			<li style="background: #ff8e08">
				名师推荐<span style="display: none"></span>
			</li>
		</ul>
		<div class="tab_content">
			<div class="tab_content_div">${storeInfo.storeDesc }</div>
			<div class="tab_content_div">
				<ul class="clearfix tab_content_div_ul">
					<c:forEach items="${specialServices }" var="specialService">
					<a href="<%=basePath %>memberCenter/view/store/special?sId=${specialService.sId }">
						<li>
							<p>
								<img src="<%=picPath %>${specialService.sImage }">
							</p>
							<div class="tab_content_div_text">
								<p>
									服务名称<span>${specialService.sName }</span>
								</p>
								<p>
									适用项目<span>${specialService.projectName }</span>
								</p>
								<p>
									贡献员工<span>${specialService.employeeCode }号 ,  ${specialService.employeeName }</span>
								</p>
							</div>
						</li>
					</a>
					</c:forEach>
				</ul>
			</div>
			<div class="tab_content_div">
				<ul class="clearfix tab_content_div_last">
					<c:forEach items="${employeeList }" var="employee">
					<a href="<%=basePath %>memberCenter/view/dateAppointment?employeeId=${employee.employeeId}">
						<li>
							<p>
								<img src="<%=picPath%>${employee.headImage}">
							</p>
							<div class="star_name">
								<p>${employee.name } (${employee.levelName })</p>
								<p>
									<span>已服务<em>${employee.serviceCount }</em>人
									</span>
								</p>
							</div>
						</li>
					</a>
                    </c:forEach>
				</ul>
			</div>
		</div>
	</div>
	<!--店铺电话-->
	<%-- <div class="s-modal hide s-modal-miss" id="shop-tel">
	    <div class="s-modal-wrap">
	        <div class="shop-tel">
	        	<c:forEach items="${telArray }" var="tel">
	        		<a href="tel:${tel }" class="modal-btn shop-modal-tel blue-word">${tel }</a>
	        	</c:forEach>
	            <a href="javascript:void(0);" class="modal-btn shop-modal-cancel">取消</a>
	        </div>
	    </div>
	</div> --%>
	<div style="height: 5rem"></div>
	<ul class="bottom_fix clearfix">
 		<a href="<%=basePath %>memberCenter/view/home/${session_key_store_account}/1">
	      <li><img src="<%=basePath %>images/mobile/member/botton_1.png">
		      <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">我的</p>
		  </li>
	    </a>
	    <a href="<%=basePath %>memberCenter/view/orderAppointment/${session_key_store_account}/1">
	    	<li><img src="<%=basePath %>images/mobile/member/botton_2.png">
		       <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">预约</p>
		    </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/shopCenter/${session_key_store_account}/1">
		  <li><img src="<%=basePath %>images/mobile/member/botton_3.png">
		    <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">商城</p>
		  </li>
	    </a>
	    <a href="<%=basePath%>memberCenter/view/storeInfo/${session_key_store_account}/1">
	     <li><img src="<%=basePath %>images/mobile/member/botton_4_4.png">
		     <p style="top:-2rem;font-size: 0.65em;font-family: '微软雅黑';color:#555">门店</p>
		  </li>
	    </a>
 	</ul>
	<%@include file="../memberBase.jsp"%>
	<script type="text/javascript" src="<%=swiperJsPath%>"></script>
	<script type="text/javascript">
		TouchSlide({
			slideCell : "#slideBox",
			titCell : ".hd ul", //开启自动分页 autoPage:true ，此时设置 titCell 为导航元素包裹层
			mainCell : ".bd ul",
			effect : "leftLoop",
			autoPage : true,//自动分页
			autoPlay : true
		//自动播放
		});
		$(function() {
			$('.tab_content .tab_content_div:gt(0)').hide();
			$('.tab li').on(
					'touchstart',
					function() {
						$(this).find('span').css('display', 'block');
						$(this).siblings().find('span').css('display', 'none')
						$('.tab_content_div').eq($(this).index()).show().siblings().hide()
					})
		})
	</script>
</body>
</html>