package com.basic.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ajax.AjaxResult;
import com.ajax.ObjectVo;
import com.basic.dao.OccupancyDao;
import com.basic.entity.CustomerBean;
import com.basic.entity.OccupancyBean;
import com.basic.entity.ReceiptBean;
import com.basic.entity.ReceiptHistoryBean;
import com.basic.entity.RoomInfoBean;
import com.common.Page;
import com.util.DateTimeUtile;
import com.util.StringUtils;

import net.sf.json.JSONObject;

/**
 * 客户管理业务层
 * @author Administrator
 *
 */
@Service
public class OccupancyService {
	@Resource
	private OccupancyDao occupancyDao;
	
	//账单
	@Resource
	private ReceiptService receiptService;
	
	//账单历史
	@Resource
	private ReceiptHistoryService receiptHistoryService;
	
	//房间信息
	@Resource
	private RoomInfoService roomInfoService;
	
	//客户信息
	@Resource
	private CustomerService customerService;
	
	/**
	 * 根据ID查询
	 * @param roomId
	 * @return
	 */
	public OccupancyBean findPancyById(ObjectVo vo) {
		Object pancyId=vo.getValueKey("pancyId");
		OccupancyBean bean=new OccupancyBean();
		if(pancyId!=null && StringUtils.isNotBlank(pancyId.toString())) {
			bean=occupancyDao.findPancyById(pancyId.toString());
		}
		return bean;
	}
	
	/**
	 * 根据ID删除
	 * @param roomId
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int deletePancyById(ObjectVo vo) {
		Object pancyId=vo.getValueKey("pancyId");
		int result=0;
		if(pancyId!=null && StringUtils.isNotBlank(pancyId.toString())) {
			result=occupancyDao.deletePancyById(pancyId.toString());
		}
		return result;
	}
	
	/**
	 * 根据ID删除
	 * @param roomId
	 * @return
	 */
	@Transactional(rollbackFor=Exception.class)
	public int removePancyById(ObjectVo vo) {
		Object pancyId=vo.getValueKey("pancyId");
		int result=0;
		if(pancyId!=null && StringUtils.isNotBlank(pancyId.toString())) {
			result=occupancyDao.removePancyById(pancyId.toString());
		}
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertPancy(ObjectVo vo) throws Exception{
		int result=0;
		OccupancyBean entity=(OccupancyBean) vo.JsonToBean(OccupancyBean.class);
		entity.setPancyId(StringUtils.getUUid());
		entity.setCreateBy(vo.getCurrentUser().getStaffId());
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy(vo.getCurrentUser().getStaffId());
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setEnabledFlag(1);
		//订单状态: 1：入住,2：预订，3.换房，4：退房,5：取消,0：结束
		//收费状态：1.预收款、2.已收费、3.已退款
		if("2".equals(entity.getPancyState())) {
			entity.setPayState("1");
		}else {
			entity.setPayState("2");
		}
		//预定状态
		if(StringUtils.isNotBlank(entity.getPancyState()) && "2".equals(entity.getPancyState())) {
			//entity.setAmountCollect(new BigDecimal("0"));
			//修改房间状态 预定
			RoomInfoBean roomBean=new RoomInfoBean();
			roomBean.setRoomId(entity.getRoomId());
			roomBean.setRoomState("2");
			roomBean.setEnabledFlag(1);
			roomInfoService.updateSelective(roomBean);
			
			//修改客户状态
			CustomerBean cust=new CustomerBean();
			cust.setCustId(entity.getCustId());
			 //客户状态：1.正常，2，预定 ,3.入住
			cust.setCustState("2");
			cust.setEnabledFlag(1);
			customerService.updateCustSelective(cust);
			
		//入住状态
		}else {
			//入住    把金额插入记录表
			ReceiptBean bean=new ReceiptBean();
			bean.setCreateBy(vo.getCurrentUser().getStaffId());
			bean.setUpdateBy(vo.getCurrentUser().getStaffId());
			bean.setCustId(entity.getCustId());
			bean.setRoomId(entity.getRoomId());
			bean.setDeposit(entity.getDeposit());
			bean.setReceiptMoney(entity.getAmountCollect());
			bean.setOrderFlag(receiptService.getMaxOrderFlag(vo));
			bean.setPancyId(entity.getPancyId());//订单id
			bean.setReceiptDate(DateTimeUtile.getTimesTmp());
			bean.setReceiptState("2");//收费状态：1.预收款、2.已收费、3.已退款
			receiptService.insertReceipt(bean);
			
			
			//金额插入历史记录
			ReceiptHistoryBean history=new ReceiptHistoryBean();
			history.setCustId(entity.getCustId());
			history.setRoomId(entity.getRoomId());
			history.setDeposit(entity.getDeposit());
			history.setReceiptMoney(entity.getAmountCollect());
			history.setOrderFlag(receiptService.getMaxOrderFlag(vo));
			history.setPancyId(entity.getPancyId());
			history.setReceiptDate(DateTimeUtile.getTimesTmp());
			history.setReceiptState("2");//收费状态：1.预收款、2.已收费、3.已退款
			history.setRemark("入住房间");
			receiptHistoryService.insertReceiptHistory(history);
			
			//修改房间状态
			RoomInfoBean roomBean=new RoomInfoBean();
			roomBean.setRoomId(entity.getRoomId());
			roomBean.setRoomState("3");//房间状态：1.空闲、2.预定、3.入住、4.退房、0.停用
			roomBean.setEnabledFlag(1);
			roomBean.setUpdateBy(vo.getCurrentUser().getStaffId());
			roomInfoService.updateSelective(roomBean);
			
			//修改客户状态
			CustomerBean cust=new CustomerBean();
			cust.setCustId(entity.getCustId());
			 //客户状态：1.正常，2，预定 ,3.入住
			cust.setCustState("3");
			cust.setEnabledFlag(1);
			customerService.updateCustSelective(cust);
		}
		entity.setHousePrice(StringUtils.parseBigDecimal(vo.getValueKey("roomPrice")));
		result=occupancyDao.insertPancy(entity);
		return result;
	}
	
	/**
	 * 插入
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int insertPancySelective(ObjectVo vo) throws Exception {
		OccupancyBean bean=(OccupancyBean) vo.JsonToBean(OccupancyBean.class);
		bean.setPancyId(StringUtils.getUUid());
		bean.setCreateBy(vo.getCurrentUser().getStaffId());
		bean.setCreateDate(DateTimeUtile.getTimesTmp());
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=occupancyDao.insertPancySelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updatePancySelective(ObjectVo vo) throws Exception {
		OccupancyBean bean=(OccupancyBean) vo.JsonToBean(OccupancyBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=occupancyDao.updatePancySelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updatePancySelective(OccupancyBean bean) throws Exception {
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		int result=occupancyDao.updatePancySelective(bean);
		return result;
	}
	
	/**
	 * 修改
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public int updatePancyById(ObjectVo vo) throws Exception {
		OccupancyBean bean=(OccupancyBean) vo.JsonToBean(OccupancyBean.class);
		bean.setUpdateBy(vo.getCurrentUser().getStaffId());
		bean.setUpdateDate(DateTimeUtile.getTimesTmp());
		//如果换了房间，原来的房间状态设为空闲
		OccupancyBean ocEntity=occupancyDao.queryAssoByPancyId(bean.getPancyId());
		RoomInfoBean entity=roomInfoService.findById(bean.getRoomId());
		if(entity!=null && ocEntity!=null) {
			if(!entity.getRoomNumber().equals(ocEntity.getRoomInfo().getRoomNumber())) {
				//修改房间状态
				RoomInfoBean roomBean=new RoomInfoBean();
				roomBean.setRoomId(entity.getRoomId());
				roomBean.setRoomState("1");
				roomInfoService.updateSelective(roomBean);
			}
		}
		bean.setHousePrice(StringUtils.parseBigDecimal(vo.getValueKey("roomPrice")));
		int result=occupancyDao.updatePancyById(bean);
		return result;
		
	}
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public Page findPancyByPage(ObjectVo vo) {
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		List<OccupancyBean> list=occupancyDao.findPancyByPage(map);
		Page page=new Page<OccupancyBean>();
		page.setTotal(this.getPancyCount(vo));
		page.setRows(list);
		return page;
	}
	
	/**
	 * 分页查询
	 * @param roomId
	 * @return
	 */
	public Page findByPage(ObjectVo vo) {
		Map<String,Object> map=vo.getParameterMap();
		map.put("pageNow",(vo.getPage()-1) * vo.getPageSize());
		map.put("pageSize",vo.getPageSize());
		map.put("param0",StringUtils.getLikeKeyword(map.get("param0")));
		map.put("param1",StringUtils.getLikeKeywordLeft(map.get("param1")));
		List<OccupancyBean> list=occupancyDao.findByPage(map);
		Page<OccupancyBean> page=new Page<OccupancyBean>();
		page.setTotal(this.getPancyCount(vo));
		page.setRows(list);
		return page;
	}
	
	/**
	 * 获得数量
	 * @param roomId
	 * @return
	 */
	public int getPancyCount(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return occupancyDao.getPancyCount(map);
	}
	
	/**
	 * 获得最大序列号
	 * @param roomId
	 * @return
	 */
	public int getPancyMaxOrderFlag(ObjectVo vo) {
		Map<String, Object> map=vo.getParameterMap();
		return occupancyDao.getPancyMaxOrderFlag(map);
	}
	
	/**
	 * 根据ID查询关联实体
	 * @param roomId
	 * @return
	 */
	public OccupancyBean queryAssoByPancyId(ObjectVo vo) {
		//Map<String, Object> map=vo.getParameterMap();
		//Object pancyId=map.get("pancyId");
		Object pancyId=vo.getValueKey("pancyId");
		if(pancyId!=null && StringUtils.isNotBlank(pancyId.toString())) {
			return occupancyDao.queryAssoByPancyId(pancyId.toString());
		}else {
			return null;
		}
	}
	
	/**
	 * 根据ID查询关联实体
	 * @param roomId
	 * @return
	 */
	public OccupancyBean findCustRoomById(String pancyId) {
		if(StringUtils.isNotBlank(pancyId)) {
			return occupancyDao.queryAssoByPancyId(pancyId);
		}else {
			return null;
		}
	}
	
	/**
	 * 预定转成 、入住房间
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void checkInRoom(ObjectVo vo) throws Exception {
		//订单状态: 1：入住,2：预订，3.换房，4：退房,5：取消,0：结束
		Object pancyId=vo.getValueKey("pancyId2");
		if(pancyId!=null && StringUtils.isNotBlank(pancyId.toString())) {
			OccupancyBean entity=this.findCustRoomById(pancyId.toString());
			Object roomNumber=vo.getValueKey("roomNumber2");
			if(entity!=null && roomNumber!=null && StringUtils.isNotBlank(roomNumber.toString())) {
				//为选房间
				//if(roomNumber.equals(entity.getRoomInfo().getRoomNumber())) {
					//修订单状态
					//OccupancyBean bean=new OccupancyBean();
					entity.setPancyId(pancyId.toString());
					entity.setPancyState("1");//入住
					entity.setPayState("2");//已收费
					entity.setUpdateBy(vo.getCurrentUser().getStaffId());
					entity.setUpdateDate(DateTimeUtile.getTimesTmp());
					entity.setPaidType(vo.getValueKey("paidType2")+"");
					entity.setEnabledFlag(1);
					entity.setHousePrice(StringUtils.parseBigDecimal(vo.getValueKey("roomPrice")));
					occupancyDao.updatePancySelective(entity);
					
					//修改房间状态
					Object roomId2=vo.getValueKey("roomId2");
					if(roomId2!=null && StringUtils.isNotBlank(roomId2.toString())) {
						RoomInfoBean roomBean=new RoomInfoBean();
						roomBean.setRoomId(roomId2.toString());
						roomBean.setUpdateBy(vo.getCurrentUser().getStaffId());
						roomBean.setUpdateDate(DateTimeUtile.getTimesTmp());
						roomBean.setRoomState("3");//入住
						roomBean.setEnabledFlag(1);
						roomInfoService.updateSelective(roomBean);
					}
					
					//入住    把金额插入记录表
					Object custId2=vo.getValueKey("custId2");
					if(roomId2!=null && StringUtils.isNotBlank(roomId2.toString()) &&
							custId2!=null && StringUtils.isNotBlank(custId2.toString())) {
						ReceiptBean reBean=new ReceiptBean();
						reBean.setCustId(custId2.toString());
						reBean.setRoomId(roomId2.toString());
						reBean.setDeposit(StringUtils.parseBigDecimal(vo.getValueKey("deposit2")));
						reBean.setReceiptMoney(StringUtils.parseBigDecimal(vo.getValueKey("amountCollect2")));
						reBean.setOrderFlag(receiptService.getMaxOrderFlag(vo));
						reBean.setPancyId(entity.getPancyId());
						reBean.setCreateBy(vo.getCurrentUser().getStaffId());
						reBean.setUpdateBy(vo.getCurrentUser().getStaffId());
						reBean.setReceiptDate(DateTimeUtile.getTimesTmp());
						reBean.setUpdateDate(DateTimeUtile.getTimesTmp());
						reBean.setCreateDate(DateTimeUtile.getTimesTmp());
						reBean.setReceiptState("2");//已收费
						receiptService.insertReceipt(reBean);
						
						//插入历史记录
						ReceiptHistoryBean history=new ReceiptHistoryBean();
						history.setCustId(custId2.toString());
						history.setRoomId(roomId2.toString());
						history.setDeposit(StringUtils.parseBigDecimal(vo.getValueKey("deposit2")));
						history.setReceiptMoney(StringUtils.parseBigDecimal(vo.getValueKey("amountCollect2")));
						history.setOrderFlag(receiptService.getMaxOrderFlag(vo));
						history.setPancyId(entity.getPancyId());
						history.setReceiptDate(DateTimeUtile.getTimesTmp());
						history.setUpdateBy(vo.getCurrentUser().getStaffId());
						history.setReceiptState("2");
						history.setRemark("入住房间");
						receiptHistoryService.insertReceiptHistory(history);
					}
				/*}else {
					
					//如果换了房间，然后把原来房间设置位空闲
					RoomInfoBean roomBean=new RoomInfoBean();
					roomBean.setRoomId(entity.getRoomInfo().getRoomId());
					roomBean.setUpdateBy(vo.getCurrentUser().getStaffId());
					roomBean.setUpdateDate(DateTimeUtile.getTimesTmp());
					roomBean.setRoomState("1");//入住
					roomBean.setEnabledFlag(1);
					roomInfoService.updateSelective(roomBean);
					
					//修改房间状态
					Object roomId2=vo.getValueKey("roomId2");
					if(roomId2!=null && StringUtils.isNotBlank(roomId2.toString())) {
						RoomInfoBean roomBean1=new RoomInfoBean();
						roomBean1.setRoomId(roomId2.toString());
						roomBean1.setRoomState("3");
						roomInfoService.updateSelective(roomBean1);
					}
					
					//入住    把金额插入记录表
					Object custId2=vo.getValueKey("custId2");
					if(roomId2!=null && StringUtils.isNotBlank(roomId2.toString()) &&
							custId2!=null && StringUtils.isNotBlank(custId2.toString())) {
						ReceiptBean reBean=new ReceiptBean();
						reBean.setCustId(custId2.toString());
						reBean.setRoomId(roomId2.toString());
						reBean.setDeposit(StringUtils.parseBigDecimal(vo.getValueKey("deposit2")));
						reBean.setReceiptMoney(StringUtils.parseBigDecimal(vo.getValueKey("amountCollect2")));
						reBean.setOrderFlag(receiptService.getMaxOrderFlag(vo));
						reBean.setPancyId(entity.getPancyId());
						reBean.setReceiptDate(DateTimeUtile.getTimesTmp());
						reBean.setReceiptState("1");
						receiptService.insertReceipt(reBean);
						
						//把金额插入记录表
						ReceiptHistoryBean history=new ReceiptHistoryBean();
						history.setCustId(custId2.toString());
						history.setRoomId(roomId2.toString());
						history.setDeposit(StringUtils.parseBigDecimal(vo.getValueKey("deposit2")));
						history.setReceiptMoney(StringUtils.parseBigDecimal(vo.getValueKey("amountCollect2")));
						history.setOrderFlag(receiptService.getMaxOrderFlag(vo));
						history.setPancyId(entity.getPancyId());
						history.setReceiptDate(DateTimeUtile.getTimesTmp());
						history.setReceiptState("1");
						history.setRemark("更换房间入住");
						receiptHistoryService.insertReceiptHistory(history);
					}
				}*/
			}
		}
	}
	
	/**
	 * 退房间
	 * @param roomId
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public void checkOutRoom(ObjectVo vo) throws Exception {
		Object pancyId=vo.getValueKey("pancyId1");
		if(pancyId!=null && StringUtils.isNotBlank(pancyId.toString())) {
			OccupancyBean entity=new OccupancyBean();
			entity.setPancyState("0");
			entity.setEnabledFlag(1);
			entity.setPancyId(pancyId.toString());
			this.updatePancySelective(vo);
		}
		//设置房间状态为空闲
		Object roomId=vo.getValueKey("roomId1");
		if(roomId!=null && StringUtils.isNotBlank(roomId.toString())) {
			RoomInfoBean roomBean=new RoomInfoBean();
			roomBean.setRoomId(roomId.toString());
			roomBean.setRoomState("1");
			roomInfoService.updateSelective(roomBean);
		}
		
		//客户状态变为正常
		Object custId=vo.getValueKey("custId1");
		if(custId!=null && StringUtils.isNotBlank(custId.toString())) {
			CustomerBean cust=new CustomerBean();
			cust.setCustId(custId.toString());
			cust.setCustState("1");
			cust.setUpdateBy(vo.getCurrentUser().getStaffId());
			cust.setUpdateDate(DateTimeUtile.getTimesTmp());
			customerService.updateCustSelective(cust);
		}
		
		//判断是否退钱
		Object tqFlag=vo.getValueKey("tqFlag");
		//退钱
		if(tqFlag!=null && StringUtils.isNotBlank(tqFlag.toString()) && "tq".equals(tqFlag)) {
			ReceiptBean reBean=new ReceiptBean();
			reBean.setReceiptMoney(StringUtils.parseBigDecimal(vo.getValueKey("amountCollect1")));
			reBean.setPancyId(pancyId.toString());
			reBean.setReceiptDate(DateTimeUtile.getTimesTmp());
			reBean.setReceiptState("0");
			reBean.setRemark(vo.getValueKey("remark1")+"");
			receiptService.insertReceipt(reBean);
		}else{
			ReceiptBean reBean=new ReceiptBean();
			reBean.setReceiptMoney(new BigDecimal(0));
			reBean.setCustId(vo.getValueKey("custId1").toString());
			reBean.setRoomId(roomId.toString());
			reBean.setPancyId(pancyId.toString());
			reBean.setReceiptDate(DateTimeUtile.getTimesTmp());
			reBean.setReceiptState("0");
			reBean.setRemark(vo.getValueKey("remark1")+"");
			receiptService.insertReceipt(reBean);
		}
	}
	
	/**
	 * 已入住，要求换房
	 * @param entity
	 * @return
	 * @throws Exception 
	 */
	@Transactional(rollbackFor=Exception.class)
	public AjaxResult changeRoom(ObjectVo vo) throws Exception {
		/**
		 * 1,把原来房间先设置为空闲
		 * 2，把订单房间和价格修改
		 * 3，把收费表订单房间修改
		 * 4，插入一条记录到历史记录表
		 */
		
		AjaxResult result=new AjaxResult();
		//获得表单信息
		//JSONObject jsonObj=vo.getFormValue();
		//新的房间
		Object custId4=vo.getValueKey("custId4");//客户
		Object roomId4=vo.getValueKey("roomId4");//更换前房间
		Object pancyId4=vo.getValueKey("pancyId4");//更换前订单
		Object roomId5=vo.getValueKey("roomId5");//更换后房间
		Object roomPrice5=vo.getValueKey("roomPrice5");
		Object checkInDate5=vo.getValueKey("checkInDate5");
		Object checkOutDate5=vo.getValueKey("checkOutDate5");
		Object prepaidNumber5=vo.getValueKey("prepaidNumber5");//入住天数
		Object discount5=vo.getValueKey("discount5");
		Object deposit5=vo.getValueKey("deposit5");
		Object prepaidAmount5=vo.getValueKey("prepaidAmount5");
		Object paidType5=vo.getValueKey("paidType5");
		Object amountCollect5=vo.getValueKey("amountCollect5");
		Object remark5=vo.getValueKey("remark5");
		
		if(roomId5!=null && StringUtils.isNotBlank(roomId5.toString()) 
				&& custId4!=null && StringUtils.isNotBlank(custId4.toString())) {
			
			//订单状态: 1：入住,2：预订，3.换房，4：退房,5：取消,0：结束
			//设置房间状态为空闲
			if(roomId4!=null && StringUtils.isNotBlank(roomId4.toString())) {
				RoomInfoBean roomBean=new RoomInfoBean();
				roomBean.setRoomId(roomId4.toString());
				roomBean.setRoomState("1");//空闲
				roomBean.setEnabledFlag(1);
				roomBean.setUpdateBy(vo.getCurrentUser().getStaffId());
				roomBean.setUpdateDate(DateTimeUtile.getTimesTmp());
				roomInfoService.updateSelective(roomBean);
			}
			
			//修改收费(根据订单)
			ReceiptBean reBean=new ReceiptBean();
			reBean.setReceiptMoney(StringUtils.parseBigDecimal(amountCollect5));
			reBean.setCustId(custId4.toString());
			reBean.setRoomId(roomId4.toString());
			reBean.setPancyId(pancyId4.toString());
			reBean.setReceiptDate(DateTimeUtile.getTimesTmp());
			reBean.setReceiptState("2");//已收费
			reBean.setRemark(vo.getValueKey("remark5")+"");
			receiptService.updateByPancyId(reBean);
			
			
			//重新生成订单
			OccupancyBean entity=new OccupancyBean();
			entity.setPancyId(pancyId4.toString());
			entity.setPancyState("3");//入住
			entity.setPayState("2");
			entity.setRoomId(roomId5.toString());
			entity.setCustId(custId4.toString());
			entity.setHousePrice(StringUtils.parseBigDecimal(roomPrice5));
			entity.setCheckInDate(DateTimeUtile.getTimesTmpStr(checkInDate5.toString()+":00"));
			entity.setCheckOutDate(DateTimeUtile.getTimesTmpStr(checkOutDate5.toString()+":00"));
			entity.setPrepaidNumber(StringUtils.parseInt(prepaidNumber5));
			entity.setDiscount(StringUtils.parseFloat(discount5));
			entity.setDeposit(StringUtils.parseBigDecimal(deposit5));
			entity.setPrepaidAmount(StringUtils.parseBigDecimal(prepaidAmount5));
			entity.setPaidType(paidType5+"");
			entity.setAmountCollect(StringUtils.parseBigDecimal(amountCollect5));
			entity.setRemark(remark5+"");
			entity.setEnabledFlag(1);
			entity.setUpdateBy(vo.getCurrentUser().getStaffId());
			entity.setUpdateDate(DateTimeUtile.getTimesTmp());
			occupancyDao.updatePancyById(entity);
			
			//房间状态修改
			RoomInfoBean roomBean=new RoomInfoBean();
			roomBean.setRoomId(roomId5.toString());
			roomBean.setRoomState("3");//入住
			roomBean.setEnabledFlag(1);
			roomBean.setUpdateBy(vo.getCurrentUser().getStaffId());
			roomBean.setUpdateDate(DateTimeUtile.getTimesTmp());
			roomInfoService.updateSelective(roomBean);
			
			//把金额插入历史记录表
			ReceiptHistoryBean history=new ReceiptHistoryBean();
			history.setCustId(custId4.toString());
			history.setRoomId(roomId5.toString());
			history.setDeposit(StringUtils.parseBigDecimal(vo.getValueKey("deposit5")));
			history.setReceiptMoney(StringUtils.parseBigDecimal(vo.getValueKey("amountCollect5")));
			history.setOrderFlag(receiptService.getMaxOrderFlag(vo));
			history.setPancyId(entity.getPancyId());
			history.setReceiptDate(DateTimeUtile.getTimesTmp());
			history.setReceiptState("2");
			history.setRemark("更换房间入住");
			receiptHistoryService.insertReceiptHistory(history);
			
			//插入记录表
			/*ReceiptBean reBean=new ReceiptBean();
			reBean.setReceiptMoney(new BigDecimal(0));
			reBean.setCustId(custId.toString());
			reBean.setRoomId(roomId.toString());
			reBean.setPancyId(pancyId.toString());
			reBean.setReceiptDate(DateTimeUtile.getTimesTmp());
			reBean.setReceiptState("0");
			reBean.setRemark(vo.getValueKey("remark5")+"");
			receiptService.insertReceipt(reBean);*/
			
			result.setStatusCode(0);
			result.setStatusMessage("保存成功");
		}else {
			result.setStatusCode(-1);
			result.setStatusMessage("保存失败");
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(DateTimeUtile.getTimesTmpStr("2018-08-08 12:00:00"));
	}
}
