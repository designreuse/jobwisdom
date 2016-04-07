package com.zefun.web.entity;


/**
 * @author 张进军
 * @date 2016年03月04日 PM 22:17:13
 */
public class UboxMachineInfo {
	/** 机器标识 */
	private String id;

	/** 门店标识 */
	private Integer storeId;

	/** 机器名称 */
	private String name;

	/** 所在地址 */
	private String address;

	/** 距离查询点距离 */
	private String distance;

	/** 是否便利店 */
	private Integer isShop;

	/** 是否存在柜子 */
	private Integer hasBox;

	/** 是否售卖快餐 */
	private Integer hasMeal;

	/** 纬度 */
	private String lat;

	/** 经度 */
	private String lng;

	/** 是否在线 */
	private Integer vmConnected;

	/** @param id	机器标识 */
	public void setId(String id){
		this.id = id;
	}

	/** @return	机器标识 */
	public String getId(){
		return id;
	}

	/** @param storeId	门店标识 */
	public void setStoreId(Integer storeId){
		this.storeId = storeId;
	}

	/** @return	门店标识 */
	public Integer getStoreId(){
		return storeId;
	}

	/** @param name	机器名称 */
	public void setName(String name){
		this.name = name;
	}

	/** @return	机器名称 */
	public String getName(){
		return name;
	}

	/** @param address	所在地址 */
	public void setAddress(String address){
		this.address = address;
	}

	/** @return	所在地址 */
	public String getAddress(){
		return address;
	}

	/** @param distance	距离查询点距离 */
	public void setDistance(String distance){
		this.distance = distance;
	}

	/** @return	距离查询点距离 */
	public String getDistance(){
		return distance;
	}

	/** @param isShop	是否便利店 */
	public void setIsShop(Integer isShop){
		this.isShop = isShop;
	}

	/** @return	是否便利店 */
	public Integer getIsShop(){
		return isShop;
	}

	/** @param hasBox	是否存在柜子 */
	public void setHasBox(Integer hasBox){
		this.hasBox = hasBox;
	}

	/** @return	是否存在柜子 */
	public Integer getHasBox(){
		return hasBox;
	}

	/** @param hasMeal	是否售卖快餐 */
	public void setHasMeal(Integer hasMeal){
		this.hasMeal = hasMeal;
	}

	/** @return	是否售卖快餐 */
	public Integer getHasMeal(){
		return hasMeal;
	}

	/** @param lat	纬度 */
	public void setLat(String lat){
		this.lat = lat;
	}

	/** @return	纬度 */
	public String getLat(){
		return lat;
	}

	/** @param lng	经度 */
	public void setLng(String lng){
		this.lng = lng;
	}

	/** @return	经度 */
	public String getLng(){
		return lng;
	}

	/** @param vmConnected	是否在线 */
	public void setVmConnected(Integer vmConnected){
		this.vmConnected = vmConnected;
	}

	/** @return	是否在线 */
	public Integer getVmConnected(){
		return vmConnected;
	}

    @Override
    public String toString() {
        return "UboxMachineInfo [id=" + id + ", storeId=" + storeId + ", name=" + name + ", address=" + address + ", distance=" + distance
                + ", isShop=" + isShop + ", hasBox=" + hasBox + ", hasMeal=" + hasMeal + ", lat=" + lat + ", lng=" + lng + ", vmConnected="
                + vmConnected + "]";
    }

}