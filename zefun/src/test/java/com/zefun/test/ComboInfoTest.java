package com.zefun.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.zefun.web.controller.ComboInfoController;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.BusinessSummaryRelativeAmt;
import com.zefun.web.dto.DeptSummaryDto;
import com.zefun.web.dto.Member2Info;
import com.zefun.web.dto.MemberLevelInfo;
import com.zefun.web.dto.ServiceReportDto;
import com.zefun.web.dto.SummaryResultDto;
import com.zefun.web.entity.ComboInfo;
import com.zefun.web.entity.StoreInfo;
import com.zefun.web.mapper.BusinessReportMapper;
import com.zefun.web.mapper.OrderDetailMapper;
import com.zefun.web.mapper.OrderInfoMapper;
import com.zefun.web.mapper.StoreInfoMapper;
import com.zefun.web.service.BusinessReporterService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 套餐信息测试
* @author 洪秋霞
* @date 2015年8月11日 下午2:38:37
 */
public class ComboInfoTest extends BaseTest {

    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(ComboInfoTest.class);
    
    /**
     * request
     */
    @Mock MockHttpServletRequest request = new MockHttpServletRequest();
    /**
     * response
     */
    @Mock MockHttpServletResponse response = new MockHttpServletResponse();

    /**
     * 套餐Controller
     */
    @Autowired private ComboInfoController comboInfoController;
    /**
     * 订单mapper
     * */
    @Autowired
    private OrderInfoMapper infoMapper;
    
    /**明细mapper*/
    @Autowired
    public OrderDetailMapper detailMapper;
    
    /**门店服务接口*/
    @Autowired
    private StoreInfoMapper storeInfoMapper;
    
    /**
     * 统计business的reporter
     * */
    @Autowired
    private BusinessReportMapper reporter;
    
    /**
     * 营业报表服务类
     * */
    @Autowired
    private BusinessReporterService reporterService;
    
    /**
    * @author 乐建建
    * @date 2016年3月3日 上午10:54:00 
    */
    @Test
    public void testGetResult(){
        Object obj=reporterService.getResult(1005, "2016-03");
        logger.info(obj);
    }
    
    /**
     * 保存
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:39:14
     */
    @Test
    public void saveComboInfo() {
        ComboInfo comboInfo = new ComboInfo();
        comboInfo.setStoreId(1);
        comboInfo.setComboName("套餐名称test");
        comboInfo.setComboDesc("套餐desc");
        comboInfo.setProjectAmount(new BigDecimal(12));
        request.addParameter("projectIds", new String[] { "1", "2" });
        request.addParameter("projectNames", new String[] { "t1", "t2" });
        request.addParameter("projectCounts", new String[] { "2", "3" });
        BaseDto dto = comboInfoController.saveComboInfo(request, response, comboInfo);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 查询
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:39:39
     */
    @Test
    public void queryComboInfoById() {
        BaseDto dto = comboInfoController.queryComboInfoById(request, response, 1);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 测试选择方法
     * */
    @Test
    public void select(){
        SummaryResultDto dto=new SummaryResultDto();
        dto.setStoreId(157);
        dto.setBegin("2016-02-17");
        dto.setEnd("2016-02-18");
        List<DeptSummaryDto> depts=infoMapper.getDeptSummary(dto);
        logger.info(JSONArray.fromObject(depts));
    }
    
    /**
     * 测试营业汇总趋势数据
    * @author 乐建建
    * @date 2016年2月18日 下午7:45:11 
    */
    @Test
    public void trend(){
        SummaryResultDto dto=new SummaryResultDto();
        dto.setStoreId(157);
        dto.setDateType(1);
        dto.setBegin("2016-02-01");
        dto.setEnd("2016-02-29");
        List<BusinessSummaryRelativeAmt> depts=infoMapper.getBusinessIncomes(dto);
        logger.info(JSONArray.fromObject(depts));
    }
    
    /**
     * 测试商品信息
    * @author 乐建建
    * @date 2016年2月20日 下午7:15:28 
    */
    @Test
    public void goodInfo(){
        SummaryResultDto dto=new SummaryResultDto();
        dto.setStoreId(1005);
        dto.setDateType(1);
        dto.setBegin("2016-02-20");
        dto.setEnd("2016-02-21");
        List<ServiceReportDto>  depts=reporter.selectGoodReportByStoreId(dto);
        logger.info(JSONArray.fromObject(depts));
    }
    
    /**
     * 测试查询会员
    * @author 乐建建
    * @date 2016年2月23日 下午2:21:41 
    */
    @Test
    public void selectMember(){
        SummaryResultDto dto=new SummaryResultDto();
        dto.setBegin("2016-02-01");
        dto.setEnd("2016-03-01");
        dto.setStoreId(1005);
        dto.setHpStoreId(100);
        List<MemberLevelInfo> members=detailMapper.getMemberInfo(dto);
        List<Member2Info> list=new ArrayList<Member2Info>();
        for (int i=0; i<members.size(); i++){
            Member2Info member=detailMapper.getMemberType2Info(members.get(i));
            list.add(member);
        }
        logger.info(JSONArray.fromObject(list));
    }
    
    /**
     * 编辑
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:39:43
     */
    @Test
    public void editComboInfo() {
        ComboInfo comboInfo = new ComboInfo();
        comboInfo.setComboId(1);
        comboInfo.setStoreId(1);
        comboInfo.setComboName("套餐名称test");
        comboInfo.setComboDesc("套餐desc");
        comboInfo.setProjectAmount(new BigDecimal(12));
        request.addParameter("projectIds", new String[] { "1", "2" });
        request.addParameter("projectNames", new String[] { "t1", "t2" });
        request.addParameter("projectCounts", new String[] { "2", "3" });
//        BaseDto dto = comboInfoController.editComboInfo(request, response, comboInfo);
//        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

    /**
     * 删除
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:39:49
     */
    @Test
    public void deleteComboInfo() {
        BaseDto dto = comboInfoController.deleteComboInfo(1);
        logger.info("result : " + JSONObject.fromObject(dto).toString());
    }

}
