package com.basic.dao;

import java.util.List;
import java.util.Map;

import com.basic.entity.ReceiptBean;

public interface ReceiptDao {
	/**
	 * 根据ID删除
	 * @param custId
	 * @return
	 */
    public int deleteReceiptById(String receiptId);

    /**
     * 新增
     * @param record
     * @return
     */
    public int insertReceipt(ReceiptBean bean);

    /**
     * 动态新增
     * @param record
     * @return
     */
    public int insertReceiptSelective(ReceiptBean bean);

    /**
     * 根据ID查询
     * @param custId
     * @return
     */
    public ReceiptBean findReceiptById(String receiptId);

    /**
     * 动态修改
     * @param record
     * @return
     */
    public int updateByIdSelective(ReceiptBean bean);
    
    /**
     * 根据订单动态修改
     * @param record
     * @return
     */
    public int updateByPancyId(ReceiptBean bean);

    /**
     * 根据ID修改
     * @param record
     * @return
     */
    public int updateReceiptById(ReceiptBean bean);
    
    /**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public List<ReceiptBean> findReceiptByPage(Map<String,Object> map);
	public List<ReceiptBean> findByPage(Map<String,Object> map);
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getReceiptCount(Map<String,Object> map);
	
	/**
	 * 获得最大序列号
	 * @param roomId
	 * @return
	 */
	public int getMaxOrderFlag(Map<String,Object> map);
}