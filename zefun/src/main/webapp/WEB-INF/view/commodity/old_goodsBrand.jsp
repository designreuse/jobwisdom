<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="/head.jsp" %>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
      	<!-- 页面代码 -->
      	
		<div class="maincontent">
		    <div class="contentinner">
		        <div class="widgetcontent">
                    <div class="more-toolbar" >
                        <div class="table-toolbar">
                            <label for="">新增品牌</label>
                        </div>
                        <div class="clearfix"></div>
                    </div><!--more-toolbar-->
                    <form id="purchaseRecodesForm">
                        <table class="table collect-money-table">
                            <thead>
                                <tr>
                                    <th>品牌名称</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td><input type="text" class="input80" name="brandName" /></td>
                                    <td><a class="btn btn-primary input100 fr" href="javascript:saveBrand()" type="button" >&nbsp;保&nbsp;&nbsp;存&nbsp;</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
		        
		        <div class="clearfix"></div><br/>
		        
		    	<!-- 商品品牌 -->
		        <div class="more-toolbar" >
                    <div class="table-toolbar">
                        <form>
                           	 商品品牌
                            <input type="search" placeholder="商品名称" name="brandName" id="brandName"/>
                            <a href="javascript:void(0)" class="button-search btn" style="margin-left: -10px;" onclick="findByBrandNames();">查询</a>
                        </form>
                    </div>
                    <div class="clearfix"></div>
                </div>
				
		        <table class="table table-bordered table-striped goodsPurchaseRecord-table">
		            <thead>
		              <tr>
		                  <th>品牌名称</th>
		                  <th>创建时间</th>
		                  <th>最后操作人</th>
		              </tr>
		            </thead>
		            <tbody id="refresh">
		            	<c:forEach var="goodsBrand" items="${page.results }" varStatus="status">
		            		<tr>
				                <td>${goodsBrand.brandName }</td>
				                <td>${goodsBrand.createTime }</td>
				                <td>${goodsBrand.employeeName }</td>
				            </tr>
		            	</c:forEach>
		            </tbody>
		        </table>
		        <%@ include file="/template/page.jsp" %>
		    </div>
		</div>
    </div>
    <!--RIGHT PANEL结束 -->
</div>

</div><!--mainwrapper-->

<script>
	//获取加载页面时的页码信息
	var pageNo = "${page.pageNo}";
	var pageSize = "${page.pageSize}";
	var totalPage = "${page.totalPage}";
</script>
<script>
var isContent = false;
var brandName;
function findByBrandNames(){
	isContent = true;
	brandName = jQuery("#brandName").val();
	changePage();
}
function saveBrand(){
	var brandName = jQuery("input[name='brandName']").val();
	if(brandName == ""){
		dialog("请输入品牌名称");
		return;
	}
	var date = "brandName="+brandName;
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl+"goodsInfo/save/brand",
        data: date,
        async: false,
        error: function(request) {
            dialog("Connection error");
        },
        success: function(data) {
        	if(data.code!=0){
        		dialog(data.msg);
        		return;
        	}
        	dialog("新增成功");
        	location.reload(true);
        	var records = data.msg.results;
        	for (var i = 0; i < records.length; i++) {
        		var str= '<tr><td>'+records[i].brandName+'<td>'+records[i].createTime+' </td><td>'+records[i].employeeName+' </td></tr>';
        		jQuery("#refresh").append(str);
        	}
        }
    });
}
/** 进货记录分页 */
//上一页
function previous(){
	if(pageNo <= 1){
		return;
	}
	pageNo--;
	changePage();
}

//下一页
function next(){
	if(pageNo >= totalPage){
		return;
	}
	pageNoB++;
	changePageB();
}

//更改每页显示数量
function changePageSize(size){
	pageNoB = 1;
	pageSizeB = size;
	jQuery(".perpage").html(size + " <span class='iconfa-caret-down afont'></span>");
	changePage();
}

//分页处理
function changePage(){
	var data = "pageNo=" + pageNo + "&pageSize=" + pageSize;
	if(brandName!=""&&typeof(brandName)!="undefined")data = data + "&brandName="+brandName;
	console.log(data);
	jQuery.ajax({
		type : "post",
		url : baseUrl + "goodsInfo/serch/brand",
		data : data,
		dataType : "json",
		success : function(e){
			refreshTableDataB(e.msg.results);
			pageNo = e.msg.pageNo;
			pageSize = e.msg.pageSize;
			totalPage = e.msg.totalPage;
			totalRecord = e.msg.totalRecord;
			if(isContent){
				unbuildPagination();
				isContent = false;
			}
		}
	});
}
function refreshTableDataB(records){
	jQuery("#refresh").empty();
	for (var i = 0; i < records.length; i++) {
		var str= '<tr><td>'+records[i].brandName+'<td>'+records[i].createTime+' </td><td>'+records[i].employeeName+' </td></tr>';
		jQuery("#refresh").append(str);
	}
}
</script>
</body>
</html>