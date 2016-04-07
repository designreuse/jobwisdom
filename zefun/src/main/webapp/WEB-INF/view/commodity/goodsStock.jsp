<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/head.jsp" %>
<body>

<div class="mainwrapper">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
      	<%@ include file="/top.jsp" %>
      	<!-- 页面代码 -->
      	
		<div class="maincontent">
		    <div class="contentinner">
		        <div class="more-toolbar" >
                    <div class="table-toolbar">
                        <form id="goodsForm">
                            	商品库存信息
	                        <input type="search" placeholder="商品名称/类别/品牌" name="goodsStockName" />
	                        <a href="javascript:void(0)" class="button-search btn" style="margin-left: -10px;" onclick="findByGoodsName(this);">查询</a>
                        </form>
                    </div>
                    <div class="clearfix"></div>
                </div>
		        
		        <table class="table table-bordered table-striped goodsStock-table">
		            <thead>
			            <tr>
			                <th>商品类别</th>
			                <th>商品品牌</th>
			                <th>货品名称</th>
			                <th>平均成本</th>
			                <th>上批成本</th>
			                <th onclick="orderGoodsInfo('goods_price')">销售价格
					          <div class="paixu">
		                      <i class="FontAwesome iconfa-caret-up afont ml8"></i>
		                      <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
		                      </div>
		                    </th>
			                <th onclick="orderGoodsInfo('goods_stock')">当前库存
			                  <div class="paixu">
		                      <i class="FontAwesome iconfa-caret-up afont ml8"></i>
		                      <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
		                      </div>
			                </th>
			                <th onclick="orderGoodsInfo('warn_stock')">报警数量
			                  <div class="paixu">
		                      <i class="FontAwesome iconfa-caret-up afont ml8"></i>
		                      <i class="FontAwesome iconfa-caret-down afont ml8" style="position: absolute;"></i>
		                      </div>
			                </th>
			            </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var="goodsInfoDto" items="${page.results }" varStatus="status">
		            		<tr>
				            	<td>${goodsInfoDto.categoryName }</td>
				            	<td>${goodsInfoDto.brandId }</td>
				            	<td>${goodsInfoDto.goodsName }</td>
				            	<td>
				            		${fn:substring(goodsInfoDto.goodsPurchaseRecordDto.avgCost,0,5)}
				            	</td>
				            	<td>
				            		${fn:substring(goodsInfoDto.goodsPurchaseRecordDto.prevCost,0,5)}
				            	</td>
				            	<td>${goodsInfoDto.goodsPrice }</td>
				            	<td>${goodsInfoDto.goodsStock }</td>
				            	<td>${goodsInfoDto.warnStock }</td>
			            	</tr>
		            	</c:forEach>
		            </tbody>
		        </table>
		        <%@ include file="/template/page.jsp" %>
		        <div class="clearfix"></div><br/>
		    </div>
		</div>
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<div class="modal hide" id="select-wordimg-modal-picture3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content select-wordimg-modal" style="width: 580px;">
            
        </div>
    </div>
</div>

<script>
	//获取加载页面时的页码信息
	var pageNo = "${page.pageNo}";
	var pageSize = "${page.pageSize}";
	var totalPage = "${page.totalPage}";
</script>
<script src="<%=basePath %>js/commodity/goodsStock.js"></script>
<script type="text/javascript">
function orderGoodsInfo(type){
	orderBy = type;
	changePage();
}
</script>
</body>
</html>