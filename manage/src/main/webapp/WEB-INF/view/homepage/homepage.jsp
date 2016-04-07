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
        <a href="#" class="current"></a>
    </div>
</div>

    <div class="container-fluid">
      <div  class="quick-actions_homepage">
          <ul class="quick-actions">
              <li class="bg_lb span1"> <a href="<%=basePath %>cust/view/query"> <i class="iconfont icon-user_add"></i> <span class="label label-important">20</span>新增客户 </a> </li>
              <li class="bg_lg span2"> <a href="<%=basePath %>homepage/view/aerarank"> <i class="iconfont icon-gengxinpaiming"></i>销售区域排名</a> </li>
              <li class="bg_ly span1"> <a href="<%=basePath %>homepage/view/persrank"> <i class="iconfont icon-jiangpai"></i>个人销售排名</a> </li>
              <li class="bg_lo span1"> <a href="<%=basePath %>user/view/query"> <i class="iconfont icon-qudaoguanli"></i><span class="label label-important">3</span> 新增渠道商 </a> </li>
              <li class="bg_ls span2"> <a href="<%=basePath %>custclue/view/publicquery"> <i class="iconfont icon-dianzan"></i><span class="label label-important">3</span> 客户公海</a> </li>
          </ul>
      </div>

      <!--Chart-box-->
<div class="row-fluid">
              <div class="widget-box">
                  <div class="widget-title bg_lg"><span class="icon"><i class="icon-signal"></i></span>
                      <h5>本月新增客户数</h5>
                      <select class="width15 c-header" name="province3" id="province"  ></select>
                      <select class="width15 c-header" name="city3" id="city"></select>
                      <select class="width15 c-header" name="area3" id="area"></select>
                  </div>
                  <div class="widget-content" >
                      <div class="row-fluid">
                          <div class="span9">
                              <div id="addUserChat"></div>
                          </div>
                          <div class="span3">
                              <ul class="site-stats">
                                  <a href="<%=basePath %>cust/view/query"><li class="bg_lh"><i class="iconfont icon-yonghu"></i> <strong class="a"></strong> <small>总客户数</small></li></a>
                                  <a href="<%=basePath %>cust/view/query?type=2"><li class="bg_lh"><i class="iconfont icon-yonghu"></i> <strong class="b"></strong> <small>连锁客户 </small></li></a>
                                  <a href="<%=basePath %>cust/view/query?type=3"><li class="bg_lh"><i class="iconfont icon-yonghu "></i> <strong class="c"></strong> <small>分店客户</small></li></a>
                                  <a href="<%=basePath %>cust/view/query?type=1"><li class="bg_lh"><i class="iconfont icon-yonghu"></i> <strong class="d"></strong> <small>单店客户</small></li></a>
                                  <a href="<%=basePath %>project/view/salesproject"><li class="bg_lh"><i class="iconfont icon-jinlingyingcaiicon13"></i> <strong class="e"></strong> <small>平台销售额</small></li></a>
                                  <a href="<%=basePath %>project/view/salesproject"><li class="bg_lh"><i class="iconfont icon-jinlingyingcaiicon13"></i> <strong class="f"></strong> <small>商品销售</small></li></a>
                              </ul>
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
 	new PCAS("province3","city3","area3");
 	$("#province").change(function(){
 		//alert("选中值变更,事件触发了。");
 		eachDaySales();
 		});
 	$("#city").change(function(){
 		//alert("选中值变更,事件触发了。");
 		eachDaySales();
 		});
 	$("#area").change(function(){
 		//alert("选中值变更,事件触发了。");
 		eachDaySales();
 		});

   </script>
      <!--自定义js-->
      <script type='text/javascript' src='<%=basePath %>js/homepage.js'></script>
  </body>
</html>