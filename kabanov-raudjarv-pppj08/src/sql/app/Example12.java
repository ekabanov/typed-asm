package sql.app;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.builder.select.SelectBuilderC1;
import sql.dict.person.c.Person;

public class Example12 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;

		Person p = new Person();
		
		SelectBuilderC1<Integer> q = new QueryBuilder(datasource)
			.from(p)
			.select(p.id);
			
		List<Integer> rows = new QueryBuilder(datasource)
			.from(q)
			.select(q.c1())
			.list();
		
		for (Integer id : rows) {
			System.out.println(id);
		}
		
	}
	
}
