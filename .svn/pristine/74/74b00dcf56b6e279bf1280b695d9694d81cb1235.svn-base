package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * jdbc执行sql 事务执行
 * @author Administrator
 *
 */
public class JdbcUtil {

	/**
	 * 用来执行增删改的方法
	 * @param sql    sql语句
	 * @param params 参数
	 * @param conn   驱动连接
	 * @param ps     预执行sql对象
	 * @return
	 * @throws SQLException
	 */
	public int ExcuteUpdate(String sql,Object[] params,Connection conn,PreparedStatement ps) throws SQLException {
		ps = conn.prepareStatement(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
		int result = ps.executeUpdate();
		return result;
	}


	/**
	 * 用来执行查询操作
	 * @param sql     sql语句
	 * @param params  参数
	 * @param clz     类(对象)
	 * @param conn    驱动连接
	 * @param ps      预执行对像
	 * @param rs      结果对象
	 * @return
	 * @throws Exception 
	 */
	public List<? extends Object> findList(String sql, Object[] params, Class<?> clz,Connection conn,PreparedStatement ps,ResultSet rs) throws Exception {
		List rslist = new ArrayList();
		ps = conn.prepareStatement(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
		rs = ps.executeQuery();
		rslist = this.RsToList(rs, clz);
		return rslist;
	}

	/**
	 * 查询单个实体
	 * @param sql     sql语句
	 * @param params  参数
	 * @param clz     类(对象)
	 * @param conn    驱动连接
	 * @param ps      预执行对像
	 * @param rs      结果对象
	 * @return
	 * @throws Exception 
	 */
	public Object findOne(String sql, Object[] params, Class<?> clz,Connection conn,PreparedStatement ps,ResultSet rs) throws Exception {
		ps = conn.prepareStatement(sql);
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
		}
		rs = ps.executeQuery();

		Object object = this.RsToObject(rs, clz);
		return object;
	}

	/**
	 * 自动封装 将将结果集自动转换为list
	 * @param rs      结果集
	 * @param clz     类对象
	 * @throws Exception 
	 */
	public static List<? extends Object> RsToList(ResultSet rs, Class clz) throws Exception {
		List rslist = new ArrayList();
		// 获取数据集的元数据
		ResultSetMetaData metaData = rs.getMetaData();
		int clumnCount = metaData.getColumnCount();
		Field[] fields = clz.getDeclaredFields();
		/*
		 * for(int i=1;i<=clumnCount;i++){ String clumnName=metaData.getColumnName(i);
		 * 
		 * }
		 */
		while (rs.next()) {
			Object obj = clz.newInstance();
			for (int i = 1; i <= clumnCount; i++) {
				// 获取数据库列名称的一些属性
				//String columnName = metaData.getColumnName(i); // 列名
				String columnName = toUpperCaseFirstOne(camelName(metaData.getColumnName(i))); // 列名
				// int jdbcType=metaData.getColumnType(i); //数据库中的类型
				String javaType = metaData.getColumnClassName(i);
				/* System.out.println("列类名："+i+metaData.getColumnClassName(i)); */
				String begin = columnName.substring(0, 1).toUpperCase(); // 拼接set方法名称，以实现反射。
				String end = columnName.substring(1, columnName.length());
				String method = "set"+ toUpperCaseFirstOne(camelName(begin))+ end;
				// 获取列的值
				Object columnValue = rs.getObject(i);
				// JDBC数据类型转换为JAVA数据类型
				Class<?> javaTypeClass = Class.forName(javaType);
				/* Class typeClass=JDBCTypesUtils.jdbcTypeToJavaType(jdbcType); */

				Method setMethod = clz.getDeclaredMethod(method, javaTypeClass);
				setMethod.invoke(obj, columnValue);
			}
			rslist.add(obj);

		}
		return rslist;
	}

	/**
	 * 自动封装 将结果集自动转换为Object
	 * @param rs
	 * @param clz
	 * @throws Exception 
	 */
	public Object RsToObject(ResultSet rs, Class clz) throws Exception {
		Object object = null;
		// 获取数据集的元数据
		ResultSetMetaData metaData = rs.getMetaData();
		int clumnCount = metaData.getColumnCount();
		Field[] fields = clz.getDeclaredFields();
		/*
		 * for(int i=1;i<=clumnCount;i++){ String clumnName=metaData.getColumnName(i);
		 * 
		 * }
		 */
		while (rs.next()) {
			object = clz.newInstance();
			for (int i = 1; i <= clumnCount; i++) {
				// 获取数据库列名称的一些属性
				String columnName = metaData.getColumnName(i); // 列名
				int jdbcType = metaData.getColumnType(i); // 数据库中的类型
				String javaType = metaData.getColumnClassName(i);
				/* System.out.println("列类名："+i+metaData.getColumnClassName(i)); */
				String begin = columnName.substring(0, 1).toUpperCase(); // 拼接set方法名称，以实现反射。
				String end = columnName.substring(1, columnName.length());
				String method = "set" + toUpperCaseFirstOne(camelName(begin)) + end;
				// 获取列的值
				Object columnValue = rs.getObject(i);
				// JDBC数据类型转换为JAVA数据类型
				Class javaTypeClass = Class.forName(javaType);
				/* Class typeClass=JDBCTypesUtils.jdbcTypeToJavaType(jdbcType); */

				Method setMethod = clz.getDeclaredMethod(method, javaTypeClass);
				setMethod.invoke(object, columnValue);
			}

		}
		return object;
	}
	
	/**
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。</br>
     * 例如：HelloWorld->HELLO_WORLD
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }

    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。</br>
     * 例如：HELLO_WORLD->HelloWorld
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel :  camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }
    
    //首字母转小写
	public static String toLowerCaseFirstOne(String s){
	  if(Character.isLowerCase(s.charAt(0)))
	    return s;
	  else
	    return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}


    //首字母转大写
    public static String toUpperCaseFirstOne(String s){
      if(Character.isUpperCase(s.charAt(0)))
        return s;
      else
        return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    
    public static void main(String[] args) {
		System.out.println(underscoreName("aa_bb"));
		System.out.println(toUpperCaseFirstOne(camelName("aa_bb")));
	}

}
