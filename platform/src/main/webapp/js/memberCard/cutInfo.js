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
		$("#cIds").val(cIds);
	}
	 function updateCutInfos(){
		 $("#updateCutInfos").submit();
	}
	 /**全选事件*/
	function initCheckBoxs(box){
		var checkBoxs =  $("input[type='checkbox']");
		for ( var s in checkBoxs) {
			checkBoxs[s].checked=box.checked;
		}
	}
