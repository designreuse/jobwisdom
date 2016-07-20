<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="<%=basePath %>css/appoint.css" type="text/css" />
<jsp:useBean id="now" class="java.util.Date" />
<style>
	.p-part-first {
		width:350px;
		float:inherit;
		display:inline-block;
	}
	.alertWrap .toast{width:400px;}
	.contentinner {
		padding-bottom:200px;
	}
	.yuyue-yg li span:nth-of-type(2) {
			color:#666;
	}
	.status-wrap-person ul{width:100%；}
	
	.status-time li{height:188px!important}
	.contentinner{background:white!important}
</style>
<script>
jQuery(function(){
    var now_=0;	 
 //向右走
 jQuery(document).on('click','.order_right',function(){
   var count=jQuery('.roll_ul_content li').size();
    if(now_<=count-6){
	    now_+=3;
       jQuery(this).parent('').find('.roll_ul_content ul').stop(true,true).animate({
	       left:-114*now_
	   
	       }) 
		  }
	  });
 //向左走
 
 jQuery(document).on('click','.order_left',function(){
    var count=jQuery('.roll_ul_content li').size();
    if(now_>=1){
	    now_-=3;
        jQuery(this).parent('').find('.roll_ul_content ul').stop(true,true).animate({
	     left:-114*now_
	   
	     }) 
	  }	
   		
 });
});	


//鼠标移动员工上,点击
jQuery(function(){
jQuery(document).on('mouseover','.roll_ul_content li',function(){
 jQuery(this).addClass('change').siblings().removeClass('change')

})

jQuery(document).on('mouseout','.roll_ul_content li',function(){
 jQuery(this).removeClass('change')

})

jQuery(document).on('click','.roll_ul_content li',function(){
jQuery(this).addClass('change1').siblings().removeClass('change1')
})
})
</script>
<body>
	
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">

		<!--left-panel start-->
		<%@ include file="/menu.jsp"%>
		<!--left-panel end-->

		<!--RIGHT PANEL开始 -->
		<div class="rightpanel" style="margin-left: 200px;">
			<%@ include file="/top.jsp"%>
			<!-- 页面代码 -->
			<div class="maincontent">
				<div class="contentinner">
					<div class="yuyue">
						<div class="more-toolbar">
							<div class="table-toolbar">
								<table>
									<tbody>
										<tr>
											<td class="fl"><span class="font-size-16 btn-color">预约管理</span>
											</td>
											<%-- <fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="MM-dd" /> --%>
											<td class="fr">日期 
												<!-- <input id="startTime" type="text" name="date" placeholder="起始时间" 
													daysoffset="0" class="datetimepicker timepicker width100"> 
												<span>-</span>
												<input id="endTime" type="text" name="date" placeholder="截止时间" 
													daysoffset="30" class="datetimepicker timepicker width100"> -->
												<input id="editOfSignOutTime" type="text" style="width:100px " onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
												<button class="btn" onclick="findMemberAppointByDate('query')">查询</button>
											</td>

										</tr>
									</tbody>
								</table>

							</div>
							<!--table-toolbar-->
							<div class="clearfix"></div>
						</div>
						<!--日期-->
						<div class="yuyue-data">
							<div class="arr-left fl">
								<span class="iconfont icon-qianjinyibu"></span>
							</div>
							<div class="yuyue-main fl ">
								<ul id="rollDateUL">
									<c:forEach items="${dateMap }" var="date">
										<%-- <c:choose> --%>
											<%-- <c:when test="${date.key == now }">
												<li class="current">
											</c:when>
											<c:otherwise>
												<li>
											</c:otherwise> --%>
											<li style="cursor:pointer" onclick="findMemberAppointByDate(this)">
												<span>${date.key }</span>
												<p>${date.value }</p>
											</li>
										<%-- </c:choose> --%>
									</c:forEach>
								</ul>
							</div>
							<div class="arr-right fr">
								<span class="iconfont icon-houtuiyibu"></span>
							</div>
						</div>
						<div id="memberAppointmentSumDiv" class="more-toolbar yuyue-detail">
							<span class="font-size-16 btn-color">本日被预约的员工：</span> <span class="cred">9人</span> 
							<span class="font-size-16 btn-color">预约客户：</span> <span class="cred">9人</span> 
							<span class="font-size-16 btn-color">预约项目总价：</span> <span class="cred">1555元</span> 
							<span class="font-size-16 btn-color">已到店人数：</span> <span class="cred">9人</span> 
							<!-- <span class="font-size-16 btn-color">到店已消费：</span> <span class="cred">15555元</span> -->
							<span class="font-size-16 btn-color">已取消人数：</span> <span class="cred">9人</span>
							<span class="font-size-16 btn-color">取消比例：</span> <span class="cred">9人</span>
						</div>
						<!--员工状态-->
						<div class="yuyue-status clearfix">
							<div class="status-title">
								<div class="status-title-l">时间段</div>
								<div class="status-title-r">被预约的员工</div>
							</div>
							<div class="status-main clearfix">
								<div class="status-time fl">
									<ul>
										<li class="current" data-target="time1">上<br />午
										</li>
										<li data-target="time2">下<br />午
										</li>
										<li data-target="time3">晚<br />上
										</li>
										<!-- <li data-target="time4">夜<br />间
										</li> -->
									</ul>
								</div>
								<!--上午-->
								<div class="status-wrap fl clearfix" id="time1">
									<div class="status-wrap-time fl">
										<ul id="forenoonPeriod">
											<li>06:00</li>
											<li>06:30</li>
											<li>07:00</li>
											<li>07:30</li>
											<li>08:00</li>
											<li>08:30</li>
											<li>09:00</li>
											<li>09:30</li>
											<li>10:00</li>
											<li>10:30</li>
											<li>11:00</li>
											<li>11:30</li>
										</ul>
									</div>
									<div class="status-wrap-person fl">
										<ul>
											<li id="06:00OfTheClock"></li>
											<li id="06:30OfTheClock"></li>
											<li id="07:00OfTheClock"></li>
											<li id="07:30OfTheClock"></li>
											<li id="08:00OfTheClock"></li>
											<li id="08:30OfTheClock"></li>
											<li id="09:00OfTheClock"></li>
											<li id="09:30OfTheClock"></li>
											<li id="10:00OfTheClock"></li>
											<li id="10:30OfTheClock"></li>
											<li id="11:00OfTheClock"></li>
											<li id="11:30OfTheClock"></li>
										</ul>
									</div>
								</div>
								<!--下午-->
								<div class="status-wrap fl hide clearfix" id="time2">
									<div class="status-wrap-time fl">
										<ul id="afternoonPeriod">
											<li>12:00</li>
											<li>12:30</li>
											<li>13:00</li>
											<li>13:30</li>
											<li>14:00</li>
											<li>14:30</li>
											<li>15:00</li>
											<li>15:30</li>
											<li>16:00</li>
											<li>16:30</li>
											<li>17:00</li>
											<li>17:30</li>
										</ul>
									</div>
									<div class="status-wrap-person fl">
										<ul>
											<li id="12:00OfTheClock"></li>
											<li id="12:30OfTheClock"></li>
											<li id="13:00OfTheClock"></li>
											<li id="13:30OfTheClock"></li>
											<li id="14:00OfTheClock"></li>
											<li id="14:30OfTheClock"></li>
											<li id="15:00OfTheClock"></li>
											<li id="15:30OfTheClock"></li>
											<li id="16:00OfTheClock"></li>
											<li id="16:30OfTheClock"></li>
											<li id="17:00OfTheClock"></li>
											<li id="17:30OfTheClock"></li>
										</ul>
									</div>
								</div>
								<!--晚上-->
								<div class="status-wrap fl hide clearfix" id="time3">
									<div class="status-wrap-time fl">
										<ul id="eveningPeriod">
											<li>18:00</li>
											<li>18:30</li>
											<li>19:00</li>
											<li>19:30</li>
											<li>20:00</li>
											<li>20:30</li>
											<li>21:00</li>
											<li>21:30</li>
											<li>22:00</li>
											<li>22:30</li>
											<li>23:00</li>
											<li>23:30</li>
										</ul>
									</div>
									<div class="status-wrap-person fl">
										<ul>
											<li id="18:00OfTheClock"></li>
											<li id="18:30OfTheClock"></li>
											<li id="19:00OfTheClock"></li>
											<li id="19:30OfTheClock"></li>
											<li id="20:00OfTheClock"></li>
											<li id="20:30OfTheClock"></li>
											<li id="21:00OfTheClock"></li>
											<li id="21:30OfTheClock"></li>
											<li id="22:00OfTheClock"></li>
											<li id="22:30OfTheClock"></li>
											<li id="23:00OfTheClock"></li>
											<li id="23:30OfTheClock"></li>
										</ul>
									</div>
								</div>
								<!-- 夜间 -->
								<div class="status-wrap fl hide clearfix" id="time4">
									<div class="status-wrap-time fl">
										<ul id="nightPeriod">
											<li>00:00</li>
											<li>00:30</li>
											<li>01:00</li>
											<li>01:30</li>
											<li>02:00</li>
											<li>02:30</li>
											<li>03:00</li>
											<li>03:30</li>
											<li>04:00</li>
											<li>04:30</li>
											<li>05:00</li>
											<li>05:30</li>
										</ul>
									</div>
									<div class="status-wrap-person fl">
										<ul>
											<li id="00:00OfTheClock"></li>
											<li id="00:30OfTheClock"></li>
											<li id="01:00OfTheClock"></li>
											<li id="01:30OfTheClock"></li>
											<li id="02:00OfTheClock"></li>
											<li id="02:30OfTheClock"></li>
											<li id="03:00OfTheClock"></li>
											<li id="03:30OfTheClock"></li>
											<li id="04:00OfTheClock"></li>
											<li id="04:30OfTheClock"></li>
											<li id="05:00OfTheClock"></li>
											<li id="05:30OfTheClock"></li>
										</ul>
									</div>
								</div>
								
							</div>
						</div>
					</div>
				</div>
			</div>

			<script>
				//点击上午,下午,晚上,夜间之间的切换
				jQuery(function() {
					jQuery(".status-time li").on(
							"click",
							function() {
								jQuery(this).addClass("current").siblings().removeClass("current");
								jQuery(".status-wrap").addClass("hide");
								jQuery("#" + jQuery(this).attr("data-target")).removeClass("hide");
								//globalPeriod全局变量赋值
								globalPeriod = jQuery(this).attr("data-target");
							});
				});

				/*日期滑动*/
				jQuery(function() {
					var sx = 0;
					var lilength = jQuery(".yuyue-main li").length;
					jQuery(".arr-left").on("click", function() {
						sx = sx - 7;
						if (sx < 0) {
							sx = 0
						}
						jQuery(".yuyue-main ul").stop().animate({
							left : -60 * sx
						}, 500)
					});
					jQuery(".arr-right").on("click", function() {
						sx = sx + 7;
						if (sx > lilength - 16) {
							sx = lilength - 16;
						}
						jQuery(".yuyue-main ul").stop().animate({
							left : -60 * sx
						}, 500)
					});
				});
				
				//新增预约模态框员工头像滚动(全局滚动员工长度)
				var globalRollEmployeeLength;
				jQuery(function() {
					var sx1 = 0;
					var worklength = jQuery(".work-name li").length;
					jQuery(".m-arr-left").on("click", function() {
						sx1 --;
						if (sx1 < 0) {
							sx1 = 0;
						}
						jQuery(".work-name ul").stop().animate({
							left : -92 * sx1 
						}, 500)
					})
					jQuery(".m-arr-right").on("click", function() {
						sx1 ++;
						if (sx1 > globalRollEmployeeLength - 7) {
							sx1 = globalRollEmployeeLength - 7;
						}
						jQuery(".work-name ul").stop().animate({
							left : -92 * sx1 
						}, 500)
					})
				});
				
				jQuery(function(){
			        jQuery(".yuyue-p").hover(function(){
			            jQuery(this).children(".yuyue-yg-s").removeClass("hide");

			        },function(){
			            jQuery(this).children(".yuyue-yg-s").addClass("hide");

			        })

			/*        jQuery('p').hover(function(){
			            alert('mouseenter function is running !');
			        },function(){
			            alert('mouseleaver function is running !');
			        });*/
			    })
			</script>


		</div>
		<!--<button class="btn" data-target="#jietu" data-toggle="modal">截图</button>-->
		<!--RIGHT PANEL结束 -->

		<div class="clearfix"></div>

		<div id="star"></div>
		<!-- <div class="left-show-btn">
        <span class="iconfont icon-quanbu110"></span>
    </div>-->
		<a href="" class="showmenu"></a> <i class="iconfont icon-fuzhi"></i>

		<!--确认框-->
		<!--提示-->
		<div class="modal hide" id="confirm" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content confirm">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">提示</h4>
					</div>

					<div class="modal-body confirm-body">需要删除"欧莱雅烫发"项目吗？</div>
					<!--modal-body-->

					<div class="modal-footer">
						<a id="quxiao" class="btn cancel-btn modal-cancel"
							data-dismiss="modal" href="#">取消</a> <a id="queren"
							class="btn btn-primary save-btn modal-confirm"
							data-dismiss="modal" href="#">保存</a>
					</div>
				</div>
			</div>
		</div>

		<!--提示框-->
		<div class="alertWrap hide">
			<div class="toast">
				<img src="<%=basePath%>images/jiazai.gif" alt="" />
			</div>
		</div>


		<!--截图-->
		<!--添加新品牌模态框-->
		<div class="modal hide" id="jietu" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content jietu ">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">截图</h4>
					</div>
					<div class="modal-body nopadding">
						<div id="tabs" style="border-bottom: 1px solid #fff">
							<ul>
								<li><a href="#tabs-1">截图</a></li>
								<li><a href="#tabs-2">图库</a></li>
							</ul>
							<!-- 截图-->
							<div id="tabs-1" class="nopadding">
								<div class="crop-container">
									<img src="<%=basePath%>images/1.jpg" id="cropbox" />
								</div>

								<div class="jietu-control">
									<input type="file" class="inputfile" accept="image/*" />
									<div class="btn dblock">选择文件</div>

									<div class="btn dblock mt10">保存</div>

									<div class="btn dblock mt10 zoomin">放大</div>
									<div class="btn dblock mt10 zoomout">缩小</div>
								</div>
								<div class="clearfix"></div>
							</div>
							<!-- 截图 -->

							<!-- 图库 -->
							<div id="tabs-2" class="nopadding">
								<ul class="tuku-list">
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>
									<li class="tuku-item"><img
										src="<%=basePath%>images/shop-img.png" alt="图库图片" /></li>

								</ul>
								<div class="clearfix"></div>
							</div>
							<!-- 图库结束 -->
						</div>
					</div>
					<!--modal body-->
				</div>
			</div>
		</div>

      </div>
	</div>
	</div>
<div class="zzc" style="display: none;">
  <div class="order_item">
     <p>预约项目</p>
     <div class="order_item_content">
          <div class="search_vip" name="memberTR" selectType="2"> 搜索会员：
                   <input type="text"name="phoneNumber" class = "input_content" placeholder="会员手机号">
			       <span class="iconfont icon-sousuo ml-30 mt5" name="seekName" style="display: none;"></span>
				   <div class="show_search" name="memberListDIV"
						style="display: none;">
						<p>
							以<i name="conditionValue"></i>为条件显示到<i name="showList"></i>位顾客
							<em><input type="checkbox" name="enterpriseCheck"
								onchange="changeAllEnterprise(this)">全店搜索<span>?</span>
							</em>
						</p>
						<div class="common_close" onclick="cancleMemberSelect(this)">
							<img src="<%=basePath%>images/emploee_3.png">
						</div>
						<div style="height: 400px; overflow: overlay;"
							name="memberoverDIV"></div>
					</div>
          </div>
          <div class="card-main1 clearfix hide" name = "memberTR" style="height: 70px;">
               <div class="common_table">   
				  	   <table style="width: 870px;">
					     <tr>
						   <td rowspan="2"><img src="" name = "memberImg"></td>
						   <td>手机号</td>
						   <td>姓名</td>
						   <td>性别</td>
						   <td>开卡门店</td>
						   <td>余额</td>
						   <td>礼金</td>
						   <td>欠款</td>
						   <td>会员卡</td>
						   <td rowspan="2"><button onclick="againSearch(this)">重新搜索</button></td>
						 </tr>
						 <tr>
						   <td name = "memberPhoneSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
						   <td name = "memberNameSpan" data-toggle="modal"  data-target="#member-data" onclick="showMemberModal(this)"></td>
						   <td name = "memberSexSpan">男</td>
						   <td name = "memberStoreName">华南美联店</td>
						   <td style="color:#eb4749" name = "memberBalanceGiftmoneyAmountSpan"></td>
						   <td style="color:#eb4749" name = "memberBalanceIntegralSpan"></td>
						   <td style="color:#eb4749" name = "needRefund"></td>
						   <td ><span name = "subAccountNum"></span>张</td>
						 </tr>
						 <input type="hidden" name = "memberId">
					   </table>
				    </div>
               </div>
		  
		  <div class="order_item_apartment clearfix">
		     <ul>
		       <c:forEach items="${deptInfoList }" var="deptInfo">
			   <li onclick="findCanAppointEmployeeByDept(this)" value="${deptInfo.deptId }">${deptInfo.deptName }</li>
			   </c:forEach>
			 </ul>
			 <script>jQuery(".order_item_apartment.clearfix").children("ul").find("li").eq(0).addClass("active");</script>
		     <div class="order_item_apartment_right_content">
			   <div class="order_item_apartment_right">
			       <div class="select_emploee">
				      <p>选择员工</p>
					  <div class="roll">
				         <span class="order_left"><img src="<%=basePath%>images/order_left.png"></span>
                       <div class="roll_ul_content">				        
						<ul class="clearfix" id="newAppoint">
						    <%-- <li>
							  <img src="<%=basePath%>images/roll_img.png">
							   <div>1101  卡拉曼达</div>
							</li>  --%>
						 </ul>
						</div> 
					    <span class="order_right"><img src="<%=basePath%>images/order_right.png"></span>
					  </div>
				   </div>
				   
				   <div class="select_series">
			          <p>选择系列</p>
					  <ul class="clearfix" id="deptCategory">
					    <li class="active">洗剪吹系列</li>
					    <li>洗剪吹系列</li>
					    <li>洗剪吹系列</li>
					    <li>洗剪吹系列</li>
					    <li>洗剪吹系列</li>
					    <li>洗剪吹系列</li>
						 <li>洗剪吹系列</li>
					    <li>洗剪吹系列</li>
					  </ul>
			       </div>
				   
				   <div class="selected_emploee">
				    <span>已选择员工：<em id="employeeCode"></em></span>
				    <span>已选择系列：<em id="categoryName"></em></span>
					 <span style="float:right">预约时间：<em id="selectTime">16-15 23：50</em></span>
				   </div>
			   </div>
			 </div>		  
		  </div> 
        <div class="order_item_button">
		   <button onclick="addAppointProject()">确认</button>
		   <button onclick="jQuery('.zzc').hide();">取消</button>
		</div>
            		  
     </div>
   </div>
</div>
<%@ include file="/template/memberData.jsp"%>
</body>

<script type="text/javascript" src="<%=basePath %>js/cashier/appointList.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<script>
var deptInfoCategory = eval('('+'${deptInfoCategorys}'+')');
function selectDeptInfoCategory(deptId){
	jQuery("#deptCategory").empty();
	for (var i = 0; i < deptInfoCategory.length; i++) {
		if (deptId == deptInfoCategory[i].deptId){
			for (var j = 0; j < deptInfoCategory[i].projectCategoryDtoList.length; j++) {
				var category = deptInfoCategory[i].projectCategoryDtoList[j];
				var categoryId = category.categoryId;
				var categoryName = category.categoryName;
				var html = '<li value='+categoryId+' onclick="globalProjectId=jQuery(this).val();'+
				'jQuery(this).addClass(\'active\').siblings().removeClass(\'active\');jQuery(\'#categoryName\').text(jQuery(this).text())">'+categoryName+'</li>';
				jQuery("#deptCategory").append(jQuery(html));
			}
		}
	}
}
selectDeptInfoCategory(deptInfoCategory[0].deptId);
</script>
</html>