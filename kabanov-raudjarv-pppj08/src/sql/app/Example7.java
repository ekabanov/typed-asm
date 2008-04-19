package sql.app;

import static sql.expr.ExpressionUtil.eq;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.builder.select.SelectBuilderC1;
import sql.builder.select.SelectBuilderC2;
import sql.builder.select.SelectBuilderC1.ClosureC1;
import sql.builder.select.SelectBuilderC2.ClosureC2;
import sql.dict.person.c.Person;
import sql.tuple.Tuple2;

public class Example7 {
	public static void main(String[] args) throws SQLException {
		DataSource datasource = null;
		
		final ClosureC2<String, String> printClosure = new ClosureC2<String, String>() {
			public void apply(SelectBuilderC2<String, String> builder) {
				List<Tuple2<String, String>> names = builder.list();
				for (Tuple2<String, String> tuple2 : names) {
					System.out.println(tuple2.v1 + " " + tuple2.v2);
				}
			}
		};
		
		final Person person = new Person("p");
		new QueryBuilder(datasource)
			.from(person)
			.select(person.name)
			.closure(new ClosureC1<String>() {
				public void apply(SelectBuilderC1<String> builder) {
					Person father = new Person("f");
					builder
						.addTables(father)
						.addColumn(father.name)
						.addConditions(eq(person.fatherId, father.id))
						.closure(printClosure);
				}
			});
	}
}
