<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/base.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>后台管理</title>
  </head>
  <body>

	<!--头部页面引用-->
	<%@include file="/WEB-INF/view/topheader.jsp"%>
	<!--左侧菜单页面引用-->
	<%@include file="/WEB-INF/view/sliderbar.jsp"%>
<div id="content">
  <div id="content-header">
    <div id="breadcrumb">
        <a href="<%=basePath %>homepage/view/query" class="tip-bottom" data-original-title="主页">
            <i class="icon-home"></i>首页看板
        </a>
       <!-- <a href="" class="tip-bottom" data-original-title=""></a>-->
        <a href="#" class="current">项目销售</a>
    </div>
</div>

    <div class="container-fluid">
         <div class="search-top">
            <div class="head-top">
            <div class="division4 mgl30" >
                    <div class="width8 c-header c-lblue">客户名称</div>
                    <input id="customerName1" type="text"  data-provide="typeahead" data-items="4" data-source="" class="width8 c-header" style="margin: 0 auto; height: 30px;width: 250px;"  >
              </div>
            </div>

            <div class="head-bottom">
            </div>
            <div class="sale-search" style="font-size: 25px;" onclick="query1()">
                    <a href="#"><i class="icon-search" style="margin-top: 20px;"></i></a>
            </div>
        </div>

        <!--<div class="head-bottom"></div>-->

        <hr>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                        <h5>用户列表</h5>

                    </div>
                    <div class="widget-content nopadding">
                        <div id="toolbar" class="btn-group">
                            <button type="button" class="btn btn-default" style="height: 30px; margin-bottom:6px; margin-left:6px;"onclick="add()">
                                <i class=" icon-plus">新增</i>
                            </button>
                        </div>
                        <!--bootstrap table-->
                        <!--data-url="http://mikepenz.com/jsfiddle/" data-show-refresh="true"-->
                        <table data-toggle="table"
                               data-method="post"
                               data-url="<%=basePath%>project/getCustSale"
                               data-pagination="true"
                               data-side-pagination="server"
                               data-query-params="queryParams1"
                               data-page-list="[5, 10, 20, 50, 100, 200]"
                               data-height="490" id="gettable"
                               data-toolbar="#toolbar">
                            <thead>
                                <tr>
                                    <th data-field="createTime" data-align="center">销售日期</th>
                                    <th data-field="goodsType"  data-align="center">系统版本</th>
                                    <th data-field="customerName"data-align="center">客户名称</th>
                                    <th data-field="goodsType" data-align="center">销售类别</th>
                                    <th data-field="goodsName" data-align="center" >项目名称</th>
                                    <th data-field="goodsQuantity" data-align="center" >数量</th>
                                    <th data-field="goodsAmount" data-align="center">销售价格</th>
                                    <th data-field="userName" data-align="center">销售人员</th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 新增线索Modal -->
<div id="add-project" class="modal hide" style="width: 1000px">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h5>增加项目    </h5>
    </div>
    <div class="modal-body nopadding">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box" style="margin: 0px;">
                    <div class="widget-content nopadding">
                        <table class="table table1" style="padding-top: 40px">
                            
                            <tr>
                                <td>
                                    <div class="add-project-plus" onclick="copyStye()">
                                        <i class="icon-plus" ></i>
                                    </div>

                            </tr>
                            <tr>
                                <td class="width1428">客户名称</td>
                                <td class="width1428" colspan="2">
	                                <input id="add_customerName" type="text"  data-provide="typeahead" data-items="4" data-source="" class="width8 c-header" style="margin: 0 auto; height: 30px;width: 250px;"  >
                                </td>
                                <td class="width1428"></td>
                                <td class="width1428"></td>
                                <td class="width1428"></td>
                                <td class="width1428"></td>

                            </tr>
                            <tr>
                                <td class="width1428"></td>
                                <td class="width1428">项目类别</td>
                                <td class="width1428">项目名称</td>
                                <td class="width1428">价格</td>
                                <td class="width1428">数量</td>
                                <td class="width1428">总金额</td>
                                <td class="width1428"><!-- 实收金额 --></td>

                            </tr>
                            <tr>
                                <td class="width1428"></td>
                                <td class="width1428">
                                    <select name="add_goodsType" id="add_goodsType" class="span11" onchange="selected(this)">
                                        <option value=""></option>
                                        <option value="1">系统类</option>
                                        <option value="2">短信套餐</option>
                                        <option value="3">外置设备</option>
                                    </select>
                                </td>
                                <td class="width1428"><select name="add_goodsName" id="add_goodsName" class="span11" onchange="selected1(this)"></select></td>
                                <td class="width1428"><input name="add_goodsPrice" id="add_goodsPrice" type="text" class="span11" disabled="disabled"/></td>
                                <td class="width1428"><input name="add_goodsQuantity"id="add_goodsQuantity" type="text" class="span11" onkeyup="changenum(this)" /></td>
                                <td class="width1428"><input name="add_goodsAmount" id="add_goodsAmount" type="text" class="span11" disabled="disabled"/></td>
                                <td class="width1428"><!-- <input name="add_realAmount" id="add_realAmount"type="text" class="span11"/> --></td>

                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="modal-footer"> <a class="btn btn-primary btn-success" href="#" onclick="addsave()">保存</a> <a data-dismiss="modal" class="btn" href="#">取消</a> </div>
</div>

<!---->
<%@include file="/WEB-INF/view/footer.jsp"%>

<%@include file="/WEB-INF/js.jsp"%>

<script type="text/javascript">



$('#gettable').bootstrapTable({
});

//打开新增窗口
function add(){
	 
	 $('#add-project').modal();
	 
	 $('#add_customerName').val("");
	 $('#add_goodsType').val("");
	 $('#add_goodsName').val("");
	 $('#add_goodsPrice').val("");
	 $('#add_goodsQuantity').val("");
	 $('#add_goodsAmount').val("");
	 $(".classtr").empty(); 
		

 }

$(document).ready(function(){
	
//-------------------------
  $.ajax({
     type: "POST",
     dataType: "json",
     url: '<%=basePath%>cust/getCustName',
     data:'',
     success: function (result) {
       //查询列表的客户查询条件
       var last=JSON.stringify(result.msg); 
       $('#customerName1').typeahead({
     	  source : result.msg,
    		  menu : "<ul class='typeahead dropdown-menu cust' style='z-index:99999;'></ul>"
    	   });
       //$("#customerName1").attr('data-source',last);
       //$("#add_customerName").attr('data-source',last);
       $('#add_customerName').typeahead({
    	  source : result.msg,
   		  menu : "<ul class='typeahead dropdown-menu cust' style='z-index:99999;'></ul>"
   	   });
     }
  });
});   
 //焦点触发事件   
$("#customerName1").blur(function(e){
    e = e ? e : window.e; 
	var obj = e.srcElement ? e.srcElement : e.target;
	 var value=$(obj).val();
});

 //去掉新增的一行
 function removeobj(obj){
	 //console.log($(obj));
	 $(obj).parent().parent().remove();
 }
 
 

 var searchData = {};

//扩展bootstrap table的请求参数
function queryParams1(params){
	
	for(var k in searchData){
		params[k] = searchData[k];
	}
	//console.log("queryParams..." + JSON.stringify(params));
	return params;
}

//调用bootstrap table的函数
function query1(){
	var customerName =$("#customerName1").val();
	if(customerName!=null){
		searchData["customerName"] = customerName;
	}
	$('#gettable').bootstrapTable('refresh', null);
}



function copyStye(){
	  var str = $("<tr class='classtr'>"+
			  "<td class='width1428'></td>"+
            "<td class='width1428'>"+
               "<select  name='add_goodsType' id='add_goodsType' class='span11' onchange='selected(this)'>"+
               "<option value=''></option>"+
                    "<option value='1'>系统类</option>"+
                    "<option value='2'>短信套餐</option>"+
                    "<option value='3'>外置设备</option>"+
                "</select>"+
            "</td>"+
            "<td class='width1428'><select name='add_goodsName' id='add_goodsName' class='span11' onchange='selected1(this)'></select></td>"+
            "<td class='width1428'><input name='add_goodsPrice' id='add_goodsPrice' type='text' class='span11' disabled='disabled'/></td>"+
            "<td class='width1428'><input name='add_goodsQuantity' id='add_goodsQuantity' type='text' class='span11' onkeyup='changenum(this)'/></td>"+
            "<td class='width1428'><input name='add_goodsAmount' id='add_goodsAmount' type='text' class='span11'  disabled='disabled'/></td>"+
          /*   "<td class='width1428'><input name='add_realAmount'  id='add_realAmount' type='text' class='span11'/></td>"+ */
            "<td><input type='button' onclick='removeobj(this)' value='去掉'></td>"+
        "</tr>");
	  $(".table1").children().append(str);
	}
function addsave(){
	var customerName=$('#add_customerName').val();
	if(customerName==""||customerName==null){
		alert("客户名称不能为空！");
		return;
	}
	var add_goodsType = $("select[name='add_goodsType']");
	var add_goodsName = $("select[name='add_goodsName']");
	var add_goodsPrice = $("input[name='add_goodsPrice']");
	var add_goodsQuantity = $("input[name='add_goodsQuantity']");
	var add_goodsAmount = $("input[name='add_goodsAmount']");
	//var add_realAmount = $("input[name='add_realAmount']");
	
	
	 var goodsTypeList = new Array();
	 var goodsNameList = new Array();
	 var goodsPriceList = new Array();
	 var goodsQuantityList = new Array();
	 var goodsAmountList = new Array();
	// var realAmountList = new Array();
	 
	 var object = new Object();

	for(var i = 0; i < add_goodsType.length; i++){
		//console.log($(goodsTypeList[i]).val());
		var goodsType=$(add_goodsType[i]).val();
		if(goodsType==""||goodsType==null){
			alert("类型不能有为空！");
			return;
		}
		var goodsName=$(add_goodsName[i]).val();
		if(goodsName==""||goodsName==null){
			alert("商品名称不能有为空！");
			return;
		}

		var goodsPrice=$(add_goodsPrice[i]).val();
		
		var goodsQuantity=$(add_goodsQuantity[i]).val();
		
		if(goodsQuantity==""||goodsQuantity==null){
			alert("数量不能有为空！");
			return;
		}
		var isNum = /^[0-9]*$/;
		 if (!isNum.test(goodsQuantity))
	    {
	        alert("数量只能输入数字！");
	        return;
	     }
		var goodsAmount=$(add_goodsAmount[i]).val();
		//var realAmount=$(add_realAmount[i]).val();
		//if(goodsName==""||goodsName==null){
		//	alert("实收金额不能有为空！");
		//	return;
		//}

		goodsTypeList.push(goodsType);
		goodsNameList.push(goodsName);
		goodsPriceList.push(goodsPrice);
		goodsQuantityList.push(goodsQuantity);
		goodsAmountList.push(goodsAmount);
		//realAmountList.push(realAmount);
		
	}
	//console.log(goodsTypeList);
	 $.ajax({
         type: "POST",
         dataType: "json",
         url: '<%=basePath%>project/addCustSale',
         data:'customerName='+customerName+'&goodsTypeList='+goodsTypeList+'&goodsNameList='+goodsNameList+'&goodsPriceList='+goodsPriceList
         +'&goodsQuantityList='+goodsQuantityList+'&goodsAmountList='+goodsAmountList,
         success: function (result) {
           if(result.code==0){
        	   alert("新增成功！");
        	   $('#add-project').modal('hide');
        	   $('#gettable').bootstrapTable('refresh', null);
           }else{
        	   alert("新增失败！"+result.msg);
           }
         }
     });
	

}

function selected(obj){
	var goodsType=$(obj).val();
	
	 $.ajax({
         type: "POST",
         dataType: "json",
         url: '<%=basePath%>project/getGoodsName',
         data:'goodsType='+goodsType,
         success: function (result) {
        	/*   var last=JSON.stringify(result.msg); 
             alert(last); */ 
             var add_goodsNameSel = $(obj).parent().parent().find("select[name='add_goodsName']");
            // console.log($(add_goodsNameSel).html());
             $(add_goodsNameSel).children().remove();
             /* add_goodsNameSel.length = 0; */
         if(result.msg.length>0){
        	 for (var i = 0; i < result.msg.length; i++) {
        		 var lal = $("<option value='"+result.msg[i].goodsId+"'>"+result.msg[i].goodsName+"</option>");
        		 //console.log($(lal).html());
        		 $(add_goodsNameSel).append(lal);
        	 }
         }else{
        	 add_goodsNameSel.add(new Option("--无--",""));
         }
         selected1(obj); 
         }
     });
}

function selected1(obj){
	var goodsType=$(obj).parent().parent().find("select[name='add_goodsType']").val();
	var goodsName=$(obj).parent().parent().find("select[name='add_goodsName']").val();
	$(obj).parent().parent().find("input[name='add_goodsQuantity']").val("");
	$(obj).parent().parent().find("input[name='add_goodsAmount']").val("");

	 $.ajax({
         type: "POST",
         dataType: "json",
         url: '<%=basePath%>project/getGoodsPrice',
         data:'goodsType='+goodsType+'&goodsName='+goodsName,
         success: function (result) {
        	 
        	  var last=JSON.stringify(result.msg); 
             $(obj).parent().parent().find("input[name='add_goodsPrice']").val(last);
         }
     });
}

/* //焦点触发事件   
$("#add_goodsQuantity").blur(function(e){
    e = e ? e : window.e; 
	var obj = e.srcElement ? e.srcElement : e.target;
	 var value=$(obj).val();
	 alert(value);
}); */

function changenum(obj){
	var goodsQuantity=$(obj).parent().parent().find("input[name='add_goodsQuantity']").val();
	var goodsPrice=$(obj).parent().parent().find("input[name='add_goodsPrice']").val();
	if(goodsQuantity==""||goodsPrice==""||goodsQuantity==null||goodsPrice==null){
		return;
	}
	var aa=(goodsQuantity*100*goodsPrice*100)/10000;
	$(obj).parent().parent().find("input[name='add_goodsAmount']").val(aa);
	
}
</script>

  </body>
</html>