package com.sys.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.ObjectVo;
import com.common.Page;
import com.sys.dao.MenuTypeMapper;
import com.sys.entity.MenuTypeBean;
import com.util.DateTimeUtile;
import com.util.StringUtils;

@Service
public class MenuTypeService {
	@Resource
	private MenuTypeMapper menuTypeMapper;
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 */
	@Transactional
	public int addMenuType(MenuTypeBean entity) throws Exception{
		entity.setTypeId(StringUtils.getUUid());
		entity.setCreateBy("admin");
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy("admin");
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return menuTypeMapper.addMenuType(entity);
	}
	
	/**
	 * 修改
	 * @param entity
	 * @return
	 */
	@Transactional
	public int updateMenuType(MenuTypeBean entity) throws Exception{
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy("admin");
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return menuTypeMapper.updateMenuType(entity);
	}
	
	/**
	 * 删除
	 * @param entity
	 * @return
	 */
	@Transactional
	public int deleteMenuType(MenuTypeBean entity) throws Exception{
		entity.setUpdateBy("admin");
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		return menuTypeMapper.deleteMenuType(entity);
	}
	
	/**
	 * 分页查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("rawtypes")
	public Page findMenuTypeByList(Map<String,Object> map) throws Exception{
		Page page=new Page();
		page.setPageNow(Integer.parseInt(map.get("page").toString()));
		page.setPageSize(Integer.parseInt(map.get("rows").toString()));
		List<MenuTypeBean> list=menuTypeMapper.findMenuTypeByList(page,map);
		page.setRows(list);
		page.setTotal(this.getCount(map));
		return page;
	}
	
	/**
	 * 根据ID查询
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public MenuTypeBean findById(ObjectVo vo) throws Exception{
		MenuTypeBean bean=(MenuTypeBean) vo.JsonToBean(MenuTypeBean.class);
		if(bean!=null && StringUtils.isNotBlank(bean.getTypeId())) {
			bean=menuTypeMapper.findById(bean.getTypeId());
			return bean;
		}else {
			return null;
		}
	}
	
	 /**
	  * 查询所有                                                                                                          
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public List<MenuTypeBean> findByList(ObjectVo vo) throws Exception{
		return menuTypeMapper.findByList();
	}
	
	/**
	 * 新增
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	public int getCount(Map<String,Object> map) throws Exception{
		return menuTypeMapper.getCount(map);
	}
	
	
}
