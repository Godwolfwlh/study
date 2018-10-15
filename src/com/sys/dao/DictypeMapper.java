package com.sys.dao;

import java.util.List;
import java.util.Map;

import com.sys.entity.DictypeBean;
/**
 * 字典分类接口类
 * @author Administrator
 *
 */
public interface DictypeMapper {
	//新增
	public int addDictype(DictypeBean entity);
	
	//修改
	public int updateDictype(DictypeBean entity);
	
	//物理删除
	public int removeDictype(String typeId);
	
	//删除
	public int deleteDictype(String typeId);
	
	//根据ID查找
	public DictypeBean findByDictypeId(String typeId);
	
	//查询所有
	public List<DictypeBean> findDictypeByPage(Map<String,Object> map);
	
	public List<DictypeBean> findDictypeByList(Map<String,Object> map);
	
	//获得条数
	public int getCount(Map<String,Object> map);
	
	//获得最大序列号
	public int getMaxOrderFlag();
	
}
