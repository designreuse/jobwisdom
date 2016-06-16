package com.zefun.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.ExcleUtils;
import com.zefun.common.utils.StringUtil;
import com.zefun.web.dto.member.MemberMigrateDto;
import com.zefun.web.entity.MemberAccount;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberSubAccount;
import com.zefun.web.mapper.MemberAccountMapper;
import com.zefun.web.mapper.MemberErrorDataMapper;
import com.zefun.web.mapper.MemberInfoMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.MemberSubAccountMapper;

/**
 * 会员数据移植处理类
* @author 张进军
* @date Mar 21, 2016 5:23:42 PM
 */
@Service
public class MemberMigrateService {

    /** 会员等级操作对象 */
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    
    /** 会员信息操作对象 */
    @Autowired
    private MemberInfoMapper memberInfoMapper;
    
    /** 会员账户操作对象 */
    @Autowired
    private MemberAccountMapper memberAccountMapper;
    
    /** 会员子账户操作对象 */
    @Autowired
    private MemberSubAccountMapper memberSubAccountMapper;
    
    /** 会员异常数据操作对象 */
    @Autowired
    private MemberErrorDataMapper memberErrorDataMapper;
    
    /** 会员信息服务对象 */
    @Autowired
    private MemberInfoService memberInfoService;
    
    /** 存储手机号码与会员主账户的映射 */
    private Map<String, MemberMigrateDto> mainAccountMap = null;
    /** 存储手机号码与会员子账户的映射 */
    private Map<String, MemberSubAccount> subAccountMap = null;
    /** 存储正常会员数据 */
    private List<MemberMigrateDto> memberMigrateList = null;
    /** 存储异常会员数据 */
    private List<MemberMigrateDto> memberMigrateErrList = null;
    /** 存储会员卡 */
    private Set<String> levelNameSet = null;
    
    /**
     * 根据文本文件做数据移植，文本内容一行一条记录，单行分隔符为TAB符
    * @author 张进军
    * @date Mar 21, 2016 5:26:29 PM
    * @param file       数据文件
    * @param sysType    系统类型(1、盛传，2、左右，3、星沙龙，4、华彩)  
    * @param storeId    门店标识
     */
    public void maigrateForExcel(File file, int sysType, int storeId) {
        InputStream io = null;
        Workbook wb = null;
        
        try {
            io = new FileInputStream(file);
            
            if (file.getName().endsWith("xlsx")) {
                wb = new XSSFWorkbook(io);
            }
            else {
                wb = new HSSFWorkbook(io);
            }
            Sheet sheet = wb.getSheetAt(0);
            
            mainAccountMap = new HashMap<>();
            subAccountMap = new HashMap<>();
            memberMigrateList = new ArrayList<>();
            memberMigrateErrList = new ArrayList<>();
            levelNameSet = new HashSet<>();
            
            for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum++) {
                Row xssfRow = sheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
                
                readMemberDataForRow(xssfRow, storeId, sysType);
            }
            
            insertNormalMemberData(memberMigrateList, storeId, levelNameSet, subAccountMap);
            
            for (MemberMigrateDto memberMigrateDto : memberMigrateErrList) {
                memberErrorDataMapper.insertByMigrate(memberMigrateDto);
            }
//            memberErrorDataMapper.insertBatch(memberMigrateErrList);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (io != null) {
                try {
                    io.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
    /**
     * 根据表格内容获取会员数据，华彩系统
    * @author 张进军
    * @date Mar 21, 2016 11:09:11 PM
    * @param xssfRow    表格内容
    * @param storeId    门店标识
    * @param sysType    系统类型(1、盛传，2、左右，3、星沙龙，4、华彩)  
     */
    private void readMemberDataForRow(Row xssfRow, int storeId, int sysType) {
        MemberMigrateDto memberInfo = parseDataForSysType(xssfRow, storeId, sysType);
        
        String phone = memberInfo.getPhone();
        if (StringUtil.isMobile(phone)) {
            
            //检查是否存在主账户，存在则累计金额
            if (mainAccountMap.containsKey(phone)) {
                MemberMigrateDto mainAccount = mainAccountMap.get(phone);
                mainAccount.setTotalAmount(mainAccount.getTotalAmount().add(memberInfo.getTotalAmount()));
                mainAccount.setBalanceAmount(mainAccount.getBalanceAmount().add(memberInfo.getBalanceAmount()));
                mainAccount.setTotalGiftmoneyAmount(mainAccount.getTotalGiftmoneyAmount().add(memberInfo.getTotalGiftmoneyAmount()));
                mainAccount.setBalanceGiftmoneyAmount(mainAccount.getBalanceGiftmoneyAmount().add(memberInfo.getBalanceGiftmoneyAmount()));
                mainAccount.setTotalIntegral(mainAccount.getTotalIntegral() + memberInfo.getTotalIntegral());
                mainAccount.setBalanceIntegral(mainAccount.getBalanceIntegral() + memberInfo.getBalanceIntegral());
            }
            else {
                memberMigrateList.add(memberInfo);
            }
            
            //检查是否存在子账户，存在则累计金额
            String key = phone + "_" + memberInfo.getLevelName();
            if (subAccountMap.containsKey(key)) {
                MemberSubAccount subAccount = subAccountMap.get(key);
                subAccount.setTotalAmount(subAccount.getTotalAmount().add(memberInfo.getTotalAmount()));
                subAccount.setBalanceAmount(subAccount.getBalanceAmount().add(memberInfo.getBalanceAmount()));
            }
            else {
                MemberSubAccount subAccount = new MemberSubAccount();
                subAccount.setTotalAmount(memberInfo.getTotalAmount());
                subAccount.setBalanceAmount(memberInfo.getBalanceAmount());
                subAccountMap.put(key, subAccount);
            }
        }
        else {
            memberMigrateErrList.add(memberInfo);
        }
        
        levelNameSet.add(memberInfo.getLevelName());
    }
    
    
    /**
     * 根据系统类型解析会员数据
    * @author 张进军
    * @date Mar 21, 2016 11:29:42 PM
    * @param xssfRow    表格内容
    * @param storeId    门店标识
    * @param sysType    系统类型(1、盛传，2、左右，3、星沙龙，4、华彩)
    * @return   会员数据
     */
    private MemberMigrateDto parseDataForSysType(Row xssfRow, int storeId, int sysType) {
        String cardNo = "";
        String phone = "";
        String name = "";
        String sex = "";
        String levelName = "";
        String birthday = "";
        String password = "";
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal balanceAmount = BigDecimal.ZERO;
        BigDecimal totalGiftmoneyAmount = BigDecimal.ZERO;
        BigDecimal balanceGiftmoneyAmount = BigDecimal.ZERO;
        int totalIntegral = 0;
        int balanceIntegral = 0;
        BigDecimal debtAmount = BigDecimal.ZERO;
        String createTime = "";
        
        switch (sysType) {
            case 4:
                cardNo = ExcleUtils.changeCellToString(xssfRow.getCell(5));
                phone = ExcleUtils.changeCellToString(xssfRow.getCell(12));
                name = ExcleUtils.changeCellToString(xssfRow.getCell(6));
                sex = ExcleUtils.changeCellToString(xssfRow.getCell(7));
                levelName = ExcleUtils.changeCellToString(xssfRow.getCell(16));
                
                if ("1".equals(sex)) {
                    sex = "男";
                }
                else {
                    sex = "女";
                }
                
                int month = Integer.parseInt(ExcleUtils.changeCellToString(xssfRow.getCell(10)));
                int day = Integer.parseInt(ExcleUtils.changeCellToString(xssfRow.getCell(11)));
                if (month > 0 && month < 10) {
                    birthday = "0" + month;
                }
                else if (month >= 10) {
                    birthday = "" + month;
                }
                
                if (StringUtils.isNotBlank(birthday)) {
                    birthday += "-";
                    if (day > 0 && day < 10) {
                        birthday += "0" + day;
                    }
                    else if (day >= 10) {
                        birthday += day;
                    }
                    else {
                        birthday += "01";
                    }
                }
                
                password = ExcleUtils.changeCellToString(xssfRow.getCell(2));
                totalAmount = new BigDecimal(ExcleUtils.changeCellToString(xssfRow.getCell(4)));
                balanceAmount = new BigDecimal(ExcleUtils.changeCellToString(xssfRow.getCell(4)));
                createTime = ExcleUtils.changeCellToString(xssfRow.getCell(14));
                if (StringUtils.isNotBlank(createTime)) {
                    createTime = DateUtil.getDateTimeByUnixTime(Long.parseLong(createTime) * 1000, "yyyy-MM-dd HH:mm:ss");
                }
                break;
    
            default:
                break;
        }
        
        String curTime = DateUtil.getCurTime();
        
        
        if (StringUtils.isBlank(createTime)) {
            createTime = curTime;
        }
        
        if (StringUtils.isBlank(password)) {
            password = "123456";
        }
        password = StringUtil.encryptPwd(password);
        String payPassword = password.split(":")[0];
        String passwordSalt = password.split(":")[1];
        
        MemberMigrateDto memberInfo = new MemberMigrateDto(cardNo, storeId, levelName, name, sex, birthday, phone, createTime, 
                payPassword, passwordSalt, totalAmount, balanceAmount, totalGiftmoneyAmount, balanceGiftmoneyAmount,
                totalIntegral, balanceIntegral, debtAmount);
        return memberInfo;
    }
    
    
    /**
     * 插入会员数据
    * @author 张进军
    * @date Mar 21, 2016 9:20:59 PM
    * @param memberMigrateList  会员数据集合
    * @param storeId            门店标识
    * @param levelNameSet       会员名称集合
    * @param subAccountMap      手机号码_等级标识与会员子账户的映射
     */
    @Transactional
    private void insertNormalMemberData(List<MemberMigrateDto> memberMigrateList, int storeId, Set<String> levelNameSet, 
            Map<String, MemberSubAccount> subAccountMap) {
        Map<String, Integer> levelMap = insertMemberLevel(storeId, levelNameSet);
        
        for (MemberMigrateDto memberMigrate : memberMigrateList) {
            int levelId = levelMap.get(memberMigrate.getLevelName());
            String phone = memberMigrate.getPhone();
            
            //新增会员信息
            MemberInfo memberInfo = new MemberInfo(memberMigrate.getStoreId(), levelId, null, memberMigrate.getName(), null, 
                    memberMigrate.getSex(), memberMigrate.getBirthday(), phone, 1, memberMigrate.getCreateTime(), 0);
            memberInfoMapper.insert(memberInfo);
            
            int memberId = memberInfo.getMemberId();
            
            //新增主账户信息
            MemberAccount memberAccount = new MemberAccount(memberId, memberMigrate.getPayPassword(), memberMigrate.getPasswordSalt(), 
                    memberMigrate.getTotalAmount(), memberMigrate.getBalanceAmount(), memberMigrate.getTotalGiftmoneyAmount(), 
                    memberMigrate.getBalanceGiftmoneyAmount(), memberMigrate.getTotalIntegral(), memberMigrate.getBalanceIntegral(), 
                    memberMigrate.getDebtAmount(), memberMigrate.getCreateTime(), 0);
            memberAccountMapper.insert(memberAccount);
            
            //检查是否需要增加积分流水
            if (memberAccount.getBalanceIntegral() > 0) {
                memberInfoService.changeIntegralToMember(memberId, memberAccount.getBalanceIntegral(), 2, "原始系统导入", null);
            }
            
            //检查是否需要增加挂账流水
            if (memberAccount.getDebtAmount().compareTo(BigDecimal.ZERO) == 1) {
                memberInfoService.insertDebtFlow(memberId, null, memberAccount.getDebtAmount(), "原始系统导入", 1, 0, memberMigrate.getCreateTime());
            }
            
            //记录储值余额最多的会员子账户等级标识
            int topLevelId = levelId;
            BigDecimal topBalanceAmount = new BigDecimal(0);
            
            //新增子账户标识
            for (String levelName : levelMap.keySet()) {
                MemberSubAccount memberSubAccount = subAccountMap.get(phone + "_" + levelName);
                if (memberSubAccount != null) {
                    int tempLevelId = levelMap.get(levelName);
                    
                    //查询余额最多的子账户会员等级
                    if (memberSubAccount.getBalanceAmount().compareTo(topBalanceAmount) == 1) {
                        topBalanceAmount = memberSubAccount.getBalanceAmount();
                        topLevelId = tempLevelId;
                    }
                    
                    memberSubAccount.setSubAccountId(null);
                    memberSubAccount.setAccountId(memberId);
                    memberSubAccount.setLevelId(tempLevelId);
                    memberSubAccount.setCreateTime(memberMigrate.getCreateTime());
                    memberSubAccountMapper.insert(memberSubAccount);
                    
                    //如果存在余额则增加资金流水
                    if (memberSubAccount.getBalanceAmount().compareTo(BigDecimal.ZERO) == 1) {
                        memberInfoService.changeMoneyFlow(memberSubAccount.getSubAccountId(), memberSubAccount.getBalanceAmount(), 
                                "原始系统导入", 7, storeId, 0);
                    }
                }
            }
            
            //如果当前会员的等级不是余额最多的子账户等级，则同步更新
            if (levelId != topLevelId) {
                memberInfo.setLevelId(topLevelId);
                memberInfoMapper.updateByPrimaryKey(memberInfo);
            }
        }
    }
    
    
    /**
     * 批量新增会员等级
    * @author 张进军
    * @date Mar 21, 2016 7:28:17 PM
    * @param storeId        门店标识
    * @param levelNameSet   会员名称集合
    * @return   会员名称对应会员等级ID的映射
     */
    private Map<String, Integer> insertMemberLevel(int storeId, Set<String> levelNameSet) {
        
        //以遍历的形式增加会员卡，方便映射
        Map<String, Integer> levelMap = new HashMap<>(levelNameSet.size());
        String curTime = DateUtil.getCurTime();
        for (String levelName : levelNameSet) {
            MemberLevel memberLevel = new MemberLevel();
//            memberLevel.setStoreId(storeId);
            memberLevel.setLevelName(levelName);
            memberLevel.setCreateTime(curTime);
            memberLevel.setUpdateTime(levelName);
            memberLevelMapper.insert(memberLevel);
            
            levelMap.put(levelName, memberLevel.getLevelId());
        }
        
        return levelMap;
    }
}
