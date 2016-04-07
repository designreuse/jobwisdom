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
        <a href="#" class="current">个人销售排名</a>
    </div>
</div>
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
                <div class="widget-box">
                    <div class="widget-title"><span class="icon"><i class="icon-th"></i></span>
                        <h5>本月个人开户汇总</h5>
                    </div>
                    <table data-toggle="table"
                           data-method="post"
                           data-url="<%=basePath %>homepage/persSummary"
                           data-pagination="true"
                           data-query-params="queryParams"
                           data-side-pagination="server"
                           data-page-list="[5, 10, 20, 50, 100, 200]"
                           data-height="490" id="monthPerTable"
                           data-toolbar="#toolbar">
                        <thead>
                        <tr>
                            <th data-field="creator_Id" >姓名</th>
                            <th data-field="count" data-sortable="true">店数</th>
                            <th data-field="lian_count" >连锁店</th>
                            <th data-field="dan_count" >单店</th>
                            <th data-field="fen_count" >分店</th>
                        </tr>
                        </thead>
                    </table>
                    <!--</div>-->
                </div>
            </div>

            <div class="row-fluid">
                <div class="span12">
                    <div class="widget-box">
                        <div class="widget-title"> <span class="icon"> <i class="icon-list"></i> </span>
                            <h5>个人销售前10名</h5>
                        </div>
                        <div class="widget-content">
                            <div id="perCityTTChart"></div>
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
 	/*本月个人开户汇总*/
   </script>
      <!--自定义js-->
   <script type='text/javascript' src='<%=basePath %>js/persrank.js'></script>
  </body>
</html>