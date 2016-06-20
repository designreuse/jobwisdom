package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.entity.InitializeInFo;
import com.zefun.web.entity.Page;
/**
 * 
* @author 骆峰
* @date 2016年6月18日 上午11:46:15
 */
public interface InitializeInFoMapper {
    /**
     * 
    * @author 骆峰
    * @date 2016年6月18日 上午11:46:26
    * @param id  参数
    * @return int
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 
    * @author 骆峰
    * @date 2016年6月18日 上午11:46:42
    * @param record 参数
    * @return int
     */
    int insert(InitializeInFo record);
    /**
     * 
    * @author 骆峰
    * @date 2016年6月18日 上午11:46:48
    * @param record 参数
    * @return int
     */
    int insertSelective(InitializeInFo record);
    /**
     * 
    * @author 骆峰
    * @date 2016年6月18日 上午11:46:52
    * @param id 参数
    * @return InitializeInFo
     */
    InitializeInFo selectByPrimaryKey(Integer id);
    /**
     * 
    * @author 骆峰
    * @date 2016年6月18日 上午11:46:56
    * @param record 参数
    * @return int
     */
    int updateByPrimaryKeySelective(InitializeInFo record);
    
    /**
     * 
    * @author 骆峰
    * @date 2016年6月18日 上午11:47:01
    * @param record 参数
    * @return int
     */
    int updateByPrimaryKey(InitializeInFo record);
    /**
     *  收支记账条件查询
    * @author 骆峰
    * @date 2016年6月18日 下午12:06:58
    * @param page page
    * @return 集合
     */
    List<InitializeInFo> selectByListStoreId(Page<InitializeInFo> page);
    

    /**
     * 分页查询方法 
    * @author 骆峰
    * @date 2016年6月20日 下午2:01:38
    * @param page page
    * @return     page
     */
    List<InitializeInFo> selectByPage(Page<InitializeInFo> page);
    
    
  
}