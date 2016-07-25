<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <link rel="stylesheet" href="<%=basePath %>editor/themes/default/default.css" /> --%>
<link rel="stylesheet" href="<%=basePath %>css/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="<%=basePath %>css/payroll_2.css" type="text/css" />
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
<div class="leftpanel" style="height: 840px; margin-left: 0px;">
<%@ include file="/menu.jsp" %>
<%@ include file="/template/employeedetail.jsp" %>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/ueditor.all.min.js"> </script> 
<script type="text/javascript" charset="utf-8" src="<%=basePath %>UEditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>

<style>
.addImage {
	position: relative;
	left: -144px;
	top: 54px;
	z-index: 1000;
	width: 20px;
	height: 20px;
	text-align: center;
	line-height: 20px;
	display: inline-block;
	border: 1px solid #fafafa;
}

.addImage:hover {
	background-color: #fff5d4;
	border: 1px solid #dcac6c;
}
</style>
<head>
    <script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
    <script type="text/javascript">
    
    function editPage (imgUrl, size) {
    	jQuery("#altContent2").remove();
    	jQuery("#xiuxiuEditor").remove();
    	var html = '<div id="altContent2"><h1>美图秀秀2</h1></div>';
    	jQuery("#flashEditorOut").append(jQuery(html));
    	xiuxiu.setLaunchVars("cropPresets", size);
    	xiuxiu.embedSWF("altContent2", 5, 700, 500);
    	
    	xiuxiu.onInit = function (id)
    	{
            xiuxiu.setUploadType(3);
            if (imgUrl != null) {
            	xiuxiu.loadPhoto(qiniuUrl + imgUrl, false);
            }
    	}
    	xiuxiu.onSaveBase64Image = function (data, fileName, fileType, id)
    	{
    		data = "data:image/"+fileType+";base64," + data;
            zccCallback(data);
    	}
    	
    	xiuxiu.onDebug = function (data)
    	{
            dialog("网咯繁忙，请重试！");
    	}
    	
    	xiuxiu.onClose = function (id)
    	{
            jQuery(".mask").hide();
    	}
    	
    }
	
    </script>
</head>
  
   
    <!--RIGHT PANEL开始 -->
    <div class="rightpanel" style="margin-left: 200px;">
    <%@ include file="/top.jsp" %>
    
<div class='content_right clearfix'>
    <div class="payroll_2_head">
	   <button id="downLondimport">导出模板下载</button><button id="downLond">导出</button><button data-toggle="modal" data-target="#toLeadModal">导入</button><button onclick="jQuery('.zzc').show();employeeId=null;">添加员工</button>
	   <span class="payroll_1_search">
	     <input type="search" id="search" placeholder="员工姓名/工号/手机号"/>
         <input type="hidden" id="querygangwei">
         <!-- <button class="button-search btn width100 ml-10" onclick="changePage()">查询</button> -->
         <button class="button-search btn width100 ml-10" onclick="findAllByNameOrCodeOrPhone()">查询</button>
	   </span>
	</div>
	
	<div class="payroll_2_content clearfix">
	   <!-- <div class="payroll_2_left">
	     <div class="content_wrap">
	         <div class="zTreeDemoBackground left">
		       <ul id="treeDemo" class="ztree"></ul>
         	</div>
         </div>
	 
	   </div> -->
	   <div class="payroll_2_right">
	     <table class="payroll_table">
		    <tr>
			  <td>工号</td>
			  <td>姓名</td>
			  <td>性别</td>
			  <td>部门</td>
			  <td>岗位</td>
			  <td>职位</td>
			  <td>员工账号</td>
			  <td>手机号</td>
			  <td>操作</td>
			 
	       </tr>
	       <c:forEach items="${employeeDtoList }" var="employee" varStatus="index">
	            <tr id="${employee.employeeId}" name = "tables">
				  <td>${employee.employeeCode }</td>
				  <td>${employee.name }</td>
				  <td>${employee.sex }</td>
				  <td>${employee.deptName }</td>
				  <td>${employee.positionName }</td>
				  <td>${employee.levelName }</td>
				  <td>${employee.userName }</td>
				  <td>${employee.phone }</td>
				  <td><span onclick="selectEmp(${employee.employeeId})"><img src="<%=basePath %>images/handle_1.png" ></span><span onclick="deleteinfo(${employee.employeeId})"><img src="<%=basePath %>images/handle_2.png"></span></td>
		        </tr>
	       </c:forEach>
	    </table>
	   </div>
	</div>
 </div>
 

	<div class="zzc" style="display: none">
   <div class="emploee_information">
      <p>员工资料</p>
      <div class="emploee_content clearfix">  
        <div class="imformation_left"> 
		 <div class="clearfix">
		    <div class="head_img">
			  <img onclick="jQuery('.mask').show();editPage(null, '200*200');chooseType=0;" src="http://7xss26.com1.z0.glb.clouddn.com/jobwisdom/project/1468392992738">
			  <input type="hidden" name="headImage" value="">
			</div>
		    <div class="information">
			   <p> <span><em>姓名</em><input type="text" name="name"><i  class = "addcolor">*</i></span>
				   <span><em>工号</em><input type="text" value="" name="employeeCode"><i  class = "addcolor">*</i></span>
					<span><em>介绍人</em><select name="recommendId">
					   <option value="">选择介绍人</option>
					   <c:forEach items="${recommendList }" var="recommend">
					       <option value="${recommend.employeeId }">${recommend.name }</option>
					   </c:forEach>
					</select>
				</p> 
			   <p><span><em>性别</em><select name="sex"><option value="男">男</option><option value="女">女</option></select></span> 
				  <span><em>出生日期</em><input type="text" name="birthday" value="" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></span>
				  <span><em>手机号</em><input type="text" name="phone" value=""><i  class = "addcolor">*</i></span>
				</p> 
           
               <p> <span><em>身份证</em><input type="text" name="identityCard" value="" style="width:350px" ><i  class = "addcolor">*</i></span>
				 <span>是否显示在预约中<input type="checkbox" style="margin-left:10px"></span>
				</p>
			</div>  
		 </div>

		 <div class="select_job_content clearfix">
        <div class="select_job_content_left">   
          <p><em>角色</em><select name="roleId"><c:forEach items="${rolelist }" var="rolelist"><option value="${rolelist.roleId }">${rolelist.roleName }</option></c:forEach></select></p>    
  		  <p><em>选择岗位</em><select onchange="changeEmployeeLevel(this.value)" name="positionId"><c:forEach items="${positionlist }" var="position"><option value="${position.positionId }">${position.positionName }</option></c:forEach></select></p>
		  <p><em>职位</em><select name="levelId"></select></p>
		  <p><em>部门</em><select name="deptId"><c:forEach items="${deptlist }" var="dept"><option value="${dept.deptId }">${dept.deptName }</option></c:forEach></select></p>
		  <p><em>当前状态</em><select name="employeeStatus" ><option value="1">在职</option><option value="2">离职</option></select></p>
		  <p><em>到职日期</em><input type="text" value="" name="entryDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></p>
		  <p><em>离职日期</em><input type="text" value="" name="leaveDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"></p>
        </div> 
       
		<div class="select_job_content_right">
			<div class="clearfix">
			<span id="editImage" onclick="jQuery('.mask').show();editPage(null, '375*200');chooseType=1;" class="addImage" title="插入图片"> <img
				src="<%=basePath%>images/insert_img.png"
				style="position: relative; left: 1px; top: 1px">
			</span>
			<script id="editor1" type="text/plain"
				style="width: 505px; height: 210px; float: left"></script>
			</div>
		   </div>	
		</div>
	  <div class="imformation_button">
	     <button onclick="saveEmployee()">保存</button>
		 <button onclick="jQuery('.zzc').hide()">取消</button>
	  </div>
	 </div>
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
                   </p>
                </form>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn btn-primary modal-confirm" href="#" id="confirm" data-dismiss="modal" onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>


<!-- 美图秀秀 -->
<div class="mask" style="display: none;">
   <div id="flashEditorOut" style="position: relative;">
   <span class="mask_close" style="position:absolute;right:-5px;top:-5px"><img onclick="xiuxiu.onClose();" src="<%=basePath %>images/seo_close.png"></span>
        <div id="altContent2">
            <h1>美图秀秀2</h1>
        </div>
	</div>
</div>


<script type="text/javascript">
//获取加载页面时的页码信息
var pageNo = "${page.pageNo}";
var pageSize = "${page.pageSize}";
var totalPage = "${page.totalPage}"

var storeDtoListStr = '${storeDtoListStr}';
var storeDtoList= eval("("+storeDtoListStr+")");
	var setting = {
	    view: {
	        dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
	        showLine: true,//是否显示节点之间的连线
	        selectedMulti: false //设置是否允许同时选中多个节点
	    },
	    data: {
	        simpleData: {//简单数据模式
	            enable:true,
	            deptIdKey: "dateId",
	            dateKey: "dateType"
	        }
	    },
	    callback: {
	        beforeClick: function(treeId, treeNode) {
	            var dateId = treeNode.dateId;
	            var dateType = treeNode.dateType;
	            jQuery.ajax({
	        		type : "post",
	        		url : baseUrl + "employee/action/selectEmployeeBydateType",
	        		data : "dateId=" +dateId + "&dateType=" + dateType,
	        		dataType : "json",
	        		success : function(e){
	        			if(e.code != 0){
	        				dialog("系统繁忙！");
	        			}
	        			var list=e.msg;
	        			jQuery(".payroll_table").find("tr:gt(0)").remove();
	        			for (var i = 0; i < list.length; i++) {
	        				var employeeDto = list[i];
	        				jQuery(".payroll_table").append("<tr id='"+employeeDto.employeeId+"'>"+
										      				  "<td>"+employeeDto.employeeCode+"</td>"+
										    				  "<td>"+employeeDto.name+"</td>"+
										    				  "<td>"+employeeDto.sex+"</td>"+
										    				  "<td>"+employeeDto.deptName+"</td>"+
										    				  "<td>"+employeeDto.positionName+"</td>"+
										    				  "<td>"+employeeDto.levelName+"</td>"+
										    				  "<td>"+employeeDto.userName+"</td>"+
										    				  "<td>"+employeeDto.phone+"</td>"+
										    				  "<td><span onclick='selectEmp("+employeeDto.employeeId+")'><img src='"+baseUrl+"images/handle_1.png' ></span><span onclick='deleteinfo("+employeeDto.employeeId+")'><img src='"+baseUrl+"images/handle_2.png'></span></td>"+
										    		        "</tr>");
	        			}
	        		}
	        		
	        	});
	        }
	    }
	};

	var zNodes = storeDtoList;

	jQuery(document).ready(function(){
		jQuery.fn.zTree.init(jQuery("#treeDemo"), setting, zNodes);
	});
	


	//模拟用户点击
	jQuery(function(){
		for(i=0;i<=100;i++){
			
		jQuery('#treeDemo_1_span,#treeDemo_'+i+'_switch').click(function(){
			
				
		})
	 
		jQuery('#treeDemo_1_span,#treeDemo_'+i+'_switch').click()
		}
	})
	
	jQuery(function(){
        jQuery('.ztree> li>ul>li>ul>li>a>span').attr('style','background-position:-110px -16px');	  
	});
	
</script>
<script type="text/javascript" src="<%=basePath %>js/employee/employee.js"></script>
<script type="text/javascript" src="<%=basePath %>js/common/jquery.ztree.core-3.5.js"></script>
</div>
<!--RIGHT PANEL结束 -->
  </div>
</div>
</body>
<script>
var toolbars = {
		toolbars : [ [ 'fullscreen', 'source', '|', 'undo', 'redo', '|',
				'bold', 'italic', 'underline', 'fontborder', 'strikethrough',
				'superscript', '|', 'subscript', 'removeformat', 'formatmatch',
				'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor',
				'backcolor', 'insertorderedlist', 'insertunorderedlist',
				'selectall', 'cleardoc', '|', 'rowspacingtop',
				'rowspacingbottom', 'lineheight', '|', 'customstyle',
				'paragraph', 'fontfamily', 'fontsize', '|',
				'directionalityltr', 'directionalityrtl', 'indent', '|',
				'justifyleft', 'justifycenter', 'justifyright',
				'justifyjustify', '|', 'touppercase', 'tolowercase', 'preview' ] ],
		maximumWords : 3000,
		elementPathEnabled : false,
		imageScaleEnabled : false,
		wordCount : false,
		autoHeightEnabled : false
	};
	var u1 = UE.getEditor('editor1', toolbars);
	
var employeeLevels = eval('('+'${employeeLevels}'+')');
jQuery(function (){
	changeEmployeeLevel(jQuery("select[name='positionId']").val());
})
// 改变岗位等级
function changeEmployeeLevel(positionId){
	jQuery("select[name='levelId']").empty();
	for(var i=0;i<employeeLevels.length;i++){
		if (employeeLevels[i].positionId == positionId){
			var html = '<option value='+employeeLevels[i].levelId+'>'+employeeLevels[i].levelName+'</option>';
			jQuery("select[name='levelId']").append(jQuery(html));
		}
	}

	
	var roleName =  jQuery("select[name='positionId'] option:checked").text();
	if(roleName=="店长"){
		var html1 = '<option name="dz" value=""></option>';
		jQuery("select[name='levelId']").append(jQuery(html1));
		jQuery("select[name='deptId']").append(jQuery(html1));
		jQuery("select[name='levelId']").val("");
		jQuery("select[name='deptId']").val("");
		jQuery("select[name='levelId']").attr("disabled",true);
		jQuery("select[name='deptId']").attr("disabled",true);
	}
	else{
		jQuery("option[name='dz']").remove();
		jQuery("select[name='levelId']").attr("disabled",false);
		jQuery("select[name='deptId']").attr("disabled",false);
	}
}
var employeeId = null;
function saveEmployee(){
	var url = "employee/action/add";
	if (employeeId != null){
		var url = "employee/action/update";
	}
	var name = jQuery("input[name='name']").val();
	var employeeCode = jQuery("input[name='employeeCode']").val();
	var sex = jQuery("select[name='sex']").val();
	var birthday = jQuery("input[name='birthday']").val();
	var recommendId = jQuery("select[name='recommendId']").val();
	var phone = jQuery("input[name='phone']").val();
	var identityCard = jQuery("input[name='identityCard']").val();
	var roleId = jQuery("select[name='roleId']").val();
	var positionId = jQuery("select[name='positionId']").val();
	var deptId = jQuery("select[name='deptId']").val();
	var levelId = jQuery("select[name='levelId']").val();
	var employeeStatus = jQuery("select[name='employeeStatus']").val();
	var entryDate = jQuery("input[name='entryDate']").val();
	var leaveDate = jQuery("input[name='leaveDate']").val();
	var employeeDesc = u1.getContent();  
	var headImage = jQuery("input[name='headImage']").val();
	
	if(!checkMobile(phone)){
		dialog("电话号码格式不对");
		return ;
	}
	
	if(!isCardNo(identityCard)){
		dialog("身份证号码格式不对，请修改");
		return ;
	}
	
	var data = {"employeeId":employeeId, "employeeCode":employeeCode, "sex":sex, "birthday":birthday, "recommendId":recommendId, "phone":phone, "identityCard":identityCard, 
			    "roleId":roleId, "positionId":positionId, "deptId":deptId, "levelId":levelId, "employeeStatus":employeeStatus, "entryDate":entryDate, "leaveDate":leaveDate, 
			    "employeeDesc":employeeDesc, "name":name, "headImage":headImage};
	console.log(data);
	jQuery.ajax({
		type : "post",
		url : baseUrl + url,
		data : JSON.stringify(data),
		dataType : "json",
		contentType : "application/json",
		async : false,
		success : function(data) {
	
			if(data.code == -2){
				dialog("员工编码已经被人引用过了！");
			}
			else if(data.code == -2){
				dialog("员工账号已经被人引用过了！");
			}
			else{
				dialog("该人员配置成功");
				window.location.href = baseUrl +"employee/view/employee";
			}
		}
	});
}

function selectEmp(id){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/action/getdetail",
		data : "employeeId="+id,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog("系统繁忙！");
			}
			else {
				employeeId = id;
				jQuery("select[name='deptId']").val(e.msg.deptId);
				jQuery("select[name='levelId']").val(e.msg.levelId);
				jQuery("input[name='name']").val(e.msg.name);
				jQuery("input[name='employeeCode']").val(e.msg.employeeCode);
				jQuery("select[name='sex']").val(e.msg.sex);
				jQuery("input[name='birthday']").val(e.msg.birthday);
				jQuery("select[name='recommendId']").val(e.msg.recommendId);
				jQuery("input[name='phone']").val(e.msg.phone);
				jQuery("input[name='identityCard']").val(e.msg.identityCard);
				jQuery("select[name='roleId']").val(e.msg.roleId);
				jQuery("select[name='positionId']").val(e.msg.positionId);
				changeEmployeeLevel(e.msg.positionId);

				jQuery("select[name='employeeStatus']").val(e.msg.employeeStatus);
				jQuery("input[name='entryDate']").val(e.msg.entryDate);
				jQuery("input[name='leaveDate']").val(e.msg.leaveDate);
				u1.setContent(e.msg.employeeDesc);
				jQuery("input[name='headImage']").val(e.msg.headImage);
				jQuery("input[name='headImage']").prev().attr("src", qiniuUrl+e.msg.headImage);
			
				jQuery(".zzc").show();
			}
       }
	});
}

function checkMobile(str) {
   var re = /^1\d{10}$/
   if (re.test(str)) {
      return true;
   } else {
	   return false;
   }
}
jQuery("input[name='phone']").blur(function(){
	var p = jQuery("input[name='phone']").val();
	if(!checkMobile(p)){
		dialog("手机号码格式不对");
	}
})
	
jQuery("input[name='identityCard']").blur(function(){
	var p = jQuery("input[name='identityCard']").val();
	if(!isCardNo(p)){
		dialog("身份证号码格式不对，请修改");
	}
})


function isCardNo(card)  
{  
   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X  
   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/; 
   if (reg.test(card)) {
	    return true;
	   }
   else {
		return false;
   }
}  


jQuery(function() {
	jQuery('.add_pic_ img').click(function() {
		jQuery(".mask").show();
		editPage(null);
		imgObject = jQuery(this);
		type = 1;
	})
	jQuery('.cancelinput').click(function() {
		jQuery('.zzc.one').hide();
		jQuery('.photo-clip-rotateLayer').html('');
	})
	/* jQuery('#editImage').click(function() {
		type = 2;
		jQuery(".mask").show();
		editPage(null);
	}) */
	
	
})




</script>
</html>