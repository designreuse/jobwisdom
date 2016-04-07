/*****************************************menu权限**********************************************************/        
var $checkableTree = $('#treeview-checkable').treeview({
          data: defaultData,
          showIcon: false,
          showCheckbox: true,
          onNodeChecked: function(event, node) {
        	  parentCheckedWithChild(node);
        	  CheckedParents(node);
        	  
            //$('#checkable-output').prepend('<p>' + node.nodes + ' was checked</p>');
            //$('#checkable-output').prepend('<p>' + node.id + ' was checked</p>');
          },
          onNodeUnchecked: function (event, node) {
        	  parentUnCheckedWithChild(node);
           // $('#checkable-output').prepend('<p>' + node.text + ' was unchecked</p>');
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
        //子子菜单选择了以后,自动将父节点也选上
        function CheckedParents(node){
        	var s = $('#treeview-checkable').treeview('getParent', node);
        	if(typeof(s.id)== "undefined")return;
        	$('#treeview-checkable').treeview('checkNode',[s.nodeId,{silent:true}]);
        	//$('#treeview-checkable').treeview('getNode', s.nodeId).state.checked=true;
        	CheckedParents(s);
        }
        
        window.onload=function(){
        	initRoles();
        }
        
        /**初始化角色信息***/
		function initRoles(){
			var html = "";
			var obj = eval(""+roleInfo+"");
			for ( var r in obj) {
				html = html + "<div class=\"txt\">"+obj[r].roleName+"<span class=\"date badge badge-important mgl10\"> <a id=\""+obj[r].roleId+"\" href=\"#\" onclick = \"roleHasBuildPrivilege(this)\">查看权限</a> </span> </div>"+"<a id=\""+obj[r].roleId+"\" onclick=\"update(\'"+obj[r].roleId+"\',\'"+obj[r].roleName+"\')\" class=\"tip\" href=\"#\" title=\"Edit Task\">修改</a> <a id=\""+obj[r].roleId+"\" onclick=\"deleteRole(this)\" class=\"tip\" href=\"#\" title=\"Delete\">删除</a> </div></li>";
			}
			$("#roleInfos").html(html);
		}
		//设置一个全局定义的roleId;
		var roleInfo_id = "";
		 /**角色已有权限信息,进行预先勾选***/
        function roleHasBuildPrivilege(a){
        	roleInfo_id = a.id;
        	var roleId = a.id;//$("#s1").val();
        	$.ajax({
	    		type : "POST",
	    		dataType : "json",
	    		url : baseUrl + 'privilege/bootstrapRoleHasBuildPrivilege',
	    		data: 'roleId='+roleId,
	    		success : function(result) {
	    			$('#treeview-checkable').treeview('uncheckAll', { silent: true });
	    			returnShow(result);
	    			}
	    	});
        }
	   	 function returnShow(result){
	 	    		var firstNode = $('#treeview-checkable').treeview('getNode', 0);
	 	    		var Siblings = $('#treeview-checkable').treeview('getSiblings', firstNode);
	 	    		checkRoleNode(firstNode,result);
	 	    		 for ( var p in Siblings) {
	 	    			 checkRoleNode(Siblings[p],result);
	 				} 
	 	 }
	   	function checkRoleNode(node,pIds){
	   			getChiles(node,pIds);
	   	}
	   	function getChiles(node,pIds){
	   		 var sno = node.nodes;
	   		 for ( var s in sno) {
	   			for ( var k in pIds) {
	   				if(sno[s].id==pIds[k]){
	   					$('#treeview-checkable').treeview('checkNode',[sno[s].nodeId,{silent:true}]);
	   					CheckedParents(sno[s]);
   					}
   				}
   			getChiles(sno[s],pIds);
	   		}
	   	}
	   	
	   	/**发送数据,实现绑定***/
	    function dataTue(){
	    	var l = "";
    		var s = $('#treeview-checkable').treeview('getChecked');
            for ( var v in s) {
            	if(s[v].id == 0)continue;
            	if (l != '') l += '_';
            	l += s[v].id; 
          		}
            sendData(l);
    		}
	    function sendData(l){
    		var roleId = roleInfo_id;//$("#s1").val();
    	    $.ajax({
        		type : "POST",
        		dataType : "json",
        		url : baseUrl + 'privilege/bootstrapRoleBuildPrivilege',
        		data: 'codes='+l+'&roleId='+roleId,
        		success : function(result) {
        			console.log(result);
        			alert(result.msg);
        			}
        	});
    	}
/**********************************************************角色信息修改****************************************************************************/
 // 角色新增
    function addRole() {
    	var roleName = $("#roleName").val();
    	if (roleName == null || roleName == "") {
    		alert("角色不能为空！");
    		return;
    	}
    	$.ajax({
    		type : "POST",
    		dataType : "json",
    		url : baseUrl + 'privilege/addRole',
    		data : 'roleName=' + roleName,
    		success : function(result) {
    			// 加载最大可退金额
    			alert(result.msg);
    			if (result.msg == "新增角色成功") {
    				getRole();
    			}
    		}
    	});
    }
    // 删除角色
    function deleteRole(a) {
    	$.ajax({
    		type : "GET",
    		dataType : "json",
    		url : baseUrl + 'privilege/deleteRole' + '?roleId=' + a.id,
    		success : function(result) {
    			alert(result.msg);
    			if (result.msg == "删除角色成功") {
    				getRole();
    			}
    		}
    	});
    }
    // 修改角色
    function update(roleId,roleName) {
    	$("#role_name").val(roleName);
    	$("#role_id").val(roleId);
    	$("#updateRole").modal();
    	/*var roleId = a.id;
    	$("#updateRole").modal();
    	$("#role_id").val(roleId);
    	$("#role_name").val(roleId);
    	art.dialog({
    	    id: 'shake-demo',
    	    title: '修改角色',
    	    content:    '角色名称：<input id="name" type="text" value="" />',
    	    initialize: function () {
    	        document.getElementById('name').focus();
    	    },
    	    lock: true,
    	    fixed: true,
    	    ok: function () {
    	      var name = document.getElementById('name');
    	      realyUpdateRole(a,name.value);
    	      alert();
    	      return true;
    	    },
    	    okValue: '确定',
    	    cancel: function () {}
    	});*/
    }
    // 保存修改信息
    function realupdateRole() {
    	var roleId = $("#role_id").val(); 
    	var roleName = $("#role_name").val();
    	$.ajax({
    		type : "POST",
    		dataType : "json",
    		url : baseUrl + 'privilege/updateRole',
    		data : 'roleId=' + roleId + '&roleName=' + roleName,
    		success : function(result) {
    			alert(result.msg);
    			if (result.msg == "修改角色成功") {
    				getRole();
    			}
    		}
    	});
    }
    
 // 获取全部角色
    function getRole() {
    	var html = "";
    	$.ajax({type : "GET",
    				dataType : "json",
    				url : baseUrl + 'privilege/getRole',
    				success : function(obj) {
    					for (var r = 0; r < obj.length; r++) {
    						html = html + "<div class=\"txt\">"+obj[r].roleName+"<span class=\"date badge badge-important mgl10\"> <a id=\""+obj[r].roleId+"\" href=\"#\" onclick = \"roleHasBuildPrivilege(this)\">查看权限</a> </span> </div>"+"<a id=\""+obj[r].roleId+"\" onclick=\"update(\'"+obj[r].roleId+"\',\'"+obj[r].roleName+"\')\" class=\"tip\" href=\"#\" title=\"Edit Task\">修改</a> <a id=\""+obj[r].roleId+"\" onclick=\"deleteRole(this)\" class=\"tip\" href=\"#\" title=\"Delete\">删除</a> </div></li>";
    					}
    					$("#roleInfos").html(html);
    				}
    			});
    }