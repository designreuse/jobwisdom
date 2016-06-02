// Download by http://www.jb51.net
jQuery.fn.extend({
  showTree:function(opt){
  	opt=opt||{};
  	var showDataObj=jQuery(this).prev();
  	var fn,bindings,title,callbackAny,inputFlag;
  	var _self=this;
  	var dataList=opt.data||{};
  	opt.callback?fn=opt.callback:fn=function(t,o){
  		showDataObj.val(jQuery(t).html());
  		jQuery.rmwin();
  	};
  	opt.bindings?bindings=opt.bindings:null;
  	opt.callbackAny?callbackAny=opt.callbackAny:callbackAny=false;
  	opt.inputFlag?inputFlag=opt.inputFlag:inputFlag=false;
  	opt.title?title=opt.title:title='选择分类';
  	var width=opt.width||210;
  	var height=opt.height||460;
  	var funcBefore=opt.before||function(){};
  	if(opt.alert=='alert'){
    	jQuery(this).click(function(){
				var html='<div  style="height:450px;width:200px;overflow:auto"><ul class="treeview filetree" id="showTreeSelectParent">';
				jQuery.each(dataList,function(i,n){
					if(n.pgid==0){
						html+='<li class="expandable"><div class="hitarea"></div><span class="folder" val="'+n.id+'" loaded="false">'+n.name+'</span></li>';
					}
				});
				html+='</ul></div>';
				html=jQuery(html);
				var win=jQuery.win({title:title,html:html,width:width,height:height});
				initTree(jQuery('#showTreeSelectParent'));
				funcBefore(html);
			});
		}
		else{
			jQuery.each(dataList,function(i,n){
				if(n.pgid==0){
					jQuery(_self).append('<li class="expandable"><div class="hitarea"></div><span class="folder" val="'+n.id+'" loaded="false">'+n.name+'</span></li>');
				}
			});
			initTree(jQuery(_self));
		}
		if(!inputFlag){
	  	showDataObj.focus(function(){
	  		jQuery(this).next().click();
	  	});
  	}
  	function initTree(t){
			t.find('span[loaded=false]').each(function(){
				if(bindings){
					jQuery(this).unbind('contextmenu').contextMenu(this,'myTreeMenuGroup',{
						bindings:bindings,
						clickFlag:true
					});
				}
				
				var id=jQuery(this).attr('val');
				var flag=false;
				//判断是不是有子元素
				jQuery.each(dataList,function(i,n){
					if(n.pgid==id){
						flag=true;
						return false;
					}
				});
				//没有子元素
				if(!flag){
					jQuery(this).unbind('click').bind('click',function(){
						fn(this,_self);
					}).attr('loaded','true').removeClass('folder').addClass('file');
					if(jQuery(this).parent().next().html()){
						jQuery(this).parent().removeClass();
					}
					else{
						jQuery(this).parent().removeClass().addClass('last');
					}
					return true;
				}
				
				jQuery(this).bind('click',this,addChindList).toggle(function(){
					var o=jQuery(this).parent();
					o.find('>ul').show();
					if(o.hasClass('lastExpandable')){
						o.addClass('lastCollapsable').removeClass('lastExpandable');
					}
					else{
						o.addClass('collapsable').removeClass('expandable');
					}
					o.siblings().each(function(){
						if(jQuery(this).hasClass('collapsable')||jQuery(this).hasClass('lastCollapsable')){
							jQuery(this).find('>span:first').click();
						}
					});
				},function(){
					var o=jQuery(this).parent();
					o.find('>ul').hide();
					if(o.hasClass('lastCollapsable')){
						o.addClass('lastExpandable').removeClass('lastCollapsable');
					}
					else{
						o.addClass('expandable').removeClass('collapsable');
					}
				});
				jQuery(this).prev().bind('click',function(){
					jQuery(this).next().click();
				});
			});
			var o=t.find('>ul >li:last');
			(o.html()==null)?o=t.find('>li:last'):null;
			if(o.hasClass('expandable')){
				o.addClass('lastExpandable').removeClass('expandable');
			}
			else{
				o.addClass('lastCollapsable').removeClass('collapsable');
			}
			t.find('.last').removeClass().addClass('last');
		}
		function addChindList(evt){
			if(callbackAny) {
				fn(evt.data,_self);
			}
			var id=jQuery(evt.data).attr('val');
			var str='';
			jQuery.each(dataList,function(i,n){
				if(n.pgid==id){
					str+='<li class="expandable"><div class="hitarea"></div><span class="folder" val="'+n.id+'" loaded="false">'+n.name+'</span></li>';
				}
			});
			if(str){
				jQuery(evt.data).parent().append('<ul>'+str+'</ul>');
			}
			jQuery(evt.data).attr('loaded','true');
			jQuery(evt.data).unbind('click',addChindList);
			initTree(jQuery(evt.data).parent());
		}	
  }
});
