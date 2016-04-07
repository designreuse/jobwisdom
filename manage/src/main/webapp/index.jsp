<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>Bootstrap Tree View</title>
    <link href="<%=basePath %>css/bootstrap.css" rel="stylesheet">
    <link href="<%=basePath %>css/bootstrap-treeview.css" rel="stylesheet"> 
  </head>
     <script src="<%=basePath %>js/jquery-1.7.1.min.js"></script>
  	 <script src="<%=basePath %>js/bootstrap-treeview.js"></script>
  <body> 
  <input id="a" type="button" value="获取选择的节点" onclick="a()"/>
        <div class="col-sm-4">
          <h2>Tree</h2>
          <div id="treeview-checkable" class=""></div>
        </div>
  	<script type="text/javascript">
  	    var baseUrl = '<%=basePath%>';
        var defaultData = [{"id":1,"nodes":[{"classname":"","id":0,"nodes":[{"classname":"","id":1,"text":"首页看板"},{"classname":"","id":2,"text":"开户注册"},{"classname":"","id":3,"nodes":[{"classname":"","id":7,"text":"客户查询"},{"classname":"","id":8,"text":"客户互动"}],"text":"客户管理"},{"classname":"","id":4,"nodes":[{"classname":"","id":9,"text":"销售线索"},{"classname":"","id":10,"text":"项目销售"},{"classname":"","id":11,"text":"销售统计"},{"classname":"","id":12,"text":"充值设置"}],"text":"销售管理"}],"text":"菜单权限"},{"classname":"","id":0,"nodes":[{"classname":"","id":22,"text":"用户新增"},{"classname":"","id":23,"text":"角色新增"},{"classname":"","id":24,"text":"授权"},{"classname":"","id":25,"text":"拉去人员信息"},{"classname":"","id":26,"text":"跳转至角色新增页面"},{"classname":"","id":27,"text":"跳转至权限新增页面"},{"classname":"","id":28,"text":"获取所有角色"},{"classname":"","id":29,"text":"删除角色"},{"classname":"","id":30,"text":"更新角色"},{"classname":"","id":31,"text":"更新角色"},{"classname":"","id":32,"text":"更新角色"},{"classname":"","id":33,"text":"获取权限,进行角色配置"},{"classname":"","id":34,"text":"获取二级菜单"},{"classname":"","id":35,"text":"获取功能权限"},{"classname":"","id":36,"text":"获取元素权限"},{"classname":"","id":37,"text":"角色权限绑定"},{"classname":"","id":38,"text":"角色权限绑定"},{"classname":"","id":39,"text":"人员授权"},{"classname":"","id":40,"text":"获得树形"},{"classname":"","id":41,"text":"获得树形"},{"classname":"","id":42,"text":"获得树形"},{"classname":"","id":43,"text":"跳转角色权限绑定"},{"classname":"","id":44,"text":"角色权限绑定"},{"classname":"","id":45,"text":"人员权限绑定-跳转设定"},{"classname":"","id":46,"text":"人员权限绑定"},{"classname":"","id":47,"text":"跳转至权限修改页面"},{"classname":"","id":48,"text":"修改权限"},{"classname":"","id":49,"text":"删除权限"},{"classname":"","id":50,"text":"人员角色绑定"},{"classname":"","id":51,"text":"人员角色绑定"},{"classname":"","id":52,"text":"人员角色绑定"},{"classname":"","id":53,"text":"人员角色绑定"},{"classname":"","id":55,"text":"首页查看"}],"text":"功能权限"},{"classname":"","id":0,"text":"元素权限"}],"text":"平台管理员"},{"id":2,"nodes":[{"classname":"","id":0,"nodes":[{"classname":"","id":5,"nodes":[{"classname":"","id":13,"text":"在线交流"},{"classname":"","id":14,"text":"资料下载"},{"classname":"","id":15,"text":"发布公告"}],"text":"内部交流"},{"classname":"","id":6,"nodes":[{"classname":"","id":16,"text":"销售项目设置"},{"classname":"","id":17,"text":"角色权限绑定"},{"classname":"","id":18,"text":"新增权限设置"},{"classname":"","id":19,"text":"销售项目设置"},{"classname":"","id":20,"text":"帐号权限设置"},{"classname":"","id":21,"text":"短信通道设置"}],"text":"系统设置"}],"text":"菜单权限"},{"classname":"","id":0,"text":"功能权限"},{"classname":"","id":0,"nodes":[{"classname":"","id":56,"text":"首页看版新增按钮"}],"text":"元素权限"}],"text":"后台管理员"},{"id":3,"nodes":[{"classname":"","id":0,"nodes":[{"classname":"","id":5,"nodes":[{"classname":"","id":13,"text":"在线交流"},{"classname":"","id":14,"text":"资料下载"},{"classname":"","id":15,"text":"发布公告"}],"text":"内部交流"},{"classname":"","id":6,"nodes":[{"classname":"","id":16,"text":"销售项目设置"},{"classname":"","id":17,"text":"角色权限绑定"},{"classname":"","id":18,"text":"新增权限设置"},{"classname":"","id":19,"text":"销售项目设置"},{"classname":"","id":20,"text":"帐号权限设置"},{"classname":"","id":21,"text":"短信通道设置"}],"text":"系统设置"}],"text":"菜单权限"},{"classname":"","id":0,"nodes":[{"classname":"","id":44,"text":"角色权限绑定"},{"classname":"","id":45,"text":"人员权限绑定-跳转设定"},{"classname":"","id":46,"text":"人员权限绑定"},{"classname":"","id":47,"text":"跳转至权限修改页面"},{"classname":"","id":48,"text":"修改权限"},{"classname":"","id":49,"text":"删除权限"},{"classname":"","id":50,"text":"人员角色绑定"},{"classname":"","id":51,"text":"人员角色绑定"},{"classname":"","id":52,"text":"人员角色绑定"},{"classname":"","id":53,"text":"人员角色绑定"},{"classname":"","id":55,"text":"首页查看"}],"text":"功能权限"},{"classname":"","id":0,"text":"元素权限"}],"text":"平台收银员"},{"id":5,"nodes":[{"classname":"","id":0,"text":"菜单权限"},{"classname":"","id":0,"text":"功能权限"},{"classname":"","id":0,"text":"元素权限"}],"text":"的说法都是"}];

        var $checkableTree = $('#treeview-checkable').treeview({
          data: defaultData,
          showIcon: false,
          showCheckbox: true,
          onNodeChecked: function(event, node) {
        	  parentCheckedWithChild(node);
            //$('#checkable-output').prepend('<p>' + node.nodes + ' was checked</p>');
            //$('#checkable-output').prepend('<p>' + node.id + ' was checked</p>');
          },
          onNodeUnchecked: function (event, node) {
        	  parentUnCheckedWithChild(node);
            $('#checkable-output').prepend('<p>' + node.text + ' was unchecked</p>');
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
   
  		
  		function a(){
  			//$('#treeview-checkable').treeview('checkNode',[[1,2,3],{silent:true}]);将制定的节点选择
  			//$('#treeview-checkable').treeview('getChecked');//获取已经选择的节点信息
  			var s = $('#treeview-checkable').treeview('getChecked');
            for ( var v in s) {
  			 console.log(s[v].text);
           }
  		}
  	</script>
</body>
</html>

