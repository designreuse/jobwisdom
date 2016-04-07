var qiniu = new QiniuJsSDK();
qiniu.uploader({
    runtimes: 'html5,flash,html4',    //上传模式,依次退化
    browse_button: 'pickfiles',       //上传选择的点选按钮，**必需**
    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
    container: 'container',           //上传区域DOM ID，默认是browser_button的父元素，
    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
	max_retries : 3, //上传失败最大重试次数
	dragdrop : true, //开启可拖曳上传
	drop_element : 'container', //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
	chunk_size : '2mb', //分块上传时，每片的体积
	auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
	init : {
		'FilesAdded' : function(up, files) {
			console.log("FilesAdded invoked ...");
			var err = false;
			plupload.each(files, function(file) {
				if(!qiniu.isImage(file.name)){
					err = true;
					return false;
				}
			});
			if(err) {
				dialog("只能上传图片文件");
				up.removeFile(files[0]);
				up.stop();
				jQuery('#mask').remove();
			}
		},
		'BeforeUpload' : function(up, file) {
			console.log("BeforeUpload invoked ...");
		},
		'UploadProgress' : function(up, file) {
			console.log("UploadProgress invoked ...");
		},
		'FileUploaded' : function(up, file, info) {
			console.log("FileUploaded invoked ...");
			var domain = up.getOption('domain');
			var res = eval('(' + info + ')');
			var sourceLink = domain + res.key;
			jQuery('#mask').remove();
			console.log(sourceLink);
			jQuery("#showImg1").attr("src", sourceLink).show();
			jQuery("#imgUrl1").val(sourceLink);
			jQuery("#img-one").parent().html(jQuery('<img style="width: 184px;height: 116px" src="'+sourceLink+'" id="img-one" alt=""/>'));
		},
		'Error' : function(up, err, errTip) {
			dialog(errTip);
			jQuery('#mask').remove();
		},
		'UploadComplete' : function() {
			console.log("UploadComplete invoked ...");
			dialog("UploadComplete");
		},
		'Key' : function(up, file) {
			console.log("Key invoked ...");
			var key = "zefun/wechat/" + new Date().getTime();
			return key;
		}
	}
});

//设置标题
function changeLeftItemsTitle(){
	  jQuery("#title1").blur(function(e){
  	     jQuery("#t1").text(jQuery(this).val());
	 });
	  jQuery("#title2").blur(function(e){
    	 jQuery("#t2").text(jQuery(this).val());
	 });
	  jQuery("#title3").blur(function(e){
        jQuery("#t3").text(jQuery(this).val());
  	 }); 
	  jQuery("#title4").blur(function(e){
        jQuery("#t4").text(jQuery(this).val());
  	 }); 
	  jQuery("#title5").blur(function(e){
        jQuery("#t5").text(jQuery(this).val());
  	 }); 
	  jQuery("#title6").blur(function(e){
        jQuery("#t6").text(jQuery(this).val());
  	 }); 
	  jQuery("#title7").blur(function(e){
        jQuery("#t7").text(jQuery(this).val());
  	 });
	  jQuery("#title8").blur(function(e){
        jQuery("#t8").text(jQuery(this).val());
  	 });  
}
//点击左侧item进行展示右侧编辑
function changeLeftItemsPrev(){
	  jQuery("#news-item1").on("click",function(){
  	  hideItems();
  	  changeCrunteItem(1);
  	  jQuery("#item1").show();
  	  });
  	  jQuery("#news-item2").on("click",function(){
      	  hideItems();
      	changeCrunteItem(2);
      	  jQuery("#item2").show();
       });
  	  jQuery("#news-item3").on("click",function(){
      	  hideItems();
      	changeCrunteItem(3);
      	  jQuery("#item3").show();
       });
  	  jQuery("#news-item4").on("click",function(){
      	  hideItems();
      	changeCrunteItem(4);
      	  jQuery("#item4").show();
       });
  	  jQuery("#news-item5").on("click",function(){
      	  hideItems();
      	changeCrunteItem(5);
      	  jQuery("#item5").show();
       });
  	  jQuery("#news-item6").on("click",function(){
      	  hideItems();
      	changeCrunteItem(6);
      	  jQuery("#item6").show();
       });
  	  jQuery("#news-item7").on("click",function(){
      	  hideItems();
      	changeCrunteItem(7);
      	  jQuery("#item7").show();
       });
  	  jQuery("#news-item8").on("click",function(){
      	  hideItems();
      	changeCrunteItem(8);
      	  jQuery("#item8").show();
       });
}
//显示删除按钮
function hideShowBlace(){
	jQuery(".news-item").hover(
	    function(){
	       jQuery(this).children(".news-edit-mask").show();
	    },
	    function(){
	        jQuery(this).children(".news-edit-mask").hide();
	    }
	);
}
//隐藏所有的图文消息
function hideItems(){
	  for (var i = 0; i <= 8; i++) {
		  jQuery("#item"+i).hide();
	}
}
