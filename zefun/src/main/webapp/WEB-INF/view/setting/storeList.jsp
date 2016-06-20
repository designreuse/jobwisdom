<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page import="java.util.Date" %>
<link rel="stylesheet" href="<%=basePath%>css/add_store.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/project.css" type="text/css" />
<link href="<%=basePath%>css/city-picker.css" rel="stylesheet" type="text/css" />
<style type="text/css">
.shop_address_{position:relative;left:30px}

	.rollBox{width:704px;padding:12px 0 5px 6px;}
.rollBox .LeftBotton{height:70px;width:50px;background:url('./assets/images/left_click.png') no-repeat 11px 0;overflow:hidden;float:left;display:inline;margin:25px 0 0 0;cursor:pointer;background-size:40px;position:relative;top:290px;left:-90px}
.rollBox .RightBotton{height:70px;width:50px;background:url('./assets/images/right_click.png') no-repeat -8px 0;overflow:hidden;float:right;display:inline;margin:25px 0 0 0;cursor:pointer;background-size:40px;position:relative;    top: -340px;
    left: 350px;}
.rollBox .Cont{width:910px;overflow:hidden;float:left;margin-left:70px}
.rollBox .ScrCont{width:10000000px;}
.rollBox .Cont .pic{float:left;text-align:center;}
.rollBox .Cont .pic img{padding:4px;background:#fff;border:1px solid #ccc;display:block;margin:0 auto;}
.rollBox .Cont .pic p{line-height:26px;color:#505050;}
.rollBox .Cont a:link,.rollBox .Cont a:visited{color:#626466;text-decoration:none;}
.rollBox .Cont a:hover{color:#f00;text-decoration:underline;}
.rollBox #List1,.rollBox #List2{float:left;}
#preview{width:100px;border:none;overflow:hidden;}
#imghead {filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=image);width:80px;height:80px}

</style>
<head>
    <script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
    <script type="text/javascript">
    
    function editPage (imgUrl) {
    	xiuxiu.embedSWF("altContent2", 5, 700, 500);
    	
    	xiuxiu.onInit = function (id)
    	{
            xiuxiu.setUploadType(3);
            if (imgUrl != null) {
            	xiuxiu.loadPhoto(qiniuUrl + imgUrl, false);
            }
    	}
    	xiuxiu.onSaveBase64Image = function (data, fileName, fileType, id)
    	{
    		data = "data:image/"+fileType+";base64," + data;
            zccCallback(data);
    	}
    	
    	xiuxiu.onDebug = function (data)
    	{
            dialog("网咯繁忙，请重试！");
    	}
    	
    	xiuxiu.onClose = function (id)
    	{
            jQuery(".mask").hide();
    	}
    	
    }
	
    </script>
</head>
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>  
				 <div class='content_right clearfix' style="background:white">
				   <div class="add_store_div">
				      <ul class="clearfix add_store_back">
					     <li class="active"><span>门店基本信息</span></li>
					     <li><span style="position:relative;top:15px">门店授权码</span></li>
					     <li><span style="position:relative;top:20px">门店短信</span></li>
					  </ul>
				
				    <div class="add_store_include">
				     <div class="add_store_content">
					   <div class="add_store_content_ clearfix">
					     <div class="add_store_button"> <button onclick="addStore()">新增</button> <button onclick="upgradeRenew()">升级续费</button> <button onclick="consumptionRecord()">消费记录</button></div>
						  <div class="add_store_span"><span>已创建门店数：<em>${enterpriseAccount.alreadyStoreNum }</em></span><span>剩余可创建门店数：<em>${enterpriseAccount.balanceStoreNum }</em></span><span>到期时间：<em>${enterpriseAccount.finishTime }</em></span></div>
					   
					   </div>
					   <div class="dd_store_content_2">
					     <ul class="clearfix">
						   <c:forEach items="${storeInfoList}" var="storeInfo" varStatus="status">
					           <li>
							    <span class="business">营业</span>
							      <p class="shop_name">${storeInfo.storeName}</p>
								  <span class="shop_pic"><img  src="<%=qiniuPath%>${storeInfo.storeLogo}"></span>
								  <div class="store_right">
								     <span class="connect_people">联系人</span>
									 <p>${storeInfo.storeLinkname}</p>
								      <span class="phone_number">电话号码</span>
								      <p style="position:relative;right:16px">${storeInfo.storeLinkphone}</p>
								  </div>
								  <div class="clearfix add_store_content_bottom">
								     <div class="add_1" style="border-right:1px solid #ccc" onclick="editStore(${storeInfo.storeId})" >
									        编辑<span><img src="<%=basePath%>images/add_store_1.png"></span>
									 </div>
									 <div class="add_1" style="position:relative;left:100px">
									        删除<span><img src="<%=basePath%>images/add_store_2.png"></span>
									 </div>
								  </div>
							    </li>
					       </c:forEach>
						 </ul>
					   </div>
					 </div>
					 <div class="add_store_content clearfix">
					   <div class="add_store_two_left">
					      <input type="hidden" name = "storeAuthorityId">
					      <div class="left_detail">
						     <p>门店<p>
							 <select name = "storeSelect" onchange="changeStore(this)">
							   <option>选择门店 </option>
							   <c:forEach items="${storeInfoList}" var="storeInfo" varStatus="status">
							       <option value="${storeInfo.storeId }">${storeInfo.storeName }</option>
							   </c:forEach>
				              </select>
				           </div>
						    <div class="left_detail">
						     <p>员工<p>
							 <select name = "employeeSelect" class='chzn-select mr5 input-medium'>
							   <option>选择员工 </option>
				              </select>
				           </div>
						   
						    <div class="left_detail">
						     <p>授权码<p>
							 <input type="text" name = "authorityValue" class = ""  style="width: 130px;height: 22px;">
							  <span><a href="javascript:;">*</a>
							    <em><i>请输入6位密码，
				数字和字母组合。</i></em>
							  </span>
				           </div>
						   
						   
						     <div class="left_detail">
						      <button onclick="addAuthority()">保存</button>
				           </div>
				        </div>
						
						<div class="right_detail">
						  <table id = "authorityTable">
						    <tr>
							   <td>门店名称</td>
							   <td>授权码</td>
							   <td>员工</td>
							   <td>最后操作时间</td>
							   <td>操作</td>
							</tr>
							
							<c:forEach items="${enterpriseStoreAuthoritys}" var="enterpriseStoreAuthority" varStatus="status">
							    <tr>
								   <td>${enterpriseStoreAuthority.storeName }</td>
								   <td>${enterpriseStoreAuthority.authorityValue }</td>
								   <td>${enterpriseStoreAuthority.employeeCode } ${enterpriseStoreAuthority.name }</td>
								   <td>${enterpriseStoreAuthority.createTime }</td>
								   <td><em onclick="updateAuthority(this, ${enterpriseStoreAuthority.storeAuthorityId }, ${enterpriseStoreAuthority.storeId }, ${enterpriseStoreAuthority.employeeId }, '${enterpriseStoreAuthority.authorityValue }')">修改</em></td>
								</tr>
							</c:forEach>
							
						  </table>
						
						</div>
					   
					 
					 </div>
					 <div class="add_store_content">
					   <div class="add_store_content_ clearfix">
					     <div class="add_store_button"> <button onclick = "msnRecharge()">充值</button> <button  onclick="rechargeFlow()">充值记录</button></div>
						  <div class="add_store_span_1">可分配短信信息：<span name = "msnNumSpan" >${enterpriseAccount.balanceMsnNum }</span></div>
				       </div> 
				         <div class="distribution clearfix">
				           <c:forEach items="${storeInfoList}" var="storeInfo" varStatus="status">
							    <div class="distribution_list">
								   <p>${storeInfo.storeName}<span>分配</span></p>
								   <div class="distribution_list_text">
								     <p>累计短信数量：${storeInfo.totalSms}条</p>
									  <p>剩余短信数量：${storeInfo.balanceSms}条</p>
								   </div>
								   <div class="distribution_alert">
								     <p>拥有${enterpriseAccount.balanceMsnNum }条</p>
								     <p>增加<input type="text" name = "distributionNum"><span style="position:relative;right:20px;color:#9f9d9d">条</span></p>
									 <div class="distribution_alert_button">
									   <button onclick="distributionMsn(this, '${storeInfo.storeId}')">确认</button>
									   <button onclick = "cancelAlert(this)">取消</button>
									 </div>
								   </div>
								</div>
					       </c:forEach>
						 </div>	   
					 </div>
				  </div>	 
				  </div>
		    </div>
		</div>
    </div>
</div>

<div class="zzc" style="display:none" name = "modelDiv" id = "addOrUpdateStore">
 
	<div class="set_content clearfix" >
	   <p class="new_shop">新建店铺</p>
	  <div class="clearfix add_store_content_1"> 
	   <div class="content_left" > 
	     <p style="font-size:14px">设置店铺LOGO:</p>

		   <div id="preview"><img border=0 affiliatedImage="" name="affiliatedImage" src="<%=qiniuPath%>system/profile/set_img.png" width="180" height="180" /></div>

		  <P>*此logo用于移动端店铺介绍页面</P>
	      <input type="hidden" class = "hide" name = "storeId" style="width: 80px; height: 80px;top: -124px;opacity:0;cursor:pointer"/>
	     
		<div class="set_name">
		 <div class="shop_name_">
		      <p>设置门店名称</p>
		      <input type="text" id="storeName">
	     </div>
	      <div class="shop_name_">
		      <p>设置店铺电话(多个以,号分割)</p>
		      <input type="text" id="storeTel">
	     </div>
		  <div class="shop_name_">
		      <p>门店负责人姓名</p>
		      <input type="text" id="storeLinkname">
	     </div>
		  <div class="shop_name_">
		      <p>门店负责人电话</p>
		      <input type="text" id="storeLinkphone">
	     </div>
	   
	    </div>
	  </div>
	  
	  <div class="content_right_">
	     <P class="shop_address_">店铺地址</P>   
		 <div class="select_city">
		     <input id="city-picker3" class="form-control" readonly type="text" value="" data-toggle="city-picker">
		     
		     <div class="detail_address">
			   <div>详细地址</div>
			   <textarea style="height:86px;margin-top:10px;border-radius:8px!important;width:220px;position:relative;left:10px" id = "searchtext"></textarea>
			 </div>
			 <div class="detail_address">
			   <div>操作员工号<em style="color:red;font-size:16px;font-weight:bold;display:inline-block;margin-left:4px" id = "userName">1007</em></div>
			  <!--  <input type="text" id = "userName" readonly>  -->       		   
			 </div>
		 
		 </div>
	 </div>
	</div> 
		 <div class="add_button">
		  <button class="submit_" onclick="saveStoreInfo()" >确认</button>
		  <button class="cancel_" onclick="cancel()">取消</button>
		 </div> 
   </div>

</div>

<div class="zzc1" style="display:none" name = "modelDiv" id = "consumptionRecordDiv">
    <div class="add_balance">
	  <p>收支明细</p>
	  <div class="add_balance_content">
	     <table id = "consumptionRecordTBODY">
		   <tr>
			 <td style="color:black">收支金额（元）</td>
			 <td>当前金额（元）</td>
			 <td>收支类型</td>
			 <td>消费时间</td>
		   </tr>

		 </table>
	  
	  </div>
	  <div class="add_balance_button">
		 <button onclick = "cancel()">取消</button>
	  </div>
	</div>
</div>
	
<div class="zzc2" style="display:none" name = "modelDiv" id = "msnRechargeDIV">
    <div class="add_balance" style="height:475px">
	  <p>短信充值</p>
	  <div class="add_balance_content" style="margin:20px auto;height:290px;overflow:visible">
	    <p>账户余额(元)：<em name = "balanceAmountEM"></em></p>
	    <p>剩余短信（条）：<em id = "balanceMsnNumEM"></em></p>
		<div class="this_buy">
		  <p>本次购买</p>
		  <ul class="clearfix">
		    <li><input type="radio" name = "msnRechargeType" value = "1"><span>100条（9元）</span></li>
			 <li><input type="radio" name = "msnRechargeType" value = "2"><span>500条（45元）</span></li>
			  <li><input type="radio" name = "msnRechargeType" value = "3"><span>1000条（90元）</span></li>
			   <li><input type="radio" name = "msnRechargeType" value = "4"><span>2000条（180元）</span></li>
			  <li><input type="radio" name = "msnRechargeType" value = "5"><span>10000条（900元）</span></li>
			  <li><input type="radio" name = "msnRechargeType" value = "6">其他<em><input type="text" name = "msnNumber" onfocus="msnInputFocus(this)"  placeholder="输入购买数量"></em></li>
			  
		  </ul>
		  <div class="total_price">总付：<span>60元</span></div>
		</div>
	  </div>
	  <div class="add_balance_button" style="position:relative;top:20px">
	    <button onclick="saveMsnRecharge()">确认</button>
		 <button onclick = "cancel()">取消</button>
	  
	  </div>
	</div>
</div>	

<div class="zzc3" style="display:none" name = "modelDiv" id = "rechargeFlow">
  <div class="assigned">
     <p>充值分配记录</p>
     <div class="assigned_table">
         <table id = "rechargeFlowTable">
             <tr>
			   <td>公司/门店</td>
			   <td>时间</td>
			   <td>当前余量（条）</td>
			   <td>数量（条）</td>
			 </tr>
			
      	 </table>  
    </div> 
	<div class="assigned_button">
	  <button >确认</button>
	  <button onclick = "cancel()">取消</button>
	</div>
  </div>
</div>

<div class="zzc4" style="display:none" name = "modelDiv" id = "upgradeRenew">
  
     <div class="assigned">
      <p>升级 续费</p>
	  <div class="assigned_table" style="overflow:visible">
	    <span class="assigned_table_span"><em>当前版本</em><em name = "enterpriseEditionUpgradeRenew">${enterpriseAccount.enterpriseEdition }</em></span>
		<span class="assigned_table_span" style="margin:0"><em>到期时间</em><em name = "finishTimeUpgradeRenew">${enterpriseAccount.finishTime }</em></span>
		<span class="assigned_table_span" style="margin-right:20px">
		    <em>升级版本</em>
		    <select name = "upgradeValue" onchange="updateUpgradeValue(this)">
		       <option value="0" >选择版本</option>
		    </select>
		</span>
		<span class="assigned_table_span" >
		   <em>续加时间</em>
		   <select name = "renewDate" onchange="updateUpgradeValue(this)">
		      <option value="0">选择时常</option>
		      <option value="1">1年</option>
		      <option value="2">2年</option>
		      <option value="3">3年</option>
		      <option value="4">4年</option>
		      <option value="5">5年</option>
		   </select>
		</span>
		<span class="assigned_table_span"  style="margin:0;position:relative;top:104px;left:350px;font-size:14px">
		   <em>缴费金额</em>
		   <em name = "payableEM" style="color:red;font-size:16px;">0.00</em>
		   <i style="position:relative;left:-25px">元</i>
	    </span>
	    
		<div class="assigned_table_price_">当前余额：<em name = "balanceAmountEM">${enterpriseAccount.balanceAmount}</em>元</div>
		<div class="assigned_table_sure_button">
		  <button onclick= "confirmUpgradeRenew()">确认</button>
		   <button onclick = "cancel()">取消</button>
		</button>
	  </div>
    </div> 
</div>
</div>

<!-- 美图秀秀 -->
<div class="mask" style="display: none;">
   <div id="flashEditorOut" style="position: relative;" >
   <span class="mask_close" style="position:absolute;right:-5px;top:-5px"><img onclick="xiuxiu.onClose();" src="<%=basePath %>images/seo_close.png"></span>
        <div id="altContent2">
            <h1>美图秀秀2</h1>
        </div>
	</div>
</div>

<script type="text/javascript">
var cssWidth = 200;
var cssHeight = 200;
var qiniuUrl = '<%=qiniuPath%>';
var imgObject;

var storeEmployeeListStr =  '${storeEmployeeListStr}';
var storeEmployeeList;
if (!isEmpty(storeEmployeeListStr)) {
	storeEmployeeList = eval("(" + storeEmployeeListStr + ")");
}

var enterpriseEdition = '${enterpriseAccount.enterpriseEdition }';
var alreadyStoreNum = '${enterpriseAccount.alreadyStoreNum}';
var priceMoneyOrTimeStr = '${priceMoneyOrTimeStr}';
var priceMoneyOrTime;
if (!isEmpty(priceMoneyOrTimeStr)) {
	priceMoneyOrTime = eval("(" + priceMoneyOrTimeStr + ")");
}
</script>
<script type="text/javascript" src="<%=basePath %>/js/setting/storeList.js"></script>
<script type="text/javascript" src="<%=basePath %>js/base/zcc.js"></script>
<script src="<%=basePath%>js/common/city-picker.data.js"></script>
<script src="<%=basePath%>js/common/city-picker.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/md5.js"></script>

</body>
</html>