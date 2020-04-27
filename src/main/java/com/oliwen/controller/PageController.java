package com.oliwen.controller;

import com.oliwen.base.BaseController;
import com.oliwen.entity.PageData;
import com.oliwen.interceptor.SecurityAnnotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/pages/")
public class PageController extends BaseController {

	@RequestMapping(value="/**")
	@SecurityAnnotation(auth = false)
	public ModelAndView toPages(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView();
		String requestURI = request.getRequestURI().replaceFirst("/pages/", "");
		mv.setViewName(requestURI);
		PageData pd=this.getPageData();
		mv.addObject("pd", pd);
		return mv;
	}
}
