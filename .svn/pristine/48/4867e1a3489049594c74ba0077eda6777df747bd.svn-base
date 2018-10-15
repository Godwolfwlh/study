package com.common.basedb;

import java.util.List;
import java.util.Map;
/**
 * 

* <p>Title: MyBatisBaseDao.java</p>  

* <p>Description: The MyBatis Common Dao Interface</p>  

* <p>Copyright: Copyright (c) 2018</p>  

* <p>Company:Kaili Yun Han Zhi Hui City Operation Management Co.,Ltd</p>  

* @author yangshenghua  

* @date 2018-03-12 09:12:40  

* @version 1.0
 */
public interface MyBatisBaseDao {
	/**
	 * 保存实体对象
	 * 
	 * @param object
	 *            要保存的实体对象
	 * @param sqlId
	 *            sqlMapper中的id
	 * @return 保存结果标志
	 */
	public boolean addObject(Object object,String sqlId) throws Exception;

	/**
	 * 删除实体对象
	 * 
	 * @param clazz
	 *            删除的实体对象类
	 * @param sqlId
	 *            sqlMapper中的id
	 * @param id
	 *            删除对象的id(数组类型)
	 * @return 删除结果标志
	 */
	public boolean deleteObject(String sqlId, int id[]) throws Exception;

	/**
	 * 更新实体对象
	 * 
	 * @param object
	 *            要更新的实体对象
	 * @param sqlId
	 *            sqlMapper中的id
	 * @return 更新结果标志
	 */
	public boolean updateObject(Object object,String sqlId) throws Exception;

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
	public Object findObjectById(String sqlId, int id) throws Exception;
	/**
	 * 查出对象总记录数
	 * 
	 * @param sqlId
	 *            sqlMapper中的id
	 * @return 总记录数
	 */
	public int getTotalCount(String sqlId,Map<String,Object> map) throws Exception;

	/**
	 * 查找对象列表
	 * 
	 * @param clazz
	 *            查询的类类型
	 * @param sqlId
	 *            sqlMapper中的id
	 * @param start
	 *            开始索引
	 * @param limit
	 *            查询条数
	 * @return 对象列表
	 */
	public List<?> findObjectList(String sqlId,Map<String,Object> map) throws Exception;
}
