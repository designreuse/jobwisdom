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
    </style>
</head>
<body>
<div class="wrap">
<div class="content">
    <div class="bd-gray">
        <div class="lunpai-project">
	            <div class="lunpai-project-part">
	                <div class="lunpai-project-title">
	                    <img src="<%=picPath%>${project.projectImage}?imageView2/1/w/136/h/136" alt=""/>
	                    <span class="name">${project.projectName}</span>
	                    <div class="waiting-num" onclick="setServeStep(this)">
	                        <div class="p-name">
		                        <span name = "stepName" projectId = "${project.projectId}" shiftStatus = "${shiftMahjongDtoList[0].stepNum}">${shiftMahjongDtoList[0].shiftMahjongName}</span>
		                        <div class="p-num">${shiftMahjongDtoList[0].stepNum}</div>
		                    </div>
		                </div>
	                    <div class="clearfix"></div>
	                </div>
	            </div>
	            <div class="lunpai-content">
                    <div class="lunpai-kind-project">
                        <ul class="kind-project-ul">
                            <c:forEach items="${shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="shiftStatus">
                                <c:choose>
                                    <c:when test="${shiftMahjongDto.stepNum == 1}">
                                        <li class="item active" shiftStatus = '${shiftMahjongDto.stepNum}' onclick="switchoverDIV(this, ${shiftMahjongDto.stepNum})">${shiftMahjongDto.shiftMahjongName}</li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="item" shiftStatus = '${shiftMahjongDto.stepNum}' onclick="switchoverDIV(this, ${shiftMahjongDto.stepNum})">${shiftMahjongDto.shiftMahjongName}</li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                    <hr>
                    <c:forEach items="${shiftMahjongDtoList}" var="shiftMahjongDto" varStatus="shiftStatus">
	                   <div <c:if test="${shiftStatus.index == 0}">class="lunpai-people"</c:if> <c:if test="${shiftStatus.index != 0}">class="lunpai-people hide"</c:if> name= "${shiftMahjongDto.stepNum}">
	                        <div class="swiper-container-daohang lunpai lunpaiHead${shiftStatus.index}" size="${fn:length(shiftMahjongDto.shiftMahjongEmployeeList)}">
	                            <div class="swiper-wrapper lunpaiBody${shiftStatus.index }">
	                               <c:forEach items="${shiftMahjongDto.shiftMahjongEmployeeList}" var="shiftMahjongEmployee" varStatus="status">
	                                  <div class="swiper-slide score-shop-li line-height-center" onclick="chooseEmployee(this)">
	                                        <div class="lunpai-img" name= "${shiftMahjongEmployee.shiftMahjongEmployeeId}" projectNum = "${shiftStatus.index}" orderNum = "${shiftMahjongDto.stepNum}" projectId = "${project.projectId}" employeeId = "${shiftMahjongEmployee.employeesId}" isType = "0">
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
        </div>
        
        <div class="footer-queren">
            <div class="foot-btn fr mt2 mr2" onclick="submits()">确定</div>
        </div>
    </div>
    
    <!--确认兑换-->
    <div class="s-modal hide s-modal-miss" id="confirmWindow">
        <div class="s-modal-wrap">
            <div class="d-member-info">
                <div class="n-modal-title text-center">
                    <span>该明细修改项目后，无法再次修改项目，是否继续修改？</span>
                    <div class="clearfix"></div>
                </div>
                <div class="s-modal-foot">
                    <div class="modal-cancel" onclick="recover()">取消</div>
                    <div class="modal-confirm" onclick="trueSubmits();">确认</div>
                </div>
            </div>
        </div>
    </div>
    
    <div class="hide s-modal-miss no-padding s-gouwuche-modal" id="yg-rotating">
        <div class="yg-state yg-rotating">
            <ul id = "rotatingStateUl">

            </ul>
        </div>
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
</div>
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"> </script>
<script type="text/javascript" src="<%=swiperJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"> </script>
<script type="text/javascript">

var clientHeight = $(window).height();
var state=$(".state-item").height();
var mH = clientHeight/2 -state/2 - 70;
$(".yg-state").css("margin-top",mH);

var widowHeight = $(window).height();
var topHeight=$(".dengdai-ul").height();
var SelectHeight=widowHeight-topHeight - 200;
$(".Service").css({"min-height":SelectHeight});

var projectInfo = '${projectInfo}';
var detailId = '${detailId}';

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

var sArr = new Array();
$(function () {
    var swiperList = jQuery(".swiper-container-daohang");
/*  var clientWidth = $(window).width() - 16;
    var number;
    if($.browser.versions.android){
          number = clientWidth / 128;
    }else if($.browser.versions.ios){
        number = clientWidth / 144;
    } */
    for (var i = 0; i < swiperList.length; i++) {
        var s = new Swiper(".lunpaiHead" + i, {
            slidesPerView: 'auto',
            paginationClickable: true,
            spaceBetween: 0,
            freeMode: true
        });
        sArr[i] = s;
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

$("#gouwucheInfo").click(function(){
    if ($("#yg-gouwuche-modal").hasClass("hide")) {
        $("#yg-gouwuche-modal").removeClass("hide");
    }
    else {
        $("#yg-gouwuche-modal").addClass("hide");
    }
});

$("html").delegate("body:not('div[name='modalDIV']')", "click", function(){
    $("#yg-buzhou-modal").addClass("hide");
});

function cancelModal() {
    $("#yg-buzhou-modal").addClass("hide");
}

$("div[name='modalDIV']").click(function () {
    event.stopPropagation();
})

function setServeStep (obj) {
    
    var itemObj = jQuery(".kind-project-ul").find(".item");
    
    var chooseNum = jQuery(obj).find("span[name='stepName']").attr("shiftStatus"); 
    
    jQuery(".Service-main").empty();
    
    for (var i = 0; i < itemObj.length; i++) {
        var shiftMahjongName = jQuery(itemObj[i]).text();
        var num = jQuery(itemObj[i]).attr("shiftStatus");

        if (parseInt(chooseNum) == parseInt(num)) {
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
    $("span[name='stepName']").attr("shiftStatus", modelChooseNum);
    $("span[name='stepName']").text(chooseShiftMahjongName);
    $("span[name='stepName']").parents().find(".p-num").text(modelChooseNum);
    
    $("#yg-buzhou-modal").addClass("hide");
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
    
    $("#yg-rotating").removeClass("hide");
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


//点击轮牌名称更换轮牌信息
function switchoverDIV(obj, num){
    $(obj).parents(".kind-project-ul").find(".item").removeClass("active");
    $(obj).addClass("active");
    $(obj).parents(".lunpai-content").find(".lunpai-people").addClass("hide");
    $(obj).parents(".lunpai-content").find(".lunpai-people[name='"+num+"']").removeClass("hide");
    var a = num - 1;
    sArr[a].onResize();
}

function submits(){
    $("#confirmWindow").removeClass("hide");
}

function trueSubmits() {
    $("#confirmWindow").addClass("hide");
    var arrayObj = new Array();
    var nextArrayObj = new Array();
    var obj = $(".lunpai-img.active");
    for (var i =0; i < obj.length; i++) {
        var projectId = $(obj[i]).attr("projectId");
        var orderNum = $(obj[i]).attr("orderNum");
        var employeeId = $(obj[i]).attr("employeeId");
        var shiftMahjongEmployeeId = $(obj[i]).attr("name");
        var isType = $(obj[i]).attr("isType");
        var employeeStr = {"projectNum":0, "shiftMahjongEmployeeId":shiftMahjongEmployeeId, "orderNum":orderNum, "projectId":projectId, "employeeId":employeeId, "isType" : isType}
        arrayObj.push(employeeStr);
    }
    var employeeObj = JSON.stringify(arrayObj);
    
    var nextObj = $(".lunpai-project-part");
    
    var nextStr = {};
    var valueObj = $(nextObj).find("span[name = 'stepName']");
    var orderNum = $(valueObj).attr("shiftStatus");
    if (!isEmpty(orderNum)) {
        var projectId = $(valueObj).attr("projectId");
        nextStr = {"projectNum" : 0, "orderNum" : orderNum, "projectId" : projectId};
        nextArrayObj.push(nextStr);
    }
    
    var nextProjectObj = JSON.stringify(nextArrayObj);
    var data = "detailId="+detailId+"&projectInfo="+projectInfo+"&employeeObj="+employeeObj+"&nextProjectObj="+nextProjectObj;
    $.ajax({
        type : "post",
        url : baseUrl + "staff/action/updateSaveDetail",
        data : data,
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            dialog("修改项目成功, 1.5秒后跳转至接待中心...");
            setTimeout(function(){
                window.location.href = baseUrl + "staff/view/reception";
            }, 1500);
        }
    });
}
</script>

<script language="JavaScript">
     javascript:window.history.forward(1);
</script>
</body>
</html>
