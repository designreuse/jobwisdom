package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 营业分析数据
* @author 高国藩
* @date 2016年1月26日 下午3:39:40
 */
public class BusinessAnalysisDto {

    /**现金消费*/
    private BigDecimal cashConsumption;
    /**划卡消费*/
    private BigDecimal cardConsumption;
    /**疗程*/
    private BigDecimal comboDeductible;
    
    /**项目消费*/
    private BigDecimal projectConsumption;
    /**卡项(疗程)消费*/
    private BigDecimal cardComboConsumption;
    /**商品销售*/
    private BigDecimal goodsDeductible;
    
    /** 固定支出*/
    private BigDecimal fixedCost;
    /** 水电费*/
    private BigDecimal writerCost;
    /** 间接运营*/
    private BigDecimal briefOperation;
    /** 人员费用*/
    private BigDecimal staffCosts;
    /** 行政管理*/
    private BigDecimal administration;
    /** 直接运营*/
    private BigDecimal directCost;
    
    /**项目名称*/
    private String businessProject;
    /**项目支出金额*/
    private BigDecimal flowAmount;
    
    /**指定比例*/
    private BigDecimal assignRatio;
    
    
    public BigDecimal getAssignRatio() {
        return assignRatio;
    }
    public void setAssignRatio(BigDecimal assignRatio) {
        this.assignRatio = assignRatio;
    }
    public String getBusinessProject() {
        return businessProject;
    }
    public void setBusinessProject(String businessProject) {
        this.businessProject = businessProject;
    }
    public BigDecimal getFlowAmount() {
        return flowAmount;
    }
    public void setFlowAmount(BigDecimal flowAmount) {
        this.flowAmount = flowAmount;
    }
    public BigDecimal getFixedCost() {
        return fixedCost;
    }
    public void setFixedCost(BigDecimal fixedCost) {
        this.fixedCost = fixedCost;
    }
    public BigDecimal getWriterCost() {
        return writerCost;
    }
    public void setWriterCost(BigDecimal writerCost) {
        this.writerCost = writerCost;
    }
    public BigDecimal getBriefOperation() {
        return briefOperation;
    }
    public void setBriefOperation(BigDecimal briefOperation) {
        this.briefOperation = briefOperation;
    }
    public BigDecimal getStaffCosts() {
        return staffCosts;
    }
    public void setStaffCosts(BigDecimal staffCosts) {
        this.staffCosts = staffCosts;
    }
    public BigDecimal getAdministration() {
        return administration;
    }
    public void setAdministration(BigDecimal administration) {
        this.administration = administration;
    }
    public BigDecimal getDirectCost() {
        return directCost;
    }
    public void setDirectCost(BigDecimal directCost) {
        this.directCost = directCost;
    }
    public BigDecimal getCashConsumption() {
        return cashConsumption;
    }
    public void setCashConsumption(BigDecimal cashConsumption) {
        this.cashConsumption = cashConsumption;
    }
    public BigDecimal getCardConsumption() {
        return cardConsumption;
    }
    public void setCardConsumption(BigDecimal cardConsumption) {
        this.cardConsumption = cardConsumption;
    }
    public BigDecimal getComboDeductible() {
        return comboDeductible;
    }
    public void setComboDeductible(BigDecimal comboDeductible) {
        this.comboDeductible = comboDeductible;
    }
    public BigDecimal getProjectConsumption() {
        return projectConsumption;
    }
    public void setProjectConsumption(BigDecimal projectConsumption) {
        this.projectConsumption = projectConsumption;
    }
    public BigDecimal getCardComboConsumption() {
        return cardComboConsumption;
    }
    public void setCardComboConsumption(BigDecimal cardComboConsumption) {
        this.cardComboConsumption = cardComboConsumption;
    }
    public BigDecimal getGoodsDeductible() {
        return goodsDeductible;
    }
    public void setGoodsDeductible(BigDecimal goodsDeductible) {
        this.goodsDeductible = goodsDeductible;
    }
    
    /**
     * 无参构造
    * @author 高国藩
    * @date 2016年1月27日 下午4:45:48
     */
    public BusinessAnalysisDto() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * 有参构造
    * @author 高国藩
    * @date 2016年1月27日 下午4:45:30
    * @param businessProject   支出项目
    * @param flowAmount        支出金额
     */
    public BusinessAnalysisDto(String businessProject, BigDecimal flowAmount) {
        super();
        this.businessProject = businessProject;
        this.flowAmount = flowAmount;
    }
    
    
    /**
     * 有参构造
    * @author 高国藩
    * @date 2016年3月3日 上午10:33:50
    * @param cashConsumption cashConsumption
    * @param cardConsumption cashConsumption
    * @param comboDeductible cashConsumption
    * @param projectConsumption cashConsumption
    * @param cardComboConsumption cashConsumption
    * @param goodsDeductible cashConsumption
    * @param fixedCost cashConsumption
    * @param writerCost cashConsumption
    * @param briefOperation cashConsumption
    * @param staffCosts cashConsumption
    * @param administration cashConsumption
    * @param directCost cashConsumption
    * @param businessProject cashConsumption
    * @param flowAmount cashConsumption
    * @param assignRatio cashConsumption
     */
    public BusinessAnalysisDto(BigDecimal cashConsumption,
            BigDecimal cardConsumption, BigDecimal comboDeductible,
            BigDecimal projectConsumption, BigDecimal cardComboConsumption,
            BigDecimal goodsDeductible, BigDecimal fixedCost,
            BigDecimal writerCost, BigDecimal briefOperation,
            BigDecimal staffCosts, BigDecimal administration,
            BigDecimal directCost, String businessProject,
            BigDecimal flowAmount, BigDecimal assignRatio) {
        this.cashConsumption = cashConsumption;
        this.cardConsumption = cardConsumption;
        this.comboDeductible = comboDeductible;
        this.projectConsumption = projectConsumption;
        this.cardComboConsumption = cardComboConsumption;
        this.goodsDeductible = goodsDeductible;
        this.fixedCost = fixedCost;
        this.writerCost = writerCost;
        this.briefOperation = briefOperation;
        this.staffCosts = staffCosts;
        this.administration = administration;
        this.directCost = directCost;
        this.businessProject = businessProject;
        this.flowAmount = flowAmount;
        this.assignRatio = assignRatio;
    }
    /**
     * 获取左侧饼图数据
    * @author 高国藩
    * @date 2016年1月27日 下午4:44:57
    * @param analysisDto   数据
    * @return              数据集合
     */
    public static List<List<Object>> getInclude(BusinessAnalysisDto analysisDto){
        List<Object> objects1 = new ArrayList<>();
        List<Object> objects2 = new ArrayList<>();
        List<Object> objects3 = new ArrayList<>();
        if (analysisDto!=null){
            objects1.add("现金业绩  "+analysisDto.getCashConsumption());
            objects1.add(analysisDto.getCashConsumption());
            objects2.add("划卡业绩  "+analysisDto.getCardConsumption());
            objects2.add(analysisDto.getCardConsumption());
            objects3.add("疗程消费  "+analysisDto.getComboDeductible());
            objects3.add(analysisDto.getComboDeductible());
            List<List<Object>> list = new ArrayList<>();
            list.add(objects1);
            list.add(objects2);
            list.add(objects3);
            return list;
        }
        else {
            objects1.add("现金业绩  "+0);
            objects1.add(0);
            objects2.add("划卡业绩  "+0);
            objects2.add(0);
            objects3.add("疗程消费  "+0);
            objects3.add(0);
            List<List<Object>> list = new ArrayList<>();
            list.add(objects1);
            list.add(objects2);
            list.add(objects3);
            return list;
        }
        
    }
    
    /**
     * 获取右侧饼图数据
    * @author 高国藩
    * @date 2016年1月27日 下午4:44:57
    * @param analysisDto   数据
    * @return              数据集合
     */
    public static List<List<Object>> getConsumption(BusinessAnalysisDto analysisDto){
        List<Object> objects1 = new ArrayList<>();
        List<Object> objects2 = new ArrayList<>();
        List<Object> objects3 = new ArrayList<>();
        if (analysisDto!=null){
            objects1.add("项目消费  "+analysisDto.getProjectConsumption());
            objects1.add(analysisDto.getProjectConsumption());
            objects2.add("卡项/疗程销售 "+analysisDto.getCardComboConsumption());
            objects2.add(analysisDto.getCardComboConsumption());
            objects3.add("商品销售  "+analysisDto.getGoodsDeductible());
            objects3.add(analysisDto.getGoodsDeductible());
            List<List<Object>> list = new ArrayList<>();
            list.add(objects1);
            list.add(objects2);
            list.add(objects3);
            return list;
        }
        else {
            objects1.add("项目消费  "+0);
            objects1.add(0);
            objects2.add("卡项/疗程销售"+0);
            objects2.add(0);
            objects3.add("商品销售  "+0);
            objects3.add(0);
            List<List<Object>> list = new ArrayList<>();
            list.add(objects1);
            list.add(objects2);
            list.add(objects3);
            return list;
        }
    }
    
    /**
     * 获得支出总额
    * @author 高国藩
    * @date 2016年1月27日 下午4:48:12
    * @param analysisDto  数据
    * @return             金额
     */
    public static BigDecimal getExpenditureAmount(BusinessAnalysisDto analysisDto){
        if (analysisDto!=null){
            return analysisDto.getFixedCost()
                    .add(analysisDto.getWriterCost())
                    .add(analysisDto.getBriefOperation())
                    .add(analysisDto.getStaffCosts())
                    .add(analysisDto.getAdministration())
                    .add(analysisDto.getDirectCost());
        }
        else {
            return new BigDecimal(0);
        }
    }
    
    
}
