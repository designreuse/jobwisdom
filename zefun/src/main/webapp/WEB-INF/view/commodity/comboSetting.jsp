<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xss26.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/demo4.css" type="text/css" />
<style>
.bt{
    width: 130px;
    height: 28px;
    color: white;
    border: none;
    border-radius: 10px;
    text-align: center;
    line-height: 28px;
    color: white;
    background: #59688a;
    margin-right: 80px;
    margin-top: 20px;
    }
</style>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript">
    
    function editPage (imgUrl) {
    	xiuxiu.setLaunchVars("cropPresets", "162*162");
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
    function zccCallback(dataBase64){
		var key = "jobwisdom/project/" + new Date().getTime();
    	jQuery("img[name='comboImage']").attr("comboImage", key);
	    var data = {"stringBase64":dataBase64,"key":key};
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
		    	   jQuery("img[name='comboImage']").attr("src", qiniuUrl+data.msg.key);
		       }
     	});
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
	  //向右走
	  var now_=0;
      jQuery(document).on('click','.right_click',function(){
	  count=jQuery(this).parents('.select_item_content').find('.select_item_content_roll_ul li').length;
         if(now_<=count-4){
		    now_+=1;
	        jQuery(this).parent().find('.select_item_content_roll_ul ul').stop(true,true).animate({
		       left:-252*now_
		   
		       }) 
			  }
		  });
	  	//向左走
	 jQuery(document).on('click','.left_click',function(){
         if(now_>=1){
		    now_-=1;
	         jQuery(this).parent().find('.select_item_content_roll_ul ul').stop(true,true).animate({
		     left:-252*now_
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
	<div class="mask" style="display: none;">
		   <div id="flashEditorOut" >
			        <div id="altContent2">
			            <h1>美图秀秀</h1>
			        </div>
				</div>
	</div>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right clearfix">
					<div class="add_store_div clearfix">
						<ul class="clearfix add_store_back">
							<li class="active"><span>新增疗程</span></li>
							<li class=""><span style="position: relative; top: 20px">价格<i style="transform: rotate(-45deg);">\</i>提成<i style="transform: rotate(-45deg);">\</i>描述
							</span></li>

						</ul>

						<div class="add_store_include">
							<div class="add_store_content clearfix" style="display: block;">
								<div class="add_store_content_1_content clearfix">
									<div class="add_store_content_1_left">
										<img onclick="jQuery('.mask').show();editPage(null);" name="comboImage" comboImage="system/profile/combo.png" src="<%=qiniu%>system/profile/combo.png">
									</div>
									<div class="add_store_content_1_center">
										<p>
											<em>疗程名称</em><input name="comboName" type="text">
										</p>
										<p>
											<em>疗程所属部门</em><select name="deptId"><c:forEach items="${deptInfoList }" var="deptInfo"><option value="${deptInfo.deptId }">${deptInfo.deptName }</option></c:forEach></select>
										</p>
									</div>
									<div class="add_store_content_1_right">
										<p>
											<em>疗程编号</em><span><input  type="text" disabled=""></span><input type="text" name="comboCodeSuffix">
										</p>
									</div>
								</div>
								<div class="select_item">
									<p>为疗程选择项目</p>
									<div class="select_item_content">
										<table>
											<tbody>
												<tr>
													<td>项目名称</td>
													<td>门店价格</td>
													<td>是否有次数限制</td>
													<td>疗程内项目数量</td>
													<td>疗程单次服务业绩计算</td>
													<td rowspan="2"><button onclick="saveProject()">确认</button></td>
												</tr>
												<tr>
													<td><select onchange="descProjectPrice(this)" name="projectId"><c:forEach items="${projectInfoDtoList }" var="projectInfo"><option projectPrice="${projectInfo.projectPrice }" value="${projectInfo.projectId }">${projectInfo.projectName }</option></c:forEach></select></td>
													<td>0.00 元</td>
													<td><span><input type="radio" name="isCountLimit" value="1" checked="checked">是</span><span><input type="radio" name="isCountLimit" value="0">否</span></td>
													<td><input type="text" name="projectCount" value="0"></td>
													<td><input type="text" name="comboPerformance" value="0" style="padding-right: 20px; width: 106px"><i>元</i></td>
												</tr>
											</tbody>
										</table>
										<div class="select_item_content_roll">
											<span class="left_click"><img src="<%=basePath%>images/left_click.png"></span>
											<div class="select_item_content_roll_ul">
												<ul id="project" class="clearfix">
												</ul>
											</div>
											<span class="right_click"><img src="<%=basePath%>images/right_click.png"></span>
										</div>
									</div>
								</div>

								<div class="select_item">
									<p>为疗程选择商品</p>
									<div class="select_item_content">
										<table>
											<tbody>
												<tr>
													<td>商品名称</td>
													<td>商品原价</td>
													<td>疗程内商品数量</td>
													<td>疗程商品业绩计算</td>
													<td rowspan="2"><button onclick="saveGoods()">创建</button></td>
												</tr>
												<tr>
													<td><select onchange="descGoodsPrice(this)" name="goodsId"><c:forEach items="${goodsinfos }" var="goodsinfo"><option goodsPrice="${goodsinfo.goodsPrice }" value="${goodsinfo.goodsId }">${goodsinfo.goodsName }</option></c:forEach></select></td>
													<td>0.00元</td>
													<td><input type="text" name="goodsCounts" value="0"></td>
													<td><input type="text" name="comboPerformanceCal" value="0" style="padding-right: 20px; width: 106px"><i style="right: 48px">元</i></td>
												</tr>
											</tbody>
										</table>
										<div class="select_item_content_roll">
											<span class="left_click"><img src="<%=basePath%>images/left_click.png"></span>
											<div class="select_item_content_roll_ul">
												<ul id="goods" class="clearfix">
													
												</ul>
											</div>
											<span class="right_click"><img src="<%=basePath%>images/right_click.png"></span>
										</div>
									</div>
								</div>
								<div class="item_button" style="text-align: center;">  
									   <button class="bt" onclick="coverData()">保存</button>
									   <button class="bt">取消</button>
								</div>
							</div>

							<div class="add_store_content clearfix" style="display: none;">
								<div class="add_store_content_2">
									<p>设置疗程价格及销售提成</p>
									<div class="add_store_content_price">
										<table>
											<tbody>
												<tr>
													<td>项目原总价</td>
													<td>是否有时间限制</td>
													<td>有效期</td>
												</tr>
												<tr>
													<td>0.00元</td>
													<td><span><input type="radio" checked="checked" name="standard" value="1">是</span><span><input type="radio" name="standard" value="0">否</span></td>
													<td><input type="text" name="validDate" value="0" style="width: 80px; padding-right: 20px"><i>天</i></td>
												</tr>
											</tbody>
										</table>
										<table style="width: 910px">
											<tbody>
												<tr>
													<td>疗程销售价</td>
													<td>提成方式</td>
													<td>现金提成</td>
													<td>划卡提成</td>
													<td>员工销售业绩计算</td>
												</tr>
												<tr>
													<td><input type="text" name="comboSalePrice" value="0"><i>元</i></td>
													<td><span><input type="radio" name="commissionType" checked="checked" value="2">固定</span><span><input type="radio" name="commissionType" value="1">比例</span></td>
													<td><input type="text" name="cashCommission" value="0" style="width: 80px; padding-right: 20px"></td>
													<td><input type="text" name="cardCommission" value="0" style="width: 80px; padding-right: 20px"></td>
													<td><input type="text" name="saleComboPerformance" value="0" style="width: 80px; padding-right: 20px"></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
								<div class="add_store_content_2_saying">
									<p>疗程描述</p>
									<div class="add_store_content_textarea">
										<textarea name="comboDesc"></textarea>
									</div>
								</div>
								<div class="item_button" style="text-align: center;">  
								   <button class="bt" onclick="coverData()">保存</button>
								   <button class="bt">取消</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script id="projectCommission" type="text/html">
</script>

<script>
	var comboId = null;
	var projectInfoList = eval(<%=request.getAttribute("projectInfoList")%>);
	var deptInfoListDate = eval(<%=request.getAttribute("js_deptInfoList")%>);
	var goodsinfos = eval(<%=request.getAttribute("goodsinfos_js")%>);
	
	// 选择查看了一个套餐
	if (!'${comboId}' == ""){
		comboId = '${comboId}';
		var comboInfo = eval(<%=request.getAttribute("comboInfo")%>);
		var comboProjectList = eval(<%=request.getAttribute("comboProjectList")%>);
		var comboGoods = eval(<%=request.getAttribute("comboGoods")%>);
		
		jQuery("img[name='comboImage']").attr("comboImage", comboInfo.comboImage);
		jQuery("img[name='comboImage']").attr("src", qiniuUrl+comboInfo.comboImage);
		jQuery("input[name='comboName']").val(comboInfo.comboName);
		jQuery("select[name='deptId']").val(comboInfo.deptId);
		jQuery("input[name='validDate']").val(comboInfo.validDate);
		jQuery("input[name='standard'][value='"+comboInfo.standard+"']").click();
		jQuery("input[name='comboSalePrice']").val(comboInfo.comboSalePrice);
		jQuery("input[name='commissionType'][value='"+comboInfo.commissionType+"']").click();
		jQuery("input[name='cashCommission']").val(comboInfo.cashCommission);
		jQuery("input[name='cardCommission']").val(comboInfo.cardCommission);
		jQuery("input[name='saleComboPerformance']").val(comboInfo.comboPerformance);
		
		jQuery("textarea[name='comboDesc']").val(comboInfo.comboDesc);
		jQuery("input[name='comboCodeSuffix']").val(comboInfo.comboCodeSuffix);
		
		for (var i = 0; i < comboProjectList.length; i++) {
			var projectId = comboProjectList[i].projectId;
			var projectName = comboProjectList[i].projectName;
			var projectPrice = comboProjectList[i].projectPrice;
			var isCountLimit = comboProjectList[i].isCountLimit;
			var projectCount = comboProjectList[i].projectCount;
			var comboPerformanceCal = comboProjectList[i].comboPerformanceCal;
			saveShowProject(comboId, projectId, projectName, projectPrice, isCountLimit, projectCount, comboPerformanceCal);
		}
		
		for (var i = 0; i < comboGoods.length; i++) {
			var goodsId = comboGoods[i].goodsId;
			var goodsName = comboGoods[i].goodsName;
			var goodsPrice = comboGoods[i].goodsPrice;
			var goodsCounts = comboGoods[i].goodsCounts;
			var comboPerformanceCal = comboGoods[i].comboPerformanceCal;
			saveShowGoods(comboId, goodsId, goodsName, goodsPrice, goodsCounts, comboPerformanceCal);
		}
		console.log(comboId);
	}
	
	/**保存套餐内的项目*/
	function saveProject(){
		var projectId = jQuery("select[name='projectId']").val();
		var projectName = jQuery("select[name='projectId']").children("option:selected").text();
		var projectPrice = jQuery("select[name='projectId']").children("option:selected").attr("projectPrice");
		var projectCount = jQuery("input[name='projectCount']").val();
		var comboPerformanceCal = jQuery("input[name='comboPerformance']").val();
		var isCountLimit = jQuery("input[name='isCountLimit']:checked").val();
		saveShowProject(comboId, projectId, projectName, projectPrice, isCountLimit, projectCount, comboPerformanceCal);
	}
	/**保存项目展示*/
	function saveShowProject(comboId, projectId, projectName, projectPrice, isCountLimit, projectCount, comboPerformanceCal){
		var html = '<li comboId="'+comboId+'" projectId="'+projectId+'" projectName="'+projectName+'" projectPrice="'+projectPrice+'" isCountLimit="'+isCountLimit+'" projectCount="'+projectCount+'" comboPerformanceCal="'+comboPerformanceCal+'">'+
						'<p>名称：'+projectName+'</p>'+
						'<p>'+
							'<span>数量：'+projectCount+'</span> <span>价格'+projectPrice+'元</span>'+
						'</p>'+
						'<p>单次服务业绩：'+comboPerformanceCal+'元</p>'+
						'<div class="select_item_content_roll_ul_button">'+
							'<button onclick="editProject(this)">编辑</button>'+
							'<button onclick="jQuery(this).parents(\'li\').remove();">删除</button>'+
						'</div>'+
				   '</li>';
		jQuery("#project").append(jQuery(html));
	}
	
	function saveGoods(){
		var goodsId = jQuery("select[name='goodsId']").val();
		var goodsName = jQuery("select[name='goodsId']").children("option:selected").text();
		var goodsPrice = jQuery("select[name='goodsId']").children("option:selected").attr("goodsPrice");
		var goodsCounts = jQuery("input[name='goodsCounts']").val();
		var comboPerformanceCal = jQuery("input[name='comboPerformanceCal']").val();
		saveShowGoods(comboId, goodsId, goodsName, goodsPrice, goodsCounts, comboPerformanceCal);
	}
	/**保存商品展示*/
	function saveShowGoods(comboId, goodsId, goodsName, goodsPrice, goodsCounts, comboPerformanceCal){
		var html = '<li comboId="'+comboId+'" goodsId="'+goodsId+'" goodsName="'+goodsName+'" goodsPrice="'+goodsPrice+'" goodsCounts="'+goodsCounts+'" comboPerformanceCal="'+comboPerformanceCal+'">'+
				'<p>名称：'+goodsName+'</p>'+
				'<p>'+
					'<span>数量：'+goodsCounts+'</span> <span>价格'+goodsPrice+'元</span>'+
				'</p>'+
				'<p>单次服务业绩：'+comboPerformanceCal+'元</p>'+
				'<div class="select_item_content_roll_ul_button">'+
					'<button onclick="editGoods(this)">编辑</button>'+
					'<button onclick="jQuery(this).parents(\'li\').remove();">删除</button>'+
				'</div>'+
			'</li>';
		jQuery("#goods").append(jQuery(html));
	}
	/**编辑项目*/
	function editProject(but){
		var projectId = jQuery(but).parents("li").attr("projectId");
		var projectName = jQuery(but).parents("li").attr("projectName");
		var projectPrice = jQuery(but).parents("li").attr("projectPrice");
		var projectCount = jQuery(but).parents("li").attr("projectCount");
		var comboPerformanceCal = jQuery(but).parents("li").attr("comboPerformanceCal");
		jQuery("select[name='projectId']").val(projectId);
		jQuery("input[name='projectCount']").val(projectCount);
		jQuery("input[name='comboPerformance']").val(comboPerformanceCal);
		jQuery(but).parents("li").remove();
	}
	/**编辑商品*/
	function editGoods(but){
		var goodsId = jQuery(but).parents("li").attr("goodsId");
		var goodsCounts = jQuery(but).parents("li").attr("goodsCounts");
		var comboPerformanceCal = jQuery(but).parents("li").attr("comboPerformanceCal");
		jQuery("select[name='goodsId']").val(goodsId);
		jQuery("input[name='goodsCounts']").val(goodsCounts);
		jQuery("input[name='comboPerformanceCal']").val(comboPerformanceCal);
		jQuery(but).parents("li").remove();
	}
	
	function coverData(){
		var data1 = [];
		jQuery("#project").children("li").each(function (){
			var projectId = jQuery(this).attr("projectId");
			var projectName = jQuery(this).attr("projectName");
			var projectPrice = jQuery(this).attr("projectPrice");
			var isCountLimit = jQuery(this).attr("isCountLimit");
			var projectCount = jQuery(this).attr("projectCount");
			var comboPerformanceCal = jQuery(this).attr("comboPerformanceCal");
			var comboProject = {"projectId":projectId,"projectName":projectName,"projectPrice":projectPrice,"isCountLimit":isCountLimit,"projectCount":projectCount,"comboPerformanceCal":comboPerformanceCal};
			data1.push(comboProject);
		});
		var data2 = [];
		jQuery("#goods").children("li").each(function (){
			var goodsId = jQuery(this).attr("goodsId");
			var goodsName = jQuery(this).attr("goodsName");
			var goodsPrice = jQuery(this).attr("goodsPrice");
			var goodsCounts = jQuery(this).attr("goodsCounts");
			var comboPerformanceCal = jQuery(this).attr("comboPerformanceCal");
			var comboGoods = {"goodsId":goodsId,"goodsName":goodsName,"goodsPrice":goodsPrice,"goodsCounts":goodsCounts,"comboPerformanceCal":comboPerformanceCal};
			data2.push(comboGoods);
		});
		
		var comboImage = jQuery("img[name='comboImage']").attr("comboImage");
		var comboName = jQuery("input[name='comboName']").val();
		var deptId = jQuery("select[name='deptId']").val();
		var validDate = jQuery("input[name='validDate']").val();
		var standard = jQuery("input[name='standard']:checked").val();
		var comboSalePrice = jQuery("input[name='comboSalePrice']").val();
		var commissionType = jQuery("input[name='commissionType']:checked").val();
		var cashCommission = jQuery("input[name='cashCommission']").val();
		var cardCommission = jQuery("input[name='cardCommission']").val();
		var comboPerformance = jQuery("input[name='saleComboPerformance']").val();
		
		var comboDesc = jQuery("textarea[name='comboDesc']").val();
		var comboCodeSuffix = jQuery("input[name='comboCodeSuffix']").val();
		var comboInfo = {"comboId":comboId,"comboImage":comboImage,"deptId":deptId,"validDate":validDate,"standard":standard,"comboCodeSuffix":comboCodeSuffix,
				"comboSalePrice":comboSalePrice,"commissionType":commissionType,"cashCommission":cashCommission,"comboName":comboName,
				"cardCommission":cardCommission,"comboPerformance":comboPerformance,"comboDesc":comboDesc};

		var data ={"comboInfo":comboInfo,"comboProject":data1,"comboGoods":data2};
		console.log(JSON.stringify(data));
		jQuery.ajax({
			type : "post",
			url : baseUrl + "comboInfo/save/all/setting",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
			async : false,
			success : function(data) {
				if (data.code == 0){
					dialog("该套餐已更新");
					comboId = data.msg.comboId;
				}
			}
		});
	}
	
	function descProjectPrice(select){
		var projectPrice = jQuery("select[name='projectId']").children("option:selected").attr("projectPrice");
		jQuery(select).parent().next().text(projectPrice+" 元");
	}
	function descGoodsPrice(select){
		var goodsPrice = jQuery("select[name='goodsId']").children("option:selected").attr("goodsPrice");
		jQuery(select).parent().next().text(goodsPrice+" 元");
	}
</script>
</body>
</html>