<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/head.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>

<style type="text/css">
    .ui-tabs-panel {
        margin: 15px;
        padding: 0;
        border: 1px solid #d9e9e9;
    }
</style>

</head>

<body>
	<div class="mainwrapper">
	
		<!--loading start-->
		<%@ include file="/loading.jsp" %>
		<!--loading end-->
		<%@ include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px;">
			<%@ include file="/top.jsp"%>

			<!-- 页面内容开始 -->
            <div class="maincontent" style="overflow-y: auto">
			    <div class="contentinner">
			        <div class="tuwen fl">
			            <h2 class="send-title">图文预览</h2>
			                 <div class="tuwen-wrap">
			                     <ul class="tuwen-wrap">
			                     <c:forEach items="${items }" var="item">
			                     	 <li>
			                             <img style="height:140px" src="${item.qiniuImg }" alt=""/>
			                             <p>${item.title }
			                             </p>
			                         </li>
			                     </c:forEach>
			                     </ul>
			                 </div>
			        </div>
			        <div class="send-oj fr">
			            <h2 class="send-title"> 选择发送对象</h2>
			            <div class="send-wrap">
			                <div class="menber-ka">
			                    <div class="send-oj-t">
			                       	 选择会员卡等级
			                    </div>
			                    <ul class="select-m one">
			                        <li style="border: none">
			                            <div class="add" data-target="#level-madal" data-toggle="modal">
			                                <span class="iconfont icon-jiahao"></span>
			                             </div>
			                        </li>
			                    </ul>
			                </div>
			                <div class="menber-gloup">
			                    <div class="send-oj-t">
			                       	 选择会员分组
			                    </div>
			                    <ul class="select-m two">
			                        <li style="border: none">
			                            <div class="add" data-target="#group-madal" data-toggle="modal">
			                                <span class="iconfont icon-jiahao"></span>
			                             </div>
			                        </li>
			                    </ul>
			                </div>
			            </div>
			            <div class="send-bottom">
			                <a  class="fr btn"  data-target="#weixin-yulan-modal" data-toggle="modal">手机预览</a>
			                <a  class="fr btn mr10" onclick="showSend()">确认发送</a>
			            </div>
			        </div>
			
			    </div>
			</div>
			            
  
		</div>
		<div class="clearfix"></div>
		<div id="star"></div>
	</div>

<!-- 会员卡等级 -->	
<div class="modal  hide" id="level-madal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-send">
            <div class="modal-header">
                <button type="button" class="close"  onclick="closeChoseLevel()"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">选择会员卡等级</h4>
            </div>
            <div class="modal-body">
                <div class="send-check" id="levels">
                    <c:forEach items="${level }" varStatus="status" step="2">
                	<div class="send-check1">
                        <div><input type="checkbox" value="${level[status.index].levelId }"/>${level[status.index].levelName }</div>
                        <div><input type="checkbox" value="${level[status.index+1].levelId }"/>${level[status.index+1].levelName }</div>
                    </div>
                </c:forEach>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" onclick="selectSends('levels',this)">确定</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->

<!-- 会员分组 -->
<div class="modal  hide" id="group-madal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-send">
            <div class="modal-header">
                <button type="button" class="close" onclick="closeChoseGroup()"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">选择会员分组</h4>
            </div>
            <div class="modal-body">
                <div class="send-check" id="groups">
                <c:forEach items="${screen }" varStatus="status" step="2">
                	<div class="send-check1">
                        <div><input type="checkbox" value="${screen[status.index].screeningId }"/>${screen[status.index].screeningName }</div>
                        <div><input type="checkbox" value="${screen[status.index+1].screeningId }"/>${screen[status.index+1].screeningName }</div>
                    </div>
                </c:forEach>
                </div>
            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" onclick="selectSends('groups',this)">确定</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div><!--modal-->

<!--微信预览模态框-->
<div class="modal hide wexin-yulan" id="weixin-yulan-modal"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content weixin-yulan-modal">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h5 class="modal-title" id="myModalLabel">微信预览</h5>
            </div>
            <div class="modal-body">
                <ul class="yulan" style="padding:0">
                    <li><span>员工内部账号 :</span><input type="text" id="userName" style="width: 162px"/></li>
                </ul>
            </div><!--modal body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" onclick="prev()" id="prev">确定</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>

<script>
    var mediaId = '${mediaId}';
    var fatherMediaId = '${fatherMediaId}';
    
    jQuery("#xiayibu1").on("click",function(){
        mediaId = jQuery("input:radio[name='selectTitle']:checked").val();
        fatherMediaId = jQuery("input:radio[name='selectTitle']:checked").parent().children("input[name='fatherMediaId']").val();
        if(typeof(mediaId)=="undefined"){
        	dialog("请选择图文消息");
        	return ;
        }
        console.log(mediaId);
        console.log(fatherMediaId);
        jQuery("#tabs-1").hide();
        jQuery("#tabs-2").show();
    });
    /** 选择要发送的人群*/
    function selectSends(id, obj){
	    jQuery(obj).parents(".modal").modal('hide');
	    if(id=="levels"){
	    	jQuery(".select-m.one").empty();
	    	var str = '<li style="border: none">'+
	            '<div class="add" data-target="#level-madal" data-toggle="modal">'+
	                '<span class="iconfont icon-jiahao"></span>'+
	             '</div>'+
	        '</li>';
	        jQuery(".select-m.one").append(jQuery(str));
	        jQuery("#"+id).find("input:checked").each(function (){
	            var str = '<li>'+jQuery(this).parent().text()+' <span class="iconfont icon-guanbi" data="'+jQuery(this).val()+'"></span></li>';
	            jQuery(".select-m.one li:last").before(jQuery(str));
	            console.log(jQuery(this).parent().text()+"....."+jQuery(this).val());
	        });
	    }else {
	    	jQuery(".select-m.two").empty();
	    	var str = '<li style="border: none">'+
	            '<div class="add" data-target="#group-madal" data-toggle="modal">'+
	                '<span class="iconfont icon-jiahao"></span>'+
	             '</div>'+
	        '</li>';
	        jQuery(".select-m.two").append(jQuery(str));
	        jQuery("#"+id).find("input:checked").each(function (){
	            var str = '<li>'+jQuery(this).parent().text()+' <span class="iconfont icon-guanbi" data="'+jQuery(this).val()+'"></span></li>';
	            jQuery(".select-m.two li:last").before(jQuery(str));
	            console.log(jQuery(this).parent().text()+"....."+jQuery(this).val());
	        });
	    }
	}
    jQuery(".select-m.one").delegate(".iconfont.icon-guanbi","click",function(){
    	jQuery(this).parents("li").hide('800');
    	jQuery("#levels").find('input[value='+jQuery(this).attr("data")+']:checked').prop("checked",false);
    });
    jQuery(".select-m.two").delegate(".iconfont.icon-guanbi","click",function(){
    	jQuery(this).parents("li").hide('800');
    	jQuery("#groups").find('input[value='+jQuery(this).attr("data")+']:checked').prop("checked",false);
    });
    /** 确定发送*/
    function showSend(){
    	var ci = jQuery("#levels input:checked");
        var level = "";
        for(i=0;i<ci.length;i++){
            if(i == ci.length-1 ){
            	level = level +jQuery(ci[i]).val();
            }else{
            	level = level +jQuery(ci[i]).val()+ ",";
            }
        }
        var ai = jQuery("#groups input:checked");
        var sceenNum = "";
        for(i=0;i<ai.length;i++){
            if(i == ai.length-1 ){
            	sceenNum = sceenNum +jQuery(ai[i]).val();
            }else{
            	sceenNum = sceenNum +jQuery(ai[i]).val()+ ",";
            }
        }
        if(level == ""&&sceenNum == ""){
        	dialog("请至少选择一个分组进行发送");
        	return;
        }
		jQuery.ajax({
			type : "post",
			url : baseUrl + "send/item/openId",
			data : "mediaId="+mediaId+"&level="+level+"&sceening="+sceenNum+"&fatherMediaId="+fatherMediaId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					dialog("发送成功");
				}else{
					dialog(e.msg);
				}
			}
		});
    }
    function prev(){
    	var userName = jQuery("#userName").val();
		jQuery.ajax({
			type : "post",
			async : false,
			url : baseUrl + "wechat/item/prev",
			data : "mediaId="+mediaId+"&userName="+userName,
			dataType : "json",
			success : function(g){
				if(g.code == 0){
					dialog("预览信息已发送至您的手机");
					jQuery("#weixin-yulan-modal").modal('hide');
				}else{
					dialog(g.msg);
				}
			}
		});
    }
    function closeChoseLevel(){
    	jQuery("#level-madal").modal('hide');
    	jQuery("#levels").find('input:checked').prop("checked",false);
    }
    function closeChoseGroup(){
    	jQuery("#group-madal").modal('hide');
    	jQuery("#groups").find('input:checked').prop("checked",false);
    }
</script>
</html>