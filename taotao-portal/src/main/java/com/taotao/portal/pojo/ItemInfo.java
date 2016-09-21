package com.taotao.portal.pojo;

import org.apache.commons.lang3.StringUtils;

import com.taotao.pojo.TbItem;

/**
 * 商品实体扩展
 * @author Administrator
 *
 */
public class ItemInfo extends TbItem{
	public String[] getImages(){
		String image = getImage();
		if (StringUtils.isNotEmpty(image)) {
			return image.split(",");
		}
		return null;
	}
}
