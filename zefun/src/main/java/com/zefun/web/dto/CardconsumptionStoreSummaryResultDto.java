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

import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.MemberLevel;

/**
 * 划卡消费汇总
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2016年1月11日
 */
public class CardconsumptionStoreSummaryResultDto extends SummaryResultDto {

    /**
     * 项目划卡消费
     */
    private double totalProjectAmount = 0;

    /**
     * 套餐划卡消费
     */
    private double totalPackageAmount = 0;

    /**
     * 商品划卡消费
     */
    private double totalCommoditysAmount = 0;

    /**
     * 按时间, 根据{@link #dateType}判断的前一段时间的项目划卡消费
     */
    private double lastTotalProjectAmount = 0;

    /**
     * 按时间, 根据{@link #dateType}判断的前一段时间的套餐划卡消费
     */
    private double lastTotalPackageAmount = 0;

    /**
     * 按时间, 根据{@link #dateType}判断的前一段时间的商品划卡消费
     */
    private double lastTotalCommoditysAmount = 0;

    /**
     * 根据{@link #dateType}判断的相较于前一段时间的增长率
     */
    private String ratio = "--.--";

    /**
     * 部门各种订单类型数据汇总
     */
    private Map<Integer, Map<Integer, StoreSummaryDto>> deptOrderTypeSums = new HashMap<Integer, Map<Integer, StoreSummaryDto>>();

    /**
     * 部门各种卡项数据汇总
     */
    private Map<Integer, Map<Integer, StoreSummaryDto>> deptCardTypeSums = new HashMap<Integer, Map<Integer, StoreSummaryDto>>();

    /**
     * 部门信息
     */
    private List<DeptInfo> deptInfos = new ArrayList<DeptInfo>();

    /**
     * 卡项信息
     */
    private List<MemberLevelDto> memberLevels = new ArrayList<MemberLevelDto>();

    /**
     * 卡项信息
     */
    private Map<Integer, MemberLevelDto> mMemberLevles = new LinkedHashMap<Integer, MemberLevelDto>();

    /**
     * 会员id与会员等级id对照
     */
    private Map<Integer, Integer> memberLevelIds = new HashMap<Integer, Integer>();

    /**
     * 按时间和划卡消费类型的数据汇总
     */
    private Map<String, Map<Integer, StoreSummaryDto>> dateOrderTypeSums = new HashMap<String, Map<Integer, StoreSummaryDto>>();

    /**
     * 所有卡类型的数据汇总
     */
    private Map<Integer, StoreSummaryDto> cardTypeSums = new HashMap<Integer, StoreSummaryDto>();

    
    /**
     * 
    * @author 张进军
    * @date Jan 16, 2016 8:52:43 PM
    * @param memberLevelIds 卡项标识列表
    * @param memberLevels   卡项信息列表
    * @param deptInfos      部门列表
    * @param dateType       日期类型
    * @param begin          开始时间
    * @param end            结束时间
    * @param summaryDtos    营业汇总数据
    * @param lastSummaryDtos    最后一次汇总数据
    * @throws Exception 统计异常
     */
    public CardconsumptionStoreSummaryResultDto(Map<Integer, Integer> memberLevelIds, List<MemberLevelDto> memberLevels, List<DeptInfo> deptInfos,
            Integer dateType, String begin, String end, List<StoreSummaryDto> summaryDtos, List<StoreSummaryDto> lastSummaryDtos) throws Exception {
        if (memberLevels == null) {
            memberLevels = new ArrayList<MemberLevelDto>();
        }
        this.memberLevels = memberLevels;
        for (MemberLevelDto memberLevel : this.memberLevels) {
            mMemberLevles.put(memberLevel.getLevelId(), memberLevel);
        }
        if (memberLevelIds == null) {
            memberLevelIds = new HashMap<Integer, Integer>();
        }
        this.memberLevelIds = memberLevelIds;
        this.deptInfos = deptInfos;
        if (this.deptInfos == null) {
            this.deptInfos = new ArrayList<DeptInfo>();
        }
        this.dateType = dateType;
        this.begin = begin.replace('-', '/');
        this.end = DateFormatUtils.format(DateUtils.addDays(DateUtils.parseDate(end, new String[]{"yyyy-MM-dd"}), -1), "yyyy/MM/dd");
        int dateLen = 10;
        String datePattern = "yyyy-MM-dd";
        if (dateType != null && dateType == 4) {
            dateLen = 7;
            datePattern = "yyyy-MM";
        }
        computeSums(summaryDtos, lastSummaryDtos, dateLen);

        if (dateType != null) {
            double lastTotalAmount = lastTotalProjectAmount + lastTotalCommoditysAmount + lastTotalPackageAmount;
            double totalAmount = totalCommoditysAmount + totalPackageAmount + totalProjectAmount;
            if (lastTotalAmount > 0) {
                ratio =  new DecimalFormat("##.##").format(((totalAmount - lastTotalAmount) / lastTotalAmount * 100)) + "%";
            }
        }

        for (DeptInfo deptInfo : deptInfos) {
            Integer deptId = deptInfo.getDeptId();
            Map<Integer, StoreSummaryDto> dtos = deptOrderTypeSums.get(deptId);
            if (dtos == null) {
                dtos = new HashMap<Integer, StoreSummaryDto>();
            }
            for (int i = 1; i < 4; i++) {
                StoreSummaryDto dto = dtos.get(i);
                if (dto == null) {
                    dto = new StoreSummaryDto();
                    dtos.put(i, dto);
                }
            }
            deptOrderTypeSums.put(deptId, dtos);

            Map<Integer, StoreSummaryDto> cardTypeSumDtos = deptCardTypeSums.get(deptId);
            if (cardTypeSumDtos == null) {
                cardTypeSumDtos = new HashMap<Integer, StoreSummaryDto>();
            }
            Map<Integer, StoreSummaryDto> sortCardTypeSumDtos = new LinkedHashMap<Integer, StoreSummaryDto>();
            for (MemberLevelDto memberLevel : this.memberLevels) {
                StoreSummaryDto dto = cardTypeSumDtos.get(memberLevel.getLevelId());
                if (dto == null) {
                    dto = new StoreSummaryDto();
                }
                sortCardTypeSumDtos.put(memberLevel.getLevelId(), dto);
            }
            deptCardTypeSums.put(deptId, sortCardTypeSumDtos);
        }

        Map<String, Map<Integer, StoreSummaryDto>> sortDateOrderTypeSums = new LinkedHashMap<String, Map<Integer, StoreSummaryDto>>();

        Date beginDate = DateUtils.parseDate(this.begin.substring(0, dateLen), new String[]{datePattern.replace('-', '/')});
        Date endDate = DateUtils.parseDate(this.end.substring(0, dateLen), new String[]{datePattern.replace('-', '/')});
        while (beginDate.getTime() <= endDate.getTime()) {
            String date = DateFormatUtils.format(beginDate, datePattern);
            Map<Integer, StoreSummaryDto> orderTypeSumDtos = dateOrderTypeSums.get(date);
            if (orderTypeSumDtos == null) {
                orderTypeSumDtos = new HashMap<Integer, StoreSummaryDto>();
            }
            for (int i = 1; i < 4; i++) {
                StoreSummaryDto orderTypeSumDto = orderTypeSumDtos.get(i);
                if (orderTypeSumDto == null) {
                    orderTypeSumDto = new StoreSummaryDto();
                    orderTypeSumDtos.put(i, orderTypeSumDto);
                }
            }
            sortDateOrderTypeSums.put(date, orderTypeSumDtos);

            if (dateType != null && dateType == 4) {
                beginDate = DateUtils.addMonths(beginDate, 1);
            } 
            else {
                beginDate = DateUtils.addDays(beginDate, 1);
            }
        }
        this.dateOrderTypeSums = sortDateOrderTypeSums;

        for (MemberLevelDto memberLevel : this.memberLevels) {
            StoreSummaryDto cardTypeSumDto = cardTypeSums.get(memberLevel.getLevelId());
            if (cardTypeSumDto == null) {
                cardTypeSums.put(memberLevel.getLevelId(), new StoreSummaryDto());
            }

        }
    }

    /**
     * 汇总计算
     * @author gebing
     * @date 2016年1月11日
     * @param summaryDtos {@link #begin}到{@link #end} 时间段内的订单记录
     * @param lastSummaryDtos 根据{@link #dateType}计算的上一时间段的订单记录
     * @param dateLen 日期长度, 默认为10, 查询年度记录时为7
     */
    private void computeSums(List<StoreSummaryDto> summaryDtos,
            List<StoreSummaryDto> lastSummaryDtos, int dateLen) {

        if (summaryDtos == null) {
            return;
        }

        for (StoreSummaryDto dto : summaryDtos) {

            Integer orderType = dto.getOrderType();
            switch (orderType) {
                case 1:
                case 2:
                case 3:
                    if (dto.getCardAmount() <= 0) {// 不是划卡消费
                        continue;
                    }
                    break;
    
                default: // 不是划卡消费
                    continue;
            }

            String date = dto.getDate();
            date = date.substring(0, dateLen);
            Integer deptId = dto.getDeptId();

            Map<Integer, StoreSummaryDto> orderTypeSumDtos = deptOrderTypeSums.get(deptId);
            if (orderTypeSumDtos == null) {
                orderTypeSumDtos = new HashMap<Integer, StoreSummaryDto>();
            }
            StoreSummaryDto orderTypeSumDto = orderTypeSumDtos.get(orderType);
            if (orderTypeSumDto == null) {
                orderTypeSumDto = new StoreSummaryDto();
            }
            orderTypeSumDto.setAmount(orderTypeSumDto.getAmount() + dto.getCardAmount());
            orderTypeSumDtos.put(orderType, orderTypeSumDto);
            deptOrderTypeSums.put(deptId, orderTypeSumDtos);


            Integer memberId = dto.getMemberId();
            Integer memberLevelId = null;
            if (memberId != null) {
                memberLevelId = memberLevelIds.get(memberId);
            }
            if (memberLevelId != null) {
                Map<Integer, StoreSummaryDto> cardTypeSumDtos = deptCardTypeSums.get(deptId);
                if (cardTypeSumDtos == null) {
                    cardTypeSumDtos = new HashMap<Integer, StoreSummaryDto>();
                }
                StoreSummaryDto cardTypeSumDto = cardTypeSumDtos.get(memberLevelId);
                if (cardTypeSumDto == null) {
                    cardTypeSumDto = new StoreSummaryDto();
                }
                cardTypeSumDto.setMemberCnt(cardTypeSumDto.getMemberCnt() + dto.getMemberCnt());
                cardTypeSumDtos.put(memberLevelId, cardTypeSumDto);
                deptCardTypeSums.put(deptId, cardTypeSumDtos);


                StoreSummaryDto cardTypeSumDto2 = cardTypeSums.get(memberLevelId);
                if (cardTypeSumDto2 == null) {
                    cardTypeSumDto2 = new StoreSummaryDto();
                }
                cardTypeSumDto2.setMemberCnt(cardTypeSumDto2.getMemberCnt() + dto.getMemberCnt());
                cardTypeSums.put(memberLevelId, cardTypeSumDto2);
            }


            Map<Integer, StoreSummaryDto> orderTypeSumDtos2 = dateOrderTypeSums.get(date);
            if (orderTypeSumDtos2 == null) {
                orderTypeSumDtos2 = new HashMap<Integer, StoreSummaryDto>();
            }
            StoreSummaryDto orderTypeSumDto2 = orderTypeSumDtos2.get(orderType);
            if (orderTypeSumDto2 == null) {
                orderTypeSumDto2 = new StoreSummaryDto();
            }
            orderTypeSumDto2.setAmount(orderTypeSumDto2.getAmount() + dto.getCardAmount());
            dateOrderTypeSums.put(date, orderTypeSumDtos2);

        }

    }

    public double getTotalProjectAmount() {
        return totalProjectAmount;
    }

    public double getTotalPackageAmount() {
        return totalPackageAmount;
    }

    public double getTotalCommoditysAmount() {
        return totalCommoditysAmount;
    }

    public double getLastTotalProjectAmount() {
        return lastTotalProjectAmount;
    }

    public double getLastTotalPackageAmount() {
        return lastTotalPackageAmount;
    }

    public double getLastTotalCommoditysAmount() {
        return lastTotalCommoditysAmount;
    }

    public String getRatio() {
        return ratio;
    }

    public Map<Integer, Map<Integer, StoreSummaryDto>> getDeptOrderTypeSums() {
        return deptOrderTypeSums;
    }

    public Map<Integer, Map<Integer, StoreSummaryDto>> getDeptCardTypeSums() {
        return deptCardTypeSums;
    }

    public List<DeptInfo> getDeptInfos() {
        return deptInfos;
    }

    public List<MemberLevelDto> getMemberLevels() {
        return memberLevels;
    }

    public Map<Integer, Integer> getMemberLevelIds() {
        return memberLevelIds;
    }

    public Map<String, Map<Integer, StoreSummaryDto>> getDateOrderTypeSums() {
        return dateOrderTypeSums;
    }

    public Map<Integer, StoreSummaryDto> getCardTypeSums() {
        return cardTypeSums;
    }

    /**
     * 获取卡项信息列表
    * @author 张进军
    * @date Jan 16, 2016 8:54:49 PM
    * @return   卡项信息列表
     */
    public Map<Integer, MemberLevelDto> getmMemberLevles() {
        return mMemberLevles;
    }

}
