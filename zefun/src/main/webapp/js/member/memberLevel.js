jQuery(function() {
	/*表头置顶*/
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
   table(); 
});

function saveSelection() {
    if (window.getSelection) {
        sel = window.getSelection();
        if (sel.getRangeAt && sel.rangeCount) {
            return sel.getRangeAt(0);
        }
    } else if (document.selection && document.selection.createRange) {
        return document.selection.createRange();
    }
    return null;
}

var r = null;
jQuery(function(){
    jQuery('#editor_id').focus();
    r = saveSelection();
    window.scrollTo(0, 0);
});

function setEndOfContenteditable(){
    var contentEditableElement = jQuery('#editor_id')[0];
    var range = r,selection;
    if(document.createRange)//Firefox, Chrome, Opera, Safari, IE 9+
    {
        range.selectNodeContents(contentEditableElement);//Select the entire contents of the element with the range
        range.collapse(false);//collapse the range to the end point. false means collapse to end rather than the start
    }
    else if(document.selection)//IE 8 and lower
    {
        range.moveToElementText(contentEditableElement);//Select the entire contents of the element with the range
        range.collapse(false);//collapse the range to the end point. false means collapse to end rather than the start
        range.select();//Select the range (make it the visible selection
    }
}

function insertHtmlAtCaret(html) {
    var range = r;
    if (window.getSelection) {
        if (sel.getRangeAt && sel.rangeCount) {
            range.deleteContents();
            var el = document.createElement("div");
            el.innerHTML = html;
            var frag = document.createDocumentFragment(), node, lastNode;
            while ( (node = el.firstChild) ) {
                lastNode = frag.appendChild(node);
            }
            range.insertNode(frag);
            if (lastNode) {
                range = range.cloneRange();
                range.setStartAfter(lastNode);
                range.collapse(true);
                sel.removeAllRanges();
                sel.addRange(range);
            }
        }
    } else if (document.selection && document.selection.type != "Control") {
        range.pasteHTML(html);
    }
    setEndOfContenteditable();
    r = saveSelection();
}

jQuery(document).ready(function(){
	jQuery(document).delegate('#editor_id', 'mouseup', function(e){
	    if(e.target.id != 'editor_id') {
	        return false;
	    }
	    r = saveSelection();
	});
	
	jQuery(document).delegate('#editor_id', 'keyup', function(e){
	    if(e.target.id != 'editor_id') {
	        return false;
	    }
	    r = saveSelection();
	});

	jQuery('#editor_id').on('paste', function( evt ) {
	    var ohml = jQuery(this).html();
	    evt.preventDefault();
	    var c = "";
	    try {
	    	c = evt.originalEvent.clipboardData.getData('Text');
	    } catch(e) {
		    c = window.clipboardData.getData('Text');
	    }

	    var vs = c.split('\n');
	    for (var i = 0; i < vs.length; i++) {
	    	var vv = vs[i];
	    	insertHtmlAtCaret('<div>' + vv + '</div>');
		}
	    return false;
	});
});

//===================内容编辑区域处理结束=============================================================================================================================

//点击新增时的处理
function showAddView(){
	jQuery(".editMemberLevelForm")[0].reset();
	jQuery('#add-member-card').modal({show:true, backdrop:"static"});
}

//提交会员等级信息，存在等级标识则修改，不存在则新增
function addOrEditMemberLevel(){
	var projectDiscountValue = jQuery("input[name='projectDiscount']").val();
	var goodsDiscountValue = jQuery("input[name='goodsDiscount']").val();
	var performanceDiscountPercentValue = jQuery("input[name='performanceDiscountPercent']").val();
	var sellAmountValue = jQuery("input[name='sellAmount']").val();
	var chargeMinMoneyValue = jQuery("input[name='chargeMinMoney']").val();
	var levelName = jQuery("input[name='levelName']").val();
	var cashDiscountType = jQuery("[name='cashDiscountType']").val();
	var levelId = jQuery("[name='levelId']").val();
	var integralUnit = jQuery("[name='integralUnit']").val();
	var integralNumber = jQuery("[name='integralNumber']").val();
	
	if (isEmpty(integralUnit)) {
		integralUnit = 0;
	}
	if (isEmpty(integralNumber)) {
		integralNumber = 0;
	}
	
	if(levelName.length == 0) {
		dialog("请填写会员卡名称");
		return;
	}
	//判断项目折扣
	if(projectDiscountValue.length == 0) {
		dialog("请填写项目折扣");
		return;
	} else if(projectDiscountValue == 0) {
		dialog("折扣不能为0,不打折请设置为100");
		return;
	} else if(parseInt(projectDiscountValue) != projectDiscountValue) {
		dialog("项目折扣为1-100内的整数");
		return;
	} else if(projectDiscountValue < 0 || projectDiscountValue > 100) {
		dialog("项目折扣为1-100内的整数");
		return;
	}
	//判断商品折扣
	if(goodsDiscountValue.length == 0) {
		dialog("请填写商品折扣");
		return;
	} else if(goodsDiscountValue == 0) {
		dialog("折扣不能为0,不打折请设置为100");
		return;
	} else if(parseInt(goodsDiscountValue) != goodsDiscountValue) {
		dialog("商品折扣为1-100内的整数");
		return;
	} else if(goodsDiscountValue < 0 || goodsDiscountValue > 100) {
		dialog("商品折扣为1-100内的整数");
		return;
	}
	//判断业绩折扣比例
	if(performanceDiscountPercentValue.length == 0) {
		dialog("业绩折扣比例为0-100内整数");
		return;
	} else if(parseInt(performanceDiscountPercentValue) != performanceDiscountPercentValue) {
		dialog("业绩折扣比例请输入整数");
		return;
	} else if(performanceDiscountPercentValue < 0 || performanceDiscountPercentValue > 100) {
		dialog("业绩折扣比例为0-100内整数");
		return;
	}
	//现金售卡
	if (sellAmountValue.length == 0) {
		dialog("请填写开卡费用");
		return;
	} else if(sellAmountValue < 0) {
		dialog("开卡费用只能为整数");
		return;
	}
	//最低充值
	if (chargeMinMoneyValue.length == 0) {
		dialog("请填写最低充值");
		return;
	} else if(chargeMinMoneyValue < 0) {
		dialog("'最低充值'填写错误");
		return;
	}
	
	var contents = new Array();
	var it_contents = function(cs){
	    cs.each(function(){
	        if(this.nodeName.toLowerCase() == '#text') {
	            if(this.data && jQuery.trim(this.data)) {
	                contents.push(this.data + "\001" + '1');
	            }
	        } else if(this.nodeName.toLowerCase() == 'div') {
	            it_contents(jQuery(this).contents());
	        } else if(this.nodeName.toLowerCase() == 'img') {
	            var s = jQuery(this).attr('src').replace(picUrl, '');
	            contents.push(s + "\001" + '2');
	        }
	    });
	};
	it_contents(jQuery('#editor_id').contents());
	
	var data = {};
	data["levelId"] = levelId;
	data["levelName"] = levelName;
	data["projectDiscount"] = projectDiscountValue;
	data["goodsDiscount"] = goodsDiscountValue;
	data["performanceDiscountPercent"] = performanceDiscountPercentValue;
	data["sellAmount"] = sellAmountValue;
	data["chargeMinMoney"] = chargeMinMoneyValue;
	data["cashDiscountType"] = cashDiscountType;
	data["integralUnit"] = integralUnit;
	data["integralNumber"] = integralNumber;
	
	if(contents.length > 0) {
	    data["levelNoticeArr"] = contents;
	}
	
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/add",
		contentType: "application/json",
        data: JSON.stringify(data),
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("提交成功,即将刷新页面...");
			resetForm(".editMemberLevelForm");
			jQuery("#editor_id").html("");
			pageNo = 1;
			changePage();
			unbuildPagination();
		}
	});
}

function czupdateDeptInfoId(obj){
	var deptId = jQuery(obj).val();
	var deptName = jQuery(obj).find("option[value='"+deptId+"']").text();
	jQuery(obj).closest('td').append("<div class='p-part'>"+
				                        "<div class='select-result'>"+
				                            "<span name='czDeptId' value='"+deptId+"'>"+deptName+"：</span>"+
				                            "<input type='text'/><span class='percent-symbol'>元</span>"+
				                            "<span onclick='czdeleteDept(this)'>&nbsp;&nbsp;X</span>"+
				                        "</div>"+
				                   "</div>");
	jQuery(obj).find("option[value='"+deptId+"']").remove();
	jQuery(obj).trigger("liszt:updated");
}

function czdeleteDept(obj){
	var deptId = jQuery(obj).parents(".p-part").find("span[name='czDeptId']").attr("value");
	var deptName = jQuery.trim((jQuery(obj).parents(".p-part").find("span[name='czDeptId']").text()).replace("：",""));
	
	jQuery(obj).parents(".p-part").remove();
	var select = jQuery(obj).closest("td").find('select');
	
	select.append("<option value='"+deptId+"'>"+deptName+"</option>");
	select.trigger("liszt:updated");
}

//修改会员等级信息
function editMemberLevel(levelId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/info",
		data : "levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			jQuery(".editMemberLevelForm [type=reset]").trigger("click");
			dataToFormByName(e.msg);
			
			var list = e.msg.levelNoticeList;
			var content = jQuery("#editor_id");
			if (list != null) {
				for (var i = 0; i < list.length; i++) {
					content.append("<div>" + list[i].text + "</div>");
				}
			}
		}
	});
}

//删除会员等级信息
function deleteMemberLevel(levelId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/delete",
		data : "levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			jQuery(".member-card-table tr[id='" + levelId + "']").fadeOut(500).remove();
		}
	});
}


//设置默认会员等级
function setDefaultLevel(levelId){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/default",
		data : "levelId=" + levelId,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			dialog("提交成功,即将刷新页面...");
			pageNo = 1;
			changePage();
		}
	});
}

//分页处理
function changePage(){
	jQuery.ajax({
		type : "post",
		url : baseUrl + "memberLevel/action/list",
		data : "pageNo=" + pageNo + "&pageSize=" + pageSize,
		dataType : "json",
		success : function(e){
			if(e.code != 0){
				dialog(e.msg);
				return;
			}
			refreshTableData(e.msg);
			totalRecord = e.msg.totalRecord;
		}
	});
}

//刷新表格数据
function refreshTableData(page){
	pageNo = page.pageNo;
	pageSize = page.pageSize;
	totalPage = page.totalPage;
	
	var memberLevelList = page.results;
	var tbody = document.createElement("tbody");
	for (var i = 0; i < memberLevelList.length; i++) {
		var memberLevel = memberLevelList[i];
		
		var tr = document.createElement("tr");
		tr.setAttribute("id", memberLevel.levelId);
		
		var levelNameTd = document.createElement("td");
		levelNameTd.innerHTML = memberLevel.levelName;
		if (memberLevel.isDefault == 1) {
			levelNameTd.innerHTML = memberLevel.levelName + ' <span class="font-999">默认等级</span>';
		}
		tr.appendChild(levelNameTd);
		
		var projectDiscountTd = document.createElement("td");
		projectDiscountTd.innerHTML = memberLevel.projectDiscount + "%";
		tr.appendChild(projectDiscountTd);
		
		var goodsDiscountTd = document.createElement("td");
		goodsDiscountTd.innerHTML = memberLevel.goodsDiscount + "%";
		tr.appendChild(goodsDiscountTd);
		
		var chargeMinMoneyTd = document.createElement("td");
		chargeMinMoneyTd.innerHTML = memberLevel.chargeMinMoney + "元";
		tr.appendChild(chargeMinMoneyTd);
		
		var sellAmountTd = document.createElement("td");
		sellAmountTd.innerHTML = memberLevel.sellAmount + "元";
		tr.appendChild(sellAmountTd);
		
		var sellAmountTd = document.createElement("td");
		if (memberLevel.cashDiscountType == 0) {
			sellAmountTd.innerHTML = "不打折";
		} else {
			sellAmountTd.innerHTML = "打折";
		}
		tr.appendChild(sellAmountTd);
		
		//业绩折扣比例
		var performanceDiscountPercentTd = document.createElement("td");
		if (memberLevel.performanceDiscountPercent === null) {
			performanceDiscountPercentTd.innerHTML = 0 + "%";
		} else {
			performanceDiscountPercentTd.innerHTML = memberLevel.performanceDiscountPercent + "%";
		}
		tr.appendChild(performanceDiscountPercentTd);
		
		var integralNumberTd = document.createElement("td");
		integralNumberTd.innerHTML = memberLevel.integralUnit + "元 = " + memberLevel.integralNumber + " 积分";
		tr.appendChild(integralNumberTd);
		
		//等级描述
		var levelNoticeTd = document.createElement("td");
		var levelNotice = '';
		if (memberLevel.levelNoticeList != null) {
			for (var i = 0; i < memberLevel.levelNoticeList.length; i++) {
				levelNotice += memberLevel.levelNoticeList[i].text + " ";
			}
		}
		levelNoticeTd.innerHTML = levelNotice + "&nbsp;";
		levelNoticeTd.setAttribute("class", "input80 ellipsis-text");
		tr.appendChild(levelNoticeTd);
        
		if(hasModify == 1) {
			var operateTd = document.createElement("td");
			//根据权限页面显示修改按钮
			var editSpan = document.createElement("span");
			editSpan.setAttribute("class", "iconfont icon-dizhixiugai");
			editSpan.setAttribute("onclick", "editMemberLevel(" + memberLevel.levelId + ")");
			operateTd.appendChild(editSpan);
			//根据权限页面显示删除按钮(默认等级任何等级都不能删除)
			console.log("memberLevel.is_default: " + memberLevel.isDefault);
			if (parseInt(memberLevel.isDefault) != 1) {
				var removeSpan = document.createElement("span");
				removeSpan.setAttribute("class", "iconfont icon-xx ml10");
				removeSpan.setAttribute("onclick", "deleteMemberLevel(" + memberLevel.levelId + ")");
				operateTd.appendChild(removeSpan);
			}
			tr.appendChild(operateTd);
		}
		
		tbody.appendChild(tr);
	}
	jQuery(".member-card-table tbody").remove();
	jQuery(".member-card-table").append(tbody);
}