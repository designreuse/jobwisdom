<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath%>css/shop_vip_manage.css" type="text/css" />
<body>
<style>
.vip_style {
    color: black;
    font-size: 14px;
    font-weight: bold;
    padding: 10px;
}
.vip_style select {
    border: 1px solid black;
    border-radius: 8px;
    color: black;
    margin-left: 10px;
    width: 90px;
    padding-left: 40px;
    font-size: 12px;
}
.preview_vip_card {
    width: 280px;
    height: 180px;
    background: #65c294;
    border-radius: 8px;
    margin:  20% auto;
    box-shadow: 0 4px 10px #474a49;
    }
.preview_vip_card>p {
    padding-top: 40px;
    text-align: center;
    font-size: 24px;
    color: white;
}
.preview_vip_card>p em {
    display: inline-block;
    margin-right: 10px;
    font-size: 30px;
}
.item_card {
    color: white;
    margin: 20px 0 0 80px;
}
.rest_money {
    color: white;
    position: relative;
    left: 140px;
}


input[type='number']{
width:55px!important}

</style>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	<!-- 页面代码 -->
		<div class='content_right clearfix'>
		   <div class="out_roll_content">
		     <div class="out_roll">
			  <span class="click_left"><img src="<%=basePath%>images/left_click.png"></span>
		     <div class="out_roll_div">	  
			  <ul class="clearfix out_roll_ul">
			     <c:forEach items="${storeInfoList}" var="storeInfo" varStatus="status">
			        <li <c:if test="${status.index == 0}"> class="active" </c:if> storeId = "${storeInfo.storeId }" onclick="chooseStore(this, ${storeInfo.storeId })">${storeInfo.storeName }</li>
			     </c:forEach>
			   </ul>
			  </div> 
			   <span class="click_right"><img src="<%=basePath%>images/right_click.png"></span>
			 </div>
			</div>
			 <p class="vip_style">会员卡类型 
			      <span>
				      <select name = "levelType" onchange="changeType()">
					      <option value="1">等级卡</option>
					      <option value="2">折扣卡</option>
					      <option value="0">全部</option>
				      </select>
			      </span></p>
			<div class="new_data">
			  
		     <div class="vip_card_table"> 	  
			  <table>
			     <tr>
				   <td>会员卡名称</td>
				   <td>会员卡类型</td>
				   <td>项目折扣</td>
				   <td>商品折扣</td>
				   <td>最低充值</td>
				   <td>开卡费用</td>
				   <td>业绩折扣比例</td>
				   <td>积分计算方式</td>
				   <td>等级说明</td>
				   <td>操作</td>
				 </tr>
			      <c:forEach items="${page.results }" var="memberLevel" varStatus="index">
			          <tr id="${memberLevel.discountId }">
			             <td>${memberLevel.levelName }
			              <c:if test="${memberLevel.isDefault == 1 }"><span class="font-999">默认等级</span></c:if>
			             </td>
			             <td>${memberLevel.levelType }</td>
			             <td>${memberLevel.projectDiscount }%</td>
			             <td>${memberLevel.goodsDiscount }%</td>
			             <td>${memberLevel.chargeMinMoney }元</td>
			             <td>
			             		${memberLevel.sellAmount }元
			             </td>
			             <td>
			             		${memberLevel.performanceDiscountPercent }%
			             </td>
			             <td>${memberLevel.integralUnit }元 = ${memberLevel.integralNumber }积分</td>
			             <td class="input80">
			             		${memberLevel.levelNotice }
			             </td>
			             
			             <td> 
			             <c:if test="${memberLevel.levelType eq '等级卡' }">
			             <em onclick="editMemberLevel(${memberLevel.discountId})"><img src="<%=basePath %>images/handle_1.png"></em>
			           	</c:if>
			             <em onclick="showMemberLevel(${memberLevel.discountId})"><img src="<%=basePath %>images/shop_vip.png"></em>
						 </td>
			           </tr>
			          </c:forEach>
				
					  
			   </table>
			 </div>
			</div>
			<%@ include file="/template/page.jsp" %>
		  </div>
    </div>
</div>
</div><!--mainwrapper-->

<div class="zzc" style="display: none">
   
	<div class="preview">
		    <div class="preview_vip_card">
			  <p name="levelName"><em>VIP</em><i>会员卡名称</i></p>
			  <div class="item_card">
			      <p name="projectDiscount">项目折扣：<i>0</i>折</p>
				  <p name="goodsDiscount">商品折扣：<i>0</i>折</p>
			  </div>
			  <div class="rest_money">
			  卡上余额：88888元
			  </div>
			</div>
          </div>

</div>


<div class="zzc1" style="display: none">
   <div class="zzc1_adjust">
	  <p>修改折扣</p>
	  <div class="white_card">
	    <p id = "levelNameP"></p>
	    <div class="white_card_content">
		   <ul class="clearfix">
		      <li>项目折扣<input type="text" name="projectDiscount"><em style="position:Relative;left:-20px">%</em></li>
			 <li>商品折扣<input type="text" name="goodsDiscount"><em style="position:Relative;left:-20px">%</em></li>
			 <li>员工业绩折扣<input type="text" name="performanceDiscountPercent" style="margin-left:8px"></li>
			 <li>开卡费用<input type="text" name="sellAmount"></li>
			 <li>最低充值<input type="text" name="chargeMinMoney" style="position:relative;left:28px"></li>
			 <li>积分计算方式：
			                            每消费<input type="number" name="integralUnit" class="input30" value="1"><span class="percent-symbol">元</span>
                    <span class="ml10">获得</span>
                    <input type="number" name="integralNumber" class="input30" value="1"><span class="percent-symbol">分</span>
                </li>
		   </ul>
		</div>
		<input type="hidden" name="discountId">
	    <div class="zzc1_adjust_button">
		  <button onclick="saveEditMemberLevel()">确认</button>
		  <button onclick="cancelModal()">取消</button>
		</div>
	  </div>
	  
   </div>

</div>

<script>


	 
	 var showType = '${showType}';
	 var chooseStoreId = '${chooseStoreId}';
	//获取加载页面时的页码信息
     var pageNo = "${page.pageNo}";
     var pageSize = "${page.pageSize}";
     var totalPage = "${page.totalPage}"
 //轮播
 
 	jQuery(function(){
 		if (showType == '0') {
 			jQuery(".out_roll_content").remove();
 		}
 		
	     var now_=0, count=jQuery('.out_roll_ul li').size();
		 
	  //向右走
      jQuery('.click_right').click(function(){
         if(now_<=count-6){
		    now_+=1;
	        jQuery(this).parent('').find('.out_roll_ul').stop(true,true).animate({
		       left:-181*now_
		   
		       }) 
			  }
		  });
	  //向左走
	  
	 jQuery('.click_left').click(function(){
         if(now_>=1){
		    now_-=1;
	         jQuery(this).parent('').find('.out_roll_ul').stop(true,true).animate({
		     left:-181*now_
		   
		     }) 
		  }	
        		
	  });
 });

     

//显示隐藏‘停止’
jQuery(function(){
  jQuery('.new_data td em').click(function(){
    jQuery(this).parent().find('ul').stop(true,true).toggle('normal');
 
  });
})
</script>
<script type="text/javascript" src="<%=basePath %>js/member/memberLevel.js"></script>
</body>
</html>