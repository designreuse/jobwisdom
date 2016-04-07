package com.lives.platform.web.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lives.platform.common.consts.Url;
import com.lives.platform.common.consts.View;
@Controller
public class IndexController {

	/**
	 * 进入登录页面页面
	* @author 洪秋霞
	* @date 2015年6月9日 下午2:18:35
	* @param model
	* @return
	 */
	@RequestMapping(value = Url.Index.TOLOGINPAGE)
	public ModelAndView toLoginPage(ModelAndView model){
		model.setViewName(View.Index.LOGINPAGE);
		return model;
	}
	/**
	 * 进入主页面
	* @author 洪秋霞
	* @date 2015年6月10日 下午2:07:37
	* @param model
	* @return
	 */
	@RequestMapping(value = Url.Index.TOINDEXPAGE)
	public ModelAndView toIndexPage(ModelAndView model){
		model.setViewName(View.Index.INDEXPAGE);
		return model;
	}
}
