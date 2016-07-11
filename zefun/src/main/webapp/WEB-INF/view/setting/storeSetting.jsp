<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/webchat.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.all.min.js"> </script>
	<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/lang/zh-cn/zh-cn.js"></script>
	
<style type="text/css">
.special-sever_content{width:200px;height:200px;border:1px solid #ccc;border-radius:10px;overflow:hidden;box-shadow:0 0 10px #ccc;float:left;margin:0 45px 20px 30px}
.special_sever_pic .serve_top img{width:200px;left:0!important;}
.special_sever_text p{padding-left:20px;margin-bottom:0px!important}
.special_sever_text{color:#7f7f7f}
.special_sever_pic span{position:absolute;top:8px;right:8px;display:inline-block;z-index:10}
.special_sever_pic{position:relative}
.special_sever_pic span img{left:0!important}
.special_sever{height:570px;overflow:overlay;padding:10px;border:1px solid #ccc}
.special_sever_text span{display:inline-block;margin-right:10px;}
.special_sever_text em{color:black}
.add_serve{    width: 130px;
	    height: 26px;
	    border-radius: 20px;
	    background: white;
	    text-align: center;
	    line-height: 26px;
	    margin-bottom: 20px;
	    box-shadow: 0px 6px 10px #ccc;
	    border: none;
		position:relative;
		left:30px;
		background:#7485a7;
		color:white
}
.add_serve:hover{background:#5e6b86;}
.add_pic li{margin-left:40px}
.edit-mingshi{
  width:570px
}
.edit-mingshi .designer-list{
    width: 268px;
}
</style>
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
    function ave(){
    	jQuery(".webchat_p").children("p").eq(0).click();
    	return false;
    }
</script>  
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right clearfix">
					<div class="webchat">
						<div class="webchat_p">
							<p style="border: none; text-align: center">添加店铺轮播</p>
							<p>
								门店介绍<em><img src="<%=basePath%>images/webchat_right.png"></em>
							</p>
							<p>
								作品展示<em><img src="<%=basePath%>images/webchat_right.png"></em>
							</p>
							<p>
								名师介绍<em><img src="<%=basePath%>images/webchat_right.png"></em>
							</p>
						</div>

					</div>

					<div class="webchat_div">
						<div class="webchat_div_">
							<ul class="add_pic clearfix" style="width:570px">
							    <c:if test="${!empty storeInfo.pictureArray }">
                                    <c:forEach items="${storeInfo.pictureArray}" var="picture">
	                                    <li style="margin-right: 0px"><img style="width: 220px;" onclick="zcc(this,'img')" src="<%=qiniuPath%>${ picture }">
	                                	<input type="hidden" name="carouselPicture" value="${ picture }">
	                                	</li>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty storeInfo.pictureArray }">
                                	<li style="margin-right: 0px"><img style="width: 220px;" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/click_add.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li style="margin-right: 0px"><img style="width: 220px;" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/click_add.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li style="margin-right: 0px"><img style="width: 220px;" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/click_add.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li style="margin-right: 0px"><img style="width: 220px;" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/click_add.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li style="margin-right: 0px"><img style="width: 220px;" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/click_add.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li style="margin-right: 0px"><img style="width: 220px;" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/click_add.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                </c:if>
							</ul>
							<button class="webchat_submit" onclick="saveCarousel()">提交</button>
 						</div>
						<div class="webchat_div_" style="display: none;">
							<p style="font-size:16px">门店介绍：</p>
							<span onclick="zcc(this,'editor1')" title="插入图片" >
								<img src="<%=basePath%>images/insert_img.png" style="position:relative;left:1px;top:1px">
							</span>
							<script id="editor1" type="text/plain" style="width:550px;height:400px;">
							${storeInfo.storeDesc }
							</script>
							<button class="webchat_submit" onclick="edit('#descriptionEditor', 1)">提交</button>
						</div>

						<div class="webchat_div_" style="display: none;">
						<div class="spe">
							<span style="font-size:16px;position:relative;top:-8px">作品展示：</span><button class="add_serve" onclick="addService(this)">添加</button>
								<div class="special_sever clearfix">
								<c:forEach items="${specialServices }" var="specialService">
									<div id="${specialService.sId}" class="special-sever_content" onclick="editSpe(${specialService.sId}, this)">
									     <div class="special_sever_pic">
										   <span onclick="deleteService(${specialService.sId}, this, event)"><img src="<%=basePath %>images/hand_close.png"></span>
										   <em class="serve_top"><img src="<%=qiniuPath%>${specialService.sImage }"></em>
										 </div>
									     <div class="special_sever_text">
										   <p><span>服务名称：</span><em>${specialService.sName }</em></p>
										   <p><span>服务项目：</span><em>${specialService.projectName }</em></p>
										   <p><span>造   型    师：</span><em>${specialService.employeeCode }  ${specialService.employeeName }</em></p>
										   <p><span>造型师：</span><em>${specialService.employeeCode }  ${specialService.employeeName }</em></p>
										 </div>
								    </div>
								</c:forEach>
							    </div>
							</div>
						    <div style="display: none">
						    	<div class="clearfix">
						    	
						    	<div style="float: right;margin-right:40px" >
						    	  输入特色名称: <input name="sName" type="text" style="box-shadow: 0 0 3px #ccc;width:160px;border-radius:8px!important;margin-left:5px"><br><br>
								选择一个项目: 
								<select data-placeholder="选择项目"  class="chzn-select input80" name="projectId" id="projectId" style="width:160px;border-radius:8px!important">
									<c:forEach items="${projectInfos }" var="projectInfo">
										<option value="${projectInfo.projectId }">${projectInfo.projectName }</option>
									</c:forEach>
		                        </select><br><br>
		                                                       选择一个员工: 
		                        <select data-placeholder="选择员工"  class="chzn-select input80" name="emp" id="emp" style="width:160px;border-radius:8px!important">
									<c:forEach items="${storeEmployeeList }" var="storeEmployee">
										<option value="${storeEmployee.employeeCode }">${storeEmployee.name }</option>
									</c:forEach>
		                        </select>
		                        
		                       </div> 
		                       
		                        	<div style="position:relative;float:left">
		                        	   <ul>
		                        	<li >
		                        		<img style="width: 200px;height: 123px" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/set_img.png">
                                        <input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
		                        		<p style="color:#b0b0b0;text-align: center;">上传logo图片</p>
                                    </li>
                                   </ul> 
                                 </div>
                               </div>    
		                        <span class="insert_img" onclick="zcc(this,'editor2')" title="插入图片" >
									<img src="<%=basePath%>images/insert_img.png" >
								</span>
								<script id="editor2" type="text/plain" style="width:550px;height:400px;">

								</script>
								<button class="webchat_submit" onclick="edit('#characteristicEditor', 2)">提交</button>
						    </div>
						</div>

						<div class="webchat_div_" style="display: block;">

							<div class="shop-edit-area edit-mingshi active">
								<div class="shop-edit">
									<div class="inner">
										<div class="search-mingshi">
											<div class="designer-list">
												<div class="designer-item-title">
													<div class="project-list-head">
														<input type="search" placeholder="搜索" class="search-input">
														<button type="button" class="btn search-button" id="search-member">搜索</button>
													</div>
												</div>
												<ul class="employeeAll">
												<c:forEach items="${storeEmployeeList }" var="employee">
	                                                 <li class="designer-item-content" employeeId="${employee.employeeId }">
	                                                    <img src="<%=picPath%>${employee.headImage}?imageView2/1/w/116/h/116"/>
	                                                    <div class="info">
	                                                        <div class="fs30 font-666">${employee.employeeCode } ${employee.name }</div>
	                                                        <div class="fs30 font-666"> ${employee.levelName }</div>
	                                                        <div><span class="normoal-word">已服务<span class="n-blue">${employee.serviceCount }</span>人</span></div>
	                                                    </div>
	                                                </li>
	                                             </c:forEach>
												</ul>
											</div>
										</div>
										<div class="yixuanze">
											<div class="designer-list">
												<div class="designer-item-title">
													<span class="font-size-14 font-666">已选择</span>
												</div>
												<ul class="employeeSelected">
                                             	<c:forEach items="${showEmployeeList }" var="employee">
                                             	<li class="designer-item-content" employeeid="${employee.employeeId }" style="background: white;">
                                            		<span class="img"><img src="<%=basePath %>images/seo_close.png"></span>
                                                    <img src="<%=picPath%>${employee.headImage}?imageView2/1/w/116/h/116">
                                                    <div class="info">
                                                        <div class="fs30 font-666">${employee.employeeCode } ${employee.name }</div>
                                                        <div class="fs30 font-666"> ${employee.levelName }</div>
                                                        <div><span class="normoal-word">已服务<span class="n-blue">${employee.serviceCount }</span>人</span></div>
                                                    </div>
	                                             </li>
                                             </c:forEach>
                                        </ul>
											</div>
										</div>
									</div>
								</div>
								<div class="btn tijiao" onclick="saveEmployee()">提交</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="zzc">
	      <div class="photo_cut">
		   <div id="clipArea"></div>
           <input type="file" id="file" style="position:absolute;width: 100px;height: 40px;left: 212px;bottom:8px;opacity:0;cursor:pointer;filter:alpha(opacity=0);">
		   <button id="clipBtn" style="position:absolute;width: 100px;height: 40px;left: 346px;bottom:8px;opacity:0;cursor:pointer;filter:alpha(opacity=0);">截取</button>
		   <div class="button_panel"> 
		   <button class="selectpic">选择图片</button>
		   <button class="sureinput">确定上传</button>
		   <button class="cancelinput">取消</button>
		  </div>
         </div>
   </div>
	<div class="mask" style="display: none;">
		   <div id="flashEditorOut" style="position:relative">
		     <span class="mask_close" style="position:absolute;right:-5px;top:-5px"><img onclick="xiuxiu.onClose();" src="<%=basePath %>images/seo_close.png"></span>
			        <div id="altContent2">
			            <h1>美图秀秀</h1>
			        </div>
				</div>
	</div>
</body>
<script>var specialServicesJs = ${specialServicesJs};</script>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/setting/storeSetting.js"></script>
<script type="text/javascript">

var toolbars = {
		toolbars: [
		   		['fullscreen', 'source', '|', 'undo', 'redo', '|',
		           'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript','|', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
		           'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
		           'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
		           'directionalityltr', 'directionalityrtl', 'indent', '|',
		           'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase','preview']
		   	],maximumWords:3000,elementPathEnabled:false,imageScaleEnabled:false,wordCount:false,autoHeightEnabled:false
		   };
var u1 = UE.getEditor('editor1', toolbars);
var u2 = UE.getEditor('editor2', toolbars);

/**tab页面切换*/
jQuery(function(){
   jQuery('.webchat_div_:gt(0)').hide();
   jQuery('.webchat_p p').click(function(){
	     jQuery('.webchat_div_').eq(jQuery(this).index()).show().siblings().hide();
	     jQuery('.webchat_div_').eq(jQuery(this).index()).children("div").eq(0).show();
	     jQuery('.webchat_div_').eq(jQuery(this).index()).children("div").eq(1).hide();
   });
})
/**复制人员*/
jQuery(function(){
  jQuery('.employeeAll .designer-item-content').click(function(){
	  if(jQuery(this).css("background-color") == "rgb(255, 255, 255)")return;
	  jQuery(this).css('background','white');
	  var htm=jQuery(this).clone();
	  var img='<span class="img"><img src="'+baseUrl+'images/seo_close.png"></span>';
	  jQuery(".employeeSelected").append(htm);
	  htm.prepend(img);
	});
})
/**取消特色人员*/
jQuery(function(){
  jQuery(document).on('click','.img',function(){
	  var employeeId = jQuery(this).parent('.designer-item-content').attr("employeeid");
	  jQuery(this).parent('.designer-item-content').remove();
	  jQuery(".employeeAll li[employeeId="+employeeId+"]").css('background','');
	})
})

var cssWidth = 375;
var cssHeight = 230;

var imgObject = null;
var imgType = null;
function zcc(opt,type){
	imgType = type;
	if(type=="img"){
		imgObject = jQuery(opt);
	}else {
		imgObject = jQuery(opt).next();
	}
	jQuery(".mask").show();
	editPage(null);
}

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
		       	var key = data.msg.key;
		       	if(imgType == "img"){
		       		imgObject.attr("src", imageUrl);
		       		jQuery(imgObject).next().val(key);
		       	}else{
		       		UE.getEditor(imgType).execCommand('insertHtml', '<img style="margin-top: 0px; width: 100%; padding: 0px; border-color: rgb(30, 155, 232); color: inherit; height: 100%;" data-width="100%" border="0" vspace="0" src="'+qiniuUrl+key+'">');
		       	}
	       }
 	});
}

jQuery(function(){
	 jQuery('.cancelinput').click(function(){
	    jQuery('.zzc').hide();
	    jQuery('.photo-clip-rotateLayer').html('');
	 })
})

function addService(opt){
	jQuery(opt).parent().hide("800").siblings("div").show("800");
	sId = null;
}


function editSpe(sIds, opt){
	sId = sIds;
	for (var i = 0; i < specialServicesJs.length; i++) {
		if(specialServicesJs[i].sId == sId){
			var sName = specialServicesJs[i].sName;
			var projectId = specialServicesJs[i].projectId;
			var emp = specialServicesJs[i].employeeCode;
			var sImage = specialServicesJs[i].sImage;
			var content = specialServicesJs[i].content;
			jQuery("input[name='sName']").val(sName);
			jQuery("select[name='projectId']").val(projectId);
			jQuery("select[name='emp']").val(emp);
			jQuery("input[name='carouselPicture']").last().val(sImage);
			jQuery("input[name='carouselPicture']").last().prev().attr("src", qiniuUrl+sImage);
			UE.getEditor("editor2").setContent(content);
		}
	}
	console.log(sId);
	jQuery(opt).parents(".spe").hide("800").siblings("div").show("800");
}

function deleteService(sIds, opt, event){
	var father = jQuery(opt);
	event.stopPropagation();
	jQuery.ajax({
		type: "POST",
		url: baseUrl+"storeinfo/action/storeSetting/special/deleted",
	       data: "sId="+sIds,
	       dataType: "json",
	       async:true,  
	       success: function(data) {
	    	   if(data.code == 0){
	    		   father.parent().parent().remove();
	    		   console.log(jQuery(opt).parents('.special-sever_content').html());
	    	   }
	    	   else {
	    		   dialog("请刷新尝试");
	    	   }
	       }
 	});
}
</script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>js/base/base64Helper.js"></script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>
</html>