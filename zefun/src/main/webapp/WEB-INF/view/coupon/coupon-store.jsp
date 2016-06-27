<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="<%=basePath%>css/coupon.css"
	type="text/css" />
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
<!-- 						<button onclick="jQuery('.zzc').show()">新建优惠券</button> -->
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
									<td>操作</td>
								</tr>

								<c:forEach  var="coupon" items="${page.results}">
									<tr name="trs" id="${coupon.couponId}">
										<td>${coupon.couponName}</td>
										<td>${coupon.couponPrice}</td>
										<td>${coupon.couponNames}</td>
										<td>${coupon.couponNum}/${coupon.couponNumber}</td>
										<td>${coupon.couponIsUse}</td>
										<td>${coupon.releaseTime}</td>
										<td>${coupon.couponStopTime}</td>
										<td>${coupon.couponStartTime}天</td>
										<td>
										<em class="up_preview" onclick="viwe(${coupon.couponId},'${coupon.couponColour}')">赠送</em>
										<em class="up_preview" onclick="viwe(${coupon.couponId},'${coupon.couponColour}')">预览</em></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<%@ include file="/template/page.jsp"%>
					</div>
				</div>


			</div>
		</div>
	</div>
	<div class="zzc2">
		<div class="coupon_card">
			<div class="coupon_card_content">
				<div class="card_logo">券</div>
				<div class="coupon_card_price">
					¥<span id="price">500</span><em id="type">通用</em>
				</div>
				<p id="time">到期时间2016/12/12</p>
			</div>
			<div class="select_color_val" dis>
				<ul class="clearfix" style="display: none">
					<li value="1" style="background: #d93717"></li>
					<li value="2" style="background: #f8b65b"></li>
					<li value="3" style="background: #585eaa"></li>
					<li value="4" style="background: #5c7a29"></li>
					<li value="5" style="background: #5f3c23"></li>
					<li value="6" style="background: #2b4490"></li>
					<li value="7" style="background: #b36d41"></li>
					<li value="8" style="background: #a7573b"></li>
					<li value="9" style="background: #f05b72"></li>
					<li value="10" style="background: #817936"></li>
					<li value="11" style="background: #2e3a1f"></li>
					<li value="12" style="background: #46485f"></li>
					<li value="13" style="background: #543044"></li>
					<li value="14" style="background: #401c44"></li>
					<li value="15" style="background: #225a1f"></li>
					<li value="16" style="background: #b69968"></li>
					<li value="17" style="background: #6d5826"></li>
					<li value="18" style="background: #d3c6a6"></li>
					<li value="19" style="background: #c1a173"></li>
					<li value="20" style="background: #5e7c85"></li>
					<li value="21" style="background: #7c8577"></li>
					<li value="22" style="background: #6c4c49"></li>
					<li value="23" style="background: #3e4145"></li>
					<li value="24" style="background: #281f1d"></li>
					<li value="25" style="background: #596032"></li>
					<li value="26" style="background: #145b7d"></li>
					<li value="27" style="background: #0c212b"></li>
					<li value="28" style="background: #508a88"></li>
					<li value="29" style="background: #145b7d"></li>
					<li value="30" style="background: #0c212b"></li>
					<li value="31" style="background: #508a88"></li>
					<li value="32" style="background: #fcaf17"></li>
					<li value="33" style="background: #8552a1"></li>
					<li value="34" style="background: #df9464"></li>
					<li value="35" style="background: #65c294"></li>
				</ul>
			</div>
		</div>
	</div>
	<div class="zzc">
		<div class="new_coupon">
			<p>新建优惠券</p>
			<div class="new_coupon_content">
				<div class="beyond_shop">
					所属门店<span><input type="checkbox" name="checkbox"
						onchange="checkb(this)">全选</span>
				</div>
				<ul class="zzc_coupon_table clearfix">
					<c:forEach items="${StoreInfo }" var="StoreInfo">
								<li><input name="checkb1" type="checkbox"
							value="${StoreInfo.storeId }">${StoreInfo.storeName }</li>
					</c:forEach>

				</ul>
				<div class="coupon_name">
					<p>
						<span>优惠券名称<input type="text" name="coupon_name"></span>
					</p>
					<p>
						<span>优惠券面额<input type="text" name="coupon_price"></span><span>发行量<input
							name="coupon_number" type="text"></span><span>每人限领<input
							name="coupon_man" type="text"></span>
					</p>
				</div>
				<div class="user_">
					<p>使用条件</p>
					<div class="simple_full">
						<span><input type="radio" name="user" value="1">单笔订单满<input
							name="price_sigle" type="text">元使用</span> <span><input
							type="radio" name="user" value="0">无条件使用</span>
					</div>
					<div class="report_time">
						<span>发布时间<input name="release_time" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></span> <span>结束时间<input
							name="coupon_stop_time" type="text"
							onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" /></span>
						<div style="margin-top: 20px">
							<span>有效时间<input name="coupon_start_time" type="text" /></span>
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
						<button onclick="jQuery('.zzc').hide()">取消</button>
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
		//通用时默认单笔消费为零
		if (startType == 2) {
			couponVantages = 0;
		}
		jQuery.ajax({
			type : "post",
			url : baseUrl + "coupons/add",
			data : JSON.stringify({
				"storeType" : storeType,
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
				"couponStopTime" : couponStopTime
			}),
			dataType : "json",
			contentType : "application/json",
			success : function(e) {
				window.location.href = baseUrl + "coupons/couponslist";
				dialog("新增成功");
			}
		});
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
// 				jQuery('.zzc').show();
			}
		})
		jQuery(".zzc").hide();
		var col = jQuery("ul li[value='1']").css('background');
		var colour = jQuery("ul li[value='1']").val();
		jQuery('.select_color').css('background', col)
		jQuery('.select_color').attr("value",colour);
	})

	function changesty() {
	
		var couponStopTime = jQuery("input[name='coupon_stop_time']").val();
		var couponPrice = jQuery("input[name='coupon_price']").val();
		var priceSigle = jQuery("input[name='price_sigle']").val();
		var checkbo = jQuery("input[name='user'][value='0']").is(':checked');
		if(checkbo==true){
			jQuery("#type").text("通用");
		}else{
			jQuery("#type").text("单笔消费"+priceSigle);
		}
		jQuery("#price").text(couponPrice);
		jQuery("#time").text("到期时间"+couponStopTime);
		jQuery('.zzc2').show();
		jQuery('.zzc').hide();
		
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

	function checkb(s) {
		var checkbd = jQuery(s).is(':checked');
		if (checkbd == true) {
			jQuery("input[name='checkb1']").click();
		} else {
			jQuery("input[name='checkb1']").removeAttr("checked");
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
							+'		<td>'+value.releaseTime+ '</td>'
							+'		<td>'+value.couponStopTime+ '</td>'
							+'		<td>'+value.couponStartTime+'</td>' 
							+'		<td><em class="up_preview" onclick="viwe('+value.couponId+',\''+value.couponColour+'\')">预览</em></td>'
							+'		</tr>'
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
		jQuery(".zzc2").show();
	}
</script>

</html>