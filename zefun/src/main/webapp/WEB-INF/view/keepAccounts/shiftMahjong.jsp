<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/emploee.css" type="text/css" />
<style>
.emploee_right_ul li{text-align:center;line-height:65px;font-size:20px;position:relative;width:120px;height:65px}
.emploee_right_ul .active{color:#ff0000;border-top:1px solid #e7dede;box-shadow:0 8px 8px #ccc}
.emploee_right_ul li img{position:absolute;right:2px;top:2px;width:16px;}
.emploee_right{margin-left:0;width:998px;overflow-x:overlay;height:100px}
.emploee_right_ul{margin-left:0}
::-webkit-scrollbar{height:8px}
.drag_content{height:600px}
.open_state {
    position: absolute;
    top: 134px;
    right: 10px;}
    
    .open_state em {
    display: inline-block;
    width: 14px;
    height: 14px;
    margin-right: 5px;
    position: relative;
    top: 3px;
}
.open_state span{display:inline-block;margin-right:8px}                                                                                                                                                                        
</style>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right clearfix'>
			     <div class="emploee_head clearfix" style="position:relative">
			      <div style="overflow:hidden;margin-right:-30px" class="clearfix">	   	
				    <div class="emploee_right">
					   <ul class="emploee_right_ul clearfix">
						   
					   </ul>
				    </div>
				</div>	
			 <div class="open_state">
			   <span><em style="background:#21d9db"></em>空闲</span>
			   <span><em style="background:#e11e23"></em>工作</span>
			   <span><em style="background:#e7a3ef"></em>点客</span>
			   <span><em style="background:#eede9f"></em>暂休</span>
			 </div>
					<div class="adjust"><span class="adjust_"><img src="<%=basePath%>images/adjust.png">调整轮牌</span><span class="adjust_button"><button class="save1" onclick="saveUpdateOrder()">保存</button><button onclick = "caltUpdate()">取消</button></span><em class="adjust_text">点击按钮，然后拖动任务卡片，即可调整排序～</em></div>
				<div class="drag_content">	
				
				 <div class="example">
			        <ul class="gridly" style="height: 156px;">
			     
			        </ul>
			    </div>
			  </div>
				 </div>
				
			    
			</div>
		</div>
    </div>
  </div>
	
	<div class="zzc1" name = "zzcDIV">
	  <div class="adjust_list_">
	     <p class="adjust_1">上牌<span class="close_1" onclick="canclezzc()"><img src="<%=basePath%>images/close_1.png"></p>
	     <div class="emplee_content_1_">
		    <ul class="emplee_content_2 clearfix">
		    
			</ul>
		 <button class="emplee_sure" onclick="upStateEmployee()">确定</button>
	    </div>
	   
	</div>
	</div>
</body>
<script src='<%=basePath %>js/common/jquery.gridly.js' type='text/javascript'></script>
<script>
    
	var shiftMahjongDtoListStr = '${shiftMahjongDtoListStr}';
    var shiftMahjongDtoList = eval("(" + shiftMahjongDtoListStr + ")");
    
    //抖动拖拽
   function drag(){ 
     jQuery(function() {
       jQuery( ".gridly" ).sortable({
         revert: true
       });
      
       jQuery( ".gridly, .brick" ).disableSelection();
     });
    } 
   
   jQuery('.free li').click(function(){
  	   var html=jQuery(this).html();
  	   jQuery(this).parents('.state').find('.select_').html(html);
  	   jQuery('.free').hide();
  	 
   })
    
    //选中头部红色
    //模拟下拉
    jQuery('.emploee_right_ul').delegate("li", "click", function(){
    	jQuery(this).siblings().removeClass("active");
        jQuery(this).addClass('active');
    });
    
   jQuery('.emploee_ul li').click(function(){
   	jQuery(this).siblings().removeClass("active");
       jQuery(this).addClass('active');
   });
   	
</script>
<script type="text/javascript" src="<%=basePath %>js/keepAccounts/shiftMahjong.js"></script><%-- ?1=<%=new Date().getTime() %> --%>
</html>