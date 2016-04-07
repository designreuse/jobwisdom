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
	                <a href="keguan.html">
	                    <span>储值卡</span>
	                </a>
	            </li>
	            <li>
	                <a href="treatment.html">
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
            <li><a href="<%=cssBasePath%>memberCard">会员卡列表</a></li>
            <li><a href="<%=cssBasePath%>cutInfo">提成列表</a></li>
            <li class="current"><a href="<%=cssBasePath%>PresentInfo">礼金/积分列表</a></li>
            <li><a href="<%=cssBasePath%>RecommendInfo">推荐管理列表</a></li>
        </ul>
        <div class="tab_box">

             <!--礼金/积分列表-->
            <div class="hair-setting">
                <div class="discard-card">
                    <table class="table">
                        <thead>
                        <tr class="table-title">
                            <td class="add-staff-btn" colspan="8">
                                <button type="button" class="appoints width100" data-toggle="modal" data-target="#treatment-lijin-patch" onclick="initModal()" style="float: left;margin-top: 5px;"> 批量设置</button>
                                <button class="add mgr10" data-toggle="modal" data-target="#rule-setting" style="float: left; margin-top: 5px; margin-left: 10px;">规则设置</button>
                                <form id="p1" action="<%=cssBasePath%>selectPresentInfo" method="POST">
                                <div class="fr" style="margin-top:5px;">
                                    <div class="per-page fl">
                                                                                                              每页显示 
                                    <select name="pageSize" id="p1_s1" onchange="selectInfosByPage()">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="5">5</option>
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
                                        <button class="prev-page mgr10"><span class="glyphicon glyphicon-chevron-left"></span></button> -->
                                    </div>
                                </div>
                                </form>
                            </td>
                        </tr>
                        <tr class="p-column-name" style="height: 40px;">
                            <td class="select-all" style="padding-left: 0px"><input id="totalBoxs" type="checkbox" onclick="initCheckBoxs(this)"/></td>
                            <td class="value-card-name">储值卡名称</td>
                            <td class="send-money">开卡送礼金</td>
                            <td class="send-score">开卡送积分</td>
                            <td class="recharge-money">充值送礼金</td>
                            <td class="recharge-score">充值送积分</td>
                            <td class="consumption-score" >消费送积分</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${MemberCardGive }" var="pre">
								<tr>
									<td style="padding-left: 0px"><input type="checkbox" id="${pre.cardId }" value="${pre.cardName }"/></td>
									<td>${pre.cardName }</td>
									<c:if test="${pre.giveCashDate>0 }">
									<td class="ticheng">${pre.giveCashType} <span class="num">${pre.giveCashMoney}</span> 分期${pre.giveCashType}个月</td>
									</c:if>
									<c:if test="${pre.giveCashDate==0 }">
									<td class="ticheng">${pre.giveCashType} <span class="num">${pre.giveCashMoney}</span> 一次赠送</td>
									</c:if>
									<td class="ticheng">${pre.giveIntegralType} <span class="num">${pre.giveIntegralMoney}</span></td>
									
									<c:if test="${pre.giveRechargeDate>0 }">
									<td class="ticheng"><span class="num">${pre.giveRechargeMoney}</span> 分期${pre.giveRechargeDate}个月</td>
									</c:if>
									<c:if test="${pre.giveRechargeDate==0 }">
									<td class="ticheng"><span class="num">${pre.giveRechargeMoney}</span> 一次赠送</td>
									</c:if>
									<td class="ticheng">${pre.giveRechargeIntegralType} <span class="num">${pre.giveRechargeIntegralMoney}</span></td>
									<td class="ticheng">${pre.giveConsumptionIntegralType} <span class="num">${pre.giveConsumptionIntegralMoney}</span></td>
								</tr>
		                </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div> 
        </div>
    </div>
   </div>
	
 </div>
 
<!--礼金/积分批量设置的模态框-->
<div class="modal fade" id="treatment-lijin-patch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content treatment-lijin-patch">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">修改会员卡</h3>
            </div>
            <div class="modal-body">
                <form id="updatePresentInfos" action="<%=cssBasePath%>updatePresentInfos" method="POST">
                <input type="hidden" id="cIds" name="cIds" value="">
                    <div class="f-group" id="initName">
                        <label for="">会员卡名称</label>
                        <input type="text" class="whole-line" placeholder="白金卡" disabled/>
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
                        <button type="button" class="btn prev-step" >取消</button><!--data-dismiss="modal"-->
                        <button type="button" onclick="updatePresentInfos()" class="btn next-step">修改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!--规则设置的模态框-->
<div class="modal fade" id="rule-setting" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                        <button type="button" class="btn cancel" data-dismiss="modal">取消</button>
                        <button type="button" class="btn confirm">确定</button>
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
	<script type="text/javascript" src="<%=cssBasePath%>js/memberCard/presentInfo.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>