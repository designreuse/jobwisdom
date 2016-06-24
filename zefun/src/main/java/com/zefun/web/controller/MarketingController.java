package com.zefun.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zefun.common.consts.Url;
import com.zefun.common.consts.View;

/**
 * 员工奖惩记录控制器
 * @author lzc
 *
 */
@Controller
public class MarketingController extends BaseController {
	
	/**
	 * 微砍价
	* @author 老王
	* @date 2016年6月13日 下午1:52:06 
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.Marketing.VIEW_SHOW_MIN_BARGAIN, method = RequestMethod.GET)
	public ModelAndView showMinBargain () {
	    ModelAndView mav = new ModelAndView(View.Marketing.SHOW_MIN_BARGAIN);
		return mav;
	}
	
	/**
	 * 大转盘
	* @author 老王
	* @date 2016年6月13日 下午1:52:06 
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.Marketing.VIEW_SHOW_BIG_TURNTABLE, method = RequestMethod.GET)
	public ModelAndView showBigTurntable () {
		ModelAndView mav = new ModelAndView(View.Marketing.SHOW_BIG_TURNTABLE);
		return mav;
	}
	
	/**
	 * 大转盘
	* @author 老王
	* @date 2016年6月13日 下午1:52:06 
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.Marketing.VIEW_SHOW_LANTERN, method = RequestMethod.GET)
	public ModelAndView showLantern () {
		ModelAndView mav = new ModelAndView(View.Marketing.SHOW_LANTERN);
		return mav;
	}
	
	/**
	 * 大转盘
	* @author 老王
	* @date 2016年6月13日 下午1:52:06 
	* @return ModelAndView
	 */
	@RequestMapping(value = Url.Marketing.VIEW_SHOW_MIN_VOTE, method = RequestMethod.GET)
	public ModelAndView showMinVote () {
		ModelAndView mav = new ModelAndView(View.Marketing.SHOW_MIN_VOTE);
		return mav;
	}
}
