package com.basic.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.basic.entity.RoomInfoBean;

public interface RoomInfoDao {
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	public RoomInfoBean findRoomById(String roomId);
	
	/**
	 * 根据ID删除
	 * @param roomId
	 * @return
	 */
	public int deleteRoomById(String roomId);
	
	/**
	 * 逻辑删除
	 * @param roomId
	 * @return
	 */
	public int removeRoomById(String roomId);
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 */
	public int insertRoom(RoomInfoBean bean);
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 */
	public int insertRoomSelective(RoomInfoBean bean);
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 */
	public int updateRoomSelective(RoomInfoBean bean);
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 */
	public int updateRoomById(RoomInfoBean bean);
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public List<RoomInfoBean> findRoomByPage(Map<String,Object> map);
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getCount(Map<String,Object> map);
	
	/**
	 * 获得最大序列号
	 * @param roomId
	 * @return
	 */
	public int getMaxOrderFlag(Map<String,Object> map);
}
