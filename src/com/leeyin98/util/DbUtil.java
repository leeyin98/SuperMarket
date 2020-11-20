package com.leeyin98.util;

import org.apache.commons.dbcp.BasicDataSourceFactory;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DbUtil {
	private static ThreadLocal<Connection> threadLocal =new ThreadLocal<Connection>();
	//dataSource:重量级的组件（程序中对该对象的创建次数最多搞一次即可。）作用是：创建连接
	public static DataSource dataSource =null;
	//定义数据库连接资源文件
	public static Properties prop = new Properties();
	
	static{
		try {
			//加载属性文件
			prop.load(DbUtil.class.getClassLoader().getResourceAsStream("db.properties"));
			//创建数据源对象
			dataSource=BasicDataSourceFactory.createDataSource(prop);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//获得连接
	public static Connection getConnection() throws SQLException{
		//从threadLocal中获取连接
		Connection conn = threadLocal.get();
		if(conn==null) {
			//从数据源中获取
			conn = dataSource.getConnection();
			//在把获取的连接放入到threadLocal中
			threadLocal.set(conn);
		}
		return conn;
	}
	
	//关闭连接的方法
	public static void closeConnection() throws SQLException{
		//从threadLocal中取出连接
		Connection conn = threadLocal.get();
		if(conn!=null) {
			conn.close();
		}
		threadLocal.set(null);
	}

	//关闭资源
	public static void closeResouce(PreparedStatement pstmt,ResultSet rs) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
