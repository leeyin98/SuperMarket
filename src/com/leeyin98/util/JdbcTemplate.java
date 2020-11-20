package com.leeyin98.util;

import com.leeyin98.dao.IObjectMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	/*
	 * executeUpdate：方法的作用是执行增、删、该操作
	 * @param sql :传递的sql语句
	 * @param args：sql语句中？的参数部分
	 * @return ：受影响的行数
	 * @throws SQLException
	 */
	public static int executeUpdate(String sql,Object...args) throws SQLException {
		int result = -1 ; 
		//准备连接
		Connection conn= DbUtil.getConnection(); 
		//准备命令器
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//设置参数
		setParams(pstmt, args);
		//执行
		result = pstmt.executeUpdate();
		//关闭资源，但是连接不关闭
		DbUtil.closeResouce(pstmt, null);
		return result;
	}
	//设置参数
	public static void setParams(PreparedStatement pstmt,Object...args) {
		try {
			if(args!=null&&args.length>0) {
				//设置参数的下标从1开始
				for(int i = 0 ;i<args.length;i++) {
					pstmt.setObject(i+1, args[i]);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static <T> List<T> executeQuery(String sql,IObjectMapper<T> map,Object...args) throws SQLException{
		List<T> list =new ArrayList<T>();
		Connection conn =DbUtil.getConnection(); 
		PreparedStatement pstmt = conn.prepareStatement(sql);
		setParams(pstmt, args);

		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			list.add(map.getObjectFromResultSet(rs));
		}
		DbUtil.closeResouce(pstmt, rs);
		return list ; 
	}

	public static boolean executeQuery1(String sql,Object...args) throws SQLException{
		boolean isTrue = false;
		Connection conn =DbUtil.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		setParams(pstmt, args);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()){
			isTrue = true;
		}
		DbUtil.closeResouce(pstmt,rs);
		return isTrue;
	}
}
