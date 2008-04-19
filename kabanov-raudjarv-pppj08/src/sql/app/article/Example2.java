package sql.app.article;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.person.a.Person;
import sql.tuple.Tuple3;
import static sql.expr.ExpressionUtil.*;

public class Example2 {

	public static void main(String[] args) throws SQLException {
		DataSource datasource = null;

		Person p = new Person(); 
		
		List<Tuple3<String, Integer, Date>> rows =
			new QueryBuilder(datasource)
				.from(p)
				.where(gt(p.height, 170))
				.select(p.name, p.height, p.birthday)
				.list();
		
		for (Tuple3<String, Integer, Date> row : rows) {
			String name = row.v1;
			Integer height = row.v2;
			Date birthday = row.v3;
			System.out.println(
				name + " " + height + " " + birthday);
		}
	}

}
