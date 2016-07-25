package com.zefun.web.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.HttpClientUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.MemberBaseDto;
import com.zefun.web.dto.MemberInfoDto;
import com.zefun.web.dto.ScreeningDto;
import com.zefun.web.entity.MemberInfo;
import com.zefun.web.entity.MemberScreening;
import com.zefun.web.entity.Page;
import com.zefun.web.entity.RumorsCourse;
import com.zefun.web.entity.StoreSetting;
import com.zefun.web.mapper.RumorsCourseMapper;
import com.zefun.web.mapper.StoreSettingMapper;
import com.zefun.web.service.MemberInfoService;

import net.sf.json.JSONObject;

/**
 * 会员管理
* @author 高国藩
* @date 2015年9月8日 下午5:50:53
 */
@Controller
public class MemberController extends BaseController{

	/**会员*/
	@Autowired
	private MemberInfoService memberInfoService;
    /**门店设置*/
    @Autowired
    private StoreSettingMapper storeSettingMapper;
    /**盛传疗程卡导入*/
    @Autowired
    private RumorsCourseMapper rumorsCourseMapper;
    /**日志记录对象*/
    private static Logger logger = Logger.getLogger(HttpClientUtil.class);
	
    /**https*/
    private static final String HTTPS_PROTOCOL = "https://";
    
    /**https 端口*/
    private static final int HTTPS_PROTOCOL_DEFAULT_PORT = 443;

	/**
	 * 会员数据展示
	* @author 高国藩
	* @date 2015年9月8日 下午5:53:46
	* @param request 请求数据封装
	* @return 跳转页面
	 */
	@RequestMapping(value = Url.Member.MEMBER_VIEW_LIST, method = RequestMethod.GET)
	public ModelAndView memberListView(HttpServletRequest request){
	    Integer storeId = getStoreId(request);
		return memberInfoService.selectMemberByStoreId(storeId);
	}
	
	
	/**
	 * 退卡操作
	* @author 高国藩
	* @date 2016年7月7日 下午4:44:10
	* @param request        request
	* @param subAccountId   subAccountId
	* @return               BaseDto
	 */
	@RequestMapping(value = Url.Member.MEMBER_RETURN_CARD, method = RequestMethod.POST)
	@ResponseBody
    public BaseDto returnCardMember(HttpServletRequest request, Integer subAccountId){
        return memberInfoService.returnCardMember(subAccountId);
    }
	
	/**
     * 冻结/解冻会员
    * @author 高国藩
    * @date 2016年7月7日 下午4:44:10
    * @param request        request
    * @param memberId   memberId
    * @param isDeleted isDeleted
    * @return               BaseDto
     */
    @RequestMapping(value = Url.Member.MEMBER_DELTED_INFO, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deletedMemberInfo(HttpServletRequest request, Integer memberId, Integer isDeleted){
        return memberInfoService.deletedMemberInfo(memberId, isDeleted);
    }
	
	/**
	 * 新增选择器
	* @author 高国藩
	* @date 2015年9月8日 下午8:10:31
	* @param memberScreening  实体
	* @param request          请求数据封装
	* @param branchOffice     总店查询分店
	* @return                 状态
	 */
	@RequestMapping(value = Url.Member.MEMBER_SCREEN_ADD, method = RequestMethod.POST)
	@ResponseBody
    public BaseDto addMemberScreening(MemberScreening memberScreening, HttpServletRequest request, String branchOffice){
        Integer storeId = getStoreId(request);
        return memberInfoService.addMemberScreening(storeId, memberScreening, branchOffice);
    }
	
	/**
     * 通过预设的条件查询会员数据
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param screeningDto  查询条件
    * @param page          分页
    * @param request       请求数据封装
    * @param branchOffice  分店storeId数组
    * @return              状态
     */
    @RequestMapping(value = Url.Member.SERCH_MEMBER_BY_TREM, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto listMemberInfosByTerm(ScreeningDto screeningDto, Page<MemberInfoDto> page, 
            HttpServletRequest request, String branchOffice){
        Integer storeId = getStoreId(request);
        screeningDto.setStoreId(storeId);
        return memberInfoService.listMemberInfosByTerm(screeningDto, page, branchOffice);
    } 
    
    /**
     * 通过姓名电话条件查询会员数据
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param content 查询条件
    * @param page    分页
    * @param sex     性别
    * @param levelId 会员等级
    * @param request 请求数据封装
    * @return        状态
     */
    @RequestMapping(value = Url.Member.SERCH_MEMBER_BY_CONTENT, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto listMemberInfosByContent(String content, Page<MemberInfoDto> page, HttpServletRequest request, String sex, Integer levelId){
        Integer storeId = getStoreId(request);
        BaseDto baseDto = memberInfoService.listMemberInfosByContent(storeId, content, page, sex, levelId);
        return baseDto;
    } 
	
	/**
     * 会员分组页面
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param request 请求数据封装
    * @return        跳转页面 
     */
    @RequestMapping(value = Url.Member.VIEW_CENSUS_LIST, method = RequestMethod.GET)
    public ModelAndView viewCensusList(HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return memberInfoService.viewCensusList(storeId);
    }
	
    /**
     * 根据选择器来查询会员数据
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param groupId   筛选器
    * @param page      分页
    * @param request   请求数据封装
    * @return          状态
     */
    @RequestMapping(value = Url.Member.VIEW_LIST_BY_SCREEN, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto viewListMemberByScreen(Integer groupId, Page<MemberInfoDto> page, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        return memberInfoService.viewListMemberByScreen(storeId, groupId, page);
    }
    
    /**
     * 会员修改资料
    * @author 高国藩
    * @date 2015年12月18日 下午3:44:44
    * @param request       请求
    * @param memberInfo    会员数据
    * @return              修改状态
     */
    @RequestMapping(value = Url.Member.ACTION_UPDATE_MEMBER, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto actionUpdateMemberInfo(HttpServletRequest request, MemberInfo memberInfo){
        Integer storeId = getStoreId(request);
        return memberInfoService.updateMemberInfo(memberInfo, storeId);
    }
    
    /**
     * 校验会员是否存在
    * @author 王大爷
    * @date 2015年9月12日 下午2:55:04
    * @param phone     手机号码
    * @param request   请求数据封装
    * @return BaseDto
     */
    @RequestMapping(value = Url.Member.SELECT_MEMBER_BYPHONE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto selectMemberByPhone(String phone, HttpServletRequest request){
        Integer storeId = getStoreId(request);
        MemberBaseDto memberBaseDto = memberInfoService.selectMemberInfoByPhone(phone, storeId);
        if (memberBaseDto == null) {
            return new BaseDto(41007, "会员号码不存在，努力回忆下");
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, memberBaseDto);
        }
    }
    
    /**
     * 删除会员分组
    * @author 高国藩
    * @date 2015年9月8日 下午8:10:31
    * @param groupId 筛选器
    * @return 状态
     */
    @RequestMapping(value = Url.Member.DELETE_CENSUS, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deleteMemberCensus(Integer groupId){
        return memberInfoService.deleteMemberCensus(groupId);
    }
    
    /**
     * 会员导入项
    * @author 高国藩
    * @date 2015年11月18日 下午3:41:59
    * @param file           excle文件
    * @param request        请求
    * @param response       相应
    * @param loginCode      公司编码
    * @param loginName      公司编码
    * @param loginPass      公司编码
    * @return               状态
     * @throws Exception    异常
     */
    @RequestMapping(value = Url.Member.IMPORTEXCLE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto importExcle(@RequestParam("file") MultipartFile[] file, String loginCode, String loginName, String loginPass,
              HttpServletRequest request, HttpServletResponse response) throws Exception   {
        Integer storeId=getStoreId(request);
        Integer lastOperatorId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        String storeName = request.getParameter("storeName");
        StoreSetting setting = storeSettingMapper.selectByPrimaryKey(storeId);
        String storeAccount = getStoreAccount(request);
        if (setting.getLastFacilitator()!=null&&!"".equals(setting.getLastFacilitator())){
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "不可重复导入");
        }
        if (storeName.equals("盛传")){
            return memberInfoService.importExcleSc(file[0], file[1], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else if (storeName.equals("左右")){
            return memberInfoService.importExcleZy(file[0], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else if (storeName.equals("云浩企汇通")){
            return memberInfoService.importExcleHt(file[0], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else if (storeName.equals("博卡")){
            return memberInfoService.importExcleBk(file[0], file[1], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else if (storeName.equals("耕宇")){
            return memberInfoService.importExcleGy(file[0], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else if (storeName.equals("共赢")){
            return memberInfoService.importExcleGi(file[0], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else if (storeName.equals("模板")){
            return memberInfoService.importExcleMb(file[0], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else if (storeName.equals("蓝蝶")){
            Map<String, String> params = new HashMap<>();
            params.put("__RequestVerificationToken", "egkJbuhSzyr5713YWUi3JzF4Hi1Q1vV2negYxvSixDiDI5NfrIge3B"
                    + "kQUur5N3MpssZzt0e47Vzkwi753MdZb9tzBHZ4kIrPspCMQNox-uSm0S7e5R8K-th_RLQe4Z5ScGqBnrQhI-n_"
                    + "2cggo-m96zq2E5MO8xXOBtCa3HUSR8g1");
            params.put("cid", loginCode);
            params.put("uid", loginName);
            params.put("pwd", loginPass);
            BasicCookieStore cookieStore = sendPostReq("http://vip.landee.com.cn/Home/SignIn", params, "UTF-8");
            return memberInfoService.importExcleLd(storeId, storeAccount, lastOperatorId, response, storeName, cookieStore);
        }
        else if (storeName.equals("西沙龙")){
            return memberInfoService.importExcleXsl(file[0], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else if (storeName.equals("华拓美业")){
            return memberInfoService.importExcleHtmy(file[0], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else if (storeName.equals("华彩")){
            return memberInfoService.importExcleHc(file[0], file[1], storeId, storeAccount, lastOperatorId, response, storeName);
        }
        else {
            return new BaseDto(App.System.API_RESULT_CODE_FOR_FAIL, "暂不支持其他服务商数据导入");
        }
    }
    
    /**
     * 进入错误会员统计页面
    * @author 高国藩
    * @date 2015年12月7日 上午10:37:39
    * @param request        请求
    * @param response       结果
    * @return               返回页面 
     * @throws IOException  异常
     */
    @RequestMapping(value = Url.Member.VIEW_ERROR_MEMBER, method = RequestMethod.GET)
    public ModelAndView viewErrorMember(HttpServletRequest request, HttpServletResponse response) throws IOException{
        Integer storeId=getStoreId(request);
        return memberInfoService.viewErrorMember(storeId, response);
    }
    
    /**
     * 盛传疗程卡分页查询
    * @author 高国藩
    * @date 2015年12月30日 下午4:25:39
    * @param request  请求
    * @param page     分页
    * @return         分页数据
     */
    @RequestMapping(value = Url.Member.RUMORS_COURSE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto viewRumorsCourse(HttpServletRequest request, Page<RumorsCourse> page){
        Integer storeId=getStoreId(request);
        Map<String, Object> params = new HashMap<>();
        params.put("storeId", storeId);
        page.setParams(params);
        List<RumorsCourse> rumorsCourses = rumorsCourseMapper.selectByPage(page);
        page.setResults(rumorsCourses);
        return new BaseDto(App.System.API_RESULT_CODE_FOR_SUCCEES, page);
    }
    
    /**
     * 会员数据余额迁移
    * @author 高国藩
    * @date 2015年12月19日 上午10:33:43
    * @param request                请求数据
    * @param id                     异常会员id
    * @param memberId               会员id
    * @param type                   类型(1:盛传 2:左右 3:企汇通 4:博卡,5:耕宇)
    * @return                       状态提示
     */
    @RequestMapping(value = Url.Member.MEMBER_BALAND_MOVE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto balandMemberMove(HttpServletRequest request, Integer type, Integer id, Integer memberId){
        Integer storeId=getStoreId(request);
        Integer lastOperatorId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        return memberInfoService.balandMemberMove(storeId, lastOperatorId, type, id, memberId);
    }
    
    
    /**
     *  异常会员疗程迁移
    * @author 高国藩
    * @date 2015年12月19日 下午1:47:11
    * @param request          请求数据
    * @param jsonObject        数据集合
    * @return                 状态
     */
    @RequestMapping(value = Url.Member.MEMBER_COMBO_MOVE_ALL, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto comboMemberMove(@RequestBody JSONObject jsonObject, HttpServletRequest request){
        Integer storeId=getStoreId(request);
        Integer lastOperatorId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        String storeAccount = getStoreAccount(request);
        return memberInfoService.comboMemberMoveAll(storeId, storeAccount, lastOperatorId, 
                (Integer)jsonObject.get("type"), jsonObject.getJSONArray("list"));
    }
    
    /**
     * 异常会员疗程迁移-单个
    * @author 高国藩
    * @date 2016年3月14日 下午3:41:59
    * @param request request
    * @param type    type
    * @param id      id
    * @param memberId memberId
    * @param comboId  comboId
    * @param overdueTime overdueTime
    * @param projectCount projectCount
    * @param moveType moveType
    * @return baseDto
     */
    @RequestMapping(value = Url.Member.MEMBER_COMBO_MOVE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto comboMemberMove(HttpServletRequest request, Integer type, Integer id, Integer memberId, Integer comboId, 
            String overdueTime, Integer[] projectCount, Integer moveType){
        Integer storeId=getStoreId(request);
        Integer lastOperatorId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        return memberInfoService.comboMemberMove(storeId, lastOperatorId, type, id, memberId, comboId, overdueTime, projectCount, moveType);
    }
    
    /**
     * 分页查询异常会员
    * @author 高国藩
    * @date 2015年12月7日 下午2:28:01
    * @param request   请求
    * @param pageNo    页码
    * @param pageSize  一夜
    * @param content   搜索条件
    * @return          集合
     */
    @RequestMapping(value = Url.Member.VIEW_ERROR_MEMBER, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto viewErrorMember(HttpServletRequest request, Integer pageNo, Integer pageSize, String content){
        Integer storeId=getStoreId(request);
        return memberInfoService.viewErrorMember(storeId, pageNo, pageSize, content);
    }
    
    
    /**
     * 会员错误数据删除操作
    * @author 张进军
    * @date Dec 13, 2015 12:59:05 PM
    * @param type     服务商类型(1:盛传,2:左右,3:云浩,4:博卡,5:耕宇)
    * @param id       错误数据标识
    * @param request  请求
    * @return         成功返回码为0，失败为其他返回码
     */
    @RequestMapping(value = Url.Member.ACTION_ERROR_MEMBER_DELETE, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deleteErrorMemberAction(HttpServletRequest request, Integer type, Integer id){
        Integer userId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        return memberInfoService.deleteErrorMemberAction(type, id, userId);
    }
    
    
    /**
     * 总店查看分店会员数据
    * @author 高国藩
    * @date 2015年12月7日 上午10:37:39
    * @param request      请求
    * @param response     结果
    * @return             返回页面 
     */
    @RequestMapping(value = Url.Member.VIEW_BASE_MEMBER, method = RequestMethod.GET)
    public ModelAndView viewBaserMember(HttpServletRequest request, HttpServletResponse response){
        Integer storeId=getStoreId(request);
        return memberInfoService.viewBaseMember(storeId);
    }
    
    /**
     * 删除会员数据
    * @author 高国藩
    * @date 2015年12月25日 下午8:34:42
    * @param memberId   会员ID
    * @param request    请求数据
    * @return           页面数据
     */
    @RequestMapping(value = Url.Member.MEMBER_DELETED, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto deleteMemberAction(HttpServletRequest request, Integer memberId){
        Integer userId = (Integer) request.getSession().getAttribute(App.Session.USER_ID);
        return memberInfoService.deleteMemberAction(memberId, userId);
    }
    
    /**
     * 导出异常会员数据
    * @author 高国藩
    * @date 2015年12月17日 下午5:24:26
    * @param request         请求
    * @return                导出
    * @throws IOException    异常 
     */
    @RequestMapping(value = Url.Member.DOWN_ERROR_MEMBER, method = RequestMethod.GET)
    public ResponseEntity<byte[]> downloadErrorMember(HttpServletRequest request) throws IOException {
        Integer storeId=getStoreId(request);
        return memberInfoService.downloadErrorMember(storeId);
    }
    
    /**
     * 发送POST请求，支持HTTP与HTTPS
     * @param url 请求地址
     * @param params 请求参数
     * @param charset 返回数据编码
     * @return 返回数据
     */
    public static BasicCookieStore sendPostReq(String url, Map<String, ?> params, String charset) {
        CloseableHttpClient httpClient = null;
        httpClient = HttpClientBuilder.create().build();
        RequestConfig reqConf = RequestConfig.DEFAULT;
        HttpPost httpPost = new HttpPost(url);
        // 封装请求参数
        List<NameValuePair> pairs = geneNameValPairs(params);
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
            // 对HTTPS请求进行处理
            if (url.toLowerCase().startsWith(HTTPS_PROTOCOL)) {
                initSSL(httpClient, getPort(url));
            }
            // 提交请求并以指定编码获取返回数据
            httpPost.setConfig(reqConf);
            HttpResponse httpResponse = httpClient.execute(httpPost);
            int statuscode = httpResponse.getStatusLine().getStatusCode();
            if ((statuscode == HttpStatus.SC_MOVED_TEMPORARILY) || (statuscode == HttpStatus.SC_MOVED_PERMANENTLY)
                    || (statuscode == HttpStatus.SC_SEE_OTHER) || (statuscode == HttpStatus.SC_TEMPORARY_REDIRECT)) {
                Header header = httpResponse.getFirstHeader("location");
                if (header != null) {
                    String newuri = header.getValue();
                    if ((newuri == null) || (newuri.equals(""))){
                        newuri = "/";
                    }
                    if (("/".equals(newuri))){
                        newuri = "http://"+httpPost.getURI().getHost();
                    }
                    try {
                        httpClient.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        httpClient = null;
                    }
                    return setCookieStore(httpResponse, httpPost.getURI().getHost());
                }
            }
            logger.info("请求地址：" + url + ";响应状态：" + httpResponse.getStatusLine());
            return setCookieStore(httpResponse, httpPost.getURI().getHost());
        }
        catch (UnsupportedEncodingException e) {
            logger.error("不支持当前参数编码格式[" + charset + "],堆栈信息如下", e);
        }
        catch (ClientProtocolException e) {
            logger.error("协议异常,堆栈信息如下", e);
        }
        catch (IOException e) {
            logger.error("网络异常,堆栈信息如下", e);
        }
        finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            }
            catch (Exception e) {
                e.printStackTrace();
                httpClient = null;
            }
        }
        return null;
    }
    
    /**
     * 将httpResponse中的cookie信息保存进请求中
    * @author 高国藩
    * @date 2016年2月20日 上午10:39:02
    * @param httpResponse       结果集合
    * @param host               目标地址
    * @return                   cookie集合
     */
    public static BasicCookieStore setCookieStore(HttpResponse httpResponse, String host) {
        BasicCookieStore cookieStore = new BasicCookieStore();
        Header[] headers = httpResponse.getHeaders("Set-Cookie");
        for (Header header : headers) {
            String value = header.getValue();
            value.split(";");
            BasicClientCookie cookie = new BasicClientCookie(value.split(";")[0].split("=")[0], value.split(";")[0].split("=")[1]);
            cookie.setVersion(0);
            cookie.setDomain(host);
            cookie.setPath("/");
            cookieStore.addCookie(cookie);
        }
        return cookieStore;
    }
    
    
    /**
     * 将map参数转换为http参数
    * @author 张进军
    * @date Nov 23, 2015 9:49:53 AM
    * @param params 请求参数对象
    * @return   http请求参数对象
     */
    private static List<NameValuePair> geneNameValPairs(Map<String, ?> params) {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        if (params == null) {
            return pairs;
        }
        for (String name : params.keySet()) {
            if (params.get(name) == null) {
                continue;
            }
            pairs.add(new BasicNameValuePair(name, params.get(name).toString()));
        }
        return pairs;
    }
    
    /**
     * 根据请求地址获取端口
    * @author 张进军
    * @date Nov 23, 2015 9:46:57 AM
    * @param url    请求地址
    * @return   端口
     */
    private static int getPort(String url) {
        int startIndex = url.indexOf("://") + "://".length();
        String host = url.substring(startIndex);
        if (host.indexOf("/") != -1) {
            host = host.substring(0, host.indexOf("/"));
        }
        int port = HTTPS_PROTOCOL_DEFAULT_PORT;
        if (host.contains(":")) {
            int i = host.indexOf(":");
            port = new Integer(host.substring(i + 1));
        }
        return port;
    }
    
    /**
     * 初始化HTTPS请求服务
     * @param httpClient HTTP客户端
     * @param port 端口
     */
    public static void initSSL(CloseableHttpClient httpClient, int port) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            final X509TrustManager trustManager = new X509TrustManager() {
                @Override
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
                }

                @Override
				public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            // 使用TrustManager来初始化该上下文,TrustManager只是被SSL的Socket所使用
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            ConnectionSocketFactory ssf = new SSLConnectionSocketFactory(sslContext);
            Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory> create().register("https", ssf).build();
            BasicHttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(r);
            HttpClients.custom().setConnectionManager(ccm).build();
        }
        catch (KeyManagementException e) {
            e.printStackTrace();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
