String.prototype.replaceAll = function(s1,s2){ 
  return this.replace(new RegExp(s1,"gm"),s2); 
}
String.prototype.startWith=function(str){
  var reg=new RegExp("^"+str);
  return reg.test(this);
};

String.prototype.endWith=function(str){
  var reg=new RegExp(str+"$");
  return reg.test(this);
};

Array.prototype.removeByIndex=function(dx) 
{ 
    if(isNaN(dx)||dx>this.length){return false;} 
    for(var i=0,n=0;i<this.length;i++) 
    { 
        if(this[i]!=this[dx]) 
        { 
            this[n++]=this[i] 
        } 
    } 
    this.length-=1 
}

function formDataToObj(data){
	if(data == null || data == undefined || data.length == 0){
		return {};
	}
	var array = data.split("&");
	var obj = {};
	for (var i = 0; i < array.length; i++) {
		var kv = array[i].split("=");
		obj[kv[0]] = kv[1];
	}
	return obj;
}

function dialog(msg){
  var $wrap = $('<div class="alertWrap"></div>');
  var $content = $('<div class="alert"></div>');
  $content.html(msg);
  $wrap.append($content);
  $("body").append($wrap);

  var windowHeight = $(window).height();
  var pt = (windowHeight - 100)/2;

  $wrap.css("padding-top",pt).fadeIn(800);

  $wrap.click(function(){
    $wrap.fadeOut(800).remove();
  })

  setTimeout(function(){
    $wrap.fadeOut(800).remove();
    },1000);
}
function isEmpty(str) {
	if (str == null || typeof(str) == "undefined" || str.toString().trim() == '') {
		return true;
	}
	return false;
}