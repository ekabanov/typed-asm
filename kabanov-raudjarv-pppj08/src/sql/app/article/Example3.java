package sql.app.article;

import static sql.expr.ExpressionUtil.*;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.person.a.Person;
import sql.tuple.Tuple3;

public class Example3 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Person p = new Person();
		
		List<Tuple3<String, Integer, Date>> persons =
			new QueryBuilder(datasource)
				.from(p)
				.where(or(
						eq(p.name, "Peter"),
						gt(p.height, 170))
					)
				.select(p.name, p.height, p.birthday)
				.list();
		
		for (Tuple3<String, Integer, Date> person : persons) {
			String name = person.v1;
			Integer height = person.v2;
			Date birthday = person.v3;
			System.out.println(name + " " + height + " " + birthday);
		}
		
	}
	
}
