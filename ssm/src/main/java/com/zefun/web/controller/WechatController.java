package com.zefun.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.App;
import com.zefun.common.consts.Url;
import com.zefun.common.utils.SignUtil;
import com.zefun.web.dto.BaseDto;
import com.zefun.web.dto.WechatMenuDto;
import com.zefun.web.entity.Menu;
import com.zefun.web.service.WeixinMessageService;
import com.zefun.web.service.WeixinUploadService;
import com.zefun.web.wechat.dto.ThumbDto;

/**
 * 微信消息处理类
* @author 高国藩
* @date 2015年8月6日 下午8:25:55 
*
 */
@Controller
public class WechatController {
    /**
     * 微信消息service
     */
    @Autowired
    private WeixinMessageService weixinMessageService;
    /**
     * 微信接口调用service
     */
    @Autowired
    private WeixinUploadService weixinUploadService;
    /**
     * 日志
     */
    private Logger logger = Logger.getLogger(WechatController.class); 
    
    /**
     * 微信端口验证
    * @author 高国藩
    * @date 2015年8月11日 上午10:55:31
    * @param request 请求信息封装
    * @param response 返回信息
    * @throws ServletException 请求异常
    * @throws IOException 读写异常
     */
    @RequestMapping(value = Url.Wechat.CHART, method = RequestMethod.GET)
    public void dochart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String signature = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        PrintWriter out = response.getWriter();
        /** 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败*/
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    /**
     * 消息回复
    * @author 高国藩
    * @date 2015年8月11日 上午10:58:15
    * @param request 请求信息封装
    * @param response 返回信息
    * @throws ServletException 请求异常
    * @throws IOException 读写异常
     */
    @RequestMapping(value = Url.Wechat.CHART, method = RequestMethod.POST)
    public void chart(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String respMessage = weixinMessageService.processRequest(request);
        logger.info("返回信息:"+respMessage);
        /** 响应消息*/
        PrintWriter out = response.getWriter();
        out.print(respMessage);
        out.close();
    }

  /**
  *  图文消息上传,在上传过程中,将媒体id(一个)和各个图文消息的信息存入库中,为自动回复做准备.
  *  关键:各个图文消息存入的字段有:标题、图片素材ID、图片微信URL、图文链接地址、描述、门店ID,总之所有在微信接口中
  *  出现的字段都是要存入数据库中的,因为如果不存入的话,在用户点击了两个按钮拉取信息的时候,就会将接口次数调用完了
  * @author 高国藩
  * @date 2015年8月11日 上午11:15:25
  * @param thumb 单个图文消息
  * @return 上传状态
   */
    @RequestMapping(value = Url.Wechat.UPLOADNEWS, method = RequestMethod.POST)
    public BaseDto uploadNews(ThumbDto thumb) {
        Integer storeId = 1;
        String accessToken = App.Wechat.ACCESS_TOKEN;
        return weixinUploadService.uploadNews(accessToken, thumb, storeId);
    }

    /**
     * 新增菜单-本地库存储,此为ajax请求链接
    * @author 高国藩
    * @date 2015年8月6日 下午4:51:15
    * @param menu 菜单
    * @return 状态信息
     */
    @RequestMapping(value = Url.Wechat.ADD_MENU, method = RequestMethod.POST)
    @ResponseBody
    public BaseDto addMenu(Menu menu) {
        return weixinMessageService.addMenu(menu);
    }

    /**
     * 获得菜单列表
    * @author 高国藩
    * @date 2015年8月6日 下午5:19:25
    * @param storeId 门店ID
    * @return 查询菜单信息
     */
    @RequestMapping(value = Url.Wechat.GETMENU)
    @ResponseBody
    public List<WechatMenuDto> menuListAction(Integer storeId) {
        return weixinMessageService.getAllMenu(storeId);
    }

    /**
     * 获得单一菜单元素
    * @author 高国藩
    * @date 2015年8月6日 下午5:19:25
    * @param menuId 菜单ID
    * @return 查询单个菜单信息
     */
    @RequestMapping(value = Url.Wechat.GET_ONE_MENU)
    @ResponseBody
    public Menu menuOneAction(Integer menuId) {
        return weixinMessageService.getMenu(menuId);
    }

    /**
     * 调用微信接口更新菜单信息
    * @author 高国藩
    * @date 2015年8月11日 上午11:25:21
    * @param request 请求封装
    * @return 状态信息
     */
    @RequestMapping(value = Url.Wechat.CREATE_MENU)
    @ResponseBody
    public BaseDto createMenu(HttpServletRequest request) {
        String accessToken = App.Wechat.ACCESS_TOKEN;
        Integer storeId = 1;
        return weixinMessageService.createMenu(storeId, accessToken);
    }

    /**
     * 跳转至微信设置菜单页面
    * @author 高国藩
    * @date 2015年8月11日 上午11:25:48
    * @param request 请求封装
    * @return 返回页面
     */
    @RequestMapping(value = Url.Wechat.VIEW_LIST_MENU)
    public ModelAndView listAction(HttpServletRequest request) {
        return weixinMessageService.sendViewPage(request);
    }

    /**
     * 删除菜单
    * @author 高国藩
    * @date 2015年8月11日 上午11:26:43
    * @param menuId 菜单ID
    * @param request 请求封装
    * @return 状态信息
     */
    @RequestMapping(value = Url.Wechat.DELETE_MENU)
    @ResponseBody
    public BaseDto deleteMenuAction(Integer menuId, HttpServletRequest request) {
        Integer storeId = 1;
        return weixinMessageService.deleteMenuAction(menuId, storeId);
    }

    /**
     * 设置菜单跳转链接
    * @author 高国藩
    * @date 2015年8月11日 上午11:27:07
    * @param menu 菜单
    * @return 状态信息
     */
    @RequestMapping(value = Url.Wechat.SET_MENU_URL)
    @ResponseBody
    public BaseDto setMenuUrl(Menu menu) {
        return weixinMessageService.setMenuUrl(menu);
    }

    /**
     * 进入测试页面
    * @author 高国藩
    * @date 2015年8月11日 上午11:27:30
    * @return 返回页面
     * @throws IOException 
     */
    @RequestMapping(value = "/wechat/test")
    public ModelAndView test(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView view = new ModelAndView("wechat/test");
        view.addObject("userName", "spring");
        return view;
    }

    /**
     * 进入上传测试页面
    * @author 高国藩
    * @date 2015年8月11日 上午11:27:45
    * @return 返回页面
     */
    @RequestMapping(value = "/wechat/upload")
    @ResponseBody
    public Menu uploadtest(String menuName) {
        return new Menu(menuName, "click", "http://www.baidu.com", 0, 1);
    }

}
