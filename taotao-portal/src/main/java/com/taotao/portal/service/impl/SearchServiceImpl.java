package com.taotao.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.portal.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * 搜索服务实现类
 * @author Administrator
 *
 */
@Service
public class SearchServiceImpl implements SearchService {
	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult search(String query, Integer page) {
		//封装参数
		Map<String , String> param = new HashMap<String, String>();
		param.put("q", query);
		param.put("page", page+"");
		//调用服务
		String result = HttpClientUtil.doGet(SEARCH_BASE_URL,param);
		//封装参数
		TaotaoResult taotaoResult = TaotaoResult.formatToPojo(result, SearchResult.class);
		if (taotaoResult != null && taotaoResult.getStatus() == 200) {
			return (SearchResult) taotaoResult.getData();
		}
		return null;
	}

}
