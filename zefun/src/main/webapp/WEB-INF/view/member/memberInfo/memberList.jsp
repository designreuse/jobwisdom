<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="<%=basePath %>css/vip_data.css" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<body>
	<script>
		jQuery(function() {
			jQuery('.content_right>p>span').click(function() {
				if (jQuery('.data_select').css('display') == 'none') {
					jQuery('.data_select').slideDown();
					jQuery('.triangle-down').css('transform', 'rotate(180deg)')
				} else {
					jQuery('.data_select').slideUp();
					jQuery('.triangle-down').css('transform', 'rotate(0deg)')
				}
			})
		})
	</script>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right clearfix">
					<p>
						<input type="text" onkeyup="searchMemberLike(this)" onfocus='if(jQuery(".fuzzysearch").children("li").length>0){jQuery(".fuzzysearch").show();}' id="serchMemberByNameOrPhone" placeholder="会员卡/姓名">
						<button id="serchMemberByNameOrPhoneDoc">查询</button>
						<button style="margin-left: 40px" value="0" onclick="selectHasDeleted(this)">已冻结账户</button>
						<button>异常会员数据</button>
						<span><em class="triangle-down"></em>更多筛选信息</span>
					</p>
					<ul class="fuzzysearch" style="display: none;"></ul>
					<div class="clearfix data_select" style="display: none">
						<div class="data_select_content">
							<div>
								储值余额<span><input type="text" id="chuzhi1""><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="chuzhi2"><i>元</i></span>
							</div>
							<div>
								积分余额<span><input type="text" id="jifenye1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="jifenye2"><i>元</i></span>
							</div>
							<div>
								生<a style="display: inline-block; margin-left: 28px; color: black; cursor: text" href="javascript:">日</a><span><input type="text" id="birthday-start" onfocus="WdatePicker({dateFmt:'MM-dd'})"><i></i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="birthday-end" onfocus="WdatePicker({dateFmt:'MM-dd'})" ><i>元</i></span>
							</div>
						</div>
						<div class="data_select_content">
							<div>
								注册日期<span><input type="text" id="register-start" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="register-end" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ><i>元</i></span>
							</div>
							<div>
								消费余额<span><input type="text" id="xiaofje1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="xiaofje2"><i>元</i></span>
							</div>
							<div>
								累计消费<span><input type="text" id="leijixf1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="leijixf2"><i>元</i></span>
							</div>
						</div>
						<div class="data_select_content">
							<div>
								距上次消费<span style="margin-left: 7px"><input type="text" id="scxf1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="scxf2"><i>元</i></span>
							</div>
							<div>
								礼金金额<span><input type="text" id="ljye1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="ljye2"><i>元</i></span>
							</div>
							<div>
								挂账金额<span><input type="text" id="gzje1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="gzje2"><i>元</i></span>
							</div>
						</div>
						<div class="data_select_content_">
							<em>*</em>搜索以后可以创建会员分组
							<button id="serch">搜索</button>

						</div>
						<div class="data_select_content_" style="border-bottom: none">
							满足当前搜索条件共<em id="member_serch_count">6</em>人，你要保存该会员分组吗？<span>会员分组名称<input type="text" id="group_name"></span><i style="display: inline-block; margin-left: 100px"><button style="width: 110px; margin-left: 0" id="baocun">保存</button>
								<button style="width: 110px; margin-left: 20px" id="fangqi">取消</button></i>
						</div>
					</div>


					<div class="vip_num">
						<p>
							<span>会员数:<em>${page.totalRecord }</em></span><span>储值总额（含赠送）:<em>${storeMember.totalAmount }</em></span><span>赠送总额:<em>${storeMember.totalPresentAmount }</em>
							</span><span>储值余额:<em>${storeMember.balanceAmount }</em></span><span>礼金金额:<em>${storeMember.balanceGiftmoneyAmount }</em></span><span>挂账金额:<em>${storeMember.debtAmount }</em></span>
						</p>
						<div class="vip_num_table">
							<table>
								<tbody id="init_member">
									<tr>
										<td>手机号</td>
										<td>姓名</td>
										<td>性别</td>
										<td>储值余额</td>
										<td>积分余额</td>
										<td>累计消费</td>
										<td>消费均额</td>
										<td>礼金金额</td>
										<td>注册日期</td>
										<td>会员等级</td>
										<td>生日</td>
										<td>挂账金额</td>
										<td colspan="2">操作</td>
									</tr>
									
									<c:forEach items="${page.results }" var="member" varStatus="index">
									<tr>
										<td>${member.phone }</td>
										<td class="can-click number" data-target="#member-data" data-toggle="modal" onclick="selectMemberInfo(${member.memberId })" id="${member.memberId }">${member.name }</td>
										<td>${member.sex }</td>
										<td>${member.balanceAmount }</td>
										<td>${member.balanceIntegral }</td>
										<td>${member.totalConsumeAmount }</td>
										<td>${member.avgConsumeAmount }</td>
										<td>${member.balanceGiftmoneyAmount }</td>
										<td>${fn:substring(member.createTime, 0, 10)}</td>
										<td>
											<c:forEach items="${member.memberSubAccounts }" var="memberSubAccount">
												<p levelId="${memberSubAccount.levelId }">${memberSubAccount.levelName }</p>
											</c:forEach>
										</td>
										<td>${member.birthday }</td>

										<td>${member.debtAmount }</td>
										<td>
											<c:forEach items="${member.memberSubAccounts }" var="memberSubAccount">
												<p><button onclick="returnCard(${memberSubAccount.subAccountId}, ${memberSubAccount.levelId}, this)">退</button></p>
											</c:forEach>
										</td>
										<td><p>
												<button style="background: #4e6fb3" onclick="deletedMember(${member.memberId }, 1, this)">冻结</button>
											</p>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							<%@ include file="/template/page.jsp" %>
						</div>
					</div>
				</div>
			</div>
			<!-- rightpanel -->
		</div>
	</div>
	<!--mainwrapper-->
	<%@ include file="/template/memberData.jsp"%>
</body>


<script>
var pageNo = '${page.pageNo}';	
var pageSize = '${page.pageSize}';	
var totalPage = '${page.totalPage}';
var str = '<%=request.getAttribute("infoDtos")%>';
var memberArray = eval("(" + '<%=request.getAttribute("infoDtos")%>' + ")");
var status = null;
function returnCard(subAccountId, levelId, p){
	if(confirm("确定执行退卡操作么")){
		jQuery.ajax({
	  		type : "post",
	  		url : baseUrl + "member/action/return/card",
	  		data : "subAccountId="+subAccountId,
	  		dataType : "json",
	  		success : function(e) {
	  			if (e.code != 0) {
	  				dialog(e.msg);
	  				return;
	  			}
	  			else {
	  				jQuery(p).parents("tr").find("p[levelId='"+levelId+"']").remove();
	  				jQuery(p).parent().remove();
	  			}
	  		}
	  	});
	}
}

function deletedMember(memberId, type, tr){
	var desc ;
	if (type == 1){
		desc = "确定冻结该会员么";
	}
	else {
		desc = "确定解冻该会员么";
	}
	if(confirm(desc)){
		jQuery.ajax({
	  		type : "post",
	  		url : baseUrl + "member/action/deleted/member",
	  		data : "memberId="+memberId+"&isDeleted="+type,
	  		dataType : "json",
	  		success : function(e) {
	  			if (e.code != 0) {
	  				dialog(e.msg);
	  				return;
	  			}
	  			else {
	  				jQuery(tr).parents("tr").remove();
	  			}
	  		}
	  	});
	}
}
// 查询冻结账户
function selectHasDeleted(but){
	if (jQuery(but).val() == 1){
		jQuery(but).val('0');
		status = "";
		jQuery(but).text("已冻结账户");
	}else {
		jQuery(but).val('1');
		status = "1";
		jQuery(but).text("未冻结账户");
	}
	jQuery("#serch").click();
}
</script>
<script src="<%=basePath%>js/member/member-list.js" type="text/javascript"></script>
<script src="<%=basePath%>js/member/memberUpdate.js" type="text/javascript"></script>
</html>