package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zefun.common.consts.App;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.ShiftInfo;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.ShiftMapper;


/**
 * 关于部门
* @author chendb
* @date 2015年9月8日 上午11:11:02
 */
@Service
public class DeptService {
    /**班次*/
    @Autowired
    private ShiftMapper shiftMapper;
    /**
     * 关于部门
     */
    @Autowired
    private DeptInfoMapper deptInfoMapper;
    
    /** redis 缓存服务对象 */
    @Autowired
    private RedisService redisService;
    /**
     * 新增部门
    * @author chendb
    * @date 2015年9月8日 上午11:14:09
    * @param deptInfo 参数
    * @return int
     */
    public int adddDept(DeptInfo deptInfo){
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("deptCode", deptInfo.getDeptCode());
        map.put("storeId", deptInfo.getStoreId());
        //判断编码是否存在
        int count =deptInfoMapper.getCount(map);
        if (count>0){
            return 1;  
        }
        //判断名称是否存在
        Map<String, Object>map1=new HashMap<String, Object>();
        map1.put("deptName", deptInfo.getDeptName());
        map1.put("storeId", deptInfo.getStoreId());
        int count1 =deptInfoMapper.getCount(map1);
        
        if (count1>0){
            return 2;  
        }
        deptInfoMapper.insert(deptInfo);
        //新增部门后顺便一起新增下班次时间
        addshift(deptInfo.getStoreId(), deptInfo.getDeptId());
        return 0;
    }
    /**
     * 新增部门时候班次初始化
    * @author chendb
    * @date 2015年11月9日 下午5:49:57
    * @param storeId 门店
    * @param deptId 部门
    * @return int
     */
    @Transactional
    public int addshift(Integer storeId, Integer deptId){
        ShiftInfo shiftInfo=new ShiftInfo();
        shiftInfo.setStoreId(storeId);
        shiftInfo.setDeptId(deptId);
        shiftInfo.setStartTime("08:00");
        shiftInfo.setEndTime("12:00");
        shiftInfo.setShifName("早班");
        shiftInfo.setCreateTime(DateUtil.getCurTime());
        shiftMapper.insertShiftinfo(shiftInfo);
        shiftInfo.setStartTime("12:00");
        shiftInfo.setEndTime("18:00");
        shiftInfo.setShifName("中班");
        shiftMapper.insertShiftinfo(shiftInfo);
        shiftInfo.setStartTime("18:00");
        shiftInfo.setEndTime("22:00");
        shiftInfo.setShifName("晚班");
        shiftMapper.insertShiftinfo(shiftInfo);
        shiftInfo.setStartTime("08:00");
        shiftInfo.setEndTime("22:00");
        shiftInfo.setShifName("全班");
        shiftMapper.insertShiftinfo(shiftInfo);
        return 0;
    }
    /**
     * 修改部门
    * @author chendb
    * @date 2015年9月8日 下午3:27:50
    * @param deptInfo 参数
    * @return int
     */
    public int updatedDept(DeptInfo deptInfo){
    	int deptId = deptInfo.getDeptId();
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("deptCode", deptInfo.getDeptCode());
        map.put("deptId", deptId);
        map.put("storeId", deptInfo.getStoreId());
        //判断编码是否存在
        int count =deptInfoMapper.getCount(map);
        if (count>0){
            return 1;  
        }
        //判断名称是否存在
        Map<String, Object>map1=new HashMap<String, Object>();
        map1.put("deptName", deptInfo.getDeptName());
        map1.put("deptId", deptId);
        map1.put("storeId", deptInfo.getStoreId());
        int count1 =deptInfoMapper.getCount(map1);
        if (count1>0){
            return 2;  
        }
        deptInfoMapper.updateByPrimaryKeySelective(deptInfo);
        
        //清除缓存
        wipeRedis(deptId);
        return 0;
    }
    /**
     * 删除部门
    * @author chendb
    * @date 2015年9月8日 下午3:29:51
    * @param deptId 
    * @param storeId 
    * @return int
     */
    public int deleteDept(Integer deptId, Integer storeId){
        //判断部门是否被引用了
        int count =deptInfoMapper.isQuote(deptId);
        if (count>0){
            return 1;  
        }
        Map<String, Object>map=new HashMap<String, Object>();
        map.put("storeId", storeId);
        map.put("deptId", deptId);
        int count1=deptInfoMapper.countProjectDept(map);
        if (count1>0){
            return 2;  
        }
        int count2=deptInfoMapper.countGoodsDept(map);
        if (count2>0){
            return 3;  
        }
        int count3=deptInfoMapper.countComboDept(map);
        if (count3>0){
            return 4;  
        }
        int count4=deptInfoMapper.countCategoryDept(map);
        if (count4>0){
            return 5;  
        }
        //只是修改状态
        deptInfoMapper.deleteByPrimaryKey(deptId);
        //清除缓存
        wipeRedis(deptId);
        return 0;
    }
    /**
     * 关于获取部门信息
    * @author chendb
    * @date 2015年9月16日 下午12:14:55
    * @param storeId 门店标识
    * @return List<DeptInfo>
     */
    public List<DeptInfo> getDeptInfo(Integer storeId){
        List<DeptInfo> list=deptInfoMapper.getDeptInfo(storeId); 
        return list;
    }

    /**
     * 
    * @author chendb
    * @date 2015年10月19日 上午9:41:49
    * @param cell 参数
    * @return String
     */
    public String changeCellToString(Cell cell){

        String returnValue = "";

        if (null != cell){

            switch(cell.getCellType()){
                //数字
                case HSSFCell.CELL_TYPE_NUMERIC:
        
                    Double doubleValue = cell.getNumericCellValue();
            
                    String str = doubleValue.toString();
            
                    if (str.contains(".0")){
            
                        str = str.replace(".0", "");
            
                    }
            
                    Integer intValue = Integer.parseInt(str);
            
                    returnValue = intValue.toString();
            
                    break;
        
                case HSSFCell.CELL_TYPE_STRING:    
                    //字符串
                    returnValue = cell.getStringCellValue();
            
                    break;
        
                case HSSFCell.CELL_TYPE_BOOLEAN:   
                    //布尔
                    Boolean booleanValue=cell.getBooleanCellValue();
            
                    returnValue = booleanValue.toString();
        
                    break;
                // 空值
                case HSSFCell.CELL_TYPE_BLANK:     
            
                    returnValue = "";
        
                    break;
                // 公式
                case HSSFCell.CELL_TYPE_FORMULA:   
            
                    returnValue = cell.getCellFormula();
        
                    break;
                // 故障
                case HSSFCell.CELL_TYPE_ERROR:     
            
                    returnValue = "";
        
                    break;
        
                default:
                
                    break;

            }

        }

        return returnValue;

    }
    
    
    /**
     * 清除redis中相关模块的缓存数据
    * @author 张进军
    * @date Jan 11, 2016 2:21:00 PM
    * @param deptId	部门标识
     */
    private void wipeRedis(int deptId){
    	redisService.hdel(App.Redis.DEPT_GOODS_BASE_INFO_KEY_HASH, deptId);
        redisService.hdel(App.Redis.DEPT_PROJECT_BASE_INFO_KEY_HASH, deptId);
        redisService.hdel(App.Redis.DEPT_PROJECT_MAHJONG_INFO_KEY_HASH, deptId);
    }
    
    /**
     * 新增或者修改部门
    * @author 高国藩
    * @date 2016年6月23日 下午6:21:49
    * @param deptInfo deptInfo
    * @return         deptInfo
     */
    @Transactional
    public BaseDto saveOrUpdateDeptInfo(DeptInfo deptInfo) {
        if (deptInfo.getDeptId()!=null){
            deptInfoMapper.updateByPrimaryKeySelective(deptInfo);
        }
        else {
            deptInfoMapper.insert(deptInfo);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, deptInfo);
    }
    
}
