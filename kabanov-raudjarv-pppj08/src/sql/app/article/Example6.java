package sql.app.article;

import static sql.expr.ExpressionUtil.eq;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.Builder;
import sql.builder.Closure;
import sql.builder.QueryBuilder;
import sql.dict.person.c.Person;
import sql.tuple.Tuple2;

public class Example6 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		final String searchName = null;
		
		final Person p = new Person();
		
		List<Tuple2<Integer, String>> rows =
			new QueryBuilder(datasource)
				.from(p)
				.closure(new Closure() {
					public void apply(Builder builder) {
						if (searchName != null) {
							builder.addConditions(
								eq(p.name, searchName));
						}
					}
				})
				.select(p.id, p.name)
				.list();
		
		for (Tuple2<Integer, String> row : rows) {
			Integer id = row.v1;
			String name = row.v2;
			System.out.println(id + " " + name);
		}
		
	}
	
}
