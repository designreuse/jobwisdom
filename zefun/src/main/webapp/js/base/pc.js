function dialog(msg){
  jQuery("#alertToast").html(msg);
  jQuery("#alertWrap").fadeIn();
  setTimeout(function(){
	  jQuery("#alertWrap").fadeOut(500);
  }, 1000);
}

//加载时弹出框效果
jQuery.ajaxSetup({
	timeout: 60000,
	error : function(xhr, textStatus, error) {
		var err_code = xhr.status;
		switch (err_code) {
		case 404:
			dialog('对不起，系统未找到您访问的地址');
			break;
		case 500:
			dialog('出错啦，刷新页面再试试看');
			break;
		case 405:
			dialog('错误的请求方式.');
			setTimeout(function(){
				window.location.href = baseUrl;
			}, 500)
			break;
		case 403:
			dialog('操作权限不足.');
			setTimeout(function(){
				window.location.href = baseUrl;
			}, 500)
			break;
		case 999:
			dialog('会话失效，请重新登录');
			setTimeout(function(){
				window.location.href = baseUrl;
			}, 500)
			break;
		default:
			dialog(xhr.responseText);
			break;
		}
	},
	contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	beforeSend : function(){
//		if (!(document.cookie || navigator.cookieEnabled)) {
//			dialog('您的浏览器关闭了cookie功能, 这样可能会影响您在本站的体验.');
//		}
		jQuery("#loadingWrap").fadeIn();//发起请求前显示加载中...
	},
	complete : function(){
		jQuery("#loadingWrap").fadeOut();//请求完毕后将加载效果移除
	},
	// crossDomain: true,
	cache : false,
	headers : {
		'isAjax' : "1"
	}
});


var audio = null;
function textToVoice(per, text){
	jQuery.ajax({
	    url : baseUrl + "qiniu/textToVoice",
	    type : "POST",
	    data : "text=" + text + "&per=" + per,
	    beforeSend : function(){
	    },
	    complete : function(){
	    },
	    success : function(e){
	    	if(e.code != 0){
	    		return;
	    	}
	    	if(audio != null) audio.pause();
	    	
            audio = new Audio();
            audio.src = picUrl + e.msg;
            audio.play();
	    }
	});
}

function printTable(str) {
    var orderhtml = "";
    if (document.getElementById(str)) { orderhtml = document.getElementById(str).outerHTML; }
    else orderhtml = str;
    /* 创建iframe */
    var headobj = document.getElementsByTagName("head").item(0); //提取head  
    printFrame = document.getElementById("lldxi_printRegionFrame_2012_0112");
    if (printFrame) { document.body.removeChild(printFrame); }
    printFrame = document.createElement("iframe");
    printFrame.setAttribute("src", "about:blank");
    printFrame.setAttribute("id", "lldxi_printRegionFrame_2012_0112");
    printFrame.setAttribute("marginheight", "0");
    printFrame.setAttribute("marginwidth", "0");
    printFrame.style.display = "none";
    document.body.appendChild(printFrame);
    if (window.ActiveXObject)//ie
    {
        var htmlobj = printFrame.contentWindow.document.createElement("html"); var bodyobj = printFrame.contentWindow.document.createElement("body");
        bodyobj.innerHTML = orderhtml; htmlobj.appendChild(headobj.cloneNode(true)); htmlobj.appendChild(bodyobj);
        printFrame.contentWindow.document.appendChild(htmlobj); printFrame.contentWindow.document.execCommand("Print", true);
    }
    else {
        var htmlstr = "<html>" + headobj.outerHTML + "<body>" + orderhtml + "<script type=\"text/javascript\">window.print();<\/script><\/body>" + "<\/html>";
        printFrame.contentWindow.document.write(htmlstr);
    }
}

var  extNotIe = (function() {
	var uri = 'data:application/vnd.ms-excel;base64,'
	    , template = '<html xmlns:o="urn:schemas-microsoft-com:office:office" xmlns:x="urn:schemas-microsoft-com:office:excel" xmlns="http://www.w3.org/TR/REC-html40"><head><!--[if gte mso 9]><xml><x:ExcelWorkbook><x:ExcelWorksheets><x:ExcelWorksheet><x:Name>{worksheet}</x:Name><x:WorksheetOptions><x:DisplayGridlines/></x:WorksheetOptions></x:ExcelWorksheet></x:ExcelWorksheets></x:ExcelWorkbook></xml><![endif]--></head><body><table>{table}</table></body></html>'
	    , base64 = function(s) { return window.btoa(unescape(encodeURIComponent(s))) }
	    , format = function(s, c) { return s.replace(/{(\w+)}/g, function(m, p) { return c[p]; }) }
	  return function(table, name) {
	    if (!table.nodeType) table = document.getElementById(table)
	    var ctx = {worksheet: name || 'Worksheet', table: table.innerHTML}
	    window.location.href = uri + base64(format(template, ctx))
	  }
})()

function exportTable(tableId){
	if (!!window.ActiveXObject || "ActiveXObject" in window){
		alert('请使用其他浏览器进行导出!');
	}
	else {
		extNotIe(tableId);
	}
}