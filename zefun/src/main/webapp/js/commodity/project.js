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

