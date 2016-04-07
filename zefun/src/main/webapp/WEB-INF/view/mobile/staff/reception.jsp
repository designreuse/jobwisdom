<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>接待大厅</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
</head>
<body>
<div class="wrap">
<div class="content">
    <div class="function-list-wrap">
        <ul class="function-list">
            <li class="function-item normal-li">
                    <span>
                        <i class="iconfont icon-shouji3" style="margin-left:-0.0625rem;"></i>
                        <input type="text" class="w60p" name="mobileNum" style="margin-left:1rem;text-align: left;" placeholder="会员手机号/姓名"/>
                        <ul class="fuzzysearch" style="display: none">
                            
                        </ul>
                    </span>
                    <a href="javascript:void(0);" style="display: inline">
                        <div id="mobileConfirm" class="btn fr confirm-reception" style="margin-top:.5rem;">确认</div>
                    </a>
            </li>
            <li class="function-item normal-li">
                <a href="javascript:void(0);" onclick="disperseClient(1)">
                    <span><i class="iconfont icon-manuser"></i> <span class="ml">男散客</span></span>
                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
                </a>
            </li>
            <li class="function-item normal-li">
                <a href="javascript:void(0);" onclick="disperseClient(2)">
                    <span><i class="iconfont icon-womanuser"></i> <span class="ml">女散客</span></span>
                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
                </a>
            </li>
        </ul>
    </div>

    <c:if test="${session_key_role_id != 4 }">
	    <div class="function-list-wrap">
	        <ul class="function-list">
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/waitingCentre">
	                    <span>
	                        <i class="iconfont icon-denghou"></i>
	                        <span class="ml">等候大厅</span>
	                        <div class="have-message"></div>
	                    </span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/order/all">
	                    <span>
	                        <i class="iconfont icon-yiwwancheng"></i>
	                        <span class="ml">全部订单</span>
	                        <div class="have-message"></div>
	                    </span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	            <li class="function-item normal-li">
	                <a href="<%=basePath%>staff/view/selectAllShiftMahjong">
	                    <span>
	                        <i class="iconfont icon-quanbulunpai"></i>
	                        <span class="ml">全部轮牌</span>
	                    </span>
	                    <span class="fr normoal-word"> <i class="iconfont icon-right"></i></span>
	                </a>
	            </li>
	        </ul>
	    </div>
    </c:if>
	
	<div class="footer">
        <ul>
            <li class="active" >
                <a href="<%=basePath%>staff/view/reception">
                    <span class="iconfont icon-jiedai"></span>
                    <span>接待</span>
                </a>
            </li>
            <li>
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
<script type="text/javascript">

var memberList = new Array();

$(function () {
	
	$.ajax({
        type : "post",
        url : baseUrl + "staff/action/selectMemberList",
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            memberList = e.msg;
        }
    });
});

function dimSelectMember (obj, str) {
	var a = 1;
	var parentsObj = jQuery(obj).parent();
	parentsObj.find(".fuzzysearch").empty();
	for (var i = 0; i < inputMemberList.length; i++) {
		var mMap = inputMemberList[i];
		var name = mMap.name.toString();
		var phone = mMap.phone.toString();
		if (name.indexOf(str.toString()) != -1){
			parentsObj.find(".fuzzysearch").append("<li class='text-center' onclick = choosePhone(this,'"+phone+"')><sapn><span class='mr10'>"+name+":</span>"+phone+"</sapn></li>");
			a++;
			if (a == 6) {
				break;
			}
			continue;
		}
		if (phone.indexOf(str) != -1) {
			parentsObj.find(".fuzzysearch").append("<li class='text-center' onclick = choosePhone(this,'"+phone+"')><sapn><span class='mr10'>"+name+":</span>"+phone+"</sapn></li>");
			a++;
			if (a == 6) {
				break;
			}
			continue;
		}
		inputMemberList.splice(i,1);//从下标为i的元素开始，连续删除1个元素
        i--;//因为删除下标为i的元素后，该位置又被新的元素所占据，所以要重新检测该位置
	}
	parentsObj.find(".fuzzysearch").append("<em class='t-border'></em>"+
                                           "<span class='t-content'></span>")
}

function choosePhone (obj, phone) {
	jQuery(obj).parents(".function-item").find("input[name='mobileNum']").val(phone);
}

jQuery("body").delegate("input[name='mobileNum']","blur", function(event){
	setTimeout(function () {
		jQuery(".fuzzysearch").css("display", "none");
	},500);
});

jQuery("body").delegate("input[name='mobileNum']","focus", function(event){
	
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	
	if (isEmpty(jQuery(obj).val())) {
		jQuery(obj).parent().find(".fuzzysearch").empty();
		inputMemberList = JSON.parse(JSON.stringify(memberList));
		inputPhoneNum = 0;
		jQuery(obj).val("");
		return;
	}

	dimSelectMember(obj, jQuery(obj).val());
	
	jQuery(obj).parent().find(".fuzzysearch").css("display", "block");
});

//当前
var inputPhoneNum = 0;
var inputMemberList = "";

jQuery(document).keyup(function(event){ 
	event = event ? event : window.event; 
	var obj = event.srcElement ? event.srcElement : event.target;
	if (jQuery(obj).attr("name") == "mobileNum") {
		
		if (isEmpty(jQuery(obj).val())) {
			jQuery(obj).parent().find(".fuzzysearch").empty();
			inputMemberList = JSON.parse(JSON.stringify(memberList));
			return;
		}
		
		jQuery(obj).parent().find(".fuzzysearch").css("display", "block");
		
		if (inputPhoneNum < jQuery(obj).val().length) {
			dimSelectMember(obj, jQuery(obj).val());
		}
		else {
			inputMemberList = JSON.parse(JSON.stringify(memberList));
			dimSelectMember(obj, jQuery(obj).val());
		}
		inputPhoneNum = jQuery(obj).val().length;
	}
}); 

$("#mobileConfirm").click(function(){
	if ($("input[name='mobileNum']").val() == "") {
		dialog("手机号码不能为空");
		return;
	} 
	$.ajax({
        type : "post",
        url : baseUrl + "staff/action/selectBaseInfo",
        data : "phone="+$("input[name='mobileNum']").val(),
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            submits(e.msg, 3);
        }
    });
});

function disperseClient(type) {
	submits(null, type);
}

function submits (memberId, type) {
	var temp = document.createElement("form");
    temp.action = baseUrl + "staff/view/selectCategory";
    temp.method = "post";
    temp.style.display = "none";
    var opt = document.createElement("textarea");
    opt.name = "memberId";
    opt.value = memberId;
    temp.appendChild(opt);
    var opt1 = document.createElement("textarea");
    opt1.name = "type";
    opt1.value = type;
    temp.appendChild(opt1);
    document.body.appendChild(temp);
    temp.submit();
}
</script>
</body>
</html>