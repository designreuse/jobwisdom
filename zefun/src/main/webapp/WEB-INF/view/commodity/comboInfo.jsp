<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>

<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<script>
	//轮播
	jQuery(function() {
		var count = jQuery('.rollBox_content ul').size(), len = count * 900, now = 0;

		jQuery('.rollBox_content_ul').css('width', len);
		jQuery('.LeftButton').click(function() {
			if (now >= 1) {
				now -= 1;
				jQuery('.rollBox_content_ul').stop(true, true).animate({
					left : -900 * now
				})
			}
		})

		jQuery('.RightButton').click(function() {
			if (now <= count - 2) {
				now += 1;
				jQuery('.rollBox_content_ul').stop(true, true).animate({
					left : -900 * now
				})
			}
		})
	})

	//选择全部
	jQuery(function() {
		jQuery('.setting_first em').hover(function() {
			jQuery(this).parent().find('.setting_all').show();
			jQuery(this).find('i').addClass('active')

		}, function() {
			jQuery(this).parent().find('.setting_all').hide();
			jQuery(this).find('i').removeClass('active')

		})
	})
	// 遮罩层
	jQuery(function() {
		jQuery('.rollBox_content_ul li').hover(function() {
			jQuery(this).find('.rollBox_top_mask').stop(true, true).fadeIn();

		}, function() {
			jQuery(this).find('.rollBox_top_mask').stop(true, true).fadeOut();
		})
	})
</script>

<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right">
					<div class="setting_first">${deptInfoList }
						<a href="<%=basePath %>comboInfo/setting"><button>新增</button></a>
						<span>部门 <em><i class="" id="dept">全部<img src="<%=basePath%>images/setting_down.png" style="position: relative; left: 10px; top: 1px; width: 15px"></i>
								<ul class="setting_all clearfix" style="display: none;">
									<li onclick="changeDept(0, this)">全部</li>
									<c:forEach items="${deptGoodsBaseDto }" var="deptInfoList"><li onclick="changeDept(${deptInfoList.deptId }, this)" deptId="${deptInfoList.deptId }">${deptInfoList.deptName }</li></c:forEach>
								</ul> </em></span> <span class="total_text">共<i>${fn:length(comboInfos) }个</i>项目，已完成创建<i>${hasFinish }个</i>，未完成创建<i>${fn:length(comboInfos)-hasFinish }个</i></span>
					</div>
					<div class="rollBox">
						<div class="LeftButton">
							<img src="<%=basePath%>images/left_click.png">
						</div>
						<div class="rollBox_content">
							<div class="rollBox_content_ul" >
							<c:forEach items="${comboInfos }" var="comboInfo" step="12" varStatus="index">
								<c:set var="count" value="${index.count*12 }"></c:set>
								<ul>
									<c:forEach var="i" begin="0" end="11">
									<c:if test="${fn:length(comboInfos) >= (count-i)}">
									<li>
										<div class="setting_write">
											<a href="<%=basePath %>comboInfo/setting?comboId=${comboInfos[count-i-1].comboId }"><img style="width: 18px;height: 18px" src="<%=basePath%>images/coupon_write.png"></a>
										</div>
										<div class="setting_close">
											<img class="close1" comboId="${comboInfos[count-i-1].comboId }" src="<%=basePath%>images/setting_close.png">
										</div>
										<div class="rollBox_top">
											<img src="<%=qiniuPath%>${comboInfos[count-i-1].comboImage }">
										</div>
										<div class="rollBox_center">
											<p>名称：${comboInfos[count-i-1].comboName }</p>
											<p>部门：${comboInfos[count-i-1].deptName }</p>
										</div>
										<div class="rollBox_bottom clearfix">
											<div class="rollBox_bottom_num">
												<p>编号</p>
												<span>${comboInfos[count-i-1].comboCodeSuffix}</span>
											</div>
											<div class="rollBox_bottom_price">
												<p>门店价格</p>
												<span>${comboInfos[count-i-1].comboSalePrice}</span>
											</div>
										</div>
										<c:if test="${comboInfos[count-i-1].comboSalePrice == null}"><div class="setting_mask">未完成</div></c:if>
									</li>
									</c:if>
									</c:forEach>
								</ul>
							</c:forEach>
							</div>
						</div>
						<div class="RightButton">
							<img src="<%=basePath%>images/right_click.png">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>js/base/roll.js"></script>
<script>
	var deptId = null;
	function changeDept(deptId, li){
		var path = "";
		if (deptId == 0){
			path = baseUrl + "comboInfo/view/comboInfoList";
		}else{
			path = baseUrl + "comboInfo/view/comboInfoList" + "?deptId=" + deptId;
		}
		window.location.href = path;
	}
	function selectDeptInfo(deptId){
		jQuery("#dept").text(jQuery("li[deptId='"+deptId+"']").text());
	}
	if ('${deptId}'!=''){
		deptId = '${deptId}';
		selectDeptInfo(deptId);
	}
	jQuery(".close1").on("click", function() {
		if (confirm("确定要删除该套餐么?")) {
			var comboId = jQuery(this).attr("comboId");
			jQuery.ajax({
				cache : true,
				type : "POST",
				url : baseUrl + "comboInfo/deleteComboInfo",
				data : "comboId=" + comboId,
				async : false,
				error : function(request) {
					dialog("Connection error");
				},
				success : function(data) {
					if (data.code == 0) {
						dialog("删除成功");
						location.reload();
					} else {
						dialog(data.msg);
					}
				}
			});
		}
		return false;
	});
</script>
</html>