package com.lives.platform.web.contorller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lives.platform.common.consts.Url;
import com.lives.platform.common.exception.ServiceException;
import com.lives.platform.common.util.DateUtils;
import com.lives.platform.web.entity.Position;
import com.lives.platform.web.service.PositionMapperService;


/**
 * 提成基础信息
 * @author 王潇
 * @date 2015年6月8日 下午2:06:08
 *
 */

@Controller
public class PositionController {

	@Autowired
	private PositionMapperService positionMapperService;
	
	
	@RequestMapping(value = Url.Position.POSITIONLOG)
	public String positionLog() throws ServiceException{
       return "position/position";
	}
	
	/**
	 * 新增基础信息
	* @author 王潇
	* @date 2015年6月8日 下午2:11:32
	* @param Position
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Position.POSITIONSUBMIT)
	public String positionAdd(Position position) throws ServiceException{
		//组装会员卡号管理表数据
		position.setCreateTime(DateUtils.getCurTime());
		Integer type  = positionMapperService.addPosition(position);
		
		if(type == 1){
			return "position/position";
		}else{
			return "position/position";
		}

	}
	
	/**
	 * 查询单条记录
	* @author 王潇
	* @date 2015年6月8日 下午2:16:17
	* @param vipId
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Position.POSITIONSELECT)
	@ResponseBody
	public Position positionIdSelect(Integer positionId) throws ServiceException{
		//查询会员综合信息
		Position position = positionMapperService.selectPosition(positionId);
    	return position;
	}
	
	/**
	 * 修改记录
	* @author 王潇
	* @date 2015年6月8日 下午2:16:17
	* @param vipId
	* @return
	* @throws ServiceException
	 */
	@RequestMapping(value = Url.Position.POSITIONUPDATE)
	@ResponseBody
	public String positionUpdate(Position position) throws ServiceException{
		//查询会员综合信息
		int i = positionMapperService.UpdatePosition(position);
		if(i == 0){
			
		}
    	return  "position/position";
	}
	
	/**
	 * 删除提成基础信息
	* @author 王潇
	* @date 2015年6月8日 下午2:56:38
	* @param projectPositionId
	* @return
	* @throws ServiceException
	 */
	
	@RequestMapping(value = Url.Position.POSITIONDELETE)
	public String positionDelete(Integer positionId) throws ServiceException{
		//查询会员综合信息
		int i = positionMapperService.deletePosition(positionId);
        if(i == 0){
			
		}
    	return  "position/position";
	}
	
	@RequestMapping(value = Url.Position.SELECTPOSITIONBYSTORESIDLIST)
	@ResponseBody
	public List<Position> selectPositionByStoresIdList(Integer storesId) throws ServiceException{
		//查询会员综合信息
		List<Position> positionList = positionMapperService.selectPositionByStoresIdList(storesId);
    	return  positionList;
	}
	
}
