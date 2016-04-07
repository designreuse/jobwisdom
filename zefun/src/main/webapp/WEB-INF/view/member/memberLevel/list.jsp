<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		    	<c:set var="hasModify" value="0" />
		    	<c:if test="${session_key_role_id == 101 || session_key_role_id == 103 }">
		    		<c:set var="hasModify" value="1" />
		    	</c:if>
		         <c:if test="${hasModify == 1 }">
			        <div class="widgetcontent">
			            <div class="more-toolbar" >
			                <div class="table-toolbar">
			                    <span class="font-size-16 btn-color mr10">添加会员卡种类</span>
			                    <button class="btn" data-toggle="modal" data-target="#toLeadModal">导入会员卡</button>
			                </div>
			                <div class="clearfix"></div>
			            </div>
			            <form class="editMemberLevelForm">
				            <table class="table collect-money-table">
				                <thead>
				                <tr>
				                    <th>会员卡名称</th>
				                    <th>项目折扣<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="折后价为原价乘以设置的百分比，不打折请填写100"></th>
				                    <th>商品折扣<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="折后价为原价乘以设置的百分比，不打折请填写100"></th>
				                    <th>最低充值</th>
				                    <th>开卡费用<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="类似折扣卡，开卡时需要支付该费用且不纳入充值金额"></th>
				                    <th>现金支付打折<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="此卡会员在使用现金支付时是否享受折扣"></th>
				                    <th>业绩折扣比例<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="类似原价卡需要扣减员工业绩与提成时设置此值，100为不减扣"></th>
				                </tr>
				                </thead>
				                <tbody>
				                <tr>
				                    <td><input type="text" class="input80" name="levelName" /></td>
				                    <td><input type="number" class="input80" name="projectDiscount" /><span class="percent-symbol">%</span></td>
				                    <td><input type="number" class="input80" name="goodsDiscount" /><span class="percent-symbol">%</span></td>
				                    <td><input type="number" class="input80" name="chargeMinMoney" /><span class="percent-symbol">元</span></td>
				                    <td><input type="number" class="input80" value="0" name="sellAmount" /><span class="percent-symbol">元</span></td>
				                    <td>
				                    	<select name="cashDiscountType" class="chzn-select wthn100">
					                        <option value="0">不打折</option>
					                        <option value="1">打折</option>
				                    	</select>
				                    </td>
				                    <td><input type="number" class="input80" value="100" name="performanceDiscountPercent" /><span class="percent-symbol">%</span></td>
				                </tr>
				                <tr>
				                    <td colspan="8">
				                        <div class="p-part-first">
				                            <span class="mr10 label12 font-bold">积分计算方式：</span>
				                            每消费<input type="number" name="integralUnit" class="input30" value="1"><span class="percent-symbol">元</span>
				                            <span class="ml10">获得</span>
				                            <input type="number" name="integralNumber" class="input30" value="1"><span class="percent-symbol">分</span>
				                        </div>
				                    </td>
				                </tr>
				                <tr>
				                	<td colspan="6">
				                        <span class="mr10 label12 font-bold">会员须知<img src="<%=picPath %>help.png" alt="" class="ml5" data-toggle="tooltip" data-placement="top" title="" data-original-title="会员须知在会员的等级信息中会显示，您可以设置类似本店不能退卡之类的说明">：</span>
				                        <div id="editor_id" contenteditable="true" class="shuru"></div>
				                    </td>
				                    <td colspan="2">
				                    	<a class="btn btn-primary fr" onclick="addOrEditMemberLevel()">&nbsp;保&nbsp;&nbsp;存&nbsp;</a>
				                        <a class="btn btn-primary fr mr10" onclick="resetForm('.editMemberLevelForm');">&nbsp;清&nbsp;&nbsp;空&nbsp;</a>
				                    </td>
				                </tr>
				                </tbody>
				            </table>
				            <input type="hidden" name="levelId" style="width: 0px; height: 0px;" >
				        </form>
			        </div>
		        </c:if>
		
		        <div class="more-toolbar" >
		            <div class="table-toolbar">
		                <span class="font-size-16 btn-color mr10">当前会员卡种类</span>
		            </div>
		            <div class="clearfix"></div>
		        </div>
		
		        <table class="table table-bordered table-striped member-card-table">
		            <thead class="t-fix">
		              <tr>
		                  <th>会员卡名称</th>
		                  <th>项目折扣</th>
		                  <th>商品折扣</th>
		                  <th>最低充值</th>
		                  <th>开卡费用</th>
		                  <th>现金支付打折</th>
		                  <th>业绩折扣比例</th>
		                  <th>积分计算方式</th>
		                  <th>等级说明</th>
		                  <c:if test="${hasModify == 1 }">
		                      <th>操作</th>
		                  </c:if>
		              </tr>
		            </thead>
		            <tbody>
			            <c:forEach items="${page.results }" var="memberLevel" varStatus="index">
			            	 <c:if test="${index.index == 0 }"><tr id="${memberLevel.levelId }" class="t-table"></c:if>
			            	 <c:if test="${index.index != 0 }"><tr id="${memberLevel.levelId }"></c:if>
                               <td>${memberLevel.levelName }
                                <c:if test="${memberLevel.isDefault == 1 }"><span class="font-999">默认等级</span></c:if>
                               </td>
                               <td>${memberLevel.projectDiscount }%</td>
                               <td>${memberLevel.goodsDiscount }%</td>
                               <td>${memberLevel.chargeMinMoney }元</td>
                               <td>
                               		${memberLevel.sellAmount }元
                               </td>
                               <td>
                               		<c:choose>
                               			<c:when test="${memberLevel.cashDiscountType == 0 }">
                               				不打折
                               			</c:when>
                               			<c:otherwise>
                               				打折
                               			</c:otherwise>
                               		</c:choose>
                               </td>
                               <td>
                               		${memberLevel.performanceDiscountPercent }%
                               </td>
                               <td>${memberLevel.integralUnit }元 = ${memberLevel.integralNumber }积分</td>
                               <td class="input80 ellipsis-text">
                               		<c:if test="${not empty memberLevel.levelNoticeList }">
                               			<c:forEach var="content" items="${memberLevel.levelNoticeList }">
                               				${content.text }
                               			</c:forEach>
                               		</c:if>
                               		&nbsp;
                               </td>
                               <c:if test="${hasModify == 1 }">
	                               <td>
	                                   <span class="iconfont icon-dizhixiugai" onclick="editMemberLevel(${memberLevel.levelId})"></span>
	                                   <c:if test="${memberLevel.isDefault == 0 }">
		                                   <span class="iconfont icon-xx ml10" onclick="deleteMemberLevel(${memberLevel.levelId})"></span>
	                                   </c:if>
	                               </td>
                               </c:if>
                             </tr>
			            </c:forEach>
		            </tbody>
		        </table>
		        <%@ include file="/template/page.jsp" %>
		    </div>
		</div>
    </div>
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->
<div class="modal hide in" id="toLeadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account" style="width: 450px;height: 180px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h5 class="modal-title" id="myModalLabel">上传文件</h5>
            </div>
            <div class="modal-body" style="height: 150px;">
                <form action="" class="editprofileform" method="post" enctype="multipart/form-data" id="excleForm">
                    <p>
                        <label>文件位置选择:</label>
                        <input type="file" name="filename" id="file" class="input-large">
                        <label>之前的服务商:</label>
                        <select data-placeholder="服务商名称" class="chzn-select" name="storeName">
			            		<option value="盛传">盛传服务商</option>
			            </select>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn btn-primary modal-confirm" href="#" id="confirm" data-dismiss="modal" onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>
<script>
/*表头置顶*/
jQuery(function(){
  var a=[];
    jQuery(".mainwrapper").show()
    var tlength=jQuery(".t-fix th").length;
        for(i=0;i<tlength;i++)  {
        a[i]=jQuery(".t-fix th").eq(i).width();
    }

   function table() {
       jQuery(".mainwrapper").show()
       var fix=jQuery(".t-fix").offset()
       var tableT=fix.top
       jQuery(window).scroll(function(event){
           var scH=jQuery(window).scrollTop()
           if(scH>tableT){
               jQuery(".t-fix").addClass("add-fix")
               for(i=0;i<jQuery(".t-fix th").length;i++){
                   var tdwidth=a[i];
                   var tbwidth=a[i];
                   jQuery(".t-fix th").eq(i).css("width",tdwidth)
                   jQuery(".t-table td").eq(i).css("width",tbwidth)

               }
           }
           else{
               jQuery(".t-fix").removeClass("add-fix")
           }
       })
   }
    table();
})
    //获取加载页面时的页码信息
    var pageNo = "${page.pageNo}";
    var pageSize = "${page.pageSize}";
    var totalPage = "${page.totalPage}"
    //session中的角色id
    var hasModify = "${hasModify}";
</script>
<script type="text/javascript" src="<%=basePath %>js/member/memberLevel.js"></script>
<script type="text/javascript">
	//手动更新选择项，两步顺序执行,
	jQuery("#registerCommissionType").val(1);
	jQuery("#registerCommissionType").trigger("liszt:updated");
	//获取选择项的值
	jQuery("#registerCommissionType :selected").val();
	//参考api：http://harvesthq.github.io/chosen/#change-update-events
	
	
function UpladFile() {
    var fileObj = document.getElementById("file").files[0]; // 获取文件对象
    var FileController = baseUrl +"memberLevel/action/importexcle";                    // 接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("storeName", jQuery("select[name='storeName']").val());       
    form.append("file", fileObj);    // 文件对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (e) {
    	/*dialog(xhr.responseText);*/
    	var json = eval("("+xhr.responseText+")");
    	if(json.code!=0){
    		dialog(json.msg);
    		return;
    	}
        dialog(json.msg);
        setTimeout(function(){
        	location.reload();
    	},300);
       
    };
    xhr.send(form);
}
</script>
</body>
</html>