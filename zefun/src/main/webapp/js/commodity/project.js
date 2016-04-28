jQuery(function(){
	
	jQuery('.tab_content_div:gt(0)').hide()
	jQuery('.right_head li').click(function(){
		var step = jQuery(this).attr("step");
		if (step > (projectStep+1)){
			dialog("请先完成前面的步骤吧");
			return;
		}
		if (step <= (projectStep)){
			status = 1;
		}else{
			status = 0;
		}
		stepNum = step;
		jQuery(this).addClass('active').siblings().removeClass('active');
		jQuery('.tab_content_div').eq(jQuery(this).index()).show().siblings().hide();
	});
	
})


//会员种类
jQuery(function(){
	
	jQuery('.write_3').click(function(){
		jQuery('.cash').show();
		jQuery(this).hide();
	
	});	
})


//职位
 jQuery(function(){
	 jQuery('.write_4').click(function(){
	     jQuery('.step_1').fadeIn('1000');
		 jQuery(this).hide();
	});
}) 

/**
 * 改变radio的样式
 * @param id
 */
function chkRadio(opt) {
	if (jQuery(this).val() == "1"){
		opt.checked = false;
		jQuery(this).val("0");
	}
	else {
		opt.checked = true;
		jQuery(this).val("1");
	}
}

//图片滚动列表 mengjia 070816
var Speed = 1; //速度(毫秒)
var Space = 6; //每次移动(px)
var PageWidth = 910; //翻页宽度
var fill = 0; //整体移位
var MoveLock = false;
var MoveTimeObj;
var Comp = 0;
var AutoPlayObj = null;
GetObj('ISL_Cont').scrollLeft = fill;
GetObj("ISL_Cont").onmouseover = function(){clearInterval(AutoPlayObj);}
GetObj("ISL_Cont").onmouseout = function(){AutoPlay();}
AutoPlay();
function GetObj(objName){if(document.getElementById){return eval('document.getElementById("'+objName+'")')}else{return eval('document.all.'+objName)}}

function ISL_GoUp(){ //上翻开始
 if(MoveLock) return;
 clearInterval(AutoPlayObj);
 MoveLock = true;
 MoveTimeObj = setInterval('ISL_ScrUp();',Speed);
}
function ISL_StopUp(){ //上翻停止
 clearInterval(MoveTimeObj);
 if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0){
  Comp = fill - (GetObj('ISL_Cont').scrollLeft % PageWidth);
  CompScr();
 }else{
  MoveLock = false;
 }
 AutoPlay();
}
function ISL_ScrUp(){ //上翻动作
 if(GetObj('ISL_Cont').scrollLeft <= 0){GetObj('ISL_Cont').scrollLeft = GetObj('ISL_Cont').scrollLeft + GetObj('List1').offsetWidth}
 GetObj('ISL_Cont').scrollLeft -= Space ;
}
function ISL_GoDown(){ //下翻
 clearInterval(MoveTimeObj);
 if(MoveLock) return;
 clearInterval(AutoPlayObj);
 MoveLock = true;
 ISL_ScrDown();
 MoveTimeObj = setInterval('ISL_ScrDown()',Speed);
}

function ISL_StopDown(){ //下翻停止
 clearInterval(MoveTimeObj);
 if(GetObj('ISL_Cont').scrollLeft % PageWidth - fill != 0 ){
  Comp = PageWidth - GetObj('ISL_Cont').scrollLeft % PageWidth + fill;
  CompScr();
 }else{
  MoveLock = false;
 }
 AutoPlay();
}

function ISL_ScrDown(){ //下翻动作
 if(GetObj('ISL_Cont').scrollLeft >= GetObj('List1').scrollWidth){GetObj('ISL_Cont').scrollLeft = GetObj('ISL_Cont').scrollLeft - GetObj('List1').scrollWidth;}
 GetObj('ISL_Cont').scrollLeft += Space ;
}
function CompScr(){
 var num;
 if(Comp == 0){MoveLock = false;return;}
 if(Comp < 0){ //上翻
  if(Comp < -Space){
   Comp += Space;
   num = Space;
  }else{
   num = -Comp;
   Comp = 0;
  }
  GetObj('ISL_Cont').scrollLeft -= num;
  setTimeout('CompScr()',Speed);
 }else{ //下翻
  if(Comp > Space){
   Comp -= Space;
   num = Space;
  }else{
   num = Comp;
   Comp = 0;
  }
  GetObj('ISL_Cont').scrollLeft += num;
  setTimeout('CompScr()',Speed);
 }
}

