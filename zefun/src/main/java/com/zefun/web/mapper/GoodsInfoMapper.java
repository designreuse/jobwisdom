package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.DeptGoodSalesSummaryDto;
import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.GoodSalesSummaryDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.dto.TrendDeptDataDto;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.Page;
import com.zefun.web.vo.CardStoreSalesVo;
import com.zefun.web.vo.CashStoreSalesVo;

/**
 * 商品信息
* @author 洪秋霞
* @date 2015年8月11日 上午10:59:55
 */
public interface GoodsInfoMapper {
    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:00:08
    * @param goodsId 商品id
    * @return int
     */
    int deleteByPrimaryKey(Integer goodsId);

    /**
     * 插入
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:00:34
    * @param goodsInfo 商品信息
    * @return int
     */
    int insertSelective(GoodsInfo goodsInfo);

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:00:45
    * @param goodsId 商品id
    * @return GoodsInfo
     */
    GoodsInfo selectByPrimaryKey(Integer goodsId);

    /**
     * 更新
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:00:58
    * @param goodsInfo 商品信息
    * @return int
     */
    int updateByPrimaryKeySelective(GoodsInfo goodsInfo);
    
    /**
     * 动态查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:01:09
    * @param goodsInfo 商品信息
    * @return List<GoodsInfo>
     */
    List<GoodsInfo> selectByProperty(GoodsInfo goodsInfo);
    
    /**
     * 根据门店id查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:01:26
    * @param storeId 门店id
    * @return List<GoodsInfo>
     */
    List<GoodsInfo> selectByStoreId(Integer storeId);

    /**
     * 查询商品库存分页
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:01:43
    * @param page 分页对象
    * @return List<GoodsInfoDto>
     */
    List<GoodsInfoDto> selectGoodsInfoPage(Page<GoodsInfoDto> page);

    /**
     * 根据部门标识查询商品信息
    * @author 高国藩
    * @date Oct 15, 2015 12:02:09 AM
    * @param deptId    部门标识
    * @return 部门下的商品信息
     */
    DeptGoodsBaseDto selectDeptGoodsByDeptId(Integer deptId);
    
    /**
     * 根据商品id查询该商品所有数据
    * @author 高国藩
    * @date 2015年10月17日 上午11:50:51
    * @param goodsId 商品id
    * @return 商品
     */
    GoodsInfoDto selectGoodsAllByPrimaryKey(Integer goodsId);

    /**
     * 将类别下面的商品删除
    * @author 高国藩
    * @date 2015年10月27日 下午6:30:05
    * @param categoryId 类别ID
    * @return 返回影响行数
     */
    int deleteByCategoryId(Integer categoryId);
    
    /**
     * 增加对应商品库存
    * @author 王大爷
    * @date 2015年12月2日 下午2:35:16
    * @param goodsInfo 参数
    * @return 是否成功
     */
    Integer updateGoodsStock(GoodsInfo goodsInfo);
    
    
    /**
    * @author 乐建建
    * @date 2016年1月22日 下午8:41:26
    * @param dto 封装参数条件 比如起始时间 终止时间 门店id
    * @return  汇总后部门商品数据
    */
    List<DeptGoodSalesSummaryDto> getGoodsInfo(SummaryResultDto dto);
    
    
    /**
    * @author 乐建建
    * @date 2016年1月23日 上午11:42:53
    * @param dto 封装参数条件 比如起始时间 终止时间 门店id
    * @return 按商品汇总后 进行排行 
    */
    List<GoodSalesSummaryDto> getGoodRankInDept(SummaryResultDto dto);
    
    /**
    * @author 乐建建
    * @date 2016年1月23日 下午2:21:49
    * @param dto 封装参数条件 比如起始时间 终止时间 门店id
    * @return 以月为单位的商品销售额数据
    */
    List<TrendDeptDataDto> getDeptGoodTrendData(SummaryResultDto dto);
    
    
    /**
    * @author 乐建建
    * @date 2016年1月28日 上午11:32:04
    * @param dto 封装参数条件 比如起始时间 终止时间 部门id
    * @return 分部门的商品销售排行数据
    */
    List<GoodSalesSummaryDto> getGoodRankByDept(SummaryResultDto dto);
    
    /**
     * @author 张洋
     * @date 2016年3月19日 下午20:09:48
     * @param dto 现金封装
     * @return 结果
     */
    CashStoreSalesVo getCashStoreSale(SummaryResultDto dto);
    
    /**
     * @author 张洋
     * @date 2016年3月19日 下午20:10:03
     * @param dto 卡金封装
     * @return 结果
     */
    CardStoreSalesVo getCardStoreSale(SummaryResultDto dto);
}