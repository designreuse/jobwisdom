<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="<%=basePath%>css/coupon.css"	type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/common.css"	type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/js/My97DatePicker/WdatePicker.js"></script>

<body>

	<div class="mainwrapper" id="mainwrapper" name="mainwrapper"
		style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel"
				style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>
				<div class="content_right clearfix">
					<p class="coupon_button">
						<button onclick="show(1)">新建优惠券</button>
					</p>
					<div class="coupon_table">
						<table>
							<tbody id="tables">
								<tr>
									<td>优惠券名称</td>
									<td>抵扣金额</td>
									<td>使用条件</td>
									<td>用量/发行量</td>
									<td>已使用次数</td>
									<td>发布时间</td>
									<td>结束时间</td>
									<td>优惠券有效时间</td>
									<td>状态</td>
									<td>操作</td>
								</tr>

								<c:forEach var="coupon" items="${page.results}">
									<tr name="trs" id="${coupon.couponId}">
										<td>${coupon.couponName}</td>
										<td>${coupon.couponPrice}</td>
										<td>${coupon.couponNames}</td>
										<td>${coupon.couponNum}/${coupon.couponNumber}</td>
										<td>${coupon.couponIsUse}</td>
										<td>${coupon.releaseTime}</td>
										<td>${coupon.couponStopTime}</td>
										<td>${coupon.couponStartTime}天</td>
										<c:if test="${coupon.status eq 1}"><td>发布中</td></c:if>
										<c:if test="${coupon.status eq 2}"><td>未发布</td></c:if>
										<c:if test="${coupon.status eq 3}"><td>已结束</td></c:if>
										 <td>
<!-- 										 上架功能 -->
<%-- 										<c:if test="${(coupon.isType eq 1)}"> --%>   
<%-- 												<em onclick="update(${coupon.couponId},0)"><span class="up" id="${coupon.couponId}">上架</span></em> --%>
<%-- 												<em onclick="update(${coupon.couponId},1)"><span class="up1" id="${coupon.couponId}1" style="display: none">下架</span></em> --%>
<%-- 											</c:if> <c:if test="${(coupon.isType eq 0)}"> --%>
<%-- 												<em onclick="update(${coupon.couponId},0)"><span class="up" id="${coupon.couponId}" style="display: none">上架</span></em> --%>
<%-- 												<em onclick="update(${coupon.couponId},1)"><span class="up1" id="${coupon.couponId}1">下架</span></em> --%>
<%-- 											</c:if>  --%>
											<em class="up_preview"onclick="viwe(${coupon.couponId},'${coupon.couponColour}')">预览</em>
											<c:if test="${coupon.status != 1}">
												<i onclick="deleted(${coupon.couponId},'${coupon.couponStopTime}',${coupon.couponStartTime},'${coupon.releaseTime}')">删除</i>
												<em onclick="updateSave('${coupon.couponId}','${coupon.storeType}','${coupon.couponMan}','${coupon.couponColour}','${coupon.couponType}','${coupon.startType}','${coupon.couponVantages}','${coupon.priceSigle}','${coupon.couponNumber}','${coupon.couponStartTime}')"><img
												src="<%=basePath%>/images/coupon_write.png"></em>
												
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
					</div>
					<%@ include file="/template/page.jsp"%>
				</div>


			</div>
		</div>
	</div>
	<div class="zzc2" style="display: none;">
		<div class="coupon_card">
			<div class="coupon_card_content">
				<div class="card_logo">券</div>
				<div class="coupon_card_price">
					¥<span id="price">500</span><em id="type">通用</em>
				</div>
				<p id="time">到期时间2016/12/12</p>
			</div>
			<div class="select_color_val">
				<ul class="clearfix">
					<li value="d93717" style="background: #d93717"></li>
					<li value="f8b65b" style="background: #f8b65b"></li>
					<li value="585eaa" style="background: #585eaa"></li>
					<li value="5c7a29" style="background: #5c7a29"></li>
					<li value="5f3c23" style="background: #5f3c23"></li>
					<li value="2b4490" style="background: #2b4490"></li>
					<li value="b36d41" style="background: #b36d41"></li>
					<li value="a7573b" style="background: #a7573b"></li>
					<li value="f05b72" style="background: #f05b72"></li>
					<li value="817936" style="background: #817936"></li>
					<li value="2e3a1f" style="background: #2e3a1f"></li>
					<li value="46485f" style="background: #46485f"></li>
					<li value="543044" style="background: #543044"></li>
					<li value="401c44" style="background: #401c44"></li>
					<li value="225a1f" style="background: #225a1f"></li>
					<li value="b69968" style="background: #b69968"></li>
					<li value="6d5826" style="background: #6d5826"></li>
					<li value="d3c6a6" style="background: #d3c6a6"></li>
					<li value="c1a173" style="background: #c1a173"></li>
					<li value="5e7c85" style="background: #5e7c85"></li>
					<li value="7c8577" style="background: #7c8577"></li>
					<li value="7c8577" style="background: #7c8577"></li>
					<li value="3e4145" style="background: #3e4145"></li>
					<li value="281f1d" style="background: #281f1d"></li>
					<li value="596032" style="background: #596032"></li>
					<li value="145b7d" style="background: #145b7d"></li>
					<li value="0c212b" style="background: #0c212b"></li>
					<li value="508a88" style="background: #508a88"></li>
					<li value="145b7d" style="background: #145b7d"></li>
					<li value="0c212b" style="background: #0c212b"></li>
					<li value="508a88" style="background: #508a88"></li>
					<li value="fcaf17" style="background: #fcaf17"></li>
					<li value="8552a1" style="background: #8552a1"></li>
					<li value="df9464" style="background: #df9464"></li>
					<li value="65c294" style="background: #65c294"></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="zzc" style="display: none;">
		<div class="new_coupon">
			<p>新增/修改优惠券</p>
			<div class="new_coupon_content">
				<div class="beyond_shop">
					所属门店<span><input type="checkbox" name="checkbox"
						onchange="checkb(this)">全选</span>
				</div>
				<ul class="zzc_coupon_table clearfix">
					<c:forEach items="${StoreInfo }" var="StoreInfo">
						<li ><input name="checkb1" type="checkbox"
							value="${StoreInfo.storeId }">${StoreInfo.storeName }</li>
					</c:forEach>

				</ul>
				<div class="coupon_name">
					<p>
						<span>优惠券名称<input type="text" name="coupon_name"><i  class = "addcolor">*</i></span>
					</p>
					<p>
						<span>优惠券面额<input type="text" name="coupon_price"><i  class = "addcolor">*</i></span><span>发行量<input
							name="coupon_number" type="text"><i  class = "addcolor">*</i></span><span>每人限领<input
							name="coupon_man" type="text"><i  class = "addcolor">*</i></span>
					</p>
				</div>
				<div class="user_ ">
					<p class = "hide">使用条件</p>
					<div class="simple_full hide">
						<span><input type="radio" name="user" value="1">单笔订单满
						<input name="price_sigle" type="text">元使用</span> 
						<span><input type="radio" name="user" value="0">无条件使用</span>
				
					</div>
					<div class="report_time">
						<span>发布时间<input name="release_time" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><i  class = "addcolor">*</i></span> <span>结束时间<input
							name="coupon_stop_time" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /><i  class = "addcolor">*</i></span>
						<div style="margin-top: 10px">
							<span style="position:relative">有效时间<input name="coupon_start_time" type="text" /><em style="position:absolute;right:16px;top:3px">天</em><i  class = "addcolor">*</i></span>
							<span style="margin-right: 4px">选择模板色</span>
							<div name="coupon_colour" onclick="changesty()"
								class="select_color" style="position: relative; left: -300px"></div>
						</div>
					</div>
				</div>
				<div class="report">
					<p>发布方式</p>
					<div class="report_way">
						<span id="1" class="active" onclick="fen(1)">积分兑换</span> <span
							id="2" onclick="fen(2)">门店发放</span>
					</div>
					<p id="jf">
						所需积分<input name="coupon_vantages" type="text">
					</p>
					<div class="zzc_coupon_button">
						<button onclick="Save()">确认</button>
						<button onclick="hiddend()">取消</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
</body>
<script>
	//颜色变量
	var colour;
	var startType =1;
	var addType;
	var couponid;
	function Save() {
		obj = jQuery("input[name='checkb1']");
		check_val = [];
		for (k in obj) {
			if (obj[k].checked)
				check_val.push(obj[k].value);
		}
		var storeType = check_val.toString();
		var couponName = jQuery("input[name='coupon_name']").val();
		var couponPrice = jQuery("input[name='coupon_price']").val();
		var couponNumber = jQuery("input[name='coupon_number']").val();
		var couponMan = jQuery("input[name='coupon_man']").val();
		var priceSigle = jQuery("input[name='price_sigle']").val();
		var releaseTime = jQuery("input[name='release_time']").val();
		var couponStartTime = jQuery("input[name='coupon_start_time']").val();
		var couponStopTime = jQuery("input[name='coupon_stop_time']").val();
		var couponColour = colour;
		var couponType = jQuery("input[name='user']:checked").val();
		var couponVantages = jQuery("input[name='coupon_vantages']").val();
		var couponNames ="单笔消费"+priceSigle;
		//通用时默认单笔消费为零
	

		
		
		if (isEmpty(storeType)) {
			dialog("门店不能为空");
			return;
		}
		if (isEmpty(couponName)) {
			dialog("优惠券名称不能为空");
			return;
		}
		if (isEmpty(couponPrice)) {
			dialog("优惠券面额不能为空");
			return;
		}
		if (isEmpty(couponNumber)) {
			dialog("优惠券发行不能为空");
			return;
		}
		/* if (isEmpty(couponMan)) {
			dialog("优惠券限领不能为空");
			return;
		} */
		if (isEmpty(releaseTime)) {
			dialog("开始时间不能为空");
			return;
		}
		if (isEmpty(couponStartTime)) {
			dialog("有效天数不能为空");
			return;
		}
		if (isEmpty(couponStopTime)) {
			dialog("结束时间不能为空");
			return;
		}
		if (isEmpty(couponVantages)) {
			couponVantages=0;
		}
		
		var checkbo = jQuery("input[name='user'][value='0']").is(':checked');
		if(checkbo==true){
// 			couponNames="通用";
			couponNames=couponName;
			priceSigle=0;
		}else{
			if (isEmpty(priceSigle)) {
				dialog("优惠券单笔满足多少元不能为空");
				return;
			}
		}
		
		
		if(addType==0){
		urltype = baseUrl + "coupons/add";
		couponid=null;
		}else{
			urltype = baseUrl + "coupons/send/update";
		}
		var nowDate = getNowFormatDate();
		var timenum = daysBetween(releaseTime,nowDate);
		var datad =  JSON.stringify({
			"storeType" : storeType,
			"couponNames" : couponNames,
			"couponName" : couponName,
			"couponPrice" : couponPrice,
			"couponNumber" : couponNumber,
			"couponMan" : couponMan,
			"priceSigle" : priceSigle,
			"releaseTime" : releaseTime,
			"couponStartTime" : couponStartTime,
			"couponColour" : couponColour,
			"couponType" : couponType,
			"couponVantages" : couponVantages,
			"startType" : startType,
			"couponStopTime" : couponStopTime,
			"couponId" : couponid
		});
		
		
		var timeDay = daysBetween(couponStopTime,releaseTime);
		if(timeDay <0){
			dialog("开始时间不能小于结束时间");
			return;
		}
		
		if(timenum==0){
			if(confirm('开始时间为今天，是否确认优惠券'))
			{ 
				saveDate(datad);
		 	}
		}
		else if(timenum <0){
			dialog("开始日期不能小于当前日期"+nowDate);
			return;
		}
		else{
			saveDate(datad);
		}
	}
	
	
	jQuery(document).ready(function() {
		jQuery('.report_way span').click(function() {
			jQuery(this).addClass('active').siblings().removeClass('active');
		})

	});

	//选择色块
	jQuery(function() {
		jQuery('.select_color_val li').click(function() {
			var bg = jQuery(this).css('background');
			jQuery('.coupon_card_content').css('background', bg);
			jQuery('.card_logo').css('color', bg)

		})
		jQuery("input[name='user'][value='0']").click();

		jQuery('.zzc2').click(function(e) {
			var tar = e.target;
			if (jQuery(tar).is('.zzc2')) {
				jQuery('.zzc2').hide()
				jQuery(".select_color_val").removeAttr("style");
			}
		})
		jQuery(".zzc").hide();
		var col = jQuery("ul li[value='d93717']").css('background');
		var colour = jQuery("ul li[value='d93717']").val();
		jQuery('.select_color').css('background', col)
		jQuery('.select_color').attr("value",colour);
		colour = jQuery('.select_color_val li').val();
	})

	function changesty() {
		var couponStopTime = jQuery("input[name='coupon_stop_time']").val();
		var couponPrice = jQuery("input[name='coupon_price']").val();
		var priceSigle = jQuery("input[name='price_sigle']").val();
		var checkbo = jQuery("input[name='user'][value='0']").is(':checked');
		if(checkbo==true){
			jQuery("#type").text(jQuery("input[name='coupon_name']").val());
		}else{
			jQuery("#type").text("单笔消费"+priceSigle);
		}
		jQuery("#price").text(couponPrice);
		jQuery("#time").text("到期时间"+couponStopTime);
		jQuery('.zzc2').show();
	}
	//颜色赋予
	jQuery(function() {
		jQuery('.select_color_val li').click(function(e) {
			var col = jQuery(this).css('background');
			colour = jQuery(this).val();
			jQuery('.select_color').css('background', col)
			jQuery('.select_color').attr("value",colour);
		})

	})
	

	function saveDate(data){
		jQuery.ajax({
			type : "post",
			url : urltype,
			data : data,
			dataType : "json",
			contentType : "application/json",
			success : function(e) {
				if(e.code==1){
					window.location.href = baseUrl + "coupons/couponslist";
					dialog("新增成功");
				}
				else{
					dialog("请在优惠券结束，有效期过后修改");
				}
			}
		});
	}
	
	
	function checkb(s) {
// 		jQuery("input[name='checkbox']").click();
		var checkbd = jQuery(s).is(':checked');
		var checkb1 = jQuery("input[name='checkb1']");
		if (checkbd == true) {
			for (var i = 0; i < checkb1.length; i++) {
				if(jQuery(checkb1[i]).is(':checked')==false){
					jQuery(checkb1[i]).click();
				}
			}
		} else { 
			for (var i = 0; i < checkb1.length; i++) {
				if(jQuery(checkb1[i]).is(':checked')==true){
					jQuery(checkb1[i]).click();
				}
			}
		} 

	}
	function fen(z) {
	
		startType = z;
		if (z == 2) {
			jQuery('#jf').hide();
			
		} else {
			jQuery('#jf').show();
			
		}
	}
	
	function changePage() {
		var datas = "pageNo=" + pageNo
		jQuery.ajax({
			type : "post",
			url : baseUrl + "view/coupons/by/page",
			data : datas,
			dataType : "json",
			success : function(e) {
				pageNo = e.msg.pageNo;
				pageSize = e.msg.pageSize;
				totalPage = e.msg.totalPage;
				totalRecord = e.msg.totalRecord;
				jQuery("#tables [name='trs']").empty();
				jQuery.each(e.msg.results, function(n, value) {
					var html = 	'<tr name="trs" id="'+value.couponId+'">'
							+'	<td>'+value.couponName+'</td>'
							+'	<td>'+value.couponPrice+'</td>'
							+'		<td>'+value.couponNames+'</td>'
							+'		<td>'+value.couponNum+'/'+value.couponNumber+'</td>'
							+'		<td>'+value.couponIsUse+'</td>'
							+'		<td>'+value.releaseTime+'</td>'
							+'		<td>'+value.couponStopTime+'</td>'
							+'		<td>'+value.couponStartTime+'</td>';
							
							if(value.status ==1){
								html+='<td>发布中</td> <td>';
							}
							if(value.status ==2){
								html+='<td>未发布</td>> <td>';
							}
							if(value.status ==3){
								html+='<td>已结束</td> <td>';
							}
							
// 							if(value.isType==1){
// 								html+='	<em onclick="update('+value.couponId+',0)"><span class="up" id="'+value.couponId+'">上架</span> </em>';
// 								html+='	<em onclick="update('+value.couponId+',1)"><span class="up1" id="'+value.couponId+'1" style="display:none">下架</span> </em>';
// 							}else{
// 								html+='	<em onclick="update('+value.couponId+',0)"><span class="up" id="'+value.couponId+'" style="display:none">上架</span> </em>';
// 								html+='	<em onclick="update('+value.couponId+',1)"><span class="up1" id="'+value.couponId+'1" >下架</span> </em>';
// 							}
 							html+='<em class="up_preview" onclick="viwe('+value.couponId+',\''+value.couponColour+'\')">预览</em>';
 							if(value.status !=1){
 								html+= '<i  onclick="deleted('+value.couponId+',\''+value.couponStopTime+'\',\''+value.couponStartTime+'\',\''+value.releaseTime+'\')">删除</i><em onclick="updateSave('+value.couponId+',\''+value.storeType+'\','+value.couponMan+','+value.couponColour+','+value.couponType+','+value.startType+','+value.couponVantages+','+value.priceSigle+','+value.couponNumber+','+value.couponStartTime+')"><img src="'+baseUrl+'/images/coupon_write.png"></em></td></tr>'
 							}
 					jQuery("#tables").append(jQuery(html));
				});
			}
		});
	}
	
	
	function viwe(couponId,couponColour){
		var tye=jQuery("tr[ id="+couponId+"]").children('td').eq(2).text();
		var price=jQuery("tr[id="+couponId+"]").children('td').eq(1).text();
		var stopTime=jQuery("tr[id="+couponId+"]").children('td').eq(6).text();
		jQuery("#type").text(tye);
		jQuery("#time").text("到期时间"+stopTime);
		jQuery("#price").text(price);
		jQuery(".clearfix li[value='"+couponColour+"']").click();
		jQuery(".select_color_val").attr("style","display: none");
		jQuery(".zzc2").show();
		
	}
	
	
	function update(id,type){
		var datas = "couponId=" + id+"&isType="+type
		jQuery.ajax({
			type : "post",
			url : baseUrl + "coupon/send/coupons",
			data : datas,
			dataType : "json",
			success : function(e) {
				if(type==0){
					  jQuery('span[id='+id+']').hide();
		    		  jQuery('span[id='+id+'1]').parents('td').find('.up1').show()
					
				}else{
					  jQuery('span[id='+id+'1]').hide();
		    		  jQuery('span[id='+id+']').parents('td').find('.up').show()	  
				}
				
			}
		});
	}
	
	function show(){
		jQuery('.zzc').show();
		addType=0;
	}
	
	
	function deleted(id,stopTime,startTime,releaseTime){
		var datas = "couponId=" + id+"&couponStopTime="+stopTime+"&couponStartTime="+startTime+"&releaseTime="+releaseTime
		jQuery.ajax({
			type : "post",
			url : baseUrl + "action/coupons/delete",
			data : datas,
			dataType : "json",
			success : function(e) {
				if(e.code==1){
					dialog("删除成功");
					var id = e.msg;
					jQuery('tr[id='+id+']').hide();
				}else{
					dialog("请在有效期过后删除");
				}
			}
		});
	}
	
	
	function updateSave(couponId,storeType,couponMan,couponColour,couponType,startType,couponVantages,priceSigle,couponNumber,couponStartTime){
		//标识修改
		addType =2;
		couponid=couponId;
		//门店标识
		var nums = [ ];
		nums=storeType.split(",");
		for (var i=0 ; i< nums.length ; i++)
		{
			if(!jQuery("input[name='checkb1'][value='"+nums[i]+"']").is(':checked')){
				jQuery("input[name='checkb1'][value='"+nums[i]+"']").click();
			}
		}
		//每人限领
		jQuery("input[name='coupon_man']").val(couponMan);
		//颜色
		jQuery(".clearfix li[value='"+couponColour+"']").click();
		//0:通用 1:单笔订
		jQuery("input[name='user'][value='"+couponType+"']").click();
		//发布方式（1积分，2门店）
		jQuery(".report_way span[id='"+startType+"']").click();
		//兑换所需积分
		jQuery("input[name='coupon_vantages']").val(couponVantages);
		jQuery("input[name='price_sigle']").val(priceSigle);
		jQuery("input[name='coupon_name']").val(jQuery("tr[id='"+couponId+"']").children('td').eq(0).text());
		jQuery("input[name='coupon_price']").val(jQuery("tr[id='"+couponId+"']").children('td').eq(1).text());
		jQuery("input[name='release_time']").val(jQuery("tr[id='"+couponId+"']").children('td').eq(5).text());
		jQuery("input[name='coupon_stop_time']").val(jQuery("tr[id='"+couponId+"']").children('td').eq(6).text());
		jQuery("input[name='coupon_start_time']").val(jQuery("tr[id='"+couponId+"']").children('td').eq(7).text());
		jQuery("input[name='coupon_number']").val(couponNumber);
		jQuery("input[name='coupon_start_time']").val(couponStartTime);
		
		
		jQuery('.zzc').show();
		
	}
	
	
	function hiddend(){
		jQuery('.zzc').hide();
		jQuery("input[name='coupon_man']").val("");
		jQuery("input[name='coupon_vantages']").val("");
		jQuery("input[name='price_sigle']").val("");
		jQuery("input[name='coupon_name']").val("");
		jQuery("input[name='coupon_price']").val("");
		jQuery("input[name='release_time']").val("");
		jQuery("input[name='coupon_stop_time']").val("");
		jQuery("input[name='coupon_start_time']").val("");
		jQuery("input[name='coupon_number']").val("");
		jQuery("input[name='coupon_start_time']").val("");
		var checkb1 = jQuery("input[name='checkb1']");
		var checkb2 = jQuery("input[name='checkbox']").is(":checked");
		for (var i = 0; i < checkb1.length; i++) {
			if(jQuery(checkb1[i]).is(':checked')==true){
				jQuery(checkb1[i]).click();
			}
		}
		if(checkb2==true){
			jQuery("input[name='checkbox']").click();
		}
		
	}
	
	function getNowFormatDate() {
	    var date = new Date();
	    var seperator1 = "-";
	    var seperator2 = ":";
	    var year = date.getFullYear();
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    if (month >= 1 && month <= 9) {
	        month = "0" + month;
	    }
	    if (strDate >= 0 && strDate <= 9) {
	        strDate = "0" + strDate;
	    }
	    var currentdate = year + seperator1 + month + seperator1 + strDate;
	            
	    return currentdate;
	}
	
	
	function daysBetween(DateOne,DateTwo)  
	{   
	    var OneMonth = DateOne.substring(5,DateOne.lastIndexOf ('-'));  
	    var OneDay = DateOne.substring(DateOne.length,DateOne.lastIndexOf ('-')+1);  
	    var OneYear = DateOne.substring(0,DateOne.indexOf ('-'));  
	  
	    var TwoMonth = DateTwo.substring(5,DateTwo.lastIndexOf ('-'));  
	    var TwoDay = DateTwo.substring(DateTwo.length,DateTwo.lastIndexOf ('-')+1);  
	    var TwoYear = DateTwo.substring(0,DateTwo.indexOf ('-'));  
	  
	    var cha=((Date.parse(OneMonth+'/'+OneDay+'/'+OneYear)- Date.parse(TwoMonth+'/'+TwoDay+'/'+TwoYear))/86400000);   
	    return cha;  
	}  
</script>

</html>