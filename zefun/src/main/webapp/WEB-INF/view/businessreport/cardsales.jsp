<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>


 <div class="maincontent">
    <div class="contentinner">
        <div class="report-title">
            <c:set var="searchType" value="本<span name='searchType'>日</span>"/>
            <c:set var="searchType2" value="上<span name='searchType'>日</span>"/>
            <c:choose>
                <c:when test="${empty summaryResult.dateType }">
                <c:set var="searchType" value="区间"/>
                <c:set var="searchType2" value="区间"/>
                </c:when>
                <c:otherwise>
                    <c:if test="${summaryResult.dateType ==  0}">
                    <c:set var="searchType" value="本<span name='searchType'>日</span>"/>
                    <c:set var="searchType2" value="上<span name='searchType'>日</span>"/>
                    </c:if>
                    <c:if test="${summaryResult.dateType ==  1}">
                    <c:set var="searchType" value="本<span name='searchType'>周</span>"/>
                    <c:set var="searchType2" value="上<span name='searchType'>周</span>"/>
                    </c:if>
                    <c:if test="${summaryResult.dateType ==  2 || summaryResult.dateType ==  3}">
                    <c:set var="searchType" value="本<span name='searchType'>月</span>"/>
                    <c:set var="searchType2" value="上<span name='searchType'>月</span>"/>
                    </c:if>
                    <c:if test="${summaryResult.dateType ==  4}">
                    <c:set var="searchType" value="本<span name='searchType'>年</span>"/>
                    <c:set var="searchType2" value="上<span name='searchType'>年</span>"/>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <ul>
                <li>
                    <fmt:formatNumber value="${summaryResult.totalOpenCardAmt}" pattern="##.##" minFractionDigits="2" var="stotalNewMemberAmount" />
                    <h1>${stotalNewMemberAmount }</h1>
                    <p>${searchType }新开</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.lastTotalOpenCardAmt}" pattern="##.##" minFractionDigits="2" var="slastTotalNewMemberAmount" />
                    <h1>${slastTotalNewMemberAmount }</h1>
                    <p>${searchType2 }新开</p>
                </li>
                <li>
                    <c:set value="0" var="rate"/><c:if test="${summaryResult.lastTotalOpenCardAmt > 0 }"><c:set value="${(summaryResult.totalOpenCardAmt - summaryResult.lastTotalOpenCardAmt) / summaryResult.lastTotalOpenCardAmt * 100 }" var="rate"/></c:if><fmt:formatNumber value="${rate}" pattern="##.#" minFractionDigits="1" var="sRate" />
                    <c:set var="cls" value="red"/>
                    <c:if test="${summaryResult.totalOpenCardAmt - summaryResult.lastTotalOpenCardAmt <= 0 }">
                    <c:set var="cls" value="green"/>
                    </c:if>
                    <h1 class="${cls }">${sRate }%</h1>
                    <p>${searchType }新开增长</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.totalChargedAmt + summaryResult.totalUpgradeAmt}" pattern="##.##" minFractionDigits="2" var="stotalChargeMemberAmount" />
                    <h1>${stotalChargeMemberAmount }</h1>
                    <p>${searchType }充值/升级</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.lastTotalChargedAmt + summaryResult.lastTotalUpgradeAmt}" pattern="##.##" minFractionDigits="2" var="slastTotalChargeMemberAmount" />
                    <h1>${slastTotalChargeMemberAmount }</h1>
                    <p>${searchType2 }充值/升级</p>
                </li>
                <li>
                    <c:set value="0" var="rate"/><c:if test="${summaryResult.lastTotalChargedAmt > 0 }"><c:set value="${(summaryResult.totalChargedAmt - summaryResult.lastTotalChargedAmt) / summaryResult.lastTotalChargedAmt * 100 }" var="rate"/></c:if><fmt:formatNumber value="${rate}" pattern="##.#" minFractionDigits="1" var="sRate" />
                    <c:set var="cls" value="red"/>
                    <c:if test="${summaryResult.totalChargedAmt - summaryResult.lastTotalChargedAmt <= 0 }">
                    <c:set var="cls" value="green"/>
                    </c:if>
                    <h1 class="${cls }">${sRate }%</h1>
                    <p>${searchType }续充增长</p>
                </li>
            </ul>
        </div>

        <div id="custom-toolbar"  style="margin-bottom: 20px;">
            <div class="table-toolbar" style="margin-bottom: 20px;">
            	<c:if test="${not empty summaryResult.branchStores}">
            		<span>门店</span>
            		<select id="pid" onchange="changeStore()">
            			<c:forEach items="${summaryResult.branchStores }" var="Store">
            				<option grade="${Store.storeId }" id="${Store.storeId}">${Store.storeName }</option>
            			</c:forEach>
            		</select>
            	</c:if>
                <span class="mr10">日期区间</span>
                <input type="text" class="datetimepicker input80" daysOffset="0" id="startDate" name="startDate" value="${summaryResult.begin }"/>－
                <input type="text" class="datetimepicker input80" daysOffset="0" id="endDate" name="endDate" value="${summaryResult.end }"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="button-search btn" style="margin-left: -10px;" onclick="querySummary();">查询</button>
                <div class="select-target-report">
                    <span onclick="changeDate(0,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 0 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日</a></span>
                    <span onclick="changeDate(1,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 1 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">周</a></span>
                    <span onclick="changeDate(2,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月</a></span>
                    <span onclick="changeDate(3,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 3 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日趋势</a></span>
                    <span onclick="changeDate(4,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 4 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月趋势</a></span>
                    <!-- <span class="report-category ml10"><a href="yingyehuizong-zongdian.html">总部汇总</a></span> -->
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="cash-day" style="width: 1050px;height:400px;<c:if test="${not empty summaryResult.dateType and (summaryResult.dateType == 3 or summaryResult.dateType == 4) }">display:none;</c:if>"></div><!--cash-day-->
            <div id="cash-day2" style="width: 1050px;height:400px; <c:if test="${empty summaryResult.dateType or summaryResult.dateType == 0 or summaryResult.dateType == 1 or summaryResult.dateType == 2 }">display:none;</c:if>"></div><!--cash-day-->
        </div>
        <!--储值卡分类统计-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">储值卡销售分类统计</span>
                    <span class="fr">时间：<span id="tableDate">${summaryResult.begin }-${summaryResult.end }</span> 
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<span class="red">单位：万元</span>
                  		</c:when>
                  		<c:otherwise>
                  			单位：元
                  		</c:otherwise>
                  	</c:choose>
                    </span>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <table id="export" class="table table-bordered dep-border-table table-dash">
               <thead class="t-fix">
               <tr>
                   <th rowspan="2">会员等级</th>
                   <th colspan="2" class="dep-bottom-botrder table-part">新开</th>
                   <th colspan="2" class="dep-bottom-botrder table-part">充值</th>
                   <th colspan="2" class="dep-bottom-botrder table-part">升级</th>
                   <th colspan="2" class="dep-bottom-botrder table-part">划卡消费</th>
                   <th colspan="6" class="dep-bottom-botrder table-part">数据统计</th>
               </tr>
               <tr>
                   
                   <th class="table-part">人数</th>                   
                   <th>金额</th>                   
                   <th class="table-part">人数</th>                   
                   <th>金额</th>                   
                   <th class="table-part">人数</th>              
                   <th>金额</th>   
                   <th class="table-part">人数</th>                   
                   <th>金额</th>                
                   <th class="table-part">总人数</th>
                   <th>人数占比</th>
                   <th>累计储值</th>                
                   <th>储值占比</th>
                   <th>余额</th>
                   <th>余额占比</th>
               </tr>
               </thead>
               <tbody>
               <c:set var="newoCnt" value="0"></c:set>
               <c:set var="newoAmt" value="0"></c:set>
               <c:set var="newcCnt" value="0"></c:set>
               <c:set var="newcAmt" value="0"></c:set>
               <c:set var="newuCnt" value="0"></c:set>
               <c:set var="newuAmt" value="0"></c:set>
               <c:set var="newhCnt" value="0"></c:set>
               <c:set var="newhAmt" value="0"></c:set>
               <c:forEach var="memberLevel" items="${summaryResult.memberLevelInfo }" varStatus="index">
               	<tr>
               		<td >${memberLevel.levelInfo.levelName }</td>
               		<td class="table-part">${memberLevel.level.openCnt }</td>
               		<td>
               		<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			${memberLevel.level.newOpenAmt /10000 }
                  		</c:when>
                  		<c:otherwise>
                  			${memberLevel.level.newOpenAmt}
                  		</c:otherwise>
                  	</c:choose>
               		</td>
               		<td class="table-part">${memberLevel.level.chargeCnt }</td>
               		<td>
               		<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			${memberLevel.level.chargeCardAmt /10000}
                  		</c:when>
                  		<c:otherwise>
                  			${memberLevel.level.chargeCardAmt }
                  		</c:otherwise>
                  	</c:choose>
               		</td>
               		<td class="table-part">${memberLevel.level.upgradeCnt }</td>
               		<td>
               		<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			${memberLevel.level.upgradeAmt /10000}
                  		</c:when>
                  		<c:otherwise>
                  			${memberLevel.level.upgradeAmt }
                  		</c:otherwise>
                  	</c:choose>
               		</td>
               		<td class="table-part">${memberLevel.memberCards.memberCnt }</td>
               		<td>
               		<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			${memberLevel.memberCards.cardAmt /10000}
                  		</c:when>
                  		<c:otherwise>
                  			${memberLevel.memberCards.cardAmt }
                  		</c:otherwise>
                  	</c:choose>
               		</td>
               		<td class="table-part">${memberLevel.memberAccounts.memberCnt }</td>
               		<td>
               			<c:set var="numRate" value="0.00"/>
               			<c:if test="${summaryResult.totalMemberCnt>0}">
               			<c:set var="numRate" value="${memberLevel.memberAccounts.memberCnt*100 /summaryResult.totalMemberCnt  }"/>
               			</c:if>
               			<fmt:formatNumber value="${numRate}" pattern="##.##" minFractionDigits="2" var="snumRate" /> 
               			${snumRate}%
               		</td>
               		<td class="table-part">
               		<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			${memberLevel.memberAccounts.accumulativeAmt / 10000}
                  		</c:when>
                  		<c:otherwise>
                  			${memberLevel.memberAccounts.accumulativeAmt}
                  		</c:otherwise>
                  	</c:choose>
               		</td>
               		<td>
               			<c:set var="amtRate" value="0.00"/>
               			<c:if test="${summaryResult.totalMemberValue>0}">
               			<c:set var="amtRate" value="${memberLevel.memberAccounts.accumulativeAmt/summaryResult.totalMemberValue*100 }"></c:set>
               			</c:if>
               			<fmt:formatNumber value="${amtRate}" pattern="##.##" minFractionDigits="2" var="samtRate" />
               			${samtRate}%
               		</td>
               		<td class="table-part">
               		<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			${memberLevel.memberAccounts.balanceAmt /10000}
                  		</c:when>
                  		<c:otherwise>
                  			${memberLevel.memberAccounts.balanceAmt }
                  		</c:otherwise>
                  	</c:choose>
               		</td>
               		<td>
               			<c:set var="balRate" value="0.00"/>
               			<c:if test="${summaryResult.totalBalanceValue>0}">
               			<c:set var="balRate" value="${memberLevel.memberAccounts.balanceAmt/summaryResult.totalBalanceValue*100 }"></c:set>
               			</c:if>
               			<fmt:formatNumber value="${balRate}" pattern="##.##" minFractionDigits="2" var="sbalRate" />
               			${sbalRate}%
               		</td>
               		<c:set var="newoCnt" value="${newoCnt+memberLevel.level.openCnt }"></c:set>
               		<c:set var="newoAmt" value="${newoAmt+memberLevel.level.newOpenAmt }"></c:set>
               		<c:set var="newcCnt" value="${newcCnt+memberLevel.level.chargeCnt }"></c:set>
               		<c:set var="newcAmt" value="${newcAmt+memberLevel.level.chargeCardAmt }"></c:set>
               		<c:set var="newuCnt" value="${newuCnt+memberLevel.level.upgradeCnt }"></c:set>
               		<c:set var="newuAmt" value="${newuAmt+memberLevel.level.upgradeAmt }"></c:set>
               		<c:set var="newhCnt" value="${newhCnt+memberLevel.memberCards.memberCnt }"></c:set>
               		<c:set var="newhAmt" value="${newhAmt+memberLevel.memberCards.cardAmt }"></c:set>
               	</tr>
               </c:forEach>
               <tr class="huizong-tr">
                   <td>合计</td>
                   <td class="table-part">${newoCnt }</td>
                   
                   <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${newoAmt/10000}" pattern="##.##" minFractionDigits="2" var="stotalAmount1" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${newoAmt}" pattern="##.##" minFractionDigits="2" var="stotalAmount1" />
                  		</c:otherwise>
                  	</c:choose>
                   <td >${stotalAmount1 }</td>
                   
                   <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${newcAmt/10000}" pattern="##.##" minFractionDigits="2" var="stotalAmount2" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${newcAmt}" pattern="##.##" minFractionDigits="2" var="stotalAmount2" />
                  		</c:otherwise>
                  	</c:choose>
                   <td class="table-part">${newcCnt }</td>
                   <td>${stotalAmount2 }</td>
                   
                   <td class="table-part">${newuCnt }</td>
                   
                   <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${newuAmt/10000}" pattern="##.##" minFractionDigits="2" var="stotalAmount3" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${newuAmt/10000}" pattern="##.##" minFractionDigits="2" var="stotalAmount3" />
                  		</c:otherwise>
                  	</c:choose>
                   <td>${stotalAmount3 }</td>
                   
                   
                   <td class="table-part">${newhCnt }</td>
                   
                   <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${newhAmt/10000}" pattern="##.##" minFractionDigits="2" var="stotalAmount3" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${newhAmt}" pattern="##.##" minFractionDigits="2" var="stotalAmount3" />
                  		</c:otherwise>
                  	</c:choose>
                   <td>${stotalAmount3 }</td>
                   
                   <td>
                   ${ summaryResult.totalMemberCnt }
                   </td>
                   <td>
                   		<c:set var="prate1" value="0.00"/>
                   			<c:if test="${summaryResult.totalMemberCnt>0 }"><c:set var="prate1" value="100"></c:set></c:if>
                   		<fmt:formatNumber value="${prate1}" pattern="##.##" minFractionDigits="2" var="sprate1" />
                   		${sprate1}%
                   </td>
                   <td>
                   <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			${summaryResult.totalMemberValue /10000}
                  		</c:when>
                  		<c:otherwise>
                  			${summaryResult.totalMemberValue }
                  		</c:otherwise>
                  	</c:choose>
                   </td>
                   <td>
                   		<c:set var="srate1" value="0.00"/>
                   			<c:if test="${summaryResult.totalMemberValue>0 }"><c:set var="srate1" value="100"></c:set></c:if>
                   		<fmt:formatNumber value="${srate1}" pattern="##.##" minFractionDigits="2" var="ssrate1" />
                   		${ssrate1}%
                   </td>
                   <td>
                   <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			${summaryResult.totalBalanceValue /10000}
                  		</c:when>
                  		<c:otherwise>
                  			${summaryResult.totalBalanceValue }
                  		</c:otherwise>
                  	</c:choose>
                   </td>
                   <td>
                   		<c:set var="brate1" value="0.00"/>
                   			<c:if test="${summaryResult.totalBalanceValue>0 }"><c:set var="brate1" value="100"></c:set></c:if>
                   		<fmt:formatNumber value="${brate1}" pattern="##.##" minFractionDigits="2" var="sbrate1" />
                   		${sbrate1}%
                   </td>
               </tr>
               </tbody>
           </table>
           <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10"  onclick ="exportTable('export')">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span>导出</span>
                        </button>
                        <button class="btn ml10">
                            <span onclick="printTable('export')">打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
        </div>
    </div>
</div>
<c:choose>
<c:when test="${not empty summaryResult.dateType && (summaryResult.dateType == 1)}">
<c:set var="dateType" value="周" />
</c:when>
<c:when test="${not empty summaryResult.dateType && (summaryResult.dateType == 2 || summaryResult.dateType == 3)}">
<c:set var="dateType" value="月" />
</c:when>
<c:when test="${not empty summaryResult.dateType && (summaryResult.dateType == 4)}">
<c:set var="dateType" value="年" />
</c:when>
<c:otherwise>
<c:set var="dateType" value="日" />
</c:otherwise>
</c:choose>

<c:choose>
<c:when test="${not empty summaryResult.dateType && (summaryResult.dateType == 4)}">
<c:set var="dateType2" value="月" />
</c:when>
<c:otherwise>
<c:set var="dateType2" value="日" />
</c:otherwise>
</c:choose>

<script type="text/javascript">
var date = new Date();
var syear = date.getFullYear() + '年';
var smonth = date.getFullYear() + '年' + (date.getMonth() + 1) + '月1日 － ' + date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDaysInMonth() + '日';
var sweekStart = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + (1 - date.getDay() + date.getDate()) + '日';
var sweekEnd = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + (7 - date.getDay() + date.getDate()) + '日';
var sweek = sweekStart + ' － ' + sweekEnd;
var sdate = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日';
sdate = '${summaryResult.begin}-${summaryResult.end}';
jQuery(function () {
		
	    initDay();
        var colors = Highcharts.getOptions().colors,
         categories = [<c:forEach var="memberLevel" items="${summaryResult.memberLevelInfo }" varStatus="memberLevelStatus">'${memberLevel.levelInfo.levelName }'<c:if test="${not memberLevelStatus.last }">,</c:if></c:forEach>],
        name = '收入分布',
        data = [
				<c:forEach var="memberLevel" items="${summaryResult.memberLevelInfo }" varStatus="memberLevelStatus">
				{
					name:'${memberLevel.levelInfo.levelName}',
					y: ${memberLevel.memberCards.cardAmt }
				}
				<c:if test="${not memberLevelStatus.last }">,</c:if>
				</c:forEach>
                ];

       function setChart(name, categories, data, color) {
                    /*chart.xAxis[0].setCategories(categories, false);*/
                    chart.series[0].remove(false);
                    chart.addSeries({
                        name: name,
                        data: data,
                        color: color || colors[0]
                    }, false);
                    chart.redraw();
                }
       <c:choose>
       <c:when test="${empty summaryResult.dateType}">var date_type = -1;</c:when>
       <c:otherwise>var date_type = ${summaryResult.dateType};</c:otherwise>
       </c:choose>
        var chart = jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '卡金消费汇总'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                categories: [
                             <c:forEach var="memberLevel" items="${summaryResult.memberLevelInfo }" varStatus="memberLevelStatus">
                             '${memberLevel.levelInfo.levelName }'
                             <c:if test="${not memberLevelStatus.last }">,</c:if>
                             </c:forEach>
                             ]
            },
            yAxis: {
            	labels: {
                    formatter: function () {
                  	  	if(2 == date_type){
                  	  		return this.value / 10000 + '万';
                  	  	} 
                  	  return this.value;
                    }
                },
                min: 0,
                title: {
                    text: '卡金收入金额(元)'
                }
            },
            plotOptions: {
                column: {
                    cursor: 'pointer',
          /*           point: {
	                  events: {
                            click: function() {
                                var drilldown = this.drilldown;
                                if (drilldown) { // drill down
                                    setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
                                } else { // restore
                                    setChart(name, categories, data);
                                }
                            }
                        }
                    } ,  */
                    dataLabels: {
                        enabled: true,
                        color:'#000',
                        style: {
                            fontWeight: 'bold',
                            boxShadow: 'none',
                            textShadow: 'none',
                        },
                        formatter: function() {
                            return this.y;
                        }
                    } 
                }
            },
            tooltip: {
                formatter: function() {
                    var point = this.point,
                            s = this.x +'销售金额:<b>'+ this.y +'元</b><br/>';
                    return s;
                }
            },
            series: [{
                name: '收入分布',
                data: data
            }],
            exporting: {
                enabled: true
            }
        }).highcharts(); // return chart


       
      if(2 == date_type || 3 == date_type || 4 == date_type) {

          jQuery('#cash-day2').highcharts({
              chart: {
                  type: 'spline'
              },
              title: {
                  text: '卡金消费(${dateType2}走势)'
              },
              subtitle: {
                  text: sdate
              },
              xAxis: {
                  categories: [                               
						<c:choose>
						<c:when test="${not empty summaryResult.trends}">
						<c:set var="trend" value="${summaryResult.trends[0]}"></c:set> 
						</c:when>
						</c:choose>
						<c:forEach items="${trend.trends}" var="dept" varStatus="deptStatus">
							${dept.currDate}
						<c:if test="${not deptStatus.last}">,</c:if>
						</c:forEach> 
                               ]
              },
              yAxis: {
            	  labels: {
                      formatter: function () {
                    	  	if(3 == date_type || 4 == date_type){
                    	  		return this.value / 10000 + '万';
                    	  	}
                        	return this.value;
                      }
                  },
                  min: 0,
                  title: {
                      text: '金额 (万元)'
                  }
              },
              tooltip: {
                  crosshairs: true,
                  headerFormat: '<span style="font-size:10px">&nbsp;&nbsp;{point.key}${dateType2}</span><table>',
                  pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                  '<td style="padding:0"><b>{point.y} </b></td></tr>',
                  footerFormat: '</table>',
                  shared: true,
                  useHTML: true
              },
              plotOptions: {
                  line: {
                      dataLabels: {
                          enabled: true
                      },
                      enableMouseTracking: false
                  },
                  spline: {
                      lineWidth: 2,
                      states: {
                          hover: {
                              lineWidth: 3
                          }
                      },
                      marker: {
                          enabled: false
                      }
                  }
              },
              series: [
            <c:forEach var="dateSumEntry" items="${summaryResult.trends }" varStatus="dateSumStatus">
            	{
            		name:'${summaryResult.idForName[dateSumEntry.orderType]}',
            		data:[
						<c:forEach items="${dateSumEntry.trends}" var="dateData" varStatus="dateStatus">
							${dateData.deptSum}
							<c:if test="${not dateStatus.last}">,</c:if>
						</c:forEach>
            		      ]
            	}          			
            	<c:if test="${not dateSumStatus.last }">,</c:if>            
            </c:forEach>
                         ]        
          });
      }
});	
		  function initDay(){
			  var objBegin='${summaryResult.begin}';
			  objBegin=objBegin.replaceAll('-','/');
			  var objEnd='${summaryResult.end}';
			  objEnd=objEnd.replaceAll('-','/');
			  jQuery("#startDate").val(objBegin);
			  jQuery("#endDate").val(objEnd);
			  
			  var objOptions=document.getElementById("pid");
		    	if(objOptions!=null){
		    		var storeId='${summaryResult.storeId}';
		        	for(var i=0; i<objOptions.length; i++){
		        		if (objOptions.options[i].getAttribute("grade")==storeId){
		        			objOptions.options[i].setAttribute("selected",true);
		        		}
		        	}
		    	}
		    	
		    	<c:choose>
		        <c:when test="${empty summaryResult.dateType}">var date_type = -1;</c:when>
		        <c:otherwise>var date_type = ${summaryResult.dateType};</c:otherwise>
		        </c:choose>
		        
			  jQuery('#cash-day').highcharts({
		            chart: {
		                type: 'column'
		            },
		            title: {
		                text: '卡金消费汇总'
		            },
		            subtitle: {
		                text: ''
		            },
		            xAxis: {
		                categories: [
		                             <c:forEach var="memberLevel" items="${summaryResult.memberLevelInfo }" varStatus="memberLevelStatus">
		                             '${memberLevel.levelInfo.levelName }'
		                             <c:if test="${not memberLevelStatus.last }">,</c:if>
		                             </c:forEach>
		                             ]
		            },
		            yAxis: {
		            	labels: {
		                      formatter: function () {
		                    	  	if(2 == date_type){
		                    	  		return this.value / 10000 + '万';
		                    	  	}
		                        	return this.value;
		                      }
		                  },
		                min: 0,
		                title: {
		                    text: '卡金收入金额(元)'
		                }
		            },
		            plotOptions: {
		                column: {
		                    cursor: 'pointer',
		          /*           point: {
			                  events: {
		                            click: function() {
		                                var drilldown = this.drilldown;
		                                if (drilldown) { // drill down
		                                    setChart(drilldown.name, drilldown.categories, drilldown.data, drilldown.color);
		                                } else { // restore
		                                    setChart(name, categories, data);
		                                }
		                            }
		                        }
		                    } ,  */
		                    dataLabels: {
		                        enabled: true,
		                        color:'#000',
		                        style: {
		                            fontWeight: 'bold',
		                            boxShadow: 'none',
		                            textShadow: 'none',
		                        },
		                        formatter: function() {
		                            return this.y;
		                        }
		                    } 
		                }
		            },
		            tooltip: {
		                formatter: function() {
		                    var point = this.point,
		                            s = this.x +'销售金额:<b>'+ this.y +'元</b><br/>';
		                    return s;
		                }
		            },
		            series: [{
		                name: name,
		                data: [
		       				<c:forEach var="memberLevel" items="${summaryResult.memberLevelInfo }" varStatus="memberLevelStatus">
		    				{
		    					name:'${memberLevel.levelInfo.levelName}',
		    					y: ${memberLevel.memberCards.cardAmt }
		    				}
		    				<c:if test="${not memberLevelStatus.last }">,</c:if>
		    				</c:forEach>
		                    ]
		            }],
		            exporting: {
		                enabled: true
		            }
		        }).highcharts(); // return chart

		        
		      if(2 == date_type || 3 == date_type || 4 == date_type) {

		          jQuery('#cash-day2').highcharts({
		              chart: {
		                  type: 'spline'
		              },
		              title: {
		                  text: '卡金消费(${dateType2}走势)'
		              },
		              subtitle: {
		                  text: sdate
		              },
		              xAxis: {
		                  categories: [                               
								<c:choose>
								<c:when test="${not empty summaryResult.trends}">
								<c:set var="trend" value="${summaryResult.trends[0]}"></c:set> 
								</c:when>
								</c:choose>
								<c:forEach items="${trend.trends}" var="dept" varStatus="deptStatus">
									${dept.currDate}
								<c:if test="${not deptStatus.last}">,</c:if>
								</c:forEach> 
		                               ]
		              },
		              yAxis: {
		            	  labels: {
		                      formatter: function () {
		                    	  	if(3 == date_type || 4 == date_type){
		                    	  		return this.value / 10000 + '万';
		                    	  	}
		                        	return this.value;
		                      }
		                  },
		                  min: 0,
		                  title: {
		                      text: '金额 (万元)'
		                  }
		              },
		              tooltip: {
		                  crosshairs: true,
		                  headerFormat: '<span style="font-size:10px">&nbsp;&nbsp;{point.key}${dateType2}</span><table>',
		                  pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
		                  '<td style="padding:0"><b>{point.y} </b></td></tr>',
		                  footerFormat: '</table>',
		                  shared: true,
		                  useHTML: true
		              },
		              plotOptions: {
		                  line: {
		                      dataLabels: {
		                          enabled: true
		                      },
		                      enableMouseTracking: false
		                  },
		                  spline: {
		                      lineWidth: 2,
		                      states: {
		                          hover: {
		                              lineWidth: 3
		                          }
		                      },
		                      marker: {
		                          enabled: false
		                      }
		                  }
		              },
		              series: [
		            <c:forEach var="dateSumEntry" items="${summaryResult.trends }" varStatus="dateSumStatus">
		            	{
		            		name:'${summaryResult.idForName[dateSumEntry.orderType]}',
		            		data:[
								<c:forEach items="${dateSumEntry.trends}" var="dateData" varStatus="dateStatus">
									${dateData.deptSum}
									<c:if test="${not dateStatus.last}">,</c:if>
								</c:forEach>
		            		      ]
		            	}          			
		            	<c:if test="${not dateSumStatus.last }">,</c:if>            
		            </c:forEach>
		                         ]        
		          });
		      }
		  }
          function changeDate(type, obj) {
              jQuery(".curent-report").removeClass('curent-report').addClass('report-category');
              jQuery(obj).removeClass('report-category').addClass('curent-report');
              <c:choose>
              <c:when test="${empty summaryResult.dateType}">var dateType = -1;</c:when>
              <c:otherwise>var dateType = ${summaryResult.dateType};</c:otherwise>
              </c:choose>
              switch(type) {
              case 0: // 日
                if(dateType != 0){
                	var objOptions=document.getElementById("pid");
                    if(objOptions!=null){
                  	  var storeId = jQuery("#pid").find("option:selected").attr("grade");
                  	  jQuery('#storeId').val(storeId);
                    }
                  jQuery('#summaryFrm').submit();
                }
                break;
              case 1: // 周
                if(dateType != 1){
                	var objOptions=document.getElementById("pid");
                    if(objOptions!=null){
                  	  var storeId = jQuery("#pid").find("option:selected").attr("grade");
                  	  jQuery('#storeId').val(storeId);
                    }
                  jQuery('#dateType').val(1);
                  jQuery('#summaryFrm').submit();
                }
                break;
              case 2: // 月
              case 3: // 日趋势
                  if(dateType != 2 && dateType != 3){
                	  var objOptions=document.getElementById("pid");
                      if(objOptions!=null){
                    	  var storeId = jQuery("#pid").find("option:selected").attr("grade");
                    	  jQuery('#storeId').val(storeId);
                      }
                    jQuery('#dateType').val(type);
                    jQuery('#summaryFrm').submit();
                    return;
                  }
                  if(dateType == 2 && type == 3) {
                    jQuery("#cash-day").hide();
                    jQuery("#cash-day2").show();
                      return;
                  }
                  if(dateType == 3 && type == 2) {
                    jQuery("#cash-day2").hide();
                    jQuery("#cash-day").show();
                      return;
                  }
                  if(dateType == 3 && type == 3) {
                      jQuery("#cash-day").hide();
                      jQuery("#cash-day2").show();
                        return;
                    }
                  if(dateType == 2 && type == 2) {
                      jQuery("#cash-day2").hide();
                      jQuery("#cash-day").show();
                        return;
                    }
                  break;
              case 4: // 月趋势
                if(dateType != 4){
                	var objOptions=document.getElementById("pid");
                    if(objOptions!=null){
                  	  var storeId = jQuery("#pid").find("option:selected").attr("grade");
                  	  jQuery('#storeId').val(storeId);
                    }
                      jQuery('#dateType').val(4);
                      jQuery('#summaryFrm').submit();
                    }
                    break;
              }

          }
          function querySummary() {
        	  var objOptions=document.getElementById("pid");
              if(objOptions!=null){
            	  var storeId = jQuery("#pid").find("option:selected").attr("grade");
            	  jQuery('#storeId').val(storeId);
              }
              jQuery('#begin').val(jQuery('#startDate').val());
              jQuery('#end').val(jQuery('#endDate').val());
              jQuery('#dateType').removeAttr('value');
              jQuery('#summaryFrm').submit();
            }
          
          jQuery(function() {
        	  /*表头置顶*/
        		var a=[];
        	    jQuery(".mainwrapper").show()
        	    var tlength=jQuery(".t-fix th").length;
        	        for(i=0;i<tlength;i++)  {
        	        a[i]=jQuery(".t-fix th").eq(i).width();
        	    }

        	   function table() {
        	       jQuery(".mainwrapper").show()
        	       var fix=jQuery(".t-fix").offset()
        	       var tableT=fix.top
        	       jQuery(window).scroll(function(event){
        	           var scH=jQuery(window).scrollTop()
        	           if(scH>tableT){
        	               jQuery(".t-fix").addClass("add-fix")
        	               for(i=0;i<jQuery(".t-fix th").length;i++){
        	                   var tdwidth=a[i];
        	                   var tbwidth=a[i];
        	                   jQuery(".t-fix th").eq(i).css("width",tdwidth)
        	                   jQuery(".t-table td").eq(i).css("width",tbwidth)

        	               }
        	           }
        	           else{
        	               jQuery(".t-fix").removeClass("add-fix")
        	           }
        	       })
        	   }
        	   table(); 
        	});
          
          function changeStore(){
          	var begin='${summaryResult.begin}';
          	var end='${summaryResult.end}'; 
          	var dateType='${summaryResult.dateType}';
              var storeId = jQuery("#pid").find("option:selected").attr("grade");
          	  jQuery('#begin').val(begin);
              jQuery('#end').val(end);
              jQuery('#storeId').val(storeId);
              jQuery('#dateType').val(dateType);
              jQuery('#summaryFrm').submit();
          }
</script>


    </div>
    <!--RIGHT PANEL结束 -->

    <a href="" class="showmenu"></a>

</div>
</div><!--mainwrapper-->

</div>


    <!--RIGHT PANEL结束 -->


</div><!--mainwrapper-->
<form action="<%=basePath %>cardsales/view/cardsales" style="display:none;" id="summaryFrm" method="post">
<input type="hidden" name="begin" id="begin"/>
<input type="hidden" name="end" id="end"/>
<input type="hidden" name="storeId" id="storeId">
<input type="hidden" name="dateType" id="dateType"/>
</form>
</body>
</html>
