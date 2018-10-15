package com.common.basedb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
/**
 * 基本数据连接
 * @author Administrator
 *
 */
public interface ConnectionDao {
	/**
	 * 获得连接
	 * @return
	 */
	public Connection getConnection(SqlSessionTemplate sqlSessionTemplate) throws SQLException;
	
	/**
	 * 开始事物
	 * @param conn
	 */
	public void connTransBegin(Connection conn) throws SQLException;
	
	/**
	 * 提交事务
	 * @param conn
	 */
	public void connTransComit(Connection conn) throws SQLException;
	
	/**
	 * 事务回滚
	 * @param conn
	 */
	public void connTransRollBack(Connection conn) throws SQLException;
	
	/**
	 * 关闭连接
	 */
	public void closeSession(Connection conn,PreparedStatement pstmt,ResultSet rs);
}
