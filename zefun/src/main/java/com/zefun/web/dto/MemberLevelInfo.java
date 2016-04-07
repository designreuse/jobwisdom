package com.zefun.web.dto;

import java.util.List;

/**
 * 会员等级信息封装类
* @author 乐建建
* @date 2016年2月23日 上午11:09:34 
*/
public class MemberLevelInfo {
    
    /**时间区间开始日期*/
    private String begin;
    
    /**时间区间结束日期*/
    private String end;
    /**会员等级id*/
    private int levelId;
    
    /**会员等级名称*/
    private String levelName;
    /**会员等级下面对应的会员数据*/
    private List<Integer> memberIds;
    /**门店id*/
    private int storeId;
    public String getBegin() {
        return begin;
    }
    public String getEnd() {
        return end;
    }
    public int getLevelId() {
        return levelId;
    }
    public String getLevelName() {
        return levelName;
    }
    public List<Integer> getMemberIds() {
        return memberIds;
    }
    public int getStoreId() {
        return storeId;
    }
    public void setBegin(String begin) {
        this.begin = begin;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }
    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
    public void setMemberIds(List<Integer> memberIds) {
        this.memberIds = memberIds;
    }
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
        
}
