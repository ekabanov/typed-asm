package sql.app;

import static sql.expr.ExpressionUtil.*;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.person.c.Person;

public class Example14 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;

		Person p = new Person();
		Person r = new Person();
		
		List<Integer> rows = new QueryBuilder(datasource)
			.from(p)
			.where(exists(
				new QueryBuilder(datasource)
					.from(r)
					.where(eq(p.fatherId, r.id))
					.select(r.id)))
			.select(p.id)
			.list();
		
		for (Integer id : rows) {
			System.out.println(id);
		}
		
	}
	
}
