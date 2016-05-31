<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/payroll_6.css" type="text/css" />
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
   <div class="leftpanel" style="height: 840px; margin-left: 0px;">
    <%@ include file="/menu.jsp" %>
    
    <div class="rightpanel" style="margin-left: 200px;">
    <%@ include file="/top.jsp" %>
      <!--headerpanel-->

<div class='content_right clearfix'>
   <div class="content_right_pull_mould">
    <div class="pull_mould">
	   <button class="pull_mould_down" id="downLondimport">导入模板下载</button>
	   <button class="pull_mould_" data-toggle="modal" data-target="#toLeadModal">导入</button>
	</div>
	
	<div class="pull_mould_content">
	   <div class="pull_mould_images clearfix">
	       <span>部门</span>
	       <span>岗位</span>
		   <span>职位</span>
	   </div>
	   
	   <div class="pull_mould_content_part clearfix">
	     <div class="part_1" id = "deptDIV">
		    <c:forEach items="${list}" var="listlist" varStatus="deptIndex">
		        <div class="hair_part <c:if test="${deptIndex.index == 0 }">active</c:if>" deptId = "${listlist.deptId}">
			      <i name = "nameValue" onclick="chooseDept(${listlist.deptId}, this)">${listlist.deptCode}&nbsp;&nbsp;&nbsp;&nbsp;${listlist.deptName}</i>
				  <em><img src="<%=basePath%>images/pull_down.png"></em>
				  <ul class="part_ul">
					  <li onclick="updatedept('${listlist.deptId}','${listlist.deptName}','${listlist.deptCode}','${listlist.isResults}',this)"><img src="<%=basePath%>images/handle_1.png"></li>
					  <li onclick="deletedept(${listlist.deptId},this)"><img src="<%=basePath%>images/handle_2.png"></li>
				   </ul>
			    </div>
		    </c:forEach>
			<div class="part_add" id="search-member" onclick="addDept()"><img src="<%=basePath%>images/money_add.png"></div>
		 </div>
		 
		   <div class="part_1" id = "positionDIV">
		     <c:forEach items="${list}" var="listlist"  varStatus="deptIndex">
		        <c:if test="${deptIndex.index == 0 }">
		            <c:forEach items="${listlist.positionInfo}" var="list" varStatus="positionIndex">
		                <div class="hair_part <c:if test="${positionIndex.index == 0 }">active</c:if>" positionId = "${list.positionId}">
					      <i name = "nameValue" onclick="choosePosition(${listlist.deptId}, ${list.positionId}, this)">${list.positionCode}&nbsp;&nbsp;&nbsp;&nbsp;${list.positionName}</i>
						  <em><img src="<%=basePath%>images/pull_down.png"></em>
						  <ul class="part_ul">
							  <li onclick="updateposition('${listlist.deptId}','${list.positionName}','${list.positionCode}','${list.positionId}','${list.isDept}',this)"><img src="<%=basePath%>images/handle_1.png"></li>
							  <li onclick="deleteposition(${list.positionId})"><img src="<%=basePath%>images/handle_2.png"></li>
						   </ul>
					    </div>
		            </c:forEach>
	            </c:if>
		    </c:forEach>
		    <div class="part_add" id="search-member" onclick="addPosition(this)"><img src="<%=basePath%>images/money_add.png"></div>
		 </div>
		 
		   <div class="part_1" style="position:relative;left:10px" id = "employeeLeveDIV">
		   <c:forEach items="${list}" var="listlist"  varStatus="deptIndex">
		        <c:if test="${deptIndex.index == 0 }">
		            <c:forEach items="${listlist.positionInfo}" var="list" varStatus="positionIndex">
		                 <c:if test="${positionIndex.index == 0 }">
		                     <c:forEach items="${list.employeeLeve}" var="listchild" >
		                         <div class="hair_part">
							         ${listchild.levelName}
								  <em><img src="<%=basePath%>images/pull_down.png"></em>
								  <ul class="part_ul">
									  <li onclick="openupdatelevel('${listchild.levelId}','${list.positionId}','${listchild.levelName}')"><img src="<%=basePath%>images/handle_1.png"></li>
									  <li onclick="deletelevel(${listchild.levelId})"><img src="<%=basePath%>images/handle_2.png"></li>
								   </ul>
							    </div>
		                     </c:forEach>
		                 </c:if>
		            </c:forEach>
	            </c:if>
		    </c:forEach>
			<div class="part_add" onclick="addEmployeeLeve(this)"><img src="<%=basePath%>images/money_add.png"></div>
		 </div>
	   
	   
	   
	   </div>
	</div>
   </div>		
 </div>

<div class="modal hide" id="add-bumen-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content add-bumen-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="bumenxinzeng" style="display: none">添加部门</h4><h4 class="modal-title" id="bumenxiugai" style="display: none">修改部门</h4>
            </div>
            <div class="modal-body" style="overflow:hidden">
                <form action="" class="editprofileform" method="post">
                    <div>
                    <input type="hidden" id="updatedeptId">
                        <select name="" id="deptCode" class="mr10 chzn-select input-medium" style="z-index: 10000;">
                            <option value="">部门编码</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                        </select>

                        <input type="text" placeholder="部门名称" id="deptName" class="mr10 ml10 input-medium"/>

                        <span>产生业绩：</span>
                        <input type="checkbox" id="isResults" class="lcs_check" checked autocomplete="on" />

                    </div>
                    <div class="modal-btn-group">
                        <a class="btn modal-cancel" href="#" onclick="quxiaoadddept()">取消</a>
                        <a class="btn btn-primary modal-confirm w80" href="javascript:addsavedept()" id="bumen1" >保存</a>
                        <a class="btn btn-primary modal-confirm w80" href="javascript:editsavedept()" id="bumen2" >保存</a>
                    </div>
                </form>
                <div class="modal-footer text-left">
                <div class="desc-title">
                    	设置说明
                </div>
                <div class="desc-content">
                    <p>1.对于拥有多部门的门店或连锁机构，设置部门编码可以方便您管理不同的业务部门，如果您的门店是专业店没有多个部门，请将部门编号设置为1.</p>
                    <p>2.某些部门属于非营业部门，请将部门设置为“不产生业绩”这样系统在生成营业报表的时候，将忽略这些部门.</p>
                </div>
            </div>
            </div><!--modal body-->
        </div>
    </div>
</div>

<!--添加岗位态框-->
<div class="modal hide" id="add-gangwei-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content add-gangwei-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="gangwei1" style="display: none">添加岗位</h4><h4 class="modal-title" id="gangwei2" style="display: none">修改岗位</h4>
            </div>
            <div class="modal-body"style="overflow:hidden">
                <form action="" class="editprofileform" method="post">
                    <div>
                    <input type="hidden" id="position_deptId">
                    <input type="hidden" id="positionId">
                        <select name="" id="positionCode" class="mr10 chzn-select input-medium">
                            <option value="">岗位编码</option>
                            <option value="1">1</option>
                            <option value="2">2</option>
                            <option value="3">3</option>
                            <option value="4">4</option>
                            <option value="5">5</option>
                            <option value="6">6</option>
                            <option value="7">7</option>
                            <option value="8">8</option>
                            <option value="9">9</option>
                        </select>

                        <input type="text" placeholder="岗位名称" id="positionName" class="mr10 ml10 input-medium"/>
                        <span>是否跨部门：</span>
                        <input type="checkbox" id="isDept" class="lcs_check lcs_check1" checked autocomplete="off" value="1"/>
                    </div>
                    <div class="modal-btn-group">
                        <a class="btn modal-cancel" href="#" onclick="quxiaoposition()">取消</a>
                        <a class="btn btn-primary modal-confirm w80" href="javascript:positionsave()" id="gangwei11" >保存</a>
                        <a class="btn btn-primary modal-confirm w80" href="javascript:positioneditsave()" id="gangwei22" >保存</a>
                    </div>
                </form>
                <div class="modal-footer text-left">
                <div class="desc-title">
                   	 设置说明
                </div>
                <div class="desc-content">
                    <p>1.设置岗位编码可以方便您同意管理该岗位的员工</p>
                    <p>2.设置跨部门后，该岗位下员工可以在其他部门轮牌中显示</p>
                    <p>3.设置目标后，该岗位下员工在目标设置中劳动业绩总体目标、指定业绩目标为次数或金额</p>
                </div>
            </div>
            </div><!--modal body-->
            
        </div>
    </div>
</div>

<!--添加职位-->
<!--添加新品牌模态框-->
<div class="modal hide" id="add-zhiwei-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document" >
        <div class="modal-content add-zhiwei-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="zhiwei1" style="display: none">添加职位</h4><h4 class="modal-title" id="zhiwei2" style="display: none">修改职位</h4>
            </div>
            <div class="modal-body"style="overflow:hidden">
                <div class="editprofileform" >
                    <div>
                    <input type="hidden" id="levelId">
                    <input type="hidden" id="zhiwei_positionid">
                    <%-- <select name="" id="zhiwei_positionid" class="mr10 chzn-select input-medium" readonly="readonly">
                     <option value="">选择岗位</option>
                    <c:forEach items="${positionlist}" var="positionlist">
                    <option value="${positionlist.positionId}">${positionlist.positionName}</option>
                    </c:forEach>
                    </select> --%>

                    <input type="text" placeholder="职位名称" id="levelName" class="mr10 ml10 input-medium"/>
                    </div>
                    <div class="modal-btn-group">
                        <a class="btn modal-cancel" href="#" onclick="quxiaozhiwei()">取消</a>
                        <a class="btn btn-primary modal-confirm w80" id="zhiwei11" style="display: none" href="javascript:levelsave()" >保存</a>
                        <a class="btn btn-primary modal-confirm w80" id="zhiwei22" style="display: none" href="javascript:updatelevel()" >保存</a>
                    </div>
                </div>
                <div class="modal-footer text-left">
                <div class="desc-title">
                                                                        设置说明
                </div>
                <div class="desc-content">
                    <p>1.您的门店将技师分为，初级技师，中级技师，高级技师。那么您只需要依次在技师岗位下添加以上三个职位。</p>
                </div>
            </div>
            </div><!--modal body-->
            
        </div>
    </div>
</div>

<div class="modal hide" id="toLeadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account" style="width: 450px;height: 180px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">上传文件</h5>
            </div>
            <div class="modal-body">
                <form action="<%=basePath %>objective/action/importExcle" class="editprofileform" method="post" enctype="multipart/form-data" id="excleForm">
                    <p>
                        <label>文件位置选择:</label>
                        <input type="file" name="filename" id="file" class="input-large" value="" readonly="readonly">
                        <!-- <a class="btn modal-cancel" href="#" id="chooseExcle">浏览</a> -->
<!--                         <input type="file" name="filevalue" class="input-large" value="" style="display: none" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
 -->                    </p>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn btn-primary modal-confirm" href="#" id="confirm" data-dismiss="modal" onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>
<script>

var listStr = '${listStr}';

var list = eval("(" + listStr + ")");

jQuery('.lcs_check').lc_switch('是', '否');


jQuery('body').delegate('.lcs_check', 'lcs-statuschange', function() {
    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
    if(status == 'checked'){
    	jQuery(this).val(1);
    }else{
    	jQuery(this).val(2);
    }
});
jQuery('.lcs_check1').lc_switch('是', '否');

jQuery('body').delegate('.lcs_check_type', 'lcs-statuschange', function() {
    var status = (jQuery(this).is(':checked')) ? 'checked' : 'unchecked';
    if(status == 'checked'){
    	jQuery(this).val(0);
    }else{
    	jQuery(this).val(1);
    }
});

jQuery(function(){
    //阻止事件冒泡的通用函数
    function stopBubble(e){
        // 如果传入了事件对象，那么就是非ie浏览器
        if(e&&e.stopPropagation){
            //因此它支持W3C的stopPropagation()方法
            e.stopPropagation();
        }else{
            //否则我们使用ie的方法来取消事件冒泡
            window.event.cancelBubble = true;
        }
    }

})


//是否切换
jQuery(function(){
   jQuery('.lcs_switch').click(function(){
      jQuery(this).toggleClass('lcs_off');
	 jQuery(this).toggleClass('lcs_on'); 
   });
})  



</script>
<script type="text/javascript" src="<%=basePath %>js/employee/positioninfo.js"></script>

    </div>
    <!--RIGHT PANEL结束 -->
  </div>

</div><!--mainwrapper-->

</body>
</html>