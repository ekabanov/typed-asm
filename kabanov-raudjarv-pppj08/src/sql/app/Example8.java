package sql.app;

import static sql.expr.ExpressionUtil.eq;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.builder.select.SelectBuilderC0;
import sql.builder.select.SelectBuilderC1;
import sql.builder.transform.ColumnAppenderC1;
import sql.dict.person.c.Person;
import sql.tuple.Tuple2;

public class Example8 {
	public static void main(String[] args) throws SQLException {
		DataSource datasource = null;
		
		final Person person = new Person();
		List<Tuple2<String, String>> names = new QueryBuilder(datasource)
			.from(person)
			.select(person.name)
			.appender(new ColumnAppenderC1<String>() {
				public SelectBuilderC1<String> append(SelectBuilderC0 builder) {
					Person father = new Person();
					return builder
						.addTables(father)
						.addColumn(father.name)
						.addConditions(eq(person.fatherId, father.id));
				}
			})
			.list();
		
		for (Tuple2<String, String> tuple2 : names) {
			System.out.println(tuple2.v1 + " " + tuple2.v2);
		}		
	}
}
