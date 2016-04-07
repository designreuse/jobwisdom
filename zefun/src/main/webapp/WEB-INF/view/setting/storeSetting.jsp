<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
 <style>
    .hide{
        display: none;
    }
    .jietu{
		height: auto;
	}
	.crop-container{
		width: 1000px;
		height: 580px;
	} 
	.jietu .modal-body{
		padding: 0px;
		overflow: hidden;		
	}
	.edui-default .edui-editor {
    	z-index: 100 !important;
	}
	/* .alertWrap .toast  {
		width:auto;padding:0 10px;
	} */
	
	#mapbox{width:850px;height:420px; position:absolute; background-color:#CCC; border:1px solid #9CF; font-size:12px;left:200px; top:50px;}  
	#mapbox #map{width:600px;height:400px; float:left;}  
	#mapbox #results{width:250px; margin-top:10px; float:right;}  
	.mapheader{ height:25px;width:250px; float:right; overflow:hidden;}  
	#mapbox h2{ margin:1px; padding-left:10px; height:20px; line-height:20px; font-size:12px; color:#0066CC; font-weight:100; background:#9CF; cursor:move}  
	#mapbox .close{ display:block; position:absolute; right:10px; top:0px; line-height:22px; text-decoration:none; color:#0000}  
</style>

<body>
<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp"%>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp"%>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
        <%@ include file="/top.jsp"%>
        <div class="maincontent">
            <div class="contentinner">
                <div class="shop-iphone">
                    <div class="iphone-inner">
                        <div class="shop-info">
                            <div class="iconfont-wrap shopImage">
                                <span class="iconfont icon-iconfonttianjiaeps"></span>
                            </div>
                            <div class="img-word cursor setting_option"  name="edit-img">添加店铺轮播图片</div>
                            <div class="shop-tel cursor setting_option" name="edit-shop-tel">设置店铺地址和电话</div>
                        </div>
                        <ul class="shop-func">
                            <li class="setting_option" name="edit-description">
                                <span class="word" >门店介绍</span> 
                                <span  class="fr iconfont icon-xiugai4"></span>
                            </li>
                            <li class="setting_option" name="edit-characteristic">
                                <span class="word">特色服务</span> 
                                <span  class="fr iconfont icon-xiugai4"></span>
                            </li>
                            <li class="setting_option" name="edit-mingshi">
                                <span class="word">名师介绍</span> 
                                <span  class="fr iconfont icon-xiugai4"></span>
                            </li>
                        </ul>
                        <!-- <img src="assets/images/1.jpg" alt="" class="shop-qrcode"/>
                        <div class="text-center qrcode-word" >扫码预览</div> -->
                    </div>
                </div>
                
                <div class="shop-edit-area edit-shop-tel ">
                    <div class="shop-edit">
                        <div class="inner">
                            <p id="storeLogoContent">
		                        <label for="" class="shop-logo-label ">设置店铺LOGO:</label>
		                        <c:choose>
		                          <c:when test="${empty storeInfo.storeLogo }">
		                              <img src="<%=basePath %>images/shop-logo.png" onclick="showImgEditor(this, 4);" class="shop-logo"/>
		                          </c:when>
		                          <c:otherwise>
		                              <img src="<%=picPath %>${storeInfo.storeLogo }?imageView2/1/w/120/h/120" onclick="showImgEditor(this, 4);" class="shop-logo"/>
		                          </c:otherwise>
		                        </c:choose>
		                        <span class="ml30">*此logo用于移动端店铺介绍页面。</span>
		                        <input type="hidden" id="storeLogo" value="${storeInfo.storeLogo }" />
		                    </p>
		                    <p>
                                <label for="">设置店铺名称</label>
                                <input type="text" id="storeName" value="${storeInfo.storeName }" class="wp97"/>
                            </p>
                            <p>
                                <label for="">设置店铺电话<span class="font-999" style="font-size: 12px">(多个以,号分割)</span></label>
                                <input type="text" id="storeTel" value="${storeInfo.storeTel }" class="wp97"/>
                            </p>
                            <p>
                                <label for="">门店负责人姓名</label>
                                <input type="text" id="storeLinkname" value="${storeInfo.storeLinkname }" class="wp97"/>
                            </p>
                            <p>
                                <label for="">门店负责人电话</label>
                                <input type="text" id="storeLinkphone" value="${storeInfo.storeLinkphone }" class="wp97"/>
                            </p>
                            <%-- <p>
                                <label for="">店铺地址</label>
                                <input type="text" id="storeAddress" value="${storeInfo.storeAddress }" class="wp97" readonly="readonly"/>
                            </p> --%>
                            <p>
		                        <label for="">店铺地址</label>
		                        <span id="city" class="addrres-difang">${storeInfo.storeProvince }${storeInfo.storeCity }</span>
		                        <input id="searchtext" type="text" class="wp60"  value="${street }" placeholder=""/>
		                        <span id="searchbt" class="addrres-search" onclick="serachlocal()" style="cursor:pointer">搜索</span>
		                    </p>
		                    <div id="lat" class="hide">${storeInfo.latitude }</div>
		                    <div id="lng" class="hide">${storeInfo.longitude }</div>
		                    <div id="results"></div>  
					        <div id="mapx"></div>  
					        <div id="mapy"></div>  
					        <div id="level"></div> 
		                    <!-- 地图填充 -->
		                    <div style="width:600px;height:550px;border:#ccc solid 1px;font-size:12px" id="map"></div>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="saveStoreInfo()">提交</div>
                </div>
        
                <div class="shop-edit-area edit-img hide">
                    <div class="shop-edit">
                        <div class="inner">
                            <div class="flex-img">
                                <c:if test="${!empty storeInfo.pictureArray }">
                                    <c:forEach items="${storeInfo.pictureArray}" var="picture">
                                       <div class="img-wrap">
	                                        <span class="iconfont icon-guanbi"></span>
	                                        <img src="<%=picPath %>${ picture }">
	                                        <input type="hidden" name="carouselPicture" value="${ picture }">
	                                    </div>
                                    </c:forEach>
                                </c:if>
                                <div class="img-wrap">
                                    <img  src="<%=basePath %>img/img-none.png" onclick="showImgEditor(this, 1)"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="saveCarousel()">提交</div>
                </div>
                
                <div class="shop-edit-area edit-description hide">
                    <div class="shop-edit">
                        <div class="inner" id="descriptionImgContent" >
	                        <span class="font-size-16">门店介绍</span>
                            <span class="btn" id="characteristicImg" onclick="showImgEditor(this, 2);">插入图片</span>
                            <div id="descriptionEditor" contentEditable="true" name="content" style="width: 100%; height: 560px; margin-top:10px; border:1px solid #e0e0e0;border-radius:4px;-webkit-border-radius:4px;-moz-border-radius:4px;overflow: auto;">
		                    	<c:if test="${!empty storeInfo.storeDescArray }">
				                    <c:forEach var="content" items="${storeInfo.storeDescArray }">
										<c:if test="${content.type == '1' }">
											<div>${content.text }</div>
										</c:if>
										<c:if test="${content.type == '2' }">
											<img style="max-width: 100%;max-height: 100%;" src="<%=picPath %>${content.text}">
										</c:if>
									</c:forEach>
								</c:if>
							</div>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="edit('#descriptionEditor', 1)">提交</div>
                </div>
                
                <div class="shop-edit-area edit-characteristic hide">
                    <div class="shop-edit">
                        <div class="inner" id="characteristicImgContent">
                            <span class="font-size-16">特色服务</span>
                            <span class="btn" id="characteristicImg" onclick="showImgEditor(this, 3);">插入图片</span>
                            <div id="characteristicEditor" contentEditable="true" name="content" style="width: 100%; height: 560px; margin-top:10px; border:1px solid #e0e0e0;border-radius:4px;-webkit-border-radius:4px;-moz-border-radius:4px;overflow: auto;">
		                    	<c:if test="${!empty storeInfo.characteristicArray }">
				                    <c:forEach var="content" items="${storeInfo.characteristicArray }">
										<c:if test="${content.type == '1' }">
											<div>${content.text }</div>
										</c:if>
										<c:if test="${content.type == '2' }">
											<img style="max-width: 100%;max-height: 100%;" src="<%=picPath %>${content.text}">
										</c:if>
									</c:forEach>
								</c:if>
							</div>
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="edit('#characteristicEditor', 2)">提交</div>
                </div>
                
                <div class="shop-edit-area edit-mingshi hide">
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
                                                 <li class="designer-item-content" employeeId="${employee.employeeId }">
                                                    <div class="shanchu-icon"><span class="iconfont icon-shanchujilu"></span></div>
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
                        </div>
                    </div>
                    <div class="btn tijiao" onclick="saveEmployee()">提交</div>
                </div>
            </div>
        </div>
        <!--RIGHT PANEL结束 -->
        <div class="clearfix"></div>

        <div id="star"></div>
    </div>
</div>

<div class="modal hide" id="jietu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content jietu ">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">图片处理</h4>
            </div>
            <div class="modal-body nopadding">
              <div class="crop-container">
                <img src="<%=basePath %>images/pic_none.gif" id="cropbox" />
              </div>

              <div class="jietu-control">
                <input type="file" class="inputfile" accept="image/*" />
                <div class="btn dblock">选择文件</div>
                <div class="btn dblock mt5 save">保存</div>
                <div class="btn dblock mt5 zoomin">放大</div>
                <div class="btn dblock mt5 zoomout">缩小</div>
              </div>

            </div>
        </div>
    </div>
</div>

<!-- 测试百度地图模态框 -->
<%-- <div class="modal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content ">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">员工资料</h5>
            </div>
            <div class="modal-body">
            	<p>
		                        <label for="">搜索店铺地址</label>
		                        <span id="city" class="addrres-difang">${city }</span>
		                        <input id="searchtext" type="text" class="wp60" placeholder=""/><span id="searchbt" class="addrres-search" onclick="serachlocal()">搜索</span>
		                    </p>
            	<div style="width:600px;height:550px;border:#ccc solid 1px;font-size:12px" id="map"></div>
            </div><!--modal-body-->
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal--> --%>
<script type="text/javascript">
	var globalLat = '${storeInfo.latitude }';
	var globalLng = '${storeInfo.longitude }';
</script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>js/base/base64Helper.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>js/setting/storeSetting.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=zxYZYzCtCT1lhiVOuxQeieOf"></script>
<script type="text/javascript" src="<%=basePath%>js/baiduMap/baiduMapOfEditStorePosition.js"></script>
</body>
</html>