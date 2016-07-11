package com.zefun.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.common.consts.App.Session;
import com.zefun.common.utils.DateUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.EmployeeBaseDto;
import com.zefun.web.dto.GoodsBrandDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.dto.OrderDetailDto;
import com.zefun.web.dto.ShipmentRecordDto;
import com.zefun.web.dto.SupplierInfoDto;
import com.zefun.web.entity.AccountGoods;
import com.zefun.web.entity.ComboGoods;
import com.zefun.web.entity.GoodsBrand;
import com.zefun.web.entity.GoodsCategory;
import com.zefun.web.entity.GoodsDiscount;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.OrderDetail;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.ShipmentRecord;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.entity.SupplierInfo;
import com.zefun.web.mapper.ComboGoodsMapper;
import com.zefun.web.mapper.GoodsInfoMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.mapper.SupplierInfoMapper;
import com.zefun.web.service.GoodsInfoService;
import com.zefun.web.service.MemberLevelService;

/**
 * 商品
* @author 洪秋霞
* @date 2015年8月7日 下午5:03:49 
*
 */
@Controller
public class GoodsInfoController extends BaseController {
    /**商品*/
    @Autowired private GoodsInfoService goodsInfoService;
    /**会员等级*/
    @Autowired private MemberLevelService memberLevelService;
    /**套餐商品关联*/
    @Autowired private ComboGoodsMapper comboGoodsMapper;
    /**供应商管理处理*/
    @Autowired private SupplierInfoMapper supplierInfoMapper;
    /**企业商品管理*/
    @Autowired private GoodsInfoMapper goodsInfoMapper;
    /**企业门店管理*/
    @Autowired private StoreInfoMapper storeInfoMapper;
   /* 
    *//** 日志 *//*
    private Logger logger = Logger.getLogger(SessionContextListener.class);*/

    /**
     * 进入商品列表
    * @author 洪秋霞
    * @date 2015年8月10日 上午10:12:29
    * @param request request
    * @param response response
    * @param categoryId categoryId
    * @param model 视图模型
    * @return ModelAndView
     */
    @RequestMapping(value = Url.GoodsInfo.GOODSINFO_LIST)
    public ModelAndView toGoodsInfoPage(HttpServletRequest request, HttpServletResponse response, Integer categoryId, ModelAndView model) {
        Integer storeId = getStoreId(request);
        
        /*List<DeptGoodsBaseDto> deptGoodsBaseDto = goodsInfoService.getDeptGoodsByStoreId(storeId);
        model.addObject("deptGoodsBaseDto", deptGoodsBaseDto);
        model.addObject("js_deptGoodsBaseDto", JSONArray.fromObject(deptGoodsBaseDto));
    
        *//**自己的品牌库*//*
        List<GoodsBrand> brands =  goodsBrandMapper.selectByStoreId(storeId);
        model.addObject("brands", brands);
        *//**智放品牌列表*//*
        List<CodeLibrary> goodsBrands = goodsInfoService.selectGoodsBrandList();
        model.addObject("goodsBrandList", goodsBrands);

        *//** 会员等级列表 *//*
        List<MemberLevel> memberLevelList = memberLevelService.queryByStoreId(storeId);
        model.addObject("memberLevels", memberLevelList);
        model.addObject("memberLevelList", JSONArray.fromObject(memberLevelList));
        List<CodeLibraryDto> images = codeLibraryMapper.selectProjectImage(); //selectGoodsImage();
        model.addObject("images", images);*/
        
        List<DeptGoodsBaseDto> deptGoodsBaseDto = goodsInfoService.getDeptGoodsByStoreId(storeId);
        model.addObject("deptGoodsBaseDto", deptGoodsBaseDto);
        model.addObject("deptGoodsBaseDtoJs", JSONArray.fromObject(deptGoodsBaseDto));
        
        List<GoodsInfoDto> goodsInfos = goodsInfoService.selectGoodsInfosByStoreId(storeId);
        if (categoryId!=null){
            goodsInfos = goodsInfos.stream().filter(g -> g.getCategoryId().equals(categoryId)).collect(Collectors.toList());
        }
        
        Long hasFinish = goodsInfos.stream().filter(g -> g.getGoodsPrice()!=null).count();
        model.addObject("hasFinish", hasFinish);
        
        model.addObject("goodsInfos", goodsInfos);
        model.setViewName(View.GoodsInfo.GOODSINFO);
        return model;
    }
    
    /**
     * 进入商品的新增页面-该页面权限,新增只允许企业
    * @author 高国藩
    * @date 2016年5月18日 下午5:43:27
    * @param request    请求
    * @param response   结果流
    * @param goodsId    商品ID
    * @return           跳转页面 
     */
    @RequestMapping(value = Url.GoodsInfo.GOODSINFO_SETTING)
    public ModelAndView toGoodsInfoSeting(HttpServletRequest request, HttpServletResponse response, Integer goodsId) {
        String storeAccount = getStoreAccount(request);
        ModelAndView model = null;
        List<StoreInfo> storeInfos = storeInfoMapper.selectByStoreAccount(storeAccount);
        
        Object storeId = request.getSession().getAttribute(Session.STORE_ID);
        if (storeId!=null){
            model = new ModelAndView(View.GoodsInfo.GOOD_SETTING_STORE);
        }
        else {
            storeId = storeInfos.get(0).getStoreId();
            model = new ModelAndView(View.GoodsInfo.GOOD_SETTING);
        }
        final Integer queryStoreId = Integer.parseInt(storeId.toString());
        
        model.addObject("storeInfos", storeInfos);
        
        SupplierInfo supplierInfo = new SupplierInfo();
        supplierInfo.setStoreAccount(storeAccount);
        
        /** 供应商品牌库*/
        List<SupplierInfoDto> supplierInfoDtos = supplierInfoMapper.selectInfoByAccount(supplierInfo);
        List<GoodsBrand> brands = supplierInfoDtos.
                stream().
                filter(info -> info.getBrands()!=null).
                flatMap(info -> info.getBrands().stream()).
                collect(Collectors.toList());
        model.addObject("brands", brands);
        
        model.addObject("supplierInfoDtos", supplierInfoDtos);
        model.addObject("supplierInfoDtosJs", JSONArray.fromObject(supplierInfoDtos));
        
        Page<GoodsInfoDto> page = new Page<>();
        page.setPageNo(1);
        page.setPageSize(15);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", queryStoreId);
        page.setParams(params);
        List<GoodsInfoDto> goodsInfoDtos = goodsInfoMapper.selectAllGoodsInfoByStoreIdByPage(page);
        page.setResults(goodsInfoDtos);
        model.addObject("page", page);
//        List<GoodsInfoDto> goodsInfoDtos = goodsInfoMapper.selectAllGoodsInfoByStoreId(queryStoreId);
//        model.addObject("goodsInfoDtos", goodsInfoDtos);
//        AccountGoods accountGoods = new AccountGoods();
//        accountGoods.setStoreAccount(storeAccount);
//        List<AccountGoods> list = accountGoodsMapper.selectByProperties(accountGoods);
//        model.addObject("accountGoods", list);
        return model;
    }
    
    /**
     * 查询商品库的分页信息
    * @author 高国藩
    * @date 2016年6月18日 下午12:15:15
    * @param request    request
    * @param response   response
    * @param pageNo     pageNo
    * @param pageSize   pageSize
    * @param storeId    storeId
    * @return           BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.GOODSINFO_SETTING_PAGE)
    @ResponseBody
    public BaseDto toGoodsInfoSeting(HttpServletRequest request, HttpServletResponse response, Integer pageNo, Integer pageSize, Integer storeId) {
        if (storeId == null){
            storeId = getStoreId(request);
        }
        Page<GoodsInfoDto> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<GoodsInfoDto> goodsInfoDtos = goodsInfoMapper.selectAllGoodsInfoByStoreIdByPage(page);
        page.setResults(goodsInfoDtos);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    
    /**
     * 新建商品的基本数据
    * @author 高国藩
    * @date 2016年5月26日 下午4:25:37
    * @param request request
    * @param response response
    * @param data data
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.GOODS_SAVE_BASE, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto saveGoodsInfoBase(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject data) {
        String storeAccount = getStoreAccount(request);
        return goodsInfoService.saveGoodsInfoBase(storeAccount, data);
    }
    
    /**
     * 企业查询分店下商品库存
    * @author 高国藩
    * @date 2016年6月1日 下午1:51:14
    * @param request   request
    * @param response  response
    * @param storeId   storeId
    * @return          BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.GOODS_QUERY_ACCOUNT, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto accountQueryGoodsInfo(HttpServletRequest request, HttpServletResponse response, @PathVariable Integer storeId) {
        String storeAccount = getStoreAccount(request);
        return goodsInfoService.accountQueryGoodsInfo(storeAccount, storeId);
    }
    
    
    /**
     * 商品上架操作页面
    * @author 高国藩
    * @date 2016年5月27日 上午11:37:26
    * @param request     request
    * @param response    response
    * @param model       model
    * @param goodsId     goodsId
    * @return            model
     */
    @RequestMapping(value = Url.GoodsInfo.GOODSINFO_SETTING_NEW)
    public ModelAndView goodsInfoSeting(HttpServletRequest request, HttpServletResponse response, ModelAndView model, Integer goodsId) {
        Integer storeId = getStoreId(request);
        String storeAccount = getStoreAccount(request);
        List<DeptGoodsBaseDto> deptGoodsBaseDto = goodsInfoService.getDeptGoodsByStoreId(storeId);
        model.addObject("deptGoodsBaseDto", deptGoodsBaseDto);
        model.addObject("js_deptGoodsBaseDto", JSONArray.fromObject(deptGoodsBaseDto));
    
        /** 会员等级列表 */
        List<MemberLevelDto> memberLevelList = memberLevelService.queryByAllStoreId(storeId);
        model.addObject("memberLevels", memberLevelList);
        model.addObject("memberLevelList", JSONArray.fromObject(memberLevelList));
        List<AccountGoods> goodsInfos = goodsInfoService.selectAccountGoodsInfo(storeAccount); 
        //goodsInfoService.selectGoodsInfosByStoreIdAndNotPay(storeId);
        model.addObject("goodsInfos", goodsInfos);
        
        if (goodsId!=null){
            GoodsInfo query = new GoodsInfo();
            query.setaId(goodsId);
            GoodsInfoDto goodsInfoDto = goodsInfoMapper.selectGoodsAllByPrimaryKey(goodsId);
            if (goodsInfoDto.getIsSellProduct().equals(1)){
                model.addObject("aId", goodsInfoDto.getaId());
            }
        }
        
        model.setViewName(View.GoodsInfo.SETTING_GOODS);
        return model;
    }
    
    
    /**
     * 保存商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:36:45
    * @param request request
    * @param response response
    * @param goodsCategory 商品类别
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_CATEGORY)
    @ResponseBody
    public BaseDto saveGoodsCategory(HttpServletRequest request, HttpServletResponse response, GoodsCategory goodsCategory) {
        try {
            goodsCategory.setStoreId(getStoreId(request));
            goodsCategory.setCreateTime(DateUtil.getCurTime());
            goodsCategory.setIsDeleted(0);
            Integer categoryId = goodsInfoService.saveGoodsCategory(goodsCategory);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, categoryId);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }
    
    /**
     * 保存商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:36:45
    * @param request request
    * @param response response
    * @param deptId 部门ID
    * @param categoryName 系列名称
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_CATEGORY_LIST)
    @ResponseBody
    public BaseDto saveGoodsCategoryList(HttpServletRequest request, HttpServletResponse response, Integer deptId, String[] categoryName) {
        List<GoodsCategory> ls = new ArrayList<GoodsCategory>();
        for (int i = 0; i < categoryName.length; i++) {
            GoodsCategory goodsCategory = new GoodsCategory();
            goodsCategory.setStoreId(getStoreId(request));
            goodsCategory.setCategoryName(categoryName[i]);
            goodsCategory.setCreateTime(DateUtil.getCurTime());
            goodsCategory.setDeptId(deptId);
            goodsCategory.setIsDeleted(0);
            Integer categoryId = goodsInfoService.saveGoodsCategory(goodsCategory);
            goodsCategory.setCategoryId(categoryId);
            ls.add(goodsCategory);
        }
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, ls);
    }

    /**
     * 编辑商品类别
    * @author 洪秋霞
    * @date 2015年9月16日 下午3:37:48
    * @param request request
    * @param response response
    * @param goodsCategory 商品类别
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.EDIT_GOODS_CATEGORY)
    @ResponseBody
    public BaseDto editGoodsCategory(HttpServletRequest request, HttpServletResponse response, GoodsCategory goodsCategory) {
        try {
            goodsCategory.setStoreId(getStoreId(request));
            goodsCategory.setCreateTime(DateUtil.getCurTime());
            goodsInfoService.editGoodsCategory(goodsCategory);
            goodsInfoService.deleteGoodsRedis(goodsCategory.getDeptId().toString());
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }


    /**
     * 保存品牌
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:21:53
    * @param request request
    * @param response response
    * @param goodsBrand 品牌
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_BRAND)
    @ResponseBody
    public BaseDto saveGoodsBrand(HttpServletRequest request, HttpServletResponse response, GoodsBrand goodsBrand) {
        try {
            goodsBrand.setStoreId(getStoreId(request));
            goodsBrand.setCreateTime(DateUtil.getCurTime());
            goodsInfoService.saveGoodsBrand(goodsBrand);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 编辑品牌
    * @author 洪秋霞
    * @date 2015年9月1日 下午2:55:42
    * @param request request
    * @param response response
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.EDIT_GOODS_BRAND)
    @ResponseBody
    public BaseDto editGoodsBrand(HttpServletRequest request, HttpServletResponse response) {
        try {
            String brandId = request.getParameter("brandId");
            String brandName = request.getParameter("brandName");
            GoodsBrand goodsBrand = new GoodsBrand();
            goodsBrand.setBrandId(Integer.parseInt(brandId));
            goodsBrand.setBrandName(brandName);
            goodsInfoService.editGoodsBrand(goodsBrand);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }


    /**
     * 保存商品
    * @author 高国藩
    * @date 2015年8月10日 上午10:13:22
    * @param request request
    * @param response response
    * @param jsonObject jsonObject 
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_INFO)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public BaseDto saveGoodsInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jsonObject) {
        GoodsInfo goodsInfo = (GoodsInfo) JSONObject.toBean(jsonObject.getJSONObject("goodsInfo"), GoodsInfo.class);
        String[] levelId = (String[]) JSONArray.toCollection(jsonObject.getJSONArray("levelId")).toArray(new String[]{});
        String[] discountAmount = (String[]) JSONArray.toCollection(jsonObject.getJSONArray("discountAmount")).toArray(new String[]{});
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        goodsInfo.setStoreId(getStoreId(request));
        try {
            if (goodsInfo.getGoodsId() == null) {
                // 新增
                Integer goodsId = goodsInfoService.saveGoodsInfo(goodsInfo, levelId, discountAmount);
                baseDto.setMsg(goodsId);
            }
            else {
                // 编辑
                goodsInfoService.editGoodsInfo(goodsInfo, levelId, discountAmount);
                baseDto.setMsg(goodsInfo.getGoodsId());
            }
            return baseDto;
        }
        
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
        finally {
            goodsInfoService.deleteGoodsRedis(goodsInfo.getDeptId().toString());
        }
    }

    /**
     * 保存商品
    * @author 洪秋霞
    * @date 2015年8月10日 上午10:13:22
    * @param request request
    * @param response response
    * @param deptId 门店信息
    * @param categoryId 系列ID
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_GOODS_INFO_LIST, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto saveGoodsInfoList(HttpServletRequest request, HttpServletResponse response, Integer deptId, Integer categoryId) {
        BaseDto baseDto = new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        String[] goodsInfoName = request.getParameterValues("goodsInfoName");
        List<GoodsInfo> ls = new ArrayList<GoodsInfo>();
        for (int i = 0; i < goodsInfoName.length; i++) {
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setStoreId(getStoreId(request));
            goodsInfo.setDeptId(deptId);
            goodsInfo.setCategoryId(categoryId);
            Integer goodsId =  goodsInfoService.saveGoodsInfos(goodsInfo);
            goodsInfo.setGoodsId(goodsId);
            ls.add(goodsInfo);
        }
        goodsInfoService.deleteGoodsRedis(deptId.toString());
        baseDto.setMsg(ls);
        return baseDto;
    }
    
    /**
     * 根据商品id查询
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:24:37
    * @param request request
    * @param response response
    * @param goodsId 商品id
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.QUERY_GOODSINFO_BYID)
    @ResponseBody
    public BaseDto queryGoodsInfoById(HttpServletRequest request, HttpServletResponse response, Integer goodsId) {
        Integer storeId = getStoreId(request);
        BaseDto baseDto = new BaseDto();
        try {
            GoodsInfoDto goodsInfo = goodsInfoService.queryGoodsInfoById(goodsId, storeId, baseDto);
            if (goodsInfo == null){
                baseDto.setCode(3);
                return baseDto;
            }
            else {
                GoodsDiscount goodsDiscount = new GoodsDiscount();
                goodsDiscount.setGoodsId(goodsInfo.getGoodsId());
                List<GoodsDiscount> goodsDiscountList = goodsInfoService.queryGoodsDiscountList(goodsDiscount);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("goodsInfo", goodsInfo);
                map.put("goodsDiscountList", goodsDiscountList);
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 删除商品信息
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:33:21
    * @param request request
    * @param response response
    * @param goodsId 商品id
    * @param deptId 部门ID
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.DELETE_GOODSINFO)
    @ResponseBody
    public BaseDto deleteGoodsInfo(HttpServletRequest request, HttpServletResponse response, Integer goodsId, Integer deptId) {
        ComboGoods comboGoods = new ComboGoods();
        comboGoods.setGoodsId(goodsId);
        List<ComboGoods> goods = comboGoodsMapper.selectByPrimaryKey(comboGoods);
        if (goods!=null&&goods.size()>0){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "该商品在套餐中引用,不可删除"); 
        }
        goodsInfoService.deleteGoodsInfo(goodsId);
        goodsInfoService.deleteGoodsRedis(deptId.toString());
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
    }
    
    /**
     * 供应商管理
    * @author 高国藩
    * @date 2016年5月28日 下午2:24:15
    * @param request    request
    * @param response   response
    * @return           ModelAndView
     */
    @RequestMapping(value = Url.GoodsInfo.VIEW_SUPPLIER)
    public ModelAndView viewSupplier(HttpServletRequest request, HttpServletResponse response) {
        String storeAccount = getStoreAccount(request);
        return goodsInfoService.viewSupplier(storeAccount);
    }


    /**
     * 查询商品30天销售量
    * @author 洪秋霞
    * @date 2015年9月18日 上午11:05:35
    * @param request request
    * @param response response
    * @param goodsId 商品id
    * @return BaseDto
     */
    @RequestMapping(value = Url.GoodsInfo.QUERY_BYGOODSSALE)
    @ResponseBody
    public BaseDto queryByGoodsSale(HttpServletRequest request, HttpServletResponse response, Integer goodsId) {
        try {
            // 30天销售量
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderType(3);
            orderDetail.setProjectId(goodsId);
            List<OrderDetailDto> orderDetailDtoList = goodsInfoService.queryByGoodsSale(orderDetail);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, orderDetailDtoList);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }


    /**
     * 根据部门查询商品类别
    * @author 高国藩
    * @date 2015年10月15日 下午1:53:27
    * @param deptId 部门Id标识 
    * @param request 请求
    * @return 查询结果
     */
    @RequestMapping(value = Url.GoodsInfo.SELECT_CATEGORY_BY_DEPT, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto listSelectCategoryByDept(Integer deptId, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return goodsInfoService.listSelectCategoryByDept(deptId, storeId);
    }
    
    /**
     * 出货管理页面
    * @author 高国藩
    * @date 2015年11月14日 上午11:11:25
    * @param request 请求
    * @return 跳转页面
     */
    @RequestMapping(value = Url.GoodsInfo.VIEW_SHIPMENT_RECORD)
    public ModelAndView viewShipmentRecord(HttpServletRequest request){
        return goodsInfoService.viewShipmentRecord(getStoreId(request));
    }
    
    /**
     * 保存出货记录
    * @author 高国藩
    * @date 2015年11月14日 上午11:12:07
    * @param request 请求
    * @param shipmentRecord 实体
    * @return 数据
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_SHIPMENT_RECORD, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto saveShipmentRecord(HttpServletRequest request, ShipmentRecord shipmentRecord){
        Integer userId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        shipmentRecord.setOperatorId(userId);
        shipmentRecord.setStoreId(getStoreId(request));
        shipmentRecord.setShipmentTime(DateUtil.getCurDate());
        EmployeeBaseDto employeeInfo = (EmployeeBaseDto) request.getSession().getAttribute(App.Session.USER_INFO);
        shipmentRecord.setOperatorName(employeeInfo.getName());
        return goodsInfoService.saveShipmentRecord(shipmentRecord);
    }
    
    /**
     * 分页查询出货记录
    * @author 高国藩
    * @date 2015年11月14日 上午11:12:28
    * @param request 请求
    * @param pageNo 源码
    * @param pageSize 大小
    * @param shipmentRecord 实体
    * @return 数据
     */
    @RequestMapping(value = Url.GoodsInfo.SERCH_SHIPMENT_RECORD, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto serchShipmentRecord(HttpServletRequest request, int pageNo, int pageSize, ShipmentRecordDto shipmentRecord){
        shipmentRecord.setStoreId(getStoreId(request));
        return goodsInfoService.serchShipmentRecord(shipmentRecord, pageNo, pageSize);
    }
    
    /**
     * 品牌记录页面
    * @author 高国藩
    * @date 2015年11月14日 上午11:11:25
    * @param request 请求
    * @return 跳转页面
     */
    @RequestMapping(value = Url.GoodsInfo.VIEW_BRAND)
    public ModelAndView viewBrand(HttpServletRequest request){
        return goodsInfoService.viewBrand(getStoreId(request));
    }
    
    /**
     * 新增品牌
    * @author 高国藩
    * @date 2015年11月14日 上午11:11:25
    * @param request    请求
    * @param goodsBrand 实体
    * @return           跳转页面
     */
    @RequestMapping(value = Url.GoodsInfo.SAVE_BRAND)
    @ResponseBody
    public BaseDto saveBrand(HttpServletRequest request, GoodsBrand goodsBrand){
        if (goodsBrand.getBrandId()!=null){
            goodsBrand.setLastOperatorId((Integer)request.getSession().getAttribute(App.Session.USER_ID));
            return goodsInfoService.editGoodsBrand(goodsBrand);
        }
        else {
            // 新增
            goodsBrand.setCreateTime(DateUtil.getCurDate());
            goodsBrand.setLastOperatorId((Integer)request.getSession().getAttribute(App.Session.USER_ID));
            goodsBrand.setIsDeleted(0);
            return goodsInfoService.saveBrand(goodsBrand);
        }
    }
    
    /**
     * 分页查询品牌
    * @author 高国藩
    * @date 2015年11月14日 上午11:12:28
    * @param request 请求
    * @param pageNo 源码
    * @param pageSize 大小
    * @param goodsBrandDto 实体
    * @return 数据
     */
    @RequestMapping(value = Url.GoodsInfo.SERCH_BRAND, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto serchBrand(HttpServletRequest request, int pageNo, int pageSize, GoodsBrandDto goodsBrandDto){
        goodsBrandDto.setStoreId(getStoreId(request));
        return goodsInfoService.serchBrand(goodsBrandDto, pageNo, pageSize);
    }
    
    /**
     * 删除品牌
    * @author 骆峰
    * @date 2016年7月11日 下午2:13:53
    * @param request request
    * @param brandId brandId
    * @return BaseDto
     */
    
    @RequestMapping(value = Url.GoodsInfo.GOODSINFO_DELETE_PAGE, method=RequestMethod.POST)
    @ResponseBody
    public BaseDto serchBrand(HttpServletRequest request, Integer brandId){
        return goodsInfoService.deleteBrand(brandId);
    }
   
    
}
