<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>散客收银</title>
<%@include file="/css.jsp" %>
<style>
.btn-lg, .btn-group-lg>.btn {
	padding: 4px 10px;
	font-size: 18px;
	line-height: 1.33;
	border-radius: 6px;
}

.btn {
	display: inline;
	padding: 5px 39px;
	margin-bottom: 0;
	font-size: 14px;
	font-weight: 400;
	line-height: 1.42857143;
	text-align: center;
	white-space: nowrap;
	vertical-align: middle;
	cursor: pointer;
	-webkit-user-select: none;
	user-select: none;
	background-image: none;
	border: 1px solid transparent;
	border-radius: 4px;
}

.btn:hover, .btn:focus {
	color: #060606;
	text-decoration: none;
}

.btn-dangers {
	color: #000;
	background-color: #fff;
	border-color: #3997f3;
}

.dropdown-menu>li>a {
	display: block;
	padding: 3px 52px;
	clear: both;
	font-weight: 400;
	line-height: 1.42857143;
	color: #333;
	white-space: nowrap;
}

.dropdown-menu .divider {
	height: 1px;
	margin: 2px 0;
	overflow: hidden;
	background-color: #e5e5e5;
}

.dropmenus {
	color: #060606;
	text-decoration: none;
	background-color: #1A75C3;
}

.appoints {
	width: 55px;
	border-radius: 0px;
	height: 40px;
	border-color: #EEEEEE;
	border-right: 1px solid #c6c6c6;
	border-top: 1px solid #c6c6c6;
	background: #fff;
	border-bottom: 1px solid #c6c6c6;
}

.wocaole {
    display: none;
}
</style>
</head>
<body>
  <%@include file="/head.jsp" %> 
  <div class="container">
    <%@include file="/sidebar.jsp" %>
    <div id="content" class="fl">
 		<div class="content-submenu">
		    <ul class="submunu clearfix">
		        <li >
		            <a href="index.html">
		                <span>散客收银</span>
		            </a>
		        </li>
		        <li>
		            <a href="vip.html">
		                <span>会员消费</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>新增会员</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>自助账单</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>账单查询</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>顾客预约</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>动态轮牌</span>
		            </a>
		        </li>
		        <li>
		            <a href="">
		                <span>收支记账</span>
		            </a>
		
		        </li>
		        <li class="active">
		            <a href="project_setting.html">
		                <span>项目设置</span>
		            </a>
		        </li>
		    </ul>
		</div>
	    <div class="box live-tab  content-table">
	        <ul class="tab_menu">
	            <li class="current">美发消费</li>
	            <li>美容消费</li>
	            <li>商品销售</li>
	        </ul>
	        <div class="tab_box">
	            <div>
	                <table class="table hair" style="width: 905px;">
	                    <thead>
	                    <tr>
	                        <th class="title project">
	                            <div class="minus" id ="project_minus"><img src="<%=cssBasePath %>app/img/head/minus.png" alt=""/></div>
	                            美发服务项目
	                            <div class="plus" id ="project_plus"><img src="<%=cssBasePath %>app/img/head/plus.png" alt=""/></div>
	                        </th>
	
	                        <th class="title career">发型设计师</th>
	
	                        <th class="title designer">美发技师</th>
	
	                        <th class="title technician">美发助理</th>
	                    </tr>
	                    </thead>
	                    <tbody id="hair_class">
	                    <tr class="project-head">
	                        <td class="border project ">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="code" >编号</td>
	                                    <td class="project-name">项目名称</td>
	                                    <td class="money">价格</td>
	                                    <td class="discount" >优惠</td>
	                                    <td class="sum">金额</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="border career">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="agent-id">工号</td>
	                                    <td class="appoint">指定</td>
	                                    <td class="performance">业绩</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="border designer">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="agent-id">工号</td>
	                                    <td class="appoint">指定</td>
	                                    <td class="performance">业绩</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="border technician">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="agent-id">工号</td>
	                                    <td class="appoint">指定</td>
	                                    <td class="performance">业绩</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	
	                    <tr class="project-body pro_tr">
	                        <td class="project last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="code" ><input type="hidden" name="proid"><input type="text" name="prono" class=" border-left-none"></td>
	                                    <td class="name"><input type="text" name="proname"></td>
	                                    <td class="money"><input type="text" name="proprice"  readonly="true"></td>
	                                    <td class="discount" ><input type="text"  name="procoupon" onkeyup="value=this.value.replace(/\D+/g,'')"></td>
	                                    <td class="sum " ><input type="text"  name="promoney" onkeyup="value=this.value.replace(/\D+/g,'')" class="border-right-none"></td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	                        <td class="career last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
		                                <td class="agent-id">
	                                        <div class="btn-group dropdown" supdiv = "supdiv">
		                                        <a data-toggle="dropdown" class="dropdown-toggle" id="semployeenoDropdown" href="javascript://" supname = "supname">
		                                           <input type="text" name="semployeeno"  dropdowninput = "dropdowninput" class="border-left-none">
		                                           <input type="hidden" name="semployeeid">
		                                        </a>
		                                        <ul class='dropdown-menu' ></ul>
						                    </div>
	                                    </td>
	                                    <td class="appoint">
		                                    <div class="btn-group dropup" supdiv = "supdiv">
								               <button type="button" class="appoints" data-toggle="dropdown" id = "sappoint" supname = "supname">是</button>
								               <ul class="dropdown-menu" style="width:160px;">
									              <li><a href="javascript://" supa = "supa">是</a></li>
									              <li role="presentation" class="divider"></li>
									              <li><a href="javascript://" supa = "supa">否</a></li>
								               </ul>
								            </div>
                                        </td>
	                                    <td class="performance">
	                                        <input type="text" name="sperformance" onkeyup="value=this.value.replace(/\D+/g,'')" class=" border-right-none">
	                                    </td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="designer last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="dropdown agent-id">
                                            <div class="btn-group dropdown" supdiv = "supdiv">
		                                        <a data-toggle="dropdown" class="dropdown-toggle" id="semployeenoDropdown" href="javascript://" supname = "supname">
		                                           <input type="text" name="memployeeno"  dropdowninput = "dropdowninput" class="border-left-none">
		                                           <input type="hidden" name="memployeeid">
		                                        </a>
		                                        <ul class='dropdown-menu' ></ul>
						                    </div>
                                        </td>
	                                    <td class="appoint">
	                                        <div class="btn-group dropup" supdiv = "supdiv">
								               <button type="button" class="appoints" data-toggle="dropdown" id = "mappoint" supname = "supname">是</button>
								               <ul class="dropdown-menu" style="width:160px;">
									              <li><a href="javascript://" supa = "supa">是</a></li>
									              <li role="presentation" class="divider"></li>
									              <li><a href="javascript://" supa = "supa">否</a></li>
								               </ul>
								             </div>
	                                    </td>
	                                    <td class="performance ">
	                                        <input type="text"  name="mperformance" onkeyup="value=this.value.replace(/\D+/g,'')" class="border-right-none">
	                                    </td>
	                                </tr>
	
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="technician last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input ">
	                                    <td class="dropdown agent-id">
	                                         <div class="btn-group dropdown" supdiv = "supdiv">
		                                        <a data-toggle="dropdown" class="dropdown-toggle" id="semployeenoDropdown" href="javascript://" supname = "supname">
		                                           <input type="text" name="aemployeeno"  dropdowninput = "dropdowninput" class="border-left-none">
		                                           <input type="hidden" name="aemployeeid">
		                                        </a>
		                                        <ul class='dropdown-menu' ></ul>
						                    </div>
                                        </td>
	                                    <td class="appoint">
	                                        <div class="btn-group dropup" supdiv = "supdiv">
								               <button type="button" class="appoints" data-toggle="dropdown" id = "aappoint" supname = "supname">是</button>
								               <ul class="dropdown-menu" style="width:160px;">
									              <li><a href="javascript://" supa = "supa">是</a></li>
									              <li role="presentation" class="divider"></li>
									              <li><a href="javascript://" supa = "supa">否</a></li>
								               </ul>
								            </div>
	                                    </td>
	                                    <td class="performance">
	                                        <input type="text"  name="aperformance" onkeyup="value=this.value.replace(/\D+/g,'')" class="border-right-none">
	                                    </td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	                    </tbody>
	                </table>
	            </div>
	
	            <!--美容消费-->
	            <div class="hide">
	                <table class="table hair face">
	                    <thead>
	                    <tr>
	                        <th class="title project project-title">
	                            <div class="minus"><img src="<%=cssBasePath %>app/img/head/minus.png" alt=""/></div>
	                                                                                                美发服务项目
	                            <div class="plus"><img src="<%=cssBasePath %>app/img/head/plus.png" alt=""/></div>
	                        </th>
	
	                        <th class="title career">发型设计师</th>
	
	                        <th class="title designer">美发技师</th>
	
	                        <th class="title technician">美发助理</th>
	                    </tr>
	                    </thead>
	                    <tbody>
	                    <tr class="project-head">
	                        <td class="border project ">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="code" >编号</td>
	                                    <td class="name">项目名称</td>
	                                    <td class="money">价格</td>
	                                    <td class="discount" >优惠</td>
	                                    <td class="sum">实收金额</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="border career">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="agent-id">工号</td>
	                                    <td class="appoint">指定</td>
	                                    <td class="performance">业绩</td>
	                                    <td class="allocation">分配</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="border designer">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="agent-id">工号</td>
	                                    <td class="appoint">指定</td>
	                                    <td class="performance">业绩</td>
	                                    <td class="allocation">分配</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="border technician">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="agent-id">工号</td>
	                                    <td class="appoint">指定</td>
	                                    <td class="performance">业绩</td>
	                                    <td class="allocation">分配</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	
	                    <tr class="project-body">
	                        <td class="project">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="code" ><input type="text" ></td>
	                                    <td class="name"><input type="text" ></td>
	                                    <td class="money"><input type="text" ></td>
	                                    <td class="discount" ><input type="text" ></td>
	                                    <td class="sum"><input type="text" ></td>
	                                    <td class="code"><input type="text" ></td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="career">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="agent-id dropdown">
	                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                                            <input type="text" >
	                                        </a>
	                                        <ul class="dropdown-menu">
	                                            <li><a href="#">jmeter</a></li>
	                                            <li><a href="#">EJB</a></li>
	                                            <li><a href="#">Jasper Report</a></li>
	                                            <li class="divider"></li>
	                                            <li><a href="#">分离的链接</a></li>
	                                            <li class="divider"></li>
	                                            <li><a href="#">另一个分离的链接</a></li>
	                                        </ul>
	                                    </td>
	                                    <td class="appoint">
	                                        <select name="" id="">
	                                            <option value="1">1</option>
	                                            <option value="2">2</option>
	                                        </select>
	                                    </td>
	                                    <td class="performance">
	                                        <input type="text" >
	                                    </td>
	                                    <td class="allocation">
	                                        <input type="text" >
	                                    </td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="designer">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="agent-id">
	                                        <input type="text" >
	                                    </td>
	                                    <td class="appoint">
	                                        <select name="" id="">
	                                            <option value="1">1</option>
	                                            <option value="2">2</option>
	                                        </select>
	                                    </td>
	                                    <td class="performance">
	                                        <input type="text" >
	                                    </td>
	                                    <td class="allocation">
	                                        <input type="text" >
	                                    </td>
	                                </tr>
	
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="technician">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="agent-id">
	                                        <input type="text" >
	                                    </td>
	                                    <td class="appoint">
	                                        <select name="" id="">
	                                            <option value="1">1</option>
	                                            <option value="2">2</option>
	                                        </select>
	                                    </td>
	                                    <td class="performance">
	                                        <input type="text" >
	                                    </td>
	                                    <td class="allocation">
	                                        <input type="text" >
	                                    </td>
	                                </tr>
	
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	                    </tbody>
	                </table>
	            </div>
	
	            <!--商品消费-->
	            <div class="hide">
	                <table class="table hair good-sale ">
	                    <thead>
	                    <tr>
	                        <th class="title project good-sale-title">
	                            <div class="minus"><img src="<%=cssBasePath %>app/img/head/minus.png" alt=""/></div>
	                                                                                         美发服务项目
	                            <div class="plus"><img src="<%=cssBasePath %>app/img/head/plus.png" alt=""/></div>
	                        </th>
	
	                        <th class="title career">销售员（1）</th>
	
	                        <th class="title designer">销售员（1）</th>
	                    </tr>
	                    </thead>
	                    <tbody>
	                    <tr class="project-head">
	                        <td class="border project ">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="code" >编号</td>
	                                    <td class="name">商品名称</td>
	                                    <td class="stock">库存量</td>
	                                    <td class="unit" >单位</td>
	                                    <td class="price">价格</td>
	                                    <td class="num">数量</td>
	                                    <td class="privilege">优惠</td>
	                                    <td class="money">金额</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="border career">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="agent-id">工号</td>
	                                    <td class="appoint">指定</td>
	                                    <td class="performance">业绩</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	                        <td class="border designer">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="agent-id">工号</td>
	                                    <td class="appoint">指定</td>
	                                    <td class="performance">业绩</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	
	                    <tr class="project-body">
	                        <td class="project">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="code" ><input type="text"/></td>
	                                    <td class="name"><input type="text"/></td>
	                                    <td class="stock"><input type="text"/></td>
	                                    <td class="unit" ><input type="text"/></td>
	                                    <td class="price"><input type="text"/></td>
	                                    <td class="num"><input type="text"/></td>
	                                    <td class="privilege"><input type="text"/></td>
	                                    <td class="money"><input type="text"/></td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="career">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="agent-id dropdown">
	                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">
	                                            <input type="text" >
	                                        </a>
	                                        <ul class="dropdown-menu">
	                                            <li><a href="#">jmeter</a></li>
	                                            <li><a href="#">EJB</a></li>
	                                            <li><a href="#">Jasper Report</a></li>
	                                            <li class="divider"></li>
	                                            <li><a href="#">分离的链接</a></li>
	                                            <li class="divider"></li>
	                                            <li><a href="#">另一个分离的链接</a></li>
	                                        </ul>
	                                    </td>
	                                    <td class="appoint">
	                                        <select name="" id="">
	                                            <option value="1">1</option>
	                                            <option value="2">2</option>
	                                        </select>
	                                    </td>
	                                    <td class="performance">
	                                        <input type="text" >
	                                    </td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	                        <td class="designer">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input last-tr" >
	                                    <td class="agent-id">
	                                        <input type="text" >
	                                    </td>
	                                    <td class="appoint">
	                                        <select name="" id="">
	                                            <option value="1">1</option>
	                                            <option value="2">2</option>
	                                        </select>
	                                    </td>
	                                    <td class="performance">
	                                        <input type="text" >
	                                    </td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	                    </tbody>
	                </table>
	            </div>
	        </div>
	    </div><!--demo2 end-->
	
	    <!--下拉菜单-->
	    <div class="dropdown-input" id="upWindow" style="display: none;">
		    <div class="arrow">
		        
		    </div>
		</div>
	    
	    <!-- 模态框 -->
		<div class="modal fade" id="modelID" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" ><span aria-hidden="true">&times;</span></button>
		            </div>
		            <div class="modal-body">
		                <table>
		                    <tbody>
		                    <tr>
		                        <td>
		                            <input  type="password" autocomplete="off" name="qd_code1" maxlength="1"/>
		                        </td>
		                        <td>
		                            <input  type="password" autocomplete="off" name="qd_code2" maxlength="1"/>
		                        </td>
		                        <td>
		                            <input  type="password" autocomplete="off" name="qd_code3" maxlength="1"/>
		                        </td>
		                        <td>
		                            <input  type="password" autocomplete="off" name="qd_code4" maxlength="1"/>
		                        </td>
		                        <td>
		                            <input  type="password" autocomplete="off" name="qd_code5" maxlength="1"/>
		                        </td>
		                        <td>
		                            <input  type="password" autocomplete="off" name="qd_code6" maxlength="1"/>
		                        </td>
		                    </tr>
		                    </tbody>
		                </table>
		            </div>
		        </div>
		    </div>
		</div>
	    
	    <!--支付-->
	    <div class="pay">
	        <div class="pay-left">
	            <div class="info">
	                <img src="<%=cssBasePath %>app/img/touxiang.jpg" alt=""/>
	                <span>张美丽</span>
	            </div>
	            <div class="pay-line1" >
	                <div class="btn-group dropup" supdiv = "supdiv" style="margin-bottom: 10px;">
		               <button type="button" class="btn btn-dangers" data-toggle="dropdown" id = "customerType" supname = "supname" style="width:135px;">学生</button>
		               <button type="button" class="btn btn-dangers dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="padding: 13px 7px;">
		               <span class="caret"></span>
		               <span class="sr-only"></span>
		               </button>
		            <ul class="dropdown-menu" style="width:160px;">
		              <li><a href="javascript://" supa = "supa">女</a></li>
		              <li role="presentation" class="divider"></li>
		              <li><a href="javascript://" supa = "supa">男</a></li>
		              <li role="presentation" class="divider"></li>
		              <li><a href="javascript://" supa = "supa">学生</a></li>
		              <li role="presentation" class="divider"></li>
		              <li><a href="javascript://" supa = "supa">老人</a></li>
		              <li role="presentation" class="divider"></li>
		              <li><a href="javascript://" supa = "supa">小孩</a></li>
		             </ul>
		           </div>
		            <div class="btn-group dropup" supdiv = "supdiv" style="margin-bottom: 10px;">
		               <button type="button" class="btn btn-dangers" data-toggle="dropdown" id = "customerSource" supname = "supname" style="width:135px;">自由进店</button>
		               <button type="button" class="btn btn-dangers dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="padding: 13px 7px;">
		               <span class="caret"></span>
		               <span class="sr-only"></span>
		               </button>
		            <ul class="dropdown-menu" style="width:160px;">
		              <li><a href="javascript://" supa = "supa">自由进店</a></li>
		              <li class="divider"></li>
		              <li><a href="javascript://" supa = "supa">客户推荐</a></li>
		              <li class="divider"></li>
		              <li><a href="javascript://" supa = "supa">商圈拓客</a></li>
		              <li class="divider"></li>
		              <li><a href="javascript://" supa = "supa">网咯拓客</a></li>
		             </ul>
		           </div>
		            <div class="btn-group dropup" supdiv = "supdiv" style="margin-bottom: 10px;">
		               <button type="button" class="btn btn-dangers" id = "customerNum" data-toggle="dropdown" supname = "supname" style="width:135px;">一人多项</button>
		               <button type="button" class="btn btn-dangers dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="padding: 13px 7px;">
		               <span class="caret"></span>
		               <span class="sr-only"></span>
		               </button>
		             <ul class="dropdown-menu" style="width:160px;">
		              <li><a href="javascript://" supa = "supa">一人多项</a></li>
		              <li role="presentation" class="divider"></li>
		              <li><a href="javascript://" supa = "supa">一人一项</a></li>
		             </ul>
		            </div>
	            </div>
	            <div class="pay-line2">
	                <div class="input-border huiyuan" style="margin-bottom: 10px;">
	                    <label for="danhao ">单号 &nbsp;&nbsp;|</label>
	                    <input type="text" name="orderNo"/>
	                    <img src="<%=cssBasePath %>app/img/jisuan.png" alt=""/>
	                </div>
	                <div class="input-border huiyuan">
	                    <label for="danhao huiyuan">签单 &nbsp;&nbsp;|</label>
	                    <input type="text" name="qdBill" onkeyup="value=this.value.replace(/\D+/g,'')"/>
	                    <input type="hidden" name = "qdEmployeeId">
	                    <img src="<%=cssBasePath %>app/img/qiandan.png" alt=""/>
	                </div>
	            </div>
	            <div class="pay-line2 tag-huaka" >
	                <div class="input-border2 btn-group dropup" style="margin-bottom: 10px;" supdiv = "supdiv">
	                    <a data-toggle="dropdown" class="dropdown-toggle"  href="javascript://" supname = "supname" style="display: inline-block; float: left;text-decoration:none; color: #757575;font-size: 12px;font-weight: 700;">划卡</a>
	                        <label for="danhao " class="xianjin">&nbsp;&nbsp;|</label>
	                    <ul class="dropdown-menu" aria-labelledby="cardBillDropdown">
	                         <li><a href="javascript://" supa = "supa">划卡</a></li>
	                         <li class="divider"></li>
	                         <li><a href="javascript://" supa = "supa">礼金</a></li>
	                    </ul>
	                    <input type="text" name="cardBill" onkeyup="value=this.value.replace(/\D+/g,'')"/>
	                    <img src="<%=cssBasePath %>app/img/xianjin.png" alt=""/>
	                </div>
	                <div class="input-border2 btn-group dropup" supdiv = "supdiv">
	                    <a data-toggle="dropdown" class="dropdown-toggle" id="cashBillDropdown" href="javascript://" supname = "supname" style="display: inline-block; text-decoration:none; float: left;color: #757575;font-size: 12px;font-weight: 700;">现金</a>
	                        <label for="danhao " class="xianjin"><input type="hidden" >&nbsp;&nbsp;|</label>
	                    <ul class="dropdown-menu" aria-labelledby="cashBillDropdown">
	                        <li><a href="javascript://" supa = "supa">支付宝</a></li>
	                        <li class="divider"></li>
	                        <li><a href="javascript://" supa = "supa">银联</a></li>
	                        <li class="divider"></li>
	                        <li><a href="javascript://" supa = "supa">微信</a></li>
	                        <li class="divider"></li>
	                        <li><a href="javascript://" supa = "supa">现金</a></li>
	                    </ul>
	                    <input type="text" name="cashBill" onkeyup="value=this.value.replace(/\D+/g,'')"/>
	                    <img src="<%=cssBasePath %>app/img/yinlian.png" alt=""/>
	                </div>
	            </div>
	            <div class="pay-right">
	                <div class="pay-content " data-toggle="modal" data-target="#jiesuan">
	                    <span class="sum">
	                        <span>结算</span>
	                        <span style="display: block;font-size: 14px;">(空格键)</span>
	                    </span>
	                    <i></i>
	                    <span class="num">
	                        <span style="display: block;font-size: 14px;color: #fff;">总金额(元)</span>
	                        <span class="money">19008.00</span>
	                    </span>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<!-- 散客收银银出框 -->
	<div class="modal fade" id="jiesuan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	    <div class="modal-dialog" role="document">
	        <div class="modal-content zhangdan-model">
	            <div class="modal-header">
	                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	                <h3 class="modal-title">账单号: 105</h3>
	            </div>
	            <div class="modal-body">
	                <!--美发服务项目-->
	                <table class="table hair" style="margin-bottom: 10px;">
	                    <thead>
	                    <tr>
	                        <th class="title project">
	                            <div class="diamonds orange-diamonds"></div>
	                            美发服务项目
	                        </th>
	                    </tr>
	                    </thead>
	                    <tbody>
	                    <tr class="project-head">
	                        <td class="border project">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="code" >编号</td>
	                                    <td class="project-name">项目名称</td>
	                                    <td class="money">价格</td>
	                                    <td class="discount" >优惠</td>
	                                    <td class="sum">金额</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	
	                    <tr class="project-body">
	                        <td class="project last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="code" ><input type="text" class=" border-left-none"></td>
	                                    <td class="name"><input type="text" ></td>
	                                    <td class="money"><input type="text" ></td>
	                                    <td class="discount" ><input type="text" ></td>
	                                    <td class="sum " ><input type="text" class="border-right-none"></td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	                    </tbody>
	                </table>
	                <!--美容服务项目-->
	                <table class="table hair" style="margin-bottom: 10px;">
	                    <thead>
	                    <tr>
	                        <th class="title project">
	                            <div class="diamonds purple-diamonds"></div>
	                            美容服务项目
	                        </th>
	                    </tr>
	                    </thead>
	                    <tbody>
	                    <tr class="project-head">
	                        <td class="border project ">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="code" >编号</td>
	                                    <td class="project-name">项目名称</td>
	                                    <td class="money">价格</td>
	                                    <td class="discount" >优惠</td>
	                                    <td class="sum">金额</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	
	                    <tr class="project-body">
	                        <td class="project last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="code" ><input type="text" class=" border-left-none"></td>
	                                    <td class="name"><input type="text" ></td>
	                                    <td class="money"><input type="text" ></td>
	                                    <td class="discount" ><input type="text" ></td>
	                                    <td class="sum " ><input type="text" class="border-right-none"></td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	                    </tbody>
	                </table >
	
	                <!--商品销售项目-->
	                <table class="table hair">
	                    <thead>
	                    <tr>
	                        <th class="title project">
	                            <div class="diamonds blue-diamonds"></div>
	                            商品销售
	                        </th>
	                    </tr>
	                    </thead>
	                    <tbody>
	                    <tr class="project-head">
	                        <td class="border project ">
	                            <table >
	                                <tbody>
	                                <tr>
	                                    <td class="code" >编号</td>
	                                    <td class="project-name">项目名称</td>
	                                    <td class="money">价格</td>
	                                    <td class="discount" >优惠</td>
	                                    <td class="sum">金额</td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	
	                    <tr class="project-body">
	                        <td class="project last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="code" ><input type="text" class=" border-left-none"></td>
	                                    <td class="name"><input type="text" ></td>
	                                    <td class="money"><input type="text" ></td>
	                                    <td class="discount"><input type="text" ></td>
	                                    <td class="sum " ><input type="text" class="border-right-none"></td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>
	                    </tbody>
	                </table>
	            </div>
	            <div class="modal-footer">
	                <div class="total-amount">
	                    <span class="word">共 </span><span class="num">81360.00</span>
	                </div>
	              <div class="print select-tag">
	                      <input type="checkbox" class="check-all"/>
	                      <span></span>
	                      <h5 class="receipt">打印小票</h5>
	              </div>
	
	                <button type="button" class="btn cancel" data-dismiss="modal">取消</button>
	                <button type="button" class="btn confirm">确定</button>
	            </div>
	        </div>
	    </div>
	</div>
  </div>
  <%@ include file="/js.jsp"%>
  <script id="projectBody" type="text/html">
		<tr class="project-body pro_tr">
	                        <td class="project last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="code" ><input type="hidden" name="proid"><input type="text" name="prono" class=" border-left-none"></td>
	                                    <td class="name"><input type="text" name="proname"></td>
	                                    <td class="money"><input type="text" name="proprice"  readonly="true"></td>
	                                    <td class="discount" ><input type="text"  name="procoupon" onkeyup="value=this.value.replace(/\D+/g,'')"></td>
	                                    <td class="sum " ><input type="text"  name="promoney" onkeyup="value=this.value.replace(/\D+/g,'')" class="border-right-none"></td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	                        <td class="career last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
		                                <td class="agent-id dropdown">
	                                    	<input type="hidden" name="semployeeid">
	                                        <input type="text" name="semployeeno" class="border-left-none">
	                                    </td>
	                                    <td class="appoint">
		                                    <div class="btn-group dropup" supdiv = "supdiv">
								               <button type="button" class="appoints" data-toggle="dropdown" id = "sappoint" supname = "supname">是</button>
								               <ul class="dropdown-menu" style="width:160px;">
									              <li><a href="javascript://" supa = "supa">是</a></li>
									              <li role="presentation" class="divider"></li>
									              <li><a href="javascript://" supa = "supa">否</a></li>
								               </ul>
								            </div>
                                        </td>
	                                    <td class="performance">
	                                        <input type="text" name="sperformance" onkeyup="value=this.value.replace(/\D+/g,'')" class=" border-right-none">
	                                    </td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="designer last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input">
	                                    <td class="dropdown agent-id">
                                            <input type="hidden" name="memployeeid">
                                            <input type="text" name="memployeeno" class="border-left-none">
                                        </td>
	                                    <td class="appoint">
	                                        <div class="btn-group dropup" supdiv = "supdiv">
								               <button type="button" class="appoints" data-toggle="dropdown" id = "mappoint" supname = "supname">是</button>
								               <ul class="dropdown-menu" style="width:160px;">
									              <li><a href="javascript://" supa = "supa">是</a></li>
									              <li role="presentation" class="divider"></li>
									              <li><a href="javascript://" supa = "supa">否</a></li>
								               </ul>
								             </div>
	                                    </td>
	                                    <td class="performance ">
	                                        <input type="text"  name="mperformance" onkeyup="value=this.value.replace(/\D+/g,'')" class="border-right-none">
	                                    </td>
	                                </tr>
	
	                                </tbody>
	                            </table>
	                        </td>
	
	
	                        <td class="technician last-tr">
	                            <table>
	                                <tbody>
	                                <tr class="tr-input ">
	                                    <td class="dropdown agent-id">
	                                         <input type="hidden" name="aemployeeid">
	                                         <input type="text" name="aemployeeno" class="border-left-none">
                                        </td>
	                                    <td class="appoint">
	                                        <div class="btn-group dropup" supdiv = "supdiv">
								               <button type="button" class="appoints" data-toggle="dropdown" id = "aappoint" supname = "supname">是</button>
								               <ul class="dropdown-menu" style="width:160px;">
									              <li><a href="javascript://" supa = "supa">是</a></li>
									              <li role="presentation" class="divider"></li>
									              <li><a href="javascript://" supa = "supa">否</a></li>
								               </ul>
								            </div>
	                                    </td>
	                                    <td class="performance">
	                                        <input type="text"  name="aperformance" onkeyup="value=this.value.replace(/\D+/g,'')" class="border-right-none">
	                                    </td>
	                                </tr>
	                                </tbody>
	                            </table>
	                        </td>
	                    </tr>				
  </script>
  <script type="text/javascript">
  	var baseUrl = "<%=cssBasePath%>";
  </script>
  <script type="text/javascript" src="<%=cssBasePath %>js/payment/retail.js"></script>
</body>
</html>