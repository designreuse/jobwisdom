<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>今日订单列表</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
    <style type="text/css">
        .content{
            height:100% !important;
        }
    </style>
  </head>
  
    <body>
    <div class="wrap">
      <div class="content">
        <div class="yg-dingdan-all">
          <div class="tab t0">
		      <ul>
		        <li <c:if test="${type == 1}">class="score-shop-li  active"</c:if><c:if test="${type != 1}">class="score-shop-li"</c:if> onclick="changeDiv(1, ${orderType})">
		          <a href="javascript:void(0);">
		            <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" <c:if test="${type != 1}">class="hide"</c:if>/>
		            <div class="tab-word">
		              <span>进行中</span>
		            </div>
		          </a>
		          <div <c:if test="${type == 1}">class="border-group hide"</c:if><c:if test="${type != 1}">class="border-group"</c:if>>
		            <div class="one-border"></div>
		            <div class="two-border"></div>
		            <div class="three-border"></div>
		          </div>
		        </li>
		        <li <c:if test="${type == 2}">class="score-shop-li  active"</c:if><c:if test="${type != 2}">class="score-shop-li"</c:if> onclick="changeDiv(2, ${orderType})">
		          <a href="javascript:void(0);">
		            <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" <c:if test="${type != 2}">class="hide"</c:if>/>
		            <div class="tab-word">
		              <span>待结账</span>
		            </div>
		          </a>
		          <div <c:if test="${type == 2}">class="border-group hide"</c:if><c:if test="${type != 2}">class="border-group"</c:if>>
		            <div class="one-border"></div>
		            <div class="two-border"></div>
		            <div class="three-border"></div>
		          </div>
		        </li>
		        <li <c:if test="${type == 3}">class="score-shop-li  active"</c:if><c:if test="${type != 3}">class="score-shop-li"</c:if> onclick="changeDiv(3, ${orderType})">
		          <a href="javascript:void(0);">
		            <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" <c:if test="${type != 3}">class="hide"</c:if>/>
		            <div class="tab-word">
		              <span>已完成</span>
		            </div>
		          </a>
		          <div <c:if test="${type == 3}">class="border-group hide"</c:if><c:if test="${type != 3}">class="border-group"</c:if>>
		            <div class="one-border"></div>
		            <div class="two-border"></div>
		            <div class="three-border"></div>
		          </div>
		        </li>
		      </ul>
		   </div>
		   <div class="clearfix"></div>
		   <div>
	       <c:forEach items="${orderList}" var="order">
              <div class="dingdan-person first-pt7" id="orderId_${order.orderId }">
                <ul class="dingdan-ul" >
                    <li class="dingdan-title">
                        <c:choose>
                            <c:when test="${!empty order.memberInfo.memberId }">
                                  <div class="dis-ib s-modal-control" onclick="showMemberInfo(${order.memberInfo.memberId})">
                                    <img src="<%=picPath %>${order.memberInfo.headUrl}?imageView2/1/w/84/h/84" alt=""/>
                                    <span class="name">${order.memberInfo.name}</span>
                                    <span class="shenfen font-size-20">${order.memberInfo.levelName}</span>
                                  </div>
                            </c:when>
                            <c:otherwise>
                                  <div class="dis-ib s-modal-control">
                                    <c:choose>
                                        <c:when test="${order.sex == '男' }">
                                            <span class="iconfont icon-manuser fs30 ml5"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="iconfont icon-womanuser fs30 ml5"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <span class="name">${order.memberInfo.name}</span>
                                  </div>
                            </c:otherwise>
                        </c:choose>
                        
                        <span name="statusContent">
                        
                        <c:choose>
                            <c:when test="${order.orderStatus == 3 || order.orderStatus == 4}">
		                          <span class="fr font-size-24 yellow-word">已结账</span>
                           </c:when>
                            <c:when test="${order.orderStatus == 5 }">
                                  <span class="fr font-size-24 font-666">已通知买单</span>
	                       </c:when>
	                       <c:when test="${order.orderStatus == 2 }">
	                           <span class="fr font-size-24 yellow-word">待结账</span>
	                       </c:when>
	                       <c:otherwise>
	                              <span class="fr font-size-24 blue-word">进行中</span>
	                       </c:otherwise>
                        </c:choose>
                        </span>
                    </li>
                    <c:forEach items="${order.orderDetailList }" var="orderDetail">
                      <c:if test="${orderDetail.orderType != 1}">
                        <li class="dingdan-list" onclick="selectOrderDetail(${order.orderId});">
                            <img src="<%=picPath %>${orderDetail.projectImage}" alt=""/>
                            <div class="list-desc">
                                <div class="list-name">${orderDetail.projectName } <span class="list-price">￥${orderDetail.discountAmount }</span></div>
                                <c:if test="${orderDetail.orderType == 2 }">
                                       <div class="faxingshi">
                                        </div>
                                        <div class="dingdan">
                                            <span class= "state shop">商品</span>
                                        </div>
                                </c:if>
                                <c:if test="${orderDetail.orderType == 3 }">
                                       <div class="faxingshi">
                                        </div>
                                        <div class="dingdan">
                                            <span class= "state shop">套餐</span>
                                        </div>
                                </c:if>
                            </div>
                            <div class="dingdan-edit fr s-modal-control">
                                <span class="font-999">X</span>
                                <span class="font-999">${orderDetail.projectCount}</span>
                            </div>
                        </li>
                      </c:if>
                      <c:if test="${orderDetail.orderType == 1}">
                        <li class="dingdan-list" onclick="selectOrderDetail(${order.orderId});">
                            <img src="<%=picPath %>${orderDetail.projectImage}" alt=""/>
                            <div class="list-desc">
                                <div class="list-name">${orderDetail.projectName }</div>
                                <c:choose>
                                   <c:when test="${orderDetail.orderStatus == 3 }">
                                       <div class="faxingshi">
                                        </div>
                                        <div class="dingdan">
                                            <span class="state yiwancheng">已完成</span>
                                            <span class="time">${orderDetail.serviceLength}</span><span class="font-999">  分钟</span>
                                            
                                        </div>
                                   </c:when>
                                   <c:otherwise>
                                        <c:forEach items="${orderDetail.stepList}" var="step">
                                            <c:if test="${step.isCurrent == 1}">
                                                <div class="faxingshi">
                                                   <span class="zhiwei">${step.shiftMahjongName}</span>
                                                   <span class="name">${step.employeeInfo.employeeCode} ${step.employeeInfo.name}</span>
                                                    <c:choose>
	                                                       <c:when test="${step.isAssign == 1 }">
	                                                           <div class="state-wrap">
                                                                <span class="zhiding-icon">
                                                                                                                                                                                                              指定
                                                                </span>
                                                              </div>
	                                                       </c:when>
	                                                       <c:when test="${step.isDesignate == 1 }">
	                                                           <div class="state-wrap">
                                                                <span class="zhipai-icon">
                                                                                                                                                                                                              指派
                                                                </span>
                                                              </div>
	                                                       </c:when>
	                                                   </c:choose>
                                               </div>
                                               <div class="dingdan">
                                                   <c:choose>
                                                       <c:when test="${step.isOver == 1 }">
                                                           <span class="state doing">服务中</span>
                                                       </c:when>
                                                       <c:otherwise>
                                                           <span class="state waiting">等待中</span>
                                                       </c:otherwise>
                                                   </c:choose>
                                                   <span class="stepTime time">${step.beginTime }</span>
                                               </div>
                                            </c:if>
                                      </c:forEach>
                                   </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="dingdan-edit fr">
                            <span class="list-price">
                                                                                                  ￥${orderDetail.discountAmount}
                            </span>
                        </div>
                        </li>
                      </c:if>
                    </c:forEach>
                    <li class="dingdan-danhao">
                        <span  class="danhao fl">
                                                                               单号: ${order.orderCode }
                        </span>
                        <span class="dingdan-price fr">
                                                                                合计: <span class="d-price">￥${order.discountAmount}</span>
                        </span>
                    </li>
                    <c:if test="${order.orderStatus != 3 && order.orderStatus != 4 }">
                        <div class="clearfix"></div>
	                    <li class="dingdan-jiezhang notify">
	                        <c:if test="${order.orderStatus == 2}">
	                            <c:choose>
	                                 <c:when test="${empty order.memberInfo.memberId }">散客前台结账</c:when>
	                                 <c:otherwise>
	                                     <div class="qiandan normal-btn" onclick="notifyPayment(${order.orderId}, this)">通知买单</div>
	                                 </c:otherwise>
	                             </c:choose>
	                        </c:if>
	                        <c:if test="${session_key_role_id != 4 }">
	                         <div class="qiandan neg-btn" onclick="deleteOrderModel(${order.orderId})">取消订单</div>
	                        </c:if>
	                    </li>
                    </c:if>
                </ul>
            </div>
          </c:forEach>            
         </div>
          
        </div>
        
        <%-- <c:if test="${type != 3}">
            <div class="today-dingdan-all" style="bottom:5.625rem;">
	            <span>今天已完成 <span class="d-red">${statisticsCount}</span>单，收入 <span class="d-red">${statisticsMoney }</span>元</span>
	        </div>
        </c:if> --%>
        
        <!-- 引入会员信息模版 -->
		<%@include file="memberInfoTemplate.jsp" %>
        
        <!--删除订单-->
        <div class="s-modal hide s-modal-miss" id="deleteOrderModel">
            <div class="s-modal-wrap">
                <div class="d-member-info">
                    <div class="n-modal-title text-center">
                        <span>是否确认取消该订单？</span>
                        <div class="clearfix"></div>
                    </div>
                    <div class="s-modal-foot">
                        <div class="modal-cancel" onclick="recover()">取消</div>
                        <div class="modal-confirm" onclick="deleteOrderInfo();">确认</div>
                    </div>
                </div>
            </div>
        </div>
            
        <!--挂账-->
		<div class="s-modal-miss no-padding s-modal hide" id="yg-guazhang">
		    <ul class="yijiao-ul bottom-ul clearfix">
		        <li class="bottom-modal-title">
		            <span class="iconfont icon-shebaozhangtao mr2"></span>
		            <span class=" gouwuche-name">挂账</span>
		        </li>
		        <li class="paiwei-li">
		            <label class="paiwei-label">金额</label>
		            <input type="number" name = "debtAmount" class="dingdan-modal-big-length" placeholder="请输入金额"/><span class="iconfont icon-zhifuzongjine ml-5"></span>
		        </li>
		        <li class="paiwei-li">
		            <label class="paiwei-label">备注</label>
		            <input type="text" name = "orderEvaluate" class="dingdan-modal-big-length" placeholder="请输入备注"/>
		        </li>
		        <li class="paiwei-btn">
		            <div class="queren btn" onclick="confirmFree(2)">确定挂账</div>
		        </li>
		    </ul>
		</div>
		
        <c:choose>
	      <c:when test="${empty orderList}">
	          <div class="kongjilv">
	            <div class="center">
	                <div class="iconfont icon-xingzhuang14"></div>
	                <div>现在还没有订单哦，加油吧</div>
	            </div>
	        </div>
	      </c:when>
	      <c:otherwise>
	          <div class="h120" style="background-color: #eee; text-align: center;"><span class="font-999">已显示全部内容</span></div>
	      </c:otherwise>
	    </c:choose>
	    
	    <div class="footer">
	        <ul>
	            <li >
	                <a href="<%=basePath%>staff/view/reception">
	                    <span class="iconfont icon-jiedai"></span>
	                    <span>接待</span>
	                </a>
	            </li>
	            <li class="active">
	                <a href="<%=basePath%>staff/view/employeeOrderView/${session_key_store_id}/2">
	                    <span class="iconfont icon-dingdan"></span>
	                    <span>订单</span>
	                </a>
	            </li>
	            <li >
	                <a href="<%=basePath%>staff/view/allEarning">
	                    <span class="iconfont icon-yejipaihang02"></span>
	                    <span>排行</span>
	                </a>
	            </li>
	            <li>
	                <a href="<%=basePath%>staff/view/staffCenter/${session_key_store_id}/2">
	                    <span class="iconfont icon-wode"></span>
	                    <span>我的</span>
	                </a>
	            </li>
	        </ul>
	    </div>
	    
    </div>
    </div>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script>
  var orderListStr = '${orderListStr}';
  var orderList = eval("(" + orderListStr + ")");
  
  var deleteOrderId = null;
  function deleteOrderModel(orderId) {
    $("#deleteOrderModel").removeClass("hide");
    deleteOrderId = orderId;
  }
  
  function deleteOrderInfo() {
	     $("#deleteOrderModel").addClass("hide");
	     $.ajax({
	        type : "post",  
	        url : baseUrl + "staff/view/deleteOrderInfo",
	        data : "orderId="+deleteOrderId,
	        dataType : "json",
	        success : function(e){
	            if(e.code != 0){
	                dialog(e.msg);
	                return;
	            }
	            dialog("取消成功, 正在为您刷新页面...");
	            setTimeout(function(){
	                window.location.href = window.location.href;
	            }, 1500);
	        }
	    });
	 }
  
  var storeId = '${storeId}';
     $(function(){
         $(".bottom-ul", ".fix-p-select").on("click", function (event) {
             event.stopPropagation();
         })
         
         $(".yellow-radio").on("click", function () {
          var radio = $(".yellow-radio");
          for(var i=0;i<radio.length;i++)
          {
              if(radio[i].checked)
              {
                  $(radio[i]).siblings(".beau-radio").addClass("active");
              }else{
                  $(radio[i]).siblings(".beau-radio").removeClass("active");
              }
          }
      });
      
      //为步骤进行时间进行定时事件绑定
      $(".yijiao-ul").click(function (event) {
             event.stopPropagation();
         })
         
         //为步骤进行时间进行定时事件绑定
      $(".fix-p-select").click(function (event) {
             event.stopPropagation();
         })
         
         //为步骤进行时间进行定时事件绑定
      $(".s-modal-wrap").click(function (event) {
             event.stopPropagation();
         })  
         
         $("#yg-fuwu-yijiao").click(function(event){
             $("#yg-fuwu-yijiao-model").removeClass("hide");
         });
      
     });
     
     /* //取消
     function yijiaoCancel(obj) {
         $(obj).parents(".yijiao-ul").parent().addClass("hide");
     } */
     
     
function recover() {
	 $("#deleteOrderModel").addClass("hide");
}    
     
//通知顾客买单
function notifyPayment(orderId, obj){
    $.ajax({
        url : baseUrl + "staff/action/order/notify",
        type : "POST",
        data : "orderId=" + orderId,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("已通知买单");
            $(obj).parent().remove();
            $("#orderId_" + orderId + " [name='statusContent']").html('<span class="fr font-666 fs22">已通知买单</span>');
        }
    });
}

function selectOrderDetail(orderId){
	var temp = document.createElement("form");
    temp.action = baseUrl + "staff/view/selectOrderDetail/${session_key_store_id}/2";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("textarea");
    opt.name = "orderId";
    opt.value = orderId;
    temp.appendChild(opt);
    document.body.appendChild(temp);
    temp.submit();
}

$(".score-shop-li").click(function (event) {
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	$(".score-shop-li").removeClass("active");
	$(".score-shop-li").find("img").addClass("hide");
	$(".score-shop-li").find(".border-group").removeClass("hide");
	
	$(obj).parents(".score-shop-li").addClass("active");
	$(obj).parents(".score-shop-li").find("img").removeClass("hide");
	$(obj).parents(".score-shop-li").find(".border-group").addClass("hide");
});

function changeDiv(type, orderType) {
	//全部订单
	if (orderType == 1) {
		var temp = document.createElement("form");
	    temp.action = baseUrl + "staff/view/order/all";
	    temp.method = "post";
	    temp.style.display = "none";
	    var opt = document.createElement("textarea");
	    opt.name = "type";
	    opt.value = type;
	    temp.appendChild(opt);
	    document.body.appendChild(temp);
	    temp.submit();
	}
	else {
		var temp = document.createElement("form");
	    temp.action = baseUrl + "staff/view/employeeOrderView/${session_key_store_id}/2";
	    temp.method = "post";
	    temp.style.display = "none";
	    var opt = document.createElement("textarea");
	    opt.name = "type";
	    opt.value = type;
	    temp.appendChild(opt);
	    document.body.appendChild(temp);
	    temp.submit();
	}
}
//订单标识
var freeOrderId = "";
//原折扣价格
var oldDiscountAmount = "";

//点击签单
function freeClick(orderIds) {
	
	freeOrderId = orderIds;
	
	$("input[name='debtAmount']").val("");
	$("input[name='orderEvaluate']").val("");
	
	$.ajax({
        url : baseUrl + "staff/action/selectOrderInfo",
        type : "POST",
        data : "orderId=" + orderIds,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            var orderInfo = e.msg;
            
           	if (orderInfo.debtAmount != 0) {
               	$("input[name='debtAmount']").val(orderInfo.debtAmount);
               }
               $("input[name='orderEvaluate']").val(orderInfo.orderEvaluate);
            oldDiscountAmount = orderInfo.discountAmount;
        }
    });
}

function confirmFree(type) {
	if(type == 1) {
		/* var freeAmount = $("input[name='freeAmount']").val();
		var orderRemark = $("input[name='orderRemark']").val();
		if (isEmpty(freeAmount)) {
			dialog("签单金额不能为空！");
			return;
		}
		if (isEmpty(orderRemark)) {
			dialog("签单备注不能为空！");
			return;
		}
	    if (oldDiscountAmount < freeAmount) {
	    	dialog("签单金额不能大于折扣金额");
	    	return;
	    }
		$.ajax({
	        url : baseUrl + "staff/action/saveFreeInfo",
	        type : "POST",
	        data : "orderId=" + freeOrderId + "&amount= " + freeAmount + "&remark=" + orderRemark + "&type=" + type,
	        success : function(e){
	            if (e.code != 0) {
	                dialog(e.msg);
	                return;
	            }
	            dialog("签单成功！");
	            $("#yg-qiandan").addClass("hide");
	            
	            $("#orderId_" + freeOrderId).find(".d-price").text("￥" + e.msg);
	        }
	    }); */
	}
	else {
		var debtAmount = $("input[name='debtAmount']").val();
		var orderEvaluate = $("input[name='orderEvaluate']").val();
		if (isEmpty(debtAmount)) {
			dialog("挂账金额不能为空！");
			return;
		}
		if (isEmpty(orderEvaluate)) {
			dialog("挂账备注不能为空！");
			return;
		}
	    if (oldDiscountAmount < orderEvaluate) {
	    	dialog("挂账金额不能大于折扣金额");
	    	return;
	    }
		$.ajax({
	        url : baseUrl + "staff/action/saveDebtInfo",
	        type : "POST",
	        data : "orderId=" + freeOrderId + "&amount= " + debtAmount + "&remark=" + orderEvaluate + "&type=" + type,
	        success : function(e){
	            if (e.code != 0) {
	                dialog(e.msg);
	                return;
	            }
	            dialog("挂账成功！");
	            
	            $("#yg-guazhang").addClass("hide");
	            
	            $("#orderId_" + freeOrderId).find(".d-price").text("￥" + e.msg);
	        }
	    });
	}
}
</script>
</body>
</html>