<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
	String picPath = "http://7xss26.com1.z0.glb.clouddn.com/";
	String qiniuPath = "http://7xss26.com1.z0.glb.clouddn.com/";

	
	String memberCssPath = basePath + "css/mobile/member-0-0-1.min.css";
	String employeeCssPath = basePath + "css/mobile/employee-0-0-1.min.css";
	String bossCssPath = basePath + "css/mobile/member-0-0-1.min.css";
	String agentCssPath = basePath + "css/mobile/member-0-0-1.min.css";
	
	String muiCssPath = basePath + "css/mobile/mui-plugin.min.css";
	String muiJsPath = basePath + "js/mobile/mui-plugin.min.js";
	String swiperCssPath = basePath + "css/mobile/swiper-plugin.min.css";
	String swiperJsPath = basePath + "js/mobile/swipe-plugin.min.js";
	String starCssPath = basePath + "css/mobile/star-rating-plugin.min.css";
	String starJsPath = basePath + "js/mobile/star-rating-plugin.min.js";
	String iconfontCssPath = basePath + "css/iconfont.css";
	String jqueryJsPath = basePath + "js/mobile/jquery.min.js";
	String mobileBaseJsPath = basePath + "js/mobile/base.js";
	
	String bossNewer = basePath + "css/mobile/boss-newer.css";
	String chartComponent = basePath + "css/mobile/chart-component.css";
	String businessAnalysis = basePath + "css/mobile/business-analysis.css";
	String employee = basePath + "js/mobile/employee.js";
	String highcharts = basePath + "js/mobile/highcharts.src.js";
	
%>
<script type="text/javascript">
	var baseUrl = "<%=basePath%>";
	var picUrl = "<%=picPath%>";
	var qiniuUrl = "<%=qiniuPath%>";
</script>

