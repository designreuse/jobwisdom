/*var $checkableTree = $('#treeview-checkable').treeview({
          data: defaultData,
          showIcon: false,
          showCheckbox: true,
          onNodeSelected:function(event,node){
        	  updatePrivilege(node);
          },
          onNodeChecked: function(event, node) {
        	  parentCheckedWithChild(node);
          },
          onNodeUnchecked: function (event, node) {
        	  parentUnCheckedWithChild(node);
          }
        });

        //选择父节点的时候,将子选项全部选中
        function parentCheckedWithChild(node){
          if(node == null)return;
          var s = node.nodes;
      	  for ( var v in s) {
      		  $('#treeview-checkable').treeview('getNode', s[v].nodeId).state.checked=true;
      		parentCheckedWithChild(s[v]);
			} 
        }
        //取消父节点的时候,将子选项全部取消
        function parentUnCheckedWithChild(node){
            if(node == null)return;
            var s = node.nodes;
        	  for ( var v in s) {
        		  $('#treeview-checkable').treeview('getNode', s[v].nodeId).state.checked=false;
        		parentUnCheckedWithChild(s[v]);
  			} 
          }
*/        
/************************************************************************新增权限**********************************************************************************/

      //新增菜单权限
$table = $('#utable').bootstrapTable({});
$table1 = $('#utable1').bootstrapTable({});
$table2 = $('#utable2').bootstrapTable({});      
function addMenu(){
      	art.dialog({
      	    id: 'shake-demo',
      	    title: '新增菜单权限',
      	    content: '权限名称：<input id="login-na" type="text" value="" />'
      	        + '<br />样式：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="login-cl" type="text" value="" />'
      	        + '<br />地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="login-ui" type="text" value="" />'
      	        + '<br />父级菜单: &nbsp;<select id="select"></select>',
      	    initialize: function () {
      	        document.getElementById('login-na').focus();
      	        var html = "<option  value=\"0\">默认一级菜单";
      	        //初始化一级菜单提供选择
      	        $.ajax({
      		        type: "GET",
      		        dataType: "json",
      		        url: baseUrl+'privilege/getPrivilege',
      		        success: function (result) {
      		        	for(var i = 0;i < result.length; i++) {
      		        		html = html + "<option value="+result[i].privilegeId+">"+result[i].privilegeName+"</option>";
      		        		}
      		        	$("#select").html(html);
      		        }
      		    }); 
      	    },
      	    lock: true,
      	    fixed: true,
      	    ok: function () {
      	      var classname = document.getElementById('login-cl');
      	      var name = document.getElementById('login-na');
      	      var url = document.getElementById('login-ui');
      	      var refId = $("#select").val();
                add(classname.value,name.value,url.value,0,refId);
      	      return true;
      	    },
      	    okValue: '确定',
      	    cancel: function () {}
      	});
      }
      //新增元素权限
      function addYs(){
      	art.dialog({
      	    id: 'shake-demo',
      	    title: '修改权限',
      	    content:    '权限名称：<input id="login-na" type="text" value="" />'
      	        + '<br />控件样式：<input id="login-cl" type="text" value="" />'
      	        + '<br />所在页面：<input id="login-ui" type="text" value="" />',
      	    initialize: function () {
      	        document.getElementById('login-na').focus();
      	    },
      	    lock: true,
      	    fixed: true,
      	    ok: function () {
      	      var classname = document.getElementById('login-cl');
      	      var name = document.getElementById('login-na');
      	      var jsp = document.getElementById('login-ui');
      	      add(classname.value,name.value,jsp.value,2,null);
      	      return true;
      	    },
      	    okValue: '确定',
      	    cancel: function () {}
      	});
      }
      //1权限新增
      function addGn(){
      	art.dialog({
      	    id: 'shake-demo',
      	    title: '修改权限',
      	    content: '权限名称：<input id="login-na" type="text" value="" />'
      	        + '<br />地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="login-ui" type="text" value="" />',
      	    initialize: function () {
      	        document.getElementById('login-na').focus();
      	    },
      	    lock: true,
      	    fixed: true,
      	    ok: function () {
      	      var name = document.getElementById('login-na');
      	      var url = document.getElementById('login-ui');
      	      add(null,name.value,url.value,1,null);
      	      return true;
      	    },
      	    okValue: '确定',
      	    cancel: function () {}
      	});
      }
      function add(classname,name,url,type,refId){
      	if(type == 0){
      	var privilegeValue = "{\"classname\":"+"'"+classname+"'"+",\"url\":"+"'"+url+"'"+"}";
      	 $.ajax({
      	        type: "POST",
      	        dataType: "json",
      	        data:"privilegeType=0"+"&privilegeName="+name+"&privilegeValue="+privilegeValue+"&refPrivilegeId="+refId,
      	        url: baseUrl+'privilege/addPrivilege/1',
      	        success: function (result) {
      	        }
      	    });
      	 }else if(type == 2){
      		 var privilegeValue = "{\"classname\":"+"'"+classname+"'"+",\"jsp\":"+"'"+url+"'"+"}";
      		 $.ajax({
      		        type: "POST",
      		        dataType: "json",
      		        data:"privilegeType=2"+"&privilegeName="+name+"&privilegeValue="+privilegeValue,
      		        url: baseUrl+'privilege/addPrivilege/1',
      		        success: function (result) {
      		        }
      		    });
      	 }else if(type == 1){
      		 $.ajax({
      		        type: "POST",
      		        dataType: "json",
      		        data:"privilegeType=1"+"&privilegeName="+name+"&privilegeValue="+url,
      		        url: baseUrl+'privilege/addPrivilege/1',
      		        success: function (result) {
      		        }
      		    });
      	 }
      }
      
/************************************************************删除权限操作*********************************************************************************/
      
	    function deleteByPid(pid){
  		var roleId = $("#s1").val();
  		$.ajax({
			type : "POST",
			dataType : "json",
			url : baseUrl + 'privilege/deletePrivilege',
			data : 'pId=' + pid,
			success : function(result) {
				alert(result.msg);
				refreshTable();
				refreshTable1();
				refreshTable2();
			}
		});
  	}
	    
/************************************************************修改权限操作*********************************************************************************/
        function updatePrivilege(node){
        	var parent = $('#treeview-checkable').treeview('getParent', node);
        	if(typeof(parent.id) == "undefined")return;
        	var type = getParents(node,node.id);
        }
        function getParents(node,pid){
        	var parent = $('#treeview-checkable').treeview('getParent', node);
        	if(typeof(parent.id) == "undefined"){
        		if(node.text == "菜单权限"){diagre1(pid,0);}
        		if(node.text == "功能权限"){diagre2(pid,1);}
        		if(node.text == "元素权限"){diagre3(pid,2);}
        	}else{
        		getParents(parent,pid);
        	}
        }
	    //菜单权限修改弹出框
	    function diagre1(pid,type){
	    		art.dialog({
	    		    id: 'shake-demo',
	    		    title: '修改权限',
	    		    content: '权限名称：<input id="login-na" type="text" value="" />'
	    		        + '<br />样式：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="login-cl" type="text" value="" />'
	    		        + '<br />地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="login-ui" type="text" value="" />'
	    		        + '<br />父级菜单: &nbsp;<select id="select"></select>',
	    		    initialize: function () {
	    		        document.getElementById('login-na').focus();
	    		        var html = "<option  value=\"0\">默认一级菜单";
	    		        //初始化一级菜单提供选择
	    		        $.ajax({
	    			        type: "GET",
	    			        dataType: "json",
	    			        url: baseUrl+'privilege/getPrivilege',
	    			        success: function (result) {
	    			        	for(var i = 0;i < result.length; i++) {
	    			        		html = html + "<option value="+result[i].privilegeId+">"+result[i].privilegeName+"</option>";
	    			        		}
	    			        	$("#select").html(html);
	    			        }
	    			    }); 
	    		    },
	    		    lock: true,
	    		    fixed: true,
	    		    ok: function () {
	    		      var classname = document.getElementById('login-cl');
	    		      var name = document.getElementById('login-na');
	    		      var url = document.getElementById('login-ui');
	    		      var refId = $("#select").val();
	                  update(pid,classname.value,name.value,url.value,type,refId);
	    		      return true;
	    		    },
	    		    okValue: '确定',
	    		    cancel: function () {}
	    		});}
	    //元素权限修改弹出框
	    function diagre2(pid,type){
	    		art.dialog({
	    		    id: 'shake-demo',
	    		    title: '修改权限',
	    		    content:    '权限名称：<input id="login-na" type="text" value="" />'
	    		        + '<br />控件样式：<input id="login-cl" type="text" value="" />'
	    		        + '<br />所在页面：<input id="login-ui" type="text" value="" />',
	    		    initialize: function () {
	    		        document.getElementById('login-na').focus();
	    		    },
	    		    lock: true,
	    		    fixed: true,
	    		    ok: function () {
	    		      var classname = document.getElementById('login-cl');
	    		      var name = document.getElementById('login-na');
	    		      var jsp = document.getElementById('login-ui');
	    		      update(pid,classname.value,name.value,jsp.value,type,null);
	    		      return true;
	    		    },
	    		    okValue: '确定',
	    		    cancel: function () {}
	    		});}
	    //功能权限修改弹出框
	    function diagre3(pid,type){
	    		art.dialog({
	    		    id: 'shake-demo',
	    		    title: '修改权限',
	    		    content: '权限名称：<input id="login-na" type="text" value="" />'
	    		        + '<br />地址：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input id="login-ui" type="text" value="" />',
	    		    initialize: function () {
	    		        document.getElementById('login-na').focus();
	    		    },
	    		    lock: true,
	    		    fixed: true,
	    		    ok: function () {
	    		      var name = document.getElementById('login-na');
	    		      var url = document.getElementById('login-ui');
	    		      update(pid,null,name.value,url.value,type,null);
	    		      return true;
	    		    },
	    		    okValue: '确定',
	    		    cancel: function () {}
	    		});
	    		}
	    function update(id,classname,name,url,type,refId){
	    	if(type == 0)
	    	{
	    	var privilegeValue = "{\"classname\":"+"'"+classname+"'"+",\"url\":"+"'"+url+"'"+"}";
	    	 $.ajax({
	    	        type: "POST",
	    	        dataType: "json",
	    	        data:"privilegeType=0"+"&privilegeName="+name+"&privilegeValue="+privilegeValue+"&privilegeId="+id+"&refPrivilegeId="+refId,
	    	        url: baseUrl+'privilege/updatePrivilege',
	    	        success: function (result) {
	    	        	alert("success");
	    	        }
	    	    });
	    	 }else if(type == 2){
	    		 var privilegeValue = "{\"classname\":"+"'"+classname+"'"+",\"jsp\":"+"'"+url+"'"+"}";
	    		 $.ajax({
	    		        type: "POST",
	    		        dataType: "json",
	    		        data:"privilegeType=2"+"&privilegeValue="+privilegeValue+"&privilegeName="+name+"&privilegeId="+id,
	    		        url: baseUrl+'privilege/updatePrivilege',
	    		        success: function (result) {
	    		        	alert("success");
	    		        }
	    		    });
	    	 }else if(type == 1){
	    		 $.ajax({
	    		        type: "POST",
	    		        dataType: "json",
	    		        data:"privilegeType=1"+"&privilegeName="+name+"&privilegeValue="+url+"&privilegeId="+id,
	    		        url: baseUrl+'privilege/updatePrivilege',
	    		        success: function (result) {
	    		        	alert(result.msg);
	    		        }
	    		    });
	    	 }
	    }
/***********************************************菜单权限的显示操作按钮*******************************************************/
    function operateFormatter(value, row, index) {
    	  return [
    	    '<a class="like" style="color: blue;" href="#projectMod" data-toggle="modal" title="修改"><i class="icon-pencil"></i></a> ',
    	    '<a class="remove" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
    	  ].join('');
    	}
    
	function queryParams(param) {
		return param;
	}
	function operateFormatter(value, row, index) {
    	  return [
    	    '<a class="like" style="color: blue;" href="#projectMod" data-toggle="modal" title="修改"><i class="icon-pencil"></i></a> ',
    	    '<a class="remove" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
    	  ].join('');
    	}
	window.operateEvents = {
			  'click .like': function (e, value, row, index) {
				  updateMenu(row);
			  },
			  'click .remove': function (e, value, row, index) {
				  deleteByPid(row.id);
				  }
			};
    /***********************************************bootstrap table 新增菜单*******************************************************/    
    function addTableMenu(){
    	initSelect("s1");
    	$("#addMenu").modal();
    }
    function initSelect(sid){
    var html = "<option  value=\"0\">默认一级菜单";
    $.ajax({
          type: "GET",
          dataType: "json",
          url: baseUrl+'privilege/getPrivilege',
          success: function (result) {
          	for(var i = 0;i < result.length; i++) {
          		html = html + "<option value="+result[i].privilegeId+">"+result[i].privilegeName+"</option>";
          		}
          	$("#"+sid).html(html);
          }
      }); 
    }
    function addsaveMenu(){
    	var name = $("#p_name").val();
    	var url = $("#p_url").val();
    	var css = $("#p_css").val();
    	var ref = $("#s1").val();
    	console.log(ref);
    	add(css,name,url,0,ref);
    	refreshTable();
    }
    
 /**************************************************修改菜单权限*****************************************************************/
	function updateMenu(row){
	  $("#p_name_update").val(row.text);
	  $("#p_url_update").val(row.url);
	  $("#p_css_update").val(row.classname);
	  $("#p_id_update").val(row.id);
	  initSelect("s2");
	  $("#updateMenu").modal();
	}
  function realupdateMenu(){
  	 var pid = $("#p_id_update").val();
	 var name = $("#p_name_update").val();
	 var url = $("#p_url_update").val();
	 var css = $("#p_css_update").val();
	 var refId = $("#s2").val();
	  	 update(pid,css,name,url,0,refId);
	  	refreshTable();
	  }
  
  /***********************************************功能权限的显示操作按钮*******************************************************/
  
  function operateFormatterGn(value, row, index) {
	  return [
	    '<a class="like" style="color: blue;" href="#projectMod" data-toggle="modal" title="修改"><i class="icon-pencil"></i></a> ',
	    '<a class="remove" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
	  ].join('');
	}

function queryParamsForGn(param) {
	console.log(param);
	return param;
}
function operateFormatterGn(value, row, index) {
	  return [
	    '<a class="like" style="color: blue;" href="#projectMod" data-toggle="modal" title="修改"><i class="icon-pencil"></i></a> ',
	    '<a class="remove" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
	  ].join('');
	}
window.operateEventsGn = {
		  'click .like': function (e, value, row, index) {
			  updateGn(row);
		  },
		  'click .remove': function (e, value, row, index) {
			  deleteByPid(row.id);
			  }
		};
/***********************************************功能权限的新增操作*******************************************************/
function addTableGn(){
	$("#addGn").modal();
}
function addsaveGn(){
	var name = $("#p_Gn_name").val();
	var url = $("#p_Gn_url").val();
	console.log(name+url);
	add(null,name,url,1,null);
	refreshTable1();
}
function updateGn(row){
	  $("#p_Gn_name_update").val(row.text);
	  $("#p_Gn_url_update").val(row.url);
	  $("#p_Gn_id_update").val(row.id);
      $("#updateGn").modal();
}
function realupdateGn(){
	 var pid = $("#p_Gn_id_update").val();
	 var name = $("#p_Gn_name_update").val();
	 var url = $("#p_Gn_url_update").val();
	 update(pid,null,name,url,1,null);
	 refreshTable1();
}
/***********************************************元素权限的显示操作按钮*******************************************************/

function operateFormatterYs(value, row, index) {
	  return [
	    '<a class="like" style="color: blue;" href="#projectMod" data-toggle="modal" title="修改"><i class="icon-pencil"></i></a> ',
	    '<a class="remove" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
	  ].join('');
	}

function queryParamsForYs(param) {
	return param;
}
function operateFormatterYs(value, row, index) {
	  return [
	    '<a class="like" style="color: blue;" href="#projectMod" data-toggle="modal" title="修改"><i class="icon-pencil"></i></a> ',
	    '<a class="remove" style="color: blue; " href="javascript:void(0)" title="删除"><i class="icon-remove"></i></a>',
	  ].join('');
	}
window.operateEventsYs = {
		  'click .like': function (e, value, row, index) {
			  updateYs(row);
		  },
		  'click .remove': function (e, value, row, index) {
			  deleteByPid(row.id);
			  }
		};
/***********************************************元素权限的新增操作*******************************************************/
function addTableYs(){
	$("#addYs").modal();
}
function addsaveYs(){
	var name = $("#p_Ys_name").val();
	var url = $("#p_Ys_url").val();
	var css = $("#p_Ys_css").val();
	add(css,name,url,2,null);
	refreshTable2();
}

/**************************************************修改元素权限*****************************************************************/
function updateYs(row){
	  $("#p_Ys_name_update").val(row.text);
	  $("#p_Ys_url_update").val(row.url);
	  $("#p_Ys_css_update").val(row.classname);
	  $("#p_Ys_id_update").val(row.id);
    $("#updateYs").modal();
}
function realupdateYs(){
	 var pid = $("#p_Ys_id_update").val();
	 var name = $("#p_Ys_name_update").val();
	 var url = $("#p_Ys_url_update").val();
	 var css = $("#p_Ys_css_update").val();
	 var refId = $("#s2").val();
	 update(pid,css,name,url,2,null);
	 refreshTable2();
}


function refreshTable(){
	 $table.bootstrapTable('refresh', {
         url: baseUrl+'privilege/showPrivilegeInfo'
     });
}
function refreshTable1(){
	 $table1.bootstrapTable('refresh', {
         url: baseUrl+'privilege/showPrivilegeGnInfo'
     });
}
function refreshTable2(){
	 $table2.bootstrapTable('refresh', {
         url: baseUrl+'privilege/showPrivilegeYsInfo'
     });
}