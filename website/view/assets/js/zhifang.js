/*loading*/
var oldOnload = window.onload;
window.onload = function () {
  jQuery('.loading').hide();
  jQuery("#mainwrapper").fadeIn();
  if (oldOnload !== null) {
    oldOnload();
  }
};

jQuery(document).ready(function () {
  /*jQuery('.loading').fadeOut();
   console.log("loading");*/
  /*tooltip*/
  jQuery('[data-toggle="tooltip"]').tooltip();

  /*模态框中点击选择图片跳到选择图片的页面*/
  jQuery(".select-img-btn").on("click", function () {
    jQuery(".add-project-main").hide();
    jQuery(".return").show();
    jQuery(".alternative-img").fadeIn();

  });

  /*在选择图片页面点击返回按钮返回到前一页*/
  jQuery(".return").on("click", function () {
    jQuery(".alternative-img").hide();
    jQuery(".return").hide();
    jQuery(".add-project-main").fadeIn();

  });

 /* // Select
  jQuery(".chzn-select").chosen({disable_search_threshold: 10, no_results_text: "",width:"95%"});
  jQuery(".chzn-select2").chosen({disable_search_threshold: 12, no_results_text: "",width:"95%"});

  // Select with Search
  jQuery(".chzn-select-search").chosen({no_results_text: "没找到匹配项"});

  //select with search input
  jQuery(".chzn-select-search-input").chosen({no_results_text: "没找到匹配项"}, {max_selected_options: 5});*/

  /*tabs*/
  jQuery('#tabs').tabs();

  var getCurrentDate = function (format) {
    var d = new Date();
    return d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate();
  };

/*
  /!*选择时间*!/
  jQuery(".datetimepicker").each(function () {
    var timepicker = jQuery(this).attr("timepicker") ? jQuery(this).attr("timepicker") : false;
    var format = jQuery(this).attr("format") ? jQuery(this).attr("format") : "Y/m/d";
    var format = jQuery(this).attr("format") ? jQuery(this).attr("format") : "Y/m/d";
    var value = '';
    if (jQuery(this).attr("daysOffset")) {
      var d = new Date();
      var days = parseInt(jQuery(this).attr("daysOffset"));
      days = days + d.getDate();
      d.setDate(days);
      value = d.getFullYear() + "/" + (d.getMonth() + 1) + "/" + d.getDate();
    }
    console.log("value : " + value);
    jQuery(this).datetimepicker({
      value: value,
      lang: 'ch',
      timepicker: timepicker,
      format: format
    });
  });
*/

  /*radio的样式替换*/
  //.beau-select这个加在input上
  //.beau-check-comp加在一个组合上
  jQuery(".beau-select").on("click", function () {
    var th = jQuery(this);
    var parent = th.parents(".beau-check-comp");
    parent.find(".check-radio").removeClass("check-after");
    if (th.is(":checked")) {
      th.siblings(".check-radio").addClass("check-after");
    } else {
      th.siblings(".check-radio").removeClass("check-after");
    }
  });

  /*最新的tab*/
  jQuery(function () {
    jQuery(".n-sub-tab").on("click", function () {
      jQuery(".n-sub-tab").removeClass("active");
      jQuery(this).addClass("active");
      var targetTab = jQuery(this).data("target");
      if (targetTab == "#tab2") {
        jQuery(".tab-word").css("border", "0px");
      } else {
        jQuery(".tab-word").css("border", "");
      }
      jQuery(".target-tab").addClass("hide");
      jQuery(targetTab).removeClass("hide");
    });
  });

  jQuery(".input-id").rating(
    {
      'clearCaption': '0分',
      'starCaptions': {
        0.5: '0.5分',
        1: '1.0分',
        1.5: '1.5分',
        2: '2.0分',
        2.5: '2.5分',
        3: '3.0分',
        3.5: '3.5分',
        4: '4.0分',
        4.5: '4.5分',
        5: '5.0分'
      }
    }
  );

  /*左右滑动按钮*/
  jQuery('.lcs_check').lc_switch('是', '否');
  jQuery('.lcs_check_assignType').lc_switch('固定', '比例');

  /*最新的漂亮的单选和多选*/
  jQuery(document).delegate(".yellow-checker", "click", function () {
    var radio = jQuery(".yellow-checker");
    for (var i = 0; i < radio.length; i++) {
      if (radio[i].checked) {
        jQuery(radio[i]).siblings(".beau-checker").addClass("active");
      } else {
        jQuery(radio[i]).siblings(".beau-checker").removeClass("active");
      }
    }
  });

  /*表头和左边菜单选中*/
  initMenu();
});

function initMenu() {
  var url = window.location.href;

  if (url.substring(0, 4) == "http") {
    url = url.substring(url.indexOf('0/') + 2);
    /*找到当前页面的名字*/
  } else {
    url = url.substring(url.indexOf('dist/') + 5);
  }

  jQuery('.left-active-img').addClass('hide');//隐藏其他菜单

  var curLi = jQuery("a[href='" + url + "']").parent();//父元素li
  curLi.addClass('active').siblings('li').removeClass('active');

  var curImg = curLi.prev();//找到当前选中元素对应的选中转态图片
  curImg.removeClass('hide').siblings('img').addClass('hide');//隐藏其它选中转态的图片
  var curUl = curImg.parent();//找到左边菜单对应的ul
  curUl.removeClass('hide').siblings('ul').addClass('hide');//显示当前地址的菜单

  jQuery('.headerlist li').removeClass('active');
  jQuery("li[role='" + curUl.attr('role') + "']").addClass('active');

  if (url === "") {
    jQuery("ul[role='cashierMenu']").removeClass('hide').siblings('ul').addClass('hide');
    jQuery("li[name='index']").addClass('active').siblings('li').removeClass('active');
    jQuery("li[name='index']").prev().removeClass('hide').siblings('img').addClass('hide');
    jQuery("li[role='cashierMenu']").addClass('active');
  } else if (url == "yingyehuizong-zongbu.html") {
    jQuery("ul[role='financialMenu']").removeClass('hide').siblings('ul').addClass('hide');
    jQuery("li[name='yingyehuizong']").addClass('active').siblings('li').removeClass('active');
    jQuery("li[name='yingyehuizong']").prev().removeClass('hide').siblings('img').addClass('hide');
    jQuery("li[role='financialMenu']").addClass('active');
  }
}

/*/!*判断模态框高度*!/
 jQuery(document).ready(function () {
 var WH=jQuery(window).height()
 var modal=jQuery(".modal").height()
 if(WH>modal){
 jQuery(".modal").css({top:50+"%"})

 jQuery('.modal').on('show.bs.modal', function (e) {
 var left = ( jQuery(this).width()) / 2;
 jQuery(this).css("margin-left", -left);
 var height = ( jQuery(this).height()) / 2;
 jQuery(this).css("margin-top", -height);
 })
 }
 else{
 var left = ( jQuery(this).width())/2;
 jQuery('.modal').on('show.bs.modal', function (e) {
 var left = ( jQuery(this).width()) / 2;
 jQuery(this).css("margin-left", -left);
 jQuery(".modal").css({top: 0})
 })
 }
 })*/
jQuery(document).ready(function() {
  var WH = jQuery(window).height();
  jQuery('.modal').on('shown.bs.modal', function(e) {
    var modal = jQuery(this).height();
    jQuery(".modal").css({
      top : 50 + "%"
    });
    var left = (jQuery(this).width()) / 2;
    jQuery(this).css("margin-left", -left);
    var height = (jQuery(this).height()) / 2;
    if (WH > modal) {
      jQuery(this).css("margin-top", -height);
    } else {
      jQuery(".modal").css({
        top : 0
      });
    }
  });
});

/*分页*/
jQuery('#pagination-demo').twbsPagination({
  totalPages: 35,
  visiblePages: 5,
});

/*
 $(function() {
 $('label').click(function(){    var radioId = $(this).attr('name');
 $('label').removeAttr('class') && $(this).attr('class', 'checked');
 $('input[type="radio"]').removeAttr('checked') && $('#' + radioId).attr('checked', 'checked');
 });
 });
 */
