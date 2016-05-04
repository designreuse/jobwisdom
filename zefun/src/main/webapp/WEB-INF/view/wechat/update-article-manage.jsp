<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
<style>
	.edui-for-preview {
		top:0px;
	}
</style>
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/articleImagesButton.js"></script>
			<!-- 页面内容开始 -->

<div class="maincontent" style="overflow-y: auto">
    <div class="contentinner">
        <div class="widgetcontent">
            <div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all " style="padding-bottom:10px;">
                <div id="tabs-1">
                    <div class="weixin-title">
                         <span>编辑营销图文</span>
                         <a href="<%=basePath %>wechat/items/manage" style="margin-right: 10px;float:right;cursor: pointer">返回图文方案库</a>
                    </div>
							<div class="news-area">
								<div class="multi edit news">
									<div class="news-content">
									<!--news-item1-->
									<c:forEach items="${items }"  var="auto" varStatus="status">
									    <c:if test="${status.first == 'true' }">
										    <div class="news-item1 first-item news-current" id="news-item1">
												<div class="cover-news-item">
												<div class="news-img-wrap ">
	                                                <div class="news-img">
	                                                   	<img  src="${auto.qiniuImg }" id="img-one" />
	                                                </div>
	                                                <div id="t1" class="news-title">
	                                                   	 ${auto.title }
	                                                </div>
	                                            </div>
												</div>
												<div class="news-edit-mask">
													<a class="js-edit" href="javascript:void(0);"><span class="icon-pencil"></span></a>
												</div>
											</div>
									    </c:if>
									    <c:if test="${status.first == 'false' }">
										    <div id="news-item${status.index+1 }" class="news-item2 news-item editing">
											    <img class="news-thumb default news-word" src="${auto.qiniuImg }">
												<h4 class="news-title">
													<a  href="javascript:void(0);"  id="t${status.index+1 }">${auto.title }</a>
												</h4>
											</div>
									    </c:if>
									</c:forEach>
									
									</div>
								</div>
								<!--news-content-->
							</div>
								<!--news-area-->
								
                              <form action="<%=basePath %>wechat/items/update" method="POST"  class="am-form" >
                              
								<div class="news-edit-area">
								<c:forEach items="${items }"  var="auto" varStatus="status">
								    <c:if test="${status.first == 'true' }">
								    <!-- 第一个item -->
									<div class="news-edit" id="item1">
										<div class="inner">
										
											<div class="edit-item" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
		                                        <span class="e-input-box "> 
			                                         <input type="hidden" name="index" value="${status.index }">
												     <input type="hidden" name="media_id" value="${auto.mediaId }">
												     <input type="hidden" name="replyId" value="${auto.replyId }">
												     <input type="hidden" name="content_source_url" value="${auto.contentSourceUrl }">
													 <input name="title" type="text" id="title1"  value="${auto.title }" placeholder="请在这里输入标题" class="e-input js_title js_counter" max-length="64"> 
													 <em class="e-input-append frm_counter">${fn:length(auto.title)}/64</em> 
												</span>
	                                    	</div>
	                                    	
											<div class="edit-item hide" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
		                                         <span class="e-input-box1 "> 
		                                         <input id="author1" name="author" type="text" class="e-input js_title js_counter"
		                                            max-length="8" placeholder="作者" value="${auto.author }">
													 <em class="e-input-append frm_counter">${fn:length(auto.author)}/8</em>
												 </span>
		                                    </div>
		                                    
											<div class="edit-item">
		                                        <label for="" class="e-label" style="cursor: auto;">封面&nbsp;&nbsp;<span style="color: #8d8da8">小图片建议尺寸：200像素x200像素</span></label>
		                                    </div>
		                                    
		                                    <div class="upload-btn">
		                                        <!-- 七牛上传图片 -->
		                                        <div class="display-frame" id="container">
		                                            <input type="hidden" id="thumb1" class="thumb" name="thumb_media_id"  value="${auto.thumbMediaId }">
													    <input type="hidden" id="qiniuImg1" name="qiniuImg" value="${auto.qiniuImg }">
													    <input type="hidden" id="picUrl1" name="picUrl" value="${auto.picUrl }">
														<img id="showImg1" src="${auto.qiniuImg }" style="width: 100px; height: 100px;" />
		                                        </div>
		                                        <button type="button" class="select-file" id="pickfiles">选择文件</button>
		                                        <button type="button" class="select-file from-picture" id="from-picture1">
		                                           	 从图片库中选择
		                                        </button>
		                                    </div>
		                                    
											
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>
											
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea name="digest" cols="30" rows="3" >${auto.description }</textarea>
											</div>
											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											<!-- edit -->
											<script id="editor1" type="text/plain" style="width:734px;height:400px;">${auto.content }</script>
										</div>
										<i class="arrow arrow_out" style="margin-top: 20px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 20px;"></i>
									</div>
							  		<!-- 第一个item -->
								    </c:if>
								    <c:if test="${status.first == 'false' }">
								    <!-- 第二个item -->	
								<div class="news-edit hide" id="item${status.index+1 }">
										<div class="inner">
										
											<div class="edit-item" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
		                                        <span class="e-input-box "> 
			                                        <input type="hidden" name="index" value="${status.index }">
												    <input type="hidden" name="media_id" value="${auto.mediaId }">
												    <input type="hidden" name="replyId" value="${auto.replyId }">
												    <input type="hidden" name="content_source_url" value="${auto.contentSourceUrl }">
												    <input name="title" type="text" placeholder="请在这里输入标题" id="title${status.index+1 }"  value="${auto.title }"  class="e-input js_title js_counter" max-length="64"> 
													<em class="e-input-append frm_counter">${fn:length(auto.title)}/64</em>
												</span>
	                                    	</div>
	                                    	
	                                    	<div class="edit-item hide" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
		                                         <span class="e-input-box1 "> 
		                                         <input id="author${status.index+1 }" placeholder="作者" name="author" type="text"  value="${auto.author }"  class="e-input js_title js_counter" max-length="64">
													 <em class="e-input-append frm_counter">${fn:length(auto.author)}/8</em>
												 </span>
		                                    </div>
		                                    
											<div class="edit-item">
												<label for="" class="e-label" style="cursor: auto;">封面<span>小图片建议尺寸：200像素x200像素</span></label>
											</div>
											
											<div class="upload-btn">
		                                        <!-- 七牛上传图片 -->
		                                        <div class="display-frame" id="container${status.index+1 }">
		                                            <input type="hidden" id="thumb${status.index+1 }" class="thumb" name="thumb_media_id"  value="${auto.thumbMediaId }">
												    <input type="hidden" id="qiniuImg${status.index+1 }" name="qiniuImg" value="${auto.qiniuImg }">
												    <input type="hidden" id="picUrl${status.index+1 }" name="picUrl" value="${auto.picUrl }">
													<img id="showImg${status.index+1 }" src="${auto.qiniuImg }" style="width: 100px; height: 100px;" />
		                                        </div>
		                                        <button type="button" class="select-file" id="pickfiles${status.index+1 }">选择文件</button>
		                                        <button type="button" class="select-file from-picture" id="from-picture${status.index+1 }">
		                                           	 从图片库中选择
		                                        </button>
		                                    </div>
		                                    
											<div class="upload-img hide">
												<img src="" alt="" /> <span class="can-click">删除</span>
											</div>
											<p></p>
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea name="digest"  cols="30" rows="3" >${auto.description }</textarea>
											</div>

											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											<!-- edit -->
											<script id="editor${status.index+1 }" type="text/plain" style="width:738px;height:400px;">${auto.content }</script>
										</div>
										<c:choose>
										<c:when test="${status.index == 1 }">
										<i class="arrow arrow_out" style="margin-top: 135px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 135px;"></i>
										</c:when>
										<c:otherwise>
										<i class="arrow arrow_out" value="${status.index}" style="margin-top: ${(status.index-1)*90+135 }px;"></i> 
										<i class="arrow arrow_in" style="margin-top: ${(status.index-1)*90+135 }px;"></i>
										</c:otherwise>
										</c:choose>
										
									</div>
								<!-- 第二个item -->	
								    </c:if>
								</c:forEach>
							    <input type="hidden" value="1" name="itemNum" id="itemNum">
								</div>
								</form>
								<div class = "clearfix"></div>
								<!--微信确认按钮-->
								<div class='wx-btn' style="margin-left: 0; margin-right: 0; -webkit-box-shadow: none; text-align: center; padding-top: 20px; padding-bottom: 50px; ">
									<span id="baocun" style="min-width: 104px; padding: 0; background-color: #44b549;"><button class="btn">确认修改</button></span>
									<span style="min-width: 104px; padding: 0; background-color: #44b549;display: none"><button class="btn">预览</button></span>
									<span style="min-width: 104px; padding: 0; background-color: #44b549;" id="baocunyulan"><button class="btn">保存并预览</button></span>
								</div>
							</div>
								<!--news-edit-area-->
								
							<!--tabs-1-->
						</div>
					</div>
				</div>
			</div>
			<!-- 页面内容结束 -->
		</div>
		</div>
		<!--RIGHT PANEL结束 -->
	</div>
	<c:forEach items="${items }"  var="itemPicture" varStatus="statusPicture">
	<!-- 选择图片模态框 -->
	<div class="modal hide" id="photo-list-modal${statusPicture.index+1 }" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content select-wordimg-modal" style="width: 600px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                    <span aria-hidden="true">&times;</span>
	                </button>
	                <h4 class="modal-title" id="myModalLabel">
	                    	选择图片
	                </h4>
	            </div>
	            <div class="imgword-list" style="overflow: auto;width: 600px;height: 400px;">
	            <c:forEach items="${pictureLibraries }" var="pictures"> 
	            	<div class="photo-item"  style="position: relative;">
	                    <div class="photo-content" id="container" >
	                        <input type="hidden" name="pictureWechat" value="${pictures.pictureWechat }" >
	                        <input type="hidden" name="thumbMediaId" value="${pictures.thumbMediaId }" >
	                        <img src="${pictures.pictureQiniu }">
	                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
	                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
	                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
	                </div>
	            </c:forEach>
	            </div>
	
	            <div class="modal-footer">
	                <button type="button" class="btn" data-dismiss="modal">取消</button>
	                <button type="button" class="btn " id="confirm-menu-url${statusPicture.index+1 }" data-dismiss="modal">确认</button>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- 选择图片模态框 -->	
	</c:forEach>

<!--微信预览模态框-->
<div class="modal hide" id="weixin-yulan-modal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content weixin-yulan-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">微信预览</h5>
            </div>
            <div class="modal-body">
                <ul class="yulan" style="padding:0">
                    <li><span>员工内部账号 :   </span><input type="text" id="userName" style="width: 162px"/></li>
                </ul>
            </div><!--modal body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" id="prev">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>
	
<%@include file="qiniuChoseImage.jsp" %>
</body>
</html>

<script src="<%=basePath%>js/wechat/update-article-manage.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/plupload.full.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
<script charset="utf-8" src="<%=basePath %>editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath %>editor/lang/zh_CN.js"></script>
<script type="text/javascript">
var mediaId = '${mediaId}';
jQuery(".news-item").hover(
    function(){
       jQuery(this).children(".news-edit-mask").show();
    },
    function(){
        jQuery(this).children(".news-edit-mask").hide();
    }
);
</script>
<script>
var toolbars = {
	toolbars: [
	   		['fullscreen', 'source', '|', 'undo', 'redo', '|','link',
	           'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript','|', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
	           'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
	           'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
	           'directionalityltr', 'directionalityrtl', 'indent', '|',
	           'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase','preview']
	   	]
   };
var u1 = UE.getEditor('editor1', toolbars);
var u2 = UE.getEditor('editor2', toolbars);
var u3 = UE.getEditor('editor3', toolbars);
var u4 = UE.getEditor('editor4', toolbars);
var u5 = UE.getEditor('editor5', toolbars);
var u6 = UE.getEditor('editor6', toolbars);
var u7 = UE.getEditor('editor7', toolbars);
var u8 = UE.getEditor('editor8', toolbars);

var qiniu1 = new QiniuJsSDK();
var qiniu2 = new QiniuJsSDK();
var qiniu3 = new QiniuJsSDK();
var qiniu4 = new QiniuJsSDK();
var qiniu5 = new QiniuJsSDK();
var qiniu6 = new QiniuJsSDK();
var qiniu7 = new QiniuJsSDK();
var qiniu8 = new QiniuJsSDK();

//控制绿色选择框
function changeCrunteItem(itemNum){
	jQuery(".news-content").children("div").removeClass("news-current");
	jQuery("#news-item"+itemNum).addClass("news-current");
}
//字数限制
jQuery("input").keyup(function(){
	var text = jQuery(this).val();
	var str = jQuery(this).next("em").text();
	var maxLength = Number(str.substring(str.indexOf("/")+1, str.length));
	if (maxLength == 0){
		return;
	}
	if (maxLength<text.length){
		jQuery(this).val(text.substring(0, maxLength));
		jQuery(this).next("em").css("color", "red");
	}else{
		jQuery(this).next("em").css("color", "");
		jQuery(this).next("em").text(text.length+str.substring(str.indexOf("/"), str.length));
	}
});
</script>
</html>