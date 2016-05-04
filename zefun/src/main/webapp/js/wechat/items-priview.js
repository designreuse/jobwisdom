jQuery(document).ready(function(){
	jQuery("#baocun").on("click",function(){
		//表单提交，记得做校验
		 var ok = checkInput();
		 if(ok == false){
			 return;
		 }
  		 var data = serializeData();
  		 data = data + "&itemNum=" + itemNum;
		 jQuery.ajax({
			type : "post",
			url : baseUrl + "uploadnews",
			data : data,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					dialog(e.msg);
					window.location.href=baseUrl+"wechat/items/manage";
				}else{
					dialog("出错了,请稍后重试");
				}
				
		  }
	 });
	}); 
});

jQuery('#weixin-yulan-modal').on('hidden.bs.modal', function () {
	window.location.href=baseUrl+"wechat/items/manage";
});
var mediaId;
jQuery(document).ready(function(){
  jQuery("#baocunyulan").on("click",function(){
  	var ok = checkInput();
	 if(ok == false){
		 return;
	 }
  	var data = serializeData();
	data = data + "&itemNum=" + itemNum;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "uploadnews",
		data : data,
		dataType : "json",
		success : function(e){
			if(e.code == 0){
				mediaId = e.msg;
				jQuery("#weixin-yulan-modal").modal();
				jQuery("#prev").one("click",function(){
					var userName = jQuery("#userName").val();
					jQuery.ajax({
						type : "post",
						async : false,
						url : baseUrl + "wechat/item/prev",
						data : "mediaId="+mediaId+"&userName="+userName,
						dataType : "json",
						success : function(g){
							if(g.code == 0){
								dialog("预览信息已发送至您的手机");
								jQuery("#weixin-yulan-modal").modal('hide');
								window.location.href=baseUrl+"wechat/items/manage"; 
							}else{
								dialog(g.msg);
							}
						}
					});
				});
			}
			else {
				dialog(e.msg);
			}
		}
	});
  }); 
});
function setUrl(data){
	var array_element1 = data[0];
	var array_element2 = data[1];
	var array_element3 = data[2];
	var array_element4 = data[3];
	var array_element5 = data[4];
	var array_element6 = data[5];
	var array_element7 = data[6];
	var array_element8 = data[7];
    jQuery("#item_yl1").on("click",function(){
    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
		jQuery("#viewShow").hide();
		jQuery("#activity-name").text(array_element1.title);
		jQuery("#ylauthor").text(array_element1.author);
		jQuery("#js_content").html(array_element1.content);
		jQuery("#img-content").show();
    });
    jQuery("#item_yl2").on("click",function(){
    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
		jQuery("#viewShow").hide();
		jQuery("#activity-name").text(array_element2.title);
		jQuery("#ylauthor").text(array_element2.author);
		jQuery("#js_content").html(array_element2.content);
		jQuery("#img-content").show();
    });
    jQuery("#item_yl3").on("click",function(){
    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
		jQuery("#viewShow").hide();
		jQuery("#activity-name").text(array_element3.title);
		jQuery("#ylauthor").text(array_element3.author);
		jQuery("#js_content").html(array_element3.content);
		jQuery("#img-content").show();
    });
    jQuery("#item_yl4").on("click",function(){
    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
		jQuery("#viewShow").hide();
		jQuery("#activity-name").text(array_element4.title);
		jQuery("#ylauthor").text(array_element4.author);
		jQuery("#js_content").html(array_element4.content);
		jQuery("#img-content").show();
    });
    jQuery("#item_yl5").on("click",function(){
    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
		jQuery("#viewShow").hide();
		jQuery("#activity-name").text(array_element5.title);
		jQuery("#ylauthor").text(array_element5.author);
		jQuery("#js_content").html(array_element5.content);
		jQuery("#img-content").show();
    });
    jQuery("#item_yl6").on("click",function(){
    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
		jQuery("#viewShow").hide();
		jQuery("#activity-name").text(array_element6.title);
		jQuery("#ylauthor").text(array_element6.author);
		jQuery("#js_content").html(array_element6.content);
		jQuery("#img-content").show();
    });
    jQuery("#item_yl7").on("click",function(){
    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
		jQuery("#viewShow").hide();
		jQuery("#activity-name").text(array_element7.title);
		jQuery("#ylauthor").text(array_element7.author);
		jQuery("#js_content").html(array_element7.content);
		jQuery("#img-content").show();
    });
    jQuery("#item_yl8").on("click",function(){
    	jQuery(".rich_media_area_primary").parent(".mobile_preview_bd").attr("class","mobile_preview_bd-other-page");
		jQuery("#viewShow").hide();
		jQuery("#activity-name").text(array_element8.title);
		jQuery("#ylauthor").text(array_element8.author);
		jQuery("#js_content").html(array_element8.content);
		jQuery("#img-content").show();
    });
    //返回
    jQuery(".mobile_preview_hd").on("click",function(){
        /**将网页全屏*/
        jQuery(".mobile_preview_bd-other-page").attr("class","mobile_preview_bd");
        jQuery("#viewShow").show();
        jQuery("#img-content").hide();
    });
}
    
//表单验证    
function checkInput(){
	var inputTitle = jQuery("input[name='title']");
	var inputAuthor = jQuery("input[name='author']");
	var textarea = jQuery("textarea[name='Description']");
	var image = jQuery(".news-content").find("img");
	for (var i = 0; i < inputTitle.length; i++) {
		if (inputTitle.eq(i).val() == ""){
			dialog("请为第 "+(i+1)+" 个图文设置标题");
			return false;
		}
	}
	for (var i = 0; i < inputAuthor.length; i++) {
		if (inputAuthor.eq(i).val() == ""){
			dialog("请为第 "+(i+1)+" 个图文设置作者");
			return false;
		}
	}
	for (var i = 0; i < textarea.length; i++) {
		if (textarea.eq(i).val() == ""){
			dialog("请为第 "+(i+1)+" 个图文设置描述");
			return false;
		}
	}
	for (var i = 0; i < image.length; i++) {
		if (image.eq(i).attr("src") == ""){
			dialog("请为第 "+(i+1)+" 个图文设置封面图片");
			return false;
		}
	}
	for (var i = 0; i < jQuery(".uedit").length; i++) {
		var editId = jQuery(".uedit").eq(i).next("div").attr("id");
		var content = UE.getEditor(editId).getContent();
		if (content == ""){
			dialog("请为第 "+(i+1)+" 个图文设置正文内容");
			return false;
		}
	}
}

var dataList = new Array();
//封装请求数据
function serializeData(){
	//给个默认值
	var data = "data=data";
	for (var i = 0; i < jQuery("input[name='title']").length; i++) {
		if(i == jQuery("input[name='title']").length-1){
			data = data+"&title="+jQuery("input[name='title']").eq(i).val();
		}else{
			data = data+"&title="+jQuery("input[name='title']").eq(i).val()+"@!";
		}
	}
	for (var i = 0; i < jQuery("input[name='author']").length; i++) {
		if(i == jQuery("input[name='author']").length-1){
			data = data+"&author="+jQuery("input[name='author']").eq(i).val();
		}else{
			data = data+"&author="+jQuery("input[name='author']").eq(i).val()+"@!";;
		}
	}
	for (var i = 0; i < jQuery("input[name='imgUrl']").length; i++) {
		if(i == jQuery("input[name='imgUrl']").length-1){
			data = data+"&imgUrl="+jQuery("input[name='imgUrl']").eq(i).val();
		}else{
			data = data+"&imgUrl="+jQuery("input[name='imgUrl']").eq(i).val()+"@!";
		}
	}
	for (var i = 0; i < jQuery("textarea[name='Description']").length; i++) {
		if(i == jQuery("textarea[name='Description']").length-1){
			data = data+"&Description="+jQuery("textarea[name='Description']").eq(i).val();
		}else{
			data = data+"&Description="+jQuery("textarea[name='Description']").eq(i).val()+"@!";
		}
	}
	for (var i = 0; i < jQuery("input[name='content_source_url']").length; i++) {
		if(i == jQuery("input[name='content_source_url']").length-1){
			data = data+"&content_source_url="+jQuery("input[name='content_source_url']").eq(i).val();
		}else{
			data = data+"&content_source_url="+jQuery("input[name='content_source_url']").eq(i).val()+"@!";
		}
	}
	for (var i = 0; i < jQuery(".uedit").length; i++) {
		var editId = jQuery(".uedit").eq(i).next("div").attr("id");
		var content = UE.getEditor(editId).getContent();
		content = content.replace(/%/g, "%25");  
		content = content.replace(/\&/g, "%26");  
		content = content.replace(/\+/g, "%2B"); 
		data = data + "&content="+content+"@!";
	}
	data = data.substring(0, data.length-2);
	data = data + "&status="+"1";
	return data;
}

