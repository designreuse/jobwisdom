window.onload=function (){
	        initPageSize();
			document.onkeydown = function(event) {
				var e = event || window.event || arguments.callee.caller.arguments[0];
				//按下键盘后焦点位置
				var focusElement = document.activeElement;
				supkeydown(e,focusElement);
			}
			
		};  
/*美发修改页面点击出现下拉菜单*/
    $(".down-operation").on('click',function(){
        var parent = $(this).parent().parent();
        if(parent.hasClass("open")){
            parent.removeClass("open");
        }else{
            parent.addClass("open");
        }
    });

    /*美发修改页面点击出现选择菜单*/
    $(".item-input").on("click",function(){
        var th = $(this);
        if(!th.hasClass("open")){
            $(this).addClass("open");
        }else{
            $(this).removeClass("open");
        }
    });

    function toggle(a,b){
        a.addClass("hide");
        b.removeClass("hide");
    }

    /*增加美发设置时的修改跳出修改页面*/
    $(".yuangong-jump").on("click", function(){
        var a = $(".hair-setting");
        var b = $(".assign-staff");
        toggle(a, b);
    });

    $(".huiyuan-jump").on("click", function(){
        var a = $(".hair-setting");
        var b = $(".assign-staff");
        toggle(a, b);
    });

    /*在修改页面点击返回跳到美发设置页面*/
    $(".return").on("click",function(){
        var a = $(".assign-staff");
        var b = $(".hair-setting");
        toggle(a, b);
    });
    
    function checkBoxChangeValue(checkBox){
    	if(checkBox.checked == true){checkBox.value = "1"}else{checkBox.value = "0"}
    	console.log(checkBox.value);
    }
    function a(name){
		var names = name.split(",");
		for ( var s in names) {
		$("input[name='"+names[s]+"']").attr("class","input1 bg-money-img");
		}
	}
	function b(name){
		var names = name.split(",");
		for ( var s in names) {
		$("input[name='"+names[s]+"']").attr("class","input1 bg-percent-img");
		}
	}
	function c(name){
		var names = name.split(",");
		for ( var s in names) {
		$("input[name='"+names[s]+"']").attr("class","part1 mgl10 bg-money-img");
		}
	}
	function d(name){
		var names = name.split(",");
		for ( var s in names) {
		$("input[name='"+names[s]+"']").attr("class","part1 mgl10 bg-percent-img");
		}
	}
	function e(name){
		var names = name.split(",");
		for ( var s in names) {
		$("input[name='"+names[s]+"']").attr("class","input2 bg-money-img");
		}
	}
	function f(name){
		var names = name.split(",");
		for ( var s in names) {
		$("input[name='"+names[s]+"']").attr("class","input2 bg-percent-img");
		}
	}
	function deleteCard(cId){
		console.log(cId);
		$.ajax({
    		type : "POST",
    		dataType : "json",
    		url : basePath + 'memberCard/deleteCard',
    		data: 'cId='+cId,
    		success : function(result) {
    			alert(result.msg);
    			}
    	});
	}
	function updateCard(cId){
		$.ajax({
    		type : "POST",
    		dataType : "json",
    		url : basePath + 'memberCard/updateCard',
    		data: 'cId='+cId,
    		success : function(date) {
    			initUpdate(date);
    			}
    	});
	}
	function initUpdate(date){
		var vipCardInfo = date.vipCardInfo;
		var cutInfo = date.cutInfo;
		var presentInfo = date.presentInfo;
		var recommendInfo = date.recommendInfo;
		var cId = date.cId;
		initVipCardInfo(vipCardInfo);
		/*initCutInfo(cutInfo);
		initPresentInfo(presentInfo);
		initRecommendInfo(recommendInfo);*/
		$("input[name='cId']").val(cId);
		$("#fixhuiyuanka").modal();
	}
	function initVipCardInfo(vipCardInfo){
		$("input[name='cName']").val(vipCardInfo.cName);
		$("input[name='cProjectDiscount']").val(vipCardInfo.cProjectDiscount);
		$("input[name='cGoodsDiscount']").val(vipCardInfo.cGoodsDiscount);
		$("input[name='cMoneyRecharge']").val(vipCardInfo.cMoneyRecharge);
		$("input[name='cMoneyCash']").val(vipCardInfo.cMoneyCash);
		if(vipCardInfo.cIsUseDate == '0'){
			$("button[id='v_c_date']").text("永久");
			$("input[name='cIsUseDate']").val('0');
		 }else{
			 $("button[id='v_c_date']").text(vipCardInfo.cIsUseDate+"个月");
		     $("input[name='cIsUseDate']").val(vipCardInfo.cIsUseDate);
		}
		if(vipCardInfo.cIsMessage == '0'){
			$("input[name='cIsMessage']").attr("checked",false);
		}else{
			$("input[name='cIsMessage']").attr("checked",true);
		}
		$("input[name='cIsMessage']").val(vipCardInfo.cIsMessage);
	}
	function initCutInfo(cutInfo){
		if(cutInfo.kLCType == '按比例'){
			$("button[id='c_i_date']").text("按比例");
			$("input[name='kLCType']").val("按比例");
		}
		
		$("input[name='kLOMoney']").val(cutInfo.kLOMoney);
		$("input[name='ofirst']").val(cutInfo.ofir);
		$("input[name='osecond']").val(cutInfo.osec);
		$("input[name='othree']").val(cutInfo.othr);
		$("input[name='ofour']").val(cutInfo.ofou);
		
		if(cutInfo.cLCType == '按比例'){
			$("button[id='c_y_date']").text("按比例");
			$("input[name='cLCType']").val("按比例");
		}
		
		$("input[name='cLOMoney']").val(cutInfo.cLOMoney);
		$("input[name='tfirst']").val(cutInfo.tfir);
		$("input[name='tsecond']").val(cutInfo.tsec);
		$("input[name='tthree']").val(cutInfo.tthr);
		$("input[name='tfour']").val(cutInfo.tfou);
	}
	function initPresentInfo(presentInfo){
		if(presentInfo.oPGType == '按比例'){
			$("button[id='p_i_data']").text("按比例");
			$("input[name='oPGType']").val("按比例");
		}
		$("input[name='oPGMoney']").val(presentInfo.oPGMoney);
		
		if(presentInfo.oPGDate == '0'){
			$("button[id='p_y_date']").text("一次赠送");
		}else{
			$("button[id='p_y_date']").text("分期"+presentInfo.oPGDate+"个月");
		}
		$("input[name='oPGDate']").val(presentInfo.oPGDate);
		
		if(presentInfo.oPIType == '按比例'){
			$("button[id='p_x_data']").text("按比例");
			$("input[name='oPIType']").val("按比例");
		}
		$("input[name='oPIMoney']").val(presentInfo.oPIMoney);
		if(presentInfo.cPGType == '按比例'){
			$("button[id='p_u_data']").text("按比例");
			$("input[name='cPGType']").val("按比例");
		}
		$("input[name='cPGMoney']").val(presentInfo.cPGMoney);
		
		if(presentInfo.cPGDate == '0'){
			$("button[id='p_o_date']").text("一次赠送");
		}else{
			$("button[id='p_o_date']").text("分期"+presentInfo.cPGDate+"个月");
		}
		$("input[name='cPGDate']").val(presentInfo.cPGDate);
		if(presentInfo.cPIType == '按比例'){
			$("button[id='p_j_data']").text("按比例");
			$("input[name='cPIType']").val("按比例");
		}
		$("input[name='cPIMoney']").val(presentInfo.cPIMoney);
		if(presentInfo.xPIType == '按比例'){
			$("button[id='p_q_data']").text("按比例");
			$("input[name='xPIType']").val("按比例");
		}
		$("input[name='xPIMoney']").val(presentInfo.xPIMoney);
	}
	function initRecommendInfo(recommendInfo){
		if(recommendInfo.zOTType == '按比例'){
			$("button[id='r_a_data']").text("按比例");
			$("input[name='zOTType']").val("按比例");
		}
		$("input[name='zOTMoney']").val(recommendInfo.zOTMoney);
		
		if(recommendInfo.zCTType == '按比例'){
			$("button[id='r_b_data']").text("按比例");
			$("input[name='zCTType']").val("按比例");
		}
		$("input[name='zCTMoney']").val(recommendInfo.zCTMoney);
		
		if(recommendInfo.zXTType == '按比例'){
			$("button[id='r_c_data']").text("按比例");
			$("input[name='zXTType']").val("按比例");
		}
		$("input[name='zXTMoney']").val(recommendInfo.zXTMoney);
		
		if(recommendInfo.jOTType == '按比例'){
			$("button[id='r_d_data']").text("按比例");
			$("input[name='jOTType']").val("按比例");
		}
		$("input[name='jOTMoney']").val(recommendInfo.jOTMoney);
		
		if(recommendInfo.jCTType == '按比例'){
			$("button[id='r_e_data']").text("按比例");
			$("input[name='jCTType']").val("按比例");
		}
		$("input[name='jCTMoney']").val(recommendInfo.jCTMoney);
		
		if(recommendInfo.jXTType == '按比例'){
			$("button[id='r_f_data']").text("按比例");
			$("input[name='jXTType']").val("按比例");
		}
		$("input[name='jXTMoney']").val(recommendInfo.jXTMoney);
	}
	
	/**点击下一步*/
	function change_current(n1,n2){
		$("#l"+n1).attr("class","");
		$("#l"+n2).attr("class","current");
		$("#d"+n1).attr("class","hide");
		$("#d"+n2).attr("class","");
	}
	/**弹出层充值*/
	function modalReset(){
		$("#l1").attr("class","current");
		$("#l2").removeAttr("class");
		$("#l3").removeAttr("class");
		$("#l4").removeAttr("class");
		
		$("#d1").attr("class","");
		$("#d2").attr("class","hide");
		$("#d3").attr("class","hide");
		$("#d4").attr("class","hide");
	}
	
	/**
	 * 分页查询
	 */
	function pageAddNo(){
		 var tempNo = $("input[name='pageNo']").val();
		 tempNo = parseInt(tempNo) + 1;
	        if(tempNo >= totalPage){
	        	tempNo = totalPage;
	        }
	        $("input[name='pageNo']").val(tempNo);
	        selectInfosByPage();
	}
	function pageDelNo(){
        var tempNo = $("input[name='pageNo']").val();
        tempNo = parseInt(tempNo) - 1;
        if(tempNo <= 0){
        	tempNo = 1;
        }
        $("input[name='pageNo']").val(tempNo);
        selectInfosByPage();
        
	}
	function selectInfosByPage(){
		$("#p1").submit();
	}
	function initPageSize(){
		$("#p1_s1").val(pageSize);
	}
	/**批量修改*/
	function initModal(){
		var cIds = "";
		var html = "<label>会员卡名称</label>";
		var checkboxs = $("input[type='checkbox']");
		for ( var s in checkboxs) {
			if(checkboxs[s].id == "totalBoxs" || typeof(checkboxs[s].id)=="undefined")
			{
			}else{
				if(checkboxs[s].checked==true){
					if(cIds == ''){
						cIds += checkboxs[s].id;
					}else{
						cIds += '_'+checkboxs[s].id;
					}
					html += "<input type=\"text\" class=\"width100\" placeholder=\""+checkboxs[s].value+"\" disabled/>";
				}
				
			}
		}
		$("#initName").html(html);
		if(cIds == "")return;
		console.log(cIds);
		$("#cIds").val(cIds);
	}
	 function updateRecommendInfos(){
		 $("#updateRecommendInfos").submit();
	}
	 /**全选事件*/
		function initCheckBoxs(box){
			var checkBoxs =  $("input[type='checkbox']");
			for ( var s in checkBoxs) {
				checkBoxs[s].checked=box.checked;
			}
		}