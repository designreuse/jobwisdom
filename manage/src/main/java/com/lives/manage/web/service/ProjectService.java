package com.lives.manage.web.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lives.manage.common.util.DateUtils;
import com.lives.manage.web.entity.GoodsInfo;
import com.lives.manage.web.mapper.GoodsInfoMapper;
import com.lives.manage.web.mapper.UserMapper;

/**
 * @author 陈端斌
 * @date 2015年6月8日 上午11:31:25
 * 
 */
@Service
public class ProjectService {

	@Autowired
	private GoodsInfoMapper goodsInfoMapper;
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 新增项目产品
	 * @author 陈端斌
	 * @date 2015年6月18日 下午2:06:09
	 */
	public String addGoodsInfo(GoodsInfo record) {
		goodsInfoMapper.insert(record);
		return null;
	}
	/**
	 * 删除项目产品
	 * @author 陈端斌
	 * @date 2015年6月18日 下午2:06:09
	 */
	public String deleteGoodsInfo(Integer goodsId) {
		goodsInfoMapper.deleteByPrimaryKey(goodsId);
		return null;
	}
	
	/**
	 * 修改项目产品
	 * @author 陈端斌
	 * @date 2015年6月18日 下午2:06:09
	 */
	public String updateGoodsInfo(GoodsInfo record) {
		goodsInfoMapper.updateByPrimaryKeySelective(record);
		return null;
	}
	
	/**
	 * 查询项目产品
	 * @author 陈端斌
	 * @date 2015年6月18日 下午2:06:09 
	 */
	public List<GoodsInfo> queryGoodsInfo(Map<String, Object> map) {
		List<GoodsInfo> queryGoodsInfo=goodsInfoMapper.queryGoodsInfo(map);
		return queryGoodsInfo;
	}
	public int goodsInfoCount(Map<String, Object> map) {
		int count =goodsInfoMapper.goodsInfoCount(map);
		return count;
	}
	
	/**
	 * 查询客户消费的项目订单
	 * @author 陈端斌
	 * @date 2015年7月1日 下午2:06:09 
	 */
	public List<Map<String,Object>> getCustSale(Map<String, Object> map) {
		List<Map<String,Object>> getCustSale=goodsInfoMapper.getCustSale(map);
		return getCustSale;
	}
	public int getCustSaleCount(Map<String, Object> map) {
		int count=goodsInfoMapper.getCustSaleCount(map);
		return count;
	}
	
	
	/**
	 * 客户新增消费的项目订单
	 * @author 陈端斌
	 * @date 2015年7月1日 下午2:06:09 
	 */
	@Transactional
	public  int addCustSale(List<Map<String, Object>> maplist,Map<String ,Object> order) {
		
		//获取操作用户拥有的金额
		Map<String,Object> map1=new HashMap<String, Object>();
		map1.put("userId", order.get("creatorId"));
		Map<String,Object> moneymap=userMapper.getUserMoney(map1);
		//总金额减去花去的金额
		String m1=moneymap.get("money").toString();
		BigDecimal money1=new BigDecimal(m1);
		//消费的金额
		String m2=order.get("realAmount").toString();
		BigDecimal money2=new BigDecimal(m2);
		BigDecimal money= money1.subtract(money2);
		if(money1.compareTo(money2)==-1){
			return 9;
		}
		map1.put("money", money);
		userMapper.updateMoney(map1);
		//记录入流水表
		map1.put("waterType", 1);
		map1.put("dsc", "新增客户购买版本");
		map1.put("money", m2);
		map1.put("creatorId", order.get("creatorId"));
		map1.put("createTime", DateUtils.getCurTime());
		userMapper.insertWater(map1);
		
		
		if(maplist.size()>0){
			goodsInfoMapper.addCustOrder(order);
			int orderId=Integer.parseInt(order.get("orderId").toString());
			
			for (int i = 0; i < maplist.size(); i++) {
				Map<String, Object> map=new HashMap<String, Object>();
				map=maplist.get(i);
				map.put("orderId", orderId);
				goodsInfoMapper.addCustSaledetail(map);
				//修改库存量数据
				goodsInfoMapper.updateGoodsStock(map);
			}
		}
		return 0;
	}
	
	/**
	 * 查询项目名称
	 * @author 陈端斌
	 * @date 2015年7月3日 下午2:06:09 
	 */
	public List<Map<String,Object>> getGoodsName(Map<String, Object> map) {
		List<Map<String,Object>> getGoodsName=goodsInfoMapper.getGoodsName(map);
		return getGoodsName;
	}
	/**
	 * 查询项目单价
	 * @author 陈端斌
	 * @date 2015年7月3日 下午2:06:09 
	 */
	public Map<String,Object> getGoodsPrice(Map<String, Object> map) {
		Map<String,Object> getGoodsName=goodsInfoMapper.getGoodsPrice(map);
		return getGoodsName;
	}
	
	/**
	 * 查询项目名称
	 * @author 陈端斌
	 * @date 2015年7月3日 下午2:06:09 
	 */
	public List<Map<String,Object>> queryGoodsName(Map<String,Object> map) {
		List<Map<String,Object>> getGoodsName=goodsInfoMapper.queryGoodsName(map);
		return getGoodsName;
	}
	
}
