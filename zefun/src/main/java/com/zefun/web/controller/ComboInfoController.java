package com.zefun.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.zefun.web.dto.DeptInfoDto;
import com.zefun.web.dto.MemberLevelDto;
import com.zefun.web.dto.ProjectCommissionDto;
import com.zefun.web.entity.ComboGoods;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.ComboMemberLevel;
import com.zefun.web.entity.ComboProject;
import com.zefun.web.entity.MemberComboRecord;
import com.zefun.web.mapper.ComboInfoMapper;
import com.zefun.web.mapper.DeptInfoMapper;
import com.zefun.web.mapper.MemberComboRecordMapper;
import com.zefun.web.service.ComboInfoService;
import com.zefun.web.service.GoodsInfoService;
import com.zefun.web.service.MemberLevelService;
import com.zefun.web.service.ProjectService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 疗程
* @author 洪秋霞
* @date 2015年8月6日 上午10:07:42 
*
 */
@Controller
public class ComboInfoController extends BaseController {
    /**疗程*/
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
    /**会员疗程关联*/
    @Autowired
    private MemberComboRecordMapper memberComboRecordMapper;
    /**疗程*/
    @Autowired
    private ComboInfoMapper comboInfoMapper;
    /**部门信息*/
    @Autowired
    private DeptInfoMapper deptInfoMapper;

    /**
     * 进入疗程页面
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
            
            List<DeptGoodsBaseDto> deptGoodsBaseDto = goodsInfoService.getDeptGoodsByStoreId(storeId);
            model.addObject("deptGoodsBaseDto", deptGoodsBaseDto);
            model.addObject("deptGoodsBaseDtoJs", JSONArray.fromObject(deptGoodsBaseDto));
            
            if (deptId!=null){
                comboInfos = comboInfos.stream().filter(c -> c.getDeptId().equals(deptId)).collect(Collectors.toList());
                model.addObject("deptId", deptId);
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
     * 新增疗程页面
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

        // 部门下商品和项目汇总
        List<DeptInfoDto> deptInfoDtos = deptInfoMapper.selectProjectAndGoodsInfoByStoreId(storeId);
        model.addObject("deptInfoList", deptInfoDtos);
        model.addObject("js_deptInfoList", JSONArray.fromObject(deptInfoDtos));
        
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
     * 疗程的修改新增操作
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
     * 批量保存疗程
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:16:55
    * @param request request
    * @param response response
    * @param deptId     部门标识
    * @param comboName  疗程名字集合
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
     * 根据疗程id查询
    * @author 洪秋霞
    * @date 2015年8月6日 下午5:28:55
    * @param request request
    * @param response response
    * @param comboId 疗程id
    * @return BaseDto
     */
    @RequestMapping(value = Url.ComboInfo.QUERY_COMBOINFO_BYID)
    @ResponseBody
    public BaseDto queryComboInfoById(HttpServletRequest request, HttpServletResponse response, Integer comboId) {
        try {
            ComboInfo comboInfo = comboInfoService.queryComboInfoById(comboId);
            // 疗程内项目列表
            List<ComboProject> comboProjectList = comboInfoService.queryComboProject(comboId);
            // 疗程会员等级关联信息
            ComboMemberLevel comboMemberLevel = comboInfoService.queryComboMemberLevelByComboId(comboId);
            // 根据疗程id查询疗程项目提成列表
            //List<ComboInfoProjectCommissionDto> comboProjectCommissionList = comboInfoService.queryComboCommissionByProjectId(comboId);
            // 疗程商品关联列表
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
     * 删除疗程
    * @author 洪秋霞
    * @date 2015年8月11日 上午10:19:05
    * @param comboId 疗程id
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
