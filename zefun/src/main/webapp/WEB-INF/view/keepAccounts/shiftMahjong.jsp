<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp" %>
<link rel="stylesheet" href="<%=basePath%>css/emploee.css" type="text/css" />
<link href='<%=basePath%>css/jquery.gridly.css' rel='stylesheet' type='text/css'>
<link href='<%=basePath%>css/sample.css' rel='stylesheet' type='text/css'>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
		<%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
			<div class='content_right clearfix'>
			        <div  class="emploee-left">
			            <c:forEach items="${deptList}" var="dept" varStatus="status">
		                    <c:if test="${status.index == 0}">
				                <div class="emploee-part active" onclick="updateDept(this,${dept.deptId})">
							         <div class="emploee_head">
									    <img src="<%=basePath%>images/bumen.jpg">
									 
									 </div>
									 <p><img src="<%=basePath%>images/partment.png"><span>${dept.deptName}</span></p>
							   
							    </div>
		                    </c:if>
		                    <c:if test="${status.index != 0}">
		                        <div class="emploee-part" onclick="updateDept(this,${dept.deptId})">
							         <div class="emploee_head">
									    <img src="<%=basePath%>images/bumen.jpg">
									 
									 </div>
									 <p><img src="<%=basePath%>images/partment.png"><span>${dept.deptName}</span></p>
							   
							    </div>
		                    </c:if>
		                </c:forEach>
					
					</div>
			    	<div>
						<div  class="emploee-right">
						
						 <div class="emplee_content_1">
						    <div class="emplee_name">轮牌名称：<input type="text" name="shiftMahjongName" placeholder="轮牌名称"><span style="color : red">*最多五个字符</span></div>
							<div class="emplee_rule">
							   <span>轮牌规则：<em><input type="radio" name="shiftMahjongRule" value="1">指定不动牌</em><em><input type="radio" name="shiftMahjongRule" value="2">指定动牌</em></span>
							   <span>上牌规则：<em><input type="radio" name="shiftMahjongUp" value="1">考勤上牌</em><em><input type="radio" name="shiftMahjongUp" value="2">持续上牌</em></span>
							   <span>离开规则：<em><input type="radio" name="nature" value="1">离开不轮牌</em><em><input type="radio" name="nature" value="2">离开轮牌</em></span>
							</div>
							
							<div class="emplee_job">
							<p>请选择改排位下的排班岗位：</p>
							<div class="emplee_job_content">
							  <div class="emplee_job_content_">

							  </div>
							</div>
						</div>	
						   <button class="emplee_save_">保存</button>
						</div>
						
						
						
						<button class="setting-update">修改轮牌</button>
						<button class="setting-up">调整顺序</button>
						<div class="emplee_roll clearfix" id = "lunpaiDIV">
							 <ul class="roll_left">
							     <li class="active">烫染师牌</li>
								 <li>洗护牌</li>
						         <li>洗剪吹设计</li>
							     <li>烫染设计师</li>
							 </ul>
						
						  	 <div class="roll_">
							      <span class="roll_left_click"><img src="<%=basePath%>images/left_click.png"></span>
							      <div class="roll_content"> 	  
									  <div class='example'>
								        <ul class='gridly'>
								          <li class='brick small'>
								            
										    <div class="roll_pic">
											   <img src="assets/images/head_pic.png">
											</div>
											<p>1189 <em>欧老板</em></p>
											<div class="state">
											 <i>1</i> <img src="assets/images/roll4.png">空闲中
											</div>
										
								          </li>
								          <li class='brick small'>
								            
										    <div class="roll_pic">
											   <img src="assets/images/head_pic.png">
											</div>
											<p>1189 <em>欧老板</em></p>
												<div class="state">
											 <i>1</i> <img src="assets/images/roll4.png">空闲中
											</div>
										
								          </li>
								          <li class='brick small'>
								            
										    <div class="roll_pic">
											   <img src="assets/images/head_pic.png">
											</div>
											<p>1189 <em>欧老板</em></p>
												<div class="state">
											 <i>1</i> <img src="assets/images/roll4.png">空闲中
											</div>
										
								          </li>
								           <li class='brick small'>
								            
										    <div class="roll_pic">
											   <img src="assets/images/head_pic.png">
											</div>
											<p>1189 <em>欧老板</em></p>
									        <div class="state">
											 <i>1</i> <img src="assets/images/roll4.png">空闲中
											</div>
										
								          </li>
								          <li class='brick small'>
								            
										    <div class="roll_pic">
											   <img src="assets/images/head_pic.png">
											</div>
											<p>1189 <em>欧老板</em></p>
											<div class="state">
											 <i>1</i> <img src="assets/images/roll4.png">空闲中
											</div>
										
								          </li>
								         <li class='brick small'>
								            
										    <div class="roll_pic">
											   <img src="assets/images/head_pic.png">
											</div>
											<p>1189 <em>欧老板</em></p>
											<div class="state">
											 <i>1</i> <img src="assets/images/roll4.png">空闲中
											</div>
										
								          </li>
								     
								        </ul>
								    </div>
								  </div> 
							     <span class="roll_right_click"><img src="<%=basePath%>images/right_click.png"></span>
							  </div>
						</div>
						
					  <!-- <div class="emplee_button">	
						<button>设置</button>
						<button>编辑</button>
					  </div> -->
						</div>
						
						
					</div>
			</div>
		</div>
    </div>
  </div>

</body>
<script src='<%=basePath %>js/common/jquery.gridly.js' type='text/javascript'></script>
<script src='<%=basePath %>js/common/sample.js' type='text/javascript'></script>
<script src='<%=basePath %>js/common/rainbow.js' type='text/javascript'></script>
<script>

    var deptId = "${deptId}";
	var deptListStr = '${deptListJson}';
    var deptDtoList = eval("(" + deptListStr + ")");
    
	jQuery(function(){
	     var now_=0, count=jQuery('.roll_content .gridly li').size();
		 
	  //向右走
      jQuery('.roll_right_click').click(function(){
         if(now_<=count-5){
		    now_+=1;
	        jQuery(this).parent('.roll_').find('.gridly').stop(true,true).animate({
		       left:-322*now_
		   
		       }) 
			  }
		  });
	  //向左走
	  
	  	//向左走
	 jQuery('.roll_left_click').click(function(){
         if(now_>=1){
		    now_-=1;
	         jQuery(this).parent('.roll_').find('.gridly').stop(true,true).animate({
		     left:-322*now_
		   
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