<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet" href="<%=basePath %>css/business_manage.css" type="text/css" />
<style>
.business_level_back_ul .active{
   display:block;
}
.business_manage_table_content{border:1px solid #dbdce2;overflow:hidden;border-radius:12px;width:1020px}

.preview_vip_card{
 width:280px;height:180px;
 background:#65c294;
 border-radius:8px;
 margin:0 auto;
 box-shadow:0 4px 10px #474a49
}
.preview_vip_card>p{padding-top:40px;text-align:center;font-size:24px;color:white}
.preview_vip_card>p em{display:inline-block;margin-right:10px;font-size:30px}
.item_card {color:white;margin:20px 0 0 80px}
.item_card >p{height:34px}
.rest_money{color:white;position:relative;left:140px}
.preview_vip_card{margin-bottom:20px}
.fly_{position:absolute;text-align:left;width:300px;border-radius:8px;background:#fdfde0;box-shadow:0 0 10px #ccc;z-index:2;top:170px}
.fly_ span{padding:0 2px!important;display:inline-block;width:160px;text-align:center;line-height:30px;height:30px;}
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
	   jQuery(this).find('span').show();
	   jQuery(this).siblings().find('span').hide();	
	})
  
  })
  
  
  jQuery(function(){
    jQuery('.business_level_content_right li').click(function(){
	   var bac=jQuery(this).find('span').css('background-color');
	   jQuery(this).css('border-color',bac).siblings().css('border-color','white');
	   jQuery('.preview_vip_card').css('background',bac)
	
	});
  })
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
   <div class="business_manage_table_content">
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
             <td class="input80">
             		${memberLevel.levelNotice }
             </td>
            
			 <td>
<%-- 				 <c:if test="${ memberLevel.levelName eq '默认会员卡'}"> --%>
					 <em onclick="editMemberLevel(${memberLevel.levelId})"><img src="<%=basePath %>images/handle_1.png"></em>
					 <em onclick="deleteMemberLevel(${memberLevel.levelId})"><img src="<%=basePath %>images/handle_2.png"></em>
<%-- 				 </c:if> --%>
			 </td>
           </tr>
          </c:forEach>
	
	
	
	</table>
  </div>	
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
			     <select name = "levelType"  onchange="changeType()">
			        <option value="折扣卡">折扣卡</option>
			        <option value="等级卡">等级卡</option>
			     </select>
			     <em class="overflow_text">?</em>
			     <div class="fly_"></div>
			  
			     
		     </span>
			 <span>会员卡名称<input type="text" name="levelName" ><i  class = "addcolor">*</i></span>
		  </div> 
		  <div class="business_level_input_right">
		     <span><i>使用须知</i><textarea id ="textarea" name="levelNotice"></textarea></span>
		  </div>
		
		</div>
		
		<div class="business_level_back">
		   <div class="business_level_back_text">
		      <ul class="business_level_back_text_ul clearfix">
		         <li>项目折扣<input type="text" name="projectDiscount"><i  class = "addcolor">*</i><em style="position:relative;left:-20px">%</em></em></li>
				 <li>现金支付打折
				     <select name="cashDiscountType" >
                        <option value="0">不打折</option>
                        <option value="1">打折</option>
                   	 </select>
                 </li>
				 <li>商品折扣<input type="text" name="goodsDiscount"><i  class = "addcolor">*</i><em style="position:relative;left:-20px">%</em></li>
				 <li>员工业绩折扣<input type="text" name="performanceDiscountPercent"><em style="position:relative;left:-20px">%</em></li>
				 <li>开卡费用<input type="text" name="sellAmount" ><span style="position:relative;left:-20px">元</span></li>
				 <li>最低充值<input type="text" name="chargeMinMoney" style="position:relative;left:22px"><span style="position:relative">元</span></li>
				 <li style="width:600px;"><span class="mr10 label12 font-bold">积分计算方式：</span>
				                            每消费<input type="number" name="integralUnit" class="input30" value="1"><span class="percent-symbol">元</span>
                     <span class="ml10">获得</span>
                     <input type="number" name="integralNumber" class="input30" value="1" ><span class="percent-symbol">分</span>
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
		  <p></p>
		  <div class="preview">
		    <div class="preview_vip_card">
			  <p name ="levelName"><em>VIP</em><i>会员卡名称</i></p>
			  <div class="item_card">
			      <p name="projectDiscount">项目折扣：<i>0</i>折</p>
				  <p name="goodsDiscount">商品折扣：<i>0</i>折</p>
			  </div>
			  <div class="rest_money">
			  卡上余额：88888元
			  </div>
			</div>
          </div>
	      <ul class="clearfix" name="colour">
		    <li ><span onclick="changetype(this)" value="d93717" style="background:#d93717"></span></li>
		    <li ><span onclick="changetype(this)" value="f8b65b" style="background:#f8b65b"></span></li>
			<li ><span onclick="changetype(this)" value="585eaa" style="background:#585eaa"></span></li>
			<li ><span onclick="changetype(this)" value="5c7a29" style="background:#5c7a29"></span></li>
			<li ><span onclick="changetype(this)" value="5f3c23" style="background:#5f3c23"></span></li>
			<li ><span onclick="changetype(this)"  value="2b4490" style="background:#2b4490"></span></li>
			<li ><span onclick="changetype(this)" value="b36d41" style="background:#b36d41"></span></li>
			<li ><span onclick="changetype(this)" value="a7573b" style="background:#a7573b"></span></li>
			<li ><span onclick="changetype(this)" value="f05b72" style="background:#f05b72"></span></li>
			<li ><span onclick="changetype(this)" value="817936" style="background:#817936"></span></li>
			<li ><span onclick="changetype(this)" value="2e3a1f" style="background:#2e3a1f"></span></li>
			<li ><span onclick="changetype(this)" value="46485f" style="background:#46485f"></span></li>
			<li ><span onclick="changetype(this)" value="543044" style="background:#543044"></span></li>
			<li ><span onclick="changetype(this)" value="401c44" style="background:#401c44"></span></li>
			<li ><span onclick="changetype(this)" value="225a1f" style="background:#225a1f"></span></li>
			<li ><span onclick="changetype(this)" value="b69968" style="background:#b69968"></span></li>
			<li ><span onclick="changetype(this)" value="6d5826" style="background:#6d5826"></span></li>
			<li ><span onclick="changetype(this)" value="d3c6a6" style="background:#d3c6a6"></span></li>
			<li ><span onclick="changetype(this)" value="c1a173" style="background:#c1a173"></span></li>
			<li ><span onclick="changetype(this)" value="5e7c85" style="background:#5e7c85"></span></li>
			<li ><span onclick="changetype(this)" value="7c8577" style="background:#7c8577"></span></li>
			<li ><span onclick="changetype(this)" value="6c4c49" style="background:#6c4c49"></span></li>
			<li ><span onclick="changetype(this)" value="3e4145" style="background:#3e4145"></span></li>
			<li ><span onclick="changetype(this)" value="281f1d" style="background:#281f1d"></span></li>
			<li ><span onclick="changetype(this)" value="596032" style="background:#596032"></span></li>
			<li ><span onclick="changetype(this)" value="145b7d" style="background:#145b7d"></span></li>
			<li ><span onclick="changetype(this)" value="0c212b" style="background:#0c212b"></span></li>
			<li ><span onclick="changetype(this)" value="508a88" style="background:#508a88"></span></li>
			<li ><span onclick="changetype(this)" value="fcaf17" style="background:#fcaf17"></span></li>
			<li ><span onclick="changetype(this)" value="8552a1" style="background:#8552a1"></span></li>
			<li ><span onclick="changetype(this)" value="df9464" style="background:#df9464"></span></li>
			<li ><span onclick="changetype(this)" value="65c294" style="background:#65c294"></span></li>
          </ul>		  
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

	//提示气泡
	var htm = "";
	jQuery(function() {
		changeType();
	})
//分页处理
function changePage(){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "/memberLevel/view/enterpriseMemberLevelList",
		data : "pageNo=" + pageNo,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			var memberLevels = e.msg.results;
			jQuery(".business_manage_table tr:gt(0)").remove();
			for (var i = 0; i < memberLevels.length; i++) {
				var memberLevel = memberLevels[i];
			
			html =' <tr id="'+memberLevel.levelId +'"><td>'+memberLevel.levelType+'</td> <td>'+memberLevel.levelName;
             if(memberLevel.isDefault == 1 ){
            	 html += '<span class="font-999">默认等级</span>';
             }
             html += '</td><td>'+memberLevel.projectDiscount+'%</td><td>'+memberLevel.goodsDiscount +'%</td><td>'+memberLevel.chargeMinMoney +'元</td><td>'+memberLevel.sellAmount +'元 </td>';
           	 if(memberLevel.cashDiscountType == 0){
           		 html += '<td>不打折';
           	 }else{
           		html += '<td>打折';
           	 }
           	 html += '</td><td>'+memberLevel.performanceDiscountPercent +'% </td><td>'+memberLevel.integralUnit +'元 = '+memberLevel.integralNumber +'积分</td>';
           	 html += '<td class="input80">'+memberLevel.levelNotice +'  </td> <td>';
	         if(memberLevel.levelName != '默认会员卡'){
	        	 html += '<em onclick="editMemberLevel('+memberLevel.levelId+')"><img src="<%=basePath %>images/handle_1.png"></em>';
	        	 html += ' <em onclick="deleteMemberLevel('+memberLevel.levelId+')"><img src="<%=basePath %>images/handle_2.png"></em>';
	         }    
	         html +='</td></tr>'  ;
	         jQuery(".business_manage_table").append(html);
			}
	         
	      
		}
	});
}
	
	
	
	
	
function changeType(){
		levelType2 =jQuery("select[name='levelType']").val();
		if(levelType2=="等级卡"){
			htm='该种卡 ，折扣均由总店设置，分店无修改权限';
		}
		if(levelType2=="折扣卡"){
			htm='该种卡 可由分店根据等级 ，设置折扣数';
			
		}
		jQuery("body").delegate(".overflow_text", "mouseover", function (){
			jQuery('.fly_').html('');
			
			jQuery(this).parent().find('.fly_').append(htm);
		});
		jQuery("body").delegate(".overflow_text", "mouseout", function (){
			jQuery('.fly_').html('')
		});
	}
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
	
	jQuery("input[name='levelName']").keyup(function(){
		jQuery("p[name='levelName']").find("i").text(jQuery("input[name='levelName']").val());
	})
  
	jQuery("input[name='projectDiscount']").keyup(function(){
		jQuery("p[name='projectDiscount']").find("i").text(jQuery("input[name='projectDiscount']").val()/10);
	})
	jQuery("input[name='goodsDiscount']").keyup(function(){
		jQuery("p[name='goodsDiscount']").find("i").text(jQuery("input[name='goodsDiscount']").val()/10);
	})
  
function deleteMemberLevel(levelId){
		  if(confirm("您确定要删除吗？")){
			jQuery.ajax({
				type : "post",
				url : baseUrl + "memberLevel/action/delete",
				data : "levelId=" + levelId,
				dataType : "json",
				success : function(e){
					if(e.code == -1){
						dialog(e.msg);
						return;
					}
					dialog("删除成功");
					jQuery("tr[id='"+levelId+"']").remove();
				}
			});
		  }
	}
  </script>

</body>
</html>