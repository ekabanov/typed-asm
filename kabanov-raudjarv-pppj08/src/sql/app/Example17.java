package sql.app;

import static sql.expr.ExpressionUtil.*;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.person.c.Person;
import sql.expr.SelectExpression;
import sql.tuple.Tuple2;

public class Example17 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Person p = new Person();
		
		List<Tuple2<String, Integer>> rows =
			new QueryBuilder(datasource)
				.from(uncheckedFrom("( SELECT * FROM person )"))
				.select(p.name, p.id)
				.list();
		
		for (Tuple2<String, Integer> row : rows) {
			System.out.println(row.v1 + " " + row.v2);
		}
		
	}
	
}
