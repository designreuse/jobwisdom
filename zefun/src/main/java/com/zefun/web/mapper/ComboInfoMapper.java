package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import com.zefun.web.dto.ComboStatementDto;
import com.zefun.web.dto.ComboSummaryDto;
import com.zefun.web.dto.DeptComboSummaryDto;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.dto.TrendDeptDataDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.vo.CardComboSalesVo;
import com.zefun.web.vo.CashComboSalesVo;
import com.zefun.web.vo.DiscountComboSalesVo;

/**
* @author 乐建建
* @date 2016年3月15日 上午10:38:07 
*/
public interface ComboInfoMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:47:01
    * @param comboId 疗程id
    * @return int
     */
    int deleteByPrimaryKey(Integer comboId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:47:10
    * @param comboInfo 疗程
    * @return int
     */
    int insertSelective(ComboInfo comboInfo);
    
    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:47:45
    * @param comboId 疗程id
    * @return ComboInfo
     */
    ComboInfo selectByPrimaryKey(Integer comboId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:48:03
    * @param comboInfo 疗程
    * @return int
     */
    int updateByPrimaryKeySelective(ComboInfo comboInfo);

    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:48:21
    * @param comboInfo 疗程
    * @return List<ComboInfoDto>
     */
    List<ComboInfo> selectByProperty(ComboInfo comboInfo);
    
    /**
     * 查询部门，疗程列表
    * @author 洪秋霞
    * @date 2015年9月16日 下午2:26:56
    * @param storeId 门店id
    * @return List<DeptInfoDto>
     */
    List<DeptInfoDto> getDetpInfoByCombo(Integer storeId);
    
    /**
     * 根据门店标识、部门标识查询疗程
    * @author 王大爷
    * @date 2015年10月14日 上午9:43:35
    * @param deptId 部门标识
    * @return List<ComboInfo>
     */
    List<ComboInfo> getComboInfo(Integer deptId);
    
    /**
     * 根据门店标识查询疗程标识列表
    * @author 张进军
    * @date Dec 25, 2015 9:50:24 PM
    * @param storeId	门店标识
    * @return	疗程标识列表
     */
    List<Integer> selectComboIdByStoreId(int storeId);
    
    /**
     * 根据门店标识查询疗程报表数据
    * @author 王大爷
    * @date 2016年1月21日 下午3:11:22
    * @param map 参数
    * @return List<ComboStatementDto>
     */
    List<ComboStatementDto> selectComboByTimeSize(Map<String, Integer> map);

    /**
     * 
    * @author 王大爷
    * @date 2016年1月21日 下午3:13:10
    * @param map 参数
    * @return Map<String, Integer>
     */
    Map<String, Object> getComboTatailTime(Map<String, Object> map);
    
    /**
     * 
    * @author 乐建建
    * @date 2016年1月21日 上午11:36:40
    * @param dto 封装所需条件为对象 包括起始时间 终止时间 门店标志
    * @return 部门下疗程汇总数据
    */
    List<DeptComboSummaryDto> getComboDetail(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年1月21日 下午4:08:25
    * @param dto 封装所需条件为对象 包括起始时间 终止时间 门店标志
    * @return 疗程排行数据
    */
    List<ComboSummaryDto> getComboRank(SummaryResultDto dto);
    
    
    /**
    * @author 乐建建
    * @date 2016年1月27日 下午6:07:50
    * @param dto 封装所需条件为对象 包括起始时间 终止时间 部门id
    * @return 分部门的疗程排行数据
    */
    List<ComboSummaryDto> getComboRankByDept(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年1月19日 下午6:57:43
    * @param dto 根据条件查询趋势数据
    * @return  List<DeptSummaryByDayDto>
    */
    List<TrendDeptDataDto> getComboTrendData(SummaryResultDto dto);
    
    /**
     * 根据订单明细标识查询对应疗程是否有次数限制
     * @param detailId 明细标识
     * @return 是否（》0为存在）
     */
    Integer checkComboCountLimit(Integer detailId);
    
    /**
    * @author 乐建建
    * @date 2016年3月15日 上午10:37:10
    * @param dto 查询条件
    * @return 现金疗程购买封装类
    */
    CashComboSalesVo getCashComboSale(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年3月15日 上午10:44:23
    * @param dto 查询条件
    * @return 卡金疗程购买封装类
    */
    CardComboSalesVo getCardComboSale(SummaryResultDto dto);
    
    /**
     * @author 张洋
     * @date 2016年3月19日 下午13:26:10
     * @param dto 查询条件
     * @return 抵扣疗程封装类
     */
    DiscountComboSalesVo getDiscountComboSale(SummaryResultDto dto);
}