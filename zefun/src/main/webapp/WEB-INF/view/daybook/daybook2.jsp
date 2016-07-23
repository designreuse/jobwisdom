<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ include file="/head.jsp" %>
<style>
	.ml4neg {
		margin-left: -4px;
	}
</style>
<body>

<div class="mainwrapper" style="background-position: 0px 0px;">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
      	<%@ include file="/top.jsp" %>
      	<div class="maincontent">
			<div class="contentinner">
	           <div class="widgetcontent">
	           	 <table class="table">
                     <tr>
                         <td>
                             <input id="ipt-search-phone" type="search" placeholder="水单号/手机号/员工工号"/>
                             <button class="btn search-button" onclick="btnSearchPhone();">立即搜索</button>
                         </td>
                         <td>
                             <input id="startDate" class="input120" type="text" name="date" placeholder="起始时间" daysOffset="0"/>
		                    -&nbsp;<input id="endDate" class="input120" type="text" name="date" placeholder="截止时间"  daysOffset="0"/>
		                     <button class="btn" onclick="btnSearchTime();">区间查询</button>
                         </td>
                         <td>
		                     <div class='ch-checker fl'>
	                              <div class='beau-checker'>
	                                <span class='iconfont icon-gou'></span>
	                              </div>
	                              <input type='checkbox' class='yellow-checker' name='isDeletedValue'/>
                             </div>
                             <span class="ml30">查询删除订单</span>
                         </td>
                     </tr>
                 </table>
	             <table class="table table-bordered ls-detai-table">
	              <thead>
		              <tr>
		                  <td colspan="7">营业收入</td>
		                  <td colspan="5">收入来源</td>
		              </tr>
	              </thead>
	              <tbody>
		              <tr>
		                  <th>现金</th>
		                  <th>银联</th>
		                  <th>微信</th>
		                  <th>支付宝</th>
		                  <th>团购</th>
		                  <th>挂账</th>
		                  <th>划卡</th>
		                  <th>项目劳动(<span id="projectSalesCount">${detailCount.projectSalesCount}</span>单)</th>
		                  <th>商品销售(<span id="goodsSalesCount">${detailCount.goodsSalesCount}</span>单)</th>
		                  <th>疗程销售(<span id="comboSalesCount">${detailCount.comboSalesCount}</span>单)</th>
		                  <th>开卡充值(<span id="cardSalesCount">${detailCount.cardSalesCount}</span>单)</th>
		                  <th>卡金赠送(<span id="presentCount">${detailCount.presentCount}</span>单)</th>
		              </tr>
		              <tr>
		                  <td><span id="cashAmount">${countBook.cashAmount }</span></td>
		                  <td><span id="unionpayAmount">${countBook.unionpayAmount }</span></td>
		                  <td><span id="wechatAmount">${countBook.wechatAmount }</span></td>
		                  <td><span id="alipayAmount">${countBook.alipayAmount }</span></td>
		                  <td><span id="groupAmount">${countBook.groupAmount }</span></td>
		                  <td><span id="debtAmount">${countBook.debtAmount }</span></td>
		                  <td><span id="cardAmount">${countBook.cardAmount }</span></td>
		                  <td><span id="projectSalesAmount">${detailCount.projectSalesAmount}</span></td>
		                  <td><span id="goodsSalesAmount">${detailCount.goodsSalesAmount}</span></td>
		                  <td><span id="comboSalesAmount">${detailCount.comboSalesAmount}</span></td>
		                  <td><span id="cardSalesAmount">${detailCount.cardSalesAmount}</span></td>
		                  <td><span id="presentAmount">${detailCount.presentAmount}</span></td>
		              </tr>
	              </tbody>
	            </table>
	            <div class="more-toolbar" >
	              <div class="table-toolbar">
	                  <!-- <span class="font-size-16 btn-color">流水详情</span> -->
	                  <div class="table-pagination">
	                        <table class="ls-detail fr">
	                            <tr>
	                                <td>总客数：</td>
	                                <td id="totalPersonCount">${countBook.count }</td>
	                                <td>应收款：</td>
	                                <td id="receivableAmount">${countBook.receivableAmount }</td>
	                                <td>礼金抵扣：</td>
	                                <td id="giftAmount">${countBook.giftAmount }</td>
	                                <td>优惠券抵扣：</td>
	                                <td id="couponAmount">${countBook.couponAmount }</td>
	                                <td>疗程抵扣：</td>
                                    <td id="comboAmount">${countBook.comboAmount }</td>
                                    <td>划卡：</td>
                                    <td id="cardAmount">${countBook.cardAmount }</td>
	                                <td>现金实收：</td>
	                                <td id="realPrice">${countBook.realPrice }</td>
	                            </tr>
	                        </table>
	                    </div>
	              </div><!--table-toolbar-->
	              <div class="clearfix"></div>
	            </div><!--more-toolbar-->
	            <table class="table table-bordered table-striped liushui-table">
	              <thead class="t-fix">
	              <tr>
	                  <th>流水单号</th>
	                  <th>顾客</th>
	                  <th class="cursor" onclick="changeOrderByType('timeOrder', this);">消费时间
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont  hide"></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th>服务内容</th>
	                  <th class="cursor" onclick="changeOrderByType('receivableOrder', this);">应收
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('cashOrder', this);">现金
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('unionpayOrder', this);">银联
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('netpayOrder', this);">网络
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('cardOrder', this);">卡金
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('comboOrder', this);">疗程
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('giftOrder', this);">礼金
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('couponOrder', this);">优惠券
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('grouppayOrder', this);">团购
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('debtOrder', this);">挂账
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
	                  <th class="cursor" onclick="changeOrderByType('realOrder', this);">实收
	                    <div class="paixu">
	                      <i class="FontAwesome iconfa-caret-up afont "></i>
	                      <i class="FontAwesome iconfa-caret-down afont" style="position: absolute;"></i>
	                    </div>
	                  </th>
					  <th>操作</th>
	              </tr>
	              </thead>
	              <tbody id="tbody-data">
	              <c:forEach var="daybook" items="${page.results}" varStatus="status">
	              	<c:if test="${status.index == 0 }"><tr class="t-table"></c:if>
	              	<c:if test="${status.index != 0 }"><tr></c:if>
	                  <td onclick="updateSelectOrder(${daybook.orderId})"><a class="can-click">${daybook.orderCode}</a></td>
	                  
	                  	<c:choose>
		                  <c:when test="${daybook.memberId == null}">
		                  <td>
		                  	散客（${daybook.sex}）
		                  </td>
		                  </c:when>
		                  <c:otherwise>
		                  <td class="can-click" data-toggle="modal" data-target="#member-data" onclick="selectMemberInfo(${daybook.memberId})">
		                  ${daybook.memberName}
		                  </td>
		                  </c:otherwise>
		                 </c:choose>
	                  
	                  <td>${daybook.createTime}</td>
	                  <td class="wthn100 ellipsis-text" data-toggle="tooltip" data-placement="right" title="${daybook.projectName}">
	                     ${daybook.projectName}
	                  </td>
	                  <td>${daybook.realPrice}</td>
	                  <td>${daybook.cashAmount}</td>
	                  <td>${daybook.unionpayAmount}</td>
	                  <td>
		                  <c:choose>
		                  	<c:when test="${daybook.wechatAmount == 0 and daybook.alipayAmount == 0}">
		                  		0
		                  	</c:when>
		                  	<c:when test="${daybook.wechatAmount > daybook.alipayAmount}">
		                  		<i class="iconfont icon-weixin fl"></i>
				                  <span class="fl ml10">
				                  	${daybook.wechatAmount + daybook.alipayAmount}
				                  </span>
		                  	</c:when>
		                  	<c:when test="${daybook.alipayAmount > daybook.wechatAmount}">
		                  		<i class="iconfont icon-zhifubao fl"></i>
				                  <span class="fl ml10">
				                  	${daybook.wechatAmount + daybook.alipayAmount}
				                  </span>
		                  	</c:when>
		                  	<c:otherwise>
		                  		0
		                  	</c:otherwise>
		                  </c:choose>
	                  </td>
	                  <td>${daybook.cardAmount}</td>
	                  <td>${daybook.comboAmount}</td>
	                  <td>${daybook.giftAmount}</td>
	                  <td>${daybook.couponAmount}</td>
	                  <td>${daybook.groupAmount}</td>
	                  <td>${daybook.debtAmount}</td>
	                  <td>${daybook.realAmount}</td>
					  <td><span class="iconfa-trash project-icon" onclick="deleteOrder(${daybook.orderId}, this)"></span></td>
	              </tr>
	              </c:forEach>
	              </tbody>
	          </table>
	          <%@ include file="/template/page.jsp" %>
			</div>
		</div>
   	</div>
    <!--RIGHT PANEL结束 -->
	
	<!--流水查询模态框-->
	<div class="modal hide" id="liushui-search" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content liushui-data">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true" onclick="cancelModel()">&times;</span></button>
	                <span id="orderCodeModel"></span>
	                &nbsp;&nbsp;&nbsp;&nbsp;
	                <span></span>
	            </div>
	            <div class="modal-body">
	                    <table class="table price">
	                        <thead>
	                            <tr>
	                                <td>应收</td>
	                                <td>现金</td>
	                                <td>银联</td>
	                                <td>微信</td>
	                                <td>支付宝</td>
	                                <td>卡金</td>
	                                <td>团购</td>
	                                <td>优惠抵扣</td>
	                                <td>挂账</td>
	                                <td>实收</td>
	                            </tr>
	                        </thead>
	                        <tbody>
	                            <tr>
	                                <td><span class="red" id = "discountAmountModel"></span></td>
	                                <td><input type="text" name = "cashAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><input type="text" name = "unionpayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><input type="text" name = "wechatAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><input type="text" name = "alipayAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><input type="text" name = "cardAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><input type="text" name = "groupAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><span class="red" id = "privilegeModel"></span></td>
	                                <td><input type="text" name = "debtAmountModel" onkeyup="checkNum(this)" placeholder="0.00"/></td>
	                                <td><span class="red" id = "realAmountModel"></span></td>
	                            </tr>
	                        </tbody>
	                    </table>
	                    <!-- 基本信息开始 -->
	                    <div class="expense" id = "detailExpense">
	                            
                        </div>
	                </div>
	            </div><!--modal-body-->
	            <!-- 基本信息结束 -->
                <div class="modal-footer clearfix">
                    <div class="fr">
                        <button class="btn" aria-hidden="true" onclick="cancelModel()" id = "cancelModel" data-dismiss="modal" aria-label="Close">取消</button>
                        <button class="btn" onclick="confirmModel()">确定</button>
                    </div>
                </div>
	        </div><!--modal-content-->
	    </div><!--modal-dialog--> 
	    
	    <!--删除提示-->
	    <div class="modal hide" id="deleteOrderTip" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	        <div class="modal-dialog" role="document">
	            <div class="modal-content confirm">
	                <div class="modal-header">
	                    <button type="button" class="close" data-dismiss="modal" onclick="czCancel()" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                    <h4 class="modal-title" id="myModalLabel">删除订单</h4>
	                </div>
	    
	                <div class="modal-body confirm-body">
	                                                订单删除后不可恢复，是否确定本次操作？
	                </div><!--modal-body-->
	    
	                <div class="modal-footer">
	                    <a class="btn cancel-btn modal-cancel" data-dismiss="modal" href="javascript:void(0);">取消</a>
	                    <a class="btn btn-primary save-btn modal-confirm" href="javascript:deleteOrderConfirm();">确定</a>
	                </div>
	            </div>
	        </div>
	    </div>  
	    
	    <!-- 会员等级 -->
	    <div class="modal hide" id="member-level-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content rotating-setting-modal" style="width: 450px;">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h5 class="modal-title" id="myModalLabel">恢复会员等级</h5>
	            </div>
	            <div class="modal-body">
	                <form action="" class="editprofileform" method="post" style="padding-bottom: 42px;">
	                    <table class="table">
	                      <tbody id = "modelTbody">
	                        <tr id = "topTR">
	                            <td colspan="2">
						                             检测到改订单为升级会员操作 ，您可以选择下列会员等级进行调整
	                            </td>
	                        </tr>
	                        
	                        </tbody>
	                    </table>
	                </form>
	            </div><!--modal body-->
	
	            <div class="modal-footer">
	                <a class="btn modal-cancel" href="#" data-dismiss="modal" aria-label="Close">取消</a>
	                <a class="btn btn-primary modal-confirm" href="#" onclick="updateMemberLevel()">确定</a>
	            </div>
	        </div>
	        
	        
	    </div>
	</div> 

    <div class="clearfix"></div>

    <div id="star"></div>

</div><!--mainwrapper-->

<%@ include file="/template/memberData.jsp" %>
<script type="text/javascript">
jQuery(function() {
	/*表头置顶*/
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
});
//获取加载页面时的页码信息
var pageNo = "${page.pageNo}";
var pageSize = "${page.pageSize}";
var totalPage = "${page.totalPage}";
var totalRecord = '${page.totalRecord}';

var queryParams = JSON.parse('${queryParamsStr}');
console.log("queryParams : " + JSON.stringify(queryParams));
</script>
<script type="text/javascript" src="<%=basePath %>js/daybook/daybook.js"></script>
</body>
</html>