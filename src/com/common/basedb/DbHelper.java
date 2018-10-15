package com.common.basedb;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbHelper {
	private static final String PROPERTIES_NAME = "E:\\first\\hotel\\src\\dbconfig.properties";
	public static String DB_DRIVER = null;
	public static String DB_URL = null;
	public static String DB_USER = null;
	public static String DB_PWD = null;
	
	
	static{
		FileInputStream in = null;
		try{
			Properties properties = new Properties();
			in = new FileInputStream(PROPERTIES_NAME);
			properties.load(in);
			DB_DRIVER = properties.getProperty("jdbc.driverClassName");
			DB_URL = properties.getProperty("jdbc.url");
			DB_USER = properties.getProperty("jdbc.username");
			DB_PWD = properties.getProperty("jdbc.password");
			Class.forName(DB_DRIVER);
			System.out.println("读取配置信息成功！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("读取配置信息失败！");
		}finally{
			if(in != null){
				try{
					in.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeConn(Connection conn,PreparedStatement pstmt,ResultSet set) {
		try {
			if(conn!=null) {
				conn.close();
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
			if(set!=null) {
				set.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Connection conn=DbHelper.getConnection();
		System.out.println(conn);
	}
}
