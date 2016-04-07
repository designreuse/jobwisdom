package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.List;

/**
* 异店会员消费封装数据
* @author 乐建建
* @date 2016年3月1日 下午4:05:15 
*/
public class DifferentStoreMemberConsumeDto {
    
    @Override
    public String toString() {
        return "DifferentStoreMemberConsumeDto [cardConsumedCnt="
                + cardConsumedCnt + ", cardConsumedAmt=" + cardConsumedAmt
                + ", chargeCardCnt=" + chargeCardCnt + ", chargeCardAmt="
                + chargeCardAmt + ", detailData=" + detailData + "]";
    }

    /**划卡消费人数*/
    private Integer cardConsumedCnt;
    
    /**划卡消费金额*/
    private BigDecimal cardConsumedAmt;
    
    /**充值人数*/
    private Integer chargeCardCnt;
    
    /**
    * @author 乐建建
    * @date 2016年3月2日 上午11:12:45 
    */
    public DifferentStoreMemberConsumeDto() {
        super();
    }

    /**
    * @author 乐建建
    * @date 2016年3月2日 上午11:12:58
    * @param cardConsumedCnt 刷卡消费人数
    * @param cardConsumedAmt 刷卡消费金额
    * @param chargeCardCnt 充值消费人数
    * @param chargeCardAmt 充值消费金额
    * @param detailData 跨店消费明细数据
    */
    public DifferentStoreMemberConsumeDto(Integer cardConsumedCnt,
            BigDecimal cardConsumedAmt, Integer chargeCardCnt,
            BigDecimal chargeCardAmt,
            List<DifferentStoreCardConsumeDto> detailData) {
        super();
        this.cardConsumedCnt = cardConsumedCnt;
        this.cardConsumedAmt = cardConsumedAmt;
        this.chargeCardCnt = chargeCardCnt;
        this.chargeCardAmt = chargeCardAmt;
        this.detailData = detailData;
    }

    /**充值金额*/
    private BigDecimal chargeCardAmt;
    
    /**他店到本店消费的明细数据*/
    private List<DifferentStoreCardConsumeDto> detailData;

    public List<DifferentStoreCardConsumeDto> getDetailData() {
        return detailData;
    }

    public void setDetailData(List<DifferentStoreCardConsumeDto> detailData) {
        this.detailData = detailData;
    }

    public Integer getCardConsumedCnt() {
        return cardConsumedCnt;
    }

    public void setCardConsumedCnt(Integer cardConsumedCnt) {
        this.cardConsumedCnt = cardConsumedCnt;
    }

    public BigDecimal getCardConsumedAmt() {
        return cardConsumedAmt;
    }

    public void setCardConsumedAmt(BigDecimal cardConsumedAmt) {
        this.cardConsumedAmt = cardConsumedAmt;
    }

    public Integer getChargeCardCnt() {
        return chargeCardCnt;
    }

    public void setChargeCardCnt(Integer chargeCardCnt) {
        this.chargeCardCnt = chargeCardCnt;
    }

    public BigDecimal getChargeCardAmt() {
        return chargeCardAmt;
    }

    public void setChargeCardAmt(BigDecimal chargeCardAmt) {
        this.chargeCardAmt = chargeCardAmt;
    }
}
