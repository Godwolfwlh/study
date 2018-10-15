package com.basic.dao;

import java.util.List;
import java.util.Map;

import com.basic.entity.OccupancyBean;

/**
 * 客户入住接口
 * @author Administrator
 *
 */
public interface OccupancyDao {
	/**
	 * 根据ID删除
	 * @param PancyId
	 * @return
	 */
    public int deletePancyById(String PancyId);
    
    /**
     * 根据ID删除
     * @param PancyId
     * @return
     */
    public int removePancyById(String PancyId);

    /**
     * 新增
     * @param bean
     * @return
     */
    public int insertPancy(OccupancyBean bean);

    /**
     * 动态新增
     * @param bean
     * @return
     */
    public int insertPancySelective(OccupancyBean bean);

    /**
     * 根据ID查询
     * @param PancyId
     * @return
     */
    public OccupancyBean findPancyById(String pancyId);

    /**
     * 动态修改
     * @param bean
     * @return
     */
    public int updatePancySelective(OccupancyBean bean);

    /**
     * 根据ID修改
     * @param bean
     * @return
     */
    public int updatePancyById(OccupancyBean bean);
    
    /**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<OccupancyBean> findPancyByPage(Map<String,Object> map);
	
	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	public List<OccupancyBean> findByPage(Map<String,Object> map);
	
	/**
	 * 获得数量
	 * @param map
	 * @return
	 */
	public int getPancyCount(Map<String,Object> map);
	
	/**
	 * 获得最大序列号
	 * @param map
	 * @return
	 */
	public int getPancyMaxOrderFlag(Map<String,Object> map);
	
	/**
	 * 关联查询
	 * @param map
	 * @return
	 */
	public OccupancyBean queryAssoByPancyId(String pancyId);
}