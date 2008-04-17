package sql;

import static sql.expr.ExpressionUtil.constant;
import static sql.expr.ExpressionUtil.eq;
import static sql.expr.ExpressionUtil.gt;
import static sql.expr.ExpressionUtil.or;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.Person;
import sql.dict.Person1;
import sql.tuple.Tuple3;

public class Example4 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		List<Tuple3<String, Integer, Date>> persons = new QueryBuilder(datasource)
			.from(Person.TABLE)
					.where(or(
							eq(Person1.NAME, constant("Peter")),
							gt(Person1.HEIGHT, constant(170)))
					)
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
