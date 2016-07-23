package com.zefun.web.dto;

import java.util.List;

import com.zefun.web.entity.StoreInfo;

/**
 * 汇总基础类
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2016年1月8日
 */
public class SummaryResultDto {

    /**
     * 开始时间
     */
    protected String begin;

	/**
     * 页面点击的日期类型, 日周月年
     */
    protected Integer dateType;

	/**
     * 结束时间
     */
    protected String end;
    
    /**
     * 订单类型 参照order_detail表的order_type字段
     * */
    private Integer orderType;

	public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
    
    /**分店列表*/
    protected List<StoreInfo> branchStores;

    public List<StoreInfo> getBranchStores() {
        return branchStores;
    }

    public void setBranchStores(List<StoreInfo> branchStores) {
        this.branchStores = branchStores;
    }

    /**
     * 门店id
     * */
    protected Integer storeId;
    
    /***
     * 当前门店所属的总店id(当前门店为总店时 id为当前门店id)
     * */
    private Integer hpStoreId;
    
    public Integer getHpStoreId() {
        return hpStoreId;
    }

    public void setHpStoreId(Integer hpStoreId) {
        this.hpStoreId = hpStoreId;
    }

    /**默认排序类型*/
    protected Integer type=1;
    
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 部门id
     * */
    private Integer deptId;
    
    /**
     * 疗程id
     * */
    private Integer projectId;
    
    /**商品id*/
    private Integer goodsId;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getBegin() {
        return begin;
    }

    public Integer getDateType() {
        return dateType;
    }

    public String getEnd() {
        return end;
    }
    
    public Integer getStoreId() {
		return storeId;
	}

	public void setBegin(String begin) {
		this.begin = begin;
	}

	public void setDateType(Integer dateType) {
		this.dateType = dateType;
	}

    public void setEnd(String end) {
		this.end = end;
	}

    public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	

}
