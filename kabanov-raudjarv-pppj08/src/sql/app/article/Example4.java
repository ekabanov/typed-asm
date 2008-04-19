package sql.app.article;

import static sql.expr.ExpressionUtil.*;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.person.b.Person;
import sql.expr.Alias;

public class Example4 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Person p = new Person();
		Alias<String> fullName =
			alias(concat(p.firstName, constant(" "), p.lastName));
		
		List<String> names = new QueryBuilder(datasource)
			.from(p)
			.where(not(eq(fullName, "Peter Griffin")))
			.select(fullName)
			.list();
		
		for (String name : names) {
			System.out.println(name);
		}
		
	}
	
}
