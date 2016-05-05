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

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>


      <div class="maincontent">
    <div class="contentinner">
        <div class="report-title">
            <c:set var="searchType" value="本<span name='searchType'>日</span>"/>
            <c:set var="searchType2" value="<span name='searchType'>日</span>"/>
            <c:choose>
                <c:when test="${empty summaryResult.dateType }">
                <c:set var="searchType" value="区间"/>
                <c:set var="searchType2" value="区间"/>
                </c:when>
                <c:otherwise>
                    <c:if test="${summaryResult.dateType ==  0}">
                    <c:set var="searchType" value="本<span name='searchType'>日</span>"/>
                    <c:set var="searchType2" value="<span name='searchType'>日</span>"/>
                    </c:if>
                    <c:if test="${summaryResult.dateType ==  1}">
                    <c:set var="searchType" value="本<span name='searchType'>周</span>"/>
                    <c:set var="searchType2" value="<span name='searchType'>周</span>"/>
                    </c:if>
                    <c:if test="${summaryResult.dateType ==  2 || summaryResult.dateType ==  3}">
                    <c:set var="searchType" value="本<span name='searchType'>月</span>"/>
                    <c:set var="searchType2" value="<span name='searchType'>月</span>"/>
                    </c:if>
                    <c:if test="${summaryResult.dateType ==  4}">
                    <c:set var="searchType" value="本<span name='searchType'>年</span>"/>
                    <c:set var="searchType2" value="<span name='searchType'>年</span>"/>
                    </c:if>
                </c:otherwise>
            </c:choose>
            <ul>                             
                <li>
                    <fmt:formatNumber value="${summaryResult.totalProjectAmt}" pattern="##.##" minFractionDigits="2" var="stotal1" />
                    <h1>${stotal1 }</h1>
                    <p>${searchType }项目现金</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.totalGoodsAmt}" pattern="##.##" minFractionDigits="2" var="stotal2" />
                    <h1>${stotal2 }</h1>
                    <p>${searchType }商品现金</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.totalComboAmt}" pattern="##.##" minFractionDigits="2" var="stotal3" />
                    <h1>${stotal3 }</h1>
                    <p>${searchType }套餐现金</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.openCardAmt}" pattern="##.##" minFractionDigits="2" var="stotal4" />
                    <h1>${stotal4 }</h1>
                    <p>${searchType }开卡现金</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.chargeCardAmt + summaryResult.upgradeAmt}" pattern="##.##" minFractionDigits="2" var="stotal5" />
                    <h1>${stotal5 }</h1>
                    <p>${searchType }充值/升级</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.totalCashAmt}" pattern="##.##" minFractionDigits="2" var="stotalRealCashAmount" />
                    <h1>${stotalRealCashAmount }</h1>
                    <p>${searchType }现金总收入</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.cashIncomeRate*100}" pattern="##.##" minFractionDigits="2" var="stotalRealCashIncrementRate" />
                    <h1>${stotalRealCashIncrementRate }%</h1>
                    <p>现金收入${searchType2 }增长</p>
                </li>
            </ul>
        </div>

        <div id="custom-toolbar" style="margin-bottom: 20px;" >
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
                <input type="text" class="datetimepicker input80" daysOffset="0" id="startDate" name="startDate"/>－
                <input type="text" class="datetimepicker input80" daysOffset="0" id="endDate" name="endDate"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="button-search btn" style="margin-left: -10px;" onclick="querySummary();">查询</button>
                <div class="select-target-report">
                    <span onclick="changeDate(0,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 0 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日</a></span>
                    <span onclick="changeDate(1,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 1 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">周</a></span>
                    <span onclick="changeDate(2,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月</a></span>
                    <span onclick="changeDate(3,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 3 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日趋势</a></span>
                    <span onclick="changeDate(4,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 4 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月趋势</a></span>
                    <!--<span class="report-category ml10"><a href="yingyehuizong-zongbu.html">总部汇总</a></span>-->
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="cash-day" style="width: 1050px;height:400px;<c:if test="${not empty summaryResult.dateType and (summaryResult.dateType == 3 or summaryResult.dateType == 4) }">display:none;</c:if>"></div><!--cash-day-->
            <div id="cash-day2" style="width: 1050px;height:400px;<c:if test="${empty summaryResult.dateType or summaryResult.dateType == 0 or summaryResult.dateType == 1 or summaryResult.dateType == 2 }">display:none;</c:if>"></div><!--cash-day-->
        </div>


        <div class="widgetcontent">
            <div style="margin-top: 20px;">
                <div class="fl" style="width: 49%">
                    <div class="more-toolbar" >
                        <div class="table-toolbar">
                            <span class="font-size-16 btn-color mr10">现金收入分布</span>
                            <span class="fr">时间：<span name="tableDate">${summaryResult.begin }-${summaryResult.end }</span> 
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
                        <thead>
                        <tr>
                            <th>部门</th>
                            <th>项目</th>
                            <th>商品</th>
                            <th>套餐</th>
                            <th>开卡</th>
                            <th>充值/升级</th>
                            <th>汇总</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:set value="0" var="total1"/>
                        <c:set value="0" var="total2"/>
                        <c:set value="0" var="total3"/>
                        <c:set value="0" var="total4"/>
                        <c:set value="0" var="total5"/>
                        <c:forEach items="${summaryResult.deptSummary }" var="dept">                                           
                        <tr>
                            <td>${dept.deptName }</td>
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${dept.deptDto.projectAmt/10000}" pattern="##.##" minFractionDigits="2" var="so1" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${dept.deptDto.projectAmt}" pattern="##.##" minFractionDigits="2" var="so1" />
                  			</c:otherwise>
                  			</c:choose>
                            <td>${so1 }</td>
                            
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${dept.deptDto.goodsAmt/10000}" pattern="##.##" minFractionDigits="2" var="so2" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${dept.deptDto.goodsAmt}" pattern="##.##" minFractionDigits="2" var="so2" />
                  			</c:otherwise>
                  			</c:choose>
                            <td>${so2 }</td>
                            
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${dept.deptDto.comboAmt/10000}" pattern="##.##" minFractionDigits="2" var="so3" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${dept.deptDto.comboAmt}" pattern="##.##" minFractionDigits="2" var="so3" />
                  			</c:otherwise>
                  			</c:choose>
                            <td>${so3 }</td>
                            
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${dept.deptDto.openCardAmt/10000}" pattern="##.##" minFractionDigits="2" var="so4" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${dept.deptDto.openCardAmt}" pattern="##.##" minFractionDigits="2" var="so4" />
                  			</c:otherwise>
                  			</c:choose>
                            <td>${so4 }</td>
                            
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${(dept.deptDto.chargedAmt + dept.deptDto.upgradeAmt)/10000}" pattern="##.##" minFractionDigits="2" var="so5" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${dept.deptDto.chargedAmt + dept.deptDto.upgradeAmt}" pattern="##.##" minFractionDigits="2" var="so5" />
                  			</c:otherwise>
                  			</c:choose>
                            <td>${so5 }</td>
                            
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${dept.deptDto.totalAmt/10000 }" pattern="##.##" minFractionDigits="2" var="so7" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${dept.deptDto.totalAmt }" pattern="##.##" minFractionDigits="2" var="so7" />
                  			</c:otherwise>
                  			</c:choose>
                            <td class="table-part1">${so7}</td>
                            <c:set var="total1" value="${total1+dept.deptDto.projectAmt }"></c:set>
                            <c:set var="total2" value="${total2+dept.deptDto.goodsAmt }"></c:set>
                            <c:set var="total3" value="${total3+dept.deptDto.comboAmt }"></c:set>
                            <c:set var="total4" value="${total4+dept.deptDto.openCardAmt }"></c:set>
                            <c:set var="total5" value="${total5+dept.deptDto.chargedAmt + dept.deptDto.upgradeAmt }"></c:set>
                        </tr>
                        </c:forEach>
                        <tr class="zhanbi-tr">
                            <td>汇总</td>
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${total1/10000}" pattern="##.##" minFractionDigits="2" var="stotal1" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${total1}" pattern="##.##" minFractionDigits="2" var="stotal1" />
                  			</c:otherwise>
                  			</c:choose>
                            
                            <td>${stotal1 }</td>
                            
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${total2/10000}" pattern="##.##" minFractionDigits="2" var="stotal2" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${total2}" pattern="##.##" minFractionDigits="2" var="stotal2" />
                  			</c:otherwise>
                  			</c:choose>
                            
                            <td>${stotal2 }</td>
                            
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${total3/10000}" pattern="##.##" minFractionDigits="2" var="stotal3" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${total3}" pattern="##.##" minFractionDigits="2" var="stotal3" />
                  			</c:otherwise>
                  			</c:choose>
                            
                            <td>${stotal3 }</td>
                            
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${total4/10000}" pattern="##.##" minFractionDigits="2" var="stotal4" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${total4}" pattern="##.##" minFractionDigits="2" var="stotal4" />
                  			</c:otherwise>
                  			</c:choose>
                            
                            <td>${stotal4 }</td>
                            
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${total5/10000}" pattern="##.##" minFractionDigits="2" var="stotal5" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${total5}" pattern="##.##" minFractionDigits="2" var="stotal5" />
                  			</c:otherwise>
                  			</c:choose>
                            
                            <td>${stotal5 }</td>
                            
                            <c:set value="${total1 + total2 + total3 + total4 + total5}" var="total7"></c:set>
                            <c:choose>
                  			<c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType || 3 == summaryResult.dateType || 4 == summaryResult.dateType}">
                  				<fmt:formatNumber value="${(total1 + total2 + total3 + total4 + total5)/10000}" pattern="##.##" minFractionDigits="2" var="stotal7" />
                  			</c:when>
                  			<c:otherwise>
                  				<fmt:formatNumber value="${total1 + total2 + total3 + total4 + total5}" pattern="##.##" minFractionDigits="2" var="stotal7" />
                  			</c:otherwise>
                  			</c:choose>
                            <td class="table-part1">${stotal7 }</td>
                        </tr>
                        <tr class="huizong-tr">
                            <td>占比</td>
                            <td><c:set value="0" var="rate1"/><c:if test="${total7 > 0 }"><c:set value="${total1 / total7 * 100 }" var="rate1"/></c:if><fmt:formatNumber value="${rate1}" pattern="##.#" minFractionDigits="1" var="sRate1" />${sRate1 }%</td>
                            <td><c:set value="0" var="rate2"/><c:if test="${total7 > 0 }"><c:set value="${total2 / total7 * 100 }" var="rate2"/></c:if><fmt:formatNumber value="${rate2}" pattern="##.#" minFractionDigits="1" var="sRate2" />${sRate2 }%</td>
                            <td><c:set value="0" var="rate3"/><c:if test="${total7 > 0 }"><c:set value="${total3 / total7 * 100 }" var="rate3"/></c:if><fmt:formatNumber value="${rate3}" pattern="##.#" minFractionDigits="1" var="sRate3" />${sRate3 }%</td>
                            <td><c:set value="0" var="rate4"/><c:if test="${total7 > 0 }"><c:set value="${total4 / total7 * 100 }" var="rate4"/></c:if><fmt:formatNumber value="${rate4}" pattern="##.#" minFractionDigits="1" var="sRate4" />${sRate4 }%</td>
                            <td><c:set value="0" var="rate5"/><c:if test="${total7 > 0 }"><c:set value="${total5 / total7 * 100 }" var="rate5"/></c:if><fmt:formatNumber value="${rate5}" pattern="##.#" minFractionDigits="1" var="sRate5" />${sRate5 }%</td>
                            <td class="table-part1">&nbsp;</td>
                        </tr>
                        </tbody>
<!--                   <tfoot>
                        <tr>
                            <td colspan="13">
                                <div class="s-btn-group fr">

                                    <button class="btn ml10">
                                        <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp"/>
                                        <span >导出</span>
                                    </button>
                                    <button class="btn ml10">
                                        <span>打印</span>
                                    </button>
                                </div>
                            </td>
                        </tr>
                        </tfoot> -->
                    </table>
                    <div class="more-toolbar">
		                <div class="table-toolbar">
		                    <div class="s-btn-group fr">
		                        <button class="btn ml10"  onclick ="exportTable('export')">
		                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp">
		                            <span>导出</span>
		                        </button>
		                        <button class="btn ml10"  onclick ="printTable('export')">
		                            <span>打印</span>
		                        </button>
		                    </div>
		                </div>
		                <div class="clearfix"></div>
		            </div>
                </div>
                
            </div>
        </div>

        <div class="widgetcontent">
            <div>
                <div class="fl" style="width: 49%">
                    <div id="project-type-pie" style="min-width:450px;height:355px"></div>
                </div>
             <!--    <div class="fr" style="width: 49%">
                    <div id="cash-type-pie" style="min-width:450px;height:355px"></div>
                </div> -->
            </div>
            <div class="clearfix"></div>
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

        jQuery('#project-type-pie').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false
            },
            title: {
                text: '各项现金收入贡献占比'
            },
            subtitle: {
                text: sdate
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: true,
                        format: '<b>{point.name}</b>: {point.percentage:.1f} %',
                        style: {
                            color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                        }
                    }
                }
            },
            series: [{
                type: 'pie',
                name: '所占比重',
                innerSize: '50%',
                data: [
                    ['项目', ${summaryResult.totalProjectAmt}],
                    ['商品',  ${summaryResult.totalGoodsAmt}],
                    ['套餐', ${summaryResult.totalComboAmt}],
                    ['开卡',  ${summaryResult.openCardAmt}],
                    ['充值',  ${summaryResult.chargeCardAmt}],
                    ['升级',  ${summaryResult.upgradeAmt}],
                    {
                        y: 0.2,
                        dataLabels: {
                            enabled: false
                        }
                    }
                ]
            }]
        });
        
    });

    function initDay(){
    	var colors = Highcharts.getOptions().colors;
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
    	
      	jQuery("#startDate").val(objBegin);
        jQuery("#endDate").val(objEnd);
        jQuery("span[name='tableDate").html('${summaryResult.begin}-${summaryResult.end}');
        jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '现金收入(${dateType})'
            },
            subtitle: {
                text: sdate
            },
            xAxis: {
                categories: [
<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus">
'${dept.deptName }'<c:if test="${not deptStatus.last }">,</c:if>
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
                    text: '金额 (元)'
                },
                stackLabels: {
                    enabled: true,
                    style: {
                        fontWeight: 'bold',
                        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                    }
                }
            },
            legend: {
                align: 'right',
                x: -30,
                verticalAlign: 'top',
                y: 25,
                floating: true,
                backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
                borderColor: '#CCC',
                borderWidth: 1,
                shadow: false
            },
            tooltip: {
                formatter: function () {
                    return this.series.name + ': ' + this.y + ' 元<br/>' +
                            '总额: ' + this.point.stackTotal+' 元';
                }
            },
            plotOptions: {
                column: {
                    stacking: 'normal',
                    dataLabels: {
                        enabled: true,
                        color: '#000',
                        style: {
                            textShadow: 'none'
                        }
                    }
                }
            },
            series: [{
                name: '项目',
                data: [
<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus">
<fmt:formatNumber value="${dept.deptDto.projectAmt}" pattern="##.##" minFractionDigits="2" var="scashRealAmount" />${scashRealAmount }
<c:if test="${not deptStatus.last }">,</c:if>
</c:forEach>
                       ]
            }, {
                name: '商品',
                data: [
<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus">
<fmt:formatNumber value="${dept.deptDto.goodsAmt}" pattern="##.##" minFractionDigits="2" var="scashRealAmount" />${scashRealAmount }
<c:if test="${not deptStatus.last }">,</c:if>
</c:forEach>
                       ]
            }, {
                name: '套餐',
                data: [
<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus">
<fmt:formatNumber value="${dept.deptDto.comboAmt}" pattern="##.##" minFractionDigits="2" var="scashRealAmount" />${scashRealAmount }
<c:if test="${not deptStatus.last }">,</c:if>
</c:forEach>
                       ]
            }, {
                name: '开卡',
                data: [
<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus">
<fmt:formatNumber value="${dept.deptDto.openCardAmt}" pattern="##.##" minFractionDigits="2" var="scashRealAmount" />${scashRealAmount }
<c:if test="${not deptStatus.last }">,</c:if>
</c:forEach>
                       ]
            }, {
                name: '充值',
                data: [
<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus">
<fmt:formatNumber value="${dept.deptDto.chargedAmt}" pattern="##.##" minFractionDigits="2" var="scashRealAmount" />${scashRealAmount }
<c:if test="${not deptStatus.last }">,</c:if>
</c:forEach>

                       ]
            }, {
                name: '升级',
                data: [
<c:forEach items="${summaryResult.deptSummary }" var="dept" varStatus="deptStatus">
<fmt:formatNumber value="${dept.deptDto.upgradeAmt}" pattern="##.##" minFractionDigits="2" var="scashRealAmount" />${scashRealAmount }
<c:if test="${not deptStatus.last }">,</c:if>
</c:forEach>
                       ]
            }]
        });
        

        if(2 == date_type || 3 == date_type || 4 == date_type) {

            jQuery('#cash-day2').highcharts({
                chart: {
                    type: 'spline'
                },
                title: {
                    text: '现金收入(${dateType2}走势)'
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
                       		<c:forEach items="${trend.trendDeptData}" var="dept" varStatus="deptStatus">
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
					<c:forEach items="${summaryResult.trends}" var="trend" varStatus="trendStatus">
						{
							name:['${summaryResult.typeForName[trend.orderType]}'],
							data:[<c:forEach items="${trend.trendDeptData}" var="dateData" varStatus="dateStatus">
									${dateData.deptSum}
									<c:if test="${not dateStatus.last}">,</c:if>
								  </c:forEach>                     			  
							]
						}
						<c:if test="${not trendStatus.last}">,</c:if>
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
<form action="<%=basePath %>cashreceipts/view/cashreceipts" style="display:none;" id="summaryFrm" method="post">
<input type="hidden" name="begin" id="begin"/>
<input type="hidden" name="end" id="end"/>
<input type="hidden" name="storeId" id="storeId">
<input type="hidden" name="dateType" id="dateType"/>
</form>
</body>
</html>
