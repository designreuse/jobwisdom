package com.zefun.web.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberDto;
import com.zefun.web.dto.MemberGroupDto;
import com.zefun.web.dto.MemberInfoDto;
import com.zefun.web.entity.MemberInfo;

/**
 * 会员信息操作类
* @author 张进军
* @date Aug 19, 2015 4:38:52 PM
 */
public interface MemberInfoMapper {
    /**
     * 根据手机号码查询会员标识
    * @author 张进军
    * @date Aug 19, 2015 7:49:56 PM
    * @param record  会员信息
    * @return       如果存在结果返回会员标识，不存在返回null
     */
    Integer selectMemberIdByPhone(MemberInfo record);

    /**
     * 根据手机号码查询会员基本信息
    * @author 王大爷
    * @date 2015年9月12日 下午2:46:56
    * @param record 会员信息
    * @return 如果存在结果返回会员标识，不存在返回null
     */
    MemberBaseDto selectMemberInfoByPhone(MemberInfo record);

    /**
     * 根据会员标识查询会员基本信息
    * @author 张进军
    * @date Aug 19, 2015 5:29:31 PM
    * @param memberId   会员标识
    * @return           会员基本信息
     */
    MemberBaseDto selectMemberBaseInfo(Integer memberId);

    /**
     * 根据会员标识删除会员信息
    * @author 张进军
    * @date Aug 19, 2015 4:39:13 PM
    * @param memberId   会员标识
    * @return           0:失败，1:成功
     */
    int deleteByPrimaryKey(Integer memberId);

    /**
     * 插入会员信息
    * @author 张进军
    * @date Aug 19, 2015 4:39:53 PM
    * @param record     会员信息
    * @return           会员标识
     */
    int insert(MemberInfo record);

    /**
     * 根据会员标识查询会员信息
    * @author 张进军
    * @date Aug 19, 2015 4:40:13 PM
    * @param memberId   会员标识
    * @return           会员信息
     */
    MemberInfo selectByPrimaryKey(Integer memberId);

    /**
     * 根据会员标识修改会员信息
    * @author 张进军
    * @date Aug 19, 2015 4:40:32 PM
    * @param record     会员信息
    * @return           0:失败，1:成功
     */
    int updateByPrimaryKey(MemberInfo record);

    /**
     * 根据门店信息查询所有会员
    * @author 高国藩
    * @date 2015年9月8日 下午6:14:10
    * @param storeId 门店信息
    * @return 集合
     */
    List<MemberInfoDto> selectMemberByStoreId(Integer storeId);

    /**
     * 根据会员信息标识查询会员信息及账户信息
    * @author 王大爷
    * @date 2015年9月12日 下午4:15:25
    * @param memberId 会员信息标识
    * @return 会员信息及账户信息
     */
    MemberDto selectByMemberResultMap(Integer memberId);

    /***
     * 查询分组中本月新增人数
    * @author 高国藩
    * @date 2015年9月12日 下午7:27:41
    * @param dateMap 参数 1.startDate 2.stopDate 3.memberList
    * @return 新增人数
     */
    Integer selectMemberCountByDate(Map<String, Object> dateMap);


    /**
     * 将预选的会员id集合中的发送次数为0的会员查询出来
    * @author 高国藩
    * @date 2015年10月7日 下午6:01:34
    * @param ls 预选名单
    * @return 在预选名单中发送次数剩余0次的会员
     */
    List<MemberInfo> selectMemberInfoByWechatParams(List<Integer> ls);

    /**
     *  根据明细标识查询对应会员
    * @author 王大爷
    * @date 2015年10月24日 上午11:16:22
    * @param detailId 明细标识
    * @return 会员基础信息
     */
    MemberBaseDto selectByDetailId(Integer detailId);

    /**
     * 查询会员信息
     * @param memberId 会员信息
     * @return map
     */
    Map<String, Object> selectSelfCashierMemberPassById(Integer memberId);

    /**
     * 动态创建表_左右_记录临时会员数据
    * @author 高国藩
    * @date 2015年12月4日 上午10:45:57
     */
    void updateTempTableZY();

    /**
     * 批量插入数据
    * @author 高国藩
    * @date 2015年12月4日 上午11:45:30
    * @param infoDtos 集合数据
     */
    void insertList(List<MemberInfoDto> infoDtos);

    /**
     * 删除表
    * @author 高国藩
    * @date 2015年12月4日 下午12:16:32
     */
    void dropTempTableZY();

    /**
     * 查询分组信息
    * @author 高国藩
    * @date 2015年12月10日 下午3:56:13
    * @param storeId  门店
    * @return         集合
     */
    List<MemberGroupDto> selectGroupDtos(Integer storeId);

    /**
     * 查询一个会员分组下的所有会员的id
    * @author 高国藩
    * @date 2015年12月11日 下午2:12:11
    * @param params        分组的信息,将dto转为map
    * @return              集合
     */
    List<Integer> selectMemberIdsByDtos2(Map<String, Object> params);

    /**
     * 给定一个会员等级集合,查询出该等价下的所有id集合
    * @author 高国藩
    * @date 2015年12月11日 下午2:29:43
    * @param asList    等级集合
    * @return          会员id集合
     */
    List<Integer> selectMemberIdsByLevelIds(List<String> asList);

    /**
     * 门店会员统计
    * @author 高国藩
    * @date 2015年12月17日 下午2:18:00
    * @param storeId  门店信息
    * @return         统计信息
     */
    MemberInfoDto selectStoreMemberAmount(Integer storeId);

    /**
     * 根据门店标识查询所有会员标识
    * @author 张进军
    * @date Dec 25, 2015 9:01:24 PM
    * @param storeId	门店标识
    * @return	会员标识集合
     */
    List<Integer> selectMemberIdByStoreId(int storeId);

    /**
     * 根据会员标识列表删除会员信息
    * @author 张进军
    * @date Dec 25, 2015 9:09:45 PM
    * @param memberIdList	会员标识列表
    * @return	删除数量
     */
    int deleteByMemberIdList(List<Integer> memberIdList);

    /**
     * 查询本店会员，名称，手机号
    * @author 王大爷
    * @date 2016年1月5日 下午6:32:11
    * @param map 门店标识
    * @return Map<String, Object>
     */
    List<Map<String, Object>> selectStoreNamePhone(Map<String, Integer> map);

    /**
     * 根据会员id查询会员信息
     * @author gebing
     * @date 2016年1月8日
     * @param memberIds 会员id
     * @return 会员信息
     */
    List<MemberInfo> selectByMemberIds(List<Integer> memberIds);

    /**
     * 根据店铺id和会员电话查询会员
    * @author DavidLiang
    * @date 2016年2月21日 下午9:25:16
    * @param storeId  店铺id
    * @param phone  会员电话
    * @return  会员信息
     */
    MemberInfo selectMemberByStoreIdAndPhone(@Param(value="storeId")int storeId, @Param(value="phone")String phone);

}
