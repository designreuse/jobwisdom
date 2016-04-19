<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/base.jsp" %>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, height = device-height">
    <meta content="telephone=no" name="format-detection" />
    <title>会议邀请函</title>
    <link rel="stylesheet" href="<%=iconfontCssPath%>"/>
    <link rel="stylesheet" href="<%=basePath%>css/mobile/agent.css?date=<%=new Date().getTime() %>"/>
    <link rel="stylesheet" href="<%=muiCssPath%>"/>
    <style>
    .text-indent2 {
    color:#333;
    }
    
    .sm-wrap p {
    color:#333;
    }
    </style>
  </head>
<body class="gray-bg">
<div class="wrap">
<div class="yaoqinghan">
    <div class="han-wrap">
        <div class="han">
        <!--涵的内容-->
        <div class="han-content">
            <div class="content-desc">
                <div class="han-title" id="jiaoyan">
                	${conferenceInfo.conferenceName }
                </div>
                <div class="title-fenxian">
                    <img src="<%=basePath %>images/mobile/agent/han-title-fenxain.png" alt=""/>
                </div>
                <div class="jb-time">
                	会议开始时间：<fmt:formatDate pattern="MM月dd日 HH点mm分"  value="${date }" />
                </div>
                <div class="jb-address">
                    <span class="iconfont icon-dizhi1 mr"></span> <span class="address-word">${conferenceInfo.address }</span>
                </div>
            </div>
        </div>
        <!--涵的封面-->
        <div class="han-fm">

            <!--我要报名-->
            <div class="bm-wrap">
                <div class="bm">
                    <ul class="sort">
                        <li class="tab-current" data-target="yqh-bm">
                            <div class="tab-border">
                                <span>我要报名</span>
                            </div>
                        </li>
                        <li data-target="yqh-hy" >
                            <div class="tab-border " >
                                <span>会议说明</span>
                            </div>
                        </li>
                        <li data-target="yqh-share" >
                            <div class="tab-border" >
                                <span>有奖分享</span>
                            </div>
                        </li>
                    </ul>

                    <div class="sm-wrap-main">
                    <form id="from">
                    <input type="hidden" name="conferenceId" value="${conferenceInfo.conferenceId }">
                    <input type="hidden" name="openId" value="${session_key_wechat_open_id }">
                    <input type="hidden" name="refereeId" value="${fromUser }">
                    <c:if test="${enrollInfo==null&&agent==null }">
                        <!--我要報名-->
                        <div class="sm-content" id="yqh-bm">
                            <div class="bm-time">
                               	 报名截止时间 : <fmt:formatDate pattern="MM月dd日 HH点mm分"  value="${date2 }" />
                            </div>
                            <div class="form-group mt35 huiyi-form">
                                <ul class="register-ul">
                                    <li class="mb20">
                                        <label for="" class="left-label register-label">手机号</label>
                                        <div class="name register-input">
                                            <input type="tel" name="phone" class="normal-input"/>
                                        </div>
                                    </li>
                                    <li class="mb20">
                                        <label for="" class="left-label register-label">联系人</label>
                                        <div class="name register-input">
                                            <input type="text" name="name" class="normal-input"/>
                                        </div>
                                    </li>
                                    <li class="mb20">
                                        <label for="" class="left-label register-label">门店名称</label>
                                        <div class="name register-input mendian">
                                            <input type="text" name="storeName" class="normal-input"/>
                                        </div>
                                    </li>
                                    <li class="mb20">
                                        <label for="" class="left-label register-label">门店类型</label>
                                        <div class="name register-input showUserPicker" >
                                            <div class="s-select"  id="mendian">
                                                <input type="hidden" name="storeType" id="storeType">
                                                <input type="text" id="storeTypeName" class="normal-input" placeholder="选择您的门店类型" readonly/>
                                                <img src="<%=basePath %>images/mobile/agent/select-trangle.png" alt="" class="select-trangle" >
                                            </div>
                                        </div>
                                    </li>
                                    <li class="mb20">
                                        <label for="" class="left-label register-label">参会费用</label>
                                        <div class="name register-input">
                                            <span class="red ch-feiyong font-size-32">${conferenceInfo.registrationAmount }元</span>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                            <div class="page-btn-wrap">
                                <div class="page-btn" onclick="join(this)">参加会议</div>
                            </div>
                            <div class="company-address">
                                <span style="text-align: center;">www.maywant.com</span>
                            </div>
                        </div>
                        </c:if>
                        <c:if test="${enrollInfo==null&&agent!=null }">
                        	<div class="sm-content" id="yqh-bm">您是发起人,分享试试吧~~~</div>
                        </c:if>
                        <c:if test="${enrollInfo!=null&&agent==null }">
                        	<c:if test="${enrollInfo.isPay == 0 }">
                        	<div class="sm-content has-pay" id="yqh-bm">
							    <div class="sm-wrap">
							        <img src="<%=basePath %>images/baoming-pic.png" alt="" class="fl bm-pic"/>
							        <div class="fr bm-detail">
							            <p class="bm-title">你已经成功报名啦！</p>
							            <p> 但是还没有缴费哦，成功缴费才能参加会议和获得分享奖励哦！</p>
							        </div>
							    </div>
							    <div class="page-btn-wrap">
							        <div class="page-btn" onclick="initWechat(${enrollInfo.personnelId}, ${enrollInfo.conferenceId})">立即缴费</div>
							    </div>
							    <div class="company-address">
							        <span>www.maywant.com</span>
							    </div>
							</div>
                        	</c:if>
                        	<c:if test="${enrollInfo.isPay == 1 }">
                        	<div class="sm-content has-pay" id="yqh-bm">
							    <div class="sm-wrap">
							        <img src="<%=basePath %>images/baoming-pic.png" alt="" class="fl bm-pic"/>
							        <div class="fr bm-detail">
							            <p class="bm-title">你已经成功报名啦！</p>
							            <p>成功缴费能参加会议和获得分享奖励哦！</p>
							        </div>
							    </div>
							    <div class="page-btn-wrap">
                                <div onclick="share()" class="page-btn s-modal-control">立即分享</div>
	                        	</div>
							    <div class="company-address">
							        <span>www.maywant.com</span>
							    </div>
							</div>
                        	</c:if>
                        </c:if>
                        </form>
                        <!--会议说明-->
                        <div class="sm-content hide" id="yqh-hy">
                            <p>
                            <div class="em-div"></div>
                            <span>会议内容</span>
                            </p>

                            <p class="text-indent2">
                                	${conferenceInfo.conferenceDesc }
                            </p>

                            <p>
                            <div class="em-div"></div>
                            <span>会议安排</span>
                            </p>

                            <div class="hyap">
                                <div>
                                    <label for="">召开时间:</label> <span>${conferenceInfo.holdTime }</span>
                                </div>
                                <div>
                                    <label for="">会议地点:</label> <span>${conferenceInfo.address }</span>
                                </div>
                                <div>
                                    <label for="">联系人:</label> <span>${conferenceInfo.linkName }</span>
                                </div>
                                <div>
                                    <label for="">联系电话:</label> <span>${conferenceInfo.linkPhone }</span>
                                </div>
                            </div>
                            <div class="page-btn-wrap">
                                <div onclick="share()" class="page-btn s-modal-control">立即分享</div>
	                        </div>
	                        <div class="company-address">
	                                <span>www.maywant.com</span>
	                        </div>
                        </div>
                        <!--有奖分享-->
                        <div class="sm-content hide" id="yqh-share">
                            <p>
                            <div class="em-div"></div>
                            <span>分享此会议邀请函给你的朋友，若朋友参加了本次会议，您就可以得到相应的一级${conferenceInfo.mainAward }元、二级${conferenceInfo.branchAward }元奖励。</span>
                            </p>

                            <table class="table fxjl-table">
                                <thead>
                                <tr>
                                    <th>姓名</th>
                                    <th>手机号</th>
                                    <th>推荐状态</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${mainAware }" var="mainAware">
	                                <tr>
	                                    <td>${mainAware.name }</td>
	                                    <td>${mainAware.phone }</td>
	                                    <td>一级推荐</td>
	                                </tr>
                                </c:forEach>
                                <c:forEach items="${branchAware }" var="branchAware">
	                                <tr>
	                                    <td>${branchAware.name }</td>
	                                    <td>${branchAware.phone }</td>
	                                    <td>二级推荐</td>
	                                </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div class="jl-sum">您获得一级推荐奖励 <span class="red font-size-32">
                            <c:if test="${mainCount == null}">
                            0
                            </c:if>
                            <c:if test="${mainCount != null}">
                            ${mainCount }
                            </c:if>
                            </span>个,二级奖励<span class="red font-size-32">
                            <c:if test="${branchCount == null}">
                            0
                            </c:if>
                            <c:if test="${branchCount != null}">
                            ${branchCount }
                            </c:if>
                            </span>个，共获得 <span class="red font-size-32">
                            <c:if test="${amount == null}">
                            0
                            </c:if>
                            <c:if test="${amount != null}">
                            ${amount }
                            </c:if>
                            </span>元</div>
                            <div class="page-btn-wrap">
                                <div onclick="share()" class="page-btn s-modal-control">立即分享</div>
	                        </div>
	                        <div class="company-address">
	                                <span>www.maywant.com</span>
	                        </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    </div>
    </div>
</div>
<div class="sm-content hide pay">
    <div class="sm-wrap">
        <img src="<%=basePath %>images/baoming-pic.png" alt="" class="fl bm-pic"/>
        <div class="fr bm-detail">
            <p class="bm-title">你已经成功报名啦！</p>
            <p>但是还没有缴费哦，成功缴费才能参加会议和获得分享奖励哦！</p>
        </div>
    </div>
    <div class="page-btn-wrap">
        <div class="page-btn">立即缴费</div>
    </div>
    <div class="company-address">
        <span>www.maywant.com</span>
    </div>
</div>
<!--邀请函-->
<div class="s-modal no-padding  s-modal-miss hide" id="judao-yaoqinghan-modal">
     <div class="yqh-wrap">
         <div class="yqh-title">
             <span class="fr s-modal-miss close-x iconfont icon-xx"></span>
         </div>
         <div class="ygh-main">
             <div class="baoming">
                 	报名成功
             </div>
             <p>请于2015年12月25日上午九点</p>
             <div class="jyh-btn">确 定</div>
         </div>
     </div>
</div>
</div>
<!-- 分享提示 -->
<div class="s-modal no-padding hide s-modal-miss" id="shareTip">
    <div class="s-modal-wrap">
        <div class="jd-wrap">
            <img src="<%=picPath %>agent_share_tip.png" alt=""/>
            <div class="jd-main">
                	已为你生成 <span id="typeName">${conferenceInfo.conferenceName }</span> 的推广链接, 请点击右上角分享吧！
                <div class="jd-btn">我知道了</div>
            </div>
        </div>
    </div>
</div>

<%@include file="../wechatBase.jsp" %>
<script type="text/javascript" src="<%=muiJsPath%>"></script>
<script type="text/javascript" src="<%=jqueryJsPath%>"> </script>
<script src="<%=mobileBaseJsPath%>"></script>
<script type="text/javascript">
var fromUser = '${fromUser}';
var conferenceId = '${conferenceInfo.conferenceId}';
var imgUrl = "http://7xkv8r.com1.z0.glb.clouddn.com/zefun/project/1452323943233";           
var desc = '${conferenceInfo.conferenceDesc}';
var link = baseUrl+'mobile/view/pay/conference?fromUser='+fromUser+'&conferenceId='+conferenceId;
var title = '${conferenceInfo.conferenceName}';

wx.ready(function(){
	wx.onMenuShareAppMessage({
		  title: title,
		  desc: desc,
		  link: link,
		  imgUrl: imgUrl
		});
	wx.onMenuShareTimeline({
	  title: title,
	  link: link,
	  imgUrl: imgUrl
	});
});
/**分享*/
function share(){
	wx.onMenuShareAppMessage({
		  title: title,
		  desc: desc,
		  link: link,
		  imgUrl: imgUrl
		});
	wx.onMenuShareTimeline({
	  title: title,
	  link: link,
	  imgUrl: imgUrl
	});
	$("#shareTip").show();
}

/**报名*/
function join(obj){
	var phone = $("input[name='phone']").val();
	var name = $("input[name='name']").val();
    var reg = /^0?1[3|4|5|7|8][0-9]\d{8}$/;
    if (!reg.test(phone)) {
     dialog("请输入正确的手机号码!");
     return;
    }
    if (name == ""){
    	dialog("请输入正确的联系人!");
        return;
    }
	var data = $("form").serialize();
	$.ajax({
		type: "POST",
		url: baseUrl+"conference/join/conference",
        data: data,
        dataType: "json",
        error: function(XMLHttpRequest, textStatus, errorThrown) {
        	dialog(XMLHttpRequest.status);
        	setTimeout(dialog(XMLHttpRequest.readyState), 3000);
        	setTimeout(dialog(textStatus), 9000);
       	},
        success: function(e) {
        	if(e.code!=0) dialog(e.msg);
        	showPayHtml(e.msg.personnelId, conferenceId, e.msg.isPay);
        	/* initWechat(e.msg);
        	callWeixin(e.msg.pay); */
        	//支付接口
        	
        	/* $("#yqh-bm").remove();
        	$("#shareTip").show();
        	$(obj).remove();
        	share(); */
        }
	});
}
/**报名成功展示付款页面*/
function showPayHtml(personnelId, conferenceId, isPay){
	if (isPay == 0){
		var str = '<div class="sm-wrap">'+
	    '<img src="'+baseUrl+'images/baoming-pic.png" alt="" class="fl bm-pic"/>'+
	    '<div class="fr bm-detail">'+
	        '<p class="bm-title">你已经成功报名啦！</p>'+
	        '<p>但是还没有缴费哦，成功缴费才能参加会议和获得分享奖励哦！</p>'+
	    '</div>'+
		 '</div>'+
		 '<div class="page-btn-wrap">'+
		    '<div class="page-btn" onclick="initWechat('+personnelId+','+conferenceId+')">立即缴费</div>'+
		 '</div>'+
		 '<div class="company-address">'+
		     '<span>www.maywant.com</span>'+
		 '</div>';
		$("#yqh-bm").empty();
		$("#yqh-bm").append($(str));
	}
	else {
		showPayCallBackHtml();
		fromUser = personnelId;
		link = baseUrl+'mobile/view/pay/conference?fromUser='+personnelId+'&conferenceId='+conferenceId;
	}
	
}
/**缴费成功后的刷新html*/
function showPayCallBackHtml(){
	var str = '<div class="sm-wrap">'+
				    '<img src="'+baseUrl+'images/baoming-pic.png" alt="" class="fl bm-pic"/>'+
				    '<div class="fr bm-detail">'+
				        '<p class="bm-title">你已经成功缴费啦！</p>'+
				        '<p>成功缴费能参加会议和获得分享奖励哦！</p>'+
				    '</div>'+
					 '</div>'+
					 '<div class="page-btn-wrap">'+
					    '<div class="page-btn" onclick="share()">立即分享</div>'+
					 '</div>'+
					 '<div class="company-address">'+
					     '<span>www.maywant.com</span>'+
					 '</div>';
	$("#yqh-bm").empty();
	$("#yqh-bm").append($(str));
}
function initWechat(personnelId, conferenceId){
	  $.ajax({
		  type : "POST",
		  url : baseUrl + "conference/init/pay",
		  data : "goodsName=渠道会议报名&personnelId="+personnelId+"&conferenceId="+conferenceId,
		  dataType : "json",
		  success : function(e){
			  if (e.code != 0) {
				  dialog(e.msg);
				  return;
			  }
			  callWeixin(e.msg, personnelId, conferenceId);
		  }
	  });
}

function callWeixin(rj, personnelId, conferenceId) {
    WeixinJSBridge.invoke('getBrandWCPayRequest', {
        "appId" : rj.appId,
        "timeStamp" : rj.timeStamp,
        "nonceStr" : rj.nonceStr,
        "package" : rj.package,
        "signType" : rj.signType,
        "paySign" : rj.paySign
    }, function(res) {
        // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
        // 因此微信团队建议，当收到ok返回时，向商户后台询问是否收到交易成功的通知，若收到通知，前端展示交易成功的界面；若此时未收到通知，商户后台主动调用查询订单接口，查询订单的当前状态，并反馈给前端展示相应的界面
        if (res.err_msg == "get_brand_wcpay_request:ok") {
        	link = baseUrl+'mobile/view/pay/conference?fromUser='+personnelId+'&conferenceId='+conferenceId;  //生成了当前分享着的分享链接,只要分享前控制好这句就行了,别的不管
        	showPayCallBackHtml();
//          WeixinJSBridge.invoke('closeWindow', {}, function(e) {});
//          window.location.href = baseUrl + "memberCenter/view/home/${session_key_store_id}/1";
        } else {
            isPay = false;
        }
    });
}

$("#shareTip").click(function(){
	$(this).hide();
});
</script>
</body>
<script>
    $(function(){
        $(".sort li").on("click", function(){
            $(this).addClass("tab-current").siblings().removeClass("tab-current");
            $(".sm-content").addClass("hide");
            $("#" + $(this).attr("data-target")).removeClass("hide");
        })
    })
</script>
<script>
    (function($, doc) {
        $.init();
        $.ready(function() {
            //普通示例
            var userPicker = new $.PopPicker();
            userPicker.setData([{
                value: '1',
                text: '连锁总部'
            }, {
                value: '2',
                text: '连锁分店'
            }, {
                value: '3',
                text: '单店'
            }]);
            var showUserPickerButton = doc.getElementById('mendian');
            showUserPickerButton.addEventListener('tap', function(event) {
                userPicker.show(function(items) {
                	doc.getElementById('storeType').value = items[0].value;
                	doc.getElementById('storeTypeName').placeholder = items[0].text;
                });
            }, false);
        });
    })(mui, document);
    /*tab链接*/
</script>
<script>
    $(function(){
        $(".tab-toggle").on("click", function(){
            var th = $(this);
            $(".tab-toggle").removeClass("active");
            th.addClass("active");
            var tabTraget = th.data("target");
            $(".tab-target").hide();
            $(tabTraget).show();
        });
    })
    /*模态框出来*/
    $(".s-modal-control").on("click", function(){
        $("body").css({"overflow":"hidden"});
        var targetModal = $(this).data("target");
        /*console.log("targetModal" + targetModal);*/
        $(targetModal).removeClass("hide");
    });

    /*模态框消失*/
    $(".s-modal-miss").on("click", function(){
        $("body").css("overflow-y","auto");
        $(".s-modal").addClass("hide");
    });
    contentHeight();

</script>
<script>
    $(function(){
        $(".sort li").click(function(){
            $(this).addClass("tab-current").siblings().removeClass("tab-current");
            $(".sm-wrap-main .sm-content").addClass("hide");
            $("#" + $(this).attr("data-target")).removeClass("hide");
        })
//        函的高度
        /*var height = document.documentElement.clientHeight;*/
        var contentHeight=function(){
            var Height=$(window).height()
            var topHeight=parseInt($(".han-fm").css("top"))
            var hanTop=parseInt($(".han").css("margin-top"))
            var hanHeight=Height-topHeight-hanTop;
             $(".han-fm").css("min-height",hanHeight)
        }
        contentHeight()
    })

</script>
</html>