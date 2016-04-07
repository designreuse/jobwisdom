<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--客服悬浮框-->
<div class="xuanfu hide">
	<span class="yuan"> <img src="<%=basePath%>images/kefu.png"
		alt="" class="kefu-icon"> <!--  <span class="xiaoxi">1</span>-->
	</span>
	<div class="xuanfu-main">
		<div class="fl">
			<div class="kefu-status">
				<span>在线客服</span>
			</div>
			<ul class="kefu-people">
				<li style="height: 40px; line-height: 40px; color: #b2b2b2">客服列表</li>
				<c:forEach items="${kfInfos }" var="info">
					<li data_id="${info.employId }" onclick="showKf(this)">
						<span class="iconfont icon-xuanzhong"></span>
						<img src="<%=basePath%>images/dd-img.png" alt="">
						<span class="text-txt">${info.employAccount }</span>
					</li>
				</c:forEach>
				<%-- <li><span class="iconfont icon-xuanzhong"></span><img
					src="<%=basePath%>images/dd-img.png" alt=""><span
					class="text-txt">客服0123</span></li>
				<li><span class="iconfont icon-xuanzhong"></span><img
					src="<%=basePath%>images/dd-img.png" alt=""><span
					class="text-txt">客服0123</span></li>
				<li><span class="iconfont icon-xuanzhong"></span><img
					src="<%=basePath%>images/dd-img.png" alt=""><span
					class="text-txt">客服0123</span></li>
				<li><span class="iconfont icon-xuanzhong"></span><img
					src="<%=basePath%>images/dd-img.png" alt=""><span
					class="text-txt">客服0123</span></li>
				<li class="current"><span class="iconfont icon-xuanzhong"></span><img
					src="<%=basePath%>images/dd-img.png" alt=""><span
					class="text-txt">客服0123</span></li>
				<li><span class="iconfont shanchu">&#xe625;</span><img
					src="<%=basePath%>images/dd-img.png" alt=""><span
					class="text-txt">客服0123</span></li>
				<li><span class="iconfont icon-xuanzhong"></span><img
					src="<%=basePath%>images/dd-img.png" alt=""><span
					class="text-txt">客服0123</span></li> --%>
			</ul>
		</div>
		<div class="fr">
                <div class="xf-top">
                    <span class="iconfont icon-xx fr"></span>
                </div>
                <div class="kefu">
	            	<c:forEach items="${kfInfos }" var="info">
						<ul ul_id="${info.employId }" class="bubble-main hide">
	                        <%-- <li class="media clearfix">
	                            <div class="media-left">
	                                <a href="javascript:void(0);" class="media-object">
	                                    <img src="<%=basePath%>images/dd-img.png" alt="">
	                                </a>
	                            </div>
	                            <div class="media-body">
	                                <p class="media-text media-arr">客服ID: ${info.employId }</p>
	                            </div>
	                        </li>
	                        <li class="media clearfix media1">
	                            <div class="media-right">
	                                <a href="javascript:void(0);" class="media-object">
	                                    <img src="<%=basePath%>images/dd-img.png" alt="">
	                                </a>
	                            </div>
	                            <div class="media-body">
	                                <p class="media-text media-arr">eros non enim commodo hendrerit.</p>
	                            </div>
	                        </li> --%>
	                    </ul>
					</c:forEach>
                    
                    <div class="bq-icon">
                        <span class="iconfont icon-xiaolian"></span>
                        <span class="iconfont icon-jietu"></span>
                        <span class="iconfont icon-scissors"></span>
                    </div>
                    <div class="search-sent">
                        <div>
                            <textarea type="text" class="sent-txt"></textarea>
                            <span class="send" onclick="sendMsg(this)">发送</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
	</div>

<script type="text/javascript">
jQuery(function() {
    var bool=true;
    jQuery(".xuanfu .yuan").on("click",function() {
        if(bool==true) {
            jQuery(".xuanfu-main").fadeOut(100)
            jQuery(".xuanfu").animate({"right":"0px"},100)
            bool=false;
        }
        else {
            jQuery(".xuanfu-main").fadeIn(100)
            jQuery(".xuanfu").animate({"right":"0px"},100)
            bool=true;
        }
    })

    jQuery(window).scroll(function(event){
        var xf=jQuery(window).scrollTop()
        jQuery(".xuanfu").css({'top':xf})
        jQuery(".xuanfu").css({'margin-top':78})
    })
    
    jQuery(".kefu").children().eq(0).removeClass('hide')
})
var kfId = '<c:out value="${kfInfos[0].employId }"></c:out>';
function showKf(obj){
	kfId = jQuery(obj).attr("data_id");
	jQuery(".bubble-main").addClass('hide');
	jQuery("ul[ul_id='"+kfId+"']").removeClass('hide');
}
function sendMsg(obj){
	var text = jQuery(obj).parent().children("textarea").val();
	if (text==null||text==''){
		dialog('消息不可为空');
		return;
	}
    var html = '<li class="media clearfix media1">'+
				    '<div class="media-right">'+
						'<a href="javascript:void(0);" class="media-object">'+
						    '<img src="'+baseUrl+'images/dd-img.png" alt="">'+
						'</a>'+
					'</div>'+
					'<div class="media-body">'+
						'<p class="media-text media-arr">'+text+'</p>'+
					'</div>'+
				'</li>';
	jQuery("ul[ul_id='"+kfId+"']").append(jQuery(html));
	socket.emit('chatMsg', userId, kfId, text, 'emp');
}

</script>
    