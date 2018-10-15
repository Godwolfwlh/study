package com.common.basedb.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.common.basedb.ConnectionDao;
/**
 * 获得数据基本链接
 * @author Administrator
 *
 */
@Service(value="connectionImpl")
public class ConnectionImpl implements ConnectionDao{
	private ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	private ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<Connection>();
	//获得连接
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/*public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}*/

	public SqlSession getSqlSession(SqlSessionTemplate sqlSessionTemplate) throws SQLException {
		//从当前线程获取
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession == null){
			sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(false);
			//将sqlSession与当前线程绑定
			threadLocal.set(sqlSession);
		}
		return sqlSession;
	}

	/**
	 * 获得session 连接
	 * @param sqlSessionTemplate
	 * @return
	 * @throws SQLException 
	 */
	public SqlSession openSession(SqlSessionTemplate sqlSessionTemplate) throws SQLException {
		SqlSessionFactory factory=sqlSessionTemplate.getSqlSessionFactory();
		return  factory.openSession(false);
	}
	
	/**
	 * 事物回滚
	 * @param sqlSessionTemplate
	 * @return
	 * @throws SQLException 
	 */
	public void sessionRollback(SqlSession session) throws SQLException {
		session.rollback();
	}
	
	/**
	 * 提交session
	 * @param sqlSessionTemplate
	 * @return
	 * @throws SQLException 
	 */
	public void sessionCommit(SqlSession session) throws SQLException {
		session.commit();
	}
	
	/**
	 * 插入语句
	 * @param sqlSessionTemplate
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public int insertTemplate(String sqlId,Object params) throws Exception{
		return this.sqlSessionTemplate.insert(sqlId, params);
	}
	
	/**
	 * 修改语句
	 * @param sqlSessionTemplate
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public int updateTemplate(String sqlId,Object params) throws Exception{
		return this.sqlSessionTemplate.update(sqlId, params);
	}
	
	/**
	 * 删除语句
	 * @param sqlSessionTemplate
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public int deleteTemplate(String sqlId,Object params) throws Exception{
		return this.sqlSessionTemplate.delete(sqlId, params);
	}
	
	/**
	 * 查询语句 List集合
	 * @param sqlSessionTemplate
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public List<?> selectListTemplate(SqlSession session,String sqlId,Object params) {
		return session.selectList(sqlId, params);
	}
	
	/**
	 * 查询一条数据
	 * @param sqlSessionTemplate
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public Object selectOneTemplate(SqlSession session,String sqlId,Object params) {
		return session.selectOne(sqlId, params);
	}
	
	/**
	 * 关闭session
	 * @param sqlSessionTemplate
	 * @return
	 */
	public void closeSession(SqlSession session) {
		SqlSession sqlSession = threadLocal.get();
		if(sqlSession != null){
			sqlSession.close();
			threadLocal.remove();
		}
	}
	/**
	 * 关闭session
	 * @param sqlSessionTemplate
	 * @return
	 */
	public void closeTemplate(SqlSessionTemplate sqlSessionTemplate) {
		if(sqlSessionTemplate!=null) {
			sqlSessionTemplate.close();
		}
	}

	/**
	 * 获得连接
	 */
	@Override
	public Connection getConnection(SqlSessionTemplate sqlSessionTemplate) throws SQLException{
		// TODO Auto-generated method stub
		//从当前线程获取
		Connection conn = threadLocalConnection.get();
		if(conn == null){
			conn = this.getSqlSession(sqlSessionTemplate).getConnection();
			//将sqlSession与当前线程绑定
			threadLocalConnection.set(conn);
		}
		return conn;
	}

	/**
	 * 开始事物
	 */
	@Override
	public void connTransBegin(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		conn.setAutoCommit(false);
	}

	/**
	 * 提交事物
	 */
	@Override
	public void connTransComit(Connection conn) throws SQLException{
		// TODO Auto-generated method stub
		conn.commit();
	}
	

	/**
	 * 关闭事物
	 */
	@Override
	public void closeSession(Connection conn,PreparedStatement pstmt,ResultSet rs){
		// TODO Auto-generated method stub
		try {
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(pstmt!=null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			if(conn!=null) {
				threadLocalConnection.remove();
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 事物回滚
	 */
	@Override
	public void connTransRollBack(Connection conn) throws SQLException {
		// TODO Auto-generated method stub
		conn.rollback();
	}
}
