<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String wechatBasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"> </script>
<script type="text/javascript">
var appId = "<%=request.getAttribute("appId")%>";
var timestamp = "<%=request.getAttribute("timestamp")%>";
var nonceStr = "<%=request.getAttribute("nonceStr")%>";
var signature = "<%=request.getAttribute("signature")%>";
</script>
<script src="<%=wechatBasePath%>js/mobile/wxapi.js"></script>