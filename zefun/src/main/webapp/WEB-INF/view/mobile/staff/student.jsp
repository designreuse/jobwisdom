<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/base.jsp"%>
<html lang="en">
<link rel="stylesheet" href="<%=basePath%>css/mobile/vendorstyle.css" />
<link rel="stylesheet" href="" />
<script src="<%=basePath%>js/mobile/jquery-1.7.1.min.js"></script>
<script src="<%=basePath%>js/mobile/vendorscript.js"></script>
<script src="<%=basePath%>js/mobile/video.js"></script>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no, height = device-height">
<meta content="telephone=no" name="format-detection" />
<title>在线学习</title>
</head>
<style>
.videoContainer {
	width: 375px;
	height: 350px;
	position: relative;
	overflow: hidden;
	background: #000;
	color: #ccc;
}

.caption {
	display: none;
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	padding: 10px;
	color: #ccc;
	font-size: 20px;
	font-weight: bold;
	box-sizing: border-box;
	-ms-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	background: #1F1F1F; /* fallback */
	background: -moz-linear-gradient(top, #242424 50%, #1F1F1F 50%, #171717 100%);
	background: -webkit-linear-gradient(top, #242424 50%, #1F1F1F 50%, #171717 100%);
	background: -o-linear-gradient(top, #242424 50%, #1F1F1F 50%, #171717 100%);
}
.control {
	background: #333;
	color: #ccc;
	position: absolute;
	bottom: 0;
	left: 0;
	width: 100%;
	z-index: 5;
	display: none;
}
.topControl {
	height: 11px;
	border-bottom: 1px solid #404040;
	padding: 1px 5px;
	background: #1F1F1F; /* fallback */
	background: -moz-linear-gradient(top, #242424 50%, #1F1F1F 50%, #171717 100%);
	background: -webkit-linear-gradient(top, #242424 50%, #1F1F1F 50%, #171717 100%);
	background: -o-linear-gradient(top, #242424 50%, #1F1F1F 50%, #171717 100%);
}
.btmControl {
	clear: both;
	background: #1F1F1F; /* fallback */
	background: -moz-linear-gradient(top, #242424 50%, #1F1F1F 50%, #171717 100%);
	background: -webkit-linear-gradient(top, #242424 50%, #1F1F1F 50%, #171717 100%);
	background: -o-linear-gradient(top, #242424 50%, #1F1F1F 50%, #171717 100%);
}
.control div.btn {
	float: left;
	width: 34px;
	height: 30px;
	padding: 0 5px;
	border-right: 1px solid #404040;
	cursor: pointer;
}
.control div.text {
	font-size: 12px;
	font-weight: bold;
	line-height: 30px;
	text-align: center;
	font-family: verdana;
	width: 20px;
	border: none;
	color: #777;
}
.control div.btnPlay {
	background: url('/jobwisdom/images/mobile/employee/control.png') no-repeat 0 0;
	border-left: 1px solid #404040;
}
.control div.paused {
	background: url('/jobwisdom/images/mobile/employee/control.png') no-repeat 0 -30px;
}
.control div.btnStop {
	background: url('/jobwisdom/images/mobile/employee/control.png') no-repeat 0 -60px;
}
.control div.spdText {
	border: none;
	font-size: 14px;
	line-height: 30px;
	font-style: italic;
}
.control div.selected {
	font-size: 15px;
	color: #ccc;
}
.control div.sound {
	background: url('/jobwisdom/images/mobile/employee/control.png') no-repeat -88px -30px;
	border: none;
	float: right;
}

.control div.sound2 {
	background: url('/jobwisdom/images/mobile/employee/control.png') no-repeat -88px -60px !important;
}

.control div.muted {
	background: url('/jobwisdom/images/mobile/employee/control.png') no-repeat -88px 0 !important;
}

.control div.btnFS {
	background: url('/jobwisdom/images/mobile/employee/control.png') no-repeat -44px 0;
	float: right;
}

.control div.btnLight {
	background: url('/jobwisdom/images/mobile/employee/control.png') no-repeat -44px -60px;
	border-left: 1px solid #404040;
	float: right;
}

.control div.lighton {
	background: url('/jobwisdom/images/mobile/employee/control.png') no-repeat -44px -30px !important;
}

/* PROGRESS BAR CSS */
.progress {
	width: 85%;
	height: 10px;
	position: relative;
	float: left;
	cursor: pointer;
	background: #444; /* fallback */
	background: -moz-linear-gradient(top, #666, #333);
	background: -webkit-linear-gradient(top, #666, #333);
	background: -o-linear-gradient(top, #666, #333);
	box-shadow: 0 2px 3px #333 inset;
	-moz-box-shadow: 0 2px 3px #333 inset;
	-webkit-box-shadow: 0 2px 3px #333 inset;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
}

.progress span {
	height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	display: block;
	border-radius: 10px;
	-moz-border-radius: 10px;
	-webkit-border-radius: 10px;
}

.timeBar {
	z-index: 10;
	width: 0;
	background: #3FB7FC; /* fallback */
	background: -moz-linear-gradient(top, #A0DCFF 50%, #3FB7FC 50%, #16A9FF 100%);
	background: -webkit-linear-gradient(top, #A0DCFF 50%, #3FB7FC 50%, #16A9FF 100%);
	background: -o-linear-gradient(top, #A0DCFF 50%, #3FB7FC 50%, #16A9FF 100%);
	box-shadow: 0 0 1px #fff;
	-moz-box-shadow: 0 0 1px #fff;
	-webkit-box-shadow: 0 0 1px #fff;
}

.bufferBar {
	z-index: 5;
	width: 0;
	background: #777;
	background: -moz-linear-gradient(top, #999, #666);
	background: -webkit-linear-gradient(top, #999, #666);
	background: -o-linear-gradient(top, #999, #666);
	box-shadow: 2px 0 5px #333;
	-moz-box-shadow: 2px 0 5px #333;
	-webkit-box-shadow: 2px 0 5px #333;
}
/* time and duration */
.time {
	width: 15%;
	float: right;
	text-align: center;
	font-size: 11px;
	line-height: 12px;
}

/* VOLUME BAR CSS */
/* volume bar */
.volume {
	position: relative;
	cursor: pointer;
	width: 70px;
	height: 10px;
	float: right;
	margin-top: 10px;
	margin-right: 10px;
}

.volumeBar {
	display: block;
	height: 100%;
	position: absolute;
	top: 0;
	left: 0;
	background-color: #eee;
	z-index: 10;
}

/* OTHERS CSS */
/* video screen cover */
.loading, #init {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: url('/jobwisdom/images/mobile/employee/loading.gif') no-repeat 50% 50%;
	z-index: 2;
	display: none;
}

#init {
	background: url('/jobwisdom/images/mobile/employee/bigplay.png') no-repeat 50% 50% !important;
	cursor: pointer;
}
</style>
<body>

	<!-- Content -->
	<section id="wrapper" style="text-align: left;">

		<!-- Title -->
		<h2>在线学习视频</h2>
		<h3>Custom HTML5 Video Controls</h3>

		<div class="videoContainer">

			<video id="myVideo" controls preload="auto" poster="/jobwisdom/images/mobile/employee/poster.jpg" >
				<source src="http://www.jobwisdom.cn/store.mp4" type="video/mp4" />
				<p>Your browser does not support the video tag.</p>
			</video>
			<div class="caption">This is HTML5 video with custom controls</div>
			<div class="control">
				<div class="topControl">
					<div class="progress">
						<span class="bufferBar"></span> <span class="timeBar"></span>
					</div>
					<div class="time">
						<span class="current"></span> / <span class="duration"></span>
					</div>
				</div>
				<div class="btmControl">
					<div class="btnPlay btn" title="Play/Pause video"></div>
					<div class="btnStop btn" title="Stop video"></div>
					<div class="spdText btn">Speed:</div>
					<div class="btnx1 btn text selected" title="Normal speed">x1</div>
					<div class="btnx3 btn text" title="Fast forward x3">x3</div>
					<div class="btnFS btn" title="Switch to full screen"></div>
					<div class="btnLight lighton btn" title="Turn on/off light"></div>
					<div class="volume" title="Set volume">
						<span class="volumeBar" style="width: 60%!important;"></span>
					</div>
					<div class="sound sound2 btn" title="Mute/Unmute sound"></div>
				</div>

			</div>
			<div class="loading"></div>
		</div>
	</section>
</body>
</html>