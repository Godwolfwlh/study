package com.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.ObjectVo;
import com.common.Page;
import com.sys.dao.DictionaryMapper;
import com.sys.entity.DictionaryBean;
import com.util.DateTimeUtile;
import com.util.StringUtils;

import net.sf.json.JSONArray;
@Service
public class DictionaryService {
	
	@Resource
	private DictionaryMapper dictionaryMapper;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addDictionary(ObjectVo vo) throws Exception{
		DictionaryBean entity=(DictionaryBean) vo.JsonToBean(DictionaryBean.class);
		entity.setDicId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return dictionaryMapper.addDictionary(entity);
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateDictionary(ObjectVo vo) throws Exception{
		DictionaryBean entity=(DictionaryBean) vo.JsonToBean(DictionaryBean.class);
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return dictionaryMapper.updateDictionary(entity);
	}
	
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteDictionary(ObjectVo vo) throws Exception{
		Object dicId=vo.getFormValue().get("dicId");
		if(dicId!=null && StringUtils.isNotBlank(dicId.toString())) {
			return dictionaryMapper.deleteDictionary(dicId.toString());
		}else {
			return -1;
		}
	}
	
	/**
	 * 分页查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Page findDictionaryByPage(ObjectVo vo){
		Page page=new Page<DictionaryBean>();
		try {
			Map<String,Object> map=vo.getParameterMap();
			map.put("pageNow",((vo.getPage()-1)*vo.getPageSize()));
			map.put("pageSize",vo.getPageSize());
			List<DictionaryBean> list=dictionaryMapper.findDictionaryByPage(map);
			page.setRows(list);
			page.setTotal(this.getCount(map));
			return page;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return page;
		}
	}
	
	/**
	 * 查询所有
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<DictionaryBean> findDictionaryList(ObjectVo vo){
		Map<String,Object> map=vo.getParameterMap();
		List<DictionaryBean> list=dictionaryMapper.findDictionaryByList(map);
		return list;
	}
	
	/**
	 * 根据分类编码查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public Map<String,Object> findByTypeCode(ObjectVo vo){
		Map<String,Object> mapVal=new HashMap<String,Object>();
		Object typeCode=vo.getFormValue().get("typeCode");
		if(typeCode!=null && StringUtils.isNotBlank(typeCode.toString())) {
			String[] str=typeCode.toString().split(",");
			Map<String,Object> map=new HashMap<String,Object>();
			for(int i=0;i<str.length;i++) {
				map.put("typeCode", str[i]);
				List<DictionaryBean> list=dictionaryMapper.findByTypeCode(map);
				if(list.size()>0 && list!=null) {
					mapVal.put(str[i],JSONArray.fromObject(list));
				}
			}
		}
		return mapVal;
	}
	
	/**
	 * 根据ID查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public DictionaryBean findByDictionaryId(ObjectVo vo) throws Exception{
		Object dicId=vo.getFormValue().get("dicId");
		if(dicId!=null && StringUtils.isNotBlank(dicId.toString())) {
			return dictionaryMapper.findByDictionaryId(dicId.toString());
		}else {
			return null;
		}
	}
	
	/**
	 * 获得条数
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public int getCount(Map<String,Object> map) throws Exception{
		return dictionaryMapper.getCount(map);
	}
	
	/**
	 * 获得最大序列号
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public int getMaxOrderFlag(){
		return dictionaryMapper.getMaxOrderFlag();
	}
}
