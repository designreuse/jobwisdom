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
		        <div class="widgetcontent">
                    <div class="more-toolbar" >
                        <div class="table-toolbar">
                            <label for="">新增进货记录</label>
                        </div>
                        <div class="clearfix"></div>
                    </div><!--more-toolbar-->
                    <form id="purchaseRecodesForm">
                        <table class="table collect-money-table">
                            <thead>
                                <tr>
                                    <th>供应商名称</th>
                                    <th>商品</th>
                                    <th>进货价格</th>
                                    <th>进货数量</th>
                                    <th>进货时间</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>
                                        <select data-placeholder="选择供应商" class="chzn-select input-medium" name="supplierId">
                                            <c:forEach var="supplierInfo" items="${supplierInfoList }" varStatus="status">
                                                <option value="${supplierInfo.supplierId }">${supplierInfo.supplierName }</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <select data-placeholder="选择商品" class="chzn-select input-medium" name="goodsId">
                                            <c:forEach var="goodsInfo" items="${goodsInfoList }" varStatus="status">
                                                <option value="${goodsInfo.goodsId }">${goodsInfo.goodsName }</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><input type="text" class="input80" name="purchasePrice" datatype="n" errormsg="进货价：请输入大于1以上的数字！" nullmsg="请填写进货价！"/></td>
                                    <td><input type="number" class="input80" name="purchaseCount" datatype="n" errormsg="进货数量：请输入大于1以上的数字！" nullmsg="请填写进货数量！"/></td>
                                    <td><input id="recodrsTime" type="text" name="purchaseTime" class="timepicker width100"/>
                                    <button class="btn btn-primary input100 fr" type="button" id="submitBtn">&nbsp;保&nbsp;&nbsp;存&nbsp;</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
		        
		        <!-- 进货记录列表 -->
		        <div class="more-toolbar" >
                    <div class="table-toolbar">
                        <form>
                            	商品进货记录
                            <input type="search" placeholder="供应商/商品名称" name="goodsName" />
                            <a href="javascript:void(0)" class="button-search btn" style="margin-left: -10px;" onclick="findByNames();">查询</a>
                        </form>
                    </div>
                    <div class="clearfix"></div>
                </div>
				
		        <table class="table table-bordered table-striped goodsPurchaseRecord-table">
		            <thead>
		              <tr>
		                  <th>供应商名称</th>
		                  <th>商品</th>
		                  <th>进货价格</th>
		                  <th>进货数量</th>
		                  <th>进货时间</th>
		                  <th>进货人</th>
		              </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var="goodsPurchaseRecordDto" items="${purchaseRecordsPage.results }" varStatus="status">
		            		<tr>
				                <td>${goodsPurchaseRecordDto.supplierName }</td>
				                <td>${goodsPurchaseRecordDto.goodsName }</td>
				                <td>${goodsPurchaseRecordDto.purchasePrice }</td>
				                <td>${goodsPurchaseRecordDto.purchaseCount }</td>
				                <td>${goodsPurchaseRecordDto.purchaseTime }</td>
				                <td>${goodsPurchaseRecordDto.employeeName }</td>
				            </tr>
		            	</c:forEach>
		            </tbody>
		        </table>
		        
				<!-- 分页 -->
				<div class="fenye">
					<span>共找到了 ${purchaseRecordsPage.totalRecord } 条数据, 共 ${purchaseRecordsPage.totalPage } 页</span>
					<ul id="pagination-demo2" class="pagination pagination-sm">
					</ul>
				</div>
				
				<script type="text/javascript">
					var pageNoR = "${purchaseRecordsPage.pageNo}";
					var pageSizeR = "${purchaseRecordsPage.pageSize}";
					var totalPageR = "${purchaseRecordsPage.totalPage}";
					var totalRecordR = '${purchaseRecordsPage.totalRecord}';
					
					function initPageClick2() {
						
						jQuery('#pagination-demo2').twbsPagination({
							totalPages : totalPageR,
							visiblePages : 5,
							onPageClick: function (event, page) {
								pageNoR = page;
								changePageR();
					        } 
						});
						
						jQuery('#pagination-demo2').prev().text('共找到了 '+totalRecordR+' 条数据, 共 '+totalPageR+' 页');
					}
					initPageClick2();
				    //重新解绑,绑定,当数据重新书写,也就是进行了查询想干的操作,就要重新绑定分页插件
					function unbuildPagination2(){
						jQuery('#pagination-demo2').empty();
						jQuery('#pagination-demo2').removeData("twbs-pagination");
						jQuery('#pagination-demo2').unbind("page");
						initPageClick2();
					}
				</script>
				<!-- 分页 -->
		        
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
	var pageNo = "${goodsStockPage.pageNo}";
	var pageSize = "${goodsStockPage.pageSize}";
	var totalPage = "${goodsStockPage.totalPage}";
	
	var pageNoR = "${purchaseRecordsPage.pageNo}";
	var pageSizeR = "${purchaseRecordsPage.pageSize}";
	var totalPageR = "${purchaseRecordsPage.totalPage}";
</script>
<script src="<%=basePath %>js/commodity/goodsStock.js"></script>

</body>
</html>