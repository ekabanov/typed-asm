package sql;

import static sql.expr.ExpressionUtil.unchecked;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.Person;
import sql.tuple.Tuple2;

public class Example13 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Person p = new Person();
		List<Tuple2<String, Integer>> rows = new QueryBuilder(datasource)
			.from(p)
			.select(p.name, unchecked(Integer.class, "util.countChildren(id)"))
			.find();
		
		for (Tuple2<String, Integer> row : rows) {
			System.out.println(row.v1 + " " + row.v2);
		}
		
	}
	
}
