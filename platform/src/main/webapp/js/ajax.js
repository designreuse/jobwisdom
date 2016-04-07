$.ajaxSetup({
	error : function(xhr, textStatus, error) {
		var err_code = xhr.status;
		switch (err_code) {
		case 404:
			alert('请求路径没有找到.');
			break;
		case 500:
			alert('服务器发生错误, 请稍后再试.');
			break;
		case 405:
			alert('错误的请求方式.');
			break;
		case 403:
			//alert('请先登陆.');
			$("#loginDialogBtn").trigger("click");
			break;
		default:
			alert('请求发生错误.');
			break;
		}
	},
	beforeSend : function(xhr) {
		if (!(document.cookie || navigator.cookieEnabled)) {
			alert('您的浏览器关闭了cookie功能, 这样可能会影响您在本站的体验.');
		}
	},
	cache: false,
	headers : {'isAjax' : "1"}
});