/*
 * 作者 hyl
 * qq:623186518
 * 该列子仅供参考
 */
var mySwiper1;
var mySwiper;
function goLocation(i){
	mySwiper.swipeTo(i, 300, function(){});
	setClass(i);
}
zepto(document).ready(function(){
 mySwiper = new Swiper('.swiper-container', {
			pagination : '.pagination',
			paginationClickable : true
			,
		});
mySwiper1 = new Swiper('.swiper-container1', {
			pagination : '.pagination',
			paginationClickable : true,
			slidesPerView : 5
});
mySwiper.params.onSlideNext = function() {
	var index = mySwiper.activeIndex;
	mySwiper1.swipeTo(index, 300, function() {
			});
	var slidleft = zepto("#slide" + index).offset().left;
	zepto(".bar").offset({
				left : slidleft
			});
	setClass(index);
	// alert(slidleft);
}
mySwiper.params.onSlidePrev = function() {
	var index = mySwiper.activeIndex;
	mySwiper1.swipeTo(index, 300, function() {
			});
	var slidleft = zepto("#slide" + index).offset().left;
	zepto(".bar").offset({
				left : slidleft
			});
	setClass(index);
}
  zepto("div[name='title']").each(function(index, el) {
		zepto(el).click(function(){
			goLocation(index);
			var slidleft = zepto("#slide" + index).offset().left;
			zepto(".bar").offset({
						left : slidleft
					});
		});
	});
});

/*--------------------socroll---------------------------------------------------*/

document.addEventListener('touchmove', function(event) { 

	if (event.targetTouches.length == 1) { 
	var touch = event.targetTouches[0]; 

	obj.style.left = touch.pageX + 'px'; 
	obj.style.top = touch.pageY + 'px'; 
	} 
	}, false);
	document.addEventListener('touchmove', function(e) {
				e.preventDefault();
}, false);

/*--------------------socroll-----------------------------------------------------*/
function goto(url) {
	window.location.href = url;
}
function setClass(i) {
	zepto("div[name='title']").each(function(index, el) {
				if (index != i) {
					if (zepto(el).hasClass("cuurent")) {
						zepto(el).removeClass("cuurent");
					}
				} else {
					zepto(el).addClass("cuurent");
				}
			});

}
