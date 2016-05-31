<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>css/business_manage.css" type="text/css" />
<<style>
.business_level_back_ul .active{
   display:block;
}
</style>
<head>
    <script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
    <script type="text/javascript">
    xiuxiu.embedSWF("altContent2", 1, 700, 500);
	
	xiuxiu.onInit = function (id)
	{
        xiuxiu.setUploadType(3);
        /* xiuxiu.loadPhoto("http://7xuvif.com1.z0.glb.clouddn.com/vip_card.png", false); */
	}
	xiuxiu.onSaveBase64Image = function (data, fileName, fileType, id)
	{
        alert("保存为base64图片,大小:" + data.length + ",文件名:" + fileName + ",类型:" + fileType);
        zccCallback(data);
	}
	
	xiuxiu.onDebug = function (data)
	{
        alert("错误响应" + data);
	}
	
	xiuxiu.onClose = function (id)
	{
        //alert(id + "关闭");
        clearFlash();
        jQuery(".flashEditorOut").addClass("hide");
	}
	
	//清除flash
    function clearFlash()
    {
        document.getElementById("flashEditorOut").innerHTML='<div id="flashEditorContent"><p><a href="http://www.adobe.com/go/getflashplayer"><img alt="Get Adobe Flash player" src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif"></a></p></div>';
    }
	
    var imgKey = "";
    function zccCallback(dataBase64){
    	imgObject.children("img").attr("src", dataBase64);
    	var key = "jobwisdom/project/" + new Date().getTime();
    	if ((typeof(imgObject.children("img").attr("projectImage")))!="undefined"){
    		var url = qiniuUrl+key;
    		imgKey = key;
    		imgObject.children("img").attr("projectImage", url);
    	}else {
    		var url = qiniuUrl+key;
    		imgKey = key;
    		imgObject.children("img").attr("affiliatedImage", url);
    	}
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
    		       	console.log(imageUrl);
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
	   <tr>
	     <td>折扣卡</td>
		 <td>三折卡</td>
		 <td>10折</td>
		 <td>15折</td>
	     <td>5000元</td>
		 <td>2000元</td>
		 <td>不打折</td>
		 <td>100%</td>
		 <td>10元=1积分</td>
		 <td>啊啊啊</td>
		 <td> <span><img src="<%=basePath %>images/vip_manage.png"> </span>
		  <div class="manage_hover">
		    <ul class="demo_fade"><li style="border-bottom:1px solid black"><img src="<%=basePath %>images/handle_2.png"></li><li><img src="<%=basePath %>images/handle_1.png"></li></ul>
		  </div>
		   
		 </td>
	   </tr>
	    <tr>
	     <td>折扣卡</td>
		 <td>三折卡</td>
		 <td>10折</td>
		 <td>15折</td>
	     <td>5000元</td>
		 <td>2000元</td>
		 <td>不打折</td>
		 <td>100%</td>
		 <td>10元=1积分</td>
		 <td>啊啊啊</td>
		 <td> <span><img src="<%=basePath %>images/vip_manage.png"> </span>
		  <div class="manage_hover">
		    <ul class="demo_fade"><li style="border-bottom:1px solid black"><img src="<%=basePath %>images/handle_2.png"></li><li><img src="<%=basePath %>images/handle_1.png"></li></ul>
		  </div>
		   
		 </td>
	   </tr>
	
	
	
	</table>
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
			     <select name = "levleType" onchange="changeType(this)">
			        <option value="折扣卡">折扣卡</option>
			        <option value="等级卡">等级卡</option>
			     </select>
			     <em>?</em>
		     </span>
			 <span>会员卡名称<input type="text" name="levelName" ></span>
		  </div> 
		  <div class="business_level_input_right">
		     <span><i>使用须知</i><textarea id="levelNotice"></textarea></span>
		  </div>
		</div>
		<div id="flashEditorOut">
	        <div id="altContent2">
	            <h1>美图秀秀2</h1>
	        </div>
		</div>
		<div class="business_level_back">
		  <p>选择卡背</p>
		   <ul class="business_level_back_ul clearfix">
		     <li><img src="http://7xuvif.com1.z0.glb.clouddn.com/vip_card.png"  value = "1">
			    <span class = "active"><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
			 <li><img src="<%=basePath %>images/vip_card.png">
			    <span><img src="<%=basePath %>images/checked.png"></span>
			 </li>
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
				 <li>最低充值<input type="text" name="chargeMinMoney"></li>
				 <li><span class="mr10 label12 font-bold">积分计算方式：</span>
				                            每消费<input type="number" name="integralUnit" class="input30" value="1"><span class="percent-symbol">元</span>
                     <span class="ml10">获得</span>
                     <input type="number" name="integralNumber" class="input30" value="1"><span class="percent-symbol">分</span>
                 </li>
			  </ul>
		   </div>
		</div>
		<input type="hidden" name="levelId" style="width: 0px; height: 0px;" >
		<div class="business_level_button">
		   <button>预览</button>
		   <button onclick="addOrEditMemberLevel()">确认</button>
		  <button onclick="cancelModal()">取消</button>
		</div>
		
		</div>
	
		<div class="business_level_content_right">
		  <p>效果图预览</p>
		  <div class="preview">
		     <div class="preview_1 clearfix" style="background:url('<%=basePath %>images/vip_card.png') no-repeat;">
			    <div class="preview_left">
				   <span>5折卡</span>
            	</div>
				<div class="preview_right">
                   <ul>
					  <li>项目折扣：5折</li>
					  <li>项目折扣：6折</li>
                   </ul>
				   <span>卡上余额：12354651元<em>充值</em></span>
	            </div>
         	 </div>  
			 
			 <div class="preview_2 clearfix" style="background:url('<%=basePath %>images/vip_card1.png') no-repeat;">
			   <div class="preview_2_top"><span>5折卡</span></div>
			   <div class="preview_2_content clearfix">
			     <div class="logo_img"><img src="<%=basePath %>images/vip_img.png"></div>
                 <div class="preview_2_content_right">
                    <p>最低充值：5000元</p> 
					<p>开卡费用：100元</p> 
					<p>现金支付打折：不打折</p>
					<p>业绩折扣比例：100%</p>
					<p>积分计算方式：1元＝2积分</p>
                 </div> 			     
			   </div>
         	 </div> 
			 
		  </div>
		</div>
	  </div>
	  
   </div>   
</div>
<script type="text/javascript" src="<%=basePath %>js/member/enterpriseMemberLevel.js"></script>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script>
   

	//VIP卡选择
	jQuery(function(){
		jQuery('.business_level_back_ul li').click(function(){
		  jQuery(this).find('span').show();
		  jQuery(this).siblings().find('span').hide();	
		})
	})
  
	jQuery()
	
  </script>

</body>
</html>