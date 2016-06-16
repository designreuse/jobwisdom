package com.zefun.web.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.DeptGoodsBaseDto;
import com.zefun.web.dto.GoodsInfoDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.dto.ProjectCommissionDto;
import com.zefun.web.dto.ProjectInfoDto;
import com.zefun.web.entity.ComboGoods;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.ComboMemberLevel;
import com.zefun.web.entity.ComboProject;
import com.zefun.web.entity.DeptInfo;
import com.zefun.web.entity.GoodsInfo;
import com.zefun.web.entity.MemberComboRecord;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.service.ComboInfoService;
import com.zefun.web.service.GoodsInfoService;
import com.zefun.web.service.MemberLevelService;
import com.zefun.web.service.ProjectService;

/**
 * 套餐
* @author 洪秋霞
* @date 2015年8月6日 上午10:07:42 
*
 */
@Controller
public class ComboInfoController extends BaseController {
    /**套餐*/
    @Autowired 
    ComboInfoService comboInfoService;
    /**项目*/
    @Autowired 
    ProjectService projectService;
    /**商品*/
    @Autowired 
    GoodsInfoService goodsInfoService;
    /**会员等级*/
    @Autowired 
    private MemberLevelService memberLevelService;
    /**会员套餐关联*/
    @Autowired
    private MemberComboRecordMapper memberComboRecordMapper;
    /**套餐*/
    @Autowired
    private ComboInfoMapper comboInfoMapper;

    /**
     * 进入套餐页面
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:13:25
    * @param request request
    * @param response response
    * @param deptId comboId
    * @param model 视图模型
    * @return ModelAndView
     */
    @RequestMapping(value = Url.ComboInfo.COMBOINFO_LIST)
    public ModelAndView toComboInfo(HttpServletRequest request, HttpServletResponse response, Integer deptId, ModelAndView model) {
        try {
            Integer storeId = getStoreId(request);
            ComboInfo comboInfo = new ComboInfo();
            comboInfo.setStoreId(storeId);
            List<ComboInfo> comboInfos = comboInfoMapper.selectByProperty(comboInfo);

//            // 部门，套餐列表
//            List<DeptInfoDto> deptInfoCmboInfoList = comboInfoService.getDetpInfoByCombo(storeId);
//            model.addObject("deptInfoCmboInfoList", deptInfoCmboInfoList);
//
//            // 部门列表
//            List<DeptInfo> deptInfoList = projectService.queryDeptInfoList(storeId);
//            model.addObject("deptInfoList", deptInfoList);
//            model.addObject("js_deptInfoList", JSONArray.fromObject(deptInfoList));
//
//            // 项目列表
//            ProjectInfoDto projectInfoDto = new ProjectInfoDto();
//            projectInfoDto.setStoreId(storeId);
//            projectInfoDto.setIsDeleted(0);
//            List<ProjectInfoDto> projectInfoDtoList = projectService.queryProjectInfoList(projectInfoDto);
//            model.addObject("projectInfoDtoList", projectInfoDtoList);
//            model.addObject("projectInfoList", JSONArray.fromObject(projectInfoDtoList));
//            
//            //商品列表
//            GoodsInfo goodsInfo = new GoodsInfo();
//            goodsInfo.setStoreId(storeId);
//            goodsInfo.setIsDeleted(0);
//            List<GoodsInfo> goodsinfos = goodsInfoService.selectGoodsInfos(goodsInfo);
//            model.addObject("goodsinfos", goodsinfos);
//            model.addObject("goodsinfos_js", JSONArray.fromObject(goodsinfos));
//            // 会员等级列表
//            List<MemberLevelDto> memberLevelList = memberLevelService.queryByAllStoreId(storeId);
//            model.addObject("memberLevels", memberLevelList);
//            
//            List<CodeLibraryDto> images = codeLibraryMapper.selectProjectImage();
//            model.addObject("images", images);
            
//            List<DeptInfo> deptInfoList = projectService.queryDeptInfoList(storeId);
//            model.addObject("deptInfoList", deptInfoList);
//            model.addObject("deptInfoListJs", JSONArray.fromObject(deptInfoList));
            
            List<DeptGoodsBaseDto> deptGoodsBaseDto = goodsInfoService.getDeptGoodsByStoreId(storeId);
            model.addObject("deptGoodsBaseDto", deptGoodsBaseDto);
            model.addObject("deptGoodsBaseDtoJs", JSONArray.fromObject(deptGoodsBaseDto));
            
            if (deptId!=null){
                comboInfos = comboInfos.stream().filter(c -> c.getDeptId().equals(deptId)).collect(Collectors.toList());
            }
            
            Long hasFinish = comboInfos.stream().filter(c -> c.getComboSalePrice()!=null).count();
            model.addObject("hasFinish", hasFinish);
            
            model.addObject("comboInfos", comboInfos);
            model.setViewName(View.ComboInfo.COMBOINFO);
            return model;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * 新增套餐页面
    * @author 高国藩
    * @date 2016年6月6日 下午7:51:42
    * @param request   request
    * @param response  response
    * @param comboId   comboId
    * @param model     model
    * @return          model
     */
    @RequestMapping(value = Url.ComboInfo.COMBOINFO_SETTING)
    public ModelAndView viewComboInfoSetting(HttpServletRequest request, HttpServletResponse response, Integer comboId, ModelAndView model) {
        
        Integer storeId = getStoreId(request);
        model.setViewName(View.ComboInfo.COMBO_SETTING);
        // 部门列表
        List<DeptInfo> deptInfoList = projectService.queryDeptInfoList(storeId);
        model.addObject("deptInfoList", deptInfoList);
        model.addObject("js_deptInfoList", JSONArray.fromObject(deptInfoList));

        // 项目列表
        ProjectInfoDto projectInfoDto = new ProjectInfoDto();
        projectInfoDto.setStoreId(storeId);
        projectInfoDto.setIsDeleted(0);
        List<ProjectInfoDto> projectInfoDtoList = projectService.queryProjectInfoList(projectInfoDto);
        model.addObject("projectInfoDtoList", projectInfoDtoList);
        model.addObject("projectInfoList", JSONArray.fromObject(projectInfoDtoList));
        
        //商品列表
        GoodsInfoDto goodsInfo = new GoodsInfoDto();
        goodsInfo.setStoreId(storeId);
        goodsInfo.setIsDeleted(0);
        goodsInfo.setIsSellProduct(1);
        List<GoodsInfoDto> goodsinfos = goodsInfoService.selectGoodsInfos(goodsInfo);
        model.addObject("goodsinfos", goodsinfos);
        model.addObject("goodsinfos_js", JSONArray.fromObject(goodsinfos));
        // 会员等级列表
        List<MemberLevelDto> memberLevelList = memberLevelService.queryByAllStoreId(storeId);
        model.addObject("memberLevels", memberLevelList);
        
        if (comboId!=null){
            BaseDto baseDto = queryComboInfoById(request, response, comboId);
            @SuppressWarnings("unchecked")
            Map<String, Object> map = (Map<String, Object>) baseDto.getMsg();
            model.addObject("comboId", ((ComboInfo)map.get("comboInfo")).getComboId());
            model.addObject("comboInfo", JSONObject.fromObject(map.get("comboInfo")));
            model.addObject("comboProjectList", JSONArray.fromObject(map.get("comboProjectList")));
            model.addObject("comboGoods", JSONArray.fromObject(map.get("comboGoods")));
            model.addObject("comboMemberLevel", JSONArray.fromObject(map.get("comboMemberLevel")));
        }
        
        return model;
    }
    
    /**
     * 套餐的修改新增操作
    * @author 高国藩
    * @date 2016年6月7日 上午10:16:40
    * @param request     request
    * @param response    response
    * @param jsonObject  jsonObject
    * @return            comboInfo
     */
    @RequestMapping(value = Url.ComboInfo.SAVE_ALL_COMBOINFO)
    @ResponseBody
    @SuppressWarnings("unchecked")
    public BaseDto saveAllComboInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody JSONObject jsonObject){
        Integer storeId = getStoreId(request);
        ComboInfo comboInfo = (ComboInfo) JSONObject.toBean(JSONObject.fromObject(jsonObject.get("comboInfo")), ComboInfo.class);
        List<ComboProject> comboProjects = (List<ComboProject>) JSONArray.toCollection(jsonObject.getJSONArray("comboProject"), ComboProject.class);
        List<ComboGoods> comboGoods = (List<ComboGoods>) JSONArray.toCollection(jsonObject.getJSONArray("comboGoods"), ComboGoods.class);
        comboInfo.setStoreId(storeId);
        return comboInfoService.saveOrUpdate(comboInfo, comboProjects, comboGoods);
    }
    

    /**
     * 保存套餐
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:16:55
    * @param request request
    * @param response response
    * @param comboInfo 套餐
    * @return BaseDto ajax返回对象
     */
    @RequestMapping(value = Url.ComboInfo.SAVE_COMBOINFO)
    @ResponseBody
    public BaseDto saveComboInfo(HttpServletRequest request, HttpServletResponse response, ComboInfo comboInfo) {
        try {
            String[] projectId = request.getParameterValues("projectIds");
            String[] projectNameArr = request.getParameterValues("projectNames");
            // String projectNameStr = request.getParameter("projectNames");
            String[] projectName = projectNameArr[0].split(",");
            String[] projectPrice = request.getParameterValues("projectPrice");
            String[] projectCount = request.getParameterValues("projectCounts");
            String[] comboPerformanceCal = request.getParameterValues("comboPerformanceCal");
            String[] royaltyRate = request.getParameterValues("royaltyRate");
            /**将所有的项目的次数相加,存入套餐总数中*/
            Integer projectCount1 = 0;
            for (int i = 0; i < projectCount.length; i++) {
                projectCount1 = projectCount1 + Integer.parseInt(projectCount[i]);
            }
            comboInfo.setProjectCount(projectCount1);

            String levelId = request.getParameter("levelId");
            String validDateVip = request.getParameter("validDateVip");

            String[] empLevelId = request.getParameterValues("empLevelId");
            String[] assignType = request.getParameterValues("assignTypeDate");
            /*String[] assignType = request.getParameterValues("assignType");*/
            String[] assignCash = request.getParameterValues("assignCash");
            String[] assignCard = request.getParameterValues("assignCard");
            String[] nonAssignCash = request.getParameterValues("assignCash");
            String[] nonAssignCard = request.getParameterValues("assignCard");
            /*String[] nonAssignCash = request.getParameterValues("nonAssignCash");
            String[] nonAssignCard = request.getParameterValues("nonAssignCard");*/
            /**下面的这个字段啊,不知道为啥,有时候就能传过数据,有的时候传不过来,所以做一个特定的处理*/
            String commissionTypeDate = request.getParameter("commissionTypeDate");
            comboInfo.setCommissionType(Integer.parseInt(commissionTypeDate));
            
            //商品开始
            String[] goodsId = request.getParameterValues("goodsId");
            String goodsName = request.getParameter("goodsName");
            String[] goodsPrice = request.getParameterValues("goodsPrice");
            String[] goodsCounts = request.getParameterValues("goodsCounts");
            String[] goodsCommissionTypeDate = request.getParameterValues("goodsCommissionTypeDate");
            String[] commissionAmount = request.getParameterValues("commissionAmount");
            String[] comboGoodsPerformanceCal = request.getParameterValues("comboGoodsPerformanceCal");
            if (comboInfo.getComboId() == null) {
                comboInfo.setStoreId(getStoreId(request));
                // 新增
                Integer comboId = comboInfoService.saveComboInfo(getUserId(request), comboInfo, projectId, projectName, projectPrice, projectCount,
                        comboPerformanceCal, royaltyRate, levelId, validDateVip, empLevelId, assignType, 
                        assignCash, assignCard, nonAssignCash, nonAssignCard);
                comboInfoService.saveComboGoods(comboId, goodsId, goodsName, goodsPrice, goodsCounts, goodsCommissionTypeDate, commissionAmount, 
                        comboGoodsPerformanceCal);
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, comboId);
            }
            else {
                Integer comboId = comboInfo.getComboId();
                List<MemberComboRecord> memberComboRecords = memberComboRecordMapper.selectByComboId(comboId);
                if (memberComboRecords!=null&&memberComboRecords.size()>0){
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "当前套餐正在使用中,不可更改");
                }
                else {
                    // 编辑
                    comboInfoService.updateComboInfo(getUserId(request), comboInfo, projectId, projectName, projectPrice, projectCount,
                            comboPerformanceCal, royaltyRate, levelId, validDateVip, empLevelId, assignType, assignCash, assignCard, 
                            nonAssignCash, nonAssignCard);
                    comboInfoService.saveComboGoods(comboId, goodsId, goodsName, goodsPrice, goodsCounts, goodsCommissionTypeDate, commissionAmount, 
                            comboGoodsPerformanceCal);
                    return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "请为套餐设置项目");
        }
    }
    
    /**
     * 批量保存套餐
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:16:55
    * @param request request
    * @param response response
    * @param deptId     部门标识
    * @param comboName  套餐名字集合
    * @return BaseDto ajax返回对象
     */
    @RequestMapping(value = Url.ComboInfo.SAVE_COMBOINFO_LIST, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto saveComboInfos(HttpServletRequest request, HttpServletResponse response, Integer deptId, String[] comboName) {
        Integer userId = (Integer) request.getAttribute(App.Session.USER_ID);
        Integer storeId = getStoreId(request);
        return comboInfoService.saveComboInfos(deptId, comboName, userId, storeId);
    }
    
    

    /**
     * 根据套餐id查询
    * @author 洪秋霞
    * @date 2015年8月6日 下午5:28:55
    * @param request request
    * @param response response
    * @param comboId 套餐id
    * @return BaseDto
     */
    @RequestMapping(value = Url.ComboInfo.QUERY_COMBOINFO_BYID)
    @ResponseBody
    public BaseDto queryComboInfoById(HttpServletRequest request, HttpServletResponse response, Integer comboId) {
        try {
            ComboInfo comboInfo = comboInfoService.queryComboInfoById(comboId);
            // 套餐内项目列表
            List<ComboProject> comboProjectList = comboInfoService.queryComboProject(comboId);
            // 套餐会员等级关联信息
            ComboMemberLevel comboMemberLevel = comboInfoService.queryComboMemberLevelByComboId(comboId);
            // 根据套餐id查询套餐项目提成列表
            //List<ComboInfoProjectCommissionDto> comboProjectCommissionList = comboInfoService.queryComboCommissionByProjectId(comboId);
            // 套餐商品关联列表
            List<ComboGoods> comboGoods = comboInfoService.queryComboGoods(comboId);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("comboInfo", comboInfo);
            map.put("comboProjectList", comboProjectList);
            map.put("comboMemberLevel", comboMemberLevel);
            //map.put("comboProjectCommissionList", comboProjectCommissionList);
            map.put("comboGoods", comboGoods);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, map);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 删除套餐
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:19:05
    * @param comboId 套餐id
    * @return BaseDto
     */
    @RequestMapping(value = Url.ComboInfo.DELETE_COMBOINFO)
    @ResponseBody
    public BaseDto deleteComboInfo(Integer comboId) {
        try {
            comboInfoService.deleteComboInfo(comboId);
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, App.System.API_RESULT_MSG_FOR_SUCCEES);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }

    /**
     * 根据项目id查询提成职位列表
    * @author 洪秋霞
    * @date 2015年9月23日 下午8:25:08
    * @param projectId 项目标识
    * @return BaseDto
     */
    @RequestMapping(value = Url.ComboInfo.QUERY_COMMISSIONBY_PROJECTID)
    @ResponseBody
    public BaseDto queryProjectCommissionByProjectId(Integer projectId) {
        try {
            if (projectId == -1) {
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, null);
            }
            else {
                List<ProjectCommissionDto> projectCommissionList = projectService.queryProjectCommissionByProjectId(projectId);
                return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, projectCommissionList);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, App.System.API_RESULT_MSG_FOR_FAIL);
        }
    }
    
}
