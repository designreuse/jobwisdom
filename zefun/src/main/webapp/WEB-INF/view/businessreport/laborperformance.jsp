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
        <c:set var="searchType2" value="<span name='searchType'>日</span>"/>
        <c:choose>
        	<c:when test="${empty summaryLaborDto.dateType}">
        	<c:set var="searchType" value="区间"/>
            <c:set var="searchType2" value="区间"/>	
        	</c:when>
        	<c:otherwise>
        		<c:if test="${summaryLaborDto.dateType ==  0}">
            	<c:set var="searchType" value="本<span name='searchType'>日</span>"/>
            	<c:set var="searchType2" value="<span name='searchType'>日</span>"/>
                </c:if>
                <c:if test="${summaryLaborDto.dateType ==  1}">
                <c:set var="searchType" value="本<span name='searchType'>周</span>"/>
                <c:set var="searchType2" value="<span name='searchType'>周</span>"/>
                </c:if>
                <c:if test="${summaryLaborDto.dateType ==  2 || summaryLaborDto.dateType ==  3}">
                <c:set var="searchType" value="本<span name='searchType'>月</span>"/>
                <c:set var="searchType2" value="<span name='searchType'>月</span>"/>
                </c:if>
                <c:if test="${summaryLaborDto.dateType ==  4}">
                <c:set var="searchType" value="本<span name='searchType'>年</span>"/>
                <c:set var="searchType2" value="<span name='searchType'>年</span>"/>
                </c:if>       	
        	</c:otherwise>
        </c:choose>
            <ul>
            	<li>                	
                    <fmt:formatNumber value="${summaryLaborDto.totalLaborIncome}" pattern="##.##" minFractionDigits="2" var="income" />
                    <h1>${income}</h1>
                    <p>${searchType}劳动业绩</p>
                </li>
                <li>
                	<fmt:formatNumber value="${summaryLaborDto.lastTotalLaborIncome}" pattern="##.##" minFractionDigits="2" var="lastIncome" />
                    <h1>${lastIncome}</h1>
                    <p>上${searchType2}劳动业绩</p>
                </li>
                <li>                	
                    <fmt:formatNumber value="${summaryLaborDto.incomeGrowthRate}" pattern="##.##" minFractionDigits="2" var="incomeRate" />
                    <h1 class="red">${incomeRate}%</h1>
                    <p>增长率</p>
                </li>
                <li>                	
                    <h1>${summaryLaborDto.totalServicedCustomer}</h1>
                    <p>${searchType}总客数</p>
                </li>
                <li>                	
                    <h1>${summaryLaborDto.lastTotalServicedCustomer}</h1>
                    <p>上${searchType2}总客数</p>
                </li>
                <li>                	
                    <fmt:formatNumber value="${summaryLaborDto.customerPrice}" pattern="##.##" minFractionDigits="2" var="CustomerUnitCost" />
                    <h1>${CustomerUnitCost}</h1>
                    <p>${searchType}客单价</p>
                </li>
                <li>                	
                    <fmt:formatNumber value="${summaryLaborDto.lastCustomerPrice}" pattern="##.##" minFractionDigits="2" var="lastCustomerUnitCost" />
                    <h1>${lastCustomerUnitCost}</h1>
                    <p>上${searchType2}客单价</p>
                </li>
            </ul>
        </div>

        <div id="custom-toolbar" style="margin-bottom: 20px;" >
            <div class="table-toolbar" style="margin-bottom: 20px;">
               <c:if test="${not empty summaryLaborDto.branchStores}">
            		<span>门店</span>
            		<select id="pid" onchange="changeStore()">
            			<c:forEach items="${summaryLaborDto.branchStores }" var="Store">
            				<option grade="${Store.storeId }" id="${Store.storeId}">${Store.storeName }</option>
            			</c:forEach>
            		</select>
            	</c:if>
                <span class="mr10">日期区间</span>
                <input type="text" class="datetimepicker input80" daysOffset="0" id="startDate" name="startDate"/>－
                <input type="text" class="datetimepicker input80" daysOffset="0" id="endDate" name="endDate"/>&nbsp;&nbsp;&nbsp;&nbsp;
                <button class="button-search btn" style="margin-left: -10px;" onclick="querySummary();">查询</button>
                <div class="select-target-report">
                	
                	<span onclick="changeDate(0,this);" class="<c:choose><c:when test="${not empty summaryLaborDto.dateType and 0 == summaryLaborDto.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日</a></span>
                    <span onclick="changeDate(1,this);" class="<c:choose><c:when test="${not empty summaryLaborDto.dateType and 1 == summaryLaborDto.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">周</a></span>
                    <span onclick="changeDate(2,this);" class="<c:choose><c:when test="${not empty summaryLaborDto.dateType and 2 == summaryLaborDto.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月</a></span>
                    <span onclick="changeDate(3,this);" class="<c:choose><c:when test="${not empty summaryLaborDto.dateType and 3 == summaryLaborDto.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">日趋势</a></span>
                    <span onclick="changeDate(4,this);" class="<c:choose><c:when test="${not empty summaryLaborDto.dateType and 4 == summaryLaborDto.dateType }">curent-report ml30</c:when><c:otherwise>report-category ml10</c:otherwise></c:choose>"><a href="javascript:void(0);">月趋势</a></span>
                
                </div>
            </div>
        </div><!--custom-toolbar-->

        <div class="widgetcontent">
            <div id="cash-day" style="width: 1050px;height:400px;<c:if test="${not empty summaryLaborDto.dateType and (summaryLaborDto.dateType == 3 or summaryLaborDto.dateType == 4) }">display:none;</c:if>"></div>
            <div id="cash-day2" style="width: 1050px;height:400px;<c:if test="${empty summaryLaborDto.dateType or summaryLaborDto.dateType == 0 or summaryLaborDto.dateType == 1 or summaryLaborDto.dateType == 2 }">display:none;</c:if>"></div>
        </div>

        <!--门店劳动业绩分类汇总-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店劳动业绩分类汇总</span>
                    <span class="fr">时间：<span id="tableDate">                  
                    	${summaryLaborDto.begin }-${summaryLaborDto.end }
                    </span> 
                    <c:choose>
			           <c:when test="${not empty summaryLaborDto.dateType and 2 == summaryLaborDto.dateType || 3 == summaryLaborDto.dateType || 4 == summaryLaborDto.dateType}">
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

            <table id="export" class="table table-bordered dep-border-table table-dash" style="border-bottom:1px solid #123234;">
                <thead>
                <tr>
                    <th >部门</th>
                    <th >系列</th>
                    <th >项目</th>
                    <th >数量</th>
                    <th >数量占比</th>
                    <th >金额</th>
                    <th >金额占比</th>
                </tr>
                </thead>
                <tbody>
                
                <c:forEach items="${summaryLaborDto.deptsSummary}" var="dept" varStatus="deptStatus">
                	
                	<c:forEach items="${dept.categoryList}" var="category" varStatus="categoryStatus">
                		<c:forEach items="${category.projectList}" var="project" varStatus="projectStatus">
	                		<tr class="series${category.categoryId} dept${dept.deptId}" data-deptid="dept${dept.deptId}" data-categorynum="${fn:length(dept.categoryList)}">
                				
                				<c:if test="${categoryStatus.index == 0 and projectStatus.index == 0}">
			                		<td name ="deptIdTD"  deptid="${dept.deptId}">${dept.deptName}</td>
                				</c:if>
                				
                				<c:choose>
                					<c:when test="${projectStatus.index == 0}">
                					
	                					<td class="series-td"  data-belong-serise="series${category.categoryId}" id="${category.categoryId}">${category.categoryName}<!-- <span class="iconfont icon-xiangshang ml5"></span> -->
	                					<i class="FontAwesome iconfa-caret-down afont ml8 "></i>
	                					</td>
	                					<td class="categorycategory light-td">${project.projectName }
	                					
	                					 </td>
				                		<td class="light-td">
					                		<c:choose>
					                			<c:when test="${empty project.projectSales.salesCount}">
					                			<c:set var="count" value="0"></c:set>
					                			<c:if test="${projectStatus.index==0}">
					                			<c:set var="count" value="${project.projectCount}"></c:set>
					                			</c:if>
					                			</c:when>
					                			<c:otherwise>
					                			<c:set var="count" value="${project.projectSales.salesCount}"></c:set>
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
					                		<fmt:formatNumber value="${numRate}" pattern="##.##" minFractionDigits="2" var="rate" />${rate }%
				                		</td>
				                		<td class="light-td">
				                		<c:choose>
								           <c:when test="${not empty summaryLaborDto.dateType and 2 == summaryLaborDto.dateType || 3 == summaryLaborDto.dateType || 4 == summaryLaborDto.dateType}">
								               	 <fmt:formatNumber value="${project.projectIncome/10000}" pattern="##.##" minFractionDigits="2" var="projectIncome" />
								           </c:when>
								           <c:otherwise>
								                 <fmt:formatNumber value="${project.projectIncome}" pattern="##.##" minFractionDigits="2" var="projectIncome" />
								           </c:otherwise>
								       </c:choose>
				                		${projectIncome}
				                		</td>
				                		<td class="light-td">
				                			<c:choose>
					                			<c:when test="${summaryLaborDto.totalLaborIncome>0}">
					                				<c:set value="${project.projectIncome/summaryLaborDto.totalLaborIncome*100}" var="amtRate"></c:set>
					                			</c:when>	
					                			<c:otherwise>
					                				<c:set value="0.00" var="amtRate"></c:set>
					                			</c:otherwise>		                			
					                		</c:choose>			                		
					                		<fmt:formatNumber value="${amtRate}" pattern="##.##" minFractionDigits="2" var="rate" />${rate }%		                		
				                		</td>
	                				</c:when>
	                				<c:otherwise>
	                					<td class="categorycategory">${project.projectName } </td>
				                		<td >
					                		<c:choose>
					                			<c:when test="${empty project.projectSales.salesCount}">
					                			<c:set var="count" value="0"></c:set>
					                			<c:if test="${projectStatus.index==0}">
					                			<c:set var="count" value="${project.projectCount}"></c:set>
					                			</c:if>
					                			</c:when>
					                			<c:otherwise>
					                			<c:set var="count" value="${project.projectSales.salesCount}"></c:set>
					                			</c:otherwise>
					                		</c:choose>
					                        ${count}
				                		</td>
				                		<td >
					                		<c:choose>
					                			<c:when test="${dept.deptSales>0}">
					                				<c:set value="${count/dept.deptSales*100}" var="numRate"></c:set>
					                			</c:when>	
					                			<c:otherwise>
					                				<c:set value="0.00" var="numRate"></c:set>
					                			</c:otherwise>		                			
					                		</c:choose>			                		
					                		<fmt:formatNumber value="${numRate}" pattern="##.##" minFractionDigits="2" var="rate" />${rate }%
				                		</td>
				                		<td >
				                		<c:choose>
								           <c:when test="${not empty summaryLaborDto.dateType and 2 == summaryLaborDto.dateType || 3 == summaryLaborDto.dateType || 4 == summaryLaborDto.dateType}">
								               	 ${project.projectIncome/10000} 
								           </c:when>
								           <c:otherwise>
								                 ${project.projectIncome}
								           </c:otherwise>
								       </c:choose>
				                		</td>
				                		<td >
				                			<c:choose>
					                			<c:when test="${summaryLaborDto.totalLaborIncome>0}">
					                				<c:set value="${project.projectIncome/summaryLaborDto.totalLaborIncome*100}" var="amtRate"></c:set>
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
          <!--         <tr>
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
                  <tr class="zhanbi-tr huizong-tr" >
                      <td>门店总计</td>
                      <td></td>
                      <td></td>
                      <td>${summaryLaborDto.totalServicedCustomer }</td>
                      <td>
                      <c:set var="numRate" value="0"/><c:if test="${summaryLaborDto.totalServicedCustomer!=0 }" ><c:set var="numRate" value="100"></c:set></c:if>
					  ${numRate}%
                      </td>
                      <td>
                      <c:choose>
							<c:when test="${not empty summaryLaborDto.dateType and 2 == summaryLaborDto.dateType || 3 == summaryLaborDto.dateType || 4 == summaryLaborDto.dateType}">
								 ${summaryLaborDto.totalLaborIncome /10000} 
							</c:when>
							<c:otherwise>
								 ${summaryLaborDto.totalLaborIncome }
							</c:otherwise>
					  </c:choose>
                      </td>
                      <td>
                      <c:set var="amtRate" value="0"/><c:if test="${summaryLaborDto.totalLaborIncome!=0 }" ><c:set var="amtRate" value="100"></c:set></c:if>
					  ${amtRate}%
                      </td>
                  </tr>
                </tbody>
            </table>
              <div class="more-toolbar">
               <div class="table-toolbar">
                   <div class="s-btn-group fr">
                       <button class="btn ml10" onclick ="exportTable('export')">
                           <img src="http://7xkv8r.com1.z0.glb.clouddn.com/out_icon.png" alt="" class="vatp">
                           <span>导出</span>
                       </button>
                       <button class="btn ml10" onclick="printTable('export')">
                           <span>打印</span>
                       </button>
                   </div>
               </div>
               <div class="clearfix"></div>
           </div>
        </div>
        <!--项目劳动业绩排行-->
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">项目劳动业绩排行</span>
                    <span class="fr">时间：<span id="tableDate">${summaryLaborDto.begin }-${summaryLaborDto.end }</span> 
                    <c:choose>
			           <c:when test="${not empty summaryLaborDto.dateType and 2 == summaryLaborDto.dateType || 3 == summaryLaborDto.dateType || 4 == summaryLaborDto.dateType}">
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
            <table class="table table-bordered table-striped dep-border-table table-dash" id="projectLaborPerformanceRank">
                <thead>
                <tr>
                    <th>项目名称</th>
                    <th style="position: relative">
                        <span class="dropdown-toggle" data-toggle="dropdown">
                            所属部门
                            <i class="FontAwesome iconfa-caret-down afont ml8 " ></i>
                        </span>
                        <ul class="dropdown-menu">
                        <li><a href="javascript:sortByNum('${summaryLaborDto.storeId }','${summaryLaborDto.begin}','${summaryLaborDto.end}','${summaryLaborDto.dateType}')">全店</a></li>                        	
                        	<c:forEach items="${summaryLaborDto.idForName}" var="myMap">
                        		<li>
                        			<a href="javascript:sortByDept('${myMap.key}','${summaryLaborDto.dateType}','${myMap.value}')">${myMap.value}</a>
                        			<!-- <a href="#"></a> -->
                        		</li>
                        	</c:forEach>                         
                        </ul>
                    </th>
                    <th>总业绩
                    	<!-- 
                        <div class="paixu">
                            <a href="javascript:sortByNum('2','${summaryLaborDto.begin}','${summaryLaborDto.end}','${summaryLaborDto.dateType}')"><i class="FontAwesome iconfa-caret-up afont ml8"></i></a>
                            <a href="javascript:sortByNum('1','${summaryLaborDto.begin}','${summaryLaborDto.end}','${summaryLaborDto.dateType}')"><i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i></a>
                        </div>
                         -->
                    </th>
                    <th>数量                    	
                    	<!-- 
                        <div class="paixu">
                            <a href="javascript:sortByNum('4','${summaryLaborDto.begin}','${summaryLaborDto.end}','${summaryLaborDto.dateType}')"><i class="FontAwesome iconfa-caret-up afont ml8"></i></a>
                            <a href="javascript:sortByNum('3','${summaryLaborDto.begin}','${summaryLaborDto.end}','${summaryLaborDto.dateType}')"><i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i></a>
                        </div>
                         -->                         
                    </th>
                    
                    <!-- 
                    <th>客单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                     -->
                    <th>平均服务时长</th>
                    <th>本期业绩排行</th>
                    <th>上期业绩排行</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${summaryLaborDto.projectLaborAchievement}" var="rank">
                <tr>
                	<td>${rank.projectName}</td>
                	<td>${rank.deptName}</td>
                	<td>
                	<c:choose>
			           <c:when test="${not empty summaryLaborDto.dateType and 2 == summaryLaborDto.dateType || 3 == summaryLaborDto.dateType || 4 == summaryLaborDto.dateType}">
			               	 ${rank.projectSummary/10000}
			           </c:when>
			           <c:otherwise>
			                 	${rank.projectSummary}
			           </c:otherwise>
			       </c:choose>
                	</td>
                	<td>${rank.projectNum}</td>                	
                	<td>${rank.avgServiceTime}</td>
                	<td>${rank.projectRank}</td>
                	<td>
                	${rank.lastProjectRank }
                	</td>
                </tr>
                </c:forEach>
                </tbody>
       <!--          <tfoot>
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
        <!--项目劳动业绩排行-->
        
        
        <!-- 
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">项目劳动业绩排行</span>
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
                    <th>数量
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>客单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>平均服务时长</th>
                    <th>本期业绩店内排行</th>
                    <th>上期业绩店内排行</th>
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
                    <td>30分钟</td>
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
                    <td>30分钟</td>
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
                    <td>30分钟</td>
                    <td>1</td>
                    <td>3</td>
                    <td>罗湖店 358</td>
                </tr>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="9">
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
        </div>
        
         -->
        
        <!--门店劳动业绩排行-->
        
        <!-- 
        
        <div class="widgetcontent">
            <div class="more-toolbar" >
                <div class="table-toolbar">
                    <span class="font-size-16 btn-color mr10">门店劳动业绩排行</span>
                    <span class="fr">时间：<span id="tableDate">2015年2月4日</span> 单位：元</span>
                </div>
                <div class="clearfix"></div>
            </div>
            <table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th>门店</th>
                    <th style="position: relative">
                        总客数
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>总业绩
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>客单价
                        <div class="paixu">
                            <i class="FontAwesome iconfa-caret-up afont ml8"></i>
                            <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
                        </div>
                    </th>
                    <th>本期总业绩排行</th>
                    <th>上期总业绩排行</th>
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
                <tfoot>
                <tr>
                    <td colspan="6">
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
        </div>
        
         -->
        
    </div>

</div>

<script type="text/javascript">

    var date = new Date();
    var syear = date.getFullYear() + '年';
    var smonth = date.getFullYear() + '年1月1日 － ' + date.getFullYear() + '年12月' + date.getDaysInMonth() + '日';
    var sweekStart = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + (1 - date.getDay() + date.getDate()) + '日';
    var sweekEnd = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + (7 - date.getDay() + date.getDate()) + '日';
    var sweek = sweekStart + ' － ' + sweekEnd;
    var sdate = date.getFullYear() + '年' + (date.getMonth() + 1) + '月' + date.getDate() + '日';
    jQuery(function () {
        initDay();
        <c:choose>
        <c:when test="${empty summaryLaborDto.dateType}">var interval = 50;</c:when>
        <c:otherwise>var interval = 1000;</c:otherwise>
        </c:choose>
        <c:choose>
        <c:when test="${empty summaryLaborDto.dateType}">var date_type = -1;</c:when>
        <c:otherwise>var date_type = ${summaryLaborDto.dateType};</c:otherwise>
        </c:choose>
		 var colors = Highcharts.getOptions().colors,
	         categories = [
							<c:forEach items="${summaryLaborDto.deptsSummary }" var="dept" varStatus="deptStatus">
							'${dept.deptName }'<c:if test="${not deptStatus.last }">,</c:if>
							</c:forEach>
	                       ],
	         name = '收入分布',
	         data = [
						<c:forEach items="${summaryLaborDto.deptsSummary }" var="dept" varStatus="deptStatus">
							{
					            name:'${dept.deptName}',
								y: ${dept.deptIncome },
					             color: colors[${deptStatus.index}],
					             drilldown: 
					            
					            {
					                 name: '${dept.deptName}',
					                 categories: [
													<c:forEach items="${dept.categoryList}" var="category" varStatus="categoryStatus">
														'${category.categoryName}'
														<c:if test="${not categoryStatus.last}">,</c:if>
													</c:forEach>
					                              ],
					                 data: [
													<c:forEach items="${dept.categoryList}" var="category" varStatus="categoryStatus">
													${category.categoryIncomeSummary}
													<c:if test="${not categoryStatus.last}">,</c:if>
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
            },false);
            chart.redraw();
        }

        var chart = jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '劳动业绩汇总'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
            	// type: 'category'
            	 categories: categories,
            	 tickPixelInterval: interval
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
                    text: '金额(元)'
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
                            s = this.x +'劳动业绩合计'+':<b>￥'+ this.y +'</b><br/>';
                    if (point.drilldown) {
                        s += '查看'+ point.category +'各系列详情';
                    } else {
                        s += '点击返回';
                    }
                    return s;
                }
            },
            series: [{
                name: "部门",
                colorByPoint: true,
                data: data
            }],
            drilldown: {
                series: [
                  <c:forEach items="${summaryLaborDto.deptsSummary }" var="dept" varStatus="deptStatus">
                  {
                	  id:'${dept.deptName}',
                	  data:[
							<c:forEach items="${dept.categoryList}" var="category" varStatus="categoryStatus">
							['${category.categoryName}',${category.categoryIncomeSummary}]<c:if test="${not categoryStatus.last}">,</c:if>
							</c:forEach>   	        
                	       ]
                  }
                  <c:if test="${not deptStatus.last}">,</c:if>
                  </c:forEach>
            ]},
            exporting: {
                enabled: true
            }
        }).highcharts(); // return chart
    });

    function initDay(){
    	var objBegin='${summaryLaborDto.begin}';
    	objBegin=objBegin.replace('-','/');
    	var objEnd='${summaryLaborDto.end}';
    	objEnd=objEnd.replace('-','/');
    	jQuery("#startDate").val(objBegin);
    	jQuery("#endDate").val(objEnd);
    	var objOptions=document.getElementById("pid");
    	if(objOptions!=null){
    		var storeId='${summaryLaborDto.storeId}';
        	for(var i=0; i<objOptions.length; i++){
        		if (objOptions.options[i].getAttribute("grade")==storeId){
        			objOptions.options[i].setAttribute("selected",true);
        		}
        	}
    	}
    	<c:choose>
        <c:when test="${empty summaryLaborDto.dateType}">var date_type = -1;</c:when>
        <c:otherwise>var date_type = ${summaryLaborDto.dateType};</c:otherwise>
        </c:choose>
        
        jQuery('#cash-day').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '项目消费统计'
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
                  <c:forEach items="${summaryLaborDto.deptsSummary }" var="dept" varStatus="deptStatus">
                  {
                	  y: ${dept.deptIncome},
                	  name:'${dept.deptName}',
                	  drilldown:'${dept.deptName}'
                  }
                  <c:if test="${not deptStatus.last}">,</c:if>
                  </c:forEach>   
                ]
            }],
            drilldown: {
                series: [
                  <c:forEach items="${summaryLaborDto.deptsSummary }" var="dept" varStatus="deptStatus">
                  {
                	  name:'${dept.deptName}',
                	  id:'${dept.deptName}',
                	  data:[
							<c:forEach items="${dept.categoryList}" var="category" varStatus="categoryStatus">
							['${category.categoryName}','${category.categoryIncomeSummary}']<c:if test="${not categoryStatus.last}">,</c:if>
							</c:forEach>   	        
                	       ]
                  }
                  <c:if test="${not deptStatus.last}">,</c:if>
                  </c:forEach>
            ]}
        });
        
        
        <c:choose>
        <c:when test="${summaryLaborDto.dateType==2 or summaryLaborDto.dateType==3}">var dateType2 = '日';</c:when>
        <c:otherwise>var dateType2 = '月';</c:otherwise>
        </c:choose>
        
        if(3 == date_type || date_type == 2 || date_type==4 ) {

            jQuery('#cash-day2').highcharts({
                chart: {
                    type: 'spline'
                },
                title: {
                    text: '劳动业绩('+dateType2+'走势)'
                },
                subtitle: {
                    text: smonth
                },
                xAxis: {
                    categories: [
                       <c:choose>
							<c:when test="${not empty summaryLaborDto.trendData}">
							<c:set var="trend" value="${summaryLaborDto.trendData[0]}"></c:set> 
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
                    headerFormat: '<span style="font-size:10px">' + syear + '&nbsp;{point.key}'+dateType2+'</span><table>',
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
                     <c:forEach items="${summaryLaborDto.trendData}" var="trend" varStatus="trendStatus">
                     	{
                     		name:['${summaryLaborDto.idForName[trend.deptId]}'],
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
        <c:when test="${empty summaryLaborDto.dateType}">var dateType=-1</c:when>
        <c:otherwise>var dateType=${summaryLaborDto.dateType}</c:otherwise>
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
   
     <c:forEach items="${summaryLaborDto.deptsSummary}" var="dept" varStatus="deptStatus">
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
            	if(jQuery('.' + belongSerise).first().find("td").length == 7){
                	jQuery("." + belongSerise).first().find("td:nth-child(2)").attr("rowspan",1);
                }else {
                	jQuery("." + belongSerise).first().find("td:nth-child(1)").attr("rowspan",1);
                }
            }else {
            	var deptRowLength = parseInt(currentRowLength) + targetSerise.length - 1;
            	jQuery("." + belongSerise).show();
                jQuery("." + deptId).first().find("td:first-child").attr("rowspan", deptRowLength);//rowspan = xilie.lenght
            	if(jQuery('.' + belongSerise).first().find("td").length == 7){
                	jQuery("." + belongSerise).first().find("td:nth-child(2)").attr("rowspan",targetSerise.length);
                }else {
                	jQuery("." + belongSerise).first().find("td:nth-child(1)").attr("rowspan",targetSerise.length);
                }
      	        
            }                          
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



    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

    <a href="" class="showmenu"></a>


</div><!--mainwrapper-->


<script>
var pageNo = "${page.pageNo}";
var pageSize = "${page.pageSize}";
var totalPage = "${page.totalPage}";
jQuery(function(){
	var categoryArray=jQuery("td[name='categoryId']");
	var oldCategoryId=-1;
	var categoryNum=1;
	var categoryObj="";
	var categoryNumInDept=0;
	for(var i=0;i<categoryArray.length;i++){
		var tempId=jQuery(categoryArray[i]).attr("id");
		if(oldCategoryId==tempId){
			jQuery(categoryArray[i]).remove();
			categoryNum++;
		}else{
			if(categoryObj != ""){
				//jQuery(categoryObj).attr("rowspan",categoryNum);
				categoryNum=1;
			}
			categoryNumInDept++;
			oldCategoryId=tempId;
			categoryObj=jQuery(categoryArray[i]);
		}
	}
	if(categoryObj != ""){
		//jQuery(categoryObj).attr("rowspan",categoryNum);
	}
	
	var deptObjArray = jQuery("td[name='deptIdTD']");
	var oldDeptId = -1;
	var deptNum = 1;
	var deptHeadObj = "";
	for (var i = 0; i < deptObjArray.length; i++) {
		var deptId = jQuery(deptObjArray[i]).attr("deptid");
		if (oldDeptId == deptId) {
			jQuery(deptObjArray[i]).remove();
			deptNum++;
		}
		else {
			if (deptHeadObj != "") {
				//deptHeadObj.attr("rowspan", deptNum);
				deptNum = 1;
			}
			oldDeptId = deptId;
			deptHeadObj = jQuery(deptObjArray[i]);
		}
	}
	if (deptHeadObj != "") {
		//deptHeadObj.attr("rowspan", deptNum);
	}
 });



jQuery("input[type='radio']").on("click", function(){
            var th = jQuery(this);
             var tr = th.parents("tr");
            tr.find(".check-radio").removeClass("check-after");
            if(th.is(":checked")){
                th.siblings(".check-radio").addClass("check-after");
            }
        });

</script>
<script type="text/javascript" src="<%=basePath %>js/employee/shift.js"></script>

</div>


    <!--RIGHT PANEL结束 -->


</div><!--mainwrapper-->
<form action="<%=basePath %>laborperformance/view/laborperformance" style="display:none;" id="summaryFrm" method="post">
<input type="hidden" name="begin" id="begin"/>
<input type="hidden" name="end" id="end"/>
<input type="hidden" name="storeId" id="storeId">
<input type="hidden" name="dateType" id="dateType"/>
</form>
</body>
</html>
