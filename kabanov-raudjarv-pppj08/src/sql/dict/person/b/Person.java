package sql.dict.person.b;

import sql.expr.Column;
import sql.expr.Table;


public class Person extends Table {
	
	private final String alias;
	
	public Person(String alias) { this.alias = alias; }
	public Person() { this.alias = null; }
	
	public String getName() { return "person"; };
	public String getAlias() { return alias; }
	
	public final Column<String> firstName = new Column<String>(this, "first_name", String.class);
	public final Column<String> lastName = new Column<String>(this, "last_name", String.class);

}
