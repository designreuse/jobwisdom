package com.zefun.web.entity;

import java.util.Date;

public class StoreInfo {
    private Date date;
    private Integer id;

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "StoreInfo [date=" + date + ", id=" + id + "]";
    }
    
}
