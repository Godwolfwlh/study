package com.basic.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.basic.dao.CustomerDao;
import com.basic.entity.CustomerBean;
import com.common.Page;
import com.util.DateTimeUtile;
import com.util.StringUtils;

/**
 * 客户管理业务层
 * @author Administrator
 *
 */
@Service
public class CustomerService {
	@Resource
	private CustomerDao customerDao;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	public CustomerBean findCustById(ObjectVo vo) {
		Object custId=vo.getValueKey("custId");
		CustomerBean bean=new CustomerBean();
		if(custId!=null && StringUtils.isNotBlank(custId.toString())) {
			bean=customerDao.findCustById(custId.toString());
		}
		return bean;
	}
	
	/**
	 * 根据ID删除
	 * @param roomId
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteCustById(ObjectVo vo) {
		Object custId=vo.getValueKey("custId");
		int result=0;
		if(custId!=null && StringUtils.isNotBlank(custId.toString())) {
			result=customerDao.deleteCustById(custId.toString());
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
	public int insertCust(ObjectVo vo) throws Exception {
		CustomerBean entity=(CustomerBean) vo.JsonToBean(CustomerBean.class);
		entity.setCustId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setCustState("1"); //客户状态：1.正常，2，预定 ,3.入住
		int result=customerDao.insertCust(entity);
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public CustomerBean insertCustRetId(ObjectVo vo) throws Exception {
		CustomerBean entity=(CustomerBean) vo.JsonToBean(CustomerBean.class);
		entity.setCustId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setOrderFlag(this.getCustMaxOrderFlag(vo));
		entity.setEnabledFlag(1);
		entity.setIsLeaguer("0");
		entity.setLeagScore(0);
		entity.setRemark("其他模块新增");
		int cnt=customerDao.insertCust(entity);
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
	public int insertCustSelective(ObjectVo vo) throws Exception {
		CustomerBean bean=(CustomerBean) vo.JsonToBean(CustomerBean.class);
		bean.setCustId(StringUtils.getUUid());
		bean.setCreateBy(vo.getCurrentUser().getStaffId());
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		bean.setEnabledFlag(1);
		int result=customerDao.insertCustSelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateCustSelective(ObjectVo vo) throws Exception {
		CustomerBean bean=(CustomerBean) vo.JsonToBean(CustomerBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=customerDao.updateCustSelective(bean);
		return result;
	}
	
	/**
	 * 修改传入实体对象
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateCustSelective(CustomerBean bean) throws Exception {
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=customerDao.updateCustSelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateCustById(ObjectVo vo) throws Exception {
		CustomerBean bean=(CustomerBean) vo.JsonToBean(CustomerBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=customerDao.updateCustById(bean);
		return result;
		
	}
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public Page findCustByPage(ObjectVo vo) {
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		map.put("param1",StringUtils.getLikeKeywordLeft(map.get("param1")));
		List<CustomerBean> list=customerDao.findCustByPage(map);
		Page page=new Page<CustomerBean>();
		page.setTotal(this.getCustCount(vo));
		page.setRows(list);
		return page;
	}
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getCustCount(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return customerDao.getCustCount(map);
	}
	
	/**
	 * 获得最大序列号
	 * @param roomId
	 * @return
	 */
	public int getCustMaxOrderFlag(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return customerDao.getCustMaxOrderFlag(map);
	}
}
