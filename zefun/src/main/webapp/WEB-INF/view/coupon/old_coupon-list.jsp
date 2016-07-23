<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <!--新增优惠券-->
        <div class="widgetcontent">
            <div class="more-toolbar">
                <div class="table-toolbar">
                    <label for="">新增优惠券</label>
                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <form action="" id="couponsForm">
            <table class="table collect-money-table">
                <thead>
                <tr>
                    <th class="dropdown-toggle" data-toggle="dropdown">优惠券名称</th>
                    <th class="dropdown-toggle" data-toggle="dropdown">适用项目/商品</th>
                    <th class="dropdown-toggle" data-toggle="dropdown">适用项目/商品</th>
                    <th>抵扣金额</th>
                    <th>兑换所需积分</th>
                    <th>是否立即发布</th>
                    <th>结束时间</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><input type="text"  id="couponName" class="input80"></td>
                    <td>
                        <select data-placeholder="通用" onchange="changeEniy(this.value)" name="couponType"  class="chzn-select input-xlarge" style="width: 100px">
                            <option value="0">通用</option>
                            <option value="1">单个项目</option>
                            <option value="2">单个商品</option>
                            <option value="4">项目系列</option>
                            <option value="5">商品系列</option>
                        </select>
                    </td>
                    <td id="select_view">
                                                         当前为通用优惠券  <!-- 项目/商品/疗程 -->
                    </td>
                    <td><input type="text" id="couponPrice" class="input-small"></td>
                    <td><input type="text"  id="couponVantages" class="input-small"></td>
                    <td>
                        <input type="checkbox" id="couponIsUse" name="assignType" value="1" class="lcs_check_assignType" checked="checked" autocomplete="on" />
                    </td>
                    <td>
                        <input  type="text" name="date" placeholder="截止时间" id="couponStopTime" format="yyyy-mm-dd" class="datetimepicker timepicker width100">
                    </td>
                </tr>
                <tr>
                    <td colspan="7">
                        <!-- <span class="mr10 label12">结束时间</span><input  type="text" class="input-small timePickerClean" id="couponStopTime"> -->
                        <div style="float: right;">
                            <a id="baocun" href="javascript:void(0)" class="btn btn-primary input100">&nbsp;保&nbsp;&nbsp;存&nbsp;</a>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
            </form>
        </div>
        <!--新增优惠券-->
        <div class="more-toolbar" >
            <div class="table-toolbar">
              	  优惠券列表
            </div><!--table-toolbar-->
            <div class="clearfix"></div>
        </div><!--more-toolbar-->

        <table class="table table-bordered table-striped member-list-table">
            <thead class="t-fix">
            <tr>
                <th>优惠券名称</th>
                <th>类型</th>
                <th>适用于</th>
                <th>抵扣金额</th>
                <!-- <th>创建时间</th> -->
                <th>兑换所需积分</th>
                <th>已兑换次数</th>
                <th>已使用次数</th>
                <th>过期时间</th>
                <th>发布时间</th>
                <th>状态</th>
                
                
                
                
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="showCoupon">
            <c:forEach items="${page.results }" var="CouponInfoDtos" varStatus="index">
            	<c:if test="${index.index == 0 }"><tr class="t-table"></c:if>
            	<c:if test="${index.index != 0 }"><tr></c:if>
                  <td>${CouponInfoDtos.couponName }</td>
                  <td>${CouponInfoDtos.couponType }</td>
                  <td>${CouponInfoDtos.couponUse }</td>
                  <td>${CouponInfoDtos.couponPrice }  元</td>
                  <td>${CouponInfoDtos.couponVantages }  分</td>
                  <td>${CouponInfoDtos.hasExchangeCount }  次</td>
                  <td>${CouponInfoDtos.hasUseCount }  次</td>
                  <td>${CouponInfoDtos.couponStopTime }</td>
                  <td>${CouponInfoDtos.releaseTime }</td>
                  <td>${CouponInfoDtos.couponIsUse }</td>
                  <td>
	                  <c:if test="${CouponInfoDtos.couponIsUse == '未发布'}">
	                  <span class="fb" id="${CouponInfoDtos.couponId }" style="cursor: pointer">发布</span>
	                  <span class="xj hide" id="${CouponInfoDtos.couponId }" style="cursor: pointer">下架</span>
	                  </c:if>
	                  <c:if test="${CouponInfoDtos.couponIsUse == '已发布'}">
	                  <span class="fb hide"  id="${CouponInfoDtos.couponId }" style="cursor: pointer">发布</span>
	                  <span class="xj" id="${CouponInfoDtos.couponId }" style="cursor: pointer">下架</span>
	                  </c:if>
                      <span class="yl" id="${CouponInfoDtos.couponId }" style="cursor: pointer">预览</span>
                      
                      <c:if test="${CouponInfoDtos.couponIsUse == '已发布'}">
	                  <span class="fs" id="${CouponInfoDtos.couponId }" style="cursor: pointer">赠送</span>
	                  </c:if>
	                  
                      <span class="delete" id="${CouponInfoDtos.couponId }" style="cursor: pointer">删除</span>
                  </td>
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

<!--发送优惠券模态框-->
<div class="modal hide" id="fabu-cousor-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-zhiwei-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">赠送优惠券</h4>
            </div>
            <div class="modal-body">
            	<label>请选择要赠送的人群</label>
            	<br/>
                <div>
                    <label>筛选器</label>
                  <div style="margin-top: 10px;">
                      <c:forEach items="${memberScreenings }" var="memberScreening">
                      <input type="checkbox" name="memberScreenings" style="margin-top: -1.5px;"  value="${memberScreening.screeningId }"> &nbsp;${memberScreening.screeningName }
                      </c:forEach>
                  </div>
                </div>
                <br>
                <div>
                    <label>会员卡</label>
                    <div style="margin-top: 10px;">
                   	  <c:forEach items="${memberLevels }" var="memberLevel">
                      <input type="checkbox" name="memberLevel" style="margin-top: -1.5px;"  value="${memberLevel.levelId }"> &nbsp;${memberLevel.levelName }
                      </c:forEach>
                    </div>
                </div>
                <br>
            </div><!--modal-body-->
            <div class="modal-footer">
                <a id="quxiao" class="btn cancel-btn modal-cancel" href="#">取消</a>
                <a id="querenfasong" class="btn btn-primary save-btn modal-confirm" href="#">赠送</a>
            </div>
        </div>
    </div>
</div>
<!--微信预览模态框-->
<div class="modal hide weixin-preview-modal"  data-backdrop="static" id="weixin-cousor" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content weixin-preview">
            <div class="mobile_preview" id="mobileDiv" style="display: block;">
                <div class="mobile_preview_hd">
                    <strong class="nickname">智放</strong>
                </div>
                <div class="mobile_preview_bd" style="overflow-y:auto;">
                    <ul id="viewShow" class="show_list">
                        <li>
                            <div class="img-link" style="padding-left:10px;">
                                <span><h4>优惠券通知</h4></span>
                                <span id="time" style="opacity: 0.75">8月18日</span>
                                <div style="margin-top: 8px;">恭喜您获得一张优惠券</div>
                                <div style="margin-top: 8px;">
                                    <span>优惠券名称：</span>
                                    <span id="yl_group_name"></span>
                                </div>
                                <div>
                                    <span>适用于项目：</span>
                                    <span id="yl_project"></span>
                                </div>
                                  <div>
                                    <span>抵扣金额：</span>
                                    <span id="yl_count"></span>
                                </div>
                                <div>
                                    <span>结束日期：</span>
                                    <span id="yl_end_time"></span>
                                </div>
                                <hr/>
                                <a style="color: #0000cc;margin-top: -10px; margin-bottom: 12px;">详情</a>
                            </div>
                        </li>
                    </ul>
                    <!--点击详情后进入content-->
                    <div id="img-content" class="rich_media_area_primary hide">
                        <div>详情页面</div>
                    </div>
                    <!--点击详情后进入content-->
                </div>
                <div class="mobile_preview_ft">
                </div>
                <a href="javascript:void(0);" class="mobile_preview_closed btn btn_default" id="viewClose" data-dismiss="modal">退出预览</a>
            </div>
        </div>
    </div>
</div>
</body>
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
var pageNo = 1;
var pageSize = 5;
var totalPage = '${page.totalPage}';	
var projectCategories = jQuery.parseJSON('${projectCategories}');
var goodsCategories = jQuery.parseJSON('${goodsCategories}');
</script>
<script src="<%=basePath%>js/coupon/coupon-list.js"></script>
</html>