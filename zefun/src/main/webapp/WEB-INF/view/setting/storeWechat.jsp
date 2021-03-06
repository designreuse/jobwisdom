<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" />
 <style>
    .hide{
        display: none!important;
    }
</style>
<body>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
	<div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
        <div class="maincontent">
		    <div class="contentinner">
		        <div class="border-head">
		            <span>微信配置(${session_key_user_info.storeName })</span>
		        </div>
		        <form id="storeWechatForm">
		        	<input type="hidden" name="storeAccount" value="${storeWechat.storeAccount }" />
			        <div class="border-content">
			            <table class="table nobordered-table">
			                <tr>
			                    <td class="text-right fb">微信原始ID:</td>
			                    <td>
			                         <input type="text" name="wechatId" value="${storeWechat.wechatId }" class="name"/>
			                    </td>
			                </tr>
			                <tr>
                                <td class="text-right fb">AppID(应用ID):</td>
                                <td>
                                     <input type="text" name="wechatAppid" value="${storeWechat.wechatAppid }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">AppSecret(应用密钥)</td>
                                <td>
                                     <input type="text" name="wechatAppsecret" value="${storeWechat.wechatAppsecret }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="fl" colspan="2">以下为微信通知模板的配置项</td>
                            </tr>
                            <tr>
                                <td class="text-right fb">客户预约提醒</td>
                                <td>
                                     <input type="text" name="tmAppointApply" value="${storeWechat.tmAppointApply }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">预约结果通知</td>
                                <td>
                                     <input type="text" name="tmAppointResult" value="${storeWechat.tmAppointResult }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">预约服务提醒</td>
                                <td>
                                     <input type="text" name="tmAppointRemind" value="${storeWechat.tmAppointRemind }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">会员充值提醒</td>
                                <td>
                                     <input type="text" name="tmChargeResult" value="${storeWechat.tmChargeResult }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">商户结算提醒</td>
                                <td>
                                     <input type="text" name="tmPaymentInfo" value="${storeWechat.tmPaymentInfo }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">任务处理通知</td>
                                <td>
                                     <input type="text" name="tmServiceTurn" value="${storeWechat.tmServiceTurn }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">服务订单通知</td>
                                <td>
                                     <input type="text" name="tmServiceTopic" value="${storeWechat.tmServiceTopic }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">卡券库存提醒</td>
                                <td>
                                     <input type="text" name="tmCouponOverdue" value="${storeWechat.tmCouponOverdue }" class="name"/>
                                </td>
                            </tr>
                            
                            <tr>
                                <td class="text-right fb">appId</td>
                                <td>
                                     <input type="text" name="appId" value="${storeWechat.appId }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">商户ID</td>
                                <td>
                                     <input type="text" name="mchId" value="${storeWechat.mchId }" class="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="text-right fb">api秘钥</td>
                                <td>
                                     <input type="text" name="mchKey" value="${storeWechat.mchKey }" class="name"/>
                                </td>
                            </tr>
                            
			                <tr>
	                            <td class="text-right fb"><a class="btn btn-primary" href="javascript:save();">保存</a></td>
	                            <td></td>
	                        </tr>
			            </table>
			        </div>
		        </form>
		    </div>
		</div>
      </div>
    </div>
</div>

<script type="text/javascript">
function save(){
    var data = jQuery("#storeWechatForm").serialize();
    jQuery.ajax({
        url : baseUrl + "system/action/storeWechat",
        type : "POST",
        data : data,
        success : function(e){
            if (e.code != 0) {
                dialog(e.msg);
                return;
            }
            dialog("已更新您的配置");
        }
    });
}
</script>
</body>
</html>