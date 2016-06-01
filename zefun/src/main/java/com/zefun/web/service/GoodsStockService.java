package com.zefun.web.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.View;
import com.zefun.common.consts.App.Session;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.AccountGoods;
import com.zefun.web.entity.GoodsStock;
import com.zefun.web.entity.GoodsStockKey;
import com.zefun.web.entity.StockFlow;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.AccountGoodsMapper;
import com.zefun.web.mapper.EnterpriseAccountMapper;
import com.zefun.web.mapper.EnterpriseInfoMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.GoodsStockMapper;
import com.zefun.web.mapper.StockFlowMapper;
import com.zefun.web.mapper.StoreInfoMapper;

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
    /** 企业管理  */
    @Autowired
    private EnterpriseAccountMapper enterpriseAccountMapper;
    /** 企业管理  */
    @Autowired
    private EnterpriseInfoMapper enterpriseInfoMapper;
    /** 门店管理  */
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    /** 商品库存  */
    @Autowired
    private GoodsStockMapper goodsStockMapper;
    /** 商品库存流水  */
    @Autowired
    private StockFlowMapper stockFlowMapper;
    /** 商品表  */
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    
    /**
     * 查看进销存页面
    * @author 高国藩
    * @date 2016年5月31日 下午4:07:49
    * @param storeAccount storeAccount
    * @param request      request
    * @return             ModelAndView
     */
    public ModelAndView viewGoodsStock(String storeAccount, HttpServletRequest request) {
        
        ModelAndView model = null; 
        List<StoreInfo> storeInfos = storeInfoMapper.selectByStoreAccount(storeAccount);
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
        
        model.addObject("inFlows", inFlows);
        model.addObject("outFlows", outFlows);
        model.addObject("moveFlows", moveFlows);
        
        AccountGoods accountGoods = new AccountGoods();
        accountGoods.setStoreAccount(storeAccount);
        List<AccountGoods> goods = accountGoodsMapper.selectByProperties(accountGoods);
        
        model.addObject("storeInfos", storeInfos);
        model.addObject("goods", goods);
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
    public BaseDto actionGoodsStock(String storeAccount, StockFlow stockFlow) {
        stockFlow.setStoreAccount(storeAccount);
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
        for (int i = 0; i < aIds.size(); i++) {
            //发货
            GoodsStockKey key = new GoodsStockKey();
            key.setaId(aIds.get(i));
            key.setStoreId(fromStore);
            GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(key);
            if (goodsStock!=null&&goodsStock.getCount()>=count.get(i)){
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
            if (goodsStock2!=null){
                goodsStock2.setCount(goodsStock2.getCount()+count.get(i));
                goodsStock2.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.updateByPrimaryKeySelective(goodsStock2);
            }
            else {
                goodsStock2 = new GoodsStock();
                goodsStock2.setaId(aIds.get(i));
                goodsStock2.setCount(count.get(i));
                goodsStock2.setStoreId(toStore);
                goodsStock2.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.insertSelective(goodsStock2);
            }
        }
        //流水
        stockFlow.setCreateTime(DateUtil.getCurDate());
        stockFlowMapper.insertSelective(stockFlow);
        return new BaseDto(0, "出库成功");
    }

    /**
     * 出库操作
    * @author 高国藩
    * @date 2016年5月31日 下午8:56:10
    * @param stockFlow  stockFlow
    * @return           BaseDto
     */
    private BaseDto outStock(StockFlow stockFlow) {
        List<Integer> aIds = Arrays.asList(stockFlow.getaIds().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> count = Arrays.asList(stockFlow.getStockCount().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        Integer toStore = stockFlow.getToStore();
        Integer fromStore = stockFlow.getFromStore();
        for (int i = 0; i < aIds.size(); i++) {
            GoodsStockKey key = new GoodsStockKey();
            key.setaId(aIds.get(i));
            key.setStoreId(fromStore);
            GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(key);
            if (goodsStock!=null&&goodsStock.getCount()>=count.get(i)){
                goodsStock.setCount(goodsStock.getCount()-count.get(i));
                goodsStock.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.updateByPrimaryKeySelective(goodsStock);
            }
            else {
                return new BaseDto(-1, "出库失败,商品没有库存");
            }
            GoodsStockKey key2 = new GoodsStockKey();
            key2.setaId(aIds.get(i));
            key2.setStoreId(toStore);
            GoodsStock goodsStock2 = goodsStockMapper.selectByPrimaryKey(key2);
            if (goodsStock2!=null){
                goodsStock2.setCount(goodsStock2.getCount()+count.get(i));
                goodsStock2.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.updateByPrimaryKeySelective(goodsStock2);
            }
            else {
                goodsStock2 = new GoodsStock();
                goodsStock2.setaId(aIds.get(i));
                goodsStock2.setCount(count.get(i));
                goodsStock2.setStoreId(toStore);
                goodsStock2.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.insertSelective(goodsStock2);
            }
        }
        //流水
        stockFlow.setCreateTime(DateUtil.getCurDate());
        stockFlowMapper.insertSelective(stockFlow);
        return new BaseDto(0, "出库成功");
    }

    /**
     * 入库
    * @author 高国藩
    * @date 2016年5月31日 下午6:39:03
    * @param stockFlow stockFlow
    * @return          BaseDto
     */
    private BaseDto inStock(StockFlow stockFlow) {
        List<Integer> aIds = Arrays.asList(stockFlow.getaIds().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> count = Arrays.asList(stockFlow.getStockCount().split(",")).stream().map(Integer::parseInt).collect(Collectors.toList());
        Integer storeId = stockFlow.getToStore();
        for (int i = 0; i < aIds.size(); i++) {
            GoodsStockKey key = new GoodsStockKey();
            key.setaId(aIds.get(i));
            key.setStoreId(storeId);
            GoodsStock goodsStock = goodsStockMapper.selectByPrimaryKey(key);
            if (goodsStock!=null){
                goodsStock.setCount(goodsStock.getCount()+count.get(i));
                goodsStock.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.updateByPrimaryKeySelective(goodsStock);
            }
            else {
                goodsStock = new GoodsStock();
                goodsStock.setaId(aIds.get(i));
                goodsStock.setCount(count.get(i));
                goodsStock.setStoreId(storeId);
                goodsStock.setUpdateTime(DateUtil.getCurDate());
                goodsStockMapper.insertSelective(goodsStock);
            }
        }
        //流水
        stockFlow.setCreateTime(DateUtil.getCurDate());
        stockFlowMapper.insertSelective(stockFlow);
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
        List<StockFlow> stockFlows = stockFlowMapper.selectByProperties(query);
        
        /**入库管理*/
        List<StockFlow> inFlows = stockFlows.stream()
                .filter(flow -> flow.getStockType()==1&&flow.getToStore().equals(storeId))
                .collect(Collectors.toList());
        /**出库管理*/
        List<StockFlow> outFlows = stockFlows.stream()
                .filter(flow -> flow.getStockType()==2&&flow.getFromStore().equals(storeId))
                .collect(Collectors.toList());
        Map<String , List<StockFlow>> map = new HashMap<>();
        map.put("inFlows", inFlows);
        map.put("outFlows", outFlows);
        BaseDto baseDto = new BaseDto(0, map);
        return baseDto;
    }

}
