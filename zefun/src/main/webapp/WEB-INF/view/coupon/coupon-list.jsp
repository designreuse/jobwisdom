<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/seo-1.css" type="text/css" />
<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right">
					<div class="add_new_">
						<p>新增优惠券:</p>
						<table class="seo-table">
							<tbody>
								<tr>
									<td>优惠券名称</td>
									<td>适用项目/商品</td>
									<td>抵扣金额</td>
									<td>适用项目/商品</td>
									<td>兑换所需积分</td>
									<td>是否立即发布</td>
									<td>结束时间</td>
								</tr>

								<tr>
									<td><input type="text" id="couponName" placeholder="请输入名称"></td>
									<td>
										<select data-placeholder="通用" onchange="changeEniy(this.value)" name="couponType"  class="chzn-select input-xlarge" style="width: 100px">
				                            <option value="0">通用</option>
				                            <option value="1">单个项目</option>
				                            <option value="2">单个商品</option>
				                            <option value="4">项目系列</option>
				                            <option value="5">商品系列</option>
				                        </select>
									</td>
									<td><input type="number" id="couponPrice" placeholder="元"></td>
									<td id="select_view">当前为通用类型</td>
									<td>
									<input type="number"  id="couponVantages" placeholder="元">
									</td>
									<td><input type="radio" onclick="chkRadio(this)" value="0" id="couponIsUse" name="assignType" style="width:18px"></td>
									<td><input type="date" name="date" placeholder="截止时间" id="couponStopTime"></td>
								</tr>
							</tbody>
						</table>
						<button id="baocun" class="seo-1-save">保存</button>
					</div>
					<div class="table_hidden">
						<table class="seo-1-list">
							<thead>
								<tr>
									<td>优惠券名称</td>
									<td>类型</td>
									<td>适用于</td>
									<td>抵扣金额</td>
									<td>兑换所需积分</td>
									<td>已兑换次数</td>
									<td>已使用次数</td>
									<td>过期时间</td>
									<td>发布时间</td>
									<td>状态</td>
									<td>操作</td>
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
				                      <%-- <span class="yl" id="${CouponInfoDtos.couponId }" style="cursor: pointer">预览</span> --%>
				                      
				                      <c:if test="${CouponInfoDtos.couponIsUse == '已发布'}">
					                  <span class="fs" id="${CouponInfoDtos.couponId }" style="cursor: pointer">赠送</span>
					                  </c:if>
				                      <span class="delete" id="${CouponInfoDtos.couponId }" style="cursor: pointer">删除</span>
				                  </td>
				              </tr>
				            </c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--mainwrapper-->

	<!--发送优惠券模态框-->
	<div class="modal hide" id="fabu-cousor-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content add-zhiwei-modal">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">赠送优惠券</h4>
				</div>
				<div class="modal-body">
					<label>请选择要赠送的人群</label> <br />
					<div>
						<label>筛选器</label>
						<div style="margin-top: 10px;">
							<c:forEach items="${memberScreenings }" var="memberScreening">
								<input type="checkbox" name="memberScreenings" style="margin-top: -1.5px;" value="${memberScreening.screeningId }"> &nbsp;${memberScreening.screeningName }
                      </c:forEach>
						</div>
					</div>
					<br>
					<div>
						<label>会员卡</label>
						<div style="margin-top: 10px;">
							<c:forEach items="${memberLevels }" var="memberLevel">
								<input type="checkbox" name="memberLevel" style="margin-top: -1.5px;" value="${memberLevel.levelId }"> &nbsp;${memberLevel.levelName }
                      </c:forEach>
						</div>
					</div>
					<br>
				</div>
				<!--modal-body-->
				<div class="modal-footer">
					<a id="quxiao" class="btn cancel-btn modal-cancel" href="#">取消</a> <a id="querenfasong" class="btn btn-primary save-btn modal-confirm" href="#">赠送</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	/**
	 * 改变radio的样式
	 * @param id
	 */
	function chkRadio(opt) {
		if (jQuery(opt).val() == "1"){
			opt.checked = false;
			jQuery(opt).val("0");
		}
		else {
			opt.checked = true;
			jQuery(opt).val("1");
		}
	}
</script>
<script src="<%=basePath%>js/coupon/coupon-list.js"></script>
</html>