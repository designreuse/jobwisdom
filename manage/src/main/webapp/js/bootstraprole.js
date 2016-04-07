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
        //子子菜单选择了以后,自动将夫夫节点也选上
        function CheckedParents(node){
        	var s = $('#treeview-checkable').treeview('getParent', node);
        	if(typeof(s.id)== "undefined"){
        		return;
        	}else{
        		$('#treeview-checkable').treeview('checkNode',[s.nodeId,{silent:true}]);
        		//$('#treeview-checkable').treeview('getNode', s.nodeId).state.checked=true;
        		CheckedParents(s);
    		}
        }
/*******************************************************************自定义js********************************************************************/        
      //$('#treeview-checkable').treeview('checkNode',[[1,2,3],{silent:true}]);将制定的节点选择
      //$('#treeview-checkable').treeview('getChecked');//获取已经选择的节点信息
        function dataTue(){
    	    //选择的pId 2_5_13_14_15_6_16_17_18_19_20_21_56|3_5_13_14_1 使用‘,’隔开了每个角色下面的权限,每一组的第一个是角色的id,后面为pId
    		var l ='';
    		var s = $('#treeview-checkable').treeview('getChecked');
            for ( var v in s) {
            	if(typeof($('#treeview-checkable').treeview('getParent', s[v]).id)== "undefined"){
            		if(l != '')l += ',';
                	l += s[v].id; 
            		continue;
            	}else{
            	if(s[v].id == 0)continue;
            	if (l != '') l += '_';
            	l += s[v].id; 
            	}
          		}
            sendData(l);
    		}
    	function getParents(node){
    		var s = $('#treeview-checkable').treeview('getParent', node);
    		if(typeof(s.id)== "undefined")return false;
    		getParents(s);
    		return true;
    	}
    	function getRole(){
    		 var o = $('#treeview-checkable').treeview('search', [ '平台管理员', {
         	}]);
              console.log(s[0]==o[0]);
    	}
    	function sendData(l){
    		var userId = $("#s1").val();
    		$.ajax({
        		type : "POST",
        		dataType : "json",
        		url : baseUrl + 'privilege/bootstrapUserBuildPrivilege',
        		data: 'codes='+l+'&userId='+userId,
        		success : function(result) {
        			alert(result.msg);
        			}
        	});
    	}
/***************************人员已有权限回显********************************/
    	 function findUserRolePrivilege(){
    		 var userId = $("#s1").val();
    			$.ajax({
    	    		type : "POST",
    	    		dataType : "json",
    	    		url : baseUrl + 'privilege/bootstrapUserHasBuildPrivilege',
    	    		data: 'userId='+userId,
    	    		success : function(result) {
    	    			$('#treeview-checkable').treeview('uncheckAll', { silent: true });
    	    			returnShow(result);
    	    			}
    	    	});
    	 }
    	 function returnShow(result){
    	    for (var i = 0; i < result[0].length; i++) {
    	    	var role  = result[0][i];
    	    	var privilege = result[1][i][role];
    	    		var firstNode = $('#treeview-checkable').treeview('getNode', 0);
    	    		var Siblings = $('#treeview-checkable').treeview('getSiblings', firstNode);
    	    		checkRoleNode(firstNode,role,privilege);
    	    		 for ( var p in Siblings) {
    	    			 checkRoleNode(Siblings[p],role,privilege);
    				} 
    			 } 
    	 }
    	 function checkRoleNode(node,role,privilege){
    		 if(node.id==role){
    			 $('#treeview-checkable').treeview('checkNode',[node.nodeId,{silent:true}]);
    			 getChiles(node,privilege);
    		 }
    	 }
    	function getChiles(node,privilege){
    		 var sno = node.nodes;
    		 for ( var s in sno) {
    			for ( var k in privilege) {
    				if(sno[s].id==privilege[k]){
    					$('#treeview-checkable').treeview('checkNode',[sno[s].nodeId,{silent:true}]);
    					CheckedParents(sno[s]);
    				}
    			}
    			getChiles(sno[s],privilege);
    		}
    	}
/***************************人员已有权限回显********************************/
    	
window.onload=function(){
	userOption();
}   
/*用户信息组装*/
function userOption(){
	var obj = eval("("+userList+")");
	var html = "";
	for (var i = 0; i < obj.length; i++) {
		html = html + "<option value=\""+obj[i].userId+"\">"+obj[i].realName;
	}
	$("#s1").html(html);
}
    	