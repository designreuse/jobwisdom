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

			<!--预约模态框-->
			<div class="modal hide in" id="yuyue" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content yuyue-modal">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">预约项目</h4>
						</div>
						<!-- 部门 -->
						<div class="modal-body">
							<div class="yuyue-tab">
								<ul id="appointmentDeptUL">
									<c:forEach items="${deptInfoList }" var="deptInfo" varStatus="index">
										<c:choose>
											<c:when test="${ index.index == 0}">
												<li class="current" value="${deptInfo.deptId }" onclick="findCanAppointEmployeeByDept(this)">${deptInfo.deptName }</li>
											</c:when>
											<c:otherwise>
												<li value="${deptInfo.deptId }" onclick="findCanAppointEmployeeByDept(this)">${deptInfo.deptName }</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</ul>
							</div>
							<!--人员-->
							<div class="yuyue-work">
								<div class="m-arr-left fl">
									<span class="iconfont icon-qianjinyibu"></span>
								</div>
								<div id="appointmentEmployeeDiv" class="fl work-name">
									<ul>
										<!-- 滚动员工列表 -->
									</ul>
								</div>
								<div class="m-arr-right fr">
									<span class="iconfont icon-houtuiyibu"></span>
								</div>
							</div>
							<div>
								<span>选择项目</span>
								<div class="select-target">
									<div class="select-style-wrap">

										<!--选择类型的系列-->
										<div class="selected-child-select">
											<ul id="projectCategoryUl">
												<c:forEach items="${projectCategories }" var="projectCategorie">
													<li onclick="jQuery(this).siblings().removeClass('active');jQuery(this).addClass('active');globalProjectId=jQuery(this).val()" value="${projectCategorie.categoryId }" class="selected-item" style="cursor: pointer">${projectCategorie.categoryName }</li>
												</c:forEach>
												<!-- 项目系列列表处 -->
											</ul>
										</div>

										<div class="all-kind-wrap">
											<ul id="projectUl">
												<!-- 项目列表处 -->
											</ul>
										</div>
										<!--选择具体的项目-->

									</div>

								</div>
								<p style="height: 62px; line-height: 62px;">
									已选择员工： <span id="selectEmployee" class="yuyue-co"></span> 
									已选择项目： <span id="selectProject" class="yuyue-co"></span>
									预约时间： <span id="selectTime" class="yuyue-co"></span>
									<!-- 调用老王查询会员 -->
	                           		<div class="p-part-first" id="partDIV" name="memberTR">
				                        <label class="w60 ml10 kaidan-label" for="">客户:</label>
				                        <div style="display: inline-block;" name= "seekTD">
				                           <input id="customerPhone" type="text" class="w185 searchinpput" name = "phoneNumber" placeholder="手机号"/>
				                           <ul class="fuzzysearch" >
			
					                       </ul>
				                           <span class="iconfont icon-sousuo ml-30 mt5" name = "seekName"></span>
				                        </div>
				                        <div name="resultTD" style="display: none;">
			                                <span data-toggle="modal" data-target="#member-data" class="can-click"></span>
			                                <input id="memberId" type="hidden" name = "memberId">
			                                <span class="ml10" name = "balance"></span>
			                                <span class="iconsweets-magnifying ml10 mt-5" name="breakName"></span>
			                            </div>
				                    </div>
				                    
				                    <span id="customerNameSpan">姓名：</span> <input id="customerName" type="text" placeholder="姓名" />
								</p>
								<div>
									<!-- <span>客户姓名：</span> <input id="customerName" type="text" /> 
									<span>手机号：</span> <input id="customerPhone" type="text" /> -->
	                           		
								</div>
							</div>
						</div>
						<!--modal-body-->
						<div class="modal-footer">
							<div class="fr f-btn">
								<button class="btn" data-dismiss="modal">取消</button>
								<button class="btn" onclick="addAppointProject()">确定预约</button>
							</div>
						</div>
					</div>
					<!--modal-content-->
				</div>
				<!--modal-dialog-->
			</div>
			<!--modal-->

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
          <div class="search_vip">
              搜索会员：<input type="text" placeholder="客户姓名/手机号/卡号">
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
</body>

<%-- <%@ include file="/template/memberData.jsp" %> 已经把memberData.js全部复制到appointList.js中--%>
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