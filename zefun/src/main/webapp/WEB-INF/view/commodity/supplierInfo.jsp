<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath%>css/seo-1.css" type="text/css" />
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right">
					<div class="add_new_">
						<p>新增供应商</p>
						<table class="seo-table">
							<thead>
				                <tr>
				                    <td>供应商名称</td>
				                    <td>经营品牌</td>
				                    <td>商品类别</td>
				                    <td>联系人</td>
				                    <td>手机号</td>
				                </tr>
			                </thead>
			                <form id="supplierInfoForm">
							<tbody>
								<tr>
				                    <td>
				                    	<input type="text" class="input80" name="supplierName" id="supplierName" />
				                    	<input type="hidden" class="input80" name="supplierId" id="supplierId"/>
				                    </td>
				                    <td>
				                        <select style="width:185px" data-placeholder="选择品牌" class="chzn-select input-xlarge" name="supplyBrand" id="supplyBrand" multiple="multiple" ">
				                            <c:forEach var="goodsBrand" items="${goodsBrandList }" varStatus="status">
				                        		<option value="${goodsBrand.codeNo }">${goodsBrand.codeName }</option>
				                        	</c:forEach>
				                        	<c:forEach var="goodsCategory" items="${goodsBrands }" varStatus="status">
				                        		<option value="${goodsCategory.brandId }">${goodsCategory.brandName }</option>
				                        	</c:forEach>
				                        </select>
				                    </td>
				                    <td>
				                        <select style="width:185px" data-placeholder="选择商品类别" class="chzn-select input-xlarge" name="supplyCategory" id="supplyCategory" multiple="multiple">
				                            <c:forEach var="goodsCategory" items="${goodsCategoryList }" varStatus="status">
				                        		<option value="${goodsCategory.categoryId }">${goodsCategory.categoryName }</option>
				                        	</c:forEach>
				                        </select>
				                    </td>
				                    <td class="expend"><input type="text" class="input80" name="linkName" id="linkName" /></td>
				                    <td><input type="text" class="input80" name="linkPhone" id="linkPhone" />
				                </tr>
							</tbody>
							</form>
						</table>
						<button id="submitBtn" onclick="saveSupplierInfo()" class="seo-1-save">保存</button>
					</div>
					<div class="table_hidden">
						<table class="seo-1-list" style="width:100%">
							<thead>
								<tr>
									<td>供应商名称</td>
									<td>经营品牌</td>
									<td>供货类别</td>
									<td>联系人</td>
									<td>手机号</td>
									<td>操作</td>
								</tr>
							</thead>
							<tbody id="showCoupon">
							<c:forEach var="supplierInfo" items="${page.results }" varStatus="status">
		            		<tr>
				                <td>${supplierInfo.supplierName }</td>
				                <td>${supplierInfo.supplyBrandStr }</td>
				                <td>${supplierInfo.supplyCategoryStr }</td>
				                <td>${supplierInfo.linkName }</td>
				                <td>${supplierInfo.linkPhone }</td>
				                <td>
				                	<i class="iconfa-pencil project-icon" onclick="showEditSupplierInfo(${supplierInfo.supplierId});"></i>
			                        <i class="iconfa-trash project-icon" onclick="deleteSupplierInfo(${supplierInfo.supplierId});"></i>
				                </td>
				            </tr>
		            	</c:forEach>
							</tbody>
						</table>
						<%@ include file="/template/page.jsp" %>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--mainwrapper-->

	<script>
		//获取加载页面时的页码信息
		var pageNo = "${page.pageNo}";
		var pageSize = "${page.pageSize}";
		var totalPage = "${page.totalPage}";
	</script>
	<script src="<%=basePath%>js/commodity/supplierInfo.js"></script>

</body>
</html>