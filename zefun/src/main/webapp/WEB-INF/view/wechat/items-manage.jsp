<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/seo.css" type="text/css" />
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right">
					<div class="right_head clearfix">
						<ul class="right_ul">

							<li class="active">
								<div></div> <span>我的图文库</span>
							</li>
							<li class="">
								<div></div> <span>我道图文库</span>
							</li>
							<li class="">
								<div></div> <span>我的图片库</span>
							</li>

						</ul>
					</div>

					<div class="seo_content" style="display: block;">

						<div class="seo_search">
							<input type="text" placeholder="标题"><span><img src="<%=basePath%>images/seach.png"></span>
							<a href="<%=basePath %>artic/manager"><button class="seo_push">新建</button></a>
						</div>

						<div class="pic_count">图文消息（共${fn:length(slefItems)}个）</div>
						
						<div class="clearfix seo_first">
						<c:forEach items="${slefItems}" var="slefItem">
							<div class="column_small_first " action-type="showdesc">
								<div class="column_img_container">
									<div class="seo_close" onclick="deleteItems('${slefItem.mediaId}',this)">
										<img src="<%=basePath%>images/seo_close.png">
									</div>
									<div class="head_pic">
										<img src="${slefItem.qiniuImg}">
									</div>
									<div class="long_hair">
										<c:choose>
								          <c:when test="${fn:length(slefItem.title) > 16}">
								              ${fn:substring(slefItem.title, 0, 16)}......
								          </c:when>
								          <c:otherwise>
								         	${slefItem.title}
								          </c:otherwise>
									    </c:choose>
									</div>
									<p class="seo_time">发布时间：${slefItem.createTime }</p>
									<p></p>
									<ul class="clearfix shop_number">
										<a href="<%=basePath%>wechat/send/update/item?mediaId=${slefItem.mediaId}"><li><img src="<%=basePath%>images/seo_write.png"></li></a>
										<a href="<%=basePath%>wechat/send/items?mediaId=${slefItem.mediaId}"><li><img src="<%=basePath%>images/seo_down.png"></li></a>
									</ul>
								</div>
							</div>
						</c:forEach>
						</div>
					</div>

					<div class="seo_content" style="display: none;">

						<div class="seo_search">
							<input type="text" placeholder="标题"><span><img src="<%=basePath%>images/seach.png"></span>
						</div>

						<div class="pic_count">图文消息（共${fn:length(items)}个）</div>
						
							<div class="clearfix seo_first">
							<c:forEach items="${items}" var="items">
								<div class="column_small_first " action-type="showdesc">
									<div class="column_img_container">
										<div class="head_pic">
											<img src="${items.qiniuImg}">
										</div>
										<div class="long_hair">
										<c:choose>
									          <c:when test="${fn:length(items.title) > 16}">
									              ${fn:substring(items.title, 0, 16)}......
									          </c:when>
									         <c:otherwise>
									         	${items.title}
									          </c:otherwise>
									    </c:choose>
										</div>
										<p class="seo_time">发布时间：${items.createTime }</p>
										<p></p>
										<ul class="clearfix shop_number_">
											<li onclick="downloadItems('${items.mediaId}',this)"><img src="<%=basePath%>images/seo_1.png"></li>
											<li onclick="pariseItems('${items.mediaId}',this)"><img src="<%=basePath%>images/seo_2.png"></li>
											<li><img src="<%=basePath%>images/seo_3.png"></li>
										</ul>
	
									</div>
								</div>
							</c:forEach>
						</div>
					</div>


					<div class="seo_content" style="display: none;" id="container">
						<div class="seo_search" style="margin-bottom: 20px">
							<button class="seo_push" id="pickfiles" style="position: relative; left: -50px">上传图片</button>
						</div>


						<div class="seo_pic_content">
							<ul class="clearfix seo_ul" style="height: 470px; overflow-y: scroll">
							<c:forEach items="${pictures }" var="picture">
								<li pictureId="${picture.pictureId }">
									<em class="seo_pic"><img src="${picture.pictureQiniu }" style="width: 195.69px;height: 195"></em> 
									<span onclick="deleteImage(this)" class="seo_close"><img src="<%=basePath%>images/seo_close.png"></span>
								</li>
							</c:forEach>
							</ul>

						</div>
					</div>


				</div>

			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	jQuery(function(){
	   jQuery('.seo_content:gt(0)').hide();
	   jQuery('.right_ul li').click(function(){
	      jQuery(this).addClass('active').siblings().removeClass('active')
	      jQuery('.seo_content').eq(jQuery(this).index()).show().siblings('.seo_content').hide();
		});
	})   
	   
	var qiniu = new QiniuJsSDK();
	qiniu.uploader({
		runtimes : 'html5,flash,html4', //上传模式,依次退化
		browse_button : 'pickfiles', //上传选择的点选按钮，**必需**
		uptoken_url : baseUrl + 'qiniu/uptoken', //Ajax请求upToken的Url，**强烈建议设置**（服务端提供）
		domain : picUrl, //bucket 域名，下载资源时用到，**必需**
		container : 'container', //上传区域DOM ID，默认是browser_button的父元素，
		filters : {
			mime_types : [ {
				title : "Image files",
				extensions : "jpg,jpeg,png,bmp"
			} ],
			max_file_size : '10m'
		},
		flash_swf_url : baseUrl + 'js/qiniu/Moxie.swf', //引入flash,相对路径
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
					if (!qiniu.isImage(file.name)) {
						err = true;
						return false;
					}
				});
				if (err) {
					dialog("只能上传图片文件");
					up.removeFile(files[0]);
					up.stop();
					jQuery('#mask').remove();
				}
			},
			'BeforeUpload' : function(up, file) {
			},
			'UploadProgress' : function(up, file) {
			},
			'FileUploaded' : function(up, file, info) {
				var domain = up.getOption('domain');
				var res = eval('(' + info + ')');
				var sourceLink = domain + res.key;
				jQuery('#mask').remove();
				console.log(sourceLink);
				//将七牛的地址传递个微信,然后将该信息全部录入数据库中
				jQuery.ajax({
					type : "post",
					url : baseUrl + "wechat/update/img",
					data : "imgUrl=" + sourceLink,
					dataType : "json",
					success : function(e) {
						if (e.code == 0) {
							initNodes(e.msg);
						}
					}
				});
			},
			'Error' : function(up, err, errTip) {
				jQuery('#mask').remove();
			},
			'UploadComplete' : function() {
			},
			'Key' : function(up, file) {
				var key = "jobwisdom/wechat/" + new Date().getTime();
				return key;
			}
		}
	});

	/**新增元素,将新增的图片显示在页面上*/
	function initNodes(data) {
		
		var html =  '<li pictureId="'+data.pictureId+'">'+
						'<em class="seo_pic"><img src="'+data.pictureQiniu+'" style="width: 195.69px;height: 195"></em>'+
						'<span onclick="deleteImage(this)" class="seo_close"><img src="'+baseUrl+'images/seo_close.png"></span>'+
					'</li>';
		jQuery(".clearfix.seo_ul").append(jQuery(html));
		
	}
	/**绑定删除图片事件*/
	function deleteImage(opt){
		var pictureId = jQuery(opt).parent().attr("pictureId");
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/delete/img",
			data : "pictureId=" + pictureId,
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					jQuery(opt).parent().hide("show");
				}
			}
		});
	}
	/*下载选中图文*/
	function downloadItems(mediaId, doc) {
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/items/copy/zhifang",
			data : "mediaId=" + mediaId,
			dataType : "json",
			success : function(e) {
				dialog(e.msg);
				window.location.reload();
			}
		});
	}
	/*点赞图文*/
	function pariseItems(mediaId, obj) {
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/praise/store/wechat",
			data : "mediaId=" + mediaId,
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					var str = parseInt(jQuery(obj).find("span").text());
					str = str + 1;
					jQuery(obj).find("span").text(str);
				} else {
					dialog(e.msg);
				}
			}
		});
	}
	/*删除图文消息,该js只能写在该页面中*/
	function deleteItems(mediaId, doc) {
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/items/delete",
			data : "mediaId=" + mediaId,
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					jQuery(doc).parents(".column_small_first").hide("show");
				}
			}
		});
	}
	/*删除智放图文消息*/
	function deleteZfItems(mediaId, doc) {
		jQuery.ajax({
			type : "post",
			url : baseUrl + "wechat/items/delete",
			data : "mediaId=" + mediaId + "&storeId=0",
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					jQuery(doc).parents(".imgword-item").hide("show");
				}
			}
		});
	}
	jQuery("#uploadItems").on("click", function() {
		jQuery.ajax({
			type : "get",
			url : baseUrl + "wechat/upload/items",
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					dialog("更新成功");
				} else {
					dialog("更新失败,请重试")
				}
			}
		});
	});
</script>
</html>