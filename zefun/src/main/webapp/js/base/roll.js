   jQuery(function(){
     var count=jQuery('.pic').size();
     var now=0;
	 //向右走
      jQuery('.RightBotton').click(function(){
         if(now<=count-5){
		    now+=4;
	        jQuery('.pic').stop(true,true).animate({
		     left:-227*now
		   
		       }) 
		  }	
        		
	    });
		//向左走
	 jQuery('.LeftBotton').click(function(){
         if(now>=1){
		    now-=4;
	        jQuery('.pic').stop(true,true).animate({
		     left:-227*now
		   
		       }) 
		  }	
        		
	    });
  })