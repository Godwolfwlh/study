package com.basic.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.basic.dao.OccupancyDao;
import com.basic.dao.OrderDao;
import com.basic.entity.CaseBean;
import com.basic.entity.CustomerBean;
import com.basic.entity.OccupancyBean;
import com.basic.entity.OrderBean;
import com.basic.entity.ReceiptBean;
import com.basic.entity.ReceiptHistoryBean;
import com.basic.entity.RoomInfoBean;
import com.common.Page;
import com.util.DateTimeUtile;
import com.util.StringUtils;


/**
 * 订单管理业务层
 * @author Administrator
 *
 */
@Service
public class OrderService {
	
	@Resource
	private OrderDao orderDao;
	
	@Resource
	private CaseService caseService;
	
	@Resource
	private RoomInfoService roomInfoService;
	
	@Resource
	private CustomerService customerService;
	
	/**
	 * 插入预定、入住
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertOrder(ObjectVo vo) throws Exception {
		OrderBean bean=(OrderBean) vo.JsonToBean(OrderBean.class);
		bean.setOrderId(StringUtils.getUUid());
		bean.setOrderFlag(this.getMaxOrderFlag(vo));
		bean.setCreateBy(vo.getCurrentUser().getStaffId());
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		bean.setEnabledFlag(1);
		
		//判断入住还是预定
		if(StringUtils.isNotBlank(bean.getOrderState()) 
				&& StringUtils.hasLength(bean.getOrderState())) {
			//状态:1：预订， 2：入住，3：换房 4：退房 5,：取消， 6,：完成
			if("1".equals(bean.getOrderState())) {
				//预订
				this.orderYuding(bean);
			}else{
				//入住
				this.orderRuzhu(bean,vo);
			}
			return orderDao.insertOrder(bean);
		}else {
			return -1;
		}
	}
	
	/**
	 * 入住状态需要收取金额
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int orderRuzhu(OrderBean bean,ObjectVo vo) throws Exception {
		int result=0;
		//Map<String, Object>  param=vo.jsonObjectConverMap(vo.getFormValue());
		//改变房间状态
		RoomInfoBean roomInfoBean=new RoomInfoBean();
		roomInfoBean.setRoomId(bean.getRoomId());
		//房间状态：1.空闲、2.预定、3.入住、4.退房、0.停用
		roomInfoBean.setRoomState("3");
		roomInfoBean.setEnabledFlag(1);
		result=result+roomInfoService.updateSelective(roomInfoBean);
		
		//改变客户信息
		CustomerBean customerBean=new CustomerBean();
		customerBean.setCustId(bean.getCustId());
		//客户状态：1.正常，2，预定 ,3.入住
		customerBean.setCustState("3");
		customerBean.setEnabledFlag(1);
		result=result+customerService.updateCustSelective(customerBean);
		
		//收入信息
		Object discount=vo.getValueKey("discount");//折扣
		Object prepaidAmount=vo.getValueKey("prepaidAmount");//应收款
		Object amountCollect=vo.getValueKey("amountCollect");//实收款
		Object paidType=vo.getValueKey("paidType");//付款方式:1,现金 ；2.微信; 3.支付宝; 4.其他
		Object roomNumber=vo.getValueKey("roomNumber");
		Object roomPrice=vo.getValueKey("roomPrice");
		Object deposit=vo.getValueKey("deposit");
		CaseBean caseBean=new CaseBean();
		caseBean.setOrderId(bean.getOrderId());
		caseBean.setCreateBy(vo.getCurrentUser().getStaffId());
		caseBean.setUpdateBy(vo.getCurrentUser().getStaffId());
		caseBean.setDiscount(StringUtils.parseFloat(discount));
		caseBean.setDeposit(StringUtils.parseFloat(deposit));
		caseBean.setPrepaidAmount(StringUtils.parseBigDecimal(prepaidAmount));
		caseBean.setReceiptMoney(StringUtils.parseBigDecimal(amountCollect));
		caseBean.setReceiptDate(DateTimeUtile.getTimesTmp());
		caseBean.setReceiptType(paidType+"");//
		caseBean.setReceiptState(bean.getPayState());//缴费状态
		caseBean.setReceiptName(vo.getCurrentUser().getStaffName());
		caseBean.setReceiptId(vo.getCurrentUser().getStaffId());
		caseBean.setCustId(bean.getCustId());
		caseBean.setRemark(bean.getRemark());
		caseBean.setEnabledFlag(1);
		caseBean.setOrderFlag(caseService.getMaxOrderFlag(vo));
		caseBean.setRoomNumber(roomNumber.toString());
		caseBean.setRoomPrice(StringUtils.parseBigDecimal(roomPrice));
		result=result+caseService.insertSelective(caseBean);
		return result;
	}
	
	/**
	 * 预定状态需要收取金额
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int orderYuding(OrderBean bean) throws Exception {
		int result=0;
		//改变房间状态
		RoomInfoBean roomInfoBean=new RoomInfoBean();
		roomInfoBean.setRoomId(bean.getRoomId());
		//房间状态：1.空闲、2.预定、3.入住、4.退房、0.停用
		roomInfoBean.setRoomState("2");
		roomInfoBean.setEnabledFlag(1);
		result=result+roomInfoService.updateSelective(roomInfoBean);
		
		//改变客户信息
		CustomerBean customerBean=new CustomerBean();
		customerBean.setCustId(bean.getCustId());
		customerBean.setEnabledFlag(1);
		//客户状态：1.正常，2，预定 ,3.入住
		customerBean.setCustState("2");
		result=result+customerService.updateCustSelective(customerBean);
		
		return result;
	}
	
	/**
	 * 插入（无需实体转换）
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertBean(OrderBean bean) throws Exception {
		bean.setOrderId(StringUtils.getUUid());
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		return orderDao.insertOrder(bean);
	}
	
	/**
	 * 动态插入
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertSelective(ObjectVo vo) throws Exception {
		OrderBean bean=(OrderBean) vo.JsonToBean(OrderBean.class);
		bean.setOrderId(StringUtils.getUUid());
		bean.setOrderFlag(this.getMaxOrderFlag(vo));
		bean.setCreateBy(vo.getCurrentUser().getStaffId());
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		return orderDao.insertSelective(bean);
	}
	
	/**
	 * 动态插入(实体)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertSelectiveBean(OrderBean bean) throws Exception {
		bean.setOrderId(StringUtils.getUUid());
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		return orderDao.insertSelective(bean);
	}
	
	/**
	 * 插入
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateOrder(ObjectVo vo) throws Exception {
		OrderBean bean=(OrderBean) vo.JsonToBean(OrderBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		return orderDao.updateOrder(bean);
	}
	
	/**
	 * 插入（无需实体转换）
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateBean(OrderBean bean) throws Exception {
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		return orderDao.updateOrder(bean);
	}
	
	/**
	 * 动态插入
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateByIdSelective(ObjectVo vo) throws Exception {
		OrderBean bean=(OrderBean) vo.JsonToBean(OrderBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		
		/******************************把原来房间变为空闲，把原来客户变为正常********************************/
		OrderBean oentity=this.findById(bean.getOrderId());
		//房间
		if(StringUtils.isNotBlank(bean.getRoomId()) && StringUtils.isNotBlank(bean.getRoomId()) 
				&& !bean.getRoomId().equals(oentity.getRoomId())) {
			//改变房间状态
			RoomInfoBean roomInfoBean=new RoomInfoBean();
			roomInfoBean.setRoomId(oentity.getRoomId());
			//房间状态：1.空闲、2.预定、3.入住、4.退房、0.停用
			roomInfoBean.setRoomState("1");
			roomInfoBean.setEnabledFlag(1);
			roomInfoService.updateSelective(roomInfoBean);
		}
		
		//客户
		if(StringUtils.isNotBlank(bean.getCustId()) && StringUtils.isNotBlank(bean.getCustId()) 
				&& !bean.getCustId().equals(oentity.getCustId())) {
			CustomerBean customerBean=new CustomerBean();
			customerBean.setCustId(oentity.getCustId());
			customerBean.setEnabledFlag(1);
			//客户状态：1.正常，2，预定 ,3.入住
			customerBean.setCustState("1");
			customerService.updateCustSelective(customerBean);
		}
		
		//判断入住还是预定
		if(StringUtils.isNotBlank(bean.getOrderState()) 
				&& StringUtils.hasLength(bean.getOrderState())) {
			//状态:1：预订， 2：入住，3：换房 4：退房 5,：取消， 6,：完成
			if("1".equals(bean.getOrderState())) {
				//预订
				this.orderYuding(bean);
			}else{
				//入住
				this.orderRuzhu(bean,vo);
			}
			return orderDao.updateByIdSelective(bean);
		}else {
			return -1;
		}
	}
	
	/**
	 * 动态插入(实体)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updateByIdSelective(OrderBean bean) throws Exception {
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		return orderDao.updateByIdSelective(bean);
	}
	
	/**
	 * 根据ID删除(物理删除)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deleteById(ObjectVo vo) throws Exception {
		Object orderId=vo.getValueKey("orderId");
		if(orderId!=null && StringUtils.isNotBlank(orderId.toString())) {
			return orderDao.deleteById(orderId.toString());
		}else {
			return 0;
		}
	}
	
	/**
	 * 根据ID删除(逻辑删除)
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@Transactional(rollbackFor=Exception.class)
	public int removeById(ObjectVo vo) throws Exception {
		Object orderId=vo.getValueKey("orderId");
		if(orderId!=null && StringUtils.isNotBlank(orderId.toString())) {
			return orderDao.removeById(orderId.toString());
		}else {
			return 0;
		}
	}
	
	/**
	 * 分页查询
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public Page findByPage(ObjectVo vo){
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		map.put("param0",StringUtils.getLikeKeyword(map.get("param0")));
		map.put("param1",StringUtils.getLikeKeywordLeft(map.get("param1")));
		List<OrderBean> list=this.orderDao.findByPage(map);
		Page<OrderBean> page=new Page<OrderBean>();
		page.setRows(list);
		page.setTotal(this.getPageTotal(vo));
		return page;
	}
	
	/**
	 * 查询list集合
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public List<OrderBean> findByList(ObjectVo vo){
		Map<String,Object> map=vo.jsonObjectConverMap(vo.getFormValue());
		List<OrderBean> list=this.orderDao.findByList(map);
		return list;
	}
	
	/**
	 * 根据ID查询
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public OrderBean findById(ObjectVo vo){
		Object orderId=vo.getValueKey("orderId");
		if(orderId!=null && StringUtils.isNotBlank(orderId.toString())) {
			OrderBean entity=this.orderDao.findById(orderId.toString());
			return entity;
		}else {
			return new OrderBean();
		}
	}
	
	/**
	 * 根据ID查询
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public OrderBean findById(String orderId){
		if(StringUtils.isNotBlank(orderId.toString())) {
			OrderBean entity=this.orderDao.findById(orderId.toString());
			return entity;
		}else {
			return null;
		}
	}
	
	/**
	 * 分页查询根据ID查询
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public OrderBean findByPageById(ObjectVo vo){
		Map<String,Object> map=vo.jsonObjectConverMap(vo.getFormValue());
		List<OrderBean> list=this.orderDao.findByPage(map);
		if(list!=null && list.size()>0) {
			return list.get(0);
		}else {
			return new OrderBean();
		}
	}
	
	/**
	 * 获得总条数
	 * @param vo
	 * @return
	 */
	public int getPageTotal(ObjectVo vo) {
		return orderDao.getCount(vo.getParameterMap());
	}
	
	/**
	 * 获得排序
	 * @param vo
	 * @return
	 */
	public int getMaxOrderFlag(ObjectVo vo) {
		return orderDao.getMaxOrderFlag(vo.jsonObjectConverMap(vo.getFormValue()));
	}
	
}
