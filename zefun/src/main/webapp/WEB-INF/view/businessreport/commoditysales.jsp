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
        <h4 class="widgettitle">
            <span class="dingdanzhuantai">套餐销售</span>
            <!-- <span class="video" style="float: right; font-weight: 400;color: #333;">视频帮助 <span class="iconfont icon-video" style="margin-top: 1px;"></span></span> -->
        </h4>
        <div class="report-title">
	        <c:set var="searchType" value="本<span name='searchType'>日</span>"/>
	        <c:set var="searchType2" value="<span name='searchType'>日</span>"/>
	        
	        <c:choose>
        	<c:when test="${empty commodityResult.dateType}">
        	<c:set var="searchType" value="区间"/>
            <c:set var="searchType2" value="区间"/>	
        	</c:when>
        	<c:otherwise>
        		<c:if test="${commodityResult.dateType ==  0}">
            	<c:set var="searchType" value="本<span name='searchType'>日</span>"/>
            	<c:set var="searchType2" value="<span name='searchType'>日</span>"/>
                </c:if>
                <c:if test="${commodityResult.dateType ==  1}">
                <c:set var="searchType" value="本<span name='searchType'>周</span>"/>
                <c:set var="searchType2" value="<span name='searchType'>周</span>"/>
                </c:if>
                <c:if test="${commodityResult.dateType ==  2 || commodityResult.dateType ==  3}">
                <c:set var="searchType" value="本<span name='searchType'>月</span>"/>
                <c:set var="searchType2" value="<span name='searchType'>月</span>"/>
                </c:if>
                <c:if test="${commodityResult.dateType ==  4}">
                <c:set var="searchType" value="本<span name='searchType'>年</span>"/>
                <c:set var="searchType2" value="<span name='searchType'>年</span>"/>
                </c:if>       	
        	</c:otherwise>
        </c:choose>
	        
            <ul>
            	<li>
					<h1>${commodityResult.cashStoreSales.cashStoreCnt }</h1>
					<p>${searchType}现金套餐销售数量</p>
				</li>
				<li>
					<fmt:formatNumber value="${commodityResult.cashStoreSales.cashStoreAmt}" pattern="##.##" minFractionDigits="2" var="income" />
					<h1>${income}</h1>
					<p>${searchType}现金套餐销售业绩</p>
				</li>
				<li>
					<h1>${commodityResult.cardStoreSales.cardStoreCnt }</h1>
					<p>${searchType}卡金套餐销售数量</p>
				</li>
				<li>
					<fmt:formatNumber value="${commodityResult.cardStoreSales.cardStoreAmt}" pattern="##.##" minFractionDigits="2" var="price" />
					<h1>${price}</h1>
					<p>${searchType }卡金套餐销售业绩</p>
				</li>
				<li>
					<fmt:formatNumber value="${commodityResult.lastGoodAmt}" pattern="##.##" minFractionDigits="2" var="lastIncome" />
					<h1>${lastIncome }</h1>
					<p>上期${searchType2}总销售业绩</p>
				</li>
				<li>
					<fmt:formatNumber value="${commodityResult.salesIncrementRate*100}" pattern="##.##" minFractionDigits="2" var="rate" />
                    <h1 class="red">${rate}%</h1>
                    <p>营业实收${searchType2}增长率</p>
				</li>
            </ul>
        </div>

        <div id="custom-toolbar" style="margin-bottom: 20px;" >
            <div class="table-toolbar" style="margin-bottom: 20px;">
            	<c:if test="${not empty commodityResult.branchStores}">
            				<span>门店</span>
            				<select id="pid" onchange="changeStore()">
            					<c:forEach items="${commodityResult.branchStores }" var="Store">
            						<option grade="${Store.storeId }" id="${Store.storeId}">${Store.storeName }</option>
            					</c:forEach>
            				</select>
            	</c:if>
                <span class="mr10">日期区间</span>
                <input type="text" class="datetimepicker input80" daysOffset="0" id="startDate" name="startDate"/>－
                <input type="text" class="datetimepicker input80" daysOffset="0" id="endDate" name="endDate"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="button-search btn" style="margin-left: -10px;" onclick="querySummary();">查询</button>
                <div class="select-target-report">
                    <span onclick="changeDate(0,this);" class="<c:choose><c:when test="${not empty commodityResult.dateType and 0 == commodityResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日</a></span>
                    <span onclick="changeDate(1,this);" class="<c:choose><c:when test="${not empty commodityResult.dateType and 1 == commodityResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">周</a></span>
                    <span onclick="changeDate(2,this);" class="<c:choose><c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月</a></span>
                    <span onclick="changeDate(3,this);" class="<c:choose><c:when test="${not empty commodityResult.dateType and 3 == commodityResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日趋势</a></span>
                    <span onclick="changeDate(4,this);" class="<c:choose><c:when test="${not empty commodityResult.dateType and 4 == commodityResult.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月趋势</a></span>
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="cash-day" style="width: 1050px;height:400px;<c:if test="${not empty commodityResult.dateType and (commodityResult.dateType == 3 or commodityResult.dateType == 4) }">display:none;</c:if>"></div>
            <div id="cash-day2" style="width: 1050px;height:400px;<c:if test="${empty commodityResult.dateType or commodityResult.dateType == 0 or commodityResult.dateType == 1 or commodityResult.dateType == 2 }">display:none;</c:if>"></div>
        </div>

        <!--商品销售业绩分类汇总-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">商品销售业绩分类汇总</span>
                    <span class="fr">时间：<span id="tableDate">${commodityResult.begin }-${commodityResult.end }</span>
					<c:choose>
							<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
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

            <table class="table table-bordered dep-border-table table-dash" style="border-bottom:1px solid #123234;" >
                <thead>
                <tr>
                    <th>部门</th>
                    <th>系列</th>
                    <th>项目</th>
                    <th>数量</th>
                    <th>数量占比</th>
                    <th>现金销售金额</th>
					<th>卡金销售金额</th>
					<th>合计金额</th>
					<th>金额占比</th>
                </tr>
                </thead>
                <tbody>
                <c:set value="0" var="countCashGoods"></c:set>
                <c:set value="0" var="countCardGoods"></c:set>
                <c:forEach items="${commodityResult.deptUpdateSummary}" var="dept" varStatus="deptStatus">                	
                	<c:forEach items="${dept.categoryList}" var="category" varStatus="categoryStatus">
                		<c:set value="${countCashGoods + category.categoryCash}" var="countCashGoods"></c:set>
                		<c:set value="${countCardGoods + category.categoryCard}" var="countCardGoods"></c:set>
                		<c:forEach items="${category.projectList}" var="goods" varStatus="projectStatus">
	                		<tr class="series${category.categoryId} dept${dept.deptId}" data-deptid="dept${dept.deptId}" data-categorynum="${fn:length(dept.categoryList)}">
                				
                				<c:if test="${categoryStatus.index == 0 and projectStatus.index == 0}">
			                		<td name ="deptIdTD"  deptid="${dept.deptId}">${dept.deptName}</td>
                				</c:if>
                	
                				<c:choose>
                					<c:when test="${projectStatus.index == 0}">
                					
	                					<td class="series-td"  data-belong-serise="series${category.categoryId}" id="${category.categoryId}">${category.categoryName}<i class="FontAwesome iconfa-caret-down afont ml8 "></i></td>
	                					<td class="categorycategory light-td">${goods.goodName} </td>
				                		<td class="light-td">
					                		<c:choose>
					                			<c:when test="${empty goods.goodSales.salesCount}">
					                				<c:set var="count" value="0"></c:set>
						                			<c:if test="${projectStatus.index==0}">
						                				<c:set var="count" value="${goods.goodCount}"></c:set>
						                			</c:if>
					                			</c:when>
					                			<c:otherwise>
					                				<c:set var="count" value="${goods.goodSales.salesCount}"></c:set>
					                			</c:otherwise>
					                		</c:choose>
					                		${count}
				                		</td>
				                		<td class="light-td">
					                		<c:choose>
					                			<c:when test="${dept.deptSales>0}">
					                				<c:set value="${count/dept.deptSales*100}" var="numRate"></c:set>
					                			</c:when>	
					                			<c:otherwise>
					                				<c:set value="0.00" var="numRate"></c:set>
					                			</c:otherwise>		                			
					                		</c:choose>			                		
					                		<fmt:formatNumber value="${numRate}" pattern="##.##" minFractionDigits="2" var="rate" />
					                		${rate }%
				                		</td>
				                		<td class="light-td">
				                			<c:choose>
												<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
													<fmt:formatNumber value="${category.categoryCash/10000}" pattern="##.##" minFractionDigits="2" var="categoryCash" />
												</c:when>
												<c:otherwise>
													<fmt:formatNumber value="${category.categoryCash}" pattern="##.##" minFractionDigits="2" var="categoryCash" />
												</c:otherwise>
											 </c:choose>
				                			${categoryCash}
				                		</td>
										<td class="light-td">
											<c:choose>
												<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
													<fmt:formatNumber value="${category.categoryCard/10000}" pattern="##.##" minFractionDigits="2" var="categoryCard" />
												</c:when>
												<c:otherwise>
													<fmt:formatNumber value="${category.categoryCard}" pattern="##.##" minFractionDigits="2" var="categoryCard" />
												</c:otherwise>
											 </c:choose>
					                		${categoryCard}
										</td>
				                		<td class="light-td">
					                		<c:choose>
												<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
													<fmt:formatNumber value="${goods.goodIncome/10000}" pattern="##.##" minFractionDigits="2" var="goodIncome" />
												</c:when>
												<c:otherwise>
													<fmt:formatNumber value="${goods.goodIncome}" pattern="##.##" minFractionDigits="2" var="goodIncome" />
												</c:otherwise>
											 </c:choose>
											 ${goodIncome}
				                		</td>
				                		<td class="light-td">
				                			<c:choose>
					                			<c:when test="${commodityResult.goodAmt>0}">
					                				<c:set value="${goods.goodIncome/commodityResult.goodAmt*100}" var="amtRate"></c:set>
					                			</c:when>	
					                			<c:otherwise>
					                				<c:set value="0.00" var="amtRate"></c:set>
					                			</c:otherwise>		                			
					                		</c:choose>			                		
					                		<fmt:formatNumber value="${amtRate}" pattern="##.##" minFractionDigits="2" var="rate" />
					                		${rate }%		                		
				                		</td>
	                				</c:when>
	                				<c:otherwise>
	                					<td class="categorycategory">${goods.goodName } </td>
				                		<td>
					                		<c:choose>
					                			<c:when test="${empty goods.goodSales.salesCount}">
					                				<c:set var="count" value="0"></c:set>
						                			<c:if test="${projectStatus.index==0}">
						                				<c:set var="count" value="${goods.goodCount}"></c:set>
						                			</c:if>
					                			</c:when>
					                			<c:otherwise>
					                				<c:set var="count" value="${goods.goodSales.salesCount}"></c:set>
					                			</c:otherwise>
					                		</c:choose>
					                        ${count}
				                		</td>
				                		<td>
					                		<c:choose>
					                			<c:when test="${dept.deptSales>0}">
					                				<c:set value="${count/dept.deptSales*100}" var="numRate"></c:set>
					                			</c:when>	
					                			<c:otherwise>
					                				<c:set value="0.00" var="numRate"></c:set>
					                			</c:otherwise>		                			
					                		</c:choose>			                		
					                		<fmt:formatNumber value="${numRate}" pattern="##.##" minFractionDigits="2" var="rate" />
					                		${rate }%
				                		</td>
				                		<td>
				                			<c:choose>
									           <c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
									               <fmt:formatNumber value="${goods.cashStoreSales.cashStoreAmt/10000}" pattern="##.##" minFractionDigits="2" var="cashStoreAmt" />
									           </c:when>
									           <c:otherwise>
									                <fmt:formatNumber value="${goods.cashStoreSales.cashStoreAmt}" pattern="##.##" minFractionDigits="2" var="cashStoreAmt" />
									           </c:otherwise>
									       </c:choose>
									       <c:choose>
										       <c:when test="${cashStoreAmt == null}">
										       		0.00
										       </c:when>
										       <c:otherwise>
										       		${cashStoreAmt}
										       </c:otherwise>
									       </c:choose>
				                		</td>
										<td>
											<c:choose>
											    <c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
											    	<fmt:formatNumber value="${goods.cardStoreSales.cardStoreAmt/10000}" pattern="##.##" minFractionDigits="2" var="cardStoreAmt" />
											    </c:when>
											    <c:otherwise>
											    	<fmt:formatNumber value="${goods.cardStoreSales.cardStoreAmt}" pattern="##.##" minFractionDigits="2" var="cardStoreAmt" />
											    </c:otherwise>
										    </c:choose>
										    <c:choose>
										       <c:when test="${cardStoreAmt == null}">
										       		0.00
										       </c:when>
										       <c:otherwise>
										       		${cardStoreAmt}
										       </c:otherwise>
									       </c:choose>
										</td>
										<td>
											<c:choose>
						                		<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
													<fmt:formatNumber value="${goods.goodIncome/10000}" pattern="##.##" minFractionDigits="2" var="goodIncome" />
												</c:when>
												<c:otherwise>
													<fmt:formatNumber value="${goods.goodIncome}" pattern="##.##" minFractionDigits="2" var="goodIncome" />
												</c:otherwise>
											</c:choose>
											 ${goodIncome}
				                		</td>				                		
				                		<td>
				                			<c:choose>
					                			<c:when test="${commodityResult.goodAmt>0}">
					                				<c:set value="${goods.goodIncome/commodityResult.goodAmt*100}" var="amtRate"></c:set>
					                			</c:when>	
					                			<c:otherwise>
					                				<c:set value="0.00" var="amtRate"></c:set>
					                			</c:otherwise>		                			
					                		</c:choose>			                		
					                		<fmt:formatNumber value="${amtRate}" pattern="##.##" minFractionDigits="2" var="rate" />${rate }%		                		
				                		</td>
	                				</c:otherwise>
                				</c:choose>               				
	                		</tr>
                		</c:forEach>               	             	
                	</c:forEach>
                </c:forEach>
                
<%--   				<c:forEach items="${commodityResult.deptGoodSummary }" var="dept" varStatus="deptStatus">
  				
  					<c:forEach items="${dept.deptGoodsInfo}" var="goods" varStatus="goodStatus">
  						<tr >
  						<c:if test="${goodStatus.index==0 }">
  							<td name ="deptIdTD" id="${dept.deptId}" rowspan="${fn:length(dept.deptGoodsInfo)}">${dept.deptName}</td>
  						</c:if>  						
  						<td>${goods.goodName}</td>
  						<td>${goods.goodCnt}</td>
  						<td>
  						<c:choose>
  							<c:when test="${commodityResult.goodCnt>0}">
  								<c:set value="${goods.goodCnt/commodityResult.goodCnt*100 }" var="numRate"></c:set>  								
  							</c:when>   														
  							<c:otherwise>
  								<c:set value="0" var="rate"></c:set>
  							</c:otherwise> 							
  						</c:choose> 
  						<fmt:formatNumber value="${numRate}" pattern="##.##" minFractionDigits="2" var="rate" /> 						
		                ${rate}%
  						</td>
  						<td>${goods.goodAmount}</td>
  						<td>
  							<c:choose>
			                		<c:when test="${commodityResult.goodCnt>0 }">
			                		<c:set value="${goods.goodAmount/commodityResult.goodCnt}" var="amtRate">			                					                		
			                		</c:set>			                		
			                		</c:when>			                		
			                		<c:otherwise>
			                			<c:set value="0" var="rate"></c:set>	
			                		</c:otherwise>	
			                				                		
		                	</c:choose> 
		                	<fmt:formatNumber value="${amtRate}" pattern="##.##" minFractionDigits="2" var="rate" />
		                	${rate}%
  						</td>
  						</tr>
  					</c:forEach>
  				
  				</c:forEach> --%>
     <!--            <tr>
                    <td colspan="7">
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
                </tr> -->
                <tr class="huizong-tr" >
                    <td>
                        	门店总计
                    </td>
                    <td></td>
                    <td></td>
                    <td>${commodityResult.goodCnt}</td>
                    <td>
                    	<c:set var="numRate" value="0"/><c:if test="${commodityResult.goodCnt!=0 }" ><c:set var="numRate" value="100"></c:set></c:if>
					${numRate}%
                    </td>
                    <td>
                    <c:choose>
						<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
							<fmt:formatNumber value="${countCashGoods/10000}" pattern="##.##" minFractionDigits="2" var="cashGoods" />
						</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${countCashGoods}" pattern="##.##" minFractionDigits="2" var="cashGoods" />
						</c:otherwise>
					</c:choose>
					${cashGoods}
                    </td>
                    <td>
                    <c:choose>
						<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
							<fmt:formatNumber value="${countCardGoods/10000}" pattern="##.##" minFractionDigits="2" var="cardGoods" />
						 </c:when>
						 <c:otherwise>
							<fmt:formatNumber value="${countCardGoods}" pattern="##.##" minFractionDigits="2" var="cardGoods" />
						</c:otherwise>
					 </c:choose>
					 ${cardGoods }
                    </td>
                    <td>
				    <c:choose>
						<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
							<fmt:formatNumber value="${commodityResult.goodAmt/10000}" pattern="##.##" minFractionDigits="2" var="goodAmt" />
						</c:when>
					<c:otherwise>
							<fmt:formatNumber value="${commodityResult.goodAmt}" pattern="##.##" minFractionDigits="2" var="goodAmt" />
					</c:otherwise>
					</c:choose>
					${goodAmt}
                    </td>
                    <td>
                    <c:set var="amtRate" value="0"/><c:if test="${commodityResult.goodAmt!=0 }" ><c:set var="amtRate" value="100"></c:set></c:if>
					${amtRate}%
                    </td>
                 </tr>
                </tbody>
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
        </div>
        <!--商品销售排行 -->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">商品销售排行</span>
                    <span class="fr">时间：<span id="tableDate">${commodityResult.begin }-${commodityResult.end }</span> 
                    	<c:choose>
							<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
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
            <table class="table table-bordered table-striped dep-border-table table-dash" id="goodsRank">
                <thead>
                <tr>
                    <th>商品名称</th>
                    <th style="position: relative">
                        <span class="dropdown-toggle" data-toggle="dropdown">
                            所属部门
                            <i class="FontAwesome iconfa-caret-down afont ml8 " ></i>
                        </span>
                        <ul class="dropdown-menu">
                        <li><a href="javascript:sortGoodsByNum('${commodityResult.storeId }','${commodityResult.begin}','${commodityResult.end}','${commodityResult.dateType}')">全店</a></li>
                            <c:forEach items="${commodityResult.idForName}" var="myMap">
                        		<li>
                        		<a href="javascript:sortGoodsByDept('${myMap.key}','${commodityResult.dateType}','${myMap.value}')">${myMap.value}</a>
                        		</li>
                        	</c:forEach>
                        </ul>
                    </th>
                    <th>销售数量
                    	<!-- 
                        <div class="paixu">
                            <a href="javascript:sortGoodsByNum('4','${commodityResult.begin}','${commodityResult.end}','${commodityResult.dateType}')"><i class="FontAwesome iconfa-caret-up afont ml8"></i></a>
                            <a href="javascript:sortGoodsByNum('3','${commodityResult.begin}','${commodityResult.end}','${commodityResult.dateType}')"><i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i></a>
                        </div>
                         -->
                    </th>
                    <th>现金销售金额</th>
					<th>卡金销售金额</th>
                    <th>总销售额
                    	<!-- 
                        <div class="paixu">
                            <a href="javascript:sortGoodsByNum('2','${commodityResult.begin}','${commodityResult.end}','${commodityResult.dateType}')"><i class="FontAwesome iconfa-caret-up afont ml8"></i></a>
                            <a href="javascript:sortGoodsByNum('1','${commodityResult.begin}','${commodityResult.end}','${commodityResult.dateType}')"><i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i></a>
                        </div>
                         -->
                    </th>
                    <th>本期销售额排行</th>
                    <th>上期销售额排行</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${commodityResult.goodRank }" var="goods">
                	<tr>
                		<td>${goods.goodName }</td>
                		<td>${commodityResult.idForName[goods.goodBelongToDeptId] }</td>
                		<td>${goods.goodCnt }</td>
                		<td>
				        <c:choose>
						    <c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
								<fmt:formatNumber value="${goods.goodAmount/10000}" pattern="##.##" minFractionDigits="2" var="goodAmount" />
							</c:when>
							<c:otherwise>
								<fmt:formatNumber value="${goods.goodAmount}" pattern="##.##" minFractionDigits="2" var="goodAmount" />
							</c:otherwise>
						</c:choose>
						${goodAmount}
                		</td>
                		<td>
                		<c:choose>
							<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
								<fmt:formatNumber value="${goods.cashStoreSales.cashStoreAmt/10000}" pattern="##.##" minFractionDigits="2" var="cashStoreAmt" />
							</c:when>
						<c:otherwise>
								<fmt:formatNumber value="${goods.cashStoreSales.cashStoreAmt}" pattern="##.##" minFractionDigits="2" var="cashStoreAmt" />
						</c:otherwise>
						</c:choose>
						 ${cashStoreAmt}
				        </td>
						<td>
						<c:choose>
							<c:when test="${not empty commodityResult.dateType and 2 == commodityResult.dateType || 3 == commodityResult.dateType || 4 == commodityResult.dateType}">
								<fmt:formatNumber value="${goods.cardStoreSales.cardStoreAmt/10000}" pattern="##.##" minFractionDigits="2" var="casdStoreAmt" />
							</c:when>
						<c:otherwise>
								<fmt:formatNumber value="${goods.cardStoreSales.cardStoreAmt}" pattern="##.##" minFractionDigits="2" var="casdStoreAmt" />
						</c:otherwise>
						</c:choose>
						${casdStoreAmt}
						</td>
                		<td>${goods.goodRank }</td>
                		<td>
                		<c:choose>
								<c:when test="${empty commodityResult.lastGoodRank[goods.goodName] }">
									<c:set var="lastRank" value="---"></c:set>
								</c:when>
								<c:otherwise>
									<c:set var="lastRank" value="${commodityResult.lastGoodRank[goods.goodName]}"></c:set>
								</c:otherwise>
						</c:choose>
						${lastRank }
                		</td>
                	</tr>
                </c:forEach>                             
                </tbody>
          <!--       <tfoot>
                <tr>
                    <td colspan="8">
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
        </div>
        
       
        <!--连锁套餐销售排行-->
        
        <!-- 
        
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">套餐销售排行</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>项目名称</th>
                    <th style="position: relative">
                        <span class="dropdown-toggle" data-toggle="dropdown">
                            所属部门
                            <i class="FontAwesome iconfa-caret-down afont ml8 " ></i>
                        </span>
                        <ul class="dropdown-menu">
                            <li><a href="#">美发</a></li>
                            <li><a href="#">美容</a></li>
                            <li><a href="#">足浴</a></li>
                        </ul>
                    </th>
                    <th>总销售数量
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总销售业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>平均购买单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>本期销售业绩排行</th>
                    <th>上期销售业绩排行</th>
                    <th>最佳项目门店/数量</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>韩式剪</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                    <td>罗湖店 358</td>
                </tr>
                <tr>
                    <td>头皮护理</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                    <td>罗湖店 358</td>
                </tr>
                <tr>
                    <td>头皮护理</td>
                    <td>美发部</td>
                    <td>12</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                    <td>罗湖店 358</td>
                </tr>
                </tbody>
            </table>
        </div>
        
         -->
        
        <!--连锁店套餐销售总业绩排行-->
        
         <!--
        
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">连锁店套餐销售总业绩排行</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>门店</th>
                    <th style="position: relative">
                        总销售件数
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总销售业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>平均购买单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>本期销售业绩排行</th>
                    <th>上期销售业绩排行</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>福田店</td>
                    <td>200</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>罗湖店</td>
                    <td>200</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr>
                    <td>罗湖店</td>
                    <td>200</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tr>
                <tr class="huizong-tr">
                    <td>连锁汇总</td>
                    <td>200</td>
                    <td>60000</td>
                    <td>300</td>
                    <td>1</td>
                    <td>3</td>
                </tbody>
            </table>
        </div>
        
        -->
        
    </div>
</div>

<script type="text/javascript">
	var date = new Date();
	var syear = date.getFullYear() + '年';
	var smonth = date.getFullYear() + '年1月1日 － ' + date.getFullYear() + '年12月' + date.getDaysInMonth() + '日';
	var sdate = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日';

    jQuery(function () {
		
        jQuery(function () {
        	
        	initDay();
        	
        	<c:choose>
            <c:when test="${empty commodityResult.dateType}">var date_type = -1;</c:when>
            <c:otherwise>var date_type = ${commodityResult.dateType};</c:otherwise>
            </c:choose>
            
            var colors = Highcharts.getOptions().colors,
                    categories = [
                                  /* '常规项目', '套餐', '疗程', '商品' */
                                <c:forEach items="${commodityResult.deptGoodSummary }" var="dept" varStatus="deptStatus">
      							'${dept.deptName }'<c:if test="${not deptStatus.last }">,</c:if>
      							</c:forEach>
                                  ],
                    name = '商品销售收入分布',
                    data = [
					<c:forEach items="${commodityResult.deptGoodSummary }" var="dept" varStatus="deptStatus">
					{
					    name:'${dept.deptName}',
						y: ${dept.deptTotalAmt },
					    color: colors[${deptStatus.index}],
					     drilldown: 
					    
					    {
					         name: '${dept.deptName}',
					         categories: [
											<c:forEach items="${dept.deptGoodsInfo}" var="goods" varStatus="goodStatus">
												'${goods.goodName}'
												<c:if test="${not goodStatus.last}">,</c:if>
											</c:forEach>
					                      ],
					         data: [
											<c:forEach items="${dept.deptGoodsInfo}" var="goods" varStatus="goodStatus">
											${goods.goodAmount}
											<c:if test="${not goodStatus.last}">,</c:if>
											</c:forEach>
					                ],
					         color: colors[${deptStatus.index}]
					     } 
					 }
					 <c:if test="${not deptStatus.last }">,</c:if>
					</c:forEach>	
                            ];

            function setChart(name, categories, data, color) {
                chart.xAxis[0].setCategories(categories);
                chart.series[0].remove(false);
                chart.addSeries({
                    name: name,
                    data: data,
                    color: color || 'white'
                }, false);
                chart.redraw();
            }

            var chart = jQuery('#cash-day').highcharts({
                chart: {
                    type: 'column'
                },
                title: {
                    text: '商品销售收入分布(${commodityResult.begin }-${commodityResult.end })'
                },
                subtitle: {
                    text: '点击柱状条查看各部门商品销售收入分布'
                },
                xAxis: {
                    categories: categories
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
                        text: '卡金收入金额'
                    }
                },
                plotOptions: {
                    column: {
                        cursor: 'pointer',
                        point: {
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
                        },
                        dataLabels: {
                            enabled: true,
                            color: '#000',
                            style: {
                                fontWeight: 'bold',
                                boxShadow: 'none',
                                textShadow:'none',
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
                                s = this.x +'商品业绩合计:<b>￥'+ this.y +'</b><br/>';
                        if (point.drilldown) {
                            s += '查看'+ point.category +'商品销售详情';
                        } else {
                            s += '返回查看各部门汇总';
                        }
                        return s;
                    }
                },
                series: [{
                    name: name,
                    data: data,
                    color: 'white'
                }],
                exporting: {
                    enabled: true
                }
            }).highcharts(); // return chart

        });
    });
    
    function changeDate(type, obj) {
        jQuery(".curent-report").removeClass('curent-report').addClass('report-category');
        jQuery(obj).removeClass('report-category').addClass('curent-report');
        <c:choose>
        <c:when test="${empty commodityResult.dateType}">var dateType=-1</c:when>
        <c:otherwise>var dateType=${commodityResult.dateType}</c:otherwise>
        </c:choose>
        switch(type){
	        case 0://日
	        	if(dateType!=0){
	        		 var objOptions=document.getElementById("pid");
	                 if(objOptions!=null){
	               	  var storeId = jQuery("#pid").find("option:selected").attr("grade");
	               	  jQuery('#storeId').val(storeId);
	                 }
	        		jQuery('#summaryFrm').submit();
	        	}
	        break;
	        case 1://周
	        	if(dateType!=1){
	        		 var objOptions=document.getElementById("pid");
	                 if(objOptions!=null){
	               	  var storeId = jQuery("#pid").find("option:selected").attr("grade");
	               	  jQuery('#storeId').val(storeId);
	                 }
	        		jQuery("#dateType").val(1);
	        		jQuery("#summaryFrm").submit();
	        	}
	        break;
	        case 2:
	        case 3:
	        	if(dateType != 3 && dateType !=2){
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
	        case 4: // 年
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
    
    function changeStore(){
    	var begin='${commodityResult.begin}';
    	var end='${commodityResult.end}'; 
    	var dateType='${commodityResult.dateType}';
        var storeId = jQuery("#pid").find("option:selected").attr("grade");
    	jQuery('#begin').val(begin);
        jQuery('#end').val(end);
        jQuery('#storeId').val(storeId);
        jQuery('#dateType').val(dateType);
        jQuery('#summaryFrm').submit();
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
    
    function initDay(){
    	jQuery("#startDate").val('${commodityResult.begin}');
    	jQuery("#endDate").val('${commodityResult.end}');
    	
    	var objOptions=document.getElementById("pid");
    	if(objOptions!=null){
    		var storeId='${commodityResult.storeId}';
        	for(var i=0; i<objOptions.length; i++){
        		if (objOptions.options[i].getAttribute("grade")==storeId){
        			objOptions.options[i].setAttribute("selected",true);
        		}
        	}
    	}
    	<c:choose>
        <c:when test="${empty commodityResult.dateType}">var date_type = -1;</c:when>
        <c:otherwise>var date_type = ${commodityResult.dateType};</c:otherwise>
        </c:choose>
    	
        jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '套餐消费统计'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                type: 'category'
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
            legend: {
                enabled: false
            },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
                        format: '{point.y}'
                    }
                }
            },

            tooltip: {
                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y}</b><br/>'
            },

            series: [{
                name: "部门",
                colorByPoint: true,
                data: [
                  <c:forEach items="${commodityResult.deptGoodSummary }" var="dept" varStatus="deptStatus">
                  {
                	  y: ${dept.deptTotalAmt},
                	  name:'${dept.deptName}',
                	  drilldown:'${dept.deptName}'
                  }
                  <c:if test="${not deptStatus.last}">,</c:if>
                  </c:forEach>   
                ]
            }],
            drilldown: {
                series: [
                  <c:forEach items="${commodityResult.deptGoodSummary }" var="dept" varStatus="deptStatus">
                  {
                	  name:'${dept.deptName}',
                	  id:'${dept.deptName}',
                	  data:[
							<c:forEach items="${dept.deptGoodsInfo}" var="goods" varStatus="goodStatus">
							['${goods.goodName}','${goods.goodAmount}']<c:if test="${not goodStatus.last}">,</c:if>
							</c:forEach>   	        
                	       ]
                  }
                  <c:if test="${not deptStatus.last}">,</c:if>
                  </c:forEach>
            ]}
        });
        
        
        <c:choose>
        <c:when test="${commodityResult.dateType==4}">var dateType2 = "月";</c:when>
        <c:otherwise>var dateType2 = "日";</c:otherwise>
        </c:choose>
        if(3 == date_type || date_type == 2){
        	dateType2='日';
        }
        if(3 == date_type || date_type == 2 || date_type == 4) {

            jQuery('#cash-day2').highcharts({
                chart: {
                    type: 'spline'
                },
                title: {
                    text: '商品收入('+dateType2+'走势)'
                },
                subtitle: {
                    text: '${commodityResult.begin}-${commodityResult.end}'
                },
                xAxis: {
                    categories: [
                       <c:choose>
							<c:when test="${not empty commodityResult.trendData}">
							<c:set var="trend" value="${commodityResult.trendData[0]}"></c:set> 
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
                        text: '金额 (元)'
                    }
                },
                tooltip: {
                    crosshairs: true,
                    headerFormat: '<span style="font-size:10px">&nbsp;{point.key}'+dateType2+'</span><table>',
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
                     <c:forEach items="${commodityResult.trendData}" var="trend" varStatus="trendStatus">
                     	{
                     		name:['${commodityResult.idForName[trend.deptId]}'],
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
    
    <c:forEach items="${commodityResult.deptUpdateSummary}" var="dept" varStatus="deptStatus">
		<c:forEach items="${dept.categoryList}" var="category">
		var categoryNum=${fn:length(dept.categoryList)};
		var categoryL= ${fn:length(category.projectList)};
		var deptId=${dept.deptId};
		var categoryId=${category.categoryId};
	        jQuery(".series" + categoryId).css("display", "none");
	        jQuery(".series" + categoryId).first().show();
	        jQuery(".dept" + deptId).first().find("td:first-child").first().attr("rowspan",categoryNum);//rowspan = xilie.lenght
	        jQuery(".series" + categoryId).first().find("td:first-child").first().attr("rowspan","1");
	    </c:forEach>
    </c:forEach>   
  
  jQuery(".series-td").on("click", function() {
	  //改列所属的系列
      var belongSerise =  jQuery(this).data("belong-serise");
	  //该系列对象
      var targetSerise = jQuery("." +belongSerise);
      jQuery(".series" + categoryId).first().show();
	  //该列的部门id
      var deptId = jQuery(this).parent().data("deptid");
	  var categoryNum = jQuery(this).parent().data("categorynum");
	  var currentRowLength =jQuery("." + deptId).first().find("td:first-child").attr("rowspan");
	  var deptIdClass = "." + deptId;
	  var flag =  false;
      	for(var i= 0; i< targetSerise.length; i++) {
      		//元素已隐藏 flag = true
      		if(targetSerise[i].style.display == "none"){
      			flag = true;
      		};
      	}
        if( flag == false ){
        	var deptRowLength = parseInt(currentRowLength) - targetSerise.length + 1;
        	jQuery("." + belongSerise).css("display", "none");
        	jQuery("." + belongSerise).first().show();
        	jQuery("." + deptId).first().find("td:first-child").attr("rowspan", deptRowLength);//rowspan = xilie.lenght
        	if(jQuery('.' + belongSerise).first().find("td").length == 9){   //对应列数
            	jQuery("." + belongSerise).first().find("td:nth-child(2)").attr("rowspan",1);
            }else {
            	jQuery("." + belongSerise).first().find("td:nth-child(1)").attr("rowspan",1);
            }
        }else {
        	var deptRowLength = parseInt(currentRowLength) + targetSerise.length - 1;
        	jQuery("." + belongSerise).show();
            jQuery("." + deptId).first().find("td:first-child").attr("rowspan", deptRowLength);//rowspan = xilie.lenght
        	if(jQuery('.' + belongSerise).first().find("td").length == 9){	//对应列数
            	jQuery("." + belongSerise).first().find("td:nth-child(2)").attr("rowspan",targetSerise.length);
            }else {
            	jQuery("." + belongSerise).first().find("td:nth-child(1)").attr("rowspan",targetSerise.length);
            }
  	        
        }                          
  }); 

</script>
<script type="text/javascript" src="<%=basePath %>js/commodity/goodsInfo.js"></script>

    </div>



    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
   <!-- <div class="left-show-btn">
        <span class="iconfont icon-quanbu110"></span>
    </div>-->
    <a href="" class="showmenu"></a>

<form action="<%=basePath %>commoditysales/view/commoditysales" style="display:none;" id="summaryFrm" method="post">
<input type="hidden" name="begin" id="begin"/>
<input type="hidden" name="end" id="end"/>
<input type="hidden" name="storeId" id="storeId">
<input type="hidden" name="dateType" id="dateType"/>
</form>
</body>
</html>