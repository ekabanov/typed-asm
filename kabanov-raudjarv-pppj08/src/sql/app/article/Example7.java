package sql.app.article;

import static sql.expr.ExpressionUtil.*;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.Person;
import sql.expr.Alias;
import sql.tuple.Tuple2;

public class Example7 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Person p = new Person();
		Alias<Integer> count = alias(count(p.id));
		
		List<Tuple2<Integer, Integer>> rows =
			new QueryBuilder(datasource)
				.from(p)
				.where(not(eq(p.name, "Peter")))
				.groupBy(p.fatherId)
				.having(gt(count, 3))
				.orderBy(desc(count))
				.select(p.fatherId, count)
				.list();
		
		for (Tuple2<Integer, Integer> row : rows) {
			Integer id = row.v1;
			Integer cnt = row.v2;
			System.out.println(id + " " + cnt);
		}
		
	}
	
}
