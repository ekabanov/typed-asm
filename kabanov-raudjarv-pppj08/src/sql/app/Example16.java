package sql.app;

import static sql.expr.ExpressionUtil.unchecked;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.person.c.Person;
import sql.expr.SelectExpression;
import sql.expr.ExpressionUtil.UncheckedBuilder;
import sql.tuple.Tuple2;

public class Example16 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		UncheckedBuilder<Integer> ub = unchecked(Integer.class);
		ub.append("util.countChildren(id)");
		SelectExpression<Integer> u = ub.end();
		
		Person p = new Person();
		
		List<Tuple2<String, Integer>> rows =
			new QueryBuilder(datasource)
				.from(p)
				.select(p.name, u)
				.list();
		
		for (Tuple2<String, Integer> row : rows) {
			System.out.println(row.v1 + " " + row.v2);
		}
		
	}
	
}
