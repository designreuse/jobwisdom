<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.Date"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath %>css/system_manage.css" type="text/css" />
<script>
	jQuery(function() {
		jQuery('.write_delete').hover(function() {
			jQuery(this).find('.write_delete_mask').show()

		}, function() {
			jQuery(this).find('.write_delete_mask').hide()

		})
	})

	//切换
	jQuery(function() {
		jQuery('.system_manage_content .income_way:gt(0)').hide();
		jQuery('.add_store_back li').click(
				function() {
					jQuery(this).addClass('active').siblings().removeClass(
							'active');
					jQuery('.system_manage_content .income_way').eq(
							jQuery(this).index()).show().siblings().hide();
				})
	})
</script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
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
											<input type="text">
											<button onclick="showModal(1)">新建</button>
										</p>
										<div class="income_out_way_write clearfix">
											<div class="write_delete">
												<div class="write_delete_mask" style="display: none;">
													<span>编辑</span><span>删除</span>
												</div>
												十大大大声
											</div>
											<div class="write_delete">
												<div class="write_delete_mask" style="display: none;">
													<span>编辑</span><span>删除</span>
												</div>
												十大大大声
											</div>
										</div>
									</div>
								</div>

								<div class="income_out_way">
									<p>支出方式</p>
									<div class="income_out_way_content">
										<p>
											<input type="text">
											<button onclick="showModal(2)">新建</button>
										</p>
										<div class="income_out_way_write clearfix">
											<div class="write_delete">
												<div class="write_delete_mask" style="display: none;">
													<span>编辑</span><span>删除</span>
												</div>
												十大大大声
											</div>
											<div class="write_delete">
												<div class="write_delete_mask" style="display: none;">
													<span>编辑</span><span>删除</span>
												</div>
												十大大大声
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
	</div>
	
	<div class="zzc">
		   <div class="zzc_add_income">
		   <p>新增收支类别</p>
		   <div class="zzc_add_income_content">
				<div class="zzc_add_income_content_first clearfix">
				    <ul class="zzc_add_income_content_left">
					  <li><span>部门</span><input type="text"></li>
					</ul>
			    </div>
			 <div class="income_button">
			   <button>保存</button>
			   <button>取消</button>
			 </div>
		    </div>
		 </div>
	</div>

</body>

<script>
var type = 1;
function showModal(types){
	type = types;
}
function saveType(){
	
}
</script>
</html>