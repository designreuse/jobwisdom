<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 分页 -->
<div class="fenye">
	<span>共找到了 ${page.totalRecord } 条数据, 共 ${page.totalPage } 页</span>
	<ul id="pagination-demo" class="pagination pagination-sm">
	</ul>
</div>

<script type="text/javascript">
	var pageNo = '${page.pageNo}';
	var pageSize = '${page.pageSize}';
	var totalPage = '${page.totalPage}';
	var totalRecord = '${page.totalRecord}';
	var isFalsh = false;
	function initPageClick() {
		jQuery('#pagination-demo').prev().text('共找到了 '+totalRecord+' 条数据, 共 '+totalPage+' 页');
		if(totalPage!=0){
			jQuery('#pagination-demo').twbsPagination({
				totalPages : totalPage,
				visiblePages : 5,
				onPageClick: function (event, page) {
					pageNo = page;
					if(isFalsh){
						isFalsh=false;
					}else{
						changePage();
					}
		        } 
			});
		}
	}
	initPageClick();
    //重新解绑,绑定,当数据重新书写,也就是进行了查询相干的操作,就要重新绑定分页插件
	function unbuildPagination(){
		isFalsh = true;
		jQuery('#pagination-demo').empty();
		jQuery('#pagination-demo').removeData("twbs-pagination");
		jQuery('#pagination-demo').unbind("page");
		initPageClick();
	}
</script>
<!-- 分页 -->