/**
 * Created by candice on 2015/6/18.
 */
$(document).ready(function(){
   $('#clientAttr').change(function(){
     var p1=$(this).children('option:selected').val();//这就是selected的值
     if (p1 == '单店'){
       $(".company-name").css("display","none");
     }else if(p1 == '连锁店'){
       $(".company-name").css("display","block");
       $(".company-input").attr("placeholder","");
     }else if(p1 == '分店'){
       $(".company-name").css("display","block");
       $(".company-input").attr("placeholder","输入关键字");
     }
   })
})
