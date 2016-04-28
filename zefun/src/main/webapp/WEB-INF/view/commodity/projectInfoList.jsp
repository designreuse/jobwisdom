<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<style type="text/css">
.rollBox{width:704px;padding:12px 0 5px 6px;}
.rollBox .LeftBotton{height:70px;width:50px;background:url('<%=basePath%>images/left_click.png') no-repeat 11px 0;overflow:hidden;float:left;display:inline;margin:25px 0 0 0;cursor:pointer;background-size:40px;position:relative;top:290px;left:-180px}
.rollBox .RightBotton{height:70px;width:50px;background:url('<%=basePath%>images/right_click.png') no-repeat -8px 0;overflow:hidden;float:right;display:inline;margin:25px 0 0 0;cursor:pointer;background-size:40px;position:relative;    top: -340px;
    left: 350px;}
.rollBox .Cont{width:910px;overflow:hidden;float:left;margin-left:70px}
.rollBox .ScrCont{width:10000000px;}
.rollBox .Cont .pic{float:left;text-align:center;}
.rollBox .Cont .pic img{padding:4px;background:#fff;border:1px solid #ccc;display:block;margin:0 auto;}
.rollBox .Cont .pic p{line-height:26px;color:#505050;}
.rollBox .Cont a:link,.rollBox .Cont a:visited{color:#626466;text-decoration:none;}
.rollBox .Cont a:hover{color:#f00;text-decoration:underline;}
.rollBox #List1,.rollBox #List2{float:left;}
</style>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<!--headerpanel-->
				<div class="write_input">
					<div class="div1">编辑</div>
					<div class="write_5" style="background: white; float: left; top: 0px !important; left: 20px !important; width: 100px !important">
						<span class="add_step" style="position: relative; left: -10px">+</span>新增
					</div>
				</div>
				<!--项目设置 -->
				<div class="alertPanel" style="">
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
					<div class="LeftBotton" onmousedown="ISL_GoUp()" onmouseup="ISL_StopUp()"></div>
					<div class="Cont" id="ISL_Cont">
						<div class="ScrCont">
							<div id="List1">
								<c:forEach items="${projectInfos }" var="project" step="2" varStatus="status">
									<div class="pic">
									<div class="column_small_first " action-type="showdesc">
										<div class="column_img_container">
											<div class="head_pic">
												<img src="<%=basePath%>images/head_pic.png">
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
									<div class="column_small_first " action-type="showdesc">
										<div class="column_img_container">
											<div class="head_pic">
												<img src="<%=basePath%>images/head_pic.png">
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
					<div class="RightBotton" onmousedown="ISL_GoDown()" onmouseup="ISL_StopDown()"></div>
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
</html>