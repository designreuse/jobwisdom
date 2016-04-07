package com.zefun.web.mapper;

import java.util.List;

import com.zefun.web.dto.member.MemberMigrateDto;
import com.zefun.web.entity.MemberErrorData;

/**
 * 会员异常数据操作对象
* @author 张进军
* @date Mar 21, 2016 10:17:41 PM
 */
public interface MemberErrorDataMapper {
    /**
     * 根据主键删除记录
    * @author 张进军
    * @date Mar 21, 2016 10:17:50 PM
    * @param id 主键
    * @return   0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 新增异常会员数据
    * @author 张进军
    * @date Mar 21, 2016 10:18:20 PM
    * @param record 异常会员数据
    * @return   0:失败，1:成功
     */
    int insert(MemberErrorData record);

    /**
     *  根据主键查询异常会员数据
    * @author 张进军
    * @date Mar 21, 2016 10:18:42 PM
    * @param id 主键
    * @return   异常会员数据
     */
    MemberErrorData selectByPrimaryKey(Integer id);

    /**
     * 修改异常会员数据
    * @author 张进军
    * @date Mar 21, 2016 10:19:02 PM
    * @param record 异常会员数据
    * @return   0:失败，1:成功
     */
    int updateByPrimaryKey(MemberErrorData record);
    
    /**
     * 批量新增会员异常数据
    * @author 张进军
    * @date Mar 21, 2016 10:24:28 PM
    * @param memberErrorList    会员异常数据列表
    * @return   0:失败，1:成功
     */
    int insertBatch(List<MemberMigrateDto> memberErrorList);

    /**
     * 新增会员异常数据
    * @author 张进军
    * @date Mar 22, 2016 12:49:07 AM
    * @param memberMigrateDto   会员异常数据
    * @return   0:失败，1:成功
     */
    int insertByMigrate(MemberMigrateDto memberMigrateDto);
}