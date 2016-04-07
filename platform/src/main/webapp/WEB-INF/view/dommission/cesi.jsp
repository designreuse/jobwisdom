<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>利用复选框往输入框添加数据</title>
</head>
<body>
    <div class="span12 content">
	<div class="box">
		<div class="box-head">
			<h3>邮件编写</h3>
		</div>
		<div class="box-content">
			<div class="form-horizontal">
			
				<div class="control-group">
					<label class="control-label">收件人</label>
					<div class="controls">
						<div class="input-append">
							<input type="text" id="jsrtxt" class="tip" />
							<span>
							<input type="button"  id="jieshouren" value="点击选择联系人" >
								<i class="icon-user" style="cursor:pointer" ></i>
								<div style="display:none" id="selectlxr"></div>
							</span>
						</div>
					</div>
				</div>
				
				<div class="control-group">
					<label class="control-label">主题</label>
					<div class="controls">
						<input type="text" class="span6 input-square" />
					</div>
				</div>
				<div class="control-group">
					<label class="control-label">内容</label>
					<div class="controls">
						<textarea class="span6 input-square"></textarea>
					</div>
				</div>
			
				<div class="control-group">
					<label class="control-label">&nbsp;</label>
					<div class="controls">
						<input type="button" class="btn btn-fo" value="提交发送" />
					</div>
				</div>
			
			</div>
		</div>
	</div>
</div>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.11.3.min.js"></script>
<script src="<%=basePath %>js/artDialog/js/jquery.artDialog.js?skin=idialog"></script>
<script type="text/javascript">
//循环输出创建十个复选框
var chtml = "";
for (var i = 0; i < 10; i++) {
   chtml += "<div style='word-wrap:break-word; width:450px; '>";
   chtml += '<label style="float:left;padding:15px"><input type="checkbox" name="aaa" value="1" class="{required:true}" /><span style="margin-left:10px">小'+i+'</span></label>';
   chtml += "</div>";
}
//把得到字符串利用jquery添加到元素里面生成checkbox
$("#selectlxr").html(chtml);
//创建一个 dialog弹出框(第三方插件有兴趣可以看下 超赞的一款插件 http://www.planeart.cn/demo/artDialog/) 把创建好的弹出框隐藏起来
var dia = $.dialog(
   {
	   title: "选择联系人", width: "500px",
	   content: $("#selectlxr").html(),
	   close: function () {
		   this.hide();
		   return false;
	   },
	   follow: document.getElementById("jieshouren")
   }
   ).hide();

//点击 显示
$("#jieshouren").click(function () {
   dia.show();
})
//事件 获取checkbox点击时候的父元素的值 添加到text 如果点击收的选中状态为checked 则添加 否则 删除
$("input[type=checkbox]").click(function () {
   try {
	   if ($(this).attr("checked")) {
		   $("#jsrtxt").val($("#jsrtxt").val() + $(this).parent().text() + ";");
	   } else {
		   $("#jsrtxt").val($("#jsrtxt").val().replace($(this).parent().text() + ';', ""));
	   }
   } catch (e) {
	   $("#jsrtxt").val("");
   }
})
//初步测试 暂无小bug 可以为text增加一个只读  
</script>
</body>
</html>

