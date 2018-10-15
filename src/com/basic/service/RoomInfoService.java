package com.basic.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.ObjectVo;
import com.basic.dao.RoomInfoDao;
import com.basic.entity.RoomInfoBean;
import com.common.Page;
import com.util.DateTimeUtile;
import com.util.StringUtils;

@Service
public class RoomInfoService {
	@Resource
	private RoomInfoDao roomInfoDao;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	public RoomInfoBean findById(ObjectVo vo) {
		Object roomId=vo.getValueKey("roomId");
		RoomInfoBean bean=new RoomInfoBean();
		if(roomId!=null && StringUtils.isNotBlank(roomId.toString())) {
			bean=roomInfoDao.findRoomById(roomId.toString());
		}
		return bean;
	}
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	public RoomInfoBean findById(String roomId) {
		RoomInfoBean bean=new RoomInfoBean();
		if(roomId!=null && StringUtils.isNotBlank(roomId.toString())) {
			bean=roomInfoDao.findRoomById(roomId.toString());
		}
		return bean;
	}
	
	/**
	 * 根据ID删除
	 * @param roomId
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteById(ObjectVo vo) {
		Object roomId=vo.getValueKey("roomId");
		int result=0;
		if(roomId!=null && StringUtils.isNotBlank(roomId.toString())) {
			result=roomInfoDao.deleteRoomById(roomId.toString());
		}
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertRoomInfo(ObjectVo vo) throws Exception {
		RoomInfoBean entity=(RoomInfoBean) vo.JsonToBean(RoomInfoBean.class);
		entity.setRoomId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=roomInfoDao.insertRoom(entity);
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertSelective(ObjectVo vo) throws Exception {
		RoomInfoBean bean=(RoomInfoBean) vo.JsonToBean(RoomInfoBean.class);
		bean.setRoomId(StringUtils.getUUid());
		bean.setCreateBy(vo.getCurrentUser().getStaffId());
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		bean.setEnabledFlag(1);
		int result=roomInfoDao.insertRoomSelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateSelective(ObjectVo vo) throws Exception {
		RoomInfoBean bean=(RoomInfoBean) vo.JsonToBean(RoomInfoBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=roomInfoDao.updateRoomSelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateSelective(RoomInfoBean bean) throws Exception {
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=roomInfoDao.updateRoomSelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateById(ObjectVo vo) throws Exception {
		RoomInfoBean bean=(RoomInfoBean) vo.JsonToBean(RoomInfoBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=roomInfoDao.updateRoomById(bean);
		return result;
		
	}
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public List<RoomInfoBean> findList(ObjectVo vo) {
		Map<String,Object> map=vo.jsonObjectConverMap(vo.getFormValue());
		map.put("pageNow",0);
		//map.put("pageSize",vo.getPageSize());
		map.put("pageSize",60);
		List<RoomInfoBean> list=roomInfoDao.findRoomByPage(map);
		return list;
	}
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public Page findByPage(ObjectVo vo) {
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		List<RoomInfoBean> list=roomInfoDao.findRoomByPage(map);
		Page page=new Page<RoomInfoBean>();
		page.setTotal(this.getCount(vo));
		page.setRows(list);
		return page;
	}
	
	/**
	 * 首页分页查询
	 * @param roomId
	 * @return
	 */
	public Page findByWebPage(ObjectVo vo) {
		Map<String,Object> map=vo.jsonObjectConverMap(vo.getFormValue());
		map.put("pageNow",(StringUtils.parseInt(map.get("pageNow"))-1) * StringUtils.parseInt(map.get("pageSize")));
		map.put("pageSize",StringUtils.parseInt(map.get("pageSize")));
		List<RoomInfoBean> list=roomInfoDao.findRoomByPage(map);
		Page page=new Page<RoomInfoBean>();
		page.setTotal(this.getCount(map));
		page.setRows(list);
		return page;
	}
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getCount(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return roomInfoDao.getCount(map);
	}
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getCount(Map<String,Object> map) {
		return roomInfoDao.getCount(map);
	}
	
	/**
	 * 获得最大序列号
	 * @param roomId
	 * @return
	 */
	public int getMaxOrderFlag(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return roomInfoDao.getMaxOrderFlag(map);
	}
}
