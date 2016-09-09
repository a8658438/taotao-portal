package com.taotao.portal.service;

/**
 * 调用内容管理的接口service
 * @author Administrator
 *
 */
public interface ContentService {
	/**
	 * 调用rest服务获取内容管理
	 * @param id 分类id
	 * @return
	 */
	String getContentList(Long id);
}
