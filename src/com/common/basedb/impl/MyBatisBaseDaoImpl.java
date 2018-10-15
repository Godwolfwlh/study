package com.common.basedb.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.common.basedb.MyBatisBaseDao;
import com.util.LogUtil;
/**
 * 

* <p>Title: MyBatisBaseDaoImpl.java</p>  

* <p>Description: MyBatis Common Dao Implement Class</p>  

* <p>Copyright: Copyright (c) 2018</p>  

* <p>Company:Kaili Yun Han Zhi Hui City Operation Management Co.,Ltd</p>  

* @author yangshenghua  

* @date 2018-03-12 09:12:06  

* @version 1.0
 */
@Repository("myBatisBaseDao")
public class MyBatisBaseDaoImpl implements MyBatisBaseDao {

	@Autowired
	public SqlSessionTemplate sqlSessionTemplate;
	
	private Logger log=LogUtil.initLog(MyBatisBaseDaoImpl.class);

	/**
	 * 保存实体对象
	 * 
	 * @param sqlId
	 *            sqlMapper中的id
	 * @param object
	 *            要保存的实体对象
	 * @return 保存结果标志
	 */
	@Override
	public boolean addObject(Object object,String sqlId) throws Exception{
		boolean flag = true;
		int result=0;
		try {
			result = sqlSessionTemplate.insert(sqlId, object);
			if (result < 1) {
				log.error("保存对象失败:"+object+",sqlId:"+sqlId);
				flag = false;
			}
		} catch (Exception e) {
			log.error("保存对象失败:"+object+",sqlId:"+sqlId);
			flag = false;
			throw new Exception(e.getMessage());
		}
		
		return flag;
	}

	/**
	 * 删除实体对象
	 * 
	 * @param clazz
	 *            删除的实体对象类
	 * @param sqlId
	 *            sqlMapper中的id
	 * @param id
	 *            删除对象的id
	 * @return 删除结果标志
	 */
	@Override
	public boolean deleteObject(String sqlId, int[] id)  throws Exception{
		boolean flag = true;
		for (int i : id) {
			int result = sqlSessionTemplate.delete(sqlId, i);
			if (result < 1) {
				log.info("删除对象失败:sqlId:"+sqlId);
				flag = false;
				break;
			}
		}
		return flag;

	}

	/**
	 * 更新实体对象
	 * 
	 * @param sqlId
	 *            sqlMapper中的id
	 * @param object
	 *            要更新的实体对象
	 * @return 更新结果标志
	 */
	@Override
	public boolean updateObject(Object object, String sqlId)  throws Exception{
		boolean flag = true;
		int result = sqlSessionTemplate.update(sqlId, object);
		if (result < 1) {
			log.info("更新对象失败:"+object+",sqlId:"+sqlId);
			flag = false;
		}

		return flag;

	}

	/**
	 * 根据id查找实体对象
	 * 
	 * @param clazz
	 *            要查找的对象类
	 * @param sqlId
	 *            sqlMapper中的id
	 * @param id
	 *            要查找对象的id
	 * @return 查找出的对象
	 */
	@Override
	public Object findObjectById(String sqlId, int id)  throws Exception{
		return sqlSessionTemplate.selectOne(sqlId, id);
	}

	/**
	 * 查出对象总记录数
	 * 
	 * @param clazz
	 *            要查找的对象类类型
	 * @param sqlId
	 *            sqlMapper中的id
	 * @return 总记录数
	 */
	@Override
	public int getTotalCount(String sqlId,Map<String,Object> map)  throws Exception{
		return sqlSessionTemplate.selectOne(sqlId,map);
	}

	/**
	 * 查找对象列表
	 * 
	 * @param clazz
	 *            要查找的对象类类型
	 * @param sqlId
	 *            sqlMapper中的id
	 * @param start
	 *            开始索引
	 * @param limit
	 *            查询条数
	 * @return 对象列表
	 */
	@Override
	public List<?> findObjectList(String sqlId,Map<String,Object> map)  throws Exception{
		List<?> list = sqlSessionTemplate.selectList(sqlId,map);
		return list;
	}
}
