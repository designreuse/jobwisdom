<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="<%=basePath %>css/vip_data.css" type="text/css" />
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<style>
.zzc{position: fixed;
    top: 0px;
    height: 1090px;
    left: 0px;
    width: 100%;
    z-index: 10000;
    background: rgba(102, 108, 121, 0.8);
	}
.input_file_content{position:relative;padding:20px}
.input_file_{position:relative;left:50px;width:400px;height:250px;border-radius:12px;background:white;overflow:hidden;margin:100px auto}
.input_file_>p{background:#323b4e;font-size:14px;color:white;height:40px;line-height:40px;text-align:center}

input[type='file']{cursor:pointer;top:-40px;left:80px;width:200px;height:50px;background:Red;position:relative;opacity:0}
.file_{position:relative;width:200px;height:50px;border-radius:12px;background:#ccc;color:white;font-size:16px;text-align:center;line-height:50px;left:80px;top:10px;}
.input_file_content>p{text-align:center;font-size:14px}
.input_button{text-align:center;margin-top:10px}
.input_button button{width:120px;height:26px;border:none;color:whiite;font-size:14px;text-align:center;color:white;line-height:24px;margin:0 30px;background:#59688a;border-radius:12px}
.input_button button:hover{background:#4c5a78}
</style>
<body>
	<script>
		jQuery(function() {
			jQuery('.content_right>p>span').click(function() {
				if (jQuery('.data_select').css('display') == 'none') {
					jQuery('.data_select').slideDown();
					jQuery('.triangle-down').css('transform', 'rotate(180deg)')
				} else {
					jQuery('.data_select').slideUp();
					jQuery('.triangle-down').css('transform', 'rotate(0deg)')
				}
			})
		})
	</script>
	<div class="zzc" style="display: none;">
	  <div class="input_file_">
	     <p>上传文件</p>
	     <div class="input_file_content">
	      <div class="file_">	   
		   	上传文件
		   </div>  
		 <input type="file" id="file" onchange="jQuery(this).next().text('文件读取成功，请点击上传');">
		 <a href="http://7xss26.com1.z0.glb.clouddn.com/jobwisdom/TM/memberTM.xlsx" target="_blank" style="position: relative;left: 89px;top: -23px;">下载模板</a>
		 <p id="p"></p>
	   </div>
	    <div class="input_button">
		  <button onclick="upload()">确定</button>
		  <button onclick="jQuery('.zzc').hide();">取消</button>
		</div>
	  </div>
	</div>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right clearfix">
					<p>
						<input type="text" onkeyup="searchMemberLike(this)" onfocus='if(jQuery(".fuzzysearch").children("li").length>0){jQuery(".fuzzysearch").show();}' id="serchMemberByNameOrPhone" placeholder="会员卡/姓名">
						<button id="serchMemberByNameOrPhoneDoc">查询</button>
						<button style="margin-left: 40px" value="0" onclick="selectHasDeleted(this)">已冻结账户</button>
						<button class="input_file" onclick="jQuery('.zzc').show();">导入会员</button>
						<span><em class="triangle-down"></em>更多筛选信息</span>
					</p>
					<ul class="fuzzysearch" style="display: none;"></ul>
					<div class="clearfix data_select" style="display: none">
						<div class="data_select_content">
							<div>
								储值余额<span><input type="text" id="chuzhi1""><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="chuzhi2"><i>元</i></span>
							</div>
							<div>
								积分余额<span><input type="text" id="jifenye1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="jifenye2"><i>元</i></span>
							</div>
							<div>
								生<a style="display: inline-block; margin-left: 25px; color: black; cursor: text" href="javascript:">日</a><span><input type="text" id="birthday-start" onfocus="WdatePicker({dateFmt:'MM-dd'})"><i>日</i><em ><img src="<%=basePath%>images/dash.png"></em><input type="text" id="birthday-end" onfocus="WdatePicker({dateFmt:'MM-dd'})" ><i>日</i></span>
							</div>
						</div>
						<div class="data_select_content">
							<div>
								注册日期<span><input type="text" id="register-start" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ><i>日</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="register-end" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" ><i>日</i></span>
							</div>
							<div>
								消费余额<span><input type="text" id="xiaofje1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="xiaofje2"><i>元</i></span>
							</div>
							<div>
								累计消费<span><input type="text" id="leijixf1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="leijixf2"><i>元</i></span>
							</div>
						</div>
						<div class="data_select_content">
							<div>
								距上次消费<span style="margin-left: 7px"><input type="text" id="scxf1"><i>日</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="scxf2"><i>日</i></span>
							</div>
							<div>
								礼金金额<span><input type="text" id="ljye1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="ljye2"><i>元</i></span>
							</div>
							<div>
								挂账金额<span><input type="text" id="gzje1"><i>元</i><em><img src="<%=basePath%>images/dash.png"></em><input type="text" id="gzje2"><i>元</i></span>
							</div>
						</div>
						<div class="data_select_content_">
							<em>*</em>搜索以后可以创建会员分组
							<button id="serch">搜索</button>

						</div>
						<div class="data_select_content_" style="border-bottom: none">
							满足当前搜索条件共<em id="member_serch_count">6</em>人，你要保存该会员分组吗？<span>会员分组名称<input type="text" id="group_name"></span><i style="display: inline-block; margin-left: 90px"><button style="width: 110px; margin-left: 0" id="baocun">保存</button>
								<button style="width: 110px; margin-left: 20px" id="fangqi">取消</button></i>
						</div>
					</div>


					<div class="vip_num">
						<p>
							<span>会员数:<em>${page.totalRecord }</em></span><span>储值总额（含赠送）:<em>${storeMember.totalAmount }</em></span><span>赠送总额:<em>${storeMember.totalPresentAmount }</em>
							</span><span>储值余额:<em>${storeMember.balanceAmount }</em></span><span>礼金金额:<em>${storeMember.balanceGiftmoneyAmount }</em></span><span>挂账金额:<em>${storeMember.debtAmount }</em></span>
						</p>
						<div class="vip_num_table">
							<table>
								<tbody id="init_member">
									<tr>
										<td>手机号</td>
										<td>姓名</td>
										<td>性别</td>
										<td>储值余额</td>
										<td>积分余额</td>
										<td>累计消费</td>
										<td>消费均额</td>
										<td>礼金金额</td>
										<td>注册日期</td>
										<td style="width: 30px">会员等级</td>
										<td>生日</td>
										<td>挂账金额</td>
										<td colspan="3">操作</td>
									</tr>
									
									<c:forEach items="${page.results }" var="member" varStatus="index">
									<tr>
										<td community="${member.community }">${member.phone }</td>
										<td class="can-click number" data-target="#member-data" data-toggle="modal" onclick="selectMemberInfo(${member.memberId })" id="${member.memberId }">${member.name }</td>
										<td>${member.sex }</td>
										<td>${member.balanceAmount }</td>
										<td>${member.balanceIntegral }</td>
										<td>${member.totalConsumeAmount }</td>
										<td>${member.avgConsumeAmount }</td>
										<td>${member.balanceGiftmoneyAmount }</td>
										<td>${fn:substring(member.createTime, 0, 10)}</td>
										<td style="width:120px">
											<c:forEach items="${member.memberSubAccounts }" var="memberSubAccount">
												<p levelId="${memberSubAccount.levelId }">${memberSubAccount.levelName }</p>
											</c:forEach>
										</td>
										<td>${member.birthday }</td>

										<td>${member.debtAmount }</td>
										<td>
											<c:forEach items="${member.memberSubAccounts }" var="memberSubAccount">
												<p><button onclick="returnCard(${memberSubAccount.subAccountId}, ${memberSubAccount.levelId}, this)">退</button></p>
											</c:forEach>
										</td>
										<td><p>
												<button style="background: #4e6fb3;" onclick="deletedMember(${member.memberId }, 1, this)">冻</button>
											</p>
										</td>
										<td><p>
												<button class="update" style="background: #2eb725">改</button>
											</p>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<%@ include file="/template/page.jsp" %>
					</div>
				</div>
			</div>
			<!-- rightpanel -->
		</div>
	</div>
	<!--mainwrapper-->
	<%@ include file="/template/memberData.jsp"%>
	
	<!-- 修改会员资料 -->
<div class="modal hide" id="member-xiugai" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-xiugai">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">修改资料</h4>
            </div>
            <div class="modal-body">
                <div style="border-bottom:1px solid #fff">

                    <!-- 基本信息开始 -->
                    <div id="tabs-1">
                        <div class="xg-title">
                            <ul>
                                <li><span>姓名：</span><input type="text" value="" onblur="changeName(this)"></li>
                                <li><span>性别：</span><label class="radio">男<input type="radio" checked="checked" onclick="changeSex('男')"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label class="radio">女<input type="radio" onclick="changeSex('女')"></label></li>
                                <li><span>生日：</span><input type="text" value="" class="timePickerClean datetimepicker" format="m-d" onblur="changeBirthday(this)"></li>
                                <li><span>手机号：</span><input type="text" value="" onblur="changePhone(this)"></li>
                                <li style="width: 100%"><span>备注：</span><input type="text" value="" style="width: 80%" onblur="changeCommunity(this)"></li>
                            </ul>
                        </div>
                        <table class="table table-bordered table-alter member-base-info-table">
                            <tbody>
                            <tr>
                                <td class="width20 td-bkg-col">会员等级</td>
                                <td class="width30">爱心卡</td>
                                <td class="td-bkg-col">储值余额</td>
                                <td>18900</td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">积分余额</td>
                                <td class="red">899</td>
                                <td class="td-bkg-col">累计消费</td>
                                <td>899</td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">消费均额</td>
                                <td>32103</td>
                                <td class="td-bkg-col">创建日期</td>
                                <td>32103</td>
                            </tr>
                        </tbody>
                    </table>
                    <form id="updateMemberInfo">
                    	<input type="hidden" name="memberId" >
	                    <input type="hidden" name="name" >
	                    <input type="hidden" name="sex" >
	                    <input type="hidden" name="birthday" >
	                    <input type="hidden" name="phone" >
	                    <input type="hidden" name="community" >
                    </form>
                  </div>
                  <!-- 基本信息结束 -->
              </div>

            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" onclick="updateMember()">确定</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div>
</body>


<script>
var pageNo = '${page.pageNo}';	
var pageSize = '${page.pageSize}';	
var totalPage = '${page.totalPage}';
var str = '<%=request.getAttribute("infoDtos")%>';
var memberArray = eval("(" + '<%=request.getAttribute("infoDtos")%>' + ")");
var status = null;
function returnCard(subAccountId, levelId, p){
	if(confirm("确定执行退卡操作么")){
		jQuery.ajax({
	  		type : "post",
	  		url : baseUrl + "member/action/return/card",
	  		data : "subAccountId="+subAccountId,
	  		dataType : "json",
	  		success : function(e) {
	  			if (e.code != 0) {
	  				dialog(e.msg);
	  				return;
	  			}
	  			else {
	  				jQuery(p).parents("tr").find("p[levelId='"+levelId+"']").remove();
	  				jQuery(p).parent().remove();
	  				var num = jQuery(".vip_num").children("p").children("span").eq(3).find("em").text();
	  				jQuery(".vip_num").children("p").children("span").eq(3).find("em").text(Number(num)-Number(e.msg));
	  			}
	  		}
	  	});
	}
}

function deletedMember(memberId, type, tr){
	var desc ;
	if (type == 1){
		desc = "确定冻结该会员么";
	}
	else {
		desc = "确定解冻该会员么";
	}
	if(confirm(desc)){
		jQuery.ajax({
	  		type : "post",
	  		url : baseUrl + "member/action/deleted/member",
	  		data : "memberId="+memberId+"&isDeleted="+type,
	  		dataType : "json",
	  		success : function(e) {
	  			if (e.code != 0) {
	  				dialog(e.msg);
	  				return;
	  			}
	  			else {
	  				jQuery(tr).parents("tr").remove();
	  			}
	  		}
	  	});
	}
}
// 查询冻结账户
function selectHasDeleted(but){
	if (jQuery(but).val() == 1){
		jQuery(".vip_num").children("p").eq(0).show();
		jQuery(but).val('0');
		status = "";
		jQuery(but).text("已冻结账户");
	}else {
		jQuery(".vip_num").children("p").eq(0).hide();
		jQuery(but).val('1');
		status = "1";
		jQuery(but).text("未冻结账户");
	}
	jQuery("#serch").click();
}
</script>
<script>
jQuery(function(){
	jQuery(document).click(function(e){	
		var tar=e.target;
		if(!jQuery(e.target).is('.fuzzysearch,#serchMemberByNameOrPhone')){
			jQuery('.fuzzysearch').hide();
		}
	})
})

function upload(){
	var fileObj = document.getElementById("file").files[0];
    var FileController = baseUrl + "member/action/uploadMemberExls";                    // 接收上传文件的后台地址 
    var form = new FormData();
    form.append("file", fileObj);
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (e) {
    	var baseDto = eval("("+xhr.responseText+")");
    	if (baseDto.code != 0){
    		dialog("导入成功");
    		window.location.href = baseUrl + "member/view/list";
    	}else {
    		jQuery("#p").text(baseDto.msg);
    	}
    };
    xhr.send(form);
}
</script>
<script src="<%=basePath%>js/member/member-list.js" type="text/javascript"></script>
<script src="<%=basePath%>js/member/memberUpdate.js" type="text/javascript"></script>
</html>