package sql.app.article;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.person.c.Person;
import static sql.expr.ExpressionUtil.*;
import sql.tuple.Tuple2;

public class Example5 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Person person = new Person();
		Person father = new Person();
		
		List<Tuple2<String, String>> names =
			new QueryBuilder(datasource)
				.from(person, father)
				.where(eq(person.fatherId, father.id))
				.select(person.name, father.name)
				.list();
		
		for (Tuple2<String, String> tuple2 : names) {
			String name = tuple2.v1;
			String fatherName = tuple2.v2;
			System.out.println(name + " " + fatherName);
		}
		
	}
	
}
