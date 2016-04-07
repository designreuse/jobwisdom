package com.zefun.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.entity.UboxStoreGoods;
import com.zefun.web.service.UboxService;
import com.zefun.web.vo.UboxGoodsEditVo;

/**
 * 友宝商城控制器
* @author 张进军
* @date Jan 28, 2016 5:02:02 PM
 */
@Controller
public class UboxController extends BaseController {
    
    /** 友宝商城服务对象 */
    @Autowired
    private UboxService uboxService;
    
    
    /**
     * 机器配置操作
    * @author 张进军
    * @date Mar 4, 2016 10:37:56 PM
    * @param vmid   机器标识
    * @param request    请求对象
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.Ubox.ACTION_MACHINE_MANAGE)
    @ResponseBody
    public BaseDto machineManageAction(String vmid, HttpServletRequest request) {
        int storeId = getStoreId(request);
        return uboxService.machineManageAction(vmid, storeId);
    }
    
    
    /**
     * 访问商品列表页面
    * @author 张进军
    * @date Jan 28, 2016 5:14:12 PM
    * @param request    请求对象
    * @return   商品列表页面
     */
    @RequestMapping(value = Url.Ubox.VIEW_GOODS_LIST)
    public ModelAndView goodsListView(HttpServletRequest request){
        int storeId = getStoreId(request);
        return uboxService.goodsListView(storeId);
    }
    
    
    /**
     * 编辑商品信息页面
    * @author 张进军
    * @date Jan 28, 2016 5:07:02 PM
    * @param goodsId    商品标识
    * @return   编辑商品信息页面
     */
    @RequestMapping(value = Url.Ubox.VIEW_GOODS_EDIT)
    public ModelAndView editGoodsView(String goodsId){
        return uboxService.editGoodsView(goodsId);
    }
    
    
    /**
     * 编辑商品信息
    * @author 张进军
    * @date Jan 28, 2016 9:21:06 PM
    * @param editInfo   商品编辑信息
    * @param request        请求对象
    * @return   成功返回码为0，失败为其他返回码
     * @throws IOException 数据解码异常
     */
    @RequestMapping(value = Url.Ubox.ACTION_GOODS_EDIT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto editGoodsAction(@RequestBody UboxGoodsEditVo editInfo, HttpServletRequest request) throws IOException{
        int userId = getUserId(request);
        return uboxService.editGoodsAction(editInfo, userId);
    }
    
    
    /**
     * 门店商品价格、奖励配置
    * @author 张进军
    * @date Jan 30, 2016 5:42:31 PM
    * @param uboxStoreGoods       门店商品信息
    * @return   成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.Ubox.ACTION_GOODS_REWARDS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto goodsRewardsAction(UboxStoreGoods uboxStoreGoods){
        return uboxService.goodsRewardsAction(uboxStoreGoods);
    }
}
