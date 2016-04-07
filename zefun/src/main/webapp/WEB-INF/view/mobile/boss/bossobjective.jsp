<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta name="x5-orientation" content="portrait">
    <meta content="telephone=no" name="format-detection" />
    <meta name="msapplication-tap-highlight" content="no">
    <title>业绩报表</title>
    
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/boss-newer.css"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/chart-component.css">
    <link rel="stylesheet" href="<%=basePath%>css/mobile/business-report.css">
    <style>
    .chart-main {
    	margin-top: 2rem;
    }
    .chart-main:first-child {
    	margin-top: 0;
    }
    </style>
</head>
<body>
<div class="content">
<div class="business-report chart-bg">

    <div class="b-slider">
        <div class="swiper-container">
            <!-- Additional required wrapper -->
            <div class="swiper-wrapper">
                <!-- Slides -->
                <div class="swiper-slide m-swiper-slide-active" onclick="changeHead(this, 0)">现金收入</div>
                <div class="swiper-slide" onclick="changeHead(this, 1)">划卡消费</div>
                <div class="swiper-slide" onclick="changeHead(this, 2)">劳动业绩</div>
                <div class="swiper-slide" onclick="changeHead(this, 3)">套餐销售</div>
                <div class="swiper-slide" onclick="changeHead(this, 4)">外卖销售</div>
            </div>
            
            <div class="swiper-pagination swiper-pagination-clickable">
                <span class="swiper-pagination-bullet swiper-pagination-bullet-active"></span>
                <span class="swiper-pagination-bullet"></span>
                <span class="swiper-pagination-bullet"></span>
                <span class="swiper-pagination-bullet"></span>
                <span class="swiper-pagination-bullet"></span>
            </div>
        </div>
    </div>
    <!--b-slider-->

    <div class="tab" name = "0">
    
       <div class="part-chart">
	       <div class="btn-bumen s-modal-control" onclick="openDeptModal(this)">全店 <span class="ml1 iconfont icon-zhankai"></span></div>
	
	
	        <div class="year-month-day">
	            <ul>
	                <li onclick="chooseDate(this, 3)">年</li>
	                <li onclick="chooseDate(this, 2)">月</li>
	                <li class="active" onclick="chooseDate(this, 1)">日</li>
	            </ul>
	        </div>
	        <!--year-month-day-->
	
	        <div class="chart-wrap">
	            <div name="container" style="width:80%; height: 300px;margin-left: 10%;"></div>
	        </div>
	        <!--chart-wrap-->
            <!-- <div class="pie-legent pl3 pr3">
                <div class="">
                    <div class="legent-cell w50p"><div class="legent-tag lengent-one"></div>商品业绩</div>
                    <div class="legent-cell w50p"><div class="legent-tag lengent-two"></div>套餐业绩</div>
                </div>
                <div>
                    <div class="legent-cell w50p"><div class="legent-tag lengent-three"></div>项目业绩</div>
                    <div class="legent-cell w50p"><div class="legent-tag lengent-four"></div>卡项业绩</div>
                </div>
            </div> -->
	    </div>
	    <!--part-chart-->
	    
	    <div class="part-chart">
            <div class="summary-item">
                <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
                <div class="part-tag">
                                        挂账记录
                </div>
                <div>
                    <ul>
                        <li>
                            <div class="gz-num" id = "newAddDebtAmount"></div>
                            <div class="item-name" name = "newAddDebtDIV">本日累计挂账金额</div>
                        </li>
                        <li>
                            <div class="sum-gz-num">${residueDebtAmount}</div>
                            <div class="item-name">应收挂帐总额</div>
                        </li>
                    </ul>
                </div>
            </div>
            <!--summary-item-->
        </div>
        <!--part-chart-->
	    
	    <div class="part-chart" id = "columnarDIV">
	        <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
	        <div class="part-tag">
	                                 现金支付方式
	        </div>
	        
	        <div class="sum-position">
                <span class="chart-sum">实收总金额:
                    <span class="num" name = "cashTypeTatail"></span>
                </span>
            </div>
	        
	        <div class="chart-wrap">
	            <div id="container1" style="width:100%;height:400px;"></div>
	        </div>
	        <!--chart-wrap-->
	    </div>
	    <!--part-chart-->
	    
	    <div class="part-chart">
	        <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
	        <div class="part-tag" name = "trendDIV">
	            7天趋势图
	        </div>
	        <div class="day" name = "trendDay">7天</div>
	        <div class="chart-wrap">
	            <div name="containerCash" style="width:100%;height:400px;"></div>
	        </div>
	        <!--chart-wrap-->
	    </div>
	    <!--part-chart-->
	    
    </div>

    <!--卡金业绩-->
    <div class="tab hide" name = "1">
        <div class="part-chart">
            <div class="btn-bumen s-modal-control" onclick="openDeptModal(this)">全店 <span class="ml1 iconfont icon-zhankai"></span></div>
            <div class="year-month-day">
                <ul>
                    <li onclick="chooseDate(this, 3)">年</li>
	                <li onclick="chooseDate(this, 2)">月</li>
	                <li class="active" onclick="chooseDate(this, 1)">日</li>
                </ul>
            </div>
            <!--year-month-day-->

            <div class="chart-wrap">
                <div name="cardContainer" style="width:80%;height:300px; margin-left: 10%;"></div>
            </div>
            
        </div>
        <!--part-chart-->


        <div class="part-chart pt2">
            <div class="summary-item">
                <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
                <div class="part-tag">
                                                          卡金变动汇总
                </div>
                <div>
                    <ul class="card-hz">
                        <li class="bd-r bd-b">
                            <div class="color1 f18">${balanceMap.totalAmount}</div>
                            <div class="item-name f12 mt1">累计储值总额</div>
                        </li>
                        <li class="bd-b bd-l">
                            <div class="color2 f18">${balanceMap.totalPresentAmount}</div>
                            <div class="item-name f12 mt1">累计赠送总额</div>
                        </li>
                        <li class="bd-r bd-t">
                            <div class="color3 f18 mt1">${balanceMap.balanceAmount}</div>
                            <div class="item-name f12 mt1">当前卡金余额</div>
                        </li>
                        <li class="bd-l bd-t">
                            <div class="color4 f18 mt1">${balanceMap.totalConsumeAmount}</div>
                            <div class="item-name f12 mt1">已使用卡金</div>
                        </li>
                    </ul>
                </div>
            </div>
            <!--summary-item-->
        </div>
        <!--part-chart-->

        <div class="part-chart pt2">
            <div class="summary-item">
                <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
                <div class="part-tag">
                                                           礼金状态汇总
                </div>
                <div>
                    <ul class="card-hz">
                        <li class="bd-r bd-b">
                            <div class="color1 f18">${balanceMap.totalGiftmoneyAmount}</div>
                            <div class="item-name f12 mt1">累计礼金赠送</div>
                        </li>
                        <li class="bd-b bd-l">
                            <div class="color2 f18">${balanceMap.useMoney}</div>
                            <div class="item-name f12 mt1">累计礼金使用</div>
                        </li>
                        <li class="bd-r bd-t">
                            <div class="color3 f18 mt1">${balanceMap.balanceGiftmoneyAmount}</div>
                            <div class="item-name f12 mt1">当前礼金余额</div>
                        </li>
                        <li class="bd-l bd-t">
                            <div class="color4 f18 mt1">${balanceMap.pastdataMoney}</div>
                            <div class="item-name f12 mt1">累计过期礼金</div>
                        </li>
                    </ul>
                </div>
            </div>
            <!--summary-item-->
        </div>
        <!--part-chart-->


        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
            <div class="part-tag" name = "trendDIV">7天趋势图</div>
	        <div class="day" name = "trendDay">7天</div>
            <div class="chart-wrap">
                <div name="containerCard" style="width:100%;height:400px;"></div>
            </div>
        </div>
    </div>
    
    <div class="tab hide" name = "2">
    
       <div class="part-chart">
	        <div class="btn-bumen s-modal-control" onclick="openDeptModal(this)">全店 <span class="ml1 iconfont icon-zhankai"></span></div>
	        <div class="year-month-day">
	            <ul>
	                <li onclick="chooseDate(this, 3)">年</li>
	                <li onclick="chooseDate(this, 2)">月</li>
	                <li class="active" onclick="chooseDate(this, 1)">日</li>
	            </ul>
	        </div>
	        <!--year-month-day-->
	
	        <div class="chart-wrap">
	            <div name="crosswiseProject" style="width:100%;height:400px"></div>
	        </div>
	        <!--chart-wrap-->
	    </div>
	    <!--part-chart-->
	
	    <div class="part-chart">
	        <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
	        
	        <div class="part-tag">
                                         劳动项目分布
            </div>
            <div class="sum-position">
                <span class="chart-sum">项目总金额:
                    <span class="num" id = projectPrice>556,568</span>
                </span>
            </div>

            <div class="chart-wrap">
                <div class="left-chart" name="container-left" style="width:50%;height:300px;"></div>
                <div class="right-chart" name="container-right" style="width:50%;height:300px;"></div>
            </div>
	        <!--chart-wrap-->
	    </div>
	    <!--part-chart-->
	
	    <div class="part-chart">
	        <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
	        <div class="part-tag" name = "trendDIV">7天趋势图</div>
	        <div class="day" name = "trendDay">7天</div>
	        <div class="chart-wrap">
	            <div name="containerProject" style="width:100%;height:400px;"></div>
	        </div>
	        <!--chart-wrap-->
	    </div>
	    <!--part-chart-->
    
    </div>
    
    
    <div class="tab hide" name = "3">
        <div class="part-chart">
            <div class="btn-bumen s-modal-control" onclick="openDeptModal(this)">全店 <span class="ml1 iconfont icon-zhankai"></span></div>
            <div class="year-month-day">
                <ul>
                    <li onclick="chooseDate(this, 3)">年</li>
	                <li onclick="chooseDate(this, 2)">月</li>
	                <li class="active" onclick="chooseDate(this, 1)">日</li>
                </ul>
            </div>
            <!--year-month-day-->
            <div class="chart-wrap mt5">
                <div id="taocan-chart" style="width:100%;height:320px"></div>
            </div>
            <!--chart-wrap-->
        </div>

        <!--数量统计-->
        <div class="part-chart pt2">
            <div class="summary-item">
                <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
                <div class="part-tag">
                                                            套餐购买统计
                </div>
                <div>
                    <ul>
                        <li>
                            <div><div class="gz-num di" name = "seriesComboTatailTime"></div><span class="f30">/人</span></div>
                            <div class="item-name">累计新增购买疗程套餐</div>
                        </li>
                        <li>
                        	<div><div class="sum-gz-num di" name = "timeComboTatailTime"></div><span class="f30">/人</span></div>
                            <div class="item-name">累计新增购买年季月套餐</div>
                        </li>
                    </ul>
                </div>
            </div>
            <!--summary-item-->
        </div>
        <!--part-chart-->

        <!--套餐详情-->
        <div class="part-chart">
            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">

            <div class="part-tag">
                                            套餐余量分析
            </div>

            <div class="chart-wrap">
                <div class="t-tab">
                    <ul class="tab-title">
                        <li class="current" onclick="chooseComboType(this, 1)">疗程套餐</li>
                        <li onclick="chooseComboType(this, 2)">月季年套餐</li>
                    </ul>
                    <!-- <div class="part-search">
                        <input type="text" class="btn-search" placeholder="搜索套餐" id="search"/>
                        <span class="iconfont icon-sousuo"></span>
                    </div> -->
                   <!--  <div class="part-wrap">
                         <ul>
                             <li><span class="t-higher">a</span>善款稍等</li>
                             <li><span class="t-higher">营养套餐</span>善款稍等</li>
                             <li><span class="t-higher">营养套餐</span>善款稍等</li>
                             <li><span class="t-higher">营养套餐</span>善款稍等</li>
                         </ul>
                     </div> -->
                    <div class="tc-labour" name="seriesCombo">
                          
                      </div>
                      
                    <div class="tc-labour hide" name="timeCombo">

                      </div>
                </div>
            </div>
            <!--chart-wrap-->
        </div>
    </div>
    
    <!--外卖-->
    <div name = "4" class="tab hide">
	        <div class="part-chart">
	            <div class="btn-bumen s-modal-control" onclick="openDeptModal(this)">全店 <span class="ml1 iconfont icon-zhankai"></span></div>
	            <div class="year-month-day">
	                <ul>
	                    <li onclick="chooseDate(this, 3)">年</li>
		                <li onclick="chooseDate(this, 2)">月</li>
		                <li class="active" onclick="chooseDate(this, 1)">日</li>
	                </ul>
	            </div>
	            <!--year-month-day-->
	
	            <div class="chart-wrap">
	                <div class="mt2" name="crosswiseGoods" style="width:100%;height:320px"></div>
	            </div>
	            <!--chart-wrap-->
	        </div>
	        <!--part-chart-->
	        <!--商品消费分析-->
	        <div class="part-chart">
	            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
	            <div class="part-tag">
	                             商品销量分析
	            </div>
	            <div class="chart-wrap ovh mt-3">
	                <div>
	                    <div name = "divNameGoods" style="width:100%;height:400px"></div>
	                    <!-- <div class="bili">商品销量占比</div> -->
	                </div>
	            </div>
	            <!--chart-wrap-->
	        </div>
	        <!--part-chart-->
	        
	        <div class="part-chart">
	            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
	            <div class="part-tag">
					单品销售排行
		        </div>
	
	            <div class="chart-wrap">
		            <table>
	                <thead>
	                <tr>
	                    <th>排序</th>
	                    <th>项目名称</th>
	                    <th class="active" onclick="chooseSortType(this, 1)">销售额</th>
	                    <th onclick="chooseSortType(this, 2)">销量</th>
	                </tr>
	                </thead>
	                <tbody id = "goodsRankingDIV">
		                
	                </tbody>
	            </table>
	        

	            </div>
	            <!--chart-wrap-->
	        </div>
	        <!--part-chart-->
	        
	       
	        
	
	        <div class="part-chart">
	            <img class="left-tag-line" src="<%=basePath%>images/mobile/boss-newer/tab-line.png" alt="">
	            <div class="part-tag" name = "trendDIV">7天趋势图</div>
	            <div class="day" name = "trendDay">7天</div>
	            <div class="chart-wrap">
	                <div class="trend-chart4" name = "containerGoods" style="width:100%;height:320px;"></div>
	            </div>
	        </div>
	    </div>
	    <!--tab end-->
	
	</div>
    
</div>

<div class="actionsheet hide s-modal" id="actionsheet">

    <div class="m-actionsheet-toggle">
        <div class="m-actionsheet-menu">
            <ul>
                <li class="actionsheet-cell" onclick="chooseDept(-1, '全店')">全店</li>
                <c:forEach items="${deptInfoList}"  var="deptInfo">
                   <li class="actionsheet-cell" onclick="chooseDept(${deptInfo.deptId}, '${deptInfo.deptName}')">${deptInfo.deptName}</li>
                </c:forEach>
            </ul>
        </div>
        <div class="m-actionsheet-action">
            <ul>
                <li class="s-modal-miss actionsheet-cell">取消</li>
            </ul>
        </div>
    </div>
</div>

<div class="footer">
    <ul>
    	<li class="active">
            <a href="<%=basePath %>boss/view/bossObjective/${session_key_store_id}/2">
                <span class="iconfont icon-yewubiaobiao"></span>
                <span>业绩报表</span>
            </a>
        </li>
        <li>
            <a href="<%=basePath %>boss/view/business/${session_key_store_id}/2">
                <span class="iconfont icon-yingyefenxi"></span>
                <span>营业分析</span>
            </a>
        </li>
        <li>
            <a href="<%=basePath %>boss/view/coutomer/${session_key_store_id}/2">
                <span class="iconfont">&#xe845;</span>
                <span class="word">客情分析</span>
            </a>
        </li>
        <li>
            <a href="<%=basePath %>boss/view/employeeCommissionHome/${session_key_store_id}/2">
                <span class="iconfont icon-yuangong"></span>
                <span>员工表现</span>
            </a>
        </li>
    </ul>
</div>

<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/template.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/highcharts.src.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/base/big.js"> </script>
<script>

    var overallDateTypeArray = new Array(1, 1, 1, 1, 1);

    var overallDeptIdArray = new Array(-1, -1, -1, -1, -1);
    
    var pageType = 0;
    
    var swiper = new Swiper('.swiper-container', {
        slidesPerView: 3,
        paginationClickable: false,
        spaceBetween: 30
    });

	function changeHead(obj, type) {
		$(obj).siblings().removeClass("m-swiper-slide-active");
		$(obj).addClass("m-swiper-slide-active");
		
		$(".swiper-pagination-bullet").removeClass("swiper-pagination-bullet-active");
		jQuery($(".swiper-pagination-bullet")[type]).addClass("swiper-pagination-bullet-active");
		
		$(".tab").addClass("hide");
		
		pageType = type;
		
		$("div[name='"+type+"']").removeClass("hide");
		
		cashAmountPage();
		
	}
    
	function chooseDate(obj, type) {
    	$(obj).siblings().removeClass("active");
    	$(obj).addClass("active");
    	
    	overallDateTypeArray[pageType] = type;
    	cashAmountPage();
    	
    	if (pageType == 0 && type == 1) {
    		$("div[name='newAddDebtDIV']").text("本日累计挂账金额");
    	}
    	else if(pageType == 0 && type == 2){
    		$("div[name='newAddDebtDIV']").text("本月累计挂账金额");
    	}
    	else if(pageType == 0 && type == 3){
    		$("div[name='newAddDebtDIV']").text("本年累计挂账金额");
    	}
    	
    	var trendDIVName = "";
    	var trendDayName = "";
    	if (type == 1) {
    		var trendDIVName = "7天趋势图";
        	var trendDayName = "7天";
    	}
    	else if (type == 2) {
    		var trendDIVName = "30天趋势图";
        	var trendDayName = "30天";
    	}
    	else {
    		var trendDIVName = "12月趋势图";
        	var trendDayName = "12月";
    	}
    	$("div[name='"+pageType+"']").find("div[name='trendDIV']").text(trendDIVName);
		$("div[name='"+pageType+"']").find("div[name='trendDay']").text(trendDayName);
    }
	
	var nowClickObj = null;
	
	function openDeptModal(obj) {
		nowClickObj = obj;
		$("#actionsheet").removeClass("hide");
	}
	
	function chooseDept(deptId, deptName) {
    	$(nowClickObj).text(deptName);
    	$(nowClickObj).append("<span class='ml1 iconfont icon-zhankai'></span>");
    	$("#actionsheet").addClass("hide");
    	
    	overallDeptIdArray[pageType]  = deptId;
    	cashAmountPage();
    }
	
	function chooseComboType (obj, type) {
		$(obj).siblings().removeClass("current");
		$(obj).addClass("current");
		if (type == 1) {
			$("div[name='seriesCombo']").removeClass("hide");
			$("div[name='timeCombo']").addClass("hide");
		}
		else {
			$("div[name='seriesCombo']").addClass("hide");
			$("div[name='timeCombo']").removeClass("hide");
		}
	}
	
    $(function () {
    	cashAmountPage();
    });

    function piePage(pieType, cashAmountMap) {
    	
    	var cashAmountArray = new Array();
    	
    	var colorsArray = new Array(); 
    	
    	var divName = "";
    	
    	var textTitle = "";
    	
    	var showTitle = '{series.name}: <b>{point.y:.1f}元</b>';
    	
    	var yValue = 0;
    	
    	if (pieType == 1) {
    		divName = "container";
    		colorsArray = ['#ffc07b', '#3982ce', '#ff7e7d', '#b5e986'];
    		textTitle = '<div style="text-align: center"><span style="font-size: 20px;color: #b8c2cc">'+ cashAmountMap.tatailAmount + '</span><br><span style="font-size: 14px;color: #7f868d">现金总收入</span></div>';
    		
    		var projectObj = {"name" : "项目消费", "y" : parseFloat(cashAmountMap.projectCashAmount)};
    		cashAmountArray.push(projectObj);
    		var goodsObj = {"name" : "商品销售", "y" : parseFloat(cashAmountMap.goodsCashAmount)};
    		cashAmountArray.push(goodsObj);
    		var comboObj = {"name" : "套餐销售", "y" : parseFloat(cashAmountMap.comboCashAmount)};
    		cashAmountArray.push(comboObj);
    		var chargeObj = {"name" : "卡项销售", "y" : parseFloat(cashAmountMap.chargeCashAmount)};
    		cashAmountArray.push(chargeObj); 
    		yValue = -35;
    	}
    	else if (pieType == 2) {
    		divName = "container-left";
    		colorsArray = ['#e99645','#5b50cd'];
    		var tatailNum = new Big(parseFloat(cashAmountMap.bigAmount) + parseFloat(cashAmountMap.smallAmount));
    		textTitle = '<div style="text-align: center"><span style="font-size: 20px;color: #b8c2cc">'+ tatailNum.toFixed(2) + '</span><br><span style="font-size: 14px;color: #7f868d">大小项</span></div>';
    		var bigObj = {"name" : "大项业绩", "y" : parseFloat(cashAmountMap.bigAmount)};
    		cashAmountArray.push(bigObj);
    		var smallObj = {"name" : "小项业绩", "y" : parseFloat(cashAmountMap.smallAmount)};
    		cashAmountArray.push(smallObj);
    		yValue = -35;
    	}
    	else if (pieType == 3) {
    		divName = "container-right";
    		colorsArray = ['#62b1ff','#2068b3'];
    		textTitle = '<div style="text-align: center"><span style="font-size: 20px;color: #b8c2cc">'+ parseFloat(cashAmountMap.tatailAmount) + '</span><br><span style="font-size: 14px;color: #7f868d">总业绩</span></div>';
    		var cardObj = {"name" : "卡金/套餐业绩", "y" : parseFloat(cashAmountMap.cardAmount)};
    		cashAmountArray.push(cardObj);
    		var cashObj = {"name" : "现金劳动业绩", "y" : parseFloat(cashAmountMap.cashAmount)};
    		cashAmountArray.push(cashObj);
    		yValue = -35;
    	}
     	else if (pieType == 4) {
    		divName = "lc-taocan" + cashAmountMap.comboId;
    		colorsArray = ['#5d31dc','#b5e986'];
    		textTitle = '<div style="text-align: center"><span style="font-size: 20px;color: #b8c2cc">'+ cashAmountMap.tataiNum + '</span><br><span style="font-size: 14px;color: #7f868d">总销量</span></div>';
    		cashAmountArray = cashAmountMap.pageArray;
    		yValue = -35;
    	}
    	else if (pieType == 5) {
    		divName = "cardContainer";
    		colorsArray =  ['#ffc07b', '#3982ce', '#ff7e7d'];
    		textTitle = '<div style="text-align: center"><span style="font-size: 20px;color: #b8c2cc">'+ cashAmountMap.tatailAmount + '</span><br><span style="font-size: 14px;color: #7f868d">划卡总金额</span></div>';
    		var projectObj = {"name" : "项目消费", "y" : parseFloat(cashAmountMap.projectCashAmount)};
    		cashAmountArray.push(projectObj);
    		var goodsObj = {"name" : "商品购买", "y" : parseFloat(cashAmountMap.goodsCashAmount)};
    		cashAmountArray.push(goodsObj);
    		var comboObj = {"name" : "套餐购买", "y" : parseFloat(cashAmountMap.comboCashAmount)};
    		cashAmountArray.push(comboObj);
    		yValue = -35;
    	}
    	else if (pieType == 6) {
    		divName = "lc-taocan" + cashAmountMap.comboId;
    		colorsArray = ['#7bffb9','#31a792'];
    		textTitle = '<div style="text-align: center"><span style="font-size: 18px;color: #b8c2cc">'+ cashAmountMap.salesCount + '</span><br><span style="font-size: 12px;color: #7f868d">总次数</span></div>';
    		cashAmountArray = cashAmountMap.pageArray;
    		yValue = -40;
    	}
    	else if (pieType == 7) {
    		divName = "divNameGoods";
    		colorsArray = ['#5d31dc','#b5e986','#ff7e7d','#4fabe6','#ffc07b','#4019b2','#4019b2'];
    		textTitle = '<div style="text-align: center"><span style="font-size: 20px;color: #b8c2cc">'+ cashAmountMap.tailTime + '</span><br><span style="font-size: 14px;color: #7f868d">总次数</span></div>';
    		showTitle = '消费: <b>{point.y:.1f}次</b>';
    		cashAmountArray = cashAmountMap.goodsTimeList;
    		yValue = -35;
    	}
        $("div[name='"+divName+"']").highcharts({
            chart: {
            	backgroundColor: 'rgba(0,0,0,0)',
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false,
                reflow:false
            },
            legend: {
                backgroundColor: 'rgba(0,0,0,0)',
                itemDistance: 40,
                itemMarginBottom: 10,
                itemWidth: 115,
                itemStyle: {
                    color: '#a2abb5',
                }
            },
            credits: {
                enabled: false,
            },
            colors: colorsArray,
            title: {
            	align: 'center',
                useHTML: true,
                text: textTitle,
                verticalAlign: 'middle',
                y: yValue,
/*                 style: {
                    fontWeight: 'bold',
                    color: '#7f868d',
                    textShadow: '0px 1px 2px black'
                } */
            },
            tooltip: {
            	backgroundColor: {
                     linearGradient: [0, 0, 0, 60],
                     stops: [
                         [0, '#FFFFFF'],
                         [1, '#FFFFFF']
                     ]
                 },
                pointFormat: showTitle
            },
            plotOptions: {
            	pie: {
                    dataLabels: {
                        enabled: false,
                        distance: 0,
                        style: {
                            fontWeight: 'bold',
                            color: '#b8c2cc',
                            textShadow: '0px 1px 2px black'
                        }
                    },
                    showInLegend: true,
                    startAngle: 0,
                    endAngle: 360,
                    center: ['50%', '50%'],
                    borderWidth: 0
                }
            },
            series: [{
                type: 'pie',
                name: '金额',
                innerSize: '65%',
                data: cashAmountArray
            }]
        });
    }
    
    function columnarPage(tatalMap) {
        var tatalArray = new Array();
    	
    	tatalArray.push(tatalMap.cashAmount);
		tatalArray.push(tatalMap.unionpayAmount);
		tatalArray.push(tatalMap.wechatAmount);
		tatalArray.push(tatalMap.alipayAmount);
		tatalArray.push(tatalMap.groupAmount);
		
        $('#container1').highcharts({
            chart: {
                backgroundColor: 'rgba(0,0,0,0)',
                type: 'column'
            },
            colors: ['#c93d6f', '#5b50cd', '#35ca66', '#3195c9', '#7b4f0d', '#b3e685'],
            credits: {
                enabled: false,
            },
            title: {
                text: ''
            },
            //关闭图例
            legend: {
                enabled: false
            },
            xAxis: {
                categories: [
                    '现金',
                    '银联',
                    '微信',
                    '支付宝',
                    '团购'
                ],
                tickWidth: 0,
                lineColor: '#383943',
                labels: {
                    align: 'center',
                    x: -5,
                    style: {
                        color: '#a2abb5'
                    }
                },
            },
            yAxis: {
                title: {
                    text: ''
                },
                tickPixelInterval: 30,
                gridLineColor: '#383943',
                labels: {
                    style: {
                        color: '#a2abb5'
                    }
                }
            },
            tooltip: {
            	pointFormat: '金额: <b>{point.y:.1f}元</b>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                },
                series: {
                	colorByPoint: true,
                    pointWidth: 5,
                    stickyTracking: false,
                    borderWidth: 0,
                }
            },
            series: [{
                data: tatalArray

            }]
        });
    }
    
    function striationPage(type, tendencyListMap) {

    	if (type == 1) {
    		divName = "containerCash";
    	}
    	else if (type == 2){
    		divName = "containerProject";
    	}
    	else if (type == 3){
    		divName = "containerCard";
    	}
    	else if (type == 4) {
    		divName = "containerGoods";
    	}
    	$("div[name="+divName+"]").highcharts({
            chart: {
                backgroundColor: '#0a1718',
                type: 'areaspline',
                inverted: false
            },
            credits: {
                enabled: false,
            },
            title: {
                text: ''
            },
            xAxis: {
                reversed: false,
                title: '',
                categories: tendencyListMap.dateList,
                maxPadding: 0.05,
                showLastLabel: true,
                tickColor: '#1d2727',
                tickWidth: 10,
                tickPosition: 'inside',
                lineColor: '#383943',
                tickInterval: 2
            },
            yAxis: {
                title: {
                    text: ''
                },
                gridLineColor: '#383943'
            },
            legend: {
                enabled: false
            },
            tooltip: {
            	 backgroundColor: '#2f3736',
                formatter: function () {
                    var s = '<span style="color:#fff;">' + this.x +":"  + this.y + '元</span>';
                   
                    return s;
                },
                shared: true
            },
            plotOptions: {
                series: {
                    color: 'rgba(17,205,200,.5)',
                    fillColor: {
                        linearGradient: [0, 0, 0, 300],
                        stops: [
                            [0, Highcharts.Color(Highcharts.getOptions().colors[9]).setOpacity(0.4).get('rgba')],
                            [1, Highcharts.Color(Highcharts.getOptions().colors[9]).setOpacity(0).get('rgba')]
                        ]
                    },
                    marker: {
                        fillColor: 'rgba(17,205,200,.1)',
                        radius: 0.1
                    }
                }
            },
            series: [{
                data: tendencyListMap.tendencyList
            }]
        });
    }
    
    function crosswisePage(categoryMap) {
    	var projectType = categoryMap.projectType;
    	
    	var divName = "";
    	
    	if (projectType == 1) {
    		divName = "crosswiseProject";
    	}
    	else {
    		divName = "crosswiseGoods";
    	}
    	var categoryNameList = categoryMap.categoryNameList;
    	
    	var cashTatailAmountList = categoryMap.cashTatailAmountList;
    	
    	var cardTatailAmountList = categoryMap.cardTatailAmountList;
    	
    	var comboTatailAmountList = categoryMap.comboTatailAmountList;
    	
    	$("div[name='"+divName+"']").highcharts({
            chart: {
            	backgroundColor: 'rgba(0,0,0,0)',
                type: 'bar',
            },
            colors: ["#2068b3", '#62b1ff'],
            legend: {
            	backgroundColor: 'rgba(0,0,0,0)',
                itemDistance: 60,
                itemStyle: {
                    color: '#a2abb5',
                }
            },
            credits: {
                enabled: false,
            },
            title: {
                text: ''
            },
            xAxis: {
            	userHtml: true,
                categories: categoryNameList,
                tickColor: '#1d2727',
                tickWidth: 2,
                lineColor: '#383943',
                labels: {
                    style: {
                        color: '#a2abb5'
                    }
                }
            },
            yAxis: {
            	min: 0,
                title: {
                    text: ''
                },
                tickPixelInterval: 30,
                gridLineColor: '#383943',
                labels: {
                    style: {
                        color: '#a2abb5'
                    }
                }
            },
            plotOptions: {
            	column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                },
                series: {
                    pointWidth: 21,
                    stacking: 'normal',
                    borderWidth: 0,
                    stickyTracking: false
                }
            },
            series: [{
                name: '现金销售',
                data: cashTatailAmountList
            }, {
                name: '卡金\套餐',
                data: cardTatailAmountList
            }]
        });
    }
    
    function taocanChart(parentsObj, taocanType) {
    	var categoriesValue = "";
    	
    	var seriesValue = "";
    	if (taocanType == 1) {
    		categoriesValue = [
    	                    '疗程套餐',
    	                    '年季月套餐'
    	                     ];
    		
    		seriesValue = [{
                name: '现金销售',
                data: parentsObj.cashMoneyList
            }, {
                name: '卡金销售',
                data: parentsObj.cardMoneyList
            }];
    	}
    	else if (taocanType == 2) {
    		categoriesValue = parentsObj.projectName;
    		seriesValue = [{
                name: '剩余',
                data: cashMoneyList
            }, {
                name: '消费',
                data: cardMoneyList
            }];
    	}
    	
        $('#taocan-chart').highcharts({
            chart: {
                backgroundColor:'#0a1718',
                type: 'column',
                borderWidth: 0,
                inverted: false,
            },
            legend: {
                backgroundColor: '#0a1718',
                itemDistance: 60,
                itemStyle: {
                    color: '#a2abb5',
                    fontWeight: 'bold'
                }
            },
            credits: {
                enabled: false,
            },
            title: {
                text: ''
            },

            colors: ["#2068b3", '#62b1ff'],

            yAxis: {
                title: {
                    text: ''
                },
                allowDecimals: false,
                gridLineColor: '#383943',
                tickPixelInterval: 30,
                labels: {
                    style: {
                        color: '#a2abb5'
                    }
                }
            },
            xAxis: {
                tickPosition: 'outside',
                tickColor: '#1d2727',
                tickWidth: 2,
                lineColor: '#383943',
                labels: {
                    align: 'center',
                    style: {
                        color: '#a2abb5'
                    }
                },
                allowDecimals: false,
                categories:categoriesValue
            },
            plotOptions: {
                column: {
                    pointPadding: 1,
                    borderWidth: 0
                },
                series: {
                    pointWidth: 20,
                }
            },
            series: seriesValue
        });
    }
    
    function transitionEndEventName () {
        var i,
                undefined,
                el = document.createElement('div'),
                transitions = {
                    'transition':'transitionend',
                    'OTransition':'otransitionend', 
                    'MozTransition':'transitionend',
                    'WebkitTransition':'webkitTransitionEnd'
                };

        for (i in transitions) {
            if (transitions.hasOwnProperty(i) && el.style[i] !== undefined) {
                return transitions[i];
            }
        }

    };

    var transitionEnd = transitionEndEventName();
    $("#container1 .highcharts-series").on(transitionEnd, function() {

    });
    
    var rankingAmountList = "";
    var rankingNumberList = "";
    
    function cashAmountPage () {
    	
    	var overallDateType = overallDateTypeArray[pageType];
    	var overallDeptId = overallDeptIdArray[pageType];
    	
    	$.ajax({
            url : baseUrl + "boss/view/cashAmountService",
            type : "POST",
            data : "chooseType=" + overallDateType + "&deptId=" + overallDeptId + "&pageType=" + pageType,
            success : function(e){
                if (e.code != 0) {
                    dialog(e.msg);
                    return;
                }
                var data = e.msg;
                if (pageType == 0) {
                	var cashAmountMap = data.cashAmountMap;
                    var tendencyListMap = data.tendencyListMap;
                    var tatalMap = data.tatalMap;
                    
                    piePage(1, cashAmountMap);
                	
                    striationPage(1, tendencyListMap);
                    
                    if (overallDeptId == -1) {
                    	$("#columnarDIV").removeClass("hide");
                    	columnarPage(tatalMap);
                    	$(".summary-item").removeClass("hide");
                    	$("#newAddDebtAmount").text(tatalMap.debtAmount);
                    	
                    	var realAmount = new Big(tatalMap.cashAmount + tatalMap.unionpayAmount + tatalMap.wechatAmount + tatalMap.alipayAmount + tatalMap.groupAmount);
                        $("span[name='cashTypeTatail']").text(realAmount.toFixed(2));
                    }
                    else {
                    	$("#columnarDIV").addClass("hide");
                    	$(".summary-item").addClass("hide");
                    }
                }
                else if (pageType == 1) {
                	var cashAmountMap = data.cashAmountMap;
                	var tendencyListMap = data.tendencyListMap;
                	
                	piePage(5, cashAmountMap);
                	striationPage(3, tendencyListMap);
                }
                else if (pageType == 2){
                	var categoryMap = data.categoryMap;
                	var sizeMap = data.sizeMap;
                	var amountTypeMap = data.amountTypeMap;
                	var tendencyListMap = data.tendencyListMap;
                	crosswisePage(categoryMap);
                	piePage(2, sizeMap);
                	piePage(3, amountTypeMap);
                	$("#projectPrice").text(sizeMap.tatailAmount);
                	
                	striationPage(2, tendencyListMap);
                }
                else if (pageType == 3) {
                	
                	
                	var cardMoneyList = data.cardMoneyList;
                	var cashMoneyList = data.cashMoneyList;
                	
                	var parentsObj = {"cardMoneyList" : cardMoneyList, "cashMoneyList" : cashMoneyList};
                	
                	taocanChart(parentsObj, 1);
                	
                	var timeStatementDtoList = data.timeStatementDtoList;
                	var packageStatementDtoList = data.packageStatementDtoList;
                	
                	$("div[name='seriesComboTatailTime']").text(data.seriesComboTatailTime);
                	$("div[name='timeComboTatailTime']").text(data.timeComboTatailTime);
                	
                	$("div[name='seriesCombo']").empty();
                	$("div[name='timeCombo']").empty();
                	
                	var timeCashAmountMap = new Object();
                	var packageCashAmountMap = new Object();
                	
                	for (var i = 0; i < timeStatementDtoList.length; i++) {
                		var timeStatementDto = timeStatementDtoList[i];
                		
                		timeCashAmountMap["comboId"] = timeStatementDto.comboId;
                		
                		var natureStr = template('chart', timeStatementDto);
                		$("div[name='seriesCombo']").append(natureStr);
                		
                		var tataiNum = 0;
                		var tatailResidueTime = 0;
                		var tatailConsumeTime = 0;
                		
                		var statementProjectDtoList = timeStatementDto.statementProjectDtoList;
                		
                		var pageArray = new Array();
                		
                		var projectTbodyObj = $("div[name='"+timeStatementDto.comboId+"']").find("tbody[name='projectTbody']");
                		
                		projectTbodyObj.empty();
                		
                		for (var j = 0; j < statementProjectDtoList.length; j++) {
                			var statementProjectDto = statementProjectDtoList[j];
                			tatailResidueTime = tatailResidueTime + statementProjectDto.residueTime;
                			tatailConsumeTime = tatailConsumeTime + statementProjectDto.consumeTatailTime;
                			var chargeObj = {"name" : statementProjectDto.projectName, "y" : statementProjectDto.residueTime};
                			projectTbodyObj.append("<tr><td>"+statementProjectDto.projectName+"</td>"+
				                                    "<td>"+statementProjectDto.consumeTime+"</td>"+
				                                    "<td>"+statementProjectDto.consumeTatailTime+"</td>"+
				                                    "<td>"+statementProjectDto.newAddTime+"</td>"+
				                                    "<td>"+statementProjectDto.residueTime+"</td></tr>");
                		}
                		var chargeObj = {"name" : "剩余次数", "y" : tatailResidueTime};
                		pageArray.push(chargeObj);
                		var chargeObj1 = {"name" : "累计消费次数", "y" : tatailConsumeTime};
                		pageArray.push(chargeObj1);
                		timeCashAmountMap["pageArray"] = pageArray;
                		timeCashAmountMap["tataiNum"] = tatailResidueTime + tatailConsumeTime;
                		
                		piePage(4, timeCashAmountMap);
                	}
                	
                	for (var i = 0; i < packageStatementDtoList.length; i++) {
                		var packageStatementDto = packageStatementDtoList[i];
                		var natureStr = template('timeChart', packageStatementDto);
                		$("div[name='timeCombo']").append(natureStr);
                		packageCashAmountMap["comboId"] = packageStatementDto.comboId;
                		
                		var pageArray = new Array();
                		
                		var chargeObj = {"name" : "有效人数", "y" : packageStatementDto.validNumber };
                		pageArray.push(chargeObj);
                		var chargeObj1 = {"name" : "已过期人数", "y" : packageStatementDto.salesCount - packageStatementDto.validNumber};
                		pageArray.push(chargeObj1);
                		packageCashAmountMap["salesCount"] = packageStatementDto.salesCount;
                		packageCashAmountMap["pageArray"] = pageArray;
                		piePage(6, packageCashAmountMap);
                	}
                }
                else if (pageType == 4){
                	var categoryMap = data.categoryMap;

                	var tendencyListMap = data.tendencyListMap;
                	
                	var amountTypeMap = data.amountTypeMap;
                	
                	crosswisePage(categoryMap);

                	piePage(7, amountTypeMap);

                	striationPage(4, tendencyListMap);
                	
                	rankingAmountList = data.rankingAmountList;
                	
                	goodsSort(rankingAmountList);
                	
                	rankingNumberList = data.rankingNumberList;
                	
                }
            }
        });
    }
    
    function chooseSortType (obj, type) {
    	$(obj).siblings().removeClass("active");
    	$(obj).addClass("active");
    	if (type == 1) {
    		goodsSort(rankingAmountList);
    	}
    	else {
    		goodsSort(rankingNumberList);
    	}
    }
    
    function goodsSort (rankingList) {
    	$("#goodsRankingDIV").empty();
    	for (var i = 0; i < rankingList.length; i++) {
    		
    		var goods = rankingList[i];
    		
    		var pageElement = "<tr>"+
					            "<td>";
	        if (i == 0) {
	        	pageElement = pageElement + "<img src='"+baseUrl+"images/mobile/boss-newer/first.png' alt=''>";
	        }
	        else if (i == 1) {
	        	pageElement = pageElement + "<img src='"+baseUrl+"images/mobile/boss-newer/second.png' alt=''>";
	        }
	        else if (i == 2) {
	        	pageElement = pageElement + "<img src='"+baseUrl+"images/mobile/boss-newer/three.png' alt=''>";
	        }
	        else {
	        	var a = i + 1;
	        	pageElement = pageElement + a;
	        }
	        pageElement = pageElement + "</td>"+
			                    "<td>"+goods.goodsName+"</td>"+
			                    "<td>"+goods.goodsRealPrice+"</td>"+
			                    "<td>"+goods.goodsCount+"</td>"+
			                 "</tr>";
    		
    		$("#goodsRankingDIV").append(pageElement)
    	}
    }
</script>

</div>

</body>
</html>

<script id="chart" type="text/html">
<div class="chart-main" name = "{{comboId}}">
  <div class="chart-title">
	  <span name = 'comboNameSpan'>{{comboName}}</span>
  </div>
  <div class="chart-wrap">
	  <div class="taocan-wrap">

			  <div class="jiner">
				  <div>
					  <p class="sum-gz-num f18">{{comboSaleTatailPrice}}</p>
					  <p class="mt2 f12">套餐销售金额</p>
				  </div>
				  <div>
					  <p class="gz-num f18 mt2">{{newAddTime}}</p>
					  <p class="mt2 f12">新增购买人数</p>
				  </div>
			  </div>
			  <div class="biao">
				  <div class="lc-taocan" name="lc-taocan{{comboId}}" style="width:100%;height:230px;"></div>
			  </div>

	  </div>
	  
      <div class="chart-title xiangmu">
           <span>套餐项目次数统计</span>
      </div>
      <div class="chart-wrap">
           <table class="small-table">
              <thead>
                 <tr>
                     <td>项目名称</td>
                     <td>新增消费</td>
                     <td>累计消费</td>
                     <td>新增购买</td>
                     <td>余量</td>
                 </tr>
              </thead>
              <tbody name = "projectTbody">
                  
              </tbody>
           </table>
       </div>
  </div>
</div>
</script>

<script id="timeChart" type="text/html">
<div class="taocan">
    <div class="chart-main">
        <div class="chart-title">
             <span name = 'comboNameSpan'>{{comboName}}</span>
        </div>
        <div class="chart-wrap">
             <div class="taocan-wrap">
                  <div class="jiner-time">
                       <div>
					       <p class="sum-gz-num f18">{{comboSaleTatailPrice}}</p>
					       <p class="mt1 f12">套餐销售金额</p>
				       </div>
				       <div class="mt2">
					       <p class="gz-num f18">{{newAddTime}}</p>
					       <p class="mt1 f12">新增购买人次</p>
				       </div>
                       <div class="mt2">
					       <p class="gz-num f18 fyellow">{{newUseTime}}</p>
					       <p class="mt1 f12">消费人次</p>
				       </div>
                 </div>
                 <div class="biao-time">
                 <div class="jika-taocan" name="lc-taocan{{comboId}}" style="width:60%; height:230px;"></div>
                 </div>
            </div>
       </div>
    </div>
</div>
</script>