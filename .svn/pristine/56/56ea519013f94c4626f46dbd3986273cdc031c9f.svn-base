package com.sys.dao;

import java.util.List;
import java.util.Map;

import com.sys.entity.DictionaryBean;
/**
 * 字典分类接口类
 * @author Administrator
 *
 */
public interface DictionaryMapper{
	//新增
	public int addDictionary(DictionaryBean entity);
	
	//修改
	public int updateDictionary(DictionaryBean entity);
	
	//逻辑删除
	public int removeDictionary(String dicId);
	
	//物理删除
	public int deleteDictionary(String dicId);
	
	//根据ID查找
	public DictionaryBean findByDictionaryId(String dicId);
	
	//分页查询
	public List<DictionaryBean> findDictionaryByPage(Map<String,Object> map);
	
	//查询所有
	public List<DictionaryBean> findDictionaryByList(Map<String,Object> map);
	
	//根据分类编码查询
	public List<DictionaryBean> findByTypeCode(Map<String,Object> map);
	
	//获得条数
	public int getCount(Map<String,Object> map);
	
	//获得最大序列号
	public int getMaxOrderFlag();
	
}
