<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<link rel="stylesheet" href="<%=basePath %>css/pay.css" type="text/css" />
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px; position: relative">
				<%@include file="/top.jsp"%>

				<div class="content_right clearfix">
					<div class="pay_price">
						<p>企业充值</p>
						<div class="pay_price_num">
							<div class="pay_price_content">
								<p>充值金额（元）</p>
								<ul class="clearfix">
									<li><input type="radio" name="amount" onclick="changeAmount(300)"><span>300</span></li>
									<li><input type="radio" name="amount" onclick="changeAmount(500)"><span>500</span></li>
									<li><input type="radio" name="amount" onclick="changeAmount(800)"><span>800</span></li>
									<li><input type="radio" name="amount" onclick="changeAmount(2000)"><span>2000</span></li>
									<li><input type="radio" name="amount" onclick="changeAmount(5000)"><span>5000</span></li>
									<li>其他<input type="text" onblur="changeAmount(this.value)" placeholder="输入充值金额"></li>
								</ul>
							</div>
							<div class="pay_way">
								<p>充值方式</p>
								<div class="pay_animate clearfix">
									<ul class="clearfix">
										<li class="click_me">点我扫码,去微信支付<img src="<%=basePath%>images/pay.png"></li>
										<li class="ul_li" style="position: relative; display: none;"><img src="<%=basePath%>images/div.png">
											<div class="webchat_" style="display: node;">
												<span> <img id="code" src="<%=basePath %>qr_code.img?codeUrl= ${codeUrl}">
												</span> <em style="position: absolute; margin-top: 4px; right: 10px"> <img src="<%=basePath%>images/pay_close.png">
												</em>
											</div></li>
									</ul>
									<div class="payfor">
										应付金额<span id="amount">100.00</span>元
									</div>
								</div>
							</div>
							<button class="pay_sure">确认</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function toUrl(){
		var url = baseUrl + "storeinfo/view/showStoreList";
		window.location = url;
	}
	var storeAccount = '${session_key_store_account}';
	var webSocket;
	if ('WebSocket' in window) {
		webSocket = new WebSocket("ws://job.jobwisdom.cn/jobwisdom/websocket?id=" + storeAccount);
    } else if ('MozWebSocket' in window) {
         websocket = new MozWebSocket("ws://job.jobwisdom.cn/jobwisdom/echo?id=" + storeAccount);
    } else {
    	webSocket = new SockJS("ws://job.jobwisdom.cn/jobwisdom/websocket?id=" + storeAccount);
    }
	webSocket.onerror = function(event) {
		onError(event);
	};
	webSocket.onopen = function(event) {
		onOpen(event);
	};
	webSocket.onmessage = function(event) {
		onMessage(event);
	};

	//处理从服务端发送过来的数据
	function onMessage(event) {
		jQuery(".webchat_").click();
		dialog("支付成功,即将跳转...");
		window.setTimeout(toUrl, 2000);
	}
	function onOpen(event) {
		console.log("ws onOpen");
	}
	function onError(event) {
		console.log("ws onError");
	}
	
</script>
<script>
function changeAmount(amount){
	jQuery("#amount").text(amount);
	jQuery.ajax({
		type : "POST",
		url : baseUrl + "app/pay/qr",
		data : "amount=" + amount,
		dataType : "json",
		success : function(data) {
			jQuery("#code").attr("src", baseUrl + "qr_code.img?codeUrl=" + data.msg);
		}
	});
}
jQuery(function(){
        jQuery('.click_me').click(function(){
	    jQuery('.ul_li').slideDown('normal',function(){
		   jQuery('.webchat_').slideDown('slow')
           });
       });
	jQuery('.webchat_').click(function(){
	  jQuery('.webchat_').slideUp('slow',function(){
	     jQuery('.ul_li').slideUp('normal');
          });
	});
})
 </script>
</html>