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
import com.basic.entity.OccupancyBean;
import com.basic.entity.ReceiptBean;
import com.basic.entity.ReceiptHistoryBean;
import com.basic.entity.RoomInfoBean;
import com.common.Page;
import com.common.basedb.impl.ConnectionImpl;
import com.util.DateTimeUtile;
import com.util.StringUtils;


/**
 * 客户管理业务层
 * @author Administrator
 *
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class OccupancyTestService {
	
	@Resource
	private ConnectionImpl connectionImpl;
	
	public int insertPancy1(ObjectVo vo) throws Exception {
		OccupancyBean entity=(OccupancyBean) vo.JsonToBean(OccupancyBean.class);
		entity.setPancyId(StringUtils.getUUid());
		entity.setCreateBy("admin");
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy("admin");
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setEnabledFlag(1);
		//int result=connectionImpl.updateTemplate("insertPancy", entity);
		//int a=5/0;
		int result=connectionImpl.insertTemplate("insertPancy",entity);
		return result;
	}
	
	public int insertPancy2(ObjectVo vo) throws Exception {
		OccupancyBean entity=(OccupancyBean) vo.JsonToBean(OccupancyBean.class);
		entity.setPancyId(StringUtils.getUUid());
		entity.setCreateBy("admin");
		entity.setCreateDate(DateTimeUtile.getTimesTmp());
		entity.setUpdateBy("admin");
		entity.setUpdateDate(DateTimeUtile.getTimesTmp());
		entity.setEnabledFlag(1);
		//int result=connectionImpl.updateTemplate("insertPancy", entity);
		int result=connectionImpl.insertTemplate("insertPancy",entity);
		return result;
	}
	
	@Transactional(rollbackFor=Exception.class)
	public int insertPancy3(ObjectVo vo) throws Exception {
		this.insertPancy1(vo);
		this.insertPancy2(vo);
		//int i=5/0;
		return -1;
	}
}
