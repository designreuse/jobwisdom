<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员列表</title>
<%@include file="/css.jsp" %>
<body>
	<%@include file="/head.jsp" %>
	<div class="container">
        <%@include file="/sidebar.jsp" %>
        <div id="content" class="fl">
        	<!--二级菜单-->
		    <div class="content-submenu">
		        <ul class="submunu clearfix">
		            <li>
		                <a href="">
		                    <span>散客收银</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>会员消费</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>新增会员</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>自助账单</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>账单查询</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>顾客预约</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>动态轮牌</span>
		                </a>
		            </li>
		            <li>
		                <a href="">
		                    <span>收支记账</span>
		                </a>
		
		            </li>
		            <li class="active">
		                <a href="project_setting.html">
		                    <span>项目设置</span>
		                </a>
		            </li>
		        </ul>
		    </div>
        	
        	<!--内容区-->
		    <div class="box live-tab  setting-content">
		        <ul class="tab_menu">
		            <li class="current">会员列表</li>
		            <li>推荐列表</li>
		        </ul>
		        <div class="tab_box">
		            <!--会员列表-->
		            <div class="hair-setting">
		                <div class="customer-management">
		                    <div class="search-filter">
		                    	<form name="searchMembersForm" action="<%=cssBasePath%>members/toMembersList">
			                        <table class="table search-filter-table">
			                            <thead>
				                            <tr>
				                                <td colspan="6">
				                                    <input type="text" class="search-input" placeholder="手机号/姓名" name="membersName"/>
				                                    <button class="search-button">搜索</button>
				                                    <span class="word">更多搜索条件 </span>
				                                    <img src="<%=cssBasePath%>app/img/d-triangle.png" alt="" class="dropdown-condition down-img"/>
				                                </td>
				                            </tr>
			                            </thead>
			                            <tbody class="condition-content hide">
				                            <tr>
				                                <td class="table-label">会员卡类型 </td>
				                                <td class="tabel-input">
				                                    <input type="text" class="whole-line" name="membersType"/>
				                                </td>
				                                <td class="table-label">会员卡名称</td>
				                                <td class="tabel-input">
			                                		<select class="whole-line" name="cardNameId">
			                                			<c:forEach var="membersCardName" items="${membersCardNameList}" varStatus="status">
			                                				<option value="${membersCardName.cardNameId }">${membersCardName.cardName }</option>
			                                			</c:forEach>
			                                		</select>
				                                </td>
				                                <td class="table-label">会员性别</td>
				                                <td class="tabel-input">
				                                    <input type="text" class="whole-line" name="sex"/>
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="table-label">礼金金额</td>
				                                <td class="tabel-input">
				                                    <input type="text" class="part-two" name="cashGiftAmountStart"/>
				                                    <span>-</span>
				                                    <input type="text" class="part-two" name="cashGiftAmountEnd"/>
				                                </td>
				                                <td class="table-label">礼金余额</td>
				                                <td class="tabel-input">
				                                    <input type="text" class="part-two" name="cashGiftBalanceStart"/>
				                                    <span>-</span>
				                                    <input type="text" class="part-two" name="cashGiftBalanceEnd"/>
				                                </td>
				                                <td class="table-label">充值总额</td>
				                                <td class="tabel-input">
				                                    <input type="text" class="part-two" name="topupTotalAmountStart"/>
				                                    <span>-</span>
				                                    <input type="text" class="part-two" name="topupTotalAmountEnd"/>
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="table-label">注册时间 </td>
				                                <td class="tabel-input">
				                                    <input type="text" class="part-two form-datetime" name="registerTimeStart" />
				                                    <span>-</span>
				                                    <input type="text" class="part-two form-datetime" name="registerTimeEnd" />
				                                </td>
				                                <td class="table-label">消费金额</td>
				                                <td class="tabel-input">
				                                    <input type="text" class="part-two" name="consumeTotalAmountStart"/>
				                                    <span>-</span>
				                                    <input type="text" class="part-two" name="consumeTotalAmountEnd"/>
				                                </td>
				                                <td class="table-label">消费次数</td>
				                                <td class="tabel-input">
				                                    <input type="text" class="part-two" name="consumeNumStart"/>
				                                    <span>-</span>
				                                    <input type="text" class="part-two" name="consumeNumEnd"/>
				                                </td>
				                            </tr>
				                            <tr>
				                                <td class="table-label">账户余额</td>
				                                <td class="tabel-input">
				                                    <input type="text" class="part-two" name="accountBalanceStart"/>
				                                    <span>-</span>
				                                    <input type="text" class="part-two" name="accountBalanceEnd"/>
				                                </td>
				                                <td></td>
				                                <td></td>
				                                <td></td>
				                                <td class="tbody-search-td">
				                                    <button class="tbody-search-button">搜索</button>
				                                </td>
				                            </tr>
				                            <tr>
				                                <td colspan="6" style="height: 20px;"></td>
				                            </tr>
			                            </tbody>
			                        </table>
		                        </form>
		                    </div>
		                    <table class="table">
		                        <thead>
			                        <tr class="table-title">
			                            <td class="add-staff-btn" colspan="10">
			                                <button onclick="addMembers();" class="add " data-toggle="modal" data-target="#create-member-card" style="float: left;margin-top: 5px;">新增会员</button>
			                                <button class="add mgl10" data-toggle="modal" data-target="#create-member-card" style="float: left;margin-top: 5px;">删除会员</button>
			                                <div class="fr">
			                                    <div class="per-page fl">
			                                    	每页显示 
			                                        <select name="" id="">
			                                        	<option value="">1</option>
			                                    	</select>
			                                    </div>
			                                    <div class="page-num fl">页数 1/1</div>
			                                    <div class="page-control fl">
			                                        <button class="next-page"><span class="glyphicon glyphicon-chevron-right"></span></button>
			                                        <button class="prev-page mgr10"><span class="glyphicon glyphicon-chevron-left"></span></button>
			                                    </div>
			                                </div>
			                            </td>
			                        </tr>
			                        <tr class="p-column-name" style="height: 40px;">
			                            <td class="select-all" style="padding-left: 0px"><input type="checkbox"/></td>
			                            <td class="telephone">手机号</td>
			                            <td class="name">姓名</td>
			                            <td class="sex">性别</td>
			                            <td class="birthday">生日</td>
			                            <td class="project-discord">开卡日期</td>
			                            <td class="shop-discount" >卡类型/名称</td>
			                            <td class="validity" >储值金额/余额</td>
			                            <td class="validity" >礼金总额/余额</td>
			                            <td class="validity" >最后消费日</td>
			                        </tr>
		                        </thead>
		                        <tbody>
		                        	<c:forEach var="members" items="${membersList}" varStatus="status">
		                        		<tr>
				                            <td style="padding-left: 0px"><input type="checkbox" name="membersId" value="${members.membersId }"/></td>
				                            <td class="emphasis" onclick="updateMembers(${members.membersId});">${members.membersMobile }</td>
				                            <td>${members.membersName }</td>
				                            <td>
				                            	<c:if test="${members.sex == 0}">
				                            		女
				                            	</c:if>
				                            	<c:if test="${members.sex == 1}">
				                            		男
				                            	</c:if>
				                            </td>
				                            <td>${members.birthday }</td>
				                            <td>${members.openCardDate }</td>
				                            <td>
				                            	<c:if test="${members.membersType == 1}">
				                            		储值卡/
				                            	</c:if>
				                            	<c:if test="${members.membersType == 2}">
				                            		疗程卡/
				                            	</c:if>
				                            	${members.cardNameId }白金卡
				                            </td>
				                            <td>${members.storedTotalAmount }/${members.storedBalance }</td>
				                            <td>${members.cashGiftAmount }/${members.cashGiftBalance }</td>
				                            <td>${members.lastConsumptionDate }</td>
			                            </tr>
		                        	</c:forEach>
		                        </tbody>
		                    </table>
		                </div>
		            </div>
		
		            <!--推荐列表-->
		            <div class="hide hair-setting">
		            </div>

		        </div>
		    </div>
        
        
        </div>
   	</div>
   	
   	<%@ include file="/js.jsp"%>
   	<script>
   		var basePath = '<%=cssBasePath%>';
   		
	    $(".dropdown-condition").on("click",function(){
	        var target = $(".condition-content");
	        if(target.hasClass("hide")){
	            target.removeClass("hide");
	            $(this).parent().addClass("open");
	        }else{
	            target.addClass("hide");
	            $(this).parent().removeClass("open");
	        }
	
	    });
	</script>
	<script src="<%=cssBasePath%>js/members/membersList.js"></script>
</body>
</html>
        