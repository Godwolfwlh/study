package com.basic.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.ObjectVo;
import com.basic.dao.ReceiptHistoryDao;
import com.basic.entity.ReceiptHistoryBean;
import com.common.Page;
import com.util.DateTimeUtile;
import com.util.StringUtils;

/**
 *收款金额管理业务层
 * @author Administrator
 *
 */
@Service
public class ReceiptHistoryService {
	@Resource
	private ReceiptHistoryDao receiptHistoryDao;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	public ReceiptHistoryBean findReceiptById(ObjectVo vo) {
		Object receiptId=vo.getValueKey("receiptId");
		ReceiptHistoryBean bean=new ReceiptHistoryBean();
		if(receiptId!=null && StringUtils.isNotBlank(receiptId.toString())) {
			bean=receiptHistoryDao.findReceiptHistoryById(receiptId.toString());
		}
		return bean;
	}
	
	/**
	 * 根据ID删除
	 * @param roomId
	 * @return
	 */
	@Transactional
	public int deleteReceiptHistoryById(ObjectVo vo) {
		Object ReceiptId=vo.getValueKey("ReceiptId");
		int result=0;
		if(ReceiptId!=null && StringUtils.isNotBlank(ReceiptId.toString())) {
			result=receiptHistoryDao.deleteReceiptHistoryById(ReceiptId.toString());
		}
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public int insertReceiptHistory(ObjectVo vo) throws Exception {
		ReceiptHistoryBean entity=(ReceiptHistoryBean) vo.JsonToBean(ReceiptHistoryBean.class);
		entity.setReceiptId(StringUtils.getUUid());
		entity.setCreateBy("admin");
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy("admin");
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setEnabledFlag(1);
		int result=receiptHistoryDao.insertReceiptHistory(entity);
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public int insertReceiptHistory(ReceiptHistoryBean entity) throws Exception {
		entity.setReceiptId(StringUtils.getUUid());
		entity.setCreateBy("admin");
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy("admin");
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setEnabledFlag(1);
		int result=receiptHistoryDao.insertReceiptHistory(entity);
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public ReceiptHistoryBean insertReceiptRetId(ObjectVo vo) throws Exception {
		ReceiptHistoryBean entity=(ReceiptHistoryBean) vo.JsonToBean(ReceiptHistoryBean.class);
		entity.setReceiptId(StringUtils.getUUid());
		entity.setCreateBy("admin");
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy("admin");
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setOrderFlag(this.getMaxOrderFlag(vo));
		entity.setEnabledFlag(1);
		int cnt=receiptHistoryDao.insertReceiptHistory(entity);
		if(cnt>0) {
			return entity;
		}else {
			return null;
		}
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public int insertReceiptHistorySelective(ObjectVo vo) throws Exception {
		ReceiptHistoryBean bean=(ReceiptHistoryBean) vo.JsonToBean(ReceiptHistoryBean.class);
		bean.setReceiptId(StringUtils.getUUid());
		bean.setCreateBy("admin");
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateBy("admin");
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptHistoryDao.insertReceiptHistorySelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public int updateReceiptHistorySelective(ObjectVo vo) throws Exception {
		ReceiptHistoryBean bean=(ReceiptHistoryBean) vo.JsonToBean(ReceiptHistoryBean.class);
		bean.setUpdateBy("admin");
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptHistoryDao.updateReceiptHistorySelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public int updateReceiptHistorySelective(ReceiptHistoryBean bean) throws Exception {
		bean.setUpdateBy("admin");
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptHistoryDao.updateReceiptHistorySelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public int updateReceiptHistoryById(ObjectVo vo) throws Exception {
		ReceiptHistoryBean bean=(ReceiptHistoryBean) vo.JsonToBean(ReceiptHistoryBean.class);
		bean.setUpdateBy("admin");
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptHistoryDao.updateReceiptHistoryById(bean);
		return result;
		
	}
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public Page findReceiptHistoryByPage(ObjectVo vo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		List<ReceiptHistoryBean> list=receiptHistoryDao.findReceiptHistoryByPage(map);
		Page page=new Page<ReceiptHistoryBean>();
		page.setTotal(this.getReceiptHistoryCount(vo));
		page.setRows(list);
		return page;
	}
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getReceiptHistoryCount(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return receiptHistoryDao.getReceiptHistoryCount(map);
	}
	
	/**
	 * 获得最大序列号
	 * @param roomId
	 * @return
	 */
	public int getMaxOrderFlag(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return receiptHistoryDao.getMaxOrderFlag(map);
	}
}
