package com.basic.dao;

import java.util.List;
import java.util.Map;

import com.basic.entity.CustomerBean;

/**
 * 接口映射
 * @author Administrator
 *
 */
public interface CustomerDao {
	/**
	 * 根据ID删除
	 * @param custId
	 * @return
	 */
    public int deleteCustById(String custId);

    /**
     * 新增
     * @param record
     * @return
     */
    public int insertCust(CustomerBean record);

    /**
     * 动态新增
     * @param record
     * @return
     */
    public int insertCustSelective(CustomerBean record);

    /**
     * 根据ID查询
     * @param custId
     * @return
     */
    public CustomerBean findCustById(String custId);

    /**
     * 动态修改
     * @param record
     * @return
     */
    public int updateCustSelective(CustomerBean record);

    /**
     * 根据ID修改
     * @param record
     * @return
     */
    public int updateCustById(CustomerBean record);
    
    /**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public List<CustomerBean> findCustByPage(Map<String,Object> map);
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getCustCount(Map<String,Object> map);
	
	/**
	 * 获得最大序列号
	 * @param roomId
	 * @return
	 */
	public int getCustMaxOrderFlag(Map<String,Object> map);
}