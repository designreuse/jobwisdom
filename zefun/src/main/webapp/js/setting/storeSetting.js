var sourceObj, jQueryimage, cropBoxData, canvasData;
var options = {
	viewMode: 1,
	aspectRatio: 1/1,
	autoCropArea: 1,
	minContainerWidth: 1000,
    minContainerHeight: 580,
    minCropBoxWidth: 580,
    minCropBoxHeight: 580,
    width:580,
    height:580,
    responsive:false,
    cropBoxMovable: false,
    cropBoxResizable: false,
    dragMode: 'move'
};

var jQueryimage = jQuery('.crop-container > img');
jQueryimage.cropper(options);

/**
 * 显示图片处理器
 * @param obj	图片父元素
 * @param type	处理类型（1、轮播图片，2、门店介绍图片，3、特色服务图片，4、店铺LOGO）
 */
function showImgEditor(obj, type){
  sourceObj = jQuery(obj);	
  var otherUrl = sourceObj.find("img").attr("src");
  if(isEmpty(otherUrl)){
	  otherUrl = picUrl + "zefun/images/pic_none.gif";
  }
  jQuery("#cropbox").attr("src", otherUrl);
  jQueryimage.cropper('reset').cropper('replace', otherUrl);
  jQuery(".cropper-view-box img").attr("src", otherUrl);
  jQuery("#jietu").modal(); 
  
  /*启用截图*/
  var jQueryinputImage = jQuery('.inputfile');
  var URL = window.URL || window.webkitURL;
  var blobURL;

  if (URL) {
    jQueryinputImage.change(function () {
      var files = this.files;
      var file;

      if (!jQueryimage.data('cropper')) {
        return;
      }

      if (files && files.length) {
        file = files[0];

        if (/^image\/\w+$/.test(file.type)) {	
            blobURL = URL.createObjectURL(file);
            jQueryimage.one('built.cropper', function () {
              URL.revokeObjectURL(blobURL);
            }).cropper('reset').cropper('replace', blobURL);
            jQueryinputImage.val('');
          } else {
            dialog('请选择一张图片');
          }
      }
    });
  } else {
    jQueryinputImage.prop('disabled', true).parent().addClass('disabled');
  }
  
  jQuery(".btn.save").one("click", function () {
	  var result = jQueryimage.cropper('getCroppedCanvas', options);
	  var base64Str = result.toDataURL("image/png");
	  var key = "zefun/store/" + new Date().getTime();
	  
	  var data = {"stringBase64" : base64Str, "key" : key};
	  jQuery("#jietu").modal('hide');
	  jQuery.ajax({
		type: "POST",
		url: baseUrl + "qiniu/base64",
	       data: JSON.stringify(data),
	       contentType: "application/json",
	       dataType: "json",
	       async: true,  
	       success: function(data) {
	    	   var key = data.msg.key;
    		   if (type == 1) {
    			   sourceObj.parent().before('<div class="img-wrap">' 
    					+ '<span class="iconfont icon-guanbi"></span>' 
    				  	+ '<img src="' + base64Str + '">' 
    				  	+ '<input type="hidden" name="carouselPicture" value="' + key + '"></div>');
    		   }
    		   else if (type == 2) {
		    	   insertHtmlAtCaret('#descriptionEditor', '<img style="max-width: 100%;max-height: 100%;" src="' + picUrl + key + '"/>');
	    	   }
	    	   else if (type == 3) {
	    		   insertHtmlAtCaret('#characteristicEditor', '<img style="max-width: 100%;max-height: 100%;" src="' + picUrl + key + '"/>');
	    	   }
	    	   else if (type == 4) {
	    		   sourceObj.attr("src", base64Str);
	    		   jQuery("#storeLogo").val(key);
	    	   }
	       }
     });
  });
}

jQuery('#jietu').on('hidden.bs.modal', function () {
      cropBoxData = jQueryimage.cropper('getCropBoxData');
      canvasData = jQueryimage.cropper('getCanvasData');
      jQueryimage.cropper('clear').cropper('reset');
      jQuery(".btn.save").unbind("click");
      console.log("clear...");
});

//===================图片处理结束=============================================================================================================================

function saveSelection() {
    if (window.getSelection) {
        sel = window.getSelection();
        if (sel.getRangeAt && sel.rangeCount) {
            return sel.getRangeAt(0);
        }
    } else if (document.selection && document.selection.createRange) {
        return document.selection.createRange();
    }
    return null;
}

var r = null;

function setEndOfContenteditable(editor){
    var contentEditableElement = jQuery(editor)[0];
    var range = r,selection;
    if(document.createRange)//Firefox, Chrome, Opera, Safari, IE 9+
    {
        range.selectNodeContents(contentEditableElement);//Select the entire contents of the element with the range
        range.collapse(false);//collapse the range to the end point. false means collapse to end rather than the start
    }
    else if(document.selection)//IE 8 and lower
    {
        range.moveToElementText(contentEditableElement);//Select the entire contents of the element with the range
        range.collapse(false);//collapse the range to the end point. false means collapse to end rather than the start
        range.select();//Select the range (make it the visible selection
    }
}

function insertHtmlAtCaret(editor, html) {
    var range = r;
    if (window.getSelection) {
        if (sel.getRangeAt && sel.rangeCount) {
            range.deleteContents();
            var el = document.createElement("div");
            el.innerHTML = html;
            var frag = document.createDocumentFragment(), node, lastNode;
            while ( (node = el.firstChild) ) {
                lastNode = frag.appendChild(node);
            }
            range.insertNode(frag);
            if (lastNode) {
                range = range.cloneRange();
                range.setStartAfter(lastNode);
                range.collapse(true);
                sel.removeAllRanges();
                sel.addRange(range);
            }
        }
    } else if (document.selection && document.selection.type != "Control") {
        range.pasteHTML(html);
    }
    setEndOfContenteditable(editor);
    r = saveSelection();
}

jQuery(document).ready(function(){
	jQuery("#descriptionEditor, #characteristicEditor").on('keyup mouseup', function(e){
	    r = saveSelection();
	});

	jQuery("#descriptionEditor, #characteristicEditor").on('paste', function( evt ) {
	    var ohml = jQuery(this).html();
	    evt.preventDefault();
	    var c = "";
	    try {
	    	c = evt.originalEvent.clipboardData.getData('Text');
	    } catch(e) {
		    c = window.clipboardData.getData('Text');
	    }
	    
	    var vs = c.split('\n');
	    for (var i = 0; i < vs.length; i++) {
	    	var vv = vs[i];
	    	insertHtmlAtCaret(this, '<div>' + vv + '</div>');
		}

//	    var vs = c.split('\r\n');
//	    for(var i in vs) {
//	    	var vv = vs[i];
//	    	var vvs = vv.split('\n');
//	    	for(var j in vvs) {
//	    		var vvv = vvs[j];
//	    		if(!vvv) {
//	    			continue;
//	    		}
//	    		insertHtmlAtCaret(this, '<div>' + vvv + '</div>');
//	    	}
//	    }
	    return false;
	});
});

//===================内容编辑区域处理结束=============================================================================================================================
var sId = null;
function edit(editor, type){
	var data = null;
	if(type == 1){
		var content = UE.getEditor("editor1").getContent();
		content = content.replace(/%/g, "%25");  
		content = content.replace(/\&/g, "%26");  
		content = content.replace(/\+/g, "%2B"); 
		data = "storeDesc=" + content;
		jQuery.ajax({
			type: "POST",
			url: baseUrl + "storeinfo/action/storeSetting",
	       	data: data,
	       	success: function(data) {
	    	   if (data.code != 0) {
	    		   dialog(data.msg);
	    		   return;
	    	   }
	    	   dialog("保存成功");
	       	}
		});
		
	}else {
		var content = UE.getEditor("editor2").getContent();
		var sName = jQuery("input[name='sName']").val();
		var projectId = jQuery("select[name='projectId']").val();
		var projectName = jQuery("#projectId option:selected").text();
		var employeeCode = jQuery("select[name='emp']").val();
		var employeeName = jQuery("#emp option:selected").text();
		var sImage = jQuery("input[name='carouselPicture']").last().val();
		
		data = {"sId":sId, "sName":sName, "projectId":projectId, "projectName":projectName, "employeeCode":employeeCode, "employeeName":employeeName, "sImage":sImage, "content":content};
		
		jQuery.ajax({
			type: "POST",
			url: baseUrl + "storeinfo/action/storeSetting/special",
			data : JSON.stringify(data),
			dataType : "json",
			contentType : "application/json",
	       	success: function(data) {
//	       		if (data.code != 0) {
//		    		   dialog(data.msg);
//		    		   return;
//	    	   }
	    	   
	    	   if(sId == null){
	    		   var html = '<div id="'+data.msg.sId+'" class="special-sever_content" onclick="editSpe('+data.msg.sId+', this)">'+
										     '<div class="special_sever_pic">'+
									   '<span onclick="deleteService('+data.msg.sId+', this, event)"><img src="'+baseUrl+'images/hand_close.png"></span>'+
									   '<em class="serve_top"><img src="'+qiniuUrl+data.msg.sImage+'"></em>'+
									 '</div>'+
								     '<div class="special_sever_text">'+
									   '<p><span>服务名称：</span><em>'+data.msg.sName+'</em></p>'+
									   '<p><span>服务项目：</span><em>'+data.msg.projectName+'</em></p>'+
									   '<p><span>适用员工：</span><em>'+data.msg.employeeCode+'  '+data.msg.employeeName+'</em></p>'+
									 '</div>'+
							    '</div>';
	    		   jQuery(".special_sever.clearfix").append(jQuery(html));
	    	   }
	    	   for (var i = 0; i < specialServicesJs.length; i++) {
	    			if(specialServicesJs[i].sId == sId){
	    					specialServicesJs[i].sName = data.msg.sName;
	    					specialServicesJs[i].projectId = data.msg.projectId;
	    					specialServicesJs[i].employeeCode = data.msg.employeeCode;
	    					specialServicesJs[i].sImage = data.msg.sImage;
	    					specialServicesJs[i].content = data.msg.content;
	    					jQuery(".special-sever_content[id='"+sId+"']").find("img").attr("src", qiniuUrl+data.msg.sImage);
	    					jQuery(".special-sever_content[id='"+sId+"']").find(".special_sever_text").empty();
	    					var html = '<p><span>服务名称：</span><em>'+data.msg.sName+'</em></p>'+
		    						   '<p><span>服务项目：</span><em>'+data.msg.projectName+'</em></p>'+
		    						   '<p><span>适用员工：</span><em>'+data.msg.employeeCode+'  '+data.msg.employeeName+'</em></p>';
	    					jQuery(".special-sever_content[id='"+sId+"']").find(".special_sever_text").append(jQuery(html));
	    			}
	    		}
	    	   jQuery(".webchat_div_").eq(2).children("div").eq(0).show("800");
	    	   jQuery(".webchat_div_").eq(2).children("div").eq(1).hide("800");
	       	}
		});
	}
}

jQuery(".rating").rating({showCaption : false, step : 0.5});

jQuery(function(){
	//将已经选择的员工打上标记
	var ea = jQuery(".employeeSelected li");
    for (var i = 0; i < ea.length; i++) {
        //jQuery(".employeeAll li[employeeId='" + jQuery(ea[i]).attr("employeeId") + "']").addClass("p-selected");
    	jQuery(".employeeAll li[employeeId='" + jQuery(ea[i]).attr("employeeId") + "']").css('background','white');
    }
});

//设置项切换
jQuery(".setting_option").click(function(){
	var name = jQuery(this).attr("name");
    jQuery(".shop-edit-area").addClass("hide").removeClass("active");
    jQuery("." + name).removeClass("hide").addClass("active");
    jQuery(".setting_option").removeClass("active");
    jQuery(this).addClass("active");
    
    if (name == 'edit-description') {
    	jQuery('#descriptionEditor').focus();
    	r = saveSelection();
        window.scrollTo(0, 0);
    } 
    else if (name == 'edit-characteristic') {
    	jQuery('#characteristicEditor').focus();
    	r = saveSelection();
        window.scrollTo(0, 0);
    }
});

//选择名师的操作控制
/*jQuery(".employeeAll li").on("click",function(){
    var employeeId = jQuery(this).attr("employeeId");
    var tmp = jQuery(".employeeSelected [employeeId='" + employeeId + "']");
    if (!isEmpty(tmp) && tmp.length > 0) {
        tmp[0].remove();
        jQuery(this).removeClass("p-selected");
    } else {
        var se = jQuery(this).prop('outerHTML');
        jQuery(".employeeSelected").prepend(se);
        jQuery(".employeeSelected [employeeId='" + employeeId + "']").prepend('<div class="shanchu-icon"><span class="iconfont icon-shanchujilu"></span></div>');
        jQuery(this).addClass("p-selected");
    }
});*/

//移除选择的名师
/*jQuery(".yixuanze").delegate(".shanchu-icon", "click", function(e){
    var se = jQuery(this).parent();
    jQuery("[employeeId='" + se.attr("employeeId") + "']").removeClass("p-selected");
    se.remove();
});*/

//移除轮播图片
jQuery(".edit-img").delegate(".icon-guanbi", "click", function(e){
    jQuery(this).parent().remove();
});

//提交名师信息
function saveEmployee(){
    var ea = jQuery(".employeeSelected li");
    if(ea == null || ea.length == 0){
        dialog("请选择需要展示的名师");
        return;
    }
    var teacherIntroduction = "";
    for (var i = 0; i < ea.length; i++) {
    	teacherIntroduction += jQuery(ea[i]).attr("employeeId") + ",";
	}
    teacherIntroduction = teacherIntroduction.substring(0, teacherIntroduction.length - 1);
    var data = "teacherIntroduction=" + teacherIntroduction;
    submit(data, "保存成功，已更新您的名师信息");
}

//提交店铺信息
function saveStoreInfo(){
	var storeLogo = jQuery("#storeLogo").val();
	var storeName = jQuery("#storeName").val();
	var storeTel = jQuery("#storeTel").val();
	var storeAddress = jQuery("#storeAddress").val();
	var storeLinkname = jQuery("#storeLinkname").val();
	var storeLinkphone = jQuery("#storeLinkphone").val();
	var latitude = jQuery("#lat").text();
	var longitude = jQuery("#lng").text();
	if (isEmpty(storeLogo)) {
        dialog("请上传您的店铺Logo");
        return;
    }
	if (isEmpty(storeName)) {
		dialog("请填写您的店铺名称");
		return;
	}
	/*if (isEmpty(storeAddress)) {
		dialog("请填写您的店铺地址");
		return;
	} else {
		var city = jQuery("#city").text();
		//alert(city);
		if (storeAddress.indexOf(city) == -1) {
			dialog("修改地址只能在您店铺所在城市进行修改");
			return;
		}
	}*/
	var city = jQuery("#city").text();
	var street = jQuery("#searchtext").val();
	if (isEmpty(city)) {
		dialog("请选择城市");
		return;
	}
	if (isEmpty(street)) {
		dialog("请选择街道");
		return;
	}
	var storeAddress = city + street;
	if (typeof storeAddress === 'undefined') {
		dialog("请选择地址");
		return;
	}
	if (isEmpty(storeTel)) {
        dialog("请填写您的店铺电话");
        return;
    }
	if (isEmpty(storeLinkname)) {
        dialog("请填写您的店铺负责人姓名");
        return;
    }
	if (isEmpty(storeLinkphone)) {
        dialog("请填写您的店铺负责人电话");
        return;
    }
	if (isEmpty(latitude) || isEmpty(longitude)) {
		dialog("店铺坐标获取失败，请刷新页面重新选取位置");
		return;
	}
	var data = "storeLogo=" + storeLogo + "&storeName=" + storeName + "&storeTel=" + storeTel + "&storeAddress=" + storeAddress
		+ "&storeLinkname=" + storeLinkname + "&storeLinkphone=" + storeLinkphone + "&latitude=" + latitude + "&longitude=" + longitude;
	submit(data, "保存成功，已更新您的店铺信息");
}

//提交店铺轮播图片
function saveCarousel(){
	var carouselList = jQuery("[name='carouselPicture']");
	if(carouselList == null || carouselList.length == 0){
        dialog("请至少上传一张店铺照片");
        return;
    }
	carousel = "";
	for (var i = 0; i < carouselList.length; i++) {
		carousel += jQuery(carouselList[i]).val() + ",";
	}
	carousel = carousel.substring(0, carousel.length - 1);
	var data = "carouselPicture=" + carousel;
	submit(data, "保存成功，已更新您的店铺信息");
}

//提交门店介绍
function saveDescription(){
	var content = editorDescription.getContent();
	content = content.replace(/%/g, "%25");  
	content = content.replace(/\&/g, "%26");  
	content = content.replace(/\+/g, "%2B");
	console.log(content);
	if(content == null || content.length == 0){
        dialog("请为您的门店介绍添加内容");
        return;
    }
	var data = "storeDesc=" + content;
	submit(data, "保存成功，已更新您的店铺信息");
}

//提交特色服务
function saveCharacteristic(){
	var content = editorCharacteristic.getContent();
	content = content.replace(/%/g, "%25");  
	content = content.replace(/\&/g, "%26");  
	content = content.replace(/\+/g, "%2B");
	if(content == null || content.length == 0){
        dialog("请为您门店的特色服务添加内容");
        return;
    }
	var data = "characteristic=" + content;
	submit(data, "保存成功，已更新您的店铺信息");
}

//数据提交
function submit(data, msg){
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/storeSetting",
        data: data,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(e.msg);
                return;
            }
        	dialog(msg);
        }
    });
}