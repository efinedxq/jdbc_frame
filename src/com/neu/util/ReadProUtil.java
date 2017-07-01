package com.neu.util;

import java.util.ResourceBundle;

/**
 * 专门负责 读取 db.properties 中的数据
 * @author Administrator
 *
 */
public class ReadProUtil {
	//指明 资源 文件的 名称和位置
//	如果 在src 的根目录下 直接 写 文件名 即可
	private static ResourceBundle rb=
			ResourceBundle.getBundle("db");
	/**
	 * 获取 名为 user 对应的数据 root
	 * @return
	 */
	public static String getUser(){
		String str=rb.getString("user");
		return str;
	}
	public static String getPassword(){
		String str=rb.getString("password");
		return str;
	}
	
	public static String getUrl(){
		String str=rb.getString("url");
		return str;
	}
	public static String getDriver(){
		String str=rb.getString("driver");
		return str;
	}
}







