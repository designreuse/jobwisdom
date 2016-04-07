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
            <li><a href="<%=cssBasePath%>PresentInfo">礼金/积分列表</a></li>
            <li class="current"><a href="<%=cssBasePath%>RecommendInfo">推荐管理列表</a></li>
        </ul>
        <div class="huishou"><img src="<%=cssBasePath %>app/img/trash.png" alt=""/><span class="word">回收箱</span></div>
        <div class="tab_box">
           <!--推荐管理列表-->
            <div class="hair-setting">
                <div class="discard-card">
                    <table class="table">
                        <thead>
                        <tr class="table-title">
                            <td class="add-staff-btn" colspan="8">
                                <button type="button" class="appoints mgr10 width100" data-toggle="modal" onclick="initModal()" data-target="#treatment-tuijian-patch" style="float: left;margin-top: 5px;"> 批量设置</button>
                                <form id="p1" action="<%=cssBasePath%>selectRecommendInfo" method="POST">
                                <div class="fr" style="margin-top:5px;">
                                    <div class="per-page fl">
                                                                                                             每页显示 
                                    <select name="pageSize" id="p1_s1" onchange="selectInfosByPage()">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="5">5</option>
                                    </select>
                                    </div>
                                    <div class="page-num fl">
                                                                                                             页数 <i id="pageNo">${page.pageNo }</i>/<i id="totalPage">${page.totalPage }</i>
                                    </div>
                                    <input type="hidden" value="${page.pageNo }" name="pageNo">
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
                            <td class="main">主/开卡提成</td>
                            <td class="main">主/充值提成</td>
                            <td class="main">主/消费提成</td>
                            <td class="sub">间/开卡提成</td>
                            <td class="sub" >间/充值提成</td>
                            <td class="sub" >间/消费提成</td>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${MemberCardRecommend }" var="rec">
							<tr>
								<td style="padding-left: 0px"><input type="checkbox" id="${rec.cardId }" value="${rec.cardName }"/></td>
								<td>${rec.cardName }</td>
								<td class="ticheng">${rec.recommendMainCardCommissionType } <span class="num">${rec.recommendMainCardCommissionMoney }</span></td>
								<td class="ticheng">${rec.recommendMainRechargeCommissionType } <span class="num">${rec.recommendMainRechargeCommissionMoney }</span></td>
								<td class="ticheng">${rec.recommendMainConsumptionCommissionType } <span class="num">${rec.recommendMainConsumptionCommissionMoney }</span></td>
								<td class="ticheng">${rec.recommendSecondaryCardCommissionType } <span class="num">${rec.recommendSecondaryCardCommissionMoney }</span></td>
								<td class="ticheng">${rec.recommendSecondaryRechargeCommissionType } <span class="num">${rec.recommendSecondaryRechargeCommissionMoney }</span></td>
								<td class="ticheng">${rec.recommendSecondaryConsumptionCommissionType } <span class="num">${rec.recommendSecondaryConsumptionCommissionMoney }</span></td>
							</tr>
		                </c:forEach>
                        <tr class="hint">
                            <td scope="row" colspan="9" style="padding-left: 20px;">
                                <div class="diamonds"></div>
                                <span class="word">主 代表主推荐人 间 代表简介推荐人</span>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
     </div>
	</div>
	
    </div>
 
<!--推荐管理批量设置的模态框-->
<div class="modal fade" id="treatment-tuijian-patch" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content treatment-tuijian-patch">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h3 class="modal-title">修改会员卡</h3>
            </div>
            <div class="modal-body">
                <form id="updateRecommendInfos" action="<%=cssBasePath%>updateRecommendInfos" method="POST">
                <input type="hidden" id="cIds" name="cIds" value="">
                    <div class="f-group" id="initName">
                        <label for="">会员卡名称</label>
                        <input type="text" class="whole-line" placeholder="白金卡" disabled/>
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
                        <button type="button" class="btn cancel" >取消</button><!--data-dismiss="modal"-->
                        <button type="button" onclick="updateRecommendInfos()" class="btn confirm">确定</button>
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
	<script type="text/javascript" src="<%=cssBasePath%>js/memberCard/recommendInfo.js"></script>
	<script type="text/javascript">
	
	</script>
</body>
</html>