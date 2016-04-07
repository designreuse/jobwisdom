<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>后台管理</title>
</head>
<body>
	<!--头部页面引用-->
	<%@include file="/WEB-INF/view/topheader.jsp"%>
	<!--左侧菜单页面引用-->
	<%@include file="/WEB-INF/view/sliderbar.jsp"%>
	<div id="content">
		<div id="content-header">
			<div id="breadcrumb">
				<a href="<%=basePath %>homepage/view/query" class="tip-bottom" data-original-title="主页">
					<i class="icon-home"></i>首页看板
				</a>
				<!-- <a href="" class="tip-bottom" data-original-title=""></a>-->
				<a href="#" class="current">公告详情</a>
			</div>
		</div>
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="control-group">
					<div class="span8" style="margin-left: 300;">
						<label class="gg-label">公告标题 :${afficheTitle }</label> 
						<label class="gg-label" style="margin-left: 30px;">发布时间 :${afficheTime }</label>
					    <label class="gg-label"style="margin-left: 30px;">公告类别 :${afficheClasses }</label>
					</div>
					<label class="gg-label">发布人 :12312312</label>
				</div>
			</div>

			<div class="row-fluid">
                <div class="widget-box" style="height: 800px;">
                    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5>公告内容</h5>
                    </div>
                    <div class="widget-content">
                        <div class="control-group">
                            <form>
                                <div class="controls">
                                
                                  ${afficheContent } 
                                  
                                </div>
                            </form>
                        </div>
                    </div>
                      <div class="control-group">
                             <button class="btn btn-success client-add-save" onclick="goback()"><a href="#"  style="text-decoration:none;color: #fff;">返回查询页面</a></button>
                            </div>
                </div>
        </div>

		</div>

	</div>
	
	<!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
	
	<script type="text/javascript">
	var baseUrl = "<%=basePath %>";
	function goback(){
		location.href=encodeURI(baseUrl +"SystemAffiche/view/selectTable"); 
	}

	</script>
</body>
</html>


