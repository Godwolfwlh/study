package com.common.basedb.conn;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import com.ajax.DateType;
import com.util.DateTimeUtile;
import com.util.StringUtils;

/**
 * 获得数据基本链接
 * @author Administrator
 *
 */
public class ConnectionImpl implements ConnectionDao{
	private ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
	private ThreadLocal<Connection> threadLocalConnection = new ThreadLocal<Connection>();
	
	//获得连接
	private SqlSessionTemplate sqlSessionTemplate;
	
	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

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
	 * jdbc 封装插入
	 * @param conn
	 * @param tableName
	 * @param colums
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int addSql(Connection conn,String tableName,String[] colums,Object[] params) throws Exception{
		int result=0;
		PreparedStatement pstmt=null;
		StringBuilder sql=new StringBuilder("INSERT INTO ");//VALUES( 
		if(StringUtils.isBlank(tableName) && StringUtils.hasLength(tableName)) {
			if(colums!=null && colums.length>0) {
				
				sql.append(tableName);
				for(int i=0;i<colums.length;i++) {
					if(i==0) {
						sql.append(colums[i]);
					}else {
						sql.append(","+colums[i]);
					}
				}
				sql.append(") VALUS (");
				
				//value 动态添加问号
				for(int i=0;i<colums.length;i++) {
					if(i==0) {
						sql.append("?");
					}else {
						sql.append(",?");
					}
				}
				sql.append(")");
				
				pstmt=conn.prepareStatement(sql.toString());
				List<String> list=this.getColumnTypes(tableName);
				if(list.size()>0 && list!=null) {
					for(int i=0;i<list.size();i++) {
						for(int k=0;k<colums.length;k++) {
							if(colums[k].equals(list.get(i))) {
								String type=list.get(i);
								if(type.equals("int")) {
									pstmt.setInt(k,StringUtils.parseInt(params[k]));
								}else if(type.equals("String")) {
									pstmt.setString(k,params[k]+"");
								}else if(type.equals("float")){
									pstmt.setFloat(k,StringUtils.parseFloat(params[k]));
								}else if(type.equals("double")){
									pstmt.setDouble(k,StringUtils.parseDouble(params[k]));
								}else if(type.equals("decimal")){
									pstmt.setBigDecimal(k,StringUtils.parseBigDecimal(params[k]));
								}else if(type.equals("Date")){
									pstmt.setDate(k,DateTimeUtile.getJavaSQlYmd(params[k]+""));
								}
							}
						}
					}
				}else {
					result=0;
				}
				result=pstmt.executeUpdate();
			}else {
				result=0;
			}
		}else {
			result=0;
		}
		if(pstmt!=null) {
			pstmt.close();
		}
		return result;
	}
	
	/**
	 * jdbc 封装插入
	 * @param conn
	 * @param tableName
	 * @param colums
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public int updateSql(Connection conn,String tableName,String[] colums,Object[] params,String[] columsParams,Object[] whereParams) throws Exception{
		int result=0;
		PreparedStatement pstmt=null;
		StringBuilder sql=new StringBuilder("UPDATE ");//VALUES( 
		if(StringUtils.isBlank(tableName) && StringUtils.hasLength(tableName)) {
			if(colums!=null && colums.length>0) {
				sql.append(tableName);
				//value 动态添加问号
				for(int i=0;i<colums.length;i++) {
					if(i==0) {
						sql.append("set "+colums[i]+" = ?");
					}else {
						sql.append(",set "+colums[i]+" = ?");
					}
				}
				if(columsParams.length>0 && columsParams!=null) {
					sql.append(" WHERE ");
					for(int w=0;w<whereParams.length;w++) {
						if(w==0) {
							sql.append(columsParams[w]+" = "+whereParams[w]);
						}else {
							sql.append(","+columsParams[w]+" = "+whereParams[w]);
						}
					}
				}else {
					result=0;
				}
				
				pstmt=conn.prepareStatement(sql.toString());
				List<String> list=this.getColumnTypes(tableName);
				if(list.size()>0 && list!=null) {
					for(int i=0;i<list.size();i++) {
						for(int k=0;k<colums.length;k++) {
							if(colums[k].equals(list.get(i))) {
								String type=list.get(i);
								if(type.equals("int")) {
									pstmt.setInt(k,StringUtils.parseInt(params[k]));
								}else if(type.equals("String")) {
									pstmt.setString(k,params[k]+"");
								}else if(type.equals("float")){
									pstmt.setFloat(k,StringUtils.parseFloat(params[k]));
								}else if(type.equals("double")){
									pstmt.setDouble(k,StringUtils.parseDouble(params[k]));
								}else if(type.equals("decimal")){
									pstmt.setBigDecimal(k,StringUtils.parseBigDecimal(params[k]));
								}else if(type.equals("Date")){
									pstmt.setDate(k,DateTimeUtile.getJavaSQlYmd(params[k]+""));
								}
							}
						}
					}
				}else {
					result=0;
				}
				result=pstmt.executeUpdate();
			}else {
				result=0;
			}
		}else {
			result=0;
		}
		if(pstmt!=null) {
			pstmt.close();
		}
		return result;
	}
	
	/**
	 * 插入语句
	 * @param sqlSessionTemplate
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public int insertTemplate(SqlSession session,String sqlId,Object params) throws Exception{
		return session.insert(sqlId, params);
	}
	
	/**
	 * 修改语句
	 * @param sqlSessionTemplate
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public int updateTemplate(SqlSession session,String sqlId,Object params) throws Exception{
		return session.update(sqlId, params);
	}
	
	/**
	 * 删除语句
	 * @param sqlSessionTemplate
	 * @param sqlId
	 * @param params
	 * @return
	 */
	public int deleteTemplate(SqlSession session,String sqlId,Object params) throws Exception{
		return session.delete(sqlId, params);
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
	
    /*
     *  获取数据库下的所有表名
    */
   public List<String> getTableNames(){
       List<String> tableNames = new ArrayList<String>();
       Connection conn=null;
       ResultSet rs = null;
       try {
           //获取数据库的元数据
    	   conn =this.getConnection(getSqlSessionTemplate());
           DatabaseMetaData db = conn.getMetaData();
           //从元数据中获取到所有的表名
           rs = db.getTables(null, null, null, new String[] { "TABLE" });
           while(rs.next()) {
               tableNames.add(rs.getString(3));
           }
       } catch (SQLException e) {
           System.err.println("getTableNames failure:"+e);
       } finally {
           try {
               rs.close();
               this.closeSession(conn, null,rs);
           } catch (SQLException e) {
               System.err.println("close ResultSet failure:"+e);
           }
       }
       return tableNames;
   }

   /**
    * 获取表中所有字段名称
    * @param tableName 表名
    * @return
 * @throws SQLException 
    */
   public List<String> getColumnNames(String tableName){
       List<String> columnNames = new ArrayList<>();
       //与数据库的连接
       Connection conn =null;
       PreparedStatement pStemt = null;
       String SQL = "SELECT * FROM ";
       String tableSql = SQL + tableName;
       try {
    	   conn=this.getConnection(getSqlSessionTemplate());
           pStemt = conn.prepareStatement(tableSql);
           //结果集元数据
           ResultSetMetaData rsmd = pStemt.getMetaData();
           //表列数
           int size = rsmd.getColumnCount();
           for (int i = 0; i < size; i++) {
               columnNames.add(rsmd.getColumnName(i + 1));
           }
       }  catch (Exception e) {
           System.err.println("getTableNames failure:"+e);
       } finally {
           this.closeSession(conn, pStemt,null);
       }
       return columnNames;
   }

   /**
    * 获取表中所有字段类型
    * @param tableName
    * @return
    */
   public List<String> getColumnTypes(String tableName) {
       List<String> columnTypes = new ArrayList<>();
       //与数据库的连接
       Connection conn =null;
       PreparedStatement pStemt = null;
       String SQL = "SELECT * FROM ";
       String tableSql = SQL + tableName;
       try {
    	   conn =this.getConnection(getSqlSessionTemplate());
           pStemt = conn.prepareStatement(tableSql);
           //结果集元数据
           ResultSetMetaData rsmd = pStemt.getMetaData();
           //表列数
           int size = rsmd.getColumnCount();
           for (int i = 0; i < size; i++) {
               columnTypes.add(rsmd.getColumnTypeName(i + 1));
           }
       } catch (Exception e) {
           System.err.println("getTableNames failure:"+e);
       } finally {
           this.closeSession(conn, pStemt,null);
       }
       return columnTypes;
   }
   
   /**
	 * 功能：获得mysql数据库列的数据类型
	 * 
	 * @param sqlType
	 * @return
	 */
	private String sqlType2JavaType(String sqlType) {

		if (sqlType.equalsIgnoreCase("bit")) {
			return "Boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "Byte";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "Short";
		} else if (sqlType.equalsIgnoreCase("int")) {
			return "int";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("numeric")
				|| sqlType.equalsIgnoreCase("real") || sqlType.equalsIgnoreCase("money")
				|| sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		}else if(sqlType.equalsIgnoreCase("decimal")) {
			return "decimal";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char")
				|| sqlType.equalsIgnoreCase("nvarchar") || sqlType.equalsIgnoreCase("nchar")
				|| sqlType.equalsIgnoreCase("text")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		} else if (sqlType.equalsIgnoreCase("image")) {
			return "Blod";
		}else {
			return null;
		}

	}
}
