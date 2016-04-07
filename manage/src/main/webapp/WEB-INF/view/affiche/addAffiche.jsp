<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/base.jsp"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>后台管理</title>
 
<!-- Include the plugin's CSS and JS: -->

</head>
<body>
		<!--头部页面引用-->
	<%@include file="/WEB-INF/view/topheader.jsp"%>
	<!--左侧菜单页面引用-->
	<%@include file="/WEB-INF/view/sliderbar.jsp"%>
	
		<div id="content">
	
			<div id="content-header">
				<div id="breadcrumb">
					<a href="<%=basePath %>homepage/view/query" class="tip-bottom" data-original-title="主页">
						<i class="icon-home"></i>首页看板
					</a>
					<!-- <a href="" class="tip-bottom" data-original-title=""></a>-->
					<a href="#" class="current">发布公告</a>
				</div>
			</div>
	
	
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="control-group">
						<div class="span4">
							<label class="gg-label">公告标题 :</label> <input type="text" id="afficheTitle"
								class="span8" />
						</div>
						<div class="span4">
							<label class="gg-label">公告类别 :</label> <select name="" id="afficheClasses"
								class="span8">
								<option value="1">类别一</option>
								<option value="2">类别二</option>
								<option value="3">类别三</option>
							</select>
						</div>
						<div class="span4">
							<label class="gg-label">发布时间 :</label>
							 <input id="afficheTime" type="text" class="span8 c-header form_datetime"/>
						</div>
					</div>
					<div>
					<div class="span4">
							<label class="gg-label">发布角色:</label> 
							<select name="role" id="role"class="span8" multiple="multiple" >
							<c:forEach items="${role}" var="role">
							<option value="${role.roleId}">${role.roleName} </option> 
							</c:forEach>
							</select>
						</div>
					</div>
				</div>
	
				<div class="row-fluid">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                        <h5>公告内容</h5>
                    </div>
                    <div class="widget-content">
                        <div class="control-group">
                            <form id="afficheContent">
                                <div class="controls">
                                    <div class="summernote" ></div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
        </div>
	
				<div class="row-fluid">
					<input class="btn btn-info pull-right mg-right30" type="button"
						value="保存" onclick="add()"/> <input class="btn btn-info pull-right mg-right30"
						type="button" value="返回查询页面" onclick="gotoquery()" />
				</div>
		</div>
		</div>
	
      	
		
		<!--尾部代码页面引用-->
	<%@include file="/WEB-INF/view/footer.jsp"%>
	<%@include file="/WEB-INF/js.jsp"%>
	<script type="text/javascript">
	$(document).ready(function() {
		/* var v='4,6,7'//这个为保存的值，自己从数据库读取来赋值给v变量
			v=','+v+',';//添加分隔符号，好indexOf进行比较
			var arr=v.split(',');
			$('#role option').each(function(){
			  if(v.indexOf(','+this.value+',')!=-1)this.selected=true;
			}); */
			//再调用插件初始化select对象
	   $('#role').multiselect();
	   
	
	   
    });
	function gotoquery(){
		location.href='<%=basePath %>SystemAffiche/view/selectTable'; 
	}
	/*时间选择器中文包*/
	$.fn.datetimepicker.dates['zh-CN'] = {
	  days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
	  daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
	  daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
	  months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	  monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
	  today: "今天",
	  suffix: [],
	  meridiem: ["上午", "下午"]
	};

	/*出发时间选择器*/
	$(".form_datetime").datetimepicker({
		  format: 'yyyy-mm-dd',//显示的格式
		  language: 'zh-CN',//中文
		  autoclose: 'true',//选择后自动关闭
		  minView: '2',//到日，不到时间
		  todayHighlight: true//今天时间高亮
		  //initialDate: new Date()//初始化时间为当前时间
		});
    function add(){
    	var role=$("#role").val();
  
    	var afficheTitle=$("#afficheTitle").val();
  		if(afficheTitle==null||afficheTitle==""){
  			alert("标题不能为空！");
  			return ;  
  		}
  		
  		var afficheClasses=$("#afficheClasses").val();
  		if(afficheClasses==null||afficheClasses==""){
  			alert("类别不能为空！");
  			return ;  
  		}
  		
  		var afficheTime=$("#afficheTime").val();
  		if(afficheTime==null||afficheTime==""){
  			alert("发布时间不能为空！");
  			return ;  
  		}
  		
  		var afficheContent=$('.summernote').code();
  		afficheContent = afficheContent.replace(/\+/g, "%2B");
		afficheContent = afficheContent.replace(/\&/g, "%26");
		if(afficheContent==null||afficheContent==""){
			alert("内容不能为空！");
			return ; 
		}

		$.ajax({
			type: "post",
			url : "<%=basePath %>SystemAffiche/addaffiche",
			data : 'afficheTitle='+afficheTitle+'&afficheClasses='+afficheClasses+'&afficheTime='+afficheTime+'&afficheContent='+afficheContent+'&role='+role,  
			dataType : "json",//返回的数据格式
			success : function(e){//返回结果处理
				alert("保存成功");
			}
		});
	}	
    	
    	/*summernote编辑器的启动 editor*/
		$('.summernote').summernote({
		   height: 200,                 // set editor height
		   minHeight: null,             // set minimum height of editor
		   maxHeight: null,             // set maximum height of editor
		   focus: true               // set focus to editable area after initializing summernote
		});
		</script>
	</body>
</html> 


