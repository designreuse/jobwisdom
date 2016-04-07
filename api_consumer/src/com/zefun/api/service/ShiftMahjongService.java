package com.zefun.api.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zefun.api.entity.ShiftMahjongEmployee;
import com.zefun.api.mapper.EmployeeInfoMapper;
import com.zefun.api.mapper.ShiftMahjongEmployeeMapper;
import com.zefun.api.utils.App;

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
	
	
	
	/**
     * 下牌接口
    * @author 王大爷
    * @date 2015年11月10日 上午10:22:36
    * @param employeeId 员工标识
     */
    public void downShiftMahjong(Integer employeeId){
        Map<String, Object> map = employeeInfoMapper.getDetail(employeeId);
        //查询所须修改资料员工对应的轮牌员工表标识
        List<ShiftMahjongEmployee> shiftMahjongEmployeeList = shiftMahjongEmployeeMapper.selectShiftMahjongEmployeeList(employeeId);
        for (int i = 0; i < shiftMahjongEmployeeList.size(); i++) {
            if (shiftMahjongEmployeeList.get(i).getState()  == 0 || shiftMahjongEmployeeList.get(i).getState()  == 4) {
                throw new RuntimeException("员工服务中，无法签退！");
            }
            ShiftMahjongEmployee shiftMahjongEmployee = new ShiftMahjongEmployee();
            shiftMahjongEmployee.setShiftMahjongEmployeeId(shiftMahjongEmployeeList.get(i).getShiftMahjongEmployeeId());
            shiftMahjongEmployee.setShiftMahjongOrder(999);
            shiftMahjongEmployee.setState(3);
            shiftMahjongEmployeeMapper.updateByPrimaryKeySelective(shiftMahjongEmployee);
        }
        //删除缓存
        redisService.hdel(App.Redis.DEPT_PROJECT_MAHJONG_INFO_KEY_HASH, String.valueOf(map.get("deptId")));
    }
}
