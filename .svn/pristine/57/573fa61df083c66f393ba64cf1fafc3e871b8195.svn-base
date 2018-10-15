package com.basic.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.ObjectVo;
import com.basic.dao.CaseDao;
import com.basic.entity.CaseBean;
import com.common.Page;
import com.util.DateTimeUtile;
import com.util.StringUtils;

@Service
public class CaseService {
	
	@Resource
	private CaseDao caseDao;
	
	/**
	 * 根据ID删除
	 * @param caseId
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
    public int deleteById(ObjectVo vo) throws Exception{
    	Object caseId=vo.getValueKey("caseId");
    	if(caseId!=null && StringUtils.isNotBlank(caseId.toString())){
    		return caseDao.deleteById(caseId.toString());
    	}else{
    		return 0;
    	}
    };
    
    /**
     * 根据ID删除(逻辑删除)
     * @param caseId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int removeById(ObjectVo vo) throws Exception{
    	Object caseId=vo.getValueKey("caseId");
    	if(caseId!=null && StringUtils.isNotBlank(caseId.toString())){
    		return caseDao.removeById(caseId.toString());
    	}else{
    		return 0;
    	}
    };

    /**
     * 插入
     * @param record
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int insertCase(ObjectVo vo) throws Exception{
    	CaseBean caseBean=(CaseBean) vo.JsonToBean(CaseBean.class);
    	caseBean.setCaseId(StringUtils.getUUid());
    	caseBean.setEnabledFlag(1);
    	caseBean.setCreateBy(vo.getCurrentUser().getStaffId());
    	caseBean.setUpdateBy(vo.getCurrentUser().getStaffId());
    	caseBean.setCreateDate(DateTimeUtile.getTimesTmp());
    	caseBean.setUpdateDate(DateTimeUtile.getTimesTmp());
    	return caseDao.insertCase(caseBean);
    };
    
    /**
     * 插入
     * @param record
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int insertCase(CaseBean caseBean) throws Exception{
    	caseBean.setCaseId(StringUtils.getUUid());
    	caseBean.setEnabledFlag(1);
    	caseBean.setCreateDate(DateTimeUtile.getTimesTmp());
    	caseBean.setUpdateDate(DateTimeUtile.getTimesTmp());
    	return caseDao.insertCase(caseBean);
    };

    /**
     * 活动插入
     * @param record
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int insertSelective(ObjectVo vo) throws Exception{
    	CaseBean caseBean=(CaseBean) vo.JsonToBean(CaseBean.class);
    	caseBean.setCaseId(StringUtils.getUUid());
    	caseBean.setEnabledFlag(1);
    	caseBean.setCreateBy(vo.getCurrentUser().getStaffId());
    	caseBean.setUpdateBy(vo.getCurrentUser().getStaffId());
    	caseBean.setCreateDate(DateTimeUtile.getTimesTmp());
    	caseBean.setUpdateDate(DateTimeUtile.getTimesTmp());
    	return caseDao.insertSelective(caseBean);
    };
    
    /**
     * 活动插入
     * @param record
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int insertSelective(CaseBean caseBean) throws Exception{
    	caseBean.setCaseId(StringUtils.getUUid());
    	caseBean.setEnabledFlag(1);
    	caseBean.setCreateDate(DateTimeUtile.getTimesTmp());
    	caseBean.setUpdateDate(DateTimeUtile.getTimesTmp());
    	return caseDao.insertSelective(caseBean);
    };

    /**
     * 根据ID查询
     * @param caseId
     * @return
     */
    public CaseBean selectById(ObjectVo vo){
    	Object caseId=vo.getValueKey("caseId");
    	if(caseId!=null && StringUtils.isNotBlank(caseId.toString())){
    		return caseDao.selectById(caseId.toString());
    	}else{
    		return new CaseBean();
    	}
    };

    /**
     * 活动更新
     * @param record
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int updateByIdSelective(ObjectVo vo) throws Exception{
    	CaseBean caseBean=(CaseBean) vo.JsonToBean(CaseBean.class);
    	caseBean.setEnabledFlag(1);
    	caseBean.setUpdateDate(DateTimeUtile.getTimesTmp());
    	return caseDao.updateByIdSelective(caseBean);
    };

    /**
     * 根据ID修改
     * @param record
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public int updateById(ObjectVo vo) throws Exception{
    	CaseBean caseBean=(CaseBean) vo.JsonToBean(CaseBean.class);
    	caseBean.setEnabledFlag(1);
    	caseBean.setUpdateDate(DateTimeUtile.getTimesTmp());
    	return caseDao.updateById(caseBean);
    };
    
    /**
     * 分页查询
     * @param caseId
     * @return
     */
    public Page<CaseBean> findByPage(ObjectVo vo){
    	Map<String,Object> map=vo.getParameterMap();
    	map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
    	List<CaseBean> list=caseDao.findByPage(map);
    	Page<CaseBean> page=new Page<CaseBean>();
    	page.setTotal(this.getResultCount(map));
    	page.setRows(list);
		return page;
    };
    
    /**
     * 获得分页总条数
     * @param map
     * @return
     */
    public int getResultCount(Map<String,Object> map){
    	return caseDao.getResultCount(map);
    };
    
    /**
     * 获得序列号
     * @param map
     * @return
     */
    public int getMaxOrderFlag(ObjectVo vo){
    	Map<String,Object> map=vo.jsonObjectConverMap(vo.getFormValue());
    	return caseDao.getMaxOrderFlag(map);
    };
}
