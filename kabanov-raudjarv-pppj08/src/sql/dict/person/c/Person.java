package sql.dict.person.c;

import sql.dict.Column;
import sql.dict.Table;


public class Person implements Table {
	
	private final String alias;
	
	public Person(String alias) { this.alias = alias; }
	public Person() { this.alias = null; }
	
	public String getName() { return "person"; };
	public String getAlias() { return alias; }
	
	public final Column<Integer> id = new Column<Integer>(this, "id", Integer.class);
	public final Column<String> name = new Column<String>(this, "name", String.class);
	public final Column<Integer> fatherId = new Column<Integer>(this, "father_id", Integer.class);

}