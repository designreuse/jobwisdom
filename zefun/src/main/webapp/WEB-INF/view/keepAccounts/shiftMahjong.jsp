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
					  <c:forEach items="${deptList}" var="dept" varStatus="status">
	                    <c:if test="${status.index == 0}">
	                        <li class="active" onclick="updateDept(this,${dept.deptId})">
			                    <span>${dept.deptName}</span>
			                </li>
	                    </c:if>
	                    <c:if test="${status.index != 0}">
	                        <li onclick="updateDept(this,${dept.deptId})">
			                    <span>${dept.deptName}</span>
			                </li>
	                    </c:if>
	                </c:forEach>
			        </ul>		
				    <div class="emploee_right">
					  <span class="left_click"><img src="<%=basePath%>images/left_click.png"></span>
					   <ul class="emploee_right_ul clearfix">
						   
					   </ul>
					   <span class="right_click"><img src="<%=basePath%>images/right_click.png"></span>
				    </div>
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


  <div class="zzc">
	  <div class="adjust_list">
	     <p class="adjust_1">调整轮牌</p>
	     <div class="emplee_content_1">
		    <div class="emplee_name"><span>轮牌名称：<span><input type="text" name="shiftMahjongName" placeholder="最多5个字"></div>
			<div class="emplee_rule">
			   <span><i class="rule_1">轮牌规则：</i><em><input type="radio" name="shiftMahjongRule" value="1">指定不动牌</em><em><input type="radio" name="shiftMahjongRule" value="2">指定动牌</em></span>
			   <span><i class="rule_1">上牌规则：</i><em><input type="radio" name="shiftMahjongUp" value="1">考勤上牌</em><em><input type="radio" name="shiftMahjongUp" value="2">持续上牌</em></span>
			   <span><i class="rule_1">离开规则：</i><em><input type="radio" name="nature" value="1">离开不轮牌</em><em><input type="radio" name="nature" value="2">离开轮牌</em></span>
			
			
			</div>
			
			<div class="emplee_job">
			<p>请选择改排位下的排班岗位：</p>
			<div class="emplee_job_content">
			
			  <div class="emplee_job_content_">

			  </div>
			  	 
			</div>
		</div>
	     <div class="emplee_save_">
		   <button>取消</button>
		   <button id= "confirm">确认</button>
		</div>
	  </div>
	
	</div>
	</div>
	
	<div class="zzc1">
	  <div class="adjust_list_">
	     <p class="adjust_1">上牌<span class="close_1"><img src="<%=basePath%>images/close_1.png"></p>
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