<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/emploee.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right clearfix'>
			     <div class="emploee_head clearfix">
			      <div style="overflow:hidden;margin-right:-30px" class="clearfix">	   
				   <ul class="clearfix emploee_ul">
			          <li class="active">美发部</li>
					  <li>美发部</li>
					  <li>烫染设计师</li>
					  <li>洗护牌</li>
			        </ul>		
				    <div class="emploee_right">
					  <span class="left_click"><img src="assets/images/left_click.png"></span>
					   <ul class="emploee_right_ul clearfix">
					     <li>
						   <div class="emploee_right_li">烫染师牌</div>
						   <span style="border-right:1px solid #ccc"><img src="assets/images/emploee_2.png"></span>
						   <span><img src="assets/images/emploee_3.png"></span>
						 </li>
						   <li>
						   <div class="emploee_right_li">烫染师牌</div>
						   <span style="border-right:1px solid #ccc"><img src="assets/images/emploee_2.png"></span>
						   <span><img src="assets/images/emploee_3.png"></span>
						 </li>
						   <li>
						   <div class="emploee_right_li">烫染师牌</div>
						   <span style="border-right:1px solid #ccc"><img src="assets/images/emploee_2.png"></span>
						   <span><img src="assets/images/emploee_3.png"></span>
						 </li>
						    <li>
						   <div class="emploee_right_li">烫染师牌</div>
						   <span style="border-right:1px solid #ccc"><img src="assets/images/emploee_2.png"></span>
						   <span><img src="assets/images/emploee_3.png"></span>
						 </li>
						   <li>
						   <div class="emploee_right_li">烫染师牌</div>
						   <span style="border-right:1px solid #ccc"><img src="assets/images/emploee_2.png"></span>
						   <span><img src="assets/images/emploee_3.png"></span>
						 </li>
						
						   <li>
					        <img src="assets/images/emploee_1.png">
						 </li>
					   </ul>
					   <span class="right_click"><img src="assets/images/right_click.png"></span>
				    </div>
				</div>	
					<div class="adjust"><span class="adjust_"><img src="assets/images/adjust.png">调整轮牌</span><span class="adjust_button"><button class="save1">保存</button><button>取消</button></span><em class="adjust_text">点击按钮，然后拖动任务卡片，即可调整排序～</em></div>
				<div class="drag_content">	
				
				 <div class="example">
			        <ul class="gridly" style="height: 156px;">
			       <li class="brick small">
			            
					    <div class="roll_pic">
						   <img src="assets/images/head_pic.png">
						</div>
						<p>1189 <em>欧老板</em></p>
						<div class="state">
						 <i>1</i><span class="line_"></span> 
						 <em style="position:relative"><span class="select_"> <em><img src="assets/images/pic1.png"></em> <span style="position:relative;left:-5px">空闲</span></span><em class="down"><img src="assets/images/down_.png"></em></em>
						  <ul class="free">
						        <li>
								  <em><img src="assets/images/emploee_6.png"></em> 空闲
								  
								</li>
								 <li> 
								  <em><img src="assets/images/emploee_5.png"></em> 工作
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_7.png"></em> 点客
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_8.png"></em> 下牌
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_9.png"></em> 暂休
			                    </li>
								
						   </ul>		
						 
						</div>
					
			          </li>
					<li class="brick small">
			            
					    <div class="roll_pic">
						   <img src="assets/images/head_pic.png">
						</div>
						<p>1189 <em>欧老板</em></p>
						<div class="state">
						 <i>1</i><span class="line_"></span> 
						 <em style="position:relative"><span class="select_"> <em><img src="assets/images/pic1.png"></em> <span style="position:relative;left:-5px">空闲</span></span><em class="down"><img src="assets/images/down_.png"></em></em>
						  <ul class="free">
						        <li>
								  <em><img src="assets/images/emploee_6.png"></em> 空闲
								  
								</li>
								 <li> 
								  <em><img src="assets/images/emploee_5.png"></em> 工作
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_7.png"></em> 点客
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_8.png"></em> 下牌
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_9.png"></em> 暂休
			                    </li>
								
						   </ul>		
						 
						</div>
					
			          </li>
					  
					  <li class="brick small">
			            
					    <div class="roll_pic">
						   <img src="assets/images/head_pic.png">
						</div>
						<p>1189 <em>欧老板</em></p>
						<div class="state">
						 <i>1</i><span class="line_"></span> 
						 <em style="position:relative"><span class="select_"> <em><img src="assets/images/pic1.png"></em> <span style="position:relative;left:-5px">空闲</span></span><em class="down"><img src="assets/images/down_.png"></em></em>
						  <ul class="free">
						        <li>
								  <em><img src="assets/images/emploee_6.png"></em> 空闲
								  
								</li>
								 <li> 
								  <em><img src="assets/images/emploee_5.png"></em> 工作
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_7.png"></em> 点客
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_8.png"></em> 下牌
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_9.png"></em> 暂休
			                    </li>
								
						   </ul>		
						 
						</div>
					
			          </li>
				<li class="brick small">
			            
					    <div class="roll_pic">
						   <img src="assets/images/head_pic.png">
						</div>
						<p>1189 <em>欧老板</em></p>
						<div class="state">
						 <i>1</i><span class="line_"></span> 
						 <em style="position:relative"><span class="select_"> <em><img src="assets/images/pic1.png"></em> <span style="position:relative;left:-5px">空闲</span></span><em class="down"><img src="assets/images/down_.png"></em></em>
						  <ul class="free">
						        <li>
								  <em><img src="assets/images/emploee_6.png"></em> 空闲
								  
								</li>
								 <li> 
								  <em><img src="assets/images/emploee_5.png"></em> 工作
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_7.png"></em> 点客
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_8.png"></em> 下牌
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_9.png"></em> 暂休
			                    </li>
								
						   </ul>		
						 
						</div>
					
			          </li>
					<li class="brick small">
			            
					    <div class="roll_pic">
						   <img src="assets/images/head_pic.png">
						</div>
						<p>1189 <em>欧老板</em></p>
						<div class="state">
						 <i>1</i><span class="line_"></span> 
						 <em style="position:relative"><span class="select_"> <em><img src="assets/images/pic1.png"></em> <span style="position:relative;left:-5px">空闲</span></span><em class="down"><img src="assets/images/down_.png"></em></em>
						  <ul class="free">
						        <li>
								  <em><img src="assets/images/emploee_6.png"></em> 空闲
								  
								</li>
								 <li> 
								  <em><img src="assets/images/emploee_5.png"></em> 工作
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_7.png"></em> 点客
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_8.png"></em> 下牌
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_9.png"></em> 暂休
			                    </li>
								
						   </ul>		
						 
						</div>
					
			          </li>
					  
					    <li class="brick small">
			            
					    <div class="roll_pic">
						   <img src="assets/images/head_pic.png">
						</div>
						<p>1189 <em>欧老板</em></p>
						<div class="state">
						 <i>1</i><span class="line_"></span> 
						 <em style="position:relative"><span class="select_"> <em><img src="assets/images/pic1.png"></em> <span style="position:relative;left:-5px">空闲</span></span><em class="down"><img src="assets/images/down_.png"></em></em>
						  <ul class="free">
						        <li>
								  <em><img src="assets/images/emploee_6.png"></em> 空闲
								  
								</li>
								 <li> 
								  <em><img src="assets/images/emploee_5.png"></em> 工作
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_7.png"></em> 点客
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_8.png"></em> 下牌
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_9.png"></em> 暂休
			                    </li>
								
						   </ul>		
						 
						</div>
					
			          </li>
					  
					   <li class="brick small">
			            
					    <div class="roll_pic">
						   <img src="assets/images/head_pic.png">
						</div>
						<p>1189 <em>欧老板</em></p>
						<div class="state">
						 <i>1</i><span class="line_"></span> 
						 <em style="position:relative"><span class="select_"> <em><img src="assets/images/pic1.png"></em> <span style="position:relative;left:-5px">空闲</span></span><em class="down"><img src="assets/images/down_.png"></em></em>
						  <ul class="free">
						        <li>
								  <em><img src="assets/images/emploee_6.png"></em> 空闲
								  
								</li>
								 <li> 
								  <em><img src="assets/images/emploee_5.png"></em> 工作
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_7.png"></em> 点客
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_8.png"></em> 下牌
			                    </li>
								 <li>
								  <em><img src="assets/images/emploee_9.png"></em> 暂休
			                    </li>
								
						   </ul>		
						 
						</div>
					
			          </li>
					    <li class="brick small">
			            
					      <img src="assets/images/emploee_10.png">
					
			          </li>
					    
			     
			        </ul>
			    </div>
			  </div>
				 </div>
				
			    
			</div>
		</div>
    </div>
  </div>

</body>
<script src='<%=basePath %>js/common/jquery.gridly.js' type='text/javascript'></script>
<script>

    var deptId = "${deptId}";
	var deptListStr = '${deptListJson}';
    var deptDtoList = eval("(" + deptListStr + ")");
    
  //抖动拖拽
    function drag(){ 
     jQuery(function() {
       jQuery( ".gridly" ).sortable({
         revert: true
       });
      
       jQuery( ".gridly, .brick" ).disableSelection();
     });
    } 

   jQuery(function(){
     jQuery('.adjust_').click(function(){
        jQuery('.gridly li').addClass('active');
   	  jQuery('.adjust_button').show();
   	  jQuery(this).hide();
   	  drag();
      });
      
   });

   //模拟下拉
   jQuery(function(){
     jQuery('.select_').click(function(){
        jQuery(this).parents('.state').find('.free').show();
   	 jQuery('.free li').click(function(){
   	   var html=jQuery(this).html();
   	   jQuery(this).parents('.state').find('.select_').html(html);
   	   jQuery('.free').hide();
   	 
   	 })
     
     });
    })
    
    //选中头部红色
    //模拟下拉
   jQuery(function(){
     jQuery('.emploee_right_ul li').click(function(){
        jQuery(this).addClass('active');
      });
    })
    
    
    //轮播
    
    	jQuery(function(){
   	     var now_=0, count=jQuery('.emploee_right_ul li').size();
   		 
   	  //向右走
         jQuery('.right_click').click(function(){
            if(now_<=count-5){
   		    now_+=1;
   	        jQuery(this).parent('').find('.emploee_right_ul').stop(true,true).animate({
   		       left:-200*now_
   		   
   		       }) 
   			  }
   		  });
   	  //向左走
   	  
   	  	//向左走
   	 jQuery('.left_click').click(function(){
            if(now_>=1){
   		    now_-=1;
   	         jQuery(this).parent('').find('.emploee_right_ul').stop(true,true).animate({
   		     left:-200*now_
   		   
   		     }) 
   		  }	
           		
   	  });
    });
   	
   	//弹出弹窗
   	jQuery(function(){
   	   
         jQuery('.setting-up').click(function(){
   	    jQuery('.zzc').show();
   		
   	  });
   	  
       jQuery('.emplee_cancle').click(function(){
   	
   	      jQuery('.zzc').hide();
   	 }) 
      });
</script>
<script type="text/javascript" src="<%=basePath %>js/keepAccounts/shiftMahjong.js"></script><%-- ?1=<%=new Date().getTime() %> --%>
</html>