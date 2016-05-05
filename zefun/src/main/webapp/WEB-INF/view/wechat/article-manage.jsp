<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
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
            <div id="tabs" class="ui-tabs ui-widget ui-widget-content ui-corner-all">
                <div id="tabs-1">
                <div class="weixin-title">
                         <span>新建图文信息</span>
                         <!-- <button class="fr muban btn" data-toggle="modal" data-target="#bianjimoban">图文编辑</button> -->
                  </div>
						<div class="news-area">
							<div class="multi edit news">
								<div class="news-content">
									<div class="news-item1 first-item news-current" id="news-item1">
										<div class="cover-news-item">
											<div class="news-img-wrap ">
                                                <div class="news-img">
                                                    	封面图片
                                                    <img style="width: 184px;height: 116px" src="" id="img-one" alt=""/>
                                                </div>
                                                <div id="t1" class="news-title">
                                                   	 标题
                                                </div>
                                            </div>
										</div>
										<div class="news-edit-mask">
											<a class="js-edit" href="javascript:void(0);"><span class="icon-pencil"></span></a>
										</div>
									</div>
									

									<a onclick="addArtile()" class="create_access_primary appmsg_add" id="js_add_appmsg" href="javascript:void(0);"> 
										<i class="icon20_common add_gray icon-plus">增加一条</i>
                                    </a>
								</div>
							</div>
							<!--news-content-->
						</div>
						<!--news-area-->
						
                              <form action="<%=basePath %>uploadnews" method="POST"  class="am-form" >
                              
								<div class="news-edit-area">
								<!-- 第一个item -->
									<div class="news-edit" id="item1">
										<div class="inner">
											<div class="edit-item" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
		                                        <span class="e-input-box "> 
		                                        	<input name="title" type="text" id="title1" class="e-input js_title js_counter" max-length="64" placeholder="请在这里输入标题"> 
		                                        	<em class="e-input-append frm_counter">0/64</em>
												</span>
		                                    </div>
											<div class="edit-item hide" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
		                                         <span class="e-input-box1 "> 
		                                         <input id="author1" name="author" value="${storeName }" type="text" class="e-input js_title js_counter" max-length="8" placeholder="作者">
						                         <em class="e-input-append frm_counter">0/16</em>
												 </span>
		                                    </div>
		                                    <div class="edit-item" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
		                                         <span class="e-input-box1 "> 
		                                         	<input name="content_source_url" type="text" class="e-input js_title js_counter"  max-length="8" placeholder="原文链接">
									             </span>
		                                    </div>
		                                    <div class="edit-item">
		                                        <label for="" class="e-label" style="cursor: auto;">封面&nbsp;&nbsp;<span style="color: #8d8da8">小图片建议尺寸：200像素x200像素</span></label>
		                                    </div>
		                                    <div class="upload-btn">
		                                        <!-- 七牛上传图片 -->
		                                        <div class="display-frame" id="container">
		                                            <input type="hidden" id="imgUrl1" name="imgUrl" value="">
		                                            <img id="showImg1" src="" style="width: 100px; height: 100px;display:none"/>
		                                        </div>
		                                        <button type="button" class="select-file" id="pickfiles">选择文件</button>
		                                        <button type="button" class="select-file from-picture" id="from-picture1">
		                                            	从图片库中选择
		                                        </button>
		                                    </div>
		                                    <div class="upload-img hide">
		                                        <img src="" alt=""/> <span class="can-click">删除</span>
		                                    </div>

											<!-- <div class="edit-item hide">
												<label for="" class="e-label">摘要（选填，该摘要只在发送图文消息为单条时显示）</label>
												<textarea name="digest" id="zhaiyao" cols="30" rows="3"></textarea>
											</div> -->
											
											<div class="edit-item">
												<label for="" class="e-label">描述</label><br>
												<textarea id="Description1" name="Description" cols="30" rows="3"></textarea>
											</div>
											
											<div class="uedit">
												<p>
													正文 <span>(自动保存：<span class="data">2015年8月5日
														20:59</span>)
													</span>
												</p>
											</div>
											
											<script id="editor1" type="text/plain" style="width:738px;height:400px;"></script>
										</div>
										<i class="arrow arrow_out" style="margin-top: 20px;"></i> 
										<i class="arrow arrow_in" style="margin-top: 20px;"></i>
									</div>
							  <!-- 第一个item -->	
							  
									</div>
									<input type="hidden" value="1" name="itemNum" id="itemNum">
								<!-- 第八个item -->	
								</form>
								<div class = "clearfix"></div>
									<!--微信确认按钮-->
									<!-- <div style="margin-left: 0; margin-right: 0; border-top: 1px solid #e7e7eb; -webkit-box-shadow: none; text-align: center; padding-top: 20px; padding-bottom: 50px; margin-top: 40px;"> -->
									<div class="wx-btn">
										<span id="baocun"style="min-width: 104px; padding: 0; background-color: #44b549;"><button class="btn">保存</button></span>
										<span style="min-width: 104px; padding: 0; background-color: #44b549;"><button id="baocunyulan" class="btn">保存并预览</button></span>
										<span style="min-width: 104px; padding: 0; background-color: #44b549;display: none"><button class="btn">保存并群发</button></span>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--mainwrapper-->
</div>	
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url1" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url2" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url3" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url4" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url5" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url6" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal7" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url7" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>
<!-- 选择图片模态框 -->
<!-- 选择图片模态框 -->
<div class="modal hide" id="photo-list-modal8" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
                    <div class="photo-content" >
                        <img src="${pictures.pictureQiniu }">
                        <div id="html5_19s8iuk7712sa1ri91hhegtv1ko53_container" class="moxie-shim moxie-shim-html5" style="position: absolute; top: 0px; left: 0px; width: 0px; height: 0px; overflow: hidden; z-index: 0;"><input id="html5_19s8iuk7712sa1ri91hhegtv1ko53" type="file" style="font-size: 999px; opacity: 0; position: absolute; top: 0px; left: 0px; width: 100%; height: 100%;" multiple="" accept="image/jpeg,image/png,image/bmp"></div></div>
                    <div class="appmsg_mask" style="display:none;position:absolute;top:0;left:0;width:100%;height:100%;background-color:#000;filter:alpha(opacity = 60);-moz-opacity:.6;-khtml-opacity:.6;opacity:.6;z-index:1"></div>
                    <i class="icon_card_selected" style="display:none;position:absolute;overflow:hidden;z-index:1;top:0;left:0;"><img  src="<%=basePath %>img/checkboxpic.png"/></i>
                </div>
            </c:forEach>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn" data-dismiss="modal">取消</button>
                <button type="button" class="btn " id="confirm-menu-url8" data-dismiss="modal">确认</button>
            </div>
        </div>
    </div>
</div>

<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage1" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage2" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage3" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage4" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image5" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage5" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image6" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage6" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image7" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage7" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>
<!-- 作为文本编辑器的上传图片按钮 -->
<div class="modal hide in" id="add-content-image8" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 480px;height: 320px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    	选择图片
                </h4>
            </div>
            
            <div id="modal-body" style="height:400px;line-height:300px;stext-align:center;">
            	<div id="contentImage8" style="width:200px;height:200px;line-height:200px;border:1px solid #ccc;margin:auto; text-align:center;margin-top:30px;">
            		<span class="iconfont icon-jiahao" style="font-size:50px;margin-left:-25px;"></span>
            	</div>
            </div>
            
        </div>
    </div>
</div>

<!--微信预览模态框-->
<div class="modal hide wexin-yulan" id="weixin-yulan-modal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content weixin-yulan-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">微信预览</h5>
            </div>
            <div class="modal-body">
                <ul class="yulan" style="padding:0">
                    <li><span>员工内部账号 :</span><input type="text" id="userName" style="width: 162px"/></li>
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

</body>

<script src="<%=basePath%>js/wechat/chose-picture.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/plupload.full.min.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/qiniu/qiniu.min.js"></script>
<%-- <script charset="utf-8" src="<%=basePath %>editor/kindeditor-min.js"></script>
<script charset="utf-8" src="<%=basePath %>editor/lang/zh_CN.js"></script> --%>
<script id="articItemsLeft" type="text/html">
<div id="news-item" class="news-item2 news-item editing">
    <img class="js_appmsg_thumb news-thumb news-img" src="">
    <i class="news-thumb default news-word">缩略图</i>
    <h4 class="news-title">
       <a href="javascript:void(0);" id="t"></a>
    </h4>
    <div class="news-edit-mask">
    <span class="iconfont icon-shanchu" onclick="deleteItems(this)"></span>
      <a class="icon18_common edit_gray js_edit" onclick="return false;" href="javascript:void(0);">编辑</a>
      <a class="icon18_common del_gray js_del" onclick="return false;" href="javascript:void(0);">删除</a>
    </div>
</div>
</script>

<script id="articItemsHtml" type="text/html">
<div class="news-edit" id="item">
<div class="inner">
	<div class="edit-item" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
        <span class="e-input-box "> 
        	<input name="title" type="text" id="title" class="e-input js_title js_counter" max-length="64" placeholder="请在这里输入标题"> 
        	<em class="e-input-append frm_counter">0/64</em>
		</span>
    </div>
	<div class="edit-item hide" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
         <span class="e-input-box1 "> 
         <input id="author" value="<c:out value='${storeName}'/>" name="author" type="text" class="e-input js_title js_counter" max-length="16" placeholder="作者">
         <em class="e-input-append frm_counter">0/16</em>
		 </span>
    </div>
    <div class="edit-item" style="border-bottom: 1px solid #e7e7eb; padding-bottom: 0.5rem;">
         <span class="e-input-box1 "> 
         	<input name="content_source_url" type="text" class="e-input js_title js_counter"  max-length="8" placeholder="原文链接">
         </span>
    </div>
    <div class="edit-item">
        <label for="" class="e-label" style="cursor: auto;">封面&nbsp;&nbsp;<span style="color: #8d8da8">小图片建议尺寸：200像素x200像素</span></label>
    </div>
    <div class="upload-btn">
        <!-- 七牛上传图片 -->
        <div class="display-frame" id="container">
            <input type="hidden" id="imgUrl" name="imgUrl" value="">
            <img id="showImg" src="" style="width: 100px; height: 100px;display:none"/>
        </div>
        <button type="button" class="select-file" id="pickfiles">选择文件</button>
        <button type="button" class="select-file from-picture" id="from-picture">
            	从图片库中选择
        </button>
    </div>
    <div class="upload-img hide">
        <img src="" alt=""/> <span class="can-click">删除</span>
    </div>

	<div class="edit-item">
		<label for="" class="e-label">描述</label><br>
		<textarea id="Description" name="Description" cols="30" rows="3"></textarea>
	</div>
	
	<div class="uedit">
		<p>
			正文 <span>(自动保存：<span class="data">2015年8月5日
				20:59</span>)
			</span>
		</p>
	</div>

</div>

</div>
</script>
<script>
		Array.prototype.remove=function(dx) 
		{ 
		    if(isNaN(dx)||dx>this.length){return false;} 
		    for(var i=0,n=0;i<this.length;i++) 
		    { 
		        if(this[i]!=this[dx]) 
		        { 
		            this[n++]=this[i] 
		        } 
		    } 
		    this.length-=1 
		} 
      //新增图文消息
      var itemNumArray = new Array(8,7,6,5,4,3,2);
      
      var itemNum = 1;
      //允许编辑器的id无限加
      var editNum = 1;
      function addArtile(){
    	  var id = itemNumArray[itemNumArray.length-1];
    	  itemNumArray.remove(itemNumArray.length-1);
    	  if(itemNum >= 8){
    		  return ;
    	  }
    	  itemNum = itemNum + 1;
    	  editNum ++;
    	  jQuery("#item"+(itemNum-1)).hide();
    	  // 右侧编辑图文
    	  var articItemsHtml = jQuery("#articItemsHtml").html();
    	  jQuery(".news-edit-area").append(articItemsHtml);
    	  jQuery("#item").find("#title").attr("id", "title"+id);
    	  jQuery("#item").find("#author").attr("id", "author"+id);
    	  jQuery("#item").find("#imgUrl").attr("id", "imgUrl"+id);
    	  jQuery("#item").find("#showImg").attr("id", "showImg"+id);
    	  jQuery("#item").find("#container").attr("id", "container"+id);
    	  jQuery("#item").find("#pickfiles").attr("id", "pickfiles"+id);
    	  jQuery("#item").find("#from-picture").attr("id", "from-picture"+id);
    	  var str = '<script id="editor'+editNum+'" type="text/plain" style="width:738px;height:400px;"><\/script>';
    	  jQuery("#item").attr("id", "item"+id);
    	  jQuery("#item"+id).find(".inner").append(str);
    	  
    	  if (itemNum == 2){
    		  str = '<i class="arrow arrow_out" style="margin-top: 135px;"></i><i class="arrow arrow_in" style="margin-top: 135px;"></i>';
  			  jQuery("#item"+id).append(jQuery(str));
    	  }
    	  else {
    		  var margin = ((itemNum-2)*90+135);
    		  str = '<i class="arrow arrow_out" style="margin-top: '+margin+'px;"></i><i class="arrow arrow_in" style="margin-top: '+margin+'px;"></i>';
			  jQuery("#item"+id).append(jQuery(str));
    	  }
    	  
    	  UE.getEditor('editor'+editNum, toolbars);
    	  // 左侧展示图文
    	  var articItemsLeft = jQuery("#articItemsLeft").html();
    	  jQuery(".news-content").children("a").before(articItemsLeft);
    	  jQuery("#news-item").find("#t").attr("id", "t"+id);
    	  jQuery("#news-item").attr("id", "news-item"+id);
    	  changeLeftItemsPrev();
    	  changeLeftItemsTitle();
    	  hideShowBlace();
    	  chosePicture();
    	  qiniuOther(pickfilesArray[id], containerArray[id], id);
    	  /**左侧显示缩略图信息*/
    	  jQuery("#news-item"+id).show();
    	  jQuery("#itemNum").val(itemNum);
    	  changeCrunteItem(id);
      }
</script>
<script type="text/javascript" src="<%=basePath%>js/wechat/items-priview.js"></script>
<script type="text/javascript">
var toolbars = {
		toolbars: [
		   		['fullscreen', 'source', '|', 'undo', 'redo', '|',
		           'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript','|', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
		           'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
		           'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
		           'directionalityltr', 'directionalityrtl', 'indent', '|',
		           'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase','preview']
		   	],maximumWords:3000,elementPathEnabled:false
		   };
var u1 = UE.getEditor('editor1', toolbars);
var u2, u3, u4, u5, u6, u7, u8 ;
var editArray = new Array("", "", u2, u3, u4, u5, u6, u7, u8);
var leftToRight = {"news-item2":"item2", "news-item3":"item3", "news-item3":"item3", "news-item4":"item4", "news-item5":"item5", "news-item6":"item6", "news-item7":"item7", "news-item7":"item7" };

var qiniu1 = new QiniuJsSDK();
var qiniu2 = new QiniuJsSDK();
var qiniu3 = new QiniuJsSDK();
var qiniu4 = new QiniuJsSDK();
var qiniu5 = new QiniuJsSDK();
var qiniu6 = new QiniuJsSDK();
var qiniu7 = new QiniuJsSDK();
var qiniu8 = new QiniuJsSDK();

var pickfilesArray = new Array("","","pickfiles2","pickfiles3","pickfiles4","pickfiles5","pickfiles6","pickfiles7","pickfiles8");
var containerArray = new Array("","","container2","container3","container4","container5","container6","container7","container8");

</script>

<script src="<%=basePath%>js/wechat/article-manage.js"></script>
<script type="text/javascript">
//删除图文
function deleteItems(obj){
	stopBubble(obj);
	var leftId = jQuery(obj).parents(".news-item").attr("id");
	jQuery("#"+leftToRight[leftId]).remove();
	jQuery(obj).parents(".news-item").remove();
	itemNum = itemNum -1;
	itemNumArray.push(Number(leftId.substr(9)));
	editArray[Number(leftId.substr(9))] = null;
}

//控制绿色选择框
function changeCrunteItem(itemNum){
	jQuery(".news-content").children("div").removeClass("news-current");
	jQuery("#news-item"+itemNum).addClass("news-current");
}

function qiniuOther(pickfilesId, containerId, itemNum){
    /** 第二个图文消息上传七牛*/
    var qiniuDemo = new QiniuJsSDK();
    qiniuDemo.uploader({
	    runtimes: 'html5,flash,html4',    //上传模式,依次退化
	    browse_button: pickfilesId,       //上传选择的点选按钮，**必需**
	    uptoken_url: baseUrl+'qiniu/uptoken',            //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
	    domain: picUrl,   //bucket 域名，下载资源时用到，**必需**
	    container: containerId,           //上传区域DOM ID，默认是browser_button的父元素，
	    filters : {mime_types : [{title : "Image files", extensions : "jpg,jpeg,png,bmp"}],max_file_size: '10m'},
	    flash_swf_url: baseUrl+'js/qiniu/Moxie.swf', //引入flash,相对路径
		max_retries : 3, //上传失败最大重试次数
		dragdrop : true, //开启可拖曳上传
		drop_element : containerId, //拖曳上传区域元素的ID，拖曳文件或文件夹后可触发上传
		chunk_size : '2mb', //分块上传时，每片的体积
		auto_start : true, //选择文件后自动上传，若关闭需要自己绑定事件触发上传,
		init : {
			'FilesAdded' : function(up, files) {
				console.log("FilesAdded invoked ...");
				var err = false;
				plupload.each(files, function(file) {
					if(!qiniuDemo.isImage(file.name)){
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
				//jQuery(document).find('body').append('<div class="modal-backdrop fade in" id="mask">loading ...</div>');
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
				jQuery("#showImg"+itemNum).attr("src", sourceLink).show();
				jQuery("#imgUrl"+itemNum).val(sourceLink);
				/** 缩略图处理*/
				jQuery("#news-item"+itemNum).children("img").attr("class","news-thumb default news-word");
				jQuery("#news-item"+itemNum).children("img").attr("src",sourceLink);
				jQuery("#news-item"+itemNum).children("i").remove();
				
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