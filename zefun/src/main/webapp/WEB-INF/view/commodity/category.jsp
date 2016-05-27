<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>

<style>
.item_list{margin-top:20px;height:310px}
.item_list select{width:104px;height:22px;padding-left:50px;border-radius:10px;font-size:14px;color:black;border:1px solid black}
.item_list button{border:none;border-radius:10px;color:white;background:#59688a;height:30px;text-align:center;line-height:30px;width:76px;margin:0 29px 0 95px}
.item_list input{height:20px;width:120px;padding-left:10px;border-radius:10px 0 0 10px;border:1px solid black;margin-left:4px}
.item_part{margin:20px 100px}
.item_list button:hover{background:#495673}

.item_part_content{margin-left:40px}
.item_part_content_left{width:283px;height:200px;border-radius:8px;overflow:overlay;float:left;border:1px solid black;margin-right:30px}
.item_part_content_right{float:left;border:1px solid black;border-radius:8px;width:700px;height:200px}

.hair_part{width:236px;height:38px;text-align:center;border:1px solid #59688a;border-left:3px solid #ff0000;line-height:38px;color: black;position:relative;
    font-size: 14px;margin:11px 0 0 4px}
.hair_part em{display:inline-block;width:47px;height:30px;border-left:2px solid #989da6;line-height:30px;position:relative;left:72px}
.item_part_content_left i,.item_part_content_right i{display:inline-block;position:relative;top:9px;left:-1px}
.demo_fade{    position: absolute;
    border-left: 1px solid #565656;
    border-right: 1px solid #565656;
    top: 38px;
    border-bottom: 1px solid #565656;
    border-top: 1px solid white;
    z-index: 2;
    top:33px;
    right: 1px;
	display:none
	}
	.item_part_content_left span,.item_part_content_right span{position:relative;display:inline-block}
	
	.demo_fade img{height:20px}
.demo_fade li:hover{background:#d8d6d6}	
.item_part_content_right span{margin-left:25px}
.demo_fade li{background:#ececec}
</style>
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right clearfix">
					<div class="item_list">
						<p>
							<img src="<%=basePath%>images/item.png" style="width: 100%">
						</p>
						<div class="item_part">
							<select name="projectCategory" onchange="changeDept(1, this)">
							<c:forEach items="${projectBaseDtos }" var="projectCategory">
							<option value="${projectCategory.deptId }">${projectCategory.deptName }</option>
							</c:forEach>
							</select>
							<button onclick="saveProjectCategory(this)">添加</button>
							<input id="projectCategory" categoryId="" type="text" placeholder="不超过八个字" style="border-radius: 10px">
						</div>
						<div class="item_part_content clearfix">
							<div class="item_part_content_left">
								
							</div>
							<div class="item_part_content_right">
								<c:forEach items="${projectBaseDtos[0].projectCategoryList }" var="projectCategory">
									<span id="${projectCategory.categoryId }"> 
										<input type="text" value="${projectCategory.categoryName }" placeholder="${projectCategory.categoryName }" style="height: 14px; width: 100px; margin-left: 5px; padding-left: 4px">
										<i><img src="<%=basePath%>images/demo2_down.png"></i>
										<ul class="demo_fade" style="">
											<li><img src="<%=basePath%>images/handle_2.png" onclick="deleted(1, ${projectCategory.categoryId }, this)"></li>
											<li><img src="<%=basePath%>images/handle_1.png" onclick="update(1, ${projectCategory.categoryId }, this)"></li>
										</ul>
									</span>
								</c:forEach>
							</div>

						</div>
					</div>

					<div class="item_list">
						<p>
							<img src="<%=basePath%>images/shop.png">
						</p>
						<div class="item_part">
							<select name="goodsCategory" onchange="changeDept(2, this)">
							<c:forEach items="${goodsBaseDtos }" var="goodsCategory">
							<option value="${goodsCategory.deptId }">${goodsCategory.deptName }</option>
							</c:forEach>
							</select>
							<button onclick="saveGoodsCategory(this)">添加</button>
							<input id="goodsCategory" categoryId="" type="text" placeholder="不超过八个字" style="border-radius: 10px">
						</div>
						<div class="item_part_content clearfix">
							<div class="item_part_content_left">
								
							</div>
							<div class="item_part_content_right">
								<c:forEach items="${goodsBaseDtos[0].goodsCategoryBaseDto }" var="goodsCategory">
									<span id="${goodsCategory.categoryId }"> 
										<input type="text" value="${goodsCategory.categoryName }" placeholder="${goodsCategory.categoryName }" style="height: 14px; width: 100px; margin-left: 5px; padding-left: 4px">
										<i><img src="<%=basePath%>images/demo2_down.png"></i>
										<ul class="demo_fade" style="">
											<li><img src="<%=basePath%>images/handle_2.png" onclick="deleted(2, ${goodsCategory.categoryId }, this)"></li>
											<li><img src="<%=basePath%>images/handle_1.png" onclick="update(2, ${goodsCategory.categoryId }, this)"></li>
										</ul>
									</span>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	var projectBaseDtosJs = eval('('+'${projectBaseDtosJs}'+')');
	var goodsBaseDtos = eval('('+'${goodsBaseDtosJs}'+')');
	jQuery("body").delegate('.item_part_content_left i,.item_part_content_right i','click',function(){
		find = jQuery(this).parent().find('.demo_fade');
		if (find.css('display') == 'none') {
			find.stop(true, true).slideDown('normal');
		} else if (find.css('display') == 'block') {
			jQuery('.demo_fade').stop(true, true).slideUp('normal');
		}
	});
	function saveProjectCategory(btn){
		var categoryName = jQuery(btn).next("input").val();
		var deptId =  jQuery("select[name='projectCategory']").val();
		var categoryId = jQuery(btn).next("input").attr("categoryId");
		if(categoryId == ""){
			jQuery.ajax({
				type: "POST",
				url: baseUrl+"project/saveProjectCategory",
		        data: "categoryName="+ categoryName + "&deptId="+deptId,
		        success: function(data) {
		        	if(data.code==0){
		        		var span = jQuery("")
		        		var html = '<span id='+data.msg+'><input value="'+categoryName+'" type="text" placeholder="'+categoryName+'" style="height: 14px; width: 100px; margin-left: 5px; padding-left: 4px"><i><img src="'+baseUrl+'images/demo2_down.png"></i>'+
												'<ul class="demo_fade" style="display: none;">'+
											'<li><img src="'+baseUrl+'images/handle_2.png" onclick="deleted(1, '+data.msg+', this)"></li>'+
											'<li><img src="'+baseUrl+'images/handle_1.png" onclick="update(1, '+data.msg+', this)"></li>'+
										'</ul>'+
									'</span>';
						jQuery(".item_part_content_left").eq(0).append(jQuery(html));
		        	}
		        }
			});
		}else {
			jQuery.ajax({
				type: "POST",
				url: baseUrl+"project/editProjectCategory",
		        data: "categoryId="+categoryId + "&categoryName="+categoryName+"&deptId="+deptId,
		        success: function(data) {
		            if(data.code == 0){
		            	dialog("修改成功");
		            	jQuery("#projectCategory").attr("categoryId", "");
		    			jQuery("#projectCategory").val("");
		    			jQuery(".item_part_content.clearfix").eq(0).find("span[id="+categoryId+"]").find("input").val(categoryName);
		            }else{
		            	dialog("error");
		            }
		        }
			});
		}
	}
	function saveGoodsCategory(btn){
		var categoryName = jQuery(btn).next("input").val();
		var deptId =  jQuery("select[name='goodsCategory']").val();
		var categoryId = jQuery(btn).next("input").attr("categoryId");
		jQuery(btn).next("input").val("");
		if(categoryId == ""){
			jQuery.ajax({
				type: "POST",
				url: baseUrl+"goodsInfo/saveGoodsCategory",
		        data: "categoryName="+ categoryName + "&deptId="+deptId,
		        success: function(data) {
		        	if(data.code==0){
		        		var span = jQuery("")
		        		var html = '<span id='+data.msg+'><input value="'+categoryName+'" type="text" placeholder="'+categoryName+'" style="height: 14px; width: 100px; margin-left: 5px; padding-left: 4px"><i><img src="'+baseUrl+'images/demo2_down.png"></i>'+
												'<ul class="demo_fade" style="display: none;">'+
											'<li><img src="'+baseUrl+'images/handle_2.png" onclick="deleted(1, '+data.msg+', this)"></li>'+
											'<li><img src="'+baseUrl+'images/handle_1.png" onclick="update(1, '+data.msg+', this)"></li>'+
										'</ul>'+
									'</span>';
						jQuery(".item_part_content_left").eq(1).append(jQuery(html));
		        	}
		        }
			});
		}else {
			jQuery.ajax({
				type: "POST",
				url: baseUrl+"goodsInfo/editGoodsCategory",
		        data: "categoryId="+categoryId + "&categoryName="+categoryName+"&deptId="+deptId,
		        success: function(data) {
		            if(data.code == 0){
		            	dialog("修改成功");
		            	jQuery("#goodsCategory").attr("categoryId", "");
		    			jQuery("#goodsCategory").val("");
		    			jQuery(".item_part_content.clearfix").eq(1).find("span[id="+categoryId+"]").find("input").val(categoryName);
		            }else{
		            	dialog("error");
		            }
		        }
			});
		}
	}
	function deleted(type, id, span){
		if(type == "1"){
			var deptId =  jQuery("select[name='projectCategory']").val();
			var data = "categoryId="+id + "&deptId=" + deptId;
			jQuery.ajax({
				url: baseUrl+"project/deleteProjectCategory",
		        data: data,
		        success: function(data) {
		            if(data.code == 0){
		            	dialog("删除成功");
		            	jQuery(span).parents("span[id]").remove();
		            }else{
		            	dialog(data.msg);
		            }
		        }
			});
		}
		if(type == "2"){
			var deptId =  jQuery("select[name='goodsCategory']").val();
			var data = "categoryId=" +id + "&deptId=" + deptId;
			jQuery.ajax({
				url: baseUrl+"goodsInfo/deleteGoodsCategory",
		        data: data,
		        success: function(data) {
		            if(data.code == 0){
		            	dialog("删除成功");
		            	jQuery(span).parents("span[id]").remove();
		            }else{
		            	dialog(data.msg);
		            }
		        }
			});
		}
	}
	function update(type, id, input){
		if(type == "1"){
			var name = jQuery(input).parents("span[id]").find("input").val();
			jQuery("#projectCategory").attr("categoryId", id);
			jQuery("#projectCategory").val(name);
		}
		if(type == "2"){
			var name = jQuery(input).parents("span[id]").find("input").val();
			jQuery("#goodsCategory").attr("categoryId", id);
			jQuery("#goodsCategory").val(name);
		}
	}
	function changeDept(type, select){
		var deptId = jQuery(select).val();
		if(type == 1){
			jQuery(".item_part_content_right").eq(0).empty();
			for (var i = 0; i < projectBaseDtosJs.length; i++) {
				if(deptId == projectBaseDtosJs[i].deptId){
					for (var j = 0; j < projectBaseDtosJs[i].projectCategoryList.length; j++) {
						var categoryName = projectBaseDtosJs[i].projectCategoryList[j].categoryName;
						var categoryId = projectBaseDtosJs[i].projectCategoryList[j].categoryId;
						var html = '<span id='+categoryId+'><input value="'+categoryName+'" type="text" placeholder="'+categoryName+'" style="height: 14px; width: 100px; margin-left: 5px; padding-left: 4px"><i><img src="'+baseUrl+'images/demo2_down.png"></i>'+
												'<ul class="demo_fade" style="display: none;">'+
											'<li><img src="'+baseUrl+'images/handle_2.png" onclick="deleted(1, '+categoryId+', this)"></li>'+
											'<li><img src="'+baseUrl+'images/handle_1.png" onclick="update(1, '+categoryId+', this)"></li>'+
										'</ul>'+
									'</span>';
						jQuery(".item_part_content_right").eq(0).append(jQuery(html));
					}
					return;
				}
			}
		}
		if(type == 2){
			jQuery(".item_part_content_right").eq(1).empty();
			for (var i = 0; i < goodsBaseDtos.length; i++) {
				if(deptId == goodsBaseDtos[i].deptId){
					for (var j = 0; j < goodsBaseDtos[i].goodsCategoryBaseDto.length; j++) {
						var categoryName = goodsBaseDtos[i].goodsCategoryBaseDto[j].categoryName;
						var categoryId = goodsBaseDtos[i].goodsCategoryBaseDto[j].categoryId;
						var html = '<span id='+categoryId+'><input value="'+categoryName+'" type="text" placeholder="'+categoryName+'" style="height: 14px; width: 100px; margin-left: 5px; padding-left: 4px"><i><img src="'+baseUrl+'images/demo2_down.png"></i>'+
												'<ul class="demo_fade" style="display: none;">'+
											'<li><img src="'+baseUrl+'images/handle_2.png" onclick="deleted(2, '+categoryId+', this)"></li>'+
											'<li><img src="'+baseUrl+'images/handle_1.png" onclick="update(2, '+categoryId+', this)"></li>'+
										'</ul>'+
									'</span>';
						jQuery(".item_part_content_right").eq(1).append(jQuery(html));
					}
					return;
				}
			}
		}
	}
</script>
</html>