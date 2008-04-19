package sql.app;

import static sql.expr.ExpressionUtil.in;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.person.c.Person;

public class Example13 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;

		Person p = new Person();
		
		List<Integer> rows = new QueryBuilder(datasource)
			.from(p)
			.where(in(p.id).value(10).value(15).end())
			.select(p.id)
			.list();
		
		for (Integer id : rows) {
			System.out.println(id);
		}
		
	}
	
}
