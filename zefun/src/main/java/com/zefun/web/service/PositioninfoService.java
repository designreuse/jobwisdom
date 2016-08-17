package com.zefun.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.PositionInfo;
import com.zefun.web.mapper.PositioninfoMapper;

import net.sf.json.JSONArray;
/**
 * 岗位信息
* @author 陈端斌
* @date 2015年8月4日 下午5:24:16 
*
 */
@Service
public class PositioninfoService {
	/**
	 * 岗位信息
	 */
	@Autowired
	private PositioninfoMapper positioninfoMapper;
	/**
	 * 查询某个店铺的职位信息
	 * 默认返回该门店最前面10条数据
	* @author chendb
	* @date 2015年8月7日 下午1:59:44
	* @param params 参数
	* @return ModelAndView
	 */
    public ModelAndView queryPositioninfo(Map<String, Object> params){
		ModelAndView mav = new ModelAndView("employee/positioninfo/positioninfo");
		int storeId=Integer.parseInt(params.get("storeId").toString());
		List<DeptInfoDto>list=positioninfoMapper.getDetpInfo(storeId);
		mav.addObject("list", list);
		mav.addObject("listStr", JSONArray.fromObject(list).toString());
		//获取岗位信息一起返回到页面
        PositionInfo positionInfo=new PositionInfo();
        positionInfo.setStoreId(storeId);
        List<PositionInfo> positionlist=positioninfoMapper.queryposition(positionInfo);
        mav.addObject("positionlist", positionlist);
		return mav;
	}
    
    /**
	 * 翻页功能
	* @author 陈端斌
	* @date 2015年8月7日 上午10:55:59
	* @param params 参数
	* @param pageNo 页码
	* @param pageSize 每页参数
	* @return BaseDto
	 */
	public BaseDto listAction(Map<String, Object> params, int pageNo, int pageSize){
		Page<PositionInfo> page = selectPageForPositioninfo(params, pageNo, pageSize);
		return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
	}
	/**
	 * 
	* @author chendb
	* @date 2015年8月11日 上午11:23:47
	* @param params 参数
	* @param pageNo 页码
	* @param pageSize 每页数量
	* @return Page<PositionInfo>
	 */
    private Page<PositionInfo> selectPageForPositioninfo(Map<String, Object> params, int pageNo, int pageSize){
		Page<PositionInfo> page = new Page<PositionInfo>();
		page.setParams(params);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		List<PositionInfo> list = positioninfoMapper.querypositioninfo(page);
		page.setResults(list);
		return page;
	}
    
	/**
	 * 新增岗位
	* @author 陈端斌
	* @date 2015年8月4日 下午8:05:58
	* @param positioninfo bean
	* @return int
	 */
	public int addPositioninfo(PositionInfo positioninfo){
		
		//判断岗位编码或者岗位名称是否已经存在
		List<PositionInfo> list1=positioninfoMapper.isPositionCode(positioninfo);
		if (list1.size()>0){
			//岗位编码已经存在了
			return 1;
		}
		List<PositionInfo> list=positioninfoMapper.isPositionName(positioninfo);
		if (list.size()>0){
			//岗位名称已经存在了
			return 2;
		}
		positioninfoMapper.insert(positioninfo);
		return 0;
	}
	
	/**
	 * 修改岗位
	* @author 陈端斌
	* @date 2015年8月4日 下午8:05:53
	* @param positioninfo 参数
	* @return int
	 */
	public int updatePositioninfo(PositionInfo positioninfo){
	    
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("positionName", positioninfo.getPositionName());
	    map.put("storeId", positioninfo.getStoreId());
	    List<PositionInfo> selectByName = positioninfoMapper.selectByName(map);
	    if (selectByName.size()>0 && positioninfo.getPositionId() == null){
	        return 2;  
	    }
	    if (selectByName.size()==1 && positioninfo.getPositionId() != selectByName.get(0).getPositionId()){
            return 2;  
        }
	 
		positioninfoMapper.updateByPrimaryKeySelective(positioninfo);
		return 0;	
	}
	/**
	 * 下拉框获取岗位（公共接口）
	* @author chendb
	* @date 2015年8月11日 上午11:26:50
	* @param positioninfo bean
	* @return List<PositionInfo>
	 */
    public List<PositionInfo>queryPosition(PositionInfo positioninfo){
    	List<PositionInfo> list=positioninfoMapper.queryposition(positioninfo);
		return list;
	}
	/**
	 * 获取岗位和职位的信息列表
	* @author chendb
	* @date 2015年8月24日 下午4:14:58
	* @param storeId 门店标识
	* @return List<PositionInfoDto>
	 */
	public List<DeptInfoDto>getpositionInfo(Integer storeId){
	    
	    List<DeptInfoDto>list=positioninfoMapper.getDetpInfo(storeId);
        return list;
	}
	 
}
