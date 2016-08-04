<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ include file="/head.jsp"%>
<%
    String qiniu = "http://7xss26.com1.z0.glb.clouddn.com/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="<%=basePath%>css/shop_data.css" type="text/css" />

<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right clearfix">
					<div class="shop_data_head">
						<input type="text" placeholder="名称/编号" name="queryname" style="padding-right: 20px; padding-left: 10px">
						<img src="<%=basePath%>images/seach.png"> 供应商
						<select name="querysupplierid" onchange="selectquerybrand(this.value)">
							<option value="0">选择一个供应商</option>
							<c:forEach items="${supplierInfoDtos }" var="supplierInfo"><option value="${supplierInfo.supplierId }">${supplierInfo.supplierName }</option></c:forEach>
						</select>
							品牌
						<select name="querybrandid">
							<option value="0">选择一个品牌</option>
							<c:forEach items="${supplierInfoDtos[0].brands }" var="brand"><option value="${brand.brandId }">${brand.brandName }</c:forEach>
						</select>
						<button onclick="query()">搜索</button>
					</div>
					<div class="shop_data_content">
						<table id="ag">
							<tbody>
								<tr class="tr">
									<td>商品编号</td>
									<td>商品名称</td>
									<td>是否卖品</td>
									<td>成本价（元）</td>
									<td>库存量</td>
									<td>供应商</td>
									<td>品牌</td>
								</tr>
								<c:forEach items="${page.results }" var="accountGood">
									<tr goodsStock="${accountGood.goodsStock }" goodsDesc="${accountGood.goodsDesc }" goodsId="${accountGood.aId }" isSellProduct="${accountGood.isSellProduct }"  supplierId="${accountGood.supplierId }" brandId="${accountGood.brandId }">
										<td>${accountGood.goodsCodeSuffix }</td>
										<td>${accountGood.goodsName }</td>
										<c:if test="${accountGood.isSellProduct == 1 }"><td>是</td></c:if>
										<c:if test="${accountGood.isSellProduct == 0 }"><td>否</td></c:if>
										<td>${accountGood.costPrice }</td>
										<td>${accountGood.goodsStock }</td>
										<td>${accountGood.supplierName }</td>
										<td>${accountGood.brandName }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				<%@ include file="/template/page.jsp" %>
				</div>
			</div>
		</div>
	</div>

	<div class="zzc" style="display: none">
	  <div class="zzc_shop_data">
	    <p>新建商品</p>
	    <div class="zzc_shop_data_content">
	      <p><span><em>商品编号</em><input type="text" maxlength="5" name="goodsCodeSuffix"></span><span><em>商品名称</em><input name="goodsName"  type="text"></span></p>
	      <p>是否非卖品 <input type="radio" name="isSellProduct" checked="checked" value="1">是<input type="radio" value="0" name="isSellProduct" >否</p>
		  <p><span><em>成本价</em><input name="costPrice" type="text"><i>元</i></span></p>
		  <p><span><em>供应商</em><select name="supplierId" onchange="selectBrand(this.value)">
								<c:forEach items="${supplierInfoDtos }" var="supplierInfo"><option value="${supplierInfo.supplierId }">${supplierInfo.supplierName }</option></c:forEach>
							</select></span>
							<span><em>品牌</em><select name="brandId">
								<c:forEach items="${supplierInfoDtos[0].brands }" var="brand"><option value="${brand.brandId }">${brand.brandName }</c:forEach>
							</select></span>
							</p>
	      <div class="shop_data_saying">
		    <div>备注</div>
		    <textarea name="goodsDesc"></textarea>
		  </div>
		  <div class="shop_data_button">
		    <button onclick="save()">确定</button>
			<button onclick="jQuery('.zzc').modal('hide');goodsId = null;">取消</button>
		  </div>
		</div>
	  </div>
	</div>
	
</body>

<script>
	var storeAccount = '${session_key_store_account}';
	var supplierInfoDtosJs = ${supplierInfoDtosJs };
	var goodsId = null;
	var storeId = '${storeId }';

	function selectBrand(id) {
		jQuery("select[name='brandId']").empty();
		for (var i = 0; i < supplierInfoDtosJs.length; i++) {
			if (supplierInfoDtosJs[i].supplierId == id) {
				for (var j = 0; j < supplierInfoDtosJs[i].brands.length; j++) {
					var brandName = supplierInfoDtosJs[i].brands[j].brandName;
					var brandId = supplierInfoDtosJs[i].brands[j].brandId;
					var html = '<option value='+brandId+'>' + brandName
							+ '</option>';
					jQuery("select[name='brandId']").append(jQuery(html));
				}
			}
		}
	}
	function selectquerybrand(id){
		jQuery("select[name='querybrandid']").empty();
		var html = '<option value="0">请选择一个品牌</option>';
		jQuery("select[name='querybrandid']").append(jQuery(html));
		for (var i = 0; i < supplierInfoDtosJs.length; i++) {
			if (supplierInfoDtosJs[i].supplierId == id) {
				for (var j = 0; j < supplierInfoDtosJs[i].brands.length; j++) {
					var brandName = supplierInfoDtosJs[i].brands[j].brandName;
					var brandId = supplierInfoDtosJs[i].brands[j].brandId;
					var html = '<option value='+brandId+'>' + brandName
							+ '</option>';
					jQuery("select[name='querybrandid']").append(jQuery(html));
				}
			}
		}
	}
	function queryGoods(id, opt) {
		goodsId = id;
		var isSellProduct = jQuery(opt).parents("tr").attr("isSellProduct");
		var goodsDesc = jQuery(opt).parents("tr").attr("goodsDesc");
		var supplierId = jQuery(opt).parents("tr").attr("supplierId");
		var brandId = jQuery(opt).parents("tr").attr("brandId");
		var goodsCodeSuffix = jQuery(opt).parents("tr").children("td").eq(0)
				.text();
		var goodsName = jQuery(opt).parents("tr").children("td").eq(1).text();
		var costPrice = jQuery(opt).parents("tr").children("td").eq(3).text();
		goodsStock = jQuery(opt).parents("tr").attr("goodsStock");

		jQuery("input[name='goodsName']").val(goodsName);
		jQuery("input[name='goodsCodeSuffix']").val(goodsCodeSuffix);
		jQuery("select[name='supplierId']").val(supplierId);
		selectBrand(supplierId);
		jQuery("select[name='brandId']").val(brandId);
		jQuery("input[name='costPrice']").val(costPrice);
		jQuery("textarea[name='goodsDesc']").val(goodsDesc);
		jQuery('input:radio[name="isSellProduct"][value="' + isSellProduct + '"]').click();
		jQuery(".zzc").modal();
	}
	
	var isQuery = false;
	/** 模糊查询*/
	function query(){
		isQuery = true;
		pageNo = 1;
		changePage();
	}

	/**分页查询*/
	function changePage() {
		var data = "pageNo=" + pageNo + "&pageSize=" + pageSize + "&storeId=" + storeId;
		if (jQuery("input[name='queryname']").val()!="") data += "&queryname=" + jQuery("input[name='queryname']").val();
		if (jQuery("select[name='querysupplierid']").val()!="0") data += "&querysupplierid=" + jQuery("select[name='querysupplierid']").val();
		if (jQuery("select[name='querybrandid']").val()!="0") data += "&querybrandid=" + jQuery("select[name='querybrandid']").val();
		jQuery.ajax({
			type : "post",
			url : baseUrl + "goodsInfo/view/setting/page",
			data : data,
			dataType : "json",
			success : function(e) {
				if (e.code == 0) {
					initTable(e);
					pageNo = e.msg.pageNo;
					pageSize = e.msg.pageSize;
					totalPage = e.msg.totalPage;
					totalRecord = e.msg.totalRecord;
					if(isQuery){
						isQuery = false;
						unbuildPagination();
					}
				} else {
					dialog('查询错误,请稍后重试');
				}
			}
		});
	}
	function initTable(e) {
		jQuery("tbody").empty();
		var tr = '<tr class="tr">' + '<td>商品编号</td>' + '<td>商品名称</td>'
				+ '<td>是否卖品</td>' + '<td>成本价（元）</td>' + '<td>库存量</td>'
				+ '<td>供应商</td>' + '<td>品牌</td>' + '</tr>';
		jQuery("tbody").append(jQuery(tr));
		var isSell = ['否', '是'];
		for (var i = 0; i < e.msg.results.length; i++) {
			var goodsCodeSuffix = e.msg.results[i].goodsCodeSuffix;
			var goodsStock = e.msg.results[i].goodsStock;
			var goodsDesc = e.msg.results[i].goodsDesc;
			var goodsPrice = e.msg.results[i].goodsPrice;
			var goodsName = e.msg.results[i].goodsName;
			var goodsId = e.msg.results[i].goodsId;
			var costPrice = e.msg.results[i].costPrice;
			var isSellProduct = e.msg.results[i].isSellProduct;
			var supplierId = e.msg.results[i].supplierId;
			var supplierName = e.msg.results[i].supplierName;
			var brandId = e.msg.results[i].brandId;
			var brandName = e.msg.results[i].brandName;
			if (goodsStock == null) {
				goodsStock = "";
			}
			var html = '<tr goodsstock="'+goodsStock+'" goodsdesc="'+goodsDesc+'" goodsid="'+goodsId+'" issellproduct="'+isSellProduct+'" supplierid="'+supplierId+'" brandid="'+brandId+'">'
					+ '<td>'
					+ goodsCodeSuffix
					+ '</td>'
					+ '<td>'
					+ goodsName
					+ '</td>'
					+ '<td>'
					+ isSell[isSellProduct]
					+ '</td>'
					+ '<td>'
					+ costPrice
					+ '</td>'
					+ '<td>'
					+ goodsStock
					+ '</td>'
					+ '<td>'
					+ supplierName
					+ '</td>'
					+ '<td>'
					+ brandName
					+ '</td>'
					+ '</tr>';
			jQuery("tbody").append(jQuery(html));
		}
	}
</script>
</html>