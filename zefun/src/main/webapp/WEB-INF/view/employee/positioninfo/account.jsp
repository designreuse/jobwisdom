<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=basePath%>css/apartment_manage.css" type="text/css" />
<script>
	//轮播
	jQuery(function() {
		var now_ = 0, count = jQuery('.out_roll_ul li').size();
		//向右走
		jQuery('.click_right').click(
				function() {
					if (now_ <= count - 6) {
						now_ += 1;
						jQuery(this).parent('').find('.out_roll_ul').stop(true,
								true).animate({
							left : -181 * now_
						})
					}
				});
		//向左走
		jQuery('.click_left').click(
				function() {
					if (now_ >= 1) {
						now_ -= 1;
						jQuery(this).parent('').find('.out_roll_ul').stop(true,
								true).animate({
							left : -181 * now_
						})
					}
				});
	});

	jQuery(function(){
		  //职位信息
		  jQuery(document).on('mouseover','.job_style',function(){
		     jQuery(this).find("ul").show();
		  })
		  jQuery(document).on('mouseout','.job_style',function(){
			  jQuery(this).find("ul").hide();
		  })
		  //部门
		  jQuery(document).on('mouseover','.data_text',function(){
		     jQuery(this).find("ul").show();
		  })
		  jQuery(document).on('mouseout','.data_text',function(){
			  jQuery(this).find("ul").hide();
		  })
	})
	//点击修改
	jQuery(function() {
		jQuery('.job_manage_content li span').click(function() {
			jQuery(this).addClass('active');
			jQuery('.set')
		})
	})
	

	//切换
	jQuery(function(){
	 jQuery('.job_manage_content_text:gt(0)').hide();
	 jQuery('.job_manage_content_ul>ul>li').click(function(){
	   jQuery(this).addClass('active').siblings().removeClass('active');
	   jQuery('.job_manage_content_text').eq(jQuery(this).index()).show().siblings().hide();
	   
	 })
	})
	
	
	//气泡
	jQuery(function(){
	  jQuery(document).on('mouseover','.depend>span',function(){
	     jQuery(this).parent().find('p').stop(true,true).fadeIn();
	  })
	  jQuery(document).on('mouseout','.depend>span',function(){
	     jQuery(this).parent().find('p').stop(true,true).fadeOut();
	  })
	})
	jQuery(function(){
	  jQuery(document).on('mouseover','.job_name>em>i',function(){
	     jQuery(this).parents('.job_name').find('p').stop(true,true).fadeIn();
	  })
	  jQuery(document).on('mouseout','.job_name>em>i',function(){
	     jQuery(this).parents('.job_name').find('p').stop(true,true).fadeOut();
	  })
	})

</script>
<style>
.job_manage_content_ul img{vertical-align: middle;margin-left:4px}

</style>
<body>
	<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
		<div class="leftpanel" style="height: 840px; margin-left: 0px;">
			<%@ include file="/menu.jsp"%>
			<div class="rightpanel" style="margin-left: 200px;">
				<%@ include file="/top.jsp"%>
				<!--headerpanel-->
				<div class="content_right clearfix">
					<div class="out_roll_content">
						<div class="out_roll">
							<span class="click_left"><img src="<%=basePath%>images/left_click.png"></span>
							<div class="out_roll_div">
								<ul class="clearfix out_roll_ul">
									<c:forEach items="${storeInfos }" var="storeInfo"><li onclick="selectStore(this, ${storeInfo.storeId })" storeId="${storeInfo.storeId }">${storeInfo.storeName }</li></c:forEach>
								</ul>
								<script>jQuery(".out_roll_ul").children("li").eq(0).addClass("active");</script>
							</div>
							<span class="click_right"><img src="<%=basePath%>images/right_click.png"></span>
						</div>
					</div>

					<div class="manage_content">
						<div class="manage_num">
							<p>部门管理</p>
							<div class="manage_num_content">
								<div class="manage_button">
									<button onclick="jQuery(this).next().show();jQuery(this).next().next().show();jQuery(this).hide();deptId=null;">新增</button>
									<input type="text" name="deptName" style="display: none">
									<span style="display: none">
										<button onclick="saveDeptInfo(this);">保存</button>
										<button onclick="jQuery(this).parent().prev().hide();jQuery(this).parent().hide();jQuery(this).parent().prev().prev().show();deptId=null;">取消</button>
									</span>
								</div>
								<div class="data_content clearfix">
									<c:forEach items="${deptInfos }" var="deptInfo">
									<div class="data_text">
										${deptInfo.deptName }
										<ul class="clearfix" style="display: none;">
											<li onclick="updateDept(this, '${deptInfo.deptName }', ${deptInfo.deptId })">修改</li>
											<li onclick="deletedDept(this, ${deptInfo.deptId })">删除</li>
										</ul>
									</div>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="job_manage">
          <p>岗位设置</p>

		   <div class="job_manage_content">
             <div class="job_manage_content_ul"> 
			  <ul class="clearfix">
                 <li class="active">
				   <p><em positionId="${positionInfos[0].positionId }">${positionInfos[0].positionName }</em><img src="<%=basePath%>images/apartment_write.png"></p>
				 </li>
				 <li class="">
				   <p><em positionId="${positionInfos[1].positionId }">${positionInfos[1].positionName }</em><img src="<%=basePath%>images/apartment_write.png"></p>

				 </li>
				 <li class="">
				   <p><em positionId="${positionInfos[2].positionId }">${positionInfos[2].positionName }</em><img src="<%=basePath%>images/apartment_write.png"></p>
				 </li>
              </ul>
			  <div class="job_manage_content_">
			    <div class="job_manage_content_text" style="display: block;" name="job_name" positionId="${positionInfos[0].positionId }">
					  <p><button onclick="showDiv(this)">新建</button></p>
					  <div class="job_name   hide" >
					    <span>职位名称<input type="text" name="levelName"></span>
						<em>参考<i>?</i></em>
						<p style="display: none;"><span>${positionInfos[0].positionName }</span><i></i></p>
						<span style="margin-right:8px">${positionInfos[1].positionName }<select class="referenceFirst"></select></span>
						<span>${positionInfos[2].positionName }<select class="referenceTwo"></select></span>
						<span style="margin-left:32px"><button onclick="saveOrUpdateLevel(this)">保存</button><button onclick="jQuery('.job_name').addClass('hide')">取消</button></span>
					  </div>
	
					  <div class="data_content clearfix" style="height:220px" name="data_content1">
						</div>
				</div>
				
				<div class="job_manage_content_text" style="display: none;" name="job_name" positionId="${positionInfos[1].positionId }">
					   <p><button onclick="showDiv(this)">新建</button></p>
					  <div class="job_name   hide" >
					    <span>职位名称<input type="text" name="levelName"></span>
						<em>参考<i>?</i></em>
						<p style="display: none;"><span>${positionInfos[1].positionName }</span><i></i></p>
						<span style="margin-right:8px">${positionInfos[0].positionName }<select class="reference"></select></span>
						<span>${positionInfos[2].positionName }<select class="referenceTwo"></select></span>
						<span style="margin-left:32px"><button>保存</button><button>确认</button></span>
					  </div>
		
					  <div class="data_content clearfix" style="height:220px"  name="data_content2">
						  </div>
				</div>
				
				  <div class="job_manage_content_text" style="display: none;" name="job_name" positionId="${positionInfos[2].positionId }">
					   <p><button onclick="showDiv(this)">新建</button></p>
					  <div class="job_name   hide" >
					    <span>职位名称<input type="text" name="levelName"></span>
						<em>参考<i>?</i></em>
						<p style="display: none;"><span>${positionInfos[2].positionName }</span><i></i></p>
						<span style="margin-right:8px">${positionInfos[0].positionName }<select class="reference"></select></span>
						<span>${positionInfos[1].positionName }<select class="referenceFirst"></select></span>
						<span style="margin-left:32px"><button>保存</button><button>确认</button></span>
					  </div>
	
					  <div class="data_content clearfix" style="height:220px" name="data_content3">
						</div>
				</div>

				</div>
			  </div>
          </div>
        </div>
	   </div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
<div class="zzc">
  <div class="adjust">
    <p>修改</p>
    <div class="adjust_content">
	   <input type="text">
      <div class="adjust_content_button">
	    <button>确认</button>
	    <button>取消</button>
	  </div>
	</div>
  </div>
</div>

<div class="zzc1">
   <div class="first_adjust">
     <p>第一岗位修改</p>
     <div class="first_adjust_content">  
        <p>职位名称<input type="text"></p>
	    <div class="num_adjust_content">
	      <div class="depend">参考<span>?</span>
		    <p><span>21212121</span><em></em></p>
		  </div>
          <div class="num_adjust_content_detail">
		    第二岗位<select><option></option></select>
		  </div>
          <div class="num_adjust_content_detail">
		    第三岗位<select><option></option></select>
		  </div>
        </div>
        <div class="first_adjust_content_button">
           <button>确认</button>
		   <button>取消</button>
       </div>		
	 </div>
   </div>
</div>	
</body>
<script>

function showDiv(s){
	jQuery(s).parent().siblings(".job_name").removeClass('hide');
	jQuery(s).parent().siblings(".job_name").find("input[name='levelName']").val();	
}
var deptId = null;
function updateDept(li, deptName, deptIds){
	if (deptId!=null){
		dialog("请先保存当前部门修改");
		return ;
	}
	jQuery(".manage_button").find("button").eq(0).click();
	jQuery("input[name='deptName']").val(deptName);
	deptId = deptIds;
	jQuery(li).parents(".data_text").remove();
}
/**保存部门数据*/
function saveDeptInfo(button){
	var storeId = jQuery(".out_roll_ul").find("li[class='active']").attr("storeId");
	var deptName = jQuery("input[name='deptName']").val();
	var data = "deptName="+deptName+"&storeId="+storeId;
	if (deptId != null){
		data = data+"&deptId="+deptId;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "dept/saveOrUpdate",
		data : data,
		dataType : "json",
		success : function(e){
			deptId = null;
			var deptInfo = e.msg;
			var html = '<div class="data_text">'+deptInfo.deptName+
							'<ul class="clearfix" style="display: none;">'+
								'<li onclick="updateDept(this, \''+deptInfo.deptName+'\', '+deptInfo.deptId+')">修改</li>'+
								'<li>删除</li>'+
							'</ul>'+
						'</div>';
			jQuery(".data_content.clearfix").append(jQuery(html));
			jQuery(button).parent().hide();
			jQuery(button).parent().prev().hide();
			jQuery(button).parent().prev().prev().show();
			jQuery("input[name='deptName']").val('');
		}
	});
}
// 删除部门信息
function deletedDept(li, deptIds){
	if(confirm("确定要删除该部门么?")){
		var data = "deptId=" + deptIds + "&storeId=" + jQuery(".out_roll_ul").find("li[class='active']").attr("storeId");
		jQuery.ajax({
			type : "post",
			url : baseUrl + "dept/action/deletedept",
			data : data,
			dataType : "json",
			success : function(e){
				if (e.code != 0){
					dialog(e.msg);
				}
				else {
					dialog(e.msg);
					jQuery(li).parents(".data_content").remove();
				}
			}
		})
	}
}
var positionId = null;
function updatePositon(type, positionName, positionIds){
	positionId = positionIds;
	jQuery("input[name='positionName']").val(positionName);
	jQuery(".set").show();
	if (type == 1){jQuery("input[name='positionName']").next().text("不设置将默认为:  美发(容/甲）手艺师");}
	if (type == 2){jQuery("input[name='positionName']").next().text("不设置将默认为:  技师/(美容)经理");}
	if (type == 3){jQuery("input[name='positionName']").next().text("不设置将默认为:  助理");}
}
/**保存岗位信息*/
function updatePosition(button){
	var positionName = jQuery("input[name='positionName']").val();
	var data = "positionName="+positionName+"&positionId="+positionId;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "position/action/update",
		data : data,
		dataType : "json",
		success : function(e){
			jQuery("input[positionId='"+positionId+"']").val(positionName);
			jQuery(button).next().click();
		}
	});
}
/**变换岗位展示职位信息*/
function selectPosition(positionId){
	var index = jQuery("#position").find("option:selected").attr("index");
	if (index == 0) {initEmployeeLevelRes(empLevels, 1, 2);jQuery(".refer").find("em").eq(0).text(jQuery("input[positionid]").eq(1).val());jQuery(".refer").find("em").eq(1).text(jQuery("input[positionid]").eq(2).val());}
	if (index == 1) {initEmployeeLevelRes(empLevels, 0, 2);jQuery(".refer").find("em").eq(0).text(jQuery("input[positionid]").eq(0).val());jQuery(".refer").find("em").eq(1).text(jQuery("input[positionid]").eq(2).val());}
	if (index == 2) {initEmployeeLevelRes(empLevels, 0, 1);jQuery(".refer").find("em").eq(0).text(jQuery("input[positionid]").eq(0).val());jQuery(".refer").find("em").eq(1).text(jQuery("input[positionid]").eq(1).val());}
}

var empLevels = eval('('+'${empLevels}'+')');
/**js初始化员工等级*/
jQuery(function (){
	initEmployee(empLevels);
	initEmployeeLevelRes(empLevels, 1, 2);
})
/**初始化职位提成*/
function initEmployee(epms){
	jQuery("div[name='data_content1']").empty();
	for (var i=0,t=1;i<epms.length;i++,t++){
		var employeeLevel = epms[i];
		var html = '<div class="data_text" referenceFirst='+employeeLevel.referenceFirst+' referenceTwo='+employeeLevel.referenceTwo+'>'+
						employeeLevel.levelName+'<ul class="data_text">'+
							'<li onclick="showUpdateLevel(this, \''+employeeLevel.levelName+'\', '+employeeLevel.levelId+', '+employeeLevel.positionId+')">修改</li>'+
							'<li onclick="deletedLevel('+employeeLevel.levelId+', this)">删除</li>'+
						'</ul>'+
					'</div>';
		jQuery("div[name='data_content"+t+"']").append(jQuery(html));
	}
}
/**初始化参考职位提成*/
function initEmployeeLevelRes(epms, rs, rd){
	jQuery(".referenceFirst").empty();
	jQuery(".referenceTwo").empty();
	


	var rsf = jQuery(".job_manage_content_ul>ul li").eq(rs).find("em").attr("positionId");;
	var rst = jQuery(".job_manage_content_ul>ul li").eq(rd).find("em").attr("positionId");;
	for (var i=0;i<epms.length;i++){
		var employeeLevel = epms[i];
		if (employeeLevel.positionId == rsf){
			var html = '<option value='+employeeLevel.levelId+'>'+employeeLevel.levelName+'</option>';
			jQuery(".referenceFirst").append(html);
		}
	}
	for (var i=0;i<epms.length;i++){
		var employeeLevel = epms[i];
		if (employeeLevel.positionId == rst){
			var html = '<option value='+employeeLevel.levelId+'>'+employeeLevel.levelName+'</option>';
			jQuery(".referenceTwo").append(html);
		}
	}
}
var levelId = null;
function showUpdateLevel(li, levelName, levelIds, positionId){
	if (levelId!=null){dialog("请先保存当前设置");return;}
	levelId = levelIds;
	jQuery("input[name='levelName']").val(levelName);
	jQuery(li).parents(".job_style").remove();
	jQuery("#position").val(positionId);
	selectPosition(positionId);
	var referencefirst = jQuery(li).parents(".job_style").attr("referencefirst");
	var referencetwo = jQuery(li).parents(".job_style").attr("referencetwo");
	
}

function saveOrUpdateLevels(){
	var levelName = jQuery(s).parents(".job_name").find("input[name='levelName']").val();
	if (levelName == ''){
		dialog('名称不可为空');
		return;
	}
	var positionId = jQuery("#position").val();
	var positionName =  jQuery("#position").find("option:selected").text();
	var storeId = jQuery(".out_roll_ul").find("li[class='active']").attr("storeId");
	var data = "levelName="+levelName+"&positionId="+positionId+"&storeId="+storeId;
	if (levelId!=null){
		data = data + "&levelId="+levelId;
	}
	var referenceFirst = jQuery("#referenceFirst").val();
	var referenceTwo = jQuery("#referenceTwo").val();
	if (referenceFirst!=null){
		data = data + "&referenceFirst="+referenceFirst;
	}
	if (referenceTwo!=null){
		data = data + "&referenceTwo="+referenceTwo;
	}
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employeelevel/action/saveOrUpdate",
		data : data,
		dataType : "json",
		success : function(e){
			levelId = null;
			jQuery(".job_content.clearfix");
			var employeeLevel = e.msg;
			var html = '<div class="job_style" referenceFirst='+employeeLevel.referenceFirst+' referenceTwo='+employeeLevel.referenceTwo+'>'+
							'<p>职位名称：'+employeeLevel.levelName+'</p>'+
							'<p>岗位类型：'+positionName+'</p>'+
							'<ul class="clearfix">'+
								'<li onclick="showUpdateLevel(this, \''+employeeLevel.levelName+'\', '+employeeLevel.levelId+')">修改</li>'+
								'<li>删除</li>'+
							'</ul>'+
						'</div>';
			jQuery(".job_content.clearfix").append(jQuery(html));
		}
	});
	jQuery("input[name='levelName']").val('');
}

/**更换门店*/
function selectStore(li, storeId){
	jQuery(li).siblings().removeClass("active");
	jQuery(li).addClass("active");
	var data = "storeId="+storeId;
	jQuery.ajax({
		type : "post",
		url : baseUrl + "employee/account/positon",
		data : data,
		dataType : "json",
		success : function(e){
			var deptInfos = e.msg.deptInfos;
			var positionInfos = e.msg.positionInfos;
			var empLevels = e.msg.empLevels;
			
			jQuery(".data_content.clearfix").empty();
			for (var i=0;i<deptInfos.length;i++){
				var dept = '<div class="data_text">'+deptInfos[i].deptName+
								'<ul class="clearfix" style="display: none;">'+
									'<li onclick="updateDept(this, \''+deptInfos[i].deptName+'\', '+deptInfos[i].deptId+')">修改</li>'+
									'<li>删除</li>'+
								'</ul>'+
							'</div>';
				jQuery(".data_content.clearfix").append(jQuery(dept));
			}
			jQuery("input[positionid]").eq(0).val(positionInfos[0].positionName);
			jQuery("input[positionid]").eq(0).attr("positionid", positionInfos[0].positionId);
			jQuery("input[positionid]").eq(0).next().attr("onclick", "updatePositon(1, '"+positionInfos[0].positionName+"', "+positionInfos[0].positionId+")");
			
			jQuery("input[positionid]").eq(1).val(positionInfos[1].positionName);
			jQuery("input[positionid]").eq(1).attr("positionid", positionInfos[1].positionId);
			jQuery("input[positionid]").eq(1).next().attr("onclick", "updatePositon(1, '"+positionInfos[1].positionName+"', "+positionInfos[1].positionId+")");
			
			jQuery("input[positionid]").eq(2).val(positionInfos[2].positionName);
			jQuery("input[positionid]").eq(2).attr("positionid", positionInfos[2].positionId);
			jQuery("input[positionid]").eq(2).next().attr("onclick", "updatePositon(1, '"+positionInfos[2].positionName+"', "+positionInfos[2].positionId+")");
			
			jQuery("#position").empty();
			for (var i=0;i<positionInfos.length;i++){
				var html = '<option index='+i+' value="'+positionInfos[i].positionId+'">'+positionInfos[i].positionName+'</option>';
				jQuery("#position").append(jQuery(html));
			}
			
			initEmployee(empLevels);
			initEmployeeLevelRes(empLevels, 1, 2);
		}
	});
}
//取消编辑职位 
function cansaleUpdateLevel(){
	var levelName = jQuery("input[name='levelName']").val();
	var positionId = jQuery("#position").val();
	var positionName =  jQuery("#position").find("option:selected").text();
	var storeId = jQuery(".out_roll_ul").find("li[class='active']").attr("storeId");
	var referenceFirst = jQuery("#referenceFirst").val();
	var referenceTwo = jQuery("#referenceTwo").val();
	var html = '<div class="job_style" referenceFirst='+referenceFirst+' referenceTwo='+referenceTwo+'>'+
					'<p>职位名称：'+levelName+'</p>'+
					'<p>岗位类型：'+positionName+'</p>'+
					'<ul class="clearfix">'+
						'<li onclick="showUpdateLevel(this, \''+levelName+'\', '+levelId+')">修改</li>'+
						'<li>删除</li>'+
					'</ul>'+
				'</div>';
	jQuery(".job_content.clearfix").append(jQuery(html));
	levelId = null;
	jQuery("input[name='levelName']").val('');
}
//删除职位提成
function deletedLevel(levelId, li){
	if(confirm("确定删除该职位信息么?")){
		var storeId = jQuery(".clearfix.out_roll_ul").find("li[class='active']").attr("storeid");
		var data = "levelId="+levelId+"&storeId="+storeId;
		jQuery.ajax({
			type : "post",
			url : baseUrl + "employeelevel/action/delete",
			data : data,
			dataType : "json",
			success : function(e){
				if (e.code!=0){
					dialog(e.msg);
				}else {
					jQuery(li).parents('.job_style').remove();
				}
			}
		});
	}
}

function initEmployeeLevelRes(epms){
	jQuery(".referenceFirst").empty();
	jQuery(".referenceTwo").empty();
	


	var rse = jQuery(".job_manage_content_ul>ul li").eq(0).find("em").attr("positionId");
	var rsf = jQuery(".job_manage_content_ul>ul li").eq(1).find("em").attr("positionId");
	var rst = jQuery(".job_manage_content_ul>ul li").eq(2).find("em").attr("positionId");
	for (var i=0;i<epms.length;i++){
		var employeeLevel = epms[i];
		if (employeeLevel.positionId == rse){
			var html = '<option value='+employeeLevel.levelId+'>'+employeeLevel.levelName+'</option>';
			jQuery(".reference").append(html);
		}
	}
	for (var i=0;i<epms.length;i++){
		var employeeLevel = epms[i];
		if (employeeLevel.positionId == rsf){
			var html = '<option value='+employeeLevel.levelId+'>'+employeeLevel.levelName+'</option>';
			jQuery(".referenceFirst").append(html);
		}
	}
	for (var i=0;i<epms.length;i++){
		var employeeLevel = epms[i];
		if (employeeLevel.positionId == rst){
			var html = '<option value='+employeeLevel.levelId+'>'+employeeLevel.levelName+'</option>';
			jQuery(".referenceTwo").append(html);
		}
	}
}
</script>
</html>