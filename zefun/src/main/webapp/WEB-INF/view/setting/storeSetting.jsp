<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/webchat.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
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
								特色服务<em><img src="<%=basePath%>images/webchat_right.png"></em>
							</p>
							<p>
								名师介绍<em><img src="<%=basePath%>images/webchat_right.png"></em>
							</p>
						</div>

					</div>

					<div class="webchat_div">
						<div class="webchat_div_">
							<ul class="add_pic clearfix">
							    <c:if test="${!empty storeInfo.pictureArray }">
                                    <c:forEach items="${storeInfo.pictureArray}" var="picture">
                                    		<li><img style="width: 89px;height: 89px" onclick="zcc(this,'img')" src="<%=qiniuPath %>${ picture }">
	                                        <input type="hidden" name="carouselPicture" value="${ picture }">
	                                        </li>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${empty storeInfo.pictureArray }">
                                	<li><img style="width: 89px;height: 89px" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/set_img.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li><img style="width: 89px;height: 89px" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/set_img.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li><img style="width: 89px;height: 89px" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/set_img.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li><img style="width: 89px;height: 89px" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/set_img.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li><img style="width: 89px;height: 89px" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/set_img.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                	<li><img style="width: 89px;height: 89px" onclick="zcc(this,'img')" src="<%=qiniuPath%>system/profile/set_img.png">
                                	<input type="hidden" name="carouselPicture" value="system/profile/set_img.png">
                                	</li>
                                </c:if>
							</ul>
							<button class="webchat_submit" onclick="saveCarousel()">提交</button>
 						</div>
						<div class="webchat_div_" style="display: none;">
							<p>门店介绍：</p>
							<button class="insert_img" onclick="zcc(this,'edit')">
								<img src="<%=basePath%>images/webchat_add.png">插入图片
							</button>
							<div contenteditable="true" id="descriptionEditor" name="content" class="webchat_area">
								<c:if test="${!empty storeInfo.storeDescArray }">
				                    <c:forEach var="content" items="${storeInfo.storeDescArray }">
										<c:if test="${content.type == '1' }">
											<div>${content.text }</div>
										</c:if>
										<c:if test="${content.type == '2' }">
											<img style="width: 560px;height: 560px" src="<%=picPath %>${content.text}">
										</c:if>
									</c:forEach>
								</c:if>
							</div>
							<button class="webchat_submit" onclick="edit('#descriptionEditor', 1)">提交</button>
						</div>

						<div class="webchat_div_" style="display: none;">
							<p>特色服务：</p>
							<button class="insert_img" onclick="zcc(this,'edit')">
								<img src="<%=basePath%>images/webchat_add.png">插入图片
							</button>
							<div contenteditable="true" id="characteristicEditor" class="webchat_area">
								<c:if test="${!empty storeInfo.characteristicArray }">
				                    <c:forEach var="content" items="${storeInfo.characteristicArray }">
										<c:if test="${content.type == '1' }">
											<div>${content.text }</div>
										</c:if>
										<c:if test="${content.type == '2' }">
											<img style="width: 560px;height: 560px" src="<%=picPath %>${content.text}">
										</c:if>
									</c:forEach>
								</c:if>
							</div>
							<button class="webchat_submit" onclick="edit('#characteristicEditor', 2)">提交</button>
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
</body>
<script type="text/javascript" charset="utf-8" src="<%=basePath%>js/setting/storeSetting.js"></script>
<script type="text/javascript">
/**tab页面切换*/
jQuery(function(){
   jQuery('.webchat_div_:gt(0)').hide();
   jQuery('.webchat_p p').click(function(){
	     jQuery('.webchat_div_').eq(jQuery(this).index()).show().siblings().hide();
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

var cssWidth = 200;
var cssHeight = 200;

var imgObject = null;
var imgType = null;
function zcc(opt,type){
	imgType = type;
	if(type=="img"){
		imgObject = jQuery(opt).parent("li");
	}
	if(type=="edit"){
		imgObject = jQuery(opt).next();
	}
	jQuery('.zzc').show();
}

function zccCallback(dataBase64){
	imgObject.children("img").attr("src", dataBase64);
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
		       		imgObject.empty();
		       		var html = '<img style="width: 89px;height: 89px" onclick="zcc(this,"img")" src="'+qiniuUrl+key+'">'+
                    		   '<input type="hidden" name="carouselPicture" value="'+key+'">';
		       	}else{
		       		var html = '<img style="width: 560px;height: 560px" src="'+qiniuUrl+key+'">';
		       	}
		       	imgObject.append(jQuery(html));
	       }
 	});
}


jQuery(function(){
	 jQuery('.cancelinput').click(function(){
	    jQuery('.zzc').hide();
	    jQuery('.photo-clip-rotateLayer').html('');
	 })
})
</script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>js/base/base64Helper.js"></script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>
</html>