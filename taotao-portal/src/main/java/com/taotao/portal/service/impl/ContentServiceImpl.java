package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.portal.service.ContentService;

@Service
public class ContentServiceImpl implements ContentService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${INDEX_AD_URL}")
	private String INDEX_AD_URL;
	
	@Override
	public String getContentList(Long id) {
		//调用接口获取数据
		String data = HttpClientUtil.doGet(REST_BASE_URL + INDEX_AD_URL + id);
		TaotaoResult taotaoResult = TaotaoResult.formatToList(data, TbContent.class);
		
		//接口调用成功
		if (taotaoResult.getStatus() == 200) {
			List<TbContent> list = (List<TbContent>) taotaoResult.getData();
			//转换数据为前端所需要的格式
			List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
			for (TbContent content : list) {
				Map<String, Object> map =new HashMap<String, Object>();
				map.put("srcB", content.getPic2());
				map.put("height", 240);
				map.put("alt", content.getTitleDesc());
				map.put("width", 670);
				map.put("src", content.getPic());
				map.put("widthB", 550);
				map.put("href", content.getUrl());
				map.put("heightB", 240);
				
				result.add(map);
			}
			return JsonUtils.objectToJson(result);
		}
		return null;
	}

}
