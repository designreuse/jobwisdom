package com.zefun.web.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.common.consts.App;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.ComboInfoProjectCommissionDto;
import com.zefun.web.dto.ComboSummaryDto;
import com.zefun.web.dto.DeptComboSummaryDto;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.ProjectCommissionDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.dto.TrendDeptDataDto;
import com.zefun.web.entity.ComboGoods;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.ComboMemberLevel;
import com.zefun.web.entity.ComboProject;
import com.zefun.web.entity.ComboProjectCommission;
import com.zefun.web.mapper.ComboGoodsMapper;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.ComboMemberLevelMapper;
import com.zefun.web.mapper.ComboProjectCommissionMapper;
import com.zefun.web.mapper.ComboProjectMapper;
import com.zefun.web.vo.CardComboSalesVo;
import com.zefun.web.vo.CashComboSalesVo;
import com.zefun.web.vo.DiscountComboSalesVo;

/**
 * 疗程
* @author 洪秋霞
* @date 2015年8月6日 上午10:08:18 
*
 */
@Service
public class ComboInfoService {
    /**
     * 门店id
     */
    // Integer storeId = 1002;

    /**用户id*/
    // private Integer userId = 1;
    
    /** 疗程信息 */
    @Autowired private ComboInfoMapper comboInfoMapper;

    /** 疗程项目关联 */
    @Autowired private ComboProjectMapper comboProjectMapper;

    /** 疗程项目提成 */
    @Autowired private ComboProjectCommissionMapper comboProjectCommissionMapper;

    /** 疗程会员等级关联表 */
    @Autowired private ComboMemberLevelMapper comboMemberLevelMapper;
    /***/
    @Autowired
    private ProjectService projectService;
    /***/
    @Autowired
    private ComboGoodsMapper comboGoodsMapper;
    

    /**
     * 查询疗程
    * @author 洪秋霞
    * @date 2015年8月5日 下午4:20:25
    * @param comboInfo 疗程信息
    * @return List<ComboInfoDto>
     */
    public List<ComboInfo> queryComboInfo(ComboInfo comboInfo) {
        return comboInfoMapper.selectByProperty(comboInfo);
    }

    /**
     * 查询部门，疗程列表
    * @author 洪秋霞
    * @date 2015年9月16日 下午2:29:06
    * @param storeId 门店Id
    * @return List<DeptInfoDto>
     */
    public List<DeptInfoDto> getDetpInfoByCombo(Integer storeId) {
        return comboInfoMapper.getDetpInfoByCombo(storeId);
    }

    /**
     * 根据疗程id查询
    * @author 洪秋霞
    * @date 2015年8月6日 下午5:35:24
    * @param comboId 疗程id
    * @return ComboInfo
     */
    public ComboInfo queryComboInfoById(Integer comboId) {
        return comboInfoMapper.selectByPrimaryKey(comboId);
    }

    /**
     * 查询疗程关联的项目
    * @author 洪秋霞
    * @date 2015年8月6日 下午1:57:01
    * @param comboId 疗程id
    * @return List<ComboProject>
     */
    public List<ComboProject> queryComboProject(Integer comboId) {
        ComboProject comboProject = new ComboProject();
        comboProject.setComboId(comboId);
        return comboProjectMapper.selectByProperty(comboProject);
    }

    /**
     * 根据疗程id查询疗程会员等级信息
    * @author 洪秋霞
    * @date 2015年9月15日 上午11:45:58
    * @param comboId 疗程id
    * @return ComboMemberLevel
     */
    public ComboMemberLevel queryComboMemberLevelByComboId(Integer comboId) {
        return comboMemberLevelMapper.selectByComboId(comboId);
    }

    /**
     * 保存疗程
    * @author 洪秋霞
    * @date 2015年8月5日 下午4:28:04
    * @param userId 用户Id
    * @param comboInfo 疗程信息
    * @param projectId 项目id
    * @param projectName 项目名称
    * @param projectPrice 项目价格
    * @param projectCount 项目次数
    * @param comboPerformanceCal 疗程内业绩计算
    * @param royaltyRate 提成比例
    * @param levelId 会员级别id 
    * @param validDateVip 优惠有效期限
    * @param empLevelId 职位id
    * @param assignType 提成方式:1:按业绩比例,2:按固定金额
    * @param assignCash 指定客现金
    * @param assignCard 指定客刷卡
    * @param nonAssignCash 非指定客现金
    * @param nonAssignCard 非指定客刷卡
    * @return Integer
     */
    @Transactional
    public Integer saveComboInfo(Integer userId, ComboInfo comboInfo, String[] projectId, String[] projectName, String[] projectPrice,
            String[] projectCount, String[] comboPerformanceCal, String[] royaltyRate, String levelId, String validDateVip, 
            String[] empLevelId, String[] assignType,
            String[] assignCash, String[] assignCard, String[] nonAssignCash, String[] nonAssignCard) {
//        List<StoreUser> storeUserList = storeInfoService.queryStoreUserList(userId);
//        if (storeUserList.size() > 1) {
//            // 总店疗程保存
//            comboInfo.setComboParentId(0);
//            comboInfo.setCreateTime(DateUtil.getCurTime());
//            comboInfoMapper.insertSelective(comboInfo);
//            Integer comboId = comboInfo.getComboId();
//            // 保存疗程项目关联
//            saveComboProject(comboId, projectId, projectName, projectPrice, projectCount, comboPerformanceCal, levelId, validDateVip);
//            // 保存疗程项目提成
//            saveComboProjectCommission(comboId, projectId, empLevelId, assignType, assignCash, assignCard, nonAssignCash, nonAssignCard);
//
//            for (int i = 0; i < storeUserList.size(); i++) {
//                comboInfo.setComboId(null);
//                comboInfo.setComboParentId(comboId);
//                comboInfo.setStoreId(storeUserList.get(i).getStoreId());
//                comboInfo.setCreateTime(DateUtil.getCurTime());
//                comboInfoMapper.insertSelective(comboInfo);
//                saveComboProject(comboInfo.getComboId(), projectId, projectName, projectPrice, projectCount, comboPerformanceCal, levelId,
//                        validDateVip);
//                saveComboProjectCommission(comboInfo.getComboId(), projectId, empLevelId, assignType, assignCash, assignCard, nonAssignCash,
//                        nonAssignCard);
//            }
//            return comboId;
//        }
//        else {
            // 单店疗程保存
        comboInfo.setCreateTime(DateUtil.getCurTime());
        comboInfoMapper.insertSelective(comboInfo);
        saveComboProject(comboInfo.getComboId(), projectId, projectName, projectPrice, projectCount, comboPerformanceCal, 
                royaltyRate, levelId, validDateVip);
//        saveComboProjectCommission(comboInfo.getComboId(), projectId, empLevelId, assignType, assignCash, assignCard, nonAssignCash,
//                nonAssignCard);
        return comboInfo.getComboId();
//        }
    }

    /**
     * 编辑疗程
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:52:33
    * @param userId 用户Id
    * @param comboInfo 疗程信息
    * @param projectId 项目id
    * @param projectName 项目名称
    * @param projectPrice 项目价格
    * @param projectCount 项目次数
    * @param comboPerformanceCal 疗程内业绩计算
    * @param royaltyRate 提成比例
    * @param levelId 会员级别id
    * @param validDateVip 优惠有效期限
    * @param empLevelId 职位id
    * @param assignType 提成方式:1:按业绩比例,2:按固定金额
    * @param assignCash 指定客现金
    * @param assignCard 指定客刷卡
    * @param nonAssignCash 非指定客现金
    * @param nonAssignCard 非指定客刷卡
     */
    @Transactional
    public void updateComboInfo(Integer userId, ComboInfo comboInfo, String[] projectId, String[] projectName, String[] projectPrice,
            String[] projectCount, String[] comboPerformanceCal, String[] royaltyRate, String levelId, String validDateVip, String[] empLevelId, 
            String[] assignType,  String[] assignCash, String[] assignCard, String[] nonAssignCash, String[] nonAssignCard) {
        Integer comboId = comboInfo.getComboId();
        comboInfoMapper.updateByPrimaryKeySelective(comboInfo);
        ComboProject comboProject = new ComboProject();
        comboProject.setComboId(comboId);
        @SuppressWarnings("unused")
        List<ComboProject> comboProjectList = comboProjectMapper.selectByProperty(comboProject);
        // 1.删除疗程项目关联
        deleteComboProject(comboInfo.getComboId());
        // 2.新增疗程项目关联
        saveComboProject(comboId, projectId, projectName, projectPrice, projectCount, comboPerformanceCal, royaltyRate, levelId, validDateVip);
        // 3.保存疗程项目提成
        //saveComboProjectCommission(comboId, projectId, empLevelId, assignType, assignCash, assignCard, nonAssignCash, nonAssignCard);

        // 分店编辑疗程
        ComboInfo comboInfoNew = new ComboInfo();
        comboInfoNew.setComboParentId(comboId);
        List<ComboInfo> comboInfoDtoList = comboInfoMapper.selectByProperty(comboInfoNew);
        for (int i = 0; i < comboInfoDtoList.size(); i++) {
            Integer queryComboId = comboInfoDtoList.get(i).getComboId();
            ComboProject queryComboProject = new ComboProject();
            queryComboProject.setComboId(queryComboId);
            // 查找分店疗程项目关联列表
            @SuppressWarnings("unused")
            List<ComboProject> queryComboProjectList = comboProjectMapper.selectByProperty(queryComboProject);
            // 1.删除疗程项目关联
            deleteComboProject(comboInfo.getComboId());
            // 2.新增疗程项目关联
            saveComboProject(queryComboId, projectId, projectName, projectPrice, projectCount, 
                    comboPerformanceCal, royaltyRate, levelId, validDateVip);
            // 3.保存疗程项目提成
            //saveComboProjectCommission(queryComboId, projectId, empLevelId, assignType, assignCash, assignCard, nonAssignCash, nonAssignCard);
        }
        
      //下面是判断连锁店,暂时去掉
      /*  List<StoreUser> storeUserList = storeInfoService.queryStoreUserList(userId);
        Integer comboId = comboInfo.getComboId();
        if (storeUserList.size() > 1) {
            // 总店编辑疗程
            comboInfoMapper.updateByPrimaryKeySelective(comboInfo);
            ComboProject comboProject = new ComboProject();
            comboProject.setComboId(comboId);
            List<ComboProject> comboProjectList = comboProjectMapper.selectByProperty(comboProject);
            // 1.删除疗程项目关联
            deleteComboProject(comboInfo.getComboId());
            // 2.新增疗程项目关联
            saveComboProject(comboId, projectId, projectName, projectPrice, projectCount, comboPerformanceCal, levelId, validDateVip);
            // 3.保存疗程项目提成
            saveComboProjectCommission(comboId, projectId, empLevelId, assignType, assignCash, assignCard, nonAssignCash, nonAssignCard);

            // 分店编辑疗程
            ComboInfo comboInfoNew = new ComboInfo();
            comboInfoNew.setComboParentId(comboId);
            List<ComboInfo> comboInfoDtoList = comboInfoMapper.selectByProperty(comboInfoNew);
            for (int i = 0; i < comboInfoDtoList.size(); i++) {
                Integer queryComboId = comboInfoDtoList.get(i).getComboId();
                ComboProject queryComboProject = new ComboProject();
                queryComboProject.setComboId(queryComboId);
                // 查找分店疗程项目关联列表
                List<ComboProject> queryComboProjectList = comboProjectMapper.selectByProperty(queryComboProject);
                // 1.删除疗程项目关联
                deleteComboProject(comboInfo.getComboId());
                // 2.新增疗程项目关联
                saveComboProject(queryComboId, projectId, projectName, projectPrice, projectCount, comboPerformanceCal, levelId, validDateVip);
                // 3.保存疗程项目提成
                saveComboProjectCommission(queryComboId, projectId, empLevelId, assignType, assignCash, assignCard, nonAssignCash, nonAssignCard);
            }
        }
        else {
            // 单店编辑疗程
            comboInfoMapper.updateByPrimaryKeySelective(comboInfo);
            ComboProject comboProject = new ComboProject();
            comboProject.setComboId(comboId);
            List<ComboProject> comboProjectList = comboProjectMapper.selectByProperty(comboProject);
            // 1.删除疗程项目关联
            deleteComboProject(comboInfo.getComboId());
            // 2.新增疗程项目关联
            saveComboProject(comboId, projectId, projectName, projectPrice, projectCount, comboPerformanceCal, levelId, validDateVip);
            // 3.保存疗程项目提成
            saveComboProjectCommission(comboId, projectId, empLevelId, assignType, assignCash, assignCard, nonAssignCash, nonAssignCard);
        }*/
    }

    /**
     * 保存疗程项目关联
    * @author 洪秋秋秋秋秋秋秋秋秋秋秋秋秋秋秋霞
    * @date 2015年8月17日 上午10:04:35
    * @param comboId 疗程id
    * @param projectId 项目id
    * @param projectName 项目名称
    * @param projectPrice 项目价格
    * @param projectCount 项目次数
    * @param comboPerformanceCal 疗程内业绩计算
    * @param royaltyRate 提成比例
    * @param levelId 会员级别id
    * @param validDateVip 优惠有效期限
     */
    private void saveComboProject(Integer comboId, String[] projectId, String[] projectName, String[] projectPrice, String[] projectCount,
            String[] comboPerformanceCal, String[] royaltyRate,  String levelId, String validDateVip) {
        List<ComboProject> cProjectList = new ArrayList<ComboProject>();
        for (int i = 0; i < projectId.length; i++) {
            ComboProject comboProject = new ComboProject();
            comboProject.setComboId(comboId);
            comboProject.setProjectId(Integer.parseInt(projectId[i].trim()));
            comboProject.setProjectName(projectName[i]);
            comboProject.setProjectPrice(new BigDecimal(projectPrice[i].trim()));
            comboProject.setProjectCount(Integer.parseInt(projectCount[i].trim()));
            comboProject.setComboPerformanceCal(new BigDecimal(comboPerformanceCal[i].trim()));
            /*comboProject.setRoyaltyRate(new BigDecimal(royaltyRate[i].trim()));*/
            comboProject.setCreateTime(DateUtil.getCurTime());
            cProjectList.add(comboProject);
        }
        comboProjectMapper.insertComboProject(cProjectList);

        if (levelId!=null && !levelId.equals("")){
            // 疗程会员等级关联表
            ComboMemberLevel comboMemberLevel = new ComboMemberLevel();
            comboMemberLevel.setComboId(comboId);
            comboMemberLevel.setLevelId(Integer.parseInt(levelId));
            ComboMemberLevel resultComboMemberLevel = comboMemberLevelMapper.selectByPrimaryKey(comboMemberLevel);
            if (resultComboMemberLevel != null) {
                resultComboMemberLevel.setValidDate(Integer.parseInt(validDateVip));
                comboMemberLevelMapper.updateByPrimaryKeySelective(resultComboMemberLevel);
            }
            else {
                comboMemberLevel.setValidDate(Integer.parseInt(validDateVip));
                comboMemberLevelMapper.insertSelective(comboMemberLevel);
            }
        }
    }

    /**
     * 删除疗程项目关联
    * @author 洪秋霞
    * @date 2015年8月17日 下午3:00:27
    * @param comboId 疗程项目关联列表
     */
    private void deleteComboProject(Integer comboId) {
        ComboProject comboProject = new ComboProject();
        comboProject.setComboId(comboId);
        comboProjectMapper.deleteByPrimaryKey(comboProject);
//        ComboProjectCommission comboProjectCommission = new ComboProjectCommission();
//        comboProjectCommission.setIsDeleted(1);
//        comboProjectCommission.setComboId(comboId);
//        comboProjectCommissionMapper.updateByPrimaryKeySelective(comboProjectCommission);
//        comboProjectCommissionMapper.deleteByComboId(comboId);
        
        
//        if (comboProjectList.size() > 0) {
//            for (int i = 0; i < comboProjectList.size(); i++) {
//                ComboProject cProject = comboProjectList.get(i);
//                ComboProject cbProject = new ComboProject();
//                cbProject.setComboId(cProject.getComboId());
//                cbProject.setProjectId(cProject.getProjectId());
//                comboProjectMapper.deleteByPrimaryKey(cbProject);
//            }
//        }
    }

    /**
     * 保存疗程项目提成表
    * @author 洪秋霞
    * @date 2015年9月24日 上午10:14:48
    * @param comboId 疗程Id
    * @param projectId 项目Id
    * @param empLevelId 职位id
    * @param assignType 提成方式:1:按业绩比例,2:按固定金额
    * @param assignCash 指定客现金
    * @param assignCard 指定客刷卡
    * @param nonAssignCash 非指定客现金
    * @param nonAssignCard 非指定客刷卡
     */
    @Transactional
    private void saveComboProjectCommission(Integer comboId, String[] projectId, String[] empLevelId, String[] assignType, String[] assignCash,
            String[] assignCard, String[] nonAssignCash, String[] nonAssignCard) {
        if (projectId.length > 0) {
            List<String> projectIds = Arrays.asList(projectId);
            /*List<String> empLevelIds = Arrays.asList(empLevelId);*/
            List<String> assignTypes = Arrays.asList(assignType);
            List<String> assignCashs = Arrays.asList(assignCash);
            List<String> assignCards = Arrays.asList(assignCard);
            List<String> nonAssignCashs = Arrays.asList(nonAssignCash);
            List<String> nonAssignCards = Arrays.asList(nonAssignCard);
            int status = 0;
            for (int i = 0; i < projectIds.size(); i++) {
                List<ComboProjectCommission> insertList = new ArrayList<ComboProjectCommission>();
                
                List<ProjectCommissionDto> projectCommissionList 
                    = projectService.queryProjectCommissionByProjectId(Integer.parseInt(projectIds.get(i)));
                
                for (int j = 0; j < projectCommissionList.size(); j++) {
                    int ok = status++;
                    ComboProjectCommission saveCommission = new ComboProjectCommission();
                    saveCommission.setComboId(comboId);
                    saveCommission.setProjectId(Integer.parseInt(projectIds.get(i)));
                    saveCommission.setLevelId(projectCommissionList.get(j).getLevelId());
                    saveCommission.setAssignCashType(Integer.parseInt(assignTypes.get(ok)));
                    saveCommission.setAssignCardType(Integer.parseInt(assignTypes.get(ok)));
                    saveCommission.setNonAssignCashType(Integer.parseInt(assignTypes.get(ok)));
                    saveCommission.setNonAssignCardType(Integer.parseInt(assignTypes.get(ok)));
                    saveCommission.setAssignCash(Integer.parseInt(assignCashs.get(ok)));
                    saveCommission.setAssignCard(Integer.parseInt(assignCards.get(ok)));
                    saveCommission.setNonAssignCash(Integer.parseInt(nonAssignCashs.get(ok)));
                    saveCommission.setNonAssignCard(Integer.parseInt(nonAssignCards.get(ok)));
                    saveCommission.setCreateTime(DateUtil.getCurDate());
                    insertList.add(saveCommission);
                }
                comboProjectCommissionMapper.insertBatch(insertList);
            }
//            List<ComboProjectCommissionDto> updateList = new ArrayList<ComboProjectCommissionDto>();
//            List<ComboProjectCommission> insertList = new ArrayList<ComboProjectCommission>();
//            // 选择的项目列表
//            for (int i = 0; i < projectId.length; i++) {
//                ComboProjectCommission comboProjectCommission = new ComboProjectCommission();
//                comboProjectCommission.setComboId(comboId);
//                comboProjectCommission.setProjectId(Integer.parseInt(projectId[i].trim()));
//                List<ComboProjectCommissionDto> comboProjectCommissionList = comboProjectCommissionMapper.selectByProperty(comboProjectCommission);
//                if (comboProjectCommissionList.size() > 0) {
//                    // 修改
//                    for (int j = 0; j < comboProjectCommissionList.size(); j++) {
//                        ComboProjectCommissionDto updateCommission = comboProjectCommissionList.get(j);
//
//                        // for (int k = 0; k < empLevelId.length; k++) {
//                        // ComboProjectCommission updateCommission = new
//                        // ComboProjectCommission();
//                        updateCommission.setCommissionId(comboProjectCommissionList.get(j).getCommissionId());
//                        updateCommission.setComboId(comboId);
//                        updateCommission.setProjectId(Integer.parseInt(projectId[i].trim()));
//
//                        updateCommission.setLevelId(Integer.parseInt(empLevelId[j].trim()));
//                        updateCommission.setAssignCashType(Integer.parseInt(assignType[j]));
//                        updateCommission.setAssignCardType(Integer.parseInt(assignType[j]));
//                        updateCommission.setNonAssignCashType(Integer.parseInt(assignType[j]));
//                        updateCommission.setNonAssignCardType(Integer.parseInt(assignType[j]));
//                        updateCommission.setAssignCash(new BigDecimal(assignCash[j]));
//                        updateCommission.setAssignCard(new BigDecimal(assignCard[j]));
//                        updateCommission.setNonAssignCash(new BigDecimal(nonAssignCash[j]));
//                        updateCommission.setNonAssignCard(new BigDecimal(nonAssignCard[j]));
//                        updateCommission.setUpdateTime(DateUtil.getCurTime());
//                        updateList.add(updateCommission);
//                        // comboProjectCommissionMapper.updateByPrimaryKeySelective(updateCommission);
//                        // }
//                    }
//                    if (updateList.size() > 0) {
//                        comboProjectCommissionMapper.updateBatch(updateList);
//                    }
//                }
//                else {
//                    // 新增
//                    for (int j = 0; j < empLevelId.length; j++) {
//                        ComboProjectCommission saveCommission = new ComboProjectCommission();
//                        saveCommission.setComboId(comboId);
//                        saveCommission.setProjectId(Integer.parseInt(projectId[i].trim()));
//
//                        saveCommission.setLevelId(Integer.parseInt(empLevelId[j].trim()));
//                        saveCommission.setAssignCashType(Integer.parseInt(assignType[j]));
//                        saveCommission.setAssignCardType(Integer.parseInt(assignType[j]));
//                        saveCommission.setNonAssignCashType(Integer.parseInt(assignType[j]));
//                        saveCommission.setNonAssignCardType(Integer.parseInt(assignType[j]));
//                        saveCommission.setAssignCash(new BigDecimal(assignCash[j]));
//                        saveCommission.setAssignCard(new BigDecimal(assignCard[j]));
//                        saveCommission.setNonAssignCash(new BigDecimal(nonAssignCash[j]));
//                        saveCommission.setNonAssignCard(new BigDecimal(nonAssignCard[j]));
//                        saveCommission.setCreateTime(DateUtil.getCurDate());
//                        insertList.add(saveCommission);
//                        // comboProjectCommissionMapper.insertSelective(saveCommission);
//                    }
//                    if (insertList.size() > 0) {
//                        comboProjectCommissionMapper.insertBatch(insertList);
//                    }
//                }
//            }
        }
    }

    /**
     * 删除疗程
    * @author 洪秋霞
    * @date 2015年8月11日 上午11:54:34
    * @param comboId 疗程id
     */
    public void deleteComboInfo(Integer comboId) {
        // 总店疗程删除
        ComboInfo comboInfo = new ComboInfo();
        comboInfo.setComboId(comboId);
        comboInfo.setIsDeleted(1); // 1:删除
        comboInfoMapper.updateByPrimaryKeySelective(comboInfo);
        //删除项目商品疗程关联信息
        ComboProject comboProject = new ComboProject();
        comboProject.setComboId(comboId);
        comboProjectMapper.deleteByPrimaryKey(comboProject);
        ComboGoods comboGoods = new ComboGoods();
        comboGoods.setComboId(comboId);
        comboGoodsMapper.deleteByPrimaryKey(comboGoods);
        // 分店疗程删除
        ComboInfo comboInfoStore = new ComboInfo();
        comboInfoStore.setComboParentId(comboId);
        List<ComboInfo> comboInfoDtoList = comboInfoMapper.selectByProperty(comboInfoStore);
        if (comboInfoDtoList.size() > 0) {
            for (int i = 0; i < comboInfoDtoList.size(); i++) {
                ComboInfo queryComboInfo = new ComboInfo();
                queryComboInfo.setComboId(comboInfoDtoList.get(i).getComboId());
                queryComboInfo.setIsDeleted(1);
                comboInfoMapper.updateByPrimaryKeySelective(queryComboInfo);
            }
        }
    }

    /**
     * 根据疗程id查询疗程项目提成列表
    * @author 洪秋霞
    * @date 2015年9月24日 下午2:50:39
    * @param comboId 疗程id
    * @return List<ComboProjectCommission>
     */
    public List<ComboInfoProjectCommissionDto> queryComboCommissionByProjectId(Integer comboId) {
        ComboProjectCommission comboProjectCommission = new ComboProjectCommission();
        comboProjectCommission.setComboId(comboId);
        return comboProjectCommissionMapper.selectByCommissionId(comboId);
        /*return comboProjectCommissionMapper.selectByProperty(comboProjectCommission);*/
    }

    /**
     * 保存疗程商品关联信息
    * @author 高国藩
    * @date 2015年11月11日 上午10:05:57
    * @param comboId 疗程ID
    * @param goodsId 商品ID
    * @param goodsNames 商品名称
    * @param goodsPrice 商品价格
    * @param goodsCounts 商品数量
    * @param goodsCommissionTypeDate 折扣类型
    * @param commissionAmount 折扣金额
    * @param comboGoodsPerformanceCal 疗程内业绩计算
     */
    public void saveComboGoods(Integer comboId, String[] goodsId,
            String goodsNames, String[] goodsPrice, String[] goodsCounts,
            String[] goodsCommissionTypeDate, String[] commissionAmount,
            String[] comboGoodsPerformanceCal) {
        ComboGoods g = new ComboGoods();
        g.setComboId(comboId);
        comboGoodsMapper.deleteByPrimaryKey(g);
        if (goodsId==null||goodsId.length<=0){
            return;
        }
        String[] goodsName = goodsNames.split(",");
        for (int i = 0; i < goodsId.length; i++) {
            ComboGoods comboGoods = new ComboGoods();
            comboGoods.setComboId(comboId);
            comboGoods.setGoodsId(Integer.parseInt(goodsId[i]));
            comboGoods.setGoodsCounts(Integer.parseInt(goodsCounts[i]));
            comboGoods.setGoodsName(goodsName[i]);
            comboGoods.setGoodsPrice(new BigDecimal(goodsPrice[i]));
            comboGoods.setComboPerformanceCal(new BigDecimal(comboGoodsPerformanceCal[i]));
            comboGoodsMapper.insertSelective(comboGoods);
        }
    }

    /**
     * 查询疗程关联商品信息
    * @author 高国藩
    * @date 2015年11月11日 上午9:55:07
    * @param comboId 疗程ID
    * @return 结合
     */
    public List<ComboGoods> queryComboGoods(Integer comboId) {
        ComboGoods comboGoods = new ComboGoods();
        comboGoods.setComboId(comboId);
        return comboGoodsMapper.selectByPrimaryKey(comboGoods);
    }

    
    /**
     * 保存疗程信息
    * @author 高国藩
    * @date Nov 12, 2015 9:29:27 PM
    * @param deptId     部门标识
    * @param comboName  疗程名称
    * @param userId     用户标识
    * @param storeId    门店标识
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto saveComboInfos(Integer deptId, String[] comboName,
            Integer userId, Integer storeId) {
        List<ComboInfo> ls = new ArrayList<>();
        for (int i = 0; i < comboName.length; i++) {
            ComboInfo comboInfo = new ComboInfo();
            comboInfo.setStoreId(storeId);
            comboInfo.setLastOperatorId(userId);
            comboInfo.setDeptId(deptId);
            comboInfo.setComboName(comboName[i]);
            comboInfo.setIsDeleted(0);
            comboInfoMapper.insertSelective(comboInfo);
            ls.add(comboInfo);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月21日 下午2:48:33
    * @param dto 封装所需条件为对象 包括起始时间 终止时间 门店标志
    * @return 部门下疗程汇总数据
    */
    public List<DeptComboSummaryDto> getComboInfo(SummaryResultDto dto){
        List<DeptComboSummaryDto> results=comboInfoMapper.getComboDetail(dto);
        //遍历部门
        for (int i=0 ; i<results.size(); i++){
            DeptComboSummaryDto dept=results.get(i);
            
            BigDecimal deptTotal=new BigDecimal(0);
            Integer deptCnt=0;
            List<ComboSummaryDto> deptCombos=dept.getCombosBelongToDept();
            //遍历部门下的疗程
            for (int j=0; j<deptCombos.size(); j++){
            	dto.setProjectId(deptCombos.get(j).getComboId());
                //获取现金和卡金销售金额
                CashComboSalesVo cash=comboInfoMapper.getCashComboSale(dto);
                CardComboSalesVo card=comboInfoMapper.getCardComboSale(dto);
                DiscountComboSalesVo discount=comboInfoMapper.getDiscountComboSale(dto);
                deptCombos.get(j).setCashComboSales(cash);
                deptCombos.get(j).setCardComboSales(card);
                deptCombos.get(j).setDiscountComboSales(discount);
                deptTotal=deptTotal.add(deptCombos.get(j).getComboAmount());
                deptCnt=deptCnt+deptCombos.get(j).getComboCnt();
            }
            if (deptCombos.size()!=0){
                deptCombos.add(new ComboSummaryDto(null, "小计", deptCnt, deptTotal, dept.getDeptId(), null));
            }            
            dept.setDeptComboSummary(deptTotal);
            dept.setDeptComboSales(deptCnt);
        }
        return results;
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月21日 下午4:30:35
    * @param dto 封装所需条件为对象 包括起始时间 终止时间 门店标志
    * @return 给定条件下疗程的销售排行
    */
    public List<ComboSummaryDto> getComboRank(SummaryResultDto dto){
        List<ComboSummaryDto>  comboRank=comboInfoMapper.getComboRank(dto);
        for (int i=0; i<comboRank.size(); i++){
        	dto.setProjectId(comboRank.get(i).getComboId());
            //获取现金和卡金销售金额
            CashComboSalesVo cash=comboInfoMapper.getCashComboSale(dto);
            CardComboSalesVo card=comboInfoMapper.getCardComboSale(dto);
            DiscountComboSalesVo discount=comboInfoMapper.getDiscountComboSale(dto);
            comboRank.get(i).setCashComboSales(cash);
            comboRank.get(i).setCardComboSales(card);
            comboRank.get(i).setDiscountComboSales(discount);
            comboRank.get(i).setComboRank(i+1);
        }
        return comboRank;
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月22日 上午11:52:26
    * @param dto 封装所需条件为对象 包括起始时间 终止时间 门店标志
    * @return 给定条件下疗程的月份汇总数据
    */
    public List<TrendDeptDataDto> getComboTrendData(SummaryResultDto dto){
        List<TrendDeptDataDto> data=comboInfoMapper.getComboTrendData(dto);
        return data;
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月27日 下午6:11:57
    * @param dto 封装所需条件为对象 包括起始时间 终止时间 部门id
    * @return 分部门的排行数据
    */
    public List<ComboSummaryDto> getComboRankByDept(SummaryResultDto dto){
        List<ComboSummaryDto>  comboRank=comboInfoMapper.getComboRankByDept(dto); 
        for (int i=0; i<comboRank.size(); i++){
            comboRank.get(i).setComboRank(i+1);
        }
        
        return comboRank;
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月27日 下午7:09:21
    * @param dto 疗程排行详细数据
    * @return 名字
    */
    public Map<String, Integer> getNameForRank(List<ComboSummaryDto> dto){
        Map<String, Integer> projectRank=new HashMap<String, Integer>();
        for (int i=0; i<dto.size(); i++){
            String name=dto.get(i).getComboName();
            Integer rank=dto.get(i).getComboRank();
            projectRank.put(name, rank);
        }
        return projectRank;
    }
    
    /**
    * @author 乐建建
    * @date 2016年1月26日 下午8:07:04
    * @param nameForRank 名称与排名的对应表
    * @param resultList 劳动业绩排行表
    * @param deptName 部门名称
    * @return 填充了上期排名的劳动业绩排行表
    */
    public List<ComboSummaryDto> fillLastRank(
            Map<String, Integer> nameForRank, String deptName,
            List<ComboSummaryDto> resultList) {
        for (int i=0; i<resultList.size(); i++){
            Integer obj=nameForRank.get(resultList.get(i).getComboName());
            resultList.get(i).setDeptName(deptName);
            if (obj!=null){
                resultList.get(i).setLastRank(obj.toString());
            } 
            else {
                resultList.get(i).setLastRank("---");
            }
        }
        return resultList;
    }
    
    /**
    * @author 乐建建
    * @date 2016年3月15日 上午10:51:09
    * @param dto 查询条件
    * @return CashComboSalesVo
    */
    public CashComboSalesVo cashComboSalesProcessed(SummaryResultDto dto){
        CashComboSalesVo cash= comboInfoMapper.getCashComboSale(dto);
        if (cash!=null){
            if (cash.getCashComboAmt()==null){
                cash.setCashComboAmt(new BigDecimal(0));
            }
            if (cash.getCashComboCnt()==null){
                cash.setCashComboCnt(0);
            }
        }
        else {
            cash=new CashComboSalesVo();
            cash.setCashComboAmt(new BigDecimal(0));
            cash.setCashComboCnt(0);
        }
        return cash;
    }
    
    /**
    * @author 乐建建
    * @date 2016年3月15日 上午11:00:08
    * @param dto 查询条件
    * @return CardComboSalesVo
    */
    public CardComboSalesVo cardComboSalesProcessed(SummaryResultDto dto){
        CardComboSalesVo card=comboInfoMapper.getCardComboSale(dto);
        if (card!=null){
            if (card.getCardComboAmt()==null){
                card.setCardComboAmt(new BigDecimal(0));
            }
            if (card.getCardComboCnt()==null){
                card.setCardComboCnt(0);
            }
        }
        else {
            card=new CardComboSalesVo();
            card.setCardComboAmt(new BigDecimal(0));
            card.setCardComboCnt(0);
        }
        return card;
    }

    /**
     * 新增或者修改疗程
    * @author 高国藩
    * @date 2016年6月7日 上午10:14:25
    * @param comboInfo        comboInfo
    * @param comboProjects    comboProjects
    * @param comboGoods       comboGoods
    * @return                 comboInfo
     */
    public BaseDto saveOrUpdate(ComboInfo comboInfo, List<ComboProject> comboProjects, List<ComboGoods> comboGoods) {
        if (comboInfo.getComboId()!=null){
            comboInfoMapper.updateByPrimaryKeySelective(comboInfo);
        }
        else {
            comboInfo.setCreateTime(DateUtil.getCurDateTrimMinutes());
            comboInfoMapper.insertSelective(comboInfo);
        }
        ComboProject comboProject = new ComboProject();
        comboProject.setComboId(comboInfo.getComboId());
        comboProjectMapper.deleteByPrimaryKey(comboProject);
        
        ComboGoods goods = new ComboGoods();
        goods.setComboId(comboInfo.getComboId());
        comboGoodsMapper.deleteByPrimaryKey(goods);
        
        if (comboProjects!=null && comboProjects.size()>0){
            for (int i = 0; i < comboProjects.size(); i++) {
                comboProjects.get(i).setComboId(comboInfo.getComboId());
                comboProjects.get(i).setCreateTime(DateUtil.getCurDateTrimMinutes());
            }
            comboProjectMapper.insertComboProject(comboProjects);
        }
        
        if (comboGoods!=null && comboGoods.size()>0){
            for (int i = 0; i < comboGoods.size(); i++) {
                comboGoods.get(i).setComboId(comboInfo.getComboId());
                comboGoods.get(i).setCreateTime(DateUtil.getCurDateTrimMinutes());
                comboGoodsMapper.insertSelective(comboGoods.get(i));
            }
        }
        return new BaseDto(0, comboInfo);
    }
    
}
