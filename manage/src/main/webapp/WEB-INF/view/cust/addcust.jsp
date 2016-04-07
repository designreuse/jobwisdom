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
        <div id="breadcrumb"> <a href="<%=basePath %>homepage/view/query" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 主页看板</a> 
        <a href="#" class="tip-bottom">开户注册</a> </div>
        <!--<h1>新增用户</h1>-->
    </div>
    <div class="container-fluid">
        <hr>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5>账号注册 </h5>
                    </div>
                    <div class="widget-content nopadding">
                        <div  class="form-horizontal">
						
						<div class="control-group">
                                <div class="span8">
                                <label for="normal" class="control-label"></label>
                                <div class="controls">
                             <span style="color:black;">尊敬的用户: 您还剩下 <span style="color: red">${money}</span>个账号可以新增</span>
                                    </div>
                                </div>
                            </div>
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
                                        <select name="" id="sysName" class="span12" onchange="changemoney()">
                                        <option value="">选择版本</option>
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
                            <div class="control-group">
                                
                              <button class="btn btn-success client-add-save"  style="margin-right: 25%" onclick="addcust()"><a style="text-decoration:none;color: #fff;">开户</a></button><h6>
                             <!-- <button class="btn btn-success client-add-save" onclick="goback()"><a href="#"  style="text-decoration:none;color: #fff;">返回查询页面</a></button> -->
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

</script>  

  <script type='text/javascript' src='<%=basePath %>js/cust.js'></script>

  </body>
</html>