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
            <li class="current"><a href="<%=cssBasePath%>cutInfo">提成列表</a></li>
            <li><a href="<%=cssBasePath%>PresentInfo">礼金/积分列表</a></li>
            <li><a href="<%=cssBasePath%>RecommendInfo">推荐管理列表</a></li>
        </ul>
        <div class="tab_box">
            <!--提成列表-->
            <div class="hair-setting">
                <div class="discard-card">
                    <table class="table big-height-table">
                        <thead>
                        <tr class="table-title">
                            <td class="add-staff-btn" colspan="8">
                                <button type="button" class="batch-setting width100" data-toggle="modal" onclick="initModal()" data-target="#treatment-ticheng-patch" style="float: left;margin-top: 5px;"> 批量设置</button>
                                <form id="p1" action="<%=cssBasePath%>selectCutInfo" method="POST">
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
                            <td class="card-single-people">开卡单人提成</td>
                            <td class="card-more-people">开卡多人提成</td>
                            <td class="recharge-single-money">充值单人提成</td>
                            <td class="recharge-more-score">充值多人提成</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${MemberCardCommission }" var="cuts">
									<tr>
										<td style="padding-left: 0px"><input type="checkbox" id="${cuts.cardId }" value="${cuts.cardName }"/></td>
										<td>${cuts.cardName }</td>
									    <c:if test="${cuts.commissionType == '按固定'}">
									         <td class="ticheng">按固定 <span class="num">${cuts.commissionSingleMoney}</span>元</td>		
										</c:if>
											<c:if test="${cuts.commissionType == '按比例'}">
											<td class="ticheng">按比例 <span class="num">${cuts.commissionSingleMoney}</span>%</td>
									   </c:if>
										<td class="two-line ticheng" style="padding-left: 0px" >
											<div class="td-one-line"><span>${cuts.commissionType }</span></div>
											<div class="td-two-line">
											<c:if test="${cuts.commissionType == '按固定'}">
												<div class="line1"><span>第一人<span class="num">${cuts.ofir }</span>元 第二人 <span class="num">${cuts.osec }</span>元</span></div>
												<div class="line2"><span>第三人<span class="num">${cuts.othr }</span>元 第四人 <span class="num">${cuts.ofou }</span>元</span></div>
											</c:if>
											<c:if test="${cuts.commissionType == '按比例'}">
												<div class="line1"><span>第一人<span class="num">${cuts.ofir }</span>% 第二人 <span class="num">${cuts.osec }</span>%</span></div>
												<div class="line2"><span>第三人<span class="num">${cuts.othr }</span>% 第四人 <span class="num">${cuts.ofou }</span>%</span></div>
											</c:if>
											</div>
										</td>
									    <c:if test="${cuts.rechargeCommissionType == '按固定'}">
									         <td class="ticheng">按固定 <span class="num">${cuts.prepaidSingleCommissionMoney}</span>元</td>		
										</c:if>
											<c:if test="${cuts.rechargeCommissionType == '按比例'}">
											<td class="ticheng">按比例 <span class="num">${cuts.prepaidSingleCommissionMoney}</span>%</td>
									    </c:if>
										<td class="two-line ticheng" style="padding-left: 0px" >
											<div class="td-one-line"><span>${cuts.rechargeCommissionType }</span></div>
											<div class="td-two-line">
											<c:if test="${cuts.rechargeCommissionType == '按固定'}">
												<div class="line1"><span>第一人<span class="num">${cuts.tfir }</span>元 第二人 <span class="num">${cuts.tsec }</span>元</span></div>
												<div class="line2"><span>第三人<span class="num">${cuts.tthr }</span>元 第四人 <span class="num">${cuts.tfou }</span>元</span></div>
											</c:if>
											<c:if test="${cuts.rechargeCommissionType == '按比例'}">
												<div class="line1"><span>第一人<span class="num">${cuts.tfir }</span>% 第二人 <span class="num">${cuts.tsec }</span>%</span></div>
												<div class="line2"><span>第三人<span class="num">${cuts.tthr }</span>% 第四人 <span class="num">${cuts.tfou }</span>%</span></div>
											</c:if>
											</div>
										</td>
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

<!--提成列表批量设置的模态框-->
<div class="modal fade" id="treatment-ticheng-patch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content treatment-ticheng-patch">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">修改会员卡</h3>
            </div>
            <div class="modal-body">
                <form id="updateCutInfos" action="<%=cssBasePath%>updateCutInfos" method="POST">
                    <input type="hidden" id="cIds" name="cIds" value="">
                    <div class="f-group" id="initName">
                        <!-- <label for="">会员卡名称</label>
                        <input type="button" class="width100" placeholder="白金卡" disabled/>
                        <input type="button" class="width100" placeholder="白金卡" disabled/>
                        <input type="button" class="width100" placeholder="白金卡" disabled/> -->
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
                            <label for="">第二人</label><input type="text" name="osecond" class="input2 bg-money-img" placeholder=" 500"/>
                        </div>
                    </div>
                    <div class="f-group">
                        <label for=""></label>
                        <div class="hint-input">
                            <label for="">第三人</label>
                            <input type="text" name="othree" class="input1 bg-money-img" placeholder=" 500"/>
                        </div>
                        <div class="hint-input">
                            <label for="">第四人</label><input type="text" name="ofour" class="input2 bg-money-img" placeholder=" 500"/>
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
                            <label for="">第二人</label><input type="text" name="tsecond" class="input2 bg-money-img" placeholder=" 500"/>
                        </div>
                    </div>
                    <div class="f-group">
                        <label for=""></label>
                        <div class="hint-input">
                            <label for="">第三人</label>
                            <input type="text" name="tthree" class="input1 bg-money-img" placeholder=" 500"/>
                        </div>
                        <div class="hint-input">
                            <label for="">第四人</label><input type="text" name="tfour" class="input2 bg-money-img" placeholder=" 500"/>
                        </div>
                    </div>

                    <div class="f-button">
                        <button type="button" class="btn cancel" >取消</button><!--data-dismiss="modal"-->
                        <button type="button" onclick="updateCutInfos()" class="btn next-step">确定</button>
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
	<script type="text/javascript" src="<%=cssBasePath%>js/memberCard/cutInfo.js"></script>
	<script type="text/javascript">
	</script>
</body>
</html>