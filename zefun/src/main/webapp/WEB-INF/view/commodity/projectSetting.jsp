<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xss26.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/demo3.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/roll.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/item_step.css"
	type="text/css" />
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>UEditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8"
	src="<%=basePath%>UEditor/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
.bordererror {
	border: 1px solid red !important
}
.add_store_sale span{width:330px}
.add_store_sale input{ width: 118px;
    height: 22px;
    border: 1px solid black;
    border-radius: 10px;
}
.addImage {
	position: relative;
	left: -604px;
	top: 54px;
	z-index: 1000;
	width: 20px;
	height: 20px;
	text-align: center;
	line-height: 20px;
	display: inline-block;
	border: 1px solid #fafafa;
}

.addImage:hover {
	background-color: #fff5d4;
	border: 1px solid #dcac6c;
}
.tab_content{padding-top:0!important}
</style>
<script src="http://open.web.meitu.com/sources/xiuxiu.js"
	type="text/javascript"></script>
<script type="text/javascript">
    
    function editPage (imgUrl) {
    	xiuxiu.setLaunchVars("cropPresets", "375*200");
    	xiuxiu.embedSWF("altContent2", 5, 700, 500);
    	xiuxiu.onInit = function (id)
    	{
            xiuxiu.setUploadType(3);
            if (imgUrl != null) {
            	xiuxiu.loadPhoto(qiniuUrl + imgUrl, false);
            }
    	}
    	xiuxiu.onSaveBase64Image = function (data, fileName, fileType, id)
    	{
            zccCallback(data);
            xiuxiu.onClose();
    	}
    	
    	xiuxiu.onDebug = function (data)
    	{
            dialog("网咯繁忙，请重试！");
    	}
    	xiuxiu.onClose = function (id)
    	{
            jQuery(".mask").hide();
    	}
    }
</script>
<script>
     //切换
      jQuery(function(){
	     jQuery('.add_store_include .add_store_content:gt(0)').hide();
	     jQuery('.add_store_back li ').click(function(){
		   jQuery(this).addClass('active').siblings().removeClass('active');
		   jQuery('.add_store_include .add_store_content').eq(jQuery(this).index()).show().siblings().hide()
		 
        });
	  });	

	  
  
   //接受预约是否
   
    jQuery(function(){
     jQuery('.shop_price_2 input[type="radio"]').click(function(){
	   if(jQuery(this).hasClass('deny_')){
	     jQuery(this).parents('.shop_price_2').find('input[type="text"]').css('background','#eff0f2').attr('disabled','true');
	   }
	   else{
	     jQuery(this).parents('.shop_price_2').find('input[type="text"]').css('background','white').removeAttr('disabled');
	   }
      });
   })
   
   jQuery(function(){
     jQuery('.add_step_content_left input[type="radio"]').click(function(){
	   if(jQuery(this).hasClass('deny')){
	     jQuery(this).parents('span').find('input[type="text"]').css('background','#eff0f2').attr('disabled','true');
	   }
	   else{
	     jQuery(this).parents('span').find('input[type="text"]').css('background','white').removeAttr('disabled');
	   }
      });
   })
   </script>
<script>
	jQuery(function(){
	  jQuery('.achievement_content input[type="checkbox"]').click(function(){
	   if(jQuery(this).is(':checked')){
            jQuery(this).parent().addClass('active')
	   }
	   else{
	   jQuery(this).parent().removeClass('active')
	    }
	  })
	})
	 
	 
	 //切换
	 jQuery(function(){
	   jQuery('.item_step_content:gt(0)').hide();
	   jQuery('.tab_content>ul>li').click(function(){
	     jQuery(this).addClass('active1').siblings().removeClass('active1');
		 jQuery('.item_step_content').eq(jQuery(this).index()).show().siblings().hide();
	   })
	 
	 })
</script>
<style>
.item_step_table i{position:absolute;right:10px;display:inline-block;}
.item_step_table table td span{position:relative}

</style>

<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper"
		style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel"
				style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right clearfix">
					<div class="add_store_div clearfix">
						<ul class="clearfix add_store_back">
							<li class="active"><span>新增项目</span></li>
							<li class=""><span style="position: relative; top: 20px">价格<i
									style="transform: rotate(-45deg);">\</i>会员价格
							</span></li>
							<li class=""><span>设置业绩提成</span></li>
						</ul>

						<div class="add_store_include">
							<div class="add_store_content clearfix" style="display: block;">
								<div class="add_store_sale">
									<p>
										<span><em>所属部门</em><select name="deptId"
											onchange="choseCategory(this.value)">
												<c:forEach var="deptProject" items="${deptProjectList }">
													<option value="${deptProject.deptId }">${deptProject.deptName }</option>
												</c:forEach>
										</select></span><span>选择大项<i></i><select name="categoryId"
											style="position: relative; left: 45px">
												<c:forEach var="projectCategoryList"
													items="${deptProjectList[0].projectCategoryList }">
													<option value="${projectCategoryList.categoryId }">${projectCategoryList.categoryName }</option>
												</c:forEach>
										</select></span>
									</p>
									<p>
										<span><em>项目名称</em><input type="text"
											name="projectName" style="width: 145px;" maxlength="8"><i  class = "addcolor">*</i></span><span><em>项目编号</em><input
											type="text" maxlength="5" name="projectCodeSuffix" style="width: 145px;"></span>
									</p>
									
						
									<ul class="clearfix" style=" position: relative;  top: -100px;left: 680px;"> 
										<li><em><img onclick="uploadImage(this)"
												projectImage="system/profile/add_img.png"
												name="projectImage"
												src="<%=qiniu%>system/profile/add_img.png" style="width:150px;height:86px"></em></li>
									</ul>
								</div>

<!-- 								<div class="add_pic_"> -->
<!-- 									<p>添加图片</p> -->
<!-- 									<ul class="clearfix"> -->
<!-- 										<li><em><img onclick="uploadImage(this)" -->
<!-- 												projectImage="system/profile/add_img.png" -->
<!-- 												name="projectImage" -->
<%-- 												src="<%=qiniu%>system/profile/add_img.png"></em><span><img --%>
<%-- 												src="<%=basePath%>images/pay_close.png"></span></li> --%>
<!-- 												<li><em><img onclick="uploadImage(this)"  -->
<!-- 												affiliatedImage="system/profile/add_img.png" -->
<!-- 												name="affiliatedImage" -->
<%-- 												src="<%=qiniu%>system/profile/add_img.png"></em><span><img --%>
<%-- 												src="<%=basePath%>images/pay_close.png"></span></li> --%>
<!-- 									</ul> -->
<!-- 								</div> -->

								<div class="item_saying">
									<p>项目描述</p>
									<div class="textarea1">
										<!-- <div><button id="editImage" style="width:130px;height:26px;line-height:26px;text-align:center;border:none;background:#617195;color:white;border-radius:10px;margin-top:10px;margin-left:10px">插入图片</button></div> -->
										<P></P>
										<div class="clearfix">
											<span id="editImage" class="addImage" title="插入图片"> <img
												src="<%=basePath%>images/insert_img.png"
												style="position: relative; left: 1px; top: 1px">
											</span>
											<script id="editor1" type="text/plain"
												style="width: 550px; height: 322px; float: left"></script>
											<div
												style="float: left; width: 320px; height: 420px; margin-top: 25px"
												class="textarea_text">
												<p>在此编辑的内容，将会在移动端－在线预约－项目详情中展示。</p>
												<p></p>
												<p>插入图片后，请保持图片的原样。切勿拖拽图片大小。自动生成的图片可自动适配所有手机显示。</p>
												<p>如若无法预览或全屏编辑或出现其他编辑问题。请更换谷歌浏览器，体验更佳。</p>
											</div>
										</div>
									</div>
								</div>
								<div class="item_button">
									<button onclick="save3()">保存</button>
									<button
										onclick="window.location.href='<%=basePath%>project/view/projects'">取消</button>
								</div>
							</div>
							<div class="add_store_content clearfix" style="display: none;">
								<div class="shop_price">
									<p class="shop_price_1">
										门店价格<span><input name="projectPrice" type="number"><em>元</em></span><i
											style="display: inline-block; width: 105px; margin-left: 72px">成本价格</i><span><input
											name="costPrice" type="text"><em>元</em></span>
									</p>
									<p class="shop_price_2">
										接受礼金<i><input
											onclick="jQuery(this).parent().next().next().show();jQuery(this).parent().next().next().next().show();"
											type="radio" name="isGiftCash" checked="checked" value="1">是</i><i><input
											onclick="jQuery('input[name=\'highestDiscount\']').val('0');jQuery(this).parent().next().hide();jQuery(this).parent().next().next().hide();"
											type="radio" name="isGiftCash" value="0">否</i><i>最大抵扣礼金</i><span><input
											name="highestDiscount" type="number"><em>元</em></span>
									</p>
									<p class="shop_price_2">
										接受预约<i><input
											onclick="jQuery(this).parent().next().next().show();jQuery(this).parent().next().next().next().show();"
											type="radio" name="isAppointment" checked="checked" value="1">是</i><i><input
											onclick="jQuery('input[name=\'appointmentPrice\']').val('0');jQuery(this).parent().next().hide();jQuery(this).parent().next().next().hide();"
											type="radio" name="isAppointment" value="0">否</i><i>预约优惠价格</i><span><input
											name="appointmentPrice" type="number" class="input_3"><em>元</em></span>
									</p>
								</div>
								<div class="vip_price">
									<p>会员价格</p>
									<div class="vip_price_content">
										<p>
											批量选择会员卡
											<select multiple="true" data-placeholder="请选择会员等级" name="huiyuan" class="chzn-select-search" >
				                                <c:forEach items="${memberLevels}" var="memberLevel">
				                                    <option value="${memberLevel.levelId}">${memberLevel.levelName}</option>
				                                </c:forEach>
			                                </select>
			                                                                          输入价格
			                                <input type="text" value="0"/>
											<button onclick="addLevel(this)"
												style="width: 126px; height: 26px; text-align: center; line-height: 26px; border-radius: 10px; color: white; border: none; background: #657392; position: relative; left: 230px">确认</button>
										</p>
										<div class="vip_roll">
											<span class="left_click"><img
												src="<%=basePath%>images/left_click.png"></span>
											<div class="roll_ul">
												<ul class="clearfix" id="projectLevel">
													<!-- 会员等级 -->
												</ul>
											</div>
											<span class="right_click"><img
												src="<%=basePath%>images/right_click.png"></span>
										</div>
									</div>
								</div>

								<div class="demo2_button">
									<button onclick="save3()">保存</button>
									<button
										onclick="window.location.href='<%=basePath%>project/view/projects'">取消</button>
								</div>
							</div>

							<div class="add_store_content clearfix" style="display: none;">
								<div class="content_right clearfix">
									<div class="tab_content">
										<ul class="clearfix">
										  
											<li id="active1" class="active1" value ="${positionInfoDtos[0].positionId }" >${positionInfoDtos[0].positionName }</li>
											<li id="active2" class="" value ="${positionInfoDtos[1].positionId }" >${positionInfoDtos[1].positionName }</li>
											<li id="active3" class="" value ="${positionInfoDtos[2].positionId }" >${positionInfoDtos[2].positionName }</li>
										</ul>
										<div class="div_content">
											<div class="item_step_content" style="display: block;">
												<div class="achievement">
													<p>业绩</p>
													<div class="achievement_content">
														业绩计算方式<select id="active11" onchange="changeprice(this,'price_step1')">
														<option  value="2">固定</option> 
														<option value="1">比例</option>
														</select>
														<span>员工业绩<input placeholder="0" id="active111" type="number"><i id="price_step1">元</i></span>
													</div>
												
													
													<p>提成</p>
													
													<ul  class="achievement_content clearfix" id="check1">
													<c:forEach items="${positionInfoDtos[0].employeeLevel }" var="selectAllByStoreId">
														<li><input   type="checkbox" onchange="insertTable(this,'${selectAllByStoreId.levelName }',${selectAllByStoreId.levelId },1)" value="${selectAllByStoreId.levelId }">${selectAllByStoreId.levelName }</li>
													</c:forEach>
													</ul>
												</div>
												<div class="item_step_table">
													<table>
														<tbody id="1">
															<tr>
														      <td rowspan="2">职位名称</td>
														      <td rowspan="2">提成方式</td>
															  <td colspan="3">指定</td>
															  <td colspan="3">非指定</td>
															  <td rowspan="2">预约奖励</td>
														   </tr>
														   <tr>
															     <td>现金</td>
																 <td>卡金</td>
																 <td>疗程</td>
																  <td>现金</td>
																 <td>卡金</td>
																 <td>疗程</td>
															</tr>
														</tbody>
													</table>
												</div>
											</div>


											<div class="item_step_content" style="display: none;">
												<div class="achievement">
													<p>业绩</p>
													<div class="achievement_content">
														业绩计算方式<select id="active12" onchange="changeprice(this,'price_step2')"><option  value="2">固定</option> <option value="1">比例</option></select><span>员工业绩<input
														id="active112"	placeholder="0" type="number"><i id="price_step2">元</i></span>
													</div>
													<p>提成</p>
													<ul class="achievement_content clearfix" id="check2">
													<c:forEach items="${positionInfoDtos[1].employeeLevel }" var="selectAllByStoreId">
														<li><input   type="checkbox" onchange="insertTable(this,'${selectAllByStoreId.levelName }',${selectAllByStoreId.levelId },2)" value="${selectAllByStoreId.levelId }">${selectAllByStoreId.levelName }</li>
													</c:forEach>
													</ul>
												</div>
												<div class="item_step_table">
													<table>
														<tbody id="2">
														<tr>
													      <td rowspan="2">职位名称</td>
														  <td rowspan="2">提成方式</td>
														  <td colspan="3">指定</td>
														  <td colspan="3">非指定</td>
													   </tr>
													   <tr>
													     <td>现金</td>
														 <td>卡金</td>
														 <td>疗程</td>
														  <td>现金</td>
														 <td>卡金</td>
														 <td>疗程</td>
													   </tr>
														</tbody>
													</table>
												</div>
											</div>

											<div class="item_step_content" style="display: none;">
												<div class="achievement">
													<p>业绩</p>
													<div class="achievement_content">
														业绩计算方式<select  id="active13" onchange="changeprice(this,'price_step3')"><option value="2">固定</option> <option value="1">比例</option></select>
														<span>员工业绩<input
														placeholder="0"	type="number" id="active113" ><i id="price_step3">元</i></span>
													</div>
													<p>提成</p>
													<ul class="achievement_content clearfix" id="check3">
													<c:forEach items="${positionInfoDtos[2].employeeLevel}" var="selectAllByStoreId">
														<li> <input  type="checkbox" onchange="insertTable(this,'${selectAllByStoreId.levelName }',${selectAllByStoreId.levelId },3)" value="${selectAllByStoreId.levelId }">${selectAllByStoreId.levelName }</li>
													</c:forEach>
													</ul>
												</div>
												<div class="item_step_table">
													<table>
														<tbody id="3">
													<tr>
													      <td rowspan="2">职位名称</td>
														  <td rowspan="2">提成方式</td>
														  <td colspan="3">指定</td>
														  <td colspan="3">非指定</td>
													   </tr>
													   <tr>
													     <td>现金</td>
														 <td>卡金</td>
														 <td>疗程</td>
														 <td>现金</td>
														 <td>卡金</td>
														 <td>疗程</td>
													   </tr>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>

									<div class="item_step_button">
									<button onclick="save3()">保存</button>
									<button onclick="window.location.href='<%=basePath%>project/view/projects'">取消</button>
									</div>
								</div>

							</div>

						</div>

					</div>
				</div>
			</div>
		</div>
		<div class="mask" style="display: none;">
			<div id="flashEditorOut" style="position: relative;">
				<span class="mask_close"
					style="position: absolute; right: -5px; top: -5px"><img
					onclick="xiuxiu.onClose();"
					src="<%=basePath%>images/seo_close.png"></span>
				<div id="altContent2">
					<h1>美图秀秀</h1>
				</div>
			</div>
		</div>
</body>
<script type="text/html" id="li">
                               <li>
							      <p>会员等级
								<select name="discountLevel">
								<c:forEach items="${memberLevels }" var="memberLevel">
									<option value="${memberLevel.levelId }">${memberLevel.levelName }</option>
								</c:forEach>
								</select></p>
							      <p>会员价格<input type="text" name="discountAmount"></p>
			                      <div class="li_button">
								    <button>编辑</button><button onclick="jQuery(this).parents('li').remove();">删除</button>
								  </div>
							   </li>
</script>
<script>
	var qiniu = '<%=qiniu%>	';
	var imgObject;
	var type = 1;
	function uploadImage(ul){
		jQuery(".mask").show();
		editPage(null);
		imgObject = jQuery(ul);
		type = 1;
	}
	jQuery(function() {
// 		jQuery('.add_pic_ img').click(function() {
// 			jQuery(".mask").show();
// 			editPage(null);
// 			imgObject = jQuery(this);
// 			type = 1;
// 		})
		jQuery('.cancelinput').click(function() {
			jQuery('.zzc.one').hide();
			jQuery('.photo-clip-rotateLayer').html('');
		})
		jQuery('#editImage').click(function() {
			type = 2;
			jQuery(".mask").show();
			editPage(null);
		})
	})
	function zccCallback(dataBase64) {
		var key = "jobwisdom/project/" + new Date().getTime();
		var data = {
			"stringBase64" : dataBase64,
			"key" : key
		};
		jQuery('.cancelinput').click();
		jQuery.ajax({
				type : "POST",
				url : baseUrl + "qiniu/base64",
				data : JSON.stringify(data),
				contentType : "application/json",
				dataType : "json",
				async : true,
				beforeSend : function() {
					console.log("beforeSend upload image");
				},
				error : function() {
					console.log("upload image error");
				},
				complete : function() {
					console.log("complete upload image");
				},
				success : function(data) {
					var imageUrl = data.msg.imageUrl;
					var key = data.msg.key;
					if (type == 1) {
						if ((typeof (imgObject.attr("projectimage"))) != "undefined") {
							imgObject.attr("projectimage", key);
							imgObject.attr("src", qiniu + key);
						} else {
							imgObject.attr("affiliatedImage", key);
							imgObject.attr("src", qiniu + key);
						}
					} else {
						u1.execCommand(
										'insertHtml',
										'<img style="margin-top: 0px; width: 100%; padding: 0px; border-color: rgb(30, 155, 232); color: inherit; height: 100%;" data-width="100%" border="0" vspace="0" src="'
												+ qiniu + key + '">');
					}
				}
			});
	}

	var toolbars = {
		toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|',
				'bold', 'italic', 'underline', 'fontborder', 'strikethrough',
				'superscript', '|', 'subscript', 'removeformat', 'formatmatch',
				'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor',
				'backcolor', 'insertorderedlist', 'insertunorderedlist',
				'selectall', 'cleardoc', '|', 'rowspacingtop',
				'rowspacingbottom', 'lineheight', '|', 'customstyle',
				'paragraph', 'fontfamily', 'fontsize', '|',
				'directionalityltr', 'directionalityrtl', 'indent', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'justifyjustify', '|', 'touppercase', 'tolowercase', 'preview' ] ],
		maximumWords : 3000,
		elementPathEnabled : false,
		imageScaleEnabled : false,
		wordCount : false,
		autoHeightEnabled : false
	};
	var u1 = UE.getEditor('editor1', toolbars);
</script>

<script>
	var deptProjectList = ${js_deptProjectList};
	var memberLevelList = ${memberLevelList};


	var storeId = '${session_key_store_id}';
	var projectId = '${projectId}';
	var deptId = deptProjectList[0].deptId;

	var project = null;
	var projectCommissionList = null;
	var projectDiscountList = null;
	var projectStepList = null;
	var selectShow = null;
	var selectShowStep = null;
	var positionInfoDtoShow = null;

	/**
	 * 更换部门切换类别
	 */
	function choseCategory(deptIds) {
		deptId = deptIds;
		for (var int = 0; int < deptProjectList.length; int++) {
			if (deptProjectList[int].deptId == deptId) {
				jQuery("select[name='categoryId']").empty();
				for (var j = 0; j < deptProjectList[int].projectCategoryList.length; j++) {
					var categoryId = deptProjectList[int].projectCategoryList[j].categoryId
					var categoryName = deptProjectList[int].projectCategoryList[j].categoryName
					var html = '<option value="'+categoryId+'">' + categoryName
							+ '</option>';
					jQuery("select[name='categoryId']").append(jQuery(html));
				}
			}
		}
		/* checkUpdateLevel(deptId); */
	}

	/**
	 * 保存数据,根据步骤去保存数据
	 */
	// 是否存在异常数据,可允许提交
	var isCommit = false;
	function save3() {
		var data = coverDate();
		if (data.projectInfo.categoryId == null){dialog("请选择项目大项");return;}
// 		if (!isCommit) {
// 			dialog("当前项目为可预约项目,请至少设置一个可预约的步骤!");
// 			return;
// 		}
		jQuery.ajax({
			type : "post",
			url : baseUrl + "project/view/save",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(data) {
				projectId = data.msg.projectId;
				dialog("该项目已更新");
			}
		});
	}

	/**
	 * 拼装后台数据
	 */
	function coverDate() {
		var data = null;
		var projectName = jQuery("input[name='projectName']").val();
		var deptId = jQuery("select[name='deptId']").val();
		var categoryId = jQuery("select[name='categoryId']").val();
		var projectName = jQuery("input[name='projectName']").val();
		var projectDesc = u1.getContent();
		var projectCodeSuffix = jQuery("input[name='projectCodeSuffix']").val();
		var projectImage = jQuery("img[name='projectImage']").attr("projectImage");
		var affiliatedImage = "";
		for (var i = 0; i < jQuery("img[name='affiliatedImage']").length; i++) {
			if (i == (jQuery("img[name='affiliatedImage']").length - 1)) {
				affiliatedImage = affiliatedImage
						+ jQuery("img[name='affiliatedImage']").eq(i).attr(
								"affiliatedImage");
			} else {
				affiliatedImage = affiliatedImage
						+ jQuery("img[name='affiliatedImage']").eq(i).attr(
								"affiliatedImage") + ",";
			}
		}
		var projectPrice = jQuery("input[name='projectPrice']").val();
		if (projectPrice == "") {projectPrice = 0;}
		var costPrice = jQuery("input[name='costPrice']").val();
		if (costPrice == "") {costPrice = 0;}
		var appointmentPrice = jQuery("input[name='appointmentPrice']").val();
		if (appointmentPrice == "") {appointmentPrice = 0;}
		var highestDiscount = jQuery("input[name='highestDiscount']").val();
		if (highestDiscount == "") {highestDiscount = 0;}
		var isGiftCash = jQuery('input:radio[name="isGiftCash"]:checked').val();
		var isAppointment = jQuery('input:radio[name="isAppointment"]:checked').val();
		data = {
			"storeId" : storeId,
			"projectId" : projectId,
			"deptId" : deptId,
			"categoryId" : categoryId,
			"projectName" : projectName,
			"projectDesc" : projectDesc,
			"projectCodeSuffix" : projectCodeSuffix,
			"projectImage" : projectImage,
			"affiliatedImage" : affiliatedImage,
			"projectId" : projectId,
			"projectPrice" : projectPrice,
			"costPrice" : costPrice,
			"appointmentPrice" : appointmentPrice,
			"highestDiscount" : highestDiscount,
			"isGiftCash" : isGiftCash,
			"isAppointment" : isAppointment
		};

		var projectLevel = [];
		jQuery("#projectLevel").find("li").each(
				function() {
					var projectId = projectId;
					var discountLevel = jQuery(this).find(
							"select[name='discountLevel']").val();
					var discountAmount = jQuery(this).find(
							"input[name='discountAmount']").val();
					projectLevel.push({
						"projectId" : projectId,
						"levelId" : discountLevel,
						"discountAmount" : discountAmount
					});
				});
		deptId = deptId;
	
		

	//项目新增 新页面  骆峰	
	var project = [];
	var positionId=jQuery('#active1').val(); 	
	var stepPerformanceType =jQuery('#active11 :checked').val();
	var stepPerformance =jQuery('#active111').val()
	stepPerformance=checked(stepPerformance);
	var projectCommission = [];
	for (var i = 0; i < jQuery(".tr1").length; i++) {
		var levelId =jQuery(".tr1").eq(i).find('td').eq(0).attr('value');
		var assignCashType = jQuery(".tr1").eq(i).find('td').eq(1).find('select option:selected').val();
		
		var commissionCash=jQuery(".tr1").eq(i).find('td').eq(2).find('span input').val();
		var commissionGold =jQuery(".tr1").eq(i).find('td').eq(3).find('span input').val();
		var commissionCourse=jQuery(".tr1").eq(i).find('td').eq(4).find('span input').val();
		
		var commissionNoCash=jQuery(".tr1").eq(i).find('td').eq(5).find('span input').val();
		var commissionNoGold =jQuery(".tr1").eq(i).find('td').eq(6).find('span input').val();
		var commissionNoCourse=jQuery(".tr1").eq(i).find('td').eq(7).find('span input').val();
		
		commissionCash=checked(commissionCash);
		commissionGold=checked(commissionGold);
		commissionCourse=checked(commissionCourse);
		
		commissionNoCash=checked(commissionNoCash);
		commissionNoGold=checked(commissionNoGold);
		commissionNoCourse=checked(commissionNoCourse);
		
	
		var commissionCard=jQuery(".tr1").eq(i).find('td').eq(8).find('span input').val();
		
		projectCommission.push(
				{"projectId":projectId,"levelId":levelId,"isDeleted":0,"positionId":positionId,"assignCashType":assignCashType,
					"commissionCash":commissionCash,"commissionGold":commissionGold,"commissionCourse":commissionCourse,"commissionNoCash":commissionNoCash,
					"commissionNoGold":commissionNoGold,"commissionNoCourse":commissionNoCourse,"commissionCard":commissionCard});
	}
	project.push({"projectId":projectId,"positionId":positionId,"stepPerformanceType":stepPerformanceType,"stepPerformance":stepPerformance});
	
	
	var positionId=jQuery('#active2').val(); 	
	var stepPerformanceType =jQuery('#active12 :checked').val();
	var stepPerformance =jQuery('#active112').val()
	stepPerformance=checked(stepPerformance);
	var projectsion = [];
	
	for (var i = 0; i < jQuery(".tr2").length; i++) {
		var levelId =jQuery(".tr2").eq(i).find('td').eq(0).attr('value');
		var assignCashType = jQuery(".tr2").eq(i).find('td').eq(1).find('select option:selected').val();
	
		var commissionCash=jQuery(".tr2").eq(i).find('td').eq(2).find('span input').val();
		var commissionGold =jQuery(".tr2").eq(i).find('td').eq(3).find('span input').val();
		var commissionCourse=jQuery(".tr2").eq(i).find('td').eq(4).find('span input').val();
		
		var commissionNoCash=jQuery(".tr2").eq(i).find('td').eq(5).find('span input').val();
		var commissionNoGold =jQuery(".tr2").eq(i).find('td').eq(6).find('span input').val();
		var commissionNoCourse=jQuery(".tr2").eq(i).find('td').eq(7).find('span input').val();
		
		commissionCash=checked(commissionCash);
		commissionGold=checked(commissionGold);
		commissionCourse=checked(commissionCourse);
		
		commissionNoCash=checked(commissionNoCash);
		commissionNoGold=checked(commissionNoGold);
		commissionNoCourse=checked(commissionNoCourse);
		
		projectCommission.push({"projectId":projectId,"levelId":levelId,"isDeleted":0,"positionId":positionId,"assignCashType":assignCashType,
			"commissionCash":commissionCash,"commissionGold":commissionGold,"commissionCourse":commissionCourse,"commissionNoCash":commissionNoCash,
			"commissionNoGold":commissionNoGold,"commissionNoCourse":commissionNoCourse,"commissionCard":commissionCard});
	}
	
	project.push({"projectId":projectId,"isDeleted":0,"positionId":positionId,"stepPerformanceType":stepPerformanceType,"stepPerformance":stepPerformance});
	var positionId=jQuery('#active3').val(); 	
	var stepPerformanceType =jQuery('#active13 :checked').val();
	var stepPerformance =jQuery('#active113').val();
	if(positionId < 0){
		dialog("员工业绩不能小于零");
		return ;
	}
	if(stepPerformanceType < 0){
		dialog("员工业绩不能小于零");
		return ;	
    }
	if(stepPerformance < 0){
		dialog("员工业绩不能小于零");
		return ;
	}
	stepPerformance=checked(stepPerformance);
	for (var i = 0; i < jQuery(".tr3").length; i++) {
		var levelId =jQuery(".tr3").eq(i).find('td').eq(0).attr('value');
		var assignCashType = jQuery(".tr3").eq(i).find('td').eq(1).find('select option:selected').val();
		var commissionCash=jQuery(".tr3").eq(i).find('td').eq(2).find('span input').val();
		var commissionGold =jQuery(".tr3").eq(i).find('td').eq(3).find('span input').val();
		var commissionCourse=jQuery(".tr3").eq(i).find('td').eq(4).find('span input').val();
		
		var commissionNoCash=jQuery(".tr3").eq(i).find('td').eq(5).find('span input').val();
		var commissionNoGold =jQuery(".tr3").eq(i).find('td').eq(6).find('span input').val();
		var commissionNoCourse=jQuery(".tr3").eq(i).find('td').eq(7).find('span input').val();
		
		commissionCash=checked(commissionCash);
		commissionGold=checked(commissionGold);
		commissionCourse=checked(commissionCourse);
		
		commissionNoCash=checked(commissionNoCash);
		commissionNoGold=checked(commissionNoGold);
		commissionNoCourse=checked(commissionNoCourse);

		projectCommission.push({"projectId":projectId,"levelId":levelId,"isDeleted":0,"positionId":positionId,"assignCashType":assignCashType,
			"commissionCash":commissionCash,"commissionGold":commissionGold,"commissionCourse":commissionCourse,"commissionNoCash":commissionNoCash,
			"commissionNoGold":commissionNoGold,"commissionNoCourse":commissionNoCourse,"commissionCard":commissionCard});
	}
	project.push({"projectId":projectId,"isDeleted":0,"positionId":positionId,"stepPerformanceType":stepPerformanceType,"stepPerformance":stepPerformance});
		return {
			"projectInfo" : data,
			"projectLevel" : projectLevel,
			"project" : project,
			"projectCommission" : projectCommission
		};
	}

	
	function checked(number){
		if(number ==""){
			number=0
		}
		return number;
	}
	/**
	 *　为项目table添加步骤和职位信息
	 */
	jQuery('.button_1').click(
			function() {
				var shiftMahjongId = jQuery("select[name='shiftMahjongId']")
						.val();
				var shiftMahjongName = jQuery("select[name='shiftMahjongId']")
						.find("option:selected").text();
			})

	u1.ready(function() {
				if (projectId != '') {

					var projectDesc = '${projectDesc}';
					project = eval('(' + '${projectInfo}' + ')');
					projectCommissionList = eval('(' + '${projectCommissionList}' + ')');
					projectDiscountList = eval('(' + '${projectDiscountList}' + ')');
					deptId = project.deptId;
				
					/**锁定项目基本信息*/
					var deptName = jQuery("select[name='deptId']").find(
							"option[value='" + deptId + "']").text();
					jQuery("select[name='deptId']").empty();
					jQuery("select[name='deptId']").append(
							jQuery('<option value="'+deptId+'">' + deptName
									+ '</option>'));
					jQuery("select[name='deptId']").val(deptId);

					var categoryId = project.categoryId;
					choseCategory(deptId);
					jQuery("select[name='categoryId']").val(categoryId);

					var projectImage = project.projectImage;
					jQuery("img[name='projectImage']").attr("projectImage",
							projectImage);
					jQuery("img[name='projectImage']").attr("src",
							qiniu + projectImage);
					var affiliatedImage = project.affiliatedImage;
					for (var i = 0; i < affiliatedImage.split(",").length; i++) {
						jQuery("img[name='affiliatedImage']").eq(i).attr(
								"affiliatedImage",
								affiliatedImage.split(",")[i]);
						jQuery("img[name='affiliatedImage']").eq(i).attr("src",
								qiniu + affiliatedImage.split(",")[i]);
					}
					/**锁定项目价格*/
					jQuery("input[name='projectName']")
							.val(project.projectName);
					u1.setContent(projectDesc);
					//jQuery("textarea[name='projectDesc']").val(project.projectDesc);
					jQuery("input[name='projectCodeSuffix']").val(
							project.projectCodeSuffix);

					jQuery("input[name='projectPrice']").val(
							project.projectPrice);
					jQuery("input[name='costPrice']").val(project.costPrice);
					jQuery("input[name='appointmentPrice']").val(
							project.appointmentPrice);
					jQuery("input[name='highestDiscount']").val(
							project.highestDiscount);

					jQuery(
							"input[name='isGiftCash'][value='"
									+ project.isGiftCash + "']").click();
					jQuery(
							"input[name='isAppointment'][value='"
									+ project.isAppointment + "']").click();


					for (var i = 0; i < projectDiscountList.length; i++) {
						var levelId = projectDiscountList[i].levelId;
						var projectPrice = project.projectPrice;
						var discountAmount = projectDiscountList[i].discountAmount;
						var memberCostPrice = projectDiscountList[i].memberCostPrice;
						var levelName = null;
						for (var j = 0; j < memberLevelList.length; j++) {
							if (levelId == memberLevelList[j].levelId) {
								levelName = memberLevelList[j].levelName;
							}
						}
						jQuery("#projectLevel").append(jQuery("#li").html());
						jQuery("#projectLevel").children("li").eq(i).find(
								"select[name='discountLevel']").val(levelId);
						jQuery("#projectLevel").children("li").eq(i).find(
								"input[name='discountAmount']").val(
								discountAmount);
					}
					/** 提成回显 */
					selectShow = ${selectShow} ;
					selectShowStep = ${selectShowStep};
// 					positionInfoDtoShow = ${positionInfo};
					showViwe();
				} else {
					projectId = null;
				}
			})

	/**
	 *　table中添加新建的轮拍和职位信息
	 */
	function uploadMessageLevel() {
		var shiftMahjongId = jQuery("select[name='shiftMahjongId']").val();
		if (jQuery("tr[shiftmahjongid='" + shiftMahjongId + "']").length > 0) {
			dialog("请不要设置重复牌位");
			return;
		}
		var shiftMahjongName = jQuery("select[name='shiftMahjongId']").find(
				"option:selected").text();
		var projectStepName = jQuery("input[name='projectStepName']").val();
		var stepPerformanceType = jQuery("select[name='stepPerformanceType']")
				.val();
		var stepPerformanceTypeName = jQuery(
				"select[name='stepPerformanceType']").find("option:selected")
				.text();
		var stepPerformance = jQuery("input[name='stepPerformance']").val();
		var isDisable = jQuery("input[name='isDisable']").val();
		//校验数据
		var checkJson = {
			"projectStepName" : projectStepName,
			"stepPerformance" : stepPerformance
		};
		var isOk = true;
		jQuery.each(checkJson, function(name, value) {
			if (!isNotNull(value)) {
				jQuery("input[name='" + name + "']").addClass("bordererror");
				isOk = false;
				return false;
			}
		});
		if (!isOk)
			return;

		var hasStep = jQuery(".table_s").find("td[rowspan][date]");
		var stepNum = hasStep.length + 1;
		if (eltNum != null) {
			stepNum = eltNum;
		}
		var disableList = [ '比例', '固定' ];
		var assignTypeList = [ '', '比例', '固定' ];

		var isHide = true;

		for (var i = 0; i < jQuery(".empLevel").length; i++) {
			var levelId = jQuery("select[name='levelId']").eq(i).val();
			var levelName = jQuery("select[name='levelId']").eq(i).find(
					"option:selected").text();
			var assignType = jQuery("select[name='assignType']").eq(i).val();
			var assignTypeName = jQuery("select[name='assignType']").eq(i)
					.find("option:selected").text();
			var assignCash = jQuery("input[name='assignCash']").eq(i).val();
			var assignCard = jQuery("input[name='assignCard']").eq(i).val();
			var appointmentReward = jQuery("input[name='appointmentReward']")
					.eq(i).val();

			var checkJson = {
				"assignCash" : assignCash,
				"assignCard" : assignCard,
				"appointmentReward" : appointmentReward
			};
			var isOk = true;
			jQuery.each(checkJson, function(name, value) {
				if (!isNotNull(value)) {
					jQuery("input[name='" + name + "']").addClass("border");
					isOk = false;
					isHide = false;
					return false;
				}
			});
			if (!isOk)
				return;

			if (i == 0) {
				var tr = jQuery('<tr shiftMahjongId="'
						+ shiftMahjongId
						+ '" step='
						+ (stepNum)
						+ '>'
						+ '<td rowspan="'
						+ jQuery(".empLevel").length
						+ '" date="1" style="border-right: 1px solid #dbdce2!important;box-shadow: 0 0 10px #ccc;">'
						+ '<span style="position:relative;top:50%">'
						+ (stepNum)
						+ '</span>'
						+ '</td>'
						+ '<td>'
						+ shiftMahjongName
						+ '</td>'
						+ '<td projectStepName="'+projectStepName+'">'
						+ projectStepName
						+ '</td>'
						+ '<td stepPerformanceType="'+stepPerformanceType+'">'
						+ stepPerformanceTypeName
						+ '</td>'
						+ '<td stepPerformance="'+stepPerformance+'">'
						+ stepPerformance
						+ '</td>'
						+ '<td isDisable="'+isDisable+'">'
						+ disableList[isDisable]
						+ '</td>'
						+ '<td levelId="'+levelId+'">'
						+ levelName
						+ '</td>'
						+ '<td assignType="'+assignType+'">'
						+ assignTypeList[assignType]
						+ '</td>'
						+ '<td assignCash="'+assignCash+'">'
						+ assignCash
						+ '</td>'
						+ '<td assignCard="'+assignCard+'">'
						+ assignCard
						+ '</td>'
						+ '<td appointmentReward="'+appointmentReward+'">'
						+ appointmentReward
						+ '</td>'
						+ '<td rowspan="'
						+ jQuery(".empLevel").length
						+ '" style="border-right: 1px solid #dbdce2!important;box-shadow: 0 0 10px #ccc;" onclick="updateProjectLevelStep(this)">'
						+ '<span>编辑</span>' + '</td>' + '</tr>');
				addElmt(tr);
			} else {
				var tr = jQuery('<tr shiftMahjongId="' + shiftMahjongId
						+ '" step=' + (stepNum) + '>' + '<td>'
						+ shiftMahjongName + '</td>'
						+ '<td projectStepName="'+projectStepName+'">'
						+ projectStepName + '</td>'
						+ '<td stepPerformanceType="'+stepPerformanceType+'">'
						+ stepPerformanceTypeName + '</td>'
						+ '<td stepPerformance="'+stepPerformance+'">'
						+ stepPerformance + '</td>'
						+ '<td isDisable="'+isDisable+'">'
						+ disableList[isDisable] + '</td>'
						+ '<td levelId="'+levelId+'">' + levelName + '</td>'
						+ '<td assignType="'+assignType+'">'
						+ assignTypeList[assignType] + '</td>'
						+ '<td assignCash="'+assignCash+'">' + assignCash
						+ '</td>' + '<td assignCard="'+assignCard+'">'
						+ assignCard + '</td>'
						+ '<td appointmentReward="'+appointmentReward+'">'
						+ appointmentReward + '</td>' + '</tr>');
				addElmt(tr);
			}
		}
		if (isHide) {
			jQuery('.step_1').hide();
			jQuery('.write_4').show();
		}
		eltNum = null;
	}
	/** 想表格中添加步骤信息,但是,此处要看是否是编辑*/
	function addElmt(tr) {
		if (eltNum != null) {
			if (jQuery("tr[step='" + (Number(eltNum) + 1) + "']").length == 0) {
				jQuery('.table_s').append(tr);
			} else {
				console.log(jQuery("tr[step='" + (Number(eltNum) + 1) + "']")
						.eq(0).html());
				jQuery("tr[step='" + (Number(eltNum) + 1) + "']").eq(0).before(
						tr);
			}
		} else {
			jQuery('.table_s').append(tr);
		}
	}

	/**
	 *  保存会员卡折扣
	 */
	jQuery('.save_').click(
			function() {
				var levelId = jQuery("select[name='discountLevel']").val();
				var levelName = jQuery("select[name='discountLevel']").find(
						"option:selected").text();
				var discountAmount = jQuery("input[name='discountAmount']")
						.val();
				var memberCostPrice = jQuery("input[name='memberCostPrice']")
						.val();

				var checkJson = {
					"discountAmount" : discountAmount,
					"memberCostPrice" : memberCostPrice
				};
				var isOk = true;
				jQuery.each(checkJson,
						function(name, value) {
							if (!isNotNull(value)) {
								jQuery("input[name='" + name + "']").addClass(
										"border");
								isOk = false;
								return false;
							}
						});
				if (!isOk)
					return;

				jQuery('.cash').hide();
				jQuery('.write_3').show();
				var table = jQuery('.tab_content_div').find('.table_');
				var projectPrice = jQuery("input[name='projectPrice']").val();
				var html = '<tr>' + '<td levelId="'+levelId+'">' + levelName
						+ '</td>' + '<td>' + projectPrice + '</td>'
						+ '<td discountAmount="'+discountAmount+'">'
						+ discountAmount + '</td>'
						+ '<td memberCostPrice="'+memberCostPrice+'">'
						+ memberCostPrice + '</td>' + '</tr>';
				table.append(jQuery(html));
			});

	jQuery(function() {
		jQuery('.tab_content_div:gt(0)').hide();
		jQuery('.right_head li').click(
				function() {
					var step = jQuery(this).attr("step");
					if (step > (projectStep + 1)) {
						dialog("请先完成前面的步骤吧");
						return;
					}
					if (step <= (projectStep)) {
						status = 1;
					} else {
						status = 0;
					}
					stepNum = step;
					jQuery(this).addClass('active').siblings().removeClass(
							'active');
					jQuery('.tab_content_div').eq(jQuery(this).index()).show()
							.siblings().hide();
				});
	})

	function addLevel(button) {
		var ids = jQuery("select[name='huiyuan']").val();
		var levelValue = jQuery(button).prev().val();
		for (var i = 0; i < ids.length; i++) {
			jQuery("#projectLevel").append(jQuery("#li").html());
			jQuery("select[name='discountLevel']").eq(i).val(ids[i]);
			jQuery("input[name='discountAmount']").eq(i).val(levelValue);
		}
		/* jQuery("#projectLevel").append(jQuery("#li").html()); */
	}

	//会员种类
	jQuery(function() {
		jQuery('.write_3').click(function() {
			jQuery('.cash').show();
			jQuery(this).hide();
		});
	})

	//职位
	jQuery(function() {
		jQuery('.write_4').click(
				function() {
					var step = jQuery(".table_s").children("tbody").children(
							"tr").last().attr("step");
					if (typeof (step) != "undefined") {
						jQuery(".first").text("第" + N[Number(step) + 1] + "步");
					}
					jQuery('.step_1').fadeIn('1000');
					jQuery(this).hide();
				});
	})

	/**
	 * 改变radio的样式
	 * @param id
	 */
	function chkRadio(opt) {
		if (jQuery(opt).val() == "1") {
			opt.checked = false;
			jQuery(opt).val("0");
		} else {
			opt.checked = true;
			jQuery(opt).val("1");
		}
	}

	/** 非空校验 */
	function isNotNull(str) {
		if (str != null && str != '' && typeof (str) != "undefined")
			return true;
		return false;
	}
	/** 重新获取焦点的时候,去掉校验的红色框 */
	jQuery(function() {
		jQuery("input").focus(function() {
			jQuery(this).removeClass("border")
		});
	})

	var N = [ "零", "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二" ];

	/**修改后重新放到*/
	var eltNum = null;
	/**修改某个步骤*/
	function updateProjectLevelStep(td) {
		jQuery(".write_4").hide();
		var shiftmahjongid = jQuery(td).parents("tr").attr("shiftmahjongid");
		var step = jQuery(td).parents("tr").attr("step");
		eltNum = step;
		jQuery(".first").text("第" + N[Number(step)] + "步");
		jQuery('.step_1').fadeIn('1000');
		/**显示轮拍*/
		jQuery("select[name='shiftMahjongId']").val(shiftmahjongid);
		changeMahjongStep(shiftmahjongid);
		/**存放要删除的职位*/
		var emt = [];
		jQuery(".empLevel").each(
				function(index) {
					if ((Number(index) + 1) > jQuery("tr[shiftmahjongid='"
							+ shiftmahjongid + "']").length) {
						emt.push(jQuery(this));
					}
				});
		for (var i = 0; i < emt.length; i++) {
			emt[i].remove();
		}
		jQuery("tr[shiftmahjongid='" + shiftmahjongid + "']").each(
				function(index) {
					var projectstepname = jQuery(this).children("td[projectstepname]").attr("projectstepname");
					jQuery("input[name='projectStepName']").val(projectstepname);
					var stepperformancetype = jQuery(this).children( "td[stepperformancetype]").attr( "stepperformancetype");
					jQuery("select[name='stepPerformanceType']").val( stepperformancetype);
					var stepperformance = jQuery(this).children( "td[stepperformance]").attr("stepperformance");
					jQuery("input[name='stepPerformance']") .val(stepperformance);
					var isdisable = jQuery(this).children("td[isdisable]") .attr("isdisable");
					if (isdisable != jQuery("input[name='isDisable']").val()) {
						jQuery("input[name='isDisable']").click();
					}
					var levelid = jQuery(this).children("td[levelid]").attr( "levelid");
					var assigntype = jQuery(this).children("td[assigntype]") .attr("assigntype");
					var assigncash = jQuery(this).children("td[assigncash]") .attr("assigncash");
					var assigncard = jQuery(this).children("td[assigncard]") .attr("assigncard");
					var appointmentreward = jQuery(this).children( "td[appointmentreward]").attr("appointmentreward");
					jQuery("select[name='levelId']").eq(index).val(levelid);
					jQuery("select[name='assignType']").eq(index).val( assigntype);
					jQuery("input[name='assignCash']").eq(index) .val(assigncash);
					jQuery("input[name='assignCard']").eq(index) .val(assigncard);
					jQuery("input[name='appointmentReward']").eq(index).val( appointmentreward);
				});
		jQuery("tr[shiftmahjongid='" + shiftmahjongid + "']").remove();
	}
	
	function changeprice(s,type){
		var price_step = jQuery(s).val();
		if(price_step==1){
			jQuery("#"+type).text("%");
		}else{
			jQuery("#"+type).text("元");
		}
		
	}
	function changeprice1(s,levelId,type){
		var price_step = jQuery(s).val();
		if(price_step==1){
			jQuery('#'+type+' i[id='+levelId+']').text("%");
		}else{
			jQuery("#"+type+" i[id="+levelId+"]").not("i[name='cardyu']").text("元");
		}
	}
	
	function insertTable(s,levelName,levelId,type){
		if(jQuery(s).is(':checked')){
			var html="<tr id ="+levelId+" class='tr"+type+" '><td value ="+levelId+">"+levelName+"</td>"
			+"<td><select onchange='changeprice1(this,"+levelId+","+type+")'><option value='2'>固定</option><option value='1'>比例</option></select></td>"
			+"<td><span><input  placeholder='0' type='text'><i id='"+levelId+"'>元</i></span></td>"
			+"<td><span><input  placeholder='0' type='text'><i id='"+levelId+"'>元</i></span></td>"
			+"<td><span><input  placeholder='0' type='text'><i id='"+levelId+"'>元</i></span></td>"
			+"<td><span><input  placeholder='0' type='text'><i id='"+levelId+"'>元</i></span></td>"
			+"<td><span><input  placeholder='0' type='text'><i id='"+levelId+"'>元</i></span></td>";
			if(type==1){
				html+="<td><span><input  placeholder='0' type='text'><i id='"+levelId+"' >元</i></span></td>";
				html+="<td><span><input  placeholder='0' type='text'><i id='"+levelId+"'  name='cardyu'>元</i></span></td></tr>";
			}else{
				html+="<td><span><input  placeholder='0' type='text'><i id='"+levelId+"'>元</i></span></td></tr>";
			}
			
			jQuery("#"+type).append(jQuery(html));
		}else{
			jQuery('#'+type+' tr[id='+levelId+']').remove();
		}
		

	}
	
	function showViwe(){
		for (var i = 0,n=1; i < selectShowStep.length; i++,n++) {
		        jQuery("#active1"+n).val(selectShowStep[i].stepPerformanceType);
		        jQuery("#active11"+n).attr("value",selectShowStep[i].stepPerformance);
		        if(selectShowStep[i].stepPerformanceType==2){
		    		jQuery("#price_step"+n).text("元");
		        }
		        if(selectShowStep[i].stepPerformanceType==1){
		    		jQuery("#price_step"+n).text("%");
		        }
		}
		for (var j = 0; j < selectShow.length; j++) {
			jQuery("input[value='"+selectShow[j].levelId+"']").click();
			jQuery("tr[id='"+selectShow[j].levelId+"']").children("td").eq(1).find("select").val(selectShow[j].assignCashType);
			jQuery("tr[id='"+selectShow[j].levelId+"']").children("td").eq(2).find("input").val(selectShow[j].commissionCash);
			jQuery("tr[id='"+selectShow[j].levelId+"']").children("td").eq(3).find("input").val(selectShow[j].commissionGold);
			jQuery("tr[id='"+selectShow[j].levelId+"']").children("td").eq(4).find("input").val(selectShow[j].commissionCourse);
			jQuery("tr[id='"+selectShow[j].levelId+"']").children("td").eq(5).find("input").val(selectShow[j].commissionNoCash);
			jQuery("tr[id='"+selectShow[j].levelId+"']").children("td").eq(6).find("input").val(selectShow[j].commissionNoGold);
			jQuery("tr[id='"+selectShow[j].levelId+"']").children("td").eq(7).find("input").val(selectShow[j].commissionNoCourse);
			if(selectShow[j].commissionCard != "" && selectShow[j].commissionCard >0){
				jQuery("tr[id='"+selectShow[j].levelId+"']").children("td").eq(8).find("input").val(selectShow[j].commissionCard);
			}
			if(selectShow[j].assignCashType==2){
				jQuery('i[id='+selectShow[j].levelId+']').text("元");
			}
			if(selectShow[j].assignCashType==1){
				jQuery('i[id='+selectShow[j].levelId+']').text("%");
			}
		}
	}
	
</script>
<script>
   jQuery(function(){
     var now_=0;
  //向右走
  jQuery(document).on('click','.right_click',function(){
	  var count=jQuery('.roll_ul ul li').size();
     if(now_<=count-5){
	    now_+=1;
        jQuery(this).parent('').find('.roll_ul ul').stop(true,true).animate({
	       left:-187*now_
	       }) 
		  }
	  });
  
  	//向左走
 jQuery(document).on('click','.left_click',function(){
	 var count=jQuery('.roll_ul ul li').size();
     if(now_>=1){
	    now_-=1;
         jQuery(this).parent('').find('.roll_ul ul').stop(true,true).animate({
	     left:-187*now_
	     }) 
	  }	
    });
  });

   
</script>
</html>