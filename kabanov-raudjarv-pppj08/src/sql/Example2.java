package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.sql.DataSource;
import sql.dict.Person;
import sql.dict.Person1;

public class Example2 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Connection con = datasource.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"SELECT " + Person1.NAME + ", " + Person1.HEIGHT + ", " + Person1.BIRTHDAY
				+ " FROM " + Person.TABLE);
		ps.execute();
		ResultSet rs = ps.getResultSet();
		while (rs.next()) {
			String name = rs.getString(Person1.NAME.getName());
			Integer height = rs.getInt(Person1.HEIGHT.getName());
			Date birthday = rs.getDate(Person1.BIRTHDAY.getName());
			System.out.println(name + " " + height + " " + birthday);
		}
		rs.close();
		con.close();
		
	}
	
}
