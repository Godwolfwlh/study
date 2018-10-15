package com.basic.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.ObjectVo;
import com.basic.dao.ReceiptDao;
import com.basic.entity.ReceiptBean;
import com.common.Page;
import com.util.DateTimeUtile;
import com.util.StringUtils;

/**
 *收款金额管理业务层
 * @author Administrator
 *
 */
@Service
public class ReceiptService {
	@Resource
	private ReceiptDao receiptDao;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	public ReceiptBean findReceiptById(ObjectVo vo) {
		Object receiptId=vo.getValueKey("receiptId");
		ReceiptBean bean=new ReceiptBean();
		if(receiptId!=null && StringUtils.isNotBlank(receiptId.toString())) {
			bean=receiptDao.findReceiptById(receiptId.toString());
		}
		return bean;
	}
	
	/**
	 * 根据ID删除
	 * @param roomId
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteReceiptById(ObjectVo vo) {
		Object ReceiptId=vo.getValueKey("ReceiptId");
		int result=0;
		if(ReceiptId!=null && StringUtils.isNotBlank(ReceiptId.toString())) {
			result=receiptDao.deleteReceiptById(ReceiptId.toString());
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
	public int insertReceipt(ObjectVo vo) throws Exception {
		ReceiptBean entity=(ReceiptBean) vo.JsonToBean(ReceiptBean.class);
		entity.setReceiptId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setEnabledFlag(1);
		int result=receiptDao.insertReceipt(entity);
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertReceipt(ReceiptBean entity) throws Exception {
		entity.setReceiptId(StringUtils.getUUid());
		//entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		//entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setEnabledFlag(1);
		int result=receiptDao.insertReceipt(entity);
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public ReceiptBean insertReceiptRetId(ObjectVo vo) throws Exception {
		ReceiptBean entity=(ReceiptBean) vo.JsonToBean(ReceiptBean.class);
		entity.setReceiptId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setOrderFlag(this.getMaxOrderFlag(vo));
		entity.setEnabledFlag(1);
		int cnt=receiptDao.insertReceipt(entity);
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
	@Transactional(rollbackFor=Exception.class)
	public int insertReceiptSelective(ObjectVo vo) throws Exception {
		ReceiptBean bean=(ReceiptBean) vo.JsonToBean(ReceiptBean.class);
		bean.setReceiptId(StringUtils.getUUid());
		bean.setCreateBy(vo.getCurrentUser().getStaffId());
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptDao.insertReceiptSelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateReceiptSelective(ObjectVo vo) throws Exception {
		ReceiptBean bean=(ReceiptBean) vo.JsonToBean(ReceiptBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptDao.updateByIdSelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateReceiptSelective(ReceiptBean bean) throws Exception {
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptDao.updateByIdSelective(bean);
		return result;
	}
	
	/**
	 * 根据订单ID修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateByPancyId(ReceiptBean bean) throws Exception {
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptDao.updateByIdSelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateReceiptById(ObjectVo vo) throws Exception {
		ReceiptBean bean=(ReceiptBean) vo.JsonToBean(ReceiptBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptDao.updateReceiptById(bean);
		return result;
		
	}
	
	/**
	 * 根据订单ID修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateReceiptById(ReceiptBean bean) throws Exception {
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=receiptDao.updateReceiptById(bean);
		return result;
		
	}
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public Page findReceiptByPage(ObjectVo vo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		List<ReceiptBean> list=receiptDao.findReceiptByPage(map);
		Page page=new Page<ReceiptBean>();
		page.setTotal(this.getReceiptCount(vo));
		page.setRows(list);
		return page;
	}
	
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public Page findByPage(ObjectVo vo) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		List<ReceiptBean> list=receiptDao.findByPage(map);
		Page page=new Page<ReceiptBean>();
		page.setTotal(this.getReceiptCount(vo));
		page.setRows(list);
		return page;
	}
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getReceiptCount(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return receiptDao.getReceiptCount(map);
	}
	
	/**
	 * 获得最大序列号
	 * @param roomId
	 * @return
	 */
	public int getMaxOrderFlag(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return receiptDao.getMaxOrderFlag(map);
	}
}
