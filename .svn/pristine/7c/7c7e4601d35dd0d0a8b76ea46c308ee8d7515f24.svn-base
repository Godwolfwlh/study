package com.basic.dao;

import java.util.List;
import java.util.Map;

import com.basic.entity.OrderBean;
/**
 * 订单
 * @author Administrator
 *
 */
public interface OrderDao {
	
	/**
	 * 物理删除
	 * @param orderId
	 * @return
	 * @throws Exception
	 */
    public int deleteById(String orderId) throws Exception;
    
    /**
     * 逻辑删除
     * @param orderId
     * @return
     * @throws Exception
     */
    public int removeById(String orderId) throws Exception;

    /**
     * 插入
     * @param bean
     * @return
     * @throws Exception
     */
    public int insertOrder(OrderBean bean) throws Exception;

    /**
     * 动态插入
     * @param bean
     * @return
     * @throws Exception
     */
    public int insertSelective(OrderBean bean) throws Exception;

    /**
     * 根据ID查询
     * @param orderId
     * @return
     */
    public OrderBean findById(String orderId);

    /**
     * 动态修改
     * @param bean
     * @return
     * @throws Exception
     */
    public int updateByIdSelective(OrderBean bean) throws Exception;

    /**
     * 根据ID修改
     * @param bean
     * @return
     * @throws Exception
     */
    public int updateOrder(OrderBean bean) throws Exception;
    
    /**
     * 分页查询
     * @param map
     * @return
     */
    public List<OrderBean> findByPage(Map<String,Object> map);
    
    /**
     * 集合查询
     * @param map
     * @return
     */
    public List<OrderBean> findByList(Map<String,Object> map);
	
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