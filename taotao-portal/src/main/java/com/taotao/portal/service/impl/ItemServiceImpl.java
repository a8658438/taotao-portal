package com.taotao.portal.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.HttpClientUtil;
import com.taotao.common.utils.JsonUtils;
import com.taotao.common.utils.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.ItemInfo;
import com.taotao.portal.service.ItemService;

/**
 * 商品内容管理实现类
 * @author Administrator
 *
 */
@Service
public class ItemServiceImpl implements ItemService {
	@Value("${REST_BASE_URL}")
	private String REST_BASE_URL;
	@Value("${REST_ITEM_BASE_URL}")
	private String REST_ITEM_BASE_URL;
	@Value("${REST_ITEM_DESC_URL}")
	private String REST_ITEM_DESC_URL;
	@Value("${REST_ITEM_PARAM_URL}")
	private String REST_ITEM_PARAM_URL;
	
	@Override
	public ItemInfo getItemById(Long itemId) {
		Object obj = HttpGetItemInfo(REST_BASE_URL+REST_ITEM_BASE_URL+itemId,ItemInfo.class,false);
		return obj == null ? null : (ItemInfo)obj;
	}

	@Override
	public String getItemDescById(Long itemId) {
		Object obj = HttpGetItemInfo(REST_BASE_URL + REST_ITEM_DESC_URL +itemId,TbItemDesc.class,false);
		TbItemDesc desc = (TbItemDesc)obj;
		return obj == null ? null : desc.getItemDesc();
	}

	/**
	 * 调用rest服务
	 * @param <T>
	 * @param url
	 * @return 
	 */
	private Object HttpGetItemInfo(String url,Class clazz,Boolean isList){
		try {
			String json = HttpClientUtil.doGet(url);//调用服务
			if (StringUtils.isNotBlank(json)) {
				//判断是转换为list还是pojo
				TaotaoResult taotaoResult = null;
				if (isList) {
					 taotaoResult = TaotaoResult.formatToList(json, clazz);
				}else{
					 taotaoResult = TaotaoResult.formatToPojo(json, clazz);
				}
				if (taotaoResult.getStatus() == 200) {
					return taotaoResult.getData();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getItemParamById(Long itemId) {
		//调用rest服务
		Object object = HttpGetItemInfo(REST_BASE_URL+REST_ITEM_PARAM_URL+itemId, TbItemParamItem.class,false);
		TbItemParamItem param = object == null ? null : (TbItemParamItem)object;
		
		//拼装参数列表
		return packageParamHtml(param);
	}

	/**
	 * 封装商品参数的html代码
	 * @param param
	 * @return
	 */
	private String packageParamHtml(TbItemParamItem param) {
		if (param == null) 
			return "";
		
		String paramStr = param.getParamData();
		
		//生成html代码
		List<Map> jsonList = JsonUtils.jsonToList(paramStr, Map.class);
		StringBuffer sb =new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for(Map m1:jsonList) {
			//生成组
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
			sb.append("        </tr>\n");
			
			//生成参数键值
			List<Map> list2 = (List<Map>) m1.get("params");
			for(Map m2:list2) {
				sb.append("        <tr>\n");
				sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
				sb.append("            <td>"+m2.get("v")+"</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");

		return sb.toString();
	}

}
