<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
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
					<h4 class="widgettitle">
						<span class="dingdanzhuantai">疗程销售</span> <span class="video"
							style="float: right; font-weight: 400; color: #333;">视频帮助
							<span class="iconfont icon-video" style="margin-top: 1px;"></span>
						</span>
					</h4>
					<div class="report-title">

						<c:set var="searchType" value="本<span name='searchType'>日</span>" />
						<c:set var="searchType2" value="<span name='searchType'>日</span>" />
						<c:choose>
							<c:when test="${empty combo.dateType}">
								<c:set var="searchType" value="区间" />
								<c:set var="searchType2" value="区间" />
							</c:when>
							<c:otherwise>
								<c:if test="${combo.dateType ==  0}">
									<c:set var="searchType"
										value="本<span name='searchType'>日</span>" />
									<c:set var="searchType2"
										value="<span name='searchType'>日</span>" />
								</c:if>
								<c:if test="${combo.dateType ==  1}">
									<c:set var="searchType"
										value="本<span name='searchType'>周</span>" />
									<c:set var="searchType2"
										value="<span name='searchType'>周</span>" />
								</c:if>
								<c:if
									test="${combo.dateType ==  2 || combo.dateType ==  3}">
									<c:set var="searchType"
										value="本<span name='searchType'>月</span>" />
									<c:set var="searchType2"
										value="<span name='searchType'>月</span>" />
								</c:if>
								<c:if test="${combo.dateType ==  4}">
									<c:set var="searchType"
										value="本<span name='searchType'>年</span>" />
									<c:set var="searchType2"
										value="<span name='searchType'>年</span>" />
								</c:if>
							</c:otherwise>
						</c:choose>

						<ul>
							<li>
								<h1>${combo.cashComboSales.cashComboCnt }</h1>
								<p>${searchType}现金疗程销售数量</p>
							</li>
							<li>
							<fmt:formatNumber value="${combo.cashComboSales.cashComboAmt}" pattern="##.##" minFractionDigits="2" var="income" />
								<h1>${income}</h1>
								<p>${searchType}现金疗程销售业绩</p>
							</li>
							<li>
								<h1>${combo.cardComboSales.cardComboCnt }</h1>
								<p>${searchType}卡金疗程销售数量</p>
							</li>
							<li>
							<fmt:formatNumber value="${combo.cardComboSales.cardComboAmt}" pattern="##.##" minFractionDigits="2" var="price" />
								<h1>${price}</h1>
								<p>${searchType }卡金疗程销售业绩</p>
							</li>
							<li>
							<fmt:formatNumber value="${combo.lastComboAmount}" pattern="##.##" minFractionDigits="2" var="lastIncome" />
								<h1>${lastIncome }</h1>
								<p>上期${searchType2}总销售业绩</p>
							</li>
							<li>
							<fmt:formatNumber value="${combo.amountIncrementRate*100}" pattern="##.##" minFractionDigits="2" var="rate" />
								<h1 class="red">${rate }%</h1>
								<p>营业实收${searchType2 }增长率</p>
							</li>
						</ul>
					</div>

					<div id="custom-toolbar" style="margin-bottom: 20px;">
						<div class="table-toolbar" style="margin-bottom: 20px;">
						<c:if test="${not empty combo.branchStores}">
            				<span>门店</span>
            				<select id="pid" onchange="changeStore()">
            					<c:forEach items="${combo.branchStores }" var="Store">
            						<option grade="${Store.storeId }" id="${Store.storeId}">${Store.storeName }</option>
            					</c:forEach>
            				</select>
            			</c:if>
							<span class="mr10">日期区间</span> <input type="text"
								class="datetimepicker input80" daysOffset="0" id="startDate"
								name="startDate" />－ <input type="text"
								class="datetimepicker input80" daysOffset="0" id="endDate"
								name="endDate" />&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button-search btn" style="margin-left: -10px;"
								onclick="querySummary();">查询</button>
							<div class="select-target-report">
								<span onclick="changeDate(0,this);"
									class="<c:choose><c:when test="${not empty combo.dateType and 0 == combo.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a
									href="javascript:void(0);">日</a></span> <span
									onclick="changeDate(1,this);"
									class="<c:choose><c:when test="${not empty combo.dateType and 1 == combo.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a
									href="javascript:void(0);">周</a></span> <span
									onclick="changeDate(2,this);"
									class="<c:choose><c:when test="${not empty combo.dateType and 2 == combo.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a
									href="javascript:void(0);">月</a></span> <span
									onclick="changeDate(3,this);"
									class="<c:choose><c:when test="${not empty combo.dateType and 3 == combo.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a
									href="javascript:void(0);">日趋势</a></span> <span
									onclick="changeDate(4,this);"
									class="<c:choose><c:when test="${not empty combo.dateType and 4 == combo.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a
									href="javascript:void(0);">月趋势</a></span>
							</div>
						</div>
					</div>
					<!--custom-toolbar-->

					<div class="widgetcontent">
						<div id="cash-day" style="width: 1050px;height:400px;<c:if test="${not empty combo.dateType and (combo.dateType == 3 or combo.dateType == 4) }">display:none;</c:if>"></div>
            			<div id="cash-day2" style="width: 1050px;height:400px;<c:if test="${empty combo.dateType or combo.dateType == 0 or combo.dateType == 1 or combo.dateType == 2 }">display:none;</c:if>"></div>
					</div>

					<!--疗程销售业绩分类汇总-->
					<div class="widgetcontent">
						<div class="more-toolbar">
							<div class="table-toolbar">
								<span class="font-size-16 btn-color mr10">疗程销售业绩分类汇总</span> <span
									class="fr">时间：<span id="tableDate">${combo.begin }-${combo.end }</span> 
									<c:choose>
							           <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
							               	 <span class="red">单位：万元</span>
							           </c:when>
							           <c:otherwise>
							                 	单位：元
							           </c:otherwise>
							       </c:choose>
								</span>
							</div>
							<div class="clearfix"></div>
						</div>
						<!--more-toolbar-->
											    	
						<table class="table table-bordered dep-border-table table-dash" style="border-bottom: 1px solid #123234;"
							id="tableId">
							<thead>
								<tr>
									<th>部门</th>
									<th>疗程</th>
									<th>数量</th>
									<th>数量占比</th>
									<th>现金销售金额</th>
									<th>卡金销售金额</th>
									<th>抵扣金额</th>
									<th>合计金额</th>
									<th>金额占比</th>									
								</tr>
							</thead>
							<tbody>
							 <c:set value="0.00" var="cashPay"/>
							 <c:set value="0.00" var="cardPay"/>
							 <c:set value="0.00" var="discountPay"/>
							 <c:set value="0.00" var="cashPay2"/>
							 <c:set value="0.00" var="cardPay2"/>
							 <c:set value="0.00" var="discountPay2"/>
				<c:forEach items="${combo.deptComboSummary}" var="dept" varStatus="deptStatus">
                		<c:forEach items="${dept.combosBelongToDept}" var="comboProject" varStatus="comboStatus">
	                		<tr>
	                			<c:if test="${comboStatus.index==0 }">
	                				<td name ="deptIdTD" deptid="${dept.deptId}" rowspan="${fn:length(dept.combosBelongToDept) }">${dept.deptName}</td>
	                			</c:if>		                		
		                		<td >${comboProject.comboName}</td>
		                		<td>${comboProject.comboCnt}</td>
		                		<td>
		                		<c:choose>
		                			<c:when test="${combo.comboSales>0 }">
		                			<c:set value="${comboProject.comboCnt/combo.comboSales*100 }" var="numRate"></c:set>
		                			</c:when>
		                			<c:otherwise>
		                			<c:set value="0" var="numRate"></c:set>
		                			</c:otherwise>		                					                			
		                		</c:choose>
		                        <fmt:formatNumber value="${numRate}" pattern="##.##" minFractionDigits="2" var="rate" />
		                        ${rate}%
		                		</td>
		                		<td>
		                		<c:choose>
		                			<c:when test="${comboProject.comboName == '小计'}">
		                				${cashPay2}
		                			</c:when>
		                			<c:otherwise>
		                				<fmt:formatNumber value="${comboProject.cashComboSales.cashComboAmt}" pattern="##.##" minFractionDigits="2" var="cashComboAmt" />
				                		<c:choose>
				                			<c:when test="${cashComboAmt == null}">0.00</c:when>
				                			<c:otherwise>
				                			<c:choose>
									           <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									               	 ${cashComboAmt/10000}
									           </c:when>
									           <c:otherwise>
									                 	${cashComboAmt}
									           </c:otherwise>
									       </c:choose>
				                			<c:set value="${cashPay+cashComboAmt}" var="cashPay"/>
				                			<c:set value="${cashPay2+cashComboAmt}" var="cashPay2"/>
				                			</c:otherwise>
				                		</c:choose>
		                			</c:otherwise>
		                		</c:choose>
		                		</td>
								<td>
								<c:choose>
		                			<c:when test="${comboProject.comboName == '小计'}">
		                				${cardPay2}
		                			</c:when>
		                			<c:otherwise>
		                				<fmt:formatNumber value="${comboProject.cardComboSales.cardComboAmt}" pattern="##.##" minFractionDigits="2" var="cardComboAmt" />
										<c:choose>
				                			<c:when test="${cardComboAmt == null}">0.00</c:when>
				                			<c:otherwise>
				                			<c:choose>
									           <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									               	${cardComboAmt/10000}
									           </c:when>
									           <c:otherwise>
									                 	${cardComboAmt}
									           </c:otherwise>
									       </c:choose>
				                			<c:set value="${cardPay+cardComboAmt}" var="cardPay"/>
				                			<c:set value="${cardPay2+cardComboAmt}" var="cardPay2"/>
				                			</c:otherwise>
				                		</c:choose>
		                			</c:otherwise>
		                		</c:choose>
								</td>
								<td>
								<c:choose>
		                			<c:when test="${comboProject.comboName == '小计'}">
		                				${discountPay2}
		                			</c:when>
		                			<c:otherwise>
		                			<fmt:formatNumber value="${comboProject.discountComboSales.discountComboAmt}" pattern="##.##" minFractionDigits="2" var="discountComboAmt" />
									<c:choose>
			                			<c:when test="${discountComboAmt == null}">0.00</c:when>
			                			<c:otherwise>
			                			<c:choose>
									           <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									               	${discountComboAmt/10000}
									           </c:when>
									           <c:otherwise>
									                 	${discountComboAmt}
									           </c:otherwise>
									       </c:choose>
			                			</c:otherwise>
			                		</c:choose>
									<c:set value="${discountPay+discountComboAmt}" var="discountPay"/>
									<c:set value="${discountPay2+discountComboAmt}" var="discountPay2"/>
		                			</c:otherwise>
		                		</c:choose>
								</td>
		                		<td>
		                		<c:choose>
							           <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
							               	 <fmt:formatNumber value="${comboProject.comboAmount/10000}" pattern="##.##" minFractionDigits="2" var="comboAmount" />
							           </c:when>
							           <c:otherwise>
							                 <fmt:formatNumber value="${comboProject.comboAmount}" pattern="##.##" minFractionDigits="2" var="comboAmount" />
							           </c:otherwise>
							     </c:choose>
							     ${comboAmount}
		                		</td>
		                		<td>
		                		<c:choose>
		                			<c:when test="${combo.comboAmount>0 }">
		                			<c:set value="${comboProject.comboAmount/combo.comboAmount*100 }" var="amtRate"></c:set>
		                			</c:when>
		                			<c:otherwise>
		                			<c:set value="0" var="amtRate"></c:set>
		                			</c:otherwise>		                					                			
		                		</c:choose>
		                        <fmt:formatNumber value="${amtRate}" pattern="##.##" minFractionDigits="2" var="rate" />
		                        ${rate}%
		                		</td>		                				                		
	                		</tr>
                		</c:forEach>
                		<c:set value="0.00" var="cashPay2"/>
						<c:set value="0.00" var="cardPay2"/>
						<c:set value="0.00" var="discountPay2"/>              	             	
                	</c:forEach>
                	
                	
				<!-- 			 	 <tr>
									<td colspan="7">
							 
										<div class="s-btn-group fr">
											<button class="btn ml10" onclick="javascript:method1('tableId')">
												<img
													src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png"
													alt="" class="vatp" /> <span>导出</span>
											</button>
											<button class="btn ml10">
												<span>打印</span>
											</button>
										</div>
								 	</td>
								</tr>  --> 
							 	<tr class="huizong-tr ">
									<td>门店总计</td>
									<td></td>
									<td>${combo.comboSales }</td>
									<td>
										<c:set var="numRate" value="0"/><c:if test="${combo.comboSales!=0 }" ><c:set var="numRate" value="100"></c:set></c:if>
									${numRate}%
									</td>
									<td>
									<fmt:formatNumber value="${cashPay}" pattern="##.##" minFractionDigits="2" var="cashPays" />
									<c:choose>
									     <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									         ${cashPays/10000}
									      </c:when>
									      <c:otherwise>
									          ${cashPays}
									     </c:otherwise>
									</c:choose>
									</td>
									<td>
									<fmt:formatNumber value="${cardPay}" pattern="##.##" minFractionDigits="2" var="cardPays" />
									<c:choose>
									     <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									         ${cardPays/10000}
									      </c:when>
									      <c:otherwise>
									          ${cardPays}
									     </c:otherwise>
									</c:choose>
									</td>
									<td>
									<fmt:formatNumber value="${discountPay}" pattern="##.##" minFractionDigits="2" var="discountPays" />
									<c:choose>
									     <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									         ${discountPays/10000}
									      </c:when>
									      <c:otherwise>
									          ${discountPays}
									     </c:otherwise>
									</c:choose>
									
									</td>
									<td>
									<c:choose>
									     <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									         ${combo.comboAmount/10000}
									      </c:when>
									      <c:otherwise>
									          ${combo.comboAmount}
									     </c:otherwise>
									</c:choose>
									</td>
									<td>
									<c:set var="amtRate" value="0"/><c:if test="${combo.comboAmount!=0 }" ><c:set var="amtRate" value="100"></c:set></c:if>
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
					<!--疗程销售排行 -->
					<div class="widgetcontent">
						<div class="more-toolbar">
							<div class="table-toolbar">
								<span class="font-size-16 btn-color mr10">疗程销售排行</span> <span
									class="fr">时间：<span id="tableDate">${combo.begin }-${combo.end }</span>
									 <c:choose>
							           <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
							               	 <span class="red">单位：万元</span>
							           </c:when>
							           <c:otherwise>
							                 	单位：元
							           </c:otherwise>
							       </c:choose>
								</span>
							</div>
							<div class="clearfix"></div>
						</div>
						<!--more-toolbar-->
						<table class="table table-bordered table-striped dep-border-table table-dash" id="packageRank">
							<thead>
								<tr>
									<th>项目名称</th>
									<th style="position: relative"><span
										class="dropdown-toggle" data-toggle="dropdown"> 所属部门 <i
											class="FontAwesome iconfa-caret-down afont ml8 "></i>
									</span>
										<ul class="dropdown-menu">
											<li><a href="javascript:sortComboByNum('${combo.storeId}','${combo.begin}','${combo.end}','${combo.dateType}')">全店</a></li>
											<c:forEach items="${combo.idForName}" var="myMap">
                        					<li>
                        					<a href="javascript:sortComboByDept('${myMap.key}','${combo.dateType}','${myMap.value}')">${myMap.value}</a>
                        					</li>
                        					</c:forEach>
										</ul></th>
									<th>销售数量
										<!-- 
										<div class="paixu">
											<a href="javascript:sortComboByNum('4','${combo.begin}','${combo.end}','${combo.dateType}')"><i class="FontAwesome iconfa-caret-up afont ml8"></i></a> 
											<a href="javascript:sortComboByNum('3','${combo.begin}','${combo.end}','${combo.dateType}')"><i class="FontAwesome iconfa-caret-down afont ml8"
												style="position: absolute;"></i></a>
										</div>
										 -->
									</th>
									<th>现金销售金额</th>
									<th>卡金销售金额</th>
									<th>抵扣金额</th>
									<th>总销售额
										<!-- 
										<div class="paixu">
											<a href="javascript:sortComboByNum('2','${combo.begin}','${combo.end}','${combo.dateType}')"><i class="FontAwesome iconfa-caret-up afont ml8"></i></a>
											<a href="javascript:sortComboByNum('1','${combo.begin}','${combo.end}','${combo.dateType}')"><i class="FontAwesome iconfa-caret-down afont ml8"
												style="position: absolute;"></i></a>
										</div>
										 -->
									</th>
									<th>本期销售额排行</th>
									<th>上期销售额排行</th>
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${combo.comboRank }" var="rank">
								<tr>
								<td>${rank.comboName}</td>
								<td>${combo.idForName[rank.deptId]}</td>
								<td>${rank.comboCnt }</td>
								<td>
		                		<fmt:formatNumber value="${rank.cashComboSales.cashComboAmt}" pattern="##.##" minFractionDigits="2" var="cashComboAmt" />
		                		<c:choose>
		                			<c:when test="${cashComboAmt == null}">0.00</c:when>
		                			<c:otherwise>
		                			<c:choose>
									     <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									         ${cashComboAmt/10000}
									      </c:when>
									      <c:otherwise>
									          ${cashComboAmt}
									     </c:otherwise>
									</c:choose>
		                			</c:otherwise>
		                		</c:choose>
		                		</td>
								<td>
								<fmt:formatNumber value="${rank.cardComboSales.cardComboAmt}" pattern="##.##" minFractionDigits="2" var="cardComboAmt" />
								<c:choose>
		                			<c:when test="${cardComboAmt == null}">0.00</c:when>
		                			<c:otherwise>
		                			<c:choose>
									     <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									         ${cardComboAmt/10000}
									      </c:when>
									      <c:otherwise>
									          ${cardComboAmt}
									     </c:otherwise>
									</c:choose>
		                			</c:otherwise>
		                		</c:choose>
								</td>
								<td>
								<fmt:formatNumber value="${rank.discountComboSales.discountComboAmt}" pattern="##.##" minFractionDigits="2" var="discountComboAmt" />
								<c:choose>
		                			<c:when test="${discountComboAmt == null}">0.00</c:when>
		                			<c:otherwise>
		                			<c:choose>
									     <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									         ${discountComboAmt/10000}
									      </c:when>
									      <c:otherwise>
									          ${discountComboAmt}
									     </c:otherwise>
									</c:choose>
		                			</c:otherwise>
		                		</c:choose>
								</td>
								<td>
								<c:choose>
									     <c:when test="${not empty combo.dateType and 2 == combo.dateType || 3 == combo.dateType || 4 == combo.dateType}">
									         ${rank.comboAmount/10000}
									      </c:when>
									      <c:otherwise>
									          ${rank.comboAmount}
									     </c:otherwise>
									</c:choose>
								</td>								
								<td>${rank.comboRank}</td>
								<td>
									<c:choose>
									<c:when test="${empty combo.lastComboRank[rank.comboName] }">
									<c:set var="lastRank" value="---"></c:set>
									</c:when>
									<c:otherwise>
									<c:set var="lastRank" value="${combo.lastComboRank[rank.comboName]}"></c:set>
									</c:otherwise>
									</c:choose>
									${lastRank }
								</td>
								</tr>
							</c:forEach>				
							</tbody>
<!-- 							<tfoot>
								<tr>
									<td colspan="8">
										<div class="s-btn-group fr">
											<button class="btn ml10" onclick="window.location.href='packagesales.jsp?exportToExcel=YES'">
												<img
													src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png"
													alt="" class="vatp" /> <span>导出</span>
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
					<!--连锁疗程销售排行-->
					<!-- 
					<div class="widgetcontent">
						<div class="more-toolbar">
							<div class="table-toolbar">
								<span class="font-size-16 btn-color mr10">疗程销售排行</span> <span
									class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元
								</span>
							</div>
							<div class="clearfix"></div>
						</div>
						
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>项目名称</th>
									<th style="position: relative"><span
										class="dropdown-toggle" data-toggle="dropdown"> 所属部门 <i
											class="FontAwesome iconfa-caret-down afont ml8 "></i>
									</span>
										<ul class="dropdown-menu">
											<li><a href="#">美发</a></li>
											<li><a href="#">美容</a></li>
											<li><a href="#">足浴</a></li>
										</ul></th>
									<th>总销售数量
										<div class="paixu">
											<i class="FontAwesome iconfa-caret-up afont ml8"></i> <i
												class="FontAwesome iconfa-caret-down afont ml8"
												style="position: absolute;"></i>
										</div>
									</th>
									<th>总销售业绩
										<div class="paixu">
											<i class="FontAwesome iconfa-caret-up afont ml8"></i> <i
												class="FontAwesome iconfa-caret-down afont ml8"
												style="position: absolute;"></i>
										</div>
									</th>
									<th>平均购买单价
										<div class="paixu">
											<i class="FontAwesome iconfa-caret-up afont ml8"></i> <i
												class="FontAwesome iconfa-caret-down afont ml8"
												style="position: absolute;"></i>
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
					<!--连锁店疗程销售总业绩排行-->
					
					<!-- 
					
					<div class="widgetcontent">
						<div class="more-toolbar">
							<div class="table-toolbar">
								<span class="font-size-16 btn-color mr10">连锁店疗程销售总业绩排行</span> <span
									class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元
								</span>
							</div>
							<div class="clearfix"></div>
						</div>
						<table class="table table-bordered table-striped">
							<thead>
								<tr>
									<th>门店</th>
									<th style="position: relative">总销售件数
										<div class="paixu">
											<i class="FontAwesome iconfa-caret-up afont ml8"></i> <i
												class="FontAwesome iconfa-caret-down afont ml8"
												style="position: absolute;"></i>
										</div>
									</th>
									<th>总销售业绩
										<div class="paixu">
											<i class="FontAwesome iconfa-caret-up afont ml8"></i> <i
												class="FontAwesome iconfa-caret-down afont ml8"
												style="position: absolute;"></i>
										</div>
									</th>
									<th>平均购买单价
										<div class="paixu">
											<i class="FontAwesome iconfa-caret-up afont ml8"></i> <i
												class="FontAwesome iconfa-caret-down afont ml8"
												style="position: absolute;"></i>
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
				
				function changeDate(type, obj) {
				        jQuery(".curent-report").removeClass('curent-report').addClass('report-category');
				        jQuery(obj).removeClass('report-category').addClass('curent-report');
				        <c:choose>
				        <c:when test="${empty combo.dateType}">var dateType=-1</c:when>
				        <c:otherwise>var dateType=${combo.dateType}</c:otherwise>
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
					
					jQuery(function() {
						
						initDay();
						
						var colors = Highcharts.getOptions().colors,
						categories = [
							/*	'常规项目', '疗程', '疗程', '商品' */
							<c:forEach items="${combo.deptComboSummary }" var="dept" varStatus="deptStatus">
							'${dept.deptName }'<c:if test="${not deptStatus.last }">,</c:if>
							</c:forEach>									
								],
						name = '疗程收入分布', 
						data = [
						        
						<c:forEach items="${combo.deptComboSummary }" var="dept" varStatus="deptStatus">
						{
						    name:'${dept.deptName}',
							y: ${dept.deptComboSummary },
						     color: colors[${deptStatus.index}],
						     drilldown: 
						    
						    {
						         name: '${dept.deptName}',
						         categories: [
												<c:forEach items="${dept.combosBelongToDept}" var="combo" varStatus="comboStatus">
													'${combo.comboName}'
													<c:if test="${not comboStatus.last}">,</c:if>
												</c:forEach>
						                      ],
						         data: [
												<c:forEach items="${dept.combosBelongToDept}" var="combo" varStatus="comboStatus">
												${combo.comboAmount}
												<c:if test="${not comboStatus.last}">,</c:if>
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
								name : name,
								data : data,
								color : color || 'white'
							}, false);
							chart.redraw();
						}
						
						<c:choose>
				        <c:when test="${empty combo.dateType}">var date_type = -1;</c:when>
				        <c:otherwise>var date_type = ${combo.dateType};</c:otherwise>
				        </c:choose>

						var chart = jQuery('#cash-day')
								.highcharts(
										{
											chart : {
												type : 'column'
											},
											title : {
												text : '疗程销售收入分布'
											},
											subtitle : {
												text : '点击柱状条查看分类疗程收入分布'
											},
											xAxis : {
												categories : categories
											},
											yAxis : {
												labels: {
							                        formatter: function () {
							                      	  	if(2 == date_type){
							                      	  		return this.value / 10000 + '万';
							                      	  	} 
							                      	  return this.value;
							                        }
							                    },
							                    min: 0,
												title : {
													text : '疗程收入金额'
												}
											},
											plotOptions : {
												column : {
													cursor : 'pointer',
													point : {
														events : {
															click : function() {
																var drilldown = this.drilldown;
																if (drilldown) { // drill down
																	setChart(
																			drilldown.name,
																			drilldown.categories,
																			drilldown.data,
																			drilldown.color);
																} else { // restore
																	setChart(
																			name,
																			categories,
																			data);
																}
															}
														}
													},
													dataLabels : {
														enabled : true,
														color : '#000',
														style : {
															fontWeight : 'bold',
															boxShadow : 'none',
															textShadow: 'none',
															
														},
														formatter : function() {
															return this.y;
														}
													}
												}
											},
											tooltip : {
												formatter : function() {
													var point = this.point, s = this.x
															+"疗程业绩合计"+ ':￥<b>'
															+ this.y
															+ '</b><br/>';
													if (point.drilldown) {
														s += '查看'
																+ point.category
																+ '各疗程详情';
													} else {
														s += '返回查看各部门汇总';
													}
													return s;
												}
											},
											series : [ {
												name : name,
												data : data,
												color : 'white'
											} ],
											exporting : {
												enabled : true
											}
										}).highcharts(); // return chart

					});
			
				
			    function initDay(){
			    	var objBegin='${combo.begin}';
			    	objBegin=objBegin.replace('-','/');
			    	var objEnd='${combo.end}';
			    	objEnd=objEnd.replace('-','/');
			    	jQuery("#startDate").val(objBegin);
			    	jQuery("#endDate").val(objEnd);
			        
			        var objOptions=document.getElementById("pid");
			    	if(objOptions!=null){
			    		var storeId='${combo.storeId}';
			        	for(var i=0; i<objOptions.length; i++){
			        		if (objOptions.options[i].getAttribute("grade")==storeId){
			        			objOptions.options[i].setAttribute("selected",true);
			        		}
			        	}
			    	}
			    	
			    	<c:choose>
			        <c:when test="${empty combo.dateType}">var date_type = -1;</c:when>
			        <c:otherwise>var date_type = ${combo.dateType};</c:otherwise>
			        </c:choose>
			        
			        jQuery('#cash-day').highcharts({
			            chart: {
			                type: 'column'
			            },
			            title: {
			                text: '疗程消费统计'
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
			                  <c:forEach items="${combo.deptComboSummary }" var="dept" varStatus="deptStatus">
			                  {
			                	  y: ${dept.deptComboSummary},
			                	  name:'${dept.deptName}',
			                	  drilldown:'${dept.deptName}'
			                  }
			                  <c:if test="${not deptStatus.last}">,</c:if>
			                  </c:forEach>   
			                ]
			            }],
			            drilldown: {
			                series: [
			                  <c:forEach items="${combo.deptComboSummary }" var="dept" varStatus="deptStatus">
			                  {
			                	  name:'${dept.deptName}',
			                	  id:'${dept.deptName}',
			                	  data:[
										<c:forEach items="${dept.combosBelongToDept}" var="comboV" varStatus="comboStatus">
										['${comboV.comboName}','${comboV.comboAmount}']<c:if test="${not comboStatus.last}">,</c:if>
										</c:forEach>   	        
			                	       ]
			                  }
			                  <c:if test="${not deptStatus.last}">,</c:if>
			                  </c:forEach>
			            ]}
			        });
			        
			        
			        <c:choose>
			        <c:when test="${combo.dateType==4}">var dateType2 = "月";</c:when>
			        <c:otherwise>var dateType2 = "日";</c:otherwise>
			        </c:choose>
			        
			        if(3 == date_type || date_type == 2 || date_type == 4) {

			            jQuery('#cash-day2').highcharts({
			                chart: {
			                    type: 'spline'
			                },
			                title: {
			                    text: '疗程收入('+dateType2+'走势)'
			                },
			                subtitle: {
			                    text: '${combo.begin}-${combo.end}'
			                },
			                xAxis: {
			                    categories: [
			                       <c:choose>
										<c:when test="${not empty combo.trendData}">
										<c:set var="trend" value="${combo.trendData[0]}"></c:set> 
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
			                     <c:forEach items="${combo.trendData}" var="trend" varStatus="trendStatus">
			                     	{
			                     		name:['${combo.idForName[trend.deptId]}'],
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
			    
			    function changeStore(){
			    	var begin='${combo.begin}';
			    	var end='${combo.end}'; 
			    	var dateType='${combo.dateType}';
			        var storeId = jQuery("#pid").find("option:selected").attr("grade");
			    	jQuery('#begin').val(begin);
			        jQuery('#end').val(end);
			        jQuery('#storeId').val(storeId);
			        jQuery('#dateType').val(dateType);
			        jQuery('#summaryFrm').submit();
			    }
			    			    			    
</script>
<script type="text/javascript" src="<%=basePath %>js/commodity/comboInfo.js"></script>

		</div>



	</div>
	<!--RIGHT PANEL结束 -->
</div>
	<a href="" class="showmenu"></a>

	<form action="<%=basePath%>packagesales/view/packagesales" style="display: none;" id="summaryFrm" method="post">
		<input type="hidden" name="begin" id="begin" /> 
		<input type="hidden" name="end" id="end" />
		<input type="hidden" name="storeId" id="storeId">
		 <input type="hidden" name="dateType" id="dateType" />
	</form>
</body>
</html>