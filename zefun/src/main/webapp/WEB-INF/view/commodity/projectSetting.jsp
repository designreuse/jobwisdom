<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xss26.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/demo3.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/roll.css" type="text/css" />
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/lang/zh-cn/zh-cn.js"></script>
<style type="text/css">
	.border {border:1px solid red!important}
</style>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript">
    
    function editPage (imgUrl) {
    	xiuxiu.setLaunchVars("cropPresets", "200*200");
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

	  
	   //轮播
 
 	jQuery(function(){
	     var now_=0, count=jQuery('.roll_ul li').size();
		 
	  //向右走
      jQuery('.right_click').click(function(){
         if(now_<=count-5){
		    now_+=1;
	        jQuery(this).parent('').find('.roll_ul ul').stop(true,true).animate({
		       left:-187*now_
		       }) 
			  }
		  });
	  //向左走
	  
	  	//向左走
	 jQuery('.left_click').click(function(){
         if(now_>=1){
		    now_-=1;
	         jQuery(this).parent('').find('.roll_ul ul').stop(true,true).animate({
		     left:-187*now_
		     }) 
		  }	
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
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class="content_right clearfix">
			    <div class="add_store_div clearfix">
			      <ul class="clearfix add_store_back">
				     <li class="active"><span>新增项目</span></li>
			         <li class=""><span style="position:relative;top:20px">价格<i style="transform:rotate(-45deg);">\</i>会员价格</span></li>
					 <li class=""><span>添加步骤</span></li>
				  </ul>
			
			    <div class="add_store_include">
			       <div class="add_store_content clearfix" style="display: block;">
				       <div class="add_store_sale">
					     <p>
						   <span><em>所属部门</em><select name="deptId" onchange="choseCategory(this.value)">
									<c:forEach var="deptProject" items="${deptProjectList }">
										<option value="${deptProject.deptId }">${deptProject.deptName }</option>
									</c:forEach>
								</select></span><span>商品系列：<i></i><select name="categoryId" style="position:relative;left:8px">
									<c:forEach var="projectCategoryList" items="${deptProjectList[0].projectCategoryList }">
										<option value="${projectCategoryList.categoryId }">${projectCategoryList.categoryName }</option>
									</c:forEach>
								</select></span>
						 </p>
					     <p>
						   <span><em>商品名称</em><input type="text" name="projectName" style="width: 145px;"></span><span>商品编号<input type="text" name="projectCodeSuffix" style="width: 145px;"></span>
						 </p>
					   </div>
					   
					   <div class="add_pic_">
					      <p>添加图片</p>
					      <ul class="clearfix">
						     <li><em><img onclick="uploadImage(this)" projectImage="system/profile/add_img.png" name="projectImage" src="<%=qiniu%>system/profile/add_img.png"></em><span><img src="<%=basePath%>images/pay_close.png"></span></li>
							 <li><em><img onclick="uploadImage(this)" affiliatedImage="system/profile/add_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img.png"></em><span><img src="<%=basePath%>images/pay_close.png"></span></li>
							 <li><em><img onclick="uploadImage(this)" affiliatedImage="system/profile/add_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img.png"></em><span><img src="<%=basePath%>images/pay_close.png"></span></li>
							 <li><em><img onclick="uploadImage(this)" affiliatedImage="system/profile/add_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img.png"></em><span><img src="<%=basePath%>images/pay_close.png"></span></li>
							 <li><em><img onclick="uploadImage(this)" affiliatedImage="system/profile/add_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img.png"></em><span><img src="<%=basePath%>images/pay_close.png"></span></li>
							 <li><em><img onclick="uploadImage(this)" affiliatedImage="system/profile/add_img.png" name="affiliatedImage" src="<%=qiniu%>system/profile/add_img.png"></em><span><img src="<%=basePath%>images/pay_close.png"></span></li>
						  </ul>
					   </div>   
					   
					   <div class="item_saying">
					     <p>项目描述</p>
					      <div class="textarea1">
						      <div><button id="editImage" style="width:130px;height:26px;line-height:26px;text-align:center;border:none;background:#617195;color:white;border-radius:10px;margin-top:10px;margin-left:10px">插入图片</button></div>
				              <P></P>
				              <div class="clearfix">
									<script id="editor1" type="text/plain" style="width:550px;height:322px;float: left"></script>
									<div style="float: left; width: 320px; height: 420px; margin-top: 25px" class="textarea_text">
										<p>在此编辑的内容，将会在移动端－在线预约－项目详情中展示。</p>
										<p></p>
										<p>插入图片后，请保持图片的原样。切勿拖拽图片大小。自动生成的图片可自动适配所有手机显示。</p>
										<p>插入图片后，请保持图片的原样。切勿拖拽图片大小。自动生成的图片可自动适配所有手机显示。</p>
										<p>如若无法预览或全屏编辑或出现其他编辑问题。请更换谷歌浏览器，体验更佳。</p>
									</div>
								</div>	
				            </div>
					   </div>
					 <div class="item_button">  
					   <button onclick="save3()">保存</button>
					   <button>取消</button>
					  </div>
				    </div>
				    <div class="add_store_content clearfix" style="display: none;">
				      <div class="shop_price">
					    <p class="shop_price_1">门店价格<span><input name="projectPrice" type="number"><em>元</em></span><i style="display:inline-block;width:105px;margin-left:72px">成本价格</i><span><input name="projectPrice" type="costPrice"><em>元</em></span></p>  
					    <p class="shop_price_2">接受礼金<i><input type="radio" name="isGiftCash" checked="checked" value="1">是</i><i><input type="radio" name="isGiftCash"  value="1">否</i><i>最大抵扣礼金</i><span><input name="highestDiscount" type="number"><em>元</em></span></p>
						<p class="shop_price_2">接受预约<i><input type="radio" name="isAppointment" checked="checked" value="1">是</i><i><input type="radio" name="isAppointment"  value="0">否</i><i>预约优惠价格</i><span><input name="appointmentPrice" type="number" class="input_3"><em>元</em></span></p>
					  </div>	 
			          <div class="vip_price">
					    <p>会员价格</p>
						<div class="vip_price_content">
						  <p>为不同会员设置折扣，会员低于<input type="text">％设置固定价格<span style="display:inline-block;width:16px;height:16px;background:#ef4f4f;color:white;border-radius:16px;line-height:16px;text-align:center;margin-left:5px;font-size:16px">?</span><button onclick="addLevel()" style="width:126px;height:26px;text-align:center;line-height:26px;border-radius:10px;color:white;border:none;background:#657392;position:relative;left:330px">新建</button></p>
						  <div class="vip_roll">
						    <span class="left_click"><img src="<%=basePath%>images/left_click.png"></span>
			               <div class="roll_ul">			    
							 <ul class="clearfix" id="projectLevel">
								<!-- 会员等级 -->
			                 </ul>
							</div>
							<span class="right_click"><img src="<%=basePath%>images/right_click.png"></span>
						  </div>
						</div>
					  </div>	
			
				     <div class="demo2_button">
				     <button onclick="save3()">保存</button>
					  <button>取消</button>
				   </div>		  
				  </div>
				  
				  <div class="add_store_content clearfix" style="display: none;">
			        <div class="table_step">
							<table class="table_s">
								<tr>
									<td style="border-right: 1px solid #dbdce2 !important; " class="color_">步骤顺序</td>
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
							<table style="border: 1px solid #dbdce2; box-shadow: 0 0 10px #ccc;width: 900px">
								<tr>
									<td style="border-right: 1px solid #dbdce2 !important; border-bottom: 1px solid #dbdce2 !important;">步骤顺序</td>
									<td >轮牌名称</td>
									<td >步骤名称</td>
									<td >业绩计算方式</td>
									<td >员工业绩</td>
									<td>是否可预约</td>
								</tr>
								<tr>
									<td class="first" style="border-right: 1px solid #dbdce2 !important;">第一步</td>
									<td td style=""><select class="cut_step" onchange="changeMahjongStep(this.value);" name="shiftMahjongId">
											<c:if test="${!empty deptMahjongList }">
												<c:forEach var="shiftMahjongDto" items="${deptMahjongList[0].mahjongLevelList }" varStatus="status">
													<option value="${shiftMahjongDto.shiftMahjongId }">${shiftMahjongDto.shiftMahjongName }</option>
												</c:forEach>
											</c:if>
									</select></td>
									<td ><input type="text" class="cut_step_1" maxlength="8" name="projectStepName"></td>
									<td ><select class="cut_step_2" name="stepPerformanceType">
											<option value="2">固定</option>
											<option value="1">比例</option>

									</select></td>
									<td ><input type="number" class="cut_step_3" name="stepPerformance"></td>
									<td >
										<div class="cut_step_4">
											<input type="radio" onclick="chkRadio(this)" value="0" name="isDisable" style="width:18px">
										</div>

									</td>
								</tr>
							</table>

							<table class="step_2">
								<tbody>
									<tr>
										<td style=" border-bottom: 1px solid #dbdce2 !important; border-right: 1px solid #dbdce2 !important;">职位名称</td>
										<td >提成方式</td>
										<td >指定提成</td>
										<td >非指定提成</td>
										<td >预约奖励</td>
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
										<td><input type="number" name="assignCash" class="cut_step_11"></td>
										<td><input type="number" name="assignCard" class="cut_step_11"></td>
										<td><input type="number" name="appointmentReward" class="cut_step_11"></td>
		                                </tr>
		                            </c:forEach>
								</tbody>
							</table>
							<button class="button_1" onclick="uploadMessageLevel()">保存</button>
						</div>
						<div class="write_4">
							<span class="add_step" style="position: relative; left: -10px">+</span>添加轮牌
						</div>
						<br>
					  <div class="item_button">  
					   <button onclick="save3()">保存</button>
					   <button>取消</button>
					  </div>
			   </div>
			   
			  </div>	 
			   
			</div>
		</div>
	</div>
</div>
<div class="mask" style="display: none;">
   <div id="flashEditorOut" >
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
	var qiniu = '<%=qiniu %>';
	var imgObject;
	var type = 1;
	jQuery(function() {
		jQuery('.add_pic_ img').click(function() {
			jQuery(".mask").show();
			editPage(null);
			imgObject = jQuery(this);
			type=1;
		})
		jQuery('.cancelinput').click(function() {
			jQuery('.zzc.one').hide();
			jQuery('.photo-clip-rotateLayer').html('');
		})
		jQuery('#editImage').click(function() {
			type=2;
			jQuery(".mask").show();
			editPage(null);
		})
	})
	function zccCallback(dataBase64){
		var key = "jobwisdom/project/" + new Date().getTime();
		if ((typeof(imgObject.attr("projectImage")))!="undefined"){
			imgObject.attr("projectImage", key);
		}else {
			imgObject.attr("affiliatedImage", key);
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
			       	if(type==1){
						if ((typeof (imgObject.attr("projectimage"))) != "undefined") {
							imgObject.attr("projectimage", key);
							imgObject.attr("src", qiniu+key);
						} else {
							imgObject.attr("affiliatedImage", key);
							imgObject.attr("src", qiniu+key);
						}
					}else{
						u1.execCommand('insertHtml', '<img style="margin-top: 0px; width: 100%; padding: 0px; border-color: rgb(30, 155, 232); color: inherit; height: 100%;" data-width="100%" border="0" vspace="0" src="'+qiniu+key+'">');
					}
		       }
     	});
	}
	
	var toolbars = {
			toolbars: [
			   		['fullscreen', 'source', '|', 'undo', 'redo', '|',
			           'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript','|', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
			           'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
			           'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
			           'directionalityltr', 'directionalityrtl', 'indent', '|',
			           'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase','preview']
			   	],maximumWords:3000,elementPathEnabled:false,imageScaleEnabled:false,wordCount:false,autoHeightEnabled:false};
	var u1 = UE.getEditor('editor1', toolbars);
	
</script>

<script>
	
	var deptProjectList = ${js_deptProjectList};
	var deptMahjongList = ${mahjongList};
	var memberLevelList = ${memberLevelList};
	var employeeLevelList = ${employeeLevelList};
	
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
						'<td style=" border-bottom: 1px solid #dbdce2 !important; border-right: 1px solid #dbdce2 !important;">职位名称</td>'+
						'<td>提成方式</td>'+
						'<td >指定提成</td>'+
						'<td >非指定提成</td>'+
						'<td>预约奖励</td>'+
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
					'<td><input type="number" name="assignCash" class="cut_step_11"></td>'+
					'<td><input type="number" name="assignCard" class="cut_step_11"></td>'+
					'<td><input type="number" name="appointmentReward" class="cut_step_11"></td>'+
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
	
	/**
	 * 保存数据,根据步骤去保存数据
	 */
	function save3(){
		var data = coverDate();
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
		var projectType = jQuery("select[name='projectType']").val();
		var projectDesc = u1.getContent();
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
		var projectPrice = jQuery("input[name='projectPrice']").val();
		var costPrice = jQuery("input[name='costPrice']").val();
		var appointmentPrice = jQuery("input[name='appointmentPrice']").val();
		var highestDiscount = jQuery("input[name='highestDiscount']").val();
		var isGiftCash = jQuery('input:radio[name="isGiftCash"]:checked').val();
		var isAppointment = jQuery('input:radio[name="isAppointment"]:checked').val();
		data = {
			"storeId" : storeId,
			"projectId" : projectId,
			"deptId" : deptId,
			"categoryId" : categoryId,
			"projectName" : projectName,
			"projectType" : projectType,
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
		jQuery("#projectLevel").find("li").each(function(){
			var projectId = projectId;
			var discountLevel = jQuery(this).find("select[name='discountLevel']").val();
			var discountAmount = jQuery(this).find("input[name='discountAmount']").val();
			projectLevel.push({"projectId":projectId,"levelId":discountLevel,"discountAmount":discountAmount});
		});
		
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
			var shiftMahjongId = jQuery('.table_s').find("tr").eq(i).attr("shiftmahjongid");
			var jsonObject = {"projectId":projectId,"shiftMahjongId":shiftMahjongId,"levelId":levelId,"assignCashType":assignCashType,"assignCash":assignCash,"assignCardType":assignCardType,"assignCard":assignCard,"appointmentRewardType":appointmentRewardType,"appointmentReward":appointmentReward,"isDeleted":0};
			data2.push(jsonObject);
		}
		var dataStep = {"projectId":projectId,"step":data1,"commission":data2};
		
		deptId = deptId;
		return {"projectInfo":data, "projectLevel":projectLevel, "projectShit":dataStep};
	}
	

	/**
	*　为项目table添加步骤和职位信息
	*/
	jQuery('.button_1').click(function(){
		var shiftMahjongId = jQuery("select[name='shiftMahjongId']").val();
		var shiftMahjongName = jQuery("select[name='shiftMahjongId']").find("option:selected").text();
	})
	
	
	u1.ready(function(){
		if (projectId != '') {
			var projectDesc = '${projectDesc}';
			project = eval('('+'${projectInfo}'+')');
			projectCommissionList = eval('('+'${projectCommissionList}'+')');
			projectDiscountList = eval('('+'${projectDiscountList}'+')');
			projectStepList = eval('('+'${projectStepList}'+')');
			projectStep = project.projectStep;
			deptId = project.deptId;
			
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
			jQuery("img[name='projectImage']").attr("src",  qiniu+projectImage);
			var affiliatedImage = project.affiliatedImage;
			for (var i = 0; i < affiliatedImage.split(",").length; i++) {
				jQuery("img[name='affiliatedImage']").eq(i).attr("affiliatedImage", affiliatedImage.split(",")[i]);
				jQuery("img[name='affiliatedImage']").eq(i).attr("src", qiniu+affiliatedImage.split(",")[i]);
			}
			/**锁定项目价格*/
			jQuery("select[name='projectType']").val(project.projectType);
			jQuery("input[name='projectName']").val(project.projectName);
			u1.setContent(projectDesc);
			//jQuery("textarea[name='projectDesc']").val(project.projectDesc);
			jQuery("input[name='projectCodeSuffix']").val(project.projectCodeSuffix);
			
			jQuery("input[name='projectPrice']").val(project.projectPrice);
			jQuery("input[name='costPrice']").val(project.costPrice);
			jQuery("input[name='appointmentPrice']").val(project.appointmentPrice);
			jQuery("input[name='highestDiscount']").val(project.highestDiscount);
			
			jQuery("input[name='isGiftCash'][value='"+project.isGiftCash+"']").click();
			jQuery("input[name='isAppointment'][value='"+project.isAppointment+"']").click();
			
			/**锁定轮牌职位信息*/
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
					
					var disableList = ['否','是'];
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
						var levelName = null;
						for (var m = 0; m < employeeLevelList.length; m++) {
							if (employeeLevelList[m].levelId == levelId){
								levelName = employeeLevelList[m].levelName;
							}
						}
						var assignType = projectCommissionList[isSame[j]].assignCashType;
						var assignCash = projectCommissionList[isSame[j]].assignCash;
						var assignCard = projectCommissionList[isSame[j]].assignCard;
						var appointmentReward = projectCommissionList[isSame[j]].appointmentReward;
						if (j == 0){
							var tr = jQuery('<tr shiftMahjongId="'+shiftMahjongId+'" step='+step+'>'+
									'<td rowspan="'+rowspan+'" style="border-right: 1px solid #dbdce2!important;">'+
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
				jQuery("#projectLevel").append(jQuery("#li").html());
				jQuery("#projectLevel").children("li").eq(i).find("select[name='discountLevel']").val(levelId);
				jQuery("#projectLevel").children("li").eq(i).find("input[name='discountAmount']").val(discountAmount);
			}
		}
		else {
			projectId = null;
		}
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
		//校验数据
		var checkJson = {"projectStepName":projectStepName,"stepPerformance":stepPerformance};
		var isOk = true;
		jQuery.each(checkJson,function(name,value) {
			if(!isNotNull(value)){
				jQuery("input[name='"+name+"']").addClass("border");
				isOk = false;
				return false;
			}
		});
		if(!isOk)return;
		
		var hasStep = jQuery(".table_s").find("td[rowspan]");
		var disableList = ['比例','固定'];
		var assignTypeList = ['','比例','固定'];
		
		var isHide = true;
		
		for (var i = 0; i < jQuery(".empLevel").length; i++) {
			var levelId = jQuery("select[name='levelId']").eq(i).val();
			var levelName = jQuery("select[name='levelId']").eq(i).find("option:selected").text();
			var assignType = jQuery("select[name='assignType']").eq(i).val();
			var assignTypeName = jQuery("select[name='assignType']").eq(i).find("option:selected").text();
			var assignCash = jQuery("input[name='assignCash']").eq(i).val();
			var assignCard = jQuery("input[name='assignCard']").eq(i).val();
			var appointmentReward = jQuery("input[name='appointmentReward']").eq(i).val();
			
			var checkJson = {"assignCash":assignCash,"assignCard":assignCard,"appointmentReward":appointmentReward};
			var isOk = true;
			jQuery.each(checkJson,function(name,value) {
				if(!isNotNull(value)){
					jQuery("input[name='"+name+"']").addClass("border");
					isOk = false;
					isHide = false;
					return false;
				}
			});
			if(!isOk)return;
			
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
		if(isHide){
			jQuery('.step_1').hide();
			jQuery('.write_4').show();
		}
	}
	
	/**
	*  保存会员卡折扣
	*/
	jQuery('.save_').click(function(){
		var levelId = jQuery("select[name='discountLevel']").val();
		var levelName = jQuery("select[name='discountLevel']").find("option:selected").text();
		var discountAmount = jQuery("input[name='discountAmount']").val();
		var memberCostPrice = jQuery("input[name='memberCostPrice']").val();
		
		var checkJson = {"discountAmount":discountAmount,"memberCostPrice":memberCostPrice};
		var isOk = true;
		jQuery.each(checkJson,function(name,value) {
			if(!isNotNull(value)){
				jQuery("input[name='"+name+"']").addClass("border");
				isOk = false;
				return false;
			}
		});
		if(!isOk)return;
		
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

	function addLevel(){
		jQuery("#projectLevel").append(jQuery("#li").html());
	}

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
		if (jQuery(opt).val() == "1"){
			opt.checked = false;
			jQuery(opt).val("0");
		}
		else {
			opt.checked = true;
			jQuery(opt).val("1");
		}
	}

	/** 非空校验 */
	function isNotNull(str){
		if(str!=null&&str!=''&&typeof(str)!="undefined")return true;
		return false;
	}
	/** 重新获取焦点的时候,去掉校验的红色框 */
	jQuery(function(){
		jQuery("input").focus(function(){jQuery(this).removeClass("border")});
	})
	
</script>
</html>