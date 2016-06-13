<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>css/business_manage.css" type="text/css" />
<style>
.business_level_back_ul .active{
   display:block;
}
</style>
<head>
    <script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
    <script type="text/javascript">
    
    function editPage (imgUrl) {
    	xiuxiu.embedSWF("altContent2", 1, 700, 500);
    	
    	xiuxiu.onInit = function (id)
    	{
            xiuxiu.setUploadType(3);
            if (imgUrl != null) {
            	xiuxiu.loadPhoto(qiniuUrl + imgUrl, false);
            }
    	}
    	xiuxiu.onSaveBase64Image = function (data, fileName, fileType, id)
    	{
            zccCallback(data);
    	}
    	
    	xiuxiu.onDebug = function (data)
    	{
            dialog("网咯繁忙，请重试！");
    	}
    	
    	xiuxiu.onClose = function (id)
    	{
            //alert(id + "关闭");
            jQuery(".mask").hide();
    	}
    	
    	/* //清除flash
        function clearFlash()
        {
            document.getElementById("flashEditorOut").innerHTML='<div id="flashEditorContent"><p><a href="http://www.adobe.com/go/getflashplayer"><img alt="Get Adobe Flash player" src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"></a></p></div>';
        } */
    }
	
    var imgKey = "";
    function zccCallback(dataBase64){
    	var key = "jobwisdom/project/" + new Date().getTime();
        var data = {"stringBase64":dataBase64,"key":key};
        jQuery('.cancelinput').click();
        jQuery.ajax({
    		type: "POST",
    		url: baseUrl+"qiniu/base64",
    	       data: JSON.stringify(data),
    	       contentType: "application/json",
    	       dataType: "json",
    	       async:true,  
    	       beforeSend:function(){
    	       	console.log("beforeSend upload image");
    	       },
    	       error:function (){
    	       	console.log("upload image error");
    	       },
    	       complete :function (){
    	       	console.log("complete upload image");
    	       },
    	       success: function(data) {
    		       	var imageUrl = data.msg.imageUrl;
    		       	var key = data.msg.key;
    		       	if (chooseType == 1) {
    		       		updatePositivePageUrl = key;
    		       	}
    		       	else {
    		       		updateOppositePageUrl = key;
    		       	}
    		       	valueChooseMemberPage(updatePositivePageUrl, updateOppositePageUrl);
    		       	jQuery(".mask").hide();
    	       }
     	});
    }
    </script>
</head>
<body>

<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	<!-- 页面代码 -->
		<div class='content_right clearfix'>
    <button class="new_vip" onclick="showAddMemberLevel()">新建会员卡</button>
	<table class="business_manage_table">
	   <tr>
	     <td id = "lite">会员卡类别</td>
		 <td>会员卡名称</td>
		 <td>项目折扣</td>
		 <td>商品折扣</td>
	     <td>最低充值</td>
		 <td>开卡充值</td>
		 <td>现金支付打折</td>
		 <td>业绩折扣比例</td>
		 <td>积分计算方式</td>
		 <td>等级说明</td>
		 <td>操作</td>
	   </tr>
	   
	   <c:forEach items="${page.results }" var="memberLevel" varStatus="index">
          <tr id="${memberLevel.levelId }">
             <td>${memberLevel.levelType }</td>
             <td>${memberLevel.levelName }
              <c:if test="${memberLevel.isDefault == 1 }"><span class="font-999">默认等级</span></c:if>
             </td>
             <td>${memberLevel.projectDiscount }%</td>
             <td>${memberLevel.goodsDiscount }%</td>
             <td>${memberLevel.chargeMinMoney }元</td>
             <td>
             		${memberLevel.sellAmount }元
             </td>
             <td>
             		<c:choose>
             			<c:when test="${memberLevel.cashDiscountType == 0 }">
             				不打折
             			</c:when>
             			<c:otherwise>
             				打折
             			</c:otherwise>
             		</c:choose>
             </td>
             <td>
             		${memberLevel.performanceDiscountPercent }%
             </td>
             <td>${memberLevel.integralUnit }元 = ${memberLevel.integralNumber }积分</td>
             <td class="input80 ellipsis-text">
             		${memberLevel.levelNotice }
             </td>
             
             <td> <span><img src="<%=basePath %>images/vip_manage.png"> </span>
			  <div class="manage_hover">
			    <ul class="demo_fade"><li style="border-bottom:1px solid black" onclick="editMemberLevel(${memberLevel.levelId})"><img src="<%=basePath %>images/handle_1.png"></li><li onclick="deleteMemberLevel(${memberLevel.levelId})"><img src="<%=basePath %>images/handle_2.png"></li></ul>
			  </div>
			 </td>
           </tr>
          </c:forEach>
	
	
	
	</table>
	<%@ include file="/template/page.jsp" %>
  </div>
    </div>
</div>
</div><!--mainwrapper-->

<div class="zzc" style="display: none;" id = "memberLevelModal">
   <div class="business_level">
      <p>新建等级卡</p>     
	  <div class="business_level_content">
	   <div class="business_level_content_left">
	    <div class="business_level_input clearfix">
		  <div class="business_level_input_left">
		     <span>会员卡种类
			     <select name = "levelType">
			        <option value="折扣卡">折扣卡</option>
			        <option value="等级卡">等级卡</option>
			     </select>
			     <em>?</em>
		     </span>
			 <span>会员卡名称<input type="text" name="levelName" ></span>
		  </div> 
		  <div class="business_level_input_right">
		     <span><i>使用须知</i><textarea name="levelNotice"></textarea></span>
		  </div>
		</div>
		
		<div class="business_level_back">
		  <p>选择卡背</p>
		   <ul class="business_level_back_ul clearfix">
		     <li onclick="chooseMemberPage(1, 'system/profile/vip_card.png', 'system/profile/vip_card1.png')"><img src="<%=qiniuPath %>system/profile/vip_card.png">
			    <span class = "active"><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li onclick="chooseMemberPage(2, 'system/profile/vip_card_11.png', 'system/profile/vip_card_12.png')"><img src="<%=qiniuPath %>system/profile/vip_card_11.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li onclick="chooseMemberPage(3, 'system/profile/vip_card_21.png', 'system/profile/vip_card_22.png')"><img src="<%=qiniuPath %>system/profile/vip_card_21.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li onclick="chooseMemberPage(4, 'system/profile/vip_card_31.png', 'system/profile/vip_card_32.png')"><img src="<%=qiniuPath %>system/profile/vip_card_31.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li onclick="chooseMemberPage(5, 'system/profile/vip_card.png', 'system/profile/vip_card1.png')"><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <%-- <li onclick="chooseMemberPage(6, 'system/profile/vip_card.png', 'system/profile/vip_card1.png')"><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li onclick="chooseMemberPage(7, 'system/profile/vip_card.png', 'system/profile/vip_card1.png')"><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li onclick="chooseMemberPage(8, 'system/profile/vip_card.png', 'system/profile/vip_card1.png')"><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li onclick="chooseMemberPage(9, 'system/profile/vip_card.png', 'system/profile/vip_card1.png')"><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li onclick="chooseMemberPage(10, 'system/profile/vip_card.png', 'system/profile/vip_card1.png')"><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li> --%>
           </ul>
		   <div class="business_level_back_text">
		      <ul class="business_level_back_text_ul clearfix">
		         <li>项目折扣<input type="text" name="projectDiscount"></li>
				 <li>现金支付打折
				     <select name="cashDiscountType" class="chzn-select wthn100">
                        <option value="0">不打折</option>
                        <option value="1">打折</option>
                   	 </select>
                 </li>
				 <li>商品折扣<input type="text" name="goodsDiscount"></li>
				 <li>业绩折扣打折<input type="text" name="performanceDiscountPercent"></li>
				 <li>开卡费用<input type="text" name="sellAmount"></li>
				 <li>最低充值<input type="text" name="chargeMinMoney" style="position:relative;left:28px"></li>
				 <li style="width:600px"><span class="mr10 label12 font-bold">积分计算方式：</span>
				                            每消费<input type="number" name="integralUnit" class="input30" value="1"><span class="percent-symbol">元</span>
                     <span class="ml10">获得</span>
                     <input type="number" name="integralNumber" class="input30" value="1"><span class="percent-symbol">分</span>
                 </li>
			  </ul>
		   </div>
		</div>
		<input type="hidden" name="levelId" style="width: 0px; height: 0px;" >
		<input type="hidden" name="discountId" style="width: 0px; height: 0px;" >
		<div class="business_level_button">
		   <button onclick="addOrEditMemberLevel()">确认</button>
		   <button onclick="cancelModal()">取消</button>
		</div>
		
		</div>
	
		<div class="business_level_content_right">
		  <p>效果图预览</p>
		  <div class="preview">
		     <div class="preview_1" name = "pagePreview" style="background:url('<%=basePath %>images/vip_card.png') no-repeat;">
			    <div class="preview_left" name = "pagePreviewLeft">
				   <span >5折卡</span>
            	</div>
				<div class="preview_right" name = "pagePreviewRight">
                   <ul class="clearfix">
					  <li>项目折扣：5折</li>
					  <li>项目折扣：6折</li>
                   </ul>
				   <span>卡上余额：88888元</span>
	            </div>
         	 </div>  
			 <div class="right_dir">
			   <span onclick="showMask(1)"><img src="<%=basePath %>images/face.png"></span>
			   <span onclick="showMask(2)"><img src="<%=basePath %>images/back_1.png"></span>
			 </div>
			 <div class="preview_2 clearfix" style="background:url('<%=basePath %>images/vip_card1.png') no-repeat;">
			   <div class="preview_2_top"></div>
			   <div class="preview_2_content clearfix">
			     <div class="logo_img"><img src="<%=basePath %>images/vip_img.png"></div>
                 <div class="preview_2_content_right">
                    黄金八折卡黄金八折卡黄金八折卡黄金八折</br>黄金八折卡黄金八折卡黄金八</br>黄金八折卡黄金八折卡黄金八折卡</br>黄金八折卡黄金八折卡黄金八折卡黄金八折
                 </div> 			     
			   </div>
         	 </div> 
			 
		  </div>
		</div>
	  </div>
	  
   </div>   
</div>

<!-- 美图秀秀 -->
<div class="mask" style="display: none;">
   <div id="flashEditorOut" >
	        <div id="altContent2">
	            <h1>美图秀秀2</h1>
	        </div>
		</div>
</div>

<script type="text/javascript" src="<%=basePath %>js/member/enterpriseMemberLevel.js"></script>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script>
jQuery(document).ready(function() {    
    jQuery('.business_manage_table td span').click(function(){
    find=jQuery(this).parent().find('.demo_fade');
  if(find.css('display')=='none'){
     find.stop(true,true).slideDown('normal');
  }    
  else if(find.css('display')=='block'){
     jQuery('.demo_fade').stop(true,true).slideUp('normal');
   }	
 
 })
});

	//VIP卡选择
	jQuery(function(){
		jQuery('.business_level_back_ul li').click(function(){
		  jQuery(this).find('span').addClass("active");
		  jQuery(this).siblings().find('span').removeClass("active");	
		})
	})
  
  </script>

</body>
</html>