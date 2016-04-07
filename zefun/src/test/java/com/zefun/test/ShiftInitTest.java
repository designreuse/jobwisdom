package com.zefun.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zefun.common.utils.DateUtil;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.ShiftInfo;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.ShiftMapper;

/**
 * 
 * @author Administrator
 *
 */
public class ShiftInitTest extends BaseTest {

	/**  */
	@Autowired
	private ShiftMapper shiftMapper;
	/**  */
	@Autowired
	private DeptInfoMapper deptInfoMapper;
	
	/**
	 * 
	 */
	@Test
	public void test() {
		List<DeptInfo> deptInfoList = deptInfoMapper.selectAllDetp();
		
		for (int i=0; i<deptInfoList.size(); i++) {
			DeptInfo deptInfo = deptInfoList.get(i); 
			if (shiftMapper.selectCountShiftByStoreAndDept(deptInfoList.get(i).getStoreId(), deptInfoList.get(i).getDeptId()) == 0) {
//				deptService.addshift(deptInfoList.get(i).getStoreId(), deptInfoList.get(i).getDeptId());
				ShiftInfo shiftInfo=new ShiftInfo();
		        shiftInfo.setStoreId(deptInfo.getStoreId());
		        shiftInfo.setDeptId(deptInfo.getDeptId());
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
			}
		}
		
		
		
	}

}
