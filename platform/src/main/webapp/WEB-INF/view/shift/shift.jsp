<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/head.jsp"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目</title>

<style>
    .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
        padding: 0px;
        border-top: 0px solid #fff;
    }
    .table > thead > tr > th {
        vertical-align: bottom;
        border-bottom: 0px solid #fff;
    }
    .alertWrap{
	  position: fixed;
	  top:0;
	  left:0;
	  height:100%;
	  width:100%;
	  z-index: 200;
	  text-align: center;
	  box-sizing:border-box;
	  -webkit-box-sizing:border-box;
	  display: none;
	}
	.alert{
	  display: inline-block;
	  background-color: rgba(0,0,0,.6);
	  color:#fff;
	  text-align: center;
	  padding: 10px;
	  margin:0 auto;
	  max-width:620px;
	  border-radius:5px;
	  -webkit-border-radius:5px;
	  box-sizing:border-box;
	  -webkit-box-sizing:border-box;
	}
  </style>

</head>
<body>
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
 
  <style>
    .table > thead > tr > th, .table > tbody > tr > th, .table > tfoot > tr > th, .table > thead > tr > td, .table > tbody > tr > td, .table > tfoot > tr > td {
        padding: 0px;
        border-top: 0px solid #fff;
    }
    .table > thead > tr > th {
        vertical-align: bottom;
        border-bottom: 0px solid #fff;
    }
     .modal-header {
        min-height: 16.42857143px;
        padding: 15px;
        border-bottom: 1px solid #ccc;
    }
    .modal-content {
        position: relative;
        background-color: #fff;
        -webkit-background-clip: padding-box;
        background-clip: padding-box;
        border: 1px solid #999;
        border: 1px solid rgba(0, 0, 0, 0.2);
        border-radius: 6px;
        outline: 0;
        -webkit-box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
        box-shadow: 0 3px 9px rgba(0, 0, 0, 0.5);
    }
    @media (min-width: 768px)
        .modal-dialog {
            width: 600px;
            margin: 150px auto;
    }
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
      .dropmenus{
		  color: #060606;
		  text-decoration: none;
		  background-color: #1A75C3;
	  }
	  .appoints{
	      width: 55px;
	      border-radius: 0px;
	      height: 40px;
	      border-color: #EEEEEE;
	      border-right: 1px solid #c6c6c6;
	      border-top: 1px solid #c6c6c6;
	      background: #fff;
	      border-bottom: 1px solid #c6c6c6;
	  }
</style>
 
<div class="box demo2  content-table">
    <ul class="tab_menu">
        <li class="current">美发消费</li>
        <li>美容消费</li>
        <li>商品销售</li>
    </ul>
    <div class="tab_box">
        <div>
            <table class="table hair">
                <thead>
                    <tr>
                        <th class="title project">
                            <div class="minus" id ="project_minus"><img src="<%=headBasePath %>app/img/head/minus.png" alt=""/></div>
                                                                                              美发服务项目
                            <div class="plus" id ="project_plus"><img src="<%=headBasePath %>app/img/head/plus.png" alt=""/></div>
                        </th>

                        <th class="title career">发型设计师</th>

                        <th class="title designer">美发技师</th>

                        <th class="title technician">美发助理</th>
                    </tr>
                </thead>
                <tbody id="hair_class">
                    <tr class="project-head">
                        <td class="border project">
                          <table >
                              <tbody>
                                <tr>
                                    <td class="code" >编号</td>
                                    <td calss="name">项目名称</td>
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
                    <tr  class ="pro_tr project-body">
                        <td class="project last-tr">
                            <table>
                                <tbody>
                                <tr class="tr-input">
                                    
                                    <td class="code"><input type="hidden" name="proid"><input type="text" name="prono" class=" border-left-none"></td>
                                    <td class="name"><input type="text" name="proname"></td>
                                    <td class="money"><input type="text" name="proprice"  readonly="true"></td>
                                    <td class="discount"><input type="text"  name="procoupon" onkeyup="value=this.value.replace(/\D+/g,'')"></td>
                                    <td class="sum"><input type="text"  name="promoney" onkeyup="value=this.value.replace(/\D+/g,'')" class="border-right-none"></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                        <td class ="career last-tr">
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
                                    <td class="performance"><input type="text" name="sperformance" onkeyup="value=this.value.replace(/\D+/g,'')" class=" border-right-none"></td>
                                </tr>
                                <%-- <tr class="pm">
                                    <td colspan="4" class="pm">
                                        <div class="plus" id="s_plus"><img src="<%=basePath %>app/img/head/plus.png" alt=""/></div>
                                        <div class="minus" id="s_minus"><img src="<%=basePath %>app/img/head/minus.png" alt=""/></div>

                                    </td>
                                </tr> --%>
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
                                    <td class="performance"><input type="text"  name="mperformance" onkeyup="value=this.value.replace(/\D+/g,'')" class="border-right-none"></td>
                                </tr>
                                <%-- <tr class="pm">
                                    <td colspan="4" class="pm">
                                        <div class="plus" id="m_plus"><img src="<%=basePath %>app/img/head/plus.png" alt=""/></div>
                                        <div class="minus" id="m_minus"><img src="<%=basePath %>app/img/head/minus.png" alt=""/></div>

                                    </td>
                                </tr> --%>

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
                                    <td class="performance"><input type="text"  name="aperformance" onkeyup="value=this.value.replace(/\D+/g,'')" class="border-right-none"></td>
                                </tr>
                                <%-- <tr class="pm">
                                    <td colspan="4" class="pm">
                                        <div class="plus" id="a_plus"><img src="<%=basePath %>app/img/head/plus.png" alt=""/></div>
                                        <div class="minus" id="a_minus"><img src="<%=basePath %>app/img/head/minus.png" alt=""/></div>

                                    </td>
                                </tr> --%>

                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
          </div>
        <div class="hide">
        </div>
        <div class="hide">
                                       商品消费
        </div>
    </div>
</div><!--demo2 end-->

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

<div class="pay">
    <div class="pay-left">
        <div class="info">
            <img src="<%=headBasePath %>app/img/touxiang.jpg" alt=""/>
            <span>张贱军</span>
        </div>
        <div class="pay-line1">
            <div class="btn-group dropup" supdiv = "supdiv" style="margin-bottom: 10px;">
               <button type="button" class="btn btn-dangers" data-toggle="dropdown" id = "customerType" supname = "supname" style="width:135px;">学生</button>
               <button type="button" class="btn btn-dangers dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
               <button type="button" class="btn btn-dangers dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
               <button type="button" class="btn btn-dangers dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
                    <img src="<%=headBasePath %>app/img/jisuan.png" alt=""/>
                </div>
                <div class="input-border huiyuan">
                    <label for="danhao huiyuan">签单 &nbsp;&nbsp;|</label>
                    <input type="text" name="qdBill" onkeyup="value=this.value.replace(/\D+/g,'')"/>
                    <input type="hidden" name = "qdEmployeeId">
                    <img src="<%=headBasePath %>app/img/qiandan.png" alt=""/>
                </div>
        </div>
            <div class="pay-line2 tag-huaka">
                <div class="input-border2 btn-group dropup" style="margin-bottom: 10px;" supdiv = "supdiv">
                    <a data-toggle="dropdown" class="dropdown-toggle"  href="javascript://" supname = "supname" style="display: inline-block; float: left;text-decoration:none; color: #757575;font-size: 12px;font-weight: 700;">划卡</a>
                        <label for="danhao " class="xianjin">&nbsp;&nbsp;|</label>
                    <ul class="dropdown-menu" aria-labelledby="cardBillDropdown">
                         <li><a href="javascript://" supa = "supa">划卡</a></li>
                         <li class="divider"></li>
                         <li><a href="javascript://" supa = "supa">礼金</a></li>
                    </ul>
                    <input type="text" name="cardBill" onkeyup="value=this.value.replace(/\D+/g,'')"/>
                    <img src="<%=headBasePath %>app/img/xianjin.png" alt=""/>
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
                    <img src="<%=headBasePath %>app/img/yinlian.png" alt=""/>
                </div>
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

<!-- 散客收银银出框 -->
<div class="modal fade" id="jiesuan" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">账单号</h4>
                <span class="fl">共81360.00</span>
            </div>
            <div class="modal-body">
                <table class="table hair">
                    <thead>
                    <tr>
                        <th class="title project" style="background-color: #e6784d;">
                            <div class="minus" style="background-color: #e6784d;"><img src="<%=headBasePath %>app/img/head/minus.png" alt=""/></div>
                                                                                                 美发服务项目
                            <div class="plus" style="background-color: #e6784d;"><img src="<%=headBasePath %>app/img/head/plus.png" alt=""/></div>
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
                                    <td calss="name">项目名称</td>
                                    <td class="money">价格</td>
                                    <td class="discount" >优惠</td>
                                    <td class="sum">实收金额</td>
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
                                    <td calss="name"><input type="text" ></td>
                                    <td class="money"><input type="text" ></td>
                                    <td class="discount" ><input type="text" ></td>
                                    <td class="sum"><input type="text" ></td>
                                    <td class="code"><input type="text" ></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <table class="table hair">
                    <thead>
                    <tr>
                        <th class="title project project-title" style="background-color: #c91f7f;">
                            <div class="minus" style="background-color: #c91f7f;"><img src="<%=headBasePath %>app/img/head/minus.png" alt=""/></div>
                                                                                               美发服务项目
                            <div class="plus" style="background-color: #c91f7f;"><img src="<%=headBasePath %>app/img/head/plus.png" alt=""/></div>
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
                                    <td calss="name">项目名称</td>
                                    <td class="money">价格</td>
                                    <td class="discount" >优惠</td>
                                    <td class="sum">实收金额</td>
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
                                    <td calss="name"><input type="text" ></td>
                                    <td class="money"><input type="text" ></td>
                                    <td class="discount" ><input type="text" ></td>
                                    <td class="sum"><input type="text" ></td>
                                    <td class="code"><input type="text" ></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>

                <table class="table hair">
                    <thead>
                    <tr>
                        <th class="title project project-title" style="background-color: #3083af;">
                            <div class="minus" style="background-color: #3083af;"><img src="<%=headBasePath %>app/img/head/minus.png" alt=""/></div>
                                                                                           美发服务项目
                            <div class="plus" style="background-color: #3083af;"><img src="<%=headBasePath %>app/img/head/plus.png" alt=""/></div>
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
                                    <td calss="name">项目名称</td>
                                    <td class="money">价格</td>
                                    <td class="discount" >优惠</td>
                                    <td class="sum">实收金额</td>
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
                                    <td calss="name"><input type="text" ></td>
                                    <td class="money"><input type="text" ></td>
                                    <td class="discount" ><input type="text" ></td>
                                    <td class="sum"><input type="text" ></td>
                                    <td class="code"><input type="text" ></td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" class="btn btn-primary">确定</button>
            </div>
        </div>
    </div>
</div>
<%@ include file="/js.jsp"%>
<script type="text/javascript">

$(".appoint").on("click", function(){
    var th = $(this);
    if(th.hasClass("open")){
        th.removeClass("open");
    }else{
        th.addClass("open");
        
    }

});

  var proobj = "";
  var empobj = new Object();;
  var proNoClick = "";
  $(function(){
	  
 	 $('.demo2').Tabs({
          event:'click'
      });
     $('.demo2').Tabs({
      event:'click'
     });
    $.ajax({
		url : "selectAutoProject",
		type: "post",
		async : false,
		dataType : "json",
		success : function(data){
			/* console.log("data : " + JSON.stringify(data)); */
			if(data.code == 0){
				proobj = data.msg;
				UpWindow(proobj);
			}
		},
     });
    
     //添加键盘按下事件
     document.onkeydown = function(event) {
    		var e = event || window.event || arguments.callee.caller.arguments[0];
    		//按下键盘后焦点位置
    		var focusElement = document.activeElement;
    		//焦点位置
    		var index = getElementIndex(focusElement);
    		
    		supkeydown(e,focusElement);
    		
    		if (e && e.keyCode == 27) { // 按 Esc
    			
    		}
    		if (e && e.keyCode == 113) { // 按 F2
    			
    		}
    		if(e && e.keyCode == 120){ // 按F9
    			copyProject();
    		}
    		if(e && e.keyCode == 32){ // 按  space

    			var arr=new Array();
    			arr=$(".num").text().split("￥");
    			if(Number(arr[1]) == Number($("input[name='qdBill']").val()) + Number($("input[name='cashBill']").val()) +Number($("input[name='cardBill']").val())){
    				accounts();
    			}else{
    				dialog("结账金额不匹配");
    				$("input[name='cashBill']").focus();
    			}
    			
    		}
    		if (e && e.keyCode == 13) {// enter 键
    			console.log(focusElement);
    		    if("supdiv" == $(focusElement).parent().attr("supdiv")){
    		    	if("customerType" == $(focusElement).parent().find("button[supname='supname']").attr("id")){
    	                $("#customerSource").focus();
     			    }
        			
        			if("customerSource" == $(focusElement).parent().find("button[supname='supname']").attr("id")){
    	                $("#customerNum").focus();
     			    }
        			
        			if("customerNum" == $(focusElement).parent().find("button[supname='supname']").attr("id")){
        				$("#customerNum").click();
    	                $("input[name='orderNo']").focus();
     			    }
    		    }
    			if("cashBillLI" == $(focusElement).attr("id")){
    				console.log("eeeerrr");
    				$("#cashBillDropdown").empty();
    				$("#cashBillDropdown").append($(focusElement).text()+"&nbsp;&nbsp;|<span class='caret'></span>")
    			}
    			if("cardBillLI" == $(focusElement).attr("id")){
    				console.log("eeeerrr");
    				$("#cardBillDropdown").empty();
    				$("#cardBillDropdown").append($(focusElement).text()+"&nbsp;&nbsp;|<span class='caret'></span>")
    			}
    			if("prono" == focusElement.name){
    				var type = 0;
    				$.each(proobj,function(i,item){
    			    	if(item != null){
    			    		$.each(item.lproject,function(j,proitem){
    			    			if(proitem != null){
    			    				if(proitem.projectNo == focusElement.value){
    			    					$(focusElement).parent().parent().find("input[name='proid']").val(proitem.projectInfoId);
    			        				$(focusElement).parent().parent().find("input[name='proname']").val(proitem.projectName);
    			        				$(focusElement).parent().parent().find("input[name='proprice']").val(proitem.projectPrice);
    			        				$(focusElement).parent().parent().find("input[name='promoney']").val(proitem.projectPrice);
    			        				type = 1;
    			        				//查找项目对应的员工
    			        				selectEmployee(proitem.projectInfoId);
    								  }
    			    			}
    						   });
    			    	}
    				});
    				if(type == 1){
    					countprice();
    					$(".dropdown-input").css({"display":"none"});
    					var targetElement = $(focusElement).parent().parent().find("input[name='procoupon']");
        				targetElement.focus();
    				}else{
    					dialog("填数据");
    					focusElement.focus();
    				}
    			}
    			if("procoupon" == focusElement.name){
    				var proprice = Number($(focusElement).parent().parent().find("input[name='proprice']").val()) - Number($(focusElement).val());
    				var targetElement = $(focusElement).parent().parent().find("input[name='promoney']");
    				countprice();
    				targetElement.val(proprice);
    				targetElement.focus();
    			}
    			if("promoney" == focusElement.name){
    				var targetElement = getElementIndexValue(focusElement,"semployeeno",0);
    				
    				targetElement.click();
    				targetElement.focus();
					var eamoney = $(document).find("input[name='promoney']");
				    var num = 0;
					for (var i = 0; i < eamoney.length; i++) {
						num = Number(num) + Number($(eamoney[i]).val());
					}
					$(".num").text("￥"+parseInt(num));
    			}
    			
    			if("semployeeno" == focusElement.name){
    				var empvalues = empobj[$(focusElement).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
    				var type = 0;
    				if(focusElement.value != ""){
    					$.each(empvalues,function(i,item){
    	          			   if(item != null){
    	          				  var pvalue = item.employeeNo;
    	          				  if(pvalue == focusElement.value ){
    	          					  $(focusElement).parent().find("input[name = 'semployeeid']").val(item.employeeId);
    	          					  type = 1;
    	     					  }
    	          			   }
    	     			   });
    				}else{
    					$(focusElement).parent().find("input[name = 'semployeeid']").val("");
    					type = 1;
    				}
	       			if(type == 1){
	   					//将semployeeno中下拉属性去掉open
	       				$(focusElement).parent().attr("class","agent-id dropdown");
	       				var targetElement = getElementWhereSelect(focusElement,"sappoint",getElementIndex(focusElement,"semployeeno"));
	       				targetElement.focus();
	   				}else{
	   					  dialog("请填写合法数据");
	   					  focusElement.focus();
	   				}
    			}
    			
    			if("sappoint" == focusElement.name){
    				var targetElement = getElementWhere(focusElement,"sperformance",getElementIndexSelect(focusElement,"sappoint"));
    				targetElement.focus();
    			}
    			
    			if("sperformance" == focusElement.name){
    				var targetElement = getElementIndexValue(focusElement,"memployeeno",0);
    				
                    targetElement.click();
    				targetElement.focus();
    			}
    			
    			if("memployeeno" == focusElement.name){
    				var empvalues = empobj[$(focusElement).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
    				var type = 0;
    				if(focusElement.value != ""){
    					$.each(empvalues,function(i,item){
    	          			   if(item != null){
    	          				  var pvalue = item.employeeNo;
    	          				  if(pvalue == focusElement.value){
    	          					  $(focusElement).parent().find("input[name = 'memployeeid']").val(item.employeeId);
    	          					  type = 1;
    	     					  }
    	          			   }
    	     			   });
    				}else{
    					$(focusElement).parent().find("input[name = 'memployeeid']").val("");
    					type = 1;
    				}
	       		   if(type == 1){
	   					//将semployeeno中下拉属性去掉open
	       				$(focusElement).parent().attr("class","agent-id dropdown");
	       				var targetElement = getElementWhereSelect(focusElement,"mappoint",getElementIndex(focusElement,"memployeeno"));
	       				targetElement.focus();
	   			   }else{
	   					  dialog("请填写合法数据");
	   					  focusElement.focus();
	   			   }
    			}
    			
    			if("mappoint" == focusElement.name){
    				var targetElement = getElementWhere(focusElement,"mperformance",getElementIndexSelect(focusElement,"mappoint"));
    				targetElement.focus();
    			}
    			
    			if("mperformance" == focusElement.name){
    				var targetElement = getElementIndexValue(focusElement,"aemployeeno",0);
    				
                    targetElement.click();
				    targetElement.focus();
    			}
    			
    			if("aemployeeno" == focusElement.name){
    				var empvalues = empobj[$(focusElement).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
    				var type = 0;
    				if(focusElement.value != ""){
    					$.each(empvalues,function(i,item){
    	          			   if(item != null){
    	          				  var pvalue = item.employeeNo;
    	          				  if(pvalue == focusElement.value){
    	          					  $(focusElement).parent().find("input[name = 'aemployeeid']").val(item.employeeId);
    	          					  type = 1;
    	     					  }
    	          			   }
    	     			});
    				}else{
    					$(focusElement).parent().find("input[name = 'aemployeeid']").val("");
    					type = 1;
    				}
          			
	       		   if(type == 1){
	   					//将semployeeno中下拉属性去掉open
	       				$(focusElement).parent().attr("class","agent-id dropdown");
	       				var targetElement = getElementWhereSelect(focusElement,"aappoint",getElementIndex(focusElement,"aemployeeno"));
	       				targetElement.focus();
	   			   }else{
	   					  dialog("请填写合法数据");
	   					  focusElement.focus();
	   			   }
    			}
    			
    			if("aappoint" == focusElement.name){
    				var targetElement = getElementWhere(focusElement,"aperformance",getElementIndexSelect(focusElement,"aappoint"));
    				targetElement.focus();
    			}
    			
    			if("aperformance" == focusElement.name){
    				console.log($(focusElement).parents(".pro_tr").next().find("input[name='prono']").attr("name"));
    				if($(focusElement).parents(".pro_tr").next().find("input[name='prono']").attr("name") == null){
    					$("#customerType").focus();
                    }else{
                    	$(focusElement).parents(".pro_tr").next().find("input[name='prono']").focus();
                    	$(focusElement).parents(".pro_tr").next().find("input[name='prono']").click();
                    }
    			}
    			
    			if("orderNo" == focusElement.name){
	                $("input[name='qdBill']").focus();
 			    }
    			
    			if("qdBill" == focusElement.name){
    				if(focusElement.value != ""){
    					modalcode();
    					$('#modelID').on('shown.bs.modal',function(e){
    						$("input[name='qd_code1']").focus(); //通过ID找到对应输入框，让其获得焦点
    					});
    				}else{
    					$("input[name='cashBill']").focus();
    				}
 			    }
    			
    			if("cashBill" == focusElement.name){
	                $("input[name='cardBill']").focus();
 			    }
    			
    		}
       };
       //添加键盘弹起事件
       document.onkeyup = function(event) {
      		var e = event || window.event || arguments.callee.caller.arguments[0];
      		//弹起键盘后焦点位置
      		var focusElement = document.activeElement;
      		//焦点位置
      		var index = getElementIndex(focusElement);
      		
      		if("qd_code1" == focusElement.name){
      			if(focusElement.value != ""){
      				$("input[name='qd_code2']").focus();
      			}
		    }
    		
    		if("qd_code2" == focusElement.name){
    			if(focusElement.value != ""){
      				$("input[name='qd_code3']").focus();
      			}
		    }
    		
    		if("qd_code3" == focusElement.name){
    			if(focusElement.value != ""){
      				$("input[name='qd_code4']").focus();
      			}
		    }
    		
    		if("qd_code4" == focusElement.name){
    			if(focusElement.value != ""){
      				$("input[name='qd_code5']").focus();
      			}
		    }
    		
    		if("qd_code5" == focusElement.name){
    			if(focusElement.value != ""){
      				$("input[name='qd_code6']").focus();
      			}
		    }
    		
    		if("qd_code6" == focusElement.name){
    			var qd_code5 = $(focusElement).parent().parent().find("input[name=qd_code5]").val();
    			var qd_code4 = $(focusElement).parent().parent().find("input[name=qd_code4]").val();
    			var qd_code3 = $(focusElement).parent().parent().find("input[name=qd_code3]").val();
    			var qd_code2 = $(focusElement).parent().parent().find("input[name=qd_code2]").val();
    			var qd_code1 = $(focusElement).parent().parent().find("input[name=qd_code1]").val();
    			var qd_code6 = focusElement.value;
    			var qd_code = String(qd_code1) + String(qd_code2) + String(qd_code3) + String(qd_code4) + String(qd_code5) + String(qd_code6);
    			$.ajax({
					url : "checkoutQDcode",
					data : "qd_code="+qd_code,
					type: "post",
					async : false,
					dataType : "json",
					success : function(data){
						if(data.msg == 'no'){
							dialog("授权码错误");
							focusElement.focus();
						}else{
							$('#modelID').modal('hide');
							$("input[name='cashBill']").focus();
							$("input[name='qdEmployeeId']").val(data.msg);//通过ID找到对应输入框，让其获得焦点
						}
					},
			    });
		    }
      		
            if (e && e.keyCode == 8) { // 按 Backspace
            	if("qd_code6" == focusElement.name){
          			$("input[name='qd_code5']").focus();
    		    }
            	if("qd_code5" == focusElement.name){
          			$("input[name='qd_code4']").focus();
    		    }
            	if("qd_code4" == focusElement.name){
          			$("input[name='qd_code3']").focus();
    		    }
            	if("qd_code3" == focusElement.name){
          			$("input[name='qd_code2']").focus();
    		    }
            	if("qd_code2" == focusElement.name){
          			$("input[name='qd_code1']").focus();
    		    }
    		}
    		
      		if("prono" == focusElement.name){
      			prodivno(focusElement.value);
      		}
      		
      		if("proname" == focusElement.name){
      			prodivname(focusElement.value);
      		}
      		
      		if("semployeeno" == focusElement.name){
      			var empvalues = empobj[$(focusElement).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
      			$.each(empvalues,function(i,item){
      			   if(item != null){
      				  var pvalue = item.employeeNo.toString();
      				  if(pvalue.indexOf(focusElement.value) >= 0){
      					  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").css('display','inline-block');
      					  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").next().css('display','inline-block');
 					  }else{
 						  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").css('display','none');
 						  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").next().css('display','none');
 					  }
      			   }
 			   });
      		}
      		
      		if("memployeeno" == focusElement.name){
      			var empvalues = empobj[$(focusElement).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
      			$.each(empvalues,function(i,item){
      			   if(item != null){
      				  var pvalue = item.employeeNo.toString();
      				  if(pvalue.indexOf(focusElement.value) >= 0){
      					  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").css('display','inline-block');
      					  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").next().css('display','inline-block');
 					  }else{
 						  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").css('display','none');
 						  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").next().css('display','none');
 					  }
      			   }
 			   });
      		}
      		
      		if("aemployeeno" == focusElement.name){
      			var empvalues = empobj[$(focusElement).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
      			$.each(empvalues,function(i,item){
      			   if(item != null){
      				  var pvalue = item.employeeNo.toString();
      				  if(pvalue.indexOf(focusElement.value) >= 0){
      					  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").css('display','inline-block');
      					  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").next().css('display','inline-block');
 					  }else{
 						  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").css('display','none');
 						  $(focusElement).parent().parent().find("li[employeeIds='"+item.employeeId+"']").next().css('display','none');
 					  }
      			   }
 			   });
      		}
      	};
    	
    });
  
  //向modle删除添加点击事件
  $(".close").click(function(){
	  $('#modelID').modal('hide');
	  $("input[name='qdBill']").focus(); //通过ID找到对应输入框，让其获得焦点

  });
  
  //向签单添加点击事件
  $("input[name='qdBill']").blur(function(e){
	  if($("input[name='qdBill']").val() != ""){
		    modalcode();
			$('#modelID').on('shown.bs.modal',function(e){
				$("input[name='qd_code1']").focus(); //通过ID找到对应输入框，让其获得焦点
			});
       }
  });
  
  //弹出授权码输入框
  function modalcode(){
	    $('#modelID').modal({backdrop: 'static', keyboard: false});
		$('#modelID').modal('show');
		$("input[name='qd_code1']").val("");
		$("input[name='qd_code2']").val("");
		$("input[name='qd_code3']").val("");
		$("input[name='qd_code4']").val("");
		$("input[name='qd_code5']").val("");
		$("input[name='qd_code6']").val("");
  }
  
  function dialog(msg){
	  var $wrap = $('<div class="alertWrap"></div>');
	  var $content = $('<div class="alert"></div>');
	  $content.html(msg);
	  $wrap.append($content);
	  $("body").append($wrap);

	  var windowHeight = $(window).height();
	  var pt = (windowHeight - 100)/2;

	  $wrap.css("padding-top",pt).fadeIn(800);

	  $wrap.click(function(){
	    $wrap.fadeOut(800).remove();
	  })

	  setTimeout(function(){
	    $wrap.fadeOut(800).remove();
	    },1000);
	}
  
  
    function selectEmployee(projectInfoId){
    	$.ajax({
			url : "selectAutoEmployee",
			data : "projectInfoId="+projectInfoId,
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){
				if(data.code == 0){
					var uiTL = $("<ul class='dropdown-menu'></ul>");
					$.each(data.msg,function(i,item){
						var liTL = $("<li name ='empname' employeeIds = '"+item.employeeId+"' style='display : inline-block'>"+item.employeeNo+"_"+item.employeeName+"</li>");
						uiTL.append(liTL);
						uiTL.append("<li class='divider' style='display : inline-block'></li>");
					});
					empobj[projectInfoId] = $(uiTL).prop("outerHTML");
					empobj[projectInfoId+"_name"] = data.msg;
				}
			},
	    });
    }
  
    //[input]计算相同tr中位置位置
    function getElementIndex(e,value){
    	 var ea = $(e).parent().parent().parent().parent().parent().find("input[name="+value+"]");
	  	 for (var i = 0; i < ea.length; i++) {
		   if(ea[i] == e){
			  return i;
		   }
	     }
	  	 return 0;
	 }
    
    //[select]计算相同tr中位置位置
    function getElementIndexSelect(e,value){
    	 var ea = $(e).parent().parent().parent().parent().parent().find("select[name="+value+"]");
	  	 for (var i = 0; i < ea.length; i++) {
		   if(ea[i] == e){
			  return i;
		   }
	     }
	  	 return 0;
	 }
    
     //[input]从tr跳到另外tr时
	 function getElementIndexValue(e,value,i){
	  	 return $(e).parent().parent().parent().parent().parent().next().find("input[name="+value+"]")[i];
	 }
     
	//[select]从tr跳到另外tr时
	 function getElementIndexValueSelect(e,value,i){
	  	 return $(e).parent().parent().parent().parent().parent().next().find("select[name="+value+"]")[i];
	 }
     
	//[input]相同tr中跳
	 function getElementWhere(e,value,i){
    	 
	  	 return $(e).parent().parent().parent().parent().parent().find("input[name="+value+"]")[i];
	 }
	
	//[select]相同tr中跳
	 function getElementWhereSelect(e,value,i){
    	 
	  	 return $(e).parent().parent().parent().parent().parent().find("select[name="+value+"]")[i];
	 }
     
	
	//向实收金额添加click事件
	$(".tab_box").delegate("input[name='promoney']",'click', function(e){
		var eamoney = $(document).find("input[name='promoney']");
	    var num = 0;
		for (var i = 0; i < eamoney.length; i++) {
			num = Number(num) + Number($(eamoney[i]).val());
		}
		$(".num").text("￥"+parseInt(num));
	});
	
	//向优惠添加click事件
	$(".tab_box").delegate("input[name='procoupon']",'blur', function(e){
		e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target;
		var proprice = Number($(obj).parent().parent().find("input[name='proprice']").val()) - Number($(obj).val());
		var targetElement = $(obj).parent().parent().find("input[name='promoney']");
		targetElement.val(proprice);
		countprice();
	});
	 
	//向发型设计师员工编号添加click事件
	$(".tab_box").delegate("input[name='semployeeno']",'click', function(e){
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		if($(obj).parent().parent().find(".dropdown-menu").html() == null){
			$(obj).parent().append(empobj[$(obj).parents(".pro_tr").find("input[name='proid']").val()]);
		}
		$(obj).parent().attr("class", "agent-id  dropdown open");
	});
	
	//向美发技师员工编号添加click事件
	$(".tab_box").delegate("input[name='memployeeno']",'click', function(e){
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		if($(obj).parent().parent().find(".dropdown-menu").html() == null){
			$(obj).parent().append(empobj[$(obj).parents(".pro_tr").find("input[name='proid']").val()]);
		}
		$(obj).parent().attr("class", "agent-id dropdown open");
	});
	
	//向美发助理员工编号添加click事件
	$(".tab_box").delegate("input[name='aemployeeno']",'click', function(e){
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		if($(obj).parent().parent().find(".dropdown-menu").html() == null){
			$(obj).parent().append(empobj[$(obj).parents(".pro_tr").find("input[name='proid']").val()]);
		}
		$(obj).parent().attr("class", "agent-id dropdown open");
	});
	
	//向发型设计师员弹出框添加click事件
	$(".tab_box").delegate("li[name='empname']",'click', function(e){
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		var arr=new Array();
		arr=$(obj).text().split("_");
		$(obj).parent().parent().find("input[name='semployeeid']").val($(obj).attr("employeeIds"));
		$(obj).parent().parent().find("input[name='semployeeno']").val(arr[0]);
		$(obj).parent().parent().attr("class", "agent-id dropdown");
	});
	
	//向美发技师员弹出框添加click事件
	$(".tab_box").delegate("li[name='empname']",'click', function(e){
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		var arr=new Array();
		arr=$(obj).text().split("_");
		$(obj).parent().parent().find("input[name='memployeeid']").val($(obj).attr("employeeIds"));
		$(obj).parent().parent().find("input[name='memployeeno']").val(arr[0]);
		$(obj).parent().parent().attr("class", "agent-id dropdown");
	});
	
	//向美发助理员弹出框添加click事件
	$(".tab_box").delegate("li[name='empname']",'click', function(e){
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		var arr=new Array();
		arr=$(obj).text().split("_");
		$(obj).parent().parent().find("input[name='aemployeeid']").val($(obj).attr("employeeIds"));
		$(obj).parent().parent().find("input[name='aemployeeno']").val(arr[0]);
		$(obj).parent().parent().attr("class", "agent-id dropdown");
	});
	
	//向发型设计师员工编号添加失去焦点事件
	$(".tab_box").delegate("input[name='semployeeno']",'blur', function(e){
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target;
		var empvalues = empobj[$(obj).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
		var type = 0;
		if($(obj).val() != ''){
			$.each(empvalues,function(i,item){
				   if(item != null){
					  var pvalue = item.employeeNo;
					  if(pvalue == obj.value){
						  $(obj).parent().find("input[name = 'semployeeid']").val(item.employeeId);
						  type = 1;
					  }
				   }
			 });
		}else{
			$(obj).parent().find("input[name = 'semployeeid']").val("");
			type = 1;
		}
		if(type == 1){
			//将semployeeno中下拉属性去掉open
			$(obj).parent().attr("class", "agent-id dropdown");
			var targetElement = getElementWhereSelect(obj,"sappoint",getElementIndex(obj,"semployeeno"));
			targetElement.focus();
		}else{
			dialog("请填写合法数据");
			obj.focus();
		}
	});
	
	//向美发技师员工编号添加失去焦点事件
	$(".tab_box").delegate("input[name='memployeeno']",'blur', function(e){
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target;
		var empvalues = empobj[$(obj).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
		var type = 0;
		if($(obj).val() != ''){
			$.each(empvalues,function(i,item){
				   if(item != null){
					  var pvalue = item.employeeNo;
					  if(pvalue == obj.value){
						  $(obj).parent().find("input[name = 'memployeeid']").val(item.employeeId);
						  type = 1;
					  }
				   }
			 });
		}else{
			$(obj).parent().find("input[name = 'semployeeid']").val("");
			type = 1;
		}
		if(type == 1){
			//将memployeeno中下拉属性去掉open
			$(obj).parent().attr("class", "agent-id dropdown");
			var targetElement = getElementWhereSelect(obj,"mappoint",getElementIndex(obj,"memployeeno"));
			targetElement.focus();
		}else{
			dialog("请填写合法数据");
			obj.focus();
		}
	});
	
	//向美发助理员工编号添加失去焦点事件
	$(".tab_box").delegate("input[name='aemployeeno']",'blur', function(e){
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		var empvalues = empobj[$(obj).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
		var type = 0;
		if($(obj).val() != ''){
			$.each(empvalues,function(i,item){
				   if(item != null){
					  var pvalue = item.employeeNo;
					  if(pvalue == obj.value){
						  $(obj).parent().find("input[name = 'aemployeeid']").val(item.employeeId);
						  type = 1;
					  }
				   }
			 });
		}else{
			$(obj).parent().find("input[name = 'semployeeid']").val("");
			type = 1;
		}
		if(type == 1){
			//将memployeeno中下拉属性去掉open
			$(obj).parent().attr("class", "agent-id dropdown");
			var targetElement = getElementWhereSelect(obj,"aappoint",getElementIndex(obj,"aemployeeno"));
			targetElement.focus();
		}else{
			dialog("请填写合法数据");
			obj.focus();
		}
	});
	
	//解除向发型设计师员工编号的blur事件
	$(".tab_box").delegate(".dropdown-menu","mouseover",function(e){
		$(".tab_box").undelegate("input[name='semployeeno']","blur");
	});
	
	//解除美发技师员工编号的blur事件
	$(".tab_box").delegate(".dropdown-menu","mouseover",function(e){
		$(".tab_box").undelegate("input[name='memployeeno']","blur");
	});
	
	//解除员工编号的blur事件
	$(".tab_box").delegate(".dropdown-menu","mouseover",function(e){
		$(".tab_box").undelegate("input[name='aemployeeno']","blur");
	});
	
    //添加向发型设计师员工编号的blur
	$(".tab_box").delegate(".dropdown-menu","mouseout",function(){
		//向发型设计师员工编号添加失去焦点事件
		$(".tab_box").delegate("input[name='semployeeno']",'blur', function(e){
		    e = e ? e : window.e; 
			var obj = e.srcElement ? e.srcElement : e.target;
			var empvalues = empobj[$(obj).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
			var type = 0;
			if(obj.value != ""){
				$.each(empvalues,function(i,item){
					   if(item != null){
						  var pvalue = item.employeeNo;
						  if(pvalue == obj.value){
							  $(obj).parent().find("input[name = 'semployeeid']").val(item.employeeId);
							  type = 1;
						  }
					   }
				 });
			}else{
				$(obj).parent().find("input[name = 'semployeeid']").val("");
				type = 1;
			}
			if(type == 1){
				//将semployeeno中下拉属性去掉open
				$(obj).parent().attr("class", "agent-id dropdown");
				var targetElement = getElementWhereSelect(obj,"sappoint",getElementIndex(obj,"semployeeno"));
				targetElement.focus();
			}else{
				dialog("请填写合法数据");
				obj.focus();
			}
		});
	});
    
	//添加向美发技师员工编号的blur
	$(".tab_box").delegate(".dropdown-menu","mouseout",function(){
		//向美发技师员工编号添加失去焦点事件
		$(".tab_box").delegate("input[name='memployeeno']",'blur', function(e){
		    e = e ? e : window.e; 
			var obj = e.srcElement ? e.srcElement : e.target;
			var empvalues = empobj[$(obj).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
			var type = 0;
			if(obj.value != ""){
				$.each(empvalues,function(i,item){
					   if(item != null){
						  var pvalue = item.employeeNo;
						  if(pvalue == obj.value){
							  $(obj).parent().find("input[name = 'memployeeid']").val(item.employeeId);
							  type = 1;
						  }
					   }
				 });
			}else{
				$(obj).parent().find("input[name = 'memployeeid']").val("");
				type = 1;
			}
			if(type == 1){
				//将memployeeno中下拉属性去掉open
				$(obj).parent().attr("class", "agent-id dropdown");
				var targetElement = getElementWhereSelect(obj,"mappoint",getElementIndex(obj,"memployeeno"));
				targetElement.focus();
			}else{
				dialog("请填写合法数据");
				obj.focus();
			}
		});
	});
	
	//添加向美发助理员工编号的blur
	$(".tab_box").delegate(".dropdown-menu","mouseout",function(){
		//向美发助理员工编号添加失去焦点事件
		$(".tab_box").delegate("input[name='aemployeeno']",'blur', function(e){
		    e = e ? e : window.e; 
			var obj = e.srcElement ? e.srcElement : e.target; 
			var empvalues = empobj[$(obj).parents(".pro_tr").find("input[name='proid']").val()+"_name"];
			var type = 0;
			if(obj.value != ""){
				$.each(empvalues,function(i,item){
					   if(item != null){
						  var pvalue = item.employeeNo;
						  if(pvalue == obj.value){
							  $(obj).parent().find("input[name = 'aemployeeid']").val(item.employeeId);
							  type = 1;
						  }
					   }
				 });
			}else{
				$(obj).parent().find("input[name = 'aemployeeid']").val("");
				type = 1;
			}
			if(type == 1){
				//将memployeeno中下拉属性去掉open
				$(obj).parent().attr("class", "agent-id dropdown");
				var targetElement = getElementWhereSelect(obj,"aappoint",getElementIndex(obj,"aemployeeno"));
				targetElement.focus();
			}else{
				dialog("请填写合法数据");
				obj.focus();
			}
		});
	});
	
    //向项目编号添加click事件
	$(".tab_box").delegate("input[name='prono']",'click', function(e){
	    var xx = e.originalEvent.x || e.originalEvent.layerX || 0;
	    var yy = e.originalEvent.y || e.originalEvent.layerY || 0;
	    var y = yy;
	    var x = xx - 50;
	    $(".dropdown-input").css({"display":"block", "left":"10%", "top":y});
	    $(".arrow").css({"left":x});
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		proNoClick = obj;
		if($(obj).val() ==''){
			$.each(proobj,function(i,item){
				   if(item != null){
					 $.each(item.lproject,function(j,proitem){
						 if(proitem != null){
						     $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','inline-block');
							 $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','inline-block');
						 }
					   });
				   }
			 });
		}else{
			prodivno($(obj).val());
		}
	});
    
	//向项目名称添加click事件
	$(".tab_box").delegate("input[name='proname']",'click', function(e){
	    var xx = e.originalEvent.x || e.originalEvent.layerX || 0;
	    var yy = e.originalEvent.y || e.originalEvent.layerY || 0;
	    var y = yy + 25;
	    var x = xx - 150;
	    $(".dropdown-input").css({"display":"block", "left":"10%", "top":y});
	    $(".arrow").css({"left":x});
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		proNoClick = obj;
		if($(obj).val() ==''){
			$.each(proobj,function(i,item){
				   if(item != null){
					 $.each(item.lproject,function(j,proitem){
						 if(proitem != null){
						     $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','inline-block');
							 $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','inline-block');
						 }
					   });
				   }
			 });
		}else{
			prodivname($(obj).val());
		}
	});
	
	//项目编号弹出框中值是否显示
	function  prodivno(value){
		$.each(proobj,function(i,item){
			   if(item != null){
				 $.each(item.lproject,function(j,proitem){
					 if(proitem != null){
						//循环是否匹配
 					  var pvalue = proitem.projectNo.toString();
 					  if(pvalue.indexOf(value) >= 0){
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','inline-block');
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','inline-block');
 					  }else{
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','none');
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','none');
 					  }
					 }
				   });
			   }
	     });
	}
	
	//项目名称弹出框中值是否显示
	function  prodivname(value){
		$.each(proobj,function(i,item){
			   if(item != null){
				 $.each(item.lproject,function(j,proitem){
					 if(proitem != null){
						//循环是否匹配
 					  var pvalue = proitem.projectName.toString();
 					  if(pvalue.indexOf(value) >= 0){
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','inline-block');
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','inline-block');
 					  }else{
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','none');
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','none');
 					  }
					 }
				   });
			   }
	     });
	}
	
    //解除项目编号的blur事件
	$(".dropdown-input").mouseover(function(){
		$(".tab_box").undelegate("input[name='prono']","blur");
	});
	
    //添加项目编号的blur
	$(".dropdown-input").mouseout(function(){
		$(".tab_box").delegate("input[name='prono']",'blur', function(event){
		    //检查输入内容，如果内容合法，隐藏弹出层，否则弹出提示，让其选择项目
		    event = event ? event : window.event; 
		    var obj = event.srcElement ? event.srcElement : event.target; 
		    opinion(obj);
		});
	});
	
   //向弹出框项目编号、项目名称绑定click事件
   $(".dropdown-input").delegate("#getitem","click",function(event){
	   getProjectItemValue(event);
	});
   
   //向弹出框项目价格绑定click事件
   $(".dropdown-input").delegate("#getmoney","click",function(event){
	   getProjectMoneyValue(event);
	});
   
   $(".tab_box").delegate("input[name='prono']",'blur', function(event){
	    //检查输入内容，如果内容合法，隐藏弹出层，否则弹出提示，让其选择项目
	    event = event ? event : window.event; 
	    var obj = event.srcElement ? event.srcElement : event.target; 
	    opinion(obj);
	});
   
   //添加项目事件
   $("#project_plus").on("click",function(){
 	    copyProject();
   });
   
   /* //添加发型设计师事件
   $(".tab_box").delegate("#s_plus","click",function(){
  	   copyStye($(this));
   });
   
   //添加美发技师事件
   $(".tab_box").delegate("#m_plus","click",function(){
	   copyMes($(this));
   });
   
   //添加美发助理事件
   $(".tab_box").delegate("#a_plus","click",function(){
	   copyAid($(this));
   }); */
   
   //移除项目事件
   $(".tab_box").delegate("#p_minus","click",function(){
       $(this).parent().parent().parent().parent().parent().parent().remove();
   });
   
   jQuery(window).load(function () {
       $('.piluku-preloader').addClass('hidden');
   });
   
   //结账
   function accounts(){
	   //取出所有项目信息
	   var eapriid = $("input[name='proid']");
	   var eaprono = $("input[name='prono']");
	   var eaproname = $("input[name='proname']");
	   var eaproprice = $("input[name='proprice']");
	   var eaprocoupon = $("input[name='procoupon']");
	   var eapromoney = $("input[name='promoney']");
	   var orderNo = $("input[name='orderNo']").val();
	   var orderAmount = $("input[name='orderAmount']").val();
	   var saleAmountList = $("input[name='orderAmount']");
	   var saleAmount = 0;
	   for (var i = 0; i < saleAmountList.length; i++) {
		   saleAmount = Number(saleAmount) + Number($(saleAmountList[i]).val());
	   }
	   
	   var qdBill = $("input[name='qdBill']").val();
	   var qdAuthorizationCode = $("input[name='qdAuthorizationCode']").val();
	   var cashBill = $("input[name='cashBill']").val();
	   var cardBill = $("input[name='cardBill']").val();
	   var qdEmployeeId = $("input[name='qdEmployeeId']").val();
	   var realIncomeAmount = $(".num").text();
	   var arr=new Array();
	   arr=$(".num").text().split("￥");
	   var orderInfoId = "";
	   $.ajax({
			url : "saveOrderInfo",
			data : "orderNo="+orderNo+"&orderAmount="+orderAmount+"&realIncomeAmount="+arr[1]+"&saleAmount="+saleAmount+"&qdBill="+qdBill+"&qdAuthorizationCode="+qdAuthorizationCode+"&cashBill="+cashBill+"&cardBill="+cardBill+"&qdEmployeeId="+qdEmployeeId,
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){
				orderInfoId = data.msg;
			},
	    });
	   var arr = new Array();
	   for(var i = 0; i < eapriid.length; i++){
		  var eaproobj = new Object();
		  if($(eapriid[i]).val() != null && $(eaprono[i]).val() != null){
			  eaproobj["priid"] = $(eapriid[i]).val();
			  eaproobj["prono"] = $(eaprono[i]).val();
			  eaproobj["proname"] = $(eaproname[i]).val();
			  eaproobj["proprice"] = $(eaproprice[i]).val();
			  eaproobj["procoupon"] = $(eaprocoupon[i]).val();
			  eaproobj["promoney"] = $(eapromoney[i]).val();
			  eaproobj["orderInfoId"] = orderInfoId;
			  //单项目下所有员工信息
			  var easemployeeid = $(eapriid[i]).parents(".pro_tr").find("input[name='semployeeid']");
			  var easappoint = $(eapriid[i]).parents(".pro_tr").find("select[name='sappoint']");
			  var easperformance = $(eapriid[i]).parents(".pro_tr").find("input[name='sperformance']");
			  var easallot = $(eapriid[i]).parents(".pro_tr").find("input[name='sallot']");
			  var sarr = new Array();
			  for(var j = 0; j < easemployeeid.length; j++){
				  var sproobj = new Object();
				  sproobj["semployeeid"] = $(easemployeeid[j]).val();
				  sproobj["sappoint"] = $(easappoint[j]).val();
				  sproobj["sperformance"] = $(easperformance[j]).val();
				  sproobj["sallot"] = $(easallot[j]).val();
				  sarr.push(sproobj);
			  }
			  eaproobj["sarr"] = sarr;
			  
			  var eamemployeeid = $(eapriid[i]).parents(".pro_tr").find("input[name='memployeeid']");
			  var eamappoint = $(eapriid[i]).parents(".pro_tr").find("select[name='mappoint']");
			  var eamperformance = $(eapriid[i]).parents(".pro_tr").find("input[name='mperformance']");
			  var eamallot = $(eapriid[i]).parents(".pro_tr").find("input[name='mallot']");
			  var marr = new Array();
			  for(var j = 0; j < eamemployeeid.length; j++){
				  var mproobj = new Object();
				  mproobj["memployeeid"] = $(eamemployeeid[j]).val();
				  mproobj["mappoint"] = $(eamappoint[j]).val();
				  mproobj["mperformance"] = $(eamperformance[j]).val();
				  mproobj["mallot"] = $(eamallot[j]).val();
				  marr.push(mproobj);
			  }
			  eaproobj["marr"] = marr;
			  
			  var eaaemployeeid = $(eapriid[i]).parents(".pro_tr").find("input[name='aemployeeid']");
			  var eaaappoint = $(eapriid[i]).parents(".pro_tr").find("select[name='aappoint']");
			  var eaaperformance = $(eapriid[i]).parents(".pro_tr").find("input[name='aperformance']");
			  var eaaallot = $(eapriid[i]).parents(".pro_tr").find("input[name='aallot']");
			  var aarr = new Array();
			  for(var j = 0; j < eaaemployeeid.length; j++){
				  var aproobj = new Object();
				  aproobj["aemployeeid"] = $(eaaemployeeid[j]).val();
				  aproobj["aappoint"] = $(eaaappoint[j]).val();
				  aproobj["aperformance"] = $(eaaperformance[j]).val();
				  aproobj["aallot"] = $(eaaallot[j]).val();
				  aarr.push(aproobj);
			  }
			  eaproobj["aarr"] = aarr;
			  arr.push(eaproobj);
		  }
	   }
	   $.ajax({
			url : "getProjectEmployeerelation",
			data : "projectMassege="+JSON.stringify(arr),
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){

			},
	    });
   }
   
   function opinion(obj){
	    var type = 0;
	    $.each(proobj,function(i,item){
	    	if(item != null){
	    		$.each(item.lproject,function(j,proitem){
	    			if(proitem != null){
	    				if(proitem.projectNo == $(obj).parent().parent().find("input[name=prono]").val() 
								  && proitem.projectName == $(obj).parent().parent().find("input[name=proname]").val() 
							      && proitem.projectPrice == $(obj).parent().parent().find("input[name=proprice]").val() 
							      && proitem.projectInfoId == $(obj).parent().parent().find("input[name=proid]").val()){
							  $(".dropdown-input").css({"display":"none"});
							  type = 1;
						  }
	    			}
				   });
	    	}
		});
	    if(type == 1){
	    	$(".dropdown-input").css({"display":"none"});
	    	return "Y";
	    }else{
	    	dialog("数据不合法");
	    	obj.focus();
	    	return "N";
	    }
   }
   
   function UpWindow(objwin){
	   var ul = $("<ul></ul>");
	   $.each(objwin,function(i,item){
		   /* var eleLi = document.createElement("li");
		   
		   var eleLabal = document.createElement("li");
		   eleLabal.addAttribute("class","item");
		   
		   var eleSpan = document.createElement("li");
		   
		   eleLi.append(eleLabal);
		   
		   eleLi.append(eleSpan);
		   
		   var ele = document.createElement("li");
		   
		   eleSpan[data] = item;
		   
		   eleArray.add(eleSpan); */
		   if(item != null){
			   var li = $("<li></li>");
			   li.append("<label>"+item.classesName+"</label>");
			   $.each(item.lproject,function(j,proitem){
				   if(proitem != null){
					   li.append("<span class='item' id='getitem' projectInfoId ='"+proitem.projectInfoId+"' style='display: inline-block;'>"+proitem.projectNo+"_"+proitem.projectName+"</span><span class='money' id='getmoney' style='display: inline-block;'>"+proitem.projectPrice+"元</span>");
				   }
			   });
		   }
		   ul.append(li);
	   });
	   $("#upWindow").append(ul);
   }
   
   function search(content){
	   for(var e in eleArray){
		   var d = eleArray[e].data;
		   if(d.name.indexOf(content) < 0){
			   d.display = "none";
		   }
	   }
   }
   
   function getProjectItemValue(event){
	   event = event ? event : window.event; 
	   var obj = event.srcElement ? event.srcElement : event.target;
	   var arr=new Array();
	   arr=$(obj).text().split("_");
	   var priarr=new Array();
	   priarr=$(obj).next().text().split("元");
	   $(proNoClick).parent().parent().find("input[name='proid']").val($(obj).attr("projectInfoId"));
	   $(proNoClick).parent().parent().find("input[name='prono']").val(arr[0]);
	   $(proNoClick).parent().parent().find("input[name='proname']").val(arr[1]);
	   $(proNoClick).parent().parent().find("input[name='proprice']").val(priarr[0]);
	   $(proNoClick).parent().parent().find("input[name='promoney']").val(priarr[0]);
	   selectEmployee($(obj).attr("projectInfoId"));
	   $("#upWindow").css("display","none");
	   var targetElement = $(proNoClick).parent().parent().find("input[name='procoupon']");
	   targetElement.focus();
	   countprice();
   }
   
 //计算订单金额和实际金额
   function countprice(){
	   var eaprice = $(document).find("input[name='proprice']");
	   var eamoney = $(document).find("input[name='promoney']");
		var num = 0;
		var orderAmount = 0;
		for (var i = 0; i < eamoney.length; i++) {
			num = Number(num) + Number($(eamoney[i]).val());
		}
		for(var i = 0; i < eaprice.length; i++){
			orderAmount = Number(orderAmount) + Number($(eaprice[i]).val());
		}
		$(".num").text("￥"+parseInt(num));
		$("input[name='orderAmount']").val(parseInt(orderAmount));
   }
   
   function getProjectMoneyValue(event){
	   event = event ? event : window.event; 
	   var obj = event.srcElement ? event.srcElement : event.target; 
	   var arr=new Array();
	   arr=$(obj).prev().text().split("_");
	   var priarr=new Array();
	   priarr=$(obj).text().split("元");
	   $(proNoClick).parent().parent().find("input[name='proid']").val($(obj).prev().attr("projectInfoId"));
	   $(proNoClick).parent().parent().find("input[name='prono']").val(arr[0]);
	   $(proNoClick).parent().parent().find("input[name='proname']").val(arr[1]);
	   $(proNoClick).parent().parent().find("input[name='proprice']").val(priarr[0]);
	   $(proNoClick).parent().parent().find("input[name='promoney']").val(priarr[0]);
	   selectEmployee($(obj).prev().attr("projectInfoId"));
	   $("#upWindow").css("display","none");
	   var targetElement = $(proNoClick).parent().parent().find("input[name='procoupon']");
	   targetElement.focus();
	   countprice();
   }
   
   function copyProject(){
  	  var ptable =$("<tr class ='pro_tr project-body'>"+
					  	"<td class='project last-tr'>"+
					  	   "<table>"+
					  	     "<tbody>"+
					  	       "<tr class='tr-input'>"+
					  	           "<td class='code'><input type='hidden' name='proid'><input type='text' name='prono' class='border-left-none'></td>"+
					  	           "<td class='name'><input type='text' name='proname'></td>"+
					  	           "<td class='money'><input type='text' name='proprice'  readonly='true'></td>"+
					  	           "<td class='discount'><input type='text' name='procoupon' onkeyup='value=this.value.replace(/\D+/g,'')'></td>"+
					  	           "<td class='sum'><input type='text' name='promoney' onkeyup='value=this.value.replace(/\D+/g,'')' class='border-right-none'></td>"+
					  	       "</tr>"+
					  	     "</tbody>"+
					  	   "</table>"+
					  	"</td>"+
					  	"<td class='career last-tr'>"+
					  	   "<table>"+
					  	     "<tbody>"+
					  	       "<tr class='tr-input'>"+
					  	           "<td class='agent-id dropdown'><input type='hidden' name='semployeeid'><input type='text' name='semployeeno' class='border-left-none'></td>"+
					  	           "<td class='appoint'>"+
						  	         "<div class='btn-group dropup' supdiv = 'supdiv'>"+
						               "<button type='button' class='appoints' data-toggle='dropdown' id = 'sappoint' supname = 'supname'>是</button>"+
						               "<ul class='dropdown-menu' style='width:160px;'>"+
							              "<li><a href='javascript://' supa = 'supa'>是</a></li>"+
							              "<li role='presentation' class='divider'></li>"+
							              "<li><a href='javascript://' supa = 'supa'>否</a></li>"+
						               "</ul>"+
						              "</div>"+
					  	           "</td>"+
					  	           "<td class='performance'><input type='text' name='sperformance' onkeyup='value=this.value.replace(/\D+/g,'')' class=' border-right-none'></td>"+
					  	       "</tr> "+
					  	     "</tbody>"+
					  	   "</table>"+
					  	"</td> "+
					  	"<td class='designer last-tr'>"+
					  	   "<table>"+
					  	     "<tbody>"+
					  	       "<tr class='tr-input'>"+
					  	           "<td class='dropdown agent-id'><input type='hidden' name='memployeeid'><input type='text' name='memployeeno' class='border-left-none'></td>"+
					  	           "<td class='appoint'>"+
						  	         "<div class='btn-group dropup' supdiv = 'supdiv'>"+
						               "<button type='button' class='appoints' data-toggle='dropdown' id = 'mappoint' supname = 'supname'>是</button>"+
						               "<ul class='dropdown-menu' style='width:160px;'>"+
							              "<li><a href='javascript://' supa = 'supa'>是</a></li>"+
							              "<li role='presentation' class='divider'></li>"+
							              "<li><a href='javascript://' supa = 'supa'>否</a></li>"+
						               "</ul>"+
						              "</div>"+
                                   "</td>"+
					  	           "<td class='performance'><input type='text' name='mperformance' onkeyup='value=this.value.replace(/\D+/g,'')' class=' border-right-none'></td>"+
					  	       "</tr>"+
					  	     "</tbody>"+
					  	  "</table>"+
					  	"</td>"+
					  	"<td class='technician last-tr'>"+
					  	   "<table>"+
					  	     "<tbody>"+
					  	       "<tr class='tr-input'>"+
					  	           "<td class='dropdown agent-id'><input type='hidden' name='aemployeeid'><input type='text' name='aemployeeno' class='border-left-none'></td>"+
					  	           "<td class='appoint'>"+
						  	           "<div class='btn-group dropup' supdiv = 'supdiv'>"+
						               "<button type='button' class='appoints' data-toggle='dropdown' id = 'aappoint' supname = 'supname'>是</button>"+
						               "<ul class='dropdown-menu' style='width:160px;'>"+
							              "<li><a href='javascript://' supa = 'supa'>是</a></li>"+
							              "<li role='presentation' class='divider'></li>"+
							              "<li><a href='javascript://' supa = 'supa'>否</a></li>"+
						               "</ul>"+
						              "</div>"+
                                   "</td>"+
					  	           "<td class='performance'><input type='text' name='aperformance' onkeyup='value=this.value.replace(/\D+/g,'')' class='border-right-none'></td>"+
					  	       "</tr>"+
					  	     "</tbody>"+
					  	  "</table>"+
					  	"</td>"+
					  "</tr>");
	  	<%-- "<tr class='pm'>"+
	      "<td colspan='4' class='pm'>"+
	          "<div class='plus' id='s_plus'><img src='<%=basePath %>app/img/head/plus.png' alt=''/></div>"+
	          "<div class='minus' id='s_minus'><img src='<%=basePath %>app/img/head/minus.png' alt=''/></div>"+
	      "</td>"+
	  "</tr>"+ --%>
		 	<%-- "<tr class='pm'>"+
		     "<td colspan='4' class='pm'>"+
		         "<div class='plus' id='m_plus'><img src='<%=basePath %>app/img/head/plus.png' alt=''/></div>"+
		         "<div class='minus' id='m_minus'><img src='<%=basePath %>app/img/head/minus.png' alt=''/></div>"+
		     "</td>"+
		 "</tr>"+ --%>
	       <%-- "<tr class='pm'>"+
	           "<td colspan='4' class='pm'>"+
	               "<div class='plus' id='a_plus'><img src='<%=basePath %>app/img/head/plus.png' alt=''/></div>"+
	               "<div class='minus' id='a_minus'><img src='<%=basePath %>app/img/head/minus.png' alt=''/></div>"+
	           "</td>"+
	       "</tr>"+ --%>
        $("#hair_class").append(ptable);
    }
    //暂时没用
	function copyStye(obj){
	  var str = $("<tr class='tr-input'>"+
					"<td class='agent-id dropdown'>"+
					    "<input type='hidden' name='semployeeid'>"+
		                "<input type='text' name='semployeeno'>"+
		            "</td>"+
			  	    "<td class='appoint'><select name='sappoint'><option value='1' selected = 'selected'>是</option><option value='0'>否</option></select></td>"+
			  	    "<td class='performance'><input type='text' name='sperformance' onkeyup='value=this.value.replace(/\D+/g,'')'></td>"+
			  	    "<td class='allocation'><input type='text' name='sallot'></td>"+
			  	  "</tr> ");
			  	  <%-- "<tr class='pm'>"+
			  	    "<td colspan='4' class='pm'>"+
			  	       "<div class='plus' id='s_plus'><img src='<%=basePath %>app/img/head/plus.png' alt=''/></div>"+
			  	       "<div class='minus' id='s_minus'><img src='<%=basePath %>app/img/head/minus.png' alt=''/></div>"+
			  	    "</td>"+
			  	  "</tr>" --%>
	  $(obj).parent().parent().parent().append(str);
	}
	//暂时没用
	function copyMes(obj){
		  var mtr = $("<tr class='tr-input'>"+
				        "<td class='dropdown agent-id'>"+
				           "<input type='hidden' name='memployeeid'>"+
                           "<input type='text' name='memployeeno'>"+
                        "</td>"+
				  	    "<td class='appoint'><select name='mappoint'><option value='1' selected = 'selected'>是</option><option value='0'>否</option></select></td>"+
				  	    "<td class='performance'><input type='text' name='mperformance'></td>"+
				  	    "<td class='allocation'><input type='text' name='mallot'></td>"+
				  	  "</tr> ");
				  	  <%-- "<tr class='pm'>"+
				  	    "<td colspan='4' class='pm'>"+
				  	       "<div class='plus' id='m_plus'><img src='<%=basePath %>app/img/head/plus.png' alt=''/></div>"+
				  	       "<div class='minus' id='m_minus'><img src='<%=basePath %>app/img/head/minus.png' alt=''/></div>"+
				  	    "</td>"+
				  	  "</tr>" --%>
		  $(obj).parent().parent().parent().append(mtr);
	}
	//暂时没用
	function copyAid(obj){
		  var atr = $("<tr class='tr-input'>"+
				  	    "<td class='agent-id dropdown'>"+
				  	         "<input type='hidden' name='aemployeeid'>"+
                             "<input type='text' name='aemployeeno'>"+
                        "</td>"+
				  	    "<td class='appoint'><select name='aappoint'><option value='1' selected = 'selected'>是</option><option value='0'>否</option></select></td>"+
				  	    "<td class='performance'><input type='text' name='aperformance' onkeyup='value=this.value.replace(/\D+/g,'')'></td>"+
				  	    "<td class='allocation'><input type='text' name='aallot'></td>"+
				  	  "</tr> ");
				  	  <%-- "<tr class='pm'>"+
				  	    "<td colspan='4' class='pm'>"+
				  	       "<div class='plus' id='a_plus'><img src='<%=basePath %>app/img/head/plus.png' alt=''/></div>"+
				  	       "<div class='minus' id='a_minus'><img src='<%=basePath %>app/img/head/minus.png' alt=''/></div>"+
				  	    "</td>"+
				  	  "</tr>" --%>
		  $(obj).parent().parent().parent().append(atr);
	}
   
</script>
</div> 
</body>
</html>