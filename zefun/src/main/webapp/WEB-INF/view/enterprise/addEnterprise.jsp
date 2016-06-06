<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/business_manage_2.css" type="text/css" />
<link href="<%=basePath%>css/city-picker.css" rel="stylesheet" type="text/css" />
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<!-- 页面元素开始 -->
            <div class='content_right clearfix'>
			     <p class="payroll_list"><button class="new_button" onclick="showModal()">新建</button>
				  
				   <span class="payroll_1_search">
				     <input type="text" placeholder="名称/代号">
				     <em><img src="assets/images/payroll_1_search.png"></em>
				   </span>
				 </p>
			    <div class="payroll_table_content">	 
				 <table class="payroll_table">
				    <tr>
					  <td>企业名称</td>
					  <td>企业代号</td>
					  <td>企业负责人姓名</td>
					  <td>企业负责人电话</td>
					  <td>渠道商</td>
					  <td>创建版本</td>
					  <td>操作</td> 
			       </tr>
			       <c:forEach items="${pages.results}" var="enterpriseInfoDto" varStatus="status">
			           <tr>
						  <td>${enterpriseInfoDto.enterpriseName }</td>
						  <td>${enterpriseInfoDto.storeAccount }</td>
						  <td>${enterpriseInfoDto.enterpriseLinkname }</td>
						  <td>${enterpriseInfoDto.enterpriseLinkphone }</td>
						  <td></td>
						  <td>${enterpriseInfoDto.enterpriseAccount.enterpriseEdition }</td>
						  <td><span><img src="<%=basePath%>images/handle_1.png"></span><span><img src="<%=basePath%>images/handle_2.png"></span></td>
				       </tr>
			       </c:forEach>
				 </table>
				</div> 
			</div>
		</div>
    </div>
</div>

<div class="zzc" style="display: none;">
   <div class="zzc_new_business">
      <p>新建企业</p>
	  <div class="new_business_content">
       	<div class="clearfix">   
	     <div class="new_business_content_left">
		   <ul>
             <li><span>企业名称</span><input type="text" id="enterpriseName"></li> 
             <li><span>企业代号</span><input type="text" id="storeAccount"></li> 
			 <li><span>企业负责人姓名</span><input type="text" id="enterpriseLinkname"></li>
             <li><span>企业负责人电话</span><input type="text" id="enterpriseLinkphone"></li> 			 
           </ul>		   
		 
		 </div>
		 <div class="new_business_content_right">
		    <span>创建版本</span>
		    <ul>
			  <li><span><input type="radio" name="enterpriseEdition" checked value = "1"><em><i>(1个门店)</i></em></span></li>
			  <li><span><input type="radio" name="enterpriseEdition" value = "2"><em><i>(2~5个门店)</i></em></span></li>
			  <li><span><input type="radio" name="enterpriseEdition" value = "3"><em><i>(5~10个门店)</i></em></span></li>
			  <li><span><input type="radio" name="enterpriseEdition" value = "4"><em><i>(10个以上门店)</i></em></span></li>
          	</ul>
			
		 </div>
	    </div>
	    <div>使用时长<select name = "useTime">
	                  <option value = "1">1年</option>
	                  <option value = "1">2年</option>
	                  <option value = "1">3年</option>
	                  <option value = "1">4年</option>
	                  <option value = "1">5年</option>
	               </select>
	    </div>
		<div class="province">
		  <p><span>省/市</span><input input id="city-picker3" class="form-control" readonly type="text" value="" data-toggle="city-picker"></p>
		  <p><span>详细地址</span><textarea id= "searchtext"></textarea></p>
		 </div>
		 <div class="province_button">
		    <button onclick="saveStoreInfo();">确认</button>
		    <button onclick="cancleModal()">取消</button>
		 </div>
	  </div>
   </div>
</div>

<script src="<%=basePath%>js/common/city-picker.data.js"></script>
<script src="<%=basePath%>js/common/city-picker.js"></script>
<script src="<%=basePath%>js/common/main.js"></script>
<script type="text/javascript">
	
	
	
	
	//提交企业信息
	function saveStoreInfo(){
		var enterpriseName = jQuery("#enterpriseName").val();
		var enterpriseLinkphone = jQuery("#enterpriseLinkphone").val();
		var enterpriseLinkname = jQuery("#enterpriseLinkname").val();
		var storeAccount = jQuery("#storeAccount").val();
		var addressList = jQuery("#city-picker3").val().split('/');
		var enterpriseProvince = addressList[0];
		var enterpriseCity = addressList[1];
		var enterpriseAddress = jQuery("#searchtext").val();
		var enterpriseEdition = jQuery("input[name='enterpriseEdition']:checked").val();
		var useTime = jQuery("select[name='useTime']").val();
		if (isEmpty(enterpriseName)) {
	        dialog("请填写您的企业名称");
	        return;
	    }
		
		if (isEmpty(storeAccount)) {
	        dialog("请填写您的企业代号");
	        return;
	    }
		if (isEmpty(enterpriseLinkname)) {
	        dialog("请填写您的企业负责人姓名");
	        return;
	    }
		if (isEmpty(enterpriseLinkphone)) {
	        dialog("请填写您的企业负责人电话");
	        return;
	    }
		
		if (isEmpty(enterpriseProvince)) {
			dialog("请选择省");
			return;
		}
		if (isEmpty(enterpriseCity)) {
			dialog("请选择市");
			return;
		}
		if (isEmpty(enterpriseAddress)) {
			dialog("请填写您的店铺地址");
			return;
		}
		
		var data = "enterpriseName=" + enterpriseName + "&enterpriseLinkphone=" + enterpriseLinkphone + "&enterpriseLinkname=" + enterpriseLinkname + 
		"&storeAccount=" + storeAccount +"&enterpriseProvince=" +enterpriseProvince + "&enterpriseCity=" +enterpriseCity + "&enterpriseAddress=" +enterpriseAddress +
		"&enterpriseEdition="+enterpriseEdition+"&useTime="+useTime;
		submit(data, "保存成功");
	}
	
	//数据提交
	function submit(data, msg){
		jQuery.ajax({
	        cache: true,
	        type: "POST",
	        url: baseUrl + "enterprise/action/addEnterprise",
	        data: data,
	        async: false,
	        success: function(data) {
	        	if (data.code != 0) {
	                dialog(e.msg);
	                return;
	            }
	        	dialog(msg);
	        	location.reload();
	        }
	    });
	}
	
	function showModal() {
		jQuery(".zzc").show();
	}
	
	function cancleModal() {
		jQuery(".zzc").hide();
	}
</script>

</body>
</html>