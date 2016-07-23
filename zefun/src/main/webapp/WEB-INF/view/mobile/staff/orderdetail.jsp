<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>订单详情</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
    <style type="text/css">
        .content{
            height:100% !important;
        }
        .overpaiwei{
		  display: inline-block;
		  height: 4.375rem;
		  line-height: 4.375rem;
		  background-color: #ccc;
		  color: #999;
		  border-radius: .375rem;
		  margin-right: 1rem;
		  padding-left: 1rem;
		  border: 1px solid #ccc;
		  width: 68%;
		}
    </style>
  </head>
  
    <body>
    <div class="wrap">
      <div class="content">

		<!--订单详情页面-->
		<div class="yg-dingdan-detail">
		    <div class="dingdan-person" id="orderId_${orderInfo.orderId }">
			      <ul class="dingdan-ul" >
			        <li class="dingdan-title">
			          <c:choose>
                            <c:when test="${!empty orderInfo.memberInfo.memberId }">
                                  <div class="dis-ib s-modal-control" onclick="showMemberInfo(${orderInfo.memberInfo.memberId})">
                                    <img src="<%=picPath %>${orderInfo.memberInfo.headUrl}?imageView2/1/w/84/h/84" alt=""/>
                                    <span class="name">${orderInfo.memberInfo.name}</span>
                                    <span class="shenfen">${orderInfo.memberInfo.levelName}</span>
                                  </div>
                            </c:when>
                            <c:otherwise>
                                  <div class="dis-ib s-modal-control">
                                    <c:choose>
                                        <c:when test="${orderInfo.sex == '男' }">
                                            <span class="iconfont icon-manuser fs30 ml5"></span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="iconfont icon-womanuser fs30 ml5"></span>
                                        </c:otherwise>
                                    </c:choose>
                                    <span class="name">${orderInfo.memberInfo.name}</span>
                                  </div>
                            </c:otherwise>
                      </c:choose>
                      <span name="statusContent">
	                       <c:choose>
	                           <c:when test="${orderInfo.orderStatus == 3 || orderInfo.orderStatus == 4}">
		                          <span class="fr fs22 font-666">已结账</span>
	                           </c:when>
	                           <c:when test="${orderInfo.orderStatus == 5 }">
	                                 <span class="fr fs22 font-666">已通知买单</span>
	                           </c:when>
	                       </c:choose>
                      </span>
			        </li>
			        <c:forEach items="${orderInfo.orderDetailList}" var="orderDetail">
			            <c:if test="${orderDetail.orderType != 1}">
			                 <li class="shop-list">
					              <img src="<%=picPath %>${orderDetail.projectImage}" alt=""/>
						          <div class="list-desc">
						                <div class="list-name">${orderDetail.projectName} <span class="shuliang">X${orderDetail.projectCount}</span></div>
							            <div class="dingdan">
							              <c:if test="${orderDetail.orderType == 2 }">
							                 <span class="state shop">商品</span>
							              </c:if>
							              <c:if test="${orderDetail.orderType == 3 }">
							                 <span class="state shop">疗程</span>
							              </c:if>
							            </div>
						          </div>
						          <div class="dingdan-edit fr">
						            <span class="list-price">￥${orderDetail.discountAmount}</span>
						          </div>
					          </li>
					          <c:if test="${orderInfo.orderStatus == 1 || orderInfo.orderStatus == 2}">
					               <li class="dingdan-jiezhang bgf5">
							          <div class="qiandan neg-btn" style="width: 6.375rem;" onclick="detailModel(${orderDetail.detailId})">
							                                      删除
							          </div>
							          <div class="qiandan neg-btn s-modal-control" style="width: 7.375rem;" data-target="#yg-qiandan" onclick="freeClick(${orderDetail.detailId})">
						                                                    改价
			                          </div>
						          </li>
					          </c:if>
			            </c:if>
			            <c:if test="${orderDetail.orderType == 1}">
			                 <li class="dingdan-list">
						         <img src="<%=picPath %>${orderDetail.projectImage}" alt=""/>
						         <div class="list-desc">
							         <div class="list-name">${orderDetail.projectName} 
							             <span class="list-price">￥${orderDetail.discountAmount}</span>
							         </div>
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
						         <div class="dingdan-edit fr s-modal-control" data-target="#yg-fix-p-select">
						              <%-- <span class="list-price">￥${orderDetail.discountAmount}</span> --%>
						              <c:if test="${orderDetail.orderStatus == 2 || orderDetail.orderStatus == 4}">
						                  <div class="qiandan normal-btn s-modal-control" data-target="#yg-fuwu-yijiao-model" onclick="editOrderInfo(${orderDetail.detailId})">
								                                         服务交接
								          </div>
						              </c:if>
						              <c:if test="${orderDetail.orderStatus == 1}">
						                  <c:forEach items="${orderDetail.stepList}" var="step">
		                                     <c:if test="${step.isCurrent == 1 && !empty step.employeeInfo}">
		                                           <div class="qiandan normal-btn s-modal-control"  onclick="updateEmployee(${orderDetail.detailId}, ${step.shiftMahjongStepId})">
										                                        换人
										          </div>
		                                     </c:if>
		                                  </c:forEach> 
						              </c:if>
						              
						         </div>
						      </li>
						      <c:if test="${orderDetail.orderStatus == 2 || orderDetail.orderStatus == 4}">
						          <li class="dingdan-jiezhang bgf5">
						              <%-- <div class="qiandan normal-btn s-modal-control" data-target="#yg-fuwu-yijiao-model" onclick="editOrderInfo(${orderDetail.detailId})">
							                                         服务交接
							          </div> --%>
							          <c:if test="${session_key_role_id != 4 }">
							              <div class="qiandan neg-btn" style="width: 6.375rem;" onclick="changeProject(${orderDetail.detailId});">
                                                                                                                                               修改
                                          </div>
                                          <c:forEach items="${orderDetail.stepList}" var="step">
		                                     <c:if test="${step.isCurrent == 1}">
		                                         <div <c:if test="${orderDetail.orderStatus == 2}">class="qiandan neg-btn"</c:if> <c:if test="${orderDetail.orderStatus == 4}">class="qiandan neg-btn hide"</c:if> style="width: 6.375rem;" name = "isHandUp" onclick="hangUpOrder(${orderDetail.detailId}, ${step.shiftMahjongStepId}, 1, this);">
	                                                                                                                                                       挂起                  
		                                         </div>
		                                      
		                                         <div <c:if test="${orderDetail.orderStatus == 4}">class="qiandan neg-btn"</c:if> <c:if test="${orderDetail.orderStatus == 2}">class="qiandan neg-btn hide"</c:if> style="width: 6.375rem;" name = "notHandUp" onclick="hangUpOrder(${orderDetail.detailId}, ${step.shiftMahjongStepId}, 2, this);">
		                                                                                                                                     取消挂起
		                                         </div>
		                                     </c:if>
		                                  </c:forEach>
	                                      
	                                      <div class="qiandan neg-btn s-modal-control" style="width: 6.375rem;" onclick="detailModel(${orderDetail.detailId})">
	                                                                                                                                      删除
	                                      </div>
	                                      <div class="qiandan neg-btn s-modal-control" style="width: 6.375rem;" data-target="#yg-qiandan" onclick="freeClick(${orderDetail.detailId})">
	                                                                                                                                      改价
	                                      </div>
							          </c:if>
							      </li>
						      </c:if>
						      <c:if test="${orderDetail.orderStatus == 1}">
						          <li class="dingdan-jiezhang bgf5">
							          <c:if test="${session_key_role_id != 4 }">
							              <div class="qiandan neg-btn" style="width: 6.375rem;" onclick="changeProject(${orderDetail.detailId});">
                                                                                                                                          修改
	                                      </div>
	                                      <div class="qiandan neg-btn s-modal-control" style="width: 6.375rem;" onclick="detailModel(${orderDetail.detailId})">
	                                                                                                                              删除
	                                      </div>
	                                      <div class="qiandan neg-btn s-modal-control" style="width: 6.375rem;" data-target="#yg-qiandan" onclick="freeClick(${orderDetail.detailId})">
	                                                                                                                              改价
	                                      </div>
							          </c:if>
							      </li>
						      </c:if>
						      <c:if test="${orderDetail.orderStatus == 3 && (orderInfo.orderStatus == 1 || orderInfo.orderStatus == 2) && session_key_role_id != 4}">
						          <li class="dingdan-jiezhang bgf5">
							          <div class="qiandan neg-btn s-modal-control" style="width: 6.375rem;" onclick="detailModel(${orderDetail.detailId})">
							                                        删除
							          </div>
							          <div class="qiandan neg-btn s-modal-control" style="width: 6.375rem;" data-target="#yg-qiandan" onclick="freeClick(${orderDetail.detailId})">
						                                                   改价
			                          </div>
							      </li>
						      </c:if>
						      
			            </c:if>
			        </c:forEach>
			        <li class="dingdan-danhao ">
		                <span  class="danhao fl">
		                                                        单号: ${orderInfo.orderCode }
		                </span>
		                <span class="dingdan-price fr">
		                                                       合计: <span class="d-price">￥${orderInfo.discountAmount}</span>
		                </span>
			        </li>
		            
		            <div class="clearfix"></div>
		            <c:if test="${orderInfo.orderStatus == 1 || orderInfo.orderStatus == 2 || orderInfo.orderStatus == 5}">
		               <li class="dingdan-jiezhang">
		                  <c:if test="${orderInfo.orderStatus == 2}">
		                      <c:choose>
		                          <c:when test="${empty orderInfo.memberInfo.memberId }">
                                                                                                                    散客前台结账
		                          </c:when>
		                          <c:otherwise>
		                              <div class="qiandan normal-btn" onclick="notifyPayment(${orderInfo.orderId}, this)">
                                                                                                                             通知买单
                                      </div>
		                          </c:otherwise>
		                      </c:choose>
			              </c:if>
				          <div class="qiandan normal-btn" onclick="appendDetail(${orderInfo.orderId})">
				                                         增加项目
				          </div>
				        </li>
		            </c:if>
			      </ul>
		    </div>
		
		  </div>
		  
	        <!--服务移交-->
		  <div class=" s-modal-miss no-padding s-modal hide" id="yg-fuwu-yijiao-model" name= "">
			   <ul class="yijiao-ul bottom-ul clearfix" id = "yijiaoUL">
			        <li class="bottom-modal-title">
			            <span class="iconfont icon-fuwujiaohuan mr2"></span>
			            <span class=" gouwuche-name">服务移交</span>
			        </li>

			        <li class="paiwei-btn" id = "bottomLI">
			            <div class="queren btn" onclick="yijiaoConfirm(this)">确定移交</div>
			            <div class="quxiao btn" onclick="yijiaoCancel(this)">取消</div>
			        </li>
			    </ul>
			</div>
	        
	        <!-- 引入会员信息模版 -->
			<%@include file="memberInfoTemplate.jsp" %>
	        
	        <!--删除明细-->
			<div class="s-modal hide s-modal-miss" id="deleteDetailModel">
			    <div class="s-modal-wrap">
			        <div class="d-member-info">
			            <div class="n-modal-title text-center">
			                <span>是否确认删除该订单明细？(只有一条明细时，订单会自动取消。)</span>
			                <div class="clearfix"></div>
			            </div>
			            <div class="s-modal-foot">
			                <div class="modal-cancel" onclick="recover()">取消</div>
			                <div class="modal-confirm" onclick="deleteOrderDetail();">确认</div>
			            </div>
			        </div>
			    </div>
			</div>
			
			<!--挂起-->
			<div class="s-modal hide s-modal-miss" id="handUpModel">
			    <div class="s-modal-wrap">
			        <div class="d-member-info">
			            <div class="n-modal-title text-center">
			                <span></span>
			                <div class="clearfix"></div>
			            </div>
			            <div class="s-modal-foot">
			                <div class="modal-cancel" onclick="recover()">取消</div>
			                <div class="modal-confirm" onclick="confirmHandUp();">确认</div>
			            </div>
			        </div>
			    </div>
			</div>
			
			<!--改价-->
			<div class="s-modal-miss no-padding s-modal hide" id="yg-qiandan">
			    <ul class="yijiao-ul bottom-ul clearfix">
			        <li class="bottom-modal-title">
			            <span class="iconfont icon-shebaozhangtao mr2"></span>
			            <span class="gouwuche-name">改价</span>
			        </li>
			        <li class="paiwei-li">
			            <label class="paiwei-label">金额</label>
			            <input type="number" name = "freeAmount" class="dingdan-modal-big-length" placeholder="请输入金额"/><span class="iconfont icon-zhifuzongjine ml-5"></span>
			        </li>
			        
			        <li class="paiwei-li">
			            <label class="paiwei-label">备注</label>
			            <input type="text" name = "orderRemark" class="dingdan-modal-big-length" placeholder="请输入备注"/>
			        </li>
			        <li class="paiwei-btn">
			            <div class="queren btn" onclick="confirmFree()">确定改价</div>
			        </li>
			    </ul>
			</div>
		
  </div>
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=basePath%>js/base/big.js"></script>
<script>

var isAssignArray =new Array("一", "二", "三", "四", "五", "六", "七", "八", "九");

var orderDetailListStr = '${orderDetailListStr}';
orderDetailListStr = orderDetailListStr.replace(/\r/g,"&nbsp;");
orderDetailListStr = orderDetailListStr.replace(/\n/g,"<br />"); 
var orderDetailList = JSON.parse(orderDetailListStr);

var memberInfoStr = '${memberInfoStr}';
var memberInfoObj = eval("(" + memberInfoStr + ")");

var orderIdStr = '${orderId}';

var storeId = '${storeId}';

jQuery(".fix-p-select").on("click", function (event) {
    event.stopPropagation();
})

jQuery("body").delegate(".yijiao-ul", "click", function (event) {
    event.stopPropagation();
})

jQuery(".bottom-ul").on("click", function (event) {
    event.stopPropagation();
})

function changeProject (detailId) {
	
	/* $.ajax({
		type : "post",  
		url : baseUrl + "staff/action/selectProjectIsUpdate",
		data : "detailId="+detailId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var temp = document.createElement("form");
			temp.action = baseUrl + "staff/view/updateProjectList";
			temp.method = "post";
			temp.style.display = "none";
			
			var opt = document.createElement("textarea");
			opt.name = "detailId";
			opt.value = detailId;
			
			temp.appendChild(opt);
			document.body.appendChild(temp);
			temp.submit();
		}
	}); */
	
	var temp = document.createElement("form");
	temp.action = baseUrl + "staff/view/updateProjectList";
	temp.method = "post";
	temp.style.display = "none";
	
	var opt = document.createElement("textarea");
	opt.name = "detailId";
	opt.value = detailId;
	
	temp.appendChild(opt);
	document.body.appendChild(temp);
	temp.submit();
}

function appendDetail(orderId) {
    var temp = document.createElement("form");
    temp.action = baseUrl + "staff/view/appendDetail";
    temp.method = "post";
    temp.style.display = "none";
    
    var opt = document.createElement("textarea");
    opt.name = "orderId";
    opt.value = orderId;
    
    temp.appendChild(opt);
    
    document.body.appendChild(temp);
    temp.submit();
}

/* function editOrderInfo(detailId) {
    $("#nextSpan").parents(".paiwei-li").removeClass("hide");
    $("#endSpan").parents(".paiwei-li").removeClass("hide");
    $(".beau-radio").removeClass("active");
    $("#nextSpan").empty();
            
    for (var j = 0; j < orderDetailList.length; j++) {
        
        if (orderDetailList[j].detailId == detailId){
            $("#yg-fuwu-yijiao-model").attr("name", detailId);
            updateDetailId = detailId;
            var stepList = orderDetailList[j].stepList;
            $("#presentSpan").text(stepList[0].shiftMahjongName);
            $("#presentSpan").attr("name", stepList[0].shiftMahjongStepId);
            $("#presentSpan").attr("shiftMahjongId", stepList[0].shiftMahjongId);
            if (stepList.length == 1) {
                $("#nextSpan").parents(".paiwei-li").addClass("hide");
                $("#endSpan").parents(".paiwei-li").find(".beau-radio").addClass("active");
                $("#endSpan").attr("name", stepList[0].shiftMahjongStepId);
            }
            else {
                if (stepList[1].isOver != 3) {
                    $("#nextSpan").attr("name", stepList[1].shiftMahjongStepId);
                    $("#nextSpan").attr("shiftMahjongId", stepList[1].shiftMahjongId);
                    for (var k = 1; k < stepList.length; k++) {
                        if (stepList[k].isOver == 2) {
                            $("#nextSpan").append("<option value='"+stepList[k].shiftMahjongId+":"+stepList[k].shiftMahjongStepId+"' >"+stepList[k].shiftMahjongName+"</option>");
                        }
                    }
                    $("#endSpan").parents(".paiwei-li").addClass("hide");
                    $("#nextSpan").parents(".paiwei-li").find(".beau-radio").addClass("active");
                }
                else {
                    $("#nextSpan").parents(".paiwei-li").addClass("hide");
                    $("#endSpan").parents(".paiwei-li").find(".beau-radio").addClass("active");
                    $("#endSpan").attr("name", stepList[0].shiftMahjongStepId);
                }
            }
            
            return;
        }
    }
} */

function editOrderInfo(detailId) {
	$("#yg-fuwu-yijiao-model").find(".paiwei-li").remove();        
    for (var j = 0; j < orderDetailList.length; j++) {
        
        if (orderDetailList[j].detailId == detailId){
            $("#yg-fuwu-yijiao-model").attr("name", detailId);
            var stepList = orderDetailList[j].stepList;
            
            var currentType = 0;
            
            for (var i = 0; i < stepList.length; i++) {
            	var stepObj = stepList[i];
            	if (stepObj.isCurrent == 1) {
            		$("#bottomLI").before("<li class='paiwei-li' type = '1' shiftMahjongStepId = '"+stepObj.shiftMahjongStepId+"' shiftMahjongId = '"+stepObj.shiftMahjongId+"'>"+
				    			             "<label class='paiwei-label'>当前步骤</label>"+
				    			             "<span class='paiwei fw-current'>"+stepObj.shiftMahjongName+"</span>"+
				    			          "</li>");
            		var currentStep = stepObj;
            	}
            	else {
            		if (stepList[i].isOver != 3) {
            			if (currentType == 0) {
            				$("#bottomLI").before("<li class='paiwei-li' type = '2' shiftMahjongStepId = '"+stepList[i].shiftMahjongStepId+"' shiftMahjongId = '"+stepList[i].shiftMahjongId+"'>"+
				            			            "<label class='paiwei-label'>步骤"+isAssignArray[i]+"</label>"+
				            			            "<span class='paiwei fw-buzhou iconfont icon-xuanzhong'>"+stepList[i].shiftMahjongName+"</span>"+
				            			          "</li>");
            				currentType = 1;
            			}
            			else {
            				$("#bottomLI").before("<li class='paiwei-li' type = '2' shiftMahjongStepId = '"+stepList[i].shiftMahjongStepId+"' shiftMahjongId = '"+stepList[i].shiftMahjongId+"'>"+
					            			          "<label class='paiwei-label'>步骤"+isAssignArray[i]+"</label>"+
					            			          "<span class='paiwei'>"+stepList[i].shiftMahjongName+"</span>"+
					            			      "</li>");
            			}
            		}
            		else {
           				$("#bottomLI").before("<li class='paiwei-li' type = '0' shiftMahjongStepId = '"+stepList[i].shiftMahjongStepId+"' shiftMahjongId = '"+stepList[i].shiftMahjongId+"'>"+
			            			            "<label class='paiwei-label'>步骤"+isAssignArray[i]+"</label>"+
			            			            "<span class='overpaiwei' >"+stepList[i].shiftMahjongName+"</span>"+
			            			        "</li>");
            		}
            	}
            	
            	if (i + 1 == stepList.length) {

        			var str = "<li class='paiwei-li' type = '3' shiftMahjongStepId = '"+currentStep.shiftMahjongStepId+"' shiftMahjongId = '"+currentStep.shiftMahjongId+"'>"+
					             "<label class='paiwei-label'></label>";
					             
        			if (currentType == 0) {
        				str = str + "<span class='paiwei fw-buzhou iconfont icon-xuanzhong''>完成</span>"+
			                      "</li>";
        			}
        			else {
        				str = str + "<span class='paiwei'>完成</span>"+
	                              "</li>";
        			}
        			$("#bottomLI").before(str);
        		}
            	/* if (i == 0) {
            		$("#bottomLI").before("<li class='paiwei-li' type = '1' shiftMahjongStepId = '"+stepList[0].shiftMahjongStepId+"' shiftMahjongId = '"+stepList[0].shiftMahjongId+"'>"+
				    			            "<label class='paiwei-label'>当前步骤</label>"+
				    			            "<span class='paiwei fw-current'>"+stepList[0].shiftMahjongName+"</span>"+
				    			        "</li>");
            	}
            	
            	if (stepList.length == 1) {
            		$("#bottomLI").before("<li class='paiwei-li' type = '3' shiftMahjongStepId = '"+stepList[0].shiftMahjongStepId+"' shiftMahjongId = '"+stepList[0].shiftMahjongId+"'>"+
				    			            "<label class='paiwei-label'></label>"+
				    			            "<span class='paiwei fw-buzhou iconfont icon-xuanzhong'>完成</span>"+
				    			        "</li>");
            		return;
            	}
            	
            	if (i > 0) {
            		var b = 0;
            		if (stepList[i].isOver != 3) {
            			if (i == 1) {
            				$("#bottomLI").before("<li class='paiwei-li' type = '2' shiftMahjongStepId = '"+stepList[i].shiftMahjongStepId+"' shiftMahjongId = '"+stepList[i].shiftMahjongId+"'>"+
				            			            "<label class='paiwei-label'>步骤"+isAssignArray[i]+"</label>"+
				            			            "<span class='paiwei fw-buzhou iconfont icon-xuanzhong'>"+stepList[i].shiftMahjongName+"</span>"+
				            			        "</li>");
            			}
            			else {
            				$("#bottomLI").before("<li class='paiwei-li' type = '2' shiftMahjongStepId = '"+stepList[i].shiftMahjongStepId+"' shiftMahjongId = '"+stepList[i].shiftMahjongId+"'>"+
				            			            "<label class='paiwei-label'>步骤"+isAssignArray[i]+"</label>"+
				            			            "<span class='paiwei'>"+stepList[i].shiftMahjongName+"</span>"+
				            			        "</li>");
            			}
            		}
            		else {
            			var a = i - 1;
            			var str = "<li class='paiwei-li' type = '3' shiftMahjongStepId = '"+stepList[0].shiftMahjongStepId+"' shiftMahjongId = '"+stepList[0].shiftMahjongId+"'>"+
						             "<label class='paiwei-label'></label>";
						             
            			if (i == 1) {
            				str = str + "<span class='paiwei fw-buzhou iconfont icon-xuanzhong''>完成</span>"+
    			                      "</li>";
            			}
            			else {
            				str = str + "<span class='paiwei'>完成</span>"+
		                              "</li>";
            			}
            			$("#bottomLI").before(str);
            			return;
            		}
            		
            		if (i + 1 == stepList.length && stepList[i].isOver != 3) {
            			$("#bottomLI").before("<li class='paiwei-li' type = '3' shiftMahjongStepId = '"+stepList[0].shiftMahjongStepId+"' shiftMahjongId = '"+stepList[0].shiftMahjongId+"'>"+
									             "<label class='paiwei-label'></label>"+
				            					 "<span class='paiwei'>完成</span>"+
					                          "</li>");
            		}
            	} */
            	
            	
            	
            }
            return;
        }
    }
}


$("#yijiaoUL").delegate(".paiwei-li", "click", function (event) {
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	var paiweiLI = $(obj).parents(".paiwei-li");
	if ($(paiweiLI).attr("type") == 0) {
		return;
	}
	
	$("#yg-fuwu-yijiao-model").find(".paiwei-li .paiwei").removeClass("iconfont");
	$("#yg-fuwu-yijiao-model").find(".paiwei-li .paiwei").removeClass("icon-xuanzhong");
	$("#yg-fuwu-yijiao-model").find(".paiwei-li .paiwei").removeClass("fw-buzhou");
	var paiwei = $(obj).parents(".paiwei-li").find(".paiwei");
	if (paiwei.hasClass("fw-current")) {
		paiwei.addClass("iconfont");
		paiwei.addClass("icon-xuanzhong");
	}
	else {
		paiwei.addClass("iconfont");
		paiwei.addClass("icon-xuanzhong");
		paiwei.addClass("fw-buzhou");
	}
});


//取消
function yijiaoCancel(obj) {
    $(obj).parents(".yijiao-ul").parent().addClass("hide");
}

var hangUpDetailId = null;
var hangUpShiftMahjongStepId = null;
var hangUpType = null;
var hangUpObj = null;
function hangUpOrder(detailId, shiftMahjongStepId, type, obj) {
	if (type == 1) {
		$("#handUpModel").find(".n-modal-title").find("span").text("是否确认挂起该订单明细？(挂起后服务员工状态将为空闲，自动派单。)");
		$("#handUpModel").removeClass("hide");
	}
	else {
		$("#handUpModel").find(".n-modal-title").find("span").text("是否取消挂起该订单明细？(取消挂起后服务员工状态将恢复状态。)");
		$("#handUpModel").removeClass("hide");
	}
	hangUpDetailId = detailId;
	hangUpShiftMahjongStepId = shiftMahjongStepId;
	hangUpType = type;
	hangUpObj = obj;
}

function confirmHandUp() {
	$.ajax({
		type : "post",  
		url : baseUrl + "staff/action/hangUpOrder",
		data : "detailId="+hangUpDetailId + "&shiftMahjongStepId="+hangUpShiftMahjongStepId +"&type="+hangUpType,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			if (hangUpType == 1) {
				dialog("挂起完成, 1.5秒后刷新页面...");
			}
			else {
				dialog("取消挂起完成, 1.5秒后刷新页面...");
			}
			setTimeout(function(){
				var temp = document.createElement("form");
  			    temp.action = baseUrl + "staff/view/selectOrderDetail/${session_key_store_account}/2";
  			    temp.method = "post";
  			    temp.style.display = "none";
  			    var opt = document.createElement("textarea");
  			    opt.name = "orderId";
  			    opt.value = orderIdStr;
  			    temp.appendChild(opt);
  			    document.body.appendChild(temp);
  			    temp.submit();
			}, 1500);
		}
	});
}

function yijiaoConfirm(obj){
	var parentObj = $("#yg-fuwu-yijiao-model").find(".icon-xuanzhong").parents(".paiwei-li");
    var type = parentObj.attr("type");
    var shiftMahjongStepId = "";
    var shiftMahjongId = "";
    
    shiftMahjongStepId = parentObj.attr("shiftMahjongStepId");
    
    shiftMahjongId = parentObj.attr("shiftMahjongId");
 
    var detailId = $("#yg-fuwu-yijiao-model").attr("name");
    
    if (type != 3) {
    	var temp = document.createElement("form");
        temp.action = baseUrl + "staff/view/serverAssociate";
        temp.method = "post";
        temp.style.display = "none";
        
        var opt = document.createElement("textarea");
        opt.name = "shiftMahjongStepId";
        opt.value = shiftMahjongStepId;
        
        var tpt = document.createElement("textarea");
        tpt.name = "type";
        tpt.value = type;
        
        var dpt = document.createElement("textarea");
        dpt.name = "detailId";
        dpt.value = detailId;
        
        var spt = document.createElement("textarea");
        spt.name = "shiftMahjongId";
        spt.value = shiftMahjongId;
        
        
        temp.appendChild(opt);
        temp.appendChild(tpt);
        temp.appendChild(dpt);
        temp.appendChild(spt);
        
        document.body.appendChild(temp);
        temp.submit(); 
    }
    else {
    	$.ajax({
    		type : "post",  
    		url : baseUrl + "staff/action/finishOrderDetail",
    		data : "shiftMahjongStepId="+shiftMahjongStepId+"&detailId="+detailId,
    		dataType : "json",
    		success : function(e){
    			if(e.code != 0){
    				dialog(e.msg);
    				return;
    			}
   				dialog("已完成, 1.5秒后刷新页面...");
   				setTimeout(function(){
   					var temp = document.createElement("form");
       			    temp.action = baseUrl + "staff/view/selectOrderDetail/${session_key_store_account}/2";
       			    temp.method = "post";
       			    temp.style.display = "none";
       			    var opt = document.createElement("textarea");
       			    opt.name = "orderId";
       			    opt.value = orderIdStr;
       			    temp.appendChild(opt);
       			    document.body.appendChild(temp);
       			    temp.submit();
   				}, 1500);
    		}
    	});
    }

}

//换人
function updateEmployee(detailId, shiftMahjongStepId){

    var temp = document.createElement("form");
    temp.action = baseUrl + "staff/view/waitingCentreShiftMahjong";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("textarea");
    opt.name = "detailId";
    opt.value = detailId;
    var tpt = document.createElement("textarea");
    tpt.name = "shiftMahjongStepId";
    tpt.value = shiftMahjongStepId;
    temp.appendChild(opt);
    temp.appendChild(tpt);
    document.body.appendChild(temp);
    temp.submit();
}

var deleteDetailId = "";

var deleteOrderId = "";

function detailModel(detailId) {
	$("#deleteDetailModel").removeClass("hide");
	deleteDetailId = detailId;
}


function deleteOrderDetail(){
	$.ajax({
		type : "post",  
		url : baseUrl + "staff/action/deleteOrderDetail",
		data : "detailId="+deleteDetailId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			if (e.msg == 1) {
				dialog("删除成功, 1.5秒后刷新页面...");
				setTimeout(function(){
					var temp = document.createElement("form");
				    temp.action = baseUrl + "staff/view/selectOrderDetail/${session_key_store_account}/2";
				    temp.method = "post";
				    temp.style.display = "none";
				    var opt = document.createElement("textarea");
				    opt.name = "orderId";
				    opt.value = orderIdStr;
				    temp.appendChild(opt);
				    document.body.appendChild(temp);
				    temp.submit();
				}, 1500);
			}
			else {
				dialog("删除成功,订单已取消, 1.5秒后跳转至接待中心...");
				setTimeout(function(){
					window.location.href = baseUrl + "staff/view/reception";
				}, 1500);
			}
		}
	});
 }
 
 function recover() {
	 $("#deleteDetailModel").addClass("hide");
	 $("#handUpModel").addClass("hide");
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

 var detailIdFree = "";
 
 //原折扣价格
 var oldDiscountAmount = 0;
 
 //点击改价
 function freeClick(detailId) {
 	
	$("input[name='freeAmount']").val("");
	$("input[name='orderRemark']").val("");
	detailIdFree = detailId;
 	$.ajax({
         url : baseUrl + "staff/action/selectOrderDetail",
         type : "POST",
         data : "detailId=" + detailId,
         success : function(e){
             if (e.code != 0) {
                 dialog(e.msg);
                 return;
             }
             var orderDetail = e.msg;
             $("input[name='freeAmount']").val(orderDetail.discountAmount);
             $("input[name='orderRemark']").val(orderDetail.orderRemark);
             
             oldDiscountAmount = parseFloat(orderDetail.discountAmount) - parseFloat(orderDetail.freeAmount);
         }
     });
 }
 
 function confirmFree() {
	var freeAmount = $("input[name='freeAmount']").val();
	var orderRemark = $("input[name='orderRemark']").val();
	if (isEmpty(freeAmount)) {
		dialog("改单金额不能为空！");
		return;
	}
	if (isEmpty(orderRemark)) {
		dialog("改单备注不能为空！");
		return;
	}
    if (parseFloat(freeAmount) < 0) {
    	dialog("改单金额不能为负值！");
    	return;
    }
    var newFreeAmount = parseFloat(freeAmount) - oldDiscountAmount;
    newFreeAmount = new Big(newFreeAmount);
    newFreeAmount = newFreeAmount.toFixed(2);
	$.ajax({
        url : baseUrl + "staff/action/saveFreeInfo",
        type : "POST",
        data : "detailId=" + detailIdFree +"&discountAmount="+freeAmount+ "&amount= " + newFreeAmount + "&remark=" + orderRemark,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("改单成功, 1.5秒后刷新页面...");
			setTimeout(function(){
				var temp = document.createElement("form");
			    temp.action = baseUrl + "staff/view/selectOrderDetail/${session_key_store_account}/2";
			    temp.method = "post";
			    temp.style.display = "none";
			    var opt = document.createElement("textarea");
			    opt.name = "orderId";
			    opt.value = orderIdStr;
			    temp.appendChild(opt);
			    document.body.appendChild(temp);
			    temp.submit();
			}, 1500);
        }
    });
 }
</script>
</body>
</html>