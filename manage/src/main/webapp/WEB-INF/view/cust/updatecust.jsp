<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ include file="/WEB-INF/base.jsp"%>  

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="author" content="lives"/>
      <meta name="description" content="admin"/>
      <meta name="keywords" content="admin lives 店务管理 美容美发"/>

      <title>店务系统后台管理</title>

   
  </head>
  <body>

	<!--头部页面引用-->
	<%@include file="/WEB-INF/view/topheader.jsp"%>
	<!--左侧菜单页面引用-->
	<%@include file="/WEB-INF/view/sliderbar.jsp"%>    

      

<div id="content">
    <div id="content-header">
        <div id="breadcrumb"> <a href="<%=basePath %>homepage/view/query" title="Go to Home" class="tip-bottom">
        <i class="icon-home"></i> 主页看板</a> <a href="#" class="current">修改客户</a> </div>
        <!--<h1>修改客户</h1>-->
    </div>
    <div class="container-fluid">
        <hr>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5>修改客户</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <div  class="form-horizontal">
                                  <input type="hidden" class="span12" id="customerId"/>
                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">客户属性: </label>
                                    <div class="controls">
                                        <select name="" class="span12 client-attribute" id="clientAttr" >
                                            <option value="1" selected>单店</option>
                                            <option value="2">连锁店</option>
                                            <option value="3">分店</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">选择版本: </label>
                                    <div class="controls">
                                        <select name="" id="sysName" class="span12" onchange="changemoney()" disabled="disabled">
                                        <c:forEach items="${listmap}" var="listmap">
											<option value="${listmap.goodsId}">${listmap.goodsName} </option> 
										</c:forEach>
                                            <!-- <option value="1">美发普及版</option>
                                            <option value="2">美发专业版</option>
                                            <option value="3">美容专业版</option>
                                            <option value="4">美甲专业版</option>
                                            <option value="5">美容美发综合版</option> -->
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group company-name" style="display:none">
                                <div class="span8">
                                    <label for="normal" class="control-label">公司名称: </label>
                                    <div class="controls">
                                        <input type="text" class="span12 company-input" autocomplete="off" id="companyName" data-provide="typeahead" data-source=""/>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">品牌名称: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" id="brandName"/>
                                    </div>
                                </div>
                            </div>

                            <div class="control-group customerName-name">
                                <div class="span8">
                                    <label for="normal" class="control-label">店铺名称: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" id="customerName"/>
                                    </div>
                                </div>
                            </div>


                            <div class="control-group">
                                <div class="span8">
                                <label for="normal" class="control-label">选择区域:</label>
                                <div class="controls">
                                    <div class="controls-x1">
                                        <select class="span4" id="province" name="province3">
                                        </select>
                                    </div>
                                    <div class="controls-x3">
                                        <select class="span4" id="city" name="city3">
                                         
                                        </select>
                                    </div>
                                    <div class="controls-x3">
                                        <select class="span4" id="area" name="area3">
                                       
                                        </select>
                                    </div>
                                </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span8">
                                <label for="normal" class="control-label">地址: </label>
                                <div class="controls">
                                    <input type="text" class="span12" id="addr"/>
                                </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="span8">
                                <label for="normal" class="control-label">身份证号码: </label>
                                <div class="controls">
                                    <input type="text" class="span12" id="customerCode"/>
                                </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">负责人姓名</label>
                                    <div class="controls">
                                        <div class="controls-x1">
                                            <input type="text" class="span4" placeholder="负责人姓名" id="principalName"/>
                                        </div>
                                        <div class="controls-x3">
                                            <select class="span4" id="principalDuty" >
                                                <option value="">职位选择</option>
                                                <option value="1">老板</option>
                                                <option value="2">股东</option>
                                                <option value="3">高管</option>
                                                <option value="4">店长</option>
                                            </select>
                                        </div>
                                        <div class="controls-x3">
                                            <input type="text" class="span4" placeholder="手机号" id="phone" />
                                        </div>
                                    </div>
                                </div>
                            </div>


                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">主账号: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" placeholder="" id="userName"/>
                                    </div>
                                </div>
                            </div>
<!--                             <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">账号密码: </label>
                                    <div class="controls">
                                        <input type="text" class="span12" placeholder="88888888"  />
                                    </div>
                                </div>
                            </div>
 -->
                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label">账号期限:</label>
                                    <div class="controls">
                                <!--         <div class="controls-x1">
                                            <select class="span4" id="expireTime" onchange="changemoney()">
                                                <option value="">账号期限</option>
                                                <option value="1">30天</option>
                                                <option value="2">1年</option>
                                                <option value="3">2年</option>
                                                <option value="4">100年</option>
                                            </select>
                                        </div> -->
                                        <!-- <div class="span4">
                                            <input type="text" class="span4" id="money" placeholder="金额" disabled="disabled"/>
                                        </div> -->
                                    </div>
                                </div>
                            </div>

                            <div class="control-group">
                                <button class="btn btn-success client-add-save"  style="margin-right: 25%" onclick="updatecust()"><a style="text-decoration:none;color: #fff;">保存</a></button>
                            <button class="btn btn-success client-add-save" onclick="goback()"><a href="#"  style="text-decoration:none;color: #fff;">返回查询页面</a></button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



<!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
   <script type="text/javascript">
var baseUrl = "<%=basePath %>";
new PCAS("province3","city3","area3");
function goback(){
	location.href=encodeURI(baseUrl +"cust/view/query"); 
}
$(document).ready(function(){
	setInfo();
	changemoney();
	//-------------------------
	  $.ajax({
	     type: "POST",
	     dataType: "json",
	     url: '<%=basePath%>cust/getCompanyName',
	     data:'',
	     success: function (result) {
	       //查询列表的客户查询条件
	       var last=JSON.stringify(result.msg); 
	       $('#companyName').typeahead({
	     	  source : result.msg,
	    		  menu : "<ul class='typeahead dropdown-menu cust' style='z-index:99999;'></ul>"
	    	   });
	     }
	  });
	});  
	
function setInfo(){
	var customerId=${infomap.customerId};
	$("#customerId").val(customerId);
	//客户属性
	var customerType='${infomap.customerType}';
	$("#clientAttr").val(customerType);
	if(customerType==2){
		$(".customerName-name").css("display","none");
	       $(".company-name").css("display","block");
	}else if(customerType==1){
		$(".company-name").css("display","none");
	       $(".customerName-name").css("display","block");
	}else{
		$(".customerName-name").css("display","block");
	       $(".company-name").css("display","block");
	}
	//系统版本
	var sysName='${infomap.sysName}';
	$("#sysName").val(sysName);
	//品牌名称
	var brandName='${infomap.brandName}';
	$("#brandName").val(brandName);
	//公司名称
	var companyName='${infomap.companyName}';
	if(customerType!=1){
		$("#companyName").val(companyName);
	}

	//店铺名称
	var customerName='${infomap.customerName}';
	if(customerType!=2){
		$("#customerName").val(customerName);
	}
	//选择区域
	var province='${infomap.province}';
	$("#province").val(province);
	$('#province').change();
	var city='${infomap.city}';
	$("#city").val(city);
	$('#city').change();
	var area='${infomap.area}';
	$("#area").val(area);
	//地址
	var addr='${infomap.addr}';
	$("#addr").val(addr);
	//身份证号码
	var customerCode='${infomap.cardNo}';
	$("#customerCode").val(customerCode);
	//负责人姓名
	var principalName='${infomap.principalName}';
	$("#principalName").val(principalName);
	//负责人职位
	var principalDuty='${infomap.principalDuty}';
	$("#principalDuty").val(principalDuty);
	//负责人电话
	var phone='${infomap.phone}';
	$("#phone").val(phone);
	//负责人电话
	var userName='${infomap.userName}';
	$("#userName").val(userName);
	//是否付费
	var isPay='${infomap.isPay}';
	$("#isPay").val(isPay);
}
function updatecust(){
		
	var customerId =$("#customerId").val();
	var customerType =$("#clientAttr").val();
	if(customerType==null||customerType==""){
		alert("客户属性不能为空");
		return;
	}
	if(customerType==1){
		$("#companyName").val("");
	}
	if(customerType==2){
		$("#customerName").val("");
	}
	
	//系统版本
	var sysName =$("#sysName").val();
	if(sysName==null||sysName==""){
		alert("系统版本不能为空");
		return;
	}
	//品牌名称
	var brandName =$("#brandName").val();

	//公司名称
	if(customerType!=1){
	var companyName =$("#companyName").val();
		if(companyName==null||companyName==""){
			alert("公司名称不能为空");
			return;
		}
	}
	

	//店铺名称
	var customerName =$("#customerName").val();
	//当客户属性不属于连锁店时候
	if(customerType!=2){
		if(customerName==null||customerName==""){
			alert("店铺名称不能为空");
			return;
		}
	}
	
	//选择区域
	var province =$("#province").val();
	var city =$("#city").val();
	var area =$("#area").val();
	
	//地址
	var addr =$("#addr").val();

	//身份证号码
	var customerCode =$("#customerCode").val();

	//负责人姓名
	var principalName =$("#principalName").val();
	if(principalName==null||principalName==""){
		alert("负责人姓名不能为空");
		return;
	}
	
	
	//负责人职位
	var principalDuty =$("#principalDuty").val();
	if(principalDuty==null||principalDuty==""){
		alert("负责人职位不能为空");
		return;
	}
	//负责人电话
	var phone =$("#phone").val();
	if(phone==null||phone==""){
		alert("电话不能为空");
		return;
	}
	//负责人电话
	var userName =$("#userName").val();
	if(userName==null||userName==""){
		alert("客户帐号不能为空");
		return;
	}
	
	 $.ajax({
         type: "POST",
         dataType: "json",
         url: baseUrl+'cust/updateCust',
         data:'customerId='+customerId+'&customerType='+customerType+'&sysName='+sysName+'&brandName='+brandName+'&customerName='+customerName+'&province='+province+'&city='+city
             +'&area='+area+'&addr='+addr+'&cardNo='+customerCode+'&principalName='+principalName+'&principalDuty='+principalDuty
             +'&phone='+phone+'&companyName='+companyName+'&userName='+userName,
         success: function (result) {
        	 if(result.code==-1){
        		 alert(result.msg);
        		 return;
        	 }else if(result.code==-2){
        		 alert(result.msg);
        		 return;
        	 }else{
        		 alert(result.msg);
        	 }
         }
     });
}
</script>  

  <script type='text/javascript' src='<%=basePath %>js/cust.js'></script>

  </body>
</html>