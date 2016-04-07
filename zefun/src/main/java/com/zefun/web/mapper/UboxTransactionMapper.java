package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.ubox.UboxTransactionDto;
import com.zefun.web.entity.UboxTransaction;

/**
 * 友宝交易信息操作对象
* @author 张进军
* @date Mar 4, 2016 9:07:09 PM
 */
public interface UboxTransactionMapper {

    /**
     * 新增友宝交易信息
    * @author 张进军
    * @date Mar 4, 2016 9:07:29 PM
    * @param record     交易信息
    * @return   0:失败，1:成功
     */
    int insert(UboxTransaction record);

    /**
     * 根据交易标识查询友宝交易信息
    * @author 张进军
    * @date Mar 4, 2016 9:08:47 PM
    * @param transactionId  交易标识
    * @return   交易信息
     */
    UboxTransaction selectByPrimaryKey(String transactionId);

    /**
     * 修改友宝交易信息
    * @author 张进军
    * @date Mar 4, 2016 9:09:48 PM
    * @param record 友宝交易信息
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(UboxTransaction record);
    
    /**
     * 根据会员标识查询交易列表
    * @author 张进军
    * @date Mar 5, 2016 9:46:25 AM
    * @param memberId   会员标识
    * @return   交易列表
     */
    List<UboxTransactionDto> selectTransactionListByMemberId(int memberId);
    
    /**
     * 根据交易标识查询交易信息
    * @author 张进军
    * @date Mar 5, 2016 9:47:14 AM
    * @param transactionId  交易标识
    * @return   交易信息
     */
    UboxTransactionDto selectTransactionInfoByTransactionId(String transactionId);
    
    /**
     * 根据友宝交易标识查询交易信息
    * @author 张进军
    * @date Mar 7, 2016 10:26:21 AM
    * @param uboxTranId 友宝交易标识
    * @return   交易信息
     */
    UboxTransaction selectTransactionInfoByUboxTranId(String uboxTranId);
}