<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/base.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>后台管理</title>
</head>
<body>
	<!--头部页面引用-->
	<%@include file="/WEB-INF/view/topheader.jsp"%>
	<!--左侧菜单页面引用-->
	<%@include file="/WEB-INF/view/sliderbar.jsp"%>
	<div id="content">
    <div id="content-header">
        <div id="breadcrumb"> <a href="<%=basePath %>homepage/view/query" title="Go to Home" class="tip-bottom"><i class="icon-home"></i> 主页看板</a> 
        <a href="#" class="current">用户详情</a> </div>
        <!--<h1>新增用户</h1>-->
    </div>
    <div class="container-fluid">
        <hr>
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5>账号详情</h5>
                    </div>
                    <div class="widget-content nopadding">
                        <div class="form-horizontal" >

                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label" style="padding-top: 10px;">选择账号角色: </label>
                                    <div class="controls">${map.roleName}</div>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label" style="padding-top: 10px;">选择管理区域: </label>
                                    <div class="controls">${map.province}<div class="controls-x3">${map.city}</div><div class="controls-x3">${map.area}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label" style="padding-top: 10px;">公司名称: </label>
                                    <div class="controls">${map.companyName}</div>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label" style="padding-top: 10px;">公司地址: </label>
                                    <div class="controls">${map.addr}</div>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="span8">
                                    <label for="normal" class="control-label" style="padding-top: 10px;">真实姓名: </label>
                                    <div class="controls">${map.realName}</div>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="control-group">
                                    <div class="span4 fl">
                                        <label for="normal" class="control-label" style="padding-top: 10px;">联系电话: </label>
                                        <div class="controls">${map.fixedNum}</div>
                                    </div>
                                    <div class="span4 fl" style="margin-left: 2.927659574468085%;padding-top: 10px;">
                                    <label for="normal" class="control-label">手机号: </label>
                                    <div class="controls">${map.phone}</div>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="control-group">
                                    <div class="span4 fl">
                                        <label for="normal" class="control-label"style="padding-top: 10px;">后台账号: </label>
                                        <div class="controls">${map.userName}</div>
                                    </div>
                                    <div class="span4 fl" style="margin-left: 2.927659574468085%;padding-top: 10px;">
                                    <label for="normal" class="control-label" style="padding-top: 10px;">密码: </label>
                                    <div class="controls">******</div>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <button class="btn btn-success client-add-save" style="margin-right: 25%" onclick="updatePassword()">修改密码</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div></div>
    <!-- 修改密码Modal -->
<div id="updatepassword" class="modal hide" style="width: 500px">
    <div class="modal-header">
        <button data-dismiss="modal" class="close" type="button">×</button>
        <h5>修改密码  </h5>
    </div>
    <div class="modal-body nopadding">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box" style="margin: 0px;">
                    <div class="widget-content nopadding">
                        <table class="table table1" style="padding-top: 40px">
                            <tr>
                                <td class="width1428" colspan="2">旧密码</td>
                                <td class="width1428" colspan="2">
	                                <input id="oldpassword" type="text"  data-provide="oldpassword" data-items="4" data-source="" class="width8 c-header" style="margin: 0 auto; height: 30px;width: 250px;"  >
                                </td>
                            </tr>
                            <tr>
                                <td class="width1428" colspan="2">新密码</td>
                                <td class="width1428" colspan="2">
	                                <input id="newpassword" type="text"  data-provide="newpassword" data-items="4" data-source="" class="width8 c-header" style="margin: 0 auto; height: 30px;width: 250px;"  >
                                </td>
                            </tr>
                            <tr>
                                <td class="width1428" colspan="2">确认密码</td>
                                <td class="width1428" colspan="2">
	                                <input id="relpassword" type="text"  data-provide="relpassword" data-items="4" data-source="" class="width8 c-header" style="margin: 0 auto; height: 30px;width: 250px;"  >
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal-footer"> <a class="btn btn-primary btn-success" href="#" onclick="addsave()">保存</a> <a data-dismiss="modal" class="btn" href="#">取消</a> </div>
</div>
	<!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
	<script type="text/javascript">
	var baseUrl = "<%=basePath %>";
	function updatePassword(){
		$('#updatepassword').modal();
	}
	function addsave(){
		var oldpassword=$("#oldpassword").val();
		if(oldpassword==""||oldpassword==null){
			alert("旧密码不能为空！");
			return;
		}
		
		var newpassword=$("#newpassword").val();
		if(newpassword==""||newpassword==null){
			alert("新密码不能为空！");
			return;
		}
		
		if(newpassword.length<6){
			alert(newpassword.length);
			alert("新密码不能少于6位数！");
			return;
		}
		var relpassword=$("#relpassword").val();
		if(relpassword==""||relpassword==null){
			alert("确认密码不能为空！");
			return;
		}
		if(newpassword!=relpassword){
			alert("新密码和确认密码不一致！");
			return;
		}
		 $.ajax({
             type: "POST",
             dataType: "json",
             url: baseUrl+'user/updatepassword',
             data:'oldpassword='+oldpassword+'&newpassword='+newpassword,
             success: function (result) {
                 if(result.code==0){
                	 alert("修改成功！请重新登录");
                	 aa();
                	return;
                 }else if(result.code==1){
                	 alert("旧密码不正确！");
                	return;
                 }
                 
             }
         });
	}
	
	function aa(){
		location.href=encodeURI(baseUrl + "loginOut"); 
	}
	</script>
</body>
</html>


