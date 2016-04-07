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
 * @param type	处理类型（1、商品图片，2、描述图片，3、商品主图）
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
	  var key = "zefun/ubox/goods/" + new Date().getTime();
	  var data = {"stringBase64" : base64Str, "key" : key};
	  jQuery("#jietu").modal('hide');
	  jQuery.ajax({
		type: "POST",
		url: baseUrl + "qiniu/base64",
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
	    	   var key = data.msg.key;
	    	   if (type == 1) {
	    		   sourceObj.find("img").attr("src", base64Str);
	    		   sourceObj.find("input[name='imgs']").val(key);
	    	   }
	    	   else if (type == 2) {
		    	   insertHtmlAtCaret('<img style="max-width: 100%;max-height: 100%;" src="' + picUrl + key + '"/>');
	    	   }
	    	   else if (type == 3) {
	    		   sourceObj.find("img").attr("src", base64Str);
	    		   sourceObj.find("input[name='goodsPicture']").val(key);
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
jQuery(function(){
    jQuery('#editor_id').focus();
    r = saveSelection();
    window.scrollTo(0, 0);
});

function setEndOfContenteditable(){
    var contentEditableElement = jQuery('#editor_id')[0];
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

function insertHtmlAtCaret(html) {
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
    setEndOfContenteditable();
    r = saveSelection();
}

jQuery(document).ready(function(){
	jQuery(document).delegate('#editor_id', 'mouseup', function(e){
	    if(e.target.id != 'editor_id') {
	        return false;
	    }
	    r = saveSelection();
	});
	
	jQuery(document).delegate('#editor_id', 'keyup', function(e){
	    if(e.target.id != 'editor_id') {
	        return false;
	    }
	    r = saveSelection();
	});

	jQuery('#editor_id').on('paste', function( evt ) {
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
	    	insertHtmlAtCaret('<div>' + vv + '</div>');
		}
	    return false;
	});
});

//===================内容编辑区域处理结束=============================================================================================================================
    
function edit(id) {
	var data = {};
        
//	var product_name = jQuery('#product_name').val();
//	if(!product_name || !jQuery.trim(product_name)) {
//	    jQuery('#product_name').focus();
//	    dialog("商品名称不能为空");
//	    return;
//	}
//	if(product_name.length > 24) {
//	    jQuery('#product_name').focus();
//	    dialog("商品名称长度不能超过24个字符");
//	    return;
//	}
//	data['goodsName'] = jQuery.trim(product_name);

	var img_ns = jQuery('#uploaded_imgs').find('input[name="imgs"]');
	var imgs = new Array();
	for (var i = 0; i < img_ns.length; i++) {
		var img = jQuery(img_ns[i]).val();
		if (!isEmpty(img)) {
			imgs.push(img);
		}
	}
	if(imgs.length == 0) {
	    dialog("最少上传一张商品图片");
	    return;
	}
	data['imgs'] = imgs;
	
	data['goodsPicture'] = jQuery('input[name="goodsPicture"]').val();

	var contents = new Array();
	var it_contents = function(cs){
	    cs.each(function(){
	        if(this.nodeName.toLowerCase() == '#text') {
	            if(this.data && jQuery.trim(this.data)) {
	                contents.push(this.data + "\001" + '1');
	            }
	        } else if(this.nodeName.toLowerCase() == 'div') {
	            it_contents(jQuery(this).contents());
	        } else if(this.nodeName.toLowerCase() == 'img') {
	            var s = jQuery(this).attr('src').replace(picUrl, '');
	            contents.push(s + "\001" + '2');
	        }
	    });
	};
	it_contents(jQuery('#editor_id').contents());
	
	if(!contents.length) {
	    jQuery('#editor_id').focus();
	    dialog("商品描述不能为空");
	    return;
	}
	data['contents'] = contents;
	data['goodsId'] = id;
	
	//提交数据
	jQuery.ajax({
		type: "POST",
		url: baseUrl + "ubox/goods/action/edit",
		contentType: "application/json",
        data: JSON.stringify(data),
       	success: function(data) {
    	   if (data.code != 0) {
    		   dialog(data.msg);
    		   return;
    	   }
    	   dialog("保存成功，为您返回商品列表页面");
    	   jQuery(window).unbind('beforeunload');
    	   setTimeout(function(){
    		   window.location.href = baseUrl + "ubox/goods/view/list"
    	   }, 1000);
       	}
	});
};