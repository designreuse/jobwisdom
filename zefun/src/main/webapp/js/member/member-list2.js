  var sex = "全部";
  var levelId = 0;	
  var serchType = 0;
  jQuery("#show_more").on("click",function(){
	    var detailInfo = jQuery(".more-condition-table");
	    //将会员手机姓名条件清空
	    jQuery("#serchMemberByNameOrPhone").val("");
	    if(detailInfo.is(':visible')){
	    	serchType = 0;
	        detailInfo.hide();
	        jQuery(".show-more").removeClass("open");
	    }else{
	    	serchType = 1;
	        detailInfo.show();
	        jQuery(".show-more").addClass("open");
	
	    }
 });
  
  var isGroup = false;
  var isPhone = false;
  var isSex = false;
  var isLevel = false;
  //查看筛选器搜索结果
  jQuery("#serch").on("click",function(){
    	var ok = checkInput();
    	if(ok==false){
    		return;
    	}
    	sex = "全部";
    	levelId = 0;
    	pageNo = 1;
    	var data = "pageNo="+pageNo+"&pageSize="+pageSize+"&sex="+sex+"&levelId="+levelId;
    	data = initDate(data);
    	isGroup = true;
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/serch/by/screen",
			data : data,
			dataType : "json",
			success : function(e){
				jQuery("#serchMemberByNameOrPhone").val('');
				if(e.code == 0){
					jQuery("#member_serch_count").text(e.msg.totalRecord);
					jQuery(".perpage").text(e.msg.pageSize);
					jQuery("#init_member").empty();
					initTable(e);
					totalPage = e.msg.totalPage;
					pageNo = e.msg.pageNo;
					pageSize = e.msg.pageSize;
					totalRecord = e.msg.totalRecord;
					if(isGroup){
						isGroup = false;
						unbuildPagination();
					}
				}else{
					jQuery("#member_serch_count").text(0);
					jQuery("#init_member").empty();
				}
			}
		});
        jQuery("#tishi").show();
    });
    jQuery("#baocun").on("click",function(){
    	var groupName = jQuery("#group_name").val();
    	var data = "screeningName="+groupName;
    	data = initDate(data);
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/add/memberScreening",
			data : data,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					dialog("新增成功");
					jQuery("#add-member-group").modal('hide');
				}
			}
		});
    });
    /*放弃保存*/
    jQuery("#fangqi").on("click",function(){
        jQuery("#tishi").hide();
    });
    function searchMemberLike(obj){
    	jQuery(".fuzzysearch").empty();
    	var str = "";
    	var inputVal = jQuery(obj).val();
    	for (var i = 0; i < memberArray.length; i++) {
    		if(inputVal != ""){
    			var memberName = memberArray[i].name;
    			var phone = memberArray[i].phone;
    			if(memberName.indexOf(inputVal) == -1 && phone.indexOf(inputVal) == -1){
    				//jQuery(".fuzzysearch").hide();
    			}else{
    				str = str + '<li class="text-center"  memberId="'+memberArray[i].memberId+'"><sapn><span class="mr10">'+memberName+':</span>'+phone+'</sapn></li>';
    			}
    		}else{
    			jQuery(".fuzzysearch").hide();
    		}
		}
    	str = str + '<em class="t-border"></em><span class="t-content"></span>';
    	jQuery(".fuzzysearch").append(jQuery(str));
    	if (jQuery(".fuzzysearch").children("li").length>0){
    		jQuery(".fuzzysearch").show();
    	}
    }
    jQuery(".fuzzysearch").delegate("li","click",function(){
    	var memberId = jQuery(this).attr("memberid");
    	for (var i = 0; i < memberArray.length; i++) {
    		if(memberArray[i].memberId == memberId){
    			var results = [{
    		        "avgConsumeAmount": memberArray[i].avgConsumeAmount,
    		        "avgConsumeDays": memberArray[i].avgConsumeDays,
    		        "balanceAmount": memberArray[i].balanceAmount,
    		        "balanceGiftmoneyAmount": memberArray[i].balanceGiftmoneyAmount,
    		        "balanceIntegral": memberArray[i].balanceIntegral,
    		        "birthday": memberArray[i].birthday,
    		        "community": memberArray[i].community,
    		        "createTime": memberArray[i].createTime,
    		        "debtAmount": memberArray[i].debtAmount,
    		        "isDeleted": memberArray[i].isDeleted,
    		        "lastConsumeTime": memberArray[i].lastConsumeTime,
    		        "lastOperatorId": memberArray[i].lastOperatorId,
    		        "levelName": memberArray[i].levelName,
    		        "memberId": memberArray[i].memberId,
    		        "name": memberArray[i].name,
    		        "phone": memberArray[i].phone,
    		        "sex": memberArray[i].sex,
    		        "storeId": memberArray[i].storeId,
    		        "storeName": memberArray[i].storeName,
    		        "totalAmount": memberArray[i].totalAmount,
    		        "totalConsumeAmount": memberArray[i].totalConsumeAmount,
    		        "totalGiftmoneyAmount": memberArray[i].totalGiftmoneyAmount,
    		        "totalIntegral": memberArray[i].totalIntegral,
    		        "totalPresentAmount": memberArray[i].totalPresentAmount
    		    }];
    			var e = {"msg":{"results":results}};
    			jQuery("#init_member").empty();
    			initTable(e);
				pageNo = 1;
				pageSize = 50;
				totalPage = 1;
				totalRecord = 1;
				unbuildPagination();
    		}
    	}
    });
    jQuery("body").delegate(".search-input","blur", function(event){
    	setTimeout(function () {
    		jQuery(".fuzzysearch").css("display", "none");
    	},500);
    });
    //根据会员名称和电话查询
    jQuery("#serchMemberByNameOrPhoneDoc").on("click",function(){
  	  var content = jQuery("#serchMemberByNameOrPhone").val();
  	  if(content == ""){
  		  location.reload();
  		  return;
  	  }
  	  jQuery(".table.table-bordered.table-striped.member-list-table").find("th").eq(3).find("span[class='dropdown-toggle']").text("会员等级");
	  jQuery(".table.table-bordered.table-striped.member-list-table").find("th").eq(2).find("span[class='dropdown-toggle']").text("性别");
	  sex = "全部";
	  pageNo = 1;
  	  levelId = 0;
  	  serchType = 0;
  	  //隐藏多条件
  	  jQuery(".table.more-condition-table").hide();
  	  jQuery("#tishi").hide();
  	  isPhone = true;
  	  serchPhoneName(content);
    });
  /*分页*/
  //上一页
  function previous(){
  	if(pageNo <= 1){
  		return;
  	}
  	pageNo--;
  	changePage();
  }
  //下一页
  function next(){
  	if(pageNo >= totalPage){
  		return;
  	}
  	pageNo++;
  	changePage();
  }
//更改每页显示数量
  function changePageSize(size){
  	pageNo = 1;
  	pageSize = size;
  	jQuery(".perpage").html(size + " <span class='iconfa-caret-down afont'></span>");
  	changePage();
  }
//分页处理
  function changePage(){
	  if (serchType == 1){
		var data = "pageNo="+pageNo+"&pageSize="+pageSize+"&sex="+sex+"&levelId="+levelId;
		data = initDate(data);
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/serch/by/screen",
			data : data,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					jQuery(".perpage").text(e.msg.pageSize);
					jQuery("#init_member").empty();
					initTable(e);
					pageNo = e.msg.pageNo;
					pageSize = e.msg.pageSize;
					totalPage = e.msg.totalPage;
					totalRecord = e.msg.totalRecord;
					if(isSex){
						isSex = false;
						unbuildPagination();
					}
					if(isLevel){
						isLevel = false;
						unbuildPagination();
					}
				}else{
					jQuery("#member_serch_count").text("当前满足条件 0 人");
					jQuery("#init_member").empty();
				}
			}
		});
	  }else{
		  var content = jQuery("#serchMemberByNameOrPhone").val();
		  serchPhoneName(content);
	  }
 }

  //封装查询date数据
  function initDate(data){
	  	var chuzhis = jQuery("#chuzhi1").val();
		if(chuzhis!="")data=data+"&unusedBalanceStart="+chuzhis;
	  	var chuzhie = jQuery("#chuzhi2").val();
	  	if(chuzhie!="")data=data+"&unusedBalanceEnd="+chuzhie;
	  	var jifenyes = jQuery("#jifenye1").val();
	  	if(jifenyes!="")data=data+"&integralBalanceStart="+jifenyes;
	  	var jifenyee = jQuery("#jifenye2").val();
	  	if(jifenyee!="")data=data+"&integralBalanceEnd="+jifenyee;
	  	var birthdays = jQuery("#birthday-start").val();
	  	if(birthdays!="")data=data+"&birthdayStart="+birthdays;
	  	var birthdaye = jQuery("#birthday-end").val();
	  	if(birthdaye!="")data=data+"&birthdayEnd="+birthdaye;
	  	var registers = jQuery("#register-start").val();
	  	if(registers!="")data=data+"&registrationDateStart="+registers;
	  	var registere = jQuery("#register-end").val();
	  	if(registere!="")data=data+"&registrationDateEnd="+registere;
	  	var xiaofeis = jQuery("#xiaofje1").val();
	  	if(xiaofeis!="")data=data+"&consumptionAverageStart="+xiaofeis;
	  	var xiaofeie = jQuery("#xiaofje2").val();
	  	if(xiaofeie!="")data=data+"&consumptionAverageEnd="+xiaofeie;
	   	var leijixfs = jQuery("#leijixf1").val();
	  	if(leijixfs!="")data=data+"&cumulativeConsumptionStart="+leijixfs;
	  	var leijixfe = jQuery("#leijixf2").val();
	  	if(leijixfe!="")data=data+"&cumulativeConsumptionEnd="+leijixfe;
	  	var scxf1 = jQuery("#scxf1").val();
	  	if(scxf1!="")data=data+"&lastConsumeTimeStart="+scxf1;
	  	var scxf2 = jQuery("#scxf2").val();
	  	if(scxf2!="")data=data+"&lastConsumeTimeEnd="+scxf2;
	  	var gzje1 = jQuery("#gzje1").val();
	  	if(gzje1!="")data=data+"&debtAmountStart="+gzje1;
	  	var gzje2 = jQuery("#gzje2").val();
	  	if(gzje2!="")data=data+"&debtAmountEnd="+gzje2;
	  	var ljye1 = jQuery("#ljye1").val();
	  	if(ljye1!="")data=data+"&giftMoneyAmountStart="+ljye1;
	  	var ljye2 = jQuery("#ljye2").val();
	  	if(ljye2!="")data=data+"&giftMoneyAmountEnd="+ljye2;
	  	return data;
  }

  function serchPhoneName(content){
		jQuery.ajax({
			type : "post",
			url : baseUrl + "member/serch/by/nameOrPhone",
			data : "pageNo="+ pageNo + "&pageSize=" + pageSize + "&content="+content+"&sex="+sex+"&levelId="+levelId,
			dataType : "json",
			success : function(e){
				if(e.code == 0){
					jQuery(".perpage").text(e.msg.pageSize);
					jQuery("#init_member").empty();
					initTable(e);
					pageNo = e.msg.pageNo;
					pageSize = e.msg.pageSize;
					totalPage = e.msg.totalPage;
					totalRecord = e.msg.totalRecord;
					if(isPhone){
						isPhone = false;
						unbuildPagination();
					}
					if(isSex){
						isSex = false;
						unbuildPagination();
					}
					if(isLevel){
						isLevel = false;
						unbuildPagination();
					}
				}else{
					jQuery("#init_member").empty();
				}
			}
		});
  }
  
  //根据性别或者会员等级进行筛选
  function serchMemberBySex(ssex,obj){
	  jQuery(".table.table-bordered.table-striped.member-list-table").find("th").eq(3).find("span[class='dropdown-toggle']").text("会员等级");
	  jQuery(obj).parent().parent().prev(".dropdown-toggle").text('性别'+'('+ssex+')');
	  jQuery(obj).parent().parent().prev(".dropdown-toggle").append('<span class="caret"></span>');
	  //在点击了按此条件查询的时候一定要全部还原,包括分页的信息
	  levelId = 0;
	  pageNo = 1;
	  pageSize = 50;
	  isSex = true;
	  if(ssex == "全部"){
	      sex = "全部";
		  changePage();
	  }else{
		  sex = ssex;
		  changePage();
	  }
  }
  function serchMemberByLevel(slevelId,levelName,obj){
	  jQuery(".table.table-bordered.table-striped.member-list-table").find("th").eq(2).find("span[class='dropdown-toggle']").text("性别");
	  jQuery(obj).parent().parent().prev(".dropdown-toggle").text('会员等级'+'('+levelName+')');
	  jQuery(obj).parent().parent().prev(".dropdown-toggle").append('<span class="caret"></span>');
	  //在点击了按此条件查询的时候一定要全部还原,包括分页的信息
	  sex = "全部";
	  pageNo = 1;
	  pageSize = 50;
	  isLevel = true;
	  if(slevelId=="0"){
		  levelId = 0;
		  changePage();
	  }else{
		  levelId = slevelId;
		  changePage();
	  }
  }
  //拼装table
  function initTable(e){
		for (var i = 0; i < e.msg.results.length; i++) {
			var tr = jQuery("<tr></tr>");
			var td1 = jQuery("<td community='"+e.msg.results[i].community+"'>"+e.msg.results[i].phone+"</td>");
			var td2 = jQuery("<td class='can-click' data-target='#member-data' onclick='selectMemberInfo("+e.msg.results[i].memberId+")' data-toggle='modal' id="+e.msg.results[i].memberId+">"+e.msg.results[i].name+"</td>");
			var td3 = jQuery("<td>"+e.msg.results[i].sex+"</td>");
			var td4 = jQuery("<td>"+e.msg.results[i].levelName+"</td>");
			var td5;
			if (e.msg.results[i].birthday==null||e.msg.results[i].birthday==""){
				td5 = jQuery("<td>"+"</td>");
			}else{
				td5 = jQuery("<td>"+e.msg.results[i].birthday+"</td>");
			}
			var td6 = jQuery("<td>"+e.msg.results[i].balanceAmount+"</td>");
			var td7 = jQuery("<td>"+e.msg.results[i].balanceIntegral+"</td>");
			var td8 = jQuery("<td>"+e.msg.results[i].totalConsumeAmount+"</td>");
			var td9 = jQuery("<td>"+e.msg.results[i].avgConsumeAmount+"</td>");
			var td12 = jQuery("<td>"+e.msg.results[i].balanceGiftmoneyAmount+"</td>");
			var td10;
			if (e.msg.results[i].createTime==null||e.msg.results[i].createTime==""){
				td10 = jQuery("<td>"+"</td>");
			}else{
				td10 = jQuery("<td class='input80'>"+e.msg.results[i].createTime.substring(0,10)+"</td>");
			}
			var td11 = jQuery('<td class="can-click m-btn update" style="text-decoration: none">'+
									'<span class="iconfont icon-icon07 "></span>'+
				            		'<span class="cursor memberlevel-default-setting hide" style="display: none;">修改</span>'+
				        	  '</td>');
			var td14 = jQuery("<td>"+e.msg.results[i].debtAmount+"</td>");
			var td13 = jQuery('<td><i class="iconfont icon-xx ml10" onclick="deleteMemberTip('+e.msg.results[i].memberId+',this);"></i></td>');
//			var td15 = jQuery('<td class="input80 ellipsis-text" data-toggle="tooltip" data-placement="right" title="'+e.msg.results[i].community+'">'
//								+e.msg.results[i].community+
//                    			'</td>');
			tr.append(td1);
			tr.append(td2);
			tr.append(td3);
			tr.append(td4);
			tr.append(td5);
			tr.append(td6);
			tr.append(td7);
			tr.append(td8);
			tr.append(td9);
			tr.append(td12);
			tr.append(td10);
			tr.append(td14);
//			tr.append(td15);
			tr.append(td11);
			tr.append(td13);
			jQuery("#init_member").append(tr);
		}
//		jQuery(".can-click.m-btn.update").hover(function() {
//			jQuery(this).find("span").show();
//		}, function() {
//			jQuery(this).find("span").eq(1).hide();
//		});
  }
  
  //校验数据
  function checkInput(){
		var chuzhis = jQuery("#chuzhi1").val();
	  	var chuzhie = jQuery("#chuzhi2").val();
	  	var jifenyes = jQuery("#jifenye1").val();
	  	var jifenyee = jQuery("#jifenye2").val();
	  	var birthdays = jQuery("#birthday-start").val();
	  	var birthdaye = jQuery("#birthday-end").val();
	  	var registers = jQuery("#register-start").val();
	  	var registere = jQuery("#register-end").val();
	  	var xiaofeis = jQuery("#xiaofje1").val();
	  	var xiaofeie = jQuery("#xiaofje2").val();
	   	var leijixfs = jQuery("#leijixf1").val();
	  	var leijixfe = jQuery("#leijixf2").val();
	  	var scxf1 = jQuery("#scxf1").val();
	  	var scxf2 = jQuery("#scxf2").val();
	  	
	  if(chuzhis!=""){
		  if((/(^[0-9]\d*$)/.test(chuzhis))!=true){
			  dialog("储值余额请输入整数");
			  return false;
		  }
	  }
	  if(chuzhie!=""){
		  if((/(^[0-9]\d*$)/.test(chuzhie))!=true){
			  dialog("储值余额请输入整数");
			  return false;
		  }
	  }
	  if(jifenyes!=""){
		  if((/(^[0-9]\d*$)/.test(jifenyes))!=true){
			  dialog("积分余额请输入整数");
			  return false;
		  }
	  }
	  if(jifenyee!=""){
		  if((/(^[0-9]\d*$)/.test(jifenyee))!=true){
			  dialog("积分余额请输入整数");
			  return false;
		  }
	  }
	  if(xiaofeis!=""){
		  if((/(^[0-9]\d*$)/.test(xiaofeis))!=true){
			  dialog("消费均额请输入整数");
			  return false;
		  }
	  }
	  if(xiaofeie!=""){
		  if((/(^[0-9]\d*$)/.test(xiaofeie))!=true){
			  dialog("消费均额请输入整数");
			  return false;
		  }
	  }
	  if(leijixfs!=""){
		  if((/(^[0-9]\d*$)/.test(leijixfs))!=true){
			  dialog("累计消费请输入整数");
			  return false;
		  }
	  }
	  if(leijixfe!=""){
		  if((/(^[0-9]\d*$)/.test(leijixfe))!=true){
			  dialog("累计消费请输入整数");
			  return false;
		  }
	  }
	  if(chuzhis!=""&&chuzhie!=""){
		  if((chuzhis-chuzhie)>=0){
			  dialog("储值余额请正确输入区间");
			  return false;
		  }
	  }
	  if(jifenyes!=""&&jifenyee!=""){
		  if((jifenyes-jifenyee)>=0){
			  dialog("积分余额请正确输入区间");
			  return false;
		  }
	  }
	  if(xiaofeis!=""&&xiaofeie!=""){
		  if((xiaofeis-xiaofeie)>=0){
			  dialog("消费均额请正确输入区间");
			  return false;
		  }
	  }
	  if(leijixfs!=""&&leijixfe!=""){
		  if((leijixfs-leijixfe)>=0){
			  dialog("累计消费请正确输入区间");
			  return false;
		  }
	  }
	  if(birthdays!=""&&birthdaye!=""){
		  var birstart = new Date(("2015-"+birthdays).replace(/-/g,"/"));
		  var birend = new Date(("2015-"+birthdaye).replace(/-/g,"/"));
		  if((birstart-birend)>=0){
			  dialog("开始时间大于结束时间");
			  return false;
		  }
	  }
	  if(registers!=""&&registere!=""){
		  var resstart = new Date(registers.replace(/-/g,"/"));
		  var resend= new Date(registere.replace(/-/g,"/"));
		  if((resstart-resend)>=0){
			  dialog("开始时间大于结束时间");
			  return false;
		  }
	  }
	  return true;
  }
  
  //删除会员数据
  var memberId = null;
  function deleteMemberTip(id, obj){
	memberId = id;
  	jQuery("#deleteErrorMemberModel").modal('show');
  }

  //删除会员数据
  function deleteMember(){
  	jQuery("#deleteErrorMemberModel").modal('hide');
  	if(isEmpty(memberId)){
  		dialog("请刷新重试！");
  		return;
  	}
  	jQuery.ajax({
  		type : "post",
  		url : baseUrl + "member/view/member/delete",
  		data : "memberId="+memberId,
  		dataType : "json",
  		success : function(e) {
  			if (e.code != 0) {
  				dialog(e.msg);
  				return;
  			}
  			else {
  				dialog(e.msg);
  				jQuery("#member_"+memberId).hide('800');
  			}
  			memberId = null;
  		}
  	});
  }