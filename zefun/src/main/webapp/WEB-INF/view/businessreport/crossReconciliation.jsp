<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>

<div class="mainwrapper" style="background-position: 0px 0px;">
    <!--loading start-->
    <%@ include file="/loading.jsp" %>
    <!--loading end-->

    <!--left-panel start-->
    <%@ include file="/menu.jsp" %>
    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
<div class="rightpanel" style="margin-left: 200px;">
      	<%@ include file="/top.jsp" %>
      	<!-- 页面代码 -->
<!--loading end-->
    <!--left-panel start-->
    <!-- LEFT PANEL 开始-->

<!--LEFT PANEL结束 -->

    <!--left-panel end-->

    <!--RIGHT PANEL开始 -->
<style>
    .ls-detai-table tbody tr:nth-of-type(2) {
        height:auto;
        line-height:inherit;
    }
    .bubble-toggle {
        display: block;

    }
    
    .fs24 {
    	font-size:15px;
    }

    .bubble {
        background-color: #fff;
        color: #959595;
        text-align: left;
        left:-25px;
        top:-55px;
        box-shadow:  0 1px 5px rgba(0,0,0,.2);
    }

    .bubble em,.bubble span {
        left: 50%;
        bottom: -20px;
        position: absolute;
        top:auto;
        -webkit-transform: translatex(-50%);
        -moz-transform: translatex(-50%);
        -ms-transform: translatex(-50%);
        -o-transform: translatex(-50%);
        transform: translatex(-50%);
        border-color:#fff transparent  transparent transparent;
        z-index: 2;
        border-width: 10px;
        border-style: solid;
         }

    .bubble span {
        bottom: -21px;
        border-color:#dddddd transparent  transparent transparent;
        z-index: 1;

    }

</style>
    

<div class="maincontent">
    <div class="contentinner">
        <div class="widgetcontent">
            <div class="more-toolbar" >

                <div class="table-toolbar">

                        <div class="clearfix">
                             <span class="font-size-16" style="vertical-align: middle">选择对账月</span>
                            <div class="btn-group">
	                        <button data-toggle="dropdown" class="btn dropdown-toggle">${month }<span class="caret"></span></button>
	                        <ul class="dropdown-menu">
	                            <c:forEach items="${monthList }" var="month">
	                            	<li><a href="<%=basePath %>reconciliation/view/crossReconciliation?month=${month }">${month }</a></li>
	                            </c:forEach>
	                        </ul>
	                    	</div>
                        </div>

                </div>
                <div class="clearfix"></div>
            </div><!--more-toolbar-->
            <!--跨店详情-->
            <table class="table table-bordered ls-detai-table table-striped">
                <thead>
                <tr>
                    <td></td>
                    <td colspan="4">他店会员本店消费</td>
                    <td colspan="4">本店会员他店消费</td>
                    <td  colspan="2"></td>
                </tr>
                </thead>
                <tbody id="menuBody">
                <tr>
                    <th>门店名称</th>
                    <th>划卡消费人数</th>
                    <th>划卡消费金额</th>
                    <th>充值人数</th>
                    <th>充值金额</th>
                    <th>划卡消费人数</th>
                    <th>划卡消费金额</th>
                    <th>充值人数</th>
                    <th>充值金额</th>
                    <th>出账金额</th>
                    <th>对账</th>
                </tr>
				<c:forEach items="${storeInformations}" var="store" varStatus="status">
					<tr>
					<td >${store.storeName }</td>
					<td>${store.meToOther.cardConsumedCnt }</td>
					<td class="${store.storeId}1">
					<a href="javascript:scanOtherDetail('${store.storeName}','${store.storeId }','${month }','1')">
					<fmt:formatNumber value="${store.meToOther.cardConsumedAmt }" pattern="##.##" minFractionDigits="2" var="cardAmt" />${cardAmt }
					</a>
					</td>
					<td>${store.meToOther.chargeCardCnt }</td>
					<td class="${store.storeId}2">
					<a href="javascript:scanOtherDetail('${store.storeName}','${store.storeId }','${month }','2')">
					<fmt:formatNumber value="${store.meToOther.chargeCardAmt }" pattern="##.##" minFractionDigits="2" var="chargeAmt" />${chargeAmt }
					</a>
					</td >					
					<td>${store.otherToMe.cardConsumedCnt }</td>
					<td class="${store.storeId}3">
					<a href="javascript:scanMeDetail('${store.storeName}','${store.storeId }','${month }','3')">
					<fmt:formatNumber value="${store.otherToMe.cardConsumedAmt }" pattern="##.##" minFractionDigits="2" var="amt" />${amt }
					</a>
					</td>
					<td>${store.otherToMe.chargeCardCnt }</td>
					<td class="${store.storeId}4">
					<a href="javascript:scanMeDetail('${store.storeName}','${store.storeId }','${month }','4')">
					<fmt:formatNumber value="${store.otherToMe.chargeCardAmt }" pattern="##.##" minFractionDigits="2" var="oamt" />${oamt}
					</a>
					</td>
					<td class="series${store.storeId}">						
						<c:choose>							
							<c:when test="${store.chargeOffState==1 }">
								<c:set var="stateInfo" value="待审核"></c:set>
							</c:when>
							<c:when test="${store.chargeOffState==3}">
								<c:set var="stateInfo" value="未出账"></c:set>
							</c:when>
							<c:otherwise>
								<c:set var="stateInfo" value="已出账"></c:set>
							</c:otherwise>
						</c:choose>
						${stateInfo}
					</td>
					<td><%-- <input class="btn w100" type="button" onclick="checkResult('${store.storeId}','${month}','${stateInfo}')"/> --%>
						<i class="iconfont icon-xianjin-SR left-icon" onclick="checkResult('${store.storeId}','${month}','${stateInfo}')"></i>
					</td>
					</tr>
				</c:forEach>
                </tbody>

            </table>
            <!--跨店详情结束-->


        </div>

        <div class="widgetcontent">
            <div class="store-qita">
            	<div hidden="hidden" id="head" class="fs24">
            	<span style="color:#13828d;" id="descripeOther"></span>---<span id="storeName" style="padding-right:20px;color:#f15917;">华北店</span><span style="color:#13828d;">消费类型</span>:<span id="consumedType" style="padding-right:20px;color:#f15917;"></span>
            	<span style="color:#13828d;">总金额</span>:<span id="totalAmt" style="color:#f15917;"></span>
            	</div>
                <!--其他店划卡消费金额详情-->
                <table id="others" class="table table-bordered ls-detai-table table-striped">
                    <thead id="otherThead">
                    </thead> 
                    <tbody id="otherTbody">
                   <!--  <tr>
                        <th>流水单号</th>
                        <th>顾客</th>
                        <th>消费时间</th>
                        <th>服务类型</th>
                        <th>服务内容</th>
                        <th>划卡/充值金额</th>
                        <th>对账/付款</th>
                        <th style="width: 130px;">数据审核</th>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" class="bd" placeholder="输入框"></td>
                        <td>未提交</td>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td>等待审核</td>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td class="store-co1">已通过</td>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td class="store-co2 bubble-toggle">
                            未通过
                        <div class="bubble width150 hide">
                            这数据不对啊，你数学是体育
                            老师教的吗？
                            <em></em>
                            <span></span>
                        </div>
                        </td>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td>未提交</td>
                    </tr> -->
 <!--                    <tr class="store-tr">
                        <td colspan="2">他店划卡金额汇总：<span class="store-co2">6000.00</span></td>
                        <td colspan="6"> <button class="btn fr">确定</button></td>
                    </tr> -->
                    </tbody>

                </table>
                <!--其他店划卡消费金额详情结束-->
                <!--其他店划卡消费金额详情-->
                <table class="table table-bordered ls-detai-table table-striped hide">
                    <thead>
                    <tr>

                        <td >华北店-其他店消费金额</td>
                        <td colspan="9"></td>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>流水单号</th>
                        <th>顾客</th>
                        <th>消费时间</th>
                        <th>服务类型</th>
                        <th>服务内容</th>
                        <th>划卡/充值金额</th>
                        <th>对账/付款</th>
                        <th>数据审核</th>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" class="bd" placeholder="输入框"></td>
                        <td>未提交</td>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="6000.00"></td>
                        <td>等待审核</td>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td class="store-co1">已通过</td>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td class="store-co2">未通过</td>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td>未提交</td>
                    </tr>
                    <tr class="store-tr">
                        <td colspan="2">他店划卡金额汇总：<span class="store-co2">6000.00</span></td>
                        <td colspan="6"> <button class="btn fr">确定</button></td>
                    </tr>
                    </tbody>

                </table>
                <!--其他店划卡消费金额详情结束-->
            </div>
<!--             <div class="store-bendian">
                本店划卡消费金额详情
                <table class="table table-bordered ls-detai-table table-striped hide">
                    <thead>
                    <tr>

                        <td >华北店－本店划卡消费</td>
                        <td colspan="9"></td>

                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <th>流水单号</th>
                        <th>顾客</th>
                        <th>消费时间</th>
                        <th>服务类型</th>
                        <th>服务内容</th>
                        <th>划卡/充值金额</th>
                        <th>对账/付款</th>
                        <th>数据审核</th>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" class="bd" placeholder="输入框"></td>
                        <td class="shuju"><span class="iconfont icon-xuanzhongzhuangtai store-co1"></span> <span class="iconfont icon-xx"></span></td>
                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td class="shuju"><span class="iconfont icon-xuanzhongzhuangtai store-co1"></span> <span class="iconfont icon-xx"></span></td>

                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td class="shuju"><span class="iconfont icon-xuanzhongzhuangtai store-co1"></span> <span class="iconfont icon-xx"></span></td>
                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td class="shuju"><span class="iconfont icon-xuanzhongzhuangtai store-co1"></span> <span class="iconfont icon-xx"></span></td>
                    </tr>
                    <tr>
                        <td>2222222</td>
                        <td>张胜男</td>
                        <td >02-25 17：09</td>
                        <td>项目</td>
                        <td>洗剪吹</td>
                        <td>80.00</td>
                        <td  class="store-txt"><input type="text" value="5860.00"></td>
                        <td class="shuju">
                            <span class="iconfont icon-xuanzhongzhuangtai"></span>
                            <span class="iconfont icon-xx store-co2" data-target="#account-modal" data-toggle="modal"></span>
                        </td>
                    </tr>
                    <tr class="store-tr">
                        <td colspan="2">他店划卡金额汇总：<span class="store-co2">6000.00</span></td>
                        <td colspan="6"> <button class="btn fr">确定</button></td>
                    </tr>

                    </tbody>

                </table>
                本店划卡消费金额详情结束
            </div> -->


        </div>
    </div>
</div>
<!--跨店对账模态框-->
<div class="modal account-modal hide" id="account-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content goods-shezhi-modal">
            <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title" id="myModalLabel">请输入数据存在的问题</h4>
        </div>
            <div class="modal-body">
                <textarea id="myTextarea" cols="30" rows="10" class="text-account"></textarea>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn modal-cancel" data-dismiss="modal">取消</a>
                <a class="btn btn-primary modal-confirm" href="javascript:saveDetail(2)">保存</a>
            </div>
        </div>
    </div>
</div>

<script>
   jQuery(function() {
     /*   jQuery(".shuju .icon-xx").on("click",function() {
           jQuery(this).addClass('store-co2').siblings().removeClass("store-co1")
       })

       jQuery(".shuju .icon-xuanzhongzhuangtai").on("click",function() {
           jQuery(this).addClass('store-co1').siblings().removeClass("store-co2")
       }) */
       
       jQuery(".table .s-price1").on("click",function() {
           jQuery(this).addClass("store-current").siblings('.s-price').removeClass('store-current')
           jQuery(".store-qita .table").addClass('hide')
           jQuery(".store-bendian .table").removeClass('hide')
       })

       jQuery(".bubble-toggle").hover(function(){
           jQuery(this).children(".bubble").removeClass("hide");
       },function(){
           jQuery(this).children(".bubble").addClass("hide");
       });
      /* if(!window.name){
	      var storeName=jQuery("#menuBody tr:eq(1) td:eq(0)").html();
	      var storeId=jQuery("#menuBody tr:eq(1) td:eq(0)").attr("name");
	      scanOtherDetail(storeName,storeId,'${month}',1);
      } */
   })

</script>

</div>
    <!--<button class="btn" data-target="#jietu" data-toggle="modal">截图</button>-->
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
   <!-- <div class="left-show-btn">
        <span class="iconfont icon-quanbu110"></span>
    </div>-->
    <a href="" class="showmenu"></a>

    <i class="iconfont icon-fuzhi"></i>

    <!--提示框-->
    <div class="alertWrap hide">
    <div class="toast">
        <img src="assets/images/jiazai.gif" alt=""/>
    </div>
</div>

<script>
    jQuery(function () {
        /*/!*模态框居中*!/
        jQuery('#jietu').on('show.bs.modal', function (e) {
            var left = (jQuery(window).width() - jQuery("#jietu").width())/2 +300;
            jQuery("#jietu").css("left", left);
        });*/

      /*启用截图*/
      var $inputImage = jQuery('.inputfile');
      var URL = window.URL || window.webkitURL;
      var blobURL;
      var $image = jQuery('.crop-container > img');
      var options = {
        aspectRatio: 1 / 1,
        autoCropArea:1,
        minContainerWidth: 1000,
        minContainerHeight: 580,
        minCanvasWidth:1000,
        minCanvasHeight:580,
        center: true,
        checkImageOrigin:true,
        width:580,
        height:580,
        strict: true,
        responsive:false,
        dragCrop: false,
        cropBoxMovable: false,
        cropBoxResizable: false
      };

      $image.cropper(options);

      if (URL) {
        $inputImage.change(function () {
          var files = this.files;
          var file;

          if (!$image.data('cropper')) {
            return;
          }

          if (files && files.length) {
            file = files[0];

            if (/^image\/\w+$/.test(file.type)) {
              blobURL = URL.createObjectURL(file);
              $image.one('built.cropper', function () {
                URL.revokeObjectURL(blobURL); // Revoke when load complete
              }).cropper('reset').cropper('replace', blobURL);
              $inputImage.val('');
            } else {
              $body.tooltip('Please choose an image file.', 'warning');
              alert("请选择一个文件");
            }
          }
        });
      } else {
        $inputImage.prop('disabled', true).parent().addClass('disabled');
      }

      cropBoxData = $image.cropper('getCropBoxData');
      canvasData = $image.cropper('getCanvasData');
      options.built = function () {
        $image.cropper('setCropBoxData', cropBoxData);
        $image.cropper('setCanvasData', canvasData);
      };

      var result  = $image.cropper('getCroppedCanvas',options);
       jQuery(".zoomin").on("click", function () {
         $image.cropper("zoom", 0.1);
        });

       jQuery(".zoomout").on("click", function () {
         $image.cropper("zoom", -0.1);
        })
    })
</script>


    <!--返回顶部-->
    <div id="return-top" class="return-top">
  <span class="iconfont icon-huidaodingbu"></span>
</div>

<script>
  window.onload = function(){
    var oTop = document.getElementById("return-top");
    /*var screenw = document.documentElement.clientWidth || document.body.clientWidth;
    var screenh = document.documentElement.clientHeight || document.body.clientHeight;
    oTop.style.left = screenw  +"px";
    oTop.style.top = screenh - oTop.offsetHeight + "px";
    window.onscroll = function(){
      var scrolltop = document.documentElement.scrollTop || document.body.scrollTop;
      oTop.style.top = screenh - oTop.offsetHeight + scrolltop +"px";
    }*/
    oTop.onclick = function(){
      document.documentElement.scrollTop = document.body.scrollTop =0;
    }
  }
  
  function checkResult(thatStoreId,month,stateInfo){
	  if(stateInfo != "已出账"){
		  dialog("不是已出账状态,无法对账哦!");
		  return ;
	  }
	  jQuery.ajax({
		  url:baseUrl+"reconciliation/view/checkReconciliation",
		  data:{"thatStoreId":thatStoreId,"month":month},
		  type:"post",
		  success:function(e){
			  if(e.code!=0){
				  dialog(e.msg);
			  }else{
				  jQuery(".series"+thatStoreId).text(e.msg);
			  }
		  }
	  })
  }
  
  function scanOtherDetail(storeName,thatStoreId,month,type){
	  jQuery(".store-current").removeClass("store-current");
	  jQuery("."+thatStoreId+type).addClass("store-current");
	  jQuery("#descripeOther").text("他店会员来本店消费");
	  jQuery("#head").removeAttr("hidden");
	  jQuery("#storeName").text(storeName);	  
	  var tvalue="划卡消费";
	  if (type==2){
		  tvalue="充值消费";
	  }
	  jQuery("#consumedType").text(tvalue);
	  jQuery.ajax({
		  type:"post",
		  data:{"thatStoreId":thatStoreId,"month":month,"type":type},
		  dataType:"json",
		  url:baseUrl + "reconciliation/view/crossReconciliationdetail",
		  success:function(returnData){
			  parseOtherData(returnData.code,returnData.msg);
		  }
	  })
  }
  
  function scanMeDetail(storeName,thatStoreId,month,type){
	  jQuery(".store-current").removeClass("store-current");
	  jQuery("."+thatStoreId+type).addClass("store-current");
	  jQuery("#descripeOther").text("本店会员去他店消费");
	  jQuery("#head").removeAttr("hidden");
	  jQuery("#storeName").text(storeName);	 
	  jQuery("#dept").text("他店");
	  var tvalue="划卡消费";
	  if (type==4){
		  tvalue="充值消费";
	  }
	  jQuery("#consumedType").text(tvalue);
	  jQuery.ajax({
		  type:"post",
		  data:{"thatStoreId":thatStoreId,"month":month,"type":type},
		  dataType:"json",
		  url:baseUrl + "reconciliation/view/crossReconciliationdetail",
		  success:function(returnData){		
			  parseMeData(returnData.code,returnData.msg);
		  }
	  })
  }
  var detailId=null;
  function disagree(id){
	  detailId=id;
	  jQuery("#account-modal").modal("show");
  }
  
  function saveDetail(type){
	  if(type==1){
		  var detailArrayObj=new Array();
		  var detailObj=jQuery(".detailTr");
		  var le=0;
		  for(var i=0;i<detailObj.length;i++){
			  var serialId=jQuery(detailObj[i]).find(".detail").val();
			  var amount=jQuery(detailObj[i]).find("input[name='hasValue']").val();	
			  var content=jQuery(detailObj[i]).find(".remark").text();
			  if(content=="审核已通过"){
				  le++;
			  }
			  if(amount==""){
				  dialog("对账数据未填写,请先补充完整!");
				  jQuery(detailObj[i]).find("input[name='hasValue']").focus();
				  return ;
			  }else{
				  if(content!="审核已通过"){
					  jQuery(detailObj[i]).find(".remark").text("等待审核");
				  }				  
			  }
			  var state=jQuery(detailObj[i]).find(".remark").text();
			  if(state=='等待审核'){
				  state=2;
			  }
			  if(state=='审核已通过'){
				  state=4;
			  }
			  var tdObj={"serialId":serialId,"reconciliation":amount,"state":state};
			  detailArrayObj.push(tdObj);
		  }
		  var arrayObjStr = JSON.stringify(detailArrayObj);
		  if(le==detailObj.length){
			  dialog("审核已通过,不允许修改!");
			  return ;
		  }
		  jQuery.ajax({
			  url:baseUrl+"reconciliation/view/crossReconciliationupdate",
			  data:{"arrayObjStr":arrayObjStr},
			  type:"post",
			  success: function (e){
				  if(e.code!=0){
					  dialog("更新失败!");
				  }else{
					  dialog("更新成功!");
					  jQuery("#head").attr("hidden","hidden");
					  jQuery("#otherThead").empty();
					  jQuery("#otherTbody").empty();
				  }
			  }
		  })
	  }else{
		  var detailArrayObj=new Array();
		  var state=null;
		  var remark=jQuery("#myTextarea").val();
		  var tdObj={"remark":remark,"serialId":detailId,"state":3};
		  detailArrayObj.push(tdObj);
		  var arrayObjStr = JSON.stringify(detailArrayObj);
		  jQuery.ajax({
			  url:baseUrl+"reconciliation/view/crossReconciliationupdate",
			  data:{"arrayObjStr":arrayObjStr},
			  type:"post",
			  success:function(e){
				  if(e.code!=0){
					  dialog("更新失败!");
				  }else{
					  dialog("更新成功!");
				  }
				  jQuery(".account-modal").modal("hide");
				  /* jQuery("#head").attr("hidden","hidden");
				  jQuery("#otherThead").empty();
				  jQuery("#otherTbody").empty(); */
			  }
		  })
	  }
  }
  
  function saveOtherDetail(){
	  var detailObj=jQuery(".detailTr");
	  var detailArrayObj=new Array();
	  var remark=null;
	  var xxnum=0;
	  var xnum=0;
	  for(var i=0;i<detailObj.length;i++){
		  var serialId=jQuery(detailObj[i]).find(".detail").val();
		  var amount=jQuery(detailObj[i]).find(".icon-xuanzhongzhuangtai");
		  var xx=jQuery(detailObj[i]).find(".icon-xx");
		  if(amount.hasClass("store-co1")){
			  state=4;
			  remark="";
		  }else{
			  xnum++;
			 continue;			
		  }
		  if (xx.css("visibility") == "hidden")   xxnum++;
		  var tdObj={"serialId":serialId,"state":state,"remark":remark};
		  detailArrayObj.push(tdObj);		  
  	  }
	  if(xxnum == detailObj.length){
		  dialog("已对账,无需更新!");
		  jQuery("#head").attr("hidden","hidden");
		  jQuery("#otherThead").empty();
		  jQuery("#otherTbody").empty();
		  return ;
	  }
	  if(xnum==detailObj.length){
		  dialog("更新成功!");
		  jQuery("#head").attr("hidden","hidden");
		  jQuery("#otherThead").empty();
		  jQuery("#otherTbody").empty();
		  return ;
	  }
	  var arrayObjStr = JSON.stringify(detailArrayObj);
	  jQuery.ajax({
		  url:baseUrl+"reconciliation/view/crossReconciliationupdate",
		  data:{"arrayObjStr":arrayObjStr},
		  type:"post",
		  success:function(e){
			  if(e.code!=0){
				  dialog("更新失败!");
			  }else{
				  dialog("更新成功!");
			  }
			  jQuery(".account-modal").modal("hide");
			  jQuery("#head").attr("hidden","hidden");
			  jQuery("#otherThead").empty();
			  jQuery("#otherTbody").empty();
		  }
	  })
	  jQuery("#month").val('${month}');
      jQuery("#summaryFrm").submit();
  }
  
  function parseOtherData(code,detailList){
	  jQuery("#otherThead").empty();
	  jQuery("#otherTbody").empty();
	  jQuery("#otherThead").append(
		"<tr>"+"<th>流水单号</th>"
		+"<th>顾客</th>"
        +"<th>消费时间</th>"
        +"<th>服务类型</th>"
        +"<th>服务内容</th>"
        +"<th>划卡/充值金额</th>"
        +"<th>对账/付款</th>"
        +"<th>审核状态</th>"
        +"</tr>"
	  );
	  var length=detailList.length;
	  if(length==0){
		  dialog("此选项没有消费数据!");
		  jQuery("#totalAmt").text("0");
		  return ;
	  }
	  var total=0;

	  for(var i=0;i<detailList.length;i++){
		  var detail=detailList[i];
		  var type="项目";
		  var value="<td  class='fill-value'><input type='text' name='hasValue' onKeyUp='checkNum(this)' value='"+detail.shopAccount.reconciliationAmount+"'/></td>";
		  if(detail.orderDetail.orderType==2){
			  type="商品";
		  }
		  var state="<td class='remark'>等待审核</td>";
		 /*  if(detail.shopAccount.checkState==2){
			  state="<td class='remark'>等待审核</td>";
		  } */
		  if(detail.shopAccount.checkState==3){
			  state="<td class='remark' data-toggle='tooltip' data-placement='top' title='"+detail.shopAccount.remark+"'>审核未通过</td>";
		  }
		  if(detail.shopAccount.checkState==4){
			  state="<td class='remark'>审核已通过</td>";
			  value="<td  class='fill-value'><input type='text' name='hasValue' disabled='disabled' value='"+detail.shopAccount.reconciliationAmount+"'/></td>"
		  }
		  total+=detail.orderDetail.realPrice;
		  jQuery("#otherTbody").append(
			"<tr class='detailTr'><input type='hidden' value='"+detail.orderDetail.detailId+"' class='detail'/> "+"<td>"+detail.orderDetail.seriesId+"</td>" +
			"<td>"+detail.orderDetail.memberName+"</td>"+
			"<td>"+detail.orderDetail.createTime+"</td>"+
			"<td>"+type+"</td>"+
			"<td>"+detail.orderDetail.projectName+"</td>"+
			"<td>"+detail.orderDetail.realPrice+"</td>"+
			value+			
			state
		  );
	  }
	  jQuery("#otherTbody").append("<tr class='store-tr'>"+
      	"<td colspan='9'> <button class='btn fr' onclick='saveDetail(1)'>确定</button></td>"+
  		"</tr>"
  		);
	  jQuery("#totalAmt").text(total);
      jQuery(".shuju .icon-xx").on("click",function() {
          jQuery(this).addClass('store-co2').siblings().removeClass("store-co1")
      })

      jQuery(".shuju .icon-xuanzhongzhuangtai").on("click",function() {
          jQuery(this).addClass('store-co1').siblings().removeClass("store-co2")
      });
      jQuery('[data-toggle="tooltip"]').tooltip();
      
  }
  
  function checkNum(obj) {  
	    //检查是否是非数字值  
	    if (isNaN(obj.value)) {  
	        obj.value = "";  
	    }   
	}
  
  function parseMeData(code,detailList){
	  jQuery("#otherThead").empty();
	  jQuery("#otherTbody").empty();
	  jQuery("#otherThead").append(
		"<tr>"+"<th>流水单号</th>"+
		"<th>顾客</th>"
        +"<th>消费时间</th>"
        +"<th>服务类型</th>"
        +"<th>服务内容</th>"
        +"<th>划卡/充值金额</th>"
        +"<th>对账金额</th>"
        +"<th>数据审核</th>"
        +"</tr>"
	  );
	  var length=detailList.length;
	  if(length==0){
		  dialog("此选项没有消费数据!");
		  jQuery("#totalAmt").text("0");
		  return ;
	  }
	  var total=0,rnum=0;
	  for(var i=0;i<detailList.length;i++){
		  var detail=detailList[i];
		  var type="项目";	
		  var state=null;
		  var reconciliationAmount=detail.shopAccount.reconciliationAmount;
		  if(detail.orderDetail.orderType==2){
			  type="商品";
		  }
		  if(code!=0){
			  if(detail.shopAccount.checkState==0){
				  reconciliationAmount='数据未填写';
				  rnum++;
			  }
			  if(detail.shopAccount.checkState==2){
				  state="<td class='shuju'><span style='' class='iconfont icon-xuanzhongzhuangtai'></span> <span style='' data-toggle='tooltip' onclick='disagree(" + detail.orderDetail.detailId + ")' class='iconfont icon-xx' data-target='#account-modal'></span></td>"
				  ; 
			  }
			  if(detail.shopAccount.checkState==3){
				  state="<td class='shuju'><span style='' class='iconfont icon-xuanzhongzhuangtai'></span> <span style='' data-toggle='tooltip' data-placement='top' title='"+detail.shopAccount.remark+"' onclick='disagree(" + detail.orderDetail.detailId + ")' class='iconfont icon-xx store-co2' data-target='#account-modal'></span></td>"
				  ;
			  }
			  if(detail.shopAccount.checkState==4){
				  state="<td class='shuju'><span style='' class='iconfont icon-xuanzhongzhuangtai store-co1'></span> <span style='' data-toggle='tooltip' onclick='disagree(" + detail.orderDetail.detailId + ")' class='iconfont icon-xx' data-target='#account-modal'></span></td>"
				  ;
			  }
		  }else{
			  state= "<td class='shuju'><span style='' class='iconfont icon-xuanzhongzhuangtai store-co1'></span> <span style='visibility:hidden' onclick='disagree(" + detail.orderDetail.detailId + ")' class='iconfont icon-xx' data-target='#account-modal'></span></td>"
				;
		  }
		  
		  total+=detail.orderDetail.realPrice;
		  jQuery("#otherTbody").append(
			"<tr class='detailTr'> <input type='hidden' value='"+detail.orderDetail.detailId+"' class='detail'/>"+"<td>"+detail.orderDetail.seriesId+"</td>" +
			"<td>"+detail.orderDetail.memberName+"</td>"+
			"<td>"+detail.orderDetail.createTime+"</td>"+
			"<td>"+type+"</td>"+
			"<td>"+detail.orderDetail.projectName+"</td>"+
			"<td>"+detail.orderDetail.realPrice+"</td>"+
			"<td class='ramount'>"+reconciliationAmount+"</td>"+			
			state+
			"<td class='remark'></td>"
		  );
	  }
	  if(rnum != detailList.length){
		  jQuery("#otherTbody").append("<tr class='store-tr'>"+
			      	"<td colspan='9'> <button class='btn fr' onclick='saveOtherDetail()'>确定</button></td>"+
			  		"</tr>"
			  		);
	  }
	  
      jQuery(".shuju .icon-xx").on("click",function() {
          jQuery(this).addClass('store-co2').siblings().removeClass("store-co1")
      })
	  jQuery("#totalAmt").text(total);
      jQuery(".shuju .icon-xuanzhongzhuangtai").on("click",function() {
          jQuery(this).addClass('store-co1').siblings().removeClass("store-co2")
      })
      jQuery('[data-toggle="tooltip"]').tooltip();
      
      
  }
  
/*   jQuery("#otherTbody").delegate("td","blur",function() {
	  jQuery("input[name='hasValue']").parent().next("td").text("等待审核");
  }) */
  
</script>

</div><!--mainwrapper-->
</body>
</html>     	
    <!--RIGHT PANEL结束 -->

    <div class="clearfix"></div>

    <div id="star"></div>
<form action="<%=basePath %>reconciliation/view/crossReconciliation" style="display:none;" id="summaryFrm" method="post">
<input type="hidden" name="month" id="month"/>
</form>
</body>
</html>