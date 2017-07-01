package com.neu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 负责 完成数据库的连接 和资源的关闭
 * @author Administrator
 *
 */
public class DBUtil {

	private static String user=ReadProUtil.getUser();
	private static String password=ReadProUtil.getPassword();
	private static String url=ReadProUtil.getUrl();
	private static String driver=ReadProUtil.getDriver();
	
	//在类加载的时候 执行 一次
	static{
		//加载驱动，只加载一次
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//把异常 抛出，不处理了 ，将来 谁调用 谁处理
	public static Connection getConnection() throws SQLException{
		Connection conn=DriverManager
				.getConnection(url, user, password);
		return conn;
	}
	
	public static void close(Connection conn,Statement stmt,ResultSet rst){
		//由小到大
		if(rst !=null){
			try {
				rst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				rst=null;
			}
		}
		if(stmt != null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				stmt=null;
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally{
				conn=null;
			}
		}
	}
	
}
