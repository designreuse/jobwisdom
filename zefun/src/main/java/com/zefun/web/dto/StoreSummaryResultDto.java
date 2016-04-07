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

/**
 * 门店营业汇总结果
 * @author <a href="mailto:bing_ge@kingdee.com">bing_ge@kingdee.com</a>
 * @date 2016年1月5日
 */
public class StoreSummaryResultDto extends SummaryResultDto {

    /**
     * 门店下部门信息
     */
    private List<DeptInfo> deptInfos = new ArrayList<DeptInfo>();

    /**
     * 总收入
     */
    private double totalIncome = 0.0f;

    /**
     * 总扣减
     */
    private double totalExpenses = 0.0f;

    /**
     * 总实际收益
     */
    private double totalAmount = 0.0f;

    /**
     * 全部现金总收入
     */
    private double totalRealCashAmount = 0.0f;

    /**
     * 根据{@link #dateType}判断的前一段时间的总现金收入
     */
    private double lastTotalRealCashAmount = 0.0f;

    /**
     * 根据{@link #dateType}判断的前一段时间的总收入
     */
    private double lastTotalIncome = 0.0f;

    /**
     * 根据{@link #dateType}判断的前一段时间的总扣减
     */
    private double lastTotalExpenses = 0.0f;

    /**
     * 根据{@link #dateType}判断的前一段时间的总实际收益
     */
    private double lastTotalAmount = 0.0f;

    /**
     * 根据{@link #dateType}判断的相较于前一段时间的增长率
     */
    private String ratio = "--.--";

    /**
     * 按时间, 部门的收入, 扣减和实际收益
     */
    private Map<Integer, Map<String, StoreSummaryDto>> deptDateSums = new HashMap<Integer, Map<String, StoreSummaryDto>>();

    /**
     * 各部门部门的总收入, 扣减和实际收益
     */
    private Map<Integer, StoreSummaryDto> deptSums = new HashMap<Integer, StoreSummaryDto>();

    /**
     * 按时间汇总收入, 扣减和实际收益
     */
    private Map<String, StoreSummaryDto> dateSums = new HashMap<String, StoreSummaryDto>();

    /**
     * 按时间, 根据{@link #dateType}判断的前一段时间的部门的收入, 扣减和实际收益
     */
    private Map<Integer, Map<String, StoreSummaryDto>> lastDateDeptSums = new HashMap<Integer, Map<String, StoreSummaryDto>>();

    /**
     *
     */
    private Map<Integer, Map<Integer, StoreSummaryDto>> lastDeptOrderTypeSums = new HashMap<Integer, Map<Integer, StoreSummaryDto>>();

    /**
     * 部门各项扣减汇总
     */
    private Map<Integer, StoreSummaryDto> deptExpensesSums = new HashMap<Integer, StoreSummaryDto>();

    /**
     * 部门各种订单类型现金收入汇总
     */
    private Map<Integer, Map<Integer, StoreSummaryDto>> deptOrderTypeSums = new HashMap<Integer, Map<Integer, StoreSummaryDto>>();

    /**
     * 部门各种订单类型现金收入汇总
     */
    private Map<String, Map<Integer, StoreSummaryDto>> dateOrderTypeSums = new HashMap<String, Map<Integer, StoreSummaryDto>>();

    /**
     * 各种项目现金收入汇总
     */
    private Map<Integer, StoreSummaryDto> orderTypeSums = new HashMap<Integer, StoreSummaryDto>();

    /**
     * 微信现金总收入
     */
    private double totalWechatAmount;

    /**
     * 支付宝现金总收入
     */
    private double totalAlipayAmount;

    /**
     * 团购现金总收入
     */
    private double totalGroupAmount;

    /**
     * 银联现金总收入
     */
    private double totalUnionpayAmount;

    /**
     * 现金收入
     */
    private double totalCashAmount;

    /**
     * 构造汇总结果
     * @param deptInfos 部门信息
     * @param dateType 时间类型
     * @param begin 开始时间
     * @param end 结束时间
     * @param summaryDtos 根据开始和结束时间查询的汇总结果
     * @param lastSummaryDtos 根据开始和结束时间以及dateType查询的汇总结果
     * @throws Exception    统计异常
     */
    public StoreSummaryResultDto(List<DeptInfo> deptInfos,
            Integer dateType, String begin, String end, List<StoreSummaryDto> summaryDtos, List<StoreSummaryDto> lastSummaryDtos) throws Exception {
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
            if (lastTotalAmount > 0) {
                ratio =  new DecimalFormat("##.##").format(((totalAmount - lastTotalAmount) / lastTotalAmount * 100)) + "%";
            }
        }

        Date beginDate = DateUtils.parseDate(this.begin.substring(0, dateLen), new String[]{datePattern.replace('-', '/')});
        Date endDate = DateUtils.parseDate(this.end.substring(0, dateLen), new String[]{datePattern.replace('-', '/')});

        Map<String, StoreSummaryDto> sortDateSums = new LinkedHashMap<String, StoreSummaryDto>();
        Map<String, Map<Integer, StoreSummaryDto>> sortDateOrderTypeSums = new LinkedHashMap<String, Map<Integer, StoreSummaryDto>>();
        while (beginDate.getTime() <= endDate.getTime()) {
            String date = DateFormatUtils.format(beginDate, datePattern);
            StoreSummaryDto dto = dateSums.get(date);
            if (dto == null) {
                dto = new StoreSummaryDto();
            }
            sortDateSums.put(date, dto);

            Map<Integer, StoreSummaryDto> orderTypeSumDtos = dateOrderTypeSums.get(date);
            if (orderTypeSumDtos == null) {
                orderTypeSumDtos = new HashMap<Integer, StoreSummaryDto>();
            }
            for (int i = 1; i < 7; i++) {
                StoreSummaryDto orderTypeSumDto = orderTypeSumDtos.get(i);
                if (orderTypeSumDto == null) {
                    orderTypeSumDto = new StoreSummaryDto();
                }
                orderTypeSumDtos.put(i, orderTypeSumDto);
            }
            sortDateOrderTypeSums.put(date, orderTypeSumDtos);

            for (DeptInfo deptInfo : deptInfos) {
                Integer deptId = deptInfo.getDeptId();
                Map<String, StoreSummaryDto> dateSumDtos = deptDateSums.get(deptId);
                if (dateSumDtos == null) {
                    dateSumDtos = new HashMap<String, StoreSummaryDto>();
                }
                StoreSummaryDto dateSumDto = dateSumDtos.get(date);
                if (dateSumDto == null) {
                    dateSumDto = new StoreSummaryDto();
                }

                dateSumDtos.put(date, dateSumDto);
                deptDateSums.put(deptId, dateSumDtos);
            }
            if (dateType != null && dateType == 4) {
                beginDate = DateUtils.addMonths(beginDate, 1);
            } 
            else {
                beginDate = DateUtils.addDays(beginDate, 1);
            }
        }
        this.dateSums = sortDateSums;
        this.dateOrderTypeSums = sortDateOrderTypeSums;

        for (DeptInfo deptInfo : deptInfos) {
            Integer deptId = deptInfo.getDeptId();
            Map<Integer, StoreSummaryDto> dtos = deptOrderTypeSums.get(deptId);
            if (dtos == null) {
                dtos = new HashMap<Integer, StoreSummaryDto>();
            }
            for (int i = 1; i < 7; i++) {
                StoreSummaryDto dto = dtos.get(i);
                if (dto == null) {
                    dto = new StoreSummaryDto();
                    dtos.put(i, dto);
                }
            }
            deptOrderTypeSums.put(deptId, dtos);

            StoreSummaryDto deptSumDto = deptSums.get(deptId);
            if (deptSumDto == null) {
                deptSumDto = new StoreSummaryDto();
            }
            deptSums.put(deptId, deptSumDto);

            StoreSummaryDto deptExpensesSumDto = deptExpensesSums.get(deptId);
            if (deptExpensesSumDto == null) {
                deptExpensesSumDto = new StoreSummaryDto();
            }
            deptExpensesSums.put(deptId, deptExpensesSumDto);
        }

        for (int i = 1; i < 7; i++) {
            StoreSummaryDto ordrTypeSumDto = orderTypeSums.get(i);
            if (ordrTypeSumDto == null) {
                ordrTypeSumDto = new StoreSummaryDto();
            }
            orderTypeSums.put(i, ordrTypeSumDto);
        }
    }


    /**
    * 计算时间差的统计数据
    * @author 张进军
    * @date Jan 16, 2016 8:58:39 PM
    * @param summaryDtos        本次统计数据
    * @param lastSummaryDtos    最后一次统计数据
    * @param dateLen            日期差
     */
    private void computeSums(List<StoreSummaryDto> summaryDtos,
            List<StoreSummaryDto> lastSummaryDtos, int dateLen) {

        if (summaryDtos == null) {
            return;
        }
        
        for (StoreSummaryDto dto : summaryDtos) {
            totalAmount += dto.getAmount();
            totalExpenses += dto.getExpenses();
            totalIncome += dto.getIncome();
            totalRealCashAmount += dto.getCashRealAmount();
            totalCashAmount += dto.getCashAmount();
            totalWechatAmount += dto.getWechatAmount();
            totalAlipayAmount += dto.getAlipayAmount();
            totalGroupAmount += dto.getGroupAmount();
            totalUnionpayAmount += dto.getUnionpayAmount();

            Integer deptId = dto.getDeptId();
            String date = dto.getDate();
            date = date.substring(0, dateLen);

            Map<String, StoreSummaryDto> deptDateDtos = deptDateSums.get(deptId);
            if (deptDateDtos == null) {
                deptDateDtos = new HashMap<String, StoreSummaryDto>();
            }
            StoreSummaryDto deptDateDto = deptDateDtos.get(date);
            if (deptDateDto == null) {
                deptDateDto = new StoreSummaryDto();
            }
            deptDateDto.setDeptId(deptId);
            deptDateDto.setAmount(deptDateDto.getAmount() + dto.getAmount());
            deptDateDto.setDate(date);
            deptDateDto.setExpenses(deptDateDto.getExpenses() + dto.getExpenses());
            deptDateDto.setIncome(deptDateDto.getIncome() + dto.getIncome());
            deptDateDtos.put(date, deptDateDto);
            deptDateSums.put(deptId, deptDateDtos);

            StoreSummaryDto deptSumDto = deptSums.get(deptId);
            if (deptSumDto == null) {
                deptSumDto = new StoreSummaryDto();
            }
            deptSumDto.setIncome(deptSumDto.getIncome() + dto.getIncome());
            deptSumDto.setAmount(deptSumDto.getAmount() + dto.getAmount());
            deptSumDto.setExpenses(deptSumDto.getExpenses() + dto.getExpenses());
            deptSumDto.setCashRealAmount(deptSumDto.getCashRealAmount() + dto.getCashRealAmount());
            deptSumDto.setCashAmount(deptSumDto.getCashAmount() + dto.getCashAmount());
            deptSumDto.setUnionpayAmount(deptSumDto.getUnionpayAmount() + dto.getUnionpayAmount());
            deptSumDto.setWechatAmount(deptSumDto.getWechatAmount() + dto.getWechatAmount());
            deptSumDto.setAlipayAmount(deptSumDto.getAlipayAmount() + dto.getAlipayAmount());
            deptSumDto.setGroupAmount(deptSumDto.getGroupAmount() + dto.getGroupAmount());
            deptSums.put(deptId, deptSumDto);


            StoreSummaryDto dateSumDto = dateSums.get(date);
            if (dateSumDto == null) {
                dateSumDto = new StoreSummaryDto();
            }
            dateSumDto.setIncome(dateSumDto.getIncome() + dto.getIncome());
            dateSumDto.setExpenses(dateSumDto.getExpenses() + dto.getExpenses());
            dateSumDto.setAmount(dateSumDto.getAmount() + dto.getAmount());
            dateSumDto.setDate(date);
            dateSums.put(date, dateSumDto);

            StoreSummaryDto deptExpensesSumDto = deptExpensesSums.get(deptId);
            if (deptExpensesSumDto == null) {
                deptExpensesSumDto = new StoreSummaryDto();
            }
            deptExpensesSumDto.setAppointOff(deptExpensesSumDto.getAppointOff() + dto.getAppointOff());
            deptExpensesSumDto.setComboAmount(deptExpensesSumDto.getComboAmount() + dto.getComboAmount());
            deptExpensesSumDto.setCouponAmount(deptExpensesSumDto.getCouponAmount() + dto.getCouponAmount());
            deptExpensesSumDto.setDebtAmount(deptExpensesSumDto.getDebtAmount() + dto.getDebtAmount());
            deptExpensesSumDto.setGiftAmount(deptExpensesSumDto.getGiftAmount() + dto.getGiftAmount());
            deptExpensesSumDto.setCardAmount(deptExpensesSumDto.getCardAmount() + dto.getCardAmount());
            deptExpensesSumDto.setExpenses(deptExpensesSumDto.getExpenses() + dto.getExpenses());
            deptExpensesSums.put(deptId, deptExpensesSumDto);

            Integer orderType = dto.getOrderType();
            Map<Integer, StoreSummaryDto> orderTypeSumDtos = deptOrderTypeSums.get(deptId);
            if (orderTypeSumDtos == null) {
                orderTypeSumDtos = new HashMap<Integer, StoreSummaryDto>();
            }
            StoreSummaryDto orderTypeSumDto = orderTypeSumDtos.get(orderType);
            if (orderTypeSumDto == null) {
                orderTypeSumDto = new StoreSummaryDto();
            }
            orderTypeSumDto.setAlipayAmount(orderTypeSumDto.getAlipayAmount() + dto.getAlipayAmount());
            orderTypeSumDto.setCashAmount(orderTypeSumDto.getCashAmount() + dto.getCashAmount());
            orderTypeSumDto.setCashRealAmount(orderTypeSumDto.getCashRealAmount() + dto.getCashRealAmount());
            orderTypeSumDto.setGroupAmount(orderTypeSumDto.getGroupAmount() + dto.getGroupAmount());
            orderTypeSumDto.setUnionpayAmount(orderTypeSumDto.getUnionpayAmount() + dto.getUnionpayAmount());
            orderTypeSumDto.setWechatAmount(orderTypeSumDto.getWechatAmount() + dto.getWechatAmount());
            orderTypeSumDto.setAmount(orderTypeSumDto.getAmount() + dto.getAmount());
            orderTypeSumDto.setOrderType(orderType);
            orderTypeSumDto.setIncome(orderTypeSumDto.getIncome() + dto.getIncome());
            orderTypeSumDtos.put(orderType, orderTypeSumDto);
            deptOrderTypeSums.put(deptId, orderTypeSumDtos);

            Map<Integer, StoreSummaryDto> orderTypeSumDtos2 = dateOrderTypeSums.get(date);
            if (orderTypeSumDtos2 == null) {
                orderTypeSumDtos2 = new HashMap<Integer, StoreSummaryDto>();
            }
            StoreSummaryDto orderTypeSumDto2 = orderTypeSumDtos2.get(orderType);
            if (orderTypeSumDto2 == null) {
                orderTypeSumDto2 = new StoreSummaryDto();
            }
            orderTypeSumDto2.setAlipayAmount(orderTypeSumDto2.getAlipayAmount() + dto.getAlipayAmount());
            orderTypeSumDto2.setCashAmount(orderTypeSumDto2.getCashAmount() + dto.getCashAmount());
            orderTypeSumDto2.setCashRealAmount(orderTypeSumDto2.getCashRealAmount() + dto.getCashRealAmount());
            orderTypeSumDto2.setGroupAmount(orderTypeSumDto2.getGroupAmount() + dto.getGroupAmount());
            orderTypeSumDto2.setUnionpayAmount(orderTypeSumDto2.getUnionpayAmount() + dto.getUnionpayAmount());
            orderTypeSumDto2.setWechatAmount(orderTypeSumDto2.getWechatAmount() + dto.getWechatAmount());
            orderTypeSumDto2.setAmount(orderTypeSumDto2.getAmount() + dto.getAmount());
            orderTypeSumDto2.setOrderType(orderType);
            orderTypeSumDto2.setIncome(orderTypeSumDto2.getIncome() + dto.getIncome());
            orderTypeSumDtos2.put(orderType, orderTypeSumDto2);
            dateOrderTypeSums.put(date, orderTypeSumDtos2);

            StoreSummaryDto orderTypeSumDto3 = orderTypeSums.get(orderType);
            if (orderTypeSumDto3 == null) {
                orderTypeSumDto3 = new StoreSummaryDto();
            }
            orderTypeSumDto3.setAlipayAmount(orderTypeSumDto3.getAlipayAmount() + dto.getAlipayAmount());
            orderTypeSumDto3.setCashAmount(orderTypeSumDto3.getCashAmount() + dto.getCashAmount());
            orderTypeSumDto3.setCashRealAmount(orderTypeSumDto3.getCashRealAmount() + dto.getCashRealAmount());
            orderTypeSumDto3.setGroupAmount(orderTypeSumDto3.getGroupAmount() + dto.getGroupAmount());
            orderTypeSumDto3.setUnionpayAmount(orderTypeSumDto3.getUnionpayAmount() + dto.getUnionpayAmount());
            orderTypeSumDto3.setWechatAmount(orderTypeSumDto3.getWechatAmount() + dto.getWechatAmount());
            orderTypeSumDto3.setAmount(orderTypeSumDto3.getAmount() + dto.getAmount());
            orderTypeSumDto3.setOrderType(orderType);
            orderTypeSumDto3.setIncome(orderTypeSumDto3.getIncome() + dto.getIncome());
            orderTypeSums.put(orderType, orderTypeSumDto3);
        }

        if (lastSummaryDtos != null) {
            for (StoreSummaryDto dto : lastSummaryDtos) {
                lastTotalAmount += dto.getAmount();
                lastTotalExpenses += dto.getExpenses();
                lastTotalIncome += dto.getIncome();
                lastTotalRealCashAmount += dto.getCashRealAmount();

                Integer deptId = dto.getDeptId();
                String date = dto.getDate();

                Map<String, StoreSummaryDto> deptDateDtos = lastDateDeptSums.get(deptId);
                if (deptDateDtos == null) {
                    deptDateDtos = new HashMap<String, StoreSummaryDto>();
                }
                StoreSummaryDto deptDateDto = deptDateDtos.get(date);
                if (deptDateDto == null) {
                    deptDateDto = new StoreSummaryDto();
                }
                deptDateDto.setDeptId(deptId);
                deptDateDto.setAmount(deptDateDto.getAmount() + dto.getAmount());
                deptDateDto.setDate(date);
                deptDateDto.setExpenses(deptDateDto.getExpenses() + dto.getExpenses());
                deptDateDto.setIncome(deptDateDto.getIncome() + dto.getIncome());
                deptDateDtos.put(date, deptDateDto);
                lastDateDeptSums.put(deptId, deptDateDtos);

                Integer orderType = dto.getOrderType();
                Map<Integer, StoreSummaryDto> deptOrderTypeSumDtos = lastDeptOrderTypeSums.get(deptId);
                if (deptOrderTypeSumDtos == null) {
                    deptOrderTypeSumDtos = new HashMap<Integer, StoreSummaryDto>();
                }
                StoreSummaryDto orderTypeSumDto = deptOrderTypeSumDtos.get(orderType);
                if (orderTypeSumDto == null) {
                    orderTypeSumDto = new StoreSummaryDto();
                }
                orderTypeSumDto.setAlipayAmount(orderTypeSumDto.getAlipayAmount() + dto.getAlipayAmount());
                orderTypeSumDto.setCashAmount(orderTypeSumDto.getCashAmount() + dto.getCashAmount());
                orderTypeSumDto.setCashRealAmount(orderTypeSumDto.getCashRealAmount() + dto.getCashRealAmount());
                orderTypeSumDto.setGroupAmount(orderTypeSumDto.getGroupAmount() + dto.getGroupAmount());
                orderTypeSumDto.setUnionpayAmount(orderTypeSumDto.getUnionpayAmount() + dto.getUnionpayAmount());
                orderTypeSumDto.setWechatAmount(orderTypeSumDto.getWechatAmount() + dto.getWechatAmount());
                orderTypeSumDto.setAmount(orderTypeSumDto.getAmount() + dto.getAmount());
                orderTypeSumDto.setOrderType(orderType);
                orderTypeSumDto.setIncome(orderTypeSumDto.getIncome() + dto.getIncome());
                deptOrderTypeSumDtos.put(orderType, orderTypeSumDto);
                lastDeptOrderTypeSums.put(deptId, deptOrderTypeSumDtos);
            }
        }
    }

    public List<DeptInfo> getDeptInfos() {
        return deptInfos;
    }


    public double getTotalIncome() {
        return totalIncome;
    }


    public double getTotalExpenses() {
        return totalExpenses;
    }


    public double getTotalAmount() {
        return totalAmount;
    }


    public double getLastTotalIncome() {
        return lastTotalIncome;
    }


    public double getLastTotalExpenses() {
        return lastTotalExpenses;
    }


    public double getLastTotalAmount() {
        return lastTotalAmount;
    }


    public String getRatio() {
        return ratio;
    }


    public Map<Integer, Map<String, StoreSummaryDto>> getDeptDateSums() {
        return deptDateSums;
    }


    public Map<Integer, Map<String, StoreSummaryDto>> getLastDateDeptSums() {
        return lastDateDeptSums;
    }


    public Map<Integer, StoreSummaryDto> getDeptSums() {
        return deptSums;
    }


    public Map<String, StoreSummaryDto> getDateSums() {
        return dateSums;
    }


    public Map<Integer, StoreSummaryDto> getDeptExpensesSums() {
        return deptExpensesSums;
    }


    public Map<Integer, Map<Integer, StoreSummaryDto>> getDeptOrderTypeSums() {
        return deptOrderTypeSums;
    }


    public double getTotalRealCashAmount() {
        return totalRealCashAmount;
    }


    public double getLastTotalRealCashAmount() {
        return lastTotalRealCashAmount;
    }


    public Map<Integer, Map<Integer, StoreSummaryDto>> getLastDeptOrderTypeSums() {
        return lastDeptOrderTypeSums;
    }


    public Map<String, Map<Integer, StoreSummaryDto>> getDateOrderTypeSums() {
        return dateOrderTypeSums;
    }


    public Map<Integer, StoreSummaryDto> getOrderTypeSums() {
        return orderTypeSums;
    }


    public double getTotalWechatAmount() {
        return totalWechatAmount;
    }


    public double getTotalAlipayAmount() {
        return totalAlipayAmount;
    }


    public double getTotalGroupAmount() {
        return totalGroupAmount;
    }


    public double getTotalUnionpayAmount() {
        return totalUnionpayAmount;
    }


    public double getTotalCashAmount() {
        return totalCashAmount;
    }

}
