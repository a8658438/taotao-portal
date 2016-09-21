package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

/**
 * 商品内容管理控制器
 * @author Administrator
 *
 */
@Controller
public class ItemController {
	@Autowired
	public ItemService itemService;
	
	/**
	 * 查询商品基本信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping("/item/{itemId}")
	public String getItemById(@PathVariable Long itemId,Model model) {
		ItemInfo itemInfo = itemService.getItemById(itemId);
		model.addAttribute("item", itemInfo);
		return "item";
	}
	
	/**
	 * 查询商品描述信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/desc/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemDescById(@PathVariable Long itemId) {
		return itemService.getItemDescById(itemId);
	}
	
	/**
	 * 查询商品参数信息
	 * @param itemId
	 * @return
	 */
	@RequestMapping(value="/item/param/{itemId}",produces=MediaType.TEXT_HTML_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemParamById(@PathVariable Long itemId) {
		return itemService.getItemParamById(itemId);
	}
}
