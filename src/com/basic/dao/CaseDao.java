package com.basic.dao;

import java.util.List;
import java.util.Map;

import com.basic.entity.CaseBean;

public interface CaseDao {
	
	/**
	 * 根据ID删除
	 * @param caseId
	 * @return
	 */
    public int deleteById(String caseId) throws Exception;
    
    /**
     * 根据ID删除
     * @param caseId
     * @return
     */
    public int removeById(String caseId) throws Exception;

    /**
     * 插入
     * @param record
     * @return
     */
    public int insertCase(CaseBean record) throws Exception;

    /**
     * 活动插入
     * @param record
     * @return
     */
    public int insertSelective(CaseBean record) throws Exception;

    /**
     * 根据ID查询
     * @param caseId
     * @return
     */
    public CaseBean selectById(String caseId);

    /**
     * 活动更新
     * @param record
     * @return
     */
    public int updateByIdSelective(CaseBean record) throws Exception;

    /**
     * 根据ID修改
     * @param record
     * @return
     */
    public int updateById(CaseBean record) throws Exception;
    
    /**
     * 分页查询
     * @param caseId
     * @return
     */
    public List<CaseBean> findByPage(Map<String,Object> map);
    
    /**
     * 获得分页总条数
     * @param map
     * @return
     */
    public int getResultCount(Map<String,Object> map);
    
    /**
     * 获得序列号
     * @param map
     * @return
     */
    public int getMaxOrderFlag(Map<String,Object> map);
}