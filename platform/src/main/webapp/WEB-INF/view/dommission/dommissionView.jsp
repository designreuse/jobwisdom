<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/base.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script src="<%=basePath %>js/artDialog/js/jquery.artDialog.js?skin=idialog"></script>
<script type="text/javascript" src="<%=basePath %>js/jquery-1.11.3.min.js"></script>
<script type="text/javascript">

   function copyModel(){
	   /* $("#trEl").after($("#trEl").clone()); */
	   
	   $.ajax({
			url : "selectPositionByStoresIdList?storesId=1001",
			dataType : "json",
			success : function(data){
				//$("#viewid").empty();
				//$("select[name = 'positionId']").empty();
				var tr = $("<tr></tr>");
				var td =$("<td></td>");
				var select = $("<select name='positionId'></select>");
				select.append("<option value=''>请选择</option>");
				for(var d in data){
					var positionId = data[d].positionId;
					var positionName = data[d].positionName;
					select.append("<option value='"+positionId+"'>"+positionName+"</option>");
				}
				td.append(select);
				tr.append(td);
				tr.append("<td><input type='text' name='employeeId'><input type ='button' onclick = 'showPopup()'></td>"+
						   "<td><input type='text' name='fixedCommission'></td>"+
						   "<td><input type='text' name='percentageCommission' ></td></tr>");
				$("#viewid").append(tr);
			},
		 });
	   
   }
   
   function addWindow(){
	   var chtml = "";
	   $.ajax({
			url : "employeedtoSelectByProperty?employeeNo=&employeeName=&positionId=4&storesId=3",
			dataType : "json",
			success : function(data){
				var select = $("<select name='positionId'></select>");
				select.append("<option value=''>请选择</option>");
				for(var d in data){
					var employeeId = data[d].employeeId;
					var employeeName = data[d].employeeName;
					select.append("<option value='"+positionId+"'>"+positionName+"</option>");
					chtml += "<div style='word-wrap:break-word; width:450px; '>";
					chtml += '<label style="float:left;padding:15px"><input type="checkbox" name="aaa" value="'+employeeId+'" class="{required:true}" /><span style="margin-left:10px">小'+positionName+'</span></label>';
					chtml += "</div>";
				}
				td.append(select);
				tr.append(td);
				tr.append("<td><input type='text' name='employeeId'></td>"+
						   "<td><input type='text' name='fixedCommission'></td>"+
						   "<td><input type='text' name='percentageCommission' ></td></tr>");
				$("#viewid").append(tr);
			},
		 });
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
   }
   
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>vip注册</title>
<style>  
#popupcontent{   
  position: absolute;  
  visibility: hidden;     
  overflow: hidden;     
  border:1px solid #CCC;     
  background-color:#F9F9F9;     
  border:1px solid #333;     
  padding:5px;}  
</style>  

</head>
<body>
     <center>
       <h1>提成设置</h1>
          <form action="dommissionAllSubmit" method="post">
            <table border="1" bgcolor="#0099CC" id="viewid">
            
              <tr>
                 <td><input type="button" onclick="copyModel()" value="添加"></td>
              </tr>
              <tr>
                  <td>选择岗位</td>
                  <td>选择员工</td>
                  <td>提成比例</td>
                  <td>固定提成</td>
              </tr>
              <tr>
                  <td>
                     <label>Input Tags</label>
                            <span class="field">
                            	<input name="tags" id="tags" class="input-large" value="foo,bar,baz" />
                            </span>
                  </td>
              </tr>
           </table>
           <input type="submit"  value="提交1">
     </form>
     <div id="windowid" style="display:none">
     
     </div>
   
</center>
</body>
</html>