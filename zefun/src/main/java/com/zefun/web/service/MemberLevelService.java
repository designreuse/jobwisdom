package com.zefun.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.ExcleUtils;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.entity.MemberLevel;
import com.zefun.web.entity.MemberLevelDiscount;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.MemberLevelDiscountMapper;
import com.zefun.web.mapper.MemberLevelMapper;
import com.zefun.web.mapper.StoreInfoMapper;

import net.sf.json.JSONArray;


/**
 * 会员等级(会员卡)管理
 * @author 张进军
 * @date Aug 5, 2015 6:28:51 PM 
 */
@Service
public class MemberLevelService {

    /** 会员等级数据操作对象 */
    @Autowired
    private MemberLevelMapper memberLevelMapper;
    /** 会员折扣操作对象*/
    @Autowired
    private MemberLevelDiscountMapper memberLevelDiscountMapper;
    /** 门店信息*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    /***/
    private static Logger log = Logger.getLogger(MemberLevelService.class);

    /**
     * 查询某个店铺的会员等级信息
     * 默认返回该门店最前面10条数据
    * @author 张进军
    * @date Aug 5, 2015 7:58:33 PM
    * @param storeAccount 企业代号
    * @return ModelAndView
    */
    public ModelAndView enterpriseMemberLevelList(String storeAccount) {
        Page<MemberLevelDto> page = selectPageForMemberLevel(storeAccount, 1, App.System.API_DEFAULT_PAGE_SIZE);
        ModelAndView mav = new ModelAndView(View.MemberLevel.ENTERPRISE_MEMBER_LEVEL);
        mav.addObject("page", page);
        return mav;
    }
    
    /**
     * 企业保存会员等级
    * @author 老王
    * @date 2016年5月31日 上午11:34:38 
    * @param userId 操作员标识
    * @param memberLevel 会员等级
    * @param memberLevelDiscount 会员等级折扣
    * @return BaseDto
     */
    @Transactional
    public BaseDto saveEnterpriseMemberLevel (Integer userId, MemberLevel memberLevel, MemberLevelDiscount memberLevelDiscount) {
    	if (memberLevel.getLevelType() == "折扣卡") {
    		//会员卡增加折扣为零检测
        	if (memberLevelDiscount.getProjectDiscount() == null || memberLevelDiscount.getProjectDiscount() == 0 
        			  || memberLevelDiscount.getGoodsDiscount() == null || memberLevelDiscount.getGoodsDiscount() == 0) {
        		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "添加失败:折扣不能为零");
        	}
    	}
        
        String curTime = DateUtil.getCurTime();
        memberLevel.setLastOperatorId(userId);
        memberLevel.setUpdateTime(curTime);
        //以供检验的会员等级对象
        MemberLevel validatorOfMemberLevel = memberLevelMapper.selectMemberLevelBySotreIdAndLevelName(memberLevel.getStoreAccount(), 
        			 memberLevel.getLevelName());
        if (memberLevel.getLevelId() != null) { 
        	//修改名称重复校验(需要多判断下是否本数据还是别条数据)
        	if (validatorOfMemberLevel != null && memberLevel.getLevelId().intValue() != validatorOfMemberLevel.getLevelId().intValue()) {
        		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "修改失败:该等级名称已存在");
        	}
            memberLevelMapper.updateByPrimaryKey(memberLevel);
        } 
        else {
        	//新增名称重复校验
        	if (validatorOfMemberLevel != null) {
        		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "添加失败:该等级名称已存在");
        	}
            memberLevel.setCreateTime(curTime);
            memberLevel.setIsDeleted(0);
            memberLevelMapper.insert(memberLevel);
        }
        
        List<StoreInfo> storeInfoList = storeInfoMapper.selectByStoreAccount(memberLevel.getStoreAccount());
        
        memberLevelDiscount.setUpdateTime(curTime);
        memberLevelDiscount.setLevelId(memberLevel.getLevelId());
        memberLevelDiscount.setLastOperatorId(userId);
        memberLevelDiscount.setCreateTime(curTime);
        
        //保存或修改企业会员等级折扣
        if (memberLevelDiscount.getDiscountId() != null) {
            memberLevelDiscountMapper.updateByPrimaryKey(memberLevelDiscount);
        }
        else {
        	memberLevelDiscount.setStoreId(0);
        	memberLevelDiscountMapper.insert(memberLevelDiscount);
        }
                
        if (storeInfoList != null && storeInfoList.size() > 0) {
        	for (StoreInfo storeInfo : storeInfoList) {
                if (memberLevelDiscount.getDiscountId() != null) {
                	Map<String, Integer> map = new HashMap<>();
                	map.put("levelId", memberLevel.getLevelId());
                	map.put("storeId", storeInfo.getStoreId());
                	MemberLevelDiscount discount = memberLevelDiscountMapper.selectByStoreLevel(map);
                	memberLevelDiscount.setDiscountId(discount.getDiscountId());
                	memberLevelDiscountMapper.updateByPrimaryKey(memberLevelDiscount);
                }	
                else {
                	memberLevelDiscount.setStoreId(storeInfo.getStoreId());
                    memberLevelDiscountMapper.insert(memberLevelDiscount);
                }
			}
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 根据会员级别查询会员数据
    * @author 老王
    * @date 2016年6月1日 上午1:23:38 
    * @param levelId 会员等级标识
    * @return BaseDto
     */
    public BaseDto selectEnterpriseMember (Integer levelId) {
    	MemberLevelDto memberLevelDto = memberLevelMapper.selectByEnterprise(levelId);
    	return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, memberLevelDto);
    }
    
    /**
     * 为某个门店新增会员等级
    * @author 张进军
    * @date 2015年8月11日 下午3:15:41
    * @param storeId 门店id
    * @param userId 用户id
    * @param memberLevel 会员等级
    * @param memberLevelDiscount 会员折扣
    * @return BaseDto
     */
    @Transactional
    public BaseDto addAction(Integer storeId, Integer userId,
            MemberLevel memberLevel, MemberLevelDiscount memberLevelDiscount) {
    	memberLevel.setStoreId(storeId);
    	if (memberLevel != null) {
    		//会员卡增加折扣为零检测
        	if (memberLevelDiscount.getProjectDiscount() == null || memberLevelDiscount.getProjectDiscount() == 0 
        			  || memberLevelDiscount.getGoodsDiscount() == null || memberLevelDiscount.getGoodsDiscount() == 0) {
        		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "添加失败:折扣不能为零");
        	}
    	}
    	
    	if (memberLevel.getLevelNoticeArr() != null && memberLevel.getLevelNoticeArr().length > 0) {
    	    String sep = "\001";
            List<Map<String, Object>> contentList = new ArrayList<Map<String, Object>>();
            for (String c : memberLevel.getLevelNoticeArr()) {
                String[] cs = c.split(sep);
                Map<String, Object> cm = new HashMap<String, Object>();
                cm.put("type", cs[1]);
                cm.put("text", cs[0]);
                contentList.add(cm);
            }
            memberLevel.setLevelNotice(JSONArray.fromObject(contentList).toString());
    	}
    	else {
    	    memberLevel.setLevelNotice("");
    	}
        
        String curTime = DateUtil.getCurTime();
        memberLevel.setLastOperatorId(userId);
        memberLevel.setUpdateTime(curTime);
        //以供检验的会员等级对象
        MemberLevel validatorOfMemberLevel = memberLevelMapper.selectMemberLevelBySotreIdAndLevelName(storeId, memberLevel.getLevelName());
        if (memberLevel.getLevelId() != null) { 
        	//修改名称重复校验(需要多判断下是否本数据还是别条数据)
        	if (validatorOfMemberLevel != null && memberLevel.getLevelId().intValue() != validatorOfMemberLevel.getLevelId().intValue()) {
        		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "修改失败:该等级名称已存在");
        	}
            memberLevelMapper.updateByPrimaryKey(memberLevel);
        } 
        else {
        	//新增名称重复校验
        	if (validatorOfMemberLevel != null) {
        		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "添加失败:该等级名称已存在");
        	}
            memberLevel.setCreateTime(curTime);
            memberLevel.setIsDeleted(0);
            memberLevelMapper.insert(memberLevel);
        }
        
        memberLevelDiscount.setUpdateTime(curTime);
        memberLevelDiscount.setLevelId(memberLevel.getLevelId());
        memberLevelDiscount.setLastOperatorId(userId);
        
        if (memberLevelDiscount.getDiscountId() != null) {
        	memberLevelDiscountMapper.updateByPrimaryKey(memberLevelDiscount);
        }
        else {
        	memberLevelDiscount.setCreateTime(curTime);
            memberLevelDiscount.setIsDeleted(0);
            memberLevelDiscountMapper.insert(memberLevelDiscount);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 查询某个店铺的会员等级信息
     * 默认返回该门店最前面10条数据
    * @author 张进军
    * @date Aug 5, 2015 7:58:33 PM
    * @param storeId	门店标识
    * @return ModelAndView
    */
    public ModelAndView listView(Integer storeId) {
        Page<MemberLevelDto> page = selectPageForMemberLevel(storeId, 1, App.System.API_DEFAULT_PAGE_SIZE);
        ModelAndView mav = new ModelAndView("member/memberLevel/list");
        mav.addObject("page", page);
        return mav;
    }

    /**
     * 分页查询某个门店的会员等级信息
    * @author 张进军
    * @date Aug 5, 2015 7:58:53 PM
    * @param storeId	门店标识
    * @param pageNo		页码
    * @param pageSize	每页显示数
    * @return BaseDto
     */
    public BaseDto listAction(Integer storeId, int pageNo, int pageSize) {
        Page<MemberLevelDto> page = selectPageForMemberLevel(storeId, pageNo,
                pageSize);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }

    /**
     * 分页查询某个门店的会员等级信息
    * @author 张进军
    * @date Aug 5, 2015 8:01:41 PM
    * @param storeAccount	企业标识
    * @param pageNo		页码
    * @param pageSize	每页显示数
    * @return Page<MemberLevel>
     */
    private Page<MemberLevelDto> selectPageForMemberLevel(String storeAccount,
            int pageNo, int pageSize) {
        Page<MemberLevelDto> page = new Page<MemberLevelDto>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("storeId", 0);
        params.put("storeAccount", storeAccount);
        page.setParams(params);
        List<MemberLevelDto> list = memberLevelMapper.selectByPage(page);
        page.setResults(list);
        return page;
    }

    /**
     * 根据等级标识查询等级信息
    * @author 张进军
    * @date Aug 5, 2015 11:45:13 PM
    * @param levelId	会员等级标识
    * @return BaseDto
     */
    public BaseDto infoAction(Integer levelId) {
        MemberLevel memberLevel = memberLevelMapper.selectByPrimaryKey(levelId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, memberLevel);
    }

    /**
     * 根据等级标识删除等级信息,逻辑删除
    * @author 张进军
    * @date Aug 5, 2015 11:45:13 PM
    * @param levelId	会员等级标识
    * @return BaseDto
     */
    public BaseDto deleteAction(Integer levelId) {
    	//检查是否有在使用的会员
    	int count = memberLevelMapper.selectCountByLevelId(levelId);
    	if (count > 0) {
    		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该卡存在" + count + "条会员记录，不能删除");
    	}
    	
        MemberLevel memberLevel = new MemberLevel();
        memberLevel.setLevelId(levelId);
        memberLevel.setIsDeleted(1);
        memberLevelMapper.updateDeleteByLevelId(memberLevel);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 根据门店id查询会员等级列表
    * @author 洪秋霞
    * @date 2015年8月10日 下午3:51:20
    * @param storeId 门店id
    * @return List<MemberLevel>
     */
    public List<MemberLevel> queryByStoreId(Integer storeId) {
        return memberLevelMapper.selectByStoreId(storeId);
    }

    /**
     * 将指定等级标识设为门店的默认等级
    * @author 张进军
    * @date Oct 12, 2015 8:53:36 AM
    * @param storeId    门店标识
    * @param levelId    等级标识
    * @return   成功返回码0；失败返回其他错误码，返回值为提示语
    */
    @Transactional
    public BaseDto defaultAction(Integer storeId, Integer levelId) {
        // 先将门店的所有等级修改为非默认等级
        memberLevelMapper.updateNonDefaultByStoreId(storeId);
        // 再将指定的会员等级设为默认
        memberLevelMapper.updateDefaultByLevelId(levelId);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES,
                App.System.API_RESULT_MSG_FOR_SUCCEES);
    }

    /**
     * 盛传会员卡导入
    * @author 高国藩
    * @date 2015年10月19日 上午9:37:26
    * @param file 文件
    * @param storeId 门店
    * @param lastOperatorId 最后操作人员ID
    * @return BaseDto
     * @throws IOException 
     */
    @SuppressWarnings("unused")
    @Transactional
    public BaseDto importExcle(MultipartFile file, Integer storeId, Integer lastOperatorId) throws IOException {
        String name = file.getOriginalFilename();
        long size = file.getSize();
        if ((name == null || name.equals("")) && size == 0) {
            return null;
        }
        boolean isE2007 = false;
        if (name.endsWith("xlsx")) {
            isE2007 = true;
        }
        InputStream input = file.getInputStream();
        Workbook wb = null;
        if (isE2007) {
            wb = new XSSFWorkbook(input);
        } 
        else {
            wb = new HSSFWorkbook(input);
        }
        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows = sheet.rowIterator();
        List<MemberLevel> levels = new ArrayList<>();
        List<MemberLevel> memberLevels = memberLevelMapper.selectByStoreId(storeId);
        List<String> hasStr = new ArrayList<>();
        for (int i = 0; i < memberLevels.size(); i++) {
            hasStr.add(memberLevels.get(i).getLevelName());
        }
        for (int rowNum = 1; rowNum <= sheet.getLastRowNum(); rowNum++) {
            Row xssfRow = sheet.getRow(rowNum);
            if (xssfRow != null) {
                MemberLevel level = new MemberLevel();
                for (int cellNum = 0; cellNum <= xssfRow.getLastCellNum(); cellNum++) {
                    try {
                        Cell cell = xssfRow.getCell(cellNum);
                        String str = ExcleUtils.changeCellToString(cell);
                        if (cellNum == 1) {
                            if (hasStr.contains(str)){
                                return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "第"+(rowNum+1)+"行"+"第"+(cellNum+1)+"列  "+str+" 会员卡名称已存在");
                            }
                            level.setLevelName(str);
                        }
                        if (cellNum == 3) {
                            Integer projectDiscount = (int)Double.parseDouble(str)*10;
                            /*level.setProjectDiscount(projectDiscount);*/
                        }
                        if (cellNum == 4) {
                            Integer goodsDiscount = (int)Double.parseDouble(str)*10;
                            /*level.setGoodsDiscount(goodsDiscount);*/
                        }
                        level.setStoreId(storeId);
                        level.setLastOperatorId(lastOperatorId);
                        level.setCreateTime(DateUtil.getCurDate());
                        level.setIsDefault(0);
                        level.setIsDeleted(0);
                    } 
                    catch (Exception e) {
                        return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "出错位置:"+"第"+(rowNum+1)+"行"+"第"+(cellNum+1)+"列"); 
                    }
                }
                levels.add(level);
            }
        }
        log.info(JSONArray.fromObject(levels).toString());
        //插入数据
        for (int i = 0; i < levels.size(); i++) {
            int ok = memberLevelMapper.insert(levels.get(i));
            log.info(ok);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, "导入成功");
    }

    /**
     * 根据门店id查询会员等级列表
    * @author 洪秋霞
    * @date 2015年8月10日 下午3:51:20
    * @param storeId 门店id
    * @return List<MemberLevel>
     */
    public List<MemberLevel> queryByAllStoreId(Integer storeId) {
        return memberLevelMapper.selectByAllStoreId(storeId);
    }
    
}
