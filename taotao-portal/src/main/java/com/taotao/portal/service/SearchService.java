package com.taotao.portal.service;

import com.taotao.portal.pojo.SearchResult;

/**
 * 进行搜索服务
 * @author Administrator
 *
 */
public interface SearchService {
	/**
	 * 按分页和查询条件进行商品查询
	 * @param query
	 * @param page
	 * @return
	 */
	SearchResult search(String query,Integer page);
}
