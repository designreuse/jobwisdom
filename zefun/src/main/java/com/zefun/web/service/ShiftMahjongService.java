package com.zefun.web.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.EmployeeDto;
import com.zefun.web.dto.EmployeeLevelBaseDto;
import com.zefun.web.dto.ShiftMahjongDto;
import com.zefun.web.entity.EmployeeAttendance;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.entity.ShiftMahjong;
import com.zefun.web.entity.ShiftMahjongEmployee;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.EmployeeAttendanceMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.EmployeeLevelMapper;
import com.zefun.web.mapper.PositioninfoMapper;
import com.zefun.web.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.web.mapper.ShiftMahjongMapper;
import com.zefun.web.mapper.StoreInfoMapper;

import net.sf.json.JSONArray;

/**
 * 轮牌信息Service
* @author 王大爷
* @date 2015年8月11日 上午11:50:57
 */
@Service
public class ShiftMahjongService {
	/** 员工信息*/
	@Autowired private EmployeeInfoMapper employeeInfoMapper;
	
	/** 轮牌员工信息Mapper*/
	@Autowired private ShiftMahjongEmployeeMapper shiftMahjongEmployeeMapper;
	
	/** redis Service*/
	@Autowired private RedisService redisService;
	
	/** 岗位*/
	@Autowired private PositioninfoMapper positioninfoMapper;
	
	/** 轮牌*/
	@Autowired private ShiftMahjongMapper shiftMahjongMapper;
	
	/** 考勤*/
	@Autowired private EmployeeAttendanceMapper employeeAttendanceMapper;
	
	/** 门店*/
	@Autowired private StoreInfoMapper storeInfoMapper;
	
	/** 职位*/
	@Autowired private EmployeeLevelMapper employeeLevelMapper;
	
	/**
	 * 初始化企业轮牌页面
	* @author 老王
	* @date 2016年6月24日 下午4:57:27 
	* @param storeAccount 企业代号
	* @param request      request
	* @return ModelAndView
	 */
	public ModelAndView initializeEnterpriseShiftMahjong (String storeAccount, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName(View.KeepAccounts.ENTERPRISE_SHIFT_MAJONG);
		List<StoreInfo> storeInfoList = storeInfoMapper.selectByStoreAccount(storeAccount);
		
		if (!storeInfoList.isEmpty() && storeInfoList.size() > 0) {
			Integer storeId = storeInfoList.get(0).getStoreId();
			List<PositionInfo> positionInfoList  = positioninfoMapper.queryAllByStoreId(storeId);
			
			List<ShiftMahjong> shiftMahjongList = shiftMahjongMapper.selectAllByStoreId(storeId);
			
			for (ShiftMahjong shiftMahjong : shiftMahjongList) {
				String positionStr = "";
				String[] positionIdList = shiftMahjong.getPositionId().split(",");
	            for (int i = 0; i < positionIdList.length; i++) {
	                String positionIdStr = positionIdList[i];
	                for (PositionInfo positionInfo : positionInfoList) {
	                	Integer positionId =  positionInfo.getPositionId();
	                	if (positionIdStr.equals(positionId.toString())) {
	                		if (i == 0) {
	                			positionStr = positionInfo.getPositionName();
	                		}
	                		else {
	                			positionStr = positionStr + "," + positionInfo.getPositionName();
	                		}
	                	}
					}
	            }
	            
	            shiftMahjong.setPosition(positionStr);
			}
			
			mav.addObject("shiftMahjongList", shiftMahjongList);
			mav.addObject("positionInfoList", positionInfoList);
			mav.addObject("storeInfoList", storeInfoList);
		}
		
		return mav;
	}
	
	/**
	 * 
	* @author 老王
	* @date 2016年6月25日 下午12:48:42 
	* @param storeId 门店标识
	* @return BaseDto
	 */
	public BaseDto showStoreShiftMahjong (Integer storeId) {
		
        List<PositionInfo> positionInfoList  = positioninfoMapper.queryAllByStoreId(storeId);
		
		List<ShiftMahjong> shiftMahjongList = shiftMahjongMapper.selectAllByStoreId(storeId);
		
		for (ShiftMahjong shiftMahjong : shiftMahjongList) {
			String positionStr = "";
			String[] positionIdList = shiftMahjong.getPositionId().split(",");
            for (int i = 0; i < positionIdList.length; i++) {
                String positionIdStr = positionIdList[i];
                for (PositionInfo positionInfo : positionInfoList) {
                	Integer positionId =  positionInfo.getPositionId();
                	if (positionIdStr.equals(positionId.toString())) {
                		if (i == 0) {
                			positionStr = positionInfo.getPositionName();
                		}
                		else {
                			positionStr = positionStr + "," + positionInfo.getPositionName();
                		}
                	}
				}
            }
            
            shiftMahjong.setPosition(positionStr);
		}
		
		Map<String, Object> map = new HashMap<>();
        map.put("shiftMahjongList", shiftMahjongList);
        map.put("positionInfoList", positionInfoList);
		
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
	}
	
	/**
	 * 新增修改企业轮牌信息及人员信息
	* @author 王大爷
	* @date 2015年9月8日 下午3:05:32
	* @param shiftMahjong 轮牌信息
	* @param positionIds 岗位标识
	* @return BaseDto
	 */
	@Transactional
	public BaseDto addUpdateShiftMahjong(ShiftMahjong shiftMahjong, List<Integer> positionIds){
		Map<String, Object> isExistmap = new HashMap<>();
		isExistmap.put("shiftMahjongName", shiftMahjong.getShiftMahjongName());
		isExistmap.put("storeId", shiftMahjong.getStoreId());
		List<ShiftMahjong> existList = shiftMahjongMapper.selectByShiftMahjongName(isExistmap);
	    if (shiftMahjong.getShiftMahjongId() == null) {
	    	
	    	if (!existList.isEmpty()) {
	    		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "改轮牌名称已存在，请修改！");
	    	}
	    	
	    	//查询出企业下所有门店
	    	shiftMahjong.setCreateTime(DateUtil.getCurDate());
            shiftMahjongMapper.insert(shiftMahjong);
            
            Map<String, String> param = new HashMap<>();
            param.put("positionIds", JSONArray.fromObject(positionIds).toString().replace("[", "").replace("]", "").trim());
            List<EmployeeLevelBaseDto> baseDtos = employeeLevelMapper.selectByPositionIds(param);
            operationShiftMahjongEmployee(baseDtos, shiftMahjong.getShiftMahjongId(), shiftMahjong.getStoreId());
	    }
	    else {
	    	
            ShiftMahjong shiftMahjongOld = shiftMahjongMapper.selectByPrimaryKey(shiftMahjong.getShiftMahjongId());
            
            if (!existList.isEmpty() && existList.size() > 0 
            		  && existList.get(0).getShiftMahjongId().intValue() !=  shiftMahjongOld.getShiftMahjongId().intValue()) {
	    		return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "改轮牌名称已存在，请修改！");
	    	}
            
            String positionIdListOldStr = shiftMahjongOld.getPositionId();
            String [] positionIdOldStr = positionIdListOldStr.split(",");
            List<String> positionIdOlds = new ArrayList<>();
            //由于Arrays.asList()转出的list是AbstractList，是不能remove的；
            for (String str : positionIdOldStr) {
            	positionIdOlds.add(str);
			}
            
            List<Integer> addPosition = new ArrayList<Integer>();
                        
            for (int k = 0; k < positionIds.size(); k++) {
                
                int a = 0;
                
                for (int j = 0; j < positionIdOlds.size(); j++) {
                    if (Integer.valueOf(positionIdOlds.get(j)).intValue() == positionIds.get(k).intValue()) {
                    	positionIdOlds.remove(j);
                        a = 1;
                        break;
                    }
                }
                
                if (a == 0) {
                    addPosition.add(positionIds.get(k));
                }
            }
            
            //当删了原来所选岗位时
            if (positionIdOlds.size() > 0) {
                Integer stateType = isSucceed(positionIdOlds, shiftMahjong.getShiftMahjongId());
                
                if (stateType == 1) {
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "更换的岗位中存在服务中的员工");
                }
                for (int k = 0; k < positionIdOlds.size(); k++) {
                    Map<String, Integer> map = new HashMap<String, Integer>();
                    map.put("positionId", Integer.valueOf(positionIdOlds.get(k)));
                    map.put("shiftMahjongId", shiftMahjong.getShiftMahjongId());
                    
                    List<ShiftMahjongEmployee> shiftMahjongEmployeeList = shiftMahjongEmployeeMapper.selectByPositionId(map);
                    //单个删除轮牌员工，其后的员工向前移一位
                    for (int j = 0; j < shiftMahjongEmployeeList.size(); j++) {
                        ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeList.get(j);
                        if (shiftMahjongEmployee.getState() == 3 || (shiftMahjong.getNature() == 2 && shiftMahjongEmployee.getState() == 2)) {
                            
                            shiftMahjongEmployeeMapper.deleteByPrimaryKey(shiftMahjongEmployee.getShiftMahjongEmployeeId());
                            
                        }
                        else if (shiftMahjongEmployee.getState() == 1 
                                || (shiftMahjong.getNature() == 1 && shiftMahjongEmployee.getState() == 2)){
                            
                            shiftMahjongEmployeeMapper.updateByToEnd(shiftMahjongEmployee);
                            shiftMahjongEmployeeMapper.deleteByPrimaryKey(shiftMahjongEmployee.getShiftMahjongEmployeeId());
                        }
                    }
                }
            }
            if (addPosition.size() > 0) {
            	//添加岗位信息
                Map<String, String> param = new HashMap<>();
                param.put("positionIds", JSONArray.fromObject(addPosition).toString().replace("[", "").replace("]", "").trim());
                List<EmployeeLevelBaseDto> baseDtos = employeeLevelMapper.selectByPositionIds(param);
                operationShiftMahjongEmployee(baseDtos, shiftMahjong.getShiftMahjongId(), shiftMahjong.getStoreId());
            }
            
	        shiftMahjongMapper.updateByPrimaryKeySelective(shiftMahjong);
	    }
	    
	    //删除缓存
        /*redisService.hdel(App.Redis.DEPT_PROJECT_MAHJONG_INFO_KEY_HASH, String.valueOf(shiftMahjong.getDeptId()));*/
	    ShiftMahjong newShiftMahjong = shiftMahjongMapper.selectByPrimaryKey(shiftMahjong.getShiftMahjongId());
	    List<PositionInfo> positionInfoList  = positioninfoMapper.queryAllByStoreId(shiftMahjong.getStoreId());
	    String[] positionIdList = newShiftMahjong.getPositionId().split(",");
	    String positionStr = "";
	    for (int i = 0; i < positionIdList.length; i++) {
            String positionIdStr = positionIdList[i];
            for (PositionInfo positionInfo : positionInfoList) {
            	Integer positionId =  positionInfo.getPositionId();
            	if (positionIdStr.equals(positionId.toString())) {
            		if (i == 0) {
            			positionStr = positionInfo.getPositionName();
            		}
            		else {
            			positionStr = positionStr + "," + positionInfo.getPositionName();
            		}
            	}
			}
        }
        
	    newShiftMahjong.setPosition(positionStr);
	    
	    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, newShiftMahjong);
	}
	
	/**
	 * 删除轮牌信息
	* @author 王大爷
	* @date 2015年9月8日 下午4:21:25
	* @param shiftMahjongId 轮牌标识
	* @return BaseDto
	 */
	public BaseDto deleteShiftMahjong(Integer shiftMahjongId){
	    
		ShiftMahjong shiftMahjong = shiftMahjongMapper.selectByPrimaryKey(shiftMahjongId);
		
		String positionIdListOldStr = shiftMahjong.getPositionId();
        List<String> positionIdOlds = Arrays.asList(positionIdListOldStr.split(","));
		
		Integer stateType = isSucceed(positionIdOlds, shiftMahjongId);
        
        if (stateType == 1) {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "更换的岗位中存在服务中的员工");
        }
	    
	    shiftMahjongMapper.deleteByPrimaryKey(shiftMahjongId);
	    shiftMahjongEmployeeMapper.deleteByShiftMahjongId(shiftMahjongId);

	    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
	}
	
	/**
	 * 初始化轮牌界面
	* @author laowang
	* @date 2015年8月8日 上午11:17:40
	* @param storeId 门店标识
	* @return ModelAndView
	 */
	public ModelAndView initializeShiftMahjong(Integer storeId){
		ModelAndView mav = new ModelAndView();
		
		List<ShiftMahjongDto> shiftMahjongDtoList = shiftMahjongMapper.selectByStoreId(storeId);
		
		mav.addObject("shiftMahjongDtoList", shiftMahjongDtoList);
		
		mav.addObject("shiftMahjongDtoListStr", JSONArray.fromObject(shiftMahjongDtoList).toString());
		
		mav.setViewName(View.KeepAccounts.SHIFT_MAHJONG);
		return mav;
	}
	
	/**
	 * 判断代码是否满足条件
	* @author 王大爷
	* @date 2015年12月5日 下午5:20:12
	* @param positionIdList 需要判断的岗位标识
	* @param shiftMahjongId 轮牌标识
	* @return 是否成功
	 */
	public Integer isSucceed(List<String> positionIdList, Integer shiftMahjongId) {
	    Map<String, Object> mapList = new HashMap<String, Object>();
        mapList.put("list", positionIdList);
        mapList.put("shiftMahjongId", shiftMahjongId);

        List<ShiftMahjongEmployee> objList = shiftMahjongEmployeeMapper.selectByPositionIdExistsWork(mapList);
        if (objList.size() > 0) {
            return 1;
        }
        return 0;
	}
	
	/**
	 * 根据部门查询轮牌信息
	* @author 王大爷
	* @date 2015年9月16日 下午4:15:28
	* @param deptId 部门标识
	* @return BaseDto
	 */
	public BaseDto selectshiftMahjong(Integer deptId){
	    List<ShiftMahjongDto> shiftMahjongDtoList = shiftMahjongMapper.selectByDeptId(deptId);
	    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, shiftMahjongDtoList);
	}
	
	/**
	 * 修改员工状态(轮牌手动修改状态)
	* @author 王大爷
	* @date 2015年9月16日 下午8:25:29
	* @param shiftMahjongEmployeeId 轮牌员工信息标识
	* @param state 状态
	* @return BaseDto
	 */
	public BaseDto updateState(Integer shiftMahjongEmployeeId, Integer state){
		
		Integer shiftMahjongId = updateStateCha(shiftMahjongEmployeeId, state);
        ShiftMahjongDto shiftMahjongDto = shiftMahjongMapper.selectByShiftMahjongIdDto(shiftMahjongId);
	    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, shiftMahjongDto);
	}
	
	/**
	 * 批量上牌
	* @author 老王
	* @date 2016年5月13日 上午1:21:34 
	* @param shiftMahjongEmployeeIdListStr 上牌人员集合
	* @param storeId 门店标识
	* @return BaseDto
	 */
	public BaseDto updateStateUp(String shiftMahjongEmployeeIdListStr, Integer storeId) {
		Integer shiftMahjongId = null;
		JSONArray shiftMahjongEmployeeIdListJson = JSONArray.fromObject(shiftMahjongEmployeeIdListStr);
		for (int i = 0; i < shiftMahjongEmployeeIdListJson.size(); i++) {
			Integer shiftMahjongEmployeeId = shiftMahjongEmployeeIdListJson.getInt(i);
			shiftMahjongId = updateStateCha(shiftMahjongEmployeeId, 1);
		}

		ShiftMahjongDto shiftMahjongDto = shiftMahjongMapper.selectByShiftMahjongIdDto(shiftMahjongId);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, shiftMahjongDto);
	}
	
	/**
	 * 
	* @author 老王
	* @date 2016年5月13日 上午3:00:55 
	* @param shiftMahjongId 轮牌id
	* @param shiftMahjongEmployeeIdListStr 员工
	* @param storeId 门店标识
	* @return BaseDto
	 */
	public BaseDto updateEmployeeOrder(Integer shiftMahjongId, String shiftMahjongEmployeeIdListStr, Integer storeId) {
		JSONArray shiftMahjongEmployeeIdListJson = JSONArray.fromObject(shiftMahjongEmployeeIdListStr);
		for (int i = 0; i < shiftMahjongEmployeeIdListJson.size(); i++) {
			Integer shiftMahjongEmployeeId = shiftMahjongEmployeeIdListJson.getInt(i);
			ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
			shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
			int a = i + 1;
			shiftMahjongEmployee.setShiftMahjongOrder(a);
			
			shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
		}

		ShiftMahjongDto shiftMahjongDto = shiftMahjongMapper.selectByShiftMahjongIdDto(shiftMahjongId);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, shiftMahjongDto);
	}
	
	/**
	 * 子方法
	* @author 老王
	* @date 2016年5月13日 上午1:13:11 
	* @param shiftMahjongEmployeeId 轮牌员工信息标识
	* @param state 状态
	* @return Integer
	 */
	public Integer updateStateCha(Integer shiftMahjongEmployeeId, Integer state) {
		ShiftMahjong shiftMahjong = shiftMahjongMapper.selectByShiftMahjongEmployeeId(shiftMahjongEmployeeId);
	    updateStateShiftMahjong(shiftMahjongEmployeeId, state, shiftMahjong);
	    ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
        shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
        shiftMahjongEmployee.setState(state);
	    if (state == 3 || (shiftMahjongEmployee.getState() == 2 && shiftMahjong.getNature() == 2)) {
	        shiftMahjongEmployee.setShiftMahjongOrder(999);
	    }
        shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
        /*if (state.intValue() == 1) {
            staffService.selfMotionExecute(shiftMahjongEmployeeId, storeId);
        }*/
        return shiftMahjong.getShiftMahjongId();
	}
	
	/**
	 * 更改轮牌员工状态(轮牌手动修改状态)
	* @author 王大爷
	* @date 2015年10月29日 下午3:59:38
	* @param shiftMahjongEmployeeId 轮牌员工信息标识
    * @param state 状态
    * @param shiftMahjong 轮牌信息
	 */
	public void updateStateShiftMahjong(Integer shiftMahjongEmployeeId, Integer state, ShiftMahjong shiftMahjong){
	    if (state == 2){
            if (shiftMahjong.getNature() == 2) {
                ShiftMahjongEmployee obj = new ShiftMahjongEmployee();
                obj.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
                obj.setShiftMahjongId(shiftMahjong.getShiftMahjongId());
                shiftMahjongEmployeeMapper.updateByToEnd(obj);
                shiftMahjongEmployeeMapper.updateByUpwardAllCount(obj);
                
            }
            else {
                ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
                if (shiftMahjongEmployee.getState() == 3) {
                    Integer shiftMahjongOrder = shiftMahjongEmployeeMapper.selectByCount(shiftMahjong.getShiftMahjongId());
                    ShiftMahjongEmployee obj = new ShiftMahjongEmployee();
                    obj.setShiftMahjongOrder(shiftMahjongOrder + 1);
                    obj.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
                    shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(obj);
                }
            }
        }
        //离开
        else if (state == 3){
            ShiftMahjongEmployee obj = new ShiftMahjongEmployee();
            obj.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
            obj.setShiftMahjongId(shiftMahjong.getShiftMahjongId());
            shiftMahjongEmployeeMapper.updateByToEnd(obj);
        }
        //空闲
        else if (state == 1) {
            ShiftMahjongEmployee shiftMahjongEmployee = shiftMahjongEmployeeMapper.selectByPrimaryKey(shiftMahjongEmployeeId);
            //当当前状态为4时，为激活员工
            if (shiftMahjongEmployee.getState() == 3|| (shiftMahjongEmployee.getState() == 2 && shiftMahjong.getNature() == 2)) {
                Integer shiftMahjongOrder = shiftMahjongEmployeeMapper.selectByCount(shiftMahjong.getShiftMahjongId());
                ShiftMahjongEmployee obj = new ShiftMahjongEmployee();
                obj.setShiftMahjongOrder(shiftMahjongOrder + 1);
                obj.setShiftMahjongEmployeeId(shiftMahjongEmployeeId);
                shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(obj);
            }
        }
	}
	
	
	/**
	 * 根据轮牌信息标识查询该轮牌对应的岗位信息
	* @author laowang
	* @date 2015年8月10日 下午5:18:09
	* @param shiftMahjongId 轮牌信息ID
	* @return BaseDto
	 */
	public BaseDto initializeModel(Integer shiftMahjongId){
	    ShiftMahjong shiftMahjong = shiftMahjongMapper.selectByPrimaryKey(shiftMahjongId);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, shiftMahjong);
	}
	
	
	/**
	 * 刷新轮牌员工
	* @author 王大爷
	* @date 2015年12月23日 下午8:49:59
	* @param shiftMahjongId 轮牌标识
	* @param stateType 状态类型
	* @return BaseDto
	 */
	public BaseDto refreshShiftMahjongEmployee(Integer shiftMahjongId, Integer stateType) {
		List<ShiftMahjongEmployee> list = new ArrayList<>();
		if (stateType == 1) {
			list = shiftMahjongEmployeeMapper.selectByShiftMahjongId(shiftMahjongId);
		}
		else {
			list = shiftMahjongEmployeeMapper.selectByShiftMahjongIdDown(shiftMahjongId);
		}
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, list);
	}
	
	/**
	 * 轮牌上移
	* @author 王大爷
	* @date 2015年8月13日 上午11:51:04
	* @param shiftMahjongEmployee 轮牌员工信息
	* @return BaseDto
	 */
	public BaseDto upwardIMG(ShiftMahjongEmployee shiftMahjongEmployee){
	    if (shiftMahjongEmployee.getShiftMahjongOrder() == 1){
	        shiftMahjongEmployeeMapper.updateByUpwardAll(shiftMahjongEmployee);
	        shiftMahjongEmployeeMapper.updateByUpwardAllCount(shiftMahjongEmployee);
	    }
	    else {
	        shiftMahjongEmployeeMapper.updateByUpwardOther(shiftMahjongEmployee);
	        shiftMahjongEmployeeMapper.updateByUpward(shiftMahjongEmployee);
	    }
	    List<ShiftMahjongEmployee> list = shiftMahjongEmployeeMapper.selectByShiftMahjongId(shiftMahjongEmployee.getShiftMahjongId());
	    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, list);
	}
	
	/**
     * 轮牌下移
    * @author 王大爷
    * @date 2015年8月13日 上午11:51:04
    * @param shiftMahjongEmployee 轮牌员工信息
    * @return BaseDto
     */
	public BaseDto nextIMG(ShiftMahjongEmployee shiftMahjongEmployee){
	    Integer order = shiftMahjongEmployeeMapper.selectByCount(shiftMahjongEmployee.getShiftMahjongId());
	    if (order.intValue() == shiftMahjongEmployee.getShiftMahjongOrder().intValue()){
	        shiftMahjongEmployeeMapper.updateByNextdAll(shiftMahjongEmployee);
            shiftMahjongEmployeeMapper.updateByNextAllCount(shiftMahjongEmployee);
	    }
	    else {
	        shiftMahjongEmployeeMapper.updateByNextOther(shiftMahjongEmployee);
	        shiftMahjongEmployeeMapper.updateByNext(shiftMahjongEmployee);
	    }
	    List<ShiftMahjongEmployee> list = shiftMahjongEmployeeMapper.selectByShiftMahjongId(shiftMahjongEmployee.getShiftMahjongId());
	    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, list);
	}
	
	/**
	 * 轮牌置顶
	* @author 王大爷
	* @date 2015年12月22日 下午3:03:57
	* @param shiftMahjongEmployee 轮牌员工信息
	* @return BaseDto
	 */
	public BaseDto toTopIMG(ShiftMahjongEmployee shiftMahjongEmployee) {
	    shiftMahjongEmployeeMapper.updateAddBig(shiftMahjongEmployee);
	    shiftMahjongEmployeeMapper.updateByNextAllCount(shiftMahjongEmployee);
	    List<ShiftMahjongEmployee> list = shiftMahjongEmployeeMapper.selectByShiftMahjongId(shiftMahjongEmployee.getShiftMahjongId());
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, list);
	}
	
	/**
	 * 轮牌置低
	* @author 王大爷
	* @date 2015年12月22日 下午3:21:32
	* @param shiftMahjongEmployee 轮牌员工信息
	* @return BaseDto
	 */
	public BaseDto toEndIMG(ShiftMahjongEmployee shiftMahjongEmployee) {
	    Integer order = shiftMahjongEmployeeMapper.selectByCount(shiftMahjongEmployee.getShiftMahjongId());
	    
	    if (order.intValue() == shiftMahjongEmployee.getShiftMahjongOrder()) {
	        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, 0);
	    }
	    else {
	        shiftMahjongEmployeeMapper.updateByToEnd(shiftMahjongEmployee);
	        shiftMahjongEmployeeMapper.updateByUpwardAllCount(shiftMahjongEmployee);
	    }
	    List<ShiftMahjongEmployee> list = shiftMahjongEmployeeMapper.selectByShiftMahjongId(shiftMahjongEmployee.getShiftMahjongId());
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, list);
	}
	
	/**
     * 员工登录后业绩
    * @author 王大爷
    * @date 2015年8月20日 下午2:35:23
    * @param mav mav
    * @return ModelAndView
     */
	public ModelAndView staffPerformance(ModelAndView mav){
        mav.setViewName(View.StaffPage.RECEPTION);
        return mav;
	}
	
	/**
	 * 修改员工资料时，更新轮牌员工信息
	* @author 王大爷
	* @date 2015年9月8日 下午4:43:12
	* @param employeeInfo 员工信息
	 */
	
	public void updateShiftMahjongEmployee(EmployeeDto employeeInfo){
	    ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
	    shiftMahjongEmployee.setEmployeeCode(employeeInfo.getEmployeeCode());
	    shiftMahjongEmployee.setName(employeeInfo.getName());
	    shiftMahjongEmployee.setHeadImage(employeeInfo.getHeadImage());
	    //查询轮牌步骤中已存在的员工
	    List<ShiftMahjongEmployee> shiftMahjongEmployeeList = shiftMahjongEmployeeMapper.selectShiftMahjongEmployeeList(employeeInfo.getEmployeeId());
	    
	    if (shiftMahjongEmployeeList != null && shiftMahjongEmployeeList.size() > 0) {
    	    //如果更换了职位
    	    if (shiftMahjongEmployeeList.get(0).getLevelId() != employeeInfo.getLevelId()) {
    	        //清除当前存在轮牌员工
    	        deleteShiftMahjongEmployee(employeeInfo.getEmployeeId());
    	        
    	        addShiftMahjongEmployee(employeeInfo);
    	        
    	    }
    	    else {
    	        for (int i = 0; i < shiftMahjongEmployeeList.size(); i++) {
    	            ShiftMahjongEmployee obj = shiftMahjongEmployeeList.get(i);
    	            
    	            shiftMahjongEmployee.setShiftMahjongEmployeeId(obj.getShiftMahjongEmployeeId());
    	            shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
    	        }
    	    }
	    }
	    
	    //删除缓存
        redisService.hdel(App.Redis.DEPT_PROJECT_MAHJONG_INFO_KEY_HASH, String.valueOf(employeeInfo.getDeptId()));
	}
	
	
	/**
	 * 新增员工信息时，更新轮牌员工信息
	* @author 王大爷
	* @date 2015年9月8日 下午5:21:09
	* @param employeeInfo 员工信息
	 */
	public void addShiftMahjongEmployee(EmployeeDto employeeInfo){
	    ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
	    shiftMahjongEmployee.setEmployeesId(employeeInfo.getEmployeeId());
	    shiftMahjongEmployee.setEmployeeCode(employeeInfo.getEmployeeCode());
        shiftMahjongEmployee.setName(employeeInfo.getName());
        shiftMahjongEmployee.setHeadImage(employeeInfo.getHeadImage());
        shiftMahjongEmployee.setLevelId(employeeInfo.getLevelId());
        shiftMahjongEmployee.setCreateTime(DateUtil.getCurDate());
        
        //查询新增员工级别对应岗位是否在轮牌中存在
	    /*List<Integer> shiftMahjongIdList = new ArrayList<Integer>();*/
	    
	    List<ShiftMahjongDto> shiftMahjongDtoList = shiftMahjongMapper.selectByStoreId(employeeInfo.getStoreId());
	    
	    Integer newEmployeeInfo = employeeInfo.getPositionId();
	    
	    for (ShiftMahjongDto shiftMahjongDto : shiftMahjongDtoList) {
	        String positionIdStr = shiftMahjongDto.getPositionId();
	        if (StringUtils.isNotBlank(positionIdStr)) {
	            String[] positionIds = positionIdStr.split(",");
	            for (int i = 0; i < positionIds.length; i++) {
	                String recommendStr = positionIds[i];

	                Integer positionId = Integer.parseInt(recommendStr.split(":")[0]);
	                if (positionId.intValue() == newEmployeeInfo.intValue()) {
	                    Integer shiftMahjongId = shiftMahjongDto.getShiftMahjongId();
	                    shiftMahjongEmployee.setShiftMahjongId(shiftMahjongId);
	                    shiftMahjongEmployee.setState(3);
//	                    shiftMahjongEmployee.setIsPunchCard(Integer.parseInt(recommendStr.split(":")[1]));
	                    shiftMahjongEmployee.setShiftMahjongOrder(999);
	                    shiftMahjongEmployeeMapper.insertSelective(shiftMahjongEmployee);
	                }
	            }
	        }
        }
	    
	    /*shiftMahjongEmployeeMapper.selectShiftMahjongIdList(employeeInfo.getLevelId())*/
	    /*for (int i = 0; i < shiftMahjongIdList.size(); i++) {
	        Integer shiftMahjongId = shiftMahjongIdList.get(i);
	        shiftMahjongEmployee.setShiftMahjongId(shiftMahjongId);
	        shiftMahjongEmployee.setState(3);
	        shiftMahjongEmployee.setShiftMahjongOrder(999);
	        shiftMahjongEmployeeMapper.insertSelective(shiftMahjongEmployee);
	    }*/
	    //删除缓存
        redisService.hdel(App.Redis.DEPT_PROJECT_MAHJONG_INFO_KEY_HASH, String.valueOf(employeeInfo.getDeptId()));
	}
	
	/**
     * 删除员工信息时，更新轮牌员工信息
    * @author 王大爷
    * @date 2015年9月8日 下午5:21:09
    * @param employeeId 员工信息
     */
    public void deleteShiftMahjongEmployee(Integer employeeId) {
        Map<String, Object> map = employeeInfoMapper.getDetail(employeeId);
        //查询所须修改资料员工对应的轮牌员工表标识
        List<ShiftMahjongEmployee> shiftMahjongEmployeeList = shiftMahjongEmployeeMapper.selectShiftMahjongEmployeeList(employeeId);
        for (int i = 0; i < shiftMahjongEmployeeList.size(); i++) {
            if (shiftMahjongEmployeeList.get(i).getState() == 0 || shiftMahjongEmployeeList.get(i).getState() == 4) {
                throw new RuntimeException("员工服务中，无法修改职位");
            }
            //将删除员工后所有员工向前移一位
            ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
            
            shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeList.get(i).getShiftMahjongEmployeeId());
            
            shiftMahjongEmployeeMapper.updateByToEnd(shiftMahjongEmployee);
            
            shiftMahjongEmployeeMapper.deleteByPrimaryKey(shiftMahjongEmployeeList.get(i).getShiftMahjongEmployeeId());
        }
        //删除缓存
        redisService.hdel(App.Redis.DEPT_PROJECT_MAHJONG_INFO_KEY_HASH, String.valueOf(map.get("deptId")));
    }
	
    /**
     * 上下牌接口
    * @author 王大爷
    * @date 2015年11月10日 上午10:20:06
    * @param shiftMahjongId 轮牌标识
    * @param employeeId 员工标识
    * @param type 类型
     */
    public void upOrDownShiftMahjong(Integer shiftMahjongId, Integer employeeId, Integer type){
    	Map<String, Integer> map = new HashMap<>();
    	map.put("shiftMahjongId", shiftMahjongId);
    	map.put("employeeId", employeeId);
    	ShiftMahjongEmployee obj = shiftMahjongEmployeeMapper.selectEmployeeByStepId(map);
    	
    	if (obj != null) {
    		
    		if (type == 1) {
    			Integer shiftMahjongOrder = shiftMahjongEmployeeMapper.selectByCount(shiftMahjongId);
                
                ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
                shiftMahjongEmployee.setShiftMahjongEmployeeId(obj.getShiftMahjongEmployeeId());
                shiftMahjongEmployee.setShiftMahjongOrder(shiftMahjongOrder + 1);
                shiftMahjongEmployee.setState(1);
                shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
    		}
    		else {
    			if (obj.getState()  == 0 || obj.getState()  == 4) {
                    throw new RuntimeException("员工服务中，无法签退！");
                }
                ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
                shiftMahjongEmployee.setShiftMahjongEmployeeId(obj.getShiftMahjongEmployeeId());
                shiftMahjongEmployee.setShiftMahjongOrder(999);
                shiftMahjongEmployee.setState(3);
                shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
    		}
    	}
    }
    
	/**
	 * 操作轮牌员工表
	* @author 王大爷
	* @date 2015年9月8日 下午3:19:54
	* @param levelId 级别list
	* @param storeId 门店标识
	* @param shiftMahjongId 轮牌信息标识
	 */
	public void operationShiftMahjongEmployee(List<EmployeeLevelBaseDto> levelId, Integer shiftMahjongId, Integer storeId){
        List<ShiftMahjongEmployee> list = new ArrayList<ShiftMahjongEmployee>();
        for (int i = 0; i < levelId.size(); i++){
            Integer level = levelId.get(i).getLevelId();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("levelId", level);
            map.put("storeId", storeId);
            List<EmployeeInfo> employeeInfoList = employeeInfoMapper.selectByLevelId(map);
            for (int j = 0 ; j < employeeInfoList.size(); j++){
                EmployeeInfo employeeInfo = employeeInfoList.get(j);
                
                //查询员工当日考勤情况
                EmployeeAttendance employeeAttendance = employeeAttendanceMapper.selectByEmployeeId(employeeInfo.getEmployeeId());
                
                ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
                shiftMahjongEmployee.setShiftMahjongId(shiftMahjongId);
                shiftMahjongEmployee.setEmployeeCode(employeeInfo.getEmployeeCode());
                shiftMahjongEmployee.setEmployeesId(employeeInfo.getEmployeeId());
                shiftMahjongEmployee.setName(employeeInfo.getName());
                shiftMahjongEmployee.setHeadImage(employeeInfo.getHeadImage());
                shiftMahjongEmployee.setLevelId(employeeInfo.getLevelId());
                
                if (employeeAttendance != null && StringUtils.isBlank(employeeAttendance.getSignOutTime())) {
                    Integer shiftMahjongOrder = shiftMahjongEmployeeMapper.selectByCount(shiftMahjongId);
                    shiftMahjongEmployee.setShiftMahjongOrder(shiftMahjongOrder + 1);
                    shiftMahjongEmployee.setState(1);
                }
                else {
                    shiftMahjongEmployee.setShiftMahjongOrder(999);
                    shiftMahjongEmployee.setState(3);
                }
                shiftMahjongEmployee.setIsAppoint(0);
                shiftMahjongEmployee.setAppointNumber(0);
                shiftMahjongEmployee.setCreateTime(DateUtil.getCurDate());
                list.add(shiftMahjongEmployee);
            }
        }
        if (list.size() > 0) {
            shiftMahjongEmployeeMapper.insertShiftMahjongEmployeeList(list);
        }
	}
	
	/**
	 * 
	* @author 王大爷
	* @date 2016年1月19日 上午10:43:02
	 */
	public void packagePositionId () {
	    List<Integer> storeIdList = storeInfoMapper.selectStoreIdAll();
	    for (Integer storeId : storeIdList) {
	        List<ShiftMahjongDto> shiftMahjongDtoList = shiftMahjongMapper.selectByStoreId(storeId);
	        for (ShiftMahjongDto shiftMahjongDto : shiftMahjongDtoList) {
	            
	            String positionIdStr = null;
	            
	            List<Map<String, Integer>> mapList = shiftMahjongEmployeeMapper.selectByPositionIdMap(shiftMahjongDto.getShiftMahjongId());
	            for (Map<String, Integer> map : mapList) {
	                if (positionIdStr == null) {
                        positionIdStr = map.get("positionId").toString() + ":" + map.get("isPunchCard").toString();
                    }
                    else {
                        positionIdStr = positionIdStr + "," + map.get("positionId").toString() + ":" + map.get("isPunchCard").toString();
                    }
                }
	            if (positionIdStr == null) {
	                positionIdStr = "";
	            }
	            ShiftMahjong objShiftMahjong = new ShiftMahjong();
                objShiftMahjong.setShiftMahjongId(shiftMahjongDto.getShiftMahjongId());
                objShiftMahjong.setPositionId(positionIdStr);
                shiftMahjongMapper.updateByPrimaryKeySelective(objShiftMahjong);
            }
        }
	}
}
