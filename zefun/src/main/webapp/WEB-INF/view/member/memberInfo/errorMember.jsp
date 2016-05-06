<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style>
.taocan-table {
  min-width: 400px;
}
</style>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	<!-- 页面代码 -->
		<div class="maincontent">
		    <div class="contentinner">
		    
		    <div class="widgetcontent">
			    <div class="more-toolbar">
		            <div class="table-toolbar">
		                <span class="font-size-16 btn-color mr10">异常会员数据</span>
		            </div>
		            <div class="clearfix"></div>
		        </div><!--more-toolbar-->
		        <div class="more-toolbar">
		            <div class="table-toolbar">
		            	<a class="btn" href="<%=basePath%>member/download/error/member">导出</a>
		                <input type="search" placeholder="手机号码/姓名/卡号" id="serchMemberByNameOrPhone">
		                <button class="button-search btn width100 ml-10" onclick="serchMemberByNameOrPhoneDoc()">查询</button>
		            </div>
		            <div class="clearfix"></div>
		        </div>
	            </div>
		        
				<div class="more-toolbar">
	                <div class="table-toolbar">
	                    <span class="font-size-16 btn-color" id="searchDate">会员信息统计</span>
	
	                    <div class="table-pagination">
	                        <table class="ls-detail fr">
	                            <tbody><tr>
	                                <td>会员总人数：</td>
	                                <td id="receivableAmount">${page.totalRecord }</td>
	                                <td>当前储值余额：</td>
	                                <td id="couponAmount">${balanceAmounts.balanceAmounts }</td>
	                                <td>欠款统计：</td>
	                                <td id="couponAmount">${balanceAmounts.debtAmounts }</td>
	                            </tr>
	                        </tbody></table>
	                    </div><!--table-pagination-->
	                </div><!--table-toolbar-->
	                <div class="clearfix"></div>
	             </div>
	            <c:if test="${lastFacilitator == '盛传' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡名</th>
			                <th>卡内总余额</th>
			                <th>卡类型</th>
			                <th>折扣</th>
			                <th>储值总额</th>
			                <th>消费总额</th>
			                <th>赠送总余额</th>
			                <th>失效日期</th>
			                <th>消费次数</th>
			                <th>当前积分</th>
			                <th>欠款金额</th>
			                <th>最后消费日</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			                <!-- <th>套餐迁移</th> -->
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.levelName }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.levelType }</td>
			                <td>${member.discount }</td>
			                <td>${member.totalAmount }</td>
			                <td>${member.totalConsumeAmount }</td>
			                <td>${member.sendAmount }</td>
			                <td>${member.aeadTime }</td>
			                <td>${member.consumeAmount }</td>
			                <td>${member.balanceIntegral }</td>
			                <td>${member.debtAmount }</td>
			                <td>${member.lastConsumeTime }</td>
			                <c:if test="${member.isDeleted == 0 }">
				                <td>
				                <i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(1, ${member.id },this);"></i>
				                </td>
				                <td class="can-click m-btn" onclick="balanceAmountMove(1, ${member.id }, this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
				                <%-- <td class="can-click m-btn" onclick="comboMove(1, ${member.id }, this, 1)" style="text-decoration: none; text-align: center;"><span class="iconfont icon-icon07 "></span></td> --%>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td>
				                	<i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(1, ${member.id },this);"></i>
				                </td>
				                <td>
				                	已处理
				                </td>
				                <!-- <td>
				                	已处理
				                </td> -->
			                </c:if>
			                </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if>    
	            
	            <c:if test="${lastFacilitator == '左右' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡名</th>
			                <th>卡内总余额</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.levelName }</td>
			                <td>${member.balanceAmount }</td>
			                <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(2, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(2, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td>
				                <i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(2, ${member.id },this);"></i>
				                </td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
		                </c:forEach>
			            </tbody>
			        </table>
	            </c:if>  
	            
	            <c:if test="${lastFacilitator=='云浩企汇通' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡名</th>
			                <th>卡内总余额 </th>
			                <th>礼金余额</th>
			                <th>累计消费次数</th>
			                <th>积分余额</th>
			                <th>最后消费日期</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.levelName }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.balanceGiftmoneyAmount }</td>
			                <td>${member.consumeCount }</td>
			                <td>${member.balanceIntegral }</td>
	                        <td>${member.lastConsumeTime }</td>
	                        <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(3, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(3, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(3, ${member.id },this);"></i></td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if>   
	            <c:if test="${lastFacilitator=='博卡' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>储值总额 </th>
			                <th>卡内总余额</th>
			                <th>累计消费总额</th>
			                <th>累计消费次数</th>
			                <th>平均消费额度</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.totalAmount }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.totalConsumeAmount }</td>
			                <td>${member.consumeCount }</td>
			                <td>${member.avgConsumeAmount }</td>
			                <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(4, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(4, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(4, ${member.id },this);"></i></td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if> 
	            <c:if test="${lastFacilitator=='耕宇' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>储值总额 </th>
			                <th>卡内总余额</th>
			                <th>累计消费总额</th>
			                <th>累计消费次数</th>
			                <th>平均消费额度</th>
			                <th>欠款金额</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.totalAmount }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.totalConsumeAmount }</td>
			                <td>${member.consumeCount }</td>
			                <td>${member.avgConsumeAmount }</td>
			                <td>${member.debtAmount }</td>
			                <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(5, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(5, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(5, ${member.id },this);"></i></td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if> 
	            <c:if test="${lastFacilitator=='蓝蝶' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡内总余额</th>
			                <th>可用积分</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.balanceIntegral }</td>
			                <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(6, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(6, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(6, ${member.id },this);"></i></td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if> 
	            <c:if test="${lastFacilitator=='共赢' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡内总余额</th>
			                <th>消费总额</th>
			                <th>礼金余额</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.totalConsumeAmount }</td>
			                <td>${member.balanceGiftmoneyAmount }</td>
			                <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(7, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(7, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(7, ${member.id },this);"></i></td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if> 
	            
	            <c:if test="${lastFacilitator=='模板' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡内余额</th>
			                <th>积分余额</th>
			                <th>礼金余额</th>
			                <th>欠款金额</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.balanceIntegral }</td>
			                <td>${member.balanceGiftmoneyAmount }</td>
			                <td>${member.debtAmount }</td>
			                <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(8, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(8, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(8, ${member.id },this);"></i></td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if> 
	            <c:if test="${lastFacilitator=='西沙龙' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡名</th>
			                <th>卡号</th>
			                <th>卡内余额</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelName }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.balanceAmount }</td>
			                <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(9, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(9, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(9, ${member.id },this);"></i></td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if> 
	            
	            <c:if test="${lastFacilitator=='华拓美业' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡名</th>
			                <th>卡内总余额 </th>
			                <th>礼金余额</th>
			                <th>积分余额</th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.levelName }</td>
			                <td>${member.balanceAmount }</td>
			                <td>${member.balanceGiftmoneyAmount }</td>
			                <td>${member.balanceIntegral }</td>
	                        <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(10, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(10, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(10, ${member.id },this);"></i></td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if> 
	            
	            
	            <c:if test="${lastFacilitator=='华彩' }">
		            <table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>性别</th>
			                <th>卡号</th>
			                <th>卡名</th>
			                <th>卡内总余额 </th>
			                <th>删除</th>
			                <th>余额迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member">
			            <c:forEach items="${page.results }" var="member">
			            <tr id="err_${member.id }">
			                <td>${member.phone }</td>
			                <td>${member.name }</td>
			                <td>${member.sex }</td>
			                <td>${member.levelNum }</td>
			                <td>${member.levelName }</td>
			                <td>${member.balanceAmount }</td>
	                        <c:if test="${member.isDeleted == 0 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(11, ${member.id },this);"></i></td>
			                    <td class="can-click m-btn" onclick="balanceAmountMove(11, ${member.id },this)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
			                </c:if>
			                <c:if test="${member.isDeleted == 1 }">
				                <td><i class="iconfont icon-xx ml10" onclick="deleteErrMemberTip(11, ${member.id },this);"></i></td>
			                    <td>已处理</td>
			                </c:if>
			            </tr>
			            </c:forEach>
			            </tbody>
			        </table>
	            </c:if> 
	            
	       <%@ include file="/template/page.jsp" %>
	       <br><br><br>
	       <c:if test="${lastFacilitator == '盛传' }">
		       <!-- 盛传疗程卡 -->
		       <p></p>
			   <div class="more-toolbar">
		                <div class="table-toolbar">
		                    <span class="font-size-16 btn-color" id="searchDate">会员疗程卡信息统计</span>
		                    <div class="table-pagination">
		                        <table class="ls-detail fr">
		                            <tbody><tr>
		                                <td>会员总人数：</td>
		                                <td id="receivableAmount">${pageRumors.totalRecord }</td>
		                                <td>当前储值余额：</td>
		                                <td id="couponAmount">${course.residueAmounts }</td>
		                                <td><a class="btn" href="javascript:jQuery('#taocan').modal()">一键迁移</a></td>
		                                
		                            </tr>
		                        </tbody></table>
		                    </div><!--table-pagination-->
		                </div><!--table-toolbar-->
		                <div class="clearfix"></div>
		             </div>
	       			<table class="table table-bordered table-striped member-list-table">
			            <thead>
			            <tr>
			                <th>手机号</th>
			                <th>姓名</th>
			                <th>卡号</th>
			                <th>卡名</th>
			                <th>卡类型</th>
			                <th>疗程项目</th>
			                <th>剩余次数</th>
			                <th>剩余金额</th>
			                <th>迁移</th>
			            </tr>
			            </thead>
			            <tbody id="init_member_rumors">
			            <c:forEach items="${pageRumors.results }" var="course">
			            <tr id="course_${course.id }">
			                <td>${course.phone }</td>
			                <td style="width: 80px">${course.name }</td>
			                <td>${course.levelNum }</td>
			                <td style="width: 80px">${course.levelName }</td>
			                <td style="width: 140px">${course.levelType }</td>
			                <td>${course.courseDesc }</td>
			                <td style="width: 80px">${course.residueDegree }</td>
			                <td style="width: 80px">${course.residueAmount }</td>
			                <td style="width: 40px" class="can-click m-btn" onclick="comboMove(1, ${course.id }, this, 2)" style="text-decoration: none;text-align: center;"><span class="iconfont icon-icon07 "></span></td>
		                </tr>
			            </c:forEach>
		            </tbody>
		        </table>	
		       <!-- 盛传疗程卡 -->
		       <!-- 分页 -->
				<div class="fenye">
					<span>共找到了 ${pageRumors.totalRecord } 条数据, 共 ${pageRumors.totalPage } 页</span>
					<ul id="pagination-demo2" class="pagination pagination-sm">
					</ul>
				</div>
				
				<script type="text/javascript">
					var pageNoR = '${pageRumors.pageNo}';
					var pageSizeR = '${pageRumors.pageSize}';
					var totalPageR = '${pageRumors.totalPage}';
					var totalRecordR = '${pageRumors.totalRecord}';
					jQuery('#pagination-demo2').twbsPagination({
						totalPages : totalPageR,
						visiblePages : 5,
						onPageClick: function (event, page) {
							pageNoR = page;
							changeRumorsPage();
				        } 
					});
				</script>
				<!-- 分页 -->
	       </c:if>
	       
	       </div><!-- contentinner -->
	       <!--删除提示-->
           <div class="modal hide" id="deleteErrorMemberModel" tabindex="-1" role="dialog" aria-labelledby="deleteErrorMemberModel">
               <div class="modal-dialog" role="document">
                   <div class="modal-content confirm">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                           <h4 class="modal-title">删除提示</h4>
                       </div>
                       <div class="modal-body confirm-body">
                        	 会员资料删除后将不可恢复，您确认要删除吗？
                       </div>
                       <div class="modal-footer">
                           <a class="btn cancel-btn modal-cancel" data-dismiss="modal" href="javascript:void();">我点错了</a>
                           <a class="btn btn-primary save-btn modal-confirm" href="javascript:deleteErrMember();">确认删除</a>
                       </div>
                   </div>
               </div>
           </div>     
       </div><!-- maincontent -->
    </div><!-- rightpanel -->
</div><!--mainwrapper-->
</div>
<!--余额迁移模态框-->
<div class="modal hide" id="yueqianyi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-yichang">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"> 余额迁移</h4>
            </div>
            <div class="modal-body">
                <div class="modal-wrap">
                    <!--原系统-->
                    <div class="xitong fl">
                        <div class="xitong-top">
                           	       原系统
                        </div>
                        <div class="xitong-main">
                            <ul>
                                <li><span>会员姓名：</span> 暂无</li>
                                <li><span>电话号码：</span> 暂无</li>
                                <li><span>会员性别：</span> 暂无</li>
                                <li><span>会员卡号：</span> 暂无</li>
                                <li><span>储值余额：</span> 暂无</li>
                                <li style="display: none"><span>积分余额：</span> 暂无</li>
                                <li style="display: none"><span>礼金余额：</span> 暂无</li>
                            </ul>
                        </div>
                    </div>
                    <!--箭头-->
                    <span><img src="<%=basePath %>images/arr.png" alt=""/></span>
                    <!--智放系统-->
                    <div class="xitong fr">
                        <div class="xitong-top">
                            	智放系统
                        </div>
                        <div class="xitong-main">
                        	<div class="xt-search">
                                <span>搜索会员</span>
                                <input type="text" placeholder="会员手机号码"/>
                                <span class="iconfont icon-sousuo" onclick="changeToMemberInfo(this)"></span>
                            </div>
                            <ul>
                                <li><span>会员姓名：</span> 暂无</li>
                                <li><span>电话号码：</span> 暂无</li>
                                <li><span>会员性别：</span> 暂无</li>
                                <li><span>会员卡名：</span> 暂无</li>
                                <li><span>当前余额：</span> 暂无</li>
                                <input type="hidden" name="memberId">
                            </ul>
                        </div>
                    </div>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" id="balandMove">确定迁移</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->


<!--套餐迁移模态框-->
<div class="modal hide" id="taocanqianyi" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-yichang">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"> 套餐迁移</h4>
            </div>
            <div class="modal-body">
                <div class="modal-wrap">
                    <!--原系统-->
                    <div class="xitong fl">
                        <div class="xitong-top">
                            	原系统
                        </div>
                        <div class="xitong-main">
                            <ul>
                                <li><span>会员姓名：</span> 暂无</li>
                                <li><span>电话号码：</span> 暂无</li>
                                <li><span>会员性别：</span> 暂无</li>
                                <li><span>会员卡号：</span> 暂无</li>
                                <li><span>项目描述：</span> 暂无</li>
                            </ul>
                        </div>
                    </div>
                    <!--箭头-->
                    <span><img src="<%=basePath %>images/arr.png" alt=""/></span>
                    <!--智放系统-->
                    <div class="xitong fr" style="padding-bottom: 60px;">
                        <div class="xitong-top">
                           		 智放系统
                        </div>
                        <div class="xitong-main">
                        	<div class="xt-search">
                                <span>搜索会员</span>
                                <input type="text" placeholder="会员手机号码"/>
                                <span class="iconfont icon-sousuo" onclick="comboPhone(this)"></span>
                            </div>
                            <ul>
                            	<li><span>会员姓名:</span>暂无</li>
                            	<li>
                            		<span>选择套餐:</span>
                            		<select data-placeholder="选择套餐" class="chzn-select input-medium" style="width:178px !important;" onchange="changeCombo(this)" name="comboId">
	                                	<option value="0" >请选择一个套餐</option>
	                                	<c:forEach items="${comboInfos }" var="comboInfo" >
	                                		<option value="${comboInfo.comboId }">${comboInfo.comboName }</option>
	                           			</c:forEach>
	                                </select>
                                </li>
                                <li><span>过期时间:</span>
                                <input type="text" name="overdueTime" placeholder="截止时间"  class="timePickerClean width100"></li>
                                <input type="hidden" name="memberId">
                            </ul>
                        </div>
                    </div>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" id="comboMove">确定迁移</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->

<div class="modal hide" id="taocan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="width: 400px">
    <div class="modal-dialog" role="document">
        <div class="modal-content taocan-table" >
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel"> 套餐迁移</h4>
            </div>
            <div class="modal-body">
                <table class="table table-bordered table-striped ">
                    <thead>
                    <tr>
                        <th>
                           	疗程卡
                        </th>
                        <th>套餐</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${projectNames }" var="projectName" varStatus="s">
	                    <tr class="tcqy">
	                        <td data_id="${projectName }">项目名称 : ${projectName }</td>
	                        <td>
								<select data-placeholder="选择套餐"  class="chzn-select input80" name="comboId" >
		                        	<c:forEach var="combo" items="${comboInfos }" >
		                        		<option value="${combo.comboId }">${combo.comboName }</option>
		                        	</c:forEach>
		                        </select>
							</td>
	                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">

                    <button class="btn" onclick="qy()">确定</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->
</body>
<script type="text/javascript">
//获取加载页面时的页码信息
var pageNo = '${page.pageNo}';	
var pageSize = '${page.pageSize}';	
var totalPage = '${page.totalPage}';	
var totalRecord = '${page.totalRecord}';
var lastFacilitator = '${lastFacilitator}';	
//盛传疗程卡
var pageNoR = '${pageRumors.pageNo}';	
var pageSizeR = '${pageRumors.pageSize}';	
var totalPageR = '${pageRumors.totalPage}';	
var totalRecordR = '${pageRumors.totalRecord}';
</script>
<script type="text/javascript" src="<%=basePath%>js/member/errorMember.js"></script>
<script type="text/javascript" src="<%=basePath%>js/member/errorMemberMove.js"></script>
<script type="text/javascript" >
function qy(){
	var query = new Array();
	jQuery(".tcqy").each(function(){
		var eId = jQuery(this).children("td").eq(0).attr('data_id');
		var cId = jQuery(this).children("td").eq(1).find('select').val()
		var data = {};
		data['name'] = eId;
		data['comboId'] = cId;
		query.push(data);
	})
	var data = {};
	data['type'] = 1;
	data['list'] = query;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "member/move/combo/info/all",
		contentType: "application/json",
        data: JSON.stringify(data),
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("提交成功,即将刷新页面...");
		}
	});
	
}
</script>
</html>