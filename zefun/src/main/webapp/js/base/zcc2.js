/** 200*200 */
jQuery('#clipArea2').photoClip({
	width: width,
	height: height,
	file: "#file2",
	view: "#view2",
	ok: "#clipBtn2",
	loadStart: function() {
		console.log("照片读取中");
	},
	loadComplete: function() {
		console.log("照片读取完成");
	},
	clipFinish: function(dataURL) {
		zccCallback2(dataURL)
	}
});