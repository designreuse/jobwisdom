jQuery(function(){
    jQuery('.business').click(function(){
	    text=jQuery(this).text();
		if(text=='停业'){
		   jQuery(this).text('营业');
		   jQuery(this).removeClass('active');
       }
	    else{
		    jQuery(this).text('停业');
			jQuery(this).addClass('active');
		 }
   });
 });

function addStore() {
	jQuery("#addOrUpdateStore").show();
}

 jQuery('#preview').click(function(){
	imgObject = jQuery(this);
    jQuery("#pageUp").show()
 })
 
 jQuery('.cancelinput').click(function(){
    jQuery("#pageUp").hide();
    jQuery('.photo-clip-rotateLayer').html('');
 })

var imgKey = "";
function zccCallback(dataBase64){
	imgObject.children("img").attr("src", dataBase64);
	var key = "jobwisdom/project/" + new Date().getTime();
	if ((typeof(imgObject.children("img").attr("projectImage")))!="undefined"){
		var url = qiniuUrl+key;
		imgKey = key;
		imgObject.children("img").attr("projectImage", url);
	}else {
		var url = qiniuUrl+key;
		imgKey = key;
		imgObject.children("img").attr("affiliatedImage", url);
	}
    var data = {"stringBase64":dataBase64,"key":key};
    jQuery('.cancelinput').click();
    jQuery.ajax({
		type: "POST",
		url: baseUrl+"qiniu/base64",
	       data: JSON.stringify(data),
	       contentType: "application/json",
	       dataType: "json",
	       async:true,  
	       beforeSend:function(){
	       	console.log("beforeSend upload image");
	       },
	       error:function (){
	       	console.log("upload image error");
	       },
	       complete :function (){
	       	console.log("complete upload image");
	       },
	       success: function(data) {
		       	var imageUrl = data.msg.imageUrl;
		       	var key = data.msg.key;
		       	console.log(imageUrl);
	       }
 	});
}

function cancel() {
	window.location.href = baseUrl + "storeinfo/view/showStoreList";
}

//提交店铺信息
function saveStoreInfo(){
	var storeLogo = imgKey;
	var storeName = jQuery("#storeName").val();
	var storeTel = jQuery("#storeTel").val();
	var addressList = jQuery("#city-picker3").val().split('/');
	var storeProvince = addressList[0];
	var storeCity = addressList[1];
	var street = jQuery("#searchtext").val();
	if (isEmpty(storeProvince)) {
		dialog("请选择省");
		return;
	}
	if (isEmpty(storeCity)) {
		dialog("请选择市");
		return;
	}
	if (isEmpty(street)) {
		dialog("请选择街道");
		return;
	}
	var storeAddress = jQuery("#city-picker3").val() + street;
	if (typeof storeAddress === 'undefined') {
		dialog("请选择地址");
		return;
	}		
	var storeLinkname = jQuery("#storeLinkname").val();
	var storeLinkphone = jQuery("#storeLinkphone").val();
/* 		var latitude = jQuery("#lat").text();
	var longitude = jQuery("#lng").text(); */
	if (isEmpty(storeLogo)) {
        dialog("请上传您的店铺Logo");
        return;
    }
	if (isEmpty(storeName)) {
		dialog("请填写您的店铺名称");
		return;
	}
	/*if (isEmpty(storeAddress)) {
		dialog("请填写您的店铺地址");
		return;
	} else {
		var city = jQuery("#city").text();
		//alert(city);
		if (storeAddress.indexOf(city) == -1) {
			dialog("修改地址只能在您店铺所在城市进行修改");
			return;
		}
	}*/

	if (isEmpty(storeTel)) {
        dialog("请填写您的店铺电话");
        return;
    }
	if (isEmpty(storeLinkname)) {
        dialog("请填写您的店铺负责人姓名");
        return;
    }
	if (isEmpty(storeLinkphone)) {
        dialog("请填写您的店铺负责人电话");
        return;
    }
/* 		if (isEmpty(latitude) || isEmpty(longitude)) {
		dialog("店铺坐标获取失败，请刷新页面重新选取位置");
		return;
	} */
	var data = "storeLogo=" + storeLogo + "&storeName=" + storeName + "&storeTel=" + storeTel + "&storeAddress=" + storeAddress + "&storeProvince=" + storeProvince + "&storeCity=" + storeCity
		+ "&storeLinkname=" + storeLinkname + "&storeLinkphone=" + storeLinkphone ;/* + "&latitude=" + latitude + "&longitude=" + longitude */
	submit(data, "保存成功，已更新您的店铺信息");
}

//数据提交
function submit(data, msg){
	jQuery.ajax({
        cache: true,
        type: "POST",
        url: baseUrl + "storeinfo/action/saveUpdateStore",
        data: data,
        async: false,
        success: function(data) {
        	if (data.code != 0) {
                dialog(e.msg);
                return;
            }
        	dialog(msg);
        	window.location.href = baseUrl + "storeinfo/view/showStoreList";
        }
    });
}