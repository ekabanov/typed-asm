package sql.app;

import static sql.expr.ExpressionUtil.*;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.Builder;
import sql.builder.Closure;
import sql.builder.QueryBuilder;
import sql.dict.Person;
import sql.tuple.Tuple2;

public class Example11 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		final String searchName = null;
		
		final Person person = new Person("p");
		
		List<Tuple2<Integer, String>> rows = new QueryBuilder(datasource)
			.from(person)
			.closure(new Closure() {
				public void apply(Builder builder) {
					if (searchName != null) {
						builder.addConditions(eq(person.name, constant(searchName)));
						builder.addDirections(asc(person.name));
					}
				}
			})
			.select(person.id, person.name)
			.list();
		
		for (Tuple2<Integer, String> row : rows) {
			Integer id = row.v1;
			String name = row.v2;
			System.out.println(id + " " + name);
		}
		
	}
	
}
