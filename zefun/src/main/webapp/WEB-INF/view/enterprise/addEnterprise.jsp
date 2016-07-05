<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/business_manage_2.css" type="text/css" />
<link href="<%=basePath%>css/city-picker.css" rel="stylesheet" type="text/css" />
<body>
<style>
.city-picker-span{position:relative;top:10px}
.new_business_content{font-size:16px;color:black}
.new_business_content select{border-radius:12px;width:130px;border:1px solid black;margin-left:57px}
.title{background:none!important}
.province span{width:auto}
.city-picker-dropdown{bottom:0px!important;left:320px!important}
</style>
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
					  <td>状态</td>
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
						  <c:if test="${(enterpriseInfoDto.enterpriseStatus eq 1)}">
						  <td>正常</td>
						  </c:if>
						  <c:if test="${(enterpriseInfoDto.enterpriseStatus eq 2)}">
						  <td>欠费</td>
						  </c:if>
					
						  <c:if test="${(enterpriseInfoDto.enterpriseStatus eq 3)}">
						  <td>停用</td>
						  </c:if>
						  <td>
						  <span onclick="update(${enterpriseInfoDto.enterpriseInfoId},'${enterpriseInfoDto.storeAccount}','${enterpriseInfoDto.enterpriseName}','${enterpriseInfoDto.enterpriseLinkname}',
						  '${enterpriseInfoDto.enterpriseLinkphone}','${enterpriseInfoDto.enterpriseAddress}','${enterpriseInfoDto.enterpriseProvince}','${enterpriseInfoDto.enterpriseCity}','${enterpriseInfoDto.enterpriseAccount.enterpriseEdition}','${enterpriseInfoDto.enterpriseAccount.beginTime}','${enterpriseInfoDto.enterpriseAccount.finishTime}') "><img src="<%=basePath%>images/handle_1.png"></span>
						  
						    <c:if test="${enterpriseInfoDto.enterpriseStatus eq 1}">
						   <span onclick="disableAndStart(1,${enterpriseInfoDto.enterpriseInfoId},'${enterpriseInfoDto.storeAccount }')"><img src="<%=basePath%>images/disable.png" width='20' style="position:relative;left:6px;top:-1px"></span>
						  </c:if>
						   <c:if test="${!(enterpriseInfoDto.enterpriseStatus eq 1)}">
						  <span onclick="disableAndStart(0,${enterpriseInfoDto.enterpriseInfoId},'${enterpriseInfoDto.storeAccount }')"><img src="<%=basePath%>images/start.png" width='24' style="position:relative;top:2px"></span>
						  </c:if>
						  </td>
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
	                  <option id="1" value = "1">1年</option>
	                  <option id="2" value = "2">2年</option>
	                  <option id="3" value = "3">3年</option>
	                  <option id="4" value = "4">4年</option>
	                  <option id="5" value = "5">5年</option>
	               </select>
	    </div>
		<div class="province">
<!-- 		  <p><span style="position:relative;top:18px">省/市</span><input input id="city-picker3" class="form-control" readonly type="text" value="" data-toggle="city-picker"></p> -->
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
	var enterInfoId =null;
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
		var enterpriseEdition = jQuery("input[name='enterpriseEdition']:checked").attr("value");
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
		"&enterpriseEdition="+enterpriseEdition+"&useTime="+useTime+"&enterpriseInfoId="+enterInfoId;
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
		jQuery("#storeAccount").val("");
		jQuery("#enterpriseName").val("");
		jQuery("#enterpriseLinkphone").val("");
		jQuery("#enterpriseLinkname").val("");
		jQuery("#searchtext").val("");
		jQuery("input[name='enterpriseEdition'][value='1']").click();
		jQuery("select[name='useTime']").get(0).selectedIndex = 0;
		jQuery("#city-picker3").citypicker('reset');
		jQuery("#storeAccount").removeAttr("disabled");
		jQuery("select[name='useTime']").removeAttr("disabled");
		enterInfoId=null;
	}
	
	
	  function update(enterpriseInfoId,storeAccount,enterpriseName,enterpriseLinkname,enterpriseLinkphone,enterpriseAddress,enterpriseProvince,enterpriseCity,enterpriseEdition,beginTime,finishTime){
		jQuery("#storeAccount").val(storeAccount);
		jQuery("#enterpriseName").val(enterpriseName);
		jQuery("#enterpriseLinkphone").val(enterpriseLinkphone);
		jQuery("#enterpriseLinkname").val(enterpriseLinkname);
		jQuery("#searchtext").val(enterpriseAddress);
		enterInfoId=enterpriseInfoId
		var number=1;
		if(	enterpriseEdition =="无限版" ){
			number=4;
		}
		else if( enterpriseEdition =="10店版"  ){
			number=3;
		}
		else if( enterpriseEdition =="5店版"  ){
			number=2;
		}
		else if( enterpriseEdition =="单店版"  ){
			number=1;
		}
		jQuery("input[name='enterpriseEdition'][value='"+number+"']").click();
		var endDate = new Date(Date.parse(finishTime));
		var startDate = new Date(Date.parse(beginTime));
		var time =(endDate-startDate)/(1000*60*60*24*365)-1;
		jQuery("select[name='useTime']").get(0).selectedIndex = time;
		
		jQuery("#city-picker3").citypicker('reset');
		jQuery("#city-picker3").citypicker('destroy');
		jQuery('#city-picker3').citypicker({
		  province: enterpriseProvince,
		  city: enterpriseCity,
		  district: '溧阳市'
		});
		jQuery("#storeAccount").attr("disabled","true");
		jQuery("select[name='useTime']").attr("disabled","disabled");
		jQuery(".zzc").show();
	  }
	  
	  function disableAndStart(start, enterpriseInfoId, storeAccount){
			jQuery.ajax({
		        cache: true,
		        type: "POST",
		        url: baseUrl + "enterprise/action/disableAndStart",
		        data: "start="+start+"&enterpriseInfoId="+enterpriseInfoId+"&storeAccount="+storeAccount,
		        async: false,
		        success: function(data) {
		        	if (data.code != 0) {
		                dialog(data.msg);
		                return;
		            }
		        	dialog(data.msg);
		        	location.reload();
		        }
		    });
	  }
</script>

</body>
</html>