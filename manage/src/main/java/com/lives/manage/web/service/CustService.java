/**
 * 
 */
package com.lives.manage.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lives.manage.common.exception.ServiceException;
import com.lives.manage.common.util.HttpClientUtils;
import com.lives.manage.common.util.StringUtils;
import com.lives.manage.web.entity.CustomerInfo;
import com.lives.manage.web.mapper.CustomerInfoMapper;
import com.lives.manage.web.mapper.GoodsInfoMapper;
import com.lives.manage.web.mapper.UserMapper;

/**
 * @author 陈端斌
 * @date 2015年6月8日 下午2:06:09
 * 
 */
@Service
public class CustService {
	@Autowired
	private CustomerInfoMapper customerInfoMapper;
	@Autowired
	private GoodsInfoMapper goodsInfoMapper;
	@Autowired
	private UserMapper userMapper;
	/**
	 * 新增客户
	* @author chendb
	* @date 2015年10月30日 下午2:36:28
	* @param customerInfo
	* @return
	 * @throws ServiceException 
	 */
	@SuppressWarnings("static-access")
    @Transactional
	public int addCust(CustomerInfo customerInfo) throws ServiceException {
		int type=customerInfo.getCustomerType();
		//判断类型为连锁店2的时候需要判断公司名称是否存在
		if(type==2){
			int count1 =customerInfoMapper.getCompanyNameCount(customerInfo);
			if(count1>0){
				//说名公司名称已经存在了
				return 1;
			}
		}else{
			if(type==3){
				int count1 =customerInfoMapper.getCompanyNameCount(customerInfo);
				if(count1<1){
					//说名公司名称不存在了
					return 3;
				}
			}
			int count =customerInfoMapper.getcustNameCount(customerInfo);
			if(count>0){
				//说名客户名称已经存在了
				return 2;
			}
		}
		//Map<String,Object> map=new HashMap<String, Object>();
		//获取操作用户拥有的金额
		Map<String,Object> map1=new HashMap<String, Object>();
		map1.put("userId", customerInfo.getCreatorId());
		Map<String,Object> moneymap=userMapper.getUserMoney(map1);
		//总次数-1
		int m1=Integer.parseInt(moneymap.get("money").toString());
		int money= m1-1;
		if(money<0){
		    return 9;
		}
		
		map1.put("money", money);
		
		//新增用户开始
		customerInfoMapper.insertSelective(customerInfo);
		
		//叼平台接口
		HttpClientUtils httpClientUtils=new HttpClientUtils();
		Map<String,Object> mapinfo=new HashMap<String, Object>();
		
		mapinfo.put("username", customerInfo.getUserName());
		String password=StringUtils.MD5(StringUtils.MD5("123456"));
		mapinfo.put("password", password);
		mapinfo.put("name", "管理员");
		mapinfo.put("phone", customerInfo.getPhone());
		mapinfo.put("address", customerInfo.getAddr());
		mapinfo.put("storeId", customerInfo.getCustomerId());
		mapinfo.put("storeName", customerInfo.getCustomerName());
		mapinfo.put("storeType", customerInfo.getCustomerType());
		
		if(customerInfo.getParentId()!=null){
		    mapinfo.put("parentId", customerInfo.getParentId()); 
		}else{
		    mapinfo.put("parentId", customerInfo.getCustomerId()); 
		}
		//本地测试地址
		String info=httpClientUtils.sendPostReq("http://localhost:8080/zefun/storeinfo/action/addstore", mapinfo, null);
		if(info==null){
		    customerInfoMapper.deleteByPrimaryKey(customerInfo.getCustomerId());
            throw new ServiceException(1, "系统出错！");
		}
		JSONObject  jasonObject = JSONObject.fromObject(info);
		@SuppressWarnings("unchecked")
        Map<String, Object> bean = (Map<String, Object>)jasonObject;
		//判断有没有新增成功   没成功删除掉数据
		String code=bean.get("code").toString();
		if(!code.equals("0")){
		    customerInfoMapper.deleteByPrimaryKey(customerInfo.getCustomerId());
              throw new ServiceException(1, "账号以及存在了请换一个再新增！");
		}
		//新增成功了 减去数量
		userMapper.updateMoney(map1);
		return 0;
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午2:06:09 修改客户信息
	 */
	public int updateCust(CustomerInfo customerInfo) {
		int type=customerInfo.getCustomerType();
		//判断类型为连锁店2的时候需要判断公司名称是否存在
		if(type==2){
			int count1 =customerInfoMapper.getCompanyNameCount(customerInfo);
			if(count1>0){
				CustomerInfo info=new CustomerInfo();
				info.setCompanyName(customerInfo.getCompanyName());
				int count2= customerInfoMapper.queryCustomerId(info);
				int id=customerInfo.getCustomerId();
				if(count2!=id){
					//说名公司名称已经存在了
					return 1;
				}
			}
		}else{
			if(type==3){
				int count3 =customerInfoMapper.getCompanyNameCount(customerInfo);
				if(count3<1){
					//说名公司名称不存在了
					return 3;
				}
			}
			int count5 =customerInfoMapper.getcustNameCount(customerInfo);
			if(count5>0){
				CustomerInfo info=new CustomerInfo();
				info.setCustomerName(customerInfo.getCustomerName());
				int count4= customerInfoMapper.queryCustomerId(info);
				int id=customerInfo.getCustomerId();
				if(count4!=id){
					//说名客户名称已经存在了
					return 2;
				}
			}
		}
		customerInfoMapper.updateByPrimaryKeySelective(customerInfo);
		return 0;
	}

	/**
	 * @author 陈端斌
	 * @date 2015年6月8日 下午2:06:09 查询客户
	 */
	public List<Map<String ,Object>> queryCustInfo(Map<String ,Object> map) {
		List<Map<String ,Object>> queryCustInfo = customerInfoMapper
				.queryCustInfo(map);
		return queryCustInfo;
	}
	public int queryCustCount(Map<String ,Object> map) {
		int count = customerInfoMapper.queryCustCount(map);
		return count;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年7月1日 下午2:06:09 
	 * 查询所有客户
	 */
	public List<Map<String,Object>>  getcustomerName() {
		List<Map<String,Object>> getcustomerName = customerInfoMapper.getcustomerName();
		return getcustomerName;
	}
	/**
	 * @author 陈端斌
	 * @date 2015年7月1日 下午2:06:09 
	 * 查询所有客户 公司名称
	 */
	public List<Map<String,Object>>  getCompanyName() {
		List<Map<String,Object>> getCompanyName = customerInfoMapper.getCompanyName();
		return getCompanyName;
	}
	
	
	/**
	 * @author 陈端斌
	 * @date 2015年7月1日 下午2:06:09 
	 * 根据客户名称查询客户id
	 */
	public int getcustomerId(Map<String,Object> map) {
		int getcustomerId=0;
		int count=customerInfoMapper.getcustomerIdCount(map);
		if(count>0){
			getcustomerId = customerInfoMapper.getcustomerId(map);
		}
		 
		return getcustomerId;
	}
	
	/**
	 * @author 陈端斌
	 * @date 2015年7月1日 下午2:06:09 
	 * 根据客户名称查询客户id
	 */
	public Map<String,Object> getCustomerInfo(Map<String,Object> map) {
		Map<String,Object> infomap=new HashMap<String, Object>();
		infomap=customerInfoMapper.getCustomerInfo(map);
		return infomap;
	}
	
	
}
