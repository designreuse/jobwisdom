<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/vip_group.css" type="text/css" />
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right clearfix">
					<div class="vip_group_left">
						<p>会员分组</p>
						<div class="vip_group_left_content">
							<c:forEach items="${groupDtos }" var="groupDto" varStatus="index">
								<div id="${groupDto.groupId }" class="vip_group_left_content_detail">
									<p>
										<span>${groupDto.groupName }</span><em>${groupDto.memberCount }人</em>
									</p>
									<div class="save_price">储值余额：${groupDto.groupCondition }</div>
									<div class="create_time">
										<span>创建日期：${groupDto.createTime }</span> <em>本月新增:${groupDto.newMember }人</em>
									</div>
								</div>
							</c:forEach>
						</div>

						<div class="vip_group_bottom">
							<span class="delete_group">删除</span>
						</div>
					</div>

					<div class="vip_group_right">
						<table>
							<thead>
								<tr>
									<td>手机号</td>
									<td>姓名</td>
									<td>性别</td>
									<td>生日</td>
									<td>储值余额</td>
									<td>积分余额</td>
									<td>累计消费</td>
									<td>消费均额</td>
									<td>注册日期</td>
								</tr>
							</thead>
							<tbody id="serch_member_list">
								
							</tbody>
						</table>
						<div class="fenye hide">
							<span>共找到了 0  条数据, 共 0  页</span>
							<ul id="pagination-demo" class="pagination pagination-sm">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="/template/memberData.jsp"%>
</body>
<script>
var id = null;
/* jQuery(function(){
  jQuery(document).on('click','.vip_group_left_content_detail',function(){
    jQuery(this).addClass('active').siblings().removeClass('active');
  });
})   */
 </script>
<script>
	//获取加载页面时的页码信息
	var pageNo = 1;
	var pageSize = 50;
	var totalPage = 0;
	var totalRecord = 0;
	var type = '${type}';
</script>
<script src="<%=basePath %>js/member/group-list.js" type="text/javascript"></script>

</html>