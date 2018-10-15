package com.basic.dao;

import java.util.List;
import java.util.Map;

import com.basic.entity.ReceiptHistoryBean;

public interface ReceiptHistoryDao {
	/**
	 * 根据ID删除
	 * @param custId
	 * @return
	 */
    public int deleteReceiptHistoryById(String receiptId);

    /**
     * 新增
     * @param record
     * @return
     */
    public int insertReceiptHistory(ReceiptHistoryBean bean);

    /**
     * 动态新增
     * @param record
     * @return
     */
    public int insertReceiptHistorySelective(ReceiptHistoryBean bean);

    /**
     * 根据ID查询
     * @param custId
     * @return
     */
    public ReceiptHistoryBean findReceiptHistoryById(String receiptId);

    /**
     * 动态修改
     * @param record
     * @return
     */
    public int updateReceiptHistorySelective(ReceiptHistoryBean bean);

    /**
     * 根据ID修改
     * @param record
     * @return
     */
    public int updateReceiptHistoryById(ReceiptHistoryBean bean);
    
    /**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public List<ReceiptHistoryBean> findReceiptHistoryByPage(Map<String,Object> map);
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getReceiptHistoryCount(Map<String,Object> map);
	
	/**
	 * 获得最大序列号
	 * @param roomId
	 * @return
	 */
	public int getMaxOrderFlag(Map<String,Object> map);
}