jQuery('#clipArea').photoClip({
	width: cssWidth,
	height: cssHeight,
	file: "#file",
	view: "#view",
	ok: "#clipBtn",
	loadStart: function() {
		console.log("照片读取中");
	},
	loadComplete: function() {
		console.log("照片读取完成");
	},
	clipFinish: function(dataURL) {
		zccCallback(dataURL)
	}
});