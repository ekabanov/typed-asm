package sql.dict.person.a;

import java.util.Date;
import sql.expr.Column;
import sql.expr.Table;


public class Person implements Table {
	
	private final String alias;
	
	public Person(String alias) { this.alias = alias; }
	public Person() { this.alias = null; }
	
	public String getName() { return "person"; };
	public String getAlias() { return alias; }
	
	public final Column<String> name = new Column<String>(this, "name", String.class);
	public final Column<Integer> height = new Column<Integer>(this, "height", Integer.class);
	public final Column<Date> birthday = new Column<Date>(this, "birthday", Date.class);

}
