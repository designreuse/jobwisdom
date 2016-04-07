<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>选择服务人员</title>
    <link rel="stylesheet" href="<%=swiperCssPath%>"/>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
    <style>
	    .lunpai-project-title .waiting-num {
	        float: right;
	        margin-top: 2rem;
	    }
	  .p-name{
	      height: 2.625rem;
	      line-height: 2.625rem;
	      padding: 0 3.625rem 0 1rem;
	      background-color: #fdf7e5;
	      border: 1px solid #ecb200;
	      border-top-left-radius: 2px;
	      border-bottom-left-radius: 2px;
	      border-top-right-radius: 1.3125rem;
	      border-bottom-right-radius: 1.3125rem;
	      position: relative;
	      text-align: center;
	      color: #666;
	      font-size: 1.25rem;
	      box-sizing: border-box;
	      border-right: 0;
	  }
	    .p-num{
	        position: absolute;
	        right: 0px;
	        top:-1px;
	        width: 2.625rem;
	        height: 2.625rem;
	        line-height: 2.625rem;
	        border: 1px solid #ecb200;
	        border-radius: 1.3125rem;
	        color: #ecb200;
	        font-size: 1.25rem;
	        box-sizing: border-box;
	
	    }
    	 .head img {
	        width:2.875rem;
	        height: 2.875rem;
	        vertical-align: middle;
	        border-radius:50%; 
	     }
	
	     .user-name {
	       display: initial;
	     }
	</style>
</head>
<body>
<div class="wrap">
<div class="head text-center">
  <c:choose>
  	 <c:when test="${not empty memberBaseDto.memberId and not empty memberBaseDto.headUrl }">
  		<img src="<%=picPath%>${memberBaseDto.headUrl}" alt=""/>
  	 </c:when>
     <c:when test="${memberBaseDto.sex == '女' }">
         <span class="iconfont icon-womanuser fs30 ml5"></span>
     </c:when>
     <c:otherwise>
         <span class="iconfont icon-manuser fs30 ml5"></span>
     </c:otherwise>
  </c:choose>
  <span class="user-name s-modal-control" onclick="showMemberInfo(${memberBaseDto.memberId })">${memberBaseDto.name }</span>
</div>

<div class="content pt5 mb-footer">
	<div class="bd-gray">
	    <c:forEach items="${list}" var="projectObj" varStatus="listStatus">
	       <div class="lunpai-project">
	            <div class="lunpai-project-part">
	                <div class="lunpai-project-title">
	                    <img src="<%=picPath%>${projectObj.projectImage}?imageView2/1/w/136/h/136" alt=""/>
	                    <span class="name">${projectObj.projectName}</span>
	                    <div class="waiting-num" onclick="setServeStep(this)">
	                        <div class="p-name">
		                        <span name = "stepName" projectNum = "${listStatus.index}" projectId = "${projectObj.projectId}" shiftStatus = "1">${projectObj.shiftMahjongDtoList[0].shiftMahjongName}</span>
		                        <div class="p-num">1</div>
		                    </div>
		                </div>
	                    <div class="clearfix"></div>
	                </div>
	            </div>
	            <div class="lunpai-content">
                    <div class="lunpai-kind-project">
                        <ul class="kind-project-ul">
                            <c:forEach items="${projectObj.shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="shiftStatus">
                                <c:choose>
                                    <c:when test="${shiftMahjongDto.stepNum == 1}">
                                        <li class="item active" shiftStatus = '${shiftMahjongDto.stepNum}' onclick="switchoverDIV(this, ${listStatus.index}, ${shiftMahjongDto.stepNum})">${shiftMahjongDto.shiftMahjongName}</li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="item" shiftStatus = '${shiftMahjongDto.stepNum}' onclick="switchoverDIV(this, ${listStatus.index}, ${shiftMahjongDto.stepNum})">${shiftMahjongDto.shiftMahjongName}</li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                    <hr>
	                 <c:forEach items="${projectObj.shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="shiftStatus">
	                   <div <c:if test="${shiftMahjongDto.stepNum == 1}">class="lunpai-people"</c:if> <c:if test="${shiftMahjongDto.stepNum != 1}">class="lunpai-people hide"</c:if> name= "${shiftMahjongDto.stepNum}">
	                        <div class="swiper-container-daohang lunpai lunpaiHead${listStatus.index}${shiftMahjongDto.stepNum}" size="${fn:length(shiftMahjongDto.shiftMahjongEmployeeList)}">
	                            <div class="swiper-wrapper lunpaiBody${listStatus.index}${shiftMahjongDto.stepNum }">
	                               <c:forEach items="${shiftMahjongDto.shiftMahjongEmployeeList}" var="shiftMahjongEmployee" varStatus="status">
	                                  <div class="swiper-slide score-shop-li line-height-center s-modal-control" data-target="#yg-rotating" onclick="chooseEmployee(this)">
	                                        <div class="lunpai-img" name= "${shiftMahjongEmployee.shiftMahjongEmployeeId}" projectNum = "${listStatus.index}" orderNum = "${shiftMahjongDto.stepNum}" projectId = "${projectObj.projectId}" employeeId = "${shiftMahjongEmployee.employeesId}" isType = "0">
	                                            <img src="<%=picPath%>${shiftMahjongEmployee.headImage}"/>
	                                            <div class="lunpai-yg-name">${shiftMahjongEmployee.employeeCode} ${shiftMahjongEmployee.name}</div>
	                                        </div>
	                                        <div class="lunpai-state" name= "${shiftMahjongEmployee.state}">
	                                            <div class="lunpai-people-state">
	                                                <div class="center kongxianzhong">
	                                                    <div class="zhuangtai">
	                                                        <span class="iconfont icon-iconkafei"></span>
	                                                    </div>
	                                                    <div class="zhuangtai-word"></div>
	                                                    <div class="select-word-wrap">
			                                                <span><c:if test="${shiftMahjongEmployee.appointNumber == 0}">未指定</c:if><c:if test="${shiftMahjongEmployee.appointNumber != 0}">指定+${shiftMahjongEmployee.appointNumber}</c:if></span>
			                                            </div>
	                                                    <div class="clearfix"></div>
	                                                </div>
	                                            </div>
	                                            <div class="clearfix"></div>
	                                        </div>
	                                   </div>
	                               </c:forEach>
	                            </div>
	                        </div>
	                    </div>
	                 </c:forEach>
	            </div>
	        </div>
	    </c:forEach>
	</div>
	
	<div class="footer-gouwuche" id= "gouwucheInfo">
	    <div class="gouwuche" >
	        <span class="iconfont icon-gouwuche"></span>
	        <span class="s-badge">${number}</span>
	    </div>
	    <div class="shop-price">
	        <span class="">共计: <span class="num-emphasize">￥ ${totilMoney}</span>元</span>
	    </div>
	    <div class="goumai-btn">
	        <button class="btn" onclick="submits()">开始服务</button>
	    </div>
	</div>
	
	<div class="hide s-modal-miss no-padding s-gouwuche-modal" id="yg-gouwuche-modal">
	    <ul class="gouwuche-ul">
	        <li class="touwuche-title clearfix">
	            <span class="fl gouwuche-name">购物车</span>
	            
	        </li>
	        <c:forEach items="${showList}" var="show" varStatus="status">
	            <li class="gouwu-li clearfix">
	                <span class="shop-name fl">${show.projectName}</span>
	                <div class="num-control fr">
	                    <span class="ml mr">${show.projectCount}</span>
	                </div>
	                <span class="shop-price fr mr2">￥ ${show.projectPrice}</span>
	            </li>
	        </c:forEach>
	    </ul>
	</div>
	    
    <div class="no-padding s-modal hide" id="yg-buzhou-modal">
	   <div class="yg-buzhou-wrap">
	       <div class="Service" name = "modalDIV">
	           <div class="Service-title clearfix">
	               <div>选择开始服务的第一项步骤</div>
	           </div>
	           <ul class="Service-main">
	           </ul>
	           <div class="buzhou-foot">
	               <div class="qiandan normal-btn" onclick="confirmChoose()">
	                                                        确定
	               </div>
	               <div class="qiandan neg-btn s-modal-control" onclick="cancelModal()">
	                                                       取消
	               </div>
	           </div>
	       </div>
	   </div>
	</div>
	
	<!-- 引入会员信息模版 -->
	<%@include file="memberInfoTemplate.jsp" %>
	
	<div class="hide s-modal-miss no-padding s-gouwuche-modal" id="yg-rotating">
	    <div class="yg-state yg-rotating">
	        <ul id = "rotatingStateUl">

	        </ul>
	    </div>
    </div>
</div>
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script>
    
	var clientHeight = $(window).height();
	var state=$(".state-item").height();
	var mH = clientHeight/2 -state/2;
	$(".yg-state").css("margin-top",mH);
	
	
	var widowHeight = $(window).height();
	var topHeight=$(".dengdai-ul").height();
	var SelectHeight=widowHeight-topHeight - 200;
	$(".Service").css({"min-height":SelectHeight});
	
    var objString = '${objString}';
    
    var orderId = '${orderId}';
    
    var totilSize = '${totilSize}';
    
    var stateList = new Array();
    var a1 = {"state":"0","name":"工作中","styles":"gongzuozhong","icon":"icon-scissors"};
    var a2 = {"state":"1","name":"空闲中","styles":"kongxianzhong","icon":"icon-iconkafei"};
    var a3 = {"state":"2","name":"暂时离开","styles":"zanshilikai","icon":"icon-bbgshizhong"};
    var a4 = {"state":"3","name":"离开","styles":"likai","icon":"icon-ico-date"};
    var a5 = {"state":"4","name":"指定服务","styles":"zhidingfuwu","icon":"icon-zhiding"};
    
    stateList[0] = a1;
    stateList[1] = a2;
    stateList[2] = a3;
    stateList[3] = a4;
    stateList[4] = a5;
    
    $(function () {
    	for (var i = 0; i < totilSize; i++) {
            var s = new Swiper(".lunpaiHead" + i + 1, {
                slidesPerView: 'auto',
                paginationClickable: true,
                spaceBetween: 0,
                freeMode: true
            });
        }
        
        $(".s-modal-control").on("click", function(){
            $("body").css("overflow","hidden");
        });
        $(".s-modal-miss").on("click", function () {
            if(!$(".s-gouwuche-modal").hasClass("hide")) {
                $("body").css("overflow-y", "auto");
            }
            $(".s-gouwuche-modal").addClass("hide");
        })
        //渲染员工状态
        var stateObj = $(".lunpai-state");
        for (var i = 0; i < stateObj.length; i++) {
            var num = $(stateObj[i]).attr("name");
            
            for (var k = 0; k < stateList.length; k++) {
                if(stateList[k].state == num){
                    $(stateObj[i]).find(".center").removeClass("kongxianzhong");
                    $(stateObj[i]).find(".center").addClass(stateList[k].styles);
                    $(stateObj[i]).find(".iconfont").addClass("icon-iconkafei");
                    $(stateObj[i]).find(".iconfont").addClass(stateList[k].icon);
                    $(stateObj[i]).find(".zhuangtai-word").text("");
                    $(stateObj[i]).find(".zhuangtai-word").text(stateList[k].name);
                }
            }
        }
    })
    
    $("html").delegate("body:not('div[name='modalDIV']')", "click", function(){
		$("#yg-buzhou-modal").addClass("hide");
	});

    function cancelModal() {
    	$("#yg-buzhou-modal").addClass("hide");
    }

    $("div[name='modalDIV']").click(function () {
    	event.stopPropagation();
    })
    
    var parentsObj = "";
    
    function setServeStep (obj) {
    	parentsObj = jQuery(obj).parents(".lunpai-project");
    	
    	var itemObj = jQuery(obj).parents(".lunpai-project").find(".item");
    	
    	var chooseNum = jQuery(obj).find("span[name='stepName']").attr("shiftStatus"); 
    	
    	jQuery(".Service-main").empty();
    	for (var i = 0; i < itemObj.length; i++) {
    		var shiftMahjongName = jQuery(itemObj[i]).text();
    		var num = jQuery(itemObj[i]).attr("shiftStatus");
    		if (parseInt(chooseNum) == num) {
    			jQuery(".Service-main").append("<li class='active' onclick = 'chooseStep(this)' chooseNum = '"+num+"' shiftMahjongName = '"+shiftMahjongName+"'>"+
    					                           "<i class='iconfont icon-xuanzhong'></i>"+
    					                           "<i class='bz-number hide'>"+num+"</i>"+
								                   "<div class='paiwei fw-current'><span>"+shiftMahjongName+"</span></div>"+
								               "</li>");
    			chooseShiftMahjongName = shiftMahjongName;
    			modelChooseNum = chooseNum;
    		}
    		else {
    			jQuery(".Service-main").append("<li onclick = 'chooseStep(this)' chooseNum = '"+num+"' shiftMahjongName = '"+shiftMahjongName+"'>"+
    					                           "<i class='iconfont icon-xuanzhong hide'></i>"+
    					                           "<i class='bz-number'>"+num+"</i>"+
								                   "<div class='paiwei fw-current'><span>"+shiftMahjongName+"</span></div>"+
								               "</li>");
    		}
    		
    	}
    	$("#yg-buzhou-modal").removeClass("hide");
    	event.stopPropagation();
    }
    
    var chooseShiftMahjongName = "";
    var modelChooseNum = 0;
    
    function chooseStep (obj) {
    	//初始化选中状态
    	$(obj).siblings().removeClass("active");
    	$("#yg-buzhou-modal").find(".icon-xuanzhong").addClass("hide");
    	$("#yg-buzhou-modal").find(".bz-number").removeClass("hide");
    	
    	$(obj).addClass("active");
    	$(obj).find(".icon-xuanzhong").removeClass("hide");
    	$(obj).find(".bz-number").addClass("hide");
    	
    	chooseShiftMahjongName = $(obj).attr("shiftMahjongName");
    	modelChooseNum = $(obj).attr("chooseNum");
    }
    
    function confirmChoose() {
    	$(parentsObj).find("span[name='stepName']").attr("shiftStatus", modelChooseNum);
    	$(parentsObj).find("span[name='stepName']").text(chooseShiftMahjongName);
    	$(parentsObj).find("span[name='stepName']").parents().find(".p-num").text(modelChooseNum);
    	
    	$("#yg-buzhou-modal").addClass("hide");
    }
    
    $(".gouwuche").click(function(){
        if ($("#yg-gouwuche-modal").hasClass("hide")) {
            $("#yg-gouwuche-modal").removeClass("hide");
        }
        else {
            $("#yg-gouwuche-modal").addClass("hide");
        }
    });
    
    
    
    //点击轮牌名称更换轮牌信息
    function switchoverDIV(obj, proNum, num){
        $(obj).parents(".kind-project-ul").find(".item").removeClass("active");
        $(obj).addClass("active");
        $(obj).parents(".lunpai-content").find(".lunpai-people").addClass("hide");
        $(obj).parents(".lunpai-content").find(".lunpai-people[name='"+num+"']").removeClass("hide");
        
        var s = new Swiper(".lunpaiHead" + proNum + num, {
            slidesPerView: 'auto',
            paginationClickable: true,
            spaceBetween: 0,
            freeMode: true
        });
    }
    
    var chooseObj = null;
    
    //选中员工
    function chooseEmployee(obj) {
    	var shiftMahjongEmployeeId = $(obj).find(".lunpai-img").attr("name");
    	var objIsType = $(obj).find(".lunpai-img").attr("isType");
    	$("#rotatingStateUl").empty();

       	if (objIsType == 0) {
       		$("#rotatingStateUl").append("<li onclick='changeAssignState(1)'>"+
									    	    "<div class='state-item zhiding'>"+
									                "<div >指定</div>"+
									            "</div>"+
									     "</li>"+
									     "<li onclick='changeAssignState(2)'>"+
									    	    "<div class='state-item zhipai'>"+
									                "<div>指派</div>"+
									            "</div>"+
									     "</li>");
       	}
       	else {
       		$("#rotatingStateUl").append("<li onclick='changeAssignState(0)'>"+
									    	    "<div class='state-item likai'>"+
									                "<div >取消</div>"+
									            "</div>"+
									     "</li>");
       	}
       	
    	chooseObj = obj;
    }
    
    function changeAssignState(type) {
    	$(chooseObj).parents(".swiper-wrapper").find(".lunpai-img").removeClass("active");
    	$(chooseObj).parents(".swiper-wrapper").find(".lunpai-img").attr("isType", "");
    	$(chooseObj).parents(".swiper-wrapper").find(".lunpai-select").remove();
    	
    	if (type == 1 || type == 2) {
    		var state = $(chooseObj).find(".lunpai-state").attr("name");
        	if (state == 3 || state == 2) {
        		dialog("员工为离开状态，可能无法及时提供服务！");
        	}
    		//指定
        	if (type == 1) {
            	$(chooseObj).find(".lunpai-img").after("<div class='lunpai-select'>"+
        								                "<img src='"+baseUrl+"images/mobile/employee/lunpai/lunpai-select-zhiding.png' alt=''/>"+
        								         "</div>");
        	}
        	else if (type == 2) {
            	$(chooseObj).find(".lunpai-img").after("<div class='lunpai-select'>"+
        								                "<img src='"+baseUrl+"images/mobile/employee/lunpai/lunpai-select-zhipai.png' alt=''/>"+
        								         "</div>");
        	}
        	$(chooseObj).find(".lunpai-img").addClass("active");
        	$(chooseObj).find(".lunpai-img").attr("isType", type);
    	}
    }
    
    function submits(){
        var arrayObj = new Array();
        var nextArrayObj = new Array();
        
        var obj = $(".lunpai-img.active");
        for (var i =0; i < obj.length; i++) {
            var shiftMahjongEmployeeId = $(obj[i]).attr("name");
            var projectId = $(obj[i]).attr("projectId");
            var projectNum = $(obj[i]).attr("projectNum");
            var orderNum = $(obj[i]).attr("orderNum");
            var employeeId = $(obj[i]).attr("employeeId");
            var isType = $(obj[i]).attr("isType");
            var employeeStr = {"projectNum":projectNum, "orderNum":orderNum, "projectId":projectId, "shiftMahjongEmployeeId":shiftMahjongEmployeeId, "employeeId":employeeId, "isType" : isType}
            arrayObj.push(employeeStr);
        }
        var employeeObj = JSON.stringify(arrayObj);
        
        var nextObj = $(".lunpai-project");
        
        for (var i =0; i < nextObj.length; i++) {
        	var valueObj = $(nextObj[i]).find("span[name = 'stepName']");
        	var projectNum = $(valueObj).attr("projectNum");
        	var orderNum = $(valueObj).attr("shiftStatus");
        	orderNum = parseInt(orderNum);
        	var projectId = $(valueObj).attr("projectId");
        	var nextStr = {"projectNum" : projectNum, "orderNum" : orderNum, "projectId" : projectId};
        	nextArrayObj.push(nextStr);
        }
        
        var nextProjectObj = JSON.stringify(nextArrayObj);
        $.ajax({
        	url : baseUrl + "staff/action/addOrder",
        	type : "POST",
        	data : "sex=${memberBaseDto.sex}&objString=" + objString + "&employeeObj=" + employeeObj + "&nextProjectObj=" + nextProjectObj + "&memberId=${memberBaseDto.memberId}&orderId=" + orderId,
        	success : function(e){
        		if (e.code != 0) {
                    dialog(e.msg);
                    return;
                }
        		var datas = e.msg;
        		if (isEmpty(orderId)) {
        			dialog("开单成功, 1.5秒后跳转...");
        		}
        		else {
        			dialog("新增成功, 1.5秒后跳转...");
        		}
        		
        		if (datas.markTypeInteger == 1) {
    				setTimeout(function(){
    					window.location.href = baseUrl + "staff/view/employeeOrderView/${session_key_store_id}/2";
    				}, 1500);
    			}
    			else {
    				setTimeout(function(){
    					window.location.href = baseUrl + "staff/view/reception";
    				}, 1500);
    			}
        	}
        });
    }
</script>

<script language="JavaScript">
     javascript:window.history.forward(1);
</script>
</body>
</html>