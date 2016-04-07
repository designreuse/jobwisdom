<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>岗位设置</title>
<%@include file="/css.jsp" %>
<style type="text/css">

.appoints {
	width: 55px;
	border-radius: 0px;
	height: 40px;
	border-color: #EEEEEE;
	border-right: 1px solid #c6c6c6;
	border-top: 1px solid #c6c6c6;
	background: #fff;
	border-bottom: 1px solid #c6c6c6;
}

.btn-group>.sign{
    position: absolute;
    margin-left: -15px;
    margin-top: 13px;
}

.btn-group > .btn:hover,
.btn-group-vertical > .btn:hover,
.btn-group > .btn:focus,
.btn-group-vertical > .btn:focus,
.btn-group > .btn:active,
.btn-group-vertical > .btn:active,
.btn-group > .btn.active,
.btn-group-vertical > .btn.active {
  z-index: 0;
}
</style>
</head>
<body>
  <%@include file="/head.jsp" %> 
  <div class="container">
    <%@include file="/sidebar.jsp" %>
    <div id="content" class="fl">
 		<div class="content-submenu">
		    <ul class="submunu clearfix">
		        <li >
		            <a href="index.html">
		                <span>散客收银</span>
		            </a>
		        </li>
		        <li>
		            <a href="vip.html">
		                <span>会员消费</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>新增会员</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>自助账单</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>账单查询</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>顾客预约</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>动态轮牌</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>收支记账</span>
		            </a>
		
		        </li>
		        <li class="active">
		            <a href="project_setting.html">
		                <span>项目设置</span>
		            </a>
		        </li>
		    </ul>
		</div>
	    <div class="box live-tab setting-content">
		    <ul class="tab_menu">
		        <li class="current">岗位设置</li>
		        <li id="employeePage">员工设置</li>
		    </ul>
		    <div class="tab_box">
		        <!--岗位设置-->
		        <div class="hair-setting post-setting">
		            <div class="add-classify">
		                <table class="table" id ="postMessageTableId">
		                    <caption id="postCaptionId"> <span style="display: inline-block;">大类设置</span><div class="plus" style="float: right;margin-right: 10px;" data-toggle="modal" data-target="#create-new-staff" id="addPost"><img src="<%=cssBasePath %>app/img/head/plus-white.png" alt=""/></div></caption>
		                    <tbody id="postMessageTbody">
			                    <c:forEach var="postMessage" items="${postMessage}" varStatus="status">
			                     <tr id='postMessageTR' <c:if test="${postMessageId == postMessage.postMessageId }">class="active"</c:if>>
	                                <td class='file' postMessageId = "${postMessage.postMessageId}">${postMessage.postMessageNo}</td>
	                                <td class='name'>${postMessage.postMessageName}</td>
	                                <td class='alter' id='postMessageAlterId' data-toggle="modal" data-target="#create-new-staff"><img src='<%=cssBasePath %>app/img/alter.png' alt=''/></td>
	                                <td class='delete' id='postMessageDeleteId'><img src='<%=cssBasePath %>app/img/delete.png' alt=''/></td>
	                              </tr>
		    	               </c:forEach>
		                    </tbody>
		                </table>
		            </div>
		            <div class="add-project add-post">
		                <table class="table middle-table">
		                    <thead>
		                        <tr class="table-title">
		                            <td class="name" colspan="5">子项设置</td>
		                            <td class="add-staff-btn" data-toggle="modal" data-target="#create-new-post"><div class="add">添加职位</div></td>
		                        </tr>
		                        <tr class="column-name">
		                            <td class="name">职位名称</td>
		                            <td class="proportion">指定比例</td>
		                            <td class="money">指定金额</td>
		                            <td class="non-proportion">非指定比例</td>
		                            <td class="non-money">非指定金额</td>
		                            <td class="operation"></td>
		                        </tr>
		                    </thead>
		                    <tbody id="positionTbody">
		                       <c:forEach var="position" items="${position}" varStatus="status">
			    		          <tr id='positionTR'>
			    		            <td id='positionNameTD' positionId = "${position.positionId}">${position.positionName}</td>
			                        <td id='appointScaleTD'>${position.appointScale}</td>
			                        <td id='appointMoneyTD'>${position.appointMoney}</td>
			                        <td id='nonAppointScaleTD'>${position.nonAppointScale}</td>
			                        <td id="nonAppointMoneyTD">${position.nonAppointMoney}</td>
			                        <td style="text-align: right;padding-right: 10px;" id='srcTD'><img id="positionAlter" src="<%=cssBasePath %>app/img/alter.png" data-toggle="modal" data-target="#create-new-post" alt="" class="img-left"/><img id="positionDelete" src="<%=cssBasePath %>app/img/delete.png" alt=""/></td>
			    	              </tr>
			    	          </c:forEach>
		                    </tbody>
		                </table>
		            </div>
		        </div>
		    </div>
		</div>

	</div>
  </div>
    <!--新建岗位的模态框-->
	<div class="modal fade" id="create-new-staff" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content create-new-post" style="height: 235px">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">创建新岗位</h4>
	            </div>
	            <div class="modal-body" id="postMessageBody">
	                    <div class="f-group">
	                        
	                        <label for="">岗位编号</label>
	                        <div class="btn-group dropdown" supdiv = "supdiv" class="whole-line">
				               <button type="button" class="btn" data-toggle="dropdown" id = "postMessageNo" supname = "supname" style="width: 206px;height: 30px;background-color: white;border-color: #C7C4C4;text-align: left;padding-left: 10px;">
				               </button>
				            <ul class="dropdown-menu" style="width:206px;">
				            </ul>
				           </div>
	                    </div>
	                    <div class="f-group">
	                        <label for="">岗位名称</label>
	                        <input type="text" name="postMessageName" class="whole-line"/>
	                        <input type='hidden' name = 'postMessageId'>
	                    </div>
	                    <div class="f-button" style="margin-top: 20px;">
	                      <button type="button" class="btn btn-default cancel" data-dismiss="modal" id="postCancel">取消</button>
	                      <button type="button" class="btn btn-primary confirm" data-dismiss="modal" id="postSave">确定</button>
	                    </div>
	            </div>
	        </div>
	    </div>
	</div>

	<!--新建职位的模态框-->
	<div class="modal fade" id="create-new-post" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content create-new-post">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h4 class="modal-title" id="myModalLabel">创建新职位</h4>
	            </div>
	            <div class="modal-body" id="positionBody">
	                    <input type="hidden" name="positionId" class="whole-line"/>
	                    <div class="f-group">
	                        <label for="">职位名称</label>
	                        <input type="text" name="positionName" class="whole-line"/>
	                    </div>
	                    <div class="hint">
	                        <div class="diamonds"></div><span class="word">提成标准设置</span>
	                    </div>
	                    <div class="f-group">
	                        <label for="">指定</label>
	                        <input type="text" class="input1" placeholder="比例" name="appointScale"/>
	                        <input type="text" class="input2" placeholder="金额" name="appointMoney"/>
	                    </div>
	                    <div class="f-group">
	                        <label for="">非指定</label>
	                        <input type="text" class="input1" placeholder="比例" name="nonAppointScale"/>
	                        <input type="text" class="input2" placeholder="金额" name="nonAppointMoney"/>
	                    </div>
	                    <div class="f-button">
	                      <button type="button" class="btn btn-default cancel" data-dismiss="modal" id="positionCancel">取消</button>
	                      <button type="button" class="btn btn-primary confirm" id="positionSave">确定</button>
	                    </div>
	            </div>
	        </div>
	    </div>
	</div>
  <%@ include file="/js.jsp"%>
  <script>
    $(".return").on("click",function(){
        $(".add-staff").addClass("hide");
        $(".staff-setting").removeClass("hide");
    });
</script>
  <script type="text/javascript">
  	var baseUrl = "<%=cssBasePath%>";
  	var employeeList ="";
  </script>
  <script type="text/javascript" src="<%=cssBasePath %>js/staff_employee_setting/staff_employee_setting.js"></script>
</body>
</html>
