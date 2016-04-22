package com.zefun.web.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.StoreInfo;

/**
  *@author Administrator
  *@date 2016年1月14日
  *@description 门店劳动业绩分类汇总
  */
@SuppressWarnings("unused")
public class DeptLaborSummaryDto extends SummaryResultDto{
	/**
    * @author 乐建建
    * @date 2016年2月19日 下午8:48:51
    * @param year 指定年
    * @param month 指定月
    * @return 指定年月的天数
    */
    public static int getLastDay(int year, int month) {
        int day = 1;
        Calendar cal = Calendar.getInstance();
        cal.set(year, month-1, day);
        int last = cal.getActualMaximum(Calendar.DATE);
        return last;
    }
	/**平均客单价*/
	private BigDecimal avgPrice;
	
	/**客单数量*/
	private Integer customerNum;
	 
	 /**部门汇总*/
	private List<ServiceReportDto> deptsSummary;
	 
	/**部门id和名称对应表*/
	private Map<Integer, String> idForName;

	/**营业收入增长率*/
	private BigDecimal incomeIncrementRate;
	
	/**上期排名 项目名与排名对应*/
	private Map<String, Integer> lastRanks;

	/**上期项目劳动业绩汇总*/
	private BigDecimal lastTotalLaborIncome;

	/**项目劳动业绩排行*/
	private List<ProjectLaborRank> projectLaborAchievement;
	
	/**总劳动业绩*/
	private BigDecimal totalLaborIncome =new BigDecimal(0);
	
	/**总服务人数*/
	private Integer totalServicedCustomer;
	/**上期总服务人数*/
	private Integer lastTotalServicedCustomer;
	
    /**趋势数据*/
	private List<TrendDeptDataDto> trendData;
	
	/**增长率*/
	private BigDecimal incomeGrowthRate;
	/**本日客单价*/
	private BigDecimal customerPrice;
	/**上期客单价*/
	private BigDecimal lastCustomerPrice;
    /**
	 *  默认构造函数
	 */
	public DeptLaborSummaryDto() {
	    
	}
    /**
	* @author 乐建建
	* @date 2016年1月18日 上午11:09:27
	* @param type 日期类型
	* @param stores 门店列表
	* @param deptInShop 门店下部门
	* @param lastsummaryDto 上期门店汇总
	* @param summaryDto 当期门店汇总
	* @param deptsSummary2 部门汇总
	* @param ranks 当期排名
	* @param lastranks2  上期排名
	* @param trendData  趋势数据
	* @param begin  起始时间
    * @param end  终止时间 
    * @param storeId 门店id
	*/
	public DeptLaborSummaryDto(List<StoreInfo> stores, String begin, String end, List<TrendDeptDataDto> trendData, 
	        Integer type, List<DeptInfo> deptInShop, 
	        DeptLaborSummaryDto lastsummaryDto, DeptLaborSummaryDto summaryDto,
			  List<ServiceReportDto> deptsSummary2, List<ProjectLaborRank> ranks, List<ProjectLaborRank> lastranks2,
			  Integer storeId) {		
		processDeptLabor(summaryDto, lastsummaryDto, deptsSummary2);
		this.deptsSummary=deptsSummary2;
		this.idForName=convertId2Name(deptInShop);
		this.projectLaborAchievement=processRanks(ranks, this.idForName);
		this.projectLaborAchievement=processRank(lastranks2, this.projectLaborAchievement);		
		this.dateType=type;
        int scale = 2;
		
		this.totalLaborIncome=summaryDto==null?null:summaryDto.getTotalLaborIncome();
		this.totalServicedCustomer=summaryDto==null?null:summaryDto.getTotalServicedCustomer();
		this.customerPrice = countCustomerPrice(summaryDto.getTotalLaborIncome(), summaryDto.getTotalServicedCustomer());
		this.lastTotalServicedCustomer=lastsummaryDto==null?null:lastsummaryDto.getTotalServicedCustomer();
		if (dateType!=null && (dateType==2 || dateType==3 || dateType==4)){
		    this.trendData=computeMonthTrend(trendData, this.dateType, begin);
		}		
		//如果是根据日期区间查询 则上期劳动业绩为0
		if (lastsummaryDto==null){
		    this.lastTotalLaborIncome=null;
		    this.incomeGrowthRate = null;
		    this.lastCustomerPrice = null;
		}
		else {
		    this.lastTotalLaborIncome=this.dateType==null?new BigDecimal(0.00):lastsummaryDto.getTotalLaborIncome();
			this.incomeGrowthRate= countIncomeGrowthRate(summaryDto, this.lastTotalLaborIncome);
			this.lastCustomerPrice=countLastCustomerPrice(this.lastTotalLaborIncome, this.lastTotalServicedCustomer);
		}
		if (summaryDto == null) {
		    this.incomeIncrementRate = null;
		}
		else {
		    this.incomeIncrementRate=this.dateType==null?new BigDecimal(0.00):summaryDto.getIncomeIncrementRate();
		}
		this.customerNum=summaryDto==null?null:summaryDto.getCustomerNum();
		//如果是根据日期区间查询 则劳动业绩增长率为0
		this.avgPrice=this.totalServicedCustomer == 0
		        ? new BigDecimal(0.00):this.totalLaborIncome.divide(new BigDecimal(this.totalServicedCustomer), BigDecimal.ROUND_FLOOR);;
		this.begin=begin.replace('-', '/');
		this.end=end.replace('-', '/');
		this.branchStores=stores;
		this.storeId=storeId;
	}
	/**
    * @author 乐建建
    * @date 2016年1月20日 下午5:21:21
    * @param trendData2 查询回来的某个特定部门的数据
    * @param type 日期类型
    * @param begin 开始日期
    * @return 某个部门的一年的数据
    */
    private List<TrendDeptDataDto> computeMonthTrend(
            List<TrendDeptDataDto> trendData2, Integer type, String begin) {
        List<TrendDeptDataDto> resultData=new ArrayList<TrendDeptDataDto>();
        Integer size=12;
        if (type==3) {
            int year=Integer.parseInt(begin.substring(0, 4));
            int month=Integer.parseInt(begin.substring(5, 7));
            size=getLastDay(year, month);
        }
        Map<Integer, BigDecimal> monthsData=new HashMap<Integer, BigDecimal>();
        if (trendData2!=null){
            //美容部 美发部 美甲部
            for (int i=0; i<trendData2.size(); i++){
                
                for (int k =1 ; k<size+1 ;  k++){
                    monthsData.put(k, new BigDecimal(0));
                }
                
                //美容部
                TrendDeptDataDto dto=trendData2.get(i);
                //美容部下面的数据库存在的月份数据
                List<DeptSummaryByDayDto> dateData= dto.getTrendDeptData();
                if (dto.getTrendDeptData().size()<size){
                    
                    for (int j =0 ; j<dateData.size(); j++){
                        if (dateData.get(j)!=null && dateData.get(j).getCurrDate()!=null){
                            monthsData.put(Integer.parseInt(dateData.get(j).getCurrDate()), dateData.get(j).getDeptSum());
                        }                        
                    }
                    dateData.clear();
                    for (int j=1; j<size+1 ; j++){
                        BigDecimal value=monthsData.get(j);
                        dateData.add(new DeptSummaryByDayDto(value, j+""));
                    }
                }
                resultData.add(new TrendDeptDataDto(dto.getDeptId(), dateData));
            }
        }

        return resultData;
    }
    /**
	 * @param deptInShop 给定门店下所有的部门信息
	 * @return 部门id和name的对应表
	 */
	private Map<Integer, String> convertId2Name(List<DeptInfo> deptInShop) {
		Map<Integer, String> idForName=new HashMap<Integer, String>();
		for (int i=0; i<deptInShop.size(); i++){
		    if (deptInShop.get(i).getIsResults()!=null && deptInShop.get(i).getIsResults()==1){
		        String name=deptInShop.get(i).getDeptName();
	            Integer id=deptInShop.get(i).getDeptId();
	            idForName.put(id, name);
		    }			
		}
		return idForName;
	}
	/**
	 * @param 计算customerPrice
	 * @param num  参数
	 * @param num1  参数1
	 * @return customerPrice
	 */
	private BigDecimal countCustomerPrice(BigDecimal num, Integer num1){
		if (num.compareTo(new BigDecimal(0)) == 0){
			return new BigDecimal(0);
		}
		else if (num1 == 0) {
			return new BigDecimal(0);
		}
		return num.divide(new BigDecimal(num1), 2, BigDecimal.ROUND_HALF_UP);
	}
    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public Integer getCustomerNum() {
		return customerNum;
	}
    public List<ServiceReportDto> getDeptsSummary() {
		return deptsSummary;
	}
    public Map<Integer, String> getIdForName() {
		return idForName;
	}

	public BigDecimal getIncomeIncrementRate() {
		return incomeIncrementRate;
	}
	
	public Map<String, Integer> getLastRanks() {
		return lastRanks;
	}

	
	public BigDecimal getLastTotalLaborIncome() {
		return lastTotalLaborIncome;
	}


	public List<ProjectLaborRank> getProjectLaborAchievement() {
		return projectLaborAchievement;
	}

	public BigDecimal getTotalLaborIncome() {
		return totalLaborIncome;
	}

	public Integer getTotalServicedCustomer() {
		return totalServicedCustomer;
	}

	public List<TrendDeptDataDto> getTrendData() {
        return trendData;
    }

	/**
	 * @param summaryDto 当期劳动业绩汇总数据
	 * @param lastsummaryDto 上期劳动业绩汇总数据
	 * @param deptsSummary 分部门汇总收入数据
	 */
	private void processDeptLabor(DeptLaborSummaryDto summaryDto, DeptLaborSummaryDto lastsummaryDto,
				 List<ServiceReportDto> deptsSummary) {
		if (summaryDto !=null  && lastsummaryDto!=null){
			BigDecimal diff=summaryDto.getTotalLaborIncome().subtract(lastsummaryDto.getTotalLaborIncome());
			if (lastsummaryDto.getTotalLaborIncome().intValue()!=0){
				summaryDto.setIncomeIncrementRate(diff.divide(lastsummaryDto.getTotalLaborIncome(), BigDecimal.ROUND_HALF_UP));
			}
			else {
				summaryDto.setIncomeIncrementRate(null);
			}
		}
			
		if (deptsSummary!=null){
			for (int i=0; i<deptsSummary.size(); i++){
				//部门相关数据
				ServiceReportDto deptDto=deptsSummary.get(i);
				BigDecimal deptSummary=new BigDecimal(0);					
				//部门下项目数量
				Integer categoryNumInDept=deptDto.getCategoryList().size();
				
				Integer deptSales=0;
				
				//遍历部门下的系列
				for (int j=0; j<deptDto.getCategoryList().size(); j++){
					//系列下项目数量
					Integer projectNumInCategory=0;
					BigDecimal seriesSummary=new BigDecimal(0);
					//系列相关数据
					CategoryReportDto categoryDto=deptDto.getCategoryList().get(j);
//						categoryDto.getProjectList().stream().map(project -> project.getProjectIncome())
//						.reduce(BigDecimal.ZERO, BigDecimal::add);
					// projectNumInDept=projectNumInDept+categoryDto.getProjectList().size();
					
					String name=categoryDto.getCategoryName()+"汇总";
					Integer projectSales=0;
					
					projectNumInCategory=projectNumInCategory+categoryDto.getProjectList().size();
					for (int k=0; k<categoryDto.getProjectList().size(); k++){
							//系列下的项目相关数据
						ItemReportDto projectDto=categoryDto.getProjectList().get(k);
						ProjectSalesDto project=projectDto.getProjectSales();
							//将projectSales类中的salesAmount设置为ProjectReportDto的收入
						projectDto.setProjectIncome(project==null?new BigDecimal(0):project.getSalesAmount());
						seriesSummary=seriesSummary.add(projectDto.getProjectIncome());
						
						projectSales=projectSales+(project==null?0:project.getSalesCount());
						
						//统计部门下所有项目的销量
						deptSales=deptSales+(project==null?0:project.getSalesCount());
					}
					categoryDto.setCategoryIncomeSummary(seriesSummary);
					categoryDto.setProjectNumInCategory(projectNumInCategory);
					deptSummary=deptSummary.add(categoryDto.getCategoryIncomeSummary());
					
					categoryDto.getProjectList().add(new ItemReportDto(null, seriesSummary, name, projectSales));
					//categoryDto.getProjectList().add(0, new ProjectReportDto(null, seriesSummary, name, projectSales));
					Collections.reverse(categoryDto.getProjectList());
				}
				deptDto.setDeptIncome(deptSummary);
				deptDto.setDeptSales(deptSales);
				deptDto.setCategoryNumInDept(categoryNumInDept);
			}
		}			
	}

	/**
	 * @param lastranks2 上期店内各项目根据销量的排名
	 * @return 上期店内项目名与排名对应表
	 * @param projectLaborAchievement 当期排行数据
	 */
	public List<ProjectLaborRank> processRank(List<ProjectLaborRank> lastranks2, List<ProjectLaborRank> projectLaborAchievement) {
		Map<String, Integer> projectRank=new HashMap<String, Integer>();
		for (int i=0; i<lastranks2.size(); i++){
			String name=lastranks2.get(i).getProjectName();
			Integer rank=lastranks2.get(i).getProjectRank();
			projectRank.put(name, rank);
		}
		for (int i=0; i<projectLaborAchievement.size(); i++){
		    String projectName=projectLaborAchievement.get(i).getProjectName();
		    String rank="---";
		    if (projectRank.get(projectName)!=null){
		        rank=projectRank.get(projectName).toString();
		    }
		    projectLaborAchievement.get(i).setLastProjectRank(rank);
		}
		return projectLaborAchievement;
	}

	/**
    * @author 乐建建
    * @date 2016年1月27日 下午1:59:24
    * @param ranks 项目劳动业绩排行
    * @param idForName2 id和名字对应表
    * @return List<ProjectLaborRank>
    */
    private List<ProjectLaborRank> processRanks(List<ProjectLaborRank> ranks,
            Map<Integer, String> idForName2) {
        if (idForName2!=null){
            for (int i=0; i<ranks.size(); i++){
                ranks.get(i).setDeptName(idForName2.get(ranks.get(i).getDeptId()));
            }
        }        
        return ranks;
    }

	public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }
	
	public void setCustomerNum(Integer customerNum) {
		this.customerNum = customerNum;
	}
	

	public void setDeptsSummary(List<ServiceReportDto> deptsSummary) {
		this.deptsSummary = deptsSummary;
	}

	public void setIdForName(Map<Integer, String> idForName) {
		this.idForName = idForName;
	}

	public void setIncomeIncrementRate(BigDecimal incomeIncrementRate) {
		this.incomeIncrementRate = incomeIncrementRate;
	}

	public void setLastRanks(Map<String, Integer> lastRanks) {
		this.lastRanks = lastRanks;
	}

	public void setLastTotalLaborIncome(BigDecimal lastTotalLaborIncome) {
		this.lastTotalLaborIncome = lastTotalLaborIncome;
	}

	public void setProjectLaborAchievement(List<ProjectLaborRank> projectLaborAchievement) {
		this.projectLaborAchievement = projectLaborAchievement;
	}

	public void setTotalLaborIncome(BigDecimal totalLaborIncome) {
		this.totalLaborIncome = totalLaborIncome;
	}
	public void setTotalServicedCustomer(Integer totalServicedCustomer) {
		this.totalServicedCustomer = totalServicedCustomer;
	}
	public Integer getLastTotalServicedCustomer() {
		return lastTotalServicedCustomer;
	}
	public void setLastTotalServicedCustomer(Integer lastTotalServicedCustomer) {
		this.lastTotalServicedCustomer = lastTotalServicedCustomer;
	}
	
	public BigDecimal getIncomeGrowthRate() {
		return incomeGrowthRate;
	}
	public void setIncomeGrowthRate(BigDecimal incomeGrowthRate) {
		this.incomeGrowthRate = incomeGrowthRate;
	}

	public BigDecimal getCustomerPrice() {
		return customerPrice;
	}
	public void setCustomerPrice(BigDecimal customerPrice) {
		this.customerPrice = customerPrice;
	}
	public BigDecimal getLastCustomerPrice() {
		return lastCustomerPrice;
	}
	public void setLastCustomerPrice(BigDecimal lastCustomerPrice) {
		this.lastCustomerPrice = lastCustomerPrice;
	}
	/**
	 * 
	* @author 张进军
	* @date Mar 19, 2016 9:56:56 PM
	* @param trendData 参数
	 */
    public void setTrendData(List<TrendDeptDataDto> trendData) {
        this.trendData = trendData;
    }
    /**
     * @author 张洋
     * @date 2016年3月21日 上午10:18:42
     * @param summaryDto 
     * @param lastTotalLaborIncomes 
     * @return incomeGrowthRates
     */
    public BigDecimal countIncomeGrowthRate(DeptLaborSummaryDto summaryDto, BigDecimal lastTotalLaborIncomes){
    	BigDecimal incomeGrowthRates = new BigDecimal(0.00);
    	if (summaryDto != null){
    		BigDecimal bigDecimalDivide = summaryDto.getTotalLaborIncome().subtract(lastTotalLaborIncomes);
    		if (lastTotalLaborIncomes.compareTo(new BigDecimal(0)) != 0){
    			incomeGrowthRates = bigDecimalDivide.divide(lastTotalLaborIncomes, 2, BigDecimal.ROUND_HALF_UP);
    		}
    	}
    	return incomeGrowthRates;
    }
    /**
     * @author 张洋
     * @date 2016年3月21日 上午10:29:44
     * @param lastTotalLaborIncomes 
     * @param lastTotalServicedCustomers 
     * @return lastCustomerPrice
     */
    public BigDecimal countLastCustomerPrice(BigDecimal lastTotalLaborIncomes, Integer lastTotalServicedCustomers){
    	BigDecimal lastCustomerPrice = new BigDecimal(0.00);
    	if (lastTotalLaborIncomes.compareTo(new BigDecimal(0)) != 0){
    		if (lastTotalServicedCustomers != 0){
    			lastCustomerPrice = lastTotalLaborIncomes.divide(new BigDecimal(lastTotalServicedCustomers), 2, BigDecimal.ROUND_HALF_UP);
    		}
    	}
    	return lastCustomerPrice;
    }
}
