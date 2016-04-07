<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>项目合并</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
</head>
<body>
<div class="wrap">
<div class="content">
    <!--员工步骤合并页面-->
	<div class="yg-buzhou-all">
	    <div class="dingdan-person">
	        <ul class="dengdai-ul" >
	            <li class="dengdai-list">
	                <div class="img-warp">
	                    <img src="<%=picPath%>${oldOrderDetailDto.projectImage}" alt=""/>
	                </div>
	                <div class="list-desc">
	                    <div class="list-name">${oldOrderDetailDto.projectName} <span class="list-price">￥${oldOrderDetailDto.discountAmount}</span></div>
	                    
	                    <c:forEach items="${oldOrderDetailDto.stepList}" var="step">
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
					                    <c:if test="${step.isOver == 1 }">
					                        <span class="state doing">服务中</span>
					                    </c:if>
					                    <c:if test="${step.isOver == 2 }">
					                        <span class="state waiting">等待中</span>
					                    </c:if>
					                    <span class="time">${step.beginTime}</span>
					                </div>
		                        </c:if>
		                </c:forEach>
	                </div>
	            </li>
	        </ul>
	    </div>
	    <div class="Service">
	            <div class="Service-title clearfix">
	                <div>${project.projectName}:服务步骤<span>（点击以下步骤匹配已服务步骤）</span></div>
	
	            </div>
	            <ul class="Service-main">
	                <c:forEach items="${projectStepList}" var="projectStep" varStatus="projectStepStatus">
                         <li class = "wait">
		                    <i class="bz-number">${projectStep.projectStepOrder}</i>
		                    <div class="paiwei fw-current" name = "paiweiDIV" projectStepId = "${projectStep.projectStepId}" style="cursor: pointer">
		                        <span>${projectStep.projectStepName}</span> 
		                    </div>
		                </li>	                
	                </c:forEach>
	            </ul>
	    </div>
	    <div class="buzhou-foot">
	        <div class="qiandan normal-btn" onclick="submits()">
	                                选择人员
	        </div>
	        <!-- <div class="qiandan neg-btn " >
	                                取消
	        </div> -->
	    </div>
	</div>
    
    <div class="no-padding s-modal hide" id="yg-buzhou-modal" style="cursor: pointer">
	   <div class="yg-buzhou-wrap">
	       <div class="Service" name = "modalDIV">
	           <div class="Service-title clearfix">
	               <div>原项目已服务步骤</div>
	           </div>
	           <ul class="Service-main">
	               <c:forEach items="${orderDetailDto.stepList}" var="step" varStatus="stepStatus">
		               <c:choose>
			               <c:when test="${stepStatus.index == 0}">
			                   <c:if test="${step.isOver == 1 || step.isOver == 3}">
			                       <li projectStepId = "" onclick="chooseStep(this, ${step.shiftMahjongStepId}, ${step.employeeInfo.employeeId}, ${step.employeeInfo.employeeCode}, '${step.employeeInfo.name}', ${step.isOver})">
				                       <i class="iconfont icon-xuanzhong hide"></i>
				                       <i class="bz-number">${step.projectStepOrder}</i>
					                   <div class="paiwei fw-current">
					                      <span>${step.projectStepName}</span> 
					                      <span>${step.shiftMahjongName}</span>
					                      <span class="s-number">${step.employeeInfo.employeeCode}</span> 
					                      <span>${step.employeeInfo.name}</span> 
					                      <c:if test="${step.isOver == 1}">
					                         <span class="yifuwu">服务中</span>
					                      </c:if>
					                      <c:if test="${step.isOver == 3}">
					                         <span class="state">已完成</span>
					                      </c:if>
					                   </div>
					              </li>
			                   </c:if>
	                       </c:when>
	                       <c:otherwise>
	                           <c:if test="${step.isOver == 1 || step.isOver == 3}">
		                          <li projectStepId = "" onclick="chooseStep(this, ${step.shiftMahjongStepId}, ${step.employeeInfo.employeeId}, ${step.employeeInfo.employeeCode}, '${step.employeeInfo.name}', ${step.isOver})">
		                               <i class="iconfont icon-xuanzhong hide"></i>
				                       <i class="bz-number">${step.projectStepOrder}</i>
					                   <div class="paiwei fw-current">
						                   <span>${step.projectStepName}</span> 
					                       <span>${step.shiftMahjongName}</span>
					                       <span class="s-number">${step.employeeInfo.employeeCode}</span> 
					                       <span>${step.employeeInfo.name}</span> 
						                   <c:if test="${step.isOver == 1}">
					                         <span class="yifuwu">服务中</span>
					                       </c:if>
					                       <c:if test="${step.isOver == 3}">
					                         <span class="state">已完成</span>
					                       </c:if>
					                   </div>
					              </li>
		                       </c:if>
	                       </c:otherwise>
                       </c:choose>
	               </c:forEach>
	           </ul>
	           <div class="buzhou-foot">
	               <div class="qiandan normal-btn" onclick="confirmChooseStep()">
	                                                         确定
	               </div>
	               <div class="qiandan neg-btn s-modal-control" onclick="cancelModal()">
	                                                        取消
	               </div>
	           </div>
	       </div>
	   </div>
	</div>
	
	<!--删除明细-->
	<div class="s-modal hide s-modal-miss" id="deleteModel">
	    <div class="s-modal-wrap">
	        <div class="d-member-info">
	            <div class="n-modal-title text-center">
	                <span>您还有原项目已服务步骤未合并，继续本操作，该步骤将删除，影响原服务人员的业绩提成！</span>
	                <div class="clearfix"></div>
	            </div>
	            <div class="s-modal-foot">
	                <div class="modal-cancel" onclick="recover()">取消</div>
	                <div class="modal-confirm" onclick="trueSubmits();">确认</div>
	            </div>
	        </div>
	    </div>
	</div>
</div>    
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script>

var projectInfo = '${projectInfo}';
var detailId = "${detailId}";

var widowHeight = $(window).height();
var topHeight=$(".dengdai-ul").height();
var SelectHeight=widowHeight-topHeight - 10;
$(".Service").css({"min-height":SelectHeight});

//全局变量
var projectStepId = "";

$("html").delegate("body:not('div[name='modalDIV']')", "click", function(){
	$("#yg-buzhou-modal").addClass("hide");
});

$("body").delegate("div[name = 'paiweiDIV']", "click", function (event) {
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	
	$("#yg-buzhou-modal").removeClass("hide");
    var clientHeight = $(window).height();
    var bzHeight=$(".yg-buzhou-wrap").height();
    var mH = clientHeight/2 -bzHeight/2;
    $(".yg-buzhou-wrap").css("margin-top",mH);
    
    var oldLI = $("#yg-buzhou-modal").find("li");
    
    for (var i = 0; i < oldLI.length; i++) {
    	if (!isEmpty($(oldLI[i]).attr("projectstepid"))) {
    		$(oldLI[i]).addClass("hide");
    	}
    }
    
	projectStepId = $(obj).attr("projectstepid");
	
	if (isEmpty(projectStepId)) {
		projectStepId = $(obj).parents(".fw-current").attr("projectstepid");
	}
	
	var objLI = $("li[projectStepId = '"+projectStepId+"']");
	$(objLI).removeClass("hide");
	$(objLI).addClass("active");
	$(objLI).find(".icon-xuanzhong").removeClass("hide");
	$(objLI).find(".bz-number").addClass("hide");
    event.stopPropagation();
});

function cancelModal() {
	$("#yg-buzhou-modal").addClass("hide");
}

$("div[name='modalDIV']").click(function () {
	event.stopPropagation();
})

var employee = {};

function  chooseStep(obj, shiftMahjongStepId, employeeId, employeeCode, name, isOver) {
	
	if ($(obj).hasClass("active")) {
		$(obj).removeClass("active");
		$(obj).find(".icon-xuanzhong").addClass("hide");
		$(obj).find(".bz-number").removeClass("hide");
		return;
	}
	
	//初始化选中状态
	$(obj).siblings().removeClass("active");
	$("#yg-buzhou-modal").find(".icon-xuanzhong").addClass("hide");
	$("#yg-buzhou-modal").find(".bz-number").removeClass("hide");
	
	$(obj).addClass("active");
	$(obj).find(".icon-xuanzhong").removeClass("hide");
	$(obj).find(".bz-number").addClass("hide");
	
	employee = {"shiftMahjongStepId" : shiftMahjongStepId, "employeeId" : employeeId, "employeeCode" : employeeCode, "name" : name, "obj" : obj, "isOver" : isOver};
	event.stopPropagation();
}

function confirmChooseStep() {
	
	if (isEmpty(employee.employeeId)) {
		$("div[projectStepId ='"+projectStepId+"']").parents("li").addClass("wait");
		var span = $("div[projectStepId ='"+projectStepId+"']").find("span");
	    $(span[0]).siblings().remove();
	    $("li[projectStepId = '"+projectStepId+"']").attr("projectStepId", "");
		cancelModal();
		return;
	}
	
	var obj = employee.obj;
	$(obj).addClass("hide");
	$("li[projectStepId = '"+projectStepId+"']").attr("projectStepId", "");
	$(obj).attr("projectStepId" , projectStepId);
	
	$("div[projectStepId ='"+projectStepId+"']").parents("li").removeClass("wait");
	
	var str = "<span class='s-number' employeeId = '"+employee.employeeId+"' isOver = '"+employee.isOver+"' shiftMahjongStepId = '"+employee.shiftMahjongStepId+"'>"+employee.employeeCode+"</span>"+
			  "<span>"+employee.name+"</span>";
    if (employee.isOver == 1) {
    	str = str + "<span class='yifuwu'>服务中</span>";
    }
    else {
    	str = str + "<span class='state'>已完成</span>";
    }
	
    var span = $("div[projectStepId ='"+projectStepId+"']").find("span");
    $(span[0]).siblings().remove();
	$("div[projectStepId ='"+projectStepId+"']").append(str);
	
	cancelModal();
	employee = {};
}

function submits(){
	var oldLI = $("#yg-buzhou-modal").find("li");
    
	for (var i = 0; i < oldLI.length; i++) {
    	if (isEmpty($(oldLI[i]).attr("projectstepid"))) {
    		$("#deleteModel").removeClass("hide");
    		return;
    	}
    }
	
    trueSubmits();
}

function trueSubmits(){
	
	var chooseWait = $(".yg-buzhou-all").find(".Service-main li");
	
	var arrayObj = new Array();
	
	for (var i = 0; i < chooseWait.length; i++) {
		if (!$(chooseWait[i]).hasClass("wait")) {
			var stepid = $(chooseWait[i]).find(".fw-current").attr("projectstepid");
			var employeeid = $(chooseWait[i]).find(".s-number").attr("employeeid");
			var isOver =  $(chooseWait[i]).find(".s-number").attr("isOver");
			var shiftMahjongStepId = $(chooseWait[i]).find(".s-number").attr("shiftMahjongStepId");
			var objStr = {"projectStepId" : stepid, "employeeId" : employeeid, "isOver" : isOver, "shiftMahjongStepId" : shiftMahjongStepId};
			arrayObj.push(objStr);
		}
	}
	var chooseStep = JSON.stringify(arrayObj);
	
	var mark = 0;
	
	$.ajax({
        url : baseUrl + "staff/action/checkoutStep",
        type : "POST",
        data : "chooseStep=" + chooseStep,
        async : false,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                mark = 1;
                return;
            }
        }
    });
	
	if (mark == 1) {
		return;
	}
	
	var temp = document.createElement("form");
	temp.action = baseUrl + "staff/view/confirmUpdateProject";
	temp.method = "post";
	temp.style.display = "none";
	var opt = document.createElement("textarea");
	opt.name = "detailId";
	opt.value = detailId;
	var tpt = document.createElement("textarea");
	tpt.name = "projectInfo";
	tpt.value = projectInfo;
	var ppt = document.createElement("textarea");
	ppt.name = "chooseStep";
	ppt.value = chooseStep;
	temp.appendChild(opt);
	temp.appendChild(tpt);
	temp.appendChild(ppt);
    document.body.appendChild(temp);
	temp.submit();
}

</script>
</body>
</html>