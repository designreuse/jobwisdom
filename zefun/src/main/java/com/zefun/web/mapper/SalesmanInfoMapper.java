package com.zefun.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zefun.web.entity.Page;
import com.zefun.web.entity.SalesmanInfo;

/**
 * 业务员信息映射
 * @author lzc
 *
 */
public interface SalesmanInfoMapper {
	/**
	 * 根据业务员id删除业务员
	 * @param salesmanId  业务员id
	 * @return  成功数目
	 */
    int deleteByPrimaryKey(Integer salesmanId);

    /**
     * 新增业务员
     * @param record  业务员实体
     * @return  成功数目
     */
    int insert(SalesmanInfo record);

    /**
     * 选择性新增业务员
     * @param record  业务员实体
     * @return  成功数目
     */
    int insertSelective(SalesmanInfo record);

    /**
     * 根据业务员id查询业务员
     * @param salesmanId  业务员id
     * @return  业务员实体
     */
    SalesmanInfo selectByPrimaryKey(Integer salesmanId);

    /**
     * 根据主键选择性更新业务员
     * @param record  业务员实体
     * @return  成功数目
     */
    int updateByPrimaryKeySelective(SalesmanInfo record);

    /**
     * 根据主键更新业务员
     * @param record  业务员实体
     * @return  成功数目
     */
    int updateByPrimaryKey(SalesmanInfo record);
    
    /**
     * 根据渠道(代理)商id查询业务员
     * @param agentId  渠道(代理)商id
     * @return  业务员集
     */
    List<SalesmanInfo> selectSalesmanInfoByAgentId(int agentId);
    
    /**
     * 分页查询业务员
     * @param page  分页查询参数
     * @return  业务员集
     */
    List<SalesmanInfo> selectSalesmanInfoByPage(Page<SalesmanInfo> page);
    
    /**
     * 根据微信标识查询业务员
     * @param openId  微信标识
     * @return  业务员信息
     */
    SalesmanInfo selectSalesmanInfoByOpenId(String openId);
    
    /**
     * 根据手机号和密码查询业务员(未被删除)
     * @param phone  手机号
     * @param password  密码
     * @return  业务员信息
     */
    SalesmanInfo selectSalesmanInfoByPhone(@Param(value="phone")String phone, @Param(value="password")String password);
    
    /**
     * 根据渠道id查看业务员总数(没删除的业务员)
     * @param agentId  渠道id
     * @return  业务员总数
     */
    int selectCountSalesmanByAgentId(@Param(value = "agentId")int agentId);
    
    /**
     * 根据微信标识查询业务员(没删除的业务员)
     * @param openId  微信标识
     * @return  业务员
     */
    SalesmanInfo selectSalesmanByOpenId(@Param(value="openId")String openId);
    /**
     * 根据微信标识查询业务员(没删除没停用的业务员)
    * @author DavidLiang
    * @date 2016年1月18日 下午8:12:27
    * @param openId  微信标识
    * @return  业务员
     */
    SalesmanInfo selectSalesmanByOpenIdNotDeleteNotDisable(@Param(value="openId")String openId);
    /**
     * 根据微信标识查询业务员(不管状态)
     * @param openId  微信标识
     * @return  业务员
     */
    SalesmanInfo selectSalesmanByOpenIdIgnoreStatus(@Param(value="openId")String openId);
    
    /**
     * 停用业务员
     * @param salesmanId  业务员id
     * @return  成功数目
     */
    int deactivateSalesman(@Param(value="salesmanId")int salesmanId);
    
    /**
     * 删除业务员
     * @param salesmanId  业务员id
     * @return  成功数目
     */
    int deleteSalesman(@Param(value="salesmanId")int salesmanId);
    
    /**
     * 根据电话查询业务员
     * @param phone  电话
     * @return  业务员实体
     */
    SalesmanInfo selectSalesmanByPhone(@Param(value = "phone")String phone);
    
    /**
     * 根据渠道商openId和业务员openId查询业务员(通常检查业务员是否在渠道下)
    * @author DavidLiang
    * @date 2016年1月18日 下午8:34:36
    * @param agentOpenId  渠道商openId
    * @param salesmanOpenId  业务员openId
    * @return  业务员实体
     */
    SalesmanInfo selectSalesmanByAgentOpenIdAndSalesmanOpenId(
    		  @Param(value = "agentOpenId")String agentOpenId, @Param(value = "salesmanOpenId")String salesmanOpenId);
    
    /**
     * 根据店铺标识(storeId)查询他的推荐者业务员名字
    * @author DavidLiang
    * @date 2016年1月21日 下午6:09:58
    * @param storeId  店铺标识
    * @return  业务员名字
     */
    String selectSalesmanNameByStoreId(@Param(value = "storeId")int storeId);
}