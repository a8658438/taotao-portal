package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.ContentService;

/**
 * 首页controller
 * 
 * @author Administrator
 * 
 */
@Controller
public class IndexController {
	@Autowired
	private ContentService contentService;
	
	@RequestMapping("index")
	public String index(Model model) {
		String data = contentService.getContentList(89L);
		model.addAttribute("ad1", data);
		return "index";
	}
}
