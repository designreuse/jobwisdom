var dropObj = new Object();
$(function(){
	initialize();
});

$(document).delegate("a[supa='supa']","mouseover",function(e){
	  e = e ? e : window.e; 
	  var obj = e.srcElement ? e.srcElement : e.target;
	  $(obj).parent().parent().parent().find(".dropmenus").attr({class:""});
	  $(obj).attr({class:"dropmenus"});
});

$(document).delegate("a[supa='supa']","click",function(e){
	  e = e ? e : window.e; 
	  var obj = e.srcElement ? e.srcElement : e.target;
	  if($(obj).parents("div[supdiv='supdiv']").find(".dropmenus").text() != ""){
  		if($(obj).parents("div[supdiv='supdiv']").find("input[type='hidden']").attr("name") != null){
	  		  $(obj).parents("div[supdiv='supdiv']").find("input[type='hidden']").val($(obj).parents("div[supdiv='supdiv']").find(".dropmenus").attr("heddenValue"));
	  	}
    	if($(obj).parents("div[supdiv='supdiv']").find("button[supname='supname']").attr("supname") != null){
    		$(obj).parents("div[supdiv='supdiv']").find("button[supname='supname']").text($(obj).parents("div[supdiv='supdiv']").find(".dropmenus").text());
    	}
    	if($(obj).parents("div[supdiv='supdiv']").find("a[supname='supname']").attr("supname") != null){
    		if($(obj).parents("div[supdiv='supdiv']").find("input[dropdowninput='dropdowninput']").attr("dropdowninput") != null){
    			$(obj).parents("div[supdiv='supdiv']").find("input[dropdowninput='dropdowninput']").val($(obj).parents("div[supdiv='supdiv']").find(".dropmenus").attr("shows"));
    			$(obj).parents("div[supdiv='supdiv']").attr({class:"btn-group dropdown"});
	    	}else{
	    		$(obj).parents("div[supdiv='supdiv']").find("a[supname='supname']").text($(obj).parents("div[supdiv='supdiv']").find(".dropmenus").text());
	    	}
    	}
  	}
});

function initialize(){
	var su = $("button[supname='supname']");
    for(var i=0;i < su.length;i++){
    	var ou = su[i];
    	var supa = $(ou).parent().find("a[supa='supa']");
    	for(var j=0;j < supa.length;j++){
    		var oupa = supa[j];
    		if($(ou).text() == $(oupa).text()){
    			$(oupa).attr({class:"dropmenus"});
        	}
    	}
    }
    
    var au = $("a[supname='supname']");
    for(var i=0;i < au.length;i++){
    	var ou = au[i];
    	var supa = $(ou).parent().find("a[supa='supa']");
    	for(var j=0;j < supa.length;j++){
    		var oupa = supa[j];
    		if($(ou).text() == $(oupa).text()){
    			$(oupa).attr({class:"dropmenus"});
        	}
    	}
    }
}


/**
 * @param obj 数据对象
 * @param columns 显示列
 * @param value 取值列(默认第一个是显示，第二个是隐藏)只有一个默认为显示
 * @returns
 */
function assembleData(obj,columns,values,dropInp){
	var liTL = "";
	var arr = columns.split(",");
	var varr = values.split(",");
	$.each(obj,function(i,item){
		var shows = "";
		for(var i = 0;i < arr.length;i++){
			if(i == 0){
				shows = item[arr[i]];
			}else{
				shows = shows + "  "+ item[arr[i]];
			}
		}
		var texts = item[varr[0]];
		var heddens = item[varr[1]];
		if(i == 0){
			liTL = "<li><a href='javascript://' shows ='"+texts+"' heddenValue = '"+heddens+"' supa = 'supa'>"+shows+"</a></li>";
		}else{
			liTL = liTL + "<li class='divider'></li><li><a href='javascript://' shows ='"+texts+"' heddenValue = '"+heddens+"' supa = 'supa'>"+shows+"</a></li>";
		}
	});
	var tempArray = new Array();
	for(var j=0;j < dropInp.length;j++){
		var liTlObj = $(liTL);
		dropObj[dropInp[j].attr("name")] = liTlObj;
	}
	
}

function supkeydown(e,focusElement){
	
	if (e && e.keyCode == 13) {// enter 键
	    if("supdiv" == $(focusElement).parents("div[supdiv='supdiv']").attr("supdiv")){
	    	if($(focusElement).parents("div[supdiv='supdiv']").find(".dropmenus").text() != null){
	    		if($(focusElement).parents("div[supdiv='supdiv']").find("input[type='hidden']").attr("name") != null){
	  	  		  $(focusElement).parents("div[supdiv='supdiv']").find("input[type='hidden']").val($(focusElement).parents("div[supdiv='supdiv']").find(".dropmenus").attr("heddenValue"));
	  	  	    }
	  	    	if($(focusElement).parents("div[supdiv='supdiv']").find("button[supname='supname']").attr("supname") != null){
	  	    		$(focusElement).parents("div[supdiv='supdiv']").find("button[supname='supname']").text($(focusElement).parents("div[supdiv='supdiv']").find(".dropmenus").text());
	  	    		$(focusElement).parents("div[supdiv='supdiv']").removeClass(open);
	  	    	}
	  	    	if($(focusElement).parents("div[supdiv='supdiv']").find("a[supname='supname']").attr("supname") != null){
	  	    		if($(focusElement).parents("div[supdiv='supdiv']").find("input[dropdowninput='dropdowninput']").attr("dropdowninput") != null){
	  	    			$(focusElement).parents("div[supdiv='supdiv']").find("input[dropdowninput='dropdowninput']").val($(focusElement).parents("div[supdiv='supdiv']").find(".dropmenus").attr("shows"));
	  	    			$(focusElement).parents("div[supdiv='supdiv']").attr({class:"btn-group dropdown"});
	  	    			$(focusElement).parents("div[supdiv='supdiv']").removeClass(open);
	  		    	}else{
	  		    		$(focusElement).parents("div[supdiv='supdiv']").find("a[supname='supname']").text($(focusElement).parents("div[supdiv='supdiv']").find(".dropmenus").text());
	  		    		$(focusElement).parents("div[supdiv='supdiv']").removeClass(open);
	  		    	}
	  	    	}
	    	}
	    }
	}
	
	if (e && e.keyCode == 38) { // 向上箭头
		if("supdiv" == $(focusElement).parents("div[supdiv = 'supdiv']").attr("supdiv")){
			if($(focusElement).parents("div[supdiv = 'supdiv']").find(".dropmenus").css("display") == "none"){
    			$(focusElement).parents("div[supdiv = 'supdiv']").find(".dropmenus").attr({class:""});
    		}
			var supaDiv = $(focusElement).parents("div[supdiv = 'supdiv']").find("a[supa='supa']");
			var texts = $(focusElement).parents("div[supdiv = 'supdiv']").find(".dropmenus").text();
			
			var supa = new Array();
			for(var i=0;i < supaDiv.length;i++){
				if($(supaDiv[i]).css("display") != "none"){
					supa.push($(supaDiv[i]));
				}
			}
			
			if(texts != ""){
				for(var j=0;j < supa.length;j++){
					if(texts == $(supa[j]).text()){
						
						var ul = $(supa[j]).parent().parent();
						//2.获取Ul的高度
						var ulHeight = $(supa[j]).parent().parent().height();
						//3.如果当前被选项的y坐标已超出ul高度，进行滚动
						if($(supa[j]).parent().position().top < (2 *  $(supa[j]).parent().height()) - 15) {
							ul.scrollTop(ul.scrollTop() - ulHeight);
						}						
						if(j == 0){
							$(supa[supa.length -1]).attr({class:"dropmenus"});
							$(supa[j]).attr({class:""});
							ul.scrollTop($(supa[supa.length -1]).parent().position().top);
						}else{
							$(supa[j-1]).attr({class:"dropmenus"});
							$(supa[j]).attr({class:""});
						}
						break;
					}
				}
			}else{
				$(supa[supa.length-1]).attr({class:"dropmenus"});
				$(supa[supa.length-1]).parent().parent().scrollTop($(supa[supa.length -1]).parent().position().top);
			}
		}
	}
	
    if (e && e.keyCode == 40) { // 向下箭头
    	if("supdiv" == $(focusElement).parents("div[supdiv = 'supdiv']").attr("supdiv")){
    		if($(focusElement).parents("div[supdiv = 'supdiv']").find(".dropmenus").css("display") == "none"){
    			$(focusElement).parents("div[supdiv = 'supdiv']").find(".dropmenus").attr({class:""});
    		}
			var supaDiv = $(focusElement).parents("div[supdiv = 'supdiv']").find("a[supa='supa']");
			var texts = $(focusElement).parents("div[supdiv = 'supdiv']").find(".dropmenus").text();
			var supa = new Array();
			for(var i=0;i < supaDiv.length;i++){
				if($(supaDiv[i]).css("display") != "none"){
					supa.push($(supaDiv[i]));
				}
			}
			if(texts != ""){
				for(var j=0;j < supa.length;j++){
					if(texts == $(supa[j]).text()){
						var ul = $(supa[j]).parent().parent();
						//2.获取Ul的高度
						var ulHeight = $(supa[j]).parent().parent().height();
						//3.如果当前被选项的y坐标已超出ul高度，进行滚动
						if($(supa[j]).parent().position().top > ulHeight - (2 *  $(supa[j]).parent().height()) + 10) {
							ul.scrollTop(ul.scrollTop() + ulHeight);
						}
						
						
						
						if(j == Number(supa.length -1)){
							$(supa[0]).attr({class:"dropmenus"});
							$(supa[j]).attr({class:""});
							ul.scrollTop(0);
						}else{
							$(supa[j+1]).attr({class:"dropmenus"});
							$(supa[j]).attr({class:""});
						}
						break;
					}
				}
			}else{
				$(supa[0]).attr({class:"dropmenus"});
			}
		}
	}
}


$(document).delegate("input[dropdowninput='dropdowninput']",'click', function(e){
    e = e ? e : window.e; 
	var obj = e.srcElement ? e.srcElement : e.target; 
	if($(obj).parents("div[supdiv='supdiv']").find(".dropdown-menu").html() == ""){
		$(obj).parents("div[supdiv='supdiv']").find(".dropdown-menu").append(dropObj[$(obj).attr("name")]);
	}else{
		var liArr = $(obj).parents("div[supdiv='supdiv']").find("a[supa='supa']");
		for (var i = 0; i < liArr.length; i++) {
			var v = liArr[i].text;
			if(v.indexOf(obj.value) < 0){
				$(liArr[i]).hide();
				$(liArr[i]).attr({class:""});
				$(liArr[i]).parent().next().hide();
				
			}else{
				if(v == $(obj).attr("shows")){
					$(liArr[i]).attr({class:""});	
				}
				$(liArr[i]).show();
				$(liArr[i]).parent().next().show();
			}
		}
	}
});

$(document).delegate("input[dropdowninput='dropdowninput']",'keyup', function(e){
    e = e ? e : window.e; 
	var obj = e.srcElement ? e.srcElement : e.target; 
	var liArr = $(obj).parents("div[supdiv='supdiv']").find("a[supa='supa']");
	for (var i = 0; i < liArr.length; i++) {
		var v = liArr[i].text;
		if(v.indexOf(obj.value) < 0){
			$(liArr[i]).hide();
			$(liArr[i]).parent().next().hide();
		}else{
			$(liArr[i]).show();
			$(liArr[i]).parent().next().show();
		}
	}
});

$(document).delegate("input[dropdowninput='dropdowninput']",'blur', function(e){
    e = e ? e : window.e; 
	var obj = e.srcElement ? e.srcElement : e.target; 
	var liArr = $(obj).parents("div[supdiv='supdiv']").find("a[supa='supa']");
	for (var i = 0; i < liArr.length; i++) {
		var v = $(liArr[i]).attr("shows");
		if(v == obj.value){
			$(liArr[i]).hide();
			$(liArr[i]).parent().next().hide();
		}else{
			$(liArr[i]).show();
			$(liArr[i]).parent().next().show();
		}
	}
});

$(document).delegate("a[supa='supa']","mouseover",function(e){
	  e = e ? e : window.e; 
	  var obj = e.srcElement ? e.srcElement : e.target;
	  $(obj).parents("div[supdiv='supdiv']").find(".dropmenus").attr({class:""});
	  $(obj).attr({class:"dropmenus"});
 });
