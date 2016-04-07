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
	function updateCard(cardId){
		$.ajax({
    		type : "POST",
    		dataType : "json",
    		url : basePath + 'memberCard/updateCard',
    		data: 'cardId='+cardId,
    		success : function(date) {
    			initUpdate(date);
    			}
    	});
	}
	function initUpdate(date){
		var memberCardInfo = date.MemberCardInfo;
		var cardId = date.cardId;
		initMemberCardInfo(memberCardInfo);
		$("input[name='cardId']").val(cardId);
		$("#fixhuiyuanka").modal();
	}
	function initMemberCardInfo(memberCardInfo){
		$("input[name='cardName']").val(memberCardInfo.cardName);
		$("input[name='cardProjectDiscount']").val(memberCardInfo.cardProjectDiscount);
		$("input[name='cardGoodsDiscount']").val(memberCardInfo.cardGoodsDiscount);
		$("input[name='cardMoneyRecharge']").val(memberCardInfo.cardMoneyRecharge);
		$("input[name='cardMoneyCash']").val(memberCardInfo.cardMoneyCash);
		if(memberCardInfo.cardIsUseDate == '0'){
			$("button[id='v_c_date']").text("永久");
			$("input[name='cardIsUseDate']").val('0');
		 }else{
			 $("button[id='v_c_date']").text(memberCardInfo.cardIsUseDate+"个月");
		     $("input[name='cardIsUseDate']").val(memberCardInfo.cardIsUseDate);
		}
		if(memberCardInfo.cardIsMessage == '0'){
			$("input[name='cardIsMessage']").attr("checked",false);
		}else{
			$("input[name='cardIsMessage']").attr("checked",true);
		}
		$("input[name='cardIsMessage']").val(memberCardInfo.cardIsMessage);
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
		 console.log(tempNo);
		 tempNo = parseInt(tempNo) + 1 ;
	        if(tempNo >= totalPage){
	        	tempNo = totalPage;
	        }
	        $("input[name='pageNo']").val(tempNo);
	        console.log($("input[name='pageNo']"));
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