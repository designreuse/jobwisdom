package com.zefun.web.dto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberLevel;

/**
 * 卡项销售汇总
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2016年1月8日
 */
public class CardsaleStoreSummaryResultDto extends SummaryResultDto {

    /**新开卡*/
    private static final String SALE_TYPE_NEW = "new";

    /**充值*/
    private static final String SALE_TYPE_CHARGE = "charge";

    /**升级*/
    private static final String SALE_TYPE_SWIPE = "swipe";

    /**
     * 会员等级
     */
    private List<MemberLevel> memberLevels = new ArrayList<MemberLevel>();

    /**
     * 新开会员金额汇总
     */
    private double totalNewMemberAmount;

    /**
     * 续充会员金额汇总
     */
    private double totalChargeMemberAmount;

    /**
     * 按时间, 根据{@link #dateType}判断的前一段时间的新开会员金额汇总
     */
    private double lastTotalNewMemberAmount;

    /**
     * 按时间, 根据{@link #dateType}判断的前一段时间的续充会员金额汇总
     */
    private double lastTotalChargeMemberAmount;

    /**
     * 新增(new), 续充(charge), 划卡(swipe) 各种卡项销售统计
     */
    private Map<Integer, Map<String, StoreSummaryDto>> cardsaleTypeSums = new HashMap<Integer, Map<String, StoreSummaryDto>>();

    /**
     * 汇总各种会员的储值总额和余额
     */
    private Map<Integer, Map<String, Object>> accountInfoSums = new HashMap<Integer, Map<String, Object>>();

    /**
     * 会员id与会员等级id对照
     */
    private Map<Integer, Integer> memberLevelIds = new HashMap<Integer, Integer>();

    /**
     * 会员的账号信息
     */
    private Map<Integer, MemberAccount> memberAccounts = new HashMap<Integer, MemberAccount>();

    /**
     * 按时间和卡类型的卡项销售汇总
     */
    private Map<String, Map<Integer, StoreSummaryDto>> dateCardTypeSums = new HashMap<String, Map<Integer, StoreSummaryDto>>();

    /**
     * 按时间的卡项销售汇总
     */
    private Map<String, Map<String, StoreSummaryDto>> dateSums = new HashMap<String, Map<String, StoreSummaryDto>>();

    /**卡项总数*/
    private int totalMemCnt = 0;

    /**
     * 构造函数
    * @author 张进军
    * @date Jan 16, 2016 8:56:03 PM
    * @param memberAccounts     会员账户列表
    * @param memberLevelIds     卡项标识列表
    * @param memberLevels       卡项信息列表
    * @param dateType           日期类型
    * @param begin              开始时间
    * @param end                结束时间
    * @param summaryDtos        统计数据
    * @param lastSummaryDtos    上次数据数据
    * @throws Exception 统计异常
     */
    public CardsaleStoreSummaryResultDto(Map<Integer, MemberAccount> memberAccounts, Map<Integer, Integer> memberLevelIds, 
            List<MemberLevel> memberLevels, Integer dateType, String begin, String end, List<StoreSummaryDto> summaryDtos, 
            List<StoreSummaryDto> lastSummaryDtos) throws Exception {
        if (memberLevels == null) {
            memberLevels = new ArrayList<MemberLevel>();
        }
        this.memberLevels = memberLevels;
        if (memberLevelIds == null) {
            memberLevelIds = new HashMap<Integer, Integer>();
        }
        this.memberLevelIds = memberLevelIds;
        if (memberAccounts == null) {
            memberAccounts = new HashMap<Integer, MemberAccount>();
        }
        this.memberAccounts = memberAccounts;
        this.dateType = dateType;
        this.begin = begin;
        this.end = end;
        this.begin = begin.replace('-', '/');
        this.end = DateFormatUtils.format(DateUtils.addDays(DateUtils.parseDate(end, new String[]{"yyyy-MM-dd"}), -1), "yyyy/MM/dd");
        int dateLen = 10;
        String datePattern = "yyyy-MM-dd";
        if (dateType != null && dateType == 4) {
            dateLen = 7;
            datePattern = "yyyy-MM";
        }

        computeMemberSums(summaryDtos, lastSummaryDtos, dateLen);

        for (MemberLevel memberLevel : this.memberLevels) {
            Integer levelId = memberLevel.getLevelId();

            Map<String, StoreSummaryDto> cardsaleTypeSumDtos = cardsaleTypeSums.get(levelId);
            if (cardsaleTypeSumDtos == null) {
                cardsaleTypeSumDtos = new HashMap<String, StoreSummaryDto>();
            }
            StoreSummaryDto newSumDto = cardsaleTypeSumDtos.get(SALE_TYPE_NEW);
            if (newSumDto == null) {
                newSumDto = new StoreSummaryDto();
            }
            cardsaleTypeSumDtos.put(SALE_TYPE_NEW, newSumDto);

            StoreSummaryDto chargeSumDto = cardsaleTypeSumDtos.get(SALE_TYPE_CHARGE);
            if (chargeSumDto == null) {
                chargeSumDto = new StoreSummaryDto();
            }
            cardsaleTypeSumDtos.put(SALE_TYPE_CHARGE, chargeSumDto);

            StoreSummaryDto swipeSumDto = cardsaleTypeSumDtos.get(SALE_TYPE_SWIPE);
            if (swipeSumDto == null) {
                swipeSumDto = new StoreSummaryDto();
            }
            cardsaleTypeSumDtos.put(SALE_TYPE_SWIPE, swipeSumDto);
            cardsaleTypeSums.put(levelId, cardsaleTypeSumDtos);

            Map<String, Object> accountInfoSumDto = accountInfoSums.get(levelId);
            if (accountInfoSumDto == null) {
                accountInfoSumDto = new HashMap<String, Object>();
                accountInfoSumDto.put("total", new Double(0));
                accountInfoSumDto.put("left", new Double(0));
            }
            double total = Double.valueOf(accountInfoSumDto.get("total").toString());
            double left = Double.valueOf(accountInfoSumDto.get("left").toString());
            if (total > 0) {
                accountInfoSumDto.put("ratio", new DecimalFormat("##.##").format((left / total * 100)) + "%");
            } 
            else {
                accountInfoSumDto.put("ratio", "--.--");
            }
            accountInfoSums.put(levelId, accountInfoSumDto);
        }

        Map<String, Map<Integer, StoreSummaryDto>> sortDateCardTypeSums = new LinkedHashMap<String, Map<Integer, StoreSummaryDto>>();
        Map<String, Map<String, StoreSummaryDto>> sortDateSums = new LinkedHashMap<String, Map<String, StoreSummaryDto>>();

        Date beginDate = DateUtils.parseDate(this.begin.substring(0, dateLen), new String[]{datePattern.replace('-', '/')});
        Date endDate = DateUtils.parseDate(this.end.substring(0, dateLen), new String[]{datePattern.replace('-', '/')});
        while (beginDate.getTime() <= endDate.getTime()) {
            String date = DateFormatUtils.format(beginDate, datePattern);
            Map<Integer, StoreSummaryDto> dateCardTypeSumDtos = dateCardTypeSums.get(date);
            if (dateCardTypeSumDtos == null) {
                dateCardTypeSumDtos = new HashMap<Integer, StoreSummaryDto>();
            }
            for (MemberLevel memberLevel : this.memberLevels) {
                Integer levelId = memberLevel.getLevelId();
                StoreSummaryDto dateCardTypeSumDto = dateCardTypeSumDtos.get(levelId);
                if (dateCardTypeSumDto == null) {
                    dateCardTypeSumDto = new StoreSummaryDto();
                }
                dateCardTypeSumDtos.put(levelId, dateCardTypeSumDto);
            }
            sortDateCardTypeSums.put(date, dateCardTypeSumDtos);


            Map<String, StoreSummaryDto> dateSumDtos = dateSums.get(date);
            if (dateSumDtos == null) {
                dateSumDtos = new HashMap<String, StoreSummaryDto>();
            }
            StoreSummaryDto newSumDto = dateSumDtos.get(SALE_TYPE_NEW);
            if (newSumDto == null) {
                newSumDto = new StoreSummaryDto();
            }
            dateSumDtos.put(SALE_TYPE_NEW, newSumDto);

            StoreSummaryDto chargeSumDto = dateSumDtos.get(SALE_TYPE_CHARGE);
            if (chargeSumDto == null) {
                chargeSumDto = new StoreSummaryDto();
            }
            dateSumDtos.put(SALE_TYPE_CHARGE, chargeSumDto);

            StoreSummaryDto swipeSumDto = dateSumDtos.get(SALE_TYPE_SWIPE);
            if (swipeSumDto == null) {
                swipeSumDto = new StoreSummaryDto();
            }
            dateSumDtos.put(SALE_TYPE_SWIPE, swipeSumDto);

            sortDateSums.put(date, dateSumDtos);

            if (dateType != null && dateType == 4) {
                beginDate = DateUtils.addMonths(beginDate, 1);
            } 
            else {
                beginDate = DateUtils.addDays(beginDate, 1);
            }

        }
        this.dateCardTypeSums = sortDateCardTypeSums;
        this.dateSums = sortDateSums;

    }

    /**
     * 汇总计算
     * @author gebing
     * @date 2016年1月11日
     * @param summaryDtos {@link #begin}到{@link #end} 时间段内的订单记录
     * @param lastSummaryDtos 根据{@link #dateType}计算的上一时间段的订单记录
     * @param dateLen 日期长度, 默认为10, 查询年度记录时为7
     */
    private void computeMemberSums(List<StoreSummaryDto> summaryDtos,
            List<StoreSummaryDto> lastSummaryDtos, int dateLen) {

        if (summaryDtos == null) {
            return;
        }

        for (StoreSummaryDto dto : summaryDtos) {
            String date = dto.getDate();
            date = date.substring(0, dateLen);
            Integer memberId = dto.getMemberId();
            Integer memberLevelId = null;
            if (memberId != null) {
                memberLevelId = memberLevelIds.get(memberId);
            }
            if (memberLevelId == null) {
                continue;
            }
            Integer orderType = dto.getOrderType();
            StoreSummaryDto storeSummaryDto = null;
            StoreSummaryDto dateSumDto = null;
            String saleType = null;
            switch (orderType) {
                case 4: // 开卡
                case 6: { // 升级
                    totalNewMemberAmount += dto.getCashRealAmount();
                    saleType = SALE_TYPE_NEW;
                    break;
                }
                case 5: { // 充值
                    totalChargeMemberAmount += dto.getCashRealAmount();
                    saleType = SALE_TYPE_CHARGE;
                    break;
                }
                default: { // 划卡
                    saleType = SALE_TYPE_SWIPE;
                    break;
                }
            }
            if (memberLevelId != null && saleType != null) {
                Map<String, StoreSummaryDto> cardsaleTypeSumDtos = cardsaleTypeSums.get(memberLevelId);
                if (cardsaleTypeSumDtos == null) {
                    cardsaleTypeSumDtos = new HashMap<String, StoreSummaryDto>();
                }
                storeSummaryDto = cardsaleTypeSumDtos.get(saleType);
                if (storeSummaryDto == null) {
                    storeSummaryDto = new StoreSummaryDto();
                }

                Map<String, StoreSummaryDto> dateSumDtos = dateSums.get(date);
                if (dateSumDtos == null) {
                    dateSumDtos = new HashMap<String, StoreSummaryDto>();
                }
                dateSumDto = dateSumDtos.get(saleType);
                if (dateSumDto == null) {
                    dateSumDto = new StoreSummaryDto();
                }

                if (SALE_TYPE_SWIPE.equals(saleType)) { // 如果是划卡消费
                    if (dto.getCardAmount() > 0) { // 是划卡消费才计算
                        storeSummaryDto.setAmount(storeSummaryDto.getAmount() + dto.getCardAmount());
                        storeSummaryDto.setMemberCnt(storeSummaryDto.getMemberCnt() + dto.getMemberCnt());
                        totalMemCnt += dto.getMemberCnt();
                        dateSumDto.setAmount(dateSumDto.getAmount() + dto.getCardAmount());
                    }
                } 
                else {
                    storeSummaryDto.setAmount(storeSummaryDto.getAmount() + dto.getCashRealAmount());
                    storeSummaryDto.setMemberCnt(storeSummaryDto.getMemberCnt() + dto.getMemberCnt());
                    totalMemCnt += dto.getMemberCnt();

                    dateSumDto.setAmount(dateSumDto.getAmount() + dto.getCashRealAmount());
                }
                cardsaleTypeSumDtos.put(saleType, storeSummaryDto);
                cardsaleTypeSums.put(memberLevelId, cardsaleTypeSumDtos);

                dateSumDtos.put(saleType, dateSumDto);
                dateSums.put(date, dateSumDtos);

                MemberAccount memberAccount = memberAccounts.get(memberId);
                if (memberAccount != null) {
                    Map<String, Object> accountInfoSumDto = accountInfoSums.get(memberLevelId);
                    if (accountInfoSumDto == null) {
                        accountInfoSumDto = new HashMap<String, Object>();
                    }
                    Double total = Double.valueOf(accountInfoSumDto.get("total") == null ? "0" : accountInfoSumDto.get("total").toString());
                    Double left = Double.valueOf(accountInfoSumDto.get("left") == null ? "0" : accountInfoSumDto.get("left").toString());
                    accountInfoSumDto.put("total", memberAccount.getTotalAmount().doubleValue() + total);
                    accountInfoSumDto.put("left", memberAccount.getBalanceAmount().doubleValue() + left);
                    accountInfoSums.put(memberLevelId, accountInfoSumDto);
                }
            }

            if (memberLevelId != null) {
                Map<Integer, StoreSummaryDto> dateCardTypeDtos = dateCardTypeSums.get(date);
                if (dateCardTypeDtos == null) {
                    dateCardTypeDtos = new HashMap<Integer, StoreSummaryDto>();
                }
                StoreSummaryDto dateCardTypeDto = dateCardTypeDtos.get(memberLevelId);
                if (dateCardTypeDto == null) {
                    dateCardTypeDto = new StoreSummaryDto();
                }
                if (orderType == 4 || orderType == 5 || orderType == 6) {
                    dateCardTypeDto.setAmount(dateCardTypeDto.getAmount() + dto.getCashRealAmount());
                } 
                else {
                    dateCardTypeDto.setAmount(dateCardTypeDto.getAmount() + dto.getCardAmount());
                }
                dateCardTypeDtos.put(memberLevelId, dateCardTypeDto);
                dateCardTypeSums.put(date, dateCardTypeDtos);
            }
        }

        if (lastSummaryDtos != null) {
            for (StoreSummaryDto dto : lastSummaryDtos) {
                Integer orderType = dto.getOrderType();
                if (orderType == 4) {
                    lastTotalNewMemberAmount += dto.getCashRealAmount();
                }
                if (orderType == 5 || orderType == 6) {
                    lastTotalChargeMemberAmount += dto.getCashRealAmount();
                }
            }
        }

    }


    public List<MemberLevel> getMemberLevels() {
        return memberLevels;
    }


    public double getTotalNewMemberAmount() {
        return totalNewMemberAmount;
    }


    public double getTotalChargeMemberAmount() {
        return totalChargeMemberAmount;
    }


    public double getLastTotalNewMemberAmount() {
        return lastTotalNewMemberAmount;
    }


    public double getLastTotalChargeMemberAmount() {
        return lastTotalChargeMemberAmount;
    }


    public Map<Integer, Map<String, StoreSummaryDto>> getCardsaleTypeSums() {
        return cardsaleTypeSums;
    }


    public Map<Integer, Map<String, Object>> getAccountInfoSums() {
        return accountInfoSums;
    }


    public Map<Integer, Integer> getMemberLevelIds() {
        return memberLevelIds;
    }


    public Map<Integer, MemberAccount> getMemberAccounts() {
        return memberAccounts;
    }

    public Map<String, Map<Integer, StoreSummaryDto>> getDateCardTypeSums() {
        return dateCardTypeSums;
    }

    public int getTotalMemCnt() {
        return totalMemCnt;
    }

    public Map<String, Map<String, StoreSummaryDto>> getDateSums() {
        return dateSums;
    }

}
