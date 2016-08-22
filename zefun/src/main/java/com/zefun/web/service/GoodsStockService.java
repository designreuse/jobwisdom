package com.zefun.web.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App.Session;
import com.zefun.common.consts.View;
import com.zefun.common.exception.ServiceException;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AccountGoods;
import com.zefun.web.entity.EmployeeInfo;
import com.zefun.web.entity.GoodsStock;
import com.zefun.web.entity.GoodsStockKey;
import com.zefun.web.entity.StockFlow;
import com.zefun.web.entity.StockFlowDetail;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.mapper.AccountGoodsMapper;
import com.zefun.web.mapper.EmployeeInfoMapper;
import com.zefun.web.mapper.GoodsStockMapper;
import com.zefun.web.mapper.StockFlowDetailMapper;
import com.zefun.web.mapper.StockFlowMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.StoreSettingMapper;

import net.sf.json.JSONArray;

/**
 * 商品
* @author 洪秋霞
* @date 2015年8月7日 下午5:04:10 
*
 */
@Service
@Transactional
public class GoodsStockService {
    
    /** 企业商品 */
    @Autowired
    private AccountGoodsMapper accountGoodsMapper;
    /** 门店管理  */
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    /** 商品库存  */
    @Autowired
    private GoodsStockMapper goodsStockMapper;
    /** 商品库存流水  */
    @Autowired
    private StockFlowMapper stockFlowMapper;
    /** 门店员工  */
    @Autowired
    private EmployeeInfoMapper employeeInfoMapper;
    /** 库存明细  */
    @Autowired
    private StockFlowDetailMapper stockFlowDetailMapper;
    /** 基本设置  */
    @Autowired
    private StoreSettingMapper storeSettingMapper;
    /**
     * 查看进销存页面
    * @author 高国藩
    * @date 2016年5月31日 下午4:07:49
    * @param storeAccount storeAccount
    * @param request      request
    * @return             ModelAndView
     */
    public ModelAndView viewGoodsStock(String storeAccount, HttpServletRequest request) {
        
        ModelAndView model = new ModelAndView(View.Stock.VIEW_STOCK);
        List<StoreInfo> storeInfos = storeInfoMapper.selectByStoreAccount(storeAccount);
        if (storeInfos.size() > 0){
            Object storeId = request.getSession().getAttribute(Session.STORE_ID);
            if (storeId!=null){
                model = new ModelAndView(View.Stock.VIEW_STORE_STOCK);
            }
            else {
                storeId = storeInfos.get(0).getStoreId();
                model = new ModelAndView(View.Stock.VIEW_STOCK);
            }
            
            StockFlow query = new StockFlow();
            query.setStoreAccount(storeAccount);
            List<StockFlow> stockFlows = stockFlowMapper.selectByProperties(query);
            
            final Integer queryStoreId = Integer.parseInt(storeId.toString());
            
            List<EmployeeInfo> employeeInfos = employeeInfoMapper.selectEmployeeByStoreId(queryStoreId);
            model.addObject("employeeInfos", employeeInfos);
            
            /**入库管理*/
            List<StockFlow> inFlows = stockFlows.stream()
                    .filter(flow -> flow.getStockType()==1&&flow.getToStore().equals(queryStoreId))
                    .collect(Collectors.toList());
            /**出库管理*/
            List<StockFlow> outFlows = stockFlows.stream()
                    .filter(flow -> flow.getStockType()==2&&flow.getFromStore().equals(queryStoreId))
                    .collect(Collectors.toList());
            /**商品调拨管理*/
            List<StockFlow> moveFlows = stockFlows.stream().filter(flow -> flow.getStockType()==3).collect(Collectors.toList());
            
            model.addObject("inFlows", JSONArray.fromObject(inFlows));
            model.addObject("outFlows", JSONArray.fromObject(outFlows));
            model.addObject("moveFlows", JSONArray.fromObject(moveFlows));
            
            AccountGoods accountGoods = new AccountGoods();
            accountGoods.setStoreAccount(storeAccount);
            List<AccountGoods> goods = accountGoodsMapper.selectByProperties(accountGoods);
            
            model.addObject("storeInfos", storeInfos);
            model.addObject("goods", goods);
        }
        return model;
    }

    /**
     * 库存设置
    * @author 高国藩
    * @date 2016年5月31日 下午6:36:18
    * @param storeAccount storeAccount
    * @param stockFlow    stockFlow
    * @return             BaseDto
     */
    @Transactional
    public BaseDto actionGoodsStock(String storeAccount, StockFlow stockFlow) {
        stockFlow.setStoreAccount(storeAccount);
        try {
        	switch (stockFlow.getStockType()) {
	            case 1:
	                return inStock(stockFlow);
	            case 2:
	                return outStock(stockFlow);
	            case 3:
	                return moveStock(stockFlow);
	            default:
	                return null;
            }
		}  
        catch (ServiceException e) {
			return new BaseDto(-1, e.getMsg());
		}
    }
    
    /**
     * 商品调拨
    * @author 高国藩
    * @date 2016年5月31日 下午8:56:10
    * @param stockFlow  stockFlow
    * @return           BaseDto
     */
    private BaseDto moveStock(StockFlow stockFlow) {
        List<Integer> aIds = Arrays.asList(stockFlow.getaIds().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> count = Arrays.asList(stockFlow.getStockCount().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        Integer toStore = stockFlow.getToStore();
        Integer fromStore = stockFlow.getFromStore();
        List<StockFlowDetail> stockFlowDetails = new ArrayList<>();
        for (int i = 0; i < aIds.size(); i++) {
            
            BigDecimal costPrice = accountGoodsMapper.selectByPrimaryKey(aIds.get(i)).getCostPrice();
            // 调拨直接存入两条数据
            StockFlowDetail stockFlowDetailFromStore = new StockFlowDetail();
            StockFlowDetail stockFlowDetailToStore = new StockFlowDetail();
            
            stockFlowDetailFromStore.setaId(aIds.get(i));
            stockFlowDetailFromStore.setFlowCount(count.get(i));
            stockFlowDetailFromStore.setCostPrice(costPrice);
            stockFlowDetailFromStore.setFlowAmount(costPrice.multiply(new BigDecimal(count.get(i))));
            stockFlowDetailFromStore.setStockType(stockFlow.getStockType());
            stockFlowDetailFromStore.setFlowType(stockFlow.getFlowType());
            stockFlowDetailFromStore.setFromStore(stockFlow.getFromStore());
            stockFlowDetailFromStore.setEmployeeId(stockFlow.getLibraryObject());
            stockFlowDetailFromStore.setStoreAccount(stockFlow.getStoreAccount());
            
            stockFlowDetailToStore.setaId(aIds.get(i));
            stockFlowDetailToStore.setFlowCount(count.get(i));
            stockFlowDetailToStore.setCostPrice(costPrice);
            stockFlowDetailToStore.setFlowAmount(costPrice.multiply(new BigDecimal(count.get(i))));
            stockFlowDetailToStore.setStockType(stockFlow.getStockType());
            stockFlowDetailToStore.setFlowType(stockFlow.getFlowType());
            stockFlowDetailToStore.setToStore(stockFlow.getToStore());
            stockFlowDetailToStore.setEmployeeId(stockFlow.getLibraryObject());
            stockFlowDetailToStore.setStoreAccount(stockFlow.getStoreAccount());
            
            //发货
            GoodsStockKey key = new GoodsStockKey();
            key.setaId(aIds.get(i));
            key.setStoreId(fromStore);
            GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(key);
            if (goodsStock != null && goodsStock.getCount() >= count.get(i)){
                stockFlowDetailFromStore.setGoodsStockCount(goodsStock.getCount());
                goodsStock.setCount(goodsStock.getCount()-count.get(i));
                goodsStock.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.updateByPrimaryKeySelective(goodsStock);
            }
            else {
                return new BaseDto(-1, "出库失败,商品没有库存");
            }
            //收货
            GoodsStockKey key2 = new GoodsStockKey();
            key2.setaId(aIds.get(i));
            key2.setStoreId(toStore);
            GoodsStock goodsStock2 = goodsStockMapper.selectByPrimaryKey(key2);
            if (goodsStock2 != null){
                stockFlowDetailToStore.setGoodsStockCount(goodsStock2.getCount());
                goodsStock2.setCount(goodsStock2.getCount()+count.get(i));
                goodsStock2.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.updateByPrimaryKeySelective(goodsStock2);
            }
            else {
                stockFlowDetailToStore.setGoodsStockCount(0);
                goodsStock2 = new GoodsStock();
                goodsStock2.setaId(aIds.get(i));
                goodsStock2.setCount(count.get(i));
                goodsStock2.setStoreId(toStore);
                goodsStock2.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.insertSelective(goodsStock2);
            }
            stockFlowDetails.add(stockFlowDetailFromStore);
            stockFlowDetails.add(stockFlowDetailToStore);
        }
        //流水
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
        stockFlow.setFlowNumber("db"+sdf.format(new Date()));
        stockFlow.setCreateTime(DateUtil.getCurDate());
        stockFlowMapper.insertSelective(stockFlow);
        
        stockFlowDetails.stream().forEach(s -> {
                s.setFlowNumber(stockFlow.getFlowNumber());
                s.setCreateTime(DateUtil.getCurDate());
                s.setIsDeleted(0);
            });
        stockFlowDetailMapper.insertStockFlowDetails(stockFlowDetails);
        
        return new BaseDto(0, "出库成功");
    }

    /**
     * 出库操作
    * @author 高国藩
    * @date 2016年5月31日 下午8:56:10
    * @param stockFlow  stockFlow
    * @return           BaseDto
     */
    public BaseDto outStock(StockFlow stockFlow) {
        List<Integer> aIds = Arrays.asList(stockFlow.getaIds().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> count = Arrays.asList(stockFlow.getStockCount().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        Integer fromStore = stockFlow.getFromStore();
        List<StockFlowDetail> stockFlowDetails = new ArrayList<>();
        for (int i = 0; i < aIds.size(); i++) {
            GoodsStockKey key = new GoodsStockKey();
            key.setaId(aIds.get(i));
            key.setStoreId(fromStore);
            
            BigDecimal costPrice = accountGoodsMapper.selectByPrimaryKey(aIds.get(i)).getCostPrice();
            StockFlowDetail stockFlowDetail = new StockFlowDetail();
            stockFlowDetail.setaId(aIds.get(i));
            stockFlowDetail.setFlowCount(count.get(i));
            stockFlowDetail.setCostPrice(costPrice);
            stockFlowDetail.setFlowAmount(costPrice.multiply(new BigDecimal(count.get(i))));
            stockFlowDetail.setStockType(stockFlow.getStockType());
            stockFlowDetail.setFlowType(stockFlow.getFlowType());
            stockFlowDetail.setFromStore(stockFlow.getFromStore());
            stockFlowDetail.setEmployeeId(stockFlow.getLibraryObject());
            stockFlowDetail.setStoreAccount(stockFlow.getStoreAccount());
            
            GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(key);
            StoreSetting storeSetting = storeSettingMapper.selectByPrimaryKey(stockFlow.getFromStore());
            if (storeSetting.getIsGoodsMinus() == 0) {
            	if (goodsStock != null && goodsStock.getCount() >= count.get(i)){
                    stockFlowDetail.setGoodsStockCount(goodsStock.getCount());
                    goodsStock.setCount(goodsStock.getCount()-count.get(i));
                    goodsStock.setUpdateTime(DateUtil.getCurDate());
                    goodsStockMapper.updateByPrimaryKeySelective(goodsStock);
                }
                else {
                    throw new ServiceException(1, "商品库存不足，操作失败。");
                }
            }
            else {
            	if (goodsStock != null){
                    stockFlowDetail.setGoodsStockCount(goodsStock.getCount());
                    goodsStock.setCount(goodsStock.getCount()-count.get(i));
                    goodsStock.setUpdateTime(DateUtil.getCurDate());
                    goodsStockMapper.updateByPrimaryKeySelective(goodsStock);
                }
            }
            stockFlowDetails.add(stockFlowDetail);
        }
        //流水
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
        stockFlow.setFlowNumber("ck"+sdf.format(new Date()));
        stockFlow.setCreateTime(DateUtil.getCurDate());
        stockFlowMapper.insertSelective(stockFlow);
        stockFlowDetails.stream().forEach(s -> {
                s.setFlowNumber(stockFlow.getFlowNumber());
                s.setCreateTime(DateUtil.getCurDate());
                s.setIsDeleted(0);
            });
        stockFlowDetailMapper.insertStockFlowDetails(stockFlowDetails);
        return new BaseDto(0, "出库成功");
    }

    /**
     * 入库
    * @author 高国藩
    * @date 2016年5月31日 下午6:39:03
    * @param stockFlow stockFlow
    * @return          BaseDto
     */
    public BaseDto inStock(StockFlow stockFlow) {
        List<Integer> aIds = Arrays.asList(stockFlow.getaIds().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> count = Arrays.asList(stockFlow.getStockCount().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        Integer storeId = stockFlow.getToStore();
        List<StockFlowDetail> stockFlowDetails = new ArrayList<>();
        
        for (int i = 0; i < aIds.size(); i++) {
            GoodsStockKey key = new GoodsStockKey();
            key.setaId(aIds.get(i));
            key.setStoreId(storeId);
            GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(key);
            BigDecimal costPrice = accountGoodsMapper.selectByPrimaryKey(aIds.get(i)).getCostPrice();
            //库存流水明细
            StockFlowDetail stockFlowDetail = new StockFlowDetail();
            stockFlowDetail.setaId(aIds.get(i));
            stockFlowDetail.setFlowCount(count.get(i));
            stockFlowDetail.setCostPrice(costPrice);
            stockFlowDetail.setFlowAmount(costPrice.multiply(new BigDecimal(count.get(i))));
            stockFlowDetail.setStockType(stockFlow.getStockType());
            stockFlowDetail.setFlowType(stockFlow.getFlowType());
            stockFlowDetail.setToStore(stockFlow.getToStore());
            stockFlowDetail.setEmployeeId(stockFlow.getLibraryObject());
            stockFlowDetail.setStoreAccount(stockFlow.getStoreAccount());
            if (goodsStock!=null){
                //将原来的库存记录下来
                stockFlowDetail.setGoodsStockCount(goodsStock.getCount());
                
                goodsStock.setCount(goodsStock.getCount()+count.get(i));
                goodsStock.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.updateByPrimaryKeySelective(goodsStock);
            }
            else {
                stockFlowDetail.setGoodsStockCount(0);
                goodsStock = new GoodsStock();
                goodsStock.setaId(aIds.get(i));
                goodsStock.setCount(count.get(i));
                goodsStock.setStoreId(storeId);
                goodsStock.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.insertSelective(goodsStock);
            }
            stockFlowDetails.add(stockFlowDetail);
        }
        //流水
        SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
        stockFlow.setFlowNumber("rk"+sdf.format(new Date()));
        stockFlow.setCreateTime(DateUtil.getCurDate());
        stockFlowMapper.insertSelective(stockFlow);
        stockFlowDetails.stream().forEach(s -> {
                s.setFlowNumber(stockFlow.getFlowNumber());
                s.setCreateTime(DateUtil.getCurDate());
                s.setIsDeleted(0);
            });
        stockFlowDetailMapper.insertStockFlowDetails(stockFlowDetails);
        return new BaseDto(0, "入库成功");
    }

    /**
     * 根据门店查询进销存
    * @author 高国藩
    * @date 2016年5月31日 下午10:43:26
    * @param storeAccount storeAccount
    * @param storeId      storeId
    * @param request      request
    * @return             BaseDto
     */
    public BaseDto queryGoodsStock(String storeAccount, Integer storeId,  HttpServletRequest request) {
        StockFlow query = new StockFlow();
        query.setStoreAccount(storeAccount);
//        Page<StockFlow> page = new Page<>();
//        page.setPageNo(1);
//        page.setPageSize(9);
//        Map<String, Object> params = new HashMap<>();
//        
//        StockFlow query = new StockFlow();
//        query.setStoreAccount(storeAccount);
//        
//        params.put("storeAccount", storeAccount);
//        page.setParams(params);
//        List<StockFlow> stockFlows = stockFlowMapper.selectByProperties(page);
        List<StockFlow> stockFlows = stockFlowMapper.selectByProperties(query);
        
        /**入库管理*/
        List<StockFlow> inFlows = stockFlows.stream()
                .filter(flow -> flow.getStockType()==1&&flow.getToStore().equals(storeId))
                .collect(Collectors.toList());
        /**出库管理*/
        List<StockFlow> outFlows = stockFlows.stream()
                .filter(flow -> flow.getStockType()==2&&flow.getFromStore().equals(storeId))
                .collect(Collectors.toList());
        
        /**商品调拨管理*/
        List<StockFlow> moveFlows = stockFlows.stream().filter(flow -> flow.getStockType()==3).collect(Collectors.toList());
        Map<String , Object> map = new HashMap<>();
        map.put("inFlows", inFlows);
        map.put("outFlows", outFlows);
        map.put("moveFlows", moveFlows);
//        map.addObject("inFlows", JSONArray.fromObject(inFlows));
//        map.addObject("outFlows", JSONArray.fromObject(outFlows));
//        map.addObject("moveFlows", JSONArray.fromObject(moveFlows));
        
        List<EmployeeInfo> employeeInfos = employeeInfoMapper.selectEmployeeByStoreId(storeId);
        map.put("employeeInfos", employeeInfos);
        BaseDto baseDto = new BaseDto(0, map);
        return baseDto;
    }

}
