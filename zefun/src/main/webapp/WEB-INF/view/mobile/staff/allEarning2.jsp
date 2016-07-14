<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/base.jsp" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>排行</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=employeeCssPath%>"/>
</head>
<body>
<div class="wrap">
<div class="content mb-footer">
    <div class="yg-performance-order">
        <div class="tab t0">
            <ul >
                <li class="score-shop-li  active" onclick="changeShow(this, 1)">
                    <a href="javascript:void(0);">
                        <img src="<%=basePath%>images/mobile/employee/active-new.png" alt=""/>
                        <div class="tab-word">
                            <span>日排行</span>
                        </div>
                    </a>
                </li>
                <li class="score-shop-li" onclick="changeShow(this, 2)">
                    <a href="javascript:void(0);">
                        <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
                        <div class="tab-word">
                            <span>周排行</span>
                        </div>
                    </a>
                </li>
                <li class="score-shop-li" onclick="changeShow(this, 3)">
                    <a href="javascript:void(0);">
                        <img src="<%=basePath%>images/mobile/employee/active-new.png" alt="" class="hide"/>
                        <div class="tab-word">
                            <span>月排行</span>
                        </div>
                    </a>
                </li>
            </ul>
        </div>
        <div class="clearfix"></div>
        
        <div class="part mt7">
	        <div class=" toggle-ctl  u-title">
	            <div class="fl">
	                                          排行
	            </div>
	            <div class="desc fl">
	                                          名称
	            </div>
	            <span class="fr">劳动业绩</span>
	            <div class="clearfix"></div>
	        </div>
	    </div>
	    
        <div id = "dateDIV">
            <c:forEach items="${hashMap.dataList}" var="toDay" varStatus="toDayStatus">
		      <div class="part">
                    <div class=" toggle-ctl  ul-title">
                        <div class="fl">
                            <span class="font-333">${toDayStatus.index + 1}</span>
                        </div>
                        <div class="desc fl">
                             <img src="<%=picPath%>${toDay.headImage}" alt="" class="person-img"/>
                            <div><span>${toDay.employeeCode}</span> <span> ${toDay.name}</span></div>
                        </div>
                        <span class="fr"><span class="blue-word">${toDay.projectValue}元 </span><i class="iconfont icon-zhankai" style="padding-left: 15px;"></i></span>
                        <div class="clearfix"></div>
                    </div>
                    <div class="table-wrap">
                        <table class="table" style="display: none;">
                        <tbody>
                        <tr class="h60 line-height60 font-gray font-999">
                            <td>均值</td>
                            <td class="text-center">关键指标</td>
                            <td class="text-right">我的表现</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${hashMap.totalProjectScale}元</td>
                            <td class="font-999 text-center">劳动业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.projectValue < hashMap.totalProjectScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="text-right ${underClass }">${toDay.projectValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${hashMap.totalAssignProjectScale}元</td>
                            <td class="font-999 text-center">指定业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.assignProjectValue < hashMap.totalAssignProjectScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.assignProjectValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${hashMap.totalScale}%</td>
                            <td class="font-999 text-center">指定比例</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.scale < hashMap.totalScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.scale}%</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${hashMap.totalComboScale}元</td>
                            <td class="font-999 text-center">套餐业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.comboValue < hashMap.totalComboScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.comboValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${hashMap.totalGoodsScale}元</td>
                            <td class="font-999 text-center">商品业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.goodsValue < hashMap.totalGoodsScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.goodsValue}元</td>
                        </tr>
                        <tr class="h90 line-height90">
                            <td class="font-gray">${hashMap.totalChargeScale}元</td>
                            <td class="font-999 text-center">卡项业绩</td>
                            <c:set var="underClass" value="" />
                            <c:if test="${toDay.chargeValue < hashMap.totalChargeScale}"><c:set var="underClass" value="font-red" /></c:if>
                            <td class="${underClass } text-right">${toDay.chargeValue}元</td>
                        </tr>
                        </tbody>
                    </table>
                    </div>
                </div>
            </c:forEach>
            
        </div>
        
    </div>
    
    <div class="footer">
        <ul>
            <li >
                <a href="<%=basePath%>staff/view/reception">
                    <span class="iconfont icon-jiedai"></span>
                    <span>接待</span>
                </a>
            </li>
            <li>
                <a href="<%=basePath%>staff/view/employeeOrderView/${session_key_store_account}/2">
                    <span class="iconfont icon-dingdan"></span>
                    <span>服务</span>
                </a>
            </li>
            <li class="active" >
                <a href="<%=basePath%>staff/view/allEarning">
                    <span class="iconfont icon-yejipaihang02"></span>
                    <span>排行</span>
                </a>
            </li>
            <li>
                <a href="<%=basePath%>staff/view/staffCenter/${session_key_store_account}/2">
                    <span class="iconfont icon-wode"></span>
                    <span>我的</span>
                </a>
            </li>
        </ul>
    </div>
</div>    
</div>
<script type="text/javascript" src="<%=jqueryJsPath%>"></script>
<script type="text/javascript" src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript" src="<%=basePath%>js/mobile/employee.js"></script>
<script>
function changeShow(obj, type) {

    $(".score-shop-li").removeClass("active");
    $(".score-shop-li").find("img").addClass("hide");
    $(".border-group").removeClass("hide");
    $(obj).find("img").removeClass("hide");
    $(obj).find(".border-group").addClass("hide");
    $(obj).addClass("active");
    
    $("#dateDIV").empty();
    
    chooseDateType(type);
}

function chooseDateType(type) {
	$.ajax({
        type : "post",  
        url : baseUrl + "staff/action/selectEmployeeData",
        data : "chooseType="+type,
        dataType : "json",
        success : function(e){
            if(e.code != 0){
                dialog(e.msg);
                return;
            }
            var data = e.msg;
            var dataList = data.dataList;
            for (var i = 0; i < dataList.length; i++) {
            	var dataObj = dataList[i];
            	var partDIV = "<div class='part'>";
            	var a = i + 1;
            	partDIV = partDIV + "<div class='toggle-ctl  ul-title'>"+
						                "<div class='fl'>"+
							                "<span class='font-333'>"+a+"</span>"+
							            "</div>"+
							            "<div class='desc fl'>"+
							                "<img src='"+picUrl + dataObj.headImage+"' alt='' class='person-img'/>"+
							                "<div><span>"+dataObj.employeeCode+"</span> <span> "+dataObj.name+"</span></div>"+
							            "</div>"+
							            "<span class='fr'><span class='blue-word'>"+dataObj.projectValue+"元 </span><i class='iconfont icon-zhankai' style='padding-left: 15px;'></i></span>"+
							            "<div class='clearfix'></div>"+
							        "</div>"+
							        "<div class='table-wrap'>"+
							        "<table class='table' style='display: none;'>"+
			                        "<tbody>"+
			                        "<tr class='h60 line-height60 font-gray font-999'>"+
			                            "<td>均值</td>"+
			                            "<td class='text-center'>关键指标</td>"+
			                            "<td class='text-right'>我的表现</td>"+
			                        "</tr>"+
			                        "<tr class='h90 line-height90'>"+
			                            "<td class='font-gray'>"+data.totalProjectScale+"元</td>"+
			                            "<td class='font-999 text-center'>劳动业绩</td>";
			    if (dataObj.projectValue < data.totalProjectScale) {
			    	partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.projectValue+"元</td>";
			    }
			    else {
			    	partDIV = partDIV + "<td class='text-right'>"+dataObj.projectValue+"元</td>";
			    }  
			    
			    partDIV = partDIV + "</tr>"+
			                        "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalAssignProjectScale+"元</td>"+
					                    "<td class='font-999 text-center'>指定业绩</td>";
	            
                if (dataObj.assignProjectValue < data.totalAssignProjectScale) {
                	partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.assignProjectValue+"元</td>";
                }
                else {
                	partDIV = partDIV + "<td class='text-right'>"+dataObj.assignProjectValue+"元</td>";
                }
                
                partDIV = partDIV + "</tr>"+
					                "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalScale+"%</td>"+
					                    "<td class='font-999 text-center'>指定比例</td>";
					                    
                if (dataObj.scale < data.totalScale) {
                	partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.scale+"%</td>";
                }
                else {
                	partDIV = partDIV + "<td class='text-right'>"+dataObj.scale+"%</td>";
                }
                
                partDIV = partDIV + "</tr>"+
					                "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalComboScale+"元</td>"+
					                    "<td class='font-999 text-center'>套餐业绩</td>";
                    
				if (dataObj.comboValue < data.totalComboScale) {
				    partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.comboValue+"元</td>";
				}
				else {
				    partDIV = partDIV + "<td class='text-right'>"+dataObj.comboValue+"元</td>";
				}
				
				partDIV = partDIV + "</tr>"+
					                "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalGoodsScale+"元</td>"+
					                    "<td class='font-999 text-center'>商品销售</td>";

				if (dataObj.goodsValue < data.totalGoodsScale) {
					partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.goodsValue+"元</td>";
				}
				else {
					partDIV = partDIV + "<td class='text-right'>"+dataObj.goodsValue+"元</td>";
				}
				
				partDIV = partDIV + "</tr>"+
					                "<tr class='h90 line-height90'>"+
					                    "<td class='font-gray'>"+data.totalChargeScale+"元</td>"+
					                    "<td class='font-999 text-center'>卡项销售</td>";

				if (dataObj.chargeValue < data.totalChargeScale) {
					partDIV = partDIV + "<td class='text-right font-red'>"+dataObj.chargeValue+"元</td>";
				}
				else {
					partDIV = partDIV + "<td class='text-right'>"+dataObj.chargeValue+"元</td>";
				}
				
				partDIV = partDIV + "</tr>"+
				                "</tbody>"+
				                "</table>"+
				                "</div>"+
				            "</div>";
				            
				$("#dateDIV").append(partDIV);
            }
        }
    });
}
</script>
</body>
</html>
