package com.zefun.web.dto;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 收益基础类
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2015年11月28日
 */
public class IncomeDto {

    /** 返回码  */
    @ApiModelProperty(value="返回码", required=true)
    public Integer code;
    /** 返回值  */
    @ApiModelProperty(value="返回信息", required=true)
    public Object msg;
    /**
     * 午餐构造
    * @author 高国藩
    * @date 2015年8月11日 下午1:59:18
     */
    public IncomeDto(){
        
    }
    /**
     * 有参构造
    * @author 高国藩
    * @date 2015年8月11日 下午1:59:44
    * @param code 返回值
    * @param msg 返回信息
     */
    public IncomeDto(Integer code, Object msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public Object getMsg() {
        return msg;
    }
    public void setMsg(Object msg) {
        this.msg = msg;
    }
    
    /**
     * 时间
     */
    protected String time;

    /**
     * 对应时间内的收益
     */
    protected double income;

    /**
     * 获取时间
     * @author gebing
     * @date 2015年12月4日
     * @return 时间
     */
    public String getTime() {
        return time;
    }

    /**
     * 设置时间
     * @author gebing
     * @date 2015年12月4日
     * @param time 时间
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * 获取收益
     * @author gebing
     * @date 2015年12月4日
     * @return 收益
     */
    public double getIncome() {
        return income;
    }

    /**
     * 设置收益
     * @author gebing
     * @date 2015年12月4日
     * @param income 收益
     */
    public void setIncome(double income) {
        this.income = ((int)(income*100))/100.0;
    }

}
