<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/system_manage.css"
	type="text/css" />
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>UEditor/wenben.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>js/keepAccounts/type.js"></script>
<script>
	jQuery(function() {
		jQuery('.write_delete').hover(function() {
			jQuery(this).find('.write_delete_mask').show()

		}, function() {
			jQuery(this).find('.write_delete_mask').hide()

		})
	})

	//切换
	jQuery(
			function() {
				jQuery('.system_manage_content .income_way:gt(0)').hide();
				jQuery('.add_store_back li').click(
						function() {
							jQuery(this).addClass('active').siblings()
									.removeClass('active');
							jQuery('.system_manage_content .income_way').eq(
									jQuery(this).index()).show().siblings()
									.hide();
						})
			})
</script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper"
		style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<!--loading end-->
			<%@ include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px;">
				<%@ include file="/top.jsp"%>

				<div class="content_right clearfix">
					<div class="add_store_div clearfix">
						<ul class="clearfix add_store_back">
							<li class="active"><span>收支方式</span></li>
						</ul>
						<div class="system_manage_content">
							<div class="income_way" style="display: block;">
								<div class="income_out_way">
									<p>收入方式</p>
									<div class="income_out_way_content">
										<p>

											<button onclick="jQuery('.zzc1').show()">新增</button>
										</p>


										<div class="income_out_way_write clearfix" id="1">
											<c:forEach var="incometype" items="${incometype}">
													<div class="write_delete" id="${incometype.incometypeId }">
														<div class="write_delete_mask" style="display: none;">
															<span   onclick="showincome('${incometype.name }', 1 , ${incometype.incometypeId }, this)" value="${incometype.incometypeId } ">编辑</span>
															<span  onclick="deleted(${incometype.incometypeId }, this)" value="${incometype.incometypeId }">删除</span>
														</div>
														<em>${incometype.name }</em>
													</div>
											</c:forEach>
										</div>
									</div>

									<div class="income_out_way">
										<p>支出方式</p>	
										<div class="income_out_way_content">
											<p>
												<button onclick="jQuery('.zzc').show()">新增</button>
											</p>
											<div class="income_out_way_write clearfix" id="2">
											<c:forEach var="incometypeto" items="${incometypeto}">
													<div class="write_delete" id="${incometypeto.incometypeId }">
														<div class="write_delete_mask" style="display: none;">
															<span    onclick="showincome('${incometypeto.name }' ,2,${incometypeto.incometypeId })" value="${incometypeto.incometypeId }">编辑</span>
															<span    onclick="deleted(${incometypeto.incometypeId }, this)" value="${incometypeto.incometypeId }">删除</span>
														</div>
														<em>${incometypeto.name }</em>
													</div>
											</c:forEach>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


		<div class="zzc">
			<div class="zzc_income_way">
				<p>新增支出方式</p>
				<div class="zzc_income_way_content">
					<p>
						<input type="text" name="name">
					</p>
					<div class="zzc_income_way_content_button">
						<button onclick="saveType('2')">保存</button>
						<button onclick="jQuery('.zzc').hide()">取消</button>

					</div>
				</div>
			</div>
		</div>
		<div class="zzc1">
			<div class="zzc_income_way">
				<p>新增收入方式</p>
				<div class="zzc_income_way_content">
					<p>
						<input type="text" name="name">
					</p>
					<div class="zzc_income_way_content_button">
						<button onclick="saveType('1')">保存</button>
						<button onclick="jQuery('.zzc1').hide()">取消</button>

					</div>
				</div>
			</div>
		</div>
		<div class="zzc2">
			<div class="zzc_income_way">
				<p>修改支出方式</p>
				<div class="zzc_income_way_content">
					<p>
						<input type="hidden" name="ids" value="">
						<input type="text" name="name" id="12" >
					</p>
					<div class="zzc_income_way_content_button" >
						<button onclick="updated('2')">保存</button>
						<button onclick="jQuery('.zzc2').hide()">取消</button>

					</div>
				</div>
			</div>
		</div>
		<div class="zzc3">
			<div class="zzc_income_way">
				<p>修改收入方式</p>
				<div class="zzc_income_way_content">
					<p>
					    <input type="hidden" name="id" value="">
						<input type="text" name="name" id="13">
					</p>
					<div class="zzc_income_way_content_button" >
						<button onclick="updated('3')">保存</button>
						<button onclick="jQuery('.zzc3').hide()">取消</button>

					</div>
				</div>
			</div>
		</div>
</body>


</html>