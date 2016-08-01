<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xkv8r.com1.z0.glb.clouddn.com/";
%>
<link rel="stylesheet" href="<%=basePath%>css/supplier_manage_brand.css" type="text/css" />
<script>
	jQuery(
			function() {
				jQuery(document).on('mouseover',
						'.supplier_manage_brand_content_right_text li',
						function() {
							jQuery(this).find('p').show();
							if (jQuery(this).find('img').length == '0') {
								jQuery(this).addClass('active');
							}
						});
				jQuery(document).on('mouseout',
						'.supplier_manage_brand_content_right_text li',
						function() {
							jQuery(this).find('p').hide();
							if (jQuery(this).find('img').length == '0') {
								jQuery(this).removeClass('active');
							}
						});
			})
</script>
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<!-- 页面代码 -->
				<div class="content_right clearfix">
					<div class="supplier_manage_brand_top clearfix">
						<input type="text" placeholder="名称/品牌/号码">
						<button>搜索</button>
						<span><button onclick="jQuery('.zzc').show('800');jQuery('.zzc').find('input[type=\'text\']').val('');">添加加盟供应商</button></span>
					</div>

					<div class="supplier_manage_brand_content clearfix">
						<div class="supplier_manage_brand_content_top clearfix">
							<div class="details">供应商详情</div>
							<div class="goods">供应商产品</div>
						</div>
						<div class="supplier_manage_brand_content_ clearfix">
							<div class="supplier_manage_brand_content_left">
								<ul>
									<c:forEach items="${supplierInfoDtos }" var="supplierInfo" varStatus="index">
										<li supplierId="${supplierInfo.supplierId }" supplierName="${supplierInfo.supplierName }" linkName="${supplierInfo.linkName }" linkPhone="${supplierInfo.linkPhone }">
											<div class="supplier_manage_brand_content_left_ul">
												<p>
													供应商：${supplierInfo.supplierName }<span onclick="updateSupper(${supplierInfo.supplierId }, this)">修改</span>
												</p>
												<p>地址：${supplierInfo.linkName }<span onclick="deleteSupper(${supplierInfo.supplierId }, ${index.count-1 })">删除</span></p>
												<p>联系方式：${supplierInfo.linkPhone }</p>
											</div>
										</li>
									</c:forEach>
								</ul>
							</div>

							<div class="supplier_manage_brand_content_right">
								<ul>
									<c:forEach items="${supplierInfoDtos }" var="supplierInfo">
										<li>
											<ul supplierId="${supplierInfo.supplierId }" class="supplier_manage_brand_content_right_text">
												<c:forEach items="${supplierInfo.brands }" var="brand">
													<li brandId="${brand.brandId }" brandName="${brand.brandName }" class="">
														${brand.brandName }
														<p style="display: none;">
															<span onclick="updateBrand(this, ${brand.brandId })">修改</span> 
															<span onclick="deleted(this, ${brand.brandId })">删除</span>
														</p>
													</li>
												</c:forEach>
												<li onclick="showAdd(${supplierInfo.supplierId })" class="a">
													<img src="<%=basePath %>images/supplier_manage_img.png">
												</li>
											</ul>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="zzc" style="display: none">
			<div class="add_supplier">
				<p>添加加盟供应商</p>
				<div class="add_supplier_content">
					<p>
						<span>供应商名称</span>
						<input type="hidden" name="supplierId">
						<input type="text" name="supplierName">
					</p>
					<p>
						<span>地址</span>
						<input type="text" style="width: 460px" name="linkName">
					</p>
					<p>
						<span>联系方式</span>
						<textarea name="linkPhone"></textarea>
					</p>
					<!-- <div class="add_supplier_button">
						<button>添加品牌</button>
						<button>删除</button>
					</div>
					<textarea class="textarea">	   
	   				</textarea> -->
					<div class="add_supplier_content_button">
						<button onclick="saveSupper()">确认</button>
						<button onclick='hideZZc(this)'>取消</button>
					</div>
				</div>
			</div>
		</div>

		<!-- <div class="zzc hide">
			<div class="add_supplier_alert">
				<p>添加供应商</p>
				<div class="add_supplier_alert_content">
					<p>
						<em>供应商名称</em>
						<input type="hidden" name="supplierId">
						<input type="text" name="supplierName">
						<i class="addcolor">*</i>
					</p>
					<p>
						<em>地址</em>
						<input type="text" style="width: 200px" name="linkName">
						<i class="addcolor">*</i>
					</p>
					<p>
						<em>联系方式</em>
						<textarea style="border: 1px solid black; width: 212px; height: 50px; border-radius: 8px" name="linkPhone"></textarea>
						<i class="addcolor">*</i>
					</p>

					<div class="add_supplier_alert_sure">
						<button onclick="saveSupper()">确认</button>
						<button onclick='hideZZc(this)'>取消</button>
					</div>
				</div>
			</div>
		</div> -->

		<div class="zzc1 hide">
			<div class="zzc1_div">
				<p>商品品牌</p>
				<div>
					<input type="hidden" name="brandId">
					<input type="text" name="brandName">
				</div>
				<div class="zzc_div_button">
					<button onclick="saveBrand()">确定</button>
					<button onclick="jQuery(this).parents('.zzc1').hide('800');supplierId = null;">取消</button>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			var supplierId = null;
			function hideZZc(opt) {
				jQuery(opt).parents(".zzc").hide("800");
				jQuery("input[name='supplierId']").val("");
				jQuery("input[name='supplierName']").val("");
				jQuery("input[name='linkName']").val("");
				jQuery("textarea[name='linkPhone']").val("");
			}
			function updateSupper(id, dm) {
				var supplierName = jQuery(dm).parents("li[supplierId]").attr("supplierName");
				var linkName = jQuery(dm).parents("li[supplierId]").attr("linkName");
				var linkPhone = jQuery(dm).parents("li[supplierId]").attr("linkPhone");
				jQuery("input[name='supplierId']").val(id);
				jQuery("input[name='supplierName']").val(supplierName);
				jQuery("input[name='linkName']").val(linkName);
				jQuery("textarea[name='linkPhone']").val(linkPhone);
				jQuery(".zzc").show('800');
			}
			function saveSupper() {
				var supplierIds = jQuery("input[name='supplierId']").val();
				var supplierName = jQuery("input[name='supplierName']").val();
				if (supplierName==""){dialog("名称可不能为空哦");return;}
				var linkName = jQuery("input[name='linkName']").val();
				var linkPhone = jQuery("textarea[name='linkPhone']").val();
				var data = "";
				if (supplierIds == "") {
					data = "supplierName=" + supplierName + "&linkName="
							+ linkName + "&linkPhone=" + linkPhone;
				} else {
					data = "supplierId=" + supplierIds + "&supplierName="
							+ supplierName + "&linkName=" + linkName
							+ "&linkPhone=" + linkPhone;
				}
				console.log(data);
				jQuery.ajax({
						type : "POST",
						url : baseUrl + "supplierInfo/saveSupplierInfo",
						data : data,
						contentType : "application/x-www-form-urlencoded",
						dataType : "json",
						success : function(data) {
							var supplierInfo = data.msg;
							window.location.href = baseUrl + "view/storeAccount/suplier";
							console.log(supplierInfo);
							if (jQuery("li[supplierId=" + supplierInfo.supplierId + "]").length == 1) {
								jQuery("li[supplierId=" + supplierInfo.supplierId + "]").attr("supplierName", supplierInfo.supplierName);
								jQuery("li[supplierId=" + supplierInfo.supplierId + "]").attr("linkName", supplierInfo.linkName);
								jQuery("li[supplierId=" + supplierInfo.supplierId + "]").attr("linkPhone", supplierInfo.linkPhone);
								jQuery("li[supplierId=" + supplierInfo.supplierId + "]").children(".supplier_manage_brand_content_left_ul").empty();
								var html = '<p>供应商：'+supplierInfo.supplierName+'<span onclick="updateSupper('+supplierInfo.supplierId+', this)">修改</span></p>';
								html += '<p>地址：'+supplierInfo.linkName+'</p><p>联系方式：'+supplierInfo.linkPhone+'</p>';
								jQuery("li[supplierId=" + supplierInfo.supplierId + "]").children(".supplier_manage_brand_content_left_ul").append(jQuery(html));
							} else {
								var html = '<li supplierId="'+supplierInfo.supplierId +'" supplierName="'+supplierInfo.supplierName +'" linkName="'+supplierInfo.linkName +'" linkPhone="'+supplierInfo.linkPhone +'" >'
										+ '<div class="supplier_manage_brand_content_left_ul ">';
								html += '<p>供应商：'+supplierInfo.supplierName+'<span onclick="updateSupper('+supplierInfo.supplierId+', this)">修改</span></p>';
								html += '<p>地址：'+supplierInfo.linkName+'</p><p>联系方式：'+supplierInfo.linkPhone+'</p>';
								html += '</div></li>';
								jQuery(".supplier_manage_brand_content_left").append(jQuery(html));
							}
							jQuery(".zzc").hide('800');
						}
					});
			}
			function updateBrand(opt, id) {
				jQuery(".zzc1").show('800');
				supplierId = jQuery(opt).parents("ul[supplierId]").attr("supplierId");
				jQuery("input[name='brandId']").val(id);
				jQuery("input[name='brandName']").val(jQuery(opt).parents("li[brandId]").attr("brandName"));
			}
			function saveBrand() {
				var brandId = jQuery("input[name='brandId']").val();
				var brandName = jQuery("input[name='brandName']").val();
				if (brandName==""){dialog("名称可不能为空哦");return;}
				if (brandId == "") {
					data = "brandName=" + brandName + "&supplierId="
							+ supplierId;
				} else {
					data = "brandName=" + brandName + "&supplierId="
							+ supplierId + "&brandId=" + brandId;
				}
				jQuery.ajax({
							type : "POST",
							url : baseUrl + "goodsInfo/save/brand",
							data : data,
							contentType : "application/x-www-form-urlencoded",
							dataType : "json",
							success : function(data) {
								var brand = data.msg;
								if (jQuery("li[brandId=" + brand.brandId + "]").length == 1) {
									jQuery("li[brandId=" + brand.brandId + "]").remove();
								}
								var html = '<li brandId="'+brand.brandId+'" brandname="'+brand.brandName+'">'+brand.brandName+'<p style="display: none;">'+
													'<span onclick="updateBrand(this, '+brand.brandId+')">修改</span> <span onclick="deleted(this, '+brand.brandId+')">删除</span>'+
												'</p>'+
											'</li>';
								jQuery("ul[supplierId='"+supplierId+"']").children("li[class='a']").before(jQuery(html));
								/* if (jQuery("li[brandId=" + brand.brandId + "]").length == 1) {
									jQuery("li[brandId=" + brand.brandId + "]").remove();
									jQuery("li[brandId=" + brand.brandId + "]").attr("brandName", brand.brandName);
									jQuery("li[brandId=" + brand.brandId + "]").children("input").attr("placeholder", brand.brandName);
								} else {
									var html = '<li brandId="'+brand.brandId+'" brandname="'+brand.brandName+'">'+brand.brandName+'<p style="display: none;">'+
														'<span onclick="updateBrand(this, '+brand.brandId+')">修改</span> <span onclick="deleted(this, '+brand.brandId+')">删除</span>'+
													'</p>'+
												'</li>';
									jQuery("ul[supplierId='"+supplierId+"']").children("li[class='a']").before(jQuery(html));
								} */
								jQuery('.zzc1').hide('800');
								supplierId = null;
							}
						});
			}
			function showAdd(id) {
				jQuery('.zzc1').show('800');
				supplierId = id;
				jQuery("input[name='brandId']").val("");
				jQuery("input[name='brandName']").val("");
			}
			function deleted(opt, id) {
				var data = "brandId=" + id;
				jQuery.ajax({
					type : "POST",
					url : baseUrl + "goodsInfo/deleteGoodsBrand",
					data : data,
					dataType : "json",
					success : function(data) {
						jQuery(opt).parents("li[brandId]").hide('800');
					}
				});
			}
			function deleteSupper(id, index){
				var length = jQuery(".supplier_manage_brand_content_right").children("ul").children("li").eq(index).children("ul").find("li").length;
				if (length > 1){
					dialog("该供应商下拥有品牌, 不可删除");
					return;
				}
				var data = "supplierId="+id;
				jQuery.ajax({
					type : "POST",
					url : baseUrl + "goodsInfo/deleteSuplier",
					data : data,
					dataType : "json",
					success : function(data) {
						if (data.code!=-1) window.location.href = baseUrl + "view/storeAccount/suplier";
					}
				});
			}
		</script></body>
</html>