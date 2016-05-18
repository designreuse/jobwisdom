<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/head.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<body>
<style>
	.percent-symbol {
	    margin-left: -20px;
	    color: #999;
	    display: inline-block;
	    height: 30px;
	    line-height: 30px;
    }
	.more-condition-table td span:nth-of-type(2) {
    	margin-left:4px;
    }
  	<style>
    .t-table td:nth-child(1) {
        width:78px;
    }

    .t-table td:nth-child(2) {
        width: 80px;
    }

  .t-table td:nth-child(3) {
      width:34px;
  }

  .t-table td:nth-child(4) {
      width:90px;
  }

    .t-table td:nth-child(5) {
        width:50px !important;
      }

    .t-table td:nth-child(11) {
        width:80px !important;
    }

    .t-table td:nth-child(13) {
        width:30px !important;

    }
    .t-table td:nth-child(14) {
        width:30px !important;

    }
</style>
<script type="text/javascript" src="<%=basePath %>js/base/exportTable.js"></script>
<script type="text/javascript" src="<%=basePath %>/js/My97DatePicker/WdatePicker.js"></script>
<div class="mainwrapper" id="mainwrapper" name="mainwrapper" style="background-position: 0px 0px;">
    <div class="leftpanel" style="height: 840px; margin-left: 0px;">
        <%@include file="/menu.jsp"%>
		<div class="rightpanel" style="margin-left: 200px; position: relative">
			<%@include file="/top.jsp"%>
      	<!-- 页面代码 -->
		<div class="maincontent">
		    <div class="contentinner">
		    	<div class="widgetcontent">
			    <div class="more-toolbar" >
		            <div class="table-toolbar">
		                <span class="font-size-16 btn-color mr10">会员数据</span>
		                <a class="button-search btn fr ml10" href="<%=basePath %>member/view/error/member/info">异常会员数据</a>
		            </div>
		            <div class="clearfix"></div>
		        </div><!--more-toolbar-->
		        <div class="more-toolbar" >
		            <div class="table-toolbar">
		                <button class="btn" data-toggle="modal" data-target="#toLeadModal">导入</button>
		                <input type="search" class="search-input" onkeyup="searchMemberLike(this)" onfocus='if(jQuery(".fuzzysearch").children("li").length>0){jQuery(".fuzzysearch").show();}' placeholder="手机号码/姓名" id="serchMemberByNameOrPhone"/>
		                <button class="button-search btn width100 ml-10" id="serchMemberByNameOrPhoneDoc">查询</button>
		                <ul class="fuzzysearch" style="display: none;">
		                </ul>
		                <div class="show-more" id="show_more">
		                    <img src="<%=basePath %>/img/common/d-triangle.png" alt="" class="ml10 mr10 more-img"/><span>更多筛选信息</span>
		                </div>
		            </div>
		            <div class="clearfix"></div>
		        </div>
	            <table class="table more-condition-table hide">
	                <tbody>
	                    <tr>
	                        <td class="width8">储值余额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="chuzhi1"/>
	                            <span class="percent-symbol">元</span>
	                            <span>-</span>
	                            <input type="number" class="input80" id="chuzhi2"/>
	                            <span class="percent-symbol">元</span>
	                        </td>
	                        <td class="width8">积分余额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="jifenye1"/>
	                            <span class="percent-symbol">分</span>
	                            <span>-</span>
	                            <input type="number" class="input80" id="jifenye2"/>
	                            <span class="percent-symbol">分</span>
	                        </td>
	                    </tr>
	
	                    <tr>
	                        <td class="width8">生　　日</td>
	                        <td class="">
	                            <input type="text" onfocus="WdatePicker({dateFmt:'MM-dd'})" class="input80" id="birthday-start"/>
	                            <span>-</span>
	                            <input type="text" onfocus="WdatePicker({dateFmt:'MM-dd'})" class="input80" id="birthday-end"/>
	                        </td>
	                        <td class="width8">注册日期</td>
	                        <td class="">
	                            <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input80" id="register-start"/>
	                            <span>-</span>
	                            <input type="text" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" class="input80" id="register-end"/>
	                        </td>
	                    </tr>
	
	                    <tr>
	                        <td class="width8">消费均额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="xiaofje1"/>
	                            <span class="percent-symbol">元</span>
	                            <span>-</span>
	                            <input type="number" class="input80" id="xiaofje2"/>
	                            <span class="percent-symbol">元</span>
	                        </td>
	                        <td class="width8">累计消费</td>
	                        <td class="">
	                            <input type="number" class="input80" id="leijixf1"/>
	                            <span class="percent-symbol">元</span>
	                            <span>-</span>
	                            <input type="number" class="input80" id="leijixf2"/>
	                            <span class="percent-symbol">元</span>
	                        </td>
	                    </tr>
	                    
	                    <tr>
	                        <td class="width8">距上次消费</td>
	                        <td class="">
	                            <input type="number" class="input80" id="scxf1"/>
	                            <span class="percent-symbol">天</span>
	                            <span>-</span>
	                            <input type="number" class="input80" id="scxf2"/>
	                            <span class="percent-symbol">天</span>
	                        </td>
	                        <td class="width8">礼金余额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="ljye1"/>
	                            <span class="percent-symbol">元</span>
	                            <span>-</span>
	                            <input type="number" class="input80" id="ljye2"/>
	                            <span class="percent-symbol">元</span>
	                        </td>
	                    </tr>
	                    
	                    <tr>
	                        <td class="width8">挂账金额</td>
	                        <td class="">
	                            <input type="number" class="input80" id="gzje1"/>
	                            <span class="percent-symbol">元</span>
	                            <span>-</span>
	                            <input type="number" class="input80" id="gzje2"/>
	                            <span class="percent-symbol">元</span>
	                        </td>
	                    </tr>
	                    
	                    <tr>
	                        <td>
	
	                        </td>
	                        <td class="">
	                            <input type="hidden" class="input80" />
	                        </td>
	                        <td class="width8"></td>
	                        <td class="">
	                            <input style="float: right;margin-right: 20px;" type="button" class="btn" id="serch" value="搜索"/><span>&nbsp;&nbsp;&nbsp;</span>
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
	            <!--保存筛选信息-->
	            <table style="border: 1px solid rgb(221, 221, 221); margin-top: 10px; margin-bottom: 0px; width: 100%; display: none;" id="tishi">
                    <tbody>
                    <tr>
                        <td style="padding-left: 10px;">
                           	 满足当前搜索条件共 <span class="red" id="member_serch_count">6</span>人，你要保存该会员分组吗？
                        </td>
                        <td>会员分组名称: <input type="text" id="group_name"></td>
                        <td></td>
                        <td>
                            <input type="button" class="btn" id="fangqi" style="margin-right: 20px;" value="取消">
                            <input type="button" class="btn" id="baocun" style="margin-right: 20px;" value="保存">
                        </td>
                    </tr>
                    </tbody>
                </table>
	            </div>
	            <!--保存筛选信息-->
		        <!-- </div> -->
		        
	            <div class="more-toolbar">
	                <div class="table-toolbar">
	                    <div class="table-pagination">
	                        <table class="ls-detail fr">
	                            <tbody><tr>
	                                <td>会员数：</td>
	                                <td>${page.totalRecord }</td>
	                                <td>储值总额(含赠送)：</td>
	                                <td>${storeMember.totalAmount }</td>
	                                <td>赠送总额：</td>
	                                <td>${storeMember.totalPresentAmount }</td>
	                                <td>储值余额：</td>
	                                <td>${storeMember.balanceAmount }</td>
	                                <td>礼金总额：</td>
	                                <td>${storeMember.totalGiftmoneyAmount }</td>
	                                <td>礼金余额：</td>
	                                <td>${storeMember.balanceGiftmoneyAmount }</td>
	                                <td>挂账金额：</td>
	                                <td>${storeMember.debtAmount }</td>
	                            </tr>
	                        </tbody></table>
	                    </div><!--table-pagination-->
	                </div><!--table-toolbar-->
	                <div class="clearfix"></div>
	             </div>
	            
		        <table class="table table-bordered table-striped member-list-table">
		            <thead class="t-fix">
		            <tr>
		                <th>手机号</th>
		                <th>姓名</th>
		                <th class="drop-th">
		                    <span class="dropdown-toggle" data-toggle="dropdown">性别 <span class="caret"></span></span>
		                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
		                    	<li><a tabindex="-1" onclick="serchMemberBySex('全部',this)" href="javascript:void(0)">全部</a></li>
		                        <li><a tabindex="-1" onclick="serchMemberBySex('男',this)" href="javascript:void(0)">男</a></li>
		                        <li><a tabindex="-1" onclick="serchMemberBySex('女',this)" href="javascript:void(0)">女</a></li>
		                    </ul>
		                </th>
		                <th class="drop-th">
		                    <span class="dropdown-toggle" data-toggle="dropdown">会员等级 <span class="caret"></span></span>
		                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu">
		                        <li><a tabindex="-1" onclick="serchMemberByLevel('0','全部',this)" href="javascript:void(0)">全部</a></li>
		                        <c:forEach items="${levels }" var="level">
		                        	<li><a tabindex="-1" onclick="serchMemberByLevel('${level.levelId}','${level.levelName }',this)" href="javascript:void(0)">${level.levelName }</a></li>
		                        </c:forEach>
		                    </ul>
		                </th>
		                <th>生日</th>
		                <th>储值余额</th>
		                <th>积分余额</th>
		                <th>累计消费</th>
		                <th>消费均额</th>
		                <th>礼金余额</th>
		                <th>注册日期</th>
		                <th>挂账金额</th>
		                <!-- <th>备注信息</th> -->
		                <th>修改</th>
		                <th>删除</th>
		            </tr>
		            </thead>
		            <tbody id="init_member">
		            <c:forEach items="${page.results }" var="member" varStatus="index">
		              <c:if test="${index.index == 0 }"><tr id="member_${member.memberId }" class="t-table"></c:if>
		              <c:if test="${index.index != 0 }"><tr id="member_${member.memberId }"></c:if>
		                <td community="${member.community }">${member.phone }</td>
		                <td class="can-click" data-target="#member-data" data-toggle="modal" onclick="selectMemberInfo(${member.memberId })" id="${member.memberId }">${member.name }</td>
		                <td>${member.sex }</td>
		                <td>${member.levelName }</td>
		                <td>${member.birthday }</td>
		                <td>${member.balanceAmount }</td>
		                <td>${member.balanceIntegral }</td>
		                <td>${member.totalConsumeAmount }</td>
		                <td>${member.avgConsumeAmount }</td>
		                <td>${member.balanceGiftmoneyAmount }</td>
		                <td>${fn:substring(member.createTime, 0, 10)}</td>
		                <td>${member.debtAmount }</td>
		                <%-- <td data-toggle="tooltip" data-placement="top" title="${member.community }" style="width: 56px;">
							<span class="input80 ellipsis-text">${member.community }</span>
                        </td> --%>
		                <td class="can-click m-btn update"  style="text-decoration: none">
		                	<span class="iconfont icon-icon07 "></span>
	                		<!-- <span class="cursor memberlevel-default-setting hide">修改</span> -->
                		</td>
                		<td>
		                	<i class="iconfont icon-xx ml10 input30" onclick="deleteMemberTip(${member.memberId },this)"></i>
                		</td>
		            </tr>
		            </c:forEach>
		            </tbody>
		        </table>
		        <%@ include file="/template/page.jsp" %>
	       </div><!-- contentinner -->
       </div><!-- maincontent -->
    </div><!-- rightpanel -->
    </div>
</div><!--mainwrapper-->
<!--删除提示-->
           <div class="modal hide" id="deleteErrorMemberModel" tabindex="-1" role="dialog" aria-labelledby="deleteErrorMemberModel">
               <div class="modal-dialog" role="document">
                   <div class="modal-content confirm">
                       <div class="modal-header">
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                           <h4 class="modal-title">删除提示</h4>
                       </div>
                       <div class="modal-body confirm-body">
                        	 会员资料删除后将不可恢复，您确认要删除吗？
                       </div>
                       <div class="modal-footer">
                           <a class="btn cancel-btn modal-cancel" data-dismiss="modal" href="javascript:void();">我点错了</a>
                           <a class="btn btn-primary save-btn modal-confirm" href="javascript:deleteMember();">确认删除</a>
                       </div>
                   </div>
               </div>
           </div>
<div class="modal hide in" id="toLeadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
    <div class="modal-dialog" role="document">
        <div class="modal-content add-account" style="width: 450px;height: 180px;">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" onclick="location.reload(true);"><span aria-hidden="true">×</span></button>
                <h5 class="modal-title" id="myModalLabel">上传文件</h5>
            </div>
            <div class="modal-body" style="height: 150px;">
                <form action="http://localhost:8080/zefun/objective/action/importExcle" class="editprofileform" method="post" enctype="multipart/form-data" id="excleForm">
                   
                        <div>
                        <label>之前的服务商:</label>
                        <select data-placeholder="服务商名称" class="chzn-select" name="storeName" onchange="changeLastHelp(this)">
			            		<option value="左右">左右服务商</option>
			            		<option value="盛传">盛传服务商</option>
			            		<option value="云浩企汇通">云浩企汇通</option>
			            		<option value="博卡">博卡</option>
			            		<option value="耕宇">耕宇</option>
			            		<option value="蓝蝶">蓝蝶</option>
			            		<option value="共赢">共赢</option>
			            		<option value="模板">模板</option>
			            		<option value="西沙龙">西沙龙</option>
			            		<option value="华拓美业">华拓美业</option>
			            		<option value="华彩">华彩</option>
			            </select>
                        </div>
                        <br>
                        <div id="sdf"></div>
			            <div id="dokb">
                        <label>选择会员数据:</label>
                        <div href="" class="filess">
						    <input type="text" name="textfield" id="textfield" class="txt" value="未选择文件">
						    <input type='button' class='btn' value='选择文件' />
						    <input type="file" id="file1" name="fileField" class="file"  size="28" onchange="document.getElementById('textfield').value=this.value" />
						</div>
                        <!-- <input type="file" name="filename" id="file1" class="input-large" value="sdf"><br> -->
                        </div>
                        <div id="boka" style="display: none">
                        <label>会员资料表:</label>
                        <div href="" class="filess">
						    <input type="text" name="textfield" id="textfield2" class="txt" value="未选择文件">
						    <input type='button' class='btn' value='选择文件' />
						    <input type="file" id="file2" name="fileField" class="file"  size="28" onchange="document.getElementById('textfield2').value=this.value" />
						</div>
                        <!-- <input type="file" name="filename" id="file2" class="input-large"><br> -->
                        </div>
                </form>
                <div class="hide" id="errorMessage">
                </div>
            </div><!--modal body-->
            <div class="modal-footer">
                <a class="btn btn-primary modal-confirm" href="#" id="confirm"  onclick="UpladFile()">确定</a>
            </div>
        </div>
    </div>
</div>

<!-- 修改会员资料 -->
<div class="modal hide" id="member-xiugai" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content member-xiugai">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title" id="myModalLabel">修改资料</h4>
            </div>
            <div class="modal-body">
                <div style="border-bottom:1px solid #fff">

                    <!-- 基本信息开始 -->
                    <div id="tabs-1">
                        <div class="xg-title">
                            <ul>
                                <li><span>姓名：</span><input type="text" value="" onblur="changeName(this)"></li>
                                <li><span>性别：</span><label class="radio">男<input type="radio" checked="checked" onclick="changeSex('男')"></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label class="radio">女<input type="radio" onclick="changeSex('女')"></label></li>
                                <li><span>生日：</span><input type="text" value="" class="timePickerClean datetimepicker" format="m-d" onblur="changeBirthday(this)"></li>
                                <li><span>手机号：</span><input type="text" value="" onblur="changePhone(this)"></li>
                                <li style="width: 100%"><span>备注：</span><input type="text" value="" style="width: 80%" onblur="changeCommunity(this)"></li>
                            </ul>
                        </div>
                        <table class="table table-bordered table-alter member-base-info-table">
                            <tbody>
                            <tr>
                                <td class="width20 td-bkg-col">会员等级</td>
                                <td class="width30">爱心卡</td>
                                <td class="td-bkg-col">储值余额</td>
                                <td>18900</td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">积分余额</td>
                                <td class="red">899</td>
                                <td class="td-bkg-col">累计消费</td>
                                <td>899</td>
                            </tr>
                            <tr>
                                <td class="td-bkg-col">消费均额</td>
                                <td>32103</td>
                                <td class="td-bkg-col">创建日期</td>
                                <td>32103</td>
                            </tr>
                        </tbody>
                    </table>
                    <form id="updateMemberInfo">
                    	<input type="hidden" name="memberId" >
	                    <input type="hidden" name="name" >
	                    <input type="hidden" name="sex" >
	                    <input type="hidden" name="birthday" >
	                    <input type="hidden" name="phone" >
	                    <input type="hidden" name="community" >
                    </form>
                  </div>
                  <!-- 基本信息结束 -->
              </div>

            </div><!--modal-body-->
            <div class="modal-footer">
                <div class="fr f-btn">
                    <button class="btn" onclick="updateMember()">确定</button>
                </div>
            </div>
        </div><!--modal-content-->
    </div><!--modal-dialog-->
</div>
<%@ include file="/template/memberData.jsp" %>
</body>

<script src="<%=basePath %>js/member/member-list.js" type="text/javascript"></script>
<script src="<%=basePath %>js/member/memberUpdate.js" type="text/javascript"></script>
<script type="text/javascript">
/*表头置顶*/
jQuery(function(){
  var a=[];
    jQuery(".mainwrapper").show()
    var tlength=jQuery(".t-fix th").length;
        for(i=0;i<tlength;i++)  {
        a[i]=jQuery(".t-fix th").eq(i).width();
    }

   function table() {
       jQuery(".mainwrapper").show()
       var fix=jQuery(".t-fix").offset()
       var tableT=fix.top
       jQuery(window).scroll(function(event){
           var scH=jQuery(window).scrollTop()
           if(scH>tableT){
               jQuery(".t-fix").addClass("add-fix")
               for(i=0;i<jQuery(".t-fix th").length;i++){
                   var tdwidth=a[i];
                   var tbwidth=a[i];
                   jQuery(".t-fix th").eq(i).css("width",tdwidth)
                   jQuery(".t-table td").eq(i).css("width",tbwidth)

               }
           }
           else{
               jQuery(".t-fix").removeClass("add-fix")
           }
       })
   }

    jQuery("#show_more").on("click",function(){
        var zt=false;
        if(zt==false) {
            jQuery(".more-condition-table").removeClass("hide")
            table();
        }
    })
    table();
})

var str = '<%=request.getAttribute("infoDtos")%>';
var memberArray = eval("(" + '<%=request.getAttribute("infoDtos")%>' + ")");
jQuery("[data-toggle='tooltip']").tooltip({container: "body"});

jQuery("select").chosen();
//日期
/* jQuery('#register-start, #register-end').datetimepicker({
	lang:'ch',
	timepicker:false,
	format:'Y-m-d'
});
jQuery('#birthday-start, #birthday-end').datetimepicker({
	lang:'ch',
	timepicker:false,
	format:'m-d'
}); */
	
//获取加载页面时的页码信息
var pageNo = '${page.pageNo}';	
var pageSize = '${page.pageSize}';	
var totalPage = '${page.totalPage}';	

function UpladFile() {
	jQuery("#loadingWrap").fadeIn();
	var storeName = jQuery("select[name='storeName']").val();
    var fileObj = document.getElementById("file1").files[0];
    console.log(fileObj)
    var FileController = baseUrl +"member/action/importexcle";                    // 接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("file", fileObj);     // 文件对象
    if(storeName=="博卡"){
    	var fileObj2 = document.getElementById("file2").files[0];
    	form.append("file", fileObj2);
    }
    if(storeName=="盛传"){
    	var fileObj2 = document.getElementById("file2").files[0];
    	form.append("file", fileObj2);
    }
    if(storeName=="华彩"){
    	var fileObj2 = document.getElementById("file2").files[0];
    	form.append("file", fileObj2);
    }
    if(storeName=="蓝蝶"){
    	form.append("loginCode", jQuery("input[name='loginCode']").val());
    	form.append("loginName", jQuery("input[name='loginName']").val());
    	form.append("loginPass", jQuery("input[name='loginPass']").val());
    }
    form.append("storeName", storeName);
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.onload = function (e) {
    	jQuery("#loadingWrap").fadeOut();
    	/*dialog(xhr.responseText);*/
    	var json = eval("("+xhr.responseText+")");
    	jQuery("#errorMessage").hide();
    	jQuery("#importCombo").hide();
    	if(json.code!=0){
    		jQuery("#errorMessage").text(json.msg);
    		jQuery("#errorMessage").show();
    		return;
    	}else{
    		if(json.msg == '导入成功'){
    			dialog(json.msg);
    	        setTimeout(function(){
    	        	location.reload();
    	    	},300);
    		}else{
	    		jQuery("#importCombo").find("a").attr("href", picUrl + json.msg);
	    		jQuery("#importCombo").show();
	    		return;
    		}
    	}
    };
    xhr.send(form);
}
function changeLastHelp(obj){
	jQuery("#dokb").show();
	jQuery("#sdf").empty();
	if (jQuery(obj).val() == "博卡"){
		jQuery("#excleForm").find("label").eq(1).text("消费余额表");
		jQuery("#boka").show();
	}
	else if (jQuery(obj).val() == "盛传"){
		jQuery("#excleForm").find("label").eq(1).text("会员资料表");
		jQuery("#excleForm").find("label").eq(2).text("疗程表");
		jQuery("#boka").show();
	}
	else if (jQuery(obj).val() == "华彩"){
		jQuery("#excleForm").find("label").eq(1).text("会员卡数据");
		jQuery("#excleForm").find("label").eq(2).text("会员数据");
		jQuery("#boka").show();
	}
	else {
		jQuery("#excleForm").find("label").eq(1).text("选择会员数据");
		jQuery("#boka").hide();
	}
	if (jQuery(obj).val() == "蓝蝶"){
		var str = '<div>'+
            '公司编码: <input type="text" name="loginCode"><br>'+
            '登陆名称:<input type="text" name="loginName"><br>'+
            '登陆密码:<input type="text" name="loginPass">'+
            '</div>';
        jQuery("#sdf").append(jQuery(str));
        jQuery("#dokb").hide();
	}
}
</script>

</html>