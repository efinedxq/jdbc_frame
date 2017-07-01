package demo_0626_02;

import java.util.ArrayList;
import java.util.List;

public class TestBaseDao {

	public static void main(String[] args) {
		String sql="insert into course_info(c_name,c_score)"
				+ "	values(?,?)";
		List<String> params=new ArrayList<String>();
		params.add("android");
//		数据库 会 自动 类型转换 你只要保证 能转换 即可
		params.add("10");
		BaseDao dao=new BaseDao();
		int row=dao.executeUpdate(sql, params);
		System.out.println(row);
	}

}
