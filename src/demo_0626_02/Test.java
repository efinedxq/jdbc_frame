package demo_0626_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		//delete();
		insert();
//		对中文支持 非常差，出现乱码
	}
	
	
	
	public static void delete(){
		String user="root";
		String password="root";
		String url="jdbc:mysql://localhost:3306/xueshengxuanke";
		String driver="com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Connection conn=DriverManager
					.getConnection(url, user, password);
			String sql="delete from course_info where c_id=7";
			PreparedStatement stmt=conn.prepareStatement(sql);
			int row=stmt.executeUpdate();
			if(row>0){
				System.out.println("成功");
			}else{
				System.out.println("失败");
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void insert(){
		String user="root";
		String password="root";
		String url="jdbc:mysql://localhost:3306/xueshengxuanke?useUnicode=true&characterEncoding=utf-8";
		String driver="com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Scanner input=new Scanner(System.in);
		System.out.print("课程名称:");
		String name=input.next();//java
		System.out.print("学分:");
		int score=input.nextInt();//10
		try {
			Connection conn=DriverManager
					.getConnection(url, user, password);
//			预编译语句
			String sql="insert into course_info(c_name,c_score)"
					+ " values(?,?)";//在要提供值得位置 写 ? 号
//			占位符
			PreparedStatement stmt=conn.prepareStatement(sql);
//			给所有的问号 依次 赋值 第一个 问号 的位置 为 1 
			//不用再考虑 数据表中 列的类型了
//			有 几个 问号 ，就 赋值 几次 ，必须 和 ？ 对应
			stmt.setObject(1, name);
			stmt.setObject(2, score);
			
			int row=stmt.executeUpdate();
			if(row>0){
				System.out.println("成功");
			}else{
				System.out.println("失败");
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
