
/*jQuery("#map").delegate(".iamthere", "click", function() {
	alert("aaa");
	jQuery("#storeAddress").val(address);
	jQuery("#lng").text(lng);
	jQuery("#lat").text(lat);
	alert("已更换位置：" + address + ", 您可通过提交修改进行保存");
});*/

//覆盖物图标
/*var myIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23, 25), {
	offset: new BMap.Size(10, 25), // 指定定位位置
	imageOffset: new BMap.Size(0, 0 - 12 * 25) // 设置图片偏移
});*/

/*
jQuery(function(){
	jQuery("#searchtext").val("深圳市家乐大厦");
	jQuery("#searchbt").first().trigger("click");
});
*/

//显示一个对象的所有属性
function showAtrributes(event){
    var out='';
    for(var p in event){  
        if(typeof(p)!="function"){  
            out+=p+"="+event[p]+"  ";  
        }  
    }  
    alert(out); 
}
var key='zxYZYzCtCT1lhiVOuxQeieOf';
var map = new BMap.Map("map", {enableMapClick:false}); // 创建地图实例(并且禁止POI事件,点击地图标志性建筑不会弹窗)
var point; // 创建点坐标   
if (globalLat != null && globalLat.length != 0 && globalLng != null && globalLng.length != 0) {
	point = new BMap.Point(globalLng, globalLat);
	map.centerAndZoom(point, 15); // 初始化地图，设置中心点坐标和地图缩放级别
} else {
	point = new BMap.Point(116.4135540000,39.9110130000);
	map.centerAndZoom(point, 4);
}
map.addControl(new BMap.NavigationControl());
map.addControl(new BMap.ScaleControl());     

map.enableScrollWheelZoom();//滚轮缩放事件  
//map.enableKeyboard(); //键盘方向键缩放事件  
map.enableContinuousZoom(); // 开启连续缩放效果  
map.enableInertialDragging(); // 开启惯性拖拽效果

//自定义图标(覆盖物)
var customerIcon = new BMap.Icon(baseUrl + "img/baiduMap/baiduMapCustomerInco2.png", new BMap.Size(50,80));

var preMarker = new BMap.Marker(point, {icon:customerIcon});
map.addOverlay(preMarker);

//设置标注可拖拽
preMarker.enableDragging();
//跳动的动画
preMarker.setAnimation(BMAP_ANIMATION_BOUNCE);
//标注拖拽后的位置
preMarker.addEventListener("dragend", function (e) {
   console.log("preMarker拖拽：" + e.point.lng + ", " + e.point.lat);
   ajaxDragend(preMarker, e.point.lng, e.point.lat);
});
/**
 * 拖拽覆盖物调用ajax事件，弹窗
 * @param whichMarker  点击的是哪个覆盖物(有1:页面上首次存在的覆盖物(preMarker), 2:点击事件出来可以拖动的覆盖物(clickMarker))
 * @param lng  经度
 * @param lat  纬度
 */
function ajaxDragend(whichMarker, lng, lat) {
    var targetUrl='http://api.map.baidu.com/geocoder/v2/?ak='+key+'&location='+lat+','+lng+'&output=json&pois=0';
	jQuery.ajax({
		url:targetUrl,
		type:"get",
		async : false,
		dataType:'jsonp',
		beforeSend:function(){
		  //alert(targetUrl);  
		},
		success:function(data,status){
			//alert(status);
			if(status=='success' && data.status==0){				
				content = "<div>" + data.result.formatted_address + "</div>";
				/*content += '<form action="" method="post"><input type="hidden" name="lng" value="'
					+ data.result.location.lng+'"><input type="hidden" name="lat" value="'+data.result.location.lat 
					+ '"><input onclick="tempSaveLocation("' + data.result.formatted_address + '", "' + lng + '", "' + lat + '")" type="button" value="我在这里"></form>';*/
				content += '<input type="hidden" name="lng" value="' + data.result.location.lng + '">' 
					+ '<input type="hidden" name="lat" value="' + data.result.location.lat + '">' 
					+ '<input class="iamthere" onclick="iAmThereOfDragend(this, ' + data.result.location.lng + ', ' + data.result.location.lat + ')" type="button" value="我在这里">';
				var info=new BMap.InfoWindow(content);
//				preMarker.openInfoWindow(info);
				whichMarker.openInfoWindow(info);
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			//alert(XMLHttpRequest.status);
			//alert(XMLHttpRequest.readyState);
			//alert(textStatus);
			//alert(errorThrown);
		}
	});
}

//点击地图事件出现覆盖物
var clickMarker;
var clickIcon = new BMap.Icon(baseUrl + "img/baiduMap/map-sprites.png", new BMap.Size(16,30));
map.addEventListener("click", function(e){   //点击事件  
	//alert(e.point.lng + ", " + e.point.lat);
    if(!e.overlay){
//        document.getElementById("mapx").innerHTML="鼠标当前x位置:"+e.point.lng;  
//        document.getElementById("mapy").innerHTML="鼠标当前y位置:"+e.point.lat;  
//        document.getElementById("level").innerHTML="缩放等级:"+this.getZoom();
        var targetUrl = 'http://api.map.baidu.com/geocoder/v2/?ak=' + key + '&location=' + e.point.lat + ',' + e.point.lng + '&output=json&pois=0';
        jQuery.ajax({
            url:targetUrl,
            type:"get",
            async : false,
            dataType:'jsonp',
            beforeSend:function(){
              //alert(targetUrl);
            },
            success:function(data,status){
                if(status=='success' && data.status==0){
                	map.removeOverlay(preMarker);
					map.removeOverlay(clickMarker);
					clickMarker = new BMap.Marker(e.point,{icon : clickIcon});
//					clickMarker = new BMap.Marker(e.point);
					map.addOverlay(clickMarker);
					//设置标注可拖拽
					clickMarker.enableDragging();
					clickMarker.addEventListener("dragend", function (e) {
					   console.log("clickMarker拖拽：" + e.point.lng + ", " + e.point.lat);
					   ajaxDragend(clickMarker, e.point.lng, e.point.lat);
					});
                    content="<div>"+data.result.formatted_address+"</div>";
                    content+='<form action="" method="post">'
                    	+ '<input type="hidden" name="lng" value="' + data.result.location.lng + '">' 
                    	+ '<input type="hidden" name="lat" value="'+ data.result.location.lat + '">' 
                    	+ '<input class="iamthere" onclick="iAmThereOfDragend(this, ' + data.result.location.lng + ', ' + data.result.location.lat + ')" type="button" value="我在这里"></form>';
                    var info=new BMap.InfoWindow(content);
                    clickMarker.openInfoWindow(info);
                    //preMarker=marker;
                }
            },
            error:function(XMLHttpRequest, textStatus, errorThrown){
//                alert(XMLHttpRequest.status);
//                alert(XMLHttpRequest.readyState);
//                alert(textStatus);
//                alert(errorThrown);
            }
        });
        
    }
});
  
map.addEventListener("zoomend", function(){   //缩放事件  
 //alert("地图缩放至：" + this.getZoom() + "级");     
});

//搜索
function serachlocal(){
    var markerArray=new Array();
    var typeArray=new Array('','－公交站','','－地铁站');
    var province = "";
    var city = "";
    var local = new BMap.LocalSearch(map, {     
    renderOptions: {     
        map: map,     
        //panel: "results",//结果容器id  
        autoViewport: true,   //自动结果标注  
        selectFirstResult: false   //不指定到第一个目标  
    },     
    pageCapacity: 8,
    //点击搜索结果之后地图上ABC...弹窗信息
    onMarkersSet:function(pois){
        for(var i=0; i<pois.length; i++){
            (function(){
                var index = i;
                var curPoi = pois[i];
                var curMarker = pois[i].marker;
                markerArray[i] = curMarker;
                //var content = "<h3>" + curPoi.title+typeArray[curPoi.type] + "</h3>";
                var content = "<h3>" + curPoi.title + typeArray[curPoi.type] + "</h3>";
                content += "<div>" + curPoi.address + "</div>";
                content += '<form action="" method="post">' 
                	+ '<input type="hidden" name="province" value="'+ curPoi.province + '">'
                	+ '<input type="hidden" name="city" value="'+ curPoi.city + '">'
                	+ '<input type="hidden" name="title" value="'+ curPoi.title + typeArray[curPoi.type] + '">'
                	+ '<input type="hidden" name="address" value="'+ curPoi.address + '">'
                	+ '<input type="hidden" name="lng" value="'+ curPoi.point.lng + '">' 
                	+ '<input type="hidden" name="lat" value="' + curPoi.point.lat+ '">' 
                	/*+ '<input onclick="tempSaveLocation(' + curPoi.province + curPoi.city + curPoi.address + ', ' + curPoi.point.lng + ', ' + curPoi.point.lat + ')" type="button" value="我在这里"></form>';*/
                	+ '<input onclick="iAmThereOfSearch(this, ' + curPoi.point.lng + ', ' + curPoi.point.lat + ')" type="button" value="我在这里"></form>';
                curMarker.addEventListener('click',function(event){
                    //showAtrributes(event);
                    var info = new BMap.InfoWindow(content);
                    curMarker.openInfoWindow(info);
                    var position = curMarker.getPosition();
                    //document.getElementById("mapx").innerHTML="点击搜索图标经度:"+position.lng;  
                    //document.getElementById("mapy").innerHTML="点击搜索图标纬度:"+position.lat;  
                    //document.getElementById("level").innerHTML="缩放等级:"+this.getZoom();  
                    
                });
            })();
        }
    },
    //搜索完毕填充结果列表
    onSearchComplete:function(results){
        if(local.getStatus() == BMAP_STATUS_SUCCESS){
//            var html='<ol style="list-style: none outside none; padding: 0px; margin: 0px;">';
        	var html = '<div class="search-dizhi"><ul>';
            for(var i=0;i<results.getCurrentNumPois();i++){
                var poi=results.getPoi(i);
                var bYposition = 2-i*20;
                /*html+='<li id="poi'+i+'" index="'+i+'" style="margin: 2px 0px; padding: 0px 5px 0px 3px; cursor: pointer; overflow: hidden; line-height: 17px;">';
                html+='<span style="background:url(http://api.map.baidu.com/bmap/red_labels.gif) 0 '+bYposition+'px no-repeat;padding-left:10px;margin-right:3px"></span>';
                html+='<span style="color:#00c;text-decoration:underline"><b>'+poi.title+'</b>'+typeArray[poi.type]+'</span>';
                html+='<span style="color:#666;">-'+poi.address+'</span>'
                html+='</li>';*/
                html += '<li id="poi'+i+'" index="'+i+'" style="margin: 2px 0px; padding: 0px 5px 0px 3px; cursor: pointer; overflow: hidden; line-height: 17px;">';
                html += '<span style="background:url(http://api.map.baidu.com/bmap/red_labels.gif) 0 '+bYposition+'px no-repeat;padding-left:10px;margin-right:3px"></span>';
                html += '<span class="font-333">' + poi.title + typeArray[poi.type] + '</span>';
                html += '<p class="font-999">' + poi.address + '</p>';
                html += '</li>';
            }
//            html+="</ol>";
            html += '</ul></div>';
            jQuery("#results").html(html);
            for(var i=0;i<results.getCurrentNumPois();i++){
                (function(){
                    var index = i + 1;
                    var poi = results.getPoi(i);
                    province = poi.province;
                    city = poi.city;
                    content = "<h3>" + poi.title + typeArray[poi.type] + "</h3>";
                    content += "<div>" + poi.address + "</div>";
                    content += '<form action="" method="post">'
	                    + '<input type="hidden" name="province2" value="'+ poi.province + '">'
	                	+ '<input type="hidden" name="city2" value="'+ poi.city + '">'
	                	+ '<input type="hidden" name="title2" value="'+ poi.title + typeArray[poi.type] + '">'
	                	+ '<input type="hidden" name="address2" value="'+ poi.address + '">'
                    	+ '<input type="hidden" name="lng2" value="' + poi.point.lng + '">'
                    	+ '<input type="hidden" name="lat2" value="' + poi.point.lat + '">'
                    	+ '<input onclick="iAmThereOfSearch(this, ' + poi.point.lng + ', ' + poi.point.lat + ')" type="button" value="我在这里"></form>';
                    var info=new BMap.InfoWindow(content);
                    jQuery("#poi"+i).click(function(){
                        markerArray[jQuery(this).attr('index')].openInfoWindow(info);
                    });
                })();
            }
        }
    },
    });
    //alert(jQuery("#city").text() + document.getElementById("searchtext").value);
    local.search(province + city + document.getElementById("searchtext").value);  
}

//点击拖拽事件"我在这里"(不能传中文参数)
function iAmThereOfDragend(obj, lng, lat) {
	/*alert("lng : " + lng);
	alert("lat : " + lat);
	alert(jQuery(".BMap_bubble_content div").text());*/
	var location = jQuery(".BMap_bubble_content div").text();
	//保存地址必须在该店铺申请城市中
	var curCity = jQuery("#city").text();  //店铺申请城市(不能修改)
	if (location.indexOf(curCity) == -1) {
		dialog("地址必须在" + curCity + "中");
		return;
	}
	if (location != null && location.length != 0) {
		var locaArr = location.split(":");
//		jQuery("#storeAddress").val(location);
		if (location.indexOf("市") >= 0) {
			var cityArr = location.split("市");
			jQuery("#searchtext").val(cityArr[1]);
		}
		jQuery("#lng").text(lng);
		jQuery("#lat").text(lat);
		dialog("已更换位置：" + location + ", 您可通过提交修改进行保存");
	}
}
/*
 * 点击搜索事件(地图ABC...)"我在这里"(不能传中文参数)
 * 和
 * 点击搜索事件(列表)"我在这里"(不能传中文参数)
 */
var addressOfSearch;
function iAmThereOfSearch(obj, lng, lat) {
	var province = jQuery(".BMap_bubble_content form input:eq(0)").val();
	var city = jQuery(".BMap_bubble_content form input:eq(1)").val();
	var address = jQuery(".BMap_bubble_content form input:eq(3)").val();
	var title = jQuery(".BMap_bubble_content form input:eq(2)").val();
	//排除address是公交站或地铁站的情况
	if (title.indexOf("－公交站") != -1 || title.indexOf("－地铁站") != -1) {
		address = title;
	}
	/*alert(province);
	alert(city);
	alert(address);*/
	decideMunicipalities(province, city, address);
//	jQuery("#storeAddress").val(addressOfSearch);
	if (addressOfSearch.indexOf("市") >= 0) {
		var cityArr = addressOfSearch.split("市");
		jQuery("#searchtext").val(cityArr[1]);
	}
	jQuery("#lng").text(lng);
	jQuery("#lat").text(lat);
	dialog("已更换位置：" + addressOfSearch + ", 您可通过提交修改进行保存");
}

//判断省市是否是一样的(直辖市是一样的)
function decideMunicipalities(province, city, address) {
	if (province == city) {
		/*
		 * 判断完直辖市(省和市相同)之后，再判断下市和街
		 * eg: city : 深圳市, address : 深圳市华强北街道办
		 */
		var index = address.indexOf(city);
		if (index != -1) {
			var addressArr = address.split(city);
			if (addressArr.length >= 2) {
				address = addressArr[1];
			} else {
				address = " ";
			}
		}
		addressOfSearch = address;
	} else {
		/*
		 * 判断完直辖市(省和市相同)之后，再判断下市和街
		 * eg: city : 深圳市, address : 深圳市华强北街道办
		 */
		var index = address.indexOf(city);
		if (index != -1) {
			var addressArr = address.split(city);
			if (addressArr.length >= 2) {
				address = addressArr[1];
			} else {
				address = " ";
			}
		}
		addressOfSearch = province + city + address;
	}
}

//保存地理位置和坐标到页面上，以供修改保存使用(点击"我在这里触发")
function tempSaveLocation(address, lng, lat) {
//	jQuery("#storeAddress").val(address);
	if (address.indexOf("市") >= 0) {
		var cityArr = address.split("市");
		jQuery("#searchtext").val(cityArr[1]);
	}
	jQuery("#lng").text(lng);
	jQuery("#lat").text(lat);
	dialog("已更换位置：" + address + ", 您可通过提交修改进行保存");
}

