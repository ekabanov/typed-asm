package sql.app;

import static sql.expr.ExpressionUtil.*;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.Person;
import sql.expr.Alias;
import sql.tuple.Tuple2;

public class Example10 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Person p = new Person();
		Alias<String> title = alias(concat(p.name, p.id), "title");
		
		List<Tuple2<String, String>> names = new QueryBuilder(datasource)
			.from(p)
			.where(not(isNull(title)))
			.orderBy(asc(p.name), desc(title))
			.select(p.name, title)
			.list();
		for (Tuple2<String, String> tuple2 : names) {
			String name = tuple2.v1;
			String tit = tuple2.v2;
			System.out.println(name + " " + tit);
		}
		
	}
	
}
