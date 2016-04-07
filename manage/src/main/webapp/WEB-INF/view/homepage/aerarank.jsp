<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/WEB-INF/base.jsp"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <meta name="author" content="lives"/>
      <meta name="description" content="admin"/>
      <meta name="keywords" content="admin lives 店务管理 美容美发"/>

      <title>店务系统后台管理</title>


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
        <a href="#" class="current">全国区域排名</a>
    </div>
</div>


    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                        <h5>本月区域开户汇总</h5>
                    </div>
                    <!--<table data-toggle="table"
                           data-url="http://mikepenz.com/jsfiddle/"
                           data-search="true"
                           data-pagination="true"
                           data-side-pagination="server"
                           data-page-list="[10, 20, 50, 100, 200]"
                           data-height="490" id="monthRegNumberTable"
                        <thead>
                        <tr>
                            <th data-field="id">省份</th>
                            <th data-field="name" data-align="center">城市</th>
                            <th data-field="price" data-sortable="true">本月店数</th>
                            <th data-field="price" >连锁店</th>
                            <th data-field="price" >单店</th>
                        </tr>
                        </thead>
                    </table>-->
                    <table data-toggle="table"
                           data-method="post"
                           data-url="<%=basePath %>homepage/monthSummary"
                           data-pagination="true"
                           data-side-pagination="server"
                           data-query-params="queryParams"
                           data-page-list="[10, 20, 50, 100, 200]"
                           data-height="490" id="monthRegNumberTable"
                           data-toolbar="#toolbar">
                        <thead>
                        <tr>
                            <th data-field="province" class="span3">省份</th>
                            <th data-field="city">城市</th>
                            <th data-field="count" data-sortable="true">本月店数</th>
                            <th data-field="lian_count" data-sortable="true">连锁店</th>
                            <th data-field="dan_count" data-sortable="true">单店</th>
                            <th data-field="fen_count" data-sortable="true">分店</th>
                        </tr>
                        </thead>
                    </table>
                    <!--</div>-->
                </div>
            </div>

            <div class="row-fluid">
                <div class="span6">
                    <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-list"></i> </span>
                        <h5>省份前10名</h5>
                    </div>
                    <div class="widget-content">
                        <div id="proviceTTChart"></div>
                    </div>
                </div>
                </div>
                <div class="span6">
                <div class="widget-box">
                    <div class="widget-title"> <span class="icon"> <i class="icon-list"></i> </span>
                        <h5>城市前10名</h5>
                    </div>
                    <div class="widget-content">
                        <div id="cityTTChart"></div>
                    </div>
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
      <!--自定义js-->
   <script type='text/javascript' src='<%=basePath %>js/aerarank.js'></script>
  </body>
</html>