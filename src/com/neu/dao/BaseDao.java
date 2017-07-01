package com.neu.dao;


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

import com.neu.util.DBUtil;

/**
 * 负责执行 各种 sql语句
 * @author Administrator
 *
 */
public class BaseDao {
	
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rst;
	/**
	 * insert
	 * update
	 * delete
	 * @param sql
	 * @param params
	 * @return
	 */
	public int executeUpdate(String sql,List<String> params){
		int row=0;
		try {
			conn=DBUtil.getConnection();
			//根据sql 构建执行的对象
			stmt=conn.prepareStatement(sql);
			//判断 sql中是否有 ? 
			if(params!=null){
//				说明 List 集合中有数据，那就证明 sql中有 ?
				for(int i=0;i<params.size();i++){
					//集合 下标 为 0 的值 赋值给 第 1 个 ？
					stmt.setString(i+1, params.get(i));
				}
			}
			row=stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, stmt, rst);
		}
		return row;
	}
	
	public List<Map<String, String>> executeQuery(String sql,List<String> params){
		List<Map<String,String>> list=
				new ArrayList<Map<String,String>>();
		try {
			conn=DBUtil.getConnection();
			stmt=conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.size();i++){
					stmt.setString(i+1, params.get(i));
				}
			}
			rst=stmt.executeQuery();
			//获取查询的列信息
			ResultSetMetaData meta=rst.getMetaData();
			//获取查询的列 数
			int count=meta.getColumnCount();
			//构建存放 列名的容器 数组
			String[] names=new String[count];
			//获取每一列 的列名
			for(int i=0;i<count;i++){
				names[i]=meta.getColumnLabel(i+1);
			}
			//存放查询的每一行记录
			Map<String,String> map=null;
			while(rst.next()){
				//构建装载 一行 数据的 容器
				map=new HashMap<String, String>();
//				把每一行 中的每一列 放入Map中
				for(int i=0;i<count;i++){
					String name=names[i];
					String value=rst.getString(name);
					map.put(name, value);
				}
				//把改行记录 存入到List 中
				list.add(map);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, stmt, rst);
		}
		return list;
	}
}
