<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 分页 -->
<div class="fenye">
	<span></span>
	<ul id="pagination-demo" class="pagination pagination-sm">
	</ul>
</div>

<script type="text/javascript">
	var pageNoOfRewardsDetail = 1;
	var pageSizeOfRewardsDetail = 50;
	var isFalsh = false;
	//初始化奖惩详情页码信息(当用户关掉奖惩详情模态框)
	function initPageInfoOfRewardsDetail() {
		pageNoOfRewardsDetail = 1;
		pageSizeOfRewardsDetail = 50;
	}
	function initPageClick(totalRecordOfRewardsDetail, totalPageOfRewardsDetail) {
		//alert("initPageClick isFalsh : " + isFalsh);
		jQuery('#pagination-demo').prev().text('共找到了 '+totalRecordOfRewardsDetail+' 条数据, 共 '+totalPageOfRewardsDetail+' 页');
		if(totalPageOfRewardsDetail != 0){
			jQuery('#pagination-demo').twbsPagination({
				totalPages : totalPageOfRewardsDetail,
				visiblePages : 5,
				onPageClick: function (event, page) {
					//alert("page wenjian : " + page);
					pageNo = page;
					if(isFalsh){
						isFalsh=false;
					}else{
						rewardDetail(jQuery("#typeOfDetail").val(), jQuery("#employeeId").val(), pageNo);
					}
		        } 
			});
		}
	}
	//rule.jsp页面刚刚加载时执行
	//initPageClick(totalRecordOfRewardsDetail, totalPageOfRewardsDetail);
    //重新解绑,绑定,当数据重新书写,也就是进行了查询相干的操作,就要重新绑定分页插件
	function unbuildPagination(totalRecordOfRewardsDetail, totalPageOfRewardsDetail) {
		//alert("unbuildPagination isFalsh : " + isFalsh);
		isFalsh = true;
		jQuery('#pagination-demo').empty();
		jQuery('#pagination-demo').removeData("twbs-pagination");
		jQuery('#pagination-demo').unbind("page");
		initPageClick(totalRecordOfRewardsDetail, totalPageOfRewardsDetail);
	}
</script>
<!-- 分页 -->