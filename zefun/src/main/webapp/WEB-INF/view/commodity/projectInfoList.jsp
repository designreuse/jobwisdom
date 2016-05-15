<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/roll.css" type="text/css" />
<style>
.close1 img{
  transition: transform 1s ease-out;
    }
.close1 img:hover{
  
 transform: rotate(360deg);
}

</style>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<!--headerpanel-->
				<div class="write_input">
					<!-- <div class="div1">编辑</div> -->
					<a href="<%=basePath%>project/view/projectList"><div class="write_5" style="background: white; float: left; top: 0px !important; left: 20px !important; width: 100px !important">
						<span class="add_step" style="position: relative; left: -10px">+</span>新增
					</div></a>
				</div>
				<!--项目设置 -->
				<div class="alertPanel" style="display: none">
				<c:forEach items="${deptProjectList }" var="deptPorject">
					<div class="part_panel">
						<p>${deptPorject.deptName }</p>
						<c:forEach items="${deptPorject.projectCategoryList }" var="projectCategory">
						<dl class="clearfix">
							<dt>${projectCategory.categoryName }</dt>
							<c:forEach items="${projectCategory.projectList }" var="project">
								<dd>${project.projectName }</dd>
							</c:forEach>
						</dl>
						</c:forEach>
					</div>
				</c:forEach>
				</div>
				<div class="rollBox">
					<div class="LeftBotton"></div>
					<div class="Cont" id="ISL_Cont">
						<div class="ScrCont">
							<div id="List1">
								<c:forEach items="${projectInfos }" var="project" step="2" varStatus="status">
									<div class="pic">
									<div class="column_small_first " action-type="showdesc" onclick='window.open("<%=basePath %>project/view/projectList?projectId=${projectInfos[status.count*2-2].projectId }","_self")'>
										<div class="column_img_container" style="position:relatiev">
										   <span deptId="${projectInfos[status.count*2-2].deptId}" projectId="${projectInfos[status.count*2-2].projectId }" class="close1" style="position:absolute;right:25px;top:15px;"><img src="<%=basePath%>images/close1.png" style="width:20px"></span>
											<div class="head_pic">
												<img src="<%=picPath%>${projectInfos[status.count*2-2].projectImage}">
											</div>
											<em class="boss_cut">${projectInfos[status.count*2-2].projectName }</em>
											<p class="hair">
												<img src="<%=basePath%>images/partment.png">${projectInfos[status.count*2-2].deptName }
											</p>
											<ul class="clearfix shop_number">
												<li><em class="number_">编号</em>
													<p class="num_">${projectInfos[status.count*2-2].projectCodeSuffix }</p>
													<p></p></li>
												<li><em class="shop_price">门店价格</em>
													<p>${projectInfos[status.count*2-2].projectPrice }</p>
													<p></p></li>
											</ul>
										</div>
									</div>
									
									<c:if test="${(status.count*2-1)!=fn:length(projectInfos) }">
									<div class="column_small_first " action-type="showdesc" onclick='window.open("<%=basePath %>project/view/projectList?projectId=${projectInfos[status.count*2-1].projectId }","_self")'>
										<div class="column_img_container">
										<span deptId="${projectInfos[status.count*2-1].deptId}" projectId="${projectInfos[status.count*2-1].projectId }" class="close1" style="position:absolute;right:25px;top:275px;"><img src="<%=basePath%>images/close1.png" style="width:20px"></span>
											<div class="head_pic">
												<img src="<%=picPath%>${projectInfos[status.count*2-1].projectImage}">
											</div>
											<em class="boss_cut">${projectInfos[status.count*2-1].projectName }</em>
											<p class="hair">
												<img src="<%=basePath%>images/partment.png">${projectInfos[status.count*2-1].deptName }
											</p>
											<ul class="clearfix shop_number">
												<li><em class="number_">编号</em>
													<p class="num_">${projectInfos[status.count*2-1].projectCodeSuffix }</p>
													<p></p></li>
												<li><em class="shop_price">门店价格</em>
													<p>${projectInfos[status.count*2-1].projectPrice }</p>
													<p></p></li>
											</ul>
										</div>
									</div>
									</c:if>
								</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="RightBotton"></div>
				</div>
				<div class="project-category">

					<ul class="project-sublist hide">
						<li class="project-sublist-title">洗剪吹系列 <span class="fr"> <i class="iconfa-plus project-icon"></i> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
							<div class="clearfix"></div>
						</li>
						<li class="project-sublist-content nopadding">
							<div class="left-li">单剪</div>
							<div class="right-li">
								<span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
								</span>
							</div>
							<div class="clearfix"></div>
						</li>
						<li class="project-sublist-content nopadding">
							<div class="left-li">洗剪吹</div>
							<div class="right-li">
								<span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
								</span>
							</div>
							<div class="clearfix"></div>
						</li>
						<li class="project-sublist-content nopadding">
							<div class="left-li">泰式洗发</div>
							<div class="right-li">
								<span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
								</span>
							</div>
							<div class="clearfix"></div>
						</li>
					</ul>
					<ul class="project-sublist hide ">
						<li class="project-sublist-title">烫染系列 <span class="fr"> <i class="iconfa-plus project-icon"></i> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
						</li>
						<li class="project-sublist-content">欧莱雅 烫发 <span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
						</li>
						<li class="project-sublist-content">轩尼诗染发 <span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
						</li>
						<li class="project-sublist-content">欧莱雅烫染 <span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
						</li>
					</ul>
				</div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>js/commodity/project.js"></script>
<script type="text/javascript" src="<%=basePath%>js/base/roll.js"></script>
<script>
jQuery(".close1").on("click", function (){
	if(confirm("确定要删除该项目么?")){
		var projectId = jQuery(this).attr("projectId");
		var deptId = jQuery(this).attr("deptId"); 
		jQuery.ajax({
	        cache: true,
	        type: "POST",
	        url: baseUrl+"project/deleteProject",
	        data: "projectId="+ projectId + "&deptId=" + deptId,
	        async: false,
	        error: function(request) {
	            dialog("Connection error");
	        },
	        success: function(data) {
	            if(data.code == 0){
	            	dialog("删除成功");
	            	location.reload();
	            }else{
	            	dialog(data.msg);
	            }
	        }
	    });
	}
	return false;
});
</script>
</html>