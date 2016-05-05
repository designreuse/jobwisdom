<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
#cash-day2 #highcharts-2 {
    width: 100%;
    
    
}
</style>
<body>
<script type="text/javascript" src="<%=basePath %>js/export/tableExport.js"></script>
<script type="text/javascript" src="<%=basePath %>js/export/jquery.base64.js"></script>
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
                <c:set var="searchType2" value="区间营业实收"/>
                </c:when>
                <c:otherwise>
                    <c:if test="${summaryResult.dateType ==  0}">
                    <c:set var="searchType" value="本<span name='searchType'>日</span>"/>
                    <c:set var="searchType2" value="上<span name='searchType'>日</span>营业实收"/>
                    </c:if>
                    <c:if test="${summaryResult.dateType ==  1}">
                    <c:set var="searchType" value="本<span name='searchType'>周</span>"/>
                    <c:set var="searchType2" value="上<span name='searchType'>周</span>营业实收"/>
                    </c:if>
                    <c:if test="${summaryResult.dateType ==  2 || summaryResult.dateType ==  3}">
                    <c:set var="searchType" value="本<span name='searchType'>月</span>"/>
                    <c:set var="searchType2" value="上<span name='searchType'>月</span>营业实收"/>
                    </c:if>
                    <c:if test="${summaryResult.dateType ==  4}">
                    <c:set var="searchType" value="本<span name='searchType'>年</span>"/>
                    <c:set var="searchType2" value="上<span name='searchType'>年</span>营业实收"/>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <ul>
                <li>
                    <fmt:formatNumber value="${summaryResult.businessIncomes}" pattern="##.##" minFractionDigits="2" var="stotalIncome" />
                    <h1>${stotalIncome }</h1>
                    <p>${searchType }营业收入</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.businessExpense}" pattern="##.##" minFractionDigits="2" var="stotalExpenses" />
                    <h1>${stotalExpenses }</h1>
                    <p>${searchType }营业扣减</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.businessRealIncome}" pattern="##.##" minFractionDigits="2" var="stotalAmount" />
                    <h1>${stotalAmount }</h1>
                    <p>${searchType }营业实收</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.lastBusinessRealIncome}" pattern="##.##" minFractionDigits="2" var="slastTotalAmount" />
                    <h1>${slastTotalAmount }</h1>
                    <p>${searchType2 }</p>
                </li>
                <li>
                    <h1 class="red">${summaryResult.businessIncomeIncrementRate*100 }%</h1>
                    <p>营业实收${searchType }增长</p>
                </li>
            </ul>
        </div>

        <div id="custom-toolbar" style="margin-bottom: 20px;">
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
                    <!-- <span class="report-category ml10"><a href="yingyehuizong-zongbu.html">总部汇总</a></span> -->
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="cash-day" style="width: 1050px;height:400px;<c:if test="${not empty summaryResult.dateType and (summaryResult.dateType == 3 or summaryResult.dateType == 4) }">display:none;</c:if>"></div><!--cash-day-->
            <div id="cash-day2" style="width: 1050px;height:400px; <c:if test="${empty summaryResult.dateType or summaryResult.dateType == 0 or summaryResult.dateType == 1 or summaryResult.dateType == 2 }">display:none;</c:if>"></div><!--cash-day-->
        </div>


        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店营业汇总</span>
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

            <table id="export" class="table dep-border-table table-bordered table-striped table-dash">
                <thead>
                <tr>
                    <th rowspan="2" class="text-center">部门</th>
                    <th colspan="3" class="text-center table-part dep-bottom-botrder">营业收入</th>
                 
                    <th colspan="5" class="text-center table-part dep-bottom-botrder">储值及套餐销售</th>
                   <!--  <th colspan="3" class="text-center table-part dep-bottom-botrder">会员刷卡</th> -->
                    <th colspan="4" class="text-center  table-part dep-bottom-botrder">营业扣减</th>
                    <th rowspan="2" class="text-center  table-part">套餐消费</th>
                    <th rowspan="2" class="text-center  table-part">会员划卡</th>
                    <th rowspan="2" class="text-center  table-part">现金实收</th>
                </tr>
                <tr>
                    <th class="table-part">项目消费</th>
                    <th>商品销售</th>
                    <th>汇总</th>
                    <!-- <th class="table-part"></th> -->
                    <!-- <th></th> -->
                    <th class="table-part">开卡</th>
                    <th>充值</th>
                    <th>升级</th>
                    <th>套餐购买</th>
                    <th >汇总</th>
                    <th class="table-part">礼金</th>
                    <th>优惠券</th>
                    <th>预约</th>
                    <th>汇总</th>
                </tr>
                </thead>
                  <tbody>
                    <c:set value="0" var="total1"/>
                    <c:set value="0" var="total2"/>
                    <c:set value="0" var="total3"/>
                    <c:set value="0" var="total4"/>
                    <c:set value="0" var="total5"/>
                    <c:set value="0" var="total6"/>
                    <c:set value="0" var="total7"/>
                    <c:set value="0" var="total8"/>
                    <c:set value="0" var="total9"/>
                    <c:set value="0" var="total10"/>
                    <c:set value="0" var="total11"/>
                    <c:set value="0" var="total12"/>
                    <c:set value="0" var="total13"/>
                    <c:set value="0" var="total14"/>
                    <c:set value="0" var="total15"/>
                    
                  <c:forEach items="${summaryResult.deptSummary}" var="dept">
                  	<tr>
                  		<td>${dept.deptName}</td>
                  		<td class="table-part">
                  			<c:set var="project" value="0.00"/>
                  			<c:if test="${not empty dept.businessIncome}">
                  				<c:set value="${dept.businessIncome.projectConsumedAmt }" var="project"></c:set>
                  			</c:if>
                  			<c:choose>
                  				<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  					${project/10000}
                  				</c:when>
                  				<c:otherwise>
                  					${project}
                  				</c:otherwise>
                  			</c:choose>
                  		</td>                  	
                  		<td>
                  			<c:set var="goods" value="0.00"/>
                  			<c:if test="${not empty dept.businessIncome}">
                  				<c:set value="${dept.businessIncome.goodsConsumedAmt }" var="goods"></c:set>
                  			</c:if>
                  			<c:choose>
                  				<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  					${goods/10000}
                  				</c:when>
                  				<c:otherwise>
                  					${goods}
                  				</c:otherwise>
                  			</c:choose>
                  		</td>
                  		<td class="table-part1">
                  			<c:set var="IncomeSummary" value="0.00"/>
                  			<c:if test="${not empty dept.businessIncome}">
                  				<c:set value="${dept.businessIncome.totalAmt }" var="IncomeSummary"></c:set>
                  			</c:if>
                  			<c:choose>
                  				<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  					${IncomeSummary/10000}
                  				</c:when>
                  				<c:otherwise>
                  					${IncomeSummary}
                  				</c:otherwise>
                  			</c:choose>
                  		</td>
                  		<td class="table-part">
                  			<c:set var="openCardVlue" value="0.00"/>
                  			<c:if test="${not empty dept.chargedIncome}">
                  				<c:set value="${dept.chargedIncome.openCardAmt }" var="openCardVlue"></c:set>
                  			</c:if>
                  			<c:choose>
                  				<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  					${openCardVlue/10000}
                  				</c:when>
                  				<c:otherwise>
                  					${openCardVlue}
                  				</c:otherwise>
                  			</c:choose>
                  		</td>
                  		<td>
                  		<c:set var="chargedVlue" value="0.00"/>
                  		<c:if test="${not empty dept.chargedIncome}">
                  			<c:set value="${dept.chargedIncome.chargedAmt }" var="chargedVlue"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${chargedVlue/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${chargedVlue}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>
                  		<td>
                  		<c:set var="upgradeVlue" value="0.00"/>
                  		<c:if test="${not empty dept.chargedIncome}">
                  			<c:set value="${dept.chargedIncome.upgradeAmt }" var="upgradeVlue"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${upgradeVlue/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${upgradeVlue}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>
                  		<td>
                  		<c:set var="comboDiscount" value="0.00"/>
                  		<c:if test="${not empty dept.chargedIncome}">
                  			<c:set value="${dept.chargedIncome.comboAmt }" var="comboDiscount"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${comboDiscount/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${comboDiscount}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>
                  		<td class="table-part1">
                  		<c:set var="chargedSummary" value="0.00"/>
                  		<c:if test="${not empty dept.chargedIncome}">
                  			<c:set value="${dept.chargedIncome.totalAmt }" var="chargedSummary"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${chargedSummary/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${chargedSummary}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>
                  		<td class="table-part">
                  		<c:set var="gift" value="0.00"/>
                  		<c:if test="${not empty dept.businessDiscount}">
                  			<c:set value="${dept.businessDiscount.giftAmt }" var="gift"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${gift/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${gift}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>
                  		<td>
                  		<c:set var="coupon" value="0.00"/>
                  		<c:if test="${not empty dept.businessDiscount}">
                  			<c:set value="${dept.businessDiscount.couponAmt }" var="coupon"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${coupon/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${coupon}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>                  		
                  		<td>
                  		<c:set var="appointOff" value="0.00"/>
                  		<c:if test="${not empty dept.businessDiscount}">
                  			<c:set value="${dept.businessDiscount.appointAmt }" var="appointOff"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${appointOff/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${appointOff}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>
                  		<td class="table-part1">
                  		<c:set var="expenseSummary" value="0.00"/>
                  		<c:if test="${not empty dept.businessDiscount}">
                  			<c:set value="${dept.businessDiscount.totalAmt }" var="expenseSummary"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${expenseSummary/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${expenseSummary}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>
                  		<td class="table-part">
                  		<c:set var="comboE" value="0.00"/>
                  		<c:if test="${not empty dept.businessDiscount}">
                  			<c:set value="${dept.businessDiscount.comboAmt }" var="comboE"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${comboE/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${comboE}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>
                  		<td class="table-part">
                  		<c:set var="cardConsumed" value="0.00"/>
                  		<c:if test="${not empty dept.cardConsumedAmt}">
                  			<c:set value="${dept.cardConsumedAmt }" var="cardConsumed"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${cardConsumed/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${cardConsumed}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>
                  		<td class="table-part">
                  		<c:set var="realIncome" value="0.00"/>
                  		<c:if test="${not empty dept.realIncomes}">
                  			<c:set value="${dept.realIncomes }" var="realIncome"></c:set>
                  		</c:if>
                  		<c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				${realIncome/10000}
                  			</c:when>
                  			<c:otherwise>
                  				${realIncome}
                  			</c:otherwise>
                  		</c:choose>
                  		</td>                  		
                  		<c:set var="total1" value="${total1+project}"/>
                  		<c:set var="total2" value="${total2+comboDiscount}"></c:set>
                  		<c:set var="total3" value="${total3+goods}"/>
                  		<c:set var="total4" value="${total4+IncomeSummary}"/>
                  		<c:set var="total5" value="${total5+openCardVlue}"/>
                  		<c:set var="total6" value="${total6+chargedVlue}"/>
                  		<c:set var="total7" value="${total7+upgradeVlue}"/>
                  		<c:set var="total8" value="${total8+chargedSummary}"/>
                  		<c:set var="total9" value="${total9+gift}"/>
                  		<c:set var="total10" value="${total10+coupon}"/>               		
                  		<c:set var="total12" value="${total12+appointOff}"/>
                  		<c:set var="total13" value="${total13+expenseSummary}"/>
                  		<c:set var="total11" value="${total11+comboE}"/>
                  		<c:set var="total14" value="${total14+cardConsumed}"/>
                  		<c:set var="total15" value="${total15+realIncome}"/>
                  	</tr>
                  </c:forEach>
                  </tbody>
                <tfoot>
                <tr class="zhanbi-tr">
                    <td >汇总</td>
                    <td class="table-part">
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total1/10000}" pattern="##.##" minFractionDigits="2"  var="stotal1" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total1}" pattern="##.##" minFractionDigits="2"  var="stotal1" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal1}
                    </td>
                    
                    <td >
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total3/10000}" pattern="##.##" minFractionDigits="2" var="stotal3" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total3}" pattern="##.##" minFractionDigits="2" var="stotal3" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal3}
                    </td>
                    
                    
                    <td class="table-part1 hz-weight">
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total4/10000 }" pattern="##.##" minFractionDigits="2" var="stotal4"/>
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total4 }" pattern="##.##" minFractionDigits="2" var="stotal4"/>
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal4}
                    </td>
                    
                    
                    <td class="table-part">
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total5/10000}" pattern="##.##" minFractionDigits="2" var="stotal5" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total5}" pattern="##.##" minFractionDigits="2" var="stotal5" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal5}
                    </td>
                    
                    <td>
                   	<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total6/10000}" pattern="##.##" minFractionDigits="2" var="stotal6" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total6}" pattern="##.##" minFractionDigits="2" var="stotal6" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal6}
                    </td>                    
                    
                    <td>
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total7/10000 }" pattern="##.##" minFractionDigits="2" var="stotal7"/>
                  		</c:when>
                  		<c:otherwise>
	                  		<fmt:formatNumber value="${total7 }" pattern="##.##" minFractionDigits="2" var="stotal7"/>
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal7}
                    </td>
                    
                    <td>
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total2/10000 }" pattern="##.##" minFractionDigits="2" var="stotal2"/>
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total2 }" pattern="##.##" minFractionDigits="2" var="stotal2"/>
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal2}
                    </td>                  
                    
                    <td class="table-part1 hz-weight">
                   	<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total8/10000}" pattern="##.##" minFractionDigits="2" var="stotal8" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total8}" pattern="##.##" minFractionDigits="2" var="stotal8" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal8}
                    </td>
                    
                    
                    <td class="table-part">
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total9/10000}" pattern="##.##" minFractionDigits="2" var="stotal9" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total9}" pattern="##.##" minFractionDigits="2" var="stotal9" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal9}
                    </td>
                    
                    <td>
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total10/10000}" pattern="##.##" minFractionDigits="2" var="stotal10" />
                  		</c:when>
                  		<c:otherwise>
                  		<fmt:formatNumber value="${total10}" pattern="##.##" minFractionDigits="2" var="stotal10" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal10}
                    </td>
                                        
                    
                    <td>
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total12/10000}" pattern="##.##" minFractionDigits="2" var="stotal12" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total12}" pattern="##.##" minFractionDigits="2" var="stotal12" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal12}
                    </td>
                    
                    <td class="table-part1 hz-weight">
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total13/10000}" pattern="##.##" minFractionDigits="2" var="stotal13" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total13}" pattern="##.##" minFractionDigits="2" var="stotal13" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal13}
                    </td>
                    
                    
                    <td class="table-part">
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total11/10000}" pattern="##.##" minFractionDigits="2" var="stotal11" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total11}" pattern="##.##" minFractionDigits="2" var="stotal11" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal11}
                    </td>
                    
                    <fmt:formatNumber value="${total14}" pattern="##.##" minFractionDigits="2" var="stotal14" />
                    <td class="table-part hz-weight">
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total14/10000}" pattern="##.##" minFractionDigits="2" var="stotal14" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total14}" pattern="##.##" minFractionDigits="2" var="stotal14" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal14}
                    </td>
                    
                    
                    <td class="table-part hz-weight">
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${total15/10000}" pattern="##.##" minFractionDigits="2" var="stotal15" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${total15}" pattern="##.##" minFractionDigits="2" var="stotal15" />
                  		</c:otherwise>
                  	</c:choose>
                  	${stotal5}
                    </td>
                </tr>
                <tr class="huizong-tr dep-bottom-botrder">               
                    <td>占比</td>
                    <td class="table-part"><c:set value="0" var="rate1"/><c:if test="${total4 > 0 }"><c:set value="${total1 / total4 * 100 }" var="rate1"/></c:if><fmt:formatNumber value="${rate1}" pattern="##.##" minFractionDigits="2" var="sRate1" />${sRate1 }%</td>
                    <td><c:set value="0" var="rate2"/><c:if test="${total4 > 0 }"><c:set value="${total3 / total4 * 100 }" var="rate2"/></c:if><fmt:formatNumber value="${rate2}" pattern="##.##" minFractionDigits="2" var="sRate2" />${sRate2 }%</td>                    
                    <td><c:set value="0.00" var="t1"/><c:if test="${sRate1+sRate2>0}"><c:set value="100" var="t1"/></c:if>${t1 }%</td>
                    
                    <td><c:set value="0" var="rate4"/><c:if test="${total8 > 0 }"><c:set value="${total5 / total8 * 100 }" var="rate4"/></c:if><fmt:formatNumber value="${rate4}" pattern="##.##" minFractionDigits="2" var="sRate4" />${sRate4 }%</td>
                    <td><c:set value="0" var="rate5"/><c:if test="${total8 > 0 }"><c:set value="${total6 / total8 * 100 }" var="rate5"/></c:if><fmt:formatNumber value="${rate5}" pattern="##.##" minFractionDigits="2" var="sRate5" />${sRate5 }%</td>
                    <td><c:set value="0" var="rate6"/><c:if test="${total8 > 0 }"><c:set value="${total7 / total8 * 100 }" var="rate6"/></c:if><fmt:formatNumber value="${rate6}" pattern="##.##" minFractionDigits="2" var="sRate6" />${sRate6 }%</td>
                    <td><c:set value="0" var="rate12"/><c:if test="${total8 > 0 }"><c:set value="${total2 / total8 * 100 }" var="rate12"/></c:if><fmt:formatNumber value="${rate12}" pattern="##.##" minFractionDigits="2" var="sRate12" />${sRate12 }%</td>
                    <td><c:set value="0.00" var="t2"/><c:if test="${sRate4+sRate5+sRate6+sRate12>0}"><c:set value="100" var="t2"/></c:if>${t2 }%</td>
                    <td class="table-part"><c:set value="0" var="rate8"/><c:if test="${total13 > 0 }"><c:set value="${total9 / total13 * 100 }" var="rate8"/></c:if><fmt:formatNumber value="${rate8}" pattern="##.##" minFractionDigits="2" var="sRate8" />${sRate8 }%</td>
                    
                    <td><c:set value="0" var="rate9"/><c:if test="${total13 > 0 }"><c:set value="${total10 / total13 * 100 }" var="rate9"/></c:if><fmt:formatNumber value="${rate9}" pattern="##.##" minFractionDigits="2" var="sRate9" />${sRate9 }%</td>                    
                    <td><c:set value="0" var="rate11"/><c:if test="${total13 > 0 }"><c:set value="${total12 / total13 * 100 }" var="rate11"/></c:if><fmt:formatNumber value="${rate11}" pattern="##.##" minFractionDigits="2" var="sRate11" />${sRate11 }%</td>
                    <td><c:set value="0.00" var="t4"/><c:if test="${sRate8+sRate9+sRate10+sRate11>0}"><c:set value="100" var="t4"/></c:if>${t4 }%</td>                    
                    
                    <td class="table-part"></td>
                    <td class="table-part"></td>
                    <td class="table-part"></td>
                </tr>
                </tfoot>
            </table>
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10" onclick ="exportTable('export')">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span >导出</span>
                        </button>
                        <button class="btn ml10" onclick="printTable('export')">
                            <span >打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
        </div>

        <c:if test="${not empty summaryResult.dateType and (summaryResult.dateType == 2 or summaryResult.dateType == 3) }">
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店营业汇总日趋势</span>
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

            <table class="table dep-border-table table-bordered table-striped">
                <thead>
                <tr>
                    <th>日期</th>
                    <th>营业收入</th>
                    <th>营业扣减</th>
                    <th>营业实收</th>
                </tr>
                </thead>
                  <tbody>

                    <c:forEach var="dateSumEntry" items="${summaryResult.trends }">
                    <tr>
                        <td>${dateSumEntry.date }</td>
                        <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${dateSumEntry.incomes/10000}" pattern="##.##" minFractionDigits="2" var="sincome" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${dateSumEntry.incomes}" pattern="##.##" minFractionDigits="2" var="sincome" />
                  		</c:otherwise>
                  		</c:choose>
                  		<td>${sincome}</td>
                  		
                  		<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${dateSumEntry.expenses/10000}" pattern="##.##" minFractionDigits="2" var="sexpenses" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${dateSumEntry.expenses}" pattern="##.##" minFractionDigits="2" var="sexpenses" />
                  		</c:otherwise>
                  		</c:choose>
                  		<td>${sexpenses }</td>
                  		
                  		<c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${dateSumEntry.realIncomes/10000}" pattern="##.##" minFractionDigits="2" var="samount" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${dateSumEntry.realIncomes}" pattern="##.##" minFractionDigits="2" var="samount" />
                  		</c:otherwise>
                  		</c:choose>
                  		<td>${samount }</td>
                    </tr>
                    </c:forEach>
                  </tbody>
                <tfoot>
                <tr class="huizong-tr dep-bottom-botrder">
                    <td>汇总</td>
                    <fmt:formatNumber value="${summaryResult.businessIncomes/10000}" pattern="##.##" minFractionDigits="2" var="stotalIncome" />
                  	<td>${stotalIncome }</td>
                    
                    <fmt:formatNumber value="${summaryResult.businessExpense/10000}" pattern="##.##" minFractionDigits="2" var="stotalExpenses" />
                  	<td>${stotalExpenses }</td>

                    <fmt:formatNumber value="${summaryResult.businessRealIncome/10000}" pattern="##.##" minFractionDigits="2" var="stotalAmount" />
                  	<td>${stotalAmount }</td>
                </tr>
                </tfoot>
            </table>
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span >导出</span>
                        </button>
                        <button class="btn ml10">
                            <span>打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
        </div>
        </c:if>

        <c:if test="${not empty summaryResult.dateType and summaryResult.dateType == 4 }">
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店营业汇总月趋势</span>
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

            <table class="table dep-border-table table-bordered table-striped">
                <thead>
                <tr>
                    <th>月份</th>
                    <th>营业收入</th>
                    <th>营业扣减</th>
                    <th>营业实收</th>
                </tr>
                </thead>
                  <tbody>
                    <c:forEach var="dateSumEntry" items="${summaryResult.trends }">
                    <tr>
                        <td>${dateSumEntry.date }</td>
                        <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${dateSumEntry.incomes/10000}" pattern="##.##" minFractionDigits="2" var="sincome" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${dateSumEntry.incomes}" pattern="##.##" minFractionDigits="2" var="sincome" />
                  		</c:otherwise>
                  		</c:choose>
                        <td>${sincome }</td>
                        
                        <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${dateSumEntry.expenses/10000}" pattern="##.##" minFractionDigits="2" var="sexpenses" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${dateSumEntry.expenses}" pattern="##.##" minFractionDigits="2" var="sexpenses" />
                  		</c:otherwise>
                  		</c:choose>
                        <td>${sexpenses }</td>
                        
                        <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${dateSumEntry.realIncomes/10000}" pattern="##.##" minFractionDigits="2" var="samount" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${dateSumEntry.realIncomes}" pattern="##.##" minFractionDigits="2" var="samount" />
                  		</c:otherwise>
                  		</c:choose>
                        <td>${samount }</td>
                    </tr>
                    </c:forEach>
                  </tbody>
                <tfoot>
                <tr class="huizong-tr dep-bottom-botrder">
                    <td>汇总</td>
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${summaryResult.businessIncomes/10000}" pattern="##.##" minFractionDigits="2" var="stotalIncome" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${summaryResult.businessIncomes}" pattern="##.##" minFractionDigits="2" var="stotalIncome" />
                  		</c:otherwise>
                  		</c:choose>
                    
                    <td>${stotalIncome }</td>
                    
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${summaryResult.businessExpense/10000}" pattern="##.##" minFractionDigits="2" var="stotalExpenses" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${summaryResult.businessExpense}" pattern="##.##" minFractionDigits="2" var="stotalExpenses" />
                  		</c:otherwise>
                  		</c:choose>
                    
                    <td>${stotalExpenses }</td>
                    
                    <c:choose>
                  		<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  			<fmt:formatNumber value="${summaryResult.businessRealIncome/10000}" pattern="##.##" minFractionDigits="2" var="stotalAmount" />
                  		</c:when>
                  		<c:otherwise>
                  			<fmt:formatNumber value="${summaryResult.businessRealIncome/10000}" pattern="##.##" minFractionDigits="2" var="stotalAmount" />
                  		</c:otherwise>
                  		</c:choose>
                    
                    <td>${stotalAmount }</td>
                </tr>
                </tfoot>
            </table>
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <div class="s-btn-group fr">
                        <button class="btn ml10">
                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                            <span >导出</span>
                        </button>
                        <button class="btn ml10">
                            <span>打印</span>
                        </button>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
        </div>
        </c:if>

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
    });

    function initDay(){
    	var objBegin='${summaryResult.begin}';
    	objBegin=objBegin.replaceAll('-','/');
    	var objEnd='${summaryResult.end}';
    	objEnd=objEnd.replaceAll('-','/');
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
    	var colors = Highcharts.getOptions().colors;
        jQuery("#startDate").val(objBegin);
        jQuery("#endDate").val(objEnd);
        jQuery("#tableDate").html('${summaryResult.begin}-${summaryResult.end}');
        //jQuery("span[name='searchType']").html('日');
        jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '门店营业汇总(${dateType})'
            },
            subtitle: {
                text: sdate
            },
            xAxis: {
                categories: [
	          <c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus">
	          '${dept.deptName }'<c:if test="${not deptStatus.last }">,</c:if>
	          </c:forEach>
                ],
                crosshair: true
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
                    text: '金额 (元)'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y} 元</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
                        shadow: false,
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
            series: [{
                name: '营业收入',
                data: [<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus"><fmt:formatNumber value="${dept.incomes }" pattern="##.##" minFractionDigits="2" var="sincome" />${sincome}<c:if test="${not deptStatus.last }">, </c:if></c:forEach>]

            }, {
                name: '营业扣减',
                data: [<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus"><fmt:formatNumber value="${dept.expenses }" pattern="##.##" minFractionDigits="2" var="sexpenses" />${sexpenses}<c:if test="${not deptStatus.last }">, </c:if></c:forEach>]

            }, {
                name: '营业实收',
                data: [<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus"><fmt:formatNumber value="${dept.realIncomes }" pattern="##.##" minFractionDigits="2" var="samount" />${samount}<c:if test="${not deptStatus.last }">, </c:if></c:forEach>]

            }]
        });
        
      if(2 == date_type || 3 == date_type || 4 == date_type) {

          jQuery('#cash-day2').highcharts({
              chart: {
                  type: 'spline'
              },
              title: {
                  text: '门店营业汇总(${dateType2}走势)'
              },
              subtitle: {
                  text: sdate
              },
              xAxis: {
                  categories: [
          <c:forEach var="dateSumEntry" items="${summaryResult.trend.businessExpense }" varStatus="dateSumStatus">
          '${dateSumEntry.date}'<c:if test="${not dateSumStatus.last }">,</c:if>
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
                  headerFormat: '<span style="font-size:10px">&nbsp;{point.key}${dateType2}</span><table>',
                  pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                  '<td style="padding:0"><b>{point.y} 元</b></td></tr>',
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
              series: [{
                  name: '营业收入',
                  data: [
            <c:forEach var="dateSumEntry" items="${summaryResult.trend.incomes }" varStatus="dateSumStatus"><fmt:formatNumber value="${dateSumEntry.totalAmt }" pattern="##.##" minFractionDigits="2" var="sincome" />${sincome}<c:if test="${not dateSumStatus.last }">,</c:if></c:forEach>
                         ]
              }, {
                  name: '营业扣减',
                  data: [
      <c:forEach var="dateSumEntry" items="${summaryResult.trend.businessExpense }" varStatus="dateSumStatus"><fmt:formatNumber value="${dateSumEntry.totalAmt }" pattern="##.##" minFractionDigits="2" var="sexpenses" />${sexpenses}<c:if test="${not dateSumStatus.last }">,</c:if></c:forEach>
                         ]
              }, {
                  name: '营业实收',
                  data: [
                         <c:forEach var="dateSumEntry" items="${summaryResult.trend.realIncomes }" varStatus="dateSumStatus"><fmt:formatNumber value="${dateSumEntry.totalAmt }" pattern="##.##" minFractionDigits="2" var="samount" />${samount}<c:if test="${not dateSumStatus.last }">,</c:if></c:forEach>
                         ]
              }]
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
    
    function changeStore(begin,end,dateType,storeId){
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
<script type="text/javascript" src="<%=basePath %>js/commodity/goodsInfo.js"></script>

    </div>
    <!--RIGHT PANEL结束 -->

   </div>
    <a href="" class="showmenu"></a>


</div><!--mainwrapper-->



</div>


    <!--RIGHT PANEL结束 -->


</div><!--mainwrapper-->
<form action="<%=basePath %>summary/view/summary" style="display:none;" id="summaryFrm" method="post">
<input type="hidden" name="begin" id="begin"/>
<input type="hidden" name="end" id="end"/>
<input type="hidden" name="storeId" id="storeId">
<input type="hidden" name="dateType" id="dateType"/>
</form>
</body>
</html>
