package sql;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.Person;
import sql.dict.Person1;
import sql.tuple.Tuple3;

public class Example3 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		// from(Person.TABLE).select(Person.FIRST_NAME, Person.LAST_NAME);
		
		List<Tuple3<String, Integer, Date>> persons = new QueryBuilder(datasource)
			.from(Person.TABLE)
			.select(Person1.NAME, Person1.HEIGHT, Person1.BIRTHDAY)
			.find();
		for (Tuple3<String, Integer, Date> person : persons) {
			String name = person.v1;
			Integer height = person.v2;
			Date birthday = person.v3;
			System.out.println(name + " " + height + " " + birthday);
		}
		
	}
	
}
