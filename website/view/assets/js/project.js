jQuery(function(){
	
	jQuery('.tab_content_div:gt(0)').hide()
	
	
	jQuery('.right_head li').click(function(){
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

//会员种类中弹窗选择后添加内容
jQuery(function(){
	
	 jQuery('.save_').click(function(){
		var selected=jQuery(".select_ option:selected").val(),
		   shopprice=jQuery('.shopprice').val(),
		   vip=jQuery('.vip').val(),
		   vipcoin=jQuery('.vipcoin').val();
		 jQuery('.cash').hide();
		 jQuery('.write_3').show();
		var table=jQuery('.tab_content_div').find('.table_');
		table.append('<tr><td>'+selected+'</td><td>'+shopprice+'</td><td>'+vip+'</td><td>'+vipcoin+'</td></tr>')
	});
})


//职位
 jQuery(function(){
	 jQuery('.write_4').click(function(){
	     jQuery('.step_1').fadeIn('1000');
		 jQuery(this).hide();
		 jQuery('.cut_step').change(function(){
			 jQuery('.step_2').remove();
			   var selected=jQuery(".cut_step option:selected").val();
			  
			   
			   var cut_change='<table class="step_2"><tr><td style="color:#afb0b6;border-bottom: 1px solid #dbdce2!important;    border-right: 1px solid #dbdce2!important;">职位名称</td><td style="color:#afb0b6">提成方式</td><td style="color:#afb0b6">指定提成</td><td style="color:#afb0b6">非指定提成</td><td style="color:#afb0b6">预约奖励</td><td style="color:#afb0b6">职位名称</td></tr> <tr><td style="color:#afb0b6;border-right: 1px solid #dbdce2!important;"><select class="cut_step_6" ><option>111</option><option>111</option></select></td><td><select class="cut_step_7"><option>111</option><option>111</option></select></td><td><select class="cut_step_8"><option>111</option><option>111</option></select></td><td><select class="cut_step_9"><option>111</option><option>111</option></select></td><td><select class="cut_step_10"><option>111</option><option>111</option></select></td><td><select class="cut_step_11"><option>111</option><option>111</option></select></td></tr></table>';
			 
			  jQuery('.step_1').append(cut_change);
			  jQuery('.button_1').show();
		 })
	
	});
}) 

//保存
 jQuery(function(){
	 jQuery('.button_1').click(function(){
		  jQuery('.editor').remove();
		 var selected0=jQuery(".first").text(),
		
		 selected=jQuery(".cut_step option:selected").val(),
		 selected1=jQuery(".cut_step_1").val(),
		 selected2=jQuery(".cut_step_2 option:selected").val(),
		 selected3=jQuery(".cut_step_3").val(),
		 selected4=jQuery(".cut_step_4 input[type='radio']:checked").val(),
		 selected5=jQuery(".cut_step_6 option:selected").val(),
		 selected6=jQuery(".cut_step_7 option:selected").val(),
		 selected7=jQuery(".cut_step_8 option:selected").val(),
		 selected8=jQuery(".cut_step_9 option:selected").val(),
		 selected9=jQuery(".cut_step_10 option:selected").val(),
		 selected10=jQuery(".cut_step_11 option:selected").val()
		
	     jQuery('.table_s').append('<tr ><td rowspan="2" style="border-right: 1px solid #dbdce2!important;box-shadow: 0 0 10px #ccc;"><span style="position:relative;top:50%">'+selected0+'</span></td><td>'+selected+'</td><td>'+selected1+'</td><td>'+selected2+'</td><td>'+selected3+'</td><td>'+selected4+'</td><td>'+selected5+'</td><td>'+selected6+'</td><td>'+selected7+'</td><td>'+selected8+'</td><td>'+selected9+'</td></tr><tr><td>'+selected+'</td><td>'+selected1+'</td><td>'+selected2+'</td><td>'+selected3+'</td><td>'+selected4+'</td><td>'+selected5+'</td><td>'+selected6+'</td><td>'+selected7+'</td><td>'+selected8+'</td><td>'+selected9+'</td></tr>');
		 jQuery('.step_1').hide();
		 jQuery(this).hide();
		 jQuery('.write_4').show();
		 jQuery('.edi').show();
		 var new1='<td class="editor">编辑</td>';
		 jQuery('.table_s tr').append(new1);
		 
	});
	
	jQuery(document).on('click', '.editor', function(e) {
		
      jQuery(this).parent('tr').remove();
      jQuery('.step_1').show();
	  jQuery('.write_4').hide();
   
});
	
}) 


//左边栏
jQuery(function(){
	jQuery('.left_nav_2 li').hover(function(){
		jQuery(this).addClass('active').siblings().removeClass('active');
		
		
		
	})
	
	
	
})



    var flag = true;
   
    function chkRadio(id) {
       id.checked = flag;
       flag = !flag;
	    if(jQuery('#rType').val()=='1'){
			
			jQuery('#rType').val('0')
		}
	  

      else{
	  jQuery('#rType').val('1')
      }
   }
   
  
 //获取焦点失去焦点  
jQuery(function(){
    
	jQuery('.input_').focus(function(){
	  if(jQuery(this).val()=='搜索'){
        jQuery(this).val('')
		}
	})
	
	
	jQuery('.input_').blur(function(){
	  if(jQuery(this).val()==''){
		jQuery(this).val('搜索')
	  }
    })
	
})
    
	
	
jQuery(function(){
	jQuery('.left_nav_2 ul:gt(0)').hide();
    jQuery('.left_nav li').click(function(){
		jQuery(this).addClass('active1').siblings().removeClass('active1');
		
		jQuery('.left_nav_2 ul').eq(jQuery(this).index()).show().siblings().hide();
		
		
		
	});
		
		
})