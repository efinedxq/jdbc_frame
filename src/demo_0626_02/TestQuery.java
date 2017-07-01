package demo_0626_02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class TestQuery {

	public static void main(String[] args) {
//		Query  DQL
		
		String user="root";
		String password="root";
		String url="jdbc:mysql://localhost:3306/xueshengxuanke"
				+ "?useUnicode=true&characterEncoding=utf-8";
		String driver="com.mysql.jdbc.Driver";
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Connection conn=DriverManager
					.getConnection(url, user, password);
			String sql="select * from tea_info";
			PreparedStatement stmt=
					conn.prepareStatement(sql);
			//执行 查询语句 获取 查询的记录
			ResultSet rst=stmt.executeQuery();
			//如何获取 select语句中查询到的列名
			//获取查询结果集中出现的列
			ResultSetMetaData meta=rst.getMetaData();
			//获取select 语句查到的列数
			int count=meta.getColumnCount();//5
			//存放查到的列名 
			String[] names=new String[count];
//			获取查询的每一个列名存放在数组中
			for(int i=0;i<count;i++){
//				getColumnLabel 指的是 显示的列名 如果有别名 就是 别名
//				getColumnName  指的是 创建表时 其中的列名 和 别名 无关
//				System.out.println(meta.getColumnLabel(i+1)+"\t"+meta.getColumnName(i+1));
				names[i]=meta.getColumnLabel(i+1);
			}
			//存放 表中的所有行数据
			List<Map<String,String>> list=new ArrayList<Map<String,String>>();
			//减少代码量
			while(rst.next()){
				Map<String, String> map=
						new HashMap<String, String>();
				//获取一行中每一列的值
//				一行中的每一列 以 列名 作为 key 值 作为 value 放入 map中了
//				map 就是 表中的一行 记录
				for(int i=0;i<count;i++){
					String key=names[i];
					String value=rst.getString(key);
					map.put(key, value);
				}
				//把每一行添加到 list 集合中
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
