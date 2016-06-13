<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/demo.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
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
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right clearfix">
					<div class="phone_goods clearfix">
						<div class="phone">
							<div class="phone_header">
								<span class="phone_input"> <input type="text" placeholder="搜索想要的商品"> <em><img src="<%=basePath%>images/phone_search.png"></em>
								</span> <span class="phone_buy"><img src="<%=basePath%>images/phone_buy.png"></span>
							</div>
							<div class="phone_ad" onclick="exchange('gg')">添加广告图</div>
							<ul class="clearfix phone_ul">
								<li onclick="exchange('xpsj')"><img src="<%=basePath%>images/phone_goods_1.png">
									<div>新品上市</div></li>
								<li onclick="exchange('rxsp')"><img src="<%=basePath%>images/phone_goods_2.png">
									<div>热销商品</div></li>
								<li><img src="<%=basePath%>images/phone_goods_3.png">
									<div>积分兑换</div></li>
							</ul>
							<div class="phone_content" >
								<p class="default">
									<span>获取</span><a href="javascript:;">更多&gt;&gt;</a>
								</p>
								
								
								<div class="phone_content_" style="position:relative">
								   <div style=" position: absolute;top: 0px; height: 315px;left: 0px;width: 100%;z-index: 10000;background: rgba(102, 108, 121, 0.8);"></div>
									<div class="zzc">商品设置列表</div>
									<ul class="clearfix">
										<li>
											<div>
												<img src="<%=basePath%>images/phone_goods_3.png">
											</div>
											<div style="padding-left: 5px">
												<h5>鞋子</h5>
												<p>
													<span>已售：121</span><em>¥500</em>
												</p>
											</div>
										</li>

										<li>
											<div>
												<img src="<%=basePath%>images/phone_goods_3.png">
											</div>
											<div style="padding-left: 5px">
												<h5>鞋子</h5>
												<p>
													<span>已售：121</span><em>¥500</em>
												</p>
											</div>
										</li>

										<li>
											<div>
												<img src="<%=basePath%>images/phone_goods_3.png">
											</div>
											<div style="padding-left: 5px">
												<h5>鞋子</h5>
												<p>
													<span>已售：121</span><em>¥500</em>
												</p>
											</div>
										</li>

										<li>
											<div>
												<img src="<%=basePath%>images/phone_goods_3.png">
											</div>
											<div style="padding-left: 5px">
												<h5>鞋子</h5>
												<p>
													<span>已售：121</span><em>¥500</em>
												</p>
											</div>
										</li>
									</ul>

								</div>
							</div>
						</div>

						<div id="gg" class="adjust_goods_1" style="display: block;">
							<!-- <button class="adjust_goods_button" onclick="jQuery('.mask').show();editPage(null);">添加图片</button> -->
							<ul class="add_pic clearfix" style="width:570px">
							   <%-- <li><img src="<%=qiniuPath%>click_add.png"></li>
							   <li><img src="<%=qiniuPath%>click_add.png"></li>
							   <li><img src="<%=qiniuPath%>click_add.png"></li>
							   <li><img src="<%=qiniuPath%>click_add.png"></li>
							   <li><img src="<%=qiniuPath%>click_add.png"></li>
							   <li><img src="<%=qiniuPath%>click_add.png"></li> --%>
							</ul>
							<button class="adjust_goods_button" onclick="save('adsense')">保存</button>
						</div>
						<div id="xpsj" class="adjust_goods" style="display: none;">
							<div class="adjust_goods_p" style="margin-top:10px">
								<span class="adjust_goods_span">调整货物</span><em>点击一个未选择商品，放入已选择吧！</em>
								<button onclick="save('xpsj')">保存</button>
							</div>

							<div class="adjust_ul">
								<p class="no_choose">未选择</p>
								<div class="adjust_list_1">
									<c:forEach items="${goodsInfos }" var="goodsInfo">
										<div class="adjust_list clearfix" goodsId="${goodsInfo.goodsId }">
											<em class="shoes_">商品</em>
											<div class="shoes_right">
												<span class="shoes_right_new">${goodsInfo.goodsName }</span>
											</div>
											<div class="shoes_right_">
												<span>商品价：${goodsInfo.goodsPrice }</span> <span>市场价：0</span> <span>重要：500g</span>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<span style="position: relative; top: 200px"> <img src="<%=basePath%>images/demo.png"></span>

							<div class="adjust_ul_">
								<p class="no_choose_">已选择</p>
								<div class="adjust_ul_list">
									
								</div>
							</div>
						</div>
						
						<div id="rxsp" class="adjust_goods" style="display: none;">
							<div class="adjust_goods_p" style="margin-top:10px">
								<span class="adjust_goods_span">调整货物</span><em>点击一个未选择商品，放入已选择吧！</em>
								<button onclick="save('rxsp')" >保存</button>
							</div>

							<div class="adjust_ul">
								<p class="no_choose">未选择</p>
								<div class="adjust_list_1">
									<c:forEach items="${goodsInfos }" var="goodsInfo">
										<div class="adjust_list clearfix" goodsId="${goodsInfo.goodsId }">
											<em class="shoes_">商品</em>
											<div class="shoes_right">
												<span class="shoes_right_new">${goodsInfo.goodsName }</span>
											</div>
											<div class="shoes_right_">
												<span>商品价：${goodsInfo.goodsPrice }</span> <span>市场价：0</span> <span>重要：500g</span>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>

							<span style="position: relative; top: 200px"> <img src="<%=basePath%>images/demo.png" style="width:24px"></span>

							<div class="adjust_ul_">
								<p class="no_choose_">已选择</p>
								<div class="adjust_ul_list">
									
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div class="mask" style="display: none;">
		   <div id="flashEditorOut" >
		   <span class="mask_close" style="position:absolute;right:-5px;top:-5px"><img onclick="xiuxiu.onClose();" src="<%=basePath %>images/seo_close.png"></span>
		        <div id="altContent2">
		            <h1>美图秀秀</h1>
		        </div>
		   </div>
	</div>
	
</body>
<script>
	var cssWidth = 375;
	var cssHeight = 230;
	var imageObject = null;
	function zccCallback(dataBase64){
		var key = "jobwisdom/project/" + new Date().getTime();
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
			       	imageObject.attr("src", imageUrl);
			       	var html = '<li><img style="width: 89px; height: 89px" src="'+imageUrl+'"></li>';
			       	/* jQuery(".add_pic.clearfix").append(jQuery(html));
			       	var key = data.msg.key;
			       	console.log(imageUrl); */
		       }
     	});
	}
</script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>
<script>
//复制

jQuery('.adjust_list').click(function() {
		if(jQuery(this).attr("class").indexOf("active")>0)return;
		var goodsId = jQuery(this).attr("goodsId");
		var html = '<div class="adjust_list clearfix" goodsId="'+goodsId+'"><span class="img"><img src="'+baseUrl+'images/seo_close.png"></span>'+
						'<em class="shoes_">鞋子</em>'+
						'<div class="shoes_right">'+
							'<span class="shoes_right_new">可丝莹565</span>'+
						'</div>'+
						'<div class="shoes_right_">'+
							'<span>商品价：79.00</span> <span>市场价：0</span> <span>重要：500g</span>'+
						'</div>'+
					'</div>';
		jQuery(this).parents(".adjust_goods").find(".adjust_ul_list").append(jQuery(html));
		jQuery(this).addClass('active');
	})

//删除
jQuery(function() {
	jQuery(document).on('click', '.img', function() {
		var goodsId = jQuery(this).parents(".adjust_list.clearfix").attr("goodsId");
		jQuery(this).parents(".adjust_goods").find(".adjust_ul").find(".adjust_list.clearfix.active[goodsId='"+goodsId+"']").removeClass("active");
		jQuery(this).parent('.adjust_list').remove();
	});
})
function exchange(id){
	jQuery(".adjust_goods_1").hide('500');
	jQuery(".adjust_goods").hide('500');
	jQuery("#"+id).show('1000');	
}
function save(id){
	var data = "";
	if(id!="adsense"){
		jQuery("#"+id)
		.find(".adjust_ul_")
		.find(".adjust_list.clearfix[goodsId]")
		.each(function(){
			data += ","+jQuery(this).attr("goodsId");
		});
		data = data.substring(1, data.length);
	}else {
		jQuery(".add_pic.clearfix").find("img")
			.each(function(){
				data += ","+jQuery(this).attr("src");
		});
		data = data.substring(1, data.length);
	}
	
	if(id == "xpsj")var str = {"xpsj":id, "data":data};
	if(id == "rxsp")var str = {"rxsp":id, "data":data};
	if(id == "adsense")var str = {"adsense":id, "data":data};
	jQuery.ajax({
		type : "POST",
		url : baseUrl + "memberCenter/store/shop/action",
		data : JSON.stringify(str),
		dataType : "json",
		contentType : "application/json",
		success : function(data) {
			dialog("设置成功");
		}
	});
}
jQuery('.cancelinput').click(function(){
    jQuery('.zzc').hide();
    jQuery('.photo-clip-rotateLayer').html('');
 })
/** 回显*/
var storeShop = eval('('+'${hasStoreShop}'+')');
jQuery.each(storeShop.newArrival.split(","),function(i, value){
	jQuery("#xpsj").find(".adjust_list.clearfix[goodsid="+value+"]").click();
})
jQuery.each(storeShop.bestSellers.split(","),function(i, value){
	jQuery("#rxsp").find(".adjust_list.clearfix[goodsid="+value+"]").click();
})
jQuery.each(storeShop.adsense.split(","),function(i, value){
	var html = '<li><img onclick="jQuery(\'.mask\').show();editPage(null);imageObject=jQuery(this);" style="width: 375px; " src="'+value+'"></li>';
   	jQuery(".add_pic.clearfix").append(jQuery(html));
})
</script>
</html>