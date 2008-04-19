package sql.app;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import sql.dict.person.a.Person;

public class Example2 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Person p = new Person();
		
		Connection con = datasource.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"SELECT " + p.name + ", " + p.height + ", " + p.birthday
				+ " FROM " + p);
		ps.execute();
		ResultSet rs = ps.getResultSet();
		while (rs.next()) {
			String name = rs.getString(p.name.getName());
			Integer height = rs.getInt(p.height.getName());
			Date birthday = rs.getDate(p.birthday.getName());
			System.out.println(name + " " + height + " " + birthday);
		}
		rs.close();
		con.close();
		
	}
	
}
