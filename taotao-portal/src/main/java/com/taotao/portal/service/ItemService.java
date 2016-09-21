package com.taotao.portal.service;

import com.taotao.portal.pojo.ItemInfo;

/**
 * 商品内容管理service
 * @author Administrator
 *
 */
public interface ItemService {
	/**
	 * 通过商品ID查询商品的基本信息
	 * @param itemId
	 * @return
	 */
	ItemInfo getItemById(Long itemId);
	/**
	 * 通过商品ID查询商品的详情信息
	 * @param itemId
	 * @return
	 */
	String getItemDescById(Long itemId);
	/**
	 * 通过商品ID查询商品的参数信息
	 * @param itemId
	 * @return
	 */
	String getItemParamById(Long itemId);
}
