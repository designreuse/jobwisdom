<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <style>
    /*发布商品*/
    .g-title{
        font-size:14px;
        color:#303030;
    }

    .add-good {
        border: 1px solid #bbb;
     }

    .good-top {
        height: 52px;
        line-height: 52px;
        border-bottom: 1px solid #e7e7eb;
        font-size: 18px;
        padding-left: 20px;
    }
    .add-good label {
        margin-bottom: 10px;
    }

    .add-good .input-xxlarge {
        border: 1px solid #e0e0e0;
        height: 20px;
        line-height: 20px;
        border-radius: 3px !important;
        color: #555;
        padding: 2px 6px;
    }

    .controls {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    .yw-upload-imglist  .item {
        width: 132px;
        height: 132px;
        border: 2px solid #CDCDCD;

    }

    .thumbnails > li {
        float: left;
    }

    .thumbnails > li a {
    color:#616161
    }

    .yw-upload-imglist  img {
        width: 132px;
        height: 132px;
        display: inline-block;

    }

    .good-wrap {
        padding: 0 10px;
    }

    .thumbnail {
        padding: 0;
        border: none;
        line-height: 0;
        display:inherit;
    }

    .good-btn {
        margin-left: 0;
        margin-right: 0;
        padding-right: 25px;
        -webkit-box-shadow: none;
        text-align: center;
        padding-bottom: 30px;
        text-align: right;
        padding-right: 55px;
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
		    <div class="contentinner ">
		        <div class="add-good">
		            <div class="good-top">
		                编辑商品信息
		            </div>
		            <div class="good-wrap">
		                <div class="row-fluid controls">
		                    <div class="span10">
		                        <label class="g-title">商品名称：</label> ${goodsInfo.goodsName }
		                    </div>
		                </div>
		                
		                <div class="controls" id="container">
		                    <label class="g-title">商品主图(不可更改)</label>
		                </div>
		                
		                <div class="row-fluid yw-upload-imglist">
		                    <ul class="thumbnails">
		                    	<li class="item">
		                            <a href="javascript:void(0);" class="thumbnail">
		                                <img src="${goodsInfo.uboxPicture }"/>
		                                <input type="hidden" name="goodsPicture" value="${goodsInfo.uboxPicture }" />
		                            </a>
		                        </li>
		                    </ul>
		                </div>
		
		                <div class="controls" id="container">
		                    <label class="g-title">商品图片</label>
		                </div>
		
		                <div class="row-fluid yw-upload-imglist">
		                    <ul class="thumbnails" id="uploaded_imgs">
		                    	<c:set var="picNum" value="0" />
		                    	<c:if test="${!empty goodsInfo.pictureArray }">
		                    		<c:forEach items="${goodsInfo.pictureArray }" var="picture" varStatus="pictureStatus">
			                    		<li class="item">
				                            <a href="javascript:void(0);" onclick="showImgEditor(this, 1)" class="thumbnail">
				                                <img src="<%=picPath %>${picture}"/>
				                                <input type="hidden" name="imgs" value="${picture }" />
				                            </a>
				                        </li>
				                        <c:set var="picNum" value="${pictureStatus.index }" />
			                    	</c:forEach>
			                    	<c:set var="picNum" value="${picNum + 1 }" />
		                    	</c:if>
		                    	<c:forEach begin="1" end="${5 - picNum }">
		                    		<li class="item">
			                            <a href="javascript:void(0);" onclick="showImgEditor(this, 1)" class="thumbnail">
			                                <img src="<%=picPath %>zefun/images/pic_none.gif"/>
			                                <input type="hidden" name="imgs" value="" />
			                            </a>
			                        </li>
		                    	</c:forEach>
		                    </ul>
		                </div>
		
		                <div class="controls" id="container2">
		                    <label class="g-title">商品描述</label> <a class="btn" href="javascript:void(0);" onclick="showImgEditor(this, 2);">插入图片</a>
		                </div>
		
		                <div class="controls">
		                    <div id="editor_id" contentEditable="true" name="content" style="width: 95%; height: 500px;border:1px solid #e0e0e0;border-radius:4px;-webkit-border-radius:4px;-moz-border-radius:4px;overflow: auto;">
		                    	<c:if test="${!empty goodsInfo.goodsContentList }">
				                    <c:forEach var="content" items="${goodsInfo.goodsContentList }">
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
		                
		                <div class="good-btn" >
		                    <span id="baocun" style="min-width: 104px; padding: 0;"><button class="btn" onclick="edit(${goodsInfo.uboxId})">确定</button></span>
							<span style="min-width: 104px; padding: 0;"><button id="baocunyulan" class="btn">取消</button></span>
		                </div>
		            </div>
		
		        </div>
		    </div>
		</div>
		
        <!--RIGHT PANEL结束 -->
        <div class="clearfix"></div>

        <div id="star"></div>
    </div>
</div>

<!-- 截图模态框 -->
<div class="modal hide" id="jietu" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content jietu ">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">图片处理</h4>
            </div>
            <div class="modal-body nopadding">
              <div class="crop-container">
                <img src="<%=picPath %>zefun/images/pic_none.gif" id="cropbox" />
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

<script type="text/javascript" src="<%=basePath %>js/ubox/editGoods.js"></script>
</body>
</html>