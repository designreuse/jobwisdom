<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String templatePath = request.getContextPath();
			String templateBasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
					+ templatePath + "/";
%>
<style>
    .s-modal {
        height: auto !important;
    }
    .s-modal, .s-gouwuche-modal {
        overflow: auto;
        z-index: 10000;
    }
</style>
<div class="s-modal hide s-modal-miss" id="memberInfo">
    <div class="s-modal-wrap yg-member-info1">
        <div class="d-member-info">
            <div class="n-modal-title">
               <div class="info-title">
                   <div class="mem-images">
               <img src="" alt=""/>
          </div>
                <div class="information">
                    <div>
                    	<span class="n-name" name="memberName"></span>
                    	<span class="sex" name="memberSex"></span>
						<span class="shenfen" name="levelName"></span>
					</div>
                    <div><span class="shop-name" name="storeName"></span></div>
                </div>
               </div> 
               <span class="fr s-modal-miss normoal-word n-close-div iconfont icon-shanchu8"></span>
               <div class="clearfix"></div>
               <div class="info-detail">
              <ul>
                  <li>
                      <p>卡金余额</p>
                      <span name="balanceAmount"></span>
                  </li>
                  <li>
                      <p>可用礼金</p>
                      <span name="giftmoneyAmount"></span>
                  </li>
                  <li>
                      <p>当前积分</p>
                      <span name="balanceIntegral"></span>
                  </li>
              </ul>
         </div>
            </div>
            <div class="s-modal-body no-padding">
          <ul class="info-ul" name="memberInfoUL">
              <li>
                  <span class="fl item-name">手机号</span>
                  <span class="fr item-content" name="memberPhone"></span>
              </li>
              <li>
                  <span class="fl item-name">消费总额</span>
                  <span class="fr item-content" name="totalConsumeAmount"></span>
              </li>
              <li>
                  <span class="fl item-name">平均消费</span>
                  <span class="fr item-content" name="avgConsumeAmount"></span>
              </li>
              <li>
                  <span class="fl item-name">消费频率</span>
                  <span class="fr item-content" name="avgConsumeDays"></span>
              </li>
              <li>
                  <span class="fl item-name">距上次来店</span>
                  <span class="fr item-content" name="lastDayNumber"></span>
              </li>
              <li>
                  <span class="fl item-name">上次来店消费项目</span>
                  <span class="fr item-content" name="lastProjectName"></span>
              </li>
          </ul>
          <div class="mem-beizhu">
              <div class="mem-beizhu-in">
              	  <span class="font-red" name="debtAmount"></span><br/>
                  <span class=" font-red" name="community"></span>
              </div>
          </div>
      </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="<%=templateBasePath%>js/mobile/jquery.min.js"> </script>
<script type="text/javascript" src="<%=templateBasePath%>js/mobile/base.js"> </script>
<script type="text/javascript">
function showMemberInfo(memberId){
	if (isEmpty(memberId)) {
		return;
	}
	
	$.ajax({
		url : "<%=templateBasePath%>staff/action/selectMemberInfo",
		type : "POST",
		data : "memberId=" + memberId,
		success : function(e){
			if (e.code == 0) {
				var memberInfo = e.msg;
				var memberInfoObj = $("#memberInfo");
				var phone = memberInfo.phone;
				/* phone = phone.substring(0, 3) + "****" + phone.substring(7, 11); */
				memberInfoObj.find("img").attr("src", picUrl + memberInfo.headUrl + "?imageView2/1/w/220/h/220");
			    memberInfoObj.find("[name='memberName']").text(memberInfo.name);
			    memberInfoObj.find("[name='memberSex']").text(memberInfo.sex);
			    memberInfoObj.find("[name='storeName']").text(memberInfo.storeName);
			    memberInfoObj.find("[name='levelName']").text(memberInfo.levelName);
			    memberInfoObj.find("[name='balanceAmount']").text(memberInfo.balanceAmount);
			    memberInfoObj.find("[name='memberPhone']").text(phone);
			    memberInfoObj.find("[name='balanceIntegral']").text(memberInfo.balanceIntegral);
			    memberInfoObj.find("[name='avgConsumeDays']").text(memberInfo.avgConsumeDays);
			    memberInfoObj.find("[name='avgConsumeAmount']").text(memberInfo.avgConsumeAmount);
			    memberInfoObj.find("[name='lastDayNumber']").text(memberInfo.lastDayNumber+ "天");
			    memberInfoObj.find("[name='giftmoneyAmount']").text(memberInfo.giftmoneyAmount);
			    memberInfoObj.find("[name='totalConsumeAmount']").text(memberInfo.totalConsumeAmount);
			    memberInfoObj.find("[name='lastProjectName']").text(memberInfo.lastProjectName);
			    memberInfoObj.find("[name='debtAmount']").text("挂账：" + memberInfo.debtAmount + "元");
			    $("[name='memberInfoUL']").find(".mem-people").remove();
				var projectStepList = memberInfo.projectStepList;
			    for (var j = 0; j < projectStepList.length; j++) {
					var projectStep = projectStepList[j];
					var serviceInfo = "<li class='mem-people'>"
						+ "<span class='fl item-name'>"+projectStep.shiftMahjongName+"</span>"
						+ "<span class='fr item-content' >"+projectStep.employeeCode+"  " + projectStep.employeeName;
					if (projectStep.employeeAssign == 1) {
						serviceInfo += "(指定)";
					}
					serviceInfo += "</span></li>";
					$("ul[name='memberInfoUL']").append(serviceInfo);
				}
			    memberInfoObj.find("[name='community']").text("备注：" + memberInfo.community);
			    memberInfoObj.removeClass("hide");
			}
		}
	});
}
</script>
