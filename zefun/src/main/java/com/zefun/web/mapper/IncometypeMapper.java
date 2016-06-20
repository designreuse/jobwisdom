package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.Incometype;
/**
 * 
* @author 骆峰
* @date 2016年6月16日 下午8:47:18
 */
public interface IncometypeMapper {
    /**
     * 删除
    * @author 骆峰
    * @date 2016年6月16日 下午8:47:36
    * @param record incometypeId
    * @return 成功删除
     */
    int deleteByPrimaryKey(Integer record);

    /**
     * 新增
    * @author 骆峰
    * @date 2016年6月16日 下午8:47:58
    * @param record int
    * @return 成功新增
     */
    int insert(Incometype record);

    /**
     * 
    * @author 骆峰
    * @date 2016年6月16日 下午8:48:20
    * @param record int
    * @return 成功新增
     */
    int insertSelective(Incometype record);

    /**
     * 查询
    * @author 骆峰
    * @date 2016年6月16日 下午8:48:20
    * @param incometypeId Incometype
    * @return 查询
     */
    Incometype selectByPrimaryKey(Integer incometypeId);

    /**
     * 
    * @author 骆峰
    * @date 2016年6月16日 下午8:48:20
    * @param record int
    * @return int
     */
    int updateByPrimaryKeySelective(Incometype record);

    /**
     * 
    * @author 骆峰
    * @date 2016年6月16日 下午8:48:20
    * @param record int
    * @return int
     */
    int updateByPrimaryKey(Incometype record);
    /**
     *  
    * @author 骆峰
    * @date 2016年6月16日 下午8:49:24
    * @param incometype 门店识别
    * @return 集合
     */
    List<Incometype> selectByListStoreId(Incometype incometype);
    
    
    
    /**
     * 
    * @author 骆峰
    * @date 2016年6月17日 15:45:19
    * @param id int
    * @return int
     */
    int updateByStoreID(Integer id);
    
    /**
     * 
    * @author 骆峰
    * @date 2016年6月18日 下午1:51:38
    * @param id 参数
    * @return 集合
     */
    List<Incometype> selectByListStore(Integer id);
}