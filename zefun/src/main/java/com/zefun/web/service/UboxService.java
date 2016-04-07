package com.zefun.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.View;
import com.zefun.common.utils.DateUtil;
import com.zefun.common.utils.UboxApiUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.ubox.UboxGoodsInfoDto;
import com.zefun.web.dto.ubox.UboxStoreGoodsDto;
import com.zefun.web.entity.CouponInfo;
import com.zefun.web.entity.UboxGoodsInfo;
import com.zefun.web.entity.UboxMachineInfo;
import com.zefun.web.entity.UboxStoreGoods;
import com.zefun.web.mapper.CouponInfoMapper;
import com.zefun.web.mapper.UboxGoodsInfoMapper;
import com.zefun.web.mapper.UboxMachineInfoMapper;
import com.zefun.web.mapper.UboxStoreGoodsMapper;
import com.zefun.web.vo.UboxGoodsEditVo;

import net.sf.json.JSONArray;

/**
 * 友宝商城服务类
* @author 张进军
* @date Jan 28, 2016 5:05:47 PM
 */
@Service
public class UboxService {
    
    /** 友宝商品信息操作对象 */
    @Autowired
    private UboxGoodsInfoMapper uboxGoodsInfoMapper;
    
    /** 友宝门店商品关联操作对象 */
    @Autowired
    private UboxStoreGoodsMapper uboxStoreGoodsMapper;
    
    /** 优惠券信息操作对象 */
    @Autowired
    private CouponInfoMapper couponInfoMapper;
    
    /** 友宝机器信息操作对象 */
    @Autowired
    private UboxMachineInfoMapper uboxMachineInfoMapper;
    
    
    /**
     * 机器配置操作
    * @author 张进军
    * @date Mar 4, 2016 10:37:56 PM
    * @param vmid   机器标识
    * @param storeId    门店标识
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto machineManageAction(String vmid, int storeId) {
        UboxMachineInfo uboxMachineInfo = uboxMachineInfoMapper.selectByPrimaryKey(vmid);
        
        BaseDto res = new BaseDto();
        res.setCode(App.System.API_RESULT_CODE_FOR_FAIL);
        if (uboxMachineInfo != null) {
            if (storeId == uboxMachineInfo.getStoreId()) {
                res.setMsg("您已配置过该机器");
            }
            else {
                res.setMsg("该机器已被其他门店配置");
            }
            return res;
        }
        uboxMachineInfo = UboxApiUtil.getMachineInfoByVmid(vmid);
        if (uboxMachineInfo == null) {
            res.setMsg("未找到结果，请检查您输入的机器编码");
        }
        else {
            uboxMachineInfo.setStoreId(storeId);
            uboxMachineInfoMapper.insert(uboxMachineInfo);
            res.setCode(App.System.API_RESULT_CODE_FOR_SUCCEES);
            res.setMsg("恭喜您，配置成功");
        }
        
        return res;
    }
    
    
    /**
     * 访问商品列表页面
    * @author 张进军
    * @date Jan 28, 2016 5:14:56 PM
    * @param storeId    门店标识
    * @return   商品列表页面
     */
    public ModelAndView goodsListView(int storeId) {
        ModelAndView mav = new ModelAndView(View.Ubox.GOODS_LIST);
        
        if (storeId == App.System.WECHAT_ZEFUN_STORE_ID) {
            List<UboxGoodsInfoDto> goodsList = uboxGoodsInfoMapper.selectAllGoodsList();
            mav.addObject("goodsList", goodsList);
            mav.addObject("isZefun", 1);
        }
        else {
            List<UboxStoreGoodsDto> goodsList = getGoodsListByStoreId(storeId);
            mav.addObject("goodsList", goodsList);
            
            if (goodsList != null && goodsList.size() > 0) {
                mav.addObject("hasGoods", 1);
                
                List<CouponInfo> couponList = couponInfoMapper.selectCouponListByStoreId(storeId);
                mav.addObject("couponList", couponList);
                
                UboxMachineInfo uboxMachineInfo = uboxMachineInfoMapper.selectMachineInfoByStoreId(storeId);
                mav.addObject("machineInfo", uboxMachineInfo);
            }
            
            mav.addObject("isZefun", 0);
        }
       
        return mav;
    }
    
    
    /**
     * 根据门店标识查询门店内售货机内的商品
    * @author 张进军
    * @date Mar 2, 2016 4:47:13 PM
    * @param storeId    门店标识
    * @return   商品列表
     */
    public List<UboxStoreGoodsDto> getGoodsListByStoreId(int storeId) {
        String vmid = uboxMachineInfoMapper.selectVmidByStoreId(storeId);
        if (StringUtils.isBlank(vmid)) {
            return null;
        }
        String curTime = DateUtil.getCurTime();
        List<UboxStoreGoodsDto> storeGoodsInfoList = new ArrayList<UboxStoreGoodsDto>();
        List<UboxGoodsInfo> ugList = UboxApiUtil.getGoodsListByVmid(vmid);
        Map<String, String> params = new HashMap<String, String>();
        params.put("storeId", String.valueOf(storeId));
        for (UboxGoodsInfo goods : ugList) {
            
            //检查商品是否入库
            UboxGoodsInfoDto goodsInfo = uboxGoodsInfoMapper.selectGoodsInfoByGoodsId(goods.getUboxId());
            if (goodsInfo == null) {
                goods.setGoodsName(goods.getUboxName());
                goods.setGoodsDesc(goods.getUboxDesc());
                goods.setCreateTime(curTime);
                goods.setUpdateTime(curTime);
                uboxGoodsInfoMapper.insert(goods);
                
                goodsInfo = new UboxGoodsInfoDto();
                goodsInfo.setUboxId(goods.getUboxId());
                goodsInfo.setUboxName(goods.getUboxName());
                goodsInfo.setGoodsName(goods.getGoodsName());
                goodsInfo.setUboxOriginalPrice(goods.getUboxOriginalPrice());
                goodsInfo.setUboxSalePrice(goods.getUboxSalePrice());
                goodsInfo.setUboxDesc(goods.getUboxDesc());
                goodsInfo.setUboxExpireTime(goods.getUboxExpireTime());
                goodsInfo.setUboxStock(goods.getUboxStock());
                goodsInfo.setUboxPicture(goods.getUboxPicture());
                
                goodsInfo.setGoodsName(goods.getUboxName());
                goodsInfo.setGoodsDesc(goods.getGoodsDesc());
                goodsInfo.setGoodsSales(0);
            }
            goodsInfo.setUboxStock(goods.getUboxStock());
            
            //检查门店是否有同步商品
            params.put("uboxId", goods.getUboxId());
            UboxStoreGoodsDto storeGoodsInfo = uboxStoreGoodsMapper.selectGoodsInfoByStoreIdAndUboxId(params);
            if (storeGoodsInfo == null) {
                UboxStoreGoods uboxStoreGoods = new UboxStoreGoods();
                uboxStoreGoods.setStoreId(storeId);
                uboxStoreGoods.setUboxGoodsId(goods.getUboxId());
                uboxStoreGoods.setStoreGoodsPrice(goods.getUboxSalePrice());
                uboxStoreGoodsMapper.insert(uboxStoreGoods);
                
                storeGoodsInfo = new UboxStoreGoodsDto();
                storeGoodsInfo.setStoreGoodsId(uboxStoreGoods.getStoreGoodsId());
                storeGoodsInfo.setStoreId(storeId);
                storeGoodsInfo.setStoreGoodsIntegral(0);
                storeGoodsInfo.setRewardsCouponId(0);
                storeGoodsInfo.setRewardsGiftAmount(0);
                storeGoodsInfo.setStoreGoodsPrice(goodsInfo.getUboxSalePrice());
                storeGoodsInfo.setStoreGoodsSales(0);
                storeGoodsInfo.setStoreGoodsStock(goods.getUboxStock());
                
                storeGoodsInfo.setGoodsInfo(goodsInfo);
            }
            storeGoodsInfoList.add(storeGoodsInfo);
        }
        
        return storeGoodsInfoList;
    }
    
    
    /**
     * 编辑商品信息页面
    * @author 张进军
    * @date Jan 28, 2016 5:07:02 PM
    * @param goodsId    商品标识
    * @return   编辑商品信息页面
     */
    public ModelAndView editGoodsView(String goodsId){
        ModelAndView mav = new ModelAndView(View.Ubox.GOODS_EDIT);
        UboxGoodsInfoDto goodsInfo = uboxGoodsInfoMapper.selectGoodsInfoByGoodsId(goodsId);
        mav.addObject("goodsInfo", goodsInfo);
        return mav;
    }
    
    
    /**
     * 编辑商品信息
    * @author 张进军
    * @date Jan 28, 2016 9:21:06 PM
    * @param editInfo   商品编辑信息
    * @param userId         操作人标识
    * @return   成功返回码为0，失败为其他返回码
     * @throws IOException 数据解码异常
     */
    public BaseDto editGoodsAction(UboxGoodsEditVo editInfo, int userId) throws IOException{
        UboxGoodsInfo uboxGoodsInfo = new UboxGoodsInfo();
        uboxGoodsInfo.setUboxId(editInfo.getGoodsId());
        uboxGoodsInfo.setUboxPicture(editInfo.getGoodsPicture());
        uboxGoodsInfo.setGoodsPictureArray(StringUtils.join(editInfo.getImgs(), ","));
        
        String sep = "\001";
        List<Map<String, Object>> contentList = new ArrayList<Map<String, Object>>();
        for (String c : editInfo.getContents()) {
            String[] cs = c.split(sep);
            Map<String, Object> cm = new HashMap<String, Object>();
            cm.put("type", cs[1]);
            cm.put("text", cs[0]);
            contentList.add(cm);
        }
        uboxGoodsInfo.setGoodsDesc(JSONArray.fromObject(contentList).toString());
        
        uboxGoodsInfo.setLastOperatorId(userId);
        uboxGoodsInfoMapper.updateByPrimaryKey(uboxGoodsInfo);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    
    /**
     * 门店商品价格、奖励配置
    * @author 张进军
    * @date Jan 30, 2016 5:42:31 PM
    * @param uboxStoreGoods       门店商品信息
    * @return   成功返回码为0，失败为其他返回码
     */
    public BaseDto goodsRewardsAction(UboxStoreGoods uboxStoreGoods){
        uboxStoreGoodsMapper.updateByPrimaryKey(uboxStoreGoods);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
}
