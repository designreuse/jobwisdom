KindEditor.plugin('picture', function(K) {
        var editor = this, name = 'picture';

        var dialog = {};
        editor.clickToolbar(name, function() {
        	 dialog = K.dialog({
                width : 500,
                title : '测试窗口',
                body : '<div style="margin:10px;width: 80px;"><div class="article-file-box"><button style="width: 90px" class="article2-btn">浏览..</button> <input id="f" type="file" style="width: 90px" class="article-file" /> </div></div>',
                closeBtn : {
                        name : '关闭',
                        click : function(e) {
                                dialog.remove();
                        }
                }
           });
         jQuery("#f").change(function(e){
           console.log("sdf");
           preview(this,dialog,editor);
          });
        });
});

//图片上传预览    IE是用了滤镜。
function preview(file,dialog,editor)
{
  var MAXWIDTH  = 260;
  var MAXHEIGHT = 180;
  if (file.files && file.files[0])
  {
    var reader = new FileReader();
    reader.onload = function(evt){
      var html = "<img style=\"width:180px;height:150px\" src=\""+evt.target.result+"\">";
      editor.insertHtml(html).focus();
      dialog.remove();
    }
    reader.readAsDataURL(file.files[0]);
  }
}
