<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>

<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->

    <div class="rightpanel" style="margin-left: 200px;">
    <%@ include file="/top.jsp" %>
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
                    <fmt:formatNumber value="${summaryResult.totalProjectAmount}" pattern="##.##" minFractionDigits="2" var="stotalProjectAmount" />
                    <h1>${stotalProjectAmount }</h1>
                    <p>${searchType }项目划卡</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.totalPackageAmount}" pattern="##.##" minFractionDigits="2" var="stotalPackageAmount" />
                    <h1>${stotalPackageAmount }</h1>
                    <p>${searchType }套餐划卡</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.totalCommoditysAmount}" pattern="##.##" minFractionDigits="2" var="stotalCommoditysAmount" />
                    <h1>${stotalCommoditysAmount }</h1>
                    <p>${searchType }商品划卡</p>
                </li>
                <li>
                    <fmt:formatNumber value="${summaryResult.totalCommoditysAmount + summaryResult.totalPackageAmount + summaryResult.totalCommoditysAmount}" pattern="##.##" minFractionDigits="2" var="stotalAmount" />
                    <h1>${stotalAmount }</h1>
                    <p>${searchType }划卡总额</p>
                </li>
                <li>
                    <h1 class="red">${summaryResult.ratio }</h1>
                    <p>划卡总额${searchType2 }增长</p>
                </li>
            </ul>
        </div>

        <div id="custom-toolbar"  style="margin-bottom: 20px;">
            <div class="table-toolbar" style="margin-bottom: 20px;">
                <span class="mr10">日期</span>
                <input type="text" class="datetimepicker input80" daysOffset="0" id="startDate" name="startDate" value="${summaryResult.begin }"/>－
                <input type="text" class="datetimepicker input80" daysOffset="0" id="endDate" name="endDate" value="${summaryResult.end }"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="button-search btn" style="margin-left: -10px;" onclick="querySummary();">查询</button>
                <div class="select-target-report">
                    <span onclick="changeDate(0,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 0 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日</a></span>
                    <span onclick="changeDate(1,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 1 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">周</a></span>
                    <span onclick="changeDate(2,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 2 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月</a></span>
                    <span onclick="changeDate(3,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 3 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日趋势</a></span>
                    <span onclick="changeDate(4,this);" class="<c:choose><c:when test="${not empty summaryResult.dateType and 4 == summaryResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月趋势</a></span>
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="cash-day" style="width: 1050px;height:400px;<c:if test="${not empty summaryResult.dateType and (summaryResult.dateType == 3 or summaryResult.dateType == 4) }">display:none;</c:if>"></div><!--cash-day-->
            <div id="cash-day2" style="width: 1050px;height:400px; <c:if test="${empty summaryResult.dateType or summaryResult.dateType == 0 or summaryResult.dateType == 1 or summaryResult.dateType == 2 }">display:none;</c:if>"></div><!--cash-day-->
        </div>

        <div class="widgetcontent">
            <div style="margin-top: 20px;">
                <!--<div class="fl" style="width: 49%">-->
                    <div class="more-toolbar" >
                        <div class="table-toolbar">
                            <span class="font-size-16 btn-color mr10">储值卡消费分布</span>
                            <span class="fr">时间：<span name="tableDate">${summaryResult.begin }-${summaryResult.end }</span> 单位：元</span>
                        </div>
                        <div class="clearfix"></div>
                    </div><!--more-toolbar-->

                    <table class="table table-bordered dep-border-table table-dash">
                        <thead>
                        <tr>
                            <th>部门</th>
                            <th>项目</th>
                            <th>商品</th>
                            <th>套餐</th>
                            <th class="table-part2">划卡金额汇总</th>
                            <c:forEach var="memberLevel" items="${summaryResult.memberLevels }">
                            <th>${memberLevel.levelName }消费人数</th>
                            </c:forEach>
                            <th>用卡人数汇总</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:set value="0" var="total1"/>
                      <c:set value="0" var="total2"/>
                      <c:set value="0" var="total3"/>
                      <c:set value="0" var="total7"/>

                        <c:forEach items="${summaryResult.deptInfos }" var="dept">

                        <c:set value="0" var="o1"/>
                      <c:set value="0" var="o2"/>
                      <c:set value="0" var="o3"/>

                      <c:forEach items="${summaryResult.deptOrderTypeSums[dept.deptId] }" var="typeEntry">
                          <c:if test="${typeEntry.key == 1 }"><c:set value="${typeEntry.value.amount }" var="o1"/>    <c:set value="${total1 + typeEntry.value.amount }" var="total1"/></c:if>
                          <c:if test="${typeEntry.key == 2 }"><c:set value="${typeEntry.value.amount }" var="o2"/>    <c:set value="${total2 + typeEntry.value.amount }" var="total2"/></c:if>
                          <c:if test="${typeEntry.key == 3 }"><c:set value="${typeEntry.value.amount }" var="o3"/>    <c:set value="${total3 + typeEntry.value.amount }" var="total3"/></c:if>
                          <c:set value="${o7 + typeEntry.value.amount }" var="o7"/>
                      </c:forEach>
                      <c:set value="${total7 + o7 }" var="total7"/>
                        <tr>
                            <td>${dept.deptName }</td>
                            <fmt:formatNumber value="${o1}" pattern="##.##" minFractionDigits="2" var="so1" />
                            <td>${so1 }</td>
                            <fmt:formatNumber value="${o2}" pattern="##.##" minFractionDigits="2" var="so2" />
                            <td>${so2 }</td>
                            <fmt:formatNumber value="${o3}" pattern="##.##" minFractionDigits="2" var="so3" />
                            <td>${so3 }</td>
                            <fmt:formatNumber value="${o7}" pattern="##.##" minFractionDigits="2" var="so7" />
                            <td class="table-part1 table-part2">${so7 }</td>
                            <c:set value="0" var="totalMemberCnt"/>
                            <c:forEach var="deptCardTypeSumEntry" items="${summaryResult.deptCardTypeSums[dept.deptId] }">
                            <td>${deptCardTypeSumEntry.value.memberCnt }</td>
                            <c:set value="${deptCardTypeSumEntry.value.memberCnt + totalMemberCnt }" var="totalMemberCnt"/>
                            </c:forEach>
                            <td class="table-part1">${totalMemberCnt }</td>
                        </tr>
                        </c:forEach>
                        <tr class="zhanbi-tr">
                            <td>汇总</td>
                            <fmt:formatNumber value="${total1}" pattern="##.##" minFractionDigits="2" var="stotal1" />
                            <td>${stotal1 }</td>
                            <fmt:formatNumber value="${total2}" pattern="##.##" minFractionDigits="2" var="stotal2" />
                            <td>${stotal2 }</td>
                            <fmt:formatNumber value="${total3}" pattern="##.##" minFractionDigits="2" var="stotal3" />
                            <td>${stotal3 }</td>
                            <fmt:formatNumber value="${total7}" pattern="##.##" minFractionDigits="2" var="stotal7" />
                            <td class="table-part1 table-part2">${stotal7 }</td>
                            <c:set value="0" var="allTotalMemberCnt"/>
                            <c:forEach var="memberLevel" items="${summaryResult.memberLevels }">
                            <c:set value="0" var="totalMemberCnt"/>
                            <c:forEach var="dept" items="${summaryResult.deptInfos }">
                            <c:set value="${totalMemberCnt + summaryResult.deptCardTypeSums[dept.deptId][memberLevel.levelId].memberCnt }" var="totalMemberCnt"/>
                            </c:forEach>
                            <td>${totalMemberCnt }</td>
                            <c:set value="${totalMemberCnt + allTotalMemberCnt }" var="allTotalMemberCnt"/>
                            </c:forEach>
                            <td class="table-part1">${ allTotalMemberCnt}</td>
                        </tr>
                        <tr class="huizong-tr">
                            <td>占比</td>
                            <c:set value="0" var="rate"/>
                           <c:if test="${o7 > 0 }">
                           <c:set value="${ o1 / o7 * 100 }" var="rate"/>
                           </c:if>
                           <fmt:formatNumber value="${rate}" pattern="##.#" minFractionDigits="1" var="sRate" />
                            <td>${sRate }%</td>

                            <c:set value="0" var="rate"/>
                           <c:if test="${o7 > 0 }">
                           <c:set value="${ o2 / o7 * 100 }" var="rate"/>
                           </c:if>
                           <fmt:formatNumber value="${rate}" pattern="##.#" minFractionDigits="1" var="sRate" />
                            <td>${sRate }%</td>

                            <c:set value="0" var="rate"/>
                           <c:if test="${o7 > 0 }">
                           <c:set value="${ o3 / o7 * 100 }" var="rate"/>
                           </c:if>
                           <fmt:formatNumber value="${rate}" pattern="##.#" minFractionDigits="1" var="sRate" />
                            <td>${sRate }%</td>

                            <td class="table-part1 table-part2">&nbsp;</td>

                            <c:forEach var="memberLevel" items="${summaryResult.memberLevels }">
                            <c:set value="0" var="totalMemberCnt"/>
                            <c:forEach var="dept" items="${summaryResult.deptInfos }">
                            <c:set value="${totalMemberCnt + summaryResult.deptCardTypeSums[dept.deptId][memberLevel.levelId].memberCnt }" var="totalMemberCnt"/>
                            </c:forEach>

                            <c:set value="0" var="rate"/>
                           <c:if test="${allTotalMemberCnt > 0 }">
                           <c:set value="${ totalMemberCnt / allTotalMemberCnt * 100 }" var="rate"/>
                           </c:if>
                           <fmt:formatNumber value="${rate}" pattern="##.#" minFractionDigits="1" var="sRate" />
                            <td>${sRate }%</td>
                            </c:forEach>

                            <td class="table-part1">&nbsp;</td>
                        </tr>
 <!--                        </tbody>
                        <tfoot>
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
		                        <button class="btn ml10">
		                            <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp">
		                            <span>导出</span>
		                        </button>
		                        <button class="btn ml10">
		                            <span>打印</span>
		                        </button>
		                    </div>
		                </div>
		                <div class="clearfix"></div>
		            </div>
                <!--</div>-->

                <!--<div class="fr" style="width: 49%">
                    <div class="more-toolbar" >
                        <div class="table-toolbar">
                            <span class="font-size-16 btn-color mr10">储值卡卡金统计</span>
                            <span class="fr">时间：<span name="tableDate">2015年2月4日</span> 单位：元</span>
                        </div>
                        <div class="clearfix"></div>
                    </div>&lt;!&ndash;more-toolbar&ndash;&gt;

                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>储值卡类别</th>
                            <th>新增会员</th>
                            <th>新增卡金</th>
                            <th>划卡人数</th>
                            <th>划卡金额</th>
                            <th>卡金余额</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>白金卡</td>
                            <td>12</td>
                            <td>239824</td>
                            <td>12</td>
                            <td>12332</td>
                            <td>42342</td>
                        </tr>
                        <tr>
                            <td>铂金卡</td>
                            <td>12</td>
                            <td>239824</td>
                            <td>12</td>
                            <td>12332</td>
                            <td>42342</td>
                        </tr>
                        <tr>
                            <td>钻石卡</td>
                            <td>12</td>
                            <td>239824</td>
                            <td>12</td>
                            <td>12332</td>
                            <td>42342</td>
                        </tr>
                        <tr class="zhanbi-tr">
                            <td>汇总</td>
                            <td>36</td>
                            <td>239824</td>
                            <td>36</td>
                            <td>23442</td>
                            <td>12332</td>
                        </tr>
                        <tr class="huizong-tr">
                            <td>占比</td>
                            <td>20%</td>
                            <td>40%</td>
                            <td>40%</td>
                            <td>40%</td>
                            <td>40%</td>
                        </tr>
                        </tbody>
                        <tfoot>
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
                        </tfoot>
                    </table>
                </div>-->
            </div>
        </div>

        <div class="widgetcontent">
            <div>
                <div class="fl" style="width: 49%">
                    <div id="project-type-pie" style="min-width:450px;height:305px"></div>
                </div>
                <div class="fr" style="width: 49%">
                    <div id="card-type-pie" style="min-width:450px;height:305px"></div>
                </div>
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
                text: '',
                align: 'center',
                verticalAlign: 'middle'
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
                    ['项目', ${stotalProjectAmount}],
                    ['套餐', ${stotalPackageAmount}],
                    ['商品', ${stotalCommoditysAmount}],
                    {
                        y: 0.2,
                        dataLabels: {
                            enabled: false
                        }
                    }
                ]
            }]
        });

        jQuery('#card-type-pie').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: 0,
                plotShadow: false
            },
            title: {
                text: '',
                align: 'center',
                verticalAlign: 'middle'
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
                    <c:forEach items="${summaryResult.cardTypeSums }" var="cardTypeSumEntry" varStatus="cardTypeSumEntryStatus">
                    ['${summaryResult.mMemberLevles[cardTypeSumEntry.key].levelName }消费人数', ${cardTypeSumEntry.value.memberCnt}],
                    </c:forEach>
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
        jQuery("#startDate").val('${summaryResult.begin}');
        jQuery("#endDate").val('${summaryResult.end}');
        jQuery("#tableDate").html('${summaryResult.begin}-${summaryResult.end}');
        jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '划卡消费(${dateType})'
            },
            subtitle: {
                text: sdate
            },
            xAxis: {
                categories: [
<c:forEach items="${summaryResult.deptInfos }" var="dept" varStatus="deptStatus">
'${dept.deptName }'<c:if test="${not deptStatus.last }">,</c:if>
</c:forEach>
                             ]
            },
            yAxis: {
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
                    return '<b>' + this.x + '</b><br/>' +
                            this.series.name + ': ' + this.y + '<br/>' +
                            '总计: ' + this.point.stackTotal;
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
<c:forEach items="${summaryResult.deptInfos }" var="dept" varStatus="deptStatus">
<c:forEach items="${summaryResult.deptOrderTypeSums[dept.deptId] }" var="typeEntry">
<c:if test="${typeEntry.key == 1 }"><fmt:formatNumber value="${typeEntry.value.amount}" pattern="##.##" minFractionDigits="2" var="samount" />${samount }</c:if>
</c:forEach><c:if test="${not deptStatus.last }">,</c:if>
</c:forEach>
                       ]
            }, {
                name: '套餐',
                data: [<c:forEach items="${summaryResult.deptInfos }" var="dept" varStatus="deptStatus">
                <c:forEach items="${summaryResult.deptOrderTypeSums[dept.deptId] }" var="typeEntry">
                <c:if test="${typeEntry.key == 3 }"><fmt:formatNumber value="${typeEntry.value.amount}" pattern="##.##" minFractionDigits="2" var="samount" />${samount }</c:if>
                </c:forEach><c:if test="${not deptStatus.last }">,</c:if>
                </c:forEach>]
            }, {
                name: '商品',
                data: [<c:forEach items="${summaryResult.deptInfos }" var="dept" varStatus="deptStatus">
                <c:forEach items="${summaryResult.deptOrderTypeSums[dept.deptId] }" var="typeEntry">
                <c:if test="${typeEntry.key == 2 }"><fmt:formatNumber value="${typeEntry.value.amount}" pattern="##.##" minFractionDigits="2" var="samount" />${samount }</c:if>
                </c:forEach><c:if test="${not deptStatus.last }">,</c:if>
                </c:forEach>]
            }]
        });


        <c:choose>
        <c:when test="${empty summaryResult.dateType}">var date_type = -1;</c:when>
        <c:otherwise>var date_type = ${summaryResult.dateType};</c:otherwise>
        </c:choose>
      if(2 == date_type || 3 == date_type || 4 == date_type) {

          jQuery('#cash-day2').highcharts({
              chart: {
                  type: 'spline'
              },
              title: {
                  text: '划卡消费(${dateType2}走势)'
              },
              subtitle: {
                  text: sdate
              },
              xAxis: {
                  categories: [
<c:forEach var="dateSumEntry" items="${summaryResult.dateOrderTypeSums }" varStatus="dateSumStatus">
<c:set var="d" value="${fn:substring(dateSumEntry.key, fn:indexOf(dateSumEntry.key, '-') + 1, -1) }"/>
<c:if test="${fn:indexOf(d, '-') > 0 }"><c:set var="d" value="${fn:substring(d, fn:indexOf(d, '-') + 1, -1) }"/></c:if>
'${d}'<c:if test="${not dateSumStatus.last }">,</c:if>
</c:forEach>
                               ]
              },
              yAxis: {
                  title: {
                      text: '金额 (元)'
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
              series: [{
                  name: '项目',
                  data: [<c:forEach items="${summaryResult.dateOrderTypeSums }" var="orderTypeSumEntry" varStatus="orderTypeSumEntryStatus">
                  <c:forEach items="${orderTypeSumEntry.value }" var="typeEntry">
                  <c:if test="${typeEntry.key == 1 }"><fmt:formatNumber value="${typeEntry.value.amount}" pattern="##.##" minFractionDigits="2" var="samount" />${samount }</c:if>
                  </c:forEach><c:if test="${not orderTypeSumEntryStatus.last }">,</c:if>
                  </c:forEach>]
              }, {
                  name: '套餐',
                  data: [<c:forEach items="${summaryResult.dateOrderTypeSums }" var="orderTypeSumEntry" varStatus="orderTypeSumEntryStatus">
                  <c:forEach items="${orderTypeSumEntry.value }" var="typeEntry">
                  <c:if test="${typeEntry.key == 3 }"><fmt:formatNumber value="${typeEntry.value.amount}" pattern="##.##" minFractionDigits="2" var="samount" />${samount }</c:if>
                  </c:forEach><c:if test="${not orderTypeSumEntryStatus.last }">,</c:if>
                  </c:forEach>]
              }, {
                  name: '商品',
                  data: [<c:forEach items="${summaryResult.dateOrderTypeSums }" var="orderTypeSumEntry" varStatus="orderTypeSumEntryStatus">
                  <c:forEach items="${orderTypeSumEntry.value }" var="typeEntry">
                  <c:if test="${typeEntry.key == 2 }"><fmt:formatNumber value="${typeEntry.value.amount}" pattern="##.##" minFractionDigits="2" var="samount" />${samount }</c:if>
                  </c:forEach><c:if test="${not orderTypeSumEntryStatus.last }">,</c:if>
                  </c:forEach>]
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
            jQuery('#summaryFrm').submit();
          }
          break;
        case 1: // 周
          if(dateType != 1){
            jQuery('#dateType').val(1);
            jQuery('#summaryFrm').submit();
          }
          break;
        case 2: // 月
        case 3: // 日趋势
            if(dateType != 2 && dateType != 3){
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
        jQuery('#begin').val(jQuery('#startDate').val());
        jQuery('#end').val(jQuery('#endDate').val());
        jQuery('#dateType').removeAttr('value');
        jQuery('#summaryFrm').submit();
      }
</script>


    </div>




    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
   <!-- <div class="left-show-btn">
        <span class="iconfont icon-quanbu110"></span>
    </div>-->
    <a href="" class="showmenu"></a>


</div><!--mainwrapper-->
<form action="<%=basePath %>cardconsumption/view/cardconsumption" style="display:none;" id="summaryFrm" method="post">
<input type="hidden" name="begin" id="begin"/>
<input type="hidden" name="end" id="end"/>
<input type="hidden" name="dateType" id="dateType"/>
</form>
</body>
</html>
