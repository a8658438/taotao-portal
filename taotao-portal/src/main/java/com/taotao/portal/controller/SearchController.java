package com.taotao.portal.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sun.misc.FpUtils;

import com.taotao.portal.pojo.Item;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 搜索服务控制器
 * @author Administrator
 *
 */
@Controller
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String search(@RequestParam("q") String query,@RequestParam(defaultValue="1")Integer page,Model model){
		try {
			query = query!=null?new String(query.getBytes("ISO8859-1"),"UTF-8") : query;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		SearchResult result = searchService.search(query, page);
		//封装前端展示参数
		model.addAttribute("query", query);
		model.addAttribute("page", page);
		model.addAttribute("totalPages", result.getPageCount());
		model.addAttribute("itemList", result.getDataList());
		return "search";
	}
}
