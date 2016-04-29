<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xss26.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right'>
				<div class="right_head clearfix">
					<ul class="right_ul">
						<li step="1" class="active">
							<div>
								<img src="<%=basePath%>/images/tab_1.png">
							</div> <span>新增项目</span>
						</li>
						<li step="2">
							<div>
								<img src="<%=basePath%>/images/tab_2.png">
							</div> <span>设置价格</span>
						</li>
						<li step="3">
							<div>
								<img src="<%=basePath%>/images/tab_4.png">
							</div> <span>添加职位</span>
						</li>
						<li step="4">
							<div>
								<img src="<%=basePath%>/images/tab_3.png">
							</div> <span>会员折扣</span>
						</li>
					</ul>
					<div class="right_button">
						<button class="save">保 存</button>
						<button class="cancle">取 消</button>
					</div>
				</div>
				<div class="tab_content">
					<!--新增项目-->
					<div class="tab_content_div clearfix">
						<div class="tab_content_div_left">
							<div>
								项目所属部门： 
								<select name="deptId" style="box-shadow: 0 0 3px #ccc;" onchange="choseCategory(this.value)">
									<c:forEach var="deptProject" items="${deptProjectList }">
										<option value="${deptProject.deptId }">${deptProject.deptName }</option>
									</c:forEach>
								</select>

							</div>
							<div>
								设置项目类别： <select name="categoryId" style="box-shadow: 0 0 3px #ccc;">
									<c:forEach var="projectCategoryList" items="${deptProjectList[0].projectCategoryList }">
										<option value="${projectCategoryList.categoryId }">${projectCategoryList.categoryName }</option>
									</c:forEach>
								</select>
							</div>
							<div>
								设置项目规格： <select name="projectType" style="box-shadow: 0 0 3px #ccc;">
									<option value="1">大项</option>
									<option value="2">小项</option>
								</select>
							</div>
							<div>
								项目名称：<input name="projectName" type="text" style="box-shadow: 0 0 3px #ccc;">

							</div>
							<div>
								项目编号：<input name="projectCodeSuffix" type="text" style="box-shadow: 0 0 3px #ccc;">

							</div>
							<div style="position: relative; top: -30px">
								项目描述：<input name="projectDesc" type="textarea" class="textarea" style="position: relative; top: 40px !important; left: 60px !important; box-shadow: 0 0 3px #ccc;">
							</div>
						</div>

						<div class="tab_content_div_right" style="margin-left:160px">
								添加图片：
				              <ul class="add_pic clearfix">
							    <li><img projectImage="<%=qiniu%>system/profile/set_img.png" name="projectImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="<%=qiniu%>system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="<%=qiniu%>system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="<%=qiniu%>system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="<%=qiniu%>system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							    <li><img affiliatedImage="<%=qiniu%>system/profile/set_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/set_img.png" style="width: 89px;height: 89px"></li>
							  </ul>
						</div>

					</div>

					<!--价格-->
					<div class="tab_content_div clearfix">

						<div class="tab_content_div_left">
							<div>
								<span class="shopp"> 门店价格：</span><input name="projectPrice" type="text" style="box-shadow: 0 0 3px #ccc;">

							</div>
							<div>
								<span class="shopp"> 成本金额：</span><input name="costPrice" type="text" style="box-shadow: 0 0 3px #ccc;">

							</div>
							<div>
								<span class="shopp"> 预约优惠金额：</span><input name="appointmentPrice" type="text" style="position: relative; left: 34px; box-shadow: 0 0 3px #ccc;">

							</div>
							<div>
								<span class="shopp"> 最大抵扣礼金：</span><input name="highestDiscount" type="text" style="position: relative; left: 34px; box-shadow: 0 0 3px #ccc;">
							</div>
						</div>


						<div class="tab_content_div_right">
							<div class="choose">
								<span class="width_"> 是否抵扣礼金：</span> <input type="radio" name="isGiftCash" class="yes" value="1" style="margin-left: 20px">是 <input type="radio" value="0" name="isGiftCash" class="yes"
									style="margin-left: 20px">否
							</div>
							<div class="choose">
								<span class="width_">接受预约：</span> <input type="radio" name="isAppointment" class="yes" value="1" style="margin-left: 20px">是 <input type="radio" name="isAppointment" value="0"
									class="yes" style="margin-left: 20px">否
							</div>
						</div>
					</div>


					<!--职位-->
					<div class="tab_content_div clearfix">
						<div class="table_step">
							<table class="table_s">
								<tr>
									<td style="border-right: 1px solid #dbdce2 !important; box-shadow: 0 0 10px #ccc;" class="color_">步骤顺序</td>
									<td class="color_">轮牌名称</td>
									<td class="color_">步骤名称</td>
									<td class="color_">业绩计算方式</td>
									<td class="color_">员工业绩</td>
									<td class="color_">是否预约</td>
									<td class="color_">职位名称</td>
									<td class="color_">提成方式</td>
									<td class="color_">指定提成</td>
									<td class="color_">非指定提成</td>
									<td class="color_">预约奖励</td>
								</tr>
							</table>

						</div>

						<div class="step_1" style="margin-left: 20px;">
							<table style="border: 1px solid #dbdce2; box-shadow: 0 0 10px #ccc;width: 1028px">
								<tr>
									<td style="border-right: 1px solid #dbdce2 !important; color: #afb0b6; border-bottom: 1px solid #dbdce2 !important;">步骤顺序</td>
									<td style="color: #afb0b6;">轮牌名称</td>
									<td style="color: #afb0b6;">步骤名称</td>
									<td style="color: #afb0b6;">业绩计算方式</td>
									<td style="color: #afb0b6;">员工业绩</td>
									<td style="color: #afb0b6;">是否可预约</td>
								</tr>
								<tr>
									<td class="first" style="border-right: 1px solid #dbdce2 !important; color: #afb0b6">第一步</td>
									<td td style="color: #afb0b6"><select class="cut_step" onchange="changeMahjongStep(this.value);" name="shiftMahjongId">
											<c:if test="${!empty deptMahjongList }">
												<c:forEach var="shiftMahjongDto" items="${deptMahjongList[0].mahjongLevelList }" varStatus="status">
													<option value="${shiftMahjongDto.shiftMahjongId }">${shiftMahjongDto.shiftMahjongName }</option>
												</c:forEach>
											</c:if>
									</select></td>
									<td td style="color: #afb0b6"><input type="text" class="cut_step_1" name="projectStepName"></td>
									<td td style="color: #afb0b6"><select class="cut_step_2" name="stepPerformanceType">
											<option value="2">固定</option>
											<option value="1">比例</option>

									</select></td>
									<td td style="color: #afb0b6"><input type="text" class="cut_step_3" name="stepPerformance"></td>
									<td td style="color: #afb0b6">
										<div class="cut_step_4">
											<input type="radio" onclick="chkRadio(this)" value="0" name="isDisable" style="width:18px">
										</div>

									</td>

								</tr>
							</table>

							<table class="step_2">
								<tbody>
									<tr>
										<td style="color: #afb0b6; border-bottom: 1px solid #dbdce2 !important; border-right: 1px solid #dbdce2 !important;">职位名称</td>
										<td style="color: #afb0b6">提成方式</td>
										<td style="color: #afb0b6">指定提成</td>
										<td style="color: #afb0b6">非指定提成</td>
										<td style="color: #afb0b6">预约奖励</td>
									</tr>
									
									<c:forEach var="employeeLevel1" items="${deptMahjongList[0].mahjongLevelList[0].employeeLevelList }" varStatus="status1">
					                    <tr class="empLevel">
		                                    <td style="color: #afb0b6; border-right: 1px solid #dbdce2 !important;">
		                                        <select name="levelId">
		                                              <c:if test="${!empty deptMahjongList && !empty deptMahjongList[0].mahjongLevelList[0].employeeLevelList }">
			                                            <c:forEach var="employeeLevel2" items="${deptMahjongList[0].mahjongLevelList[0].employeeLevelList }" varStatus="status2">
			                                            <c:if test="${employeeLevel1.levelId == employeeLevel2.levelId }">
			                                            	<option selected="selected" value="${employeeLevel2.levelId }">${employeeLevel2.levelName }</option>
			                                            </c:if>
			                                            <c:if test="${employeeLevel1.levelId != employeeLevel2.levelId }">
			                                            	<option value="${employeeLevel2.levelId }">${employeeLevel2.levelName }</option>
			                                            </c:if>
			                                            </c:forEach>
			                                          </c:if>
		                                        </select>
		                                    </td>
		                                   <td><select class="cut_step_7" name="assignType">
											<option value="2">固定</option>
											<option value="1">比例</option>
											</select>
										</td>
										<td><input type="text" name="assignCash" class="cut_step_11"></td>
										<td><input type="text" name="assignCard" class="cut_step_11"></td>
										<td><input type="text" name="appointmentReward" class="cut_step_11"></td>
		                                </tr>
		                            </c:forEach>
								</tbody>
							</table>
							<button class="button_1" onclick="uploadMessageLevel()">保存</button>
						</div>
						<div class="write_4">
							<span class="add_step" style="position: relative; left: -10px">+</span>添加轮牌
						</div>
					</div>

					<!--会员种类-->
					<div class="tab_content_div clearfix">
						<table class="table_" style="position: relative; left: 160px">
							<tr>
								<td>会员卡种类</td>
								<td>会员门店价</td>
								<td>门店价格</td>
								<td>会员成本金额</td>
							<tr>
						</table>
						<div class="tab_content_div_left cash">
							<div>
								<span class="price_">会员种类：</span> 
								<select name="discountLevel" class="select_" style="box-shadow: 0 0 2px #ccc; left: 26px">
								<c:forEach items="${memberLevels }" var="memberLevel">
									<option value="${memberLevel.levelId }">${memberLevel.levelName }</option>
								</c:forEach>
								</select>
							</div>
							<div>
								<span class="price_"> 会员门店价格：</span><input name="discountAmount" type="text" class="vip" style="box-shadow: 0 0 2px #ccc; left: 28px">
							</div>
							<div>
								<span class="price_">会员成本金额：</span><input name="memberCostPrice" type="text" class="vipcoin" style="box-shadow: 0 0 2px #ccc; left: 28px">

							</div>


							<button class="save_">添加</button>
						</div>
						<div class="write_3" style="position: relative; left: 60px;width: 120px;">
							<img src="<%=basePath %>images/add.png"><em style="position:relative;left:4px">编辑</em>
						</div>
						<div class="tab_content_div_right"></div>

					</div>
				</div>
			</div>
		</div>
			
		</div>
	</div>
	
	<div class="zzc">
	      <div class="photo_cut">
		   <div id="clipArea"></div>
           <input type="file" id="file" style="position:absolute;width: 100px;height: 40px;left: 212px;bottom:8px;opacity:0;cursor:pointer;filter:alpha(opacity=0);">
		   <button id="clipBtn" style="position:absolute;width: 100px;height: 40px;left: 346px;bottom:8px;opacity:0;cursor:pointer;filter:alpha(opacity=0);">截取</button>
		   <div class="button_panel"> 
		    <button class="selectpic">选择图片</button>
		    <button class="sureinput">确定上传</button>
			<button class="cancelinput">取消</button>
		  </div>
         </div>
   </div>

</body>

<script>
	var cssWidth = 200;
	var cssHeight = 200;
	var qiniu = '<%=qiniu %>';
	var imgObject;
	jQuery(function(){
	     jQuery('.add_pic li').click(function(){
	    	imgObject = jQuery(this);
		    jQuery('.zzc').show()
		 })
		 jQuery('.cancelinput').click(function(){
		    jQuery('.zzc').hide();
		    jQuery('.photo-clip-rotateLayer').html('');
		 })
	})
	function zccCallback(dataBase64){
		imgObject.children("img").attr("src", dataBase64);
		var key = "jobwisdom/project/" + new Date().getTime();
		if ((typeof(imgObject.children("img").attr("projectImage")))!="undefined"){
			var url = qiniu+key;
			imgObject.children("img").attr("projectImage", url);
		}else {
			var url = qiniu+key;
			imgObject.children("img").attr("affiliatedImage", url);
		}
	    var data = {"stringBase64":dataBase64,"key":key};
	    jQuery('.cancelinput').click();
	    jQuery.ajax({
			type: "POST",
			url: baseUrl+"qiniu/base64",
		       data: JSON.stringify(data),
		       contentType: "application/json",
		       dataType: "json",
		       async:true,  
		       beforeSend:function(){
		       	console.log("beforeSend upload image");
		       },
		       error:function (){
		       	console.log("upload image error");
		       },
		       complete :function (){
		       	console.log("complete upload image");
		       },
		       success: function(data) {
			       	var imageUrl = data.msg.imageUrl;
			       	var key = data.msg.key;
			       	console.log(imageUrl);
		       }
     	});
	}
</script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>
<script>
	var deptProjectList = ${js_deptProjectList};
	var deptMahjongList = ${mahjongList};
	var memberLevelList = ${memberLevelList};
	
	var status = 0;
	var stepNum = 1;

	var storeId = '${session_key_store_id}';
	var projectId = '${projectId}';
	var deptId = deptProjectList[0].deptId;
	
	var project = null;
	var projectCommissionList = null;
	var projectDiscountList = null;
	var projectStepList = null;
	
	/**
	 * 更换轮拍,更换职位信息
	 */
	function changeMahjongStep(shiftMahjongId) {
		var html = '<tr>'+
						'<td style="color: #afb0b6; border-bottom: 1px solid #dbdce2 !important; border-right: 1px solid #dbdce2 !important;">职位名称</td>'+
						'<td style="color: #afb0b6">提成方式</td>'+
						'<td style="color: #afb0b6">指定提成</td>'+
						'<td style="color: #afb0b6">非指定提成</td>'+
						'<td style="color: #afb0b6">预约奖励</td>'+
					'</tr>';
		jQuery(".step_2").children("tbody").empty();
		jQuery(".step_2").children("tbody").append(jQuery(html));
		var tempList;
		for (var i = 0; i < deptMahjongList.length; i++) {
			if (deptMahjongList[i].deptId == deptId){
				for (var j = 0; j < deptMahjongList[i].mahjongLevelList.length; j++) {
					if(deptMahjongList[i].mahjongLevelList[j].shiftMahjongId == shiftMahjongId){
						tempList = deptMahjongList[i].mahjongLevelList[j].employeeLevelList;
					}
				}
			}
		}
		for (var j = 0; j < tempList.length; j++) {
			var str = "<tr class='empLevel'>"
				+ "<td>"
				+ "<select name='levelId'>";
			for (var i = 0; i < tempList.length; i++) {
				var level = tempList[i];
				str += "<option value='" + level.levelId + "'>" + level.levelName + "</option>";
			}
			str += '</select>'+
				'</td>'+
		            '<td><select class="cut_step_7" name="assignType">'+
						'<option value="2">固定</option>'+
						'<option value="1">比例</option>'+
						'</select>'+
					'</td>'+
					'<td><input type="text" name="assignCash" class="cut_step_11"></td>'+
					'<td><input type="text" name="assignCard" class="cut_step_11"></td>'+
					'<td><input type="text" name="appointmentReward" class="cut_step_11"></td>'+
		         '</tr>';
		         jQuery(".step_2").append(jQuery(str));
		}
		for (var j = 0; j < tempList.length; j++) {
			jQuery(".step_2").find("select[name='levelId']").eq(j).find("option").eq(j).attr('selected','selected');
			jQuery(".step_2").find("select[name='levelId']").eq(j).val(tempList[j].levelId);
		}
	}
	
	/**
	* 更换部门,更换轮拍和职位信息
	*/
	function checkUpdateLevel(deptIds){
		deptId = deptIds;
		jQuery("select[name='shiftMahjongId']").empty();
		for (var i = 0; i < deptMahjongList.length; i++) {
			if (deptMahjongList[i].deptId == deptId) {
				for (var j = 0; j < deptMahjongList[i].mahjongLevelList.length; j++) {
					var shiftMahjongId = deptMahjongList[i].mahjongLevelList[j].shiftMahjongId;
					var shiftMahjongName = deptMahjongList[i].mahjongLevelList[j].shiftMahjongName;
					var html = '<option value="'+shiftMahjongId+'">'+shiftMahjongName+'</option>';
					jQuery("select[name='shiftMahjongId']").append(jQuery(html));
				}
				if (deptMahjongList[i].mahjongLevelList.length == 0)changeMahjongStep(0);
				changeMahjongStep(deptMahjongList[i].mahjongLevelList[0].shiftMahjongId);
			}
		}
	}
	/**
	 * 更换部门切换类别
	 */
	function choseCategory(deptIds) {
		deptId = deptIds;
		for (var int = 0; int < deptProjectList.length; int++) {
			if (deptProjectList[int].deptId == deptId) {
				jQuery("select[name='categoryId']").empty()
				for (var j = 0; j < deptProjectList[int].projectCategoryList.length; j++) {
					var categoryId = deptProjectList[int].projectCategoryList[j].categoryId
					var categoryName = deptProjectList[int].projectCategoryList[j].categoryName
					var html = '<option value="'+categoryId+'">' + categoryName
							+ '</option>';
					jQuery("select[name='categoryId']").append(jQuery(html));
				}
			}
		}
		checkUpdateLevel(deptId);
	}
	
	var projectStep = 0;
	if (projectId != '') {
		project = eval('('+'${projectInfo}'+')');
		projectCommissionList = eval('('+'${projectCommissionList}'+')');
		projectDiscountList = eval('('+'${projectDiscountList}'+')');
		projectStepList = eval('('+'${projectStepList}'+')');
		projectStep = project.projectStep;
		deptId = project.deptId;
		status = 1;
		
		/**锁定项目基本信息*/
		var deptName = jQuery("select[name='deptId']").find("option[value='"+deptId+"']").text();
		jQuery("select[name='deptId']").empty();
		jQuery("select[name='deptId']").append(jQuery('<option value="'+deptId+'">'+deptName+'</option>'));
		jQuery("select[name='deptId']").val(deptId);
		
		var categoryId = project.categoryId;
		choseCategory(deptId);
		jQuery("select[name='categoryId']").val(categoryId);
		
		var projectImage = project.projectImage;
		jQuery("img[name='projectImage']").attr("projectImage", projectImage);
		jQuery("img[name='projectImage']").attr("src", projectImage);
		var affiliatedImage = project.affiliatedImage;
		for (var i = 0; i < affiliatedImage.split(",").length; i++) {
			jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage", affiliatedImage.split(",")[i]);
			jQuery("img[name='affiliatedImage']").eq(i).attr("src", affiliatedImage.split(",")[i]);
		}
		
		jQuery("select[name='projectType']").val(project.projectType);
		jQuery("input[name='projectName']").val(project.projectName);
		jQuery("input[name='projectDesc']").val(project.projectDesc);
		jQuery("input[name='projectCodeSuffix']").val(project.projectCodeSuffix);
		
		/**锁定项目价格*/
		if(projectStep>=2){
			
			jQuery("input[name='projectPrice']").val(project.projectPrice);
			jQuery("input[name='costPrice']").val(project.costPrice);
			jQuery("input[name='appointmentPrice']").val(project.appointmentPrice);
			jQuery("input[name='highestDiscount']").val(project.highestDiscount);
			
			jQuery("input[name='isGiftCash'][value='"+project.isGiftCash+"']").click();
			jQuery("input[name='isAppointment'][value='"+project.isAppointment+"']").click();
		}
		
		/**锁定轮牌职位信息*/
		if(projectStep>=3){
			for (var i = 0; i < projectStepList.length; i++) {
				var shiftMahjongId = projectStepList[i].shiftMahjongId;
				var shiftMahjongName = null;
				for (var m = 0; m < deptMahjongList.length; m++) {
					if (deptMahjongList[m].deptId == deptId){
						for (var r = 0; r < deptMahjongList[m].mahjongLevelList.length; r++) {
							if (deptMahjongList[m].mahjongLevelList[r].shiftMahjongId == shiftMahjongId){
								shiftMahjongName = deptMahjongList[m].mahjongLevelList[r].shiftMahjongName;
							}
						}
					}
				}
				
				var step = projectStepList[i].projectStepOrder;
				var rowspan = 0;
				var isSame = [];
				
				var disableList = ['比例','固定'];
				var assignTypeList = ['','比例','固定'];
				var projectStepName = projectStepList[i].projectStepName;
				var stepPerformanceType = projectStepList[i].stepPerformanceType;
				var stepPerformanceTypeName = assignTypeList[stepPerformanceType];
				var stepPerformance = projectStepList[i].stepPerformance;
				var isDisable = projectStepList[i].isDisable;
				for (var j = 0; j < projectCommissionList.length; j++) {
					if(shiftMahjongId == projectCommissionList[j].shiftMahjongId){
						rowspan += 1;
						isSame.push(j);
					}
				}
				
				for (var j = 0; j < isSame.length; j++) {
					var levelId = projectCommissionList[isSame[j]].levelId;
					var levelName = "sd";
					var assignType = projectCommissionList[isSame[j]].assignCashType;
					var assignCash = projectCommissionList[isSame[j]].assignCash;
					var assignCard = projectCommissionList[isSame[j]].assignCard;
					var appointmentReward = projectCommissionList[isSame[j]].appointmentReward;
					if (j == 0){
						var tr = jQuery('<tr shiftMahjongId="'+shiftMahjongId+'" step='+step+'>'+
								'<td rowspan="'+rowspan+'" style="border-right: 1px solid #dbdce2!important;box-shadow: 0 0 10px #ccc;">'+
								'<span style="position:relative;top:50%">'+step+'</span>'+
								'</td><td>'+shiftMahjongName+'</td>'+
								'<td projectStepName="'+projectStepName+'">'+projectStepName+'</td>'+
								'<td stepPerformanceType="'+stepPerformanceType+'">'+stepPerformanceTypeName+'</td>'+
								'<td stepPerformance="'+stepPerformance+'">'+stepPerformance+'</td>'+
								'<td isDisable="'+isDisable+'">'+disableList[isDisable]+'</td>'+
								'<td levelId="'+levelId+'">'+levelName+'</td>'+
								'<td assignType="'+assignType+'">'+assignTypeList[assignType]+'</td>'+
								'<td assignCash="'+assignCash+'">'+assignCash+'</td>'+
								'<td assignCard="'+assignCard+'">'+assignCard+'</td>'+
								'<td appointmentReward="'+appointmentReward+'">'+appointmentReward+'</td>'+
								'</tr>');
						jQuery('.table_s').append(tr);
					}else {
						var tr = jQuery('<tr shiftMahjongId="'+shiftMahjongId+'" step='+step+'>'+
								'<td>'+shiftMahjongName+'</td>'+
								'<td projectStepName="'+projectStepName+'">'+projectStepName+'</td>'+
								'<td stepPerformanceType="'+stepPerformanceType+'">'+stepPerformanceTypeName+'</td>'+
								'<td stepPerformance="'+stepPerformance+'">'+stepPerformance+'</td>'+
								'<td isDisable="'+isDisable+'">'+disableList[isDisable]+'</td>'+
								'<td levelId="'+levelId+'">'+levelName+'</td>'+
								'<td assignType="'+assignType+'">'+assignTypeList[assignType]+'</td>'+
								'<td assignCash="'+assignCash+'">'+assignCash+'</td>'+
								'<td assignCard="'+assignCard+'">'+assignCard+'</td>'+
								'<td appointmentReward="'+appointmentReward+'">'+appointmentReward+'</td>'+
								'</tr>');
						jQuery('.table_s').append(tr);
					}
				}
			}
		}
		if(projectStep>=4){
			for (var i = 0; i < projectDiscountList.length; i++) {
				var levelId = projectDiscountList[i].levelId;
				var projectPrice = project.projectPrice;
				var discountAmount = projectDiscountList[i].discountAmount;
				var memberCostPrice = projectDiscountList[i].memberCostPrice;
				var levelName = null;
				for (var j = 0; j < memberLevelList.length; j++) {
					if (levelId == memberLevelList[j].levelId){
						levelName = memberLevelList[j].levelName;
					}
				}
				var html = '<tr>'+
							'<td levelid="'+levelId+'">'+levelName+
							'</td><td>'+projectPrice+
							'</td><td discountamount="'+discountAmount+'">'+discountAmount+
							'</td><td membercostprice="'+memberCostPrice+'">'+memberCostPrice+'</td></tr>';
				jQuery(".table_").children("tbody").append(jQuery(html));
			}
		}
	}
	else {
		projectId = null;
	}

	/**
	 * 保存数据,根据步骤去保存数据
	 */
	jQuery(".save").on("click", function() {
		var data = coverDate(stepNum);
		console.log(data);
		jQuery.ajax({
			type : "post",
			data : JSON.stringify(data),
			url : baseUrl + "project/save/by/step/" + stepNum + "/" + status,
			dataType : "json",
			contentType : "application/json",
			success : function(returnData) {
				projectId = returnData.msg.projectId;
				stepNum = returnData.msg.projectStep;
				projectStep = returnData.msg.projectStep;
				if (projectStep == 4){
					jQuery("li[step="+(projectStep)+"]").click();
				}
				else {
					jQuery("li[step="+(projectStep+1)+"]").click();
				}
			}
		});
	})
	/**
	 * 拼装后台数据
	 */
	function coverDate(step) {
		if (step == 1) {
			var data = null;
			var projectName = jQuery("input[name='projectName']").val();
			var deptId = jQuery("select[name='deptId']").val();
			var categoryId = jQuery("select[name='categoryId']").val();
			var projectName = jQuery("input[name='projectName']").val();
			var projectType = jQuery("select[name='projectType']").val();
			var projectDesc = jQuery("input[name='projectDesc']").val();
			var projectCodeSuffix = jQuery("input[name='projectCodeSuffix']").val();
			var projectImage = jQuery("img[name='projectImage']").attr("projectImage");
			var affiliatedImage = "";
			for (var i = 0; i < jQuery("img[name='affiliatedImage']").length; i++) {
				if (i == (jQuery("img[name='affiliatedImage']").length-1)){
					affiliatedImage = affiliatedImage + jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage");
				}else {
					affiliatedImage = affiliatedImage + jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage")+ ",";
				}
			}
			data = {
				"storeId" : storeId,
				"projectId" : projectId,
				"projectName" : projectName,
				"deptId" : deptId,
				"categoryId" : categoryId,
				"projectName" : projectName,
				"projectType" : projectType,
				"projectDesc" : projectDesc,
				"projectCodeSuffix" : projectCodeSuffix,
				"projectImage" : projectImage,
				"affiliatedImage" : affiliatedImage
			};
			deptId = deptId;
			return data;
		} else if (step == 2) {
			var projectPrice = jQuery("input[name='projectPrice']").val();
			var costPrice = jQuery("input[name='costPrice']").val();
			var appointmentPrice = jQuery("input[name='appointmentPrice']").val();
			var highestDiscount = jQuery("input[name='highestDiscount']").val();
			var isGiftCash = jQuery('input:radio[name="isGiftCash"]:checked').val();
			var isAppointment = jQuery('input:radio[name="isAppointment"]:checked').val();
			data = {
				"projectId" : projectId,
				"projectPrice" : projectPrice,
				"costPrice" : costPrice,
				"appointmentPrice" : appointmentPrice,
				"highestDiscount" : highestDiscount,
				"isGiftCash" : isGiftCash,
				"isAppointment" : isAppointment
			};
			return data;
		} else if (step == 3) {
			var data1 = [];
			var data2 = [];
			for (var i = 0; i < jQuery('.table_s').find("td[rowspan]").length; i++) {
				var shiftMahjongId = jQuery('.table_s').find("td[rowspan]").eq(i).parent("tr").attr("shiftmahjongid");
				var projectStepOrder = jQuery('.table_s').find("td[rowspan]").eq(i).parent("tr").attr("step");
				var projectStepName = jQuery('.table_s').find("td[rowspan]").eq(i).parent("tr").children("td[projectstepname]").attr("projectstepname");
				var stepPerformance = jQuery('.table_s').find("td[rowspan]").eq(i).parent("tr").children("td[stepperformance]").attr("stepperformance");
				var stepPerformanceType = jQuery('.table_s').find("td[rowspan]").eq(i).parent("tr").children("td[stepperformancetype]").attr("stepperformancetype");
				var isDisable = jQuery('.table_s').find("td[rowspan]").eq(i).parent("tr").children("td[isdisable]").attr("isdisable");
				var jsonObject = {"projectId":projectId,"shiftMahjongId":shiftMahjongId,"projectStepOrder":projectStepOrder,"projectStepName":projectStepName,"stepPerformance":stepPerformance,"stepPerformanceType":stepPerformanceType,"isDisable":isDisable,"isDeleted":0};
				data1.push(jsonObject);
			}
			for (var i = 1; i < jQuery('.table_s').find("tr").length; i++) {
				var levelId = jQuery('.table_s').find("tr").eq(i).children("td[levelid]").attr("levelid");
				var assignCashType = jQuery('.table_s').find("tr").eq(i).children("td[assigntype]").attr("assigntype");
				var assignCash = jQuery('.table_s').find("tr").eq(i).children("td[assigncash]").attr("assigncash");
				var assignCardType = jQuery('.table_s').find("tr").eq(i).children("td[assigntype]").attr("assigntype");
				var assignCard = jQuery('.table_s').find("tr").eq(i).children("td[assigncard]").attr("assigncard");
				var appointmentRewardType = jQuery('.table_s').find("tr").eq(i).children("td[assigntype]").attr("assigntype");
				var appointmentReward = jQuery('.table_s').find("tr").eq(i).children("td[appointmentreward]").attr("appointmentreward");
				var jsonObject = {"projectId":projectId,"shiftMahjongId":shiftMahjongId,"levelId":levelId,"assignCashType":assignCashType,"assignCash":assignCash,"assignCardType":assignCardType,"assignCard":assignCard,"appointmentRewardType":appointmentRewardType,"appointmentReward":appointmentReward,"isDeleted":0};
				data2.push(jsonObject);
			}
			var data = {"projectId":projectId,"step":data1,"commission":data2};
			return data;

		} else if (step == 4) {
				var data1 = [];
				var table = jQuery(".table_");
				for (var i = 2; i < table.find("tr").length; i++) {
					var levelId = table.find("tr").eq(i).children("td[levelid]").attr("levelid");
					var discountAmount = table.find("tr").eq(i).children("td[discountamount]").attr("discountamount");
					var memberCostPrice = table.find("tr").eq(i).children("td[membercostprice]").attr("membercostprice");
					var data = {"projectId":projectId,"levelId":levelId,"discountAmount":discountAmount,"memberCostPrice":memberCostPrice,"isDeleted":0};
					data1.push(data);
				}
				var data = {"data":data1,"projectId":projectId};
				return data;
		}
	}
	

	/**
	*　为项目table添加步骤和职位信息
	*/
	jQuery('.button_1').click(function(){
		var shiftMahjongId = jQuery("select[name='shiftMahjongId']").val();
		var shiftMahjongName = jQuery("select[name='shiftMahjongId']").find("option:selected").text();
		
	})
	
	/**
	*　table中添加新建的轮拍和职位信息
	*/
	function uploadMessageLevel(){
		var shiftMahjongId = jQuery("select[name='shiftMahjongId']").val();
		var shiftMahjongName = jQuery("select[name='shiftMahjongId']").find("option:selected").text();
		var projectStepName = jQuery("input[name='projectStepName']").val();
		var stepPerformanceType = jQuery("select[name='stepPerformanceType']").val();
		var stepPerformanceTypeName = jQuery("select[name='stepPerformanceType']").find("option:selected").text();
		var stepPerformance = jQuery("input[name='stepPerformance']").val();
		var isDisable = jQuery("input[name='isDisable']").val(); 
		
		var hasStep = jQuery(".table_s").find("td[rowspan]");
		var disableList = ['比例','固定'];
		var assignTypeList = ['','比例','固定'];
		
		for (var i = 0; i < jQuery(".empLevel").length; i++) {
			var levelId = jQuery("select[name='levelId']").eq(i).val();
			var levelName = jQuery("select[name='levelId']").eq(i).find("option:selected").text();
			var assignType = jQuery("select[name='assignType']").eq(i).val();
			var assignTypeName = jQuery("select[name='assignType']").eq(i).find("option:selected").text();
			var assignCash = jQuery("input[name='assignCash']").eq(i).val();
			var assignCard = jQuery("input[name='assignCard']").eq(i).val();
			var appointmentReward = jQuery("input[name='appointmentReward']").eq(i).val();
			if(i==0){
				var tr = jQuery('<tr shiftMahjongId="'+shiftMahjongId+'" step='+(hasStep.length+1)+'>'+
						'<td rowspan="'+jQuery(".empLevel").length+'" style="border-right: 1px solid #dbdce2!important;box-shadow: 0 0 10px #ccc;">'+
						'<span style="position:relative;top:50%">'+(hasStep.length+1)+'</span>'+
						'</td><td>'+shiftMahjongName+'</td>'+
						'<td projectStepName="'+projectStepName+'">'+projectStepName+'</td>'+
						'<td stepPerformanceType="'+stepPerformanceType+'">'+stepPerformanceTypeName+'</td>'+
						'<td stepPerformance="'+stepPerformance+'">'+stepPerformance+'</td>'+
						'<td isDisable="'+isDisable+'">'+disableList[isDisable]+'</td>'+
						'<td levelId="'+levelId+'">'+levelName+'</td>'+
						'<td assignType="'+assignType+'">'+assignTypeList[assignType]+'</td>'+
						'<td assignCash="'+assignCash+'">'+assignCash+'</td>'+
						'<td assignCard="'+assignCard+'">'+assignCard+'</td>'+
						'<td appointmentReward="'+appointmentReward+'">'+appointmentReward+'</td>'+
						'</tr>');
				jQuery('.table_s').append(tr);
			}else {
				var tr = jQuery('<tr shiftMahjongId="'+shiftMahjongId+'" step='+(hasStep.length+1)+'>'+
						'<td>'+shiftMahjongName+'</td>'+
						'<td projectStepName="'+projectStepName+'">'+projectStepName+'</td>'+
						'<td stepPerformanceType="'+stepPerformanceType+'">'+stepPerformanceTypeName+'</td>'+
						'<td stepPerformance="'+stepPerformance+'">'+stepPerformance+'</td>'+
						'<td isDisable="'+isDisable+'">'+disableList[isDisable]+'</td>'+
						'<td levelId="'+levelId+'">'+levelName+'</td>'+
						'<td assignType="'+assignType+'">'+assignTypeList[assignType]+'</td>'+
						'<td assignCash="'+assignCash+'">'+assignCash+'</td>'+
						'<td assignCard="'+assignCard+'">'+assignCard+'</td>'+
						'<td appointmentReward="'+appointmentReward+'">'+appointmentReward+'</td>'+
						'</tr>');
				jQuery('.table_s').append(tr);
			}
		}
		jQuery('.step_1').hide();
		jQuery('.write_4').show();
	}
	
	/**
	*  保存会员卡折扣
	*/
	jQuery('.save_').click(function(){
		var levelId = jQuery("select[name='discountLevel']").val();
		var levelName = jQuery("select[name='discountLevel']").find("option:selected").text();
		var discountAmount = jQuery("input[name='discountAmount']").val();
		var memberCostPrice = jQuery("input[name='memberCostPrice']").val();
	    jQuery('.cash').hide();
	    jQuery('.write_3').show();
		var table=jQuery('.tab_content_div').find('.table_');
		var projectPrice = jQuery("input[name='projectPrice']").val();
		var html = '<tr>'+
					'<td levelId="'+levelId+'">'+levelName+'</td>'+
					'<td>'+projectPrice+'</td>'+
					'<td discountAmount="'+discountAmount+'">'+discountAmount+'</td>'+
					'<td memberCostPrice="'+memberCostPrice+'">'+memberCostPrice+'</td>'+
					'</tr>';
		table.append(jQuery(html));
	});

	jQuery(function(){
		jQuery('.tab_content_div:gt(0)').hide();
		jQuery('.right_head li').click(function(){
			var step = jQuery(this).attr("step");
			if (step > (projectStep+1)){
				dialog("请先完成前面的步骤吧");
				return;
			}
			if (step <= (projectStep)){
				status = 1;
			}else{
				status = 0;
			}
			stepNum = step;
			jQuery(this).addClass('active').siblings().removeClass('active');
			jQuery('.tab_content_div').eq(jQuery(this).index()).show().siblings().hide();
		});
	})


	//会员种类
	jQuery(function(){
		jQuery('.write_3').click(function(){
			jQuery('.cash').show();
			jQuery(this).hide();
		});	
	})

	//职位
	 jQuery(function(){
		 jQuery('.write_4').click(function(){
		     jQuery('.step_1').fadeIn('1000');
			 jQuery(this).hide();
		});
	}) 

	/**
	 * 改变radio的样式
	 * @param id
	 */
	function chkRadio(opt) {
		if (jQuery(this).val() == "1"){
			opt.checked = false;
			jQuery(this).val("0");
		}
		else {
			opt.checked = true;
			jQuery(this).val("1");
		}
	}

</script>
</html>