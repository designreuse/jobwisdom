<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/base.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>后台管理</title>

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
       <!-- <a href="" class="tip-bottom" data-original-title="销售管理">销售管理</a>-->
        <a href="#" class="current">销售统计</a>
    </div>
</div>


    <div class="container-fluid">

      <!--销售公共部分-->
<div class="quick-actions_homepage">
 <!--    <ul class="quick-actions t-menu">
        <li class="c-dblue"> <a href="sales-stat.html">平台销售统计</a> </li>
        <li class="c-yellow"> <a href="version-stat.html">版本销售统计</a></li>
        <li class="c-green"> <a href="reg-stat.html"> 区域销售统计 </a> </li>
        <li class="c-red"> <a href="shop-stat.html"> 商品销售统计 </a> </li>
        <li class="c-lblue"> <a href="year-stat.html">年度汇总统计</a></li>
    </ul> -->
</div>
<hr>


        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                        <h5>用户列表</h5>
                    </div>
                    <table data-toggle="table"
                             data-url="<%=basePath %>hchart/queryCount"
                               data-pagination="true"
                               data-side-pagination="server"
                               data-page-list="[5, 10, 20]"
                               data-height="480" id="salestable"
                               data-toolbar="#toolbar">
                            <thead>
                            <tr>
                                <th data-field="monthTime" data-sortable="true" >日期</th>
                                <th data-field="count1">单店数量</th>
                                <th data-field="moneyCount1" >金额</th>
                                <th data-field="count2">总部数量</th>
                                <th data-field="moneyCount2" >金额</th>
                                <th data-field="count3">分店数量</th>
                                <th data-field="moneyCount3" >金额</th>
                                <th data-field="count0" >总店数</th>
                                <th data-field="moneyCount0" >总金额</th>
                            </tr>
                            </thead>
                        </table>
                </div>
            </div>
        </div>

        <div class="row-fluid">
        <div class="span12">
            <div class="widget-box">
                <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                    <h5>平台销售走势图</h5>
                    <select name="" id="ptxszs_years" class="mgt3 js_select_year" onchange="platSaleChat()" >
                        <option value="2015">2015年</option>
                        <option value="2016">2016年</option>
                        <option value="2017">2017年</option>
                    </select>
                </div>
                <div class="widget-content">
                    <p id="platSaleChat"></p>
                </div>
            </div>
        </div>
    </div>

        <div class="row-fluid">
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                        <h5>月平台数量分析图</h5>
                        <select name="" id="ptyxs_years" class="width22 mgt3" onchange="platAnalysisChat()">
                            <option value="2015">2015年</option>
                        	<option value="2016">2016年</option>
                        	<option value="2017">2017年</option>
                        </select>
                        <select name="" id="ptyxs_month" class="width20 mgt3" onchange="platAnalysisChat()">
                            <option value="1">1月</option>
                            <option value="2">2月</option>
                            <option value="3">3月</option>
                            <option value="4">4月</option>
                            <option value="5">5月</option>
                            <option value="6">6月</option>
                            <option value="7">7月</option>
                            <option value="8">8月</option>
                            <option value="9">9月</option>
                            <option value="10">10月</option>
                            <option value="11">11月</option>
                            <option value="12">12月</option>
                        </select>
                    </div>
                    <div class="widget-content">
                        <div id="platAnalysisChat"></div>
                    </div>
                </div>
            </div>
            <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-signal"></i> </span>
                        <h5>销量图</h5>
                        <select name="" id="xsje_years" class="width22 mgt3" onchange="salesVolumeChar()">
                            <option value="2015">2015年</option>
                        	<option value="2016">2016年</option>
                        	<option value="2017">2017年</option>
                        </select>
                        <select name="" id="xsje_month" class="width20 mgt3" onchange="salesVolumeChar()">
                            <option value="1">1月</option>
                            <option value="2">2月</option>
                            <option value="3">3月</option>
                            <option value="4">4月</option>
                            <option value="5">5月</option>
                            <option value="6">6月</option>
                            <option value="7">7月</option>
                            <option value="8">8月</option>
                            <option value="9">9月</option>
                            <option value="10">10月</option>
                            <option value="11">11月</option>
                            <option value="12">12月</option>
                        </select>
                    </div>
                    <div class="widget-content">
                        <div id="salesVolumeChar"></div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>


<!--尾部代码页面引用-->
<%@include file="/WEB-INF/view/footer.jsp"%>
<%@include file="/WEB-INF/js.jsp"%>
  <script type="text/javascript">
 	var baseUrl = "<%=basePath %>";
   </script>
<script type='text/javascript' src='<%=basePath %>js/hchart.js'></script>

  </body>
</html>