$(".appoint").on("click", function(){
    var th = $(this);
    if(th.hasClass("open")){
        th.removeClass("open");
    }else{
        th.addClass("open");
        
    }

});
  var proobj = "";
  var empobj = new Object();
  var proNoClick = "";
  $(function(){
	  
 	 $('.demo2').Tabs({
          event:'click'
      });
     $('.demo2').Tabs({
      event:'click'
     });
    $.ajax({
		url : baseUrl + "payment/action/selectAutoProject",
		type: "post",
		async : false,
		dataType : "json",
		success : function(data){
			/* console.log("data : " + JSON.stringify(data)); */
			if(data.code == 0){
				proobj = data.msg;
				UpWindow(proobj);
			}
		},
     });
    
     //添加键盘按下事件
     document.onkeydown = function(event) {
    		var e = event || window.event || arguments.callee.caller.arguments[0];
    		//按下键盘后焦点位置
    		var focusElement = document.activeElement;
    		
    		supkeydown(e,focusElement);
    		
    		if (e && e.keyCode == 27) { // 按 Esc
    			
    		}
    		if (e && e.keyCode == 113) { // 按 F2
    			
    		}
    		if(e && e.keyCode == 120){ // 按F9
    			copyProject();
    		}
    		if(e && e.keyCode == 32){ // 按  space

    			var arr=new Array();
    			arr=$(".num").text().split("￥");
    			if(Number(arr[1]) == Number($("input[name='qdBill']").val()) + Number($("input[name='cashBill']").val()) +Number($("input[name='cardBill']").val())){
    				accounts();
    			}else{
    				dialog("结账金额不匹配");
    				$("input[name='cashBill']").focus();
    			}
    			
    		}
    		if (e && e.keyCode == 13) {// enter 键
    			console.log(focusElement);
    		    if("supdiv" == $(focusElement).parent().attr("supdiv")){
    		    	if("customerType" == $(focusElement).parent().find("button[supname='supname']").attr("id")){
    	                $("#customerSource").focus();
     			    }
        			
        			if("customerSource" == $(focusElement).parent().find("button[supname='supname']").attr("id")){
    	                $("#customerNum").focus();
     			    }
        			
        			if("customerNum" == $(focusElement).parent().find("button[supname='supname']").attr("id")){
        				$("#customerNum").click();
    	                $("input[name='orderNo']").focus();
     			    }
    		    }
    			if("cashBillLI" == $(focusElement).attr("id")){
    				console.log("eeeerrr");
    				$("#cashBillDropdown").empty();
    				$("#cashBillDropdown").append($(focusElement).text()+"&nbsp;&nbsp;|<span class='caret'></span>")
    			}
    			if("cardBillLI" == $(focusElement).attr("id")){
    				console.log("eeeerrr");
    				$("#cardBillDropdown").empty();
    				$("#cardBillDropdown").append($(focusElement).text()+"&nbsp;&nbsp;|<span class='caret'></span>")
    			}
    			if("prono" == focusElement.name){
    				var type = 0;
    				$.each(proobj,function(i,item){
    			    	if(item != null){
    			    		$.each(item.lproject,function(j,proitem){
    			    			if(proitem != null){
    			    				if(proitem.projectNo == focusElement.value){
    			    					$(focusElement).parent().parent().find("input[name='proid']").val(proitem.projectInfoId);
    			        				$(focusElement).parent().parent().find("input[name='proname']").val(proitem.projectName);
    			        				$(focusElement).parent().parent().find("input[name='proprice']").val(proitem.projectPrice);
    			        				$(focusElement).parent().parent().find("input[name='promoney']").val(proitem.projectPrice);
    			        				type = 1;
    			        				//查找项目对应的员工
    			        				selectEmployee(proitem.projectInfoId);
    								  }
    			    			}
    						   });
    			    	}
    				});
    				if(type == 1){
    					countprice();
    					$(".dropdown-input").css({"display":"none"});
    					var targetElement = $(focusElement).parent().parent().find("input[name='procoupon']");
        				targetElement.focus();
    				}else{
    					dialog("填数据");
    					focusElement.focus();
    				}
    			}
    			if("procoupon" == focusElement.name){
    				var proprice = Number($(focusElement).parent().parent().find("input[name='proprice']").val()) - Number($(focusElement).val());
    				var targetElement = $(focusElement).parent().parent().find("input[name='promoney']");
    				countprice();
    				targetElement.val(proprice);
    				targetElement.focus();
    			}
    			if("promoney" == focusElement.name){
    				var targetElement = getElementIndexValue(focusElement,"semployeeno",0);
    				
    				targetElement.click();
    				targetElement.focus();
					var eamoney = $(document).find("input[name='promoney']");
				    var num = 0;
					for (var i = 0; i < eamoney.length; i++) {
						num = Number(num) + Number($(eamoney[i]).val());
					}
					$(".num").text("￥"+parseInt(num));
    			}
    			
    			if("semployeeno" == focusElement.name){
    				var empvalues = $(focusElement).parents("div[supdiv='supdiv']").find("a[supa = 'supa']");
    				var type = 0;
    				if(focusElement.value != ""){
    					for(var i=0;i< empvalues.length;i++){
    						var display =$(empvalues[i]).css('display');
    						if(display != 'none'){
    							var pvalue = $(empvalues[i]).attr("shows");
    	          				if(pvalue == focusElement.value ){
    	          					$(focusElement).parent().find("input[name = 'semployeeid']").val($(empvalues[i]).attr("heddens"));
    	          					type = 1;
    	     					}
    						}
    					}
    				}else{
    					$(focusElement).parent().find("input[name = 'semployeeid']").val("");
    					type = 1;
    				}
	       			if(type == 1){
	       				$(focusElement).parents(".pro_tr").find("#sappoint").focus();
	       				$(focusElement).parents(".pro_tr").find("#sappoint").click();
	   				}else{
	   					  dialog("请填写合法数据");
	   					  focusElement.focus();
	   				}
    			}
    			
    			if("sappoint" == focusElement.name){
    				var targetElement = getElementWhere(focusElement,"sperformance",getElementIndexSelect(focusElement,"sappoint"));
    				targetElement.focus();
    			}
    			
    			if("sperformance" == focusElement.name){
    				var targetElement = getElementIndexValue(focusElement,"memployeeno",0);
    				
                    targetElement.click();
    				targetElement.focus();
    			}
    			
    			if("memployeeno" == focusElement.name){
    				var empvalues = $(focusElement).parents("div[supdiv='supdiv']").find("a[supa = 'supa']");
    				var type = 0;
    				if(focusElement.value != ""){
    					for(var i=0;i< empvalues.length;i++){
    						var display =$(empvalues[i]).css('display');
    						if(display != 'none'){
    							var pvalue = $(empvalues[i]).attr("shows");
    	          				if(pvalue == focusElement.value ){
    	          					$(focusElement).parent().find("input[name = 'memployeeid']").val($(empvalues[i]).attr("heddens"));
    	          					type = 1;
    	     					}
    						}
    					}
    				}else{
    					$(focusElement).parent().find("input[name = 'memployeeid']").val("");
    					type = 1;
    				}
	       		   if(type == 1){
	       				$(focusElement).parents(".pro_tr").find("#mappoint").focus();
	       				$(focusElement).parents(".pro_tr").find("#mappoint").click();
	   			   }else{
	   					  dialog("请填写合法数据");
	   					  focusElement.focus();
	   			   }
    			}
    			
    			if("mappoint" == focusElement.name){
    				var targetElement = getElementWhere(focusElement,"mperformance",getElementIndexSelect(focusElement,"mappoint"));
    				targetElement.focus();
    			}
    			
    			if("mperformance" == focusElement.name){
    				var targetElement = getElementIndexValue(focusElement,"aemployeeno",0);
    				
                    targetElement.click();
				    targetElement.focus();
    			}
    			
    			if("aemployeeno" == focusElement.name){
    				var empvalues = $(focusElement).parents("div[supdiv='supdiv']").find("a[supa = 'supa']");
    				var type = 0;
    				if(focusElement.value != ""){
    					for(var i=0;i< empvalues.length;i++){
    						var display =$(empvalues[i]).css('display');
    						if(display != 'none'){
    							var pvalue = $(empvalues[i]).attr("shows");
    	          				if(pvalue == focusElement.value ){
    	          					$(focusElement).parent().find("input[name = 'aemployeeid']").val($(empvalues[i]).attr("heddens"));
    	          					type = 1;
    	     					}
    						}
    					}
    				}else{
    					$(focusElement).parent().find("input[name = 'aemployeeid']").val("");
    					type = 1;
    				}
          			
	       		   if(type == 1){
	       				$(focusElement).parents(".pro_tr").find("#aappoint").focus();
	       				$(focusElement).parents(".pro_tr").find("#aappoint").click();
	   			   }else{
	   					  dialog("请填写合法数据");
	   					  focusElement.focus();
	   			   }
    			}
    			
    			if("aappoint" == focusElement.name){
    				var targetElement = getElementWhere(focusElement,"aperformance",getElementIndexSelect(focusElement,"aappoint"));
    				targetElement.focus();
    			}
    			
    			if("aperformance" == focusElement.name){
    				console.log($(focusElement).parents(".pro_tr").next().find("input[name='prono']").attr("name"));
    				if($(focusElement).parents(".pro_tr").next().find("input[name='prono']").attr("name") == null){
    					$("#customerType").focus();
                    }else{
                    	$(focusElement).parents(".pro_tr").next().find("input[name='prono']").focus();
                    	$(focusElement).parents(".pro_tr").next().find("input[name='prono']").click();
                    }
    			}
    			
    			if("orderNo" == focusElement.name){
	                $("input[name='qdBill']").focus();
 			    }
    			
    			if("qdBill" == focusElement.name){
    				if(focusElement.value != ""){
    					modalcode();
    					$('#modelID').on('shown.bs.modal',function(e){
    						$("input[name='qd_code1']").focus(); //通过ID找到对应输入框，让其获得焦点
    					});
    				}else{
    					$("input[name='cashBill']").focus();
    				}
 			    }
    			
    			if("cashBill" == focusElement.name){
	                $("input[name='cardBill']").focus();
 			    }
    			
    		}
       };
       //添加键盘弹起事件
       document.onkeyup = function(event) {
      		var e = event || window.event || arguments.callee.caller.arguments[0];
      		//弹起键盘后焦点位置
      		var focusElement = document.activeElement;
      		
      		if("qd_code1" == focusElement.name){
      			if(focusElement.value != ""){
      				$("input[name='qd_code2']").focus();
      			}
		    }
    		
    		if("qd_code2" == focusElement.name){
    			if(focusElement.value != ""){
      				$("input[name='qd_code3']").focus();
      			}
		    }
    		
    		if("qd_code3" == focusElement.name){
    			if(focusElement.value != ""){
      				$("input[name='qd_code4']").focus();
      			}
		    }
    		
    		if("qd_code4" == focusElement.name){
    			if(focusElement.value != ""){
      				$("input[name='qd_code5']").focus();
      			}
		    }
    		
    		if("qd_code5" == focusElement.name){
    			if(focusElement.value != ""){
      				$("input[name='qd_code6']").focus();
      			}
		    }
    		
    		if("qd_code6" == focusElement.name){
    			var qd_code5 = $(focusElement).parent().parent().find("input[name=qd_code5]").val();
    			var qd_code4 = $(focusElement).parent().parent().find("input[name=qd_code4]").val();
    			var qd_code3 = $(focusElement).parent().parent().find("input[name=qd_code3]").val();
    			var qd_code2 = $(focusElement).parent().parent().find("input[name=qd_code2]").val();
    			var qd_code1 = $(focusElement).parent().parent().find("input[name=qd_code1]").val();
    			var qd_code6 = focusElement.value;
    			var qd_code = String(qd_code1) + String(qd_code2) + String(qd_code3) + String(qd_code4) + String(qd_code5) + String(qd_code6);
    			$.ajax({
					url : baseUrl + "payment/action/checkoutQDcode",
					data : "qd_code="+qd_code,
					type: "post",
					async : false,
					dataType : "json",
					success : function(data){
						if(data.msg == 'no'){
							dialog("授权码错误");
							focusElement.focus();
						}else{
							$('#modelID').modal('hide');
							$("input[name='cashBill']").focus();
							$("input[name='qdEmployeeId']").val(data.msg);//通过ID找到对应输入框，让其获得焦点
						}
					},
			    });
		    }
      		
            if (e && e.keyCode == 8) { // 按 Backspace
            	if("qd_code6" == focusElement.name){
          			$("input[name='qd_code5']").focus();
    		    }
            	if("qd_code5" == focusElement.name){
          			$("input[name='qd_code4']").focus();
    		    }
            	if("qd_code4" == focusElement.name){
          			$("input[name='qd_code3']").focus();
    		    }
            	if("qd_code3" == focusElement.name){
          			$("input[name='qd_code2']").focus();
    		    }
            	if("qd_code2" == focusElement.name){
          			$("input[name='qd_code1']").focus();
    		    }
    		}
    		
      		if("prono" == focusElement.name){
      			prodivno(focusElement.value);
      		}
      		
      		if("proname" == focusElement.name){
      			prodivname(focusElement.value);
      		}
      	};
    	
    });
  
  //向modle删除添加点击事件
  $(".close").click(function(){
	  $('#modelID').modal('hide');
	  $("input[name='qdBill']").focus(); //通过ID找到对应输入框，让其获得焦点

  });
  
  //向签单添加点击事件
  $("input[name='qdBill']").blur(function(e){
	  if($("input[name='qdBill']").val() != ""){
		    modalcode();
			$('#modelID').on('shown.bs.modal',function(e){
				$("input[name='qd_code1']").focus(); //通过ID找到对应输入框，让其获得焦点
			});
       }
  });
  
  //弹出授权码输入框
  function modalcode(){
	    $('#modelID').modal({backdrop: 'static', keyboard: false});
		$('#modelID').modal('show');
		$("input[name='qd_code1']").val("");
		$("input[name='qd_code2']").val("");
		$("input[name='qd_code3']").val("");
		$("input[name='qd_code4']").val("");
		$("input[name='qd_code5']").val("");
		$("input[name='qd_code6']").val("");
  }
  
    function selectEmployee(projectInfoId){
    	$.ajax({
			url : baseUrl + "payment/action/selectAutoEmployee",
			data : "projectInfoId="+projectInfoId,
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){
				if(data.code == 0){
                    var columns = "employeeNo,employeeName";
                    var dropInp = new Array();
                    if($("input[name='proid']").val() == projectInfoId){
                    	dropInp.push($("input[name='proid']").parents(".pro_tr").find("input[name='semployeeno']"));
                    	dropInp.push($("input[name='proid']").parents(".pro_tr").find("input[name='memployeeno']"));
                    	dropInp.push($("input[name='proid']").parents(".pro_tr").find("input[name='aemployeeno']"));
                    }
					assembleData(data.msg,"employeeNo,employeeName","employeeNo,employeeId",dropInp);
				}
			},
	    });
    }
  
    //[input]计算相同tr中位置位置
    function getElementIndex(e,value){
    	 var ea = $(e).parent().parent().parent().parent().parent().find("input[name="+value+"]");
	  	 for (var i = 0; i < ea.length; i++) {
		   if(ea[i] == e){
			  return i;
		   }
	     }
	  	 return 0;
	 }
    
    //[select]计算相同tr中位置位置
    function getElementIndexSelect(e,value){
    	 var ea = $(e).parent().parent().parent().parent().parent().find("select[name="+value+"]");
	  	 for (var i = 0; i < ea.length; i++) {
		   if(ea[i] == e){
			  return i;
		   }
	     }
	  	 return 0;
	 }
     
	
	//向实收金额添加click事件
	$(".tab_box").delegate("input[name='promoney']",'click', function(e){
		var eamoney = $(document).find("input[name='promoney']");
	    var num = 0;
		for (var i = 0; i < eamoney.length; i++) {
			num = Number(num) + Number($(eamoney[i]).val());
		}
		$(".num").text("￥"+parseInt(num));
	});
	
	//向优惠添加click事件
	$(".tab_box").delegate("input[name='procoupon']",'blur', function(e){
		e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target;
		var proprice = Number($(obj).parent().parent().find("input[name='proprice']").val()) - Number($(obj).val());
		var targetElement = $(obj).parent().parent().find("input[name='promoney']");
		targetElement.val(proprice);
		countprice();
	});
	
	//向发型设计师员工编号添加失去焦点事件
	$(".tab_box").delegate("input[name='semployeeno']",'blur', function(e){
		e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target;
		var empvalues = $(obj).parents("div[supdiv='supdiv']").find("a[supa = 'supa']");
		var type = 0;
		if(obj.value != ""){
			for(var i=0;i< empvalues.length;i++){
				var display =$(empvalues[i]).css('display');
				if(display != 'none'){
					var pvalue = $(empvalues[i]).attr("shows");
      				if(pvalue == obj.value ){
      					$(obj).parent().find("input[name = 'semployeeid']").val($(empvalues[i]).attr("heddens"));
      					type = 1;
 					}
				}
			}
		}else{
			$(obj).parent().find("input[name = 'semployeeid']").val("");
			type = 1;
		}
		if(type == 1){
			$(obj).parents(".pro_tr").find("#sappoint").click();
		}else{
			dialog("请填写合法数据");
			obj.focus();
		}
	});
	
	//向美发技师员工编号添加失去焦点事件
	$(".tab_box").delegate("input[name='memployeeno']",'blur', function(e){
		e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target;
		var empvalues = $(obj).parents("div[supdiv='supdiv']").find("a[supa = 'supa']");
		var type = 0;
		if(obj.value != ""){
			for(var i=0;i< empvalues.length;i++){
				var display =$(empvalues[i]).css('display');
				if(display != 'none'){
					var pvalue = $(empvalues[i]).attr("shows");
      				if(pvalue == obj.value ){
      					$(obj).parent().find("input[name = 'memployeeid']").val($(empvalues[i]).attr("heddens"));
      					type = 1;
 					}
				}
			}
		}else{
			$(obj).parent().find("input[name = 'memployeeid']").val("");
			type = 1;
		}
		if(type == 1){
			$(obj).parents(".pro_tr").find("#mappoint").click();
		}else{
			dialog("请填写合法数据");
			obj.focus();
		}
	});
	
	//向美发助理员工编号添加失去焦点事件
	$(".tab_box").delegate("input[name='aemployeeno']",'blur', function(e){
		e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target;
		var empvalues = $(obj).parents("div[supdiv='supdiv']").find("a[supa = 'supa']");
		var type = 0;
		if(obj.value != ""){
			for(var i=0;i< empvalues.length;i++){
				var display =$(empvalues[i]).css('display');
				if(display != 'none'){
					var pvalue = $(empvalues[i]).attr("shows");
      				if(pvalue == obj.value ){
      					$(obj).parent().find("input[name = 'aemployeeid']").val($(empvalues[i]).attr("heddens"));
      					type = 1;
 					}
				}
			}
		}else{
			$(obj).parent().find("input[name = 'aemployeeid']").val("");
			type = 1;
		}
		if(type == 1){
			$(obj).parents(".pro_tr").find("#aappoint").click();
		}else{
			dialog("请填写合法数据");
			obj.focus();
		}
	});
	
    //向项目编号添加click事件
	$(".tab_box").delegate("input[name='prono']",'click', function(e){
		var x= this.getBoundingClientRect().left+document.documentElement.scrollLeft-540.5;

		var y =this.getBoundingClientRect().top+document.documentElement.scrollTop;
	    $(".dropdown-input").css({"display":"block", "left":x, "top":y});
	    $(".arrow").css({"left":x});
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		proNoClick = obj;
		if($(obj).val() ==''){
			$.each(proobj,function(i,item){
				   if(item != null){
					 $.each(item.lproject,function(j,proitem){
						 if(proitem != null){
						     $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','inline-block');
							 $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','inline-block');
						 }
					   });
				   }
			 });
		}else{
			prodivno($(obj).val());
		}
	});
    
	//向项目名称添加click事件
	$(".tab_box").delegate("input[name='proname']",'click', function(e){
		var x= this.getBoundingClientRect().left+document.documentElement.scrollLeft-540.5;

		var y =this.getBoundingClientRect().top+document.documentElement.scrollTop;
	    $(".dropdown-input").css({"display":"block", "left":"10%", "top":y});
	    $(".arrow").css({"left":x});
	    e = e ? e : window.e; 
		var obj = e.srcElement ? e.srcElement : e.target; 
		proNoClick = obj;
		if($(obj).val() ==''){
			$.each(proobj,function(i,item){
				   if(item != null){
					 $.each(item.lproject,function(j,proitem){
						 if(proitem != null){
						     $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','inline-block');
							 $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','inline-block');
						 }
					   });
				   }
			 });
		}else{
			prodivname($(obj).val());
		}
	});
	
	//项目编号弹出框中值是否显示
	function  prodivno(value){
		$.each(proobj,function(i,item){
			   if(item != null){
				 $.each(item.lproject,function(j,proitem){
					 if(proitem != null){
						//循环是否匹配
 					  var pvalue = proitem.projectNo.toString();
 					  if(pvalue.indexOf(value) >= 0){
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','inline-block');
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','inline-block');
 					  }else{
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','none');
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','none');
 					  }
					 }
				   });
			   }
	     });
	}
	
	//项目名称弹出框中值是否显示
	function  prodivname(value){
		$.each(proobj,function(i,item){
			   if(item != null){
				 $.each(item.lproject,function(j,proitem){
					 if(proitem != null){
						//循环是否匹配
 					  var pvalue = proitem.projectName.toString();
 					  if(pvalue.indexOf(value) >= 0){
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','inline-block');
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','inline-block');
 					  }else{
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").css('display','none');
 						  $("span[projectInfoId="+proitem.projectInfoId+"]").next().css('display','none');
 					  }
					 }
				   });
			   }
	     });
	}
	
    //解除项目编号的blur事件
	$(".dropdown-input").mouseover(function(){
		$(".tab_box").undelegate("input[name='prono']","blur");
	});
	
    //添加项目编号的blur
	$(".dropdown-input").mouseout(function(){
		$(".tab_box").delegate("input[name='prono']",'blur', function(event){
		    //检查输入内容，如果内容合法，隐藏弹出层，否则弹出提示，让其选择项目
		    event = event ? event : window.event; 
		    var obj = event.srcElement ? event.srcElement : event.target; 
		    opinion(obj);
		});
	});
	
   //向弹出框项目编号、项目名称绑定click事件
   $(".dropdown-input").delegate("#getitem","click",function(event){
	   getProjectItemValue(event);
	});
   
   //向弹出框项目价格绑定click事件
   $(".dropdown-input").delegate("#getmoney","click",function(event){
	   getProjectMoneyValue(event);
	});
   
   $(".tab_box").delegate("input[name='prono']",'blur', function(event){
	    //检查输入内容，如果内容合法，隐藏弹出层，否则弹出提示，让其选择项目
	    event = event ? event : window.event; 
	    var obj = event.srcElement ? event.srcElement : event.target; 
	    opinion(obj);
	});
   
   //添加项目事件
   $("#project_plus").on("click",function(){
 	    copyProject();
   });
   
   //移除项目事件
   $(".tab_box").delegate("#p_minus","click",function(){
       $(this).parent().parent().parent().parent().parent().parent().remove();
   });
   
   jQuery(window).load(function () {
       $('.piluku-preloader').addClass('hidden');
   });
   
   //结账
   function accounts(){
	   //取出所有项目信息
	   var eapriid = $("input[name='proid']");
	   var eaprono = $("input[name='prono']");
	   var eaproname = $("input[name='proname']");
	   var eaproprice = $("input[name='proprice']");
	   var eaprocoupon = $("input[name='procoupon']");
	   var eapromoney = $("input[name='promoney']");
	   var orderNo = $("input[name='orderNo']").val();
	   var orderAmount = $("input[name='orderAmount']").val();
	   var saleAmountList = $("input[name='orderAmount']");
	   var saleAmount = 0;
	   for (var i = 0; i < saleAmountList.length; i++) {
		   saleAmount = Number(saleAmount) + Number($(saleAmountList[i]).val());
	   }
	   
	   var qdBill = $("input[name='qdBill']").val();
	   var qdAuthorizationCode = $("input[name='qdAuthorizationCode']").val();
	   var cashBill = $("input[name='cashBill']").val();
	   var cardBill = $("input[name='cardBill']").val();
	   var qdEmployeeId = $("input[name='qdEmployeeId']").val();
	   var realIncomeAmount = $(".num").text();
	   var arr=new Array();
	   arr=$(".num").text().split("￥");
	   var orderInfoId = "";
	   $.ajax({
			url : baseUrl + "payment/action/saveOrderInfo",
			data : "orderNo="+orderNo+"&orderAmount="+orderAmount+"&realIncomeAmount="+arr[1]+"&saleAmount="+saleAmount+"&qdBill="+qdBill+"&qdAuthorizationCode="+qdAuthorizationCode+"&cashBill="+cashBill+"&cardBill="+cardBill+"&qdEmployeeId="+qdEmployeeId,
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){
				orderInfoId = data.msg;
			},
	    });
	   var arr = new Array();
	   for(var i = 0; i < eapriid.length; i++){
		  var eaproobj = new Object();
		  if($(eapriid[i]).val() != null && $(eaprono[i]).val() != null){
			  eaproobj["priid"] = $(eapriid[i]).val();
			  eaproobj["prono"] = $(eaprono[i]).val();
			  eaproobj["proname"] = $(eaproname[i]).val();
			  eaproobj["proprice"] = $(eaproprice[i]).val();
			  eaproobj["procoupon"] = $(eaprocoupon[i]).val();
			  eaproobj["promoney"] = $(eapromoney[i]).val();
			  eaproobj["orderInfoId"] = orderInfoId;
			  //单项目下所有员工信息
			  var easemployeeid = $(eapriid[i]).parents(".pro_tr").find("input[name='semployeeid']");
			  var easappoint = $(eapriid[i]).parents(".pro_tr").find("select[name='sappoint']");
			  var easperformance = $(eapriid[i]).parents(".pro_tr").find("input[name='sperformance']");
			  var easallot = $(eapriid[i]).parents(".pro_tr").find("input[name='sallot']");
			  var sarr = new Array();
			  for(var j = 0; j < easemployeeid.length; j++){
				  var sproobj = new Object();
				  sproobj["semployeeid"] = $(easemployeeid[j]).val();
				  sproobj["sappoint"] = $(easappoint[j]).val();
				  sproobj["sperformance"] = $(easperformance[j]).val();
				  sproobj["sallot"] = $(easallot[j]).val();
				  sarr.push(sproobj);
			  }
			  eaproobj["sarr"] = sarr;
			  
			  var eamemployeeid = $(eapriid[i]).parents(".pro_tr").find("input[name='memployeeid']");
			  var eamappoint = $(eapriid[i]).parents(".pro_tr").find("select[name='mappoint']");
			  var eamperformance = $(eapriid[i]).parents(".pro_tr").find("input[name='mperformance']");
			  var eamallot = $(eapriid[i]).parents(".pro_tr").find("input[name='mallot']");
			  var marr = new Array();
			  for(var j = 0; j < eamemployeeid.length; j++){
				  var mproobj = new Object();
				  mproobj["memployeeid"] = $(eamemployeeid[j]).val();
				  mproobj["mappoint"] = $(eamappoint[j]).val();
				  mproobj["mperformance"] = $(eamperformance[j]).val();
				  mproobj["mallot"] = $(eamallot[j]).val();
				  marr.push(mproobj);
			  }
			  eaproobj["marr"] = marr;
			  
			  var eaaemployeeid = $(eapriid[i]).parents(".pro_tr").find("input[name='aemployeeid']");
			  var eaaappoint = $(eapriid[i]).parents(".pro_tr").find("select[name='aappoint']");
			  var eaaperformance = $(eapriid[i]).parents(".pro_tr").find("input[name='aperformance']");
			  var eaaallot = $(eapriid[i]).parents(".pro_tr").find("input[name='aallot']");
			  var aarr = new Array();
			  for(var j = 0; j < eaaemployeeid.length; j++){
				  var aproobj = new Object();
				  aproobj["aemployeeid"] = $(eaaemployeeid[j]).val();
				  aproobj["aappoint"] = $(eaaappoint[j]).val();
				  aproobj["aperformance"] = $(eaaperformance[j]).val();
				  aproobj["aallot"] = $(eaaallot[j]).val();
				  aarr.push(aproobj);
			  }
			  eaproobj["aarr"] = aarr;
			  arr.push(eaproobj);
		  }
	   }
	   $.ajax({
			url : baseUrl + "payment/action/getProjectEmployeerelation",
			data : "projectMassege="+JSON.stringify(arr),
			type: "post",
			async : false,
			dataType : "json",
			success : function(data){

			},
	    });
   }
   
   function opinion(obj){
	    var type = 0;
	    $.each(proobj,function(i,item){
	    	if(item != null){
	    		$.each(item.lproject,function(j,proitem){
	    			if(proitem != null){
	    				if(proitem.projectNo == $(obj).parent().parent().find("input[name=prono]").val() 
								  && proitem.projectName == $(obj).parent().parent().find("input[name=proname]").val() 
							      && proitem.projectPrice == $(obj).parent().parent().find("input[name=proprice]").val() 
							      && proitem.projectInfoId == $(obj).parent().parent().find("input[name=proid]").val()){
							  $(".dropdown-input").css({"display":"none"});
							  type = 1;
						  }
	    			}
				   });
	    	}
		});
	    if(type == 1){
	    	$(".dropdown-input").css({"display":"none"});
	    	return "Y";
	    }else{
	    	dialog("数据不合法");
	    	obj.focus();
	    	return "N";
	    }
   }
   
   function UpWindow(objwin){
	   var ul = $("<ul></ul>");
	   $.each(objwin,function(i,item){
		   if(item != null){
			   var proitem = item.lproject;
			   console.log("soosos : "+Math.ceil(proitem.length/5));
			   for(var k=1;k <= Math.ceil(proitem.length/5);k++){
				   if(k ==1){
					   var li = $("<li></li>");
					   li.append("<label>"+item.classesName+"</label>");
					   for(var j= 0;j < 5 *Number(k);j++){
						   if(proitem[j] != null){
							   li.append("<span class='item' id='getitem' projectInfoId ='"+proitem[j].projectInfoId+"' style='display: inline-block;'>"+proitem[j].projectNo+"_"+proitem[j].projectName+"</span><span class='money' id='getmoney' style='display: inline-block;'>"+proitem[j].projectPrice+"元</span>");
						   }
					   }
					   ul.append(li);
				   }else if(1 < k < Math.ceil(proitem.length/5)){
					   var li = $("<li></li>");
					   for(var j=(5 *Number(k- 1));j < 5 *Number(k);j++){
						   if(proitem[j] != null){
							   li.append("<span class='item' id='getitem' projectInfoId ='"+proitem[j].projectInfoId+"' style='display: inline-block;'>"+proitem[j].projectNo+"_"+proitem[j].projectName+"</span><span class='money' id='getmoney' style='display: inline-block;'>"+proitem[j].projectPrice+"元</span>");
						   }
					   }
					   ul.append(li);
				   }else if(k == Math.ceil(proitem.length/5)){
					   var li = $("<li></li>");
					   for(var j=(5 *Number(k) - 1);j < proitem.length;j++){
						   if(proitem[j] != null){
							   li.append("<span class='item' id='getitem' projectInfoId ='"+proitem[j].projectInfoId+"' style='display: inline-block;'>"+proitem[j].projectNo+"_"+proitem[j].projectName+"</span><span class='money' id='getmoney' style='display: inline-block;'>"+proitem[j].projectPrice+"元</span>");
						   }
					   }
					   ul.append(li);
				   }
			   }
		   }
	   });
	   $("#upWindow").append(ul);
   }
   
   function search(content){
	   for(var e in eleArray){
		   var d = eleArray[e].data;
		   if(d.name.indexOf(content) < 0){
			   d.display = "none";
		   }
	   }
   }
   
   function getProjectItemValue(event){
	   event = event ? event : window.event; 
	   var obj = event.srcElement ? event.srcElement : event.target;
	   var arr=new Array();
	   arr=$(obj).text().split("_");
	   var priarr=new Array();
	   priarr=$(obj).next().text().split("元");
	   $(proNoClick).parent().parent().find("input[name='proid']").val($(obj).attr("projectInfoId"));
	   $(proNoClick).parent().parent().find("input[name='prono']").val(arr[0]);
	   $(proNoClick).parent().parent().find("input[name='proname']").val(arr[1]);
	   $(proNoClick).parent().parent().find("input[name='proprice']").val(priarr[0]);
	   $(proNoClick).parent().parent().find("input[name='promoney']").val(priarr[0]);
	   selectEmployee($(obj).attr("projectInfoId"));
	   $("#upWindow").css("display","none");
	   var targetElement = $(proNoClick).parent().parent().find("input[name='procoupon']");
	   targetElement.focus();
	   countprice();
   }
   
 //计算订单金额和实际金额
   function countprice(){
	   var eaprice = $(document).find("input[name='proprice']");
	   var eamoney = $(document).find("input[name='promoney']");
		var num = 0;
		var orderAmount = 0;
		for (var i = 0; i < eamoney.length; i++) {
			num = Number(num) + Number($(eamoney[i]).val());
		}
		for(var i = 0; i < eaprice.length; i++){
			orderAmount = Number(orderAmount) + Number($(eaprice[i]).val());
		}
		$(".num").text("￥"+parseInt(num));
		$("input[name='orderAmount']").val(parseInt(orderAmount));
   }
   
   function getProjectMoneyValue(event){
	   event = event ? event : window.event; 
	   var obj = event.srcElement ? event.srcElement : event.target; 
	   var arr=new Array();
	   arr=$(obj).prev().text().split("_");
	   var priarr=new Array();
	   priarr=$(obj).text().split("元");
	   $(proNoClick).parent().parent().find("input[name='proid']").val($(obj).prev().attr("projectInfoId"));
	   $(proNoClick).parent().parent().find("input[name='prono']").val(arr[0]);
	   $(proNoClick).parent().parent().find("input[name='proname']").val(arr[1]);
	   $(proNoClick).parent().parent().find("input[name='proprice']").val(priarr[0]);
	   $(proNoClick).parent().parent().find("input[name='promoney']").val(priarr[0]);
	   selectEmployee($(obj).prev().attr("projectInfoId"));
	   $("#upWindow").css("display","none");
	   var targetElement = $(proNoClick).parent().parent().find("input[name='procoupon']");
	   targetElement.focus();
	   countprice();
   }
   
   function copyProject(){
        $("#hair_class").append($("#projectBody").html());
    }	
   
 //解除向发型设计师员工编号的blur事件
	$(".tab_box").delegate(".dropdown-menu","mouseover",function(e){
		$(".tab_box").undelegate("input[name='semployeeno']","blur");
	});
	
	//解除美发技师员工编号的blur事件
	$(".tab_box").delegate(".dropdown-menu","mouseover",function(e){
		$(".tab_box").undelegate("input[name='memployeeno']","blur");
	});
	
	//解除员工编号的blur事件
	$(".tab_box").delegate(".dropdown-menu","mouseover",function(e){
		$(".tab_box").undelegate("input[name='aemployeeno']","blur");
	});
	
   //添加向发型设计师员工编号的blur
	$(".tab_box").delegate(".dropdown-menu","mouseout",function(){
		//向发型设计师员工编号添加失去焦点事件
		$(".tab_box").delegate("input[name='semployeeno']",'blur', function(e){
			e = e ? e : window.e; 
			var obj = e.srcElement ? e.srcElement : e.target;
			var empvalues = $(obj).parents("div[supdiv='supdiv']").find("a[supa = 'supa']");
			var type = 0;
			if(obj.value != ""){
				for(var i=0;i< empvalues.length;i++){
					var display =$(empvalues[i]).css('display');
					if(display != 'none'){
						var pvalue = $(empvalues[i]).attr("shows");
	      				if(pvalue == obj.value ){
	      					$(obj).parent().find("input[name = 'semployeeid']").val($(empvalues[i]).attr("heddens"));
	      					type = 1;
	 					}
					}
				}
			}else{
				$(obj).parent().find("input[name = 'semployeeid']").val("");
				type = 1;
			}
			if(type == 1){
				$(obj).parents(".pro_tr").find("#sappoint").click();
			}else{
				dialog("请填写合法数据");
				obj.focus();
			}
		});
	});
   
	//添加向美发技师员工编号的blur
	$(".tab_box").delegate(".dropdown-menu","mouseout",function(){
		//向美发技师员工编号添加失去焦点事件
		$(".tab_box").delegate("input[name='memployeeno']",'blur', function(e){
			e = e ? e : window.e; 
			var obj = e.srcElement ? e.srcElement : e.target;
			var empvalues = $(obj).parents("div[supdiv='supdiv']").find("a[supa = 'supa']");
			var type = 0;
			if(obj.value != ""){
				for(var i=0;i< empvalues.length;i++){
					var display =$(empvalues[i]).css('display');
					if(display != 'none'){
						var pvalue = $(empvalues[i]).attr("shows");
	      				if(pvalue == obj.value ){
	      					$(obj).parent().find("input[name = 'memployeeid']").val($(empvalues[i]).attr("heddens"));
	      					type = 1;
	 					}
					}
				}
			}else{
				$(obj).parent().find("input[name = 'memployeeid']").val("");
				type = 1;
			}
			if(type == 1){
				$(obj).parents(".pro_tr").find("#mappoint").click();
			}else{
				dialog("请填写合法数据");
				obj.focus();
			}
	   });
    });	
	//添加向美发助理员工编号的blur
	$(".tab_box").delegate(".dropdown-menu","mouseout",function(){
		//向美发助理员工编号添加失去焦点事件
		$(".tab_box").delegate("input[name='aemployeeno']",'blur', function(e){
			e = e ? e : window.e; 
			var obj = e.srcElement ? e.srcElement : e.target;
			var empvalues = $(obj).parents("div[supdiv='supdiv']").find("a[supa = 'supa']");
			var type = 0;
			if(obj.value != ""){
				for(var i=0;i< empvalues.length;i++){
					var display =$(empvalues[i]).css('display');
					if(display != 'none'){
						var pvalue = $(empvalues[i]).attr("shows");
	      				if(pvalue == obj.value ){
	      					$(obj).parent().find("input[name = 'aemployeeid']").val($(empvalues[i]).attr("heddens"));
	      					type = 1;
	 					}
					}
				}
			}else{
				$(obj).parent().find("input[name = 'aemployeeid']").val("");
				type = 1;
			}
			if(type == 1){
				$(obj).parents(".pro_tr").find("#aappoint").click();
			}else{
				dialog("请填写合法数据");
				obj.focus();
			}
		});
	});