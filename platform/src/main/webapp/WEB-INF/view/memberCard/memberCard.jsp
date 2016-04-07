<%@page import="java.util.List"%>
<%@page import="com.lives.platform.web.entity.Page"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目</title>
<%@include file="/css.jsp" %>
<link rel="stylesheet" href="<%=cssBasePath %>css/superdropdown.css"/>
<body>
	<%@include file="/head.jsp" %>
	<div class="container">
        <%@include file="/sidebar.jsp" %>
        
        <div id="content" class="fl">
         <!--二级菜单-->
		<div class="content-submenu">
	        <ul class="submunu clearfix">
	            <li class="active">
	                <a href="<%=cssBasePath %>selectVipCardsByPage">
	                    <span>储值卡</span>
	                </a>
	            </li>
	            <li>
	                <a href="<%=cssBasePath %>selectCoursesByPage">
	                    <span>疗程卡</span>
	                </a>
	            </li>
	            <li>
	                <a href="member_search.html">
	                    <span>会员查询</span>
	                </a>
	            </li>
	        </ul>
	    </div>
		   	<!--内容区-->
    <div class="box live-tab  setting-content">
    <ul class="tab_menu">
            <li class="current"><a href="<%=cssBasePath%>memberCard">会员卡列表</a></li>
            <li><a href="<%=cssBasePath%>cutInfo">提成列表</a></li>
            <li><a href="<%=cssBasePath%>PresentInfo">礼金/积分列表</a></li>
            <li><a href="<%=cssBasePath%>RecommendInfo">推荐管理列表</a></li>
        </ul>
        <div class="tab_box">
            <!--折扣列表-->
            <div class="hair-setting">
                <div class="discard-card">
                    <table class="table">
                        <thead>
                        <tr class="table-title">
                            <td class="add-staff-btn" colspan="8">
                                <button class="add " data-toggle="modal" data-target="#create-member-card" style="float: left;margin-top: 5px;">添加会员卡</button>
                                <form id="p1" action="<%=cssBasePath%>selectVipCardsByPage" method="POST">
                                <div class="fr">
                                    <div class="per-page fl">
                                    	每页显示 
	                                    <select name="pageSize" id="p1_s1" onchange="selectInfosByPage()">
	                                        <option value="1">1</option>
	                                        <option value="5">5</option>
	                                        <option value="15">15</option>
	                                        <option value="20">20</option>
	                                    </select>
                                        <input type="hidden" value="${page.pageNo }" name="pageNo">
                                    </div>
                                    <div class="page-num fl">
                                                                                                             页数 <i id="pageNo">${page.pageNo }</i>/<i id="totalPage">${page.totalPage }</i>
                                    </div>
                                    <div class="page-control fl">
                                        <span onclick="pageDelNo()">上一页</span>
                                        <span onclick="pageAddNo()">下一页</span> 
                                        <!-- <button class="next-page"><span class="glyphicon glyphicon-chevron-right"></span></button> 
                                        <button class="prev-page mgr10"  ><span class="glyphicon glyphicon-chevron-left"></span></button>  -->
                                    </div>
                                </div>
                                </form>
                            </td>
                        </tr>
                        <tr class="p-column-name" style="height: 40px;">
                            <td class="code">编号</td>
                            <td class="member-card-name">会员卡名称</td>
                            <td class="card-sold-money">开卡售卖金额</td>
                            <td class="card-recharge-money">开卡充值金额</td>
                            <td class="project-discord">项目折扣</td>
                            <td class="shop-discount" >商品折扣</td>
                            <td class="validity" >有效期</td>
                            <td class="f-operation" ></td>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach items="${MemberCardInfo }" var="cards">
								<tr>
									<td>${cards.cardId}</td>
									<td>${cards.cardName}</td>
									<td>${cards.cardMoneyCash}</td>
									<td>${cards.cardMoneyRecharge}</td>
									<td>${cards.cardProjectDiscount}</td>
									<td>${cards.cardGoodsDiscount}</td>
									<c:if test="${cards.cardIsUseDate>0}">
									<td>${cards.cardIsUseDate}个月</td>
									</c:if>
									<c:if test="${cards.cardIsUseDate==0}">
									<td>永久</td>
									</c:if>
									<td>
									<img src="<%=cssBasePath%>app/img/alter.png" alt="" onclick="updateCard(${cards.cardId})" >
									<img src="<%=cssBasePath%>app/img/delete.png" onclick ="deleteCard(${cards.cardId})" alt="删除"/></td>
								</tr>
		                </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
         </div>
	</div>
	
    </div></div>
 
<!--新建会员卡的模态框-->
<div class="modal fade" id="create-member-card" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content create-member-card">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" onclick="modalReset()">&times;</span></button>
                <h3 class="modal-title">创建新项目</h3>
            </div>
            <div class="modal-body">
                <div class="box modal-tab">
                    <ul class="tab_menu">
                        <li id="l1" class="current">1.基本信息</li>
                        <li id="l2">2.提成信息</li>
                        <li id="l3">3.赠送信息</li>
                        <li id="l4">4.推介信息</li>
                    </ul>
                    <div class="tab_box">
                       <!-- 1.基本信息-->
                        <div id="d1">
                            <form id="f_s_u"  action="<%=cssBasePath%>save" method="POST">
                                <div class="f-group">
                                    <label for="">会员卡名称</label>
                                    <input type="hidden"  name="cardId"/>
                                    <input type="hidden"  name="cardType" value="1"/>
                                    <input type="text" id="initCardName" class="whole-line" name="cardName" placeholder="白金卡"/>
                                </div>
                                <div class="f-group">
                                    <label for="">项目折扣</label>
                                    <input type="text" name="cardProjectDiscount" class="whole-line bg-percent-img"/>
                                </div>
                                <div class="f-group">
                                    <label for="">商品折扣</label>
                                    <input type="text" name="cardGoodsDiscount" class="whole-line bg-percent-img"/>
                                </div>
                                <div class="f-group">
                                    <label for="" class="form-part-title">开卡规则</label>
                                </div>
                                <div class="f-group">
                                    <label for="">充值开卡</label>
                                    <input type="text" name="cardMoneyRecharge" class="whole-line bg-money-img"/>
                                </div>
                                <div class="f-group">
                                    <label for="">现金售卡</label>
                                    <input type="text" name="cardMoneyCash" class="whole-line bg-money-img"/>
                                </div>
                                <div class="f-group">
                                    <label for="">有效期限</label>
                                    
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "v_c_date" supname = "supname" style="width: 109px;height: 28px;">永久</button>
										   <input type="hidden" name="cardIsUseDate" value="0">
										   <ul class="dropdown-menu" style="width: 109px;">
										   <c:forEach items="${isUse }" var="isUse">
										   <c:if test="${isUse.codeNo==0 }">
										   <li><a href="javascript://" heddenValue="${isUse.codeNo }" supa = "supa" >永久</a></li>
										   </c:if>
										   <c:if test="${isUse.codeNo!=0 }">
										   <li><a href="javascript://" heddenValue="${isUse.codeNo }" supa = "supa" >${isUse.codeName }</a></li>
										   <li class="divider"></li>	
										   </c:if>
										   </c:forEach>
										   </ul>
								 	</div>
								 	
                                    <!-- <input type="text" name="cIsUseDate" class="whole-line bg-percent-img"/> -->
                                </div>
                                <div class="f-group select-tag">
                                    <label for="">短信通知</label>
                                    <input type="checkbox" onclick="checkBoxChangeValue(this)" value="0" class="check-all" name="cardIsMessage"/>
                                    <span></span>
                                                                                                             结账信息
                                </div>
                                <div class="f-button">
                                    <button type="button" class="btn btn-default cancel" onclick="modalReset()" data-dismiss="modal">取消</button>
                                    <button type="button" onclick="change_current('1','2')" class="btn btn-primary confirm">下一步</button>
                                </div>
                        </div>

                        <!--2.提成信息-->
                        <div class="hide" id="d2">
                                <div class="f-group">
                                    <label for="">会员卡名称</label>
                                    <input type="text" id="initCardName2" class="whole-line" placeholder="白金卡" disabled/>
                                </div>
                                <div class="f-group">
                                    <label for="" class="form-part-title">开卡规则</label>
                                </div>
                                <div class="f-group">
                                    <label for="">提成方式</label>
                                    
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "c_i_date" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="commissionType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="a('commissionSingleMoney,ofirst,osecond,othree,ofour')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="b('commissionSingleMoney,ofirst,osecond,othree,ofour')">按比例</a></li>
										   </ul>
								 	</div>
                                </div>
                                <div class="f-group">
                                    <label for="">单人提成</label>
                                    <input type="text" name="commissionSingleMoney" class="input1 bg-money-img"/>
                                </div>
                                <div class="f-group">
                                    <label for="">多人提成</label>
                                    <div class="hint-input">
                                        <label for="">第一人</label>
                                        <input type="text" name="ofirst" class="input1 bg-money-img" placeholder=" 500"/>
                                    </div>
                                    <div class="hint-input">
                                        <label for="">第二人</label>
                                        <input type="text" name="osecond" class="input1 bg-money-img" placeholder=" 500"/>
                                    </div>
                                </div>
                                <div class="f-group">
                                    <label for=""></label>
                                    <div class="hint-input">
                                        <label for="">第三人</label>
                                        <input type="text" name="othree" class="input1 bg-money-img" placeholder=" 500"/>
                                    </div>
                                    <div class="hint-input">
                                        <label for="">第四人</label>
                                        <input type="text" name="ofour" class="input1 bg-money-img" placeholder=" 500"/>
                                    </div>
                                </div>


                                <div class="f-group">
                                    <label for="" class="form-part-title">充值提成</label>
                                </div>
                                <div class="f-group">
                                    <label for="">提成方式</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "c_y_date" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="rechargeCommissionType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="a('prepaidSingleCommissionMoney,tfirst,tsecond,tthree,tfour')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="b('prepaidSingleCommissionMoney,tfirst,tsecond,tthree,tfour')">按比例</a></li>
										   </ul>
								 	</div>
                                </div>
                                <div class="f-group">
                                    <label for="">单人提成</label>
                                    
                                    <input type="text" name="prepaidSingleCommissionMoney" class="input1 bg-money-img" value="500"/>
                                </div>
                                <div class="f-group">
                                    <label for="">多人提成</label>
                                    <div class="hint-input">
                                        <label for="">第一人</label>
                                        <input type="text" name="tfirst" class="input1 bg-money-img" placeholder=" 500"/>
                                    </div>
                                    <div class="hint-input">
                                        <label for="">第一人</label>
                                        <input type="text" name="tsecond" class="input1 bg-money-img" placeholder=" 500"/>
                                    </div>
                                </div>
                                <div class="f-group">
                                    <label for=""></label>
                                    <div class="hint-input">
                                        <label for="">第一人</label>
                                        <input type="text" name="tthree" class="input1 bg-money-img" placeholder=" 500"/>
                                    </div>
                                    <div class="hint-input">
                                        <label for="">第一人</label>
                                        <input type="text" name="tfour" class="input1 bg-money-img" placeholder=" 500"/>
                                    </div>
                                </div>

                                <div class="f-button">
                                    <button type="button" class="btn prev-step" >上一步</button><!--data-dismiss="modal"-->
                                    <button type="button" onclick="change_current('2','3')" class="btn next-step">下一步</button>
                                </div>
                        </div>

                        <!--3.赠送信息-->
                        <div class="hide" id="d3">
                                <div class="f-group">
                                    <label for="">会员卡名称</label>
                                    <input type="text" id="initCardName3" class="whole-line" placeholder="白金卡" disabled/>
                                </div>

                                <div class="f-group">
                                    <label for="" class="form-part-title">开卡赠送</label>
                                </div>
                                <div class="f-group">
                                    <label for="">礼金</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "p_i_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="giveCashType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="c('giveCashMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="d('giveCashMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="giveCashMoney" class="part1 mgl10 bg-money-img"/>
                                    
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "p_y_date" supname = "supname" style="width: 109px;height: 28px;">一次赠送</button>
										   <input type="hidden" name="giveCashDate" value="0">
										   <ul class="dropdown-menu" style="width: 109px;">
										   <c:forEach items="${isUse }" var="isUse">
										   <c:if test="${isUse.codeNo==0 }">
										   <li><a href="javascript://" heddenValue="${isUse.codeNo }" supa = "supa" >${isUse.codeName }</a></li>	
                                           <li class="divider"></li>
										   </c:if>
										   <c:if test="${isUse.codeNo!=0 }">
										   <li><a href="javascript://" heddenValue="${isUse.codeNo }" supa = "supa" >分期${isUse.codeName }</a></li>	
                                           <li class="divider"></li>
										   </c:if>
										   </c:forEach>
										   </ul>
								 	</div>
                                </div>
                                <div class="f-group">
                                    <label for="">积分</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "p_x_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="giveIntegralType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="c('giveIntegralMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="d('giveIntegralMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="giveIntegralMoney" class="part1 mgl10 bg-money-img"/>
                                </div>

                                <div class="f-group">
                                    <label for="" class="form-part-title">充值赠送</label>
                                </div>
                                <div class="f-group">
                                    <label for="">礼金</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "p_u_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="giveRechargeType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="c('giveRechargeMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="d('giveRechargeMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="giveRechargeMoney" class="part1 mgl10 bg-money-img"/>
                                    
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "p_o_date" supname = "supname" style="width: 109px;height: 28px;">一次赠送</button>
										   <input type="hidden" name="giveRechargeDate" value="0">
										   <ul class="dropdown-menu" style="width: 109px;">
										    <c:forEach items="${isUse }" var="isUse">
										   <c:if test="${isUse.codeNo==0 }">
										   <li><a href="javascript://" heddenValue="${isUse.codeNo }" supa = "supa" >${isUse.codeName }</a></li>	
                                           <li class="divider"></li>
										   </c:if>
										   <c:if test="${isUse.codeNo!=0 }">
										   <li><a href="javascript://" heddenValue="${isUse.codeNo }" supa = "supa" >分期${isUse.codeName }</a></li>	
                                           <li class="divider"></li>
										   </c:if>
										   </c:forEach>
										   </ul>
								 	</div>
                                </div>
                                <div class="f-group">
                                    <label for="">积分</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "p_j_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="giveRechargeIntegralType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="c('giveRechargeIntegralMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="d('giveRechargeIntegralMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="giveRechargeIntegralMoney" class="part1 mgl10 bg-money-img"/>
                                </div>

                                <div class="f-group">
                                    <label for="" class="form-part-title">消费赠送</label>
                                </div>
                                <div class="f-group">
                                    <label for="">积分</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "p_q_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="giveConsumptionIntegralType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="c('giveConsumptionIntegralMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="d('giveConsumptionIntegralMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="giveConsumptionIntegralMoney" class="part1 mgl10 bg-money-img"/>
                                </div>

                                <div class="f-button">
                                    <button type="button" class="btn prev-step" >上一步</button><!--data-dismiss="modal"-->
                                    <button type="button" onclick="change_current('3','4')" class="btn next-step">下一步</button>
                                </div>
                        </div>

                        <!--4.推介信息-->
                        <div class="hide" id="d4">
                                <div class="f-group">
                                    <label for="">会员卡名称</label>
                                    <input type="text" id="initCardName4" class="whole-line" placeholder="白金卡" disabled/>
                                </div>

                                <div class="f-group">
                                    <label for="" class="form-part-title">主推荐人</label>
                                </div>
                                <div class="f-group">
                                    <label for="">开卡提成</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "r_a_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="recommendMainCardCommissionType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="e('recommendMainCardCommissionMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="f('recommendMainCardCommissionMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="recommendMainCardCommissionMoney" class="input2 bg-money-img"/>
                                </div>
                                <div class="f-group">
                                    <label for="">充值提成</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "r_b_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="recommendMainRechargeCommissionType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="e('recommendMainRechargeCommissionMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="f('recommendMainRechargeCommissionMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="recommendMainRechargeCommissionMoney" class="input2 bg-money-img"/>
                                </div>
                                <div class="f-group">
                                    <label for="">消费提成</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "r_c_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="recommendMainConsumptionCommissionType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="e('recommendMainConsumptionCommissionMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="f('recommendMainConsumptionCommissionMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="recommendMainConsumptionCommissionMoney" class="input2 bg-money-img"/>
                                </div>

                                <div class="f-group">
                                    <label for="" class="form-part-title">间接推荐人</label>
                                </div>
                                <div class="f-group">
                                    <label for="">开卡提成</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "r_d_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="recommendSecondaryCardCommissionType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="e('recommendSecondaryCardCommissionMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="f('recommendSecondaryCardCommissionMoney')" >按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="recommendSecondaryCardCommissionMoney" class="input2 bg-money-img"/>
                                </div>
                                <div class="f-group">
                                    <label for="">充值提成</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "r_e_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="recommendSecondaryRechargeCommissionType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="e('recommendSecondaryRechargeCommissionMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="f('recommendSecondaryRechargeCommissionMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="recommendSecondaryRechargeCommissionMoney" class="input2 bg-money-img"/>
                                </div>
                                <div class="f-group">
                                    <label for="">消费提成</label>
                                    <div class="btn-group dropdown" supdiv = "supdiv">
										   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "r_f_data" supname = "supname" style="width: 109px;height: 28px;">按固定</button>
										   <input type="hidden" name="recommendSecondaryConsumptionCommissionType" value="按固定">
										   <ul class="dropdown-menu" style="width: 109px;">
                                              <li><a href="javascript://" heddenValue="按固定" supa = "supa" onclick="e('recommendSecondaryConsumptionCommissionMoney')">按固定</a></li>	
                                              <li class="divider"></li>			
                                              <li><a href="javascript://" heddenValue="按比例" supa = "supa" onclick="f('recommendSecondaryConsumptionCommissionMoney')">按比例</a></li>
										   </ul>
								 	</div>
                                    <input type="text" name="recommendSecondaryConsumptionCommissionMoney" class="input2 bg-money-img"/>
                                </div>

                                <div class="f-button">
                                    <button type="button" class="btn prev-step" >上一步</button><!--data-dismiss="modal"-->
                                    <button type="submit" class="btn confirm">确定保存</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!--新建规则的模态框-->
<div  class="modal fade" id="rule-setting" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content rule-setting">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">规则设置</h3>
            </div>
            <div class="modal-body">
                <form action="">
                    <div class="f-group">
                        <label for="" class="form-part-title">礼金规则</label>
                    </div>
                    <div class="f-group">
                        <label for="">项目消费</label>
                        <input type="text" class="whole-line"/>
                    </div>
                    <div class="f-group">
                        <label for="">商品消费</label>
                        <input type="text" class="whole-line"/>
                    </div>
                    <div class="f-group select-tag">
                        <label for="">是否清零</label>
                        <input type="radio" name="lijin-qingling"  class="check-all"/>
                        <span></span>
                        月底清零
                        <input type="radio" name="lijin-qingling" class="check-all"/>
                        <span></span>
                        累计不清零
                    </div>
                    <div class="f-group" >
                        <label for="" class="form-part-title">积分规则</label>
                    </div>
                    <div class="f-group">
                        <div class="f-group select-tag">
                            <label for="">是否清零</label>
                            <input type="radio" name="jifen-qingling"  class="check-all"/>
                            <span></span>
                            月底清零
                            <input type="radio" name="jifen-qingling" class="check-all"/>
                            <span></span>
                            累计不清零
                        </div>
                    </div>
                    <div class="f-button">
                        <button type="button" class="btn prev-step" data-dismiss="modal">上一步</button>
                        <button type="button" class="btn next-step">下一步</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- 修改会员卡模态框 -->
<div class="modal fade" id="fixhuiyuanka" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content fixhuiyuanka">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">修改会员卡</h3>
            </div>
            <div class="modal-body">
                <!-- 1.基本信息-->
                <form action="<%=cssBasePath%>memberCard/realyUpdate" method="POST">
                        <div class="f-group">
                            <label for="">会员卡名称</label>
							<input type="hidden"  name="cardId"/>
                            <input type="hidden"  name="cardType" value="1"/>
                            <input type="text" name="cardName" class="whole-line" value=""/>
                        </div>
                        <div class="f-group">
                            <label for="">项目折扣</label>
							<input type="text" name="cardProjectDiscount" class="whole-line bg-percent-img"/>
                        </div>
                        <div class="f-group">
                            <label for="">商品折扣</label>
                            <input type="text" name="cardGoodsDiscount" class="whole-line bg-percent-img"/>
                        </div>
                        <div class="f-group">
                            <label for="" class="form-part-title">开卡规则</label>
                        </div>
                        <div class="f-group">
                            <label for="">充值开卡</label>
                            <input type="text" name="cardMoneyRecharge" class="whole-line bg-money-img"/>
                        </div>
                        <div class="f-group">
                            <label for="">现金售卡</label>
                            <input type="text" name="cardMoneyCash" class="whole-line bg-money-img"/>
                        </div>
                        <div class="f-group">
                            <label for="">有效期限</label>
                            <div class="btn-group dropdown" supdiv = "supdiv">
							   <button type="button" class="dropDownIcon" data-toggle="dropdown" id = "v_c_date" supname = "supname" style="width: 109px;height: 28px;">永久</button>
							   <input type="hidden" name="cardIsUseDate" value="0">
							   <ul class="dropdown-menu" style="width: 109px;">
							   <c:forEach items="${isUse }" var="isUse">
							   <c:if test="${isUse.codeNo==0 }">
							   <li><a href="javascript://" heddenValue="${isUse.codeNo }" supa = "supa" >永久</a></li>
							   </c:if>
							   <c:if test="${isUse.codeNo!=0 }">
							   <li><a href="javascript://" heddenValue="${isUse.codeNo }" supa = "supa" >${isUse.codeName }</a></li>
							   <li class="divider"></li>	
							   </c:if>
							   </c:forEach>
							   </ul>
							</div>
                        </div>
                        <div class="f-group select-tag">
                            <label for="">短信通知</label>
                            <input type="checkbox" onclick="checkBoxChangeValue(this)" value="0" class="check-all" name="cardIsMessage"/>
                            <span></span>
                                                                                    结账信息
                        </div>
                        <div class="f-button">
                            <button type="button" class="btn btn-default cancel" data-dismiss="modal">取消</button>
                            <button type="submit" class="btn btn-primary confirm">确认</button>
                        </div>
                    </form>
            </div>
        </div>
    </div>
</div>
    <%@ include file="/js.jsp"%>
	<script type="text/javascript">
		var basePath = '<%=cssBasePath%>';
		/**分页*/
		var totalPage = ${page.totalPage };
		var pageNo = ${page.pageNo};
		var pageSize = ${page.pageSize};
	</script>
	<script type="text/javascript" src="<%=cssBasePath%>js/memberCard/memberCard.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){  
	    $("#initCardName").bind("blur",function(){
	    	$("#initCardName2").attr("placeholder",$("#initCardName").val());
	    	$("#initCardName3").attr("placeholder",$("#initCardName").val());
	    	$("#initCardName4").attr("placeholder",$("#initCardName").val());
	    });  
	}); 
	</script>
</body>
</html>