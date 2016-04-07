/**
 * Created by candice on 2015/7/2.
 */

/*tab*/
$(function(){
  $('.live-tab').Tabs({
    event:'click'
  });

  $('.background-tab').Tabs({
    event:'click'
  });

  $('.background-tab').Tabs({
    event:'click'
  });

  $('.liaocheng-tab').Tabs({
    event:'click'
  });

  $('.modal-tab').Tabs({
    event:'click'
  });

  var config = {
    // How long Waves effect duration
    // when it's clicked (in milliseconds)
    duration: 700,

    // Delay showing Waves effect on touch
    // and hide the effect if user scrolls
    // (0 to disable delay) (in milliseconds)
    delay: 300
  };
  /*Waves.attach('.waves-effect', ['waves-button', 'waves-light']);
  Waves.init(config);*/
});

///*时间选择器中文包*/
//$.fn.datetimepicker.dates['zh-CN'] = {
//  days: ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"],
//  daysShort: ["周日", "周一", "周二", "周三", "周四", "周五", "周六", "周日"],
//  daysMin:  ["日", "一", "二", "三", "四", "五", "六", "日"],
//  months: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
//  monthsShort: ["一月", "二月", "三月", "四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月"],
//  today: "今天",
//  suffix: [],
//  meridiem: ["上午", "下午"]
//};
///*datatime-picker*/
//$('.form-datetime').datetimepicker({
//  format: 'yyyy-mm-dd',//显示的格式
//  language: 'zh-CN',//中文
//  autoclose: 'true',//选择后自动关闭
//  minView: '2',//到日，不到时间
//  todayHighlight: true,//今天时间高亮
//  initialDate: new Date()//初始化时间为当前时间
//});


//$('#birthday').datetimepicker({
//  language: 'zh-CN',
//  format:'yyyy-MM-dd',
//  autoclose: 1,
//  todayBtn: 1,
//  startDate: "2013-02-14",
//  minuteStep: 10,
//  pickerPosition: "bottom-left",
//  format: 'yyyy-mm-dd',
//  minView: 'month',
//  todayBtn:false,
//  todayHighlight: true,
//  initialDate: new Date()
//});
