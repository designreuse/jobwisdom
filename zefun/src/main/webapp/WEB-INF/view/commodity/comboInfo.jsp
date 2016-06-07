<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/roll.css" type="text/css" />
<style>
.close1 img{
  transition: transform 1s ease-out;
    }
.close1 img:hover{
  
 transform: rotate(360deg);
}
.arrows_right{position:absolute;right:0;top:450px}
.arrows_right img{width:65px;z-index:10}
.left_1 img{ transition: transform 1s ease-out;}
</style>

<script>
 jQuery(function(){
    jQuery('.left_1').click(function(){
       jQuery('.alertPanel').animate({
    	    right:'0'
       })
       jQuery('.left_1').animate({
    	    right:'331'
       },function(){
    	   jQuery(this).addClass('b');
    	   jQuery(this).find('img').css('transform','rotate(180deg)')  
    	   
       });
    });
    jQuery(document).on('click','.b',function(){
    	
    	 jQuery('.alertPanel').animate({
           right:'-331'
        });
    	 
    	 jQuery('.left_1').animate({
     	    right:'0'
         },function(){
     	   jQuery(this).removeClass('b');
     	   jQuery(this).find('img').css('transform','rotate(0deg)')  
     	   
        }); 
    	
    	
    });
  })

</script>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
            
				<div class="rollBox">
					<div class="LeftBotton"></div>
					<div class="Cont" id="ISL_Cont">
						<div class="ScrCont">
							<div id="List1">
								<c:forEach items="${comboInfos }" var="comboInfo" step="2" varStatus="status">
									<div class="pic">
									<div class="column_small_first " action-type="showdesc" onclick='window.open("<%=basePath %>comboInfo/setting?comboId=${comboInfos[status.count*2-2].comboId }","_self")'>
										<div class="column_img_container" style="position:relatiev">
										   <span deptId="${comboInfos[status.count*2-2].deptId}" comboId="${comboInfos[status.count*2-2].comboId }" class="close1" style="position:absolute;right:25px;top:15px;"><img src="<%=basePath%>images/close1.png" style="width:20px"></span>
											<div class="head_pic">
												<img src="<%=picPath%>${comboInfos[status.count*2-2].comboImage}">
											</div>
											<em class="boss_cut">${comboInfos[status.count*2-2].comboName }</em>
											<p class="hair">
												<img src="<%=basePath%>images/partment.png">
												<c:if test="${empty comboInfos[status.count*2-2].deptName }">暂无</c:if>
												<c:if test="${! empty comboInfos[status.count*2-2].deptName}">${comboInfos[status.count*2-2].deptName }</c:if>
											</p>
											<ul class="clearfix shop_number">
												<li><em class="number_">编号</em>
													<p class="num_">${comboInfos[status.count*2-2].comboCodeSuffix }<%-- ${goodsInfos[status.count*2-2].projectCodeSuffix } --%></p>
													<p></p></li>
												<li><em class="shop_price">门店价格</em>
													<p>
													<c:if test="${empty comboInfos[status.count*2-2].comboSalePrice }">暂无</c:if>
													<c:if test="${! empty comboInfos[status.count*2-2].comboSalePrice}">${comboInfos[status.count*2-2].comboSalePrice }</c:if>
													</p>
													<p></p></li>
											</ul>
										</div>
									</div>
									
									<c:if test="${(status.count*2-1)!=fn:length(comboInfos) }">
									<div class="column_small_first " action-type="showdesc" onclick='window.open("<%=basePath %>comboInfo/setting?comboId=${comboInfos[status.count*2-1].comboId }","_self")'>
										<div class="column_img_container">
										<span deptId="${comboInfos[status.count*2-1].deptId}" comboId="${comboInfos[status.count*2-1].comboId }" class="close1" style="position:absolute;right:25px;top:275px;"><img src="<%=basePath%>images/close1.png" style="width:20px"></span>
											<div class="head_pic">
												<img src="<%=picPath%>${comboInfos[status.count*2-1].comboImage}">
											</div>
											<em class="boss_cut">${comboInfos[status.count*2-1].comboName }</em>
											<p class="hair">
												<img src="<%=basePath%>images/partment.png">
												<c:if test="${empty comboInfos[status.count*2-1].deptName }">暂无</c:if>
												<c:if test="${! empty comboInfos[status.count*2-1].deptName}">${comboInfos[status.count*2-1].deptName }</c:if>
											</p>
											<ul class="clearfix shop_number">
												<li><em class="number_">编号</em>
													<p class="num_">${comboInfos[status.count*2-1].comboCodeSuffix }<%-- ${goodsInfos[status.count*2-1].projectCodeSuffix } --%></p>
													<p></p></li>
												<li><em class="shop_price">门店价格</em>
													<p>
													<c:if test="${empty comboInfos[status.count*2-1].comboSalePrice }">暂无</c:if>
													<c:if test="${! empty comboInfos[status.count*2-1].comboSalePrice}">${comboInfos[status.count*2-1].comboSalePrice }</c:if>
													</p>
													<p></p></li>
											</ul>
										</div>
									</div>
									</c:if>
								</div>
								</c:forEach>
							</div>
						</div>
					</div>
					<div class="RightBotton"></div>
				</div>
				<div class="project-category">

					<ul class="project-sublist hide">
						<li class="project-sublist-title">洗剪吹系列 <span class="fr"> <i class="iconfa-plus project-icon"></i> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
							<div class="clearfix"></div>
						</li>
						<li class="project-sublist-content nopadding">
							<div class="left-li">单剪</div>
							<div class="right-li">
								<span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
								</span>
							</div>
							<div class="clearfix"></div>
						</li>
						<li class="project-sublist-content nopadding">
							<div class="left-li">洗剪吹</div>
							<div class="right-li">
								<span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
								</span>
							</div>
							<div class="clearfix"></div>
						</li>
						<li class="project-sublist-content nopadding">
							<div class="left-li">泰式洗发</div>
							<div class="right-li">
								<span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
								</span>
							</div>
							<div class="clearfix"></div>
						</li>
					</ul>
					<ul class="project-sublist hide ">
						<li class="project-sublist-title">烫染系列 <span class="fr"> <i class="iconfa-plus project-icon"></i> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
						</li>
						<li class="project-sublist-content">欧莱雅 烫发 <span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
						</li>
						<li class="project-sublist-content">轩尼诗染发 <span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
						</li>
						<li class="project-sublist-content">欧莱雅烫染 <span class="fr"> <i class="iconfa-pencil project-icon"></i> <i class="iconfa-trash project-icon"></i>
						</span>
						</li>
					</ul>
				</div>

			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="<%=basePath%>js/commodity/project.js"></script>
<script type="text/javascript" src="<%=basePath%>js/base/roll.js"></script>
<script>
jQuery(".close1").on("click", function (){
	if(confirm("确定要删除该套餐么?")){
		var comboId = jQuery(this).attr("comboId");
		jQuery.ajax({
	        cache: true,
	        type: "POST",
	        url: baseUrl+"comboInfo/deleteComboInfo",
	        data: "comboId="+ comboId,
	        async: false,
	        error: function(request) {
	            dialog("Connection error");
	        },
	        success: function(data) {
	            if(data.code == 0){
	            	dialog("删除成功");
	            	location.reload();
	            }else{
	            	dialog(data.msg);
	            }
	        }
	    });
	}
	return false;
});
</script>
</html>