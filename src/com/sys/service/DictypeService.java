package com.sys.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.ObjectVo;
import com.common.Page;
import com.sys.dao.DictypeMapper;
import com.sys.entity.DictypeBean;
import com.util.DateTimeUtile;
import com.util.StringUtils;
@Service
public class DictypeService {
	
	@Resource
	private DictypeMapper dictypeMapper;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int addDictype(ObjectVo vo) throws Exception{
		DictypeBean entity=(DictypeBean) vo.JsonToBean(DictypeBean.class);
		entity.setTypeId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return dictypeMapper.addDictype(entity);
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateDictype(ObjectVo vo) throws Exception{
		DictypeBean entity=(DictypeBean) vo.JsonToBean(DictypeBean.class);
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return dictypeMapper.updateDictype(entity);
	}
	
	/**
	 * 物理删除
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteDictype(ObjectVo vo) throws Exception{
		Object typeId=vo.getFormValue().get("typeId");
		if(typeId!=null && StringUtils.isNotBlank(typeId.toString())) {
			return dictypeMapper.deleteDictype(typeId.toString());
		}else {
			return -1;
		}
	}
	
	/**
	 * 逻辑删除
	 * @param entity
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int removeDictype(ObjectVo vo) throws Exception{
		Object typeId=vo.getFormValue().get("typeId");
		if(typeId!=null && StringUtils.isNotBlank(typeId.toString())) {
			return dictypeMapper.removeDictype(typeId.toString());
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
	public Page findDictypeByPage(ObjectVo vo) throws Exception{
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",((vo.getPage()-1)*vo.getPageSize()));
		map.put("pageSize",vo.getPageSize());
		List<DictypeBean> list=dictypeMapper.findDictypeByPage(map);
		Page<DictypeBean> page=new Page<DictypeBean>();
		page.setRows(list);
		page.setTotal(this.getCount(map));
		return page;
	}
	
	/**
	 * 查询所有
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<DictypeBean> findDictypeList(ObjectVo vo){
		Map<String,Object> map=new HashMap<String,Object>();
		List<DictypeBean> list=dictypeMapper.findDictypeByList(map);
		return list;
	}
	
	/**
	 * 根据ID查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public DictypeBean findByDictypeId(ObjectVo vo) throws Exception{
		Object typeId=vo.getFormValue().get("typeId");
		if(typeId!=null && StringUtils.isNotBlank(typeId.toString())) {
			return dictypeMapper.findByDictypeId(typeId.toString());
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
		return dictypeMapper.getCount(map);
	}
	
	/**
	 * 获得最大序列号
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public int getMaxOrderFlag(){
		return dictypeMapper.getMaxOrderFlag();
	}
}
