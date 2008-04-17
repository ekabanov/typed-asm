package sql;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import sql.builder.QueryBuilder;
import sql.dict.Person;
import sql.expr.ExpressionUtil;
import sql.tuple.Tuple2;

public class Example5 {

	public static void main(String[] args) throws SQLException {
		
		DataSource datasource = null;
		
		Person person = new Person("p"), father = new Person("f");
		
		List<Tuple2<String, String>> names = new QueryBuilder(datasource)
			.from(person, father)
			.where(ExpressionUtil.eq(person.fatherId, father.id))
			.select(person.name, father.name)
			.find();
		for (Tuple2<String, String> tuple2 : names) {
			String name = tuple2.v1;
			String fatherName = tuple2.v2;
			System.out.println(name + " " + fatherName);
		}
		
	}
	
}
