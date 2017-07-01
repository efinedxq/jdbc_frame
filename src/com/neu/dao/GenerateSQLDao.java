package com.neu.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenerateSQLDao extends BaseDao {

	// 就是 编写sql语句是 操作的表名
	private String tableName;

	// 用调整表名
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public GenerateSQLDao(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * 表中不能为 null 列 必须 提供值
	 * 
	 * @param map
	 * @return
	 */
	public int insert(Map<String, String> map) {
		int row = 0;
		String sql = "insert into " + tableName + "(";
		// 存放列名的列表
		String cols = "";
		// 给列提供的值 只不过 用 ？ 占位符表示
		String values = "";
		// 存放 ？对应的值
		List<String> params = new ArrayList<String>();
		// 从 Map 中获取数据
		for (Map.Entry<String, String> entry : map.entrySet()) {
			cols += "," + entry.getKey();
			values += ",?";
			params.add(entry.getValue());
		}
		sql += cols.substring(1);// 从下标为1 的位置截取 ，这样可以把第一个 , 去掉
		sql += ") values(";
		sql += values.substring(1);
		sql += ")";
		System.out.println(sql);
		row = super.executeUpdate(sql, params);
		return row;
	}

	/**
	 * delete from 表明 where 1=1 and 列=值and... 可批量刪除
	 * 
	 * @return
	 */
	public int delete(Map<String, String> map) {
		int row = 0;
		String sql = "delete from " + tableName + " where 1=1";
		List<String> params = null;
		if (map != null) {
			params = new ArrayList<String>();
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String value = entry.getValue();
				if (value.indexOf(",") > 0) {
					sql += " and " + entry.getKey() + " in(" + value + ")";
				} else {
					sql += " and " + entry.getKey() + "=?";
					params.add(entry.getValue());
				}
			}
		}
		System.out.println(sql);
		row = super.executeUpdate(sql, params);
		return row;
	}

	/**
	 * update 表明 set 列=？,列=？where 1=1 and 列=？ and ...
	 * 
	 * @return
	 */
	public int update(Map<String, String> map, Map<String, String> where) {
		int row = 0;
		String sql = "update " + tableName + " set ";
		List<String> params = new ArrayList<String>();
		String sets = "";

		for (Map.Entry<String, String> entry : map.entrySet()) {
			sql += "," + entry.getKey() + "=?";
			params.add(entry.getValue());
		}
		sql += sets.substring(1);
		sql += " where 1=1 ";
		if (where != null) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				String value = entry.getValue();
				if (value.indexOf(",") > 0) {
					sql += " and " + entry.getKey() + " in(" + value + ")";
				} else {
					sql += " and " + entry.getKey() + "=?";
					params.add(entry.getValue());
				}
			}
		}
		row = super.executeUpdate(sql, params);
		return row;
	}
	/**
	 * select 动态生成 必须基于 视图
	 * 只能做 等值 查询
	 * @param map
	 * @return
	 */
    public List<Map<String, String>> select(Map<String,String> map){
    	List<Map<String,String>> list = null;
    	String sql = "select * from "+tableName+" where 1=1";
    	List<String> params = null;
    	if(map!=null){
    		params = new ArrayList<String>();
            for(Map.Entry<String, String> entry:map.entrySet()){
            	sql += " and "+entry.getKey()+"=?";
            	params.add(entry.getValue());
            }
    	}
    	list = super.executeQuery(sql, params);
    	return list;
    }
}
