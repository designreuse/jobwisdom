package com.zefun.web.mapper;

import com.zefun.web.entity.TransactionInfo;


/**
 * 交易信息操作对象
* @author 张进军
* @date Mar 4, 2016 11:03:16 AM
 */
public interface TransactionInfoMapper {
    /**
     * 新增交易信息
    * @author 张进军
    * @date Mar 4, 2016 11:03:44 AM
    * @param record 交易信息
    * @return   0:失败，1:成功
     */
    int insert(TransactionInfo record);

    /**
     * 根据交易标识查询交易信息
    * @author 张进军
    * @date Mar 4, 2016 11:04:04 AM
    * @param transactionId  交易标识
    * @return   交易信息
     */
    TransactionInfo selectByPrimaryKey(String transactionId);

    /**
     * 修改交易信息
    * @author 张进军
    * @date Mar 4, 2016 11:04:21 AM
    * @param record 交易信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(TransactionInfo record);
}