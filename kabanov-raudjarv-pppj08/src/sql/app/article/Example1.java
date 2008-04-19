package sql.app.article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import sql.api.SqlUtil;

public class Example1 {

	public static void main(String[] args) throws SQLException {
		
		ResultSet rs = SqlUtil.executeQuery(
				"SELECT name, height, birthday " +
				"FROM person " +
				"WHERE height >= " + 170);
		
		while (rs.next()) {
			String name = rs.getString("name");
			Integer height = rs.getInt("height");
			Date birthday = rs.getDate("birthday");
			System.out.println(
				name + " " + height + " " + birthday);
		}
		
	}
	
}
